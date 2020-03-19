package com.xiaomi.mipush.sdk;

import android.content.Context;

final class n
  implements Runnable
{
  n(Context paramContext) {}
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	com/xiaomi/mipush/sdk/n:a	Landroid/content/Context;
    //   4: invokestatic 26	com/xiaomi/mipush/sdk/c:a	(Landroid/content/Context;)Z
    //   7: ifne +209 -> 216
    //   10: iconst_1
    //   11: aload_0
    //   12: getfield 12	com/xiaomi/mipush/sdk/n:a	Landroid/content/Context;
    //   15: invokestatic 31	com/xiaomi/mipush/sdk/i:a	(Landroid/content/Context;)Lcom/xiaomi/mipush/sdk/i;
    //   18: invokevirtual 35	com/xiaomi/mipush/sdk/i:m	()I
    //   21: if_icmpne +195 -> 216
    //   24: aload_0
    //   25: getfield 12	com/xiaomi/mipush/sdk/n:a	Landroid/content/Context;
    //   28: invokevirtual 41	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   31: iconst_4
    //   32: invokevirtual 47	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   35: astore 4
    //   37: aload 4
    //   39: ifnull +177 -> 216
    //   42: aload 4
    //   44: invokeinterface 53 1 0
    //   49: astore 4
    //   51: aload 4
    //   53: invokeinterface 59 1 0
    //   58: ifeq +158 -> 216
    //   61: aload 4
    //   63: invokeinterface 63 1 0
    //   68: checkcast 65	android/content/pm/PackageInfo
    //   71: getfield 69	android/content/pm/PackageInfo:services	[Landroid/content/pm/ServiceInfo;
    //   74: astore 5
    //   76: aload 5
    //   78: ifnull -27 -> 51
    //   81: aload 5
    //   83: arraylength
    //   84: istore_2
    //   85: iconst_0
    //   86: istore_1
    //   87: iload_1
    //   88: iload_2
    //   89: if_icmpge -38 -> 51
    //   92: aload 5
    //   94: iload_1
    //   95: aaload
    //   96: astore 6
    //   98: aload 6
    //   100: getfield 75	android/content/pm/ServiceInfo:exported	Z
    //   103: ifeq +104 -> 207
    //   106: aload 6
    //   108: getfield 78	android/content/pm/ServiceInfo:enabled	Z
    //   111: ifeq +96 -> 207
    //   114: ldc 80
    //   116: aload 6
    //   118: getfield 84	android/content/pm/ServiceInfo:name	Ljava/lang/String;
    //   121: invokevirtual 90	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   124: ifeq +83 -> 207
    //   127: aload_0
    //   128: getfield 12	com/xiaomi/mipush/sdk/n:a	Landroid/content/Context;
    //   131: invokevirtual 94	android/content/Context:getPackageName	()Ljava/lang/String;
    //   134: aload 6
    //   136: getfield 97	android/content/pm/ServiceInfo:packageName	Ljava/lang/String;
    //   139: invokevirtual 90	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   142: istore_3
    //   143: iload_3
    //   144: ifne +63 -> 207
    //   147: invokestatic 103	java/lang/Math:random	()D
    //   150: ldc2_w 104
    //   153: dmul
    //   154: dconst_1
    //   155: dadd
    //   156: d2l
    //   157: ldc2_w 106
    //   160: lmul
    //   161: invokestatic 113	java/lang/Thread:sleep	(J)V
    //   164: new 115	android/content/Intent
    //   167: dup
    //   168: invokespecial 116	android/content/Intent:<init>	()V
    //   171: astore 7
    //   173: aload 7
    //   175: aload 6
    //   177: getfield 97	android/content/pm/ServiceInfo:packageName	Ljava/lang/String;
    //   180: aload 6
    //   182: getfield 84	android/content/pm/ServiceInfo:name	Ljava/lang/String;
    //   185: invokevirtual 120	android/content/Intent:setClassName	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   188: pop
    //   189: aload 7
    //   191: ldc 122
    //   193: invokevirtual 126	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   196: pop
    //   197: aload_0
    //   198: getfield 12	com/xiaomi/mipush/sdk/n:a	Landroid/content/Context;
    //   201: aload 7
    //   203: invokevirtual 130	android/content/Context:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   206: pop
    //   207: iload_1
    //   208: iconst_1
    //   209: iadd
    //   210: istore_1
    //   211: goto -124 -> 87
    //   214: astore 4
    //   216: return
    //   217: astore 7
    //   219: goto -55 -> 164
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	222	0	this	n
    //   86	125	1	i	int
    //   84	6	2	j	int
    //   142	2	3	bool	boolean
    //   35	27	4	localObject	Object
    //   214	1	4	localThrowable	Throwable
    //   74	19	5	arrayOfServiceInfo	android.content.pm.ServiceInfo[]
    //   96	85	6	localServiceInfo	android.content.pm.ServiceInfo
    //   171	31	7	localIntent	android.content.Intent
    //   217	1	7	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   24	37	214	java/lang/Throwable
    //   42	51	214	java/lang/Throwable
    //   51	76	214	java/lang/Throwable
    //   81	85	214	java/lang/Throwable
    //   98	143	214	java/lang/Throwable
    //   147	164	214	java/lang/Throwable
    //   164	207	214	java/lang/Throwable
    //   147	164	217	java/lang/InterruptedException
  }
}
