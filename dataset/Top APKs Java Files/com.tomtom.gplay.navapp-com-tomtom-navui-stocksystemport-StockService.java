package com.tomtom.navui.stocksystemport;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import com.tomtom.navcloud.connector.api.NavCloudConnectionProvider;
import com.tomtom.navcloud.connector.api.NavCloudConnectionProvider.BindingIntentBuilder;
import com.tomtom.navcloud.connector.domain.NCFeature;
import com.tomtom.navui.appkit.AppContext;
import com.tomtom.navui.library.R.drawable;
import com.tomtom.navui.library.R.string;
import com.tomtom.navui.stocksystemport.navcloud.StockNavCloudConnectorManager;
import com.tomtom.navui.systemport.SystemApplication;
import com.tomtom.navui.systemport.SystemContext;
import com.tomtom.navui.systemport.SystemService;
import com.tomtom.navui.systemport.SystemSettings;
import com.tomtom.navui.taskkit.TaskContext;
import com.tomtom.navui.taskkit.TaskContext.SystemAdaptation;
import com.tomtom.navui.util.Log;
import com.tomtom.navui.util.SuppressWarnings;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings({"UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR", "SIC_INNER_SHOULD_BE_STATIC_ANON"})
public abstract class StockService
  extends Service
  implements SystemService
{
  private String a = null;
  private int b = -1;
  private boolean c = false;
  private IBinder d = new StockService.StockBinder(this);
  private StockNavCloudConnectorManager e;
  private ServiceConnection f = new StockService.1(this);
  
  public StockService() {}
  
  private static String a(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null) {
      return "";
    }
    paramPackageInfo = paramPackageInfo.services;
    if (paramPackageInfo == null) {
      return "no services";
    }
    StringBuilder localStringBuilder = new StringBuilder(256);
    int j = paramPackageInfo.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramPackageInfo[i];
      localStringBuilder.append("[").append(localObject.name).append("]");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static void a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(4);
    if ((paramContext == null) || (paramContext.isEmpty())) {
      if (!Log.e) {}
    }
    for (;;)
    {
      return;
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (Log.e) {
          new StringBuilder().append(localPackageInfo.packageName).append(" - services[").append(a(localPackageInfo)).append("]");
        }
      }
    }
  }
  
  public void bindService(Intent paramIntent)
  {
    if (paramIntent.getAction().equalsIgnoreCase("com.tomtom.navkit.NavKitLifeline"))
    {
      paramIntent.putExtra("FOREGROUND", false);
      try
      {
        if (!bindService(paramIntent, this.f, 1)) {
          throw new IllegalArgumentException("com.tomtom.navkit.NavKitLifeline service isn't installed");
        }
      }
      catch (SecurityException paramIntent)
      {
        a(this);
        throw paramIntent;
      }
      this.c = true;
    }
    Object localObject;
    do
    {
      return;
      if (!paramIntent.getAction().equalsIgnoreCase("com.tomtom.navcloud.connector.NavCloudConnectorService")) {
        break label315;
      }
      paramIntent = getSystemApplication().getAppKit();
      this.e = ((StockNavCloudConnectorManager)paramIntent.getSystemPort().getNavCloudConnectorManager());
      if (this.e == null) {
        break;
      }
      localObject = new NavCloudConnectionProvider(paramIntent.getTaskKit().getSystemAdaptation().getNavCloudPropertiesFilePath()).bindingIntentBuilder();
      if (this.a != null) {
        ((NavCloudConnectionProvider.BindingIntentBuilder)localObject).withNCCProcessPackageName(this.a).withNavKitProcessPackageName(this.a);
      }
      if (this.b != -1) {
        ((NavCloudConnectionProvider.BindingIntentBuilder)localObject).withNavKitPort(this.b);
      }
      localObject = ((NavCloudConnectionProvider.BindingIntentBuilder)localObject).buildIntent();
      SystemSettings localSystemSettings = paramIntent.getSystemPort().getSettings("com.tomtom.navui.settings");
      ((Intent)localObject).putExtra(NCFeature.ACTIVE_ROUTE_SYNC.toString(), localSystemSettings.getBoolean("com.tomtom.navui.setting.feature.ActiveDestinationSync", false));
      ((Intent)localObject).putExtra(NCFeature.FAVORITES_SYNC.toString(), localSystemSettings.getBoolean("com.tomtom.navui.setting.feature.FavoriteSync", false));
      ((Intent)localObject).putExtra(NCFeature.POI_DOWNLOAD.toString(), localSystemSettings.getBoolean("com.tomtom.navui.setting.feature.CommunityPoiSync2", false));
      ((Intent)localObject).putExtra(NCFeature.TRACK_SYNC.toString(), localSystemSettings.getBoolean("com.tomtom.navui.setting.feature.TrackSync", true));
      getSystemApplication().releaseAppKit(paramIntent);
    } while (bindService((Intent)localObject, this.e, 1));
    throw new IllegalArgumentException("service isn't installed");
    getSystemApplication().releaseAppKit(paramIntent);
    return;
    label315:
    throw new IllegalArgumentException("Unknown Service Name Specified");
  }
  
  public SystemApplication getSystemApplication()
  {
    return (SystemApplication)getApplication();
  }
  
  public boolean isRunning(Intent paramIntent)
  {
    if (paramIntent.getAction().equalsIgnoreCase("com.tomtom.navkit.NavKitLifeline")) {
      return this.c;
    }
    throw new IllegalArgumentException("unknown Service");
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    if (localBundle != null)
    {
      if (localBundle.containsKey("CUSTOM_PACKAGE_NAME")) {
        this.a = paramIntent.getStringExtra("CUSTOM_PACKAGE_NAME");
      }
      if (localBundle.containsKey("CUSTOM_NAVKIT_PORT")) {
        this.b = paramIntent.getIntExtra("CUSTOM_NAVKIT_PORT", -1);
      }
    }
    return this.d;
  }
  
  public void onCreate()
  {
    super.onCreate();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
  }
  
  @SuppressLint({"NewApi"})
  public void onTrimMemory(int paramInt)
  {
    if (Log.b) {
      new StringBuilder("onTrimMemory() [0x").append(Integer.toHexString(paramInt)).append("]");
    }
    super.onTrimMemory(paramInt);
  }
  
  public void setServiceInForeground(boolean paramBoolean, PendingIntent paramPendingIntent)
  {
    if (paramBoolean)
    {
      String str = getResources().getString(R.string.navui_fg_notification_content_title);
      startForeground(1, StockNotification.getNotificationImpl().createNotification(this, R.drawable.a, null, str, null, paramPendingIntent));
      return;
    }
    stopForeground(true);
  }
  
  public void unbindService(Intent paramIntent)
  {
    if (paramIntent.getAction().equalsIgnoreCase("com.tomtom.navkit.NavKitLifeline")) {
      if (this.c)
      {
        unbindService(this.f);
        this.c = false;
      }
    }
    do
    {
      return;
      if (!paramIntent.getAction().equalsIgnoreCase("com.tomtom.navcloud.connector.NavCloudConnectorService")) {
        break;
      }
    } while (this.e == null);
    unbindService(this.e);
    return;
    throw new IllegalArgumentException("unknown Service");
  }
}
