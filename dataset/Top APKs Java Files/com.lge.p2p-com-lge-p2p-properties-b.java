package com.lge.p2p.properties;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.lge.p2p.a.u;
import com.lge.p2p.a.w;
import com.lge.p2p.g;
import com.lge.p2p.g.a.p;
import com.lge.p2p.h;
import com.lge.p2p.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class b
{
  private final Context a;
  private a b;
  
  public b(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public static void a(Context paramContext)
  {
    try
    {
      SharedPreferences.Editor localEditor = c.j(paramContext).edit();
      String str = paramContext.getPackageName();
      localEditor.putString("package_name", str);
      localEditor.putInt("version", paramContext.getPackageManager().getPackageInfo(str, 0).versionCode);
      localEditor.commit();
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void a(Context paramContext, Bundle paramBundle, String paramString1, Object paramObject, String paramString2)
  {
    paramContext = c.l(paramContext).edit();
    if ((paramObject instanceof String)) {
      paramContext.putString(paramString2, paramBundle.getString(paramString1)).commit();
    }
    do
    {
      return;
      if ((paramObject instanceof Boolean))
      {
        paramContext.putBoolean(paramString2, paramBundle.getBoolean(paramString1)).commit();
        return;
      }
      if ((paramObject instanceof Integer))
      {
        paramContext.putInt(paramString2, paramBundle.getInt(paramString1)).commit();
        return;
      }
      if ((paramObject instanceof Float))
      {
        paramContext.putFloat(paramString2, paramBundle.getFloat(paramString1)).commit();
        return;
      }
    } while (!(paramObject instanceof Long));
    paramContext.putLong(paramString2, paramBundle.getLong(paramString1)).commit();
  }
  
  public static void a(Context paramContext, String paramString)
  {
    if (paramString != null)
    {
      com.lge.p2p.h.a.c("Remove application property after uninstall it.");
      paramString = paramString.split(":");
      paramString = paramString[(paramString.length - 1)];
      if (paramString != null)
      {
        Object localObject = c.l(paramContext).getAll();
        paramContext = c.l(paramContext).edit();
        localObject = ((Map)localObject).keySet().iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str = (String)((Iterator)localObject).next();
          if (str.startsWith(paramString)) {
            paramContext.remove(str).commit();
          }
        }
      }
    }
  }
  
  private static void a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {
      c.a(paramContext, true);
    }
    c.j(paramContext).edit().putBoolean("is_connected", paramBoolean).commit();
  }
  
  private void a(boolean paramBoolean)
  {
    com.lge.p2p.h.a.c("QPair connected");
    d();
    if (paramBoolean)
    {
      c(this.a);
      d(this.a);
    }
  }
  
  public static void b(Context paramContext)
  {
    SharedPreferences.Editor localEditor = c.j(paramContext).edit();
    String str1 = com.lge.p2p.g.a.c.a(paramContext).h();
    String str2 = c.c(paramContext);
    com.lge.p2p.h.a.e("deviceName : " + str1);
    com.lge.p2p.h.a.e("Properties.getLocalName : " + str2);
    if ((!"QPair Device".equals(str1)) || (str2.length() == 0)) {
      localEditor.putString("device_name", str1).commit();
    }
    localEditor.putString("bluetooth_address", com.lge.p2p.g.a.c.a(paramContext).i()).commit();
  }
  
  private void c()
  {
    com.lge.p2p.h.a.c("QPair disconnected");
    if (this.b != null)
    {
      this.a.unregisterReceiver(this.b);
      this.b = null;
    }
  }
  
  public static void c(Context paramContext)
  {
    com.lge.p2p.h.a.c("Write new application property after install it.");
    List localList = paramContext.getApplicationContext().getPackageManager().getInstalledApplications(128);
    int i = 0;
    while (i < localList.size())
    {
      Object localObject1 = (ApplicationInfo)localList.get(i);
      Bundle localBundle = ((ApplicationInfo)localObject1).metaData;
      localObject1 = ((ApplicationInfo)localObject1).packageName;
      if (localBundle != null)
      {
        Iterator localIterator = localBundle.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          if (str1.startsWith("com.lge.qpair.property/local/"))
          {
            Object localObject2 = localBundle.get(str1);
            Object localObject3 = str1.split("/");
            String str2 = localObject3[(localObject3.length - 2)];
            localObject3 = localObject3[(localObject3.length - 1)];
            if (str2.equals(localObject1)) {
              a(paramContext, localBundle, str1, localObject2, str2 + "/" + (String)localObject3);
            }
          }
        }
      }
      i += 1;
    }
  }
  
  private void d()
  {
    if (this.b == null)
    {
      this.b = new a();
      IntentFilter localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
      localIntentFilter.addAction("android.intent.action.PACKAGE_INSTALL");
      localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
      localIntentFilter.addDataScheme("package");
      this.a.registerReceiver(this.b, localIntentFilter);
    }
  }
  
  public static void d(Context paramContext)
  {
    com.lge.p2p.h.a.c("Remove application property after uninstall it and activate QPair.");
    Object localObject1 = new ArrayList(Arrays.asList(new String[] { "call", "message", "hotspot", "hotspot_auto", "sns_notification", "qmemo", "ticker", "ring" }));
    Map localMap = c.l(paramContext).getAll();
    Object localObject2 = new HashSet();
    Object localObject3 = localMap.keySet().iterator();
    while (((Iterator)localObject3).hasNext())
    {
      String[] arrayOfString = ((String)((Iterator)localObject3).next()).split("/");
      if (arrayOfString != null) {
        ((Set)localObject2).add(arrayOfString[0]);
      }
    }
    ((Set)localObject2).removeAll((Collection)localObject1);
    if (((Set)localObject2).size() != 0)
    {
      localObject1 = paramContext.getApplicationContext().getPackageManager();
      paramContext = c.l(paramContext).edit();
      localObject2 = ((Set)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        Iterator localIterator;
        try
        {
          ((PackageManager)localObject1).getApplicationLabel(((PackageManager)localObject1).getApplicationInfo((String)localObject3, 8192)).toString();
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localIterator = localMap.keySet().iterator();
        }
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          if (str.contains((CharSequence)localObject3)) {
            paramContext.remove(str).commit();
          }
        }
      }
    }
  }
  
  private static void e(Context paramContext)
  {
    a(paramContext);
    b(paramContext);
  }
  
  public void a()
  {
    a.a.a.c.a().a(this);
    e(this.a);
    a(false);
  }
  
  public void b()
  {
    a.a.a.c.a().c(this);
    a(this.a, false);
    c();
  }
  
  public void onEvent(u paramU)
  {
    a(this.a, true);
    a(true);
  }
  
  public void onEvent(w paramW)
  {
    a(this.a, false);
    c();
  }
  
  public void onEvent(p paramP)
  {
    c.a(this.a, false);
  }
  
  public void onEvent(g paramG)
  {
    if (paramG.a)
    {
      c.d(this.a);
      e(this.a);
      return;
    }
    c.j(this.a).edit().putBoolean("is_on", false).commit();
  }
  
  public void onEvent(i paramI)
  {
    c.j(this.a).edit().putBoolean("is_on", true).commit();
    a.a.a.c.a().d(new h());
  }
}
