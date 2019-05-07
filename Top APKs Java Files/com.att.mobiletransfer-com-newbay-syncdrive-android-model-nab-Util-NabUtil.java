package com.newbay.syncdrive.android.model.nab.Util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract.Profile;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import com.fusionone.android.dsp.log.LogImpl;
import com.fusionone.android.sync.provider.ContactsCloud.Contacts;
import com.fusionone.android.sync.provider.ContactsCloud.Data;
import com.fusionone.android.sync.provider.Dataclasses.DataclassStatistics;
import com.fusionone.android.sync.provider.Settings.SettingsTable;
import com.fusionone.android.wsgModel.contactsCommonObjects.Contact;
import com.fusionone.android.wsgModel.contactsCommonObjects.Telephone;
import com.fusionone.android.wsgModel.contactsCommonObjects.TelephoneTypeEnum;
import com.newbay.syncdrive.android.model.R.bool;
import com.newbay.syncdrive.android.model.configuration.ApiConfigManager;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NabUtil
{
  public static final String BA_INSTALLED = "BA_INSTALLED";
  private static final String[] BA_PACKAGE_NAME_PREFIX = { "com.fusionone.android.sync.service", "com.fusionone.android.sync.baclient", "com.fusionone.android.sync.sonyericssonr800xbaclient" };
  public static final String CH_PREF_FILENAME = "ch_prefs";
  private static final String COUNTRY_CODE = "1";
  public static final String COUNT_MAP = "count_map";
  private static final String DEBUG_PREF_FILE = "com.sncr.debug";
  private static final String DEBUG_PREF_KEY_GOOGLE_ENABLED = "is_google_contact_enabled";
  public static final String DEFAULT_SYNC = "default_sync";
  public static final String EMAIL_ADDRESS = "email";
  private static final String EMULATE_MDN = "f1.emulate.line1Number";
  public static final String FEATURE_CODE = "featureCode";
  public static final String GOOGLE_CONTACTS_PREFERENCES = "google_contacts_pref";
  public static final String HUX_TAG = "HYBRID_HUX";
  public static final String HYBRID_HUX_REPROVISION = "hybrid_hux_reprovision";
  private static final String IN_COUNTRY_CODE = "91";
  public static final String IS_BUDDY_SYNC_ON = "is_buddy_sync_on";
  public static final String LCID = "lcid";
  public static final String MANDATORY_UPDATE = "mandatory_update";
  private static final long MDN_ATTEMPTS = 1L;
  private static final String MDN_SEPERATOR = "-";
  private static final long MDN_WAIT_INTERVAL_TIME = 1000L;
  public static final String NAB_TOKEN = "nab_token";
  public static final String PREV_SNC_LOCATION_URI = "prev_location.uri";
  public static final String PROVISION_HYBRID_HUX = "provision_hybrid_hux";
  public static final String PROVISION_WITH_NEW_APP = "provision_with_new_app";
  public static final String REFRESH_TOKEN = "refresh_token";
  public static final String RIL_EMAIL = "ril_email";
  public static final String SELECT_BACKUP_SIZE = "backup_size";
  public static final String SIGN_UP_OBJECT = "sign_up_object_13_5";
  public static final String SIZE_MAP = "size_map";
  public static final String SL_TOKEN = "sl_token";
  public static final String SNC_LOCATION_URI = "location.uri";
  public static final String SYNC_CONTACTS_AFTER_UPGRADE = "sync_contacts_after_upgrade";
  private static final String SYSTEM_INFO_FILE_NAME = "system.properties";
  public ArrayList contactChanges;
  private final Context context;
  @Inject
  ApiConfigManager mApiConfigManager;
  public HashMap snapshotmap;
  @Inject
  TelephonyManager telephonyManager;
  
  @Inject
  public NabUtil(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private String getSuffix(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "'th'";
    case 1: 
    case 21: 
    case 31: 
      return "'st'";
    case 2: 
    case 22: 
      return "'nd'";
    }
    return "'rd'";
  }
  
  private static boolean isMdnChanged(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null))
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
  
  public void applyGoogleContactEnabled(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.context.getSharedPreferences("com.sncr.debug", 0).edit();
    localEditor.putString("is_google_contact_enabled", String.valueOf(paramBoolean));
    localEditor.commit();
  }
  
  public boolean checkMdnChange()
  {
    return isMdnChanged(getProvisionedMdnFromPreferences(), getDeviceMdn());
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
    return isMdnChanged(getProvisionedMdnFromPreferences(), paramString);
  }
  
  public boolean checkTabletMdnChange()
  {
    return (getNabPreferences().getString("tabletNumber", null) != null) && (!getNabPreferences().getString("tabletNumber", null).equals(getDeviceMdn()));
  }
  
  public void clearSnapshotmap()
  {
    if (this.snapshotmap != null) {
      this.snapshotmap.clear();
    }
  }
  
  public void deleteDataStatistics(String paramString)
  {
    Handler localHandler = new Handler(Looper.getMainLooper());
    Dataclasses.DataclassStatistics.deleteAccount(this.context.getContentResolver(), new NabUtil.1(this, localHandler), paramString);
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
  
  public String formattedDate(String paramString)
  {
    if (paramString != null) {
      try
      {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss zzz", Locale.US);
        localSimpleDateFormat.setLenient(false);
        long l = localSimpleDateFormat.parse(paramString).getTime();
        l = (new Date().getTime() - l) / 86400000L;
        return String.valueOf(l);
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  public ArrayList getContactChanges()
  {
    return this.contactChanges;
  }
  
  public int getContactsSyncStatsFromDatabase()
  {
    Integer localInteger = Settings.SettingsTable.getIntSetting(this.context.getContentResolver(), "contacts.sync");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public String getDate(int paramInt)
  {
    try
    {
      Object localObject1 = Calendar.getInstance();
      Object localObject2 = new Date();
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MMMM dd yyyy", Locale.US);
      ((Calendar)localObject1).setTime(localSimpleDateFormat.parse(localSimpleDateFormat.format((Date)localObject2)));
      ((Calendar)localObject1).add(5, -paramInt);
      localObject1 = ((Calendar)localObject1).getTime();
      localObject2 = getSuffix(Integer.parseInt(new SimpleDateFormat("dd", Locale.US).format((Date)localObject1)));
      localObject1 = new SimpleDateFormat("MMMM dd" + (String)localObject2 + " yyyy", Locale.US).format((Date)localObject1);
      return localObject1;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return "";
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
            if (!this.mApiConfigManager.bW()) {
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
                    Thread.sleep(1000L);
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
  
  public String getDevicePhoneNumber()
  {
    Object localObject1 = "";
    int i = 0;
    for (;;)
    {
      if (i <= 1L)
      {
        System.out.println("ATTEMPT  :: " + i);
        localObject3 = this.telephonyManager.getLine1Number();
        System.out.println("ATTEMPT  number :: " + (String)localObject3);
        localObject1 = localObject3;
        if (!showTabletUI())
        {
          localObject1 = localObject3;
          if (!this.mApiConfigManager.bW()) {
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
                  Thread.sleep(1000L);
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
    Object localObject3 = localInterruptedException;
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
      if (((String)localObject3).length() > 10)
      {
        localObject2 = localObject3;
        if (((String)localObject3).startsWith("91")) {
          localObject2 = ((String)localObject3).substring(2);
        }
      }
    }
    if (localObject2 != null)
    {
      localObject3 = localObject2;
      if (localObject2 != null)
      {
        localObject3 = localObject2;
        if (((String)localObject2).length() >= 10) {}
      }
    }
    else
    {
      localObject3 = "";
    }
    System.out.println("ATTEMPT  ::return Mdn " + (String)localObject3);
    return localObject3;
  }
  
  public String getDisplayMdn(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramString.length() == 11) && (paramString.startsWith("1"))) {
      localStringBuilder.append(paramString.substring(1, 4)).append("-").append(paramString.substring(4, 7)).append("-").append(paramString.substring(7, 11));
    }
    for (;;)
    {
      return localStringBuilder.toString();
      if ((paramString.length() == 11) && (!paramString.startsWith("1"))) {
        localStringBuilder.append(paramString.substring(0, 3)).append("-").append(paramString.substring(3, 6)).append("-").append(paramString.substring(6, 11));
      } else if (paramString.length() == 10) {
        localStringBuilder.append(paramString.substring(0, 3)).append("-").append(paramString.substring(3, 6)).append("-").append(paramString.substring(6, 10));
      } else {
        localStringBuilder.append(paramString);
      }
    }
  }
  
  public List<String> getGoogleAccount()
  {
    ArrayList localArrayList = new ArrayList();
    Account[] arrayOfAccount = AccountManager.get(this.context).getAccountsByType("com.google");
    int j = arrayOfAccount.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(arrayOfAccount[i].name);
      i += 1;
    }
    return localArrayList;
  }
  
  public int getGoogleSyncStatusFromDatabase()
  {
    Integer localInteger = Settings.SettingsTable.getIntSetting(this.context.getContentResolver(), "google.contacts.sync");
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  public SharedPreferences getNabPreferences()
  {
    return this.context.getSharedPreferences("ch_prefs", 0);
  }
  
  public Contact getProfileContactFromDb(Cursor paramCursor)
  {
    for (int i = 0; paramCursor.moveToNext(); i = paramCursor.getInt(paramCursor.getColumnIndex("_id"))) {}
    Object localObject = this.context.getContentResolver().query(ContactsCloud.Data.CONTENT_URI, null, "contact_id=?", new String[] { String.valueOf(i) }, null);
    paramCursor = Contact.a(LogImpl.b());
    paramCursor.e(true);
    Telephone localTelephone = new Telephone();
    while (((Cursor)localObject).moveToNext())
    {
      String str1 = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("mimetype"));
      if (str1.equals("vnd.vcast.cursor.item/name"))
      {
        str1 = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("data2"));
        String str2 = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("data3"));
        paramCursor.e(str1);
        paramCursor.j(str2);
      }
      else if (str1.equals("vnd.vcast.cursor.item/phone_v2"))
      {
        i = ((Cursor)localObject).getInt(((Cursor)localObject).getColumnIndex("data2"));
        localTelephone.a(((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("data1")));
        if (i == 2) {
          localTelephone.d(TelephoneTypeEnum.MOBILE.toString());
        }
      }
    }
    localObject = new ArrayList();
    ((List)localObject).add(localTelephone);
    paramCursor.d((List)localObject);
    return paramCursor;
  }
  
  public int getProfileContactId()
  {
    Cursor localCursor = this.context.getContentResolver().query(ContactsCloud.Contacts.CONTENT_URI, new String[] { "_id" }, "profile=?", new String[] { "1" }, null);
    if (localCursor.getCount() > 0) {
      for (i = 0; localCursor.moveToNext(); i = localCursor.getInt(localCursor.getColumnIndex("_id"))) {}
    }
    int i = 0;
    localCursor.close();
    return i;
  }
  
  public Cursor getProfileDataExistWithDirtyFlag()
  {
    return this.context.getContentResolver().query(ContactsCloud.Contacts.CONTENT_URI, null, "profile=1 AND dirty=?", new String[] { "1" }, null);
  }
  
  public String getProvisionedMDN()
  {
    return getNabPreferences().getString("phoneNumber", null);
  }
  
  public String getProvisionedMdnFromPreferences()
  {
    return getNabPreferences().getString("phoneNumber", null);
  }
  
  public HashMap getSnapshotmap()
  {
    return this.snapshotmap;
  }
  
  public boolean isBuddySyncStatusOn()
  {
    return this.context.getSharedPreferences("ch_prefs", 0).getBoolean("is_buddy_sync_on", false);
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
      localObject2 = ((AssetManager)localObject2).open("system.properties");
      if (localObject2 != null) {
        ((Properties)localObject1).load((InputStream)localObject2);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    localObject1 = ((Properties)localObject1).getProperty("is_google_contact_enabled");
    return Boolean.parseBoolean((String)localObject1);
  }
  
  public boolean isGoogleEndPointProvisioned()
  {
    Integer localInteger = Settings.SettingsTable.getIntSetting(this.context.getContentResolver(), "google_contacts_setting");
    return (localInteger != null) && (1 == localInteger.intValue());
  }
  
  public boolean isMDNChangeDetected()
  {
    String str = getDeviceMdn();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (str != null)
    {
      bool1 = bool2;
      if (str.length() > 0)
      {
        bool1 = bool2;
        if (this.context.getResources().getBoolean(R.bool.u))
        {
          if ((showTabletUI()) || (this.mApiConfigManager.bW())) {
            break label64;
          }
          bool1 = checkMdnChange();
        }
      }
    }
    return bool1;
    label64:
    return checkTabletMdnChange();
  }
  
  public boolean isSimAvailable()
  {
    return ((TelephonyManager)this.context.getSystemService("phone")).getSimState() != 1;
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
  
  public boolean isUserProfileExist()
  {
    Cursor localCursor = this.context.getContentResolver().query(ContactsCloud.Contacts.CONTENT_URI, null, "profile=?", new String[] { "1" }, null);
    return (localCursor != null) && (localCursor.getCount() > 0);
  }
  
  public boolean isValidMdn(String paramString)
  {
    return ((paramString.length() == 11) && (paramString.startsWith("1"))) || (paramString.length() == 10);
  }
  
  @TargetApi(14)
  public String[] readMeCardInformation()
  {
    String[] arrayOfString = new String[3];
    Cursor localCursor = this.context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, "data"), new String[] { "data1", "is_primary", "data3", "data2", "data1", "is_primary", "photo_uri", "mimetype" }, "data_set IS NULL AND (mimetype=? OR mimetype=? OR mimetype=? OR mimetype =? )", new String[] { "vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/name", "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/photo" }, "is_primary DESC");
    while (localCursor.moveToNext())
    {
      String str = localCursor.getString(7);
      if (str.equals("vnd.android.cursor.item/name"))
      {
        arrayOfString[0] = localCursor.getString(3);
        arrayOfString[1] = localCursor.getString(2);
      }
      else if ((str.equals("vnd.android.cursor.item/phone_v2")) && (localCursor.getInt(localCursor.getColumnIndex("data2")) == 2))
      {
        arrayOfString[2] = localCursor.getString(localCursor.getColumnIndex("data1"));
      }
    }
    localCursor.close();
    return arrayOfString;
  }
  
  public void setContactChanges(ArrayList paramArrayList)
  {
    this.contactChanges = paramArrayList;
  }
  
  public void setSnapshotmap(HashMap paramHashMap)
  {
    this.snapshotmap = paramHashMap;
  }
  
  public boolean showTabletUI()
  {
    return this.context.getResources().getBoolean(R.bool.aP);
  }
  
  public void updateBuddySyncStatus(boolean paramBoolean)
  {
    this.context.getSharedPreferences("ch_prefs", 0).edit().putBoolean("is_buddy_sync_on", paramBoolean).commit();
  }
  
  public void updateGoolgeEnpointSettings(int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", "google_contacts_setting");
    localContentValues.put("value", Integer.valueOf(paramInt));
    localContentValues.put("type", "0");
    localContentValues.put("dirty", "0");
    Settings.SettingsTable.updateSettingByName(this.context.getContentResolver(), "google_contacts_setting", localContentValues);
  }
}
