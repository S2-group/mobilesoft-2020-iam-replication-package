package net.robotmedia.billing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.android.easytracker.Analytics;
import com.aviary.android.feather.cds.AviaryCdsReceiver;
import com.datasync.SyncAdapter;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.squidtooth.lightspeed.HideService;
import com.squidtooth.settings.Settings;
import com.squidtooth.vault.data.Provider;
import com.squidtooth.vault.helpers.Storage;
import java.util.Iterator;
import java.util.List;

public class CustomBroadcastReceiver
  extends BroadcastReceiver
{
  private static final String ACTION_INSTALL_REFERRER = "com.android.vending.INSTALL_REFERRER";
  private static final String ACTION_NOTIFY = "com.android.vending.billing.IN_APP_NOTIFY";
  private static final String ACTION_PURCHASE_STATE_CHANGED = "com.android.vending.billing.PURCHASE_STATE_CHANGED";
  private static final String ACTION_RESPONSE_CODE = "com.android.vending.billing.RESPONSE_CODE";
  private static final String AVIARY_PLUGIN_PACKAGE_PREFIX = "com.aviary.android.feather.plugins.";
  private static final String COMPETITION_TRACKING = "Competition Tracking";
  private static final String[] COMPETITORS = { "com.smartanuj.hideitpro", "com.smartanuj.hideitprokey", "feipeng.ultimatesecret1", "com.kii.safe", "com.gmail.kdjsoftware.gallerypro.media", "ukzzang.android.gallerylocklite", "com.project.memoryerrorsafetwo", "com.devfo.mv", "com.crowbar.beaverbrowser", "com.hideimagevideo.supervault", "com.ti.privateimage", "com.camfiler.photosafe", "com.netqin.ps", "com.stithmar.photoprotector.lite", "com.android.PhotoVault", "PSS.VideoVault", "pssinc.MediaVault", "ukzzang.android.gallerylock", "com.thinkyeah.galleryvault", "com.handyapps.photoLocker", "com.handyapps.videolocker", "com.colure.app.privacygallery", "appplus.mobi.gallery", "com.capacus.neo", "com.ghostfilesplus", "com.code.android.vibevault", "com.xcs.piclock", "com.hi.piclock", "com.javamonkey.image.video.hide" };
  static final String EXTRA_INAPP_SIGNATURE = "inapp_signature";
  static final String EXTRA_INAPP_SIGNED_DATA = "inapp_signed_data";
  static final String EXTRA_NOTIFICATION_ID = "notification_id";
  static final String EXTRA_REQUEST_ID = "request_id";
  static final String EXTRA_RESPONSE_CODE = "response_code";
  private static final String tag = "Vaulty-CustomBroadcastReceiver";
  
  public CustomBroadcastReceiver() {}
  
  private String getActionNameFromAction(String paramString)
  {
    return paramString.replace("android.intent.action.PACKAGE_", "");
  }
  
  private boolean isCompetitor(String paramString)
  {
    String[] arrayOfString = COMPETITORS;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (paramString.startsWith(arrayOfString[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private boolean isPackageAction(String paramString)
  {
    return (paramString.equals("android.intent.action.PACKAGE_ADDED")) || (paramString.equals("android.intent.action.PACKAGE_REMOVED")) || (paramString.equals("android.intent.action.PACKAGE_REPLACED"));
  }
  
  private boolean isSyncTrigger(String paramString)
  {
    return paramString.equals("android.intent.action.ACTION_POWER_CONNECTED");
  }
  
  private boolean isThisApp(Context paramContext, String paramString)
  {
    return paramString.equals(paramContext.getPackageName());
  }
  
  private boolean isUpgradeAction(String paramString)
  {
    return paramString.equals("android.intent.action.PACKAGE_REPLACED");
  }
  
  private void scan(Context paramContext)
  {
    HideService.setToLaunchLater(paramContext, 0L);
    Storage.clearWriteModeCache();
  }
  
  private void trackCompetitor(String paramString1, String paramString2)
  {
    Analytics.trackEvent("Competition Tracking", paramString2, paramString1, 0);
  }
  
  public void onReceive(final Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if (("android.intent.action.MEDIA_MOUNTED".equals(str)) || ("android.intent.action.MEDIA_REMOVED".equals(str)) || ("android.intent.action.MEDIA_UNMOUNTED".equals(str)) || ("android.intent.action.MEDIA_SCANNER_FINISHED".equals(str))) {
      scan(paramContext);
    }
    do
    {
      do
      {
        return;
        if (str.equals("com.android.vending.INSTALL_REFERRER"))
        {
          scan(paramContext);
          new CampaignTrackingReceiver().onReceive(paramContext, paramIntent);
          new Thread(new Runnable()
          {
            public void run()
            {
              Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(0).iterator();
              while (localIterator.hasNext())
              {
                ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
                if (CustomBroadcastReceiver.this.isCompetitor(localApplicationInfo.packageName)) {
                  CustomBroadcastReceiver.this.trackCompetitor(localApplicationInfo.packageName, "ALREADY_INSTALLED");
                }
              }
            }
          }).start();
          return;
        }
        if (!isPackageAction(str)) {
          break;
        }
        paramIntent = paramIntent.getData().getSchemeSpecificPart();
        if ((isThisApp(paramContext, paramIntent)) && (isUpgradeAction(str)))
        {
          scan(paramContext);
          return;
        }
        if (isCompetitor(paramIntent))
        {
          trackCompetitor(paramIntent, getActionNameFromAction(str));
          return;
        }
      } while (!paramIntent.startsWith("com.aviary.android.feather.plugins."));
      Analytics.trackEvent("Aviary", getActionNameFromAction(str), paramIntent.replace("com.aviary.android.feather.plugins.", ""), 0);
      return;
      if (isSyncTrigger(str))
      {
        Settings.loadPreferences(paramContext);
        SyncAdapter.queueSync(Provider.getProviderAuthority(paramContext));
        return;
      }
    } while (!str.equals("android.intent.action.DOWNLOAD_COMPLETE"));
    new AviaryCdsReceiver().onReceive(paramContext, paramIntent);
  }
}
