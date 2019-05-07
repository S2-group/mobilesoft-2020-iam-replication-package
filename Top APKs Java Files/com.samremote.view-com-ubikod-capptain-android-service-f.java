package com.ubikod.capptain.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.ubikod.capptain.bx;
import com.ubikod.capptain.l;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

public final class f
  extends BroadcastReceiver
{
  private final l a;
  private final Context b;
  private final SharedPreferences c;
  private boolean d;
  private a e;
  
  f(l paramL, a paramA)
  {
    this.a = paramL;
    this.b = a.a();
    this.c = this.b.getSharedPreferences("capptain.packages", 0);
    this.e = paramA;
  }
  
  private void a(Collection paramCollection, String paramString)
  {
    if ((paramCollection != null) && (!paramCollection.isEmpty()))
    {
      paramString = new bx(paramString);
      paramString.c(new JSONArray(paramCollection).toString());
      this.a.a(paramString);
    }
  }
  
  private void a(Collection paramCollection1, Collection paramCollection2)
  {
    a(paramCollection1, "install");
    a(paramCollection2, "uninstall");
  }
  
  private static boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) == 0;
  }
  
  final void a()
  {
    if (this.d) {
      return;
    }
    this.d = true;
    Object localObject2 = new ArrayList();
    Object localObject1 = new HashSet(this.c.getAll().keySet());
    Object localObject3 = this.b.getPackageManager().getInstalledApplications(0).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject3).next();
      String str = localApplicationInfo.packageName;
      if ((a(localApplicationInfo)) && (!((Collection)localObject1).remove(str))) {
        ((Collection)localObject2).add(str);
      }
    }
    a((Collection)localObject2, (Collection)localObject1);
    localObject3 = this.c.edit();
    localObject2 = ((Collection)localObject2).iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((SharedPreferences.Editor)localObject3).putString((String)((Iterator)localObject2).next(), "");
    }
    localObject1 = ((Collection)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      ((SharedPreferences.Editor)localObject3).remove((String)((Iterator)localObject1).next());
    }
    ((SharedPreferences.Editor)localObject3).commit();
    localObject1 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject1).addDataScheme("package");
    this.b.registerReceiver(this, (IntentFilter)localObject1);
  }
  
  final void b()
  {
    if (this.d)
    {
      this.d = false;
      this.b.unregisterReceiver(this);
    }
  }
  
  final void c()
  {
    b();
    this.e = null;
  }
  
  protected final void finalize()
  {
    new StringBuilder("finalize() ").append(this).toString();
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    new StringBuilder("Package event ").append(paramIntent).toString();
    paramContext = paramIntent.getData().getSchemeSpecificPart();
    boolean bool2 = "android.intent.action.PACKAGE_ADDED".equals(paramIntent.getAction());
    if (this.d) {}
    try
    {
      bool1 = a(this.b.getPackageManager().getApplicationInfo(paramContext, 0));
      if (bool1)
      {
        paramIntent = this.c.edit();
        localSet = Collections.singleton(paramContext);
        if (bool2)
        {
          a(localSet, null);
          paramIntent.putString(paramContext, "");
          paramIntent.commit();
        }
      }
      else
      {
        if (this.e != null)
        {
          if (!bool2) {
            break label154;
          }
          this.e.b();
        }
        return;
      }
    }
    catch (PackageManager.NameNotFoundException paramIntent)
    {
      for (;;)
      {
        Set localSet;
        boolean bool1 = true;
        continue;
        a(null, localSet);
        paramIntent.remove(paramContext);
      }
      label154:
      this.e.a(paramContext);
    }
  }
  
  static abstract interface a
  {
    public abstract void a(String paramString);
    
    public abstract void b();
  }
}
