package com.tencent.stat.a;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.stat.n;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b
{
  private static String A;
  private static long B;
  private static long C = 0L;
  public static String a;
  private static String b = null;
  private static String c = null;
  private static String d = null;
  private static String e = null;
  private static Random f = null;
  private static DisplayMetrics g = null;
  private static String h = null;
  private static String i = "";
  private static String j = "";
  private static String k = "";
  private static String l = "";
  private static int m = -1;
  private static d n = null;
  private static String o = null;
  private static String p = null;
  private static volatile int q = -1;
  private static String r = null;
  private static String s = null;
  private static long t = -1L;
  private static String u = "";
  private static b v = null;
  private static String w = "__MTA_FIRST_ACTIVATE__";
  private static int x = -1;
  private static long y = -1L;
  private static int z = 0;
  
  static
  {
    A = "";
    B = -1L;
    a = "next_al_report_time";
  }
  
  public static void A(Context paramContext)
  {
    y = System.currentTimeMillis();
    e.b(paramContext, "mta.qq.com.checktime", y);
  }
  
  public static int B(Context paramContext)
  {
    return e.a(paramContext, "mta.qq.com.difftime", 0);
  }
  
  public static Map<String, Integer> C(Context paramContext)
  {
    localHashMap = new HashMap();
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext()) {
        localHashMap.put(((ActivityManager.RunningAppProcessInfo)paramContext.next()).processName, Integer.valueOf(1));
      }
      return localHashMap;
    }
    catch (Throwable paramContext)
    {
      n.b(paramContext);
    }
  }
  
  public static Map<String, Integer> D(Context paramContext)
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
    catch (Throwable paramContext)
    {
      n.b(paramContext);
    }
  }
  
  public static int E(Context paramContext)
  {
    try
    {
      String str = k(paramContext) + ".run.cnt";
      int i1 = e.a(paramContext, str, 1);
      e.b(paramContext, str, i1 + 1);
      return i1;
    }
    catch (Throwable paramContext) {}
    return 0;
  }
  
  public static JSONArray F(Context paramContext)
  {
    localJSONArray = new JSONArray();
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
      Map localMap = C(paramContext);
      paramContext = D(paramContext);
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        try
        {
          JSONObject localJSONObject = new JSONObject();
          String str = localPackageInfo.packageName;
          localJSONObject.put("pn", str);
          localJSONObject.put("av", localPackageInfo.versionName);
          localJSONObject.put("fit", localPackageInfo.firstInstallTime / 1000L);
          localJSONObject.put("lut", localPackageInfo.lastUpdateTime / 1000L);
          localJSONObject.put("fg", localPackageInfo.applicationInfo.flags);
          if (localMap.containsKey(str)) {
            localJSONObject.put("rn", 1);
          }
          if (paramContext.containsKey(str)) {
            localJSONObject.put("rt", 1);
          }
          localJSONArray.put(localJSONObject);
        }
        catch (JSONException localJSONException)
        {
          n.b(localJSONException);
        }
      }
      return localJSONArray;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String G(Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return null;
      try
      {
        Intent localIntent = new Intent("android.intent.action.MAIN");
        localIntent.addCategory("android.intent.category.HOME");
        paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
        if ((paramContext.activityInfo != null) && (!paramContext.activityInfo.packageName.equals("android")))
        {
          paramContext = paramContext.activityInfo.packageName;
          return paramContext;
        }
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return null;
  }
  
  public static boolean H(Context paramContext)
  {
    if (!com.tencent.stat.d.A()) {
      return false;
    }
    if (B == -1L)
    {
      C = Long.valueOf(com.tencent.stat.d.a("autoAL", String.valueOf(86400L))).longValue() * 1000L;
      if (C <= 10800000L) {
        break label104;
      }
    }
    label104:
    for (B = e.a(paramContext, a, 0L);; B = Long.MAX_VALUE)
    {
      n.h("next_al_report_time:" + B);
      if (B >= System.currentTimeMillis()) {
        break;
      }
      return true;
    }
  }
  
  public static void I(Context paramContext)
  {
    B = System.currentTimeMillis() + C;
    e.b(paramContext, a, B);
  }
  
  public static String J(Context paramContext)
  {
    try
    {
      paramContext = (String)com.tencent.mid.api.c.class.getMethod("getNewMid", new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      n.d("MidService.getNewMid method notfound");
    }
    return null;
  }
  
  private static long K(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    paramContext.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem;
  }
  
  public static int a()
  {
    return g().nextInt(Integer.MAX_VALUE);
  }
  
  public static int a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {
      z = B(paramContext);
    }
    return z;
  }
  
  public static int a(Object... paramVarArgs)
  {
    int i3 = 0;
    int i1 = 0;
    if (paramVarArgs != null)
    {
      int i4 = paramVarArgs.length;
      int i2 = 0;
      for (;;)
      {
        i3 = i1;
        if (i2 >= i4) {
          break;
        }
        Object localObject = paramVarArgs[i2];
        i3 = i1;
        if (localObject != null) {
          i3 = i1 + localObject.toString().length();
        }
        i2 += 1;
        i1 = i3;
      }
    }
    return i3;
  }
  
  public static Long a(String paramString1, String paramString2, int paramInt1, int paramInt2, Long paramLong)
  {
    if ((paramString1 == null) || (paramString2 == null)) {}
    do
    {
      return paramLong;
      String str;
      if (!paramString2.equalsIgnoreCase("."))
      {
        str = paramString2;
        if (!paramString2.equalsIgnoreCase("|")) {}
      }
      else
      {
        str = "\\" + paramString2;
      }
      paramString2 = paramString1.split(str);
    } while (paramString2.length != paramInt2);
    try
    {
      paramString1 = Long.valueOf(0L);
      paramInt2 = 0;
      while (paramInt2 < paramString2.length)
      {
        long l1 = paramInt1;
        long l2 = paramString1.longValue();
        long l3 = Long.valueOf(paramString2[paramInt2]).longValue();
        paramInt2 += 1;
        paramString1 = Long.valueOf(l1 * (l2 + l3));
      }
      return paramString1;
    }
    catch (NumberFormatException paramString1) {}
    return paramLong;
  }
  
  public static String a(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.roll(6, paramInt);
    return new SimpleDateFormat("yyyyMMdd").format(localCalendar.getTime());
  }
  
  public static String a(long paramLong)
  {
    return new SimpleDateFormat("yyyyMMdd").format(new Date(paramLong));
  }
  
  public static String a(Context paramContext, String paramString)
  {
    String str = paramString;
    if (com.tencent.stat.d.t() == true)
    {
      if (p == null) {
        p = s(paramContext);
      }
      str = paramString;
      if (p != null) {
        str = paramString + "_" + p;
      }
    }
    return str;
  }
  
  public static String a(String paramString)
  {
    if (paramString == null) {
      return "0";
    }
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i1 = 0;
      while (i1 < paramString.length)
      {
        int i2 = paramString[i1] & 0xFF;
        if (i2 < 16) {
          ((StringBuffer)localObject).append("0");
        }
        ((StringBuffer)localObject).append(Integer.toHexString(i2));
        i1 += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (Throwable paramString) {}
    return "0";
  }
  
  public static HttpHost a(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    do
    {
      try
      {
        if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0) {
          return null;
        }
        paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (paramContext == null) {
          return null;
        }
        if ((paramContext.getTypeName() != null) && (paramContext.getTypeName().equalsIgnoreCase("WIFI"))) {
          return null;
        }
        paramContext = paramContext.getExtraInfo();
        if (paramContext == null) {
          return null;
        }
        if ((paramContext.equals("cmwap")) || (paramContext.equals("3gwap")) || (paramContext.equals("uniwap")))
        {
          paramContext = new HttpHost("10.0.0.172", 80);
          return paramContext;
        }
      }
      catch (Throwable paramContext)
      {
        n.b(paramContext);
        return null;
      }
      if (paramContext.equals("ctwap")) {
        return new HttpHost("10.0.0.200", 80);
      }
      paramContext = Proxy.getDefaultHost();
    } while ((paramContext == null) || (paramContext.trim().length() <= 0));
    paramContext = new HttpHost(paramContext, Proxy.getDefaultPort());
    return paramContext;
  }
  
  public static JSONArray a(Throwable paramThrowable)
  {
    JSONArray localJSONArray = new JSONArray();
    if (paramThrowable != null)
    {
      localJSONArray.put(paramThrowable.toString());
      a(localJSONArray, paramThrowable.getStackTrace());
    }
    return localJSONArray;
  }
  
  public static JSONArray a(JSONArray paramJSONArray, StackTraceElement[] paramArrayOfStackTraceElement)
  {
    if (paramArrayOfStackTraceElement != null)
    {
      int i2 = paramArrayOfStackTraceElement.length;
      int i1 = 0;
      while (i1 < i2)
      {
        paramJSONArray.put(paramArrayOfStackTraceElement[i1].toString());
        i1 += 1;
      }
    }
    return paramJSONArray;
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    z = paramInt;
    e.b(paramContext, "mta.qq.com.difftime", paramInt);
  }
  
  public static void a(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
      paramCloseable.printStackTrace();
    }
  }
  
  public static void a(Process paramProcess)
  {
    if (paramProcess != null) {}
    try
    {
      paramProcess.exitValue();
      return;
    }
    catch (IllegalThreadStateException paramProcess) {}catch (Throwable paramProcess) {}
  }
  
  public static boolean a(n paramN)
  {
    if (paramN == null) {
      return false;
    }
    return c(paramN.c());
  }
  
  public static byte[] a(byte[] paramArrayOfByte)
    throws IOException
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    GZIPInputStream localGZIPInputStream = new GZIPInputStream(localByteArrayInputStream);
    byte[] arrayOfByte = new byte['က'];
    paramArrayOfByte = new ByteArrayOutputStream(paramArrayOfByte.length * 2);
    for (;;)
    {
      int i1 = localGZIPInputStream.read(arrayOfByte);
      if (i1 == -1) {
        break;
      }
      paramArrayOfByte.write(arrayOfByte, 0, i1);
    }
    arrayOfByte = paramArrayOfByte.toByteArray();
    localByteArrayInputStream.close();
    localGZIPInputStream.close();
    paramArrayOfByte.close();
    return arrayOfByte;
  }
  
  public static long b(String paramString)
  {
    return a(paramString, ".", 100, 3, Long.valueOf(0L)).longValue();
  }
  
  public static d b()
  {
    try
    {
      if (n == null)
      {
        n = new d("MtaSDK");
        n.a(false);
      }
      d localD = n;
      return localD;
    }
    finally {}
  }
  
  public static String b(long paramLong)
  {
    return new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date(paramLong));
  }
  
  /* Error */
  public static String b(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 52	com/tencent/stat/a/b:b	Ljava/lang/String;
    //   6: ifnull +24 -> 30
    //   9: getstatic 52	com/tencent/stat/a/b:b	Ljava/lang/String;
    //   12: invokevirtual 579	java/lang/String:trim	()Ljava/lang/String;
    //   15: invokevirtual 439	java/lang/String:length	()I
    //   18: ifeq +12 -> 30
    //   21: getstatic 52	com/tencent/stat/a/b:b	Ljava/lang/String;
    //   24: astore_0
    //   25: ldc 2
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: aload_0
    //   31: invokestatic 665	com/tencent/stat/a/f:a	(Landroid/content/Context;)Ljava/lang/String;
    //   34: putstatic 52	com/tencent/stat/a/b:b	Ljava/lang/String;
    //   37: getstatic 52	com/tencent/stat/a/b:b	Ljava/lang/String;
    //   40: ifnull +15 -> 55
    //   43: getstatic 52	com/tencent/stat/a/b:b	Ljava/lang/String;
    //   46: invokevirtual 579	java/lang/String:trim	()Ljava/lang/String;
    //   49: invokevirtual 439	java/lang/String:length	()I
    //   52: ifne +18 -> 70
    //   55: invokestatic 424	com/tencent/stat/a/b:g	()Ljava/util/Random;
    //   58: ldc_w 425
    //   61: invokevirtual 431	java/util/Random:nextInt	(I)I
    //   64: invokestatic 667	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   67: putstatic 52	com/tencent/stat/a/b:b	Ljava/lang/String;
    //   70: getstatic 52	com/tencent/stat/a/b:b	Ljava/lang/String;
    //   73: astore_0
    //   74: goto -49 -> 25
    //   77: astore_0
    //   78: ldc 2
    //   80: monitorexit
    //   81: aload_0
    //   82: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	25	77	finally
    //   30	55	77	finally
    //   55	70	77	finally
    //   70	74	77	finally
  }
  
  public static boolean b(Object... paramVarArgs)
  {
    return a(paramVarArgs) > 61440;
  }
  
  public static long c()
  {
    try
    {
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(11, 0);
      localCalendar.set(12, 0);
      localCalendar.set(13, 0);
      localCalendar.set(14, 0);
      long l1 = localCalendar.getTimeInMillis();
      return l1 + 86400000L;
    }
    catch (Throwable localThrowable)
    {
      n.b(localThrowable);
    }
    return System.currentTimeMillis() + 86400000L;
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      if ((d == null) || (d.trim().length() == 0)) {
        d = f.b(paramContext);
      }
      paramContext = d;
      return paramContext;
    }
    finally {}
  }
  
  public static boolean c(String paramString)
  {
    return (paramString != null) && (paramString.trim().length() != 0);
  }
  
  public static DisplayMetrics d(Context paramContext)
  {
    if (g == null)
    {
      g = new DisplayMetrics();
      ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(g);
    }
    return g;
  }
  
  public static String d()
  {
    if (c(s)) {
      return s;
    }
    long l1 = e() / 1000000L;
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l2 = localStatFs.getBlockSize();
    l2 = localStatFs.getAvailableBlocks() * l2 / 1000000L;
    s = String.valueOf(l2) + "/" + String.valueOf(l1);
    return s;
  }
  
  public static long e()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l1 = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l1;
  }
  
  public static boolean e(Context paramContext)
  {
    try
    {
      if (!f.a(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
        break label71;
      }
      paramContext = (ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity");
      if (paramContext == null) {
        break label100;
      }
      paramContext = paramContext.getAllNetworkInfo();
      if (paramContext == null) {
        break label100;
      }
      i1 = 0;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        int i1;
        label71:
        n.b(paramContext);
        continue;
        i1 += 1;
      }
    }
    if (i1 < paramContext.length) {
      if ((paramContext[i1].getTypeName().equalsIgnoreCase("WIFI")) && (paramContext[i1].isConnected()))
      {
        return true;
        n.c("can not get the permission of android.permission.ACCESS_WIFI_STATE");
        return false;
      }
    }
    label100:
    return false;
  }
  
  public static String f(Context paramContext)
  {
    if (c != null) {
      return c;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString("TA_APPKEY");
        if (paramContext == null) {
          break label62;
        }
        c = paramContext;
        return paramContext;
      }
    }
    catch (Throwable paramContext)
    {
      n.b("Could not read APPKEY meta-data from AndroidManifest.xml");
    }
    for (;;)
    {
      return null;
      label62:
      n.b("Could not read APPKEY meta-data from AndroidManifest.xml");
    }
  }
  
  public static String g(Context paramContext)
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
        n.d("Could not read InstallChannel meta-data from AndroidManifest.xml");
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        n.d("Could not read InstallChannel meta-data from AndroidManifest.xml");
      }
    }
    return null;
  }
  
  private static Random g()
  {
    try
    {
      if (f == null) {
        f = new Random();
      }
      Random localRandom = f;
      return localRandom;
    }
    finally {}
  }
  
  /* Error */
  private static long h()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: getstatic 92	com/tencent/stat/a/b:t	J
    //   6: lconst_0
    //   7: lcmp
    //   8: ifle +7 -> 15
    //   11: getstatic 92	com/tencent/stat/a/b:t	J
    //   14: lreturn
    //   15: lconst_1
    //   16: lstore_3
    //   17: new 779	java/io/FileReader
    //   20: dup
    //   21: ldc_w 781
    //   24: invokespecial 782	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   27: astore 9
    //   29: new 784	java/io/BufferedReader
    //   32: dup
    //   33: aload 9
    //   35: sipush 8192
    //   38: invokespecial 787	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   41: astore 7
    //   43: aload 7
    //   45: invokevirtual 790	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   48: astore 8
    //   50: lload_3
    //   51: lstore_1
    //   52: aload 8
    //   54: ifnull +27 -> 81
    //   57: aload 8
    //   59: ldc_w 792
    //   62: invokevirtual 456	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   65: iconst_1
    //   66: aaload
    //   67: invokestatic 795	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   70: invokevirtual 798	java/lang/Integer:intValue	()I
    //   73: istore_0
    //   74: iload_0
    //   75: i2l
    //   76: ldc2_w 799
    //   79: lmul
    //   80: lstore_1
    //   81: aload 9
    //   83: ifnull +8 -> 91
    //   86: aload 9
    //   88: invokevirtual 801	java/io/FileReader:close	()V
    //   91: lload_1
    //   92: lstore 5
    //   94: aload 7
    //   96: ifnull +11 -> 107
    //   99: aload 7
    //   101: invokevirtual 802	java/io/BufferedReader:close	()V
    //   104: lload_1
    //   105: lstore 5
    //   107: lload 5
    //   109: putstatic 92	com/tencent/stat/a/b:t	J
    //   112: getstatic 92	com/tencent/stat/a/b:t	J
    //   115: lreturn
    //   116: astore 7
    //   118: aload 7
    //   120: invokevirtual 803	java/lang/Exception:printStackTrace	()V
    //   123: lload_1
    //   124: lstore 5
    //   126: goto -19 -> 107
    //   129: astore 9
    //   131: aconst_null
    //   132: astore 7
    //   134: aload 9
    //   136: invokevirtual 803	java/lang/Exception:printStackTrace	()V
    //   139: aload 8
    //   141: ifnull +8 -> 149
    //   144: aload 8
    //   146: invokevirtual 801	java/io/FileReader:close	()V
    //   149: lload_3
    //   150: lstore 5
    //   152: aload 7
    //   154: ifnull -47 -> 107
    //   157: aload 7
    //   159: invokevirtual 802	java/io/BufferedReader:close	()V
    //   162: lload_3
    //   163: lstore 5
    //   165: goto -58 -> 107
    //   168: astore 7
    //   170: aload 7
    //   172: invokevirtual 803	java/lang/Exception:printStackTrace	()V
    //   175: lload_3
    //   176: lstore 5
    //   178: goto -71 -> 107
    //   181: astore 8
    //   183: aconst_null
    //   184: astore 7
    //   186: aconst_null
    //   187: astore 9
    //   189: aload 9
    //   191: ifnull +8 -> 199
    //   194: aload 9
    //   196: invokevirtual 801	java/io/FileReader:close	()V
    //   199: aload 7
    //   201: ifnull +8 -> 209
    //   204: aload 7
    //   206: invokevirtual 802	java/io/BufferedReader:close	()V
    //   209: aload 8
    //   211: athrow
    //   212: astore 7
    //   214: aload 7
    //   216: invokevirtual 803	java/lang/Exception:printStackTrace	()V
    //   219: goto -10 -> 209
    //   222: astore 8
    //   224: aconst_null
    //   225: astore 7
    //   227: goto -38 -> 189
    //   230: astore 8
    //   232: goto -43 -> 189
    //   235: astore 10
    //   237: aload 8
    //   239: astore 9
    //   241: aload 10
    //   243: astore 8
    //   245: goto -56 -> 189
    //   248: astore 10
    //   250: aconst_null
    //   251: astore 7
    //   253: aload 9
    //   255: astore 8
    //   257: aload 10
    //   259: astore 9
    //   261: goto -127 -> 134
    //   264: astore 10
    //   266: aload 9
    //   268: astore 8
    //   270: aload 10
    //   272: astore 9
    //   274: goto -140 -> 134
    // Local variable table:
    //   start	length	slot	name	signature
    //   73	2	0	i1	int
    //   51	73	1	l1	long
    //   16	160	3	l2	long
    //   92	85	5	l3	long
    //   41	59	7	localBufferedReader	java.io.BufferedReader
    //   116	3	7	localException1	Exception
    //   132	26	7	localObject1	Object
    //   168	3	7	localException2	Exception
    //   184	21	7	localObject2	Object
    //   212	3	7	localException3	Exception
    //   225	27	7	localObject3	Object
    //   1	144	8	str	String
    //   181	29	8	localObject4	Object
    //   222	1	8	localObject5	Object
    //   230	8	8	localObject6	Object
    //   243	26	8	localObject7	Object
    //   27	60	9	localFileReader	java.io.FileReader
    //   129	6	9	localException4	Exception
    //   187	86	9	localObject8	Object
    //   235	7	10	localObject9	Object
    //   248	10	10	localException5	Exception
    //   264	7	10	localException6	Exception
    // Exception table:
    //   from	to	target	type
    //   86	91	116	java/lang/Exception
    //   99	104	116	java/lang/Exception
    //   17	29	129	java/lang/Exception
    //   144	149	168	java/lang/Exception
    //   157	162	168	java/lang/Exception
    //   17	29	181	finally
    //   194	199	212	java/lang/Exception
    //   204	209	212	java/lang/Exception
    //   29	43	222	finally
    //   43	50	230	finally
    //   57	74	230	finally
    //   134	139	235	finally
    //   29	43	248	java/lang/Exception
    //   43	50	264	java/lang/Exception
    //   57	74	264	java/lang/Exception
  }
  
  public static String h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    return paramContext.getClass().getName();
  }
  
  public static String i(Context paramContext)
  {
    if (h != null) {
      return h;
    }
    for (;;)
    {
      try
      {
        if (!f.a(paramContext, "android.permission.READ_PHONE_STATE")) {
          continue;
        }
        if (m(paramContext))
        {
          paramContext = (TelephonyManager)paramContext.getSystemService("phone");
          if (paramContext != null) {
            h = paramContext.getSimOperator();
          }
        }
      }
      catch (Throwable paramContext)
      {
        n.b(paramContext);
        continue;
      }
      return h;
      n.f("Could not get permission of android.permission.READ_PHONE_STATE");
    }
  }
  
  public static String j(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (f.a(paramContext, "android.permission.READ_PHONE_STATE"))
        {
          if (!m(paramContext)) {
            break label50;
          }
          paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
          if (paramContext != null) {
            return paramContext;
          }
          return null;
        }
      }
      catch (Throwable paramContext)
      {
        n.b(paramContext);
      }
      return "";
      label50:
      paramContext = null;
    }
  }
  
  public static String k(Context paramContext)
  {
    if (c(i)) {
      return i;
    }
    try
    {
      i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      if (i == null) {
        return "";
      }
    }
    catch (Throwable paramContext)
    {
      n.b(paramContext);
    }
    return i;
  }
  
  public static String l(Context paramContext)
  {
    i1 = 0;
    if (c(k)) {
      return k;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64);
      if (paramContext == null)
      {
        n.f("packageInfo is null ");
        return "";
      }
      paramContext = paramContext.signatures;
      if ((paramContext == null) || (paramContext.length == 0))
      {
        n.f("signatures is null");
        return "";
      }
      paramContext = paramContext[0].toByteArray();
      paramContext = MessageDigest.getInstance("SHA1").digest(paramContext);
      localStringBuffer = new StringBuffer();
      i2 = paramContext.length;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        StringBuffer localStringBuffer;
        int i2;
        String str;
        paramContext.printStackTrace();
      }
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        continue;
        i1 += 1;
      }
    }
    if (i1 < i2)
    {
      str = Integer.toHexString(paramContext[i1] & 0xFF).toUpperCase(Locale.US);
      if (str.length() == 1) {
        localStringBuffer.append("0");
      }
      localStringBuffer.append(str);
      if (i1 != i2 - 1) {
        localStringBuffer.append(":");
      }
    }
    else
    {
      k = localStringBuffer.toString();
      return k;
    }
  }
  
  public static boolean m(Context paramContext)
  {
    return paramContext.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramContext.getPackageName()) == 0;
  }
  
  public static String n(Context paramContext)
  {
    Object localObject;
    try
    {
      if ((f.a(paramContext, "android.permission.INTERNET")) && (f.a(paramContext, "android.permission.ACCESS_NETWORK_STATE")))
      {
        localObject = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((localObject != null) && (((NetworkInfo)localObject).isConnected()))
        {
          paramContext = ((NetworkInfo)localObject).getTypeName();
          localObject = ((NetworkInfo)localObject).getExtraInfo();
          if (paramContext != null)
          {
            if (paramContext.equalsIgnoreCase("WIFI")) {
              return "WIFI";
            }
            if (paramContext.equalsIgnoreCase("MOBILE"))
            {
              if (localObject == null) {
                break label142;
              }
              if (((String)localObject).trim().length() > 0) {
                break label140;
              }
              break label142;
            }
            if (localObject == null) {
              return paramContext;
            }
            if (((String)localObject).trim().length() > 0) {
              break label140;
            }
            return paramContext;
          }
        }
      }
      else
      {
        n.f("can not get the permission of android.permission.ACCESS_WIFI_STATE");
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        n.b(paramContext);
      }
    }
    return "";
    label140:
    return localObject;
    label142:
    return "MOBILE";
    return paramContext;
  }
  
  public static Integer o(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        int i1 = paramContext.getNetworkType();
        return Integer.valueOf(i1);
      }
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  public static String p(Context paramContext)
  {
    if (c(l)) {
      return l;
    }
    try
    {
      l = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      if ((l == null) || (l.length() == 0)) {
        return "unknown";
      }
    }
    catch (Throwable paramContext)
    {
      n.b(paramContext);
    }
    return l;
  }
  
  public static int q(Context paramContext)
  {
    if (m != -1) {
      return m;
    }
    m = 0;
    try
    {
      if (c.a()) {
        m = 1;
      }
      return m;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        n.b(paramContext);
      }
    }
  }
  
  public static String r(Context paramContext)
  {
    Object localObject = null;
    if (c(o)) {
      paramContext = o;
    }
    for (;;)
    {
      return paramContext;
      try
      {
        if (f.a(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE"))
        {
          String str = Environment.getExternalStorageState();
          paramContext = localObject;
          if (str != null)
          {
            paramContext = localObject;
            if (str.equals("mounted"))
            {
              str = Environment.getExternalStorageDirectory().getPath();
              paramContext = localObject;
              if (str != null)
              {
                paramContext = new StatFs(str);
                long l1 = paramContext.getBlockCount() * paramContext.getBlockSize() / 1000000L;
                long l2 = paramContext.getAvailableBlocks();
                l2 = paramContext.getBlockSize() * l2 / 1000000L;
                o = String.valueOf(l2) + "/" + String.valueOf(l1);
                return o;
              }
            }
          }
        }
        else
        {
          n.c("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
          return null;
        }
      }
      catch (Throwable paramContext)
      {
        n.b(paramContext);
      }
    }
    return null;
  }
  
  public static String s(Context paramContext)
  {
    try
    {
      if (p != null) {
        return p;
      }
      int i1 = android.os.Process.myPid();
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.pid == i1) {
          p = localRunningAppProcessInfo.processName;
        }
      }
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return p;
  }
  
  public static String t(Context paramContext)
  {
    return a(paramContext, c.b);
  }
  
  public static Integer u(Context paramContext)
  {
    int i1 = 0;
    for (;;)
    {
      int i2;
      try
      {
        if (q > 0)
        {
          i2 = q;
          if (i2 % 1000 == 0) {}
          try
          {
            i2 = q;
            if (q < 2147383647) {
              break label109;
            }
            e.b(paramContext, "MTA_EVENT_INDEX", i1);
          }
          catch (Throwable paramContext)
          {
            n.d(paramContext);
            continue;
          }
          q += 1;
          i1 = q;
          return Integer.valueOf(i1);
        }
      }
      finally {}
      q = e.a(paramContext, "MTA_EVENT_INDEX", 0);
      e.b(paramContext, "MTA_EVENT_INDEX", q + 1000);
      continue;
      label109:
      i1 = i2 + 1000;
    }
  }
  
  public static String v(Context paramContext)
  {
    try
    {
      long l1 = K(paramContext) / 1000000L;
      long l2 = h() / 1000000L;
      paramContext = String.valueOf(l1) + "/" + String.valueOf(l2);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static JSONObject w(Context paramContext)
  {
    paramContext = new JSONObject();
    try
    {
      paramContext.put("n", a.a());
      String str = a.d();
      if ((str != null) && (str.length() > 0)) {
        paramContext.put("na", str);
      }
      int i1 = a.b();
      if (i1 > 0) {
        paramContext.put("fx", i1 / 1000000);
      }
      i1 = a.c();
      if (i1 > 0) {
        paramContext.put("fn", i1 / 1000000);
      }
      return paramContext;
    }
    catch (Throwable localThrowable)
    {
      Log.w("MtaSDK", "get cpu error", localThrowable);
    }
    return paramContext;
  }
  
  /* Error */
  public static int x(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 102	com/tencent/stat/a/b:x	I
    //   6: iconst_m1
    //   7: if_icmpeq +12 -> 19
    //   10: getstatic 102	com/tencent/stat/a/b:x	I
    //   13: istore_1
    //   14: ldc 2
    //   16: monitorexit
    //   17: iload_1
    //   18: ireturn
    //   19: aload_0
    //   20: invokestatic 935	com/tencent/stat/a/b:y	(Landroid/content/Context;)V
    //   23: getstatic 102	com/tencent/stat/a/b:x	I
    //   26: istore_1
    //   27: goto -13 -> 14
    //   30: astore_0
    //   31: ldc 2
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	paramContext	Context
    //   13	14	1	i1	int
    // Exception table:
    //   from	to	target	type
    //   3	14	30	finally
    //   19	27	30	finally
  }
  
  public static void y(Context paramContext)
  {
    x = e.a(paramContext, w, 1);
    if (x == 1) {
      e.b(paramContext, w, 0);
    }
  }
  
  public static boolean z(Context paramContext)
  {
    if (y < 0L) {
      y = e.a(paramContext, "mta.qq.com.checktime", 0L);
    }
    return Math.abs(System.currentTimeMillis() - y) > 86400000L;
  }
  
  static class a
  {
    static int a()
    {
      try
      {
        int i = new File("/sys/devices/system/cpu/").listFiles(new a()).length;
        return i;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return 1;
    }
    
    static int b()
    {
      j = 0;
      String str = "";
      try
      {
        InputStream localInputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" }).start().getInputStream();
        byte[] arrayOfByte = new byte[24];
        while (localInputStream.read(arrayOfByte) != -1) {
          str = str + new String(arrayOfByte);
        }
        localInputStream.close();
        str = str.trim();
        i = j;
        if (str.length() > 0) {
          i = Integer.valueOf(str).intValue();
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          b.f().b(localException);
          int i = j;
        }
      }
      return i * 1000;
    }
    
    static int c()
    {
      j = 0;
      String str = "";
      try
      {
        InputStream localInputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq" }).start().getInputStream();
        byte[] arrayOfByte = new byte[24];
        while (localInputStream.read(arrayOfByte) != -1) {
          str = str + new String(arrayOfByte);
        }
        localInputStream.close();
        str = str.trim();
        i = j;
        if (str.length() > 0) {
          i = Integer.valueOf(str).intValue();
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          b.f().b(localThrowable);
          int i = j;
        }
      }
      return i * 1000;
    }
    
    /* Error */
    static String d()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: aconst_null
      //   3: astore_2
      //   4: iconst_2
      //   5: anewarray 38	java/lang/String
      //   8: astore 4
      //   10: aload 4
      //   12: iconst_0
      //   13: ldc 34
      //   15: aastore
      //   16: aload 4
      //   18: iconst_1
      //   19: ldc 34
      //   21: aastore
      //   22: new 112	java/io/FileReader
      //   25: dup
      //   26: ldc 114
      //   28: invokespecial 115	java/io/FileReader:<init>	(Ljava/lang/String;)V
      //   31: astore_1
      //   32: new 117	java/io/BufferedReader
      //   35: dup
      //   36: aload_1
      //   37: sipush 8192
      //   40: invokespecial 120	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
      //   43: astore_2
      //   44: aload_2
      //   45: invokevirtual 123	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   48: astore_3
      //   49: aload_3
      //   50: ifnull +57 -> 107
      //   53: aload_3
      //   54: ldc 125
      //   56: invokevirtual 129	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   59: astore_3
      //   60: iconst_2
      //   61: istore_0
      //   62: iload_0
      //   63: aload_3
      //   64: arraylength
      //   65: if_icmpge +42 -> 107
      //   68: aload 4
      //   70: iconst_0
      //   71: new 63	java/lang/StringBuilder
      //   74: dup
      //   75: invokespecial 64	java/lang/StringBuilder:<init>	()V
      //   78: aload 4
      //   80: iconst_0
      //   81: aaload
      //   82: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   85: aload_3
      //   86: iload_0
      //   87: aaload
      //   88: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   91: ldc -125
      //   93: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: invokevirtual 75	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   99: aastore
      //   100: iload_0
      //   101: iconst_1
      //   102: iadd
      //   103: istore_0
      //   104: goto -42 -> 62
      //   107: aload_2
      //   108: ifnull +7 -> 115
      //   111: aload_2
      //   112: invokevirtual 132	java/io/BufferedReader:close	()V
      //   115: aload_1
      //   116: ifnull +7 -> 123
      //   119: aload_1
      //   120: invokevirtual 133	java/io/FileReader:close	()V
      //   123: aload 4
      //   125: iconst_0
      //   126: aaload
      //   127: areturn
      //   128: astore_1
      //   129: aconst_null
      //   130: astore_1
      //   131: aload_1
      //   132: ifnull +7 -> 139
      //   135: aload_1
      //   136: invokevirtual 132	java/io/BufferedReader:close	()V
      //   139: aload_2
      //   140: ifnull -17 -> 123
      //   143: aload_2
      //   144: invokevirtual 133	java/io/FileReader:close	()V
      //   147: goto -24 -> 123
      //   150: astore_1
      //   151: goto -28 -> 123
      //   154: astore_2
      //   155: aconst_null
      //   156: astore_1
      //   157: aload_3
      //   158: ifnull +7 -> 165
      //   161: aload_3
      //   162: invokevirtual 132	java/io/BufferedReader:close	()V
      //   165: aload_1
      //   166: ifnull +7 -> 173
      //   169: aload_1
      //   170: invokevirtual 133	java/io/FileReader:close	()V
      //   173: aload_2
      //   174: athrow
      //   175: astore_1
      //   176: goto -3 -> 173
      //   179: astore_2
      //   180: goto -23 -> 157
      //   183: astore 4
      //   185: aload_2
      //   186: astore_3
      //   187: aload 4
      //   189: astore_2
      //   190: goto -33 -> 157
      //   193: astore_2
      //   194: aconst_null
      //   195: astore_3
      //   196: aload_1
      //   197: astore_2
      //   198: aload_3
      //   199: astore_1
      //   200: goto -69 -> 131
      //   203: astore_3
      //   204: aload_1
      //   205: astore_3
      //   206: aload_2
      //   207: astore_1
      //   208: aload_3
      //   209: astore_2
      //   210: goto -79 -> 131
      //   213: astore_1
      //   214: goto -91 -> 123
      // Local variable table:
      //   start	length	slot	name	signature
      //   61	43	0	i	int
      //   31	89	1	localFileReader	java.io.FileReader
      //   128	1	1	localIOException1	IOException
      //   130	6	1	localObject1	Object
      //   150	1	1	localIOException2	IOException
      //   156	14	1	localObject2	Object
      //   175	22	1	localIOException3	IOException
      //   199	9	1	localObject3	Object
      //   213	1	1	localIOException4	IOException
      //   3	141	2	localBufferedReader	java.io.BufferedReader
      //   154	20	2	localObject4	Object
      //   179	7	2	localObject5	Object
      //   189	1	2	localObject6	Object
      //   193	1	2	localIOException5	IOException
      //   197	13	2	localObject7	Object
      //   1	198	3	localObject8	Object
      //   203	1	3	localIOException6	IOException
      //   205	4	3	localObject9	Object
      //   8	116	4	arrayOfString	String[]
      //   183	5	4	localObject10	Object
      // Exception table:
      //   from	to	target	type
      //   22	32	128	java/io/IOException
      //   135	139	150	java/io/IOException
      //   143	147	150	java/io/IOException
      //   22	32	154	finally
      //   161	165	175	java/io/IOException
      //   169	173	175	java/io/IOException
      //   32	44	179	finally
      //   44	49	183	finally
      //   53	60	183	finally
      //   62	100	183	finally
      //   32	44	193	java/io/IOException
      //   44	49	203	java/io/IOException
      //   53	60	203	java/io/IOException
      //   62	100	203	java/io/IOException
      //   111	115	213	java/io/IOException
      //   119	123	213	java/io/IOException
    }
    
    class a
      implements FileFilter
    {
      a() {}
      
      public boolean accept(File paramFile)
      {
        return Pattern.matches("cpu[0-9]", paramFile.getName());
      }
    }
  }
  
  static class b {}
  
  static class c
  {
    private static int a = -1;
    
    public static boolean a()
    {
      if (a == 1) {
        return true;
      }
      if (a == 0) {
        return false;
      }
      String[] arrayOfString = new String[6];
      arrayOfString[0] = "/bin";
      arrayOfString[1] = "/system/bin/";
      arrayOfString[2] = "/system/xbin/";
      arrayOfString[3] = "/system/sbin/";
      arrayOfString[4] = "/sbin/";
      arrayOfString[5] = "/vendor/bin/";
      int i = 0;
      for (;;)
      {
        try
        {
          if (i < arrayOfString.length)
          {
            File localFile = new File(arrayOfString[i] + "su");
            if ((localFile == null) || (!localFile.exists())) {
              break label114;
            }
            a = 1;
            return true;
          }
        }
        catch (Exception localException)
        {
          a = 0;
          return false;
        }
        label114:
        i += 1;
      }
    }
  }
}
