package ru.mail.android.mytracker.providers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ru.mail.android.mytracker.Tracer;
import ru.mail.android.mytracker.builders.JSONBuilder;
import ru.mail.android.mytracker.utils.EncryptionUtils;
import ru.mail.android.mytracker.utils.ListUtils;
import ru.mail.android.mytracker.utils.PreferencesManager;

public class AppsDataProvider
  extends AbstractFPDataProvider
{
  private ArrayList<AppInfo> apps;
  private String hash;
  private boolean isChanged = false;
  private boolean isEnabled = true;
  
  public AppsDataProvider() {}
  
  private ArrayList<AppInfo> getInstalledApps(Context paramContext)
  {
    ArrayList localArrayList = null;
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        localArrayList = new ArrayList();
        paramContext = paramContext.iterator();
        if (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
          if ((localApplicationInfo.flags & 0x1) != 0) {
            continue;
          }
          if (Build.VERSION.SDK_INT <= 8) {
            break label122;
          }
          l = localPackageInfo.firstInstallTime / 1000L;
          localArrayList.add(new AppInfo(localApplicationInfo.packageName, l));
          continue;
        }
      }
      catch (Throwable paramContext)
      {
        Tracer.d(paramContext.toString());
        paramContext = localArrayList;
        continue;
      }
      return localArrayList;
      label122:
      long l = 0L;
    }
  }
  
  public void collectData(Context paramContext)
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      Tracer.d("AppsDataProvider: You must not call collectData method from main thread");
    }
    for (;;)
    {
      return;
      if (this.isEnabled) {
        try
        {
          Object localObject = PreferencesManager.getInstance().init(paramContext);
          this.isChanged = false;
          this.apps = getInstalledApps(paramContext);
          paramContext = ListUtils.toString(this.apps);
          if ((paramContext != null) && (!paramContext.equals("")))
          {
            localObject = ((PreferencesManager)localObject).getAppsInstalledHash();
            this.hash = EncryptionUtils.md5(paramContext);
            if (((String)localObject).equals(this.hash))
            {
              Tracer.d("Apps hash did not changed");
              return;
            }
          }
        }
        catch (Throwable paramContext)
        {
          Tracer.d("PreferencesManager error: " + paramContext);
          return;
        }
      }
    }
    this.isChanged = true;
    Tracer.d("Apps hash changed");
  }
  
  public boolean isEnabled()
  {
    return this.isEnabled;
  }
  
  public void putDataToBuilder(JSONBuilder paramJSONBuilder)
  {
    if (this.isChanged) {
      paramJSONBuilder.addInstalledApps(this.apps);
    }
  }
  
  public void putDataToMap(Map<String, String> paramMap) {}
  
  public void setEnabled(boolean paramBoolean)
  {
    this.isEnabled = paramBoolean;
  }
  
  public void storeData(Context paramContext)
  {
    super.storeData(paramContext);
    if (this.isChanged) {}
    try
    {
      PreferencesManager.getInstance().init(paramContext).setAppsInstalledHash(this.hash);
      this.isChanged = false;
      return;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        Tracer.d("PreferencesManager error: " + paramContext);
      }
    }
  }
  
  public static class AppInfo
  {
    public final String bundle;
    public final long firstInstallTime;
    
    public AppInfo(String paramString, long paramLong)
    {
      this.bundle = paramString;
      this.firstInstallTime = paramLong;
    }
  }
}
