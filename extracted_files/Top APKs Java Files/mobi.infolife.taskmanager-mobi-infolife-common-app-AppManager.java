package mobi.infolife.common.app;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mobi.infolife.taskmanager.Utils;

public class AppManager
{
  public AppManager() {}
  
  public static List<AppInfo> getAllAppList(Context paramContext, IconCache paramIconCache, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject2 = paramContext.getPackageManager().getInstalledPackages(8192);
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    localObject1 = paramContext.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
    ArrayList localArrayList = new ArrayList();
    String str = paramContext.getPackageName();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
      if ((!paramBoolean2) || (localPackageInfo.packageName == null) || (!localPackageInfo.packageName.equals(str)))
      {
        int j = 0;
        Iterator localIterator = ((List)localObject1).iterator();
        ResolveInfo localResolveInfo;
        do
        {
          i = j;
          if (!localIterator.hasNext()) {
            break;
          }
          localResolveInfo = (ResolveInfo)localIterator.next();
        } while ((localPackageInfo.packageName == null) || (!localPackageInfo.packageName.equals(localResolveInfo.activityInfo.packageName)));
        int i = 1;
        if (i != 0)
        {
          int k = 0;
          i = 0;
          for (;;)
          {
            j = k;
            if (i < localArrayList.size())
            {
              if (((AppInfo)localArrayList.get(i)).getPackageName().equals(localPackageInfo.packageName)) {
                j = 1;
              }
            }
            else
            {
              if (j != 0) {
                break;
              }
              try
              {
                localArrayList.add(new AppInfo(paramContext, localPackageInfo, paramIconCache, paramBoolean1));
              }
              catch (Exception localException) {}
              break;
            }
            i += 1;
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static int getAndroidVersion()
  {
    return Integer.parseInt(Build.VERSION.SDK);
  }
  
  public static String getClassNameByPackageName(Context paramContext, String paramString)
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0);
    localObject = "";
    Iterator localIterator = paramContext.iterator();
    do
    {
      paramContext = (Context)localObject;
      if (!localIterator.hasNext()) {
        break;
      }
      paramContext = (ResolveInfo)localIterator.next();
    } while (!paramContext.activityInfo.packageName.equals(paramString));
    paramContext = paramContext.activityInfo.name;
    return paramContext;
  }
  
  public static List<AppInfo> getInstalledAppList(Context paramContext, IconCache paramIconCache, boolean paramBoolean1, boolean paramBoolean2)
  {
    new Intent("android.intent.action.MAIN", null).addCategory("android.intent.category.LAUNCHER");
    Object localObject = paramContext.getPackageManager().getInstalledPackages(8192);
    ArrayList localArrayList = new ArrayList();
    String str = paramContext.getPackageName();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((!paramBoolean2) || (localPackageInfo.packageName == null) || (!localPackageInfo.packageName.equals(str)))
      {
        int i;
        label115:
        int k;
        if ((localPackageInfo.applicationInfo.flags & 0x1) != 0)
        {
          i = 1;
          if ((i != 0) && ((localPackageInfo.applicationInfo.sourceDir.startsWith("/system/")) || (localPackageInfo.applicationInfo.sourceDir.startsWith("/vendor/")))) {
            break label240;
          }
          k = 0;
          i = 0;
        }
        for (;;)
        {
          int j = k;
          if (i < localArrayList.size())
          {
            if (((AppInfo)localArrayList.get(i)).getPackageName().equals(localPackageInfo.packageName)) {
              j = 1;
            }
          }
          else
          {
            if (j != 0) {
              break;
            }
            try
            {
              localArrayList.add(new AppInfo(paramContext, localPackageInfo, paramIconCache, paramBoolean1));
            }
            catch (Exception localException) {}
            break;
            i = 0;
            break label115;
            label240:
            break;
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  public static boolean isAndroidV22()
  {
    return getAndroidVersion() == 8;
  }
  
  public static boolean isAndroidV23()
  {
    return getAndroidVersion() == 9;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isGEAndroidV22()
  {
    return getAndroidVersion() >= 8;
  }
  
  public static boolean isGEAndroidV23()
  {
    return getAndroidVersion() >= 9;
  }
  
  public static boolean isGEAndroidV30()
  {
    return getAndroidVersion() >= 11;
  }
  
  public static boolean isGoogleMarketInstalled(Context paramContext)
  {
    return isAppInstalled(paramContext, "com.android.vending");
  }
  
  public static boolean isServiceExists(Context paramContext, ComponentName paramComponentName)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getServiceInfo(paramComponentName, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void launchApp(Context paramContext, ComponentName paramComponentName)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.SUBJECT", paramContext.getString(2131165390));
    localIntent.putExtra("android.intent.extra.TEXT", paramContext.getString(2131165389) + Utils.sShortShareLink);
    localIntent.setType("text/plain");
    localIntent.setFlags(268435456);
    localIntent.setComponent(paramComponentName);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void launchApp(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString, getClassNameByPackageName(paramContext, paramString));
    localIntent.setFlags(131072);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void showApplicationInfo(Context paramContext, String paramString)
  {
    Intent localIntent;
    if (isGEAndroidV23())
    {
      localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
      if (!isAndroidV22()) {
        break label60;
      }
      localIntent.putExtra("pkg", paramString);
    }
    for (;;)
    {
      paramContext.startActivity(localIntent);
      return;
      localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      break;
      label60:
      if (isGEAndroidV23()) {
        localIntent.setData(Uri.parse("package:" + paramString));
      } else {
        localIntent.putExtra("com.android.settings.ApplicationPkgName", paramString);
      }
    }
  }
  
  public static void uninstallPackage(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString)));
  }
}
