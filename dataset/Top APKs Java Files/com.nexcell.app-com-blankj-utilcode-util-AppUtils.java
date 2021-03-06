package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class AppUtils
{
  private AppUtils()
  {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }
  
  public static boolean cleanAppData(File... paramVarArgs)
  {
    boolean bool = CleanUtils.cleanInternalCache() & CleanUtils.cleanInternalDbs() & CleanUtils.cleanInternalSp() & CleanUtils.cleanInternalFiles() & CleanUtils.cleanExternalCache();
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      bool &= CleanUtils.cleanCustomCache(paramVarArgs[i]);
      i += 1;
    }
    return bool;
  }
  
  public static boolean cleanAppData(String... paramVarArgs)
  {
    File[] arrayOfFile = new File[paramVarArgs.length];
    int k = paramVarArgs.length;
    int j = 0;
    int i = 0;
    while (j < k)
    {
      arrayOfFile[i] = new File(paramVarArgs[j]);
      j += 1;
      i += 1;
    }
    return cleanAppData(arrayOfFile);
  }
  
  public static void exitApp()
  {
    List localList = Utils.sActivityList;
    int i = localList.size() - 1;
    while (i >= 0)
    {
      ((Activity)localList.get(i)).finish();
      localList.remove(i);
      i -= 1;
    }
    System.exit(0);
  }
  
  public static void getAppDetailsSettings()
  {
    getAppDetailsSettings(Utils.getApp().getPackageName());
  }
  
  public static void getAppDetailsSettings(String paramString)
  {
    if (isSpace(paramString)) {
      return;
    }
    Utils.getApp().startActivity(IntentUtils.getAppDetailsSettingsIntent(paramString, true));
  }
  
  public static Drawable getAppIcon()
  {
    return getAppIcon(Utils.getApp().getPackageName());
  }
  
  public static Drawable getAppIcon(String paramString)
  {
    if (isSpace(paramString)) {
      return null;
    }
    try
    {
      PackageManager localPackageManager = Utils.getApp().getPackageManager();
      paramString = localPackageManager.getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.applicationInfo.loadIcon(localPackageManager);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static AppInfo getAppInfo()
  {
    return getAppInfo(Utils.getApp().getPackageName());
  }
  
  public static AppInfo getAppInfo(String paramString)
  {
    try
    {
      PackageManager localPackageManager = Utils.getApp().getPackageManager();
      paramString = getBean(localPackageManager, localPackageManager.getPackageInfo(paramString, 0));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String getAppName()
  {
    return getAppName(Utils.getApp().getPackageName());
  }
  
  public static String getAppName(String paramString)
  {
    if (isSpace(paramString)) {
      return null;
    }
    try
    {
      PackageManager localPackageManager = Utils.getApp().getPackageManager();
      paramString = localPackageManager.getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.applicationInfo.loadLabel(localPackageManager).toString();
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String getAppPackageName()
  {
    return Utils.getApp().getPackageName();
  }
  
  public static String getAppPath()
  {
    return getAppPath(Utils.getApp().getPackageName());
  }
  
  public static String getAppPath(String paramString)
  {
    if (isSpace(paramString)) {
      return null;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.applicationInfo.sourceDir;
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static Signature[] getAppSignature()
  {
    return getAppSignature(Utils.getApp().getPackageName());
  }
  
  public static Signature[] getAppSignature(String paramString)
  {
    if (isSpace(paramString)) {
      return null;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getPackageInfo(paramString, 64);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.signatures;
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String getAppSignatureSHA1()
  {
    return getAppSignatureSHA1(Utils.getApp().getPackageName());
  }
  
  public static String getAppSignatureSHA1(String paramString)
  {
    paramString = getAppSignature(paramString);
    if (paramString == null) {
      return null;
    }
    return EncryptUtils.encryptSHA1ToString(paramString[0].toByteArray()).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
  }
  
  public static int getAppVersionCode()
  {
    return getAppVersionCode(Utils.getApp().getPackageName());
  }
  
  public static int getAppVersionCode(String paramString)
  {
    if (isSpace(paramString)) {
      return -1;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return -1;
      }
      int i = paramString.versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return -1;
  }
  
  public static String getAppVersionName()
  {
    return getAppVersionName(Utils.getApp().getPackageName());
  }
  
  public static String getAppVersionName(String paramString)
  {
    if (isSpace(paramString)) {
      return null;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.versionName;
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static List<AppInfo> getAppsInfo()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = Utils.getApp().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      AppInfo localAppInfo = getBean(localPackageManager, (PackageInfo)localIterator.next());
      if (localAppInfo != null) {
        localArrayList.add(localAppInfo);
      }
    }
    return localArrayList;
  }
  
  private static AppInfo getBean(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    if ((paramPackageManager != null) && (paramPackageInfo != null))
    {
      ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
      String str1 = paramPackageInfo.packageName;
      String str2 = localApplicationInfo.loadLabel(paramPackageManager).toString();
      paramPackageManager = localApplicationInfo.loadIcon(paramPackageManager);
      String str3 = localApplicationInfo.sourceDir;
      String str4 = paramPackageInfo.versionName;
      int i = paramPackageInfo.versionCode;
      boolean bool;
      if ((localApplicationInfo.flags & 0x1) != 0) {
        bool = true;
      } else {
        bool = false;
      }
      return new AppInfo(str1, str2, paramPackageManager, str3, str4, i, bool);
    }
    return null;
  }
  
  public static void installApp(Activity paramActivity, File paramFile, String paramString, int paramInt)
  {
    if (!FileUtils.isFileExists(paramFile)) {
      return;
    }
    paramActivity.startActivityForResult(IntentUtils.getInstallAppIntent(paramFile, paramString), paramInt);
  }
  
  public static void installApp(Activity paramActivity, String paramString1, String paramString2, int paramInt)
  {
    installApp(paramActivity, FileUtils.getFileByPath(paramString1), paramString2, paramInt);
  }
  
  public static void installApp(File paramFile, String paramString)
  {
    if (!FileUtils.isFileExists(paramFile)) {
      return;
    }
    Utils.getApp().startActivity(IntentUtils.getInstallAppIntent(paramFile, paramString, true));
  }
  
  public static void installApp(String paramString1, String paramString2)
  {
    installApp(FileUtils.getFileByPath(paramString1), paramString2);
  }
  
  public static boolean installAppSilent(String paramString)
  {
    boolean bool1 = FileUtils.isFileExists(FileUtils.getFileByPath(paramString));
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    bool1 = isDeviceRooted();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install ");
    ((StringBuilder)localObject).append(paramString);
    localObject = ShellUtils.execCmd(((StringBuilder)localObject).toString(), bool1);
    if ((((ShellUtils.CommandResult)localObject).successMsg != null) && (((ShellUtils.CommandResult)localObject).successMsg.toLowerCase().contains("success"))) {
      return true;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("LD_LIBRARY_PATH=/vendor/lib:/system/lib64 pm install ");
    ((StringBuilder)localObject).append(paramString);
    paramString = ShellUtils.execCmd(((StringBuilder)localObject).toString(), bool1, true);
    bool1 = bool2;
    if (paramString.successMsg != null)
    {
      bool1 = bool2;
      if (paramString.successMsg.toLowerCase().contains("success")) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isAppDebug()
  {
    return isAppDebug(Utils.getApp().getPackageName());
  }
  
  public static boolean isAppDebug(String paramString)
  {
    boolean bool1 = isSpace(paramString);
    boolean bool2 = false;
    if (bool1) {
      return false;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getApplicationInfo(paramString, 0);
      bool1 = bool2;
      if (paramString != null)
      {
        int i = paramString.flags;
        bool1 = bool2;
        if ((i & 0x2) != 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static boolean isAppForeground()
  {
    Object localObject = ((ActivityManager)Utils.getApp().getSystemService("activity")).getRunningAppProcesses();
    if (localObject != null)
    {
      if (((List)localObject).size() == 0) {
        return false;
      }
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        if (localRunningAppProcessInfo.importance == 100) {
          return localRunningAppProcessInfo.processName.equals(Utils.getApp().getPackageName());
        }
      }
      return false;
    }
    return false;
  }
  
  public static boolean isAppForeground(String paramString)
  {
    return (!isSpace(paramString)) && (paramString.equals(ProcessUtils.getForegroundProcessName()));
  }
  
  public static boolean isAppRoot()
  {
    ShellUtils.CommandResult localCommandResult = ShellUtils.execCmd("echo root", true);
    if (localCommandResult.result == 0) {
      return true;
    }
    if (localCommandResult.errorMsg != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("isAppRoot() called");
      localStringBuilder.append(localCommandResult.errorMsg);
      Log.d("AppUtils", localStringBuilder.toString());
    }
    return false;
  }
  
  private static boolean isDeviceRooted()
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "/system/bin/";
    arrayOfString[1] = "/system/xbin/";
    arrayOfString[2] = "/sbin/";
    arrayOfString[3] = "/system/sd/xbin/";
    arrayOfString[4] = "/system/bin/failsafe/";
    arrayOfString[5] = "/data/local/xbin/";
    arrayOfString[6] = "/data/local/bin/";
    arrayOfString[7] = "/data/local/";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("su");
      if (new File(localStringBuilder.toString()).exists()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isInstallApp(String paramString)
  {
    return (!isSpace(paramString)) && (IntentUtils.getLaunchAppIntent(paramString) != null);
  }
  
  public static boolean isInstallApp(String paramString1, String paramString2)
  {
    paramString1 = new Intent(paramString1);
    paramString1.addCategory(paramString2);
    paramString2 = Utils.getApp().getPackageManager();
    boolean bool = false;
    if (paramString2.resolveActivity(paramString1, 0) != null) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean isSpace(String paramString)
  {
    if (paramString == null) {
      return true;
    }
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      if (!Character.isWhitespace(paramString.charAt(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static boolean isSystemApp()
  {
    return isSystemApp(Utils.getApp().getPackageName());
  }
  
  public static boolean isSystemApp(String paramString)
  {
    if (isSpace(paramString)) {
      return false;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getApplicationInfo(paramString, 0);
      if (paramString != null)
      {
        int i = paramString.flags;
        if ((i & 0x1) != 0) {
          return true;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static void launchApp(Activity paramActivity, String paramString, int paramInt)
  {
    if (isSpace(paramString)) {
      return;
    }
    paramActivity.startActivityForResult(IntentUtils.getLaunchAppIntent(paramString), paramInt);
  }
  
  public static void launchApp(String paramString)
  {
    if (isSpace(paramString)) {
      return;
    }
    Utils.getApp().startActivity(IntentUtils.getLaunchAppIntent(paramString, true));
  }
  
  public static void uninstallApp(Activity paramActivity, String paramString, int paramInt)
  {
    if (isSpace(paramString)) {
      return;
    }
    paramActivity.startActivityForResult(IntentUtils.getUninstallAppIntent(paramString), paramInt);
  }
  
  public static void uninstallApp(String paramString)
  {
    if (isSpace(paramString)) {
      return;
    }
    Utils.getApp().startActivity(IntentUtils.getUninstallAppIntent(paramString, true));
  }
  
  public static boolean uninstallAppSilent(String paramString, boolean paramBoolean)
  {
    boolean bool2 = isSpace(paramString);
    boolean bool1 = false;
    if (bool2) {
      return false;
    }
    bool2 = isDeviceRooted();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall ");
    if (paramBoolean) {
      localObject = "-k ";
    } else {
      localObject = "";
    }
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(paramString);
    Object localObject = ShellUtils.execCmd(localStringBuilder.toString(), bool2, true);
    if ((((ShellUtils.CommandResult)localObject).successMsg != null) && (((ShellUtils.CommandResult)localObject).successMsg.toLowerCase().contains("success"))) {
      return true;
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("LD_LIBRARY_PATH=/vendor/lib:/system/lib64 pm uninstall ");
    if (paramBoolean) {
      localObject = "-k ";
    } else {
      localObject = "";
    }
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(paramString);
    paramString = ShellUtils.execCmd(localStringBuilder.toString(), bool2, true);
    paramBoolean = bool1;
    if (paramString.successMsg != null)
    {
      paramBoolean = bool1;
      if (paramString.successMsg.toLowerCase().contains("success")) {
        paramBoolean = true;
      }
    }
    return paramBoolean;
  }
  
  public static class AppInfo
  {
    private Drawable icon;
    private boolean isSystem;
    private String name;
    private String packageName;
    private String packagePath;
    private int versionCode;
    private String versionName;
    
    public AppInfo(String paramString1, String paramString2, Drawable paramDrawable, String paramString3, String paramString4, int paramInt, boolean paramBoolean)
    {
      setName(paramString2);
      setIcon(paramDrawable);
      setPackageName(paramString1);
      setPackagePath(paramString3);
      setVersionName(paramString4);
      setVersionCode(paramInt);
      setSystem(paramBoolean);
    }
    
    public Drawable getIcon()
    {
      return this.icon;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public String getPackageName()
    {
      return this.packageName;
    }
    
    public String getPackagePath()
    {
      return this.packagePath;
    }
    
    public int getVersionCode()
    {
      return this.versionCode;
    }
    
    public String getVersionName()
    {
      return this.versionName;
    }
    
    public boolean isSystem()
    {
      return this.isSystem;
    }
    
    public void setIcon(Drawable paramDrawable)
    {
      this.icon = paramDrawable;
    }
    
    public void setName(String paramString)
    {
      this.name = paramString;
    }
    
    public void setPackageName(String paramString)
    {
      this.packageName = paramString;
    }
    
    public void setPackagePath(String paramString)
    {
      this.packagePath = paramString;
    }
    
    public void setSystem(boolean paramBoolean)
    {
      this.isSystem = paramBoolean;
    }
    
    public void setVersionCode(int paramInt)
    {
      this.versionCode = paramInt;
    }
    
    public void setVersionName(String paramString)
    {
      this.versionName = paramString;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("pkg name: ");
      localStringBuilder.append(getPackageName());
      localStringBuilder.append("\napp name: ");
      localStringBuilder.append(getName());
      localStringBuilder.append("\napp path: ");
      localStringBuilder.append(getPackagePath());
      localStringBuilder.append("\napp v name: ");
      localStringBuilder.append(getVersionName());
      localStringBuilder.append("\napp v code: ");
      localStringBuilder.append(getVersionCode());
      localStringBuilder.append("\nis system: ");
      localStringBuilder.append(isSystem());
      return localStringBuilder.toString();
    }
  }
}
