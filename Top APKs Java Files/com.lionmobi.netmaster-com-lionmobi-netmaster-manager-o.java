package com.lionmobi.netmaster.manager;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.lionmobi.netmaster.ApplicationEx;
import com.lionmobi.netmaster.dao.TrafficInfoDao;
import com.lionmobi.netmaster.dao.TrafficRankInfoDao;
import com.lionmobi.netmaster.dao.e;
import com.lionmobi.netmaster.domain.TrafficRankInfo;
import com.lionmobi.netmaster.domain.d;
import com.lionmobi.netmaster.eventbus.message.EventBoostApps;
import com.lionmobi.netmaster.eventbus.message.EventPackageAdd;
import com.lionmobi.netmaster.eventbus.message.EventQueryTrafficRankInfo;
import com.lionmobi.netmaster.eventbus.message.EventQueryTrafficRankInfo.EventQueryTrafficRankInfoResult;
import com.lionmobi.netmaster.eventbus.message.EventTrafficReports;
import com.lionmobi.netmaster.eventbus.message.EventTrafficReports.EventTrafficReportsResult;
import com.lionmobi.netmaster.eventbus.message.b;
import com.lionmobi.netmaster.eventbus.message.g;
import com.lionmobi.netmaster.eventbus.message.h;
import com.lionmobi.netmaster.f.ao;
import com.lionmobi.netmaster.f.ap;
import com.lionmobi.netmaster.f.r;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import me.dozen.dpreference.a;

