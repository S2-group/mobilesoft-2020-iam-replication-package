package com.devtodev.core.a.a;

import android.content.Context;
import com.devtodev.core.utils.IOUtils;
import java.io.File;
import java.util.UUID;

public class a
{
  public static final String a = "a";
  
  public a() {}
  
  public static String a(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    String str2 = a(paramContext, "store_for_udid");
    String str1 = str2;
    if (str2 == null)
    {
      str2 = b(paramContext, "store_for_udid");
      str1 = str2;
      if (str2 == null) {
        str1 = UUID.randomUUID().toString();
      }
      IOUtils.saveString(paramContext, "store_for_udid", str1);
    }
    return str1;
  }
  
  private static String a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getFileStreamPath(paramString);
    if (paramContext.exists()) {
      return IOUtils.loadString(paramContext);
    }
    return null;
  }
  
  /* Error */
  private static String b(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 44	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   5: astore_3
    //   6: aload_0
    //   7: invokevirtual 62	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: astore 4
    //   12: aload 4
    //   14: aload_0
    //   15: invokevirtual 65	android/content/Context:getPackageName	()Ljava/lang/String;
    //   18: iconst_0
    //   19: invokevirtual 71	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   22: astore_1
    //   23: goto +10 -> 33
    //   26: astore_0
    //   27: aload_0
    //   28: invokestatic 77	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   31: aconst_null
    //   32: astore_1
    //   33: aload_3
    //   34: invokevirtual 80	java/io/File:getCanonicalPath	()Ljava/lang/String;
    //   37: astore 5
    //   39: aload_3
    //   40: astore_0
    //   41: aload_1
    //   42: ifnull +86 -> 128
    //   45: aload_3
    //   46: astore_0
    //   47: aload 5
    //   49: aload_1
    //   50: getfield 85	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   53: invokevirtual 91	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   56: ifeq +72 -> 128
    //   59: aload 5
    //   61: aload_1
    //   62: getfield 85	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   65: invokevirtual 95	java/lang/String:length	()I
    //   68: invokevirtual 99	java/lang/String:substring	(I)Ljava/lang/String;
    //   71: astore_1
    //   72: aload 4
    //   74: iconst_0
    //   75: invokevirtual 103	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   78: invokeinterface 109 1 0
    //   83: astore 4
    //   85: aload_3
    //   86: astore_0
    //   87: aload 4
    //   89: invokeinterface 114 1 0
    //   94: ifeq +34 -> 128
    //   97: new 46	java/io/File
    //   100: dup
    //   101: aload 4
    //   103: invokeinterface 118 1 0
    //   108: checkcast 82	android/content/pm/ApplicationInfo
    //   111: getfield 85	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   114: aload_1
    //   115: invokespecial 121	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   118: astore_0
    //   119: aload_0
    //   120: invokevirtual 50	java/io/File:exists	()Z
    //   123: istore_2
    //   124: iload_2
    //   125: ifeq -40 -> 85
    //   128: aload_0
    //   129: invokevirtual 50	java/io/File:exists	()Z
    //   132: ifeq +29 -> 161
    //   135: aload_0
    //   136: invokestatic 54	com/devtodev/core/utils/IOUtils:loadString	(Ljava/io/File;)Ljava/lang/String;
    //   139: areturn
    //   140: astore_0
    //   141: goto +22 -> 163
    //   144: astore_0
    //   145: aload_0
    //   146: invokestatic 77	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   149: aload_3
    //   150: invokevirtual 50	java/io/File:exists	()Z
    //   153: ifeq +8 -> 161
    //   156: aload_3
    //   157: invokestatic 54	com/devtodev/core/utils/IOUtils:loadString	(Ljava/io/File;)Ljava/lang/String;
    //   160: areturn
    //   161: aconst_null
    //   162: areturn
    //   163: aload_3
    //   164: invokevirtual 50	java/io/File:exists	()Z
    //   167: ifeq +8 -> 175
    //   170: aload_3
    //   171: invokestatic 54	com/devtodev/core/utils/IOUtils:loadString	(Ljava/io/File;)Ljava/lang/String;
    //   174: areturn
    //   175: aload_0
    //   176: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	177	0	paramContext	Context
    //   0	177	1	paramString	String
    //   123	2	2	bool	boolean
    //   5	166	3	localFile	File
    //   10	92	4	localObject	Object
    //   37	23	5	str	String
    // Exception table:
    //   from	to	target	type
    //   12	23	26	android/content/pm/PackageManager$NameNotFoundException
    //   6	12	140	finally
    //   12	23	140	finally
    //   27	31	140	finally
    //   33	39	140	finally
    //   47	85	140	finally
    //   87	124	140	finally
    //   145	149	140	finally
    //   6	12	144	java/io/IOException
    //   12	23	144	java/io/IOException
    //   27	31	144	java/io/IOException
    //   33	39	144	java/io/IOException
    //   47	85	144	java/io/IOException
    //   87	124	144	java/io/IOException
  }
}
