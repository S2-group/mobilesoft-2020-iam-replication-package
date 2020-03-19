package com.ew.sdk.task.util;

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
import com.ew.sdk.R.color;
import com.ew.sdk.R.drawable;
import com.ew.sdk.R.string;
import com.ew.sdk.R.style;
import com.ew.sdk.a.l;
import com.ew.sdk.a.q;
import com.ew.sdk.a.y;
import com.ew.sdk.task.util.processutil.AndroidAppProcess;
import com.ew.sdk.task.util.processutil.h;
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
  
  public static int a(com.ew.sdk.task.b.a paramA, com.ew.sdk.task.b.b paramB, com.ew.sdk.task.b.d paramD)
  {
    if ((paramB != null) && (paramA != null))
    {
      if (!b.D) {
        return 0;
      }
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
      int j = paramB.getRewardsCount();
      int i = j;
      if (j > 0)
      {
        d2 = j;
        Double.isNaN(d2);
        j = (int)(d1 * d2);
        i = j;
        if (j <= 0) {
          i = 1;
        }
      }
      j = i;
      if (o(paramA)) {
        j = i * 2;
      }
      return j;
    }
    return 0;
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
      com.ew.sdk.a.e.a(localNumberFormatException);
    }
    return 0L;
  }
  
  public static long a(b.a paramA)
  {
    Object localObject = "";
    switch (d.1.a[paramA.ordinal()])
    {
    default: 
      paramA = (b.a)localObject;
      break;
    case 4: 
      paramA = "yyyyMMddHHmmss";
      break;
    case 3: 
      paramA = "yyyyMMddHHmm";
      break;
    case 2: 
      paramA = "yyyyMMddHH";
      break;
    case 1: 
      paramA = "yyyyMMdd";
    }
    try
    {
      if (!TextUtils.isEmpty(paramA))
      {
        localObject = new Date();
        paramA = new SimpleDateFormat(paramA).format((Date)localObject);
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
  
  public static TextView a(Activity paramActivity, int paramInt)
  {
    if (paramActivity == null) {
      return null;
    }
    if (paramInt == 1) {
      paramInt = R.drawable.ew_task_two_adlabel_bg;
    } else {
      paramInt = R.drawable.ew_task_adlabel_bg;
    }
    TextView localTextView = new TextView(paramActivity);
    localTextView.setGravity(17);
    localTextView.setText(R.string.ew_task_adLabel);
    int i = com.ew.sdk.a.f.a(paramActivity, 18);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(i, i);
    localLayoutParams.gravity = 80;
    localTextView.setLayoutParams(localLayoutParams);
    localTextView.setTextSize(10.0F);
    localTextView.setTextColor(paramActivity.getResources().getColor(R.color.ew_task_adlabel));
    localTextView.setBackgroundResource(paramInt);
    return localTextView;
  }
  
  public static com.ew.sdk.task.b.b a(com.ew.sdk.task.b.a paramA)
  {
    if (paramA == null) {
      return null;
    }
    Object localObject = paramA.getTaskContent();
    if (localObject == null) {
      return null;
    }
    paramA = ((com.ew.sdk.task.b.c)localObject).getCurrentTaskBranchIndex();
    localObject = ((com.ew.sdk.task.b.c)localObject).getTaskBranch().iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.ew.sdk.task.b.b localB = (com.ew.sdk.task.b.b)((Iterator)localObject).next();
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
  
  public static com.ew.sdk.task.b.b a(com.ew.sdk.task.b.a paramA, a paramA1)
  {
    if (paramA == null) {
      return null;
    }
    paramA = paramA.getTaskContent();
    if (paramA == null) {
      return null;
    }
    paramA = paramA.getTaskBranch();
    if (paramA != null)
    {
      if (paramA.size() <= 0) {
        return null;
      }
      paramA = paramA.iterator();
      while (paramA.hasNext())
      {
        com.ew.sdk.task.b.b localB = (com.ew.sdk.task.b.b)paramA.next();
        if ((localB != null) && (paramA1.equals(localB.getBranchIndex()))) {
          return localB;
        }
      }
      return null;
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
  
  public static String a(Context paramContext, com.ew.sdk.task.b.c paramC, String paramString, boolean paramBoolean)
  {
    if (paramContext != null)
    {
      if (TextUtils.isEmpty(paramString)) {
        return paramString;
      }
      String str = paramContext.getPackageName();
      if (TextUtils.isEmpty(str)) {
        return paramString;
      }
      paramContext = paramString;
      try
      {
        paramString = paramString.replace("$ACCOUNT", paramC.getTargetId());
        if (!paramBoolean) {}
      }
      catch (Exception paramC) {}
    }
    try
    {
      paramContext = paramString.replace("$PKGNAME", paramC.getAppStore());
    }
    catch (Exception paramC)
    {
      for (;;)
      {
        paramContext = paramString;
      }
    }
    paramContext = paramString.replace("$PKGNAME", paramC.getTargetPkgName());
    for (;;)
    {
      paramContext = paramC;
      paramC = paramC.replace("$HostPKGNAME", str);
      return paramC;
      paramC.printStackTrace();
      return paramContext;
      return paramString;
      paramC = paramContext;
    }
  }
  
  public static String a(com.ew.sdk.task.b.a paramA, String[] paramArrayOfString)
  {
    if (paramA != null)
    {
      paramA = paramA.getTaskContent();
      if (paramA != null)
      {
        paramA = paramA.getTargetFeature();
        break label23;
      }
    }
    paramA = null;
    label23:
    if (TextUtils.isEmpty(paramA)) {
      return paramA;
    }
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (paramA.contains(str)) {
        return str;
      }
      i += 1;
    }
    return paramA;
  }
  
  public static String a(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      if (TextUtils.isEmpty(paramString1)) {
        return paramString1;
      }
      paramString1 = new StringBuilder(paramString1).insert(paramInt, paramString2).toString();
      return paramString1;
    }
    catch (NullPointerException paramString1)
    {
      paramString1.printStackTrace();
    }
    return "";
  }
  
  public static ArrayList<com.ew.sdk.task.b.a> a(ArrayList<com.ew.sdk.task.b.a> paramArrayList)
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
    if (TextUtils.isEmpty(paramString)) {
      return paramHashMap;
    }
    paramString = paramString.split(":");
    if (paramString.length == 2) {
      paramHashMap.put(paramString[0], Integer.valueOf(Integer.parseInt(paramString[1])));
    }
    if (paramString.length == 1) {
      paramHashMap.put(paramString[0], Integer.valueOf(-1));
    }
    return paramHashMap;
  }
  
  private static HashMap<String, Integer> a(HashMap<String, Integer> paramHashMap, String paramString1, String paramString2)
  {
    paramString1 = paramString1.split(paramString2);
    if (paramString1.length <= 0) {
      return paramHashMap;
    }
    int j = paramString1.length;
    int i = 0;
    while (i < j)
    {
      paramString2 = paramString1[i];
      if ((!TextUtils.isEmpty(paramString2)) && (!paramString2.equals("&"))) {
        if (paramString2.contains("&")) {
          paramHashMap = a(paramHashMap, paramString2, "&");
        } else {
          paramHashMap = c(paramHashMap, paramString2);
        }
      }
      i += 1;
    }
    return paramHashMap;
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    if (paramActivity == null) {
      return;
    }
    if (com.ew.sdk.a.e.a())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("TaskTools  task copy content:");
      localStringBuilder.append(paramString);
      com.ew.sdk.a.e.b(localStringBuilder.toString());
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
        b.u = paramString2;
        return;
      case 1: 
        b.s = paramString2;
        return;
      }
      b.r = paramString2;
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
  
  public static boolean a(com.ew.sdk.task.b.c paramC, String paramString)
  {
    if (paramC == null) {
      return false;
    }
    paramC = paramC.getTaskType();
    return (paramString.equals(paramC)) || ("allTask".equals(paramString)) || ("all".equals(paramC));
  }
  
  public static boolean a(String paramString)
  {
    return y.c(paramString);
  }
  
  public static boolean a(ArrayList<com.ew.sdk.task.b.a> paramArrayList, com.ew.sdk.task.b.a paramA)
  {
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        String str = ((com.ew.sdk.task.b.a)paramArrayList.next()).getId();
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
      if (TextUtils.isEmpty(paramString)) {
        break label43;
      }
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      StringBuilder localStringBuilder;
      label43:
      for (;;) {}
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("TaskTools NumberFormatException,parseLong：");
    localStringBuilder.append(paramString);
    com.ew.sdk.a.e.c(localStringBuilder.toString());
    return 0L;
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
          break label96;
        }
        String str3 = com.ew.sdk.a.f.a().toLowerCase();
        str1 = str2;
        if (TextUtils.isEmpty(str3)) {
          break label96;
        }
        if ((!str3.equals("tw")) && (!str3.equals("hk")))
        {
          str1 = str2;
          if (!str3.equals("mo")) {}
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
    label96:
    Object localObject = str1;
    if (TextUtils.isEmpty(str1)) {
      localObject = "en";
    }
    return localObject;
  }
  
  public static ArrayList<com.ew.sdk.task.b.a> b(ArrayList<com.ew.sdk.task.b.a> paramArrayList)
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
  
  private static boolean b(Context paramContext, String paramString)
  {
    if (!paramString.equals(""))
    {
      if (paramContext == null) {
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
    return false;
  }
  
  public static boolean b(com.ew.sdk.task.b.a paramA)
  {
    boolean bool = false;
    if (paramA == null) {
      return false;
    }
    c localC = paramA.getTaskState();
    if ((c.COMPLETED.equals(localC)) || (c.CLOSE.equals(localC))) {
      bool = true;
    }
    if ((com.ew.sdk.a.e.a()) && (bool))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("TaskTools task state:");
      localStringBuilder.append(localC);
      localStringBuilder.append(" taskId:");
      localStringBuilder.append(paramA.getId());
      com.ew.sdk.a.e.b(localStringBuilder.toString());
    }
    return bool;
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
  
  public static com.ew.sdk.task.b.b c(com.ew.sdk.task.b.a paramA)
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
  
  public static HashMap<String, Integer> c(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager == null) {
      return null;
    }
    Object localObject1 = localPackageManager.getInstalledPackages(0);
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
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      ApplicationInfo localApplicationInfo = ((PackageInfo)((Iterator)localObject1).next()).applicationInfo;
      Object localObject2 = localApplicationInfo.loadLabel(localPackageManager);
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject2 = ((CharSequence)localObject2).toString().toLowerCase();
        if ((localApplicationInfo.flags & 0x1) != 0)
        {
          int j = arrayOfString.length;
          int i = 0;
          while (i < j)
          {
            if (((String)localObject2).contains(arrayOfString[i]))
            {
              i = 0;
              break label368;
            }
            i += 1;
          }
          i = 1;
          label368:
          if (i != 0) {}
        }
        else if (((String)localObject2).contains(" "))
        {
          paramContext = a(paramContext, (String)localObject2, " ");
        }
        else if (((String)localObject2).contains("&"))
        {
          paramContext = a(paramContext, (String)localObject2, "&");
        }
        else
        {
          paramContext = c(paramContext, (String)localObject2);
        }
      }
    }
    return paramContext;
  }
  
  public static HashMap<String, com.ew.sdk.task.b.a> c(ArrayList<com.ew.sdk.task.b.a> paramArrayList)
  {
    if (paramArrayList == null) {
      return null;
    }
    HashMap localHashMap = new HashMap(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      com.ew.sdk.task.b.a localA = (com.ew.sdk.task.b.a)paramArrayList.next();
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
        if (paramString.exists())
        {
          paramString.delete();
          return;
        }
      }
    }
    catch (Exception paramString)
    {
      com.ew.sdk.a.e.a(paramString);
    }
  }
  
  private static boolean c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = h.a(paramContext);
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
  
  public static int d(com.ew.sdk.task.b.a paramA)
  {
    if ((paramA != null) && (paramA.getTaskContent() != null))
    {
      paramA = paramA.getTaskContent().getSdkInterStyle();
      if ("photo".equals(paramA)) {
        return R.style.ew_task_full_dialog;
      }
      if ("photo_text".equals(paramA)) {
        return R.style.ew_task_full_dialog;
      }
      return R.style.ew_task_full_dialog;
    }
    return R.style.ew_task_full_dialog;
  }
  
  public static com.ew.sdk.task.b.a d(ArrayList<com.ew.sdk.task.b.a> paramArrayList)
  {
    if (paramArrayList != null)
    {
      if (paramArrayList.size() <= 0) {
        return null;
      }
      Object localObject1 = paramArrayList.iterator();
      int k = 0;
      int i = 0;
      while (((Iterator)localObject1).hasNext()) {
        i += ((com.ew.sdk.task.b.a)((Iterator)localObject1).next()).getWeight().intValue();
      }
      localObject1 = new ArrayList(paramArrayList.size());
      ArrayList localArrayList = new ArrayList(paramArrayList.size());
      Object localObject2 = b(paramArrayList);
      paramArrayList = "";
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        com.ew.sdk.task.b.a localA = (com.ew.sdk.task.b.a)((Iterator)localObject2).next();
        j = localA.getWeight().intValue() * 100 / i;
        ((ArrayList)localObject1).add(Integer.valueOf(j));
        localArrayList.add(localA);
        if (com.ew.sdk.a.e.a())
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramArrayList);
          localStringBuilder.append("[taskId");
          localStringBuilder.append(localA.getId());
          localStringBuilder.append(",percent:");
          localStringBuilder.append(j);
          localStringBuilder.append(",weight:");
          localStringBuilder.append(localA.getWeight());
          localStringBuilder.append("]; ");
          paramArrayList = localStringBuilder.toString();
        }
      }
      int m = new Random().nextInt(100);
      localArrayList = b(localArrayList);
      int j = 0;
      i = k;
      while (i < ((ArrayList)localObject1).size())
      {
        j += ((Integer)((ArrayList)localObject1).get(i)).intValue();
        if (m < j)
        {
          if (com.ew.sdk.a.e.a())
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("task follow calculateTaskNumerator msg,size:");
            ((StringBuilder)localObject2).append(((ArrayList)localObject1).size());
            ((StringBuilder)localObject2).append(",");
            ((StringBuilder)localObject2).append(paramArrayList);
            com.ew.sdk.a.e.b(((StringBuilder)localObject2).toString());
            paramArrayList = new StringBuilder();
            paramArrayList.append("task follow calculateTaskNumerator Random number:");
            paramArrayList.append(m);
            paramArrayList.append(" percent:");
            paramArrayList.append(((ArrayList)localObject1).get(i));
            paramArrayList.append(" block:");
            paramArrayList.append(j - ((Integer)((ArrayList)localObject1).get(i)).intValue());
            paramArrayList.append("~");
            paramArrayList.append(j);
            paramArrayList.append(" taskId:");
            paramArrayList.append(((com.ew.sdk.task.b.a)localArrayList.get(i)).getId());
            paramArrayList.append(" weight:");
            paramArrayList.append(((com.ew.sdk.task.b.a)localArrayList.get(i)).getWeight());
            com.ew.sdk.a.e.b(paramArrayList.toString());
          }
          return (com.ew.sdk.task.b.a)localArrayList.get(i);
        }
        i += 1;
      }
      return null;
    }
    return null;
  }
  
  public static ArrayList<String> d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if (!paramString.contains(","))
    {
      localObject = new ArrayList(1);
      ((ArrayList)localObject).add(paramString);
      return localObject;
    }
    Object localObject = paramString.trim();
    boolean bool = ((String)localObject).contains("null");
    int j = 0;
    paramString = (String)localObject;
    String[] arrayOfString;
    if (bool)
    {
      arrayOfString = ((String)localObject).split("null");
      k = arrayOfString.length;
      i = 0;
      for (;;)
      {
        paramString = (String)localObject;
        if (i >= k) {
          break;
        }
        paramString = arrayOfString[i];
        if ((!TextUtils.isEmpty(paramString)) && (!paramString.equals("null"))) {
          break;
        }
        i += 1;
      }
    }
    paramString = paramString.split(",");
    int i = paramString.length;
    if (i <= 0) {
      return null;
    }
    localObject = new ArrayList(i);
    int k = paramString.length;
    i = j;
    while (i < k)
    {
      arrayOfString = paramString[i];
      if (!TextUtils.isEmpty(arrayOfString)) {
        ((ArrayList)localObject).add(arrayOfString);
      }
      i += 1;
    }
    ((ArrayList)localObject).trimToSize();
    return localObject;
  }
  
  public static void d(Activity paramActivity)
  {
    try
    {
      int i = paramActivity.getResources().getConfiguration().orientation;
      if (i == 2)
      {
        paramActivity.setRequestedOrientation(0);
        if (com.ew.sdk.a.e.a()) {
          com.ew.sdk.a.e.b("TaskTools set is-----SCREEN_ORIENTATION_PORTRAIT");
        }
      }
      else if (i == 1)
      {
        paramActivity.setRequestedOrientation(1);
        if (com.ew.sdk.a.e.a())
        {
          com.ew.sdk.a.e.b("TaskTools set is SCREEN_ORIENTATION_PORTRAIT");
          return;
        }
      }
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static String e(com.ew.sdk.task.b.a paramA)
  {
    if ((paramA != null) && (paramA.getTaskContent() != null)) {
      return paramA.getTaskContent().getTaskType();
    }
    return null;
  }
  
  public static HashMap<String, Double> e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    Object localObject1 = d(paramString.trim());
    if (localObject1 != null)
    {
      if (((ArrayList)localObject1).size() <= 0) {
        return null;
      }
      paramString = new HashMap(((ArrayList)localObject1).size());
      localObject1 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (String)((Iterator)localObject1).next();
        if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (((String)localObject2).contains(":")))
        {
          localObject2 = ((String)localObject2).split(":");
          if (localObject2.length != 2) {}
        }
        try
        {
          double d = Double.parseDouble(localObject2[1]);
          paramString.put(localObject2[0].toLowerCase().trim(), Double.valueOf(d));
        }
        catch (NumberFormatException localNumberFormatException)
        {
          for (;;) {}
        }
        com.ew.sdk.a.e.c("TaskTools splitData NumberFormatException");
      }
      return paramString;
    }
    return null;
  }
  
  public static ArrayList<String> f(String paramString)
  {
    ArrayList localArrayList = new ArrayList(5);
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
  
  public static boolean f(com.ew.sdk.task.b.a paramA)
  {
    return a(g(paramA));
  }
  
  public static String g(com.ew.sdk.task.b.a paramA)
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
  
  public static String h(String paramString)
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
  
  public static boolean h(com.ew.sdk.task.b.a paramA)
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
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if (com.ew.sdk.task.d.c.a().c().b(paramString))
    {
      int i = paramString.lastIndexOf("/");
      if (i == -1) {
        i = 0;
      } else {
        i += 1;
      }
      paramString = paramString.substring(i);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(b.G);
      localStringBuilder.append(q.a(paramString));
      return localStringBuilder.toString();
    }
    return null;
  }
  
  public static boolean i(com.ew.sdk.task.b.a paramA)
  {
    if (paramA == null) {
      return false;
    }
    ArrayList localArrayList = com.ew.sdk.task.d.b.a().d();
    return b(com.ew.sdk.task.d.b.a().a(localArrayList, paramA.getId()));
  }
  
  public static boolean j(com.ew.sdk.task.b.a paramA)
  {
    paramA = a(paramA);
    return (paramA != null) && (paramA.isVerificationByApp());
  }
  
  public static boolean k(com.ew.sdk.task.b.a paramA)
  {
    if (paramA != null)
    {
      Object localObject = paramA.getLocationTypeList();
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        boolean bool = ((List)localObject).contains("none");
        if ((com.ew.sdk.a.e.a()) && (bool))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("task isFilterNoneTask filterNone,taskId:");
          ((StringBuilder)localObject).append(paramA.getId());
          com.ew.sdk.a.e.b(((StringBuilder)localObject).toString());
        }
        return bool;
      }
    }
    return false;
  }
  
  public static boolean l(com.ew.sdk.task.b.a paramA)
  {
    return a(paramA.getCloseTaskTime()) == a(b.a.DATE);
  }
  
  public static boolean m(com.ew.sdk.task.b.a paramA)
  {
    boolean bool = false;
    if (paramA == null) {
      return false;
    }
    int i = paramA.getShow_frequency();
    if (i == 0) {
      return false;
    }
    StringBuilder localStringBuilder;
    if ((i != 100) && (i != -1))
    {
      int j = new Random().nextInt(100);
      if (com.ew.sdk.a.e.a())
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("task show_frequency Random number:");
        localStringBuilder.append(j);
        localStringBuilder.append(" task percent:");
        localStringBuilder.append(i);
        localStringBuilder.append(" taskId:");
        localStringBuilder.append(paramA.getId());
        com.ew.sdk.a.e.b(localStringBuilder.toString());
      }
      if (j < i) {
        bool = true;
      }
      return bool;
    }
    if (com.ew.sdk.a.e.a())
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("task show_frequency task percent:");
      localStringBuilder.append(i);
      localStringBuilder.append(" taskId:");
      localStringBuilder.append(paramA.getId());
      com.ew.sdk.a.e.b(localStringBuilder.toString());
    }
    return true;
  }
  
  public static boolean n(com.ew.sdk.task.b.a paramA)
  {
    return "follow".equals(paramA.getTaskContent().getTaskType());
  }
  
  private static boolean o(com.ew.sdk.task.b.a paramA)
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
