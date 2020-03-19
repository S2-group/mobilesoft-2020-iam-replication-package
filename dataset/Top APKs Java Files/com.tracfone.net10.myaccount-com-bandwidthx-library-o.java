package com.bandwidthx.library;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class o
{
  public static final int d = 0;
  public static final int e = 1;
  public static final int f = 2;
  public static final int g = 3;
  public static final int h = 4;
  private static ai i = null;
  protected Long a = Long.valueOf(0L);
  protected Long b = Long.valueOf(10000L);
  protected Long c = Long.valueOf(10000L);
  private Long j = Long.valueOf(0L);
  private Integer k = Integer.valueOf(0);
  private String l = "";
  private Long m = Long.valueOf(0L);
  private Long n = Long.valueOf(0L);
  private Boolean o = Boolean.valueOf(false);
  private Boolean p = Boolean.valueOf(false);
  private Boolean q = Boolean.valueOf(false);
  private Boolean r = Boolean.valueOf(false);
  private Boolean s = Boolean.valueOf(false);
  private Boolean t = Boolean.valueOf(false);
  private Boolean u = Boolean.valueOf(true);
  private Boolean v = Boolean.valueOf(true);
  private Long w = Long.valueOf(0L);
  
  public o(ai paramAi)
  {
    try
    {
      i = paramAi;
      return;
    }
    catch (Exception paramAi)
    {
      ah.a(paramAi);
    }
  }
  
  public Boolean a(Boolean paramBoolean)
  {
    for (;;)
    {
      Boolean localBoolean2;
      try
      {
        localBoolean2 = this.v;
        if ((paramBoolean.booleanValue()) && (i.g().k().booleanValue()))
        {
          this.w = Long.valueOf(0L);
          paramBoolean = Boolean.valueOf(true);
          return paramBoolean;
        }
        if (i.I().j().length() > 0)
        {
          Pattern localPattern = Pattern.compile(i.I().j());
          if (this.w.longValue() < cu.f().longValue() - this.c.longValue())
          {
            this.v = Boolean.valueOf(true);
            this.w = cu.f();
          }
          try
          {
            paramBoolean = Long.valueOf(0L);
            localObject1 = "";
            localObject2 = "";
            PackageManager localPackageManager = ai.d().getPackageManager();
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
                i.g();
                str = c.b(Integer.valueOf(((ApplicationInfo)localObject3).uid));
                if (!localPattern.matcher(str).matches()) {
                  break label683;
                }
                localObject5 = localPackageManager.getPackageInfo(str, 2);
                if (localObject5 == null) {
                  break label683;
                }
                localObject3 = ((PackageInfo)localObject5).receivers;
                if (localObject3 == null) {
                  break label683;
                }
                int i2 = localObject3.length;
                i1 = 0;
                if (i1 >= i2) {
                  break label683;
                }
                if (localObject3[i1].name.contains("BxReceiver"))
                {
                  if ((!((PackageInfo)localObject5).applicationInfo.enabled) || ((((PackageInfo)localObject5).applicationInfo.flags & 0x200000) != 0)) {
                    break label683;
                  }
                  if (BxService.a(ai.d(), str).booleanValue())
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
            ah.a(paramBoolean);
            continue;
          }
        }
      }
      finally {}
      try
      {
        if (paramBoolean.longValue() >= ((PackageInfo)localObject5).lastUpdateTime) {
          break label670;
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
          if (((String)localObject1).equals(c.c())) {
            this.v = Boolean.valueOf(true);
          }
        }
        else
        {
          if (localBoolean2 != this.v)
          {
            localObject4 = new StringBuilder().append("APP Client is now ");
            if (!this.v.booleanValue()) {
              break label615;
            }
            paramBoolean = "active";
            ah.b(paramBoolean + ": " + (String)localObject2);
          }
          paramBoolean = this.v;
          continue;
        }
        this.v = Boolean.valueOf(false);
        continue;
        label615:
        paramBoolean = "passive, yielding to " + (String)localObject1;
        continue;
        this.v = Boolean.valueOf(true);
        if (!localBoolean2.booleanValue())
        {
          ah.b("APP Client is now active");
          continue;
          label670:
          label683:
          Boolean localBoolean1 = paramBoolean;
          paramBoolean = (Boolean)localObject1;
          localObject1 = localBoolean1;
        }
      }
    }
  }
  
  public void a()
  {
    this.k = Integer.valueOf(i.D().a("AutoWifiSuspendedReason", this.k.intValue()));
    this.j = Long.valueOf(i.D().a("AutoWifiSuspendedTicks", this.j.longValue()));
    this.l = i.D().a("AutoWifiSuspendedSSID", this.l);
    if (i.D().a("XAutoWifiSuspendedSSID", "").length() > 0) {}
    try
    {
      this.l = t.b(t.c(), i.D().a("XAutoWifiSuspendedSSID", ""));
      this.n = Long.valueOf(i.D().a("AutoWifiSuspendedSSIDTicks", this.n.longValue()));
      this.o = Boolean.valueOf(i.D().a("AutoWifiSuspendedScreenOn", this.o.booleanValue()));
      this.p = Boolean.valueOf(i.D().a("AutoWifiSuspendedScreenOff", this.p.booleanValue()));
      this.q = Boolean.valueOf(i.D().a("AutoWifiSuspendedForeground", this.q.booleanValue()));
      this.r = Boolean.valueOf(i.D().a("AutoWifiSuspendedRadioOn", this.r.booleanValue()));
      this.s = Boolean.valueOf(i.D().a("AutoWifiSuspendedRadioOff", this.s.booleanValue()));
      this.t = Boolean.valueOf(i.D().a("AutoWifiSuspendedConnected", this.t.booleanValue()));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ah.a(localException, Boolean.valueOf(false));
      }
    }
  }
  
  public void a(Long paramLong, Integer paramInteger)
  {
    a(paramLong, "", paramInteger);
  }
  
  public void a(Long paramLong, String paramString, Integer paramInteger)
  {
    if ((i.I().ag().booleanValue()) && (paramInteger.intValue() != 0)) {
      ah.b("APP Won't suspend due to test mode");
    }
    for (;;)
    {
      return;
      Boolean localBoolean = c();
      this.k = paramInteger;
      if (paramLong.longValue() == Long.MAX_VALUE)
      {
        this.j = paramLong;
        this.l = paramString;
        this.m = Long.valueOf(0L);
        this.n = this.j;
        this.o = Boolean.valueOf(false);
        this.p = Boolean.valueOf(false);
        this.q = Boolean.valueOf(false);
        this.r = Boolean.valueOf(false);
        this.s = Boolean.valueOf(false);
        this.t = Boolean.valueOf(false);
        i.D().b("AutoWifiSuspendedReason", this.k.intValue());
        i.D().b("AutoWifiSuspendedTicks", this.j.longValue());
      }
      try
      {
        i.D().b("XAutoWifiSuspendedSSID", t.a(t.c(), this.l));
        i.D().b("AutoWifiSuspendedSSIDTicks", this.n.longValue());
        i.D().b("AutoWifiSuspendedScreenOn", this.o.booleanValue());
        i.D().b("AutoWifiSuspendedScreenOff", this.p.booleanValue());
        i.D().b("AutoWifiSuspendedForeground", this.q.booleanValue());
        i.D().b("AutoWifiSuspendedRadioOn", this.r.booleanValue());
        i.D().b("AutoWifiSuspendedRadioOff", this.s.booleanValue());
        i.D().b("AutoWifiSuspendedConnected", this.t.booleanValue());
        i.D().c();
        i.l().w();
        i.l().t();
        if (!localBoolean.booleanValue()) {
          u.c();
        }
        i.H().a(Boolean.valueOf(true));
        if (i.f() == null) {
          continue;
        }
        i.f().k();
        return;
        this.j = Long.valueOf(cu.f().longValue() + paramLong.longValue());
      }
      catch (Exception paramLong)
      {
        for (;;)
        {
          ah.a(paramLong);
        }
      }
    }
  }
  
  public void b() {}
  
  public Boolean c()
  {
    if (f().longValue() > 0L) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  public Integer d()
  {
    if ((this.j.longValue() == 0L) && (!s().booleanValue())) {
      return Integer.valueOf(4);
    }
    return this.k;
  }
  
  public String e()
  {
    return this.l;
  }
  
  public Long f()
  {
    o();
    if (this.j.longValue() == Long.MAX_VALUE) {
      return this.j;
    }
    if (this.j.longValue() > cu.f().longValue()) {
      return Long.valueOf(this.j.longValue() - cu.f().longValue());
    }
    if (!s().booleanValue()) {
      return Long.valueOf(1L);
    }
    if ((this.l.length() > 0) && (this.j.longValue() > 0L))
    {
      if ((this.m.longValue() > 0L) && (this.m.longValue() < cu.f().longValue() - i.I().w().longValue())) {
        this.m = Long.valueOf(0L);
      }
      if (this.m.longValue() == 0L)
      {
        this.m = cu.f();
        this.j = Long.valueOf(cu.f().longValue() + i.I().w().longValue());
        i.D().b("AutoWifiSuspendedTicks", this.j.longValue());
        i.D().c();
        new AsyncTask()
        {
          protected Void a(Void... paramAnonymousVarArgs)
          {
            ah.a("CheckSuspension");
            try
            {
              if (o.a(o.this).length() > 0)
              {
                ah.b("APP Suspension period has ended, check to see if " + o.a(o.this) + " is still available");
                o.t().h().k();
              }
              ah.d();
              return null;
            }
            catch (Exception paramAnonymousVarArgs)
            {
              for (;;)
              {
                ah.a(paramAnonymousVarArgs, Boolean.valueOf(false));
              }
            }
          }
        }.execute(new Void[0]);
      }
      return Long.valueOf(1L);
    }
    if ((this.k.intValue() == 3) && (i.r().e().intValue() < i.I().r().intValue()))
    {
      this.j = Long.valueOf(cu.f().longValue() + i.I().s().longValue());
      i.D().b("AutoWifiSuspendedTicks", this.j.longValue());
      i.D().c();
      StringBuilder localStringBuilder = new StringBuilder().append("APP Suspend due to battery level: ").append(i.r().e().toString());
      if (i.r().d().booleanValue()) {}
      for (String str = "*";; str = "")
      {
        ah.b(str);
        return Long.valueOf(this.j.longValue() - cu.f().longValue());
      }
    }
    return Long.valueOf(0L);
  }
  
  public void g()
  {
    Boolean localBoolean = c();
    this.k = Integer.valueOf(0);
    this.j = Long.valueOf(0L);
    this.l = "";
    this.m = Long.valueOf(0L);
    this.n = Long.valueOf(0L);
    this.o = Boolean.valueOf(false);
    this.p = Boolean.valueOf(false);
    this.q = Boolean.valueOf(false);
    this.r = Boolean.valueOf(false);
    this.s = Boolean.valueOf(false);
    this.t = Boolean.valueOf(false);
    i.D().b("AutoWifiSuspendedReason", this.k.intValue());
    i.D().b("AutoWifiSuspendedTicks", this.j.longValue());
    try
    {
      i.D().b("XAutoWifiSuspendedSSID", t.a(t.c(), this.l));
      i.D().b("AutoWifiSuspendedSSIDTicks", this.n.longValue());
      i.D().b("AutoWifiSuspendedScreenOn", this.o.booleanValue());
      i.D().b("AutoWifiSuspendedScreenOff", this.p.booleanValue());
      i.D().b("AutoWifiSuspendedForeground", this.q.booleanValue());
      i.D().b("AutoWifiSuspendedRadioOn", this.r.booleanValue());
      i.D().b("AutoWifiSuspendedRadioOff", this.s.booleanValue());
      i.D().b("AutoWifiSuspendedConnected", this.t.booleanValue());
      i.D().c();
      if (localBoolean.booleanValue()) {
        u.d();
      }
      i.H().a(Boolean.valueOf(true));
      if (i.f() != null) {
        i.f().a(Boolean.valueOf(false));
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ah.a(localException);
      }
    }
  }
  
  public void h()
  {
    this.q = Boolean.valueOf(true);
    i.D().b("AutoWifiSuspendedForeground", this.q.booleanValue());
    i.D().c();
  }
  
  public void i()
  {
    this.r = Boolean.valueOf(true);
    i.D().b("AutoWifiSuspendedRadioOn", this.r.booleanValue());
    i.D().c();
  }
  
  public void j()
  {
    this.s = Boolean.valueOf(true);
    i.D().b("AutoWifiSuspendedRadioOff", this.s.booleanValue());
    i.D().c();
  }
  
  public void k()
  {
    this.o = Boolean.valueOf(true);
    i.D().b("AutoWifiSuspendedScreenOn", this.o.booleanValue());
    i.D().c();
  }
  
  public void l()
  {
    this.p = Boolean.valueOf(true);
    i.D().b("AutoWifiSuspendedScreenOff", this.p.booleanValue());
    i.D().c();
  }
  
  public void m()
  {
    this.t = Boolean.valueOf(true);
    i.D().b("AutoWifiSuspendedConnected", this.t.booleanValue());
    i.D().c();
  }
  
  public void n()
  {
    this.m = Long.valueOf(0L);
  }
  
  public void o()
  {
    try
    {
      if (this.n.longValue() == 0L)
      {
        this.n = this.j;
        i.D().b("AutoWifiSuspendedSSIDTicks", this.n.longValue());
        i.D().c();
      }
      if ((this.j.longValue() > 0L) && (this.l.length() > 0) && (i.O().e().booleanValue()) && (i.O().c("", this.l).booleanValue()))
      {
        this.n = cu.f();
        i.D().b("AutoWifiSuspendedSSIDTicks", this.n.longValue());
        i.D().c();
      }
      return;
    }
    catch (Exception localException)
    {
      ah.a(localException, Boolean.valueOf(false));
    }
  }
  
  public void p()
  {
    try
    {
      if (this.j.longValue() > 0L)
      {
        Boolean localBoolean2 = Boolean.valueOf(false);
        o();
        Boolean localBoolean1;
        if ((i.O().r().booleanValue()) && (this.k.intValue() == 2))
        {
          ah.b("APP Access point mode, so resume");
          localBoolean1 = Boolean.valueOf(true);
        }
        while (localBoolean1.booleanValue())
        {
          this.j = Long.valueOf(Long.MAX_VALUE);
          i.D().b("AutoWifiSuspendedTicks", this.j.longValue());
          i.D().c();
          g();
          i.h().a(Long.valueOf(0L));
          return;
          if ((this.o.booleanValue()) && (i.r().i().booleanValue()))
          {
            ah.b("APP Screen is on, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.p.booleanValue()) && (i.r().j().booleanValue()))
          {
            ah.b("APP Screen is off, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.q.booleanValue()) && (i.g().k().booleanValue()))
          {
            ah.b("APP App is in foreground, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.r.booleanValue()) && (i.O().e().booleanValue()))
          {
            ah.b("APP Radio is on, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.s.booleanValue()) && (!i.O().e().booleanValue()))
          {
            ah.b("APP Radio is off, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else if ((this.t.booleanValue()) && (i.O().i().booleanValue()))
          {
            ah.b("APP Device is connected, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else
          {
            localBoolean1 = localBoolean2;
            if (this.l.length() > 0)
            {
              localBoolean1 = localBoolean2;
              if (this.n.longValue() < cu.f().longValue() - i.I().x().longValue())
              {
                ah.b("APP Hotspot " + this.l + " has not been seen for " + v.d(i.I().x(), Boolean.valueOf(false)) + ", last seen " + v.a(this.n) + ", so resume");
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
      ah.a(localException, Boolean.valueOf(false));
    }
  }
  
  public a q()
  {
    return new a();
  }
  
  public b r()
  {
    return new b();
  }
  
  public Boolean s()
  {
    Object localObject1 = this.u;
    try
    {
      Object localObject2;
      Long localLong;
      Double localDouble1;
      Double localDouble2;
      Object localObject3;
      if (this.a.longValue() < cu.f().longValue() - this.b.longValue())
      {
        this.u = Boolean.valueOf(true);
        this.a = cu.f();
        localObject2 = cu.f();
        localLong = Long.valueOf(((Long)localObject2).longValue() / 86400000L * 86400000L);
        localDouble1 = i.v().l();
        localDouble2 = i.v().m();
        if (i.I().ay().booleanValue())
        {
          this.u = Boolean.valueOf(false);
          localObject3 = i.I().az();
          localObject4 = i.I().aA();
          localObject5 = "|" + i.I().aB().toLowerCase() + "|";
          if ((!((Boolean)localObject3).booleanValue()) || (!i.z().k().booleanValue())) {
            break label1189;
          }
        }
      }
      for (this.u = Boolean.valueOf(true);; this.u = Boolean.valueOf(true))
      {
        Object localObject6;
        if (!this.u.booleanValue())
        {
          localObject3 = i.z().p();
          if (((String)localObject3).length() > 0)
          {
            localObject4 = ((String)localObject3).split(":");
            localObject3 = localObject4[0];
            localObject4 = localObject4[1];
            localObject5 = i.I().aC().iterator();
            while (((Iterator)localObject5).hasNext())
            {
              localObject6 = (a)((Iterator)localObject5).next();
              if ((((Long)localObject2).longValue() >= localLong.longValue() + ((a)localObject6).b.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((a)localObject6).c.longValue()))
              {
                localObject6 = "|" + ((a)localObject6).a + "|";
                if ((((String)localObject6).contains("|" + (String)localObject3 + "|")) || (((String)localObject6).contains("|" + (String)localObject3 + ":" + (String)localObject4 + "|"))) {
                  this.u = Boolean.valueOf(true);
                }
              }
            }
          }
        }
        if (!this.u.booleanValue())
        {
          localObject3 = i.I().aD().iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (b)((Iterator)localObject3).next();
            if ((((Long)localObject2).longValue() >= localLong.longValue() + ((b)localObject4).e.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((b)localObject4).f.longValue()) && (localDouble1.doubleValue() >= ((b)localObject4).b.doubleValue()) && (localDouble1.doubleValue() <= ((b)localObject4).d.doubleValue()) && (localDouble2.doubleValue() >= ((b)localObject4).a.doubleValue()) && (localDouble2.doubleValue() <= ((b)localObject4).c.doubleValue())) {
              this.u = Boolean.valueOf(true);
            }
          }
        }
        if (i.I().aE().booleanValue())
        {
          localObject3 = i.I().aF();
          localObject4 = i.I().aG();
          localObject5 = "|" + i.I().aH().toLowerCase() + "|";
          if ((!((Boolean)localObject3).booleanValue()) || (!i.z().k().booleanValue())) {
            break label1301;
          }
          this.u = Boolean.valueOf(false);
          if (this.u.booleanValue())
          {
            localObject3 = i.z().p();
            if (((String)localObject3).length() > 0)
            {
              localObject4 = ((String)localObject3).split(":");
              localObject3 = localObject4[0];
              localObject4 = localObject4[1];
              localObject5 = i.I().aI().iterator();
              while (((Iterator)localObject5).hasNext())
              {
                localObject6 = (a)((Iterator)localObject5).next();
                if ((((Long)localObject2).longValue() >= localLong.longValue() + ((a)localObject6).b.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((a)localObject6).c.longValue()))
                {
                  localObject6 = "|" + ((a)localObject6).a + "|";
                  if ((((String)localObject6).contains("|" + (String)localObject3 + "|")) || (((String)localObject6).contains("|" + (String)localObject3 + ":" + (String)localObject4 + "|"))) {
                    this.u = Boolean.valueOf(false);
                  }
                }
              }
            }
          }
          if (this.u.booleanValue())
          {
            localObject3 = i.I().aJ().iterator();
            while (((Iterator)localObject3).hasNext())
            {
              localObject4 = (b)((Iterator)localObject3).next();
              if ((((Long)localObject2).longValue() >= localLong.longValue() + ((b)localObject4).e.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((b)localObject4).f.longValue()) && (localDouble1.doubleValue() >= ((b)localObject4).b.doubleValue()) && (localDouble1.doubleValue() <= ((b)localObject4).d.doubleValue()) && (localDouble2.doubleValue() >= ((b)localObject4).a.doubleValue()) && (localDouble2.doubleValue() <= ((b)localObject4).c.doubleValue())) {
                this.u = Boolean.valueOf(false);
              }
            }
          }
        }
        if (localObject1 != this.u)
        {
          localObject2 = new StringBuilder().append("APP Policy is now ");
          if (!this.u.booleanValue()) {
            break label1405;
          }
          localObject1 = "resume";
          ah.b((String)localObject1);
        }
        return this.u;
        label1189:
        if ((((Integer)localObject4).intValue() >= 0) || (((Integer)localObject4).intValue() > i.z().s().intValue())) {
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
        ah.a(localException);
        continue;
        if ((((String)localObject5).length() > 2) && (((String)localObject5).contains("|" + i.z().i().toLowerCase() + "|")))
        {
          this.u = Boolean.valueOf(true);
          continue;
          label1301:
          if ((((Integer)localObject4).intValue() < 0) && (((Integer)localObject4).intValue() <= i.z().s().intValue()))
          {
            this.u = Boolean.valueOf(false);
          }
          else if ((((String)localObject5).length() > 2) && (((String)localObject5).contains("|" + i.z().i().toLowerCase() + "|")))
          {
            this.u = Boolean.valueOf(false);
            continue;
            label1405:
            localObject1 = "suspend";
          }
        }
      }
    }
  }
  
  public class a
  {
    public String a = "";
    public Long b = Long.valueOf(0L);
    public Long c = Long.valueOf(0L);
    
    public a() {}
  }
  
  public class b
  {
    public Double a = Double.valueOf(0.0D);
    public Double b = Double.valueOf(0.0D);
    public Double c = Double.valueOf(0.0D);
    public Double d = Double.valueOf(0.0D);
    public Long e = Long.valueOf(0L);
    public Long f = Long.valueOf(0L);
    
    public b() {}
  }
}
