import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.lm.powersecurity.app.ApplicationEx;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class uy
{
  private static uy a = null;
  private static ConcurrentHashMap<String, String> b = new ConcurrentHashMap();
  private ArrayList<PackageInfo> c = new ArrayList();
  private ArrayList<PackageInfo> d;
  private Object e = new Object();
  private Object f = new Object();
  private ArrayList<String> g = new ArrayList();
  private wv h = wu.getInstance().register();
  
  private uy()
  {
    a();
  }
  
  private Set<String> a(boolean paramBoolean)
  {
    Set localSet = getPkgNameOfInstalledApp(paramBoolean);
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((abt.getLaunchIntentForPackage(ApplicationEx.getInstance().getPackageManager(), str) == null) || (ApplicationEx.getInstance().getPackageName().equals(str)) || (abt.getNameByPackage(str, false).equals(str)) || (!abt.isAppInstalled(str))) {
        localIterator.remove();
      }
    }
    return localSet;
  }
  
  private void a()
  {
    this.h.register(yc.class, new wu.b()
    {
      public void onEventMainThread(yc paramAnonymousYc)
      {
        uy.this.onEventMainThread(paramAnonymousYc);
      }
    });
    this.h.register(xx.class, new wu.b()
    {
      public void onEventMainThread(xx paramAnonymousXx)
      {
        uy.this.onEventMainThread(paramAnonymousXx);
      }
    });
    this.h.register(yb.class, new wu.b()
    {
      public void onEventMainThread(yb paramAnonymousYb)
      {
        uy.this.onEventMainThread(paramAnonymousYb);
      }
    });
  }
  
  private void b() {}
  
  public static uy getInstance()
  {
    if (a == null) {}
    try
    {
      if (a == null) {
        a = new uy();
      }
      return a;
    }
    finally {}
  }
  
  public boolean addPackageInfo(Context paramContext, String paramString)
  {
    try
    {
      synchronized (a)
      {
        paramContext = getPackageManager(paramContext).getPackageInfo(paramString, 0);
        if (paramContext == null) {
          return false;
        }
        this.c.add(paramContext);
        b();
        return true;
      }
      return false;
    }
    catch (Exception paramContext) {}
  }
  
  public String getAppNameFromCache(String paramString)
  {
    if (b.size() == 0) {
      getInstalledAppMap();
    }
    if (adx.isEmpty((String)b.get(paramString))) {
      try
      {
        Object localObject = getPackageManager(ApplicationEx.getInstance());
        localObject = ((PackageManager)localObject).getPackageInfo(paramString, 0).applicationInfo.loadLabel((PackageManager)localObject).toString();
        if (!adx.isEmpty((String)localObject))
        {
          b.put(paramString, localObject);
          return localObject;
        }
      }
      catch (Exception localException) {}
    }
    return (String)b.get(paramString);
  }
  
  public List<String> getClonedAppListFromCache()
  {
    if (this.g.isEmpty()) {}
    synchronized (this.f)
    {
      boolean bool = this.g.isEmpty();
      if (bool) {}
      try
      {
        this.g.addAll(a(false));
        return (ArrayList)this.g.clone();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          ud.error(localException);
        }
      }
    }
  }
  
  public ArrayList<PackageInfo> getClonedUninstalledPackageInfoList()
  {
    synchronized (this.e)
    {
      if (this.d == null)
      {
        this.d = new ArrayList();
        localObject2 = getPackageManager(ApplicationEx.getInstance());
        this.d.addAll(((PackageManager)localObject2).getInstalledPackages(12288));
      }
      Object localObject2 = (ArrayList)this.d.clone();
      return localObject2;
    }
  }
  
  /* Error */
  public java.util.Map<String, String> getInstalledAppMap()
  {
    // Byte code:
    //   0: new 229	java/util/HashMap
    //   3: dup
    //   4: invokespecial 230	java/util/HashMap:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: invokestatic 91	com/lm/powersecurity/app/ApplicationEx:getInstance	()Lcom/lm/powersecurity/app/ApplicationEx;
    //   12: invokevirtual 147	uy:getPackageManager	(Landroid/content/Context;)Landroid/content/pm/PackageManager;
    //   15: astore_3
    //   16: aload_0
    //   17: iconst_0
    //   18: invokevirtual 234	uy:getPackageInfoList	(Z)Ljava/util/List;
    //   21: invokeinterface 237 1 0
    //   26: astore 4
    //   28: aload 4
    //   30: invokeinterface 80 1 0
    //   35: ifeq +101 -> 136
    //   38: aload 4
    //   40: invokeinterface 84 1 0
    //   45: checkcast 179	android/content/pm/PackageInfo
    //   48: astore 5
    //   50: getstatic 37	uy:b	Ljava/util/concurrent/ConcurrentHashMap;
    //   53: aload 5
    //   55: getfield 241	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   58: invokevirtual 244	java/util/concurrent/ConcurrentHashMap:containsKey	(Ljava/lang/Object;)Z
    //   61: ifeq +54 -> 115
    //   64: getstatic 37	uy:b	Ljava/util/concurrent/ConcurrentHashMap;
    //   67: aload 5
    //   69: getfield 241	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   72: invokevirtual 172	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   75: checkcast 86	java/lang/String
    //   78: astore_1
    //   79: aload_1
    //   80: invokestatic 249	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   83: ifne +16 -> 99
    //   86: getstatic 37	uy:b	Ljava/util/concurrent/ConcurrentHashMap;
    //   89: aload 5
    //   91: getfield 241	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   94: aload_1
    //   95: invokevirtual 198	java/util/concurrent/ConcurrentHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   98: pop
    //   99: aload_2
    //   100: aload 5
    //   102: getfield 241	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   105: aload_1
    //   106: invokeinterface 252 3 0
    //   111: pop
    //   112: goto -84 -> 28
    //   115: aload 5
    //   117: getfield 183	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   120: aload_3
    //   121: invokevirtual 189	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   124: invokestatic 256	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   127: astore_1
    //   128: goto -49 -> 79
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 259	java/lang/Exception:printStackTrace	()V
    //   136: aload_2
    //   137: areturn
    //   138: astore_1
    //   139: goto -111 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	this	uy
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
  
  public List<PackageInfo> getPackageInfoList(boolean paramBoolean)
  {
    int i = 1;
    Object localObject1 = getPackageManager(ApplicationEx.getInstance());
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
          catch (Exception localException1) {}
        }
      }
      localObject1 = (List)this.c.clone();
      return localObject1;
      i = 0;
    }
  }
  
  public List<PackageInfo> getPackageInfoListWhenInit(boolean paramBoolean)
  {
    getPackageInfoList(paramBoolean);
    b();
    return (List)this.c.clone();
  }
  
  public PackageManager getPackageManager(Context paramContext)
  {
    return paramContext.getPackageManager();
  }
  
  public Set<String> getPkgNameOfInstalledApp(boolean paramBoolean)
  {
    localHashSet = new HashSet();
    try
    {
      Iterator localIterator = getPackageInfoList(paramBoolean).iterator();
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
  
  public void onEventMainThread(xx arg1)
  {
    synchronized (this.f)
    {
      this.g.clear();
    }
    synchronized (this.e)
    {
      if (this.d != null)
      {
        this.d.clear();
        this.d = null;
      }
      return;
      localObject1 = finally;
      throw localObject1;
    }
  }
  
  public void onEventMainThread(yb arg1)
  {
    synchronized (this.f)
    {
      this.g.clear();
      return;
    }
  }
  
  public void onEventMainThread(yc arg1)
  {
    synchronized (this.f)
    {
      this.g.clear();
      return;
    }
  }
  
  public boolean removePackageInfo(String paramString)
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
  
  /* Error */
  public PackageInfo replacePackageInfo(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: getstatic 30	uy:a	Luy;
    //   3: astore 6
    //   5: aload 6
    //   7: monitorenter
    //   8: aconst_null
    //   9: astore 4
    //   11: aload_0
    //   12: getfield 44	uy:c	Ljava/util/ArrayList;
    //   15: invokevirtual 279	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   18: astore 7
    //   20: aload 7
    //   22: invokeinterface 80 1 0
    //   27: ifeq +36 -> 63
    //   30: aload 7
    //   32: invokeinterface 84 1 0
    //   37: checkcast 179	android/content/pm/PackageInfo
    //   40: astore 5
    //   42: aload 5
    //   44: getfield 241	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   47: aload_1
    //   48: invokevirtual 109	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   51: istore_3
    //   52: iload_3
    //   53: ifeq +39 -> 92
    //   56: aload 5
    //   58: astore 4
    //   60: goto -40 -> 20
    //   63: aload_0
    //   64: aload_1
    //   65: invokevirtual 285	uy:removePackageInfo	(Ljava/lang/String;)Z
    //   68: pop
    //   69: aload_0
    //   70: aload_2
    //   71: aload_1
    //   72: invokevirtual 287	uy:addPackageInfo	(Landroid/content/Context;Ljava/lang/String;)Z
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
    //   0	95	0	this	uy
    //   0	95	1	paramString	String
    //   0	95	2	paramContext	Context
    //   51	2	3	bool	boolean
    //   9	71	4	localObject	Object
    //   40	17	5	localPackageInfo	PackageInfo
    //   3	81	6	localUy	uy
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
}
