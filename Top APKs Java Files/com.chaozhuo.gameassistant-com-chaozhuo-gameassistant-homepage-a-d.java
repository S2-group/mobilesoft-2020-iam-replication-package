package com.chaozhuo.gameassistant.homepage.a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.chaozhuo.gameassistant.XApp;
import com.chaozhuo.gameassistant.utils.ChannelUtils;
import com.chaozhuo.gameassistant.utils.w;
import com.chaozhuo.superme.b;
import com.chaozhuo.superme.client.SupermeCore;
import com.chaozhuo.superme.remote.InstalledAppInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jdeferred.Promise;
import org.jdeferred.android.AndroidDeferredManager;

public class d
{
  private static final String a = "com.chaozhuo.gameassistant_SP_APP_MANAGER";
  private static final String b = "KEY_APP_ADD_LIST";
  private static final String c = "KEY_REMOVE_QQ_ONCE";
  private static final String d = "KEY_REMOVE_FB_ONCE2";
  private static final String e = "KEY_ADD_APPS_ONCE";
  private static final String f = "KEY_REMOVE_GAPPS_ONCE";
  private static d g;
  
  private d() {}
  
  public static d a()
  {
    if (g == null) {
      g = new d();
    }
    return g;
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  private static boolean b(PackageInfo paramPackageInfo)
  {
    boolean bool1 = false;
    try
    {
      if (!b.a.contains(paramPackageInfo.packageName))
      {
        boolean bool2 = b.b.contains(paramPackageInfo.packageName);
        if (!bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception paramPackageInfo)
    {
      paramPackageInfo.printStackTrace();
    }
    return false;
  }
  
  public List<String> a(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    String str = SupermeCore.a().m();
    PackageManager localPackageManager = XApp.a().getPackageManager();
    Object localObject = localPackageManager.getInstalledPackages(0);
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((!str.equals(localPackageInfo.packageName)) && (!a(localPackageInfo)) && (!b(localPackageInfo)) && (!com.chaozhuo.superme.client.b.c.d(localPackageInfo.packageName)) && ((paramBoolean) || (!ChannelUtils.is64bitChannel()) || (a(localPackageInfo.applicationInfo.nativeLibraryDir))) && ((paramBoolean) || (ChannelUtils.is64bitChannel()) || (!a(localPackageInfo.applicationInfo.nativeLibraryDir))))
      {
        localIntent.setPackage(localPackageInfo.packageName);
        List localList = localPackageManager.queryIntentActivities(localIntent, 0);
        if ((localList != null) && (!localList.isEmpty())) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
    }
    return localArrayList;
  }
  
  public void a(List<com.chaozhuo.gameassistant.homepage.b.c> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramList.size())
    {
      localStringBuilder.append(((com.chaozhuo.gameassistant.homepage.b.c)paramList.get(i)).a).append(",");
      i += 1;
    }
    paramList = XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_APP_MANAGER", 0).edit();
    paramList.putString("KEY_APP_ADD_LIST", localStringBuilder.toString());
    paramList.apply();
  }
  
  public boolean a(String paramString)
  {
    return TextUtils.equals("arm64", new File(paramString).getName());
  }
  
  public com.chaozhuo.gameassistant.homepage.b.c b(String paramString)
  {
    Object localObject = SupermeCore.a().g(paramString, 0);
    if (localObject == null) {
      return null;
    }
    localObject = ((PackageInfo)localObject).applicationInfo;
    InstalledAppInfo localInstalledAppInfo = SupermeCore.a().d(paramString, 0);
    PackageManager localPackageManager = XApp.a().getPackageManager();
    com.chaozhuo.gameassistant.homepage.b.c localC = new com.chaozhuo.gameassistant.homepage.b.c();
    localC.a = paramString;
    localC.d = true;
    if (((ApplicationInfo)localObject).publicSourceDir != null) {}
    for (paramString = ((ApplicationInfo)localObject).publicSourceDir;; paramString = ((ApplicationInfo)localObject).sourceDir)
    {
      localC.c = paramString;
      if (localInstalledAppInfo != null) {
        localC.b = localInstalledAppInfo.apkPath;
      }
      if (localC.b == null) {
        localC.b = localC.c;
      }
      localC.e = ((ApplicationInfo)localObject).loadIcon(localPackageManager);
      localC.f = ((ApplicationInfo)localObject).loadLabel(localPackageManager);
      if (localInstalledAppInfo != null) {
        localC.g = localInstalledAppInfo.getInstalledUsers().length;
      }
      return localC;
    }
  }
  
  public void b()
  {
    SharedPreferences localSharedPreferences = XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_APP_MANAGER", 0);
    if (localSharedPreferences.getBoolean("KEY_REMOVE_QQ_ONCE", false)) {
      return;
    }
    localSharedPreferences.edit().putBoolean("KEY_REMOVE_QQ_ONCE", true).commit();
    e("com.tencent.mobileqq");
  }
  
  public void c()
  {
    Object localObject = XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_APP_MANAGER", 0);
    if (((SharedPreferences)localObject).getBoolean("KEY_REMOVE_FB_ONCE2", false)) {}
    for (;;)
    {
      return;
      ((SharedPreferences)localObject).edit().putBoolean("KEY_REMOVE_FB_ONCE2", true).commit();
      localObject = com.chaozhuo.superme.client.b.c.d.iterator();
      while (((Iterator)localObject).hasNext()) {
        e((String)((Iterator)localObject).next());
      }
    }
  }
  
  public void c(String paramString)
  {
    Object localObject = XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_APP_MANAGER", 0);
    String str = ((SharedPreferences)localObject).getString("KEY_APP_ADD_LIST", "");
    if (Arrays.asList(str.split(",")).contains(paramString)) {
      return;
    }
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putString("KEY_APP_ADD_LIST", str + paramString + ",");
    ((SharedPreferences.Editor)localObject).apply();
  }
  
  public void d()
  {
    Object localObject = XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_APP_MANAGER", 0);
    if (((SharedPreferences)localObject).getBoolean("KEY_REMOVE_GAPPS_ONCE", false)) {}
    for (;;)
    {
      return;
      ((SharedPreferences)localObject).edit().putBoolean("KEY_REMOVE_GAPPS_ONCE", true).commit();
      localObject = b.a.iterator();
      while (((Iterator)localObject).hasNext()) {
        e((String)((Iterator)localObject).next());
      }
      localObject = b.b.iterator();
      while (((Iterator)localObject).hasNext()) {
        e((String)((Iterator)localObject).next());
      }
    }
  }
  
  public boolean d(String paramString)
  {
    boolean bool2 = false;
    List localList = Arrays.asList(XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_APP_MANAGER", 0).getString("KEY_APP_ADD_LIST", "").split(","));
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < localList.size())
      {
        if ((!TextUtils.isEmpty(paramString)) && (((String)localList.get(i)).equals(paramString))) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public void e()
  {
    SharedPreferences localSharedPreferences = XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_APP_MANAGER", 0);
    if (localSharedPreferences.getBoolean("KEY_ADD_APPS_ONCE", false)) {
      return;
    }
    localSharedPreferences.edit().putBoolean("KEY_ADD_APPS_ONCE", true).commit();
    w.a().when(e.a()).done(f.a());
  }
  
  public void e(String paramString)
  {
    Object localObject = XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_APP_MANAGER", 0);
    List localList = Arrays.asList(((SharedPreferences)localObject).getString("KEY_APP_ADD_LIST", "").split(","));
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < localList.size())
    {
      if (((String)localList.get(i)).equals(paramString)) {}
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append((String)localList.get(i));
        localStringBuilder.append(",");
      }
    }
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putString("KEY_APP_ADD_LIST", localStringBuilder.toString());
    ((SharedPreferences.Editor)localObject).apply();
    SupermeCore.a().g(paramString);
  }
  
  public List<com.chaozhuo.gameassistant.homepage.b.d> f()
  {
    Object localObject = a(true);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      com.chaozhuo.gameassistant.homepage.b.d localD = new com.chaozhuo.gameassistant.homepage.b.d();
      localD.b = b(str);
      localD.c = d(str);
      localArrayList.add(localD);
    }
    Collections.sort(localArrayList, g.a());
    return localArrayList;
  }
  
  public List<com.chaozhuo.gameassistant.homepage.b.c> g()
  {
    Object localObject1 = Arrays.asList(XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_APP_MANAGER", 0).getString("KEY_APP_ADD_LIST", "").split(","));
    ArrayList localArrayList = new ArrayList();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    PackageManager localPackageManager = XApp.a().getPackageManager();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (String)((Iterator)localObject1).next();
      if (!TextUtils.equals((CharSequence)localObject2, SupermeCore.a().m()))
      {
        com.chaozhuo.gameassistant.homepage.b.c localC = b((String)localObject2);
        if (localC == null)
        {
          e((String)localObject2);
        }
        else
        {
          localIntent.setPackage((String)localObject2);
          localObject2 = localPackageManager.queryIntentActivities(localIntent, 0);
          if ((localObject2 != null) && (!((List)localObject2).isEmpty())) {
            localArrayList.add(localC);
          }
        }
      }
    }
    return localArrayList;
  }
}
