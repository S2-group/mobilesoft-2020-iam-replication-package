package com.sec.spp.push.notisvc.tracking;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.sec.spp.push.PushClientApplication;
import com.sec.spp.push.notisvc.alarm.AlarmEventManager;
import com.sec.spp.push.notisvc.registration.o;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  private final String a = a.class.getSimpleName();
  private final String b = "tracking";
  private final String c = "retry_track";
  private final String d = "seq";
  private final String e = "in";
  private final String f = "del";
  private final char g = ':';
  private final char h = ';';
  private final String i = "https://ew1.reg.bigdata.ssp.samsung.com:80/tracking/";
  private final long j = 3L;
  
  public a() {}
  
  private int a(int paramInt1, int paramInt2)
  {
    if (paramInt2 >= paramInt1) {
      return paramInt2 - paramInt1;
    }
    return paramInt2 - paramInt1 + 24;
  }
  
  private String a(long paramLong, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ts");
    localStringBuilder.append(':');
    localStringBuilder.append(paramLong);
    localStringBuilder.append(';');
    localStringBuilder.append("sid");
    localStringBuilder.append(':');
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }
  
  private String a(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    if (((paramArrayList1 == null) || (paramArrayList1.isEmpty())) && ((paramArrayList2 == null) || (paramArrayList2.isEmpty()))) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("seq", com.sec.spp.push.notisvc.e.d.C(PushClientApplication.b()));
    if ((paramArrayList1 != null) && (!paramArrayList1.isEmpty())) {
      a(paramArrayList1, localJSONObject, "in");
    }
    if ((paramArrayList2 != null) && (!paramArrayList2.isEmpty())) {
      a(paramArrayList2, localJSONObject, "del");
    }
    com.sec.spp.push.notisvc.e.b.b("Event List : " + localJSONObject.toString(), this.a);
    return localJSONObject.toString();
  }
  
  private ArrayList a(ArrayList paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.isEmpty())) {
      return null;
    }
    PackageManager localPackageManager = PushClientApplication.b().getPackageManager();
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      d localD = (d)localIterator.next();
      try
      {
        localPackageManager.getPackageInfo(localD.a(), 128);
        com.sec.spp.push.notisvc.e.b.b("[ReInstalled] " + localD.a(), this.a);
        localArrayList.add(localD);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        com.sec.spp.push.notisvc.e.b.b(localNameNotFoundException.getMessage(), this.a);
      }
    }
    paramArrayList.removeAll(localArrayList);
    return paramArrayList;
  }
  
  private JSONObject a(ArrayList paramArrayList, JSONObject paramJSONObject, String paramString)
  {
    JSONArray localJSONArray1 = new JSONArray();
    JSONArray localJSONArray2 = new JSONArray();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      d localD = (d)paramArrayList.next();
      localJSONArray1.put(localD.a());
      localJSONArray2.put(localD.b());
    }
    paramArrayList = new JSONObject();
    paramArrayList.put("sid", localJSONArray1);
    paramArrayList.put("ts", localJSONArray2);
    paramJSONObject.put(paramString, paramArrayList);
    return paramJSONObject;
  }
  
  private void a(Context paramContext, String paramString, long paramLong)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("eventType", com.sec.spp.push.notisvc.alarm.a.a.ordinal());
    AlarmEventManager.a(paramContext, paramString, paramLong, localBundle, false);
  }
  
  private void a(com.sec.spp.push.notisvc.d.b paramB)
  {
    if (paramB.a)
    {
      com.sec.spp.push.notisvc.e.b.b("AT Success : " + paramB.b, this.a);
      paramB = PushClientApplication.b();
      com.sec.spp.push.notisvc.e.d.b(paramB, System.currentTimeMillis());
      int m = com.sec.spp.push.notisvc.e.d.C(paramB) + 1;
      int k = m;
      if (m < 0) {
        k = 1;
      }
      com.sec.spp.push.notisvc.e.d.g(paramB, k);
      if (!com.sec.spp.push.notisvc.e.d.q(paramB)) {}
      for (k = 1;; k = 0)
      {
        if (k == 1) {
          com.sec.spp.push.notisvc.e.d.a(paramB, true);
        }
        f.a().c();
        com.sec.spp.push.notisvc.e.d.d(paramB, false);
        return;
      }
    }
    com.sec.spp.push.notisvc.e.b.d("AT FAIL : " + paramB.b, this.a);
    e(PushClientApplication.b());
  }
  
  public static boolean a(Context paramContext)
  {
    return (com.sec.spp.push.notisvc.e.d.u(paramContext)) && (o.d(paramContext));
  }
  
  private String b(ArrayList paramArrayList)
  {
    JSONArray localJSONArray1 = new JSONArray();
    JSONArray localJSONArray2 = new JSONArray();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      localObject = (b)paramArrayList.next();
      localJSONArray1.put(((b)localObject).a());
      localJSONArray2.put(((b)localObject).b());
    }
    Object localObject = new JSONObject();
    paramArrayList = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("sid", localJSONArray1);
      ((JSONObject)localObject).put("ts", localJSONArray2);
      paramArrayList.put("seq", com.sec.spp.push.notisvc.e.d.C(PushClientApplication.b()));
      paramArrayList.put("in", localObject);
      com.sec.spp.push.notisvc.e.b.b("Body : " + paramArrayList.toString(), this.a);
      return paramArrayList.toString();
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        com.sec.spp.push.notisvc.e.b.a(localJSONException.getMessage(), this.a);
      }
    }
  }
  
  private boolean b(int paramInt1, int paramInt2)
  {
    int k = Calendar.getInstance().get(11);
    if (paramInt1 < paramInt2) {
      if ((paramInt1 > k) || (k >= paramInt2)) {}
    }
    do
    {
      return true;
      return false;
      if (paramInt1 <= paramInt2) {
        break;
      }
    } while (((paramInt1 <= k) && (k < 24)) || ((k >= 0) && (k < paramInt2)));
    return false;
    return false;
  }
  
  private void c(String paramString)
  {
    com.sec.spp.push.notisvc.e.b.b("Pkg : " + paramString + " Deleted", this.a);
    long l = System.currentTimeMillis();
    f.a().a(a(l, paramString));
  }
  
  private String d()
  {
    Object localObject4 = null;
    Object localObject1 = localObject4;
    Object localObject5;
    do
    {
      try
      {
        if (e() == true)
        {
          localObject1 = localObject4;
          Object localObject2 = c();
          if (localObject2 != null)
          {
            localObject1 = localObject4;
            if (((ArrayList)localObject2).size() > 0) {}
          }
          else
          {
            localObject1 = localObject4;
            com.sec.spp.push.notisvc.e.b.a("Fail. No Pre Installed Package List", this.a);
            return null;
          }
          localObject1 = localObject4;
          localObject2 = b(c.a((ArrayList)localObject2));
          localObject1 = localObject4;
          localObject2 = new com.sec.spp.push.notisvc.e.e().a((String)localObject2, f());
          localObject1 = localObject2;
          com.sec.spp.push.notisvc.e.b.b("Data : " + (String)localObject2, this.a);
          return localObject2;
        }
      }
      catch (Exception localException)
      {
        com.sec.spp.push.notisvc.e.b.a(localException.getMessage(), this.a);
        return localObject1;
      }
      localObject1 = localObject4;
      Object localObject3 = b();
      localObject1 = localObject4;
      localObject5 = h();
      if (localObject3 != null)
      {
        localObject1 = localObject4;
        if (((ArrayList)localObject3).size() > 0) {}
      }
      else if (localObject5 != null)
      {
        localObject1 = localObject4;
        if (((ArrayList)localObject5).size() > 0) {}
      }
      else
      {
        localObject1 = localObject4;
        com.sec.spp.push.notisvc.e.b.b("No Event has occured", this.a);
        return null;
      }
      localObject1 = localObject4;
      localObject5 = a(c.b((ArrayList)localObject3), a((ArrayList)localObject5));
      localObject3 = localObject5;
      localObject1 = localObject4;
    } while (!TextUtils.isEmpty((CharSequence)localObject5));
    localObject1 = localObject4;
    com.sec.spp.push.notisvc.e.b.b("No event", this.a);
    return null;
  }
  
  private boolean e()
  {
    return !com.sec.spp.push.notisvc.e.d.q(PushClientApplication.b());
  }
  
  private String f()
  {
    return com.sec.spp.push.notisvc.e.d.e(PushClientApplication.b());
  }
  
  private String g()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://ew1.reg.bigdata.ssp.samsung.com:80/tracking/");
    localStringBuilder.append(f());
    return localStringBuilder.toString();
  }
  
  private ArrayList h()
  {
    Object localObject = f.a().b();
    if (localObject == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      d localD = b((String)((Iterator)localObject).next());
      localD.a(e.b);
      localArrayList.add(localD);
    }
    return localArrayList;
  }
  
  public void a()
  {
    if (TextUtils.isEmpty(f()))
    {
      com.sec.spp.push.notisvc.e.b.a("Empty Reg ID", this.a);
      return;
    }
    Object localObject = g();
    HashMap localHashMap = new HashMap();
    localHashMap.put("content-type", "application/json");
    String str = d();
    if (TextUtils.isEmpty(str))
    {
      com.sec.spp.push.notisvc.e.b.d("tracking data is empty", this.a);
      return;
    }
    if (com.sec.spp.push.notisvc.c.a.booleanValue()) {}
    for (localObject = new com.sec.spp.push.notisvc.d.a().a((String)localObject, localHashMap, str, "POST", true, true);; localObject = new com.sec.spp.push.notisvc.d.a().a((String)localObject, localHashMap, str, "POST", true))
    {
      a((com.sec.spp.push.notisvc.d.b)localObject);
      return;
    }
  }
  
  public void a(String paramString)
  {
    if (!com.sec.spp.push.notisvc.e.d.v(PushClientApplication.b()))
    {
      com.sec.spp.push.notisvc.e.b.b("Tracking isn't activated", this.a);
      return;
    }
    if (!c.a(paramString))
    {
      com.sec.spp.push.notisvc.e.b.b("Pkg : " + paramString + " is excluded", this.a);
      return;
    }
    c(paramString);
  }
  
  public d b(String paramString)
  {
    paramString = paramString.split(Character.toString(';'));
    d localD = new d();
    localD.a(Long.valueOf(paramString[0].split(Character.toString(':'))[1]).longValue());
    localD.a(paramString[1].split(Character.toString(':'))[1]);
    com.sec.spp.push.notisvc.e.b.b("time : " + localD.b() + ", pkg : " + localD.a(), this.a);
    return localD;
  }
  
  public ArrayList b()
  {
    localObject1 = PushClientApplication.b();
    Object localObject2 = ((Context)localObject1).getPackageManager().getInstalledPackages(128);
    long l = com.sec.spp.push.notisvc.e.d.w((Context)localObject1);
    localObject1 = new ArrayList();
    try
    {
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
        if (localPackageInfo.firstInstallTime > l)
        {
          d localD = new d();
          localD.a(localPackageInfo.packageName);
          localD.a(localPackageInfo.firstInstallTime);
          localD.a(e.a);
          ((ArrayList)localObject1).add(localD);
        }
      }
      return localObject1;
    }
    catch (Exception localException)
    {
      com.sec.spp.push.notisvc.e.b.b(localException.getMessage(), this.a);
    }
  }
  
  public void b(Context paramContext)
  {
    com.sec.spp.push.notisvc.e.b.b("setAlarmAfterBoot", this.a);
    c(paramContext);
  }
  
  public ArrayList c()
  {
    Object localObject1 = PushClientApplication.b().getPackageManager().getInstalledPackages(128);
    long l = System.currentTimeMillis();
    ArrayList localArrayList = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
      b localB = new b();
      localObject2 = ((PackageInfo)localObject2).packageName;
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        localB.a((String)localObject2);
        localB.a(l);
        localArrayList.add(localB);
      }
    }
    return localArrayList;
  }
  
  public void c(Context paramContext)
  {
    if (com.sec.spp.push.notisvc.e.d.v(paramContext))
    {
      if (b(com.sec.spp.push.notisvc.e.d.r(paramContext), com.sec.spp.push.notisvc.e.d.s(paramContext)))
      {
        com.sec.spp.push.notisvc.e.b.b("tracking alarm time : current", this.a);
        a(paramContext, "tracking", System.currentTimeMillis());
      }
    }
    else {
      return;
    }
    d(paramContext);
  }
  
  public void d(Context paramContext)
  {
    int k = com.sec.spp.push.notisvc.e.d.r(paramContext);
    int m = com.sec.spp.push.notisvc.e.d.s(paramContext);
    int n = a(k, m);
    int i1 = Calendar.getInstance().get(11);
    long l = Math.abs(new Random().nextLong() % (n * 3600000L));
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(11, k);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    if ((b(k, m)) || (k < i1)) {
      localCalendar.setTimeInMillis(localCalendar.getTimeInMillis() + 86400000L);
    }
    localCalendar.setTimeInMillis(localCalendar.getTimeInMillis() + l);
    com.sec.spp.push.notisvc.e.b.b("tracking alarm random time(m) : " + l / 60000L, this.a);
    a(paramContext, "tracking", localCalendar.getTimeInMillis());
  }
  
  public void e(Context paramContext)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("eventType", com.sec.spp.push.notisvc.alarm.a.b.ordinal());
    AlarmEventManager.a(paramContext, "retry_track", System.currentTimeMillis() + 10800000L, localBundle, false);
  }
  
  public void f(Context paramContext)
  {
    AlarmEventManager.a(paramContext, "tracking");
  }
  
  public void g(Context paramContext)
  {
    AlarmEventManager.a(paramContext, "retry_track");
  }
}
