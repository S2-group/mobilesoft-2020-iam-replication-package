package com.dewmobile.kuaiya.b.e;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.dewmobile.library.transfer.DmTransferBean;
import com.dewmobile.library.transfer.DmTransferBean.ApkInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class a
{
  private List<DmTransferBean> a;
  private HashMap<String, DmTransferBean> b;
  private List<a> c;
  private List<a> d;
  private HashMap<String, a> e;
  private HashMap<String, a> f;
  private List<DmTransferBean> g;
  private TreeMap<String, DmTransferBean> h;
  private Context i;
  private PackageManager j;
  private HashMap<String, String> k = new HashMap();
  private HashMap<String, String> l = new HashMap();
  private HashMap<String, DmTransferBean> m = new HashMap();
  
  public a(Context paramContext, List<DmTransferBean> paramList, HashMap<String, String> paramHashMap)
  {
    this.i = paramContext;
    this.a = paramList;
    this.j = this.i.getPackageManager();
    if (this.a == null) {
      this.a = new ArrayList();
    }
    d();
    this.c = new ArrayList();
    this.e = new HashMap();
    this.d = new ArrayList();
    this.f = new HashMap();
    this.g = new ArrayList();
    this.h = new TreeMap();
    if (paramHashMap != null) {
      this.k.putAll(paramHashMap);
    }
  }
  
  private void d()
  {
    this.b = new HashMap();
    int n = this.a.size() - 1;
    if (n >= 0)
    {
      DmTransferBean localDmTransferBean = (DmTransferBean)this.a.get(n);
      DmTransferBean.ApkInfo localApkInfo = localDmTransferBean.y();
      if ((localApkInfo != null) && (!TextUtils.isEmpty(localApkInfo.c))) {
        this.b.put(localApkInfo.c, localDmTransferBean);
      }
      for (;;)
      {
        n -= 1;
        break;
        this.a.remove(n);
      }
    }
  }
  
  private void e()
  {
    if (this.j == null) {}
    for (;;)
    {
      return;
      List localList = this.j.getInstalledPackages(128);
      if (localList != null)
      {
        this.d.clear();
        this.f.clear();
        int n = 0;
        while (n < localList.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(n);
          Object localObject = localPackageInfo.applicationInfo;
          if ((localObject != null) && (((ApplicationInfo)localObject).sourceDir != null) && (((((ApplicationInfo)localObject).flags & 0x1) == 0) || ((((ApplicationInfo)localObject).flags & 0x80) != 0)) && (com.dewmobile.transfer.api.a.a(((ApplicationInfo)localObject).sourceDir).canRead()) && (!((ApplicationInfo)localObject).packageName.equals("com.ud.intellegentschoolsystem")) && (!((ApplicationInfo)localObject).packageName.equals("com.up.textcasterpro")))
          {
            localObject = new a();
            ((a)localObject).a = localPackageInfo.packageName;
            ((a)localObject).b = localPackageInfo.versionCode;
            this.d.add(localObject);
            this.f.put(((a)localObject).a, localObject);
          }
          n += 1;
        }
      }
    }
  }
  
  private void f()
  {
    this.g.clear();
    this.h.clear();
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
    {
      DmTransferBean localDmTransferBean = (DmTransferBean)localIterator.next();
      DmTransferBean.ApkInfo localApkInfo = localDmTransferBean.y();
      if ((localApkInfo != null) && (!TextUtils.isEmpty(localApkInfo.c))) {
        if (this.f.containsKey(localApkInfo.c))
        {
          a localA = (a)this.f.get(localApkInfo.c);
          if (localApkInfo.d > localA.b)
          {
            this.g.add(localDmTransferBean);
            this.h.put(localApkInfo.c, localDmTransferBean);
          }
        }
        else
        {
          this.g.add(localDmTransferBean);
          this.h.put(localApkInfo.c, localDmTransferBean);
        }
      }
    }
  }
  
  public void a()
  {
    e();
    f();
  }
  
  public void a(HashMap<String, DmTransferBean> paramHashMap)
  {
    this.m = paramHashMap;
  }
  
  public boolean a(String paramString)
  {
    boolean bool2 = false;
    for (;;)
    {
      try
      {
        Object localObject = this.j.getPackageInfo(paramString, 128);
        a localA = new a();
        localNameNotFoundException1.printStackTrace();
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        try
        {
          localA.a = ((PackageInfo)localObject).packageName;
          localA.b = ((PackageInfo)localObject).versionCode;
          this.d.add(localA);
          this.f.put(localA.a, localA);
          bool1 = bool2;
          if (!this.b.containsKey(paramString)) {
            break label202;
          }
          bool1 = bool2;
          if (localA == null) {
            break label202;
          }
          bool1 = true;
          localObject = (DmTransferBean)this.b.get(paramString);
          localApkInfo = ((DmTransferBean)localObject).y();
          if ((localA.b < localApkInfo.d) && (!TextUtils.isEmpty(((DmTransferBean)localObject).r()))) {
            break;
          }
          paramString = (DmTransferBean)this.h.remove(paramString);
          this.g.remove(paramString);
          return true;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2)
        {
          boolean bool1;
          DmTransferBean.ApkInfo localApkInfo;
          for (;;) {}
        }
        localNameNotFoundException1 = localNameNotFoundException1;
        localA = null;
      }
    }
    this.h.put(localApkInfo.c, localNameNotFoundException1);
    this.g.add(localNameNotFoundException1);
    label202:
    return bool1;
  }
  
  public List<DmTransferBean> b()
  {
    ArrayList localArrayList = new ArrayList();
    if ((this.g != null) && (!this.g.isEmpty()))
    {
      Iterator localIterator = this.g.iterator();
      while (localIterator.hasNext())
      {
        DmTransferBean localDmTransferBean = (DmTransferBean)localIterator.next();
        if ((!TextUtils.isEmpty(localDmTransferBean.r())) && (com.dewmobile.transfer.api.a.a(localDmTransferBean.r()).exists())) {
          localArrayList.add(localDmTransferBean);
        }
      }
    }
    return localArrayList;
  }
  
  public boolean b(String paramString)
  {
    boolean bool = false;
    if (this.f.containsKey(paramString))
    {
      a localA = (a)this.f.remove(paramString);
      this.d.remove(localA);
    }
    if (this.b.containsKey(paramString))
    {
      bool = true;
      paramString = (DmTransferBean)this.b.get(paramString);
      this.h.put(paramString.y().c, paramString);
      this.g.add(paramString);
    }
    return bool;
  }
  
  public Map<String, a> c()
  {
    return this.f;
  }
  
  public boolean c(String paramString)
  {
    b(paramString);
    return a(paramString);
  }
  
  public static class a
  {
    public String a;
    public int b;
    
    public a() {}
  }
}
