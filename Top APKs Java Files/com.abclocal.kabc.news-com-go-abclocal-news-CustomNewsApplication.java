package com.go.abclocal.news;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import com.fedorvlasov.lazylist.ImageLoader;
import com.go.abclocal.analytics.TrackingManager;
import com.go.abclocal.news.ad.AdFactory;
import com.go.abclocal.news.model.Configuration;
import com.go.abclocal.news.service.PushNotificationReceiver;
import com.go.abclocal.news.util.AppUtility;
import com.go.abclocal.services.ApplicationServices;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.UAirship;
import com.urbanairship.push.CustomPushNotificationBuilder;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushNotificationBuilder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CustomNewsApplication
  extends Application
{
  public static final String RAN_ONCE = "ranOnce";
  private static final String TAG = "ABCOTV-NewsApplication";
  
  public CustomNewsApplication() {}
  
  public static boolean hasGoogleService(Application paramApplication)
  {
    boolean bool1 = false;
    Object localObject = PreferenceManager.getDefaultSharedPreferences(paramApplication.getApplicationContext());
    SharedPreferences.Editor localEditor = ((SharedPreferences)localObject).edit();
    if (!((SharedPreferences)localObject).getBoolean("ranOnce", false)) {
      for (;;)
      {
        try
        {
          paramApplication = paramApplication.getPackageManager().getInstalledPackages(8192).iterator();
          bool2 = paramApplication.hasNext();
          if (bool2) {
            continue;
          }
        }
        catch (Exception paramApplication)
        {
          boolean bool2;
          continue;
        }
        localEditor.putBoolean("hasGoogleService", bool1);
        localEditor.apply();
        return bool1;
        localObject = (PackageInfo)paramApplication.next();
        if ((!((PackageInfo)localObject).packageName.equals("com.google.market")) && (!((PackageInfo)localObject).packageName.equals("com.google.vending")))
        {
          bool2 = ((PackageInfo)localObject).packageName.equals("com.android.vending");
          if (!bool2) {}
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return ((SharedPreferences)localObject).getBoolean("hasGoogleService", true);
  }
  
  public void onCreate()
  {
    super.onCreate();
    Object localObject2 = getApplicationContext();
    Object localObject1 = Build.MODEL;
    Log.d("ABCOTV-NewsApplication", "Device Model: " + (String)localObject1);
    ApplicationServices.getInstance(this);
    ImageLoader.getInstance((Context)localObject2);
    AdFactory.getInstance(this);
    TrackingManager.getInstance(this);
    try
    {
      AirshipConfigOptions localAirshipConfigOptions = AirshipConfigOptions.loadDefaultOptions(this);
      if ((AppUtility.signedWithDebugKey((Context)localObject2)) || (AppUtility.isDebuggable((Context)localObject2)))
      {
        localAirshipConfigOptions.inProduction = false;
        com.urbanairship.Logger.logLevel = 2;
      }
      for (AppUtility.logLevel = 3;; AppUtility.logLevel = 5)
      {
        localAirshipConfigOptions.transport = "gcm";
        if (!hasGoogleService(this)) {
          localAirshipConfigOptions.transport = "helium";
        }
        UAirship.takeOff(this, localAirshipConfigOptions);
        localObject2 = new HashSet();
        ((Set)localObject2).add(localObject1);
        PushManager.shared().setTags((Set)localObject2);
        localObject1 = new CustomPushNotificationBuilder();
        ((CustomPushNotificationBuilder)localObject1).statusBarIconDrawableId = R.drawable.icon_push_status_bar_small;
        ((CustomPushNotificationBuilder)localObject1).layout = R.layout.push_notification_message;
        ((CustomPushNotificationBuilder)localObject1).layoutIconDrawableId = R.drawable.icon_push_status_bar;
        ((CustomPushNotificationBuilder)localObject1).layoutIconId = R.id.icon;
        ((CustomPushNotificationBuilder)localObject1).layoutSubjectId = R.id.subject;
        ((CustomPushNotificationBuilder)localObject1).layoutMessageId = R.id.message;
        PushManager.shared().setNotificationBuilder((PushNotificationBuilder)localObject1);
        PushManager.shared().setIntentReceiver(PushNotificationReceiver.class);
        localObject1 = Configuration.shared(true);
        if (localObject1 == null) {
          break;
        }
        ((Configuration)localObject1).setHasBeingParsed(false);
        return;
        localAirshipConfigOptions.inProduction = true;
        com.urbanairship.Logger.logLevel = 5;
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e("ABCOTV-NewsApplication", "Error setting push manager", localException);
    }
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    ImageLoader.getInstance(getApplicationContext()).clearCache(true);
  }
}
