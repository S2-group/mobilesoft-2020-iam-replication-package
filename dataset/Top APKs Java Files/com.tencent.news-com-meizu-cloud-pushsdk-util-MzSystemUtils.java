package com.meizu.cloud.pushsdk.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.base.a.d;
import com.meizu.cloud.pushsdk.base.c;
import com.meizu.cloud.pushsdk.base.k;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MzSystemUtils
{
  private static final String TAG = "MzSystemUtils";
  
  public MzSystemUtils() {}
  
  public static boolean compareVersion(String paramString1, String paramString2)
  {
    boolean bool = false;
    paramString1 = paramString1.split("\\.");
    paramString2 = paramString2.split("\\.");
    int m = Math.min(paramString1.length, paramString2.length);
    int i = 0;
    int j = 0;
    while (j < m)
    {
      int k = paramString1[j].length() - paramString2[j].length();
      i = k;
      if (k != 0) {
        break;
      }
      k = paramString1[j].compareTo(paramString2[j]);
      i = k;
      if (k != 0) {
        break;
      }
      j += 1;
      i = k;
    }
    if (i != 0) {}
    for (;;)
    {
      if (i >= 0) {
        bool = true;
      }
      return bool;
      i = paramString1.length - paramString2.length;
    }
  }
  
  public static boolean compatApi(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
  
  public static String findReceiver(Context paramContext, String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {}
    do
    {
      return null;
      paramString1 = new Intent(paramString1);
      paramString1.setPackage(paramString2);
      paramContext = paramContext.getPackageManager().queryBroadcastReceivers(paramString1, 0);
    } while ((paramContext == null) || (paramContext.size() <= 0));
    return ((ResolveInfo)paramContext.get(0)).activityInfo.name;
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "";
  }
  
  public static String getAppVersionName(Context paramContext, String paramString)
  {
    try
    {
      paramString = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionName;
      if (paramString != null)
      {
        paramContext = paramString;
        if (paramString.length() > 0) {}
      }
      else
      {
        paramContext = "";
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("VersionInfo", "Exception message " + paramContext.getMessage());
    }
    return "";
  }
  
  public static String getBSSID(Context paramContext)
  {
    try
    {
      paramContext = (WifiManager)paramContext.getSystemService("wifi");
      if (paramContext != null)
      {
        paramContext = paramContext.getConnectionInfo();
        if (paramContext != null)
        {
          paramContext = paramContext.getBSSID();
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "getOperator error " + paramContext.getMessage());
    }
    return null;
  }
  
  public static String getCurrentLanguage()
  {
    try
    {
      String str = Locale.getDefault().getLanguage();
      return str;
    }
    catch (Exception localException)
    {
      DebugLogger.e("MzSystemUtils", "getCurrentLanguage error " + localException.getMessage());
    }
    return null;
  }
  
  public static String getDeviceId(Context paramContext)
  {
    try
    {
      paramContext = c.a(paramContext);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "getDeviceId error " + paramContext.getMessage());
    }
    return null;
  }
  
  public static String getDeviceId(Context paramContext, int paramInt)
  {
    paramContext = getPhoneInfo(paramContext, paramInt, "getDeviceId");
    if (paramContext != null) {
      return (String)paramContext;
    }
    return "";
  }
  
  public static List<String> getInstalledPackage(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager();
      if (paramContext != null)
      {
        paramContext = paramContext.getInstalledPackages(0);
        if (paramContext != null)
        {
          paramContext = paramContext.iterator();
          while (paramContext.hasNext()) {
            localArrayList.add(((PackageInfo)paramContext.next()).packageName);
          }
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "getInstalledPackage error " + paramContext.getMessage());
    }
  }
  
  public static String getLineNumber(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getLine1Number();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "getLineNumber error " + paramContext.getMessage());
    }
    return null;
  }
  
  public static Class[] getMethodParamTypes(String paramString)
  {
    try
    {
      arrayOfMethod = TelephonyManager.class.getDeclaredMethods();
      localObject1 = null;
      i = 0;
    }
    catch (Exception paramString)
    {
      try
      {
        Method[] arrayOfMethod;
        int i;
        Object localObject3;
        if (i < arrayOfMethod.length)
        {
          localObject3 = localObject1;
          localObject2 = localObject1;
          if (paramString.equals(arrayOfMethod[i].getName()))
          {
            localObject2 = localObject1;
            localObject1 = arrayOfMethod[i].getParameterTypes();
            localObject3 = localObject1;
            localObject2 = localObject1;
            if (localObject1.length >= 1)
            {
              localObject2 = localObject1;
              DebugLogger.d("MzSystemUtils", "getMethodParamTypes " + localObject1.length);
              localObject3 = localObject1;
            }
          }
        }
        else
        {
          return localObject3;
        }
        i += 1;
        Object localObject1 = localObject3;
      }
      catch (Exception paramString)
      {
        Object localObject2;
        for (;;) {}
      }
      paramString = paramString;
      localObject2 = null;
    }
    localObject3 = localObject1;
    localObject2 = localObject1;
    DebugLogger.d("MzSystemUtils", "getMethodParamTypes " + paramString.toString());
    return localObject2;
  }
  
  public static String getMzPushServicePackageName(Context paramContext)
  {
    String str = paramContext.getPackageName();
    try
    {
      paramContext = getServicesByPackageName(paramContext, "com.meizu.cloud");
      if ((!TextUtils.isEmpty(paramContext)) && (paramContext.contains("mzservice_v1"))) {
        return "com.meizu.cloud";
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      DebugLogger.i("SystemUtils", "startservice package name " + str);
    }
    return str;
  }
  
  public static String getNetWorkType(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null) {
        switch (paramContext.getType())
        {
        case 0: 
          switch (paramContext.getSubtype())
          {
          }
          break;
        }
      }
    }
    catch (SecurityException paramContext)
    {
      DebugLogger.e("MzSystemUtils", "Security exception checking connection: " + paramContext.getMessage());
      return "";
    }
    return "";
    for (;;)
    {
      return "OTHER";
      return "WIFI";
      return "ETHERNET";
      return "BLUETOOTH";
      continue;
    }
  }
  
  public static String getOperator(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getSimOperator();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "getOperator error " + paramContext.getMessage());
    }
    return null;
  }
  
  public static Object getPhoneInfo(Context paramContext, int paramInt, String paramString)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramString = paramContext.getClass().getMethod(paramString, getMethodParamTypes(paramString));
        if (paramInt >= 0)
        {
          paramContext = paramString.invoke(paramContext, new Object[] { Integer.valueOf(paramInt) });
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      DebugLogger.d("MzSystemUtils", paramContext.toString());
    }
    return null;
  }
  
  public static String getProcessName(Context paramContext)
  {
    try
    {
      int i = Process.myPid();
      paramContext = ((ActivityManager)paramContext.getApplicationContext().getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        DebugLogger.i("MzSystemUtils", "processName " + localRunningAppProcessInfo.processName);
        if (localRunningAppProcessInfo.pid == i)
        {
          paramContext = localRunningAppProcessInfo.processName;
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "getProcessName error " + paramContext.getMessage());
      return null;
    }
    return "";
  }
  
  public static List<String> getRunningProcess(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((ActivityManager.RunningAppProcessInfo)paramContext.next()).processName);
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "can not get running process info so set running true");
    }
  }
  
  private static String getServicesByPackageName(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 4).services;
      if (paramContext == null) {
        return null;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
      int i = 0;
      while (i < paramContext.length)
      {
        if ("com.meizu.cloud.pushsdk.pushservice.MzPushService".equals(paramContext[i].name)) {
          return paramContext[i].processName;
        }
        i += 1;
      }
    }
    return null;
  }
  
  public static String getSn()
  {
    String str = k.a("ro.serialno");
    if (!TextUtils.isEmpty(str)) {
      return str;
    }
    return Build.SERIAL;
  }
  
  /* Error */
  public static int getSubId(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: ldc_w 397
    //   3: invokestatic 403	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   6: astore_2
    //   7: aload_0
    //   8: invokevirtual 407	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   11: aload_2
    //   12: iconst_2
    //   13: anewarray 19	java/lang/String
    //   16: dup
    //   17: iconst_0
    //   18: ldc_w 409
    //   21: aastore
    //   22: dup
    //   23: iconst_1
    //   24: ldc_w 411
    //   27: aastore
    //   28: ldc_w 413
    //   31: iconst_1
    //   32: anewarray 19	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: iload_1
    //   38: invokestatic 416	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   41: aastore
    //   42: aconst_null
    //   43: invokevirtual 422	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   46: astore_2
    //   47: aload_2
    //   48: ifnull +44 -> 92
    //   51: aload_2
    //   52: astore_0
    //   53: aload_2
    //   54: invokeinterface 427 1 0
    //   59: ifeq +33 -> 92
    //   62: aload_2
    //   63: astore_0
    //   64: aload_2
    //   65: aload_2
    //   66: ldc_w 409
    //   69: invokeinterface 430 2 0
    //   74: invokeinterface 434 2 0
    //   79: istore_1
    //   80: aload_2
    //   81: ifnull +9 -> 90
    //   84: aload_2
    //   85: invokeinterface 437 1 0
    //   90: iload_1
    //   91: ireturn
    //   92: aload_2
    //   93: ifnull +9 -> 102
    //   96: aload_2
    //   97: invokeinterface 437 1 0
    //   102: iconst_m1
    //   103: ireturn
    //   104: astore_3
    //   105: aconst_null
    //   106: astore_2
    //   107: aload_2
    //   108: astore_0
    //   109: ldc 8
    //   111: new 125	java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   118: ldc_w 439
    //   121: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: aload_3
    //   125: invokevirtual 262	java/lang/Exception:toString	()Ljava/lang/String;
    //   128: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 138	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: invokestatic 261	com/meizu/cloud/pushinternal/DebugLogger:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   137: aload_2
    //   138: ifnull -36 -> 102
    //   141: aload_2
    //   142: invokeinterface 437 1 0
    //   147: goto -45 -> 102
    //   150: astore_2
    //   151: aconst_null
    //   152: astore_0
    //   153: aload_0
    //   154: ifnull +9 -> 163
    //   157: aload_0
    //   158: invokeinterface 437 1 0
    //   163: aload_2
    //   164: athrow
    //   165: astore_2
    //   166: goto -13 -> 153
    //   169: astore_3
    //   170: goto -63 -> 107
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	173	0	paramContext	Context
    //   0	173	1	paramInt	int
    //   6	136	2	localObject1	Object
    //   150	14	2	localObject2	Object
    //   165	1	2	localObject3	Object
    //   104	21	3	localException1	Exception
    //   169	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	47	104	java/lang/Exception
    //   0	47	150	finally
    //   53	62	165	finally
    //   64	80	165	finally
    //   109	137	165	finally
    //   53	62	169	java/lang/Exception
    //   64	80	169	java/lang/Exception
  }
  
  public static String getSubscriberId(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getSubscriberId();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "getSubscribeId error " + paramContext.getMessage());
    }
    return null;
  }
  
  public static String getSubscriberId(Context paramContext, int paramInt)
  {
    paramContext = getPhoneInfo(paramContext, paramInt, "getSubscriberId");
    if (paramContext != null) {
      return (String)paramContext;
    }
    return "";
  }
  
  public static String getWifiMac(Context paramContext)
  {
    try
    {
      paramContext = (WifiManager)paramContext.getSystemService("wifi");
      if (paramContext != null)
      {
        paramContext = paramContext.getConnectionInfo();
        if (paramContext != null)
        {
          paramContext = paramContext.getMacAddress();
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "getWifiMac error " + paramContext.getMessage());
    }
    return null;
  }
  
  public static boolean isApplicationDebug(Context paramContext)
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
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isBrandMeizu(Context paramContext)
  {
    if ((!TextUtils.isEmpty(k.a("ro.meizu.product.model"))) || ("meizu".equalsIgnoreCase(Build.BRAND)) || ("22c4185e".equalsIgnoreCase(Build.BRAND))) {}
    for (boolean bool = true;; bool = false)
    {
      if (!bool) {
        com.meizu.cloud.pushsdk.a.a.b(paramContext.getApplicationContext());
      }
      return bool;
    }
  }
  
  public static boolean isHuaWei()
  {
    String str = k.a("ro.build.version.emui");
    DebugLogger.e("MzSystemUtils", "huawei eui " + str);
    return !TextUtils.isEmpty(str);
  }
  
  public static boolean isIndiaLocal()
  {
    return "india".equals(k.a("ro.meizu.locale.region"));
  }
  
  public static boolean isInternational()
  {
    if (com.meizu.cloud.pushsdk.base.a.a().a) {
      return ((Boolean)com.meizu.cloud.pushsdk.base.a.a().b).booleanValue();
    }
    return false;
  }
  
  public static boolean isMeizu(Context paramContext)
  {
    return isBrandMeizu(paramContext);
  }
  
  public static boolean isPackageInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isRunningProcess(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      boolean bool1 = false;
      while (paramContext.hasNext())
      {
        boolean bool2 = ((ActivityManager.RunningAppProcessInfo)paramContext.next()).processName.contains(paramString);
        bool1 = bool2;
        if (bool2) {
          bool1 = bool2;
        }
      }
      DebugLogger.i("MzSystemUtils", paramString + " is running " + bool1);
      return bool1;
    }
    catch (Exception paramContext)
    {
      DebugLogger.e("MzSystemUtils", "can not get running process info so set running true");
    }
    return true;
  }
  
  public static boolean isXiaoMi()
  {
    return ("Xiaomi".equals(Build.MODEL)) || ("Xiaomi".equals(Build.MANUFACTURER));
  }
  
  public static void sendMessageFromBroadcast(Context paramContext, Intent paramIntent, String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1)) {
      paramIntent.setAction(paramString1);
    }
    if (!TextUtils.isEmpty(paramString2)) {
      paramIntent.setPackage(paramString2);
    }
    paramString1 = findReceiver(paramContext, paramString1, paramString2);
    if (!TextUtils.isEmpty(paramString1)) {
      paramIntent.setClassName(paramString2, paramString1);
    }
    paramContext.sendBroadcast(paramIntent);
  }
}
