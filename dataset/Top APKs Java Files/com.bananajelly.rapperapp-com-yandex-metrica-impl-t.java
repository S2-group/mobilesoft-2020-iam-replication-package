package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import android.util.Base64;
import com.yandex.metrica.impl.utils.d;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class t
{
  JSONObject a;
  
  public t(String paramString)
  {
    try
    {
      this.a = new JSONObject(paramString);
      return;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    this.a = new JSONObject();
  }
  
  static <T> T a(JSONObject paramJSONObject, String paramString, T paramT)
    throws JSONException
  {
    if (!paramJSONObject.has(paramString)) {
      paramJSONObject.put(paramString, paramT);
    }
    return paramJSONObject.get(paramString);
  }
  
  public t a()
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
  
  t a(Context paramContext)
    throws JSONException, UnsupportedEncodingException
  {
    Object localObject = (JSONObject)a((JSONObject)a(this.a, "dfid", new JSONObject()), "au", new JSONObject());
    JSONArray localJSONArray1 = (JSONArray)a((JSONObject)localObject, "aun", new JSONArray());
    JSONArray localJSONArray2 = (JSONArray)a((JSONObject)localObject, "ausf", new JSONArray());
    JSONArray localJSONArray3 = (JSONArray)a((JSONObject)localObject, "audf", new JSONArray());
    JSONArray localJSONArray4 = (JSONArray)a((JSONObject)localObject, "aulu", new JSONArray());
    ((JSONObject)localObject).put("auv", 0);
    localObject = new HashSet();
    paramContext = bg.a(paramContext, new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuYWN0aW9uLk1BSU4=", 0), "UTF-8"), new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuY2F0ZWdvcnkuTEFVTkNIRVI=", 0), "UTF-8")).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = ((ResolveInfo)paramContext.next()).activityInfo.applicationInfo;
      if (((HashSet)localObject).add(localApplicationInfo.packageName))
      {
        localJSONArray1.put(localApplicationInfo.packageName);
        boolean bool;
        if ((localApplicationInfo.flags & 0x1) == 1) {
          bool = true;
        } else {
          bool = false;
        }
        localJSONArray2.put(bool);
        localJSONArray4.put(new File(localApplicationInfo.sourceDir).lastModified());
        if (!localApplicationInfo.enabled) {
          bool = true;
        } else {
          bool = false;
        }
        localJSONArray3.put(bool);
      }
    }
    return this;
  }
  
  t b()
    throws JSONException
  {
    ((JSONObject)a(this.a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000L);
    return this;
  }
  
  t b(Context paramContext)
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
    while (paramContext.hasNext())
    {
      localObject = (PackageInfo)paramContext.next();
      localJSONArray1.put(((PackageInfo)localObject).packageName);
      boolean bool;
      if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) == 1) {
        bool = true;
      } else {
        bool = false;
      }
      localJSONArray2.put(bool);
      if (!((PackageInfo)localObject).applicationInfo.enabled) {
        bool = true;
      } else {
        bool = false;
      }
      localJSONArray3.put(bool);
      localJSONArray4.put(((PackageInfo)localObject).firstInstallTime / 1000L);
      localJSONArray5.put(((PackageInfo)localObject).lastUpdateTime / 1000L);
    }
    return this;
  }
  
  t c()
    throws JSONException
  {
    JSONObject localJSONObject = (JSONObject)a(this.a, "dfid", new JSONObject());
    long l1 = bg.a(true);
    long l2 = bg.a(false);
    long l3 = bg.b(true);
    long l4 = bg.b(false);
    localJSONObject.put("tds", l1 + l2);
    localJSONObject.put("fds", l3 + l4);
    return this;
  }
  
  public String d()
  {
    return Base64.encodeToString(new d().a(be.c(this.a.toString())), 0);
  }
  
  public String toString()
  {
    return this.a.toString();
  }
}
