package com.edmunds.ui;

import android.app.Dialog;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import com.edmunds.dagger.Dagger;
import com.edmunds.ui.preference.AppPreferences;
import com.edmunds.ui.search.MainActivity;
import com.edmunds.ui.wtf.AbTestingManager;
import com.edmunds.ui.wtf.AbTestingManager.OnRecipesUpdateFinishedListener;
import com.edmunds.util.EdmundsDialogBuilder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplashScreenActivity
  extends AppCompatActivity
  implements AbTestingManager.OnRecipesUpdateFinishedListener
{
  private static final Logger LOG = LoggerFactory.getLogger("SplashScreenActivity");
  private static volatile boolean isAbTestingResponseReceived = false;
  
  public SplashScreenActivity() {}
  
  private boolean checkPlayStoreIsInstalled()
  {
    Iterator localIterator = getPackageManager().getInstalledPackages(8192).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals("com.android.vending")) {
        return true;
      }
    }
    return false;
  }
  
  private void initAbTestingManager()
  {
    ((AbTestingManager)Dagger.get(AbTestingManager.class)).updateRecipes(null, this);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (!SplashScreenActivity.isAbTestingResponseReceived) {
          SplashScreenActivity.this.onRecipesUpdateTimeout();
        }
        if (!SplashScreenActivity.this.isFinishing())
        {
          Intent localIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
          SplashScreenActivity.this.startActivity(localIntent);
          SplashScreenActivity.this.finish();
        }
      }
    }, ((AppPreferences)Dagger.get(AppPreferences.class)).getSplashScreenDisplayMillis());
  }
  
  private void initFullscreen()
  {
    getWindow().getDecorView().setSystemUiVisibility(6);
  }
  
  protected boolean checkPlayServicesAvailableOrInvalid()
  {
    final int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if ((i != 0) && (GooglePlayServicesUtil.isUserRecoverableError(i)))
    {
      if (checkPlayStoreIsInstalled())
      {
        if (((AppPreferences)Dagger.get(AppPreferences.class)).isGooglePlayServicesUpdateDeclined()) {
          return true;
        }
        Dialog localDialog = new EdmundsDialogBuilder(this).setPositiveButton1(2131689664, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            try
            {
              paramAnonymousDialogInterface = GooglePlayServicesUtil.getErrorPendingIntent(i, SplashScreenActivity.this, 0);
              if (paramAnonymousDialogInterface != null) {
                paramAnonymousDialogInterface.send();
              }
            }
            catch (PendingIntent.CanceledException paramAnonymousDialogInterface)
            {
              String str = SplashScreenActivity.class.getSimpleName();
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("");
              localStringBuilder.append(paramAnonymousDialogInterface.getMessage());
              Log.d(str, localStringBuilder.toString(), paramAnonymousDialogInterface);
            }
            SplashScreenActivity.this.initAbTestingManager();
          }
        }).setPositiveButton2(2131689663, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            SplashScreenActivity.this.initAbTestingManager();
          }
        }).setNegativeButton(2131689662, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            ((AppPreferences)Dagger.get(AppPreferences.class)).setIsGooglePlayServicesUpdateDeclined(true);
            SplashScreenActivity.this.initAbTestingManager();
          }
        }).setBody(2131689661).create();
        localDialog.setCanceledOnTouchOutside(false);
        localDialog.setCancelable(false);
        localDialog.show();
        return false;
      }
      return true;
    }
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    initFullscreen();
    if (checkPlayServicesAvailableOrInvalid()) {
      initAbTestingManager();
    }
  }
  
  public void onRecipesUpdateFinished()
  {
    isAbTestingResponseReceived = true;
    LOG.debug("recipes received");
  }
  
  public void onRecipesUpdateFinishedWithError()
  {
    isAbTestingResponseReceived = true;
    LOG.debug("recipes error");
  }
  
  public void onRecipesUpdateTimeout()
  {
    ((AbTestingManager)Dagger.get(AbTestingManager.class)).cancelAllRequests();
    LOG.debug("recipes timeout");
  }
}
