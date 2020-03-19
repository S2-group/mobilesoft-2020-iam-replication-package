import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class vv
  extends BroadcastReceiver
{
  private final Set<String> a = Collections.synchronizedSet(new HashSet());
  private final ExecutorService b = Executors.newSingleThreadExecutor();
  private final vv.a c;
  
  vv(vv.a paramA)
  {
    this.c = paramA;
  }
  
  void a(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      this.a.add(localApplicationInfo.packageName);
    }
    localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject).addDataScheme("package");
    paramContext.getApplicationContext().registerReceiver(this, (IntentFilter)localObject);
  }
  
  String[] a()
  {
    if (this.a.isEmpty()) {
      return null;
    }
    return (String[])this.a.toArray(new String[this.a.size()]);
  }
  
  public void onReceive(final Context paramContext, final Intent paramIntent)
  {
    paramContext = paramIntent.getAction();
    paramIntent = paramIntent.getData();
    this.b.execute(new Runnable()
    {
      public void run()
      {
        String str;
        if (paramIntent != null)
        {
          str = paramIntent.getEncodedSchemeSpecificPart();
          if (!"android.intent.action.PACKAGE_ADDED".equals(paramContext)) {
            break label64;
          }
          vv.a(vv.this).add(str);
        }
        for (;;)
        {
          if (vv.b(vv.this) != null) {
            vv.b(vv.this).e();
          }
          return;
          label64:
          if ("android.intent.action.PACKAGE_REMOVED".equals(paramContext)) {
            vv.a(vv.this).remove(str);
          }
        }
      }
    });
  }
  
  static abstract interface a
  {
    public abstract void e();
  }
}
