package com.igg.iggsdkbusiness;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.igg.sdk.IGGSDK;
import com.igg.sdk.IGGSDKConstant.IGGFamily;
import com.igg.sdk.IGGSDKConstant.IGGIDC;
import com.igg.sdk.IGGSDKConstant.IGGPlatform;
import com.igg.sdk.account.GooglePlay;
import com.igg.sdk.account.IGGAccountSession;
import com.igg.sdk.error.IGGError;
import com.igg.sdk.log.IGGLogger;
import com.igg.sdk.log.listener.LoggerListener;
import com.igg.sdk.push.IGGGCMPushNotification;
import com.igg.sdk.push.IGGPushNotification;
import com.igg.sdk.push.IIGGPushNotification;
import com.igg.util.DeviceUtil;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class IGGSDKPlugin
  extends UnityPlayerActivity
{
  public static final String EXTRA_MESSAGE = "message";
  protected static final String MANAGER_NAME = "GoogleCloudMessagingManager";
  public static final String PROPERTY_GCM_SENDER_ID = "sender_id";
  public static final String PROPERTY_REG_ID = "registration_id";
  public static final int RESULT_OK = -1;
  private static IGGSDKPlugin _instance;
  private static boolean _isAppInForeground;
  private static boolean isAppPrintLog = true;
  public static IGGSDKPlugin mContext;
  String AlertDialogStr = "";
  String BindAccountCallBack = "BindAccountCallBack";
  private String MessageId = "GM";
  String RateUsFailedCallBack = "RateUsFailedCallBack";
  String RateUsSuccessfulCallBack = "RateUsSuccessfulCallBack";
  String SwitchAccountCallBack = "SwitchAccountCallBack";
  protected Class<?> _unityPlayerClass;
  
  public IGGSDKPlugin() {}
  
  public static void DebugLog(String paramString)
  {
    if (isAppPrintLog) {
      UnitySendMessage("SopDebugLog", "GetMessage", paramString + " -from IGGSDKPlugin log-");
    }
  }
  
  public static Activity GetActivity()
  {
    return UnityPlayer.currentActivity;
  }
  
  protected static void UnitySendMessage(String paramString1, String paramString2, String paramString3)
  {
    UnityPlayer.UnitySendMessage(paramString1, paramString2, paramString3);
  }
  
  public static String getAuthorityFromPermission(Context paramContext)
  {
    String str2 = getAuthorityFromPermissionDefault(paramContext);
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.trim().equals("")) {}
    }
    else
    {
      str1 = getThirdAuthorityFromPermission(paramContext, getCurrentLauncherPackageName(paramContext) + ".permission.READ_SETTINGS");
    }
    paramContext = str1;
    int i;
    if (TextUtils.isEmpty(str1))
    {
      i = Build.VERSION.SDK_INT;
      if (i >= 8) {
        break label94;
      }
      paramContext = "com.android.launcher.settings";
    }
    for (;;)
    {
      return "content://" + paramContext + "/favorites?notify=true";
      label94:
      if (i < 19) {
        paramContext = "com.android.launcher2.settings";
      } else if (i < 21) {
        paramContext = "com.android.launcher3.settings";
      } else {
        paramContext = "com.android.launcher4.settings";
      }
    }
  }
  
  public static String getAuthorityFromPermissionDefault(Context paramContext)
  {
    return getThirdAuthorityFromPermission(paramContext, "com.android.launcher.permission.READ_SETTINGS");
  }
  
  public static String getCurrentLauncherPackageName(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if ((paramContext == null) || (paramContext.activityInfo == null)) {
      return "";
    }
    if (paramContext.activityInfo.packageName.equals("android")) {
      return "";
    }
    return paramContext.activityInfo.packageName;
  }
  
  private static String getLauncherPkgName(Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    String str;
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      str = ((ActivityManager.RunningAppProcessInfo)paramContext.next()).processName;
    } while ((!str.contains("launcher")) || (!str.contains("android")));
    return str;
  }
  
  public static String getMD5EncryptedString(String paramString)
  {
    Object localObject = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localObject = localMessageDigest;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        System.out.println("Exception while encrypting to md5");
        localNoSuchAlgorithmException.printStackTrace();
        continue;
        paramString = "0" + paramString;
      }
    }
    localObject.update(paramString.getBytes(), 0, paramString.length());
    paramString = new BigInteger(1, localObject.digest()).toString(16);
    if (paramString.length() >= 32) {
      return paramString;
    }
  }
  
  public static String getThirdAuthorityFromPermission(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(8);
        if (paramContext == null) {
          return "";
        }
        paramContext = paramContext.iterator();
        boolean bool = paramContext.hasNext();
        if (bool) {
          continue;
        }
      }
      catch (Exception paramContext)
      {
        ProviderInfo[] arrayOfProviderInfo;
        int j;
        int i;
        ProviderInfo localProviderInfo;
        paramContext.printStackTrace();
        continue;
      }
      return "";
      arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
      if (arrayOfProviderInfo != null)
      {
        j = arrayOfProviderInfo.length;
        i = 0;
        if (i < j)
        {
          localProviderInfo = arrayOfProviderInfo[i];
          if (((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission))) && (!TextUtils.isEmpty(localProviderInfo.authority)) && (localProviderInfo.authority.contains(".launcher.settings")))
          {
            paramContext = localProviderInfo.authority;
            return paramContext;
          }
          i += 1;
        }
      }
    }
  }
  
  public static boolean hasShortcut(Context paramContext)
  {
    boolean bool2 = false;
    Object localObject1 = null;
    Log.e("hasShortcut", "title = " + null);
    try
    {
      localObject2 = paramContext.getPackageManager();
      localObject2 = ((PackageManager)localObject2).getApplicationLabel(((PackageManager)localObject2).getApplicationInfo(paramContext.getPackageName(), 128)).toString();
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2;
        Uri localUri;
        Cursor localCursor;
        boolean bool1;
        Log.e("hasShortcut", "hasShortcut Exception");
      }
    }
    Log.e("hasShortcut", "title = " + localObject1);
    localUri = Uri.parse(getAuthorityFromPermission(paramContext));
    Log.e("hasShortcut", "CONTENT_URI = " + localUri);
    localObject2 = paramContext.getContentResolver().query(localUri, null, "title=?", new String[] { localObject1 }, null);
    Log.e("hasShortcut", "Cursor = " + localObject2);
    localCursor = paramContext.getContentResolver().query(localUri, new String[] { "title", "iconResource" }, "title=?", new String[] { localObject1 }, null);
    Log.e("hasShortcut", "cursor2 = " + localCursor);
    paramContext = paramContext.getContentResolver().query(localUri, new String[] { "title" }, "title=?", new String[] { localObject1 }, null);
    Log.e("hasShortcut", "cursor3 = " + paramContext);
    bool1 = bool2;
    if (localObject2 != null)
    {
      bool1 = bool2;
      if (((Cursor)localObject2).getCount() > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static IGGSDKPlugin instance()
  {
    return mContext;
  }
  
  private void setupForIGGPlatform()
  {
    IGGSDK.sharedInstance().setPlatform(IGGSDKConstant.IGGPlatform.IGG);
    IGGSDK.sharedInstance().setFamily(IGGSDKConstant.IGGFamily.IGG);
    IGGSDK.sharedInstance().setApplication(getActivity().getApplication());
    IGGSDK.sharedInstance().setGameId("1051019902");
    IGGSDK.sharedInstance().setSecretKey("f6239975b2faae941ec24695e4db5bba");
    IGGSDK.sharedInstance().setPaymentKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp4s2WiW7VMKUewEIr+/Y3hMUO4AQQU2tbkg4irGkHYOGmdBT5SBxysco4jtJ1wbfx3bl2PfotPutU6xrTz/0+r0rmcEfv6EaKwyreNkp4Ej/yX7qvoXHHl33ISdpC3UOkFNb+8KaovSA8/TBGBA+O5TpeCTtTs0H1aoSCtR5LdYciRj9a2GkO+rcgB8OqZc+laXDIIzm8E+sn4r0oMDeQnJ+XiSmDpzAlQnbw2K7ycjlk9I/UhkyhiHz6U8KxuOvuU9im4mc1omOF3sD7EVWtd3OAr5xdJAipYoM8+0T4Jz5OOD3eClF6q5i9xtcRVoyEAqy8K+7bQOWB8/Zv57BLQIDAQAB");
    IGGSDK.sharedInstance().setGCMSenderId("489219977954");
    IGGSDK.sharedInstance().setIDC(IGGSDKConstant.IGGIDC.TW);
    IGGSDK.sharedInstance().initialize();
  }
  
  public void AppsFlyerSignUp()
  {
    AppsFlyerHelper.sharedInstance().AppsFlyerSignUp();
  }
  
  public void AppsFlyerTutorialCompletion()
  {
    AppsFlyerHelper.sharedInstance().TutorialCompletion();
  }
  
  public void AutoLogin()
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        Login.sharedInstance().AutoLogin();
      }
    }, 1000L);
  }
  
  public void BuyProduct(final String paramString, final int paramInt)
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        ProuductGooglePay.sharedInstance().pay(paramString, Integer.toString(paramInt), "0");
      }
    }, 1000L);
  }
  
  public void CancelNotification(int paramInt)
  {
    Log.e("Alarm", "CancelLocalNotification Cancel ID =" + paramInt);
    LocalNotificationAlarmHelper.sharedInstance().Cancel(paramInt);
  }
  
  public boolean CheckGoogleAPI23()
  {
    Log.e("CheckGoogleAPI23", "CheckGoogleAPI23");
    try
    {
      i = Build.VERSION.SDK_INT;
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          try
          {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(IGGSDKPlugin.GetActivity());
            localBuilder.setTitle("setTitle");
            localBuilder.setMessage("Message");
            localBuilder.setPositiveButton("PositiveButton", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
            });
            localBuilder.show();
            return;
          }
          catch (Exception localException)
          {
            Log.e("CheckGoogleAPI23", "Exception = " + localException.toString());
          }
        }
      }, 1000L);
      if (i >= 23) {
        return true;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        int i = 0;
      }
    }
    return false;
  }
  
  public boolean CheckGooglePlayServicesUtil()
  {
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) != 0)
    {
      Log.i("CheckGooglePlayServicesUtil", "No valid Google Play Services APK found");
      return false;
    }
    Log.i("CheckGooglePlayServicesUtil", "ConnectionResult.SUCCESS");
    return true;
  }
  
  public void ClearFacebookSession()
  {
    FacebookHelper.sharedInstance().ClearFacebookSession();
  }
  
  public void ClosePushNotification()
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        GCMRegister.sharedInstance().onDestroy();
      }
    }, 1000L);
  }
  
  public void CompleteRegistration(String paramString)
  {
    FacebookHelper.sharedInstance().CompleteRegistration(paramString);
  }
  
  public String ExchangeUrl()
  {
    String str1 = IGGSDK.sharedInstance().getGameId();
    String str2 = IGGAccountSession.currentSession.getAccesskey();
    String str3 = getMD5EncryptedString("from=phone&game_id=" + str1 + "&signed_key=" + str2 + "&char_id=0ebmQCLWxSdT5bUXulo.tw.igg.com");
    return "http://lo.tw.igg.com/event/cdkey/?from=phone&game_id=" + str1 + "&signed_key=" + str2 + "&char_id=0&event_sig=" + str3;
  }
  
  public void FacebookLike()
  {
    LoadWebView(IGGUrlHelper.sharedInstance().GetFaceBookLike());
    SendMessageToUnity("FacebookLikeCallBack", "");
  }
  
  public void Flush()
  {
    FacebookHelper.sharedInstance().Flush();
  }
  
  public void ForumLink()
  {
    String str = IGGUrlHelper.sharedInstance().GetForumLink();
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(str));
    startActivity(localIntent);
  }
  
  public String GetCountry()
  {
    String str = getResources().getConfiguration().locale.getCountry();
    Log.e("GetCountry", str);
    return str;
  }
  
  public String[] GetGoogleAccountList()
  {
    Account[] arrayOfAccount = AccountManager.get(mContext.getApplicationContext()).getAccountsByType("com.google");
    String[] arrayOfString = new String[arrayOfAccount.length];
    int i = 0;
    for (;;)
    {
      if (i >= arrayOfString.length) {
        return arrayOfString;
      }
      arrayOfString[i] = arrayOfAccount[i].name;
      i += 1;
    }
  }
  
  public String GetID()
  {
    return IGGSDK.sharedInstance().getDeviceRegisterId();
  }
  
  public String GetNewsUrl(String paramString1, String paramString2)
  {
    String str1 = IGGSDK.sharedInstance().getGameId();
    String str2 = IGGAccountSession.currentSession.getAccesskey();
    paramString2 = getMD5EncryptedString("game_id=" + str1 + "&signed_key=" + str2 + "&char_id=" + paramString2);
    return paramString1 + "game_id=" + str1 + "&signed_key=" + str2 + "&char_id=0&sig=" + paramString2;
  }
  
  public void GetProductList()
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        ProuductGooglePay.sharedInstance().getProduct();
      }
    }, 1000L);
  }
  
  public void GetToken()
  {
    FacebookHelper.sharedInstance().GetToken();
  }
  
  public void GeustLogin()
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        Login.sharedInstance().GuestLogin();
      }
    }, 1000L);
  }
  
  public void GoToNews(String paramString1, String paramString2)
  {
    LoadWebView(GetNewsUrl(paramString1, paramString2));
  }
  
  public void GoogleAccountLogin()
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        SwitchingGoogleAccount.sharedInstance().showAccount(false);
      }
    }, 1000L);
  }
  
  public void GoogleAccountLogin(final String paramString)
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        SwitchingGoogleAccount.sharedInstance().switchAccount(paramString);
      }
    }, 1000L);
  }
  
  public void Guide(String paramString)
  {
    LoadWebView(paramString + "game_id=" + IGGSDK.sharedInstance().getGameId());
  }
  
  public void Init(boolean paramBoolean)
  {
    isAppPrintLog = paramBoolean;
  }
  
  public void InitIggData() {}
  
  public boolean IsAppInstalled(String paramString)
  {
    PackageManager localPackageManager = mContext.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public boolean IsPaymentReady()
  {
    return ProuductGooglePay.sharedInstance().IsPaymentReady();
  }
  
  public void LaunchEvent()
  {
    AppsFlyerHelper.sharedInstance().LaunchEvent();
  }
  
  public void LinkGoogleAccount()
  {
    DebugLog("LinkGoogleAccount。。。。！");
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        final String[] arrayOfString = GooglePlay.getLocalRegisteredEmails();
        if (arrayOfString.length == 0)
        {
          IGGSDKPlugin.DebugLog("names.length == 0。。。。！");
          return;
        }
        new AlertDialog.Builder(IGGSDKPlugin.this.getActivity()).setItems(arrayOfString, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = arrayOfString[paramAnonymous2Int];
            IGGSDKPlugin.DebugLog(paramAnonymous2DialogInterface);
            BindingGoogleAccount.sharedInstance().bindingGoogle(paramAnonymous2DialogInterface);
          }
        }).create().show();
      }
    }, 1000L);
  }
  
  public void LinkGoogleAccount(final String paramString)
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        BindingGoogleAccount.sharedInstance().bindingGoogle(paramString);
      }
    }, 1000L);
  }
  
  public void LoadAdLog() {}
  
  public void LoadEventConfig()
  {
    IGGAppConfigHelper.sharedInstance().LoadEventConfig();
  }
  
  public void LoadGameConfig()
  {
    IGGAppConfigHelper.sharedInstance().LoadGameConfig();
  }
  
  public void LoadWebView(String paramString)
  {
    Intent localIntent = new Intent(this, IGGWebView.class);
    localIntent.putExtra("Url", paramString);
    startActivity(localIntent);
  }
  
  public void LoginLog()
  {
    final IGGLogger localIGGLogger = new IGGLogger(IGGSDKConstant.IGGIDC.TW);
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            this.val$logger.loginLog("", "", new LoggerListener()
            {
              public void onFinished(IGGError paramAnonymous3IGGError)
              {
                if (paramAnonymous3IGGError.isNone())
                {
                  Log.i("LoginLog", "success");
                  IGGSDKPlugin.this.SendMessageToUnity("LoginLogDebugLog", "success");
                  return;
                }
                Log.i("LoginLog", paramAnonymous3IGGError.getOriginal().getMessage());
                IGGSDKPlugin.this.SendMessageToUnity("LoginLogDebugLog", paramAnonymous3IGGError.getOriginal().getMessage());
              }
            });
          }
        }).start();
      }
    }, 1000L);
  }
  
  public void NotificationUninitialize()
  {
    if (CheckGooglePlayServicesUtil()) {
      ((IGGGCMPushNotification)IGGPushNotification.sharedInstance().initialize(IGGAccountSession.currentSession.getIGGId())).uninitialize();
    }
  }
  
  public void OpenFbByUrl(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    startActivity(localIntent);
    Log.d("OpenFbByUrl", "OpenFbByUrl CallBack");
  }
  
  public void OpenGooglePlayStoreUrl(final String paramString1, final String paramString2)
  {
    Handler localHandler = new Handler(Looper.getMainLooper());
    Log.e("OpenGooglePlayStoreUrl", "url = " + paramString1 + ";" + "my_package_name = " + paramString2);
    localHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
        try
        {
          IGGSDKPlugin.this.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString1)));
          return;
        }
        catch (Exception localException)
        {
          Uri.parse("https://play.google.com/store/apps/details?id=" + paramString2);
          localIntent.addFlags(268959744);
          IGGSDKPlugin.this.getActivity().startActivity(localIntent);
        }
      }
    }, 1000L);
  }
  
  public void OpenPushNotification()
  {
    Log.e("GCM", "OpenPushNotification。。。。！");
    if (CheckGooglePlayServicesUtil())
    {
      new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
      {
        public void run()
        {
          GCMRegister.sharedInstance().init();
        }
      }, 1000L);
      return;
    }
    Log.e("GCM", "CheckGooglePlayServicesUtil = False");
  }
  
  public void OpenThirdPartyPayment(int paramInt)
  {
    String str1 = IGGAccountSession.currentSession.getAccesskey();
    String str2 = IGGSDK.sharedInstance().getGameId();
    LoadWebView("http://pay-fb.igg.com/platform_mixing/?g_id=" + str2 + "&s_id=" + paramInt + "&signed_key=" + str1);
  }
  
  public void PrivacyPolicy()
  {
    LoadWebView(IGGUrlHelper.sharedInstance().GetPrivacyPolicy());
  }
  
  public void RateGooglePlayApplication(final String paramString)
  {
    DebugLog("rate googleplay application:" + paramString);
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        try
        {
          IGGSDKPlugin.this.getActivity().startActivity(localIntent);
          IGGSDKPlugin.DebugLog("RateGooglePlayApplication！");
          IGGSDKPlugin.this.SendMessageToUnity("RateGooglePlayApplicationSucceeded", "true");
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          Toast.makeText(IGGSDKPlugin.this.getActivity().getApplicationContext(), " unable to find market app", 1).show();
          IGGSDKPlugin.this.SendMessageToUnity("RateGooglePlayApplicationSucceeded", "false");
        }
      }
    }, 1000L);
  }
  
  public void RateUs()
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.igg.android.lordsonline_tw"));
        try
        {
          IGGSDKPlugin.this.getActivity().startActivity(localIntent);
          IGGSDKPlugin.DebugLog("RateGooglePlayApplication！");
          IGGSDKPlugin.this.SendMessageToUnity(IGGSDKPlugin.this.RateUsSuccessfulCallBack, "true");
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          Toast.makeText(IGGSDKPlugin.this.getActivity().getApplicationContext(), " unable to find market app", 1).show();
          IGGSDKPlugin.this.SendMessageToUnity(IGGSDKPlugin.this.RateUsFailedCallBack, "false");
        }
      }
    }, 1000L);
  }
  
  public void RegisterCallback()
  {
    FacebookHelper.sharedInstance().RegisterCallback();
  }
  
  public void SendMail(final String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, final String paramString6, final String paramString7, final String paramString8, final String paramString9)
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          Intent localIntent = new Intent("android.intent.action.SENDTO");
          localIntent.setData(Uri.parse(paramString1));
          localIntent.putExtra("android.intent.extra.SUBJECT", paramString2);
          localIntent.putExtra("android.intent.extra.TEXT", new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("=======================\n")).append("UTCTime : ").append(paramString3).append("\n").toString())).append("Game : ").append(paramString4).append("\n").toString())).append("GameVersion : ").append(paramString5).append("\n").toString())).append("PlayerID : ").append(paramString6).append("\n").toString())).append("Category : ").append(paramString2).append("\n").toString())).append("Language : ").append(paramString7).append("\n").toString())).append("DeviceType : ").append(paramString8).append("\n").toString())).append("OsVersion : ").append(paramString9).append("\n").toString() + "=======================\n");
          IGGSDKPlugin.this.startActivity(localIntent);
          return;
        }
        catch (Exception localException) {}
      }
    }, 1000L);
  }
  
  public void SendMessageToUnity(String paramString1, String paramString2)
  {
    UnitySendMessage(this.MessageId, paramString1, paramString2);
  }
  
  public void SendTicket()
  {
    LoadWebView(IGGUrlHelper.sharedInstance().GetSendTicket());
  }
  
  public void SetAppsFlyerKey()
  {
    AppsFlyerHelper.sharedInstance().SetAppsFlyerKey();
  }
  
  public void SetFacebookEventActivateApp()
  {
    FacebookHelper.sharedInstance().ActivateApp();
  }
  
  public void SetFacebookEventCompletedTutorial()
  {
    FacebookHelper.sharedInstance().CompletedTutorial();
  }
  
  public void SetFacebookEventDeactivateApp()
  {
    FacebookHelper.sharedInstance().DeactivateApp();
  }
  
  public void SetFacebookEventLaunched()
  {
    FacebookHelper.sharedInstance().AppLaunched();
  }
  
  public void SetFacebookEventPurchases(double paramDouble, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    FacebookHelper.sharedInstance().Purchases(paramDouble, paramString1, paramString2, paramString3, paramString4);
  }
  
  public void SetFacebookEventSpentCredits(double paramDouble, String paramString1, String paramString2)
  {
    FacebookHelper.sharedInstance().SpentCredits(paramDouble, paramString1, paramString2);
  }
  
  public void SetFacebookSdkInitialize()
  {
    FacebookHelper.sharedInstance().SdkInitialize(this);
  }
  
  public void SetLocalNotification(int paramInt1, String paramString, int paramInt2)
  {
    LocalNotificationAlarmHelper.sharedInstance().SetAlarm(paramInt1, paramString, paramInt2);
  }
  
  public void SetTragetLanguage(String paramString)
  {
    TranslateHelper.sharedInstance().SetTragetLanguage(paramString);
  }
  
  public void SetupIGGPlatform(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    IGGSDK.sharedInstance().setPlatform(IGGSDKConstant.IGGPlatform.IGG);
    IGGSDK.sharedInstance().setFamily(IGGSDKConstant.IGGFamily.IGG);
    IGGSDK.sharedInstance().setApplication(getActivity().getApplication());
    IGGSDK.sharedInstance().setGameId(paramString1);
    IGGSDK.sharedInstance().setSecretKey(paramString2);
    IGGSDK.sharedInstance().setPaymentKey(paramString3);
    IGGSDK.sharedInstance().setGCMSenderId(paramString4);
    if (paramString1.equals("1051019902")) {
      IGGSDK.sharedInstance().setIDC(IGGSDKConstant.IGGIDC.TW);
    }
    for (;;)
    {
      Log.i("SetupIGGPlatform", "IDC = " + IGGSDK.sharedInstance().getIDC());
      IGGSDK.sharedInstance().setChinaMainland(false);
      IGGSDK.sharedInstance().setUseExternalStorage(false);
      IGGSDK.sharedInstance().setApplication(getApplication());
      IGGSDK.sharedInstance().initialize();
      return;
      IGGSDK.sharedInstance().setIDC(IGGSDKConstant.IGGIDC.SND);
    }
  }
  
  public void ShareOnFacebook()
  {
    FacebookHelper.sharedInstance().ShareOnFacebook();
  }
  
  public void ShowAccount(final boolean paramBoolean)
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        SwitchingGoogleAccount.sharedInstance().showAccount(paramBoolean);
      }
    }, 1000L);
  }
  
  public void ShowFacebookDebug()
  {
    FacebookHelper.sharedInstance().ShowDebug();
  }
  
  public void SubmitQuestion()
  {
    String str = IGGAccountSession.currentSession.getAccesskey();
    str = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder("http://goto-tw.igg.com/game/service/").append(IGGSDK.sharedInstance().getGameId()).toString())).append("?mobile=1").toString())).append("&signed_key=").append(str).toString())).append("&dev_ver=").append(Build.MODEL).append(";").append(Build.VERSION.RELEASE).toString())).append("&game_ver=").append(DeviceUtil.getAppVersionName(IGGSDK.sharedInstance().getApplication())).toString())).append("&l=").append(Locale.getDefault().getLanguage()).toString() + "&login";
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(str));
    startActivity(localIntent);
  }
  
  public void SupportLiveOnLogin()
  {
    String str = IGGUrlHelper.sharedInstance().GetSupportLiveOnLogin();
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(str));
    startActivity(localIntent);
  }
  
  public void SupportLiveOnLogin_GlobalEdition()
  {
    String str = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder("http://goto.igg.com/game/livechat/").append(IGGSDK.sharedInstance().getGameId()).toString())).append("?signed_key=").append(IGGAccountSession.currentSession.getAccesskey()).toString())).append("&uid=").append(IGGAccountSession.currentSession.getIGGId()).toString())).append("&username=").append(IGGAccountSession.currentSession.getIGGId()).toString() + "&game_info=";
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(str));
    startActivity(localIntent);
  }
  
  public void SupportLiveOnShop()
  {
    String str = IGGUrlHelper.sharedInstance().GetSupportLiveOnShop();
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(str));
    startActivity(localIntent);
  }
  
  public void SupportLiveOnShop_GlobalEdition()
  {
    String str = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder("http://goto.igg.com/game/livechat/").append(IGGSDK.sharedInstance().getGameId()).append("/payment").toString())).append("?signed_key=").append(IGGAccountSession.currentSession.getAccesskey()).toString())).append("&uid=").append(IGGAccountSession.currentSession.getIGGId()).toString())).append("&username=").append(IGGAccountSession.currentSession.getIGGId()).toString() + "&game_info=";
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(str));
    startActivity(localIntent);
  }
  
  public void SwitchFacebook()
  {
    FacebookHelper.sharedInstance().SwitchFacebook();
  }
  
  public void TermsofService()
  {
    LoadWebView(IGGUrlHelper.sharedInstance().GetTermsofService());
  }
  
  public void ThirdPartPayment()
  {
    LoadWebView(IGGUrlHelper.sharedInstance().GetThirdPartPayment());
  }
  
  public void Translate(String paramString)
  {
    TranslateHelper.sharedInstance().Translate(paramString);
  }
  
  public void TranslateBatch(String paramString)
  {
    TranslateHelper.sharedInstance().TranslateBatch(paramString);
  }
  
  public void TranslateBatch_Diplomatic(String paramString)
  {
    TranslateHelper.sharedInstance().TranslateBatch_Diplomatic(paramString);
  }
  
  public void TranslateBatch_Mail(String paramString)
  {
    TranslateHelper.sharedInstance().TranslateBatch_Mail(paramString);
  }
  
  public void Translate_KA(String paramString)
  {
    TranslateHelper.sharedInstance().Translate_KA(paramString);
  }
  
  public void Translate_Mail(String paramString)
  {
    TranslateHelper.sharedInstance().Translate_Mail(paramString);
  }
  
  public void UseExchange()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(ExchangeUrl()));
    startActivity(localIntent);
  }
  
  public void addShortcut(String paramString1, String paramString2)
  {
    DebugLog("it will create shortcut!" + true);
    Log.e("delShortcut", "delShortcut begin");
    delShortcut(paramString1, paramString2);
    Log.e("delShortcut", "delShortcut end");
    if (1 != 0) {}
    try
    {
      Log.e("delShortcut", "delShortcut begin");
      delShortcut(paramString1, paramString2);
      Log.e("delShortcut", "delShortcut end");
      paramString2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
      paramString2.putExtra("android.intent.extra.shortcut.NAME", paramString1);
      paramString2.putExtra("duplicate", false);
      paramString2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getActivity().getApplicationContext(), 2130837504));
      paramString1 = new Intent("android.intent.action.MAIN");
      paramString1.setFlags(2097152);
      paramString1.addFlags(1048576);
      paramString1.addCategory("android.intent.category.LAUNCHER");
      paramString1.setClass(getActivity(), getActivity().getClass());
      paramString2.putExtra("android.intent.extra.shortcut.INTENT", paramString1);
      getActivity().sendBroadcast(paramString2);
      return;
    }
    catch (Exception paramString2)
    {
      for (;;)
      {
        Log.e("delShortcut", "Exception = " + paramString2.toString());
      }
    }
  }
  
  public void delShortcut(String paramString1, String paramString2)
  {
    paramString1 = getActivity().getApplicationContext();
    Intent localIntent = new Intent();
    localIntent.setClassName("com.igg.android", paramString2);
    localIntent.addFlags(268435456);
    localIntent.addFlags(67108864);
    paramString2 = new Intent();
    try
    {
      paramString2.putExtra("android.intent.extra.shortcut.INTENT", Intent.parseUri(localIntent.toUri(0), 0));
      paramString2.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
      paramString1.sendBroadcast(paramString2);
      return;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      for (;;)
      {
        localURISyntaxException.printStackTrace();
      }
    }
  }
  
  public Activity getActivity()
  {
    return mContext;
  }
  
  public String getDeviceName()
  {
    return Build.MODEL;
  }
  
  public int getSdkVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  protected boolean isActivityAlive()
  {
    try
    {
      Activity localActivity = getActivity();
      if (localActivity != null) {
        return true;
      }
    }
    catch (Exception localException)
    {
      DebugLog("Activity is null so we are not running currently");
    }
    return false;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Log.d("onActivityResult", paramInt1 + "resultcode" + paramInt2);
    DebugLog("onActivityResult" + paramInt1 + "resultcode" + paramInt2);
    if (paramInt1 == 1001)
    {
      if (paramInt2 != -1) {
        break label200;
      }
      SwitchingGoogleAccount.sharedInstance().switchAccount(SwitchingGoogleAccount.lastAccount);
      SwitchingGoogleAccount.lastAccount = "";
    }
    for (;;)
    {
      if ((paramInt1 == 1002) && (paramInt2 == -1))
      {
        BindingGoogleAccount.sharedInstance().bindingGoogle(BindingGoogleAccount.lastAccount);
        BindingGoogleAccount.lastAccount = "";
      }
      if (paramInt1 == 53714) {
        ProuductGooglePay.sharedInstance().handleResult(paramInt1, paramInt2, paramIntent);
      }
      if (paramInt1 == 64206) {
        FacebookHelper.sharedInstance().OnActivityResult(paramInt1, paramInt2, paramIntent);
      }
      if ((paramInt1 == 65535) && (paramInt2 == -1)) {}
      try
      {
        SendMessageToUnity(this.SwitchAccountCallBack, paramIntent.getStringExtra("authAccount"));
        if ((paramInt1 == 65534) && (paramInt2 == -1)) {}
        try
        {
          SendMessageToUnity(this.BindAccountCallBack, paramIntent.getStringExtra("authAccount"));
          return;
        }
        catch (Exception paramIntent)
        {
          label200:
          Log.e("onActivityResult Exception", paramIntent.toString());
        }
        SwitchingGoogleAccount.sharedInstance().SwitchingGoogleAccountCancel();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e("onActivityResult Exception", localException.toString());
        }
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mContext = this;
    try
    {
      Class.forName("android.os.AsyncTask");
      return;
    }
    catch (Throwable paramBundle) {}
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}
