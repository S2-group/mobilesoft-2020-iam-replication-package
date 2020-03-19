package com.jiubang.golauncher.appcenter.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class m
{
  public static final String a = Environment.getExternalStorageDirectory().getPath();
  public static final String b = a + "/GoStore/test";
  private static List<String> c = new ArrayList();
  
  public static String a(Context paramContext)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = paramContext.getPackageManager();
    paramContext = b(paramContext);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramContext.size())
    {
      if (i < 50) {
        localArrayList.add(paramContext.get(i));
      }
      i += 1;
    }
    Collections.sort(localArrayList, new n((PackageManager)localObject));
    paramContext = localArrayList.iterator();
    while (paramContext.hasNext())
    {
      localObject = (PackageInfo)paramContext.next();
      if (localObject != null) {
        localStringBuffer.append(((PackageInfo)localObject).packageName).append(",");
      }
    }
    try
    {
      localStringBuffer.deleteCharAt(localStringBuffer.lastIndexOf(","));
      Log.d("mjw", "UnSystemAppPkgNameString: " + localStringBuffer.toString());
      return localStringBuffer.toString();
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static ArrayList<PackageInfo> b(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localArrayList.add(localPackageInfo);
      }
      i += 1;
    }
    return localArrayList;
  }
}
