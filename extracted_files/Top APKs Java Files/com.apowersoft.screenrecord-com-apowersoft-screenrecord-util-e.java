package com.apowersoft.screenrecord.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import com.apowersoft.screenrecord.GlobalApplication;
import com.apowersoft.screenrecord.b.a;
import com.apowersoft.screenrecord.h.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class e
{
  public static int a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 5: 
      return 2160;
    case 4: 
      return 1440;
    case 3: 
      return 1080;
    case 2: 
      return 720;
    case 1: 
      return 480;
    }
    return 360;
  }
  
  public static String a(Context paramContext)
  {
    int i = c.a().A();
    String str = paramContext.getString(2131755308);
    switch (i)
    {
    default: 
      return str;
    case 2: 
      return paramContext.getString(2131755309);
    }
    return paramContext.getString(2131755310);
  }
  
  public static List<String> a()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("360P");
    localArrayList.add("480P");
    if (d.d * d.c <= 409920) {
      return localArrayList;
    }
    localArrayList.add("720P");
    if (d.d * d.c <= 1036800) {
      return localArrayList;
    }
    if (b.a(1080))
    {
      localArrayList.add("1080P");
      if (d.d * d.c <= 2332800) {
        return localArrayList;
      }
      if (b.a(1440))
      {
        localArrayList.add("1440P");
        if (d.d * d.c <= 4147200) {
          return localArrayList;
        }
        if (b.a(2160)) {
          localArrayList.add("2160P");
        }
      }
    }
    return localArrayList;
  }
  
  public static int[] a(Context paramContext, int paramInt)
  {
    int j = c.a().A();
    if (c.a().a == 0)
    {
      i = j;
      if (j != 0) {
        break label74;
      }
      com.apowersoft.a.e.d.c("自动");
      if (paramContext.getResources().getConfiguration().orientation == 2)
      {
        com.apowersoft.a.e.d.c("自动判断现在是横屏！");
        break label72;
      }
      com.apowersoft.a.e.d.c("自动判断现在是竖屏！");
    }
    else
    {
      if (1 != c.a().a) {
        break label72;
      }
    }
    int i = 1;
    break label74;
    label72:
    i = 2;
    label74:
    paramContext = new int[2];
    if (paramInt != 360)
    {
      if (paramInt != 480)
      {
        if (paramInt != 720)
        {
          if (paramInt != 1080)
          {
            if (paramInt != 1440)
            {
              if (paramInt != 2160) {
                return paramContext;
              }
              if (i == 1)
              {
                paramContext[1] = d(2160);
                paramContext[0] = 2160;
                return paramContext;
              }
              paramContext[0] = d(2160);
              paramContext[1] = 2160;
              return paramContext;
            }
            if (i == 1)
            {
              paramContext[1] = d(1440);
              paramContext[0] = 1440;
              return paramContext;
            }
            paramContext[0] = d(1440);
            paramContext[1] = 1440;
            return paramContext;
          }
          if (i == 1)
          {
            paramContext[1] = d(1080);
            paramContext[0] = 1080;
            return paramContext;
          }
          paramContext[0] = d(1080);
          paramContext[1] = 1080;
          return paramContext;
        }
        if (i == 1)
        {
          paramContext[1] = d(720);
          paramContext[0] = 720;
          return paramContext;
        }
        paramContext[0] = d(720);
        paramContext[1] = 720;
        return paramContext;
      }
      if (i == 1)
      {
        paramContext[1] = d(480);
        paramContext[0] = 480;
        return paramContext;
      }
      paramContext[0] = d(480);
      paramContext[1] = 480;
      return paramContext;
    }
    if (i == 1)
    {
      paramContext[1] = d(360);
      paramContext[0] = 360;
      return paramContext;
    }
    paramContext[0] = d(360);
    paramContext[1] = 360;
    return paramContext;
  }
  
  public static int b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 30;
    case 5: 
      return 24;
    case 4: 
      return 25;
    case 3: 
      return 30;
    case 2: 
      return 48;
    case 1: 
      return 50;
    }
    return 60;
  }
  
  public static String b(Context paramContext, int paramInt)
  {
    return (String)c(paramContext).get(paramInt);
  }
  
  public static List<String> b()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("3");
    localArrayList.add("2");
    localArrayList.add("1");
    localArrayList.add("1/2");
    localArrayList.add("1/3");
    return localArrayList;
  }
  
  public static List<String> b(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramContext.getString(2131755308));
    localArrayList.add(paramContext.getString(2131755310));
    localArrayList.add(paramContext.getString(2131755309));
    return localArrayList;
  }
  
  public static float c(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 5.0F;
    case 7: 
      return 1.0F;
    case 6: 
      return 1.5F;
    case 5: 
      return 2.5F;
    case 4: 
      return 4.0F;
    case 3: 
      return 5.0F;
    case 2: 
      return 7.5F;
    case 1: 
      return 8.0F;
    }
    return 12.0F;
  }
  
  public static int c()
  {
    int j = c.a().z();
    int i = 2;
    switch (j)
    {
    case -1: 
    case 0: 
    default: 
      return 2;
    case 3: 
      return 0;
    case 2: 
      return 1;
    case -2: 
      return 3;
    case -3: 
      i = 4;
    }
    return i;
  }
  
  public static List<String> c(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramContext.getString(2131755322));
    localArrayList.add(paramContext.getString(2131755324));
    if (b.a(paramContext, 2)) {
      localArrayList.add(paramContext.getString(2131755323));
    }
    return localArrayList;
  }
  
  public static int d()
  {
    int i = c.a().y();
    if (i != 360)
    {
      if (i != 480)
      {
        if (i != 720)
        {
          if (i != 1080)
          {
            if (i != 1440)
            {
              if (i != 2160) {
                return -1;
              }
              return 5;
            }
            return 4;
          }
          return 3;
        }
        return 2;
      }
      return 1;
    }
    return 0;
  }
  
  private static int d(int paramInt)
  {
    int i = Math.max(d.d, d.c);
    int j = Math.min(d.d, d.c);
    if (c.a().c())
    {
      i = 16;
      j = 9;
    }
    i = paramInt * i / j;
    paramInt = i;
    if (i % 2 != 0) {
      paramInt = i + 1;
    }
    return paramInt;
  }
  
  public static List<String> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramContext.getString(2131755090));
    localArrayList.add(paramContext.getString(2131755091));
    localArrayList.add(paramContext.getString(2131755089));
    localArrayList.add(paramContext.getString(2131755092));
    return localArrayList;
  }
  
  public static String e(Context paramContext)
  {
    switch (c.a().i())
    {
    default: 
      return paramContext.getString(2131755091);
    case 3: 
      return paramContext.getString(2131755092);
    case 2: 
      return paramContext.getString(2131755089);
    case 1: 
      return paramContext.getString(2131755091);
    }
    return paramContext.getString(2131755090);
  }
  
  public static List<String> e()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("60 FPS");
    localArrayList.add("50 FPS");
    localArrayList.add("48 FPS");
    localArrayList.add("30 FPS");
    localArrayList.add("25 FPS");
    localArrayList.add("24 FPS");
    return localArrayList;
  }
  
  public static int f()
  {
    int i = c.a().w();
    if (i != 30)
    {
      if (i != 48)
      {
        if (i != 50)
        {
          if (i != 60)
          {
            switch (i)
            {
            default: 
              return -1;
            case 25: 
              return 4;
            }
            return 5;
          }
          return 0;
        }
        return 1;
      }
      return 2;
    }
    return 3;
  }
  
  public static List<a> f(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledApplications(8192).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      Object localObject;
      if ((localApplicationInfo.className != null) && (!localApplicationInfo.className.equals("")))
      {
        localObject = new a();
        ((a)localObject).a((String)localApplicationInfo.loadLabel(paramContext));
        ((a)localObject).a(localApplicationInfo.loadIcon(paramContext));
        ((a)localObject).b(localApplicationInfo.packageName);
        localArrayList.add(localObject);
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("app.packageName:");
        ((StringBuilder)localObject).append(localApplicationInfo.packageName);
        Log.i("app service", ((StringBuilder)localObject).toString());
      }
    }
    return localArrayList;
  }
  
  public static int g()
  {
    return c.a().i();
  }
  
  public static int h()
  {
    switch (c.a().i())
    {
    default: 
      return 540;
    case 3: 
      return 1080;
    case 2: 
      return 720;
    case 1: 
      return 540;
    }
    return 360;
  }
  
  public static float i()
  {
    switch (c.a().i())
    {
    default: 
      return 5.0F;
    case 3: 
      return 12.0F;
    case 2: 
      return 6.0F;
    case 1: 
      return 3.0F;
    }
    return 2.5F;
  }
  
  public static int j()
  {
    switch (c.a().i())
    {
    default: 
      return 24;
    case 3: 
      return 60;
    case 2: 
      return 48;
    case 1: 
      return 30;
    }
    return 24;
  }
  
  public static List<String> k()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("12 Mbps");
    localArrayList.add("8 Mbps");
    localArrayList.add("7.5 Mbps");
    localArrayList.add("5 Mbps");
    localArrayList.add("4 Mbps");
    localArrayList.add("2.5 Mbps");
    localArrayList.add("1.5 Mbps");
    localArrayList.add("1 Mbps");
    return localArrayList;
  }
  
  public static int l()
  {
    float f = c.a().x();
    if (f == 12.0F) {
      return 0;
    }
    if (f == 8.0F) {
      return 1;
    }
    double d = f;
    if (d == 7.5D) {
      return 2;
    }
    if (f == 5.0F) {
      return 3;
    }
    if (f == 4.0F) {
      return 4;
    }
    if (d == 2.5D) {
      return 5;
    }
    if (d == 1.5D) {
      return 6;
    }
    if (f == 1.0F) {
      return 7;
    }
    return -1;
  }
  
  public static List<String> m()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("3s");
    localArrayList.add("5s");
    localArrayList.add(GlobalApplication.c().getString(2131755284));
    return localArrayList;
  }
  
  public static int n()
  {
    return c.a().G();
  }
}
