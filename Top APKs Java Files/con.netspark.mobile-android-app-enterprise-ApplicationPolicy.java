package android.app.enterprise;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.sec.enterprise.AppIdentity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationPolicy
{
  public static final String ACTION_APPLICATION_FOCUS_CHANGE = "com.samsung.edm.intent.action.APPLICATION_FOCUS_CHANGE";
  public static final String ACTION_EDM_BACKUP_SERVICE_AVAILABLE = "edm.intent.action.backup.service.available";
  public static final String ACTION_PREVENT_APPLICATION_START = "edm.intent.application.action.prevent.start";
  public static final String ACTION_PREVENT_APPLICATION_STOP = "edm.intent.application.action.prevent.stop";
  public static final int APPLICATION_INSTALLATION_MODE_ALLOW = 1;
  public static final int APPLICATION_INSTALLATION_MODE_DISALLOW = 0;
  public static final int APPLICATION_UNINSTALLATION_MODE_ALLOW = 1;
  public static final int APPLICATION_UNINSTALLATION_MODE_DISALLOW = 0;
  public static final int BACKUP_APPLICATION_BUSY_SERVICE = -3;
  public static final int BACKUP_APPLICATION_FAILED = -2;
  public static final int BACKUP_APPLICATION_SERVICE_ERROR = -1;
  public static final int BACKUP_APPLICATION_SUCCESS = 0;
  public static final Intent DEVICE_ASSISTANCE_ACTIVITY_TASK;
  public static final Intent DEVICE_ASSISTANCE_SERVICE_TASK;
  public static final int ERROR_CALLER_TARGET_SDK_NOT_SUPPORTED = -4;
  public static final int ERROR_INVALID_INPUT = -1;
  public static final int ERROR_NONE = 0;
  public static final int ERROR_PROXY_NOT_INSTALLED = -5;
  public static final int ERROR_PROXY_NO_ADMIN_RECEIVER = -6;
  public static final int ERROR_SIGNATURE_MISMATCH = -3;
  public static final int ERROR_UNKNOWN = -2;
  public static final String EXTRA_APPLICATION_FOCUS_COMPONENT_NAME = "application_focus_component_name";
  public static final String EXTRA_APPLICATION_FOCUS_STATUS = "application_focus_status";
  public static final String EXTRA_APPLICATION_PACKAGE_NAME = "application_package_name";
  public static final String EXTRA_ERROR_CLASS = "error_class";
  public static final String EXTRA_ERROR_REASON = "error_reason";
  public static final String EXTRA_ERROR_TYPE = "error_type";
  public static final String EXTRA_USER_ID = "user_id";
  public static final int FLAG_ALLOW_PROXY_FOR_PFW = 1;
  public static final Intent LAUNCHER_TASK;
  public static final int NOTIFICATION_MODE_BLOCK_ALL = 2;
  public static final int NOTIFICATION_MODE_BLOCK_TEXT = 3;
  public static final int NOTIFICATION_MODE_BLOCK_TEXT_AND_SOUND = 4;
  public static final Intent OPEN_DIALER_TASK;
  public static final Intent OPEN_PDF_TASK;
  public static final Intent OPEN_URL_TASK;
  public static final int PERMISSION_POLICY_STATE_DEFAULT = 0;
  public static final int PERMISSION_POLICY_STATE_DENY = 2;
  public static final int PERMISSION_POLICY_STATE_GRANT = 1;
  public static final Intent PLAY_AUDIO_TASK;
  public static final Intent PLAY_VIDEO_TASK;
  public static final String PROXY_FLAGS = "proxyFlags";
  public static final Intent SMS_MMS_TASK;
  
  ApplicationPolicy()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addAppPackageNameToBlackList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addAppPackageNameToWhiteList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addAppPackageNameToWhiteList(String paramString, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addAppPermissionToBlackList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addAppSignatureToBlackList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addAppSignatureToWhiteList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addAppSignatureToWhiteList(String paramString, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addHomeShortcut(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToClearCacheBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToClearCacheWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToClearDataBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToClearDataWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableClipboardBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableClipboardWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableClipboardWhiteList(List<String> paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableUpdateBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableUpdateWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableUpdateWhiteList(List<String> paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToFocusMonitoringList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToForceStopBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToForceStopWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToForceStopWhiteList(List<String> paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToNotificationBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToNotificationWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToNotificationWhiteList(List<String> paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> addPackagesToPreventStartBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToWidgetBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToWidgetWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToWidgetWhiteList(List<String> paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addUsbDevicesForDefaultAccess(String paramString, List<UsbDeviceConfig> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int applyRuntimePermissions(AppIdentity paramAppIdentity, List<String> paramList, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int backupApplicationData(String paramString, ParcelFileDescriptor paramParcelFileDescriptor)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean changeApplicationIcon(String paramString, byte[] paramArrayOfByte)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean changeApplicationName(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearAppPackageNameFromList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearAppSignatureFromList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearDisableClipboardBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearDisableClipboardWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearDisableUpdateBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearDisableUpdateWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearFocusMonitoringList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearPackagesFromDisableClipboardList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearPackagesFromDisableUpdateList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearPackagesFromForceStopList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearPackagesFromNotificationList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearPackagesFromWidgetList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearPreventStartBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean clearUsbDevicesForDefaultAccess(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean deleteHomeShortcut(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean deleteManagedAppInfo(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void disableAndroidBrowser()
  {
    throw new RuntimeException("Stub!");
  }
  
  public void disableAndroidMarket()
  {
    throw new RuntimeException("Stub!");
  }
  
  public void disableVoiceDialer()
  {
    throw new RuntimeException("Stub!");
  }
  
  public void disableYouTube()
  {
    throw new RuntimeException("Stub!");
  }
  
  public void enableAndroidBrowser()
  {
    throw new RuntimeException("Stub!");
  }
  
  public void enableAndroidMarket()
  {
    throw new RuntimeException("Stub!");
  }
  
  public void enableVoiceDialer()
  {
    throw new RuntimeException("Stub!");
  }
  
  public void enableYouTube()
  {
    throw new RuntimeException("Stub!");
  }
  
  public AppInfoLastUsage[] getAllAppLastUsage()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<DefaultAppConfiguration> getAllDefaultApplications()
  {
    throw new RuntimeException("Stub!");
  }
  
  public Map<AppWidgetProviderInfo, ArrayList<Integer>> getAllWidgets(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean getAppInstallToSdCard()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<AppControlInfo> getAppPackageNamesAllBlackLists()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<AppControlInfo> getAppPackageNamesAllWhiteLists()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<AppControlInfo> getAppPermissionsAllBlackLists()
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] getAppPermissionsBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<AppControlInfo> getAppSignaturesAllBlackLists()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<AppControlInfo> getAppSignaturesAllWhiteLists()
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] getAppSignaturesBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] getAppSignaturesWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public long getApplicationCacheSize(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public long getApplicationCodeSize(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean getApplicationComponentState(ComponentName paramComponentName)
  {
    throw new RuntimeException("Stub!");
  }
  
  public long getApplicationCpuUsage(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public long getApplicationDataSize(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean getApplicationInstallationEnabled(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int getApplicationInstallationMode()
  {
    throw new RuntimeException("Stub!");
  }
  
  public long getApplicationMemoryUsage(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public String getApplicationName(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<NetworkStats> getApplicationNetworkStats()
  {
    throw new RuntimeException("Stub!");
  }
  
  public int getApplicationNotificationMode()
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean getApplicationStateEnabled(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] getApplicationStateList(boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public long getApplicationTotalSize(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int getApplicationUid(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean getApplicationUninstallationEnabled(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int getApplicationUninstallationMode()
  {
    throw new RuntimeException("Stub!");
  }
  
  public String getApplicationVersion(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int getApplicationVersionCode(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public AppInfoLastUsage[] getAvgNoAppUsagePerMonth()
  {
    throw new RuntimeException("Stub!");
  }
  
  public ComponentName getDefaultApplication(Intent paramIntent)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<ComponentName> getHomeShortcuts(String paramString, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] getInstalledApplicationsIDList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] getInstalledManagedApplicationsList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public ManagedAppInfo[] getManagedApplicationStatus(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<AppInfo> getMostCpuUsageApps(int paramInt, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<AppInfo> getMostDataUsageApps(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<AppInfo> getMostMemoryUsageApps(int paramInt, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromClearCacheBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromClearCacheWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromClearDataBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromClearDataWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromDisableClipboardBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromDisableClipboardWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromDisableUpdateBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromDisableUpdateWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromFocusMonitoringList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromForceStopBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromForceStopWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromNotificationBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromNotificationWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromPreventStartBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromWidgetBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromWidgetWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getRuntimePermissions(String paramString, int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<UsbDeviceConfig> getUsbDevicesForDefaultAccess(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean installApplication(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean installApplication(String paramString, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean isApplicationInstalled(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean isApplicationRunning(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removeAppPackageNameFromBlackList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removeAppPackageNameFromWhiteList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removeAppPermissionFromBlackList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removeAppSignatureFromBlackList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removeAppSignatureFromWhiteList(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removeDefaultApplication(Intent paramIntent, ComponentName paramComponentName)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromClearCacheBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromClearCacheWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromClearDataBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromClearDataWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromDisableClipboardBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromDisableClipboardWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromDisableUpdateBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromDisableUpdateWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromFocusMonitoringList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromForceStopBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromForceStopWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromNotificationBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromNotificationWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromPreventStartBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromWidgetBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromWidgetWhiteList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int restoreApplicationData(String paramString, ParcelFileDescriptor paramParcelFileDescriptor)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int setAfWProxy(boolean paramBoolean, AppIdentity paramAppIdentity, Bundle paramBundle)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean setAppInstallToSdCard(boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean setApplicationComponentState(ComponentName paramComponentName, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void setApplicationInstallationDisabled(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void setApplicationInstallationEnabled(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean setApplicationInstallationMode(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean setApplicationNotificationMode(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] setApplicationStateList(String[] paramArrayOfString, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void setApplicationUninstallationDisabled(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public void setApplicationUninstallationEnabled(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean setApplicationUninstallationMode(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean setAsManagedApp(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean setDefaultApplication(Intent paramIntent, ComponentName paramComponentName)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean setDisableApplication(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean setEnableApplication(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean startApp(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean stopApp(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean uninstallApplication(String paramString, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> uninstallApplications(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean updateApplication(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean wipeApplicationData(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
}
