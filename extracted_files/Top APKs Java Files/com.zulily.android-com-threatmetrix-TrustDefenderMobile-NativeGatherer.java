package com.threatmetrix.TrustDefenderMobile;

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

class NativeGatherer
{
  static final int POPULATE_LIST_ALL = 3;
  static final int POPULATE_LIST_AND_FLUSH = 7;
  static final int POPULATE_LIST_DB_ONLY = 8;
  static final int POPULATE_LIST_FLUSH_DB = 4;
  static final int POPULATE_LIST_HASHES = 2;
  static final int POPULATE_LIST_NAMES = 1;
  private static final String TAG;
  private static volatile NativeGatherer s_gatherer;
  private static final Lock s_lock;
  private String[] m_appList = null;
  private NativeGathererHelper m_gathererHelper = new NativeGathererHelper();
  private long m_lastAPKScanTime = 0L;
  
  static
  {
    if (!NativeGatherer.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      TAG = StringUtils.getLogTag(NativeGatherer.class);
      s_lock = new ReentrantLock();
      return;
    }
  }
  
  private NativeGatherer() {}
  
  private String[] findAPKPaths(Context paramContext)
  {
    if ((this.m_appList != null) && (TimeUnit.SECONDS.convert(System.nanoTime() - this.m_lastAPKScanTime, TimeUnit.NANOSECONDS) < 60L)) {
      return this.m_appList;
    }
    this.m_lastAPKScanTime = System.nanoTime();
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
    paramContext.size();
    this.m_appList = ((String[])paramContext.toArray(new String[paramContext.size()]));
    return this.m_appList;
  }
  
  private void finit()
  {
    if (this.m_gathererHelper.m_available) {
      this.m_gathererHelper.finit();
    }
    this.m_gathererHelper.m_available = false;
  }
  
  public static NativeGatherer getInstance()
  {
    if (s_gatherer == null) {}
    try
    {
      s_lock.lock();
      if (s_gatherer == null) {
        s_gatherer = new NativeGatherer();
      }
      return s_gatherer;
    }
    finally
    {
      s_lock.unlock();
    }
  }
  
