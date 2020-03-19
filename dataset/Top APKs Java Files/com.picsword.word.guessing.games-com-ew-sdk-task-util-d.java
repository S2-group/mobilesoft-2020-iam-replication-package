package com.ew.sdk.task.util;

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
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import com.ew.sdk.R.style;
import com.ew.sdk.a.l;
import com.ew.sdk.a.q;
import com.ew.sdk.a.z;
import com.ew.sdk.task.util.processutil.AndroidAppProcess;
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

public class d
{
  public static int a(Context paramContext)
  {
    for (;;)
    {
      int i;
      int j;
      try
      {
        paramContext = paramContext.getResources().getDisplayMetrics();
        if (paramContext.widthPixels > paramContext.heightPixels) {
          i = paramContext.heightPixels;
        } else {
          i = paramContext.widthPixels;
        }
        j = (int)(i / paramContext.density);
        i = 50;
        if ((j >= 320) && (j < 468))
        {
          float f1 = i;
          float f2 = paramContext.density;
          return (int)(f1 * f2);
        }
      }
      catch (NullPointerException paramContext)
      {
        paramContext.printStackTrace();
        return 0;
      }
      if ((j >= 468) && (j < 728)) {
        i = 60;
      } else if (j >= 728) {
        i = 90;
      }
    }
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    int i = (int)paramFloat;
    try
    {
      float f = paramContext.getResources().getDisplayMetrics().density;
      return (int)(paramFloat * f + 0.5F);
    }
    catch (NullPointerException paramContext)
    {
      paramContext.printStackTrace();
    }
    return i;
  }
  
  public static int a(com.ew.sdk.task.b.a paramA, int paramInt)
  {
    if (paramInt <= 0) {
      return 0;
    }
    if (paramInt > 10000) {
      return 10000;
    }
    if (paramA.isTopTask()) {
      return 10000;
    }
    if (paramA.isPerfectTask())
    {
      int i = paramInt + 100;
      paramInt = i;
      if (i > 10000) {
        paramInt = 10000;
      }
      return paramInt;
    }
    return paramInt;
  }
  
  public static int a(com.ew.sdk.task.b.a paramA, com.ew.sdk.task.b.b paramB)
  {
    try
    {
      int i = paramB.getRewardsCount();
      if (a(paramA)) {
        return i * 2;
      }
      double d = paramB.getRewardsRate();
      return (int)(i * d);
    }
    catch (NullPointerException paramA)
    {
      paramA.printStackTrace();
    }
    return 0;
  }
  
