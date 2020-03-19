package ks.cm.antivirus.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import com.cleanmaster.security.util.Singleton;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import ks.cm.antivirus.main.MobileDubaApplication;
import ks.cm.antivirus.main.RuntimeCheck;

public class v
{
  private static Singleton<v> a = new Singleton()
  {
    protected v a()
    {
      return new v();
    }
  };
  private static final aa b = new aa(null);
  private y c;
  private x d;
  private final ConcurrentHashMap<String, Reference<PackageInfo>> e = new ConcurrentHashMap();
  private Reference<List<PackageInfo>> f = z.a(null);
  private boolean g = false;
  private final ConcurrentHashMap<String, Reference<String>> h = new ConcurrentHashMap();
  private final ConcurrentHashMap<String, Reference<List<ResolveInfo>>> i = new ConcurrentHashMap();
  private final ConcurrentHashMap<String, Reference<ApplicationInfo>> j = new ConcurrentHashMap();
  private final ConcurrentHashMap<String, Reference<w>> k = new ConcurrentHashMap();
  
  public v() {}
  
  private String a(Intent paramIntent, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramIntent.getAction());
    localStringBuilder.append(paramIntent.getCategories()).append(paramInt);
    if (Build.VERSION.SDK_INT >= 15)
    {
      paramIntent = paramIntent.getSelector();
      if (paramIntent != null) {
        localStringBuilder.append(paramIntent.getAction()).append(paramIntent.getCategories());
      }
    }
    return localStringBuilder.toString();
  }
  
  private List<PackageInfo> a(List<PackageInfo> paramList, Context paramContext, int paramInt)
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {}
    do
    {
      return null;
      if (paramList != null)
      {
        paramContext = new ArrayList();
        paramContext.addAll(paramList);
        return paramContext;
      }
      int m = paramInt;
      if (paramInt == 0) {
        m = 4224;
      }
      try
      {
        paramContext = paramContext.getInstalledPackages(m);
        paramList = paramContext;
      }
      catch (Throwable paramContext)
      {
        for (;;) {}
      }
      if (m == 4224) {
        this.f = z.a(paramList);
      }
    } while (paramList == null);
    paramContext = new ArrayList();
    paramContext.addAll(paramList);
    return paramContext;
  }
  
  private List<ResolveInfo> a(List<ResolveInfo> paramList, String paramString, final Context paramContext, Intent paramIntent, int paramInt)
  {
    Object localObject = null;
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return null;
    }
    if (paramList != null)
    {
      paramString = new ArrayList();
      paramString.addAll(paramList);
      return paramString;
    }
    paramContext = paramContext.queryIntentActivities(paramIntent, paramInt);
    this.i.put(paramString, z.b(paramContext));
    paramList = localObject;
    if (paramContext != null)
    {
      paramList = new ArrayList();
      paramList.addAll(paramContext);
    }
    try
    {
      if ((RuntimeCheck.c()) && (!this.g) && (paramContext != null))
      {
        this.g = true;
        new Thread(new Runnable()
        {
          public void run()
          {
            if (paramContext != null)
            {
              Iterator localIterator = paramContext.iterator();
              while (localIterator.hasNext())
              {
                ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
                v.this.a(localResolveInfo);
              }
            }
          }
        }, "PackageInfoLoader:labelPreload").start();
      }
      return paramList;
    }
    finally {}
  }
  
  public static v a()
  {
    return (v)a.c();
  }
  
  /* Error */
  private void a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 177	ks/cm/antivirus/common/utils/v:c	Lks/cm/antivirus/common/utils/y;
    //   6: ifnonnull +64 -> 70
    //   9: aload_0
    //   10: new 179	ks/cm/antivirus/common/utils/y
    //   13: dup
    //   14: aload_0
    //   15: aconst_null
    //   16: invokespecial 182	ks/cm/antivirus/common/utils/y:<init>	(Lks/cm/antivirus/common/utils/v;Lks/cm/antivirus/common/utils/v$1;)V
    //   19: putfield 177	ks/cm/antivirus/common/utils/v:c	Lks/cm/antivirus/common/utils/y;
    //   22: new 184	android/content/IntentFilter
    //   25: dup
    //   26: invokespecial 185	android/content/IntentFilter:<init>	()V
    //   29: astore_2
    //   30: aload_2
    //   31: ldc -69
    //   33: invokevirtual 190	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   36: aload_2
    //   37: ldc -64
    //   39: invokevirtual 190	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   42: aload_2
    //   43: ldc -62
    //   45: invokevirtual 190	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   48: aload_2
    //   49: ldc -60
    //   51: invokevirtual 199	android/content/IntentFilter:addDataScheme	(Ljava/lang/String;)V
    //   54: aload_2
    //   55: ldc -55
    //   57: invokevirtual 204	android/content/IntentFilter:addCategory	(Ljava/lang/String;)V
    //   60: aload_1
    //   61: aload_0
    //   62: getfield 177	ks/cm/antivirus/common/utils/v:c	Lks/cm/antivirus/common/utils/y;
    //   65: aload_2
    //   66: invokevirtual 208	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   69: pop
    //   70: aload_0
    //   71: getfield 210	ks/cm/antivirus/common/utils/v:d	Lks/cm/antivirus/common/utils/x;
    //   74: ifnonnull +40 -> 114
    //   77: aload_0
    //   78: new 212	ks/cm/antivirus/common/utils/x
    //   81: dup
    //   82: aload_0
    //   83: aconst_null
    //   84: invokespecial 213	ks/cm/antivirus/common/utils/x:<init>	(Lks/cm/antivirus/common/utils/v;Lks/cm/antivirus/common/utils/v$1;)V
    //   87: putfield 210	ks/cm/antivirus/common/utils/v:d	Lks/cm/antivirus/common/utils/x;
    //   90: new 184	android/content/IntentFilter
    //   93: dup
    //   94: invokespecial 185	android/content/IntentFilter:<init>	()V
    //   97: astore_2
    //   98: aload_2
    //   99: ldc -41
    //   101: invokevirtual 190	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   104: aload_1
    //   105: aload_0
    //   106: getfield 210	ks/cm/antivirus/common/utils/v:d	Lks/cm/antivirus/common/utils/x;
    //   109: aload_2
    //   110: invokevirtual 208	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   113: pop
    //   114: aload_0
    //   115: monitorexit
    //   116: return
    //   117: astore_1
    //   118: aload_0
    //   119: monitorexit
    //   120: aload_1
    //   121: athrow
    //   122: astore_1
    //   123: goto -9 -> 114
    //   126: astore_2
    //   127: goto -57 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	this	v
    //   0	130	1	paramContext	Context
    //   29	81	2	localIntentFilter	android.content.IntentFilter
    //   126	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	60	117	finally
    //   60	70	117	finally
    //   70	104	117	finally
    //   104	114	117	finally
    //   104	114	122	java/lang/Exception
    //   60	70	126	java/lang/Exception
  }
  
  private void c()
  {
    this.f = z.a(null);
    this.i.clear();
    this.e.clear();
    this.j.clear();
    this.h.clear();
    this.k.clear();
  }
  
  public String a(ApplicationInfo paramApplicationInfo)
  {
    Object localObject3;
    if (paramApplicationInfo == null)
    {
      localObject3 = "";
      return localObject3;
    }
    for (;;)
    {
      try
      {
        String str = paramApplicationInfo.packageName + ":" + paramApplicationInfo.labelRes;
        Object localObject1 = (Reference)this.h.get(str);
        if (localObject1 != null)
        {
          localObject1 = (String)((Reference)localObject1).get();
          localObject3 = localObject1;
          if (localObject1 != null) {
            break;
          }
          localObject1 = paramApplicationInfo.loadLabel(MobileDubaApplication.getInstance().getPackageManager()).toString();
          this.h.put(str, z.a((String)localObject1));
          return localObject1;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return paramApplicationInfo.packageName;
      }
      Object localObject2 = null;
    }
  }
  
  public String a(ResolveInfo paramResolveInfo)
  {
    if (paramResolveInfo == null)
    {
      localObject1 = "";
      return localObject1;
    }
    Object localObject2;
    if (paramResolveInfo.activityInfo != null) {
      localObject2 = paramResolveInfo.activityInfo;
    }
    String str2;
    for (;;)
    {
      try
      {
        if ((paramResolveInfo.resolvePackageName == null) || (paramResolveInfo.labelRes == 0)) {
          break label164;
        }
        str2 = paramResolveInfo.resolvePackageName + ":" + paramResolveInfo.labelRes;
        localObject1 = (Reference)this.h.get(str2);
        if (localObject1 == null) {
          break label159;
        }
        str1 = (String)((Reference)localObject1).get();
        localObject1 = str1;
        if (str1 != null) {
          break;
        }
        localObject1 = paramResolveInfo.loadLabel(MobileDubaApplication.getInstance().getPackageManager());
        if (localObject1 == null) {
          break label164;
        }
        paramResolveInfo = ((CharSequence)localObject1).toString().trim();
        this.h.put(str2, z.a(paramResolveInfo));
        return paramResolveInfo;
      }
      catch (Exception paramResolveInfo)
      {
        paramResolveInfo.printStackTrace();
        return ((ComponentInfo)localObject2).packageName;
      }
      localObject2 = paramResolveInfo.serviceInfo;
      continue;
      label159:
      str1 = null;
    }
    label164:
    Object localObject1 = ((ComponentInfo)localObject2).applicationInfo;
    if (paramResolveInfo.labelRes != 0)
    {
      str2 = ((ComponentInfo)localObject2).packageName + ":" + paramResolveInfo.labelRes;
      localObject1 = (Reference)this.h.get(str2);
      if (localObject1 == null) {
        break label382;
      }
    }
    label382:
    for (String str1 = (String)((Reference)localObject1).get();; str1 = null)
    {
      localObject1 = str1;
      if (str1 != null) {
        break;
      }
      localObject1 = paramResolveInfo.loadLabel(MobileDubaApplication.getInstance().getPackageManager());
      if (localObject1 != null)
      {
        paramResolveInfo = ((CharSequence)localObject1).toString().trim();
        this.h.put(str2, z.a(paramResolveInfo));
        return paramResolveInfo;
      }
      str2 = ((ComponentInfo)localObject2).packageName + ":" + ((ComponentInfo)localObject2).labelRes;
      localObject1 = (Reference)this.h.get(str2);
      if (localObject1 != null) {}
      for (str1 = (String)((Reference)localObject1).get();; str1 = null)
      {
        localObject1 = str1;
        if (str1 != null) {
          break;
        }
        paramResolveInfo = paramResolveInfo.loadLabel(MobileDubaApplication.getInstance().getPackageManager()).toString();
        this.h.put(str2, z.a(paramResolveInfo));
        return paramResolveInfo;
      }
    }
  }
  
  public List<PackageInfo> a(Context paramContext, int paramInt)
  {
    if (paramContext == null) {}
    PackageManager localPackageManager;
    do
    {
      return null;
      localPackageManager = paramContext.getPackageManager();
    } while (localPackageManager == null);
    a(paramContext);
    int m = paramInt;
    if (paramInt == 0) {
      m = 4224;
    }
    if ((m & 0x1080) == m) {
      return a((List)this.f.get(), paramContext, m);
    }
    try
    {
      paramContext = localPackageManager.getInstalledPackages(m);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public List<ResolveInfo> a(Context paramContext, Intent paramIntent, int paramInt)
  {
    List localList = null;
    if (paramContext == null) {
      return null;
    }
    a(paramContext);
    String str = a(paramIntent, paramInt);
    Reference localReference = (Reference)this.i.get(str);
    if (localReference != null) {
      localList = (List)localReference.get();
    }
    return a(localList, str, paramContext, paramIntent, paramInt);
  }
  
  public w a(String paramString, int paramInt)
  {
    Object localObject1 = null;
    if ((paramInt == 0) || (128 == paramInt)) {}
    Object localObject2;
    try
    {
      localObject2 = (Reference)this.k.get(paramString);
      if (localObject2 == null) {
        break label165;
      }
      localObject1 = (w)((Reference)localObject2).get();
    }
    catch (Exception paramString)
    {
      return null;
    }
    paramString = MobileDubaApplication.getInstance().getPackageManager().getApplicationInfo(paramString, paramInt);
    paramString = new w(paramString, a(paramString));
    return paramString;
    try
    {
      do
      {
        localObject2 = MobileDubaApplication.getInstance().getPackageManager().getApplicationInfo(paramString, 128);
        localObject2 = new w((ApplicationInfo)localObject2, a((ApplicationInfo)localObject2));
        localObject1 = localObject2;
        if (localObject2 == null) {
          break;
        }
        if (paramInt != 0)
        {
          localObject1 = localObject2;
          if (paramInt != 128) {
            break;
          }
        }
      } while (localObject1 == null);
    }
    catch (Exception paramString)
    {
      try
      {
        this.k.put(paramString, z.a((w)localObject2));
        return localObject2;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        localObject1 = localObject2;
        try
        {
          throw paramString;
        }
        catch (Exception paramString)
        {
          return localObject1;
        }
        paramString = paramString;
        return localObject1;
      }
      catch (Exception paramString)
      {
        return localObject2;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    label165:
    return localObject1;
  }
  
  public PackageInfo b(String paramString, int paramInt)
  {
    int m = paramInt;
    if (paramInt == 0) {
      m = 4224;
    }
    Object localObject1 = null;
    if ((m & 0x1080) == m)
    {
      localObject2 = (Reference)this.e.get(paramString);
      if (localObject2 == b) {
        throw new PackageManager.NameNotFoundException();
      }
      if (localObject2 != null) {
        localObject1 = (PackageInfo)((Reference)localObject2).get();
      }
      if (localObject1 != null) {
        return localObject1;
      }
      localObject2 = (List)this.f.get();
      if (localObject2 != null)
      {
        Iterator localIterator = ((List)localObject2).iterator();
        while (localIterator.hasNext())
        {
          localObject2 = (PackageInfo)localIterator.next();
          if (((PackageInfo)localObject2).packageName.equalsIgnoreCase(paramString))
          {
            localObject1 = localObject2;
            label138:
            if (localObject1 != null)
            {
              this.e.put(paramString, z.a((PackageInfo)localObject1));
              return localObject1;
            }
            this.e.put(paramString, b);
            throw new PackageManager.NameNotFoundException();
          }
        }
      }
    }
    for (Object localObject2 = localObject1;; localObject2 = null)
    {
      localObject1 = localObject2;
      if (localObject2 != null) {
        break;
      }
      try
      {
        localObject2 = MobileDubaApplication.getInstance().getPackageManager().getPackageInfo(paramString, m);
        localObject1 = localObject2;
        if (m != 4224) {
          break;
        }
        localObject1 = localObject2;
        if (localObject2 == null) {
          break;
        }
        this.e.put(paramString, z.a((PackageInfo)localObject2));
        return localObject2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        if (m == 4224) {
          this.e.put(paramString, b);
        }
        throw localNameNotFoundException;
      }
      break label138;
    }
  }
  
  /* Error */
  public void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 177	ks/cm/antivirus/common/utils/v:c	Lks/cm/antivirus/common/utils/y;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +18 -> 26
    //   11: invokestatic 253	ks/cm/antivirus/main/MobileDubaApplication:getInstance	()Lks/cm/antivirus/main/MobileDubaApplication;
    //   14: aload_0
    //   15: getfield 177	ks/cm/antivirus/common/utils/v:c	Lks/cm/antivirus/common/utils/y;
    //   18: invokevirtual 354	ks/cm/antivirus/main/MobileDubaApplication:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   21: aload_0
    //   22: aconst_null
    //   23: putfield 177	ks/cm/antivirus/common/utils/v:c	Lks/cm/antivirus/common/utils/y;
    //   26: aload_0
    //   27: getfield 210	ks/cm/antivirus/common/utils/v:d	Lks/cm/antivirus/common/utils/x;
    //   30: astore_1
    //   31: aload_1
    //   32: ifnull +18 -> 50
    //   35: invokestatic 253	ks/cm/antivirus/main/MobileDubaApplication:getInstance	()Lks/cm/antivirus/main/MobileDubaApplication;
    //   38: aload_0
    //   39: getfield 210	ks/cm/antivirus/common/utils/v:d	Lks/cm/antivirus/common/utils/x;
    //   42: invokevirtual 354	ks/cm/antivirus/main/MobileDubaApplication:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   45: aload_0
    //   46: aconst_null
    //   47: putfield 210	ks/cm/antivirus/common/utils/v:d	Lks/cm/antivirus/common/utils/x;
    //   50: aload_0
    //   51: monitorexit
    //   52: return
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    //   58: astore_1
    //   59: goto -9 -> 50
    //   62: astore_1
    //   63: goto -37 -> 26
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	v
    //   6	26	1	localObject1	Object
    //   53	4	1	localObject2	Object
    //   58	1	1	localException1	Exception
    //   62	1	1	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   2	7	53	finally
    //   11	26	53	finally
    //   26	31	53	finally
    //   35	50	53	finally
    //   35	50	58	java/lang/Exception
    //   11	26	62	java/lang/Exception
  }
  
  public PackageInfo c(String paramString, int paramInt)
  {
    try
    {
      paramString = MobileDubaApplication.getInstance().getPackageManager().getPackageInfo(paramString, paramInt);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      throw paramString;
    }
  }
}
