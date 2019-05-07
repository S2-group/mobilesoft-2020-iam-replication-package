package com.kapp.ifont.x.installer.a;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public final class c
{
  public static int a = 2;
  private static c b = null;
  private final com.kapp.ifont.x.installer.a c = com.kapp.ifont.x.installer.a.q();
  private SharedPreferences d = this.c.getSharedPreferences("enabled_modules", 0);
  private final PackageManager e = this.c.getPackageManager();
  private final String f = this.c.getPackageName();
  private a g = null;
  private Map<String, a> h = new HashMap();
  private boolean i = false;
  private final List<b> j = new CopyOnWriteArrayList();
  
  private c() {}
  
  public static String a()
  {
    return com.kapp.ifont.x.installer.a.p() + "conf/modules.list";
  }
  
  public static c b()
  {
    try
    {
      if (b == null)
      {
        b = new c();
        b.c();
      }
      c localC = b;
      return localC;
    }
    finally {}
  }
  
  public static int c(String paramString)
  {
    int k = 0;
    int n = paramString.length();
    int m = 0;
    while (k < n)
    {
      int i1 = paramString.charAt(k);
      if ((48 > i1) || (i1 > 57)) {
        break;
      }
      m = m * 10 + (i1 - 48);
      k += 1;
    }
    return m;
  }
  
  public a a(String paramString)
  {
    Iterator localIterator;
    try
    {
      Object localObject1 = this.e.getPackageInfo(paramString, 128);
      Object localObject2 = ((PackageInfo)localObject1).applicationInfo;
      if ((((ApplicationInfo)localObject2).enabled) && (((ApplicationInfo)localObject2).metaData != null) && (((ApplicationInfo)localObject2).metaData.containsKey("xposedmodule")))
      {
        localObject1 = new a((PackageInfo)localObject1, false, null);
        this.h.put(paramString, localObject1);
        localObject2 = this.j.iterator();
        while (((Iterator)localObject2).hasNext()) {
          ((b)((Iterator)localObject2).next()).a(b, paramString, (a)localObject1);
        }
        return localIterator;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      if ((a)this.h.remove(paramString) != null)
      {
        localIterator = this.j.iterator();
        while (localIterator.hasNext()) {
          ((b)localIterator.next()).a(b, paramString, null);
        }
      }
      return null;
    }
    if ((a)this.h.remove(paramString) != null)
    {
      localIterator = this.j.iterator();
      while (localIterator.hasNext()) {
        ((b)localIterator.next()).a(b, paramString, null);
      }
    }
    return null;
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.d.edit().putInt(paramString, 1).commit();
      return;
    }
    this.d.edit().remove(paramString).commit();
  }
  
  public void a(boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        Log.i("XposedInstaller", "updating modules.list");
        k = b.g();
        if (k != 0) {
          continue;
        }
      }
      catch (IOException localIOException)
      {
        int k;
        PrintWriter localPrintWriter;
        Iterator localIterator;
        a localA;
        Log.e("XposedInstaller", "cannot write /data/data/de.robv.android.xposed.installer/conf/modules.list", localIOException);
        continue;
      }
      finally {}
      return;
      localPrintWriter = new PrintWriter(a());
      localIterator = e().iterator();
      if (localIterator.hasNext())
      {
        localA = (a)localIterator.next();
        if ((localA.f <= k) && (localA.f >= a)) {
          localPrintWriter.println(localA.a.sourceDir);
        }
      }
      else
      {
        localObject.close();
        FileUtils.setPermissions(a(), 436, -1, -1);
        a.a(new File(a()), new File("/data/data/de.robv.android.xposed.installer/conf/modules.list"), 493);
        if (!paramBoolean) {}
      }
    }
  }
  
  public boolean b(String paramString)
  {
    return this.f.equals(paramString);
  }
  
  public void c()
  {
    for (;;)
    {
      PackageInfo localPackageInfo;
      try
      {
        if (this.i) {
          return;
        }
        this.i = true;
        HashMap localHashMap = new HashMap();
        Iterator localIterator2 = this.e.getInstalledPackages(128).iterator();
        if (!localIterator2.hasNext()) {
          break;
        }
        localPackageInfo = (PackageInfo)localIterator2.next();
        Object localObject3 = localPackageInfo.applicationInfo;
        if (!((ApplicationInfo)localObject3).enabled) {
          continue;
        }
        if ((((ApplicationInfo)localObject3).metaData != null) && (((ApplicationInfo)localObject3).metaData.containsKey("xposedmodule")))
        {
          localObject3 = new a(localPackageInfo, false, null);
          localHashMap.put(localPackageInfo.packageName, localObject3);
          continue;
        }
        if (!b(localPackageInfo.packageName)) {
          continue;
        }
      }
      finally {}
      this.g = new a(localPackageInfo, true, null);
    }
    this.h = localObject1;
    try
    {
      this.i = false;
      Iterator localIterator1 = this.j.iterator();
      while (localIterator1.hasNext()) {
        ((b)localIterator1.next()).a(b);
      }
      return;
    }
    finally {}
  }
  
  public String d()
  {
    return this.f;
  }
  
  public List<a> e()
  {
    LinkedList localLinkedList = new LinkedList();
    Object localObject1 = new ArrayList();
    ((List)localObject1).add("com.kapp.ifont");
    ((List)localObject1).add("com.kapp.ifont.donate");
    ((List)localObject1).add("com.kapp.ifont.x.perappfonts");
    localObject1 = ((List)localObject1).iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = a((String)((Iterator)localObject1).next());
      if (localObject2 != null) {
        localLinkedList.add(localObject2);
      }
    }
    localObject1 = this.d.getAll().keySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Iterator)localObject1).next();
      a localA = a((String)localObject2);
      if (localA != null) {
        localLinkedList.add(localA);
      } else {
        a((String)localObject2, false);
      }
    }
    return localLinkedList;
  }
  
  public class a
  {
    public ApplicationInfo a;
    public final String b;
    public final boolean c;
    public final String d;
    public final int e;
    public final int f;
    private String h;
    private String i;
    private Drawable.ConstantState j = null;
    
    private a(PackageInfo paramPackageInfo, boolean paramBoolean)
    {
      this.a = paramPackageInfo.applicationInfo;
      this.b = paramPackageInfo.packageName;
      this.c = paramBoolean;
      this.d = paramPackageInfo.versionName;
      this.e = paramPackageInfo.versionCode;
      if (paramBoolean)
      {
        this.f = 0;
        this.i = "";
        return;
      }
      this$1 = this.a.metaData.get("xposedminversion");
      if ((c.this instanceof Integer))
      {
        this.f = ((Integer)c.this).intValue();
        return;
      }
      if ((c.this instanceof String))
      {
        this.f = c.c((String)c.this);
        return;
      }
      this.f = 0;
    }
    
    public String a()
    {
      if (this.h == null) {
        this.h = this.a.loadLabel(c.a(c.this)).toString();
      }
      return this.h;
    }
    
    public String toString()
    {
      return a();
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(c paramC);
    
    public abstract void a(c paramC, String paramString, c.a paramA);
  }
}
