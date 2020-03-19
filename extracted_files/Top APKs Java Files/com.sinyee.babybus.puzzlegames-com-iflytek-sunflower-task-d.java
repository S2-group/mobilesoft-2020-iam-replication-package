package com.iflytek.sunflower.task;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.iflytek.sunflower.c.g;
import com.iflytek.sunflower.config.a;
import com.iflytek.sunflower.e;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d
  extends Thread
{
  private com.iflytek.sunflower.b.b a;
  private JSONObject b;
  private Context c;
  private String d;
  private HashMap<String, String> e;
  
  public d(Context paramContext, JSONObject paramJSONObject, String paramString, HashMap<String, String> paramHashMap)
  {
    this.c = paramContext.getApplicationContext();
    this.b = paramJSONObject;
    this.d = paramString;
    this.e = paramHashMap;
  }
  
  private boolean c()
  {
    if (this.e != null) {
      return Boolean.parseBoolean((String)this.e.get(com.iflytek.sunflower.config.b.j));
    }
    return false;
  }
  
  private boolean d()
  {
    if (this.e != null) {
      return Boolean.parseBoolean((String)this.e.get(com.iflytek.sunflower.config.b.k));
    }
    return false;
  }
  
  private boolean e()
  {
    if (this.e != null) {
      return Boolean.parseBoolean((String)this.e.get(com.iflytek.sunflower.config.b.i));
    }
    long l = com.iflytek.sunflower.d.a(this.c).getLong(com.iflytek.sunflower.config.b.h, 0L);
    if (System.currentTimeMillis() - l >= a.r) {
      return true;
    }
    String str = com.iflytek.sunflower.d.d(this.c);
    return com.iflytek.sunflower.d.a(this.c, str) >= a.s;
  }
  
  private JSONObject f()
  {
    JSONObject localJSONObject = this.b;
    if (localJSONObject == null) {
      return null;
    }
    try
    {
      localJSONObject.put("type", this.d);
      return localJSONObject;
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("input error ");
      localStringBuilder.append(localException);
      g.d("Collector", localStringBuilder.toString());
    }
    return localJSONObject;
  }
  
  public JSONArray a()
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      PackageManager localPackageManager = this.c.getPackageManager();
      int i = 0;
      List localList = localPackageManager.getInstalledPackages(0);
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put(localPackageInfo.packageName, localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localJSONArray.put(localJSONObject);
        }
        i += 1;
      }
      return localJSONArray;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public void a(String paramString)
  {
    paramString = com.iflytek.sunflower.d.d(this.c);
    try
    {
      try
      {
        if (c())
        {
          localObject2 = a();
          if (localObject2 != null)
          {
            localObject1 = new JSONObject();
            ((JSONObject)localObject1).put("appinfo", localObject2);
            ((JSONObject)localObject1).put("ts", System.currentTimeMillis());
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(",");
            ((StringBuilder)localObject2).append(((JSONObject)localObject1).toString());
            localObject1 = ((StringBuilder)localObject2).toString();
            com.iflytek.sunflower.d.a(this.c, (String)localObject1, paramString, 32768);
          }
        }
        if (d())
        {
          localObject2 = b();
          if (localObject2 != null)
          {
            localObject1 = new JSONObject();
            ((JSONObject)localObject1).put("hisinfo", localObject2);
            ((JSONObject)localObject1).put("ts", System.currentTimeMillis());
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(",");
            ((StringBuilder)localObject2).append(((JSONObject)localObject1).toString());
            localObject1 = ((StringBuilder)localObject2).toString();
            com.iflytek.sunflower.d.a(this.c, (String)localObject1, paramString, 32768);
          }
        }
        if (e())
        {
          localObject1 = com.iflytek.sunflower.d.f(this.c);
          if (localObject1 == null) {
            return;
          }
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("[");
          ((StringBuilder)localObject2).append(((String)localObject1).substring(1));
          ((StringBuilder)localObject2).append("]");
          localObject2 = ((StringBuilder)localObject2).toString();
          localObject1 = com.iflytek.sunflower.d.d(this.c);
        }
      }
      catch (Exception paramString)
      {
        Object localObject2;
        label348:
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("send error");
        ((StringBuilder)localObject1).append(paramString);
        g.f("Collector", ((StringBuilder)localObject1).toString());
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
    try
    {
      if (!TextUtils.isEmpty((CharSequence)localObject1)) {
        this.c.deleteFile((String)localObject1);
      }
      paramString = new JSONArray((String)localObject2);
      localObject2 = new JSONObject();
      ((JSONObject)localObject2).put("log", paramString);
      paramString = e.a((JSONObject)localObject2, e.a(this.c));
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("send: ");
      ((StringBuilder)localObject2).append(paramString.toString());
      g.e("Collector", ((StringBuilder)localObject2).toString());
      this.a = new com.iflytek.sunflower.b.b(this.c);
      this.a.a(paramString, 1);
      return;
    }
    catch (JSONException paramString)
    {
      break label348;
    }
    paramString = (String)localObject1;
    this.c.deleteFile(paramString);
  }
  
  public JSONArray b()
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      PackageManager localPackageManager = this.c.getPackageManager();
      Iterator localIterator = ((ActivityManager)this.c.getSystemService("activity")).getRecentTasks(20, 1).iterator();
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
      return localJSONArray;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public void run()
  {
    Object localObject2 = f();
    Object localObject1 = "";
    if (localObject2 != null) {}
    try
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(",");
      ((StringBuilder)localObject1).append(((JSONObject)localObject2).toString());
      localObject1 = ((StringBuilder)localObject1).toString();
      localObject2 = com.iflytek.sunflower.d.d(this.c);
      com.iflytek.sunflower.d.a(this.c, (String)localObject1, (String)localObject2, 32768);
      a((String)localObject1);
      return;
    }
    catch (Exception localException)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("send error");
      ((StringBuilder)localObject2).append(localException);
      g.f("Collector", ((StringBuilder)localObject2).toString());
    }
  }
}
