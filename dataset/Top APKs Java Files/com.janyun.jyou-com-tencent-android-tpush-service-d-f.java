package com.tencent.android.tpush.service.d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
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
import android.os.Environment;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.XGPushService;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
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

public class f
{
  private static int a = -1;
  private static String b = null;
  
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
    try
    {
      String str = TpnsSecurity.generateLocalSocketServieName(com.tencent.android.tpush.service.m.e());
      return str;
    }
    catch (Exception localException)
    {
      com.tencent.android.tpush.a.a.c("XGService", "getSocketName", localException);
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
    String str2 = "" + com.tencent.android.tpush.common.m.a(paramContext, new StringBuilder().append("tpush_msgId_").append(paramLong).toString(), "");
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (str2.trim().length() != 0) {}
    }
    else
    {
      str1 = a(paramContext, "tpush_msgId_" + paramLong, true);
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
  
  public static String a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    String str1;
    try
    {
      String str2 = (String)com.tencent.android.tpush.service.cache.a.a(paramString);
      str1 = str2;
      if (str2 != null) {
        return str1;
      }
      paramContext = com.tencent.android.tpush.service.channel.c.f.a(paramContext).a(d(paramString));
      com.tencent.android.tpush.service.cache.a.a(paramString, paramContext);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      com.tencent.android.tpush.a.a.c("XGService", "getString", paramContext);
      str1 = "";
    }
    paramContext = com.tencent.android.tpush.service.channel.c.f.a(paramContext).a(d(paramString));
    return paramContext;
    return str1;
  }
  
  public static String a(String paramString, int paramInt)
  {
    int j = paramString.length();
    String str;
    if (j >= paramInt)
    {
      str = paramString;
      return str;
    }
    int i = 0;
    for (;;)
    {
      str = paramString;
      if (i >= paramInt - j) {
        break;
      }
      paramString = paramString + " ";
      i += 1;
    }
  }
  
