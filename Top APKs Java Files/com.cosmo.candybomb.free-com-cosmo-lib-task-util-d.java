package com.cosmo.lib.task.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.cosmo.lib.R.color;
import com.cosmo.lib.R.drawable;
import com.cosmo.lib.R.string;
import com.cosmo.lib.R.style;
import com.cosmo.lib.a.l;
import com.cosmo.lib.a.q;
import com.cosmo.lib.a.y;
import com.cosmo.lib.task.c.h;
import com.cosmo.lib.task.util.processutil.AndroidAppProcess;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
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
  
  public static int a(com.cosmo.lib.task.b.a paramA, int paramInt)
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
      } while (paramA.isTopTask());
      if (!paramA.isPerfectTask()) {
        break;
      }
      paramInt += 100;
      i = j;
    } while (paramInt > 10000);
    return paramInt;
    return paramInt;
  }
  
  public static int a(com.cosmo.lib.task.b.a paramA, com.cosmo.lib.task.b.b paramB, com.cosmo.lib.task.b.d paramD)
  {
    int j;
    if ((paramB == null) || (paramA == null) || (!b.D)) {
      j = 0;
    }
    int i;
    do
    {
      return j;
      double d2 = 1.0D;
      double d1 = d2;
      if (paramD != null)
      {
        HashMap localHashMap = paramD.getOfferRewardsMsgMap();
        d1 = d2;
        if (localHashMap != null)
        {
          String str = paramB.getRewardsName();
          paramD = str;
          if (!TextUtils.isEmpty(str)) {
            paramD = str.toLowerCase();
          }
          d1 = d2;
          if (localHashMap.containsKey(paramD)) {
            d1 = ((Double)localHashMap.get(paramD)).doubleValue();
          }
        }
      }
      if (d1 <= 0.0D) {
        return 0;
      }
      j = paramB.getRewardsCount();
      i = j;
      if (j > 0)
      {
        j = (int)(j * d1);
        i = j;
        if (j <= 0) {
          i = 1;
        }
      }
      j = i;
    } while (!o(paramA));
    return i * 2;
  }
  
  public static long a(long paramLong)
  {
    String str = new SimpleDateFormat("yyyyMMdd").format(Long.valueOf(paramLong));
    try
    {
      paramLong = Long.parseLong(str);
      return paramLong;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      com.cosmo.lib.a.e.a(localNumberFormatException);
    }
    return 0L;
  }
  
  public static long a(b.a paramA)
  {
    long l2 = 0L;
    Object localObject = "";
    switch (1.a[paramA.ordinal()])
    {
    default: 
      paramA = (b.a)localObject;
    }
    for (;;)
    {
      long l1 = l2;
      try
      {
        if (!TextUtils.isEmpty(paramA))
        {
          localObject = new Date();
          paramA = new SimpleDateFormat(paramA).format((Date)localObject);
          l1 = l2;
          if (!TextUtils.isEmpty(paramA)) {
            l1 = b(paramA);
          }
        }
        return l1;
      }
      catch (Exception paramA)
      {
        com.cosmo.lib.a.e.c("TaskTools getNowTime is error:" + paramA.getMessage());
      }
      paramA = "yyyyMMdd";
      continue;
      paramA = "yyyyMMddHH";
      continue;
      paramA = "yyyyMMddHHmm";
      continue;
      paramA = "yyyyMMddHHmmss";
    }
    return 0L;
  }
  
  public static TextView a(Activity paramActivity, int paramInt)
  {
    if (paramActivity == null) {
      return null;
    }
    if (paramInt == 1) {}
    for (paramInt = R.drawable.cosmo_task_two_adlabel_bg;; paramInt = R.drawable.cosmo_task_adlabel_bg)
    {
      TextView localTextView = new TextView(paramActivity);
      localTextView.setGravity(17);
      localTextView.setText(R.string.cosmo_task_adLabel);
      int i = com.cosmo.lib.a.f.a(paramActivity, 18);
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(i, i);
      localLayoutParams.gravity = 80;
      localTextView.setLayoutParams(localLayoutParams);
      localTextView.setTextSize(10.0F);
      localTextView.setTextColor(paramActivity.getResources().getColor(R.color.cosmo_task_adlabel));
      localTextView.setBackgroundResource(paramInt);
      return localTextView;
    }
  }
  
  public static com.cosmo.lib.task.b.b a(com.cosmo.lib.task.b.a paramA)
  {
    if (paramA == null) {
      return null;
    }
    Object localObject = paramA.getTaskContent();
    if (localObject == null) {
      return null;
    }
    paramA = ((com.cosmo.lib.task.b.c)localObject).getCurrentTaskBranchIndex();
    localObject = ((com.cosmo.lib.task.b.c)localObject).getTaskBranch().iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.cosmo.lib.task.b.b localB = (com.cosmo.lib.task.b.b)((Iterator)localObject).next();
      if (localB != null)
      {
        a localA = localB.getBranchIndex();
        if ((paramA != null) && (paramA == localA)) {
          return localB;
        }
      }
    }
    return null;
  }
  
  public static com.cosmo.lib.task.b.b a(com.cosmo.lib.task.b.a paramA, a paramA1)
  {
    if (paramA == null) {
      return null;
    }
    paramA = paramA.getTaskContent();
    if (paramA == null) {
      return null;
    }
    paramA = paramA.getTaskBranch();
    if ((paramA == null) || (paramA.size() <= 0)) {
      return null;
    }
    paramA = paramA.iterator();
    while (paramA.hasNext())
    {
      com.cosmo.lib.task.b.b localB = (com.cosmo.lib.task.b.b)paramA.next();
      if ((localB != null) && (paramA1.equals(localB.getBranchIndex()))) {
        return localB;
      }
    }
    return null;
  }
  
  public static String a(Activity paramActivity)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addCategory("android.intent.category.BROWSABLE");
    localIntent.setDataAndType(Uri.parse("http://"), null);
    try
    {
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
  
  public static String a(Context paramContext, com.cosmo.lib.task.b.c paramC, String paramString, boolean paramBoolean)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {}
    String str;
    do
    {
      return paramString;
      str = paramContext.getPackageName();
    } while (TextUtils.isEmpty(str));
    paramContext = paramString;
    try
    {
      paramString = paramString.replace("$ACCOUNT", paramC.getTargetId());
      if (paramBoolean) {
        paramContext = paramString;
      }
      for (paramC = paramString.replace("$PKGNAME", paramC.getAppStore());; paramC = paramString.replace("$PKGNAME", paramC.getTargetPkgName()))
      {
        paramContext = paramC;
        paramC = paramC.replace("$HostPKGNAME", str);
        paramContext = paramC;
        break;
        paramContext = paramString;
      }
      return paramContext;
    }
    catch (Exception paramC)
    {
      paramC.printStackTrace();
    }
  }
  
  public static String a(com.cosmo.lib.task.b.a paramA, String[] paramArrayOfString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramA != null)
    {
      paramA = paramA.getTaskContent();
      localObject1 = localObject2;
      if (paramA != null) {
        localObject1 = paramA.getTargetFeature();
      }
    }
    if (TextUtils.isEmpty((CharSequence)localObject1)) {}
    for (;;)
    {
      return localObject1;
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        paramA = paramArrayOfString[i];
        if (((String)localObject1).contains(paramA)) {
          return paramA;
        }
        i += 1;
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
      paramString1 = new StringBuilder(paramString1).insert(paramInt, paramString2).toString();
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
  
  public static ArrayList<com.cosmo.lib.task.b.a> a(ArrayList<com.cosmo.lib.task.b.a> paramArrayList)
  {
    if ((paramArrayList != null) && (paramArrayList.size() > 0)) {
      Collections.sort(paramArrayList, new e());
    }
    return paramArrayList;
  }
  
  public static ArrayList<String> a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramJSONObject = paramJSONObject.keys();
    while (paramJSONObject.hasNext()) {
      localArrayList.add((String)paramJSONObject.next());
    }
    localArrayList.trimToSize();
    return localArrayList;
  }
  
  public static HashMap<String, Integer> a(HashMap<String, Integer> paramHashMap, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return paramHashMap;
      paramString = paramString.split(":");
      if (paramString.length == 2) {
        paramHashMap.put(paramString[0], Integer.valueOf(Integer.parseInt(paramString[1])));
      }
    } while (paramString.length != 1);
    paramHashMap.put(paramString[0], Integer.valueOf(-1));
    return paramHashMap;
  }
  
  private static HashMap<String, Integer> a(HashMap<String, Integer> paramHashMap, String paramString1, String paramString2)
  {
    paramString2 = paramString1.split(paramString2);
    if (paramString2.length <= 0) {
      return paramHashMap;
    }
    int j = paramString2.length;
    int i = 0;
    paramString1 = paramHashMap;
    if (i < j)
    {
      CharSequence localCharSequence = paramString2[i];
      if (TextUtils.isEmpty(localCharSequence)) {
        paramHashMap = paramString1;
      }
      for (;;)
      {
        i += 1;
        paramString1 = paramHashMap;
        break;
        paramHashMap = paramString1;
        if (!localCharSequence.equals("&")) {
          if (localCharSequence.contains("&")) {
            paramHashMap = a(paramString1, localCharSequence, "&");
          } else {
            paramHashMap = c(paramString1, localCharSequence);
          }
        }
      }
    }
    return paramString1;
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    if (paramActivity == null) {}
    for (;;)
    {
      return;
      if (com.cosmo.lib.a.e.a()) {
        com.cosmo.lib.a.e.b("TaskTools  task copy content:" + paramString);
      }
      try
      {
        paramActivity = (ClipboardManager)paramActivity.getSystemService("clipboard");
        paramString = ClipData.newPlainText("copy", paramString);
        if (paramActivity != null)
        {
          paramActivity.setPrimaryClip(paramString);
          return;
        }
      }
      catch (IllegalStateException paramActivity)
      {
        paramActivity.printStackTrace();
      }
    }
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
  
  public static void a(com.cosmo.lib.task.b.a paramA, JSONObject paramJSONObject)
  {
    if ((paramA == null) || (paramJSONObject == null)) {
      return;
    }
    paramA = paramA.getTaskState();
    h localH = h.a();
    try
    {
      if (c.RUNNING.equals(paramA))
      {
        paramJSONObject.put("taskStateLanguage", localH.b());
        return;
      }
    }
    catch (NullPointerException paramA)
    {
      paramA.printStackTrace();
      return;
      if ((c.CLOSE.equals(paramA)) || (c.COMPLETED.equals(paramA)))
      {
        paramJSONObject.put("taskStateLanguage", localH.c());
        paramJSONObject.put("taskComplete", true);
        return;
      }
    }
    catch (JSONException paramA)
    {
      paramA.printStackTrace();
      return;
    }
    paramJSONObject.put("taskStateLanguage", "");
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
    b.r = paramString2;
    return;
    b.s = paramString2;
    return;
    b.u = paramString2;
  }
  
  public static boolean a()
  {
    long l = com.cosmo.lib.task.d.a.a().b("markNowDateKey");
    if (l <= 0L) {}
    while (a(b.a.DATE) == l) {
      return false;
    }
    return true;
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
  
  public static boolean a(com.cosmo.lib.task.b.c paramC, String paramString)
  {
    if (paramC == null) {}
    do
    {
      return false;
      paramC = paramC.getTaskType();
    } while ((!paramString.equals(paramC)) && (!"allTask".equals(paramString)) && (!"all".equals(paramC)));
    return true;
  }
  
  public static boolean a(String paramString)
  {
    return y.c(paramString);
  }
  
  public static boolean a(ArrayList<com.cosmo.lib.task.b.a> paramArrayList, com.cosmo.lib.task.b.a paramA)
  {
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        String str = ((com.cosmo.lib.task.b.a)paramArrayList.next()).getId();
        if ((!TextUtils.isEmpty(str)) && (str.equals(paramA.getId()))) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static int b(HashMap<String, Integer> paramHashMap, String paramString)
  {
    if (paramHashMap == null) {
      return 3;
    }
    if ((paramHashMap.containsKey(paramString)) && (((Integer)paramHashMap.get(paramString)).intValue() > -1)) {
      return ((Integer)paramHashMap.get(paramString)).intValue();
    }
    if ((paramHashMap.containsKey("default")) && (((Integer)paramHashMap.get("default")).intValue() > -1)) {
      return ((Integer)paramHashMap.get("default")).intValue();
    }
    return 3;
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
    catch (NumberFormatException localNumberFormatException)
    {
      com.cosmo.lib.a.e.c("TaskTools NumberFormatException,parseLong：" + paramString);
    }
    return 0L;
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
            str = com.cosmo.lib.a.f.a().toLowerCase();
            localObject1 = localObject2;
            if (!TextUtils.isEmpty(str)) {
              if ((!str.equals("tw")) && (!str.equals("hk")))
              {
                localObject1 = localObject2;
                if (!str.equals("mo")) {}
              }
              else
              {
                localObject1 = "tw";
              }
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
  
  public static ArrayList<com.cosmo.lib.task.b.a> b(ArrayList<com.cosmo.lib.task.b.a> paramArrayList)
  {
    if ((paramArrayList != null) && (paramArrayList.size() > 0)) {
      Collections.sort(paramArrayList, new f());
    }
    return paramArrayList;
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
        com.cosmo.lib.a.e.c("TaskTools isPortrait NullPointerException:" + paramContext.getMessage());
      }
    }
    return true;
  }
  
  private static boolean b(Context paramContext, String paramString)
  {
    if ((paramString.equals("")) || (paramContext == null)) {
      return false;
    }
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getRunningAppProcesses();
    try
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.processName.equals(paramString))
        {
          int i = localRunningAppProcessInfo.importance;
          if (i == 100) {
            return true;
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean b(com.cosmo.lib.task.b.a paramA)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    if (paramA == null) {}
    c localC;
    do
    {
      do
      {
        return bool2;
        localC = paramA.getTaskState();
        if ((c.COMPLETED.equals(localC)) || (c.CLOSE.equals(localC))) {
          bool1 = true;
        }
        bool2 = bool1;
      } while (!com.cosmo.lib.a.e.a());
      bool2 = bool1;
    } while (!bool1);
    com.cosmo.lib.a.e.b("TaskTools task state:" + localC + " taskId:" + paramA.getId());
    return bool1;
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
  
  public static com.cosmo.lib.task.b.b c(com.cosmo.lib.task.b.a paramA)
  {
    switch (paramA.getTaskContent().getTaskBranch().size())
    {
    default: 
      return null;
    case 1: 
      return a(paramA, a.INDEX_ONE);
    case 2: 
      return a(paramA, a.INDEX_TWO);
    }
    return a(paramA, a.INDEX_THREE);
  }
  
  public static String c()
  {
    String str = b.z;
    if (b.A)
    {
      b.A = false;
      str = "sdk_inter";
    }
    return str;
  }
  
  public static HashMap<String, Integer> c(Context paramContext)
  {
    Object localObject1 = null;
    if (paramContext == null) {}
    PackageManager localPackageManager;
    do
    {
      return localObject1;
      localPackageManager = paramContext.getPackageManager();
    } while (localPackageManager == null);
    localObject1 = localPackageManager.getInstalledPackages(0);
    paramContext = new HashMap(((List)localObject1).size());
    String[] arrayOfString = new String[27];
    arrayOfString[0] = "reddit";
    arrayOfString[1] = "kakao";
    arrayOfString[2] = "weheartit";
    arrayOfString[3] = "beetalk";
    arrayOfString[4] = "qq";
    arrayOfString[5] = "qqweibo";
    arrayOfString[6] = "quora";
    arrayOfString[7] = "line";
    arrayOfString[8] = "kik";
    arrayOfString[9] = "vk";
    arrayOfString[10] = "facebook";
    arrayOfString[11] = "youtube";
    arrayOfString[12] = "pinterest";
    arrayOfString[13] = "wechat";
    arrayOfString[14] = "googleplay";
    arrayOfString[15] = "twitter";
    arrayOfString[16] = "weibo";
    arrayOfString[17] = "flicker";
    arrayOfString[18] = "instagram";
    arrayOfString[19] = "tumblr";
    arrayOfString[20] = "askfm";
    arrayOfString[21] = "whatsapp";
    arrayOfString[22] = "messenger";
    arrayOfString[23] = "musically";
    arrayOfString[24] = "googleplus";
    arrayOfString[25] = "snapchat";
    arrayOfString[26] = "linkedin";
    Iterator localIterator = ((List)localObject1).iterator();
    label334:
    label381:
    label422:
    label425:
    for (;;)
    {
      localObject1 = paramContext;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject1 = ((PackageInfo)localIterator.next()).applicationInfo;
      Object localObject2 = ((ApplicationInfo)localObject1).loadLabel(localPackageManager);
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject2 = ((CharSequence)localObject2).toString().toLowerCase();
        if ((((ApplicationInfo)localObject1).flags & 0x1) != 0)
        {
          int j = arrayOfString.length;
          i = 0;
          if (i >= j) {
            break label422;
          }
          if (!((String)localObject2).contains(arrayOfString[i])) {
            break label381;
          }
        }
        for (int i = 0;; i = 1)
        {
          if (i != 0) {
            break label425;
          }
          if (((String)localObject2).contains(" "))
          {
            paramContext = a(paramContext, (String)localObject2, " ");
            break;
            i += 1;
            break label334;
          }
          if (((String)localObject2).contains("&"))
          {
            paramContext = a(paramContext, (String)localObject2, "&");
            break;
          }
          paramContext = c(paramContext, (String)localObject2);
          break;
        }
      }
    }
  }
  
  public static HashMap<String, com.cosmo.lib.task.b.a> c(ArrayList<com.cosmo.lib.task.b.a> paramArrayList)
  {
    if (paramArrayList == null) {
      return null;
    }
    HashMap localHashMap = new HashMap(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      com.cosmo.lib.task.b.a localA = (com.cosmo.lib.task.b.a)paramArrayList.next();
      if ((localA != null) && (!TextUtils.isEmpty(localA.getId()))) {
        localHashMap.put(localA.getId(), localA);
      }
    }
    return localHashMap;
  }
  
  private static HashMap<String, Integer> c(HashMap<String, Integer> paramHashMap, String paramString)
  {
    paramString = paramString.toLowerCase();
    if (paramHashMap.containsKey(paramString))
    {
      paramHashMap.put(paramString, Integer.valueOf(((Integer)paramHashMap.get(paramString)).intValue() + 1));
      return paramHashMap;
    }
    paramHashMap.put(paramString, Integer.valueOf(1));
    return paramHashMap;
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
      com.cosmo.lib.a.e.a(paramString);
    }
  }
  
  private static boolean c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = com.cosmo.lib.task.util.processutil.f.a(paramContext);
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
  
  public static int d(com.cosmo.lib.task.b.a paramA)
  {
    if ((paramA == null) || (paramA.getTaskContent() == null)) {
      return R.style.cosmo_task_full_dialog;
    }
    paramA = paramA.getTaskContent().getSdkInterStyle();
    if ("photo".equals(paramA)) {
      return R.style.cosmo_task_full_dialog;
    }
    if ("photo_text".equals(paramA)) {
      return R.style.cosmo_task_full_dialog;
    }
    return R.style.cosmo_task_full_dialog;
  }
  
  public static com.cosmo.lib.task.b.a d(ArrayList<com.cosmo.lib.task.b.a> paramArrayList)
  {
    int j = 0;
    if ((paramArrayList == null) || (paramArrayList.size() <= 0)) {
      return null;
    }
    Object localObject1 = paramArrayList.iterator();
    for (int i = 0; ((Iterator)localObject1).hasNext(); i = ((com.cosmo.lib.task.b.a)((Iterator)localObject1).next()).getWeight().intValue() + i) {}
    localObject1 = new ArrayList(paramArrayList.size());
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    Object localObject2 = b(paramArrayList);
    paramArrayList = "";
    localObject2 = ((ArrayList)localObject2).iterator();
    int k;
    if (((Iterator)localObject2).hasNext())
    {
      com.cosmo.lib.task.b.a localA = (com.cosmo.lib.task.b.a)((Iterator)localObject2).next();
      k = localA.getWeight().intValue() * 100 / i;
      ((ArrayList)localObject1).add(Integer.valueOf(k));
      localArrayList.add(localA);
      if (!com.cosmo.lib.a.e.a()) {
        break label452;
      }
      paramArrayList = paramArrayList + "[taskId" + localA.getId() + ",percent:" + k + ",weight:" + localA.getWeight() + "]; ";
    }
    label452:
    for (;;)
    {
      break;
      int m = new Random().nextInt(100);
      localArrayList = b(localArrayList);
      k = 0;
      i = j;
      j = k;
      while (i < ((ArrayList)localObject1).size())
      {
        j += ((Integer)((ArrayList)localObject1).get(i)).intValue();
        if (m < j)
        {
          if (com.cosmo.lib.a.e.a())
          {
            com.cosmo.lib.a.e.b("task follow calculateTaskNumerator msg,size:" + ((ArrayList)localObject1).size() + "," + paramArrayList);
            com.cosmo.lib.a.e.b("task follow calculateTaskNumerator Random number:" + m + " percent:" + ((ArrayList)localObject1).get(i) + " block:" + (j - ((Integer)((ArrayList)localObject1).get(i)).intValue()) + "~" + j + " taskId:" + ((com.cosmo.lib.task.b.a)localArrayList.get(i)).getId() + " weight:" + ((com.cosmo.lib.task.b.a)localArrayList.get(i)).getWeight());
          }
          return (com.cosmo.lib.task.b.a)localArrayList.get(i);
        }
        i += 1;
      }
      return null;
    }
  }
  
  public static ArrayList<String> d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    ArrayList localArrayList;
    if (!paramString.contains(","))
    {
      localArrayList = new ArrayList(1);
      localArrayList.add(paramString);
      return localArrayList;
    }
    paramString = paramString.trim();
    String[] arrayOfString;
    int j;
    int i;
    if (paramString.contains("null"))
    {
      arrayOfString = paramString.split("null");
      j = arrayOfString.length;
      i = 0;
      if (i < j)
      {
        localArrayList = arrayOfString[i];
        if ((!TextUtils.isEmpty(localArrayList)) && (!localArrayList.equals("null"))) {
          paramString = localArrayList;
        }
      }
    }
    for (;;)
    {
      paramString = paramString.split(",");
      i = paramString.length;
      if (i <= 0)
      {
        return null;
        i += 1;
        break;
      }
      localArrayList = new ArrayList(i);
      j = paramString.length;
      i = 0;
      while (i < j)
      {
        arrayOfString = paramString[i];
        if (!TextUtils.isEmpty(arrayOfString)) {
          localArrayList.add(arrayOfString);
        }
        i += 1;
      }
      localArrayList.trimToSize();
      return localArrayList;
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
        if (com.cosmo.lib.a.e.a()) {
          com.cosmo.lib.a.e.b("TaskTools set is-----SCREEN_ORIENTATION_PORTRAIT");
        }
      }
      else if (i == 1)
      {
        paramActivity.setRequestedOrientation(1);
        if (com.cosmo.lib.a.e.a())
        {
          com.cosmo.lib.a.e.b("TaskTools set is SCREEN_ORIENTATION_PORTRAIT");
          return;
        }
      }
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static String e(com.cosmo.lib.task.b.a paramA)
  {
    if ((paramA == null) || (paramA.getTaskContent() == null)) {
      return null;
    }
    return paramA.getTaskContent().getTaskType();
  }
  
  public static HashMap<String, Double> e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      localObject1 = d(paramString.trim());
    } while ((localObject1 == null) || (((ArrayList)localObject1).size() <= 0));
    paramString = new HashMap(((ArrayList)localObject1).size());
    Object localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (String)((Iterator)localObject1).next();
      if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (((String)localObject2).contains(":")))
      {
        localObject2 = ((String)localObject2).split(":");
        if (localObject2.length == 2) {
          try
          {
            double d = Double.parseDouble(localObject2[1]);
            paramString.put(localObject2[0].toLowerCase().trim(), Double.valueOf(d));
          }
          catch (NumberFormatException localNumberFormatException)
          {
            com.cosmo.lib.a.e.c("TaskTools splitData NumberFormatException");
          }
        }
      }
    }
    return paramString;
  }
  
  public static String f(String paramString)
  {
    try
    {
      if ((!TextUtils.isEmpty(paramString)) && (com.cosmo.lib.task.d.c.a().c().b(paramString)))
      {
        int i = paramString.lastIndexOf("/");
        if (i == -1) {
          i = 0;
        }
        for (;;)
        {
          paramString = paramString.substring(i);
          paramString = "file://" + b.G + q.a(paramString);
          return paramString;
          i += 1;
        }
      }
      return "";
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean f(com.cosmo.lib.task.b.a paramA)
  {
    return a(g(paramA));
  }
  
  public static String g(com.cosmo.lib.task.b.a paramA)
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
  
  public static String g(String paramString)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    return str;
  }
  
  public static ArrayList<String> h(String paramString)
  {
    ArrayList localArrayList = new ArrayList(5);
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
  
  public static boolean h(com.cosmo.lib.task.b.a paramA)
  {
    if (paramA != null)
    {
      paramA = paramA.getTaskContent();
      if (paramA != null) {
        return "app".equals(paramA.getTaskType());
      }
    }
    return false;
  }
  
  public static String i(String paramString)
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
  
  public static boolean i(com.cosmo.lib.task.b.a paramA)
  {
    if (paramA == null) {}
    ArrayList localArrayList;
    do
    {
      return false;
      localArrayList = com.cosmo.lib.task.d.b.a().d();
    } while (!b(com.cosmo.lib.task.d.b.a().a(localArrayList, paramA.getId())));
    return true;
  }
  
  public static String j(String paramString)
  {
    paramString = d(paramString);
    if ((paramString != null) && (paramString.size() > 0))
    {
      Iterator localIterator = paramString.iterator();
      paramString = "";
      for (;;)
      {
        str = paramString;
        if (!localIterator.hasNext()) {
          break;
        }
        str = f((String)localIterator.next());
        if (TextUtils.isEmpty(paramString)) {
          paramString = str;
        } else {
          paramString = paramString + "," + str;
        }
      }
    }
    String str = "";
    return str;
  }
  
  public static boolean j(com.cosmo.lib.task.b.a paramA)
  {
    paramA = a(paramA);
    return (paramA != null) && (paramA.isVerificationByApp());
  }
  
  public static String k(String paramString)
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
  
  public static boolean k(com.cosmo.lib.task.b.a paramA)
  {
    if (paramA != null)
    {
      ArrayList localArrayList = paramA.getLocationTypeList();
      if ((localArrayList != null) && (localArrayList.size() > 0))
      {
        boolean bool = localArrayList.contains("none");
        if ((com.cosmo.lib.a.e.a()) && (bool)) {
          com.cosmo.lib.a.e.b("task isFilterNoneTask filterNone,taskId:" + paramA.getId());
        }
        return bool;
      }
    }
    return false;
  }
  
  public static String l(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (!com.cosmo.lib.task.d.c.a().c().b(paramString)) {
      return null;
    }
    int i = paramString.lastIndexOf("/");
    if (i == -1) {
      i = 0;
    }
    for (;;)
    {
      paramString = paramString.substring(i);
      return b.G + q.a(paramString);
      i += 1;
    }
  }
  
  public static boolean l(com.cosmo.lib.task.b.a paramA)
  {
    return a(paramA.getCloseTaskTime()) == a(b.a.DATE);
  }
  
  public static boolean m(com.cosmo.lib.task.b.a paramA)
  {
    boolean bool = true;
    if (paramA == null) {}
    int i;
    do
    {
      return false;
      i = paramA.getShow_frequency();
    } while (i == 0);
    if ((i == 100) || (i == -1))
    {
      if (com.cosmo.lib.a.e.a()) {
        com.cosmo.lib.a.e.b("task show_frequency task percent:" + i + " taskId:" + paramA.getId());
      }
      return true;
    }
    int j = new Random().nextInt(100);
    if (com.cosmo.lib.a.e.a()) {
      com.cosmo.lib.a.e.b("task show_frequency Random number:" + j + " task percent:" + i + " taskId:" + paramA.getId());
    }
    if (j < i) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public static boolean n(com.cosmo.lib.task.b.a paramA)
  {
    return "follow".equals(paramA.getTaskContent().getTaskType());
  }
  
  private static boolean o(com.cosmo.lib.task.b.a paramA)
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
}
