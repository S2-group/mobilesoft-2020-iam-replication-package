import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class agq
{
  private static ArrayList<String> a = new ArrayList();
  private static long b;
  
  public static boolean canLaunch(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramString) != null;
  }
  
  public static boolean canShow(String paramString)
  {
    return (getAppIconBitmap(paramString) != null) && (!TextUtils.isEmpty(getNameByPackage(agv.getInstance().getGlobalContext(), paramString)));
  }
  
  public static ArrayList<String> getAllRunningListSwipe(Context paramContext)
  {
    int i = 0;
    for (;;)
    {
      int j;
      try
      {
        localArrayList = new ArrayList();
      }
      catch (Exception paramContext)
      {
        try
        {
          List localList = paramContext.getPackageManager().getInstalledPackages(0);
          boolean bool = ahu.needHideInRecent();
          paramContext = ahf.getPackageName(paramContext);
          j = 0;
          if (j < localList.size())
          {
            PackageInfo localPackageInfo = (PackageInfo)localList.get(j);
            if (((localPackageInfo.applicationInfo.flags & 0x1) != 0) || ((localPackageInfo.applicationInfo.flags & 0x80) != 0) || ((localPackageInfo.applicationInfo.flags & 0x200000) != 0)) {
              break label167;
            }
            if ((paramContext != null) && (paramContext.equals(localPackageInfo.packageName)))
            {
              if (bool) {
                break label164;
              }
              localArrayList.add(localPackageInfo.packageName);
              i += 1;
              break label170;
            }
            localArrayList.add(localPackageInfo.packageName);
            i += 1;
            break label170;
          }
          return localArrayList;
        }
        catch (Exception paramContext)
        {
          ArrayList localArrayList;
          return localArrayList;
        }
        paramContext = paramContext;
        return null;
      }
      label164:
      label167:
      label170:
      if (i != 8) {
        j += 1;
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
      paramString = ahg.getInstance().loadImage(paramString, agv.getInstance().getGlobalContext());
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static String getNameByPackage(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = paramContext.getPackageManager();
        paramString = paramContext.getApplicationInfo(paramString, 8192);
        if (paramString != null)
        {
          paramContext = paramString.loadLabel(paramContext).toString();
          return paramContext;
        }
      }
      catch (Exception paramContext) {}
    }
    return null;
  }
  
  public static ArrayList<String> getRecentAppList()
  {
    if (ahe.a) {
      Log.v("easy-swipe", "loadRecentAppList");
    }
    b = System.currentTimeMillis();
    ArrayList localArrayList = getAllRunningListSwipe(agv.getInstance().getGlobalContext());
    HashSet localHashSet = new HashSet();
    int i = localArrayList.size() - 1;
    if (i >= 0)
    {
      String str2 = (String)localArrayList.get(i);
      String str1 = str2;
      if (str2.contains(":")) {
        str1 = str2.split(":")[0];
      }
      if ((localHashSet.contains(str1)) || (!canShow(str1))) {
        localArrayList.remove(i);
      }
      for (;;)
      {
        i -= 1;
        break;
        localHashSet.add(str1);
      }
    }
    return localArrayList;
  }
  
  public static ArrayList<String> loadRecentAppList(boolean paramBoolean)
  {
    ArrayList localArrayList = a;
    if (!paramBoolean) {}
    try
    {
      if ((a.size() == 0) || (System.currentTimeMillis() - b > 600000L))
      {
        a.clear();
        a.addAll(getRecentAppList());
      }
      return (ArrayList)a.clone();
    }
    finally {}
  }
}
