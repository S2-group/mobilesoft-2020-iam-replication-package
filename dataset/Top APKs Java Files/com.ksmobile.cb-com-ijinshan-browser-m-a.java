package com.ijinshan.browser.m;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.ijinshan.browser.report.j;
import com.ijinshan.browser.utils.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
{
  public a() {}
  
  public List<String> a()
  {
    Object localObject = com.ijinshan.browser.a.aj();
    y.e("Track", "[Track] track list = " + (String)localObject);
    ArrayList localArrayList = new ArrayList();
    if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!"0".equals(localObject)))
    {
      localObject = ((String)localObject).split(",");
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        CharSequence localCharSequence = localObject[i];
        if (!TextUtils.isEmpty(localCharSequence)) {
          localArrayList.add(localCharSequence);
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public void a(Context paramContext)
  {
    y.e("Track", "[Track] start track");
    List localList = a();
    if (localList.size() == 0) {
      y.e("Track", "[Track] not need track");
    }
    for (;;)
    {
      return;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(1).iterator();
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          String str1 = localPackageInfo.packageName;
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            String str2 = (String)localIterator.next();
            if (str1.contains(str2)) {
              new j(str2, str1, localPackageInfo.versionName).a();
            }
          }
        }
        return;
      }
      catch (Exception paramContext)
      {
        y.e("Track", "[Track] track exception");
      }
    }
  }
}
