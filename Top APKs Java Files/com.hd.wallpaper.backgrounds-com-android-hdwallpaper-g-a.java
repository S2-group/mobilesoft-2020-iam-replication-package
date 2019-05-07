package com.android.hdwallpaper.g;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class a
{
  private static String a;
  private static AsyncTask<Context, Void, String> b = new AsyncTask()
  {
    protected String a(Context... paramAnonymousVarArgs)
    {
      String str = "UNABLE-TO-RETRIEVE";
      try
      {
        paramAnonymousVarArgs = AdvertisingIdClient.getAdvertisingIdInfo(paramAnonymousVarArgs[0]);
        if (paramAnonymousVarArgs != null) {
          str = paramAnonymousVarArgs.getId();
        }
        return str;
      }
      catch (Throwable paramAnonymousVarArgs)
      {
        for (;;)
        {
          paramAnonymousVarArgs.printStackTrace();
          paramAnonymousVarArgs = null;
        }
      }
    }
  };
  
  public static void a()
  {
    l.b("Test", Log.getStackTraceString(new RuntimeException("kill process: " + Process.myPid())));
    a(Process.myPid());
  }
  
  public static void a(int paramInt)
  {
    l.b("Test", Log.getStackTraceString(new RuntimeException("kill process: " + paramInt)));
    Process.killProcess(paramInt);
  }
  
  public static boolean a(Context paramContext)
  {
    return a(paramContext, "com.android.vending");
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    try
    {
      paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 0);
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        bool1 = bool2;
        if (paramContext.size() > 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 1024);
      return true;
    }
    catch (Exception paramContext)
    {
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = b(paramContext, paramString1);
    boolean bool1 = bool2;
    if (!bool2) {
      bool1 = c(paramContext, paramString2);
    }
    return bool1;
  }
  
  public static long b()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static String b(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 65536);
    if ((paramContext == null) || (paramContext.activityInfo == null)) {}
    while (paramContext.activityInfo.packageName.equals("android")) {
      return null;
    }
    return paramContext.activityInfo.packageName;
  }
  
  public static ArrayList<PackageInfo> b(Context paramContext, String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if ((localPackageInfo.packageName.contains(paramString1)) || (localPackageInfo.packageName.contains(paramString2))) {
        localArrayList.add(localPackageInfo);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  /* Error */
  public static boolean b(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: astore_3
    //   2: aload_1
    //   3: invokestatic 192	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifne +74 -> 80
    //   9: aload_0
    //   10: ldc -62
    //   12: iconst_0
    //   13: invokevirtual 198	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   16: astore 4
    //   18: aload_1
    //   19: astore_3
    //   20: aload 4
    //   22: ldc -56
    //   24: iconst_0
    //   25: invokeinterface 206 3 0
    //   30: ifeq +50 -> 80
    //   33: aload 4
    //   35: ldc -48
    //   37: aconst_null
    //   38: invokeinterface 212 3 0
    //   43: astore 4
    //   45: aload 4
    //   47: invokestatic 192	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   50: istore_2
    //   51: aload_1
    //   52: astore_3
    //   53: iload_2
    //   54: ifne +26 -> 80
    //   57: aload_0
    //   58: invokestatic 214	com/android/hdwallpaper/g/a:a	(Landroid/content/Context;)Z
    //   61: ifne +81 -> 142
    //   64: aload_0
    //   65: aload 4
    //   67: invokestatic 106	com/android/hdwallpaper/g/a:c	(Landroid/content/Context;Ljava/lang/String;)Z
    //   70: istore_2
    //   71: iload_2
    //   72: ireturn
    //   73: astore_3
    //   74: aload_3
    //   75: invokevirtual 94	java/lang/Exception:printStackTrace	()V
    //   78: aload_1
    //   79: astore_3
    //   80: new 130	android/content/Intent
    //   83: dup
    //   84: ldc -40
    //   86: aload_3
    //   87: invokestatic 222	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   90: invokespecial 225	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   93: astore_1
    //   94: aload_1
    //   95: ldc 68
    //   97: invokevirtual 228	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   100: pop
    //   101: aload_0
    //   102: instanceof 230
    //   105: ifeq +17 -> 122
    //   108: aload_1
    //   109: ldc -25
    //   111: invokevirtual 235	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   114: pop
    //   115: aload_0
    //   116: aload_1
    //   117: invokevirtual 239	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   120: iconst_1
    //   121: ireturn
    //   122: aload_1
    //   123: ldc -16
    //   125: invokevirtual 235	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   128: pop
    //   129: goto -14 -> 115
    //   132: astore_0
    //   133: iconst_0
    //   134: ireturn
    //   135: astore_3
    //   136: aload 4
    //   138: astore_1
    //   139: goto -65 -> 74
    //   142: aload 4
    //   144: astore_3
    //   145: goto -65 -> 80
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	148	0	paramContext	Context
    //   0	148	1	paramString	String
    //   50	22	2	bool	boolean
    //   1	52	3	str1	String
    //   73	2	3	localException1	Exception
    //   79	8	3	str2	String
    //   135	1	3	localException2	Exception
    //   144	1	3	localObject1	Object
    //   16	127	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   9	18	73	java/lang/Exception
    //   20	51	73	java/lang/Exception
    //   115	120	132	java/lang/Exception
    //   57	71	135	java/lang/Exception
  }
  
  public static long c()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
  }
  
  public static String c(Context paramContext)
  {
    paramContext = e(paramContext, paramContext.getPackageName());
    char[] arrayOfChar = paramContext.toCharArray();
    int i = 0;
    while (i < arrayOfChar.length)
    {
      if (Character.isLetter(arrayOfChar[i])) {
        return paramContext.substring(0, i);
      }
      i += 1;
    }
    return paramContext;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    do
    {
      return false;
      paramString = Uri.parse(paramString);
    } while (paramString == null);
    paramString = new Intent("android.intent.action.VIEW", paramString);
    paramString.setFlags(268435456);
    try
    {
      paramContext.startActivity(paramString);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static int d(Context paramContext, String paramString)
  {
    int i = 0;
    if (paramString != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      i = paramContext.getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      l.b("AppUtils", "getVersionCodeByPkgName=" + paramString + " has " + paramContext.getMessage());
    }
    return 0;
  }
  
  public static String d()
  {
    if (a == null) {
      return "UNABLE-TO-RETRIEVE";
    }
    return a;
  }
  
  /* Error */
  public static String d(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 38	android/os/Process:myPid	()I
    //   3: istore_1
    //   4: aload_0
    //   5: ldc_w 289
    //   8: invokevirtual 293	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   11: checkcast 295	android/app/ActivityManager
    //   14: invokevirtual 299	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   17: astore_0
    //   18: aload_0
    //   19: ifnull +44 -> 63
    //   22: aload_0
    //   23: invokeinterface 303 1 0
    //   28: astore_0
    //   29: aload_0
    //   30: invokeinterface 309 1 0
    //   35: ifeq +28 -> 63
    //   38: aload_0
    //   39: invokeinterface 313 1 0
    //   44: checkcast 315	android/app/ActivityManager$RunningAppProcessInfo
    //   47: astore_2
    //   48: aload_2
    //   49: getfield 318	android/app/ActivityManager$RunningAppProcessInfo:pid	I
    //   52: iload_1
    //   53: if_icmpne -24 -> 29
    //   56: aload_2
    //   57: getfield 321	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   60: astore_2
    //   61: aload_2
    //   62: areturn
    //   63: invokestatic 327	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   66: new 25	java/lang/StringBuilder
    //   69: dup
    //   70: invokespecial 26	java/lang/StringBuilder:<init>	()V
    //   73: ldc_w 329
    //   76: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: iload_1
    //   80: invokevirtual 41	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   83: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokevirtual 333	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   89: astore_0
    //   90: new 335	java/io/BufferedReader
    //   93: dup
    //   94: new 337	java/io/InputStreamReader
    //   97: dup
    //   98: aload_0
    //   99: invokevirtual 343	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   102: invokespecial 346	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   105: invokespecial 349	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   108: astore_2
    //   109: aload_2
    //   110: astore 4
    //   112: aload_0
    //   113: astore_3
    //   114: aload_2
    //   115: invokevirtual 352	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   118: astore 5
    //   120: aload 5
    //   122: ifnull +144 -> 266
    //   125: aload_2
    //   126: astore 4
    //   128: aload_0
    //   129: astore_3
    //   130: ldc 21
    //   132: new 25	java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial 26	java/lang/StringBuilder:<init>	()V
    //   139: ldc_w 354
    //   142: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: aload 5
    //   147: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 59	com/android/hdwallpaper/g/l:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   156: aload_2
    //   157: astore 4
    //   159: aload_0
    //   160: astore_3
    //   161: aload_2
    //   162: invokevirtual 352	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   165: astore 5
    //   167: aload 5
    //   169: ifnull +97 -> 266
    //   172: aload_2
    //   173: astore 4
    //   175: aload_0
    //   176: astore_3
    //   177: ldc 21
    //   179: new 25	java/lang/StringBuilder
    //   182: dup
    //   183: invokespecial 26	java/lang/StringBuilder:<init>	()V
    //   186: ldc_w 354
    //   189: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: aload 5
    //   194: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: invokestatic 59	com/android/hdwallpaper/g/l:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   203: aload_2
    //   204: astore 4
    //   206: aload_0
    //   207: astore_3
    //   208: aload 5
    //   210: ldc_w 356
    //   213: ldc_w 357
    //   216: invokevirtual 361	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   219: astore 5
    //   221: aload_2
    //   222: astore 4
    //   224: aload_0
    //   225: astore_3
    //   226: aload 5
    //   228: aload 5
    //   230: arraylength
    //   231: iconst_1
    //   232: isub
    //   233: aaload
    //   234: astore 5
    //   236: aload_2
    //   237: ifnull +7 -> 244
    //   240: aload_2
    //   241: invokevirtual 364	java/io/BufferedReader:close	()V
    //   244: aload 5
    //   246: astore_2
    //   247: aload_0
    //   248: ifnull -187 -> 61
    //   251: aload_0
    //   252: invokevirtual 367	java/lang/Process:destroy	()V
    //   255: aload 5
    //   257: areturn
    //   258: astore_2
    //   259: aload_2
    //   260: invokevirtual 368	java/io/IOException:printStackTrace	()V
    //   263: goto -19 -> 244
    //   266: aload_2
    //   267: ifnull +7 -> 274
    //   270: aload_2
    //   271: invokevirtual 364	java/io/BufferedReader:close	()V
    //   274: aload_0
    //   275: ifnull +7 -> 282
    //   278: aload_0
    //   279: invokevirtual 367	java/lang/Process:destroy	()V
    //   282: aconst_null
    //   283: areturn
    //   284: astore_2
    //   285: aload_2
    //   286: invokevirtual 368	java/io/IOException:printStackTrace	()V
    //   289: goto -15 -> 274
    //   292: astore 5
    //   294: aconst_null
    //   295: astore_2
    //   296: aconst_null
    //   297: astore_0
    //   298: aload_2
    //   299: astore 4
    //   301: aload_0
    //   302: astore_3
    //   303: aload 5
    //   305: invokevirtual 369	java/lang/Throwable:printStackTrace	()V
    //   308: aload_2
    //   309: ifnull +7 -> 316
    //   312: aload_2
    //   313: invokevirtual 364	java/io/BufferedReader:close	()V
    //   316: aload_0
    //   317: ifnull -35 -> 282
    //   320: aload_0
    //   321: invokevirtual 367	java/lang/Process:destroy	()V
    //   324: goto -42 -> 282
    //   327: astore_2
    //   328: aload_2
    //   329: invokevirtual 368	java/io/IOException:printStackTrace	()V
    //   332: goto -16 -> 316
    //   335: astore_2
    //   336: aconst_null
    //   337: astore 4
    //   339: aconst_null
    //   340: astore_0
    //   341: aload 4
    //   343: ifnull +8 -> 351
    //   346: aload 4
    //   348: invokevirtual 364	java/io/BufferedReader:close	()V
    //   351: aload_0
    //   352: ifnull +7 -> 359
    //   355: aload_0
    //   356: invokevirtual 367	java/lang/Process:destroy	()V
    //   359: aload_2
    //   360: athrow
    //   361: astore_3
    //   362: aload_3
    //   363: invokevirtual 368	java/io/IOException:printStackTrace	()V
    //   366: goto -15 -> 351
    //   369: astore_2
    //   370: aconst_null
    //   371: astore 4
    //   373: goto -32 -> 341
    //   376: astore_2
    //   377: aload_3
    //   378: astore_0
    //   379: goto -38 -> 341
    //   382: astore 5
    //   384: aconst_null
    //   385: astore_2
    //   386: goto -88 -> 298
    //   389: astore 5
    //   391: goto -93 -> 298
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	394	0	paramContext	Context
    //   3	77	1	i	int
    //   47	200	2	localObject1	Object
    //   258	13	2	localIOException1	java.io.IOException
    //   284	2	2	localIOException2	java.io.IOException
    //   295	18	2	localObject2	Object
    //   327	2	2	localIOException3	java.io.IOException
    //   335	25	2	localObject3	Object
    //   369	1	2	localObject4	Object
    //   376	1	2	localObject5	Object
    //   385	1	2	localObject6	Object
    //   113	190	3	localContext	Context
    //   361	17	3	localIOException4	java.io.IOException
    //   110	262	4	localObject7	Object
    //   118	138	5	localObject8	Object
    //   292	12	5	localThrowable1	Throwable
    //   382	1	5	localThrowable2	Throwable
    //   389	1	5	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   240	244	258	java/io/IOException
    //   270	274	284	java/io/IOException
    //   63	90	292	java/lang/Throwable
    //   312	316	327	java/io/IOException
    //   63	90	335	finally
    //   346	351	361	java/io/IOException
    //   90	109	369	finally
    //   114	120	376	finally
    //   130	156	376	finally
    //   161	167	376	finally
    //   177	203	376	finally
    //   208	221	376	finally
    //   226	236	376	finally
    //   303	308	376	finally
    //   90	109	382	java/lang/Throwable
    //   114	120	389	java/lang/Throwable
    //   130	156	389	java/lang/Throwable
    //   161	167	389	java/lang/Throwable
    //   177	203	389	java/lang/Throwable
    //   208	221	389	java/lang/Throwable
    //   226	236	389	java/lang/Throwable
  }
  
  public static String e(Context paramContext)
  {
    if (b.getStatus() == AsyncTask.Status.RUNNING) {
      return "UNABLE-TO-RETRIEVE";
    }
    if (a == null) {}
    try
    {
      a = (String)b.execute(new Context[] { paramContext }).get(8000L, TimeUnit.MILLISECONDS);
      if (a == null) {
        a = "UNABLE-TO-RETRIEVE";
      }
      return a;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String e(Context paramContext, String paramString)
  {
    String str = "0.0";
    if (paramString != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      str = paramContext.getPackageInfo(paramString, 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "0.0";
  }
  
  public static long f(Context paramContext, String paramString)
  {
    try
    {
      long l = new File(paramContext.getPackageManager().getApplicationInfo(paramString, 0).sourceDir).lastModified();
      return l;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0L;
  }
}
