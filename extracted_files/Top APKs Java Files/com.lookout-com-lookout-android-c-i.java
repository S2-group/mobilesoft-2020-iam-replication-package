package com.lookout.android.c;

import android.content.Context;
import android.content.pm.PackageManager;
import org.a.b;
import org.a.c;

public abstract class i
  extends e
{
  private static final b b = c.a(i.class);
  protected final PackageManager a;
  
  public i(Context paramContext)
  {
    this.a = paramContext.getPackageManager();
  }
  
  /* Error */
  protected java.util.Collection a()
  {
    // Byte code:
    //   0: new 37	java/util/LinkedList
    //   3: dup
    //   4: invokespecial 38	java/util/LinkedList:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: getfield 30	com/lookout/android/c/i:a	Landroid/content/pm/PackageManager;
    //   12: sipush 4096
    //   15: invokevirtual 44	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   18: astore_2
    //   19: aload_2
    //   20: invokeinterface 50 1 0
    //   25: ifeq +15 -> 40
    //   28: getstatic 17	com/lookout/android/c/i:b	Lorg/a/b;
    //   31: ldc 52
    //   33: invokeinterface 58 2 0
    //   38: aload_1
    //   39: areturn
    //   40: aload_2
    //   41: invokeinterface 62 1 0
    //   46: astore_2
    //   47: aload_2
    //   48: invokeinterface 67 1 0
    //   53: ifeq +135 -> 188
    //   56: aload_2
    //   57: invokeinterface 71 1 0
    //   62: checkcast 73	android/content/pm/PackageInfo
    //   65: astore_3
    //   66: aload_3
    //   67: invokestatic 78	com/lookout/androidsecurity/i/e:a	()Lcom/lookout/androidsecurity/i/e;
    //   70: aload_3
    //   71: getfield 82	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   74: invokevirtual 86	com/lookout/androidsecurity/i/e:c	(Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   77: getfield 90	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   80: putfield 90	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   83: aload_3
    //   84: getfield 90	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   87: ifnonnull +33 -> 120
    //   90: getstatic 17	com/lookout/android/c/i:b	Lorg/a/b;
    //   93: new 92	java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   100: aload_3
    //   101: getfield 82	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   104: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: ldc 99
    //   109: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   115: invokeinterface 58 2 0
    //   120: aload_1
    //   121: invokestatic 78	com/lookout/androidsecurity/i/e:a	()Lcom/lookout/androidsecurity/i/e;
    //   124: aload_3
    //   125: invokevirtual 106	com/lookout/androidsecurity/i/e:a	(Landroid/content/pm/PackageInfo;)Lcom/lookout/androidsecurity/a/a/a;
    //   128: invokeinterface 110 2 0
    //   133: pop
    //   134: goto -87 -> 47
    //   137: astore_2
    //   138: getstatic 17	com/lookout/android/c/i:b	Lorg/a/b;
    //   141: ldc 112
    //   143: aload_2
    //   144: invokeinterface 116 3 0
    //   149: aload_1
    //   150: areturn
    //   151: astore 4
    //   153: getstatic 17	com/lookout/android/c/i:b	Lorg/a/b;
    //   156: new 92	java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   163: aload_3
    //   164: getfield 82	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   167: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: ldc 99
    //   172: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: aload 4
    //   180: invokeinterface 116 3 0
    //   185: goto -102 -> 83
    //   188: aload_1
    //   189: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	190	0	this	i
    //   7	182	1	localLinkedList	java.util.LinkedList
    //   18	39	2	localObject	Object
    //   137	7	2	localException	Exception
    //   65	99	3	localPackageInfo	android.content.pm.PackageInfo
    //   151	28	4	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   8	38	137	java/lang/Exception
    //   40	47	137	java/lang/Exception
    //   47	66	137	java/lang/Exception
    //   66	83	137	java/lang/Exception
    //   83	120	137	java/lang/Exception
    //   120	134	137	java/lang/Exception
    //   153	185	137	java/lang/Exception
    //   66	83	151	android/content/pm/PackageManager$NameNotFoundException
  }
}
