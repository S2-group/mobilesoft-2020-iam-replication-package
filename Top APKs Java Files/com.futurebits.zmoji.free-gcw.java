import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class gcw
{
  private static volatile gcw a;
  private List<PackageInfo> b;
  private Set<String> c = new HashSet();
  private List<gcw.a> d;
  
  private gcw()
  {
    try
    {
      this.b = fze.e().getPackageManager().getInstalledPackages(0);
      if ((this.b != null) && (this.b.size() > 0))
      {
        localObject1 = this.b.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (PackageInfo)((Iterator)localObject1).next();
          this.c.add(((PackageInfo)localObject2).packageName);
        }
      }
    }
    catch (Exception localException)
    {
      Object localObject1;
      Object localObject2;
      for (;;) {}
    }
    localObject1 = new IntentFilter();
    ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject1).addDataScheme("package");
    localObject2 = new gcw.b(null);
    fze.e().registerReceiver((BroadcastReceiver)localObject2, (IntentFilter)localObject1);
  }
  
  public static gcw a()
  {
    if (a == null) {
      try
      {
        if (a == null) {
          a = new gcw();
        }
      }
      finally {}
    }
    return a;
  }
  
  private void c()
  {
    if (this.d != null)
    {
      Iterator localIterator = this.d.iterator();
      while (localIterator.hasNext()) {
        ((gcw.a)localIterator.next()).a();
      }
    }
  }
  
  private void d()
  {
    if (this.d != null)
    {
      Iterator localIterator = this.d.iterator();
      while (localIterator.hasNext()) {
        ((gcw.a)localIterator.next()).b();
      }
    }
  }
  
  private void e()
  {
    if (this.d != null)
    {
      Iterator localIterator = this.d.iterator();
      while (localIterator.hasNext()) {
        ((gcw.a)localIterator.next()).c();
      }
    }
  }
  
  public boolean a(String paramString)
  {
    return this.c.contains(paramString);
  }
  
  public List<PackageInfo> b()
  {
    if (this.b != null) {
      return new ArrayList(this.b);
    }
    return Collections.emptyList();
  }
  
  public static abstract interface a
  {
    public abstract void a();
    
    public abstract void b();
    
    public abstract void c();
  }
  
  class b
    extends BroadcastReceiver
  {
    private b() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getAction();
      String str = paramIntent.getData().getEncodedSchemeSpecificPart();
      if (str == null) {
        return;
      }
      boolean bool = paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false);
      if ("android.intent.action.PACKAGE_ADDED".equals(paramContext))
      {
        if (bool)
        {
          gcw.a(gcw.this);
          return;
        }
        gcw.b(gcw.this).add(str);
        gcw.c(gcw.this);
        return;
      }
      if ("android.intent.action.PACKAGE_REMOVED".equals(paramContext))
      {
        gcw.b(gcw.this).remove(str);
        gcw.d(gcw.this);
      }
    }
  }
}
