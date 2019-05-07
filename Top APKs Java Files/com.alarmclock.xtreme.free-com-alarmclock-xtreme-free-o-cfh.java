package com.alarmclock.xtreme.free.o;

public class cfh
{
  /* Error */
  public static java.util.List<android.content.pm.PackageInfo> a(android.content.Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 14	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore 4
    //   6: getstatic 20	android/os/Build$VERSION:SDK_INT	I
    //   9: bipush 22
    //   11: if_icmplt +21 -> 32
    //   14: aload 4
    //   16: iconst_0
    //   17: invokevirtual 26	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   20: astore_0
    //   21: aload_0
    //   22: invokeinterface 32 1 0
    //   27: ifle +27 -> 54
    //   30: aload_0
    //   31: areturn
    //   32: aload 4
    //   34: iconst_0
    //   35: invokevirtual 26	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   38: astore_0
    //   39: aload_0
    //   40: areturn
    //   41: astore_0
    //   42: getstatic 37	com/alarmclock/xtreme/free/o/cff:a	Lcom/alarmclock/xtreme/free/o/chf;
    //   45: ldc 39
    //   47: iconst_0
    //   48: anewarray 4	java/lang/Object
    //   51: invokevirtual 45	com/alarmclock/xtreme/free/o/chf:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   54: new 47	java/util/ArrayList
    //   57: dup
    //   58: invokespecial 51	java/util/ArrayList:<init>	()V
    //   61: astore_3
    //   62: aconst_null
    //   63: astore_1
    //   64: aconst_null
    //   65: astore_2
    //   66: aload_1
    //   67: astore_0
    //   68: invokestatic 57	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   71: ldc 59
    //   73: invokevirtual 63	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   76: astore 5
    //   78: aload_1
    //   79: astore_0
    //   80: new 65	java/io/BufferedReader
    //   83: dup
    //   84: new 67	java/io/InputStreamReader
    //   87: dup
    //   88: aload 5
    //   90: invokevirtual 73	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   93: ldc 75
    //   95: invokespecial 78	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   98: invokespecial 81	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   101: astore_1
    //   102: aload_1
    //   103: invokevirtual 85	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   106: astore_0
    //   107: aload_0
    //   108: ifnull +60 -> 168
    //   111: aload_3
    //   112: aload 4
    //   114: aload_0
    //   115: aload_0
    //   116: bipush 58
    //   118: invokevirtual 91	java/lang/String:indexOf	(I)I
    //   121: iconst_1
    //   122: iadd
    //   123: invokevirtual 95	java/lang/String:substring	(I)Ljava/lang/String;
    //   126: iconst_0
    //   127: invokevirtual 99	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   130: invokeinterface 103 2 0
    //   135: pop
    //   136: goto -34 -> 102
    //   139: astore_2
    //   140: aload_1
    //   141: astore_0
    //   142: getstatic 37	com/alarmclock/xtreme/free/o/cff:a	Lcom/alarmclock/xtreme/free/o/chf;
    //   145: aload_2
    //   146: ldc 105
    //   148: iconst_0
    //   149: anewarray 4	java/lang/Object
    //   152: invokevirtual 109	com/alarmclock/xtreme/free/o/chf:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   155: iconst_1
    //   156: anewarray 111	java/io/Closeable
    //   159: dup
    //   160: iconst_0
    //   161: aload_1
    //   162: aastore
    //   163: invokestatic 116	com/alarmclock/xtreme/free/o/cpb:a	([Ljava/io/Closeable;)V
    //   166: aload_3
    //   167: areturn
    //   168: aload 5
    //   170: invokevirtual 119	java/lang/Process:waitFor	()I
    //   173: pop
    //   174: iconst_1
    //   175: anewarray 111	java/io/Closeable
    //   178: dup
    //   179: iconst_0
    //   180: aload_1
    //   181: aastore
    //   182: invokestatic 116	com/alarmclock/xtreme/free/o/cpb:a	([Ljava/io/Closeable;)V
    //   185: goto -19 -> 166
    //   188: astore_1
    //   189: iconst_1
    //   190: anewarray 111	java/io/Closeable
    //   193: dup
    //   194: iconst_0
    //   195: aload_0
    //   196: aastore
    //   197: invokestatic 116	com/alarmclock/xtreme/free/o/cpb:a	([Ljava/io/Closeable;)V
    //   200: aload_1
    //   201: athrow
    //   202: astore_2
    //   203: aload_1
    //   204: astore_0
    //   205: aload_2
    //   206: astore_1
    //   207: goto -18 -> 189
    //   210: astore_0
    //   211: aload_2
    //   212: astore_1
    //   213: aload_0
    //   214: astore_2
    //   215: goto -75 -> 140
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	218	0	paramContext	android.content.Context
    //   63	118	1	localBufferedReader	java.io.BufferedReader
    //   188	16	1	localObject1	Object
    //   206	7	1	localObject2	Object
    //   65	1	2	localObject3	Object
    //   139	7	2	localException	Exception
    //   202	10	2	localObject4	Object
    //   214	1	2	localContext	android.content.Context
    //   61	106	3	localArrayList	java.util.ArrayList
    //   4	109	4	localPackageManager	android.content.pm.PackageManager
    //   76	93	5	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   32	39	41	java/lang/Exception
    //   102	107	139	java/lang/Exception
    //   111	136	139	java/lang/Exception
    //   168	174	139	java/lang/Exception
    //   68	78	188	finally
    //   80	102	188	finally
    //   142	155	188	finally
    //   102	107	202	finally
    //   111	136	202	finally
    //   168	174	202	finally
    //   68	78	210	java/lang/Exception
    //   80	102	210	java/lang/Exception
  }
}
