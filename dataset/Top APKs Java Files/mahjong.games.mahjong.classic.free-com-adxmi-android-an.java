package com.adxmi.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.format.Time;
import java.util.ArrayList;
import java.util.List;

public class an
{
  public static final void a(Context paramContext, final av paramAv, final int paramInt)
  {
    ds.execute(new Runnable()
    {
      public void run()
      {
        an.b(this.cI, paramAv, paramInt);
      }
    });
  }
  
  public static final void a(Context paramContext, final String paramString)
  {
    ds.execute(new Runnable()
    {
      public void run()
      {
        an.b(this.cI, paramString);
      }
    });
  }
  
  public static int b(Context paramContext, String paramString)
  {
    List localList = r(paramContext);
    if ((localList != null) && (localList.contains(paramString))) {
      return 2;
    }
    return new am(paramContext, paramString, 2).N();
  }
  
  public static void b(Context paramContext, av paramAv, int paramInt) {}
  
  public static int q(Context paramContext)
  {
    int i = 0;
    try
    {
      localObject = new Time();
      ((Time)localObject).setToNow();
      localObject = ((Time)localObject).format("%Y-%m-%d");
      if (((String)localObject).equals(bi.a(paramContext, ab.z(), ""))) {
        return 2;
      }
      bi.a(paramContext, ab.z(), (String)localObject, 86400000L);
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        Object localObject;
        ArrayList localArrayList;
        StringBuilder localStringBuilder1;
        StringBuilder localStringBuilder2;
      }
    }
    localObject = al.p(paramContext).M();
    if (localObject == null)
    {
      localObject = new ArrayList();
      localArrayList = new ArrayList();
      localStringBuilder1 = new StringBuilder();
      localStringBuilder2 = new StringBuilder();
      for (;;)
      {
        try
        {
          localList = paramContext.getPackageManager().getInstalledPackages(0);
          int j = localList.size();
          if (i >= j) {
            break;
          }
        }
        catch (Throwable localThrowable1)
        {
          try
          {
            List localList;
            PackageInfo localPackageInfo;
            String str;
            if (!be.I(localStringBuilder1.toString()))
            {
              i = new am(paramContext, localStringBuilder1.toString(), 1).N();
              if (((i == 0) && (localArrayList.size() > 0) && (al.p(paramContext).b(localArrayList))) && (i != 1)) {
                return i;
              }
              new am(paramContext, localStringBuilder1.toString(), 1).N();
              return i;
            }
          }
          catch (Throwable paramContext) {}
          return -1;
        }
        try
        {
          localPackageInfo = (PackageInfo)localList.get(i);
          str = localPackageInfo.packageName;
          if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
          {
            localStringBuilder2.append(localPackageInfo.packageName);
            if (i != localList.size() - 1) {
              localStringBuilder2.append("|");
            }
            if (!((List)localObject).contains(str))
            {
              localStringBuilder1.append(localPackageInfo.packageName);
              if (i != localList.size() - 1) {
                localStringBuilder1.append("|");
              }
              localArrayList.add(str);
            }
          }
        }
        catch (Throwable localThrowable3)
        {
          continue;
        }
        i += 1;
      }
    }
    return i;
  }
  
  private static final List r(Context paramContext)
  {
    try
    {
      paramContext = au.v(paramContext).Q();
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
}
