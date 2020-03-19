package com.gto.store.main.recommend;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.TextView;
import com.gto.core.tools.c.b;
import com.gto.store.a.d;
import com.gto.store.a.g;
import com.gto.store.main.recommend.bean.AppBean;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class h
{
  public static final String a = Environment.getExternalStorageDirectory().getPath();
  private static String b = "";
  
  public static int a(Context paramContext, float paramFloat)
  {
    return b.a(paramContext, paramFloat);
  }
  
  public static int a(int[] paramArrayOfInt)
  {
    return paramArrayOfInt[new java.util.Random().nextInt(paramArrayOfInt.length)];
  }
  
  public static void a()
  {
    b = "";
  }
  
  public static void a(Context paramContext, TextView paramTextView, AppBean paramAppBean)
  {
    a(paramContext, paramTextView, paramAppBean, -1);
  }
  
  public static void a(Context paramContext, TextView paramTextView, AppBean paramAppBean, int paramInt)
  {
    if (paramInt == 7) {
      paramTextView.setBackgroundResource(a.d.h);
    }
    while (paramAppBean.isInstalled(paramContext, paramAppBean.getPkgName()))
    {
      paramTextView.setText(paramContext.getString(a.g.k));
      paramTextView.setBackgroundResource(a.d.g);
      return;
      paramTextView.setBackgroundResource(a.d.f);
    }
    paramTextView.setText(paramAppBean.getProcessedPrice(paramContext));
  }
  
  public static boolean a(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo() != null;
  }
  
  public static String b(Context paramContext)
  {
    if (TextUtils.isEmpty(b)) {
      b = c(paramContext);
    }
    return b;
  }
  
  private static String c(Context paramContext)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = paramContext.getPackageManager();
    paramContext = d(paramContext);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramContext.size())
    {
      if (i < 50) {
        localArrayList.add(paramContext.get(i));
      }
      i += 1;
    }
    if (Build.VERSION.SDK_INT >= 9) {
      Collections.sort(localArrayList, new a((PackageManager)localObject));
    }
    paramContext = localArrayList.iterator();
    while (paramContext.hasNext())
    {
      localObject = (PackageInfo)paramContext.next();
      if (localObject != null) {
        localStringBuffer.append(((PackageInfo)localObject).packageName).append(",");
      }
    }
    try
    {
      localStringBuffer.deleteCharAt(localStringBuffer.lastIndexOf(","));
      return localStringBuffer.toString();
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private static ArrayList<PackageInfo> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localArrayList.add(localPackageInfo);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static class a
    implements Comparator
  {
    private PackageManager a;
    
    public a(PackageManager paramPackageManager)
    {
      this.a = paramPackageManager;
    }
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      try
      {
        paramObject1 = ((PackageInfo)paramObject1).applicationInfo.packageName;
        paramObject2 = ((PackageInfo)paramObject2).applicationInfo.packageName;
        long l1;
        long l2;
        try
        {
          l1 = this.a.getPackageInfo(paramObject1, 0).firstInstallTime;
          l2 = this.a.getPackageInfo(paramObject2, 0).firstInstallTime;
          if (l1 == l2) {
            return 0;
          }
        }
        catch (Exception paramObject1)
        {
          paramObject1.printStackTrace();
          return 0;
        }
        if (l1 < l2) {
          return 1;
        }
        return -1;
      }
      catch (Exception paramObject1) {}
      return 0;
    }
  }
}
