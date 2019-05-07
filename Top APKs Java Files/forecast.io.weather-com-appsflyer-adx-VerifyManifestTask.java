package com.appsflyer.adx;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class VerifyManifestTask
  implements Observable.OnSubscribe<Boolean>
{
  private final String TAG = getClass().getSimpleName();
  private final String[] activities = { "com.appsflyer.adx.ads.MonsterFullscreenAdActivity", "com.appsflyer.adx.ads.MonsterPopupAdsActivity", "com.appsflyer.adx.feedback.FeedbackActivity", "com.appsflyer.adx.update.UpdateAppActivity", "com.appsflyer.adx.ads.TransparentAdsActivity", "com.appsflyer.adx.ads.AutoCloseActivity", "com.google.android.gms.ads.AdActivity", "com.facebook.ads.AudienceNetworkActivity" };
  private final String[] configsManifest = { "<activity\n            android:name=\"com.appsflyer.adx.ads.MonsterFullscreenAdActivity\"\n            android:configChanges=\"keyboardHidden|orientation|screenSize\"\n            android:label=\"\"\n            android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n            android:screenOrientation=\"portrait\"/>", "<activity android:name=\"com.appsflyer.adx.ads.MonsterPopupAdsActivity\"\n            android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n            android:label=\"\"\n            android:launchMode=\"singleTask\"/>", "<activity android:name=\"com.appsflyer.adx.feedback.FeedbackActivity\"\n            android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n            android:windowSoftInputMode=\"stateHidden|adjustPan|adjustNothing\"\n            android:configChanges=\"keyboard|keyboardHidden|screenLayout|screenSize|orientation\"\n            android:screenOrientation=\"portrait\"/>", "<activity android:name=\"com.appsflyer.adx.update.UpdateAppActivity\"\n            android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"/>", "<activity android:name=\"com.appsflyer.adx.ads.TransparentAdsActivity\"\n            android:theme=\"@android:style/Theme.Translucent.NoTitleBar\" />", "<activity android:name=\"com.appsflyer.adx.ads.AutoCloseActivity\"\n            android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"/>", "<activity\n            android:name=\"com.google.android.gms.ads.AdActivity\"\n            android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\"\n            android:exported=\"false\"\n            android:theme=\"@android:style/Theme.Translucent\" \n            android:icon=\"@drawable/common_full_open_on_phone\"\n            android:label=\"\"/>", "<activity\n            android:name=\"com.facebook.ads.AudienceNetworkActivity\"\n            android:configChanges=\"keyboardHidden|orientation|screenSize\" \n            android:icon=\"@drawable/common_full_open_on_phone\"\n            android:label=\"\"/>" };
  private Context context;
  
  public VerifyManifestTask(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
  }
  
  private ActivityInfo[] getActivityInfos()
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledPackages(1).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      if (((PackageInfo)localObject).packageName.equals(this.context.getPackageName()))
      {
        localObject = ((PackageInfo)localObject).activities;
        if (localObject != null) {
          return localObject;
        }
      }
    }
    return null;
  }
  
  public void call(Subscriber<? super Boolean> paramSubscriber)
  {
    getActivityInfos();
  }
}
