package com.ihs.b.d.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f
  extends a
{
  Set g = new HashSet(Arrays.asList(new String[] { "baseRevisionCode", "packageName", "firstInstallTime", "lastUpdateTime", "sharedUserId", "sharedUserLabel", "versionCode", "versionName" }));
  Set h = new HashSet(Arrays.asList(new String[] { "className", "enabled", "uid", "flags" }));
  private boolean i;
  private int j;
  private int k = 0;
  private boolean l = true;
  
  public f(Context paramContext, com.ihs.commons.i.n paramN, d paramD)
  {
    super(paramContext, paramN, paramD);
  }
  
  private JSONObject a(PackageInfo paramPackageInfo)
  {
    JSONObject localJSONObject = new JSONObject();
    Object localObject4 = this.g.iterator();
    for (;;)
    {
      if (((Iterator)localObject4).hasNext())
      {
        String str = (String)((Iterator)localObject4).next();
        try
        {
          Object localObject1 = PackageInfo.class.getDeclaredField(str).get(paramPackageInfo);
          if (!TextUtils.equals(str, "baseRevisionCode"))
          {
            localObject3 = localObject1;
            if (!TextUtils.equals(str, "sharedUserLabel")) {}
          }
          else
          {
            localObject3 = localObject1;
            if (localObject1 != null)
            {
              localObject3 = localObject1;
              if (((Integer)localObject1).intValue() == 0) {
                localObject3 = null;
              }
            }
          }
          if (localObject3 != null)
          {
            try
            {
              localJSONObject.put(str, localObject3);
            }
            catch (JSONException localJSONException)
            {
              localJSONException.printStackTrace();
            }
            if (com.ihs.commons.i.g.a()) {
              throw new AssertionError();
            }
          }
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          for (;;)
          {
            if (com.ihs.commons.i.g.a()) {
              throw new AssertionError();
            }
            Object localObject2 = null;
          }
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          for (;;)
          {
            localApplicationInfo = null;
          }
        }
      }
    }
    ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
    Object localObject3 = this.h.iterator();
    for (;;)
    {
      if (((Iterator)localObject3).hasNext())
      {
        localObject4 = (String)((Iterator)localObject3).next();
        try
        {
          paramPackageInfo = ApplicationInfo.class.getDeclaredField((String)localObject4).get(localApplicationInfo);
          if (paramPackageInfo != null)
          {
            try
            {
              localJSONObject.put((String)localObject4, paramPackageInfo);
            }
            catch (JSONException paramPackageInfo)
            {
              paramPackageInfo.printStackTrace();
            }
            if (com.ihs.commons.i.g.a()) {
              throw new AssertionError();
            }
          }
        }
        catch (IllegalAccessException paramPackageInfo)
        {
          for (;;)
          {
            if (com.ihs.commons.i.g.a()) {
              throw new AssertionError();
            }
            paramPackageInfo = null;
          }
        }
        catch (NoSuchFieldException paramPackageInfo)
        {
          for (;;)
          {
            paramPackageInfo = null;
          }
        }
      }
    }
    return localJSONObject;
  }
  
  private JSONArray e()
  {
    Object localObject2 = a().getPackageManager().getInstalledPackages(128);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new ArrayList();
    }
    HashSet localHashSet = new HashSet();
    JSONArray localJSONArray = new JSONArray();
    localObject1 = ((List)localObject1).iterator();
    for (;;)
    {
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        localHashSet.add(((PackageInfo)localObject2).packageName);
        localObject2 = a((PackageInfo)localObject2);
        try
        {
          ((JSONObject)localObject2).put("uninstalled", false);
          localJSONArray.put(localObject2);
        }
        catch (JSONException localJSONException1)
        {
          for (;;)
          {
            localJSONException1.printStackTrace();
          }
        }
      }
    }
    localObject2 = a().getPackageManager().getInstalledPackages(8320);
    localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new ArrayList();
    }
    localObject1 = ((List)localObject1).iterator();
    for (;;)
    {
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (localHashSet.contains(((PackageInfo)localObject2).packageName)) {
          continue;
        }
        localObject2 = a((PackageInfo)localObject2);
        try
        {
          ((JSONObject)localObject2).put("uninstalled", true);
          localJSONArray.put(localObject2);
        }
        catch (JSONException localJSONException2)
        {
          for (;;)
          {
            localJSONException2.printStackTrace();
          }
        }
      }
    }
    return localJSONArray;
  }
  
  protected void b(Map paramMap)
  {
    if (paramMap.size() == 0)
    {
      this.f.a(true);
      return;
    }
    com.ihs.a.a.n.a(new g(this, paramMap));
  }
  
  protected boolean b()
  {
    return this.i;
  }
  
  protected String c()
  {
    return "kSharedPreferenceKey_PackageInfos";
  }
  
  public void d()
  {
    JSONArray localJSONArray = e();
    this.i = true;
    Object localObject1 = new JSONArray();
    ArrayList localArrayList = new ArrayList();
    int m = 0;
    while (m < localJSONArray.length())
    {
      Object localObject2 = localJSONArray.optJSONObject(m);
      if (localObject2 != null) {
        ((JSONArray)localObject1).put(localObject2);
      }
      if (((JSONArray)localObject1).length() != 100)
      {
        localObject2 = localObject1;
        if (m != localJSONArray.length() - 1) {}
      }
      else
      {
        localObject2 = new HashMap();
        if (localJSONArray != null) {
          ((Map)localObject2).put("package_infos", localObject1);
        }
        localArrayList.add(localObject2);
        localObject2 = new JSONArray();
      }
      m += 1;
      localObject1 = localObject2;
    }
    this.j = localArrayList.size();
    this.k = 0;
    localObject1 = localArrayList.iterator();
    while (((Iterator)localObject1).hasNext()) {
      b((Map)((Iterator)localObject1).next());
    }
  }
}
