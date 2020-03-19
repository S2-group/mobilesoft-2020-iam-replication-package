package com.straighttalk.straighttalkwifi2;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aq
{
  private static ch d = null;
  protected Long a = Long.valueOf(0L);
  protected Long b = Long.valueOf(10000L);
  protected Long c = Long.valueOf(10000L);
  private Long e = Long.valueOf(0L);
  private Integer f = Integer.valueOf(0);
  private String g = "";
  private Long h = Long.valueOf(0L);
  private Long i = Long.valueOf(0L);
  private Boolean j = Boolean.valueOf(false);
  private Boolean k = Boolean.valueOf(false);
  private Boolean l = Boolean.valueOf(false);
  private Boolean m = Boolean.valueOf(false);
  private Boolean n = Boolean.valueOf(false);
  private Boolean o = Boolean.valueOf(false);
  private Boolean p = Boolean.valueOf(true);
  private Boolean q = Boolean.valueOf(true);
  private Long r = Long.valueOf(0L);
  
  public aq(ch paramCh)
  {
    try
    {
      d = paramCh;
      return;
    }
    catch (Exception paramCh)
    {
      ce.a(paramCh);
    }
  }
  
  public Boolean a(Boolean paramBoolean)
  {
    for (;;)
    {
      Boolean localBoolean2;
      try
      {
        localBoolean2 = this.q;
        if ((paramBoolean.booleanValue()) && (d.g().j().booleanValue()))
        {
          this.r = Long.valueOf(0L);
          paramBoolean = Boolean.valueOf(true);
          return paramBoolean;
        }
        if (d.F().i().length() > 0)
        {
          Pattern localPattern = Pattern.compile(d.F().i());
          if (this.r.longValue() < hk.e().longValue() - this.c.longValue())
          {
            this.q = Boolean.valueOf(true);
            this.r = hk.e();
          }
          try
          {
            paramBoolean = Long.valueOf(0L);
            localObject1 = "";
            localObject2 = "";
            PackageManager localPackageManager = ch.d().getPackageManager();
            Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
            if (localIterator.hasNext())
            {
              Object localObject3 = (ApplicationInfo)localIterator.next();
              String str;
              Object localObject5;
              int i1;
              long l1;
              try
              {
                d.g();
                str = h.a(Integer.valueOf(((ApplicationInfo)localObject3).uid));
                if (!localPattern.matcher(str).matches()) {
                  break label681;
                }
                localObject5 = localPackageManager.getPackageInfo(str, 2);
                if (localObject5 == null) {
                  break label681;
                }
                localObject3 = ((PackageInfo)localObject5).receivers;
                if (localObject3 == null) {
                  break label681;
                }
                int i2 = localObject3.length;
                i1 = 0;
                if (i1 >= i2) {
                  break label681;
                }
                if (localObject3[i1].name.contains("BxReceiver"))
                {
                  if ((!((PackageInfo)localObject5).applicationInfo.enabled) || ((((PackageInfo)localObject5).applicationInfo.flags & 0x200000) != 0)) {
                    break label681;
                  }
                  if (BxService.a(ch.d(), str).booleanValue())
                  {
                    StringBuilder localStringBuilder = new StringBuilder().append((String)localObject2);
                    if (((String)localObject2).length() > 0)
                    {
                      localObject3 = "; ";
                      localObject3 = (String)localObject3 + str + " (running)";
                      localObject2 = localObject3;
                    }
                  }
                }
              }
              catch (Exception localException1) {}
            }
          }
          catch (Exception paramBoolean)
          {
            Object localObject2;
            Object localObject4;
            ce.a(paramBoolean);
            continue;
          }
        }
      }
      finally {}
      try
      {
        if (paramBoolean.longValue() >= ((PackageInfo)localObject5).lastUpdateTime) {
          break label668;
        }
        l1 = ((PackageInfo)localObject5).lastUpdateTime;
        localObject1 = Long.valueOf(l1);
        paramBoolean = str;
      }
      catch (Exception localException2)
      {
        continue;
        localBoolean1 = paramBoolean;
        paramBoolean = (Boolean)localObject1;
        localObject1 = localBoolean1;
        continue;
      }
      localObject3 = localObject1;
      Object localObject1 = paramBoolean;
      paramBoolean = (Boolean)localObject3;
      continue;
      localObject3 = "";
      continue;
      localObject5 = new StringBuilder().append((String)localObject2);
      if (((String)localObject2).length() > 0)
      {
        localObject3 = "; ";
        str = (String)localObject3 + str + " (not running)";
        localObject3 = paramBoolean;
        localObject2 = str;
        paramBoolean = (Boolean)localObject1;
        localObject1 = localObject3;
      }
      else
      {
        localObject3 = "";
        continue;
        i1 += 1;
        continue;
        localObject4 = paramBoolean;
        paramBoolean = (Boolean)localObject1;
        localObject1 = localObject4;
        continue;
        if (((String)localObject1).length() > 0)
        {
          if (((String)localObject1).equals(h.b())) {
            this.q = Boolean.valueOf(true);
          }
        }
        else
        {
          if (localBoolean2 != this.q)
          {
            localObject4 = new StringBuilder().append("APP Client is now ");
            if (!this.q.booleanValue()) {
              break label613;
            }
            paramBoolean = "active";
            ce.b(paramBoolean + ": " + (String)localObject2);
          }
          paramBoolean = this.q;
          continue;
        }
        this.q = Boolean.valueOf(false);
        continue;
        label613:
        paramBoolean = "passive, yielding to " + (String)localObject1;
        continue;
        this.q = Boolean.valueOf(true);
        if (!localBoolean2.booleanValue())
        {
          ce.b("APP Client is now active");
          continue;
          label668:
          label681:
          Boolean localBoolean1 = paramBoolean;
          paramBoolean = (Boolean)localObject1;
          localObject1 = localBoolean1;
        }
      }
    }
  }
  
  public void a()
  {
    this.f = Integer.valueOf(d.A().a("AutoWifiSuspendedReason", this.f.intValue()));
    this.e = Long.valueOf(d.A().a("AutoWifiSuspendedTicks", this.e.longValue()));
    this.g = d.A().a("AutoWifiSuspendedSSID", this.g);
    if (d.A().a("XAutoWifiSuspendedSSID", "").length() > 0) {}
    try
    {
      this.g = bf.b(bf.c(), d.A().a("XAutoWifiSuspendedSSID", ""));
      this.i = Long.valueOf(d.A().a("AutoWifiSuspendedSSIDTicks", this.i.longValue()));
      this.j = Boolean.valueOf(d.A().a("AutoWifiSuspendedScreenOn", this.j.booleanValue()));
      this.k = Boolean.valueOf(d.A().a("AutoWifiSuspendedScreenOff", this.k.booleanValue()));
      this.l = Boolean.valueOf(d.A().a("AutoWifiSuspendedForeground", this.l.booleanValue()));
      this.m = Boolean.valueOf(d.A().a("AutoWifiSuspendedRadioOn", this.m.booleanValue()));
      this.n = Boolean.valueOf(d.A().a("AutoWifiSuspendedRadioOff", this.n.booleanValue()));
      this.o = Boolean.valueOf(d.A().a("AutoWifiSuspendedConnected", this.o.booleanValue()));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ce.a(localException, Boolean.valueOf(false));
      }
    }
  }
  
  public void a(Long paramLong, Integer paramInteger)
  {
    a(paramLong, "", paramInteger);
  }
  
  public void a(Long paramLong, String paramString, Integer paramInteger)
  {
    if ((d.F().af().booleanValue()) && (paramInteger.intValue() != 0)) {
      ce.b("APP Won't suspend due to test mode");
    }
    for (;;)
    {
      return;
      Boolean localBoolean = b();
      this.f = paramInteger;
      if (paramLong.longValue() == Long.MAX_VALUE)
      {
        this.e = paramLong;
        this.g = paramString;
        this.h = Long.valueOf(0L);
        this.i = this.e;
        this.j = Boolean.valueOf(false);
        this.k = Boolean.valueOf(false);
        this.l = Boolean.valueOf(false);
        this.m = Boolean.valueOf(false);
        this.n = Boolean.valueOf(false);
        this.o = Boolean.valueOf(false);
        d.A().b("AutoWifiSuspendedReason", this.f.intValue());
        d.A().b("AutoWifiSuspendedTicks", this.e.longValue());
      }
      try
      {
        d.A().b("XAutoWifiSuspendedSSID", bf.a(bf.c(), this.g));
        d.A().b("AutoWifiSuspendedSSIDTicks", this.i.longValue());
        d.A().b("AutoWifiSuspendedScreenOn", this.j.booleanValue());
        d.A().b("AutoWifiSuspendedScreenOff", this.k.booleanValue());
        d.A().b("AutoWifiSuspendedForeground", this.l.booleanValue());
        d.A().b("AutoWifiSuspendedRadioOn", this.m.booleanValue());
        d.A().b("AutoWifiSuspendedRadioOff", this.n.booleanValue());
        d.A().b("AutoWifiSuspendedConnected", this.o.booleanValue());
        d.A().b();
        d.l().u();
        d.l().r();
        if (!localBoolean.booleanValue()) {
          bg.b();
        }
        d.E().a(Boolean.valueOf(true));
        if (d.f() == null) {
          continue;
        }
        d.f().i();
        return;
        this.e = Long.valueOf(hk.e().longValue() + paramLong.longValue());
      }
      catch (Exception paramLong)
      {
        for (;;)
        {
          ce.a(paramLong);
        }
      }
    }
  }
  
  public Boolean b()
  {
    if (e().longValue() > 0L) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  public Integer c()
  {
    if ((this.e.longValue() == 0L) && (!r().booleanValue())) {
      return Integer.valueOf(4);
    }
    return this.f;
  }
  
  public String d()
  {
    return this.g;
  }
  
  public Long e()
  {
    n();
    if (this.e.longValue() == Long.MAX_VALUE) {
      return this.e;
    }
    if (this.e.longValue() > hk.e().longValue()) {
      return Long.valueOf(this.e.longValue() - hk.e().longValue());
    }
    if (!r().booleanValue()) {
      return Long.valueOf(1L);
    }
    if ((this.g.length() > 0) && (this.e.longValue() > 0L))
    {
      if ((this.h.longValue() > 0L) && (this.h.longValue() < hk.e().longValue() - d.F().v().longValue())) {
        this.h = Long.valueOf(0L);
      }
      if (this.h.longValue() == 0L)
      {
        this.h = hk.e();
        this.e = Long.valueOf(hk.e().longValue() + d.F().v().longValue());
        d.A().b("AutoWifiSuspendedTicks", this.e.longValue());
        d.A().b();
        new ar(this).execute(new Void[0]);
      }
      return Long.valueOf(1L);
    }
    if ((this.f.intValue() == 3) && (d.r().d().intValue() < d.F().q().intValue()))
    {
      this.e = Long.valueOf(hk.e().longValue() + d.F().r().longValue());
      d.A().b("AutoWifiSuspendedTicks", this.e.longValue());
      d.A().b();
      StringBuilder localStringBuilder = new StringBuilder().append("APP Suspend due to battery level: ").append(d.r().d().toString());
      if (d.r().c().booleanValue()) {}
      for (String str = "*";; str = "")
      {
        ce.b(str);
        return Long.valueOf(this.e.longValue() - hk.e().longValue());
      }
    }
    return Long.valueOf(0L);
  }
  
  public void f()
  {
    Boolean localBoolean = b();
    this.f = Integer.valueOf(0);
    this.e = Long.valueOf(0L);
    this.g = "";
    this.h = Long.valueOf(0L);
    this.i = Long.valueOf(0L);
    this.j = Boolean.valueOf(false);
    this.k = Boolean.valueOf(false);
    this.l = Boolean.valueOf(false);
    this.m = Boolean.valueOf(false);
    this.n = Boolean.valueOf(false);
    this.o = Boolean.valueOf(false);
    d.A().b("AutoWifiSuspendedReason", this.f.intValue());
    d.A().b("AutoWifiSuspendedTicks", this.e.longValue());
    try
    {
      d.A().b("XAutoWifiSuspendedSSID", bf.a(bf.c(), this.g));
      d.A().b("AutoWifiSuspendedSSIDTicks", this.i.longValue());
      d.A().b("AutoWifiSuspendedScreenOn", this.j.booleanValue());
      d.A().b("AutoWifiSuspendedScreenOff", this.k.booleanValue());
      d.A().b("AutoWifiSuspendedForeground", this.l.booleanValue());
      d.A().b("AutoWifiSuspendedRadioOn", this.m.booleanValue());
      d.A().b("AutoWifiSuspendedRadioOff", this.n.booleanValue());
      d.A().b("AutoWifiSuspendedConnected", this.o.booleanValue());
      d.A().b();
      if (localBoolean.booleanValue()) {
        bg.c();
      }
      d.E().a(Boolean.valueOf(true));
      if (d.f() != null) {
        d.f().a(Boolean.valueOf(false));
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ce.a(localException);
      }
    }
  }
  
  public void g()
  {
    this.l = Boolean.valueOf(true);
    d.A().b("AutoWifiSuspendedForeground", this.l.booleanValue());
    d.A().b();
  }
  
  public void h()
  {
    this.m = Boolean.valueOf(true);
    d.A().b("AutoWifiSuspendedRadioOn", this.m.booleanValue());
    d.A().b();
  }
  
  public void i()
  {
    this.n = Boolean.valueOf(true);
    d.A().b("AutoWifiSuspendedRadioOff", this.n.booleanValue());
    d.A().b();
  }
  
  public void j()
  {
    this.j = Boolean.valueOf(true);
    d.A().b("AutoWifiSuspendedScreenOn", this.j.booleanValue());
    d.A().b();
  }
  
  public void k()
  {
    this.k = Boolean.valueOf(true);
    d.A().b("AutoWifiSuspendedScreenOff", this.k.booleanValue());
    d.A().b();
  }
  
  public void l()
  {
    this.o = Boolean.valueOf(true);
    d.A().b("AutoWifiSuspendedConnected", this.o.booleanValue());
    d.A().b();
  }
  
  public void m()
  {
    this.h = Long.valueOf(0L);
  }
  
  public void n()
  {
    try
    {
      if (this.i.longValue() == 0L)
      {
        this.i = this.e;
        d.A().b("AutoWifiSuspendedSSIDTicks", this.i.longValue());
        d.A().b();
      }
      if ((this.e.longValue() > 0L) && (this.g.length() > 0) && (d.J().d().booleanValue()) && (d.J().c("", this.g).booleanValue()))
      {
        this.i = hk.e();
        d.A().b("AutoWifiSuspendedSSIDTicks", this.i.longValue());
        d.A().b();
      }
      return;
    }
    catch (Exception localException)
    {
      ce.a(localException, Boolean.valueOf(false));
    }
  }
  
  public void o()
  {
    try
    {
      if (this.e.longValue() > 0L)
      {
        Boolean localBoolean2 = Boolean.valueOf(false);
        n();
        Boolean localBoolean1;
        if ((d.J().q().booleanValue()) && (this.f.intValue() == 2))
        {
          ce.b("APP Access point mode, so resume");
          localBoolean1 = Boolean.valueOf(true);
        }
        while (localBoolean1.booleanValue())
        {
          this.e = Long.valueOf(Long.MAX_VALUE);
          d.A().b("AutoWifiSuspendedTicks", this.e.longValue());
          d.A().b();
          f();
          d.h().a(Long.valueOf(0L));
          return;
          if ((this.j.booleanValue()) && (d.r().h().booleanValue()))
          {
            ce.b("APP Screen is on, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.k.booleanValue()) && (d.r().i().booleanValue()))
          {
            ce.b("APP Screen is off, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.l.booleanValue()) && (d.g().j().booleanValue()))
          {
            ce.b("APP App is in foreground, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.m.booleanValue()) && (d.J().d().booleanValue()))
          {
            ce.b("APP Radio is on, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.n.booleanValue()) && (!d.J().d().booleanValue()))
          {
            ce.b("APP Radio is off, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.o.booleanValue()) && (d.J().h().booleanValue()))
          {
            ce.b("APP Device is connected, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else
          {
            localBoolean1 = localBoolean2;
            if (this.g.length() > 0)
            {
              localBoolean1 = localBoolean2;
              if (this.i.longValue() < hk.e().longValue() - d.F().w().longValue())
              {
                ce.b("APP Hotspot " + this.g + " has not been seen for " + bi.d(d.F().w(), Boolean.valueOf(false)) + ", last seen " + bi.a(this.i) + ", so resume");
                localBoolean1 = Boolean.valueOf(true);
              }
            }
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      ce.a(localException, Boolean.valueOf(false));
    }
  }
  
  public as p()
  {
    return new as(this);
  }
  
  public at q()
  {
    return new at(this);
  }
  
  public Boolean r()
  {
    Object localObject1 = this.p;
    try
    {
      Object localObject2;
      Long localLong;
      Double localDouble1;
      Double localDouble2;
      Object localObject3;
      if (this.a.longValue() < hk.e().longValue() - this.b.longValue())
      {
        this.p = Boolean.valueOf(true);
        this.a = hk.e();
        localObject2 = hk.e();
        localLong = Long.valueOf(((Long)localObject2).longValue() / 86400000L * 86400000L);
        localDouble1 = d.v().k();
        localDouble2 = d.v().l();
        if (d.F().ax().booleanValue())
        {
          this.p = Boolean.valueOf(false);
          localObject3 = d.F().ay();
          localObject4 = d.F().az();
          localObject5 = "|" + d.F().aA().toLowerCase() + "|";
          if ((!((Boolean)localObject3).booleanValue()) || (!d.x().j().booleanValue())) {
            break label1189;
          }
        }
      }
      for (this.p = Boolean.valueOf(true);; this.p = Boolean.valueOf(true))
      {
        Object localObject6;
        if (!this.p.booleanValue())
        {
          localObject3 = d.x().o();
          if (((String)localObject3).length() > 0)
          {
            localObject4 = ((String)localObject3).split(":");
            localObject3 = localObject4[0];
            localObject4 = localObject4[1];
            localObject5 = d.F().aB().iterator();
            while (((Iterator)localObject5).hasNext())
            {
              localObject6 = (as)((Iterator)localObject5).next();
              if ((((Long)localObject2).longValue() >= localLong.longValue() + ((as)localObject6).b.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((as)localObject6).c.longValue()))
              {
                localObject6 = "|" + ((as)localObject6).a + "|";
                if ((((String)localObject6).contains("|" + (String)localObject3 + "|")) || (((String)localObject6).contains("|" + (String)localObject3 + ":" + (String)localObject4 + "|"))) {
                  this.p = Boolean.valueOf(true);
                }
              }
            }
          }
        }
        if (!this.p.booleanValue())
        {
          localObject3 = d.F().aC().iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (at)((Iterator)localObject3).next();
            if ((((Long)localObject2).longValue() >= localLong.longValue() + ((at)localObject4).e.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((at)localObject4).f.longValue()) && (localDouble1.doubleValue() >= ((at)localObject4).b.doubleValue()) && (localDouble1.doubleValue() <= ((at)localObject4).d.doubleValue()) && (localDouble2.doubleValue() >= ((at)localObject4).a.doubleValue()) && (localDouble2.doubleValue() <= ((at)localObject4).c.doubleValue())) {
              this.p = Boolean.valueOf(true);
            }
          }
        }
        if (d.F().aD().booleanValue())
        {
          localObject3 = d.F().aE();
          localObject4 = d.F().aF();
          localObject5 = "|" + d.F().aG().toLowerCase() + "|";
          if ((!((Boolean)localObject3).booleanValue()) || (!d.x().j().booleanValue())) {
            break label1301;
          }
          this.p = Boolean.valueOf(false);
          if (this.p.booleanValue())
          {
            localObject3 = d.x().o();
            if (((String)localObject3).length() > 0)
            {
              localObject4 = ((String)localObject3).split(":");
              localObject3 = localObject4[0];
              localObject4 = localObject4[1];
              localObject5 = d.F().aH().iterator();
              while (((Iterator)localObject5).hasNext())
              {
                localObject6 = (as)((Iterator)localObject5).next();
                if ((((Long)localObject2).longValue() >= localLong.longValue() + ((as)localObject6).b.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((as)localObject6).c.longValue()))
                {
                  localObject6 = "|" + ((as)localObject6).a + "|";
                  if ((((String)localObject6).contains("|" + (String)localObject3 + "|")) || (((String)localObject6).contains("|" + (String)localObject3 + ":" + (String)localObject4 + "|"))) {
                    this.p = Boolean.valueOf(false);
                  }
                }
              }
            }
          }
          if (this.p.booleanValue())
          {
            localObject3 = d.F().aI().iterator();
            while (((Iterator)localObject3).hasNext())
            {
              localObject4 = (at)((Iterator)localObject3).next();
              if ((((Long)localObject2).longValue() >= localLong.longValue() + ((at)localObject4).e.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((at)localObject4).f.longValue()) && (localDouble1.doubleValue() >= ((at)localObject4).b.doubleValue()) && (localDouble1.doubleValue() <= ((at)localObject4).d.doubleValue()) && (localDouble2.doubleValue() >= ((at)localObject4).a.doubleValue()) && (localDouble2.doubleValue() <= ((at)localObject4).c.doubleValue())) {
                this.p = Boolean.valueOf(false);
              }
            }
          }
        }
        if (localObject1 != this.p)
        {
          localObject2 = new StringBuilder().append("APP Policy is now ");
          if (!this.p.booleanValue()) {
            break label1405;
          }
          localObject1 = "resume";
          ce.b((String)localObject1);
        }
        return this.p;
        label1189:
        if ((((Integer)localObject4).intValue() >= 0) || (((Integer)localObject4).intValue() > d.x().p().intValue())) {
          break;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject4;
        Object localObject5;
        ce.a(localException);
        continue;
        if ((((String)localObject5).length() > 2) && (((String)localObject5).contains("|" + d.x().h().toLowerCase() + "|")))
        {
          this.p = Boolean.valueOf(true);
          continue;
          label1301:
          if ((((Integer)localObject4).intValue() < 0) && (((Integer)localObject4).intValue() <= d.x().p().intValue()))
          {
            this.p = Boolean.valueOf(false);
          }
          else if ((((String)localObject5).length() > 2) && (((String)localObject5).contains("|" + d.x().h().toLowerCase() + "|")))
          {
            this.p = Boolean.valueOf(false);
            continue;
            label1405:
            localObject1 = "suspend";
          }
        }
      }
    }
  }
}
