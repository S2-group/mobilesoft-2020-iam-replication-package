import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.ihs.device.common.HSAppFilter;
import com.ihs.device.common.HSAppFilter.a;
import com.ihs.device.common.HSAppInfo;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class gbg
{
  private final Map<String, gbg.a> a;
  private ReentrantReadWriteLock b;
  
  /* Error */
  private gbg()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 23	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: new 25	java/util/HashMap
    //   8: dup
    //   9: invokespecial 26	java/util/HashMap:<init>	()V
    //   12: putfield 28	gbg:a	Ljava/util/Map;
    //   15: aload_0
    //   16: new 30	java/util/concurrent/locks/ReentrantReadWriteLock
    //   19: dup
    //   20: invokespecial 31	java/util/concurrent/locks/ReentrantReadWriteLock:<init>	()V
    //   23: putfield 33	gbg:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   26: aload_0
    //   27: getfield 33	gbg:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   30: invokevirtual 37	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   33: invokevirtual 42	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:lock	()V
    //   36: new 44	android/content/IntentFilter
    //   39: dup
    //   40: invokespecial 45	android/content/IntentFilter:<init>	()V
    //   43: astore_1
    //   44: aload_1
    //   45: ldc 47
    //   47: invokevirtual 51	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   50: aload_1
    //   51: ldc 53
    //   53: invokevirtual 51	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   56: aload_1
    //   57: ldc 55
    //   59: invokevirtual 58	android/content/IntentFilter:addDataScheme	(Ljava/lang/String;)V
    //   62: invokestatic 64	fze:e	()Landroid/content/Context;
    //   65: new 6	gbg$1
    //   68: dup
    //   69: aload_0
    //   70: invokespecial 67	gbg$1:<init>	(Lgbg;)V
    //   73: aload_1
    //   74: invokevirtual 73	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   77: pop
    //   78: invokestatic 64	fze:e	()Landroid/content/Context;
    //   81: invokevirtual 77	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   84: astore_1
    //   85: new 79	java/util/HashSet
    //   88: dup
    //   89: invokestatic 84	gbk:a	()Ljava/util/List;
    //   92: invokespecial 87	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   95: astore_2
    //   96: aload_1
    //   97: sipush 128
    //   100: invokevirtual 93	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   103: invokeinterface 99 1 0
    //   108: astore_3
    //   109: aload_3
    //   110: invokeinterface 105 1 0
    //   115: ifeq +64 -> 179
    //   118: aload_3
    //   119: invokeinterface 109 1 0
    //   124: checkcast 111	android/content/pm/ApplicationInfo
    //   127: astore 4
    //   129: aload_0
    //   130: aload 4
    //   132: aload_1
    //   133: invokespecial 114	gbg:a	(Landroid/content/pm/ApplicationInfo;Landroid/content/pm/PackageManager;)Z
    //   136: ifeq -27 -> 109
    //   139: aload_0
    //   140: getfield 28	gbg:a	Ljava/util/Map;
    //   143: aload 4
    //   145: getfield 118	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   148: new 10	gbg$a
    //   151: dup
    //   152: aload 4
    //   154: aload_1
    //   155: aload_2
    //   156: aload 4
    //   158: getfield 118	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   161: invokeinterface 124 2 0
    //   166: aconst_null
    //   167: invokespecial 127	gbg$a:<init>	(Landroid/content/pm/ApplicationInfo;Landroid/content/pm/PackageManager;ZLgbg$1;)V
    //   170: invokeinterface 133 3 0
    //   175: pop
    //   176: goto -67 -> 109
    //   179: new 135	java/lang/StringBuilder
    //   182: dup
    //   183: invokespecial 136	java/lang/StringBuilder:<init>	()V
    //   186: astore_1
    //   187: aload_1
    //   188: ldc -118
    //   190: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: pop
    //   194: aload_1
    //   195: aload_0
    //   196: getfield 28	gbg:a	Ljava/util/Map;
    //   199: invokeinterface 146 1 0
    //   204: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   207: pop
    //   208: ldc -105
    //   210: aload_1
    //   211: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokestatic 160	gaz:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   217: goto +12 -> 229
    //   220: astore_1
    //   221: goto +19 -> 240
    //   224: astore_1
    //   225: aload_1
    //   226: invokevirtual 163	java/lang/Exception:printStackTrace	()V
    //   229: aload_0
    //   230: getfield 33	gbg:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   233: invokevirtual 37	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   236: invokevirtual 166	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   239: return
    //   240: aload_0
    //   241: getfield 33	gbg:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   244: invokevirtual 37	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   247: invokevirtual 166	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   250: aload_1
    //   251: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	252	0	this	gbg
    //   43	168	1	localObject1	Object
    //   220	1	1	localObject2	Object
    //   224	27	1	localException	Exception
    //   95	61	2	localHashSet	java.util.HashSet
    //   108	11	3	localIterator	Iterator
    //   127	30	4	localApplicationInfo	ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   36	109	220	finally
    //   109	176	220	finally
    //   179	217	220	finally
    //   225	229	220	finally
    //   36	109	224	java/lang/Exception
    //   109	176	224	java/lang/Exception
    //   179	217	224	java/lang/Exception
  }
  
  public static gbg a()
  {
    return gbg.b.a();
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager)
  {
    if (paramApplicationInfo != null)
    {
      if (paramPackageManager == null) {
        return false;
      }
      return !TextUtils.isEmpty(paramApplicationInfo.packageName);
    }
    return false;
  }
  
  private List<gbg.a> b()
  {
    this.b.readLock().lock();
    try
    {
      ArrayList localArrayList = new ArrayList(this.a.values());
      return localArrayList;
    }
    finally
    {
      this.b.readLock().unlock();
    }
  }
  
  /* Error */
  public <T extends HSAppInfo> T a(Class<T> paramClass, String paramString)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic 179	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: getfield 33	gbg:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   13: invokevirtual 184	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   16: invokevirtual 187	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:lock	()V
    //   19: aload_0
    //   20: getfield 28	gbg:a	Ljava/util/Map;
    //   23: aload_2
    //   24: invokeinterface 203 2 0
    //   29: checkcast 10	gbg$a
    //   32: astore_2
    //   33: aload_2
    //   34: ifnull +21 -> 55
    //   37: aload_2
    //   38: aload_1
    //   39: invokestatic 206	gbg$a:a	(Lgbg$a;Ljava/lang/Class;)Lcom/ihs/device/common/HSAppInfo;
    //   42: astore_1
    //   43: aload_0
    //   44: getfield 33	gbg:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   47: invokevirtual 184	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   50: invokevirtual 195	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   53: aload_1
    //   54: areturn
    //   55: aload_0
    //   56: getfield 33	gbg:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   59: invokevirtual 184	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   62: invokevirtual 195	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   65: aconst_null
    //   66: areturn
    //   67: astore_1
    //   68: goto +20 -> 88
    //   71: astore_1
    //   72: aload_1
    //   73: invokevirtual 163	java/lang/Exception:printStackTrace	()V
    //   76: aload_0
    //   77: getfield 33	gbg:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   80: invokevirtual 184	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   83: invokevirtual 195	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   86: aconst_null
    //   87: areturn
    //   88: aload_0
    //   89: getfield 33	gbg:b	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   92: invokevirtual 184	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   95: invokevirtual 195	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   98: aload_1
    //   99: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	100	0	this	gbg
    //   0	100	1	paramClass	Class<T>
    //   0	100	2	paramString	String
    // Exception table:
    //   from	to	target	type
    //   19	33	67	finally
    //   37	43	67	finally
    //   72	76	67	finally
    //   19	33	71	java/lang/Exception
    //   37	43	71	java/lang/Exception
  }
  
  public <T extends HSAppInfo> ArrayList<T> a(Class<T> paramClass, HSAppFilter paramHSAppFilter)
  {
    HSAppFilter localHSAppFilter = paramHSAppFilter;
    if (paramHSAppFilter == null) {
      localHSAppFilter = new HSAppFilter();
    }
    Object localObject1 = b();
    paramHSAppFilter = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (gbg.a)((Iterator)localObject1).next();
      if (localHSAppFilter.a((HSAppFilter.a)localObject2))
      {
        localObject2 = gbg.a.a((gbg.a)localObject2, paramClass);
        if (localObject2 != null) {
          paramHSAppFilter.add(localObject2);
        }
      }
    }
    return paramHSAppFilter;
  }
  
  public boolean a(String paramString, HSAppFilter paramHSAppFilter)
  {
    HSAppFilter localHSAppFilter = paramHSAppFilter;
    if (paramHSAppFilter == null) {
      localHSAppFilter = new HSAppFilter();
    }
    boolean bool1 = TextUtils.isEmpty(paramString);
    boolean bool2 = false;
    if (bool1) {
      return false;
    }
    this.b.readLock().lock();
    try
    {
      paramString = (gbg.a)this.a.get(paramString);
      bool1 = bool2;
      if (paramString != null)
      {
        boolean bool3 = localHSAppFilter.a(paramString);
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    finally
    {
      this.b.readLock().unlock();
    }
  }
  
  static class a
    implements HSAppFilter.a
  {
    private final String a;
    private final String b;
    private final int c;
    private final String d;
    private final boolean e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final int j;
    
    private a(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager)
    {
      this(paramApplicationInfo, paramPackageManager, gbk.c(paramApplicationInfo.packageName));
    }
    
    private a(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager, boolean paramBoolean)
    {
      this.a = paramApplicationInfo.packageName;
      paramPackageManager = paramPackageManager.getApplicationLabel(paramApplicationInfo);
      if (paramPackageManager == null) {
        paramPackageManager = "";
      } else {
        paramPackageManager = paramPackageManager.toString().trim();
      }
      this.b = paramPackageManager;
      this.c = paramApplicationInfo.flags;
      this.d = paramApplicationInfo.publicSourceDir;
      this.e = gbk.a(paramApplicationInfo.packageName);
      this.f = gbk.a(paramApplicationInfo);
      this.g = gbk.b(paramApplicationInfo.packageName);
      this.h = paramBoolean;
      this.i = gbk.d(paramApplicationInfo.packageName);
      this.j = paramApplicationInfo.uid;
    }
    
    private <T extends HSAppInfo> T a(Class<T> paramClass)
    {
      try
      {
        paramClass = (HSAppInfo)paramClass.getConstructor(new Class[] { String.class }).newInstance(new Object[] { this.a });
        paramClass.b(this.b);
        paramClass.a(this.c);
        paramClass.a(this.d);
        paramClass.c(this.e);
        paramClass.a(this.f);
        paramClass.b(this.g);
        paramClass.d(this.h);
        paramClass.e(this.i);
        paramClass.b(this.j);
        return paramClass;
      }
      catch (Throwable paramClass)
      {
        for (;;) {}
      }
      gaz.d("AppInfoManager", "error getConstructor");
      return null;
    }
    
    public String a()
    {
      return this.a;
    }
    
    public boolean b()
    {
      return this.e;
    }
    
    public boolean c()
    {
      return this.f;
    }
    
    public boolean d()
    {
      return this.g;
    }
    
    public boolean e()
    {
      return this.h;
    }
    
    public boolean f()
    {
      return this.i;
    }
    
    public boolean g()
    {
      return false;
    }
    
    public boolean h()
    {
      return false;
    }
  }
  
  static class b
  {
    private static final gbg a = new gbg(null);
  }
}
