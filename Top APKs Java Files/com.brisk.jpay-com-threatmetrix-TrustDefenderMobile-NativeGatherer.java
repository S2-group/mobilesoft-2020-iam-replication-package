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
  private NativeGatherer.NativeGathererHelper m_gathererHelper = new NativeGatherer.NativeGathererHelper(this);
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
    Log.d(TAG, "Starting path find for apk");
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
    Log.d(TAG, "findAPKPaths found : " + paramContext.size());
    this.m_appList = ((String[])paramContext.toArray(new String[paramContext.size()]));
    return this.m_appList;
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
  
  public int cancel()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        int i = this.m_gathererHelper.cancel();
        return i;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(TAG, "Native code:", localThrowable);
    }
    return -1;
  }
  
  public String[] checkURLs(String[] paramArrayOfString)
  {
    try
    {
      String str2 = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      if (this.m_gathererHelper.m_available) {}
      for (String str1 = " available ";; str1 = "not available ")
      {
        Log.d(str2, str1 + " Found " + this.m_gathererHelper.m_packagesFound);
        if ((!this.m_gathererHelper.m_available) || (paramArrayOfString == null)) {
          break;
        }
        paramArrayOfString = this.m_gathererHelper.checkURLs(paramArrayOfString);
        if (!Thread.interrupted()) {
          return paramArrayOfString;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramArrayOfString)
    {
      Log.e(TAG, "Native code:", paramArrayOfString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramArrayOfString = null;
    return paramArrayOfString;
  }
  
  public String[] findAllProcs()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String[] arrayOfString1 = this.m_gathererHelper.findAllProcs();
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(TAG, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public String[] findInstalledProcs()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String[] arrayOfString1 = this.m_gathererHelper.findInstalledProcs();
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(TAG, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  public int findPackages(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
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
    }
    catch (Throwable paramContext)
    {
      Log.e(TAG, "Native code:", paramContext);
      i = j;
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    return i;
  }
  
  public String[] findRunningProcs()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String[] arrayOfString1 = this.m_gathererHelper.findRunningProcs();
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(TAG, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String[] arrayOfString2 = null;
    return arrayOfString2;
  }
  
  void finit()
  {
    if (this.m_gathererHelper.m_available) {
      this.m_gathererHelper.finit();
    }
    this.m_gathererHelper.m_available = false;
  }
  
  public String getBinaryArch()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String str = this.m_gathererHelper.getBinaryArch();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(TAG, "Native code:", localThrowable);
    }
    return null;
  }
  
  public String getConfig(String paramString)
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.getConfig(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(TAG, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public List<String> getFontList(String paramString)
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
          Log.d(TAG, "Thread interrupt detected, throwing");
          throw new InterruptedException();
        }
        paramString = new ArrayList();
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(TAG, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public String[] getNetworkInfo()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        String[] arrayOfString = this.m_gathererHelper.getNetworkInfo();
        return arrayOfString;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(TAG, "Native code:", localThrowable);
    }
    return null;
  }
  
  public String getRandomString(int paramInt)
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramInt > 0))
      {
        String str1 = this.m_gathererHelper.getRandomString(paramInt);
        if (!Thread.interrupted()) {
          return ???;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(TAG, "Native code:", localThrowable);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    String str2 = null;
    return str2;
  }
  
  public String hashFile(String paramString)
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.hashFile(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(TAG, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public boolean init(Context paramContext)
  {
    return this.m_gathererHelper.init(paramContext);
  }
  
  public boolean isAvailable()
  {
    return this.m_gathererHelper.m_available;
  }
  
  public String md5(String paramString)
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.md5(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(TAG, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public int setConfig(String paramString1, String paramString2)
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString1 != null) && (paramString2 != null))
      {
        i = this.m_gathererHelper.setConfig(paramString1, paramString2);
        if (!Thread.interrupted()) {
          return i;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString1)
    {
      Log.e(TAG, "Native code:", paramString1);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    int i = -1;
    return i;
  }
  
  public String sha1(String paramString)
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.sha1(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(TAG, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public String urlEncode(String paramString)
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString != null))
      {
        paramString = this.m_gathererHelper.urlEncode(paramString);
        if (!Thread.interrupted()) {
          return paramString;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString)
    {
      Log.e(TAG, "Native code:", paramString);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString = null;
    return paramString;
  }
  
  public int waitUntilCancelled()
  {
    try
    {
      if (this.m_gathererHelper.m_available)
      {
        int i = this.m_gathererHelper.waitUntilCancelled();
        return i;
      }
    }
    catch (Throwable localThrowable)
    {
      Log.e(TAG, "Native code:", localThrowable);
    }
    return -1;
  }
  
  public String xor(String paramString1, String paramString2)
  {
    try
    {
      if ((this.m_gathererHelper.m_available) && (paramString2 != null) && (paramString1 != null) && (paramString2.length() > 0) && (!paramString1.isEmpty()))
      {
        paramString1 = this.m_gathererHelper.xor(paramString1, paramString2);
        if (!Thread.interrupted()) {
          return paramString1;
        }
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    catch (Throwable paramString1)
    {
      Log.e(TAG, "Native code:", paramString1);
    }
    finally
    {
      if (Thread.interrupted())
      {
        Log.d(TAG, "Thread interrupt detected, throwing");
        throw new InterruptedException();
      }
    }
    paramString1 = null;
    return paramString1;
  }
}
