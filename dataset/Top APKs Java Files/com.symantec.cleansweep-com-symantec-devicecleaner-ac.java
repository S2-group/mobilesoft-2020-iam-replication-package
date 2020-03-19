package com.symantec.devicecleaner;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import com.symantec.cleansweep.b.h;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ac
  extends k
{
  private Context a;
  private l b;
  private AsyncTask<Void, Long, Void> c;
  private String d;
  private Set<m> e = new CopyOnWriteArraySet();
  private Map<String, Collection<String>> f = new HashMap();
  private List<Pattern> g;
  private Set<String> h;
  private Map<String, ag> i;
  
  ac() {}
  
  private long a(ag paramAg)
  {
    Iterator localIterator = ag.a(paramAg).iterator();
    while (localIterator.hasNext()) {
      org.apache.commons.a.b.b(new File((String)localIterator.next()));
    }
    return ag.b(paramAg);
  }
  
  private long a(File paramFile)
  {
    try
    {
      long l = org.apache.commons.a.b.f(paramFile);
      return l;
    }
    catch (IllegalArgumentException paramFile)
    {
      com.symantec.symlog.b.b("InitialCleanService", "attempted to calculate size of file that does not exist", paramFile);
    }
    return 0L;
  }
  
  private Collection<String> a(h paramH)
  {
    Object localObject = new ArrayList();
    ((Collection)localObject).addAll(paramH.b());
    ((Collection)localObject).addAll(paramH.c());
    paramH = new ArrayList();
    localObject = ((Collection)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (!d(str))
      {
        str = c(str);
        if (str != null) {
          paramH.add(str);
        }
      }
    }
    return paramH;
  }
  
  private void a(Context paramContext)
  {
    int j = 0;
    this.g = new ArrayList(0);
    paramContext = paramContext.getResources().obtainTypedArray(an.exceptionpaths_patterns);
    while (j < paramContext.length())
    {
      Pattern localPattern = Pattern.compile(paramContext.getString(j));
      this.g.add(localPattern);
      j += 1;
    }
    paramContext.recycle();
  }
  
  private boolean b(String paramString)
  {
    return this.h.contains(paramString);
  }
  
  private String c(String paramString)
  {
    if (paramString.startsWith("sdcard:"))
    {
      paramString = paramString.substring("sdcard:".length());
      return this.d + paramString;
    }
    return null;
  }
  
  private void d()
  {
    Object localObject = this.a.getPackageManager().getInstalledPackages(0);
    this.h = new HashSet(((Collection)localObject).size());
    localObject = ((Collection)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      this.h.add(localPackageInfo.packageName);
    }
  }
  
  private boolean d(String paramString)
  {
    if (this.g != null)
    {
      Iterator localIterator = this.g.iterator();
      while (localIterator.hasNext()) {
        if (((Pattern)localIterator.next()).matcher(paramString).find()) {
          return true;
        }
      }
    }
    return false;
  }
  
  private void e()
  {
    if (this.c != null)
    {
      this.c.cancel(true);
      this.c = null;
    }
    if (this.i != null)
    {
      this.i.clear();
      this.i = null;
    }
    this.g = null;
  }
  
  private String j()
  {
    com.symantec.android.a localA = new com.symantec.android.a();
    String str2 = localA.a();
    String str1 = str2;
    if (!localA.a(str2)) {
      str1 = localA.b();
    }
    if (localA.a(str1)) {
      return str1;
    }
    return null;
  }
  
  void a()
  {
    e();
  }
  
  void a(Context paramContext, l paramL)
  {
    this.a = paramContext;
    this.b = paramL;
    a(this.a);
    this.d = j();
    d();
  }
  
  String b()
  {
    return "InitialCleanServiceComponent";
  }
  
  int c()
  {
    return 2;
  }
  
  Collection<m> f()
  {
    return this.e;
  }
  
  void g()
  {
    this.b.a(this);
    if (i())
    {
      com.symantec.symlog.b.a("InitialCleanService", "already scanning");
      return;
    }
    boolean bool = this.a.getSharedPreferences("INITIAL_CLEAN_SERVICE_COMPONENT", 0).getBoolean("first_run", true);
    com.symantec.symlog.b.a("InitialCleanService", String.format("started scan [%s]", new Object[] { String.valueOf(bool) }));
    if (bool)
    {
      this.c = android.support.v4.d.a.a(new ad(this, null), new Void[0]);
      return;
    }
    Iterator localIterator = this.e.iterator();
    while (localIterator.hasNext())
    {
      m localM = (m)localIterator.next();
      this.b.a(this, localM.f());
    }
    com.symantec.symlog.b.a("InitialCleanService", "scan completed");
    this.b.b(this);
  }
  
  void h()
  {
    e();
  }
  
  boolean i()
  {
    return this.c != null;
  }
}
