package com.chaozhuo.gameassistant.recommendpage.helper;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.chaozhuo.c.e;
import com.chaozhuo.c.h;
import com.chaozhuo.gameassistant.XApp;
import com.chaozhuo.gameassistant.recommendpage.bean.RecommendAppDetail;
import com.chaozhuo.gameassistant.recommendpage.bean.RecommendAppInfo;
import com.chaozhuo.gameassistant.utils.q;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  public static final String a = "/v1/octopus/apps";
  public static final String b = "/v1/market/recommendation/appdetail";
  private static final String c = "GetInfoHelper";
  private static final String d = "_OverseasAppList_";
  private static final String e = "KEY_FOR_CURRENT_VERSION";
  private static final String f = "KEY_FOR_OVERSEAS_LIST";
  private static volatile d g;
  private SharedPreferences h = XApp.a().getSharedPreferences("_OverseasAppList_", 0);
  private Handler i = new Handler(Looper.getMainLooper());
  
  private d() {}
  
  private h a(com.chaozhuo.c.f paramF, String paramString)
  {
    paramF = paramF.a(XApp.a());
    com.chaozhuo.c.k localK = new com.chaozhuo.c.k();
    localK.a = paramString;
    localK.b = paramF.getBytes();
    return e.a(localK);
  }
  
  public static d a()
  {
    if (g == null) {}
    try
    {
      if (g == null) {
        g = new d();
      }
      return g;
    }
    finally {}
  }
  
  @Nullable
  private RecommendAppDetail c(final String paramString)
  {
    try
    {
      paramString = a(new com.chaozhuo.c.f()
      {
        protected void a(JSONObject paramAnonymousJSONObject)
        {
          super.a(paramAnonymousJSONObject);
          try
          {
            paramAnonymousJSONObject.put("version_code", com.chaozhuo.gameassistant.utils.k.b(XApp.a()));
            paramAnonymousJSONObject.put("lang", d.a(d.this));
            paramAnonymousJSONObject.put("app_id", paramString);
            return;
          }
          catch (JSONException paramAnonymousJSONObject)
          {
            paramAnonymousJSONObject.printStackTrace();
          }
        }
      }, "/v1/market/recommendation/appdetail");
      if ((paramString == null) || (paramString.b == null))
      {
        com.chaozhuo.gameassistant.convert.g.f.e("GetInfoHelper", "mRetData is null when get recommend app detail");
        return null;
      }
      if (paramString.a != 200)
      {
        com.chaozhuo.gameassistant.convert.g.f.e("GetInfoHelper", "Server error, mRetCode is " + paramString.a + " when get recommend app list");
        return null;
      }
      paramString = (RecommendAppDetail)new Gson().fromJson(new String(paramString.b), RecommendAppDetail.class);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  private boolean c()
  {
    boolean bool2 = false;
    List localList = XApp.a().getPackageManager().getInstalledPackages(0);
    int j = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (j < localList.size())
      {
        if (((PackageInfo)localList.get(j)).packageName.equalsIgnoreCase("com.android.vending")) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      j += 1;
    }
  }
  
  @Nullable
  private List<RecommendAppInfo> d()
  {
    try
    {
      Object localObject = a(new com.chaozhuo.c.f()
      {
        protected void a(JSONObject paramAnonymousJSONObject)
        {
          super.a(paramAnonymousJSONObject);
          try
          {
            paramAnonymousJSONObject.put("lang", d.a(d.this));
            paramAnonymousJSONObject.put("start", 0);
            paramAnonymousJSONObject.put("count", 1000);
            if (q.b(XApp.a())) {
              paramAnonymousJSONObject.put("category", "game");
            }
            return;
          }
          catch (JSONException paramAnonymousJSONObject)
          {
            paramAnonymousJSONObject.printStackTrace();
          }
        }
      }, "/v1/octopus/apps");
      if ((localObject == null) || (((h)localObject).b == null))
      {
        com.chaozhuo.gameassistant.convert.g.f.e("GetInfoHelper", "mRetData is null when get recommend app list");
        return null;
      }
      if (((h)localObject).a != 200)
      {
        com.chaozhuo.gameassistant.convert.g.f.e("GetInfoHelper", "Server error, mRetCode is " + ((h)localObject).a + " when get recommend app list");
        return null;
      }
      Gson localGson = new Gson();
      Type localType = new TypeToken() {}.getType();
      localObject = (List)localGson.fromJson(new String(((h)localObject).b), localType);
      return localObject;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private String e()
  {
    String str = Locale.getDefault().getLanguage();
    if (str.toLowerCase().equals("zh")) {
      return "zh_cn";
    }
    if (str.toLowerCase().equals("en")) {
      return "en";
    }
    return "zh_cn";
  }
  
  public RecommendAppDetail a(String paramString)
  {
    return c(paramString);
  }
  
  @Nullable
  public RecommendAppInfo b(final String paramString)
  {
    try
    {
      paramString = a(new com.chaozhuo.c.f()
      {
        protected void a(JSONObject paramAnonymousJSONObject)
        {
          super.a(paramAnonymousJSONObject);
          try
          {
            paramAnonymousJSONObject.put("version_code", com.chaozhuo.gameassistant.utils.k.b(XApp.a()));
            paramAnonymousJSONObject.put("lang", d.a(d.this));
            paramAnonymousJSONObject.put("app_id", paramString);
            return;
          }
          catch (JSONException paramAnonymousJSONObject)
          {
            paramAnonymousJSONObject.printStackTrace();
          }
        }
      }, "/v1/market/recommendation/appdetail");
      if ((paramString == null) || (paramString.b == null))
      {
        com.chaozhuo.gameassistant.convert.g.f.e("GetInfoHelper", "mRetData is null when get single recommend app info");
        return null;
      }
      if (paramString.a != 200)
      {
        com.chaozhuo.gameassistant.convert.g.f.e("GetInfoHelper", "Server error, mRetCode is " + paramString.a + " when get single recommend app info");
        return null;
      }
      try
      {
        paramString = (RecommendAppInfo)new Gson().fromJson(new String(paramString.b), RecommendAppInfo.class);
        return paramString;
      }
      catch (Exception paramString)
      {
        return null;
      }
      return null;
    }
    catch (Exception paramString) {}
  }
  
  public List<RecommendAppInfo> b()
  {
    return d();
  }
}
