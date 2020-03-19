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
import com.tencent.android.tpush.data.RegisterEntity;
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
    if (j >= paramInt) {
      return paramString;
    }
    int i = 0;
    while (i < paramInt - j)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append(" ");
      paramString = localStringBuilder.toString();
      i += 1;
    }
    return paramString;
  }
  
  public static void a(Context paramContext, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return;
    }
    String str = paramJSONObject.optString("name", "");
    if (b(str)) {
      return;
    }
    if (a(paramContext, str)) {
      return;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("pullUpOtherServiceByProviderAndActivityJSONOject ");
    ((StringBuilder)localObject).append(str);
    com.tencent.android.tpush.a.a.g("Util", ((StringBuilder)localObject).toString());
    localObject = paramJSONObject.optString("intent", "");
    if (!b((String)localObject)) {}
    try
    {
      localObject = new Intent((String)localObject);
      ((Intent)localObject).setFlags(268435456);
      paramContext.startActivity((Intent)localObject);
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    paramJSONObject = paramJSONObject.optString("url", "");
    if (!b(paramJSONObject)) {
      g.a().a(new n(paramContext, str, paramJSONObject), 2000L);
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
  
  private static boolean a(Context paramContext, String paramString, long paramLong, boolean paramBoolean)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    for (;;)
    {
      try
      {
        localPackageManager.getPackageInfo(paramString, 0);
        return true;
      }
      catch (Exception localException)
      {
        if (e(paramContext, paramString)) {
          return true;
        }
        if (f(paramContext, paramString)) {
          return true;
        }
        if (!paramBoolean) {
          break label115;
        }
      }
      do
      {
        try
        {
          paramContext = CacheManager.getRegisterInfo(paramContext);
          if (paramContext == null) {
            break label115;
          }
          paramContext = paramContext.iterator();
        }
        catch (Exception paramContext)
        {
          for (;;)
          {
            long l;
          }
        }
        if (!paramContext.hasNext()) {
          break label115;
        }
        paramString = (RegisterEntity)paramContext.next();
        l = paramString.accessId;
      } while (l != paramLong);
      label115:
      try
      {
        localPackageManager.getPackageInfo(paramString.packageName, 0);
      }
      catch (Exception paramString) {}
    }
    com.tencent.android.tpush.a.a.c("XGService", "isAppInstalled", localException);
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
        if (localJSONArray.length() == 0) {
          return true;
        }
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
          if ((k * 60 + m <= j) && (j <= n * 60 + i1)) {
            return true;
          }
        }
        else
        {
          paramIntent = new StringBuilder();
          paramIntent.append(" discurd the msg due to time not accepted! acceptTime = ");
          paramIntent.append((String)localObject1);
          paramIntent.append(" , curTime= ");
          paramIntent.append(j);
          com.tencent.android.tpush.a.a.i("Utils", paramIntent.toString());
          return false;
        }
      }
      catch (Throwable paramIntent)
      {
        com.tencent.android.tpush.a.a.c("XGService", "checkAcceptTime", paramIntent);
        return true;
      }
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
    for (;;)
    {
      int i;
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
          break label350;
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
      if (i < ((JSONArray)localObject2).length())
      {
        localObject3 = new JSONObject(new JSONObject(((JSONArray)localObject2).getString(i)).getString("start"));
        m = Integer.valueOf(((JSONObject)localObject3).getString("hour")).intValue() * 60 + Integer.valueOf(((JSONObject)localObject3).getString("min")).intValue();
        if (m >= k)
        {
          j = k;
          if (k != 0) {
            break label360;
          }
        }
      }
      else
      {
        l2 = l1 + k * 60 * 1000L;
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
        paramIntent = new StringBuilder();
        paramIntent.append("get acceptTime = ");
        paramIntent.append((String)localObject1);
        paramIntent.append(" , acceptBeginTime= ");
        paramIntent.append(l1);
        com.tencent.android.tpush.a.a.e("Utils", paramIntent.toString());
        return l1;
        label350:
        i = 0;
        k = 0;
        continue;
      }
      int j = m;
      label360:
      i += 1;
      int k = j;
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
              paramContext = new StringBuilder();
              paramContext.append("isSurvive srvPkg :");
              paramContext.append((String)localObject2);
              com.tencent.android.tpush.a.a.c("XGService", paramContext.toString());
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
    if (paramString != null)
    {
      if (paramString.equals("0")) {
        return 0L;
      }
      paramString = paramString.trim();
      long[] arrayOfLong = new long[4];
      int i = paramString.indexOf(".");
      int j = i + 1;
      int k = paramString.indexOf(".", j);
      int m = k + 1;
      int n = paramString.indexOf(".", m);
      try
      {
        arrayOfLong[3] = Long.parseLong(paramString.substring(0, i));
        arrayOfLong[2] = Long.parseLong(paramString.substring(j, k));
        arrayOfLong[1] = Long.parseLong(paramString.substring(m, n));
        arrayOfLong[0] = Long.parseLong(paramString.substring(n + 1));
      }
      catch (Throwable localThrowable)
      {
        i = 0;
        while (i < arrayOfLong.length)
        {
          arrayOfLong[i] = 0L;
          i += 1;
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("service Util@@parseIpAddress(");
        localStringBuilder.append(paramString);
        localStringBuilder.append(")");
        com.tencent.android.tpush.a.a.c("TPush", localStringBuilder.toString(), localThrowable);
      }
      return (arrayOfLong[0] << 24) + (arrayOfLong[1] << 16) + (arrayOfLong[2] << 8) + arrayOfLong[3];
    }
    return 0L;
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
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
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
    if (paramContext != null) {
      try
      {
        HashMap localHashMap = new HashMap();
        paramContext = paramContext.getPackageManager();
        Object localObject = paramContext.queryIntentActivities(new Intent("android.intent.action"), 32);
        ((List)localObject).addAll(paramContext.queryIntentActivities(new Intent(""), 32));
        ((List)localObject).addAll(paramContext.queryBroadcastReceivers(new Intent("com.tencent.android.tpush.action.SDK"), 512));
        paramContext = ((List)localObject).iterator();
        while (paramContext.hasNext())
        {
          localObject = (ResolveInfo)paramContext.next();
          localHashMap.put(((ResolveInfo)localObject).activityInfo.applicationInfo.packageName, localObject);
        }
        paramContext = new ArrayList(localHashMap.values());
        return paramContext;
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "getLocalPushAppsInfo", paramContext);
      }
    }
    return null;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (!TextUtils.isEmpty(paramString))) {
      return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
    }
    throw new IllegalArgumentException("empty params");
  }
  
  public static String e()
  {
    return Build.MODEL;
  }
  
  public static void e(Context paramContext)
  {
    for (;;)
    {
      int i;
      try
      {
        paramContext = com.tencent.android.tpush.service.a.a.a(paramContext).J;
        if (paramContext != null)
        {
          Iterator localIterator = paramContext.entrySet().iterator();
          if (localIterator.hasNext())
          {
            Object localObject2 = (Map.Entry)localIterator.next();
            try
            {
              paramContext = new StringBuilder();
              paramContext.append("am startservice -n ");
              paramContext.append((String)((Map.Entry)localObject2).getKey());
              paramContext.append("/");
              paramContext.append((String)((Map.Entry)localObject2).getValue());
              paramContext = paramContext.toString();
              localObject1 = Runtime.getRuntime().exec(paramContext);
              int j = ((Process)localObject1).waitFor();
              i = j;
              if (j == 0) {
                break label295;
              }
              paramContext = new StringBuilder();
              paramContext.append("am startservice --user 0 -n ");
              paramContext.append((String)((Map.Entry)localObject2).getKey());
              paramContext.append("/");
              paramContext.append((String)((Map.Entry)localObject2).getValue());
              paramContext = paramContext.toString();
              localObject1 = Runtime.getRuntime().exec(paramContext);
              i = ((Process)localObject1).waitFor();
            }
            catch (Throwable paramContext)
            {
              Object localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("pullUpServerConfigPkgs error exec cmd:");
              ((StringBuilder)localObject1).append(paramContext);
              com.tencent.android.tpush.a.a.i("XGService", ((StringBuilder)localObject1).toString());
            }
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("pullUpServerConfigPkgs error exec cmd:");
            ((StringBuilder)localObject2).append(paramContext);
            ((StringBuilder)localObject2).append(",exitValud:");
            ((StringBuilder)localObject2).append(((Process)localObject1).exitValue());
            com.tencent.android.tpush.a.a.i("XGService", ((StringBuilder)localObject2).toString());
            continue;
            continue;
          }
        }
        return;
      }
      catch (Throwable paramContext)
      {
        return;
      }
      label295:
      if (i != 0) {}
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
    if (b(paramString)) {
      return false;
    }
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
    return paramString.contains("clean");
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
      try
      {
        localObject = com.tencent.android.tpush.service.a.a.a(paramContext).I;
        if (localObject != null)
        {
          if (((JSONArray)localObject).length() != 0) {
            break label149;
          }
          continue;
          if (i >= ((JSONArray)localObject).length()) {
            continue;
          }
          a(paramContext, ((JSONArray)localObject).optJSONObject(i));
          i += 1;
          continue;
        }
        com.tencent.android.tpush.a.a.g("Util", "pullupOtherServiceByProviderAndActivity no running");
        if (com.tencent.android.tpush.service.a.a.a(paramContext).H != 0)
        {
          localObject = new JSONObject();
          ((JSONObject)localObject).put("name", "com.tencent.qgame:wns");
          ((JSONObject)localObject).put("intent", "com.tencent.qgame.XINGEPUSH");
          ((JSONObject)localObject).put("url", "com.tencent.qgame.keepalive/keepalive");
          com.tencent.android.tpush.a.a.g("Util", "pullupOtherServiceByProviderAndActivity  qgame");
          a(paramContext, (JSONObject)localObject);
          return;
        }
      }
      catch (Throwable paramContext)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("pullupOtherServiceByProviderAndActivity");
        ((StringBuilder)localObject).append(paramContext);
        com.tencent.android.tpush.a.a.i("XGService", ((StringBuilder)localObject).toString());
      }
      return;
      label149:
      int i = 0;
    }
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    if (t.c(paramString)) {
      return false;
    }
    if (paramContext != null) {
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(".PUSH_ACTION");
        paramContext = c(paramContext, localStringBuilder.toString());
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
    return false;
  }
  
  public static void g(Context paramContext)
  {
    com.tencent.android.tpush.a.b(paramContext);
    if (a(paramContext.getPackageName()))
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramContext.getPackageName());
      localStringBuilder1.append(" ingore.");
      com.tencent.android.tpush.a.a.g("Util", localStringBuilder1.toString());
      return;
    }
    try
    {
      if (h(paramContext) >= 2)
      {
        com.tencent.android.tpush.a.a.g("Util", "more than two XGV3 service running");
      }
      else
      {
        z(paramContext);
        A(paramContext);
      }
    }
    catch (Throwable localThrowable)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("pullUpXGServiceByRemoteService");
      localStringBuilder2.append(localThrowable);
      com.tencent.android.tpush.a.a.i("XGService", localStringBuilder2.toString());
    }
    f(paramContext);
    e(paramContext);
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
          for (;;)
          {
            j = i;
            k = i;
            if (!((Iterator)localObject).hasNext()) {
              break;
            }
            j = i;
            boolean bool = paramContext.equals(((ActivityManager.RunningServiceInfo)((Iterator)localObject).next()).service.getClassName());
            if (bool) {
              i += 1;
            }
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
        if (paramContext.isAvailable())
        {
          if (!paramContext.isConnected()) {
            return -1;
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
            case 13: 
              return 4;
            case 3: 
            case 5: 
            case 6: 
            case 8: 
            case 9: 
            case 10: 
            case 15: 
              return 3;
            }
            return 2;
          }
          return 0;
        }
        return -1;
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "getNetworkType", paramContext);
      }
    }
    return -1;
  }
  
  public static byte l(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimOperator();
        if (paramContext != null) {
          if ((!paramContext.equals("46000")) && (!paramContext.equals("46002")) && (!paramContext.equals("46007")) && (!paramContext.equals("46020")))
          {
            if ((!paramContext.equals("46001")) && (!paramContext.equals("46006")))
            {
              if (!paramContext.equals("46003"))
              {
                boolean bool = paramContext.equals("46005");
                if (!bool) {}
              }
              else
              {
                return 1;
              }
            }
            else {
              return 2;
            }
          }
          else {
            return 3;
          }
        }
      }
      catch (Exception paramContext)
      {
        com.tencent.android.tpush.a.a.c("XGService", "getIsp", paramContext);
      }
    }
    return 0;
  }
  
  public static String m(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        Object localObject = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localObject != null) && (((NetworkInfo)localObject).getType() == 1)) {
          return n(paramContext);
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("");
        ((StringBuilder)localObject).append(l(paramContext));
        ((StringBuilder)localObject).append(k(paramContext));
        paramContext = ((StringBuilder)localObject).toString();
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
    paramContext = o(paramContext);
    if ((paramContext != null) && (!paramContext.equals("0"))) {
      return paramContext;
    }
    return f();
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
    if (paramContext == null) {
      return null;
    }
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if (paramContext != null)
    {
      if (paramContext.activityInfo == null) {
        return null;
      }
      if (paramContext.activityInfo.packageName.equals("android")) {
        return null;
      }
      return paramContext.activityInfo.packageName;
    }
    return null;
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
    localJSONArray = new JSONArray();
    try
    {
      Map localMap1 = s(paramContext);
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
      {
        Map localMap2 = B(paramContext);
        Object localObject1 = d(paramContext);
        paramContext = new HashMap();
        Object localObject2;
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
      return localJSONArray;
    }
    catch (Throwable paramContext)
    {
      com.tencent.android.tpush.a.a.c("TPush", "failed to get app.", paramContext);
    }
  }
  
  public static Map s(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
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
    return localHashMap;
  }
  
  public static JSONArray t(Context paramContext)
  {
    localJSONArray = new JSONArray();
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
      {
        Map localMap = B(paramContext);
        Object localObject1 = d(paramContext);
        paramContext = new HashMap();
        Object localObject2;
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
      return localJSONArray;
    }
    catch (Throwable paramContext)
    {
      com.tencent.android.tpush.a.a.c("TPush", "failed to get app.", paramContext);
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
      return true;
    }
    catch (Throwable paramContext) {}
    return false;
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
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("serviceSafeExit @ ");
        localStringBuilder.append(paramContext);
        com.tencent.android.tpush.a.a.c("XGService", localStringBuilder.toString());
        XGPushServiceV3.b().stopSelf();
      }
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  private static void z(Context paramContext)
  {
    Object localObject1 = d(paramContext);
    Iterator localIterator;
    int i;
    if (localObject1 != null)
    {
      localIterator = ((List)localObject1).iterator();
      i = 0;
    }
    for (;;)
    {
      int j;
      Object localObject3;
      if (localIterator.hasNext())
      {
        localObject1 = (ResolveInfo)localIterator.next();
        j = i + 1;
        if ("oppo".equals(t.b()))
        {
          if (j <= 2) {}
        }
        else if (j > 4) {
          return;
        }
        localObject3 = ((ResolveInfo)localObject1).activityInfo.applicationInfo.packageName;
        i = j;
        if (!b((String)localObject3))
        {
          i = j;
          if (!paramContext.getPackageName().equals(localObject3))
          {
            i = j;
            if (b(paramContext, (String)localObject3)) {}
          }
        }
      }
      else
      {
        do
        {
          try
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("am startservice -n ");
            ((StringBuilder)localObject1).append((String)localObject3);
            ((StringBuilder)localObject1).append("/");
            ((StringBuilder)localObject1).append(XGPushServiceV3.class.getName());
            localObject1 = ((StringBuilder)localObject1).toString();
            localObject2 = Runtime.getRuntime().exec((String)localObject1);
            int k = ((Process)localObject2).waitFor();
            i = k;
            if (k == 0) {
              continue;
            }
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("am startservice --user 0 -n ");
            ((StringBuilder)localObject1).append((String)localObject3);
            ((StringBuilder)localObject1).append("/");
            ((StringBuilder)localObject1).append(XGPushServiceV3.class.getName());
            localObject1 = ((StringBuilder)localObject1).toString();
            localObject2 = Runtime.getRuntime().exec((String)localObject1);
            i = ((Process)localObject2).waitFor();
          }
          catch (Throwable localThrowable)
          {
            Object localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("pull up error exec cmd:");
            ((StringBuilder)localObject2).append(localThrowable);
            com.tencent.android.tpush.a.a.i("XGService", ((StringBuilder)localObject2).toString());
            i = j;
          }
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("pull up error exec cmd:");
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append(",exitValud:");
          ((StringBuilder)localObject3).append(((Process)localObject2).exitValue());
          com.tencent.android.tpush.a.a.i("XGService", ((StringBuilder)localObject3).toString());
          i = j;
          break;
          break;
          com.tencent.android.tpush.a.a.f("XGService", "pullupXGServices  with null content");
          return;
        } while (i != 0);
        i = j;
      }
    }
  }
}
