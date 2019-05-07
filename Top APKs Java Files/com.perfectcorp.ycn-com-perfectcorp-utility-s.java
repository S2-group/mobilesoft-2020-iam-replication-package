package com.perfectcorp.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import com.cyberlink.beautycircle.Globals;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class s
{
  public static StringBuilder a(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new StringBuilder("[\"");
    localObject = ((List)localObject).iterator();
    if (((Iterator)localObject).hasNext()) {
      paramContext.append(((ApplicationInfo)((Iterator)localObject).next()).packageName);
    }
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      paramContext.append("\", \"");
      paramContext.append(localApplicationInfo.packageName);
    }
    paramContext.append("\"]");
    return paramContext;
  }
  
  public static boolean a()
  {
    return (f()) || (g()) || (h());
  }
  
  public static void b()
  {
    AsyncTask.execute(new t());
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
        paramContext = ((File)localObject2).listFiles(i.d(".xml"));
      }
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      e.e(new Object[] { paramContext });
    }
    return null;
  }
  
  public static void c()
  {
    String str1 = Build.VERSION.RELEASE;
    SharedPreferences localSharedPreferences = Globals.D();
    String str2 = localSharedPreferences.getString("OsUpgradeHistory", "");
    if (str2.isEmpty()) {
      localSharedPreferences.edit().putString("OsUpgradeHistory", str1).commit();
    }
    for (;;)
    {
      return;
      int i = str2.lastIndexOf(";");
      if (i <= 0) {
        i = 0;
      }
      while (!str2.substring(i, str2.length()).equalsIgnoreCase(str1))
      {
        str1 = str2 + ";" + str1;
        localSharedPreferences.edit().putString("OsUpgradeHistory", str1).commit();
        return;
        i += 1;
      }
    }
  }
  
  public static String d()
  {
    return Globals.D().getString("OsUpgradeHistory", "");
  }
  
  public static boolean e()
  {
    if (!Build.MANUFACTURER.equalsIgnoreCase("motorola")) {}
    int i;
    do
    {
      Matcher localMatcher;
      do
      {
        return false;
        localMatcher = Pattern.compile("XT(\\d{4})").matcher(Build.MODEL);
      } while (!localMatcher.find());
      i = Integer.parseInt(localMatcher.group(1));
    } while ((i <= 1060) || (i >= 1070));
    return true;
  }
  
  private static boolean f()
  {
    String str = Build.TAGS;
    return (str != null) && (str.contains("test-keys"));
  }
  
  private static boolean g()
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
  private static boolean h()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: invokestatic 260	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   5: iconst_2
    //   6: anewarray 152	java/lang/String
    //   9: dup
    //   10: iconst_0
    //   11: ldc_w 262
    //   14: aastore
    //   15: dup
    //   16: iconst_1
    //   17: ldc_w 264
    //   20: aastore
    //   21: invokevirtual 268	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   24: astore_0
    //   25: aload_0
    //   26: astore_1
    //   27: new 270	java/io/BufferedReader
    //   30: dup
    //   31: new 272	java/io/InputStreamReader
    //   34: dup
    //   35: aload_0
    //   36: invokevirtual 278	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   39: invokespecial 281	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   42: invokespecial 284	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   45: invokevirtual 287	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   48: astore_2
    //   49: aload_2
    //   50: ifnull +13 -> 63
    //   53: aload_0
    //   54: ifnull +7 -> 61
    //   57: aload_0
    //   58: invokevirtual 290	java/lang/Process:destroy	()V
    //   61: iconst_1
    //   62: ireturn
    //   63: aload_0
    //   64: ifnull +7 -> 71
    //   67: aload_0
    //   68: invokevirtual 290	java/lang/Process:destroy	()V
    //   71: iconst_0
    //   72: ireturn
    //   73: astore_0
    //   74: aconst_null
    //   75: astore_0
    //   76: aload_0
    //   77: ifnull +7 -> 84
    //   80: aload_0
    //   81: invokevirtual 290	java/lang/Process:destroy	()V
    //   84: iconst_0
    //   85: ireturn
    //   86: astore_0
    //   87: aload_1
    //   88: ifnull +7 -> 95
    //   91: aload_1
    //   92: invokevirtual 290	java/lang/Process:destroy	()V
    //   95: aload_0
    //   96: athrow
    //   97: astore_1
    //   98: goto -22 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   24	44	0	localProcess	Process
    //   73	1	0	localThrowable1	Throwable
    //   75	6	0	localObject1	Object
    //   86	10	0	localObject2	Object
    //   1	91	1	localObject3	Object
    //   97	1	1	localThrowable2	Throwable
    //   48	2	2	str	String
    // Exception table:
    //   from	to	target	type
    //   2	25	73	java/lang/Throwable
    //   2	25	86	finally
    //   27	49	86	finally
    //   27	49	97	java/lang/Throwable
  }
}
