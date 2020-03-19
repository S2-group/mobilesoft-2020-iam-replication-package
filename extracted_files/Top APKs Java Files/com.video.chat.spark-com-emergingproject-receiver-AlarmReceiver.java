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
import com.emergingproject.i.c;
import com.emergingproject.l.ab;
import com.emergingproject.l.ak;
import com.emergingproject.l.ap;
import com.emergingproject.l.av;
import com.emergingproject.l.aw;
import com.emergingproject.l.bi;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.a.a.e.h;
import org.a.a.g;

public class AlarmReceiver
  extends BroadcastReceiver
{
  public AlarmReceiver() {}
  
  private void a()
  {
    List localList = com.emergingproject.ui.widget.chatinputview.expressionstore.d.d.a();
    if ((localList != null) && (!localList.isEmpty()))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localList.size());
      localStringBuilder.append("");
      com.emergingproject.statistics.d.a("event_state_user_expression_count", new android.support.v4.f.j[] { new android.support.v4.f.j("count", localStringBuilder.toString()) });
      return;
    }
    com.emergingproject.statistics.d.a("event_state_user_expression_count", new android.support.v4.f.j[] { new android.support.v4.f.j("count", "0") });
  }
  
  private void a(Context paramContext)
  {
    paramContext = CustomApp.a().b().g();
    org.a.a.a.e localE = CustomApp.a().b().o();
    localE.b(new org.a.a.a.d()
    {
      public void a(org.a.a.a.b paramAnonymousB)
      {
        paramAnonymousB = (List)paramAnonymousB.a();
        int i;
        if (paramAnonymousB != null) {
          i = paramAnonymousB.size();
        } else {
          i = 0;
        }
        paramAnonymousB = new HashMap();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(i);
        localStringBuilder.append("");
        paramAnonymousB.put("size", localStringBuilder.toString());
        com.emergingproject.statistics.d.a("sessionst", paramAnonymousB);
      }
    });
    localE.a(paramContext.g().a(SessionDao.Properties.c.a(Long.valueOf(bi.b())), new org.a.a.e.j[0]).b(new g[] { SessionDao.Properties.e }).a());
  }
  
  private void b()
  {
    Object localObject = com.emergingproject.f.b.a().b(bi.b());
    if (localObject == null) {
      return;
    }
    int i;
    if (!TextUtils.isEmpty(((com.emergingproject.c.g.e)localObject).d())) {
      i = 1;
    } else {
      i = 0;
    }
    localObject = ((com.emergingproject.c.g.e)localObject).y();
    int j = i;
    if (localObject != null) {
      j = i + ((List)localObject).size();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(j);
    ((StringBuilder)localObject).append("");
    com.emergingproject.statistics.d.a("user_profile_count", "count", ((StringBuilder)localObject).toString());
  }
  
  private void b(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("fb_isinstall", String.valueOf(aw.b(paramContext, "com.facebook.katana")));
    localHashMap.put("gp_isinstall", String.valueOf(aw.b(paramContext, "com.android.vending")));
    com.emergingproject.statistics.d.a("fb_gp_installsta", localHashMap);
  }
  
  private void c()
  {
    MatchLogDao localMatchLogDao = CustomApp.a().b().d();
    org.a.a.a.e localE = CustomApp.a().b().o();
    localE.b(new org.a.a.a.d()
    {
      public void a(org.a.a.a.b paramAnonymousB)
      {
        paramAnonymousB = (List)paramAnonymousB.a();
        int i;
        if (paramAnonymousB != null) {
          i = paramAnonymousB.size();
        } else {
          i = 0;
        }
        paramAnonymousB = new StringBuilder();
        paramAnonymousB.append("reportMatchHistoryCount() count:");
        paramAnonymousB.append(i);
        ak.c("AlarmReceiver", paramAnonymousB.toString());
        paramAnonymousB = new HashMap();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(i);
        localStringBuilder.append("");
        paramAnonymousB.put("size", localStringBuilder.toString());
        com.emergingproject.statistics.d.a("match_history_count", paramAnonymousB);
      }
    });
    localE.a(localMatchLogDao.g().a(MatchLogDao.Properties.b.a(Long.valueOf(bi.b())), new org.a.a.e.j[0]).a());
  }
  
  private void c(Context paramContext)
  {
    if (bi.b() > 0L)
    {
      paramContext = new HashMap();
      paramContext.put("score", bi.B());
      com.emergingproject.statistics.d.a("score_state", paramContext);
    }
  }
  
  private void d()
  {
    if (!TextUtils.isEmpty(bi.c()))
    {
      org.a.a.a.e localE = CustomApp.a().b().o();
      localE.b(new org.a.a.a.d()
      {
        public void a(org.a.a.a.b paramAnonymousB)
        {
          paramAnonymousB = (List)paramAnonymousB.a();
          HashMap localHashMap = new HashMap();
          localHashMap.put("nums", String.valueOf(paramAnonymousB.size()));
          com.emergingproject.statistics.d.a("friends_num", localHashMap);
        }
      });
      localE.a(CustomApp.a().b().k().g().a(UserDao.Properties.s.a(Integer.valueOf(2)), new org.a.a.e.j[] { UserDao.Properties.c.a(Long.valueOf(bi.b())), UserDao.Properties.b.b(Integer.valueOf(100)) }).a());
    }
  }
  
  private void d(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    int j = 0;
    Object localObject1 = paramContext.getInstalledPackages(0);
    if (localObject1 != null)
    {
      if (((List)localObject1).size() == 0) {
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
      while (i < k)
      {
        Object localObject2 = localObject1[i];
        if (paramContext.contains(localObject2)) {
          localHashMap.put(localObject2, "true");
        } else {
          localHashMap.put(localObject2, "false");
        }
        i += 1;
      }
      com.emergingproject.statistics.d.a("competitive_product", localHashMap);
      return;
    }
  }
  
  private void e()
  {
    if (ap.c(CustomApp.a()))
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("type", ap.a());
      com.emergingproject.statistics.d.a("ip_type", localHashMap);
    }
  }
  
  private void f()
  {
    HashMap localHashMap = new HashMap();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(av.a());
    localStringBuilder.append("");
    localHashMap.put("selected_filter", localStringBuilder.toString());
    localHashMap.put("selected_sticker", c.h());
    com.emergingproject.statistics.d.a("video_filter_type", localHashMap);
  }
  
  private void g()
  {
    String str1 = bi.q();
    String str2 = bi.r();
    String str3 = bi.s();
    String str4 = bi.u();
    String str5 = bi.v();
    if ((TextUtils.isEmpty(str1)) && (TextUtils.isEmpty(str2)) && (TextUtils.isEmpty(str3)) && (TextUtils.isEmpty(str4)) && (TextUtils.isEmpty(str5)))
    {
      com.emergingproject.statistics.d.a("state_user_profile_has_info", "hasInfo", "false");
      return;
    }
    com.emergingproject.statistics.d.a("state_user_profile_has_info", "hasInfo", "true");
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.video.chat.spark.action.TWELVE_HOURS_ALARM".equals(paramIntent.getAction()))
    {
      ak.c("AlarmReceiver", "daily report trigger");
      long l = ab.q(paramContext);
      l = System.currentTimeMillis() - l;
      if (l > 43200000L)
      {
        com.emergingproject.statistics.d.b();
        com.emergingproject.statistics.b.a(paramContext);
        f();
        l = ab.r(paramContext);
        if (l % 2L == 0L)
        {
          c();
          a(paramContext);
          b(paramContext);
          c(paramContext);
          d(paramContext);
          d();
          e();
          ab.f(paramContext, System.currentTimeMillis());
          b();
          g();
          a();
        }
        ab.g(paramContext, l + 1L);
        ak.c("AlarmReceiver", "daily report done");
        return;
      }
      paramContext = new StringBuilder();
      paramContext.append("daily report refused because timeDiff is ");
      paramContext.append(DateUtils.formatElapsedTime(l / 1000L));
      paramContext.append(",less than half day");
      ak.c("AlarmReceiver", paramContext.toString());
    }
  }
}
