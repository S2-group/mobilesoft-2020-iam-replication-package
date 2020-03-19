package com.emergingproject.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import com.emergingproject.CustomApp;
import com.emergingproject.dao.MatchLogDao;
import com.emergingproject.dao.MatchLogDao.Properties;
import com.emergingproject.dao.SessionDao;
import com.emergingproject.dao.SessionDao.Properties;
import com.emergingproject.dao.UserDao;
import com.emergingproject.dao.UserDao.Properties;
import com.emergingproject.i.a;
import com.emergingproject.l.ae;
import com.emergingproject.l.aj;
import com.emergingproject.l.ao;
import com.emergingproject.l.ap;
import com.emergingproject.l.az;
import com.emergingproject.l.v;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.a.a.a.d;
import org.a.a.e.h;
import org.a.a.e.j;
import org.a.a.g;

public class AlarmReceiver
  extends BroadcastReceiver
{
  public AlarmReceiver() {}
  
  private void a()
  {
    Object localObject = com.emergingproject.e.b.a().a(az.b());
    int i = 0;
    if (localObject == null) {
      return;
    }
    if (!TextUtils.isEmpty(((com.emergingproject.c.f.c)localObject).d())) {
      i = 1;
    }
    localObject = ((com.emergingproject.c.f.c)localObject).x();
    int j = i;
    if (localObject != null) {
      j = i + ((List)localObject).size();
    }
    com.emergingproject.i.c.a("user_profile_count", "count", j + "");
  }
  
  private void a(Context paramContext)
  {
    paramContext = CustomApp.a().b().f();
    org.a.a.a.e localE = CustomApp.a().b().l();
    localE.b(new d()
    {
      public void a(org.a.a.a.b paramAnonymousB)
      {
        paramAnonymousB = (List)paramAnonymousB.a();
        if (paramAnonymousB != null) {}
        for (int i = paramAnonymousB.size();; i = 0)
        {
          paramAnonymousB = new HashMap();
          paramAnonymousB.put("size", i + "");
          com.emergingproject.i.c.a("sessionst", paramAnonymousB);
          return;
        }
      }
    });
    localE.a(paramContext.g().a(SessionDao.Properties.c.a(Long.valueOf(az.b())), new j[0]).b(new g[] { SessionDao.Properties.e }).a());
  }
  
  private void b()
  {
    MatchLogDao localMatchLogDao = CustomApp.a().b().c();
    org.a.a.a.e localE = CustomApp.a().b().l();
    localE.b(new d()
    {
      public void a(org.a.a.a.b paramAnonymousB)
      {
        paramAnonymousB = (List)paramAnonymousB.a();
        if (paramAnonymousB != null) {}
        for (int i = paramAnonymousB.size();; i = 0)
        {
          ae.c("AlarmReceiver", "reportMatchHistoryCount() count:" + i);
          paramAnonymousB = new HashMap();
          paramAnonymousB.put("size", i + "");
          com.emergingproject.i.c.a("match_history_count", paramAnonymousB);
          return;
        }
      }
    });
    localE.a(localMatchLogDao.g().a(MatchLogDao.Properties.b.a(Long.valueOf(az.b())), new j[0]).a());
  }
  
  private void b(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("fb_isinstall", String.valueOf(ap.b(paramContext, "com.facebook.katana")));
    localHashMap.put("gp_isinstall", String.valueOf(ap.b(paramContext, "com.android.vending")));
    com.emergingproject.i.c.a("fb_gp_installsta", localHashMap);
  }
  
  private void c()
  {
    if (!TextUtils.isEmpty(az.c()))
    {
      org.a.a.a.e localE = CustomApp.a().b().l();
      localE.b(new d()
      {
        public void a(org.a.a.a.b paramAnonymousB)
        {
          paramAnonymousB = (List)paramAnonymousB.a();
          HashMap localHashMap = new HashMap();
          localHashMap.put("nums", String.valueOf(paramAnonymousB.size()));
          com.emergingproject.i.c.a("friends_num", localHashMap);
        }
      });
      localE.a(CustomApp.a().b().i().g().a(UserDao.Properties.m.a(Integer.valueOf(2)), new j[] { UserDao.Properties.c.a(Long.valueOf(az.b())), UserDao.Properties.b.b(Integer.valueOf(100)) }).a());
    }
  }
  
  private void c(Context paramContext)
  {
    if (az.b() > 0L)
    {
      paramContext = new HashMap();
      paramContext.put("score", az.w());
      com.emergingproject.i.c.a("score_state", paramContext);
    }
  }
  
  private void d()
  {
    if (aj.b(CustomApp.a()))
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("type", aj.a());
      com.emergingproject.i.c.a("ip_type", localHashMap);
    }
  }
  
  private void d(Context paramContext)
  {
    int j = 0;
    Object localObject1 = paramContext.getPackageManager().getInstalledPackages(0);
    if ((localObject1 == null) || (((List)localObject1).size() == 0)) {
      return;
    }
    paramContext = new HashSet();
    int i = 0;
    while (i < ((List)localObject1).size())
    {
      paramContext.add(((PackageInfo)((List)localObject1).get(i)).packageName.toLowerCase());
      i += 1;
    }
    localObject1 = new String[6];
    localObject1[0] = "com.exutech.chacha";
    localObject1[1] = "com.azarlive.android";
    localObject1[2] = "com.buddy.tiki";
    localObject1[3] = "com.mesh.video";
    localObject1[4] = "com.wescan.alo";
    localObject1[5] = "com.sgiggle.production";
    HashMap localHashMap = new HashMap();
    int k = localObject1.length;
    i = j;
    if (i < k)
    {
      Object localObject2 = localObject1[i];
      if (paramContext.contains(localObject2)) {
        localHashMap.put(localObject2, "true");
      }
      for (;;)
      {
        i += 1;
        break;
        localHashMap.put(localObject2, "false");
      }
    }
    com.emergingproject.i.c.a("competitive_product", localHashMap);
  }
  
  private void e()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("selected_filter", ao.a() + "");
    localHashMap.put("selected_sticker", com.emergingproject.h.e.g());
    com.emergingproject.i.c.a("video_filter_type", localHashMap);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    long l;
    if ("com.meet.android.action.TWELVE_HOURS_ALARM".equals(paramIntent.getAction()))
    {
      ae.c("AlarmReceiver", "daily report trigger");
      l = v.r(paramContext);
      l = System.currentTimeMillis() - l;
      if (l > 43200000L)
      {
        com.emergingproject.i.c.b();
        a.a(paramContext);
        e();
        l = v.s(paramContext);
        if (l % 2L == 0L)
        {
          b();
          a(paramContext);
          b(paramContext);
          c(paramContext);
          d(paramContext);
          c();
          d();
          v.g(paramContext, System.currentTimeMillis());
          a();
        }
        v.h(paramContext, l + 1L);
        ae.c("AlarmReceiver", "daily report done");
      }
    }
    else
    {
      return;
    }
    ae.c("AlarmReceiver", "daily report refused because timeDiff is " + DateUtils.formatElapsedTime(l / 1000L) + ",less than half day");
  }
}
