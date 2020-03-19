package com.clean.spaceplus.cleansdk.base.utils.system;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.clean.spaceplus.cleansdk.app.SpaceApplication;
import com.clean.spaceplus.cleansdk.base.utils.monitor.MonitorManager;
import com.clean.spaceplus.cleansdk.base.utils.monitor.MonitorManager.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import u.a.c.f;

public class b
{
  private static final String b = b.class.getSimpleName();
  private static b c = new b();
  Context a = SpaceApplication.getInstance().getContext();
  private b d = null;
  
  private b()
  {
    if (!f.c())
    {
      this.d = new a(null);
      com.hawkclean.framework.a.b.a(b, " not service process: new PMCacheableWrapper() ", new Object[0]);
      return;
    }
    com.hawkclean.framework.a.b.a(b, "  service process: new PMWrapper() ", new Object[0]);
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
    //   2: getstatic 75	com/clean/spaceplus/cleansdk/junk/a/c/a/b:a	Ljava/lang/String;
    //   5: ldc 77
    //   7: iconst_0
    //   8: anewarray 4	java/lang/Object
    //   11: invokestatic 64	com/hawkclean/framework/a/b:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   14: aconst_null
    //   15: astore_3
    //   16: aload_1
    //   17: iload_2
    //   18: invokevirtual 83	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   21: astore_1
    //   22: aload_1
    //   23: ifnull +37 -> 60
    //   26: getstatic 75	com/clean/spaceplus/cleansdk/junk/a/c/a/b:a	Ljava/lang/String;
    //   29: new 85	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   36: ldc 88
    //   38: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_1
    //   42: invokeinterface 98 1 0
    //   47: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   50: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: iconst_0
    //   54: anewarray 4	java/lang/Object
    //   57: invokestatic 64	com/hawkclean/framework/a/b:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_1
    //   63: areturn
    //   64: astore_1
    //   65: getstatic 75	com/clean/spaceplus/cleansdk/junk/a/c/a/b:a	Ljava/lang/String;
    //   68: new 85	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   75: ldc 106
    //   77: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: aload_1
    //   81: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: iconst_0
    //   88: anewarray 4	java/lang/Object
    //   91: invokestatic 111	com/hawkclean/framework/a/b:c	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   94: aload_3
    //   95: astore_1
    //   96: goto -74 -> 22
    //   99: astore_1
    //   100: aload_0
    //   101: monitorexit
    //   102: aload_1
    //   103: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	104	0	this	b
    //   0	104	1	paramPackageManager	PackageManager
    //   0	104	2	paramInt	int
    //   15	80	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   16	22	64	java/lang/Exception
    //   2	14	99	finally
    //   16	22	99	finally
    //   26	60	99	finally
    //   65	94	99	finally
  }
  
  public List<PackageInfo> b()
  {
    return this.d.a();
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
    private void b(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_1
      //   3: ifnull +31 -> 34
      //   6: aload_0
      //   7: getfield 35	com/clean/spaceplus/cleansdk/base/utils/system/b$a:h	Ljava/util/List;
      //   10: astore_2
      //   11: aload_2
      //   12: ifnull +22 -> 34
      //   15: aload_0
      //   16: getfield 55	com/clean/spaceplus/cleansdk/base/utils/system/b$a:b	Landroid/content/pm/PackageManager;
      //   19: aload_1
      //   20: iconst_0
      //   21: invokevirtual 61	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   24: astore_1
      //   25: aload_1
      //   26: ifnull +8 -> 34
      //   29: aload_0
      //   30: aload_1
      //   31: invokespecial 63	com/clean/spaceplus/cleansdk/base/utils/system/b$a:a	(Landroid/content/pm/PackageInfo;)V
      //   34: aload_0
      //   35: monitorexit
      //   36: return
      //   37: astore_1
      //   38: aload_1
      //   39: invokevirtual 67	java/lang/Exception:printStackTrace	()V
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
    
    private void c(String paramString)
    {
      a(paramString);
      b(paramString);
    }
    
    public int a(int paramInt, Object paramObject1, Object paramObject2)
    {
      if (paramInt == MonitorManager.e)
      {
        paramObject1 = (Intent)paramObject2;
        paramObject2 = paramObject1.getData().getSchemeSpecificPart();
        if (paramObject1.getBooleanExtra("android.intent.extra.REPLACING", false)) {
          c(paramObject2);
        }
      }
      boolean bool;
      do
      {
        do
        {
          return 0;
          b(paramObject2);
          return 0;
        } while (paramInt != MonitorManager.f);
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
        if (this.h == null)
        {
          this.h = b.a(b.this, this.b, 0);
          if (this.h != null) {
            com.hawkclean.framework.a.b.a(b.c(), "PMCacheableWrapper getInstalledPackagesNoThrow mPkgList size = " + this.h.size(), new Object[0]);
          }
          this.g = this.f;
        }
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
  }
}
