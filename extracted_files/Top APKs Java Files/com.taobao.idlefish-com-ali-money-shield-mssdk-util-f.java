package com.ali.money.shield.mssdk.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class f
{
  /* Error */
  public static boolean a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: invokevirtual 14	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   9: iconst_0
    //   10: invokevirtual 20	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   13: invokeinterface 26 1 0
    //   18: astore_0
    //   19: aload_0
    //   20: invokeinterface 32 1 0
    //   25: ifeq +129 -> 154
    //   28: aload_0
    //   29: invokeinterface 36 1 0
    //   34: checkcast 38	android/content/pm/PackageInfo
    //   37: getfield 42	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   40: aload_1
    //   41: invokevirtual 48	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   44: istore_2
    //   45: iload_2
    //   46: ifeq -27 -> 19
    //   49: iconst_1
    //   50: ireturn
    //   51: astore 5
    //   53: invokestatic 54	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   56: ldc 56
    //   58: invokevirtual 60	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   61: astore_0
    //   62: new 62	java/io/BufferedReader
    //   65: dup
    //   66: new 64	java/io/InputStreamReader
    //   69: dup
    //   70: aload_0
    //   71: invokevirtual 70	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   74: invokespecial 74	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   77: invokespecial 77	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   80: astore_3
    //   81: aload_3
    //   82: invokevirtual 81	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   85: astore 4
    //   87: aload 4
    //   89: ifnull +44 -> 133
    //   92: aload 4
    //   94: aload_1
    //   95: invokevirtual 85	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   98: istore_2
    //   99: iload_2
    //   100: ifeq -19 -> 81
    //   103: aload_3
    //   104: ifnull +7 -> 111
    //   107: aload_3
    //   108: invokevirtual 89	java/io/BufferedReader:close	()V
    //   111: aload_0
    //   112: ifnull +7 -> 119
    //   115: aload_0
    //   116: invokevirtual 92	java/lang/Process:destroy	()V
    //   119: iconst_1
    //   120: ireturn
    //   121: astore_1
    //   122: aload 5
    //   124: invokevirtual 95	java/lang/Throwable:printStackTrace	()V
    //   127: goto -16 -> 111
    //   130: astore_0
    //   131: aload_0
    //   132: athrow
    //   133: aload_0
    //   134: invokevirtual 99	java/lang/Process:waitFor	()I
    //   137: pop
    //   138: aload_3
    //   139: ifnull +7 -> 146
    //   142: aload_3
    //   143: invokevirtual 89	java/io/BufferedReader:close	()V
    //   146: aload_0
    //   147: ifnull +7 -> 154
    //   150: aload_0
    //   151: invokevirtual 92	java/lang/Process:destroy	()V
    //   154: iconst_0
    //   155: ireturn
    //   156: astore_1
    //   157: aload 5
    //   159: invokevirtual 95	java/lang/Throwable:printStackTrace	()V
    //   162: goto -16 -> 146
    //   165: astore_0
    //   166: aconst_null
    //   167: astore_0
    //   168: aload_3
    //   169: astore_1
    //   170: aload_0
    //   171: ifnull +7 -> 178
    //   174: aload_0
    //   175: invokevirtual 89	java/io/BufferedReader:close	()V
    //   178: aload_1
    //   179: ifnull -25 -> 154
    //   182: aload_1
    //   183: invokevirtual 92	java/lang/Process:destroy	()V
    //   186: goto -32 -> 154
    //   189: astore_0
    //   190: goto -36 -> 154
    //   193: astore_0
    //   194: aload 5
    //   196: invokevirtual 95	java/lang/Throwable:printStackTrace	()V
    //   199: goto -21 -> 178
    //   202: astore_1
    //   203: aconst_null
    //   204: astore_0
    //   205: aload 4
    //   207: astore_3
    //   208: aload_3
    //   209: ifnull +7 -> 216
    //   212: aload_3
    //   213: invokevirtual 89	java/io/BufferedReader:close	()V
    //   216: aload_0
    //   217: ifnull +7 -> 224
    //   220: aload_0
    //   221: invokevirtual 92	java/lang/Process:destroy	()V
    //   224: aload_1
    //   225: athrow
    //   226: astore_3
    //   227: aload 5
    //   229: invokevirtual 95	java/lang/Throwable:printStackTrace	()V
    //   232: goto -16 -> 216
    //   235: astore_0
    //   236: goto -117 -> 119
    //   239: astore_0
    //   240: goto -86 -> 154
    //   243: astore_0
    //   244: goto -20 -> 224
    //   247: astore_1
    //   248: aload 4
    //   250: astore_3
    //   251: goto -43 -> 208
    //   254: astore_1
    //   255: goto -47 -> 208
    //   258: astore_1
    //   259: aconst_null
    //   260: astore_3
    //   261: aload_0
    //   262: astore_1
    //   263: aload_3
    //   264: astore_0
    //   265: goto -95 -> 170
    //   268: astore_1
    //   269: aload_0
    //   270: astore_1
    //   271: aload_3
    //   272: astore_0
    //   273: goto -103 -> 170
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	276	0	paramContext	Context
    //   0	276	1	paramString	String
    //   44	56	2	bool	boolean
    //   4	209	3	localObject	Object
    //   226	1	3	localThrowable1	Throwable
    //   250	22	3	str1	String
    //   1	248	4	str2	String
    //   51	177	5	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   5	19	51	java/lang/Throwable
    //   19	45	51	java/lang/Throwable
    //   107	111	121	java/lang/Throwable
    //   5	19	130	finally
    //   19	45	130	finally
    //   107	111	130	finally
    //   115	119	130	finally
    //   122	127	130	finally
    //   142	146	130	finally
    //   150	154	130	finally
    //   157	162	130	finally
    //   174	178	130	finally
    //   182	186	130	finally
    //   194	199	130	finally
    //   212	216	130	finally
    //   220	224	130	finally
    //   224	226	130	finally
    //   227	232	130	finally
    //   142	146	156	java/lang/Throwable
    //   53	62	165	java/lang/Throwable
    //   182	186	189	java/lang/Throwable
    //   174	178	193	java/lang/Throwable
    //   53	62	202	finally
    //   212	216	226	java/lang/Throwable
    //   115	119	235	java/lang/Throwable
    //   150	154	239	java/lang/Throwable
    //   220	224	243	java/lang/Throwable
    //   62	81	247	finally
    //   81	87	254	finally
    //   92	99	254	finally
    //   133	138	254	finally
    //   62	81	258	java/lang/Throwable
    //   81	87	268	java/lang/Throwable
    //   92	99	268	java/lang/Throwable
    //   133	138	268	java/lang/Throwable
  }
  
  public static PackageInfo b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static String c(Context paramContext, String paramString)
  {
    long l = System.currentTimeMillis();
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = d.a(paramContext.getPackageInfo(paramString, 64).signatures);
      c.c("MS-SDK", "PackagesUtil.getSignatureMD5ForInstalled cost: " + (System.currentTimeMillis() - l) + "ms");
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
}
