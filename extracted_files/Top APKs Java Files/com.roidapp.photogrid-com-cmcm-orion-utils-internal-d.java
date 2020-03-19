package com.cmcm.orion.utils.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.orion.adsdk.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class d
{
  private static d a = new d();
  private Context b = e.a();
  private PackageManager c = this.b.getPackageManager();
  private List<PackageInfo> d;
  private Object e = new Object();
  
  public d() {}
  
  public static d a()
  {
    try
    {
      d localD = a;
      return localD;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  private List<PackageInfo> b()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 41	com/cmcm/orion/utils/internal/d:e	Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 47	com/cmcm/orion/utils/internal/d:d	Ljava/util/List;
    //   11: ifnonnull +15 -> 26
    //   14: aload_0
    //   15: aload_0
    //   16: getfield 39	com/cmcm/orion/utils/internal/d:c	Landroid/content/pm/PackageManager;
    //   19: iconst_0
    //   20: invokevirtual 53	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   23: putfield 47	com/cmcm/orion/utils/internal/d:d	Ljava/util/List;
    //   26: aload_1
    //   27: monitorexit
    //   28: aload_0
    //   29: getfield 47	com/cmcm/orion/utils/internal/d:d	Ljava/util/List;
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
    //   0	42	0	this	d
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
  
  public final List<String> a(boolean paramBoolean)
  {
    for (;;)
    {
      int i;
      try
      {
        Object localObject2 = b();
        synchronized (this.e)
        {
          ArrayList localArrayList = new ArrayList();
          if ((localObject2 == null) || (((List)localObject2).size() <= 0)) {
            break label137;
          }
          localObject2 = ((List)localObject2).iterator();
          if (!((Iterator)localObject2).hasNext()) {
            break label137;
          }
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
          if (!paramBoolean)
          {
            ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
            if (((localApplicationInfo.flags & 0x1) == 0) && ((localApplicationInfo.flags & 0x80) == 0))
            {
              i = 1;
              break label142;
            }
          }
          else
          {
            localArrayList.add(localPackageInfo.packageName);
          }
        }
        i = 0;
      }
      catch (Exception localException)
      {
        return null;
      }
      break label142;
      label137:
      return localList;
      label142:
      if (i == 0) {}
    }
  }
  
  public final void a(String paramString)
  {
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
            if (!((PackageInfo)this.d.get(i)).packageName.equals(paramString)) {
              break label71;
            }
            this.d.remove(i);
          }
        }
        return;
      }
      label71:
      i += 1;
    }
  }
  
  public final void a(String paramString, Context paramContext)
  {
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
              if (((PackageInfo)this.d.get(i)).packageName.equals(paramString)) {
                this.d.remove(i);
              }
            }
            else {
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
}
