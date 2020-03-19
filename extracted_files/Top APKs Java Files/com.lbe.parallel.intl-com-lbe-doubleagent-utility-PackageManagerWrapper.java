package com.lbe.doubleagent.utility;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.text.TextUtils;
import com.lbe.doubleagent.client.b;
import com.lbe.doubleagent.service.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PackageManagerWrapper
  extends PackageManager
{
  private static final Object b = new Object();
  private static final List<PackageInfo> f = new ArrayList();
  private static final List<PackageInfo> g = new ArrayList();
  private static m h;
  int[] a = { 100, 500, 1000 };
  private PackageManager c;
  private Context d;
  private int e;
  
  public PackageManagerWrapper(Context paramContext)
  {
    this.c = paramContext.getPackageManager();
    this.d = paramContext;
    this.e = b.c();
  }
  
  public PackageManagerWrapper(Context paramContext, int paramInt)
  {
    this.c = paramContext.getPackageManager();
    this.d = paramContext;
    this.e = paramInt;
  }
  
  private PackageInfo a(Iterator<PackageInfo> paramIterator, String paramString)
  {
    while (paramIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramIterator.next();
      if (TextUtils.equals(localPackageInfo.packageName, paramString))
      {
        paramIterator.remove();
        return localPackageInfo;
      }
    }
    return null;
  }
  
  private void a(int paramInt1, List<ResolveInfo> paramList, Intent paramIntent, int paramInt2)
  {
    if (h == null) {}
    do
    {
      return;
      paramIntent = h.c(paramInt1, null, paramIntent, "", paramInt2);
    } while ((paramIntent == null) || (paramIntent.size() <= 0));
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramIntent.iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ResolveInfo)localIterator.next()).serviceInfo.packageName);
    }
    localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      if (localHashSet.contains(((ResolveInfo)localIterator.next()).serviceInfo.packageName)) {
        localIterator.remove();
      }
    }
    paramList.addAll(paramIntent);
  }
  
  private void a(List<ResolveInfo> paramList, Intent paramIntent, int paramInt)
  {
    if (h == null) {}
    do
    {
      return;
      paramIntent = h.a(this.e, null, paramIntent, "", paramInt);
    } while ((paramIntent == null) || (paramIntent.size() <= 0));
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramIntent.iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ResolveInfo)localIterator.next()).activityInfo.packageName);
    }
    localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      if (localHashSet.contains(((ResolveInfo)localIterator.next()).activityInfo.packageName)) {
        localIterator.remove();
      }
    }
    paramList.addAll(paramIntent);
  }
  
  private void a(List<ProviderInfo> paramList, String paramString, int paramInt1, int paramInt2)
  {
    if (h == null) {}
    do
    {
      return;
      paramString = h.e(this.e, null, paramString, paramInt2);
    } while ((paramString == null) || (paramString.size() <= 0));
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramString.iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ProviderInfo)localIterator.next()).packageName);
    }
    localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      if (localHashSet.contains(((ProviderInfo)localIterator.next()).packageName)) {
        localIterator.remove();
      }
    }
    paramList.addAll(paramString);
  }
  
  private void a(List<PackageInfo> paramList1, List<PackageInfo> paramList2)
  {
    if ((paramList2 == null) || (paramList1 == null) || (paramList2.size() == 0) || (paramList1.size() == 0)) {
      return;
    }
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramList2.iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((PackageInfo)localIterator.next()).packageName);
    }
    localIterator = paramList1.iterator();
    while (localIterator.hasNext()) {
      if (localHashSet.contains(((PackageInfo)localIterator.next()).packageName)) {
        localIterator.remove();
      }
    }
    paramList1.addAll(paramList2);
  }
  
  private boolean a(int paramInt)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramInt != 0)
    {
      bool1 = bool2;
      if (paramInt != 8192) {}
    }
    else
    {
      if (f.size() == 0) {
        f.addAll(getSystemInstalledPackageInfos(8192));
      }
      bool1 = bool2;
      if (f.size() > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void b(List<ResolveInfo> paramList, Intent paramIntent, int paramInt)
  {
    if (h == null) {}
    do
    {
      return;
      paramIntent = h.b(this.e, null, paramIntent, "", paramInt);
    } while ((paramIntent == null) || (paramIntent.size() <= 0));
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramIntent.iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ResolveInfo)localIterator.next()).activityInfo.packageName);
    }
    localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      if (localHashSet.contains(((ResolveInfo)localIterator.next()).activityInfo.packageName)) {
        localIterator.remove();
      }
    }
    paramList.addAll(paramIntent);
  }
  
  private void b(List<ApplicationInfo> paramList1, List<ApplicationInfo> paramList2)
  {
    if ((paramList2 == null) || (paramList1 == null) || (paramList2.size() == 0) || (paramList1.size() == 0)) {
      return;
    }
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramList2.iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ApplicationInfo)localIterator.next()).packageName);
    }
    localIterator = paramList1.iterator();
    while (localIterator.hasNext()) {
      if (localHashSet.contains(((ApplicationInfo)localIterator.next()).packageName)) {
        localIterator.remove();
      }
    }
    paramList1.addAll(paramList2);
  }
  
  @TargetApi(19)
  private void c(List<ResolveInfo> paramList, Intent paramIntent, int paramInt)
  {
    if (h == null) {}
    do
    {
      return;
      paramIntent = h.d(this.e, null, paramIntent, "", paramInt);
    } while ((paramIntent == null) || (paramIntent.size() <= 0));
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramIntent.iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ResolveInfo)localIterator.next()).providerInfo.packageName);
    }
    localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      if (localHashSet.contains(((ResolveInfo)localIterator.next()).providerInfo.packageName)) {
        localIterator.remove();
      }
    }
    paramList.addAll(paramIntent);
  }
  
  public static void notifyPhoneCacheInvalide()
  {
    synchronized (b)
    {
      f.clear();
      return;
    }
  }
  
  public static void setDAPackageManager(m paramM)
  {
    synchronized (b)
    {
      h = paramM;
      updatePluginPackageInfo();
      return;
    }
  }
  
  protected static void updatePluginPackageInfo()
  {
    List localList = null;
    for (;;)
    {
      synchronized (b)
      {
        if (g.size() == 0)
        {
          i = 1;
          if (i != 0)
          {
            if (h != null) {
              localList = h.a(b.c(), null, 0);
            }
            ??? = b;
            if (localList == null) {}
          }
        }
      }
      int i = 0;
    }
  }
  
  public void addPackageToPreferred(String paramString)
  {
    this.c.addPackageToPreferred(paramString);
  }
  
  public boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return this.c.addPermission(paramPermissionInfo);
  }
  
  @TargetApi(8)
  public boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return this.c.addPermissionAsync(paramPermissionInfo);
  }
  
  public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.c.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  @TargetApi(8)
  public String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return this.c.canonicalToCurrentPackageNames(paramArrayOfString);
  }
  
  public int checkPermission(String paramString1, String paramString2)
  {
    return this.c.checkPermission(paramString1, paramString2);
  }
  
  public int checkSignatures(int paramInt1, int paramInt2)
  {
    return this.c.checkSignatures(paramInt1, paramInt2);
  }
  
  public int checkSignatures(String paramString1, String paramString2)
  {
    return this.c.checkSignatures(paramString1, paramString2);
  }
  
  public void clearPackagePreferredActivities(String paramString)
  {
    this.c.clearPackagePreferredActivities(paramString);
  }
  
  @TargetApi(8)
  public String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    return this.c.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  @TargetApi(17)
  public void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
  {
    this.c.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
  }
  
  @TargetApi(20)
  public Drawable getActivityBanner(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getActivityBanner(paramComponentName);
  }
  
  @TargetApi(20)
  public Drawable getActivityBanner(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getActivityBanner(paramIntent);
  }
  
  public Drawable getActivityIcon(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getActivityIcon(paramComponentName);
  }
  
  public Drawable getActivityIcon(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getActivityIcon(paramIntent);
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (h != null)
    {
      ActivityInfo localActivityInfo = h.a(this.e, null, paramComponentName, paramInt);
      if (localActivityInfo != null) {
        return localActivityInfo;
      }
    }
    return this.c.getActivityInfo(paramComponentName, paramInt);
  }
  
  @TargetApi(9)
  public Drawable getActivityLogo(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getActivityLogo(paramComponentName);
  }
  
  @TargetApi(9)
  public Drawable getActivityLogo(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getActivityLogo(paramIntent);
  }
  
  public List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    return this.c.getAllPermissionGroups(paramInt);
  }
  
  @TargetApi(20)
  public Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
  {
    return this.c.getApplicationBanner(paramApplicationInfo);
  }
  
  @TargetApi(20)
  public Drawable getApplicationBanner(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getApplicationBanner(paramString);
  }
  
  public int getApplicationEnabledSetting(String paramString)
  {
    return this.c.getApplicationEnabledSetting(paramString);
  }
  
  public Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.c.getApplicationIcon(paramApplicationInfo);
  }
  
  public Drawable getApplicationIcon(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return getApplicationIcon(getApplicationInfo(paramString, 0));
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (h != null)
    {
      localObject = h.c(this.e, null, paramString, paramInt);
      if (localObject != null) {
        return localObject;
      }
    }
    Object localObject = b;
    if (paramInt == 0) {
      try
      {
        if (f.size() > 0)
        {
          Iterator localIterator = f.iterator();
          while (localIterator.hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
            if (TextUtils.equals(localPackageInfo.applicationInfo.packageName, paramString))
            {
              paramString = localPackageInfo.applicationInfo;
              return paramString;
            }
          }
        }
      }
      finally {}
    }
    return this.c.getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    return this.c.getApplicationLabel(paramApplicationInfo);
  }
  
  @TargetApi(9)
  public Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
  {
    return this.c.getApplicationLogo(paramApplicationInfo);
  }
  
  @TargetApi(9)
  public Drawable getApplicationLogo(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getApplicationLogo(paramString);
  }
  
  public int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return this.c.getComponentEnabledSetting(paramComponentName);
  }
  
  public Drawable getDefaultActivityIcon()
  {
    return this.c.getDefaultActivityIcon();
  }
  
  public Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.c.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public HashMap<String, Integer> getHomePackages()
  {
    HashMap localHashMap = new HashMap();
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    localObject = queryIntentActivities((Intent)localObject, 65536);
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
        localHashMap.put(localResolveInfo.activityInfo.packageName, Integer.valueOf(localResolveInfo.activityInfo.applicationInfo.uid));
      }
    }
    return localHashMap;
  }
  
  public List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    List localList = null;
    ArrayList localArrayList = new ArrayList();
    Object localObject;
    if ((paramInt == 0) || (paramInt == 8192))
    {
      localObject = getPhoneAppApplications();
      localList = getPluginApplicationInfoListByUid(this.e, paramInt);
    }
    for (;;)
    {
      b((List)localObject, localList);
      return localObject;
      localArrayList.addAll(getSystemInstalledApplications(paramInt));
      localObject = localArrayList;
      if (h != null)
      {
        localList = h.b(this.e, null, paramInt);
        localObject = localArrayList;
      }
    }
  }
  
  public List<PackageInfo> getInstalledPackages(int paramInt)
  {
    ??? = null;
    ArrayList localArrayList = new ArrayList();
    if ((paramInt == 0) || (paramInt == 8192)) {}
    for (;;)
    {
      synchronized (b)
      {
        a(8192);
        localArrayList.addAll(f);
        ??? = getPluginPackageInfoListByUid(this.e, paramInt);
        a(localArrayList, (List)???);
        return localArrayList;
      }
      localObject2.addAll(getSystemInstalledPackageInfos(paramInt));
      if (h != null) {
        ??? = h.a(this.e, null, paramInt);
      }
    }
  }
  
  public String getInstallerPackageName(String paramString)
  {
    return this.c.getInstallerPackageName(paramString);
  }
  
  public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getInstrumentationInfo(paramComponentName, paramInt);
  }
  
  public Intent getLaunchIntentForPackage(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.INFO");
    localIntent.setPackage(paramString);
    Object localObject = queryIntentActivities(localIntent, 0);
    if ((localObject == null) || (((List)localObject).size() <= 0))
    {
      localIntent.removeCategory("android.intent.category.INFO");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setPackage(paramString);
    }
    for (paramString = queryIntentActivities(localIntent, 0);; paramString = (String)localObject)
    {
      if ((paramString == null) || (paramString.size() <= 0)) {
        return null;
      }
      localObject = new Intent(localIntent);
      ((Intent)localObject).setFlags(268435456);
      ((Intent)localObject).setClassName(((ResolveInfo)paramString.get(0)).activityInfo.packageName, ((ResolveInfo)paramString.get(0)).activityInfo.name);
      return localObject;
    }
  }
  
  @TargetApi(21)
  public Intent getLeanbackLaunchIntentForPackage(String paramString)
  {
    return this.c.getLeanbackLaunchIntentForPackage(paramString);
  }
  
  public String getNameForUid(int paramInt)
  {
    return this.c.getNameForUid(paramInt);
  }
  
  public PackageInfo getPackageArchiveInfo(String paramString, int paramInt)
  {
    return this.c.getPackageArchiveInfo(paramString, paramInt);
  }
  
  public int[] getPackageGids(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getPackageGids(paramString);
  }
  
  public int[] getPackageGids(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getPackageGids(paramString, paramInt);
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (h != null)
    {
      localObject = h.b(this.e, null, paramString, paramInt);
      if (localObject != null) {
        return localObject;
      }
    }
    Object localObject = b;
    if (paramInt == 0) {
      try
      {
        if (f.size() > 0)
        {
          Iterator localIterator = f.iterator();
          while (localIterator.hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
            if (TextUtils.equals(localPackageInfo.packageName, paramString)) {
              return localPackageInfo;
            }
          }
        }
      }
      finally {}
    }
    try
    {
      paramString = this.c.getPackageInfo(paramString, paramInt);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  @TargetApi(21)
  public PackageInstaller getPackageInstaller()
  {
    return this.c.getPackageInstaller();
  }
  
  public PackageManager getPackageManager()
  {
    return this.c;
  }
  
  public int getPackageUid(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getPackageUid(paramString, paramInt);
  }
  
  public String[] getPackagesForUid(int paramInt)
  {
    return this.c.getPackagesForUid(paramInt);
  }
  
  @TargetApi(18)
  public List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
  {
    return this.c.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
  }
  
  public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getPermissionGroupInfo(paramString, paramInt);
  }
  
  public PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getPermissionInfo(paramString, paramInt);
  }
  
  public List<ApplicationInfo> getPhoneAppApplications()
  {
    synchronized (b)
    {
      if (!a(8192)) {
        break label73;
      }
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = f.iterator();
      if (localIterator.hasNext()) {
        localArrayList.add(((PackageInfo)localIterator.next()).applicationInfo);
      }
    }
    return localList;
    label73:
    return getSystemInstalledApplications(8192);
  }
  
  public ApplicationInfo getPhoneApplicationInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if ((paramInt == 0) || (paramInt == 8192)) {}
    synchronized (b)
    {
      if (a(paramInt))
      {
        Iterator localIterator = f.iterator();
        while (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          if (TextUtils.equals(localPackageInfo.packageName, paramString))
          {
            paramString = localPackageInfo.applicationInfo;
            return paramString;
          }
        }
      }
      return this.c.getApplicationInfo(paramString, paramInt);
    }
  }
  
  public List<ApplicationInfo> getPluginApplicationInfoListByUid(int paramInt1, int paramInt2)
  {
    Object localObject = getPluginPackageInfoListByUid(paramInt1, paramInt2);
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(((PackageInfo)((Iterator)localObject).next()).applicationInfo);
    }
    return localArrayList;
  }
  
  public List<PackageInfo> getPluginPackageInfoListByUid(int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    if (h != null)
    {
      if ((paramInt2 == 0) || (paramInt2 == 8192)) {
        updatePluginPackageInfo();
      }
      for (;;)
      {
        synchronized (b)
        {
          localArrayList.addAll(g);
          if (paramInt1 < 0) {
            break;
          }
          ??? = h.h(paramInt1, null);
          Iterator localIterator = localArrayList.iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          if (((Set)???).contains(((PackageInfo)localIterator.next()).packageName)) {
            continue;
          }
          localIterator.remove();
        }
        localList.addAll(h.a(paramInt1, null, paramInt2));
      }
    }
    return localList;
  }
  
  public int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    return this.c.getPreferredActivities(paramList, paramList1, paramString);
  }
  
  public List<PackageInfo> getPreferredPackages(int paramInt)
  {
    return this.c.getPreferredPackages(paramInt);
  }
  
  @TargetApi(9)
  public ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (h != null)
    {
      ProviderInfo localProviderInfo = h.d(this.e, null, paramComponentName, paramInt);
      if (localProviderInfo != null) {
        return localProviderInfo;
      }
    }
    return this.c.getProviderInfo(paramComponentName, paramInt);
  }
  
  public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (h != null)
    {
      ActivityInfo localActivityInfo = h.b(this.e, null, paramComponentName, paramInt);
      if (localActivityInfo != null) {
        return localActivityInfo;
      }
    }
    return this.c.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public Resources getResourcesForActivity(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return getResourcesForApplication(getActivityInfo(paramComponentName, 0).applicationInfo);
  }
  
  public Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getResourcesForApplication(paramApplicationInfo);
  }
  
  public Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getResourcesForApplication(paramString);
  }
  
  public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (h != null)
    {
      ServiceInfo localServiceInfo = h.c(this.e, null, paramComponentName, paramInt);
      if (localServiceInfo != null) {
        return localServiceInfo;
      }
    }
    return this.c.getServiceInfo(paramComponentName, paramInt);
  }
  
  public FeatureInfo[] getSystemAvailableFeatures()
  {
    return this.c.getSystemAvailableFeatures();
  }
  
  /* Error */
  public List<ApplicationInfo> getSystemInstalledApplications(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: getfield 49	com/lbe/doubleagent/utility/PackageManagerWrapper:c	Landroid/content/pm/PackageManager;
    //   7: iload_1
    //   8: invokevirtual 521	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   11: astore 5
    //   13: aload 5
    //   15: astore 4
    //   17: iconst_0
    //   18: istore_2
    //   19: iload_2
    //   20: ifeq +85 -> 105
    //   23: iconst_0
    //   24: istore_3
    //   25: iload_3
    //   26: aload_0
    //   27: getfield 41	com/lbe/doubleagent/utility/PackageManagerWrapper:a	[I
    //   30: arraylength
    //   31: if_icmpge +47 -> 78
    //   34: iload_2
    //   35: ifeq +43 -> 78
    //   38: aload_0
    //   39: getfield 41	com/lbe/doubleagent/utility/PackageManagerWrapper:a	[I
    //   42: iload_3
    //   43: iaload
    //   44: i2l
    //   45: invokestatic 527	java/lang/Thread:sleep	(J)V
    //   48: aload_0
    //   49: getfield 49	com/lbe/doubleagent/utility/PackageManagerWrapper:c	Landroid/content/pm/PackageManager;
    //   52: iload_1
    //   53: invokevirtual 521	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   56: astore 5
    //   58: aload 5
    //   60: astore 4
    //   62: iconst_0
    //   63: istore_2
    //   64: iload_3
    //   65: iconst_1
    //   66: iadd
    //   67: istore_3
    //   68: goto -43 -> 25
    //   71: astore 5
    //   73: iconst_1
    //   74: istore_2
    //   75: goto -56 -> 19
    //   78: iload_2
    //   79: ifeq +13 -> 92
    //   82: aload_0
    //   83: getfield 49	com/lbe/doubleagent/utility/PackageManagerWrapper:c	Landroid/content/pm/PackageManager;
    //   86: iload_1
    //   87: invokevirtual 521	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   90: astore 4
    //   92: aload 4
    //   94: areturn
    //   95: astore 5
    //   97: goto -33 -> 64
    //   100: astore 5
    //   102: goto -54 -> 48
    //   105: goto -27 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	PackageManagerWrapper
    //   0	108	1	paramInt	int
    //   18	61	2	i	int
    //   24	44	3	j	int
    //   1	92	4	localObject	Object
    //   11	48	5	localList	List
    //   71	1	5	localThrowable1	Throwable
    //   95	1	5	localThrowable2	Throwable
    //   100	1	5	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   3	13	71	java/lang/Throwable
    //   48	58	95	java/lang/Throwable
    //   38	48	100	java/lang/InterruptedException
  }
  
  /* Error */
  public List<PackageInfo> getSystemInstalledPackageInfos(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: getfield 49	com/lbe/doubleagent/utility/PackageManagerWrapper:c	Landroid/content/pm/PackageManager;
    //   7: iload_1
    //   8: invokevirtual 529	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   11: astore 5
    //   13: aload 5
    //   15: astore 4
    //   17: iconst_0
    //   18: istore_2
    //   19: iload_2
    //   20: ifeq +85 -> 105
    //   23: iconst_0
    //   24: istore_3
    //   25: iload_3
    //   26: aload_0
    //   27: getfield 41	com/lbe/doubleagent/utility/PackageManagerWrapper:a	[I
    //   30: arraylength
    //   31: if_icmpge +47 -> 78
    //   34: iload_2
    //   35: ifeq +43 -> 78
    //   38: aload_0
    //   39: getfield 41	com/lbe/doubleagent/utility/PackageManagerWrapper:a	[I
    //   42: iload_3
    //   43: iaload
    //   44: i2l
    //   45: invokestatic 527	java/lang/Thread:sleep	(J)V
    //   48: aload_0
    //   49: getfield 49	com/lbe/doubleagent/utility/PackageManagerWrapper:c	Landroid/content/pm/PackageManager;
    //   52: iload_1
    //   53: invokevirtual 529	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   56: astore 5
    //   58: aload 5
    //   60: astore 4
    //   62: iconst_0
    //   63: istore_2
    //   64: iload_3
    //   65: iconst_1
    //   66: iadd
    //   67: istore_3
    //   68: goto -43 -> 25
    //   71: astore 5
    //   73: iconst_1
    //   74: istore_2
    //   75: goto -56 -> 19
    //   78: iload_2
    //   79: ifeq +13 -> 92
    //   82: aload_0
    //   83: getfield 49	com/lbe/doubleagent/utility/PackageManagerWrapper:c	Landroid/content/pm/PackageManager;
    //   86: iload_1
    //   87: invokevirtual 529	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   90: astore 4
    //   92: aload 4
    //   94: areturn
    //   95: astore 5
    //   97: goto -33 -> 64
    //   100: astore 5
    //   102: goto -54 -> 48
    //   105: goto -27 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	PackageManagerWrapper
    //   0	108	1	paramInt	int
    //   18	61	2	i	int
    //   24	44	3	j	int
    //   1	92	4	localObject	Object
    //   11	48	5	localList	List
    //   71	1	5	localThrowable1	Throwable
    //   95	1	5	localThrowable2	Throwable
    //   100	1	5	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   3	13	71	java/lang/Throwable
    //   48	58	95	java/lang/Throwable
    //   38	48	100	java/lang/InterruptedException
  }
  
  public String[] getSystemSharedLibraryNames()
  {
    return this.c.getSystemSharedLibraryNames();
  }
  
  public CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.c.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  @TargetApi(21)
  public Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
  {
    return this.c.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
  }
  
  @TargetApi(21)
  public Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
  {
    return this.c.getUserBadgedIcon(paramDrawable, paramUserHandle);
  }
  
  @TargetApi(21)
  public CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
  {
    return this.c.getUserBadgedLabel(paramCharSequence, paramUserHandle);
  }
  
  public XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.c.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public boolean hasSystemFeature(String paramString)
  {
    return this.c.hasSystemFeature(paramString);
  }
  
  public boolean hasSystemFeature(String paramString, int paramInt)
  {
    return this.c.hasSystemFeature(paramString, paramInt);
  }
  
  @TargetApi(23)
  public boolean isPermissionRevokedByPolicy(String paramString1, String paramString2)
  {
    return this.c.isPermissionRevokedByPolicy(paramString1, paramString2);
  }
  
  public boolean isSafeMode()
  {
    return this.c.isSafeMode();
  }
  
  public List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    List localList = this.c.queryBroadcastReceivers(paramIntent, paramInt);
    Object localObject = localList;
    if (localList == null) {
      localObject = new ArrayList();
    }
    b((List)localObject, paramIntent, paramInt);
    return localObject;
  }
  
  public List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    List localList = this.c.queryContentProviders(paramString, paramInt1, paramInt2);
    Object localObject = localList;
    if (localList == null) {
      localObject = new ArrayList();
    }
    a((List)localObject, paramString, paramInt1, paramInt2);
    return localObject;
  }
  
  public List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    return this.c.queryInstrumentation(paramString, paramInt);
  }
  
  public List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    List localList = this.c.queryIntentActivities(paramIntent, paramInt);
    Object localObject = localList;
    if (localList == null) {
      localObject = new ArrayList();
    }
    a((List)localObject, paramIntent, paramInt);
    return localObject;
  }
  
  public List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return this.c.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  @TargetApi(19)
  public List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
  {
    List localList = this.c.queryIntentContentProviders(paramIntent, paramInt);
    Object localObject = localList;
    if (localList == null) {
      localObject = new ArrayList();
    }
    c((List)localObject, paramIntent, paramInt);
    return localObject;
  }
  
  public List<ResolveInfo> queryIntentServices(int paramInt1, Intent paramIntent, int paramInt2)
  {
    try
    {
      List localList = this.c.queryIntentServices(paramIntent, paramInt2);
      Object localObject = localList;
      if (localList == null) {
        localObject = new ArrayList();
      }
      a(paramInt1, (List)localObject, paramIntent, paramInt2);
      return localObject;
    }
    catch (Exception paramIntent) {}
    return null;
  }
  
  public List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    try
    {
      List localList = this.c.queryIntentServices(paramIntent, paramInt);
      Object localObject = localList;
      if (localList == null) {
        localObject = new ArrayList();
      }
      a(this.e, (List)localObject, paramIntent, paramInt);
      return localObject;
    }
    catch (Exception paramIntent) {}
    return null;
  }
  
  public List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.c.queryPermissionsByGroup(paramString, paramInt);
  }
  
  public void removePackageFromPreferred(String paramString)
  {
    this.c.removePackageFromPreferred(paramString);
  }
  
  public void removePermission(String paramString)
  {
    this.c.removePermission(paramString);
  }
  
  public ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    paramIntent = queryIntentActivities(paramIntent, paramInt);
    if ((paramIntent != null) && (paramIntent.size() > 0)) {
      return (ResolveInfo)paramIntent.get(0);
    }
    return null;
  }
  
  public ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    if (h != null)
    {
      ProviderInfo localProviderInfo = h.d(this.e, null, paramString, paramInt);
      if (localProviderInfo != null) {
        return localProviderInfo;
      }
    }
    return this.c.resolveContentProvider(paramString, paramInt);
  }
  
  public ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return this.c.resolveService(paramIntent, paramInt);
  }
  
  public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    this.c.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    this.c.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
  
  @TargetApi(11)
  public void setInstallerPackageName(String paramString1, String paramString2)
  {
    this.c.setInstallerPackageName(paramString1, paramString2);
  }
  
  public void updatePackageInfo(PackageInfo paramPackageInfo, String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      updatePluginPackageInfo();
      synchronized (b)
      {
        a(g.iterator(), paramString);
        g.add(0, paramPackageInfo);
        return;
      }
    }
    if (f.size() > 0)
    {
      updatePluginPackageInfo();
      synchronized (b)
      {
        a(f.iterator(), paramString);
        f.add(0, paramPackageInfo);
        paramString = g.iterator();
        while (paramString.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramString.next();
          if (TextUtils.equals(localPackageInfo.packageName, paramPackageInfo.packageName)) {
            if (localPackageInfo.versionCode <= paramPackageInfo.versionCode) {
              paramString.remove();
            }
          }
        }
        return;
      }
    }
  }
  
  public void updatePackageInfoRemoved(String paramString, boolean paramBoolean)
  {
    localObject = b;
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        a(g.iterator(), paramString);
        return;
      }
      finally {}
      a(f.iterator(), paramString);
    }
  }
  
  @TargetApi(14)
  public void verifyPendingInstall(int paramInt1, int paramInt2)
  {
    this.c.verifyPendingInstall(paramInt1, paramInt2);
  }
}
