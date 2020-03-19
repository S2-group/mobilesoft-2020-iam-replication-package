package com.burockgames.timeclocker.a;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.usage.UsageEvents;
import android.app.usage.UsageEvents.Event;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import com.burockgames.timeclocker.service.AlarmController;
import com.burockgames.timeclocker.widget.Widget_Provider;
import com.burockgames.timeclocker.widget.Widget_Provider_Second;
import com.google.android.gms.ads.c.a;
import com.google.android.gms.ads.h;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

public class f
{
  private static h d;
  private final UsageStatsManager a;
  private final Context b;
  private final g c;
  
  public f(Context paramContext)
  {
    this.b = paramContext;
    this.a = ((UsageStatsManager)this.b.getSystemService("usagestats"));
    this.c = new g(this.b);
  }
  
  public static c a(Context paramContext, int paramInt)
  {
    if (paramInt == 1) {
      return c.a(paramContext, new Locale("tr"));
    }
    if (paramInt == 2) {
      return c.a(paramContext, new Locale("de"));
    }
    if (paramInt == 3) {
      return c.a(paramContext, new Locale("es"));
    }
    if (paramInt == 4) {
      return c.a(paramContext, new Locale("ru"));
    }
    if (paramInt == 5) {
      return c.a(paramContext, new Locale("fr"));
    }
    return c.a(paramContext, new Locale(""));
  }
  
