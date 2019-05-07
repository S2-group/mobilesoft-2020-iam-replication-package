package jp.pioneer.mbg.appradio.AppRadioLauncher.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jp.pioneer.mbg.appradio.AppRadioLauncher.common.d;
import jp.pioneer.mbg.appradio.recommend.j;

public class a
  implements b
{
  public a() {}
  
  private List a(PackageManager paramPackageManager, List paramList, String paramString1, String paramString2, String paramString3)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return localArrayList;
      }
      PackageInfo localPackageInfo = (PackageInfo)paramList.next();
      if ((localPackageInfo != null) && (a(paramPackageManager, paramString1, localPackageInfo.packageName, paramString3)) && (!paramString2.contentEquals(localPackageInfo.packageName))) {
        localArrayList.add(localPackageInfo);
      }
    }
  }
  
  private List a(List paramList, String[] paramArrayOfString, Context paramContext)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
    {
      paramList = paramList.iterator();
      for (;;)
      {
        if (!paramList.hasNext()) {
          return localArrayList;
        }
        localArrayList.add(new c((PackageInfo)paramList.next(), false));
      }
    }
    int k = paramList.size();
    int i = 0;
    label91:
    CharSequence localCharSequence;
    int j;
    if (i < k)
    {
      localCharSequence = paramContext.getPackageManager().getApplicationLabel(((PackageInfo)paramList.get(i)).applicationInfo);
      jp.pioneer.mbg.pioneerkit.common.a.a("lyl, context.getPackageManager().getApplicationLabel:" + localCharSequence);
      j = 0;
    }
    for (;;)
    {
      if (j >= paramArrayOfString.length) {}
      for (;;)
      {
        if (j == paramArrayOfString.length)
        {
          jp.pioneer.mbg.pioneerkit.common.a.a("lyl, index == values.length :" + localCharSequence);
          localArrayList.add(new c((PackageInfo)paramList.get(i), false));
        }
        i += 1;
        break label91;
        break;
        if (!localCharSequence.equals(paramArrayOfString[j])) {
          break label276;
        }
        jp.pioneer.mbg.pioneerkit.common.a.a("lyl, equals text :" + localCharSequence);
        localArrayList.add(new c((PackageInfo)paramList.get(i), true));
      }
      label276:
      j += 1;
    }
  }
  
  private boolean a(PackageManager paramPackageManager, String paramString1, String paramString2, String paramString3)
  {
    Object localObject = null;
    boolean bool2 = false;
    boolean bool1;
    if (paramPackageManager.checkPermission(paramString1, paramString2) == 0) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      try
      {
        paramPackageManager = paramPackageManager.getApplicationInfo(paramString2, 128);
        bool1 = bool2;
        if (paramPackageManager == null) {
          continue;
        }
        bool1 = bool2;
        if (paramPackageManager.metaData == null) {
          continue;
        }
        return paramPackageManager.metaData.getBoolean(paramString3);
      }
      catch (PackageManager.NameNotFoundException paramPackageManager)
      {
        for (;;)
        {
          paramPackageManager.printStackTrace();
          paramPackageManager = localObject;
        }
      }
    }
  }
  
  public List a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    String str2 = paramContext.getPackageName();
    List localList = localPackageManager.getInstalledPackages(4096);
    String[] arrayOfString = d.a(paramContext);
    Object localObject = j.f();
    String str1;
    if (((j)localObject).v() == 1)
    {
      localObject = "pioneer.permission.appradio.ADVANCED_APPMODE";
      str1 = "pioneer_supported_aam";
    }
    for (;;)
    {
      localObject = a(a(localPackageManager, localList, (String)localObject, str2, str1), arrayOfString, paramContext);
      paramContext = (Context)localObject;
      if (localObject == null) {
        paramContext = new ArrayList();
      }
      return paramContext;
      if (((j)localObject).v() == 2)
      {
        localObject = "pioneer.permission.appradio.AAM2";
        str1 = "pioneer_supported_aam2";
      }
      else
      {
        localObject = "pioneer.permission.appradio.AAM2";
        str1 = "pioneer_supported_aam2";
      }
    }
  }
}
