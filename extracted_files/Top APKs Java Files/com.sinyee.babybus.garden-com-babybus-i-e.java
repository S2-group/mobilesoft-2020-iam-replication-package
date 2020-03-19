package com.babybus.i;

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
import android.text.TextUtils;
import android.util.Log;
import com.babybus.app.App;
import com.babybus.app.b.y;
import com.babybus.bean.AppInfoBean;
import com.babybus.bean.AppInfoForWonderlandWBean;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class e
{
  private static long jdField_do = 1000L;
  
  public e() {}
  
  public static String jdMethod_break()
  {
    if (jdMethod_byte()) {
      return "3";
    }
    return "2";
  }
  
  public static void jdMethod_byte(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ApkUtil launchSubPackage:");
    localStringBuilder.append(paramString);
    Log.e("Test", localStringBuilder.toString());
    paramString = App.jdMethod_do().jdField_switch.getPackageManager().getLaunchIntentForPackage(paramString);
    paramString.setFlags(268435456);
    paramString.putExtra("IS_FROM_WONDERLAND", true);
    App.jdMethod_do().jdField_switch.startActivity(paramString);
  }
  
  public static boolean jdMethod_byte()
  {
    return ("A005".equals(App.jdMethod_do().jdField_else)) || ("A030".equals(App.jdMethod_do().jdField_else));
  }
  
  public static String jdMethod_case()
  {
    try
    {
      int i = App.jdMethod_do().getApplicationContext().getPackageManager().getPackageInfo(App.jdMethod_do().getPackageName(), 0).applicationInfo.labelRes;
      String str = App.jdMethod_do().getResources().getString(i);
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static boolean jdMethod_case(String paramString)
  {
    return "com.sinyee.babybus.recommendapp".equals(paramString);
  }
  
  public static List<String> jdMethod_catch()
  {
    ArrayList localArrayList = new ArrayList();
    if (al.jdMethod_do("android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      File[] arrayOfFile = new File(b.y.else).listFiles();
      if ((arrayOfFile != null) && (arrayOfFile.length > 0))
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          File localFile = arrayOfFile[i];
          if ((localFile != null) && (jdMethod_else(localFile.getPath()))) {
            localArrayList.add(au.jdMethod_do(localFile.getPath()));
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  public static boolean jdMethod_char()
  {
    return jdMethod_for("com.tencent.mm");
  }
  
  public static boolean jdMethod_char(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(b.y.else);
    localStringBuilder.append("/");
    localStringBuilder.append(paramString);
    localStringBuilder.append(".apk");
    paramString = localStringBuilder.toString();
    return (g.jdMethod_case(paramString)) && (jdMethod_else(paramString));
  }
  
  public static boolean jdMethod_class()
  {
    Object localObject = const().iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (str.startsWith("com.sinyee"))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("isRunBabyApp");
        localStringBuilder.append(str);
        z.jdMethod_for(localStringBuilder.toString());
        if ((!TextUtils.equals(str, "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(str, "com.sinyee.babybus.wonderland")) && (!TextUtils.equals(str, "com.sinyee.babybus.bbtime.android")) && (!TextUtils.equals(str, "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(str, "com.sinyee.babybus.chants")) && (!TextUtils.equals(str, "com.sinyee.babybus.talk2kiki")) && (!TextUtils.equals(str, App.jdMethod_do().jdField_try)))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("isRunBabyApp gl ");
          ((StringBuilder)localObject).append(str);
          z.jdMethod_for(((StringBuilder)localObject).toString());
          return false;
        }
      }
    }
    return true;
  }
  
  private static List<String> const()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      Object localObject1 = App.jdMethod_do().getPackageManager().getInstalledApplications(8192);
      HashMap localHashMap = new HashMap();
      Object localObject2 = ((ActivityManager)App.jdMethod_do().getSystemService("activity")).getRunningAppProcesses().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
        String[] arrayOfString = localRunningAppProcessInfo.pkgList;
        int i = 0;
        while (i < arrayOfString.length)
        {
          localHashMap.put(arrayOfString[i], localRunningAppProcessInfo);
          i += 1;
        }
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        if (localHashMap.containsKey(((ApplicationInfo)localObject2).packageName)) {
          localArrayList.add(((ApplicationInfo)localObject2).packageName);
        }
      }
      return localArrayList;
    }
    catch (Exception localException) {}
    return localArrayList;
  }
  
  public static void jdMethod_do(Context paramContext, final int paramInt, final String paramString1, final String paramString2, final DialogInterface.OnClickListener paramOnClickListener1, final DialogInterface.OnClickListener paramOnClickListener2)
  {
    ax.jdMethod_do(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.jdField_do);
        localBuilder.setCancelable(true);
        localBuilder.setIcon(paramInt);
        localBuilder.setTitle(paramString1);
        localBuilder.setMessage(paramString2);
        localBuilder.setInverseBackgroundForced(true);
        localBuilder.setPositiveButton("Continue", paramOnClickListener1);
        localBuilder.setNegativeButton("Cancel", paramOnClickListener2);
        localBuilder.create().show();
      }
    });
  }
  
  public static void jdMethod_do(File paramFile)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    App.jdMethod_do().startActivity(localIntent);
  }
  
  public static void jdMethod_do(String paramString, int paramInt)
  {
    if ((paramString != null) && (!"".equals(paramString)))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addFlags(268435456);
      localIntent.setData(Uri.parse(paramString));
      App.jdMethod_do().jdMethod_byte().startActivityForResult(localIntent, paramInt);
    }
  }
  
  public static void jdMethod_do(String paramString1, String paramString2)
  {
    if (al.jdMethod_do("android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      a.jdMethod_new(paramString2);
      com.babybus.f.a.jdMethod_do().jdMethod_if(paramString1, paramString2);
      return;
    }
    com.babybus.f.a.jdMethod_do().jdMethod_for(paramString1, paramString2);
  }
  
  public static void jdMethod_do(String paramString, boolean paramBoolean)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("launchApp:");
      localStringBuilder.append(paramString);
      localStringBuilder.append("--");
      localStringBuilder.append(paramBoolean);
      Log.e("Test", localStringBuilder.toString());
      paramString = App.jdMethod_do().getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.setFlags(268435456);
      App.jdMethod_do().startActivity(paramString);
      if (!paramBoolean) {
        break label88;
      }
      App.jdMethod_do().jdMethod_else();
      return;
    }
    catch (Exception paramString)
    {
      label88:
      for (;;) {}
    }
    z.jdMethod_new("launchApp error");
  }
  
  public static boolean jdMethod_do()
  {
    boolean bool3 = false;
    boolean bool2 = false;
    try
    {
      int i = App.jdMethod_do().getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
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
    z.jdMethod_if("com.android.providers.downloads is no found");
    return false;
  }
  
  public static boolean jdMethod_do(long paramLong)
  {
    return Math.abs(paramLong - App.jdMethod_do().strictfp) < do;
  }
  
  public static boolean jdMethod_do(Activity paramActivity, String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("mqqwpa://im/chat?chat_type=wpa&uin=");
      localStringBuilder.append(paramString);
      localStringBuilder.append("&version=1");
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return true;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    aw.jdMethod_do("您的设备上还没有安装QQ哦~");
    return false;
  }
  
  public static boolean jdMethod_do(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      if ((i & 0x2) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean jdMethod_do(String paramString)
  {
    PackageManager localPackageManager = App.jdMethod_do().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean jdMethod_else()
  {
    return jdMethod_void("A004");
  }
  
  public static boolean jdMethod_else(String paramString)
  {
    return App.jdMethod_do().getPackageManager().getPackageArchiveInfo(paramString, 1) != null;
  }
  
  public static List<AppInfoBean> jdMethod_for()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = jdMethod_if().iterator();
    while (localIterator.hasNext())
    {
      AppInfoBean localAppInfoBean = (AppInfoBean)localIterator.next();
      if ((!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.wonderland")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.bbtime.android")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.chants")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.talk2kiki")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), App.jdMethod_do().jdField_try))) {
        localArrayList.add(localAppInfoBean);
      }
    }
    return localArrayList;
  }
  
  @Deprecated
  public static boolean jdMethod_for(String paramString)
  {
    if ((paramString != null) && (!"".equals(paramString)))
    {
      List localList = App.jdMethod_do().getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        if (paramString.endsWith(((PackageInfo)localList.get(i)).packageName)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static String jdMethod_goto(String paramString)
  {
    Object localObject;
    if ("com.sinyee.babybus.wonderland".equals(App.jdMethod_do().getPackageName()))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(b.y.long);
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(".apk");
      localObject = ((StringBuilder)localObject).toString();
      com.babybus.i.b.g.jdMethod_do().jdMethod_do((String)localObject, new com.babybus.i.b.e(paramString));
      paramString = (String)localObject;
    }
    else
    {
      localObject = new com.babybus.i.b.e(paramString);
      if (!App.goto)
      {
        paramString = new StringBuilder();
        paramString.append(App.jdMethod_do().getExternalFilesDir("apks"));
        paramString.append("/");
        paramString.append(((com.babybus.i.b.e)localObject).jdMethod_do());
        paramString.append(".apk");
        paramString = paramString.toString();
        com.babybus.i.b.g.jdMethod_do().jdMethod_if((com.babybus.i.b.e)localObject);
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(b.y.long);
        localStringBuilder.append("/");
        localStringBuilder.append(paramString);
        localStringBuilder.append(".apk");
        paramString = localStringBuilder.toString();
        com.babybus.i.b.g.jdMethod_do().jdMethod_do((com.babybus.i.b.e)localObject);
      }
    }
    paramString = new File(paramString);
    try
    {
      long l = g.jdMethod_do(paramString);
      paramString = new DecimalFormat("#.00").format(l * 1.0D / 1000.0D / 1000.0D);
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return "-1";
  }
  
  public static boolean jdMethod_goto()
  {
    return jdMethod_void("A023");
  }
  
  public static List<AppInfoBean> jdMethod_if()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = App.jdMethod_do().getPackageManager();
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
          localArrayList.add(localAppInfoBean);
        }
      }
    }
    return localArrayList;
  }
  
  public static void jdMethod_if(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("num ===");
    localStringBuilder.append(paramString);
    Log.e("babybus", localStringBuilder.toString());
    paramString = new Intent("android.intent.action.MAIN");
    paramString.setComponent(new ComponentName("com.sinyee.babybus.recommendapp", "com.sinyee.babybus.recommendapp.Main"));
    App.jdMethod_do().jdField_switch.startActivity(paramString);
  }
  
  public static void jdMethod_if(String paramString, boolean paramBoolean)
  {
    Intent localIntent = App.jdMethod_do().getPackageManager().getLaunchIntentForPackage(paramString);
    if ("com.sinyee.babybus.wonderland".equals(App.jdMethod_do().getPackageName())) {
      x.jdMethod_do().jdMethod_for("wonderland", paramString);
    }
    App.jdMethod_do().startActivity(localIntent);
    if (paramBoolean) {
      App.jdMethod_do().jdMethod_else();
    }
  }
  
  public static int jdMethod_int(String paramString)
  {
    Iterator localIterator = App.jdMethod_do().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (TextUtils.equals(paramString, localPackageInfo.packageName)) {
        return localPackageInfo.versionCode;
      }
    }
    return -1;
  }
  
  public static List<String> jdMethod_int()
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject = App.jdMethod_do().getPackageManager();
      int i = 0;
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      int j = ((List)localObject).size();
      while (i < j)
      {
        String str = ((PackageInfo)((List)localObject).get(i)).packageName;
        if ((str.contains("com.sinyee")) && (!TextUtils.equals(str, "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(str, "com.sinyee.babybus.wonderland")) && (!TextUtils.equals(str, "com.sinyee.babybus.bbtime.android")) && (!TextUtils.equals(str, "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(str, "com.sinyee.babybus.chants")) && (!TextUtils.equals(str, "com.sinyee.babybus.talk2kiki")) && (!TextUtils.equals(str, App.jdMethod_do().jdField_try))) {
          localArrayList.add(str);
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public static void jdMethod_long(String paramString)
  {
    jdMethod_do(paramString, "");
  }
  
  public static boolean jdMethod_long()
  {
    return jdMethod_void("A022");
  }
  
  public static String jdMethod_new()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = App.jdMethod_do().getPackageManager();
    if (localPackageManager != null)
    {
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          localArrayList.add(new AppInfoForWonderlandWBean(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString(), localPackageInfo.versionName, localPackageInfo.packageName));
        }
      }
    }
    return new Gson().toJson(localArrayList);
  }
  
  public static void jdMethod_new(String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("package:");
    ((StringBuilder)localObject).append(paramString);
    localObject = new Intent("android.intent.action.DELETE", Uri.parse(((StringBuilder)localObject).toString()));
    ((Intent)localObject).addFlags(536870912);
    App.jdMethod_do().jdField_continue = paramString;
    App.jdMethod_do().jdField_switch.startActivityForResult((Intent)localObject, 8402);
  }
  
  public static String jdMethod_this(String paramString)
  {
    if (paramString.endsWith(".huawei")) {
      return paramString.substring(0, paramString.indexOf(".huawei"));
    }
    String str = paramString;
    if (paramString.endsWith(".nearme.gamecenter")) {
      str = paramString.substring(0, paramString.indexOf(".nearme.gamecenter"));
    }
    return str;
  }
  
  public static boolean jdMethod_this()
  {
    return jdMethod_void("A016");
  }
  
  public static void jdMethod_try()
  {
    Gson localGson = new Gson();
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = App.jdMethod_do().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localArrayList.add(new AppInfoForWonderlandWBean(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString(), localPackageInfo.versionName, localPackageInfo.packageName));
      }
    }
    at.jdMethod_do("INSTALL_APP_INFO", localGson.toJson(localArrayList));
  }
  
  public static boolean jdMethod_try(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramString.contains("com.sinyee")) {
        return true;
      }
      if (paramString.contains("com.mampod.ergedd")) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean jdMethod_void()
  {
    return (jdMethod_else()) || (jdMethod_goto()) || (jdMethod_long()) || (jdMethod_this());
  }
  
  private static boolean jdMethod_void(String paramString)
  {
    return (paramString.equals(App.jdMethod_do().jdField_else)) && (!"zh".equals(ax.jdMethod_if()));
  }
}