  public static List a(Context paramContext)
  {
    HashMap localHashMap;
    if (paramContext != null) {
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
        com.tencent.android.tpush.a.a.c("XGService", "getLocalPushAppsInfo", paramContext);
      }
    }
    paramContext = new ArrayList(localHashMap.values());
    return paramContext;
  }
  
  public static List a(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      paramString = new Intent(paramString);
      try
      {
        paramContext = paramContext.getPackageManager().queryIntentServices(paramString, 512);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "getLocalPushServicesInfo", paramContext);
        return null;
      }
    }
    com.tencent.android.tpush.a.a.h("XGService", "getLocalPushServicesInfo the context == null");
    return null;
  }
  
  public static boolean a(Context paramContext, String paramString, float paramFloat)
  {
    try
    {
      boolean bool = com.tencent.android.tpush.service.channel.c.f.a(paramContext).a(d(paramString), paramFloat);
      return bool;
    }
    catch (Exception paramContext)
    {
      com.tencent.android.tpush.a.a.c("XGService", "putFloat", paramContext);
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      boolean bool = com.tencent.android.tpush.service.channel.c.f.a(paramContext).a(d(paramString), paramInt);
      return bool;
    }
    catch (Exception paramContext)
    {
      com.tencent.android.tpush.a.a.c("XGService", "putInt", paramContext);
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
    //   7: invokevirtual 160	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: astore 11
    //   12: aload 11
    //   14: aload_1
    //   15: iconst_0
    //   16: invokevirtual 259	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: pop
    //   20: iconst_1
    //   21: istore 7
    //   23: iload 7
    //   25: ireturn
    //   26: astore 10
    //   28: iload 9
    //   30: istore 7
    //   32: aload_0
    //   33: aload_1
    //   34: invokestatic 262	com/tencent/android/tpush/service/d/f:b	(Landroid/content/Context;Ljava/lang/String;)Z
    //   37: ifne -14 -> 23
    //   40: iload 9
    //   42: istore 7
    //   44: aload_0
    //   45: aload_1
    //   46: invokestatic 264	com/tencent/android/tpush/service/d/f:c	(Landroid/content/Context;Ljava/lang/String;)Z
    //   49: ifne -26 -> 23
    //   52: iload 8
    //   54: istore 7
    //   56: iload 4
    //   58: ifeq -35 -> 23
    //   61: aload_0
    //   62: invokestatic 269	com/tencent/android/tpush/service/cache/CacheManager:getRegisterInfo	(Landroid/content/Context;)Ljava/util/List;
    //   65: astore_0
    //   66: aload_0
    //   67: ifnull +85 -> 152
    //   70: aload_0
    //   71: invokeinterface 183 1 0
    //   76: astore_0
    //   77: aload_0
    //   78: invokeinterface 189 1 0
    //   83: ifeq +69 -> 152
    //   86: aload_0
    //   87: invokeinterface 193 1 0
    //   92: checkcast 271	com/tencent/android/tpush/data/a
    //   95: astore_1
    //   96: aload_1
    //   97: getfield 274	com/tencent/android/tpush/data/a:a	J
    //   100: lstore 5
    //   102: lload 5
    //   104: lload_2
    //   105: lcmp
    //   106: ifne -29 -> 77
    //   109: aload 11
    //   111: aload_1
    //   112: getfield 276	com/tencent/android/tpush/data/a:d	Ljava/lang/String;
    //   115: iconst_0
    //   116: invokevirtual 259	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   119: pop
    //   120: iconst_1
    //   121: istore 4
    //   123: iload 4
    //   125: istore 7
    //   127: goto -104 -> 23
    //   130: astore_0
    //   131: ldc 59
    //   133: ldc_w 278
    //   136: aload 10
    //   138: invokestatic 67	com/tencent/android/tpush/a/a:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   141: iload 8
    //   143: istore 7
    //   145: goto -122 -> 23
    //   148: astore_1
    //   149: goto -72 -> 77
    //   152: iconst_0
    //   153: istore 4
    //   155: goto -32 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	158	0	paramContext	Context
    //   0	158	1	paramString	String
    //   0	158	2	paramLong	long
    //   0	158	4	paramBoolean	boolean
    //   100	3	5	l	long
    //   21	123	7	bool1	boolean
    //   1	141	8	bool2	boolean
    //   4	37	9	bool3	boolean
    //   26	111	10	localException	Exception
    //   10	100	11	localPackageManager	PackageManager
    // Exception table:
    //   from	to	target	type
    //   12	20	26	java/lang/Exception
    //   61	66	130	java/lang/Exception
    //   70	77	130	java/lang/Exception
    //   77	102	130	java/lang/Exception
    //   109	120	148	java/lang/Exception
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      String str = (String)com.tencent.android.tpush.service.cache.a.a(paramString1);
      if ((str != null) && (paramString2 != null) && (str.equals(paramString2))) {
        return true;
      }
      com.tencent.android.tpush.service.cache.a.a(paramString1, paramString2);
      paramBoolean = com.tencent.android.tpush.service.channel.c.f.a(paramContext).a(d(paramString1), paramString2);
      return paramBoolean;
    }
    catch (Exception paramContext)
    {
      com.tencent.android.tpush.a.a.c("XGService", "putString", paramContext);
    }
    return false;
  }
  
  public static boolean a(Intent paramIntent)
  {
    for (;;)
    {
      int i;
      try
      {
        Object localObject1 = new JSONObject(Rijndael.decrypt(paramIntent.getStringExtra("content")));
        if (((JSONObject)localObject1).isNull("accept_time")) {
          return true;
        }
        localObject1 = ((JSONObject)localObject1).getString("accept_time");
        JSONArray localJSONArray = new JSONArray((String)localObject1);
        if (localJSONArray.length() != 0)
        {
          Object localObject2 = Calendar.getInstance();
          long l1 = paramIntent.getLongExtra("server_time", 0L);
          long l2 = paramIntent.getLongExtra("time_gap", 0L);
          if ((l1 != 0L) && (l2 != 0L) && (l1 == 0L)) {
            ((Calendar)localObject2).setTimeInMillis(System.currentTimeMillis() - l2);
          }
          int j = ((Calendar)localObject2).get(11) * 60 + ((Calendar)localObject2).get(12);
          i = 0;
          if (i < localJSONArray.length())
          {
            paramIntent = new JSONObject(localJSONArray.getString(i));
            localObject2 = new JSONObject(paramIntent.getString("start"));
            int k = Integer.valueOf(((JSONObject)localObject2).getString("hour")).intValue();
            int m = Integer.valueOf(((JSONObject)localObject2).getString("min")).intValue();
            paramIntent = new JSONObject(paramIntent.getString("end"));
            int n = Integer.valueOf(paramIntent.getString("hour")).intValue();
            int i1 = Integer.valueOf(paramIntent.getString("min")).intValue();
            if (m + k * 60 > j) {
              break label331;
            }
            if (j > n * 60 + i1) {
              break label331;
            }
          }
          else
          {
            com.tencent.android.tpush.a.a.h("Utils", " discurd the msg due to time not accepted! acceptTime = " + (String)localObject1 + " , curTime= " + j);
            return false;
          }
        }
      }
      catch (Throwable paramIntent)
      {
        com.tencent.android.tpush.a.a.c("XGService", "checkAcceptTime", paramIntent);
      }
      return true;
      label331:
      i += 1;
    }
  }
  
  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0) || (paramString.trim().length() == 0);
  }
  
  public static float b(Context paramContext, String paramString, float paramFloat)
  {
    try
    {
      paramFloat = com.tencent.android.tpush.service.channel.c.f.a(paramContext).b(d(paramString), paramFloat);
      return paramFloat;
    }
    catch (Exception paramContext)
    {
      com.tencent.android.tpush.a.a.c("XGService", "getFloat", paramContext);
    }
    return 0.0F;
  }
  
  public static int b(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      paramInt = com.tencent.android.tpush.service.channel.c.f.a(paramContext).b(d(paramString), paramInt);
      return paramInt;
    }
    catch (Exception paramContext)
    {
      com.tencent.android.tpush.a.a.c("XGService", "getInt", paramContext);
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
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        arrayOfLong[4] = 0L;
        arrayOfLong[3] = 0L;
        arrayOfLong[2] = 0L;
        arrayOfLong[0] = 0L;
        com.tencent.android.tpush.a.a.c("TPush", "service Util@@parseIpAddress(" + paramString + ")", localThrowable);
      }
    }
  }
  
  public static void b(Context paramContext)
  {
    try
    {
      Object localObject1 = a(paramContext);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          String str = ((ResolveInfo)((Iterator)localObject1).next()).activityInfo.applicationInfo.packageName;
          if ((!a(str)) && (!paramContext.getPackageName().equals(str)))
          {
            Object localObject2 = a(com.tencent.android.tpush.service.m.e(), str + ".PUSH_ACTION");
            if ((localObject2 != null) || (((List)localObject2).size() > 0))
            {
              localObject2 = new Intent();
              ((Intent)localObject2).setAction(str + ".PUSH_ACTION");
              ((Intent)localObject2).setPackage(str);
              paramContext.startService((Intent)localObject2);
            }
          }
        }
      }
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static boolean b()
  {
    try
    {
      boolean bool = "mounted".equals(Environment.getExternalStorageState());
      return bool;
    }
    catch (Exception localException)
    {
      com.tencent.android.tpush.a.a.c("XGService", "isSDCardMounted", localException);
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    if (p.b(paramString)) {
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
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "isLocalApp", paramContext);
      }
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString, long paramLong)
  {
    try
    {
      com.tencent.android.tpush.service.channel.c.f.a(paramContext).a(d(paramString), paramLong);
      return false;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        com.tencent.android.tpush.a.a.c("XGService", "putLong", paramContext);
      }
    }
  }
  
  public static int c()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static long c(Context paramContext, String paramString, long paramLong)
  {
    try
    {
      paramLong = com.tencent.android.tpush.service.channel.c.f.a(paramContext).b(d(paramString), paramLong);
      return paramLong;
    }
    catch (Exception paramContext)
    {
      com.tencent.android.tpush.a.a.c("XGService", "getLong", paramContext);
    }
    return 0L;
  }
  
  public static String c(String paramString)
  {
    if (com.tencent.android.tpush.service.m.e() != null) {
      try
      {
        paramString = TpnsSecurity.getEncryptAPKSignature(com.tencent.android.tpush.service.m.e().createPackageContext(paramString, 0));
        return paramString;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        com.tencent.android.tpush.a.a.c("TPush", "+++ getAppCert exception.", paramString);
      }
    }
    return "";
  }
  
  public static boolean c(Context paramContext)
  {
    boolean bool = false;
    if (paramContext != null) {
      bool = a(paramContext, "tpush.running.service.name", Rijndael.encrypt(paramContext.getPackageName()), false);
    }
    return bool;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    if (p.b(paramString)) {}
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
        catch (Exception paramContext)
        {
          com.tencent.android.tpush.a.a.c("XGService", "isPkgHasRemoteService", paramContext);
        }
      }
    }
    return false;
  }
  
  public static String d()
  {
    return Build.MODEL;
  }
  
  public static String d(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
        return paramContext;
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("Util", ">>get imei err", paramContext);
      }
    }
    return "";
  }
  
  private static String d(String paramString)
  {
    return com.tencent.android.tpush.encrypt.a.a(paramString);
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      Object localObject = CacheManager.getRegisterInfos(paramContext);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          if ((((String)((Iterator)localObject).next()).equals(paramString)) && (!paramContext.getPackageName().equals(paramString))) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public static boolean d(Context paramContext, String paramString, long paramLong)
  {
    return a(paramContext, paramString, paramLong, false);
  }
  
  public static String e()
  {
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
      com.tencent.android.tpush.a.a.c("XGService", "getLocalIpAddress", localException);
    }
    return "0";
  }
  
  public static boolean e(Context paramContext)
  {
    paramContext = CacheManager.getRegisterInfos(paramContext);
    return (paramContext != null) && (paramContext.size() > 0);
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
    if (paramContext != null) {
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null) {
          return 0;
        }
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext == null) {
          return -1;
        }
        if ((!paramContext.isAvailable()) || (!paramContext.isConnected())) {
          break label170;
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
        com.tencent.android.tpush.a.a.c("XGService", "getNetworkType", paramContext);
      }
    }
    return -1;
    label170:
    return -1;
  }
  
  public static boolean f()
  {
    try
    {
      boolean bool = Environment.getExternalStorageState().equals("mounted");
      if (!bool) {
        com.tencent.android.tpush.a.a.c("XGService", "SDCard is not mounted");
      }
      return bool;
    }
    catch (Exception localException)
    {
      com.tencent.android.tpush.a.a.c("XGService", "SDCard is not mounted", localException);
    }
    return false;
  }
  
  public static byte g(Context paramContext)
  {
    byte b1 = 0;
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
            b1 = 1;
          }
        }
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "getIsp", paramContext);
        return 0;
      }
      b1 = 0;
    }
    label134:
    label138:
    for (b1 = 3;; b1 = 2) {
      return b1;
    }
  }
  
  public static String h(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 1)) {
          return i(paramContext);
        }
        paramContext = "" + g(paramContext) + f(paramContext);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "getKey", paramContext);
      }
    }
    return "";
  }
  
  public static String i(Context paramContext)
  {
    String str = j(paramContext);
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
  
  public static String j(Context paramContext)
  {
    try
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
      if (paramContext == null) {
        return "0";
      }
      paramContext = paramContext.getBSSID();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      com.tencent.android.tpush.a.a.c("XGService", "getRouteMac", paramContext);
    }
    return "0";
  }
  
  public static String k(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return null;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.HOME");
      paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    } while ((paramContext == null) || (paramContext.activityInfo == null) || (paramContext.activityInfo.packageName.equals("android")));
    return paramContext.activityInfo.packageName;
  }
  
  public static int l(Context paramContext)
  {
    if (a != -1) {
      return a;
    }
    try
    {
      if (g.a()) {
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
  
  public static JSONArray m(Context paramContext)
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
        localMap = r(paramContext);
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
      com.tencent.android.tpush.a.a.c("TPush", "failed to get app.", paramContext);
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
  
  public static boolean n(Context paramContext)
  {
    try
    {
      paramContext = o(paramContext);
      if (paramContext == null)
      {
        com.tencent.android.tpush.a.a.i("TPush", "Failed to init due to null ApplicationInfo.");
        return false;
      }
      if (paramContext.icon == 0)
      {
        com.tencent.android.tpush.a.a.i("TPush", "Failed to get Application icon in AndroidManifest.xml, You App maybe can not show notification, Please add Application icon in AndroidManifest.xml");
        return false;
      }
    }
    catch (Throwable paramContext)
    {
      return false;
    }
    return true;
  }
  
  public static ApplicationInfo o(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      com.tencent.android.tpush.a.a.d("TPush", "Failed to get Application info", paramContext);
    }
    return null;
  }
  
  public static String p(Context paramContext)
  {
    if (TextUtils.isEmpty(b))
    {
      int i = Process.myPid();
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (i == localRunningAppProcessInfo.pid) {
          b = localRunningAppProcessInfo.processName;
        }
      }
    }
    return b;
  }
  
  public static void q(Context paramContext)
  {
    try
    {
      if (p(paramContext).endsWith(":xg_service_v2"))
      {
        if (!"huawei".equalsIgnoreCase(Build.MANUFACTURER))
        {
          Process.killProcess(Process.myPid());
          return;
        }
        XGPushService.a().stopSelf();
        return;
      }
    }
    catch (Throwable paramContext) {}
  }
  
  private static Map r(Context paramContext)
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
