package com.quantcast.measurement.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.quantcast.c.a;
import com.quantcast.c.b;
import com.quantcast.c.c;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class e
  implements b, c
{
  private static final n.a a = new n.a(e.class);
  private static final String b = e.class.getName();
  private static final String c = e.class.getName() + ".present";
  private static final String d = a.class.getName() + ".blockEventCollection";
  private final Context e;
  
  public e(Context paramContext)
  {
    this.e = paramContext.getApplicationContext();
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = c(paramContext);
    if (a(paramContext)) {
      bool = new File(paramContext, paramString).exists();
    }
    return bool;
  }
  
  private static boolean a(File paramFile)
  {
    if ((paramFile == null) || (!paramFile.exists())) {
      return false;
    }
    return b(paramFile);
  }
  
  private static boolean b(Context paramContext)
  {
    return a(paramContext, c);
  }
  
  private static boolean b(File paramFile)
  {
    boolean bool = true;
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isDirectory()) || (!paramFile.canRead()) || (!paramFile.canWrite()))
    {
      bool = false;
      if (paramFile != null) {
        n.c(a, "The directory (" + paramFile.getAbsolutePath() + ") cannot be accessed appropriately.");
      }
    }
    else
    {
      return bool;
    }
    n.c(a, "A null directory has could not be tested.");
    return false;
  }
  
  private static File c(Context paramContext)
  {
    return paramContext.getDir(b, 3);
  }
  
  public a a()
  {
    return a(this.e);
  }
  
  public a a(Context paramContext)
  {
    return new a(a(paramContext, d));
  }
  
  public void a(Context paramContext, a paramA)
  {
    paramContext = c(paramContext);
    if (b(paramContext))
    {
      paramContext = new File(paramContext, d);
      if (!paramA.b) {
        paramContext.delete();
      }
    }
    else
    {
      return;
    }
    try
    {
      paramContext.createNewFile();
      return;
    }
    catch (IOException paramA)
    {
      n.c(a, "Unable to create opt-out file (" + paramContext.getAbsolutePath() + ".");
    }
  }
  
  public void a(a paramA)
  {
    a(this.e, paramA);
  }
  
  public boolean b()
  {
    return b(this.e);
  }
  
  public void c()
  {
    File localFile = c(this.e);
    if (b(localFile)) {
      localFile = new File(localFile, c);
    }
    try
    {
      localFile.createNewFile();
      return;
    }
    catch (IOException localIOException)
    {
      n.c(a, "Unable to create presence file (" + localFile.getAbsolutePath() + ".");
    }
  }
  
  public Context d()
  {
    Iterator localIterator = this.e.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      if (!((PackageInfo)localObject).packageName.equals(this.e.getPackageName())) {
        try
        {
          localObject = this.e.createPackageContext(((PackageInfo)localObject).packageName, 0);
          boolean bool = b((Context)localObject);
          if (bool) {
            return localObject;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          n.b(a, "Unable to create context from package name.", localNameNotFoundException);
        }
      }
    }
    return null;
  }
  
  public Queue<Context> e()
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.e.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      if (!((PackageInfo)localObject).packageName.equals(this.e.getPackageName())) {
        try
        {
          localObject = this.e.createPackageContext(((PackageInfo)localObject).packageName, 0);
          if (b((Context)localObject)) {
            localLinkedList.add(localObject);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          n.b(a, "Unable to create context from package name.", localNameNotFoundException);
        }
      }
    }
    return localLinkedList;
  }
}
