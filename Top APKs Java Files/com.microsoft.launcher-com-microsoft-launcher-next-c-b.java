package com.microsoft.launcher.next.c;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.jaredrummler.android.processes.models.Stat;
import com.microsoft.launcher.LauncherApplication;
import com.microsoft.launcher.q;
import com.microsoft.launcher.utils.ad;
import com.microsoft.launcher.utils.ak;
import com.microsoft.launcher.utils.am;
import com.microsoft.launcher.utils.g;
import com.microsoft.launcher.utils.v;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class b
{
  private static long A;
  private static float B;
  private static List<String> C;
  private static int D;
  private static int E;
  private static int F;
  private static List<com.microsoft.launcher.next.model.a.a> G;
  private static List<com.microsoft.launcher.next.model.a.a> H;
  private static double I;
  private static int J;
  private static Comparator<String> K;
  public static List<String> a = Collections.synchronizedList(com.microsoft.launcher.utils.b.a(t, new ArrayList()));
  public static ConcurrentHashMap<String, Long> b = com.microsoft.launcher.utils.b.b(u, new ConcurrentHashMap());
  public static String c;
  public static String d;
  public static List<String> e;
  public static List<String> f;
  public static List<String> g;
  public static List<String> h;
  public static List<String> i;
  public static HashSet<String> j = (HashSet)com.microsoft.launcher.utils.b.a(c, new HashSet());
  public static HashSet<String> k = (HashSet)com.microsoft.launcher.utils.b.a(d, new HashSet());
  public static HashSet<String> l;
  public static int m;
  public static int n;
  public static int o;
  public static int p;
  public static HashSet<String> q;
  public static HashSet<String> r;
  private static final Object s = new Object();
  private static String t;
  private static String u;
  private static com.microsoft.launcher.next.model.a.b v;
  private static Double w;
  private static String x;
  private static String y;
  private static int z;
  
  static
  {
    a = new CopyOnWriteArrayList();
    b = new ConcurrentHashMap();
    c = "BlockListKey";
    d = "HiddenListKey";
    e = new ArrayList();
    f = new ArrayList();
    g = new ArrayList();
    h = new ArrayList();
    j = new HashSet();
    k = new HashSet();
    l = new HashSet();
    m = 4;
    n = 10;
    o = 10;
    p = 20;
    q = new HashSet();
    r = new HashSet();
    t = "RecentAppsKey";
    u = "RecentAppsTimestamp";
    v = com.microsoft.launcher.next.model.a.b.b;
    w = Double.valueOf(0.0D);
    x = "AppInfoListKey";
    y = "IsFirstTimeLoadAppFrequency";
    z = 20;
    A = 172800000L;
    B = 0.87F;
    D = 5;
    E = 10;
    F = 5;
    I = 0.1D;
    J = 0;
    K = new c();
    i = new ArrayList();
    i.add("com.facebook.katana");
    i.add("com.google.android.apps.maps");
    i.add("com.joelapenna.foursquared");
    i.add("com.pandora.android");
    i.add("com.instagram.android");
    i.add("com.google.android.gallery3d");
    i.add("com.htc.album");
    i.add("com.sec.android.gallery3d");
    i.add("com.motorola.MotGallery2");
    i.add("com.android.mms");
    i.add("com.android.calendar");
    i.add("com.android.chrome");
    i.add("com.microsoft.bing");
    i.add("com.google.android.youtube");
    i.add("org.npr.android.news");
    i.add("com.nytimes.android");
    i.add("com.android.vending");
    i.add("com.android.com.microsoft.office.onenote");
    i.add("com.twitter.android");
    i.add("com.opera.mini.android");
    i.add("org.mozilla.firefox");
    i.add("com.whatsapp");
    i.add("com.skype.polaris");
    i.add("com.microsoft.office.lync15");
    i.add("com.microsoft.office.lync");
    i.add("com.viber.voip");
    i.add("com.yelp.android");
    i.add("com.ebay.mobile");
    i.add("flipboard.cn");
    i.add("flipboard.com");
    i.add("com.ideashower.readitlater.pro");
    i.add("com.chase.sig.android");
    i.add("com.yahoo.mobile.client.android.weather");
    l.add(LauncherApplication.c.getPackageName());
    l.add("com.android.launcher");
    l.add("com.sec.android.app.launcher");
    l.add("com.htc.launcher");
    l.add("com.tul.aviateu");
    l.add("com.tul.aviate");
    l.add("com.cm.launcher");
    l.add("com.apusapps.launcher");
    Object localObject1 = LauncherApplication.c.getPackageManager();
    Object localObject2 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject2).addCategory("android.intent.category.HOME");
    localObject1 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 0);
    if (!((List)localObject1).isEmpty())
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ResolveInfo)((Iterator)localObject1).next();
        if (!l.contains(((ResolveInfo)localObject2).activityInfo.packageName)) {
          l.add(((ResolveInfo)localObject2).activityInfo.packageName);
        }
      }
    }
    l.remove("com.google.android.googlequicksearchbox");
    l.add("com.google.android.launcher");
    l.add("com.yadavapp.keypadlockscreen");
    l.add("com.microsoft.next");
    l.add("com.securesolution.app.lockscreen");
    l.add("com.wow.keypad.lock.screen");
    l.add("com.hexadev.newkeypad.lock.screen");
    l.add("com.pandasoft.unloacker.drawinglockscreen");
    l.add("com.kiwisoftapps.iphone5lockscreen");
    l.add("arrow.passcode.lock");
    l.add("com.smart.mobile.lin.photo.keypad.locker");
    l.add("com.wow.g3.lock.screen");
    l.add("com.wandoujia.roshan");
    l.add("com.qodester.combination.lock");
    l.add("com.mah.basketballlockscreen");
    l.add("es.mamba.lockscreen");
    l.add("com.galaxyapps.lock");
    l.add("com.androbeings.puppy.zipper.lock.screen.free");
    l.add("com.systemclips.doorscreenlock");
    l.add("com.highsecure.lockscreenpasscode");
    l.add("com.feng.lingcodelocklockscreen");
    l.add("com.cmcm.locker");
    l.add("com.zuimeia.suite.lockscreen.international");
    l.add("com.doublelabs.androscreen.echo");
    l.add("com.app.free.studio.firefly.locker");
    l.add("com.jiubang.gocreenlock");
    l.add("com.microsoft.androidapps.picturesque");
    l.add("com.coverscreen.cover");
    l.add("com.google.android.inputmethod.latin");
    l.add("com.google.android.apps.handwriting.ime");
    l.add("com.qisiemoji.inputmethod");
    l.add("com.sohu.inputmethod.sogou");
    l.add("com.google.android.inputmethod.pinyin");
    l.add("com.google.andoird.inputmethod.zhuyin");
    l.add("com.android.systemui");
    l.add("com.sec.connectionhandler");
    l.add("com.google.android.inputmethod.pinyin");
    l.add("com.android.stk");
    q.add("com.asurion.android.mobilerecovery.att");
    q.add("com.locationlabs.cni.att");
    q.add("com.att.android.attsmartwifi");
    q.add("com.att.android.digitallocker");
    q.add("com.att.mobiletransfer");
    q.add("com.att.myWireless");
    q.add("com.att.android.mobile.attmessages");
    q.add("com.att.android.uverse");
    q.add("com.att.mobile.android.vvm");
    q.add("com.telenav.app.android.cingular");
    q.add("com.yahoo.mobile.client.android.mail.att");
    q.add("stealthychief.icon.pack.stealth");
    q.add("com.xonyxltd.icon.silhouettedonate");
    q.add("com.hooolm.smokeandglass");
    q.add("com.benx9.cactus");
    q.add("com.vertumus.cryten");
    q.add("com.samymarboy.theme.sunshine");
    q.add("com.xynapse.futurounds");
    q.add("com.vertumus.elun");
    q.add("tomy.cadus.adastra");
    q.add("com.sikebo.materialistik.material.icons");
    q.add("com.whicons.iconpack");
    q.add("com.thearclabs.polycon");
    q.add("com.sec.android.app.shealth");
    q.add("com.drivemode");
    C = new ArrayList();
    C.add("com.sec.android.app.camera");
    C.add("com.android.camera");
    C.add("com.google.android.GoogleCamera");
    C.add("com.motorola.camera");
    e.add("com.android.contacts");
    e.add("com.htc.contacts");
    e.add("com.android.htccontacts");
    e.add("com.sonyericsson.android.socialphonebook");
    f.add("com.google.android.app.dialer");
    f.add("com.google.android.dialer");
    f.add("com.android.dialer");
    g.add("com.android.mms");
    g.add("com.htc.sense.mms");
    g.add("com.sonyericsson.conversations");
    h.add("com.google.android.talk");
    h.add("com.textra");
    h.add("com.handcent.nextsms");
    h.add("com.p1.chompsms");
    h.add("com.jb.gosms");
    h.add("com.hellotext.hello");
  }
  
  public static com.microsoft.launcher.next.model.a.b a()
  {
    return v;
  }
  
  public static List<com.microsoft.launcher.next.model.a.a> a(int paramInt)
  {
    if (am.b(22))
    {
      List localList = a(paramInt * 2, false, false, true, true, null, null);
      if ((localList != null) && (localList.size() > 0)) {
        return localList;
      }
      return c(paramInt);
    }
    if (am.e()) {
      return b(paramInt);
    }
    return d(paramInt);
  }
  
  public static List<com.microsoft.launcher.next.model.a.a> a(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, HashSet<String> paramHashSet1, HashSet<String> paramHashSet2)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      HashSet localHashSet = new HashSet();
      int i2 = 0;
      int i3 = -1;
      Object localObject1 = com.jaredrummler.android.processes.a.a();
      int i1 = i3;
      Object localObject2;
      Object localObject3;
      if (paramBoolean2)
      {
        localObject2 = ((List)localObject1).iterator();
        do
        {
          i1 = i3;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          localObject3 = (AndroidAppProcess)((Iterator)localObject2).next();
        } while (!((AndroidAppProcess)localObject3).c.equals("com.microsoft.launcher"));
        i1 = ((AndroidAppProcess)localObject3).c().b();
      }
      localObject1 = ((List)localObject1).iterator();
      for (;;)
      {
        if (((Iterator)localObject1).hasNext())
        {
          localObject2 = (AndroidAppProcess)((Iterator)localObject1).next();
          localObject3 = ((AndroidAppProcess)localObject2).c;
          com.microsoft.launcher.next.model.a.a localA = new com.microsoft.launcher.next.model.a.a();
          localA.c = ((String)localObject3);
          if (((!paramBoolean1) || (((AndroidAppProcess)localObject2).a)) && ((!paramBoolean2) || (((AndroidAppProcess)localObject2).c().b() != i1)) && (!TextUtils.isEmpty((CharSequence)localObject3)) && ((!paramBoolean4) || (!localHashSet.contains(localObject3))) && ((!paramBoolean3) || (ad.a((String)localObject3) != null)) && ((paramHashSet1 == null) || (!paramHashSet1.contains(localObject3))) && ((paramHashSet2 == null) || (!paramHashSet2.contains(localObject3))))
          {
            localArrayList.add(localA);
            localHashSet.add(localObject3);
            i2 += 1;
            if (i2 < paramInt) {}
          }
        }
        else
        {
          return localArrayList;
        }
      }
      return null;
    }
    catch (Exception paramHashSet1) {}
  }
  
  public static List<String> a(com.microsoft.launcher.next.model.a.b paramB, int paramInt, boolean paramBoolean)
  {
    if (paramB == null) {
      paramB = v;
    }
    ArrayList localArrayList = new ArrayList();
    try
    {
      HashSet localHashSet1 = (HashSet)com.microsoft.launcher.utils.b.a(c, new HashSet());
      HashSet localHashSet2 = (HashSet)com.microsoft.launcher.utils.b.a(d, new HashSet());
      synchronized (s)
      {
        Map localMap = g();
        if ((localMap == null) || (localMap.isEmpty())) {
          break label359;
        }
        Iterator localIterator = localMap.entrySet().iterator();
        String str;
        do
        {
          do
          {
            do
            {
              if (!localIterator.hasNext()) {
                break;
              }
              str = (String)((Map.Entry)localIterator.next()).getKey();
              Pair localPair = d.a(str);
              paramB = "";
              if (localPair != null) {
                paramB = (String)localPair.first;
              }
            } while ((localHashSet1.contains(paramB)) || (localHashSet2.contains(paramB)));
          } while (localHashSet1.contains(String.format("%s%s0", new Object[] { paramB, ":" })));
        } while ((localHashSet2.contains(String.format("%s%s0", new Object[] { paramB, ":" }))) || (com.microsoft.launcher.mostusedapp.d.f.contains(str)) || (TextUtils.isEmpty(paramB)) || (ad.b(paramB).size() <= 0) || (localMap.get(str) == null) || (((com.microsoft.launcher.d.a.a)localMap.get(str)).c() <= I));
        localArrayList.add(str);
      }
      return localArrayList;
    }
    catch (Exception paramB)
    {
      paramB.printStackTrace();
    }
    label359:
    for (;;)
    {
      int i1 = localArrayList.size();
      if ((i1 > 0) && (paramBoolean)) {
        a(localArrayList);
      }
      if (i1 >= paramInt)
      {
        paramB = new ArrayList(localArrayList.subList(0, paramInt));
        return paramB;
      }
    }
  }
  
  public static List<String> a(List<String> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return paramList;
    }
    try
    {
      Collections.sort(paramList, K);
      return paramList;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append((String)localIterator.next()).append(";");
      }
      v.a("Error: AppFrequencyUtils sortAppList", "IllegalArgumentException", String.format(Locale.US, "sortAppList, appList:%s, message:%s, exception:%s", new Object[] { localStringBuilder.toString(), localIllegalArgumentException.getMessage(), Log.getStackTraceString(localIllegalArgumentException) }));
    }
    return paramList;
  }
  
  public static List<String> a(List<String> paramList, int paramInt, boolean paramBoolean)
  {
    g();
    Object localObject1 = (HashSet)com.microsoft.launcher.utils.b.a(c, new HashSet());
    int i1;
    PackageManager localPackageManager;
    Object localObject2;
    HashSet localHashSet;
    Object localObject3;
    if (paramList.size() < z)
    {
      i1 = z - paramList.size();
      localPackageManager = LauncherApplication.c.getPackageManager();
      localObject2 = localPackageManager.getInstalledPackages(0);
      localHashSet = new HashSet();
      localObject3 = paramList.iterator();
      while (((Iterator)localObject3).hasNext()) {
        localHashSet.add((String)((Iterator)localObject3).next());
      }
      localObject2 = ((List)localObject2).iterator();
    }
    for (;;)
    {
      if (((Iterator)localObject2).hasNext())
      {
        Object localObject4 = (PackageInfo)((Iterator)localObject2).next();
        if ((localObject4 != null) && (((PackageInfo)localObject4).versionName != null))
        {
          localObject3 = ((PackageInfo)localObject4).applicationInfo.packageName;
          String str = d.a((String)localObject3, ((PackageInfo)localObject4).applicationInfo.name);
          if (!TextUtils.isEmpty((CharSequence)localObject3))
          {
            localObject4 = ad.b(((PackageInfo)localObject4).applicationInfo.packageName);
            if ((localObject4 != null) && (((List)localObject4).size() != 0) && (!localHashSet.contains(localObject3)) && (!j.contains(localObject3)) && (!((HashSet)localObject1).contains(localObject3))) {
              if ((!((HashSet)localObject1).contains(String.format("%s%s0", new Object[] { localObject3, ":" }))) && (!com.microsoft.launcher.mostusedapp.d.f.contains(str)) && (!((ResolveInfo)((List)localObject4).get(0)).loadLabel(localPackageManager).toString().equalsIgnoreCase((String)localObject3)))
              {
                paramList.add(localObject3);
                if (paramList.size() >= paramInt)
                {
                  localObject1 = paramList;
                  if (paramBoolean) {
                    localObject1 = a(paramList);
                  }
                  return localObject1;
                }
                i1 -= 1;
                if (i1 != 0) {}
              }
            }
          }
        }
      }
      else
      {
        return a(paramList);
      }
    }
  }
  
  public static void a(String paramString, Long paramLong)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (a.contains(paramString))
      {
        a.remove(paramString);
        b.remove(paramString);
      }
      a.add(0, paramString);
      ak.a("add recent app %s %d", new Object[] { paramString, paramLong });
      ConcurrentHashMap localConcurrentHashMap = b;
      if (paramLong == null) {}
      for (long l1 = System.currentTimeMillis();; l1 = paramLong.longValue())
      {
        localConcurrentHashMap.put(paramString, Long.valueOf(l1));
        if (a.size() <= com.microsoft.launcher.mostusedapp.d.d) {
          break;
        }
        a = Collections.synchronizedList(a.subList(0, com.microsoft.launcher.mostusedapp.d.d));
        paramString = b.entrySet().iterator();
        while (paramString.hasNext())
        {
          paramLong = (Map.Entry)paramString.next();
          if (!a.contains(paramLong.getKey().toString())) {
            paramString.remove();
          }
        }
      }
      com.microsoft.launcher.utils.b.b(t, a);
      com.microsoft.launcher.utils.b.a(u, b);
    }
  }
  
  public static void a(String paramString1, String arg1)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return;
    }
    synchronized (s)
    {
      j.add(paramString1);
      com.microsoft.launcher.utils.b.b(c, j);
      if (com.microsoft.launcher.mostusedapp.d.a() != null)
      {
        com.microsoft.launcher.mostusedapp.views.AppsPageFrequent.f = true;
        com.microsoft.launcher.mostusedapp.d.a().d(true);
      }
      return;
    }
  }
  
  public static void a(String paramString1, String paramString2, int paramInt)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return;
    }
    synchronized (s)
    {
      d.a().a(paramString1, paramString2, paramInt);
      return;
    }
  }
  
  public static void a(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
  {
    a(paramString1, paramString2, false, paramInt);
    if (paramBoolean) {
      com.microsoft.launcher.mostusedapp.d.a().i();
    }
    paramInt = J;
    J = paramInt + 1;
    if (paramInt >= 10)
    {
      b();
      J = 0;
    }
  }
  
  public static void a(String paramString1, String paramString2, boolean paramBoolean, int paramInt)
  {
    a(paramString1, paramString2, paramBoolean, v, paramInt);
  }
  
  private static void a(String paramString1, String paramString2, boolean paramBoolean, com.microsoft.launcher.next.model.a.b arg3, int paramInt)
  {
    if ((TextUtils.isEmpty(paramString1)) || (r.contains(paramString1))) {
      return;
    }
    synchronized (s)
    {
      d.a().a(paramString1, paramString2, paramBoolean, paramInt);
      return;
    }
  }
  
  private static void a(List<com.microsoft.launcher.next.model.a.a> paramList1, List<com.microsoft.launcher.next.model.a.a> paramList2, boolean paramBoolean)
  {
    if (paramList2 == null) {}
    Object localObject2;
    int i2;
    do
    {
      return;
      localObject1 = paramList1;
      if (paramList1 == null) {
        localObject1 = new ArrayList();
      }
      paramList1 = new HashMap();
      i1 = 0;
      while (i1 < ((List)localObject1).size())
      {
        localObject2 = ((com.microsoft.launcher.next.model.a.a)((List)localObject1).get(i1)).c;
        if (!paramList1.containsKey(localObject2)) {
          paramList1.put(localObject2, Integer.valueOf(i1));
        }
        i1 += 1;
      }
      i2 = 0;
    } while (i2 >= paramList2.size());
    Object localObject1 = ((com.microsoft.launcher.next.model.a.a)paramList2.get(i2)).c;
    if ((paramList1.get(localObject1) == null) || (i2 < ((Integer)paramList1.get(localObject1)).intValue()))
    {
      if (!paramBoolean) {
        break label233;
      }
      boolean bool = am.e();
      if (q.contains(localObject1)) {
        break label248;
      }
      if (!bool) {
        break label226;
      }
    }
    label170:
    label226:
    label233:
    label248:
    for (int i1 = F;; i1 = 0)
    {
      localObject2 = ad.b((String)localObject1);
      if ((localObject2 != null) && (((List)localObject2).size() == 1)) {}
      try
      {
        a((String)localObject1, ((ResolveInfo)((List)localObject2).get(0)).activityInfo.name, false, i1);
        i2 += 1;
        break;
        i1 = E;
        break label170;
        i1 = 1;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
    }
  }
  
  public static void a(Map<String, Integer> paramMap)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Pair localPair = d.a((String)localEntry.getKey());
      ??? = "";
      if (localPair != null) {
        ??? = (String)localPair.first;
      }
      if ((TextUtils.isEmpty((CharSequence)localEntry.getKey())) || (r.contains(???))) {
        localIterator.remove();
      }
    }
    synchronized (s)
    {
      d.a().a(paramMap);
      return;
    }
  }
  
  private static void a(Map<String, com.microsoft.launcher.d.a.a> paramMap, float paramFloat)
  {
    a(paramMap, paramFloat, 1);
  }
  
  private static void a(Map<String, com.microsoft.launcher.d.a.a> paramMap, float paramFloat, int paramInt)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {
      return;
    }
    HashMap localHashMap = new HashMap();
    paramMap = paramMap.entrySet().iterator();
    double d1 = Math.pow(paramFloat, paramInt);
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      Double localDouble = Double.valueOf(((com.microsoft.launcher.d.a.a)localEntry.getValue()).c() * d1);
      if (localDouble.doubleValue() > I) {
        localHashMap.put(localEntry.getKey(), localDouble);
      }
    }
    d.a().b(localHashMap);
  }
  
  public static List<com.microsoft.launcher.next.model.a.a> b(int paramInt)
  {
    int i1 = 0;
    try
    {
      ArrayList localArrayList = new ArrayList();
      HashSet localHashSet1 = new HashSet();
      Object localObject1 = ((ActivityManager)LauncherApplication.c.getSystemService("activity")).getRunningAppProcesses();
      HashSet localHashSet2 = (HashSet)com.microsoft.launcher.utils.b.a(c, new HashSet());
      localObject1 = ((List)localObject1).iterator();
      for (;;)
      {
        if (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
          if ((((ActivityManager.RunningAppProcessInfo)localObject2).importance == 100) || (((ActivityManager.RunningAppProcessInfo)localObject2).importance == 200) || (((ActivityManager.RunningAppProcessInfo)localObject2).importance == 300) || (((ActivityManager.RunningAppProcessInfo)localObject2).importance == 400))
          {
            localObject2 = localObject2.pkgList[0];
            if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (!localHashSet1.contains(localObject2)))
            {
              com.microsoft.launcher.next.model.a.a localA = new com.microsoft.launcher.next.model.a.a();
              localA.c = ((String)localObject2);
              if ((!r.contains(localA.c)) && (ad.a((String)localObject2) != null) && (!localHashSet2.contains(localA.c)))
              {
                localArrayList.add(localA);
                localHashSet1.add(localObject2);
                int i2 = i1 + 1;
                i1 = i2;
                if (i2 < paramInt) {}
              }
            }
          }
        }
        else
        {
          return localArrayList;
        }
      }
      return null;
    }
    catch (Exception localException) {}
  }
  
  public static void b() {}
  
  public static void b(String paramString1, String paramString2, int paramInt)
  {
    a(paramString1, paramString2, paramInt, true);
  }
  
  public static List<com.microsoft.launcher.next.model.a.a> c(int paramInt)
  {
    localArrayList = new ArrayList();
    try
    {
      Object localObject = (ActivityManager)LauncherApplication.c.getSystemService("activity");
      HashSet localHashSet1 = new HashSet();
      HashSet localHashSet2 = (HashSet)com.microsoft.launcher.utils.b.a(c, new HashSet());
      int i1 = 0;
      localObject = ((ActivityManager)localObject).getRunningServices(paramInt * 5).iterator();
      for (;;)
      {
        if (((Iterator)localObject).hasNext())
        {
          ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject).next();
          String str = localRunningServiceInfo.service.getPackageName();
          if ((!localHashSet1.contains(str)) && (!r.contains(str)) && (ad.a(str) != null) && (!localHashSet2.contains(str)))
          {
            com.microsoft.launcher.next.model.a.a localA = new com.microsoft.launcher.next.model.a.a();
            localA.c = str;
            localA.e = Long.valueOf(localRunningServiceInfo.lastActivityTime);
            localHashSet1.add(str);
            localArrayList.add(localA);
            int i2 = i1 + 1;
            i1 = i2;
            if (i2 <= paramInt) {}
          }
        }
        else
        {
          return localArrayList;
        }
      }
      return localArrayList;
    }
    catch (Exception localException) {}
  }
  
  public static void c()
  {
    HashMap localHashMap = q.a().b();
    Object localObject2 = LauncherApplication.c.getPackageManager().getInstalledPackages(0);
    Object localObject1 = new HashSet();
    localObject2 = ((List)localObject2).iterator();
    Object localObject3;
    String str1;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (PackageInfo)((Iterator)localObject2).next();
      if ((localObject3 != null) && (((PackageInfo)localObject3).versionName != null))
      {
        str1 = ((PackageInfo)localObject3).applicationInfo.packageName;
        if (TextUtils.isEmpty(str1)) {
          return;
        }
        localObject3 = ad.b(((PackageInfo)localObject3).applicationInfo.packageName).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          String str2 = ((ResolveInfo)((Iterator)localObject3).next()).activityInfo.name;
          String str3 = d.a(str1, str2);
          if ((!((HashSet)localObject1).contains(str3)) && (!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2))) {
            ((HashSet)localObject1).add(str3);
          }
        }
      }
    }
    localObject2 = new HashMap();
    localObject1 = ((HashSet)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      str1 = (String)((Iterator)localObject1).next();
      localObject3 = (String)d.a(str1).first;
      if ((localHashMap.containsKey(localObject3)) && (!((String)localObject3).contains("input")) && (!((String)localObject3).contains("lockscreen")) && (!q.contains(localObject3))) {
        ((Map)localObject2).put(str1, localHashMap.get(localObject3));
      }
    }
    a((Map)localObject2);
    b();
  }
  
  private static List<com.microsoft.launcher.next.model.a.a> d(int paramInt)
  {
    Object localObject = ((ActivityManager)LauncherApplication.c.getSystemService("activity")).getRecentTasks(paramInt, 0);
    HashSet localHashSet1 = (HashSet)com.microsoft.launcher.utils.b.a(c, new HashSet());
    ArrayList localArrayList = new ArrayList();
    HashSet localHashSet2 = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Intent localIntent = ((ActivityManager.RecentTaskInfo)((Iterator)localObject).next()).baseIntent;
      try
      {
        com.microsoft.launcher.next.model.a.a localA = new com.microsoft.launcher.next.model.a.a();
        localA.c = localIntent.getComponent().getPackageName();
        if ((!r.contains(localA.c)) && (!localHashSet2.contains(localA.c)) && (!localHashSet1.contains(localA.c)))
        {
          localArrayList.add(localA);
          localHashSet2.add(localA.c);
        }
      }
      catch (Exception localException) {}
    }
    return localArrayList;
  }
  
  public static void d()
  {
    if (G == null) {
      G = com.microsoft.launcher.next.model.a.a.a(com.microsoft.launcher.utils.b.a(x, null));
    }
    boolean bool = com.microsoft.launcher.utils.b.c(y, true);
    if (bool) {}
    for (int i1 = o;; i1 = 2)
    {
      H = a(i1);
      a(G, H, bool);
      G = H;
      e();
      com.microsoft.launcher.utils.b.a(y, false);
      return;
    }
  }
  
  public static void e()
  {
    if (G == null) {
      return;
    }
    com.microsoft.launcher.utils.b.b(x, com.microsoft.launcher.next.model.a.a.b(G));
  }
  
  public static void f()
  {
    long l1 = m().getTimeInMillis();
    ??? = com.microsoft.launcher.utils.b.c("LastDecayTimeStampKey", "0");
    if (((String)???).equals("0")) {
      com.microsoft.launcher.utils.b.a("LastDecayTimeStampKey", "" + l1);
    }
    while (l1 - Long.parseLong((String)???) < A) {
      return;
    }
    synchronized (s)
    {
      a(g(), B);
      com.microsoft.launcher.utils.b.a("LastDecayTimeStampKey", "" + l1);
      b();
      g.a("Complete decay frequency map");
      return;
    }
  }
  
  public static Map<String, com.microsoft.launcher.d.a.a> g()
  {
    return d.a().b();
  }
  
  public static List<com.microsoft.launcher.next.model.a.a> h()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = (HashSet)com.microsoft.launcher.utils.b.a(d, new HashSet());
    PackageManager localPackageManager = LauncherApplication.c.getPackageManager();
    localObject = ((HashSet)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.microsoft.launcher.next.model.a.a localA = com.microsoft.launcher.next.model.a.a.a(localPackageManager, (String)((Iterator)localObject).next());
      if (localA != null) {
        localArrayList.add(localA);
      }
    }
    return localArrayList;
  }
  
  public static void i()
  {
    if (!LauncherApplication.e()) {
      j.addAll(l);
    }
    for (;;)
    {
      com.microsoft.launcher.utils.b.b(c, j);
      return;
      j.add(LauncherApplication.c.getPackageName());
    }
  }
  
  public static void j()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = j.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!l.contains(str)) {
        localHashSet.add(str);
      }
    }
    j.removeAll(localHashSet);
    k.clear();
    com.microsoft.launcher.utils.b.b(c, j);
    com.microsoft.launcher.utils.b.b(d, k);
  }
  
  private static Calendar m()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    return localCalendar;
  }
}
