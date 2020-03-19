package com.clean.spaceplus.base.utils.system;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.clean.spaceplus.app.SpaceApplication;
import com.clean.spaceplus.base.utils.e;
import com.clean.spaceplus.base.utils.monitor.MonitorManager;
import com.clean.spaceplus.base.utils.monitor.MonitorManager.a;
import com.tcl.framework.log.NLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import space.network.c.j;

public class b
{
  private static final String b = b.class.getSimpleName();
  private static b c = new b();
  Context a = SpaceApplication.l();
  private b d = null;
  
  private b()
  {
    if (!j.e())
    {
      this.d = new a(null);
      return;
    }
    this.d = new b(null);
  }
  
  public static b a()
  {
    return c;
  }
  
  /* Error */
  private List<PackageInfo> a(PackageManager paramPackageManager, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 64	com/clean/spaceplus/base/utils/e:a	()Ljava/lang/Boolean;
    //   5: invokevirtual 69	java/lang/Boolean:booleanValue	()Z
    //   8: ifeq +15 -> 23
    //   11: getstatic 28	com/clean/spaceplus/base/utils/system/b:b	Ljava/lang/String;
    //   14: ldc 71
    //   16: iconst_0
    //   17: anewarray 4	java/lang/Object
    //   20: invokestatic 76	com/tcl/framework/log/NLog:d	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   23: aconst_null
    //   24: astore_3
    //   25: aload_1
    //   26: iload_2
    //   27: invokevirtual 82	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   30: astore_1
    //   31: aload_1
    //   32: ifnull +46 -> 78
    //   35: invokestatic 64	com/clean/spaceplus/base/utils/e:a	()Ljava/lang/Boolean;
    //   38: invokevirtual 69	java/lang/Boolean:booleanValue	()Z
    //   41: ifeq +37 -> 78
    //   44: getstatic 28	com/clean/spaceplus/base/utils/system/b:b	Ljava/lang/String;
    //   47: new 84	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   54: ldc 87
    //   56: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload_1
    //   60: invokeinterface 97 1 0
    //   65: invokevirtual 100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   68: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: iconst_0
    //   72: anewarray 4	java/lang/Object
    //   75: invokestatic 76	com/tcl/framework/log/NLog:d	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_1
    //   81: areturn
    //   82: astore 4
    //   84: aload_3
    //   85: astore_1
    //   86: invokestatic 64	com/clean/spaceplus/base/utils/e:a	()Ljava/lang/Boolean;
    //   89: invokevirtual 69	java/lang/Boolean:booleanValue	()Z
    //   92: ifeq -61 -> 31
    //   95: getstatic 28	com/clean/spaceplus/base/utils/system/b:b	Ljava/lang/String;
    //   98: new 84	java/lang/StringBuilder
    //   101: dup
    //   102: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   105: ldc 105
    //   107: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: aload 4
    //   112: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   115: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   118: iconst_0
    //   119: anewarray 4	java/lang/Object
    //   122: invokestatic 110	com/tcl/framework/log/NLog:e	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   125: aload_3
    //   126: astore_1
    //   127: goto -96 -> 31
    //   130: astore_1
    //   131: aload_0
    //   132: monitorexit
    //   133: aload_1
    //   134: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	135	0	this	b
    //   0	135	1	paramPackageManager	PackageManager
    //   0	135	2	paramInt	int
    //   24	102	3	localObject	Object
    //   82	29	4	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   25	31	82	java/lang/Throwable
    //   2	23	130	finally
    //   25	31	130	finally
    //   35	78	130	finally
    //   86	125	130	finally
  }
  
  public static boolean a(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {}
    while (((paramApplicationInfo.flags & 0x1) != 0) || ((paramApplicationInfo.flags & 0x80) != 0)) {
      return false;
    }
    return true;
  }
  
  public static boolean b(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {}
    while (((paramApplicationInfo.flags & 0x1) == 0) && ((paramApplicationInfo.flags & 0x80) == 0)) {
      return false;
    }
    return true;
  }
  
