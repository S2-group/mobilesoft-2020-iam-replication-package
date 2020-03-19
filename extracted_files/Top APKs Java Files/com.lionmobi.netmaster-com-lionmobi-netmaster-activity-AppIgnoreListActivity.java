package com.lionmobi.netmaster.activity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.lionmobi.netmaster.a.a;
import com.lionmobi.netmaster.b.d;
import com.lionmobi.netmaster.database.e;
import com.lionmobi.netmaster.database.q;
import com.lionmobi.netmaster.database.s;
import com.lionmobi.netmaster.database.t;
import com.lionmobi.netmaster.eventbus.message.j;
import com.lionmobi.netmaster.eventbus.message.k;
import com.lionmobi.netmaster.f.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppIgnoreListActivity
  extends b
{
  public int a = 0;
  d b;
  ListView c;
  ListView d;
  TextView e;
  TextView f;
  TextView g;
  View h;
  View i;
  List j;
  List k;
  List l;
  List m;
  a n;
  a o;
  com.lionmobi.netmaster.a.c p = new com.lionmobi.netmaster.a.c()
  {
    public void add()
    {
      if ((AppIgnoreListActivity.this.o != null) && (AppIgnoreListActivity.this.n != null) && (!AppIgnoreListActivity.this.l.contains(AppIgnoreListActivity.this.b)))
      {
        t localT = (t)e.getInstance().createItemDao(5);
        localT = new t();
        s localS = new s();
        localS.setDescription("白名单页面添加");
        localS.setTimestamp(System.currentTimeMillis());
        localS.setType(204);
        localS.setPackageinfo(AppIgnoreListActivity.this.b);
        localT.saveItem(localS);
        AppIgnoreListActivity.this.m.remove(AppIgnoreListActivity.this.b);
        AppIgnoreListActivity.this.l.add(AppIgnoreListActivity.this.b);
        AppIgnoreListActivity.this.o.notifyDataSetChanged();
        AppIgnoreListActivity.this.n.notifyDataSetChanged();
        AppIgnoreListActivity.this.e.setText("" + AppIgnoreListActivity.this.m.size());
        c.c.getDefault().post(new j(localS.getPackageinfo().getPackagsname()));
      }
    }
    
    public void delet()
    {
      if ((AppIgnoreListActivity.this.o != null) && (AppIgnoreListActivity.this.n != null) && (!AppIgnoreListActivity.this.m.contains(AppIgnoreListActivity.this.b)))
      {
        t localT = (t)e.getInstance().createItemDao(5);
        new t().deleteItem(AppIgnoreListActivity.this.b);
        AppIgnoreListActivity.this.l.remove(AppIgnoreListActivity.this.b);
        AppIgnoreListActivity.this.m.add(AppIgnoreListActivity.this.b);
        AppIgnoreListActivity.this.o.notifyDataSetChanged();
        AppIgnoreListActivity.this.n.notifyDataSetChanged();
        AppIgnoreListActivity.this.e.setText("" + AppIgnoreListActivity.this.l.size());
        c.c.getDefault().post(new k(AppIgnoreListActivity.this.b.getPackagsname()));
      }
    }
  };
  
  public AppIgnoreListActivity() {}
  
  private int a(String paramString)
  {
    try
    {
      paramString = getPackageManager().getPackageInfo(paramString, 0);
      if (!isSystemApp(paramString))
      {
        boolean bool = isSystemUpdateApp(paramString);
        if (!bool) {}
      }
      else
      {
        return 1;
      }
      return 2;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return -1;
  }
  
  private void a()
  {
    Iterator localIterator = getApplicationContext().getPackageManager().getInstalledPackages(0).iterator();
    if (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      d localD = new d();
      localD.setPackagsname(localPackageInfo.packageName);
      switch (a(localPackageInfo.packageName))
      {
      default: 
        localD.a = -1;
      }
      for (;;)
      {
        this.j.add(localD);
        break;
        localD.a = 1;
        continue;
        localD.a = 2;
      }
    }
  }
  
  private void b()
  {
    Object localObject = (t)e.getInstance().createItemDao(5);
    this.k = new t().findAllItems();
    localObject = this.k.iterator();
    if (((Iterator)localObject).hasNext())
    {
      s localS = (s)((Iterator)localObject).next();
      d localD = new d();
      localD.setPackagsname(localS.getPackageinfo().getPackagsname());
      switch (a(localS.getPackageinfo().getPackagsname()))
      {
      default: 
        localD.a = -1;
      }
      for (;;)
      {
        this.l.add(localD);
        break;
        localD.a = 1;
        continue;
        localD.a = 2;
      }
    }
  }
  
  private void c()
  {
    this.m.addAll(this.j);
    Iterator localIterator1 = this.j.iterator();
    while (localIterator1.hasNext())
    {
      d localD1 = (d)localIterator1.next();
      if (localD1.getPackagsname().indexOf("com.lionmobi") >= 0)
      {
        this.m.remove(localD1);
      }
      else
      {
        Iterator localIterator2 = this.l.iterator();
        while (localIterator2.hasNext())
        {
          d localD2 = (d)localIterator2.next();
          if (localD1.getPackagsname().equals(localD2.getPackagsname())) {
            this.m.remove(localD1);
          }
        }
      }
    }
  }
  
  public boolean isSystemApp(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  public boolean isSystemUpdateApp(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x80) != 0;
  }
  
  public void onBackPressed()
  {
    if (this.a == 0)
    {
      if (this.o != null) {
        this.o.clearnDraw();
      }
      if (this.n != null) {
        this.n.clearnDraw();
      }
      this.o = null;
      this.n = null;
      finish();
    }
    while (this.a != 1) {
      return;
    }
    this.a = 0;
    this.c.setVisibility(0);
    this.d.setVisibility(8);
    this.e.setText("" + this.l.size());
    this.f.setText(2131099722);
    this.g.setText(2131100021);
    this.h.setVisibility(0);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903119);
    f.translucentStatusBar(this);
    this.j = new ArrayList();
    this.l = new ArrayList();
    this.m = new ArrayList();
    this.c = ((ListView)findViewById(2131493291));
    this.d = ((ListView)findViewById(2131493292));
    this.g = ((TextView)findViewById(2131493287));
    this.i = findViewById(2131493286);
    this.f = ((TextView)findViewById(2131493289));
    this.h = findViewById(2131493288);
    this.e = ((TextView)findViewById(2131493290));
    this.b = new d();
    a();
    b();
    c();
    sort(this.l);
    sort(this.m);
    this.e.setText("" + this.l.size());
    this.n = new a(this, this.l, 0);
    this.o = new a(this, this.m, 1);
    this.o.setListener(this.p);
    this.n.setListener(this.p);
    this.c.setAdapter(this.n);
    this.d.setAdapter(this.o);
    this.h.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (AppIgnoreListActivity.this.a == 0)
        {
          AppIgnoreListActivity.this.a = 1;
          AppIgnoreListActivity.this.d.setVisibility(0);
          AppIgnoreListActivity.this.c.setVisibility(8);
          AppIgnoreListActivity.this.e.setText("" + AppIgnoreListActivity.this.m.size());
          AppIgnoreListActivity.this.f.setText(2131099838);
          AppIgnoreListActivity.this.g.setText(2131100021);
          AppIgnoreListActivity.this.h.setVisibility(4);
        }
        while (AppIgnoreListActivity.this.a != 1) {
          return;
        }
        AppIgnoreListActivity.this.a = 0;
        AppIgnoreListActivity.this.c.setVisibility(0);
        AppIgnoreListActivity.this.d.setVisibility(8);
        AppIgnoreListActivity.this.e.setText("" + AppIgnoreListActivity.this.l.size());
        AppIgnoreListActivity.this.f.setText(2131099722);
        AppIgnoreListActivity.this.g.setText(2131100021);
      }
    });
    this.i.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AppIgnoreListActivity.this.onBackPressed();
      }
    });
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.j.clear();
    this.j = null;
    this.n = null;
    this.k.clear();
    this.k = null;
    this.l.clear();
    this.l = null;
    this.d = null;
    this.m.clear();
    this.m = null;
  }
  
  public void setAPP(d paramD)
  {
    this.b = paramD;
  }
  
  public void sort(List paramList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      d localD = (d)localIterator.next();
      switch (localD.a)
      {
      default: 
        localArrayList1.add(localD);
        break;
      case 1: 
        localArrayList3.add(localD);
        break;
      case 2: 
        localArrayList2.add(localD);
      }
    }
    paramList.clear();
    paramList.addAll(localArrayList1);
    paramList.addAll(localArrayList2);
    paramList.addAll(localArrayList3);
  }
}
