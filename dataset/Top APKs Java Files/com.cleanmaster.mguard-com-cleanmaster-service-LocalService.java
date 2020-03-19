package com.cleanmaster.service;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageMoveObserver.Stub;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import client.core.model.TimeStamp;
import com.cleanmaster.base.util.e.e.a;
import com.cleanmaster.boost.process.util.ProcessModel;
import com.cleanmaster.boost.process.util.o.b;
import com.cleanmaster.cloudconfig.v;
import com.cleanmaster.cloudconfig.v.a;
import com.cleanmaster.common.a.l;
import com.cleanmaster.common.a.q;
import com.cleanmaster.common.a.r;
import com.cleanmaster.common.a.u;
import com.cleanmaster.dao.InstallMoveInfo;
import com.cleanmaster.data.filter.IFilter;
import com.cleanmaster.feedback.FeedBackDataBean;
import com.cleanmaster.func.cache.DiskCache;
import com.cleanmaster.func.cache.e.b;
import com.cleanmaster.kinfoc.k;
import com.cleanmaster.kinfoc.p;
import com.cleanmaster.security.scan.ApkResultImpl;
import com.cleanmaster.security.scan.IApkResult;
import com.cleanmaster.security.scan.IElfResult;
import com.cleanmaster.security.scan.MonitorInstallActivity;
import com.cleanmaster.security.scan.model.ScanUnknownFilesModel;
import com.cleanmaster.security.scan.model.c.a;
import com.cleanmaster.security.scan.o.3;
import com.cleanmaster.security.scan.o.4;
import com.cleanmaster.security.upload.ResultSampleReport;
import com.cleanmaster.security.upload.ResultSampleReport.a;
import com.cleanmaster.security.upload.ResultSampleReport.b;
import com.cleanmaster.ui.app.market.transport.AppCategoryParam;
import com.cleanmaster.util.OpLog;
import com.cleanmaster.util.i;
import com.hoi.antivirus.AntiVirusFunc;
import com.ijinshan.cleaner.bean.UninstallAppData;
import com.ijinshan.cleaner.receiver.UninstallBroadcastReceiver;
import com.ijinshan.rt.common.IRootKeeper;
import com.keniu.security.curlmonitor.MonitorManager.a;
import com.keniu.security.monitor.MonitorManager;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import ks.cm.antivirus.api.ICMSecurityAPI;
import ks.cm.antivirus.api.ICMSecurityAPI.Stub;

