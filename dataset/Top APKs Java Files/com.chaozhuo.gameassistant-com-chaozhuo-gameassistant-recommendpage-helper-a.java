package com.chaozhuo.gameassistant.recommendpage.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.chaozhuo.gameassistant.XApp;
import com.chaozhuo.gameassistant.recommendpage.bean.RecommendAppInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class a
{
  private static final String a = "DiscoveryDownloadStorag";
  private static final String b = "com.chaozhuo.gameassistant_SP_DISCOVERY_DOWNLOAD_STORAGE";
  private static final String c = "KEY_DISCOVERY_DOWNLOAD_LIST";
  private static volatile a d;
  
  private a() {}
  
  public static a a()
  {
    if (d == null) {}
    try
    {
      if (d == null) {
        d = new a();
      }
      return d;
    }
    finally {}
  }
  
  private List<String> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList;
  }
  
  private SharedPreferences d()
  {
    return XApp.a().getSharedPreferences("com.chaozhuo.gameassistant_SP_DISCOVERY_DOWNLOAD_STORAGE", 0);
  }
  
  public void a(RecommendAppInfo paramRecommendAppInfo)
  {
    if (paramRecommendAppInfo == null) {
      return;
    }
    Object localObject = b();
    if (!((List)localObject).contains(paramRecommendAppInfo)) {
      ((List)localObject).add(paramRecommendAppInfo);
    }
    paramRecommendAppInfo = new Gson().toJson(localObject);
    localObject = d().edit();
    ((SharedPreferences.Editor)localObject).putString("KEY_DISCOVERY_DOWNLOAD_LIST", paramRecommendAppInfo);
    ((SharedPreferences.Editor)localObject).apply();
  }
  
  public List<RecommendAppInfo> b()
  {
    String str = d().getString("KEY_DISCOVERY_DOWNLOAD_LIST", "[]");
    Gson localGson = new Gson();
    Type localType = new TypeToken() {}.getType();
    ArrayList localArrayList = new ArrayList();
    try
    {
      localArrayList.addAll((List)localGson.fromJson(str, localType));
      return localArrayList;
    }
    catch (Exception localException) {}
    return localArrayList;
  }
  
  public void b(RecommendAppInfo paramRecommendAppInfo)
  {
    Object localObject = b();
    ((List)localObject).remove(paramRecommendAppInfo);
    paramRecommendAppInfo = new Gson().toJson(localObject);
    localObject = d().edit();
    ((SharedPreferences.Editor)localObject).putString("KEY_DISCOVERY_DOWNLOAD_LIST", paramRecommendAppInfo);
    ((SharedPreferences.Editor)localObject).apply();
  }
  
  public List<RecommendAppInfo> c()
  {
    List localList = b();
    Object localObject1 = new ArrayList();
    Object localObject2 = a(XApp.a());
    int i = 0;
    while (i < localList.size())
    {
      RecommendAppInfo localRecommendAppInfo = (RecommendAppInfo)localList.get(i);
      if (localRecommendAppInfo != null)
      {
        if (((List)localObject2).contains(localRecommendAppInfo.app_id)) {
          ((List)localObject1).add(localRecommendAppInfo);
        }
        if ((!new File(localRecommendAppInfo.target_path).exists()) && (!new File(localRecommendAppInfo.target_path + ".temp").exists())) {
          ((List)localObject1).add(localRecommendAppInfo);
        }
      }
      i += 1;
    }
    localList.removeAll((Collection)localObject1);
    localObject1 = new Gson().toJson(localList);
    localObject2 = d().edit();
    ((SharedPreferences.Editor)localObject2).putString("KEY_DISCOVERY_DOWNLOAD_LIST", (String)localObject1);
    ((SharedPreferences.Editor)localObject2).apply();
    return localList;
  }
}
