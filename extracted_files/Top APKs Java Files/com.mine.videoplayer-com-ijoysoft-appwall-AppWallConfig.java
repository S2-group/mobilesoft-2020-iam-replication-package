package com.ijoysoft.appwall;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

public class AppWallConfig
{
  public static boolean isFirstTimeInApp;
  public static boolean isNeedSecondUrl = true;
  static ArrayList<GiftEntity> result;
  public static int title_num = 0;
  
  public static void IntentActivity(Context paramContext)
  {
    paramContext.startActivity(new Intent(paramContext, GiftActivity.class));
  }
  
  public static void getData(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = new NetMusicXmlParseTask(new NetMusicXmlParseTask.HttpCallBack()
    {
      public void handleResult(ArrayList<GiftEntity> paramAnonymousArrayList)
      {
        AppWallConfig.updateView(this.val$context.getApplicationContext(), paramAnonymousArrayList);
      }
    });
    paramContext.execute(new String[] { paramString1 + paramString2 });
    paramContext.getStatus();
  }
  
  public static int getTitleNum(Context paramContext)
  {
    return paramContext.getSharedPreferences("title", 0).getInt("title_num", 0);
  }
  
  public static void init(Context paramContext)
  {
    PropertiesUtil.init(paramContext);
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("first_time", 0);
    if (localSharedPreferences.getBoolean("first", true))
    {
      localSharedPreferences.edit().putBoolean("first", false).commit();
      isFirstTimeInApp = true;
      isNeedSecondUrl = true;
      getData(paramContext, PropertiesUtil.getURL(), PropertiesUtil.getFile());
    }
    isFirstTimeInApp = false;
  }
  
  private static boolean isAvilible(Context paramContext, String paramString)
  {
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        localArrayList.add(((PackageInfo)localList.get(i)).packageName);
        i += 1;
      }
      return localArrayList.contains(paramString);
    }
    if (isAvilible(paramContext, "com.skype.android.verizon")) {
      new Intent().setComponent(new ComponentName("com.skype.android.verizon", "com.skype.android.verizon.SkypeActivity"));
    }
    return true;
  }
  
  public static boolean isFirstTimeInApp(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("first_time", 0);
    if (paramContext.getBoolean("first", true))
    {
      paramContext.edit().putBoolean("first", false).commit();
      return true;
    }
    return false;
  }
  
  public static void updateView(Context paramContext, ArrayList<GiftEntity> paramArrayList)
  {
    if (paramArrayList != null)
    {
      new GiftActivity();
      GiftActivity.setResult(paramArrayList);
      result = paramArrayList;
      localArrayList = new ArrayList();
      i = 0;
      while (i < paramArrayList.size())
      {
        if (!isAvilible(paramContext, ((GiftEntity)paramArrayList.get(i)).getPackageName())) {
          localArrayList.add(paramArrayList.get(i));
        }
        i += 1;
      }
      title_num = getTitleNum(paramContext);
      localIntent = new Intent();
      localEditor = paramContext.getSharedPreferences("Setting2", 0).edit();
      localEditor.putString("num", paramArrayList.size() + "");
      localEditor.commit();
      localIntent.setAction("popularize.newnum");
      localIntent.putExtra("newnum", paramArrayList.size() + "");
      paramContext.sendBroadcast(localIntent);
      paramContext = paramContext.getSharedPreferences("title", 0).edit();
      paramContext.putInt("title_num", localArrayList.size());
      paramContext.commit();
    }
    while (!isNeedSecondUrl)
    {
      ArrayList localArrayList;
      int i;
      Intent localIntent;
      SharedPreferences.Editor localEditor;
      return;
    }
    getData(paramContext, PropertiesUtil.getSecondURL(), PropertiesUtil.getSecondFile());
    isNeedSecondUrl = false;
  }
}
