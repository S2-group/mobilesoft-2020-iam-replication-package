package com.duowan.basesdk.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class d
{
  private static HashMap<String, SimpleDateFormat> a = new HashMap();
  
  public static SimpleDateFormat a(String paramString)
  {
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      SimpleDateFormat localSimpleDateFormat2 = (SimpleDateFormat)a.get(paramString);
      SimpleDateFormat localSimpleDateFormat1 = localSimpleDateFormat2;
      if (localSimpleDateFormat2 == null)
      {
        localSimpleDateFormat1 = new SimpleDateFormat(paramString);
        a.put(paramString, localSimpleDateFormat1);
      }
      return localSimpleDateFormat1;
    }
    return new SimpleDateFormat(paramString);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
}
