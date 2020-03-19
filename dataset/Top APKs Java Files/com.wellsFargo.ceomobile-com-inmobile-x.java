package com.inmobile;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipFile;

class x
{
  static MalwareLog F(Application paramApplication)
  {
    localObject1 = O();
    try
    {
      localObject1 = new JsonParser().parse((String)localObject1).getAsJsonObject();
      localObject2 = ((JsonObject)localObject1).get("version");
      if (localObject2 == null) {
        break label457;
      }
      if (Double.parseDouble(((JsonElement)localObject2).getAsString()) < 3.0D) {
        throw new Exception("SIGFILE_OUT_OF_DATE");
      }
      localObject1 = ((JsonObject)localObject1).get("entry");
      if (localObject1 == null) {
        break label447;
      }
      localJsonArray = ((JsonElement)localObject1).getAsJsonArray();
      localObject1 = null;
      localObject2 = localObject1;
      localObject3 = localObject2;
      i = 0;
    }
    catch (Exception paramApplication)
    {
      for (;;)
      {
        JsonArray localJsonArray;
        int i;
        JsonElement localJsonElement;
        Object localObject6;
        int j;
        label447:
        label457:
        continue;
        j += 1;
        continue;
        continue;
        j += 1;
        continue;
        Object localObject5 = localObject2;
        Object localObject4 = localObject1;
        i += 1;
        localObject1 = localObject4;
        Object localObject2 = localObject5;
        Object localObject3 = localObject6;
      }
    }
    if (i < localJsonArray.size())
    {
      localJsonElement = localJsonArray.get(i);
      localObject4 = localObject1;
      localObject5 = localObject2;
      localObject6 = localObject3;
      if (localJsonElement == null) {
        break label505;
      }
      localObject4 = localJsonElement.getAsJsonObject().get("apps");
      if (localObject4 != null)
      {
        localObject4 = ((JsonElement)localObject4).getAsJsonArray();
        localObject2 = new HashSet(((JsonArray)localObject4).size());
        localObject1 = new HashSet(((JsonArray)localObject4).size());
        j = 0;
        if (j < ((JsonArray)localObject4).size())
        {
          localObject5 = ((JsonArray)localObject4).get(j).getAsJsonObject().entrySet().iterator();
          while (((Iterator)localObject5).hasNext())
          {
            localObject6 = (Map.Entry)((Iterator)localObject5).next();
            ((Set)localObject1).add(((Map.Entry)localObject6).getKey());
            ((Set)localObject2).add(((JsonElement)((Map.Entry)localObject6).getValue()).getAsString());
          }
        }
      }
      else
      {
        localJsonElement = localJsonElement.getAsJsonObject().get("dexApps");
        localObject4 = localObject1;
        localObject5 = localObject2;
        localObject6 = localObject3;
        if (localJsonElement == null) {
          break label505;
        }
        localObject3 = localJsonElement.getAsJsonArray();
        localObject6 = new HashSet(((JsonArray)localObject3).size());
        j = 0;
        if (j >= ((JsonArray)localObject3).size()) {
          break label498;
        }
        localObject4 = ((JsonArray)localObject3).get(j).getAsJsonObject().entrySet().iterator();
        while (((Iterator)localObject4).hasNext()) {
          ((Set)localObject6).add(((JsonElement)((Map.Entry)((Iterator)localObject4).next()).getValue()).getAsString());
        }
      }
    }
    else
    {
      if ((localObject1 != null) && (localObject2 != null) && (localObject3 != null))
      {
        paramApplication = a(paramApplication, (Set)localObject1, (Set)localObject2, (Set)localObject3);
        if (paramApplication.isEmpty()) {
          return new MalwareLog("NO_MALWARE_FOUND", "NO_ERROR", null);
        }
        return new MalwareLog("MALWARE_FOUND", "NO_ERROR", paramApplication);
      }
      return new MalwareLog();
      throw new Exception("SIGFILE_CORRUPT");
      throw new Exception("SIGFILE_CORRUPT");
      throw new Exception("SIGFILE_CORRUPT");
    }
  }
  
  private static String M()
  {
    return "5.1.0";
  }
  
  static String N()
  {
    try
    {
      Object localObject = O();
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
  
  private static String O()
  {
    byte[] arrayOfByte = aa.l("123abc");
    if ((arrayOfByte != null) && (arrayOfByte.length != 0)) {
      return new String(arrayOfByte);
    }
    throw new Exception("MISSING_MALWARE_SIGNATURES");
  }
  
  private static List<String> a(Application paramApplication, Set<String> paramSet1, Set<String> paramSet2, Set<String> paramSet3)
  {
    localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        paramApplication = paramApplication.getApplicationContext().getPackageManager();
        i = 0;
        paramApplication = paramApplication.getInstalledPackages(0);
        if (i < paramApplication.size())
        {
          str = ((PackageInfo)paramApplication.get(i)).packageName;
          localObject2 = ((PackageInfo)paramApplication.get(i)).applicationInfo.sourceDir;
          if (paramSet1.contains(str))
          {
            localObject1 = MMEUtilities.fileToMD5((String)localObject2);
            if (paramSet2.contains(localObject1))
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append(str);
              ((StringBuilder)localObject2).append(";");
              ((StringBuilder)localObject2).append((String)localObject1);
              localArrayList.add(((StringBuilder)localObject2).toString());
            }
          }
        }
      }
      catch (Exception paramApplication)
      {
        int i;
        String str;
        Object localObject2;
        Object localObject1;
        return localArrayList;
      }
      try
      {
        localObject1 = new ZipFile(new File((String)localObject2));
        localObject2 = MMEUtilities.streamToSHA256(((ZipFile)localObject1).getInputStream(((ZipFile)localObject1).getEntry("classes.dex")));
        if (paramSet3.contains(localObject2))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(str);
          localStringBuilder.append(";");
          localStringBuilder.append((String)localObject2);
          localArrayList.add(localStringBuilder.toString());
        }
        ((ZipFile)localObject1).close();
      }
      catch (Exception localException)
      {
        continue;
      }
      i += 1;
    }
    return localArrayList;
  }
  
  protected static List<Map<String, String>> m(Application paramApplication)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramApplication = F(paramApplication);
    }
    catch (Exception paramApplication)
    {
      paramApplication = paramApplication.getMessage();
      if (paramApplication.equals("MISSING_MALWARE_SIGNATURES")) {
        paramApplication = new MalwareLog("COMPROMISED", "MISSING_SIGFILE", null);
      } else if (paramApplication.equals("SIGFILE_OUT_OF_DATE")) {
        paramApplication = new MalwareLog("COMPROMISED", "OUTDATED_SIGFILE", null);
      } else if (paramApplication.equals("SIGFILE_CORRUPT")) {
        paramApplication = new MalwareLog("COMPROMISED", "DECRYPTION_FAILURE", null);
      } else {
        paramApplication = new MalwareLog();
      }
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("found_malware", paramApplication.getMalwareStatus());
    localHashMap.put("reason_code", paramApplication.getMalwareReasonCode());
    localHashMap.put("malware_sdk_version", M());
    localHashMap.put("sigfile_version", N());
    localHashMap.put("package_list", paramApplication.getMalwareList().toString());
    localArrayList.add(localHashMap);
    return localArrayList;
  }
}
