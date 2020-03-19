package com.tencent.android.tpush.service.e;

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
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.android.tpush.common.t;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.XGPushServiceV3;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.n;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class h
{
  static List a = new ArrayList();
  private static int b = -1;
  private static String c = null;
  
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
      String str = TpnsSecurity.generateLocalSocketServieName(n.f());
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
  
  public static String a(Context paramContext)
  {
    if (paramContext != null) {
      return paramContext.getPackageName();
    }
    return "";
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
    //   7: invokevirtual 118	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: astore 11
    //   12: aload 11
    //   14: aload_1
    //   15: iconst_0
    //   16: invokevirtual 140	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
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
    //   34: invokestatic 143	com/tencent/android/tpush/service/e/h:b	(Landroid/content/Context;Ljava/lang/String;)Z
    //   37: ifne -14 -> 23
    //   40: iload 9
    //   42: istore 7
    //   44: aload_0
    //   45: aload_1
    //   46: invokestatic 145	com/tencent/android/tpush/service/e/h:c	(Landroid/content/Context;Ljava/lang/String;)Z
    //   49: ifne -26 -> 23
    //   52: iload 8
    //   54: istore 7
    //   56: iload 4
    //   58: ifeq -35 -> 23
    //   61: aload_0
    //   62: invokestatic 151	com/tencent/android/tpush/service/cache/CacheManager:getRegisterInfo	(Landroid/content/Context;)Ljava/util/List;
    //   65: astore_0
    //   66: aload_0
    //   67: ifnull +84 -> 151
    //   70: aload_0
    //   71: invokeinterface 157 1 0
    //   76: astore_0
    //   77: aload_0
    //   78: invokeinterface 163 1 0
    //   83: ifeq +68 -> 151
    //   86: aload_0
    //   87: invokeinterface 167 1 0
    //   92: checkcast 169	com/tencent/android/tpush/data/RegisterEntity
    //   95: astore_1
    //   96: aload_1
    //   97: getfield 173	com/tencent/android/tpush/data/RegisterEntity:accessId	J
    //   100: lstore 5
    //   102: lload 5
    //   104: lload_2
    //   105: lcmp
    //   106: ifne -29 -> 77
    //   109: aload 11
    //   111: aload_1
    //   112: getfield 176	com/tencent/android/tpush/data/RegisterEntity:packageName	Ljava/lang/String;
    //   115: iconst_0
    //   116: invokevirtual 140	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   119: pop
    //   120: iconst_1
    //   121: istore 4
    //   123: iload 4
    //   125: istore 7
    //   127: goto -104 -> 23
    //   130: astore_0
    //   131: ldc 67
    //   133: ldc -78
    //   135: aload 10
    //   137: invokestatic 74	com/tencent/android/tpush/a/a:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: iload 8
    //   142: istore 7
    //   144: goto -121 -> 23
    //   147: astore_1
    //   148: goto -71 -> 77
    //   151: iconst_0
    //   152: istore 4
    //   154: goto -31 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	paramContext	Context
    //   0	157	1	paramString	String
    //   0	157	2	paramLong	long
    //   0	157	4	paramBoolean	boolean
    //   100	3	5	l	long
    //   21	122	7	bool1	boolean
    //   1	140	8	bool2	boolean
    //   4	37	9	bool3	boolean
    //   26	110	10	localException	Exception
    //   10	100	11	localPackageManager	PackageManager
    // Exception table:
    //   from	to	target	type
    //   12	20	26	java/lang/Exception
    //   61	66	130	java/lang/Exception
    //   70	77	130	java/lang/Exception
    //   77	102	130	java/lang/Exception
    //   109	120	147	java/lang/Exception
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
              break label320;
            }
            if (j > n * 60 + i1) {
              break label320;
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
      label320:
      i += 1;
    }
  }
  
  public static boolean a(String paramString)
  {
    return b().contains(paramString);
  }
  
  public static long b(Intent paramIntent)
  {
    int i = 0;
    for (;;)
    {
      int j;
      int m;
      try
      {
        localObject3 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        localObject2 = paramIntent.getStringExtra("date");
        localObject1 = localObject2;
        if (b((String)localObject2)) {
          localObject1 = ((SimpleDateFormat)localObject3).format(new Date());
        }
        l1 = ((SimpleDateFormat)localObject3).parse((String)localObject1).getTime();
        localObject1 = new JSONObject(Rijndael.decrypt(paramIntent.getStringExtra("content")));
        if (((JSONObject)localObject1).isNull("accept_time")) {
          return l1;
        }
        localObject1 = ((JSONObject)localObject1).getString("accept_time");
        localObject2 = new JSONArray((String)localObject1);
        if (((JSONArray)localObject2).length() != 0) {
          break label334;
        }
        return l1;
      }
      catch (Throwable paramIntent)
      {
        Object localObject3;
        Object localObject2;
        Object localObject1;
        long l1;
        long l2;
        long l3;
        long l4;
        com.tencent.android.tpush.a.a.c("XGService", "getAcceptBeginTime", paramIntent);
        return 0L;
      }
      if (j < ((JSONArray)localObject2).length())
      {
        localObject3 = new JSONObject(new JSONObject(((JSONArray)localObject2).getString(j)).getString("start"));
        m = Integer.valueOf(((JSONObject)localObject3).getString("hour")).intValue() * 60 + Integer.valueOf(((JSONObject)localObject3).getString("min")).intValue();
        if (m >= i)
        {
          k = i;
          if (i != 0) {
            break label342;
          }
        }
      }
      else
      {
        l2 = i * 60 * 1000L + l1;
        l3 = paramIntent.getLongExtra("server_time", 0L);
        l4 = paramIntent.getLongExtra("time_gap", 0L);
        l1 = l2;
        if (l3 != 0L)
        {
          l1 = l2;
          if (l4 != 0L)
          {
            l1 = l2;
            if (l3 == 0L) {
              l1 = l2 + l4;
            }
          }
        }
        com.tencent.android.tpush.a.a.d("Utils", "get acceptTime = " + (String)localObject1 + " , acceptBeginTime= " + l1);
        return l1;
        label334:
        j = 0;
        continue;
      }
      int k = m;
      label342:
      j += 1;
      i = k;
    }
  }
  
  public static List b()
  {
    if (a.isEmpty()) {
      a.add("com.jingdong.app.mall");
    }
    return a;
  }
  
  public static List b(Context paramContext)
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
  
  public static boolean b(Context paramContext, String paramString)
  {
    if (t.b(paramString)) {
      return false;
    }
    if (paramContext != null) {
      try
      {
        paramContext = b(paramContext);
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
  
  public static boolean b(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0) || (paramString.trim().length() == 0);
  }
  
  public static long c(String paramString)
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
        i = 0;
        while (i < arrayOfLong.length)
        {
          arrayOfLong[i] = 0L;
          i += 1;
        }
        com.tencent.android.tpush.a.a.c("TPush", "service Util@@parseIpAddress(" + paramString + ")", localThrowable);
      }
    }
  }
  
  public static void c(Context paramContext)
  {
    label302:
    for (;;)
    {
      try
      {
        paramContext = com.tencent.android.tpush.service.a.a.a(paramContext).E;
        if (paramContext != null)
        {
          Iterator localIterator = paramContext.entrySet().iterator();
          if (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            System.out.println("Key = " + (String)localEntry.getKey() + ", Value = " + (String)localEntry.getValue());
            try
            {
              String str = "am startservice -n " + (String)localEntry.getKey() + "/" + (String)localEntry.getValue();
              paramContext = Runtime.getRuntime().exec(str);
              int i = paramContext.waitFor();
              if (i == 0) {
                break label302;
              }
              str = "am startservice --user 0 -n " + (String)localEntry.getKey() + "/" + (String)localEntry.getValue();
              paramContext = Runtime.getRuntime().exec(str);
              i = paramContext.waitFor();
              if (i == 0) {
                continue;
              }
              com.tencent.android.tpush.a.a.h("XGService", "pullUpServerConfigPkgs error exec cmd:" + str + ",exitValud:" + paramContext.exitValue());
            }
            catch (Throwable paramContext)
            {
              com.tencent.android.tpush.a.a.h("XGService", "pullUpServerConfigPkgs error exec cmd:" + paramContext);
            }
            continue;
          }
        }
        return;
      }
      catch (Throwable paramContext) {}
    }
  }
  
  public static boolean c()
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
  
  public static boolean c(Context paramContext, String paramString)
  {
    if (t.b(paramString)) {}
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
  
  public static int d()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String d(String paramString)
  {
    if (n.f() != null) {
      try
      {
        paramString = TpnsSecurity.getEncryptAPKSignature(n.f().createPackageContext(paramString, 0));
        return paramString;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        com.tencent.android.tpush.a.a.c("TPush", "+++ getAppCert exception.", paramString);
      }
    }
    return "";
  }
  
  public static void d(Context paramContext)
  {
    try
    {
      if (a(paramContext.getPackageName()))
      {
        com.tencent.android.tpush.a.a.f("Util", paramContext.getPackageName() + " ingore.");
        return;
      }
      Object localObject = b(paramContext);
      if (localObject != null)
      {
        Iterator localIterator = ((List)localObject).iterator();
        while (localIterator.hasNext())
        {
          String str2 = ((ResolveInfo)localIterator.next()).activityInfo.applicationInfo.packageName;
          com.tencent.android.tpush.a.a.e("XGService", "pullupXGServices " + str2);
          if (!b(str2))
          {
            boolean bool = paramContext.getPackageName().equals(str2);
            if (!bool) {
              try
              {
                String str1 = "am startservice -n " + str2 + "/" + XGPushServiceV3.class.getName();
                localObject = Runtime.getRuntime().exec(str1);
                int j = ((Process)localObject).waitFor();
                int i = j;
                if (j != 0)
                {
                  str1 = "am startservice --user 0 -n " + str2 + "/" + XGPushServiceV3.class.getName();
                  localObject = Runtime.getRuntime().exec(str1);
                  i = ((Process)localObject).waitFor();
                }
                if (i != 0) {
                  com.tencent.android.tpush.a.a.h("XGService", "pull up error exec cmd:" + str1 + ",exitValud:" + ((Process)localObject).exitValue());
                }
              }
              catch (Throwable localThrowable)
              {
                com.tencent.android.tpush.a.a.h("XGService", "pull up error exec cmd:" + localThrowable);
              }
            }
          }
        }
      }
      com.tencent.android.tpush.a.a.e("XGService", "pullupXGServices  with null content");
      c(paramContext);
      return;
    }
    catch (Throwable paramContext) {}
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
  
  public static String e()
  {
    return Build.MODEL;
  }
  
  public static String e(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
        return paramContext;
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("Util", ">>get imei err: ", paramContext.getCause());
      }
    }
    return "";
  }
  
  private static boolean e(String paramString)
  {
    if (b(paramString)) {}
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
  
  public static String f()
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
  
  public static boolean f(Context paramContext)
  {
    paramContext = CacheManager.getRegisterInfos(paramContext);
    return (paramContext != null) && (paramContext.size() > 0);
  }
  
  public static byte g(Context paramContext)
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
  
  public static boolean g()
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
  
  public static byte h(Context paramContext)
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
  
  public static String i(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 1)) {
          return j(paramContext);
        }
        paramContext = "" + h(paramContext) + g(paramContext);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "getKey", paramContext);
      }
    }
    return "";
  }
  
  public static String j(Context paramContext)
  {
    String str = k(paramContext);
    if (str != null)
    {
      paramContext = str;
      if (!str.equals("0")) {}
    }
    else
    {
      paramContext = f();
    }
    return paramContext;
  }
  
  public static String k(Context paramContext)
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
  
  public static String l(Context paramContext)
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
  
  public static int m(Context paramContext)
  {
    if (b != -1) {
      return b;
    }
    try
    {
      if (i.a()) {
        b = 1;
      }
      b = 0;
      return b;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
  }
  
  public static JSONArray n(Context paramContext)
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
        localMap = t(paramContext);
        localObject1 = b(paramContext);
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
  
  public static boolean o(Context paramContext)
  {
    try
    {
      paramContext = p(paramContext);
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
  
  public static ApplicationInfo p(Context paramContext)
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
  
  public static boolean q(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject == null) {
      return false;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if ((localRunningAppProcessInfo.processName.equals(paramContext.getPackageName())) && (localRunningAppProcessInfo.importance == 100)) {
        return true;
      }
    }
    return false;
  }
  
  public static String r(Context paramContext)
  {
    if (TextUtils.isEmpty(c))
    {
      int i = android.os.Process.myPid();
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (i == localRunningAppProcessInfo.pid) {
          c = localRunningAppProcessInfo.processName;
        }
      }
    }
    return c;
  }
  
  public static void s(Context paramContext)
  {
    try
    {
      paramContext = r(paramContext);
      if (paramContext.contains(":xg_service_v"))
      {
        if (!"huawei".equalsIgnoreCase(Build.MANUFACTURER))
        {
          android.os.Process.killProcess(android.os.Process.myPid());
          return;
        }
        com.tencent.android.tpush.a.a.c("XGService", "serviceSafeExit @ " + paramContext);
        XGPushServiceV3.b().stopSelf();
        return;
      }
    }
    catch (Throwable paramContext) {}
  }
  
  private static Map t(Context paramContext)
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
