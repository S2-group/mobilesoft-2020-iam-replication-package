package com.babybus.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.babybus.app.App;
import com.babybus.app.C.Path;
import com.babybus.bean.AppInfoBean;
import com.babybus.bean.AppInfoForWonderlandWBean;
import com.babybus.bean.OpenAppBean;
import com.babybus.managers.DownloadAndInstallApkManager;
import com.babybus.utils.downloadutils.InstallInfo;
import com.babybus.utils.downloadutils.InstallUtil;
import com.babybus.utils.permissionsutils.PermissionUtil;
import com.google.gson.Gson;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.PatchProxyResult;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApkUtil
{
  public static ChangeQuickRedirect changeQuickRedirect;
  
  public ApkUtil() {}
  
  public static boolean apkIsComplete(String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "apkIsComplete(String)", new Class[] { String.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    return App.get().getPackageManager().getPackageArchiveInfo(paramString, 1) != null;
  }
  
  public static boolean chetByQQ(Activity paramActivity, String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramActivity, paramString }, null, (ChangeQuickRedirect)localObject, true, "chetByQQ(Activity,String)", new Class[] { Activity.class, String.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    try
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("mqqwpa://im/chat?chat_type=wpa&uin=");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("&version=1");
      paramString = ((StringBuilder)localObject).toString();
      if (ShellCmdBuilder.isCmdStartActivity())
      {
        ShellCmdBuilder.get().setAction("android.intent.action.VIEW").addFlags(268435456).setDataUri(paramString).execShellCmd();
        return true;
      }
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    ToastUtil.toastShort("您的设备上还没有安装QQ哦~");
    return false;
  }
  
  public static boolean clickLock(long paramLong)
  {
    Object localObject = new Long(paramLong);
    ChangeQuickRedirect localChangeQuickRedirect = changeQuickRedirect;
    Class localClass1 = Long.TYPE;
    Class localClass2 = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { localObject }, null, localChangeQuickRedirect, true, "clickLock(long)", new Class[] { localClass1 }, localClass2);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    return Math.abs(paramLong - App.get().lastTime) < 1000L;
  }
  
  public static String dealPackageName(String paramString)
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "dealPackageName(String)", new Class[] { String.class }, String.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (String)((PatchProxyResult)localObject).result;
    }
    if (paramString.endsWith(".huawei")) {
      return paramString.substring(0, paramString.indexOf(".huawei"));
    }
    localObject = paramString;
    if (paramString.endsWith(".nearme.gamecenter")) {
      localObject = paramString.substring(0, paramString.indexOf(".nearme.gamecenter"));
    }
    return localObject;
  }
  
  public static boolean downloadManagerIsEnabled()
  {
    boolean bool3 = false;
    boolean bool2 = false;
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "downloadManagerIsEnabled()", new Class[0], localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    try
    {
      int i = App.get().getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
      if (Build.VERSION.SDK_INT > 18)
      {
        bool1 = bool2;
        if (i != 2)
        {
          bool1 = bool2;
          if (i != 3)
          {
            bool1 = bool2;
            if (i != 4) {
              bool1 = true;
            }
          }
        }
        return bool1;
      }
      boolean bool1 = bool3;
      if (i != 2)
      {
        bool1 = bool3;
        if (i != 3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    LogUtil.d("com.android.providers.downloads is no found");
    return false;
  }
  
  public static int getApkVersionCode(String paramString)
  {
    Object localObject1 = changeQuickRedirect;
    Object localObject2 = Integer.TYPE;
    localObject1 = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject1, true, "getApkVersionCode(String)", new Class[] { String.class }, (Class)localObject2);
    if (((PatchProxyResult)localObject1).isSupported) {
      return ((Integer)((PatchProxyResult)localObject1).result).intValue();
    }
    localObject1 = App.get().getPackageManager().getInstalledPackages(0).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if (TextUtils.equals(paramString, ((PackageInfo)localObject2).packageName)) {
        return ((PackageInfo)localObject2).versionCode;
      }
    }
    return -1;
  }
  
  public static String getAppName()
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "getAppName()", new Class[0], String.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (String)((PatchProxyResult)localObject).result;
    }
    try
    {
      int i = App.get().getApplicationContext().getPackageManager().getPackageInfo(App.get().getPackageName(), 0).applicationInfo.labelRes;
      localObject = App.get().getResources().getString(i);
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static List<AppInfoBean> getBabyBusInstalledAppList()
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "getBabyBusInstalledAppList()", new Class[0], List.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (List)((PatchProxyResult)localObject).result;
    }
    localObject = new ArrayList();
    Iterator localIterator = getInstalledAppList().iterator();
    while (localIterator.hasNext())
    {
      AppInfoBean localAppInfoBean = (AppInfoBean)localIterator.next();
      if ((!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.wonderland")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.bbtime.android")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.chants")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.talk2kiki")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), App.get().packName))) {
        ((List)localObject).add(localAppInfoBean);
      }
    }
    return localObject;
  }
  
  public static String getInstalledAppInfo()
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "getInstalledAppInfo()", new Class[0], String.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (String)((PatchProxyResult)localObject).result;
    }
    localObject = new ArrayList();
    PackageManager localPackageManager = App.get().getPackageManager();
    if (localPackageManager != null)
    {
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          ((List)localObject).add(new AppInfoForWonderlandWBean(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString(), localPackageInfo.versionName, localPackageInfo.packageName));
        }
      }
    }
    return new Gson().toJson(localObject);
  }
  
  public static List<AppInfoBean> getInstalledAppList()
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "getInstalledAppList()", new Class[0], List.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (List)((PatchProxyResult)localObject).result;
    }
    localObject = new ArrayList();
    PackageManager localPackageManager = App.get().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        String str = localPackageInfo.packageName;
        if (isMediaBabybusApp(str))
        {
          AppInfoBean localAppInfoBean = new AppInfoBean();
          localAppInfoBean.setPackageName(str);
          localAppInfoBean.setName(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localAppInfoBean.setVersion(localPackageInfo.versionName);
          localAppInfoBean.setVersionCode(localPackageInfo.versionCode);
          localAppInfoBean.setApplicationInfo(localPackageInfo.applicationInfo);
          localAppInfoBean.setIcon(localPackageManager.getApplicationIcon(localPackageInfo.applicationInfo));
          ((List)localObject).add(localAppInfoBean);
        }
      }
    }
    return localObject;
  }
  
  public static List<AppInfoBean> getInstalledAppListWithOutLianyong()
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "getInstalledAppListWithOutLianyong()", new Class[0], List.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (List)((PatchProxyResult)localObject).result;
    }
    localObject = new ArrayList();
    PackageManager localPackageManager = App.get().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        String str = localPackageInfo.packageName;
        if (str.startsWith("com.sinyee"))
        {
          AppInfoBean localAppInfoBean = new AppInfoBean();
          localAppInfoBean.setPackageName(str);
          localAppInfoBean.setName(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localAppInfoBean.setVersion(localPackageInfo.versionName);
          localAppInfoBean.setVersionCode(localPackageInfo.versionCode);
          localAppInfoBean.setApplicationInfo(localPackageInfo.applicationInfo);
          localAppInfoBean.setIcon(localPackageManager.getApplicationIcon(localPackageInfo.applicationInfo));
          ((List)localObject).add(localAppInfoBean);
        }
      }
    }
    return localObject;
  }
  
  public static List<String> getInstalledBabyBusAppPackageName()
  {
    int i = 0;
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "getInstalledBabyBusAppPackageName()", new Class[0], List.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (List)((PatchProxyResult)localObject).result;
    }
    try
    {
      localObject = new ArrayList();
      List localList = App.get().getPackageManager().getInstalledPackages(0);
      int j = localList.size();
      while (i < j)
      {
        String str = ((PackageInfo)localList.get(i)).packageName;
        if (isMediaBabybusApp(str)) {
          ((List)localObject).add(str);
        }
        i += 1;
      }
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  private static long getPackageFirstInstallTime(Context paramContext)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Long.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramContext }, null, (ChangeQuickRedirect)localObject, true, "getPackageFirstInstallTime(Context)", new Class[] { Context.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Long)((PatchProxyResult)localObject).result).longValue();
    }
    localObject = paramContext.getPackageName();
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo((String)localObject, 0).firstInstallTime;
      return l;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0L;
  }
  
  private static long getPackageLastUpdateTime(Context paramContext)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Long.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramContext }, null, (ChangeQuickRedirect)localObject, true, "getPackageLastUpdateTime(Context)", new Class[] { Context.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Long)((PatchProxyResult)localObject).result).longValue();
    }
    localObject = paramContext.getPackageName();
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo((String)localObject, 0).lastUpdateTime;
      return l;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0L;
  }
  
  public static String getPlatform()
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "getPlatform()", new Class[0], String.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (String)((PatchProxyResult)localObject).result;
    }
    if (isInternationalApp()) {
      return "3";
    }
    return "2";
  }
  
  public static List<String> getUninstalledAppList()
  {
    int i = 0;
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "getUninstalledAppList()", new Class[0], List.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (List)((PatchProxyResult)localObject).result;
    }
    localObject = new ArrayList();
    if (PermissionUtil.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      File[] arrayOfFile = new File(C.Path.APK_PATH).listFiles();
      if ((arrayOfFile != null) && (arrayOfFile.length > 0))
      {
        int j = arrayOfFile.length;
        while (i < j)
        {
          File localFile = arrayOfFile[i];
          if ((localFile != null) && (apkIsComplete(localFile.getPath()))) {
            ((List)localObject).add(StringUtil.getFileNameWithOutExtension(localFile.getPath()));
          }
          i += 1;
        }
      }
    }
    return localObject;
  }
  
  public static int getVersionCodeUtil(String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Integer.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "getVersionCodeUtil(String)", new Class[] { String.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Integer)((PatchProxyResult)localObject).result).intValue();
    }
    try
    {
      int i = App.get().getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  public static String installApk(String paramString)
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "installApk(String)", new Class[] { String.class }, String.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (String)((PatchProxyResult)localObject).result;
    }
    if ("com.sinyee.babybus.wonderland".equals(App.get().getPackageName()))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(C.Path.WONDERLAND_APK_PATH);
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(".apk");
      localObject = ((StringBuilder)localObject).toString();
      InstallUtil.get().installApkWithPath((String)localObject, new InstallInfo(paramString));
      paramString = (String)localObject;
    }
    else
    {
      localObject = new InstallInfo(paramString);
      if (!App.writeSDCard)
      {
        paramString = new StringBuilder();
        paramString.append(App.get().getExternalFilesDir("apks"));
        paramString.append("/");
        paramString.append(((InstallInfo)localObject).getOpenAppBean().appKey);
        paramString.append(".apk");
        paramString = paramString.toString();
        InstallUtil.get().installPrivateDirApk((InstallInfo)localObject);
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(C.Path.WONDERLAND_APK_PATH);
        localStringBuilder.append("/");
        localStringBuilder.append(paramString);
        localStringBuilder.append(".apk");
        paramString = localStringBuilder.toString();
        InstallUtil.get().installPublicDirApk((InstallInfo)localObject);
      }
    }
    paramString = new File(paramString);
    try
    {
      long l = BBFileUtil.fileSize(paramString);
      paramString = new DecimalFormat("#.00").format(l * 1.0D / 1000.0D / 1000.0D);
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return "-1";
  }
  
  public static void installApkInSDCard(String paramString)
  {
    ChangeQuickRedirect localChangeQuickRedirect = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramString }, null, localChangeQuickRedirect, true, "installApkInSDCard(String)", new Class[] { String.class }, localClass).isSupported) {
      return;
    }
    installApkWithInfo(new OpenAppBean(paramString));
  }
  
  public static void installApkWithInfo(OpenAppBean paramOpenAppBean)
  {
    ChangeQuickRedirect localChangeQuickRedirect = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramOpenAppBean }, null, localChangeQuickRedirect, true, "installApkWithInfo(OpenAppBean)", new Class[] { OpenAppBean.class }, localClass).isSupported) {
      return;
    }
    if (PermissionUtil.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      DownloadAndInstallApkManager.get().installPublicApk(paramOpenAppBean);
      return;
    }
    DownloadAndInstallApkManager.get().installPrivateApk(paramOpenAppBean);
  }
  
  public static void installApp(File paramFile)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramFile }, null, (ChangeQuickRedirect)localObject, true, "installApp(File)", new Class[] { File.class }, localClass).isSupported) {
      return;
    }
    if (ShellCmdBuilder.isCmdStartActivity())
    {
      ShellCmdBuilder.get().setAction("android.intent.action.VIEW").addFlags(268435456).setDataUri(Uri.fromFile(paramFile).toString()).setMimeType("application/vnd.android.package-archive").execShellCmd();
      return;
    }
    localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).setFlags(268435456);
    ((Intent)localObject).setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    App.get().startActivity((Intent)localObject);
  }
  
  public static boolean isApkInDebug(Context paramContext)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramContext }, null, (ChangeQuickRedirect)localObject, true, "isApkInDebug(Context)", new Class[] { Context.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      return (i & 0x2) != 0;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isBabybusPackage(String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "isBabybusPackage(String)", new Class[] { String.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    return (!TextUtils.isEmpty(paramString)) && (isMediaBabybusApp(paramString));
  }
  
  public static boolean isDomesticChannelInternationalApp()
  {
    boolean bool = false;
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "isDomesticChannelInternationalApp()", new Class[0], localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    if ((isXiaomiInternationalApp()) || (isHuaweiInternationalApp()) || (isSamsungInternationalApp()) || (isOppoInternationalApp())) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isDownloaded(String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "isDownloaded(String)", new Class[] { String.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(C.Path.APK_PATH);
    ((StringBuilder)localObject).append("/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(".apk");
    paramString = ((StringBuilder)localObject).toString();
    return (BBFileUtil.checkFile(paramString)) && (apkIsComplete(paramString));
  }
  
  public static Map<String, Boolean> isDownloadeds(List<String> paramList)
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[] { paramList }, null, (ChangeQuickRedirect)localObject, true, "isDownloadeds(List)", new Class[] { List.class }, Map.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (Map)((PatchProxyResult)localObject).result;
    }
    localObject = new HashMap();
    if (paramList == null) {
      return localObject;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      ((Map)localObject).put(str, Boolean.valueOf(isDownloaded(str)));
    }
    return localObject;
  }
  
  public static boolean isFirstInstall()
  {
    boolean bool = false;
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "isFirstInstall()", new Class[0], localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    if (getPackageFirstInstallTime(App.get()) == getPackageLastUpdateTime(App.get())) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isHuaweiInternationalApp()
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "isHuaweiInternationalApp()", new Class[0], localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    return isInternationalApp("A023");
  }
  
  public static boolean isInstalled(String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "isInstalled(String)", new Class[] { String.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    if (App.get().getPackageName().equals(paramString)) {
      return true;
    }
    localObject = App.get().getPackageManager();
    try
    {
      ((PackageManager)localObject).getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isInstalledWeiXin()
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "isInstalledWeiXin()", new Class[0], localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    return isInstalled("com.tencent.mm");
  }
  
  public static Map<String, Boolean> isInstalleds(List<String> paramList)
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[] { paramList }, null, (ChangeQuickRedirect)localObject, true, "isInstalleds(List)", new Class[] { List.class }, Map.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (Map)((PatchProxyResult)localObject).result;
    }
    localObject = new HashMap();
    if (paramList == null) {
      return localObject;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      ((Map)localObject).put(str, Boolean.valueOf(isInstalled(str)));
    }
    return localObject;
  }
  
  public static boolean isInternationalApp()
  {
    boolean bool = false;
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "isInternationalApp()", new Class[0], localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    if (("A005".equals(App.get().channel)) || ("A030".equals(App.get().channel))) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean isInternationalApp(String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "isInternationalApp(String)", new Class[] { String.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    return (paramString.equals(App.get().channel)) && (!"zh".equals(UIUtil.getLanguage()));
  }
  
  public static Boolean isLY()
  {
    Object localObject = changeQuickRedirect;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "isLY()", new Class[0], Boolean.class);
    if (((PatchProxyResult)localObject).isSupported) {
      return (Boolean)((PatchProxyResult)localObject).result;
    }
    return Boolean.valueOf(App.getMetaData().getBoolean("IS_LY", false));
  }
  
  private static boolean isMediaBabybusApp(String paramString)
  {
    boolean bool2 = true;
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "isMediaBabybusApp(String)", new Class[] { String.class }, localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    boolean bool1 = bool2;
    if (!paramString.contains("com.sinyee"))
    {
      bool1 = bool2;
      if (!paramString.contains("com.kid58.lianyong"))
      {
        if (paramString.contains("com.yw")) {
          return true;
        }
        bool1 = false;
      }
    }
    return bool1;
  }
  
  public static boolean isOppoInternationalApp()
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "isOppoInternationalApp()", new Class[0], localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    return isInternationalApp("A016");
  }
  
  public static boolean isRunBabyApp()
  {
    Object localObject1 = changeQuickRedirect;
    Object localObject2 = Boolean.TYPE;
    localObject1 = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject1, true, "isRunBabyApp()", new Class[0], (Class)localObject2);
    if (((PatchProxyResult)localObject1).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject1).result).booleanValue();
    }
    localObject2 = queryAllRunningAppInfo().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject1 = (String)((Iterator)localObject2).next();
      if (((String)localObject1).startsWith("com.sinyee"))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("isRunBabyApp");
        localStringBuilder.append((String)localObject1);
        LogUtil.e(localStringBuilder.toString());
        if ((!TextUtils.equals((CharSequence)localObject1, "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals((CharSequence)localObject1, "com.sinyee.babybus.wonderland")) && (!TextUtils.equals((CharSequence)localObject1, "com.sinyee.babybus.bbtime.android")) && (!TextUtils.equals((CharSequence)localObject1, "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals((CharSequence)localObject1, "com.sinyee.babybus.chants")) && (!TextUtils.equals((CharSequence)localObject1, "com.sinyee.babybus.talk2kiki")) && (!TextUtils.equals((CharSequence)localObject1, App.get().packName)))
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("isRunBabyApp gl ");
          ((StringBuilder)localObject2).append((String)localObject1);
          LogUtil.e(((StringBuilder)localObject2).toString());
          return false;
        }
      }
    }
    return true;
  }
  
  public static boolean isSamsungInternationalApp()
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "isSamsungInternationalApp()", new Class[0], localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    return isInternationalApp("A022");
  }
  
  public static boolean isXiaomiInternationalApp()
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Boolean.TYPE;
    localObject = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject, true, "isXiaomiInternationalApp()", new Class[0], localClass);
    if (((PatchProxyResult)localObject).isSupported) {
      return ((Boolean)((PatchProxyResult)localObject).result).booleanValue();
    }
    return isInternationalApp("A004");
  }
  
  public static void launchApp(String paramString, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.useAs(TypeTransformer.java:868)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:668)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static void launchApp4Recommend(String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "launchApp4Recommend(String)", new Class[] { String.class }, localClass).isSupported) {
      return;
    }
    if (ShellCmdBuilder.isCmdStartActivity())
    {
      ShellCmdBuilder.get().setAction("android.intent.action.MAIN").addFlags(268435456).setComponent("com.sinyee.babybus.recommendapp", "com.sinyee.babybus.recommendapp.Main").execShellCmd();
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("num ===");
    ((StringBuilder)localObject).append(paramString);
    Log.e("babybus", ((StringBuilder)localObject).toString());
    paramString = new Intent("android.intent.action.MAIN");
    paramString.setComponent(new ComponentName("com.sinyee.babybus.recommendapp", "com.sinyee.babybus.recommendapp.Main"));
    App.get().mainActivity.startActivity(paramString);
  }
  
  public static void launchApp4wonderland(String paramString, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static void launchSubPackage(String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "launchSubPackage(String)", new Class[] { String.class }, localClass).isSupported) {
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("ApkUtil launchSubPackage:");
    ((StringBuilder)localObject).append(paramString);
    Log.e("Test", ((StringBuilder)localObject).toString());
    localObject = App.get().mainActivity.getPackageManager().getLaunchIntentForPackage(paramString);
    if (ShellCmdBuilder.isCmdStartActivity())
    {
      localObject = ((Intent)localObject).getComponent();
      ShellCmdBuilder.get().addFlags(268435456).setComponent(paramString, ((ComponentName)localObject).getClassName()).addPram("IS_FROM_WONDERLAND", Boolean.valueOf(true)).execShellCmd();
      return;
    }
    ((Intent)localObject).setFlags(268435456);
    ((Intent)localObject).putExtra("IS_FROM_WONDERLAND", true);
    App.get().mainActivity.startActivity((Intent)localObject);
  }
  
  public static void openBrowser(String paramString, int paramInt)
  {
    Object localObject = new Integer(paramInt);
    ChangeQuickRedirect localChangeQuickRedirect = changeQuickRedirect;
    Class localClass1 = Integer.TYPE;
    Class localClass2 = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramString, localObject }, null, localChangeQuickRedirect, true, "openBrowser(String,int)", new Class[] { String.class, localClass1 }, localClass2).isSupported) {
      return;
    }
    if ((paramString != null) && (!"".equals(paramString)))
    {
      localObject = new Intent();
      ((Intent)localObject).setAction("android.intent.action.VIEW");
      ((Intent)localObject).addFlags(268435456);
      ((Intent)localObject).setData(Uri.parse(paramString));
      App.get().getCurrentAct().startActivityForResult((Intent)localObject, paramInt);
    }
  }
  
  private static List<String> queryAllRunningAppInfo()
  {
    Object localObject1 = changeQuickRedirect;
    localObject1 = PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject1, true, "queryAllRunningAppInfo()", new Class[0], List.class);
    if (((PatchProxyResult)localObject1).isSupported) {
      return (List)((PatchProxyResult)localObject1).result;
    }
    localObject1 = new ArrayList();
    try
    {
      Object localObject2 = App.get().getPackageManager().getInstalledApplications(8192);
      HashMap localHashMap = new HashMap();
      Object localObject3 = ((ActivityManager)App.get().getSystemService("activity")).getRunningAppProcesses().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject3).next();
        String[] arrayOfString = localRunningAppProcessInfo.pkgList;
        int i = 0;
        while (i < arrayOfString.length)
        {
          localHashMap.put(arrayOfString[i], localRunningAppProcessInfo);
          i += 1;
        }
      }
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
        if (localHashMap.containsKey(((ApplicationInfo)localObject3).packageName)) {
          ((List)localObject1).add(((ApplicationInfo)localObject3).packageName);
        }
      }
      return localObject1;
    }
    catch (Exception localException) {}
    return localObject1;
  }
  
  public static void refreshInstalledAppInfo()
  {
    Object localObject1 = changeQuickRedirect;
    Object localObject2 = Void.TYPE;
    if (PatchProxy.proxy(new Object[0], null, (ChangeQuickRedirect)localObject1, true, "refreshInstalledAppInfo()", new Class[0], (Class)localObject2).isSupported) {
      return;
    }
    localObject1 = new Gson();
    localObject2 = new ArrayList();
    PackageManager localPackageManager = App.get().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        ((List)localObject2).add(new AppInfoForWonderlandWBean(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString(), localPackageInfo.versionName, localPackageInfo.packageName));
      }
    }
    SpUtil.putString("INSTALL_APP_INFO", ((Gson)localObject1).toJson(localObject2));
  }
  
  public static void showContinueCancelDialog(Context paramContext, final int paramInt, final String paramString1, final String paramString2, final DialogInterface.OnClickListener paramOnClickListener1, final DialogInterface.OnClickListener paramOnClickListener2)
  {
    Integer localInteger = new Integer(paramInt);
    ChangeQuickRedirect localChangeQuickRedirect = changeQuickRedirect;
    Class localClass1 = Integer.TYPE;
    Class localClass2 = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramContext, localInteger, paramString1, paramString2, paramOnClickListener1, paramOnClickListener2 }, null, localChangeQuickRedirect, true, "showContinueCancelDialog(Context,int,String,String,DialogInterface$OnClickListener,DialogInterface$OnClickListener)", new Class[] { Context.class, localClass1, String.class, String.class, DialogInterface.OnClickListener.class, DialogInterface.OnClickListener.class }, localClass2).isSupported) {
      return;
    }
    UIUtil.runOnUiThread(new Runnable()
    {
      public static ChangeQuickRedirect changeQuickRedirect;
      
      public void run()
      {
        Object localObject = changeQuickRedirect;
        Class localClass = Void.TYPE;
        if (PatchProxy.proxy(new Object[0], this, (ChangeQuickRedirect)localObject, false, "run()", new Class[0], localClass).isSupported) {
          return;
        }
        localObject = new AlertDialog.Builder(this.val$context);
        ((AlertDialog.Builder)localObject).setCancelable(true);
        ((AlertDialog.Builder)localObject).setIcon(paramInt);
        ((AlertDialog.Builder)localObject).setTitle(paramString1);
        ((AlertDialog.Builder)localObject).setMessage(paramString2);
        ((AlertDialog.Builder)localObject).setInverseBackgroundForced(true);
        ((AlertDialog.Builder)localObject).setPositiveButton("Continue", paramOnClickListener1);
        ((AlertDialog.Builder)localObject).setNegativeButton("Cancel", paramOnClickListener2);
        ((AlertDialog.Builder)localObject).create().show();
      }
    });
  }
  
  public static void uninstallApp(String paramString)
  {
    Object localObject = changeQuickRedirect;
    Class localClass = Void.TYPE;
    if (PatchProxy.proxy(new Object[] { paramString }, null, (ChangeQuickRedirect)localObject, true, "uninstallApp(String)", new Class[] { String.class }, localClass).isSupported) {
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("package:");
    ((StringBuilder)localObject).append(paramString);
    localObject = new Intent("android.intent.action.DELETE", Uri.parse(((StringBuilder)localObject).toString()));
    ((Intent)localObject).addFlags(536870912);
    App.get().uninstallApk = paramString;
    App.get().mainActivity.startActivityForResult((Intent)localObject, 8402);
  }
}