  /* Error */
  public List<android.content.pm.ResolveInfo> a(PackageManager paramPackageManager)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: new 126	android/content/Intent
    //   7: dup
    //   8: ldc -128
    //   10: aconst_null
    //   11: invokespecial 131	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   14: astore_3
    //   15: aload_3
    //   16: ldc -123
    //   18: invokevirtual 137	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   21: pop
    //   22: aload_1
    //   23: aload_3
    //   24: iconst_0
    //   25: invokevirtual 141	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   28: astore_1
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_1
    //   32: areturn
    //   33: astore_3
    //   34: aload_2
    //   35: astore_1
    //   36: invokestatic 64	com/clean/spaceplus/base/utils/e:a	()Ljava/lang/Boolean;
    //   39: invokevirtual 69	java/lang/Boolean:booleanValue	()Z
    //   42: ifeq -13 -> 29
    //   45: aload_3
    //   46: invokestatic 145	com/tcl/framework/log/NLog:printStackTrace	(Ljava/lang/Throwable;)V
    //   49: aload_2
    //   50: astore_1
    //   51: goto -22 -> 29
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	b
    //   0	59	1	paramPackageManager	PackageManager
    //   1	49	2	localObject	Object
    //   14	10	3	localIntent	Intent
    //   33	13	3	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   22	29	33	java/lang/Throwable
    //   4	22	54	finally
    //   22	29	54	finally
    //   36	49	54	finally
  }
  
  /* Error */
  public List<android.content.pm.ResolveInfo> a(String paramString, PackageManager paramPackageManager)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: monitorenter
    //   4: new 126	android/content/Intent
    //   7: dup
    //   8: ldc -128
    //   10: aconst_null
    //   11: invokespecial 131	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   14: astore 4
    //   16: aload 4
    //   18: ldc -123
    //   20: invokevirtual 137	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   23: pop
    //   24: aload 4
    //   26: aload_1
    //   27: invokevirtual 150	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   30: pop
    //   31: aload_2
    //   32: aload 4
    //   34: iconst_0
    //   35: invokevirtual 141	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: areturn
    //   43: astore_2
    //   44: aload_3
    //   45: astore_1
    //   46: invokestatic 64	com/clean/spaceplus/base/utils/e:a	()Ljava/lang/Boolean;
    //   49: invokevirtual 69	java/lang/Boolean:booleanValue	()Z
    //   52: ifeq -13 -> 39
    //   55: aload_2
    //   56: invokestatic 145	com/tcl/framework/log/NLog:printStackTrace	(Ljava/lang/Throwable;)V
    //   59: aload_3
    //   60: astore_1
    //   61: goto -22 -> 39
    //   64: astore_1
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	b
    //   0	69	1	paramString	String
    //   0	69	2	paramPackageManager	PackageManager
    //   1	59	3	localObject	Object
    //   14	19	4	localIntent	Intent
    // Exception table:
    //   from	to	target	type
    //   31	39	43	java/lang/Throwable
    //   4	31	64	finally
    //   31	39	64	finally
    //   46	59	64	finally
  }
  
  /* Error */
  public List<PackageInfo> a(ArrayList<String> paramArrayList)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 64	com/clean/spaceplus/base/utils/e:a	()Ljava/lang/Boolean;
    //   5: invokevirtual 69	java/lang/Boolean:booleanValue	()Z
    //   8: ifeq +15 -> 23
    //   11: getstatic 28	com/clean/spaceplus/base/utils/system/b:b	Ljava/lang/String;
    //   14: ldc 71
    //   16: iconst_0
    //   17: anewarray 4	java/lang/Object
    //   20: invokestatic 76	com/tcl/framework/log/NLog:d	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   23: new 154	java/util/ArrayList
    //   26: dup
    //   27: invokespecial 155	java/util/ArrayList:<init>	()V
    //   30: astore_2
    //   31: aload_0
    //   32: getfield 43	com/clean/spaceplus/base/utils/system/b:a	Landroid/content/Context;
    //   35: invokevirtual 161	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   38: astore_3
    //   39: aload_1
    //   40: invokevirtual 165	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   43: astore_1
    //   44: aload_1
    //   45: invokeinterface 170 1 0
    //   50: ifeq +28 -> 78
    //   53: aload_2
    //   54: aload_3
    //   55: aload_1
    //   56: invokeinterface 174 1 0
    //   61: checkcast 176	java/lang/String
    //   64: iconst_0
    //   65: invokevirtual 180	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   68: invokeinterface 184 2 0
    //   73: pop
    //   74: goto -30 -> 44
    //   77: astore_1
    //   78: aload_2
    //   79: ifnull +46 -> 125
    //   82: invokestatic 64	com/clean/spaceplus/base/utils/e:a	()Ljava/lang/Boolean;
    //   85: invokevirtual 69	java/lang/Boolean:booleanValue	()Z
    //   88: ifeq +37 -> 125
    //   91: getstatic 28	com/clean/spaceplus/base/utils/system/b:b	Ljava/lang/String;
    //   94: new 84	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   101: ldc -70
    //   103: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_2
    //   107: invokeinterface 97 1 0
    //   112: invokevirtual 100	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   115: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   118: iconst_0
    //   119: anewarray 4	java/lang/Object
    //   122: invokestatic 76	com/tcl/framework/log/NLog:d	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   125: aload_0
    //   126: monitorexit
    //   127: aload_2
    //   128: areturn
    //   129: astore_1
    //   130: aload_0
    //   131: monitorexit
    //   132: aload_1
    //   133: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	this	b
    //   0	134	1	paramArrayList	ArrayList<String>
    //   30	98	2	localArrayList	ArrayList
    //   38	17	3	localPackageManager	PackageManager
    // Exception table:
    //   from	to	target	type
    //   39	44	77	java/lang/Throwable
    //   44	74	77	java/lang/Throwable
    //   2	23	129	finally
    //   23	39	129	finally
    //   39	44	129	finally
    //   44	74	129	finally
    //   82	125	129	finally
  }
  
  public boolean a(String paramString)
  {
    return this.d.b(paramString);
  }
  
  public ProviderInfo[] a(Context paramContext, String paramString)
  {
    return this.d.a(paramContext, paramString);
  }
  
  public List<PackageInfo> b()
  {
    return this.d.a();
  }
  
  public List<PackageInfo> c()
  {
    return this.d.c();
  }
  
  public List<PackageInfo> d()
  {
    return this.d.b();
  }
  
  public List<String> e()
  {
    return this.d.d();
  }
  
  private class a
    extends b.b
    implements MonitorManager.a
  {
    private int d = 0;
    private int e = 1;
    private int f = 2;
    private int g = this.d;
    private List<PackageInfo> h = null;
    
    private a()
    {
      super(null);
    }
    
    private void a(PackageInfo paramPackageInfo)
    {
      try
      {
        if ((this.h != null) && (paramPackageInfo != null))
        {
          this.h.remove(paramPackageInfo);
          this.h.add(paramPackageInfo);
        }
        return;
      }
      finally {}
    }
    
    /* Error */
    private void c(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_1
      //   3: ifnull +31 -> 34
      //   6: aload_0
      //   7: getfield 35	com/clean/spaceplus/base/utils/system/b$a:h	Ljava/util/List;
      //   10: astore_2
      //   11: aload_2
      //   12: ifnull +22 -> 34
      //   15: aload_0
      //   16: getfield 56	com/clean/spaceplus/base/utils/system/b$a:b	Landroid/content/pm/PackageManager;
      //   19: aload_1
      //   20: iconst_0
      //   21: invokevirtual 62	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   24: astore_1
      //   25: aload_1
      //   26: ifnull +8 -> 34
      //   29: aload_0
      //   30: aload_1
      //   31: invokespecial 64	com/clean/spaceplus/base/utils/system/b$a:a	(Landroid/content/pm/PackageInfo;)V
      //   34: aload_0
      //   35: monitorexit
      //   36: return
      //   37: astore_1
      //   38: aload_1
      //   39: invokevirtual 68	java/lang/Exception:printStackTrace	()V
      //   42: aconst_null
      //   43: astore_1
      //   44: goto -19 -> 25
      //   47: astore_1
      //   48: aload_0
      //   49: monitorexit
      //   50: aload_1
      //   51: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	52	0	this	a
      //   0	52	1	paramString	String
      //   10	2	2	localList	List
      // Exception table:
      //   from	to	target	type
      //   15	25	37	java/lang/Exception
      //   6	11	47	finally
      //   15	25	47	finally
      //   29	34	47	finally
      //   34	36	47	finally
      //   38	42	47	finally
      //   48	50	47	finally
    }
    
    private void d(String paramString)
    {
      a(paramString);
      c(paramString);
    }
    
    public int a(int paramInt, Object paramObject1, Object paramObject2)
    {
      if (paramInt == MonitorManager.d)
      {
        paramObject1 = (Intent)paramObject2;
        paramObject2 = paramObject1.getData().getSchemeSpecificPart();
        if (paramObject1.getBooleanExtra("android.intent.extra.REPLACING", false)) {
          d(paramObject2);
        }
      }
      boolean bool;
      do
      {
        do
        {
          return 0;
          c(paramObject2);
          return 0;
        } while (paramInt != MonitorManager.e);
        paramObject1 = (Intent)paramObject2;
        bool = paramObject1.getBooleanExtra("android.intent.extra.REPLACING", false);
        paramObject1 = paramObject1.getData().getSchemeSpecificPart();
      } while (bool);
      a(paramObject1);
      return 0;
    }
    
    public List<PackageInfo> a()
    {
      try
      {
        this.h = b.a(b.this, this.b, 0);
        if ((this.h != null) && (e.a().booleanValue())) {
          NLog.d(b.f(), "PMCacheableWrapper getInstalledPackagesNoThrow mPkgList size = " + this.h.size(), new Object[0]);
        }
        this.g = this.f;
        ArrayList localArrayList = null;
        if (this.h != null)
        {
          localArrayList = new ArrayList();
          localArrayList.addAll(this.h);
        }
        return localArrayList;
      }
      finally {}
    }
    
    public void a(String paramString)
    {
      if (paramString != null) {}
      for (;;)
      {
        try
        {
          if (this.h != null)
          {
            Iterator localIterator = this.h.iterator();
            if (!localIterator.hasNext()) {
              break label82;
            }
            PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
            if ((localPackageInfo == null) || (!paramString.equals(localPackageInfo.packageName))) {
              continue;
            }
            paramString = localPackageInfo;
            if (paramString != null) {
              this.h.remove(paramString);
            }
          }
          return;
        }
        finally {}
        label82:
        paramString = null;
      }
    }
  }
  
  private class b
  {
    PackageManager b = b.this.a.getPackageManager();
    
    private b() {}
    
    public List<PackageInfo> a()
    {
      return b.a(b.this, this.b, 0);
    }
    
    public void a(String paramString) {}
    
    public ProviderInfo[] a(Context paramContext, String paramString)
    {
      try
      {
        paramContext = this.b.getPackageInfo(paramString, 8).providers;
        return paramContext;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public List<PackageInfo> b()
    {
      Object localObject = b.a(b.this, this.b, 0);
      if (localObject == null) {
        return null;
      }
      LinkedList localLinkedList = new LinkedList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (b.a(localPackageInfo.applicationInfo)) {
          localLinkedList.addFirst(localPackageInfo);
        } else {
          localLinkedList.addLast(localPackageInfo);
        }
      }
      return localLinkedList;
    }
    
    public boolean b(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      try
      {
        boolean bool = b.b(this.b.getPackageInfo(paramString, 0).applicationInfo);
        return bool;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      return false;
    }
    
    public List<PackageInfo> c()
    {
      Object localObject = b.a(b.this, this.b, 0);
      if (localObject == null) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localArrayList.add((PackageInfo)((Iterator)localObject).next());
      }
      return localArrayList;
    }
    
    public List<String> d()
    {
      Object localObject = b.a(b.this, this.b, 0);
      if ((localObject == null) || (((List)localObject).isEmpty())) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((localPackageInfo != null) && (b.a(localPackageInfo.applicationInfo))) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
      return localArrayList;
    }
  }
}
