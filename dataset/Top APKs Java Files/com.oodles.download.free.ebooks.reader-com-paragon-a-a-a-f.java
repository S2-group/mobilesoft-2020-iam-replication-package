package com.paragon.a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class f
{
  private static final b b = new b(d.a, d.a);
  HashMap<d, HashSet<e>> a;
  private Context c;
  private HashMap<b, HashSet<a>> d;
  
  public f(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("\"ctx\" param must not be null");
    }
    this.c = paramContext;
    this.d = new HashMap();
    this.a = new HashMap();
    b();
  }
  
  private void b()
  {
    Iterator localIterator = this.c.getPackageManager().getInstalledPackages(64).iterator();
    if (localIterator.hasNext())
    {
      Object localObject1 = (PackageInfo)localIterator.next();
      for (;;)
      {
        try
        {
          Object localObject2 = this.c.getPackageManager().getApplicationInfo(((PackageInfo)localObject1).packageName, 128);
          if ((localObject2 == null) || (((ApplicationInfo)localObject2).metaData == null) || (!((ApplicationInfo)localObject2).metaData.containsKey("open.dictionary.api.minVersionCode")) || (2 < ((ApplicationInfo)localObject2).metaData.getInt("open.dictionary.api.minVersionCode")) || (((ApplicationInfo)localObject2).metaData.getInt("open.dictionary.api.versionCode") <= 0)) {
            break;
          }
          localObject1 = new a(this.c, this, (ApplicationInfo)localObject2);
          if (((a)localObject1).a())
          {
            i = 2;
            a[] arrayOfA = new a[i];
            arrayOfA[0] = localObject1;
            if (((a)localObject1).a()) {
              arrayOfA[1] = ((a)localObject1).i;
            }
            int k = arrayOfA.length;
            i = 0;
            if (i >= k) {
              break;
            }
            Object localObject3 = arrayOfA[i];
            localObject2 = (HashSet)this.d.get(((a)localObject3).h);
            localObject1 = localObject2;
            Object localObject4;
            if (localObject2 == null)
            {
              localObject2 = this.d;
              localObject4 = ((a)localObject3).h;
              localObject1 = new HashSet();
              ((HashMap)localObject2).put(localObject4, localObject1);
            }
            ((HashSet)localObject1).add(localObject3);
            if (((a)localObject3).j == null) {
              break label404;
            }
            j = 1;
            if (j != 0)
            {
              localObject3 = ((a)localObject3).j;
              localObject2 = (HashSet)this.a.get(((e)localObject3).d);
              localObject1 = localObject2;
              if (localObject2 == null)
              {
                localObject2 = this.a;
                localObject4 = ((e)localObject3).d;
                localObject1 = new HashSet();
                ((HashMap)localObject2).put(localObject4, localObject1);
              }
              ((HashSet)localObject1).add(localObject3);
            }
            i += 1;
            continue;
          }
        }
        catch (Exception localException)
        {
          Log.e("Open Dictionary API", "[" + this.c.getPackageName() + "] Can't get application info of \"" + ((PackageInfo)localObject1).packageName + "\"", localException);
        }
        int i = 1;
        continue;
        label404:
        int j = 0;
      }
    }
  }
  
  public final HashSet<a> a()
  {
    return a(b);
  }
  
  public final HashSet<a> a(b paramB)
  {
    if (paramB == null) {
      throw new IllegalArgumentException("\"dir\" param must not be null");
    }
    HashSet localHashSet = new HashSet();
    if ((d.a.equals(paramB.a)) || (d.a.equals(paramB.b))) {
      if ((d.a.equals(paramB.a)) && (d.a.equals(paramB.b))) {
        paramB = this.d.values().iterator();
      }
    }
    while (paramB.hasNext())
    {
      localHashSet.addAll((HashSet)paramB.next());
      continue;
      Map.Entry localEntry;
      if (d.a.equals(paramB.a))
      {
        localIterator = this.d.entrySet().iterator();
        while (localIterator.hasNext())
        {
          localEntry = (Map.Entry)localIterator.next();
          if (((b)localEntry.getKey()).b.equals(paramB.b)) {
            localHashSet.addAll((Collection)localEntry.getValue());
          }
        }
      }
      Iterator localIterator = this.d.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        if (((b)localEntry.getKey()).a.equals(paramB.a))
        {
          localHashSet.addAll((Collection)localEntry.getValue());
          continue;
          paramB = (HashSet)this.d.get(paramB);
          if (paramB != null) {
            localHashSet.addAll(paramB);
          }
        }
      }
    }
    return localHashSet;
  }
}
