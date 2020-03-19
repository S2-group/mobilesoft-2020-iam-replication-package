package cfl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class dww
{
  private static dww d = new dww();
  public PackageManager a = ecl.i().getPackageManager();
  private ArrayList<dwi> b = new ArrayList();
  private ArrayList<String> c = new ArrayList();
  
  private dww() {}
  
  private static ActivityInfo a(String paramString, PackageManager paramPackageManager)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setPackage(paramString);
    try
    {
      paramString = paramPackageManager.queryIntentActivities(localIntent, 0);
      if ((paramString == null) || (paramString.size() <= 0)) {
        return null;
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        dpi.a(paramString);
        paramString = null;
      }
    }
    return ((ResolveInfo)paramString.get(0)).activityInfo;
  }
  
  public static dww a()
  {
    return d;
  }
  
  private List<dwi> c()
  {
    Object localObject1 = this.a.getInstalledPackages(0);
    new StringBuilder("init Allapps == ").append(((List)localObject1).size());
    try
    {
      this.b.clear();
      this.c.clear();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
        a(localPackageInfo);
        dvp.a(localPackageInfo.packageName);
      }
      new StringBuilder("init Allappd == ").append(this.c.size());
    }
    finally {}
    return this.b;
  }
  
  public final void a(PackageInfo paramPackageInfo)
  {
    if ((paramPackageInfo.applicationInfo != null) && (!this.c.contains(paramPackageInfo.packageName)))
    {
      ActivityInfo localActivityInfo = a(paramPackageInfo.packageName, this.a);
      if (localActivityInfo != null)
      {
        dwi localDwi = new dwi(paramPackageInfo);
        localDwi.d = localActivityInfo.name;
        try
        {
          this.b.add(localDwi);
          this.c.add(paramPackageInfo.packageName);
          return;
        }
        finally {}
      }
    }
  }
  
  public final void a(String paramString)
  {
    try
    {
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext()) {
        if (TextUtils.equals(((dwi)localIterator.next()).c, paramString)) {
          localIterator.remove();
        }
      }
      this.c.remove(paramString);
    }
    finally {}
  }
  
  /* Error */
  public final dwi b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 31	cfl/dww:b	Ljava/util/ArrayList;
    //   6: invokevirtual 169	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   9: astore 4
    //   11: aload 4
    //   13: invokeinterface 122 1 0
    //   18: ifeq +33 -> 51
    //   21: aload 4
    //   23: invokeinterface 126 1 0
    //   28: checkcast 156	cfl/dwi
    //   31: astore_3
    //   32: aload_1
    //   33: aload_3
    //   34: getfield 171	cfl/dwi:c	Ljava/lang/String;
    //   37: invokestatic 177	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   40: istore_2
    //   41: iload_2
    //   42: ifeq -31 -> 11
    //   45: aload_3
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: areturn
    //   51: aconst_null
    //   52: astore_1
    //   53: goto -6 -> 47
    //   56: astore_1
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	dww
    //   0	61	1	paramString	String
    //   40	2	2	bool	boolean
    //   31	15	3	localDwi	dwi
    //   9	13	4	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   2	11	56	finally
    //   11	41	56	finally
  }
  
  public final List<dwi> b()
  {
    return new ArrayList(this.b);
  }
}
