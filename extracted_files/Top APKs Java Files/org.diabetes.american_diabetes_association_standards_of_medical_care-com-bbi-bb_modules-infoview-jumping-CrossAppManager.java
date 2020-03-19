package com.bbi.bb_modules.infoview.jumping;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import com.bbi.bb_modules.google_analytics.GoogleAnalyticsTracker;
import com.bbi.bb_modules.infoview.extra.NetworkManager;
import com.bbi.behavior.appbehavior.CommonStringBehavior;
import com.bbi.supporting_modules.dialog.Callback;
import com.bbi.supporting_modules.dialog.MessageAlertDialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CrossAppManager
{
  private String category;
  private final CrossAppBehaviour crossAppBehaviour;
  private final Context ctx;
  private int itemId;
  private final NetworkManager networkManager;
  private String packageName;
  
  public CrossAppManager(Context paramContext, int paramInt)
  {
    this.ctx = paramContext;
    this.itemId = paramInt;
    this.networkManager = new NetworkManager(paramContext);
    this.crossAppBehaviour = CrossAppBehaviour.getInstance();
    startOtherApp();
  }
  
  public CrossAppManager(Context paramContext, int paramInt, String paramString)
  {
    this.ctx = paramContext;
    this.itemId = paramInt;
    this.networkManager = new NetworkManager(paramContext);
    this.crossAppBehaviour = CrossAppBehaviour.getInstance();
    this.category = paramString;
    startOtherApp();
  }
  
  public CrossAppManager(Context paramContext, String paramString1, String paramString2)
  {
    this.ctx = paramContext;
    this.networkManager = new NetworkManager(paramContext);
    this.crossAppBehaviour = CrossAppBehaviour.getInstance();
    this.category = paramString2;
    startOtherAppWithPackage(paramString1);
  }
  
  private boolean isAppInstalled()
  {
    Iterator localIterator = this.ctx.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(this.packageName)) {
        return true;
      }
    }
    return false;
  }
  
  private void launchApp(String paramString)
  {
    GoogleAnalyticsTracker localGoogleAnalyticsTracker = new GoogleAnalyticsTracker((Activity)this.ctx);
    if (isAppInstalled()) {
      if ((localGoogleAnalyticsTracker != null) && (localGoogleAnalyticsTracker.isOn())) {
        localGoogleAnalyticsTracker.catchOnClickEvent(this.category + " app", this.category + " app opened in device", this.category + " button", null);
      }
    }
    try
    {
      paramString = this.ctx.getPackageManager().getLaunchIntentForPackage(paramString);
      this.ctx.startActivity(paramString);
      return;
    }
    catch (Exception paramString) {}
    if ((localGoogleAnalyticsTracker != null) && (localGoogleAnalyticsTracker.isOn())) {
      localGoogleAnalyticsTracker.catchOnClickEvent(this.category + " app", this.category + " app opened on Google Play", this.category + " button", null);
    }
    openAppInPlayStore();
    return;
  }
  
  private void openAppInPlayStore()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this.packageName));
    this.ctx.startActivity(localIntent);
  }
  
  private void singleButtonAlert(Object... paramVarArgs)
  {
    AppCompatActivity localAppCompatActivity = (AppCompatActivity)this.ctx;
    MessageAlertDialog localMessageAlertDialog = new MessageAlertDialog();
    localMessageAlertDialog.setPositiveButton(true);
    localMessageAlertDialog.setMessage(paramVarArgs[0].toString());
    localMessageAlertDialog.show(localAppCompatActivity.getFragmentManager(), "downloadDialog1");
  }
  
  private void startOtherApp()
  {
    this.packageName = ((CrossAppItemProfile)this.crossAppBehaviour.getCrossAppItemlist().get(this.itemId)).getPackageName();
    showTwoButtonCustomAlert(this.packageName);
  }
  
  private void startOtherAppWithPackage(String paramString)
  {
    launchApp(paramString);
  }
  
  @SuppressLint({"CommitTransaction"})
  public void showTwoButtonCustomAlert(final String paramString)
  {
    AppCompatActivity localAppCompatActivity = (AppCompatActivity)this.ctx;
    paramString = new MessageAlertDialog(CommonStringBehavior.getInstance().getMessage(), new Callback()new Callback
    {
      public Object callback(Object... paramAnonymousVarArgs)
      {
        CrossAppManager.this.launchApp(paramString);
        return null;
      }
    }, new Callback()
    {
      public Object callback(Object... paramAnonymousVarArgs)
      {
        return null;
      }
    });
    paramString.setPositiveButtonText(CommonStringBehavior.getInstance().getYesButtonTitle());
    paramString.setNegativeButtonText(CommonStringBehavior.getInstance().getNoButtonTitle());
    paramString.show(localAppCompatActivity.getFragmentManager().beginTransaction(), "AppjumpingPopup", true);
  }
}
