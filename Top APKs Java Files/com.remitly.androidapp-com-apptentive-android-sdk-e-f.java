package com.apptentive.android.sdk.e;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.apptentive.android.sdk.m;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class f
{
  public f() {}
  
  public static double a()
  {
    return System.currentTimeMillis() / 1000.0D;
  }
  
  public static Integer a(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    int j;
    int i;
    if (paramString != null)
    {
      paramString = paramString.split(",");
      j = paramString.length;
      i = 0;
    }
    for (;;)
    {
      localObject1 = localObject2;
      if (i < j)
      {
        localObject1 = paramString[i].trim();
        if (!((String)localObject1).startsWith("max-age=")) {
          break label103;
        }
        localObject1 = ((String)localObject1).split("=");
        if (localObject1.length != 2) {
          break label103;
        }
        localObject1 = localObject1[1];
      }
      try
      {
        int k = Integer.parseInt((String)localObject1);
        localObject1 = Integer.valueOf(k);
        return localObject1;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Throwable localThrowable;
        label103:
        for (;;) {}
      }
      m.d("Error parsing cache expiration as number: %s", localThrowable, new Object[] { localObject1 });
      i += 1;
    }
  }
  
  public static String a(Context paramContext, String paramString)
  {
    paramContext = c(paramContext, paramString);
    if (paramContext == null) {
      paramString = null;
    }
    do
    {
      return paramString;
      paramString = paramContext.toString();
      paramContext = paramString;
      if (paramString.endsWith("'")) {
        paramContext = paramString.substring(0, paramString.length() - 1);
      }
      paramString = paramContext;
    } while (!paramContext.startsWith("'"));
    return paramContext.substring(1, paramContext.length());
  }
  
  /* Error */
  public static String a(java.io.InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 86	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   9: astore 4
    //   11: sipush 8196
    //   14: newarray char
    //   16: astore 5
    //   18: new 89	java/io/InputStreamReader
    //   21: dup
    //   22: aload_0
    //   23: aload_1
    //   24: invokespecial 92	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   27: astore_0
    //   28: aload_0
    //   29: aload 5
    //   31: iconst_0
    //   32: sipush 8196
    //   35: invokevirtual 98	java/io/Reader:read	([CII)I
    //   38: istore_2
    //   39: iload_2
    //   40: iflt +27 -> 67
    //   43: aload 4
    //   45: aload 5
    //   47: iconst_0
    //   48: iload_2
    //   49: invokevirtual 102	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: goto -25 -> 28
    //   56: astore_1
    //   57: aload_0
    //   58: invokestatic 105	com/apptentive/android/sdk/e/f:a	(Ljava/io/Closeable;)V
    //   61: aload 4
    //   63: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: areturn
    //   67: aload_0
    //   68: invokestatic 105	com/apptentive/android/sdk/e/f:a	(Ljava/io/Closeable;)V
    //   71: goto -10 -> 61
    //   74: astore_1
    //   75: aload_3
    //   76: astore_0
    //   77: aload_0
    //   78: invokestatic 105	com/apptentive/android/sdk/e/f:a	(Ljava/io/Closeable;)V
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: goto -7 -> 77
    //   87: astore_0
    //   88: aconst_null
    //   89: astore_0
    //   90: goto -33 -> 57
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramInputStream	java.io.InputStream
    //   0	93	1	paramString	String
    //   38	11	2	i	int
    //   1	75	3	localObject	Object
    //   9	53	4	localStringBuilder	StringBuilder
    //   16	30	5	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   28	39	56	java/lang/Exception
    //   43	53	56	java/lang/Exception
    //   18	28	74	finally
    //   28	39	83	finally
    //   43	53	83	finally
    //   18	28	87	java/lang/Exception
  }
  
  public static void a(Activity paramActivity, View paramView)
  {
    if (paramView != null) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }
  
  public static void a(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  public static boolean a(CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (paramCharSequence.length() == 0);
  }
  
  public static String[] a(Context paramContext)
  {
    localArrayList = new ArrayList();
    if (b(paramContext, "android.permission.GET_ACCOUNTS"))
    {
      paramContext = AccountManager.get(paramContext);
      try
      {
        paramContext = paramContext.getAccountsByType("com.google");
        int j = paramContext.length;
        int i = 0;
        while (i < j)
        {
          localArrayList.add(paramContext[i].name);
          i += 1;
        }
        return (String[])localArrayList.toArray(new String[localArrayList.size()]);
      }
      catch (VerifyError paramContext) {}
    }
  }
  
  public static Integer b()
  {
    try
    {
      String[] arrayOfString = Build.VERSION.RELEASE.split("\\.");
      if ((arrayOfString != null) && (arrayOfString.length != 0))
      {
        int i = Integer.parseInt(arrayOfString[0]);
        return Integer.valueOf(i);
      }
    }
    catch (Exception localException)
    {
      m.c("Error getting major OS version", localException, new Object[0]);
    }
    return Integer.valueOf(-1);
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return (paramContext != null) && (paramContext.getActiveNetworkInfo() != null);
  }
  
  private static boolean b(Context paramContext, String paramString)
  {
    String str = paramContext.getApplicationContext().getPackageName();
    paramContext = paramContext.getPackageManager().getInstalledPackages(4096).iterator();
    while (paramContext.hasNext())
    {
      Object localObject = (PackageInfo)paramContext.next();
      if ((((PackageInfo)localObject).packageName.equals(str)) && (((PackageInfo)localObject).requestedPermissions != null))
      {
        localObject = ((PackageInfo)localObject).requestedPermissions;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          if (localObject[i].equals(paramString)) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
  
  public static Point c(Context paramContext)
  {
    Point localPoint = new Point();
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    localPoint.set(paramContext.getWidth(), paramContext.getHeight());
    return localPoint;
  }
  
  private static Object c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.get(paramString);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String d(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      m.d("Error getting app version name.", paramContext, new Object[0]);
    }
    return null;
  }
  
  public static int e(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      m.d("Error getting app version code.", paramContext, new Object[0]);
    }
    return -1;
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName());
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
}