public class LocalService
  extends IntentService
{
  public static boolean a;
  public static boolean c = false;
  public static client.core.model.g e = new client.core.model.g("ui");
  private static long f;
  PackageManager b = null;
  int d = 0;
  private h g = new h();
  private IFilter<PackageInfo> h = null;
  
  static
  {
    LocalService.class.getSimpleName();
    a = true;
    f = 15000L;
  }
  
  public LocalService()
  {
    super("LocalService");
  }
  
  private static String a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "image";
    case 0: 
      return "image";
    case 1: 
      return "image_1";
    }
    return "image_2";
  }
  
  public static void a(Context paramContext)
  {
    if (com.cleanmaster.configmanager.d.a(paramContext).N()) {
      g(paramContext, ":notification");
    }
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra(":page", paramInt);
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_PRELOAD_AD_RESULT_PAGE");
    paramContext.startService(localIntent);
  }
  
  private static void a(Context paramContext, final Intent paramIntent, final d paramD)
  {
    if (!com.cm.root.d.a().g())
    {
      paramContext = new MonitorManager.a()
      {
        private int a = 1;
        
        public final int monitorNotify(int paramAnonymousInt, Object paramAnonymousObject1, Object paramAnonymousObject2)
        {
          if (((Boolean)paramAnonymousObject1).booleanValue())
          {
            this.b.startService(paramIntent);
            MonitorManager.a().b(MonitorManager.e, this);
          }
          for (;;)
          {
            return 0;
            paramAnonymousInt = this.a + 1;
            this.a = paramAnonymousInt;
            if (paramAnonymousInt >= this.d)
            {
              MonitorManager.a().b(MonitorManager.e, this);
              if (paramD != null)
              {
                paramAnonymousObject1 = paramD;
                paramAnonymousInt = i.c();
                if ((i.i(paramAnonymousObject1.a) <= 0) && (paramAnonymousInt != 0) && (!com.cm.root.d.a().g()))
                {
                  com.cm.root.d.a();
                  if (com.cm.root.d.b())
                  {
                    i.j(paramAnonymousObject1.a);
                    i.g(paramAnonymousObject1.a);
                  }
                }
                else
                {
                  paramAnonymousObject1 = new com.cleanmaster.common.a.o();
                  paramAnonymousObject1.c = LocalService.e;
                  client.core.a.a().a(paramAnonymousObject1);
                }
              }
            }
            else
            {
              com.cm.root.d.a().f();
            }
          }
        }
      };
      MonitorManager.a().a(MonitorManager.e, paramContext);
      com.cm.root.d.a().f();
      return;
    }
    paramContext.startService(paramIntent);
  }
  
  public static void a(Context paramContext, ScanUnknownFilesModel paramScanUnknownFilesModel)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_SECURITY_UPLOAD_UNKNOWN_FILES");
    localIntent.putExtra(":unknown_files", paramScanUnknownFilesModel);
    paramContext.startService(localIntent);
  }
  
  public static void a(Context paramContext, com.cleanmaster.ui.resultpage.item.h paramH)
  {
    if ((paramContext == null) || (paramH == null) || (paramH.D == null)) {
      return;
    }
    paramH = paramH.D;
    new StringBuilder(" sort : 存储展示次数 :  n : ").append(paramH.c).append(" page : ").append(paramH.a).append("  posid : ").append(paramH.b).append(" 展示次数 : ").append(paramH.e);
    Intent localIntent = new Intent();
    localIntent.putExtra(":pageid", paramH.a);
    localIntent.putExtra(":posid", paramH.b);
    localIntent.putExtra(":n", paramH.c);
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_REPORT_RESULT_PAGE_STAT");
    paramContext.startService(localIntent);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.putExtra(":newversion", paramString);
    localIntent.putExtra(":force", false);
    localIntent.setAction("com.cleanmaster.service.ACTION_SYNC_RESULT_PAGE_CTRL");
    paramContext.startService(localIntent);
  }
  
  public static void a(Context paramContext, String paramString, long paramLong)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.putExtra("pkg", paramString);
    localIntent.putExtra("size", paramLong);
    localIntent.setAction("com.cleanmaster.service.ACTION_START_TOP_APP_ACTIVITY");
    paramContext.startService(localIntent);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.putExtra(":content", paramString1);
    localIntent.putExtra(":contact", paramString2);
    localIntent.putExtra(":type", paramString3);
    localIntent.setAction("com.cleanmaster.service.ACTION_UPLOAD_FEEDBACK_SIDE_SLIP");
    paramContext.startService(localIntent);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, FeedBackDataBean paramFeedBackDataBean)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.putExtra(":content", paramString1);
    localIntent.putExtra(":contact", paramString2);
    localIntent.putExtra(":type", paramString3);
    localIntent.putExtra(":uploadImagePaths", paramArrayOfString);
    localIntent.putExtra(":feedback_data", paramFeedBackDataBean);
    localIntent.setAction("com.cleanmaster.service.ACTION_UPLOAD_FEEDBACK_AND_LOGS");
    paramContext.startService(localIntent);
  }
  
  public static void a(Context paramContext, ArrayList<String> paramArrayList)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.putStringArrayListExtra(":target", paramArrayList);
    localIntent.setAction("com.cleanmaster.service.ACTION_BACKUP_DEL");
    paramContext.startService(localIntent);
  }
  
  public static void a(Context paramContext, ArrayList<String> paramArrayList, Bundle paramBundle)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_RESTORE");
    localIntent.putStringArrayListExtra(":packages", paramArrayList);
    if (paramBundle != null) {
      localIntent.putExtra(":meta_data", paramBundle);
    }
    a(paramContext, localIntent, null);
  }
  
  public static void a(Context paramContext, ArrayList<String> paramArrayList, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_GET_SYSTEM_MOVABLE_APPS");
    localIntent.putStringArrayListExtra(":key-search", paramArrayList);
    localIntent.putExtra(":key-tag", paramString);
    paramContext.startService(localIntent);
  }
  
  private static void a(Context paramContext, ArrayList<AppCategoryParam> paramArrayList, boolean paramBoolean)
  {
    if ((paramArrayList == null) || (paramArrayList.isEmpty())) {
      return;
    }
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_SYNC_APP_CATEGORY");
    localIntent.putExtra(":params", paramArrayList);
    localIntent.putExtra(":upQueryTime", paramBoolean);
    paramContext.startService(localIntent);
  }
  
  public static void a(Context paramContext, List<com.cleanmaster.common.model.a> paramList, String paramString)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return;
    }
    ArrayList localArrayList = new ArrayList(paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(((com.cleanmaster.common.model.a)paramList.next()).b);
    }
    a(paramContext, localArrayList, paramString);
  }
  
  private static void a(Intent paramIntent)
  {
    if (LibcoreWrapper.a.b("app_market", "B_INFOC_SSID", false)) {}
    int i;
    do
    {
      do
      {
        do
        {
          return;
          paramIntent = paramIntent.getStringExtra(":ssid");
        } while (TextUtils.isEmpty(paramIntent));
        localObject = com.keniu.security.d.a().getSharedPreferences("diss", 0);
        i = ((SharedPreferences)localObject).getInt(paramIntent, 0);
        ((SharedPreferences)localObject).edit().putInt(paramIntent, i + 1).commit();
        paramIntent = Calendar.getInstance();
        paramIntent.setTimeInMillis(System.currentTimeMillis());
        i = paramIntent.get(6);
      } while (com.keniu.security.d.a().getSharedPreferences("misc", 0).getInt(":diss-report", 0) == i);
      paramIntent = com.keniu.security.d.a().getSharedPreferences("diss", 0);
      localObject = paramIntent.getAll();
    } while ((localObject == null) || (((Map)localObject).isEmpty()));
    Object localObject = ((Map)localObject).keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      p.a().a("cm_wifimsg2", "ssid=" + com.cleanmaster.kinfocreporter.a.infocEscape(str), true);
    }
    paramIntent.edit().clear().commit();
    com.keniu.security.d.a().getSharedPreferences("misc", 0).edit().putInt(":diss-report", i).commit();
  }
  
  private static void a(client.core.model.c paramC, String paramString)
  {
    paramC.c = e;
    paramC.a = paramString;
    client.core.a.a().a(paramC);
  }
  
  private static void a(String paramString)
  {
    Log.e("app2sd", paramString);
  }
  
  private void a(List<com.cleanmaster.common.model.a> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(((com.cleanmaster.common.model.a)paramList.next()).b);
    }
    new GetPackageStatObserver(localArrayList);
  }
  
  public static boolean a(Context paramContext, List<PackageInfo> paramList, Map<String, com.cleanmaster.ui.app.market.transport.a> paramMap)
  {
    if (paramList == null) {
      paramList = com.cleanmaster.func.cache.e.a().b.c();
    }
    for (;;)
    {
      Object localObject1 = paramMap;
      if (paramMap == null) {
        localObject1 = DiskCache.a().a(null);
      }
      if ((localObject1 == null) || (((Map)localObject1).isEmpty())) {
        paramMap = paramList;
      }
      Object localObject2;
      for (;;)
      {
        if (!paramMap.isEmpty())
        {
          com.cleanmaster.ui.app.provider.download.a.a();
          paramMap = com.cleanmaster.ui.app.provider.download.a.a(paramMap);
          DiskCache.a().b(paramMap);
          ((Map)localObject1).putAll(paramMap);
          b(com.cleanmaster.ui.app.a.g.b());
        }
        localObject2 = AppCategoryParam.getSync(paramList, (Map)localObject1);
        if (!((ArrayList)localObject2).isEmpty()) {
          break;
        }
        paramContext = com.cleanmaster.ui.app.a.g.a();
        paramContext.c = e;
        client.core.a.a().a(paramContext);
        return false;
        paramMap = new ArrayList();
        localObject2 = paramList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
          if (!((Map)localObject1).containsKey(localPackageInfo.packageName)) {
            paramMap.add(localPackageInfo);
          }
        }
      }
      if ((!b.p("onHandle_ACTION_SYNC_APP_CATEGORY")) && (localObject1 != null) && (!((Map)localObject1).isEmpty()) && (!com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).a("new_app_un_category", false)))
      {
        paramContext = com.cleanmaster.ui.app.a.g.a();
        paramContext.c = e;
        client.core.a.a().a(paramContext);
        return false;
      }
      int j = ((ArrayList)localObject2).size();
      int i = 0;
      paramList = null;
      if (i < j)
      {
        paramMap = paramList;
        if (i % 50 == 0) {
          if (paramList != null) {
            if (i + 1 != j) {
              break label329;
            }
          }
        }
        label329:
        for (boolean bool = true;; bool = false)
        {
          a(paramContext, paramList, bool);
          paramMap = new ArrayList(50);
          paramMap.add(((ArrayList)localObject2).get(i));
          i += 1;
          paramList = paramMap;
          break;
        }
      }
      if (!paramList.isEmpty()) {
        a(paramContext, paramList, true);
      }
      return true;
    }
  }
  
  public static boolean a(File paramFile)
  {
    return (paramFile != null) && (paramFile.exists());
  }
  
  private static List<IElfResult> b(List<IElfResult> paramList)
  {
    int j = 0;
    Object localObject1 = com.keniu.security.d.a();
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = paramList.iterator();
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (IElfResult)((Iterator)localObject2).next();
      if (new File(((IElfResult)localObject3).b()).exists()) {
        localArrayList.add(localObject3);
      }
    }
    paramList.clear();
    if (localArrayList.isEmpty()) {
      return null;
    }
    if (LibcoreWrapper.a.a(((IElfResult)localArrayList.get(localArrayList.size() - 1)).e()) >= 2L)
    {
      paramList = new AntiVirusFunc();
      localObject2 = new String[localArrayList.size()];
      localObject3 = new String[localArrayList.size()];
      localObject1 = LibcoreWrapper.a.M((Context)localObject1);
      int i = 0;
      while (i < localArrayList.size())
      {
        localObject2[i] = ((IElfResult)localArrayList.get(i)).b();
        localObject3[i] = "00000000000000000000000000000000";
        i += 1;
      }
      new c.a()
      {
        public final boolean a()
        {
          return true;
        }
      };
      paramList = ResultSampleReport.a((String[])localObject2, (String[])localObject3, paramList, (String)localObject1, 2);
      if (paramList != null)
      {
        localObject1 = new ArrayList();
        i = j;
        while (i < localArrayList.size())
        {
          localObject2 = (ResultSampleReport.a)paramList.get(i);
          if (((ResultSampleReport.a)localObject2).a == 1)
          {
            localObject3 = (IElfResult)localArrayList.get(i);
            ((IElfResult)localObject3).a(((ResultSampleReport.a)localObject2).b);
            ((IElfResult)localObject3).a(System.currentTimeMillis());
            ((List)localObject1).add(localObject3);
          }
          i += 1;
        }
        return c((List)localObject1);
      }
      return null;
    }
    return c(localArrayList);
  }
  
  private void b()
  {
    if (!com.cleanmaster.base.util.net.b.i(getBaseContext())) {}
    long l;
    do
    {
      return;
      l = com.cleanmaster.configmanager.d.a(getBaseContext()).a("cm_last_update_user_apps_description", 0L);
    } while ((l > 0L) && (System.currentTimeMillis() - l < 86400000L));
    new h();
    Object localObject = com.cleanmaster.func.cache.e.a().b.a();
    LinkedList localLinkedList = new LinkedList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((h.a(localPackageInfo)) && (!getPackageName().equals(localPackageInfo.packageName)))
      {
        b.a();
        if (TextUtils.isEmpty(b.f(localPackageInfo.packageName)))
        {
          b.a();
          if (b.b(localPackageInfo.packageName) == null) {
            localLinkedList.add(localPackageInfo.packageName);
          }
        }
      }
    }
    v.a().a(localLinkedList, new v.a()
    {
      public final void a(String paramAnonymousString1, String paramAnonymousString2)
      {
        if (!TextUtils.isEmpty(paramAnonymousString2))
        {
          b.a();
          b.a(paramAnonymousString1, paramAnonymousString2);
        }
      }
    });
    com.cleanmaster.configmanager.d.a(getBaseContext()).b("cm_last_update_user_apps_description", System.currentTimeMillis());
  }
  
  public static void b(Context paramContext)
  {
    if ((!com.cleanmaster.configmanager.d.a(paramContext).a("cm_next_recommand_game_uninstall_dialog", false)) && (com.cleanmaster.configmanager.d.a(paramContext).N())) {
      g(paramContext, ":dialog");
    }
  }
  
  public static void b(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_FIX_SYSTEM_RISK_BY_DUBA");
    localIntent.putExtra("hole_type", paramInt);
    paramContext.startService(localIntent);
  }
  
  public static void b(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_GET_APPLICATION_INFO");
    localIntent.putExtra(":key-tag", paramString);
    paramContext.startService(localIntent);
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, FeedBackDataBean paramFeedBackDataBean)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.putExtra(":content", paramString1);
    localIntent.putExtra(":contact", paramString2);
    localIntent.putExtra(":type", paramString3);
    localIntent.putExtra(":uploadImagePaths", paramArrayOfString);
    localIntent.putExtra(":follow_transfer_model", 107);
    localIntent.putExtra(":feedback_data", paramFeedBackDataBean);
    localIntent.setAction("com.cleanmaster.service.ACTION_UPLOAD_FEEDBACK_CPU");
    paramContext.startService(localIntent);
  }
  
  public static void b(Context paramContext, ArrayList<String> paramArrayList)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_MOVE");
    localIntent.putStringArrayListExtra(":packages", paramArrayList);
    a(paramContext, localIntent, null);
  }
  
  public static void b(Context paramContext, ArrayList<UninstallAppData> paramArrayList, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_SILENCE_UNINSTALL");
    localIntent.putExtra(":tag", paramString);
    localIntent.putParcelableArrayListExtra(":uninstall-packages", paramArrayList);
    a(paramContext, localIntent, null);
  }
  
  private static void b(Intent paramIntent)
  {
    paramIntent = paramIntent.getStringArrayListExtra(":target");
    if (paramIntent == null) {
      return;
    }
    paramIntent = paramIntent.iterator();
    while (paramIntent.hasNext()) {
      com.cleanmaster.base.c.b(new File((String)paramIntent.next()));
    }
    b(new com.cleanmaster.ui.app.a.b());
  }
  
  private static void b(client.core.model.c paramC)
  {
    paramC.c = e;
    client.core.a.a().a(paramC);
  }
  
  private static boolean b(File paramFile)
  {
    boolean bool2 = false;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < 3) {}
      try
      {
        Thread.sleep(100L);
        i += 1;
        if (!i.e(paramFile)) {
          continue;
        }
        bool1 = true;
        return bool1;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
  }
  
  private static List<IElfResult> c(List<IElfResult> paramList)
  {
    int j = 0;
    if (paramList.size() == 0) {
      return null;
    }
    ArrayList localArrayList1 = new ArrayList();
    Object localObject = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    while (i < paramList.size())
    {
      ((List)localObject).add(((IElfResult)paramList.get(i)).b());
      localArrayList2.add(((IElfResult)paramList.get(i)).d());
      i += 1;
    }
    localObject = ResultSampleReport.a(com.keniu.security.d.a(), (List)localObject, localArrayList2, 2);
    i = j;
    if (localObject == null) {
      return localArrayList1;
    }
    while (i < localObject.length)
    {
      if (localObject[i] == 0) {
        localArrayList1.add((IElfResult)paramList.get(i));
      }
      i += 1;
    }
    return localArrayList1;
  }
  
  private void c()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    Object localObject1 = com.cleanmaster.func.cache.e.a().b.a();
    HashMap localHashMap = DiskCache.a().d();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
      ContentValues localContentValues = (ContentValues)localHashMap.get(((PackageInfo)localObject2).packageName);
      if ((com.cleanmaster.base.c.a(((PackageInfo)localObject2).applicationInfo)) && (localContentValues != null))
      {
        localObject2 = com.cleanmaster.common.model.a.a(this, localPackageManager, (PackageInfo)localObject2);
        ((com.cleanmaster.common.model.a)localObject2).h = localContentValues.getAsLong("size").longValue();
        ((com.cleanmaster.common.model.a)localObject2).j = localContentValues.getAsLong("unused_last").longValue();
        localArrayList.add(localObject2);
      }
    }
    b(new com.cleanmaster.service.a.a(localArrayList));
  }
  
  public static void c(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_GET_APPLICATION_INFO");
    paramContext.startService(localIntent);
  }
  
  public static void c(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_GET_SYSTEM_MOVABLE_APPS");
    localIntent.putExtra(":key-tag", paramString);
    paramContext.startService(localIntent);
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, FeedBackDataBean paramFeedBackDataBean)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.putExtra(":content", paramString1);
    localIntent.putExtra(":contact", paramString2);
    localIntent.putExtra(":type", paramString3);
    localIntent.putExtra(":uploadImagePaths", paramArrayOfString);
    localIntent.putExtra(":follow_transfer_model", 115);
    localIntent.putExtra(":feedback_data", paramFeedBackDataBean);
    localIntent.setAction("com.cleanmaster.service.ACTION_UPLOAD_FEEDBACK_GAMEBOX");
    paramContext.startService(localIntent);
  }
  
  public static void c(Context paramContext, ArrayList<String> paramArrayList)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_SYSTEM_MOVE_TO_SD");
    localIntent.putStringArrayListExtra(":packages", paramArrayList);
    a(paramContext, localIntent, null);
  }
  
  private void c(Intent paramIntent)
  {
    long l = System.currentTimeMillis();
    Object localObject1 = paramIntent.getStringExtra(":content");
    Object localObject2 = paramIntent.getStringExtra(":contact");
    Object localObject3 = paramIntent.getStringExtra(":type");
    int i = paramIntent.getIntExtra(":follow_transfer_model", 0);
    String[] arrayOfString = paramIntent.getStringArrayExtra(":uploadImagePaths");
    paramIntent = (FeedBackDataBean)paramIntent.getSerializableExtra(":feedback_data");
    LibcoreWrapper.a.a(this, null);
    HashMap localHashMap = new HashMap();
    localHashMap.put("content", localObject1);
    localHashMap.put("contact", localObject2);
    localHashMap.put("model", Build.MODEL);
    localHashMap.put("type", localObject3);
    localHashMap.put("sysversion", Build.VERSION.RELEASE);
    localHashMap.put("cmversion", d());
    localHashMap.put("uuid", LibcoreWrapper.a.p(this));
    localHashMap.put("syslang", Locale.getDefault().getLanguage());
    localHashMap.put("follow_transfer_model", String.valueOf(i));
    if ((paramIntent != null) && (!paramIntent.isGameBoost) && (paramIntent.isMisFile))
    {
      localHashMap.put("middelreason", paramIntent.misFileReason);
      localHashMap.put("misdeltype", paramIntent.misFileType);
    }
    localObject2 = new com.cleanmaster.kinfoc.d[4];
    localObject3 = new com.cleanmaster.kinfoc.d();
    paramIntent = null;
    if (com.cleanmaster.base.c.q() != null)
    {
      localObject1 = new File(com.cleanmaster.base.c.q());
      paramIntent = (Intent)localObject1;
      if (((File)localObject1).length() > 2097152L) {
        paramIntent = null;
      }
    }
    if ((paramIntent != null) && (paramIntent.exists())) {
      ((com.cleanmaster.kinfoc.d)localObject3).a = paramIntent;
    }
    for (;;)
    {
      ((com.cleanmaster.kinfoc.d)localObject3).b = "log";
      if ((((com.cleanmaster.kinfoc.d)localObject3).a.exists()) && (((com.cleanmaster.kinfoc.d)localObject3).a.length() > 0L)) {
        localObject2[0] = localObject3;
      }
      if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
        break;
      }
      i = 0;
      while (i < arrayOfString.length)
      {
        paramIntent = arrayOfString[i];
        if (!TextUtils.isEmpty(paramIntent))
        {
          localObject1 = new com.cleanmaster.kinfoc.d();
          ((com.cleanmaster.kinfoc.d)localObject1).a = new File(paramIntent);
          ((com.cleanmaster.kinfoc.d)localObject1).b = a(i);
          if ((((com.cleanmaster.kinfoc.d)localObject1).a.exists()) && (((com.cleanmaster.kinfoc.d)localObject1).a.length() > 0L)) {
            localObject2[(i + 1)] = localObject1;
          }
        }
        i += 1;
      }
      if (Environment.getExternalStorageDirectory() != null) {
        ((com.cleanmaster.kinfoc.d)localObject3).a = new File(com.keniu.security.a.f(), "/logs/0.log");
      }
    }
    b(new com.cleanmaster.feedback.a(k.a("http://fk.cm.ksmobile.com/CMFeedback", localHashMap, (com.cleanmaster.kinfoc.d[])localObject2), l));
  }
  
  private static String d()
  {
    String str2 = com.keniu.security.update.m.a().d;
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    return str1;
  }
  
  public static void d(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_GET_SYSTEM_MOVABLE_APPS");
    paramContext.startService(localIntent);
  }
  
  public static void d(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_PACKAGE_ADD");
    localIntent.putExtra(":package-name", paramString);
    paramContext.startService(localIntent);
  }
  
  public static void d(Context paramContext, String paramString1, String paramString2, String[] paramArrayOfString, String paramString3, FeedBackDataBean paramFeedBackDataBean)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.putExtra(":content", paramString1);
    localIntent.putExtra(":contact", paramString2);
    localIntent.putExtra(":type", paramString3);
    localIntent.putExtra(":uploadImagePaths", paramArrayOfString);
    localIntent.putExtra(":follow_transfer_model", 116);
    localIntent.putExtra(":feedback_data", paramFeedBackDataBean);
    localIntent.setAction("com.cleanmaster.service.ACTION_UPLOAD_FEEDBACK_GAMEBOX");
    paramContext.startService(localIntent);
  }
  
  public static void d(Context paramContext, ArrayList<String> paramArrayList)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_SYSTEM_MOVE_TO_PHONE");
    localIntent.putStringArrayListExtra(":packages", paramArrayList);
    a(paramContext, localIntent, null);
  }
  
  private void d(Intent paramIntent)
  {
    long l = System.currentTimeMillis();
    Object localObject1 = paramIntent.getStringExtra(":content");
    Object localObject2 = paramIntent.getStringExtra(":contact");
    Object localObject3 = paramIntent.getStringExtra(":type");
    int i = paramIntent.getIntExtra(":follow_transfer_model", 0);
    String[] arrayOfString = paramIntent.getStringArrayExtra(":uploadImagePaths");
    paramIntent = (FeedBackDataBean)paramIntent.getSerializableExtra(":feedback_data");
    LibcoreWrapper.a.a(this, null);
    HashMap localHashMap = new HashMap();
    localHashMap.put("content", localObject1);
    localHashMap.put("contact", localObject2);
    localHashMap.put("model", Build.MODEL);
    localHashMap.put("type", localObject3);
    localHashMap.put("sysversion", Build.VERSION.RELEASE);
    localHashMap.put("cmversion", d());
    localHashMap.put("uuid", LibcoreWrapper.a.p(this));
    localHashMap.put("syslang", Locale.getDefault().getLanguage());
    localHashMap.put("follow_transfer_model", String.valueOf(i));
    if ((paramIntent != null) && (!paramIntent.isGameBoost) && (paramIntent.isMisFile))
    {
      localHashMap.put("middelreason", paramIntent.misFileReason);
      localHashMap.put("misdeltype", paramIntent.misFileType);
    }
    localObject2 = new com.cleanmaster.kinfoc.d[4];
    localObject3 = new com.cleanmaster.kinfoc.d();
    paramIntent = null;
    if (com.cleanmaster.base.c.q() != null)
    {
      localObject1 = new File(com.cleanmaster.base.c.q());
      paramIntent = (Intent)localObject1;
      if (((File)localObject1).length() > 2097152L) {
        paramIntent = null;
      }
    }
    if ((paramIntent != null) && (paramIntent.exists())) {
      ((com.cleanmaster.kinfoc.d)localObject3).a = paramIntent;
    }
    for (;;)
    {
      ((com.cleanmaster.kinfoc.d)localObject3).b = "log";
      if ((((com.cleanmaster.kinfoc.d)localObject3).a.exists()) && (((com.cleanmaster.kinfoc.d)localObject3).a.length() > 0L)) {
        localObject2[0] = localObject3;
      }
      if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
        break;
      }
      i = 0;
      while (i < arrayOfString.length)
      {
        paramIntent = arrayOfString[i];
        if (!TextUtils.isEmpty(paramIntent))
        {
          localObject1 = new com.cleanmaster.kinfoc.d();
          ((com.cleanmaster.kinfoc.d)localObject1).a = new File(paramIntent);
          ((com.cleanmaster.kinfoc.d)localObject1).b = a(i);
          if ((((com.cleanmaster.kinfoc.d)localObject1).a.exists()) && (((com.cleanmaster.kinfoc.d)localObject1).a.length() > 0L)) {
            localObject2[(i + 1)] = localObject1;
          }
        }
        i += 1;
      }
      if (Environment.getExternalStorageDirectory() != null) {
        ((com.cleanmaster.kinfoc.d)localObject3).a = new File(com.keniu.security.a.f(), "/logs/0.log");
      }
    }
    b(new com.cleanmaster.feedback.a(k.a("http://fk.cm.ksmobile.com/CMFeedback", localHashMap, (com.cleanmaster.kinfoc.d[])localObject2), l));
  }
  
  public static void e(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_DUMP_USER_APPS");
    paramContext.startService(localIntent);
  }
  
  public static void e(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_PACKAGE_REPLACE");
    localIntent.putExtra(":package-name", paramString);
    paramContext.startService(localIntent);
  }
  
  private void e(Intent paramIntent)
  {
    long l = System.currentTimeMillis();
    Object localObject1 = paramIntent.getStringExtra(":content");
    Object localObject2 = paramIntent.getStringExtra(":contact");
    Object localObject3 = paramIntent.getStringExtra(":type");
    String[] arrayOfString = paramIntent.getStringArrayExtra(":uploadImagePaths");
    paramIntent = (FeedBackDataBean)paramIntent.getSerializableExtra(":feedback_data");
    LibcoreWrapper.a.a(this, null);
    HashMap localHashMap = new HashMap();
    localHashMap.put("content", localObject1);
    localHashMap.put("contact", localObject2);
    localHashMap.put("model", Build.MODEL);
    localHashMap.put("type", localObject3);
    localHashMap.put("sysversion", Build.VERSION.RELEASE);
    localHashMap.put("cmversion", d());
    localHashMap.put("uuid", LibcoreWrapper.a.p(this));
    localHashMap.put("syslang", Locale.getDefault().getLanguage());
    if (paramIntent != null)
    {
      localHashMap.put("follow_transfer_model", String.valueOf(paramIntent.internationid));
      if ((!paramIntent.isGameBoost) && (paramIntent.isMisFile))
      {
        localHashMap.put("middelreason", paramIntent.misFileReason);
        localHashMap.put("misdeltype", paramIntent.misFileType);
      }
    }
    localObject2 = new com.cleanmaster.kinfoc.d[4];
    localObject3 = new com.cleanmaster.kinfoc.d();
    paramIntent = null;
    if (com.cleanmaster.base.c.q() != null)
    {
      localObject1 = new File(com.cleanmaster.base.c.q());
      paramIntent = (Intent)localObject1;
      if (((File)localObject1).length() > 2097152L) {
        paramIntent = null;
      }
    }
    if ((paramIntent != null) && (paramIntent.exists())) {
      ((com.cleanmaster.kinfoc.d)localObject3).a = paramIntent;
    }
    for (;;)
    {
      ((com.cleanmaster.kinfoc.d)localObject3).b = "log";
      if ((((com.cleanmaster.kinfoc.d)localObject3).a.exists()) && (((com.cleanmaster.kinfoc.d)localObject3).a.length() > 0L)) {
        localObject2[0] = localObject3;
      }
      if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
        break;
      }
      int i = 0;
      while (i < arrayOfString.length)
      {
        paramIntent = arrayOfString[i];
        if (!TextUtils.isEmpty(paramIntent))
        {
          localObject1 = new com.cleanmaster.kinfoc.d();
          ((com.cleanmaster.kinfoc.d)localObject1).a = new File(paramIntent);
          ((com.cleanmaster.kinfoc.d)localObject1).b = a(i);
          if ((((com.cleanmaster.kinfoc.d)localObject1).a.exists()) && (((com.cleanmaster.kinfoc.d)localObject1).a.length() > 0L)) {
            localObject2[(i + 1)] = localObject1;
          }
        }
        i += 1;
      }
      if (Environment.getExternalStorageDirectory() != null) {
        ((com.cleanmaster.kinfoc.d)localObject3).a = new File(com.keniu.security.a.f(), "/logs/0.log");
      }
    }
    b(new com.cleanmaster.feedback.a(k.a("http://fk.cm.ksmobile.com/CMFeedback", localHashMap, (com.cleanmaster.kinfoc.d[])localObject2), l));
  }
  
  public static void f(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_UPLOAD_GAID");
    paramContext.startService(localIntent);
  }
  
  public static void f(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_PACKAGE_REMOVE");
    localIntent.putExtra(":package-name", paramString);
    paramContext.startService(localIntent);
  }
  
  private void f(Intent paramIntent)
  {
    a = false;
    Object localObject1 = paramIntent.getParcelableArrayListExtra(":uninstall-packages");
    Object localObject2 = paramIntent.getStringExtra(":tag");
    Iterator localIterator = ((ArrayList)localObject1).iterator();
    UninstallAppData localUninstallAppData;
    String str;
    StringBuilder localStringBuilder;
    boolean bool1;
    label216:
    int i;
    if (localIterator.hasNext())
    {
      localUninstallAppData = (UninstallAppData)localIterator.next();
      if (!a)
      {
        a(new com.cleanmaster.common.a.f(localUninstallAppData), (String)localObject2);
        if (localUninstallAppData.b == 0)
        {
          str = LocalService.class.getSimpleName();
          localStringBuilder = new StringBuilder("pm uninstall ").append(localUninstallAppData.a).append(", remain size: ").append(localUninstallAppData.i).append(", filelist size: ");
          if (localUninstallAppData.k != null) {}
          for (paramIntent = Integer.valueOf(localUninstallAppData.k.size());; paramIntent = "null")
          {
            OpLog.d(str, paramIntent);
            bool1 = com.cm.root.d.a().a("pm uninstall " + localUninstallAppData.a, f);
            OpLog.d(LocalService.class.getSimpleName(), "finish uninstall " + bool1);
            if (bool1) {
              com.cleanmaster.base.b.a().a(localUninstallAppData.g);
            }
            a(new com.cleanmaster.common.a.h(bool1, localUninstallAppData), (String)localObject2);
            if (localUninstallAppData.b != 1) {
              break label718;
            }
            i = 1;
            label257:
            if ((i == 0) || (!bool1)) {
              break label721;
            }
            p.a().a("cm_systemapps_uninstall", String.format("model=%s&pkgname=%s", new Object[] { com.cleanmaster.base.util.system.d.h(), localUninstallAppData.a }), true);
            UninstallBroadcastReceiver.a(this, localUninstallAppData.a, localUninstallAppData.e);
            break;
          }
        }
        OpLog.d(LocalService.class.getSimpleName(), "uninstall package:   " + localUninstallAppData.a);
        if (com.cleanmaster.base.f.c())
        {
          paramIntent = com.cm.root.d.a();
          str = com.cleanmaster.base.f.a("/system");
          if (str == null)
          {
            bool1 = false;
            OpLog.d(LocalService.class.getSimpleName(), "mountSystemRW:   " + bool1);
          }
        }
        else
        {
          bool1 = com.cm.root.d.a().a("pm uninstall " + localUninstallAppData.a, f);
          OpLog.d(LocalService.class.getSimpleName(), "isUninstall:   " + bool1);
          if (!localUninstallAppData.j) {}
        }
      }
    }
    for (;;)
    {
      try
      {
        localUninstallAppData.e = getPackageManager().getApplicationInfo(localUninstallAppData.a, 128).sourceDir;
        bool1 = com.cm.root.d.a().a("pm uninstall " + localUninstallAppData.a, f);
        OpLog.d(LocalService.class.getSimpleName(), "second systemupdated package isUninstall:   " + bool1);
        paramIntent = localUninstallAppData.e;
        if ((TextUtils.isEmpty(paramIntent)) || (paramIntent.length() <= 12)) {
          break label816;
        }
        bool2 = com.cm.root.d.a().i(paramIntent);
        OpLog.d(LocalService.class.getSimpleName(), "rm " + paramIntent + "           result:  " + bool2);
        if ((bool1) && (bool2))
        {
          bool1 = true;
          OpLog.d(LocalService.class.getSimpleName(), "isSuccessed:    " + bool1);
          break label216;
          localStringBuilder = new StringBuilder(com.cm.root.d.g("mount"));
          localStringBuilder.append(" -o remount,rw ");
          localStringBuilder.append(str);
          localStringBuilder.append(" /system\n");
          bool1 = paramIntent.b(localStringBuilder.toString());
        }
      }
      catch (PackageManager.NameNotFoundException paramIntent)
      {
        paramIntent.printStackTrace();
        continue;
        bool1 = false;
        continue;
      }
      label718:
      i = 0;
      break label257;
      label721:
      break;
      a = true;
      a(new com.cleanmaster.common.a.g((ArrayList)localObject1), (String)localObject2);
      if (!com.cleanmaster.base.f.c())
      {
        paramIntent = com.cm.root.d.a();
        localObject1 = com.cleanmaster.base.f.a("/system");
        if (localObject1 != null)
        {
          localObject2 = new StringBuilder(com.cm.root.d.g("mount"));
          ((StringBuilder)localObject2).append(" -o remount,ro ");
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(" /system\n");
          paramIntent.b(((StringBuilder)localObject2).toString());
        }
      }
      return;
      label816:
      boolean bool2 = false;
    }
  }
  
  public static void g(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_CMBOX_SETUP");
    a(paramContext, localIntent, new d(paramContext));
  }
  
  private static void g(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra(":type", paramString);
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_SCAN_UNUSED_RECOMMAND");
    paramContext.startService(localIntent);
  }
  
  private void g(Intent paramIntent)
  {
    if (!LibcoreWrapper.a.A("android.permission.WRITE_EXTERNAL_STORAGE")) {
      return;
    }
    Object localObject1 = paramIntent.getStringArrayListExtra(":packages");
    if (paramIntent.hasExtra(":meta_data")) {}
    for (paramIntent = paramIntent.getBundleExtra(":meta_data");; paramIntent = null)
    {
      System.out.println("report=" + paramIntent);
      int i = 0;
      TimeStamp localTimeStamp = new TimeStamp();
      localObject1 = ((ArrayList)localObject1).iterator();
      long l;
      if (((Iterator)localObject1).hasNext())
      {
        String str = (String)((Iterator)localObject1).next();
        a("app2sd moving : " + str);
        Object localObject2 = new com.cleanmaster.ui.app.a.f("move");
        ((com.cleanmaster.ui.app.a.f)localObject2).d = str;
        b((client.core.model.c)localObject2);
        localTimeStamp.b();
        localObject2 = new e.a()
        {
          public final void a(File paramAnonymousFile1, File paramAnonymousFile2)
          {
            if (i.e(i.c(LocalService.this))) {
              i.a(i.c(LocalService.this), LocalService.this, 3);
            }
            i.a();
            paramAnonymousFile1 = i.d(LocalService.this);
            if (paramAnonymousFile1 != null)
            {
              int j = paramAnonymousFile1.length;
              int i = 0;
              while (i < j)
              {
                String str = paramAnonymousFile1[i];
                if (i.e(new File(Environment.getExternalStorageDirectory(), str))) {
                  i.a(new File(Environment.getExternalStorageDirectory(), str), LocalService.this, 3);
                }
                i += 1;
              }
            }
            i.c(paramAnonymousFile2);
          }
          
          public final void a(String paramAnonymousString)
          {
            String str = paramAnonymousString;
            if (paramAnonymousString.contains(File.separator)) {
              str = paramAnonymousString.substring(paramAnonymousString.lastIndexOf(File.separator), paramAnonymousString.length() - 1);
            }
            LocalService.a(new r(str, "move"));
          }
        };
        Object localObject3 = i.a();
        File localFile = i.c(str);
        if (!i.a(localFile)) {}
        for (j = 6;; j = i.a(str, localFile, new File(((i)localObject3).b, str), (e.a)localObject2))
        {
          i.a();
          localObject3 = i.d(str);
          i = j;
          if (localObject3 == null) {
            break;
          }
          i = j;
          if (j == 5) {
            break;
          }
          i = j;
          if (j == 6) {
            break;
          }
          i = j;
          if (j == 7) {
            break;
          }
          int m = localObject3.length;
          int k = 0;
          for (i = j; k < m; i = j)
          {
            localFile = localObject3[k];
            i.a();
            j = i.a(str, new File(Environment.getExternalStorageDirectory(), localFile), new File(i.a().a, localFile), (e.a)localObject2);
            i = j;
            if (localObject3 == null) {
              break;
            }
            i = j;
            if (j == 5) {
              break;
            }
            i = j;
            if (j == 6) {
              break;
            }
            i = j;
            if (j == 7) {
              break;
            }
            k += 1;
          }
          i.a(str);
        }
        localObject2 = new l();
        ((client.core.model.c)localObject2).a = "move";
        ((l)localObject2).d = str;
        ((l)localObject2).e = i;
        b((client.core.model.c)localObject2);
        if (paramIntent != null)
        {
          l = paramIntent.getLong(str);
          label409:
          j = localTimeStamp.a();
          p.a().a("cm_app2sd_info", "pn=" + ((l)localObject2).d + "&an=" + com.cleanmaster.base.util.system.m.q(this, ((l)localObject2).d) + "&stat=" + String.valueOf(((l)localObject2).e) + "&op=0&size=" + l + "&ot=" + j, true);
          j = i;
          if (i != 5)
          {
            j = i;
            if (i != 6) {
              if (i != 7) {
                break label551;
              }
            }
          }
        }
      }
      for (int j = i;; j = i)
      {
        if (j == 6) {
          break label557;
        }
        b(new q("move"));
        return;
        l = 0L;
        break label409;
        label551:
        break;
      }
      label557:
      break;
    }
  }
  
  public static void h(Context paramContext)
  {
    if (b.a("com.cleanmaster.service.ACTION_SELF_SPACE_MONITOR", b.a(1)))
    {
      Intent localIntent = new Intent();
      localIntent.setClass(paramContext, LocalService.class);
      localIntent.setAction("com.cleanmaster.service.ACTION_SELF_SPACE_MONITOR");
      paramContext.startService(localIntent);
    }
  }
  
  private void h(Intent paramIntent)
  {
    if (!LibcoreWrapper.a.A("android.permission.WRITE_EXTERNAL_STORAGE")) {
      return;
    }
    Object localObject1 = paramIntent.getStringArrayListExtra(":packages");
    int j = 0;
    TimeStamp localTimeStamp = new TimeStamp();
    if (paramIntent.hasExtra(":meta_data")) {}
    for (paramIntent = paramIntent.getBundleExtra(":meta_data");; paramIntent = null)
    {
      localObject1 = ((ArrayList)localObject1).iterator();
      label55:
      String str;
      Object localObject2;
      int i;
      if (((Iterator)localObject1).hasNext())
      {
        str = (String)((Iterator)localObject1).next();
        localTimeStamp.b();
        localObject2 = new e.a()
        {
          public final void a(File paramAnonymousFile1, File paramAnonymousFile2)
          {
            i.d(paramAnonymousFile2);
            i.a(paramAnonymousFile2, paramAnonymousFile1);
          }
          
          public final void a(String paramAnonymousString)
          {
            String str = paramAnonymousString;
            if (paramAnonymousString.contains(File.separator)) {
              str = paramAnonymousString.substring(paramAnonymousString.lastIndexOf(File.separator), paramAnonymousString.length() - 1);
            }
            LocalService.a(new r(str, "restore"));
          }
        };
        Object localObject3 = i.a();
        File localFile = i.c(str);
        if (!i.b(localFile)) {}
        for (j = 6;; j = i.a(localFile, new File(((i)localObject3).b, str), str, (e.a)localObject2))
        {
          i.a();
          localObject3 = i.d(str);
          i = j;
          if (localObject3 == null) {
            break;
          }
          i = j;
          if (!i.d.containsKey(str)) {
            break;
          }
          i = j;
          if (j == 5) {
            break;
          }
          i = j;
          if (j == 6) {
            break;
          }
          i = j;
          if (j == 7) {
            break;
          }
          int m = localObject3.length;
          int k = 0;
          for (i = j; k < m; i = j)
          {
            localFile = localObject3[k];
            i.a();
            j = i.a(new File(Environment.getExternalStorageDirectory(), localFile), new File(i.a().a, localFile), str, (e.a)localObject2);
            i = j;
            if (localObject3 == null) {
              break;
            }
            i = j;
            if (j == 5) {
              break;
            }
            i = j;
            if (j == 6) {
              break;
            }
            i = j;
            if (j == 7) {
              break;
            }
            k += 1;
          }
          i.a(str);
        }
        localObject2 = new l();
        ((client.core.model.c)localObject2).a = "restore";
        ((l)localObject2).d = str;
        ((l)localObject2).e = i;
        b((client.core.model.c)localObject2);
        if (paramIntent == null) {
          break label490;
        }
      }
      label490:
      for (long l = paramIntent.getLong(str);; l = 0L)
      {
        j = localTimeStamp.a();
        p.a().a("cm_app2sd_info", "pn=" + ((l)localObject2).d + "&an=" + com.cleanmaster.base.util.system.m.q(this, ((l)localObject2).d) + "&stat=" + String.valueOf(((l)localObject2).e) + "&op=1&size=" + l + "&ot=" + j, true);
        j = i;
        if (i != 5)
        {
          j = i;
          if (i != 6)
          {
            j = i;
            if (i != 7) {
              break label55;
            }
            j = i;
          }
        }
        if (j == 6) {
          break;
        }
        b(new q("restore"));
        return;
      }
    }
  }
  
  public static void i(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_ad_doubleclick");
    paramContext.startService(localIntent);
  }
  
  private void i(Intent paramIntent)
  {
    Object localObject2 = null;
    if (paramIntent.hasExtra(":key-search")) {}
    for (Object localObject1 = paramIntent.getStringArrayListExtra(":key-search");; localObject1 = null)
    {
      if (paramIntent.hasExtra(":key-tag")) {}
      label335:
      for (paramIntent = paramIntent.getStringExtra(":key-tag");; paramIntent = null)
      {
        if (localObject1 != null) {}
        for (localObject1 = new com.cleanmaster.data.filter.a(new b((Collection)localObject1), this.h);; localObject1 = this.h)
        {
          Object localObject3 = getPackageManager();
          try
          {
            localObject3 = ((PackageManager)localObject3).getInstalledPackages(0);
            localObject2 = localObject3;
          }
          catch (Exception localException)
          {
            com.cleanmaster.common.a.a localA;
            for (;;)
            {
              localException.printStackTrace();
              continue;
              int i = 0;
            }
            b(localA);
            this.d = 0;
            a(localA.d);
            c = false;
            return;
          }
          if (TextUtils.isEmpty(paramIntent))
          {
            if (localObject2 == null) {
              break;
            }
            i = ((List)localObject2).size();
            b(new com.cleanmaster.common.a.e(i, true));
          }
          if (this.b == null) {
            this.b = getPackageManager();
          }
          localObject3 = new com.cleanmaster.bitloader.a.b();
          localA = new com.cleanmaster.common.a.a();
          localA.f = paramIntent;
          if (localObject2 == null) {
            break label335;
          }
          localObject2 = ((List)localObject2).iterator();
          i = 0;
          while (((Iterator)localObject2).hasNext())
          {
            PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
            if ((!c) && (!getPackageName().equals(localPackageInfo.packageName)))
            {
              if ((TextUtils.isEmpty(paramIntent)) && (i % 7 == 0)) {
                b(new com.cleanmaster.common.a.d(localPackageInfo.applicationInfo.loadLabel(getPackageManager()).toString()));
              }
              if ((((IFilter)localObject1).a(localPackageInfo)) && (!((com.cleanmaster.bitloader.a.b)localObject3).contains(localPackageInfo.packageName)))
              {
                ((com.cleanmaster.bitloader.a.b)localObject3).add(localPackageInfo.packageName);
                localA.a(com.cleanmaster.common.model.a.a(getApplicationContext(), this.b, localPackageInfo));
              }
              i += 1;
            }
          }
        }
      }
    }
  }
  
  public static void j(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, LocalService.class);
    localIntent.setAction("com.cleanmaster.service.ACTION_PRELOAD_PACKAGE_INFO");
    paramContext.startService(localIntent);
  }
  
  private void j(Intent paramIntent)
  {
    String str = null;
    if (paramIntent.hasExtra(":key-search")) {}
    for (Object localObject1 = paramIntent.getStringArrayListExtra(":key-search");; localObject1 = null)
    {
      if (paramIntent.hasExtra(":key-tag")) {
        str = paramIntent.getStringExtra(":key-tag");
      }
      paramIntent = new com.cleanmaster.data.filter.a(new h(), new a());
      if (localObject1 != null) {
        paramIntent = new com.cleanmaster.data.filter.a(paramIntent, new b((Collection)localObject1));
      }
      for (;;)
      {
        Object localObject2 = com.cleanmaster.func.cache.e.a().b.a();
        if (TextUtils.isEmpty(str)) {
          b(new com.cleanmaster.common.a.e(((List)localObject2).size(), false));
        }
        if (this.b == null) {
          this.b = getPackageManager();
        }
        localObject1 = new com.cleanmaster.bitloader.a.b();
        com.cleanmaster.common.a.a localA = new com.cleanmaster.common.a.a();
        localA.f = str;
        localObject2 = ((List)localObject2).iterator();
        int i = 0;
        while (((Iterator)localObject2).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
          if ((!c) && (!getPackageName().equals(localPackageInfo.packageName)))
          {
            if ((TextUtils.isEmpty(str)) && (i % 7 == 0)) {
              b(new com.cleanmaster.common.a.d(localPackageInfo.applicationInfo.loadLabel(getPackageManager()).toString()));
            }
            if ((paramIntent.a(localPackageInfo)) && (!((com.cleanmaster.bitloader.a.b)localObject1).contains(localPackageInfo.packageName)))
            {
              ((com.cleanmaster.bitloader.a.b)localObject1).add(localPackageInfo.packageName);
              localA.a(com.cleanmaster.common.model.b.b(getApplicationContext(), this.b, localPackageInfo));
            }
            i += 1;
          }
        }
        localA.e = i.a().b(this);
        this.d = 0;
        a(localA.d);
        b(localA);
        c = false;
        return;
      }
    }
  }
  
  private void k(Intent paramIntent)
  {
    int j = 0;
    Object localObject1 = com.keniu.security.d.a();
    if (!com.cleanmaster.base.util.net.b.j((Context)localObject1)) {}
    do
    {
      ScanUnknownFilesModel localScanUnknownFilesModel;
      int i;
      do
      {
        Object localObject2;
        Object localObject3;
        do
        {
          return;
          localScanUnknownFilesModel = (ScanUnknownFilesModel)paramIntent.getParcelableExtra(":unknown_files");
          Object localObject5 = localScanUnknownFilesModel.a;
          int k = ((List)localObject5).size();
          localObject2 = new AntiVirusFunc();
          localObject3 = new String[k];
          Object localObject4 = new String[k];
          paramIntent = new IApkResult[k];
          i = 0;
          Object localObject6;
          while (i < k)
          {
            localObject6 = (IApkResult)((List)localObject5).get(i);
            new StringBuilder("apk.pkg=").append(((IApkResult)localObject6).e());
            paramIntent[i] = localObject6;
            localObject3[i] = ((IApkResult)localObject6).g();
            localObject4[i] = ((AntiVirusFunc)localObject2).a(((IApkResult)localObject6).g());
            i += 1;
          }
          localObject5 = LibcoreWrapper.a.M((Context)localObject1);
          new c.a()
          {
            public final boolean a()
            {
              return true;
            }
          };
          localObject3 = ResultSampleReport.a((String[])localObject3, (String[])localObject4, (AntiVirusFunc)localObject2, (String)localObject5, 1);
          i = j;
          if (localObject3 == null) {
            break;
          }
          localObject4 = new ArrayList();
          localObject2 = new ArrayList();
          localObject5 = new ArrayList();
          i = 0;
          while (i < k)
          {
            localObject6 = (ResultSampleReport.a)((List)localObject3).get(i);
            IApkResult localIApkResult = paramIntent[i];
            if (((ResultSampleReport.a)localObject6).a == 1)
            {
              localObject6 = new ResultSampleReport.b(localIApkResult, ((ResultSampleReport.a)localObject6).b);
              ((List)localObject2).add(((ResultSampleReport.b)localObject6).a);
              ((List)localObject4).add(((ResultSampleReport.b)localObject6).a.g());
              ((List)localObject5).add(((ResultSampleReport.b)localObject6).b);
            }
            i += 1;
          }
          i = j;
          if (((List)localObject4).size() <= 0) {
            break;
          }
          paramIntent = ResultSampleReport.a((Context)localObject1, (List)localObject4, (List)localObject5, 1);
        } while (paramIntent == null);
        localObject1 = new ArrayList();
        i = 0;
        while (i < paramIntent.length)
        {
          if (paramIntent[i] == 0) {
            ((ArrayList)localObject1).add((ApkResultImpl)((List)localObject2).get(i));
          }
          i += 1;
        }
        i = j;
        if (((ArrayList)localObject1).size() > 0)
        {
          paramIntent = null;
          if (localScanUnknownFilesModel.o() > 0) {
            paramIntent = b(localScanUnknownFilesModel.b);
          }
          localObject2 = com.cleanmaster.security.scan.o.a();
          if (!((List)localObject1).isEmpty())
          {
            localObject3 = new Timer();
            ((Timer)localObject3).schedule(new o.4((com.cleanmaster.security.scan.o)localObject2, (List)localObject1, (Timer)localObject3, paramIntent), 300000L);
          }
          i = 1;
        }
      } while (i != 0);
      com.cleanmaster.security.scan.o.a();
      paramIntent = b(localScanUnknownFilesModel.b);
    } while ((paramIntent == null) || (paramIntent.size() <= 0));
    new Timer().schedule(new o.3(paramIntent), 600000L);
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.h = new com.cleanmaster.data.filter.a(this.g, new c(getApplicationContext()));
    new com.cleanmaster.data.filter.a(this.g, new c(getApplicationContext()));
  }
  
  /* Error */
  protected void onHandleIntent(Intent paramIntent)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: iconst_0
    //   4: istore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: aload_1
    //   8: ifnonnull +4 -> 12
    //   11: return
    //   12: aload_1
    //   13: invokevirtual 1361	android/content/Intent:getAction	()Ljava/lang/String;
    //   16: astore 12
    //   18: ldc_w 1100
    //   21: aload 12
    //   23: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   26: ifeq +178 -> 204
    //   29: ldc_w 1363
    //   32: invokestatic 1133	com/cleanmaster/service/LocalService:a	(Ljava/lang/String;)V
    //   35: invokestatic 1146	com/cleanmaster/util/i:a	()Lcom/cleanmaster/util/i;
    //   38: invokevirtual 1366	com/cleanmaster/util/i:d	()Ljava/util/ArrayList;
    //   41: invokevirtual 138	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   44: astore_1
    //   45: aload_1
    //   46: invokeinterface 144 1 0
    //   51: ifeq +134 -> 185
    //   54: aload_1
    //   55: invokeinterface 148 1 0
    //   60: checkcast 1368	com/cleanmaster/common/model/c
    //   63: astore 11
    //   65: aload_0
    //   66: aload 11
    //   68: getfield 1370	com/cleanmaster/common/model/c:c	Ljava/lang/String;
    //   71: invokestatic 1373	com/cleanmaster/base/util/system/m:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   74: ifne +30 -> 104
    //   77: new 239	java/lang/StringBuilder
    //   80: dup
    //   81: ldc_w 1375
    //   84: invokespecial 242	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   87: aload 11
    //   89: getfield 1370	com/cleanmaster/common/model/c:c	Ljava/lang/String;
    //   92: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: invokestatic 1133	com/cleanmaster/service/LocalService:a	(Ljava/lang/String;)V
    //   101: goto -56 -> 45
    //   104: invokestatic 1146	com/cleanmaster/util/i:a	()Lcom/cleanmaster/util/i;
    //   107: pop
    //   108: aload 11
    //   110: getfield 1370	com/cleanmaster/common/model/c:c	Ljava/lang/String;
    //   113: invokestatic 1161	com/cleanmaster/util/i:a	(Ljava/lang/String;)V
    //   116: aload 11
    //   118: getfield 1376	com/cleanmaster/common/model/c:a	Ljava/io/File;
    //   121: aload 11
    //   123: getfield 1370	com/cleanmaster/common/model/c:c	Ljava/lang/String;
    //   126: iconst_3
    //   127: invokestatic 1379	com/cleanmaster/util/i:a	(Ljava/io/File;Ljava/lang/String;I)Z
    //   130: pop
    //   131: aload 11
    //   133: getfield 1376	com/cleanmaster/common/model/c:a	Ljava/io/File;
    //   136: aload 11
    //   138: getfield 1380	com/cleanmaster/common/model/c:b	Ljava/io/File;
    //   141: invokestatic 1383	com/cleanmaster/util/i:a	(Ljava/io/File;Ljava/io/File;)Z
    //   144: pop
    //   145: new 1385	com/cleanmaster/common/a/p
    //   148: dup
    //   149: invokespecial 1386	com/cleanmaster/common/a/p:<init>	()V
    //   152: astore 12
    //   154: aload 12
    //   156: aload 11
    //   158: getfield 1376	com/cleanmaster/common/model/c:a	Ljava/io/File;
    //   161: invokestatic 1387	com/cleanmaster/service/LocalService:b	(Ljava/io/File;)Z
    //   164: putfield 1389	com/cleanmaster/common/a/p:d	Z
    //   167: aload 12
    //   169: aload 11
    //   171: getfield 1370	com/cleanmaster/common/model/c:c	Ljava/lang/String;
    //   174: putfield 1390	com/cleanmaster/common/a/p:e	Ljava/lang/String;
    //   177: aload 12
    //   179: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   182: goto -137 -> 45
    //   185: new 1392	com/cleanmaster/common/a/o
    //   188: dup
    //   189: invokespecial 1393	com/cleanmaster/common/a/o:<init>	()V
    //   192: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   195: invokestatic 1146	com/cleanmaster/util/i:a	()Lcom/cleanmaster/util/i;
    //   198: pop
    //   199: aload_0
    //   200: invokestatic 1395	com/cleanmaster/util/i:f	(Landroid/content/Context;)V
    //   203: return
    //   204: ldc_w 718
    //   207: aload 12
    //   209: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   212: ifeq +9 -> 221
    //   215: aload_0
    //   216: aload_1
    //   217: invokespecial 1397	com/cleanmaster/service/LocalService:g	(Landroid/content/Intent;)V
    //   220: return
    //   221: ldc_w 1399
    //   224: aload 12
    //   226: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   229: ifne -218 -> 11
    //   232: ldc_w 336
    //   235: aload 12
    //   237: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   240: ifeq +9 -> 249
    //   243: aload_0
    //   244: aload_1
    //   245: invokespecial 1401	com/cleanmaster/service/LocalService:h	(Landroid/content/Intent;)V
    //   248: return
    //   249: ldc_w 720
    //   252: aload 12
    //   254: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   257: ifeq +9 -> 266
    //   260: aload_0
    //   261: aload_1
    //   262: invokespecial 1403	com/cleanmaster/service/LocalService:f	(Landroid/content/Intent;)V
    //   265: return
    //   266: ldc_w 1405
    //   269: aload 12
    //   271: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   274: ifeq +369 -> 643
    //   277: aload_1
    //   278: ldc_w 942
    //   281: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   284: astore_1
    //   285: aload_1
    //   286: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   289: ifne -278 -> 11
    //   292: invokestatic 1408	com/cleanmaster/base/c:x	()Z
    //   295: istore 5
    //   297: aload_0
    //   298: aload_1
    //   299: invokestatic 1411	com/cleanmaster/base/util/system/m:e	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/pm/ApplicationInfo;
    //   302: astore 11
    //   304: aload 11
    //   306: ifnull -295 -> 11
    //   309: aload_1
    //   310: aload_0
    //   311: invokevirtual 678	com/cleanmaster/service/LocalService:getPackageName	()Ljava/lang/String;
    //   314: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   317: ifne -306 -> 11
    //   320: aload_0
    //   321: aload 11
    //   323: invokestatic 1414	com/cleanmaster/base/c:a	(Landroid/content/Context;Landroid/content/pm/ApplicationInfo;)Z
    //   326: istore 6
    //   328: aload_0
    //   329: invokevirtual 1268	com/cleanmaster/service/LocalService:getApplicationContext	()Landroid/content/Context;
    //   332: aload_1
    //   333: invokestatic 1416	com/cleanmaster/base/util/system/m:f	(Landroid/content/Context;Ljava/lang/String;)Z
    //   336: istore 7
    //   338: aload_0
    //   339: invokestatic 166	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   342: invokevirtual 1419	com/cleanmaster/configmanager/d:z	()Z
    //   345: istore 8
    //   347: iload 5
    //   349: ifeq -338 -> 11
    //   352: iload 6
    //   354: ifeq -343 -> 11
    //   357: iload 7
    //   359: ifne -348 -> 11
    //   362: getstatic 1423	com/cleanmaster/security/scan/MonitorInstallActivity:h	Z
    //   365: ifne -354 -> 11
    //   368: iload 8
    //   370: ifeq -359 -> 11
    //   373: new 1425	com/cleanmaster/dao/InstallMoveInfo
    //   376: dup
    //   377: invokespecial 1426	com/cleanmaster/dao/InstallMoveInfo:<init>	()V
    //   380: astore 11
    //   382: aload 11
    //   384: aload_0
    //   385: aload_1
    //   386: invokestatic 1187	com/cleanmaster/base/util/system/m:q	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   389: putfield 1427	com/cleanmaster/dao/InstallMoveInfo:f	Ljava/lang/String;
    //   392: aload 11
    //   394: aload_1
    //   395: putfield 1428	com/cleanmaster/dao/InstallMoveInfo:d	Ljava/lang/String;
    //   398: aload 11
    //   400: aload_0
    //   401: ldc_w 1430
    //   404: aload_1
    //   405: invokestatic 1435	com/cleanmaster/base/util/system/b:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    //   408: putfield 1436	com/cleanmaster/dao/InstallMoveInfo:a	Z
    //   411: aload 11
    //   413: aload_0
    //   414: ldc_w 1438
    //   417: aload_1
    //   418: invokestatic 1435	com/cleanmaster/base/util/system/b:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    //   421: putfield 1439	com/cleanmaster/dao/InstallMoveInfo:c	Z
    //   424: aload 11
    //   426: aload_0
    //   427: ldc_w 1441
    //   430: aload_1
    //   431: invokestatic 1442	com/cleanmaster/base/util/system/m:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    //   434: putfield 1444	com/cleanmaster/dao/InstallMoveInfo:b	Z
    //   437: invokestatic 1445	com/cleanmaster/base/c:N	()Z
    //   440: ifeq +68 -> 508
    //   443: aload_0
    //   444: invokevirtual 763	com/cleanmaster/service/LocalService:getPackageManager	()Landroid/content/pm/PackageManager;
    //   447: invokevirtual 1449	java/lang/Object:getClass	()Ljava/lang/Class;
    //   450: ldc_w 1451
    //   453: iconst_2
    //   454: anewarray 68	java/lang/Class
    //   457: dup
    //   458: iconst_0
    //   459: ldc_w 470
    //   462: aastore
    //   463: dup
    //   464: iconst_1
    //   465: ldc_w 1453
    //   468: aastore
    //   469: invokevirtual 1457	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   472: aload_0
    //   473: invokevirtual 763	com/cleanmaster/service/LocalService:getPackageManager	()Landroid/content/pm/PackageManager;
    //   476: iconst_2
    //   477: anewarray 1024	java/lang/Object
    //   480: dup
    //   481: iconst_0
    //   482: aload_1
    //   483: aastore
    //   484: dup
    //   485: iconst_1
    //   486: new 46	com/cleanmaster/service/LocalService$e
    //   489: dup
    //   490: aload_0
    //   491: aload 11
    //   493: invokespecial 1460	com/cleanmaster/service/LocalService$e:<init>	(Lcom/cleanmaster/service/LocalService;Lcom/cleanmaster/dao/InstallMoveInfo;)V
    //   496: aastore
    //   497: invokevirtual 1466	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   500: pop
    //   501: return
    //   502: astore_1
    //   503: aload_1
    //   504: invokevirtual 1272	java/lang/Exception:printStackTrace	()V
    //   507: return
    //   508: ldc_w 1468
    //   511: ldc_w 1470
    //   514: iconst_1
    //   515: invokestatic 1473	LibcoreWrapper/a:c	(Ljava/lang/String;Ljava/lang/String;I)I
    //   518: istore_2
    //   519: ldc_w 1468
    //   522: ldc_w 1475
    //   525: iconst_5
    //   526: invokestatic 1473	LibcoreWrapper/a:c	(Ljava/lang/String;Ljava/lang/String;I)I
    //   529: istore_3
    //   530: aload_0
    //   531: invokestatic 1477	com/cleanmaster/base/c:k	(Landroid/content/Context;)I
    //   534: istore 4
    //   536: iload 4
    //   538: iload_3
    //   539: if_icmplt -528 -> 11
    //   542: aload_0
    //   543: invokestatic 166	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   546: iload_2
    //   547: ldc_w 1479
    //   550: invokevirtual 1482	com/cleanmaster/configmanager/d:a	(ILjava/lang/String;)Z
    //   553: ifeq -542 -> 11
    //   556: aload_0
    //   557: invokestatic 166	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   560: ldc_w 1479
    //   563: invokevirtual 1485	com/cleanmaster/configmanager/d:l	(Ljava/lang/String;)V
    //   566: aload_0
    //   567: aload_0
    //   568: ldc_w 1486
    //   571: iconst_1
    //   572: anewarray 1024	java/lang/Object
    //   575: dup
    //   576: iconst_0
    //   577: iload 4
    //   579: invokestatic 990	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   582: aastore
    //   583: invokevirtual 1490	com/cleanmaster/service/LocalService:getString	(I[Ljava/lang/Object;)Ljava/lang/String;
    //   586: aload_0
    //   587: ldc_w 1491
    //   590: invokevirtual 1493	com/cleanmaster/service/LocalService:getString	(I)Ljava/lang/String;
    //   593: invokestatic 1494	com/cleanmaster/base/c:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   596: invokestatic 475	com/cleanmaster/kinfoc/p:a	()Lcom/cleanmaster/kinfoc/p;
    //   599: ldc_w 1496
    //   602: new 239	java/lang/StringBuilder
    //   605: dup
    //   606: ldc_w 1498
    //   609: invokespecial 242	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   612: iload 4
    //   614: invokevirtual 250	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   617: ldc_w 1500
    //   620: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: invokestatic 1503	LibcoreWrapper/a:aS	()I
    //   626: invokevirtual 250	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   629: ldc_w 1505
    //   632: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   635: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   638: iconst_1
    //   639: invokevirtual 490	com/cleanmaster/kinfoc/p:a	(Ljava/lang/String;Ljava/lang/String;Z)V
    //   642: return
    //   643: ldc_w 712
    //   646: aload 12
    //   648: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   651: ifeq +9 -> 660
    //   654: aload_0
    //   655: aload_1
    //   656: invokespecial 1507	com/cleanmaster/service/LocalService:j	(Landroid/content/Intent;)V
    //   659: return
    //   660: ldc_w 349
    //   663: aload 12
    //   665: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   668: ifeq +9 -> 677
    //   671: aload_0
    //   672: aload_1
    //   673: invokespecial 1509	com/cleanmaster/service/LocalService:i	(Landroid/content/Intent;)V
    //   676: return
    //   677: ldc_w 940
    //   680: aload 12
    //   682: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   685: ifeq +368 -> 1053
    //   688: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   691: invokestatic 166	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   694: ldc_w 590
    //   697: iconst_1
    //   698: invokevirtual 1512	com/cleanmaster/configmanager/d:b	(Ljava/lang/String;Z)V
    //   701: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   704: ldc_w 1514
    //   707: iconst_0
    //   708: invokevirtual 411	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   711: invokeinterface 421 1 0
    //   716: ldc_w 1516
    //   719: iconst_1
    //   720: invokeinterface 1520 3 0
    //   725: invokeinterface 430 1 0
    //   730: pop
    //   731: aload_1
    //   732: ldc_w 942
    //   735: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   738: astore 11
    //   740: new 177	android/content/Intent
    //   743: dup
    //   744: invokespecial 178	android/content/Intent:<init>	()V
    //   747: astore 12
    //   749: aload 12
    //   751: aload_0
    //   752: ldc 2
    //   754: invokevirtual 188	android/content/Intent:setClass	(Landroid/content/Context;Ljava/lang/Class;)Landroid/content/Intent;
    //   757: pop
    //   758: aload 12
    //   760: ldc_w 1405
    //   763: invokevirtual 194	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   766: pop
    //   767: aload 12
    //   769: ldc_w 942
    //   772: aload 11
    //   774: invokevirtual 283	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   777: pop
    //   778: aload_0
    //   779: aload 12
    //   781: invokevirtual 200	android/content/Context:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   784: pop
    //   785: new 134	java/util/ArrayList
    //   788: dup
    //   789: invokespecial 519	java/util/ArrayList:<init>	()V
    //   792: astore 12
    //   794: aload_1
    //   795: ldc_w 942
    //   798: invokevirtual 1112	android/content/Intent:hasExtra	(Ljava/lang/String;)Z
    //   801: ifeq +46 -> 847
    //   804: aload_1
    //   805: ldc_w 942
    //   808: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   811: astore_1
    //   812: aload_1
    //   813: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   816: ifne +31 -> 847
    //   819: aload 12
    //   821: aload_1
    //   822: invokevirtual 383	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   825: pop
    //   826: getstatic 1523	com/cleanmaster/ui/app/activity/b:j	Z
    //   829: ifeq +18 -> 847
    //   832: invokestatic 1408	com/cleanmaster/base/c:x	()Z
    //   835: ifeq +207 -> 1042
    //   838: aload_0
    //   839: aload 12
    //   841: ldc_w 1525
    //   844: invokestatic 385	com/cleanmaster/service/LocalService:a	(Landroid/content/Context;Ljava/util/ArrayList;Ljava/lang/String;)V
    //   847: invokestatic 1528	com/cleanmaster/base/util/system/o:g	()Z
    //   850: ifeq +30 -> 880
    //   853: aload_0
    //   854: invokevirtual 655	com/cleanmaster/service/LocalService:getBaseContext	()Landroid/content/Context;
    //   857: aload 11
    //   859: invokestatic 1187	com/cleanmaster/base/util/system/m:q	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   862: astore_1
    //   863: aload_1
    //   864: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   867: ifne +13 -> 880
    //   870: invokestatic 684	com/cleanmaster/service/b:a	()Lcom/cleanmaster/service/b;
    //   873: pop
    //   874: aload 11
    //   876: aload_1
    //   877: invokestatic 1530	com/cleanmaster/service/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   880: new 10	com/cleanmaster/service/LocalService$11
    //   883: dup
    //   884: aload_0
    //   885: invokevirtual 1268	com/cleanmaster/service/LocalService:getApplicationContext	()Landroid/content/Context;
    //   888: aload 11
    //   890: invokespecial 1532	com/cleanmaster/service/LocalService$11:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   893: astore_1
    //   894: aload_1
    //   895: iconst_1
    //   896: putfield 1536	com/cleanmaster/ui/app/task/b:f	Z
    //   899: aload_1
    //   900: iconst_0
    //   901: putfield 1537	com/cleanmaster/ui/app/task/b:d	Z
    //   904: aload_1
    //   905: iconst_1
    //   906: putfield 1539	com/cleanmaster/ui/app/task/b:e	Z
    //   909: aload_1
    //   910: iconst_0
    //   911: anewarray 1541	java/lang/Void
    //   914: invokevirtual 1544	com/cleanmaster/ui/app/task/b:c	([Ljava/lang/Object;)Lcom/cleanmaster/ui/app/AsyncTaskEx;
    //   917: pop
    //   918: new 1546	com/cleanmaster/common/a/i
    //   921: dup
    //   922: aload 11
    //   924: invokespecial 1547	com/cleanmaster/common/a/i:<init>	(Ljava/lang/String;)V
    //   927: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   930: aload 11
    //   932: invokestatic 1551	com/cleanmaster/internalapp/ad/control/f:a	(Ljava/lang/String;)Z
    //   935: ifeq +26 -> 961
    //   938: invokestatic 684	com/cleanmaster/service/b:a	()Lcom/cleanmaster/service/b;
    //   941: pop
    //   942: invokestatic 1552	com/cleanmaster/service/b:f	()V
    //   945: invokestatic 684	com/cleanmaster/service/b:a	()Lcom/cleanmaster/service/b;
    //   948: pop
    //   949: iconst_0
    //   950: invokestatic 1555	com/cleanmaster/service/b:a	(Z)V
    //   953: invokestatic 684	com/cleanmaster/service/b:a	()Lcom/cleanmaster/service/b;
    //   956: pop
    //   957: iconst_1
    //   958: invokestatic 1557	com/cleanmaster/service/b:b	(Z)V
    //   961: aload 11
    //   963: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   966: ifne -955 -> 11
    //   969: invokestatic 1562	com/cleanmaster/func/b/d:a	()Lcom/cleanmaster/func/b/d;
    //   972: getfield 1565	com/cleanmaster/func/b/d:a	Lcom/cleanmaster/func/b/b;
    //   975: astore_1
    //   976: iload_3
    //   977: istore_2
    //   978: aload 11
    //   980: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   983: ifne +25 -> 1008
    //   986: iload_3
    //   987: istore_2
    //   988: aload_1
    //   989: invokevirtual 1570	com/cleanmaster/func/b/b:a	()Landroid/content/SharedPreferences;
    //   992: aload 11
    //   994: aconst_null
    //   995: invokeinterface 1573 3 0
    //   1000: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1003: ifne +5 -> 1008
    //   1006: iconst_1
    //   1007: istore_2
    //   1008: iload_2
    //   1009: ifeq -998 -> 11
    //   1012: aload_0
    //   1013: invokevirtual 1268	com/cleanmaster/service/LocalService:getApplicationContext	()Landroid/content/Context;
    //   1016: invokestatic 166	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   1019: new 239	java/lang/StringBuilder
    //   1022: dup
    //   1023: ldc_w 1575
    //   1026: invokespecial 242	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1029: aload 11
    //   1031: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1034: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1037: iconst_1
    //   1038: invokevirtual 1512	com/cleanmaster/configmanager/d:b	(Ljava/lang/String;Z)V
    //   1041: return
    //   1042: invokestatic 1146	com/cleanmaster/util/i:a	()Lcom/cleanmaster/util/i;
    //   1045: pop
    //   1046: invokestatic 1577	com/cleanmaster/util/i:f	()Z
    //   1049: pop
    //   1050: goto -203 -> 847
    //   1053: ldc_w 948
    //   1056: aload 12
    //   1058: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1061: ifeq +37 -> 1098
    //   1064: aload_1
    //   1065: ldc_w 942
    //   1068: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   1071: astore_1
    //   1072: invokestatic 1581	com/cleanmaster/ui/app/market/a/a:b	()Z
    //   1075: ifeq +11 -> 1086
    //   1078: invokestatic 1586	com/cleanmaster/ui/app/market/storage/MarketStorage:a	()Lcom/cleanmaster/ui/app/market/storage/MarketStorage;
    //   1081: aload_1
    //   1082: invokevirtual 1588	com/cleanmaster/ui/app/market/storage/MarketStorage:k	(Ljava/lang/String;)Z
    //   1085: pop
    //   1086: new 1590	com/cleanmaster/common/a/t
    //   1089: dup
    //   1090: aload_1
    //   1091: invokespecial 1591	com/cleanmaster/common/a/t:<init>	(Ljava/lang/String;)V
    //   1094: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   1097: return
    //   1098: ldc_w 955
    //   1101: aload 12
    //   1103: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1106: ifeq +508 -> 1614
    //   1109: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   1112: invokevirtual 1592	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1115: astore 13
    //   1117: new 134	java/util/ArrayList
    //   1120: dup
    //   1121: invokespecial 519	java/util/ArrayList:<init>	()V
    //   1124: astore 11
    //   1126: aload_1
    //   1127: ldc_w 942
    //   1130: invokevirtual 1112	android/content/Intent:hasExtra	(Ljava/lang/String;)Z
    //   1133: ifeq +46 -> 1179
    //   1136: aload_1
    //   1137: ldc_w 942
    //   1140: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   1143: astore 12
    //   1145: aload 12
    //   1147: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1150: ifne +29 -> 1179
    //   1153: aload 11
    //   1155: aload 12
    //   1157: invokevirtual 383	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1160: pop
    //   1161: getstatic 1523	com/cleanmaster/ui/app/activity/b:j	Z
    //   1164: ifeq +15 -> 1179
    //   1167: new 1594	com/cleanmaster/common/a/c
    //   1170: dup
    //   1171: aload 12
    //   1173: invokespecial 1595	com/cleanmaster/common/a/c:<init>	(Ljava/lang/String;)V
    //   1176: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   1179: aload_1
    //   1180: ldc_w 942
    //   1183: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   1186: astore 12
    //   1188: aload 12
    //   1190: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1193: ifne -1182 -> 11
    //   1196: aload 12
    //   1198: invokestatic 1600	com/cleanmaster/common_transition/report/c:a	(Ljava/lang/String;)Lcom/cleanmaster/common_transition/report/c;
    //   1201: invokevirtual 1603	com/cleanmaster/common_transition/report/c:report	()V
    //   1204: invokestatic 684	com/cleanmaster/service/b:a	()Lcom/cleanmaster/service/b;
    //   1207: pop
    //   1208: aload 12
    //   1210: invokestatic 1606	com/cleanmaster/service/b:n	(Ljava/lang/String;)V
    //   1213: invokestatic 1581	com/cleanmaster/ui/app/market/a/a:b	()Z
    //   1216: ifeq +12 -> 1228
    //   1219: invokestatic 1586	com/cleanmaster/ui/app/market/storage/MarketStorage:a	()Lcom/cleanmaster/ui/app/market/storage/MarketStorage;
    //   1222: aload 12
    //   1224: invokevirtual 1588	com/cleanmaster/ui/app/market/storage/MarketStorage:k	(Ljava/lang/String;)Z
    //   1227: pop
    //   1228: invokestatic 542	com/cleanmaster/func/cache/DiskCache:a	()Lcom/cleanmaster/func/cache/DiskCache;
    //   1231: aload 12
    //   1233: invokevirtual 1609	com/cleanmaster/func/cache/DiskCache:b	(Ljava/lang/String;)Lcom/ijinshan/cleaner/bean/g;
    //   1236: astore 14
    //   1238: invokestatic 684	com/cleanmaster/service/b:a	()Lcom/cleanmaster/service/b;
    //   1241: pop
    //   1242: aload 12
    //   1244: invokestatic 1611	com/cleanmaster/service/b:h	(Ljava/lang/String;)Ljava/lang/String;
    //   1247: astore 11
    //   1249: aload 11
    //   1251: astore_1
    //   1252: aload 11
    //   1254: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1257: ifeq +20 -> 1277
    //   1260: aload 11
    //   1262: astore_1
    //   1263: aload 14
    //   1265: ifnull +12 -> 1277
    //   1268: aload 14
    //   1270: getfield 1612	com/ijinshan/cleaner/bean/g:c	Ljava/lang/String;
    //   1273: invokestatic 1613	com/cleanmaster/base/c:f	(Ljava/lang/String;)Ljava/lang/String;
    //   1276: astore_1
    //   1277: invokestatic 542	com/cleanmaster/func/cache/DiskCache:a	()Lcom/cleanmaster/func/cache/DiskCache;
    //   1280: pop
    //   1281: aload 12
    //   1283: invokestatic 1615	com/cleanmaster/func/cache/DiskCache:d	(Ljava/lang/String;)Lcom/ijinshan/cleaner/bean/g;
    //   1286: pop
    //   1287: aload_1
    //   1288: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1291: pop
    //   1292: new 1617	com/cleanmaster/common/a/s
    //   1295: dup
    //   1296: aload 12
    //   1298: invokespecial 1618	com/cleanmaster/common/a/s:<init>	(Ljava/lang/String;)V
    //   1301: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   1304: invokestatic 684	com/cleanmaster/service/b:a	()Lcom/cleanmaster/service/b;
    //   1307: pop
    //   1308: aload 12
    //   1310: invokestatic 1620	com/cleanmaster/service/b:g	(Ljava/lang/String;)V
    //   1313: aload 13
    //   1315: invokestatic 1625	com/cleanmaster/dao/f:n	(Landroid/content/Context;)Lcom/cleanmaster/dao/AppOpenFrequencyDaoImpl;
    //   1318: aload 12
    //   1320: invokevirtual 1628	com/cleanmaster/dao/AppOpenFrequencyDaoImpl:a	(Ljava/lang/String;)Z
    //   1323: pop
    //   1324: aload 12
    //   1326: ifnull +105 -> 1431
    //   1329: invokestatic 529	com/cleanmaster/func/cache/e:a	()Lcom/cleanmaster/func/cache/e;
    //   1332: getfield 532	com/cleanmaster/func/cache/e:b	Lcom/cleanmaster/func/cache/e$b;
    //   1335: aload 12
    //   1337: invokevirtual 1630	com/cleanmaster/func/cache/e$b:b	(Ljava/lang/String;)V
    //   1340: aload 12
    //   1342: invokestatic 1633	com/cleanmaster/settings/b:a	(Ljava/lang/String;)Z
    //   1345: pop
    //   1346: getstatic 1639	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:SYSCACHE	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1349: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1352: getstatic 1647	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:SYSFIXEDCACHE	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1355: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1358: getstatic 1650	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:SDCACHE	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1361: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1364: getstatic 1653	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:PROCESS	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1367: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1370: getstatic 1656	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:APPLEFTOVER	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1373: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1376: getstatic 1659	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:APKFILE	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1379: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1382: getstatic 1662	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:ROOTCACHE	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1385: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1388: getstatic 1665	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:SDCACHE_ADV	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1391: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1394: getstatic 1668	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:APPLEFTOVER_ADV	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1397: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1400: getstatic 1671	com/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE:SDCACHE_OFF	Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;
    //   1403: invokestatic 1644	com/cleanmaster/junk/ui/activity/JunkManagerActivity:a	(Lcom/cleanmaster/junk/engine/IJunkRequest$EM_JUNK_DATA_TYPE;)V
    //   1406: invokestatic 1676	com/cleanmaster/ui/resultpage/storage/ResultPageStorage:b	()Lcom/cleanmaster/ui/resultpage/storage/ResultPageStorage;
    //   1409: invokevirtual 1679	com/cleanmaster/ui/resultpage/storage/ResultPageStorage:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   1412: astore_1
    //   1413: aload_1
    //   1414: ifnull +17 -> 1431
    //   1417: aload_1
    //   1418: aload 12
    //   1420: invokestatic 1685	com/cleanmaster/junk/bean/CleanItem:removeTABLE_NAME_TOP1	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)I
    //   1423: pop
    //   1424: aload_1
    //   1425: aload 12
    //   1427: invokestatic 1688	com/cleanmaster/junk/bean/CleanItem:removeTABLE_NAME_TOP7	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)I
    //   1430: pop
    //   1431: aload 12
    //   1433: ifnull +69 -> 1502
    //   1436: invokestatic 1693	com/cleanmaster/func/cache/MultiUnusedCache:a	()Lcom/cleanmaster/func/cache/MultiUnusedCache;
    //   1439: astore_1
    //   1440: aload 12
    //   1442: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1445: ifne +57 -> 1502
    //   1448: aload_1
    //   1449: invokevirtual 1696	com/cleanmaster/func/cache/MultiUnusedCache:b	()Lcom/cleanmaster/dao/r;
    //   1452: astore_1
    //   1453: aload_1
    //   1454: ifnull +48 -> 1502
    //   1457: aload_1
    //   1458: getstatic 1697	com/cleanmaster/func/cache/MultiUnusedCache:b	Ljava/lang/String;
    //   1461: ldc_w 1699
    //   1464: iconst_1
    //   1465: anewarray 470	java/lang/String
    //   1468: dup
    //   1469: iconst_0
    //   1470: aload 12
    //   1472: aastore
    //   1473: invokevirtual 1704	com/cleanmaster/dao/r:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   1476: istore_3
    //   1477: iload_3
    //   1478: istore_2
    //   1479: iload_2
    //   1480: ifle +22 -> 1502
    //   1483: aload_1
    //   1484: iconst_3
    //   1485: invokestatic 1707	com/cleanmaster/func/cache/MultiUnusedCache:a	(Lcom/cleanmaster/dao/r;I)V
    //   1488: aload_1
    //   1489: iconst_1
    //   1490: invokestatic 1707	com/cleanmaster/func/cache/MultiUnusedCache:a	(Lcom/cleanmaster/dao/r;I)V
    //   1493: aload_1
    //   1494: iconst_2
    //   1495: invokestatic 1707	com/cleanmaster/func/cache/MultiUnusedCache:a	(Lcom/cleanmaster/dao/r;I)V
    //   1498: aload_1
    //   1499: invokestatic 1710	com/cleanmaster/func/cache/MultiUnusedCache:a	(Lcom/cleanmaster/dao/r;)V
    //   1502: aload 12
    //   1504: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1507: ifne -1496 -> 11
    //   1510: aload_0
    //   1511: invokestatic 1715	com/cleanmaster/configmanager/e:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/e;
    //   1514: invokevirtual 1717	com/cleanmaster/configmanager/e:q	()Ljava/util/List;
    //   1517: astore_1
    //   1518: aload_0
    //   1519: invokestatic 1715	com/cleanmaster/configmanager/e:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/e;
    //   1522: invokevirtual 1720	com/cleanmaster/configmanager/e:r	()Ljava/util/List;
    //   1525: astore 11
    //   1527: aload_1
    //   1528: ifnull +31 -> 1559
    //   1531: aload_1
    //   1532: aload 12
    //   1534: invokeinterface 1721 2 0
    //   1539: ifeq +20 -> 1559
    //   1542: aload_1
    //   1543: aload 12
    //   1545: invokeinterface 1724 2 0
    //   1550: pop
    //   1551: aload_0
    //   1552: invokestatic 1715	com/cleanmaster/configmanager/e:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/e;
    //   1555: aload_1
    //   1556: invokevirtual 1725	com/cleanmaster/configmanager/e:a	(Ljava/util/List;)V
    //   1559: aload 11
    //   1561: ifnull -1550 -> 11
    //   1564: aload 11
    //   1566: aload 12
    //   1568: invokeinterface 1721 2 0
    //   1573: ifeq -1562 -> 11
    //   1576: aload 11
    //   1578: aload 12
    //   1580: invokeinterface 1724 2 0
    //   1585: pop
    //   1586: aload_0
    //   1587: invokestatic 1715	com/cleanmaster/configmanager/e:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/e;
    //   1590: aload 11
    //   1592: invokevirtual 1727	com/cleanmaster/configmanager/e:b	(Ljava/util/List;)V
    //   1595: return
    //   1596: astore_1
    //   1597: aload_1
    //   1598: invokevirtual 1272	java/lang/Exception:printStackTrace	()V
    //   1601: goto -170 -> 1431
    //   1604: astore 11
    //   1606: aload 11
    //   1608: invokevirtual 1272	java/lang/Exception:printStackTrace	()V
    //   1611: goto -132 -> 1479
    //   1614: ldc_w 807
    //   1617: aload 12
    //   1619: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1622: ifeq +30 -> 1652
    //   1625: aload_1
    //   1626: ldc_w 338
    //   1629: invokevirtual 732	android/content/Intent:getStringArrayListExtra	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   1632: astore_1
    //   1633: aload_1
    //   1634: ifnull -1623 -> 11
    //   1637: new 31	com/cleanmaster/service/LocalService$MovePackageObserver
    //   1640: dup
    //   1641: aload_1
    //   1642: ldc_w 1137
    //   1645: invokespecial 1730	com/cleanmaster/service/LocalService$MovePackageObserver:<init>	(Ljava/util/ArrayList;Ljava/lang/String;)V
    //   1648: invokevirtual 1732	com/cleanmaster/service/LocalService$MovePackageObserver:a	()V
    //   1651: return
    //   1652: ldc_w 944
    //   1655: aload 12
    //   1657: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1660: ifeq +30 -> 1690
    //   1663: aload_1
    //   1664: ldc_w 338
    //   1667: invokevirtual 732	android/content/Intent:getStringArrayListExtra	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   1670: astore_1
    //   1671: aload_1
    //   1672: ifnull -1661 -> 11
    //   1675: new 31	com/cleanmaster/service/LocalService$MovePackageObserver
    //   1678: dup
    //   1679: aload_1
    //   1680: ldc_w 1216
    //   1683: invokespecial 1730	com/cleanmaster/service/LocalService$MovePackageObserver:<init>	(Ljava/util/ArrayList;Ljava/lang/String;)V
    //   1686: invokevirtual 1732	com/cleanmaster/service/LocalService$MovePackageObserver:a	()V
    //   1689: return
    //   1690: ldc_w 1734
    //   1693: aload 12
    //   1695: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1698: ifne -1687 -> 11
    //   1701: ldc_w 322
    //   1704: aload 12
    //   1706: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1709: ifeq +9 -> 1718
    //   1712: aload_0
    //   1713: aload_1
    //   1714: invokespecial 1736	com/cleanmaster/service/LocalService:e	(Landroid/content/Intent;)V
    //   1717: return
    //   1718: ldc_w 716
    //   1721: aload 12
    //   1723: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1726: ifeq +9 -> 1735
    //   1729: aload_0
    //   1730: aload_1
    //   1731: invokespecial 1738	com/cleanmaster/service/LocalService:d	(Landroid/content/Intent;)V
    //   1734: return
    //   1735: ldc_w 805
    //   1738: aload 12
    //   1740: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1743: ifeq +9 -> 1752
    //   1746: aload_0
    //   1747: aload_1
    //   1748: invokespecial 1740	com/cleanmaster/service/LocalService:c	(Landroid/content/Intent;)V
    //   1751: return
    //   1752: ldc_w 1742
    //   1755: aload 12
    //   1757: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1760: ifeq +161 -> 1921
    //   1763: invokestatic 442	java/lang/System:currentTimeMillis	()J
    //   1766: lstore 9
    //   1768: aload_1
    //   1769: ldc_w 303
    //   1772: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   1775: astore 11
    //   1777: aload_1
    //   1778: ldc_w 305
    //   1781: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   1784: astore 12
    //   1786: aload_1
    //   1787: ldc_w 307
    //   1790: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   1793: astore_1
    //   1794: new 768	java/util/HashMap
    //   1797: dup
    //   1798: invokespecial 824	java/util/HashMap:<init>	()V
    //   1801: astore 13
    //   1803: aload 13
    //   1805: ldc_w 826
    //   1808: aload 11
    //   1810: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1813: pop
    //   1814: aload 13
    //   1816: ldc_w 832
    //   1819: aload 12
    //   1821: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1824: pop
    //   1825: aload 13
    //   1827: ldc_w 834
    //   1830: getstatic 839	android/os/Build:MODEL	Ljava/lang/String;
    //   1833: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1836: pop
    //   1837: aload 13
    //   1839: ldc_w 841
    //   1842: aload_1
    //   1843: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1846: pop
    //   1847: aload 13
    //   1849: ldc_w 843
    //   1852: getstatic 848	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   1855: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1858: pop
    //   1859: aload 13
    //   1861: ldc_w 850
    //   1864: invokestatic 851	com/cleanmaster/service/LocalService:d	()Ljava/lang/String;
    //   1867: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1870: pop
    //   1871: aload 13
    //   1873: ldc_w 853
    //   1876: aload_0
    //   1877: invokestatic 855	LibcoreWrapper/a:p	(Landroid/content/Context;)Ljava/lang/String;
    //   1880: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1883: pop
    //   1884: aload 13
    //   1886: ldc_w 857
    //   1889: invokestatic 863	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1892: invokevirtual 866	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   1895: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1898: pop
    //   1899: new 920	com/cleanmaster/feedback/a
    //   1902: dup
    //   1903: ldc_w 922
    //   1906: aload 13
    //   1908: aconst_null
    //   1909: invokestatic 927	com/cleanmaster/kinfoc/k:a	(Ljava/lang/String;Ljava/util/Map;[Lcom/cleanmaster/kinfoc/d;)Ljava/lang/String;
    //   1912: lload 9
    //   1914: invokespecial 929	com/cleanmaster/feedback/a:<init>	(Ljava/lang/String;J)V
    //   1917: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   1920: return
    //   1921: ldc_w 946
    //   1924: aload 12
    //   1926: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1929: ifeq +18 -> 1947
    //   1932: invokestatic 1747	com/cleanmaster/base/util/concurrent/BackgroundThread:b	()Landroid/os/Handler;
    //   1935: new 26	com/cleanmaster/service/LocalService$9
    //   1938: dup
    //   1939: invokespecial 1748	com/cleanmaster/service/LocalService$9:<init>	()V
    //   1942: invokevirtual 1754	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   1945: pop
    //   1946: return
    //   1947: ldc_w 1756
    //   1950: aload 12
    //   1952: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1955: ifeq +8 -> 1963
    //   1958: aload_0
    //   1959: invokespecial 1758	com/cleanmaster/service/LocalService:b	()V
    //   1962: return
    //   1963: ldc_w 1760
    //   1966: aload 12
    //   1968: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1971: ifeq +8 -> 1979
    //   1974: aload_0
    //   1975: invokespecial 1762	com/cleanmaster/service/LocalService:c	()V
    //   1978: return
    //   1979: ldc_w 1764
    //   1982: aload 12
    //   1984: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1987: ifeq +142 -> 2129
    //   1990: new 134	java/util/ArrayList
    //   1993: dup
    //   1994: invokespecial 519	java/util/ArrayList:<init>	()V
    //   1997: astore 11
    //   1999: invokestatic 1769	com/cleanmaster/boost/process/util/o:a	()Lcom/cleanmaster/boost/process/util/o;
    //   2002: pop
    //   2003: aload_0
    //   2004: invokevirtual 655	com/cleanmaster/service/LocalService:getBaseContext	()Landroid/content/Context;
    //   2007: astore_1
    //   2008: new 22	com/cleanmaster/service/LocalService$7
    //   2011: dup
    //   2012: aload 11
    //   2014: invokespecial 1770	com/cleanmaster/service/LocalService$7:<init>	(Ljava/util/List;)V
    //   2017: astore 11
    //   2019: new 1772	com/cleanmaster/boost/boostengine/d/c
    //   2022: dup
    //   2023: invokespecial 1773	com/cleanmaster/boost/boostengine/d/c:<init>	()V
    //   2026: astore 12
    //   2028: aload 12
    //   2030: getstatic 1776	com/cleanmaster/boost/boostengine/a:a	I
    //   2033: putfield 1777	com/cleanmaster/boost/boostengine/d/c:a	I
    //   2036: new 1779	com/cleanmaster/boost/boostengine/c/d
    //   2039: dup
    //   2040: invokespecial 1780	com/cleanmaster/boost/boostengine/c/d:<init>	()V
    //   2043: astore 13
    //   2045: aload_1
    //   2046: invokestatic 166	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   2049: invokevirtual 1781	com/cleanmaster/configmanager/d:x	()Z
    //   2052: ifeq +13 -> 2065
    //   2055: aload_1
    //   2056: invokestatic 166	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   2059: invokevirtual 1784	com/cleanmaster/configmanager/d:y	()Z
    //   2062: ifeq +58 -> 2120
    //   2065: aload 12
    //   2067: getfield 1787	com/cleanmaster/boost/boostengine/d/c:c	Ljava/util/Map;
    //   2070: aload 12
    //   2072: getfield 1777	com/cleanmaster/boost/boostengine/d/c:a	I
    //   2075: invokestatic 990	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   2078: aload 13
    //   2080: invokeinterface 1788 3 0
    //   2085: pop
    //   2086: new 134	java/util/ArrayList
    //   2089: dup
    //   2090: invokespecial 519	java/util/ArrayList:<init>	()V
    //   2093: astore 13
    //   2095: new 1790	com/cleanmaster/boost/boostengine/d/b
    //   2098: dup
    //   2099: aload_1
    //   2100: aload 12
    //   2102: invokespecial 1793	com/cleanmaster/boost/boostengine/d/b:<init>	(Landroid/content/Context;Lcom/cleanmaster/boost/boostengine/d/c;)V
    //   2105: new 1795	com/cleanmaster/boost/process/util/o$d
    //   2108: dup
    //   2109: aload 13
    //   2111: aload 11
    //   2113: invokespecial 1798	com/cleanmaster/boost/process/util/o$d:<init>	(Ljava/util/List;Lcom/cleanmaster/boost/process/util/o$b;)V
    //   2116: invokevirtual 1801	com/cleanmaster/boost/boostengine/d/b:a	(Lcom/cleanmaster/boost/boostengine/d/b$a;)V
    //   2119: return
    //   2120: aload 13
    //   2122: iconst_1
    //   2123: putfield 1802	com/cleanmaster/boost/boostengine/c/d:b	Z
    //   2126: goto -61 -> 2065
    //   2129: ldc_w 331
    //   2132: aload 12
    //   2134: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2137: ifeq +8 -> 2145
    //   2140: aload_1
    //   2141: invokestatic 1804	com/cleanmaster/service/LocalService:b	(Landroid/content/Intent;)V
    //   2144: return
    //   2145: ldc_w 1806
    //   2148: aload 12
    //   2150: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2153: ifeq +91 -> 2244
    //   2156: aload_1
    //   2157: ldc_w 1808
    //   2160: invokevirtual 1296	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   2163: checkcast 962	com/ijinshan/cleaner/bean/UninstallAppData
    //   2166: astore_1
    //   2167: new 1810	com/cleanmaster/ui/app/task/f
    //   2170: dup
    //   2171: aload_1
    //   2172: getfield 973	com/ijinshan/cleaner/bean/UninstallAppData:a	Ljava/lang/String;
    //   2175: aload_0
    //   2176: invokespecial 1813	com/cleanmaster/ui/app/task/f:<init>	(Ljava/lang/String;Landroid/content/Context;)V
    //   2179: astore 11
    //   2181: aload 11
    //   2183: iconst_0
    //   2184: putfield 1814	com/cleanmaster/ui/app/task/f:j	Z
    //   2187: aload 11
    //   2189: invokevirtual 1816	com/cleanmaster/ui/app/task/f:a	()Z
    //   2192: ifeq +44 -> 2236
    //   2195: aload_1
    //   2196: aload 11
    //   2198: getfield 1818	com/cleanmaster/ui/app/task/f:c	Ljava/util/ArrayList;
    //   2201: putfield 985	com/ijinshan/cleaner/bean/UninstallAppData:k	Ljava/util/ArrayList;
    //   2204: aload_1
    //   2205: aload 11
    //   2207: getfield 1820	com/cleanmaster/ui/app/task/f:e	J
    //   2210: putfield 976	com/ijinshan/cleaner/bean/UninstallAppData:i	J
    //   2213: new 1822	com/cleanmaster/ui/app/a/d
    //   2216: dup
    //   2217: aload_1
    //   2218: getfield 973	com/ijinshan/cleaner/bean/UninstallAppData:a	Ljava/lang/String;
    //   2221: aload_1
    //   2222: getfield 976	com/ijinshan/cleaner/bean/UninstallAppData:i	J
    //   2225: aload_1
    //   2226: getfield 985	com/ijinshan/cleaner/bean/UninstallAppData:k	Ljava/util/ArrayList;
    //   2229: invokespecial 1825	com/cleanmaster/ui/app/a/d:<init>	(Ljava/lang/String;JLjava/util/ArrayList;)V
    //   2232: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   2235: return
    //   2236: aload_1
    //   2237: lconst_0
    //   2238: putfield 976	com/ijinshan/cleaner/bean/UninstallAppData:i	J
    //   2241: goto -28 -> 2213
    //   2244: ldc_w 708
    //   2247: aload 12
    //   2249: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2252: ifeq +97 -> 2349
    //   2255: new 1024	java/lang/Object
    //   2258: dup
    //   2259: invokespecial 1826	java/lang/Object:<init>	()V
    //   2262: astore 11
    //   2264: invokestatic 1829	LibcoreWrapper/a:bn	()Z
    //   2267: ifeq -2256 -> 11
    //   2270: new 177	android/content/Intent
    //   2273: dup
    //   2274: ldc_w 1831
    //   2277: invokespecial 1832	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   2280: astore 12
    //   2282: aload_1
    //   2283: ldc_w 1834
    //   2286: ldc_w 1836
    //   2289: invokevirtual 1839	android/content/Intent:setClassName	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   2292: pop
    //   2293: aload_0
    //   2294: aload 12
    //   2296: new 20	com/cleanmaster/service/LocalService$6
    //   2299: dup
    //   2300: aload_0
    //   2301: aload 12
    //   2303: ldc_w 710
    //   2306: iconst_1
    //   2307: invokevirtual 1843	android/content/Intent:getByteExtra	(Ljava/lang/String;B)B
    //   2310: aload 11
    //   2312: invokespecial 1846	com/cleanmaster/service/LocalService$6:<init>	(Lcom/cleanmaster/service/LocalService;BLjava/lang/Object;)V
    //   2315: iconst_1
    //   2316: invokevirtual 1850	com/cleanmaster/service/LocalService:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   2319: pop
    //   2320: aload 11
    //   2322: monitorenter
    //   2323: aload 11
    //   2325: ldc2_w 1851
    //   2328: invokevirtual 1855	java/lang/Object:wait	(J)V
    //   2331: aload 11
    //   2333: monitorexit
    //   2334: return
    //   2335: astore_1
    //   2336: aload 11
    //   2338: monitorexit
    //   2339: aload_1
    //   2340: athrow
    //   2341: astore_1
    //   2342: aload_1
    //   2343: invokevirtual 754	java/lang/InterruptedException:printStackTrace	()V
    //   2346: goto -15 -> 2331
    //   2349: ldc_w 1104
    //   2352: aload 12
    //   2354: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2357: ifeq +95 -> 2452
    //   2360: aload_1
    //   2361: ldc_w 307
    //   2364: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   2367: astore 11
    //   2369: new 1857	com/cleanmaster/scanengin/d
    //   2372: dup
    //   2373: invokespecial 1858	com/cleanmaster/scanengin/d:<init>	()V
    //   2376: astore_1
    //   2377: aload_1
    //   2378: new 1860	com/cleanmaster/scanengin/d$a
    //   2381: dup
    //   2382: aload_0
    //   2383: aload 11
    //   2385: invokespecial 1863	com/cleanmaster/scanengin/d$a:<init>	(Lcom/cleanmaster/service/LocalService;Ljava/lang/String;)V
    //   2388: putfield 1866	com/cleanmaster/scanengin/d:d	Lcom/cleanmaster/scanengin/d$a;
    //   2391: aload_1
    //   2392: getfield 1869	com/cleanmaster/scanengin/d:b	Lcom/cleanmaster/scanengin/n;
    //   2395: ifnonnull +14 -> 2409
    //   2398: aload_1
    //   2399: new 1871	com/cleanmaster/scanengin/n
    //   2402: dup
    //   2403: invokespecial 1872	com/cleanmaster/scanengin/n:<init>	()V
    //   2406: putfield 1869	com/cleanmaster/scanengin/d:b	Lcom/cleanmaster/scanengin/n;
    //   2409: new 1874	com/cleanmaster/scanengin/a
    //   2412: dup
    //   2413: iconst_1
    //   2414: iconst_2
    //   2415: invokespecial 1877	com/cleanmaster/scanengin/a:<init>	(II)V
    //   2418: astore 11
    //   2420: aload 11
    //   2422: new 1879	com/cleanmaster/scanengin/d$1
    //   2425: dup
    //   2426: aload_1
    //   2427: invokespecial 1882	com/cleanmaster/scanengin/d$1:<init>	(Lcom/cleanmaster/scanengin/d;)V
    //   2430: invokevirtual 1885	com/cleanmaster/scanengin/a:a	(Lcom/cleanmaster/junk/engine/g;)V
    //   2433: aload_1
    //   2434: getfield 1869	com/cleanmaster/scanengin/d:b	Lcom/cleanmaster/scanengin/n;
    //   2437: aload 11
    //   2439: invokevirtual 1888	com/cleanmaster/scanengin/n:a	(Lcom/cleanmaster/scanengin/h;)Z
    //   2442: pop
    //   2443: aload_1
    //   2444: getfield 1869	com/cleanmaster/scanengin/d:b	Lcom/cleanmaster/scanengin/n;
    //   2447: invokevirtual 1890	com/cleanmaster/scanengin/n:e	()Z
    //   2450: pop
    //   2451: return
    //   2452: ldc_w 1198
    //   2455: aload 12
    //   2457: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2460: ifeq +7 -> 2467
    //   2463: invokestatic 1893	com/cleanmaster/service/b/e:a	()V
    //   2466: return
    //   2467: ldc_w 359
    //   2470: aload 12
    //   2472: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2475: ifeq +256 -> 2731
    //   2478: aload_1
    //   2479: ldc_w 361
    //   2482: invokevirtual 818	android/content/Intent:getSerializableExtra	(Ljava/lang/String;)Ljava/io/Serializable;
    //   2485: checkcast 134	java/util/ArrayList
    //   2488: astore 11
    //   2490: aload_1
    //   2491: ldc_w 363
    //   2494: iconst_1
    //   2495: invokevirtual 1896	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   2498: istore 5
    //   2500: aload 11
    //   2502: ifnull +13 -> 2515
    //   2505: aload 11
    //   2507: invokeinterface 368 1 0
    //   2512: ifeq +10 -> 2522
    //   2515: invokestatic 571	com/cleanmaster/ui/app/a/g:a	()Lcom/cleanmaster/ui/app/a/g;
    //   2518: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   2521: return
    //   2522: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   2525: invokestatic 1898	com/cleanmaster/base/util/net/b:c	(Landroid/content/Context;)Z
    //   2528: ifeq -2517 -> 11
    //   2531: new 1129	client/core/model/TimeStamp
    //   2534: dup
    //   2535: new 239	java/lang/StringBuilder
    //   2538: dup
    //   2539: ldc_w 1900
    //   2542: invokespecial 242	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2545: aload 11
    //   2547: invokeinterface 370 1 0
    //   2552: invokevirtual 250	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2555: ldc_w 1902
    //   2558: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2561: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2564: invokespecial 1903	client/core/model/TimeStamp:<init>	(Ljava/lang/String;)V
    //   2567: invokevirtual 1142	client/core/model/TimeStamp:b	()Lclient/core/model/TimeStamp;
    //   2570: pop
    //   2571: aload 11
    //   2573: invokeinterface 368 1 0
    //   2578: ifne -2567 -> 11
    //   2581: getstatic 1906	com/cleanmaster/ui/app/market/transport/f:e	Z
    //   2584: ifeq +134 -> 2718
    //   2587: invokestatic 1911	com/cleanmaster/ui/app/market/transport/e:a	()Lcom/cleanmaster/ui/app/market/transport/e;
    //   2590: pop
    //   2591: aload 11
    //   2593: invokestatic 1914	com/cleanmaster/ui/app/market/transport/e:b	(Ljava/util/List;)Lcom/cleanmaster/ui/app/market/transport/b;
    //   2596: astore_1
    //   2597: aload_1
    //   2598: getfield 1917	com/cleanmaster/ui/app/market/transport/b:b	Z
    //   2601: ifeq +16 -> 2617
    //   2604: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   2607: invokestatic 166	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   2610: ldc_w 590
    //   2613: iconst_0
    //   2614: invokevirtual 1512	com/cleanmaster/configmanager/d:b	(Ljava/lang/String;Z)V
    //   2617: invokestatic 571	com/cleanmaster/ui/app/a/g:a	()Lcom/cleanmaster/ui/app/a/g;
    //   2620: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   2623: aload_1
    //   2624: getfield 1917	com/cleanmaster/ui/app/market/transport/b:b	Z
    //   2627: ifeq +18 -> 2645
    //   2630: iload 5
    //   2632: ifeq +13 -> 2645
    //   2635: ldc_w 582
    //   2638: iconst_3
    //   2639: invokestatic 1201	com/cleanmaster/service/b:a	(I)J
    //   2642: invokestatic 1918	com/cleanmaster/service/b:b	(Ljava/lang/String;J)V
    //   2645: new 239	java/lang/StringBuilder
    //   2648: dup
    //   2649: ldc_w 1920
    //   2652: invokespecial 242	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2655: aload_1
    //   2656: getfield 1922	com/cleanmaster/ui/app/market/transport/b:a	Ljava/util/Map;
    //   2659: invokeinterface 1923 1 0
    //   2664: invokevirtual 250	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2667: ldc_w 1925
    //   2670: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2673: pop
    //   2674: invokestatic 542	com/cleanmaster/func/cache/DiskCache:a	()Lcom/cleanmaster/func/cache/DiskCache;
    //   2677: aload_1
    //   2678: getfield 1922	com/cleanmaster/ui/app/market/transport/b:a	Ljava/util/Map;
    //   2681: invokevirtual 554	com/cleanmaster/func/cache/DiskCache:b	(Ljava/util/Map;)I
    //   2684: istore_2
    //   2685: new 239	java/lang/StringBuilder
    //   2688: dup
    //   2689: ldc_w 1927
    //   2692: invokespecial 242	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2695: iload_2
    //   2696: invokevirtual 250	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2699: ldc_w 1925
    //   2702: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2705: pop
    //   2706: iload 5
    //   2708: ifeq -2697 -> 11
    //   2711: getstatic 1930	com/cleanmaster/ui/app/a/g:d	Lcom/cleanmaster/ui/app/a/g;
    //   2714: invokestatic 496	com/cleanmaster/service/LocalService:b	(Lclient/core/model/c;)V
    //   2717: return
    //   2718: invokestatic 1911	com/cleanmaster/ui/app/market/transport/e:a	()Lcom/cleanmaster/ui/app/market/transport/e;
    //   2721: pop
    //   2722: aload 11
    //   2724: invokestatic 1932	com/cleanmaster/ui/app/market/transport/e:a	(Ljava/util/List;)Lcom/cleanmaster/ui/app/market/transport/b;
    //   2727: astore_1
    //   2728: goto -131 -> 2597
    //   2731: ldc_w 1934
    //   2734: aload 12
    //   2736: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2739: ifeq +8 -> 2747
    //   2742: aload_1
    //   2743: invokestatic 1936	com/cleanmaster/service/LocalService:a	(Landroid/content/Intent;)V
    //   2746: return
    //   2747: ldc_w 290
    //   2750: aload 12
    //   2752: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2755: ifeq +242 -> 2997
    //   2758: aload_1
    //   2759: ldc_w 280
    //   2762: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   2765: astore 11
    //   2767: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   2770: ldc_w 1938
    //   2773: iconst_0
    //   2774: invokevirtual 411	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   2777: ldc_w 1940
    //   2780: aconst_null
    //   2781: invokeinterface 1573 3 0
    //   2786: astore 12
    //   2788: aload_1
    //   2789: ldc_w 285
    //   2792: iconst_0
    //   2793: invokevirtual 1896	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   2796: istore 5
    //   2798: getstatic 1120	java/lang/System:out	Ljava/io/PrintStream;
    //   2801: new 239	java/lang/StringBuilder
    //   2804: dup
    //   2805: ldc_w 1942
    //   2808: invokespecial 242	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2811: iload 5
    //   2813: invokevirtual 1005	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2816: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2819: invokevirtual 1127	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2822: aload 11
    //   2824: aload 12
    //   2826: invokestatic 1944	LibcoreWrapper/a:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2829: ifgt +8 -> 2837
    //   2832: iload 5
    //   2834: ifeq -2823 -> 11
    //   2837: invokestatic 1949	com/cleanmaster/ui/resultpage/ctrl/j:a	()Lcom/cleanmaster/ui/resultpage/ctrl/j;
    //   2840: astore_1
    //   2841: new 239	java/lang/StringBuilder
    //   2844: dup
    //   2845: invokespecial 1950	java/lang/StringBuilder:<init>	()V
    //   2848: getstatic 1951	com/cleanmaster/ui/resultpage/ctrl/j:a	Ljava/lang/String;
    //   2851: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2854: invokestatic 1954	LibcoreWrapper/a:C	()Ljava/lang/String;
    //   2857: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2860: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2863: iconst_0
    //   2864: invokestatic 1957	com/cleanmaster/ui/app/market/transport/e:a	(Ljava/lang/String;Z)Ljava/lang/String;
    //   2867: invokestatic 1962	com/cleanmaster/ui/resultpage/ctrl/b:a	(Ljava/lang/String;)Lcom/cleanmaster/ui/resultpage/ctrl/b;
    //   2870: astore 12
    //   2872: aload 12
    //   2874: getfield 1963	com/cleanmaster/ui/resultpage/ctrl/b:a	I
    //   2877: ifne +95 -> 2972
    //   2880: iconst_1
    //   2881: istore_2
    //   2882: iload_2
    //   2883: ifeq +109 -> 2992
    //   2886: invokestatic 1676	com/cleanmaster/ui/resultpage/storage/ResultPageStorage:b	()Lcom/cleanmaster/ui/resultpage/storage/ResultPageStorage;
    //   2889: invokevirtual 1679	com/cleanmaster/ui/resultpage/storage/ResultPageStorage:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   2892: astore 13
    //   2894: aload 13
    //   2896: ifnull +14 -> 2910
    //   2899: aload 13
    //   2901: ldc_w 1965
    //   2904: aconst_null
    //   2905: aconst_null
    //   2906: invokevirtual 1970	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   2909: pop
    //   2910: invokestatic 1676	com/cleanmaster/ui/resultpage/storage/ResultPageStorage:b	()Lcom/cleanmaster/ui/resultpage/storage/ResultPageStorage;
    //   2913: aload 12
    //   2915: getfield 1973	com/cleanmaster/ui/resultpage/ctrl/b:b	Ljava/util/Collection;
    //   2918: invokevirtual 1976	com/cleanmaster/ui/resultpage/storage/ResultPageStorage:a	(Ljava/util/Collection;)I
    //   2921: istore_2
    //   2922: iload_2
    //   2923: ifle +7 -> 2930
    //   2926: aload_1
    //   2927: invokevirtual 1977	com/cleanmaster/ui/resultpage/ctrl/j:b	()V
    //   2930: iload_2
    //   2931: ifle +56 -> 2987
    //   2934: iconst_1
    //   2935: istore_2
    //   2936: iload_2
    //   2937: ifeq -2926 -> 11
    //   2940: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   2943: ldc_w 1938
    //   2946: iconst_0
    //   2947: invokevirtual 411	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   2950: invokeinterface 421 1 0
    //   2955: ldc_w 1940
    //   2958: aload 11
    //   2960: invokeinterface 1981 3 0
    //   2965: invokeinterface 430 1 0
    //   2970: pop
    //   2971: return
    //   2972: iconst_0
    //   2973: istore_2
    //   2974: goto -92 -> 2882
    //   2977: astore 13
    //   2979: aload 13
    //   2981: invokevirtual 1272	java/lang/Exception:printStackTrace	()V
    //   2984: goto -74 -> 2910
    //   2987: iconst_0
    //   2988: istore_2
    //   2989: goto -53 -> 2936
    //   2992: iconst_0
    //   2993: istore_2
    //   2994: goto -58 -> 2936
    //   2997: ldc_w 272
    //   3000: aload 12
    //   3002: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3005: ifeq +53 -> 3058
    //   3008: aload_1
    //   3009: ldc_w 266
    //   3012: iconst_0
    //   3013: invokevirtual 810	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   3016: istore_2
    //   3017: aload_1
    //   3018: ldc_w 268
    //   3021: iconst_0
    //   3022: invokevirtual 810	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   3025: istore_3
    //   3026: aload_1
    //   3027: ldc_w 270
    //   3030: ldc_w 1982
    //   3033: invokevirtual 810	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   3036: istore 4
    //   3038: iload 4
    //   3040: ldc_w 1982
    //   3043: if_icmpeq -3032 -> 11
    //   3046: invokestatic 1676	com/cleanmaster/ui/resultpage/storage/ResultPageStorage:b	()Lcom/cleanmaster/ui/resultpage/storage/ResultPageStorage;
    //   3049: iload_2
    //   3050: iload_3
    //   3051: iload 4
    //   3053: invokevirtual 1985	com/cleanmaster/ui/resultpage/storage/ResultPageStorage:a	(III)Z
    //   3056: pop
    //   3057: return
    //   3058: ldc_w 1987
    //   3061: aload 12
    //   3063: invokevirtual 1990	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3066: ifeq +62 -> 3128
    //   3069: aload_1
    //   3070: ldc_w 1992
    //   3073: iconst_0
    //   3074: invokevirtual 1896	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   3077: istore 5
    //   3079: invokestatic 1997	com/cleanmaster/ui/app/market/c/h:a	()Lcom/cleanmaster/ui/app/market/c/h;
    //   3082: astore_1
    //   3083: aload_1
    //   3084: getfield 1998	com/cleanmaster/ui/app/market/c/h:a	Z
    //   3087: ifne -3076 -> 11
    //   3090: invokestatic 2001	com/cleanmaster/base/e:b	()Z
    //   3093: ifeq -3082 -> 11
    //   3096: invokestatic 2004	com/cleanmaster/screensave/newscreensaver/init/ScreenAdTask:g	()Z
    //   3099: ifne -3088 -> 11
    //   3102: aload_1
    //   3103: iconst_1
    //   3104: putfield 1998	com/cleanmaster/ui/app/market/c/h:a	Z
    //   3107: new 744	java/lang/Thread
    //   3110: dup
    //   3111: new 2006	com/cleanmaster/ui/app/market/c/h$4
    //   3114: dup
    //   3115: aload_1
    //   3116: iload 5
    //   3118: invokespecial 2009	com/cleanmaster/ui/app/market/c/h$4:<init>	(Lcom/cleanmaster/ui/app/market/c/h;Z)V
    //   3121: invokespecial 2012	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   3124: invokevirtual 2015	java/lang/Thread:start	()V
    //   3127: return
    //   3128: ldc -66
    //   3130: aload 12
    //   3132: invokevirtual 1990	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3135: ifeq +32 -> 3167
    //   3138: aload_1
    //   3139: ldc -76
    //   3141: iconst_0
    //   3142: invokevirtual 810	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   3145: istore_2
    //   3146: invokestatic 1997	com/cleanmaster/ui/app/market/c/h:a	()Lcom/cleanmaster/ui/app/market/c/h;
    //   3149: astore_1
    //   3150: invokestatic 1747	com/cleanmaster/base/util/concurrent/BackgroundThread:b	()Landroid/os/Handler;
    //   3153: new 2017	com/cleanmaster/ui/app/market/c/h$12
    //   3156: dup
    //   3157: aload_1
    //   3158: iload_2
    //   3159: invokespecial 2020	com/cleanmaster/ui/app/market/c/h$12:<init>	(Lcom/cleanmaster/ui/app/market/c/h;I)V
    //   3162: invokevirtual 1754	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   3165: pop
    //   3166: return
    //   3167: ldc_w 953
    //   3170: aload 12
    //   3172: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3175: ifeq +18 -> 3193
    //   3178: invokestatic 1747	com/cleanmaster/base/util/concurrent/BackgroundThread:b	()Landroid/os/Handler;
    //   3181: new 8	com/cleanmaster/service/LocalService$10
    //   3184: dup
    //   3185: invokespecial 2021	com/cleanmaster/service/LocalService$10:<init>	()V
    //   3188: invokevirtual 1754	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   3191: pop
    //   3192: return
    //   3193: ldc_w 1220
    //   3196: aload 12
    //   3198: invokevirtual 1990	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   3201: ifeq +93 -> 3294
    //   3204: invokestatic 2026	com/cleanmaster/gaid/a:b	()Lcom/cleanmaster/gaid/a;
    //   3207: getfield 2027	com/cleanmaster/gaid/a:a	Ljava/lang/String;
    //   3210: astore_1
    //   3211: aload_1
    //   3212: invokestatic 278	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   3215: ifeq +17 -> 3232
    //   3218: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   3221: invokestatic 2032	com/cleanmaster/configmanager/h:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/h;
    //   3224: ldc_w 2034
    //   3227: iconst_1
    //   3228: invokevirtual 2035	com/cleanmaster/configmanager/h:b	(Ljava/lang/String;Z)V
    //   3231: return
    //   3232: aload_1
    //   3233: invokevirtual 2038	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   3236: invokestatic 2042	android/support/percent/a:b	(Ljava/lang/String;)Ljava/lang/String;
    //   3239: astore_1
    //   3240: iconst_0
    //   3241: ldc_w 1982
    //   3244: invokestatic 2045	com/cleanmaster/base/c:a	(II)I
    //   3247: istore_2
    //   3248: new 239	java/lang/StringBuilder
    //   3251: dup
    //   3252: ldc_w 2047
    //   3255: invokespecial 242	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3258: aload_1
    //   3259: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3262: ldc_w 2049
    //   3265: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3268: iload_2
    //   3269: invokevirtual 250	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   3272: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3275: iconst_0
    //   3276: invokestatic 1957	com/cleanmaster/ui/app/market/transport/e:a	(Ljava/lang/String;Z)Ljava/lang/String;
    //   3279: pop
    //   3280: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   3283: invokestatic 2032	com/cleanmaster/configmanager/h:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/h;
    //   3286: ldc_w 2034
    //   3289: iconst_1
    //   3290: invokevirtual 2035	com/cleanmaster/configmanager/h:b	(Ljava/lang/String;Z)V
    //   3293: return
    //   3294: ldc_w 1278
    //   3297: aload 12
    //   3299: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3302: ifeq +26 -> 3328
    //   3305: invokestatic 2054	com/cleanmaster/ui/msgdistrub/c/c:a	()Lcom/cleanmaster/ui/msgdistrub/c/c;
    //   3308: getfield 2055	com/cleanmaster/ui/msgdistrub/c/c:a	Ljava/util/List;
    //   3311: ifnonnull -3300 -> 11
    //   3314: new 2057	com/cleanmaster/ui/msgdistrub/a/a
    //   3317: dup
    //   3318: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   3321: invokespecial 2058	com/cleanmaster/ui/msgdistrub/a/a:<init>	(Landroid/content/Context;)V
    //   3324: invokevirtual 2059	com/cleanmaster/ui/msgdistrub/a/a:a	()V
    //   3327: return
    //   3328: ldc -31
    //   3330: aload 12
    //   3332: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3335: ifeq +9 -> 3344
    //   3338: aload_0
    //   3339: aload_1
    //   3340: invokespecial 2061	com/cleanmaster/service/LocalService:k	(Landroid/content/Intent;)V
    //   3343: return
    //   3344: ldc_w 309
    //   3347: aload 12
    //   3349: invokevirtual 681	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3352: ifeq -3341 -> 11
    //   3355: invokestatic 442	java/lang/System:currentTimeMillis	()J
    //   3358: lstore 9
    //   3360: aload_1
    //   3361: ldc_w 303
    //   3364: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   3367: astore 13
    //   3369: aload_1
    //   3370: ldc_w 305
    //   3373: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   3376: astore 14
    //   3378: aload_1
    //   3379: ldc_w 307
    //   3382: invokevirtual 400	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   3385: astore_1
    //   3386: new 768	java/util/HashMap
    //   3389: dup
    //   3390: invokespecial 824	java/util/HashMap:<init>	()V
    //   3393: astore 12
    //   3395: aload 12
    //   3397: ldc_w 826
    //   3400: aload 13
    //   3402: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3405: pop
    //   3406: aload 12
    //   3408: ldc_w 832
    //   3411: aload 14
    //   3413: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3416: pop
    //   3417: aload 12
    //   3419: ldc_w 834
    //   3422: getstatic 839	android/os/Build:MODEL	Ljava/lang/String;
    //   3425: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3428: pop
    //   3429: aload 12
    //   3431: ldc_w 841
    //   3434: aload_1
    //   3435: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3438: pop
    //   3439: aload 12
    //   3441: ldc_w 843
    //   3444: getstatic 848	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   3447: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3450: pop
    //   3451: aload 12
    //   3453: ldc_w 850
    //   3456: invokestatic 851	com/cleanmaster/service/LocalService:d	()Ljava/lang/String;
    //   3459: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3462: pop
    //   3463: aload 12
    //   3465: ldc_w 853
    //   3468: aload_0
    //   3469: invokestatic 855	LibcoreWrapper/a:p	(Landroid/content/Context;)Ljava/lang/String;
    //   3472: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3475: pop
    //   3476: aload 12
    //   3478: ldc_w 857
    //   3481: invokestatic 863	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   3484: invokevirtual 866	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   3487: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3490: pop
    //   3491: aload 12
    //   3493: ldc_w 868
    //   3496: ldc_w 2063
    //   3499: invokevirtual 830	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3502: pop
    //   3503: iconst_4
    //   3504: anewarray 889	com/cleanmaster/kinfoc/d
    //   3507: astore 13
    //   3509: new 889	com/cleanmaster/kinfoc/d
    //   3512: dup
    //   3513: invokespecial 890	com/cleanmaster/kinfoc/d:<init>	()V
    //   3516: astore 14
    //   3518: aload 11
    //   3520: astore_1
    //   3521: invokestatic 893	com/cleanmaster/base/c:q	()Ljava/lang/String;
    //   3524: ifnull +28 -> 3552
    //   3527: new 603	java/io/File
    //   3530: dup
    //   3531: invokestatic 893	com/cleanmaster/base/c:q	()Ljava/lang/String;
    //   3534: invokespecial 616	java/io/File:<init>	(Ljava/lang/String;)V
    //   3537: astore_1
    //   3538: aload_1
    //   3539: invokevirtual 896	java/io/File:length	()J
    //   3542: ldc2_w 897
    //   3545: lcmp
    //   3546: ifle +156 -> 3702
    //   3549: aload 11
    //   3551: astore_1
    //   3552: aload_1
    //   3553: ifnull +122 -> 3675
    //   3556: aload_1
    //   3557: invokevirtual 606	java/io/File:exists	()Z
    //   3560: ifeq +115 -> 3675
    //   3563: aload 14
    //   3565: aload_1
    //   3566: putfield 901	com/cleanmaster/kinfoc/d:a	Ljava/io/File;
    //   3569: aload 14
    //   3571: ldc_w 903
    //   3574: putfield 904	com/cleanmaster/kinfoc/d:b	Ljava/lang/String;
    //   3577: aload 14
    //   3579: getfield 901	com/cleanmaster/kinfoc/d:a	Ljava/io/File;
    //   3582: invokevirtual 606	java/io/File:exists	()Z
    //   3585: ifeq +22 -> 3607
    //   3588: aload 14
    //   3590: getfield 901	com/cleanmaster/kinfoc/d:a	Ljava/io/File;
    //   3593: invokevirtual 896	java/io/File:length	()J
    //   3596: lconst_0
    //   3597: lcmp
    //   3598: ifle +9 -> 3607
    //   3601: aload 13
    //   3603: iconst_0
    //   3604: aload 14
    //   3606: aastore
    //   3607: ldc_w 922
    //   3610: aload 12
    //   3612: aload 13
    //   3614: invokestatic 927	com/cleanmaster/kinfoc/k:a	(Ljava/lang/String;Ljava/util/Map;[Lcom/cleanmaster/kinfoc/d;)Ljava/lang/String;
    //   3617: astore_1
    //   3618: invokestatic 405	com/keniu/security/d:a	()Landroid/content/Context;
    //   3621: astore 11
    //   3623: new 177	android/content/Intent
    //   3626: dup
    //   3627: ldc_w 2065
    //   3630: invokespecial 1832	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   3633: astore 12
    //   3635: aload 12
    //   3637: aload 11
    //   3639: invokevirtual 2066	android/content/Context:getPackageName	()Ljava/lang/String;
    //   3642: invokevirtual 2069	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   3645: pop
    //   3646: aload 12
    //   3648: ldc_w 2071
    //   3651: aload_1
    //   3652: invokevirtual 283	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   3655: pop
    //   3656: aload 12
    //   3658: ldc_w 2073
    //   3661: lload 9
    //   3663: invokevirtual 298	android/content/Intent:putExtra	(Ljava/lang/String;J)Landroid/content/Intent;
    //   3666: pop
    //   3667: aload 11
    //   3669: aload 12
    //   3671: invokevirtual 2076	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   3674: return
    //   3675: invokestatic 909	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   3678: ifnull -109 -> 3569
    //   3681: aload 14
    //   3683: new 603	java/io/File
    //   3686: dup
    //   3687: invokestatic 913	com/keniu/security/a:f	()Ljava/lang/String;
    //   3690: ldc_w 915
    //   3693: invokespecial 918	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   3696: putfield 901	com/cleanmaster/kinfoc/d:a	Ljava/io/File;
    //   3699: goto -130 -> 3569
    //   3702: goto -150 -> 3552
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3705	0	this	LocalService
    //   0	3705	1	paramIntent	Intent
    //   4	3265	2	i	int
    //   6	3045	3	j	int
    //   534	2518	4	k	int
    //   295	2822	5	bool1	boolean
    //   326	27	6	bool2	boolean
    //   336	22	7	bool3	boolean
    //   345	24	8	bool4	boolean
    //   1766	1896	9	l	long
    //   1	1590	11	localObject1	Object
    //   1604	3	11	localException1	Exception
    //   1775	1893	11	localObject2	Object
    //   16	3654	12	localObject3	Object
    //   1115	1785	13	localObject4	Object
    //   2977	3	13	localException2	Exception
    //   3367	246	13	localObject5	Object
    //   1236	2446	14	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   443	501	502	java/lang/Exception
    //   1417	1431	1596	java/lang/Exception
    //   1457	1477	1604	java/lang/Exception
    //   2323	2331	2335	finally
    //   2331	2334	2335	finally
    //   2336	2339	2335	finally
    //   2342	2346	2335	finally
    //   2323	2331	2341	java/lang/InterruptedException
    //   2899	2910	2977	java/lang/Exception
  }
  
  public class GetPackageStatObserver
    extends IPackageStatsObserver.Stub
  {
    private List<String> a = null;
    private int b = 0;
    
    public GetPackageStatObserver(int paramInt)
    {
      this.a = paramInt;
      this.b = 1000;
      a();
    }
    
    private void a()
    {
      if ((this.a == null) || (this.a.isEmpty()) || (LocalService.this.d >= this.b)) {
        return;
      }
      try
      {
        LocalService.this.b.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class }).invoke(LocalService.this.b, new Object[] { this.a.get(0), this });
        try
        {
          LocalService localLocalService = LocalService.this;
          localLocalService.d += 1;
          return;
        }
        finally {}
        return;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException.printStackTrace();
        return;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
        return;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        localInvocationTargetException.printStackTrace();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    
    public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
    {
      this.a.remove(paramPackageStats.packageName);
      paramPackageStats = new com.cleanmaster.common.a.m(paramPackageStats);
      paramPackageStats.a = "LocalService";
      if (!this.a.isEmpty()) {
        int i = LocalService.this.d;
      }
      if ((this.a == null) || (this.a.isEmpty()) || (LocalService.this.d >= this.b)) {
        paramPackageStats.i = true;
      }
      LocalService.a(paramPackageStats);
      a();
    }
  }
  
  public static class MovePackageObserver
    extends IPackageMoveObserver.Stub
  {
    private ArrayList<String> a;
    private String b;
    
    public MovePackageObserver(ArrayList<String> paramArrayList, String paramString)
    {
      this.a = paramArrayList;
      this.b = paramString;
    }
    
    public final void a()
    {
      Object localObject;
      if (!this.a.isEmpty())
      {
        localObject = new com.cleanmaster.ui.app.a.f(this.b);
        ((com.cleanmaster.ui.app.a.f)localObject).d = ((String)this.a.get(0));
        LocalService.a((client.core.model.c)localObject);
        if ("move".equals(this.b))
        {
          localObject = com.cm.root.d.a();
          str = (String)this.a.get(0);
        }
      }
      else
      {
        try
        {
          ((com.cm.root.d)localObject).a.a(str, this, com.cm.root.d.a(PackageManager.class, "MOVE_EXTERNAL_MEDIA", 2));
          return;
        }
        catch (RemoteException localRemoteException1)
        {
          localRemoteException1.printStackTrace();
          return;
        }
      }
      com.cm.root.d localD = com.cm.root.d.a();
      String str = (String)this.a.get(0);
      try
      {
        localD.a.a(str, this, com.cm.root.d.a(PackageManager.class, "MOVE_INTERNAL", 1));
        return;
      }
      catch (RemoteException localRemoteException2)
      {
        localRemoteException2.printStackTrace();
      }
    }
    
    public void packageMoved(String paramString, int paramInt)
    {
      this.a.remove(paramString);
      if (i.e) {
        this.a.clear();
      }
      u localU = new u();
      localU.a = this.b;
      localU.d = paramString;
      localU.e = paramInt;
      if (paramInt == -1)
      {
        i.e = false;
        this.a.clear();
      }
      LocalService.a(localU);
      a();
      if ((this.a.isEmpty()) && (paramInt != -1))
      {
        i.e = false;
        LocalService.a(new q(this.b));
      }
    }
  }
  
  final class a
    implements IFilter<PackageInfo>
  {
    a() {}
  }
  
  static final class b
    implements IFilter<PackageInfo>
  {
    private Set<String> a = null;
    
    public b(Collection<? extends String> paramCollection)
    {
      if (!paramCollection.isEmpty())
      {
        this.a = new com.cleanmaster.bitloader.a.b(paramCollection.size());
        this.a.addAll(paramCollection);
      }
    }
  }
  
  public static final class c
    implements IFilter<PackageInfo>
  {
    private Context a = null;
    
    public c(Context paramContext)
    {
      this.a = paramContext;
    }
  }
  
  public static final class d
  {
    d(Context paramContext) {}
  }
  
  final class e
    extends IPackageStatsObserver.Stub
  {
    private InstallMoveInfo a = null;
    
    e(InstallMoveInfo paramInstallMoveInfo)
    {
      this.a = paramInstallMoveInfo;
    }
    
    public final void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
    {
      this.a.e = paramPackageStats.codeSize;
      MonitorInstallActivity.a(LocalService.this, this.a);
    }
  }
  
  static final class f
    implements Comparator<ProcessModel>
  {
    private f() {}
  }
  
  static final class g
    implements Comparator<com.ijinshan.cleaner.bean.g>
  {
    g() {}
  }
  
  public static final class h
    implements IFilter<PackageInfo>
  {
    public h() {}
    
    public static boolean a(PackageInfo paramPackageInfo)
    {
      return com.cleanmaster.base.c.a(paramPackageInfo.applicationInfo);
    }
  }
}
