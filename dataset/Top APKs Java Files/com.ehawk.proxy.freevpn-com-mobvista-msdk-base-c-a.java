package com.mobvista.msdk.base.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.text.TextUtils;
import com.mobvista.msdk.base.entity.c;
import com.mobvista.msdk.base.utils.e;
import com.mobvista.msdk.base.utils.i;
import com.mobvista.msdk.base.utils.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class a
{
  public static final String a = a.class.getSimpleName();
  public static List<String> b = new ArrayList();
  private static a c;
  private static CopyOnWriteArraySet<c> i = new CopyOnWriteArraySet();
  private Context d;
  private String e;
  private String f;
  private String g;
  private boolean h = false;
  private String j;
  private Location k;
  
  private a() {}
  
  public static Set<c> b()
  {
    return i;
  }
  
  public static a c()
  {
    if (c == null) {}
    try
    {
      if (c == null) {
        c = new a();
      }
      return c;
    }
    finally {}
  }
  
  public static List<Long> g()
  {
    ArrayList localArrayList;
    try
    {
      Iterator localIterator;
      if ((i != null) && (i.size() > 0))
      {
        localIterator = i.iterator();
        localArrayList = new ArrayList();
      }
      while (localIterator.hasNext())
      {
        c localC = (c)localIterator.next();
        boolean bool = localArrayList.contains(localC.a());
        if (!bool)
        {
          try
          {
            localArrayList.add(Long.valueOf(Long.parseLong(localC.a())));
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
          }
          continue;
          return null;
        }
      }
    }
    catch (Throwable localThrowable) {}
    return localArrayList;
  }
  
  public final String a()
  {
    try
    {
      if (!TextUtils.isEmpty(this.j)) {
        return this.j;
      }
      String str1;
      if (this.d != null)
      {
        String str3 = (String)m.b(this.d, "applicationIds", "");
        str1 = str3;
        if (!TextUtils.isEmpty(str3)) {}
      }
      else if (this.d != null)
      {
        str1 = this.d.getPackageName();
        return str1;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      String str2 = null;
      return str2;
    }
  }
  
  public final void a(Context paramContext)
  {
    this.d = paramContext;
  }
  
  public final void a(String paramString)
  {
    try
    {
      this.j = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        m.a(this.d, "applicationIds", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final void b(String paramString)
  {
    this.e = paramString;
  }
  
  public final void c(String paramString)
  {
    try
    {
      this.f = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        m.a(this.d, "sp_appId", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final void d()
  {
    if (this.h == true) {
      return;
    }
    new Thread(new a.1(this)).start();
    this.h = true;
  }
  
  public final void d(String paramString)
  {
    try
    {
      this.g = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        m.a(this.d, "sp_appKey", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  public final void e()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 53	com/mobvista/msdk/base/c/a:d	Landroid/content/Context;
    //   6: invokestatic 174	com/mobvista/msdk/base/utils/i:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/i;
    //   9: aload_0
    //   10: getfield 59	com/mobvista/msdk/base/c/a:f	Ljava/lang/String;
    //   13: invokevirtual 177	com/mobvista/msdk/base/utils/i:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: astore_3
    //   17: aload_3
    //   18: putstatic 46	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   21: aload_3
    //   22: ifnull +14 -> 36
    //   25: getstatic 46	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   28: invokevirtual 75	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   31: istore_1
    //   32: iload_1
    //   33: ifne +6 -> 39
    //   36: aload_0
    //   37: monitorexit
    //   38: return
    //   39: new 43	java/util/concurrent/CopyOnWriteArraySet
    //   42: dup
    //   43: invokespecial 44	java/util/concurrent/CopyOnWriteArraySet:<init>	()V
    //   46: astore_3
    //   47: getstatic 46	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   50: invokevirtual 79	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   53: astore 4
    //   55: aload 4
    //   57: invokeinterface 85 1 0
    //   62: istore_2
    //   63: iload_2
    //   64: ifeq +84 -> 148
    //   67: aload 4
    //   69: invokeinterface 89 1 0
    //   74: checkcast 91	com/mobvista/msdk/base/entity/c
    //   77: astore 5
    //   79: iconst_0
    //   80: istore_1
    //   81: iload_1
    //   82: getstatic 41	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   85: invokeinterface 178 1 0
    //   90: if_icmpge -35 -> 55
    //   93: getstatic 41	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   96: iload_1
    //   97: invokeinterface 182 2 0
    //   102: checkcast 137	java/lang/String
    //   105: aload 5
    //   107: invokevirtual 184	com/mobvista/msdk/base/entity/c:b	()Ljava/lang/String;
    //   110: invokevirtual 187	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   113: ifeq +10 -> 123
    //   116: aload_3
    //   117: aload 5
    //   119: invokevirtual 188	java/util/concurrent/CopyOnWriteArraySet:add	(Ljava/lang/Object;)Z
    //   122: pop
    //   123: iload_1
    //   124: iconst_1
    //   125: iadd
    //   126: istore_1
    //   127: goto -46 -> 81
    //   130: astore 5
    //   132: aload 5
    //   134: invokevirtual 143	java/lang/Exception:printStackTrace	()V
    //   137: getstatic 34	com/mobvista/msdk/base/c/a:a	Ljava/lang/String;
    //   140: ldc -66
    //   142: invokestatic 195	com/mobvista/msdk/base/utils/e:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   145: goto -90 -> 55
    //   148: getstatic 46	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   151: ifnull +9 -> 160
    //   154: getstatic 46	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   157: invokevirtual 198	java/util/concurrent/CopyOnWriteArraySet:clear	()V
    //   160: aload_3
    //   161: invokevirtual 75	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   164: ifle +11 -> 175
    //   167: getstatic 46	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   170: aload_3
    //   171: invokevirtual 202	java/util/concurrent/CopyOnWriteArraySet:addAll	(Ljava/util/Collection;)Z
    //   174: pop
    //   175: aload_0
    //   176: getfield 53	com/mobvista/msdk/base/c/a:d	Landroid/content/Context;
    //   179: invokestatic 174	com/mobvista/msdk/base/utils/i:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/i;
    //   182: getstatic 46	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   185: invokevirtual 205	com/mobvista/msdk/base/utils/i:a	(Ljava/util/Set;)V
    //   188: goto -152 -> 36
    //   191: astore_3
    //   192: aload_0
    //   193: monitorexit
    //   194: aload_3
    //   195: athrow
    //   196: astore_3
    //   197: goto -161 -> 36
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	200	0	this	a
    //   31	96	1	m	int
    //   62	2	2	bool	boolean
    //   16	155	3	localCopyOnWriteArraySet	CopyOnWriteArraySet
    //   191	4	3	localObject	Object
    //   196	1	3	localThrowable	Throwable
    //   53	15	4	localIterator	Iterator
    //   77	41	5	localC	c
    //   130	3	5	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   67	79	130	java/lang/Exception
    //   81	123	130	java/lang/Exception
    //   2	21	191	finally
    //   25	32	191	finally
    //   39	55	191	finally
    //   55	63	191	finally
    //   67	79	191	finally
    //   81	123	191	finally
    //   132	145	191	finally
    //   148	160	191	finally
    //   160	175	191	finally
    //   175	188	191	finally
    //   2	21	196	java/lang/Throwable
    //   25	32	196	java/lang/Throwable
    //   39	55	196	java/lang/Throwable
    //   55	63	196	java/lang/Throwable
    //   67	79	196	java/lang/Throwable
    //   81	123	196	java/lang/Throwable
    //   132	145	196	java/lang/Throwable
    //   148	160	196	java/lang/Throwable
    //   160	175	196	java/lang/Throwable
    //   175	188	196	java/lang/Throwable
  }
  
  public final void f()
  {
    try
    {
      if ((i != null) && (i.size() > 0)) {
        i.a(this.d).a(i);
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public final Location h()
  {
    return this.k;
  }
  
  public final Context i()
  {
    return this.d;
  }
  
  public final String j()
  {
    return this.e;
  }
  
  public final String k()
  {
    try
    {
      if (!TextUtils.isEmpty(this.f)) {
        return this.f;
      }
      if (this.d != null)
      {
        String str = (String)m.b(this.d, "sp_appId", "");
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public final String l()
  {
    if (!TextUtils.isEmpty(this.g)) {
      return this.g;
    }
    if (this.d != null) {
      return (String)m.b(this.d, "sp_appKey", "");
    }
    return null;
  }
  
  public final List<String> m()
  {
    try
    {
      if (b != null) {
        return b;
      }
      List localList = this.d.getPackageManager().getInstalledPackages(0);
      int m = 0;
      while (m < localList.size())
      {
        b.add(((PackageInfo)localList.get(m)).packageName);
        m += 1;
      }
      localList = b;
      return localList;
    }
    catch (Exception localException)
    {
      e.d(a, "get package info list error");
    }
    return null;
  }
}
