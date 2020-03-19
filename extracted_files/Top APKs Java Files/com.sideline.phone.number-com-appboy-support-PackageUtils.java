package com.appboy.support;

import com.appboy.Constants;

public class PackageUtils
{
  private static final String a = String.format("%s.%s", new Object[] { Constants.APPBOY_LOG_TAG_PREFIX, PackageUtils.class.getName() });
  private static String b;
  
  public PackageUtils() {}
  
  /* Error */
  public static String getResourcePackageName(android.content.Context paramContext)
  {
    // Byte code:
    //   0: getstatic 44	com/appboy/support/PackageUtils:b	Ljava/lang/String;
    //   3: ifnull +7 -> 10
    //   6: getstatic 44	com/appboy/support/PackageUtils:b	Ljava/lang/String;
    //   9: areturn
    //   10: aload_0
    //   11: invokevirtual 50	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   14: astore_2
    //   15: aload_2
    //   16: getstatic 56	com/appboy/R$string:resource_for_package_identification	I
    //   19: invokevirtual 61	android/content/res/Resources:getResourcePackageName	(I)Ljava/lang/String;
    //   22: astore_3
    //   23: aload_3
    //   24: putstatic 44	com/appboy/support/PackageUtils:b	Ljava/lang/String;
    //   27: aload_3
    //   28: areturn
    //   29: aload_0
    //   30: invokevirtual 65	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   33: iconst_0
    //   34: invokevirtual 71	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   37: invokeinterface 77 1 0
    //   42: astore_3
    //   43: aload_3
    //   44: invokeinterface 83 1 0
    //   49: ifeq +61 -> 110
    //   52: aload_2
    //   53: ldc 85
    //   55: aconst_null
    //   56: aload_3
    //   57: invokeinterface 89 1 0
    //   62: checkcast 91	android/content/pm/PackageInfo
    //   65: getfield 94	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   68: invokevirtual 98	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   71: istore_1
    //   72: iload_1
    //   73: ifeq +34 -> 107
    //   76: aload_2
    //   77: iload_1
    //   78: invokevirtual 101	android/content/res/Resources:getResourceName	(I)Ljava/lang/String;
    //   81: ldc 85
    //   83: invokevirtual 105	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   86: ifeq +18 -> 104
    //   89: aload_2
    //   90: iload_1
    //   91: invokevirtual 61	android/content/res/Resources:getResourcePackageName	(I)Ljava/lang/String;
    //   94: astore 4
    //   96: aload 4
    //   98: putstatic 44	com/appboy/support/PackageUtils:b	Ljava/lang/String;
    //   101: aload 4
    //   103: areturn
    //   104: goto -61 -> 43
    //   107: goto -64 -> 43
    //   110: goto +40 -> 150
    //   113: getstatic 30	com/appboy/support/PackageUtils:a	Ljava/lang/String;
    //   116: ldc 107
    //   118: invokestatic 113	com/appboy/support/AppboyLogger:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   121: pop
    //   122: goto +28 -> 150
    //   125: astore_2
    //   126: getstatic 30	com/appboy/support/PackageUtils:a	Ljava/lang/String;
    //   129: ldc 115
    //   131: aload_2
    //   132: invokestatic 119	com/appboy/support/AppboyLogger:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   135: pop
    //   136: goto +14 -> 150
    //   139: astore_2
    //   140: getstatic 30	com/appboy/support/PackageUtils:a	Ljava/lang/String;
    //   143: ldc 121
    //   145: aload_2
    //   146: invokestatic 119	com/appboy/support/AppboyLogger:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   149: pop
    //   150: aload_0
    //   151: invokevirtual 124	android/content/Context:getPackageName	()Ljava/lang/String;
    //   154: astore_0
    //   155: aload_0
    //   156: putstatic 44	com/appboy/support/PackageUtils:b	Ljava/lang/String;
    //   159: aload_0
    //   160: areturn
    //   161: astore_2
    //   162: goto -49 -> 113
    //   165: astore_3
    //   166: goto -137 -> 29
    //   169: astore 4
    //   171: goto -64 -> 107
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	paramContext	android.content.Context
    //   71	20	1	i	int
    //   14	76	2	localResources	android.content.res.Resources
    //   125	7	2	localNullPointerException	NullPointerException
    //   139	7	2	localException1	Exception
    //   161	1	2	localNotFoundException	android.content.res.Resources.NotFoundException
    //   22	35	3	localObject	Object
    //   165	1	3	localException2	Exception
    //   94	8	4	str	String
    //   169	1	4	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   10	15	125	java/lang/NullPointerException
    //   15	27	125	java/lang/NullPointerException
    //   29	43	125	java/lang/NullPointerException
    //   43	72	125	java/lang/NullPointerException
    //   76	101	125	java/lang/NullPointerException
    //   10	15	139	java/lang/Exception
    //   29	43	139	java/lang/Exception
    //   43	72	139	java/lang/Exception
    //   10	15	161	android/content/res/Resources$NotFoundException
    //   15	27	161	android/content/res/Resources$NotFoundException
    //   29	43	161	android/content/res/Resources$NotFoundException
    //   43	72	161	android/content/res/Resources$NotFoundException
    //   76	101	161	android/content/res/Resources$NotFoundException
    //   15	27	165	java/lang/Exception
    //   76	101	169	java/lang/Exception
  }
}
