import android.content.pm.PackageManager;
import java.util.Collections;
import java.util.HashSet;

public final class kvl
  implements kvu
{
  private final stq a;
  private final PackageManager b;
  private final kpq c;
  
  kvl(stq paramStq, PackageManager paramPackageManager, kpq paramKpq)
  {
    this.a = paramStq;
    this.b = paramPackageManager;
    this.c = paramKpq;
  }
  
  /* Error */
  private final void a(qre paramQre, String paramString, java.util.Set paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 19	kvl:a	Lstq;
    //   4: invokeinterface 32 1 0
    //   9: checkcast 34	kms
    //   12: astore 4
    //   14: aload 4
    //   16: invokeinterface 36 1 0
    //   21: aload_0
    //   22: getfield 21	kvl:b	Landroid/content/pm/PackageManager;
    //   25: sipush 4096
    //   28: invokevirtual 42	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   31: invokeinterface 48 1 0
    //   36: astore 5
    //   38: aload 5
    //   40: invokeinterface 54 1 0
    //   45: ifeq +118 -> 163
    //   48: aload 5
    //   50: invokeinterface 57 1 0
    //   55: checkcast 59	android/content/pm/PackageInfo
    //   58: astore 7
    //   60: aload_3
    //   61: aload 7
    //   63: getfield 63	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   66: invokeinterface 69 2 0
    //   71: ifne -33 -> 38
    //   74: aload 7
    //   76: getfield 63	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   79: astore 6
    //   81: aload_0
    //   82: getfield 23	kvl:c	Lkpq;
    //   85: aload 7
    //   87: invokevirtual 74	kpq:a	(Landroid/content/pm/PackageInfo;)Ljava/util/Map;
    //   90: aload_2
    //   91: invokeinterface 80 2 0
    //   96: checkcast 82	java/lang/Boolean
    //   99: astore 7
    //   101: aload 7
    //   103: ifnull -65 -> 38
    //   106: aload_1
    //   107: getstatic 87	qre:b	Lqre;
    //   110: if_acmpne +17 -> 127
    //   113: aload 4
    //   115: aload 6
    //   117: aload_2
    //   118: iconst_0
    //   119: invokeinterface 90 4 0
    //   124: goto -86 -> 38
    //   127: aload 7
    //   129: invokevirtual 93	java/lang/Boolean:booleanValue	()Z
    //   132: ifeq +17 -> 149
    //   135: aload 4
    //   137: aload 6
    //   139: aload_2
    //   140: iconst_1
    //   141: invokeinterface 90 4 0
    //   146: goto -108 -> 38
    //   149: aload 4
    //   151: aload 6
    //   153: aload_2
    //   154: iconst_2
    //   155: invokeinterface 90 4 0
    //   160: goto -122 -> 38
    //   163: aload 4
    //   165: invokeinterface 95 1 0
    //   170: return
    //   171: astore_1
    //   172: goto +44 -> 216
    //   175: astore_1
    //   176: getstatic 100	kph:a	Lkkd;
    //   179: ldc 102
    //   181: aload_1
    //   182: ldc 104
    //   184: iconst_1
    //   185: anewarray 4	java/lang/Object
    //   188: dup
    //   189: iconst_0
    //   190: aload_2
    //   191: aastore
    //   192: invokevirtual 109	kkd:b	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   195: new 111	ksv
    //   198: dup
    //   199: ldc 104
    //   201: iconst_1
    //   202: anewarray 4	java/lang/Object
    //   205: dup
    //   206: iconst_0
    //   207: aload_2
    //   208: aastore
    //   209: invokestatic 117	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   212: invokespecial 120	ksv:<init>	(Ljava/lang/String;)V
    //   215: athrow
    //   216: aload 4
    //   218: invokeinterface 95 1 0
    //   223: aload_1
    //   224: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	this	kvl
    //   0	225	1	paramQre	qre
    //   0	225	2	paramString	String
    //   0	225	3	paramSet	java.util.Set
    //   12	205	4	localKms	kms
    //   36	13	5	localIterator	java.util.Iterator
    //   79	73	6	str	String
    //   58	70	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   14	38	171	finally
    //   38	101	171	finally
    //   106	124	171	finally
    //   127	146	171	finally
    //   149	160	171	finally
    //   176	216	171	finally
    //   14	38	175	kmv
    //   38	101	175	kmv
    //   106	124	175	kmv
    //   127	146	175	kmv
    //   149	160	175	kmv
  }
  
  public final void a(rew paramRew)
  {
    paramRew = paramRew.b;
    if (paramRew == null) {
      paramRew = rex.d;
    }
    paramRew = paramRew.c;
    a(qre.b, paramRew, Collections.EMPTY_SET);
  }
  
  public final void a(rew paramRew, kqn paramKqn)
  {
    ren localRen = (ren)qms.a(ren.d.getParserForType(), paramRew);
    paramKqn = qre.a(localRen.b);
    if (paramKqn == null) {
      paramKqn = qre.a;
    }
    if ((paramKqn != qre.b) && (paramKqn != qre.c))
    {
      kph.a.b("PermissionGroupModePolicyProcessor", "Unknown control mode", new Object[0]);
      return;
    }
    paramRew = paramRew.b;
    if (paramRew == null) {
      paramRew = rex.d;
    }
    a(paramKqn, paramRew.c, Collections.unmodifiableSet(new HashSet(localRen.c)));
  }
}
