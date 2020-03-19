package com.avl.engine.h;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public final class i
{
  private static final byte[] a = { 80, 75, 3, 4 };
  
  private i() {}
  
  public static ApplicationInfo a(Context paramContext, String paramString)
  {
    return a(paramContext.getPackageManager(), paramString);
  }
  
  /* Error */
  private static ApplicationInfo a(PackageManager paramPackageManager, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: sipush 128
    //   8: invokevirtual 36	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   11: astore_0
    //   12: goto +18 -> 30
    //   15: astore_0
    //   16: goto +19 -> 35
    //   19: astore_0
    //   20: ldc 38
    //   22: ldc 39
    //   24: aload_0
    //   25: invokestatic 44	com/avl/engine/h/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   28: aconst_null
    //   29: astore_0
    //   30: ldc 2
    //   32: monitorexit
    //   33: aload_0
    //   34: areturn
    //   35: ldc 2
    //   37: monitorexit
    //   38: aload_0
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	paramPackageManager	PackageManager
    //   0	40	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   3	12	15	finally
    //   20	28	15	finally
    //   30	33	15	finally
    //   35	38	15	finally
    //   3	12	19	android/content/pm/PackageManager$NameNotFoundException
  }
  
  /* Error */
  public static PackageInfo a(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: iload_2
    //   6: invokevirtual 49	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   9: astore_0
    //   10: goto +18 -> 28
    //   13: astore_0
    //   14: goto +19 -> 33
    //   17: astore_0
    //   18: ldc 38
    //   20: ldc 50
    //   22: aload_0
    //   23: invokestatic 44	com/avl/engine/h/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   26: aconst_null
    //   27: astore_0
    //   28: ldc 2
    //   30: monitorexit
    //   31: aload_0
    //   32: areturn
    //   33: ldc 2
    //   35: monitorexit
    //   36: aload_0
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	paramPackageManager	PackageManager
    //   0	38	1	paramString	String
    //   0	38	2	paramInt	int
    // Exception table:
    //   from	to	target	type
    //   3	10	13	finally
    //   18	26	13	finally
    //   28	31	13	finally
    //   33	36	13	finally
    //   3	10	17	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static String a(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    try
    {
      paramPackageManager = paramApplicationInfo.loadLabel(paramPackageManager).toString();
      return paramPackageManager;
    }
    finally {}
  }
  
  /* Error */
  public static java.util.List a(PackageManager paramPackageManager)
  {
    // Byte code:
    //   0: new 68	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 69	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: ldc 2
    //   10: monitorenter
    //   11: aload_0
    //   12: iconst_0
    //   13: invokevirtual 73	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   16: astore_0
    //   17: goto +18 -> 35
    //   20: astore_0
    //   21: goto +19 -> 40
    //   24: astore_0
    //   25: ldc 38
    //   27: ldc 75
    //   29: aload_0
    //   30: invokestatic 44	com/avl/engine/h/a:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   33: aload_1
    //   34: astore_0
    //   35: ldc 2
    //   37: monitorexit
    //   38: aload_0
    //   39: areturn
    //   40: ldc 2
    //   42: monitorexit
    //   43: aload_0
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	paramPackageManager	PackageManager
    //   7	27	1	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   11	17	20	finally
    //   25	33	20	finally
    //   35	38	20	finally
    //   40	43	20	finally
    //   11	17	24	java/lang/RuntimeException
  }
  
  private static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    if (paramString.toLowerCase(Locale.US).endsWith(".apk")) {
      return true;
    }
    paramString = new File(paramString);
    if (paramString.length() > 4L) {}
    try
    {
      paramString = g.b(paramString);
      boolean bool = Arrays.equals(a, paramString);
      return bool;
    }
    catch (IOException paramString) {}
    return false;
    return false;
  }
  
  public static PackageInfo b(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    for (;;)
    {
      try
      {
        if (a(paramString))
        {
          paramPackageManager = paramPackageManager.getPackageArchiveInfo(paramString, paramInt);
          return paramPackageManager;
        }
      }
      finally {}
      paramPackageManager = null;
    }
  }
}
