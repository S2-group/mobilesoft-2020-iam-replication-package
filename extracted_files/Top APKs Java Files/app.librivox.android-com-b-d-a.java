package com.b.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;
import com.b.a.a.ab;
import com.b.a.a.ap;
import com.b.c.m;
import com.b.c.q;
import com.b.d.a.j;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class a
{
  private static a a;
  private static Context b;
  private Geocoder c;
  private Location d;
  private com.b.d.a.b e = null;
  private boolean f = false;
  private boolean g = false;
  private HashMap h = null;
  private List i = null;
  private ConcurrentHashMap j = null;
  private AtomicBoolean k = new AtomicBoolean(true);
  private com.b.d.a.a l = new com.b.d.a.a();
  private Location m;
  private long n;
  private final Semaphore o = new Semaphore(1);
  
  private a() {}
  
  public static a a()
  {
    if (a == null) {
      a = new a();
    }
    return a;
  }
  
  private String a(String paramString, ab paramAb)
  {
    Object localObject2 = "";
    if (paramAb == null)
    {
      com.b.b.b.a(m.c, "AdswizzSonar", "NULL bson document");
      return "";
    }
    Object localObject1 = localObject2;
    Object localObject3 = localObject2;
    try
    {
      paramString = (HttpURLConnection)FirebasePerfUrlConnection.instrument(new URL(paramString).openConnection());
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramString.setConnectTimeout(10000);
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramString.setReadTimeout(10000);
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramString.setRequestMethod("POST");
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramString.setRequestProperty("Content-Type", "application/bson");
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramString.setDoInput(true);
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramString.setDoOutput(true);
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramString.setUseCaches(false);
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramString.setAllowUserInteraction(false);
      localObject1 = localObject2;
      localObject3 = localObject2;
      Object localObject4 = paramString.getOutputStream();
      localObject1 = localObject2;
      localObject3 = localObject2;
      ByteBuffer localByteBuffer = ByteBuffer.allocate(ap.a(paramAb)).order(ByteOrder.LITTLE_ENDIAN);
      localObject1 = localObject2;
      localObject3 = localObject2;
      ap.a(localByteBuffer, paramAb);
      localObject1 = localObject2;
      localObject3 = localObject2;
      localByteBuffer.position(0);
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramAb = Channels.newChannel((OutputStream)localObject4);
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramAb.write(localByteBuffer);
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramAb.close();
      localObject1 = localObject2;
      localObject3 = localObject2;
      ((OutputStream)localObject4).close();
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramString.connect();
      localObject1 = localObject2;
      localObject3 = localObject2;
      if (paramString.getResponseCode() == 200)
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        paramAb = new BufferedReader(new InputStreamReader(paramString.getInputStream()));
        for (paramString = (String)localObject2;; paramString = ((StringBuilder)localObject4).toString())
        {
          localObject1 = paramString;
          localObject3 = paramString;
          localObject2 = paramAb.readLine();
          localObject1 = paramString;
          if (localObject2 == null) {
            break;
          }
          localObject1 = paramString;
          localObject3 = paramString;
          localObject4 = new StringBuilder();
          localObject1 = paramString;
          localObject3 = paramString;
          ((StringBuilder)localObject4).append(paramString);
          localObject1 = paramString;
          localObject3 = paramString;
          ((StringBuilder)localObject4).append((String)localObject2);
          localObject1 = paramString;
          localObject3 = paramString;
        }
      }
      return "";
    }
    catch (Exception paramString)
    {
      paramAb = m.b;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Exception:");
      ((StringBuilder)localObject2).append(paramString.toString());
      com.b.b.b.a(paramAb, "AdswizzSonar", ((StringBuilder)localObject2).toString());
      return localObject1;
      com.b.b.b.a(m.b, "AdswizzSonar", "SocketTimeoutException");
      return localObject3;
    }
    catch (SocketTimeoutException paramString)
    {
      for (;;) {}
    }
  }
  
  private String a(String paramString, HashMap paramHashMap)
  {
    return a(paramString, ap.a(paramHashMap));
  }
  
  private void a(Location paramLocation)
  {
    if (paramLocation != null)
    {
      if (System.currentTimeMillis() - this.n < q.a().u()) {
        return;
      }
      if ((this.m != null) && (this.m.distanceTo(paramLocation) <= 1.0D)) {
        return;
      }
      Log.d("AdswizzSonar", "/tracking");
      this.m = paramLocation;
      this.n = System.currentTimeMillis();
      try
      {
        com.b.c.f.b.execute(new g(this, paramLocation));
        return;
      }
      catch (Exception paramLocation)
      {
        m localM = m.b;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("sendTrackingRequest() exception=");
        localStringBuilder.append(paramLocation.toString());
        com.b.b.b.a(localM, "AdswizzSonar", localStringBuilder.toString());
      }
    }
  }
  
  private void a(Location paramLocation, HashMap paramHashMap)
  {
    if (paramLocation == null) {
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("lat", Double.valueOf(paramLocation.getLatitude()));
    localHashMap.put("long", Double.valueOf(paramLocation.getLongitude()));
    localHashMap.put("alt", Double.valueOf(paramLocation.getAltitude()));
    localHashMap.put("speed", Double.valueOf(paramLocation.getSpeed()));
    localHashMap.put("epoch", Long.valueOf(paramLocation.getTime()));
    localHashMap.put("accuracy", Double.valueOf(paramLocation.getAccuracy()));
    localHashMap.put("provider", paramLocation.getProvider());
    paramHashMap.put("gps", localHashMap);
    try
    {
      paramLocation = this.c.getFromLocation(paramLocation.getLatitude(), paramLocation.getLongitude(), 1);
      if (paramLocation.size() > 0) {
        localHashMap.put("placemarksGeocode", ((Address)paramLocation.get(0)).toString());
      }
      return;
    }
    catch (IOException paramLocation) {}
  }
  
  private void a(boolean paramBoolean)
  {
    try
    {
      com.b.c.f.b.execute(new d(this, paramBoolean));
      return;
    }
    catch (Exception localException)
    {
      m localM = m.b;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("collectData() exception=");
      localStringBuilder.append(localException.toString());
      com.b.b.b.a(localM, "AdswizzSonar", localStringBuilder.toString());
    }
  }
  
  private void d()
  {
    if (this.h != null) {
      return;
    }
    if (b != null) {}
    for (Object localObject = j.a(b);; localObject = new HashMap())
    {
      this.h = ((HashMap)localObject);
      break;
    }
    localObject = m.b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("General infos: ");
    localStringBuilder.append(this.h);
    com.b.b.b.a((m)localObject, "AdswizzSonar", localStringBuilder.toString());
  }
  
  private void e()
  {
    if (this.i != null) {
      return;
    }
    this.i = new ArrayList();
    Iterator localIterator = b.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      this.i.add(localApplicationInfo.packageName);
    }
  }
  
  private void f()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(this.h);
    localHashMap.put("idfa", com.b.c.f.f());
    localHashMap.put("installedApps", this.i);
    localHashMap.put("clientVersion", com.b.c.f.c());
    a(this.d, localHashMap);
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(q.a().q());
    ((StringBuilder)localObject1).append("/getProfile");
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = a((String)localObject1, localHashMap);
    this.l.a((String)localObject2);
    localObject2 = m.b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("getSonarProfile() from url= ");
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append(" uploadParams = ");
    localStringBuilder.append(localHashMap);
    com.b.b.b.a((m)localObject2, "AdswizzSonar", localStringBuilder.toString());
  }
  
  /* Error */
  private final void g()
  {
    // Byte code:
    //   0: getstatic 237	com/b/c/m:b	Lcom/b/c/m;
    //   3: astore_3
    //   4: new 227	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 228	java/lang/StringBuilder:<init>	()V
    //   11: astore 4
    //   13: aload 4
    //   15: ldc_w 481
    //   18: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: pop
    //   22: aload 4
    //   24: invokestatic 487	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   27: invokevirtual 490	java/lang/Thread:getName	()Ljava/lang/String;
    //   30: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: aload_3
    //   35: ldc 93
    //   37: aload 4
    //   39: invokevirtual 235	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: invokestatic 100	com/b/b/b:a	(Lcom/b/c/m;Ljava/lang/String;Ljava/lang/String;)V
    //   45: lconst_0
    //   46: lstore_1
    //   47: aload_0
    //   48: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   51: ifnull +152 -> 203
    //   54: aload_0
    //   55: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   58: getfield 495	com/b/d/a/b:e	Ljava/util/ArrayList;
    //   61: astore_3
    //   62: aload_3
    //   63: monitorenter
    //   64: aload_0
    //   65: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   68: getfield 495	com/b/d/a/b:e	Ljava/util/ArrayList;
    //   71: invokevirtual 498	java/util/ArrayList:clone	()Ljava/lang/Object;
    //   74: checkcast 414	java/util/ArrayList
    //   77: astore 5
    //   79: aload_0
    //   80: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   83: getfield 500	com/b/d/a/b:h	Ljava/util/concurrent/atomic/AtomicBoolean;
    //   86: iconst_1
    //   87: invokevirtual 503	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
    //   90: aload_3
    //   91: monitorexit
    //   92: aload_0
    //   93: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   96: getfield 505	com/b/d/a/b:c	Ljava/util/ArrayList;
    //   99: astore 4
    //   101: aload 4
    //   103: monitorenter
    //   104: aload_0
    //   105: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   108: getfield 505	com/b/d/a/b:c	Ljava/util/ArrayList;
    //   111: invokevirtual 498	java/util/ArrayList:clone	()Ljava/lang/Object;
    //   114: checkcast 414	java/util/ArrayList
    //   117: astore_3
    //   118: aload_0
    //   119: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   122: getfield 507	com/b/d/a/b:f	Ljava/util/concurrent/atomic/AtomicBoolean;
    //   125: iconst_1
    //   126: invokevirtual 503	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
    //   129: aload 4
    //   131: monitorexit
    //   132: aload_0
    //   133: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   136: getfield 509	com/b/d/a/b:d	Ljava/util/ArrayList;
    //   139: astore 6
    //   141: aload 6
    //   143: monitorenter
    //   144: aload_0
    //   145: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   148: getfield 509	com/b/d/a/b:d	Ljava/util/ArrayList;
    //   151: invokevirtual 498	java/util/ArrayList:clone	()Ljava/lang/Object;
    //   154: checkcast 414	java/util/ArrayList
    //   157: astore 4
    //   159: aload_0
    //   160: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   163: getfield 511	com/b/d/a/b:g	Ljava/util/concurrent/atomic/AtomicBoolean;
    //   166: iconst_1
    //   167: invokevirtual 503	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
    //   170: aload 6
    //   172: monitorexit
    //   173: aload_0
    //   174: getfield 38	com/b/d/a:e	Lcom/b/d/a/b;
    //   177: getfield 513	com/b/d/a/b:a	J
    //   180: lstore_1
    //   181: goto +48 -> 229
    //   184: astore_3
    //   185: aload 6
    //   187: monitorexit
    //   188: aload_3
    //   189: athrow
    //   190: astore_3
    //   191: aload 4
    //   193: monitorexit
    //   194: aload_3
    //   195: athrow
    //   196: astore 4
    //   198: aload_3
    //   199: monitorexit
    //   200: aload 4
    //   202: athrow
    //   203: new 414	java/util/ArrayList
    //   206: dup
    //   207: invokespecial 415	java/util/ArrayList:<init>	()V
    //   210: astore_3
    //   211: new 414	java/util/ArrayList
    //   214: dup
    //   215: invokespecial 415	java/util/ArrayList:<init>	()V
    //   218: astore 4
    //   220: new 414	java/util/ArrayList
    //   223: dup
    //   224: invokespecial 415	java/util/ArrayList:<init>	()V
    //   227: astore 5
    //   229: new 302	java/util/HashMap
    //   232: dup
    //   233: invokespecial 303	java/util/HashMap:<init>	()V
    //   236: astore 6
    //   238: aload 6
    //   240: ldc_w 515
    //   243: aload_3
    //   244: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   247: pop
    //   248: aload 6
    //   250: ldc_w 517
    //   253: aload 4
    //   255: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   258: pop
    //   259: aload 6
    //   261: ldc_w 458
    //   264: invokestatic 460	com/b/c/f:f	()Ljava/lang/String;
    //   267: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   270: pop
    //   271: aload 6
    //   273: ldc_w 519
    //   276: lload_1
    //   277: invokestatic 345	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   280: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   283: pop
    //   284: aload 6
    //   286: ldc_w 464
    //   289: invokestatic 466	com/b/c/f:c	()Ljava/lang/String;
    //   292: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   295: pop
    //   296: aload 5
    //   298: invokevirtual 520	java/util/ArrayList:size	()I
    //   301: ifle +14 -> 315
    //   304: aload 6
    //   306: ldc_w 522
    //   309: aload 5
    //   311: invokevirtual 319	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   314: pop
    //   315: aload_0
    //   316: aload_0
    //   317: getfield 71	com/b/d/a:d	Landroid/location/Location;
    //   320: aload 6
    //   322: invokespecial 385	com/b/d/a:a	(Landroid/location/Location;Ljava/util/HashMap;)V
    //   325: aload_0
    //   326: getfield 51	com/b/d/a:k	Ljava/util/concurrent/atomic/AtomicBoolean;
    //   329: invokevirtual 524	java/util/concurrent/atomic/AtomicBoolean:get	()Z
    //   332: ifeq +117 -> 449
    //   335: aload 6
    //   337: invokestatic 245	com/b/a/a/ap:a	(Ljava/util/Map;)Lcom/b/a/a/ab;
    //   340: astore_3
    //   341: new 227	java/lang/StringBuilder
    //   344: dup
    //   345: invokespecial 228	java/lang/StringBuilder:<init>	()V
    //   348: astore 4
    //   350: aload 4
    //   352: invokestatic 261	com/b/c/q:a	()Lcom/b/c/q;
    //   355: invokevirtual 469	com/b/c/q:q	()Ljava/lang/String;
    //   358: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: pop
    //   362: aload 4
    //   364: ldc_w 526
    //   367: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: pop
    //   371: aload_0
    //   372: aload 4
    //   374: invokevirtual 235	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   377: aload_3
    //   378: invokespecial 247	com/b/d/a:a	(Ljava/lang/String;Lcom/b/a/a/ab;)Ljava/lang/String;
    //   381: pop
    //   382: getstatic 237	com/b/c/m:b	Lcom/b/c/m;
    //   385: astore_3
    //   386: new 227	java/lang/StringBuilder
    //   389: dup
    //   390: invokespecial 228	java/lang/StringBuilder:<init>	()V
    //   393: astore 4
    //   395: aload 4
    //   397: ldc_w 528
    //   400: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: pop
    //   404: aload 4
    //   406: invokestatic 487	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   409: invokevirtual 490	java/lang/Thread:getName	()Ljava/lang/String;
    //   412: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: pop
    //   416: aload 4
    //   418: ldc_w 530
    //   421: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: pop
    //   425: aload 4
    //   427: aload 6
    //   429: invokevirtual 411	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   432: pop
    //   433: aload 4
    //   435: invokevirtual 235	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   438: astore 4
    //   440: aload_3
    //   441: ldc 93
    //   443: aload 4
    //   445: invokestatic 100	com/b/b/b:a	(Lcom/b/c/m;Ljava/lang/String;Ljava/lang/String;)V
    //   448: return
    //   449: aload_0
    //   450: getfield 58	com/b/d/a:o	Ljava/util/concurrent/Semaphore;
    //   453: invokevirtual 533	java/util/concurrent/Semaphore:acquire	()V
    //   456: aload_0
    //   457: getfield 44	com/b/d/a:j	Ljava/util/concurrent/ConcurrentHashMap;
    //   460: astore_3
    //   461: aload_3
    //   462: monitorenter
    //   463: aload 6
    //   465: aload_0
    //   466: getfield 44	com/b/d/a:j	Ljava/util/concurrent/ConcurrentHashMap;
    //   469: invokevirtual 456	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   472: aload_3
    //   473: monitorexit
    //   474: aload 6
    //   476: invokestatic 245	com/b/a/a/ap:a	(Ljava/util/Map;)Lcom/b/a/a/ab;
    //   479: astore_3
    //   480: aload_0
    //   481: getfield 58	com/b/d/a:o	Ljava/util/concurrent/Semaphore;
    //   484: astore 4
    //   486: aload 4
    //   488: invokevirtual 536	java/util/concurrent/Semaphore:release	()V
    //   491: goto +34 -> 525
    //   494: astore 4
    //   496: aload_3
    //   497: monitorexit
    //   498: aload 4
    //   500: athrow
    //   501: astore_3
    //   502: goto +133 -> 635
    //   505: astore_3
    //   506: aload_3
    //   507: invokevirtual 539	java/lang/InterruptedException:printStackTrace	()V
    //   510: aload 6
    //   512: invokestatic 245	com/b/a/a/ap:a	(Ljava/util/Map;)Lcom/b/a/a/ab;
    //   515: astore_3
    //   516: aload_0
    //   517: getfield 58	com/b/d/a:o	Ljava/util/concurrent/Semaphore;
    //   520: astore 4
    //   522: goto -36 -> 486
    //   525: aload_0
    //   526: getfield 51	com/b/d/a:k	Ljava/util/concurrent/atomic/AtomicBoolean;
    //   529: iconst_1
    //   530: invokevirtual 503	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
    //   533: new 227	java/lang/StringBuilder
    //   536: dup
    //   537: invokespecial 228	java/lang/StringBuilder:<init>	()V
    //   540: astore 4
    //   542: aload 4
    //   544: invokestatic 261	com/b/c/q:a	()Lcom/b/c/q;
    //   547: invokevirtual 469	com/b/c/q:q	()Ljava/lang/String;
    //   550: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: pop
    //   554: aload 4
    //   556: ldc_w 526
    //   559: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   562: pop
    //   563: aload_0
    //   564: aload 4
    //   566: invokevirtual 235	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   569: aload_3
    //   570: invokespecial 247	com/b/d/a:a	(Ljava/lang/String;Lcom/b/a/a/ab;)Ljava/lang/String;
    //   573: pop
    //   574: getstatic 237	com/b/c/m:b	Lcom/b/c/m;
    //   577: astore_3
    //   578: new 227	java/lang/StringBuilder
    //   581: dup
    //   582: invokespecial 228	java/lang/StringBuilder:<init>	()V
    //   585: astore 4
    //   587: aload 4
    //   589: ldc_w 528
    //   592: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   595: pop
    //   596: aload 4
    //   598: invokestatic 487	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   601: invokevirtual 490	java/lang/Thread:getName	()Ljava/lang/String;
    //   604: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   607: pop
    //   608: aload 4
    //   610: ldc_w 530
    //   613: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: pop
    //   617: aload 4
    //   619: aload 6
    //   621: invokevirtual 411	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   624: pop
    //   625: aload 4
    //   627: invokevirtual 235	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   630: astore 4
    //   632: goto -192 -> 440
    //   635: aload_0
    //   636: getfield 58	com/b/d/a:o	Ljava/util/concurrent/Semaphore;
    //   639: invokevirtual 536	java/util/concurrent/Semaphore:release	()V
    //   642: aload_3
    //   643: athrow
    //   644: astore_3
    //   645: getstatic 91	com/b/c/m:c	Lcom/b/c/m;
    //   648: astore 4
    //   650: new 227	java/lang/StringBuilder
    //   653: dup
    //   654: invokespecial 228	java/lang/StringBuilder:<init>	()V
    //   657: astore 5
    //   659: aload 5
    //   661: ldc_w 541
    //   664: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   667: pop
    //   668: aload 5
    //   670: aload_3
    //   671: invokevirtual 240	java/lang/Exception:toString	()Ljava/lang/String;
    //   674: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   677: pop
    //   678: aload 4
    //   680: ldc 93
    //   682: aload 5
    //   684: invokevirtual 235	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   687: invokestatic 100	com/b/b/b:a	(Lcom/b/c/m;Ljava/lang/String;Ljava/lang/String;)V
    //   690: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	691	0	this	a
    //   46	231	1	l1	long
    //   3	115	3	localObject1	Object
    //   184	5	3	localObject2	Object
    //   190	9	3	localObject3	Object
    //   501	1	3	localObject5	Object
    //   505	2	3	localInterruptedException	InterruptedException
    //   515	128	3	localObject6	Object
    //   644	27	3	localException	Exception
    //   11	181	4	localObject7	Object
    //   196	5	4	localObject8	Object
    //   218	269	4	localObject9	Object
    //   494	5	4	localObject10	Object
    //   520	159	4	localObject11	Object
    //   77	606	5	localObject12	Object
    //   139	481	6	localObject13	Object
    // Exception table:
    //   from	to	target	type
    //   144	173	184	finally
    //   185	188	184	finally
    //   104	132	190	finally
    //   191	194	190	finally
    //   64	92	196	finally
    //   198	200	196	finally
    //   463	474	494	finally
    //   496	498	494	finally
    //   449	463	501	finally
    //   474	480	501	finally
    //   498	501	501	finally
    //   506	516	501	finally
    //   449	463	505	java/lang/InterruptedException
    //   474	480	505	java/lang/InterruptedException
    //   498	501	505	java/lang/InterruptedException
    //   47	64	644	java/lang/Exception
    //   92	104	644	java/lang/Exception
    //   132	144	644	java/lang/Exception
    //   173	181	644	java/lang/Exception
    //   188	190	644	java/lang/Exception
    //   194	196	644	java/lang/Exception
    //   200	203	644	java/lang/Exception
    //   203	229	644	java/lang/Exception
    //   229	315	644	java/lang/Exception
    //   315	440	644	java/lang/Exception
    //   440	448	644	java/lang/Exception
    //   480	486	644	java/lang/Exception
    //   486	491	644	java/lang/Exception
    //   516	522	644	java/lang/Exception
    //   525	632	644	java/lang/Exception
    //   635	644	644	java/lang/Exception
  }
  
  /* Error */
  private void h()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 58	com/b/d/a:o	Ljava/util/concurrent/Semaphore;
    //   4: invokevirtual 533	java/util/concurrent/Semaphore:acquire	()V
    //   7: aload_0
    //   8: getfield 44	com/b/d/a:j	Ljava/util/concurrent/ConcurrentHashMap;
    //   11: invokevirtual 549	java/util/concurrent/ConcurrentHashMap:values	()Ljava/util/Collection;
    //   14: invokeinterface 552 1 0
    //   19: astore_1
    //   20: aload_1
    //   21: invokeinterface 437 1 0
    //   26: ifeq +27 -> 53
    //   29: aload_1
    //   30: invokeinterface 441 1 0
    //   35: checkcast 414	java/util/ArrayList
    //   38: invokevirtual 555	java/util/ArrayList:clear	()V
    //   41: goto -21 -> 20
    //   44: astore_1
    //   45: goto +16 -> 61
    //   48: astore_1
    //   49: aload_1
    //   50: invokevirtual 539	java/lang/InterruptedException:printStackTrace	()V
    //   53: aload_0
    //   54: getfield 58	com/b/d/a:o	Ljava/util/concurrent/Semaphore;
    //   57: invokevirtual 536	java/util/concurrent/Semaphore:release	()V
    //   60: return
    //   61: aload_0
    //   62: getfield 58	com/b/d/a:o	Ljava/util/concurrent/Semaphore;
    //   65: invokevirtual 536	java/util/concurrent/Semaphore:release	()V
    //   68: aload_1
    //   69: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	70	0	this	a
    //   19	11	1	localIterator	Iterator
    //   44	1	1	localObject	Object
    //   48	21	1	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   0	20	44	finally
    //   20	41	44	finally
    //   49	53	44	finally
    //   0	20	48	java/lang/InterruptedException
    //   20	41	48	java/lang/InterruptedException
  }
  
  public String a(String paramString)
  {
    if ((this.l != null) && (this.l.a)) {
      return this.l.b(paramString);
    }
    return null;
  }
  
  public void a(Context paramContext, com.b.c.b.a.a paramA, b paramB)
  {
    try
    {
      boolean bool = this.f;
      if (bool) {
        return;
      }
      b = paramContext;
      this.c = new Geocoder(paramContext, Locale.ENGLISH);
      bool = paramB.c;
      this.n = 0L;
      com.b.c.f.a(new c(this, bool));
      this.j = new ConcurrentHashMap();
      this.j.put("jackInfos", new ArrayList());
      this.j.put("bluetoothInfos", new ArrayList());
      this.j.put("powerInfos", new ArrayList());
      this.j.put("lightInfos", new ArrayList());
      a(paramB.a);
      if (paramB.b) {
        this.e = new com.b.d.a.b(paramContext, paramA);
      } else {
        com.b.b.b.a(m.b, "AdswizzSonar", "dynamicManager not initialised!");
      }
      this.f = true;
      return;
    }
    finally {}
  }
  
  public void b()
  {
    try
    {
      com.b.c.f.b.execute(new f(this));
      return;
    }
    catch (Exception localException)
    {
      m localM = m.b;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("triggerUploadMotionData() exception=");
      localStringBuilder.append(localException.toString());
      com.b.b.b.a(localM, "AdswizzSonar", localStringBuilder.toString());
    }
  }
  
  public void b(String paramString)
  {
    if (this.f)
    {
      if (this.g) {
        return;
      }
      this.g = true;
      com.b.c.f.b.execute(new e(this, paramString));
    }
  }
  
  public void c()
  {
    try
    {
      Object localObject1 = m.b;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("collectExtraDynamicData() on thread ");
      ((StringBuilder)localObject2).append(Thread.currentThread().getName());
      com.b.b.b.a((m)localObject1, "AdswizzSonar", ((StringBuilder)localObject2).toString());
      long l1 = System.currentTimeMillis();
      if (this.k.get()) {
        h();
      }
      Object localObject4 = (ArrayList)this.j.get("jackInfos");
      localObject3 = (ArrayList)this.j.get("bluetoothInfos");
      localObject2 = (ArrayList)this.j.get("powerInfos");
      localObject1 = (ArrayList)this.j.get("lightInfos");
      HashMap localHashMap = new HashMap();
      localHashMap.put("epoch", Long.valueOf(l1));
      localHashMap.put("jackPlugged", Boolean.valueOf(j.h(b)));
      ((ArrayList)localObject4).add(localHashMap);
      localObject4 = new HashMap();
      ((HashMap)localObject4).put("epoch", Long.valueOf(l1));
      ((HashMap)localObject4).putAll(j.a());
      ((ArrayList)localObject3).add(localObject4);
      localObject3 = new HashMap();
      ((HashMap)localObject3).put("epoch", Long.valueOf(l1));
      ((HashMap)localObject3).putAll(j.b(b));
      ((ArrayList)localObject2).add(localObject3);
      localObject2 = new HashMap();
      ((HashMap)localObject2).put("epoch", Long.valueOf(l1));
      ((HashMap)localObject2).put("brightness", Integer.valueOf(j.e(b)));
      ((ArrayList)localObject1).add(localObject2);
      this.k.set(false);
      return;
    }
    catch (Exception localException)
    {
      this.k.set(true);
      Object localObject2 = m.c;
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("exception=");
      ((StringBuilder)localObject3).append(localException.toString());
      com.b.b.b.a((m)localObject2, "AdswizzSonar", ((StringBuilder)localObject3).toString());
    }
  }
}
