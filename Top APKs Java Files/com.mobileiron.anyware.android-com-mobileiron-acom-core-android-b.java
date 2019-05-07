package com.mobileiron.acom.core.android;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.mobileiron.acom.core.utils.l;
import com.mobileiron.acom.core.utils.n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;

public final class b
{
  private static final Logger a = n.a("AppsUtils");
  
  public static int a()
  {
    return a(0).size();
  }
  
  public static PackageInfo a(String paramString)
  {
    return a(paramString, 0);
  }
  
  public static PackageInfo a(String paramString, int paramInt)
  {
    try
    {
      PackageInfo localPackageInfo = g.a().getPackageManager().getPackageInfo(paramString, paramInt);
      return localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      a.debug("Application not found: {}", paramString);
      return null;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        a.error("getPackageInfo: ", paramString);
      }
    }
  }
  
  private static ResolveInfo a(Intent paramIntent, int paramInt)
  {
    paramIntent = g.a().getPackageManager().queryIntentActivities(paramIntent, paramInt);
    if (l.a(paramIntent)) {
      return null;
    }
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramIntent.next();
      Object localObject = localResolveInfo.activityInfo;
      if (localObject != null)
      {
        localObject = a(((ActivityInfo)localObject).packageName, paramInt);
        if ((localObject != null) && (a(((PackageInfo)localObject).applicationInfo))) {
          return localResolveInfo;
        }
      }
    }
    return null;
  }
  
  public static List<PackageInfo> a(int paramInt)
  {
    return g.a().getPackageManager().getInstalledPackages(paramInt);
  }
  
  public static List<PackageInfo> a(boolean paramBoolean)
  {
    String str1 = g.a().getPackageName();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = b(0).iterator();
    label135:
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((!localPackageInfo.packageName.equals(str1)) && ((!paramBoolean) || (!localPackageInfo.packageName.equals("com.forgepond.locksmith"))))
      {
        String str2 = localPackageInfo.packageName;
        if ((!str2.equals(g.a().getPackageName())) && (g.a().getPackageManager().checkPermission("com.forgepond.locksmith.permission.ACCESS_KEY", str2) == 0)) {}
        for (int i = 1;; i = 0)
        {
          if (i == 0) {
            break label135;
          }
          localArrayList.add(localPackageInfo);
          break;
        }
      }
    }
    return localArrayList;
  }
  
  public static void a(ComponentName paramComponentName, boolean paramBoolean)
  {
    a.info("enableComponent: {}, enable? {}", paramComponentName.getClassName(), Boolean.valueOf(paramBoolean));
    PackageManager localPackageManager = g.a().getPackageManager();
    if (paramBoolean) {}
    for (int i = 1;; i = 2)
    {
      localPackageManager.setComponentEnabledSetting(paramComponentName, i, 1);
      return;
    }
  }
  
  public static void a(String paramString, boolean paramBoolean)
  {
    a.info("enableApplication: {}, enable? {}", paramString, Boolean.valueOf(false));
    g.a().getPackageManager().setApplicationEnabledSetting(paramString, 2, 0);
  }
  
  private static void a(List<String> paramList1, List<String> paramList2)
  {
    paramList2 = a(8192);
    if (l.a(paramList2)) {
      return;
    }
    paramList2 = paramList2.iterator();
    label22:
    ApplicationInfo localApplicationInfo;
    while (paramList2.hasNext())
    {
      localApplicationInfo = ((PackageInfo)paramList2.next()).applicationInfo;
      if ((localApplicationInfo != null) && (a(localApplicationInfo))) {
        if ((localApplicationInfo.flags & 0x800000) != 0) {
          break label103;
        }
      }
    }
    label103:
    for (int i = 1;; i = 0)
    {
      boolean bool = h.c(localApplicationInfo.packageName);
      if ((i != 0) || (bool)) {
        break label22;
      }
      paramList1.add(localApplicationInfo.packageName);
      break label22;
      break;
    }
  }
  
  public static boolean a(ApplicationInfo paramApplicationInfo)
  {
    if ((paramApplicationInfo.flags & 0x80) != 0) {}
    while ((paramApplicationInfo.flags & 0x1) != 0) {
      return true;
    }
    return false;
  }
  
  public static int b()
  {
    return a(g.a().getPackageName(), 0).versionCode;
  }
  
  public static String b(String paramString)
  {
    Object localObject = g.a().getPackageManager();
    try
    {
      ApplicationInfo localApplicationInfo = ((PackageManager)localObject).getApplicationInfo(paramString, 128);
      if (localApplicationInfo != null)
      {
        localObject = (String)((PackageManager)localObject).getApplicationLabel(localApplicationInfo);
        return localObject;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      a.debug("Application not found: {}", paramString);
    }
    return null;
  }
  
  public static List<PackageInfo> b(int paramInt)
  {
    int i = 0;
    while (i < 10) {
      try
      {
        List localList = a(paramInt);
        return localList;
      }
      catch (Exception localException)
      {
        a.error("Exception on calling getPackages() - retrying...  ", localException);
        i += 1;
      }
    }
    return Collections.emptyList();
  }
  
  public static boolean b(String paramString, int paramInt)
  {
    boolean bool2 = false;
    paramString = a(paramString, 0);
    boolean bool1 = bool2;
    if (paramString != null)
    {
      bool1 = bool2;
      if (paramString.versionCode > 2080)
      {
        bool1 = bool2;
        if (paramString.applicationInfo != null) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static String c()
  {
    return a(g.a().getPackageName(), 0).versionName;
  }
  
  public static List<ResolveInfo> c(int paramInt)
  {
    return g.a().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("content://contacts/people/")), 8192);
  }
  
  public static boolean c(String paramString)
  {
    boolean bool = false;
    if (a(paramString, 0) != null) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean c(String paramString, int paramInt)
  {
    boolean bool2 = false;
    paramString = a(paramString, 0);
    boolean bool1 = bool2;
    if (paramString != null)
    {
      bool1 = bool2;
      if (paramString.versionCode < paramInt)
      {
        bool1 = bool2;
        if (paramString.applicationInfo != null) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static Intent d(String paramString)
  {
    if (paramString != null) {
      return g.a().getPackageManager().getLaunchIntentForPackage(paramString);
    }
    return null;
  }
  
  public static ResolveInfo d(int paramInt)
  {
    return a(new Intent("android.media.action.IMAGE_CAPTURE"), paramInt);
  }
  
  private static ResolveInfo d(String paramString, int paramInt)
  {
    paramString = new Intent(paramString);
    paramString.setData(Uri.parse("tel:0123456789"));
    return a(paramString, paramInt);
  }
  
  public static boolean d()
  {
    PackageInfo localPackageInfo = a(g.a().getPackageName(), 0);
    if (localPackageInfo == null) {
      a.error("isClientDebuggable(): no package info, assuming false");
    }
    do
    {
      return false;
      if (localPackageInfo.applicationInfo == null)
      {
        a.error("isClientDebuggable(): no application info, assuming false");
        return false;
      }
    } while ((localPackageInfo.applicationInfo.flags & 0x2) == 0);
    return true;
  }
  
  public static ResolveInfo e(int paramInt)
  {
    return d("android.intent.action.DIAL", paramInt);
  }
  
  public static void e()
  {
    a.info("Launching client UI");
    Context localContext = g.a();
    localContext.startActivity(d(localContext.getPackageName()));
  }
  
  public static boolean e(String paramString)
  {
    if (g.a().getPackageName().equals(paramString)) {}
    while ((i(paramString)) || (g.a().getPackageManager().getLaunchIntentForPackage(paramString) == null)) {
      return false;
    }
    return true;
  }
  
  public static ResolveInfo f(int paramInt)
  {
    return d("android.intent.action.CALL", paramInt);
  }
  
  public static String f()
  {
    return "com.mobileiron.samsungproxy";
  }
  
  public static boolean f(String paramString)
  {
    return ("com.mobileiron.anyware.android".equals(paramString)) || ("com.mobileiron.workplace.android".equals(paramString)) || ("com.mobileiron.launchpad.android".equals(paramString)) || ("com.mobileiron.anyware.android.qa".equals(paramString)) || ("com.mobileiron.anyware.android.odin".equals(paramString));
  }
  
  public static boolean g()
  {
    boolean bool = false;
    PackageInfo localPackageInfo = a("com.mobileiron.samsungproxy", 0);
    if (localPackageInfo != null) {
      bool = "2.7".equals(localPackageInfo.versionName);
    }
    return bool;
  }
  
  public static boolean g(String paramString)
  {
    return ("com.mobileiron".equals(paramString)) || ("com.mobileiron.qa".equals(paramString)) || ("com.mobileiron.griffin".equals(paramString)) || ("com.mobileiron.mdmpp".equals(paramString)) || ("de.telekom.mdm".equals(paramString)) || ("com.mobileiron.vodafone.MIClient".equals(paramString));
  }
  
  public static ProviderInfo h(String paramString)
  {
    return g.a().getPackageManager().resolveContentProvider(paramString, 0);
  }
  
  @TargetApi(21)
  public static boolean h()
  {
    if (AndroidRelease.e()) {
      return g.a().getPackageManager().hasSystemFeature("android.software.managed_users");
    }
    a.warn("Device does not support managed users: SDK {}", Integer.valueOf(AndroidRelease.a()));
    return false;
  }
  
  public static boolean i()
  {
    return c("com.forgepond.locksmith");
  }
  
  public static boolean i(String paramString)
  {
    paramString = a(paramString, 8192);
    return (paramString != null) && (a(paramString.applicationInfo));
  }
  
  public static int j()
  {
    try
    {
      int i = g.a().getPackageManager().getApplicationInfo("com.forgepond.locksmith", 128).uid;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      a.error("getSecureAppsManagerUserId: {}", localNameNotFoundException.getMessage());
    }
    return -1;
  }
  
  public static int k()
  {
    ArrayList localArrayList = new ArrayList();
    a(localArrayList, null);
    return localArrayList.size();
  }
}
