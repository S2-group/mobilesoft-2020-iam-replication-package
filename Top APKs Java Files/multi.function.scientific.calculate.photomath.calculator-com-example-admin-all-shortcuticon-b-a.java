package com.example.admin.all.shortcuticon.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a
{
  private static List<PackageInfo> a;
  
  public static List<List<c>> getHorizontalGroups(Context paramContext)
  {
    Object localObject1 = getVerticalGroups(paramContext);
    paramContext = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = ((b)((Iterator)localObject1).next()).c.iterator();
      while (((Iterator)localObject2).hasNext()) {
        paramContext.add((c)((Iterator)localObject2).next());
      }
    }
    localObject1 = new ArrayList();
    paramContext = paramContext.iterator();
    int i = 0;
    while (paramContext.hasNext())
    {
      localObject2 = (c)paramContext.next();
      if (i % 24 == 0) {
        ((List)localObject1).add(new ArrayList());
      }
      ((List)((List)localObject1).get(((List)localObject1).size() - 1)).add(localObject2);
      i += 1;
    }
    return localObject1;
  }
  
  public static List<b> getVerticalGroups(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    label308:
    label357:
    label456:
    for (;;)
    {
      HashMap localHashMap;
      Object localObject2;
      c localC;
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        a = localPackageManager.getInstalledPackages(1);
        localHashMap = new HashMap();
        Iterator localIterator = a.iterator();
        if (!localIterator.hasNext()) {
          break label357;
        }
        localObject1 = (PackageInfo)localIterator.next();
        if ((((PackageInfo)localObject1).activities == null) || (((PackageInfo)localObject1).activities.length == 0) || ((((PackageInfo)localObject1).applicationInfo.flags & 0x1) > 0)) {
          continue;
        }
        localObject2 = ((PackageInfo)localObject1).packageName;
        localC = new c();
        localC.c = ((String)localObject2);
        localC.a = com.example.admin.all.shortcuticon.c.a.getNameByPackage(paramContext, (String)localObject2);
        localC.d = com.example.admin.all.shortcuticon.c.a.getLaunchIntentForPackage(localPackageManager, (String)localObject2);
        if (localC.d == null) {
          continue;
        }
        localC.b = localPackageManager.getApplicationIcon(((PackageInfo)localObject1).applicationInfo);
        localObject1 = "-";
        if (localC.a.length() != 0) {
          localObject1 = localC.a.substring(0, 1);
        }
        localObject2 = Pattern.compile("^[A-Za-z]+$").matcher((CharSequence)localObject1);
        localObject1 = Pattern.compile("^[0-9]+$").matcher((CharSequence)localObject1);
        if (((Matcher)localObject2).matches())
        {
          localObject1 = localC.a.trim().substring(0, 1).toUpperCase();
          if (!TextUtils.isEmpty((CharSequence)localObject1)) {
            break label456;
          }
          localObject1 = "...";
          List localList = (List)localHashMap.get(localObject1);
          localObject2 = localList;
          if (localList == null)
          {
            localObject2 = new ArrayList();
            localHashMap.put(localObject1, localObject2);
          }
          ((List)localObject2).add(localC);
          continue;
        }
        if (!((Matcher)localObject1).matches()) {
          break label308;
        }
      }
      catch (Exception paramContext)
      {
        Log.e("data_error", paramContext.toString());
        return localArrayList;
      }
      Object localObject1 = "...";
      continue;
      localObject1 = new d().toPinYin(localC.a.trim().replaceAll(" ", ""), "", d.a.a).replaceAll(" ", "").substring(0, 1).toUpperCase();
      continue;
      paramContext = localHashMap.keySet().iterator();
      while (paramContext.hasNext())
      {
        localObject2 = (String)paramContext.next();
        localObject1 = new b();
        ((b)localObject1).a = ((String)localObject2);
        localObject2 = (List)localHashMap.get(localObject2);
        ((b)localObject1).b = ((List)localObject2).size();
        ((b)localObject1).c = ((List)localObject2);
        localArrayList.add(localObject1);
      }
      Collections.sort(localArrayList, new Comparator()
      {
        public int compare(b paramAnonymousB1, b paramAnonymousB2)
        {
          return paramAnonymousB1.a.compareTo(paramAnonymousB2.a);
        }
      });
      return localArrayList;
    }
  }
  
  public static List<c> search(String paramString, List<b> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Iterator localIterator = ((b)paramList.next()).c.iterator();
      while (localIterator.hasNext())
      {
        c localC = (c)localIterator.next();
        if (localC.a.toLowerCase().contains(paramString.toLowerCase()))
        {
          localArrayList.add(localC);
        }
        else
        {
          d localD = new d();
          try
          {
            if (localD.toPinYin(localC.a, "", d.a.a).toUpperCase().contains(paramString.toUpperCase())) {
              localArrayList.add(localC);
            }
          }
          catch (b.a.a.a.a.a localA)
          {
            localA.printStackTrace();
          }
        }
      }
    }
    return localArrayList;
  }
}
