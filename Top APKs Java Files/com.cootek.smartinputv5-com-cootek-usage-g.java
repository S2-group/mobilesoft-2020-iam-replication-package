package com.cootek.usage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class g
{
  private static final int a = 0;
  private static final int b = 1;
  private static final String c = "noah_info";
  private AbsUsageAssist d;
  
  g(AbsUsageAssist paramAbsUsageAssist)
  {
    this.d = paramAbsUsageAssist;
  }
  
  static int a()
  {
    return 1;
  }
  
  static String a(int paramInt)
  {
    if (paramInt != 0)
    {
      StringBuilder localStringBuilder = new StringBuilder("error info value: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return "noah_reserve_03";
  }
  
  static String b()
  {
    return "noah_info";
  }
  
  private h c()
  {
    h localH = new h(this);
    Object localObject1 = this.d.getContext().getPackageManager();
    List localList = ((PackageManager)localObject1).getInstalledPackages(0);
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    while (i < localList.size())
    {
      Object localObject2 = (PackageInfo)localList.get(i);
      if (((((PackageInfo)localObject2).applicationInfo.flags & 0x1) == 0) || ((((PackageInfo)localObject2).applicationInfo.flags & 0x80) != 0))
      {
        String str = ((PackageInfo)localObject2).applicationInfo.loadLabel((PackageManager)localObject1).toString();
        localObject2 = ((PackageInfo)localObject2).packageName;
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("name", str);
          localJSONObject.put("package_name", localObject2);
          localJSONArray.put(localJSONObject);
          localH.d = true;
        }
        catch (JSONException localJSONException2)
        {
          ThrowableExtension.b(localJSONException2);
        }
      }
      i += 1;
    }
    localObject1 = new JSONObject();
    try
    {
      ((JSONObject)localObject1).put("data", localJSONArray);
    }
    catch (JSONException localJSONException1)
    {
      ThrowableExtension.b(localJSONException1);
    }
    UsageData localUsageData = new UsageData();
    localUsageData.type = "noah_info";
    localUsageData.path = a(0);
    localUsageData.value = ((JSONObject)localObject1).toString();
    Log.i("InfoProvider/getApps", localUsageData.value);
    localH.a = localUsageData;
    localH.c = a(0);
    return localH;
  }
  
  final h b(int paramInt)
  {
    if (paramInt != 0)
    {
      StringBuilder localStringBuilder = new StringBuilder("error info value: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return c();
  }
}
