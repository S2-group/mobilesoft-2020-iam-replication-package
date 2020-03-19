package com.viettran.INKredible.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public final class t
{
  public static final String a(String paramString)
  {
    c.a.b.b.b(paramString, "appName");
    m.a("VersionHelper", paramString);
    Object localObject = Environment.getExternalStorageDirectory();
    c.a.b.b.a(localObject, "Environment.getExternalStorageDirectory()");
    localObject = ((File)localObject).getAbsolutePath();
    paramString = (String)localObject + File.separator + paramString + File.separator + "Documents";
    return paramString + File.separator + "Notebooks";
  }
  
  public static final boolean a()
  {
    return true;
  }
  
  public static final boolean a(Context paramContext)
  {
    c.a.b.b.b(paramContext, "context");
    return a(paramContext, "com.viettran.INKrediblePro");
  }
  
  public static final boolean a(Context paramContext, String paramString)
  {
    c.a.b.b.b(paramContext, "context");
    c.a.b.b.b(paramString, "packageName");
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    c.a.b.b.a(paramContext, "pm.getInstalledApplications(0)");
    paramContext = (Iterable)paramContext;
    if (((paramContext instanceof Collection)) && (((Collection)paramContext).isEmpty())) {
      return false;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext()) {
      if (c.a.b.b.a(((ApplicationInfo)paramContext.next()).packageName, paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static final boolean a(String paramString1, String paramString2, c.a.a.a<? super Boolean, c.a> paramA)
  {
    int i = 0;
    c.a.b.b.b(paramString1, "srcPath");
    c.a.b.b.b(paramString2, "dstPath");
    c.a.b.b.b(paramA, "updateProgress");
    Object localObject1 = new File(paramString1);
    File localFile = new File(paramString2);
    if (!((File)localObject1).exists()) {
      return false;
    }
    for (;;)
    {
      try
      {
        paramA.a(Boolean.valueOf(false));
        if (((File)localObject1).isDirectory())
        {
          if (!com.viettran.nsvg.document.a.b.c().q(paramString2)) {
            break;
          }
          localObject1 = ((File)localObject1).list();
          int j = localObject1.length;
          if (i >= j) {
            break label248;
          }
          localFile = localObject1[i];
          Object localObject2 = new File(paramString1 + File.separator + localFile);
          if (((File)localObject2).isDirectory())
          {
            localObject2 = ((File)localObject2).getPath();
            c.a.b.b.a(localObject2, "childFile.path");
            a((String)localObject2, paramString2 + File.separator + localFile, paramA);
          }
          else
          {
            org.apache.a.a.b.a((File)localObject2, new File(paramString2 + File.separator + localFile));
          }
        }
      }
      catch (IOException paramString1)
      {
        paramString1.printStackTrace();
        return true;
      }
      org.apache.a.a.b.a((File)localObject1, localFile);
      label248:
      return true;
      i += 1;
    }
  }
  
  public static final String b()
  {
    return a("INKredible PRO") + File.separator + "From INKredible";
  }
  
  public static final boolean b(Context paramContext)
  {
    c.a.b.b.b(paramContext, "context");
    return (a()) && (a(paramContext));
  }
  
  private static final int c(String paramString)
  {
    int k = 0;
    int i = 0;
    paramString = new File(paramString).listFiles();
    if (paramString != null)
    {
      int m = paramString.length;
      int j = 0;
      for (;;)
      {
        k = i;
        if (j >= m) {
          break;
        }
        k = i + 1;
        Object localObject = paramString[j];
        c.a.b.b.a(localObject, "file");
        i = k;
        if (((File)localObject).isDirectory())
        {
          localObject = ((File)localObject).getAbsolutePath();
          c.a.b.b.a(localObject, "file.absolutePath");
          i = k + c((String)localObject);
        }
        j += 1;
      }
    }
    return k;
  }
  
  public static final boolean c()
  {
    return com.viettran.nsvg.document.a.b.c().j(b());
  }
}
