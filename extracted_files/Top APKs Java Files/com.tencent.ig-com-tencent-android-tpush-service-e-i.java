package com.tencent.android.tpush.service.e;

import android.app.ActivityManager;
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
import android.content.pm.ServiceInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.android.tpush.TypeStr;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.MobileType;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.XGPushServiceV4;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class i
{
  static List<String> a = new ArrayList();
  private static long b = 0L;
  private static long c = 0L;
  private static int d = -1;
  private static String e = null;
  
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
  
  private static Object a(Context paramContext, String paramString, Object paramObject)
  {
    ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
    paramContext = paramObject;
    if (localApplicationInfo != null)
    {
      paramString = localApplicationInfo.metaData.get(paramString);
      paramContext = paramObject;
      if (paramString != null) {
        paramContext = paramString;
      }
    }
    return paramContext;
  }
  
  public static String a()
  {
    try
    {
      String str = TpnsSecurity.generateLocalSocketServieName(com.tencent.android.tpush.service.b.f());
      com.tencent.android.tpush.b.a.f("XGService", "getSocketName: " + str);
      return str;
    }
    catch (Exception localException)
    {
      com.tencent.android.tpush.b.a.d("XGService", "getSocketName", localException);
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
  
  public static List<String> a(Context paramContext, List<String> paramList)
  {
    if (paramList == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramList.size())
    {
      if (i(paramContext, (String)paramList.get(i)))
      {
        localArrayList.clear();
        localArrayList.add(paramList.get(i));
        return localArrayList;
      }
      if (a(paramContext, (String)paramList.get(i))) {
        localArrayList.add(paramList.get(i));
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static void a(Context paramContext, final JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    for (;;)
    {
      return;
      final String str = paramJSONObject.optString("name", "");
      if ((b(str)) || (b(paramContext, str))) {
        continue;
      }
      com.tencent.android.tpush.b.a.g("Util", "pullUpOtherServiceByProviderAndActivityJSONOject " + str);
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
        com.tencent.android.tpush.common.c.a().a(new Runnable()
        {
          public void run()
          {
            try
            {
              if (i.b(this.a, str)) {
                return;
              }
              Uri localUri = Uri.parse("content://" + paramJSONObject);
              com.tencent.android.tpush.a.b.a(this.a, localUri);
              return;
            }
            catch (Throwable localThrowable) {}
          }
        }, 2000L);
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  }
  
  public static boolean a(Context paramContext, Long paramLong)
  {
    return (paramLong.longValue() > 0L) && (paramLong.longValue() == XGPushConfig.getChannelId(paramContext));
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        if (((ActivityManager.RunningAppProcessInfo)paramContext.next()).processName.equals(paramString)) {
          return true;
        }
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
    //   7: invokevirtual 69	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   10: astore 11
    //   12: aload 11
    //   14: aload_1
    //   15: iconst_0
    //   16: invokevirtual 280	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
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
    //   34: invokestatic 282	com/tencent/android/tpush/service/e/i:f	(Landroid/content/Context;Ljava/lang/String;)Z
    //   37: ifne -14 -> 23
    //   40: iload 9
    //   42: istore 7
    //   44: aload_0
    //   45: aload_1
    //   46: invokestatic 284	com/tencent/android/tpush/service/e/i:g	(Landroid/content/Context;Ljava/lang/String;)Z
    //   49: ifne -26 -> 23
    //   52: iload 8
    //   54: istore 7
    //   56: iload 4
    //   58: ifeq -35 -> 23
    //   61: aload_0
    //   62: invokestatic 290	com/tencent/android/tpush/service/cache/CacheManager:getRegisterInfo	(Landroid/content/Context;)Ljava/util/List;
    //   65: astore_0
    //   66: aload_0
    //   67: ifnull +85 -> 152
    //   70: aload_0
    //   71: invokeinterface 254 1 0
    //   76: astore_0
    //   77: aload_0
    //   78: invokeinterface 260 1 0
    //   83: ifeq +69 -> 152
    //   86: aload_0
    //   87: invokeinterface 264 1 0
    //   92: checkcast 292	com/tencent/android/tpush/data/RegisterEntity
    //   95: astore_1
    //   96: aload_1
    //   97: getfield 295	com/tencent/android/tpush/data/RegisterEntity:accessId	J
    //   100: lstore 5
    //   102: lload 5
    //   104: lload_2
    //   105: lcmp
    //   106: ifne -29 -> 77
    //   109: aload 11
    //   111: aload_1
    //   112: getfield 298	com/tencent/android/tpush/data/RegisterEntity:packageName	Ljava/lang/String;
    //   115: iconst_0
    //   116: invokevirtual 280	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   119: pop
    //   120: iconst_1
    //   121: istore 4
    //   123: iload 4
    //   125: istore 7
    //   127: goto -104 -> 23
    //   130: astore_0
    //   131: ldc 107
    //   133: ldc_w 300
    //   136: aload 10
    //   138: invokestatic 129	com/tencent/android/tpush/b/a:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
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
            com.tencent.android.tpush.b.a.i("Utils", " discurd the msg due to time not accepted! acceptTime = " + (String)localObject1 + " , curTime= " + j);
            return false;
          }
        }
      }
      catch (Throwable paramIntent)
      {
        com.tencent.android.tpush.b.a.d("XGService", "checkAcceptTime", paramIntent);
      }
      return true;
      label331:
      i += 1;
    }
  }
  
  public static boolean a(String paramString)
  {
    return d().contains(paramString);
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
        com.tencent.android.tpush.b.a.d("XGService", "getAcceptBeginTime", paramIntent);
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
        com.tencent.android.tpush.b.a.e("Utils", "get acceptTime = " + (String)localObject1 + " , acceptBeginTime= " + l1);
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
  
  public static String b()
  {
    String str = a();
    return com.tencent.android.tpush.encrypt.a.a(str + "V4");
  }
  
  public static List<String> b(Context paramContext, Long paramLong)
  {
    localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = paramContext.getPackageManager().queryBroadcastReceivers(new Intent("com.tencent.android.tpush.action.SDK"), 512).iterator();
      while (localIterator.hasNext())
      {
        String str = ((ResolveInfo)localIterator.next()).activityInfo.applicationInfo.packageName;
        try
        {
          Object localObject = a(paramContext.createPackageContext(str, 0), "XG_V4_CHANNEL_ID", null);
          if ((localObject != null) && (paramLong.toString().equals(localObject.toString()))) {
            localArrayList.add(str);
          }
        }
        catch (Exception localException) {}
      }
      return localArrayList;
    }
    catch (Throwable paramContext) {}
  }
  
  public static List<String> b(Context paramContext, List<Long> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      List localList = c(paramContext, (Long)paramList.next());
      localList.removeAll(localArrayList);
      localArrayList.addAll(localList);
    }
    return localArrayList;
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
      localJSONArray = o(paramContext);
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
  
  static boolean b(Context paramContext, String paramString)
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
        com.tencent.android.tpush.b.a.d("TPush", "service Util@@parseIpAddress(" + paramString + ")", localThrowable);
      }
    }
  }
  
  public static String c()
  {
    String str = a();
    return com.tencent.android.tpush.encrypt.a.a(str + "V4.Slave");
  }
  
  public static List<String> c(Context paramContext, Long paramLong)
  {
    localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = paramContext.getPackageManager().queryBroadcastReceivers(new Intent("com.tencent.android.tpush.action.SDK"), 512).iterator();
      while (localIterator.hasNext())
      {
        String str = ((ResolveInfo)localIterator.next()).activityInfo.applicationInfo.packageName;
        try
        {
          Object localObject = a(paramContext.createPackageContext(str, 0), "XG_V2_ACCESS_ID", null);
          if ((localObject != null) && (paramLong.toString().equals(localObject.toString()))) {
            localArrayList.add(str);
          }
        }
        catch (Exception localException) {}
      }
      return localArrayList;
    }
    catch (Throwable paramContext) {}
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
        com.tencent.android.tpush.b.a.d("TPush", "reportNotificationStatus", paramContext);
        return;
      }
      int i = 0;
    }
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.queryIntentServices(new Intent(paramContext.createPackageContext(paramString, 0), XGPushServiceV4.class), 0);
      if ((paramContext == null) || (paramContext.isEmpty())) {
        break label90;
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        boolean bool = ((ResolveInfo)paramContext.next()).serviceInfo.processName.contains("xg_service");
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
    label90:
    return false;
  }
  
  public static String d(Context paramContext)
  {
    String str1 = b();
    String str2 = CacheManager.getToken(paramContext);
    paramContext = str1;
    if (!l.c(str2))
    {
      paramContext = str1;
      if (!"0".equals(str2)) {
        paramContext = str1 + str2;
      }
    }
    return paramContext;
  }
  
  public static String d(String paramString)
  {
    if (com.tencent.android.tpush.service.b.f() != null) {
      try
      {
        paramString = TpnsSecurity.getEncryptAPKSignature(com.tencent.android.tpush.service.b.f().createPackageContext(paramString, 0));
        return paramString;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        com.tencent.android.tpush.b.a.d("TPush", "+++ getAppCert exception.", paramString);
      }
    }
    return "";
  }
  
  public static List<String> d()
  {
    if (a.isEmpty())
    {
      a.add("com.jingdong.app.mall");
      a.add("com.ifeng.news2");
    }
    return a;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    try
    {
      Object localObject1 = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        paramContext = XGPushServiceV4.class.getName();
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (ActivityManager.RunningServiceInfo)((Iterator)localObject1).next();
          if (paramContext.equals(((ActivityManager.RunningServiceInfo)localObject2).service.getClassName()))
          {
            localObject2 = ((ActivityManager.RunningServiceInfo)localObject2).service.getPackageName();
            if ((com.tencent.android.tpush.stat.a.c.b((String)localObject2)) && (((String)localObject2).equals(paramString)))
            {
              com.tencent.android.tpush.b.a.c("XGService", "isSurvive srvPkg :" + (String)localObject2);
              return true;
            }
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      com.tencent.android.tpush.b.a.d("XGService", "checkXGServiceV3IsRunningByPkgName", paramContext);
    }
    return false;
  }
  
  public static int e()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static List<ResolveInfo> e(Context paramContext)
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
        com.tencent.android.tpush.b.a.d("XGService", "getLocalPushAppsInfo", paramContext);
      }
    }
    paramContext = new ArrayList(localHashMap.values());
    return paramContext;
  }
  
  public static List<ResolveInfo> e(Context paramContext, String paramString)
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
        com.tencent.android.tpush.b.a.d("XGService", "getLocalPushServicesInfo", paramContext);
        return null;
      }
    }
    com.tencent.android.tpush.b.a.i("XGService", "getLocalPushServicesInfo the context == null");
    return null;
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
  
  public static void f(Context paramContext)
  {
    label248:
    for (;;)
    {
      try
      {
        paramContext = com.tencent.android.tpush.service.a.a.a(paramContext).K;
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
              com.tencent.android.tpush.b.a.i("XGService", "pullUpServerConfigPkgs error exec cmd:" + str + ",exitValud:" + paramContext.exitValue());
            }
            catch (Throwable paramContext)
            {
              com.tencent.android.tpush.b.a.i("XGService", "pullUpServerConfigPkgs error exec cmd:" + paramContext);
            }
            continue;
          }
        }
        return;
      }
      catch (Throwable paramContext) {}
    }
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    if (l.c(paramString)) {
      return false;
    }
    if (paramContext != null) {
      try
      {
        paramContext = e(paramContext);
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
        com.tencent.android.tpush.b.a.d("XGService", "isLocalApp", paramContext);
      }
    }
    return false;
  }
  
  public static void g(Context paramContext)
  {
    for (;;)
    {
      int i;
      try
      {
        localJSONArray = com.tencent.android.tpush.service.a.a.a(paramContext).J;
        if ((localJSONArray != null) && (localJSONArray.length() != 0)) {
          break label79;
        }
        com.tencent.android.tpush.b.a.g("Util", "pullupOtherServiceByProviderAndActivity no running");
        return;
      }
      catch (Throwable paramContext)
      {
        JSONArray localJSONArray;
        com.tencent.android.tpush.b.a.i("XGService", "pullupOtherServiceByProviderAndActivity" + paramContext);
      }
      if (i < localJSONArray.length())
      {
        a(paramContext, localJSONArray.optJSONObject(i));
        i += 1;
      }
      else
      {
        return;
        label79:
        i = 0;
      }
    }
  }
  
  public static boolean g(Context paramContext, String paramString)
  {
    if (l.c(paramString)) {}
    for (;;)
    {
      return false;
      if (paramContext != null) {
        try
        {
          paramContext = e(paramContext, paramString + ".PUSH_ACTION");
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
          com.tencent.android.tpush.b.a.d("XGService", "isPkgHasRemoteService", paramContext);
        }
      }
    }
    return false;
  }
  
  public static void h(Context paramContext)
  {
    com.tencent.android.tpush.a.b(paramContext);
    if (a(paramContext.getPackageName()))
    {
      com.tencent.android.tpush.b.a.g("Util", paramContext.getPackageName() + " ingore.");
      return;
    }
    try
    {
      if (i(paramContext) >= 2) {
        com.tencent.android.tpush.b.a.g("Util", "more than two XGV3 service running");
      }
      for (;;)
      {
        g(paramContext);
        f(paramContext);
        return;
        w(paramContext);
        x(paramContext);
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        com.tencent.android.tpush.b.a.i("XGService", "pullUpXGServiceByRemoteService" + localThrowable);
      }
    }
  }
  
  public static boolean h(Context paramContext, String paramString)
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
  
  public static int i(Context paramContext)
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
            paramContext = XGPushServiceV4.class.getName();
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
        com.tencent.android.tpush.b.a.d("XGService", "checkXGServiceV3IsRunningByPkgName", paramContext);
        k = j;
      }
    }
  }
  
  public static boolean i(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if ((localRunningAppProcessInfo.processName.equals(paramString)) && (localRunningAppProcessInfo.importance == 100)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean j(Context paramContext)
  {
    paramContext = CacheManager.getRegisterInfos(paramContext);
    return (paramContext != null) && (paramContext.size() > 0);
  }
  
  public static byte k(Context paramContext)
  {
    byte b1 = MobileType.UNKNOWN.getType();
    if (paramContext != null) {
      try
      {
        paramContext = CustomDeviceInfos.getSimOperator(paramContext);
        if (paramContext != null)
        {
          if ((paramContext.equals("46000")) || (paramContext.equals("46002")) || (paramContext.equals("46007")) || (paramContext.equals("46020"))) {
            return MobileType.CHINAMOBILE.getType();
          }
          if ((paramContext.equals("46001")) || (paramContext.equals("46006"))) {
            return MobileType.UNICOM.getType();
          }
          if ((paramContext.equals("46003")) || (paramContext.equals("46005")))
          {
            byte b2 = MobileType.TELCOM.getType();
            return b2;
          }
        }
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.b.a.d("XGService", "getIsp", paramContext);
      }
    }
    return b1;
  }
  
  public static String l(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 1)) {
          return m(paramContext);
        }
        paramContext = "" + k(paramContext) + DeviceInfos.getNetworkType(paramContext);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.b.a.d("XGService", "getKey", paramContext);
      }
    }
    return "";
  }
  
  public static String m(Context paramContext)
  {
    String str = CustomDeviceInfos.getWiFiBBSID(paramContext);
    Object localObject;
    if (str != null)
    {
      localObject = str;
      if (str.length() != 0) {}
    }
    else
    {
      paramContext = CustomDeviceInfos.getIp(paramContext);
      if (paramContext != null)
      {
        localObject = paramContext;
        if (paramContext.length() != 0) {}
      }
      else
      {
        localObject = "0";
      }
    }
    return localObject;
  }
  
  public static String n(Context paramContext)
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
  
  public static JSONArray o(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    Map localMap1;
    PackageManager localPackageManager;
    Map localMap2;
    Object localObject1;
    HashMap localHashMap;
    Object localObject2;
    try
    {
      localMap1 = CustomDeviceInfos.getRecentTasks(paramContext);
      localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
      {
        localMap2 = CustomDeviceInfos.getRunningAppProces(paramContext);
        localObject1 = e(paramContext);
        localHashMap = new HashMap();
        if ((localObject1 != null) && (((List)localObject1).size() > 0))
        {
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ResolveInfo)((Iterator)localObject1).next();
            if (((ResolveInfo)localObject2).activityInfo != null) {
              localHashMap.put(((ResolveInfo)localObject2).activityInfo.packageName, Integer.valueOf(1));
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
      com.tencent.android.tpush.b.a.d("TPush", "failed to get app.", paramContext);
    }
    for (;;)
    {
      paramContext = CustomDeviceInfos.getInstalledPackages(paramContext).iterator();
      while (paramContext.hasNext())
      {
        localObject1 = (PackageInfo)paramContext.next();
        localObject2 = new JSONObject();
        ApplicationInfo localApplicationInfo = ((PackageInfo)localObject1).applicationInfo;
        if ((((PackageInfo)localObject1).applicationInfo.flags & 0x1) != 0)
        {
          if (e(localApplicationInfo.packageName)) {
            ((JSONObject)localObject2).put("s", "1");
          }
        }
        else
        {
          String str = localPackageManager.getApplicationLabel(((PackageInfo)localObject1).applicationInfo).toString();
          if (str != null) {
            ((JSONObject)localObject2).put("n", str);
          }
          if (localApplicationInfo.packageName != null) {
            ((JSONObject)localObject2).put("pn", localApplicationInfo.packageName);
          }
          if (((PackageInfo)localObject1).versionName != null) {
            ((JSONObject)localObject2).put("av", ((PackageInfo)localObject1).versionName);
          }
          if (localMap2.containsKey(localApplicationInfo.packageName)) {
            ((JSONObject)localObject2).put("rn", "1");
          }
          if (localHashMap.containsKey(localApplicationInfo.packageName)) {
            ((JSONObject)localObject2).put("xg", "1");
          }
          ((JSONObject)localObject2).put("fit", ((PackageInfo)localObject1).firstInstallTime / 1000L);
          ((JSONObject)localObject2).put("lut", ((PackageInfo)localObject1).lastUpdateTime / 1000L);
          ((JSONObject)localObject2).put("fg", ((PackageInfo)localObject1).applicationInfo.flags);
          if (localMap1.containsKey(localApplicationInfo.packageName)) {
            ((JSONObject)localObject2).put("rt", 1);
          }
          localJSONArray.put(localObject2);
        }
      }
    }
  }
  
  public static JSONArray p(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    PackageManager localPackageManager;
    Map localMap;
    Object localObject1;
    HashMap localHashMap;
    Object localObject2;
    try
    {
      localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
      {
        localMap = CustomDeviceInfos.getRunningAppProces(paramContext);
        localObject1 = e(paramContext);
        localHashMap = new HashMap();
        if ((localObject1 != null) && (((List)localObject1).size() > 0))
        {
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ResolveInfo)((Iterator)localObject1).next();
            if (((ResolveInfo)localObject2).activityInfo != null) {
              localHashMap.put(((ResolveInfo)localObject2).activityInfo.packageName, Integer.valueOf(1));
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
      com.tencent.android.tpush.b.a.d("TPush", "failed to get app.", paramContext);
    }
    for (;;)
    {
      paramContext = CustomDeviceInfos.getInstalledPackages(paramContext).iterator();
      while (paramContext.hasNext())
      {
        localObject1 = (PackageInfo)paramContext.next();
        localObject2 = new JSONObject();
        ApplicationInfo localApplicationInfo = ((PackageInfo)localObject1).applicationInfo;
        if ((localMap.containsKey(localApplicationInfo.packageName)) || (localHashMap.containsKey(localApplicationInfo.packageName))) {
          if ((((PackageInfo)localObject1).applicationInfo.flags & 0x1) != 0)
          {
            if (e(localApplicationInfo.packageName)) {
              ((JSONObject)localObject2).put("s", "1");
            }
          }
          else
          {
            String str = localPackageManager.getApplicationLabel(((PackageInfo)localObject1).applicationInfo).toString();
            if (str != null) {
              ((JSONObject)localObject2).put("n", str);
            }
            if (localApplicationInfo.packageName != null) {
              ((JSONObject)localObject2).put("p", localApplicationInfo.packageName);
            }
            if (((PackageInfo)localObject1).versionName != null) {
              ((JSONObject)localObject2).put("v", ((PackageInfo)localObject1).versionName);
            }
            if (localMap.containsKey(localApplicationInfo.packageName)) {
              ((JSONObject)localObject2).put("r", "1");
            }
            if (localHashMap.containsKey(localApplicationInfo.packageName)) {
              ((JSONObject)localObject2).put("xg", "1");
            }
            localJSONArray.put(localObject2);
          }
        }
      }
    }
  }
  
  public static boolean q(Context paramContext)
  {
    try
    {
      paramContext = r(paramContext);
      if (paramContext == null)
      {
        com.tencent.android.tpush.b.a.j("TPush", "Failed to init due to null ApplicationInfo.");
        return false;
      }
      if (paramContext.icon == 0)
      {
        com.tencent.android.tpush.b.a.j("TPush", "Failed to get Application icon in AndroidManifest.xml, You App maybe can not show notification, Please add Application icon in AndroidManifest.xml");
        return false;
      }
    }
    catch (Throwable paramContext)
    {
      return false;
    }
    return true;
  }
  
  public static ApplicationInfo r(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      com.tencent.android.tpush.b.a.e("TPush", "Failed to get Application info", paramContext);
    }
    return null;
  }
  
  public static boolean s(Context paramContext)
  {
    return i(paramContext, paramContext.getPackageName());
  }
  
  public static String t(Context paramContext)
  {
    if (TextUtils.isEmpty(e))
    {
      int i = android.os.Process.myPid();
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (i == localRunningAppProcessInfo.pid) {
          e = localRunningAppProcessInfo.processName;
        }
      }
    }
    return e;
  }
  
  public static void u(Context paramContext)
  {
    try
    {
      paramContext = t(paramContext);
      if (paramContext.contains(":xg_service_v"))
      {
        if (!"huawei".equalsIgnoreCase(Build.MANUFACTURER))
        {
          android.os.Process.killProcess(android.os.Process.myPid());
          return;
        }
        com.tencent.android.tpush.b.a.c("XGService", "serviceSafeExit @ " + paramContext);
        XGPushServiceV4.b().stopSelf();
        return;
      }
    }
    catch (Throwable paramContext) {}
  }
  
  /* Error */
  public static boolean v(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 599	com/tencent/android/tpush/service/e/i:b	()Ljava/lang/String;
    //   3: astore 4
    //   5: new 1047	android/net/LocalServerSocket
    //   8: dup
    //   9: aload 4
    //   11: invokespecial 1048	android/net/LocalServerSocket:<init>	(Ljava/lang/String;)V
    //   14: astore_3
    //   15: ldc_w 545
    //   18: new 109	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 110	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 1050
    //   28: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload 4
    //   33: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: invokestatic 1052	com/tencent/android/tpush/b/a:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   42: aload_0
    //   43: invokestatic 1054	com/tencent/android/tpush/service/e/i:d	(Landroid/content/Context;)Ljava/lang/String;
    //   46: astore 6
    //   48: new 1047	android/net/LocalServerSocket
    //   51: dup
    //   52: aload 6
    //   54: invokespecial 1048	android/net/LocalServerSocket:<init>	(Ljava/lang/String;)V
    //   57: astore_0
    //   58: aload_0
    //   59: astore 5
    //   61: aload_3
    //   62: astore 4
    //   64: ldc_w 545
    //   67: new 109	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 110	java/lang/StringBuilder:<init>	()V
    //   74: ldc_w 1056
    //   77: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: aload 6
    //   82: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: invokestatic 1052	com/tencent/android/tpush/b/a:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   91: iconst_0
    //   92: istore_2
    //   93: aload_3
    //   94: ifnull +7 -> 101
    //   97: aload_3
    //   98: invokevirtual 1059	android/net/LocalServerSocket:close	()V
    //   101: iload_2
    //   102: istore_1
    //   103: aload_0
    //   104: ifnull +9 -> 113
    //   107: aload_0
    //   108: invokevirtual 1059	android/net/LocalServerSocket:close	()V
    //   111: iload_2
    //   112: istore_1
    //   113: iload_1
    //   114: ireturn
    //   115: astore_3
    //   116: ldc_w 545
    //   119: ldc_w 1061
    //   122: aload_3
    //   123: invokestatic 129	com/tencent/android/tpush/b/a:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   126: goto -25 -> 101
    //   129: astore_0
    //   130: ldc_w 545
    //   133: ldc_w 1063
    //   136: aload_0
    //   137: invokestatic 129	com/tencent/android/tpush/b/a:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: iconst_0
    //   141: ireturn
    //   142: astore_0
    //   143: aconst_null
    //   144: astore_0
    //   145: aconst_null
    //   146: astore_3
    //   147: aload_0
    //   148: astore 5
    //   150: aload_3
    //   151: astore 4
    //   153: ldc_w 545
    //   156: ldc_w 1065
    //   159: invokestatic 1067	com/tencent/android/tpush/b/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: iconst_1
    //   163: istore_1
    //   164: aload_3
    //   165: ifnull +7 -> 172
    //   168: aload_3
    //   169: invokevirtual 1059	android/net/LocalServerSocket:close	()V
    //   172: aload_0
    //   173: ifnull -60 -> 113
    //   176: aload_0
    //   177: invokevirtual 1059	android/net/LocalServerSocket:close	()V
    //   180: iconst_1
    //   181: ireturn
    //   182: astore_0
    //   183: ldc_w 545
    //   186: ldc_w 1063
    //   189: aload_0
    //   190: invokestatic 129	com/tencent/android/tpush/b/a:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   193: iconst_1
    //   194: ireturn
    //   195: astore_3
    //   196: ldc_w 545
    //   199: ldc_w 1061
    //   202: aload_3
    //   203: invokestatic 129	com/tencent/android/tpush/b/a:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   206: goto -34 -> 172
    //   209: astore_0
    //   210: aconst_null
    //   211: astore_3
    //   212: aconst_null
    //   213: astore 5
    //   215: aload_3
    //   216: ifnull +7 -> 223
    //   219: aload_3
    //   220: invokevirtual 1059	android/net/LocalServerSocket:close	()V
    //   223: aload 5
    //   225: ifnull +8 -> 233
    //   228: aload 5
    //   230: invokevirtual 1059	android/net/LocalServerSocket:close	()V
    //   233: aload_0
    //   234: athrow
    //   235: astore_3
    //   236: ldc_w 545
    //   239: ldc_w 1061
    //   242: aload_3
    //   243: invokestatic 129	com/tencent/android/tpush/b/a:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   246: goto -23 -> 223
    //   249: astore_3
    //   250: ldc_w 545
    //   253: ldc_w 1063
    //   256: aload_3
    //   257: invokestatic 129	com/tencent/android/tpush/b/a:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   260: goto -27 -> 233
    //   263: astore_0
    //   264: aconst_null
    //   265: astore 5
    //   267: goto -52 -> 215
    //   270: astore_0
    //   271: aload 4
    //   273: astore_3
    //   274: goto -59 -> 215
    //   277: astore_0
    //   278: aconst_null
    //   279: astore_0
    //   280: goto -133 -> 147
    //   283: astore 4
    //   285: goto -138 -> 147
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	288	0	paramContext	Context
    //   102	62	1	bool1	boolean
    //   92	20	2	bool2	boolean
    //   14	84	3	localLocalServerSocket	android.net.LocalServerSocket
    //   115	8	3	localThrowable1	Throwable
    //   146	23	3	localObject1	Object
    //   195	8	3	localThrowable2	Throwable
    //   211	9	3	localObject2	Object
    //   235	8	3	localThrowable3	Throwable
    //   249	8	3	localThrowable4	Throwable
    //   273	1	3	localObject3	Object
    //   3	269	4	localObject4	Object
    //   283	1	4	localThrowable5	Throwable
    //   59	207	5	localContext	Context
    //   46	35	6	str	String
    // Exception table:
    //   from	to	target	type
    //   97	101	115	java/lang/Throwable
    //   107	111	129	java/lang/Throwable
    //   0	15	142	java/lang/Throwable
    //   176	180	182	java/lang/Throwable
    //   168	172	195	java/lang/Throwable
    //   0	15	209	finally
    //   219	223	235	java/lang/Throwable
    //   228	233	249	java/lang/Throwable
    //   15	58	263	finally
    //   64	91	270	finally
    //   153	162	270	finally
    //   15	58	277	java/lang/Throwable
    //   64	91	283	java/lang/Throwable
  }
  
  private static void w(Context paramContext)
  {
    Object localObject = e(paramContext);
    if (i(paramContext) >= 2) {}
    int j;
    label31:
    int k;
    do
    {
      return;
      if (localObject == null) {
        break label360;
      }
      Iterator localIterator = ((List)localObject).iterator();
      j = 0;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (ResolveInfo)localIterator.next();
      k = j + 1;
      if (!"oppo".equals(l.b())) {
        break label354;
      }
    } while (k > 2);
    label354:
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
      if (d(paramContext, str2)) {
        break label31;
      }
      if (i(paramContext) >= 2) {
        break;
      }
      try
      {
        String str1 = "am startservice -n " + str2 + "/" + XGPushServiceV4.class.getName();
        localObject = Runtime.getRuntime().exec(str1);
        com.tencent.android.tpush.b.a.c("XGService", "pull up pullUpXGServiceByRemoteService " + str2);
        j = ((Process)localObject).waitFor();
        int i = j;
        if (j != 0)
        {
          str1 = "am startservice --user 0 -n " + str2 + "/" + XGPushServiceV4.class.getName();
          localObject = Runtime.getRuntime().exec(str1);
          i = ((Process)localObject).waitFor();
        }
        j = k;
        if (i == 0) {
          break label31;
        }
        com.tencent.android.tpush.b.a.i("XGService", "pull up error exec cmd:" + str1 + ",exitValud:" + ((Process)localObject).exitValue());
        j = k;
      }
      catch (Throwable localThrowable)
      {
        com.tencent.android.tpush.b.a.i("XGService", "pull up error exec cmd:" + localThrowable);
        j = k;
      }
      break label31;
      break;
    }
    return;
    label360:
    com.tencent.android.tpush.b.a.f("XGService", "pullupXGServices  with null content");
  }
  
  private static void x(Context paramContext)
  {
    if (i(paramContext) >= 2) {
      return;
    }
    com.tencent.android.tpush.common.c.a().a(new Runnable()
    {
      public void run()
      {
        if (i.i(this.a) >= 2) {}
        int i;
        label33:
        Object localObject2;
        int j;
        do
        {
          return;
          Object localObject1 = i.e(this.a);
          if (localObject1 == null) {
            break label280;
          }
          localObject1 = ((List)localObject1).iterator();
          i = 0;
          if (!((Iterator)localObject1).hasNext()) {
            break;
          }
          localObject2 = (ResolveInfo)((Iterator)localObject1).next();
          j = i + 1;
          if (!"oppo".equals(l.b())) {
            break label274;
          }
        } while (j > 2);
        label274:
        while (j <= 4)
        {
          localObject2 = ((ResolveInfo)localObject2).activityInfo.applicationInfo.packageName;
          i = j;
          if (i.b((String)localObject2)) {
            break label33;
          }
          i = j;
          if (this.a.getPackageName().equals(localObject2)) {
            break label33;
          }
          i = j;
          if (i.d(this.a, (String)localObject2)) {
            break label33;
          }
          i = j;
          if (!i.c(this.a, (String)localObject2)) {
            break label33;
          }
          try
          {
            if (i.i(this.a) >= 2) {
              break;
            }
            com.tencent.android.tpush.b.a.c("XGService", "pull up by provider " + (String)localObject2);
            localObject2 = Uri.parse("content://" + (String)localObject2 + ".AUTH_XGPUSH" + "/" + TypeStr.pullupxg.getStr());
            com.tencent.android.tpush.a.b.a(this.a, (Uri)localObject2);
            Thread.sleep(200L);
            i = j;
          }
          catch (Throwable localThrowable)
          {
            com.tencent.android.tpush.b.a.i("XGService", "pull up by provider error" + localThrowable);
            i = j;
          }
          break label33;
          break;
        }
        return;
        label280:
        com.tencent.android.tpush.b.a.f("XGService", "pullupXGServices  with null content");
      }
    }, 2000L);
  }
}
