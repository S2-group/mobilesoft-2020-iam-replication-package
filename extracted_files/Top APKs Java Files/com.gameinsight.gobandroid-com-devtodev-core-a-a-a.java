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
    //   28: invokevirtual 74	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   31: aconst_null
    //   32: astore_1
    //   33: aload_3
    //   34: invokevirtual 77	java/io/File:getCanonicalPath	()Ljava/lang/String;
    //   37: astore 5
    //   39: aload_3
    //   40: astore_0
    //   41: aload_1
    //   42: ifnull +97 -> 139
    //   45: aload_3
    //   46: astore_0
    //   47: aload 5
    //   49: aload_1
    //   50: getfield 82	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   53: invokevirtual 88	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   56: ifeq +83 -> 139
    //   59: aload 5
    //   61: aload_1
    //   62: getfield 82	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   65: invokevirtual 92	java/lang/String:length	()I
    //   68: invokevirtual 96	java/lang/String:substring	(I)Ljava/lang/String;
    //   71: astore_1
    //   72: aload 4
    //   74: iconst_0
    //   75: invokevirtual 100	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   78: astore 4
    //   80: aload_3
    //   81: astore_0
    //   82: aload 4
    //   84: ifnull +55 -> 139
    //   87: aload 4
    //   89: invokeinterface 106 1 0
    //   94: astore 4
    //   96: aload_3
    //   97: astore_0
    //   98: aload 4
    //   100: invokeinterface 111 1 0
    //   105: ifeq +34 -> 139
    //   108: new 46	java/io/File
    //   111: dup
    //   112: aload 4
    //   114: invokeinterface 115 1 0
    //   119: checkcast 79	android/content/pm/ApplicationInfo
    //   122: getfield 82	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   125: aload_1
    //   126: invokespecial 118	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   129: astore_0
    //   130: aload_0
    //   131: invokevirtual 50	java/io/File:exists	()Z
    //   134: istore_2
    //   135: iload_2
    //   136: ifeq -40 -> 96
    //   139: aload_0
    //   140: invokevirtual 50	java/io/File:exists	()Z
    //   143: ifeq +29 -> 172
    //   146: aload_0
    //   147: invokestatic 54	com/devtodev/core/utils/IOUtils:loadString	(Ljava/io/File;)Ljava/lang/String;
    //   150: areturn
    //   151: astore_0
    //   152: goto +22 -> 174
    //   155: astore_0
    //   156: aload_0
    //   157: invokevirtual 119	java/io/IOException:printStackTrace	()V
    //   160: aload_3
    //   161: invokevirtual 50	java/io/File:exists	()Z
    //   164: ifeq +8 -> 172
    //   167: aload_3
    //   168: invokestatic 54	com/devtodev/core/utils/IOUtils:loadString	(Ljava/io/File;)Ljava/lang/String;
    //   171: areturn
    //   172: aconst_null
    //   173: areturn
    //   174: aload_3
    //   175: invokevirtual 50	java/io/File:exists	()Z
    //   178: ifeq +8 -> 186
    //   181: aload_3
    //   182: invokestatic 54	com/devtodev/core/utils/IOUtils:loadString	(Ljava/io/File;)Ljava/lang/String;
    //   185: areturn
    //   186: aload_0
    //   187: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	paramContext	Context
    //   0	188	1	paramString	String
    //   134	2	2	bool	boolean
    //   5	177	3	localFile	File
    //   10	103	4	localObject	Object
    //   37	23	5	str	String
    // Exception table:
    //   from	to	target	type
    //   12	23	26	android/content/pm/PackageManager$NameNotFoundException
    //   6	12	151	finally
    //   12	23	151	finally
    //   27	31	151	finally
    //   33	39	151	finally
    //   47	80	151	finally
    //   87	96	151	finally
    //   98	135	151	finally
    //   156	160	151	finally
    //   6	12	155	java/io/IOException
    //   12	23	155	java/io/IOException
    //   27	31	155	java/io/IOException
    //   33	39	155	java/io/IOException
    //   47	80	155	java/io/IOException
    //   87	96	155	java/io/IOException
    //   98	135	155	java/io/IOException
  }
}
