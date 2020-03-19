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
import com.didi.sdk.apm.aspect.ThreadCreationAspect;
import com.tencent.map.geolocation.internal.TencentHttpClient;
import com.tencent.map.geolocation.internal.TencentHttpClientFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONException;
import org.json.JSONObject;

public class dl
{
  private static volatile dl m;
  private static final JoinPoint.StaticPart o;
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
  
  static {}
  
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
      a();
      return;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        j.a("AppContext", 6, "transactionTooLarge");
      }
    }
  }
  
  public static dl a(Context paramContext)
  {
    if (m == null) {}
    try
    {
      if (m == null) {
        m = new dl(paramContext);
      }
      return m;
    }
    finally {}
  }
  
  static final Thread a(dl paramDl, Runnable paramRunnable, JoinPoint paramJoinPoint)
  {
    return new Thread(paramRunnable);
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
    catch (Exception paramContext) {}
    return "";
  }
  
  private String b(String paramString)
  {
    dm localDm = this.b;
    HashMap localHashMap = new HashMap();
    localHashMap.put("version", localDm.d());
    localHashMap.put("app_name", c(localDm.h));
    localHashMap.put("app_label", c(localDm.i));
    localHashMap.put("l", paramString);
    try
    {
      paramString = new JSONObject(localHashMap);
      localDm = this.b;
      localHashMap = new HashMap();
      localHashMap.put("imei", c(localDm.a()));
      localHashMap.put("imsi", c(localDm.b()));
      localHashMap.put("n", c(j.e(localDm.d)));
      localHashMap.put("qq", c(j.e(localDm.f)));
      localHashMap.put("mac", c(localDm.c().toLowerCase(Locale.ENGLISH)));
      localHashMap.put("model", c(Build.MANUFACTURER + "_" + Build.MODEL));
      paramString = paramString.put("attribute", new JSONObject(localHashMap)).toString();
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
  
  private static void g()
  {
    Factory localFactory = new Factory("TL", dl.class);
    o = localFactory.makeSJP("constructor-call", localFactory.makeConstructorSig("1", "java.lang.Thread", "java.lang.Runnable", "target", ""), 140);
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
    Runnable local2 = new Runnable()
    {
      public final void run()
      {
        dl localDl = dl.this;
        for (;;)
        {
          try
          {
            j.a("AppContext", 4, "doInBg: app status init start");
            localDm = localDl.b;
            localPackageInfo = localDl.e();
            int i = localPackageInfo.versionCode;
            localDm.h = localPackageInfo.versionName;
            localObject1 = localDl.a.getApplicationInfo().loadLabel(localDl.d);
            if (localObject1 != null)
            {
              localObject1 = ((CharSequence)localObject1).toString();
              localDm.i = ((String)localObject1);
              localObject1 = localDl.e;
              if (localObject1 != null)
              {
                localObject2 = new int[2];
                ew.a((TelephonyManager)localObject1, (int[])localObject2);
                localDm.j = localObject2[0];
                localDm.k = localObject2[1];
                localDm.a = ((TelephonyManager)localObject1).getPhoneType();
              }
            }
          }
          catch (Throwable localThrowable1)
          {
            dm localDm;
            PackageInfo localPackageInfo;
            Object localObject1;
            Object localObject2;
            boolean bool1;
            boolean bool2;
            boolean bool3;
            j.a("AppContext", "doInBg: app status init error", localThrowable1);
            continue;
          }
          try
          {
            localDl.i = localDl.e.getDeviceId();
            localDl.i = fa.a(localDl.i, fa.a).toUpperCase(Locale.ENGLISH);
            localObject2 = fa.a(((TelephonyManager)localObject1).getSubscriberId(), fa.b);
            localObject1 = fa.a(((TelephonyManager)localObject1).getLine1Number(), fa.c);
            localDm.b = localDl.i;
            localDm.c = ((String)localObject2);
            localDm.d = ((String)localObject1);
            localDm.e = fa.a(localDl.f().replaceAll(":", "").toUpperCase(Locale.ENGLISH), fa.d);
            localObject1 = localDl.d;
            bool1 = ((PackageManager)localObject1).hasSystemFeature("android.hardware.location.gps");
            bool2 = ((PackageManager)localObject1).hasSystemFeature("android.hardware.wifi");
            bool3 = ((PackageManager)localObject1).hasSystemFeature("android.hardware.telephony");
            j.a("AppContext", 4, "doInBg: hasGps=" + bool1 + ",hasWifi=" + bool2 + ",hasCell=" + bool3);
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("os:").append(Build.MODEL).append(" ").append(Build.VERSION.RELEASE).append(" ").append(localDm.a()).append(" net:").append(localDm.j).append(" ").append(localDm.k).append(" app:").append(localPackageInfo.versionCode).append(" ").append(localPackageInfo.versionName).append(" sdk: ").append(localDm.d()).append(" ").append(localDm.e());
            j.a("AppContext", 4, ((StringBuilder)localObject1).toString());
            j.a("AppContext", 4, "doInBg: app status init done");
            dl.a(dl.this).countDown();
            return;
            localObject1 = "unknown";
          }
          catch (Throwable localThrowable2)
          {
            j.a("AppContext", 6, localThrowable2.toString());
          }
        }
      }
    };
    JoinPoint localJoinPoint = Factory.makeJP(o, this, null, local2);
    ((Thread)ThreadCreationAspect.aspectOf().aroundCreateAnonymousThread(new dl.AjcClosure1(new Object[] { this, local2, localJoinPoint }).linkClosureAndJoinPoint(4096))).start();
  }
  
  public final void a(Object paramObject)
  {
    for (;;)
    {
      Object localObject3;
      try
      {
        if (this.n == null) {
          this.n = new ArrayList();
        }
        Object localObject1 = this.n.iterator();
        if (!((Iterator)localObject1).hasNext()) {
          break label191;
        }
        localObject2 = ((fc)((Iterator)localObject1).next()).c;
        if (paramObject != localObject2) {
          continue;
        }
        i1 = 1;
        if (i1 != 0) {
          return;
        }
        localObject1 = paramObject.getClass().getDeclaredMethods();
        int i2 = localObject1.length;
        i1 = 0;
        if (i1 >= i2) {
          continue;
        }
        localObject2 = localObject1[i1];
        localObject3 = ((Method)localObject2).getName();
        if ((!((String)localObject3).startsWith("on")) || (!((String)localObject3).endsWith("Event"))) {
          break label184;
        }
        localObject3 = ((Method)localObject2).getParameterTypes();
        if (localObject3.length != 1) {
          throw new IllegalArgumentException("EventHandler methods must specify a single Object paramter.");
        }
      }
      finally {}
      Object localObject2 = new fc(localObject3[0], (Method)localObject2, paramObject);
      this.n.add(localObject2);
      label184:
      i1 += 1;
      continue;
      label191:
      int i1 = 0;
    }
  }
  
  /* Error */
  public final void b(@org.eclipse.jdt.annotation.Nullable Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull +6 -> 9
    //   6: aload_0
    //   7: monitorexit
    //   8: return
    //   9: aload_0
    //   10: getfield 389	c/t/m/g/dl:n	Ljava/util/List;
    //   13: astore_3
    //   14: aload_3
    //   15: ifnull -9 -> 6
    //   18: aload_3
    //   19: invokeinterface 398 1 0
    //   24: astore_3
    //   25: aload_3
    //   26: invokeinterface 404 1 0
    //   31: ifeq -25 -> 6
    //   34: aload_3
    //   35: invokeinterface 408 1 0
    //   40: checkcast 410	c/t/m/g/fc
    //   43: astore 4
    //   45: aload_1
    //   46: invokevirtual 417	java/lang/Object:getClass	()Ljava/lang/Class;
    //   49: aload 4
    //   51: getfield 458	c/t/m/g/fc:a	Ljava/lang/Class;
    //   54: invokevirtual 461	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   57: istore_2
    //   58: iload_2
    //   59: ifeq -34 -> 25
    //   62: aload 4
    //   64: getfield 464	c/t/m/g/fc:b	Ljava/lang/reflect/Method;
    //   67: aload 4
    //   69: getfield 413	c/t/m/g/fc:c	Ljava/lang/Object;
    //   72: iconst_1
    //   73: anewarray 4	java/lang/Object
    //   76: dup
    //   77: iconst_0
    //   78: aload_1
    //   79: aastore
    //   80: invokevirtual 468	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   83: pop
    //   84: goto -59 -> 25
    //   87: astore 4
    //   89: ldc -76
    //   91: ldc -32
    //   93: aload 4
    //   95: invokestatic 471	c/t/m/g/j:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   98: goto -73 -> 25
    //   101: astore_1
    //   102: aload_0
    //   103: monitorexit
    //   104: aload_1
    //   105: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	this	dl
    //   0	106	1	paramObject	Object
    //   57	2	2	bool	boolean
    //   13	22	3	localObject	Object
    //   43	25	4	localFc	fc
    //   87	7	4	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   62	84	87	java/lang/Exception
    //   9	14	101	finally
    //   18	25	101	finally
    //   25	58	101	finally
    //   62	84	101	finally
    //   89	98	101	finally
  }
  
  public final boolean b()
  {
    return this.e != null;
  }
  
  public final dm c()
  {
    if (-1L > 0L) {}
    try
    {
      if (!this.k.await(-1L, TimeUnit.MILLISECONDS))
      {
        return null;
        this.k.await();
      }
      return this.b;
    }
    catch (InterruptedException localInterruptedException) {}
    return null;
  }
  
  public final String d()
  {
    if (!dj.a().d("up_apps")) {
      return null;
    }
    int i2 = (int)(System.currentTimeMillis() / 86400000L % 5L);
    for (;;)
    {
      try
      {
        i1 = Math.abs(this.i.hashCode()) % 5;
        if (i2 == i1) {
          if (!this.l.getBoolean("flag", false))
          {
            try
            {
              Object localObject1 = this.d.getInstalledPackages(8192);
              localStringBuilder = new StringBuilder();
              localObject1 = ((List)localObject1).iterator();
              if (!((Iterator)localObject1).hasNext()) {
                continue;
              }
              localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
              i1 = localPackageInfo.applicationInfo.flags;
              localObject2 = localPackageInfo.applicationInfo;
              if ((i1 & 0x1) > 0) {
                continue;
              }
              localObject2 = localPackageInfo.applicationInfo.loadLabel(this.d).toString();
              if ((((String)localObject2).length() > 30) || (((String)localObject2).startsWith("com.")) || (((String)localObject2).contains("theme"))) {
                break label334;
              }
              if (!((String)localObject2).contains("CheilPengtai")) {
                continue;
              }
            }
            catch (Exception localException1)
            {
              StringBuilder localStringBuilder;
              PackageInfo localPackageInfo;
              Object localObject2;
              this.l.edit().putBoolean("flag", true).apply();
            }
            if (i1 != 0) {
              continue;
            }
            localStringBuilder.append((String)localObject2);
            localStringBuilder.append('_');
            localStringBuilder.append(localPackageInfo.versionName);
            localStringBuilder.append('|');
            continue;
          }
          else
          {
            return null;
          }
        }
      }
      catch (Exception localException2)
      {
        i1 = 0;
        continue;
        i1 = 0;
        continue;
        this.l.edit().putBoolean("flag", true).apply();
        String str = b(localException2.toString());
        return str;
      }
      this.l.edit().putBoolean("flag", false).apply();
      continue;
      label334:
      int i1 = 1;
    }
  }
  
  final PackageInfo e()
  {
    try
    {
      PackageInfo localPackageInfo = this.d.getPackageInfo(this.a.getPackageName(), 0);
      return localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return new PackageInfo();
  }
  
  /* Error */
  final String f()
  {
    // Byte code:
    //   0: ldc -32
    //   2: astore_3
    //   3: aload_0
    //   4: getfield 121	c/t/m/g/dl:l	Landroid/content/SharedPreferences;
    //   7: ldc_w 587
    //   10: ldc -32
    //   12: invokeinterface 590 3 0
    //   17: astore 4
    //   19: aload 4
    //   21: astore_3
    //   22: aload_3
    //   23: invokestatic 595	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   26: ifne +5 -> 31
    //   29: aload_3
    //   30: areturn
    //   31: invokestatic 601	java/net/NetworkInterface:getNetworkInterfaces	()Ljava/util/Enumeration;
    //   34: astore 5
    //   36: aload_3
    //   37: astore 4
    //   39: aload 4
    //   41: astore_3
    //   42: aload 5
    //   44: invokeinterface 606 1 0
    //   49: ifeq +210 -> 259
    //   52: aload 4
    //   54: astore_3
    //   55: aload 5
    //   57: invokeinterface 609 1 0
    //   62: checkcast 597	java/net/NetworkInterface
    //   65: astore 6
    //   67: aload 6
    //   69: ifnull -30 -> 39
    //   72: aload 4
    //   74: astore_3
    //   75: aload 6
    //   77: invokevirtual 610	java/net/NetworkInterface:getName	()Ljava/lang/String;
    //   80: ldc_w 612
    //   83: invokevirtual 615	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   86: ifeq -47 -> 39
    //   89: aload 4
    //   91: astore_3
    //   92: aload 6
    //   94: invokevirtual 619	java/net/NetworkInterface:getHardwareAddress	()[B
    //   97: astore 6
    //   99: aload 6
    //   101: ifnull -62 -> 39
    //   104: aload 4
    //   106: astore_3
    //   107: aload 6
    //   109: arraylength
    //   110: ifeq -71 -> 39
    //   113: aload 4
    //   115: astore_3
    //   116: new 284	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 285	java/lang/StringBuilder:<init>	()V
    //   123: astore 7
    //   125: aload 4
    //   127: astore_3
    //   128: aload 6
    //   130: arraylength
    //   131: istore_2
    //   132: iconst_0
    //   133: istore_1
    //   134: iload_1
    //   135: iload_2
    //   136: if_icmpge +39 -> 175
    //   139: aload 4
    //   141: astore_3
    //   142: aload 7
    //   144: ldc_w 621
    //   147: iconst_1
    //   148: anewarray 4	java/lang/Object
    //   151: dup
    //   152: iconst_0
    //   153: aload 6
    //   155: iload_1
    //   156: baload
    //   157: invokestatic 627	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   160: aastore
    //   161: invokestatic 631	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   164: invokevirtual 294	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: iload_1
    //   169: iconst_1
    //   170: iadd
    //   171: istore_1
    //   172: goto -38 -> 134
    //   175: aload 4
    //   177: astore_3
    //   178: aload 7
    //   180: invokevirtual 632	java/lang/StringBuilder:length	()I
    //   183: ifle +19 -> 202
    //   186: aload 4
    //   188: astore_3
    //   189: aload 7
    //   191: aload 7
    //   193: invokevirtual 632	java/lang/StringBuilder:length	()I
    //   196: iconst_1
    //   197: isub
    //   198: invokevirtual 636	java/lang/StringBuilder:deleteCharAt	(I)Ljava/lang/StringBuilder;
    //   201: pop
    //   202: aload 4
    //   204: astore_3
    //   205: aload 7
    //   207: invokevirtual 302	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   210: astore 4
    //   212: aload 4
    //   214: astore_3
    //   215: aload_0
    //   216: getfield 121	c/t/m/g/dl:l	Landroid/content/SharedPreferences;
    //   219: invokeinterface 564 1 0
    //   224: ldc_w 587
    //   227: aload 4
    //   229: invokeinterface 639 3 0
    //   234: invokeinterface 573 1 0
    //   239: goto -200 -> 39
    //   242: astore 4
    //   244: ldc_w 641
    //   247: bipush 6
    //   249: aload 4
    //   251: invokevirtual 642	java/lang/Throwable:toString	()Ljava/lang/String;
    //   254: invokestatic 185	c/t/m/g/j:a	(Ljava/lang/String;ILjava/lang/String;)V
    //   257: aload_3
    //   258: areturn
    //   259: aload 4
    //   261: areturn
    //   262: astore 4
    //   264: goto -20 -> 244
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	267	0	this	dl
    //   133	39	1	i1	int
    //   131	6	2	i2	int
    //   2	256	3	localObject1	Object
    //   17	211	4	localObject2	Object
    //   242	18	4	localThrowable1	Throwable
    //   262	1	4	localThrowable2	Throwable
    //   34	22	5	localEnumeration	java.util.Enumeration
    //   65	89	6	localObject3	Object
    //   123	83	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   3	19	242	java/lang/Throwable
    //   42	52	242	java/lang/Throwable
    //   55	67	242	java/lang/Throwable
    //   75	89	242	java/lang/Throwable
    //   92	99	242	java/lang/Throwable
    //   107	113	242	java/lang/Throwable
    //   116	125	242	java/lang/Throwable
    //   128	132	242	java/lang/Throwable
    //   142	168	242	java/lang/Throwable
    //   178	186	242	java/lang/Throwable
    //   189	202	242	java/lang/Throwable
    //   205	212	242	java/lang/Throwable
    //   215	239	242	java/lang/Throwable
    //   22	29	262	java/lang/Throwable
    //   31	36	262	java/lang/Throwable
  }
}
