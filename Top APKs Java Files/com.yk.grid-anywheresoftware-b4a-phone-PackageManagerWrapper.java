package anywheresoftware.b4a.phone;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.objects.IntentWrapper;
import java.util.Iterator;

@BA.ShortName("PackageManager")
public class PackageManagerWrapper
{
  private PackageManager pm = BA.applicationContext.getPackageManager();
  
  public PackageManagerWrapper() {}
  
  public Drawable GetApplicationIcon(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.pm.getApplicationIcon(paramString);
  }
  
  public IntentWrapper GetApplicationIntent(String paramString)
  {
    IntentWrapper localIntentWrapper = new IntentWrapper();
    localIntentWrapper.setObject(this.pm.getLaunchIntentForPackage(paramString));
    return localIntentWrapper;
  }
  
  public String GetApplicationLabel(String paramString)
    throws PackageManager.NameNotFoundException
  {
    paramString = this.pm.getApplicationLabel(this.pm.getApplicationInfo(paramString, 0));
    if (paramString == null) {
      return "";
    }
    return paramString.toString();
  }
  
  public anywheresoftware.b4a.objects.collections.List GetInstalledPackages()
  {
    anywheresoftware.b4a.objects.collections.List localList = new anywheresoftware.b4a.objects.collections.List();
    Object localObject = this.pm.getInstalledPackages(0);
    localList.Initialize();
    localObject = ((java.util.List)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return localList;
      }
      localList.Add(((PackageInfo)((Iterator)localObject).next()).packageName);
    }
  }
  
  public int GetVersionCode(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.pm.getPackageInfo(paramString, 0).versionCode;
  }
  
  public String GetVersionName(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this.pm.getPackageInfo(paramString, 0).versionName;
  }
  
  public anywheresoftware.b4a.objects.collections.List QueryIntentActivities(Intent paramIntent)
  {
    Object localObject = this.pm.queryIntentActivities(paramIntent, 0);
    paramIntent = new anywheresoftware.b4a.objects.collections.List();
    paramIntent.Initialize();
    localObject = ((java.util.List)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return paramIntent;
      }
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      paramIntent.Add(new ComponentName(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name).flattenToShortString());
    }
  }
}
