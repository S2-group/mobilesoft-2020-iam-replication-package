package com.newbay.syncdrive.android.model.nab.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import com.fusionone.android.dsp.SimpleDb.SharedPreferenceDocumentStore;
import com.fusionone.android.dsp.SimpleDb.Treasure;
import com.fusionone.android.dsp.session.SessionManager;
import com.fusionone.android.dsp.session.SessionManagerImpl;
import com.fusionone.android.sync.provider.Dataclasses.DataclassStatistics;
import com.fusionone.android.sync.provider.Settings.SettingsTable;
import com.fusionone.android.wsgModel.accountProperties.AccountProperties;
import com.fusionone.android.wsgModel.accountProperties.Attributes;
import com.fusionone.android.wsgModel.accountProperties.Config;
import com.fusionone.syncml.sdk.utils.CryptUtil;
import com.newbay.syncdrive.android.model.R.bool;
import com.newbay.syncdrive.android.model.R.string;
import com.newbay.syncdrive.android.model.configuration.ApiConfigManager;
import com.newbay.syncdrive.android.model.util.AuthenticationStorage;
import com.newbay.syncdrive.android.model.util.sync.SyncConfigurationPrefHelper;
import com.synchronoss.mockable.android.content.IntentFactory;
import com.synchronoss.mockable.android.text.TextUtils;
import com.synchronoss.mockable.java.lang.ThreadUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NabUtil
{
  public static final String ACCOUNT_AUTHORIZATION_TYPE = "authtype";
  public static final String ACCOUNT_ELIGIBILITY = "account_eligibility";
  public static final String BA_INSTALLED = "BA_INSTALLED";
  private static final String[] BA_PACKAGE_NAME_PREFIX = { "com.fusionone.android.sync.service", "com.fusionone.android.sync.baclient", "com.fusionone.android.sync.sonyericssonr800xbaclient" };
  public static final String CH_PREF_FILENAME = "ch_prefs";
  public static final String COUNTRY_CODE = "1";
  public static final String COUNT_MAP = "count_map";
  private static final String DEBUG_PREF_FILE = "com.sncr.debug";
  private static final String DEBUG_PREF_KEY_GOOGLE_ENABLED = "is_google_contact_enabled";
  public static final String DEFAULT_SYNC = "default_sync";
  public static final String EMAIL_ADDRESS = "email";
  private static final String EMULATE_MDN = "f1.emulate.line1Number";
  public static final String FEATURE_CODE = "featureCode";
  public static final String HUX_TAG = "HYBRID_HUX";
  public static final String HYBRID_HUX_REPROVISION = "hybrid_hux_reprovision";
  public static final String LAST_SCHEDULE_SYNC_TIME = "last_sync_interval_time";
  public static final String LCID = "lcid";
  public static final String LOB_INDICATOR = "lobIndicator";
  private static final long MDN_ATTEMPTS = 1L;
  private static final String MDN_SEPERATOR = "-";
  private static final long MDN_WAIT_INTERVAL_TIME = 1000L;
  public static final String PERMISSION_DISCLIAMER_ACCEPT_FLAG = "permission_disclaimer_accepted";
  public static final String PREV_SNC_LOCATION_URI = "prev_location.uri";
  public static final String PROVISION_HYBRID_HUX = "provision_hybrid_hux";
  public static final String PROVISION_WITH_NEW_APP = "provision_with_new_app";
  public static final String RECOVERED_DEVICE = "recovered_device";
  public static final String RECOVERED_DEVICE_REPROVISION = "recovered_device_reprovision";
  public static final String REFRESH_TOKEN = "refresh_token";
  public static final String SELECT_BACKUP_SIZE = "backup_size";
  public static final String SIGN_UP_OBJECT = "sign_up_object_13_5";
  public static final String SIZE_MAP = "size_map";
  public static final String SL_TOKEN = "sl_token";
  public static final String SNC_LOCATION_URI = "location.uri";
  private static final String STORY_CREATION_TIME = "story_creation_time";
  private static final String SYSTEM_INFO_FILE_NAME = "system.properties";
  private final Context context;
  protected int dvAccountStatusCode;
  private boolean isAnyContactPendingCount;
  private final AccountManager mAccountManager;
  private final ApiConfigManager mApiConfigManager;
  private final IntentFactory mIntentFactory;
  private final TextUtils mTextUtils;
  private final ThreadUtils mThreadUtils;
  private final TelephonyManager telephonyManager;
  
  @Inject
  public NabUtil(Context paramContext, ApiConfigManager paramApiConfigManager, TelephonyManager paramTelephonyManager, TextUtils paramTextUtils, AccountManager paramAccountManager, ThreadUtils paramThreadUtils, IntentFactory paramIntentFactory)
  {
    this.context = paramContext;
    this.mApiConfigManager = paramApiConfigManager;
    this.telephonyManager = paramTelephonyManager;
    this.mTextUtils = paramTextUtils;
    this.mAccountManager = paramAccountManager;
    this.mThreadUtils = paramThreadUtils;
    this.mIntentFactory = paramIntentFactory;
  }
  
  private Account getAccount()
  {
    return new Account(this.context.getResources().getString(R.string.f), "com.vcast.mediamanager.account");
  }
  
  public long calculateFirstSyncTimeOnUpgrade()
  {
    long l1 = 0L;
    String str = getProvisionedMDN();
    if (isAuthorizationTypeVZT()) {
      str = getDeviceMdn();
    }
    if (str != null) {
      if (!str.startsWith("+")) {
        break label119;
      }
    }
    label119:
    for (l1 = Long.parseLong(str.substring(1, str.length()));; l1 = Long.parseLong(str))
    {
      int i = 0;
      if (this.mApiConfigManager.cL() != 0) {
        i = (int)(l1 % this.mApiConfigManager.cL());
      }
      int j = (int)(l1 % 24L);
      long l3 = System.currentTimeMillis();
      long l2 = (j + i * 24) * 3600000L;
      l1 = l2;
      if (l2 < 0L) {
        l1 = 0L;
      }
      l1 = l3 + l1;
      return l1;
    }
  }
  
  public void checkAndUpdateDVAccountStatus(Map<String, Object> paramMap, SyncConfigurationPrefHelper paramSyncConfigurationPrefHelper, AuthenticationStorage paramAuthenticationStorage)
  {
    if (paramMap.containsKey("dvAccountStatusCode"))
    {
      this.dvAccountStatusCode = ((Integer)paramMap.get("dvAccountStatusCode")).intValue();
      if ((this.dvAccountStatusCode == 4548) || (this.dvAccountStatusCode == 4546) || (this.dvAccountStatusCode == 4552))
      {
        SharedPreferences localSharedPreferences = this.context.getSharedPreferences("ch_prefs", 0);
        if (paramMap.containsKey("daystoreactivatedv")) {
          localSharedPreferences.edit().putString("daystoreactivatedv", (String)paramMap.get("daystoreactivatedv")).apply();
        }
        if (paramMap.containsKey("datetoreactivatedv")) {
          localSharedPreferences.edit().putString("datetoreactivatedv", (String)paramMap.get("datetoreactivatedv")).apply();
        }
        localSharedPreferences.edit().putInt("dvAccountStatusCode", this.dvAccountStatusCode).apply();
        paramSyncConfigurationPrefHelper.c(true);
        paramAuthenticationStorage.b(true);
      }
    }
  }
  
  public boolean checkMDNChange(boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = this.context.getSharedPreferences("ch_prefs", 0);
    if ((localSharedPreferences.getBoolean("MDN_CHANGED_RESET_APP", false)) || (isMDNChangeDetected())) {}
    for (int i = 1; (i != 0) && (isStateProvisioned()); i = 0)
    {
      if (localSharedPreferences.getBoolean("MDN_CHANGED_RESET_APP", false)) {
        localSharedPreferences.edit().putBoolean("RESET_APP", true);
      }
      localSharedPreferences.edit().putInt("nab_reset_code", 1).apply();
      if (paramBoolean) {
        displayErrorForMDNChange();
      }
      return true;
    }
    if ((i != 0) && (paramBoolean) && (localSharedPreferences.getBoolean("MDN_CHANGED_IGNORE_PROVISIONED_STATE_FOR_DIALOG", false))) {
      displayErrorForMDNChange();
    }
    return false;
  }
  
  public boolean checkPackagesIfBaClientInstalled()
  {
    Iterator localIterator = this.context.getPackageManager().getInstalledApplications(0).iterator();
    boolean bool = false;
    label95:
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName != null)
      {
        String[] arrayOfString = BA_PACKAGE_NAME_PREFIX;
        int j = arrayOfString.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            break label95;
          }
          String str = arrayOfString[i];
          if (localApplicationInfo.packageName.startsWith(str))
          {
            bool = true;
            break;
          }
          i += 1;
        }
      }
    }
    return bool;
  }
  
  public boolean checkTabletLinkedMdnChange(String paramString)
  {
    if (isAuthorizationTypeVZT()) {
      return isTabletLinkedVztUserIdChanged(paramString);
    }
    return isMdnChanged(getProvisionedMdnFromPreferences(), paramString);
  }
  
  public boolean checkTabletMdnChange()
  {
    String str1 = getDeviceMdn();
    String str2 = getNabPreferences().getString("tabletNumber", null);
    return (!TextUtils.a(str1)) && (!TextUtils.a(str2)) && (!str1.equals(str2));
  }
  
  public boolean displayAccountDeletedError()
  {
    SharedPreferences localSharedPreferences = this.context.getSharedPreferences("ch_prefs", 0);
    if (localSharedPreferences.getBoolean("RE_PROVISION_NEEDED", false))
    {
      localSharedPreferences.edit().putBoolean("RESET_APP", true);
      displayErrorForAccountDeleted();
      return true;
    }
    return false;
  }
  
  public boolean displayAccountSuspendedError()
  {
    SharedPreferences localSharedPreferences = this.context.getSharedPreferences("ch_prefs", 0);
    if (localSharedPreferences.getBoolean("NAB_RESET_ACCOUNT_SUSPENDED", false))
    {
      localSharedPreferences.edit().putBoolean("RESET_APP", true);
      displayErrorForAccountSuspended();
      return true;
    }
    return false;
  }
  
  public void displayErrorForAccountDeleted()
  {
    Intent localIntent = IntentFactory.a("com.newbay.syncdrive.intent.action.ERROR_DIALOG_ACTIVITY");
    localIntent.setFlags(268435456);
    localIntent.putExtra("nab_reset_code", 3);
    this.context.startActivity(localIntent);
  }
  
  public void displayErrorForAccountSuspended()
  {
    Intent localIntent = IntentFactory.a("com.newbay.syncdrive.intent.action.ERROR_DIALOG_ACTIVITY");
    localIntent.setFlags(268435456);
    localIntent.putExtra("nab_reset_code", 4);
    this.context.startActivity(localIntent);
  }
  
  public void displayErrorForMDNChange()
  {
    Intent localIntent = IntentFactory.a("com.newbay.syncdrive.intent.action.ERROR_DIALOG_ACTIVITY");
    localIntent.setFlags(268435456);
    localIntent.putExtra("nab_reset_code", 1);
    this.context.startActivity(localIntent);
  }
  
  public String formatMDN(String paramString)
  {
    String[] arrayOfString = paramString.split("-");
    if (arrayOfString.length > 1)
    {
      paramString = new StringBuilder();
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        paramString.append(arrayOfString[i]);
        i += 1;
      }
      paramString = paramString.toString();
    }
    return paramString;
  }
  
  public AccountProperties getAccountProperties()
  {
    return (AccountProperties)Treasure.a(this.context, "Account_props").a("account_properties", AccountProperties.class);
  }
  
  public int getCalculatedRandomizationConfigDays()
  {
    return getNabPreferences().getInt("randomizationBaseConfigDays", this.mApiConfigManager.cL());
  }
  
  public boolean getContactPendingCount()
  {
    return this.isAnyContactPendingCount;
  }
  
  public int getContactsCount(Context paramContext)
  {
    if ((this.mApiConfigManager.bN()) || (isAuthorizationTypeVZT())) {}
    int i;
    for (String str = getProvisionedMdnFromPreferences();; str = getDeviceMdn())
    {
      paramContext = Dataclasses.DataclassStatistics.getByDataclassName(paramContext.getContentResolver(), "contacts", str);
      if (paramContext == null) {
        break label69;
      }
      i = paramContext.getPendingItemsCount();
      if (i <= 0) {
        break;
      }
      setContactPendingCount(true);
      return i;
    }
    setContactPendingCount(false);
    return i;
    label69:
    return 0;
  }
  
  public int getContactsSyncStatsFromDatabase()
  {
    Integer localInteger = Settings.SettingsTable.getIntSetting(this.context.getContentResolver(), "contacts.sync");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public String getDecryptedValue(String paramString)
  {
    if (paramString != null) {
      return CryptUtil.b(paramString);
    }
    return null;
  }
  
  public String getDeviceMdn()
  {
    Object localObject3 = Settings.System.getString(this.context.getContentResolver(), "f1.emulate.line1Number");
    Object localObject1;
    if (localObject3 != null)
    {
      localObject1 = localObject3;
      if (!((String)localObject3).equals("")) {}
    }
    else
    {
      int i = 0;
      localObject1 = localObject3;
      for (;;)
      {
        if (i <= 1L)
        {
          localObject3 = this.telephonyManager.getLine1Number();
          localObject1 = localObject3;
          if (!showTabletUI())
          {
            localObject1 = localObject3;
            if (!this.mApiConfigManager.bM()) {
              if (localObject3 != null)
              {
                localObject1 = localObject3;
                if (((String)localObject3).length() != 0) {}
              }
              else
              {
                localObject1 = localObject3;
                if (i < 1L) {
                  try
                  {
                    ThreadUtils.a(1000L);
                    i += 1;
                    localObject1 = localObject3;
                  }
                  catch (InterruptedException localInterruptedException)
                  {
                    for (;;)
                    {
                      localInterruptedException.printStackTrace();
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    localObject3 = localInterruptedException;
    if (localInterruptedException != null)
    {
      localObject3 = localInterruptedException;
      if (!localInterruptedException.equals(""))
      {
        localObject3 = localInterruptedException;
        if (localInterruptedException.startsWith("+")) {
          localObject3 = localInterruptedException.substring(1);
        }
      }
    }
    Object localObject2 = localObject3;
    if (localObject3 != null)
    {
      localObject2 = localObject3;
      if (((String)localObject3).length() == 10) {
        localObject2 = "1" + (String)localObject3;
      }
    }
    return localObject2;
  }
  
  public String getDisplayMdn(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (!isAuthorizationTypeVZT()) {
      if ((paramString.length() == 11) && (paramString.startsWith("1"))) {
        localStringBuilder.append(paramString.substring(1, 4)).append("-").append(paramString.substring(4, 7)).append("-").append(paramString.substring(7, 11));
      }
    }
    for (;;)
    {
      return localStringBuilder.toString();
      if ((paramString.length() == 11) && (!paramString.startsWith("1")))
      {
        localStringBuilder.append(paramString.substring(0, 3)).append("-").append(paramString.substring(3, 6)).append("-").append(paramString.substring(6, 11));
      }
      else if (paramString.length() == 10)
      {
        localStringBuilder.append(paramString.substring(0, 3)).append("-").append(paramString.substring(3, 6)).append("-").append(paramString.substring(6, 10));
      }
      else
      {
        localStringBuilder.append(paramString);
        continue;
        localStringBuilder.append(paramString);
      }
    }
  }
  
  public String getEmailAddress()
  {
    return Settings.SettingsTable.getStringSetting(this.context.getContentResolver(), "email.address");
  }
  
  public List<String> getGoogleAccount()
  {
    ArrayList localArrayList = new ArrayList();
    Account[] arrayOfAccount = this.mAccountManager.getAccountsByType("com.google");
    int j = arrayOfAccount.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(arrayOfAccount[i].name);
      i += 1;
    }
    return localArrayList;
  }
  
  public SharedPreferences getNabPreferences()
  {
    return this.context.getSharedPreferences("ch_prefs", 0);
  }
  
  public int getNabSyncInterval()
  {
    int j = 1;
    Object localObject = getAccountProperties();
    int i = j;
    if (localObject != null)
    {
      i = j;
      if (((AccountProperties)localObject).c() != null)
      {
        localObject = ((AccountProperties)localObject).c().a();
        i = j;
        if (localObject != null)
        {
          i = j;
          if (((String)localObject).length() > 0) {
            i = Integer.parseInt((String)localObject);
          }
        }
      }
    }
    return i;
  }
  
  public String getProvisionedMDN()
  {
    return getNabPreferences().getString("phoneNumber", null);
  }
  
  public String getProvisionedMdnFromPreferences()
  {
    return getNabPreferences().getString("phoneNumber", null);
  }
  
  public SessionManager getSessionManagerInstance(Context paramContext)
  {
    return new SessionManagerImpl(paramContext);
  }
  
  public int getUpdateCheckValueFromTreasure()
  {
    Object localObject = (AccountProperties)Treasure.a(this.context, "Account_props").a("account_properties", AccountProperties.class);
    if ((localObject != null) && (((AccountProperties)localObject).c() != null))
    {
      localObject = ((AccountProperties)localObject).c().d();
      if ((localObject != null) && (((String)localObject).length() > 0)) {
        return Integer.parseInt((String)localObject);
      }
    }
    return -1;
  }
  
  public long getUpgradeFirstSyncRandomizeTime()
  {
    return Treasure.a(this.context, "Session_Info").b("upgradeFirstSyncRandomizeTime", 0L);
  }
  
  public boolean isAfterContentTransfer()
  {
    return getNabPreferences().getBoolean("is_after_content_transfer", false);
  }
  
  public boolean isAuthorizationTypeVZT()
  {
    boolean bool2 = false;
    String str = getNabPreferences().getString("authtype", null);
    boolean bool1 = bool2;
    if (str != null)
    {
      bool1 = bool2;
      if (str.equalsIgnoreCase("CID")) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isGoogleContactEnabled()
  {
    Object localObject2 = this.context.getSharedPreferences("com.sncr.debug", 0).getString("is_google_contact_enabled", null);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new Properties();
      localObject2 = this.context.getResources().getAssets();
    }
    try
    {
      ((Properties)localObject1).load(((AssetManager)localObject2).open("system.properties"));
      localObject1 = ((Properties)localObject1).getProperty("is_google_contact_enabled");
      return Boolean.parseBoolean((String)localObject1);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public boolean isGovtAccountStatus()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (getNabPreferences().getInt("dvAccountStatusCode", 0) == 4552)
    {
      bool1 = bool2;
      if (this.mApiConfigManager.cQ()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isMDNChangeDetected()
  {
    if ((!showTabletUI()) && (!this.mApiConfigManager.bM())) {
      return isMdnChanged(getProvisionedMdnFromPreferences(), getDeviceMdn());
    }
    return checkTabletMdnChange();
  }
  
  protected boolean isMdnChanged(String paramString1, String paramString2)
  {
    if ((!isAuthorizationTypeVZT()) && (!TextUtils.a(paramString1)) && (!TextUtils.a(paramString2)))
    {
      String str = paramString2;
      if (paramString2.length() > 10) {
        str = paramString2.substring(1);
      }
      paramString2 = paramString1;
      if (paramString1.length() > 10) {
        paramString2 = paramString1.substring(1);
      }
      if (!str.equals(paramString2)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isStateProvisioned()
  {
    Integer localInteger = Settings.SettingsTable.getIntSetting(this.context.getContentResolver(), "app.state");
    return (localInteger != null) && (1 == localInteger.intValue());
  }
  
  public boolean isStateProvisioningError()
  {
    Integer localInteger = Settings.SettingsTable.getIntSetting(this.context.getContentResolver(), "app.state");
    return (localInteger != null) && (3 == localInteger.intValue());
  }
  
  public boolean isStateProvisioningInProgress()
  {
    Integer localInteger = Settings.SettingsTable.getIntSetting(this.context.getContentResolver(), "app.state");
    return (localInteger != null) && (2 == localInteger.intValue());
  }
  
  public boolean isSyncTriggerAfterUpgrade()
  {
    return getUpgradeFirstSyncRandomizeTime() == 0L;
  }
  
  public boolean isTOSAccepted()
  {
    Object localObject = getAccountProperties();
    if (localObject != null)
    {
      localObject = ((AccountProperties)localObject).b();
      if ((localObject != null) && (!((Attributes)localObject).a())) {
        return false;
      }
    }
    return true;
  }
  
  protected boolean isTabletLinkedVztUserIdChanged(String paramString)
  {
    String str = getNabPreferences().getString("loginUsername", "");
    return (!TextUtils.a(str)) && (!str.equalsIgnoreCase(paramString));
  }
  
  public boolean isVZTLoginFromTools()
  {
    return getNabPreferences().getBoolean("vzt_sign_in_from_tools", false);
  }
  
  public void removeCloudAccountReset(String paramString)
  {
    this.mAccountManager.removeAccount(getAccount(), new NabUtil.AccountRemoveCallBack(this, paramString, this.context), null);
  }
  
  public void resetAppUpgradeValue()
  {
    AccountProperties localAccountProperties = getAccountProperties();
    if ((localAccountProperties != null) && (localAccountProperties.c() != null))
    {
      localAccountProperties.c().c(null);
      saveAccountProperties(localAccountProperties);
    }
  }
  
  public void saveAccountProperties(AccountProperties paramAccountProperties)
  {
    Treasure.a(this.context, "Account_props").a("account_properties", paramAccountProperties);
  }
  
  public void saveAfterContentTransfer(boolean paramBoolean)
  {
    getNabPreferences().edit().putBoolean("is_after_content_transfer", paramBoolean).commit();
  }
  
  public void saveTOSAccepted()
  {
    AccountProperties localAccountProperties = getAccountProperties();
    if (localAccountProperties != null)
    {
      Attributes localAttributes = localAccountProperties.b();
      if (localAttributes != null)
      {
        localAttributes.a(true);
        saveAccountProperties(localAccountProperties);
      }
    }
  }
  
  public void setCalculatedRandomizationConfigDays(int paramInt)
  {
    getNabPreferences().edit().putInt("randomizationBaseConfigDays", paramInt).apply();
  }
  
  public void setContactPendingCount(boolean paramBoolean)
  {
    this.isAnyContactPendingCount = paramBoolean;
  }
  
  public void setGovtAccFeatureFlag()
  {
    getNabPreferences().edit().putBoolean("govt_account_feature_enabled", this.mApiConfigManager.cQ()).apply();
  }
  
  public void setLoggedOutFalse()
  {
    SharedPreferences.Editor localEditor = getNabPreferences().edit();
    localEditor.putBoolean("logout", false);
    localEditor.remove("lcid");
    localEditor.apply();
  }
  
  public void setUpgradeFirstSyncRandomizeTime(long paramLong)
  {
    Treasure.a(this.context, "Session_Info").a("upgradeFirstSyncRandomizeTime", paramLong);
  }
  
  public void settingUpVobsFeature()
  {
    if (this.mApiConfigManager.cF()) {
      getNabPreferences().edit().putBoolean("vobs_feature_support", true).apply();
    }
  }
  
  public boolean showTabletUI()
  {
    return this.context.getResources().getBoolean(R.bool.m);
  }
}