  public static void a(boolean paramBoolean, Context paramContext)
  {
    if (!paramBoolean) {
      try
      {
        d = new h(paramContext);
        d.a("ca-app-pub-2348383263850497/5191537845");
        d.a(new com.google.android.gms.ads.a()
        {
          public void a()
          {
            f.k().a(new c.a().a());
          }
        });
        d.a(new c.a().a());
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private String d(long paramLong)
  {
    int i = (int)paramLong / 1000;
    int j = i % 60;
    Object localObject1;
    if (j < 10)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("0");
      ((StringBuilder)localObject1).append(j);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("");
      ((StringBuilder)localObject1).append(j);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    i /= 60;
    j = i % 60;
    Object localObject2;
    if (j < 10)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("0");
      ((StringBuilder)localObject2).append(j);
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    else
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("");
      ((StringBuilder)localObject2).append(j);
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    i /= 60;
    Object localObject3;
    if (i < 10)
    {
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("0");
      ((StringBuilder)localObject3).append(i);
      localObject3 = ((StringBuilder)localObject3).toString();
    }
    else
    {
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("");
      ((StringBuilder)localObject3).append(i);
      localObject3 = ((StringBuilder)localObject3).toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject3);
    localStringBuilder.append(":");
    localStringBuilder.append((String)localObject2);
    localStringBuilder.append(":");
    localStringBuilder.append((String)localObject1);
    return localStringBuilder.toString();
  }
  
  private String e(long paramLong)
  {
    int i = this.c.p();
    Object localObject1 = "";
    Object localObject2 = "";
    paramLong /= 1000L;
    long l = paramLong / 60L;
    int j = (int)(l / 60L);
    if (j != 0)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(j);
      ((StringBuilder)localObject1).append(e.g(this.b, i));
      ((StringBuilder)localObject1).append(" ");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    int k = (int)(l % 60L);
    if (k != 0)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(k);
      ((StringBuilder)localObject2).append(e.h(this.b, i));
      ((StringBuilder)localObject2).append(" ");
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    int m = (int)(paramLong % 60L);
    Object localObject3;
    if ((m == 0) && ((j != 0) || (k != 0)))
    {
      localObject3 = "";
    }
    else
    {
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(m);
      ((StringBuilder)localObject3).append(e.i(this.b, i));
      localObject3 = ((StringBuilder)localObject3).toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append((String)localObject2);
    localStringBuilder.append((String)localObject3);
    return localStringBuilder.toString();
  }
  
  private void g(List<com.burockgames.timeclocker.alarm.b> paramList)
  {
    HashMap localHashMap = new HashMap();
    int j = 0;
    int i = 0;
    while (i < paramList.size())
    {
      localHashMap.put(((com.burockgames.timeclocker.alarm.b)paramList.get(i)).a(), Long.valueOf(0L));
      i += 1;
    }
    Object localObject = new HashMap();
    i = 0;
    while (i < paramList.size())
    {
      ((HashMap)localObject).put(((com.burockgames.timeclocker.alarm.b)paramList.get(i)).a(), Long.valueOf(-1L));
      i += 1;
    }
    UsageEvents localUsageEvents = this.a.queryEvents(0L, System.currentTimeMillis());
    UsageEvents.Event localEvent = new UsageEvents.Event();
    long l = l();
    for (;;)
    {
      i = j;
      if (!localUsageEvents.hasNextEvent()) {
        break;
      }
      localUsageEvents.getNextEvent(localEvent);
      String str1 = localEvent.getPackageName();
      if (localEvent.getTimeStamp() >= l)
      {
        String str2 = this.c.b();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str1);
        localStringBuilder.append("(&)");
        if ((!str2.contains(localStringBuilder.toString())) && (localHashMap.containsKey(localEvent.getPackageName()))) {
          if (localEvent.getEventType() == 1)
          {
            ((HashMap)localObject).put(str1, Long.valueOf(localEvent.getTimeStamp()));
          }
          else if ((localEvent.getEventType() == 2) && (((Long)((HashMap)localObject).get(str1)).longValue() != -1L))
          {
            localHashMap.put(str1, Long.valueOf(((Long)localHashMap.get(str1)).longValue() + localEvent.getTimeStamp() - ((Long)((HashMap)localObject).get(str1)).longValue()));
            ((HashMap)localObject).put(str1, Long.valueOf(-1L));
          }
        }
      }
    }
    while (i < paramList.size())
    {
      localObject = (com.burockgames.timeclocker.alarm.b)paramList.get(i);
      ((com.burockgames.timeclocker.alarm.b)localObject).a(((Long)localHashMap.get(((com.burockgames.timeclocker.alarm.b)localObject).a())).longValue());
      i += 1;
    }
  }
  
  private void h(List<com.burockgames.timeclocker.alarm.b> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      int k;
      for (int j = 0; j < paramList.size() - 1; j = k)
      {
        Object localObject = ((com.burockgames.timeclocker.alarm.b)paramList.get(j)).b();
        k = j + 1;
        if (((String)localObject).compareTo(((com.burockgames.timeclocker.alarm.b)paramList.get(k)).b()) > 0)
        {
          localObject = (com.burockgames.timeclocker.alarm.b)paramList.get(k);
          paramList.set(k, paramList.get(j));
          paramList.set(j, localObject);
        }
      }
      i += 1;
    }
  }
  
  public static void i()
  {
    try
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (System.currentTimeMillis() - b.a <= 180000L) {
            return;
          }
          if ((f.k() != null) && (f.k().a()))
          {
            f.k().b();
            b.a = System.currentTimeMillis();
          }
        }
      }, 2000L);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void i(List<com.burockgames.timeclocker.widget.c> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      int k;
      for (int j = 0; j < paramList.size() - 1; j = k)
      {
        long l = ((com.burockgames.timeclocker.widget.c)paramList.get(j)).c();
        k = j + 1;
        if (l > ((com.burockgames.timeclocker.widget.c)paramList.get(k)).c())
        {
          com.burockgames.timeclocker.widget.c localC = (com.burockgames.timeclocker.widget.c)paramList.get(k);
          paramList.set(k, paramList.get(j));
          paramList.set(j, localC);
        }
      }
      i += 1;
    }
  }
  
  private long j(String paramString)
  {
    UsageEvents localUsageEvents = this.a.queryEvents(0L, System.currentTimeMillis());
    UsageEvents.Event localEvent2 = new UsageEvents.Event();
    long l3 = l();
    long l1 = 0L;
    UsageEvents.Event localEvent1 = null;
    long l2 = -1L;
    while (localUsageEvents.hasNextEvent())
    {
      localUsageEvents.getNextEvent(localEvent2);
      String str1 = localEvent2.getPackageName();
      if (localEvent2.getTimeStamp() >= l3)
      {
        String str2 = this.c.b();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append("(&)");
        if (!str2.contains(localStringBuilder.toString())) {
          if (str1.equals(paramString)) {
            if (localEvent2.getEventType() == 1)
            {
              l2 = localEvent2.getTimeStamp();
              localEvent1 = localEvent2;
            }
            else if (localEvent2.getEventType() == 2)
            {
              if (l2 != -1L)
              {
                l1 += localEvent2.getTimeStamp() - l2;
                l2 = -1L;
                localEvent1 = null;
              }
            }
            else {}
          }
        }
      }
    }
    l2 = l1;
    if (localEvent1 != null) {
      l2 = l1 + (System.currentTimeMillis() - localEvent1.getTimeStamp());
    }
    return l2;
  }
  
  private void j(List<com.burockgames.timeclocker.widget.c> paramList)
  {
    HashMap localHashMap = new HashMap();
    int j = 0;
    int i = 0;
    while (i < paramList.size())
    {
      localHashMap.put(((com.burockgames.timeclocker.widget.c)paramList.get(i)).a(), Long.valueOf(0L));
      i += 1;
    }
    Object localObject = new HashMap();
    i = 0;
    while (i < paramList.size())
    {
      ((HashMap)localObject).put(((com.burockgames.timeclocker.widget.c)paramList.get(i)).a(), Long.valueOf(-1L));
      i += 1;
    }
    UsageEvents localUsageEvents = this.a.queryEvents(0L, System.currentTimeMillis());
    UsageEvents.Event localEvent = new UsageEvents.Event();
    long l = l();
    for (;;)
    {
      i = j;
      if (!localUsageEvents.hasNextEvent()) {
        break;
      }
      localUsageEvents.getNextEvent(localEvent);
      String str1 = localEvent.getPackageName();
      if (localEvent.getTimeStamp() >= l)
      {
        String str2 = this.c.b();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str1);
        localStringBuilder.append("(&)");
        if ((!str2.contains(localStringBuilder.toString())) && (localHashMap.containsKey(localEvent.getPackageName()))) {
          if (localEvent.getEventType() == 1)
          {
            ((HashMap)localObject).put(str1, Long.valueOf(localEvent.getTimeStamp()));
          }
          else if ((localEvent.getEventType() == 2) && (((Long)((HashMap)localObject).get(str1)).longValue() != -1L))
          {
            localHashMap.put(str1, Long.valueOf(((Long)localHashMap.get(str1)).longValue() + localEvent.getTimeStamp() - ((Long)((HashMap)localObject).get(str1)).longValue()));
            ((HashMap)localObject).put(str1, Long.valueOf(-1L));
          }
        }
      }
    }
    while (i < paramList.size())
    {
      localObject = (com.burockgames.timeclocker.widget.c)paramList.get(i);
      ((com.burockgames.timeclocker.widget.c)localObject).a(((Long)localHashMap.get(((com.burockgames.timeclocker.widget.c)localObject).a())).longValue());
      i += 1;
    }
  }
  
  private void k(List<com.burockgames.timeclocker.widget.c> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      int k;
      for (int j = 0; j < paramList.size() - 1; j = k)
      {
        long l = ((com.burockgames.timeclocker.widget.c)paramList.get(j)).c();
        k = j + 1;
        if (l < ((com.burockgames.timeclocker.widget.c)paramList.get(k)).c())
        {
          com.burockgames.timeclocker.widget.c localC = (com.burockgames.timeclocker.widget.c)paramList.get(k);
          paramList.set(k, paramList.get(j));
          paramList.set(j, localC);
        }
      }
      i += 1;
    }
  }
  
  private long l()
  {
    int i = Integer.parseInt(new SimpleDateFormat("HH", Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis())));
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(11, this.c.r());
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    if (i < this.c.r()) {
      localCalendar.add(5, -1);
    }
    return localCalendar.getTimeInMillis();
  }
  
  public String a()
  {
    Object localObject3 = this.c.b();
    PackageManager localPackageManager = this.b.getPackageManager();
    Object localObject2 = new HashMap();
    Object localObject1 = localPackageManager.getInstalledApplications(128).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject1).next();
      try
      {
        if (localPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
          ((HashMap)localObject2).put(localApplicationInfo.packageName, Integer.valueOf(0));
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localObject1 = this.a.queryEvents(0L, System.currentTimeMillis());
    UsageEvents.Event localEvent = new UsageEvents.Event();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    Object localObject4 = "";
    long l3 = l();
    long l1 = -1L;
    long l2 = -1L;
    int i = 0;
    Object localObject5;
    while (((UsageEvents)localObject1).hasNextEvent())
    {
      ((UsageEvents)localObject1).getNextEvent(localEvent);
      String str = localEvent.getPackageName();
      if (localEvent.getTimeStamp() >= l3)
      {
        localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append(str);
        ((StringBuilder)localObject5).append("(&)");
        if (((String)localObject3).contains(((StringBuilder)localObject5).toString())) {}
      }
      else
      {
        localObject5 = localObject4;
        if (localEvent.getEventType() == 1)
        {
          if ((((String)localObject4).equals(str)) && (localSimpleDateFormat.format(Long.valueOf(l1)).equals(localSimpleDateFormat.format(Long.valueOf(localEvent.getTimeStamp()))))) {
            i = 1;
          } else {
            i = 0;
          }
          localObject5 = str;
        }
        if (((HashMap)localObject2).containsKey(str)) {
          if (localEvent.getEventType() == 1)
          {
            l2 = localEvent.getTimeStamp();
          }
          else if (localEvent.getEventType() == 2)
          {
            l1 = localEvent.getTimeStamp();
            if (l2 != -1L)
            {
              if (i != 0)
              {
                int j = localArrayList3.size() - 1;
                if (j != -1)
                {
                  localArrayList3.set(j, Long.valueOf(((Long)localArrayList3.get(j)).longValue() + (localEvent.getTimeStamp() - l2)));
                }
                else
                {
                  localArrayList1.add(str);
                  localArrayList2.add(Long.valueOf(l2));
                  localArrayList3.add(Long.valueOf(localEvent.getTimeStamp() - l2));
                }
              }
              else
              {
                localArrayList1.add(str);
                localArrayList2.add(Long.valueOf(l2));
                localArrayList3.add(Long.valueOf(localEvent.getTimeStamp() - l2));
              }
              l2 = -1L;
            }
          }
          else {}
        }
        localObject4 = localObject5;
      }
    }
    localObject4 = new SimpleDateFormat("kk:mm:ss", Locale.getDefault());
    i = 0;
    while (i < localArrayList3.size())
    {
      localObject3 = (Long)localArrayList2.get(i);
      localObject5 = (Long)localArrayList3.get(i);
      localObject1 = (String)localArrayList1.get(i);
      if (((Long)localObject5).longValue() >= 1000L) {}
      try
      {
        localObject1 = localPackageManager.getApplicationInfo((String)localObject1, 0);
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
      localObject1 = null;
      if (localObject1 != null) {
        localObject1 = localPackageManager.getApplicationLabel((ApplicationInfo)localObject1);
      } else {
        localObject1 = "-";
      }
      localObject1 = (String)localObject1;
      localStringBuilder.append("★");
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append("\n");
      localObject2 = ((SimpleDateFormat)localObject4).format(localObject3);
      localObject1 = localObject2;
      if (((String)localObject2).substring(0, 2).equals("24"))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("00");
        ((StringBuilder)localObject1).append(((String)localObject2).substring(2));
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject3 = ((SimpleDateFormat)localObject4).format(Long.valueOf(((Long)localObject3).longValue() + ((Long)localObject5).longValue()));
      localObject2 = localObject3;
      if (((String)localObject3).substring(0, 2).equals("24"))
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("00");
        ((StringBuilder)localObject2).append(((String)localObject3).substring(2));
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(" - ");
      localStringBuilder.append((String)localObject2);
      localStringBuilder.append(" -> ");
      localStringBuilder.append(b(((Long)localObject5).longValue()));
      localStringBuilder.append("\n");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public String a(long paramLong)
  {
    int i = this.c.p();
    Object localObject1 = "";
    Object localObject2 = "";
    int j = (int)(paramLong / 60L);
    if (j != 0)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(j);
      ((StringBuilder)localObject1).append(e.g(this.b, i));
      ((StringBuilder)localObject1).append(" ");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    j = (int)(paramLong % 60L);
    if (j != 0)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(j);
      ((StringBuilder)localObject2).append(e.h(this.b, i));
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append((String)localObject2);
    return localStringBuilder.toString();
  }
  
  public String a(String paramString)
  {
    Object localObject4 = this.c.b();
    StringBuilder localStringBuilder = new StringBuilder();
    UsageEvents localUsageEvents = this.a.queryEvents(0L, System.currentTimeMillis());
    UsageEvents.Event localEvent = new UsageEvents.Event();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    Object localObject1 = "";
    long l3 = l();
    long l2 = -1L;
    long l1 = -1L;
    int i = 0;
    Object localObject2;
    while (localUsageEvents.hasNextEvent())
    {
      localUsageEvents.getNextEvent(localEvent);
      localObject3 = localEvent.getPackageName();
      if (localEvent.getTimeStamp() >= l3)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramString);
        ((StringBuilder)localObject2).append("(&)");
        if (!((String)localObject4).contains(((StringBuilder)localObject2).toString()))
        {
          localObject2 = localObject1;
          if (localEvent.getEventType() == 1)
          {
            if ((((String)localObject1).equals(localObject3)) && (localSimpleDateFormat.format(Long.valueOf(l2)).equals(localSimpleDateFormat.format(Long.valueOf(localEvent.getTimeStamp()))))) {
              i = 1;
            } else {
              i = 0;
            }
            localObject2 = localObject3;
          }
          if (((String)localObject3).equals(paramString))
          {
            if (localEvent.getEventType() == 1)
            {
              l1 = localEvent.getTimeStamp();
              localObject1 = localObject2;
            }
            else if (localEvent.getEventType() == 2)
            {
              l2 = localEvent.getTimeStamp();
              if (l1 != -1L)
              {
                if (i != 0)
                {
                  int j = localArrayList2.size() - 1;
                  if (j != -1)
                  {
                    localArrayList2.set(j, Long.valueOf(((Long)localArrayList2.get(j)).longValue() + (localEvent.getTimeStamp() - l1)));
                  }
                  else
                  {
                    localArrayList1.add(Long.valueOf(l1));
                    localArrayList2.add(Long.valueOf(localEvent.getTimeStamp() - l1));
                  }
                }
                else
                {
                  localArrayList1.add(Long.valueOf(l1));
                  localArrayList2.add(Long.valueOf(localEvent.getTimeStamp() - l1));
                }
                l1 = -1L;
                localObject1 = localObject2;
              }
              else
              {
                localObject1 = localObject2;
              }
            }
            else
            {
              localObject1 = localObject2;
            }
          }
          else {
            localObject1 = localObject2;
          }
        }
      }
    }
    Object localObject3 = new SimpleDateFormat("kk:mm:ss", Locale.getDefault());
    i = 0;
    while (i < localArrayList2.size())
    {
      localObject2 = (Long)localArrayList1.get(i);
      localObject4 = (Long)localArrayList2.get(i);
      if (((Long)localObject4).longValue() >= 1000L)
      {
        localObject1 = ((SimpleDateFormat)localObject3).format(localObject2);
        paramString = (String)localObject1;
        if (((String)localObject1).substring(0, 2).equals("24"))
        {
          paramString = new StringBuilder();
          paramString.append("00");
          paramString.append(((String)localObject1).substring(2));
          paramString = paramString.toString();
        }
        localObject2 = ((SimpleDateFormat)localObject3).format(Long.valueOf(((Long)localObject2).longValue() + ((Long)localObject4).longValue()));
        localObject1 = localObject2;
        if (((String)localObject2).substring(0, 2).equals("24"))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("00");
          ((StringBuilder)localObject1).append(((String)localObject2).substring(2));
          localObject1 = ((StringBuilder)localObject1).toString();
        }
        localStringBuilder.append(paramString);
        localStringBuilder.append(" - ");
        localStringBuilder.append((String)localObject1);
        localStringBuilder.append(" -> ");
        localStringBuilder.append(b(((Long)localObject4).longValue()));
        localStringBuilder.append("\n");
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public void a(int paramInt)
  {
    long l;
    if (paramInt == 0) {
      l = 2500L;
    } else {
      l = 60000L;
    }
    try
    {
      Object localObject = new Intent(this.b, Widget_Provider.class);
      AlarmManager localAlarmManager = (AlarmManager)this.b.getSystemService("alarm");
      if (localAlarmManager == null)
      {
        a(0);
        return;
      }
      localObject = PendingIntent.getBroadcast(this.b, 3, (Intent)localObject, 134217728);
      localAlarmManager.set(1, System.currentTimeMillis() + l, (PendingIntent)localObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    a(0);
  }
  
  public void a(String paramString, long paramLong)
  {
    String str = this.c.c();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("---");
    if (!str.contains(((StringBuilder)localObject).toString())) {
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("---");
    int i = str.indexOf(((StringBuilder)localObject).toString());
    int j = str.indexOf("(&)", i) + 3;
    if (j == str.length())
    {
      str = str.substring(0, i);
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str.substring(0, i));
      ((StringBuilder)localObject).append(str.substring(j));
      str = ((StringBuilder)localObject).toString();
    }
    j = d(paramString);
    i = j;
    if (j == -1) {
      i = 0;
    }
    localObject = new SimpleDateFormat("yyyy-MM-dd-HH", Locale.getDefault());
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(5, 1);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append(paramString);
    localStringBuilder.append("---");
    localStringBuilder.append(paramLong);
    localStringBuilder.append("---");
    localStringBuilder.append(i);
    localStringBuilder.append("---");
    localStringBuilder.append(((SimpleDateFormat)localObject).format(Long.valueOf(localCalendar.getTimeInMillis())));
    localStringBuilder.append("(&)");
    paramString = localStringBuilder.toString();
    this.c.a("alarmApps", paramString);
  }
  
  public void a(String paramString, long paramLong, int paramInt, boolean paramBoolean)
  {
    c(paramString);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH", Locale.getDefault());
    Calendar localCalendar = Calendar.getInstance();
    if (paramBoolean) {
      localCalendar.add(5, 1);
    }
    String str = this.c.c();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append(paramString);
    localStringBuilder.append("---");
    localStringBuilder.append(paramLong);
    localStringBuilder.append("---");
    localStringBuilder.append(paramInt);
    localStringBuilder.append("---");
    localStringBuilder.append(localSimpleDateFormat.format(Long.valueOf(localCalendar.getTimeInMillis())));
    localStringBuilder.append("(&)");
    paramString = localStringBuilder.toString();
    this.c.a("alarmApps", paramString);
  }
  
  public void a(List<com.burockgames.timeclocker.main.b> paramList)
  {
    Object localObject = this.c.b();
    HashMap localHashMap1 = new HashMap();
    int j = 0;
    int i = 0;
    while (i < paramList.size())
    {
      localHashMap1.put(((com.burockgames.timeclocker.main.b)paramList.get(i)).a(), Long.valueOf(0L));
      i += 1;
    }
    HashMap localHashMap2 = new HashMap();
    i = 0;
    while (i < paramList.size())
    {
      localHashMap2.put(((com.burockgames.timeclocker.main.b)paramList.get(i)).a(), Long.valueOf(-1L));
      i += 1;
    }
    UsageEvents localUsageEvents = this.a.queryEvents(0L, System.currentTimeMillis());
    UsageEvents.Event localEvent = new UsageEvents.Event();
    long l = l();
    for (;;)
    {
      i = j;
      if (!localUsageEvents.hasNextEvent()) {
        break;
      }
      localUsageEvents.getNextEvent(localEvent);
      String str = localEvent.getPackageName();
      if (localEvent.getTimeStamp() >= l)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append("(&)");
        if ((!((String)localObject).contains(localStringBuilder.toString())) && (localHashMap1.containsKey(localEvent.getPackageName()))) {
          if (localEvent.getEventType() == 1)
          {
            localHashMap2.put(str, Long.valueOf(localEvent.getTimeStamp()));
          }
          else if ((localEvent.getEventType() == 2) && (((Long)localHashMap2.get(str)).longValue() != -1L))
          {
            localHashMap1.put(str, Long.valueOf(((Long)localHashMap1.get(str)).longValue() + localEvent.getTimeStamp() - ((Long)localHashMap2.get(str)).longValue()));
            localHashMap2.put(str, Long.valueOf(-1L));
          }
        }
      }
    }
    while (i < paramList.size())
    {
      localObject = (com.burockgames.timeclocker.main.b)paramList.get(i);
      ((com.burockgames.timeclocker.main.b)localObject).a(((Long)localHashMap1.get(((com.burockgames.timeclocker.main.b)localObject).a())).longValue());
      i += 1;
    }
  }
  
  public String b(long paramLong)
  {
    if (this.c.q()) {
      return d(paramLong);
    }
    return e(paramLong);
  }
  
  public List<com.burockgames.timeclocker.alarm.b> b()
  {
    int i;
    if (Build.VERSION.SDK_INT >= 26) {
      i = 0;
    } else {
      i = 1;
    }
    ArrayList localArrayList1 = new ArrayList();
    String str1 = this.c.c();
    PackageManager localPackageManager = this.b.getPackageManager();
    Object localObject1 = localPackageManager.getInstalledApplications(128);
    ArrayList localArrayList2 = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    Object localObject3;
    while (((Iterator)localObject1).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject1).next();
      try
      {
        if (localPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName) != null)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(localApplicationInfo.packageName);
          ((StringBuilder)localObject3).append("---");
          if (str1.contains(((StringBuilder)localObject3).toString())) {
            localArrayList2.add(localApplicationInfo);
          }
        }
      }
      catch (Exception localException3)
      {
        localException3.printStackTrace();
      }
    }
    int j = 0;
    while (j < localArrayList2.size())
    {
      localObject1 = (ApplicationInfo)localArrayList2.get(j);
      String str2 = ((ApplicationInfo)localObject1).packageName;
      String str3 = ((ApplicationInfo)localObject1).loadLabel(localPackageManager).toString();
      Object localObject2 = null;
      if (i != 0) {}
      try
      {
        localObject1 = ((ApplicationInfo)localObject1).loadIcon(localPackageManager);
        localObject2 = null;
        break label258;
        localObject3 = d.a(localPackageManager, str2);
        localObject1 = android.support.v4.a.a.a(this.b, 2131230892);
        localObject2 = localObject3;
      }
      catch (Exception localException1)
      {
        label258:
        for (;;) {}
      }
      localObject1 = android.support.v4.a.a.a(this.b, 2131230892);
      try
      {
        long l = e(str2);
        int k = d(str2);
        localArrayList1.add(new com.burockgames.timeclocker.alarm.b(str2, str3, localObject2, (Drawable)localObject1, 0L, l, k));
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      j += 1;
    }
    g(localArrayList1);
    h(localArrayList1);
    if (str1.contains("com.burockgames.to_tal---")) {
      localArrayList1.add(0, new com.burockgames.timeclocker.alarm.b("com.burockgames.to_tal", e.c(this.b, this.c.p()), null, android.support.v4.a.a.a(this.b, 2131230889), d(), e("com.burockgames.to_tal"), 0));
    }
    return localArrayList1;
  }
  
  public void b(int paramInt)
  {
    long l;
    if (paramInt == 0) {
      l = 1250L;
    } else {
      l = 60000L;
    }
    try
    {
      Object localObject = new Intent(this.b, Widget_Provider_Second.class);
      AlarmManager localAlarmManager = (AlarmManager)this.b.getSystemService("alarm");
      if (localAlarmManager == null)
      {
        b(0);
        return;
      }
      localObject = PendingIntent.getBroadcast(this.b, 4, (Intent)localObject, 134217728);
      localAlarmManager.set(1, System.currentTimeMillis() + l, (PendingIntent)localObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    b(0);
  }
  
  public void b(List<com.burockgames.timeclocker.main.b> paramList)
  {
    String str2 = this.c.b();
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramList.size())
    {
      localHashMap.put(((com.burockgames.timeclocker.main.b)paramList.get(i)).a(), Integer.valueOf(0));
      i += 1;
    }
    UsageEvents localUsageEvents = this.a.queryEvents(0L, System.currentTimeMillis());
    UsageEvents.Event localEvent = new UsageEvents.Event();
    Object localObject1 = "";
    long l3 = l();
    long l2 = -1L;
    boolean bool1 = false;
    while (localUsageEvents.hasNextEvent())
    {
      localUsageEvents.getNextEvent(localEvent);
      String str1 = localEvent.getPackageName();
      Object localObject2;
      if (localEvent.getTimeStamp() >= l3)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(str1);
        ((StringBuilder)localObject2).append("(&)");
        if (str2.contains(((StringBuilder)localObject2).toString())) {}
      }
      else
      {
        long l1;
        boolean bool2;
        if (localEvent.getEventType() == 1)
        {
          l1 = localEvent.getTimeStamp();
          bool2 = ((String)localObject1).equals(str1);
          localObject2 = str1;
        }
        else if (localEvent.getEventType() == 2)
        {
          localObject2 = localObject1;
          l1 = l2;
          bool2 = bool1;
          if (l2 != -1L)
          {
            if ((!bool1) && (localHashMap.containsKey(str1))) {
              localHashMap.put(str1, Integer.valueOf(((Integer)localHashMap.get(str1)).intValue() + 1));
            }
            l1 = -1L;
            localObject2 = localObject1;
            bool2 = bool1;
          }
        }
        else
        {
          bool2 = bool1;
          l1 = l2;
          localObject2 = localObject1;
        }
        localObject1 = localObject2;
        l2 = l1;
        bool1 = bool2;
      }
    }
    i = 0;
    while (i < paramList.size())
    {
      localObject1 = (com.burockgames.timeclocker.main.b)paramList.get(i);
      ((com.burockgames.timeclocker.main.b)localObject1).a(((Integer)localHashMap.get(((com.burockgames.timeclocker.main.b)localObject1).a())).intValue());
      i += 1;
    }
  }
  
  public long[] b(String paramString)
  {
    String str = this.c.b();
    int j = this.c.r();
    Object localObject1 = Calendar.getInstance();
    int i = Integer.parseInt(new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis())));
    ((Calendar)localObject1).add(5, -6);
    if (i < j) {
      ((Calendar)localObject1).add(5, -1);
    }
    UsageEvents localUsageEvents = this.a.queryEvents(0L, System.currentTimeMillis());
    UsageEvents.Event localEvent = new UsageEvents.Event();
    long[] arrayOfLong = new long[7];
    i = 0;
    while (i < 7)
    {
      arrayOfLong[i] = ((Calendar)localObject1).get(5);
      ((Calendar)localObject1).add(5, 1);
      i += 1;
    }
    localObject1 = new long[7];
    i = 0;
    while (i < 7)
    {
      localObject1[i] = 0L;
      i += 1;
    }
    long l1 = -1L;
    while (localUsageEvents.hasNextEvent())
    {
      localUsageEvents.getNextEvent(localEvent);
      Object localObject2 = localEvent.getPackageName();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("(&)");
      if (!str.contains(localStringBuilder.toString())) {
        if (((String)localObject2).equals(paramString)) {
          if (localEvent.getEventType() == 1) {
            l1 = localEvent.getTimeStamp();
          } else if (localEvent.getEventType() == 2)
          {
            if (l1 != -1L)
            {
              localObject2 = Calendar.getInstance();
              ((Calendar)localObject2).setTimeInMillis(l1);
              if (((Calendar)localObject2).get(11) < j) {
                ((Calendar)localObject2).add(5, -1);
              }
              i = ((Calendar)localObject2).get(5);
              long l2 = arrayOfLong[0];
              long l3 = i;
              i = 4;
              if (l2 == l3) {
                i = 0;
              } else if (arrayOfLong[1] == l3) {
                i = 1;
              } else if (arrayOfLong[2] == l3) {
                i = 2;
              } else if (arrayOfLong[3] == l3) {
                i = 3;
              } else if (arrayOfLong[4] != l3) {
                if (arrayOfLong[5] == l3) {
                  i = 5;
                } else if (arrayOfLong[6] == l3) {
                  i = 6;
                } else {
                  i = -1;
                }
              }
              if (i != -1) {
                localObject1[i] += localEvent.getTimeStamp() - l1;
              }
              l1 = -1L;
            }
          }
          else {}
        }
      }
    }
    return localObject1;
  }
  
  public String c()
  {
    String str = "com.burockgames.no_thing";
    long l = System.currentTimeMillis();
    UsageEvents localUsageEvents = this.a.queryEvents(l - 3600000L, l);
    UsageEvents.Event localEvent = new UsageEvents.Event();
    while (localUsageEvents.hasNextEvent())
    {
      localUsageEvents.getNextEvent(localEvent);
      if (localEvent.getEventType() == 1) {
        str = localEvent.getPackageName();
      }
    }
    return str;
  }
  
  public String c(long paramLong)
  {
    int i = this.c.p();
    Object localObject1 = "";
    Object localObject2 = "";
    paramLong = paramLong / 1000L / 60L;
    int j = (int)(paramLong / 60L);
    if (j != 0)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(j);
      ((StringBuilder)localObject1).append(e.g(this.b, i));
      ((StringBuilder)localObject1).append(" ");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    j = (int)(paramLong % 60L);
    if (j != 0)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(j);
      ((StringBuilder)localObject2).append(e.h(this.b, i));
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    if ((((String)localObject1).equals("")) && (((String)localObject2).equals("")))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("0 ");
      ((StringBuilder)localObject1).append(e.h(this.b, i));
      return ((StringBuilder)localObject1).toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append((String)localObject2);
    return localStringBuilder.toString();
  }
  
  public void c(String paramString)
  {
    String str = this.c.c();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("---");
    if (!str.contains(localStringBuilder.toString())) {
      return;
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("---");
    int i = str.indexOf(localStringBuilder.toString());
    int j = str.indexOf("(&)", i) + 3;
    if (j == str.length())
    {
      paramString = str.substring(0, i);
    }
    else
    {
      paramString = new StringBuilder();
      paramString.append(str.substring(0, i));
      paramString.append(str.substring(j));
      paramString = paramString.toString();
    }
    this.c.a("alarmApps", paramString);
  }
  
  public long[] c(List<com.burockgames.timeclocker.history.a> paramList)
  {
    String str = this.c.b();
    int j = this.c.r();
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramList.size())
    {
      localHashMap.put(((com.burockgames.timeclocker.history.a)paramList.get(i)).a(), Long.valueOf(0L));
      i += 1;
    }
    Object localObject1 = Calendar.getInstance();
    i = Integer.parseInt(new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis())));
    ((Calendar)localObject1).add(5, -6);
    if (i < j) {
      ((Calendar)localObject1).add(5, -1);
    }
    paramList = this.a.queryEvents(0L, System.currentTimeMillis());
    UsageEvents.Event localEvent = new UsageEvents.Event();
    long[] arrayOfLong = new long[7];
    i = 0;
    while (i < 7)
    {
      arrayOfLong[i] = ((Calendar)localObject1).get(5);
      ((Calendar)localObject1).add(5, 1);
      i += 1;
    }
    localObject1 = new long[7];
    i = 0;
    while (i < 7)
    {
      localObject1[i] = 0L;
      i += 1;
    }
    long l1 = -1L;
    while (paramList.hasNextEvent())
    {
      paramList.getNextEvent(localEvent);
      Object localObject2 = localEvent.getPackageName();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject2);
      localStringBuilder.append("(&)");
      if (!str.contains(localStringBuilder.toString())) {
        if (localHashMap.containsKey(localObject2)) {
          if (localEvent.getEventType() == 1) {
            l1 = localEvent.getTimeStamp();
          } else if (localEvent.getEventType() == 2)
          {
            if (l1 != -1L)
            {
              localObject2 = Calendar.getInstance();
              ((Calendar)localObject2).setTimeInMillis(l1);
              if (((Calendar)localObject2).get(11) < j) {
                ((Calendar)localObject2).add(5, -1);
              }
              i = ((Calendar)localObject2).get(5);
              long l2 = arrayOfLong[0];
              long l3 = i;
              i = 6;
              if (l2 == l3) {
                i = 0;
              } else if (arrayOfLong[1] == l3) {
                i = 1;
              } else if (arrayOfLong[2] == l3) {
                i = 2;
              } else if (arrayOfLong[3] == l3) {
                i = 3;
              } else if (arrayOfLong[4] == l3) {
                i = 4;
              } else if (arrayOfLong[5] == l3) {
                i = 5;
              } else if (arrayOfLong[6] != l3) {
                i = -1;
              }
              if (i != -1) {
                localObject1[i] += localEvent.getTimeStamp() - l1;
              }
              l1 = -1L;
            }
          }
          else {}
        }
      }
    }
    return localObject1;
  }
  
  public int d(String paramString)
  {
    String str = this.c.c();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("---");
    if (!str.contains(localStringBuilder.toString())) {
      return -1;
    }
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("---");
      int i = str.indexOf(localStringBuilder.toString());
      paramString = str.substring(i, str.indexOf("(&)", i));
      if (paramString.contains("---1---")) {
        return 1;
      }
      boolean bool = paramString.contains("---2---");
      if (bool) {
        return 2;
      }
      return 0;
    }
    catch (Exception paramString) {}
    return 0;
  }
  
  public long d()
  {
    String str1 = this.c.b();
    Object localObject1 = this.b.getPackageManager();
    HashMap localHashMap = new HashMap();
    Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(128).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      try
      {
        if (((PackageManager)localObject1).getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
          localHashMap.put(localApplicationInfo.packageName, Integer.valueOf(0));
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    UsageEvents localUsageEvents = this.a.queryEvents(0L, System.currentTimeMillis());
    localObject2 = new UsageEvents.Event();
    long l3 = l();
    long l1 = 0L;
    localObject1 = null;
    long l2 = -1L;
    while (localUsageEvents.hasNextEvent())
    {
      localUsageEvents.getNextEvent((UsageEvents.Event)localObject2);
      String str2 = ((UsageEvents.Event)localObject2).getPackageName();
      if (((UsageEvents.Event)localObject2).getTimeStamp() >= l3)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str2);
        localStringBuilder.append("(&)");
        if (!str1.contains(localStringBuilder.toString())) {
          if (localHashMap.containsKey(str2)) {
            if (((UsageEvents.Event)localObject2).getEventType() == 1)
            {
              l2 = ((UsageEvents.Event)localObject2).getTimeStamp();
              localObject1 = localObject2;
            }
            else if (((UsageEvents.Event)localObject2).getEventType() == 2)
            {
              if (l2 != -1L)
              {
                l1 += ((UsageEvents.Event)localObject2).getTimeStamp() - l2;
                l2 = -1L;
                localObject1 = null;
              }
            }
            else {}
          }
        }
      }
    }
    l2 = l1;
    if (localObject1 != null) {
      l2 = l1 + (System.currentTimeMillis() - ((UsageEvents.Event)localObject1).getTimeStamp());
    }
    return l2;
  }
  
  public HashMap<String, long[]> d(List<com.burockgames.timeclocker.history.a> paramList)
  {
    String str = this.c.b();
    int k = this.c.r();
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramList.size())
    {
      localObject1 = new long[7];
      int j = 0;
      while (j < 7)
      {
        localObject1[j] = 0L;
        j += 1;
      }
      localHashMap.put(((com.burockgames.timeclocker.history.a)paramList.get(i)).a(), localObject1);
      i += 1;
    }
    Object localObject2 = Calendar.getInstance();
    i = Integer.parseInt(new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis())));
    ((Calendar)localObject2).add(5, -6);
    if (i < k) {
      ((Calendar)localObject2).add(5, -1);
    }
    paramList = this.a.queryEvents(0L, System.currentTimeMillis());
    Object localObject1 = new UsageEvents.Event();
    long[] arrayOfLong = new long[7];
    i = 0;
    while (i < 7)
    {
      arrayOfLong[i] = ((Calendar)localObject2).get(5);
      ((Calendar)localObject2).add(5, 1);
      i += 1;
    }
    long l1 = -1L;
    while (paramList.hasNextEvent())
    {
      paramList.getNextEvent((UsageEvents.Event)localObject1);
      localObject2 = ((UsageEvents.Event)localObject1).getPackageName();
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append((String)localObject2);
      ((StringBuilder)localObject3).append("(&)");
      if (!str.contains(((StringBuilder)localObject3).toString())) {
        if (localHashMap.containsKey(localObject2))
        {
          localObject2 = (long[])localHashMap.get(localObject2);
          if (((UsageEvents.Event)localObject1).getEventType() == 1) {
            l1 = ((UsageEvents.Event)localObject1).getTimeStamp();
          } else if (((UsageEvents.Event)localObject1).getEventType() == 2)
          {
            if (l1 != -1L)
            {
              localObject3 = Calendar.getInstance();
              ((Calendar)localObject3).setTimeInMillis(l1);
              if (((Calendar)localObject3).get(11) < k) {
                ((Calendar)localObject3).add(5, -1);
              }
              i = ((Calendar)localObject3).get(5);
              long l2 = arrayOfLong[0];
              long l3 = i;
              i = 6;
              if (l2 == l3) {
                i = 0;
              } else if (arrayOfLong[1] == l3) {
                i = 1;
              } else if (arrayOfLong[2] == l3) {
                i = 2;
              } else if (arrayOfLong[3] == l3) {
                i = 3;
              } else if (arrayOfLong[4] == l3) {
                i = 4;
              } else if (arrayOfLong[5] == l3) {
                i = 5;
              } else if (arrayOfLong[6] != l3) {
                i = -1;
              }
              if (i != -1) {
                localObject2[i] += ((UsageEvents.Event)localObject1).getTimeStamp() - l1;
              }
              l1 = -1L;
            }
          }
          else {}
        }
      }
    }
    return localHashMap;
  }
  
  public long e(String paramString)
  {
    String str = this.c.c();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("---");
    if (!str.contains(localStringBuilder.toString())) {
      return 0L;
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("---");
    int i = str.indexOf(localStringBuilder.toString());
    int j = str.indexOf("(&)", i);
    return Long.parseLong(str.substring(i + paramString.length() + 3, j - 20));
  }
  
  public HashMap<com.burockgames.timeclocker.history.a, Long> e(List<com.burockgames.timeclocker.history.a> paramList)
  {
    Object localObject2 = this.c.b();
    int m = this.c.r();
    Object localObject1 = new HashMap();
    int i = 0;
    while (i < paramList.size())
    {
      ((HashMap)localObject1).put(((com.burockgames.timeclocker.history.a)paramList.get(i)).a(), Long.valueOf(0L));
      i += 1;
    }
    Object localObject6 = Calendar.getInstance();
    i = Integer.parseInt(new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis())));
    ((Calendar)localObject6).add(5, -6);
    int k = -1;
    if (i < m) {
      ((Calendar)localObject6).add(5, -1);
    }
    Object localObject3 = this.a.queryEvents(0L, System.currentTimeMillis());
    Object localObject4 = new UsageEvents.Event();
    Object localObject5 = new long[7];
    int j = 0;
    i = m;
    while (j < 7)
    {
      localObject5[j] = ((Calendar)localObject6).get(5);
      ((Calendar)localObject6).add(5, 1);
      j += 1;
    }
    long l2 = l();
    long l1 = -1L;
    Object localObject7 = localObject1;
    j = i;
    i = k;
    localObject1 = localObject2;
    while (((UsageEvents)localObject3).hasNextEvent())
    {
      ((UsageEvents)localObject3).getNextEvent((UsageEvents.Event)localObject4);
      localObject2 = ((UsageEvents.Event)localObject4).getPackageName();
      if (((UsageEvents.Event)localObject4).getTimeStamp() >= l2)
      {
        localObject6 = new StringBuilder();
        ((StringBuilder)localObject6).append((String)localObject2);
        ((StringBuilder)localObject6).append("(&)");
        if (!((String)localObject1).contains(((StringBuilder)localObject6).toString())) {
          if (localObject7.containsKey(localObject2)) {
            if (((UsageEvents.Event)localObject4).getEventType() == 1) {
              l1 = ((UsageEvents.Event)localObject4).getTimeStamp();
            } else if (((UsageEvents.Event)localObject4).getEventType() == 2)
            {
              if (l1 != -1L)
              {
                localObject6 = Calendar.getInstance();
                ((Calendar)localObject6).setTimeInMillis(l1);
                if (((Calendar)localObject6).get(11) < j) {
                  ((Calendar)localObject6).add(5, i);
                }
                i = ((Calendar)localObject6).get(5);
                long l3 = localObject5[0];
                long l4 = i;
                i = 6;
                if (l3 == l4) {
                  i = 0;
                } else if (localObject5[1] == l4) {
                  i = 1;
                } else if (localObject5[2] == l4) {
                  i = 2;
                } else if (localObject5[3] == l4) {
                  i = 3;
                } else if (localObject5[4] == l4) {
                  i = 4;
                } else if (localObject5[5] == l4) {
                  i = 5;
                } else if (localObject5[6] != l4) {
                  i = -1;
                }
                k = -1;
                if (i != -1) {
                  localObject7.put(localObject2, Long.valueOf(((Long)localObject7.get(localObject2)).longValue() + (((UsageEvents.Event)localObject4).getTimeStamp() - l1)));
                }
                l1 = -1L;
                i = k;
              }
            }
            else {}
          }
        }
      }
    }
    Object localObject8 = localObject7.entrySet().iterator();
    localObject6 = null;
    localObject4 = localObject6;
    localObject2 = localObject4;
    localObject1 = localObject2;
    localObject3 = localObject1;
    while (((Iterator)localObject8).hasNext())
    {
      localObject5 = (Map.Entry)((Iterator)localObject8).next();
      if ((localObject6 != null) && (((Long)((Map.Entry)localObject5).getValue()).longValue() <= ((Long)((Map.Entry)localObject6).getValue()).longValue()))
      {
        if ((localObject4 != null) && (((Long)((Map.Entry)localObject5).getValue()).longValue() <= ((Long)((Map.Entry)localObject4).getValue()).longValue()))
        {
          if ((localObject2 != null) && (((Long)((Map.Entry)localObject5).getValue()).longValue() <= ((Long)((Map.Entry)localObject2).getValue()).longValue()))
          {
            if ((localObject1 != null) && (((Long)((Map.Entry)localObject5).getValue()).longValue() <= ((Long)((Map.Entry)localObject1).getValue()).longValue()))
            {
              if ((localObject3 == null) || (((Long)((Map.Entry)localObject5).getValue()).longValue() > ((Long)((Map.Entry)localObject3).getValue()).longValue())) {
                localObject3 = localObject5;
              }
            }
            else
            {
              localObject3 = localObject1;
              localObject1 = localObject5;
            }
          }
          else
          {
            localObject3 = localObject1;
            localObject1 = localObject2;
            localObject2 = localObject5;
          }
        }
        else
        {
          localObject3 = localObject1;
          localObject1 = localObject2;
          localObject2 = localObject4;
          localObject4 = localObject5;
        }
      }
      else
      {
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject4;
        localObject4 = localObject6;
        localObject6 = localObject5;
      }
    }
    if (localObject6 != null) {
      localObject7.remove(((Map.Entry)localObject6).getKey());
    }
    if (localObject4 != null) {
      localObject7.remove(((Map.Entry)localObject4).getKey());
    }
    if (localObject2 != null) {
      localObject7.remove(((Map.Entry)localObject2).getKey());
    }
    if (localObject1 != null) {
      localObject7.remove(((Map.Entry)localObject1).getKey());
    }
    if (localObject3 != null) {
      localObject7.remove(((Map.Entry)localObject3).getKey());
    }
    localObject5 = new HashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localObject8 = (com.burockgames.timeclocker.history.a)paramList.next();
      if ((localObject6 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject6).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject6).getValue());
      } else if ((localObject4 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject4).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject4).getValue());
      } else if ((localObject2 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject2).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject2).getValue());
      } else if ((localObject1 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject1).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject1).getValue());
      } else if ((localObject3 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject3).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject3).getValue());
      }
    }
    paramList = localObject7.entrySet().iterator();
    for (l1 = 0L; paramList.hasNext(); l1 += ((Long)((Map.Entry)paramList.next()).getValue()).longValue()) {}
    ((HashMap)localObject5).put(new com.burockgames.timeclocker.history.a("com.burockgames.ot_her", this.b.getResources().getString(2131624171), null, android.support.v4.a.a.a(this.b, 2131230907)), Long.valueOf(l1));
    return localObject5;
  }
  
  public boolean e()
  {
    if (!f("com.burockgames.to_tal")) {
      return false;
    }
    long l1 = e("com.burockgames.to_tal");
    long l2 = d();
    if (l2 > l1)
    {
      a("com.burockgames.to_tal", l1);
      this.c.a("com.burockgames.to_tal", "", l2, l1);
      return true;
    }
    return false;
  }
  
  public HashMap<com.burockgames.timeclocker.history.a, Long> f(List<com.burockgames.timeclocker.history.a> paramList)
  {
    Object localObject2 = this.c.b();
    int m = this.c.r();
    Object localObject1 = new HashMap();
    int i = 0;
    while (i < paramList.size())
    {
      ((HashMap)localObject1).put(((com.burockgames.timeclocker.history.a)paramList.get(i)).a(), Long.valueOf(0L));
      i += 1;
    }
    Object localObject6 = Calendar.getInstance();
    i = Integer.parseInt(new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis())));
    ((Calendar)localObject6).add(5, -6);
    int k = -1;
    if (i < m) {
      ((Calendar)localObject6).add(5, -1);
    }
    Object localObject3 = this.a.queryEvents(0L, System.currentTimeMillis());
    Object localObject4 = new UsageEvents.Event();
    Object localObject5 = new long[7];
    int j = 0;
    i = m;
    while (j < 7)
    {
      localObject5[j] = ((Calendar)localObject6).get(5);
      ((Calendar)localObject6).add(5, 1);
      j += 1;
    }
    long l1 = -1L;
    Object localObject7 = localObject1;
    j = i;
    i = k;
    while (((UsageEvents)localObject3).hasNextEvent())
    {
      ((UsageEvents)localObject3).getNextEvent((UsageEvents.Event)localObject4);
      localObject1 = ((UsageEvents.Event)localObject4).getPackageName();
      localObject6 = new StringBuilder();
      ((StringBuilder)localObject6).append((String)localObject1);
      ((StringBuilder)localObject6).append("(&)");
      if (!((String)localObject2).contains(((StringBuilder)localObject6).toString())) {
        if (localObject7.containsKey(localObject1)) {
          if (((UsageEvents.Event)localObject4).getEventType() == 1) {
            l1 = ((UsageEvents.Event)localObject4).getTimeStamp();
          } else if (((UsageEvents.Event)localObject4).getEventType() == 2)
          {
            if (l1 != -1L)
            {
              localObject6 = Calendar.getInstance();
              ((Calendar)localObject6).setTimeInMillis(l1);
              if (((Calendar)localObject6).get(11) < j) {
                ((Calendar)localObject6).add(5, i);
              }
              i = ((Calendar)localObject6).get(5);
              long l2 = localObject5[0];
              long l3 = i;
              i = 6;
              if (l2 == l3) {
                i = 0;
              } else if (localObject5[1] == l3) {
                i = 1;
              } else if (localObject5[2] == l3) {
                i = 2;
              } else if (localObject5[3] == l3) {
                i = 3;
              } else if (localObject5[4] == l3) {
                i = 4;
              } else if (localObject5[5] == l3) {
                i = 5;
              } else if (localObject5[6] != l3) {
                i = -1;
              }
              k = -1;
              if (i != -1) {
                localObject7.put(localObject1, Long.valueOf(((Long)localObject7.get(localObject1)).longValue() + (((UsageEvents.Event)localObject4).getTimeStamp() - l1)));
              }
              l1 = -1L;
              i = k;
            }
          }
          else {}
        }
      }
    }
    Object localObject8 = localObject7.entrySet().iterator();
    localObject6 = null;
    localObject4 = localObject6;
    localObject2 = localObject4;
    localObject1 = localObject2;
    localObject3 = localObject1;
    while (((Iterator)localObject8).hasNext())
    {
      localObject5 = (Map.Entry)((Iterator)localObject8).next();
      if ((localObject6 != null) && (((Long)((Map.Entry)localObject5).getValue()).longValue() <= ((Long)((Map.Entry)localObject6).getValue()).longValue()))
      {
        if ((localObject4 != null) && (((Long)((Map.Entry)localObject5).getValue()).longValue() <= ((Long)((Map.Entry)localObject4).getValue()).longValue()))
        {
          if ((localObject2 != null) && (((Long)((Map.Entry)localObject5).getValue()).longValue() <= ((Long)((Map.Entry)localObject2).getValue()).longValue()))
          {
            if ((localObject1 != null) && (((Long)((Map.Entry)localObject5).getValue()).longValue() <= ((Long)((Map.Entry)localObject1).getValue()).longValue()))
            {
              if ((localObject3 == null) || (((Long)((Map.Entry)localObject5).getValue()).longValue() > ((Long)((Map.Entry)localObject3).getValue()).longValue())) {
                localObject3 = localObject5;
              }
            }
            else
            {
              localObject3 = localObject1;
              localObject1 = localObject5;
            }
          }
          else
          {
            localObject3 = localObject1;
            localObject1 = localObject2;
            localObject2 = localObject5;
          }
        }
        else
        {
          localObject3 = localObject1;
          localObject1 = localObject2;
          localObject2 = localObject4;
          localObject4 = localObject5;
        }
      }
      else
      {
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject4;
        localObject4 = localObject6;
        localObject6 = localObject5;
      }
    }
    if (localObject6 != null) {
      localObject7.remove(((Map.Entry)localObject6).getKey());
    }
    if (localObject4 != null) {
      localObject7.remove(((Map.Entry)localObject4).getKey());
    }
    if (localObject2 != null) {
      localObject7.remove(((Map.Entry)localObject2).getKey());
    }
    if (localObject1 != null) {
      localObject7.remove(((Map.Entry)localObject1).getKey());
    }
    if (localObject3 != null) {
      localObject7.remove(((Map.Entry)localObject3).getKey());
    }
    localObject5 = new HashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localObject8 = (com.burockgames.timeclocker.history.a)paramList.next();
      if ((localObject6 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject6).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject6).getValue());
      } else if ((localObject4 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject4).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject4).getValue());
      } else if ((localObject2 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject2).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject2).getValue());
      } else if ((localObject1 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject1).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject1).getValue());
      } else if ((localObject3 != null) && (((com.burockgames.timeclocker.history.a)localObject8).a().equals(((Map.Entry)localObject3).getKey()))) {
        ((HashMap)localObject5).put(localObject8, ((Map.Entry)localObject3).getValue());
      }
    }
    paramList = localObject7.entrySet().iterator();
    for (l1 = 0L; paramList.hasNext(); l1 += ((Long)((Map.Entry)paramList.next()).getValue()).longValue()) {}
    ((HashMap)localObject5).put(new com.burockgames.timeclocker.history.a("com.burockgames.ot_her", this.b.getResources().getString(2131624171), null, android.support.v4.a.a.a(this.b, 2131230907)), Long.valueOf(l1));
    return localObject5;
  }
  
  public List<com.burockgames.timeclocker.widget.c> f()
  {
    ArrayList localArrayList1 = new ArrayList();
    Object localObject1 = this.c.c();
    PackageManager localPackageManager = this.b.getPackageManager();
    Object localObject2 = localPackageManager.getInstalledApplications(128);
    ArrayList localArrayList2 = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      try
      {
        if (localPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName) != null)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(localApplicationInfo.packageName);
          localStringBuilder.append("---");
          if (((String)localObject1).contains(localStringBuilder.toString())) {
            localArrayList2.add(localApplicationInfo);
          }
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    int i = 0;
    while (i < localArrayList2.size())
    {
      localObject1 = (ApplicationInfo)localArrayList2.get(i);
      localObject2 = ((ApplicationInfo)localObject1).packageName;
      String str = ((ApplicationInfo)localObject1).loadLabel(localPackageManager).toString();
      try
      {
        long l = e(((ApplicationInfo)localObject1).packageName);
        if (f(((ApplicationInfo)localObject1).packageName)) {
          localArrayList1.add(new com.burockgames.timeclocker.widget.c((String)localObject2, str, l));
        }
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
      i += 1;
    }
    i(localArrayList1);
    return localArrayList1;
  }
  
  public boolean f(String paramString)
  {
    String str = this.c.c();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("---");
    boolean bool5 = str.contains(localStringBuilder.toString());
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool1 = false;
    boolean bool2 = false;
    if (!bool5) {
      return false;
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append("---");
    int m = str.indexOf("(&)", str.indexOf(localStringBuilder.toString()));
    int i = Integer.parseInt(str.substring(m - 13, m - 9));
    int j = Integer.parseInt(str.substring(m - 8, m - 6));
    int k = Integer.parseInt(str.substring(m - 5, m - 3));
    m = Integer.parseInt(str.substring(m - 2, m));
    paramString = Calendar.getInstance();
    int n = paramString.get(1);
    int i1 = paramString.get(2) + 1;
    int i2 = paramString.get(5);
    int i3 = paramString.get(11);
    int i4 = this.c.r();
    if (i < n) {
      return true;
    }
    if (i > n)
    {
      bool1 = bool2;
      if (j == 1)
      {
        bool1 = bool2;
        if (k == 1)
        {
          bool1 = bool2;
          if (m < i4)
          {
            bool1 = bool2;
            if (i3 >= i4) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    if (j < i1) {
      return true;
    }
    if (j > i1)
    {
      bool1 = bool3;
      if (k == 1)
      {
        bool1 = bool3;
        if (m < i4)
        {
          bool1 = bool3;
          if (i3 >= i4) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    if (k < i2) {
      return true;
    }
    if (k > i2)
    {
      if (k - 1 == i2)
      {
        bool1 = bool4;
        if (m < i4)
        {
          bool1 = bool4;
          if (i3 >= i4) {
            bool1 = true;
          }
        }
        return bool1;
      }
      return false;
    }
    if (m < i3) {
      return true;
    }
    if ((m >= i4) && (i3 >= i4)) {
      return true;
    }
    if (m < i4) {
      bool1 = true;
    }
    return bool1;
  }
  
  public List<com.burockgames.timeclocker.widget.c> g()
  {
    ArrayList localArrayList1 = new ArrayList();
    Object localObject1 = this.c.a();
    PackageManager localPackageManager = this.b.getPackageManager();
    Object localObject2 = localPackageManager.getInstalledApplications(128);
    ArrayList localArrayList2 = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      try
      {
        if (localPackageManager.getLaunchIntentForPackage(localApplicationInfo.packageName) != null)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(localApplicationInfo.packageName);
          localStringBuilder.append("(&)");
          if (((String)localObject1).contains(localStringBuilder.toString())) {
            localArrayList2.add(localApplicationInfo);
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    int i = 0;
    while (i < localArrayList2.size())
    {
      localObject1 = (ApplicationInfo)localArrayList2.get(i);
      localArrayList1.add(new com.burockgames.timeclocker.widget.c(((ApplicationInfo)localObject1).packageName, ((ApplicationInfo)localObject1).loadLabel(localPackageManager).toString(), 0L));
      i += 1;
    }
    j(localArrayList1);
    k(localArrayList1);
    return localArrayList1;
  }
  
  public boolean g(String paramString)
  {
    if (d(paramString) != 2) {
      return false;
    }
    long l1 = e(paramString);
    long l2 = j(paramString);
    if (l2 > l1) {}
    try
    {
      localObject = this.b.getPackageManager();
      localObject = ((PackageManager)localObject).getApplicationLabel(((PackageManager)localObject).getApplicationInfo(paramString, 0)).toString();
    }
    catch (Exception localException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = "-";
    a(paramString, l1);
    this.c.a(paramString, (String)localObject, l2, l1);
    return true;
    return false;
  }
  
  public void h()
  {
    try
    {
      Object localObject = new Intent(this.b, AlarmController.class);
      AlarmManager localAlarmManager = (AlarmManager)this.b.getSystemService("alarm");
      if (localAlarmManager == null)
      {
        h();
        return;
      }
      localObject = PendingIntent.getBroadcast(this.b, 2, (Intent)localObject, 134217728);
      localAlarmManager.set(1, System.currentTimeMillis() + 2500L, (PendingIntent)localObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    h();
  }
  
  public boolean h(String paramString)
  {
    if (d(paramString) != 1) {
      return false;
    }
    if (!f(paramString)) {
      return false;
    }
    long l1 = e(paramString);
    long l2 = j(paramString);
    if (l2 > l1) {}
    try
    {
      localObject = this.b.getPackageManager();
      localObject = ((PackageManager)localObject).getApplicationLabel(((PackageManager)localObject).getApplicationInfo(paramString, 0)).toString();
    }
    catch (Exception localException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = "-";
    this.c.a(paramString, (String)localObject, l2, l1);
    return true;
    return false;
  }
  
  public boolean i(String paramString)
  {
    if (d(paramString) != 0) {
      return false;
    }
    if (!f(paramString)) {
      return false;
    }
    long l1 = e(paramString);
    long l2 = j(paramString);
    if (l2 > l1) {}
    try
    {
      localObject = this.b.getPackageManager();
      localObject = ((PackageManager)localObject).getApplicationLabel(((PackageManager)localObject).getApplicationInfo(paramString, 0)).toString();
    }
    catch (Exception localException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = "-";
    a(paramString, l1);
    this.c.a(paramString, (String)localObject, l2, l1);
    return true;
    return false;
  }
  
  public void j()
  {
    try
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (System.currentTimeMillis() - b.a > 180000L)
          {
            if (!f.this.c().equals("com.burockgames.timeclocker")) {
              return;
            }
            if ((f.k() != null) && (f.k().a()))
            {
              f.k().b();
              b.a = System.currentTimeMillis();
            }
            return;
          }
        }
      }, 15000L);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}