public class o
  implements Handler.Callback
{
  private static final p f = new p()
  {
    public String getDimensionality()
    {
      return "";
    }
    
    public long getValue(ap paramAnonymousAp)
    {
      return paramAnonymousAp.getTotalMobile() + paramAnonymousAp.getTotalWifi();
    }
  };
  private static final p g = new p()
  {
    public String getDimensionality()
    {
      return "/mobile";
    }
    
    public long getValue(ap paramAnonymousAp)
    {
      return paramAnonymousAp.getTotalMobile();
    }
  };
  private static final p h = new p()
  {
    public String getDimensionality()
    {
      return "/total/background";
    }
    
    public long getValue(ap paramAnonymousAp)
    {
      return paramAnonymousAp.getTotalBackground();
    }
  };
  private static final p[] i = { f, g, h };
  long a = 0L;
  long b = 0L;
  long c = 0L;
  boolean d = false;
  long e = 0L;
  private final Map j = new HashMap();
  private final List k = new ArrayList();
  private Handler l;
  private PackageManager m;
  private SharedPreferences n;
  private ao o;
  private ActivityManager p;
  private Context q;
  
  public o() {}
  
  private static int a(long paramLong1, long paramLong2)
  {
    GregorianCalendar localGregorianCalendar1 = new GregorianCalendar();
    localGregorianCalendar1.setTimeInMillis(paramLong1);
    GregorianCalendar localGregorianCalendar2 = new GregorianCalendar();
    localGregorianCalendar2.setTimeInMillis(paramLong2);
    localGregorianCalendar1.set(11, 0);
    localGregorianCalendar1.set(12, 0);
    localGregorianCalendar1.set(13, 0);
    localGregorianCalendar1.set(14, 0);
    localGregorianCalendar2.set(11, 0);
    localGregorianCalendar2.set(12, 0);
    localGregorianCalendar2.set(13, 0);
    localGregorianCalendar2.set(14, 0);
    return (int)((localGregorianCalendar2.getTimeInMillis() - localGregorianCalendar1.getTimeInMillis()) / 86400000L);
  }
  
  private long a(d paramD)
  {
    Object localObject = new GregorianCalendar();
    ((Calendar)localObject).setTime(new Date());
    ((Calendar)localObject).setFirstDayOfWeek(1);
    ((Calendar)localObject).getFirstDayOfWeek();
    int i1 = ((Calendar)localObject).get(7);
    int i2 = ((Calendar)localObject).get(8);
    localObject = d.dataAsList(paramD.getHours(), 24);
    paramD = d.dataAsList(paramD.getDays(), i2, i1);
    localObject = ((List)localObject).iterator();
    for (long l1 = 0L; ((Iterator)localObject).hasNext(); l1 = ((Long)((Iterator)localObject).next()).longValue() + l1) {}
    long l2 = l1;
    if (paramD != null)
    {
      paramD = paramD.iterator();
      for (;;)
      {
        l2 = l1;
        if (!paramD.hasNext()) {
          break;
        }
        l1 += ((Long)paramD.next()).longValue();
      }
    }
    return l2;
  }
  
  private long a(d paramD, int paramInt)
  {
    if (paramInt <= 0) {
      return 0L;
    }
    Iterator localIterator = d.dataAsList(paramD.getHours(), 24).iterator();
    for (long l1 = 0L; localIterator.hasNext(); l1 = ((Long)localIterator.next()).longValue() + l1) {}
    paramInt -= 1;
    if (paramInt <= 0) {
      return l1;
    }
    paramD = d.dataAsList(paramD.getDays(), 31);
    int i2 = paramD.size();
    if (paramInt > i2) {
      paramInt = i2;
    }
    for (;;)
    {
      int i1 = 1;
      while (i1 <= paramInt)
      {
        l1 += ((Long)paramD.get(i2 - i1)).longValue();
        i1 += 1;
      }
      return l1;
    }
  }
  
  private String a(String paramString)
  {
    return paramString + "/history";
  }
  
  private void a()
  {
    Object localObject;
    long l2;
    long l3;
    if (this.q != null)
    {
      localObject = new a(this.q, "com.powerwifi_dpref");
      l1 = ((a)localObject).getPrefLong("total_data_flow", -1L);
      l2 = ((a)localObject).getPrefLong("use_data_flow", -1L);
      l3 = ((a)localObject).getPrefLong("last_total_data_flow", -1L);
      if ((l1 != -1L) && (l2 != -1L) && (l3 != -1L)) {
        break label210;
      }
      if (this.b > 10485760L)
      {
        localObject = new b();
        ((b)localObject).setType(1);
        ((b)localObject).d = this.b;
        c.c.getDefault().post(localObject);
      }
      if (this.b > 1048576L)
      {
        localObject = new com.lionmobi.netmaster.eventbus.message.c();
        ((com.lionmobi.netmaster.eventbus.message.c)localObject).a = this.b;
        ((com.lionmobi.netmaster.eventbus.message.c)localObject).b = this.c;
        ((com.lionmobi.netmaster.eventbus.message.c)localObject).c = this.a;
        c.c.getDefault().post(localObject);
        r.e("Network_Master", "monitor lTodayFlow =" + this.b);
      }
    }
    return;
    label210:
    GregorianCalendar localGregorianCalendar1 = new GregorianCalendar();
    localGregorianCalendar1.setTime(new Date());
    GregorianCalendar localGregorianCalendar2 = new GregorianCalendar();
    localGregorianCalendar2.setTimeInMillis(this.e);
    if ((this.e != 0L) && (localGregorianCalendar1.get(2) != localGregorianCalendar2.get(2)))
    {
      ((a)localObject).setPrefLong("use_data_flow", 0L);
      ((a)localObject).setPrefLong("last_total_data_flow", 0L);
    }
    int i1 = (int)((this.a - l3 + l2) * 100L / l1);
    if (i1 > 100)
    {
      i1 = 100;
      label316:
      if ((i1 <= 80L) || (this.b <= 10485760L)) {
        break label406;
      }
      localObject = new b();
      ((b)localObject).setType(2);
      ((b)localObject).d = this.b;
      ((b)localObject).c = (l1 - l2);
      if (((b)localObject).c >= 0L) {
        break label408;
      }
    }
    label406:
    label408:
    for (long l1 = 0L;; l1 = ((b)localObject).c)
    {
      ((b)localObject).c = l1;
      ((b)localObject).b = i1;
      c.c.getDefault().post(localObject);
      break;
      break label316;
      break;
    }
  }
  
  private void a(TrafficInfoDao paramTrafficInfoDao, String paramString, long paramLong)
  {
    try
    {
      paramTrafficInfoDao.insertOrReplace(new d(paramString, System.currentTimeMillis(), paramLong, 0, d.listAsData(d.dataAsList("", 120)), d.listAsData(d.dataAsList("", 24)), d.listAsData(d.dataAsList("", 31))));
      return;
    }
    catch (Exception paramTrafficInfoDao)
    {
      paramTrafficInfoDao.printStackTrace();
    }
  }
  
  private void a(TrafficInfoDao paramTrafficInfoDao, Map paramMap, String paramString, long paramLong)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    localGregorianCalendar.setTime(new Date());
    paramMap = (d)paramMap.get(paramString);
    if (paramMap == null)
    {
      a(paramTrafficInfoDao, paramString, paramLong);
      return;
    }
    long l2 = paramLong - paramMap.getPreValue();
    long l1 = l2;
    if (l2 < 0L) {
      l1 = 0L;
    }
    paramTrafficInfoDao = new GregorianCalendar();
    paramTrafficInfoDao.setTimeInMillis(paramMap.getPreTime());
    paramString = d.dataAsList(paramMap.getDots(), 120);
    int i2 = (int)((localGregorianCalendar.getTimeInMillis() - paramMap.getPreTime() + 15000L) / ApplicationEx.getInstance().a);
    int i1 = i2;
    if (i2 > 120) {
      i1 = 120;
    }
    i2 = 0;
    if (i2 < i1)
    {
      paramString.remove(0);
      if (i2 == i1 - 1) {}
      for (l2 = l1;; l2 = 0L)
      {
        paramString.add(Long.valueOf(l2));
        i2 += 1;
        break;
      }
    }
    paramMap.setDots(d.listAsData(paramString));
    paramString = d.dataAsList(paramMap.getHours(), 24);
    if (localGregorianCalendar.get(6) != paramTrafficInfoDao.get(6))
    {
      l2 = 0L;
      Object localObject = paramString.iterator();
      while (((Iterator)localObject).hasNext()) {
        l2 += ((Long)((Iterator)localObject).next()).longValue();
      }
      paramString.clear();
      i1 = 0;
      while (i1 < 24)
      {
        paramString.add(Long.valueOf(0L));
        i1 += 1;
      }
      localObject = d.dataAsList(paramMap.getDays(), 31);
      i2 = a(paramTrafficInfoDao.getTimeInMillis(), localGregorianCalendar.getTimeInMillis());
      i1 = 0;
      if (i1 < i2)
      {
        ((List)localObject).remove(0);
        if (i1 == 0) {}
        for (long l3 = l2;; l3 = 0L)
        {
          ((List)localObject).add(Long.valueOf(l3));
          i1 += 1;
          break;
        }
      }
      paramMap.setDays(d.listAsData((List)localObject));
    }
    i1 = localGregorianCalendar.get(11);
    paramString.set(i1, Long.valueOf(l1 + ((Long)paramString.get(i1)).longValue()));
    paramMap.setHours(d.listAsData(paramString));
    paramMap.setDotNum(paramMap.getDotNum() + 1);
    paramMap.setPreTime(localGregorianCalendar.getTimeInMillis());
    paramMap.setPreValue(paramLong);
    this.e = localGregorianCalendar.getTimeInMillis();
  }
  
  private void a(TrafficRankInfoDao paramTrafficRankInfoDao, String paramString, ap paramAp)
  {
    try
    {
      TrafficRankInfo localTrafficRankInfo = new TrafficRankInfo(paramString);
      localTrafficRankInfo.setMobiBackgroundTx(paramAp.h);
      localTrafficRankInfo.setMobiBackgroundRx(paramAp.g);
      localTrafficRankInfo.setMobiForegroundTx(paramAp.f);
      localTrafficRankInfo.setMobiForegroundRx(paramAp.e);
      localTrafficRankInfo.setWifiBackgroundTx(paramAp.h);
      localTrafficRankInfo.setWifiBackgroundRx(paramAp.g);
      localTrafficRankInfo.setWifiForegroundTx(paramAp.f);
      localTrafficRankInfo.setWifiForegroundRx(paramAp.e);
      paramTrafficRankInfoDao.insertOrReplace(localTrafficRankInfo);
      paramString = new TrafficRankInfo(a(paramString));
      paramString.setMobiBackgroundTx(paramAp.h);
      paramString.setMobiBackgroundRx(paramAp.g);
      paramString.setMobiForegroundTx(paramAp.f);
      paramString.setMobiForegroundRx(paramAp.e);
      paramString.setWifiBackgroundTx(paramAp.h);
      paramString.setWifiBackgroundRx(paramAp.g);
      paramString.setWifiForegroundTx(paramAp.f);
      paramString.setWifiForegroundRx(paramAp.e);
      paramTrafficRankInfoDao.insertOrReplace(paramString);
      return;
    }
    catch (Exception paramTrafficRankInfoDao)
    {
      paramTrafficRankInfoDao.printStackTrace();
    }
  }
  
  /* Error */
  private void a(e paramE)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: getfield 390	com/lionmobi/netmaster/manager/o:m	Landroid/content/pm/PackageManager;
    //   7: iconst_0
    //   8: invokevirtual 396	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   11: astore 15
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload 15
    //   18: ifnonnull +10 -> 28
    //   21: return
    //   22: astore_1
    //   23: ldc 2
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    //   28: aload_0
    //   29: aload_1
    //   30: iconst_0
    //   31: invokespecial 399	com/lionmobi/netmaster/manager/o:c	(Lcom/lionmobi/netmaster/dao/e;Z)V
    //   34: aload_1
    //   35: invokevirtual 405	com/lionmobi/netmaster/dao/e:getTrafficInfoDao	()Lcom/lionmobi/netmaster/dao/TrafficInfoDao;
    //   38: invokevirtual 409	com/lionmobi/netmaster/dao/TrafficInfoDao:loadAll	()Ljava/util/List;
    //   41: astore 16
    //   43: new 67	java/util/ArrayList
    //   46: dup
    //   47: aload 15
    //   49: invokeinterface 161 1 0
    //   54: invokespecial 411	java/util/ArrayList:<init>	(I)V
    //   57: astore_1
    //   58: aload 16
    //   60: invokestatic 415	com/lionmobi/netmaster/domain/d:listAsMap	(Ljava/util/List;)Ljava/util/Map;
    //   63: astore 16
    //   65: aload_0
    //   66: iconst_1
    //   67: putfield 78	com/lionmobi/netmaster/manager/o:d	Z
    //   70: lconst_0
    //   71: lstore_3
    //   72: lconst_0
    //   73: lstore 7
    //   75: lconst_0
    //   76: lstore 5
    //   78: iconst_0
    //   79: istore_2
    //   80: iload_2
    //   81: aload 15
    //   83: invokeinterface 161 1 0
    //   88: if_icmpge +465 -> 553
    //   91: aload 15
    //   93: iload_2
    //   94: invokeinterface 164 2 0
    //   99: checkcast 417	android/content/pm/ApplicationInfo
    //   102: astore 21
    //   104: aload 21
    //   106: ifnonnull +32 -> 138
    //   109: lload_3
    //   110: lstore 13
    //   112: lload 7
    //   114: lstore 11
    //   116: lload 5
    //   118: lstore 9
    //   120: iload_2
    //   121: iconst_1
    //   122: iadd
    //   123: istore_2
    //   124: lload 9
    //   126: lstore 5
    //   128: lload 11
    //   130: lstore 7
    //   132: lload 13
    //   134: lstore_3
    //   135: goto -55 -> 80
    //   138: lload 5
    //   140: lstore 9
    //   142: lload 7
    //   144: lstore 11
    //   146: lload_3
    //   147: lstore 13
    //   149: getstatic 422	com/lionmobi/netmaster/f/g:b	Ljava/util/HashSet;
    //   152: aload 21
    //   154: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   157: invokevirtual 431	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   160: ifne -40 -> 120
    //   163: new 347	com/lionmobi/netmaster/domain/TrafficRankInfo
    //   166: dup
    //   167: aload 21
    //   169: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   172: invokespecial 349	com/lionmobi/netmaster/domain/TrafficRankInfo:<init>	(Ljava/lang/String;)V
    //   175: astore 17
    //   177: aload_0
    //   178: getfield 433	com/lionmobi/netmaster/manager/o:o	Lcom/lionmobi/netmaster/f/ao;
    //   181: aload 21
    //   183: getfield 436	android/content/pm/ApplicationInfo:uid	I
    //   186: invokevirtual 442	com/lionmobi/netmaster/f/ao:getUidGroupedTrafficInfo	(I)Lcom/lionmobi/netmaster/f/ap;
    //   189: astore 18
    //   191: aload 16
    //   193: getstatic 51	com/lionmobi/netmaster/manager/o:g	Lcom/lionmobi/netmaster/manager/p;
    //   196: aload 21
    //   198: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   201: invokevirtual 445	com/lionmobi/netmaster/manager/p:getKey	(Ljava/lang/String;)Ljava/lang/String;
    //   204: invokeinterface 283 2 0
    //   209: checkcast 122	com/lionmobi/netmaster/domain/d
    //   212: astore 19
    //   214: aload 16
    //   216: getstatic 48	com/lionmobi/netmaster/manager/o:f	Lcom/lionmobi/netmaster/manager/p;
    //   219: aload 21
    //   221: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   224: invokevirtual 445	com/lionmobi/netmaster/manager/p:getKey	(Ljava/lang/String;)Ljava/lang/String;
    //   227: invokeinterface 283 2 0
    //   232: checkcast 122	com/lionmobi/netmaster/domain/d
    //   235: astore 20
    //   237: lload 5
    //   239: lstore 9
    //   241: lload 7
    //   243: lstore 11
    //   245: lload_3
    //   246: lstore 13
    //   248: aload 19
    //   250: ifnull -130 -> 120
    //   253: lload 5
    //   255: lstore 9
    //   257: lload 7
    //   259: lstore 11
    //   261: lload_3
    //   262: lstore 13
    //   264: aload 20
    //   266: ifnull -146 -> 120
    //   269: aload_0
    //   270: getfield 433	com/lionmobi/netmaster/manager/o:o	Lcom/lionmobi/netmaster/f/ao;
    //   273: aload 21
    //   275: getfield 436	android/content/pm/ApplicationInfo:uid	I
    //   278: invokevirtual 448	com/lionmobi/netmaster/f/ao:getTrafficStatsInfoByUid	(I)Ljava/util/List;
    //   281: astore 21
    //   283: aload_0
    //   284: getfield 78	com/lionmobi/netmaster/manager/o:d	Z
    //   287: ifeq +142 -> 429
    //   290: aload 18
    //   292: invokevirtual 451	com/lionmobi/netmaster/f/ap:getTotalMobile	()J
    //   295: aload 19
    //   297: invokevirtual 288	com/lionmobi/netmaster/domain/d:getPreValue	()J
    //   300: lsub
    //   301: lstore 13
    //   303: aload_0
    //   304: aload 19
    //   306: iconst_1
    //   307: invokespecial 453	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/domain/d;I)J
    //   310: lload 13
    //   312: ladd
    //   313: lstore 9
    //   315: lload 7
    //   317: lload 9
    //   319: ladd
    //   320: lstore 7
    //   322: aload_0
    //   323: aload 19
    //   325: invokespecial 455	com/lionmobi/netmaster/manager/o:b	(Lcom/lionmobi/netmaster/domain/d;)J
    //   328: lload 13
    //   330: ladd
    //   331: lstore 11
    //   333: lload_3
    //   334: lload 11
    //   336: ladd
    //   337: lstore_3
    //   338: aload_0
    //   339: aload 19
    //   341: invokespecial 457	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/domain/d;)J
    //   344: lload 13
    //   346: ladd
    //   347: lstore 13
    //   349: lload 5
    //   351: lload 13
    //   353: ladd
    //   354: lstore 5
    //   356: aload 17
    //   358: lload 11
    //   360: invokevirtual 370	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundRx	(J)V
    //   363: aload 17
    //   365: lload 9
    //   367: putfield 459	com/lionmobi/netmaster/domain/TrafficRankInfo:i	J
    //   370: aload 17
    //   372: lload 13
    //   374: putfield 461	com/lionmobi/netmaster/domain/TrafficRankInfo:j	J
    //   377: aload 17
    //   379: aload 18
    //   381: invokevirtual 464	com/lionmobi/netmaster/f/ap:getTotalWifi	()J
    //   384: aload 18
    //   386: invokevirtual 451	com/lionmobi/netmaster/f/ap:getTotalMobile	()J
    //   389: ladd
    //   390: aload 20
    //   392: invokevirtual 288	com/lionmobi/netmaster/domain/d:getPreValue	()J
    //   395: lsub
    //   396: aload_0
    //   397: aload 20
    //   399: invokespecial 455	com/lionmobi/netmaster/manager/o:b	(Lcom/lionmobi/netmaster/domain/d;)J
    //   402: ladd
    //   403: invokevirtual 467	com/lionmobi/netmaster/domain/TrafficRankInfo:setTotalTraffic	(J)V
    //   406: aload_1
    //   407: aload 17
    //   409: invokeinterface 314 2 0
    //   414: pop
    //   415: lload 5
    //   417: lstore 9
    //   419: lload 7
    //   421: lstore 11
    //   423: lload_3
    //   424: lstore 13
    //   426: goto -306 -> 120
    //   429: lload 5
    //   431: lstore 9
    //   433: lload 7
    //   435: lstore 11
    //   437: lload_3
    //   438: lstore 13
    //   440: aload 21
    //   442: ifnull -322 -> 120
    //   445: aload 21
    //   447: invokeinterface 142 1 0
    //   452: astore 18
    //   454: lconst_0
    //   455: lstore 11
    //   457: lconst_0
    //   458: lstore 9
    //   460: aload 18
    //   462: invokeinterface 148 1 0
    //   467: ifeq +69 -> 536
    //   470: aload 18
    //   472: invokeinterface 152 1 0
    //   477: checkcast 469	com/lionmobi/netmaster/f/aq
    //   480: astore 19
    //   482: lload 11
    //   484: aload 19
    //   486: getfield 470	com/lionmobi/netmaster/f/aq:f	J
    //   489: ladd
    //   490: aload 19
    //   492: getfield 471	com/lionmobi/netmaster/f/aq:h	J
    //   495: ladd
    //   496: lstore 11
    //   498: aload_0
    //   499: getfield 179	com/lionmobi/netmaster/manager/o:q	Landroid/content/Context;
    //   502: aload 19
    //   504: getfield 473	com/lionmobi/netmaster/f/aq:b	Ljava/lang/String;
    //   507: invokestatic 477	com/lionmobi/netmaster/f/ao:isMobileInterface	(Landroid/content/Context;Ljava/lang/String;)Z
    //   510: ifeq +99 -> 609
    //   513: aload 19
    //   515: getfield 470	com/lionmobi/netmaster/f/aq:f	J
    //   518: lstore 13
    //   520: aload 19
    //   522: getfield 471	com/lionmobi/netmaster/f/aq:h	J
    //   525: lload 9
    //   527: lload 13
    //   529: ladd
    //   530: ladd
    //   531: lstore 9
    //   533: goto -73 -> 460
    //   536: aload 17
    //   538: lload 11
    //   540: invokevirtual 467	com/lionmobi/netmaster/domain/TrafficRankInfo:setTotalTraffic	(J)V
    //   543: aload 17
    //   545: lload 9
    //   547: invokevirtual 370	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundRx	(J)V
    //   550: goto -144 -> 406
    //   553: aload_0
    //   554: getfield 70	com/lionmobi/netmaster/manager/o:k	Ljava/util/List;
    //   557: astore 15
    //   559: aload 15
    //   561: monitorenter
    //   562: aload_0
    //   563: getfield 70	com/lionmobi/netmaster/manager/o:k	Ljava/util/List;
    //   566: invokeinterface 321 1 0
    //   571: aload_0
    //   572: getfield 70	com/lionmobi/netmaster/manager/o:k	Ljava/util/List;
    //   575: aload_1
    //   576: invokeinterface 481 2 0
    //   581: pop
    //   582: aload_0
    //   583: lload 7
    //   585: putfield 74	com/lionmobi/netmaster/manager/o:b	J
    //   588: aload_0
    //   589: lload_3
    //   590: putfield 72	com/lionmobi/netmaster/manager/o:a	J
    //   593: aload_0
    //   594: lload 5
    //   596: putfield 76	com/lionmobi/netmaster/manager/o:c	J
    //   599: aload 15
    //   601: monitorexit
    //   602: return
    //   603: astore_1
    //   604: aload 15
    //   606: monitorexit
    //   607: aload_1
    //   608: athrow
    //   609: goto -76 -> 533
    //   612: astore 15
    //   614: aconst_null
    //   615: astore 15
    //   617: goto -604 -> 13
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	620	0	this	o
    //   0	620	1	paramE	e
    //   79	45	2	i1	int
    //   71	519	3	l1	long
    //   76	519	5	l2	long
    //   73	511	7	l3	long
    //   118	428	9	l4	long
    //   114	425	11	l5	long
    //   110	418	13	l6	long
    //   612	1	15	localException	Exception
    //   615	1	15	localObject1	Object
    //   41	174	16	localObject2	Object
    //   175	369	17	localTrafficRankInfo	TrafficRankInfo
    //   189	282	18	localObject3	Object
    //   212	309	19	localObject4	Object
    //   235	163	20	localD	d
    //   102	344	21	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   3	13	22	finally
    //   13	16	22	finally
    //   23	26	22	finally
    //   562	602	603	finally
    //   604	607	603	finally
    //   3	13	612	java/lang/Exception
  }
  
  /* Error */
  private void a(e paramE, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: ldc 2
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield 390	com/lionmobi/netmaster/manager/o:m	Landroid/content/pm/PackageManager;
    //   10: aload_2
    //   11: iconst_0
    //   12: invokevirtual 486	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   15: astore 6
    //   17: aload 6
    //   19: astore 5
    //   21: ldc 2
    //   23: monitorexit
    //   24: aload 5
    //   26: ifnonnull +16 -> 42
    //   29: return
    //   30: astore_1
    //   31: ldc 2
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    //   36: astore_1
    //   37: aload_1
    //   38: invokevirtual 277	java/lang/Exception:printStackTrace	()V
    //   41: return
    //   42: aload_1
    //   43: invokevirtual 490	com/lionmobi/netmaster/dao/e:getTrafficRankInfoDao	()Lcom/lionmobi/netmaster/dao/TrafficRankInfoDao;
    //   46: new 347	com/lionmobi/netmaster/domain/TrafficRankInfo
    //   49: dup
    //   50: aload_0
    //   51: aload_2
    //   52: invokespecial 387	com/lionmobi/netmaster/manager/o:a	(Ljava/lang/String;)Ljava/lang/String;
    //   55: invokespecial 349	com/lionmobi/netmaster/domain/TrafficRankInfo:<init>	(Ljava/lang/String;)V
    //   58: invokevirtual 385	com/lionmobi/netmaster/dao/TrafficRankInfoDao:insertOrReplace	(Ljava/lang/Object;)J
    //   61: pop2
    //   62: aload_1
    //   63: invokevirtual 405	com/lionmobi/netmaster/dao/e:getTrafficInfoDao	()Lcom/lionmobi/netmaster/dao/TrafficInfoDao;
    //   66: astore_1
    //   67: getstatic 58	com/lionmobi/netmaster/manager/o:i	[Lcom/lionmobi/netmaster/manager/p;
    //   70: astore 5
    //   72: aload 5
    //   74: arraylength
    //   75: istore 4
    //   77: iconst_0
    //   78: istore_3
    //   79: iload_3
    //   80: iload 4
    //   82: if_icmpge +60 -> 142
    //   85: aload 5
    //   87: iload_3
    //   88: aaload
    //   89: aload_2
    //   90: invokevirtual 445	com/lionmobi/netmaster/manager/p:getKey	(Ljava/lang/String;)Ljava/lang/String;
    //   93: astore 6
    //   95: aload_1
    //   96: aload 6
    //   98: invokevirtual 493	com/lionmobi/netmaster/dao/TrafficInfoDao:load	(Ljava/lang/Object;)Ljava/lang/Object;
    //   101: checkcast 122	com/lionmobi/netmaster/domain/d
    //   104: astore 7
    //   106: aload 7
    //   108: ifnonnull +14 -> 122
    //   111: aload_0
    //   112: aload_1
    //   113: aload 6
    //   115: lconst_0
    //   116: invokespecial 285	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/dao/TrafficInfoDao;Ljava/lang/String;J)V
    //   119: goto +24 -> 143
    //   122: aload 7
    //   124: lconst_0
    //   125: invokevirtual 344	com/lionmobi/netmaster/domain/d:setPreValue	(J)V
    //   128: aload_1
    //   129: aload 7
    //   131: invokevirtual 496	com/lionmobi/netmaster/dao/TrafficInfoDao:update	(Ljava/lang/Object;)V
    //   134: goto +9 -> 143
    //   137: astore 6
    //   139: goto -118 -> 21
    //   142: return
    //   143: iload_3
    //   144: iconst_1
    //   145: iadd
    //   146: istore_3
    //   147: goto -68 -> 79
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	this	o
    //   0	150	1	paramE	e
    //   0	150	2	paramString	String
    //   78	69	3	i1	int
    //   75	8	4	i2	int
    //   1	85	5	localObject1	Object
    //   15	99	6	localObject2	Object
    //   137	1	6	localException	Exception
    //   104	26	7	localD	d
    // Exception table:
    //   from	to	target	type
    //   6	17	30	finally
    //   21	24	30	finally
    //   31	34	30	finally
    //   3	6	36	java/lang/Exception
    //   34	36	36	java/lang/Exception
    //   42	77	36	java/lang/Exception
    //   85	106	36	java/lang/Exception
    //   111	119	36	java/lang/Exception
    //   122	134	36	java/lang/Exception
    //   6	17	137	java/lang/Exception
  }
  
  private void a(e arg1, boolean paramBoolean)
  {
    if ((paramBoolean) || (this.k.size() == 0)) {
      a(???);
    }
    ArrayList localArrayList = new ArrayList();
    synchronized (this.k)
    {
      localArrayList.addAll(this.k);
      long l1 = this.b;
      long l2 = this.a;
      long l3 = this.c;
      if (localArrayList != null)
      {
        ??? = new EventTrafficReports.EventTrafficReportsResult(localArrayList);
        ???.a = this.d;
        ???.c = l2;
        ???.b = l1;
        ???.d = l3;
        c.c.getDefault().post(new h(???));
      }
      return;
    }
  }
  
  private boolean a(d paramD, AtomicLong paramAtomicLong, long paramLong1, long paramLong2)
  {
    int i2 = (int)(paramLong1 / 30000L);
    if (paramD != null)
    {
      paramD = d.dataAsList(paramD.getDots(), 120);
      paramLong1 = 0L;
      int i1 = 1;
      while (i1 < i2 + 1)
      {
        paramLong1 += ((Long)paramD.get(paramD.size() - i1)).longValue();
        i1 += 1;
      }
      if (paramAtomicLong != null) {
        paramAtomicLong.addAndGet(paramLong1);
      }
      return paramLong1 > paramLong2;
    }
    return false;
  }
  
  private long b(d paramD)
  {
    Object localObject = d.dataAsList(paramD.getHours(), 24);
    paramD = d.dataAsList(paramD.getDays(), 31);
    localObject = ((List)localObject).iterator();
    for (long l1 = 0L; ((Iterator)localObject).hasNext(); l1 = ((Long)((Iterator)localObject).next()).longValue() + l1) {}
    int i1 = 30;
    int i3 = paramD.size();
    if (30 > i3) {
      i1 = i3;
    }
    localObject = new GregorianCalendar();
    ((Calendar)localObject).setTime(new Date());
    int i4 = ((Calendar)localObject).get(5);
    int i2 = 1;
    while (i2 <= i1 - (i4 - 1))
    {
      l1 += ((Long)paramD.get(i3 - i2)).longValue();
      i2 += 1;
    }
    return l1;
  }
  
  private Set b()
  {
    HashSet localHashSet = new HashSet();
    ??? = this.p.getRunningServices(Integer.MAX_VALUE);
    if (??? != null)
    {
      ??? = ((List)???).iterator();
      while (((Iterator)???).hasNext()) {
        localHashSet.add(((ActivityManager.RunningServiceInfo)((Iterator)???).next()).service.getPackageName());
      }
    }
    ??? = this.p.getRunningAppProcesses();
    if (??? != null)
    {
      ??? = ((List)???).iterator();
      while (((Iterator)???).hasNext()) {
        localHashSet.add(((android.app.ActivityManager.RunningAppProcessInfo)???.next()).pkgList[0]);
      }
    }
    for (;;)
    {
      String str;
      synchronized (this.j)
      {
        long l1 = System.currentTimeMillis();
        Iterator localIterator = new HashSet(this.j.keySet()).iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        str = (String)localIterator.next();
        if (l1 - ((Long)this.j.get(str)).longValue() < 120000L) {
          localHashSet.remove(str);
        }
      }
      this.j.remove(str);
    }
    return localSet;
  }
  
  /* Error */
  private void b(e paramE)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 574	com/lionmobi/netmaster/manager/o:n	Landroid/content/SharedPreferences;
    //   4: ldc_w 576
    //   7: lconst_0
    //   8: invokeinterface 581 4 0
    //   13: invokestatic 586	android/net/TrafficStats:getTotalRxBytes	()J
    //   16: lcmp
    //   17: ifle +623 -> 640
    //   20: iconst_1
    //   21: istore 6
    //   23: ldc_w 588
    //   26: new 167	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   33: ldc_w 590
    //   36: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: iload 6
    //   41: invokevirtual 593	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   44: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   47: invokestatic 598	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   50: pop
    //   51: ldc 2
    //   53: monitorenter
    //   54: aload_0
    //   55: getfield 390	com/lionmobi/netmaster/manager/o:m	Landroid/content/pm/PackageManager;
    //   58: iconst_0
    //   59: invokevirtual 396	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   62: astore 7
    //   64: ldc 2
    //   66: monitorexit
    //   67: aload 7
    //   69: ifnonnull +10 -> 79
    //   72: return
    //   73: astore_1
    //   74: ldc 2
    //   76: monitorexit
    //   77: aload_1
    //   78: athrow
    //   79: aload_1
    //   80: invokevirtual 405	com/lionmobi/netmaster/dao/e:getTrafficInfoDao	()Lcom/lionmobi/netmaster/dao/TrafficInfoDao;
    //   83: astore 8
    //   85: aload 8
    //   87: invokevirtual 409	com/lionmobi/netmaster/dao/TrafficInfoDao:loadAll	()Ljava/util/List;
    //   90: astore 9
    //   92: aload 9
    //   94: invokestatic 415	com/lionmobi/netmaster/domain/d:listAsMap	(Ljava/util/List;)Ljava/util/Map;
    //   97: astore 10
    //   99: aload_1
    //   100: invokevirtual 490	com/lionmobi/netmaster/dao/e:getTrafficRankInfoDao	()Lcom/lionmobi/netmaster/dao/TrafficRankInfoDao;
    //   103: astore_1
    //   104: aload_1
    //   105: invokevirtual 599	com/lionmobi/netmaster/dao/TrafficRankInfoDao:loadAll	()Ljava/util/List;
    //   108: astore 11
    //   110: new 62	java/util/HashMap
    //   113: dup
    //   114: invokespecial 63	java/util/HashMap:<init>	()V
    //   117: astore 12
    //   119: iconst_0
    //   120: istore_2
    //   121: iload_2
    //   122: aload 11
    //   124: invokeinterface 161 1 0
    //   129: if_icmpge +38 -> 167
    //   132: aload 11
    //   134: iload_2
    //   135: invokeinterface 164 2 0
    //   140: checkcast 347	com/lionmobi/netmaster/domain/TrafficRankInfo
    //   143: astore 13
    //   145: aload 12
    //   147: aload 13
    //   149: invokevirtual 602	com/lionmobi/netmaster/domain/TrafficRankInfo:getPname	()Ljava/lang/String;
    //   152: aload 13
    //   154: invokeinterface 606 3 0
    //   159: pop
    //   160: iload_2
    //   161: iconst_1
    //   162: iadd
    //   163: istore_2
    //   164: goto -43 -> 121
    //   167: aload_0
    //   168: getfield 433	com/lionmobi/netmaster/manager/o:o	Lcom/lionmobi/netmaster/f/ao;
    //   171: invokevirtual 609	com/lionmobi/netmaster/f/ao:reload	()V
    //   174: iconst_0
    //   175: istore_3
    //   176: iload_3
    //   177: aload 7
    //   179: invokeinterface 161 1 0
    //   184: if_icmpge +439 -> 623
    //   187: aload 7
    //   189: iload_3
    //   190: invokeinterface 164 2 0
    //   195: checkcast 417	android/content/pm/ApplicationInfo
    //   198: astore 13
    //   200: getstatic 422	com/lionmobi/netmaster/f/g:b	Ljava/util/HashSet;
    //   203: aload 13
    //   205: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   208: invokevirtual 431	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   211: ifeq +44 -> 255
    //   214: iload_3
    //   215: iconst_1
    //   216: iadd
    //   217: istore_3
    //   218: goto -42 -> 176
    //   221: astore 13
    //   223: ldc_w 588
    //   226: ldc_w 611
    //   229: aload 13
    //   231: invokestatic 614	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   234: pop
    //   235: goto -61 -> 174
    //   238: astore 13
    //   240: ldc_w 588
    //   243: ldc_w 616
    //   246: aload 13
    //   248: invokestatic 614	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   251: pop
    //   252: goto -78 -> 174
    //   255: aload_0
    //   256: getfield 433	com/lionmobi/netmaster/manager/o:o	Lcom/lionmobi/netmaster/f/ao;
    //   259: aload 13
    //   261: getfield 436	android/content/pm/ApplicationInfo:uid	I
    //   264: invokevirtual 442	com/lionmobi/netmaster/f/ao:getUidGroupedTrafficInfo	(I)Lcom/lionmobi/netmaster/f/ap;
    //   267: astore 14
    //   269: iconst_0
    //   270: istore_2
    //   271: getstatic 58	com/lionmobi/netmaster/manager/o:i	[Lcom/lionmobi/netmaster/manager/p;
    //   274: astore 15
    //   276: aload 15
    //   278: arraylength
    //   279: istore 5
    //   281: iconst_0
    //   282: istore 4
    //   284: iload 4
    //   286: iload 5
    //   288: if_icmpge +129 -> 417
    //   291: aload 15
    //   293: iload 4
    //   295: aaload
    //   296: astore 16
    //   298: aload 16
    //   300: aload 13
    //   302: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   305: invokevirtual 445	com/lionmobi/netmaster/manager/p:getKey	(Ljava/lang/String;)Ljava/lang/String;
    //   308: astore 17
    //   310: aload 10
    //   312: aload 17
    //   314: invokeinterface 283 2 0
    //   319: checkcast 122	com/lionmobi/netmaster/domain/d
    //   322: astore 18
    //   324: aload 18
    //   326: ifnonnull +27 -> 353
    //   329: aload_0
    //   330: aload 8
    //   332: aload 17
    //   334: aload 16
    //   336: aload 14
    //   338: invokevirtual 620	com/lionmobi/netmaster/manager/p:getValue	(Lcom/lionmobi/netmaster/f/ap;)J
    //   341: invokespecial 285	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/dao/TrafficInfoDao;Ljava/lang/String;J)V
    //   344: iload 4
    //   346: iconst_1
    //   347: iadd
    //   348: istore 4
    //   350: goto -66 -> 284
    //   353: aload 16
    //   355: getstatic 48	com/lionmobi/netmaster/manager/o:f	Lcom/lionmobi/netmaster/manager/p;
    //   358: if_acmpne +279 -> 637
    //   361: aload 18
    //   363: invokevirtual 288	com/lionmobi/netmaster/domain/d:getPreValue	()J
    //   366: aload 16
    //   368: aload 14
    //   370: invokevirtual 620	com/lionmobi/netmaster/manager/p:getValue	(Lcom/lionmobi/netmaster/f/ap;)J
    //   373: lcmp
    //   374: ifle +19 -> 393
    //   377: iconst_1
    //   378: istore_2
    //   379: iload 6
    //   381: ifeq +17 -> 398
    //   384: aload 18
    //   386: lconst_0
    //   387: invokevirtual 344	com/lionmobi/netmaster/domain/d:setPreValue	(J)V
    //   390: goto -46 -> 344
    //   393: iconst_0
    //   394: istore_2
    //   395: goto -16 -> 379
    //   398: iload_2
    //   399: ifeq +15 -> 414
    //   402: aload 18
    //   404: aload 16
    //   406: aload 14
    //   408: invokevirtual 620	com/lionmobi/netmaster/manager/p:getValue	(Lcom/lionmobi/netmaster/f/ap;)J
    //   411: invokevirtual 344	com/lionmobi/netmaster/domain/d:setPreValue	(J)V
    //   414: goto -70 -> 344
    //   417: aload 12
    //   419: aload 13
    //   421: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   424: invokeinterface 283 2 0
    //   429: checkcast 347	com/lionmobi/netmaster/domain/TrafficRankInfo
    //   432: astore 15
    //   434: aload 12
    //   436: aload_0
    //   437: aload 13
    //   439: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   442: invokespecial 387	com/lionmobi/netmaster/manager/o:a	(Ljava/lang/String;)Ljava/lang/String;
    //   445: invokeinterface 283 2 0
    //   450: checkcast 347	com/lionmobi/netmaster/domain/TrafficRankInfo
    //   453: astore 16
    //   455: aload 15
    //   457: ifnull +8 -> 465
    //   460: aload 16
    //   462: ifnonnull +18 -> 480
    //   465: aload_0
    //   466: aload_1
    //   467: aload 13
    //   469: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   472: aload 14
    //   474: invokespecial 622	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/dao/TrafficRankInfoDao;Ljava/lang/String;Lcom/lionmobi/netmaster/f/ap;)V
    //   477: goto -263 -> 214
    //   480: iload 6
    //   482: ifeq +54 -> 536
    //   485: aload 16
    //   487: lconst_0
    //   488: invokevirtual 356	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiBackgroundTx	(J)V
    //   491: aload 16
    //   493: lconst_0
    //   494: invokevirtual 361	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiBackgroundRx	(J)V
    //   497: aload 16
    //   499: lconst_0
    //   500: invokevirtual 366	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundTx	(J)V
    //   503: aload 16
    //   505: lconst_0
    //   506: invokevirtual 370	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundRx	(J)V
    //   509: aload 16
    //   511: lconst_0
    //   512: invokevirtual 373	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiBackgroundTx	(J)V
    //   515: aload 16
    //   517: lconst_0
    //   518: invokevirtual 376	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiBackgroundRx	(J)V
    //   521: aload 16
    //   523: lconst_0
    //   524: invokevirtual 379	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiForegroundTx	(J)V
    //   527: aload 16
    //   529: lconst_0
    //   530: invokevirtual 382	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiForegroundRx	(J)V
    //   533: goto -319 -> 214
    //   536: iload_2
    //   537: ifeq -323 -> 214
    //   540: aload 16
    //   542: aload 14
    //   544: getfield 353	com/lionmobi/netmaster/f/ap:h	J
    //   547: invokevirtual 356	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiBackgroundTx	(J)V
    //   550: aload 16
    //   552: aload 14
    //   554: getfield 358	com/lionmobi/netmaster/f/ap:g	J
    //   557: invokevirtual 361	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiBackgroundRx	(J)V
    //   560: aload 16
    //   562: aload 14
    //   564: getfield 363	com/lionmobi/netmaster/f/ap:f	J
    //   567: invokevirtual 366	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundTx	(J)V
    //   570: aload 16
    //   572: aload 14
    //   574: getfield 367	com/lionmobi/netmaster/f/ap:e	J
    //   577: invokevirtual 370	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundRx	(J)V
    //   580: aload 16
    //   582: aload 14
    //   584: getfield 623	com/lionmobi/netmaster/f/ap:d	J
    //   587: invokevirtual 373	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiBackgroundTx	(J)V
    //   590: aload 16
    //   592: aload 14
    //   594: getfield 624	com/lionmobi/netmaster/f/ap:c	J
    //   597: invokevirtual 376	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiBackgroundRx	(J)V
    //   600: aload 16
    //   602: aload 14
    //   604: getfield 625	com/lionmobi/netmaster/f/ap:b	J
    //   607: invokevirtual 379	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiForegroundTx	(J)V
    //   610: aload 16
    //   612: aload 14
    //   614: getfield 626	com/lionmobi/netmaster/f/ap:a	J
    //   617: invokevirtual 382	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiForegroundRx	(J)V
    //   620: goto -406 -> 214
    //   623: aload 8
    //   625: aload 9
    //   627: invokevirtual 630	com/lionmobi/netmaster/dao/TrafficInfoDao:updateInTx	(Ljava/lang/Iterable;)V
    //   630: aload_1
    //   631: aload 11
    //   633: invokevirtual 631	com/lionmobi/netmaster/dao/TrafficRankInfoDao:updateInTx	(Ljava/lang/Iterable;)V
    //   636: return
    //   637: goto -258 -> 379
    //   640: iconst_0
    //   641: istore 6
    //   643: goto -620 -> 23
    //   646: astore 7
    //   648: aconst_null
    //   649: astore 7
    //   651: goto -587 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	654	0	this	o
    //   0	654	1	paramE	e
    //   120	417	2	i1	int
    //   175	43	3	i2	int
    //   282	67	4	i3	int
    //   279	10	5	i4	int
    //   21	621	6	bool	boolean
    //   62	126	7	localList1	List
    //   646	1	7	localException1	Exception
    //   649	1	7	localObject1	Object
    //   83	541	8	localTrafficInfoDao	TrafficInfoDao
    //   90	536	9	localList2	List
    //   97	214	10	localMap	Map
    //   108	524	11	localList3	List
    //   117	318	12	localHashMap	HashMap
    //   143	61	13	localObject2	Object
    //   221	9	13	localIOException	java.io.IOException
    //   238	230	13	localException2	Exception
    //   267	346	14	localAp	ap
    //   274	182	15	localObject3	Object
    //   296	315	16	localTrafficRankInfo	TrafficRankInfo
    //   308	25	17	str	String
    //   322	81	18	localD	d
    // Exception table:
    //   from	to	target	type
    //   54	64	73	finally
    //   64	67	73	finally
    //   74	77	73	finally
    //   167	174	221	java/io/IOException
    //   167	174	238	java/lang/Exception
    //   54	64	646	java/lang/Exception
  }
  
  private void b(e arg1, boolean paramBoolean)
  {
    if ((paramBoolean) || (this.k.size() == 0)) {
      a(???);
    }
    ArrayList localArrayList = new ArrayList();
    synchronized (this.k)
    {
      localArrayList.addAll(this.k);
      long l1 = this.b;
      long l2 = this.a;
      ??? = new EventQueryTrafficRankInfo.EventQueryTrafficRankInfoResult(localArrayList);
      ???.a = this.d;
      ???.c = l2;
      ???.b = l1;
      c.c.getDefault().post(new h(???));
      return;
    }
  }
  
  /* Error */
  private void c(e paramE, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: getfield 390	com/lionmobi/netmaster/manager/o:m	Landroid/content/pm/PackageManager;
    //   7: iconst_0
    //   8: invokevirtual 396	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   11: astore 54
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload 54
    //   18: ifnonnull +10 -> 28
    //   21: return
    //   22: astore_1
    //   23: ldc 2
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    //   28: ldc_w 588
    //   31: new 167	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   38: ldc_w 639
    //   41: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: invokestatic 259	java/lang/System:currentTimeMillis	()J
    //   47: invokevirtual 234	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   50: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokestatic 598	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   56: pop
    //   57: aload_0
    //   58: getfield 433	com/lionmobi/netmaster/manager/o:o	Lcom/lionmobi/netmaster/f/ao;
    //   61: invokevirtual 609	com/lionmobi/netmaster/f/ao:reload	()V
    //   64: aload_0
    //   65: getfield 574	com/lionmobi/netmaster/manager/o:n	Landroid/content/SharedPreferences;
    //   68: invokeinterface 643 1 0
    //   73: astore 55
    //   75: aload 55
    //   77: ldc_w 576
    //   80: invokestatic 586	android/net/TrafficStats:getTotalRxBytes	()J
    //   83: invokeinterface 649 4 0
    //   88: pop
    //   89: aload 55
    //   91: invokeinterface 652 1 0
    //   96: aload_1
    //   97: invokevirtual 490	com/lionmobi/netmaster/dao/e:getTrafficRankInfoDao	()Lcom/lionmobi/netmaster/dao/TrafficRankInfoDao;
    //   100: astore 58
    //   102: aload 58
    //   104: invokevirtual 599	com/lionmobi/netmaster/dao/TrafficRankInfoDao:loadAll	()Ljava/util/List;
    //   107: astore 59
    //   109: new 62	java/util/HashMap
    //   112: dup
    //   113: invokespecial 63	java/util/HashMap:<init>	()V
    //   116: astore 60
    //   118: iconst_0
    //   119: istore_3
    //   120: iload_3
    //   121: aload 59
    //   123: invokeinterface 161 1 0
    //   128: if_icmpge +51 -> 179
    //   131: aload 59
    //   133: iload_3
    //   134: invokeinterface 164 2 0
    //   139: checkcast 347	com/lionmobi/netmaster/domain/TrafficRankInfo
    //   142: astore 55
    //   144: aload 60
    //   146: aload 55
    //   148: invokevirtual 602	com/lionmobi/netmaster/domain/TrafficRankInfo:getPname	()Ljava/lang/String;
    //   151: aload 55
    //   153: invokeinterface 606 3 0
    //   158: pop
    //   159: iload_3
    //   160: iconst_1
    //   161: iadd
    //   162: istore_3
    //   163: goto -43 -> 120
    //   166: astore_1
    //   167: ldc_w 588
    //   170: ldc_w 616
    //   173: aload_1
    //   174: invokestatic 614	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   177: pop
    //   178: return
    //   179: aconst_null
    //   180: astore 57
    //   182: aconst_null
    //   183: astore 55
    //   185: iload_2
    //   186: ifeq +1062 -> 1248
    //   189: aload_1
    //   190: invokevirtual 405	com/lionmobi/netmaster/dao/e:getTrafficInfoDao	()Lcom/lionmobi/netmaster/dao/TrafficInfoDao;
    //   193: astore_1
    //   194: aload_1
    //   195: invokevirtual 409	com/lionmobi/netmaster/dao/TrafficInfoDao:loadAll	()Ljava/util/List;
    //   198: astore 56
    //   200: aload 56
    //   202: invokestatic 415	com/lionmobi/netmaster/domain/d:listAsMap	(Ljava/util/List;)Ljava/util/Map;
    //   205: astore 55
    //   207: new 518	java/util/concurrent/atomic/AtomicLong
    //   210: dup
    //   211: invokespecial 653	java/util/concurrent/atomic/AtomicLong:<init>	()V
    //   214: astore 57
    //   216: new 62	java/util/HashMap
    //   219: dup
    //   220: invokespecial 63	java/util/HashMap:<init>	()V
    //   223: astore 62
    //   225: aload_0
    //   226: invokespecial 655	com/lionmobi/netmaster/manager/o:b	()Ljava/util/Set;
    //   229: astore 63
    //   231: new 62	java/util/HashMap
    //   234: dup
    //   235: invokespecial 63	java/util/HashMap:<init>	()V
    //   238: astore 61
    //   240: new 518	java/util/concurrent/atomic/AtomicLong
    //   243: dup
    //   244: invokespecial 653	java/util/concurrent/atomic/AtomicLong:<init>	()V
    //   247: astore 64
    //   249: iconst_0
    //   250: istore_3
    //   251: iload_3
    //   252: aload 54
    //   254: invokeinterface 161 1 0
    //   259: if_icmpge +850 -> 1109
    //   262: aload 54
    //   264: iload_3
    //   265: invokeinterface 164 2 0
    //   270: checkcast 417	android/content/pm/ApplicationInfo
    //   273: astore 65
    //   275: getstatic 422	com/lionmobi/netmaster/f/g:b	Ljava/util/HashSet;
    //   278: aload 65
    //   280: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   283: invokevirtual 431	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   286: ifeq +10 -> 296
    //   289: iload_3
    //   290: iconst_1
    //   291: iadd
    //   292: istore_3
    //   293: goto -42 -> 251
    //   296: aload 60
    //   298: aload 65
    //   300: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   303: invokeinterface 283 2 0
    //   308: checkcast 347	com/lionmobi/netmaster/domain/TrafficRankInfo
    //   311: astore 67
    //   313: aload 60
    //   315: aload_0
    //   316: aload 65
    //   318: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   321: invokespecial 387	com/lionmobi/netmaster/manager/o:a	(Ljava/lang/String;)Ljava/lang/String;
    //   324: invokeinterface 283 2 0
    //   329: checkcast 347	com/lionmobi/netmaster/domain/TrafficRankInfo
    //   332: astore 68
    //   334: aload_0
    //   335: getfield 433	com/lionmobi/netmaster/manager/o:o	Lcom/lionmobi/netmaster/f/ao;
    //   338: aload 65
    //   340: getfield 436	android/content/pm/ApplicationInfo:uid	I
    //   343: invokevirtual 442	com/lionmobi/netmaster/f/ao:getUidGroupedTrafficInfo	(I)Lcom/lionmobi/netmaster/f/ap;
    //   346: astore 66
    //   348: aload 67
    //   350: ifnull +8 -> 358
    //   353: aload 68
    //   355: ifnonnull +19 -> 374
    //   358: aload_0
    //   359: aload 58
    //   361: aload 65
    //   363: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   366: aload 66
    //   368: invokespecial 622	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/dao/TrafficRankInfoDao;Ljava/lang/String;Lcom/lionmobi/netmaster/f/ap;)V
    //   371: goto -82 -> 289
    //   374: aload 67
    //   376: invokevirtual 658	com/lionmobi/netmaster/domain/TrafficRankInfo:getMobiBackgroundRx	()J
    //   379: lstore 6
    //   381: aload 66
    //   383: getfield 358	com/lionmobi/netmaster/f/ap:g	J
    //   386: lstore 8
    //   388: aload 68
    //   390: invokevirtual 658	com/lionmobi/netmaster/domain/TrafficRankInfo:getMobiBackgroundRx	()J
    //   393: lstore 10
    //   395: aload 67
    //   397: invokevirtual 661	com/lionmobi/netmaster/domain/TrafficRankInfo:getMobiBackgroundTx	()J
    //   400: lstore 12
    //   402: aload 66
    //   404: getfield 353	com/lionmobi/netmaster/f/ap:h	J
    //   407: lstore 14
    //   409: aload 68
    //   411: invokevirtual 661	com/lionmobi/netmaster/domain/TrafficRankInfo:getMobiBackgroundTx	()J
    //   414: lstore 16
    //   416: aload 67
    //   418: invokevirtual 664	com/lionmobi/netmaster/domain/TrafficRankInfo:getMobiForegroundRx	()J
    //   421: lstore 18
    //   423: aload 66
    //   425: getfield 367	com/lionmobi/netmaster/f/ap:e	J
    //   428: lstore 20
    //   430: aload 68
    //   432: invokevirtual 664	com/lionmobi/netmaster/domain/TrafficRankInfo:getMobiForegroundRx	()J
    //   435: lstore 22
    //   437: aload 67
    //   439: invokevirtual 667	com/lionmobi/netmaster/domain/TrafficRankInfo:getMobiForegroundTx	()J
    //   442: lstore 24
    //   444: aload 66
    //   446: getfield 363	com/lionmobi/netmaster/f/ap:f	J
    //   449: lstore 26
    //   451: aload 68
    //   453: invokevirtual 667	com/lionmobi/netmaster/domain/TrafficRankInfo:getMobiForegroundTx	()J
    //   456: lstore 28
    //   458: aload 67
    //   460: invokevirtual 670	com/lionmobi/netmaster/domain/TrafficRankInfo:getWifiBackgroundRx	()J
    //   463: lstore 30
    //   465: aload 66
    //   467: getfield 624	com/lionmobi/netmaster/f/ap:c	J
    //   470: lstore 32
    //   472: aload 68
    //   474: invokevirtual 670	com/lionmobi/netmaster/domain/TrafficRankInfo:getWifiBackgroundRx	()J
    //   477: lstore 34
    //   479: aload 67
    //   481: invokevirtual 673	com/lionmobi/netmaster/domain/TrafficRankInfo:getWifiBackgroundTx	()J
    //   484: lstore 36
    //   486: aload 66
    //   488: getfield 623	com/lionmobi/netmaster/f/ap:d	J
    //   491: lstore 38
    //   493: aload 68
    //   495: invokevirtual 673	com/lionmobi/netmaster/domain/TrafficRankInfo:getWifiBackgroundTx	()J
    //   498: lstore 40
    //   500: aload 67
    //   502: invokevirtual 676	com/lionmobi/netmaster/domain/TrafficRankInfo:getWifiForegroundRx	()J
    //   505: lstore 42
    //   507: aload 66
    //   509: getfield 626	com/lionmobi/netmaster/f/ap:a	J
    //   512: lstore 44
    //   514: aload 68
    //   516: invokevirtual 676	com/lionmobi/netmaster/domain/TrafficRankInfo:getWifiForegroundRx	()J
    //   519: lstore 46
    //   521: aload 67
    //   523: invokevirtual 679	com/lionmobi/netmaster/domain/TrafficRankInfo:getWifiForegroundTx	()J
    //   526: lstore 48
    //   528: aload 66
    //   530: getfield 625	com/lionmobi/netmaster/f/ap:b	J
    //   533: lstore 50
    //   535: aload 68
    //   537: invokevirtual 679	com/lionmobi/netmaster/domain/TrafficRankInfo:getWifiForegroundTx	()J
    //   540: lstore 52
    //   542: aload 67
    //   544: lload 6
    //   546: lload 8
    //   548: lload 10
    //   550: lsub
    //   551: ladd
    //   552: invokevirtual 361	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiBackgroundRx	(J)V
    //   555: aload 67
    //   557: lload 12
    //   559: lload 14
    //   561: lload 16
    //   563: lsub
    //   564: ladd
    //   565: invokevirtual 356	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiBackgroundTx	(J)V
    //   568: aload 67
    //   570: lload 18
    //   572: lload 20
    //   574: lload 22
    //   576: lsub
    //   577: ladd
    //   578: invokevirtual 370	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundRx	(J)V
    //   581: aload 67
    //   583: lload 24
    //   585: lload 26
    //   587: lload 28
    //   589: lsub
    //   590: ladd
    //   591: invokevirtual 366	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundTx	(J)V
    //   594: aload 67
    //   596: lload 30
    //   598: lload 32
    //   600: lload 34
    //   602: lsub
    //   603: ladd
    //   604: invokevirtual 376	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiBackgroundRx	(J)V
    //   607: aload 67
    //   609: lload 36
    //   611: lload 38
    //   613: lload 40
    //   615: lsub
    //   616: ladd
    //   617: invokevirtual 373	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiBackgroundTx	(J)V
    //   620: aload 67
    //   622: lload 42
    //   624: lload 44
    //   626: lload 46
    //   628: lsub
    //   629: ladd
    //   630: invokevirtual 382	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiForegroundRx	(J)V
    //   633: aload 67
    //   635: lload 48
    //   637: lload 50
    //   639: lload 52
    //   641: lsub
    //   642: ladd
    //   643: invokevirtual 379	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiForegroundTx	(J)V
    //   646: aload 68
    //   648: aload 66
    //   650: getfield 358	com/lionmobi/netmaster/f/ap:g	J
    //   653: invokevirtual 361	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiBackgroundRx	(J)V
    //   656: aload 68
    //   658: aload 66
    //   660: getfield 353	com/lionmobi/netmaster/f/ap:h	J
    //   663: invokevirtual 356	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiBackgroundTx	(J)V
    //   666: aload 68
    //   668: aload 66
    //   670: getfield 367	com/lionmobi/netmaster/f/ap:e	J
    //   673: invokevirtual 370	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundRx	(J)V
    //   676: aload 68
    //   678: aload 66
    //   680: getfield 363	com/lionmobi/netmaster/f/ap:f	J
    //   683: invokevirtual 366	com/lionmobi/netmaster/domain/TrafficRankInfo:setMobiForegroundTx	(J)V
    //   686: aload 68
    //   688: aload 66
    //   690: getfield 624	com/lionmobi/netmaster/f/ap:c	J
    //   693: invokevirtual 376	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiBackgroundRx	(J)V
    //   696: aload 68
    //   698: aload 66
    //   700: getfield 623	com/lionmobi/netmaster/f/ap:d	J
    //   703: invokevirtual 373	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiBackgroundTx	(J)V
    //   706: aload 68
    //   708: aload 66
    //   710: getfield 626	com/lionmobi/netmaster/f/ap:a	J
    //   713: invokevirtual 382	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiForegroundRx	(J)V
    //   716: aload 68
    //   718: aload 66
    //   720: getfield 625	com/lionmobi/netmaster/f/ap:b	J
    //   723: invokevirtual 379	com/lionmobi/netmaster/domain/TrafficRankInfo:setWifiForegroundTx	(J)V
    //   726: iload_2
    //   727: ifeq -438 -> 289
    //   730: getstatic 58	com/lionmobi/netmaster/manager/o:i	[Lcom/lionmobi/netmaster/manager/p;
    //   733: astore 67
    //   735: aload 67
    //   737: arraylength
    //   738: istore 5
    //   740: iconst_0
    //   741: istore 4
    //   743: iload 4
    //   745: iload 5
    //   747: if_icmpge +43 -> 790
    //   750: aload 67
    //   752: iload 4
    //   754: aaload
    //   755: astore 68
    //   757: aload_0
    //   758: aload_1
    //   759: aload 55
    //   761: aload 68
    //   763: aload 65
    //   765: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   768: invokevirtual 445	com/lionmobi/netmaster/manager/p:getKey	(Ljava/lang/String;)Ljava/lang/String;
    //   771: aload 68
    //   773: aload 66
    //   775: invokevirtual 620	com/lionmobi/netmaster/manager/p:getValue	(Lcom/lionmobi/netmaster/f/ap;)J
    //   778: invokespecial 681	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/dao/TrafficInfoDao;Ljava/util/Map;Ljava/lang/String;J)V
    //   781: iload 4
    //   783: iconst_1
    //   784: iadd
    //   785: istore 4
    //   787: goto -44 -> 743
    //   790: aload 55
    //   792: getstatic 54	com/lionmobi/netmaster/manager/o:h	Lcom/lionmobi/netmaster/manager/p;
    //   795: aload 65
    //   797: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   800: invokevirtual 445	com/lionmobi/netmaster/manager/p:getKey	(Ljava/lang/String;)Ljava/lang/String;
    //   803: invokeinterface 283 2 0
    //   808: checkcast 122	com/lionmobi/netmaster/domain/d
    //   811: astore 67
    //   813: aload 63
    //   815: aload 65
    //   817: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   820: invokeinterface 682 2 0
    //   825: ifeq +89 -> 914
    //   828: aload 57
    //   830: invokevirtual 684	java/util/concurrent/atomic/AtomicLong:get	()J
    //   833: lstore 6
    //   835: aload_0
    //   836: aload 67
    //   838: aload 57
    //   840: ldc2_w 685
    //   843: lconst_0
    //   844: invokespecial 688	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/domain/d;Ljava/util/concurrent/atomic/AtomicLong;JJ)Z
    //   847: pop
    //   848: aload 57
    //   850: invokevirtual 684	java/util/concurrent/atomic/AtomicLong:get	()J
    //   853: lload 6
    //   855: lsub
    //   856: ldc2_w 689
    //   859: lcmp
    //   860: iflt +54 -> 914
    //   863: ldc_w 692
    //   866: new 167	java/lang/StringBuilder
    //   869: dup
    //   870: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   873: ldc_w 694
    //   876: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   879: aload 65
    //   881: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   884: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   887: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   890: invokestatic 239	com/lionmobi/netmaster/f/r:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   893: aload 61
    //   895: aload 65
    //   897: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   900: aload_0
    //   901: aload 67
    //   903: iconst_1
    //   904: invokespecial 453	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/domain/d;I)J
    //   907: invokestatic 310	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   910: invokevirtual 695	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   913: pop
    //   914: aload 55
    //   916: getstatic 51	com/lionmobi/netmaster/manager/o:g	Lcom/lionmobi/netmaster/manager/p;
    //   919: aload 65
    //   921: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   924: invokevirtual 445	com/lionmobi/netmaster/manager/p:getKey	(Ljava/lang/String;)Ljava/lang/String;
    //   927: invokeinterface 283 2 0
    //   932: checkcast 122	com/lionmobi/netmaster/domain/d
    //   935: astore 67
    //   937: new 518	java/util/concurrent/atomic/AtomicLong
    //   940: dup
    //   941: invokespecial 653	java/util/concurrent/atomic/AtomicLong:<init>	()V
    //   944: astore 68
    //   946: aload_0
    //   947: aload 67
    //   949: aload 68
    //   951: ldc2_w 685
    //   954: lconst_0
    //   955: invokespecial 688	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/domain/d;Ljava/util/concurrent/atomic/AtomicLong;JJ)Z
    //   958: pop
    //   959: aload 68
    //   961: invokevirtual 684	java/util/concurrent/atomic/AtomicLong:get	()J
    //   964: ldc2_w 689
    //   967: lcmp
    //   968: iflt +54 -> 1022
    //   971: aload 62
    //   973: aload 65
    //   975: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   978: aload_0
    //   979: aload 67
    //   981: iconst_1
    //   982: invokespecial 453	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/domain/d;I)J
    //   985: invokestatic 310	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   988: invokevirtual 695	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   991: pop
    //   992: ldc_w 692
    //   995: new 167	java/lang/StringBuilder
    //   998: dup
    //   999: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1002: ldc_w 697
    //   1005: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1008: aload 65
    //   1010: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   1013: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1016: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1019: invokestatic 239	com/lionmobi/netmaster/f/r:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1022: aload 67
    //   1024: ifnonnull +27 -> 1051
    //   1027: aload_0
    //   1028: aload_1
    //   1029: getstatic 51	com/lionmobi/netmaster/manager/o:g	Lcom/lionmobi/netmaster/manager/p;
    //   1032: aload 65
    //   1034: getfield 426	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   1037: invokevirtual 445	com/lionmobi/netmaster/manager/p:getKey	(Ljava/lang/String;)Ljava/lang/String;
    //   1040: aload 66
    //   1042: invokevirtual 451	com/lionmobi/netmaster/f/ap:getTotalMobile	()J
    //   1045: invokespecial 285	com/lionmobi/netmaster/manager/o:a	(Lcom/lionmobi/netmaster/dao/TrafficInfoDao;Ljava/lang/String;J)V
    //   1048: goto -759 -> 289
    //   1051: aload 67
    //   1053: invokevirtual 294	com/lionmobi/netmaster/domain/d:getDots	()Ljava/lang/String;
    //   1056: bipush 120
    //   1058: invokestatic 130	com/lionmobi/netmaster/domain/d:dataAsList	(Ljava/lang/String;I)Ljava/util/List;
    //   1061: invokeinterface 142 1 0
    //   1066: astore 65
    //   1068: aload 65
    //   1070: invokeinterface 148 1 0
    //   1075: ifeq -786 -> 289
    //   1078: aload 65
    //   1080: invokeinterface 152 1 0
    //   1085: checkcast 154	java/lang/Long
    //   1088: astore 66
    //   1090: aload 66
    //   1092: ifnull -24 -> 1068
    //   1095: aload 64
    //   1097: aload 66
    //   1099: invokevirtual 157	java/lang/Long:longValue	()J
    //   1102: invokevirtual 522	java/util/concurrent/atomic/AtomicLong:addAndGet	(J)J
    //   1105: pop2
    //   1106: goto -38 -> 1068
    //   1109: aload 57
    //   1111: invokevirtual 684	java/util/concurrent/atomic/AtomicLong:get	()J
    //   1114: ldc2_w 698
    //   1117: lcmp
    //   1118: ifle +85 -> 1203
    //   1121: invokestatic 215	c/c:getDefault	()Lc/c;
    //   1124: new 701	com/lionmobi/netmaster/eventbus/message/i
    //   1127: dup
    //   1128: invokespecial 702	com/lionmobi/netmaster/eventbus/message/i:<init>	()V
    //   1131: iconst_2
    //   1132: invokevirtual 705	com/lionmobi/netmaster/eventbus/message/i:setType	(I)Lcom/lionmobi/netmaster/eventbus/message/i;
    //   1135: aload 61
    //   1137: invokevirtual 709	com/lionmobi/netmaster/eventbus/message/i:setAppInfo	(Ljava/util/HashMap;)Lcom/lionmobi/netmaster/eventbus/message/i;
    //   1140: invokevirtual 219	c/c:post	(Ljava/lang/Object;)V
    //   1143: iload_2
    //   1144: ifeq +9 -> 1153
    //   1147: aload_1
    //   1148: aload 56
    //   1150: invokevirtual 630	com/lionmobi/netmaster/dao/TrafficInfoDao:updateInTx	(Ljava/lang/Iterable;)V
    //   1153: new 711	com/lionmobi/netmaster/eventbus/message/EventOneHourMobileTrafficUpdate
    //   1156: dup
    //   1157: aload_0
    //   1158: getfield 74	com/lionmobi/netmaster/manager/o:b	J
    //   1161: invokespecial 713	com/lionmobi/netmaster/eventbus/message/EventOneHourMobileTrafficUpdate:<init>	(J)V
    //   1164: iconst_1
    //   1165: invokestatic 717	com/lionmobi/netmaster/eventbus/message/h:postRemoteAndLoal	(Landroid/os/Parcelable;Z)V
    //   1168: new 719	com/lionmobi/netmaster/eventbus/message/EventSpeedStateUpdate
    //   1171: dup
    //   1172: aload 61
    //   1174: invokevirtual 720	java/util/HashMap:size	()I
    //   1177: aload 57
    //   1179: invokevirtual 684	java/util/concurrent/atomic/AtomicLong:get	()J
    //   1182: invokespecial 723	com/lionmobi/netmaster/eventbus/message/EventSpeedStateUpdate:<init>	(IJ)V
    //   1185: iconst_1
    //   1186: invokestatic 717	com/lionmobi/netmaster/eventbus/message/h:postRemoteAndLoal	(Landroid/os/Parcelable;Z)V
    //   1189: aload 58
    //   1191: aload 59
    //   1193: invokevirtual 631	com/lionmobi/netmaster/dao/TrafficRankInfoDao:updateInTx	(Ljava/lang/Iterable;)V
    //   1196: return
    //   1197: astore_1
    //   1198: aload_1
    //   1199: invokevirtual 277	java/lang/Exception:printStackTrace	()V
    //   1202: return
    //   1203: aload 64
    //   1205: invokevirtual 684	java/util/concurrent/atomic/AtomicLong:get	()J
    //   1208: ldc2_w 724
    //   1211: lcmp
    //   1212: ifle -69 -> 1143
    //   1215: invokestatic 215	c/c:getDefault	()Lc/c;
    //   1218: new 701	com/lionmobi/netmaster/eventbus/message/i
    //   1221: dup
    //   1222: invokespecial 702	com/lionmobi/netmaster/eventbus/message/i:<init>	()V
    //   1225: iconst_1
    //   1226: invokevirtual 705	com/lionmobi/netmaster/eventbus/message/i:setType	(I)Lcom/lionmobi/netmaster/eventbus/message/i;
    //   1229: aload 62
    //   1231: invokevirtual 709	com/lionmobi/netmaster/eventbus/message/i:setAppInfo	(Ljava/util/HashMap;)Lcom/lionmobi/netmaster/eventbus/message/i;
    //   1234: invokevirtual 219	c/c:post	(Ljava/lang/Object;)V
    //   1237: goto -94 -> 1143
    //   1240: astore_1
    //   1241: aload_1
    //   1242: invokevirtual 277	java/lang/Exception:printStackTrace	()V
    //   1245: goto -92 -> 1153
    //   1248: aconst_null
    //   1249: astore 56
    //   1251: aload 57
    //   1253: astore_1
    //   1254: goto -1047 -> 207
    //   1257: astore 54
    //   1259: aconst_null
    //   1260: astore 54
    //   1262: goto -1249 -> 13
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1265	0	this	o
    //   0	1265	1	paramE	e
    //   0	1265	2	paramBoolean	boolean
    //   119	174	3	i1	int
    //   741	45	4	i2	int
    //   738	10	5	i3	int
    //   379	475	6	l1	long
    //   386	161	8	l2	long
    //   393	156	10	l3	long
    //   400	158	12	l4	long
    //   407	153	14	l5	long
    //   414	148	16	l6	long
    //   421	150	18	l7	long
    //   428	145	20	l8	long
    //   435	140	22	l9	long
    //   442	142	24	l10	long
    //   449	137	26	l11	long
    //   456	132	28	l12	long
    //   463	134	30	l13	long
    //   470	129	32	l14	long
    //   477	124	34	l15	long
    //   484	126	36	l16	long
    //   491	121	38	l17	long
    //   498	116	40	l18	long
    //   505	118	42	l19	long
    //   512	113	44	l20	long
    //   519	108	46	l21	long
    //   526	110	48	l22	long
    //   533	105	50	l23	long
    //   540	100	52	l24	long
    //   11	252	54	localList1	List
    //   1257	1	54	localException	Exception
    //   1260	1	54	localObject1	Object
    //   73	842	55	localObject2	Object
    //   198	1052	56	localList2	List
    //   180	1072	57	localAtomicLong1	AtomicLong
    //   100	1090	58	localTrafficRankInfoDao	TrafficRankInfoDao
    //   107	1085	59	localList3	List
    //   116	198	60	localHashMap1	HashMap
    //   238	935	61	localHashMap2	HashMap
    //   223	1007	62	localHashMap3	HashMap
    //   229	585	63	localSet	Set
    //   247	957	64	localAtomicLong2	AtomicLong
    //   273	806	65	localObject3	Object
    //   346	752	66	localObject4	Object
    //   311	741	67	localObject5	Object
    //   332	628	68	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   3	13	22	finally
    //   13	16	22	finally
    //   23	26	22	finally
    //   57	64	166	java/io/IOException
    //   1189	1196	1197	java/lang/Exception
    //   1147	1153	1240	java/lang/Exception
    //   3	13	1257	java/lang/Exception
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    for (;;)
    {
      e localE;
      try
      {
        localE = com.lionmobi.netmaster.dao.f.createDaoSession(this.q, true);
        if (localE == null) {
          return true;
        }
        switch (paramMessage.what)
        {
        case 0: 
          if (paramMessage.arg1 == 1) {
            bool1 = true;
          }
          c(localE, bool1);
          break;
        case 2: 
          b(localE);
        }
      }
      finally {}
      continue;
      a(localE, (String)paramMessage.obj);
      continue;
      bool1 = bool2;
      if (paramMessage.arg1 == 1) {
        bool1 = true;
      }
      b(localE, bool1);
      continue;
      bool1 = bool3;
      if (paramMessage.arg1 == 1) {
        bool1 = true;
      }
      a(localE, bool1);
      continue;
      a(localE);
      a();
    }
  }
  
  public void onCreate(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    paramContext = paramContext.getApplicationContext();
    HandlerThread localHandlerThread = new HandlerThread("TrafficMonitor");
    localHandlerThread.start();
    this.l = new Handler(localHandlerThread.getLooper(), this);
    this.q = paramContext;
    this.m = paramContext.getPackageManager();
    this.n = paramSharedPreferences;
    this.o = new ao(paramContext);
    this.p = ((ActivityManager)paramContext.getSystemService("activity"));
    this.l.sendEmptyMessage(2);
    if (!c.c.getDefault().isRegistered(this)) {
      c.c.getDefault().register(this);
    }
  }
  
  public void onDestroy()
  {
    this.l.removeCallbacksAndMessages(null);
    this.l.getLooper().quit();
    if (c.c.getDefault().isRegistered(this)) {
      c.c.getDefault().unregister(this);
    }
  }
  
  public void onEvent(EventBoostApps paramEventBoostApps)
  {
    synchronized (this.j)
    {
      paramEventBoostApps = paramEventBoostApps.a;
      System.currentTimeMillis();
      paramEventBoostApps = paramEventBoostApps.iterator();
      if (paramEventBoostApps.hasNext())
      {
        String str = (String)paramEventBoostApps.next();
        this.j.put(str, Long.valueOf(System.currentTimeMillis()));
      }
    }
    this.l.obtainMessage(0, 0, 0).sendToTarget();
  }
  
  public void onEvent(EventPackageAdd paramEventPackageAdd)
  {
    this.l.obtainMessage(4, paramEventPackageAdd.a).sendToTarget();
  }
  
  public void onEvent(EventQueryTrafficRankInfo paramEventQueryTrafficRankInfo)
  {
    Handler localHandler = this.l;
    if (paramEventQueryTrafficRankInfo.a) {}
    for (int i1 = 1;; i1 = 0)
    {
      localHandler.obtainMessage(5, i1, -1).sendToTarget();
      return;
    }
  }
  
  public void onEvent(EventTrafficReports paramEventTrafficReports)
  {
    Handler localHandler = this.l;
    if (paramEventTrafficReports.a) {}
    for (int i1 = 1;; i1 = 0)
    {
      localHandler.obtainMessage(6, i1, -1).sendToTarget();
      return;
    }
  }
  
  public void onEvent(com.lionmobi.netmaster.eventbus.message.f paramF)
  {
    this.l.obtainMessage(1, 1, 0).sendToTarget();
  }
  
  public void onEvent(g paramG)
  {
    this.l.obtainMessage(0, 1, 0).sendToTarget();
  }
}
