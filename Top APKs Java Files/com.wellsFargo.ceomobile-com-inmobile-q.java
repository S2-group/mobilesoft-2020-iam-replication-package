package com.inmobile;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class q
{
  q() {}
  
  private static int a(boolean paramBoolean1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, double paramDouble1, double paramDouble2, boolean paramBoolean2)
  {
    int i = 0;
    double d;
    if (!paramBoolean1) {
      d = paramInt1 + paramInt2 + paramInt3 + paramInt4 + paramInt5;
    }
    try
    {
      Double localDouble = new Double((paramInt2 * paramDouble1 + paramInt3 * paramDouble2) / d * 100.0D);
      if ((localDouble.intValue() <= 100) && (!paramBoolean2))
      {
        paramInt1 = localDouble.intValue();
        return paramInt1;
      }
      i = 100;
      return i;
    }
    catch (ArithmeticException localArithmeticException) {}
    return 0;
  }
  
  private static String a(Application paramApplication, String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt1 >= paramInt2) {}
    try
    {
      paramApplication = c(paramApplication, paramString);
      return paramApplication;
    }
    catch (PackageManager.NameNotFoundException paramApplication)
    {
      for (;;) {}
    }
    return "";
  }
  
  private static boolean a(Application paramApplication, String paramString)
  {
    return (paramApplication.getPackageManager().getApplicationInfo(paramString, 0).flags & 0x1) != 0;
  }
  
  private static boolean a(String[] paramArrayOfString)
  {
    paramArrayOfString = Arrays.asList(paramArrayOfString);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("android.permission.ACCESS_SUPERUSER");
    return paramArrayOfString.containsAll(localArrayList);
  }
  
  private static String b(Application paramApplication, String paramString)
  {
    paramApplication = paramApplication.getPackageManager();
    return (String)paramApplication.getApplicationLabel(paramApplication.getApplicationInfo(paramString, 128));
  }
  
  private static String c(Application paramApplication, String paramString)
  {
    return MMEUtilities.fileToSHA256(paramApplication.getPackageManager().getApplicationInfo(paramString, 0).publicSourceDir);
  }
  
  private static String f(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("PROTECTION_UNKNOWN : ");
      localStringBuilder.append(paramInt);
      return localStringBuilder.toString();
    case 4080: 
      return "PROTECTION_MASK_FLAGS";
    case 2048: 
      return "PROTECTION_FLAG_SETUP";
    case 1218: 
      return "PROTECTION_SYSTEM_LEVEL";
    case 1024: 
      return "PROTECTION_FLAG_PREINSTALLED";
    case 512: 
      return "PROTECTION_FLAG_VERIFIER";
    case 274: 
      return "PROTECTION_SYSTEM_LEVEL";
    case 258: 
      return "PROTECTION_SYSTEM_LEVEL";
    case 256: 
      return "PROTECTION_FLAG_INSTALLER";
    case 128: 
      return "PROTECTION_FLAG_PRE23";
    case 64: 
      return "PROTECTION_FLAG_APPOP";
    case 50: 
      return "PROTECTION_SYSTEM_LEVEL";
    case 32: 
      return "PROTECTION_FLAG_DEVELOPMENT";
    case 18: 
      return "PROTECTION_SYSTEM_LEVEL";
    case 16: 
      return "PROTECTION_FLAG_SYSTEM|PROTECTION_FLAG_PRIVILEGED";
    case 15: 
      return "PROTECTION_MASK_BASE";
    case 3: 
      return "PROTECTION_SIGNATURE_OR_SYSTEM";
    case 2: 
      return "PROTECTION_SIGNATURE";
    case 1: 
      return "PROTECTION_DANGEROUS";
    }
    return "PROTECTION_NORMAL";
  }
  
  private static List<PackageInfo> w(Application paramApplication)
  {
    try
    {
      paramApplication = paramApplication.getPackageManager().getInstalledPackages(4096);
      return paramApplication;
    }
    catch (Exception paramApplication)
    {
      for (;;) {}
    }
    return null;
  }
  
  public List<Map<String, Object>> m(Application paramApplication)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = w(paramApplication);
    if (localList == null) {
      return localArrayList;
    }
    int i = 0;
    while (i < localList.size())
    {
      Object localObject1 = "";
      Object localObject3 = ((PackageInfo)localList.get(i)).requestedPermissions;
      String str = ((PackageInfo)localList.get(i)).packageName;
      if (str.equals("com.android.keyguard")) {
        localObject1 = "Keyguard";
      }
      try
      {
        localObject2 = b(paramApplication, str);
        localObject1 = localObject2;
      }
      catch (Exception localException1)
      {
        Object localObject2;
        for (;;) {}
      }
      if ((localObject3 != null) && (localObject3.length != 0)) {}
      try
      {
        bool1 = a(paramApplication, ((PackageInfo)localList.get(i)).packageName);
      }
      catch (Exception localException2)
      {
        boolean bool1;
        int i4;
        int i3;
        int i2;
        int i1;
        int n;
        int m;
        int k;
        boolean bool2;
        int j;
        for (;;) {}
      }
      bool1 = false;
      i4 = localObject3.length;
      i3 = 0;
      i2 = 0;
      i1 = 0;
      n = 0;
      m = 0;
      k = 0;
      bool2 = false;
      j = i;
      while (i3 < i4)
      {
        localObject2 = localObject3[i3];
        for (;;)
        {
          try
          {
            localPackageManager = paramApplication.getPackageManager();
          }
          catch (Exception localException3)
          {
            PackageManager localPackageManager;
            continue;
          }
          try
          {
            localObject2 = f(localPackageManager.getPermissionInfo((String)localObject2, 0).protectionLevel);
            i = ((String)localObject2).hashCode();
            if (i != -1866999558)
            {
              if (i != -1759193020)
              {
                if (i != -1327959795)
                {
                  if ((i == 1073837758) && (((String)localObject2).equals("PROTECTION_SIGNATURE_OR_SYSTEM")))
                  {
                    i = 3;
                    continue;
                  }
                }
                else if (((String)localObject2).equals("PROTECTION_NORMAL"))
                {
                  i = 0;
                  continue;
                }
              }
              else if (((String)localObject2).equals("PROTECTION_DANGEROUS"))
              {
                i = 1;
                continue;
              }
            }
            else
            {
              boolean bool3 = ((String)localObject2).equals("PROTECTION_SYSTEM_LEVEL");
              if (bool3)
              {
                i = 2;
                continue;
              }
            }
            i = -1;
            switch (i)
            {
            default: 
              i2 += 1;
              break;
            case 2: 
            case 3: 
              n += 1;
              break;
            case 1: 
              m += 1;
              break;
            case 0: 
              k += 1;
            }
          }
          catch (Exception localException4) {}
        }
        bool2 = a((String[])localObject3);
        i1 += 1;
        i3 += 1;
      }
      i = a(bool1, k, m, n, i1, i2, 0.7D, 6.0D, bool2);
      localObject3 = a(paramApplication, str, i, 75);
      if (!bool1)
      {
        localObject2 = new HashMap();
        ((Map)localObject2).put("name", localObject1);
        ((Map)localObject2).put("package_name", str);
        ((Map)localObject2).put("normal_permissions", Integer.valueOf(k));
        ((Map)localObject2).put("dangerous_permissions", Integer.valueOf(m));
        ((Map)localObject2).put("system_level_permissions", Integer.valueOf(n));
        ((Map)localObject2).put("custom_permissions", Integer.valueOf(i1));
        ((Map)localObject2).put("other_permissions", Integer.valueOf(i2));
        ((Map)localObject2).put("elevated_risk_score", Integer.valueOf(i));
        ((Map)localObject2).put("application_signature", localObject3);
        localArrayList.add(localObject2);
        i = j;
      }
      else
      {
        i = j;
      }
      i += 1;
    }
    return localArrayList;
  }
}
