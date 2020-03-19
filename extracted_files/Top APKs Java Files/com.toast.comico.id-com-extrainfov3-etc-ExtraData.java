package com.extrainfov3.etc;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.soft.Dc;
import android.support.v4.soft.OriginalApplication;
import com.nhnent.appguard.AppGuard.IOnLoadInformation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ExtraData
{
  static IOnLoadPackageInfo A;
  static Map<String, String> F;
  private static double I = 20.0D;
  private static int J;
  static Map<String, String> L = new HashMap();
  public static boolean adinfoNo;
  private static int d;
  private static double i = 0.1D;
  static Map<String, String> l = new HashMap();
  
  static
  {
    A = null;
    adinfoNo = false;
    F = new HashMap();
    J = 0;
    d = 0;
  }
  
  public ExtraData() {}
  
  protected static void D(IOnLoadPackageInfo paramIOnLoadPackageInfo)
  {
    F(paramIOnLoadPackageInfo);
    new M(null).start();
  }
  
  protected static String F(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledApplications(128);
    J = ((List)localObject1).size();
    F(paramContext);
    String str = new String();
    Iterator localIterator = ((List)localObject1).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      localObject2 = localApplicationInfo.packageName;
      paramContext = new SimpleDateFormat(OriginalApplication.F("D\nD\np>Y\027u;P\036N\000"));
      Date localDate = F(localPackageManager, (String)localObject2);
      localObject1 = "";
      if (localDate != null) {
        localObject1 = paramContext.format(localDate);
      }
      paramContext = (Context)localObject2;
      if (localApplicationInfo.packageName.length() > 128)
      {
        paramContext = ((String)localObject2).substring(0, 128);
        paramContext = Dc.F("\000\034m\025");
      }
      localObject2 = OriginalApplication.F("I");
      localObject1 = (String)localObject1;
      localObject2 = Dc.F("\027");
      localObject1 = localObject2;
      if (L.containsKey(localApplicationInfo.packageName) == true) {
        localObject1 = (String)L.get(localApplicationInfo.packageName);
      }
      localObject2 = OriginalApplication.F("I");
      localObject1 = localObject2;
      if (l.containsKey(localApplicationInfo.packageName) == true) {
        localObject1 = (String)l.get(localApplicationInfo.packageName);
      }
      localObject1 = Dc.F("\027");
      F.put(paramContext, localObject1);
      getpackageSize(localPackageManager, localApplicationInfo.packageName);
    }
    while ((d < J) && (I > 0.0D))
    {
      I -= i;
      SystemClock.sleep((i * 1000.0D));
    }
    Object localObject2 = F.keySet().iterator();
    paramContext = str;
    for (;;)
    {
      localObject1 = paramContext;
      if (((Iterator)localObject2).hasNext())
      {
        localObject1 = (String)((Iterator)localObject2).next();
        paramContext = (String)localObject1 + (String)F.get(localObject1) + OriginalApplication.F("_");
        if (paramContext.length() > 39800) {
          localObject1 = Dc.F("\036]/\030o\035");
        }
      }
      else
      {
        return localObject1;
      }
    }
  }
  
  protected static String F(String paramString)
    throws IOException
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localBufferedReader = new BufferedReader(new FileReader(paramString));
      String str;
      if (localObject == null) {
        break label94;
      }
    }
    finally
    {
      try
      {
        paramString = localBufferedReader.readLine();
        localObject = "";
        for (str = paramString; paramString != null; str = paramString)
        {
          localStringBuilder.append((String)localObject).append(str);
          localObject = Dc.F("'");
          paramString = localBufferedReader.readLine();
        }
        paramString = localStringBuilder.toString();
        if (localBufferedReader != null) {
          localBufferedReader.close();
        }
        return paramString;
      }
      finally
      {
        for (;;)
        {
          BufferedReader localBufferedReader;
          Object localObject = localBufferedReader;
        }
      }
      paramString = finally;
      localObject = null;
    }
    ((BufferedReader)localObject).close();
    label94:
    throw paramString;
  }
  
  protected static void F(Context paramContext)
  {
    if (Build.VERSION.SDK_INT <= 21)
    {
      paramContext = ((ActivityManager)paramContext.getSystemService(OriginalApplication.F("\\\020I\032K\032I\n"))).getRunningAppProcesses();
      if (paramContext != null) {}
    }
    for (;;)
    {
      return;
      Object localObject1 = paramContext.iterator();
      Object localObject2;
      SimpleDateFormat localSimpleDateFormat;
      long l1;
      long l2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
        localSimpleDateFormat = new SimpleDateFormat(Dc.F("&T&T\022`;I\027e2@,^"));
        l1 = F(((ActivityManager.RunningAppProcessInfo)localObject2).pid);
        l2 = SystemClock.elapsedRealtime();
        paramContext = "";
        if (l1 != 0L) {
          paramContext = localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis() - (l2 - l1)));
        }
        L.put(localObject2.pkgList[0], paramContext);
        l1 = D(((ActivityManager.RunningAppProcessInfo)localObject2).pid);
        l.put(localObject2.pkgList[0], Long.toString(l1));
      }
      continue;
      localObject1 = new File(OriginalApplication.F("\\M\001R\020")).listFiles();
      int m = localObject1.length;
      int k = 0;
      for (int j = 0; k < m; j = k)
      {
        paramContext = localObject1[j];
        if (paramContext.isDirectory()) {}
        for (;;)
        {
          try
          {
            k = Integer.parseInt(paramContext.getName());
          }
          catch (NumberFormatException paramContext)
          {
            continue;
          }
          try
          {
            localObject2 = F(String.format(Dc.F("p]-B<\002zIpN2I3D1H"), new Object[] { Integer.valueOf(k) })).trim();
            if ((!((String)localObject2).isEmpty()) && (((String)localObject2).indexOf('/') == -1) && (((String)localObject2).indexOf(':') == -1) && (((String)localObject2).indexOf('.') != -1))
            {
              localSimpleDateFormat = new SimpleDateFormat(OriginalApplication.F("D\nD\np>Y\027u;P\036N\000"));
              l1 = F(k);
              l2 = SystemClock.elapsedRealtime();
              paramContext = "";
              if (l1 != 0L) {
                paramContext = localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis() - (l2 - l1)));
              }
              L.put(localObject2, paramContext);
              l1 = D(k);
              l.put(localObject2, Long.toString(l1));
            }
          }
          catch (IOException paramContext) {}
        }
        k = j + 1;
      }
    }
  }
  
  protected static void F(Context paramContext, AppGuard.IOnLoadInformation paramIOnLoadInformation, String paramString, boolean paramBoolean)
  {
    if (paramIOnLoadInformation == null) {
      return;
    }
    paramIOnLoadInformation.onComplete(paramString, new SimpleDateFormat(Dc.F("T&T&\000\022`rI;\r\027ee@2\027,^"), Locale.US).format(new Date()), F(paramContext));
  }
  
  protected static void F(IOnLoadPackageInfo paramIOnLoadPackageInfo)
  {
    if (A != paramIOnLoadPackageInfo) {
      A = null;
    }
    A = paramIOnLoadPackageInfo;
  }
  
  public static void collect(Context paramContext, AppGuard.IOnLoadInformation paramIOnLoadInformation)
  {
    boolean bool = false;
    try
    {
      Class.forName(OriginalApplication.F("\020R\036\023\024R\034Z\037X]\\\035Y\001R\032Y]Z\036N]\\\027N]T\027X\035I\032[\032X\001\0232Y\005X\001I\032N\032S\024t\027~\037T\026S\007"));
      bool = true;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    F(bool, paramContext, paramIOnLoadInformation);
  }
  
  public static void getpackageSize(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager.getClass().getMethod(Dc.F("8H+}>N4L8H\fD%H\026C9B"), new Class[] { String.class, IPackageStatsObserver.class }).invoke(paramPackageManager, new Object[] { paramString, new g(null) });
      return;
    }
    catch (Exception paramPackageManager) {}catch (InvocationTargetException paramPackageManager) {}catch (IllegalAccessException paramPackageManager) {}catch (IllegalArgumentException paramPackageManager) {}catch (NoSuchMethodException paramPackageManager) {}catch (SecurityException paramPackageManager) {}
  }
  
  protected static abstract interface IOnLoadPackageInfo
  {
    public abstract void onComplete(String paramString, boolean paramBoolean);
  }
}
