package a.g;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import com.appgo.lib.R.style;
import com.appgo.lib.task.util.processutil.AndroidAppProcess;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public class ym
{
  public static int a(wg paramWg, int paramInt)
  {
    int j = 10000;
    int i;
    if (paramInt <= 0) {
      i = 0;
    }
    do
    {
      do
      {
        do
        {
          return i;
          i = j;
        } while (paramInt > 10000);
        i = j;
      } while (paramWg.isTopTask());
      if (!paramWg.isPerfectTask()) {
        break;
      }
      paramInt += 100;
      i = j;
    } while (paramInt > 10000);
    return paramInt;
    return paramInt;
  }
  
  public static int a(wg paramWg, wh paramWh)
  {
    try
    {
      int i = paramWh.getRewardsCount();
      if (a(paramWg)) {
        return i * 2;
      }
      double d = paramWh.getRewardsRate();
      return (int)(i * d);
    }
    catch (NullPointerException paramWg)
    {
      paramWg.printStackTrace();
    }
    return 0;
  }
  
  public static int a(Context paramContext)
  {
    int j = 50;
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      int i;
      int k;
      if (paramContext.widthPixels > paramContext.heightPixels)
      {
        i = paramContext.heightPixels;
        k = (int)(i / paramContext.density);
        if ((k < 320) || (k >= 468)) {
          break label69;
        }
        i = j;
      }
      for (;;)
      {
        return (int)(i * paramContext.density);
        i = paramContext.widthPixels;
        break;
        label69:
        if ((k >= 468) && (k < 728))
        {
          i = 60;
        }
        else
        {
          i = j;
          if (k >= 728) {
            i = 90;
          }
        }
      }
      return 0;
    }
    catch (NullPointerException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    int i = (int)paramFloat;
    try
    {
      float f = paramContext.getResources().getDisplayMetrics().density;
      return (int)(f * paramFloat + 0.5F);
    }
    catch (NullPointerException paramContext)
    {
      paramContext.printStackTrace();
    }
    return i;
  }
  
  public static long a(yk.a paramA)
  {
    long l2 = 0L;
    String str = "";
    if (paramA == yk.a.DATE) {
      str = "yyyyMMdd";
    }
    for (;;)
    {
      long l1 = l2;
      try
      {
        if (!TextUtils.isEmpty(str))
        {
          paramA = new Date();
          paramA = new SimpleDateFormat(str).format(paramA);
          l1 = l2;
          if (!TextUtils.isEmpty(paramA)) {
            l1 = b(paramA);
          }
        }
        return l1;
      }
      catch (Exception paramA)
      {
        zb.c("TaskTools getNowTime is error:" + paramA.getMessage());
      }
      if (paramA == yk.a.HOURS) {
        str = "yyyyMMddHH";
      } else if (paramA == yk.a.MINUTES) {
        str = "yyyyMMddHHmm";
      } else if (paramA == yk.a.SECONDS) {
        str = "yyyyMMddHHmmss";
      }
    }
    return 0L;
  }
  
  public static wh a(wg paramWg, yj paramYj)
  {
    paramWg = paramWg.getTaskContent().getTaskBranch().iterator();
    while (paramWg.hasNext())
    {
      wh localWh = (wh)paramWg.next();
      if (paramYj.equals(localWh.getBranchIndex())) {
        return localWh;
      }
    }
    return null;
  }
  
  public static String a(Activity paramActivity)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.addCategory("android.intent.category.BROWSABLE");
      localIntent.setDataAndType(Uri.parse("http://"), null);
      paramActivity = paramActivity.getPackageManager().queryIntentActivities(localIntent, 32);
      if ((paramActivity != null) && (paramActivity.size() > 0))
      {
        paramActivity = ((ResolveInfo)paramActivity.get(0)).activityInfo.packageName;
        return paramActivity;
      }
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return "";
  }
  
  public static String a(Context paramContext, wi paramWi, String paramString, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return paramString;
      localObject = paramString;
      try
      {
        String str = paramContext.getPackageName();
        localObject = paramString;
        if (TextUtils.isEmpty(str)) {
          continue;
        }
        localObject = paramString;
        paramContext = paramString.replace("$ACCOUNT", paramWi.getTargetId());
        if (paramBoolean) {
          localObject = paramContext;
        }
        for (paramContext = paramContext.replace("$PKGNAME", paramWi.getAppStore());; paramContext = paramContext.replace("$PKGNAME", paramWi.getTargetPkgName()))
        {
          localObject = paramContext;
          paramContext = paramContext.replace("$HostPKGNAME", str);
          localObject = paramContext;
          break;
          localObject = paramContext;
        }
        return localObject;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String a(String paramString1, String paramString2, int paramInt)
  {
    str = "";
    try
    {
      if (TextUtils.isEmpty(paramString1)) {
        return paramString1;
      }
      paramString1 = new StringBuffer(paramString1).insert(paramInt, paramString2).toString();
    }
    catch (NullPointerException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
        paramString1 = str;
      }
    }
    return paramString1;
  }
  
  public static List<wg> a(List<wg> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0)) {
      Collections.sort(paramList, new yn());
    }
    return paramList;
  }
  
  public static List<String> a(JSONObject paramJSONObject)
  {
    ArrayList localArrayList;
    if (paramJSONObject != null) {
      try
      {
        localArrayList = new ArrayList();
        paramJSONObject = paramJSONObject.keys();
        while (paramJSONObject.hasNext())
        {
          localArrayList.add((String)paramJSONObject.next());
          continue;
          return null;
        }
      }
      catch (Exception paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
    return localArrayList;
  }
  
  public static Map<String, Integer> a(Map<String, Integer> paramMap, String paramString)
  {
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return paramMap;
      }
      paramString = paramString.split(":");
      if (paramString.length == 2)
      {
        paramMap.put(paramString[0], Integer.valueOf(Integer.parseInt(paramString[1])));
        return paramMap;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return paramMap;
  }
  
  /* Error */
  private static Map<String, Integer> a(Map<String, Integer> paramMap, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: invokevirtual 297	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   5: astore_2
    //   6: aload_2
    //   7: ifnull +109 -> 116
    //   10: aload_2
    //   11: arraylength
    //   12: ifgt +5 -> 17
    //   15: aload_0
    //   16: areturn
    //   17: aload_2
    //   18: arraylength
    //   19: istore 4
    //   21: iconst_0
    //   22: istore_3
    //   23: aload_0
    //   24: astore_1
    //   25: iload_3
    //   26: iload 4
    //   28: if_icmpge +82 -> 110
    //   31: aload_2
    //   32: iload_3
    //   33: aaload
    //   34: astore 5
    //   36: aload 5
    //   38: invokestatic 83	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   41: ifeq +8 -> 49
    //   44: aload_0
    //   45: astore_1
    //   46: goto +72 -> 118
    //   49: aload_0
    //   50: astore_1
    //   51: aload 5
    //   53: ldc_w 317
    //   56: invokevirtual 318	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   59: ifne +59 -> 118
    //   62: aload 5
    //   64: ldc_w 317
    //   67: invokevirtual 321	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   70: ifeq +16 -> 86
    //   73: aload_0
    //   74: aload 5
    //   76: ldc_w 317
    //   79: invokestatic 323	a/g/ym:a	(Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Map;
    //   82: astore_1
    //   83: goto +35 -> 118
    //   86: aload_0
    //   87: aload 5
    //   89: invokestatic 325	a/g/ym:b	(Ljava/util/Map;Ljava/lang/String;)Ljava/util/Map;
    //   92: astore_1
    //   93: goto +25 -> 118
    //   96: astore_1
    //   97: aload_1
    //   98: invokevirtual 129	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   101: invokestatic 327	a/g/zb:b	(Ljava/lang/String;)V
    //   104: aload_1
    //   105: invokevirtual 231	java/lang/Exception:printStackTrace	()V
    //   108: aload_0
    //   109: astore_1
    //   110: aload_1
    //   111: areturn
    //   112: astore_1
    //   113: goto -16 -> 97
    //   116: aload_0
    //   117: areturn
    //   118: iload_3
    //   119: iconst_1
    //   120: iadd
    //   121: istore_3
    //   122: aload_1
    //   123: astore_0
    //   124: goto -101 -> 23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	127	0	paramMap	Map<String, Integer>
    //   0	127	1	paramString1	String
    //   0	127	2	paramString2	String
    //   22	100	3	i	int
    //   19	10	4	j	int
    //   34	54	5	localCharSequence	CharSequence
    // Exception table:
    //   from	to	target	type
    //   0	6	96	java/lang/Exception
    //   10	15	96	java/lang/Exception
    //   17	21	96	java/lang/Exception
    //   36	44	112	java/lang/Exception
    //   51	83	112	java/lang/Exception
    //   86	93	112	java/lang/Exception
  }
  
  public static void a(WebView paramWebView)
  {
    if (paramWebView != null)
    {
      paramWebView.clearCache(true);
      paramWebView.clearHistory();
      paramWebView.destroy();
    }
  }
  
  public static void a(String paramString1, String paramString2)
  {
    int i;
    if (!TextUtils.isEmpty(paramString1))
    {
      i = -1;
      switch (paramString1.hashCode())
      {
      }
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return;
        if (paramString1.equals("sdk_banner"))
        {
          i = 0;
          continue;
          if (paramString1.equals("sdk_native"))
          {
            i = 1;
            continue;
            if (paramString1.equals("sdk_inter")) {
              i = 2;
            }
          }
        }
        break;
      }
    }
    yk.i = paramString2;
    return;
    yk.j = paramString2;
    return;
    yk.l = paramString2;
  }
  
  public static boolean a()
  {
    long l = xg.a().a("markNowDateKey");
    if (l <= 0L) {}
    while (a(yk.a.DATE) == l) {
      return false;
    }
    return true;
  }
  
  public static boolean a(wg paramWg)
  {
    if (paramWg.isHolidaySale())
    {
      paramWg = paramWg.getMarketingTime();
      if (!TextUtils.isEmpty(paramWg))
      {
        paramWg = paramWg.split("-");
        if (paramWg.length == 2)
        {
          long l1 = b(paramWg[0]);
          long l2 = b(paramWg[1]);
          long l3 = a(yk.a.DATE);
          if ((l1 != 0L) && (l2 != 0L) && (l3 >= l1) && (l3 <= l2)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public static boolean a(wi paramWi, String paramString)
  {
    try
    {
      paramWi = paramWi.getTaskType();
      if ((!paramString.equals(paramWi)) && (!"allTask".equals(paramString)))
      {
        boolean bool = "all".equals(paramWi);
        if (!bool) {}
      }
      else
      {
        return true;
      }
    }
    catch (NullPointerException paramWi)
    {
      paramWi.printStackTrace();
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return c(paramContext, paramString);
    }
    return b(paramContext, paramString);
  }
  
  public static boolean a(String paramString)
  {
    return zw.c(paramString);
  }
  
  public static long b(String paramString)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        long l = Long.parseLong(paramString);
        return l;
      }
    }
    catch (NumberFormatException paramString)
    {
      zb.c("TaskTools NumberFormatException,parseLong");
    }
    return 0L;
  }
  
  public static wh b(wg paramWg)
  {
    if (paramWg != null) {
      try
      {
        Object localObject = paramWg.getTaskContent();
        paramWg = ((wi)localObject).getCurrentTaskBranchIndex();
        localObject = ((wi)localObject).getTaskBranch().iterator();
        while (((Iterator)localObject).hasNext())
        {
          wh localWh = (wh)((Iterator)localObject).next();
          boolean bool = paramWg.equals(localWh.getBranchIndex());
          if (bool) {
            return localWh;
          }
        }
      }
      catch (NullPointerException paramWg)
      {
        paramWg.printStackTrace();
      }
    }
    return null;
  }
  
  public static String b()
  {
    for (;;)
    {
      try
      {
        Object localObject2 = Locale.getDefault().getLanguage().toLowerCase().trim();
        localObject1 = localObject2;
        String str;
        localException1.printStackTrace();
      }
      catch (Exception localException1)
      {
        try
        {
          if (((String)localObject2).equals("zh"))
          {
            str = zc.a().toLowerCase();
            if (!str.equals("tw"))
            {
              localObject1 = localObject2;
              if (!str.equals("hk")) {}
            }
            else
            {
              localObject1 = "tw";
            }
          }
          localObject2 = localObject1;
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            localObject2 = "en";
          }
          return localObject2;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            Object localObject1 = localException1;
            Object localObject3 = localException2;
          }
        }
        localException1 = localException1;
        localObject1 = "";
      }
    }
  }
  
  public static Map<String, wg> b(List<wg> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      HashMap localHashMap = new HashMap();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        wg localWg = (wg)paramList.next();
        localHashMap.put(localWg.getId(), localWg);
      }
      return localHashMap;
    }
    return null;
  }
  
  private static Map<String, Integer> b(Map<String, Integer> paramMap, String paramString)
  {
    try
    {
      paramString = paramString.toLowerCase();
      if (paramMap.containsKey(paramString))
      {
        paramMap.put(paramString, Integer.valueOf(((Integer)paramMap.get(paramString)).intValue() + 1));
        return paramMap;
      }
      paramMap.put(paramString, Integer.valueOf(1));
      return paramMap;
    }
    catch (Exception paramString)
    {
      zb.b(paramString.getMessage());
      paramString.printStackTrace();
    }
    return paramMap;
  }
  
  public static void b(Activity paramActivity)
  {
    try
    {
      paramActivity.getWindow().setFlags(1024, 1024);
      return;
    }
    catch (NullPointerException paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return true;
      try
      {
        int i = paramContext.getResources().getConfiguration().orientation;
        if (i == 2) {
          return false;
        }
      }
      catch (NullPointerException paramContext)
      {
        zb.c("TaskTools isPortrait NullPointerException:" + paramContext.getMessage());
      }
    }
    return true;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    int k;
    int i;
    try
    {
      k = paramString.equals("");
      if (paramString != null) {
        break label96;
      }
      i = 1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    for (;;)
    {
      if (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.processName.equals(paramString))
        {
          i = localRunningAppProcessInfo.importance;
          if (i == 100) {
            return true;
          }
        }
      }
      else
      {
        return false;
      }
    }
    while ((i | k) != 0)
    {
      return false;
      label96:
      int j = 0;
    }
  }
  
  public static int c(Activity paramActivity)
  {
    try
    {
      Rect localRect = new Rect();
      paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
      int i = localRect.top;
      return i;
    }
    catch (NullPointerException paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return 0;
  }
  
  public static Map<String, Integer> c(Context paramContext)
  {
    Object localObject1 = new HashMap();
    if (paramContext == null) {
      return localObject1;
    }
    for (;;)
    {
      int i;
      int j;
      try
      {
        localPackageManager = paramContext.getPackageManager();
        if (localPackageManager == null) {
          break;
        }
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        if ((paramContext == null) || (paramContext.size() <= 0)) {
          break;
        }
        localIterator = paramContext.iterator();
        paramContext = (Context)localObject1;
      }
      catch (Exception paramContext)
      {
        try
        {
          PackageManager localPackageManager;
          Iterator localIterator;
          if (localIterator.hasNext())
          {
            Object localObject2 = (PackageInfo)localIterator.next();
            paramContext = (Context)localObject1;
            if (localObject2 == null) {
              continue;
            }
            localObject2 = ((PackageInfo)localObject2).applicationInfo;
            paramContext = (Context)localObject1;
            if (localObject2 == null) {
              continue;
            }
            Object localObject3 = ((ApplicationInfo)localObject2).loadLabel(localPackageManager);
            paramContext = (Context)localObject1;
            if (TextUtils.isEmpty((CharSequence)localObject3)) {
              continue;
            }
            localObject3 = ((CharSequence)localObject3).toString().toLowerCase();
            if ((((ApplicationInfo)localObject2).flags & 0x1) != 0)
            {
              paramContext = yk.c.a;
              int k = 1;
              i = 0;
              j = k;
              if (i >= paramContext.length) {
                break label266;
              }
              if (!((String)localObject3).contains(paramContext[i])) {
                break label276;
              }
              j = 0;
              break label266;
            }
            if (((String)localObject3).indexOf(" ") > -1)
            {
              paramContext = a((Map)localObject1, (String)localObject3, " ");
              continue;
            }
            if (((String)localObject3).contains("&"))
            {
              paramContext = a((Map)localObject1, (String)localObject3, "&");
              continue;
            }
            paramContext = b((Map)localObject1, (String)localObject3);
            continue;
          }
          return localObject1;
        }
        catch (Exception paramContext)
        {
          continue;
          paramContext = (Context)localObject1;
        }
        paramContext = paramContext;
        zb.b(paramContext.getMessage());
        paramContext.printStackTrace();
        return localObject1;
      }
      localObject1 = paramContext;
      label266:
      if (j == 0)
      {
        continue;
        label276:
        i += 1;
      }
    }
  }
  
  public static void c(String paramString)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = new File(paramString);
        if (paramString.exists()) {
          paramString.delete();
        }
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean c(@NonNull wg paramWg)
  {
    yl localYl = paramWg.getTaskState();
    zb.b("TaskTools task state:" + localYl + " taskId:" + paramWg.getId());
    return (yl.COMPLETED.equals(localYl)) || (yl.CLOSE.equals(localYl));
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = yt.a(paramContext);
      if (paramContext == null) {
        break label77;
      }
      if (paramContext.size() <= 0) {
        return false;
      }
      paramContext = paramContext.iterator();
      do
      {
        AndroidAppProcess localAndroidAppProcess;
        do
        {
          if (!paramContext.hasNext()) {
            break;
          }
          localAndroidAppProcess = (AndroidAppProcess)paramContext.next();
        } while (localAndroidAppProcess == null);
        bool = localAndroidAppProcess.a().equals(paramString);
      } while (!bool);
      bool = true;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        boolean bool = false;
      }
    }
    return bool;
    label77:
    return false;
  }
  
  public static wh d(wg paramWg)
  {
    switch (paramWg.getTaskContent().getTaskBranch().size())
    {
    default: 
      return null;
    case 1: 
      return a(paramWg, yj.INDEX_ONE);
    case 2: 
      return a(paramWg, yj.INDEX_TWO);
    }
    return a(paramWg, yj.INDEX_THREE);
  }
  
  public static List<String> d(String paramString)
  {
    int j = 0;
    localArrayList = new ArrayList();
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return localArrayList;
      }
      String str = paramString;
      String[] arrayOfString;
      int k;
      int i;
      if (paramString.contains("null"))
      {
        arrayOfString = paramString.split("null");
        k = arrayOfString.length;
        i = 0;
      }
      for (;;)
      {
        str = paramString;
        if (i < k)
        {
          str = arrayOfString[i];
          if ((str == "null") || (TextUtils.isEmpty(str))) {}
        }
        else
        {
          paramString = str.split(",");
          if ((paramString == null) || (paramString.length <= 0)) {
            break;
          }
          k = paramString.length;
          i = j;
          while (i < k)
          {
            str = paramString[i];
            if (!TextUtils.isEmpty(str)) {
              localArrayList.add(str);
            }
            i += 1;
          }
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void d(Activity paramActivity)
  {
    try
    {
      int i = paramActivity.getResources().getConfiguration().orientation;
      if (i == 2)
      {
        paramActivity.setRequestedOrientation(0);
        zb.b("TaskTools set is-----SCREEN_ORIENTATION_PORTRAIT");
        return;
      }
      if (i == 1)
      {
        paramActivity.setRequestedOrientation(1);
        zb.b("TaskTools set is SCREEN_ORIENTATION_PORTRAIT");
        return;
      }
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static int e(wg paramWg)
  {
    try
    {
      paramWg = paramWg.getTaskContent().getSdkInterStyle();
      if ("photo".equals(paramWg)) {
        return R.style.appgo_task_full_dialog;
      }
      if ("photo_text".equals(paramWg))
      {
        int i = R.style.appgo_task_full_dialog;
        return i;
      }
    }
    catch (NullPointerException paramWg)
    {
      paramWg.printStackTrace();
    }
    return R.style.appgo_task_full_dialog;
  }
  
  public static List<String> e(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return localArrayList;
      paramString = paramString.toLowerCase();
      if (paramString.contains("chrome"))
      {
        localArrayList.add("com.android.chrome");
        localArrayList.add("com.chrome.beta");
        localArrayList.add("com.chrome.dev");
        localArrayList.add("com.chrome.canary");
        localArrayList.add("com.chrome.dev");
        return localArrayList;
      }
      if (paramString.contains("firefox"))
      {
        localArrayList.add("org.mozilla.firefox");
        localArrayList.add("org.mozilla.firefox_beta");
        return localArrayList;
      }
      if (paramString.contains("opera"))
      {
        localArrayList.add("com.opera.browser");
        localArrayList.add("com.opera.mini.native");
        return localArrayList;
      }
      if (paramString.contains("uc"))
      {
        localArrayList.add("com.UCMobile.intl");
        localArrayList.add("com.uc.browser.en");
        localArrayList.add("com.uc.browser");
        localArrayList.add("com.UCMobile");
        localArrayList.add("com.opera.browser");
        return localArrayList;
      }
      if (paramString.contains("dolphin"))
      {
        localArrayList.add("mobi.mgeek.TunnyBrowser");
        localArrayList.add("com.dolphin.browser.zero");
        localArrayList.add("com.dolphin.browser.engine");
        return localArrayList;
      }
      if (paramString.contains("webbrowser"))
      {
        localArrayList.add("com.explore.web.browser");
        return localArrayList;
      }
      if (paramString.contains("cmbrowser"))
      {
        localArrayList.add("com.ksmobile.cb");
        localArrayList.add("com.cmcm.armorfly");
        return localArrayList;
      }
    } while (!paramString.contains("qqbrowser"));
    localArrayList.add("com.tencent.mtt");
    localArrayList.add("com.tencent.mtt.intl");
    return localArrayList;
  }
  
  public static String f(wg paramWg)
  {
    try
    {
      paramWg = paramWg.getTaskContent().getTaskType();
      return paramWg;
    }
    catch (NullPointerException paramWg)
    {
      paramWg.printStackTrace();
    }
    return null;
  }
  
  public static String f(String paramString)
  {
    String str = paramString;
    if (paramString != null) {
      str = paramString;
    }
    try
    {
      if (paramString.length() > 0)
      {
        int i = paramString.lastIndexOf('.');
        str = paramString;
        if (i > -1)
        {
          str = paramString;
          if (i < paramString.length() - 1) {
            str = paramString.substring(i + 1);
          }
        }
      }
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }
  
  public static String g(String paramString)
  {
    String str = paramString;
    if (paramString != null) {
      str = paramString;
    }
    try
    {
      if (paramString.length() > 0)
      {
        int i = paramString.lastIndexOf('.');
        str = paramString;
        if (i > -1)
        {
          str = paramString;
          if (i < paramString.length()) {
            str = paramString.substring(0, i);
          }
        }
      }
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }
  
  public static boolean g(wg paramWg)
  {
    return a(h(paramWg));
  }
  
  public static String h(wg paramWg)
  {
    if (paramWg != null)
    {
      paramWg = paramWg.getTaskContent();
      if (paramWg != null)
      {
        if ("app".equals(paramWg.getTaskType())) {
          return paramWg.getTargetId();
        }
        return paramWg.getTargetPkgName();
      }
    }
    return null;
  }
  
  public static void h(String paramString)
  {
    zb.b("Task_PeiQiPig " + paramString);
  }
  
  public static String i(String paramString)
  {
    try
    {
      if ((!TextUtils.isEmpty(paramString)) && (xi.a().c().b(paramString)))
      {
        int i = paramString.lastIndexOf("/");
        if (i == -1) {
          i = 0;
        }
        for (;;)
        {
          paramString = paramString.substring(i);
          paramString = yk.y + zn.a(paramString);
          return paramString;
          i += 1;
        }
      }
      return null;
    }
    catch (NullPointerException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean i(wg paramWg)
  {
    if (paramWg != null)
    {
      paramWg = paramWg.getTaskContent();
      if ((paramWg != null) && ("app".equals(paramWg.getTaskType()))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean j(wg paramWg)
  {
    try
    {
      List localList = xh.a().d();
      boolean bool = c(xh.a().a(localList, paramWg.getId()));
      if (bool) {
        return true;
      }
    }
    catch (NullPointerException paramWg)
    {
      paramWg.printStackTrace();
    }
    return false;
  }
}
