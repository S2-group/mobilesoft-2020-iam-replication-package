package com.kikatech.theme;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;
import com.kikatech.theme.util.PackageUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class InstalledPackagesHolder
{
  private Context mApplicationContext;
  private boolean mInited;
  private List<PackageInfo> mInstalledPackages = new LinkedList();
  
  private InstalledPackagesHolder() {}
  
  public static InstalledPackagesHolder getInstance()
  {
    return Singleton.INSTANCE;
  }
  
  public List<PackageInfo> getInstalledPackages()
  {
    return this.mInstalledPackages;
  }
  
  /* Error */
  public void init(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 42	com/kikatech/theme/InstalledPackagesHolder:mInited	Z
    //   6: ifeq +11 -> 17
    //   9: aload_0
    //   10: aload_1
    //   11: putfield 44	com/kikatech/theme/InstalledPackagesHolder:mApplicationContext	Landroid/content/Context;
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield 42	com/kikatech/theme/InstalledPackagesHolder:mInited	Z
    //   22: aload_0
    //   23: aload_1
    //   24: putfield 44	com/kikatech/theme/InstalledPackagesHolder:mApplicationContext	Landroid/content/Context;
    //   27: aload_0
    //   28: aload_1
    //   29: invokestatic 50	com/kikatech/theme/util/PackageUtil:queryInstalledPackages	(Landroid/content/Context;)Ljava/util/List;
    //   32: putfield 25	com/kikatech/theme/InstalledPackagesHolder:mInstalledPackages	Ljava/util/List;
    //   35: goto -21 -> 14
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	InstalledPackagesHolder
    //   0	43	1	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   2	14	38	finally
    //   17	35	38	finally
  }
  
  public void notifyPackageAdd(String paramString)
  {
    Log.e("InstalledPackagesHolder", paramString);
    paramString = PackageUtil.getPackageInfo(this.mApplicationContext, paramString);
    if (paramString != null)
    {
      this.mInstalledPackages.add(paramString);
      ImeStateManager.getInstance().setHasChange();
    }
  }
  
  public void notifyPackageRemove(String paramString)
  {
    Log.e("InstalledPackagesHolder", paramString);
    if (TextUtils.isEmpty(paramString)) {}
    ListIterator localListIterator;
    do
    {
      return;
      while (!localListIterator.hasNext()) {
        localListIterator = this.mInstalledPackages.listIterator();
      }
    } while (!paramString.equals(((PackageInfo)localListIterator.next()).packageName));
    localListIterator.remove();
    ImeStateManager.getInstance().setHasChange();
  }
  
  private static class Singleton
  {
    static InstalledPackagesHolder INSTANCE = new InstalledPackagesHolder(null);
  }
}
