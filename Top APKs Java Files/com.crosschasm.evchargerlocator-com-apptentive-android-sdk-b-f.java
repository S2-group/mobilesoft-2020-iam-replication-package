package com.apptentive.android.sdk.b;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.apptentive.android.sdk.Log;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class f
{
  public static double a()
  {
    return System.currentTimeMillis() / 1000.0D;
  }
  
  public static float a(Context paramContext, int paramInt)
  {
    return paramContext.getResources().getDisplayMetrics().density * paramInt;
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
      Log.d("Error parsing cache expiration as number: %s", localThrowable, new Object[] { localObject1 });
      i += 1;
    }
  }
  
  public static String a(long paramLong)
  {
    return a(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ"), new Date(paramLong));
  }
  
  /* Error */
  public static String a(java.io.InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 97	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   9: astore 4
    //   11: sipush 8196
    //   14: newarray char
    //   16: astore 5
    //   18: new 102	java/io/InputStreamReader
    //   21: dup
    //   22: aload_0
    //   23: aload_1
    //   24: invokespecial 105	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   27: astore_0
    //   28: aload_0
    //   29: aload 5
    //   31: iconst_0
    //   32: sipush 8196
    //   35: invokevirtual 111	java/io/Reader:read	([CII)I
    //   38: istore_2
    //   39: iload_2
    //   40: ifge +13 -> 53
    //   43: aload_0
    //   44: invokestatic 114	com/apptentive/android/sdk/b/f:a	(Ljava/io/Closeable;)V
    //   47: aload 4
    //   49: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: areturn
    //   53: aload 4
    //   55: aload 5
    //   57: iconst_0
    //   58: iload_2
    //   59: invokevirtual 121	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: goto -35 -> 28
    //   66: astore_1
    //   67: aload_0
    //   68: invokestatic 114	com/apptentive/android/sdk/b/f:a	(Ljava/io/Closeable;)V
    //   71: goto -24 -> 47
    //   74: astore_1
    //   75: aload_3
    //   76: astore_0
    //   77: aload_0
    //   78: invokestatic 114	com/apptentive/android/sdk/b/f:a	(Ljava/io/Closeable;)V
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: goto -7 -> 77
    //   87: astore_0
    //   88: aconst_null
    //   89: astore_0
    //   90: goto -23 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramInputStream	java.io.InputStream
    //   0	93	1	paramString	String
    //   38	21	2	i	int
    //   1	75	3	localObject	Object
    //   9	45	4	localStringBuilder	StringBuilder
    //   16	40	5	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   28	39	66	java/lang/Exception
    //   53	63	66	java/lang/Exception
    //   18	28	74	finally
    //   28	39	83	finally
    //   53	63	83	finally
    //   18	28	87	java/lang/Exception
  }
  
  public static String a(String paramString, Double paramDouble)
  {
    return a(new SimpleDateFormat(paramString), new Date(Math.round(paramDouble.doubleValue() * 1000.0D))).replace("PM", "pm").replace("AM", "am");
  }
  
  public static String a(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
  
  public static String a(DateFormat paramDateFormat, Date paramDate)
  {
    return paramDateFormat.format(paramDate);
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
  
  public static boolean a(Context paramContext, String paramString)
  {
    return a(paramContext, paramContext.getApplicationContext().getPackageName(), paramString);
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = g(paramContext).iterator();
    while (paramContext.hasNext())
    {
      Object localObject = (PackageInfo)paramContext.next();
      if ((((PackageInfo)localObject).packageName.equals(paramString1)) && (((PackageInfo)localObject).requestedPermissions != null))
      {
        localObject = ((PackageInfo)localObject).requestedPermissions;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          if (localObject[i].equals(paramString2)) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
  
  public static boolean a(CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (paramCharSequence.length() == 0);
  }
  
  public static String[] a(Context paramContext)
  {
    localArrayList = new ArrayList();
    if (a(paramContext, "android.permission.GET_ACCOUNTS"))
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
  
  public static int b()
  {
    return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000;
  }
  
  public static Object b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.get(paramString);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return (paramContext != null) && (paramContext.getActiveNetworkInfo() != null);
  }
  
  public static boolean b(String paramString)
  {
    return paramString.matches("^[^\\s@]+@[^\\s@]+$");
  }
  
  public static Point c(Context paramContext)
  {
    Point localPoint = new Point();
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    localPoint.set(paramContext.getWidth(), paramContext.getHeight());
    return localPoint;
  }
  
  public static Integer c()
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
      Log.c("Error getting major OS version", localException, new Object[0]);
    }
    return Integer.valueOf(-1);
  }
  
  public static String c(Context paramContext, String paramString)
  {
    paramContext = b(paramContext, paramString);
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
  
  public static String d(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.d("Error getting app version name.", paramContext, new Object[0]);
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
      Log.d("Error getting app version code.", paramContext, new Object[0]);
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
  
  private static List<PackageInfo> g(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(4096);
  }
}
