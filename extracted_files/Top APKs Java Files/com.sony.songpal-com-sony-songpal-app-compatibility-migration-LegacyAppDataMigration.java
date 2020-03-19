package com.sony.songpal.app.compatibility.migration;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import com.sony.songpal.app.SongPal;
import com.sony.songpal.app.controller.addapps.AddAppsLoader.VoiceType;
import com.sony.songpal.app.storage.AddAppsDatabaseHelper;
import com.sony.songpal.app.storage.AddAppsDatabaseHelper.AppEntry;
import com.sony.songpal.app.storage.AppSettingsPreference;
import com.sony.songpal.app.storage.KeysPreference;
import com.sony.songpal.foundation.Capability;
import com.sony.songpal.foundation.FoundationStorage;
import com.sony.songpal.foundation.device.BdAddress;
import com.sony.songpal.foundation.device.DeviceId;
import com.sony.songpal.foundation.device.DeviceIdProvider;
import com.sony.songpal.foundation.device.IdSyntaxException;
import com.sony.songpal.foundation.device.MacAddress;
import com.sony.songpal.foundation.device.RecognitionManager;
import com.sony.songpal.foundation.device.UpnpUuid;
import com.sony.songpal.util.IOUtil;
import com.sony.songpal.util.SpLog;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;

public class LegacyAppDataMigration
{
  private static final String a = LegacyAppDataMigration.class.getSimpleName();
  
  public LegacyAppDataMigration() {}
  
