package com.cmcm.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.adsdk.CMAdManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class j
{
  private static j a = new j();
  private Context b = CMAdManager.getContext();
  private PackageManager c = this.b.getPackageManager();
  private List<PackageInfo> d;
  private Object e = new Object();
  
  public j() {}
  
  public static j a()
  {
    try
    {
      j localJ = a;
      return localJ;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  private List<PackageInfo> a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 42	com/cmcm/utils/j:e	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 48	com/cmcm/utils/j:d	Ljava/util/List;
    //   11: ifnonnull +15 -> 26
    //   14: aload_0
    //   15: aload_0
    //   16: getfield 40	com/cmcm/utils/j:c	Landroid/content/pm/PackageManager;
    //   19: iload_1
    //   20: invokevirtual 53	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   23: putfield 48	com/cmcm/utils/j:d	Ljava/util/List;
    //   26: aload_2
    //   27: monitorexit
    //   28: aload_0
    //   29: getfield 48	com/cmcm/utils/j:d	Ljava/util/List;
    //   32: areturn
    //   33: astore_3
    //   34: aload_2
    //   35: monitorexit
    //   36: aload_3
    //   37: athrow
    //   38: astore_2
    //   39: goto -11 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	this	j
    //   0	42	1	paramInt	int
    //   38	1	2	localException	Exception
    //   33	4	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   7	26	33	finally
    //   26	28	33	finally
    //   34	36	33	finally
    //   0	7	38	java/lang/Exception
    //   36	38	38	java/lang/Exception
  }
  
  public List<String> a(boolean paramBoolean)
  {
    Object localObject = a(0);
    ArrayList localArrayList = new ArrayList();
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((paramBoolean) || (Commons.isUserApp(localPackageInfo.applicationInfo))) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
    }
    return localArrayList;
  }
  
  public void a(String paramString)
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
  
  public void a(String paramString, Context paramContext)
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
