package com.lbe.parallel;

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
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import com.lbe.mdremote.common.o;
import com.lbe.mdremote.common.x.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class fq
  extends PackageManager
{
  private static final Object a = new Object();
  private static final List<PackageInfo> d = new ArrayList();
  private static final List<PackageInfo> e = new ArrayList();
  private static boolean f = false;
  private static o g;
  private PackageManager b;
  private int c;
  private int[] h = { 100, 500, 1000 };
  
  public fq(Context paramContext)
  {
    this(paramContext, fe.c(paramContext));
  }
  
  public fq(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, fe.c(paramContext));
  }
  
  private fq(Context paramContext, int paramInt, o paramO)
  {
    this.b = paramContext.getPackageManager();
    this.c = paramInt;
    g = paramO;
  }
  
  private fq(Context paramContext, o paramO)
  {
    this.b = paramContext.getPackageManager();
    g = paramO;
    paramContext = new a();
    if (!f) {}
    try
    {
      g.a(paramContext);
      f = true;
      return;
    }
    catch (RemoteException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  private List<ApplicationInfo> a(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: getfield 65	com/lbe/parallel/fq:b	Landroid/content/pm/PackageManager;
    //   7: iload_1
    //   8: invokevirtual 88	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
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
    //   27: getfield 57	com/lbe/parallel/fq:h	[I
    //   30: arraylength
    //   31: if_icmpge +47 -> 78
    //   34: iload_2
    //   35: ifeq +43 -> 78
    //   38: aload_0
    //   39: getfield 57	com/lbe/parallel/fq:h	[I
    //   42: iload_3
    //   43: iaload
    //   44: i2l
    //   45: invokestatic 94	java/lang/Thread:sleep	(J)V
    //   48: aload_0
    //   49: getfield 65	com/lbe/parallel/fq:b	Landroid/content/pm/PackageManager;
    //   52: iload_1
    //   53: invokevirtual 88	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
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
    //   83: getfield 65	com/lbe/parallel/fq:b	Landroid/content/pm/PackageManager;
    //   86: iload_1
    //   87: invokevirtual 88	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
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
    //   0	108	0	this	fq
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
  
  public static List<PackageInfo> a(int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    if (g != null)
    {
      if ((paramInt2 == 0) || (paramInt2 == 8192)) {
        a.b();
      }
      for (;;)
      {
        synchronized (a)
        {
          localArrayList.addAll(e);
          if (paramInt1 < 0) {
            return localArrayList;
          }
        }
        try
        {
          ??? = g.e(paramInt1, null);
          Iterator localIterator = localArrayList.iterator();
          for (;;)
          {
            if (localIterator.hasNext()) {
              if (!((List)???).contains(((PackageInfo)localIterator.next()).packageName))
              {
                localIterator.remove();
                continue;
                localList = finally;
                throw localList;
                try
                {
                  localList.addAll(g.a(paramInt1, null, paramInt2));
                }
                catch (RemoteException localRemoteException1)
                {
                  localRemoteException1.printStackTrace();
                }
              }
            }
          }
        }
        catch (RemoteException localRemoteException2)
        {
          for (;;)
          {
            localRemoteException2.printStackTrace();
            Object localObject2 = null;
          }
        }
      }
    }
    return localList;
  }
  
  private static void a(int paramInt1, List<ResolveInfo> paramList, Intent paramIntent, int paramInt2)
  {
    if (g == null) {}
    for (;;)
    {
      return;
      try
      {
        paramIntent = g.a(paramInt1, null, paramIntent, "", paramInt2);
        if ((paramIntent != null) && (paramIntent.size() > 0))
        {
          localHashSet = new HashSet();
          localIterator = paramIntent.iterator();
          while (localIterator.hasNext()) {
            localHashSet.add(((ResolveInfo)localIterator.next()).serviceInfo.packageName);
          }
        }
      }
      catch (RemoteException paramIntent)
      {
        HashSet localHashSet;
        for (;;)
        {
          paramIntent.printStackTrace();
          paramIntent = null;
        }
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (localHashSet.contains(((ResolveInfo)localIterator.next()).serviceInfo.packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(paramIntent);
      }
    }
  }
  
  private void a(List<ResolveInfo> paramList, Intent paramIntent, int paramInt)
  {
    if (g == null) {}
    for (;;)
    {
      return;
      try
      {
        paramIntent = g.c(this.c, null, paramIntent, "", paramInt);
        if ((paramIntent != null) && (paramIntent.size() > 0))
        {
          localHashSet = new HashSet();
          localIterator = paramIntent.iterator();
          while (localIterator.hasNext()) {
            localHashSet.add(((ResolveInfo)localIterator.next()).activityInfo.packageName);
          }
        }
      }
      catch (RemoteException paramIntent)
      {
        HashSet localHashSet;
        for (;;)
        {
          paramIntent.printStackTrace();
          paramIntent = null;
        }
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (localHashSet.contains(((ResolveInfo)localIterator.next()).activityInfo.packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(paramIntent);
      }
    }
  }
  
  private void a(List<ProviderInfo> paramList, String paramString, int paramInt)
  {
    HashSet localHashSet = null;
    if (g == null) {}
    for (;;)
    {
      return;
      try
      {
        paramString = g.e(this.c, null, paramString, paramInt);
        if ((paramString != null) && (paramString.size() > 0))
        {
          localHashSet = new HashSet();
          localIterator = paramString.iterator();
          while (localIterator.hasNext()) {
            localHashSet.add(((ProviderInfo)localIterator.next()).packageName);
          }
        }
      }
      catch (RemoteException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
          paramString = localHashSet;
        }
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (localHashSet.contains(((ProviderInfo)localIterator.next()).packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(paramString);
      }
    }
  }
  
  private static void a(List<PackageInfo> paramList1, List<PackageInfo> paramList2)
  {
    if ((paramList2 == null) || (paramList2.size() == 0) || (paramList1.size() == 0)) {
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
  
  /* Error */
  private List<PackageInfo> b(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: getfield 65	com/lbe/parallel/fq:b	Landroid/content/pm/PackageManager;
    //   7: iload_1
    //   8: invokevirtual 192	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
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
    //   27: getfield 57	com/lbe/parallel/fq:h	[I
    //   30: arraylength
    //   31: if_icmpge +47 -> 78
    //   34: iload_2
    //   35: ifeq +43 -> 78
    //   38: aload_0
    //   39: getfield 57	com/lbe/parallel/fq:h	[I
    //   42: iload_3
    //   43: iaload
    //   44: i2l
    //   45: invokestatic 94	java/lang/Thread:sleep	(J)V
    //   48: aload_0
    //   49: getfield 65	com/lbe/parallel/fq:b	Landroid/content/pm/PackageManager;
    //   52: iload_1
    //   53: invokevirtual 192	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
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
    //   83: getfield 65	com/lbe/parallel/fq:b	Landroid/content/pm/PackageManager;
    //   86: iload_1
    //   87: invokevirtual 192	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
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
    //   0	108	0	this	fq
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
  
  private static List<ApplicationInfo> b(int paramInt1, int paramInt2)
  {
    Object localObject = a(paramInt1, paramInt2);
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(((PackageInfo)((Iterator)localObject).next()).applicationInfo);
    }
    return localArrayList;
  }
  
  private void b(List<ResolveInfo> paramList, Intent paramIntent, int paramInt)
  {
    if (g == null) {}
    for (;;)
    {
      return;
      try
      {
        paramIntent = g.d(this.c, null, paramIntent, "", paramInt);
        if ((paramIntent != null) && (paramIntent.size() > 0))
        {
          localHashSet = new HashSet();
          localIterator = paramIntent.iterator();
          while (localIterator.hasNext()) {
            localHashSet.add(((ResolveInfo)localIterator.next()).activityInfo.packageName);
          }
        }
      }
      catch (RemoteException paramIntent)
      {
        HashSet localHashSet;
        for (;;)
        {
          paramIntent.printStackTrace();
          paramIntent = null;
        }
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (localHashSet.contains(((ResolveInfo)localIterator.next()).activityInfo.packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(paramIntent);
      }
    }
  }
  
  private static void b(List<ApplicationInfo> paramList1, List<ApplicationInfo> paramList2)
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
    if (g == null) {}
    for (;;)
    {
      return;
      try
      {
        paramIntent = g.b(this.c, null, paramIntent, "", paramInt);
        if ((paramIntent != null) && (paramIntent.size() > 0))
        {
          localHashSet = new HashSet();
          localIterator = paramIntent.iterator();
          while (localIterator.hasNext()) {
            localHashSet.add(((ResolveInfo)localIterator.next()).providerInfo.packageName);
          }
        }
      }
      catch (RemoteException paramIntent)
      {
        HashSet localHashSet;
        for (;;)
        {
          paramIntent.printStackTrace();
          paramIntent = null;
        }
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (localHashSet.contains(((ResolveInfo)localIterator.next()).providerInfo.packageName)) {
            localIterator.remove();
          }
        }
        paramList.addAll(paramIntent);
      }
    }
  }
  
  private List<ApplicationInfo> f()
  {
    synchronized (a)
    {
      if (!g()) {
        break label70;
      }
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = d.iterator();
      if (localIterator.hasNext()) {
        localArrayList.add(((PackageInfo)localIterator.next()).applicationInfo);
      }
    }
    return localList;
    label70:
    return a(8192);
  }
  
  private boolean g()
  {
    if (d.size() == 0) {
      d.addAll(b(8192));
    }
    return d.size() > 0;
  }
  
  public final PackageManager a()
  {
    return this.b;
  }
  
  public final void addPackageToPreferred(String paramString)
  {
    this.b.addPackageToPreferred(paramString);
  }
  
  public final boolean addPermission(PermissionInfo paramPermissionInfo)
  {
    return this.b.addPermission(paramPermissionInfo);
  }
  
  @TargetApi(8)
  public final boolean addPermissionAsync(PermissionInfo paramPermissionInfo)
  {
    return this.b.addPermissionAsync(paramPermissionInfo);
  }
  
  public final void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
  {
    this.b.addPreferredActivity(paramIntentFilter, paramInt, paramArrayOfComponentName, paramComponentName);
  }
  
  @TargetApi(8)
  public final String[] canonicalToCurrentPackageNames(String[] paramArrayOfString)
  {
    return this.b.canonicalToCurrentPackageNames(paramArrayOfString);
  }
  
  public final int checkPermission(String paramString1, String paramString2)
  {
    return this.b.checkPermission(paramString1, paramString2);
  }
  
  public final int checkSignatures(int paramInt1, int paramInt2)
  {
    return this.b.checkSignatures(paramInt1, paramInt2);
  }
  
  public final int checkSignatures(String paramString1, String paramString2)
  {
    return this.b.checkSignatures(paramString1, paramString2);
  }
  
  public final void clearPackagePreferredActivities(String paramString)
  {
    this.b.clearPackagePreferredActivities(paramString);
  }
  
  @TargetApi(8)
  public final String[] currentToCanonicalPackageNames(String[] paramArrayOfString)
  {
    return this.b.currentToCanonicalPackageNames(paramArrayOfString);
  }
  
  @TargetApi(17)
  public final void extendVerificationTimeout(int paramInt1, int paramInt2, long paramLong)
  {
    this.b.extendVerificationTimeout(paramInt1, paramInt2, paramLong);
  }
  
  @TargetApi(20)
  public final Drawable getActivityBanner(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getActivityBanner(paramComponentName);
  }
  
  @TargetApi(20)
  public final Drawable getActivityBanner(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getActivityBanner(paramIntent);
  }
  
  public final Drawable getActivityIcon(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getActivityIcon(paramComponentName);
  }
  
  public final Drawable getActivityIcon(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getActivityIcon(paramIntent);
  }
  
  public final ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (g != null) {
      try
      {
        ActivityInfo localActivityInfo = g.a(this.c, null, paramComponentName, paramInt);
        if (localActivityInfo != null) {
          return localActivityInfo;
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          Object localObject = null;
        }
      }
    }
    return this.b.getActivityInfo(paramComponentName, paramInt);
  }
  
  @TargetApi(9)
  public final Drawable getActivityLogo(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getActivityLogo(paramComponentName);
  }
  
  @TargetApi(9)
  public final Drawable getActivityLogo(Intent paramIntent)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getActivityLogo(paramIntent);
  }
  
  public final List<PermissionGroupInfo> getAllPermissionGroups(int paramInt)
  {
    return this.b.getAllPermissionGroups(paramInt);
  }
  
  @TargetApi(20)
  public final Drawable getApplicationBanner(ApplicationInfo paramApplicationInfo)
  {
    return this.b.getApplicationBanner(paramApplicationInfo);
  }
  
  @TargetApi(20)
  public final Drawable getApplicationBanner(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getApplicationBanner(paramString);
  }
  
  public final int getApplicationEnabledSetting(String paramString)
  {
    return this.b.getApplicationEnabledSetting(paramString);
  }
  
  public final Drawable getApplicationIcon(ApplicationInfo paramApplicationInfo)
  {
    return this.b.getApplicationIcon(paramApplicationInfo);
  }
  
  public final Drawable getApplicationIcon(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return getApplicationIcon(getApplicationInfo(paramString, 0));
  }
  
  public final ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (g != null) {
      try
      {
        ApplicationInfo localApplicationInfo = g.c(this.c, null, paramString, paramInt);
        if (localApplicationInfo != null) {
          return localApplicationInfo;
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          localObject = null;
        }
      }
    }
    Object localObject = a;
    if (paramInt == 0) {
      try
      {
        if (d.size() > 0)
        {
          Iterator localIterator = d.iterator();
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
    return this.b.getApplicationInfo(paramString, paramInt);
  }
  
  public final CharSequence getApplicationLabel(ApplicationInfo paramApplicationInfo)
  {
    return this.b.getApplicationLabel(paramApplicationInfo);
  }
  
  @TargetApi(9)
  public final Drawable getApplicationLogo(ApplicationInfo paramApplicationInfo)
  {
    return this.b.getApplicationLogo(paramApplicationInfo);
  }
  
  @TargetApi(9)
  public final Drawable getApplicationLogo(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getApplicationLogo(paramString);
  }
  
  public final int getComponentEnabledSetting(ComponentName paramComponentName)
  {
    return this.b.getComponentEnabledSetting(paramComponentName);
  }
  
  public final Drawable getDefaultActivityIcon()
  {
    return this.b.getDefaultActivityIcon();
  }
  
  public final Drawable getDrawable(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.b.getDrawable(paramString, paramInt, paramApplicationInfo);
  }
  
  public final List<ApplicationInfo> getInstalledApplications(int paramInt)
  {
    Object localObject4 = null;
    ArrayList localArrayList = new ArrayList();
    Object localObject3;
    Object localObject1;
    if ((paramInt == 0) || (paramInt == 8192))
    {
      localObject3 = f();
      localObject1 = b(this.c, paramInt);
    }
    for (;;)
    {
      b((List)localObject3, (List)localObject1);
      return localObject3;
      localArrayList.addAll(a(paramInt));
      localObject1 = localObject4;
      localObject3 = localArrayList;
      if (g != null) {
        try
        {
          localObject1 = g.d(this.c, null, paramInt);
          localObject3 = localArrayList;
        }
        catch (RemoteException localRemoteException)
        {
          localRemoteException.printStackTrace();
          Object localObject2 = localObject4;
          localObject3 = localArrayList;
        }
      }
    }
  }
  
  public final List<PackageInfo> getInstalledPackages(int paramInt)
  {
    Object localObject3 = null;
    ArrayList localArrayList = new ArrayList();
    if ((paramInt == 0) || (paramInt == 8192)) {}
    for (;;)
    {
      synchronized (a)
      {
        g();
        localArrayList.addAll(d);
        ??? = a(this.c, paramInt);
        a(localArrayList, (List)???);
        return localArrayList;
      }
      localArrayList.addAll(b(paramInt));
      ??? = localObject4;
      if (g != null) {
        try
        {
          ??? = g.a(this.c, null, paramInt);
        }
        catch (RemoteException localRemoteException)
        {
          localRemoteException.printStackTrace();
          Object localObject2 = localObject4;
        }
      }
    }
  }
  
  public final String getInstallerPackageName(String paramString)
  {
    return this.b.getInstallerPackageName(paramString);
  }
  
  public final InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getInstrumentationInfo(paramComponentName, paramInt);
  }
  
  public final Intent getLaunchIntentForPackage(String paramString)
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
  public final Intent getLeanbackLaunchIntentForPackage(String paramString)
  {
    return this.b.getLeanbackLaunchIntentForPackage(paramString);
  }
  
  public final String getNameForUid(int paramInt)
  {
    return this.b.getNameForUid(paramInt);
  }
  
  public final PackageInfo getPackageArchiveInfo(String paramString, int paramInt)
  {
    return this.b.getPackageArchiveInfo(paramString, paramInt);
  }
  
  public final int[] getPackageGids(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getPackageGids(paramString);
  }
  
  public final int[] getPackageGids(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getPackageGids(paramString, paramInt);
  }
  
  public final PackageInfo getPackageInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (g != null) {
      try
      {
        PackageInfo localPackageInfo1 = g.d(this.c, null, paramString, paramInt);
        if (localPackageInfo1 != null) {
          return localPackageInfo1;
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          localObject = null;
        }
      }
    }
    Object localObject = a;
    if (paramInt == 0) {
      try
      {
        if (d.size() > 0)
        {
          Iterator localIterator = d.iterator();
          while (localIterator.hasNext())
          {
            PackageInfo localPackageInfo2 = (PackageInfo)localIterator.next();
            if (TextUtils.equals(localPackageInfo2.packageName, paramString)) {
              return localPackageInfo2;
            }
          }
        }
      }
      finally {}
    }
    try
    {
      paramString = this.b.getPackageInfo(paramString, paramInt);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  @TargetApi(21)
  public final PackageInstaller getPackageInstaller()
  {
    return this.b.getPackageInstaller();
  }
  
  public final int getPackageUid(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getPackageUid(paramString, paramInt);
  }
  
  public final String[] getPackagesForUid(int paramInt)
  {
    return this.b.getPackagesForUid(paramInt);
  }
  
  @TargetApi(18)
  public final List<PackageInfo> getPackagesHoldingPermissions(String[] paramArrayOfString, int paramInt)
  {
    return this.b.getPackagesHoldingPermissions(paramArrayOfString, paramInt);
  }
  
  public final PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getPermissionGroupInfo(paramString, paramInt);
  }
  
  public final PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getPermissionInfo(paramString, paramInt);
  }
  
  public final int getPreferredActivities(List<IntentFilter> paramList, List<ComponentName> paramList1, String paramString)
  {
    return this.b.getPreferredActivities(paramList, paramList1, paramString);
  }
  
  public final List<PackageInfo> getPreferredPackages(int paramInt)
  {
    return this.b.getPreferredPackages(paramInt);
  }
  
  @TargetApi(9)
  public final ProviderInfo getProviderInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (g != null) {
      try
      {
        ProviderInfo localProviderInfo = g.b(this.c, null, paramComponentName, paramInt);
        if (localProviderInfo != null) {
          return localProviderInfo;
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          Object localObject = null;
        }
      }
    }
    return this.b.getProviderInfo(paramComponentName, paramInt);
  }
  
  public final ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (g != null) {
      try
      {
        ActivityInfo localActivityInfo = g.c(this.c, null, paramComponentName, paramInt);
        if (localActivityInfo != null) {
          return localActivityInfo;
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          Object localObject = null;
        }
      }
    }
    return this.b.getReceiverInfo(paramComponentName, paramInt);
  }
  
  public final Resources getResourcesForActivity(ComponentName paramComponentName)
    throws PackageManager.NameNotFoundException
  {
    return getResourcesForApplication(getActivityInfo(paramComponentName, 0).applicationInfo);
  }
  
  public final Resources getResourcesForApplication(ApplicationInfo paramApplicationInfo)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getResourcesForApplication(paramApplicationInfo);
  }
  
  public final Resources getResourcesForApplication(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.b.getResourcesForApplication(paramString);
  }
  
  public final ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    if (g != null) {
      try
      {
        ServiceInfo localServiceInfo = g.d(this.c, null, paramComponentName, paramInt);
        if (localServiceInfo != null) {
          return localServiceInfo;
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          Object localObject = null;
        }
      }
    }
    return this.b.getServiceInfo(paramComponentName, paramInt);
  }
  
  public final FeatureInfo[] getSystemAvailableFeatures()
  {
    return this.b.getSystemAvailableFeatures();
  }
  
  public final String[] getSystemSharedLibraryNames()
  {
    return this.b.getSystemSharedLibraryNames();
  }
  
  public final CharSequence getText(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.b.getText(paramString, paramInt, paramApplicationInfo);
  }
  
  @TargetApi(21)
  public final Drawable getUserBadgedDrawableForDensity(Drawable paramDrawable, UserHandle paramUserHandle, Rect paramRect, int paramInt)
  {
    return this.b.getUserBadgedDrawableForDensity(paramDrawable, paramUserHandle, paramRect, paramInt);
  }
  
  @TargetApi(21)
  public final Drawable getUserBadgedIcon(Drawable paramDrawable, UserHandle paramUserHandle)
  {
    return this.b.getUserBadgedIcon(paramDrawable, paramUserHandle);
  }
  
  @TargetApi(21)
  public final CharSequence getUserBadgedLabel(CharSequence paramCharSequence, UserHandle paramUserHandle)
  {
    return this.b.getUserBadgedLabel(paramCharSequence, paramUserHandle);
  }
  
  public final XmlResourceParser getXml(String paramString, int paramInt, ApplicationInfo paramApplicationInfo)
  {
    return this.b.getXml(paramString, paramInt, paramApplicationInfo);
  }
  
  public final boolean hasSystemFeature(String paramString)
  {
    return this.b.hasSystemFeature(paramString);
  }
  
  public final boolean hasSystemFeature(String paramString, int paramInt)
  {
    return this.b.hasSystemFeature(paramString, paramInt);
  }
  
  @TargetApi(23)
  public final boolean isPermissionRevokedByPolicy(String paramString1, String paramString2)
  {
    return this.b.isPermissionRevokedByPolicy(paramString1, paramString2);
  }
  
  public final boolean isSafeMode()
  {
    return this.b.isSafeMode();
  }
  
  public final List<ResolveInfo> queryBroadcastReceivers(Intent paramIntent, int paramInt)
  {
    List localList = this.b.queryBroadcastReceivers(paramIntent, paramInt);
    Object localObject = localList;
    if (localList == null) {
      localObject = new ArrayList();
    }
    b((List)localObject, paramIntent, paramInt);
    return localObject;
  }
  
  public final List<ProviderInfo> queryContentProviders(String paramString, int paramInt1, int paramInt2)
  {
    List localList = this.b.queryContentProviders(paramString, paramInt1, paramInt2);
    Object localObject = localList;
    if (localList == null) {
      localObject = new ArrayList();
    }
    a((List)localObject, paramString, paramInt2);
    return localObject;
  }
  
  public final List<InstrumentationInfo> queryInstrumentation(String paramString, int paramInt)
  {
    return this.b.queryInstrumentation(paramString, paramInt);
  }
  
  public final List<ResolveInfo> queryIntentActivities(Intent paramIntent, int paramInt)
  {
    List localList = this.b.queryIntentActivities(paramIntent, paramInt);
    Object localObject = localList;
    if (localList == null) {
      localObject = new ArrayList();
    }
    a((List)localObject, paramIntent, paramInt);
    return localObject;
  }
  
  public final List<ResolveInfo> queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt)
  {
    return this.b.queryIntentActivityOptions(paramComponentName, paramArrayOfIntent, paramIntent, paramInt);
  }
  
  @TargetApi(19)
  public final List<ResolveInfo> queryIntentContentProviders(Intent paramIntent, int paramInt)
  {
    List localList = this.b.queryIntentContentProviders(paramIntent, paramInt);
    Object localObject = localList;
    if (localList == null) {
      localObject = new ArrayList();
    }
    c((List)localObject, paramIntent, paramInt);
    return localObject;
  }
  
  public final List<ResolveInfo> queryIntentServices(Intent paramIntent, int paramInt)
  {
    try
    {
      List localList = this.b.queryIntentServices(paramIntent, paramInt);
      Object localObject = localList;
      if (localList == null) {
        localObject = new ArrayList();
      }
      a(this.c, (List)localObject, paramIntent, paramInt);
      return localObject;
    }
    catch (Exception paramIntent) {}
    return null;
  }
  
  public final List<PermissionInfo> queryPermissionsByGroup(String paramString, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    return this.b.queryPermissionsByGroup(paramString, paramInt);
  }
  
  public final void removePackageFromPreferred(String paramString)
  {
    this.b.removePackageFromPreferred(paramString);
  }
  
  public final void removePermission(String paramString)
  {
    this.b.removePermission(paramString);
  }
  
  public final ResolveInfo resolveActivity(Intent paramIntent, int paramInt)
  {
    paramIntent = queryIntentActivities(paramIntent, paramInt);
    if ((paramIntent != null) && (paramIntent.size() > 0)) {
      return (ResolveInfo)paramIntent.get(0);
    }
    return null;
  }
  
  public final ProviderInfo resolveContentProvider(String paramString, int paramInt)
  {
    if (g != null) {
      try
      {
        ProviderInfo localProviderInfo = g.b(this.c, null, paramString, paramInt);
        if (localProviderInfo != null) {
          return localProviderInfo;
        }
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          Object localObject = null;
        }
      }
    }
    return this.b.resolveContentProvider(paramString, paramInt);
  }
  
  public final ResolveInfo resolveService(Intent paramIntent, int paramInt)
  {
    return this.b.resolveService(paramIntent, paramInt);
  }
  
  public final void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
  {
    this.b.setApplicationEnabledSetting(paramString, paramInt1, paramInt2);
  }
  
  public final void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
  {
    this.b.setComponentEnabledSetting(paramComponentName, paramInt1, paramInt2);
  }
  
  @TargetApi(11)
  public final void setInstallerPackageName(String paramString1, String paramString2)
  {
    this.b.setInstallerPackageName(paramString1, paramString2);
  }
  
  @TargetApi(14)
  public final void verifyPendingInstall(int paramInt1, int paramInt2)
  {
    this.b.verifyPendingInstall(paramInt1, paramInt2);
  }
  
  static final class a
    extends x.a
  {
    a() {}
    
    private static PackageInfo a(Iterator<PackageInfo> paramIterator, String paramString)
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
    
    protected static void b()
    {
      Object localObject4 = null;
      int i = 0;
      synchronized (fq.b())
      {
        if (fq.c().size() == 0) {
          i = 1;
        }
        if (i != 0)
        {
          ??? = localObject4;
          if (fq.e() == null) {}
        }
      }
      try
      {
        ??? = fq.e().a(-1, null, 0);
        localObject4 = fq.b();
        if (??? != null) {}
        try
        {
          if (fq.c().size() == 0) {
            fq.c().addAll((Collection)???);
          }
          return;
        }
        finally {}
        localObject5 = finally;
        throw localObject5;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          Object localObject2 = localObject5;
        }
      }
    }
    
    public final void a()
    {
      synchronized ()
      {
        fq.d().clear();
        return;
      }
    }
    
    public final void a(PackageInfo paramPackageInfo, String paramString, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        b();
        synchronized (fq.b())
        {
          a(fq.c().iterator(), paramString);
          fq.c().add(0, paramPackageInfo);
          return;
        }
      }
      if (fq.d().size() > 0)
      {
        b();
        synchronized (fq.b())
        {
          a(fq.d().iterator(), paramString);
          fq.d().add(0, paramPackageInfo);
          paramString = fq.c().iterator();
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
    
    public final void a(String paramString, boolean paramBoolean)
    {
      localObject = fq.b();
      if (paramBoolean) {}
      for (;;)
      {
        try
        {
          a(fq.c().iterator(), paramString);
          return;
        }
        finally {}
        a(fq.d().iterator(), paramString);
      }
    }
  }
}
