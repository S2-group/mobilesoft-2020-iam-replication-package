package com.note.easy.memo_pub.notebook.esutil.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import com.note.easy.memo_pub.notebook.esutil.utils.a.a;
import com.note.easy.memo_pub.notebook.esutil.utils.a.b;
import com.note.easy.memo_pub.notebook.esutil.utils.common.e;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class i
{
  private static ArrayList<String> a = new ArrayList();
  private static long b;
  
  public static boolean canLaunch(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramString) != null;
  }
  
  public static boolean canShow(String paramString)
  {
    return (getAppIconBitmap(paramString) != null) && (!TextUtils.isEmpty(getNameByPackage(c.getInstance().getGlobalContext(), paramString)));
  }
  
  public static ArrayList<String> getAllRunningListWidgets(Context paramContext)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject;
      int k;
      boolean bool;
      String str;
      int j;
      int m;
      int i;
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        try
        {
          localObject = paramContext.getPackageManager();
          k = 0;
          localObject = ((PackageManager)localObject).getInstalledPackages(0);
          bool = m.needHideInRecent();
          str = a.getPackageName(paramContext);
          j = 0;
          paramContext = localArrayList;
          if (k < ((List)localObject).size())
          {
            paramContext = (PackageInfo)((List)localObject).get(k);
            m = j;
            if ((paramContext.applicationInfo.flags & 0x1) == 0)
            {
              m = j;
              if ((paramContext.applicationInfo.flags & 0x80) == 0)
              {
                m = j;
                if ((paramContext.applicationInfo.flags & 0x200000) == 0)
                {
                  if ((str != null) && (str.equals(paramContext.packageName)))
                  {
                    i = j;
                    if (!bool)
                    {
                      localArrayList.add(paramContext.packageName);
                      i = j + 1;
                    }
                  }
                  else
                  {
                    localArrayList.add(paramContext.packageName);
                    i = j + 1;
                  }
                  m = i;
                  if (i == 8) {
                    return localArrayList;
                  }
                }
              }
            }
            k += 1;
            j = m;
            continue;
            paramContext = null;
          }
          return paramContext;
        }
        catch (Exception paramContext) {}
        paramContext = paramContext;
      }
    }
  }
  
  public static Bitmap getAppIconBitmap(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    try
    {
      paramString = b.getInstance().loadImage(paramString, c.getInstance().getGlobalContext());
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String getNameByPackage(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    try
    {
      paramContext = paramContext.getPackageManager();
      paramString = paramContext.getApplicationInfo(paramString, 8192);
      if (paramString == null) {
        return null;
      }
      paramContext = paramString.loadLabel(paramContext).toString();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static ArrayList<String> getRecentAppList()
  {
    if (e.a) {
      Log.v("tt-tags", "loadRecentAppList");
    }
    b = System.currentTimeMillis();
    ArrayList localArrayList = getAllRunningListWidgets(c.getInstance().getGlobalContext());
    HashSet localHashSet = new HashSet();
    int i = localArrayList.size() - 1;
    while (i >= 0)
    {
      String str2 = (String)localArrayList.get(i);
      String str1 = str2;
      if (str2.contains(":")) {
        str1 = str2.split(":")[0];
      }
      if ((!localHashSet.contains(str1)) && (canShow(str1))) {
        localHashSet.add(str1);
      } else {
        localArrayList.remove(i);
      }
      i -= 1;
    }
    return localArrayList;
  }
  
  public static ArrayList<String> loadRecentAppList(boolean paramBoolean)
  {
    ArrayList localArrayList = a;
    if (!paramBoolean) {}
    try
    {
      if (a.size() != 0) {
        if (System.currentTimeMillis() - b <= 600000L) {
          break label52;
        }
      }
      a.clear();
      a.addAll(getRecentAppList());
      label52:
      return (ArrayList)a.clone();
    }
    finally
    {
      Object localObject1;
      for (;;) {}
    }
    throw localObject1;
  }
}
