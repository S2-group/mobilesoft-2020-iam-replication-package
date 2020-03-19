package com.common.tools.cycinspect.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.common.tools.cycinspect.c.e;
import com.common.tools.cycinspect.e.c;
import com.common.tools.cycinspect.e.c.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
  implements c.a
{
  private static boolean t = false;
  private static boolean u = false;
  private static boolean v = false;
  private final com.common.tools.cycinspect.e.b a;
  private final Context b;
  private final String c;
  private final String d;
  private final List<String> e = new ArrayList();
  private boolean f = false;
  private final List<String> g = new ArrayList();
  private boolean h = false;
  private long i;
  private int j;
  private long k;
  private Handler l = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1118) {
        a.a(a.this);
      }
      do
      {
        return;
        if (paramAnonymousMessage.what == 1119)
        {
          a.this.a();
          return;
        }
      } while (paramAnonymousMessage.what != 1120);
      a.a(a.this, (String)paramAnonymousMessage.obj);
    }
  };
  private boolean m = false;
  private boolean n = false;
  private final List<String> o = new ArrayList();
  private boolean p = false;
  private final List<String> q = new ArrayList();
  private final List<String> r = new ArrayList();
  private final List<String> s = new ArrayList();
  
  public a(com.common.tools.cycinspect.e.b paramB, Context paramContext)
  {
    this.a = paramB;
    this.b = paramContext;
    this.c = this.b.getPackageName();
    this.d = e.e(this.b);
    c();
    a(paramContext);
  }
  
  private void a(Context paramContext)
  {
    for (;;)
    {
      int i1;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
        i1 = 0;
        int i2 = paramContext.size();
        if (i1 < i2) {
          try
          {
            Object localObject = (PackageInfo)paramContext.get(i1);
            if ((localObject == null) || ((((PackageInfo)localObject).applicationInfo.flags & 0x1) != 0) || (this.c.equals(((PackageInfo)localObject).packageName))) {
              break label102;
            }
            localObject = ((PackageInfo)localObject).packageName;
            this.e.add(localObject);
          }
          catch (Exception localException) {}
        }
        return;
      }
      catch (Exception paramContext) {}
      label102:
      i1 += 1;
    }
  }
  
  private void a(Context paramContext, List<String> paramList, String paramString)
  {
    Object localObject = paramList;
    if (paramList == null) {
      localObject = new ArrayList();
    }
    try
    {
      paramContext = e.a(paramContext, paramString);
      if (!TextUtils.isEmpty(paramContext))
      {
        paramContext = com.common.tools.cycinspect.c.b.a(paramContext).split(",");
        if (paramContext != null)
        {
          int i2 = paramContext.length;
          int i1 = 0;
          while (i1 < i2)
          {
            ((List)localObject).add(paramContext[i1]);
            i1 += 1;
          }
        }
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  private List<String> b(Context paramContext)
  {
    if (!this.p)
    {
      a(paramContext, this.o, "data");
      this.p = true;
    }
    return this.o;
  }
  
  private boolean b(String paramString)
  {
    if (d(paramString))
    {
      if (!com.common.tools.cycinspect.e.a.g()) {}
    }
    else {
      while (this.g.contains(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private List<String> c(Context paramContext)
  {
    if (!t)
    {
      a(paramContext, this.q, "data1");
      t = true;
    }
    return this.q;
  }
  
  private void c()
  {
    this.i = com.common.tools.cycinspect.e.d.a(this.b, "lst_poptm", 0L);
    this.j = com.common.tools.cycinspect.e.d.b(this.b, "lst_popct", 0);
    this.k = com.common.tools.cycinspect.e.d.a(this.b, "lst_sctm", 0L);
  }
  
  private void c(String paramString)
  {
    if (com.common.tools.cycinspect.e.a.o())
    {
      com.common.tools.cycinspect.e.a.b(paramString);
      d();
      c.a().a("onScanEnd-sucShow");
      long l1 = com.common.tools.cycinspect.e.a.c();
      this.l.removeMessages(1119);
      this.l.sendEmptyMessageDelayed(1119, l1);
      return;
    }
    com.common.tools.cycinspect.e.a.a(paramString);
  }
  
  private List<String> d(Context paramContext)
  {
    if (!u)
    {
      a(paramContext, this.r, "data3");
      u = true;
    }
    return this.r;
  }
  
  private void d()
  {
    if (!e.a(this.i)) {
      this.j = 0;
    }
    this.j += 1;
    this.i = System.currentTimeMillis();
    com.common.tools.cycinspect.e.d.a(this.b, "lst_poptm", Long.valueOf(this.i));
    com.common.tools.cycinspect.e.d.a(this.b, "lst_popct", this.j);
  }
  
  private boolean d(String paramString)
  {
    return (paramString != null) && ((paramString.endsWith("launcher")) || (paramString.equals(this.d)));
  }
  
  private List<String> e(Context paramContext)
  {
    if (!v)
    {
      a(paramContext, this.s, "data2");
      v = true;
    }
    return this.s;
  }
  
  private boolean e()
  {
    return (com.common.tools.cycinspect.e.a.j()) && (Build.VERSION.SDK_INT > 21) && (Build.VERSION.SDK_INT < 24) && (!com.common.tools.cycinspect.c.d.a(this.b));
  }
  
  private boolean f()
  {
    if (this.h) {}
    while ((!this.a.d()) || (!com.common.tools.cycinspect.e.a.a()) || (e.a(this.b)) || (e.b(this.b)) || (e.c(this.b) != 1) || (com.common.tools.cycinspect.e.a.b()) || (g())) {
      return false;
    }
    return true;
  }
  
  private boolean g()
  {
    boolean bool2 = false;
    int i1 = com.common.tools.cycinspect.e.a.i();
    boolean bool1 = bool2;
    if (i1 > 0)
    {
      if (!e.a(this.i)) {
        this.j = 0;
      }
      bool1 = bool2;
      if (this.j >= i1) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void h()
  {
    Object[] arrayOfObject;
    boolean bool;
    if (f())
    {
      arrayOfObject = i();
      bool = ((Boolean)arrayOfObject[0]).booleanValue();
      if (bool)
      {
        if (!com.common.tools.cycinspect.e.a.o()) {
          break label129;
        }
        com.common.tools.cycinspect.e.a.b((String)arrayOfObject[1]);
        d();
      }
      if ((!bool) && (!this.m) && (!com.common.tools.cycinspect.e.a.o())) {
        break label142;
      }
    }
    label129:
    label142:
    for (long l1 = com.common.tools.cycinspect.e.a.d();; l1 = com.common.tools.cycinspect.e.a.e())
    {
      long l3 = com.common.tools.cycinspect.e.a.c() - Math.abs(System.currentTimeMillis() - this.i);
      long l2 = l1;
      if (l3 > 0L)
      {
        l2 = l1;
        if (l3 > l1) {
          l2 = l3;
        }
      }
      this.l.sendEmptyMessageDelayed(1118, l2);
      this.m = bool;
      return;
      com.common.tools.cycinspect.e.a.a((String)arrayOfObject[1]);
      break;
    }
  }
  
  private Object[] i()
  {
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Boolean.valueOf(false);
    try
    {
      Object[] arrayOfObject2 = e.d(this.b);
      if ((arrayOfObject2 != null) && (!this.c.equals(arrayOfObject2[0])))
      {
        if (((Boolean)arrayOfObject2[2]).booleanValue())
        {
          arrayOfObject1[0] = Boolean.valueOf(b((String)arrayOfObject2[0]));
          arrayOfObject1[1] = arrayOfObject2[0];
          return arrayOfObject1;
        }
        if (com.common.tools.cycinspect.e.a.f())
        {
          arrayOfObject1[0] = Boolean.valueOf(b((String)arrayOfObject2[0]));
          arrayOfObject1[1] = arrayOfObject2[0];
          return arrayOfObject1;
        }
      }
    }
    catch (Exception localException) {}
    return arrayOfObject1;
  }
  
  private void j()
  {
    label332:
    label341:
    label346:
    label349:
    for (;;)
    {
      synchronized (this.g)
      {
        this.g.clear();
        this.f = com.common.tools.cycinspect.e.a.l();
        if (this.f)
        {
          if (this.e.size() <= 0) {
            break label332;
          }
          List localList1 = com.common.tools.cycinspect.e.a.n();
          if ((localList1 != null) && (localList1.size() > 0)) {
            break label349;
          }
          localList1 = e(this.b);
          localObject3 = com.common.tools.cycinspect.e.a.m();
          if ((localObject3 != null) && (((List)localObject3).size() > 0)) {
            break label346;
          }
          localObject3 = c(this.b);
          List localList3 = d(this.b);
          Iterator localIterator1 = this.e.iterator();
          if (!localIterator1.hasNext()) {
            break label332;
          }
          String str = (String)localIterator1.next();
          if (((localObject3 != null) && (((List)localObject3).contains(str))) || ((localList3 != null) && (localList3.contains(str)))) {
            continue;
          }
          if (localList1 == null) {
            break label341;
          }
          Iterator localIterator2 = localList1.iterator();
          if (!localIterator2.hasNext()) {
            break label341;
          }
          if (!str.startsWith((String)localIterator2.next())) {
            continue;
          }
          i1 = 1;
          if (i1 != 0) {
            continue;
          }
          this.g.add(str);
        }
      }
      Object localObject3 = com.common.tools.cycinspect.e.a.h();
      Object localObject2;
      if (localObject3 != null)
      {
        localObject2 = localObject3;
        if (((List)localObject3).size() > 0) {}
      }
      else
      {
        localObject2 = b(this.b);
      }
      if ((localObject2 != null) && (((List)localObject2).size() > 0))
      {
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (String)((Iterator)localObject2).next();
          if (this.e.contains(localObject3)) {
            this.g.add(localObject3);
          }
        }
      }
      this.n = true;
      return;
      int i1 = 0;
      continue;
    }
  }
  
  public void a()
  {
    c localC;
    List localList;
    if ((!this.l.hasMessages(1118)) && (!c.a().b()) && (f()))
    {
      j();
      if ((com.common.tools.cycinspect.e.a.g()) || (this.g.size() > 0))
      {
        if (!e()) {
          break label131;
        }
        if (this.d != null) {
          this.g.remove(this.d);
        }
        localC = c.a();
        localList = this.g;
        if (!com.common.tools.cycinspect.e.a.g()) {
          break label125;
        }
      }
    }
    label125:
    for (String str = this.d;; str = null)
    {
      localC.a(localList, str);
      c.a().a(this);
      c.a().c();
      return;
    }
    label131:
    long l1 = com.common.tools.cycinspect.e.a.d();
    long l2 = com.common.tools.cycinspect.e.a.c() - Math.abs(System.currentTimeMillis() - this.i);
    if ((l2 > 0L) && (l2 > l1)) {
      l1 = l2;
    }
    for (;;)
    {
      this.l.sendEmptyMessageDelayed(1118, l1);
      return;
    }
  }
  
  public void a(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (this.c.equals(paramString))) {}
    while ((!com.common.tools.cycinspect.e.a.g()) && (d(paramString))) {
      return;
    }
    if (!f())
    {
      c.a().a("onScanEnd-check FAIL.");
      return;
    }
    Message localMessage = new Message();
    localMessage.what = 1120;
    localMessage.obj = paramString;
    this.l.sendMessage(localMessage);
  }
  
  public void b()
  {
    this.l.removeMessages(1118);
    c.a().a("CTL - stop");
  }
}
