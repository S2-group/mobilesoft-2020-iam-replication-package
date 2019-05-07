package com.nvison.gpsmycity.master.activities;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.google.gson.Gson;
import com.nvison.gpsmycity.data.DatabaseManager;
import com.nvison.gpsmycity.data.SessionManager;
import com.nvison.gpsmycity.entity.Upgrade;
import com.nvison.gpsmycity.master.common.AppConstants;
import com.nvison.gpsmycity.master.common.CustomDialog;
import com.nvison.gpsmycity.master.common.FileUtils;
import com.nvison.gpsmycity.master.common.FileUtils.DownloadListner;
import com.nvison.gpsmycity.master.objects.BaseDo;
import com.nvison.gpsmycity.master.objects.GuideDataDo;
import com.nvison.gpsmycity.master.utils.KeyValue;
import com.nvison.gpsmycity.master.utils.SharedPrefUtils;
import com.nvison.gpsmycity.ui.AppWebViewClients;
import com.nvison.gpsmycity.util.IabHelper;
import com.nvison.gpsmycity.util.IabHelper.OnIabPurchaseFinishedListener;
import com.nvison.gpsmycity.util.IabHelper.OnIabSetupFinishedListener;
import com.nvison.gpsmycity.util.IabHelper.QueryInventoryFinishedListener;
import com.nvison.gpsmycity.util.IabResult;
import com.nvison.gpsmycity.util.Inventory;
import com.nvison.gpsmycity.util.Purchase;
import com.nvison.gpsmycity.util.Utils;
import com.nvison.gpsmycity.web.WebManager;
import com.skobbler.ngx.SKDeveloperKeyException;
import com.skobbler.ngx.SKMaps;
import com.skobbler.ngx.SKMapsInitSettings;
import com.skobbler.ngx.map.SKMapViewStyle;
import com.skobbler.ngx.navigation.SKAdvisorSettings;
import com.skobbler.ngx.navigation.SKAdvisorSettings.SKAdvisorLanguage;
import com.skobbler.ngx.packages.SKPackage;
import com.skobbler.ngx.packages.SKPackageManager;
import com.skobbler.ngx.util.SKLogging;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class UpgradActivity
  extends Activity
  implements IabHelper.OnIabSetupFinishedListener, IabHelper.OnIabPurchaseFinishedListener, View.OnClickListener, FileUtils.DownloadListner
{
  protected static final String TAG = UpgradActivity.class.getSimpleName();
  private int authenticatefor = 222;
  private boolean bindService;
  private Button btnupgrade;
  private CustomDialog dialog;
  private Dialog dialogDownload;
  private GuideDataDo guide;
  private String inappcode;
  private ImageView ivback;
  private String mGasPrice;
  IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener()
  {
    public void onQueryInventoryFinished(IabResult paramAnonymousIabResult, Inventory paramAnonymousInventory)
    {
      if ((UpgradActivity.this.mHelper != null) || (paramAnonymousInventory != null)) {}
      try
      {
        paramAnonymousInventory.hasPurchase(UpgradActivity.this.guide.inapp_code);
        return;
      }
      catch (Throwable paramAnonymousIabResult) {}
    }
  };
  IabHelper mHelper;
  private String mPremiumUpgradePrice;
  IInAppBillingService mService;
  ServiceConnection mServiceConn = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      UpgradActivity.this.mService = IInAppBillingService.Stub.asInterface(paramAnonymousIBinder);
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      UpgradActivity.this.mService = null;
    }
  };
  private String mapResourcesDir;
  private String mapResourcesDirPath;
  Thread myThread = new Thread(new Runnable()
  {
    public void run()
    {
      try
      {
        Object localObject1 = new ArrayList();
        ((ArrayList)localObject1).add("premiumUpgrade");
        ((ArrayList)localObject1).add("gas");
        Object localObject2 = new Bundle();
        ((Bundle)localObject2).putStringArrayList("ITEM_ID_LIST", (ArrayList)localObject1);
        localObject1 = UpgradActivity.this.mService.getSkuDetails(3, UpgradActivity.this.getPackageName(), "inapp", (Bundle)localObject2);
        if (((Bundle)localObject1).getInt("RESPONSE_CODE") == 0)
        {
          localObject1 = ((Bundle)localObject1).getStringArrayList("DETAILS_LIST").iterator();
          for (;;)
          {
            if (!((Iterator)localObject1).hasNext()) {
              return;
            }
            localObject2 = new JSONObject((String)((Iterator)localObject1).next());
            UpgradActivity.this.sku = ((JSONObject)localObject2).getString("productId");
            localObject2 = ((JSONObject)localObject2).getString("price");
            if (UpgradActivity.this.sku.equals("premiumUpgrade")) {
              UpgradActivity.this.mPremiumUpgradePrice = ((String)localObject2);
            } else if (UpgradActivity.this.sku.equals("gas")) {
              UpgradActivity.this.mGasPrice = ((String)localObject2);
            }
          }
        }
        return;
      }
      catch (Exception localException) {}
    }
  });
  private ProgressBar progressBar;
  protected String promoCode;
  private SessionManager sessionmanager;
  private String sku;
  private Timer timer;
  private TextView tvProgress;
  private TextView tvtextupgrade;
  private TextView tvupgradedesc;
  private TextView tvwhyregister;
  
  public UpgradActivity() {}
  
  private void changeAppVersion()
  {
    Upgrade localUpgrade = new Upgrade();
    localUpgrade.setId(1);
    try
    {
      String str = new Sha1Hex().makeSHA1Hash("ept" + this.guide.getinappcode() + "dbp");
      if (DatabaseManager.getInstance().upgradecatalog(this.guide, str.substring(9, str.length() - 1))) {
        localUpgrade.setVersion(str);
      }
      Utils.setAPP_VERSION(true);
      DatabaseManager.getInstance(this).updateUpgradeTable(localUpgrade);
      overridePendingTransition(2130968578, 2130968579);
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        localNoSuchAlgorithmException.printStackTrace();
      }
    }
  }
  
  private Intent getExplicitIapIntent()
  {
    Object localObject = getApplicationContext().getPackageManager();
    Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
    localIntent.setPackage("com.android.vending");
    localObject = ((PackageManager)localObject).queryIntentServices(localIntent, 0);
    if ((localObject == null) || (((List)localObject).size() != 1)) {
      return null;
    }
    localObject = (ResolveInfo)((List)localObject).get(0);
    localObject = new ComponentName(((ResolveInfo)localObject).serviceInfo.packageName, ((ResolveInfo)localObject).serviceInfo.name);
    localIntent = new Intent();
    localIntent.setComponent((ComponentName)localObject);
    return localIntent;
  }
  
  private void initPlaystore()
  {
    try
    {
      Bundle localBundle = this.mService.getBuyIntent(3, getPackageName(), this.guide.inapp_code, "inapp", null);
      int i = localBundle.getInt("RESPONSE_CODE");
      if (i == 0)
      {
        startIntentSenderForResult(((PendingIntent)localBundle.getParcelable("BUY_INTENT")).getIntentSender(), 1001, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
        return;
      }
      if (i == 7)
      {
        downloadskmfile();
        changeAppVersion();
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
      return;
    }
    catch (IntentSender.SendIntentException localSendIntentException)
    {
      localSendIntentException.printStackTrace();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private void popup()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Add Gmail account");
    localBuilder.setMessage("These options rely on a Gmail account, but you don't seem to have one configured. Would you like to configure one now?");
    localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AccountManager.get(UpgradActivity.this.getApplicationContext()).addAccount("com.google", null, null, null, UpgradActivity.this, null, null);
      }
    });
    localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  @SuppressLint({"NewApi"})
  private void showDownloadProgressBar()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        View localView = LayoutInflater.from(UpgradActivity.this).inflate(2130903173, null);
        UpgradActivity.this.progressBar = ((ProgressBar)localView.findViewById(2131493286));
        UpgradActivity.this.tvProgress = ((TextView)localView.findViewById(2131493279));
        if (UpgradActivity.this.dialogDownload == null)
        {
          UpgradActivity.this.dialogDownload = new Dialog(UpgradActivity.this);
          UpgradActivity.this.dialogDownload.getWindow().setBackgroundDrawable(new ColorDrawable(0));
          UpgradActivity.this.dialogDownload.requestWindowFeature(1);
          UpgradActivity.this.dialogDownload.setCancelable(false);
        }
        UpgradActivity.this.dialogDownload.setContentView(localView, new ViewGroup.LayoutParams(700, -2));
        UpgradActivity.this.dialogDownload.getWindow().setGravity(17);
        UpgradActivity.this.progressBar.setMax(100);
        UpgradActivity.this.progressBar.setProgress(0);
        UpgradActivity.this.tvProgress.setText("0 %");
        UpgradActivity.this.dialogDownload.show();
      }
    });
  }
  
  private void showPromoCodePopup()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2130903174);
    localDialog.show();
    localDialog.findViewById(2131493297).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
        paramAnonymousView = (EditText)localDialog.findViewById(2131493773);
        UpgradActivity.this.promoCode = paramAnonymousView.getText().toString();
        if ((!UpgradActivity.this.promoCode.equals("")) && (UpgradActivity.this.promoCode != null))
        {
          if (Utils.isNetworkAvailable(UpgradActivity.this))
          {
            WebManager.getInstance().getauthenticate(UpgradActivity.this, null, 1, true);
            return;
          }
          UpgradActivity.this.showcustomdialog("NO INTERNET CONNECTION", 2130903110);
          return;
        }
        Toast.makeText(UpgradActivity.this, 2131165327, 0).show();
      }
    });
    localDialog.findViewById(2131493291).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
  }
  
  private void showRestoreAppDialog()
  {
    Object localObject1 = new Dialog(this);
    ((Dialog)localObject1).requestWindowFeature(1);
    ((Dialog)localObject1).setContentView(2130903178);
    Object localObject2 = new WindowManager.LayoutParams();
    ((WindowManager.LayoutParams)localObject2).copyFrom(((Dialog)localObject1).getWindow().getAttributes());
    ((WindowManager.LayoutParams)localObject2).width = -2;
    ((WindowManager.LayoutParams)localObject2).height = -2;
    ((Dialog)localObject1).show();
    ((Dialog)localObject1).getWindow().setAttributes((WindowManager.LayoutParams)localObject2);
    ((Button)((Dialog)localObject1).findViewById(2131493781)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        this.val$dialog.dismiss();
      }
    });
    WebView localWebView = (WebView)((Dialog)localObject1).findViewById(2131493783);
    localWebView.setWebViewClient(new AppWebViewClients((ProgressBar)((Dialog)localObject1).findViewById(2131493784)));
    localWebView.getSettings().setBuiltInZoomControls(false);
    localWebView.getSettings().setSupportZoom(false);
    localWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    localWebView.getSettings().setAllowFileAccess(true);
    localWebView.getSettings().setDomStorageEnabled(true);
    localObject2 = new BufferedInputStream(getResources().openRawResource(2131099652));
    localObject1 = null;
    try
    {
      localObject2 = IOUtils.toString((InputStream)localObject2, "UTF-8");
      localObject1 = localObject2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
    localWebView.loadData((String)localObject1, "text/html", "UTF-8");
    localWebView.setBackgroundColor(0);
    localWebView.setLayerType(1, null);
  }
  
  private void showUpgradePopup()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2130903235);
    TextView localTextView = (TextView)localDialog.findViewById(2131494041);
    String[] arrayOfString = getResources().getString(2131165283).split("%");
    String str = arrayOfString[0];
    for (;;)
    {
      try
      {
        if (!this.guide.getinappprice().equalsIgnoreCase("0.99")) {
          continue;
        }
        localTextView.setText(str.concat("$ 1.99").concat(arrayOfString[1]));
        localDialog.show();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        continue;
      }
      localDialog.findViewById(2131494042).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localDialog.dismiss();
          UpgradActivity.this.initPlaystore();
        }
      });
      localDialog.findViewById(2131493291).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localDialog.dismiss();
        }
      });
      localDialog.findViewById(2131494043).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localDialog.dismiss();
          UpgradActivity.this.showRestoreAppDialog();
        }
      });
      localDialog.findViewById(2131494044).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localDialog.dismiss();
          UpgradActivity.this.showPromoCodePopup();
        }
      });
      return;
      localTextView.setText(str.concat("$ " + this.guide.getinappprice()).concat(arrayOfString[1]));
    }
  }
  
  public boolean downloadskm(String paramString1, String paramString2, FileUtils.DownloadListner paramDownloadListner)
  {
    if ((new File(AppConstants.DATABASE_PATH, paramString2).exists()) || (!TextUtils.isEmpty(FileUtils.downloadSQLITE(paramString1, AppConstants.DATABASE_PATH, this, paramString2, paramDownloadListner)))) {
      return true;
    }
    if (paramDownloadListner != null) {
      paramDownloadListner.onError();
    }
    return false;
  }
  
  public void downloadskmfile()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        String str1 = UpgradActivity.this.guide.getskmfile();
        int i = UpgradActivity.this.guide.getid();
        final String str2 = "SKM" + String.valueOf(i);
        UpgradActivity.this.initializeLibrary(UpgradActivity.this);
        SKPackage[] arrayOfSKPackage = SKPackageManager.getInstance().getInstalledPackages();
        int j = 0;
        i = 0;
        for (;;)
        {
          if (i >= arrayOfSKPackage.length)
          {
            i = j;
            label76:
            if (i != 0) {
              break;
            }
          }
          else
          {
            try
            {
              UpgradActivity.this.showDownloadProgressBar();
              if (UpgradActivity.this.downloadskm(str1, str2, UpgradActivity.this))
              {
                UpgradActivity.this.runOnUiThread(new Runnable()
                {
                  private void changeAppVersion() {}
                  
                  public void run()
                  {
                    SKPackageManager.getInstance().addOfflinePackage(AppConstants.DATABASE_PATH + "Travelarticles/", str2);
                    KeyValue localKeyValue = new KeyValue(UpgradActivity.this.guide.getid(), "true");
                    SharedPrefUtils.setValue(UpgradActivity.this, SharedPrefUtils.TRAVEL_ARTICLE_PROMO, localKeyValue);
                  }
                });
                return;
                if (str2.equalsIgnoreCase(arrayOfSKPackage[i].getName()))
                {
                  i = 1;
                  break label76;
                }
                i += 1;
              }
              else
              {
                UpgradActivity.this.runOnUiThread(new Runnable()
                {
                  public void run()
                  {
                    Utils.showBasicOkDialog(null, "No Internet connection. Map is not available, please connect to the Internet to download the map", null, "Ok", UpgradActivity.this);
                  }
                });
                return;
              }
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
              return;
            }
          }
        }
        Object localObject = new KeyValue(UpgradActivity.this.guide.getid(), "true");
        SharedPrefUtils.setValue(UpgradActivity.this, SharedPrefUtils.TRAVEL_ARTICLE_PROMO, (KeyValue)localObject);
        localObject = new Intent();
        UpgradActivity.this.setResult(1, (Intent)localObject);
        UpgradActivity.this.finish();
      }
    }).start();
  }
  
  public boolean initializeLibrary(Context paramContext)
  {
    SKLogging.enableLogs(true);
    this.mapResourcesDir = new SessionManager(paramContext).getMapPath();
    if (this.mapResourcesDir.equals("")) {
      this.mapResourcesDir = (this.mapResourcesDir + File.separator + "PreinstalledMaps" + File.separator + "v1" + File.separator + "20150413" + File.separator + "package");
    }
    SKMapsInitSettings localSKMapsInitSettings = new SKMapsInitSettings();
    localSKMapsInitSettings.setMapResourcesPaths(this.mapResourcesDir, new SKMapViewStyle(this.mapResourcesDir + "daystyle/", "daystyle.json"));
    SKAdvisorSettings localSKAdvisorSettings = localSKMapsInitSettings.getAdvisorSettings();
    localSKAdvisorSettings.setAdvisorConfigPath(this.mapResourcesDir + "/Advisor");
    localSKAdvisorSettings.setResourcePath(this.mapResourcesDir + "/Advisor/Languages");
    localSKAdvisorSettings.setLanguage(SKAdvisorSettings.SKAdvisorLanguage.LANGUAGE_EN);
    localSKAdvisorSettings.setAdvisorVoice("en");
    localSKMapsInitSettings.setAdvisorSettings(localSKAdvisorSettings);
    localSKMapsInitSettings.setPreinstalledMapsPath(this.mapResourcesDir + "/PreinstalledMaps");
    localSKMapsInitSettings.setConnectivityMode(2);
    localSKMapsInitSettings.setMapDetailLevel("full/");
    try
    {
      SKMaps.getInstance().initializeSKMaps(paramContext, localSKMapsInitSettings);
      System.out.println("");
      return true;
    }
    catch (SKDeveloperKeyException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1001) && (paramIntent != null) && (paramInt2 == -1))
    {
      downloadskmfile();
      changeAppVersion();
    }
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    finish();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    if (Utils.isNetworkAvailable(this))
    {
      showUpgradePopup();
      return;
    }
    Utils.showBasicOkDialog(null, "Request aborted. You must connect to internet.", null, "Ok", this);
  }
  
  public void onComplete()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          if ((!UpgradActivity.this.isFinishing()) && (UpgradActivity.this.dialogDownload != null) && (UpgradActivity.this.dialogDownload.isShowing()))
          {
            UpgradActivity.this.dialogDownload.dismiss();
            Intent localIntent = new Intent();
            UpgradActivity.this.setResult(1, localIntent);
            UpgradActivity.this.finish();
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903238);
    this.tvtextupgrade = ((TextView)findViewById(2131494026));
    this.tvwhyregister = ((TextView)findViewById(2131494025));
    this.tvupgradedesc = ((TextView)findViewById(2131494048));
    this.ivback = ((ImageView)findViewById(2131492877));
    this.btnupgrade = ((Button)findViewById(2131494049));
    this.tvupgradedesc.setText(Html.fromHtml(getResources().getString(2131165354)));
    this.tvtextupgrade.setTypeface(AppConstants.Iowan_Old_Style_ROMAN);
    this.tvupgradedesc.setTypeface(AppConstants.Roboto_Regular);
    this.tvwhyregister.setTypeface(AppConstants.SFUIDisplay_Semibold);
    if (getIntent().hasExtra("guide")) {
      this.guide = ((GuideDataDo)getIntent().getExtras().getSerializable("guide"));
    }
    if (this.guide.getinappprice().equalsIgnoreCase("0.99")) {
      this.btnupgrade.setText("Upgrade Now for $ 1.99");
    }
    for (;;)
    {
      this.sessionmanager = new SessionManager(this);
      AppConstants.DATABASE_PATH = getFilesDir().toString() + "/";
      setTouchAndClickListener(this.btnupgrade);
      this.btnupgrade.setOnClickListener(this);
      if (getIntent() != null) {
        this.inappcode = getIntent().getStringExtra("inappcode");
      }
      this.ivback.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          UpgradActivity.this.finish();
        }
      });
      try
      {
        this.mHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxslYVrSnr9esdHCBShjd5f9+VQJDF6MpoaMnLBcG8Biy/jkGATuoP04Wq68vaN+azuEjZIFjser5mBegwabMg+7UhW4j1omSlK7Wwp15ua0KP8YQ8vzOYxuIEPdy4MO1c1itZ7wxRRHzz7upPVvxu3q9O3NR+n2RqkfTTYOyUBAxa2///4q3IoqoBpibc1EHH2/WZAITWrcxcHtrycO/IcGPz3uYR7QjmDASRJMqBu7ASAY1wvaXcKM1dsZJ2ujtkuD0XbHkr4DlaVb1kEA6QsqVXsNkWkqvNPNgEiPGO7x59oq3uFpYZOr6xvj63+nDwr1jTVS2kfRgbO31pGE4VQIDAQAB");
        this.mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener()
        {
          public void onIabSetupFinished(IabResult paramAnonymousIabResult)
          {
            if (!paramAnonymousIabResult.isSuccess()) {
              Log.d(UpgradActivity.TAG, "Problem setting up In-app Billing: " + paramAnonymousIabResult);
            }
            if (UpgradActivity.this.mHelper != null) {
              UpgradActivity.this.mHelper.queryInventoryAsync(UpgradActivity.this.mGotInventoryListener);
            }
          }
        });
        if (Build.VERSION.SDK_INT > 19)
        {
          paramBundle = getExplicitIapIntent();
          this.bindService = getApplicationContext().bindService(paramBundle, this.mServiceConn, 1);
          if (!this.bindService) {
            break label435;
          }
          Log.i(TAG, "Market Billing Service Successfully Bound");
          return;
          this.btnupgrade.setText("Upgrade Now for $ " + this.guide.getinappprice());
        }
      }
      catch (Throwable paramBundle)
      {
        for (;;)
        {
          Utils.showBasicOkDialog("Error!!", "Can't Detect any Google Account On Device", "Ok", null, this);
          continue;
          paramBundle = new Intent("com.android.vending.billing.InAppBillingService.BIND");
          paramBundle.setPackage("com.android.vending");
        }
        label435:
        Log.e(TAG, "Market Billing Service could not be bound.");
      }
    }
  }
  
  public void onError()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          if ((!UpgradActivity.this.isFinishing()) && (UpgradActivity.this.dialogDownload != null) && (UpgradActivity.this.dialogDownload.isShowing())) {
            UpgradActivity.this.dialogDownload.dismiss();
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  public void onIabPurchaseFinished(IabResult paramIabResult, Purchase paramPurchase)
  {
    Log.d(TAG, "Purchase finished: " + paramIabResult + ", purchase: " + paramPurchase);
    if (paramIabResult.isFailure()) {
      return;
    }
    Log.d(TAG, "Purchase successful.");
  }
  
  public void onIabSetupFinished(IabResult paramIabResult)
  {
    if (paramIabResult.isSuccess())
    {
      Log.d("In-app Billing set up" + paramIabResult, this.guide.inapp_code);
      return;
    }
    Log.d("Problem setting up In-app Billing: " + paramIabResult, this.guide.inapp_code);
  }
  
  public void onProgrss(final int paramInt)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (UpgradActivity.this.dialogDownload != null)
        {
          UpgradActivity.this.progressBar.setProgress(paramInt);
          UpgradActivity.this.tvProgress.setText(paramInt + " %");
        }
      }
    });
  }
  
  protected void onRestart()
  {
    super.onRestart();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  public void processResponse(String paramString, int paramInt)
  {
    if (paramInt == 1)
    {
      paramString = (BaseDo)new Gson().fromJson(paramString, BaseDo.class);
      if (!paramString.getSid().equals("")) {}
    }
    while ((paramInt != 222) || (paramString == null) || (paramString.equals("")))
    {
      return;
      this.sessionmanager.persistSid(paramString.getSid());
      WebManager.getInstance().upgradeApp_new(this, 222, this.promoCode, this.guide.getid(), true);
      return;
    }
    if (((BaseDo)new Gson().fromJson(paramString, BaseDo.class)).getStatus().equals("1"))
    {
      downloadskmfile();
      return;
    }
    showcustomdialog("Promo code is invalid or expired", 2130903110);
  }
  
  public void setTouchAndClickListener(View paramView)
  {
    paramView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        }
        for (;;)
        {
          return false;
          paramAnonymousView.setAlpha(0.5F);
          continue;
          paramAnonymousView.setAlpha(0.5F);
          continue;
          paramAnonymousView.setAlpha(1.0F);
        }
      }
    });
  }
  
  public void showcustomdialog(String paramString, int paramInt)
  {
    this.dialog = new CustomDialog(this, null, paramString, paramInt, 0);
    this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    if (paramInt == 2130903110)
    {
      paramString = this.dialog.getWindow().getAttributes();
      paramString.gravity = 48;
      paramString.width = -1;
      paramString.flags &= 0xFFFFFFFD;
      this.dialog.getWindow().setAttributes(paramString);
      this.dialog.getWindow().getAttributes().windowAnimations = 2131230732;
    }
    try
    {
      if (!this.dialog.isShowing()) {
        this.dialog.show();
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public class Sha1Hex
  {
    public Sha1Hex() {}
    
    public String makeSHA1Hash(String paramString)
      throws NoSuchAlgorithmException
    {
      Object localObject = MessageDigest.getInstance("SHA1");
      ((MessageDigest)localObject).reset();
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      paramString = "";
      int i = 0;
      for (;;)
      {
        if (i >= localObject.length) {
          return paramString;
        }
        paramString = paramString + Integer.toString((localObject[i] & 0xFF) + 256, 16).substring(1);
        i += 1;
      }
    }
  }
}
