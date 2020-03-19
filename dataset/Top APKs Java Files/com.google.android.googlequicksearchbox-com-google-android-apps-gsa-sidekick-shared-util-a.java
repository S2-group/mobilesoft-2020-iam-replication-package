package com.google.android.apps.gsa.sidekick.shared.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.common.collect.Sets;
import com.google.common.collect.cj;
import com.google.common.collect.cl;
import com.google.l.b.c.ee;
import com.google.l.b.c.el;
import com.google.l.b.c.o;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class a
{
  public static cj<ee> a(el paramEl, Context paramContext)
  {
    Object localObject1 = paramContext.getPackageManager();
    paramContext = Sets.newHashSet();
    localObject1 = ((PackageManager)localObject1).getInstalledPackages(0).iterator();
    while (((Iterator)localObject1).hasNext()) {
      paramContext.add(((PackageInfo)((Iterator)localObject1).next()).packageName);
    }
    localObject1 = new cl();
    if (paramEl != null)
    {
      paramEl = paramEl.ryc;
      int n = paramEl.length;
      int i = 0;
      int j = 0;
      if (i < n)
      {
        Object localObject2 = paramEl[i];
        if ((localObject2.rwz.aAn & 0x1) != 0) {}
        for (int m = 1;; m = 0)
        {
          int k = j;
          if (m != 0)
          {
            k = j;
            if (paramContext.contains(localObject2.rwz.bvZ))
            {
              ((cl)localObject1).ct(localObject2);
              k = j + 1;
              if (k == 4) {
                break label172;
              }
            }
          }
          i += 1;
          j = k;
          break;
        }
      }
    }
    label172:
    return ((cl)localObject1).bEm();
  }
}
