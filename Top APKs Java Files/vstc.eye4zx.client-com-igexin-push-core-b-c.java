package com.igexin.push.core.b;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.RemoteException;
import com.igexin.push.core.g;
import com.igexin.sdk.aidl.ICACallback;
import java.util.List;

public class c
{
  private static c a;
  
  public c() {}
  
  public static c a()
  {
    if (a == null) {
      a = new c();
    }
    return a;
  }
  
  public int a(String paramString, i paramI)
  {
    int k = 0;
    if (paramString != null)
    {
      paramString = e.a().a(paramString);
      long l = Long.valueOf(paramString.c()).longValue();
      int i;
      if (System.currentTimeMillis() > l)
      {
        i = -1;
        return i;
      }
      paramString = paramString.e();
      int j = 0;
      for (;;)
      {
        if (j >= paramString.size()) {
          break label85;
        }
        i = k;
        if (paramString.get(j) == paramI) {
          break;
        }
        j += 1;
      }
    }
    return -1;
    label85:
    return -2;
  }
  
  public String a(String paramString)
  {
    List localList = g.g.getPackageManager().getInstalledPackages(64);
    int i = 0;
    while (i < localList.size())
    {
      Object localObject = (PackageInfo)localList.get(i);
      if (((PackageInfo)localObject).packageName.equals(paramString))
      {
        localObject = ((PackageInfo)localObject).signatures;
        if ((localObject != null) && (localObject.length > 0)) {
          return localObject[0].toCharsString();
        }
      }
      i += 1;
    }
    return null;
  }
  
  public void a(Intent paramIntent)
  {
    Object localObject;
    long l1;
    if ((paramIntent != null) && (paramIntent.hasExtra("action")))
    {
      localObject = paramIntent.getStringExtra("action");
      if (!((String)localObject).equals("connected")) {
        break label192;
      }
      String str = paramIntent.getStringExtra("pkgname");
      localObject = b.a().a(str);
      long l3 = System.currentTimeMillis();
      h localH = e.a().a(str);
      paramIntent = localH.b();
      long l2 = localH.c();
      l1 = l2;
      if (l3 > l2)
      {
        paramIntent = com.igexin.a.b.a.a(str + "-" + l3);
        l1 = 604800L + l3;
        localH.b(paramIntent);
        localH.a(l1);
      }
      e.a().a(str, localH);
    }
    try
    {
      if (((a)localObject).b() != null) {
        ((a)localObject).b().onAuthenticated(g.e, "com.igexin.sdk.PushService", paramIntent, l1);
      }
      for (;;)
      {
        try
        {
          if (((a)localObject).b() != null) {
            g.g.unbindService(((a)localObject).a());
          }
          return;
        }
        catch (Exception paramIntent)
        {
          label192:
          return;
        }
        if (!((String)localObject).equals("disconnected")) {
          continue;
        }
        paramIntent = paramIntent.getStringExtra("pkgname");
        paramIntent = b.a().a(paramIntent);
        try
        {
          if (paramIntent.b() == null) {
            continue;
          }
          g.g.unbindService(paramIntent.a());
          return;
        }
        catch (Exception paramIntent) {}
      }
    }
    catch (RemoteException paramIntent)
    {
      for (;;) {}
    }
  }
  
  public void b(Intent paramIntent)
  {
    Object localObject;
    String str;
    if (paramIntent.getStringExtra("action").equals("com.igexin.sdk.action.refreshls"))
    {
      localObject = paramIntent.getStringExtra("callback_pkgname");
      str = paramIntent.getStringExtra("callback_classname");
      paramIntent = new a();
      paramIntent.a((String)localObject);
      paramIntent.a(new d(this));
      b.a().a((String)localObject, paramIntent);
    }
    try
    {
      localObject = g.g.createPackageContext((String)localObject, 3);
      localObject = new Intent((Context)localObject, ((Context)localObject).getClassLoader().loadClass(str));
      g.g.bindService((Intent)localObject, paramIntent.a(), 1);
      return;
    }
    catch (Exception paramIntent) {}
  }
}
