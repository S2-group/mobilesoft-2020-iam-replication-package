package com.tencent.android.tpush.service.d;

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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.encrypt.a;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.l;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class d
{
  private static int a = -1;
  
  public static Intent a(int paramInt1, String paramString, int paramInt2)
  {
    Intent localIntent = new Intent("com.tencent.android.tpush.action.FEEDBACK");
    if ((paramString != null) && (paramString.length() != 0)) {
      localIntent.setPackage(paramString);
    }
    localIntent.putExtra("TPUSH.FEEDBACK", paramInt2);
    localIntent.putExtra("TPUSH.ERRORCODE", paramInt1);
    return localIntent;
  }
  
  public static String a()
  {
    TLog.v("XGService", "@@ getSocketName()");
    try
    {
      String str = TpnsSecurity.generateLocalSocketServieName(l.f());
      return str;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.getMessage());
    }
    return null;
  }
  
  public static String a(long paramLong)
  {
    StringBuffer localStringBuffer = new StringBuffer("");
    localStringBuffer.append(String.valueOf(0xFF & paramLong));
    localStringBuffer.append(".");
    localStringBuffer.append(String.valueOf((0xFFFF & paramLong) >>> 8));
    localStringBuffer.append(".");
    localStringBuffer.append(String.valueOf((0xFFFFFF & paramLong) >>> 16));
    localStringBuffer.append(".");
    localStringBuffer.append(String.valueOf(paramLong >>> 24));
    return localStringBuffer.toString();
  }
  
  public static String a(Context paramContext, long paramLong)
  {
    String str2 = "" + m.a(paramContext, new StringBuilder().append("tpush_msgId_").append(paramLong).toString(), "");
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (str2.trim().length() != 0) {}
    }
    else
    {
      TLog.i("XGService", "msgIds is empty, try read from System.Setting.");
      str1 = e(paramContext, "tpush_msgId_" + paramLong);
    }
    paramContext = str1;
    if (str1 != null)
    {
      paramContext = str1;
      if (str1.length() > 20480) {
        paramContext = str1.substring(0, str1.indexOf("@@", 5120));
      }
    }
    if (paramContext != null) {
      return paramContext;
    }
    return "";
  }
  
  public static List a(Context paramContext)
  {
    HashMap localHashMap;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getLocalPushAppsInfo(" + paramContext.getPackageName() + ")");
      try
      {
        localHashMap = new HashMap();
        paramContext = paramContext.getPackageManager();
        Object localObject = paramContext.queryIntentActivities(new Intent("android.intent.action"), 32);
        ((List)localObject).addAll(paramContext.queryIntentActivities(new Intent(""), 32));
        ((List)localObject).addAll(paramContext.queryBroadcastReceivers(new Intent("com.tencent.android.tpush.action.SDK"), 512));
        paramContext = ((List)localObject).iterator();
        while (paramContext.hasNext())
        {
          localObject = (ResolveInfo)paramContext.next();
          localHashMap.put(((ResolveInfo)localObject).activityInfo.applicationInfo.packageName, localObject);
          continue;
          return null;
        }
      }
      catch (Exception paramContext)
      {
        TLog.e("XGService", paramContext.getMessage());
      }
    }
    paramContext = new ArrayList(localHashMap.values());
    return paramContext;
  }
  
  public static List a(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getLocalPushServicesInfo(" + paramContext.getPackageName() + ")");
      paramString = new Intent(paramString);
      try
      {
        paramContext = paramContext.getPackageManager().queryIntentServices(paramString, 512);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        TLog.e("XGService", paramContext.getMessage());
        return null;
      }
    }
    TLog.e("XGService", "getLocalPushServicesInfo the context == null");
    return null;
  }
  
  public static boolean a(Context paramContext, String paramString, float paramFloat)
  {
    try
    {
      boolean bool = Settings.System.putFloat(paramContext.getContentResolver(), d(paramString), paramFloat);
      return bool;
    }
    catch (Exception paramContext)
    {
      TLog.e("XGService", paramContext.getMessage());
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      boolean bool = Settings.System.putInt(paramContext.getContentResolver(), d(paramString), paramInt);
      return bool;
    }
    catch (Exception paramContext)
    {
      TLog.e("XGService", paramContext.getMessage());
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString, long paramLong)
  {
    return a(paramContext, paramString, paramLong, false);
  }
  
  /* Error */
  private static boolean a(Context paramContext, String paramString, long paramLong, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 8
    //   3: iconst_1
    //   4: istore 9
    //   6: aload_0
    //   7: invokevirtual 150	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: astore 10
    //   12: aload 10
    //   14: aload_1
    //   15: iconst_0
    //   16: invokevirtual 252	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: pop
    //   20: iconst_1
    //   21: istore 7
    //   23: ldc 43
    //   25: new 96	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 98	java/lang/StringBuilder:<init>	()V
    //   32: ldc -2
    //   34: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_0
    //   38: invokevirtual 141	android/content/Context:getPackageName	()Ljava/lang/String;
    //   41: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: ldc_w 256
    //   47: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload_1
    //   51: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: ldc_w 258
    //   57: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: iload 7
    //   62: invokevirtual 261	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   65: ldc -113
    //   67: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: invokestatic 51	com/tencent/android/tpush/logging/TLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   76: iload 7
    //   78: ireturn
    //   79: astore 11
    //   81: iload 9
    //   83: istore 7
    //   85: aload_0
    //   86: aload_1
    //   87: invokestatic 265	com/tencent/android/tpush/service/d/d:b	(Landroid/content/Context;Ljava/lang/String;)Z
    //   90: ifne -14 -> 76
    //   93: iload 9
    //   95: istore 7
    //   97: aload_0
    //   98: aload_1
    //   99: invokestatic 268	com/tencent/android/tpush/service/d/d:c	(Landroid/content/Context;Ljava/lang/String;)Z
    //   102: ifne -26 -> 76
    //   105: iload 8
    //   107: istore 7
    //   109: iload 4
    //   111: ifeq -88 -> 23
    //   114: aload_0
    //   115: invokestatic 273	com/tencent/android/tpush/service/cache/CacheManager:getRegisterInfo	(Landroid/content/Context;)Ljava/util/List;
    //   118: astore 11
    //   120: aload 11
    //   122: ifnull +94 -> 216
    //   125: aload 11
    //   127: invokeinterface 173 1 0
    //   132: astore 11
    //   134: aload 11
    //   136: invokeinterface 179 1 0
    //   141: ifeq +75 -> 216
    //   144: aload 11
    //   146: invokeinterface 183 1 0
    //   151: checkcast 275	com/tencent/android/tpush/data/b
    //   154: astore 12
    //   156: aload 12
    //   158: getfield 278	com/tencent/android/tpush/data/b:a	J
    //   161: lstore 5
    //   163: lload 5
    //   165: lload_2
    //   166: lcmp
    //   167: ifne -33 -> 134
    //   170: aload 10
    //   172: aload 12
    //   174: getfield 280	com/tencent/android/tpush/data/b:d	Ljava/lang/String;
    //   177: iconst_0
    //   178: invokevirtual 252	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   181: pop
    //   182: iconst_1
    //   183: istore 4
    //   185: iload 4
    //   187: istore 7
    //   189: goto -166 -> 23
    //   192: astore 10
    //   194: ldc 43
    //   196: aload 10
    //   198: invokevirtual 66	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   201: invokestatic 283	com/tencent/android/tpush/logging/TLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   204: iload 8
    //   206: istore 7
    //   208: goto -185 -> 23
    //   211: astore 12
    //   213: goto -79 -> 134
    //   216: iconst_0
    //   217: istore 4
    //   219: goto -34 -> 185
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	222	0	paramContext	Context
    //   0	222	1	paramString	String
    //   0	222	2	paramLong	long
    //   0	222	4	paramBoolean	boolean
    //   161	3	5	l	long
    //   21	186	7	bool1	boolean
    //   1	204	8	bool2	boolean
    //   4	90	9	bool3	boolean
    //   10	161	10	localPackageManager	PackageManager
    //   192	5	10	localException1	Exception
    //   79	1	11	localException2	Exception
    //   118	27	11	localObject	Object
    //   154	19	12	localB	com.tencent.android.tpush.data.b
    //   211	1	12	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   12	20	79	java/lang/Exception
    //   114	120	192	java/lang/Exception
    //   125	134	192	java/lang/Exception
    //   134	163	192	java/lang/Exception
    //   170	182	211	java/lang/Exception
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      boolean bool = Settings.System.putString(paramContext.getContentResolver(), d(paramString1), paramString2);
      return bool;
    }
    catch (Exception paramContext)
    {
      TLog.e("XGService", paramContext.getMessage());
    }
    return false;
  }
  
  public static boolean a(Intent paramIntent)
  {
    boolean bool = true;
    TLog.tf("XGService", "@@ checkAcceptTime(" + paramIntent + ")");
    Object localObject1;
    int j;
    for (;;)
    {
      int i;
      try
      {
        localObject1 = new JSONObject(Rijndael.decrypt(paramIntent.getStringExtra("content")));
        if (((JSONObject)localObject1).isNull("accept_time")) {
          return true;
        }
        localObject1 = ((JSONObject)localObject1).getString("accept_time");
        TLog.i("XGService", ">> accept time:" + (String)localObject1);
        JSONArray localJSONArray = new JSONArray((String)localObject1);
        if (localJSONArray.length() == 0) {
          break label452;
        }
        Object localObject2 = Calendar.getInstance();
        long l1 = paramIntent.getLongExtra("server_time", 0L);
        long l2 = paramIntent.getLongExtra("time_gap", 0L);
        if ((l1 != 0L) && (l2 != 0L) && (l1 == 0L)) {
          ((Calendar)localObject2).setTimeInMillis(System.currentTimeMillis() - l2);
        }
        j = ((Calendar)localObject2).get(11) * 60 + ((Calendar)localObject2).get(12);
        i = 0;
        if (i >= localJSONArray.length()) {
          break;
        }
        paramIntent = new JSONObject(localJSONArray.getString(i));
        localObject2 = new JSONObject(paramIntent.getString("start"));
        int k = Integer.valueOf(((JSONObject)localObject2).getString("hour")).intValue();
        k = Integer.valueOf(((JSONObject)localObject2).getString("min")).intValue() + k * 60;
        paramIntent = new JSONObject(paramIntent.getString("end"));
        int m = Integer.valueOf(paramIntent.getString("hour")).intValue() * 60 + Integer.valueOf(paramIntent.getString("min")).intValue();
        TLog.tf("XGService", ">> check accept time, current:" + j + ", startTime:" + k + ", endTime:" + m);
        if ((k <= j) && (j <= m))
        {
          TLog.i("XGService", ">> time accepted!");
          return true;
        }
      }
      catch (Throwable paramIntent)
      {
        TLog.e("XGService", paramIntent.getMessage());
        return true;
      }
      i += 1;
    }
    TLog.e("XGService", ">> time not accepted!");
    TLog.tf("XGService", ">>  time not accepted!acceptTime=" + (String)localObject1 + ",curTime=" + j);
    bool = false;
    label452:
    return bool;
  }
  
  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0) || (paramString.trim().length() == 0);
  }
  
  public static float b(Context paramContext, String paramString, float paramFloat)
  {
    try
    {
      paramFloat = Settings.System.getFloat(paramContext.getContentResolver(), d(paramString), paramFloat);
      return paramFloat;
    }
    catch (Exception paramContext)
    {
      TLog.e("XGService", paramContext.getMessage());
    }
    return 0.0F;
  }
  
  public static int b(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      paramInt = Settings.System.getInt(paramContext.getContentResolver(), d(paramString), paramInt);
      return paramInt;
    }
    catch (Exception paramContext)
    {
      TLog.e("XGService", paramContext.getMessage());
    }
    return 0;
  }
  
  public static long b(String paramString)
  {
    if ((paramString == null) || (paramString.equals("0"))) {
      return 0L;
    }
    paramString = paramString.trim();
    long[] arrayOfLong = new long[4];
    int i = paramString.indexOf(".");
    int j = paramString.indexOf(".", i + 1);
    int k = paramString.indexOf(".", j + 1);
    try
    {
      arrayOfLong[3] = Long.parseLong(paramString.substring(0, i));
      arrayOfLong[2] = Long.parseLong(paramString.substring(i + 1, j));
      arrayOfLong[1] = Long.parseLong(paramString.substring(j + 1, k));
      arrayOfLong[0] = Long.parseLong(paramString.substring(k + 1));
      return (arrayOfLong[0] << 24) + (arrayOfLong[1] << 16) + (arrayOfLong[2] << 8) + arrayOfLong[3];
    }
    catch (Exception localException)
    {
      for (;;)
      {
        arrayOfLong[4] = 0L;
        arrayOfLong[3] = 0L;
        arrayOfLong[2] = 0L;
        arrayOfLong[0] = 0L;
        TLog.e("TPush", "service Util@@parseIpAddress(" + paramString + ")", localException);
      }
    }
  }
  
  public static boolean b()
  {
    try
    {
      boolean bool = "mounted".equals(Environment.getExternalStorageState());
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ setServicePackageName(" + paramContext.getPackageName() + ")");
      return a(paramContext, "tpush.running.service.name", Rijndael.encrypt(paramContext.getPackageName()));
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    if (p.a(paramString)) {
      return false;
    }
    if (paramContext != null) {
      try
      {
        paramContext = a(paramContext);
        if (paramContext != null)
        {
          paramContext = paramContext.iterator();
          while (paramContext.hasNext())
          {
            boolean bool = paramString.equals(((ResolveInfo)paramContext.next()).activityInfo.packageName);
            if (bool) {
              return true;
            }
          }
        }
      }
      catch (Exception paramContext) {}
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString, long paramLong)
  {
    try
    {
      boolean bool = Settings.System.putLong(paramContext.getContentResolver(), d(paramString), paramLong);
      return bool;
    }
    catch (Exception paramContext)
    {
      TLog.e("XGService", paramContext.getMessage());
    }
    return false;
  }
  
  public static int c()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static long c(Context paramContext, String paramString, long paramLong)
  {
    try
    {
      paramLong = Settings.System.getLong(paramContext.getContentResolver(), d(paramString), paramLong);
      return paramLong;
    }
    catch (Exception paramContext)
    {
      TLog.e("XGService", paramContext.getMessage());
    }
    return 0L;
  }
  
  public static String c(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
        return paramContext;
      }
      catch (Exception paramContext)
      {
        TLog.w("Util", ">>get imei err", paramContext);
      }
    }
    return "";
  }
  
  public static String c(String paramString)
  {
    if (l.f() != null) {
      try
      {
        paramString = TpnsSecurity.getEncryptAPKSignature(l.f().createPackageContext(paramString, 0));
        return paramString;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        TLog.e("TPush", "+++ getAppCert exception.", paramString);
      }
    }
    return "";
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    if (p.a(paramString)) {}
    for (;;)
    {
      return false;
      if (paramContext != null) {
        try
        {
          paramContext = a(paramContext, paramString + ".PUSH_ACTION");
          if (paramContext != null)
          {
            int i = paramContext.size();
            if (i <= 0) {}
          }
          else
          {
            return true;
          }
        }
        catch (Exception paramContext) {}
      }
    }
    return false;
  }
  
  public static String d()
  {
    return Build.MODEL;
  }
  
  private static String d(String paramString)
  {
    return a.a(paramString);
  }
  
  public static boolean d(Context paramContext)
  {
    paramContext = CacheManager.getRegisterInfos(paramContext);
    return (paramContext != null) && (paramContext.size() > 0);
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ isTPushApp(" + paramContext.getPackageName() + "," + paramString + ")");
      Object localObject = CacheManager.getRegisterInfos(paramContext);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          if ((((String)((Iterator)localObject).next()).equals(paramString)) && (!paramContext.getPackageName().equals(paramString)))
          {
            TLog.i("XGService", ">> Is tpush app [ true ] @" + paramString + "," + paramContext.getPackageName());
            return true;
          }
        }
      }
      TLog.i("XGService", ">> Is tpush app [ false ]@" + paramString + "," + paramContext.getPackageName());
    }
    return false;
  }
  
  public static boolean d(Context paramContext, String paramString, long paramLong)
  {
    return a(paramContext, paramString, paramLong, false);
  }
  
  public static byte e(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        TLog.v("XGService", "@@ getNetworkType(" + paramContext.getPackageName() + ")");
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null) {
          return 0;
        }
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext == null) {
          return -1;
        }
        if ((!paramContext.isAvailable()) || (!paramContext.isConnected())) {
          break label202;
        }
        if (paramContext.getType() == 1) {
          return 1;
        }
        if (paramContext.getType() == 0)
        {
          int i = paramContext.getSubtype();
          switch (i)
          {
          case 12: 
          case 14: 
          default: 
            return 0;
          case 1: 
          case 2: 
          case 4: 
          case 7: 
          case 11: 
            return 2;
          case 3: 
          case 5: 
          case 6: 
          case 8: 
          case 9: 
          case 10: 
          case 15: 
            return 3;
          }
          return 4;
        }
        return 0;
      }
      catch (Exception paramContext)
      {
        TLog.e("XGService", paramContext.getMessage());
      }
    }
    return -1;
    label202:
    return -1;
  }
  
  public static String e()
  {
    TLog.v("XGService", "@@ getLocalIpAddress()");
    try
    {
      if (NetworkInterface.getNetworkInterfaces() == null) {
        return "0";
      }
      Object localObject;
      boolean bool;
      do
      {
        do
        {
          Iterator localIterator1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
          Iterator localIterator2;
          while (!localIterator2.hasNext())
          {
            if (!localIterator1.hasNext()) {
              break;
            }
            localIterator2 = Collections.list(((NetworkInterface)localIterator1.next()).getInetAddresses()).iterator();
          }
          localObject = (InetAddress)localIterator2.next();
        } while (((InetAddress)localObject).isLoopbackAddress());
        localObject = ((InetAddress)localObject).getHostAddress();
        bool = InetAddressUtils.isIPv4Address((String)localObject);
      } while (!bool);
      return localObject;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.getMessage());
    }
    return "0";
  }
  
  public static String e(Context paramContext, String paramString)
  {
    try
    {
      paramContext = Settings.System.getString(paramContext.getContentResolver(), d(paramString));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      TLog.e("XGService", paramContext.getMessage());
    }
    return "";
  }
  
  private static boolean e(String paramString)
  {
    if (a(paramString)) {}
    do
    {
      return false;
      paramString = paramString.toLowerCase();
      if (paramString.contains(".lbe.")) {
        return true;
      }
      if (paramString.contains(".qihoo360.")) {
        return true;
      }
      if (paramString.contains("jinshan.")) {
        return true;
      }
      if (paramString.contains(".qqpimsecure")) {
        return true;
      }
      if (paramString.contains(".phonoalbumshoushou")) {
        return true;
      }
      if (paramString.contains(".netqin.")) {
        return true;
      }
      if (paramString.contains(".kms.")) {
        return true;
      }
      if (paramString.contains(".avg.")) {
        return true;
      }
      if (paramString.contains(".am321.")) {
        return true;
      }
      if (paramString.contains("safe")) {
        return true;
      }
      if (paramString.contains("security")) {
        return true;
      }
    } while (!paramString.contains("clean"));
    return true;
  }
  
  public static byte f(Context paramContext)
  {
    byte b = 0;
    if (paramContext != null)
    {
      try
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimOperator();
        if (paramContext != null)
        {
          if ((paramContext.equals("46000")) || (paramContext.equals("46002")) || (paramContext.equals("46007")) || (paramContext.equals("46020"))) {
            break label134;
          }
          if ((paramContext.equals("46001")) || (paramContext.equals("46006"))) {
            break label138;
          }
          if (!paramContext.equals("46003"))
          {
            boolean bool = paramContext.equals("46005");
            if (!bool) {}
          }
          else
          {
            b = 1;
          }
        }
      }
      catch (Exception paramContext)
      {
        TLog.e("XGService", paramContext.getMessage());
        return 0;
      }
      b = 0;
    }
    label134:
    label138:
    for (b = 3;; b = 2) {
      return b;
    }
  }
  
  public static String g(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getKey(" + paramContext.getPackageName() + ")");
      try
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 1)) {
          return h(paramContext);
        }
        paramContext = "" + f(paramContext) + e(paramContext);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        TLog.e("XGService", paramContext.getMessage());
      }
    }
    return "";
  }
  
  public static String h(Context paramContext)
  {
    String str = i(paramContext);
    if (str != null)
    {
      paramContext = str;
      if (!str.equals("0")) {}
    }
    else
    {
      paramContext = e();
    }
    return paramContext;
  }
  
  public static String i(Context paramContext)
  {
    TLog.v("XGService", "@@ getRouteMac(" + paramContext.getPackageName() + ")");
    try
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
      if (paramContext == null) {
        return "0";
      }
      paramContext = paramContext.getBSSID();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      TLog.e("XGService", paramContext.getMessage());
    }
    return "0";
  }
  
  public static String j(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return null;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.HOME");
      paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    } while ((paramContext.activityInfo == null) || (paramContext.activityInfo.packageName.equals("android")));
    return paramContext.activityInfo.packageName;
  }
  
  public static int k(Context paramContext)
  {
    if (a != -1) {
      return a;
    }
    try
    {
      if (e.a()) {
        a = 1;
      }
      a = 0;
      return a;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
  }
  
  public static JSONArray l(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    PackageManager localPackageManager;
    Map localMap;
    Object localObject1;
    Object localObject2;
    try
    {
      localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
      {
        localMap = n(paramContext);
        localObject1 = a(paramContext);
        paramContext = new HashMap();
        if ((localObject1 != null) && (((List)localObject1).size() > 0))
        {
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ResolveInfo)((Iterator)localObject1).next();
            if (((ResolveInfo)localObject2).activityInfo != null) {
              paramContext.put(((ResolveInfo)localObject2).activityInfo.packageName, Integer.valueOf(1));
            }
          }
        }
      }
      else
      {
        return localJSONArray;
      }
    }
    catch (Throwable paramContext)
    {
      TLog.w("TPush", "failed to get app.", paramContext);
    }
    for (;;)
    {
      localObject1 = localPackageManager.getInstalledPackages(0).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PackageInfo)((Iterator)localObject1).next();
        JSONObject localJSONObject = new JSONObject();
        ApplicationInfo localApplicationInfo = ((PackageInfo)localObject2).applicationInfo;
        if ((localMap.containsKey(localApplicationInfo.packageName)) || (paramContext.containsKey(localApplicationInfo.packageName))) {
          if ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) != 0)
          {
            if (e(localApplicationInfo.packageName)) {
              localJSONObject.put("s", "1");
            }
          }
          else
          {
            String str = localPackageManager.getApplicationLabel(((PackageInfo)localObject2).applicationInfo).toString();
            if (str != null) {
              localJSONObject.put("n", str);
            }
            if (localApplicationInfo.packageName != null) {
              localJSONObject.put("p", localApplicationInfo.packageName);
            }
            if (((PackageInfo)localObject2).versionName != null) {
              localJSONObject.put("v", ((PackageInfo)localObject2).versionName);
            }
            if (localMap.containsKey(localApplicationInfo.packageName)) {
              localJSONObject.put("r", "1");
            }
            if (paramContext.containsKey(localApplicationInfo.packageName)) {
              localJSONObject.put("xg", "1");
            }
            localJSONArray.put(localJSONObject);
          }
        }
      }
    }
  }
  
  public static String m(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.get("InstallChannel");
        if (paramContext != null) {
          return paramContext.toString();
        }
        TLog.i("XGService", "Could not read InstallChannel meta-data from AndroidManifest.xml");
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        TLog.w("XGService", "Could not read InstallChannel meta-data from AndroidManifest.xml");
      }
    }
    return null;
  }
  
  private static Map n(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      String[] arrayOfString = localRunningAppProcessInfo.pkgList;
      int i = 0;
      while (i < arrayOfString.length)
      {
        localHashMap.put(arrayOfString[i], localRunningAppProcessInfo);
        i += 1;
      }
    }
    return localHashMap;
  }
}
