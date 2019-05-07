package com.tencent.android.tpush.service.e;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
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
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.t;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.XGPushServiceV3;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.stat.a.e;
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
import org.json.JSONException;
import org.json.JSONObject;

public class m
{
  static List a = new ArrayList();
  private static long b = 0L;
  private static long c = 0L;
  private static int d = -1;
  private static int e = -1;
  private static String f = null;
  
  private static void A(Context paramContext)
  {
    if (h(paramContext) >= 2) {
      return;
    }
    g.a().a(new o(paramContext), 2000L);
  }
  
  private static Map B(Context paramContext)
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
      String str = TpnsSecurity.generateLocalSocketServieName(com.tencent.android.tpush.service.n.f());
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
  
  public static void a(Context paramContext, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    for (;;)
    {
      return;
      String str = paramJSONObject.optString("name", "");
      if ((b(str)) || (a(paramContext, str))) {
        continue;
      }
      com.tencent.android.tpush.a.a.g("Util", "pullUpOtherServiceByProviderAndActivityJSONOject " + str);
      Object localObject = paramJSONObject.optString("intent", "");
      if (!b((String)localObject)) {}
      try
      {
        localObject = new Intent((String)localObject);
        ((Intent)localObject).setFlags(268435456);
        paramContext.startActivity((Intent)localObject);
        paramJSONObject = paramJSONObject.optString("url", "");
        if (b(paramJSONObject)) {
          continue;
        }
        g.a().a(new n(paramContext, str, paramJSONObject), 2000L);
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  }
  
  static boolean a(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return false;
    }
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if ((localRunningAppProcessInfo != null) && (localRunningAppProcessInfo.processName != null) && (localRunningAppProcessInfo.processName.startsWith(paramString))) {
        return true;
      }
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
    //   7: invokevirtual 244	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: astore 11
    //   12: aload 11
    //   14: aload_1
    //   15: iconst_0
    //   16: invokevirtual 250	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
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
    //   34: invokestatic 252	com/tencent/android/tpush/service/e/m:e	(Landroid/content/Context;Ljava/lang/String;)Z
    //   37: ifne -14 -> 23
    //   40: iload 9
    //   42: istore 7
    //   44: aload_0
    //   45: aload_1
    //   46: invokestatic 254	com/tencent/android/tpush/service/e/m:f	(Landroid/content/Context;Ljava/lang/String;)Z
    //   49: ifne -26 -> 23
    //   52: iload 8
    //   54: istore 7
    //   56: iload 4
    //   58: ifeq -35 -> 23
    //   61: aload_0
    //   62: invokestatic 260	com/tencent/android/tpush/service/cache/CacheManager:getRegisterInfo	(Landroid/content/Context;)Ljava/util/List;
    //   65: astore_0
    //   66: aload_0
    //   67: ifnull +85 -> 152
    //   70: aload_0
    //   71: invokeinterface 79 1 0
    //   76: astore_0
    //   77: aload_0
    //   78: invokeinterface 85 1 0
    //   83: ifeq +69 -> 152
    //   86: aload_0
    //   87: invokeinterface 89 1 0
    //   92: checkcast 262	com/tencent/android/tpush/data/RegisterEntity
    //   95: astore_1
    //   96: aload_1
    //   97: getfield 265	com/tencent/android/tpush/data/RegisterEntity:accessId	J
    //   100: lstore 5
    //   102: lload 5
    //   104: lload_2
    //   105: lcmp
    //   106: ifne -29 -> 77
    //   109: aload 11
    //   111: aload_1
    //   112: getfield 268	com/tencent/android/tpush/data/RegisterEntity:packageName	Ljava/lang/String;
    //   115: iconst_0
    //   116: invokevirtual 250	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   119: pop
    //   120: iconst_1
    //   121: istore 4
    //   123: iload 4
    //   125: istore 7
    //   127: goto -104 -> 23
    //   130: astore_0
    //   131: ldc -113
    //   133: ldc_w 270
    //   136: aload 10
    //   138: invokestatic 150	com/tencent/android/tpush/a/a:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
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
            com.tencent.android.tpush.a.a.i("Utils", " discurd the msg due to time not accepted! acceptTime = " + (String)localObject1 + " , curTime= " + j);
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
    return b().contains(paramString);
  }
  
  public static boolean a(JSONArray paramJSONArray)
  {
    return (paramJSONArray == null) || (paramJSONArray.length() <= 0);
  }
  
  public static boolean a(JSONObject paramJSONObject)
  {
    return (paramJSONObject == null) || (paramJSONObject.length() <= 0);
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
          break label342;
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
            break label350;
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
        com.tencent.android.tpush.a.a.e("Utils", "get acceptTime = " + (String)localObject1 + " , acceptBeginTime= " + l1);
        return l1;
        label342:
        j = 0;
        continue;
      }
      int k = m;
      label350:
      j += 1;
      i = k;
    }
  }
  
  public static List b()
  {
    if (a.isEmpty())
    {
      a.add("com.jingdong.app.mall");
      a.add("com.ifeng.news2");
    }
    return a;
  }
  
  public static void b(Context paramContext)
  {
    if (b <= 0L) {
      b = h.a(paramContext, "last_reportAppList_time", -1L);
    }
    long l = System.currentTimeMillis();
    JSONArray localJSONArray;
    JSONObject localJSONObject;
    if (l - b > 259200000L)
    {
      b = l;
      localJSONArray = r(paramContext);
      localJSONObject = new JSONObject();
    }
    try
    {
      localJSONObject.put("ap_ls", localJSONArray);
      com.tencent.android.tpush.service.d.a.a(paramContext, "app_list", localJSONObject);
      h.b(paramContext, "last_reportAppList_time", b);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      Object localObject1 = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        paramContext = XGPushServiceV3.class.getName();
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (ActivityManager.RunningServiceInfo)((Iterator)localObject1).next();
          if (paramContext.equals(((ActivityManager.RunningServiceInfo)localObject2).service.getClassName()))
          {
            localObject2 = ((ActivityManager.RunningServiceInfo)localObject2).service.getPackageName();
            if ((e.b((String)localObject2)) && (((String)localObject2).equals(paramString)))
            {
              com.tencent.android.tpush.a.a.c("XGService", "isSurvive srvPkg :" + (String)localObject2);
              return true;
            }
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      com.tencent.android.tpush.a.a.c("XGService", "checkXGServiceV3IsRunningByPkgName", paramContext);
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString, long paramLong)
  {
    return a(paramContext, paramString, paramLong, false);
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
  
  public static List c(Context paramContext, String paramString)
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
    com.tencent.android.tpush.a.a.i("XGService", "getLocalPushServicesInfo the context == null");
    return null;
  }
  
  public static void c(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (d.a(paramContext))
        {
          i = 1;
          if (d < 0) {
            d = f.b(paramContext, "notification_st", -1);
          }
          if (c <= 0L) {
            c = h.a(paramContext, "last_reportNotification_time", -1L);
          }
          long l = System.currentTimeMillis();
          if ((i == d) && (l - c > 259200000L)) {
            return;
          }
          d = i;
          c = l;
          h.b(paramContext, "last_reportNotification_time", c);
          f.a(paramContext, "notification_st", i);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("nf_st", d);
          com.tencent.android.tpush.service.d.a.a(paramContext, "notification_st", localJSONObject);
          return;
        }
      }
      catch (Throwable paramContext)
      {
        com.tencent.android.tpush.a.a.c("TPush", "reportNotificationStatus", paramContext);
        return;
      }
      int i = 0;
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
  
  public static int d()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String d(String paramString)
  {
    if (com.tencent.android.tpush.service.n.f() != null) {
      try
      {
        paramString = TpnsSecurity.getEncryptAPKSignature(com.tencent.android.tpush.service.n.f().createPackageContext(paramString, 0));
        return paramString;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        com.tencent.android.tpush.a.a.c("TPush", "+++ getAppCert exception.", paramString);
      }
    }
    return "";
  }
  
  public static List d(Context paramContext)
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
  
  public static boolean d(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      throw new IllegalArgumentException("empty params");
    }
    return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
  }
  
  public static String e()
  {
    return Build.MODEL;
  }
  
  public static void e(Context paramContext)
  {
    label248:
    for (;;)
    {
      try
      {
        paramContext = com.tencent.android.tpush.service.a.a.a(paramContext).J;
        if (paramContext != null)
        {
          Iterator localIterator = paramContext.entrySet().iterator();
          if (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            try
            {
              String str = "am startservice -n " + (String)localEntry.getKey() + "/" + (String)localEntry.getValue();
              paramContext = Runtime.getRuntime().exec(str);
              int i = paramContext.waitFor();
              if (i == 0) {
                break label248;
              }
              str = "am startservice --user 0 -n " + (String)localEntry.getKey() + "/" + (String)localEntry.getValue();
              paramContext = Runtime.getRuntime().exec(str);
              i = paramContext.waitFor();
              if (i == 0) {
                continue;
              }
              com.tencent.android.tpush.a.a.i("XGService", "pullUpServerConfigPkgs error exec cmd:" + str + ",exitValud:" + paramContext.exitValue());
            }
            catch (Throwable paramContext)
            {
              com.tencent.android.tpush.a.a.i("XGService", "pullUpServerConfigPkgs error exec cmd:" + paramContext);
            }
            continue;
          }
        }
        return;
      }
      catch (Throwable paramContext) {}
    }
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    if (t.c(paramString)) {
      return false;
    }
    if (paramContext != null) {
      try
      {
        paramContext = d(paramContext);
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
  
  public static void f(Context paramContext)
  {
    for (;;)
    {
      int i;
      try
      {
        localObject = com.tencent.android.tpush.service.a.a.a(paramContext).I;
        if ((localObject != null) && (((JSONArray)localObject).length() != 0)) {
          break label140;
        }
        com.tencent.android.tpush.a.a.g("Util", "pullupOtherServiceByProviderAndActivity no running");
        if (com.tencent.android.tpush.service.a.a.a(paramContext).H == 0) {
          break label139;
        }
        localObject = new JSONObject();
        ((JSONObject)localObject).put("name", "com.tencent.qgame:wns");
        ((JSONObject)localObject).put("intent", "com.tencent.qgame.XINGEPUSH");
        ((JSONObject)localObject).put("url", "com.tencent.qgame.keepalive/keepalive");
        com.tencent.android.tpush.a.a.g("Util", "pullupOtherServiceByProviderAndActivity  qgame");
        a(paramContext, (JSONObject)localObject);
        return;
      }
      catch (Throwable paramContext)
      {
        Object localObject;
        com.tencent.android.tpush.a.a.i("XGService", "pullupOtherServiceByProviderAndActivity" + paramContext);
      }
      if (i < ((JSONArray)localObject).length())
      {
        a(paramContext, ((JSONArray)localObject).optJSONObject(i));
        i += 1;
        continue;
        label139:
        return;
        label140:
        i = 0;
      }
    }
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    if (t.c(paramString)) {}
    for (;;)
    {
      return false;
      if (paramContext != null) {
        try
        {
          paramContext = c(paramContext, paramString + ".PUSH_ACTION");
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
  
  public static void g(Context paramContext)
  {
    com.tencent.android.tpush.a.b(paramContext);
    if (a(paramContext.getPackageName()))
    {
      com.tencent.android.tpush.a.a.g("Util", paramContext.getPackageName() + " ingore.");
      return;
    }
    try
    {
      if (h(paramContext) >= 2) {
        com.tencent.android.tpush.a.a.g("Util", "more than two XGV3 service running");
      }
      for (;;)
      {
        f(paramContext);
        e(paramContext);
        return;
        z(paramContext);
        A(paramContext);
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        com.tencent.android.tpush.a.a.i("XGService", "pullUpXGServiceByRemoteService" + localThrowable);
      }
    }
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
  
  public static boolean g(Context paramContext, String paramString)
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
  
  public static int h(Context paramContext)
  {
    int m = 0;
    int n = 0;
    int i = 0;
    int j = m;
    label138:
    for (;;)
    {
      try
      {
        Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        int k = n;
        if (localObject != null)
        {
          j = m;
          k = n;
          if (((List)localObject).size() > 0)
          {
            j = m;
            paramContext = XGPushServiceV3.class.getName();
            j = m;
            localObject = ((List)localObject).iterator();
            j = i;
            k = i;
            if (((Iterator)localObject).hasNext())
            {
              j = i;
              boolean bool = paramContext.equals(((ActivityManager.RunningServiceInfo)((Iterator)localObject).next()).service.getClassName());
              if (!bool) {
                break label138;
              }
              i += 1;
              continue;
            }
          }
        }
        return k;
      }
      catch (Throwable paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "checkXGServiceV3IsRunningByPkgName", paramContext);
        k = j;
      }
    }
  }
  
  public static String i(Context paramContext)
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
  
  public static boolean j(Context paramContext)
  {
    paramContext = CacheManager.getRegisterInfos(paramContext);
    return (paramContext != null) && (paramContext.size() > 0);
  }
  
  public static byte k(Context paramContext)
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
  
  public static byte l(Context paramContext)
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
  
  public static String m(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 1)) {
          return n(paramContext);
        }
        paramContext = "" + l(paramContext) + k(paramContext);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "getKey", paramContext);
      }
    }
    return "";
  }
  
  public static String n(Context paramContext)
  {
    String str = o(paramContext);
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
  
  public static String o(Context paramContext)
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
  
  public static String p(Context paramContext)
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
  
  public static int q(Context paramContext)
  {
    if (e != -1) {
      return e;
    }
    try
    {
      if (p.a()) {
        e = 1;
      }
      e = 0;
      return e;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
  }
  
  public static JSONArray r(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    Map localMap1;
    PackageManager localPackageManager;
    Map localMap2;
    Object localObject1;
    Object localObject2;
    try
    {
      localMap1 = s(paramContext);
      localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
      {
        localMap2 = B(paramContext);
        localObject1 = d(paramContext);
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
            localJSONObject.put("pn", localApplicationInfo.packageName);
          }
          if (((PackageInfo)localObject2).versionName != null) {
            localJSONObject.put("av", ((PackageInfo)localObject2).versionName);
          }
          if (localMap2.containsKey(localApplicationInfo.packageName)) {
            localJSONObject.put("rn", "1");
          }
          if (paramContext.containsKey(localApplicationInfo.packageName)) {
            localJSONObject.put("xg", "1");
          }
          localJSONObject.put("fit", ((PackageInfo)localObject2).firstInstallTime / 1000L);
          localJSONObject.put("lut", ((PackageInfo)localObject2).lastUpdateTime / 1000L);
          localJSONObject.put("fg", ((PackageInfo)localObject2).applicationInfo.flags);
          if (localMap1.containsKey(localApplicationInfo.packageName)) {
            localJSONObject.put("rt", 1);
          }
          localJSONArray.put(localJSONObject);
        }
      }
    }
  }
  
  public static Map s(Context paramContext)
  {
    localHashMap = new HashMap();
    try
    {
      Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRecentTasks(64, 1).iterator();
      while (localIterator.hasNext())
      {
        Object localObject = ((ActivityManager.RecentTaskInfo)localIterator.next()).baseIntent;
        localObject = paramContext.getPackageManager().resolveActivity((Intent)localObject, 0);
        if (localObject != null) {
          localHashMap.put(((ResolveInfo)localObject).resolvePackageName, Integer.valueOf(1));
        }
      }
      return localHashMap;
    }
    catch (Throwable paramContext) {}
  }
  
  public static JSONArray t(Context paramContext)
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
        localMap = B(paramContext);
        localObject1 = d(paramContext);
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
  
  public static boolean u(Context paramContext)
  {
    try
    {
      paramContext = v(paramContext);
      if (paramContext == null)
      {
        com.tencent.android.tpush.a.a.j("TPush", "Failed to init due to null ApplicationInfo.");
        return false;
      }
      if (paramContext.icon == 0)
      {
        com.tencent.android.tpush.a.a.j("TPush", "Failed to get Application icon in AndroidManifest.xml, You App maybe can not show notification, Please add Application icon in AndroidManifest.xml");
        return false;
      }
    }
    catch (Throwable paramContext)
    {
      return false;
    }
    return true;
  }
  
  public static ApplicationInfo v(Context paramContext)
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
  
  public static boolean w(Context paramContext)
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
  
  public static String x(Context paramContext)
  {
    if (TextUtils.isEmpty(f))
    {
      int i = android.os.Process.myPid();
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (i == localRunningAppProcessInfo.pid) {
          f = localRunningAppProcessInfo.processName;
        }
      }
    }
    return f;
  }
  
  public static void y(Context paramContext)
  {
    try
    {
      paramContext = x(paramContext);
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
  
  private static void z(Context paramContext)
  {
    Object localObject = d(paramContext);
    if (h(paramContext) >= 2) {}
    int j;
    label31:
    int k;
    do
    {
      return;
      if (localObject == null) {
        break label334;
      }
      Iterator localIterator = ((List)localObject).iterator();
      j = 0;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (ResolveInfo)localIterator.next();
      k = j + 1;
      if (!"oppo".equals(t.b())) {
        break label328;
      }
    } while (k > 2);
    label328:
    while (k <= 4)
    {
      String str2 = ((ResolveInfo)localObject).activityInfo.applicationInfo.packageName;
      j = k;
      if (b(str2)) {
        break label31;
      }
      j = k;
      if (paramContext.getPackageName().equals(str2)) {
        break label31;
      }
      j = k;
      if (b(paramContext, str2)) {
        break label31;
      }
      if (h(paramContext) >= 2) {
        break;
      }
      try
      {
        String str1 = "am startservice -n " + str2 + "/" + XGPushServiceV3.class.getName();
        localObject = Runtime.getRuntime().exec(str1);
        j = ((Process)localObject).waitFor();
        int i = j;
        if (j != 0)
        {
          str1 = "am startservice --user 0 -n " + str2 + "/" + XGPushServiceV3.class.getName();
          localObject = Runtime.getRuntime().exec(str1);
          i = ((Process)localObject).waitFor();
        }
        j = k;
        if (i == 0) {
          break label31;
        }
        com.tencent.android.tpush.a.a.i("XGService", "pull up error exec cmd:" + str1 + ",exitValud:" + ((Process)localObject).exitValue());
        j = k;
      }
      catch (Throwable localThrowable)
      {
        com.tencent.android.tpush.a.a.i("XGService", "pull up error exec cmd:" + localThrowable);
        j = k;
      }
      break label31;
      break;
    }
    return;
    label334:
    com.tencent.android.tpush.a.a.f("XGService", "pullupXGServices  with null content");
  }
}
