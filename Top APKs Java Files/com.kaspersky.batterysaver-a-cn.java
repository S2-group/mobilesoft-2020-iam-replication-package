package a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class cn
{
  private static cn e;
  BroadcastReceiver a = new BroadcastReceiver()
  {
    public String a(Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent.getData() != null) {
        return paramAnonymousIntent.getData().getSchemeSpecificPart();
      }
      return null;
    }
    
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ((paramAnonymousIntent != null) && (paramAnonymousIntent.getAction() != null)) {
        if (paramAnonymousIntent.getAction().equals("android.intent.action.PACKAGE_ADDED"))
        {
          paramAnonymousContext = a(paramAnonymousIntent);
          if (paramAnonymousContext != null) {
            cn.a(cn.this, paramAnonymousContext);
          }
        }
        else if (paramAnonymousIntent.getAction().equals("android.intent.action.PACKAGE_REMOVED"))
        {
          paramAnonymousContext = a(paramAnonymousIntent);
          if (paramAnonymousContext != null) {
            cn.b(cn.this, paramAnonymousContext);
          }
        }
      }
    }
  };
  private List<String> b = new ArrayList();
  private boolean c;
  private boolean d;
  
  private cn() {}
  
  public static cn a()
  {
    if (e == null) {
      e = new cn();
    }
    return e;
  }
  
  private boolean a(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  private void b(String paramString)
  {
    try
    {
      this.b.add(paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  private void c(String paramString)
  {
    try
    {
      this.b.remove(paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public List<String> a(List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (a(str)) {
        localArrayList.add(str);
      }
    }
    return localArrayList;
  }
  
  public void a(Context paramContext)
  {
    try
    {
      this.c = false;
      this.b.clear();
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if ((localPackageInfo != null) && (!a(localPackageInfo))) {
          this.b.add(localPackageInfo.packageName);
        }
      }
      this.c = true;
      return;
    }
    finally {}
  }
  
  public boolean a(String paramString)
  {
    return this.b.contains(paramString.trim());
  }
  
  public void b(Context paramContext)
  {
    try
    {
      if (!this.d)
      {
        this.d = true;
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        localIntentFilter.addDataScheme("package");
        LocalBroadcastManager.getInstance(paramContext).registerReceiver(this.a, localIntentFilter);
      }
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public boolean b()
  {
    try
    {
      boolean bool = this.c;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void c(Context paramContext)
  {
    try
    {
      LocalBroadcastManager.getInstance(paramContext).unregisterReceiver(this.a);
      this.d = false;
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
}
