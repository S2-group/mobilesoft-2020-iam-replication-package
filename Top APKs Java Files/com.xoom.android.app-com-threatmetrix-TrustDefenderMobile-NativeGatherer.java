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
  private NativeGathererHelper e = new NativeGathererHelper();
  private String[] f = null;
  private long g = 0L;
  
  static
  {
    if (!NativeGatherer.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      c = af.a(NativeGatherer.class);
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
    Object localObject = c;
    this.g = System.nanoTime();
    localObject = paramContext.getPackageManager().getInstalledApplications(0);
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
    localObject = c;
    new StringBuilder("findAPKPaths found : ").append(paramContext.size());
    this.f = ((String[])paramContext.toArray(new String[paramContext.size()]));
    return this.f;
  }
  
  public final int a(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
    throws InterruptedException
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
        paramContext = c;
        throw new InterruptedException();
      }
    }
    return i;
  }
  
  public final int a(String paramString1, String paramString2)
    throws InterruptedException
  {
    try
    {
      if ((this.e.a) && (paramString1 != null) && (paramString2 != null))
      {
        i = this.e.setConfig(paramString1, paramString2);
        if (!Thread.interrupted()) {
          return i;
        }
        paramString1 = c;
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
        paramString1 = c;
        throw new InterruptedException();
      }
    }
    int i = -1;
    return i;
  }
  
  public final String a(int paramInt)
    throws InterruptedException
  {
    try
    {
      if (this.e.a)
      {
        str1 = this.e.getRandomString(32);
        if (!Thread.interrupted()) {
          return str1;
        }
        str1 = c;
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      String str1;
      Log.e(c, "Native code:", localThrowable);
    }
    finally
    {
      String str2;
      if (Thread.interrupted())
      {
        str3 = c;
        throw new InterruptedException();
      }
    }
    String str3 = null;
    return str3;
  }
  
  public final String a(String paramString)
    throws InterruptedException
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.hashFile(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        paramString = c;
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
        paramString = c;
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final boolean a(Context paramContext)
  {
    return this.e.a(paramContext);
  }
  
  public final String[] a(String[] paramArrayOfString)
    throws InterruptedException
  {
    try
    {
      String str = c;
      StringBuilder localStringBuilder = new StringBuilder();
      if (this.e.a) {}
      for (str = " available ";; str = "not available ")
      {
        localStringBuilder.append(str).append(" Found ").append(this.e.b);
        if ((!this.e.a) || (paramArrayOfString == null)) {
          break;
        }
        paramArrayOfString = this.e.checkURLs(paramArrayOfString);
        if (!Thread.interrupted()) {
          return paramArrayOfString;
        }
        paramArrayOfString = c;
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
        paramArrayOfString = c;
        throw new InterruptedException();
      }
    }
    paramArrayOfString = null;
    return paramArrayOfString;
  }
  
  public final String b(String paramString)
    throws InterruptedException
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.md5(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        paramString = c;
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
        paramString = c;
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String b(String paramString1, String paramString2)
    throws InterruptedException
  {
    try
    {
      if ((this.e.a) && (paramString2 != null) && (paramString1 != null) && (paramString2.length() > 0) && (!paramString1.isEmpty()))
      {
        paramString1 = this.e.xor(paramString1, paramString2);
        if (!Thread.interrupted()) {
          return paramString1;
        }
        paramString1 = c;
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
        paramString1 = c;
        throw new InterruptedException();
      }
    }
    paramString1 = null;
    return paramString1;
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
    throws InterruptedException
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.getConfig(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        paramString = c;
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
        paramString = c;
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
    throws InterruptedException
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.sha1(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        paramString = c;
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
        paramString = c;
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String e(String paramString)
    throws InterruptedException
  {
    try
    {
      if ((this.e.a) && (paramString != null))
      {
        paramString = this.e.urlEncode(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        paramString = c;
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
        paramString = c;
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String[] e()
    throws InterruptedException
  {
    try
    {
      if (this.e.a)
      {
        localObject1 = this.e.findRunningProcs();
        if (!Thread.interrupted()) {
          return localObject1;
        }
        localObject1 = c;
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Object localObject1;
      Log.e(c, "Native code:", localThrowable);
    }
    finally
    {
      String str1;
      if (Thread.interrupted())
      {
        str2 = c;
        throw new InterruptedException();
      }
    }
    String str2 = null;
    return str2;
  }
  
  public final List<String> f(String paramString)
    throws InterruptedException
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
          paramString = c;
          throw new InterruptedException();
        }
        paramString = new ArrayList();
        if (!Thread.interrupted()) {
          return paramString;
        }
        paramString = c;
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
        paramString = c;
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public final String[] f()
    throws InterruptedException
  {
    try
    {
      if (this.e.a)
      {
        localObject1 = this.e.findInstalledProcs();
        if (!Thread.interrupted()) {
          return localObject1;
        }
        localObject1 = c;
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Object localObject1;
      Log.e(c, "Native code:", localThrowable);
    }
    finally
    {
      String str1;
      if (Thread.interrupted())
      {
        str2 = c;
        throw new InterruptedException();
      }
    }
    String str2 = null;
    return str2;
  }
  
  public final String[] g()
    throws InterruptedException
  {
    try
    {
      if (this.e.a)
      {
        localObject1 = this.e.findAllProcs();
        if (!Thread.interrupted()) {
          return localObject1;
        }
        localObject1 = c;
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Object localObject1;
      Log.e(c, "Native code:", localThrowable);
    }
    finally
    {
      String str1;
      if (Thread.interrupted())
      {
        str2 = c;
        throw new InterruptedException();
      }
    }
    String str2 = null;
    return str2;
  }
  
  public final String h()
  {
    try
    {
      if (this.e.a)
      {
        String str = this.e.getBinaryArch();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(c, "Native code:", localThrowable);
    }
    return null;
  }
  
  public final String[] i()
  {
    try
    {
      if (this.e.a)
      {
        String[] arrayOfString = this.e.getNetworkInfo();
        return arrayOfString;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(c, "Native code:", localThrowable);
    }
    return null;
  }
  
  private class NativeGathererHelper
  {
    boolean a = false;
    int b = 0;
    String[] c = { "/system/app", "/system/priv-app" };
    private final String f = af.a(NativeGathererHelper.class);
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
    final boolean a(Context paramContext)
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
      //   77: getstatic 102	com/threatmetrix/TrustDefenderMobile/aq:a	Ljava/lang/Integer;
      //   80: invokevirtual 108	java/lang/Integer:intValue	()I
      //   83: aload_1
      //   84: invokevirtual 112	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:init	(ILjava/lang/String;)Z
      //   87: putfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   90: aload_0
      //   91: getfield 44	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:f	Ljava/lang/String;
      //   94: astore_1
      //   95: new 114	java/lang/StringBuilder
      //   98: dup
      //   99: ldc 116
      //   101: invokespecial 118	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   104: aload_0
      //   105: getfield 48	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:b	I
      //   108: invokevirtual 122	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   111: pop
      //   112: aload_0
      //   113: getfield 61	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:g	Ljava/util/concurrent/locks/Lock;
      //   116: invokeinterface 77 1 0
      //   121: aload_0
      //   122: getfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   125: ireturn
      //   126: astore_1
      //   127: aload_0
      //   128: iconst_0
      //   129: putfield 46	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:a	Z
      //   132: goto -42 -> 90
      //   135: astore_1
      //   136: aload_0
      //   137: getfield 61	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:g	Ljava/util/concurrent/locks/Lock;
      //   140: invokeinterface 77 1 0
      //   145: aload_1
      //   146: athrow
      //   147: astore_1
      //   148: aload_0
      //   149: getfield 44	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:f	Ljava/lang/String;
      //   152: ldc 124
      //   154: aload_1
      //   155: invokestatic 129	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   158: pop
      //   159: goto -69 -> 90
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	162	0	this	NativeGathererHelper
      //   0	162	1	paramContext	Context
      //   50	11	2	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   62	90	126	java/lang/UnsatisfiedLinkError
      //   30	51	135	finally
      //   62	90	135	finally
      //   90	112	135	finally
      //   127	132	135	finally
      //   148	159	135	finally
      //   62	90	147	java/lang/Throwable
    }
    
    native int cancel();
    
    native String[] checkURLs(String[] paramArrayOfString);
    
    protected void finalize()
      throws Throwable
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
