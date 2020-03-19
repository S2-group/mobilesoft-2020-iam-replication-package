package com.trusteer.otrf.n;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.trusteer.otrf.p.b;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class e
  extends j
{
  e() {}
  
  private static String a(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  private static String a(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getInstallerPackageName(paramString);
  }
  
  private static boolean a(PackageInfo paramPackageInfo, String[] paramArrayOfString, boolean paramBoolean)
  {
    if (paramPackageInfo.requestedPermissions != null)
    {
      if (paramBoolean) {
        return !Collections.disjoint(Arrays.asList(paramPackageInfo.requestedPermissions), Arrays.asList(paramArrayOfString));
      }
      return Arrays.asList(paramPackageInfo.requestedPermissions).containsAll(Arrays.asList(paramArrayOfString));
    }
    return false;
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString1.length() != 0) && (paramString2 != null))
    {
      if (paramString2.length() == 0) {
        return false;
      }
      paramString1 = paramString1.toLowerCase();
      paramString2 = paramString2.toLowerCase();
      int j = 0;
      int i = j;
      while ((j < paramString1.length()) && (i < paramString2.length()) && (paramString2.charAt(i) != '*'))
      {
        if ((paramString1.charAt(j) != paramString2.charAt(i)) && (paramString2.charAt(i) != '?')) {
          return false;
        }
        i += 1;
        j += 1;
      }
      int k = j;
      int m = i;
      int n;
      for (;;)
      {
        n = i;
        if (j >= paramString1.length()) {
          break;
        }
        if ((i < paramString2.length()) && (paramString2.charAt(i) == '*'))
        {
          m = i + 1;
          if (m == paramString2.length()) {
            return true;
          }
          k = j + 1;
          i = m;
        }
        else if ((i < paramString2.length()) && ((paramString2.charAt(i) == '?') || (paramString1.charAt(j) == paramString2.charAt(i))))
        {
          i += 1;
          j += 1;
        }
        else
        {
          i = m;
          n = k + 1;
          j = k;
          k = n;
        }
      }
      while (paramString2.charAt(n) == '*') {
        n += 1;
      }
      return (j == paramString1.length()) && (n >= paramString2.length());
    }
    return false;
  }
  
  private static String[] a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(12288);
    paramContext = new ArrayList(((List)localObject).size());
    int i;
    if (paramString3.equals("and"))
    {
      i = 0;
    }
    else
    {
      if (!paramString3.equals("or")) {
        break label231;
      }
      i = 1;
    }
    paramString3 = paramString2.split(";");
    int j;
    if ((paramString2 != null) && (paramString2.length() != 0)) {
      j = 0;
    } else {
      j = 1;
    }
    paramString2 = ((List)localObject).iterator();
    while (paramString2.hasNext())
    {
      localObject = (PackageInfo)paramString2.next();
      boolean bool2 = a(((PackageInfo)localObject).packageName, paramString1);
      if (j != 0) {}
      for (;;)
      {
        bool1 = true;
        break label193;
        if (((PackageInfo)localObject).requestedPermissions == null) {
          break label190;
        }
        if (i == 0) {
          break;
        }
        if (Collections.disjoint(Arrays.asList(((PackageInfo)localObject).requestedPermissions), Arrays.asList(paramString3))) {
          break label190;
        }
      }
      boolean bool1 = Arrays.asList(((PackageInfo)localObject).requestedPermissions).containsAll(Arrays.asList(paramString3));
      break label193;
      label190:
      bool1 = false;
      label193:
      if ((bool2) && (bool1)) {
        paramContext.add(((PackageInfo)localObject).packageName);
      }
    }
    return (String[])paramContext.toArray(new String[paramContext.size()]);
    label231:
    return (String[])paramContext.toArray(new String[paramContext.size()]);
  }
  
  private static String b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = (String)paramContext.getApplicationLabel(paramContext.getApplicationInfo(paramString, 0));
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  private static String b(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Object localObject2 = "";
    Object localObject3 = null;
    String str = null;
    Object localObject1 = null;
    for (;;)
    {
      ZipFile localZipFile2;
      ZipFile localZipFile1;
      try
      {
        localZipFile2 = new ZipFile(d.a(paramContext, paramString1));
        localObject3 = localObject2;
      }
      catch (IOException paramContext)
      {
        try
        {
          Enumeration localEnumeration;
          int i;
          ((InputStream)localObject1).close();
          if (localZipFile1 != null) {
            localZipFile1.close();
          }
          return localObject2;
        }
        catch (IOException paramContext) {}
        paramContext = paramContext;
        continue;
      }
      try
      {
        localEnumeration = localZipFile2.entries();
        i = 0;
        paramString1 = (String)localObject1;
        paramContext = (Context)localObject2;
        localObject2 = paramContext;
        localObject1 = paramString1;
        localZipFile1 = localZipFile2;
        if (i == 0)
        {
          localObject2 = paramContext;
          localObject1 = paramString1;
          localZipFile1 = localZipFile2;
          localObject3 = paramContext;
          str = paramString1;
          if (localEnumeration.hasMoreElements())
          {
            localObject3 = paramContext;
            str = paramString1;
            localObject1 = (ZipEntry)localEnumeration.nextElement();
            localObject3 = paramContext;
            str = paramString1;
            if (!((ZipEntry)localObject1).getName().equals(paramString2)) {
              continue;
            }
            localObject3 = paramContext;
            str = paramString1;
            paramString1 = localZipFile2.getInputStream((ZipEntry)localObject1);
          }
        }
      }
      catch (IOException paramContext)
      {
        localObject2 = localObject3;
        localObject1 = str;
        localZipFile1 = localZipFile2;
        continue;
      }
      try
      {
        localObject1 = b.a(paramString1, paramString3);
        i = 1;
        paramContext = (Context)localObject1;
      }
      catch (IOException paramString2) {}
    }
    localObject2 = paramContext;
    localObject1 = paramString1;
    localZipFile1 = localZipFile2;
    break label174;
    localZipFile1 = null;
    localObject1 = localObject3;
    label174:
    if (localObject1 != null) {}
    return localObject2;
  }
}
