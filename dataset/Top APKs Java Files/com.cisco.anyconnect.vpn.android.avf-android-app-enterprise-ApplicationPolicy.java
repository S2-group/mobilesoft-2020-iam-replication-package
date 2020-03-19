package android.app.enterprise;

import android.appwidget.AppWidgetProviderInfo;
import android.os.ParcelFileDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationPolicy
{
  public static final String ACTION_EDM_BACKUP_SERVICE_AVAILABLE = "edm.intent.action.backup.service.available";
  public static final int APPLICATION_INSTALLATION_MODE_ALLOW = 1;
  public static final int APPLICATION_INSTALLATION_MODE_DISALLOW = 0;
  public static final int APPLICATION_UNINSTALLATION_MODE_ALLOW = 1;
  public static final int APPLICATION_UNINSTALLATION_MODE_DISALLOW = 0;
  public static final int BACKUP_APPLICATION_BUSY_SERVICE = -3;
  public static final int BACKUP_APPLICATION_FAILED = -2;
  public static final int BACKUP_APPLICATION_SERVICE_ERROR = -1;
  public static final int BACKUP_APPLICATION_SUCCESS = 0;
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
  
  public boolean addPackagesToForceStopBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToForceStopWhiteList(List<String> paramList)
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
  
  public boolean addPackagesToWidgetBlackList(List<String> paramList)
  {
    throw new RuntimeException("Stub!");
  }
  
  public boolean addPackagesToWidgetWhiteList(List<String> paramList)
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
  
  public AppInfoLastUsage[] getAvgNoAppUsagePerMonth()
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
  
  public List<String> getPackagesFromWidgetBlackList()
  {
    throw new RuntimeException("Stub!");
  }
  
  public List<String> getPackagesFromWidgetWhiteList()
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
  
  public boolean setAppInstallToSdCard(boolean paramBoolean)
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