  public final int cancel()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        int i = this.m_gathererHelper.cancel();
        return i;
      }
    }
    catch (Throwable localThrowable) {}
    return -1;
  }
  
  public final String[] checkURLs(String[] paramArrayOfString)
    throws InterruptedException
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramArrayOfString != null))
      {
        paramArrayOfString = this.m_gathererHelper.checkURLs(paramArrayOfString);
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
  
  public final String[] findAllProcs()
    throws InterruptedException
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String[] arrayOfString1 = this.m_gathererHelper.findAllProcs();
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
  
  public final String[] findInstalledProcs()
    throws InterruptedException
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String[] arrayOfString1 = this.m_gathererHelper.findInstalledProcs();
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
  
  public final int findPackages(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
    throws InterruptedException
  {
    assert (paramContext != null);
    int j = 0;
    int i = 0;
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        this.m_gathererHelper.m_apkPaths = findAPKPaths(paramContext);
        i = this.m_gathererHelper.findPackages(paramInt2, paramInt3, this.m_gathererHelper.m_apkPaths, paramInt1);
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
  
  public final String[] findRunningProcs()
    throws InterruptedException
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String[] arrayOfString1 = this.m_gathererHelper.findRunningProcs();
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
  
  public final String getBinaryArch()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String str = this.m_gathererHelper.getBinaryArch();
        return str;
      }
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public final String getConfig(String paramString)
    throws InterruptedException
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.getConfig(paramString);
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
  
  public final List<String> getFontList(String paramString)
    throws InterruptedException
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.getFontList(paramString);
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
  
  public final String[] getNetworkInfo()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String[] arrayOfString = this.m_gathererHelper.getNetworkInfo();
        return arrayOfString;
      }
    }
    catch (Throwable localThrowable) {}
    return null;
  }
  
  public final String getRandomString(int paramInt)
    throws InterruptedException
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String str1 = this.m_gathererHelper.getRandomString(32);
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
  
  public final String hashFile(String paramString)
    throws InterruptedException
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.hashFile(paramString);
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
  
  public final boolean init(Context paramContext)
  {
    return this.m_gathererHelper.init(paramContext);
  }
  
  public final boolean isAvailable()
  {
    return this.m_gathererHelper.m_available;
  }
  
  public final String md5(String paramString)
    throws InterruptedException
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.md5(paramString);
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
  
  public final int setConfig(String paramString1, String paramString2)
    throws InterruptedException
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString1 != null) && (paramString2 != null))
      {
        i = this.m_gathererHelper.setConfig(paramString1, paramString2);
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
  
  public final String sha1(String paramString)
    throws InterruptedException
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.sha1(paramString);
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
  
  public final String urlEncode(String paramString)
    throws InterruptedException
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.urlEncode(paramString);
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
  
  public final int waitUntilCancelled()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        int i = this.m_gathererHelper.waitUntilCancelled();
        return i;
      }
    }
    catch (Throwable localThrowable) {}
    return -1;
  }
  
  public final String xor(String paramString1, String paramString2)
    throws InterruptedException
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString2 != null) && (paramString1 != null) && (paramString2.length() > 0) && (!paramString1.isEmpty()))
      {
        paramString1 = this.m_gathererHelper.xor(paramString1, paramString2);
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
  
  private class NativeGathererHelper
  {
    private final String TAG = StringUtils.getLogTag(NativeGathererHelper.class);
    String[] m_apkPaths = { "/system/app", "/system/priv-app" };
    boolean m_available = false;
    private final Lock m_initLock = new ReentrantLock();
    int m_packagesFound = 0;
    
    static
    {
      if (!NativeGatherer.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }
    
    NativeGathererHelper() {}
    
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
    
    /* Error */
    final boolean init(Context paramContext)
    {
      // Byte code:
      //   0: getstatic 30	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:$assertionsDisabled	Z
      //   3: ifne +15 -> 18
      //   6: aload_1
      //   7: ifnonnull +11 -> 18
      //   10: new 98	java/lang/AssertionError
      //   13: dup
      //   14: invokespecial 99	java/lang/AssertionError:<init>	()V
      //   17: athrow
      //   18: aload_0
      //   19: getfield 47	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_available	Z
      //   22: ifeq +8 -> 30
      //   25: aload_0
      //   26: getfield 47	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_available	Z
      //   29: ireturn
      //   30: aload_0
      //   31: getfield 62	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_initLock	Ljava/util/concurrent/locks/Lock;
      //   34: invokeinterface 104 1 0
      //   39: aload_0
      //   40: getfield 47	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_available	Z
      //   43: ifeq +19 -> 62
      //   46: aload_0
      //   47: getfield 47	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_available	Z
      //   50: istore_2
      //   51: aload_0
      //   52: getfield 62	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_initLock	Ljava/util/concurrent/locks/Lock;
      //   55: invokeinterface 107 1 0
      //   60: iload_2
      //   61: ireturn
      //   62: ldc 109
      //   64: invokestatic 115	java/lang/System:loadLibrary	(Ljava/lang/String;)V
      //   67: aload_1
      //   68: invokevirtual 121	android/content/Context:getFilesDir	()Ljava/io/File;
      //   71: invokevirtual 126	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   74: astore_1
      //   75: aload_0
      //   76: aload_0
      //   77: getstatic 132	com/threatmetrix/TrustDefenderMobile/TrustDefenderMobileVersion:numeric	Ljava/lang/Integer;
      //   80: invokevirtual 137	java/lang/Integer:intValue	()I
      //   83: aload_1
      //   84: invokevirtual 139	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:init	(ILjava/lang/String;)Z
      //   87: putfield 47	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_available	Z
      //   90: aload_0
      //   91: getfield 62	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_initLock	Ljava/util/concurrent/locks/Lock;
      //   94: invokeinterface 107 1 0
      //   99: aload_0
      //   100: getfield 47	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_available	Z
      //   103: ireturn
      //   104: astore_1
      //   105: aload_0
      //   106: iconst_0
      //   107: putfield 47	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_available	Z
      //   110: goto -20 -> 90
      //   113: astore_1
      //   114: aload_0
      //   115: getfield 62	com/threatmetrix/TrustDefenderMobile/NativeGatherer$NativeGathererHelper:m_initLock	Ljava/util/concurrent/locks/Lock;
      //   118: invokeinterface 107 1 0
      //   123: aload_1
      //   124: athrow
      //   125: astore_1
      //   126: goto -36 -> 90
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	129	0	this	NativeGathererHelper
      //   0	129	1	paramContext	Context
      //   50	11	2	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   62	90	104	java/lang/UnsatisfiedLinkError
      //   30	51	113	finally
      //   62	90	113	finally
      //   105	110	113	finally
      //   62	90	125	java/lang/Throwable
    }
    
    native String md5(String paramString);
    
    native int setConfig(String paramString1, String paramString2);
    
    native String sha1(String paramString);
    
    native String urlEncode(String paramString);
    
    native int waitUntilCancelled();
    
    native String xor(String paramString1, String paramString2);
  }
}
