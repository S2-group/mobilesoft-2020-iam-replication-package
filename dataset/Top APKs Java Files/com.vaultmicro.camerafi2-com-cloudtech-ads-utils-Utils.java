package com.cloudtech.ads.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.provider.Settings.Secure;
import android.support.annotation.Keep;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class Utils
{
  private static String a = "";
  private static String b = "";
  private static String c = "";
  
  public Utils() {}
  
  public static int a(Context paramContext, int paramInt)
  {
    return (int)paramContext.getResources().getDisplayMetrics().density * paramInt;
  }
  
  public static Drawable a(int paramInt)
  {
    return ContextHolder.getGlobalAppContext().getResources().getDrawable(paramInt);
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      YeLog.e(String.format("[msg=get AndroidId][result=fail]", new Object[0]));
    }
    return "";
  }
  
  public static String a(JSONObject paramJSONObject, String... paramVarArgs)
  {
    if (paramJSONObject == null) {
      paramJSONObject = null;
    }
    for (;;)
    {
      paramVarArgs = paramJSONObject;
      if (paramJSONObject == null) {
        paramVarArgs = "";
      }
      return paramVarArgs;
      int i = 0;
      for (;;)
      {
        if (i >= paramVarArgs.length - 1) {
          break label51;
        }
        paramJSONObject = paramJSONObject.optJSONObject(paramVarArgs[i]);
        if (paramJSONObject == null)
        {
          paramJSONObject = null;
          break;
        }
        i += 1;
      }
      label51:
      if (paramJSONObject == null) {
        paramJSONObject = null;
      } else {
        paramJSONObject = paramJSONObject.optString(paramVarArgs[(paramVarArgs.length - 1)]);
      }
    }
  }
  
  public static List<String> a(List<String> paramList)
  {
    return a(paramList, false, false);
  }
  
  public static List<String> a(List<String> paramList, boolean paramBoolean1, boolean paramBoolean2)
  {
    ArrayList localArrayList = new ArrayList();
    long l2 = System.currentTimeMillis();
    long l1 = l2;
    if (paramBoolean1)
    {
      YeLog.d("NoSense::isEven" + paramBoolean2);
      if (!paramBoolean2) {
        break label143;
      }
      if (l2 % 1000L / 100L % 2L != 0L) {
        break label137;
      }
    }
    label137:
    for (int i = 0;; i = 100)
    {
      l1 = i + l2;
      i = 0;
      while (i < paramList.size())
      {
        String str = (String)paramList.get(i);
        localArrayList.add(str + "&ts=" + l1);
        i += 1;
      }
    }
    label143:
    if (l2 % 1000L / 100L % 2L == 0L) {}
    for (i = 100;; i = 0)
    {
      l1 = i + l2;
      break;
    }
    return localArrayList;
  }
  
  public static void a(View paramView)
  {
    if (paramView == null) {}
    do
    {
      return;
      paramView = paramView.getParent();
    } while ((paramView == null) || ((paramView instanceof AdapterView)));
    ((ViewGroup)paramView).removeAllViews();
  }
  
  public static void a(StringBuilder paramStringBuilder, Map<String, String> paramMap)
  {
    a(paramStringBuilder, paramMap, true);
  }
  
  public static void a(StringBuilder paramStringBuilder, Map<String, String> paramMap, boolean paramBoolean)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = (String)paramMap.get(str1);
      if ((!b(str1)) && (!b(str2)) && (!str2.equals("null")))
      {
        if (paramBoolean)
        {
          paramBoolean = false;
          paramStringBuilder.append("?");
        }
        for (;;)
        {
          paramStringBuilder.append(c(str1));
          paramStringBuilder.append("=");
          paramStringBuilder.append(c(str2));
          break;
          paramStringBuilder.append("&");
        }
      }
    }
  }
  
  public static boolean a(String paramString)
  {
    return (paramString != null) && (!"".equals(paramString));
  }
  
  public static int b(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, ContextHolder.getGlobalAppContext().getResources().getDisplayMetrics());
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).packageName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      YeLog.e("工具-应用包名 Error=" + paramContext.getMessage());
    }
    return "";
  }
  
  public static String b(List<String> paramList)
  {
    if (paramList == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    int i = 1;
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (!b(str))
      {
        if (i != 0) {
          i = 0;
        }
        for (;;)
        {
          localStringBuilder.append(str);
          break;
          localStringBuilder.append(",");
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public static boolean b(String paramString)
  {
    return (paramString == null) || ("".equals(paramString));
  }
  
  private static String c(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new UnsupportedOperationException(paramString);
    }
  }
  
  public static boolean c(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).getState();
    return (paramContext != null) && (NetworkInfo.State.CONNECTED == paramContext);
  }
  
  @Keep
  public static Context checkAndSaveContext(Context paramContext)
  {
    ContextHolder.init(paramContext);
    return ContextHolder.getGlobalAppContext();
  }
  
  /* Error */
  public static String d(Context paramContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: aload_0
    //   3: ifnull +12 -> 15
    //   6: ldc_w 299
    //   9: invokestatic 200	com/cloudtech/ads/utils/Utils:b	(Ljava/lang/String;)Z
    //   12: ifeq +32 -> 44
    //   15: ldc_w 301
    //   18: iconst_1
    //   19: anewarray 4	java/lang/Object
    //   22: dup
    //   23: iconst_0
    //   24: ldc_w 299
    //   27: aastore
    //   28: invokestatic 74	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   31: invokestatic 80	com/cloudtech/ads/utils/YeLog:e	(Ljava/lang/String;)V
    //   34: iconst_0
    //   35: istore_1
    //   36: iload_1
    //   37: ifne +26 -> 63
    //   40: aconst_null
    //   41: astore_3
    //   42: aload_3
    //   43: areturn
    //   44: aload_0
    //   45: ldc_w 299
    //   48: invokestatic 306	android/os/Process:myPid	()I
    //   51: invokestatic 309	android/os/Process:myUid	()I
    //   54: invokevirtual 313	android/content/Context:checkPermission	(Ljava/lang/String;II)I
    //   57: ifne -23 -> 34
    //   60: goto -24 -> 36
    //   63: aload_0
    //   64: ldc_w 315
    //   67: invokevirtual 271	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   70: checkcast 317	android/telephony/TelephonyManager
    //   73: astore_2
    //   74: aload_2
    //   75: ifnull +62 -> 137
    //   78: aload_2
    //   79: invokevirtual 320	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   82: astore_2
    //   83: aload_2
    //   84: astore_3
    //   85: aload_2
    //   86: invokestatic 326	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   89: ifeq -47 -> 42
    //   92: aload_0
    //   93: invokevirtual 58	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   96: ldc 60
    //   98: invokestatic 66	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   101: astore_0
    //   102: aload_0
    //   103: areturn
    //   104: astore_0
    //   105: ldc 12
    //   107: astore_2
    //   108: new 108	java/lang/StringBuilder
    //   111: dup
    //   112: ldc_w 328
    //   115: invokespecial 112	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   118: aload_0
    //   119: invokevirtual 244	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   122: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: invokevirtual 120	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokestatic 80	com/cloudtech/ads/utils/YeLog:e	(Ljava/lang/String;)V
    //   131: aload_2
    //   132: areturn
    //   133: astore_0
    //   134: goto -26 -> 108
    //   137: ldc 12
    //   139: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	paramContext	Context
    //   1	36	1	i	int
    //   73	59	2	localObject1	Object
    //   41	44	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   63	74	104	java/lang/Exception
    //   78	83	104	java/lang/Exception
    //   85	102	133	java/lang/Exception
  }
  
  public static int[] e(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    if (paramContext == null) {
      return new int[] { 0, 0 };
    }
    return new int[] { paramContext.widthPixels, paramContext.heightPixels };
  }
  
  public static boolean f(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo("com.android.vending", 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static String g(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkCountryIso();
  }
  
  @Keep
  public static String getProcessName(Context paramContext, int paramInt)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == paramInt) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  public static int h(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      paramContext = localConnectivityManager.getActiveNetworkInfo();
      if (paramContext != null) {
        return paramContext.getType();
      }
      return 8;
    }
    return 8;
  }
  
  public static String i(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(a))
        {
          paramContext = a;
          return paramContext;
        }
        paramContext = o(paramContext);
        if ((paramContext == null) || (TextUtils.isEmpty(paramContext))) {
          break label91;
        }
        if (!paramContext.equals("null")) {
          break label62;
        }
      }
      finally {}
      a = paramContext;
      continue;
      label62:
      int i = Math.min(3, paramContext.length());
      if (paramContext == null)
      {
        paramContext = "";
      }
      else
      {
        paramContext = paramContext.substring(0, i);
        continue;
        label91:
        paramContext = "";
      }
    }
  }
  
  public static String j(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(b))
        {
          paramContext = b;
          return paramContext;
        }
        paramContext = o(paramContext);
        if ((paramContext == null) || (TextUtils.isEmpty(paramContext))) {
          break label90;
        }
        if (!paramContext.equals("null")) {
          break label62;
        }
      }
      finally {}
      b = paramContext;
      continue;
      label62:
      int i = Math.min(3, paramContext.length());
      if (paramContext == null)
      {
        paramContext = "";
      }
      else
      {
        paramContext = paramContext.substring(i);
        continue;
        label90:
        paramContext = "";
      }
    }
  }
  
  public static String k(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  public static List<String> l(Context paramContext)
  {
    localArrayList = new ArrayList();
    YeLog.d("getInstalledApps::");
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((localApplicationInfo.flags & 0x1) == 0) {
          localArrayList.add(localApplicationInfo.packageName);
        }
      }
      return localArrayList;
    }
    catch (Throwable paramContext)
    {
      YeLog.d("getInstalledApps::" + paramContext.getMessage());
    }
  }
  
  public static String m(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Intent localIntent = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    int i;
    String str;
    label49:
    int j;
    if (localIntent.getIntExtra("status", -1) == 2)
    {
      i = 1;
      if (i == 0) {
        break label166;
      }
      str = "1";
      localStringBuilder.append(str);
      localStringBuilder.append(",");
      localStringBuilder.append(String.valueOf(localIntent.getIntExtra("level", -1)));
      localStringBuilder.append(",");
      j = localIntent.getIntExtra("plugged", -1);
      if (j != 2) {
        break label173;
      }
      i = 1;
      label107:
      if (j != 1) {
        break label178;
      }
      j = 1;
      label114:
      if (i == 0) {
        break label183;
      }
      str = "1";
    }
    for (;;)
    {
      label122:
      localStringBuilder.append(str);
      localStringBuilder.append(",");
      if (p(paramContext)) {}
      for (paramContext = "0";; paramContext = "1")
      {
        localStringBuilder.append(paramContext);
        return localStringBuilder.toString();
        i = 0;
        break;
        label166:
        str = "0";
        break label49;
        label173:
        i = 0;
        break label107;
        label178:
        j = 0;
        break label114;
        label183:
        if (j == 0) {
          break label201;
        }
        str = "2";
        break label122;
      }
      label201:
      str = "0";
    }
  }
  
  public static int n(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Throwable paramContext)
    {
      YeLog.e(paramContext.getMessage());
    }
    return Integer.MAX_VALUE;
  }
  
  private static String o(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    String str = paramContext.getNetworkOperator();
    if ((paramContext.getPhoneType() == 2) && (paramContext.getSimState() == 5)) {
      return paramContext.getSimOperator();
    }
    return str;
  }
  
  private static boolean p(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      if ((i & 0x2) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      YeLog.e(paramContext.getMessage());
    }
    return false;
  }
}
