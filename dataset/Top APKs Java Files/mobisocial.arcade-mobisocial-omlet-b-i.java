package mobisocial.omlet.b;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.content.e;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import mobisocial.longdan.b.eo;
import mobisocial.longdan.b.er;
import mobisocial.omlet.b.a.b;
import mobisocial.omlib.api.OmlibApiManager;

public class i
  extends e<b>
{
  OmlibApiManager a;
  b b = new b();
  private final Runnable c;
  
  public i(Context paramContext, Runnable paramRunnable)
  {
    super(paramContext);
    this.a = OmlibApiManager.getInstance(paramContext);
    this.c = paramRunnable;
  }
  
  private void a(List<b.er> paramList)
  {
    try
    {
      localObject = ((UsageStatsManager)getContext().getSystemService("usagestats")).queryAndAggregateUsageStats(System.currentTimeMillis() - 2592000000L, System.currentTimeMillis());
    }
    catch (Throwable localThrowable)
    {
      Object localObject;
      final HashMap localHashMap;
      Iterator localIterator;
      for (;;) {}
    }
    localObject = new HashMap();
    localHashMap = new HashMap();
    localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      b.er localEr = (b.er)localIterator.next();
      localHashMap.put(localEr, new mobisocial.omlet.b.a.a(localEr));
    }
    try
    {
      Collections.sort(paramList, new Comparator()
      {
        public int a(b.er paramAnonymousEr1, b.er paramAnonymousEr2)
        {
          if (!this.a.isEmpty())
          {
            Iterator localIterator = paramAnonymousEr1.j.iterator();
            long l2 = 0L;
            label28:
            Object localObject;
            for (long l1 = 0L; localIterator.hasNext(); l1 = ((UsageStats)localObject).getLastTimeUsed())
            {
              localObject = (b.eo)localIterator.next();
              localObject = (UsageStats)this.a.get(((b.eo)localObject).b);
              if ((localObject == null) || (((UsageStats)localObject).getLastTimeUsed() <= l1)) {
                break label28;
              }
            }
            localIterator = paramAnonymousEr2.j.iterator();
            while (localIterator.hasNext())
            {
              localObject = (b.eo)localIterator.next();
              localObject = (UsageStats)this.a.get(((b.eo)localObject).b);
              if ((localObject != null) && (((UsageStats)localObject).getLastTimeUsed() > l2)) {
                l2 = ((UsageStats)localObject).getLastTimeUsed();
              }
            }
            if (l1 != l2) {
              return l2 < l1;
            }
          }
          return ((mobisocial.omlet.b.a.a)localHashMap.get(paramAnonymousEr1)).a(i.this.getContext()).compareTo(((mobisocial.omlet.b.a.a)localHashMap.get(paramAnonymousEr2)).a(i.this.getContext()));
        }
      });
      return;
    }
    catch (Exception paramList)
    {
      Log.e("GamesListLoader", "error sorting", paramList);
      return;
    }
  }
  
  private boolean a(List<b.er> paramList1, List<b.er> paramList2)
  {
    if (paramList1 == null) {
      return paramList2 == null;
    }
    if (paramList2 == null) {
      return false;
    }
    if (paramList1.size() != paramList2.size()) {
      return false;
    }
    HashSet localHashSet = new HashSet();
    int i = 0;
    while (i < paramList1.size())
    {
      localHashSet.add(((b.er)paramList1.get(i)).k.b);
      i += 1;
    }
    i = 0;
    while (i < paramList2.size())
    {
      if (!localHashSet.contains(((b.er)paramList2.get(i)).k.b)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private void b(List<b.er> paramList)
  {
    paramList = paramList.iterator();
    Object localObject = getContext().getPackageManager().getInstalledApplications(128);
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localHashSet.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
    }
    while (paramList.hasNext())
    {
      localObject = (b.er)paramList.next();
      int j = 0;
      localObject = ((b.er)localObject).j.iterator();
      b.eo localEo;
      do
      {
        i = j;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        localEo = (b.eo)((Iterator)localObject).next();
      } while ((!"Android".equals(localEo.c)) || (!localHashSet.contains(localEo.b)));
      int i = 1;
      if (i == 0) {
        paramList.remove();
      }
    }
  }
  
  protected void a()
  {
    new AsyncTask()
    {
      final SharedPreferences a = i.this.getContext().getSharedPreferences("installed_apps", 0);
      boolean b;
      
      protected b a(Void... paramAnonymousVarArgs)
      {
        paramAnonymousVarArgs = this.a.getString("cached", null);
        Object localObject;
        if (paramAnonymousVarArgs == null)
        {
          paramAnonymousVarArgs = new b();
          paramAnonymousVarArgs.a = new j(i.this.getContext()).i();
          if (paramAnonymousVarArgs.a == null) {
            paramAnonymousVarArgs.a = new ArrayList();
          }
          i.a(i.this, paramAnonymousVarArgs.a);
          localObject = mobisocial.b.a.b(paramAnonymousVarArgs);
          this.a.edit().putString("cached", (String)localObject).apply();
        }
        else
        {
          this.b = true;
          localObject = (b)mobisocial.b.a.a(paramAnonymousVarArgs, b.class);
          if (localObject != null)
          {
            paramAnonymousVarArgs = (Void[])localObject;
            if (((b)localObject).a != null) {}
          }
          else
          {
            ((b)localObject).a = new ArrayList();
            paramAnonymousVarArgs = (Void[])localObject;
          }
        }
        i.b(i.this, paramAnonymousVarArgs.a);
        return paramAnonymousVarArgs;
      }
      
      protected void a(final b paramAnonymousB)
      {
        i.this.a(paramAnonymousB);
        if (this.b) {
          new AsyncTask()
          {
            protected List<b.er> a(Void... paramAnonymous2VarArgs)
            {
              paramAnonymous2VarArgs = new j(i.this.getContext()).i();
              if (paramAnonymous2VarArgs != null) {
                i.b(i.this, paramAnonymous2VarArgs);
              }
              return paramAnonymous2VarArgs;
            }
            
            protected void a(List<b.er> paramAnonymous2List)
            {
              if ((paramAnonymous2List != null) && (!i.a(i.this, paramAnonymous2List, paramAnonymousB.a)))
              {
                b localB = new b();
                localB.a = paramAnonymous2List;
                i.a(i.this, localB.a);
                paramAnonymous2List = mobisocial.b.a.b(localB);
                i.1.this.a.edit().putString("cached", paramAnonymous2List).apply();
                i.this.a(localB);
              }
            }
          }.executeOnExecutor(OmlibApiManager.THREAD_POOL_EXECUTOR, new Void[0]);
        }
      }
    }.executeOnExecutor(OmlibApiManager.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  public void a(b paramB)
  {
    super.deliverResult(paramB);
    if (paramB.a != null) {
      this.b = paramB;
    } else if (this.c != null) {
      this.c.run();
    }
    if (isStarted()) {
      super.deliverResult(this.b);
    }
  }
  
  protected void e()
  {
    if ((takeContentChanged()) || (this.b.a == null)) {
      forceLoad();
    }
  }
}
