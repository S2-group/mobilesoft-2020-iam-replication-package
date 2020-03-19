package com.security.antivirus.scan.manager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.security.antivirus.scan.app.ApplicationEx;
import com.security.antivirus.scan.h.a;
import com.security.antivirus.scan.i.c.d;
import com.security.antivirus.scan.i.c.e;
import com.security.antivirus.scan.util.an;
import com.security.antivirus.scan.util.q;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.j;

public class k
{
  private static k a = null;
  private static ConcurrentHashMap<String, String> b = new ConcurrentHashMap();
  private ArrayList<PackageInfo> c = new ArrayList();
  private List<String> d = new ArrayList();
  private ArrayList<PackageInfo> e;
  private Object f = new Object();
  
  private k()
  {
    org.greenrobot.eventbus.c.a().a(this);
    if (q.a) {
      com.security.antivirus.scan.h.b.a("app_init", a.a(1));
    }
  }
  
  public static k a()
  {
    if (a == null) {}
    try
    {
      if (a == null) {
        a = new k();
      }
      return a;
    }
    finally {}
  }
  
  private void c(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (p.b("install_quit_scan_setting_enable", false))) {
      c.a().a(paramString);
    }
  }
  
  private void d() {}
  
  private List<String> e()
  {
    Set localSet = c(false);
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((str.equals(ApplicationEx.a().getPackageName())) || (com.security.antivirus.scan.util.b.a(ApplicationEx.a().getPackageManager(), str) == null)) {
        localIterator.remove();
      }
    }
    return Arrays.asList(localSet.toArray(new String[0]));
  }
  
  /* Error */
  public PackageInfo a(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: getstatic 22	com/security/antivirus/scan/manager/k:a	Lcom/security/antivirus/scan/manager/k;
    //   3: astore 6
    //   5: aload 6
    //   7: monitorenter
    //   8: aconst_null
    //   9: astore 4
    //   11: aload_0
    //   12: getfield 36	com/security/antivirus/scan/manager/k:c	Ljava/util/ArrayList;
    //   15: invokevirtual 151	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   18: astore 7
    //   20: aload 7
    //   22: invokeinterface 104 1 0
    //   27: ifeq +36 -> 63
    //   30: aload 7
    //   32: invokeinterface 108 1 0
    //   37: checkcast 153	android/content/pm/PackageInfo
    //   40: astore 5
    //   42: aload 5
    //   44: getfield 157	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   47: aload_1
    //   48: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   51: istore_3
    //   52: iload_3
    //   53: ifeq +39 -> 92
    //   56: aload 5
    //   58: astore 4
    //   60: goto -40 -> 20
    //   63: aload_0
    //   64: aload_1
    //   65: invokevirtual 160	com/security/antivirus/scan/manager/k:b	(Ljava/lang/String;)Z
    //   68: pop
    //   69: aload_0
    //   70: aload_2
    //   71: aload_1
    //   72: invokevirtual 163	com/security/antivirus/scan/manager/k:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   75: pop
    //   76: aload 6
    //   78: monitorexit
    //   79: aload 4
    //   81: areturn
    //   82: astore_1
    //   83: aload 6
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    //   88: astore_1
    //   89: goto -13 -> 76
    //   92: goto -32 -> 60
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	k
    //   0	95	1	paramString	String
    //   0	95	2	paramContext	Context
    //   51	2	3	bool	boolean
    //   9	71	4	localObject	Object
    //   40	17	5	localPackageInfo	PackageInfo
    //   3	81	6	localK	k
    //   18	13	7	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   11	20	82	finally
    //   20	52	82	finally
    //   63	76	82	finally
    //   76	79	82	finally
    //   83	86	82	finally
    //   63	76	88	java/lang/Exception
  }
  
  public PackageManager a(Context paramContext)
  {
    return paramContext.getPackageManager();
  }
  
  public String a(String paramString)
  {
    if (b.size() == 0) {
      b();
    }
    if (an.b((String)b.get(paramString))) {
      try
      {
        Object localObject = a(ApplicationEx.a());
        localObject = ((PackageManager)localObject).getPackageInfo(paramString, 0).applicationInfo.loadLabel((PackageManager)localObject).toString();
        if (!an.b((String)localObject))
        {
          b.put(paramString, localObject);
          return localObject;
        }
      }
      catch (Exception localException)
      {
        com.security.antivirus.scan.h.b.a(localException);
      }
    }
    return (String)b.get(paramString);
  }
  
  public List<PackageInfo> a(boolean paramBoolean)
  {
    b(paramBoolean);
    d();
    return (List)this.c.clone();
  }
  
  public boolean a(Context paramContext, String paramString)
  {
    try
    {
      synchronized (a)
      {
        paramContext = a(paramContext).getPackageInfo(paramString, 0);
        if (paramContext == null) {
          return false;
        }
        this.c.add(paramContext);
        d();
        return true;
      }
      return false;
    }
    catch (Exception paramContext) {}
  }
  
  public List<PackageInfo> b(boolean paramBoolean)
  {
    int i = 1;
    Object localObject1 = a(ApplicationEx.a());
    synchronized (a)
    {
      int j = this.c.size();
      if (j <= 0) {}
      try
      {
        this.c.clear();
        ArrayList localArrayList = this.c;
        if (!paramBoolean) {
          break label78;
        }
        localArrayList.addAll(((PackageManager)localObject1).getInstalledPackages(i));
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          try
          {
            label78:
            this.c.clear();
            this.c.addAll(((PackageManager)localObject1).getInstalledPackages(1));
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
          }
        }
      }
      localObject1 = (List)this.c.clone();
      return localObject1;
      i = 0;
    }
  }
  
  /* Error */
  public java.util.Map<String, String> b()
  {
    // Byte code:
    //   0: new 243	java/util/HashMap
    //   3: dup
    //   4: invokespecial 244	java/util/HashMap:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: invokestatic 115	com/security/antivirus/scan/app/ApplicationEx:a	()Lcom/security/antivirus/scan/app/ApplicationEx;
    //   12: invokevirtual 184	com/security/antivirus/scan/manager/k:a	(Landroid/content/Context;)Landroid/content/pm/PackageManager;
    //   15: astore_3
    //   16: aload_0
    //   17: iconst_0
    //   18: invokevirtual 215	com/security/antivirus/scan/manager/k:b	(Z)Ljava/util/List;
    //   21: invokeinterface 245 1 0
    //   26: astore 4
    //   28: aload 4
    //   30: invokeinterface 104 1 0
    //   35: ifeq +101 -> 136
    //   38: aload 4
    //   40: invokeinterface 108 1 0
    //   45: checkcast 153	android/content/pm/PackageInfo
    //   48: astore 5
    //   50: getstatic 29	com/security/antivirus/scan/manager/k:b	Ljava/util/concurrent/ConcurrentHashMap;
    //   53: aload 5
    //   55: getfield 157	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   58: invokevirtual 248	java/util/concurrent/ConcurrentHashMap:containsKey	(Ljava/lang/Object;)Z
    //   61: ifeq +54 -> 115
    //   64: getstatic 29	com/security/antivirus/scan/manager/k:b	Ljava/util/concurrent/ConcurrentHashMap;
    //   67: aload 5
    //   69: getfield 157	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   72: invokevirtual 179	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   75: checkcast 110	java/lang/String
    //   78: astore_1
    //   79: aload_1
    //   80: invokestatic 74	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   83: ifne +16 -> 99
    //   86: getstatic 29	com/security/antivirus/scan/manager/k:b	Ljava/util/concurrent/ConcurrentHashMap;
    //   89: aload 5
    //   91: getfield 157	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   94: aload_1
    //   95: invokevirtual 209	java/util/concurrent/ConcurrentHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   98: pop
    //   99: aload_2
    //   100: aload 5
    //   102: getfield 157	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   105: aload_1
    //   106: invokeinterface 251 3 0
    //   111: pop
    //   112: goto -84 -> 28
    //   115: aload 5
    //   117: getfield 194	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   120: aload_3
    //   121: invokevirtual 200	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   124: invokestatic 255	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   127: astore_1
    //   128: goto -49 -> 79
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 241	java/lang/Exception:printStackTrace	()V
    //   136: aload_2
    //   137: areturn
    //   138: astore_1
    //   139: goto -111 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	this	k
    //   78	50	1	str	String
    //   131	2	1	localException1	Exception
    //   138	1	1	localException2	Exception
    //   7	130	2	localHashMap	java.util.HashMap
    //   15	106	3	localPackageManager	PackageManager
    //   26	13	4	localIterator	Iterator
    //   48	68	5	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   8	28	131	java/lang/Exception
    //   28	50	131	java/lang/Exception
    //   50	79	138	java/lang/Exception
    //   79	99	138	java/lang/Exception
    //   99	112	138	java/lang/Exception
    //   115	128	138	java/lang/Exception
  }
  
  public boolean b(String paramString)
  {
    synchronized (a)
    {
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (localPackageInfo.packageName.equals(paramString))
        {
          this.c.remove(localPackageInfo);
          return true;
        }
      }
      return false;
    }
  }
  
  public ArrayList<PackageInfo> c()
  {
    synchronized (this.f)
    {
      if (this.e == null)
      {
        this.e = new ArrayList();
        localObject2 = a(ApplicationEx.a());
        this.e.addAll(((PackageManager)localObject2).getInstalledPackages(12288));
      }
      Object localObject2 = (ArrayList)this.e.clone();
      return localObject2;
    }
  }
  
  public Set<String> c(boolean paramBoolean)
  {
    localHashSet = new HashSet();
    try
    {
      Iterator localIterator = b(paramBoolean).iterator();
      while (localIterator.hasNext()) {
        localHashSet.add(((PackageInfo)localIterator.next()).packageName);
      }
      return localHashSet;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public List<String> d(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.d.size() > 0)) {
      return this.d;
    }
    ArrayList localArrayList = new ArrayList(e());
    this.d.clear();
    this.d.addAll(localArrayList);
    return localArrayList;
  }
  
  @j(a=ThreadMode.MAIN)
  public void onEventMainThread(com.security.antivirus.scan.i.c.c arg1)
  {
    this.d.clear();
    y.d();
    c(???.a);
    synchronized (this.f)
    {
      if (this.e != null)
      {
        this.e.clear();
        this.e = null;
      }
      return;
    }
  }
  
  @j(a=ThreadMode.MAIN)
  public void onEventMainThread(d paramD)
  {
    this.d.clear();
  }
  
  @j(a=ThreadMode.MAIN)
  public void onEventMainThread(e paramE)
  {
    this.d.clear();
  }
}
