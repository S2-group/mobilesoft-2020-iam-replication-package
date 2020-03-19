package com.perfectcorp.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class n
{
  public static StringBuilder a(Context paramContext)
  {
    StringBuilder localStringBuilder = null;
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128);
      localStringBuilder = new StringBuilder("[\"");
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        if (paramContext.hasNext()) {
          localStringBuilder.append(((ApplicationInfo)paramContext.next()).packageName);
        }
        while (paramContext.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
          localStringBuilder.append("\", \"");
          localStringBuilder.append(localApplicationInfo.packageName);
        }
      }
      localStringBuilder.append("\"]");
      return localStringBuilder;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = localStringBuilder;
      }
    }
  }
  
  public static boolean a()
  {
    return (d()) || (e()) || (f());
  }
  
  public static String b()
  {
    return j.a().getString("OsUpgradeHistory", "");
  }
  
  public static File[] b(Context paramContext)
  {
    Object localObject1 = null;
    Object localObject2 = paramContext.getPackageManager();
    try
    {
      paramContext = ((PackageManager)localObject2).getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.dataDir;
      localObject2 = new File(paramContext + "/shared_prefs");
      paramContext = localObject1;
      if (((File)localObject2).isDirectory()) {
        paramContext = ((File)localObject2).listFiles(h.c(".xml"));
      }
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      f.f(new Object[] { paramContext });
    }
    return null;
  }
  
  public static boolean c()
  {
    boolean bool = true;
    if (!Build.MANUFACTURER.equalsIgnoreCase("motorola")) {}
    Matcher localMatcher;
    do
    {
      return false;
      localMatcher = Pattern.compile("XT(\\d{4})").matcher(Build.MODEL);
    } while (!localMatcher.find());
    int i = Integer.parseInt(localMatcher.group(1));
    if ((i > 1060) && (i < 1070)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  private static boolean d()
  {
    String str = Build.TAGS;
    return (str != null) && (str.contains("test-keys"));
  }
  
  private static boolean e()
  {
    String[] arrayOfString = new String[9];
    arrayOfString[0] = "/system/app/Superuser.apk";
    arrayOfString[1] = "/sbin/su";
    arrayOfString[2] = "/system/bin/su";
    arrayOfString[3] = "/system/xbin/su";
    arrayOfString[4] = "/data/local/xbin/su";
    arrayOfString[5] = "/data/local/bin/su";
    arrayOfString[6] = "/system/sd/xbin/su";
    arrayOfString[7] = "/system/bin/failsafe/su";
    arrayOfString[8] = "/data/local/su";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (new File(arrayOfString[i]).exists()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  /* Error */
  private static boolean f()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_0
    //   2: aconst_null
    //   3: astore_2
    //   4: invokestatic 216	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   7: iconst_2
    //   8: anewarray 144	java/lang/String
    //   11: dup
    //   12: iconst_0
    //   13: ldc -38
    //   15: aastore
    //   16: dup
    //   17: iconst_1
    //   18: ldc -36
    //   20: aastore
    //   21: invokevirtual 224	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   24: astore_1
    //   25: aload_1
    //   26: astore_2
    //   27: new 226	java/io/BufferedReader
    //   30: dup
    //   31: new 228	java/io/InputStreamReader
    //   34: dup
    //   35: aload_1
    //   36: invokevirtual 234	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   39: invokespecial 237	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   42: invokespecial 240	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   45: invokevirtual 243	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   48: astore_3
    //   49: aload_3
    //   50: ifnull +13 -> 63
    //   53: aload_1
    //   54: ifnull +7 -> 61
    //   57: aload_1
    //   58: invokevirtual 246	java/lang/Process:destroy	()V
    //   61: iload_0
    //   62: ireturn
    //   63: iconst_0
    //   64: istore_0
    //   65: goto -12 -> 53
    //   68: astore_1
    //   69: aconst_null
    //   70: astore_1
    //   71: aload_1
    //   72: ifnull +7 -> 79
    //   75: aload_1
    //   76: invokevirtual 246	java/lang/Process:destroy	()V
    //   79: iconst_0
    //   80: ireturn
    //   81: astore_1
    //   82: aload_2
    //   83: ifnull +7 -> 90
    //   86: aload_2
    //   87: invokevirtual 246	java/lang/Process:destroy	()V
    //   90: aload_1
    //   91: athrow
    //   92: astore_2
    //   93: goto -22 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	64	0	bool	boolean
    //   24	34	1	localProcess	Process
    //   68	1	1	localThrowable1	Throwable
    //   70	6	1	localObject1	Object
    //   81	10	1	localObject2	Object
    //   3	84	2	localObject3	Object
    //   92	1	2	localThrowable2	Throwable
    //   48	2	3	str	String
    // Exception table:
    //   from	to	target	type
    //   4	25	68	java/lang/Throwable
    //   4	25	81	finally
    //   27	49	81	finally
    //   27	49	92	java/lang/Throwable
  }
}
