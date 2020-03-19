package com.hld.anzenbokusu.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.hld.anzenbokusu.App;
import com.hld.anzenbokusu.db.entity.HideApp;
import com.hld.anzenbokusu.db.entity.LockApp;
import com.hld.anzenbokusu.mvp.entity.AppInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import mv;
import xa;

public class O0000Oo
{
  private static Boolean O000000o(PackageManager paramPackageManager, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(paramString);
    boolean bool2 = false;
    paramPackageManager = paramPackageManager.queryIntentActivities(localIntent, 0);
    boolean bool1 = bool2;
    if (paramPackageManager != null)
    {
      bool1 = bool2;
      if (paramPackageManager.size() > 0) {
        bool1 = true;
      }
    }
    return Boolean.valueOf(bool1);
  }
  
  public static String O000000o(String paramString)
  {
    try
    {
      paramString = O0000o.O00000Oo(paramString).substring(0, 1).toUpperCase(Locale.CHINESE);
      if (paramString.matches("[A-Z]")) {
        return paramString;
      }
      return "#";
    }
    catch (Exception paramString)
    {
      xa.O00000o(paramString.toString());
    }
    return "#";
  }
  
  public static List<AppInfo> O000000o()
  {
    ArrayList localArrayList1 = new ArrayList();
    PackageManager localPackageManager = App.O000000o().getPackageManager();
    Object localObject1 = localPackageManager.getInstalledPackages(8192);
    ArrayList localArrayList2 = new ArrayList();
    boolean bool = O00000o.O000000o(App.O000000o());
    Object localObject2 = mv.O00000o().O0000ooO().iterator();
    while (((Iterator)localObject2).hasNext()) {
      localArrayList2.add(((HideApp)((Iterator)localObject2).next()).getPackageName());
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if ((!App.O000000o().getPackageName().equals(((PackageInfo)localObject2).applicationInfo.packageName)) && (bool ? (O00000o.O000000o(((PackageInfo)localObject2).applicationInfo.packageName)) || (!localArrayList2.contains(((PackageInfo)localObject2).applicationInfo.packageName)) : !localArrayList2.contains(((PackageInfo)localObject2).applicationInfo.packageName))) {
        if (!O000000o(localPackageManager, ((PackageInfo)localObject2).applicationInfo.packageName).booleanValue())
        {
          AppInfo localAppInfo = new AppInfo();
          localAppInfo.setAppName(((PackageInfo)localObject2).applicationInfo.loadLabel(localPackageManager).toString());
          localAppInfo.setPackageName(((PackageInfo)localObject2).applicationInfo.packageName);
          localAppInfo.setApplicationInfo(((PackageInfo)localObject2).applicationInfo);
          O000000o(localAppInfo);
          localArrayList1.add(localAppInfo);
        }
      }
    }
    Collections.sort(localArrayList1, new O0000Oo0());
    return localArrayList1;
  }
  
  public static List<AppInfo> O000000o(boolean paramBoolean1, boolean paramBoolean2)
  {
    ArrayList localArrayList1 = new ArrayList();
    PackageManager localPackageManager = App.O000000o().getPackageManager();
    Object localObject1 = localPackageManager.getInstalledPackages(8192);
    List localList = O00000o();
    ArrayList localArrayList2 = new ArrayList();
    Object localObject2;
    if (paramBoolean2)
    {
      boolean bool2 = O00000o.O000000o(App.O000000o());
      localObject2 = mv.O00000o().O0000ooO().iterator();
      for (;;)
      {
        bool1 = bool2;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localArrayList2.add(((HideApp)((Iterator)localObject2).next()).getPackageName());
      }
    }
    boolean bool1 = false;
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if ((!App.O000000o().getPackageName().equals(((PackageInfo)localObject2).applicationInfo.packageName)) && (!"com.hld.azbk".equals(((PackageInfo)localObject2).applicationInfo.packageName)) && ((!paramBoolean2) || (bool1 ? !O00000o.O000000o(((PackageInfo)localObject2).applicationInfo.packageName) : !localArrayList2.contains(((PackageInfo)localObject2).applicationInfo.packageName)))) {
        if (((paramBoolean1) && ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) != 0)) || ((!paramBoolean1) && ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) == 0)))
        {
          AppInfo localAppInfo = new AppInfo();
          localAppInfo.setAppName(((PackageInfo)localObject2).applicationInfo.loadLabel(localPackageManager).toString());
          localAppInfo.setPackageName(((PackageInfo)localObject2).applicationInfo.packageName);
          localAppInfo.setApplicationInfo(((PackageInfo)localObject2).applicationInfo);
          if (!paramBoolean2) {
            localAppInfo.setChecked(localList.contains(((PackageInfo)localObject2).applicationInfo.packageName));
          }
          O000000o(localAppInfo);
          localArrayList1.add(localAppInfo);
        }
      }
    }
    Collections.sort(localArrayList1, new O0000Oo0());
    if (!paramBoolean2) {
      Collections.sort(localArrayList1, new O0000OOo());
    }
    return localArrayList1;
  }
  
  private static void O000000o(AppInfo paramAppInfo)
  {
    try
    {
      String str2 = paramAppInfo.getAppName().trim();
      String str1 = str2;
      if (str2.startsWith(" ")) {
        str1 = str2.replace(" ", "");
      }
      str1 = O0000o.O00000Oo(str1).substring(0, 1).toUpperCase(Locale.CHINESE);
      if (str1.matches("[A-Z]"))
      {
        paramAppInfo.setRankLetter(str1);
        return;
      }
      paramAppInfo.setRankLetter("#");
      return;
    }
    catch (Exception localException)
    {
      xa.O00000o(localException.toString());
      paramAppInfo.setRankLetter("#");
    }
  }
  
  public static boolean O000000o(Intent paramIntent)
  {
    return (paramIntent != null) && (paramIntent.hasCategory("android.intent.category.HOME"));
  }
  
  public static String O00000Oo()
  {
    Object localObject = App.O000000o().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(App.O000000o().getPackageName(), 0).versionName;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      xa.O00000o(localNameNotFoundException.toString());
    }
    return "";
  }
  
  public static boolean O00000Oo(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    try
    {
      App.O000000o().getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  @NonNull
  private static List<String> O00000o()
  {
    ArrayList localArrayList = new ArrayList();
    List localList = mv.O00000o().O0000o();
    int j = localList.size();
    int i = 0;
    while (i < j)
    {
      String str = ((LockApp)localList.get(i)).getPackageName();
      if (O00000Oo(str)) {
        localArrayList.add(i, str);
      } else {
        mv.O00000o().O0000O0o(str);
      }
      i += 1;
    }
    if ((localArrayList.size() <= 0) && (O00Oo0OO.O00000o0("app_lock_using", false))) {
      O00Oo0OO.O000000o("app_lock_using", false);
    }
    return localArrayList;
  }
  
  public static int O00000o0()
  {
    PackageManager localPackageManager = App.O000000o().getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(App.O000000o().getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      xa.O00000o(localNameNotFoundException.toString());
    }
    return 0;
  }
}