  public static void a()
  {
    Object localObject = PreferenceManager.getDefaultSharedPreferences(SongPal.a());
    if (((SharedPreferences)localObject).getInt("SongPalVersionCodeKey", -1) == -1) {
      SpLog.c(a, "Legacy Preferences has no SongPal version code key");
    }
    do
    {
      return;
      AppSettingsPreference.c(((SharedPreferences)localObject).getBoolean("SmartExtrasSettingKey", false));
      AppSettingsPreference.b(((SharedPreferences)localObject).getBoolean("TextMessageAutoReadKey", false));
      AppSettingsPreference.a(((SharedPreferences)localObject).getBoolean("LaunchConfirmAgreement", true));
      AppSettingsPreference.d(((SharedPreferences)localObject).getBoolean("DefaultMobileAppsRegisted", false));
      ((SharedPreferences)localObject).edit().clear().apply();
      SpLog.c(a, "Cleared default shared preferences");
      localObject = SongPal.a();
      Iterator localIterator = ((Context)localObject).getPackageManager().getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        SharedPreferences localSharedPreferences = ((Context)localObject).getSharedPreferences(localPackageInfo.packageName, 0);
        if (localSharedPreferences.getAll().keySet().contains("ExtensionEnableKey"))
        {
          boolean bool = localSharedPreferences.getBoolean("ExtensionEnableKey", false);
          AppSettingsPreference.a(localPackageInfo.packageName, bool);
          localSharedPreferences.edit().clear().apply();
        }
      }
      localObject = SongPal.a().getSharedPreferences("SONGPAL_NETWORK_SERVICE", 0).getString("GnUserId", null);
    } while (localObject == null);
    KeysPreference.a((String)localObject);
  }
  
  public static void a(FoundationStorage paramFoundationStorage)
  {
    if (!SongPal.a().getDatabasePath("audiocomp_database").exists())
    {
      SpLog.c(a, "Legacy DB does not exist");
      return;
    }
    HistoryDeviceDatabaseOpenHelper localHistoryDeviceDatabaseOpenHelper = new HistoryDeviceDatabaseOpenHelper();
    for (;;)
    {
      try
      {
        localCursor = localHistoryDeviceDatabaseOpenHelper.getReadableDatabase().query("audiocomp_history_device", new String[] { "bt_address", "bt_name", "supported_ctrl_line_from_bt", "line_priority_from_bt", "dmr_uuid_from_bt", "upnp_uuid_from_bt", "mac_address_from_bt1", "mac_address_from_bt2", "mac_address_from_bt3", "mac_address_from_bt4", "mac_address_from_bt5", "device_name_from_bt", "device_version_from_bt", "is_osusowake_target", "nw_address1", "nw_address2", "nw_address3", "nw_address4", "nw_address5", "nw_name", "nw_dmr_uuid", "nw_has_dmr", "unique_id", "never_display_osusowake_flag", "total_ctrl_line", "time" }, null, null, null, null, null, null);
      }
      finally
      {
        DeviceIdProvider localDeviceIdProvider;
        boolean bool;
        Capability localCapability;
        int i;
        DeviceId localDeviceId;
        Cursor localCursor = null;
        continue;
        Object localObject = null;
        continue;
        i += 1;
        continue;
        i += 1;
        continue;
      }
      try
      {
        localDeviceIdProvider = new DeviceIdProvider(paramFoundationStorage);
        bool = localCursor.moveToFirst();
        if (!bool) {
          continue;
        }
        localCapability = new Capability();
        localCursor.getString(localCursor.getColumnIndex("unique_id"));
        localCursor.getColumnIndex("supported_ctrl_line_from_bt");
        localCursor.getColumnIndex("line_priority_from_bt");
        try
        {
          str1 = localCursor.getString(localCursor.getColumnIndex("bt_address"));
          if (str1 != null) {
            localCapability.a(new BdAddress(str1));
          }
        }
        catch (IdSyntaxException localIdSyntaxException1)
        {
          String str1;
          SpLog.a(a, localIdSyntaxException1);
          continue;
        }
        localCapability.d(localCursor.getString(localCursor.getColumnIndex("bt_name")));
        localCursor.getString(localCursor.getColumnIndex("dmr_uuid_from_bt"));
        localCursor.getString(localCursor.getColumnIndex("upnp_uuid_from_bt"));
        i = 1;
        if (i >= 6) {
          continue;
        }
        str1 = localCursor.getString(localCursor.getColumnIndex("mac_address_from_bt" + i));
        if (str1 != null) {
          continue;
        }
        continue;
      }
      finally {}
      IOUtil.a(localCursor);
      throw paramFoundationStorage;
      try
      {
        localCapability.a(new MacAddress(localIdSyntaxException1));
      }
      catch (IdSyntaxException localIdSyntaxException2)
      {
        SpLog.a(a, localIdSyntaxException2);
      }
      localCursor.getString(localCursor.getColumnIndex("device_name_from_bt"));
      localCapability.e(localCursor.getString(localCursor.getColumnIndex("device_version_from_bt")));
      localCursor.getInt(localCursor.getColumnIndex("is_osusowake_target"));
      localCursor.getInt(localCursor.getColumnIndex("never_display_osusowake_flag"));
      localCapability.a(localCursor.getString(localCursor.getColumnIndex("nw_name")));
      try
      {
        str2 = localCursor.getString(localCursor.getColumnIndex("nw_dmr_uuid"));
        if (str2 != null) {
          localCapability.a(new UpnpUuid(str2));
        }
      }
      catch (IdSyntaxException localIdSyntaxException3)
      {
        String str2;
        SpLog.a(a, localIdSyntaxException3);
        continue;
        try
        {
          localCapability.a(new MacAddress(localIdSyntaxException3));
        }
        catch (IdSyntaxException localIdSyntaxException4)
        {
          SpLog.a(a, localIdSyntaxException4);
        }
        if (localCursor.getInt(localCursor.getColumnIndex("nw_has_dmr")) != 1) {
          continue;
        }
        localCapability.p();
        if (localCapability.b() == null) {
          continue;
        }
      }
      i = 1;
      if (i < 6)
      {
        str2 = localCursor.getString(localCursor.getColumnIndex("nw_address" + i));
        if (str2 == null) {
          continue;
        }
      }
      if (localCapability.d() != null)
      {
        localDeviceId = localDeviceIdProvider.a(localCapability.d());
        localDeviceIdProvider.a(localDeviceId, localCapability.b());
        if (localDeviceId == null) {}
      }
      try
      {
        paramFoundationStorage.b(localDeviceId.toString(), RecognitionManager.a(localCapability));
        bool = localCursor.moveToNext();
        continue;
        if (localCapability.b() != null)
        {
          localDeviceId = localDeviceIdProvider.a(localCapability.b());
        }
        else
        {
          if (localCapability.d() == null) {
            continue;
          }
          localDeviceId = localDeviceIdProvider.a(localCapability.d());
        }
      }
      catch (JSONException localJSONException)
      {
        SpLog.a(a, localJSONException);
      }
    }
    IOUtil.a(localCursor);
    localHistoryDeviceDatabaseOpenHelper.close();
    if (SongPal.a().deleteDatabase("audiocomp_database"))
    {
      SpLog.c(a, "Successfully deleted legacy DB file");
      return;
    }
    SpLog.d(a, "Failed to delete legacy DB file");
  }
  
  public static void b()
  {
    if (!SongPal.a().getDatabasePath("audiocomp_database").exists()) {
      SpLog.c(a, "Legacy DB does not exist");
    }
    for (;;)
    {
      return;
      if (AppSettingsPreference.i())
      {
        AddAppsDatabaseHelper localAddAppsDatabaseHelper = new AddAppsDatabaseHelper(SongPal.a());
        Iterator localIterator = localAddAppsDatabaseHelper.a(AddAppsLoader.VoiceType.c).iterator();
        AddAppsDatabaseHelper.AppEntry localAppEntry;
        do
        {
          if (!localIterator.hasNext()) {
            break;
          }
          localAppEntry = (AddAppsDatabaseHelper.AppEntry)localIterator.next();
        } while (localAppEntry.c != AddAppsLoader.VoiceType.c);
        while (localAppEntry != null)
        {
          AppSettingsPreference.b(localAppEntry.a);
          localAddAppsDatabaseHelper.c(new AddAppsDatabaseHelper.AppEntry(localAppEntry.b, localAppEntry.a, AddAppsLoader.VoiceType.a));
          return;
          localAppEntry = null;
        }
      }
    }
  }
}
