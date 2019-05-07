package com.dl.shell.scenerydispatcher.d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.dl.shell.common.a.d;
import com.dl.shell.scenerydispatcher.f;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class g
{
  public static final boolean DEBUG = ;
  private static ConnectivityManager adD;
  private static final Set<String> cqz = new LinkedHashSet();
  
  static
  {
    cqz.add("com.android.gallery3d");
    cqz.add("com.android.gallery");
    cqz.add("com.google.android.gallery3d");
    cqz.add("com.sec.android.gallery3d");
    cqz.add("com.meizu.media.gallery");
    cqz.add("com.miui.gallery");
    cqz.add("com.sonyericsson.album");
  }
  
  public static String E(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getPackageName();
    try
    {
      String str = URLEncoder.encode(String.format("utm_source=%s&utm_medium=%s&pid=%s&bdct=%s", new Object[] { paramContext, paramString1, paramContext, paramString2 }), "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      if (DEBUG) {
        d.e("getEncodedReferrer: ", "UnsupportedEncodingException: ", localUnsupportedEncodingException);
      }
    }
    return "utm_source%3D" + paramContext + "%26utm_medium%3D" + paramString1 + "%26pid%3D" + paramContext + "%26bdct%3D" + paramString2;
  }
  
  public static void L(Context paramContext, String paramString)
  {
    if (DEBUG) {
      d.d("jumpToInstall", "Google Market Url: " + paramString);
    }
    if (e(paramContext, "com.android.vending")) {
      try
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        localIntent.setFlags(268435456);
        localIntent.setPackage("com.android.vending");
        paramContext.startActivity(localIntent);
        return;
      }
      catch (Exception localException)
      {
        ay(paramContext, paramString);
        return;
      }
    }
    ay(paramContext, paramString);
  }
  
  public static boolean ado()
  {
    return Build.VERSION.SDK_INT < 21;
  }
  
  public static boolean afs()
  {
    return Build.VERSION.SDK_INT <= 10;
  }
  
  private static ConnectivityManager at(Context paramContext)
  {
    if (adD == null) {
      adD = (ConnectivityManager)paramContext.getSystemService("connectivity");
    }
    return adD;
  }
  
  public static int au(Context paramContext)
  {
    paramContext = at(paramContext);
    if (paramContext == null) {}
    int i;
    do
    {
      do
      {
        return -1;
        paramContext = paramContext.getActiveNetworkInfo();
      } while (paramContext == null);
      i = paramContext.getType();
      int j = paramContext.getSubtype();
      if (DEBUG) {
        d.d("ShellScene", "network type = " + i + " : " + j);
      }
      if ((i == 1) || (i == 6) || (i == 9)) {
        return 1;
      }
      if ((i == 0) || ((i == 7) && (j > 0)))
      {
        if ((j == 3) || (j == 5) || (j == 6) || (j == 8) || (j == 9) || (j == 10) || (j == 12) || (j == 14) || (j == 15)) {
          return 3;
        }
        if (j == 13) {
          return 5;
        }
        return 2;
      }
    } while ((i == 2) || (i == 7));
    return 2;
  }
  
  protected static void ay(Context paramContext, String paramString)
  {
    if (!isMarketUrl(paramString)) {}
    PackageManager localPackageManager;
    ResolveInfo localResolveInfo;
    do
    {
      return;
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.addFlags(268435456);
      localPackageManager = paramContext.getPackageManager();
      localResolveInfo = localPackageManager.resolveActivity(paramString, 65536);
    } while (localResolveInfo == null);
    if ("android".equals(localResolveInfo.activityInfo.packageName)) {
      paramString.setPackage(((ResolveInfo)localPackageManager.queryIntentActivities(paramString, 65536).get(0)).activityInfo.packageName);
    }
    paramContext.startActivity(paramString);
  }
  
  public static void d(Context paramContext, List<e> paramList)
  {
    if (paramList == null) {}
    e localE1;
    do
    {
      return;
      localE1 = h.lo(paramContext);
    } while (localE1 == null);
    Iterator localIterator1 = paramList.iterator();
    paramList = localE1;
    int i = 0;
    label29:
    e localE2;
    if (localIterator1.hasNext())
    {
      localE2 = (e)localIterator1.next();
      if ((localE2.priority >= paramList.priority) && ((localE2.priority != paramList.priority) || (localE2.cnY <= paramList.cnY))) {
        break label430;
      }
      i = 1;
      paramList = localE2;
    }
    label200:
    label225:
    label275:
    label340:
    label350:
    label360:
    label368:
    label430:
    for (;;)
    {
      if (localE1.coc < localE2.coc)
      {
        localE1.coc = localE2.coc;
        i = 1;
      }
      if (localE1.cod < localE2.cod)
      {
        localE1.cod = localE2.cod;
        i = 1;
      }
      Iterator localIterator2 = f.cpf.iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        Object localObject = (Integer)localE2.coe.get(str);
        int j;
        int k;
        long l1;
        if (localObject == null)
        {
          j = 0;
          localObject = (Integer)localE1.coe.get(str);
          if (localObject != null) {
            break label340;
          }
          k = 0;
          if (j > k)
          {
            localE1.coe.put(str, Integer.valueOf(j));
            i = 1;
          }
          localObject = (Long)localE2.cqy.get(str);
          if (localObject != null) {
            break label350;
          }
          l1 = 0L;
          localObject = (Long)localE1.cqy.get(str);
          if (localObject != null) {
            break label360;
          }
        }
        for (long l2 = 0L;; l2 = ((Long)localObject).longValue())
        {
          if (l1 <= l2) {
            break label368;
          }
          localE1.cqy.put(str, Long.valueOf(l1));
          i = 1;
          break;
          j = ((Integer)localObject).intValue();
          break label200;
          k = ((Integer)localObject).intValue();
          break label225;
          l1 = ((Long)localObject).longValue();
          break label275;
        }
      }
      break label29;
      if (!TextUtils.equals(paramList.pkgName, localE1.pkgName))
      {
        localE1.cnZ = paramList.cnZ;
        localE1.coa = paramList.coa;
        localE1.cob = paramList.cob;
      }
      if (i == 0) {
        break;
      }
      h.a(paramContext, paramContext.getPackageName(), localE1);
      return;
    }
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  /* Error */
  public static String f(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_1
    //   7: invokevirtual 347	android/net/Uri:getScheme	()Ljava/lang/String;
    //   10: astore_3
    //   11: aload_3
    //   12: ifnonnull +10 -> 22
    //   15: aload_1
    //   16: invokevirtual 350	android/net/Uri:getPath	()Ljava/lang/String;
    //   19: astore_0
    //   20: aload_0
    //   21: areturn
    //   22: ldc_w 352
    //   25: aload_3
    //   26: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   29: ifeq +11 -> 40
    //   32: aload_1
    //   33: invokevirtual 350	android/net/Uri:getPath	()Ljava/lang/String;
    //   36: astore_0
    //   37: goto -17 -> 20
    //   40: ldc_w 354
    //   43: aload_3
    //   44: invokevirtual 228	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   47: ifeq +131 -> 178
    //   50: aload_0
    //   51: invokevirtual 358	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   54: aload_1
    //   55: iconst_1
    //   56: anewarray 63	java/lang/String
    //   59: dup
    //   60: iconst_0
    //   61: ldc_w 360
    //   64: aastore
    //   65: aconst_null
    //   66: aconst_null
    //   67: aconst_null
    //   68: invokevirtual 366	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   71: astore_1
    //   72: aload_1
    //   73: ifnull +110 -> 183
    //   76: aload_1
    //   77: astore_0
    //   78: aload_1
    //   79: invokeinterface 371 1 0
    //   84: ifeq +99 -> 183
    //   87: aload_1
    //   88: astore_0
    //   89: aload_1
    //   90: ldc_w 360
    //   93: invokeinterface 375 2 0
    //   98: istore_2
    //   99: iload_2
    //   100: iconst_m1
    //   101: if_icmple +82 -> 183
    //   104: aload_1
    //   105: astore_0
    //   106: aload_1
    //   107: iload_2
    //   108: invokeinterface 379 2 0
    //   113: astore_3
    //   114: aload_3
    //   115: astore_0
    //   116: aload_1
    //   117: ifnull -97 -> 20
    //   120: aload_1
    //   121: invokeinterface 382 1 0
    //   126: aload_3
    //   127: astore_0
    //   128: goto -108 -> 20
    //   131: astore_3
    //   132: aconst_null
    //   133: astore_1
    //   134: aload_1
    //   135: astore_0
    //   136: aload_3
    //   137: invokevirtual 385	java/lang/Exception:printStackTrace	()V
    //   140: aload_1
    //   141: ifnull +37 -> 178
    //   144: aload_1
    //   145: invokeinterface 382 1 0
    //   150: aconst_null
    //   151: astore_0
    //   152: goto -132 -> 20
    //   155: astore_1
    //   156: aconst_null
    //   157: astore_0
    //   158: aload_0
    //   159: ifnull +9 -> 168
    //   162: aload_0
    //   163: invokeinterface 382 1 0
    //   168: aload_1
    //   169: athrow
    //   170: astore_1
    //   171: goto -13 -> 158
    //   174: astore_3
    //   175: goto -41 -> 134
    //   178: aconst_null
    //   179: astore_0
    //   180: goto -160 -> 20
    //   183: aconst_null
    //   184: astore_3
    //   185: goto -71 -> 114
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	paramContext	Context
    //   0	188	1	paramUri	Uri
    //   98	10	2	i	int
    //   10	117	3	str	String
    //   131	6	3	localException1	Exception
    //   174	1	3	localException2	Exception
    //   184	1	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   50	72	131	java/lang/Exception
    //   50	72	155	finally
    //   78	87	170	finally
    //   89	99	170	finally
    //   106	114	170	finally
    //   136	140	170	finally
    //   78	87	174	java/lang/Exception
    //   89	99	174	java/lang/Exception
    //   106	114	174	java/lang/Exception
  }
  
  private static boolean fc(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
    if ((localObject == null) || (((List)localObject).isEmpty())) {}
    do
    {
      return false;
      localObject = (ActivityManager.RunningTaskInfo)((List)localObject).get(0);
    } while ((localObject == null) || (((ActivityManager.RunningTaskInfo)localObject).topActivity == null));
    localObject = ((ActivityManager.RunningTaskInfo)localObject).topActivity.getPackageName();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.setPackage((String)localObject);
    paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    if ((paramContext != null) && (!paramContext.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean fd(Context paramContext)
  {
    Object localObject1 = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if ((localObject1 == null) || (((List)localObject1).isEmpty())) {}
    for (;;)
    {
      return false;
      localObject1 = ((List)localObject1).iterator();
      Object localObject2;
      do
      {
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject2 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
      } while ((localObject2 == null) || (((ActivityManager.RunningAppProcessInfo)localObject2).importance != 100) || (((ActivityManager.RunningAppProcessInfo)localObject2).pkgList == null) || (((ActivityManager.RunningAppProcessInfo)localObject2).pkgList.length == 0));
      for (localObject1 = localObject2.pkgList[0]; !TextUtils.isEmpty((CharSequence)localObject1); localObject1 = null)
      {
        localObject2 = new Intent("android.intent.action.MAIN");
        ((Intent)localObject2).addCategory("android.intent.category.HOME");
        ((Intent)localObject2).setPackage((String)localObject1);
        paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject2, 0);
        if ((paramContext != null) && (!paramContext.isEmpty())) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
    }
  }
  
  public static boolean hB(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return fd(paramContext);
    }
    return fc(paramContext);
  }
  
  public static boolean isMarketUrl(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0)) {}
    while ((!paramString.startsWith("http://market.")) && (!paramString.startsWith("https://market.")) && (!paramString.startsWith("https://play.")) && (!paramString.startsWith("http://play.")) && (!paramString.startsWith("market:"))) {
      return false;
    }
    return true;
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    try
    {
      paramContext = at(paramContext);
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnected();
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static List<e> ji(Context paramContext)
  {
    List localList = la(paramContext);
    if ((localList == null) || (localList.isEmpty())) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    String str;
    if (i < localList.size())
    {
      str = (String)localList.get(i);
      if (!TextUtils.isEmpty(str)) {}
    }
    label80:
    label290:
    label295:
    for (;;)
    {
      i += 1;
      break;
      Object localObject2 = null;
      int j = 0;
      Object localObject1;
      if (j < 5)
      {
        localObject1 = h.bY(paramContext, str);
        if ((localObject1 != null) && (((e)localObject1).priority != Integer.MAX_VALUE))
        {
          localObject2 = localObject1;
          if (!DEBUG) {
            break label290;
          }
          d.d("ShellScene", "getMultiConfigList: get config success; pkgName = " + str);
        }
      }
      for (;;)
      {
        if (localObject1 == null) {
          break label295;
        }
        if (DEBUG) {
          d.d("ShellScene", "getMultiConfigList: result config = \n " + ((e)localObject1).toString());
        }
        localArrayList.add(localObject1);
        break;
        int k = j + 1;
        if (DEBUG) {
          d.d("ShellScene", "getMultiConfigList: get config failed; count=" + k + "; pkgName = " + str);
        }
        try
        {
          Thread.sleep(10L);
          localObject2 = localObject1;
          j = k;
        }
        catch (InterruptedException localInterruptedException)
        {
          localObject2 = localObject1;
          j = k;
        }
        if (!DEBUG) {
          break label80;
        }
        localInterruptedException.printStackTrace();
        localObject2 = localObject1;
        j = k;
        break label80;
        return localArrayList;
        localObject1 = localObject2;
      }
    }
  }
  
  public static boolean kX(Context paramContext)
  {
    int i = au(paramContext);
    return (i == 2) || (i == -1);
  }
  
  public static boolean kY(Context paramContext)
  {
    return au(paramContext) == 1;
  }
  
  public static long kZ(Context paramContext)
  {
    long l1 = -1L;
    long l2;
    Object localObject;
    int i;
    do
    {
      try
      {
        List localList = la(paramContext);
        l2 = l1;
        if (localList != null)
        {
          if (localList.isEmpty()) {
            l2 = l1;
          }
        }
        else {
          return l2;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localObject = null;
        }
        i = 0;
      }
      l2 = l1;
    } while (i >= localObject.size());
    long l3 = h.bU(paramContext, (String)localObject.get(i));
    if (l3 <= 0L) {
      l2 = l1;
    }
    for (;;)
    {
      i += 1;
      l1 = l2;
      break;
      if (l1 > 0L)
      {
        l2 = l1;
        if (l3 >= l1) {}
      }
      else
      {
        l2 = l3;
      }
    }
  }
  
  public static void l(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramString2 = E(paramContext, paramString2, paramString3);
    L(paramContext, "https://play.google.com/store/apps/details?id=" + paramString1 + "&referrer=" + paramString2);
  }
  
  public static List<String> la(Context paramContext)
  {
    Object localObject = lc(paramContext);
    if ((localObject == null) || (((List)localObject).isEmpty())) {
      return null;
    }
    LinkedList localLinkedList = new LinkedList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null) && (!localApplicationInfo.metaData.isEmpty()) && (!TextUtils.equals(localApplicationInfo.packageName, paramContext.getPackageName())) && (localApplicationInfo.metaData.getInt("shell_scenery_dispatcher_version", -1) >= 0)) {
        localLinkedList.add(localApplicationInfo.packageName);
      }
    }
    return localLinkedList;
  }
  
  public static void lb(Context paramContext)
  {
    try
    {
      Object localObject1 = ((WifiManager)paramContext.getApplicationContext().getSystemService("wifi")).getConfiguredNetworks();
      StringBuilder localStringBuilder = new StringBuilder("");
      if (localObject1 != null)
      {
        Iterator localIterator = ((List)localObject1).iterator();
        while (localIterator.hasNext())
        {
          localObject1 = (WifiConfiguration)localIterator.next();
          if (DEBUG) {
            d.d("ShellScene", "updateWifiSsidData：config.SSID = " + ((WifiConfiguration)localObject1).SSID);
          }
          Object localObject2 = ((WifiConfiguration)localObject1).SSID;
          if (localObject2 != null)
          {
            localObject1 = localObject2;
            if (((String)localObject2).startsWith("\"")) {
              localObject1 = ((String)localObject2).substring(1, ((String)localObject2).length());
            }
            localObject2 = localObject1;
            if (((String)localObject1).endsWith("\"")) {
              localObject2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
            }
            localStringBuilder.append((String)localObject2);
            localStringBuilder.append(",");
          }
        }
      }
      h.cd(paramContext, localStringBuilder.toString());
    }
    catch (Exception paramContext)
    {
      if (DEBUG) {
        paramContext.printStackTrace();
      }
      return;
    }
  }
  
  public static List<ApplicationInfo> lc(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getInstalledApplications(128);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      List localList;
      try
      {
        paramContext = localPackageManager.getInstalledPackages(0);
        localList = Collections.emptyList();
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          try
          {
            localList.add(localPackageManager.getApplicationInfo(localPackageInfo.packageName, 128));
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          paramContext = Collections.emptyList();
        }
      }
      return localList;
    }
  }
  
  public static boolean ld(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return lf(paramContext);
    }
    return le(paramContext);
  }
  
  private static boolean le(Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
    if ((paramContext == null) || (paramContext.isEmpty())) {
      return false;
    }
    paramContext = (ActivityManager.RunningTaskInfo)paramContext.get(0);
    if ((paramContext == null) || (paramContext.topActivity == null)) {
      return false;
    }
    paramContext = paramContext.topActivity.getPackageName();
    return cqz.contains(paramContext);
  }
  
  private static boolean lf(Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if ((paramContext == null) || (paramContext.isEmpty())) {
      return false;
    }
    paramContext = paramContext.iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      if (!paramContext.hasNext()) {
        break;
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
    } while ((localRunningAppProcessInfo == null) || (localRunningAppProcessInfo.importance != 100) || (localRunningAppProcessInfo.pkgList == null) || (localRunningAppProcessInfo.pkgList.length == 0));
    for (paramContext = localRunningAppProcessInfo.pkgList[0];; paramContext = null)
    {
      if (TextUtils.isEmpty(paramContext)) {
        return false;
      }
      return cqz.contains(paramContext);
    }
  }
}
