package com.softartstudio.carwebguru.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.util.Log;
import com.softartstudio.carwebguru.j;
import com.softartstudio.carwebguru.p.o;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class a
{
  protected Context a = null;
  public ArrayList<d> b = null;
  public ArrayList<String> c = null;
  
  public a(Context paramContext)
  {
    this.a = paramContext;
    this.b = new ArrayList();
    this.c = new ArrayList();
    d();
  }
  
  private int a(List<ResolveInfo> paramList)
  {
    c("scanList");
    PackageManager localPackageManager = this.a.getApplicationContext().getPackageManager();
    int i = 0;
    while (i < paramList.size())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramList.get(i);
      Object localObject = "";
      try
      {
        if (localResolveInfo.activityInfo != null)
        {
          String str = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(localResolveInfo.activityInfo.packageName, 0)).toString();
          localObject = str;
        }
        else
        {
          localObject = "Unknown";
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
      if (!d(localResolveInfo.activityInfo.packageName))
      {
        localObject = a((String)localObject);
        if (localResolveInfo.activityInfo != null) {
          ((d)localObject).a(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name);
        }
        if (localResolveInfo.serviceInfo != null) {
          ((d)localObject).a(localResolveInfo.serviceInfo.packageName, localResolveInfo.serviceInfo.name);
        }
        if (Build.VERSION.SDK_INT >= 19) {
          localObject = localResolveInfo.providerInfo;
        }
      }
      i += 1;
    }
    return this.b.size();
  }
  
  public static void c(String paramString)
  {
    if (j.a) {
      Log.d("SAS", paramString);
    }
  }
  
  private void d()
  {
    this.c.add("com.ghisler.android.TotalCommander");
    this.c.add("com.android.chrome");
    this.c.add("com.google.android.youtube");
    this.c.add("com.miui.cit");
    this.c.add("com.opera.browser");
    this.c.add("org.telegram.messenger");
    this.c.add("cn.wps.moffice_eng");
  }
  
  private boolean d(String paramString)
  {
    return this.c.contains(paramString);
  }
  
  public int a(boolean paramBoolean1, boolean paramBoolean2, String paramString)
  {
    if (j.a) {
      c("getListApps");
    }
    a();
    PackageManager localPackageManager = this.a.getApplicationContext().getPackageManager();
    List localList = localPackageManager.getInstalledApplications(0);
    if (localList == null) {
      return 0;
    }
    Object localObject1;
    StringBuilder localStringBuilder;
    if (j.a)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("All APPS from system, Count: ");
      ((StringBuilder)localObject1).append(localList.size());
      localStringBuilder = new StringBuilder(((StringBuilder)localObject1).toString());
    }
    else
    {
      localStringBuilder = null;
    }
    int j = 0;
    while (j < localList.size())
    {
      Object localObject2 = (ApplicationInfo)localList.get(j);
      localObject1 = localPackageManager.getLaunchIntentForPackage(((ApplicationInfo)localObject2).packageName);
      paramBoolean2 = true;
      int i;
      if (localObject1 != null) {
        i = 1;
      } else {
        i = 0;
      }
      if ((((ApplicationInfo)localObject2).flags & 0x1) != 0) {
        paramBoolean1 = true;
      } else {
        paramBoolean1 = false;
      }
      if ((((ApplicationInfo)localObject2).flags & 0x800000) == 0) {
        paramBoolean2 = false;
      }
      if (j.a)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("appName: ");
        ((StringBuilder)localObject1).append(((ApplicationInfo)localObject2).loadLabel(localPackageManager).toString());
        ((StringBuilder)localObject1).append("\n");
        localStringBuilder.append(((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("packageName: ");
        ((StringBuilder)localObject1).append(((ApplicationInfo)localObject2).packageName);
        ((StringBuilder)localObject1).append("\n");
        localStringBuilder.append(((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("className: ");
        ((StringBuilder)localObject1).append(((ApplicationInfo)localObject2).className);
        ((StringBuilder)localObject1).append("\n");
        localStringBuilder.append(((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("sourceDir: ");
        ((StringBuilder)localObject1).append(((ApplicationInfo)localObject2).sourceDir);
        ((StringBuilder)localObject1).append("\n");
        localStringBuilder.append(((StringBuilder)localObject1).toString());
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("is_system: ");
        ((StringBuilder)localObject1).append(paramBoolean1);
        ((StringBuilder)localObject1).append(", Installed: ");
        ((StringBuilder)localObject1).append(paramBoolean2);
        ((StringBuilder)localObject1).append(" flags: ");
        ((StringBuilder)localObject1).append(((ApplicationInfo)localObject2).flags);
        ((StringBuilder)localObject1).append("\n");
        localStringBuilder.append(((StringBuilder)localObject1).toString());
      }
      if (((ApplicationInfo)localObject2).packageName.equals(paramString)) {
        i = 0;
      }
      d localD;
      if (i != 0)
      {
        localD = a(((ApplicationInfo)localObject2).loadLabel(localPackageManager).toString());
        localD.a(((ApplicationInfo)localObject2).packageName, ((ApplicationInfo)localObject2).className);
        localD.g = ((ApplicationInfo)localObject2).sourceDir;
        localD.k = paramBoolean1;
      }
      for (;;)
      {
        try
        {
          if (Build.VERSION.SDK_INT > 8) {
            localObject1 = localPackageManager.getPackageInfo(((ApplicationInfo)localObject2).packageName, 0);
          }
        }
        catch (Exception localException1)
        {
          continue;
        }
        try
        {
          localD.h = ((PackageInfo)localObject1).firstInstallTime;
        }
        catch (Exception localException3) {}
      }
      localObject1 = null;
      break label583;
      localObject1 = null;
      c("Can not extract install date");
      try
      {
        label583:
        localObject2 = new File(((ApplicationInfo)localObject2).sourceDir);
        if (!((File)localObject2).exists()) {
          break label624;
        }
        localD.i = ((File)localObject2).lastModified();
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      c("can not extract update date");
      label624:
      if (j.a)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Launch Activity: ");
        ((StringBuilder)localObject2).append(localPackageManager.getLaunchIntentForPackage(((PackageInfo)localObject1).packageName));
        localStringBuilder.append(((StringBuilder)localObject2).toString());
      }
      if (j.a) {
        localStringBuilder.append("............................\n");
      }
      j += 1;
    }
    if (j.a)
    {
      paramString = new StringBuilder();
      paramString.append(com.softartstudio.carwebguru.p.m.e());
      paramString.append("app-list.txt");
      paramString = paramString.toString();
      if (!o.f(paramString)) {
        o.e(paramString, localStringBuilder.toString());
      }
      c();
    }
    return this.b.size();
  }
  
  public d a(String paramString)
  {
    paramString = new d(this.a, paramString);
    this.b.add(paramString);
    return paramString;
  }
  
  public void a()
  {
    this.b.clear();
  }
  
  public boolean a(int paramInt)
  {
    return (paramInt >= 0) && (paramInt < this.b.size());
  }
  
  public int b()
  {
    c("getListQueryMB");
    a();
    return a(this.a.getApplicationContext().getPackageManager().queryBroadcastReceivers(new Intent("android.intent.action.MEDIA_BUTTON"), 0));
  }
  
  public d b(int paramInt)
  {
    if (a(paramInt)) {
      return (d)this.b.get(paramInt);
    }
    return null;
  }
  
  public d b(String paramString)
  {
    Object localObject2 = this.a.getApplicationContext().getPackageManager();
    Object localObject1 = "Unknown";
    try
    {
      localObject2 = ((PackageManager)localObject2).getApplicationLabel(((PackageManager)localObject2).getApplicationInfo(paramString, 0)).toString();
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    localObject1 = new d(this.a, (String)localObject1);
    ((d)localObject1).a(paramString, "");
    return localObject1;
  }
  
  public void c()
  {
    boolean bool = j.a;
    int j = 0;
    Object localObject1;
    int i;
    Object localObject2;
    if (bool)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("debugList Total: ");
      ((StringBuilder)localObject1).append(this.b.size());
      ((StringBuilder)localObject1).append(" items");
      c(((StringBuilder)localObject1).toString());
      i = 0;
      while (i < this.b.size())
      {
        localObject1 = (d)this.b.get(i);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(" > aPackage: ");
        ((StringBuilder)localObject2).append(((d)localObject1).b());
        c(((StringBuilder)localObject2).toString());
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(" > aClass: ");
        ((StringBuilder)localObject2).append(((d)localObject1).c());
        c(((StringBuilder)localObject2).toString());
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(" > sPackage: ");
        ((StringBuilder)localObject2).append(((d)localObject1).d());
        c(((StringBuilder)localObject2).toString());
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(" > sClass: ");
        ((StringBuilder)localObject2).append(((d)localObject1).e());
        c(((StringBuilder)localObject2).toString());
        c(".............");
        i += 1;
      }
      c("------------");
    }
    if (j.b)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("debugList APP Total: ");
      ((StringBuilder)localObject1).append(this.b.size());
      ((StringBuilder)localObject1).append(" items");
      ((StringBuilder)localObject1).append("\n");
      localObject1 = new StringBuilder(((StringBuilder)localObject1).toString());
      i = j;
      while (i < this.b.size())
      {
        localObject2 = (d)this.b.get(i);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(" > Title: ");
        localStringBuilder.append(((d)localObject2).a());
        localStringBuilder.append("\n");
        ((StringBuilder)localObject1).append(localStringBuilder.toString());
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(" > aPackage: ");
        localStringBuilder.append(((d)localObject2).b());
        localStringBuilder.append("\n");
        ((StringBuilder)localObject1).append(localStringBuilder.toString());
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(" > aClass: ");
        localStringBuilder.append(((d)localObject2).c());
        localStringBuilder.append("\n");
        ((StringBuilder)localObject1).append(localStringBuilder.toString());
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(" > sPackage: ");
        localStringBuilder.append(((d)localObject2).d());
        localStringBuilder.append("\n");
        ((StringBuilder)localObject1).append(localStringBuilder.toString());
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(" > sClass: ");
        localStringBuilder.append(((d)localObject2).e());
        localStringBuilder.append("\n");
        ((StringBuilder)localObject1).append(localStringBuilder.toString());
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(" > sFolder: ");
        localStringBuilder.append(((d)localObject2).g);
        localStringBuilder.append("\n");
        ((StringBuilder)localObject1).append(localStringBuilder.toString());
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(".............");
        ((StringBuilder)localObject2).append("\n");
        ((StringBuilder)localObject1).append(((StringBuilder)localObject2).toString());
        i += 1;
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("------------");
      ((StringBuilder)localObject2).append("\n");
      ((StringBuilder)localObject1).append(((StringBuilder)localObject2).toString());
      com.softartstudio.carwebguru.m.a(((StringBuilder)localObject1).toString());
    }
  }
}
