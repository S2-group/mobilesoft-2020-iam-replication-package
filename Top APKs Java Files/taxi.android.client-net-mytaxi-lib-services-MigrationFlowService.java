package net.mytaxi.lib.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.mytaxi.lib.interfaces.IUrlService;
import net.mytaxi.lib.locationsettings.LocationSettings;
import net.mytaxi.lib.util.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import taxi.android.client.activity.WelcomePageActivity;
import taxi.android.client.feature.migration.MigrationService;
import taxi.android.client.feature.migration.MigrationService.ICancelCallback;
import taxi.android.client.ui.startup.StartupActivity;

public class MigrationFlowService
  implements IMigrationFlowService
{
  private static final Logger log = LoggerFactory.getLogger(MigrationFlowService.class);
  private final Context context;
  private final LocationSettings locationSettings;
  private final IUrlService urlService;
  
  public MigrationFlowService(Context paramContext, LocationSettings paramLocationSettings, IUrlService paramIUrlService)
  {
    this.context = paramContext;
    this.locationSettings = paramLocationSettings;
    this.urlService = paramIUrlService;
  }
  
  private String getHailoPackageSuffix()
  {
    return "";
  }
  
  private boolean isHailoInstalled()
  {
    Object localObject = this.context.getPackageManager().getInstalledPackages(128);
    String str = "com.hailocab.consumer" + getHailoPackageSuffix();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (str.equals(localPackageInfo.packageName))
      {
        log.debug("Found Hailo version: " + localPackageInfo.versionCode);
        if (localPackageInfo.versionCode >= 1680) {
          return true;
        }
      }
    }
    return false;
  }
  
  private void sendCheckIntent(Context paramContext)
  {
    Intent localIntent = new Intent("com.hailocab.consumer.action.CHECK");
    localIntent.setComponent(new ComponentName("com.hailocab.consumer" + getHailoPackageSuffix(), "com.hailocab.consumer.services.MigrationService"));
    paramContext.startService(localIntent);
    log.debug("Check for token in Hailo with checkIntent: " + localIntent);
  }
  
  public boolean isHailoLoginAvailable()
  {
    return (isHailoInstalled()) && (this.locationSettings.isHailoLoginAvailable(DataUtils.getCountryCode(this.context)));
  }
  
  public void startMigrationInHailo(Context paramContext, MigrationService.ICancelCallback paramICancelCallback)
  {
    MigrationService.setCancelStartupCallback(paramICancelCallback);
    paramICancelCallback = new Intent("com.hailocab.consumer.action.MIGRATE");
    paramICancelCallback.setComponent(new ComponentName("com.hailocab.consumer" + getHailoPackageSuffix(), "com.hailocab.consumer.services.MigrationService"));
    paramContext.startService(paramICancelCallback);
    log.debug("Started migration in Hailo with intent: " + paramICancelCallback);
  }
  
  public void tryLoginWithHailoCredentials(final StartupActivity paramStartupActivity)
  {
    if (isHailoLoginAvailable())
    {
      log.debug("try to login with migration service");
      MigrationService.setCancelStartupCallback(new MigrationService.ICancelCallback()
      {
        public void cancelStartup(boolean paramAnonymousBoolean)
        {
          MigrationFlowService.log.debug("Cancel startup from migration service, token: " + paramAnonymousBoolean);
          this.val$timerSubscription.unsubscribe();
          if (!paramAnonymousBoolean)
          {
            WelcomePageActivity.start(paramStartupActivity);
            paramStartupActivity.finish();
          }
        }
      });
      sendCheckIntent(paramStartupActivity);
      return;
    }
    WelcomePageActivity.start(paramStartupActivity);
    paramStartupActivity.finish();
  }
}
