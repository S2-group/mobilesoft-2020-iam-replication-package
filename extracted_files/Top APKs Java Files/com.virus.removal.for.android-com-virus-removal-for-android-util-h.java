package com.virus.removal.for.android.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v7.app.c.a;
import android.text.TextUtils.SimpleStringSplitter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.FacebookSdk;
import com.virus.removal.for.android.activities.DoneBoostActivity;
import com.virus.removal.for.android.e.e;
import com.virus.removal.for.android.e.f;
import com.virus.removal.for.android.e.j;
import com.virus.removal.for.android.service.MyAccessibilityService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class h
{
  public static String a = "app_advide";
  public static int b;
  public static List<j> c = new ArrayList();
  
  public static Intent AppDetailsIntent(String paramString)
  {
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setFlags(276824064);
    localIntent.setData(Uri.parse("package:" + paramString));
    return localIntent;
  }
  
  public static boolean checkIfAppWasInstalledThroughGooglePlay(Context paramContext, String paramString)
  {
    boolean bool1 = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      boolean bool2 = "com.android.vending".equals(paramContext.getInstallerPackageName(paramContext.getApplicationInfo(paramString, 0).packageName));
      if (bool2) {
        bool1 = true;
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean checkIfUSBDebugIsEnabled(Context paramContext)
  {
    return Settings.Secure.getInt(paramContext.getContentResolver(), "adb_enabled", 0) == 1;
  }
  
  public static boolean checkIfUnknownAppIsEnabled(Context paramContext)
  {
    return Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps", 0) == 1;
  }
  
  /* Error */
  public static void clearCache(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 26	com/virus/removal/for/android/util/h:c	Ljava/util/List;
    //   3: invokeinterface 132 1 0
    //   8: new 134	com/virus/removal/for/android/util/b
    //   11: dup
    //   12: invokespecial 135	com/virus/removal/for/android/util/b:<init>	()V
    //   15: astore_1
    //   16: aload_0
    //   17: invokevirtual 74	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   20: astore_0
    //   21: getstatic 141	java/lang/Long:TYPE	Ljava/lang/Class;
    //   24: astore_2
    //   25: aload_0
    //   26: invokevirtual 145	java/lang/Object:getClass	()Ljava/lang/Class;
    //   29: ldc -109
    //   31: iconst_2
    //   32: anewarray 149	java/lang/Class
    //   35: dup
    //   36: iconst_0
    //   37: aload_2
    //   38: aastore
    //   39: dup
    //   40: iconst_1
    //   41: ldc -105
    //   43: aastore
    //   44: invokevirtual 155	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   47: astore_2
    //   48: aload_2
    //   49: aload_0
    //   50: iconst_2
    //   51: anewarray 4	java/lang/Object
    //   54: dup
    //   55: iconst_0
    //   56: ldc2_w 156
    //   59: invokestatic 161	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   62: aastore
    //   63: dup
    //   64: iconst_1
    //   65: aload_1
    //   66: aastore
    //   67: invokevirtual 167	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   70: pop
    //   71: return
    //   72: astore_0
    //   73: aload_0
    //   74: invokevirtual 170	java/lang/Exception:printStackTrace	()V
    //   77: return
    //   78: astore_0
    //   79: aload_0
    //   80: invokevirtual 171	java/lang/NoSuchMethodException:printStackTrace	()V
    //   83: return
    //   84: astore_0
    //   85: goto -12 -> 73
    //   88: astore_0
    //   89: goto -16 -> 73
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	paramContext	Context
    //   15	51	1	localB	b
    //   24	25	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   48	71	72	java/lang/IllegalAccessException
    //   25	48	78	java/lang/NoSuchMethodException
    //   48	71	78	java/lang/NoSuchMethodException
    //   73	77	78	java/lang/NoSuchMethodException
    //   48	71	84	java/lang/IllegalArgumentException
    //   48	71	88	java/lang/reflect/InvocationTargetException
  }
  
  public static float convertDpToPixel(float paramFloat, Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi / 160.0F * paramFloat;
  }
  
  public static String convertFileSizeToString(long paramLong)
  {
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "b";
    arrayOfString[1] = "Kb";
    arrayOfString[2] = "Mb";
    arrayOfString[3] = "Gb";
    arrayOfString[4] = "Tb";
    arrayOfString[5] = "Pb";
    if (paramLong <= 0L) {
      return "0Kb";
    }
    int i = (int)(Math.log10(paramLong) / Math.log10(1024.0D));
    if (i > arrayOfString.length - 1) {
      return "Too big";
    }
    return new DecimalFormat("#,##0.#").format(paramLong / Math.pow(1024.0D, i)) + arrayOfString[i];
  }
  
  public static <T> T deserializeFromDataFolder(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = getInternalDataPath(paramContext) + File.separatorChar + paramString;
      paramContext = localObject;
      if (existsFile(paramString)) {
        paramContext = deserializeFromFile(paramString);
      }
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  public static <T> T deserializeFromFile(String paramString)
  {
    // Byte code:
    //   0: new 259	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 260	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   8: astore_1
    //   9: new 262	java/io/ObjectInputStream
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 265	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_2
    //   18: aload_2
    //   19: invokevirtual 269	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   22: astore_0
    //   23: aload_2
    //   24: invokevirtual 272	java/io/ObjectInputStream:close	()V
    //   27: aload_1
    //   28: invokevirtual 273	java/io/FileInputStream:close	()V
    //   31: aload_0
    //   32: areturn
    //   33: astore_1
    //   34: aconst_null
    //   35: astore_0
    //   36: aload_1
    //   37: invokevirtual 274	java/lang/ClassNotFoundException:printStackTrace	()V
    //   40: aload_0
    //   41: areturn
    //   42: astore_1
    //   43: goto -7 -> 36
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	46	0	paramString	String
    //   8	20	1	localFileInputStream	java.io.FileInputStream
    //   33	4	1	localClassNotFoundException1	ClassNotFoundException
    //   42	1	1	localClassNotFoundException2	ClassNotFoundException
    //   17	7	2	localObjectInputStream	java.io.ObjectInputStream
    // Exception table:
    //   from	to	target	type
    //   0	23	33	java/lang/ClassNotFoundException
    //   23	31	42	java/lang/ClassNotFoundException
  }
  
  public static boolean existsFile(String paramString)
  {
    paramString = new File(paramString);
    return (paramString.exists()) && (!paramString.isDirectory());
  }
  
  public static boolean existsFolder(String paramString)
  {
    paramString = new File(paramString);
    return (paramString.exists()) && (paramString.isDirectory());
  }
  
  public static String formatSize(long paramLong)
  {
    double d = Math.round(paramLong / 1.073741824E9D * 100.0D) / 100.0D;
    return d + "GB";
  }
  
  public static List<com.virus.removal.for.android.e.c> getAppLock(Context paramContext)
  {
    f localF = new f(paramContext);
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = paramContext.getPackageManager();
    Object localObject2 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
    localObject1 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 0).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ResolveInfo)((Iterator)localObject1).next();
      if (!localF.isAppLocked(((ResolveInfo)localObject2).activityInfo.packageName))
      {
        localObject2 = new com.virus.removal.for.android.e.c(getAppNameFromPackage(paramContext, ((ResolveInfo)localObject2).activityInfo.packageName), ((ResolveInfo)localObject2).activityInfo.packageName, false);
        if (isRecommendAppLock(((com.virus.removal.for.android.e.c)localObject2).getPackageName())) {
          ((com.virus.removal.for.android.e.c)localObject2).setRecommend(true);
        }
        localArrayList.add(localObject2);
      }
    }
    return localArrayList;
  }
  
  public static String getAppNameFromPackage(Context paramContext, String paramString)
  {
    paramContext = paramContext.getApplicationContext().getPackageManager();
    try
    {
      paramContext = (String)paramContext.getApplicationLabel(paramContext.getApplicationInfo(paramString, 128));
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "Unkown app";
  }
  
  public static List<PackageInfo> getApps(Context paramContext, int paramInt)
  {
    try
    {
      List localList = paramContext.getPackageManager().getInstalledPackages(paramInt);
      return localList;
    }
    catch (Exception localException) {}
    return getInstalledPackages(paramContext, paramInt);
  }
  
  public static long getAvailableInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  /* Error */
  public static void getCacheSize(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 26	com/virus/removal/for/android/util/h:c	Ljava/util/List;
    //   3: invokeinterface 132 1 0
    //   8: aload_0
    //   9: invokevirtual 74	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   12: iconst_0
    //   13: invokevirtual 381	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   16: invokeinterface 322 1 0
    //   21: astore_1
    //   22: aload_1
    //   23: invokeinterface 327 1 0
    //   28: ifeq +101 -> 129
    //   31: aload_1
    //   32: invokeinterface 330 1 0
    //   37: checkcast 408	android/content/pm/PackageInfo
    //   40: astore_2
    //   41: aload_0
    //   42: invokevirtual 74	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   45: invokevirtual 145	java/lang/Object:getClass	()Ljava/lang/Class;
    //   48: ldc_w 410
    //   51: iconst_2
    //   52: anewarray 149	java/lang/Class
    //   55: dup
    //   56: iconst_0
    //   57: ldc 93
    //   59: aastore
    //   60: dup
    //   61: iconst_1
    //   62: ldc_w 412
    //   65: aastore
    //   66: invokevirtual 155	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   69: astore_3
    //   70: aload_3
    //   71: aload_0
    //   72: invokevirtual 74	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   75: iconst_2
    //   76: anewarray 4	java/lang/Object
    //   79: dup
    //   80: iconst_0
    //   81: aload_2
    //   82: getfield 413	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   85: aastore
    //   86: dup
    //   87: iconst_1
    //   88: new 6	com/virus/removal/for/android/util/h$1
    //   91: dup
    //   92: aload_0
    //   93: aload_2
    //   94: invokespecial 416	com/virus/removal/for/android/util/h$1:<init>	(Landroid/content/Context;Landroid/content/pm/PackageInfo;)V
    //   97: aastore
    //   98: invokevirtual 167	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   101: pop
    //   102: goto -80 -> 22
    //   105: astore_2
    //   106: aload_2
    //   107: invokevirtual 417	java/lang/IllegalAccessException:printStackTrace	()V
    //   110: goto -88 -> 22
    //   113: astore_2
    //   114: aload_2
    //   115: invokevirtual 171	java/lang/NoSuchMethodException:printStackTrace	()V
    //   118: goto -96 -> 22
    //   121: astore_2
    //   122: aload_2
    //   123: invokevirtual 418	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   126: goto -104 -> 22
    //   129: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	paramContext	Context
    //   21	11	1	localIterator	Iterator
    //   40	54	2	localPackageInfo	PackageInfo
    //   105	2	2	localIllegalAccessException	IllegalAccessException
    //   113	2	2	localNoSuchMethodException	NoSuchMethodException
    //   121	2	2	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   69	2	3	localMethod	java.lang.reflect.Method
    // Exception table:
    //   from	to	target	type
    //   70	102	105	java/lang/IllegalAccessException
    //   41	70	113	java/lang/NoSuchMethodException
    //   70	102	113	java/lang/NoSuchMethodException
    //   106	110	113	java/lang/NoSuchMethodException
    //   122	126	113	java/lang/NoSuchMethodException
    //   70	102	121	java/lang/reflect/InvocationTargetException
  }
  
  public static long getCurrentTime()
  {
    return System.currentTimeMillis();
  }
  
  public static String getDateTime()
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
  }
  
  public static long getFreeRAM(Context paramContext)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem;
  }
  
  public static Intent getHomeIntent()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.addFlags(268435456);
    return localIntent;
  }
  
  public static Drawable getIconFromPackage(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramString = paramContext.getApplicationIcon(paramString);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  /* Error */
  public static List<PackageInfo> getInstalledPackages(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 74	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: aload_3
    //   6: iload_1
    //   7: invokevirtual 381	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: astore_0
    //   14: new 21	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 24	java/util/ArrayList:<init>	()V
    //   21: astore 4
    //   23: aconst_null
    //   24: astore_0
    //   25: invokestatic 484	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   28: ldc_w 486
    //   31: invokevirtual 490	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   34: astore 5
    //   36: new 492	java/io/BufferedReader
    //   39: dup
    //   40: new 494	java/io/InputStreamReader
    //   43: dup
    //   44: aload 5
    //   46: invokevirtual 500	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   49: invokespecial 501	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   52: invokespecial 504	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   55: astore_2
    //   56: aload_2
    //   57: astore_0
    //   58: aload_2
    //   59: invokevirtual 507	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   62: astore 6
    //   64: aload 6
    //   66: ifnull +64 -> 130
    //   69: aload_2
    //   70: astore_0
    //   71: aload 4
    //   73: aload_3
    //   74: aload 6
    //   76: aload 6
    //   78: bipush 58
    //   80: invokevirtual 511	java/lang/String:indexOf	(I)I
    //   83: iconst_1
    //   84: iadd
    //   85: invokevirtual 515	java/lang/String:substring	(I)Ljava/lang/String;
    //   88: iload_1
    //   89: invokevirtual 519	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   92: invokeinterface 364 2 0
    //   97: pop
    //   98: goto -42 -> 56
    //   101: astore_3
    //   102: aload_2
    //   103: astore_0
    //   104: aload_3
    //   105: invokevirtual 170	java/lang/Exception:printStackTrace	()V
    //   108: aload 4
    //   110: astore_0
    //   111: aload_2
    //   112: ifnull -101 -> 11
    //   115: aload_2
    //   116: invokevirtual 520	java/io/BufferedReader:close	()V
    //   119: aload 4
    //   121: areturn
    //   122: astore_0
    //   123: aload_0
    //   124: invokevirtual 253	java/io/IOException:printStackTrace	()V
    //   127: aload 4
    //   129: areturn
    //   130: aload_2
    //   131: astore_0
    //   132: aload 5
    //   134: invokevirtual 523	java/lang/Process:waitFor	()I
    //   137: pop
    //   138: aload 4
    //   140: astore_0
    //   141: aload_2
    //   142: ifnull -131 -> 11
    //   145: aload_2
    //   146: invokevirtual 520	java/io/BufferedReader:close	()V
    //   149: aload 4
    //   151: areturn
    //   152: astore_0
    //   153: aload_0
    //   154: invokevirtual 253	java/io/IOException:printStackTrace	()V
    //   157: aload 4
    //   159: areturn
    //   160: astore_3
    //   161: aload_0
    //   162: astore_2
    //   163: aload_3
    //   164: astore_0
    //   165: aload_2
    //   166: ifnull +7 -> 173
    //   169: aload_2
    //   170: invokevirtual 520	java/io/BufferedReader:close	()V
    //   173: aload_0
    //   174: athrow
    //   175: astore_2
    //   176: aload_2
    //   177: invokevirtual 253	java/io/IOException:printStackTrace	()V
    //   180: goto -7 -> 173
    //   183: astore_3
    //   184: aload_0
    //   185: astore_2
    //   186: aload_3
    //   187: astore_0
    //   188: goto -23 -> 165
    //   191: astore_3
    //   192: aconst_null
    //   193: astore_2
    //   194: goto -92 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	197	0	paramContext	Context
    //   0	197	1	paramInt	int
    //   55	115	2	localObject1	Object
    //   175	2	2	localIOException	IOException
    //   185	9	2	localContext	Context
    //   4	70	3	localPackageManager	PackageManager
    //   101	4	3	localException1	Exception
    //   160	4	3	localObject2	Object
    //   183	4	3	localObject3	Object
    //   191	1	3	localException2	Exception
    //   21	137	4	localArrayList	ArrayList
    //   34	99	5	localProcess	Process
    //   62	15	6	str	String
    // Exception table:
    //   from	to	target	type
    //   5	11	13	java/lang/Exception
    //   58	64	101	java/lang/Exception
    //   71	98	101	java/lang/Exception
    //   132	138	101	java/lang/Exception
    //   115	119	122	java/io/IOException
    //   145	149	152	java/io/IOException
    //   25	56	160	finally
    //   169	173	175	java/io/IOException
    //   58	64	183	finally
    //   71	98	183	finally
    //   104	108	183	finally
    //   132	138	183	finally
    //   25	56	191	java/lang/Exception
  }
  
  public static String getInternalDataPath(Context paramContext)
  {
    String str2 = paramContext.getFilesDir().getPath();
    String str1 = str2;
    if (str2.length() == 0)
    {
      str1 = "/data/data/" + paramContext.getPackageName() + "/files";
      new File(str1).mkdirs();
    }
    return str1;
  }
  
  public static long getMemoryOfService(Context paramContext, int paramInt)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    paramContext.getMemoryInfo(new ActivityManager.MemoryInfo());
    return paramContext.getProcessMemoryInfo(new int[] { paramInt })[0].getTotalPss();
  }
  
  public static List<PackageInfo> getNonSystemApps(Context paramContext, List<PackageInfo> paramList)
  {
    paramContext = new ArrayList();
    int i = 0;
    while (i < paramList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramList.get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x81) == 0) {
        paramContext.add(localPackageInfo);
      }
      i += 1;
    }
    return paramContext;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext, String paramString, int paramInt)
  {
    return paramContext.getPackageManager().getPackageInfo(paramString, paramInt);
  }
  
  public static List<e> getRunningApplications(Context paramContext)
  {
    Object localObject2 = (ActivityManager)paramContext.getSystemService("activity");
    try
    {
      SharedPreferences localSharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences(a, 0);
      Object localObject3 = ((ActivityManager)localObject2).getRunningServices(Integer.MAX_VALUE);
      localObject2 = new ArrayList();
      localObject3 = ((List)localObject3).iterator();
      for (;;)
      {
        if (!((Iterator)localObject3).hasNext()) {
          break label364;
        }
        localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject3).next();
        localObject4 = localRunningServiceInfo.service.getPackageName();
        try
        {
          int i = paramContext.getPackageManager().getApplicationInfo((String)localObject4, 0).flags;
          if (((i & 0x1) == 1) || (localRunningServiceInfo.process.equals(paramContext.getPackageName()))) {
            continue;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          boolean bool;
          label135:
          e localE;
          Object localObject1;
          for (;;) {}
        }
        try
        {
          bool = ((String)localObject4).equals(FacebookSdk.getApplicationContext().getPackageName());
          if (bool) {
            continue;
          }
        }
        catch (Exception localException2)
        {
          break label135;
        }
        localE = new e(localRunningServiceInfo.pid, (String)localObject4);
        localE.setChoose(true);
        if ((localSharedPreferences != null) && (localSharedPreferences.contains((String)localObject4)) && (localSharedPreferences.getInt((String)localObject4, 0) >= 3))
        {
          Log.d("get apps", "set app advice " + (String)localObject4);
          localE.setChoose(false);
        }
        if (((String)localObject4).contains("facebook")) {
          localE.setChoose(false);
        }
        if (((String)localObject4).contains("labankey")) {
          localE.setChoose(false);
        }
        if (((List)localObject2).contains(localE)) {
          break;
        }
        localE.setName(getAppNameFromPackage(paramContext, (String)localObject4));
        localE.setIcon(getIconFromPackage((String)localObject4, paramContext));
        localE.setSize(getMemoryOfService(paramContext, localRunningServiceInfo.pid));
        ((List)localObject2).add(localE);
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        ActivityManager.RunningServiceInfo localRunningServiceInfo;
        localObject1 = null;
        continue;
        Object localObject4 = (e)((List)localObject2).get(((List)localObject2).indexOf(localE));
        ((e)localObject4).setSize(((e)localObject4).getSize() + getMemoryOfService(paramContext, localRunningServiceInfo.pid));
      }
    }
    label364:
    return localObject2;
  }
  
  public static long getTotalInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
  }
  
  public static long getTotalRAM(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
      return localMemoryInfo.totalMem;
    }
    long l = 0L;
    try
    {
      int i = Integer.parseInt(new RandomAccessFile("/proc/meminfo", "r").readLine().replaceAll("\\D+", "")) / 1024;
      l = i;
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    return l * 1024L;
  }
  
  public static void hideKeyBoard(Context paramContext, EditText paramEditText)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
  }
  
  public static boolean isAccessibilitySettingsOn(Context paramContext)
  {
    boolean bool2 = false;
    String str1 = paramContext.getPackageName();
    String str2 = MyAccessibilityService.class.getSimpleName();
    try
    {
      i = Settings.Secure.getInt(paramContext.getApplicationContext().getContentResolver(), "accessibility_enabled");
      TextUtils.SimpleStringSplitter localSimpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
      boolean bool1 = bool2;
      if (i == 1)
      {
        paramContext = Settings.Secure.getString(paramContext.getApplicationContext().getContentResolver(), "enabled_accessibility_services");
        bool1 = bool2;
        if (paramContext != null)
        {
          localSimpleStringSplitter.setString(paramContext);
          do
          {
            bool1 = bool2;
            if (!localSimpleStringSplitter.hasNext()) {
              break;
            }
            paramContext = localSimpleStringSplitter.next();
          } while ((!paramContext.contains(str1)) || (!paramContext.contains(str2)));
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      for (;;)
      {
        int i = 0;
      }
    }
  }
  
  public static boolean isPackageInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 128);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isRecommendAppLock(String paramString)
  {
    String[] arrayOfString = new String[18];
    arrayOfString[0] = "com.android.settings";
    arrayOfString[1] = "com.android.email";
    arrayOfString[2] = "com.android.chrome";
    arrayOfString[3] = "com.google.android.youtube";
    arrayOfString[4] = "com.facebook.katana";
    arrayOfString[5] = "com.facebook.orca";
    arrayOfString[6] = "com.google.android.gm";
    arrayOfString[7] = "com.instagram.android";
    arrayOfString[8] = "com.twitter.android";
    arrayOfString[9] = "com.snapchat.android";
    arrayOfString[10] = "com.whatsapp";
    arrayOfString[11] = "com.zing.zalo";
    arrayOfString[12] = "com.facebook.lite";
    arrayOfString[13] = "com.viber.voip";
    arrayOfString[14] = "com.skype.raider";
    arrayOfString[15] = "com.dropbox.android";
    arrayOfString[16] = "com.google.android.apps.docs";
    arrayOfString[17] = "com.google.android.apps.photos";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfString[i].equals(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isServiceRunning(Context paramContext, Class<?> paramClass)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (paramContext.hasNext()) {
      if (((ActivityManager.RunningServiceInfo)paramContext.next()).service.getClassName().equals(paramClass.getName())) {
        return true;
      }
    }
    return false;
  }
  
  @TargetApi(21)
  public static boolean isUsageAccessEnabled(Context paramContext)
  {
    try
    {
      ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0);
      int i = ((AppOpsManager)paramContext.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", localApplicationInfo.uid, localApplicationInfo.packageName);
      return i == 0;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static void killBackgroundProcesses(Context paramContext, String paramString)
  {
    ((ActivityManager)paramContext.getSystemService("activity")).killBackgroundProcesses(paramString);
  }
  
  public static String loadJSONFromAsset(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString);
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString, "UTF-8");
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String loadJSONFromFile(Context paramContext, String paramString)
  {
    paramContext = new StringBuilder();
    try
    {
      paramString = new BufferedReader(new FileReader(paramString));
      for (;;)
      {
        String str = paramString.readLine();
        if (str == null) {
          break;
        }
        paramContext.append(str);
        paramContext.append('\n');
      }
      paramString.close();
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    return paramContext.toString();
  }
  
  public static void notificateAppLock(Context paramContext, int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, Intent paramIntent)
  {
    paramString1 = new NotificationCompat.Builder(paramContext).setSmallIcon(2130837763).setLargeIcon(BitmapFactory.decodeResource(paramContext.getResources(), paramInt2)).setContentTitle(paramString2).setContentText(paramString3).setTicker(paramString1);
    paramString1.setContentIntent(PendingIntent.getActivity(paramContext, paramInt1, paramIntent, 134217728));
    paramString1.setAutoCancel(true);
    paramString1.setOnlyAlertOnce(true);
    ((NotificationManager)paramContext.getSystemService("notification")).notify(paramInt1, paramString1.build());
  }
  
  public static void notificatePush(Context paramContext, int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, Intent paramIntent)
  {
    paramString1 = new NotificationCompat.Builder(paramContext).setSmallIcon(2130837763).setLargeIcon(BitmapFactory.decodeResource(paramContext.getResources(), paramInt2)).setContentTitle(paramString2).setContentText(paramString3).setTicker(paramString1);
    if (paramIntent != null) {
      paramString1.setContentIntent(PendingIntent.getActivity(paramContext, paramInt1, paramIntent, 134217728));
    }
    paramString1.setAutoCancel(true);
    paramString1.setOnlyAlertOnce(true);
    ((NotificationManager)paramContext.getSystemService("notification")).notify(paramInt1, paramString1.build());
  }
  
  public static void openDeveloperSettings(Context paramContext)
  {
    paramContext.startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
  }
  
  public static void openSecuritySettings(Context paramContext)
  {
    paramContext.startActivity(new Intent("android.settings.SECURITY_SETTINGS"));
  }
  
  @TargetApi(21)
  public static void openUsageAccessSetings(Context paramContext)
  {
    if (!isUsageAccessEnabled(paramContext))
    {
      Intent localIntent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
    }
  }
  
  public static boolean packageInfoHasPermission(PackageInfo paramPackageInfo, String paramString)
  {
    if (paramPackageInfo.requestedPermissions == null) {}
    for (;;)
    {
      return false;
      paramPackageInfo = paramPackageInfo.requestedPermissions;
      int j = paramPackageInfo.length;
      int i = 0;
      while (i < j)
      {
        if (paramPackageInfo[i].equals(paramString)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static void rateUs(Activity paramActivity)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramActivity.getPackageName()));
    localIntent.addFlags(1208483840);
    try
    {
      paramActivity.getSharedPreferences(DoneBoostActivity.a, 0).edit().putInt(DoneBoostActivity.b, 3).commit();
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramActivity.getPackageName())));
    }
  }
  
  public static String roundFileSize(long paramLong)
  {
    String str = null;
    float f2 = (float)paramLong;
    float f1 = f2;
    if (f2 >= 1024.0F)
    {
      str = "KB";
      f2 = (float)(f2 / 1024.0D);
      f1 = f2;
      if (f2 >= 1024.0F)
      {
        str = "MB";
        f2 = (float)(f2 / 1024.0D);
        f1 = f2;
        if (f2 >= 1024.0F)
        {
          str = "GB";
          f1 = (float)(f2 / 1024.0D);
        }
      }
    }
    if (str == null) {
      return paramLong + "";
    }
    if (!str.equals("KB"))
    {
      f2 = f1;
      if (!str.equals("MB")) {}
    }
    else
    {
      f2 = (float)Math.ceil(f1);
    }
    f1 = f2;
    if (str.equals("GB")) {
      f1 = (float)Math.ceil(f2 * 100.0F) / 100.0F;
    }
    return (int)f1 + "";
  }
  
  public static void serializeToDataFolder(Context paramContext, Serializable paramSerializable, String paramString)
  {
    paramContext = getInternalDataPath(paramContext);
    serializeToFile(paramContext + File.separatorChar + paramString, paramSerializable);
  }
  
  public static void serializeToFile(String paramString, Serializable paramSerializable)
  {
    Object localObject = new File(paramString).getParent();
    File localFile = new File((String)localObject);
    if ((localObject != null) && (!existsFolder((String)localObject))) {
      localFile.mkdirs();
    }
    paramString = new FileOutputStream(paramString);
    localObject = new ObjectOutputStream(paramString);
    ((ObjectOutputStream)localObject).writeObject(paramSerializable);
    ((ObjectOutputStream)localObject).close();
    paramString.close();
  }
  
  public static void showConfirmDialog(Context paramContext, String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    c.a localA = new c.a(paramContext, 2131558638);
    localA.setTitle(paramString);
    localA.setPositiveButton(paramContext.getString(2131427490), paramOnClickListener);
    localA.setNegativeButton(paramContext.getString(2131427382), null);
    localA.create().show();
  }
  
  public static boolean stringMatchesMask(String paramString1, String paramString2)
  {
    int i;
    if (paramString2.charAt(paramString2.length() - 1) == '*')
    {
      paramString2 = paramString2.substring(0, paramString2.length() - 2);
      i = 1;
      if (i != 1) {
        break label51;
      }
      if (!paramString1.startsWith(paramString2)) {
        break label49;
      }
    }
    label49:
    label51:
    while (paramString1.equals(paramString2))
    {
      return true;
      i = 0;
      break;
      return false;
    }
    return false;
  }
  
  public static void writeTextFile(String paramString1, String paramString2)
  {
    try
    {
      localBufferedWriter = new BufferedWriter(new FileWriter(paramString1));
      if (paramString2 == null) {
        break label41;
      }
    }
    finally
    {
      try
      {
        localBufferedWriter.write(paramString2);
        if (localBufferedWriter != null) {
          localBufferedWriter.close();
        }
        return;
      }
      finally
      {
        BufferedWriter localBufferedWriter;
        paramString2 = localBufferedWriter;
      }
      paramString1 = finally;
      paramString2 = null;
    }
    paramString2.close();
    label41:
    throw paramString1;
  }
}
