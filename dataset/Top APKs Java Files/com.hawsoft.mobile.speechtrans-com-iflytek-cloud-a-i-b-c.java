package com.iflytek.cloud.a.i.b;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.iflytek.cloud.Version;
import com.iflytek.cloud.a.i.b;
import com.iflytek.cloud.a.i.k;
import com.iflytek.cloud.a.i.k.a;
import com.iflytek.cloud.a.i.l;
import com.iflytek.cloud.a.i.n;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class c
{
  private static c a = null;
  private static Context b = null;
  private static SharedPreferences c;
  private boolean d = false;
  private boolean e = false;
  private boolean f = false;
  private boolean g = false;
  private boolean h = false;
  private long i = 0L;
  private long j = 0L;
  private long k = 0L;
  private long l = 0L;
  private long m = 0L;
  private k.a n = new f(this);
  private k.a o = new g(this);
  
  private c(Context paramContext)
  {
    if (paramContext != null)
    {
      b = paramContext.getApplicationContext();
      paramContext = new StringBuilder("iflytek_state_");
      paramContext.append(b.getPackageName());
      c = b.getSharedPreferences(paramContext.toString(), 0);
      this.h = c.getBoolean("is_collect", false);
      this.i = c.getLong("ti_app_list", 0L);
      this.l = c.getLong("list_app_time", 0L);
      this.k = c.getLong("ti_app_active", 0L);
      this.m = c.getLong("active_app_time", 0L);
    }
  }
  
  public static c a(Context paramContext)
  {
    if (a == null) {
      a = new c(paramContext);
    }
    return a;
  }
  
  private static JSONObject a(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("header", paramJSONObject2);
      localJSONObject.put("body", paramJSONObject1);
      return localJSONObject;
    }
    catch (Throwable paramJSONObject1)
    {
      a.b(paramJSONObject1);
    }
    return localJSONObject;
  }
  
  private static JSONObject a(boolean paramBoolean, com.iflytek.cloud.b.a paramA, String paramString)
  {
    JSONObject localJSONObject1 = new JSONObject();
    localJSONObject2 = new JSONObject();
    paramA = paramA.c().entrySet().iterator();
    try
    {
      while (paramA.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramA.next();
        localJSONObject2.put((String)localEntry.getKey(), localEntry.getValue());
      }
      return localJSONObject2;
    }
    catch (Throwable paramA)
    {
      a.b(paramA);
      while (paramBoolean)
      {
        return localJSONObject1;
        localJSONObject1.put(paramString, localJSONObject2);
      }
    }
  }
  
  private void a(JSONObject paramJSONObject)
  {
    try
    {
      if (l.c(b))
      {
        paramJSONObject = paramJSONObject.toString().getBytes("utf-8");
        byte[] arrayOfByte = com.iflytek.cloud.a.i.e.a(paramJSONObject);
        k localK = new k();
        localK.b(20000);
        localK.a(1);
        localK.a("http://scs.openspeech.cn/scs", "cmd=statsdklog&logver=1.0.2&size=" + paramJSONObject.length, arrayOfByte);
        localK.a(this.o);
        paramJSONObject = c.edit();
        if (this.f)
        {
          this.l = (System.currentTimeMillis() / 1000L);
          paramJSONObject.putLong("list_app_time", this.l);
        }
        if (this.g)
        {
          this.m = (System.currentTimeMillis() / 1000L);
          paramJSONObject.putLong("active_app_time", this.m);
        }
        paramJSONObject.commit();
        return;
      }
      this.e = false;
      return;
    }
    catch (Throwable paramJSONObject)
    {
      this.e = false;
      a.b(paramJSONObject);
    }
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  private static JSONObject b(Context paramContext)
  {
    Object localObject = com.iflytek.cloud.a.i.a.b(paramContext).b();
    com.iflytek.cloud.b.c.a(paramContext, (com.iflytek.cloud.b.a)localObject);
    ((com.iflytek.cloud.b.a)localObject).a("appid", com.iflytek.cloud.b.c.a());
    ((com.iflytek.cloud.b.a)localObject).a("unique_id", n.a(paramContext));
    ((com.iflytek.cloud.b.a)localObject).a("src", "msc");
    ((com.iflytek.cloud.b.a)localObject).a("ver", Version.getVersion());
    ((com.iflytek.cloud.b.a)localObject).a("lang", Locale.getDefault().getLanguage());
    ((com.iflytek.cloud.b.a)localObject).a("logtime", "" + System.currentTimeMillis());
    localObject = a(false, (com.iflytek.cloud.b.a)localObject, "header");
    try
    {
      DecimalFormat localDecimalFormat = new DecimalFormat("#.########");
      ((JSONObject)localObject).put("lat", localDecimalFormat.format(b.a(paramContext).b("msc.lat")));
      ((JSONObject)localObject).put("lng", localDecimalFormat.format(b.a(paramContext).b("msc.lng")));
      return localObject;
    }
    catch (Throwable paramContext)
    {
      a.b(paramContext);
    }
    return localObject;
  }
  
  private boolean d()
  {
    try
    {
      long l1 = c.getLong("ti_request", 0L);
      long l2 = c.getLong("request_time", 0L);
      long l3 = System.currentTimeMillis() / 1000L;
      return l3 - l2 > l1;
    }
    catch (Throwable localThrowable)
    {
      a.b(localThrowable);
    }
    return true;
  }
  
  private void e()
  {
    try
    {
      Object localObject = new JSONObject();
      ((JSONObject)localObject).put("pver", "2");
      ((JSONObject)localObject).put("type", "app_list");
      ((JSONObject)localObject).put("appid", com.iflytek.cloud.b.c.a());
      ((JSONObject)localObject).put("src", "msc");
      a.c("CollectInfo", ((JSONObject)localObject).toString());
      if (l.c(b))
      {
        localObject = com.iflytek.cloud.a.i.e.a(((JSONObject)localObject).toString().getBytes("utf-8"));
        k localK = new k();
        localK.b(20000);
        localK.a(1);
        localK.a("http://data.openspeech.cn/index.php/clientrequest/clientcollect/isCollect", "", (byte[])localObject);
        localK.a(this.n);
        localObject = c.edit();
        ((SharedPreferences.Editor)localObject).putLong("request_time", System.currentTimeMillis() / 1000L);
        ((SharedPreferences.Editor)localObject).commit();
        return;
      }
      this.d = false;
      return;
    }
    catch (Throwable localThrowable)
    {
      this.d = false;
      a.b(localThrowable);
    }
  }
  
  private boolean f()
  {
    if (!this.h) {
      return false;
    }
    long l1 = System.currentTimeMillis() / 1000L;
    if (l1 - this.l > this.j)
    {
      bool = true;
      label33:
      this.f = bool;
      if (l1 - this.m <= this.k) {
        break label80;
      }
    }
    label80:
    for (boolean bool = true;; bool = false)
    {
      this.g = bool;
      if ((!this.f) && (!this.g)) {
        break;
      }
      return true;
      bool = false;
      break label33;
    }
  }
  
  private void g()
  {
    try
    {
      Object localObject1 = new JSONArray();
      JSONObject localJSONObject;
      if (this.f)
      {
        localObject2 = h();
        if (localObject2 != null)
        {
          localJSONObject = new JSONObject();
          localJSONObject.put("appinfo", localObject2);
          localJSONObject.put("ts", System.currentTimeMillis());
          ((JSONArray)localObject1).put(localJSONObject);
        }
      }
      if (this.g)
      {
        localObject2 = i();
        if (localObject2 != null)
        {
          localJSONObject = new JSONObject();
          localJSONObject.put("hisinfo", localObject2);
          localJSONObject.put("ts", System.currentTimeMillis());
          ((JSONArray)localObject1).put(localJSONObject);
        }
      }
      Object localObject2 = new JSONObject();
      ((JSONObject)localObject2).put("log", localObject1);
      localObject1 = a((JSONObject)localObject2, b(b));
      a.c("CollectInfo", ((JSONObject)localObject1).toString());
      a((JSONObject)localObject1);
      return;
    }
    catch (Throwable localThrowable)
    {
      this.e = false;
      a.b(localThrowable);
    }
  }
  
  private JSONArray h()
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      PackageManager localPackageManager = b.getPackageManager();
      List localList = localPackageManager.getInstalledPackages(0);
      int i2 = localList.size();
      int i1 = 0;
      while (i1 < i2)
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i1);
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put(localPackageInfo.packageName, localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localJSONArray.put(localJSONObject);
        }
        i1 += 1;
      }
      return localThrowable;
    }
    catch (Throwable localThrowable)
    {
      a.b(localThrowable);
      return null;
    }
  }
  
  private JSONArray i()
  {
    if (!a(b, "android.permission.GET_TASKS")) {
      return null;
    }
    try
    {
      JSONArray localJSONArray = new JSONArray();
      PackageManager localPackageManager = b.getPackageManager();
      Iterator localIterator = ((ActivityManager)b.getSystemService("activity")).getRecentTasks(20, 1).iterator();
      while (localIterator.hasNext())
      {
        ResolveInfo localResolveInfo = localPackageManager.resolveActivity(((ActivityManager.RecentTaskInfo)localIterator.next()).baseIntent, 0);
        if (localResolveInfo != null)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put(localResolveInfo.activityInfo.packageName, localResolveInfo.loadLabel(localPackageManager).toString());
          localJSONArray.put(localJSONObject);
        }
      }
      return localThrowable;
    }
    catch (Throwable localThrowable)
    {
      a.b(localThrowable);
      return null;
    }
  }
  
  public void a()
  {
    for (;;)
    {
      try
      {
        boolean bool = this.d;
        if (bool) {
          return;
        }
        this.d = true;
        if (d()) {
          new Thread(new d(this)).start();
        } else {
          this.d = false;
        }
      }
      finally {}
    }
  }
  
  public void b()
  {
    for (;;)
    {
      try
      {
        boolean bool = this.e;
        if (bool) {
          return;
        }
        this.e = true;
        if (f()) {
          new Thread(new e(this)).start();
        } else {
          this.e = false;
        }
      }
      finally {}
    }
  }
}
