package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import android.util.Base64;
import com.yandex.metrica.impl.utils.b;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class u
{
  JSONObject a;
  
  public u(String paramString)
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
  {
    if (!paramJSONObject.has(paramString)) {
      paramJSONObject.put(paramString, paramT);
    }
    return paramJSONObject.get(paramString);
  }
  
  public u a()
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
  
  u a(Context paramContext)
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
      localJSONArray3.put(((PackageInfo)localObject).applicationInfo.enabled ^ true);
      localJSONArray4.put(((PackageInfo)localObject).firstInstallTime / 1000L);
      localJSONArray5.put(((PackageInfo)localObject).lastUpdateTime / 1000L);
    }
    return this;
  }
  
  u a(Context paramContext, boolean paramBoolean)
  {
    Object localObject = (JSONObject)a((JSONObject)a(this.a, "dfid", new JSONObject()), "au", new JSONObject());
    JSONArray localJSONArray1 = (JSONArray)a((JSONObject)localObject, "aun", new JSONArray());
    JSONArray localJSONArray2 = (JSONArray)a((JSONObject)localObject, "ausf", new JSONArray());
    JSONArray localJSONArray3 = (JSONArray)a((JSONObject)localObject, "audf", new JSONArray());
    JSONArray localJSONArray4 = (JSONArray)a((JSONObject)localObject, "aulu", new JSONArray());
    JSONArray localJSONArray5 = new JSONArray();
    if (paramBoolean) {
      a((JSONObject)localObject, "aufi", localJSONArray5);
    }
    localObject = new HashSet();
    Iterator localIterator = bl.a(paramContext, new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuYWN0aW9uLk1BSU4=", 0), "UTF-8"), new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuY2F0ZWdvcnkuTEFVTkNIRVI=", 0), "UTF-8")).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = ((ResolveInfo)localIterator.next()).activityInfo.applicationInfo;
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
        localJSONArray3.put(true ^ localApplicationInfo.enabled);
        if (paramBoolean)
        {
          if (bool) {}
          while (bl.d(paramContext, localApplicationInfo.packageName) == null)
          {
            localJSONArray5.put(0);
            break;
          }
          localJSONArray5.put(bl.d(paramContext, localApplicationInfo.packageName).firstInstallTime / 1000L);
        }
      }
    }
    return this;
  }
  
  u b()
  {
    ((JSONObject)a(this.a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000L);
    return this;
  }
  
  u c()
  {
    JSONObject localJSONObject = (JSONObject)a(this.a, "dfid", new JSONObject());
    long l1 = an.a(true);
    long l2 = an.a(false);
    long l3 = an.c(true);
    long l4 = an.c(false);
    localJSONObject.put("tds", l1 + l2);
    localJSONObject.put("fds", l3 + l4);
    return this;
  }
  
  public String d()
  {
    byte[] arrayOfByte = new b().a(bj.c(this.a.toString()));
    if (arrayOfByte == null) {
      return null;
    }
    return Base64.encodeToString(arrayOfByte, 0);
  }
  
  public String toString()
  {
    return this.a.toString();
  }
}
