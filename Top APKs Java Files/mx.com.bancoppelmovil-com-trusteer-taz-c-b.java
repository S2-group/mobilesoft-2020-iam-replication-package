package com.trusteer.taz.c;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public final class b
{
  public static final String a = "TAZ";
  public static final int b = 10000;
  public static final int c = 100000;
  private static int d = 1;
  private static int e = 2;
  private static int f = 3;
  
  public b() {}
  
  public static String a(Context paramContext)
  {
    Object localObject4 = "";
    int i = f;
    Object localObject1 = localObject4;
    int j = i;
    try
    {
      if (Build.VERSION.SDK_INT == 23)
      {
        localObject1 = localObject4;
        j = i;
        String str1 = d(paramContext);
        localObject1 = str1;
        try
        {
          localObject4 = new StringBuilder("API level 23. Foreground package is: ");
          localObject1 = str1;
          ((StringBuilder)localObject4).append(str1);
          localObject1 = str1;
          ((StringBuilder)localObject4).append(". detectionMethod=");
          localObject1 = str1;
          ((StringBuilder)localObject4).append(i);
          localObject1 = str1;
        }
        catch (Exception localException1)
        {
          break label645;
        }
      }
      else
      {
        localObject1 = localObject4;
        j = i;
        if (Build.VERSION.SDK_INT == 21) {
          break label390;
        }
        localObject1 = localObject4;
        j = i;
        if (Build.VERSION.SDK_INT == 22) {
          break label390;
        }
        localObject1 = localObject4;
        j = i;
        if (Build.VERSION.SDK_INT < 21)
        {
          localObject1 = localObject4;
          j = i;
          if (paramContext.checkPermission("android.permission.GET_TASKS", Process.myPid(), Process.myUid()) == 0)
          {
            String str2 = "";
            localObject1 = localObject4;
            j = i;
            List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
            localObject2 = str2;
            if (localList != null)
            {
              localObject2 = str2;
              localObject1 = localObject4;
              j = i;
              if (!localList.isEmpty())
              {
                localObject1 = localObject4;
                j = i;
                localObject2 = ((ActivityManager.RunningTaskInfo)localList.get(0)).topActivity.getPackageName();
              }
            }
            localObject1 = localObject2;
            j = i;
            i = e;
            try
            {
              localObject1 = new StringBuilder("API level below 21 and GET_TASKS permission exists. Foreground package is: ");
              ((StringBuilder)localObject1).append((String)localObject2);
              ((StringBuilder)localObject1).append(". detectionMethod=");
              ((StringBuilder)localObject1).append(i);
              localObject1 = localObject2;
            }
            catch (Exception localException3)
            {
              localObject1 = localObject2;
              localObject2 = localException3;
              break label645;
            }
          }
          localObject1 = localException3;
          j = i;
          localObject2 = d(paramContext);
          localObject1 = localObject2;
          j = f;
          try
          {
            localObject1 = new StringBuilder("API level below 21. Foreground package is: ");
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append(". detectionMethod=");
            ((StringBuilder)localObject1).append(j);
            i = j;
            localObject1 = localObject2;
          }
          catch (Exception localException4)
          {
            i = j;
            localObject1 = localObject2;
            localObject2 = localException4;
            break label645;
          }
        }
        else
        {
          localObject1 = "";
        }
      }
      break label663;
      label390:
      localObject1 = localException4;
      j = i;
      Object localObject2 = b(paramContext);
      localObject1 = localObject2;
      if (!((String)localObject2).equals(""))
      {
        localObject1 = localObject2;
        if (Build.VERSION.RELEASE.equals("5.1.1"))
        {
          localObject1 = localObject2;
          if (paramContext.getPackageName().equals(localObject2)) {}
        }
        else
        {
          localObject1 = localObject2;
          j = d;
          i = j;
          break label547;
        }
      }
      localObject1 = localObject2;
      StringBuilder localStringBuilder = new StringBuilder("API level 21/22. OS version: ");
      localObject1 = localObject2;
      localStringBuilder.append(Build.VERSION.RELEASE);
      localObject1 = localObject2;
      localStringBuilder.append(". Foreground package is: ");
      localObject1 = localObject2;
      localStringBuilder.append((String)localObject2);
      localObject1 = localObject2;
      localStringBuilder.append(". calling getForegroundApp (by proc)");
      localObject1 = localObject2;
      localObject2 = d(paramContext);
      localObject1 = localObject2;
      j = i;
      i = f;
      label547:
      localObject1 = localObject2;
      j = i;
      localStringBuilder = new StringBuilder("API level 21/22. OS version: ");
      localObject1 = localObject2;
      j = i;
      localStringBuilder.append(Build.VERSION.RELEASE);
      localObject1 = localObject2;
      j = i;
      localStringBuilder.append(". Foreground package is: ");
      localObject1 = localObject2;
      j = i;
      localStringBuilder.append((String)localObject2);
      localObject1 = localObject2;
      j = i;
      localStringBuilder.append(". detectionMethod=");
      localObject1 = localObject2;
      j = i;
      localStringBuilder.append(i);
      localObject1 = localObject2;
    }
    catch (Exception localException2)
    {
      i = j;
      label645:
      new StringBuilder("Failed to get foreground package: ").append(localException2.getMessage());
    }
    label663:
    if (!((String)localObject1).equals(""))
    {
      int m = paramContext.getPackageName().equals(localObject1) ^ true;
      paramContext = paramContext.getPackageManager();
      j = m;
      if (m != 0)
      {
        Object localObject3 = new Intent("android.intent.action.MAIN");
        ((Intent)localObject3).addCategory("android.intent.category.HOME");
        localObject3 = paramContext.resolveActivity((Intent)localObject3, 65536);
        j = m;
        if (localObject3 != null) {
          k = ((ResolveInfo)localObject3).activityInfo.packageName.equals(localObject1) ^ true;
        }
      }
      m = k;
      if (k != 0) {
        m = c(paramContext, (String)localObject1) ^ true;
      }
      int k = m;
      if (m != 0) {
        k = d(paramContext, (String)localObject1) ^ true;
      }
      if (k != 0)
      {
        paramContext = new StringBuilder();
        paramContext.append((String)localObject1);
        paramContext.append(":");
        paramContext.append(i);
        return paramContext.toString();
      }
    }
    return "";
  }
  
  private static String a(String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = new BufferedReader(new FileReader(paramString));
    localStringBuilder.append(paramString.readLine());
    for (;;)
    {
      String str = paramString.readLine();
      if (str == null) {
        break;
      }
      localStringBuilder.append('\n');
      localStringBuilder.append(str);
    }
    paramString.close();
    return localStringBuilder.toString();
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    boolean bool2 = paramContext.getPackageName().equals(paramString) ^ true;
    paramContext = paramContext.getPackageManager();
    boolean bool1 = bool2;
    if (bool2)
    {
      Object localObject = new Intent("android.intent.action.MAIN");
      ((Intent)localObject).addCategory("android.intent.category.HOME");
      localObject = paramContext.resolveActivity((Intent)localObject, 65536);
      bool1 = bool2;
      if (localObject != null) {
        bool1 = ((ResolveInfo)localObject).activityInfo.packageName.equals(paramString) ^ true;
      }
    }
    bool2 = bool1;
    if (bool1) {
      bool2 = c(paramContext, paramString) ^ true;
    }
    bool1 = bool2;
    if (bool2) {
      bool1 = d(paramContext, paramString) ^ true;
    }
    return bool1;
  }
  
  private static boolean a(PackageManager paramPackageManager, String paramString)
  {
    Intent localIntent = new Intent("com.android.services.telephony.common.ICallHandlerService");
    boolean bool1 = false;
    paramPackageManager = paramPackageManager.queryIntentServices(localIntent, 0).iterator();
    while (paramPackageManager.hasNext())
    {
      boolean bool2 = paramString.equals(((ResolveInfo)paramPackageManager.next()).serviceInfo.packageName);
      bool1 = bool2;
      if (bool2) {
        bool1 = bool2;
      }
    }
    return bool1;
  }
  
  private static String b(Context paramContext)
  {
    Object localObject = (ActivityManager)paramContext.getSystemService("activity");
    try
    {
      paramContext = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
      localObject = ((ActivityManager)localObject).getRunningAppProcesses().iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        if ((localRunningAppProcessInfo.importance == 100) && (localRunningAppProcessInfo.importanceReasonCode == 0) && (Integer.valueOf(paramContext.getInt(localRunningAppProcessInfo)).intValue() == 2))
        {
          paramContext = localRunningAppProcessInfo.pkgList[0];
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      new StringBuilder("Exception : \n").append(paramContext.getMessage());
    }
    return "";
  }
  
  private static String b(Context paramContext, String paramString)
  {
    String str1 = "";
    String str2 = "";
    Object localObject = paramString;
    if (paramString.startsWith("/data/data/")) {
      localObject = paramString.substring(11);
    }
    int j = ((String)localObject).indexOf('/');
    int i = j;
    if (j == -1) {
      i = ((String)localObject).indexOf(':');
    }
    j = i;
    if (i == -1) {
      j = ((String)localObject).indexOf(0);
    }
    paramString = str2;
    if (j > 0) {
      paramString = ((String)localObject).substring(0, j);
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      localObject = paramContext.getPackageInfo(paramString, 0).packageName;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    localObject = paramContext.getInstalledApplications(0).iterator();
    do
    {
      paramContext = str1;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      paramContext = (ApplicationInfo)((Iterator)localObject).next();
    } while (!paramContext.processName.equals(paramString));
    paramContext = paramContext.packageName;
    return paramContext;
  }
  
  private static boolean b(PackageManager paramPackageManager, String paramString)
  {
    int i = 0;
    boolean bool2 = false;
    boolean bool1;
    for (;;)
    {
      bool1 = bool2;
      if (i >= 2) {
        break;
      }
      String str = new String[] { "android.intent.action.CALL", "android.intent.action.CALL_BUTTON" }[i];
      ResolveInfo localResolveInfo2 = paramPackageManager.resolveActivity(new Intent(str, Uri.parse("tel:555-5555")), 65536);
      ResolveInfo localResolveInfo1 = localResolveInfo2;
      if (localResolveInfo2 == null) {
        localResolveInfo1 = paramPackageManager.resolveActivity(new Intent(str), 65536);
      }
      if (localResolveInfo1 != null)
      {
        bool2 = localResolveInfo1.activityInfo.packageName.equals(paramString);
        bool1 = bool2;
        if (bool2) {
          break;
        }
      }
      bool1 = bool2;
      if (bool2) {
        break;
      }
      i += 1;
    }
    return bool1;
  }
  
  private static String c(Context paramContext)
  {
    String str = "";
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
    paramContext = str;
    if (localList != null)
    {
      paramContext = str;
      if (!localList.isEmpty()) {
        paramContext = ((ActivityManager.RunningTaskInfo)localList.get(0)).topActivity.getPackageName();
      }
    }
    return paramContext;
  }
  
  private static boolean c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      boolean bool = paramContext.getPackageInfo(paramString, 0).applicationInfo.sourceDir.startsWith("/system");
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private static boolean c(PackageManager paramPackageManager, String paramString)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge Z and I\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  private static String d(Context paramContext)
  {
    File[] arrayOfFile = new File("/proc").listFiles();
    int n = arrayOfFile.length;
    j = Integer.MAX_VALUE;
    localObject1 = "";
    int i = 0;
    while (i < n)
    {
      Object localObject5 = arrayOfFile[i];
      Object localObject2 = localObject1;
      k = j;
      if (((File)localObject5).isDirectory()) {}
      try
      {
        i1 = Integer.parseInt(((File)localObject5).getName());
      }
      catch (NumberFormatException|Exception localNumberFormatException)
      {
        for (;;)
        {
          int i1;
          String[] arrayOfString;
          int m;
          label114:
          String str;
          label368:
          boolean bool1;
          boolean bool2;
          Object localObject3 = localObject1;
          k = j;
        }
      }
      try
      {
        localObject2 = a(String.format("/proc/%d/cgroup", new Object[] { Integer.valueOf(i1) }));
        localObject5 = "";
        arrayOfString = ((String)localObject2).split("\n");
        m = arrayOfString.length;
        localObject2 = "";
        k = 0;
      }
      catch (Exception localException)
      {
        break label368;
        k += 1;
        Object localObject4 = localObject6;
        break label114;
      }
      if (k < m)
      {
        str = arrayOfString[k];
        if (str.contains("cpu:"))
        {
          localObject6 = str;
          break label716;
        }
        localObject6 = localObject2;
        if (!str.contains("cpuacct:")) {
          break label716;
        }
        localObject5 = str;
        localObject6 = localObject2;
        break label716;
      }
      if ((Build.VERSION.SDK_INT > 20) && (!((String)localObject5).endsWith(Integer.toString(i1))))
      {
        localObject2 = localObject1;
        k = j;
      }
      else if (((String)localObject2).endsWith("bg_non_interactive"))
      {
        localObject2 = localObject1;
        k = j;
      }
      else
      {
        localObject5 = ((String)localObject5).split(":");
        localObject2 = new String[0];
        if (localObject5.length > 2) {
          localObject2 = localObject5[2].split("/");
        }
        if (localObject2.length > 1)
        {
          if (!localObject2[1].contains("uid_")) {
            localObject2 = localObject2[2];
          } else {
            localObject2 = localObject2[1].replace("uid_", "");
          }
          k = Integer.parseInt((String)localObject2);
        }
        else
        {
          k = 1000;
        }
        if ((k >= 1000) && (k <= 1038))
        {
          localObject2 = localObject1;
          k = j;
        }
        else
        {
          m = k - 10000;
          while (m > 100000) {
            m -= 100000;
          }
          localObject2 = localObject1;
          k = j;
          if (m >= 0)
          {
            localObject5 = a(String.format("/proc/%d/cmdline", new Object[] { Integer.valueOf(i1) }));
            localObject2 = localObject1;
            k = j;
            if (!((String)localObject5).contains("com.android.systemui")) {
              if (((String)localObject5).equals("null"))
              {
                localObject2 = localObject1;
                k = j;
              }
              else
              {
                localObject2 = new File(String.format("/proc/%d/oom_score_adj", new Object[] { Integer.valueOf(i1) }));
                if ((((File)localObject2).canRead()) && (Integer.parseInt(a(((File)localObject2).getAbsolutePath())) != 0))
                {
                  localObject2 = localObject1;
                  k = j;
                }
                else
                {
                  localObject5 = b(paramContext, (String)localObject5);
                  if (((String)localObject5).equals(""))
                  {
                    localObject2 = localObject1;
                    k = j;
                  }
                  else
                  {
                    bool1 = c(paramContext, (String)localObject5);
                    bool2 = d(paramContext, (String)localObject5);
                    m = Integer.parseInt(a(String.format("/proc/%d/oom_score", new Object[] { Integer.valueOf(i1) })));
                    localObject2 = new StringBuilder("Foreground package: oomscore=");
                    ((StringBuilder)localObject2).append(m);
                    ((StringBuilder)localObject2).append(". isSystemPacakge=");
                    ((StringBuilder)localObject2).append(bool1);
                    ((StringBuilder)localObject2).append(". hasUI=");
                    ((StringBuilder)localObject2).append(bool2);
                    ((StringBuilder)localObject2).append(". package=");
                    ((StringBuilder)localObject2).append((String)localObject5);
                    ((StringBuilder)localObject2).append(". pid=");
                    ((StringBuilder)localObject2).append(i1);
                    localObject2 = localObject1;
                    k = j;
                    if (!bool1)
                    {
                      localObject2 = localObject1;
                      k = j;
                      if (bool2)
                      {
                        localObject2 = localObject1;
                        k = j;
                        if (m < j)
                        {
                          k = m;
                          localObject2 = localObject5;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      i += 1;
      localObject1 = localObject2;
      j = k;
    }
    return localObject1;
  }
  
  private static boolean d(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 1);
      if (paramContext.activities != null)
      {
        int i = paramContext.activities.length;
        if (i > 0) {
          return true;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private static boolean d(PackageManager paramPackageManager, String paramString)
  {
    int i = 0;
    while (i < 2)
    {
      Object localObject = new String[] { "smsto:", "sms:" }[i];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(123456);
      localObject = paramPackageManager.resolveActivity(new Intent("android.intent.action.SENDTO", Uri.parse(localStringBuilder.toString())), 65536);
      if ((localObject != null) && (((ResolveInfo)localObject).activityInfo.packageName.equals(paramString))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
}
