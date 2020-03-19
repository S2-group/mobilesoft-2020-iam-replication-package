package jp.co.disney.apps.paid_b.wmwaterapp;

import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.disney.SPP2.CallDownloadBaseApp;
import java.util.Iterator;
import java.util.List;

public class WMWDisneyBootView
  extends View
{
  static final int REQUEST_ID_IntallBaseApp = 104;
  static WMWActivity _context;
  public static String urlBaseApp = "";
  private final String BASE_APP_PACKAGE_NAME = "jp.co.disney.apps.base.disneymarketapp";
  final int REQUEST_ID_AddRegistrationID = 8;
  final int REQUEST_ID_Billing = 14;
  final int REQUEST_ID_BillingPaymentDownloadHistory = 5;
  final int REQUEST_ID_BoPay = 104;
  final int REQUEST_ID_Catalog = 2;
  final int REQUEST_ID_DeleteRegistrationID = 9;
  final int REQUEST_ID_Download = 12;
  final int REQUEST_ID_GetAdInfo = 6;
  final int REQUEST_ID_GetCarrierInfo = 17;
  final int REQUEST_ID_GetPersonKeyInfo = 7;
  final int REQUEST_ID_GetVersionInfo = 16;
  final int REQUEST_ID_Help = 15;
  final int REQUEST_ID_MemberShip = 4;
  final int REQUEST_ID_MyPage = 1;
  final int REQUEST_ID_Open = 103;
  final int REQUEST_ID_PushLogToSPP = 14;
  final int REQUEST_ID_Reminder = 3;
  final int REQUEST_ID_RestoreUser = 11;
  private final int REQUEST_ID_SPP_PATH_LOGIN = 5001;
  final int REQUEST_ID_UserActionLog = 13;
  final int REQUEST_ID_WithdrawUser = 10;
  private String myAppId = "";
  
  public WMWDisneyBootView(WMWActivity paramWMWActivity)
  {
    super(paramWMWActivity);
    _context = paramWMWActivity;
    getAppId();
    getUrlBaseApp();
    if (doesDisneyBaseAppExist())
    {
      login();
      return;
    }
    downloadBaseApp();
  }
  
  private boolean checkingInstallPackage()
  {
    List localList = _context.getPackageManager().getInstalledPackages(8192);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return false;
      }
      if (((PackageInfo)localList.get(i)).packageName.equals("jp.co.disney.apps.base.disneymarketapp")) {
        return true;
      }
      i += 1;
    }
  }
  
  private boolean doesDisneyBaseAppExist()
  {
    Iterator localIterator = _context.getPackageManager().getInstalledApplications(128).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      do
      {
        if (!localIterator.hasNext()) {
          return false;
        }
        localApplicationInfo = (ApplicationInfo)localIterator.next();
      } while (!localApplicationInfo.packageName.contains("jp.co.disney"));
      Log.d("WMW", localApplicationInfo.packageName);
    } while (localApplicationInfo.packageName.compareTo("jp.co.disney.apps.base.disneymarketapp") != 0);
    return true;
  }
  
  private void getAppId()
  {
    try
    {
      this.myAppId = _context.getPackageManager().getApplicationInfo(_context.getPackageName(), 128).metaData.getString("AppId");
      this.myAppId = "010600064";
      Log.i("WMW", "********app id: " + this.myAppId);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
  }
  
  public static void getFinishContentApp()
  {
    _context.finish();
  }
  
  public static void getSetResult(String paramString)
  {
    _context.setResult(-1);
    _context.finish();
  }
  
  public static void getStarActivityForResult(Intent paramIntent)
  {
    _context.startActivityForResult(paramIntent, 104);
  }
  
  private void login()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.putExtra("argFunc", 101);
    localIntent.putExtra("argAppId", this.myAppId);
    localIntent.setComponent(new ComponentName("jp.co.disney.apps.base.disneymarketapp", "jp.co.disney.apps.base.disneymarketapp.actBase"));
    _context.startActivityForResult(localIntent, 1);
  }
  
  private void onLoginFailedDialogClosed()
  {
    _context.onDisneyBootFailed();
  }
  
  private void onNoBaseAppDialogClosed()
  {
    _context.onDisneyBootFailed();
  }
  
  private void showLoginFailedDialog()
  {
    new AlertDialog.Builder(_context).setTitle("WHERES_MY_WATER").setMessage("LOGIN_FAILED").setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        WMWDisneyBootView.this.onLoginFailedDialogClosed();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        WMWDisneyBootView.this.onLoginFailedDialogClosed();
      }
    }).show();
  }
  
  private void showNoBaseAppDialog()
  {
    new AlertDialog.Builder(_context).setTitle("WHERES_MY_WATER").setMessage("NO_BASE_APP").setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        WMWDisneyBootView.this.onNoBaseAppDialogClosed();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        WMWDisneyBootView.this.onNoBaseAppDialogClosed();
      }
    }).show();
  }
  
  private boolean validateIntentResult(String paramString)
  {
    return (paramString.toLowerCase().contains("<Result>0".toLowerCase())) && (paramString.toLowerCase().contains("<Status>true".toLowerCase()));
  }
  
  public void downloadBaseApp()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(_context);
    localBuilder.setMessage("Do you want to install DisneyMarket ");
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new CallDownloadBaseApp();
        paramAnonymousDialogInterface.context = WMWDisneyBootView._context;
        paramAnonymousDialogInterface.execute(new Object[0]);
      }
    });
    localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          paramAnonymousDialogInterface.cancel();
          return;
        }
        catch (Throwable paramAnonymousDialogInterface)
        {
          paramAnonymousDialogInterface.printStackTrace();
        }
      }
    });
    localBuilder.show();
  }
  
  public void getUrlBaseApp()
  {
    try
    {
      urlBaseApp = _context.getPackageManager().getApplicationInfo(_context.getPackageName(), 128).metaData.getString("urlBaseApp");
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
  }
  
  public void onIntentResultReceived(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 1) && (paramInt2 == -1)) {
      if (paramIntent.getExtras() != null) {}
    }
    do
    {
      return;
      String str = paramIntent.getExtras().getString("resultXML");
      Log.e("WMW", "**** xml response: " + str);
      if (validateIntentResult(str))
      {
        _context.onDisneyBootSucceeded();
        return;
      }
      if ((paramInt1 != 4) || (paramInt2 != -1)) {
        break;
      }
    } while (paramIntent.getExtras() == null);
    paramIntent = paramIntent.getExtras().getString("resultXML");
    Log.e("WMW", "**** xml response: " + paramIntent);
    if (validateIntentResult(paramIntent))
    {
      _context.onDisneyBootSucceeded();
      return;
    }
    if (paramInt1 == 104)
    {
      paramIntent = urlBaseApp.split("/");
      paramIntent = paramIntent[(paramIntent.length - 1)];
      _context.deleteFile(paramIntent);
      return;
    }
    _context.onDestroy();
  }
}
