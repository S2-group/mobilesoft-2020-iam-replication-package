package com.cleanmaster.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.StatFs;
import android.provider.MediaStore.Files;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.cleanmaster.appwidget.MainAppWidgetBlackProvider;
import com.cleanmaster.appwidget.MainAppWidgetWhiteProvider;
import com.cleanmaster.base.util.system.h;
import com.cleanmaster.base.util.system.j;
import com.cleanmaster.base.util.system.j.d;
import com.cleanmaster.base.widget.CustomToast;
import com.cleanmaster.dao.InstallMoveInfo;
import com.cleanmaster.data.filter.IFilter;
import com.cleanmaster.func.cache.e.b;
import com.cleanmaster.notification.normal.NotificationSetting;
import com.cleanmaster.service.LocalService.c;
import com.cleanmaster.service.LocalService.h;
import com.cleanmaster.service.PermanentService;
import com.cleanmaster.synipc.ISyncIpcService;
import com.cleanmaster.ui.app.activity.AppManagerActivity;
import com.cleanmaster.ui.app.provider.download.DownloadService;
import com.cleanmaster.util.INameFilter;
import com.cleanmaster.util.OpLog;
import com.cleanmaster.util.ac;
import com.cleanmaster.util.ah;
import com.cleanmaster.util.aj;
import com.cleanmaster.util.al;
import com.cleanmaster.util.al.c;
import com.cleanmaster.util.al.d;
import com.cleanmaster.util.y;
import com.ijinshan.cleaner.bean.JunkInfoBase;
import com.keniu.security.monitor.MonitorManager;
import com.keniu.security.update.o.b;
import com.keniu.security.update.o.b.1;
import com.keniu.security.update.o.b.2;
import com.keniu.security.util.d.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class c
{
  private static Map<String, String[]> a = null;
  private static volatile int b;
  private static volatile String c;
  private static Boolean d;
  private static Context e;
  private static volatile File f;
  private static Random g;
  private static String h;
  private static int i;
  private static int j;
  private static PackageInfo k = null;
  private static Runnable l = null;
  private static Random m = null;
  private static String[] n = { "asus/WW_a501cg", "Coolpad/Coolpad7620L", "motorola/condor", "OPPO/1105", "OPPO/1107", "OPPO/3007", "OPPO/A31", "OPPO/R2017", "OPPO/R6007", "OPPO/R7007", "OPPO/R831S", "samsung/fortuna3g", "samsung/fortunave3gxx", "samsung/goyawifixx", "samsung/kanas3gxx", "samsung/ms013g", "samsung/serrano", "samsung/t03gxx/t03g:4.4.2", "Sony/D6503/D6503:5", "yulong/Coolpad8720L" };
  
  static
  {
    a = new HashMap();
    a.put("000", new String[] { "com.whatsapp", "com.tencent.mm", "com.lenovo.anyshare.gps", "cn.xender", "com.tencent.mobileqq" });
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "com.lenovo.anyshare.gps";
    arrayOfString[1] = "cn.xender";
    arrayOfString[2] = "com.whatsapp";
    arrayOfString[3] = "com.tencent.mm";
    arrayOfString[4] = "com.tencent.mobileqq";
    a.put("404", arrayOfString);
    a.put("405", arrayOfString);
    a.put("406", arrayOfString);
    a.put("460", new String[] { "com.tencent.mm", "com.tencent.mobileqq", "com.lenovo.anyshare.gps", "cn.xender", "com.whatsapp" });
    b = 0;
    c = null;
    d = null;
    e = null;
    f = null;
    g = new Random(System.currentTimeMillis());
    h = null;
    i = 0;
    j = -1;
    new com.cleanmaster.bitloader.a.b();
  }
  
  public c() {}
  
  public static String A()
  {
    return Integer.valueOf(E()).toString();
  }
  
  public static String B()
  {
    return af();
  }
  
  /* Error */
  public static int C()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: ldc -72
    //   5: invokestatic 187	com/cleanmaster/base/c:i	(Ljava/lang/String;)Ljava/lang/String;
    //   8: astore_1
    //   9: aload_1
    //   10: invokestatic 193	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   13: ifeq +12 -> 25
    //   16: getstatic 107	com/cleanmaster/base/c:i	I
    //   19: istore_0
    //   20: ldc 2
    //   22: monitorexit
    //   23: iload_0
    //   24: ireturn
    //   25: aload_1
    //   26: invokestatic 196	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   29: invokevirtual 199	java/lang/Integer:intValue	()I
    //   32: istore_0
    //   33: goto -13 -> 20
    //   36: astore_1
    //   37: ldc 2
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   19	14	0	i1	int
    //   8	18	1	str	String
    //   36	5	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	20	36	finally
    //   25	33	36	finally
  }
  
  public static boolean D()
  {
    boolean bool = true;
    for (;;)
    {
      int i1;
      try
      {
        com.cleanmaster.configmanager.d localD = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
        if (localD != null)
        {
          i1 = localD.a("always_show_splash", -1);
          if (i1 != -1)
          {
            if (i1 == 1) {
              return bool;
            }
            bool = false;
            continue;
          }
        }
        String str = d.a().a("global", "alwaysShowSplash");
        if (str == null)
        {
          localD.t(0);
          bool = false;
          continue;
        }
        i1 = Integer.valueOf(str).intValue();
        if ((1 == i1) && (localD != null)) {
          localD.t(1);
        } else {
          localD.t(0);
        }
      }
      finally {}
      if (i1 == 0) {
        bool = false;
      }
    }
  }
  
  public static int E()
  {
    for (;;)
    {
      int i1;
      int i2;
      try
      {
        i1 = i;
        if ((i1 != 0) && (200001 != i1) && (200013 != i1) && (100009 != i1)) {
          return i1;
        }
        com.cleanmaster.configmanager.d localD = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
        if (localD != null)
        {
          i2 = localD.a("appChannelId", 0);
          i1 = i2;
          if (i2 != 0)
          {
            i1 = i2;
            if (100000 != i2)
            {
              i1 = i2;
              if (200000 != i2) {
                if (i2 > 0)
                {
                  i1 = i2;
                  if (i2 <= 5) {}
                }
                else
                {
                  i = i2;
                  i1 = i2;
                  continue;
                }
              }
            }
          }
        }
        str = i("cn");
      }
      finally {}
      String str;
      if (!TextUtils.isEmpty(str))
      {
        i2 = Integer.valueOf(str).intValue();
        i = i2;
        i1 = i2;
        if (localObject != null)
        {
          localObject.b("appChannelId", i2);
          i1 = i2;
        }
      }
    }
  }
  
  public static boolean F()
  {
    boolean bool = true;
    int i1 = E();
    if ((i1 == 2010001043) || (i1 == 2010001359) || (i1 == 100009)) {
      bool = false;
    }
    return bool;
  }
  
  public static boolean G()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (E() == 2010000982)
    {
      bool1 = bool2;
      if (com.cleanmaster.boost.process.util.f.b() <= 536870912L) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean H()
  {
    int i1 = 1;
    boolean bool = false;
    if (j >= 0) {
      if (j != 0) {
        bool = true;
      }
    }
    com.cleanmaster.configmanager.d localD;
    String str;
    do
    {
      do
      {
        return bool;
        localD = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a());
        if (localD == null) {
          break;
        }
        int i2 = localD.a("forbidden_notify_update_flag", -1);
        j = i2;
        if (i2 < 0) {
          break;
        }
      } while (j == 0);
      return true;
      str = d.a().a("global", "fnui");
      if (!TextUtils.isEmpty(str)) {
        break;
      }
      j = 0;
    } while (localD == null);
    localD.b("forbidden_notify_update_flag", 0);
    return false;
    if (!str.equals("0"))
    {
      bool = true;
      if (!bool) {
        break label137;
      }
    }
    for (;;)
    {
      j = i1;
      if (localD != null) {
        localD.b("forbidden_notify_update_flag", j);
      }
      return bool;
      bool = false;
      break;
      label137:
      i1 = 0;
    }
  }
  
  public static List<PackageInfo> I()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      List localList = com.cleanmaster.func.cache.e.a().b.a();
      if (localList == null) {
        return localArrayList;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        localIterator = null;
      }
      Iterator localIterator = localIterator.iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == 0) && ((localApplicationInfo.flags & 0x80) == 0)) {
          localArrayList.add(localPackageInfo);
        }
      }
    }
    return localArrayList;
  }
  
  public static int J()
  {
    long l1 = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a().getApplicationContext()).a("CampaignTrackingTime", -1L);
    if (-1L == l1) {
      return -1;
    }
    l1 /= 1000L;
    if (l1 < 0L) {
      return -2;
    }
    if (l1 > 2147483647L) {
      return Integer.MAX_VALUE;
    }
    return (int)l1;
  }
  
  public static void K()
  {
    try
    {
      com.keniu.security.d.b().h();
      Thread.sleep(1500L);
      Process.killProcess(Process.myPid());
      System.exit(10);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static List<PackageInfo> L()
  {
    Object localObject1 = com.keniu.security.d.a().getApplicationContext();
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = ((Context)localObject1).getPackageManager();
    localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    Iterator localIterator = localPackageManager.queryIntentActivities((Intent)localObject1, 0).iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        localObject1 = (ResolveInfo)localIterator.next();
        if (a(((ResolveInfo)localObject1).activityInfo.applicationInfo.packageName, localArrayList)) {
          continue;
        }
        try
        {
          localObject1 = localPackageManager.getPackageInfo(((ResolveInfo)localObject1).activityInfo.applicationInfo.packageName, 0);
          if (localObject1 != null) {
            localArrayList.add(localObject1);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            localNameNotFoundException.printStackTrace();
            Object localObject2 = null;
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static boolean M()
  {
    switch ()
    {
    default: 
      if (!com.cleanmaster.configmanager.d.a(com.keniu.security.d.a().getApplicationContext()).b(com.keniu.security.d.a().getApplicationContext()).M.equals(h.h)) {
        break;
      }
    case 200003: 
    case 200004: 
      return true;
    }
    return false;
  }
  
  public static boolean N()
  {
    long l1 = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).a("LAST_MOVE_INSTALL_APP", 0L);
    if (System.currentTimeMillis() - l1 > 86400000L)
    {
      com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).b("LAST_MOVE_INSTALL_APP", System.currentTimeMillis());
      com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).b("MOVE_INSTALL_TIMES", 1);
      return true;
    }
    if (com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).a("MOVE_INSTALL_TIMES", 0) <= 0)
    {
      com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).b("MOVE_INSTALL_TIMES", com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).a("MOVE_INSTALL_TIMES", 0) + 1);
      return true;
    }
    return false;
  }
  
  public static int O()
  {
    Object localObject = com.keniu.security.update.m.a().g();
    if (localObject == null) {}
    do
    {
      return 0;
      localObject = ((String)localObject).split("\\.");
    } while ((localObject == null) || (4 != localObject.length));
    return ((Integer.valueOf(localObject[0]).intValue() % 100 * 100 + Integer.valueOf(localObject[1]).intValue() % 100) * 100 + Integer.valueOf(localObject[2]).intValue() % 100) * 10000 + Integer.valueOf(localObject[3]).intValue() % 10000;
  }
  
  public static boolean P()
  {
    long l2 = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a().getApplicationContext()).M();
    long l1 = l2;
    if (l2 == -1L) {
      l1 = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).a("cm_first_install_time", 0L);
    }
    return System.currentTimeMillis() - l1 > 259200000L;
  }
  
  public static boolean Q()
  {
    long l2 = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a().getApplicationContext()).M();
    long l1 = l2;
    if (l2 == -1L) {
      l1 = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).a("cm_first_install_time", 0L);
    }
    return System.currentTimeMillis() - l1 > 604800000L;
  }
  
  public static void R()
  {
    Context localContext = com.keniu.security.d.a().getApplicationContext();
    j.a().a(false);
    Object localObject1 = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = com.cleanmaster.ui.app.market.transport.a.b;
    int i2 = localObject2.length;
    int i1 = 0;
    while (i1 < i2)
    {
      Object localObject3 = localObject2[i1];
      Object localObject4 = b(localContext, "type=" + (String)localObject3, com.cleanmaster.ui.app.market.transport.a.a(localContext, (String)localObject3));
      if ((localObject4 != null) && (((List)localObject4).size() > 0))
      {
        localArrayList.add(localObject3);
        localObject3 = ((List)localObject4).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (String)((Iterator)localObject3).next();
          if (!((List)localObject1).contains(localObject4)) {
            ((List)localObject1).add(localObject4);
          }
        }
      }
      i1 += 1;
    }
    if (((List)localObject1).size() <= 0) {
      return;
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Iterator)localObject1).next();
      a(localContext, (String)localObject2, j.d.a(4), 2000L);
      a(localContext, (String)localObject2, j.d.a(localContext), 2000L);
    }
    new HashSet().addAll(localArrayList);
  }
  
  public static void S()
  {
    Context localContext = com.keniu.security.d.a().getApplicationContext();
    com.cleanmaster.boost.onetap.g.a();
    Object localObject1 = com.cleanmaster.boost.onetap.g.c();
    Object localObject3;
    boolean bool;
    try
    {
      localObject3 = localContext.getString(2131296756);
      localObject1 = b(localContext, (String)localObject1, (String)localObject3);
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        com.cleanmaster.boost.onetap.g.a();
        bool = com.cleanmaster.boost.onetap.g.b();
        localObject3 = ((List)localObject1).iterator();
      }
      for (;;)
      {
        if (((Iterator)localObject3).hasNext())
        {
          String str = (String)((Iterator)localObject3).next();
          if (bool)
          {
            localObject1 = j.d.a(7);
            a(localContext, str, (Intent)localObject1, 1000L);
            if (!bool) {
              break label144;
            }
            localObject1 = j.d.a(7);
            label108:
            a(localContext, str, (Intent)localObject1, 1000L);
            continue;
          }
        }
      }
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      localNotFoundException.printStackTrace();
      OpLog.d("shortcut", "fixErrorOneTapShortCut----can not find resources , fix failed");
    }
    Object localObject2;
    label144:
    do
    {
      localObject2 = j.d.a(1);
      break;
      localObject2 = j.d.b(localContext);
      break label108;
      localObject2 = j.a().a(false);
      com.cleanmaster.boost.onetap.g.a();
      localObject3 = com.cleanmaster.boost.onetap.g.c();
    } while (j.a().a((String)localObject2, (String)localObject3));
    OpLog.d("shortcut", "fixErrorOneTapShortCut----isNewOnetap=" + bool);
    j.a(localContext);
  }
  
  public static String T()
  {
    try
    {
      Object localObject1 = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a().getApplicationContext());
      int i2 = ((com.cleanmaster.configmanager.d)localObject1).a("cmidcmidcmid", 0);
      int i1 = i2;
      if (i2 <= 0)
      {
        do
        {
          i1 = (int)(o() * 2.147483647E9D);
        } while (i1 == 0);
        ((com.cleanmaster.configmanager.d)localObject1).b("cmidcmidcmid", i1);
      }
      localObject1 = Integer.toString(i1);
      return localObject1;
    }
    finally {}
  }
  
  public static int U()
  {
    int i1 = 0;
    Context localContext = com.keniu.security.d.a();
    try
    {
      int i2 = Settings.Secure.getInt(localContext.getContentResolver(), "install_non_market_apps", 0);
      if (i2 > 0) {
        i1 = 1;
      }
      return i1;
    }
    catch (Exception localException) {}
    return -1;
  }
  
  public static String V()
  {
    try
    {
      Object localObject1 = new BufferedReader(new FileReader("/proc/version"), 256);
      try
      {
        String str2 = ((BufferedReader)localObject1).readLine();
        ((BufferedReader)localObject1).close();
        localObject1 = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(str2);
        if (!((Matcher)localObject1).matches()) {
          return "Unavailable";
        }
      }
      finally
      {
        ((BufferedReader)localObject1).close();
      }
      if (localIOException.groupCount() >= 4) {
        break label76;
      }
    }
    catch (IOException localIOException)
    {
      return "Unavailable";
    }
    return "Unavailable";
    label76:
    String str1 = localIOException.group(1) + " " + localIOException.group(2) + " " + localIOException.group(3) + " " + localIOException.group(4);
    return str1;
  }
  
  public static boolean W()
  {
    Object localObject = com.keniu.security.d.a().getApplicationContext();
    localObject = com.cleanmaster.configmanager.d.a((Context)localObject).b((Context)localObject);
    return (h.n.equals(((h)localObject).M)) && (h.I.equals(((h)localObject).N));
  }
  
  public static int X()
  {
    if (m == null) {
      m = new Random(System.currentTimeMillis());
    }
    return m.nextInt(100);
  }
  
  public static boolean Y()
  {
    Object localObject = com.keniu.security.d.a().getApplicationContext();
    localObject = com.cleanmaster.configmanager.d.a((Context)localObject).b((Context)localObject);
    return ((((h)localObject).N.equals(h.I)) && (((h)localObject).M.equals(h.n))) || (((h)localObject).M.equals(h.b));
  }
  
  public static void Z()
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      return;
    }
    throw new IllegalStateException("This method must be called from the UI thread.");
  }
  
  public static int a(int paramInt1, int paramInt2)
  {
    if (m == null) {
      m = new Random(System.currentTimeMillis());
    }
    return m.nextInt(paramInt2 - paramInt1) + paramInt1;
  }
  
  public static int a(long paramLong1, long paramLong2)
  {
    if ((paramLong1 < 0L) || (paramLong2 <= 0L)) {
      Log.w("CP", "n:" + paramLong1 + " all:" + paramLong2);
    }
    while (0L == paramLong1) {
      return 0;
    }
    return (int)Math.round(100L * paramLong1 / paramLong2);
  }
  
  public static SpannableString a(String paramString1, String paramString2, Drawable paramDrawable)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)) || (paramDrawable == null)) {
      return null;
    }
    SpannableString localSpannableString = new SpannableString(paramString1);
    int i2 = paramString1.indexOf(paramString2);
    int i1;
    if (i2 != -1)
    {
      ImageSpan localImageSpan = new ImageSpan(paramDrawable, 1);
      i1 = paramString2.length();
      localSpannableString.setSpan(localImageSpan, i2, i2 + i1, 33);
      if (i2 != 0) {
        break label111;
      }
      i1 = i2 + i1;
      i2 = paramString1.length();
    }
    for (;;)
    {
      localSpannableString.setSpan(new com.cleanmaster.base.widget.b(paramDrawable), i1, i2, 33);
      return localSpannableString;
      label111:
      i1 = 0;
    }
  }
  
  public static InstallMoveInfo a(Context paramContext, com.cleanmaster.common.model.a paramA)
  {
    InstallMoveInfo localInstallMoveInfo = new InstallMoveInfo();
    localInstallMoveInfo.f = com.cleanmaster.base.util.system.m.q(paramContext, paramA.a);
    localInstallMoveInfo.d = paramA.b;
    localInstallMoveInfo.a = com.cleanmaster.base.util.system.b.a(paramContext, "android.appwidget.action.APPWIDGET_UPDATE", paramA.b);
    localInstallMoveInfo.c = com.cleanmaster.base.util.system.b.a(paramContext, "android.intent.action.BOOT_COMPLETED", paramA.b);
    localInstallMoveInfo.b = com.cleanmaster.base.util.system.m.a(paramContext, "android.permission.BIND_WALLPAPER", paramA.b);
    return localInstallMoveInfo;
  }
  
  public static String a(long paramLong)
  {
    h localH = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a()).b(com.keniu.security.d.a());
    return com.cleanmaster.base.util.c.a.a(com.keniu.security.d.a().getApplicationContext(), paramLong, localH);
  }
  
  public static String a(Context paramContext, JunkInfoBase paramJunkInfoBase)
  {
    int i3 = 1;
    if ((paramContext == null) || (paramJunkInfoBase == null)) {
      return null;
    }
    if ((paramJunkInfoBase.getAudioNum() <= 0) && (paramJunkInfoBase.getImageNum() <= 0) && (paramJunkInfoBase.getVideoNum() <= 0)) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramJunkInfoBase.getAudioNum() > 0) {
      if (1 != paramJunkInfoBase.getAudioNum())
      {
        i1 = paramJunkInfoBase.getAudioNum();
        if ((!(paramJunkInfoBase instanceof com.ijinshan.cleaner.bean.f)) || (i1 <= ((com.ijinshan.cleaner.bean.f)paramJunkInfoBase).h())) {
          break label391;
        }
        i1 = (int)((com.ijinshan.cleaner.bean.f)paramJunkInfoBase).h();
        localStringBuilder.append(paramContext.getResources().getString(2131296469, new Object[] { Integer.valueOf(i1) }));
      }
    }
    for (int i1 = 1;; i1 = 0)
    {
      int i2 = i1;
      if (paramJunkInfoBase.getImageNum() > 0)
      {
        if (i1 != 0) {
          localStringBuilder.append(", ");
        }
        if (1 == paramJunkInfoBase.getImageNum()) {
          break label337;
        }
        i1 = paramJunkInfoBase.getImageNum();
        if ((!(paramJunkInfoBase instanceof com.ijinshan.cleaner.bean.f)) || (i1 <= ((com.ijinshan.cleaner.bean.f)paramJunkInfoBase).h())) {
          break label388;
        }
        i1 = (int)((com.ijinshan.cleaner.bean.f)paramJunkInfoBase).h();
      }
      label214:
      label278:
      label337:
      label380:
      label388:
      for (;;)
      {
        localStringBuilder.append(paramContext.getResources().getString(2131296471, new Object[] { Integer.valueOf(i1) }));
        i2 = 1;
        if (paramJunkInfoBase.getVideoNum() > 0)
        {
          if (i2 != 0) {
            localStringBuilder.append(", ");
          }
          if (1 != paramJunkInfoBase.getVideoNum())
          {
            i1 = paramJunkInfoBase.getVideoNum();
            if ((!(paramJunkInfoBase instanceof com.ijinshan.cleaner.bean.f)) || (i1 <= ((com.ijinshan.cleaner.bean.f)paramJunkInfoBase).h())) {
              break label380;
            }
            i1 = (int)((com.ijinshan.cleaner.bean.f)paramJunkInfoBase).h();
            localStringBuilder.append(paramContext.getResources().getString(2131296473, new Object[] { Integer.valueOf(i1) }));
            i1 = i3;
          }
        }
        for (;;)
        {
          if (i1 != 0)
          {
            return localStringBuilder.toString();
            localStringBuilder.append(paramContext.getResources().getString(2131296470));
            break;
            localStringBuilder.append(paramContext.getResources().getString(2131296472));
            break label214;
            localStringBuilder.append(paramContext.getResources().getString(2131296474));
            i1 = i3;
            continue;
          }
          return null;
          break label278;
          i1 = i2;
        }
      }
      label391:
      break;
    }
  }
  
  public static String a(Context paramContext, List<com.ijinshan.cleaner.bean.a> paramList)
  {
    if ((paramContext == null) || (paramList == null)) {
      return null;
    }
    com.ijinshan.cleaner.bean.a localA1 = new com.ijinshan.cleaner.bean.a();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      com.ijinshan.cleaner.bean.a localA2 = (com.ijinshan.cleaner.bean.a)paramList.next();
      if ((localA2 != null) && ((localA2.getAudioNum() > 0) || (localA2.getImageNum() > 0) || (localA2.getVideoNum() > 0)))
      {
        if (localA2.getAudioNum() >= 0)
        {
          if (localA1.getAudioNum() < 0) {
            localA1.setAudioNum(localA2.getAudioNum());
          }
        }
        else {
          label91:
          if (localA2.getImageNum() >= 0)
          {
            if (localA1.getImageNum() >= 0) {
              break label154;
            }
            localA1.setImageNum(localA2.getImageNum());
          }
        }
        for (;;)
        {
          if (localA2.getVideoNum() < 0) {
            break label168;
          }
          if (localA1.getVideoNum() >= 0) {
            break label170;
          }
          localA1.setVideoNum(localA2.getVideoNum());
          break;
          localA1.setAudioNum(localA2.getAudioNum() + localA1.getAudioNum());
          break label91;
          label154:
          localA1.setImageNum(localA2.getImageNum() + localA1.getImageNum());
        }
        label168:
        continue;
        label170:
        localA1.setVideoNum(localA2.getVideoNum() + localA1.getVideoNum());
      }
    }
    return a(paramContext, localA1);
  }
  
  public static String a(PackageManager paramPackageManager)
  {
    Iterator localIterator = paramPackageManager.getInstalledPackages(4160).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((!TextUtils.isEmpty(localPackageInfo.sharedUserId)) && (localPackageInfo.sharedUserId.equalsIgnoreCase("android.uid.system")) && (localPackageInfo.signatures != null)) {
        return com.cleanmaster.base.util.system.m.a(paramPackageManager, localPackageInfo.packageName);
      }
    }
    return "";
  }
  
  public static String a(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    return com.cleanmaster.func.cache.d.a().a(paramString1, paramString2, paramInt, paramString3);
  }
  
  public static void a(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    Intent localIntent = new Intent(paramContext, DownloadService.class);
    try
    {
      paramContext.startService(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return;
      Intent localIntent = new Intent(paramContext, PermanentService.class);
      localIntent.putExtra("cheOnAppUsageChangeExck_type", paramInt);
      try
      {
        if (paramContext.startService(localIntent) == null)
        {
          Log.e("Servive", "start service fail");
          return;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static void a(Context paramContext, com.keniu.security.update.o paramO, boolean paramBoolean)
  {
    switch ()
    {
    default: 
      if (!com.cleanmaster.base.util.net.b.c(paramContext))
      {
        paramO = new d.a(paramContext);
        paramO.a(paramContext.getString(2131300862));
        paramO.b(paramContext.getString(2131300868));
        paramO.a(paramContext.getString(2131300863), new DialogInterface.OnClickListener()
        {
          public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            com.cleanmaster.base.util.net.b.d(this.a);
          }
        });
        paramO.f(true);
        paramO.b(paramContext.getString(2131300860), null);
        paramO.h();
        return;
      }
      break;
    case 100009: 
    case 200001: 
    case 200013: 
      a(paramContext, paramBoolean);
      return;
    case 200003: 
      q(paramContext);
      return;
    case 200004: 
      r(paramContext);
      return;
    case 200005: 
      s(paramContext);
      return;
    }
    Object localObject = new com.keniu.security.update.o(paramContext);
    paramO = new o.b((com.keniu.security.update.o)localObject, (byte)0);
    localObject = new d.a(((com.keniu.security.update.o)localObject).a);
    paramO.a = new com.keniu.security.update.i(2);
    ((d.a)localObject).a(2131300869);
    ((d.a)localObject).b(LayoutInflater.from(paramO.c.a).inflate(2130903890, null));
    ((d.a)localObject).b(2131297371, new o.b.1(paramO));
    ((d.a)localObject).a(new o.b.2(paramO));
    ((d.a)localObject).f(true);
    paramO.c.b = ((d.a)localObject).h();
    MonitorManager.a().a(MonitorManager.d, paramO);
    com.keniu.security.update.m.a().a(paramO.a);
    com.cleanmaster.configmanager.d.a(paramContext).b("UpdateDateRecord", System.currentTimeMillis());
  }
  
  public static void a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    localIntent.setFlags(268435456);
    if (!com.cleanmaster.base.util.system.b.a(paramContext, localIntent)) {
      Toast.makeText(paramContext, paramContext.getString(2131299017), 0).show();
    }
  }
  
  public static void a(Context paramContext, String paramString, Intent paramIntent, long paramLong)
  {
    Intent localIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    paramContext.sendBroadcast(localIntent);
    if (paramLong > 0L) {}
    try
    {
      Thread.sleep(paramLong);
      return;
    }
    catch (InterruptedException paramContext) {}
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    com.cleanmaster.notification.e.a();
    com.cleanmaster.notification.e.a(768);
    paramContext = AppManagerActivity.b(paramContext, 2);
    paramContext.putExtra("KEY:TO_MOVE_PAGE", true);
    NotificationSetting localNotificationSetting = new NotificationSetting();
    localNotificationSetting.a = 768;
    localNotificationSetting.f = 2;
    if (e.M()) {
      localNotificationSetting.u = true;
    }
    com.cleanmaster.notification.normal.f localF = new com.cleanmaster.notification.normal.f();
    localF.b = paramString1;
    localF.c = paramString1;
    localF.d = paramString2;
    localF.e = 1;
    localF.q = paramContext;
    com.cleanmaster.notification.e.a().c(localNotificationSetting, localF);
  }
  
  private static void a(Context paramContext, boolean paramBoolean)
  {
    Object localObject = com.keniu.security.a.a();
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setClassName("com.android.vending", "com.google.android.finsky.activities.MainActivity");
    localIntent.setData(Uri.parse("market://details?id=" + (String)localObject));
    if (com.cleanmaster.base.util.system.b.a(paramContext, localIntent)) {
      if ((u(paramContext)) && (paramBoolean)) {
        ag();
      }
    }
    do
    {
      return;
      localObject = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + (String)localObject));
      ((Intent)localObject).setFlags(268435456);
      com.cleanmaster.base.util.system.b.a(paramContext, (Intent)localObject);
    } while ((!u(paramContext)) || (!paramBoolean));
    ag();
  }
  
  public static void a(Intent paramIntent)
  {
    Intent localIntent = null;
    PackageManager localPackageManager = com.keniu.security.d.a().getPackageManager();
    paramIntent.addCategory("android.intent.category.DEFAULT");
    paramIntent = localPackageManager.resolveActivity(paramIntent, 0);
    if ((paramIntent != null) && (paramIntent.activityInfo != null)) {}
    for (paramIntent = paramIntent.activityInfo.packageName;; paramIntent = null)
    {
      if (paramIntent != null) {
        localIntent = localPackageManager.getLaunchIntentForPackage(paramIntent);
      }
      if (localIntent != null) {
        com.cleanmaster.base.util.system.b.a(com.keniu.security.d.a(), localIntent);
      }
      return;
    }
  }
  
  public static void a(File paramFile, com.cleanmaster.b.a.c paramC)
  {
    if (paramFile != null) {
      OpLog.b("DFo", paramFile.getPath());
    }
    a(paramFile, paramC, true);
  }
  
  private static void a(File paramFile, com.cleanmaster.b.a.c paramC, int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0) {}
    al.c localC;
    do
    {
      return;
      f = paramFile;
      localC = al.a(paramFile.getPath());
    } while (localC == null);
    for (;;)
    {
      try
      {
        localD2 = localC.a();
        localD1 = localD2;
        if (localD2 == null) {
          continue;
        }
        localD1 = localD2;
      }
      finally
      {
        al.d localD2;
        Iterator localIterator;
        String str;
        al.d localD1 = null;
        continue;
        int i1 = 0;
        continue;
      }
      try
      {
        if ((localD2.d() <= 0) || (paramInt2 <= 0) || (paramInt2 == 65535)) {
          continue;
        }
        localD1 = localD2;
        if (System.currentTimeMillis() - paramFile.lastModified() >= paramInt2 * 24 * 60 * 60 * 1000) {
          continue;
        }
        i1 = paramInt2;
        localD1 = localD2;
        localIterator = localD2.iterator();
        localD1 = localD2;
        if (!localIterator.hasNext()) {
          continue;
        }
        localD1 = localD2;
        str = (String)localIterator.next();
        localD1 = localD2;
        a(new File(android.support.percent.a.B(paramFile.getPath()) + str), paramC, false, i1);
        continue;
        if (localD1 == null) {
          continue;
        }
      }
      finally {}
    }
    localD1.c();
    localC.c();
    throw paramFile;
    localD1 = localD2;
    localD2.c();
    localD1 = null;
    localD2 = localC.b();
    if (localD2 != null)
    {
      localD1 = localD2;
      localIterator = localD2.iterator();
      for (;;)
      {
        localD1 = localD2;
        if (!localIterator.hasNext()) {
          break;
        }
        localD1 = localD2;
        str = (String)localIterator.next();
        localD1 = localD2;
        a(new File(android.support.percent.a.B(paramFile.getPath()) + str), paramC, paramInt1 - 1, paramInt2);
      }
    }
    if (localD2 != null) {
      localD2.c();
    }
    localC.c();
  }
  
  /* Error */
  private static void a(File paramFile, com.cleanmaster.b.a.c paramC, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aload_1
    //   6: ifnonnull +13 -> 19
    //   9: aload_0
    //   10: aconst_null
    //   11: iconst_1
    //   12: iconst_0
    //   13: invokestatic 1133	com/cleanmaster/base/c:a	(Ljava/io/File;Lcom/cleanmaster/b/a/c;ZI)Z
    //   16: ifne -12 -> 4
    //   19: aload_0
    //   20: invokevirtual 1145	java/io/File:isFile	()Z
    //   23: ifeq +16 -> 39
    //   26: iload_2
    //   27: ifeq -23 -> 4
    //   30: aload_0
    //   31: aload_1
    //   32: iconst_1
    //   33: iconst_0
    //   34: invokestatic 1133	com/cleanmaster/base/c:a	(Ljava/io/File;Lcom/cleanmaster/b/a/c;ZI)Z
    //   37: pop
    //   38: return
    //   39: aload_0
    //   40: iconst_0
    //   41: aload_1
    //   42: iload_2
    //   43: sipush 128
    //   46: iconst_0
    //   47: invokestatic 1148	com/cleanmaster/base/c:a	(Ljava/io/File;ZLcom/cleanmaster/b/a/c;ZII)V
    //   50: aconst_null
    //   51: putstatic 90	com/cleanmaster/base/c:f	Ljava/io/File;
    //   54: aload_0
    //   55: invokevirtual 1151	java/io/File:delete	()Z
    //   58: pop
    //   59: getstatic 1156	android/os/Build$VERSION:SDK_INT	I
    //   62: bipush 19
    //   64: if_icmplt +28 -> 92
    //   67: aload_0
    //   68: invokevirtual 1159	java/io/File:exists	()Z
    //   71: ifeq +21 -> 92
    //   74: new 1161	com/cleanmaster/util/ah
    //   77: dup
    //   78: invokestatic 1163	com/cleanmaster/base/c:i	()Landroid/content/Context;
    //   81: invokevirtual 562	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   84: aload_0
    //   85: invokespecial 1166	com/cleanmaster/util/ah:<init>	(Landroid/content/ContentResolver;Ljava/io/File;)V
    //   88: invokevirtual 1168	com/cleanmaster/util/ah:a	()Z
    //   91: pop
    //   92: aload_1
    //   93: ifnull -89 -> 4
    //   96: aload_1
    //   97: aload_0
    //   98: invokevirtual 1100	java/io/File:getPath	()Ljava/lang/String;
    //   101: invokevirtual 1172	com/cleanmaster/b/a/c:a	(Ljava/lang/String;)V
    //   104: return
    //   105: astore_3
    //   106: aconst_null
    //   107: putstatic 90	com/cleanmaster/base/c:f	Ljava/io/File;
    //   110: goto -56 -> 54
    //   113: astore_0
    //   114: aconst_null
    //   115: putstatic 90	com/cleanmaster/base/c:f	Ljava/io/File;
    //   118: aload_0
    //   119: athrow
    //   120: astore_3
    //   121: aload_3
    //   122: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   125: goto -33 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	paramFile	File
    //   0	128	1	paramC	com.cleanmaster.b.a.c
    //   0	128	2	paramBoolean	boolean
    //   105	1	3	localStackOverflowError	StackOverflowError
    //   120	2	3	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   39	50	105	java/lang/StackOverflowError
    //   39	50	113	finally
    //   74	92	120	java/io/IOException
  }
  
  private static void a(File paramFile, boolean paramBoolean1, com.cleanmaster.b.a.c paramC, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0) {
      return;
    }
    f = paramFile;
    al.c localC = al.a(paramFile.getPath());
    Object localObject1 = null;
    al.d localD = null;
    if (localC != null) {
      if (paramBoolean2) {
        localObject1 = localD;
      }
    }
    for (;;)
    {
      Object localObject2;
      try
      {
        localD = localC.a();
        localObject1 = localD;
        if (localD != null)
        {
          localObject1 = localD;
          if ((localD.d() <= 0) || (paramInt2 <= 0) || (paramInt2 == 65535)) {
            break label413;
          }
          localObject1 = localD;
          if (System.currentTimeMillis() - paramFile.lastModified() >= paramInt2 * 24 * 60 * 60 * 1000) {
            break label413;
          }
          i1 = paramInt2;
          localObject1 = localD;
          localObject2 = localD.iterator();
          localObject1 = localD;
          if (((Iterator)localObject2).hasNext())
          {
            localObject1 = localD;
            String str = (String)((Iterator)localObject2).next();
            localObject1 = localD;
            a(new File(android.support.percent.a.B(paramFile.getPath()) + str), paramC, true, i1);
            continue;
            if (localObject1 == null) {}
          }
        }
      }
      finally {}
      for (;;)
      {
        ((al.d)localObject1).c();
        localC.c();
        throw paramFile;
        localObject1 = localD;
        localD.c();
        localObject1 = null;
        localD = localC.b();
        if (localD != null) {
          try
          {
            localObject1 = localD.iterator();
            for (;;)
            {
              if (((Iterator)localObject1).hasNext())
              {
                localObject2 = (String)((Iterator)localObject1).next();
                a(new File(android.support.percent.a.B(paramFile.getPath()) + (String)localObject2), true, paramC, paramBoolean2, paramInt1 - 1, paramInt2);
                continue;
                break;
              }
            }
          }
          finally
          {
            localObject1 = localD;
          }
        }
      }
      if (localD != null) {
        localD.c();
      }
      localC.c();
      if (!paramBoolean1) {
        break;
      }
      paramFile.delete();
      if ((Build.VERSION.SDK_INT < 19) || (!paramFile.exists())) {
        break;
      }
      try
      {
        new ah(i().getContentResolver(), paramFile).a();
        return;
      }
      catch (IOException paramFile)
      {
        paramFile.printStackTrace();
        return;
      }
      label413:
      int i1 = 0;
    }
  }
  
  public static void a(ArrayList<b> paramArrayList1, ArrayList<b> paramArrayList2, String paramString, INameFilter paramINameFilter, int paramInt)
  {
    if (paramInt <= 0) {}
    al.c localC;
    do
    {
      return;
      localC = al.a(paramString, paramINameFilter);
    } while (localC == null);
    try
    {
      localD2 = localC.a();
      localD1 = localD2;
      if (localD2 == null) {
        break label160;
      }
      if (paramArrayList1 == null) {
        break label146;
      }
      localD1 = localD2;
      try
      {
        localIterator = localD2.iterator();
        for (;;)
        {
          localD1 = localD2;
          if (!localIterator.hasNext()) {
            break;
          }
          localD1 = localD2;
          str = (String)localIterator.next();
          localD1 = localD2;
          paramArrayList1.add(new b(android.support.percent.a.B(paramString) + str));
        }
        if (localD1 == null) {
          break label137;
        }
      }
      finally {}
    }
    finally
    {
      for (;;)
      {
        al.d localD2;
        Iterator localIterator;
        String str;
        label137:
        al.d localD1 = null;
      }
    }
    localD1.c();
    localC.c();
    throw paramArrayList1;
    label146:
    localD1 = localD2;
    localD2.c();
    localD1 = null;
    label160:
    localD2 = localC.b();
    if (localD2 != null)
    {
      localD1 = localD2;
      localIterator = localD2.iterator();
      for (;;)
      {
        localD1 = localD2;
        if (!localIterator.hasNext()) {
          break;
        }
        localD1 = localD2;
        str = (String)localIterator.next();
        localD1 = localD2;
        str = android.support.percent.a.B(paramString) + str;
        if (paramArrayList2 != null)
        {
          localD1 = localD2;
          paramArrayList2.add(new b(str));
        }
        localD1 = localD2;
        a(paramArrayList1, paramArrayList2, str, paramINameFilter, paramInt - 1 - 1);
      }
    }
    if (localD2 != null) {
      localD2.c();
    }
    localC.c();
  }
  
  public static void a(ArrayList<b> paramArrayList1, ArrayList<b> paramArrayList2, List<String> paramList, HashMap<Object, Integer> paramHashMap, int paramInt)
  {
    if (paramInt == 0) {}
    for (;;)
    {
      return;
      paramHashMap = new INameFilter()
      {
        public final boolean accept(String paramAnonymousString1, String paramAnonymousString2, boolean paramAnonymousBoolean)
        {
          if (paramAnonymousBoolean) {
            return true;
          }
          if (this.a.isEmpty()) {
            return this.b;
          }
          paramAnonymousString1 = this.a.entrySet().iterator();
          while (paramAnonymousString1.hasNext())
          {
            Object localObject = (Map.Entry)paramAnonymousString1.next();
            int i = ((Integer)((Map.Entry)localObject).getValue()).intValue();
            if (i == 0)
            {
              localObject = (String)((Map.Entry)localObject).getKey();
              if (com.cleanmaster.base.util.g.d.g(paramAnonymousString2).equals(com.cleanmaster.base.util.g.d.g((String)localObject))) {
                return this.b;
              }
            }
            else if (i == 1)
            {
              localObject = (String)((Map.Entry)localObject).getKey();
              if (com.cleanmaster.base.util.g.d.g(paramAnonymousString2).startsWith(com.cleanmaster.base.util.g.d.g((String)localObject))) {
                return this.b;
              }
            }
            else if (i == 2)
            {
              localObject = (String)((Map.Entry)localObject).getKey();
              if (com.cleanmaster.base.util.g.d.g(paramAnonymousString2).endsWith(com.cleanmaster.base.util.g.d.g((String)localObject))) {
                return this.b;
              }
            }
            else if (i == 3)
            {
              localObject = (String)((Map.Entry)localObject).getKey();
              if ((((String)localObject).length() <= paramAnonymousString2.length()) && (com.cleanmaster.base.util.g.d.g(paramAnonymousString2).contains(com.cleanmaster.base.util.g.d.g((String)localObject)))) {
                return this.b;
              }
            }
            else if ((i == 5) && (((Pattern)((Map.Entry)localObject).getKey()).matcher(paramAnonymousString2).matches()))
            {
              return this.b;
            }
          }
          return !this.b;
        }
        
        public final boolean needState()
        {
          return false;
        }
        
        public final void onFile(String paramAnonymousString, long paramAnonymousLong1, long paramAnonymousLong2) {}
      };
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        f = new File(str);
        a(paramArrayList1, paramArrayList2, str, paramHashMap, paramInt);
      }
    }
  }
  
  public static boolean a(int paramInt)
  {
    return ((paramInt & 0x1) == 0) && ((paramInt & 0x80) == 0);
  }
  
  public static boolean a(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    try
    {
      paramActivity.startActivityForResult(paramIntent, paramInt);
      return true;
    }
    catch (ActivityNotFoundException paramActivity)
    {
      return false;
    }
    catch (SecurityException paramActivity)
    {
      return false;
    }
    catch (NullPointerException paramActivity) {}
    return false;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().resolveActivity(paramIntent, 0) != null;
  }
  
  public static boolean a(Context paramContext, ApplicationInfo paramApplicationInfo)
  {
    boolean bool = false;
    if ((paramApplicationInfo.flags & 0x40000) != 0)
    {
      bool = true;
      return bool;
    }
    paramApplicationInfo = paramApplicationInfo.packageName;
    Object localObject = com.cleanmaster.base.util.system.m.e(paramContext, paramApplicationInfo);
    if (localObject != null)
    {
      localObject = com.cm.root.d.a(localObject, "installLocation");
      if (localObject == null) {}
    }
    for (int i1 = ((Integer)localObject).intValue();; i1 = -2)
    {
      if (i1 != -2) {}
      while ((i1 == 0) || (i1 == 2))
      {
        return true;
        i1 = f(paramContext, paramApplicationInfo);
      }
      if (i1 != -1) {
        break;
      }
      for (;;)
      {
        try
        {
          paramContext = Class.forName("android.app.ActivityThread").getMethod("getPackageManager", new Class[0]).invoke(null, new Object[0]);
          i1 = ((Integer)paramContext.getClass().getMethod("getInstallLocation", new Class[0]).invoke(paramContext, new Object[0])).intValue();
          if (2 == i1)
          {
            bool = true;
            return bool;
          }
        }
        catch (ClassNotFoundException paramContext)
        {
          paramContext.printStackTrace();
          return false;
        }
        catch (NoSuchMethodException paramContext)
        {
          paramContext.printStackTrace();
          return false;
        }
        catch (IllegalArgumentException paramContext)
        {
          paramContext.printStackTrace();
          return false;
        }
        catch (IllegalAccessException paramContext)
        {
          paramContext.printStackTrace();
          return false;
        }
        catch (InvocationTargetException paramContext)
        {
          paramContext.printStackTrace();
          return false;
        }
        bool = false;
      }
    }
  }
  
  public static boolean a(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {}
    while (((paramApplicationInfo.flags & 0x1) != 0) || ((paramApplicationInfo.flags & 0x80) != 0)) {
      return false;
    }
    return true;
  }
  
  public static boolean a(PackageInfo paramPackageInfo)
  {
    boolean bool2 = false;
    try
    {
      if (k == null) {
        k = com.keniu.security.d.a().getPackageManager().getPackageInfo("android", 64);
      }
      boolean bool1 = bool2;
      if (paramPackageInfo != null)
      {
        bool1 = bool2;
        if (paramPackageInfo.signatures != null)
        {
          boolean bool3 = k.signatures[0].equals(paramPackageInfo.signatures[0]);
          bool1 = bool2;
          if (bool3) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramPackageInfo) {}
    return false;
  }
  
  public static boolean a(File paramFile)
  {
    return al.a(paramFile.getPath(), 128, null);
  }
  
  private static boolean a(File paramFile, com.cleanmaster.b.a.c paramC, boolean paramBoolean, int paramInt)
  {
    if (paramC != null)
    {
      if ((paramInt > 0) && (paramInt != 65535) && (System.currentTimeMillis() - paramFile.lastModified() < paramInt * 24 * 60 * 60 * 1000)) {
        return false;
      }
      paramC.a = (paramFile.length() + paramC.a);
      label57:
      if (paramFile.delete()) {
        break label193;
      }
      paramInt = 1;
      label66:
      if ((paramInt == 0) || (Build.VERSION.SDK_INT < 19)) {
        break label208;
      }
    }
    for (;;)
    {
      try
      {
        boolean bool = new ah(i().getContentResolver(), paramFile).a();
        if (!bool)
        {
          paramInt = 1;
          i1 = paramInt;
          if (paramInt != 0)
          {
            i1 = paramInt;
            if (paramBoolean)
            {
              i1 = paramInt;
              if (com.cm.root.d.a().g())
              {
                if (com.cm.root.d.a().i(paramFile.getPath())) {
                  break label211;
                }
                i1 = 1;
              }
            }
          }
          if (i1 != 0) {
            break;
          }
          return true;
          if ((paramInt <= 0) || (paramInt == 65535) || (System.currentTimeMillis() - paramFile.lastModified() >= paramInt * 24 * 60 * 60 * 1000)) {
            break label57;
          }
          return false;
          label193:
          paramInt = 0;
          break label66;
        }
        paramInt = 0;
        continue;
        continue;
      }
      catch (IOException paramC)
      {
        paramC.printStackTrace();
      }
      label208:
      label211:
      int i1 = 0;
    }
  }
  
  public static boolean a(File paramFile1, File paramFile2)
  {
    if (paramFile1.isFile()) {
      return a(paramFile1, paramFile2, null);
    }
    return b(paramFile1, paramFile2, null);
  }
  
  private static boolean a(File paramFile1, File paramFile2, com.cleanmaster.b.a.c paramC)
  {
    boolean bool2;
    if ((paramFile1 == null) || (paramFile2 == null)) {
      bool2 = false;
    }
    boolean bool1;
    do
    {
      return bool2;
      if (paramFile1 != null) {
        OpLog.b("MFi", paramFile1.getPath() + " to " + paramFile2.getPath());
      }
      bool2 = paramFile1.renameTo(paramFile2);
      bool1 = bool2;
      if (!bool2)
      {
        bool1 = bool2;
        if (com.cm.root.d.a().g()) {
          bool1 = com.cm.root.d.a().a(paramFile1.getPath(), paramFile2.getPath());
        }
      }
      bool2 = bool1;
    } while (paramC == null);
    paramC.b(paramFile1.getPath());
    return bool1;
  }
  
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    String str = j.a().a(false);
    return j.a().a(str, paramString);
  }
  
  public static boolean a(String paramString, File paramFile)
  {
    return a(paramString, paramFile, false);
  }
  
  /* Error */
  private static boolean a(String paramString, File paramFile, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 1302	java/io/File:getParentFile	()Ljava/io/File;
    //   4: astore_3
    //   5: aload_3
    //   6: invokevirtual 1159	java/io/File:exists	()Z
    //   9: ifne +12 -> 21
    //   12: aload_3
    //   13: invokevirtual 1305	java/io/File:mkdirs	()Z
    //   16: ifeq +5 -> 21
    //   19: iconst_0
    //   20: ireturn
    //   21: new 1307	java/io/FileWriter
    //   24: dup
    //   25: aload_1
    //   26: iconst_0
    //   27: invokespecial 1310	java/io/FileWriter:<init>	(Ljava/io/File;Z)V
    //   30: astore_1
    //   31: aload_1
    //   32: aload_0
    //   33: invokevirtual 1313	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   36: aload_1
    //   37: invokevirtual 1316	java/io/FileWriter:flush	()V
    //   40: aload_1
    //   41: invokevirtual 1317	java/io/FileWriter:close	()V
    //   44: iconst_1
    //   45: ireturn
    //   46: astore_0
    //   47: aload_0
    //   48: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   51: iconst_0
    //   52: ireturn
    //   53: astore_0
    //   54: aload_0
    //   55: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   58: goto -14 -> 44
    //   61: astore_0
    //   62: aload_0
    //   63: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   66: aload_1
    //   67: invokevirtual 1317	java/io/FileWriter:close	()V
    //   70: goto -26 -> 44
    //   73: astore_0
    //   74: aload_0
    //   75: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   78: goto -34 -> 44
    //   81: astore_0
    //   82: aload_1
    //   83: invokevirtual 1317	java/io/FileWriter:close	()V
    //   86: aload_0
    //   87: athrow
    //   88: astore_1
    //   89: aload_1
    //   90: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   93: goto -7 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	paramString	String
    //   0	96	1	paramFile	File
    //   0	96	2	paramBoolean	boolean
    //   4	9	3	localFile	File
    // Exception table:
    //   from	to	target	type
    //   21	36	46	java/io/IOException
    //   40	44	53	java/io/IOException
    //   36	40	61	java/io/IOException
    //   66	70	73	java/io/IOException
    //   36	40	81	finally
    //   62	66	81	finally
    //   82	86	88	java/io/IOException
  }
  
  private static boolean a(String paramString, List<PackageInfo> paramList)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      if (paramString.equals(((PackageInfo)paramList.next()).packageName)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(List<String> paramList, y paramY)
  {
    if (((paramY.a() & 0x1) == 0) && (!paramList.isEmpty())) {
      OpLog.b("NDFo", (String)paramList.get(0));
    }
    int[] arrayOfInt = new int[6];
    Arrays.fill(arrayOfInt, 0);
    int i3 = paramY.b();
    int i2 = paramY.c();
    List localList1 = paramY.d();
    List localList2 = paramY.e();
    List localList3 = paramY.g();
    List localList4 = paramY.f();
    ArrayList localArrayList = new f().b();
    int i1 = i2;
    if (i2 == 65535) {
      i1 = 0;
    }
    boolean bool = al.a(arrayOfInt, paramList, i3, i1, localList1, localList2, localList3, localList4, new ac()
    {
      private HashMap<String, c.a> a = new HashMap();
      
      public final void a(int paramAnonymousInt) {}
      
      public final void a(String paramAnonymousString)
      {
        if (paramAnonymousString != null) {
          this.a.put(paramAnonymousString, new c.a(paramAnonymousString, this.b));
        }
      }
      
      public final void a(String paramAnonymousString, long paramAnonymousLong, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (this.b != null) {
          this.b.a(paramAnonymousString, paramAnonymousLong);
        }
      }
      
      public final void a(String paramAnonymousString1, String paramAnonymousString2)
      {
        if (paramAnonymousString1 == null) {
          try
          {
            new ah(c.i().getContentResolver(), new File(paramAnonymousString2)).a();
            return;
          }
          catch (IOException paramAnonymousString1)
          {
            paramAnonymousString1.printStackTrace();
            return;
          }
        }
        ((c.a)this.a.get(paramAnonymousString1)).a(paramAnonymousString2);
      }
      
      public final void a(String paramAnonymousString, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2, int paramAnonymousInt)
      {
        if (this.b != null) {
          this.b.a(paramAnonymousString, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousInt);
        }
      }
      
      public final boolean a(String paramAnonymousString, long paramAnonymousLong)
      {
        if (this.b != null) {
          return this.b.b(paramAnonymousString, paramAnonymousLong);
        }
        return true;
      }
      
      public final void b(String paramAnonymousString)
      {
        if (paramAnonymousString != null)
        {
          ((c.a)this.a.get(paramAnonymousString)).a(false);
          this.a.remove(paramAnonymousString);
        }
      }
      
      public final void b(String paramAnonymousString1, String paramAnonymousString2)
      {
        if (paramAnonymousString1 != null) {
          ((c.a)this.a.get(paramAnonymousString1)).b(paramAnonymousString2);
        }
      }
      
      public final void onFeedback(String paramAnonymousString1, String paramAnonymousString2, long paramAnonymousLong)
      {
        if (this.b != null) {
          this.b.a(paramAnonymousString1, paramAnonymousString2, paramAnonymousLong);
        }
      }
    }, h(), Environment.getExternalStorageDirectory().getAbsolutePath(), null, localArrayList, false);
    if ((paramY.a() & 0x2) == 2) {
      paramY.a(arrayOfInt[1], arrayOfInt[2], arrayOfInt[3], arrayOfInt[4], arrayOfInt[5]);
    }
    return bool;
  }
  
  public static boolean a(boolean paramBoolean)
  {
    if (!LibcoreWrapper.a.cq()) {}
    do
    {
      do
      {
        do
        {
          return true;
          try
          {
            str = LibcoreWrapper.a.p(com.keniu.security.d.a());
            i1 = Integer.parseInt(str.substring(str.length() - 3, str.length() - 2), 16);
            if (i1 != 4) {
              break;
            }
            bool = true;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              String str;
              int i1;
              long l1;
              localException.printStackTrace();
              boolean bool = false;
            }
          }
        } while (!android.support.v4.a.c.a("low_performance_policy_enable", bool, "selection_low_performance_policy"));
        str = com.cleanmaster.base.util.net.b.t(com.keniu.security.d.a());
      } while (((paramBoolean) && (str != null) && ("404,405".contains(str))) || (com.cleanmaster.boost.process.util.f.b() / 1024L > 1048576L));
      l1 = LibcoreWrapper.a.cr();
      i1 = android.support.v4.a.c.a("low_performance_new_user", 72, "selection_low_performance_policy");
    } while (System.currentTimeMillis() - l1 >= i1 * 60 * 60 * 1000L);
    return false;
  }
  
  public static String[] a()
  {
    Object localObject1 = com.cleanmaster.base.util.net.b.t(com.keniu.security.d.a());
    Object localObject2 = com.cleanmaster.func.cache.e.a().b.a();
    if ((localObject2 == null) || (((List)localObject2).size() < 10)) {
      return null;
    }
    Object localObject3 = android.support.v4.app.b.a("junk_scan_eng_bg_setting", "junk_scan_bg_special_apps", "");
    if (!TextUtils.isEmpty((CharSequence)localObject3)) {
      localObject1 = ((String)localObject3).split(";");
    }
    for (;;)
    {
      localObject3 = new HashSet(((List)localObject2).size());
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((Set)localObject3).add(((PackageInfo)((Iterator)localObject2).next()).packageName);
      }
      if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (a.containsKey(localObject1))) {
        localObject1 = (String[])a.get(localObject1);
      } else {
        localObject1 = (String[])a.get("000");
      }
    }
    localObject2 = new ArrayList(localObject1.length);
    int i2 = localObject1.length;
    int i1 = 0;
    while (i1 < i2)
    {
      CharSequence localCharSequence = localObject1[i1];
      if ((!TextUtils.isEmpty(localCharSequence)) && (((Set)localObject3).contains(localCharSequence))) {
        ((List)localObject2).add(localCharSequence);
      }
      i1 += 1;
    }
    if (((List)localObject2).size() > 0) {
      return (String[])((List)localObject2).toArray(new String[((List)localObject2).size()]);
    }
    return null;
  }
  
  public static boolean aa()
  {
    if (!e.j())
    {
      long l2 = com.cleanmaster.base.util.system.m.a(com.cleanmaster.base.util.system.m.c(com.keniu.security.d.a(), com.keniu.security.d.a().getPackageName()));
      int i2 = LibcoreWrapper.a.c("switch", "preinstall_avoid_time_" + i, 0);
      int i1 = i2;
      if (i2 == 0) {
        i1 = LibcoreWrapper.a.c("switch", "preinstall_avoid_time", 65535);
      }
      long l1 = l2;
      if (l2 <= 0L) {
        l1 = System.currentTimeMillis();
      }
      if (System.currentTimeMillis() - l1 < i1 * 24 * 60 * 60 * 1000L)
      {
        Log.i("OEM", "Blocked the pre-install notification, Limit --> " + i1 + " Used --> " + (System.currentTimeMillis() - l1) / 86400000L);
        return true;
      }
      Log.e("OEM", "Let off the pre-install notification, Limit --> " + i1 + " Used--> " + (System.currentTimeMillis() - l1) / 86400000L);
    }
    return false;
  }
  
  public static int ab()
  {
    try
    {
      Display localDisplay = ah().getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(localDisplay, new Object[] { localDisplayMetrics });
      int i1 = localDisplayMetrics.heightPixels;
      return i1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  public static int ac()
  {
    try
    {
      Display localDisplay = ah().getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[] { DisplayMetrics.class }).invoke(localDisplay, new Object[] { localDisplayMetrics });
      int i1 = localDisplayMetrics.widthPixels;
      return i1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  private static File ad()
  {
    Object localObject = t();
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      localObject = new File((String)localObject);
      if (((File)localObject).exists()) {
        return localObject;
      }
    }
    return null;
  }
  
  /* Error */
  private static boolean ae()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: iconst_0
    //   3: istore_0
    //   4: aconst_null
    //   5: astore_2
    //   6: aconst_null
    //   7: astore 4
    //   9: aload_2
    //   10: astore_3
    //   11: new 1514	java/util/Properties
    //   14: dup
    //   15: invokespecial 1515	java/util/Properties:<init>	()V
    //   18: astore 5
    //   20: aload_2
    //   21: astore_3
    //   22: new 1517	java/io/FileInputStream
    //   25: dup
    //   26: new 1097	java/io/File
    //   29: dup
    //   30: invokestatic 1520	android/os/Environment:getRootDirectory	()Ljava/io/File;
    //   33: ldc_w 1522
    //   36: invokespecial 1525	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   39: invokespecial 1528	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   42: astore_2
    //   43: aload_2
    //   44: astore_3
    //   45: aload 5
    //   47: aload_2
    //   48: invokevirtual 1532	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   51: aload_2
    //   52: astore_3
    //   53: aload 5
    //   55: ldc_w 1534
    //   58: aconst_null
    //   59: invokevirtual 1537	java/util/Properties:getProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   62: ifnonnull +35 -> 97
    //   65: aload_2
    //   66: astore_3
    //   67: aload 5
    //   69: ldc_w 1539
    //   72: aconst_null
    //   73: invokevirtual 1537	java/util/Properties:getProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   76: ifnonnull +21 -> 97
    //   79: aload_2
    //   80: astore_3
    //   81: aload 5
    //   83: ldc_w 1541
    //   86: aconst_null
    //   87: invokevirtual 1537	java/util/Properties:getProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   90: astore 4
    //   92: aload 4
    //   94: ifnull +5 -> 99
    //   97: iconst_1
    //   98: istore_0
    //   99: aload_2
    //   100: invokevirtual 1542	java/io/FileInputStream:close	()V
    //   103: iload_0
    //   104: ireturn
    //   105: astore_2
    //   106: aload_2
    //   107: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   110: iload_0
    //   111: ireturn
    //   112: astore 4
    //   114: aconst_null
    //   115: astore_2
    //   116: aload_2
    //   117: astore_3
    //   118: aload 4
    //   120: invokevirtual 1543	java/io/FileNotFoundException:printStackTrace	()V
    //   123: iload_1
    //   124: istore_0
    //   125: aload_2
    //   126: ifnull -23 -> 103
    //   129: aload_2
    //   130: invokevirtual 1542	java/io/FileInputStream:close	()V
    //   133: iconst_0
    //   134: ireturn
    //   135: astore_2
    //   136: aload_2
    //   137: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   140: iconst_0
    //   141: ireturn
    //   142: astore_3
    //   143: aload 4
    //   145: astore_2
    //   146: aload_3
    //   147: astore 4
    //   149: aload_2
    //   150: astore_3
    //   151: aload 4
    //   153: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   156: iload_1
    //   157: istore_0
    //   158: aload_2
    //   159: ifnull -56 -> 103
    //   162: aload_2
    //   163: invokevirtual 1542	java/io/FileInputStream:close	()V
    //   166: iconst_0
    //   167: ireturn
    //   168: astore_2
    //   169: aload_2
    //   170: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   173: iconst_0
    //   174: ireturn
    //   175: astore_2
    //   176: aload_3
    //   177: ifnull +7 -> 184
    //   180: aload_3
    //   181: invokevirtual 1542	java/io/FileInputStream:close	()V
    //   184: aload_2
    //   185: athrow
    //   186: astore_3
    //   187: aload_3
    //   188: invokevirtual 1173	java/io/IOException:printStackTrace	()V
    //   191: goto -7 -> 184
    //   194: astore_2
    //   195: goto -19 -> 176
    //   198: astore 4
    //   200: goto -51 -> 149
    //   203: astore 4
    //   205: goto -89 -> 116
    // Local variable table:
    //   start	length	slot	name	signature
    //   3	155	0	bool1	boolean
    //   1	156	1	bool2	boolean
    //   5	95	2	localFileInputStream	java.io.FileInputStream
    //   105	2	2	localIOException1	IOException
    //   115	15	2	localObject1	Object
    //   135	2	2	localIOException2	IOException
    //   145	18	2	localObject2	Object
    //   168	2	2	localIOException3	IOException
    //   175	10	2	localObject3	Object
    //   194	1	2	localObject4	Object
    //   10	108	3	localObject5	Object
    //   142	5	3	localIOException4	IOException
    //   150	31	3	localObject6	Object
    //   186	2	3	localIOException5	IOException
    //   7	86	4	str	String
    //   112	32	4	localFileNotFoundException1	java.io.FileNotFoundException
    //   147	5	4	localObject7	Object
    //   198	1	4	localIOException6	IOException
    //   203	1	4	localFileNotFoundException2	java.io.FileNotFoundException
    //   18	64	5	localProperties	java.util.Properties
    // Exception table:
    //   from	to	target	type
    //   99	103	105	java/io/IOException
    //   11	20	112	java/io/FileNotFoundException
    //   22	43	112	java/io/FileNotFoundException
    //   129	133	135	java/io/IOException
    //   11	20	142	java/io/IOException
    //   22	43	142	java/io/IOException
    //   162	166	168	java/io/IOException
    //   11	20	175	finally
    //   22	43	175	finally
    //   151	156	175	finally
    //   180	184	186	java/io/IOException
    //   45	51	194	finally
    //   53	65	194	finally
    //   67	79	194	finally
    //   81	92	194	finally
    //   118	123	194	finally
    //   45	51	198	java/io/IOException
    //   53	65	198	java/io/IOException
    //   67	79	198	java/io/IOException
    //   81	92	198	java/io/IOException
    //   45	51	203	java/io/FileNotFoundException
    //   53	65	203	java/io/FileNotFoundException
    //   67	79	203	java/io/FileNotFoundException
    //   81	92	203	java/io/FileNotFoundException
  }
  
  /* Error */
  private static String af()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 105	com/cleanmaster/base/c:h	Ljava/lang/String;
    //   6: invokestatic 193	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: ifne +12 -> 21
    //   12: getstatic 105	com/cleanmaster/base/c:h	Ljava/lang/String;
    //   15: astore_0
    //   16: ldc 2
    //   18: monitorexit
    //   19: aload_0
    //   20: areturn
    //   21: invokestatic 206	com/keniu/security/d:a	()Landroid/content/Context;
    //   24: invokestatic 211	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   27: astore_0
    //   28: aload_0
    //   29: ifnull +32 -> 61
    //   32: aload_0
    //   33: ldc_w 1545
    //   36: ldc_w 818
    //   39: invokevirtual 1546	com/cleanmaster/configmanager/d:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   42: astore_1
    //   43: aload_1
    //   44: putstatic 105	com/cleanmaster/base/c:h	Ljava/lang/String;
    //   47: aload_1
    //   48: invokestatic 193	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   51: ifne +10 -> 61
    //   54: getstatic 105	com/cleanmaster/base/c:h	Ljava/lang/String;
    //   57: astore_0
    //   58: goto -42 -> 16
    //   61: ldc_w 1548
    //   64: invokestatic 187	com/cleanmaster/base/c:i	(Ljava/lang/String;)Ljava/lang/String;
    //   67: astore_1
    //   68: aload_1
    //   69: putstatic 105	com/cleanmaster/base/c:h	Ljava/lang/String;
    //   72: aload_1
    //   73: invokestatic 193	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   76: ifne +20 -> 96
    //   79: aload_0
    //   80: ldc_w 1545
    //   83: getstatic 105	com/cleanmaster/base/c:h	Ljava/lang/String;
    //   86: invokevirtual 1549	com/cleanmaster/configmanager/d:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: getstatic 105	com/cleanmaster/base/c:h	Ljava/lang/String;
    //   92: astore_0
    //   93: goto -77 -> 16
    //   96: ldc_w 260
    //   99: putstatic 105	com/cleanmaster/base/c:h	Ljava/lang/String;
    //   102: goto -13 -> 89
    //   105: astore_0
    //   106: ldc 2
    //   108: monitorexit
    //   109: aload_0
    //   110: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   15	78	0	localObject1	Object
    //   105	5	0	localObject2	Object
    //   42	31	1	str	String
    // Exception table:
    //   from	to	target	type
    //   3	16	105	finally
    //   21	28	105	finally
    //   32	58	105	finally
    //   61	89	105	finally
    //   89	93	105	finally
    //   96	102	105	finally
  }
  
  private static void ag()
  {
    if (l == null) {
      l = new Runnable()
      {
        public final void run()
        {
          String str = com.cleanmaster.base.util.system.m.d(com.keniu.security.d.a().getApplicationContext());
          if ((Build.VERSION.SDK_INT >= 11) && ("com.android.vending".equals(str)))
          {
            if (com.keniu.security.d.b().a != null) {
              com.keniu.security.d.b().a.cancel();
            }
            com.keniu.security.d.b().a = new CustomToast(com.keniu.security.d.a());
            com.keniu.security.d.b().a.setGravity(119, 0, 0);
            com.keniu.security.d.b().a.setDuration(1);
            com.keniu.security.d.b().a.a();
            com.keniu.security.d.b().a.show();
          }
        }
      };
    }
    com.keniu.security.d.b().f().postDelayed(l, 1000L);
  }
  
  private static WindowManager ah()
  {
    return (WindowManager)com.keniu.security.d.a().getSystemService("window");
  }
  
  public static int b()
  {
    if (b == 0) {}
    try
    {
      if (b == 0) {
        b = android.support.v4.app.b.a("section_junk_other", "subkey_other_delete_task_show_progressdialog_valve", 100);
      }
      return b;
    }
    finally {}
  }
  
  public static int b(int paramInt)
  {
    try
    {
      int i1 = LibcoreWrapper.a.q(com.keniu.security.d.a());
      return i1 % paramInt;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public static int b(Context paramContext, Intent paramIntent)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramIntent.addCategory("android.intent.category.DEFAULT");
    paramContext = null;
    try
    {
      paramIntent = localPackageManager.queryIntentActivities(paramIntent, 0);
      paramContext = paramIntent;
    }
    catch (Exception paramIntent)
    {
      for (;;)
      {
        paramIntent.printStackTrace();
      }
    }
    if (paramContext == null) {
      return 0;
    }
    return paramContext.size();
  }
  
  public static Intent b(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    localIntent.setFlags(268435456);
    return localIntent;
  }
  
  public static List<String> b(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    boolean bool = false;
    localObject1 = j.a().a(false);
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      bool = ((String)localObject1).startsWith("com.sec.android.app.");
    }
    if (!bool) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    localObject1 = localObject3;
    localObject2 = localObject4;
    try
    {
      Object localObject5 = com.cleanmaster.base.util.system.m.w(paramContext, "com.sec.android.app.launcher");
      localObject1 = localObject3;
      localObject2 = localObject4;
      if (TextUtils.isEmpty((CharSequence)localObject5)) {
        return null;
      }
      localObject1 = localObject3;
      localObject2 = localObject4;
      localObject5 = Uri.parse("content://" + (String)localObject5 + "/favorites?notify=true");
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramContext = paramContext.getContentResolver();
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramString1 = "%" + paramString1 + "%";
      localObject1 = localObject3;
      localObject2 = localObject4;
      paramContext = paramContext.query((Uri)localObject5, new String[] { "title", "intent" }, "intent like ?", new String[] { paramString1 }, null);
      if (paramContext != null)
      {
        localObject1 = paramContext;
        localObject2 = paramContext;
        if (paramContext.moveToFirst()) {
          do
          {
            localObject1 = paramContext;
            localObject2 = paramContext;
            if (!paramContext.getString(0).equals(paramString2))
            {
              localObject1 = paramContext;
              localObject2 = paramContext;
              localArrayList.add(paramContext.getString(0));
            }
            localObject1 = paramContext;
            localObject2 = paramContext;
            paramContext.getString(0);
            localObject1 = paramContext;
            localObject2 = paramContext;
            bool = paramContext.moveToNext();
          } while (bool);
        }
      }
      if (paramContext != null) {
        paramContext.close();
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        if (localObject1 != null) {
          ((Cursor)localObject1).close();
        }
      }
    }
    finally
    {
      if (localObject2 == null) {
        break label342;
      }
      ((Cursor)localObject2).close();
    }
    return localArrayList;
  }
  
  public static void b(Context paramContext)
  {
    LibcoreWrapper.a.a(com.cleanmaster.configmanager.d.a(paramContext).b(paramContext), paramContext);
  }
  
  public static void b(File paramFile)
  {
    c(paramFile);
  }
  
  public static void b(File paramFile, com.cleanmaster.b.a.c paramC)
  {
    OpLog.b("DEFo", paramFile.getPath());
    a(paramFile, paramC, false);
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {}
    List localList;
    do
    {
      return false;
      int i1 = Build.VERSION.SDK_INT;
      paramString = c(paramString);
      paramString.setFlags(268435456);
      localList = paramContext.getPackageManager().queryIntentActivities(paramString, 65536);
    } while ((localList == null) || (localList.size() == 0));
    return com.cleanmaster.base.util.system.b.a(paramContext, paramString);
  }
  
  public static boolean b(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {}
    while (((paramApplicationInfo.flags & 0x1) == 0) && ((paramApplicationInfo.flags & 0x80) == 0)) {
      return false;
    }
    return true;
  }
  
  /* Error */
  private static boolean b(File paramFile1, File paramFile2, com.cleanmaster.b.a.c paramC)
  {
    // Byte code:
    //   0: aload_0
    //   1: putstatic 90	com/cleanmaster/base/c:f	Ljava/io/File;
    //   4: iconst_0
    //   5: istore 5
    //   7: aload_0
    //   8: invokevirtual 1100	java/io/File:getPath	()Ljava/lang/String;
    //   11: invokestatic 1111	com/cleanmaster/util/al:a	(Ljava/lang/String;)Lcom/cleanmaster/util/al$c;
    //   14: astore 9
    //   16: aload 9
    //   18: ifnull +406 -> 424
    //   21: iconst_1
    //   22: istore 4
    //   24: iconst_1
    //   25: istore_3
    //   26: aload 9
    //   28: invokeinterface 1116 1 0
    //   33: astore 6
    //   35: aload 6
    //   37: ifnull +479 -> 516
    //   40: aload 6
    //   42: astore 7
    //   44: iload 4
    //   46: istore_3
    //   47: aload_1
    //   48: invokevirtual 1305	java/io/File:mkdirs	()Z
    //   51: iconst_1
    //   52: iand
    //   53: istore 4
    //   55: aload 6
    //   57: astore 7
    //   59: iload 4
    //   61: istore_3
    //   62: aload 6
    //   64: invokeinterface 1125 1 0
    //   69: astore 10
    //   71: iload 4
    //   73: istore_3
    //   74: aload 6
    //   76: astore 7
    //   78: aload 6
    //   80: astore 8
    //   82: iload_3
    //   83: istore 4
    //   85: aload 10
    //   87: invokeinterface 296 1 0
    //   92: ifeq +111 -> 203
    //   95: aload 6
    //   97: astore 7
    //   99: aload 6
    //   101: astore 8
    //   103: iload_3
    //   104: istore 4
    //   106: aload 10
    //   108: invokeinterface 300 1 0
    //   113: checkcast 56	java/lang/String
    //   116: astore 11
    //   118: aload 6
    //   120: astore 7
    //   122: aload 6
    //   124: astore 8
    //   126: iload_3
    //   127: istore 4
    //   129: iload_3
    //   130: new 1097	java/io/File
    //   133: dup
    //   134: new 462	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 753	java/lang/StringBuilder:<init>	()V
    //   141: aload_0
    //   142: invokevirtual 1100	java/io/File:getPath	()Ljava/lang/String;
    //   145: invokestatic 1129	android/support/percent/a:B	(Ljava/lang/String;)Ljava/lang/String;
    //   148: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 11
    //   153: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 472	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokespecial 1130	java/io/File:<init>	(Ljava/lang/String;)V
    //   162: new 1097	java/io/File
    //   165: dup
    //   166: new 462	java/lang/StringBuilder
    //   169: dup
    //   170: invokespecial 753	java/lang/StringBuilder:<init>	()V
    //   173: aload_1
    //   174: invokevirtual 1100	java/io/File:getPath	()Ljava/lang/String;
    //   177: invokestatic 1129	android/support/percent/a:B	(Ljava/lang/String;)Ljava/lang/String;
    //   180: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: aload 11
    //   185: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: invokevirtual 472	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   191: invokespecial 1130	java/io/File:<init>	(Ljava/lang/String;)V
    //   194: aload_2
    //   195: invokestatic 1282	com/cleanmaster/base/c:a	(Ljava/io/File;Ljava/io/File;Lcom/cleanmaster/b/a/c;)Z
    //   198: iand
    //   199: istore_3
    //   200: goto -126 -> 74
    //   203: aload 6
    //   205: astore 7
    //   207: aload 6
    //   209: astore 8
    //   211: iload_3
    //   212: istore 4
    //   214: aload 6
    //   216: invokeinterface 1135 1 0
    //   221: aconst_null
    //   222: astore 6
    //   224: aload 9
    //   226: invokeinterface 1138 1 0
    //   231: astore 7
    //   233: aload 7
    //   235: astore 6
    //   237: iload_3
    //   238: istore 5
    //   240: aload 6
    //   242: ifnull +159 -> 401
    //   245: aload 6
    //   247: astore 7
    //   249: aload 6
    //   251: astore 8
    //   253: iload_3
    //   254: istore 4
    //   256: aload 6
    //   258: invokeinterface 1125 1 0
    //   263: astore 10
    //   265: iload_3
    //   266: istore 5
    //   268: aload 6
    //   270: astore 7
    //   272: aload 6
    //   274: astore 8
    //   276: iload_3
    //   277: istore 4
    //   279: aload 10
    //   281: invokeinterface 296 1 0
    //   286: ifeq +115 -> 401
    //   289: aload 6
    //   291: astore 7
    //   293: aload 6
    //   295: astore 8
    //   297: iload_3
    //   298: istore 4
    //   300: aload 10
    //   302: invokeinterface 300 1 0
    //   307: checkcast 56	java/lang/String
    //   310: astore 11
    //   312: aload 6
    //   314: astore 7
    //   316: aload 6
    //   318: astore 8
    //   320: iload_3
    //   321: istore 4
    //   323: new 1097	java/io/File
    //   326: dup
    //   327: new 462	java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial 753	java/lang/StringBuilder:<init>	()V
    //   334: aload_0
    //   335: invokevirtual 1100	java/io/File:getPath	()Ljava/lang/String;
    //   338: invokestatic 1129	android/support/percent/a:B	(Ljava/lang/String;)Ljava/lang/String;
    //   341: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: aload 11
    //   346: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: invokevirtual 472	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: invokespecial 1130	java/io/File:<init>	(Ljava/lang/String;)V
    //   355: new 1097	java/io/File
    //   358: dup
    //   359: new 462	java/lang/StringBuilder
    //   362: dup
    //   363: invokespecial 753	java/lang/StringBuilder:<init>	()V
    //   366: aload_1
    //   367: invokevirtual 1100	java/io/File:getPath	()Ljava/lang/String;
    //   370: invokestatic 1129	android/support/percent/a:B	(Ljava/lang/String;)Ljava/lang/String;
    //   373: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: aload 11
    //   378: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: invokevirtual 472	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   384: invokespecial 1130	java/io/File:<init>	(Ljava/lang/String;)V
    //   387: aload_2
    //   388: invokestatic 1284	com/cleanmaster/base/c:b	(Ljava/io/File;Ljava/io/File;Lcom/cleanmaster/b/a/c;)Z
    //   391: istore 5
    //   393: iload_3
    //   394: iload 5
    //   396: iand
    //   397: istore_3
    //   398: goto -133 -> 265
    //   401: aload 6
    //   403: ifnull +10 -> 413
    //   406: aload 6
    //   408: invokeinterface 1135 1 0
    //   413: aload 9
    //   415: invokeinterface 1136 1 0
    //   420: aconst_null
    //   421: putstatic 90	com/cleanmaster/base/c:f	Ljava/io/File;
    //   424: iload 5
    //   426: ireturn
    //   427: astore_0
    //   428: aconst_null
    //   429: astore 6
    //   431: aload 6
    //   433: ifnull +10 -> 443
    //   436: aload 6
    //   438: invokeinterface 1135 1 0
    //   443: aload 9
    //   445: invokeinterface 1136 1 0
    //   450: aconst_null
    //   451: putstatic 90	com/cleanmaster/base/c:f	Ljava/io/File;
    //   454: iload_3
    //   455: ireturn
    //   456: astore_0
    //   457: aconst_null
    //   458: astore 7
    //   460: aload 7
    //   462: ifnull +10 -> 472
    //   465: aload 7
    //   467: invokeinterface 1135 1 0
    //   472: aload 9
    //   474: invokeinterface 1136 1 0
    //   479: aconst_null
    //   480: putstatic 90	com/cleanmaster/base/c:f	Ljava/io/File;
    //   483: aload_0
    //   484: athrow
    //   485: astore_0
    //   486: goto -26 -> 460
    //   489: astore_0
    //   490: aload 6
    //   492: astore 7
    //   494: goto -34 -> 460
    //   497: astore_0
    //   498: goto -67 -> 431
    //   501: astore_0
    //   502: iload 4
    //   504: istore_3
    //   505: aload 8
    //   507: astore 6
    //   509: goto -78 -> 431
    //   512: astore_0
    //   513: goto -82 -> 431
    //   516: iconst_1
    //   517: istore_3
    //   518: goto -294 -> 224
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	521	0	paramFile1	File
    //   0	521	1	paramFile2	File
    //   0	521	2	paramC	com.cleanmaster.b.a.c
    //   25	493	3	bool1	boolean
    //   22	481	4	bool2	boolean
    //   5	420	5	bool3	boolean
    //   33	475	6	localObject1	Object
    //   42	451	7	localObject2	Object
    //   80	426	8	localObject3	Object
    //   14	459	9	localC	al.c
    //   69	232	10	localIterator	Iterator
    //   116	261	11	str	String
    // Exception table:
    //   from	to	target	type
    //   26	35	427	java/lang/StackOverflowError
    //   26	35	456	finally
    //   47	55	485	finally
    //   62	71	485	finally
    //   85	95	485	finally
    //   106	118	485	finally
    //   129	200	485	finally
    //   214	221	485	finally
    //   256	265	485	finally
    //   279	289	485	finally
    //   300	312	485	finally
    //   323	393	485	finally
    //   224	233	489	finally
    //   47	55	497	java/lang/StackOverflowError
    //   62	71	497	java/lang/StackOverflowError
    //   85	95	501	java/lang/StackOverflowError
    //   106	118	501	java/lang/StackOverflowError
    //   129	200	501	java/lang/StackOverflowError
    //   214	221	501	java/lang/StackOverflowError
    //   256	265	501	java/lang/StackOverflowError
    //   279	289	501	java/lang/StackOverflowError
    //   300	312	501	java/lang/StackOverflowError
    //   323	393	501	java/lang/StackOverflowError
    //   224	233	512	java/lang/StackOverflowError
  }
  
  public static Intent c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    int i1 = Build.VERSION.SDK_INT;
    Intent localIntent = new Intent();
    if (i1 >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
      return localIntent;
    }
    if (i1 == 8) {}
    for (String str = "pkg";; str = "com.android.settings.ApplicationPkgName")
    {
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      localIntent.putExtra(str, paramString);
      break;
    }
  }
  
  /* Error */
  public static String c()
  {
    // Byte code:
    //   0: getstatic 84	com/cleanmaster/base/c:c	Ljava/lang/String;
    //   3: ifnonnull +26 -> 29
    //   6: ldc 2
    //   8: monitorenter
    //   9: getstatic 84	com/cleanmaster/base/c:c	Ljava/lang/String;
    //   12: astore_0
    //   13: aload_0
    //   14: ifnonnull +12 -> 26
    //   17: invokestatic 1364	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   20: invokevirtual 1367	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   23: putstatic 84	com/cleanmaster/base/c:c	Ljava/lang/String;
    //   26: ldc 2
    //   28: monitorexit
    //   29: getstatic 84	com/cleanmaster/base/c:c	Ljava/lang/String;
    //   32: areturn
    //   33: astore_0
    //   34: aconst_null
    //   35: putstatic 84	com/cleanmaster/base/c:c	Ljava/lang/String;
    //   38: goto -12 -> 26
    //   41: astore_0
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload_0
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   12	2	0	str	String
    //   33	1	0	localException	Exception
    //   41	5	0	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   17	26	33	java/lang/Exception
    //   9	13	41	finally
    //   17	26	41	finally
    //   26	29	41	finally
    //   34	38	41	finally
    //   42	45	41	finally
  }
  
  public static String c(Context paramContext, Intent paramIntent)
  {
    Object localObject = paramContext.getPackageManager();
    paramIntent.addCategory("android.intent.category.DEFAULT");
    paramContext = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    ((PackageManager)localObject).getPreferredActivities(paramContext, localArrayList, null);
    int i2 = paramContext.size();
    int i1 = 0;
    while (i1 < i2)
    {
      if (((IntentFilter)paramContext.get(i1)).hasAction(paramIntent.getAction()))
      {
        localObject = paramIntent.getCategories().iterator();
        if (((Iterator)localObject).hasNext())
        {
          if (!((IntentFilter)paramContext.get(i1)).hasCategory((String)((Iterator)localObject).next())) {
            return null;
          }
          return ((ComponentName)localArrayList.get(i1)).getPackageName();
        }
      }
      i1 += 1;
    }
    return null;
  }
  
  public static void c(Context paramContext, String paramString)
  {
    Intent localIntent = AppManagerActivity.a(paramContext, 6);
    com.cleanmaster.notification.e.a();
    com.cleanmaster.notification.e.a(769);
    NotificationSetting localNotificationSetting = new NotificationSetting();
    localNotificationSetting.a = 769;
    localNotificationSetting.f = 2;
    com.cleanmaster.notification.normal.f localF = new com.cleanmaster.notification.normal.f();
    localF.b = paramString;
    localF.c = paramContext.getString(2131296531);
    localF.d = paramString;
    localF.e = 1;
    localF.q = localIntent;
    com.cleanmaster.notification.e.a().c(localNotificationSetting, localF);
  }
  
  public static void c(File paramFile)
  {
    if (paramFile != null) {
      OpLog.b("DFi", paramFile.getPath());
    }
    if (paramFile == null) {}
    do
    {
      return;
      if (!paramFile.isFile()) {
        break;
      }
    } while (!a(paramFile, null, false, 0));
    al.g(paramFile.getAbsolutePath());
    return;
    try
    {
      a(paramFile, null, 128, 0);
      al.g(paramFile.getAbsolutePath());
      f = null;
      return;
    }
    catch (StackOverflowError paramFile)
    {
      paramFile = paramFile;
      f = null;
      return;
    }
    finally
    {
      paramFile = finally;
      f = null;
      throw paramFile;
    }
  }
  
  public static boolean c(Context paramContext)
  {
    return !TextUtils.isEmpty(com.cleanmaster.configmanager.d.a(paramContext).L());
  }
  
  public static int d(Context paramContext, String paramString)
  {
    paramString = com.cleanmaster.base.util.system.m.c(paramContext, paramString);
    if (paramString == null) {
      return -1;
    }
    paramContext = (Boolean)com.cm.root.d.a(paramString.applicationInfo, "enabled");
    paramString = (Integer)com.cm.root.d.a(paramString.applicationInfo, "enabledSetting");
    if (paramContext == null) {
      return -1;
    }
    if (paramContext.booleanValue() == true) {
      return 1;
    }
    if (paramString == null) {
      return -1;
    }
    if (paramString.intValue() != 3) {
      return -1;
    }
    return 0;
  }
  
  public static String d(String paramString)
  {
    Object localObject = com.keniu.security.d.a();
    if (localObject == null) {
      return null;
    }
    localStringBuilder = new StringBuilder();
    try
    {
      paramString = new BufferedReader(new InputStreamReader(((Context)localObject).getAssets().open(paramString)));
      for (;;)
      {
        localObject = paramString.readLine();
        if (localObject == null) {
          break;
        }
        localStringBuilder.append((String)localObject);
      }
      return localStringBuilder.toString();
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      aj.b("liufan", "---------read Config error!");
    }
  }
  
  public static void d(File paramFile)
  {
    if (!paramFile.exists()) {}
    al.c localC;
    do
    {
      return;
      if (paramFile.isFile())
      {
        paramFile.delete();
        return;
      }
      localC = al.a(paramFile.getPath());
    } while (localC == null);
    try
    {
      localObject2 = localC.a();
      localObject1 = localObject2;
      if (localObject2 == null) {
        break label152;
      }
      localObject1 = localObject2;
      try
      {
        localIterator = ((al.d)localObject2).iterator();
        for (;;)
        {
          localObject1 = localObject2;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject1 = localObject2;
          str = (String)localIterator.next();
          localObject1 = localObject2;
          new File(android.support.percent.a.B(paramFile.getPath()) + str).delete();
        }
        if (localObject1 == null) {
          break label134;
        }
      }
      finally {}
    }
    finally
    {
      for (;;)
      {
        Object localObject2;
        Iterator localIterator;
        String str;
        label134:
        Object localObject1 = null;
      }
    }
    localObject1.c();
    localC.c();
    throw paramFile;
    localObject1 = localObject2;
    ((al.d)localObject2).c();
    localObject1 = null;
    label152:
    localObject2 = localC.b();
    localObject1 = localObject2;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      localIterator = ((al.d)localObject2).iterator();
      for (;;)
      {
        localObject1 = localObject2;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = localObject2;
        str = (String)localIterator.next();
        localObject1 = localObject2;
        d(new File(android.support.percent.a.B(paramFile.getPath()) + str));
      }
      localObject1 = localObject2;
      ((al.d)localObject2).c();
      localObject1 = null;
    }
    localObject2 = al.a(paramFile.getPath());
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((al.d)localObject2).d() != 0) {}
    }
    else
    {
      localObject1 = localObject2;
      paramFile.delete();
    }
    if (localObject2 != null) {
      ((al.d)localObject2).c();
    }
    localC.c();
  }
  
  public static boolean d()
  {
    boolean bool = false;
    String str = j.a().a(false);
    if (!TextUtils.isEmpty(str)) {
      bool = j.a(str);
    }
    return bool;
  }
  
  public static boolean d(Context paramContext)
  {
    if (paramContext != null)
    {
      ComponentName localComponentName = new ComponentName(paramContext, MainAppWidgetWhiteProvider.class);
      paramContext = AppWidgetManager.getInstance(paramContext).getAppWidgetIds(localComponentName);
      if ((paramContext != null) && (paramContext.length > 0)) {
        return true;
      }
    }
    return false;
  }
  
  public static long e(String paramString)
  {
    if (!LibcoreWrapper.a.A("android.permission.READ_EXTERNAL_STORAGE")) {}
    do
    {
      return 0L;
      paramString = new File(Environment.getExternalStorageDirectory(), "Android/obb/" + paramString);
    } while (!paramString.exists());
    long[] arrayOfLong = new long[3];
    al.b(paramString.getPath(), arrayOfLong, null);
    return arrayOfLong[0];
  }
  
  public static boolean e()
  {
    boolean bool2 = false;
    String str1 = com.cleanmaster.kinfocreporter.a.brand();
    String str2 = com.cleanmaster.kinfocreporter.a.model();
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(str1))
    {
      bool1 = bool2;
      if (!TextUtils.isEmpty(str2))
      {
        bool1 = bool2;
        if (str1.toLowerCase().equals("meizu")) {
          if (!str2.toLowerCase().equals("m351"))
          {
            bool1 = bool2;
            if (!str2.toLowerCase().equals("m353")) {}
          }
          else
          {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public static boolean e(Context paramContext)
  {
    if (paramContext != null)
    {
      ComponentName localComponentName = new ComponentName(paramContext, MainAppWidgetBlackProvider.class);
      paramContext = AppWidgetManager.getInstance(paramContext).getAppWidgetIds(localComponentName);
      if ((paramContext != null) && (paramContext.length > 0)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    if (com.cleanmaster.base.util.system.m.a(paramContext, paramString)) {
      return 1 == d(paramContext, paramString);
    }
    return false;
  }
  
  /* Error */
  private static int f(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_0
    //   4: aload_1
    //   5: iconst_0
    //   6: invokevirtual 1764	android/content/Context:createPackageContext	(Ljava/lang/String;I)Landroid/content/Context;
    //   9: invokevirtual 1692	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   12: ldc_w 1766
    //   15: invokevirtual 1770	android/content/res/AssetManager:openXmlResourceParser	(Ljava/lang/String;)Landroid/content/res/XmlResourceParser;
    //   18: astore_0
    //   19: iconst_m1
    //   20: istore_3
    //   21: iload_3
    //   22: istore 4
    //   24: iload_3
    //   25: istore 5
    //   27: aload_0
    //   28: astore 7
    //   30: aload_0
    //   31: invokeinterface 1775 1 0
    //   36: iconst_1
    //   37: if_icmpeq +352 -> 389
    //   40: iload_3
    //   41: istore_2
    //   42: iload_3
    //   43: istore 4
    //   45: aload_0
    //   46: astore 7
    //   48: aload_0
    //   49: invokeinterface 1775 1 0
    //   54: iconst_2
    //   55: if_icmpne +175 -> 230
    //   58: iload_3
    //   59: istore_2
    //   60: iload_3
    //   61: istore 4
    //   63: aload_0
    //   64: astore 7
    //   66: aload_0
    //   67: invokeinterface 1778 1 0
    //   72: ldc_w 1780
    //   75: invokevirtual 1783	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   78: ifeq +152 -> 230
    //   81: iconst_0
    //   82: istore 5
    //   84: iload_3
    //   85: istore_2
    //   86: iload_3
    //   87: istore 4
    //   89: aload_0
    //   90: astore 7
    //   92: iload 5
    //   94: aload_0
    //   95: invokeinterface 1786 1 0
    //   100: if_icmpge +130 -> 230
    //   103: iload_3
    //   104: istore 4
    //   106: aload_0
    //   107: astore 7
    //   109: ldc_w 1780
    //   112: new 462	java/lang/StringBuilder
    //   115: dup
    //   116: ldc_w 1788
    //   119: invokespecial 467	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   122: aload_1
    //   123: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc_w 1790
    //   129: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_0
    //   133: iload 5
    //   135: invokeinterface 1793 2 0
    //   140: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc_w 1795
    //   146: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload_0
    //   150: iload 5
    //   152: invokeinterface 1798 2 0
    //   157: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: invokevirtual 472	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 533	com/cleanmaster/util/OpLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: iload_3
    //   167: istore 4
    //   169: aload_0
    //   170: astore 7
    //   172: aload_0
    //   173: iload 5
    //   175: invokeinterface 1793 2 0
    //   180: ldc_w 1218
    //   183: invokevirtual 808	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   186: ifeq +194 -> 380
    //   189: iload_3
    //   190: istore 4
    //   192: aload_0
    //   193: astore 7
    //   195: aload_0
    //   196: iload 5
    //   198: invokeinterface 1798 2 0
    //   203: invokestatic 1801	android/text/TextUtils:isDigitsOnly	(Ljava/lang/CharSequence;)Z
    //   206: ifeq +84 -> 290
    //   209: iload_3
    //   210: istore 4
    //   212: aload_0
    //   213: astore 7
    //   215: aload_0
    //   216: iload 5
    //   218: invokeinterface 1798 2 0
    //   223: invokestatic 196	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   226: invokevirtual 199	java/lang/Integer:intValue	()I
    //   229: istore_2
    //   230: iload_2
    //   231: istore 5
    //   233: iload_2
    //   234: iconst_m1
    //   235: if_icmpne +154 -> 389
    //   238: iload_2
    //   239: istore 4
    //   241: aload_0
    //   242: astore 7
    //   244: aload_0
    //   245: invokeinterface 1803 1 0
    //   250: pop
    //   251: iload_2
    //   252: istore_3
    //   253: goto -232 -> 21
    //   256: astore_1
    //   257: iload 4
    //   259: istore_2
    //   260: aload_1
    //   261: invokevirtual 285	java/lang/Exception:printStackTrace	()V
    //   264: ldc_w 1805
    //   267: aload_1
    //   268: invokevirtual 1808	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   271: invokestatic 533	com/cleanmaster/util/OpLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   274: iload_2
    //   275: istore_3
    //   276: aload_0
    //   277: ifnull +11 -> 288
    //   280: aload_0
    //   281: invokeinterface 1809 1 0
    //   286: iload_2
    //   287: istore_3
    //   288: iload_3
    //   289: ireturn
    //   290: iload_3
    //   291: istore 4
    //   293: aload_0
    //   294: astore 7
    //   296: aload_0
    //   297: iload 5
    //   299: invokeinterface 1798 2 0
    //   304: ldc_w 1811
    //   307: invokevirtual 264	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   310: ifeq +8 -> 318
    //   313: iconst_0
    //   314: istore_2
    //   315: goto -85 -> 230
    //   318: iload_3
    //   319: istore 4
    //   321: aload_0
    //   322: astore 7
    //   324: aload_0
    //   325: iload 5
    //   327: invokeinterface 1798 2 0
    //   332: ldc_w 1813
    //   335: invokevirtual 264	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   338: ifeq +8 -> 346
    //   341: iconst_1
    //   342: istore_2
    //   343: goto -113 -> 230
    //   346: iload_3
    //   347: istore 4
    //   349: aload_0
    //   350: astore 7
    //   352: aload_0
    //   353: iload 5
    //   355: invokeinterface 1798 2 0
    //   360: ldc_w 1815
    //   363: invokevirtual 264	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   366: istore 6
    //   368: iload_3
    //   369: istore_2
    //   370: iload 6
    //   372: ifeq -142 -> 230
    //   375: iconst_2
    //   376: istore_2
    //   377: goto -147 -> 230
    //   380: iload 5
    //   382: iconst_1
    //   383: iadd
    //   384: istore 5
    //   386: goto -302 -> 84
    //   389: iload 5
    //   391: istore_3
    //   392: aload_0
    //   393: ifnull -105 -> 288
    //   396: aload_0
    //   397: invokeinterface 1809 1 0
    //   402: iload 5
    //   404: ireturn
    //   405: astore_0
    //   406: aload 7
    //   408: ifnull +10 -> 418
    //   411: aload 7
    //   413: invokeinterface 1809 1 0
    //   418: aload_0
    //   419: athrow
    //   420: astore_1
    //   421: aload_0
    //   422: astore 7
    //   424: aload_1
    //   425: astore_0
    //   426: goto -20 -> 406
    //   429: astore_1
    //   430: aconst_null
    //   431: astore_0
    //   432: iconst_m1
    //   433: istore_2
    //   434: goto -174 -> 260
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	437	0	paramContext	Context
    //   0	437	1	paramString	String
    //   41	393	2	i1	int
    //   20	372	3	i2	int
    //   22	326	4	i3	int
    //   25	378	5	i4	int
    //   366	5	6	bool	boolean
    //   1	422	7	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   30	40	256	java/lang/Exception
    //   48	58	256	java/lang/Exception
    //   66	81	256	java/lang/Exception
    //   92	103	256	java/lang/Exception
    //   109	166	256	java/lang/Exception
    //   172	189	256	java/lang/Exception
    //   195	209	256	java/lang/Exception
    //   215	230	256	java/lang/Exception
    //   244	251	256	java/lang/Exception
    //   296	313	256	java/lang/Exception
    //   324	341	256	java/lang/Exception
    //   352	368	256	java/lang/Exception
    //   3	19	405	finally
    //   30	40	405	finally
    //   48	58	405	finally
    //   66	81	405	finally
    //   92	103	405	finally
    //   109	166	405	finally
    //   172	189	405	finally
    //   195	209	405	finally
    //   215	230	405	finally
    //   244	251	405	finally
    //   296	313	405	finally
    //   324	341	405	finally
    //   352	368	405	finally
    //   260	274	420	finally
    //   3	19	429	java/lang/Exception
  }
  
  public static String f(String paramString)
  {
    int i2 = 0;
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    paramString = paramString.toCharArray();
    StringBuilder localStringBuilder = new StringBuilder();
    int i4 = paramString.length;
    int i1 = 0;
    if (i1 < i4)
    {
      char c1 = paramString[i1];
      int i3;
      if ((i2 == 0) && (TextUtils.isGraphic(c1)))
      {
        i3 = 1;
        localStringBuilder.append(c1);
      }
      for (;;)
      {
        i1 += 1;
        i2 = i3;
        break;
        i3 = i2;
        if (i2 != 0)
        {
          localStringBuilder.append(c1);
          i3 = i2;
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public static void f(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("http://bbs.liebao.cn/forum-97-1.html"));
    com.cleanmaster.base.util.system.b.a(paramContext, localIntent);
  }
  
  public static boolean f()
  {
    boolean bool2 = false;
    String str1 = com.cleanmaster.kinfocreporter.a.brand();
    String str2 = com.cleanmaster.kinfocreporter.a.model();
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(str1))
    {
      bool1 = bool2;
      if (!TextUtils.isEmpty(str2))
      {
        bool1 = bool2;
        if (str1.toLowerCase().equals("meizu"))
        {
          bool1 = bool2;
          if (str2.toLowerCase().equals("m040")) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public static void g(Context paramContext)
  {
    a(paramContext, "https://www.facebook.com/kscleanmaster");
  }
  
  public static void g(String paramString)
  {
    com.keniu.security.d.a().getApplicationContext();
    OpLog.d("VerInfo", "CMVersion: 51314417 msver: " + O() + " Root: " + com.cm.root.d.a().g() + " TaskName: " + paramString);
  }
  
  public static boolean g()
  {
    String str = com.cleanmaster.kinfocreporter.a.brand();
    return (!TextUtils.isEmpty(str)) && (str.toLowerCase().equals("meizu"));
  }
  
  public static void h(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setPackage("com.google.android.apps.plus");
    Object localObject1 = com.cleanmaster.configmanager.d.a(com.keniu.security.d.a().getApplicationContext()).b(com.keniu.security.d.a().getApplicationContext());
    if (((h)localObject1).M.equals(h.k)) {
      localObject1 = "https://plus.google.com/communities/111916490906918567516";
    }
    Object localObject2;
    for (;;)
    {
      localObject2 = localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        localObject2 = "https://plus.google.com/u/0/communities/103389439789411974839";
      }
      localIntent.setData(Uri.parse((String)localObject2));
      localObject1 = paramContext.getPackageManager().queryIntentActivities(localIntent, 65536);
      if ((localObject1 == null) || (((List)localObject1).isEmpty())) {
        break;
      }
      if (com.cleanmaster.base.util.system.b.b(paramContext, localIntent) != 0) {
        a(paramContext, "https://plus.google.com/u/0/communities/103389439789411974839");
      }
      return;
      if (((h)localObject1).M.equals(h.d)) {
        localObject1 = "https://plus.google.com/communities/101864561710815647290";
      } else if (((h)localObject1).M.equals(h.e)) {
        localObject1 = "https://plus.google.com/communities/100572821595959856554";
      } else {
        localObject1 = "https://plus.google.com/u/0/communities/103389439789411974839";
      }
    }
    a(paramContext, (String)localObject2);
  }
  
  public static void h(String paramString)
  {
    Toast localToast = Toast.makeText(i(), 2131297416, 0);
    localToast.setView(LayoutInflater.from(i()).inflate(2130903356, null));
    ((TextView)localToast.getView().findViewById(2131625933)).setText(paramString);
    localToast.setGravity(17, 0, 0);
    localToast.show();
  }
  
  @TargetApi(12)
  public static boolean h()
  {
    if (d != null) {
      return d.booleanValue();
    }
    boolean bool;
    if (Build.VERSION.SDK_INT >= 19) {
      if (!LibcoreWrapper.a.A("android.permission.WRITE_EXTERNAL_STORAGE")) {
        bool = false;
      }
    }
    for (;;)
    {
      d = Boolean.valueOf(bool);
      return bool;
      Object localObject2 = new f().b();
      Object localObject1;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (!((ArrayList)localObject2).isEmpty()) {}
      }
      else
      {
        if (!Environment.getExternalStorageState().equals("mounted")) {
          break label244;
        }
        localObject1 = new ArrayList();
        ((ArrayList)localObject1).add(Environment.getExternalStorageDirectory().getPath());
      }
      if (((ArrayList)localObject1).size() >= 2)
      {
        localObject2 = ((ArrayList)localObject1).iterator();
        for (;;)
        {
          if (((Iterator)localObject2).hasNext())
          {
            localObject1 = (String)((Iterator)localObject2).next();
            if (!((String)localObject1).startsWith(Environment.getExternalStorageDirectory().getAbsolutePath()))
            {
              localObject2 = new File((String)localObject1, ".CleanMaster" + System.currentTimeMillis() + ".tmp");
              try
              {
                bool = ((File)localObject2).createNewFile();
                if (bool)
                {
                  ((File)localObject2).delete();
                  bool = true;
                }
              }
              catch (IOException localIOException)
              {
                for (;;)
                {
                  localIOException.printStackTrace();
                  bool = false;
                }
                OpLog.d("Del2SD", "SDK:" + Build.VERSION.SDK_INT + " SecondSdCard not write! SecondSdCardPath:" + (String)localObject1);
                bool = false;
              }
              break;
            }
          }
        }
      }
      label244:
      bool = true;
    }
  }
  
  public static Context i()
  {
    if (e == null) {
      e = com.keniu.security.d.a().getApplicationContext();
    }
    return e;
  }
  
  /* Error */
  private static String i(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 8
    //   6: aconst_null
    //   7: astore 4
    //   9: invokestatic 206	com/keniu/security/d:a	()Landroid/content/Context;
    //   12: astore_1
    //   13: aload_1
    //   14: ifnonnull +5 -> 19
    //   17: aconst_null
    //   18: areturn
    //   19: aload_1
    //   20: invokevirtual 1692	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   23: astore_1
    //   24: aload_1
    //   25: aload_0
    //   26: invokevirtual 1698	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   29: astore_2
    //   30: aload_2
    //   31: ifnull +229 -> 260
    //   34: new 1688	java/io/InputStreamReader
    //   37: dup
    //   38: aload_2
    //   39: invokespecial 1700	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   42: astore_3
    //   43: new 575	java/io/BufferedReader
    //   46: dup
    //   47: aload_3
    //   48: invokespecial 1703	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   51: astore_1
    //   52: aload_1
    //   53: astore 6
    //   55: aload_3
    //   56: astore 5
    //   58: aload_2
    //   59: astore 4
    //   61: aload 8
    //   63: astore 7
    //   65: aload_1
    //   66: invokevirtual 586	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   69: astore 8
    //   71: aload 8
    //   73: astore 4
    //   75: aload_1
    //   76: astore 5
    //   78: aload_3
    //   79: astore 6
    //   81: aload 8
    //   83: ifnull +33 -> 116
    //   86: aload_1
    //   87: astore 6
    //   89: aload_3
    //   90: astore 5
    //   92: aload_2
    //   93: astore 4
    //   95: aload 8
    //   97: astore 7
    //   99: aload 8
    //   101: invokevirtual 1928	java/lang/String:trim	()Ljava/lang/String;
    //   104: astore 8
    //   106: aload 8
    //   108: astore 4
    //   110: aload_3
    //   111: astore 6
    //   113: aload_1
    //   114: astore 5
    //   116: aload 5
    //   118: invokestatic 1931	LibcoreWrapper/a:a	(Ljava/io/Closeable;)V
    //   121: aload 6
    //   123: invokestatic 1931	LibcoreWrapper/a:a	(Ljava/io/Closeable;)V
    //   126: aload_2
    //   127: invokestatic 1931	LibcoreWrapper/a:a	(Ljava/io/Closeable;)V
    //   130: aload 4
    //   132: areturn
    //   133: astore_1
    //   134: aconst_null
    //   135: astore_1
    //   136: aconst_null
    //   137: astore_3
    //   138: aconst_null
    //   139: astore_2
    //   140: aload_1
    //   141: astore 6
    //   143: aload_3
    //   144: astore 5
    //   146: aload_2
    //   147: astore 4
    //   149: getstatic 1935	java/lang/System:err	Ljava/io/PrintStream;
    //   152: new 462	java/lang/StringBuilder
    //   155: dup
    //   156: ldc_w 1937
    //   159: invokespecial 467	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   162: aload_0
    //   163: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: ldc_w 1939
    //   169: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: invokevirtual 472	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: invokevirtual 1944	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   178: aload_1
    //   179: invokestatic 1931	LibcoreWrapper/a:a	(Ljava/io/Closeable;)V
    //   182: aload_3
    //   183: invokestatic 1931	LibcoreWrapper/a:a	(Ljava/io/Closeable;)V
    //   186: aload_2
    //   187: invokestatic 1931	LibcoreWrapper/a:a	(Ljava/io/Closeable;)V
    //   190: aload 7
    //   192: areturn
    //   193: astore_0
    //   194: aconst_null
    //   195: astore_3
    //   196: aconst_null
    //   197: astore_2
    //   198: aconst_null
    //   199: astore_1
    //   200: aload_1
    //   201: invokestatic 1931	LibcoreWrapper/a:a	(Ljava/io/Closeable;)V
    //   204: aload_3
    //   205: invokestatic 1931	LibcoreWrapper/a:a	(Ljava/io/Closeable;)V
    //   208: aload_2
    //   209: invokestatic 1931	LibcoreWrapper/a:a	(Ljava/io/Closeable;)V
    //   212: aload_0
    //   213: athrow
    //   214: astore_0
    //   215: aconst_null
    //   216: astore_3
    //   217: aconst_null
    //   218: astore_1
    //   219: goto -19 -> 200
    //   222: astore_0
    //   223: aconst_null
    //   224: astore_1
    //   225: goto -25 -> 200
    //   228: astore_0
    //   229: aload 6
    //   231: astore_1
    //   232: aload 5
    //   234: astore_3
    //   235: aload 4
    //   237: astore_2
    //   238: goto -38 -> 200
    //   241: astore_1
    //   242: aconst_null
    //   243: astore_1
    //   244: aconst_null
    //   245: astore_3
    //   246: goto -106 -> 140
    //   249: astore_1
    //   250: aconst_null
    //   251: astore_1
    //   252: goto -112 -> 140
    //   255: astore 4
    //   257: goto -117 -> 140
    //   260: aconst_null
    //   261: astore 5
    //   263: aconst_null
    //   264: astore 6
    //   266: goto -150 -> 116
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	269	0	paramString	String
    //   12	102	1	localObject1	Object
    //   133	1	1	localIOException1	IOException
    //   135	97	1	localObject2	Object
    //   241	1	1	localIOException2	IOException
    //   243	1	1	localObject3	Object
    //   249	1	1	localIOException3	IOException
    //   251	1	1	localObject4	Object
    //   29	209	2	localObject5	Object
    //   42	204	3	localObject6	Object
    //   7	229	4	localObject7	Object
    //   255	1	4	localIOException4	IOException
    //   56	206	5	localObject8	Object
    //   53	212	6	localObject9	Object
    //   1	190	7	localObject10	Object
    //   4	103	8	str	String
    // Exception table:
    //   from	to	target	type
    //   24	30	133	java/io/IOException
    //   24	30	193	finally
    //   34	43	214	finally
    //   43	52	222	finally
    //   65	71	228	finally
    //   99	106	228	finally
    //   149	178	228	finally
    //   34	43	241	java/io/IOException
    //   43	52	249	java/io/IOException
    //   65	71	255	java/io/IOException
    //   99	106	255	java/io/IOException
  }
  
  public static void i(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("sinaweibo://userinfo?nick=猎豹清理大师&sid=t_wap_android"));
    localIntent.setPackage("com.sina.weibo");
    List localList = paramContext.getPackageManager().queryIntentActivities(localIntent, 65536);
    if ((localList != null) && (!localList.isEmpty()))
    {
      if (com.cleanmaster.base.util.system.b.b(paramContext, localIntent) != 0) {
        a(paramContext, "http://www.weibo.com/qinglidashi");
      }
      return;
    }
    a(paramContext, "http://www.weibo.com/qinglidashi");
  }
  
  public static void j()
  {
    com.cleanmaster.base.util.system.o.b();
    com.cleanmaster.base.util.system.o.e();
    com.cm.root.d.a();
    if ((com.cm.root.d.b()) && (!com.cm.root.d.a().g())) {}
    try
    {
      com.cleanmaster.synipc.b.a().c().c();
      return;
    }
    catch (RemoteException localRemoteException) {}
  }
  
  public static void j(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://weixin.qq.com/r/8nUtNUzEPhFBrQhW9yDg"));
    localIntent.setPackage("com.tencent.mm");
    localIntent.setFlags(268435456);
    List localList = paramContext.getPackageManager().queryIntentActivities(localIntent, 65536);
    if ((localList != null) && (!localList.isEmpty()))
    {
      if (-2 == com.cleanmaster.base.util.system.b.b(paramContext, localIntent)) {}
      try
      {
        localIntent = new Intent("qinglidashi");
        localIntent.addFlags(1073741824);
        localIntent.addFlags(268435456);
        localIntent.setClassName("com.tencent.mm", new String(new byte[] { 99, 111, 109, 46, 116, 101, 110, 99, 101, 110, 116, 46, 109, 109, 46, 117, 105, 46, 76, 97, 117, 110, 99, 104, 101, 114, 85, 73 }));
        localIntent.putExtra(new String(new byte[] { 76, 97, 117, 110, 99, 104, 101, 114, 85, 73, 95, 70, 114, 111, 109, 95, 66, 105, 122, 95, 83, 104, 111, 114, 116, 99, 117, 116 }), true);
        paramContext.startActivity(localIntent);
        return;
      }
      catch (Exception localException)
      {
        Toast.makeText(paramContext, paramContext.getString(2131296300), 0).show();
        return;
      }
    }
    Toast.makeText(paramContext, paramContext.getString(2131299025), 0).show();
  }
  
  public static int k(Context paramContext)
  {
    com.cleanmaster.data.filter.a localA = new com.cleanmaster.data.filter.a(new LocalService.h(), new LocalService.c(paramContext));
    Object localObject = com.cleanmaster.func.cache.e.a().b.a();
    com.cleanmaster.bitloader.a.b localB = new com.cleanmaster.bitloader.a.b();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((!localPackageInfo.packageName.equals(paramContext.getPackageName())) && (localA.a(localPackageInfo)) && ((localPackageInfo.applicationInfo.flags & 0x40000) == 0) && (!localB.contains(localPackageInfo.packageName))) {
        localB.add(localPackageInfo.packageName);
      }
    }
    return localB.size();
  }
  
  public static com.cleanmaster.base.util.e.g k()
  {
    Object localObject2 = new f().b(true);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new ArrayList();
    }
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      if (!((ArrayList)localObject1).isEmpty()) {
        break label61;
      }
      ((ArrayList)localObject1).add(Environment.getExternalStorageDirectory().getPath());
    }
    for (;;)
    {
      return LibcoreWrapper.a.a((ArrayList)localObject1);
      label61:
      if (com.cleanmaster.base.util.e.c.a())
      {
        if (((ArrayList)localObject1).size() > 1)
        {
          int i1 = ((ArrayList)localObject1).indexOf(Environment.getExternalStorageDirectory().getPath());
          if (i1 >= 0) {
            ((ArrayList)localObject1).remove(i1);
          }
        }
      }
      else
      {
        localObject2 = Environment.getExternalStorageDirectory().getPath();
        if (!((ArrayList)localObject1).contains(localObject2)) {
          ((ArrayList)localObject1).add(localObject2);
        }
      }
    }
  }
  
  public static com.cleanmaster.base.util.e.g l()
  {
    ArrayList localArrayList = new f().b(true);
    if (localArrayList == null) {
      return null;
    }
    return LibcoreWrapper.a.a(localArrayList);
  }
  
  public static void l(Context paramContext)
  {
    a(paramContext, true);
  }
  
  public static int m(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    try
    {
      int i1 = Settings.System.getInt(paramContext, "screen_brightness");
      return i1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static com.cleanmaster.base.util.e.g m()
  {
    ArrayList localArrayList = new f().b(false);
    if (localArrayList == null) {
      return null;
    }
    return LibcoreWrapper.a.a(localArrayList);
  }
  
  public static int n(Context paramContext)
  {
    try
    {
      int i1 = Settings.System.getInt(paramContext.getContentResolver(), "screen_off_timeout", 0);
      return i1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static com.cleanmaster.base.util.e.g n()
  {
    ArrayList localArrayList = new f().b();
    if (localArrayList == null) {
      return null;
    }
    return LibcoreWrapper.a.a(localArrayList);
  }
  
  public static double o()
  {
    synchronized (g)
    {
      double d1 = g.nextDouble();
      return d1;
    }
  }
  
  public static boolean o(Context paramContext)
  {
    try
    {
      int i1 = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness_mode");
      return i1 == 1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static String p()
  {
    Object localObject3 = null;
    Object localObject1 = localObject3;
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject1 = com.keniu.security.a.h();
      if ((localObject1 != null) && (!((File)localObject1).exists())) {
        ((File)localObject1).mkdirs();
      }
      localObject2 = localObject1;
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (!((File)localObject1).exists()) {
          localObject2 = null;
        }
      }
      localObject1 = localObject3;
      if (localObject2 != null) {
        localObject1 = android.support.percent.a.B(((File)localObject2).getPath());
      }
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = android.support.percent.a.B(com.keniu.security.d.a().getApplicationInfo().dataDir);
    }
    return localObject2;
  }
  
  public static void p(Context paramContext)
  {
    a(paramContext, null, true);
  }
  
  public static String q()
  {
    String str = p();
    if (str == null) {
      return null;
    }
    return str + "log.zip";
  }
  
  @SuppressLint({"InlinedApi"})
  public static void q(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("samsungapps://ProductDetail/com.cleanmaster.mguard"));
    localIntent.addFlags(335544352);
    if (com.cleanmaster.base.util.system.b.a(paramContext, localIntent)) {
      return;
    }
    com.cleanmaster.base.util.system.b.a(paramContext, new Intent("android.intent.action.VIEW", Uri.parse("http://apps.samsung.com/topApps/topAppsDetail.as?productId=000000575600")));
  }
  
  public static String r()
  {
    String str = com.keniu.security.d.b().e().getLanguage();
    str = str + "_";
    return str + com.keniu.security.d.b().e().getCountry();
  }
  
  public static void r(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setClassName("com.skt.skaf.A000Z00040", "com.skt.skaf.A000Z00040.A000Z00040");
    localIntent.setAction("COLLAB_ACTION");
    localIntent.putExtra("com.skt.skaf.COL.URI", "PRODUCT_VIEW/0000361598/0".getBytes());
    localIntent.putExtra("com.skt.skaf.COL.REQUESTER", "A000Z00040");
    if (com.cleanmaster.base.util.system.b.a(paramContext, localIntent)) {
      return;
    }
    com.cleanmaster.base.util.system.b.a(paramContext, new Intent("android.intent.action.VIEW", Uri.parse("http://tsto.re/0000361598")));
  }
  
  public static String s()
  {
    String str1 = com.keniu.security.d.b().e().getLanguage();
    String str2 = com.keniu.security.d.b().e().getCountry();
    return str2 + "_" + str1;
  }
  
  public static void s(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("amzn://apps/android?p=com.cleanmaster.mguard"));
    localIntent.addFlags(335544320);
    if (com.cleanmaster.base.util.system.b.a(paramContext, localIntent)) {
      return;
    }
    com.cleanmaster.base.util.system.b.a(paramContext, new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=com.cleanmaster.mguard")));
  }
  
  public static String t()
  {
    if ((Build.MODEL.equals("ZTE V955")) || (Build.MODEL.equals("MI-ONE Plus"))) {
      return Environment.getExternalStorageDirectory().getPath();
    }
    return new f().a(true);
  }
  
  public static boolean t(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null) {}
    try
    {
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      bool1 = bool2;
      if (paramContext != null)
      {
        int i1 = paramContext.getIntExtra("plugged", -1);
        if (i1 != 1)
        {
          bool1 = bool2;
          if (i1 != 2) {}
        }
        else
        {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean u()
  {
    Object localObject = ad();
    if (localObject != null)
    {
      localObject = LibcoreWrapper.a.d((File)localObject);
      if ((localObject != null) && (((com.cleanmaster.base.util.e.g)localObject).a > 104857600L)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean u(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      int i1 = paramContext.getPackageInfo("com.android.vending", 1).versionName.compareTo("4.9.13");
      return i1 >= 0;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean v()
  {
    Object localObject = ad();
    if (localObject != null)
    {
      localObject = LibcoreWrapper.a.d((File)localObject);
      if ((localObject != null) && (((com.cleanmaster.base.util.e.g)localObject).b < 10485760L)) {
        return true;
      }
    }
    return false;
  }
  
  @SuppressLint({"NewApi"})
  public static String w()
  {
    Object localObject1;
    if ((Build.MODEL.equals("ZTE V955")) || (Build.MODEL.equals("MI-ONE Plus"))) {
      localObject1 = new f().a(false);
    }
    do
    {
      try
      {
        localObject2 = new StatFs((String)localObject1);
        int i1 = ((StatFs)localObject2).getBlockCount();
        int i2 = ((StatFs)localObject2).getBlockSize();
        if (i2 * i1 < 536870912L) {
          break;
        }
        return localObject1;
      }
      catch (Exception localException)
      {
        Object localObject2;
        for (;;) {}
      }
      if (Build.VERSION.SDK_INT >= 9)
      {
        if ((!Environment.getExternalStorageState().equals("mounted")) || (Environment.isExternalStorageRemovable())) {
          break;
        }
        return Environment.getExternalStorageDirectory().getPath();
      }
      if (!Environment.getExternalStorageState().equals("mounted")) {
        break;
      }
      localObject2 = Environment.getExternalStorageDirectory().getPath();
      localObject1 = localObject2;
    } while (!((String)localObject2).equalsIgnoreCase(t()));
    return null;
  }
  
  @TargetApi(11)
  public static boolean x()
  {
    boolean bool = false;
    int i1;
    label43:
    String str;
    if (Build.MANUFACTURER.equals("Xiaomi"))
    {
      i1 = 0;
      if (i1 != 0)
      {
        if (!LibcoreWrapper.a.A("android.permission.WRITE_EXTERNAL_STORAGE")) {
          break label213;
        }
        if ((!com.cleanmaster.base.util.e.c.a) || (!u())) {
          break label190;
        }
        i1 = 1;
        if (i1 != 0) {}
      }
      else
      {
        str = com.cleanmaster.kinfocreporter.a.SP1("ro.build.fingerprint");
        if ((str != null) && (!str.isEmpty())) {
          break label218;
        }
        i1 = 0;
      }
    }
    for (;;)
    {
      if (i1 != 0) {
        bool = true;
      }
      return bool;
      if ((Build.MANUFACTURER.equalsIgnoreCase("huawei")) && (com.cleanmaster.base.util.system.d.h().equalsIgnoreCase("u8110")))
      {
        i1 = 0;
        break;
      }
      if (ae())
      {
        i1 = 0;
        break;
      }
      if (com.cleanmaster.base.util.e.c.a)
      {
        if ((Build.MANUFACTURER.equalsIgnoreCase("samsung")) && ((com.cleanmaster.base.util.system.d.h().contains("GT-I95")) || (com.cleanmaster.base.util.system.d.h().contains("SM-N9")) || (com.cleanmaster.base.util.system.d.h().contains("SM-G9")))) {}
        for (i1 = 1;; i1 = 0)
        {
          if (i1 != 0) {
            break label185;
          }
          i1 = 0;
          break;
        }
      }
      label185:
      i1 = 1;
      break;
      label190:
      if ((!com.cleanmaster.base.util.e.c.a) && ("mounted".equals(Environment.getExternalStorageState())))
      {
        i1 = 1;
        break label43;
      }
      label213:
      i1 = 0;
      break label43;
      label218:
      String[] arrayOfString = n;
      int i2 = arrayOfString.length;
      i1 = 0;
      for (;;)
      {
        if (i1 >= i2) {
          break label257;
        }
        if (str.startsWith(arrayOfString[i1]))
        {
          i1 = 1;
          break;
        }
        i1 += 1;
      }
      label257:
      i1 = 0;
    }
  }
  
  public static boolean y()
  {
    com.cleanmaster.util.i.a();
    return (com.cleanmaster.util.i.f()) && (u());
  }
  
  public static boolean z()
  {
    return (x()) || (y());
  }
  
  public static final class a
  {
    y a = null;
    private String b;
    private boolean c = false;
    private boolean d = false;
    private long e = 0L;
    private ContentResolver f;
    private Uri g;
    private int h;
    private ArrayList<ContentValues> i;
    private int j = 0;
    
    @TargetApi(11)
    public a(String paramString, y paramY)
    {
      this(paramString, paramY, 1);
    }
    
    @TargetApi(11)
    private a(String paramString, y paramY, int paramInt)
    {
      this.b = paramString;
      this.a = paramY;
      int m = paramY.b();
      int k = paramY.c();
      if (k == 65535) {
        k = 0;
      }
      for (;;)
      {
        if ((m == 1) || (m == 0)) {}
        for (boolean bool1 = true;; bool1 = false)
        {
          this.c = bool1;
          if (m != 2)
          {
            bool1 = bool2;
            if (m != 0) {}
          }
          else
          {
            bool1 = true;
          }
          this.d = bool1;
          if (k != 0) {
            this.e = (System.currentTimeMillis() / 1000L - 86400 * k);
          }
          this.g = MediaStore.Files.getContentUri("external");
          this.f = c.i().getContentResolver();
          this.j = 200;
          this.i = new ArrayList(this.j);
          this.h = paramInt;
          return;
        }
      }
    }
    
    @TargetApi(11)
    private void c(String paramString)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("_data", paramString);
      this.i.add(localContentValues);
      if (this.i.size() >= this.j) {}
      try
      {
        this.f.bulkInsert(this.g, (ContentValues[])this.i.toArray(new ContentValues[this.i.size()]));
        this.i.clear();
        return;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
    }
    
    public final void a(String paramString)
    {
      if (this.c) {
        c(paramString);
      }
    }
    
    public final boolean a(final boolean paramBoolean)
    {
      if (this.i.isEmpty()) {
        return true;
      }
      try
      {
        this.f.bulkInsert(this.g, (ContentValues[])this.i.toArray(new ContentValues[this.i.size()]));
        Object localObject2 = new StringBuffer();
        String[] arrayOfString = new String[2];
        arrayOfString[0] = android.support.percent.a.B(this.b);
        Object localObject1 = this.b;
        if (android.support.v4.a.c.a((String)localObject1)) {
          localObject1 = "0";
        }
        for (;;)
        {
          arrayOfString[1] = localObject1;
          String str = "_data = '" + android.support.percent.a.C(this.b) + "'";
          localObject1 = "";
          if (this.e != 0L) {
            localObject1 = " AND date_modified < " + this.e;
          }
          if ((this.c) && (this.d))
          {
            ((StringBuffer)localObject2).append(" _data > ? AND _data < ? ").append((String)localObject1);
            label184:
            localObject1 = new ContentValues();
            ((ContentValues)localObject1).put("media_type", Integer.valueOf(this.h));
            this.f.update(this.g, (ContentValues)localObject1, ((StringBuffer)localObject2).toString(), arrayOfString);
            if (this.d) {
              this.f.update(this.g, (ContentValues)localObject1, str, null);
            }
            boolean bool = this.d;
            if (!bool) {}
          }
          try
          {
            this.f.delete(this.g, ((StringBuffer)localObject2).toString() + " ORDER BY _data DESC", arrayOfString);
            this.f.delete(this.g, ((StringBuffer)localObject2).toString(), arrayOfString);
            if (this.d) {
              this.f.delete(this.g, str, null);
            }
            if (this.h != 4)
            {
              localObject1 = new int[6];
              int m = this.a.c();
              int k = m;
              if (m == 65535) {
                k = 0;
              }
              localObject2 = new ArrayList(1);
              ((ArrayList)localObject2).add(this.b);
              al.a((int[])localObject1, (List)localObject2, this.a.b(), k, this.a.d(), this.a.e(), this.a.g(), this.a.f(), new ac()
              {
                private c.a a = null;
                
                public final void a(int paramAnonymousInt) {}
                
                public final void a(String paramAnonymousString)
                {
                  if (paramAnonymousString != null) {
                    this.a = new c.a(paramAnonymousString, c.a.this.a, (byte)0);
                  }
                }
                
                public final void a(String paramAnonymousString, long paramAnonymousLong, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
                
                public final void a(String paramAnonymousString1, String paramAnonymousString2)
                {
                  if ((paramAnonymousString1 != null) && (this.a != null)) {
                    this.a.a(paramAnonymousString2);
                  }
                }
                
                public final void a(String paramAnonymousString, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2, int paramAnonymousInt) {}
                
                public final boolean a(String paramAnonymousString, long paramAnonymousLong)
                {
                  if (c.a.this.a != null) {
                    return c.a.this.a.b(paramAnonymousString, paramAnonymousLong);
                  }
                  return true;
                }
                
                public final void b(String paramAnonymousString)
                {
                  if ((paramAnonymousString != null) && (this.a != null))
                  {
                    this.a.a(paramBoolean);
                    this.a = null;
                  }
                }
                
                public final void b(String paramAnonymousString1, String paramAnonymousString2)
                {
                  if ((paramAnonymousString1 != null) && (this.a != null)) {
                    this.a.b(paramAnonymousString2);
                  }
                }
                
                public final void onFeedback(String paramAnonymousString1, String paramAnonymousString2, long paramAnonymousLong) {}
              }, c.h(), Environment.getExternalStorageDirectory().getAbsolutePath(), null, null, paramBoolean);
              break label608;
              if (((String)localObject1).charAt(((String)localObject1).length() - 1) != File.separatorChar)
              {
                localObject1 = (String)localObject1 + "0";
                continue;
              }
              localObject1 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1) + "0";
              continue;
              if (this.c)
              {
                ((StringBuffer)localObject2).append(" _data > ? AND _data < ? ").append(" AND format != 12289 ").append((String)localObject1);
                break label184;
              }
              ((StringBuffer)localObject2).append(" _data > ? AND _data < ? ").append(" AND format = 12289 ").append((String)localObject1);
            }
          }
          catch (Exception localException1)
          {
            for (;;) {}
          }
        }
      }
      catch (Exception localException2)
      {
        label608:
        for (;;) {}
      }
      return true;
    }
    
    public final void b(String paramString)
    {
      if (this.d) {
        c(android.support.percent.a.C(paramString));
      }
    }
  }
  
  public static final class b
  {
    public String a;
    
    public b(String paramString)
    {
      this.a = paramString;
    }
  }
}
