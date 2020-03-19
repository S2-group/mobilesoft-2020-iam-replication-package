package com.intellijoy.plugin;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.unity3d.player.UnityPlayerActivity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MainActivity
  extends UnityPlayerActivity
{
  private static final String ALL_IN_ONE_PACKAGE_NAME = "com.intellijoy.pack";
  private static final String ALL_IN_ONE_SIGNATURE_HASH = "56:e4:66:52:de:5d:1e:0d:8b:f7:6d:b9:e3:26:42:1d";
  private static final int KITKAT_VERSION_NUMBER = 19;
  private static final int SUBSCRIPTION_STATUS_ACTIVE = 1;
  private static final int SUBSCRIPTION_STATUS_CANCELLED = 2;
  private static final int SUBSCRIPTION_STATUS_CHECK_REQUEST_CODE = 2;
  private static final String SUBSCRIPTION_STATUS_KEY = "subscription_status";
  private static final int SUBSCRIPTION_STATUS_UNKNOWN = 3;
  private static final String TAG = "intellijoy";
  private List<String> installedPackages;
  private PromoCodeActivationManager promoCodeActivationManager;
  
  public MainActivity() {}
  
  private AlertDialog createAllInOneDialog(String paramString1, String paramString2)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.setTitle(paramString1);
    localAlertDialog.setMessage(paramString2);
    localAlertDialog.setButton(-2, "Exit", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.finish();
      }
    });
    localAlertDialog.setButton(-1, "Take Me to All-in-One", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.startActivity(MainActivity.this.getPackageManager().getLaunchIntentForPackage("com.intellijoy.pack"));
        MainActivity.this.finish();
      }
    });
    localAlertDialog.setCancelable(false);
    localAlertDialog.setCanceledOnTouchOutside(false);
    return localAlertDialog;
  }
  
  private static String createFingerprint(byte[] paramArrayOfByte, String paramString)
    throws NoSuchAlgorithmException
  {
    paramString = MessageDigest.getInstance(paramString);
    paramString.update(paramArrayOfByte);
    byte[] arrayOfByte = paramString.digest();
    paramString = "";
    int i = 0;
    while (i < arrayOfByte.length)
    {
      paramArrayOfByte = paramString;
      if (i != 0) {
        paramArrayOfByte = paramString + ":";
      }
      String str = Integer.toHexString(arrayOfByte[i] & 0xFF);
      paramString = paramArrayOfByte;
      if (str.length() == 1) {
        paramString = paramArrayOfByte + "0";
      }
      paramString = paramString + str;
      i += 1;
    }
    return paramString;
  }
  
  private List<String> getInstalledPackages()
  {
    if (this.installedPackages == null) {
      initInstalledPackages();
    }
    return new ArrayList(this.installedPackages);
  }
  
  private void initInstalledPackages()
  {
    this.installedPackages = new ArrayList();
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      this.installedPackages.add(localApplicationInfo.packageName);
    }
  }
  
  private boolean isAllInOneSignatureValid()
  {
    try
    {
      boolean bool = "56:e4:66:52:de:5d:1e:0d:8b:f7:6d:b9:e3:26:42:1d".equals(createFingerprint(getPackageManager().getPackageInfo("com.intellijoy.pack", 64).signatures[0].toByteArray(), "MD5"));
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  @SuppressLint({"NewApi"})
  private void setImmersiveMode()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      findViewById(16908290).setSystemUiVisibility(5894);
    }
  }
  
  private void showAllInOneInvalidSubscriptionStatusWarning(int paramInt)
  {
    AlertDialog localAlertDialog = createAllInOneDialog("Invalid Subscription Status", "Status: " + paramInt);
    localAlertDialog.setButton(-1, null, (DialogInterface.OnClickListener)null);
    localAlertDialog.show();
  }
  
  private void showAllInOneSubscriptionRequiredWarning()
  {
    createAllInOneDialog("Subscription Required", "Looks like your All-in-One subscription is no longer valid.").show();
  }
  
  private void showAllInOneVerificationRequiredWarning()
  {
    createAllInOneDialog("All-in-One Verification Required", "Your All-in-One subscription needs to be verified first.").show();
  }
  
  private void showAllOneIsNotInstalledWarning()
  {
    AlertDialog localAlertDialog = createAllInOneDialog("Important", "Looks like you uninstalled the All-in-One app.  Please download it first.");
    localAlertDialog.setButton(-1, "Download", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.intellijoy.pack")));
        MainActivity.this.finish();
      }
    });
    localAlertDialog.show();
  }
  
  private void showInvalidSignatureWarning()
  {
    AlertDialog localAlertDialog = createAllInOneDialog("Error", "Invalid signature");
    localAlertDialog.setButton(-1, null, (DialogInterface.OnClickListener)null);
    localAlertDialog.show();
  }
  
  public void checkSubscriptionStatus()
  {
    Intent localIntent = new Intent("com.intellijoy.pack.subscription.CHECK");
    localIntent.setPackage("com.intellijoy.pack");
    try
    {
      startActivityForResult(localIntent, 2);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException) {}
  }
  
  public String getCountry()
  {
    return getResources().getConfiguration().locale.getCountry();
  }
  
  public String getLanguage()
  {
    return getResources().getConfiguration().locale.getLanguage();
  }
  
  public int getNumberOfInstalledApps(String... paramVarArgs)
  {
    List localList = getInstalledPackages();
    localList.retainAll(Arrays.asList(paramVarArgs));
    return localList.size();
  }
  
  public boolean isActivatedByPromoCode()
  {
    return this.promoCodeActivationManager.isActivated();
  }
  
  public boolean isInstalled(String paramString)
  {
    return getNumberOfInstalledApps(new String[] { paramString }) == 1;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 2)
    {
      if (paramInt2 == -1) {
        break label15;
      }
      showAllOneIsNotInstalledWarning();
    }
    label15:
    do
    {
      return;
      paramInt1 = paramIntent.getExtras().getInt("subscription_status");
      if (paramInt1 != 1) {
        break;
      }
    } while (isAllInOneSignatureValid());
    showInvalidSignatureWarning();
    return;
    if (paramInt1 == 2)
    {
      showAllInOneSubscriptionRequiredWarning();
      return;
    }
    if (paramInt1 == 3)
    {
      showAllInOneVerificationRequiredWarning();
      return;
    }
    showAllInOneInvalidSubscriptionStatusWarning(paramInt1);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    Log.d("intellijoy", "onCreate");
    super.onCreate(paramBundle);
    this.promoCodeActivationManager = new PromoCodeActivationManager(this);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    setImmersiveMode();
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  protected void onStop()
  {
    Log.d("intellijoy", "onStop");
    try
    {
      Thread.sleep(2000L);
      super.onStop();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (paramBoolean) {
      setImmersiveMode();
    }
  }
  
  public void openAppInAmazonStore(String paramString)
  {
    Log.d("intellijoy", "openAppInAmazonStore(" + paramString + ")");
    paramString = "amzn://apps/android?p=" + paramString;
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    startActivity(localIntent);
  }
  
  public void openAppInFuhuStore(String paramString)
  {
    Log.d("intellijoy", "openAppInFuhuStore(" + paramString + ")");
    Intent localIntent = new Intent("com.fuhu.appzone.SHOWAPP");
    localIntent.putExtra("id", paramString);
    sendBroadcast(localIntent);
  }
  
  public void openAppInGooglePlay(String paramString)
  {
    Log.d("intellijoy", "openAppInGooglePlay(" + paramString + ")");
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
  }
  
  public void openAppInNookStore(String paramString)
  {
    Log.d("intellijoy", "openAppInNookStore(" + paramString + ")");
    Intent localIntent = new Intent();
    localIntent.setAction("com.bn.sdk.shop.details");
    localIntent.putExtra("product_details_ean", paramString);
    startActivity(localIntent);
  }
  
  public void openAppInSamsungStore(String paramString)
  {
    Log.d("intellijoy", "openAppInSamsungStore(" + paramString + ")");
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(" samsungapps://ProductDetail/" + paramString)));
  }
  
  public void showPromoCodeActivationDialog()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.promoCodeActivationManager.showActivationDialod();
      }
    });
  }
  
  public void startAnotherAppAndFinishCurrent(String paramString)
  {
    Log.d("intellijoy", "startAnotherAppAndFinishCurrent: " + paramString);
    startActivity(getPackageManager().getLaunchIntentForPackage(paramString));
    finish();
  }
  
  public void vibrate(long paramLong)
  {
    Log.d("intellijoy", "Vibrate");
    ((Vibrator)getSystemService("vibrator")).vibrate(paramLong);
  }
}