  public static long a(b.a paramA)
  {
    Object localObject = "";
    if (paramA == b.a.DATE) {
      localObject = "yyyyMMdd";
    } else if (paramA == b.a.HOURS) {
      localObject = "yyyyMMddHH";
    } else if (paramA == b.a.MINUTES) {
      localObject = "yyyyMMddHHmm";
    } else if (paramA == b.a.SECONDS) {
      localObject = "yyyyMMddHHmmss";
    }
    try
    {
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        paramA = new Date();
        paramA = new SimpleDateFormat((String)localObject).format(paramA);
        if (!TextUtils.isEmpty(paramA))
        {
          long l = b(paramA);
          return l;
        }
      }
    }
    catch (Exception paramA)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("TaskTools getNowTime is error:");
      ((StringBuilder)localObject).append(paramA.getMessage());
      com.ew.sdk.a.e.c(((StringBuilder)localObject).toString());
    }
    return 0L;
  }
  
  public static com.ew.sdk.task.b.b a(com.ew.sdk.task.b.a paramA, a paramA1)
  {
    paramA = paramA.getTaskContent().getTaskBranch().iterator();
    while (paramA.hasNext())
    {
      com.ew.sdk.task.b.b localB = (com.ew.sdk.task.b.b)paramA.next();
      if (paramA1.equals(localB.getBranchIndex())) {
        return localB;
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
  
  /* Error */
  public static String a(Context paramContext, com.ew.sdk.task.b.c paramC, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic 98	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +5 -> 9
    //   7: aload_2
    //   8: areturn
    //   9: aload_2
    //   10: astore 4
    //   12: aload_0
    //   13: invokevirtual 235	android/content/Context:getPackageName	()Ljava/lang/String;
    //   16: astore 5
    //   18: aload_2
    //   19: astore 4
    //   21: aload 5
    //   23: invokestatic 98	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   26: ifeq +5 -> 31
    //   29: aload_2
    //   30: areturn
    //   31: aload_2
    //   32: astore 4
    //   34: aload_2
    //   35: ldc -19
    //   37: aload_1
    //   38: invokevirtual 240	com/ew/sdk/task/b/c:getTargetId	()Ljava/lang/String;
    //   41: invokevirtual 246	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   44: astore_0
    //   45: iload_3
    //   46: ifeq +19 -> 65
    //   49: aload_0
    //   50: ldc -8
    //   52: aload_1
    //   53: invokevirtual 251	com/ew/sdk/task/b/c:getAppStore	()Ljava/lang/String;
    //   56: invokevirtual 246	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   59: astore_1
    //   60: aload_1
    //   61: astore_0
    //   62: goto +42 -> 104
    //   65: aload_0
    //   66: ldc -8
    //   68: aload_1
    //   69: invokevirtual 254	com/ew/sdk/task/b/c:getTargetPkgName	()Ljava/lang/String;
    //   72: invokevirtual 246	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   75: astore_1
    //   76: aload_1
    //   77: astore_0
    //   78: goto +26 -> 104
    //   81: aload_0
    //   82: astore 4
    //   84: aload_0
    //   85: ldc_w 256
    //   88: aload 5
    //   90: invokevirtual 246	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   93: astore_0
    //   94: aload_0
    //   95: areturn
    //   96: astore_0
    //   97: aload_0
    //   98: invokevirtual 231	java/lang/Exception:printStackTrace	()V
    //   101: aload 4
    //   103: areturn
    //   104: goto -23 -> 81
    //   107: astore_1
    //   108: aload_0
    //   109: astore 4
    //   111: aload_1
    //   112: astore_0
    //   113: goto -16 -> 97
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	paramContext	Context
    //   0	116	1	paramC	com.ew.sdk.task.b.c
    //   0	116	2	paramString	String
    //   0	116	3	paramBoolean	boolean
    //   10	100	4	localObject	Object
    //   16	73	5	str	String
    // Exception table:
    //   from	to	target	type
    //   12	18	96	java/lang/Exception
    //   21	29	96	java/lang/Exception
    //   34	45	96	java/lang/Exception
    //   84	94	96	java/lang/Exception
    //   49	60	107	java/lang/Exception
    //   65	76	107	java/lang/Exception
  }
  
  public static String a(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      if (TextUtils.isEmpty(paramString1)) {
        return paramString1;
      }
      paramString1 = new StringBuffer(paramString1).insert(paramInt, paramString2).toString();
      return paramString1;
    }
    catch (NullPointerException paramString1)
    {
      paramString1.printStackTrace();
    }
    return "";
  }
  
  public static List<com.ew.sdk.task.b.a> a(List<com.ew.sdk.task.b.a> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0)) {
      Collections.sort(paramList, new e());
    }
    return paramList;
  }
  
  public static List<String> a(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      try
      {
        ArrayList localArrayList = new ArrayList();
        paramJSONObject = paramJSONObject.keys();
        while (paramJSONObject.hasNext()) {
          localArrayList.add((String)paramJSONObject.next());
        }
        return localArrayList;
      }
      catch (Exception paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
    return null;
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
  
  private static Map<String, Integer> a(Map<String, Integer> paramMap, String paramString1, String paramString2)
  {
    Map<String, Integer> localMap = paramMap;
    try
    {
      paramString1 = paramString1.split(paramString2);
      if (paramString1 != null)
      {
        localMap = paramMap;
        if (paramString1.length <= 0) {
          return paramMap;
        }
        localMap = paramMap;
        int j = paramString1.length;
        int i = 0;
        for (;;)
        {
          localMap = paramMap;
          if (i >= j) {
            break;
          }
          paramString2 = paramString1[i];
          localMap = paramMap;
          if (!TextUtils.isEmpty(paramString2))
          {
            localMap = paramMap;
            if (!paramString2.equals("&"))
            {
              localMap = paramMap;
              if (paramString2.contains("&"))
              {
                localMap = paramMap;
                paramMap = a(paramMap, paramString2, "&");
              }
              else
              {
                localMap = paramMap;
                paramMap = b(paramMap, paramString2);
              }
            }
          }
          i += 1;
        }
      }
      return paramMap;
    }
    catch (Exception paramMap)
    {
      com.ew.sdk.a.e.b(paramMap.getMessage());
      paramMap.printStackTrace();
    }
    return localMap;
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
    if (!TextUtils.isEmpty(paramString1))
    {
      int i = -1;
      int j = paramString1.hashCode();
      if (j != -217714537)
      {
        if (j != 1628203729)
        {
          if ((j == 1971927996) && (paramString1.equals("sdk_native"))) {
            i = 1;
          }
        }
        else if (paramString1.equals("sdk_banner")) {
          i = 0;
        }
      }
      else if (paramString1.equals("sdk_inter")) {
        i = 2;
      }
      switch (i)
      {
      default: 
        return;
      case 2: 
        b.p = paramString2;
        return;
      case 1: 
        b.n = paramString2;
        return;
      }
      b.m = paramString2;
    }
  }
  
  public static boolean a()
  {
    long l = com.ew.sdk.task.d.a.a().a("markNowDateKey");
    boolean bool = false;
    if (l <= 0L) {
      return false;
    }
    if (a(b.a.DATE) != l) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (!TextUtils.isEmpty(paramString)))
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return c(paramContext, paramString);
      }
      return b(paramContext, paramString);
    }
    return false;
  }
  
  public static boolean a(com.ew.sdk.task.b.a paramA)
  {
    if (paramA.isHolidaySale())
    {
      paramA = paramA.getMarketingTime();
      if (!TextUtils.isEmpty(paramA))
      {
        paramA = paramA.split("-");
        if (paramA.length == 2)
        {
          long l1 = b(paramA[0]);
          long l2 = b(paramA[1]);
          long l3 = a(b.a.DATE);
          if ((l1 != 0L) && (l2 != 0L) && (l3 >= l1) && (l3 <= l2)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public static boolean a(com.ew.sdk.task.b.c paramC, String paramString)
  {
    try
    {
      paramC = paramC.getTaskType();
      if ((!paramString.equals(paramC)) && (!"allTask".equals(paramString)))
      {
        boolean bool = "all".equals(paramC);
        if (!bool) {}
      }
      else
      {
        return true;
      }
    }
    catch (NullPointerException paramC)
    {
      paramC.printStackTrace();
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    return z.c(paramString);
  }
  
  public static long b(String paramString)
  {
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        break label20;
      }
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException paramString)
    {
      label20:
      for (;;) {}
    }
    com.ew.sdk.a.e.c("TaskTools NumberFormatException,parseLong");
    return 0L;
  }
  
  public static com.ew.sdk.task.b.b b(com.ew.sdk.task.b.a paramA)
  {
    if (paramA != null) {
      try
      {
        Object localObject = paramA.getTaskContent();
        paramA = ((com.ew.sdk.task.b.c)localObject).getCurrentTaskBranchIndex();
        localObject = ((com.ew.sdk.task.b.c)localObject).getTaskBranch().iterator();
        while (((Iterator)localObject).hasNext())
        {
          com.ew.sdk.task.b.b localB = (com.ew.sdk.task.b.b)((Iterator)localObject).next();
          boolean bool = paramA.equals(localB.getBranchIndex());
          if (bool) {
            return localB;
          }
        }
      }
      catch (NullPointerException paramA)
      {
        paramA.printStackTrace();
      }
    }
    return null;
  }
  
  public static String b()
  {
    String str1;
    try
    {
      String str2 = Locale.getDefault().getLanguage().toLowerCase().trim();
      str1 = str2;
      try
      {
        if (!str2.equals("zh")) {
          break label77;
        }
        String str3 = com.ew.sdk.a.f.a().toLowerCase();
        if (!str3.equals("tw"))
        {
          str1 = str2;
          if (!str3.equals("hk")) {}
        }
        else
        {
          str1 = "tw";
        }
      }
      catch (Exception localException2)
      {
        str1 = str2;
      }
      Exception localException3;
      localException3.printStackTrace();
    }
    catch (Exception localException1)
    {
      str1 = "";
      localException3 = localException1;
    }
    label77:
    Object localObject = str1;
    if (TextUtils.isEmpty(str1)) {
      localObject = "en";
    }
    return localObject;
  }
  
  public static Map<String, com.ew.sdk.task.b.a> b(List<com.ew.sdk.task.b.a> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      HashMap localHashMap = new HashMap();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        com.ew.sdk.task.b.a localA = (com.ew.sdk.task.b.a)paramList.next();
        localHashMap.put(localA.getId(), localA);
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
      com.ew.sdk.a.e.b(paramString.getMessage());
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
    if (paramContext == null) {
      return true;
    }
    try
    {
      int i = paramContext.getResources().getConfiguration().orientation;
      if (i == 2) {
        return false;
      }
    }
    catch (NullPointerException paramContext)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("TaskTools isPortrait NullPointerException:");
      localStringBuilder.append(paramContext.getMessage());
      com.ew.sdk.a.e.c(localStringBuilder.toString());
    }
    return true;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    int j;
    int i;
    label88:
    do
    {
      try
      {
        j = paramString.equals("");
        if (paramString != null) {
          break label88;
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
      i = 0;
    } while ((j | i) == 0);
    return false;
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
    Object localObject2 = new HashMap();
    if (paramContext == null) {
      return localObject2;
    }
    Object localObject1 = localObject2;
    label335:
    label342:
    label349:
    for (;;)
    {
      int i;
      int j;
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        if (localPackageManager == null) {
          return localObject2;
        }
        localObject1 = localObject2;
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        if (paramContext != null)
        {
          localObject1 = localObject2;
          if (paramContext.size() <= 0) {
            return localObject2;
          }
          localObject1 = localObject2;
          Iterator localIterator = paramContext.iterator();
          paramContext = (Context)localObject2;
          localObject1 = paramContext;
          localObject2 = paramContext;
          if (localIterator.hasNext())
          {
            localObject1 = paramContext;
            localObject2 = (PackageInfo)localIterator.next();
            if (localObject2 == null) {
              continue;
            }
            localObject1 = paramContext;
            localObject2 = ((PackageInfo)localObject2).applicationInfo;
            if (localObject2 == null) {
              continue;
            }
            localObject1 = paramContext;
            Object localObject3 = ((ApplicationInfo)localObject2).loadLabel(localPackageManager);
            localObject1 = paramContext;
            if (TextUtils.isEmpty((CharSequence)localObject3)) {
              continue;
            }
            localObject1 = paramContext;
            localObject3 = ((CharSequence)localObject3).toString().toLowerCase();
            localObject1 = paramContext;
            i = ((ApplicationInfo)localObject2).flags;
            int k = 1;
            if ((i & 0x1) != 0)
            {
              localObject1 = paramContext;
              localObject2 = b.c.a;
              i = 0;
              localObject1 = paramContext;
              j = k;
              if (i >= localObject2.length) {
                break label342;
              }
              localObject1 = paramContext;
              if (!((String)localObject3).contains(localObject2[i])) {
                break label335;
              }
              j = 0;
              break label342;
            }
            localObject1 = paramContext;
            if (((String)localObject3).indexOf(" ") > -1)
            {
              localObject1 = paramContext;
              paramContext = a(paramContext, (String)localObject3, " ");
              break label349;
            }
            localObject1 = paramContext;
            if (((String)localObject3).contains("&"))
            {
              localObject1 = paramContext;
              paramContext = a(paramContext, (String)localObject3, "&");
              break label349;
            }
            localObject1 = paramContext;
            paramContext = b(paramContext, (String)localObject3);
            break label349;
          }
        }
        else
        {
          return localObject2;
        }
      }
      catch (Exception paramContext)
      {
        com.ew.sdk.a.e.b(paramContext.getMessage());
        paramContext.printStackTrace();
        localObject2 = localObject1;
      }
      return localObject2;
      i += 1;
      continue;
      if (j != 0) {}
    }
  }
  
  public static void c(String paramString)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = new File(paramString);
        if (paramString.exists())
        {
          paramString.delete();
          return;
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = com.ew.sdk.task.util.processutil.f.a(paramContext);
      if (paramContext != null)
      {
        if (paramContext.size() <= 0) {
          return false;
        }
        paramContext = paramContext.iterator();
        boolean bool;
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
        return true;
      }
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean c(com.ew.sdk.task.b.a paramA)
  {
    c localC = paramA.getTaskState();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TaskTools task state:");
    localStringBuilder.append(localC);
    localStringBuilder.append(" taskId:");
    localStringBuilder.append(paramA.getId());
    com.ew.sdk.a.e.b(localStringBuilder.toString());
    return (c.COMPLETED.equals(localC)) || (c.CLOSE.equals(localC));
  }
  
  public static com.ew.sdk.task.b.b d(com.ew.sdk.task.b.a paramA)
  {
    switch (paramA.getTaskContent().getTaskBranch().size())
    {
    default: 
      return null;
    case 3: 
      return a(paramA, a.INDEX_THREE);
    case 2: 
      return a(paramA, a.INDEX_TWO);
    }
    return a(paramA, a.INDEX_ONE);
  }
  
  public static List<String> d(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      int i;
      try
      {
        if (TextUtils.isEmpty(paramString)) {
          return localArrayList;
        }
        boolean bool = paramString.contains("null");
        int j = 0;
        String str = paramString;
        int k;
        if (bool)
        {
          String[] arrayOfString = paramString.split("null");
          k = arrayOfString.length;
          i = 0;
          str = paramString;
          if (i < k)
          {
            str = arrayOfString[i];
            if ((str == "null") || (TextUtils.isEmpty(str))) {
              break label152;
            }
          }
        }
        paramString = str.split(",");
        if ((paramString != null) && (paramString.length > 0))
        {
          k = paramString.length;
          i = j;
          if (i < k)
          {
            str = paramString[i];
            if (!TextUtils.isEmpty(str)) {
              localArrayList.add(str);
            }
            i += 1;
            continue;
          }
        }
        return localArrayList;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      label152:
      i += 1;
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
        com.ew.sdk.a.e.b("TaskTools set is-----SCREEN_ORIENTATION_PORTRAIT");
        return;
      }
      if (i == 1)
      {
        paramActivity.setRequestedOrientation(1);
        com.ew.sdk.a.e.b("TaskTools set is SCREEN_ORIENTATION_PORTRAIT");
        return;
      }
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static int e(com.ew.sdk.task.b.a paramA)
  {
    try
    {
      paramA = paramA.getTaskContent().getSdkInterStyle();
      if ("photo".equals(paramA)) {
        return R.style.ew_task_full_dialog;
      }
      if ("photo_text".equals(paramA))
      {
        int i = R.style.ew_task_full_dialog;
        return i;
      }
    }
    catch (NullPointerException paramA)
    {
      paramA.printStackTrace();
    }
    return R.style.ew_task_full_dialog;
  }
  
  public static List<String> e(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (TextUtils.isEmpty(paramString)) {
      return localArrayList;
    }
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
    if (paramString.contains("qqbrowser"))
    {
      localArrayList.add("com.tencent.mtt");
      localArrayList.add("com.tencent.mtt.intl");
      return localArrayList;
    }
    return localArrayList;
  }
  
  public static String f(com.ew.sdk.task.b.a paramA)
  {
    try
    {
      paramA = paramA.getTaskContent().getTaskType();
      return paramA;
    }
    catch (NullPointerException paramA)
    {
      paramA.printStackTrace();
    }
    return null;
  }
  
  public static String f(String paramString)
  {
    if (paramString != null) {
      try
      {
        if (paramString.length() > 0)
        {
          int i = paramString.lastIndexOf('.');
          if ((i > -1) && (i < paramString.length() - 1))
          {
            String str = paramString.substring(i + 1);
            return str;
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return paramString;
  }
  
  public static String g(String paramString)
  {
    if (paramString != null) {
      try
      {
        if (paramString.length() > 0)
        {
          int i = paramString.lastIndexOf('.');
          if ((i > -1) && (i < paramString.length()))
          {
            String str = paramString.substring(0, i);
            return str;
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return paramString;
  }
  
  public static boolean g(com.ew.sdk.task.b.a paramA)
  {
    return a(h(paramA));
  }
  
  public static String h(com.ew.sdk.task.b.a paramA)
  {
    if (paramA != null)
    {
      paramA = paramA.getTaskContent();
      if (paramA != null)
      {
        if ("app".equals(paramA.getTaskType())) {
          return paramA.getTargetId();
        }
        return paramA.getTargetPkgName();
      }
    }
    return null;
  }
  
  public static void h(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Task_PeiQiPig ");
    localStringBuilder.append(paramString);
    com.ew.sdk.a.e.b(localStringBuilder.toString());
  }
  
  public static String i(String paramString)
  {
    for (;;)
    {
      int i;
      try
      {
        if ((!TextUtils.isEmpty(paramString)) && (com.ew.sdk.task.d.c.a().c().b(paramString)))
        {
          i = paramString.lastIndexOf("/");
          if (i != -1) {
            break label83;
          }
          i = 0;
          paramString = paramString.substring(i);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(b.C);
          localStringBuilder.append(q.a(paramString));
          paramString = localStringBuilder.toString();
          return paramString;
        }
      }
      catch (NullPointerException paramString)
      {
        paramString.printStackTrace();
      }
      return null;
      label83:
      i += 1;
    }
  }
  
  public static boolean i(com.ew.sdk.task.b.a paramA)
  {
    if (paramA != null)
    {
      paramA = paramA.getTaskContent();
      if ((paramA != null) && ("app".equals(paramA.getTaskType()))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean j(com.ew.sdk.task.b.a paramA)
  {
    try
    {
      List localList = com.ew.sdk.task.d.b.a().d();
      boolean bool = c(com.ew.sdk.task.d.b.a().a(localList, paramA.getId()));
      if (bool) {
        return true;
      }
    }
    catch (NullPointerException paramA)
    {
      paramA.printStackTrace();
    }
    return false;
  }
  
  public static boolean k(com.ew.sdk.task.b.a paramA)
  {
    paramA = b(paramA);
    return (paramA != null) && (paramA.isVerificationByApp());
  }
  
  public static boolean l(com.ew.sdk.task.b.a paramA)
  {
    if (paramA != null)
    {
      paramA = paramA.getLocationTypeList();
      if ((paramA != null) && (paramA.size() > 0) && (paramA.contains("none"))) {
        return true;
      }
    }
    return false;
  }
}
