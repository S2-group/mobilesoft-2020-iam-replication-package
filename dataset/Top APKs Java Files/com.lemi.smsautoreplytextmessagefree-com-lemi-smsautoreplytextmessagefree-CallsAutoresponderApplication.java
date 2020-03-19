package com.lemi.smsautoreplytextmessagefree;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Patterns;
import com.android.mms.transaction.TransactionService;
import com.android.mms.transaction.TransactionServiceL;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;
import com.google.android.mms.pdu.PduParser;
import com.lemi.callsautoresponder.callreceiver.CustomPhoneStateListener;
import com.lemi.callsautoresponder.callreceiver.DynamicPhoneStateReceiver;
import com.lemi.callsautoresponder.callreceiver.NotificationHandler;
import com.lemi.callsautoresponder.callreceiver.UpdateReceiver;
import com.lemi.callsautoresponder.data.KeywordBillingData;
import com.lemi.callsautoresponder.data.SettingsHandler;
import com.lemi.callsautoresponder.data.UiConst;
import com.lemi.callsautoresponder.db.DbHandler;
import com.lemi.callsautoresponder.db.KeywordIaBillingTbl;
import com.lemi.callsautoresponder.db.KeywordSubscriptionsTbl;
import com.lemi.callsautoresponder.db.MenuTbl;
import com.lemi.callsautoresponder.db.ProfilersTbl;
import com.lemi.callsautoresponder.screen.EditKeywordRespStatus;
import com.lemi.callsautoresponder.screen.EditResponderStatus;
import com.lemi.callsautoresponder.screen.EditSenderStatus;
import com.lemi.callsautoresponder.screen.EditSubscriptionMsgStatus;
import com.lemi.callsautoresponder.screen.MainActivity;
import com.lemi.callsautoresponder.screen.SetKeywordResponderStatus;
import com.lemi.callsautoresponder.screen.SetProfile;
import com.lemi.callsautoresponder.screen.SetResponderStatus;
import com.lemi.callsautoresponder.screen.SetSenderStatus;
import com.lemi.callsautoresponder.screen.SetSubscriptionMessage;
import com.lemi.callsautoresponder.service.DynamicMenuService;
import com.lemi.callsautoresponder.service.IaBillingService;
import com.lemi.callsautoresponder.service.UpdateService;
import com.lemi.callsautoresponder.utils.KeywordUtils;
import com.lemi.callsautoresponderlib.R.array;
import com.lemi.callsautoresponderlib.R.string;
import com.lemi.utils.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CallsAutoresponderApplication
  extends Application
{
  private static final String COUNTRY = " COUNTRY: ";
  private static final String DEVICE = " DEVICE: ";
  private static final String DVTOCH = ": ";
  private static final String LANGUAGE = " LANGUAGE: ";
  public static final int LOLLIPOP = 21;
  private static final int NO_VERSION_CODE = -1;
  private static final String SDK_INT = " SDK_INT: ";
  private static final String TAG = "CallsAutoresponderApplication";
  private static final int VC_ADDED_MAIN_PAGE = 51;
  private static final int VC_ONCE_REPLAY_UPD = 20;
  private static final int VC_SETTINGS_UPGRADE = 10;
  private static final int VC_VIBRATE_OFF_UPD = 23;
  private static PhoneStateListener _phoneListener;
  protected static Context appContext;
  private static CallsAutoresponderApplication instance;
  private static Locale lastLocale;
  protected static String packageName;
  private static TelephonyManager telephonyManager;
  protected static int versionCode;
  protected static String versionName;
  private DynamicPhoneStateReceiver _phoneReceiver;
  protected boolean hasAds = false;
  private Tracker mTracker;
  
  public CallsAutoresponderApplication() {}
  
  public static ArrayList<Integer> checkInstalledApps(Context paramContext)
  {
    Log.i("CallsAutoresponderApplication", "printInstalledApps");
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString = paramContext.getResources().getStringArray(R.array.hamper_app_pkgs);
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      Object localObject = (PackageInfo)localList.get(i);
      if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) != 0) {}
      for (int j = 1;; j = 0)
      {
        if (j != 1)
        {
          String str = ((PackageInfo)localObject).applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
          localObject = ((PackageInfo)localObject).applicationInfo.packageName;
          if (UiConst.getIsDebug(paramContext)) {
            Log.i("CallsAutoresponderApplication", "App № " + Integer.toString(i) + " : " + str + " Package : " + (String)localObject);
          }
          j = isHamperApp(arrayOfString, (String)localObject);
          if (j >= 0) {
            localArrayList.add(Integer.valueOf(j));
          }
        }
        i += 1;
        break;
      }
    }
    return localArrayList;
  }
  
  /* Error */
  public static String geIntarnationalFormattedPhoneNumber(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 182	com/lemi/smsautoreplytextmessagefree/CallsAutoresponderApplication:isValidPhoneNumber	(Ljava/lang/CharSequence;)Z
    //   7: istore_1
    //   8: iload_1
    //   9: ifne +10 -> 19
    //   12: aconst_null
    //   13: astore_0
    //   14: ldc 2
    //   16: monitorexit
    //   17: aload_0
    //   18: areturn
    //   19: invokestatic 188	com/google/i18n/phonenumbers/PhoneNumberUtil:getInstance	()Lcom/google/i18n/phonenumbers/PhoneNumberUtil;
    //   22: astore_2
    //   23: aload_2
    //   24: aload_2
    //   25: aload_0
    //   26: getstatic 190	com/lemi/smsautoreplytextmessagefree/CallsAutoresponderApplication:appContext	Landroid/content/Context;
    //   29: invokestatic 194	com/lemi/smsautoreplytextmessagefree/CallsAutoresponderApplication:getCountry	(Landroid/content/Context;)Ljava/lang/String;
    //   32: invokevirtual 198	com/google/i18n/phonenumbers/PhoneNumberUtil:parse	(Ljava/lang/String;Ljava/lang/String;)Lcom/google/i18n/phonenumbers/Phonenumber$PhoneNumber;
    //   35: getstatic 204	com/google/i18n/phonenumbers/PhoneNumberUtil$PhoneNumberFormat:INTERNATIONAL	Lcom/google/i18n/phonenumbers/PhoneNumberUtil$PhoneNumberFormat;
    //   38: invokevirtual 208	com/google/i18n/phonenumbers/PhoneNumberUtil:format	(Lcom/google/i18n/phonenumbers/Phonenumber$PhoneNumber;Lcom/google/i18n/phonenumbers/PhoneNumberUtil$PhoneNumberFormat;)Ljava/lang/String;
    //   41: invokestatic 211	com/google/i18n/phonenumbers/PhoneNumberUtil:normalizeDigitsOnly	(Ljava/lang/String;)Ljava/lang/String;
    //   44: astore_0
    //   45: ldc 28
    //   47: new 143	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   54: ldc -43
    //   56: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload_0
    //   60: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokestatic 72	com/lemi/utils/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   69: goto -55 -> 14
    //   72: astore_0
    //   73: ldc 28
    //   75: new 143	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   82: ldc -41
    //   84: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: aload_0
    //   88: invokevirtual 218	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   91: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: aload_0
    //   98: invokestatic 222	com/lemi/utils/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   101: aconst_null
    //   102: astore_0
    //   103: goto -89 -> 14
    //   106: astore_0
    //   107: ldc 2
    //   109: monitorexit
    //   110: aload_0
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	paramString	String
    //   7	2	1	bool	boolean
    //   22	3	2	localPhoneNumberUtil	com.google.i18n.phonenumbers.PhoneNumberUtil
    // Exception table:
    //   from	to	target	type
    //   23	69	72	java/lang/Exception
    //   3	8	106	finally
    //   19	23	106	finally
    //   23	69	106	finally
    //   73	101	106	finally
  }
  
  public static Class getActivateProfileClass(Context paramContext)
  {
    paramContext = paramContext.getResources().getString(R.string.activate_profile_class);
    Log.i("CallsAutoresponderApplication", "ActivateProfile class " + paramContext);
    try
    {
      Class localClass = Class.forName(paramContext);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("CallsAutoresponderApplication", "SetProfile Class not found by name : " + paramContext, localClassNotFoundException);
    }
    return SetProfile.class;
  }
  
  public static String getActivateProfileClassName(Context paramContext)
  {
    return paramContext.getResources().getString(R.string.activate_profile_class);
  }
  
  public static String getAppPackageName(Context paramContext)
  {
    if (paramContext == null) {
      return "com.lemi.default";
    }
    return paramContext.getPackageName();
  }
  
  public static CallsAutoresponderApplication getApplication()
  {
    return instance;
  }
  
  public static Context getContext()
  {
    return appContext;
  }
  
  public static String getCountry(Context paramContext)
  {
    Object localObject1 = null;
    try
    {
      localObject2 = instance;
      localObject2 = telephonyManager.getNetworkCountryIso().toUpperCase();
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2;
        Log.e("CallsAutoresponderApplication", "Error get Network Country Iso : " + localException.getMessage(), localException);
      }
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty(localObject1))
    {
      paramContext = paramContext.getResources().getConfiguration().locale;
      localObject2 = localObject1;
      if (localObject1 != null) {
        localObject2 = paramContext.getCountry().toUpperCase();
      }
    }
    return localObject2;
  }
  
  public static String getDeviceInfo(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getString(R.string.app_name)).append(": ").append(versionName);
    localStringBuilder.append(" DEVICE: ").append(Build.MANUFACTURER).append(" ").append(Build.DEVICE);
    localStringBuilder.append(" SDK_INT: ").append(Build.VERSION.SDK_INT);
    localStringBuilder.append(" COUNTRY: ").append(getCountry(paramContext));
    localStringBuilder.append(" LANGUAGE: ").append(getLanguage(paramContext));
    return localStringBuilder.toString();
  }
  
  public static Class getEditStatusClass(int paramInt)
  {
    Object localObject;
    if (paramInt == 1) {
      localObject = EditResponderStatus.class;
    }
    for (;;)
    {
      if (localObject != null) {
        Log.i("CallsAutoresponderApplication", "getEditStatusClass for type=" + paramInt + " class=" + ((Class)localObject).getName());
      }
      return localObject;
      if (paramInt == 3) {
        localObject = EditKeywordRespStatus.class;
      } else if (paramInt == 2) {
        localObject = EditSenderStatus.class;
      } else if (paramInt == 4) {
        localObject = EditSubscriptionMsgStatus.class;
      } else {
        localObject = null;
      }
    }
  }
  
  public static Class getFirstAppActivityClass(Context paramContext)
  {
    if (SettingsHandler.getInstance(paramContext).getBooleanFromSettings("use_profiles_list_as_home", false)) {
      return getActivateProfileClass(paramContext);
    }
    paramContext = paramContext.getResources().getString(R.string.set_first_activity_class);
    Log.i("CallsAutoresponderApplication", "FirstAppActivity class " + paramContext);
    try
    {
      Class localClass = Class.forName(paramContext);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("CallsAutoresponderApplication", "SetProfile Class not found by name : " + paramContext, localClassNotFoundException);
    }
    return MainActivity.class;
  }
  
  public static String getLanguage(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.getDisplayName();
  }
  
  public static Locale getLocale()
  {
    CallsAutoresponderApplication localCallsAutoresponderApplication = instance;
    return lastLocale;
  }
  
  public static int getOneType(Context paramContext)
  {
    if (UiConst.getIsResponder(paramContext)) {
      return 1;
    }
    if (UiConst.getHasKeywords(paramContext)) {
      return 3;
    }
    if (UiConst.getIsSender(paramContext)) {
      return 2;
    }
    if (UiConst.getHasSubscription(paramContext)) {
      return 4;
    }
    return -1;
  }
  
  public static PduParser getPduParser(byte[] paramArrayOfByte)
  {
    if (Build.VERSION.SDK_INT >= 22) {
      return new PduParser(paramArrayOfByte, false);
    }
    return new PduParser(paramArrayOfByte);
  }
  
  public static Class getSetKeywordResponderStatusClass(Context paramContext)
  {
    paramContext = paramContext.getResources().getString(R.string.set_keyword_responder_status_class);
    Log.i("CallsAutoresponderApplication", "SetKeywordResponder class " + paramContext);
    try
    {
      Class localClass = Class.forName(paramContext);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("CallsAutoresponderApplication", "SetKeywordResponder Class not found by name : " + paramContext, localClassNotFoundException);
    }
    return SetKeywordResponderStatus.class;
  }
  
  public static Class getSetResponderStatusClass(Context paramContext)
  {
    paramContext = paramContext.getResources().getString(R.string.set_responder_status_class);
    Log.i("CallsAutoresponderApplication", "SetResponderStatus class " + paramContext);
    try
    {
      Class localClass = Class.forName(paramContext);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("CallsAutoresponderApplication", "SetResponderStatus Class not found by name : " + paramContext, localClassNotFoundException);
    }
    return SetResponderStatus.class;
  }
  
  public static Class getSetSenderStatusClass(Context paramContext)
  {
    paramContext = paramContext.getResources().getString(R.string.set_sender_status_class);
    Log.i("CallsAutoresponderApplication", "SetSenderStatus class " + paramContext);
    try
    {
      Class localClass = Class.forName(paramContext);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("CallsAutoresponderApplication", "SetSenderStatus Class not found by name : " + paramContext, localClassNotFoundException);
    }
    return SetSenderStatus.class;
  }
  
  public static Class getSetStatusTimeClass(Context paramContext, int paramInt)
  {
    if (paramInt == 1) {
      return getSetResponderStatusClass(paramContext);
    }
    if (paramInt == 3) {
      return getSetKeywordResponderStatusClass(paramContext);
    }
    if (paramInt == 2) {
      return getSetSenderStatusClass(paramContext);
    }
    if (paramInt == 4) {
      return getSetSubscriptionMessageClass(paramContext);
    }
    return null;
  }
  
  public static Class getSetSubscriptionMessageClass(Context paramContext)
  {
    paramContext = paramContext.getResources().getString(R.string.set_subscription_message_class);
    Log.i("CallsAutoresponderApplication", "SetSenderStatus class " + paramContext);
    try
    {
      Class localClass = Class.forName(paramContext);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      Log.e("CallsAutoresponderApplication", "SetSenderStatus Class not found by name : " + paramContext, localClassNotFoundException);
    }
    return SetSubscriptionMessage.class;
  }
  
  public static SettingsHandler getSettingsHandler(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    return SettingsHandler.getInstance(paramContext);
  }
  
  public static TelephonyManager getTelephonyManager()
  {
    return telephonyManager;
  }
  
  public static Class<?> getTransactionServiceClass()
  {
    if (Build.VERSION.SDK_INT < 21) {
      return TransactionService.class;
    }
    return TransactionServiceL.class;
  }
  
  public static String getTransactionServiceSendAction()
  {
    if (Build.VERSION.SDK_INT < 21) {
      return "android.intent.action.ACTION_SEND_UMESSAGE";
    }
    return "android.intent.action.ACTION_ENABLE_AUTO_RETRIEVE";
  }
  
  public static String getVersion(Context paramContext)
  {
    if (versionName == null) {}
    try
    {
      versionName = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return versionName;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        versionName = "9.9.9";
      }
    }
  }
  
  private static int isHamperApp(String[] paramArrayOfString, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return -1;
    }
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      if (paramString.equals(paramArrayOfString[i]))
      {
        Log.i("CallsAutoresponderApplication", "isHamperApp for " + paramString + " return " + i);
        return i;
      }
      i += 1;
    }
    Log.i("CallsAutoresponderApplication", "isHamperApp for " + paramString + " return -1");
    return -1;
  }
  
  public static boolean isLollipopAndAbove()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static boolean isOneStatusTypeApp(Context paramContext)
  {
    int j = 0;
    if (UiConst.getIsResponder(paramContext)) {
      j = 0 + 1;
    }
    int i = j;
    if (UiConst.getHasKeywords(paramContext)) {
      i = j + 1;
    }
    j = i;
    if (UiConst.getIsSender(paramContext)) {
      j = i + 1;
    }
    i = j;
    if (UiConst.getHasSubscription(paramContext)) {
      i = j + 1;
    }
    return i == 1;
  }
  
  public static final boolean isValidPhoneNumber(CharSequence paramCharSequence)
  {
    if ((paramCharSequence == null) || (TextUtils.isEmpty(paramCharSequence)))
    {
      Log.i("CallsAutoresponderApplication", "isValidPhoneNumber return false. Empty phone number.");
      return false;
    }
    boolean bool = Patterns.PHONE.matcher(paramCharSequence).matches();
    Log.i("CallsAutoresponderApplication", "isValidPhoneNumber return " + bool + " for number=" + paramCharSequence);
    return bool;
  }
  
  public static void openFirstAppScreen(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, getFirstAppActivityClass(paramContext));
    localIntent.addFlags(67108864);
    localIntent.addFlags(268435456);
    if (Build.VERSION.SDK_INT >= 11) {
      localIntent.addFlags(32768);
    }
    paramContext.startActivity(localIntent);
  }
  
  public static void openProfilesListScreen(Context paramContext, boolean paramBoolean)
  {
    Intent localIntent = new Intent(paramContext, getActivateProfileClass(paramContext));
    if (paramBoolean)
    {
      localIntent.addFlags(67108864);
      localIntent.addFlags(268435456);
      if (Build.VERSION.SDK_INT >= 11) {
        localIntent.addFlags(32768);
      }
    }
    paramContext.startActivity(localIntent);
  }
  
  public static void openSetKeywordResponderStatusScreen(Context paramContext)
  {
    paramContext.startActivity(new Intent(paramContext, getSetKeywordResponderStatusClass(paramContext)));
  }
  
  public static void openSetResponderStatusScreen(Context paramContext)
  {
    paramContext.startActivity(new Intent(paramContext, getSetResponderStatusClass(paramContext)));
  }
  
  public static void openSetSenderStatusScreen(Context paramContext)
  {
    paramContext.startActivity(new Intent(paramContext, getSetSenderStatusClass(paramContext)));
  }
  
  public static void openSetSubscriptionMessageScreen(Context paramContext)
  {
    paramContext.startActivity(new Intent(paramContext, getSetSubscriptionMessageClass(paramContext)));
  }
  
  private static void startListenPhoneState(Context paramContext)
  {
    Log.i("CallsAutoresponderApplication", "startListenPhoneState");
    if (telephonyManager == null) {
      telephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    }
    if (_phoneListener == null) {
      _phoneListener = new CustomPhoneStateListener(paramContext);
    }
    Log.i("CallsAutoresponderApplication", "startListenPhoneState telephonyManager=" + telephonyManager + " _phoneListener=" + _phoneListener);
    telephonyManager.listen(_phoneListener, 32);
  }
  
  public static void turnServerDebugModeOff(Context paramContext, SettingsHandler paramSettingsHandler)
  {
    if (Log.IS_LOG) {
      Log.i("CallsAutoresponderApplication", "turnServerDebugModeOff");
    }
    paramSettingsHandler.saveInSettings("send_server_log", false, false);
    paramSettingsHandler.saveInSettings("debug_user_name", "", false);
    paramSettingsHandler.saveInSettings("send_log_by_wifi", true, true);
    Log.init(paramContext);
  }
  
  private void updateLanguageDependentData()
  {
    Log.i("CallsAutoresponderApplication", "updateLanguageDependentData");
    DbHandler localDbHandler = DbHandler.getInstance(appContext);
    localDbHandler.updateMenuTbl();
    localDbHandler.getMenuTbl().updateMenuData();
    DynamicMenuService.updateDynamicMenuData(appContext, null);
  }
  
  public Tracker getDefaultTracker()
  {
    try
    {
      if (this.mTracker == null)
      {
        this.mTracker = GoogleAnalytics.getInstance(this).newTracker(getString(R.string.ga_trackingId));
        if (UiConst.getIsDebug(appContext)) {
          GoogleAnalytics.getInstance(this).getLogger().setLogLevel(0);
        }
      }
      Tracker localTracker = this.mTracker;
      return localTracker;
    }
    finally {}
  }
  
  public String getNetworkOperatorName(Context paramContext)
  {
    paramContext = new StringBuilder();
    try
    {
      str = telephonyManager.getNetworkOperatorName();
      paramContext.append("TelMng operator: ");
      paramContext.append(str);
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          String str = telephonyManager.getSimOperatorName();
          paramContext.append(" Sim operator: ");
          paramContext.append(str);
          return paramContext.toString();
          localException1 = localException1;
          paramContext.append("TelMng operator error: " + localException1.getMessage());
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          paramContext.append("Sim operator error: " + localException2.getMessage());
        }
      }
    }
  }
  
  public TextToSpeech getTextToSpeech(Context paramContext, TextToSpeech.OnInitListener paramOnInitListener)
  {
    Object localObject = null;
    try
    {
      paramContext = new TextToSpeech(paramContext, paramOnInitListener);
      return paramContext;
    }
    catch (Exception paramOnInitListener)
    {
      do
      {
        paramContext = localObject;
      } while (!Log.IS_LOG);
      Log.e("CallsAutoresponderApplication", "error createTtsInstance:" + paramOnInitListener.getMessage());
    }
    return null;
  }
  
  public boolean hasAds()
  {
    return this.hasAds;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    Log.i("CallsAutoresponderApplication", "Application onConfigurationChanged : new config locale " + paramConfiguration.locale + " old locale " + lastLocale);
    super.onConfigurationChanged(paramConfiguration);
    if (!paramConfiguration.locale.equals(lastLocale)) {
      updateLanguageDependentData();
    }
    lastLocale = paramConfiguration.locale;
  }
  
  public void onCreate()
  {
    super.onCreate();
    instance = this;
    appContext = getApplicationContext();
    packageName = appContext.getPackageName();
    DbHandler localDbHandler;
    try
    {
      Object localObject1 = getPackageManager().getPackageInfo(getPackageName(), 0);
      versionName = ((PackageInfo)localObject1).versionName;
      versionCode = ((PackageInfo)localObject1).versionCode;
      Log.init(appContext);
      Log.i("CallsAutoresponderApplication", "Create Application " + packageName + " version " + versionName + " versionCode " + versionCode);
      localObject1 = appContext.getResources();
      boolean bool2;
      if (localObject1 != null)
      {
        localObject1 = ((Resources)localObject1).getConfiguration();
        lastLocale = ((Configuration)localObject1).locale;
        Log.i("CallsAutoresponderApplication", "application locale " + ((Configuration)localObject1).locale);
        telephonyManager = (TelephonyManager)appContext.getSystemService("phone");
        UpdateReceiver.setUpdateAlarm(appContext, 2419200000L, 2);
        localObject1 = SettingsHandler.getInstance(appContext);
        bool2 = ((SettingsHandler)localObject1).getBooleanFromSettings("first_run", true);
        if ((!isLollipopAndAbove()) && (!((SettingsHandler)localObject1).getBooleanFromSettings("apn_search", false))) {
          DynamicMenuService.loadApnData(appContext, null);
        }
        localDbHandler = DbHandler.getInstance(appContext);
      }
      try
      {
        if (UiConst.getIsDebug(appContext)) {
          Log.i("CallsAutoresponderApplication", "Added for the smil builder initialization.");
        }
        Class.forName("com.android.mms.layout.LayoutManager").getMethod("init", new Class[] { Context.class }).invoke(null, new Object[] { this });
      }
      catch (Exception localException2)
      {
        long l1;
        long l2;
        for (;;)
        {
          int i;
          boolean bool1;
          long l3;
          long l5;
          Object localObject2;
          long l4;
          int j;
          if (UiConst.getIsDebug(appContext))
          {
            Log.e("CallsAutoresponderApplication", "---- smil builder reflection to com.android.mms.layout.LayoutManager error : " + localException2.getMessage());
            continue;
            bool1 = false;
            continue;
            if (i < versionCode)
            {
              onUpgrade(i, versionCode);
              continue;
              if (l1 > 86400000L) {
                localException1.saveInSettings("show_long_debug_dialog", true, true);
              }
            }
          }
        }
        if (l2 + 259200000L < l1) {
          break label1594;
        }
        if (!Log.IS_LOG) {
          break label1588;
        }
        Log.i("CallsAutoresponderApplication", "showKeywordWarningNotification");
        localException2.showKeywordWarningNotification();
        return;
        if (!Log.IS_LOG) {
          break label1608;
        }
        Log.i("CallsAutoresponderApplication", "turn off and notify");
        localDbHandler.getProfilersTbl().setAllNotActiveAndNotWorking();
        localException2.removeKeywordWarningNotification();
        localException2.showKeywordTurnOffNotification();
        localException1.saveInSettings("show_keyword_turn_off_dlg", true, true);
      }
      Log.i("CallsAutoresponderApplication", "DeviceInfo : " + getDeviceInfo(appContext));
      i = ((SettingsHandler)localObject1).getIntFromSettings("ver_code", -1);
      if (UiConst.getIsDebug(appContext)) {
        Log.i("CallsAutoresponderApplication", "appContext oldVersionCode: " + i + " newVersionCode: " + versionCode);
      }
      if ((i == -1) && (bool2))
      {
        bool1 = true;
        if (UiConst.getIsDebug(appContext)) {
          Log.i("CallsAutoresponderApplication", "Fresh installation " + bool1 + " firstRun=" + bool2);
        }
        ((SettingsHandler)localObject1).saveInSettings("ver_code", versionCode, true);
        if (UiConst.getIsDebug(appContext)) {
          Log.i("CallsAutoresponderApplication", "oldVersionCode=" + i + " versionCode=" + versionCode + " saved.");
        }
        if (!bool1) {
          break label1522;
        }
        if (UiConst.getHasMms(appContext)) {
          DynamicMenuService.copyAssetsToDevice(appContext);
        }
        UpdateService.checkUpdate(appContext);
        startListenPhoneState(appContext);
        IaBillingService.checkIaBilling(appContext);
        registerPhoneStateReceiver();
        if (UiConst.getIsDebug(appContext))
        {
          l1 = System.currentTimeMillis();
          l1 -= ((SettingsHandler)localObject1).getLongFromSettings("debug_mode_start_time", l1);
          if (l1 <= 604800000L) {
            break label1540;
          }
          ((SettingsHandler)localObject1).saveInSettings("show_debug_over_time_dialog", true, true);
          turnServerDebugModeOff(appContext, (SettingsHandler)localObject1);
        }
        if (UiConst.getHasKeywords(appContext))
        {
          l1 = System.currentTimeMillis();
          l2 = ((SettingsHandler)localObject1).getLongFromSettings("build_in_keywords_start_time", 0L);
          l3 = l2 + 2592000000L;
          l5 = ((SettingsHandler)localObject1).getLongFromSettings("promo_code_keywords_start_time", 0L);
          if (Log.IS_LOG) {
            Log.i("CallsAutoresponderApplication", "--##--  PROMOCODE  --##--");
          }
          if (l5 > 0L)
          {
            i = ((SettingsHandler)localObject1).getIntFromSettings("promo_code_keywords_billing_id", 0);
            if (Log.IS_LOG) {
              Log.i("CallsAutoresponderApplication", "startPromocodeUsing=" + l5 + " promocodeBillingId=" + i);
            }
            if (i > 0)
            {
              localObject2 = localDbHandler.getKeywordIaBillingTbl().getKeywordBillingDataById(i);
              if (Log.IS_LOG) {
                Log.i("CallsAutoresponderApplication", "KeywordBillingData next " + ((KeywordBillingData)localObject2).toString());
              }
              if (localObject2 != null)
              {
                l4 = ((KeywordBillingData)localObject2).getPeriod() * 30L * 86400000L;
                l5 += l4;
                if (Log.IS_LOG) {
                  Log.i("CallsAutoresponderApplication", "promoTrialPeriod=" + l4 + " stopPromocodeBillingTime=" + l5 + " now=" + l1);
                }
                if (l5 < l1)
                {
                  localDbHandler.getKeywordSubscriptionsTbl().deleteSubscription(i);
                  ((SettingsHandler)localObject1).saveInSettings("promo_code_keywords_start_time", 0L, false);
                  ((SettingsHandler)localObject1).saveInSettings("promo_code_keywords_billing_id", 0, true);
                }
              }
            }
          }
          if (Log.IS_LOG) {
            Log.i("CallsAutoresponderApplication", "--##--  BONUS  --##--");
          }
          l4 = ((SettingsHandler)localObject1).getLongFromSettings("invite_send_bonus_keywords_start_time", 0L);
          if (Log.IS_LOG) {
            Log.i("CallsAutoresponderApplication", "startSentInviteBonusKeyword=" + l4);
          }
          if (l4 > 0L)
          {
            l4 = KeywordUtils.getStopPeriodTime(l4, 1);
            if (Log.IS_LOG) {
              Log.i("CallsAutoresponderApplication", "stopInviteBonusTime=" + l4 + " now=" + l1);
            }
            if (l4 < l1)
            {
              localDbHandler.getKeywordSubscriptionsTbl().deleteSubscription(3);
              ((SettingsHandler)localObject1).saveInSettings("invite_send_bonus_keywords_start_time", 0L, false);
            }
          }
          if (Log.IS_LOG) {
            Log.i("CallsAutoresponderApplication", "--##--  RECEIVE BONUS  --##--");
          }
          l4 = ((SettingsHandler)localObject1).getLongFromSettings("invite_receive_bonus_keywords_start_time", 0L);
          if (Log.IS_LOG) {
            Log.i("CallsAutoresponderApplication", "startReceiveInviteBonusKeyword=" + l4);
          }
          if (l4 > 0L)
          {
            l4 = KeywordUtils.getStopPeriodTime(l4, 1);
            if (Log.IS_LOG) {
              Log.i("CallsAutoresponderApplication", "stopInviteBonusTime=" + l4 + " now=" + l1);
            }
            if (l4 < l1)
            {
              localDbHandler.getKeywordSubscriptionsTbl().deleteSubscription(4);
              ((SettingsHandler)localObject1).saveInSettings("invite_receive_bonus_keywords_start_time", 0L, false);
            }
          }
          if (Log.IS_LOG) {
            Log.i("CallsAutoresponderApplication", "--##--  BUILT IN SUBSCRIPTION  --##--");
          }
          if (Log.IS_LOG) {
            Log.i("CallsAutoresponderApplication", "HasKeywords startBuildInKeyword=" + l2 + " trialPeriod=" + 2592000000L + " stopTrialTime=" + l3);
          }
          if ((l2 > 0L) && (l3 < l1))
          {
            if (Log.IS_LOG) {
              Log.i("CallsAutoresponderApplication", "Delete built in keyword subscription.");
            }
            localDbHandler.getKeywordSubscriptionsTbl().deleteSubscription(1);
            ((SettingsHandler)localObject1).saveInSettings("build_in_keywords_start_time", 0L, true);
          }
          i = localDbHandler.getUsedKeywordsCount();
          j = localDbHandler.getKeywordSubscriptionsTbl().getKeywordCount();
          if (Log.IS_LOG) {
            Log.i("CallsAutoresponderApplication", "usedKeywordsCount=" + i + " paidKeywordsCount=" + j);
          }
          if (i > j)
          {
            l2 = ((SettingsHandler)localObject1).getLongFromSettings("warninng_time_start", 0L);
            if (Log.IS_LOG) {
              Log.i("CallsAutoresponderApplication", "warningTimeStart=" + l2 + " warningTimeOut=" + 259200000L);
            }
            localObject2 = NotificationHandler.getInstance(appContext);
            if (l2 > 0L) {
              break label1562;
            }
            if (Log.IS_LOG) {
              Log.i("CallsAutoresponderApplication", "WARNING_TIME_START");
            }
            ((SettingsHandler)localObject1).saveInSettings("warninng_time_start", l1, true);
            ((NotificationHandler)localObject2).showKeywordWarningNotification();
          }
        }
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        localException1.printStackTrace();
        versionName = "unknown";
        continue;
        lastLocale = Locale.getDefault();
      }
    }
    label1522:
    label1540:
    label1562:
    label1588:
    label1594:
    label1608:
    return;
  }
  
  protected void onUpgrade(int paramInt1, int paramInt2)
  {
    Log.i("CallsAutoresponderApplication", "appContext oldVersionCode: " + paramInt1 + " newVersionCode: " + paramInt2);
    SettingsHandler localSettingsHandler = SettingsHandler.getInstance(appContext);
    if (paramInt1 < 10)
    {
      Log.i("CallsAutoresponderApplication", "upgradeDefaultSettings ");
      localSettingsHandler.upgradeDefaultSettings();
    }
    if (paramInt1 < 23)
    {
      boolean bool = localSettingsHandler.getBooleanFromSettings("only_once", false);
      localSettingsHandler.saveInSettings("only_once", bool, false);
      Log.i("CallsAutoresponderApplication", "upgradeDefaultSettings ONLY_ONCE_REPLAY to " + bool);
      bool = localSettingsHandler.getBooleanFromSettings("vibrate_off_on_status", true);
      localSettingsHandler.saveInSettings("vibrate_off_on_status", bool, true);
      Log.i("CallsAutoresponderApplication", "upgradeDefaultSettings VIBRATE_OFF_ON_STATUS to " + bool);
    }
    if ((paramInt1 < 51) && (!localSettingsHandler.getBooleanFromSettings("show_ver_51_upd_news_dialog", false))) {
      localSettingsHandler.saveInSettings("show_ver_51_upd_news_dialog", true, true);
    }
    localSettingsHandler.saveInSettings("show_new_in_version", true, true);
    DynamicMenuService.updateDynamicMenuData(appContext, null);
    DbHandler.getInstance(appContext).getMenuTbl().refreshIcons(appContext);
  }
  
  public void registerPhoneStateReceiver()
  {
    Log.i("CallsAutoresponderApplication", "register PhoneState receiver");
    this._phoneReceiver = new DynamicPhoneStateReceiver();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PHONE_STATE");
    localIntentFilter.setPriority(Integer.MAX_VALUE);
    localIntentFilter.addCategory("android.intent.category.DEFAULT");
    appContext.registerReceiver(this._phoneReceiver, localIntentFilter);
    localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
    localIntentFilter.setPriority(Integer.MAX_VALUE);
    localIntentFilter.addCategory("android.intent.category.DEFAULT");
    appContext.registerReceiver(this._phoneReceiver, localIntentFilter, "android.permission.BROADCAST_SMS", null);
    localIntentFilter = new IntentFilter("android.provider.Telephony.GSM_SMS_RECEIVED");
    localIntentFilter.setPriority(Integer.MAX_VALUE);
    localIntentFilter.addCategory("android.intent.category.DEFAULT");
    appContext.registerReceiver(this._phoneReceiver, localIntentFilter, "android.permission.BROADCAST_SMS", null);
    _phoneListener = new CustomPhoneStateListener(appContext);
    startListenPhoneState(appContext);
  }
}
