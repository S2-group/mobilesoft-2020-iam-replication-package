package com.cmcm.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.adsdk.CMAdManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ad
{
  private static ad ˊᵎ = new ad();
  private PackageManager ʻˊ = this.ˈˋ.getPackageManager();
  private Context ˈˋ = CMAdManager.getContext();
  private Object ˊـ = new Object();
  private List ᵎᴵ;
  
  public ad() {}
  
  public static ad ˊᵎ()
  {
    try
    {
      ad localAd = ˊᵎ;
      return localAd;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  private List ˊᵎ(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 41	com/cmcm/b/ad:ˊـ	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 47	com/cmcm/b/ad:ᵎᴵ	Ljava/util/List;
    //   11: ifnonnull +15 -> 26
    //   14: aload_0
    //   15: aload_0
    //   16: getfield 39	com/cmcm/b/ad:ʻˊ	Landroid/content/pm/PackageManager;
    //   19: iload_1
    //   20: invokevirtual 52	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   23: putfield 47	com/cmcm/b/ad:ᵎᴵ	Ljava/util/List;
    //   26: aload_2
    //   27: monitorexit
    //   28: aload_0
    //   29: getfield 47	com/cmcm/b/ad:ᵎᴵ	Ljava/util/List;
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
    //   0	42	0	this	ad
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
  
  public List ˊᵎ(boolean paramBoolean)
  {
    Object localObject2 = ˊᵎ(0);
    if ((localObject2 == null) || (((List)localObject2).isEmpty())) {
      return null;
    }
    synchronized (this.ˊـ)
    {
      Object localObject3 = new ArrayList((Collection)localObject2);
      ArrayList localArrayList = new ArrayList();
      if ((localObject2 != null) && (((List)localObject2).size() > 0))
      {
        localObject2 = ((List)localObject3).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (PackageInfo)((Iterator)localObject2).next();
          if ((paramBoolean) || (b.ˊᵎ(((PackageInfo)localObject3).applicationInfo))) {
            localArrayList.add(((PackageInfo)localObject3).packageName);
          }
        }
      }
    }
    return localList;
  }
  
  public void ˊᵎ(String paramString)
  {
    for (;;)
    {
      int i;
      synchronized (this.ˊـ)
      {
        if (this.ᵎᴵ != null)
        {
          i = 0;
          if (i < this.ᵎᴵ.size())
          {
            if (!((PackageInfo)this.ᵎᴵ.get(i)).packageName.equals(paramString)) {
              break label71;
            }
            this.ᵎᴵ.remove(i);
          }
        }
        return;
      }
      label71:
      i += 1;
    }
  }
  
  public void ˊᵎ(String paramString, Context paramContext)
  {
    for (;;)
    {
      try
      {
        int i;
        synchronized (this.ˊـ)
        {
          if (this.ᵎᴵ != null)
          {
            paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
            i = 0;
            if (i < this.ᵎᴵ.size())
            {
              if (((PackageInfo)this.ᵎᴵ.get(i)).packageName.equals(paramString)) {
                this.ᵎᴵ.remove(i);
              }
            }
            else {
              this.ᵎᴵ.add(paramContext);
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
