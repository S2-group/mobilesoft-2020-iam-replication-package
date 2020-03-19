package com.blankj.utilcode.util;

import android.app.Activity;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class AppUtils
{
  private static final char[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  
  private AppUtils()
  {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }
  
  private static String bytes2HexString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return "";
    }
    int k = paramArrayOfByte.length;
    if (k <= 0) {
      return "";
    }
    char[] arrayOfChar1 = new char[k << 1];
    int i = 0;
    int j = 0;
    while (i < k)
    {
      int m = j + 1;
      char[] arrayOfChar2 = HEX_DIGITS;
      arrayOfChar1[j] = arrayOfChar2[(paramArrayOfByte[i] >>> 4 & 0xF)];
      j = m + 1;
      arrayOfChar1[m] = arrayOfChar2[(paramArrayOfByte[i] & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar1);
  }
  
  private static byte[] encryptSHA1(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      if (paramArrayOfByte.length <= 0) {
        return null;
      }
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA1");
        localMessageDigest.update(paramArrayOfByte);
        paramArrayOfByte = localMessageDigest.digest();
        return paramArrayOfByte;
      }
      catch (NoSuchAlgorithmException paramArrayOfByte)
      {
        paramArrayOfByte.printStackTrace();
      }
    }
    return null;
  }
  
  private static String encryptSHA1ToString(byte[] paramArrayOfByte)
  {
    return bytes2HexString(encryptSHA1(paramArrayOfByte));
  }
  
  public static void exitApp()
  {
    LinkedList localLinkedList = Utils.getActivityList();
    int i = localLinkedList.size() - 1;
    while (i >= 0)
    {
      ((Activity)localLinkedList.get(i)).finish();
      i -= 1;
    }
    System.exit(0);
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
      return "";
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
    return "";
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
      return "";
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
    return "";
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
    if (isSpace(paramString)) {
      return "";
    }
    paramString = getAppSignature(paramString);
    if ((paramString != null) && (paramString.length > 0)) {
      return encryptSHA1ToString(paramString[0].toByteArray()).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }
    return "";
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
      return "";
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
    return "";
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
  
  private static File getFileByPath(String paramString)
  {
    if (isSpace(paramString)) {
      return null;
    }
    return new File(paramString);
  }
  
  public static void installApp(Activity paramActivity, File paramFile, int paramInt)
  {
    if (!isFileExists(paramFile)) {
      return;
    }
    paramActivity.startActivityForResult(IntentUtils.getInstallAppIntent(paramFile), paramInt);
  }
  
  @Deprecated
  public static void installApp(Activity paramActivity, File paramFile, String paramString, int paramInt)
  {
    if (!isFileExists(paramFile)) {
      return;
    }
    paramActivity.startActivityForResult(IntentUtils.getInstallAppIntent(paramFile, paramString), paramInt);
  }
  
  public static void installApp(Activity paramActivity, String paramString, int paramInt)
  {
    installApp(paramActivity, getFileByPath(paramString), paramInt);
  }
  
  @Deprecated
  public static void installApp(Activity paramActivity, String paramString1, String paramString2, int paramInt)
  {
    installApp(paramActivity, getFileByPath(paramString1), paramString2, paramInt);
  }
  
  public static void installApp(File paramFile)
  {
    if (!isFileExists(paramFile)) {
      return;
    }
    Utils.getApp().startActivity(IntentUtils.getInstallAppIntent(paramFile, true));
  }
  
  @Deprecated
  public static void installApp(File paramFile, String paramString)
  {
    if (!isFileExists(paramFile)) {
      return;
    }
    Utils.getApp().startActivity(IntentUtils.getInstallAppIntent(paramFile, paramString, true));
  }
  
  public static void installApp(String paramString)
  {
    installApp(getFileByPath(paramString));
  }
  
  @Deprecated
  public static void installApp(String paramString1, String paramString2)
  {
    installApp(getFileByPath(paramString1), paramString2);
  }
  
  public static boolean installAppSilent(File paramFile)
  {
    return installAppSilent(paramFile, null);
  }
  
  public static boolean installAppSilent(File paramFile, String paramString)
  {
    return installAppSilent(paramFile, paramString, isDeviceRooted());
  }
  
  public static boolean installAppSilent(File paramFile, String paramString, boolean paramBoolean)
  {
    if (!isFileExists(paramFile)) {
      return false;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append('"');
    ((StringBuilder)localObject).append(paramFile.getAbsolutePath());
    ((StringBuilder)localObject).append('"');
    localObject = ((StringBuilder)localObject).toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm install ");
    if (paramString == null)
    {
      paramFile = "";
    }
    else
    {
      paramFile = new StringBuilder();
      paramFile.append(paramString);
      paramFile.append(" ");
      paramFile = paramFile.toString();
    }
    localStringBuilder.append(paramFile);
    localStringBuilder.append((String)localObject);
    paramFile = ShellUtils.execCmd(localStringBuilder.toString(), paramBoolean);
    if ((paramFile.successMsg != null) && (paramFile.successMsg.toLowerCase().contains("success"))) {
      return true;
    }
    paramString = new StringBuilder();
    paramString.append("installAppSilent successMsg: ");
    paramString.append(paramFile.successMsg);
    paramString.append(", errorMsg: ");
    paramString.append(paramFile.errorMsg);
    Log.e("AppUtils", paramString.toString());
    return false;
  }
  
  public static boolean installAppSilent(String paramString)
  {
    return installAppSilent(getFileByPath(paramString), null);
  }
  
  public static boolean installAppSilent(String paramString1, String paramString2)
  {
    return installAppSilent(getFileByPath(paramString1), paramString2);
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
    return Utils.isAppForeground();
  }
  
  public static boolean isAppForeground(String paramString)
  {
    if (paramString != null) {
      return (!isSpace(paramString)) && (paramString.equals(ProcessUtils.getForegroundProcessName()));
    }
    throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    if (paramString != null) {
      return (!isSpace(paramString)) && (IntentUtils.getLaunchAppIntent(paramString) != null);
    }
    throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
  }
  
  public static boolean isAppInstalled(String paramString1, String paramString2)
  {
    if (paramString1 != null)
    {
      if (paramString2 != null)
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
      throw new NullPointerException("Argument 'category' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
    throw new NullPointerException("Argument 'action' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
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
  
  public static boolean isAppSystem()
  {
    return isAppSystem(Utils.getApp().getPackageName());
  }
  
  public static boolean isAppSystem(String paramString)
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
  
  private static boolean isFileExists(File paramFile)
  {
    return (paramFile != null) && (paramFile.exists());
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
  
  public static void launchAppDetailsSettings()
  {
    launchAppDetailsSettings(Utils.getApp().getPackageName());
  }
  
  public static void launchAppDetailsSettings(String paramString)
  {
    if (isSpace(paramString)) {
      return;
    }
    Utils.getApp().startActivity(IntentUtils.getLaunchAppDetailsSettingsIntent(paramString, true));
  }
  
  public static void relaunchApp()
  {
    Intent localIntent = Utils.getApp().getPackageManager().getLaunchIntentForPackage(Utils.getApp().getPackageName());
    if (localIntent == null) {
      return;
    }
    localIntent = Intent.makeRestartActivityTask(localIntent.getComponent());
    Utils.getApp().startActivity(localIntent);
    System.exit(0);
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
  
  public static boolean uninstallAppSilent(String paramString)
  {
    return uninstallAppSilent(paramString, false);
  }
  
  public static boolean uninstallAppSilent(String paramString, boolean paramBoolean)
  {
    return uninstallAppSilent(paramString, paramBoolean, isDeviceRooted());
  }
  
  public static boolean uninstallAppSilent(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (isSpace(paramString)) {
      return false;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm uninstall ");
    if (paramBoolean1) {
      localObject = "-k ";
    } else {
      localObject = "";
    }
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(paramString);
    paramString = ShellUtils.execCmd(localStringBuilder.toString(), paramBoolean2);
    if ((paramString.successMsg != null) && (paramString.successMsg.toLowerCase().contains("success"))) {
      return true;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("uninstallAppSilent successMsg: ");
    ((StringBuilder)localObject).append(paramString.successMsg);
    ((StringBuilder)localObject).append(", errorMsg: ");
    ((StringBuilder)localObject).append(paramString.errorMsg);
    Log.e("AppUtils", ((StringBuilder)localObject).toString());
    return false;
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
      localStringBuilder.append("\napp icon: ");
      localStringBuilder.append(getIcon());
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
