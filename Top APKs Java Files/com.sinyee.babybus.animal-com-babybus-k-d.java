package com.babybus.k;

import android.app.Activity;
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
import com.babybus.app.b.t;
import com.babybus.bean.AppInfoBean;
import com.babybus.bean.AppInfoForWonderlandWBean;
import com.babybus.k.b.e;
import com.babybus.k.b.g;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class d
{
  private static long jdField_do = 1000L;
  
  public d() {}
  
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
  
  public static void jdMethod_case(String paramString)
  {
    Log.e("Test", "ApkUtil launchSubPackage:" + paramString);
    paramString = App.jdMethod_do().throws.getPackageManager().getLaunchIntentForPackage(paramString);
    paramString.setFlags(268435456);
    paramString.putExtra("IS_FROM_WONDERLAND", true);
    App.jdMethod_do().throws.startActivity(paramString);
  }
  
  public static boolean jdMethod_char()
  {
    return jdMethod_for("com.tencent.mm");
  }
  
  public static boolean jdMethod_char(String paramString)
  {
    return "com.sinyee.babybus.recommendapp".equals(paramString);
  }
  
  public static void jdMethod_do(Context paramContext, final int paramInt, final String paramString1, final String paramString2, final DialogInterface.OnClickListener paramOnClickListener1, final DialogInterface.OnClickListener paramOnClickListener2)
  {
    aq.jdMethod_do(new Runnable()
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
    if (App.goto)
    {
      a.jdMethod_byte(paramString2);
      com.babybus.h.a.jdMethod_do().jdMethod_if(paramString1, paramString2);
      return;
    }
    com.babybus.h.a.jdMethod_do().jdMethod_for(paramString1, paramString2);
  }
  
  public static void jdMethod_do(String paramString, boolean paramBoolean)
  {
    try
    {
      Log.e("Test", "launchApp:" + paramString + "--" + paramBoolean);
      paramString = App.jdMethod_do().getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.setFlags(268435456);
      App.jdMethod_do().startActivity(paramString);
      if (paramBoolean) {
        App.jdMethod_do().jdMethod_else();
      }
      return;
    }
    catch (Exception paramString)
    {
      u.jdMethod_new("launchApp error");
    }
  }
  
  public static boolean jdMethod_do()
  {
    int i;
    do
    {
      try
      {
        i = App.jdMethod_do().getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
        if (Build.VERSION.SDK_INT <= 18) {
          continue;
        }
        if ((i != 2) && (i != 3) && (i != 4)) {
          return true;
        }
      }
      catch (Exception localException)
      {
        u.jdMethod_if("com.android.providers.downloads is no found");
        return false;
      }
      return false;
    } while ((i != 2) && (i != 3));
    return false;
  }
  
  public static boolean jdMethod_do(long paramLong)
  {
    return Math.abs(paramLong - App.jdMethod_do().protected) < do;
  }
  
  public static boolean jdMethod_do(Activity paramActivity, String paramString)
  {
    try
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + paramString + "&version=1")));
      return true;
    }
    catch (Exception paramActivity)
    {
      ap.jdMethod_do("您的设备上还没有安装QQ哦~");
    }
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
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean jdMethod_else()
  {
    return ("A004".equals(App.jdMethod_do().jdField_else)) && (!"zh".equals(aq.jdMethod_if()));
  }
  
  public static boolean jdMethod_else(String paramString)
  {
    paramString = b.t.case + "/" + paramString + ".apk";
    return (f.jdMethod_byte(paramString)) && (jdMethod_goto(paramString));
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
    boolean bool2 = false;
    boolean bool1 = bool2;
    List localList;
    int i;
    if (paramString != null)
    {
      bool1 = bool2;
      if (!"".equals(paramString))
      {
        localList = App.jdMethod_do().getPackageManager().getInstalledPackages(0);
        i = 0;
      }
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < localList.size())
      {
        if (paramString.endsWith(((PackageInfo)localList.get(i)).packageName)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean jdMethod_goto()
  {
    return ("A023".equals(App.jdMethod_do().jdField_else)) && (!"zh".equals(aq.jdMethod_if()));
  }
  
  public static boolean jdMethod_goto(String paramString)
  {
    return App.jdMethod_do().getPackageManager().getPackageArchiveInfo(paramString, 1) != null;
  }
  
  public static void jdMethod_if(String paramString)
  {
    Log.e("babybus", "num ===" + paramString);
    paramString = new Intent("android.intent.action.MAIN");
    paramString.setComponent(new ComponentName("com.sinyee.babybus.recommendapp", "com.sinyee.babybus.recommendapp.Main"));
    App.jdMethod_do().throws.startActivity(paramString);
  }
  
  public static void jdMethod_if(String paramString, boolean paramBoolean)
  {
    Intent localIntent = App.jdMethod_do().getPackageManager().getLaunchIntentForPackage(paramString);
    if ("com.sinyee.babybus.wonderland".equals(App.jdMethod_do().getPackageName())) {
      s.jdMethod_do().jdMethod_for("wonderland", paramString);
    }
    App.jdMethod_do().startActivity(localIntent);
    if (paramBoolean) {
      App.jdMethod_do().jdMethod_else();
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
  
  public static String jdMethod_int()
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
  
  public static String jdMethod_long(String paramString)
  {
    Object localObject;
    if ("com.sinyee.babybus.wonderland".equals(App.jdMethod_do().getPackageName()))
    {
      localObject = b.t.else + "/" + paramString + ".apk";
      g.jdMethod_do().jdMethod_do((String)localObject, new e(paramString));
      paramString = (String)localObject;
    }
    for (;;)
    {
      paramString = new File(paramString);
      try
      {
        long l = f.jdMethod_do(paramString);
        paramString = new DecimalFormat("#.00").format(l * 1.0D / 1000.0D / 1000.0D);
        return paramString;
      }
      catch (IOException paramString)
      {
        paramString.printStackTrace();
      }
      localObject = new e(paramString);
      if (!App.goto)
      {
        paramString = App.jdMethod_do().getExternalFilesDir("apks") + "/" + ((e)localObject).jdMethod_do() + ".apk";
        g.jdMethod_do().jdMethod_if((e)localObject);
      }
      else
      {
        paramString = b.t.else + "/" + paramString + ".apk";
        g.jdMethod_do().jdMethod_do((e)localObject);
      }
    }
    return "-1";
  }
  
  public static boolean jdMethod_long()
  {
    return (jdMethod_else()) || (jdMethod_goto());
  }
  
  public static void jdMethod_new()
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
    al.jdMethod_do("INSTALL_APP_INFO", localGson.toJson(localArrayList));
  }
  
  public static void jdMethod_new(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString));
    localIntent.addFlags(536870912);
    App.jdMethod_do().strictfp = paramString;
    App.jdMethod_do().throws.startActivityForResult(localIntent, 8402);
  }
  
  public static void jdMethod_this(String paramString)
  {
    jdMethod_do(paramString, "");
  }
  
  public static String jdMethod_try(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return paramString.substring(paramString.lastIndexOf("/") + 1, paramString.lastIndexOf("."));
    }
    return "";
  }
  
  public static boolean jdMethod_try()
  {
    return "com.sinyee.babybus.wonderland".equals(App.jdMethod_do().getPackageName());
  }
}
