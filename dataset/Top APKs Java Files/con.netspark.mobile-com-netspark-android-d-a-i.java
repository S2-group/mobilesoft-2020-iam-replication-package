package com.netspark.android.d.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.netspark.android.apps.al;
import com.netspark.android.d.a;
import com.netspark.android.netsvpn.dx;
import java.util.Iterator;
import java.util.List;

public class i
{
  public static int a(Context paramContext, a paramA)
  {
    Object localObject2 = "";
    boolean bool = false;
    int i;
    try
    {
      Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(al.a(4096)).iterator();
      i = 0;
      paramContext = (Context)localObject2;
      for (;;)
      {
        Object localObject1 = paramContext;
        j = i;
        try
        {
          if (!localIterator.hasNext()) {
            break label247;
          }
          localObject2 = (PackageInfo)localIterator.next();
          if (((PackageInfo)localObject2).requestedPermissions != null)
          {
            String[] arrayOfString = ((PackageInfo)localObject2).requestedPermissions;
            int n = arrayOfString.length;
            int k = 0;
            for (;;)
            {
              if (k < n)
              {
                localObject1 = paramContext;
                int m = i;
                j = i;
                try
                {
                  if (arrayOfString[k].equals("android.permission.ACCESS_SUPERUSER"))
                  {
                    m = i + 1;
                    j = m;
                    localObject1 = new StringBuilder();
                    j = m;
                    ((StringBuilder)localObject1).append(paramContext);
                    j = m;
                    ((StringBuilder)localObject1).append(((PackageInfo)localObject2).packageName);
                    j = m;
                    ((StringBuilder)localObject1).append("-#-");
                    j = m;
                    localObject1 = ((StringBuilder)localObject1).toString();
                  }
                  k += 1;
                  paramContext = (Context)localObject1;
                  i = m;
                }
                catch (Throwable localThrowable1)
                {
                  i = j;
                }
              }
            }
          }
        }
        catch (Throwable localThrowable2) {}
      }
      localObject2 = new StringBuilder();
    }
    catch (Throwable localThrowable3)
    {
      i = 0;
      paramContext = (Context)localObject2;
    }
    ((StringBuilder)localObject2).append("TestForSuperSuPermissionOnManifest err: ");
    ((StringBuilder)localObject2).append(localThrowable3);
    dx.k(((StringBuilder)localObject2).toString());
    int j = i;
    Context localContext = paramContext;
    label247:
    if (j > 3) {
      i = 2;
    } else if (j > 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (j > 0) {
      bool = true;
    }
    paramContext = new StringBuilder();
    paramContext.append("got ACCESS_SUPERUSER_STRING for ");
    paramContext.append(j);
    paramContext.append(" times, packages:");
    paramContext.append(localContext);
    paramA.a(bool, paramContext.toString(), i);
    return j;
  }
  
  public static boolean a(a paramA)
  {
    return new j().a(paramA, true);
  }
  
  public static final int b(Context paramContext, a paramA)
  {
    Object localObject1 = "";
    boolean bool = false;
    for (;;)
    {
      int j;
      int i;
      try
      {
        paramContext = paramContext.getPackageManager().queryBroadcastReceivers(new Intent().setAction("eu.chainfire.supersu.action.HIDE_OVERLAYS").addCategory("android.intent.category.INFO"), 131072);
        if ((paramContext != null) && (paramContext.size() > 0))
        {
          j = paramContext.size();
          localObject2 = localObject1;
          try
          {
            localIterator = paramContext.iterator();
            paramContext = (Context)localObject1;
          }
          catch (Throwable paramContext)
          {
            try
            {
              Iterator localIterator;
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append(paramContext);
              ((StringBuilder)localObject2).append(((ResolveInfo)localObject1).activityInfo.name);
              ((StringBuilder)localObject2).append("-#-");
              localObject1 = ((StringBuilder)localObject2).toString();
              paramContext = (Context)localObject1;
            }
            catch (Throwable localThrowable) {}
            paramContext = paramContext;
            localObject1 = localObject2;
            i = j;
          }
          localObject2 = paramContext;
          localObject1 = paramContext;
          i = j;
          if (!localIterator.hasNext()) {
            break label203;
          }
          localObject2 = paramContext;
          localObject1 = (ResolveInfo)localIterator.next();
        }
        else
        {
          i = 0;
        }
      }
      catch (Throwable paramContext)
      {
        i = 0;
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("TestForSuperSuHideOverlayAction err: ");
        ((StringBuilder)localObject2).append(paramContext);
        dx.k(((StringBuilder)localObject2).toString());
      }
      label203:
      if (i > 3) {
        j = 2;
      } else if (i > 0) {
        j = 1;
      } else {
        j = 0;
      }
      if (i > 0) {
        bool = true;
      }
      paramContext = new StringBuilder();
      paramContext.append("got HIDE_OVERLAYS for ");
      paramContext.append(i);
      paramContext.append(" times, packages:");
      paramContext.append((String)localObject1);
      paramA.a(bool, paramContext.toString(), j);
      return i;
    }
  }
}
