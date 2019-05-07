package com.cmcm.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.adsdk.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class y
{
  private static y ﹶʻ = new y();
  private Object ˋﾞ = new Object();
  private Context ᴵˏ = b.ﹶʻ();
  private PackageManager ᵎʾ = this.ᴵˏ.getPackageManager();
  private List ﾞʽ;
  
  public y() {}
  
  public static y ﹶʻ()
  {
    try
    {
      y localY = ﹶʻ;
      return localY;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  private List ﹶʻ(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 40	com/cmcm/utils/y:ˋﾞ	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 46	com/cmcm/utils/y:ﾞʽ	Ljava/util/List;
    //   11: ifnonnull +15 -> 26
    //   14: aload_0
    //   15: aload_0
    //   16: getfield 38	com/cmcm/utils/y:ᵎʾ	Landroid/content/pm/PackageManager;
    //   19: iload_1
    //   20: invokevirtual 51	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   23: putfield 46	com/cmcm/utils/y:ﾞʽ	Ljava/util/List;
    //   26: aload_2
    //   27: monitorexit
    //   28: aload_0
    //   29: getfield 46	com/cmcm/utils/y:ﾞʽ	Ljava/util/List;
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
    //   0	42	0	this	y
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
  
  public List ﹶʻ(boolean paramBoolean)
  {
    Object localObject2 = ﹶʻ(0);
    if ((localObject2 == null) || (((List)localObject2).isEmpty())) {
      return null;
    }
    synchronized (this.ˋﾞ)
    {
      Object localObject3 = new ArrayList((Collection)localObject2);
      ArrayList localArrayList = new ArrayList();
      if ((localObject2 != null) && (((List)localObject2).size() > 0))
      {
        localObject2 = ((List)localObject3).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (PackageInfo)((Iterator)localObject2).next();
          if ((paramBoolean) || (a.ﹶʻ(((PackageInfo)localObject3).applicationInfo))) {
            localArrayList.add(((PackageInfo)localObject3).packageName);
          }
        }
      }
    }
    return localList;
  }
  
  public void ﹶʻ(String paramString)
  {
    for (;;)
    {
      int i;
      synchronized (this.ˋﾞ)
      {
        if (this.ﾞʽ != null)
        {
          i = 0;
          if (i < this.ﾞʽ.size())
          {
            if (!((PackageInfo)this.ﾞʽ.get(i)).packageName.equals(paramString)) {
              break label71;
            }
            this.ﾞʽ.remove(i);
          }
        }
        return;
      }
      label71:
      i += 1;
    }
  }
  
  public void ﹶʻ(String paramString, Context paramContext)
  {
    for (;;)
    {
      try
      {
        int i;
        synchronized (this.ˋﾞ)
        {
          if (this.ﾞʽ != null)
          {
            paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
            i = 0;
            if (i < this.ﾞʽ.size())
            {
              if (((PackageInfo)this.ﾞʽ.get(i)).packageName.equals(paramString)) {
                this.ﾞʽ.remove(i);
              }
            }
            else {
              this.ﾞʽ.add(paramContext);
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
