package com.mdg.mw.utilities;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.mdg.utilities.FdmgUtilities;
import com.mdg.utilities.abc.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a
{
  private static a ˊ;
  
  private a() {}
  
  private static int ˊ(String[] paramArrayOfString, String paramString, int paramInt1, int paramInt2)
  {
    int i;
    for (;;)
    {
      if (paramInt2 < paramInt1) {
        return -1;
      }
      i = (paramInt1 + paramInt2) / 2;
      String[] arrayOfString = paramArrayOfString[i].split(";");
      if (arrayOfString[0].compareTo(paramString) > 0)
      {
        paramInt2 = i - 1;
      }
      else
      {
        if (arrayOfString[0].compareTo(paramString) >= 0) {
          break;
        }
        paramInt1 = i + 1;
      }
    }
    return i;
  }
  
  public static a ˊ()
  {
    if (ˊ == null) {
      ˊ = new a();
    }
    return ˊ;
  }
  
  public static com.mdg.utilities.abc.a ˊ(String paramString1, String paramString2)
  {
    Object localObject2 = "COMPROMISED";
    int i = 0;
    Object localObject1 = localObject2;
    if (paramString1 != null)
    {
      String str = c.ˊ().ˋ(paramString1);
      int j = c.ˊ().af3();
      localObject1 = localObject2;
      i = j;
      if (str != null)
      {
        paramString1 = "";
        localObject1 = str.split("#####");
        localObject2 = localObject1[0].split("(\\r|\\n)");
        if ("GET_SIGFILE_VERSION".equals(paramString2)) {
          paramString1 = localObject2[0];
        }
        if ("GET_MALWARE_LIST".equals(paramString2)) {
          paramString1 = localObject1[1];
        }
        i = j;
        localObject1 = paramString1;
      }
    }
    paramString1 = new com.mdg.utilities.abc.a();
    paramString1.ˊ = ((String)localObject1);
    paramString1.ˋ = com.mdg.utilities.abc.a.ˊ(i);
    return paramString1;
  }
  
  public static List<String> ˊ(Application paramApplication, String paramString)
  {
    paramString = paramString.split("(\\r|\\n)");
    List localList = paramApplication.getApplicationContext().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    int k = 0;
    while (k < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(k);
      int i = ˊ(paramString, localPackageInfo.packageName, 0, paramString.length - 1);
      if (i != -1)
      {
        int j;
        for (paramApplication = paramString[(i - 1)].split(";");; paramApplication = paramString[(i - 1)].split(";"))
        {
          j = i;
          if (!localPackageInfo.packageName.equals(paramApplication[0])) {
            break;
          }
          i -= 1;
        }
        for (;;)
        {
          paramApplication = paramString[j].split(";");
          if (!localPackageInfo.packageName.equals(paramApplication[0])) {
            break;
          }
          String str = localPackageInfo.applicationInfo.sourceDir;
          if (localHashMap.containsKey(paramApplication[0])) {
            break;
          }
          str = FdmgUtilities.ˋ(str);
          if (str == null)
          {
            j += 1;
          }
          else
          {
            if (str.equals(paramApplication[1]))
            {
              localArrayList.add(paramString[j]);
              localHashMap.put(paramApplication[0], paramApplication[1]);
              break;
            }
            j += 1;
          }
        }
      }
      k += 1;
    }
    return localArrayList;
  }
  
  public static List<String> ˋ(Application paramApplication, String paramString)
  {
    paramString = paramString.split("(\\r|\\n)");
    paramApplication = paramApplication.getApplicationContext().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    int i = 1;
    while (i < paramString.length)
    {
      String[] arrayOfString = paramString[i].split(";");
      int j = 0;
      while (j < paramApplication.size())
      {
        Object localObject = (PackageInfo)paramApplication.get(j);
        if (((PackageInfo)localObject).packageName.equals(arrayOfString[0]))
        {
          localObject = ((PackageInfo)localObject).applicationInfo.sourceDir;
          if (!localHashMap.containsKey(arrayOfString[0]))
          {
            localObject = FdmgUtilities.ˋ((String)localObject);
            if ((localObject != null) && (((String)localObject).equals(arrayOfString[1])))
            {
              localArrayList.add(paramString[i]);
              localHashMap.put(arrayOfString[0], arrayOfString[1]);
            }
          }
        }
        j += 1;
      }
      i += 1;
    }
    return localArrayList;
  }
}
