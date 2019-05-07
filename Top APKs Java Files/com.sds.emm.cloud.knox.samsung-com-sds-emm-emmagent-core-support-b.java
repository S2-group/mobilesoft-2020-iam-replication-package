package com.sds.emm.emmagent.core.support;

import AGENT.ck.g;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.sds.emm.emmagent.core.support.android.n;
import com.sds.emm.emmagent.core.support.log.c;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class b
{
  public static PackageInfo a(int paramInt, String paramString)
  {
    Object localObject1;
    try
    {
      String[] arrayOfString = AGENT.cn.b.a(paramInt);
    }
    catch (Throwable localThrowable)
    {
      c.d(localThrowable);
      localObject1 = null;
    }
    paramInt = 0;
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = new String[0];
    }
    int i = localObject2.length;
    while (paramInt < i)
    {
      if (localObject2[paramInt].equals(paramString)) {
        try
        {
          paramString = n.d().getPackageInfo(paramString, 8192);
          return paramString;
        }
        catch (Throwable paramString)
        {
          c.b(paramString);
        }
      }
      paramInt += 1;
    }
    return null;
  }
  
  public static PackageInfo a(String paramString)
  {
    return a(paramString, 0);
  }
  
  public static PackageInfo a(String paramString, int paramInt)
  {
    try
    {
      paramString = n.d().getPackageInfo(paramString, paramInt);
      return paramString;
    }
    catch (Throwable paramString)
    {
      c.b(paramString);
    }
    return null;
  }
  
  public static com.sds.emm.emmagent.service.general.inventory.app.d a(PackageInfo paramPackageInfo)
  {
    Object localObject = null;
    com.sds.emm.emmagent.service.general.inventory.app.d localD = null;
    if (paramPackageInfo != null)
    {
      if ((paramPackageInfo.applicationInfo.flags & 0x1) != 0) {
        localD = com.sds.emm.emmagent.service.general.inventory.app.d.PRELOAD_APP;
      }
      if ((paramPackageInfo.applicationInfo.flags & 0x80) != 0) {
        localD = com.sds.emm.emmagent.service.general.inventory.app.d.PRELOAD_UPDATED_APP;
      }
      localObject = localD;
      if (localD == null) {
        localObject = com.sds.emm.emmagent.service.general.inventory.app.d.THIRD_PARTY_APP;
      }
    }
    return localObject;
  }
  
  public static List<PackageInfo> a()
  {
    return n.d().getInstalledPackages(0);
  }
  
  public static List<PackageInfo> a(int paramInt)
  {
    if (com.sds.emm.emmagent.core.support.android.a.d())
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject2 = AGENT.cn.b.a(paramInt);
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new String[0];
      }
      localObject2 = c().iterator();
      label106:
      while (((Iterator)localObject2).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
        int i = localObject1.length;
        paramInt = 0;
        for (;;)
        {
          if (paramInt >= i) {
            break label106;
          }
          Object localObject3 = localObject1[paramInt];
          if (localPackageInfo.packageName.equals(localObject3))
          {
            localArrayList.add(localPackageInfo);
            break;
          }
          paramInt += 1;
        }
      }
      return localArrayList;
    }
    return a();
  }
  
  public static void a(Context paramContext, final String paramString)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.MAIN");
      localIntent.addFlags(268435456);
      localIntent.addCategory("android.intent.category.HOME");
      paramContext.startActivity(localIntent);
    }
    catch (Throwable localThrowable)
    {
      c.c(localThrowable);
    }
    new AGENT.cr.a(new Runnable()
    {
      public void run()
      {
        ActivityManager localActivityManager = (ActivityManager)this.a.getSystemService("activity");
        Iterator localIterator = localActivityManager.getRunningAppProcesses().iterator();
        while (localIterator.hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
          if ((localRunningAppProcessInfo.processName != null) && (localRunningAppProcessInfo.processName.equals(paramString)))
          {
            if (localRunningAppProcessInfo.importance >= 400)
            {
              localActivityManager.killBackgroundProcesses(paramString);
              return;
            }
            Thread.yield();
          }
        }
      }
    }).a("KillProcess");
  }
  
  public static boolean b()
  {
    boolean bool = false;
    try
    {
      int i = Settings.Secure.getInt(com.sds.emm.emmagent.core.support.android.d.a().getContentResolver(), "install_non_market_apps");
      if (i == 1) {
        bool = true;
      }
      return bool;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      c.c(localSettingNotFoundException);
    }
    return false;
  }
  
  public static boolean b(int paramInt, String paramString)
  {
    return a(paramInt, paramString) != null;
  }
  
  public static boolean b(String paramString)
  {
    return a(paramString) != null;
  }
  
  public static String c(String paramString)
  {
    try
    {
      paramString = n.d().getInstallerPackageName(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      c.c(paramString);
    }
    return null;
  }
  
  private static List<PackageInfo> c()
  {
    return n.d().getInstalledPackages(8192);
  }
  
  public static boolean d(String paramString)
  {
    try
    {
      Object localObject = Class.forName("android.app.ActivityManagerNative");
      localObject = Class.forName("android.app.IActivityManager").cast(((Class)localObject).getMethod("getDefault", new Class[0]).invoke(localObject, new Object[0]));
      localObject.getClass().getMethod("forceStopPackage", new Class[] { String.class }).invoke(localObject, new Object[] { paramString });
      return true;
    }
    catch (Throwable paramString)
    {
      c.c(paramString);
    }
    return false;
  }
  
  @Deprecated
  public static String e(String paramString)
  {
    localStringBuilder = new StringBuilder();
    try
    {
      paramString = n.d().getPackageInfo(paramString, 64);
      if ((paramString != null) && (paramString.signatures != null))
      {
        paramString = paramString.signatures;
        int j = paramString.length;
        int i = 0;
        while (i < j)
        {
          localStringBuilder.append(paramString[i].toCharsString());
          i += 1;
        }
      }
      return localStringBuilder.toString();
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      c.b(paramString);
    }
  }
  
  public static String f(String paramString)
  {
    localStringBuilder = new StringBuilder();
    try
    {
      paramString = n.d().getPackageInfo(paramString, 64);
      if ((paramString != null) && (paramString.signatures != null))
      {
        Signature[] arrayOfSignature = paramString.signatures;
        int j = arrayOfSignature.length;
        int i = 0;
        while (i < j)
        {
          paramString = new ByteArrayInputStream(arrayOfSignature[i].toByteArray());
          try
          {
            paramString = (X509Certificate)CertificateFactory.getInstance("X509").generateCertificate(paramString);
            MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
            localMessageDigest.update(paramString.getEncoded());
            localStringBuilder.append(AGENT.ci.b.a(localMessageDigest.digest()));
          }
          catch (NoSuchAlgorithmException paramString)
          {
            c.b(paramString);
          }
          catch (CertificateException paramString)
          {
            for (;;) {}
          }
          i += 1;
        }
      }
      return localStringBuilder.toString();
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      c.b(paramString);
    }
  }
  
  public static boolean g(String paramString)
  {
    return e("android").equals(e(paramString));
  }
  
  public static boolean h(String paramString)
  {
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    PackageManager localPackageManager = n.d();
    boolean bool2 = false;
    localObject = localPackageManager.queryIntentActivities((Intent)localObject, 0);
    boolean bool1 = bool2;
    if (!f.a((List)localObject))
    {
      localObject = ((List)localObject).iterator();
      do
      {
        bool1 = bool2;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!g.a(paramString, ((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName));
      bool1 = true;
    }
    return bool1;
  }
}
