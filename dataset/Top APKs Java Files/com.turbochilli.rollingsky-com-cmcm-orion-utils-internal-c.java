package com.cmcm.orion.utils.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.cmcm.orion.adsdk.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class c
{
  private static c a = new c();
  private Context b = e.a();
  private PackageManager c = this.b.getPackageManager();
  private List<PackageInfo> d;
  private Object e = new Object();
  
  public c() {}
  
  public static c a()
  {
    try
    {
      c localC = a;
      return localC;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final void a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    for (;;)
    {
      int i;
      synchronized (this.e)
      {
        if (this.d != null)
        {
          i = 0;
          if (i < this.d.size())
          {
            PackageInfo localPackageInfo = (PackageInfo)this.d.get(i);
            if ((localPackageInfo == null) || (!paramString.equals(localPackageInfo.packageName))) {
              break label88;
            }
            this.d.remove(i);
          }
        }
        return;
      }
      label88:
      i += 1;
    }
  }
  
  public final void a(String paramString, Context paramContext)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramContext == null)) {
      return;
    }
    for (;;)
    {
      try
      {
        int i;
        synchronized (this.e)
        {
          if (this.d != null)
          {
            paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
            i = 0;
            if (i < this.d.size())
            {
              PackageInfo localPackageInfo = (PackageInfo)this.d.get(i);
              if ((localPackageInfo != null) && (paramString.equals(localPackageInfo.packageName))) {
                this.d.remove(i);
              }
            }
            else
            {
              this.d.add(paramContext);
            }
          }
          else
          {
            return;
          }
        }
        i += 1;
      }
      catch (Exception paramString)
      {
        return;
      }
    }
  }
  
  public final List<String> b()
  {
    try
    {
      Object localObject2 = c();
      synchronized (this.e)
      {
        ArrayList localArrayList = new ArrayList();
        if ((localObject2 != null) && (((List)localObject2).size() > 0))
        {
          localObject2 = ((List)localObject2).iterator();
          if (((Iterator)localObject2).hasNext()) {
            localArrayList.add(((PackageInfo)((Iterator)localObject2).next()).packageName);
          }
        }
      }
    }
    catch (Exception localException)
    {
      return null;
    }
    return localList;
  }
  
  /* Error */
  public final List<PackageInfo> c()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 41	com/cmcm/orion/utils/internal/c:e	Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 51	com/cmcm/orion/utils/internal/c:d	Ljava/util/List;
    //   11: ifnonnull +15 -> 26
    //   14: aload_0
    //   15: aload_0
    //   16: getfield 39	com/cmcm/orion/utils/internal/c:c	Landroid/content/pm/PackageManager;
    //   19: iconst_0
    //   20: invokevirtual 114	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   23: putfield 51	com/cmcm/orion/utils/internal/c:d	Ljava/util/List;
    //   26: aload_1
    //   27: monitorexit
    //   28: aload_0
    //   29: getfield 51	com/cmcm/orion/utils/internal/c:d	Ljava/util/List;
    //   32: areturn
    //   33: astore_2
    //   34: aload_1
    //   35: monitorexit
    //   36: aload_2
    //   37: athrow
    //   38: astore_1
    //   39: goto -11 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	this	c
    //   38	1	1	localException	Exception
    //   33	4	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   7	26	33	finally
    //   26	28	33	finally
    //   34	36	33	finally
    //   0	7	38	java/lang/Exception
    //   36	38	38	java/lang/Exception
  }
}
