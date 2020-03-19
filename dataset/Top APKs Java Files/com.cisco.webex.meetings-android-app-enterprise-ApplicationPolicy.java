package android.app.enterprise;

import android.content.ComponentName;
import android.os.ParcelFileDescriptor;
import java.util.List;
import java.util.Map;

public class ApplicationPolicy
{
  public static final String ACTION_EDM_BACKUP_SERVICE_AVAILABLE = "edm.intent.action.backup.service.available";
  public static final String ACTION_PREVENT_APPLICATION_START = "edm.intent.application.action.prevent.start";
  public static final int APPLICATION_INSTALLATION_MODE_ALLOW = 1;
  public static final int APPLICATION_INSTALLATION_MODE_DISALLOW = 0;
  public static final int APPLICATION_UNINSTALLATION_MODE_ALLOW = 1;
  public static final int APPLICATION_UNINSTALLATION_MODE_DISALLOW = 0;
  public static final int BACKUP_APPLICATION_BUSY_SERVICE = -3;
  public static final int BACKUP_APPLICATION_FAILED = -2;
  public static final int BACKUP_APPLICATION_SERVICE_ERROR = -1;
  public static final int BACKUP_APPLICATION_SUCCESS = 0;
  public static final String EXTRA_APPLICATION_PACKAGE_NAME = "application_package_name";
  public static final int NOTIFICATION_MODE_BLOCK_ALL = 2;
  public static final int NOTIFICATION_MODE_BLOCK_TEXT = 3;
  public static final int NOTIFICATION_MODE_BLOCK_TEXT_AND_SOUND = 4;
  
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
  
  public boolean addPackagesToClearCacheBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToClearCacheWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToClearDataBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToClearDataWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableClipboardBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableClipboardWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableClipboardWhiteList(List paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableUpdateBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableUpdateWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToDisableUpdateWhiteList(List paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToForceStopBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToForceStopWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToForceStopWhiteList(List paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToNotificationBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToNotificationWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToNotificationWhiteList(List paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List addPackagesToPreventStartBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToWidgetBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToWidgetWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToWidgetWhiteList(List paramList, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addUsbDevicesForDefaultAccess(String paramString, List paramList)
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
  
  public Map getAllWidgets(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean getAppInstallToSdCard()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getAppPackageNamesAllBlackLists()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getAppPackageNamesAllWhiteLists()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getAppPermissionsAllBlackLists()
  {
    throw new RuntimeException("Stub!");
  }
  
  public String[] getAppPermissionsBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getAppSignaturesAllBlackLists()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getAppSignaturesAllWhiteLists()
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
  
  public List getApplicationNetworkStats()
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
  
  public List getHomeShortcuts(String paramString, boolean paramBoolean)
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
  
  public List getMostCpuUsageApps(int paramInt, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getMostDataUsageApps(int paramInt)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getMostMemoryUsageApps(int paramInt, boolean paramBoolean)
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromClearCacheBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromClearCacheWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromClearDataBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromClearDataWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromDisableClipboardBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromDisableClipboardWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromDisableUpdateBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromDisableUpdateWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromForceStopBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromForceStopWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromNotificationBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromNotificationWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromPreventStartBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromWidgetBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getPackagesFromWidgetWhiteList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List getUsbDevicesForDefaultAccess(String paramString)
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
  
  public boolean removePackagesFromClearCacheBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromClearCacheWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromClearDataBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromClearDataWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromDisableClipboardBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromDisableClipboardWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromDisableUpdateBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromDisableUpdateWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromForceStopBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromForceStopWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromNotificationBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromNotificationWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromPreventStartBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromWidgetBlackList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean removePackagesFromWidgetWhiteList(List paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public int restoreApplicationData(String paramString, ParcelFileDescriptor paramParcelFileDescriptor)
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
  
  public List uninstallApplications(List paramList)
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
