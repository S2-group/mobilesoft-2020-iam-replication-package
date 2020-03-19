package com.mdg.root.utilities;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.mdg.utilities.abc.c;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class a
{
  private static a ˊ;
  
  private a() {}
  
  public static a ˊ()
  {
    if (ˊ == null) {
      ˊ = new a();
    }
    return ˊ;
  }
  
  public static com.mdg.utilities.abc.a ˊ(String paramString1, String paramString2)
  {
    String str2 = "COMPROMISED";
    int i = 0;
    String str1 = str2;
    if (paramString1 != null)
    {
      paramString1 = c.ˊ().ˊ(paramString1);
      int j = c.ˊ().af2();
      str1 = str2;
      i = j;
      if (paramString1 != null) {
        if ("GET_SIGFILE_VERSION".equals(paramString2))
        {
          str1 = paramString1.split("#####")[0].split("(\\r|\\n)")[0];
          i = j;
        }
        else
        {
          str1 = str2;
          i = j;
          if ("GET_ROOT_LIST".equals(paramString2))
          {
            str1 = paramString1;
            i = j;
          }
        }
      }
    }
    paramString1 = new com.mdg.utilities.abc.a();
    paramString1.ˊ = str1;
    paramString1.ˋ = com.mdg.utilities.abc.a.ˊ(i);
    return paramString1;
  }
  
  public static List<String> ˊ(Application paramApplication, String[] paramArrayOfString)
  {
    paramApplication = paramApplication.getApplicationContext().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      int j = 0;
      int k = paramApplication.size();
      while (j < k)
      {
        if (((PackageInfo)paramApplication.get(j)).packageName.equals(paramArrayOfString[i])) {
          localArrayList.add(paramArrayOfString[i]);
        }
        j += 1;
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static boolean ˊ(Application paramApplication, List<String> paramList)
  {
    boolean bool1 = false;
    paramApplication = ((ActivityManager)paramApplication.getSystemService("activity")).getRunningAppProcesses();
    int i = 1;
    int k = paramList.size();
    boolean bool3;
    for (;;)
    {
      bool3 = bool1;
      if (i >= k) {
        break;
      }
      String str = (String)paramList.get(i);
      int j = 0;
      boolean bool2;
      for (;;)
      {
        bool2 = bool1;
        if (j >= paramApplication.size()) {
          break;
        }
        if (((ActivityManager.RunningAppProcessInfo)paramApplication.get(j)).processName.equals(str))
        {
          bool2 = true;
          break;
        }
        j += 1;
      }
      bool3 = bool2;
      if (bool2) {
        break;
      }
      i += 1;
      bool1 = bool2;
    }
    return bool3;
  }
  
  public static boolean ˊ(String[] paramArrayOfString)
  {
    int i = 1;
    while (i < paramArrayOfString.length)
    {
      File localFile = new File(paramArrayOfString[i]);
      if ((!localFile.exists()) && ((localFile.list() != null) && (localFile.canRead()) && (localFile.canWrite()))) {
        break;
      }
      i += 1;
    }
    return false;
  }
}
