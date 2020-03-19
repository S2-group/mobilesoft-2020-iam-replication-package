package a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.kaspersky.batterysaver.AppInstallationReceiver;
import com.kaspersky.batterysaver.ApplicationContext;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class avz
{
  private static final String a = "avz";
  private final Set<b> b = new HashSet();
  private final axs c;
  private final Executor d;
  
  @Inject
  avz(@ApplicationContext Context paramContext, axs paramAxs, @Named("worker") Executor paramExecutor)
  {
    this.c = paramAxs;
    this.d = paramExecutor;
    paramContext.registerReceiver(new AppInstallationReceiver(), AppInstallationReceiver.a());
    b(paramContext);
  }
  
  private Set<a> a()
  {
    return (Set)bqs.b(this.c.e("key_prefs_app_monitor_app_list")).a(awe.a).a(bpo.c());
  }
  
  private void a(bpr<Set<a>> paramBpr)
  {
    Set localSet = a();
    paramBpr.accept(localSet);
    a(localSet);
  }
  
  private void a(Context paramContext, PackageManager paramPackageManager, Iterable<a> paramIterable, Collection<String> paramCollection)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      a localA = (a)paramIterable.next();
      try
      {
        PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(localA.a(), 0);
        paramCollection.add(localPackageInfo.packageName);
        if (localA.b() >= localPackageInfo.lastUpdateTime) {
          continue;
        }
        b(paramContext, localPackageInfo.packageName);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;) {}
      }
      a(localA.a());
    }
  }
  
  private void a(Context paramContext, PackageManager paramPackageManager, Collection<String> paramCollection, boolean paramBoolean)
  {
    paramPackageManager = paramPackageManager.getInstalledPackages(0).iterator();
    while (paramPackageManager.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramPackageManager.next();
      if ((paramBoolean) && (!paramCollection.contains(localPackageInfo.packageName))) {
        a(paramContext, localPackageInfo.packageName);
      }
    }
  }
  
  private static void a(String paramString, Collection<a> paramCollection)
  {
    paramCollection.removeAll((Collection)bqs.b(paramCollection).c(new awd(paramString)).a(bpo.d()));
  }
  
  private void a(Collection<a> paramCollection)
  {
    this.c.a("key_prefs_app_monitor_app_list", (List)bqs.b(paramCollection).a(awf.a).a(bpo.d())).a();
  }
  
  private void b(Context paramContext)
  {
    try
    {
      this.d.execute(new awa(this, paramContext));
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private void b(String paramString)
  {
    a(new awc(paramString));
  }
  
  private void c(Context paramContext, String paramString)
  {
    a(new awb(paramString, a.a(paramContext, paramString)));
  }
  
  public void a(b paramB)
  {
    this.b.add(paramB);
  }
  
  public final void a(Context paramContext, String paramString)
  {
    c(paramContext, paramString);
    paramContext = new HashSet(this.b).iterator();
    while (paramContext.hasNext()) {
      ((b)paramContext.next()).onPackageInstalled(paramString);
    }
  }
  
  public final void a(String paramString)
  {
    b(paramString);
    Iterator localIterator = new HashSet(this.b).iterator();
    while (localIterator.hasNext()) {
      ((b)localIterator.next()).onPackageRemoved(paramString);
    }
  }
  
  public void b(b paramB)
  {
    this.b.remove(paramB);
  }
  
  public final void b(Context paramContext, String paramString)
  {
    c(paramContext, paramString);
    paramContext = new HashSet(this.b).iterator();
    while (paramContext.hasNext()) {
      ((b)paramContext.next()).onPackageUpdated(paramString);
    }
  }
  
  static final class a
  {
    private final String a;
    private final long b;
    
    a(String paramString, long paramLong)
    {
      this.a = paramString;
      this.b = paramLong;
    }
    
    static a a(Context paramContext, String paramString)
    {
      try
      {
        paramContext = new a(paramString, paramContext.getPackageManager().getPackageInfo(paramString, 0).lastUpdateTime);
        return paramContext;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        for (;;) {}
      }
      return new a(paramString, System.currentTimeMillis());
    }
    
    static a a(String paramString)
    {
      paramString = paramString.split(String.valueOf(';'));
      return new a(paramString[0], Long.parseLong(paramString[1]));
    }
    
    String a()
    {
      return this.a;
    }
    
    long b()
    {
      return this.b;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.a);
      localStringBuilder.append(';');
      localStringBuilder.append(this.b);
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface b
  {
    public abstract void onPackageInstalled(String paramString);
    
    public abstract void onPackageRemoved(String paramString);
    
    public abstract void onPackageUpdated(String paramString);
  }
}
