package com.Q.F.Q;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.Q.F.a;
import com.Q.F.a.F;
import com.android.absbase.receiver.InstalledReceiver;
import com.android.absbase.receiver.InstalledReceiver.F;
import com.android.absbase.receiver.InstalledReceiver.a;
import com.android.absbase.service.JobAssignmentService;
import com.android.absbase.service.JobAssignmentService.F;
import com.android.absbase.service.JobAssignmentService.a;
import com.android.absbase.utils.F.g;
import com.android.absbase.utils.fr;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.jvm.internal.C;

public final class F
{
  public static final F F = new F();
  private static String Q;
  private static long U;
  private static final TreeMap<String, F> a = new TreeMap();
  private static long g;
  
  private F() {}
  
  /* Error */
  private final F F(ApplicationInfo paramApplicationInfo)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_1
    //   7: getfield 52	android/content/pm/ApplicationInfo:flags	I
    //   10: iconst_1
    //   11: iand
    //   12: ifle +5 -> 17
    //   15: aconst_null
    //   16: areturn
    //   17: aload_1
    //   18: getfield 55	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   21: astore_1
    //   22: aload_1
    //   23: checkcast 57	java/lang/CharSequence
    //   26: invokestatic 63	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifeq +5 -> 34
    //   32: aconst_null
    //   33: areturn
    //   34: invokestatic 68	com/android/absbase/F:F	()Landroid/content/Context;
    //   37: invokevirtual 74	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +137 -> 181
    //   47: iconst_1
    //   48: anewarray 76	java/lang/String
    //   51: astore 5
    //   53: aload 5
    //   55: iconst_0
    //   56: ldc 78
    //   58: invokevirtual 84	java/lang/Class:getName	()Ljava/lang/String;
    //   61: aastore
    //   62: aload 5
    //   64: arraylength
    //   65: istore_3
    //   66: iconst_0
    //   67: istore_2
    //   68: iload_2
    //   69: iload_3
    //   70: if_icmpge +135 -> 205
    //   73: new 86	android/content/ComponentName
    //   76: dup
    //   77: aload_1
    //   78: aload 5
    //   80: iload_2
    //   81: aaload
    //   82: invokespecial 89	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   85: astore 6
    //   87: aload 4
    //   89: aload 6
    //   91: iconst_0
    //   92: invokevirtual 95	android/content/pm/PackageManager:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   95: astore 6
    //   97: aload 6
    //   99: ifnull +86 -> 185
    //   102: iconst_1
    //   103: istore_2
    //   104: iload_2
    //   105: ifeq +74 -> 179
    //   108: new 6	com/Q/F/Q/F$F
    //   111: dup
    //   112: invokespecial 96	com/Q/F/Q/F$F:<init>	()V
    //   115: astore 5
    //   117: aload 4
    //   119: aload_1
    //   120: iconst_0
    //   121: invokevirtual 100	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   124: astore 4
    //   126: aload_1
    //   127: ldc 101
    //   129: invokestatic 106	kotlin/jvm/internal/C:F	(Ljava/lang/Object;Ljava/lang/String;)V
    //   132: aload 5
    //   134: aload_1
    //   135: invokevirtual 109	com/Q/F/Q/F$F:F	(Ljava/lang/String;)V
    //   138: aload 5
    //   140: ldc 111
    //   142: invokevirtual 113	com/Q/F/Q/F$F:a	(Ljava/lang/String;)V
    //   145: aload 5
    //   147: aload 4
    //   149: getfield 118	android/content/pm/PackageInfo:firstInstallTime	J
    //   152: invokevirtual 121	com/Q/F/Q/F$F:F	(J)V
    //   155: aload 5
    //   157: aload 4
    //   159: getfield 124	android/content/pm/PackageInfo:lastUpdateTime	J
    //   162: invokevirtual 126	com/Q/F/Q/F$F:a	(J)V
    //   165: aload 5
    //   167: aload 4
    //   169: getfield 129	android/content/pm/PackageInfo:versionCode	I
    //   172: invokevirtual 132	com/Q/F/Q/F$F:F	(I)V
    //   175: aload 5
    //   177: areturn
    //   178: astore_1
    //   179: aconst_null
    //   180: areturn
    //   181: aconst_null
    //   182: areturn
    //   183: astore 6
    //   185: iload_2
    //   186: iconst_1
    //   187: iadd
    //   188: istore_2
    //   189: goto -121 -> 68
    //   192: astore 6
    //   194: goto -9 -> 185
    //   197: astore_1
    //   198: goto -19 -> 179
    //   201: astore_1
    //   202: goto -23 -> 179
    //   205: iconst_0
    //   206: istore_2
    //   207: goto -103 -> 104
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	210	0	this	F
    //   0	210	1	paramApplicationInfo	ApplicationInfo
    //   67	140	2	i	int
    //   65	6	3	j	int
    //   40	128	4	localObject1	Object
    //   51	125	5	localObject2	Object
    //   85	13	6	localObject3	Object
    //   183	1	6	localNameNotFoundException	PackageManager.NameNotFoundException
    //   192	1	6	localRuntimeException	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   108	175	178	android/content/pm/PackageManager$NameNotFoundException
    //   87	97	183	android/content/pm/PackageManager$NameNotFoundException
    //   87	97	192	java/lang/RuntimeException
    //   108	175	197	java/lang/Exception
    //   108	175	201	java/lang/RuntimeException
  }
  
  private final void F(F paramF)
  {
    if (paramF == null) {
      return;
    }
    for (;;)
    {
      try
      {
        if (a.containsKey(paramF.F()))
        {
          F localF = (F)a.get(paramF.F());
          if (localF != null) {
            localF.F(paramF);
          }
          U();
          break;
        }
      }
      finally {}
      ((Map)a).put(paramF.F(), paramF);
    }
  }
  
  private final void U()
  {
    if (g == 0L)
    {
      Q = com.android.absbase.F.a();
      g = g();
    }
    Iterator localIterator = a.values().iterator();
    while (localIterator.hasNext())
    {
      F localF = (F)localIterator.next();
      if (g > localF.a())
      {
        g = localF.a();
        Q = localF.F();
      }
    }
  }
  
  /* Error */
  private final void a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: checkcast 57	java/lang/CharSequence
    //   6: invokestatic 63	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: istore_2
    //   10: iload_2
    //   11: ifeq +6 -> 17
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: getstatic 34	com/Q/F/Q/F:a	Ljava/util/TreeMap;
    //   20: aload_1
    //   21: invokevirtual 139	java/util/TreeMap:containsKey	(Ljava/lang/Object;)Z
    //   24: ifeq -10 -> 14
    //   27: getstatic 34	com/Q/F/Q/F:a	Ljava/util/TreeMap;
    //   30: aload_1
    //   31: invokevirtual 193	java/util/TreeMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   34: pop
    //   35: lconst_0
    //   36: putstatic 157	com/Q/F/Q/F:g	J
    //   39: aconst_null
    //   40: checkcast 76	java/lang/String
    //   43: putstatic 161	com/Q/F/Q/F:Q	Ljava/lang/String;
    //   46: aload_0
    //   47: invokespecial 148	com/Q/F/Q/F:U	()V
    //   50: goto -36 -> 14
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	F
    //   0	58	1	paramString	String
    //   9	2	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	10	53	finally
    //   17	50	53	finally
  }
  
  private final long g()
  {
    try
    {
      long l = com.android.absbase.F.F().getPackageManager().getPackageInfo(com.android.absbase.F.a(), 0).firstInstallTime;
      return l;
    }
    catch (Exception localException) {}
    return a.F.F().a();
  }
  
  private final void l()
  {
    g.F((Runnable)g.F);
  }
  
  public final void F()
  {
    Object localObject = com.android.absbase.F.F().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledApplications(128);
      C.F(localObject, "packageManager.getInstal…ageManager.GET_META_DATA)");
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        F(F((ApplicationInfo)((Iterator)localObject).next()));
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public final void F(String paramString, boolean paramBoolean)
  {
    C.a(paramString, "packageName");
    if (TextUtils.isEmpty((CharSequence)paramString)) {}
    PackageManager localPackageManager;
    do
    {
      return;
      if (paramBoolean)
      {
        a(paramString);
        return;
      }
      localPackageManager = com.android.absbase.F.F().getPackageManager();
    } while (localPackageManager == null);
    try
    {
      F(F(localPackageManager.getApplicationInfo(paramString, 128)));
      return;
    }
    catch (PackageManager.NameNotFoundException paramString) {}catch (Exception paramString) {}catch (RuntimeException paramString) {}
  }
  
  public final boolean F(String paramString)
  {
    C.a(paramString, "packagename");
    return a.containsKey(paramString);
  }
  
  public final void Q()
  {
    JobAssignmentService.F.F(com.android.absbase.F.F());
    JobAssignmentService.F.F((JobAssignmentService.a)new a());
    if (!fr.F(System.currentTimeMillis(), U)) {
      l();
    }
    InstalledReceiver.F.F((InstalledReceiver.a)new Q());
  }
  
  public final void a() {}
  
  public static final class F
    implements Comparator<F>
  {
    private String F = "";
    private int Q;
    private long U;
    private String a = "";
    private long g;
    
    public F() {}
    
    public int F(F paramF1, F paramF2)
    {
      C.a(paramF1, "o1");
      C.a(paramF2, "o2");
      return paramF1.g < paramF2.g;
    }
    
    public final String F()
    {
      return this.F;
    }
    
    public final void F(int paramInt)
    {
      this.Q = paramInt;
    }
    
    public final void F(long paramLong)
    {
      this.g = paramLong;
    }
    
    public final void F(String paramString)
    {
      C.a(paramString, "<set-?>");
      this.F = paramString;
    }
    
    public final boolean F(F paramF)
    {
      if ((paramF == null) || (!TextUtils.equals((CharSequence)this.F, (CharSequence)paramF.F))) {
        return false;
      }
      this.a = paramF.a;
      this.Q = paramF.Q;
      this.g = paramF.g;
      this.U = paramF.U;
      return true;
    }
    
    public final long a()
    {
      return this.g;
    }
    
    public final void a(long paramLong)
    {
      this.U = paramLong;
    }
    
    public final void a(String paramString)
    {
      C.a(paramString, "<set-?>");
      this.a = paramString;
    }
  }
  
  public static final class Q
    implements InstalledReceiver.a
  {
    Q() {}
    
    public void F(String paramString)
    {
      C.a(paramString, "packageName");
      F.F.F(paramString, false);
    }
    
    public void a(String paramString)
    {
      C.a(paramString, "packageName");
      F.F.F(paramString, true);
    }
  }
  
  public static final class a
    implements JobAssignmentService.a
  {
    a() {}
    
    public long F()
    {
      return 86400000L;
    }
    
    public boolean Q()
    {
      return !fr.F(System.currentTimeMillis(), F.F(F.F));
    }
    
    public void a()
    {
      F.a(F.F);
    }
  }
  
  static final class g
    implements Runnable
  {
    public static final g F = new g();
    
    g() {}
    
    public final void run()
    {
      F.F.F();
      F.F.a();
      F.F(F.F, System.currentTimeMillis());
    }
  }
}
