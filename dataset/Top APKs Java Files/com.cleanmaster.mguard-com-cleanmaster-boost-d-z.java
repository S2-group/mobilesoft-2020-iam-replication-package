package com.cleanmaster.boost.d;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.cleanmaster.activitymanagerhelper.RunningAppProcessInfo;
import com.cleanmaster.boost.process.util.f;
import com.keniu.security.d;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class z
  extends com.cleanmaster.kinfocreporter.a
{
  private String a;
  private boolean b;
  private int c;
  private int d;
  private int e;
  private String f;
  private String g;
  private boolean h;
  private int i;
  private int j;
  private int k = -1;
  private int l;
  private int m;
  private int n;
  private int o;
  private int p;
  private int q;
  private int r;
  private int s;
  private int t;
  private int u;
  
  public z()
  {
    super("cm_cpu_push");
  }
  
  public final void a()
  {
    Object localObject1 = d.a();
    Object localObject2 = ((Context)localObject1).getPackageManager().getInstalledPackages(0);
    if (localObject2 != null) {
      this.p = ((List)localObject2).size();
    }
    set("installnum", this.p);
    localObject2 = new com.cleanmaster.activitymanagerhelper.a();
    ((com.cleanmaster.activitymanagerhelper.a)localObject2).a = com.cmcm.rtstub.a.a();
    localObject2 = ((com.cleanmaster.activitymanagerhelper.a)localObject2).a((Context)localObject1);
    this.q = ((List)localObject2).size();
    localObject1 = new HashSet();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Object localObject3 = (RunningAppProcessInfo)((Iterator)localObject2).next();
      if ((localObject3 != null) && (((RunningAppProcessInfo)localObject3).pkgList != null))
      {
        localObject3 = ((RunningAppProcessInfo)localObject3).pkgList;
        int i2 = localObject3.length;
        int i1 = 0;
        while (i1 < i2)
        {
          CharSequence localCharSequence = localObject3[i1];
          if (!TextUtils.isEmpty(localCharSequence)) {
            ((HashSet)localObject1).add(localCharSequence);
          }
          i1 += 1;
        }
      }
    }
    this.u = ((HashSet)localObject1).size();
    set("activenum", this.q);
    set("activeappnum", this.u);
    this.r = ((int)(f.a() / 1024L / 1024L));
    set("idleram", this.r);
    this.s = ((int)(f.b() / 1024L / 1024L));
    set("ram", this.s);
  }
  
  public final void a(int paramInt)
  {
    this.c = paramInt;
    set("syscpu", paramInt);
  }
  
  public final void a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      this.a = "";
      set("pn", "");
      return;
    }
    this.a = paramString;
    set("pn", paramString);
  }
  
  public final void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
    if (paramBoolean) {}
    for (int i1 = 1;; i1 = 2)
    {
      set("apptype", i1);
      return;
    }
  }
  
  public final z b()
  {
    int i1 = 1;
    if ((this.d != 0) || ((1 != this.l) && (2 != this.l))) {
      return null;
    }
    z localZ = new z();
    localZ.a(this.a);
    localZ.a(this.b);
    localZ.a(this.c);
    localZ.b(this.d);
    int i2 = this.e;
    localZ.e = i2;
    localZ.set("pkgcpu", i2);
    localZ.b(this.f);
    localZ.c(this.g);
    boolean bool = this.h;
    localZ.h = bool;
    if (bool) {}
    for (;;)
    {
      localZ.set("cloudinfo", i1);
      i1 = this.i;
      localZ.i = i1;
      localZ.set("avg", i1);
      i1 = this.j;
      localZ.j = i1;
      localZ.set("max", i1);
      localZ.c(this.k);
      localZ.d(3);
      localZ.e(this.m);
      localZ.f(this.n);
      localZ.set("sencetype", this.o);
      localZ.set("installnum", this.p);
      localZ.set("activenum", this.q);
      localZ.set("idleram", this.r);
      localZ.set("ram", this.s);
      localZ.g(this.t);
      localZ.set("activeappnum", this.u);
      return localZ;
      i1 = 2;
    }
  }
  
  public final void b(int paramInt)
  {
    this.d = paramInt;
    set("op", paramInt);
  }
  
  public final void b(String paramString)
  {
    this.f = paramString;
    set("pkgvercode", paramString);
  }
  
  public final void c(int paramInt)
  {
    this.k = paramInt;
    set("env", paramInt);
  }
  
  public final void c(String paramString)
  {
    this.g = paramString;
    set("pkgvername", paramString);
  }
  
  public final void d(int paramInt)
  {
    this.l = paramInt;
    set("pushtype", paramInt);
  }
  
  public final void e(int paramInt)
  {
    this.m = paramInt;
    set("appnum", paramInt);
  }
  
  public final void f(int paramInt)
  {
    this.n = paramInt;
    set("lagtype", paramInt);
  }
  
  public final void g(int paramInt)
  {
    this.t = paramInt;
    set("txtid", paramInt);
  }
  
  public final void h(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
    case 4: 
      paramInt = 1;
    }
    for (;;)
    {
      this.o = paramInt;
      set("sencetype", paramInt);
      return;
      paramInt = 2;
      continue;
      paramInt = 3;
      continue;
      paramInt = 4;
      continue;
      paramInt = 5;
    }
  }
  
  protected final void onPreReport()
  {
    super.onPreReport();
  }
  
  public final void reset()
  {
    super.reset();
    set("pn", "");
    set("apptype", 0);
    set("syscpu", 0);
    set("op", 0);
    set("pkgcpu", 0);
    set("pkgvercode", "");
    set("pkgvername", "");
    set("cloudinfo", 0);
    set("avg", 0);
    set("max", 0);
    set("env", -1);
    set("isagain", 0);
    set("pushtype", 0);
    set("appnum", 0);
    set("lagtype", 0);
    set("sencetype", 0);
    set("installnum", 0);
    set("activenum", 0);
    set("idleram", 0);
    set("ram", 0);
    set("txtid", 0);
    set("activeappnum", 0);
  }
}
