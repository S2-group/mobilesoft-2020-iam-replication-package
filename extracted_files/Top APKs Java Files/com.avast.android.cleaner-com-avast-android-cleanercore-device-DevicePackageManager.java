package com.avast.android.cleanercore.device;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.RemoteException;
import com.avast.android.cleanercore.exception.InvalidApkFileException;
import com.avast.android.cleanercore.exception.PackageManagerException;
import com.avast.android.utils.android.IntentUtils;
import eu.inmite.android.fw.DebugLog;
import eu.inmite.android.fw.SL;
import eu.inmite.android.fw.interfaces.IService;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DevicePackageManager
  implements IService
{
  private static final List<String> a = Arrays.asList(new String[] { "com.google.android.gms", "com.google.android.instantapps.supervisor" });
  private final Context b;
  private final PackageManager c;
  private final DevicePolicyManager d;
  private final ActivityManager e;
  private LinkedList<ActivityInfo> f;
  
  public DevicePackageManager(Context paramContext)
  {
    this.b = paramContext;
    this.c = paramContext.getPackageManager();
    this.e = ((ActivityManager)paramContext.getSystemService("activity"));
    this.d = ((DevicePolicyManager)paramContext.getSystemService("device_policy"));
  }
  
  /* Error */
  private void b(String paramString, IPackageStatsObserver.Stub paramStub)
    throws PackageManagerException
  {
    // Byte code:
    //   0: new 78	android/content/pm/PackageStats
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 81	android/content/pm/PackageStats:<init>	(Ljava/lang/String;)V
    //   8: astore_3
    //   9: ldc 83
    //   11: invokestatic 88	eu/inmite/android/fw/SL:a	(Ljava/lang/Class;)Ljava/lang/Object;
    //   14: checkcast 83	com/avast/android/cleanercore/device/DeviceStorageManager
    //   17: astore 4
    //   19: aload_3
    //   20: aload 4
    //   22: aload 4
    //   24: aload_1
    //   25: invokevirtual 91	com/avast/android/cleanercore/device/DeviceStorageManager:b	(Ljava/lang/String;)Ljava/io/File;
    //   28: invokevirtual 94	com/avast/android/cleanercore/device/DeviceStorageManager:a	(Ljava/io/File;)J
    //   31: putfield 98	android/content/pm/PackageStats:externalCacheSize	J
    //   34: aload_3
    //   35: aload 4
    //   37: aload 4
    //   39: aload_1
    //   40: invokevirtual 100	com/avast/android/cleanercore/device/DeviceStorageManager:c	(Ljava/lang/String;)Ljava/io/File;
    //   43: invokevirtual 94	com/avast/android/cleanercore/device/DeviceStorageManager:a	(Ljava/io/File;)J
    //   46: putfield 103	android/content/pm/PackageStats:externalDataSize	J
    //   49: aload_3
    //   50: aload 4
    //   52: aload 4
    //   54: aload_1
    //   55: invokevirtual 105	com/avast/android/cleanercore/device/DeviceStorageManager:a	(Ljava/lang/String;)Ljava/io/File;
    //   58: invokevirtual 94	com/avast/android/cleanercore/device/DeviceStorageManager:a	(Ljava/io/File;)J
    //   61: putfield 108	android/content/pm/PackageStats:externalObbSize	J
    //   64: aload_0
    //   65: getfield 45	com/avast/android/cleanercore/device/DevicePackageManager:b	Landroid/content/Context;
    //   68: ldc 110
    //   70: invokevirtual 112	android/content/Context:getSystemService	(Ljava/lang/Class;)Ljava/lang/Object;
    //   73: checkcast 110	android/app/usage/StorageStatsManager
    //   76: astore 4
    //   78: aload 4
    //   80: ifnull +63 -> 143
    //   83: invokestatic 118	android/os/Process:myUserHandle	()Landroid/os/UserHandle;
    //   86: astore 5
    //   88: aload 4
    //   90: getstatic 124	android/os/storage/StorageManager:UUID_DEFAULT	Ljava/util/UUID;
    //   93: aload_1
    //   94: aload 5
    //   96: invokevirtual 128	android/app/usage/StorageStatsManager:queryStatsForPackage	(Ljava/util/UUID;Ljava/lang/String;Landroid/os/UserHandle;)Landroid/app/usage/StorageStats;
    //   99: astore 4
    //   101: aload_3
    //   102: aload 4
    //   104: invokevirtual 134	android/app/usage/StorageStats:getAppBytes	()J
    //   107: aload_3
    //   108: getfield 108	android/content/pm/PackageStats:externalObbSize	J
    //   111: lsub
    //   112: putfield 137	android/content/pm/PackageStats:codeSize	J
    //   115: aload_3
    //   116: aload 4
    //   118: invokevirtual 140	android/app/usage/StorageStats:getDataBytes	()J
    //   121: aload_3
    //   122: getfield 103	android/content/pm/PackageStats:externalDataSize	J
    //   125: lsub
    //   126: putfield 143	android/content/pm/PackageStats:dataSize	J
    //   129: aload_3
    //   130: aload 4
    //   132: invokevirtual 146	android/app/usage/StorageStats:getCacheBytes	()J
    //   135: aload_3
    //   136: getfield 98	android/content/pm/PackageStats:externalCacheSize	J
    //   139: lsub
    //   140: putfield 149	android/content/pm/PackageStats:cacheSize	J
    //   143: aload_2
    //   144: aload_3
    //   145: iconst_1
    //   146: invokevirtual 155	android/content/pm/IPackageStatsObserver$Stub:onGetStatsCompleted	(Landroid/content/pm/PackageStats;Z)V
    //   149: return
    //   150: astore 4
    //   152: aload_3
    //   153: aload_0
    //   154: aload_1
    //   155: invokespecial 158	com/avast/android/cleanercore/device/DevicePackageManager:i	(Ljava/lang/String;)Ljava/io/File;
    //   158: invokevirtual 163	java/io/File:length	()J
    //   161: putfield 137	android/content/pm/PackageStats:codeSize	J
    //   164: goto -21 -> 143
    //   167: astore_1
    //   168: goto -25 -> 143
    //   171: astore_1
    //   172: new 72	com/avast/android/cleanercore/exception/PackageManagerException
    //   175: dup
    //   176: aload_1
    //   177: invokevirtual 167	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   180: aload_1
    //   181: invokespecial 170	com/avast/android/cleanercore/exception/PackageManagerException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   184: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	185	0	this	DevicePackageManager
    //   0	185	1	paramString	String
    //   0	185	2	paramStub	IPackageStatsObserver.Stub
    //   8	145	3	localPackageStats	PackageStats
    //   17	114	4	localObject	Object
    //   150	1	4	localSecurityException	SecurityException
    //   86	9	5	localUserHandle	android.os.UserHandle
    // Exception table:
    //   from	to	target	type
    //   83	143	150	java/lang/SecurityException
    //   152	164	167	java/lang/Exception
    //   0	78	171	java/lang/Exception
    //   83	143	171	java/lang/Exception
    //   143	149	171	java/lang/Exception
  }
  
  private void c(final String paramString, final IPackageStatsObserver.Stub paramStub)
    throws PackageManagerException
  {
    try
    {
      PackageManager.class.getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class }).invoke(this.c, new Object[] { paramString, new IPackageStatsObserver.Stub()
      {
        public void onGetStatsCompleted(PackageStats paramAnonymousPackageStats, boolean paramAnonymousBoolean)
          throws RemoteException
        {
          if (paramAnonymousBoolean)
          {
            DeviceStorageManager localDeviceStorageManager = (DeviceStorageManager)SL.a(DeviceStorageManager.class);
            if (paramAnonymousPackageStats.externalObbSize == 0L)
            {
              paramAnonymousPackageStats.externalObbSize = localDeviceStorageManager.a(localDeviceStorageManager.a(paramString));
              if ((paramAnonymousPackageStats.externalObbSize > 0L) && (paramAnonymousPackageStats.codeSize > paramAnonymousPackageStats.externalObbSize)) {
                paramAnonymousPackageStats.codeSize -= paramAnonymousPackageStats.externalObbSize;
              }
            }
            File localFile = localDeviceStorageManager.b(paramString);
            if ((paramAnonymousPackageStats.externalCacheSize == 0L) && (localFile.exists()) && (localFile.listFiles().length > 0))
            {
              paramAnonymousPackageStats.externalCacheSize = localDeviceStorageManager.a(localFile);
              paramAnonymousPackageStats.externalDataSize = localDeviceStorageManager.a(localDeviceStorageManager.c(paramString));
            }
          }
          paramStub.onGetStatsCompleted(paramAnonymousPackageStats, paramAnonymousBoolean);
        }
      } });
      return;
    }
    catch (Exception paramString)
    {
      throw new PackageManagerException(paramString.getMessage(), paramString);
    }
  }
  
  private File i(String paramString)
    throws PackageManagerException
  {
    PackageManager localPackageManager = this.c;
    try
    {
      paramString = new File(localPackageManager.getApplicationInfo(paramString, 0).publicSourceDir);
      if (!paramString.exists()) {
        throw new PackageManagerException("APK was not found");
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      DebugLog.f("DevicePackageManager.getApkStorePath() - failed");
      throw new PackageManagerException("APK was not found");
    }
    return paramString;
  }
  
  public IApkFile a(File paramFile)
    throws InvalidApkFileException
  {
    return h(paramFile.getAbsolutePath());
  }
  
  public CharSequence a(String paramString)
  {
    try
    {
      Object localObject = b(paramString);
      localObject = this.b.getPackageManager().getApplicationLabel((ApplicationInfo)localObject);
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      DebugLog.b("DevicePackageManager.getApplicationName(" + paramString + ") failed");
      return "";
    }
    catch (Exception localException)
    {
      DebugLog.c("DevicePackageManager.getApplicationName(" + paramString + ") failed", localException);
    }
    return "";
  }
  
  public List<String> a(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.c.getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ((!a(localApplicationInfo)) || (paramBoolean)) {
        localArrayList.add(localApplicationInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public Set<String> a()
  {
    HashSet localHashSet = new HashSet();
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    localObject = this.c.queryIntentActivities((Intent)localObject, 0).iterator();
    while (((Iterator)localObject).hasNext()) {
      localHashSet.add(((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName);
    }
    return localHashSet;
  }
  
  public void a(Activity paramActivity, String paramString)
  {
    try
    {
      IntentUtils.b(paramActivity, paramString);
      return;
    }
    catch (ActivityNotFoundException paramActivity) {}
  }
  
  public void a(String paramString, IPackageStatsObserver.Stub paramStub)
    throws PackageManagerException
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      b(paramString, paramStub);
      return;
    }
    c(paramString, paramStub);
  }
  
  public boolean a(ApplicationInfo paramApplicationInfo)
  {
    return ((paramApplicationInfo.flags & 0x1) != 0) || (a.contains(paramApplicationInfo.packageName));
  }
  
  public ApplicationInfo b(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.c.getApplicationInfo(paramString, 0);
  }
  
  public Set<String> b()
  {
    HashSet localHashSet = new HashSet();
    Object localObject = new Intent("android.view.InputMethod");
    localObject = this.c.queryIntentServices((Intent)localObject, 0).iterator();
    while (((Iterator)localObject).hasNext()) {
      localHashSet.add(((ResolveInfo)((Iterator)localObject).next()).serviceInfo.packageName);
    }
    return localHashSet;
  }
  
  public PackageInfo c(String paramString)
    throws PackageManagerException
  {
    PackageManager localPackageManager = this.b.getPackageManager();
    try
    {
      paramString = localPackageManager.getPackageInfo(paramString, 0);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      throw new PackageManagerException(paramString.getMessage(), paramString);
    }
  }
  
  public String c()
  {
    try
    {
      String str = android.provider.Settings.Secure.getString(this.b.getContentResolver(), "default_input_method").split("/")[0];
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public List<ApplicationInfo> d()
  {
    return this.b.getPackageManager().getInstalledApplications(0);
  }
  
  public boolean d(String paramString)
    throws PackageManagerException
  {
    if (a.contains(paramString)) {
      return true;
    }
    try
    {
      paramString = b(paramString);
      return a(paramString);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      throw new PackageManagerException(paramString.getMessage(), paramString);
    }
  }
  
  public List<ActivityInfo> e()
  {
    for (;;)
    {
      try
      {
        if (this.f != null)
        {
          localObject1 = this.f;
          return localObject1;
        }
        this.f = new LinkedList();
        Object localObject1 = new Intent("android.intent.action.MAIN");
        ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
        localObject1 = this.b.getPackageManager().queryIntentActivities((Intent)localObject1, 0).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject1).next();
          if (this.f.contains(localResolveInfo.activityInfo)) {
            continue;
          }
          this.f.add(localResolveInfo.activityInfo);
          continue;
        }
        Collections.sort(this.f, new PackageItemInfo.DisplayNameComparator(this.b.getPackageManager()));
      }
      finally {}
      LinkedList localLinkedList = this.f;
    }
  }
  
  public void e(String paramString)
  {
    this.e.killBackgroundProcesses(paramString);
  }
  
  public List<ApplicationInfo> f()
  {
    List localList = d();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext()) {
      if (a((ApplicationInfo)localIterator.next())) {
        localIterator.remove();
      }
    }
    return localList;
  }
  
  public boolean f(String paramString)
  {
    PackageManager localPackageManager = this.b.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      DebugLog.b("DevicePackageManager.isPackageInstalled(" + paramString + ") - package not found.");
      return false;
    }
    catch (Exception paramString)
    {
      DebugLog.c("DevicePackageManager.isPackageInstalled() - failed.", paramString);
    }
    return false;
  }
  
  public Drawable g(String paramString)
    throws PackageManagerException
  {
    try
    {
      paramString = this.c.getApplicationIcon(paramString);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      throw new PackageManagerException(paramString.getMessage(), paramString);
    }
  }
  
  public IApkFile h(String paramString)
    throws InvalidApkFileException
  {
    return new ApkFile(paramString, this.c);
  }
}
