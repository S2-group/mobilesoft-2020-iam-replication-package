package com.mobileiron.polaris.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import com.mobileiron.acom.core.android.AndroidRelease;
import com.mobileiron.acom.core.android.b;
import com.mobileiron.acom.core.android.c;
import com.mobileiron.acom.core.android.g;
import com.mobileiron.acom.core.android.h;
import com.mobileiron.acom.core.android.n;
import com.mobileiron.acom.core.utils.l;
import com.mobileiron.locksmith.f;
import com.mobileiron.polaris.model.properties.a;
import com.mobileiron.polaris.model.properties.aq;
import com.mobileiron.polaris.model.properties.o.a;
import com.mobileiron.protocol.v1.Apps.App.AppSourceType;
import com.mobileiron.protocol.v1.Apps.App.InstalledAppType;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class p
{
  private static final Logger a = LoggerFactory.getLogger("PackageManagerUtils");
  private static ResolveInfo b;
  private static Drawable c;
  private static ResolveInfo d;
  private static ResolveInfo e;
  private static Drawable f;
  
  public static aq a()
  {
    PackageInfo localPackageInfo = b.a(f.a());
    if (localPackageInfo == null) {
      return new aq();
    }
    return new aq(localPackageInfo.versionCode, localPackageInfo.versionName);
  }
  
  static o.a a(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    try
    {
      ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
      int i = paramPackageInfo.versionCode;
      Object localObject = paramPackageManager.getInstallerPackageName(paramPackageInfo.packageName);
      Apps.App.InstalledAppType localInstalledAppType;
      o.a localA;
      if ((localObject != null) && (("com.google.android.feedback".equals(localObject)) || ("com.android.vending".equals(localObject))))
      {
        localObject = Apps.App.AppSourceType.MARKET;
        localInstalledAppType = a(localApplicationInfo);
        localA = new o.a();
        if ((localApplicationInfo == null) || ("com.android.keyguard".equals(localApplicationInfo.packageName))) {
          break label155;
        }
        paramPackageManager = paramPackageManager.getApplicationLabel(localApplicationInfo);
        if (paramPackageManager == null) {
          break label155;
        }
      }
      label155:
      for (paramPackageManager = paramPackageManager.toString();; paramPackageManager = null)
      {
        return localA.a(com.mobileiron.acom.core.utils.o.c(paramPackageManager)).b(paramPackageInfo.packageName).a((Apps.App.AppSourceType)localObject).a(localInstalledAppType).a(i).c(com.mobileiron.acom.core.utils.o.c(paramPackageInfo.versionName)).a(h.c(paramPackageInfo.packageName));
        localObject = Apps.App.AppSourceType.NON_MARKET;
        break;
      }
      return null;
    }
    catch (Exception paramPackageManager)
    {
      a.error("Exception converting package to installed app: {}, {}", paramPackageInfo.packageName, paramPackageManager.toString());
    }
  }
  
  static Apps.App.InstalledAppType a(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {
      return Apps.App.InstalledAppType.UNKNOWN_INSTALL_TYPE;
    }
    if ((paramApplicationInfo.flags & 0x80) != 0) {
      return Apps.App.InstalledAppType.UPDATED_SYSTEM_APP;
    }
    if ((paramApplicationInfo.flags & 0x1) != 0) {
      return Apps.App.InstalledAppType.SYSTEM_APP;
    }
    return Apps.App.InstalledAppType.NON_SYSTEM_APP;
  }
  
  public static List<Intent> a(File paramFile, int paramInt)
  {
    ArrayList localArrayList = new ArrayList(2);
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(FileProvider.a(g.a(), GoFileUtils.a(), paramFile), "application/vnd.android.package-archive");
    localIntent.addFlags(paramInt | 0x1);
    if ((n.a()) && ((AndroidRelease.c()) || (AndroidRelease.d()) || (AndroidRelease.h()))) {}
    for (int i = 1;; i = 0)
    {
      if (i == 0) {
        localArrayList.add(localIntent);
      }
      localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      paramFile = Uri.fromFile(paramFile);
      localIntent.setDataAndType(Uri.parse("file://" + paramFile.toString()), "application/vnd.android.package-archive");
      localIntent.addFlags(paramInt);
      localArrayList.add(localIntent);
      return localArrayList;
    }
  }
  
  private static List<com.mobileiron.polaris.model.properties.o> a(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 8192;; i = 0)
    {
      localObject = b.b(i);
      if (!l.a((List)localObject)) {
        break;
      }
      return Collections.emptyList();
    }
    PackageManager localPackageManager = g.a().getPackageManager();
    ArrayList localArrayList = new ArrayList();
    Object localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((!paramBoolean) || ((localPackageInfo.applicationInfo.flags & 0x800000) != 0))
      {
        o.a localA = a(localPackageManager, localPackageInfo);
        if (localA == null) {
          a.error("convertToInstalledApp returned null: {}", b(localPackageManager, localPackageInfo));
        } else {
          localArrayList.add(localA.a());
        }
      }
    }
    return localArrayList;
  }
  
  private static void a(PackageManager paramPackageManager, String paramString, List<PackageInfo> paramList)
  {
    a.error("Package count (with flag == {}): {}", paramString, Integer.valueOf(paramList.size()));
    Collections.sort(paramList, new Comparator() {});
    paramString = paramList.iterator();
    while (paramString.hasNext())
    {
      paramList = (PackageInfo)paramString.next();
      a.error("   {}", b(paramPackageManager, paramList));
    }
  }
  
  public static boolean a(Intent paramIntent)
  {
    return l.a(g.a().getPackageManager().queryIntentActivities(paramIntent, 65536));
  }
  
  public static boolean a(a paramA)
  {
    Object localObject2 = null;
    String str = paramA.e();
    PackageInfo localPackageInfo = b.a(str);
    Object localObject1 = localObject2;
    if (localPackageInfo != null)
    {
      localObject1 = a(g.a().getPackageManager(), localPackageInfo);
      if (localObject1 != null) {
        break label88;
      }
      localObject1 = localObject2;
    }
    while (localObject1 != null) {
      if (paramA.c())
      {
        int i = ((com.mobileiron.polaris.model.properties.o)localObject1).f();
        int j = paramA.f();
        if (i == j)
        {
          a.info("InHouse app is installed: {}, {}", str, Integer.valueOf(j));
          return true;
          label88:
          localObject1 = ((o.a)localObject1).a();
        }
        else
        {
          a.info("InHouse app is installed but wrong version: {}, {}; requested {}", new Object[] { str, Integer.valueOf(i), Integer.valueOf(j) });
          return false;
        }
      }
      else
      {
        a.info("Public app is installed: {}", str);
        return true;
      }
    }
    a.info("App not installed: {}", str);
    return false;
  }
  
  public static boolean a(String paramString)
  {
    return b.a(paramString) != null;
  }
  
  public static boolean a(String paramString, int paramInt)
  {
    paramString = b.a(paramString);
    return (paramString != null) && (paramString.versionCode == paramInt);
  }
  
  private static String b(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
    Object localObject = a(localApplicationInfo);
    int i;
    if ((localObject == Apps.App.InstalledAppType.SYSTEM_APP) || (localObject == Apps.App.InstalledAppType.UPDATED_SYSTEM_APP))
    {
      i = 1;
      StringBuilder localStringBuilder = new StringBuilder(localApplicationInfo.packageName);
      if ((localApplicationInfo.flags & 0x800000) == 0) {
        break label188;
      }
      localObject = " - Installed";
      label58:
      localStringBuilder = localStringBuilder.append((String)localObject);
      if (i == 0) {
        break label195;
      }
      localObject = " Sys";
      label74:
      localObject = localStringBuilder.append((String)localObject);
      if (paramPackageManager.getLaunchIntentForPackage(paramPackageInfo.packageName) != null) {
        break label202;
      }
      paramPackageManager = " NoLauncher";
      label96:
      paramPackageInfo = ((StringBuilder)localObject).append(paramPackageManager);
      if (!h.c(localApplicationInfo.packageName)) {
        break label209;
      }
      paramPackageManager = " Hidden";
      label117:
      paramPackageInfo = paramPackageInfo.append(paramPackageManager);
      if (!localApplicationInfo.enabled) {
        break label216;
      }
      paramPackageManager = " Enabled";
      label135:
      paramPackageInfo = paramPackageInfo.append(paramPackageManager);
      if ((localApplicationInfo.flags & 0x1000000) == 0) {
        break label223;
      }
    }
    label188:
    label195:
    label202:
    label209:
    label216:
    label223:
    for (paramPackageManager = " DataOnly";; paramPackageManager = " NotDataOnly")
    {
      return paramPackageManager + " 0x" + Integer.toHexString(localApplicationInfo.flags);
      i = 0;
      break;
      localObject = " - NotInstalled";
      break label58;
      localObject = " NotSys";
      break label74;
      paramPackageManager = " Launcher";
      break label96;
      paramPackageManager = " NotHidden";
      break label117;
      paramPackageManager = " NotEnabled";
      break label135;
    }
  }
  
  public static List<PackageInfo> b(Intent paramIntent)
  {
    ArrayList localArrayList = new ArrayList();
    paramIntent = g.a().getPackageManager().queryIntentActivities(paramIntent, 65536);
    if (!l.a(paramIntent))
    {
      paramIntent = paramIntent.iterator();
      while (paramIntent.hasNext())
      {
        Object localObject = ((ResolveInfo)paramIntent.next()).activityInfo;
        if (localObject != null)
        {
          localObject = b.a(((ActivityInfo)localObject).packageName);
          if (localObject != null) {
            localArrayList.add(localObject);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static List<ResolveInfo> b(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setPackage(paramString);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    return g.a().getPackageManager().queryIntentActivities(localIntent, 0);
  }
  
  public static Set<String> b()
  {
    HashSet localHashSet = new HashSet();
    Object localObject = b(new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com")));
    if (!l.a((List)localObject))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localHashSet.add(((PackageInfo)((Iterator)localObject).next()).packageName);
      }
    }
    localObject = b(new Intent("android.intent.action.VIEW", Uri.parse("https://www.example.com")));
    if (!l.a((List)localObject))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localHashSet.add(((PackageInfo)((Iterator)localObject).next()).packageName);
      }
    }
    localObject = b(Intent.makeMainSelectorActivity("android.intent.action.MAIN", "android.intent.category.APP_BROWSER"));
    if (!l.a((List)localObject))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localHashSet.add(((PackageInfo)((Iterator)localObject).next()).packageName);
      }
    }
    a.debug("Browser set size: {}", Integer.valueOf(localHashSet.size()));
    return localHashSet;
  }
  
  public static boolean b(String paramString, int paramInt)
  {
    paramString = b.a(paramString);
    return (paramString != null) && (paramString.versionCode > paramInt);
  }
  
  public static ResolveInfo c()
  {
    ResolveInfo localResolveInfo = null;
    if (b != null)
    {
      a.debug("System camera already selected: {}", b.activityInfo.packageName);
      localResolveInfo = b;
    }
    while (!c.a()) {
      return localResolveInfo;
    }
    localResolveInfo = b.d(0);
    if (localResolveInfo == null)
    {
      a.debug("No system camera activity found");
      return null;
    }
    a.info("System camera package selected: {}", localResolveInfo.activityInfo.packageName);
    b = localResolveInfo;
    PackageManager localPackageManager = g.a().getPackageManager();
    Drawable localDrawable = localResolveInfo.loadIcon(localPackageManager);
    c = localDrawable;
    if (localDrawable == null)
    {
      a.warn("resolveInfo.loadIcon was null, using activityInfo.loadIcon()");
      c = localResolveInfo.activityInfo.loadIcon(localPackageManager);
    }
    return b;
  }
  
  public static Drawable d()
  {
    if (c == null) {
      c();
    }
    return c;
  }
  
  public static ResolveInfo e()
  {
    ResolveInfo localResolveInfo = null;
    if (d != null)
    {
      a.debug("System dialer already selected: {}", d.activityInfo.packageName);
      localResolveInfo = d;
    }
    while (!g()) {
      return localResolveInfo;
    }
    localResolveInfo = b.e(0);
    if (localResolveInfo == null)
    {
      a.debug("No system dialer activity found");
      return null;
    }
    a.info("System dialer package selected: {}", localResolveInfo.activityInfo.packageName);
    d = localResolveInfo;
    return localResolveInfo;
  }
  
  public static Drawable f()
  {
    if (f == null)
    {
      if (e == null) {
        break label40;
      }
      a.debug("System caller already selected: {}", e.activityInfo.packageName);
    }
    for (;;)
    {
      ResolveInfo localResolveInfo = e;
      for (;;)
      {
        return f;
        label40:
        if (g())
        {
          localResolveInfo = b.f(0);
          if (localResolveInfo != null) {
            break;
          }
          a.debug("No system caller activity found");
        }
      }
      a.info("System caller system package selected: {}", localResolveInfo.activityInfo.packageName);
      e = localResolveInfo;
      PackageManager localPackageManager = g.a().getPackageManager();
      Drawable localDrawable = localResolveInfo.loadIcon(localPackageManager);
      f = localDrawable;
      if (localDrawable == null)
      {
        a.warn("resolveInfo.loadIcon was null, using activityInfo.loadIcon()");
        f = localResolveInfo.activityInfo.loadIcon(localPackageManager);
      }
    }
  }
  
  public static boolean g()
  {
    return g.a().getPackageManager().hasSystemFeature("android.hardware.telephony");
  }
  
  public static List<com.mobileiron.polaris.model.properties.o> h()
  {
    return a(false);
  }
  
  public static List<com.mobileiron.polaris.model.properties.o> i()
  {
    return a(true);
  }
  
  public static List<String> j()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = g.a().getPackageManager();
    Object localObject = localPackageManager.getInstalledPackages(0);
    if (l.a((List)localObject)) {
      return localArrayList;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (localPackageManager.getLaunchIntentForPackage(localPackageInfo.packageName) != null) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public static List<String> k()
  {
    Object localObject = g.a().getPackageManager().getInstalledPackages(0);
    if (l.a((List)localObject)) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (b.e(localPackageInfo.packageName)) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public static List<String> l()
  {
    Object localObject = g.a().getPackageManager().getInstalledPackages(8192);
    if (l.a((List)localObject)) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((!b.a(localPackageInfo.applicationInfo)) && (h.c(localPackageInfo.packageName))) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public static void m()
  {
    PackageManager localPackageManager = g.a().getPackageManager();
    a.error("#######################################################################");
    Object localObject = b.d(8192);
    Logger localLogger = a;
    if ((localObject != null) && (((ResolveInfo)localObject).activityInfo != null))
    {
      localObject = ((ResolveInfo)localObject).activityInfo.packageName;
      localLogger.error("System camera: {}", localObject);
      localObject = b.e(8192);
      localLogger = a;
      if ((localObject == null) || (((ResolveInfo)localObject).activityInfo == null)) {
        break label205;
      }
      localObject = ((ResolveInfo)localObject).activityInfo.packageName;
      label88:
      localLogger.error("System dialer: {}", localObject);
      localObject = b.f(8192);
      localLogger = a;
      if ((localObject == null) || (((ResolveInfo)localObject).activityInfo == null)) {
        break label212;
      }
    }
    label205:
    label212:
    for (localObject = ((ResolveInfo)localObject).activityInfo.packageName;; localObject = "not found")
    {
      localLogger.error("System caller: {}", localObject);
      a.error("-----------------------------------------------------------------------");
      a(localPackageManager, "0", localPackageManager.getInstalledPackages(0));
      a.error("-----------------------------------------------------------------------");
      a(localPackageManager, "GET_UNINSTALLED_PACKAGES", localPackageManager.getInstalledPackages(8192));
      a.error("#######################################################################");
      return;
      localObject = "not found";
      break;
      localObject = "not found";
      break label88;
    }
  }
}
