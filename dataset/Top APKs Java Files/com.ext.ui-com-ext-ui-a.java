package com.ext.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.q;
import android.support.v7.s;
import android.support.v7.v;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class a
  extends AsyncTaskLoader<HashMap<Integer, ArrayList<Object>>>
{
  private static Context b;
  List<PackageInfo> a;
  private final String c = "ApplicationLoader";
  private PackageManager d;
  private HashMap<Integer, ArrayList<Object>> e;
  private List<Object> f;
  private List<Object> g;
  private BroadcastReceiver h;
  private BroadcastReceiver i;
  private ArrayList<Object> j;
  private Comparator<Object> k = new Comparator()
  {
    Collator a = Collator.getInstance();
    
    public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
    {
      return this.a.compare(((s)paramAnonymousObject1).d().toLowerCase(), ((s)paramAnonymousObject2).d().toLowerCase());
    }
  };
  
  public a(Context paramContext)
  {
    super(paramContext);
    b = paramContext;
    this.d = getContext().getPackageManager();
  }
  
  public HashMap<Integer, ArrayList<Object>> a()
  {
    q.a("ApplicationLoader", "loadInBackground");
    this.e = new HashMap();
    if (this.a == null) {
      this.a = new ArrayList();
    }
    if (this.f == null) {
      this.f = new ArrayList();
    }
    if (this.g == null) {
      this.g = new ArrayList();
    }
    if (this.j == null) {
      this.j = new ArrayList();
    }
    this.g.clear();
    this.f.clear();
    this.a.clear();
    this.j.clear();
    this.a = this.d.getInstalledPackages(0);
    int m = 0;
    Object localObject2;
    if (m < this.a.size())
    {
      Object localObject3 = (PackageInfo)this.a.get(m);
      localObject1 = ((PackageInfo)localObject3).applicationInfo.loadLabel(this.d).toString();
      localObject2 = ((PackageInfo)localObject3).packageName;
      String str1 = ((PackageInfo)localObject3).versionName;
      int n = ((PackageInfo)localObject3).versionCode;
      String str2 = ((PackageInfo)localObject3).applicationInfo.publicSourceDir;
      if ((((PackageInfo)localObject3).applicationInfo.flags & 0x1) != 0) {}
      for (boolean bool = true;; bool = false)
      {
        localObject3 = new s();
        ((s)localObject3).a((String)localObject1);
        ((s)localObject3).b((String)localObject2);
        ((s)localObject3).c(str1);
        ((s)localObject3).a(n);
        ((s)localObject3).d(str2);
        ((s)localObject3).a(bool);
        this.j.add(localObject3);
        m += 1;
        break;
      }
    }
    Collections.sort(this.j, this.k);
    Object localObject1 = this.j.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (s)((Iterator)localObject1).next();
      if (((s)localObject2).g()) {
        this.f.add(localObject2);
      } else {
        this.g.add(localObject2);
      }
    }
    q.a("ApplicationLoader", " Total Apps :" + this.j.size() + " System apps: " + this.f.size() + " installed apps: " + this.g.size());
    this.e.put(Integer.valueOf(1), this.j);
    this.e.put(Integer.valueOf(2), (ArrayList)this.f);
    this.e.put(Integer.valueOf(3), (ArrayList)this.g);
    return this.e;
  }
  
  public void a(HashMap<Integer, ArrayList<Object>> paramHashMap)
  {
    if (isReset()) {}
    do
    {
      return;
      HashMap localHashMap = this.e;
      this.e = paramHashMap;
    } while (!isStarted());
    q.a("ApplicationLoader", "deliverResult done");
    super.deliverResult(paramHashMap);
  }
  
  public void b(HashMap<Integer, ArrayList<Object>> paramHashMap)
  {
    super.onCanceled(paramHashMap);
    q.a("ApplicationLoader", "onCanceled");
  }
  
  protected void onForceLoad()
  {
    super.onForceLoad();
    q.a("ApplicationLoader", "onForceLoadCalled");
    new Handler().post(new Runnable()
    {
      public void run()
      {
        ((MainActivity)a.b()).a(v.a().a(2131558484), 0);
      }
    });
  }
  
  protected void onReset()
  {
    q.a("ApplicationLoader", "onRest");
    super.onReset();
    stopLoading();
    if (this.h != null)
    {
      getContext().unregisterReceiver(this.h);
      this.h = null;
    }
    if (this.i != null)
    {
      getContext().unregisterReceiver(this.i);
      this.i = null;
    }
  }
  
  protected void onStartLoading()
  {
    q.a("ApplicationLoader", "onStartLoading");
    if (this.h == null) {
      this.h = new InstalledAppObserver(this);
    }
    if (this.i == null) {
      this.i = new SystemLocaleObserver(this);
    }
    if (this.e != null) {
      a(this.e);
    }
    if ((takeContentChanged()) || (this.e == null)) {
      forceLoad();
    }
  }
  
  protected void onStopLoading()
  {
    q.a("ApplicationLoader", "onStopLoading");
    cancelLoad();
  }
}
