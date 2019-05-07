package com.authentec.drmagent.v2.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.util.Config;
import android.util.Log;
import com.authentec.drmagent.v2.HTTPConnectionHelper;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DRMUtilities
{
  public static boolean DEBUG = Config.DEBUG;
  public static final String TAG = "DRMUtilities";
  public static String mApplicationPath;
  public static HTTPConnectionHelper mHTTPConnectionHelper;
  private static MessageDigest sDigest;
  
  public DRMUtilities() {}
  
  public static void DEFENSE(String paramString, Object paramObject)
  {
    if (paramObject == null) {
      throw new NullPointerException(paramString);
    }
  }
  
  public static long copyStream(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    return copyStream(paramInputStream, paramOutputStream, 0);
  }
  
  public static long copyStream(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    Object localObject2 = new StringBuilder().append("Starting copying ");
    Object localObject1;
    long l3;
    if (paramInt == 0)
    {
      localObject1 = "all";
      d("DRMUtilities", localObject1 + " byte(s)");
      l3 = System.currentTimeMillis();
      if ((paramInt <= 0) || (paramInt >= 16192)) {
        break label249;
      }
    }
    label249:
    for (int i = paramInt;; i = 16192)
    {
      d("DRMUtilities", "Copying buffer size: " + i);
      paramInputStream = new BufferedInputStream(paramInputStream, i);
      localObject1 = new BufferedOutputStream(paramOutputStream, i);
      localObject2 = new byte[i];
      long l1 = 0L;
      long l2;
      do
      {
        do
        {
          int j = paramInputStream.read((byte[])localObject2, 0, i);
          l2 = l1;
          if (j == -1) {
            break;
          }
          ((BufferedOutputStream)localObject1).write((byte[])localObject2, 0, j);
          l2 = l1 + j;
          l1 = l2;
        } while (paramInt <= 0);
        l1 = l2;
      } while (l2 <= paramInt);
      ((BufferedOutputStream)localObject1).flush();
      ((BufferedOutputStream)localObject1).close();
      paramOutputStream.flush();
      paramOutputStream.close();
      l1 = System.currentTimeMillis();
      d("DRMUtilities", "Copying completed: " + l2 + " byte(s) in " + (l1 - l3) + " millisecond(s)");
      return l2;
      localObject1 = Integer.valueOf(paramInt);
      break;
    }
  }
  
  public static AndroidHttpClient createHttpClient(URL paramURL)
  {
    if (mHTTPConnectionHelper != null)
    {
      paramURL = mHTTPConnectionHelper.getUserAgent(paramURL);
      if (paramURL != null) {
        return AndroidHttpClient.newInstance(paramURL);
      }
    }
    return AndroidHttpClient.newInstance("AuthenTec DRM Agent for Android");
  }
  
  public static void d(String paramString1, String paramString2)
  {
    if (DEBUG) {
      Log.d(paramString1, paramString2);
    }
  }
  
  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (DEBUG) {
      Log.d(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void e(String paramString1, String paramString2)
  {
    if (DEBUG) {
      Log.e(paramString1, paramString2);
    }
  }
  
  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (DEBUG) {
      Log.e(paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void i(String paramString1, String paramString2)
  {
    if (DEBUG) {
      Log.i(paramString1, paramString2);
    }
  }
  
  public static boolean isSecure(Context paramContext)
  {
    Object localObject1 = new ArrayList(Arrays.asList(new String[] { "/system/app/SuperUser.apk", "com.noshufou.android.su-1.apk", "/system/xbin/su" })).iterator();
    while (((Iterator)localObject1).hasNext()) {
      if (new File((String)((Iterator)localObject1).next()).exists()) {
        return false;
      }
    }
    Object localObject2 = paramContext.getPackageManager().getInstalledApplications(0);
    localObject1 = new ArrayList(Arrays.asList(new String[] { "superuser.apk", "androidroot.apk", "root.apk", "com.noshufou.android.su-1.apk" }));
    Object localObject3;
    Object localObject4;
    String str;
    do
    {
      localObject2 = ((List)localObject2).iterator();
      while (!((Iterator)localObject4).hasNext())
      {
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
        localObject4 = ((List)localObject1).iterator();
      }
      str = (String)((Iterator)localObject4).next();
    } while (!((ApplicationInfo)localObject3).publicSourceDir.toLowerCase().contains(str));
    return false;
    localObject1 = paramContext.getPackageManager().getInstalledPackages(0);
    paramContext = new ArrayList(Arrays.asList(new String[] { "com.noshufou.android.su", "superuser" }));
    do
    {
      localObject1 = ((List)localObject1).iterator();
      while (!((Iterator)localObject3).hasNext())
      {
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        localObject3 = paramContext.iterator();
      }
      localObject4 = (String)((Iterator)localObject3).next();
    } while (!((PackageInfo)localObject2).packageName.contains((CharSequence)localObject4));
    return false;
    try
    {
      paramContext = Runtime.getRuntime().exec("ls -l /data");
      paramContext.waitFor();
      i = paramContext.exitValue();
      if (i == 0) {
        return false;
      }
    }
    catch (Exception paramContext)
    {
      try
      {
        paramContext = Runtime.getRuntime().exec("su -c ls");
        paramContext.waitFor();
        int i = paramContext.exitValue();
        if (i == 0) {
          return false;
        }
      }
      catch (Exception paramContext) {}
    }
    return true;
  }
  
  public static String md5(byte[] paramArrayOfByte)
  {
    try
    {
      d("DRMUtilities", "Initiating MD5 Calculation: " + paramArrayOfByte.length + " byte(s) to process");
      long l1 = System.currentTimeMillis();
      if (sDigest == null) {
        sDigest = MessageDigest.getInstance("MD5");
      }
      sDigest.update(paramArrayOfByte);
      byte[] arrayOfByte = sDigest.digest();
      long l2 = System.currentTimeMillis();
      d("DRMUtilities", "MD5 calculation completed: " + paramArrayOfByte.length + " byte(s) processed in " + (l2 - l1) + " millisecond(s)");
      StringBuffer localStringBuffer = new StringBuffer();
      int j = arrayOfByte.length;
      int i = 0;
      while (i < j)
      {
        for (paramArrayOfByte = Integer.toHexString(arrayOfByte[i] & 0xFF); paramArrayOfByte.length() < 2; paramArrayOfByte = "0" + paramArrayOfByte) {}
        localStringBuffer.append(paramArrayOfByte);
        i += 1;
      }
      l2 = System.currentTimeMillis();
      d("DRMUtilities", "MD5 hex conversion completed: " + (l2 - l1) + " millisecond(s)");
      paramArrayOfByte = localStringBuffer.toString();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      throw new RuntimeException(paramArrayOfByte.getMessage());
    }
    finally {}
  }
  
  public static String retrieveApplicationPath(Context paramContext)
  {
    if (mApplicationPath != null) {
      return mApplicationPath;
    }
    if (paramContext != null)
    {
      paramContext = paramContext.getFilesDir();
      if (paramContext == null)
      {
        w("DRMUtilities", "Context returned null for 'filesDir', will return null");
        return null;
      }
      String str = paramContext.getParent();
      paramContext = str;
      if (!str.endsWith("/")) {
        paramContext = str.concat("/");
      }
      d("DRMUtilities", "Application path is " + paramContext);
      mApplicationPath = paramContext;
    }
    return mApplicationPath;
  }
  
  public static void v(String paramString1, String paramString2)
  {
    if (DEBUG) {
      Log.v(paramString1, paramString2);
    }
  }
  
  public static void w(String paramString1, String paramString2)
  {
    if (DEBUG) {
      Log.w(paramString1, paramString2);
    }
  }
}
