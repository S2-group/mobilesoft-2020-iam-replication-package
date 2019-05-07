package com.S.c.m;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.S.c.n;
import com.S.c.n.c;
import com.android.absbase.receiver.InstalledReceiver;
import com.android.absbase.receiver.InstalledReceiver.c;
import com.android.absbase.receiver.InstalledReceiver.n;
import com.android.absbase.service.JobAssignmentService;
import com.android.absbase.service.JobAssignmentService.c;
import com.android.absbase.service.JobAssignmentService.n;
import com.android.absbase.utils.c.F;
import com.android.absbase.utils.fO;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.jvm.internal.zA;

public final class c
{
  private static long F;
  private static long S;
  public static final c c = new c();
  private static String m;
  private static final TreeMap<String, c> n = new TreeMap();
  
  private c() {}
  
  private final long F()
  {
    try
    {
      long l = com.android.absbase.c.c().getPackageManager().getPackageInfo(com.android.absbase.c.n(), 0).firstInstallTime;
      return l;
    }
    catch (Exception localException) {}
    return n.c.c().n();
  }
  
  private final void S()
  {
    if (F == 0L)
    {
      m = com.android.absbase.c.n();
      F = F();
    }
    Iterator localIterator = n.values().iterator();
    while (localIterator.hasNext())
    {
      c localC = (c)localIterator.next();
      if (F > localC.n())
      {
        F = localC.n();
        m = localC.c();
      }
    }
  }
  
  /* Error */
  private final c c(ApplicationInfo paramApplicationInfo)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_1
    //   7: getfield 119	android/content/pm/ApplicationInfo:flags	I
    //   10: iconst_1
    //   11: iand
    //   12: ifle +5 -> 17
    //   15: aconst_null
    //   16: areturn
    //   17: aload_1
    //   18: getfield 122	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   21: astore_1
    //   22: aload_1
    //   23: checkcast 124	java/lang/CharSequence
    //   26: invokestatic 130	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifeq +5 -> 34
    //   32: aconst_null
    //   33: areturn
    //   34: invokestatic 44	com/android/absbase/c:c	()Landroid/content/Context;
    //   37: invokevirtual 50	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +137 -> 181
    //   47: iconst_1
    //   48: anewarray 132	java/lang/String
    //   51: astore 5
    //   53: aload 5
    //   55: iconst_0
    //   56: ldc -122
    //   58: invokevirtual 139	java/lang/Class:getName	()Ljava/lang/String;
    //   61: aastore
    //   62: aload 5
    //   64: arraylength
    //   65: istore_3
    //   66: iconst_0
    //   67: istore_2
    //   68: iload_2
    //   69: iload_3
    //   70: if_icmpge +135 -> 205
    //   73: new 141	android/content/ComponentName
    //   76: dup
    //   77: aload_1
    //   78: aload 5
    //   80: iload_2
    //   81: aaload
    //   82: invokespecial 144	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   85: astore 6
    //   87: aload 4
    //   89: aload 6
    //   91: iconst_0
    //   92: invokevirtual 148	android/content/pm/PackageManager:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   95: astore 6
    //   97: aload 6
    //   99: ifnull +86 -> 185
    //   102: iconst_1
    //   103: istore_2
    //   104: iload_2
    //   105: ifeq +74 -> 179
    //   108: new 8	com/S/c/m/c$c
    //   111: dup
    //   112: invokespecial 149	com/S/c/m/c$c:<init>	()V
    //   115: astore 5
    //   117: aload 4
    //   119: aload_1
    //   120: iconst_0
    //   121: invokevirtual 59	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   124: astore 4
    //   126: aload_1
    //   127: ldc -106
    //   129: invokestatic 155	kotlin/jvm/internal/zA:c	(Ljava/lang/Object;Ljava/lang/String;)V
    //   132: aload 5
    //   134: aload_1
    //   135: invokevirtual 158	com/S/c/m/c$c:c	(Ljava/lang/String;)V
    //   138: aload 5
    //   140: ldc -96
    //   142: invokevirtual 162	com/S/c/m/c$c:n	(Ljava/lang/String;)V
    //   145: aload 5
    //   147: aload 4
    //   149: getfield 64	android/content/pm/PackageInfo:firstInstallTime	J
    //   152: invokevirtual 165	com/S/c/m/c$c:c	(J)V
    //   155: aload 5
    //   157: aload 4
    //   159: getfield 168	android/content/pm/PackageInfo:lastUpdateTime	J
    //   162: invokevirtual 170	com/S/c/m/c$c:n	(J)V
    //   165: aload 5
    //   167: aload 4
    //   169: getfield 173	android/content/pm/PackageInfo:versionCode	I
    //   172: invokevirtual 176	com/S/c/m/c$c:c	(I)V
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
    //   0	210	0	this	c
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
  
  private final void c(c paramC)
  {
    if (paramC == null) {
      return;
    }
    for (;;)
    {
      try
      {
        if (n.containsKey(paramC.c()))
        {
          c localC = (c)n.get(paramC.c());
          if (localC != null) {
            localC.c(paramC);
          }
          S();
          break;
        }
      }
      finally {}
      ((Map)n).put(paramC.c(), paramC);
    }
  }
  
