package com.netspark.android.apps;

final class am
  extends Thread
{
  am() {}
  
  /* Error */
  @android.annotation.SuppressLint({"InlinedApi"})
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 21	com/netspark/android/netsvpn/dx:j	()Landroid/content/pm/PackageManager;
    //   5: iconst_0
    //   6: invokestatic 27	com/netspark/android/apps/al:a	(I)I
    //   9: invokevirtual 33	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   12: invokeinterface 39 1 0
    //   17: astore_3
    //   18: ldc2_w 40
    //   21: lstore_1
    //   22: aload_3
    //   23: invokeinterface 47 1 0
    //   28: ifeq +73 -> 101
    //   31: aload_3
    //   32: invokeinterface 51 1 0
    //   37: checkcast 53	android/content/pm/PackageInfo
    //   40: astore 4
    //   42: aload 4
    //   44: getfield 57	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   47: getfield 63	android/content/pm/ApplicationInfo:flags	I
    //   50: iconst_1
    //   51: invokestatic 68	com/netspark/android/apps/ak:a	(IZ)Z
    //   54: ifeq +6 -> 60
    //   57: goto -35 -> 22
    //   60: aload 4
    //   62: getfield 72	android/content/pm/PackageInfo:firstInstallTime	J
    //   65: lload_1
    //   66: lcmp
    //   67: ifge -45 -> 22
    //   70: aload 4
    //   72: getfield 72	android/content/pm/PackageInfo:firstInstallTime	J
    //   75: lstore_1
    //   76: goto -54 -> 22
    //   79: lload_1
    //   80: invokestatic 78	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   83: invokestatic 81	com/netspark/android/apps/al:a	(Ljava/lang/Long;)V
    //   86: goto +8 -> 94
    //   89: astore_3
    //   90: aload_0
    //   91: monitorexit
    //   92: aload_3
    //   93: athrow
    //   94: aload_0
    //   95: monitorexit
    //   96: return
    //   97: astore_3
    //   98: goto -4 -> 94
    //   101: lload_1
    //   102: ldc2_w 40
    //   105: lcmp
    //   106: ifne +6 -> 112
    //   109: goto -30 -> 79
    //   112: lload_1
    //   113: ldc2_w 82
    //   116: ladd
    //   117: lstore_1
    //   118: goto -39 -> 79
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	121	0	this	am
    //   21	97	1	l	long
    //   17	15	3	localIterator	java.util.Iterator
    //   89	4	3	localObject	Object
    //   97	1	3	localThrowable	Throwable
    //   40	31	4	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   2	18	89	finally
    //   22	57	89	finally
    //   60	76	89	finally
    //   79	86	89	finally
    //   2	18	97	java/lang/Throwable
    //   22	57	97	java/lang/Throwable
    //   60	76	97	java/lang/Throwable
    //   79	86	97	java/lang/Throwable
  }
}
