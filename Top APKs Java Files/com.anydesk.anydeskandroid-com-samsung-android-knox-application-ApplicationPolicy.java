package com.samsung.android.knox.application;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.SupportLibUtils;
import com.sec.enterprise.knox.AdvancedRestrictionPolicy;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationPolicy
{
  public static final String ACTION_APPLICATION_FOCUS_CHANGE = "com.samsung.android.knox.intent.action.APPLICATION_FOCUS_CHANGE";
  public static final String ACTION_PREVENT_APPLICATION_START = "com.samsung.android.knox.intent.action.PREVENT_APPLICATION_START";
  public static final String ACTION_PREVENT_APPLICATION_STOP = "com.samsung.android.knox.intent.action.PREVENT_APPLICATION_STOP";
  public static final String DEFAULT_TYPE_AUDIO = "audio/*";
  public static final String DEFAULT_TYPE_PDF = "application/pdf";
  public static final Intent DEVICE_ASSISTANCE_ACTIVITY_TASK = createAssistActivityTask();
  public static final Intent DEVICE_ASSISTANCE_SERVICE_TASK = createAssistServiceTask();
  public static final String EXTRA_APPLICATION_FOCUS_COMPONENT_NAME = "com.samsung.android.knox.intent.extra.APPLICATION_FOCUS_COMPONENT_NAME";
  public static final String EXTRA_APPLICATION_FOCUS_STATUS = "com.samsung.android.knox.intent.extra.APPLICATION_FOCUS_STATUS";
  public static final String EXTRA_APPLICATION_PACKAGE_NAME = "com.samsung.android.knox.intent.extra.APPLICATION_PACKAGE_NAME";
  public static final String EXTRA_ERROR_CLASS = "com.samsung.android.knox.intent.extra.ERROR_CLASS";
  public static final String EXTRA_ERROR_REASON = "com.samsung.android.knox.intent.extra.ERROR_REASON";
  public static final String EXTRA_ERROR_TYPE = "com.samsung.android.knox.intent.extra.ERROR_TYPE";
  public static final String EXTRA_USER_ID = "com.samsung.android.knox.intent.extra.USER_ID";
  public static final Intent LAUNCHER_TASK;
  public static final Intent OPEN_DIALER_TASK;
  public static final Intent OPEN_PDF_TASK;
  public static final Intent OPEN_URL_TASK;
  public static final Intent PLAY_AUDIO_TASK;
  public static final Intent PLAY_VIDEO_TASK;
  private static final String PROXY_FLAGS = "proxy-flags";
  public static final Intent SMS_MMS_TASK = ;
  private AdvancedRestrictionPolicy mAdvancedRestrictionPolicy;
  private android.app.enterprise.ApplicationPolicy mApplicationPolicy;
  
  static
  {
    LAUNCHER_TASK = createLauncherTask();
    OPEN_URL_TASK = createOpenURLTask();
    OPEN_PDF_TASK = createOpenPDFTask();
    OPEN_DIALER_TASK = createOpenDialerTask();
    PLAY_AUDIO_TASK = createAudioTask();
    PLAY_VIDEO_TASK = createVideoTask();
  }
  
  public ApplicationPolicy(android.app.enterprise.ApplicationPolicy paramApplicationPolicy, AdvancedRestrictionPolicy paramAdvancedRestrictionPolicy)
  {
    this.mApplicationPolicy = paramApplicationPolicy;
    this.mAdvancedRestrictionPolicy = paramAdvancedRestrictionPolicy;
  }
  
  private Bundle convertToOldBundle(Bundle paramBundle)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("proxyFlags", paramBundle.getInt("proxy-flags", 0));
    return localBundle;
  }
  
  private static Intent createAssistActivityTask()
  {
    return new Intent("android.intent.action.ASSIST");
  }
  
  private static Intent createAssistServiceTask()
  {
    return new Intent("android.service.voice.VoiceInteractionService");
  }
  
  private static Intent createAudioTask()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File("audio.mp3")), "audio/*");
    return localIntent;
  }
  
  private static Intent createLauncherTask()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    return localIntent;
  }
  
  private static Intent createOpenDialerTask()
  {
    Intent localIntent = new Intent("android.intent.action.DIAL");
    localIntent.setData(Uri.parse("tel:"));
    return localIntent;
  }
  
  private static Intent createOpenPDFTask()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File("file.pdf")), "application/pdf");
    return localIntent;
  }
  
  private static Intent createOpenURLTask()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addCategory("android.intent.category.BROWSABLE");
    localIntent.setData(Uri.parse("http://"));
    return localIntent;
  }
  
  private static Intent createSmsMmsTask()
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.setData(Uri.parse("smsto:"));
    return localIntent;
  }
  
  private static Intent createVideoTask()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setType("video/*");
    return localIntent;
  }
  
  public boolean addAppPackageNameToBlackList(String paramString)
  {
    return this.mApplicationPolicy.addAppPackageNameToBlackList(paramString);
  }
  
  public boolean addAppPackageNameToWhiteList(String paramString)
  {
    return this.mApplicationPolicy.addAppPackageNameToWhiteList(paramString);
  }
  
  public boolean addAppPackageNameToWhiteList(String paramString, boolean paramBoolean)
  {
    return this.mApplicationPolicy.addAppPackageNameToWhiteList(paramString, paramBoolean);
  }
  
  public boolean addAppPermissionToBlackList(String paramString)
  {
    return this.mApplicationPolicy.addAppPermissionToBlackList(paramString);
  }
  
  public boolean addAppSignatureToBlackList(String paramString)
  {
    return this.mApplicationPolicy.addAppSignatureToBlackList(paramString);
  }
  
  public boolean addAppSignatureToWhiteList(String paramString)
  {
    return this.mApplicationPolicy.addAppSignatureToWhiteList(paramString);
  }
  
  public boolean addAppSignatureToWhiteList(String paramString, boolean paramBoolean)
  {
    return this.mApplicationPolicy.addAppSignatureToWhiteList(paramString, paramBoolean);
  }
  
  public boolean addHomeShortcut(String paramString1, String paramString2)
  {
    return this.mApplicationPolicy.addHomeShortcut(paramString1, paramString2);
  }
  
  public boolean addNewAdminActivationAppWhiteList(List<String> paramList)
  {
    try
    {
      boolean bool = this.mAdvancedRestrictionPolicy.addNewAdminActivationAppWhiteList(paramList);
      return bool;
    }
    catch (NoClassDefFoundError paramList)
    {
      throw new NoClassDefFoundError(paramList.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "addNewAdminActivationAppWhiteList", new Class[] { List.class }, 11));
    }
    catch (NoSuchMethodError paramList)
    {
      for (;;) {}
    }
  }
  
  public int addPackageToBatteryOptimizationWhiteList(AppIdentity paramAppIdentity)
  {
    try
    {
      int i = this.mApplicationPolicy.addPackageToBatteryOptimizationWhiteList(AppIdentity.convertToOld(paramAppIdentity));
      return i;
    }
    catch (NoClassDefFoundError paramAppIdentity)
    {
      throw new NoClassDefFoundError(paramAppIdentity.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "addPackageToBatteryOptimizationWhiteList", new Class[] { AppIdentity.class }, 20));
    }
    catch (NoSuchMethodError paramAppIdentity)
    {
      for (;;) {}
    }
  }
  
  public boolean addPackagesToClearCacheBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToClearCacheBlackList(paramList);
  }
  
  public boolean addPackagesToClearCacheWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToClearCacheWhiteList(paramList);
  }
  
  public boolean addPackagesToClearDataBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToClearDataBlackList(paramList);
  }
  
  public boolean addPackagesToClearDataWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToClearDataWhiteList(paramList);
  }
  
  public boolean addPackagesToDisableClipboardBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToDisableClipboardBlackList(paramList);
  }
  
  public boolean addPackagesToDisableClipboardWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToDisableClipboardWhiteList(paramList);
  }
  
  public boolean addPackagesToDisableClipboardWhiteList(List<String> paramList, boolean paramBoolean)
  {
    return this.mApplicationPolicy.addPackagesToDisableClipboardWhiteList(paramList, paramBoolean);
  }
  
  public boolean addPackagesToDisableUpdateBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToDisableUpdateBlackList(paramList);
  }
  
  public boolean addPackagesToDisableUpdateWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToDisableUpdateWhiteList(paramList);
  }
  
  public boolean addPackagesToDisableUpdateWhiteList(List<String> paramList, boolean paramBoolean)
  {
    return this.mApplicationPolicy.addPackagesToDisableUpdateWhiteList(paramList, paramBoolean);
  }
  
  public boolean addPackagesToFocusMonitoringList(List<String> paramList)
  {
    try
    {
      boolean bool = this.mApplicationPolicy.addPackagesToFocusMonitoringList(paramList);
      return bool;
    }
    catch (NoSuchMethodError paramList)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "addPackagesToFocusMonitoringList", new Class[] { List.class }, 15));
  }
  
  public boolean addPackagesToForceStopBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToForceStopBlackList(paramList);
  }
  
  public boolean addPackagesToForceStopWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToForceStopWhiteList(paramList);
  }
  
  public boolean addPackagesToForceStopWhiteList(List<String> paramList, boolean paramBoolean)
  {
    return this.mApplicationPolicy.addPackagesToForceStopWhiteList(paramList, paramBoolean);
  }
  
  public boolean addPackagesToNotificationBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToNotificationBlackList(paramList);
  }
  
  public boolean addPackagesToNotificationWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToNotificationWhiteList(paramList);
  }
  
  public boolean addPackagesToNotificationWhiteList(List<String> paramList, boolean paramBoolean)
  {
    return this.mApplicationPolicy.addPackagesToNotificationWhiteList(paramList, paramBoolean);
  }
  
  public List<String> addPackagesToPreventStartBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToPreventStartBlackList(paramList);
  }
  
  public boolean addPackagesToWidgetBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToWidgetBlackList(paramList);
  }
  
  public boolean addPackagesToWidgetWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.addPackagesToWidgetWhiteList(paramList);
  }
  
  public boolean addPackagesToWidgetWhiteList(List<String> paramList, boolean paramBoolean)
  {
    return this.mApplicationPolicy.addPackagesToWidgetWhiteList(paramList, paramBoolean);
  }
  
  public boolean addUsbDevicesForDefaultAccess(String paramString, List<UsbDeviceConfig> paramList)
  {
    try
    {
      boolean bool = this.mApplicationPolicy.addUsbDevicesForDefaultAccess(paramString, UsbDeviceConfig.convertToOldList(paramList));
      return bool;
    }
    catch (NoClassDefFoundError paramString)
    {
      throw new NoClassDefFoundError(paramString.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "addUsbDevicesForDefaultAccess", new Class[] { String.class, List.class }, 12));
    }
    catch (NoSuchMethodError paramString)
    {
      for (;;) {}
    }
  }
  
  public int applyRuntimePermissions(AppIdentity paramAppIdentity, List<String> paramList, int paramInt)
  {
    try
    {
      paramInt = this.mApplicationPolicy.applyRuntimePermissions(AppIdentity.convertToOld(paramAppIdentity), paramList, paramInt);
      return paramInt;
    }
    catch (NoClassDefFoundError paramAppIdentity)
    {
      throw new NoClassDefFoundError(paramAppIdentity.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "applyRuntimePermissions", new Class[] { AppIdentity.class, List.class, Integer.TYPE }, 19));
    }
    catch (NoSuchMethodError paramAppIdentity)
    {
      for (;;) {}
    }
  }
  
  public boolean changeApplicationIcon(String paramString, byte[] paramArrayOfByte)
  {
    return this.mApplicationPolicy.changeApplicationIcon(paramString, paramArrayOfByte);
  }
  
  public boolean changeApplicationName(String paramString1, String paramString2)
  {
    return this.mApplicationPolicy.changeApplicationName(paramString1, paramString2);
  }
  
  public boolean clearAppPackageNameFromList()
  {
    return this.mApplicationPolicy.clearAppPackageNameFromList();
  }
  
  public boolean clearAppSignatureFromList()
  {
    return this.mApplicationPolicy.clearAppSignatureFromList();
  }
  
  public boolean clearDisableClipboardBlackList()
  {
    return this.mApplicationPolicy.clearDisableClipboardBlackList();
  }
  
  public boolean clearDisableClipboardWhiteList()
  {
    return this.mApplicationPolicy.clearDisableClipboardWhiteList();
  }
  
  public boolean clearDisableUpdateBlackList()
  {
    return this.mApplicationPolicy.clearDisableUpdateBlackList();
  }
  
  public boolean clearDisableUpdateWhiteList()
  {
    return this.mApplicationPolicy.clearDisableUpdateWhiteList();
  }
  
  public boolean clearFocusMonitoringList()
  {
    try
    {
      boolean bool = this.mApplicationPolicy.clearFocusMonitoringList();
      return bool;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "clearFocusMonitoringList", null, 15));
  }
  
  public boolean clearNewAdminActivationAppWhiteList()
  {
    try
    {
      boolean bool = this.mAdvancedRestrictionPolicy.clearNewAdminActivationAppWhiteList();
      return bool;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      throw new NoClassDefFoundError(localNoClassDefFoundError.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "clearNewAdminActivationAppWhiteList", null, 11));
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
  }
  
  public boolean clearPackagesFromDisableClipboardList()
  {
    return this.mApplicationPolicy.clearPackagesFromDisableClipboardList();
  }
  
  public boolean clearPackagesFromDisableUpdateList()
  {
    return this.mApplicationPolicy.clearPackagesFromDisableUpdateList();
  }
  
  public boolean clearPackagesFromForceStopList()
  {
    return this.mApplicationPolicy.clearPackagesFromForceStopList();
  }
  
  public boolean clearPackagesFromNotificationList()
  {
    return this.mApplicationPolicy.clearPackagesFromNotificationList();
  }
  
  public boolean clearPackagesFromWidgetList()
  {
    return this.mApplicationPolicy.clearPackagesFromWidgetList();
  }
  
  public boolean clearPreventStartBlackList()
  {
    return this.mApplicationPolicy.clearPreventStartBlackList();
  }
  
  public boolean clearUsbDevicesForDefaultAccess(String paramString)
  {
    try
    {
      boolean bool = this.mApplicationPolicy.clearUsbDevicesForDefaultAccess(paramString);
      return bool;
    }
    catch (NoSuchMethodError paramString)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "clearUsbDevicesForDefaultAccess", new Class[] { String.class }, 12));
  }
  
  public boolean deleteHomeShortcut(String paramString1, String paramString2)
  {
    return this.mApplicationPolicy.deleteHomeShortcut(paramString1, paramString2);
  }
  
  public void disableAndroidBrowser()
  {
    this.mApplicationPolicy.disableAndroidBrowser();
  }
  
  public void disableAndroidMarket()
  {
    this.mApplicationPolicy.disableAndroidMarket();
  }
  
  public void disableVoiceDialer()
  {
    this.mApplicationPolicy.disableVoiceDialer();
  }
  
  public void disableYouTube()
  {
    this.mApplicationPolicy.disableYouTube();
  }
  
  public void enableAndroidBrowser()
  {
    this.mApplicationPolicy.enableAndroidBrowser();
  }
  
  public void enableAndroidMarket()
  {
    this.mApplicationPolicy.enableAndroidMarket();
  }
  
  public void enableVoiceDialer()
  {
    this.mApplicationPolicy.enableVoiceDialer();
  }
  
  public void enableYouTube()
  {
    this.mApplicationPolicy.enableYouTube();
  }
  
  public AppInfoLastUsage[] getAllAppLastUsage()
  {
    return AppInfoLastUsage.convertToNewArray(this.mApplicationPolicy.getAllAppLastUsage());
  }
  
  public List<DefaultAppConfiguration> getAllDefaultApplications()
  {
    try
    {
      List localList = DefaultAppConfiguration.convertToNewList(this.mApplicationPolicy.getAllDefaultApplications());
      return localList;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getAllDefaultApplications", null, 15));
  }
  
  public Map<AppWidgetProviderInfo, ArrayList<Integer>> getAllWidgets(String paramString)
  {
    return this.mApplicationPolicy.getAllWidgets(paramString);
  }
  
  public List<AppControlInfo> getAppPackageNamesAllBlackLists()
  {
    return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppPackageNamesAllBlackLists());
  }
  
  public List<AppControlInfo> getAppPackageNamesAllWhiteLists()
  {
    return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppPackageNamesAllWhiteLists());
  }
  
  public List<AppControlInfo> getAppPermissionsAllBlackLists()
  {
    return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppPermissionsAllBlackLists());
  }
  
  public String[] getAppPermissionsBlackList()
  {
    return this.mApplicationPolicy.getAppPermissionsBlackList();
  }
  
  public List<AppControlInfo> getAppSignaturesAllBlackLists()
  {
    return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppSignaturesAllBlackLists());
  }
  
  public List<AppControlInfo> getAppSignaturesAllWhiteLists()
  {
    return AppControlInfo.convertToNewList(this.mApplicationPolicy.getAppSignaturesAllWhiteLists());
  }
  
  public String[] getAppSignaturesBlackList()
  {
    return this.mApplicationPolicy.getAppSignaturesBlackList();
  }
  
  public String[] getAppSignaturesWhiteList()
  {
    return this.mApplicationPolicy.getAppSignaturesWhiteList();
  }
  
  public long getApplicationCacheSize(String paramString)
  {
    return this.mApplicationPolicy.getApplicationCacheSize(paramString);
  }
  
  public long getApplicationCodeSize(String paramString)
  {
    return this.mApplicationPolicy.getApplicationCodeSize(paramString);
  }
  
  public boolean getApplicationComponentState(ComponentName paramComponentName)
  {
    return this.mApplicationPolicy.getApplicationComponentState(paramComponentName);
  }
  
  public long getApplicationCpuUsage(String paramString)
  {
    return this.mApplicationPolicy.getApplicationCpuUsage(paramString);
  }
  
  public long getApplicationDataSize(String paramString)
  {
    return this.mApplicationPolicy.getApplicationDataSize(paramString);
  }
  
  public boolean getApplicationInstallationEnabled(String paramString)
  {
    return this.mApplicationPolicy.getApplicationInstallationEnabled(paramString);
  }
  
  public int getApplicationInstallationMode()
  {
    return this.mApplicationPolicy.getApplicationInstallationMode();
  }
  
  public long getApplicationMemoryUsage(String paramString)
  {
    return this.mApplicationPolicy.getApplicationMemoryUsage(paramString);
  }
  
  public String getApplicationName(String paramString)
  {
    return this.mApplicationPolicy.getApplicationName(paramString);
  }
  
  public List<NetworkStats> getApplicationNetworkStats()
  {
    return NetworkStats.convertToNewList(this.mApplicationPolicy.getApplicationNetworkStats());
  }
  
  public int getApplicationNotificationMode()
  {
    return this.mApplicationPolicy.getApplicationNotificationMode();
  }
  
  public Bundle getApplicationRestrictions(ComponentName paramComponentName, String paramString)
  {
    try
    {
      paramComponentName = this.mApplicationPolicy.getApplicationRestrictions(paramComponentName, paramString);
      return paramComponentName;
    }
    catch (NoSuchMethodError paramComponentName)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getApplicationRestrictions", new Class[] { ComponentName.class, String.class }, 20));
  }
  
  public boolean getApplicationStateEnabled(String paramString)
  {
    return this.mApplicationPolicy.getApplicationStateEnabled(paramString);
  }
  
  public String[] getApplicationStateList(boolean paramBoolean)
  {
    return this.mApplicationPolicy.getApplicationStateList(paramBoolean);
  }
  
  public long getApplicationTotalSize(String paramString)
  {
    return this.mApplicationPolicy.getApplicationTotalSize(paramString);
  }
  
  public int getApplicationUid(String paramString)
  {
    try
    {
      int i = this.mApplicationPolicy.getApplicationUid(paramString);
      return i;
    }
    catch (NoSuchMethodError paramString)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getApplicationUid", new Class[] { String.class }, 12));
  }
  
  public boolean getApplicationUninstallationEnabled(String paramString)
  {
    return this.mApplicationPolicy.getApplicationUninstallationEnabled(paramString);
  }
  
  public int getApplicationUninstallationMode()
  {
    return this.mApplicationPolicy.getApplicationUninstallationMode();
  }
  
  public String getApplicationVersion(String paramString)
  {
    return this.mApplicationPolicy.getApplicationVersion(paramString);
  }
  
  public int getApplicationVersionCode(String paramString)
  {
    return this.mApplicationPolicy.getApplicationVersionCode(paramString);
  }
  
  public AppInfoLastUsage[] getAvgNoAppUsagePerMonth()
  {
    return AppInfoLastUsage.convertToNewArray(this.mApplicationPolicy.getAvgNoAppUsagePerMonth());
  }
  
  public ComponentName getDefaultApplication(Intent paramIntent)
  {
    try
    {
      paramIntent = this.mApplicationPolicy.getDefaultApplication(paramIntent);
      return paramIntent;
    }
    catch (NoSuchMethodError paramIntent)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getDefaultApplication", new Class[] { Intent.class }, 15));
  }
  
  public List<ComponentName> getHomeShortcuts(String paramString, boolean paramBoolean)
  {
    return this.mApplicationPolicy.getHomeShortcuts(paramString, paramBoolean);
  }
  
  public String[] getInstalledApplicationsIDList()
  {
    return this.mApplicationPolicy.getInstalledApplicationsIDList();
  }
  
  public List<AppInfo> getMostCpuUsageApps(int paramInt, boolean paramBoolean)
  {
    return AppInfo.convertToNewList(this.mApplicationPolicy.getMostCpuUsageApps(paramInt, paramBoolean));
  }
  
  public List<AppInfo> getMostDataUsageApps(int paramInt)
  {
    return AppInfo.convertToNewList(this.mApplicationPolicy.getMostDataUsageApps(paramInt));
  }
  
  public List<AppInfo> getMostMemoryUsageApps(int paramInt, boolean paramBoolean)
  {
    return AppInfo.convertToNewList(this.mApplicationPolicy.getMostMemoryUsageApps(paramInt, paramBoolean));
  }
  
  public List<String> getNewAdminActivationAppWhiteList()
  {
    try
    {
      List localList = this.mAdvancedRestrictionPolicy.getNewAdminActivationAppWhiteList();
      return localList;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      throw new NoClassDefFoundError(localNoClassDefFoundError.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getNewAdminActivationAppWhiteList", null, 11));
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
  }
  
  public List<String> getPackagesFromBatteryOptimizationWhiteList()
  {
    try
    {
      List localList = this.mApplicationPolicy.getPackagesFromBatteryOptimizationWhiteList();
      return localList;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getPackagesFromBatteryOptimizationWhiteList", null, 20));
  }
  
  public List<String> getPackagesFromClearCacheBlackList()
  {
    return this.mApplicationPolicy.getPackagesFromClearCacheBlackList();
  }
  
  public List<String> getPackagesFromClearCacheWhiteList()
  {
    return this.mApplicationPolicy.getPackagesFromClearCacheWhiteList();
  }
  
  public List<String> getPackagesFromClearDataBlackList()
  {
    return this.mApplicationPolicy.getPackagesFromClearDataBlackList();
  }
  
  public List<String> getPackagesFromClearDataWhiteList()
  {
    return this.mApplicationPolicy.getPackagesFromClearDataWhiteList();
  }
  
  public List<String> getPackagesFromDisableClipboardBlackList()
  {
    return this.mApplicationPolicy.getPackagesFromDisableClipboardBlackList();
  }
  
  public List<String> getPackagesFromDisableClipboardWhiteList()
  {
    return this.mApplicationPolicy.getPackagesFromDisableClipboardWhiteList();
  }
  
  public List<String> getPackagesFromDisableUpdateBlackList()
  {
    return this.mApplicationPolicy.getPackagesFromDisableUpdateBlackList();
  }
  
  public List<String> getPackagesFromDisableUpdateWhiteList()
  {
    return this.mApplicationPolicy.getPackagesFromDisableUpdateWhiteList();
  }
  
  public List<String> getPackagesFromFocusMonitoringList()
  {
    try
    {
      List localList = this.mApplicationPolicy.getPackagesFromFocusMonitoringList();
      return localList;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getPackagesFromFocusMonitoringList", null, 15));
  }
  
  public List<String> getPackagesFromForceStopBlackList()
  {
    return this.mApplicationPolicy.getPackagesFromForceStopBlackList();
  }
  
  public List<String> getPackagesFromForceStopWhiteList()
  {
    return this.mApplicationPolicy.getPackagesFromForceStopWhiteList();
  }
  
  public List<String> getPackagesFromNotificationBlackList()
  {
    return this.mApplicationPolicy.getPackagesFromNotificationBlackList();
  }
  
  public List<String> getPackagesFromNotificationWhiteList()
  {
    return this.mApplicationPolicy.getPackagesFromNotificationWhiteList();
  }
  
  public List<String> getPackagesFromPreventStartBlackList()
  {
    return this.mApplicationPolicy.getPackagesFromPreventStartBlackList();
  }
  
  public List<String> getPackagesFromWidgetBlackList()
  {
    return this.mApplicationPolicy.getPackagesFromWidgetBlackList();
  }
  
  public List<String> getPackagesFromWidgetWhiteList()
  {
    return this.mApplicationPolicy.getPackagesFromWidgetWhiteList();
  }
  
  public List<String> getRuntimePermissions(String paramString, int paramInt)
  {
    try
    {
      paramString = this.mApplicationPolicy.getRuntimePermissions(paramString, paramInt);
      return paramString;
    }
    catch (NoSuchMethodError paramString)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getRuntimePermissions", new Class[] { String.class, Integer.TYPE }, 19));
  }
  
  public List<UsbDeviceConfig> getUsbDevicesForDefaultAccess(String paramString)
  {
    try
    {
      paramString = UsbDeviceConfig.convertToNewList(this.mApplicationPolicy.getUsbDevicesForDefaultAccess(paramString));
      return paramString;
    }
    catch (NoSuchMethodError paramString)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "getUsbDevicesForDefaultAccess", new Class[] { String.class }, 12));
  }
  
  public boolean installApplication(String paramString)
  {
    try
    {
      boolean bool = this.mApplicationPolicy.installApplication(paramString);
      return bool;
    }
    catch (NoSuchMethodError paramString)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "installApplication", new Class[] { String.class }, 12));
  }
  
  public boolean installApplication(String paramString, boolean paramBoolean)
  {
    return this.mApplicationPolicy.installApplication(paramString, paramBoolean);
  }
  
  public boolean isApplicationInstalled(String paramString)
  {
    return this.mApplicationPolicy.isApplicationInstalled(paramString);
  }
  
  public boolean isApplicationRunning(String paramString)
  {
    return this.mApplicationPolicy.isApplicationRunning(paramString);
  }
  
  public boolean isNewAdminActivationEnabled(boolean paramBoolean)
  {
    try
    {
      paramBoolean = this.mAdvancedRestrictionPolicy.isNewAdminActivationEnabled(paramBoolean);
      return paramBoolean;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      throw new NoClassDefFoundError(localNoClassDefFoundError.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "isNewAdminActivationEnabled", new Class[] { Boolean.TYPE }, 11));
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
  }
  
  public boolean isNewAdminInstallationEnabled(boolean paramBoolean)
  {
    try
    {
      paramBoolean = this.mAdvancedRestrictionPolicy.isNewAdminInstallationEnabled(paramBoolean);
      return paramBoolean;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      throw new NoClassDefFoundError(localNoClassDefFoundError.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "isNewAdminInstallationEnabled", new Class[] { Boolean.TYPE }, 11));
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
  }
  
  public boolean preventNewAdminActivation(boolean paramBoolean)
  {
    try
    {
      paramBoolean = this.mAdvancedRestrictionPolicy.preventNewAdminActivation(paramBoolean);
      return paramBoolean;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      throw new NoClassDefFoundError(localNoClassDefFoundError.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "preventNewAdminActivation", new Class[] { Boolean.TYPE }, 11));
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
  }
  
  public boolean preventNewAdminInstallation(boolean paramBoolean)
  {
    try
    {
      paramBoolean = this.mAdvancedRestrictionPolicy.preventNewAdminInstallation(paramBoolean);
      return paramBoolean;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      throw new NoClassDefFoundError(localNoClassDefFoundError.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "preventNewAdminInstallation", new Class[] { Boolean.TYPE }, 11));
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
  }
  
  public boolean removeAppPackageNameFromBlackList(String paramString)
  {
    return this.mApplicationPolicy.removeAppPackageNameFromBlackList(paramString);
  }
  
  public boolean removeAppPackageNameFromWhiteList(String paramString)
  {
    return this.mApplicationPolicy.removeAppPackageNameFromWhiteList(paramString);
  }
  
  public boolean removeAppPermissionFromBlackList(String paramString)
  {
    return this.mApplicationPolicy.removeAppPermissionFromBlackList(paramString);
  }
  
  public boolean removeAppSignatureFromBlackList(String paramString)
  {
    return this.mApplicationPolicy.removeAppSignatureFromBlackList(paramString);
  }
  
  public boolean removeAppSignatureFromWhiteList(String paramString)
  {
    return this.mApplicationPolicy.removeAppSignatureFromWhiteList(paramString);
  }
  
  public boolean removeDefaultApplication(Intent paramIntent, ComponentName paramComponentName)
  {
    try
    {
      boolean bool = this.mApplicationPolicy.removeDefaultApplication(paramIntent, paramComponentName);
      return bool;
    }
    catch (NoSuchMethodError paramIntent)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "removeDefaultApplication", new Class[] { Intent.class, ComponentName.class }, 15));
  }
  
  public int removePackageFromBatteryOptimizationWhiteList(AppIdentity paramAppIdentity)
  {
    try
    {
      int i = this.mApplicationPolicy.removePackageFromBatteryOptimizationWhiteList(AppIdentity.convertToOld(paramAppIdentity));
      return i;
    }
    catch (NoClassDefFoundError paramAppIdentity)
    {
      throw new NoClassDefFoundError(paramAppIdentity.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "removePackageFromBatteryOptimizationWhiteList", new Class[] { AppIdentity.class }, 20));
    }
    catch (NoSuchMethodError paramAppIdentity)
    {
      for (;;) {}
    }
  }
  
  public boolean removePackagesFromClearCacheBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromClearCacheBlackList(paramList);
  }
  
  public boolean removePackagesFromClearCacheWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromClearCacheWhiteList(paramList);
  }
  
  public boolean removePackagesFromClearDataBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromClearDataBlackList(paramList);
  }
  
  public boolean removePackagesFromClearDataWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromClearDataWhiteList(paramList);
  }
  
  public boolean removePackagesFromDisableClipboardBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromDisableClipboardBlackList(paramList);
  }
  
  public boolean removePackagesFromDisableClipboardWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromDisableClipboardWhiteList(paramList);
  }
  
  public boolean removePackagesFromDisableUpdateBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromDisableUpdateBlackList(paramList);
  }
  
  public boolean removePackagesFromDisableUpdateWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromDisableUpdateWhiteList(paramList);
  }
  
  public boolean removePackagesFromFocusMonitoringList(List<String> paramList)
  {
    try
    {
      boolean bool = this.mApplicationPolicy.removePackagesFromFocusMonitoringList(paramList);
      return bool;
    }
    catch (NoSuchMethodError paramList)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "removePackagesFromFocusMonitoringList", new Class[] { List.class }, 15));
  }
  
  public boolean removePackagesFromForceStopBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromForceStopBlackList(paramList);
  }
  
  public boolean removePackagesFromForceStopWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromForceStopWhiteList(paramList);
  }
  
  public boolean removePackagesFromNotificationBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromNotificationBlackList(paramList);
  }
  
  public boolean removePackagesFromNotificationWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromNotificationWhiteList(paramList);
  }
  
  public boolean removePackagesFromPreventStartBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromPreventStartBlackList(paramList);
  }
  
  public boolean removePackagesFromWidgetBlackList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromWidgetBlackList(paramList);
  }
  
  public boolean removePackagesFromWidgetWhiteList(List<String> paramList)
  {
    return this.mApplicationPolicy.removePackagesFromWidgetWhiteList(paramList);
  }
  
  public int setAfWProxy(boolean paramBoolean, AppIdentity paramAppIdentity, Bundle paramBundle)
  {
    try
    {
      int i = this.mApplicationPolicy.setAfWProxy(paramBoolean, AppIdentity.convertToOld(paramAppIdentity), convertToOldBundle(paramBundle));
      return i;
    }
    catch (NoClassDefFoundError paramAppIdentity)
    {
      throw new NoClassDefFoundError(paramAppIdentity.getMessage());
      throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "setAfWProxy", new Class[] { Boolean.TYPE, AppIdentity.class, Bundle.class }, 19));
    }
    catch (NoSuchMethodError paramAppIdentity)
    {
      for (;;) {}
    }
  }
  
  public boolean setApplicationComponentState(ComponentName paramComponentName, boolean paramBoolean)
  {
    return this.mApplicationPolicy.setApplicationComponentState(paramComponentName, paramBoolean);
  }
  
  public void setApplicationInstallationDisabled(String paramString)
  {
    this.mApplicationPolicy.setApplicationInstallationDisabled(paramString);
  }
  
  public void setApplicationInstallationEnabled(String paramString)
  {
    this.mApplicationPolicy.setApplicationInstallationEnabled(paramString);
  }
  
  public boolean setApplicationInstallationMode(int paramInt)
  {
    return this.mApplicationPolicy.setApplicationInstallationMode(paramInt);
  }
  
  public boolean setApplicationNotificationMode(int paramInt)
  {
    return this.mApplicationPolicy.setApplicationNotificationMode(paramInt);
  }
  
  public void setApplicationRestrictions(ComponentName paramComponentName, String paramString, Bundle paramBundle)
  {
    try
    {
      this.mApplicationPolicy.setApplicationRestrictions(paramComponentName, paramString, paramBundle);
      return;
    }
    catch (NoSuchMethodError paramComponentName)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "setApplicationRestrictions", new Class[] { ComponentName.class, String.class, Bundle.class }, 20));
  }
  
  public String[] setApplicationStateList(String[] paramArrayOfString, boolean paramBoolean)
  {
    return this.mApplicationPolicy.setApplicationStateList(paramArrayOfString, paramBoolean);
  }
  
  public void setApplicationUninstallationDisabled(String paramString)
  {
    this.mApplicationPolicy.setApplicationUninstallationDisabled(paramString);
  }
  
  public void setApplicationUninstallationEnabled(String paramString)
  {
    this.mApplicationPolicy.setApplicationUninstallationEnabled(paramString);
  }
  
  public boolean setApplicationUninstallationMode(int paramInt)
  {
    return this.mApplicationPolicy.setApplicationUninstallationMode(paramInt);
  }
  
  public boolean setDefaultApplication(Intent paramIntent, ComponentName paramComponentName)
  {
    try
    {
      boolean bool = this.mApplicationPolicy.setDefaultApplication(paramIntent, paramComponentName);
      return bool;
    }
    catch (NoSuchMethodError paramIntent)
    {
      for (;;) {}
    }
    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ApplicationPolicy.class, "setDefaultApplication", new Class[] { Intent.class, ComponentName.class }, 15));
  }
  
  public boolean setDisableApplication(String paramString)
  {
    return this.mApplicationPolicy.setDisableApplication(paramString);
  }
  
  public boolean setEnableApplication(String paramString)
  {
    return this.mApplicationPolicy.setEnableApplication(paramString);
  }
  
  public boolean startApp(String paramString1, String paramString2)
  {
    return this.mApplicationPolicy.startApp(paramString1, paramString2);
  }
  
  public boolean stopApp(String paramString)
  {
    return this.mApplicationPolicy.stopApp(paramString);
  }
  
  public boolean uninstallApplication(String paramString, boolean paramBoolean)
  {
    return this.mApplicationPolicy.uninstallApplication(paramString, paramBoolean);
  }
  
  public List<String> uninstallApplications(List<String> paramList)
  {
    return this.mApplicationPolicy.uninstallApplications(paramList);
  }
  
  public boolean updateApplication(String paramString)
  {
    return this.mApplicationPolicy.updateApplication(paramString);
  }
  
  public boolean wipeApplicationData(String paramString)
  {
    return this.mApplicationPolicy.wipeApplicationData(paramString);
  }
}
