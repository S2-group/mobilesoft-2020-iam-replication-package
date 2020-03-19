package com.superpro.WJ.Ox;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.superpro.WJ.Aw;
import com.umeng.statistics.StatisticsUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class WJ
{
  private static String Aw;
  private static Map<String, WJ> WJ = new TreeMap();
  private static long xf;
  
  public static String Aw()
  {
    return Aw;
  }
  
  /* Error */
  private static void Aw(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 33	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   7: istore_1
    //   8: iload_1
    //   9: ifeq +7 -> 16
    //   12: ldc 2
    //   14: monitorexit
    //   15: return
    //   16: getstatic 22	com/superpro/WJ/Ox/WJ:WJ	Ljava/util/Map;
    //   19: aload_0
    //   20: invokeinterface 39 2 0
    //   25: ifeq -13 -> 12
    //   28: getstatic 22	com/superpro/WJ/Ox/WJ:WJ	Ljava/util/Map;
    //   31: aload_0
    //   32: invokeinterface 43 2 0
    //   37: pop
    //   38: lconst_0
    //   39: putstatic 45	com/superpro/WJ/Ox/WJ:xf	J
    //   42: aconst_null
    //   43: putstatic 26	com/superpro/WJ/Ox/WJ:Aw	Ljava/lang/String;
    //   46: invokestatic 48	com/superpro/WJ/Ox/WJ:ZC	()V
    //   49: goto -37 -> 12
    //   52: astore_0
    //   53: ldc 2
    //   55: monitorexit
    //   56: aload_0
    //   57: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	paramString	String
    //   7	2	1	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	8	52	finally
    //   16	49	52	finally
  }
  
  private static WJ WJ(ApplicationInfo paramApplicationInfo)
  {
    int j = 1;
    if (paramApplicationInfo == null) {}
    String str;
    Object localObject1;
    do
    {
      do
      {
        do
        {
          return null;
        } while ((paramApplicationInfo.flags & 0x1) > 0);
        str = paramApplicationInfo.packageName;
      } while ((TextUtils.isEmpty(str)) || (TextUtils.equals(str, com.ox.component.WJ.Aw())));
      localObject1 = com.ox.component.WJ.WJ().getPackageManager();
    } while (localObject1 == null);
    Object localObject2 = new String[2];
    localObject2[0] = "com.b1.b2.b3.ui.activity.InterstitialActivity";
    localObject2[1] = "com.superpro.commercialize.batmobi.ui.activity.InterstitialActivity";
    int k = localObject2.length;
    int i = 0;
    label231:
    for (;;)
    {
      Object localObject3;
      if (i < k) {
        localObject3 = new ComponentName(str, localObject2[i]);
      }
      try
      {
        localObject3 = ((PackageManager)localObject1).getActivityInfo((ComponentName)localObject3, 0);
        if (localObject3 == null) {
          break label231;
        }
        i = j;
      }
      catch (RuntimeException localRuntimeException)
      {
        for (;;)
        {
          try
          {
            if (paramApplicationInfo.metaData != null)
            {
              paramApplicationInfo = paramApplicationInfo.metaData.getString("id", "");
              localObject2 = new WJ();
              localObject1 = ((PackageManager)localObject1).getPackageInfo(str, 0);
              ((WJ)localObject2).WJ = str;
              ((WJ)localObject2).Aw = paramApplicationInfo;
              ((WJ)localObject2).ZC = ((PackageInfo)localObject1).firstInstallTime;
              ((WJ)localObject2).bW = ((PackageInfo)localObject1).lastUpdateTime;
              ((WJ)localObject2).xf = ((PackageInfo)localObject1).versionCode;
              return localObject2;
            }
            paramApplicationInfo = "";
            continue;
            localRuntimeException = localRuntimeException;
          }
          catch (Exception paramApplicationInfo)
          {
            return null;
          }
          catch (RuntimeException paramApplicationInfo)
          {
            return null;
          }
          catch (PackageManager.NameNotFoundException paramApplicationInfo)
          {
            return null;
          }
          i = 0;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        i += 1;
      }
      if (i == 0) {
        break;
      }
    }
  }
  
  private static String WJ(Collection<String> paramCollection, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramCollection = paramCollection.iterator();
    int i = 0;
    while (paramCollection.hasNext())
    {
      String str = (String)paramCollection.next();
      if (i > 0) {
        localStringBuilder.append(paramString);
      }
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static void WJ()
  {
    Object localObject = com.ox.component.WJ.WJ().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledApplications(128);
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        WJ(WJ((ApplicationInfo)((Iterator)localObject).next()));
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private static void WJ(WJ paramWJ)
  {
    if (paramWJ == null) {
      return;
    }
    for (;;)
    {
      try
      {
        if (TextUtils.equals(paramWJ.WJ, com.ox.component.WJ.Aw())) {
          break;
        }
        if (WJ.containsKey(paramWJ.WJ))
        {
          ((WJ)WJ.get(paramWJ.WJ)).WJ(paramWJ);
          ZC();
          break;
        }
      }
      finally {}
      WJ.put(paramWJ.WJ, paramWJ);
    }
  }
  
  public static void WJ(String paramString, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString)) {}
    PackageManager localPackageManager;
    do
    {
      return;
      if (paramBoolean)
      {
        Aw(paramString);
        return;
      }
      localPackageManager = com.ox.component.WJ.WJ().getPackageManager();
    } while (localPackageManager == null);
    try
    {
      WJ(WJ(localPackageManager.getApplicationInfo(paramString, 128)));
      return;
    }
    catch (PackageManager.NameNotFoundException paramString) {}catch (Exception paramString) {}catch (RuntimeException paramString) {}
  }
  
  public static boolean WJ(String paramString)
  {
    return WJ.containsKey(paramString);
  }
  
  private static void ZC()
  {
    if (xf == 0L) {
      Aw = com.ox.component.WJ.Aw();
    }
    try
    {
      xf = com.ox.component.WJ.WJ().getPackageManager().getPackageInfo(Aw, 0).firstInstallTime;
      Iterator localIterator = WJ.values().iterator();
      while (localIterator.hasNext())
      {
        WJ localWJ = (WJ)localIterator.next();
        if (xf > localWJ.ZC)
        {
          xf = localWJ.ZC;
          Aw = localWJ.WJ;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        xf = Aw.WJ().Ox();
      }
    }
  }
  
  public static void xf()
  {
    if (WJ.size() == 0) {
      return;
    }
    Object localObject1 = new TreeSet(WJ.keySet());
    ArrayList localArrayList = new ArrayList(((Set)localObject1).size() * 2);
    Iterator localIterator = ((Set)localObject1).iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject2 = (WJ)WJ.get(str);
      if (localObject2 != null)
      {
        str = ((WJ)localObject2).WJ;
        localObject2 = String.format(Locale.ENGLISH, "%s_%d", new Object[] { ((WJ)localObject2).Aw, Integer.valueOf(((WJ)localObject2).xf) });
        if ((!TextUtils.isEmpty(str)) && (!TextUtils.isEmpty((CharSequence)localObject2)))
        {
          localArrayList.add(str);
          localArrayList.add(localObject2);
        }
      }
    }
    int i = ((Set)localObject1).size();
    localObject1 = WJ((Collection)localObject1, "_");
    localArrayList.add("pkgs");
    localArrayList.add(localObject1);
    if ((i >= 1) && (i <= 3))
    {
      StatisticsUtils.statisics("offline", "brothers_" + i, (String[])localArrayList.toArray(new String[0]));
      return;
    }
    StatisticsUtils.statisics("offline", "brothers_+", (String[])localArrayList.toArray(new String[0]));
  }
  
  public static class WJ
  {
    String Aw;
    String WJ;
    long ZC;
    long bW;
    int xf;
    
    public WJ() {}
    
    public boolean WJ(WJ paramWJ)
    {
      if ((paramWJ == null) || (!TextUtils.equals(this.WJ, paramWJ.WJ))) {
        return false;
      }
      this.Aw = paramWJ.Aw;
      this.xf = paramWJ.xf;
      this.ZC = paramWJ.ZC;
      this.bW = paramWJ.bW;
      return true;
    }
  }
}
