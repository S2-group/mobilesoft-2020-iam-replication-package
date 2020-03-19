package com.taobao.android.taoapp.api;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaoappUtils
{
  public static final String Action_Network_Update = "com.taobao.appcenter.update_network";
  public static final String Action_Taoapp_Uninstalled = "com.taobao.appcenter.taoapp_uninstalled";
  public static final String Action_Update_App_Update = "com.taobao.appcenter.app_update";
  public static final String AppcenterName = "yyzx";
  public static final String Key_UpdateCount = "key_updatecount";
  public static final long sTaoappMsgID = -1024L;
  
  public TaoappUtils() {}
  
  private static ResolveInfo a(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    new ArrayList();
    paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    int i = 0;
    while (i < paramContext.size())
    {
      if ("com.taobao.appcenter".equals(((ResolveInfo)paramContext.get(i)).activityInfo.packageName)) {
        return (ResolveInfo)paramContext.get(i);
      }
      i += 1;
    }
    return null;
  }
  
  private static List<PackageInfo> a(List<PackageInfo> paramList)
  {
    if (paramList == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramList.next();
      if (a(localPackageInfo)) {
        localArrayList.add(localPackageInfo);
      }
    }
    return localArrayList;
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo != null) && (paramPackageInfo.applicationInfo != null) && (paramPackageInfo.applicationInfo.packageName != null);
  }
  
  private static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return false;
      paramString = paramString.split("\\.");
    } while ((paramString == null) || (paramString.length != 3));
    return true;
  }
  
  private static int b(Context paramContext)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.taobao.appcenter", 0);
      if (paramContext == null) {
        return 0;
      }
      return paramContext.versionCode;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext = localObject;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = localObject;
      }
    }
  }
  
  public static PackageInfo checkPackage(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return null;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    return paramContext;
  }
  
  public static void downloadTaoapp(Context paramContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.addFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void downloadTaoappFromApplication(Context paramContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.addFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void downloadTaoappFromPlugin(Context paramContext)
  {
    downloadTaoapp(paramContext, "http://rj.m.taobao.com/wap/appmark/outSideDownLoad.htm?key=TaobaoMainEntry");
  }
  
  public static boolean for360(ITaoapp paramITaoapp)
  {
    try
    {
      boolean bool = paramITaoapp.isConfigFor360();
      return bool;
    }
    catch (Exception paramITaoapp)
    {
      return true;
    }
    catch (Throwable paramITaoapp)
    {
      for (;;) {}
    }
  }
  
  public static List<PackageInfo> getInstalledApplication(PackageManager paramPackageManager)
  {
    try
    {
      paramPackageManager = a(paramPackageManager.getInstalledPackages(0));
      return paramPackageManager;
    }
    catch (Throwable paramPackageManager)
    {
      return null;
    }
    catch (OutOfMemoryError paramPackageManager)
    {
      for (;;) {}
    }
    catch (Exception paramPackageManager)
    {
      for (;;) {}
    }
  }
  
  public static void gotoTaoapp(Activity paramActivity, Source paramSource)
  {
    Object localObject;
    switch (1.$SwitchMap$com$taobao$android$taoapp$api$TaoappUtils$Source[paramSource.ordinal()])
    {
    default: 
      localObject = "http://rj.m.taobao.com/wap/appmark/outSideDownLoad.htm?key=TaobaoMainEntry";
      if (!sCheckTaoappState(paramActivity).mSupportSecurity) {
        break label165;
      }
      if (paramSource.equals(Source.MyTaobao)) {
        paramSource = new Intent("com.taobao.appcenter.export.security");
      }
      break;
    }
    for (;;)
    {
      try
      {
        paramActivity.startActivity(paramSource);
        return;
      }
      catch (Exception paramActivity) {}
      localObject = "http://rj.m.taobao.com/wap/appmark/outSideDownLoad.htm?key=TaobaoMainSMSCenter";
      break;
      localObject = "http://rj.m.taobao.com/wap/appmark/outSideDownLoad.htm?key=TaobaoMainMyTaobao";
      break;
      try
      {
        paramSource = a(paramActivity);
        if (paramSource != null)
        {
          localObject = new Intent();
          ((Intent)localObject).setAction("android.intent.action.MAIN");
          ((Intent)localObject).setPackage(paramActivity.getPackageName());
          ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
          ((Intent)localObject).setComponent(new ComponentName(paramSource.activityInfo.packageName, paramSource.activityInfo.name));
          ((Intent)localObject).setFlags(335544320);
          paramActivity.startActivity((Intent)localObject);
          return;
        }
      }
      catch (Exception paramActivity)
      {
        return;
      }
    }
    label165:
    downloadTaoapp(paramActivity, (String)localObject);
    return;
  }
  
  public static boolean gotoTaobaoDetail(Activity paramActivity)
  {
    Intent localIntent = new Intent("com.taobao.appcenter.export.detail");
    localIntent.putExtra("key_appId", "520001");
    localIntent.putExtra("auto_start_download_pri", 3);
    localIntent.putExtra("force_back", true);
    try
    {
      paramActivity.startActivity(localIntent);
      return true;
    }
    catch (Exception paramActivity) {}
    return false;
  }
  
  public static boolean isDisplayPhoneTaoHelpUpdate(Context paramContext, String paramString, ITaoapp paramITaoapp)
  {
    paramContext = sCheckTaoappState(paramContext);
    return (!for360(paramITaoapp)) && (paramContext.mSupportSecurity) && (paramContext.mEnabled) && (a(paramString));
  }
  
  public static boolean openApp(Context paramContext, String paramString)
  {
    Object localObject1 = paramContext.getPackageManager();
    Object localObject2;
    try
    {
      paramString = ((PackageManager)localObject1).getPackageInfo(paramString, 0);
      if (paramString == null) {
        return false;
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString = null;
      }
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        paramString = null;
      }
      localObject2 = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
      ((Intent)localObject2).setPackage(paramString.packageName);
      paramString = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 0);
      if (paramString == null) {
        break label166;
      }
    }
    Intent localIntent;
    if (paramString.size() > 0)
    {
      localObject2 = (ResolveInfo)paramString.iterator().next();
      if (localObject2 != null)
      {
        localObject1 = ((ResolveInfo)localObject2).activityInfo.packageName;
        localObject2 = ((ResolveInfo)localObject2).activityInfo.name;
        localIntent = new Intent("android.intent.action.MAIN");
        localIntent.addFlags(268435456);
        localIntent.addCategory("android.intent.category.LAUNCHER");
        localIntent.setComponent(new ComponentName((String)localObject1, (String)localObject2));
      }
    }
    try
    {
      paramContext.startActivity(localIntent);
      paramString.clear();
      label166:
      return true;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
  }
  
  public static boolean openTaoappDetail(Context paramContext, String paramString1, String paramString2)
  {
    if ((checkPackage(paramContext, "com.taobao.appcenter") == null) || (!supportUpdate(paramContext)))
    {
      downloadTaoappFromPlugin(paramContext);
      return false;
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("key_appId", paramString1);
    localBundle.putString("key_packageName", paramString2);
    paramString1 = new Intent("com.taobao.appcenter.export.detail");
    paramString1.putExtras(localBundle);
    paramString1.addFlags(268435456);
    try
    {
      paramContext.startActivity(paramString1);
      return true;
    }
    catch (Exception paramString1)
    {
      downloadTaoappFromPlugin(paramContext);
    }
    return false;
  }
  
  public static TaoappState sCheckTaoappState(Context paramContext)
  {
    TaoappState localTaoappState = new TaoappState();
    Object localObject = null;
    try
    {
      PackageInfo localPackageInfo = checkPackage(paramContext, "com.taobao.appcenter");
      localObject = localPackageInfo;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if (localObject == null) {
      return localTaoappState;
    }
    localTaoappState.mInstall = true;
    localTaoappState.mSupportSecurity = supportSecurity(paramContext);
    localTaoappState.mEnabled = localObject.applicationInfo.enabled;
    return localTaoappState;
  }
  
  public static boolean supportDetail(Context paramContext)
  {
    return b(paramContext) > 82;
  }
  
  public static boolean supportDownload(Context paramContext)
  {
    return b(paramContext) > 1307;
  }
  
  public static boolean supportSecurity(Context paramContext)
  {
    return b(paramContext) > 82;
  }
  
  public static boolean supportUpdate(Context paramContext)
  {
    return b(paramContext) > 5;
  }
  
  public static enum Source
  {
    MessageCenter,  MyTaobao;
    
    private Source() {}
  }
  
  public static class TaoappState
  {
    public boolean mEnabled;
    public boolean mInstall;
    public boolean mSupportSecurity;
    
    public TaoappState() {}
  }
}
