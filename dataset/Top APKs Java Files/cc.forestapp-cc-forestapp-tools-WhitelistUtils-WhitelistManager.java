package cc.forestapp.tools.WhitelistUtils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import cc.forestapp.constants.AppInfoState;
import cc.forestapp.tools.coredata.CoreDataManager;
import cc.forestapp.tools.coredata.WADataManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.grandcentrix.tray.core.ItemNotFoundException;

public class WhitelistManager
{
  private static Map<String, InstalledAppInfo> a = new LinkedHashMap();
  private static Map<String, InstalledAppInfo> b = new LinkedHashMap();
  private static final ArrayList<String> c = new ArrayList() {};
  
  public WhitelistManager() {}
  
  private static AppInfoState a(Context paramContext, ApplicationInfo paramApplicationInfo)
  {
    String str = paramApplicationInfo.packageName;
    paramContext = AppInfoState.a;
    if (a(paramApplicationInfo)) {
      paramContext = AppInfoState.b;
    }
    paramApplicationInfo = c.iterator();
    if (paramApplicationInfo.hasNext())
    {
      if (!str.contains((String)paramApplicationInfo.next())) {
        break label82;
      }
      paramContext = AppInfoState.b;
    }
    label82:
    for (;;)
    {
      break;
      try
      {
        if (CoreDataManager.getWaDataManager().isAppBlack(str)) {
          return AppInfoState.a;
        }
        paramApplicationInfo = AppInfoState.b;
        return paramApplicationInfo;
      }
      catch (ItemNotFoundException paramApplicationInfo)
      {
        return paramContext;
      }
    }
  }
  
  public static Map<String, InstalledAppInfo> a()
  {
    return a;
  }
  
  public static void a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(128);
    Collections.sort((List)localObject, new Comparator()
    {
      public int a(ApplicationInfo paramAnonymousApplicationInfo1, ApplicationInfo paramAnonymousApplicationInfo2)
      {
        return paramAnonymousApplicationInfo1.loadLabel(this.a).toString().compareToIgnoreCase(paramAnonymousApplicationInfo2.loadLabel(this.a).toString());
      }
    });
    localObject = ((List)localObject).iterator();
    if (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      String str1 = localApplicationInfo.packageName;
      InstalledAppInfo localInstalledAppInfo = new InstalledAppInfo(str1, localApplicationInfo.loadLabel(localPackageManager).toString(), a(paramContext, localApplicationInfo));
      WADataManager localWADataManager = CoreDataManager.getWaDataManager();
      String str2 = localInstalledAppInfo.b();
      if (localInstalledAppInfo.d() == AppInfoState.a) {}
      for (boolean bool = true;; bool = false)
      {
        localWADataManager.setAppIsBlack(str2, bool);
        a.put(str1, localInstalledAppInfo);
        if (a(localApplicationInfo)) {
          break;
        }
        b.put(str1, localInstalledAppInfo);
        break;
      }
    }
  }
  
  private static boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  public static Map<String, InstalledAppInfo> b()
  {
    return b;
  }
}
