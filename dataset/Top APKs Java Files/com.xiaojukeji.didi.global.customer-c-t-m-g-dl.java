package c.t.m.g;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import com.tencent.map.geolocation.internal.TencentHttpClient;
import com.tencent.map.geolocation.internal.TencentHttpClientFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.eclipse.jdt.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class dl
{
  private static volatile dl m;
  public final Context a;
  public final dm b;
  public final ExecutorService c;
  final PackageManager d;
  public final TelephonyManager e;
  public final WifiManager f;
  public final LocationManager g;
  public final TencentHttpClient h;
  String i;
  private final HashMap<String, dq> j;
  private CountDownLatch k;
  private final SharedPreferences l;
  private List<fc> n;
  
  private dl(Context paramContext)
  {
    this.a = paramContext;
    this.d = paramContext.getPackageManager();
    this.e = ((TelephonyManager)paramContext.getSystemService("phone"));
    this.f = ((WifiManager)paramContext.getSystemService("wifi"));
    this.g = ((LocationManager)paramContext.getSystemService("location"));
    Object localObject = new Bundle();
    ((Bundle)localObject).putString("channelId", j.g(paramContext.getPackageName()));
    this.h = TencentHttpClientFactory.getInstance().getTencentHttpClient(paramContext, (Bundle)localObject);
    this.l = paramContext.getSharedPreferences("LocationSDK", 0);
    localObject = new ThreadFactory()
    {
      public final Thread newThread(Runnable paramAnonymousRunnable)
      {
        return new Thread(paramAnonymousRunnable, "network_request_pool");
      }
    };
    localObject = new ThreadPoolExecutor(1, 5, 60000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), (ThreadFactory)localObject);
    if (Build.VERSION.SDK_INT >= 9) {
      ((ThreadPoolExecutor)localObject).allowCoreThreadTimeOut(true);
    }
    this.c = ((ExecutorService)localObject);
    this.j = new HashMap();
    if (Build.VERSION.SDK_INT >= 12) {
      this.j.put("cell", new ce("cell"));
    }
    this.b = new dm();
    try
    {
      this.b.g = b(paramContext);
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    j.a("AppContext", 6, "transactionTooLarge");
    a();
  }
  
  public static dl a(Context paramContext)
  {
    if (m == null) {
      try
      {
        if (m == null) {
          m = new dl(paramContext);
        }
      }
      finally {}
    }
    return m;
  }
  
  private static String b(Context paramContext)
    throws Exception
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      if (paramContext != null)
      {
        if (paramContext.containsKey("TencentGeoLocationSDK")) {
          return paramContext.getString("TencentGeoLocationSDK");
        }
        if (paramContext.containsKey("TencentMapSDK"))
        {
          paramContext = paramContext.getString("TencentMapSDK");
          return paramContext;
        }
        return "";
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  private String b(String paramString)
  {
    Object localObject1 = this.b;
    Object localObject2 = new HashMap();
    ((HashMap)localObject2).put("version", ((dm)localObject1).d());
    ((HashMap)localObject2).put("app_name", c(((dm)localObject1).h));
    ((HashMap)localObject2).put("app_label", c(((dm)localObject1).i));
    ((HashMap)localObject2).put("l", paramString);
    try
    {
      paramString = new JSONObject((Map)localObject2);
      localObject2 = this.b;
      localObject1 = new HashMap();
      ((HashMap)localObject1).put("imei", c(((dm)localObject2).a()));
      ((HashMap)localObject1).put("imsi", c(((dm)localObject2).b()));
      ((HashMap)localObject1).put("n", c(j.e(((dm)localObject2).d)));
      ((HashMap)localObject1).put("qq", c(j.e(((dm)localObject2).f)));
      ((HashMap)localObject1).put("mac", c(((dm)localObject2).c().toLowerCase(Locale.ENGLISH)));
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(Build.MANUFACTURER);
      ((StringBuilder)localObject2).append("_");
      ((StringBuilder)localObject2).append(Build.MODEL);
      ((HashMap)localObject1).put("model", c(((StringBuilder)localObject2).toString()));
      paramString = paramString.put("attribute", new JSONObject((Map)localObject1)).toString();
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private static String c(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    return str;
  }
  
  public final dq a(String paramString)
  {
    paramString = (dq)this.j.get(paramString);
    if (paramString != null) {
      return paramString;
    }
    return dp.a;
  }
  
  public final void a()
  {
    this.k = new CountDownLatch(1);
    new Thread(new Runnable()
    {
      public final void run()
      {
        dl localDl = dl.this;
        for (;;)
        {
          try
          {
            j.a("AppContext", 4, "doInBg: app status init start");
            dm localDm = localDl.b;
            PackageInfo localPackageInfo = localDl.e();
            int i = localPackageInfo.versionCode;
            localDm.h = localPackageInfo.versionName;
            Object localObject = localDl.a.getApplicationInfo().loadLabel(localDl.d);
            if (localObject == null) {
              break label581;
            }
            localObject = ((CharSequence)localObject).toString();
            localDm.i = ((String)localObject);
            localObject = localDl.e;
            if (localObject != null)
            {
              int[] arrayOfInt = new int[2];
              ew.a((TelephonyManager)localObject, arrayOfInt);
              localDm.j = arrayOfInt[0];
              localDm.k = arrayOfInt[1];
              localDm.a = ((TelephonyManager)localObject).getPhoneType();
              try
              {
                localDl.i = localDl.e.getDeviceId();
                localDl.i = fa.a(localDl.i, fa.a).toUpperCase(Locale.ENGLISH);
              }
              catch (Throwable localThrowable2)
              {
                j.a("AppContext", 6, localThrowable2.toString());
              }
              String str2 = fa.a(((TelephonyManager)localObject).getSubscriberId(), fa.b);
              localObject = fa.a(((TelephonyManager)localObject).getLine1Number(), fa.c);
              localDm.b = localDl.i;
              localDm.c = str2;
              localDm.d = ((String)localObject);
            }
            localDm.e = fa.a(localDl.f().replaceAll(":", "").toUpperCase(Locale.ENGLISH), fa.d);
            localObject = localDl.d;
            boolean bool1 = ((PackageManager)localObject).hasSystemFeature("android.hardware.location.gps");
            boolean bool2 = ((PackageManager)localObject).hasSystemFeature("android.hardware.wifi");
            boolean bool3 = ((PackageManager)localObject).hasSystemFeature("android.hardware.telephony");
            localObject = new StringBuilder("doInBg: hasGps=");
            ((StringBuilder)localObject).append(bool1);
            ((StringBuilder)localObject).append(",hasWifi=");
            ((StringBuilder)localObject).append(bool2);
            ((StringBuilder)localObject).append(",hasCell=");
            ((StringBuilder)localObject).append(bool3);
            j.a("AppContext", 4, ((StringBuilder)localObject).toString());
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("os:");
            ((StringBuilder)localObject).append(Build.MODEL);
            ((StringBuilder)localObject).append(" ");
            ((StringBuilder)localObject).append(Build.VERSION.RELEASE);
            ((StringBuilder)localObject).append(" ");
            ((StringBuilder)localObject).append(localDm.a());
            ((StringBuilder)localObject).append(" net:");
            ((StringBuilder)localObject).append(localDm.j);
            ((StringBuilder)localObject).append(" ");
            ((StringBuilder)localObject).append(localDm.k);
            ((StringBuilder)localObject).append(" app:");
            ((StringBuilder)localObject).append(localPackageInfo.versionCode);
            ((StringBuilder)localObject).append(" ");
            ((StringBuilder)localObject).append(localPackageInfo.versionName);
            ((StringBuilder)localObject).append(" sdk: ");
            ((StringBuilder)localObject).append(localDm.d());
            ((StringBuilder)localObject).append(" ");
            ((StringBuilder)localObject).append(localDm.e());
            j.a("AppContext", 4, ((StringBuilder)localObject).toString());
            j.a("AppContext", 4, "doInBg: app status init done");
          }
          catch (Throwable localThrowable1)
          {
            j.a("AppContext", "doInBg: app status init error", localThrowable1);
          }
          dl.a(dl.this).countDown();
          return;
          label581:
          String str1 = "unknown";
        }
      }
    }).start();
  }
  
  public final void a(Object paramObject)
  {
    try
    {
      if (this.n == null) {
        this.n = new ArrayList();
      }
      Object localObject1 = this.n.iterator();
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = ((fc)((Iterator)localObject1).next()).c;
        if (paramObject == localObject2)
        {
          i1 = 1;
          break label69;
        }
      }
      int i1 = 0;
      label69:
      if (i1 != 0) {
        return;
      }
      localObject1 = paramObject.getClass().getDeclaredMethods();
      int i2 = localObject1.length;
      i1 = 0;
      while (i1 < i2)
      {
        localObject2 = localObject1[i1];
        Object localObject3 = ((Method)localObject2).getName();
        if ((((String)localObject3).startsWith("on")) && (((String)localObject3).endsWith("Event")))
        {
          localObject3 = ((Method)localObject2).getParameterTypes();
          if (localObject3.length != 1) {
            throw new IllegalArgumentException("EventHandler methods must specify a single Object paramter.");
          }
          localObject2 = new fc(localObject3[0], (Method)localObject2, paramObject);
          this.n.add(localObject2);
        }
        i1 += 1;
      }
      return;
    }
    finally {}
  }
  
  public final void b(@Nullable Object paramObject)
  {
    if (paramObject == null) {
      return;
    }
    try
    {
      Object localObject = this.n;
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          fc localFc = (fc)((Iterator)localObject).next();
          boolean bool = paramObject.getClass().equals(localFc.a);
          if (bool) {
            try
            {
              localFc.b.invoke(localFc.c, new Object[] { paramObject });
            }
            catch (Exception localException)
            {
              j.a("AppContext", "", localException);
            }
          }
        }
      }
      return;
    }
    finally {}
  }
  
  public final boolean b()
  {
    return this.e != null;
  }
  
  public final dm c()
  {
    try
    {
      this.k.await();
      return this.b;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public final String d()
  {
    if (!dj.a().d("up_apps")) {
      return null;
    }
    int i2 = (int)(System.currentTimeMillis() / 86400000L % 5L);
    try
    {
      i1 = Math.abs(this.i.hashCode()) % 5;
    }
    catch (Exception localException1)
    {
      for (;;) {}
    }
    i1 = 0;
    if ((i2 != i1) || (!this.l.getBoolean("flag", false)))
    {
      for (;;)
      {
        try
        {
          Object localObject2 = this.d.getInstalledPackages(8192);
          localObject1 = new StringBuilder();
          localObject2 = ((List)localObject2).iterator();
          if (!((Iterator)localObject2).hasNext()) {
            continue;
          }
          localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
          i1 = localPackageInfo.applicationInfo.flags;
          localObject3 = localPackageInfo.applicationInfo;
          if ((i1 & 0x1) > 0) {
            continue;
          }
          localObject3 = localPackageInfo.applicationInfo.loadLabel(this.d).toString();
          if ((((String)localObject3).length() > 30) || (((String)localObject3).startsWith("com.")) || (((String)localObject3).contains("theme"))) {
            continue;
          }
          if (!((String)localObject3).contains("CheilPengtai")) {
            continue;
          }
        }
        catch (Exception localException2)
        {
          Object localObject1;
          PackageInfo localPackageInfo;
          Object localObject3;
          continue;
          i1 = 0;
          continue;
          i1 = 1;
          continue;
        }
        if (i1 == 0)
        {
          ((StringBuilder)localObject1).append((String)localObject3);
          ((StringBuilder)localObject1).append('_');
          ((StringBuilder)localObject1).append(localPackageInfo.versionName);
          ((StringBuilder)localObject1).append('|');
        }
      }
      this.l.edit().putBoolean("flag", true).apply();
      localObject1 = b(((StringBuilder)localObject1).toString());
      return localObject1;
      this.l.edit().putBoolean("flag", true).apply();
      return null;
      this.l.edit().putBoolean("flag", false).apply();
    }
    return null;
  }
  
  final PackageInfo e()
  {
    try
    {
      PackageInfo localPackageInfo = this.d.getPackageInfo(this.a.getPackageName(), 0);
      return localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    return new PackageInfo();
  }
  
  /* Error */
  final String f()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 115	c/t/m/g/dl:l	Landroid/content/SharedPreferences;
    //   4: ldc_w 524
    //   7: ldc -43
    //   9: invokeinterface 527 3 0
    //   14: astore_3
    //   15: aload_3
    //   16: astore 4
    //   18: aload_3
    //   19: invokestatic 532	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   22: ifne +5 -> 27
    //   25: aload_3
    //   26: areturn
    //   27: aload_3
    //   28: astore 4
    //   30: invokestatic 538	java/net/NetworkInterface:getNetworkInterfaces	()Ljava/util/Enumeration;
    //   33: astore 6
    //   35: aload_3
    //   36: astore 4
    //   38: aload_3
    //   39: astore 5
    //   41: aload 6
    //   43: invokeinterface 543 1 0
    //   48: ifeq +222 -> 270
    //   51: aload_3
    //   52: astore 4
    //   54: aload 6
    //   56: invokeinterface 546 1 0
    //   61: checkcast 534	java/net/NetworkInterface
    //   64: astore 5
    //   66: aload 5
    //   68: ifnull -33 -> 35
    //   71: aload_3
    //   72: astore 4
    //   74: aload 5
    //   76: invokevirtual 547	java/net/NetworkInterface:getName	()Ljava/lang/String;
    //   79: ldc_w 549
    //   82: invokevirtual 552	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   85: ifeq -50 -> 35
    //   88: aload_3
    //   89: astore 4
    //   91: aload 5
    //   93: invokevirtual 556	java/net/NetworkInterface:getHardwareAddress	()[B
    //   96: astore 5
    //   98: aload 5
    //   100: ifnull -65 -> 35
    //   103: aload_3
    //   104: astore 4
    //   106: aload 5
    //   108: arraylength
    //   109: ifeq -74 -> 35
    //   112: aload_3
    //   113: astore 4
    //   115: new 271	java/lang/StringBuilder
    //   118: dup
    //   119: invokespecial 272	java/lang/StringBuilder:<init>	()V
    //   122: astore 7
    //   124: aload_3
    //   125: astore 4
    //   127: aload 5
    //   129: arraylength
    //   130: istore_2
    //   131: iconst_0
    //   132: istore_1
    //   133: iload_1
    //   134: iload_2
    //   135: if_icmpge +39 -> 174
    //   138: aload_3
    //   139: astore 4
    //   141: aload 7
    //   143: ldc_w 558
    //   146: iconst_1
    //   147: anewarray 4	java/lang/Object
    //   150: dup
    //   151: iconst_0
    //   152: aload 5
    //   154: iload_1
    //   155: baload
    //   156: invokestatic 564	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   159: aastore
    //   160: invokestatic 568	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   163: invokevirtual 281	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: iload_1
    //   168: iconst_1
    //   169: iadd
    //   170: istore_1
    //   171: goto -38 -> 133
    //   174: aload_3
    //   175: astore 4
    //   177: aload 7
    //   179: invokevirtual 569	java/lang/StringBuilder:length	()I
    //   182: ifle +19 -> 201
    //   185: aload_3
    //   186: astore 4
    //   188: aload 7
    //   190: aload 7
    //   192: invokevirtual 569	java/lang/StringBuilder:length	()I
    //   195: iconst_1
    //   196: isub
    //   197: invokevirtual 573	java/lang/StringBuilder:deleteCharAt	(I)Ljava/lang/StringBuilder;
    //   200: pop
    //   201: aload_3
    //   202: astore 4
    //   204: aload 7
    //   206: invokevirtual 291	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   209: astore_3
    //   210: aload_0
    //   211: getfield 115	c/t/m/g/dl:l	Landroid/content/SharedPreferences;
    //   214: invokeinterface 501 1 0
    //   219: ldc_w 524
    //   222: aload_3
    //   223: invokeinterface 576 3 0
    //   228: invokeinterface 510 1 0
    //   233: goto -198 -> 35
    //   236: astore 5
    //   238: goto +16 -> 254
    //   241: astore 5
    //   243: aload 4
    //   245: astore_3
    //   246: goto +8 -> 254
    //   249: astore 5
    //   251: ldc -43
    //   253: astore_3
    //   254: ldc_w 578
    //   257: bipush 6
    //   259: aload 5
    //   261: invokevirtual 579	java/lang/Throwable:toString	()Ljava/lang/String;
    //   264: invokestatic 177	c/t/m/g/j:a	(Ljava/lang/String;ILjava/lang/String;)V
    //   267: aload_3
    //   268: astore 5
    //   270: aload 5
    //   272: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	273	0	this	dl
    //   132	39	1	i1	int
    //   130	6	2	i2	int
    //   14	254	3	localObject1	Object
    //   16	228	4	localObject2	Object
    //   39	114	5	localObject3	Object
    //   236	1	5	localThrowable1	Throwable
    //   241	1	5	localThrowable2	Throwable
    //   249	11	5	localThrowable3	Throwable
    //   268	3	5	localObject4	Object
    //   33	22	6	localEnumeration	java.util.Enumeration
    //   122	83	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   210	233	236	java/lang/Throwable
    //   18	25	241	java/lang/Throwable
    //   30	35	241	java/lang/Throwable
    //   41	51	241	java/lang/Throwable
    //   54	66	241	java/lang/Throwable
    //   74	88	241	java/lang/Throwable
    //   91	98	241	java/lang/Throwable
    //   106	112	241	java/lang/Throwable
    //   115	124	241	java/lang/Throwable
    //   127	131	241	java/lang/Throwable
    //   141	167	241	java/lang/Throwable
    //   177	185	241	java/lang/Throwable
    //   188	201	241	java/lang/Throwable
    //   204	210	241	java/lang/Throwable
    //   0	15	249	java/lang/Throwable
  }
}
