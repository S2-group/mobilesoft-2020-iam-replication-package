package com.blankj.utilcode.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppUtils
{
  private AppUtils()
  {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }
  
  public static boolean cleanAppData(Context paramContext, String... paramVarArgs)
  {
    paramContext = new File[paramVarArgs.length];
    int k = paramVarArgs.length;
    int j = 0;
    int i = 0;
    while (j < k)
    {
      paramContext[i] = new File(paramVarArgs[j]);
      j += 1;
      i += 1;
    }
    return cleanAppData(paramContext);
  }
  
  public static boolean cleanAppData(File... paramVarArgs)
  {
    boolean bool = CleanUtils.cleanInternalCache() & CleanUtils.cleanInternalDbs() & CleanUtils.cleanInternalSP() & CleanUtils.cleanInternalFiles() & CleanUtils.cleanExternalCache();
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      bool &= CleanUtils.cleanCustomCache(paramVarArgs[i]);
      i += 1;
    }
    return bool;
  }
  
  public static void getAppDetailsSettings(Context paramContext)
  {
    getAppDetailsSettings(paramContext, paramContext.getPackageName());
  }
  
  public static void getAppDetailsSettings(Context paramContext, String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return;
    }
    paramContext.startActivity(IntentUtils.getAppDetailsSettingsIntent(paramString));
  }
  
  public static Drawable getAppIcon(Context paramContext)
  {
    return getAppIcon(paramContext, paramContext.getPackageName());
  }
  
  public static Drawable getAppIcon(Context paramContext, String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return null;
    }
    try
    {
      paramContext = paramContext.getPackageManager();
      paramString = paramContext.getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramContext = paramString.applicationInfo.loadIcon(paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static AppInfo getAppInfo(Context paramContext)
  {
    return getAppInfo(paramContext, paramContext.getPackageName());
  }
  
  public static AppInfo getAppInfo(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      paramContext = getBean(paramContext, paramContext.getPackageInfo(paramString, 0));
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getAppName(Context paramContext)
  {
    return getAppName(paramContext, paramContext.getPackageName());
  }
  
  public static String getAppName(Context paramContext, String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return null;
    }
    try
    {
      paramContext = paramContext.getPackageManager();
      paramString = paramContext.getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramContext = paramString.applicationInfo.loadLabel(paramContext).toString();
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getAppPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static String getAppPath(Context paramContext)
  {
    return getAppPath(paramContext, paramContext.getPackageName());
  }
  
  public static String getAppPath(Context paramContext, String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return null;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.applicationInfo.sourceDir;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Signature[] getAppSignature(Context paramContext)
  {
    return getAppSignature(paramContext, paramContext.getPackageName());
  }
  
  @SuppressLint({"PackageManagerGetSignatures"})
  public static Signature[] getAppSignature(Context paramContext, String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return null;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 64);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.signatures;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getAppSignatureSHA1(Context paramContext)
  {
    return getAppSignatureSHA1(paramContext, paramContext.getPackageName());
  }
  
  public static String getAppSignatureSHA1(Context paramContext, String paramString)
  {
    paramContext = getAppSignature(paramContext, paramString);
    if (paramContext == null) {
      return null;
    }
    return EncryptUtils.encryptSHA1ToString(paramContext[0].toByteArray()).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    return getAppVersionCode(paramContext, paramContext.getPackageName());
  }
  
  public static int getAppVersionCode(Context paramContext, String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return -1;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return -1;
      }
      int i = paramContext.versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    return getAppVersionName(paramContext, paramContext.getPackageName());
  }
  
  public static String getAppVersionName(Context paramContext, String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return null;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static List<AppInfo> getAppsInfo(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      AppInfo localAppInfo = getBean(paramContext, (PackageInfo)localIterator.next());
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
  
  public static void installApp(Activity paramActivity, File paramFile, int paramInt)
  {
    if (!FileUtils.isFileExists(paramFile)) {
      return;
    }
    paramActivity.startActivityForResult(IntentUtils.getInstallAppIntent(paramFile), paramInt);
  }
  
  public static void installApp(Activity paramActivity, String paramString, int paramInt)
  {
    installApp(paramActivity, FileUtils.getFileByPath(paramString), paramInt);
  }
  
  public static void installApp(Context paramContext, File paramFile)
  {
    if (!FileUtils.isFileExists(paramFile)) {
      return;
    }
    paramContext.startActivity(IntentUtils.getInstallAppIntent(paramFile));
  }
  
  public static void installApp(Context paramContext, String paramString)
  {
    installApp(paramContext, FileUtils.getFileByPath(paramString));
  }
  
  public static boolean installAppSilent(String paramString)
  {
    boolean bool1 = FileUtils.isFileExists(FileUtils.getFileByPath(paramString));
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install ");
    localStringBuilder.append(paramString);
    paramString = ShellUtils.execCmd(localStringBuilder.toString(), isSystemApp(Utils.getContext()) ^ true, true);
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
  
  public static boolean isAppDebug(Context paramContext)
  {
    return isAppDebug(paramContext, paramContext.getPackageName());
  }
  
  public static boolean isAppDebug(Context paramContext, String paramString)
  {
    boolean bool1 = StringUtils.isSpace(paramString);
    boolean bool2 = false;
    if (bool1) {
      return false;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      bool1 = bool2;
      if (paramContext != null)
      {
        int i = paramContext.flags;
        bool1 = bool2;
        if ((i & 0x2) != 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isAppForeground(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
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
          return localRunningAppProcessInfo.processName.equals(paramContext.getPackageName());
        }
      }
      return false;
    }
    return false;
  }
  
  public static boolean isAppForeground(Context paramContext, String paramString)
  {
    return (!StringUtils.isSpace(paramString)) && (paramString.equals(ProcessUtils.getForegroundProcessName()));
  }
  
  public static boolean isAppRoot()
  {
    ShellUtils.CommandResult localCommandResult = ShellUtils.execCmd("echo root", true);
    if (localCommandResult.result == 0) {
      return true;
    }
    if (localCommandResult.errorMsg != null) {
      LogUtils.d("isAppRoot", localCommandResult.errorMsg);
    }
    return false;
  }
  
  public static boolean isInstallApp(Context paramContext, String paramString)
  {
    return (!StringUtils.isSpace(paramString)) && (IntentUtils.getLaunchAppIntent(paramString) != null);
  }
  
  public static boolean isSystemApp(Context paramContext)
  {
    return isSystemApp(paramContext, paramContext.getPackageName());
  }
  
  public static boolean isSystemApp(Context paramContext, String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return false;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      if (paramContext != null)
      {
        int i = paramContext.flags;
        if ((i & 0x1) != 0) {
          return true;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void launchApp(Activity paramActivity, String paramString, int paramInt)
  {
    if (StringUtils.isSpace(paramString)) {
      return;
    }
    paramActivity.startActivityForResult(IntentUtils.getLaunchAppIntent(paramString), paramInt);
  }
  
  public static void launchApp(String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return;
    }
    Utils.getContext().startActivity(IntentUtils.getLaunchAppIntent(paramString));
  }
  
  public static void uninstallApp(Activity paramActivity, String paramString, int paramInt)
  {
    if (StringUtils.isSpace(paramString)) {
      return;
    }
    paramActivity.startActivityForResult(IntentUtils.getUninstallAppIntent(paramString), paramInt);
  }
  
  public static void uninstallApp(Context paramContext, String paramString)
  {
    if (StringUtils.isSpace(paramString)) {
      return;
    }
    paramContext.startActivity(IntentUtils.getUninstallAppIntent(paramString));
  }
  
  public static boolean uninstallAppSilent(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (StringUtils.isSpace(paramString)) {
      return false;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall ");
    String str;
    if (paramBoolean) {
      str = "-k ";
    } else {
      str = "";
    }
    localStringBuilder.append(str);
    localStringBuilder.append(paramString);
    paramContext = ShellUtils.execCmd(localStringBuilder.toString(), isSystemApp(paramContext) ^ true, true);
    return (paramContext.successMsg != null) && (paramContext.successMsg.toLowerCase().contains("success"));
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
      localStringBuilder.append("App包名：");
      localStringBuilder.append(getPackageName());
      localStringBuilder.append("\nApp名称：");
      localStringBuilder.append(getName());
      localStringBuilder.append("\nApp图标：");
      localStringBuilder.append(getIcon());
      localStringBuilder.append("\nApp路径：");
      localStringBuilder.append(getPackagePath());
      localStringBuilder.append("\nApp版本号：");
      localStringBuilder.append(getVersionName());
      localStringBuilder.append("\nApp版本码：");
      localStringBuilder.append(getVersionCode());
      localStringBuilder.append("\n是否系统App：");
      localStringBuilder.append(isSystem());
      return localStringBuilder.toString();
    }
  }
}
