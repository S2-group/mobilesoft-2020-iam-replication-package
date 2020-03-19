package com.xiaomi.gamecenter.sdk;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.xiaomi.game.plugin.stat.MiGamePluginStat;
import com.xiaomi.gamecenter.sdk.entry.AccountStatus;
import com.xiaomi.gamecenter.sdk.entry.CardBuyInfo;
import com.xiaomi.gamecenter.sdk.entry.LoginType;
import com.xiaomi.gamecenter.sdk.entry.MiAccountInfo;
import com.xiaomi.gamecenter.sdk.entry.MiAppInfo;
import com.xiaomi.gamecenter.sdk.entry.MiBuyInfo;
import com.xiaomi.gamecenter.sdk.entry.MiBuyInfoOffline;
import com.xiaomi.gamecenter.sdk.entry.MiBuyInfoOnline;
import com.xiaomi.gamecenter.sdk.entry.ScreenOrientation;
import com.xiaomi.gamecenter.sdk.gam.MiliaoInfo;
import com.xiaomi.gamecenter.sdk.pay.PaySDK;
import com.xiaomi.gamecenter.sdk.utils.HyUtils;
import com.xiaomi.gamecenter.sdk.utils.ReporterUtils;
import com.xiaomi.gamecenter.sdk.utils.RunEnvironmentCheck;
import com.xiaomi.gamecenter.sdk.utils.k;
import com.xiaomi.licensinglibrary.model.ProductCatalog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class MiCommplatform
{
  private static final long DEFAULT_TIMEOUT_MILLIS = 30000L;
  private static final String MI_PRODUCTCATALOG = "MiGameProductCatalog.prop";
  private static final String PERMISSION_GETACCOUNTS = "android.permission.GET_ACCOUNTS";
  private static final String SERVICE_FILE_NAME = "MiGameCenterSDKService.apk";
  private static final String SERVICE_NAME = "com.xiaomi.gamecenter.sdk.service";
  private static final String SERVICE_PKG_NAME = "com.xiaomi.gamecenter.sdk.service";
  private static boolean debugLogin = false;
  private static boolean debugTierCheck = true;
  private static volatile MiCommplatform sInstance;
  private Object _Lock_ = new Object();
  private Object _check_service_lock_;
  private SdkAccountAdapter accountAdapter;
  private Activity activity;
  private MiAppInfo appInfo;
  private ServiceConnection checkLoginConnection = new ai(this);
  private volatile int connect_flag = Integer.MIN_VALUE;
  private ServiceConnection connection = new aa(this);
  private Context ctx;
  private IGameCenterSDK loginSdk;
  private Context mApplication;
  private boolean mTouch = false;
  private MiliaoInfo miliaoInfo = null;
  private ServiceConnection openAppConnection = new ag(this);
  private IGameCenterSDK sdk;
  private IServiceCallback serviceCallback = new MiCommplatform.5(this);
  private int service_isntall_ask;
  private long systemTime;
  private Handler toastHandler = new af(this, Looper.getMainLooper());
  private String useHeartToken = null;
  
  private MiCommplatform(Context paramContext, MiAppInfo paramMiAppInfo)
  {
    RunEnvironmentCheck.a(paramContext);
    RunEnvironmentCheck.b(paramContext);
    this.ctx = paramContext;
    this.mApplication = paramContext.getApplicationContext();
    this.appInfo = paramMiAppInfo;
    this.appInfo.setSocialGame(false);
    ReporterUtils.init(paramContext, paramMiAppInfo.getAppId());
    aq.a(paramContext);
    this.accountAdapter = new SdkAccountAdapter(paramContext);
    if ((isSdkServiceExist(paramContext)) && (checkServiceVersion(paramContext)) && (HyUtils.a(paramContext)))
    {
      this.accountAdapter.a(AccountStatus.SERVICE);
      openAppForInit();
    }
    for (;;)
    {
      if (HyUtils.b()) {
        readProductCatalog();
      }
      return;
      this.accountAdapter.a(AccountStatus.LOCAL);
      PaySDK.a(paramContext, this.appInfo.getAppId(), this.appInfo.getAppKey());
    }
  }
  
  public static void Init(Context paramContext, MiAppInfo paramMiAppInfo)
  {
    if (sInstance == null)
    {
      if (paramMiAppInfo == null) {
        throw new NullPointerException("MiAppInfo is Null");
      }
      sInstance = new MiCommplatform(paramContext, paramMiAppInfo);
      return;
    }
    sInstance.appInfo = paramMiAppInfo;
  }
  
  private String analysisProductCatalog(MiBuyInfo paramMiBuyInfo)
  {
    if (HyUtils.b.containsKey(paramMiBuyInfo.getProductCode()))
    {
      ProductCatalog localProductCatalog = (ProductCatalog)HyUtils.b.get(paramMiBuyInfo.getProductCode());
      paramMiBuyInfo = "You're buying " + localProductCatalog.a() + " * " + paramMiBuyInfo.getCount() + " Total:RMB￥" + String.format("%.2f", new Object[] { Double.valueOf(localProductCatalog.b() * paramMiBuyInfo.getCount() / 100.0D) });
      if (localProductCatalog.c().equalsIgnoreCase("Non-Consumable")) {
        return paramMiBuyInfo + ".In the formal environment this props can not be repeated purchase!";
      }
    }
    else
    {
      return null;
    }
    return paramMiBuyInfo;
  }
  
  private boolean checkPermission(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName());
      if (i == 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private void chmod(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = "chmod " + paramString1 + " " + paramString2;
      Runtime.getRuntime().exec(paramString1);
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  private boolean export_install_service_apk()
  {
    FileOutputStream localFileOutputStream;
    try
    {
      localObject = this.ctx.getAssets().open("MiGameCenterSDKService.apk");
      File localFile = new File(this.ctx.getFilesDir(), "MiGameCenterSDKService.apk");
      localFileOutputStream = this.ctx.openFileOutput("MiGameCenterSDKService.apk", 1);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = ((InputStream)localObject).read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
      localFileOutputStream.flush();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return false;
    }
    localFileOutputStream.close();
    ((InputStream)localObject).close();
    chmod("777", localException.getAbsolutePath());
    Object localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).addFlags(268435456);
    ((Intent)localObject).setDataAndType(Uri.parse("file://" + localException.getAbsolutePath()), "application/vnd.android.package-archive");
    this.ctx.startActivity((Intent)localObject);
    return true;
  }
  
  public static MiCommplatform getInstance()
  {
    if (sInstance == null) {
      throw new IllegalStateException("Please call MiCommplatform.Init () in application onCreate() and the MiappInfo parameter can not be null");
    }
    return sInstance;
  }
  
  private boolean install_service_apk(Context paramContext, boolean paramBoolean, String arg3)
  {
    if ((!TextUtils.isEmpty(???)) && (paramBoolean)) {
      ReporterUtils.getInstance().xmsdkReport(???, 2016);
    }
    this.service_isntall_ask = 0;
    this._check_service_lock_ = new Object();
    new am(this, Looper.getMainLooper(), paramBoolean, paramContext, ???).sendEmptyMessage(0);
    synchronized (this._check_service_lock_)
    {
      try
      {
        this._check_service_lock_.wait();
        if (this.service_isntall_ask != 1)
        {
          this._check_service_lock_ = null;
          return false;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
    if (!export_install_service_apk()) {
      return false;
    }
    try
    {
      Thread.sleep(1000L);
      while (!isTopActivity((Activity)paramContext)) {
        try
        {
          Thread.sleep(100L);
        }
        catch (InterruptedException ???)
        {
          ???.printStackTrace();
        }
      }
    }
    catch (InterruptedException ???)
    {
      for (;;)
      {
        ???.printStackTrace();
      }
    }
    return isSdkServiceExist(paramContext);
  }
  
  private void miCardPay(Activity paramActivity, CardBuyInfo paramCardBuyInfo, OnCardPayProcessListener paramOnCardPayProcessListener)
    throws RemoteException
  {
    if (this.mTouch)
    {
      paramOnCardPayProcessListener.finishCardPayProcess(47530);
      return;
    }
    this.mTouch = true;
    new al(this, paramActivity, paramOnCardPayProcessListener, paramCardBuyInfo).start();
  }
  
  private void miLogout(OnLoginProcessListener paramOnLoginProcessListener)
  {
    if (this.mTouch)
    {
      paramOnLoginProcessListener.finishLoginProcess(47530, null);
      return;
    }
    this.mTouch = true;
    new aj(this, paramOnLoginProcessListener).start();
  }
  
  private void miWindow(OnPayProcessListener paramOnPayProcessListener)
  {
    if (this.mTouch)
    {
      paramOnPayProcessListener.finishPayProcess(47530);
      return;
    }
    this.mTouch = true;
    new ak(this, paramOnPayProcessListener).start();
  }
  
  private void openApp(MiAppInfo paramMiAppInfo)
  {
    try
    {
      IGameCenterSDK localIGameCenterSDK = this.sdk;
      if (localIGameCenterSDK == null) {
        return;
      }
      this.sdk.openAppReportForInit(paramMiAppInfo, this.ctx.getPackageName());
      return;
    }
    catch (RemoteException paramMiAppInfo)
    {
      paramMiAppInfo.printStackTrace();
      return;
    }
    finally
    {
      this.ctx.getApplicationContext().unbindService(this.openAppConnection);
    }
  }
  
  private void openAppForInit()
  {
    if (!isSdkServiceExist(this.ctx)) {}
    for (;;)
    {
      return;
      Object localObject = this.ctx.getPackageManager();
      try
      {
        if (((PackageManager)localObject).getPackageInfo("com.xiaomi.gamecenter.sdk.service", 64).versionCode >= 43070)
        {
          localObject = new Intent("com.xiaomi.gamecenter.sdk.service");
          ((Intent)localObject).setPackage("com.xiaomi.gamecenter.sdk.service");
          this.ctx.getApplicationContext().bindService((Intent)localObject, this.openAppConnection, 1);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private String readProductCatalog()
  {
    for (;;)
    {
      int i;
      String str1;
      Object localObject;
      String str2;
      String str3;
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(this.ctx.getAssets().open("MiGameProductCatalog.prop"), "UTF-8"));
        i = 0;
        str1 = localBufferedReader.readLine();
        if (str1 != null)
        {
          if (i == 0)
          {
            i += 1;
            continue;
          }
          localObject = str1.split("\t");
          if (11 != localObject.length) {
            break label355;
          }
          str1 = localObject[0];
          str2 = localObject[1];
          str3 = localObject[2];
          if ((!HyUtils.b.containsKey(str1)) && (str1.length() > 0)) {
            break label121;
          }
          throw new IllegalStateException("productId must be unique in MiProductCatalog.prop");
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
      label121:
      if ("published".equalsIgnoreCase(str3))
      {
        if (("update".equalsIgnoreCase(str2)) || ("append".equalsIgnoreCase(str2)))
        {
          localObject = new ProductCatalog(localObject[0], localObject[2], localObject[3], localObject[4], localObject[5], localObject[6], localObject[7], localObject[8], localObject[9], localObject[10]);
          if (((ProductCatalog)localObject).d() <= 0)
          {
            Log.e("MiCommplatform", "Whole sale price tier must above 0");
            continue;
          }
          if ((debugTierCheck) && (((ProductCatalog)localObject).d() < 92) && (com.xiaomi.licensinglibrary.util.Constants.a[localObject.d()] != ((ProductCatalog)localObject).b()))
          {
            Log.e("MiCommplatform", "Whole sale price tier not match price. pt：" + ((ProductCatalog)localObject).d() + " price:" + ((ProductCatalog)localObject).b());
            continue;
          }
          HyUtils.b.put(str1, localObject);
        }
        else if ("delete".equalsIgnoreCase(str2))
        {
          Log.w("MiCommplatform", " Delete operation will be ignore in debug mode ");
        }
      }
      else {
        Log.w("MiCommplatform", "Not published product will be ignore in debug mode ");
      }
      label355:
      i += 1;
    }
  }
  
  public static void setDebugPriceTierCheck(boolean paramBoolean)
  {
    debugTierCheck = paramBoolean;
  }
  
  public static void setNeedCheckPayment(boolean paramBoolean)
  {
    RunEnvironmentCheck.a = paramBoolean;
  }
  
  public static void setUnityDebug(boolean paramBoolean)
  {
    HyUtils.a(paramBoolean);
  }
  
  public final boolean canOpenEntrancePage()
  {
    if (!isSdkServiceExist(this.ctx)) {}
    for (;;)
    {
      return false;
      PackageManager localPackageManager = this.ctx.getPackageManager();
      try
      {
        int i = localPackageManager.getPackageInfo("com.xiaomi.gamecenter.sdk.service", 64).versionCode;
        if (i >= 8) {
          return true;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    return false;
  }
  
  protected final boolean checkServiceVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo("com.xiaomi.gamecenter.sdk.service", 64).versionCode;
      int j = PluginVersionCode.a();
      if (i >= j) {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  protected final int check_and_connect(Context paramContext, boolean paramBoolean)
  {
    return check_and_connect(paramContext, paramBoolean, null);
  }
  
  protected final int check_and_connect(Context paramContext, boolean paramBoolean, String arg3)
  {
    if ((paramContext instanceof Activity)) {
      this.activity = ((Activity)paramContext);
    }
    if (this.appInfo == null)
    {
      Log.e("MiCommplatform", "Please call MiCommplatform.Init () in application onCreate() and the MiappInfo parameter can not be null");
      return -1;
    }
    synchronized (this._Lock_)
    {
      this.connect_flag = Integer.MIN_VALUE;
      Intent localIntent = new Intent("com.xiaomi.gamecenter.sdk.service");
      localIntent.setPackage("com.xiaomi.gamecenter.sdk.service");
      paramContext.getApplicationContext().bindService(localIntent, this.connection, 1);
      try
      {
        this._Lock_.wait();
        if (this.sdk != null)
        {
          Log.i(">>>", "sdk.ConnService");
          return this.connect_flag;
        }
      }
      catch (InterruptedException paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
        }
      }
    }
    return -1;
  }
  
  protected final boolean check_user_changed(Activity paramActivity, int paramInt)
  {
    boolean bool = false;
    if (paramInt == -51)
    {
      this.appInfo.setAccount(null);
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity);
      localBuilder.setTitle("提示");
      localBuilder.setMessage("系统账户异常，请退出后重新登录！");
      localBuilder.setCancelable(false);
      localBuilder.setPositiveButton("确定", new ac(this));
      paramActivity.runOnUiThread(new ad(this, localBuilder));
      bool = true;
    }
    return bool;
  }
  
  protected final void disconnect()
  {
    try
    {
      if (this.sdk == null) {
        return;
      }
      this.ctx.getApplicationContext().unbindService(this.connection);
      this.sdk.unregistCallBack(this.serviceCallback, getVersion());
      Intent localIntent = new Intent("com.xiaomi.gamecenter.sdk.service");
      localIntent.setPackage("com.xiaomi.gamecenter.sdk.service");
      boolean bool = this.ctx.getApplicationContext().stopService(localIntent);
      this.sdk = null;
      Log.i(">>>>", "disconnect:" + bool);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public final String generateCpOrderId()
  {
    return getMD5(UUID.randomUUID().toString().getBytes());
  }
  
  final SdkAccountAdapter getAccountAdapter()
  {
    return this.accountAdapter;
  }
  
  public final Context getApplicationContext()
  {
    return this.mApplication;
  }
  
  final String getMD5(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null) {
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramArrayOfByte);
        paramArrayOfByte = new StringBuffer();
        localObject = ((MessageDigest)localObject).digest();
        int i = 0;
        while (i < localObject.length)
        {
          int k = localObject[i];
          int j = k;
          if (k < 0) {
            j = k + 256;
          }
          if (j < 25) {
            paramArrayOfByte.append("0");
          }
          paramArrayOfByte.append(Integer.toHexString(j));
          i += 1;
        }
        paramArrayOfByte = paramArrayOfByte.toString().substring(5, 30);
        return paramArrayOfByte;
      }
      catch (NoSuchAlgorithmException paramArrayOfByte)
      {
        paramArrayOfByte.printStackTrace();
      }
    }
    return null;
  }
  
  public final MiAccountInfo getMiAccountInfo()
  {
    if (this.appInfo != null) {
      return this.appInfo.getAccount();
    }
    return null;
  }
  
  public final MiAppInfo getMiAppInfo()
  {
    return this.appInfo;
  }
  
  protected final IGameCenterSDK getSDKService()
  {
    return this.sdk;
  }
  
  protected final String getVersion()
  {
    return "470400";
  }
  
  public final boolean isMiAccountLogin()
  {
    boolean bool2 = false;
    if (this.ctx == null) {
      throw new NullPointerException("Context is Null, please init SDK");
    }
    if (!checkPermission(this.ctx, "android.permission.GET_ACCOUNTS")) {
      throw new SecurityException("XiaomiSDK lacks any of android.permission.GET_ACCOUNTS");
    }
    if (this.accountAdapter.b())
    {
      Account[] arrayOfAccount = AccountManager.get(this.ctx).getAccountsByType("com.xiaomi");
      boolean bool1 = bool2;
      if (arrayOfAccount != null)
      {
        bool1 = bool2;
        if (arrayOfAccount.length > 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    this.accountAdapter.a();
    return false;
  }
  
  final boolean isSdkServiceExist(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < paramContext.size())
      {
        boolean bool = ((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase("com.xiaomi.gamecenter.sdk.service");
        if (bool) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  protected final boolean isTopActivity(Activity paramActivity)
  {
    String str = paramActivity.getPackageName();
    paramActivity = ((ActivityManager)paramActivity.getSystemService("activity")).getRunningTasks(1);
    return (paramActivity.size() > 0) && (str.equals(((ActivityManager.RunningTaskInfo)paramActivity.get(0)).topActivity.getPackageName()));
  }
  
  public final void miAppExit(Activity paramActivity, OnExitListner paramOnExitListner)
  {
    if (this.accountAdapter == null)
    {
      paramOnExitListner.onExit(10001);
      return;
    }
    if (this.accountAdapter.a())
    {
      paramOnExitListner.onExit(10001);
      return;
    }
    if (this.mTouch)
    {
      paramOnExitListner.onExit(47530);
      return;
    }
    this.mTouch = true;
    new Thread(new ae(this, paramActivity, paramOnExitListner)).start();
  }
  
  public final void miChangeAccountLogin(Activity paramActivity, OnLoginProcessListener paramOnLoginProcessListener)
  {
    if (HyUtils.b())
    {
      HyUtils.a(paramActivity, new LoginResult(paramOnLoginProcessListener, paramActivity));
      return;
    }
    if (this.mTouch)
    {
      paramOnLoginProcessListener.finishLoginProcess(47530, null);
      return;
    }
    this.mTouch = true;
    aq.a(paramActivity, paramOnLoginProcessListener, LoginType.CHANGEACCOUNT);
  }
  
  public final void miLogin(Activity paramActivity, OnLoginProcessListener paramOnLoginProcessListener)
  {
    if (HyUtils.b())
    {
      HyUtils.a(paramActivity, new LoginResult(paramOnLoginProcessListener, paramActivity));
      return;
    }
    if (this.mTouch)
    {
      paramOnLoginProcessListener.finishLoginProcess(47530, null);
      return;
    }
    this.mTouch = true;
    if ((isSdkServiceExist(this.ctx)) && (checkServiceVersion(this.ctx)) && (HyUtils.a(this.ctx))) {
      if (this.accountAdapter.a())
      {
        this.accountAdapter.a(AccountStatus.SERVICE);
        openAppForInit();
      }
    }
    for (;;)
    {
      aq.a(paramActivity, paramOnLoginProcessListener, LoginType.LOGIN);
      return;
      if (this.accountAdapter.b())
      {
        MiGamePluginStat.setCheckInitEnv(false);
        this.accountAdapter.a(AccountStatus.LOCAL);
        PaySDK.a(this.ctx, this.appInfo.getAppId(), this.appInfo.getAppKey());
      }
    }
  }
  
  public final int miUniPay(Activity paramActivity, MiBuyInfo paramMiBuyInfo, OnPayProcessListener paramOnPayProcessListener)
    throws RemoteException
  {
    if (HyUtils.b())
    {
      if (!debugLogin)
      {
        Toast.makeText(paramActivity, "Please login first!", 0).show();
        paramOnPayProcessListener.finishPayProcess(47533);
        return 0;
      }
      String str;
      if (paramMiBuyInfo.getAmount() == 0)
      {
        str = analysisProductCatalog(paramMiBuyInfo);
        if (TextUtils.isEmpty(str)) {
          break label82;
        }
        HyUtils.a(paramActivity, new PaymentCallback(paramOnPayProcessListener, paramMiBuyInfo), str);
      }
      for (;;)
      {
        return 0;
        str = "Not support amount yet!";
        break;
        label82:
        paramOnPayProcessListener.finishPayProcess(47533);
      }
    }
    if (this.mTouch)
    {
      paramOnPayProcessListener.finishPayProcess(47530);
      return -1;
    }
    this.mTouch = true;
    SDKPaymentUtil.a(paramActivity, paramMiBuyInfo, paramOnPayProcessListener);
    return 0;
  }
  
  @Deprecated
  public final int miUniPayOffline(Activity paramActivity, MiBuyInfoOffline paramMiBuyInfoOffline, OnPayProcessListener paramOnPayProcessListener)
    throws RemoteException
  {
    if (this.mTouch)
    {
      paramOnPayProcessListener.finishPayProcess(47530);
      return -1;
    }
    this.mTouch = true;
    MiBuyInfo localMiBuyInfo = new MiBuyInfo();
    localMiBuyInfo.setCpOrderId(paramMiBuyInfoOffline.getCpOrderId());
    localMiBuyInfo.setProductCode(paramMiBuyInfoOffline.getProductCode());
    localMiBuyInfo.setCount(paramMiBuyInfoOffline.getCount());
    SDKPaymentUtil.a(paramActivity, localMiBuyInfo, paramOnPayProcessListener);
    return 0;
  }
  
  @Deprecated
  public final int miUniPayOnline(Activity paramActivity, MiBuyInfoOnline paramMiBuyInfoOnline, Bundle paramBundle, OnPayProcessListener paramOnPayProcessListener)
    throws RemoteException
  {
    if (this.mTouch)
    {
      paramOnPayProcessListener.finishPayProcess(47530);
      return -1;
    }
    this.mTouch = true;
    MiBuyInfo localMiBuyInfo = new MiBuyInfo();
    localMiBuyInfo.setCpOrderId(paramMiBuyInfoOnline.getCpOrderId());
    localMiBuyInfo.setAmount(paramMiBuyInfoOnline.getMili());
    localMiBuyInfo.setCpUserInfo(paramMiBuyInfoOnline.getCpUserInfo());
    localMiBuyInfo.setExtraInfo(paramBundle);
    SDKPaymentUtil.a(paramActivity, localMiBuyInfo, paramOnPayProcessListener);
    return 0;
  }
  
  public final boolean newSDKInstalled(Activity paramActivity)
  {
    boolean bool = true;
    if (check_and_connect(paramActivity, true) == 0) {}
    for (;;)
    {
      disconnect();
      return bool;
      bool = false;
    }
  }
  
  protected final void sendLogToSDKSerivce(String paramString)
  {
    try
    {
      if (this.sdk == null) {
        return;
      }
      this.sdk.sendLogToService(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  protected final void sendToastMsg()
  {
    if (this.toastHandler != null) {
      this.toastHandler.sendEmptyMessage(0);
    }
  }
  
  protected final void setTouch(boolean paramBoolean)
  {
    this.mTouch = paramBoolean;
  }
  
  public final void update_screen_orientation(ScreenOrientation paramScreenOrientation)
  {
    this.appInfo.setOrientation(paramScreenOrientation);
  }
  
  public class LoginResult
    implements k
  {
    private OnLoginProcessListener b;
    private Context c;
    
    public LoginResult(OnLoginProcessListener paramOnLoginProcessListener, Context paramContext)
    {
      this.b = paramOnLoginProcessListener;
      this.c = paramContext;
    }
    
    public final void a()
    {
      MiCommplatform.access$1202(false);
      this.b.finishLoginProcess(-102, null);
    }
    
    public final void a(MiAccountInfo paramMiAccountInfo)
    {
      paramMiAccountInfo = new MiCommplatform.NetworkResult(MiCommplatform.this, this.b);
      HyUtils.b(this.c, paramMiAccountInfo);
    }
  }
  
  public class NetworkResult
    implements k
  {
    private OnLoginProcessListener b;
    
    public NetworkResult(OnLoginProcessListener paramOnLoginProcessListener)
    {
      this.b = paramOnLoginProcessListener;
    }
    
    public final void a()
    {
      MiCommplatform.access$1202(false);
      this.b.finishLoginProcess(-102, null);
    }
    
    public final void a(MiAccountInfo paramMiAccountInfo)
    {
      MiCommplatform.access$1202(true);
      this.b.finishLoginProcess(0, paramMiAccountInfo);
    }
  }
  
  public class PaymentCallback
    implements k
  {
    private OnPayProcessListener b;
    private MiBuyInfo c;
    
    public PaymentCallback(OnPayProcessListener paramOnPayProcessListener, MiBuyInfo paramMiBuyInfo)
    {
      this.b = paramOnPayProcessListener;
      this.c = paramMiBuyInfo;
    }
    
    public final void a()
    {
      this.b.finishPayProcess(47533);
    }
    
    public final void a(MiAccountInfo paramMiAccountInfo)
    {
      if (MiCommplatform.this.analysisProductCatalog(this.c) != null)
      {
        this.b.finishPayProcess(0);
        return;
      }
      this.b.finishPayProcess(47533);
    }
  }
}
