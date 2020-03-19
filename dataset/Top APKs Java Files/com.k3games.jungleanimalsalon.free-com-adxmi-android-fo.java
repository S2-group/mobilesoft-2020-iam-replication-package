package com.adxmi.android;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class fo
{
  private static List jo;
  private static List jp;
  
  public static List a(Context paramContext, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = aQ(paramContext);
    try
    {
      Object localObject5;
      if (Build.VERSION.SDK_INT < 21)
      {
        localObject2 = paramContext.getPackageManager();
        localObject3 = ((ActivityManager)paramContext.getSystemService("activity")).getRecentTasks(paramInt, 2);
        paramContext = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME").resolveActivityInfo((PackageManager)localObject2, 0);
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (ActivityManager.RecentTaskInfo)((Iterator)localObject3).next();
          localObject5 = new Intent(((ActivityManager.RecentTaskInfo)localObject4).baseIntent);
          if (((ActivityManager.RecentTaskInfo)localObject4).origActivity != null) {
            ((Intent)localObject5).setComponent(((ActivityManager.RecentTaskInfo)localObject4).origActivity);
          }
          if ((paramContext == null) || (!paramContext.packageName.equals(((Intent)localObject5).getComponent().getPackageName())) || (!paramContext.name.equals(((Intent)localObject5).getComponent().getClassName())))
          {
            ((Intent)localObject5).setFlags(((Intent)localObject5).getFlags() & 0xFFDFFFFF | 0x10000000);
            localObject4 = ((PackageManager)localObject2).resolveActivity((Intent)localObject5, 0);
            if ((localObject4 != null) && (!((List)localObject1).contains(((ResolveInfo)localObject4).activityInfo.packageName))) {
              localArrayList.add(((ResolveInfo)localObject4).activityInfo.packageName);
            }
          }
        }
      }
      long l = System.currentTimeMillis();
      Object localObject2 = Class.forName("android.app.usage.UsageStatsManager");
      paramContext = paramContext.getSystemService("usagestats");
      Object localObject4 = (List)((Class)localObject2).getMethod("queryUsageStats", new Class[] { Integer.TYPE, Long.TYPE, Long.TYPE }).invoke(paramContext, new Object[] { Integer.valueOf(4), Long.valueOf(l - 1800000L), Long.valueOf(l) });
      if ((localObject4 == null) || (((List)localObject4).isEmpty())) {
        break label584;
      }
      localObject2 = Class.forName("android.app.usage.UsageStats");
      paramContext = ((Class)localObject2).getMethod("getLastTimeUsed", new Class[0]);
      Object localObject3 = ((Class)localObject2).getMethod("getPackageName", new Class[0]);
      localObject2 = new HashMap();
      localObject4 = ((List)localObject4).iterator();
      while (((Iterator)localObject4).hasNext())
      {
        Object localObject6 = ((Iterator)localObject4).next();
        if (localObject6 != null) {
          try
          {
            localObject5 = (String)((Method)localObject3).invoke(localObject6, new Object[0]);
            if ((!be.I((String)localObject5)) && (!((List)localObject1).contains(localObject5)))
            {
              localObject6 = (Long)paramContext.invoke(localObject6, new Object[0]);
              if (localObject6 != null) {
                ((HashMap)localObject2).put(localObject6, localObject5);
              }
            }
          }
          catch (Throwable localThrowable) {}
        }
      }
      paramContext = new TreeMap(new Comparator()
      {
        public int a(Long paramAnonymousLong1, Long paramAnonymousLong2)
        {
          return paramAnonymousLong2.compareTo(paramAnonymousLong1);
        }
      });
      paramContext.putAll((Map)localObject2);
      paramContext = paramContext.entrySet().iterator();
      while (paramContext.hasNext())
      {
        localObject1 = (Map.Entry)paramContext.next();
        if (localArrayList.size() < paramInt) {
          localArrayList.add(((Map.Entry)localObject1).getValue());
        }
      }
      return localArrayList;
    }
    catch (Throwable paramContext)
    {
      return localArrayList;
    }
    return localArrayList;
    label584:
    return localArrayList;
  }
  
  public static List aP(Context paramContext)
  {
    if ((jo == null) || (jo.isEmpty())) {
      try
      {
        paramContext = paramContext.getPackageManager();
        Object localObject = paramContext.getInstalledApplications(8192);
        Collections.sort((List)localObject, new ApplicationInfo.DisplayNameComparator(paramContext));
        jo = new ArrayList();
        paramContext = ((List)localObject).iterator();
        while (paramContext.hasNext())
        {
          localObject = (ApplicationInfo)paramContext.next();
          if ((((ApplicationInfo)localObject).flags & 0x1) != 0) {
            jo.add(Integer.valueOf(((ApplicationInfo)localObject).uid));
          }
        }
        return jo;
      }
      catch (Throwable paramContext)
      {
        jo = Collections.emptyList();
      }
    }
  }
  
  public static List aQ(Context paramContext)
  {
    if ((jp == null) || (jp.isEmpty())) {
      try
      {
        paramContext = paramContext.getPackageManager();
        Object localObject = paramContext.getInstalledApplications(8192);
        Collections.sort((List)localObject, new ApplicationInfo.DisplayNameComparator(paramContext));
        jp = new ArrayList();
        paramContext = ((List)localObject).iterator();
        while (paramContext.hasNext())
        {
          localObject = (ApplicationInfo)paramContext.next();
          if ((((ApplicationInfo)localObject).flags & 0x1) != 0) {
            jp.add(((ApplicationInfo)localObject).packageName);
          }
        }
        return jp;
      }
      catch (Throwable paramContext)
      {
        jp = Collections.emptyList();
      }
    }
  }
  
  public static List aR(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = aQ(paramContext);
    File[] arrayOfFile = new File("/proc").listFiles();
    int n = arrayOfFile.length;
    int i = 0;
    Object localObject1;
    for (;;)
    {
      if (i >= n) {
        break label392;
      }
      localObject1 = arrayOfFile[i];
      if (((File)localObject1).isDirectory()) {
        break;
      }
      i += 1;
    }
    for (;;)
    {
      int j;
      try
      {
        int k = Integer.parseInt(((File)localObject1).getName());
        try
        {
          localObject1 = aW(String.format("/proc/%d/cgroup", new Object[] { Integer.valueOf(k) }));
          if (be.I((String)localObject1)) {
            break;
          }
          Object localObject3 = ((String)localObject1).split("\n");
          int m = localObject3.length;
          j = 0;
          if (j >= m) {
            break label400;
          }
          localObject1 = localObject3[j];
          if (((String)localObject1).contains("cpuacct"))
          {
            if ((!be.I((String)localObject1)) && (!((String)localObject1).endsWith(Integer.toString(k)))) {
              break;
            }
            localObject1 = aW(String.format("/proc/%d/cmdline", new Object[] { Integer.valueOf(k) })).trim();
            if ((((String)localObject1).contains("com.android.systemui")) || (((String)localObject1).contains("libairDog.so")) || (((String)localObject1).contains("/")) || (((String)localObject1).contains(":"))) {
              break;
            }
            m = -1;
            localObject3 = aW(String.format("/proc/%d/status", new Object[] { Integer.valueOf(k) })).split("\n");
            int i1 = localObject3.length;
            j = 0;
            k = m;
            if (j < i1)
            {
              Object localObject4 = localObject3[j];
              if (!localObject4.startsWith("Uid:")) {
                continue;
              }
              localObject3 = localObject4.substring(4).trim();
              k = Integer.parseInt(((String)localObject3).substring(0, ((String)localObject3).indexOf("\t")));
            }
            if ((k < 10000) || (aP(paramContext).contains(Integer.valueOf(k))) || (localList.contains(localObject1))) {
              break;
            }
            localArrayList.add(localObject1);
          }
        }
        catch (Throwable localThrowable) {}
      }
      catch (NumberFormatException localNumberFormatException) {}
      j += 1;
      continue;
      j += 1;
      continue;
      label392:
      return localArrayList;
      break;
      label400:
      Object localObject2 = null;
    }
  }
  
  private static String aW(String paramString)
  {
    return e(new File(paramString));
  }
  
  /* Error */
  private static String e(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnull +106 -> 109
    //   6: aload_0
    //   7: invokevirtual 370	java/io/File:exists	()Z
    //   10: ifeq +99 -> 109
    //   13: aload_0
    //   14: invokevirtual 373	java/io/File:isFile	()Z
    //   17: ifeq +92 -> 109
    //   20: aload_0
    //   21: invokevirtual 376	java/io/File:canRead	()Z
    //   24: ifeq +85 -> 109
    //   27: new 378	java/io/BufferedReader
    //   30: dup
    //   31: new 380	java/io/FileReader
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 383	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   39: invokespecial 386	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   42: astore_0
    //   43: new 388	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 389	java/lang/StringBuilder:<init>	()V
    //   50: astore_3
    //   51: aload_3
    //   52: aload_0
    //   53: invokevirtual 392	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   56: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload_0
    //   61: invokevirtual 392	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   64: astore_1
    //   65: aload_1
    //   66: ifnull +22 -> 88
    //   69: aload_3
    //   70: bipush 10
    //   72: invokevirtual 399	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   75: aload_1
    //   76: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload_0
    //   81: invokevirtual 392	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   84: astore_1
    //   85: goto -20 -> 65
    //   88: aload_3
    //   89: invokevirtual 401	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: astore_1
    //   93: aload_1
    //   94: astore_2
    //   95: aload_2
    //   96: astore_1
    //   97: aload_0
    //   98: ifnull +9 -> 107
    //   101: aload_0
    //   102: invokevirtual 404	java/io/BufferedReader:close	()V
    //   105: aload_2
    //   106: astore_1
    //   107: aload_1
    //   108: areturn
    //   109: aload_2
    //   110: astore_1
    //   111: iconst_0
    //   112: ifeq -5 -> 107
    //   115: new 406	java/lang/NullPointerException
    //   118: dup
    //   119: invokespecial 407	java/lang/NullPointerException:<init>	()V
    //   122: athrow
    //   123: astore_0
    //   124: aconst_null
    //   125: areturn
    //   126: astore_0
    //   127: aconst_null
    //   128: astore_0
    //   129: aload_2
    //   130: astore_1
    //   131: aload_0
    //   132: ifnull -25 -> 107
    //   135: aload_0
    //   136: invokevirtual 404	java/io/BufferedReader:close	()V
    //   139: aconst_null
    //   140: areturn
    //   141: astore_0
    //   142: aconst_null
    //   143: areturn
    //   144: astore_1
    //   145: aconst_null
    //   146: astore_0
    //   147: aload_0
    //   148: ifnull +7 -> 155
    //   151: aload_0
    //   152: invokevirtual 404	java/io/BufferedReader:close	()V
    //   155: aload_1
    //   156: athrow
    //   157: astore_0
    //   158: aload_2
    //   159: areturn
    //   160: astore_0
    //   161: goto -6 -> 155
    //   164: astore_1
    //   165: goto -18 -> 147
    //   168: astore_1
    //   169: goto -40 -> 129
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	172	0	paramFile	File
    //   64	67	1	localObject1	Object
    //   144	12	1	localObject2	Object
    //   164	1	1	localObject3	Object
    //   168	1	1	localThrowable	Throwable
    //   1	158	2	localObject4	Object
    //   50	39	3	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   115	123	123	java/lang/Throwable
    //   6	43	126	java/lang/Throwable
    //   135	139	141	java/lang/Throwable
    //   6	43	144	finally
    //   101	105	157	java/lang/Throwable
    //   151	155	160	java/lang/Throwable
    //   43	65	164	finally
    //   69	85	164	finally
    //   88	93	164	finally
    //   43	65	168	java/lang/Throwable
    //   69	85	168	java/lang/Throwable
    //   88	93	168	java/lang/Throwable
  }
}