  private final void g()
  {
    F.c((Runnable)F.c);
  }
  
  /* Error */
  private final void n(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: checkcast 124	java/lang/CharSequence
    //   6: invokestatic 130	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: istore_2
    //   10: iload_2
    //   11: ifeq +6 -> 17
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: getstatic 34	com/S/c/m/c:n	Ljava/util/TreeMap;
    //   20: aload_1
    //   21: invokevirtual 181	java/util/TreeMap:containsKey	(Ljava/lang/Object;)Z
    //   24: ifeq -10 -> 14
    //   27: getstatic 34	com/S/c/m/c:n	Ljava/util/TreeMap;
    //   30: aload_1
    //   31: invokevirtual 214	java/util/TreeMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   34: pop
    //   35: lconst_0
    //   36: putstatic 78	com/S/c/m/c:F	J
    //   39: aconst_null
    //   40: checkcast 132	java/lang/String
    //   43: putstatic 80	com/S/c/m/c:m	Ljava/lang/String;
    //   46: aload_0
    //   47: invokespecial 190	com/S/c/m/c:S	()V
    //   50: goto -36 -> 14
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	c
    //   0	58	1	paramString	String
    //   9	2	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	10	53	finally
    //   17	50	53	finally
  }
  
  public final void c()
  {
    Object localObject = com.android.absbase.c.c().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledApplications(128);
      zA.c(localObject, "packageManager.getInstal…ageManager.GET_META_DATA)");
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        c(c((ApplicationInfo)((Iterator)localObject).next()));
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public final void c(String paramString, boolean paramBoolean)
  {
    zA.n(paramString, "packageName");
    if (TextUtils.isEmpty((CharSequence)paramString)) {}
    PackageManager localPackageManager;
    do
    {
      return;
      if (paramBoolean)
      {
        n(paramString);
        return;
      }
      localPackageManager = com.android.absbase.c.c().getPackageManager();
    } while (localPackageManager == null);
    try
    {
      c(c(localPackageManager.getApplicationInfo(paramString, 128)));
      return;
    }
    catch (PackageManager.NameNotFoundException paramString) {}catch (Exception paramString) {}catch (RuntimeException paramString) {}
  }
  
  public final boolean c(String paramString)
  {
    zA.n(paramString, "packagename");
    return n.containsKey(paramString);
  }
  
  public final void m()
  {
    JobAssignmentService.c.c(com.android.absbase.c.c());
    JobAssignmentService.c.c((JobAssignmentService.n)new n());
    if (!fO.c(System.currentTimeMillis(), S)) {
      g();
    }
    InstalledReceiver.c.c((InstalledReceiver.n)new m());
  }
  
  public final void n() {}
  
  static final class F
    implements Runnable
  {
    public static final F c = new F();
    
    F() {}
    
    public final void run()
    {
      c.c.c();
      c.c.n();
      c.c(c.c, System.currentTimeMillis());
    }
  }
  
  public static final class c
    implements Comparator<c>
  {
    private long F;
    private long S;
    private String c = "";
    private int m;
    private String n = "";
    
    public c() {}
    
    public int c(c paramC1, c paramC2)
    {
      zA.n(paramC1, "o1");
      zA.n(paramC2, "o2");
      return paramC1.F < paramC2.F;
    }
    
    public final String c()
    {
      return this.c;
    }
    
    public final void c(int paramInt)
    {
      this.m = paramInt;
    }
    
    public final void c(long paramLong)
    {
      this.F = paramLong;
    }
    
    public final void c(String paramString)
    {
      zA.n(paramString, "<set-?>");
      this.c = paramString;
    }
    
    public final boolean c(c paramC)
    {
      if ((paramC == null) || (!TextUtils.equals((CharSequence)this.c, (CharSequence)paramC.c))) {
        return false;
      }
      this.n = paramC.n;
      this.m = paramC.m;
      this.F = paramC.F;
      this.S = paramC.S;
      return true;
    }
    
    public final long n()
    {
      return this.F;
    }
    
    public final void n(long paramLong)
    {
      this.S = paramLong;
    }
    
    public final void n(String paramString)
    {
      zA.n(paramString, "<set-?>");
      this.n = paramString;
    }
  }
  
  public static final class m
    implements InstalledReceiver.n
  {
    m() {}
    
    public void c(String paramString)
    {
      zA.n(paramString, "packageName");
      c.c.c(paramString, false);
    }
    
    public void n(String paramString)
    {
      zA.n(paramString, "packageName");
      c.c.c(paramString, true);
    }
  }
  
  public static final class n
    implements JobAssignmentService.n
  {
    n() {}
    
    public long c()
    {
      return 86400000L;
    }
    
    public boolean m()
    {
      return !fO.c(System.currentTimeMillis(), c.c(c.c));
    }
    
    public void n()
    {
      c.n(c.c);
    }
  }
}
