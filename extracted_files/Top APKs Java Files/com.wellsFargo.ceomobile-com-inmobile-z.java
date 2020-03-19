package com.inmobile;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class z
{
  private static String M()
  {
    return "5.0.1";
  }
  
  static String N()
  {
    try
    {
      Object localObject = Q();
      localObject = new JsonParser().parse((String)localObject).getAsJsonObject().get("version");
      if (localObject != null) {
        return ((JsonElement)localObject).getAsString();
      }
      return "MISSING_SIGFILE";
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "MISSING_SIGFILE";
  }
  
  private static String Q()
  {
    byte[] arrayOfByte = aa.l("a1b2c3");
    if ((arrayOfByte != null) && (arrayOfByte.length != 0)) {
      return new String(arrayOfByte);
    }
    throw new Exception("MISSING_ROOT_SIGNATURES");
  }
  
  private static List<String> a(Application paramApplication, HashSet<String> paramHashSet)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramApplication = paramApplication.getApplicationContext().getPackageManager();
      int i = 0;
      paramApplication = paramApplication.getInstalledPackages(0);
      while (i < paramApplication.size())
      {
        String str = ((PackageInfo)paramApplication.get(i)).packageName;
        if (paramHashSet.contains(str)) {
          localArrayList.add(str);
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception paramApplication) {}
    return localArrayList;
  }
  
  protected static List<Map<String, Object>> a(Application paramApplication, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramApplication = b(paramApplication, paramBoolean);
    }
    catch (Exception paramApplication)
    {
      paramApplication = paramApplication.getMessage();
      if (paramApplication.equals("MISSING_ROOT_SIGNATURES")) {
        paramApplication = new RootLog("COMPROMISED", "MISSING_SIGFILE", null);
      } else if (paramApplication.equals("SIGFILE_CORRUPT")) {
        paramApplication = new RootLog("COMPROMISED", "DECRYPTION_FAILURE", null);
      } else {
        paramApplication = new RootLog();
      }
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("root_status_code", paramApplication.getRootStatus());
    localHashMap.put("sdk_version", M());
    localHashMap.put("root_status_reason_code", paramApplication.getRootReasonCode());
    localHashMap.put("root_version", M());
    localHashMap.put("sigfile_version", N());
    localHashMap.put("root_list", paramApplication.getRootList());
    localArrayList.add(localHashMap);
    return localArrayList;
  }
  
  static RootLog b(Application paramApplication, boolean paramBoolean)
  {
    Object localObject1 = Q();
    try
    {
      localObject2 = new JsonParser().parse((String)localObject1).getAsJsonObject().get("entry").getAsJsonArray();
      k = 0;
      localObject3 = ((JsonArray)localObject2).get(0).getAsJsonObject().get("su").getAsJsonArray();
      localObject1 = ((JsonArray)localObject2).get(1).getAsJsonObject().get("hiders").getAsJsonArray();
      localJsonArray = ((JsonArray)localObject2).get(2).getAsJsonObject().get("related_apps").getAsJsonArray();
      j = ((JsonArray)localObject3).size();
      n = ((JsonArray)localObject1).size();
      m = localJsonArray.size();
      localObject2 = new HashSet(j + m);
      localHashSet = new HashSet(n);
      i = 0;
    }
    catch (Exception paramApplication)
    {
      for (;;)
      {
        Object localObject2;
        int k;
        Object localObject3;
        JsonArray localJsonArray;
        int j;
        int n;
        int m;
        HashSet localHashSet;
        Iterator localIterator;
        label426:
        continue;
        i += 1;
        continue;
        int i = 0;
        continue;
        i += 1;
        continue;
        if (paramBoolean)
        {
          paramApplication = "DEVICE_ROOTED";
          continue;
          paramApplication = "DEVICE_NOT_ROOTED";
        }
      }
    }
    if (i < j)
    {
      localIterator = ((JsonArray)localObject3).get(i).getAsJsonObject().entrySet().iterator();
      while (localIterator.hasNext()) {
        ((HashSet)localObject2).add(((Map.Entry)localIterator.next()).getKey());
      }
      j = k;
      if (i < n)
      {
        localObject3 = ((JsonArray)localObject1).get(i).getAsJsonObject().entrySet().iterator();
        while (((Iterator)localObject3).hasNext()) {
          localHashSet.add(((Map.Entry)((Iterator)localObject3).next()).getKey());
        }
      }
      while (j < m)
      {
        localObject1 = localJsonArray.get(j).getAsJsonObject().entrySet().iterator();
        while (((Iterator)localObject1).hasNext()) {
          ((HashSet)localObject2).add(((Map.Entry)((Iterator)localObject1).next()).getKey());
        }
        j += 1;
      }
      localObject1 = null;
      try
      {
        localObject2 = a(paramApplication, (HashSet)localObject2);
        paramApplication = a(paramApplication, localHashSet);
        ((List)localObject2).addAll(paramApplication);
        localObject1 = localObject2;
      }
      catch (Exception paramApplication)
      {
        for (;;) {}
      }
      paramApplication = null;
      try
      {
        paramBoolean = ad.d(paramBoolean);
        if ((!paramBoolean) || (paramApplication == null) || (paramApplication.isEmpty())) {
          break label496;
        }
        paramApplication = "DEVICE_ROOTED_WITH_CLOAK";
        break label426;
        if ((paramApplication != null) && (!paramApplication.isEmpty()))
        {
          paramApplication = "ROOT_CLOAKER_DETECTED";
        }
        else
        {
          if ((localObject1 == null) || (((List)localObject1).isEmpty())) {
            break label506;
          }
          paramApplication = "ROOT_APPLICATIONS_INSTALLED";
        }
        localObject2 = "NO_ERROR";
      }
      catch (Exception paramApplication)
      {
        for (;;) {}
      }
      paramApplication = "COMPROMISED";
      localObject2 = "INTERNAL_ERROR";
      paramApplication = new RootLog(paramApplication, (String)localObject2, (List)localObject1);
      return paramApplication;
      throw new Exception("SIGFILE_CORRUPT");
    }
  }
}
