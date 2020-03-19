package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.system.Os;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class CarUUID
{
  private static final Pattern a = Pattern.compile("(\\w{32})");
  
  public CarUUID() {}
  
  private static String a(Context paramContext)
  {
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  /* Error */
  private static String a(File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +73 -> 74
    //   4: aload_0
    //   5: invokevirtual 52	java/io/File:exists	()Z
    //   8: ifeq +66 -> 74
    //   11: new 54	java/io/FileInputStream
    //   14: dup
    //   15: aload_0
    //   16: invokespecial 57	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   19: astore_0
    //   20: sipush 1024
    //   23: newarray byte
    //   25: astore_2
    //   26: new 39	java/lang/String
    //   29: dup
    //   30: aload_2
    //   31: iconst_0
    //   32: aload_0
    //   33: aload_2
    //   34: invokevirtual 61	java/io/FileInputStream:read	([B)I
    //   37: invokespecial 64	java/lang/String:<init>	([BII)V
    //   40: astore_2
    //   41: getstatic 18	com/baidu/mobstat/CarUUID:a	Ljava/util/regex/Pattern;
    //   44: aload_2
    //   45: invokevirtual 68	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   48: invokevirtual 73	java/util/regex/Matcher:matches	()Z
    //   51: istore_1
    //   52: iload_1
    //   53: ifeq +9 -> 62
    //   56: aload_0
    //   57: invokestatic 78	com/baidu/mobstat/fc:a	(Ljava/io/Closeable;)V
    //   60: aload_2
    //   61: areturn
    //   62: aconst_null
    //   63: astore_2
    //   64: goto -8 -> 56
    //   67: astore_0
    //   68: aconst_null
    //   69: astore_0
    //   70: aload_0
    //   71: invokestatic 78	com/baidu/mobstat/fc:a	(Ljava/io/Closeable;)V
    //   74: aconst_null
    //   75: areturn
    //   76: astore_2
    //   77: aconst_null
    //   78: astore_0
    //   79: aload_0
    //   80: invokestatic 78	com/baidu/mobstat/fc:a	(Ljava/io/Closeable;)V
    //   83: aload_2
    //   84: athrow
    //   85: astore_2
    //   86: goto -7 -> 79
    //   89: astore_2
    //   90: goto -20 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramFile	File
    //   51	2	1	bool	boolean
    //   25	39	2	localObject1	Object
    //   76	8	2	localObject2	Object
    //   85	1	2	localObject3	Object
    //   89	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   11	20	67	java/lang/Exception
    //   11	20	76	finally
    //   20	52	85	finally
    //   20	52	89	java/lang/Exception
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    bool1 = true;
    localFileOutputStream1 = null;
    localFileOutputStream2 = localFileOutputStream1;
    try
    {
      if (Build.VERSION.SDK_INT < 21) {
        break label126;
      }
      i = 0;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        int i;
        boolean bool2;
        paramContext = paramContext;
        paramContext = null;
        fc.a(paramContext);
      }
    }
    finally
    {
      fc.a(localFileOutputStream2);
    }
    localFileOutputStream2 = localFileOutputStream1;
    localFileOutputStream1 = paramContext.openFileOutput("libdueros_uuid.so", i);
    localFileOutputStream2 = localFileOutputStream1;
  }
  
  @SuppressLint({"NewApi"})
  private static boolean a(File paramFile, int paramInt)
  {
    if (Build.VERSION.SDK_INT < 21) {
      return true;
    }
    try
    {
      Os.chmod(paramFile.getAbsolutePath(), paramInt);
      return true;
    }
    catch (Exception paramFile) {}
    return false;
  }
  
  private static boolean a(FileOutputStream paramFileOutputStream, String paramString)
  {
    try
    {
      paramFileOutputStream.write(paramString.getBytes());
      paramFileOutputStream.flush();
      return true;
    }
    catch (Exception paramFileOutputStream) {}
    return false;
  }
  
  private static String b(Context paramContext)
  {
    return a(paramContext.getFileStreamPath("libdueros_uuid.so"));
  }
  
  /* Error */
  private static boolean b(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc -109
    //   3: invokestatic 150	com/baidu/mobstat/CarUUID:c	(Landroid/content/Context;Ljava/lang/String;)Z
    //   6: ifne +5 -> 11
    //   9: iconst_0
    //   10: ireturn
    //   11: new 48	java/io/File
    //   14: dup
    //   15: new 48	java/io/File
    //   18: dup
    //   19: invokestatic 156	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   22: ldc -98
    //   24: invokespecial 161	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   27: ldc -93
    //   29: invokespecial 161	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   32: astore_0
    //   33: aconst_null
    //   34: astore_3
    //   35: new 135	java/io/FileOutputStream
    //   38: dup
    //   39: aload_0
    //   40: invokespecial 164	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   43: astore_0
    //   44: aload_0
    //   45: aload_1
    //   46: invokestatic 96	com/baidu/mobstat/CarUUID:a	(Ljava/io/FileOutputStream;Ljava/lang/String;)Z
    //   49: istore_2
    //   50: aload_0
    //   51: invokestatic 78	com/baidu/mobstat/fc:a	(Ljava/io/Closeable;)V
    //   54: iload_2
    //   55: ireturn
    //   56: astore_0
    //   57: aconst_null
    //   58: astore_0
    //   59: aload_0
    //   60: invokestatic 78	com/baidu/mobstat/fc:a	(Ljava/io/Closeable;)V
    //   63: iconst_0
    //   64: ireturn
    //   65: astore_0
    //   66: aload_3
    //   67: astore_1
    //   68: aload_1
    //   69: invokestatic 78	com/baidu/mobstat/fc:a	(Ljava/io/Closeable;)V
    //   72: aload_0
    //   73: athrow
    //   74: astore_3
    //   75: aload_0
    //   76: astore_1
    //   77: aload_3
    //   78: astore_0
    //   79: goto -11 -> 68
    //   82: astore_1
    //   83: goto -24 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	paramContext	Context
    //   0	86	1	paramString	String
    //   49	6	2	bool	boolean
    //   34	33	3	localObject1	Object
    //   74	4	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   35	44	56	java/lang/Exception
    //   35	44	65	finally
    //   44	50	74	finally
    //   44	50	82	java/lang/Exception
  }
  
  private static String c(Context paramContext)
  {
    if (!c(paramContext, "android.permission.READ_EXTERNAL_STORAGE")) {
      return null;
    }
    return a(new File(new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig"), ".dueros_uuid"));
  }
  
  private static boolean c(Context paramContext, String paramString)
  {
    return paramContext.checkPermission(paramString, Process.myPid(), Process.myUid()) == 0;
  }
  
  private static String d(Context paramContext)
  {
    Object localObject1 = paramContext.getPackageManager().getInstalledApplications(0);
    paramContext = paramContext.getApplicationInfo();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
      if (!paramContext.packageName.equals(((ApplicationInfo)localObject2).packageName))
      {
        localObject2 = a(new File(new File(((ApplicationInfo)localObject2).dataDir, "files"), "libdueros_uuid.so"));
        if (localObject2 != null) {
          return localObject2;
        }
      }
    }
    return null;
  }
  
  public static String optUUID(Context paramContext)
  {
    String str = b(paramContext);
    if (str != null) {
      return str;
    }
    str = c(paramContext);
    if (str != null)
    {
      a(paramContext, str);
      return str;
    }
    str = d(paramContext);
    if (str != null)
    {
      a(paramContext, str);
      b(paramContext, str);
      return str;
    }
    str = a(paramContext);
    if (str != null)
    {
      a(paramContext, str);
      b(paramContext, str);
      return str;
    }
    return "";
  }
}
