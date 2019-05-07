package clear.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class dw
  implements dr.a
{
  private static final String a = dw.class.getSimpleName();
  private static dw g;
  private final Context b;
  private final Object c = new Object();
  private final BroadcastReceiver d = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = null;
      Uri localUri = paramAnonymousIntent.getData();
      if (localUri != null) {
        paramAnonymousContext = localUri.getSchemeSpecificPart();
      }
      if (TextUtils.isEmpty(paramAnonymousContext)) {
        return;
      }
      if ("android.intent.action.PACKAGE_REMOVED".equals(paramAnonymousIntent.getAction()))
      {
        if (paramAnonymousIntent.getBooleanExtra("android.intent.extra.REPLACING", false))
        {
          dw.a(dw.this, paramAnonymousContext);
          return;
        }
        dw.b(dw.this, paramAnonymousContext);
        return;
      }
      dw.a(dw.this, paramAnonymousContext);
    }
  };
  private HashMap<String, ApplicationInfo> e;
  private HashMap<String, PackageInfo> f;
  
  private dw(Context paramContext)
  {
    this.b = paramContext;
    try
    {
      paramContext = new IntentFilter();
      paramContext.addAction("android.intent.action.PACKAGE_ADDED");
      paramContext.addAction("android.intent.action.PACKAGE_REMOVED");
      paramContext.addAction("android.intent.action.PACKAGE_CHANGED");
      paramContext.addAction("android.intent.action.PACKAGE_INSTALL");
      paramContext.addAction("android.intent.action.PACKAGE_REPLACED");
      paramContext.addAction("android.intent.action.PACKAGE_RESTARTED");
      paramContext.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
      paramContext.addDataScheme("package");
      eg.a(this.b, this.d, paramContext);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static dw a(Context paramContext)
  {
    try
    {
      if (g == null) {
        g = new dw(paramContext);
      }
      paramContext = g;
      return paramContext;
    }
    finally {}
  }
  
  private final void a(String paramString)
  {
    synchronized (this.c)
    {
      if (this.e != null) {
        this.e.remove(paramString);
      }
      if (this.f != null) {
        this.f.remove(paramString);
      }
      return;
    }
  }
  
  private final void b(String paramString)
  {
    synchronized (this.c)
    {
      try
      {
        Object localObject2;
        if (this.e != null)
        {
          localObject2 = this.b.getPackageManager().getApplicationInfo(paramString, 0);
          if (localObject2 != null) {
            this.e.put(paramString, localObject2);
          }
        }
        if (this.f != null)
        {
          localObject2 = this.b.getPackageManager().getPackageInfo(paramString, 0);
          if (localObject2 != null) {
            this.f.put(paramString, localObject2);
          }
        }
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          Log.e(a, paramString.getMessage(), paramString);
        }
      }
      return;
    }
  }
  
  public PackageInfo a(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    Object localObject = this.c;
    PackageInfo localPackageInfo;
    if (paramInt == 0) {
      localPackageInfo = null;
    }
    try
    {
      if (this.f != null) {
        localPackageInfo = (PackageInfo)this.f.get(paramString);
      }
      if (localPackageInfo != null) {
        return localPackageInfo;
      }
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, paramInt);
      return paramPackageManager;
    }
    finally {}
  }
  
  public List<ApplicationInfo> a(PackageManager paramPackageManager, int paramInt)
  {
    Object localObject = this.c;
    if (paramInt != 0) {}
    try
    {
      paramPackageManager = paramPackageManager.getInstalledApplications(paramInt);
      return paramPackageManager;
    }
    finally {}
    if (this.e == null)
    {
      paramPackageManager = paramPackageManager.getInstalledApplications(paramInt);
      if ((paramPackageManager == null) || (paramPackageManager.size() <= 0))
      {
        paramPackageManager = new ArrayList(0);
        return paramPackageManager;
      }
      this.e = new HashMap();
      localIterator = paramPackageManager.iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        this.e.put(localApplicationInfo.packageName, localApplicationInfo);
      }
      return paramPackageManager;
    }
    paramPackageManager = new ArrayList();
    Iterator localIterator = this.e.values().iterator();
    while (localIterator.hasNext()) {
      paramPackageManager.add((ApplicationInfo)localIterator.next());
    }
    return paramPackageManager;
  }
  
  public List<ResolveInfo> a(PackageManager paramPackageManager, Intent paramIntent, int paramInt)
  {
    synchronized (this.c)
    {
      paramPackageManager = paramPackageManager.queryIntentActivities(paramIntent, paramInt);
      return paramPackageManager;
    }
  }
}
