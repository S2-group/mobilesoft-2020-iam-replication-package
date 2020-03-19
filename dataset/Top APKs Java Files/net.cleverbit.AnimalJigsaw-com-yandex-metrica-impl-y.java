package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import android.util.Base64;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class y
{
  private final JSONObject a = new JSONObject();
  
  public y() {}
  
  static <T> T a(JSONObject paramJSONObject, String paramString, T paramT)
    throws JSONException
  {
    if (!paramJSONObject.has(paramString)) {
      paramJSONObject.put(paramString, paramT);
    }
    return paramJSONObject.get(paramString);
  }
  
  public y a()
  {
    try
    {
      c();
      b();
      return this;
    }
    catch (Exception localException) {}
    return this;
  }
  
  y a(Context paramContext)
    throws JSONException
  {
    Object localObject = (JSONObject)a((JSONObject)a(this.a, "dfid", new JSONObject()), "apps", new JSONObject());
    JSONArray localJSONArray1 = (JSONArray)a((JSONObject)localObject, "names", new JSONArray());
    JSONArray localJSONArray2 = (JSONArray)a((JSONObject)localObject, "system_flags", new JSONArray());
    JSONArray localJSONArray3 = (JSONArray)a((JSONObject)localObject, "disabled_flags", new JSONArray());
    JSONArray localJSONArray4 = (JSONArray)a((JSONObject)localObject, "first_install_time", new JSONArray());
    JSONArray localJSONArray5 = (JSONArray)a((JSONObject)localObject, "last_update_time", new JSONArray());
    ((JSONObject)localObject).put("version", 0);
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    if (paramContext.hasNext())
    {
      localObject = (PackageInfo)paramContext.next();
      localJSONArray1.put(((PackageInfo)localObject).packageName);
      if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) == 1)
      {
        bool = true;
        label199:
        localJSONArray2.put(bool);
        if (((PackageInfo)localObject).applicationInfo.enabled) {
          break label264;
        }
      }
      label264:
      for (boolean bool = true;; bool = false)
      {
        localJSONArray3.put(bool);
        localJSONArray4.put(((PackageInfo)localObject).firstInstallTime / 1000L);
        localJSONArray5.put(((PackageInfo)localObject).lastUpdateTime / 1000L);
        break;
        bool = false;
        break label199;
      }
    }
    return this;
  }
  
  y a(Context paramContext, boolean paramBoolean)
    throws JSONException, UnsupportedEncodingException
  {
    Object localObject1 = (JSONObject)a((JSONObject)a(this.a, "dfid", new JSONObject()), "au", new JSONObject());
    JSONArray localJSONArray1 = (JSONArray)a((JSONObject)localObject1, "aun", new JSONArray());
    JSONArray localJSONArray2 = (JSONArray)a((JSONObject)localObject1, "ausf", new JSONArray());
    JSONArray localJSONArray3 = (JSONArray)a((JSONObject)localObject1, "audf", new JSONArray());
    JSONArray localJSONArray4 = (JSONArray)a((JSONObject)localObject1, "aulu", new JSONArray());
    JSONArray localJSONArray5 = new JSONArray();
    if (paramBoolean) {
      a((JSONObject)localObject1, "aufi", localJSONArray5);
    }
    localObject1 = new HashSet();
    Iterator localIterator = bw.a(paramContext, new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuYWN0aW9uLk1BSU4=", 0), "UTF-8"), new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuY2F0ZWdvcnkuTEFVTkNIRVI=", 0), "UTF-8")).iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = ((ResolveInfo)localIterator.next()).activityInfo.applicationInfo;
      if (((HashSet)localObject1).add(((ApplicationInfo)localObject2).packageName))
      {
        localJSONArray1.put(((ApplicationInfo)localObject2).packageName);
        boolean bool1;
        if ((((ApplicationInfo)localObject2).flags & 0x1) == 1)
        {
          bool1 = true;
          label250:
          localJSONArray2.put(bool1);
          localJSONArray4.put(new File(((ApplicationInfo)localObject2).sourceDir).lastModified());
          if (((ApplicationInfo)localObject2).enabled) {
            break label320;
          }
        }
        label320:
        for (boolean bool2 = true;; bool2 = false)
        {
          localJSONArray3.put(bool2);
          if (!paramBoolean) {
            break;
          }
          if (!bool1) {
            break label326;
          }
          localJSONArray5.put(0);
          break;
          bool1 = false;
          break label250;
        }
        label326:
        localObject2 = bw.d(paramContext, ((ApplicationInfo)localObject2).packageName);
        if (localObject2 == null) {
          localJSONArray5.put(0);
        } else {
          localJSONArray5.put(((PackageInfo)localObject2).firstInstallTime / 1000L);
        }
      }
    }
    return this;
  }
  
  y b()
    throws JSONException
  {
    ((JSONObject)a(this.a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000L);
    return this;
  }
  
  y c()
    throws JSONException
  {
    JSONObject localJSONObject = (JSONObject)a(this.a, "dfid", new JSONObject());
    long l1 = aw.a(true);
    long l2 = aw.a(false);
    long l3 = aw.c(true);
    long l4 = aw.c(false);
    localJSONObject.put("tds", l1 + l2);
    localJSONObject.put("fds", l3 + l4);
    return this;
  }
  
  public String d()
  {
    return this.a.toString();
  }
  
  public String toString()
  {
    return this.a.toString();
  }
}
