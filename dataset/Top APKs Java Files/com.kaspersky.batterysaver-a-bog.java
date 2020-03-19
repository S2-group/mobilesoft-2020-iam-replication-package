package a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class bog
  extends BroadcastReceiver
{
  @NonNull
  private final Set<String> a = Collections.synchronizedSet(new HashSet());
  @NonNull
  private final ExecutorService b = Executors.newSingleThreadExecutor();
  @Nullable
  private final a c;
  
  bog(@Nullable a paramA)
  {
    this.c = paramA;
  }
  
  void a(@NonNull Context paramContext)
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
  
  @Nullable
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
        if (paramIntent != null)
        {
          String str = paramIntent.getEncodedSchemeSpecificPart();
          if ("android.intent.action.PACKAGE_ADDED".equals(paramContext)) {
            bog.a(bog.this).add(str);
          } else if ("android.intent.action.PACKAGE_REMOVED".equals(paramContext)) {
            bog.a(bog.this).remove(str);
          }
        }
        if (bog.b(bog.this) != null) {
          bog.b(bog.this).f();
        }
      }
    });
  }
  
  static abstract interface a
  {
    public abstract void f();
  }
}
