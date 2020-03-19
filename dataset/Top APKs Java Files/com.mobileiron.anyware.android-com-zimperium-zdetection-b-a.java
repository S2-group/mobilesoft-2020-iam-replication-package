package com.zimperium.zdetection.b;

import a.a.a.c;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Environment;
import android.text.TextUtils;
import com.zimperium.zdetection.api.v1.ThreatType;
import com.zimperium.zdetection.api.v1.malware.MaliciousAppInfo;
import com.zimperium.zdetection.api.v1.malware.MalwareScanCallback;
import com.zimperium.zdetection.api.v1.malware.MalwareScanController;
import com.zimperium.zdetection.db.contentprovider.ZDetectionProvider;
import com.zimperium.zdetection.db.model.Threat;
import com.zimperium.zdetection.internal.ZDetectionInternal;
import com.zimperium.zdetection.knox.KnoxManager;
import com.zimperium.zdetection.service.ZcloudRunnerService;
import com.zimperium.zdetection.threats.ThreatUtil;
import com.zimperium.zdetection.utils.ZLog;
import com.zimperium.zdetection.utils.ZipsStatistics;
import com.zimperium.zdetection.utils.e.a;
import com.zimperium.zips.Zcloud;
import com.zimperium.zips.zcloud.ZipsZcloud.ScanCategory;
import com.zimperium.zips.zcloud.ZipsZcloud.threat_type;
import com.zimperium.zips.zcloud.ZipsZcloud.zEventThreatDetected;
import com.zimperium.zips.zcloud.ZipsZcloud.zEventThreatDetected.Builder;
import com.zimperium.zips.zcloud.ZipsZcloud.zEventThreatDetected.zHostAttack;
import com.zimperium.zips.zcloud.ZipsZcloud.zEventThreatDetected.zHostAttack.Builder;
import com.zimperium.zips.zcloud.ZipsZcloud.zEventThreatDetected.zHostAttack.zMalwareMatch;
import com.zimperium.zips.zcloud.ZipsZcloud.zEventThreatDetected.zHostAttack.zMalwareMatch.Builder;
import com.zimperium.zips.zcloud.ZipsZcloud.zIPSEvent;
import com.zimperium.zips.zcloud.ZipsZcloud.zIPSEvent.Builder;
import com.zimperium.zips.zcloud.ZipsZcloud.zips_event_names;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class a
{
  private Context a;
  private List<a.d> b = new ArrayList();
  private List<c> c = new ArrayList();
  private boolean d = true;
  private boolean e = false;
  private MalwareScanCallback f;
  private int g = 0;
  private String h;
  private String i;
  private long j;
  private ExecutorService k;
  private final Map<String, h> l = new HashMap();
  private final Map<String, MaliciousAppInfo> m = Collections.synchronizedMap(new HashMap());
  private final Map<String, h> n = Collections.synchronizedMap(new HashMap());
  private final a.b o = new a.b(this, c.c);
  private final a.b p = new a.b(this, c.d);
  private final a.b q = new a.b(this, c.b);
  private final a.b r = new a.b(this, c.a);
  private final a.b s = new a.b(this, c.e);
  private boolean t = false;
  
  private a(Context paramContext, long paramLong, boolean paramBoolean, String paramString1, String paramString2, List<h> paramList, List<c> paramList1, List<a.d> paramList2, MalwareScanCallback paramMalwareScanCallback)
  {
    this.a = paramContext;
    this.c.addAll(paramList1);
    this.b.addAll(paramList2);
    this.f = paramMalwareScanCallback;
    this.h = paramString1;
    this.i = paramString2;
    this.e = paramBoolean;
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      h localH = (h)paramList.next();
      this.l.put(localH.getPath(), localH);
    }
    b("MalwareScan()");
    b("\tremoteScanUrl=" + paramString1);
    b("\tfilterScanUrl=" + paramString2);
    b("\ttypes=" + paramList2.toString());
    b("\ttargets=" + paramList1.toString());
    b("\tafterEpoch=" + paramLong);
    this.k = Executors.newFixedThreadPool(5);
    if ((j()) || (i()) || (l()))
    {
      paramBoolean = true;
      this.d = paramBoolean;
      if (this.b.contains(a.d.a)) {
        paramString1 = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      }
    }
    else
    {
      for (;;)
      {
        if (!paramString1.hasNext()) {
          break label819;
        }
        paramList = (ApplicationInfo)paramString1.next();
        if ((KnoxManager.isActivated()) && (!KnoxManager.getApplicationStateEnabled(paramContext, paramList.packageName)))
        {
          b("\tKnox has this package disabled: " + paramList.packageName + " - not scanning.");
          continue;
          paramBoolean = false;
          break;
        }
        paramString2 = new h(paramList.publicSourceDir);
        paramString2.a(paramList.packageName);
        if (paramLong > 0L) {
          try
          {
            if (paramContext.getPackageManager().getPackageInfo(paramList.packageName, 0).firstInstallTime <= paramLong) {
              continue;
            }
            this.l.put(paramString2.getPath(), paramString2);
          }
          catch (Exception paramList)
          {
            b("\tException: " + paramList + " add it to the list anyhow.");
            this.l.put(paramString2.getPath(), paramString2);
          }
        } else {
          this.l.put(paramString2.getPath(), paramString2);
        }
      }
    }
    if (this.b.contains(a.d.c))
    {
      paramContext = paramContext.getPackageManager();
      long l1 = ZipsStatistics.getLStat("STAT_MALWARE_DATE");
      paramString1 = paramContext.getInstalledApplications(0).iterator();
      while (paramString1.hasNext())
      {
        paramString2 = (ApplicationInfo)paramString1.next();
        try
        {
          paramList = paramContext.getPackageInfo(paramString2.packageName, 0);
          if (Math.max(paramList.firstInstallTime, paramList.lastUpdateTime) >= l1)
          {
            paramList = new h(paramString2.publicSourceDir);
            paramList.a(paramString2.packageName);
            this.l.put(paramList.getPath(), paramList);
          }
        }
        catch (PackageManager.NameNotFoundException paramString2)
        {
          b("\tException: " + paramString2);
        }
      }
    }
    label819:
    if (this.b.contains(a.d.b))
    {
      paramContext = com.zimperium.zdetection.utils.a.a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).iterator();
      while (paramContext.hasNext())
      {
        paramString1 = (File)paramContext.next();
        if (paramLong > 0L)
        {
          if (paramString1.lastModified() > paramLong) {
            this.l.put(paramString1.getPath(), new h(paramString1.getPath()));
          }
        }
        else {
          this.l.put(paramString1.getPath(), new h(paramString1.getPath()));
        }
      }
    }
    b("\tFiles to scan: " + this.l.size());
    if ((this.l.size() == 0) && (paramMalwareScanCallback != null))
    {
      paramMalwareScanCallback.onScanStart(0);
      e();
      paramMalwareScanCallback.onScanComplete();
    }
  }
  
  public static a a(Context paramContext)
  {
    return new a(paramContext, null);
  }
  
  private List<List<h>> a(List<h> paramList, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    int i2 = paramList.size();
    int i1 = 0;
    while (i1 < i2)
    {
      localArrayList.add(new ArrayList(paramList.subList(i1, Math.min(i2, i1 + paramInt))));
      i1 += paramInt;
    }
    return localArrayList;
  }
  
  private void a(MaliciousAppInfo paramMaliciousAppInfo)
  {
    synchronized (this.m)
    {
      b("processScanMalware(): " + paramMaliciousAppInfo.appPath);
      if (!this.m.containsKey(paramMaliciousAppInfo.appPath))
      {
        this.m.put(paramMaliciousAppInfo.appPath, paramMaliciousAppInfo);
        this.g += 1;
        b("\tThis is a new report.");
        if (this.f != null) {
          this.f.onMaliciousApp(paramMaliciousAppInfo);
        }
        return;
      }
      b("\tThis is a duplicate report.");
    }
  }
  
  private boolean a(Threat paramThreat)
  {
    boolean bool2 = false;
    if (!new File(paramThreat.getMalwarePath()).exists()) {
      return true;
    }
    boolean bool1;
    if (paramThreat.getThreatType() == ThreatType.SIDELOADED_APP) {
      if (ZDetectionInternal.isAppBlackListed(com.zimperium.zdetection.utils.a.b(paramThreat.getSideloadedAppPackage()))) {
        bool1 = bool2;
      }
    }
    for (;;)
    {
      return bool1;
      bool1 = bool2;
      if (ZDetectionInternal.isAppWhiteListed(com.zimperium.zdetection.utils.a.b(paramThreat.getSideloadedAppPackage())))
      {
        bool1 = true;
        continue;
        bool1 = bool2;
        if (!ZDetectionInternal.isAppBlackListed(paramThreat.getMalwarePath()))
        {
          bool1 = bool2;
          if (ZDetectionInternal.isAppWhiteListed(paramThreat.getMalwarePath())) {
            bool1 = true;
          }
        }
      }
    }
  }
  
  private boolean a(String paramString1, String paramString2, ThreatType paramThreatType)
  {
    paramThreatType = ThreatUtil.getLatestAppThreat(paramString1, paramString2, paramThreatType);
    if (paramThreatType == null) {}
    long l1;
    do
    {
      for (;;)
      {
        return true;
        l1 = paramThreatType.getAttackTime();
        paramThreatType = com.zimperium.zdetection.utils.a.b(new File(paramString2));
        if (TextUtils.equals(paramThreatType, "CATEGORY_INSTALLED")) {
          try
          {
            long l2 = this.a.getPackageManager().getPackageInfo(paramString1, 0).firstInstallTime;
            if (l1 > l2) {
              return false;
            }
          }
          catch (PackageManager.NameNotFoundException paramString1)
          {
            return false;
          }
        }
      }
      if (!TextUtils.equals(paramThreatType, "CATEGORY_DOWNLOADED")) {
        break label111;
      }
      paramString1 = new File(paramString2);
      if (!paramString1.exists()) {
        break;
      }
    } while (l1 <= paramString1.lastModified());
    return false;
    return false;
    label111:
    return false;
  }
  
  private static void b(String paramString)
  {
    ZLog.i("MalwareScan: " + paramString, new Object[0]);
  }
  
  private boolean b()
  {
    return (this.r.d()) && (this.q.d()) && (this.o.d()) && (this.p.d()) && (this.s.d());
  }
  
  private boolean c()
  {
    return (this.r.a()) || (this.q.a()) || (this.o.a()) || (this.p.a()) || (this.s.a());
  }
  
  private void d()
  {
    int i1 = 0;
    b("processScanResult(): processedResults=" + this.t);
    try
    {
      if (this.t) {
        return;
      }
      if (c())
      {
        this.t = true;
        this.k.shutdownNow();
        b("\t***************************************************");
        b("\t* processScanResults() ");
        b("\t* Malware Threats found:\t" + this.g);
        b("\t* Notify only new:\t\t" + this.d);
        b("\t* Current Thread:\t\t" + Thread.currentThread().getName());
        Object localObject3 = this.r.b();
        Object localObject1 = localObject3;
        if (localObject3 == null) {
          localObject1 = this.q.b();
        }
        localObject3 = localObject1;
        if (localObject1 == null) {
          localObject3 = this.o.b();
        }
        localObject1 = localObject3;
        if (localObject3 == null) {
          localObject1 = this.p.b();
        }
        localObject3 = localObject1;
        if (localObject1 == null) {
          localObject3 = this.s.b();
        }
        b("\t* Exception:\t\t" + localObject3);
        if (this.f != null) {
          this.f.onScanError((Exception)localObject3);
        }
        return;
      }
    }
    finally {}
    if (b())
    {
      if (this.f != null)
      {
        this.f.onScanProgress(this.l.size());
        this.f.onScanComplete();
      }
      b("\t***************************************************");
      b("\t* processScanResults() ");
      b("\t* Malware Threats found:\t" + this.m.size());
      b("\t* Sideloaded Apps found:\t" + this.n.size());
      b("\t* Bypass Notify:\t\t" + this.e);
      b("\t* Notify only new:\t\t" + this.d);
      b("\t* Current Thread:\t\t" + Thread.currentThread().getName());
      b("\t* Types:\t\t\t" + this.b.toString());
      b("\t* Targets:\t\t\t" + this.c.toString());
      b("\t* Runtime (seconds):\t\t" + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - this.j));
      e();
      g();
      if (m()) {
        f();
      }
      if ((k()) || (l())) {
        break label790;
      }
    }
    for (;;)
    {
      if (i1 != 0)
      {
        if (!ZipsStatistics.getBooleanStat("STAT_PUSH_TOKEN_REGISTERED", false)) {
          break label610;
        }
        b("\t* PUSH is enabled for this device. Wait for push from zbackend.");
      }
      for (;;)
      {
        b("\t* Scan date:\t" + ZipsStatistics.formatDate(this.a, System.currentTimeMillis()));
        b("\t***************************************************");
        break;
        label610:
        b("\t* PUSH is not enabled for this device. Queuing command url read.");
        ZcloudRunnerService.queueCommandPoll(10000L);
        ZcloudRunnerService.queueCommandPoll(TimeUnit.MINUTES.toMillis(1L) + 11000L);
        ZcloudRunnerService.queueCommandPoll(TimeUnit.MINUTES.toMillis(5L));
      }
      b("\tScan still running...");
      b("\tz9Listener:" + this.r.d());
      b("\tremoteListener:" + this.q.d());
      b("\tzBlbListener:" + this.o.d());
      b("\tfilterScanListener:" + this.p.d());
      b("\tsideLoadListener:" + this.s.d());
      break;
      label790:
      i1 = 1;
    }
  }
  
  private void e()
  {
    b("\t* processExistingThreats()");
    Object localObject = ThreatUtil.getAllAppThreats();
    com.zimperium.zdetection.db.a localA = new com.zimperium.zdetection.db.a(this.a);
    b("\t* Threat Log: count=" + ((List)localObject).size());
    Iterator localIterator = ((List)localObject).iterator();
    int i2 = 0;
    Threat localThreat;
    label217:
    boolean bool2;
    h localH;
    int i1;
    boolean bool1;
    for (;;)
    {
      if (localIterator.hasNext())
      {
        localThreat = (Threat)localIterator.next();
        b("\t*===========================================");
        if (localThreat.isMitigated())
        {
          b("\t*\tMitigated=" + localThreat.getPackageName());
        }
        else
        {
          b("\t*\tType=" + localThreat.getThreatType());
          if (localThreat.getThreatType() == ThreatType.SIDELOADED_APP)
          {
            b("\t*\tName=" + localThreat.getSideloadedAppName());
            b("\t*\tPackage=" + localThreat.getSideloadedAppPackage());
            localObject = com.zimperium.zdetection.utils.a.b(localThreat.getSideloadedAppPackage());
            bool2 = new File((String)localObject).exists();
            b("\t*\tappPathExists =" + bool2);
            localH = (h)this.l.get(localObject);
            if (localH == null) {
              break label697;
            }
            if (localH.exists()) {
              break label549;
            }
            b("*\t\tThreat file doesn't exist anymore.");
            i1 = 0;
            bool1 = true;
          }
        }
      }
    }
    for (;;)
    {
      label291:
      if (bool1)
      {
        com.zimperium.zdetection.b.a.a(this.a);
        KnoxManager.allowPackageTraffic(this.a, localThreat.getPackageName());
        KnoxManager.allowApp(this.a, localThreat.getPackageName());
        KnoxManager.enableApp(this.a, localThreat.getPackageName());
        if (i1 != 0)
        {
          b("\tMitigating with WHITELIST reason.");
          com.zimperium.zdetection.utils.e.a(localThreat, e.a.c);
          label359:
          if (i1 != 0) {
            c.a().a(localA.getWritableDatabase()).b(localThreat);
          }
        }
      }
      for (i1 = 1;; i1 = i2)
      {
        i2 = i1;
        break;
        b("\t*\tclassifications=" + Arrays.toString(localThreat.getMalwareClassifications().toArray()));
        b("\t*\tPath=" + localThreat.getMalwarePath());
        b("\t*\tFile=" + localThreat.getFilePath());
        b("\t*\tName=" + localThreat.getMalwareName());
        b("\t*\tdetected locally =" + localThreat.getDetectedLocally());
        b("\t*\tsource =" + localThreat.getMalwareSource());
        localObject = localThreat.getMalwarePath();
        break label217;
        label549:
        if (localH.e())
        {
          b("\t*\t\tThreat is still blacklisted: " + localThreat.getPackageName());
          i1 = 0;
          bool1 = false;
          break label291;
        }
        if (localH.h())
        {
          b("\t*\t\tRemoving Threat from event log because it is whitelisted: " + localThreat.getPackageName());
          i1 = 1;
          bool1 = true;
          break label291;
        }
        if ((localThreat.getThreatType() != ThreatType.APK_SUSPECTED) || (this.m.containsKey(localThreat.getMalwarePath()))) {
          break label828;
        }
        if (((localThreat.getDetectedLocally()) && (j())) || ((!localThreat.getDetectedLocally()) && (i()))) {}
        for (bool1 = true;; bool1 = false)
        {
          i1 = 0;
          break;
        }
        label697:
        bool1 = a(localThreat);
        if ((!bool1) || (!bool2)) {
          break label823;
        }
        i1 = 1;
        break label291;
        if (com.zimperium.zdetection.utils.a.f((String)localObject))
        {
          b("\tMitigating with DELETED reason.");
          com.zimperium.zdetection.utils.e.a(localThreat, e.a.b);
          break label359;
        }
        b("\tMitigating with UNINSTALLED reason.");
        com.zimperium.zdetection.utils.e.a(localThreat, e.a.a);
        break label359;
        if (i2 != 0)
        {
          b("\t* Notifying ContentProvider of change.");
          this.a.getContentResolver().notifyChange(ZDetectionProvider.getContentUriThreatsApps(this.a), null);
        }
        try
        {
          localA.close();
          return;
        }
        catch (Exception localException)
        {
          b("\t* Exception closing threat db: " + localException);
          return;
        }
      }
      label823:
      i1 = 0;
      continue;
      label828:
      i1 = 0;
      bool1 = false;
    }
  }
  
  private void f()
  {
    b("\t* processNewSideLoaded()");
    Iterator localIterator = this.n.keySet().iterator();
    if (localIterator.hasNext())
    {
      Object localObject1 = (String)localIterator.next();
      h localH = (h)this.n.get(localObject1);
      for (;;)
      {
        PackageInfo localPackageInfo;
        try
        {
          boolean bool = a(localH.c(), localH.getPath(), ThreatType.SIDELOADED_APP);
          b("\t* ================================================");
          b("\t* Malware:\t\t" + localH.a());
          b("\t* Path:\t\t\t" + localH.getPath());
          b("\t* Notify ZCloud:\t" + bool);
          if (!bool) {
            break;
          }
          String str2 = localH.c();
          ZLog.i("Sideloaded app threat for : {}", new Object[] { str2 });
          StringBuilder localStringBuilder = new StringBuilder();
          try
          {
            localObject1 = this.a.getPackageManager();
            localPackageInfo = ((PackageManager)localObject1).getPackageInfo(str2, 64);
            Signature[] arrayOfSignature = localPackageInfo.signatures;
            int i2 = arrayOfSignature.length;
            int i1 = 0;
            if (i1 < i2)
            {
              ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfSignature[i1].toByteArray());
              try
              {
                localStringBuilder.append(((X509Certificate)CertificateFactory.getInstance("X509").generateCertificate(localByteArrayInputStream)).getSubjectDN());
                i1 += 1;
              }
              catch (CertificateException localCertificateException)
              {
                ZLog.infoException("Error getting cert info for sideloaded app", localCertificateException);
                continue;
              }
            }
          }
          catch (Exception localException1)
          {
            ZLog.errorException("Error getting package information for sideloaded app", localException1);
            Object localObject2 = "";
            localObject2 = ZipsZcloud.zEventThreatDetected.newBuilder().setType(ZipsZcloud.threat_type.SIDELOADED_APP).setSideloadedAppDeveloper(localStringBuilder.toString()).setSideloadedAppName((String)localObject2).setSideloadedAppFilehash(localH.d()).setSideloadedAppPackage(str2).build();
            localObject2 = ZipsZcloud.zIPSEvent.newBuilder().setThreatDetected((ZipsZcloud.zEventThreatDetected)localObject2).build();
            Zcloud.notifyZipsEvent(ZipsZcloud.zips_event_names.EVENT_THREAT_DETECTED, (ZipsZcloud.zIPSEvent)localObject2);
          }
        }
        catch (Exception localException2)
        {
          b("\t* Exception: \t\t" + localException2);
          ZLog.errorException("\t* Error sending new malware threat", localException2);
        }
        String str1 = localPackageInfo.applicationInfo.loadLabel(localException2);
      }
    }
  }
  
  private void g()
  {
    b("\t* processNewThreats()");
    if (this.e)
    {
      b("\t* \tbypassing new threats for this scan.");
      return;
    }
    Iterator localIterator = this.m.keySet().iterator();
    int i1 = 0;
    label678:
    label683:
    for (;;)
    {
      if (localIterator.hasNext())
      {
        Object localObject = (String)localIterator.next();
        MaliciousAppInfo localMaliciousAppInfo = (MaliciousAppInfo)this.m.get(localObject);
        for (;;)
        {
          try
          {
            boolean bool = a(localMaliciousAppInfo.packageName, localMaliciousAppInfo.appPath, ThreatType.APK_SUSPECTED);
            b("\t* ================================================");
            b("\t* Malware:\t\t" + localMaliciousAppInfo.packageName);
            b("\t* Source:\t\t" + localMaliciousAppInfo.apkSource);
            b("\t* Path:\t\t\t" + localMaliciousAppInfo.appPath);
            b("\t* Notify ZCloud:\t" + bool);
            if (!bool) {
              break label678;
            }
            localObject = com.zimperium.zdetection.utils.a.b(new File(localMaliciousAppInfo.appPath));
            ZipsZcloud.zEventThreatDetected.Builder localBuilder;
            ZipsZcloud.zEventThreatDetected.zHostAttack.Builder localBuilder1;
            if (localMaliciousAppInfo.malwareName.length() > 0)
            {
              localBuilder = ZipsZcloud.zEventThreatDetected.newBuilder().setType(ZipsZcloud.threat_type.APK_SUSPECTED);
              localBuilder1 = ZipsZcloud.zEventThreatDetected.zHostAttack.newBuilder().setFilename(localMaliciousAppInfo.appPath).setFileHash(localMaliciousAppInfo.apkHash);
              if (TextUtils.equals((CharSequence)localObject, "CATEGORY_INSTALLED"))
              {
                localObject = ZipsZcloud.ScanCategory.INSTALLED;
                localObject = localBuilder.setHostAttack(localBuilder1.setMalwareScanCategory((ZipsZcloud.ScanCategory)localObject).setMalwareRiskScale(5).setProcess(localMaliciousAppInfo.packageName).setDetectedLocally(localMaliciousAppInfo.detectedLocally).setMalwareThreatName(localMaliciousAppInfo.malwareName).setApplication(localMaliciousAppInfo.packageName).addMalwareMatches(ZipsZcloud.zEventThreatDetected.zHostAttack.zMalwareMatch.newBuilder().setName(localMaliciousAppInfo.malwareName).setScore(5.0F))).build();
                b("\t REPORTING: file=" + ((ZipsZcloud.zEventThreatDetected)localObject).getHostAttack().getFilename() + " (" + ((ZipsZcloud.zEventThreatDetected)localObject).getHostAttack().getFileHash() + ")");
                localObject = ZipsZcloud.zIPSEvent.newBuilder().setThreatDetected((ZipsZcloud.zEventThreatDetected)localObject).build();
                Zcloud.notifyZipsEvent(ZipsZcloud.zips_event_names.EVENT_THREAT_DETECTED, (ZipsZcloud.zIPSEvent)localObject);
              }
              else
              {
                localObject = ZipsZcloud.ScanCategory.DOWNLOADED;
                continue;
              }
            }
            else
            {
              localBuilder = ZipsZcloud.zEventThreatDetected.newBuilder().setType(ZipsZcloud.threat_type.APK_SUSPECTED);
              localBuilder1 = ZipsZcloud.zEventThreatDetected.zHostAttack.newBuilder().setFilename(localMaliciousAppInfo.appPath).setFileHash(localMaliciousAppInfo.apkHash);
              if (TextUtils.equals((CharSequence)localObject, "CATEGORY_INSTALLED"))
              {
                localObject = ZipsZcloud.ScanCategory.INSTALLED;
                localObject = localBuilder.setHostAttack(localBuilder1.setMalwareScanCategory((ZipsZcloud.ScanCategory)localObject).setMalwareRiskScale(5).setProcess(localMaliciousAppInfo.packageName).setDetectedLocally(localMaliciousAppInfo.detectedLocally).setMalwareThreatName(localMaliciousAppInfo.malwareName).setApplication(localMaliciousAppInfo.packageName)).build();
                b("\t REPORTING: file=" + ((ZipsZcloud.zEventThreatDetected)localObject).getHostAttack().getFilename() + " (" + ((ZipsZcloud.zEventThreatDetected)localObject).getHostAttack().getFileHash() + ")");
                localObject = ZipsZcloud.zIPSEvent.newBuilder().setThreatDetected((ZipsZcloud.zEventThreatDetected)localObject).build();
                Zcloud.notifyZipsEvent(ZipsZcloud.zips_event_names.EVENT_THREAT_DETECTED, (ZipsZcloud.zIPSEvent)localObject);
              }
            }
          }
          catch (Exception localException)
          {
            b("\t* Exception: \t\t" + localException);
            ZLog.errorException("\t* Error sending new malware threat", localException);
          }
          break;
          ZipsZcloud.ScanCategory localScanCategory = ZipsZcloud.ScanCategory.DOWNLOADED;
        }
      }
      if (i1 == 0) {
        break;
      }
      if (ZipsStatistics.getBooleanStat("STAT_PUSH_TOKEN_REGISTERED", false))
      {
        b("\t* PUSH is enabled for this device. Wait for push from zbackend.");
        return;
      }
      b("\t* PUSH is not enabled for this device. Queuing command url read.");
      ZcloudRunnerService.queueCommandPoll(10000L);
      ZcloudRunnerService.queueCommandPoll(TimeUnit.MINUTES.toMillis(1L) + 11000L);
      ZcloudRunnerService.queueCommandPoll(TimeUnit.MINUTES.toMillis(5L));
      return;
      break label683;
      i1 = 1;
    }
  }
  
  private void h()
  {
    int i1 = this.r.c();
    int i2 = this.q.c();
    int i3 = this.o.c();
    int i4 = this.p.c();
    int i5 = this.s.c();
    b("processScanProgress():\tlocal=" + i1 + "  remote=" + i2 + "  zblb=" + i3 + "  filter=" + i4 + " sideload=" + i5);
    if (this.f != null)
    {
      ArrayList localArrayList = new ArrayList();
      if (j()) {
        localArrayList.add(Integer.valueOf(i1));
      }
      if (i()) {
        localArrayList.add(Integer.valueOf(i2));
      }
      if (k()) {
        localArrayList.add(Integer.valueOf(i3));
      }
      if (l()) {
        localArrayList.add(Integer.valueOf(i4));
      }
      if (m()) {
        localArrayList.add(Integer.valueOf(i5));
      }
      this.f.onScanProgress(((Integer)Collections.min(localArrayList)).intValue());
    }
  }
  
  private boolean i()
  {
    return this.c.contains(c.b);
  }
  
  private boolean j()
  {
    return this.c.contains(c.a);
  }
  
  private boolean k()
  {
    return this.c.contains(c.c);
  }
  
  private boolean l()
  {
    return this.c.contains(c.d);
  }
  
  private boolean m()
  {
    return this.c.contains(c.e);
  }
  
  public MalwareScanController a()
  {
    b("start()");
    if (this.f != null) {
      this.f.onScanStart(this.l.size());
    }
    this.j = System.currentTimeMillis();
    if (i()) {
      this.q.a(this.l.size());
    }
    if (j()) {
      this.r.a(this.l.size());
    }
    if (k()) {
      this.o.a(this.l.size());
    }
    if (l()) {
      this.p.a(this.l.size());
    }
    if (m()) {
      this.s.a(this.l.size());
    }
    Object localObject = new ArrayList(this.l.values());
    List localList;
    if ((this.c.size() == 1) && (this.c.get(0) == c.d))
    {
      localObject = a((List)localObject, 50);
      b("\tBatched " + this.l.size() + " into: " + ((List)localObject).size() + " chunks.");
      localObject = ((List)localObject).iterator();
      i2 = 0;
      if (!((Iterator)localObject).hasNext()) {
        break label671;
      }
      localList = (List)((Iterator)localObject).next();
      if (!ZipsStatistics.getBooleanStat("STAT_BLOCK_REMOTE_SCAN", false)) {
        break label415;
      }
      b("\tCollection policy blocks schedule application information, blocking remote malware scan.");
      i1 = i2;
    }
    Iterator localIterator;
    h localH;
    for (;;)
    {
      if (!j()) {
        break label490;
      }
      localIterator = localList.iterator();
      i2 = i1;
      for (;;)
      {
        i1 = i2;
        if (!localIterator.hasNext()) {
          break;
        }
        localH = (h)localIterator.next();
        b("\tSubmitting: " + localH.getPath() + " to be z9 scanned");
        this.k.submit(new d(localH, this.r));
        i2 += 1;
      }
      localObject = a((List)localObject, 5);
      break;
      label415:
      i1 = i2;
      if (i())
      {
        b("\tSubmitting: " + localList.size() + " files to be remotely scanned");
        this.k.submit(new e(this.h, localList, this.q));
        i1 = i2 + 1;
      }
    }
    label490:
    int i2 = i1;
    if (k())
    {
      localIterator = localList.iterator();
      for (;;)
      {
        i2 = i1;
        if (!localIterator.hasNext()) {
          break;
        }
        localH = (h)localIterator.next();
        b("\tSubmitting: " + localH.getPath() + " to be ZBLB scanned");
        this.k.submit(new g(localH, this.o));
        i1 += 1;
      }
    }
    int i1 = i2;
    if (l())
    {
      this.k.submit(new b(this.i, localList, this.p));
      i1 = i2 + 1;
    }
    if (m())
    {
      this.k.submit(new f(localList, this.s));
      i1 += 1;
    }
    for (;;)
    {
      i2 = i1;
      break;
      label671:
      this.k.shutdown();
      b("\tQueue count: " + i2);
      return new a.1(this);
    }
  }
  
  public static class a
  {
    private Context a;
    private List<a.d> b = new ArrayList();
    private List<a.c> c = new ArrayList();
    private List<h> d = new ArrayList();
    private MalwareScanCallback e;
    private String f = "";
    private String g = "";
    private boolean h = false;
    private long i = 0L;
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    private static void d(String paramString)
    {
      ZLog.i("Builder: " + paramString, new Object[0]);
    }
    
    public a a(long paramLong)
    {
      this.i = paramLong;
      return this;
    }
    
    public a a(ApplicationInfo paramApplicationInfo)
    {
      h localH = new h(paramApplicationInfo.publicSourceDir);
      if (!this.d.contains(localH))
      {
        localH.a(paramApplicationInfo.packageName);
        this.d.add(localH);
      }
      return this;
    }
    
    public a a(MalwareScanCallback paramMalwareScanCallback)
    {
      this.e = paramMalwareScanCallback;
      return this;
    }
    
    public a a(a.c paramC)
    {
      if (!this.c.contains(paramC)) {
        this.c.add(paramC);
      }
      return this;
    }
    
    public a a(a.d paramD)
    {
      if (!this.b.contains(paramD)) {
        this.b.add(paramD);
      }
      return this;
    }
    
    public a a(File paramFile)
    {
      paramFile = new h(paramFile.getPath());
      if (!this.d.contains(paramFile)) {
        this.d.add(paramFile);
      }
      return this;
    }
    
    public a a(String paramString)
    {
      this.f = paramString;
      return this;
    }
    
    public a a(boolean paramBoolean)
    {
      this.h = paramBoolean;
      return this;
    }
    
    public a a()
    {
      return new a(this.a, this.i, this.h, this.f, this.g, this.d, this.c, this.b, this.e, null);
    }
    
    public a b(String paramString)
    {
      this.g = paramString;
      return this;
    }
    
    public a c(String paramString)
    {
      PackageManager localPackageManager = this.a.getPackageManager();
      try
      {
        a(new File(localPackageManager.getApplicationInfo(paramString, 0).publicSourceDir));
        return this;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        d("\tException: " + paramString);
      }
      return this;
    }
  }
  
  public static enum c
  {
    private c() {}
  }
}
