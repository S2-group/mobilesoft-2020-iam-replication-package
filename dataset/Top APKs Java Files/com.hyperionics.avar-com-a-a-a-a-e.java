package com.a.a.a.a;

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

public final class e
{
  private static final b b = new b(c.a, c.a);
  HashMap a;
  private Context c;
  private HashMap d;
  
  public e(Context paramContext)
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
        Object localObject5;
        try
        {
          Object localObject2 = this.c.getPackageManager().getApplicationInfo(((PackageInfo)localObject1).packageName, 128);
          if ((localObject2 == null) || (((ApplicationInfo)localObject2).metaData == null) || (!((ApplicationInfo)localObject2).metaData.containsKey("open.dictionary.api.minVersionCode")) || (4 < ((ApplicationInfo)localObject2).metaData.getInt("open.dictionary.api.minVersionCode")) || (((ApplicationInfo)localObject2).metaData.getInt("open.dictionary.api.versionCode") < 1)) {
            break;
          }
          localObject1 = new a(this.c, this, (ApplicationInfo)localObject2);
          if (((a)localObject1).b())
          {
            i = 2;
            a[] arrayOfA = new a[i];
            arrayOfA[0] = localObject1;
            if (((a)localObject1).b()) {
              arrayOfA[1] = ((a)localObject1).c();
            }
            int j = arrayOfA.length;
            i = 0;
            if (i >= j) {
              break;
            }
            localObject4 = arrayOfA[i];
            localObject2 = (HashSet)this.d.get(((a)localObject4).a());
            localObject1 = localObject2;
            if (localObject2 == null)
            {
              localObject2 = this.d;
              localObject5 = ((a)localObject4).a();
              localObject1 = new HashSet();
              ((HashMap)localObject2).put(localObject5, localObject1);
            }
            ((HashSet)localObject1).add(localObject4);
            if (((a)localObject4).d()) {
              break label322;
            }
            i += 1;
            continue;
          }
        }
        catch (Exception localException)
        {
          Log.e("Open Dictionary API", "[" + this.c.getPackageName() + "] " + "Can't get application info of \"" + ((PackageInfo)localObject1).packageName + "\"", localException);
        }
        int i = 1;
        continue;
        label322:
        Object localObject4 = ((a)localObject4).e();
        Object localObject3 = (HashSet)this.a.get(((d)localObject4).a());
        localObject1 = localObject3;
        if (localObject3 == null)
        {
          localObject3 = this.a;
          localObject5 = ((d)localObject4).a();
          localObject1 = new HashSet();
          ((HashMap)localObject3).put(localObject5, localObject1);
        }
        ((HashSet)localObject1).add(localObject4);
      }
    }
  }
  
  public HashSet a()
  {
    return a(b);
  }
  
  public HashSet a(b paramB)
  {
    if (paramB == null) {
      throw new IllegalArgumentException("\"dir\" param must not be null");
    }
    HashSet localHashSet = new HashSet();
    if ((c.a.equals(paramB.a)) || (c.a.equals(paramB.b))) {
      if ((c.a.equals(paramB.a)) && (c.a.equals(paramB.b))) {
        paramB = this.d.values().iterator();
      }
    }
    while (paramB.hasNext())
    {
      localHashSet.addAll((HashSet)paramB.next());
      continue;
      Map.Entry localEntry;
      if (c.a.equals(paramB.a))
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
