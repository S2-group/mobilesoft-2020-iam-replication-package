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
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.babybus.app.App;
import com.babybus.app.b.x;
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
  
  public static boolean jdMethod_break()
  {
    return (jdMethod_goto()) || (jdMethod_long()) || (jdMethod_this()) || (jdMethod_void());
  }
  
  private static boolean jdMethod_break(String paramString)
  {
    return (paramString.equals(App.jdMethod_do().jdField_else)) && (!"zh".equals(ay.jdMethod_if()));
  }
  
  public static Drawable jdMethod_byte(String paramString)
  {
    PackageManager localPackageManager = App.jdMethod_do().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (((localPackageInfo.applicationInfo.flags & 0x1) == 0) && (localPackageInfo.packageName.startsWith(paramString))) {
        return localPackageManager.getApplicationIcon(localPackageInfo.applicationInfo);
      }
    }
    return null;
  }
  
  public static boolean jdMethod_byte()
  {
    return "com.sinyee.babybus.wonderland".equals(App.jdMethod_do().getPackageName());
  }
  
  public static boolean jdMethod_case()
  {
    return ("A005".equals(App.jdMethod_do().jdField_else)) || ("A030".equals(App.jdMethod_do().jdField_else));
  }
  
  public static boolean jdMethod_case(String paramString)
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
  
  public static String jdMethod_catch()
  {
    if (jdMethod_case()) {
      return "3";
    }
    return "2";
  }
  
  public static String jdMethod_char()
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
  
  public static void jdMethod_char(String paramString)
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
  
  public static List<String> jdMethod_class()
  {
    ArrayList localArrayList = new ArrayList();
    if (al.jdMethod_do("android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      File[] arrayOfFile = new File(b.x.char).listFiles();
      if ((arrayOfFile != null) && (arrayOfFile.length > 0))
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          File localFile = arrayOfFile[i];
          if ((localFile != null) && (jdMethod_long(localFile.getPath()))) {
            localArrayList.add(av.jdMethod_do(localFile.getPath()));
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  public static boolean const()
  {
    Object localObject = jdMethod_final().iterator();
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
  
  public static void jdMethod_do(Context paramContext, final int paramInt, final String paramString1, final String paramString2, final DialogInterface.OnClickListener paramOnClickListener1, final DialogInterface.OnClickListener paramOnClickListener2)
  {
    ay.jdMethod_do(new Runnable()
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
      App.jdMethod_do().jdMethod_case().startActivityForResult(localIntent, paramInt);
    }
  }
  
  public static void jdMethod_do(String paramString1, String paramString2)
  {
    if (ar.jdMethod_for())
    {
      a.jdMethod_byte(paramString2);
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
      App.jdMethod_do().jdMethod_goto();
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
    ax.jdMethod_do("您的设备上还没有安装QQ哦~");
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
    return jdMethod_for("com.tencent.mm");
  }
  
  public static boolean jdMethod_else(String paramString)
  {
    return "com.sinyee.babybus.recommendapp".equals(paramString);
  }
  
  private static List<String> jdMethod_final()
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
  
  public static List<AppInfoBean> jdMethod_for()
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
  
  public static boolean jdMethod_goto()
  {
    return jdMethod_break("A004");
  }
  
  public static boolean jdMethod_goto(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(b.x.char);
    localStringBuilder.append("/");
    localStringBuilder.append(paramString);
    localStringBuilder.append(".apk");
    paramString = localStringBuilder.toString();
    return (g.jdMethod_case(paramString)) && (jdMethod_long(paramString));
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
      App.jdMethod_do().jdMethod_goto();
    }
  }
  
  public static boolean jdMethod_if()
  {
    Intent localIntent = new Intent();
    localIntent.setData(Uri.parse("market://details?id=android.browser"));
    return App.jdMethod_do().getPackageManager().queryIntentActivities(localIntent, 65536).size() != 0;
  }
  
  public static int jdMethod_int(String paramString)
  {
    Iterator localIterator = App.jdMethod_do().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (paramString.equals(localPackageInfo.packageName)) {
        return localPackageInfo.versionCode;
      }
    }
    return -1;
  }
  
  public static List<AppInfoBean> jdMethod_int()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = jdMethod_for().iterator();
    while (localIterator.hasNext())
    {
      AppInfoBean localAppInfoBean = (AppInfoBean)localIterator.next();
      if ((!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.wonderland")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.bbtime.android")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.chants")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.talk2kiki")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), App.jdMethod_do().jdField_try))) {
        localArrayList.add(localAppInfoBean);
      }
    }
    return localArrayList;
  }
  
  public static boolean jdMethod_long()
  {
    return jdMethod_break("A023");
  }
  
  public static boolean jdMethod_long(String paramString)
  {
    return App.jdMethod_do().getPackageManager().getPackageArchiveInfo(paramString, 1) != null;
  }
  
  public static String jdMethod_new()
  {
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
    Object localObject;
    if ("com.sinyee.babybus.wonderland".equals(App.jdMethod_do().getPackageName()))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(b.x.goto);
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
        localStringBuilder.append(b.x.goto);
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
  
  public static boolean jdMethod_this()
  {
    return jdMethod_break("A022");
  }
  
  public static String jdMethod_try(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return paramString.substring(paramString.lastIndexOf("/") + 1, paramString.lastIndexOf("."));
    }
    return "";
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
  
  public static void jdMethod_void(String paramString)
  {
    jdMethod_do(paramString, "");
  }
  
  public static boolean jdMethod_void()
  {
    return jdMethod_break("A016");
  }
}
