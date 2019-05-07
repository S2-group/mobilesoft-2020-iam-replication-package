package com.uc.e.a.h;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import com.uc.e.a.f.a;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class d
{
  private static List<PackageInfo> bCl;
  private static final Object bCm = new Object();
  private static a cTJ = new a((byte)0);
  private static d cTK;
  
  public d() {}
  
  public static void Cn()
  {
    PackageManager localPackageManager = i.RL().getPackageManager();
    try
    {
      synchronized (bCm)
      {
        bCl = localPackageManager.getInstalledPackages(0);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  public static d RM()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 58	com/uc/e/a/h/d:cTK	Lcom/uc/e/a/h/d;
    //   6: ifnonnull +32 -> 38
    //   9: new 2	com/uc/e/a/h/d
    //   12: dup
    //   13: invokespecial 59	com/uc/e/a/h/d:<init>	()V
    //   16: putstatic 58	com/uc/e/a/h/d:cTK	Lcom/uc/e/a/h/d;
    //   19: invokestatic 61	com/uc/e/a/h/d:Cn	()V
    //   22: invokestatic 40	com/uc/e/a/h/i:RL	()Landroid/content/Context;
    //   25: astore_0
    //   26: getstatic 26	com/uc/e/a/h/d:cTJ	Lcom/uc/e/a/h/d$a;
    //   29: astore_1
    //   30: aload_0
    //   31: ifnull +7 -> 38
    //   34: aload_1
    //   35: ifnonnull +12 -> 47
    //   38: getstatic 58	com/uc/e/a/h/d:cTK	Lcom/uc/e/a/h/d;
    //   41: astore_0
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload_0
    //   46: areturn
    //   47: new 63	android/content/IntentFilter
    //   50: dup
    //   51: invokespecial 64	android/content/IntentFilter:<init>	()V
    //   54: astore_2
    //   55: aload_2
    //   56: ldc 66
    //   58: invokevirtual 70	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   61: aload_2
    //   62: ldc 72
    //   64: invokevirtual 70	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   67: aload_2
    //   68: ldc 74
    //   70: invokevirtual 77	android/content/IntentFilter:addDataScheme	(Ljava/lang/String;)V
    //   73: aload_0
    //   74: aload_1
    //   75: aload_2
    //   76: invokevirtual 81	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   79: pop
    //   80: goto -42 -> 38
    //   83: astore_0
    //   84: ldc 2
    //   86: monitorexit
    //   87: aload_0
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   25	49	0	localObject1	Object
    //   83	5	0	localObject2	Object
    //   29	46	1	localA	a
    //   54	22	2	localIntentFilter	android.content.IntentFilter
    // Exception table:
    //   from	to	target	type
    //   3	30	83	finally
    //   38	42	83	finally
    //   47	80	83	finally
  }
  
  public static List<PackageInfo> RN()
  {
    for (;;)
    {
      synchronized (bCm)
      {
        if (bCl != null)
        {
          i = bCl.size();
          ArrayList localArrayList = new ArrayList(i);
          if (bCl == null) {
            break;
          }
          Iterator localIterator = bCl.iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          if (localPackageInfo == null) {
            continue;
          }
          localArrayList.add(localPackageInfo);
        }
      }
      int i = 0;
    }
    return localList;
  }
  
  public static int RO()
  {
    ApplicationInfo localApplicationInfo = i.RL().getApplicationInfo();
    if ((localApplicationInfo.flags & 0x40000) == 0) {
      return 0;
    }
    if (localApplicationInfo.dataDir.startsWith(Environment.getDataDirectory().getAbsolutePath())) {
      return 1;
    }
    if (localApplicationInfo.dataDir.startsWith("/mnt/expand/")) {
      return 2;
    }
    return 3;
  }
  
  public static long getFirstInstallTime()
  {
    PackageInfo localPackageInfo = hp(i.RL().getPackageName());
    if (localPackageInfo == null) {
      return -1L;
    }
    return localPackageInfo.firstInstallTime;
  }
  
  public static PackageInfo getPackageInfo(String paramString, int paramInt)
  {
    if (paramInt == 0) {
      return hp(paramString);
    }
    try
    {
      paramString = i.RL().getPackageManager().getPackageInfo(paramString, paramInt);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  public static int getVersionCode()
  {
    PackageInfo localPackageInfo = hp(i.RL().getPackageName());
    if (localPackageInfo == null) {
      throw new PackageManager.NameNotFoundException();
    }
    return localPackageInfo.versionCode;
  }
  
  public static PackageInfo hp(String paramString)
  {
    if ((paramString == null) || (bCl == null)) {
      return null;
    }
    Object localObject = bCm;
    int i = 0;
    for (;;)
    {
      try
      {
        if (i >= bCl.size()) {
          break;
        }
        PackageInfo localPackageInfo = (PackageInfo)bCl.get(i);
        if (paramString.equals(localPackageInfo.packageName)) {
          return localPackageInfo;
        }
      }
      finally {}
      i += 1;
    }
    return null;
  }
  
  public static boolean nR(String paramString)
  {
    return hp(paramString) != null;
  }
  
  public static boolean nS(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString.trim()))) {}
    for (;;)
    {
      return false;
      try
      {
        paramString = new File(paramString);
        if (paramString.exists())
        {
          Intent localIntent = new Intent();
          localIntent.addFlags(268435456);
          localIntent.addFlags(134217728);
          localIntent.setAction("android.intent.action.VIEW");
          localIntent.setDataAndType(Uri.fromFile(paramString), "application/vnd.android.package-archive");
          i.RL().startActivity(localIntent);
          return true;
        }
      }
      catch (Exception paramString) {}
    }
    return false;
  }
  
  public static boolean nT(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString.trim()))) {
      return false;
    }
    try
    {
      paramString = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString));
      paramString.setFlags(268435456);
      i.RL().startActivity(paramString);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private static final class a
    extends BroadcastReceiver
  {
    private a() {}
    
    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramIntent.getAction().equals("android.intent.action.PACKAGE_ADDED")) || (paramIntent.getAction().equals("android.intent.action.PACKAGE_REMOVED"))) {
        a.execute(new Runnable()
        {
          public final void run() {}
        });
      }
    }
  }
}
