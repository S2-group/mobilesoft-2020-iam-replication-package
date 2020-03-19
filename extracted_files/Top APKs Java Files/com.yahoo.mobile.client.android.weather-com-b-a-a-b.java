package com.b.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class b
{
  private static PermissionInfo a(PermissionInfo[] paramArrayOfPermissionInfo, String paramString)
  {
    int j = paramArrayOfPermissionInfo.length;
    int i = 0;
    while (i < j)
    {
      PermissionInfo localPermissionInfo = paramArrayOfPermissionInfo[i];
      if (paramString.equals(localPermissionInfo.name)) {
        return localPermissionInfo;
      }
      i += 1;
    }
    return null;
  }
  
  public static HashMap<PackageInfo, ArrayList<a>> a(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    PackageManager localPackageManager = paramContext.getPackageManager();
    for (;;)
    {
      Object localObject1;
      Iterator localIterator;
      int i;
      int k;
      int m;
      for (;;)
      {
        try
        {
          localObject1 = localPackageManager.getPackageInfo(paramContext.getPackageName(), 4096);
          localIterator = localPackageManager.getInstalledPackages(4096).iterator();
          if (localIterator.hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
            if ((!localPackageInfo.packageName.equals(((PackageInfo)localObject1).packageName)) && (localPackageInfo.permissions != null))
            {
              ArrayList localArrayList = new ArrayList();
              PermissionInfo[] arrayOfPermissionInfo = localPackageInfo.permissions;
              int j = arrayOfPermissionInfo.length;
              i = 0;
              if (i < j)
              {
                PermissionInfo localPermissionInfo1 = arrayOfPermissionInfo[i];
                PermissionInfo localPermissionInfo2 = a(((PackageInfo)localObject1).permissions, localPermissionInfo1.name);
                if (localPermissionInfo2 == null) {
                  break label422;
                }
                a localA = new a(localPermissionInfo1);
                k = localPermissionInfo1.protectionLevel & 0xF;
                m = localPermissionInfo2.protectionLevel & 0xF;
                if (k < m)
                {
                  localA.b = true;
                  break label391;
                }
                if (k <= m) {
                  break label391;
                }
                localA.c = true;
                break label391;
                label202:
                localA.e = true;
                try
                {
                  String str1 = c.a(paramContext);
                  String str2 = localPackageInfo.packageName;
                  try
                  {
                    boolean bool = str1.equals(c.a(paramContext, str2));
                    if (bool) {
                      try
                      {
                        localA.e = false;
                      }
                      catch (Exception localException1) {}
                    }
                  }
                  catch (Exception localException2) {}
                  Log.e("PermissionUtils", "Exception comparing signatures", localException3);
                }
                catch (Exception localException3) {}
                if (!TextUtils.equals(localPermissionInfo1.loadDescription(localPackageManager), localPermissionInfo2.loadDescription(localPackageManager))) {
                  localA.d = true;
                } else if (!TextUtils.equals(localPermissionInfo1.loadLabel(localPackageManager), localPermissionInfo2.loadLabel(localPackageManager))) {
                  localA.d = true;
                }
                localArrayList.add(localA);
                break label422;
              }
              Object localObject2 = localObject1;
              localObject1 = localObject2;
              if (localArrayList.size() > 0)
              {
                localHashMap.put(localPackageInfo, localArrayList);
                localObject1 = localObject2;
              }
            }
            break;
          }
          return localHashMap;
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          throw new RuntimeException("We do not exist?!?", paramContext);
        }
        label391:
        if ((k != 2) && (k != 3)) {}
        if (m != 2) {
          if (m != 3) {
            break label202;
          }
        }
      }
      label422:
      i += 1;
    }
  }
}
