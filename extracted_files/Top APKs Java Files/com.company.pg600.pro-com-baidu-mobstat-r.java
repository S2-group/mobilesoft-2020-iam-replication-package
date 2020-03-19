package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class r
{
  static final r a = new r();
  
  r() {}
  
  private void a(boolean paramBoolean, PackageInfo paramPackageInfo, JSONArray paramJSONArray)
  {
    long l2 = 0L;
    if (!paramBoolean) {}
    try
    {
      do
      {
        long l1 = paramPackageInfo.firstInstallTime;
        try
        {
          long l3 = paramPackageInfo.lastUpdateTime;
          l2 = l3;
        }
        catch (Throwable localThrowable2)
        {
          for (;;)
          {
            JSONObject localJSONObject;
            bc.b(localThrowable2);
          }
        }
        localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("n", paramPackageInfo.packageName);
          localJSONObject.put("v", String.valueOf(paramPackageInfo.versionName));
          localJSONObject.put("f", l1);
          localJSONObject.put("l", l2);
          paramJSONArray.put(localJSONObject);
          return;
        }
        catch (JSONException paramPackageInfo)
        {
          bc.b(paramPackageInfo);
        }
      } while (!paramPackageInfo.packageName.startsWith("com.android."));
      return;
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        bc.b(localThrowable1);
        l1 = 0L;
      }
    }
  }
  
  private void b(Context paramContext, boolean paramBoolean)
  {
    int i = 0;
    Object localObject1 = paramContext.getPackageManager();
    if (localObject1 != null) {
      paramContext = new ArrayList(1);
    }
    try
    {
      localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
      paramContext = (Context)localObject1;
    }
    catch (Exception localException1)
    {
      Object localObject2;
      do
      {
        for (;;)
        {
          bc.b(localException1);
        }
        localObject2 = (PackageInfo)paramContext.next();
        localObject3 = ((PackageInfo)localObject2).applicationInfo;
      } while (localObject3 == null);
      if ((((ApplicationInfo)localObject3).flags & 0x1) != 0) {
        break label237;
      }
      for (bool = false; paramBoolean == bool; bool = true)
      {
        a(paramBoolean, (PackageInfo)localObject2, localException1);
        break;
      }
    }
    localObject1 = new JSONArray();
    paramContext = paramContext.iterator();
    if (!paramContext.hasNext())
    {
      if (((JSONArray)localObject1).length() == 0) {
        break label243;
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(System.currentTimeMillis() + "|");
      if (paramBoolean) {
        break label244;
      }
    }
    for (;;)
    {
      ((StringBuilder)localObject2).append(i);
      paramContext = "";
      try
      {
        localObject3 = new JSONObject();
        ((JSONObject)localObject3).put("app_list", localObject1);
        ((JSONObject)localObject3).put("meta-data", ((StringBuilder)localObject2).toString());
        localObject1 = cj.a(((JSONObject)localObject3).toString().getBytes());
        paramContext = (Context)localObject1;
      }
      catch (Exception localException2)
      {
        Object localObject3;
        boolean bool;
        long l;
        for (;;) {}
      }
      if (!TextUtils.isEmpty(paramContext)) {
        break;
      }
      return;
      return;
      label237:
      label243:
      return;
      label244:
      i = 1;
    }
    l = System.currentTimeMillis();
    y.b.a(l, paramContext);
  }
  
  public void a(Context paramContext, boolean paramBoolean)
  {
    try
    {
      b(paramContext, paramBoolean);
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
}
