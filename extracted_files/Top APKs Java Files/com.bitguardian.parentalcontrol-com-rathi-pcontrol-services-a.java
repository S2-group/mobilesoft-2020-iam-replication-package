package com.rathi.pcontrol.services;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.rathi.pcontrol.AppController;
import com.rathi.pcontrol.g.s;
import com.rathi.pcontrol.receiver.d;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class a
  extends android.support.v4.a.a<List<s>>
{
  public static final Comparator<s> s = new Comparator()
  {
    private final Collator a = Collator.getInstance();
    
    public int a(s paramAnonymousS1, s paramAnonymousS2)
    {
      return this.a.compare(paramAnonymousS1.c(), paramAnonymousS2.c());
    }
  };
  final a o = new a();
  public final PackageManager p = h().getPackageManager();
  List<s> q;
  d r;
  private com.rathi.pcontrol.db.a t = AppController.d();
  
  public a(Context paramContext)
  {
    super(paramContext);
  }
  
  public void a(List<s> paramList)
  {
    if ((k()) && (paramList != null)) {
      c(paramList);
    }
    List localList = this.q;
    this.q = paramList;
    if (i()) {
      super.b(paramList);
    }
    if (localList != null) {
      c(localList);
    }
  }
  
  public void b(List<s> paramList)
  {
    super.a(paramList);
    c(paramList);
  }
  
  protected void c(List<s> paramList) {}
  
  protected void m()
  {
    if (this.q != null) {
      a(this.q);
    }
    if (this.r == null) {
      this.r = new d(this);
    }
    boolean bool = this.o.a(h().getResources());
    if ((v()) || (this.q == null) || (bool)) {
      o();
    }
  }
  
  protected void q()
  {
    n();
  }
  
  protected void u()
  {
    super.u();
    q();
    if (this.q != null)
    {
      c(this.q);
      this.q = null;
    }
    if (this.r != null)
    {
      h().unregisterReceiver(this.r);
      this.r = null;
    }
  }
  
  public List<s> z()
  {
    Object localObject2 = this.p.getInstalledApplications(8704);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new ArrayList();
    }
    h();
    localObject2 = new ArrayList(((List)localObject1).size());
    int i = 0;
    while (i < ((List)localObject1).size()) {
      i += 1;
    }
    Collections.sort((List)localObject2, s);
    return localObject2;
  }
  
  public static class a
  {
    final Configuration a = new Configuration();
    int b;
    
    public a() {}
    
    boolean a(Resources paramResources)
    {
      int j = this.a.updateFrom(paramResources.getConfiguration());
      int i;
      if (this.b != paramResources.getDisplayMetrics().densityDpi) {
        i = 1;
      } else {
        i = 0;
      }
      if ((i == 0) && ((j & 0x304) == 0)) {
        return false;
      }
      this.b = paramResources.getDisplayMetrics().densityDpi;
      return true;
    }
  }
}
