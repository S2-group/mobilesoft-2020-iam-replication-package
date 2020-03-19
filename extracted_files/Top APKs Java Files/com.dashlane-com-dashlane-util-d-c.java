package com.dashlane.util.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.dashlane.util.bv;
import com.dashlane.util.cc;
import d.g.b.i;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class c
{
  public static final c a = new c();
  
  private c() {}
  
  public static final List<b> a(Context paramContext)
  {
    i.b(paramContext, "context");
    ArrayList localArrayList = new ArrayList(a.a().values());
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    while (paramContext.hasNext())
    {
      localObject1 = ((ApplicationInfo)paramContext.next()).packageName;
      if (cc.a((CharSequence)localObject1))
      {
        i.a(localObject1, "packageName");
        localObject1 = bv.a((String)localObject1);
        if (localObject1 != null)
        {
          localArrayList.remove(localObject1);
          localArrayList.add(0, localObject1);
        }
      }
    }
    Object localObject1 = (Iterable)localArrayList;
    paramContext = new HashSet();
    localArrayList = new ArrayList();
    localObject1 = ((Iterable)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = ((Iterator)localObject1).next();
      if (paramContext.add(((b)localObject2).b)) {
        localArrayList.add(localObject2);
      }
    }
    return (List)localArrayList;
  }
}
