package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.GpsStatus.NmeaListener;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TreeMap;

public final class al
{
  private static int D = 10000;
  private static al u;
  private Timer A;
  private Thread B;
  private Looper C;
  private Context a = null;
  private TelephonyManager b = null;
  private LocationManager c = null;
  private WifiManager d = null;
  private SensorManager e = null;
  private String f = "";
  private String g = "";
  private String h = "";
  private boolean i;
  private int j;
  private boolean k;
  private long l;
  private String m;
  private String n;
  private int o;
  private int p;
  private int q;
  private String r;
  private long s;
  private long t;
  private an v;
  private ao w;
  private CellLocation x;
  private ap y;
  private List z;
  
  private al(Context paramContext)
  {
    boolean bool = false;
    this.i = false;
    this.j = 0;
    this.k = false;
    this.l = -1L;
    this.m = "";
    this.n = "";
    this.o = 0;
    this.p = 0;
    this.q = 0;
    this.r = "";
    this.s = 0L;
    this.t = 0L;
    this.v = null;
    this.w = null;
    this.x = null;
    this.y = null;
    this.z = new ArrayList();
    this.A = null;
    this.B = null;
    this.C = null;
    if (paramContext == null) {
      return;
    }
    this.a = paramContext;
    this.f = Build.MODEL;
    this.b = ((TelephonyManager)paramContext.getSystemService("phone"));
    this.c = ((LocationManager)paramContext.getSystemService("location"));
    this.d = ((WifiManager)paramContext.getSystemService("wifi"));
    this.e = ((SensorManager)paramContext.getSystemService("sensor"));
    if (this.b != null) {
      if (this.d == null) {
        return;
      }
    }
    try
    {
      this.g = this.b.getDeviceId();
      this.h = this.b.getSubscriberId();
      if (this.d.getConnectionInfo() != null)
      {
        this.n = this.d.getConnectionInfo().getMacAddress();
        if ((this.n != null) && (this.n.length() > 0)) {
          this.n = this.n.replace(":", "");
        }
      }
      String[] arrayOfString = b(this.b);
      this.o = Integer.parseInt(arrayOfString[0]);
      this.p = Integer.parseInt(arrayOfString[1]);
      this.q = this.b.getNetworkType();
      this.r = paramContext.getPackageName();
      if (this.b.getPhoneType() == 2) {
        bool = true;
      }
      this.i = bool;
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private void A()
  {
    if (this.d == null) {
      return;
    }
    try
    {
      if (be.a) {
        this.d.startScan();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private CellLocation B()
  {
    if (this.b == null) {
      return null;
    }
    try
    {
      CellLocation localCellLocation = b((List)ai.a(this.b, "getAllCellInfo", new Object[0]));
      return localCellLocation;
    }
    catch (NoSuchMethodException|Exception localNoSuchMethodException)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static int a(CellLocation paramCellLocation, Context paramContext)
  {
    int i2 = Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0);
    int i1 = 1;
    if (i2 == 1) {
      return 9;
    }
    if (paramCellLocation == null) {
      return 9;
    }
    if ((paramCellLocation instanceof GsmCellLocation)) {}
    for (;;)
    {
      return i1;
      try
      {
        Class.forName("android.telephony.cdma.CdmaCellLocation");
        i1 = 2;
      }
      catch (Exception paramCellLocation) {}
    }
    return 9;
  }
  
  protected static al a(Context paramContext)
  {
    if ((u == null) && (c(paramContext)))
    {
      Object localObject = (LocationManager)paramContext.getSystemService("location");
      if (localObject != null)
      {
        localObject = ((LocationManager)localObject).getAllProviders().iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str = (String)((Iterator)localObject).next();
          if ((str.equals("passive")) || (str.equals("gps")))
          {
            i1 = 1;
            break label83;
          }
        }
      }
      int i1 = 0;
      label83:
      if (i1 != 0) {
        u = new al(paramContext);
      }
    }
    return u;
  }
  
  private void a(BroadcastReceiver paramBroadcastReceiver)
  {
    if (paramBroadcastReceiver != null)
    {
      if (this.a == null) {
        return;
      }
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
      this.a.registerReceiver(paramBroadcastReceiver, localIntentFilter);
    }
  }
  
  private static void a(List paramList)
  {
    if (paramList != null)
    {
      if (paramList.size() <= 0) {
        return;
      }
      HashMap localHashMap = new HashMap();
      int i1 = 0;
      while (i1 < paramList.size())
      {
        localObject = (ScanResult)paramList.get(i1);
        if (((ScanResult)localObject).SSID == null) {
          ((ScanResult)localObject).SSID = "null";
        }
        localHashMap.put(Integer.valueOf(((ScanResult)localObject).level), localObject);
        i1 += 1;
      }
      Object localObject = new TreeMap(Collections.reverseOrder());
      ((TreeMap)localObject).putAll(localHashMap);
      paramList.clear();
      Iterator localIterator = ((TreeMap)localObject).keySet().iterator();
      while (localIterator.hasNext()) {
        paramList.add(((TreeMap)localObject).get((Integer)localIterator.next()));
      }
      localHashMap.clear();
      ((TreeMap)localObject).clear();
    }
  }
  
  private boolean a(CellLocation paramCellLocation)
  {
    if (paramCellLocation == null) {
      return false;
    }
    switch (a(paramCellLocation, this.a))
    {
    default: 
      break;
    }
    try
    {
      if (ai.b(paramCellLocation, "getSystemId", new Object[0]) <= 0) {
        return false;
      }
      if (ai.b(paramCellLocation, "getNetworkId", new Object[0]) < 0) {
        return false;
      }
      int i1 = ai.b(paramCellLocation, "getBaseStationId", new Object[0]);
      if (i1 >= 0) {
        break label168;
      }
      return false;
    }
    catch (Exception paramCellLocation)
    {
      for (;;) {}
    }
    paramCellLocation = (GsmCellLocation)paramCellLocation;
    if (paramCellLocation.getLac() == -1) {
      return false;
    }
    if (paramCellLocation.getLac() == 0) {
      return false;
    }
    if (paramCellLocation.getLac() > 65535) {
      return false;
    }
    if (paramCellLocation.getCid() == -1) {
      return false;
    }
    if (paramCellLocation.getCid() == 0) {
      return false;
    }
    if (paramCellLocation.getCid() == 65535) {
      return false;
    }
    return paramCellLocation.getCid() < 268435455;
  }
  
  private static boolean a(Object paramObject)
  {
    try
    {
      Method localMethod = WifiManager.class.getDeclaredMethod("isScanAlwaysAvailable", null);
      if (localMethod != null)
      {
        boolean bool = ((Boolean)localMethod.invoke(paramObject, null)).booleanValue();
        return bool;
      }
    }
    catch (Exception paramObject)
    {
      for (;;) {}
    }
    return false;
  }
  
  private static int b(Object paramObject)
  {
    try
    {
      Method localMethod = Sensor.class.getDeclaredMethod("getMinDelay", null);
      if (localMethod != null)
      {
        int i1 = ((Integer)localMethod.invoke(paramObject, null)).intValue();
        return i1;
      }
    }
    catch (Exception paramObject)
    {
      for (;;) {}
    }
    return 0;
  }
  
  /* Error */
  private static CellLocation b(List arg0)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +595 -> 596
    //   4: aload_0
    //   5: invokeinterface 421 1 0
    //   10: ifeq +6 -> 16
    //   13: goto +583 -> 596
    //   16: invokestatic 427	java/lang/ClassLoader:getSystemClassLoader	()Ljava/lang/ClassLoader;
    //   19: astore 11
    //   21: iconst_0
    //   22: istore_3
    //   23: iconst_0
    //   24: istore_1
    //   25: aconst_null
    //   26: astore 9
    //   28: aconst_null
    //   29: astore 8
    //   31: iload_1
    //   32: istore_2
    //   33: aload 9
    //   35: astore 7
    //   37: aload 8
    //   39: astore 10
    //   41: iload_3
    //   42: aload_0
    //   43: invokeinterface 314 1 0
    //   48: if_icmpge +537 -> 585
    //   51: aload_0
    //   52: iload_3
    //   53: invokeinterface 321 2 0
    //   58: astore 12
    //   60: iload_1
    //   61: istore_2
    //   62: aload 9
    //   64: astore 7
    //   66: aload 8
    //   68: astore 10
    //   70: aload 12
    //   72: ifnull +496 -> 568
    //   75: iload_1
    //   76: istore_2
    //   77: aload 11
    //   79: ldc_w 429
    //   82: invokevirtual 432	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   85: astore 13
    //   87: iload_1
    //   88: istore_2
    //   89: aload 11
    //   91: ldc_w 434
    //   94: invokevirtual 432	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   97: astore 14
    //   99: iload_1
    //   100: istore_2
    //   101: aload 11
    //   103: ldc_w 436
    //   106: invokevirtual 432	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   109: astore 15
    //   111: iload_1
    //   112: istore_2
    //   113: aload 11
    //   115: ldc_w 438
    //   118: invokevirtual 432	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   121: astore 16
    //   123: iload_1
    //   124: istore_2
    //   125: aload 13
    //   127: aload 12
    //   129: invokevirtual 441	java/lang/Class:isInstance	(Ljava/lang/Object;)Z
    //   132: ifeq +8 -> 140
    //   135: iconst_1
    //   136: istore_1
    //   137: goto +54 -> 191
    //   140: iload_1
    //   141: istore_2
    //   142: aload 14
    //   144: aload 12
    //   146: invokevirtual 441	java/lang/Class:isInstance	(Ljava/lang/Object;)Z
    //   149: ifeq +8 -> 157
    //   152: iconst_2
    //   153: istore_1
    //   154: goto +37 -> 191
    //   157: iload_1
    //   158: istore_2
    //   159: aload 15
    //   161: aload 12
    //   163: invokevirtual 441	java/lang/Class:isInstance	(Ljava/lang/Object;)Z
    //   166: ifeq +8 -> 174
    //   169: iconst_3
    //   170: istore_1
    //   171: goto +20 -> 191
    //   174: iload_1
    //   175: istore_2
    //   176: aload 16
    //   178: aload 12
    //   180: invokevirtual 441	java/lang/Class:isInstance	(Ljava/lang/Object;)Z
    //   183: ifeq +456 -> 639
    //   186: iconst_4
    //   187: istore_1
    //   188: goto +3 -> 191
    //   191: iload_1
    //   192: istore_2
    //   193: aload 9
    //   195: astore 7
    //   197: aload 8
    //   199: astore 10
    //   201: iload_1
    //   202: ifle +366 -> 568
    //   205: iload_1
    //   206: iconst_1
    //   207: if_icmpne +17 -> 224
    //   210: iload_1
    //   211: istore_2
    //   212: aload 13
    //   214: aload 12
    //   216: invokevirtual 444	java/lang/Class:cast	(Ljava/lang/Object;)Ljava/lang/Object;
    //   219: astore 7
    //   221: goto +60 -> 281
    //   224: iload_1
    //   225: iconst_2
    //   226: if_icmpne +17 -> 243
    //   229: iload_1
    //   230: istore_2
    //   231: aload 14
    //   233: aload 12
    //   235: invokevirtual 444	java/lang/Class:cast	(Ljava/lang/Object;)Ljava/lang/Object;
    //   238: astore 7
    //   240: goto +41 -> 281
    //   243: iload_1
    //   244: iconst_3
    //   245: if_icmpne +17 -> 262
    //   248: iload_1
    //   249: istore_2
    //   250: aload 15
    //   252: aload 12
    //   254: invokevirtual 444	java/lang/Class:cast	(Ljava/lang/Object;)Ljava/lang/Object;
    //   257: astore 7
    //   259: goto +22 -> 281
    //   262: iload_1
    //   263: iconst_4
    //   264: if_icmpne +380 -> 644
    //   267: iload_1
    //   268: istore_2
    //   269: aload 16
    //   271: aload 12
    //   273: invokevirtual 444	java/lang/Class:cast	(Ljava/lang/Object;)Ljava/lang/Object;
    //   276: astore 7
    //   278: goto +3 -> 281
    //   281: iload_1
    //   282: istore_2
    //   283: aload 7
    //   285: ldc_w 446
    //   288: iconst_0
    //   289: anewarray 4	java/lang/Object
    //   292: invokestatic 216	com/aps/ai:a	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    //   295: astore 10
    //   297: aload 10
    //   299: ifnonnull +16 -> 315
    //   302: iload_1
    //   303: istore_2
    //   304: aload 9
    //   306: astore 7
    //   308: aload 8
    //   310: astore 10
    //   312: goto +256 -> 568
    //   315: iload_1
    //   316: iconst_4
    //   317: if_icmpne +119 -> 436
    //   320: iload_1
    //   321: istore_2
    //   322: new 448	android/telephony/cdma/CdmaCellLocation
    //   325: dup
    //   326: invokespecial 449	android/telephony/cdma/CdmaCellLocation:<init>	()V
    //   329: astore 7
    //   331: aload 10
    //   333: ldc_w 377
    //   336: iconst_0
    //   337: anewarray 4	java/lang/Object
    //   340: invokestatic 380	com/aps/ai:b	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)I
    //   343: istore_2
    //   344: aload 10
    //   346: ldc_w 382
    //   349: iconst_0
    //   350: anewarray 4	java/lang/Object
    //   353: invokestatic 380	com/aps/ai:b	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)I
    //   356: istore 4
    //   358: aload 10
    //   360: ldc_w 451
    //   363: iconst_0
    //   364: anewarray 4	java/lang/Object
    //   367: invokestatic 380	com/aps/ai:b	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)I
    //   370: istore 5
    //   372: aload 10
    //   374: ldc_w 453
    //   377: iconst_0
    //   378: anewarray 4	java/lang/Object
    //   381: invokestatic 380	com/aps/ai:b	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)I
    //   384: istore 6
    //   386: aload 7
    //   388: iload 5
    //   390: aload 10
    //   392: ldc_w 455
    //   395: iconst_0
    //   396: anewarray 4	java/lang/Object
    //   399: invokestatic 380	com/aps/ai:b	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)I
    //   402: iload 6
    //   404: iload_2
    //   405: iload 4
    //   407: invokevirtual 459	android/telephony/cdma/CdmaCellLocation:setCellLocationData	(IIIII)V
    //   410: aload 7
    //   412: astore 10
    //   414: iload_1
    //   415: istore_2
    //   416: aload 9
    //   418: astore 7
    //   420: goto +165 -> 585
    //   423: aload 7
    //   425: astore 10
    //   427: iload_1
    //   428: istore_2
    //   429: aload 9
    //   431: astore 7
    //   433: goto +135 -> 568
    //   436: iload_1
    //   437: iconst_3
    //   438: if_icmpne +71 -> 509
    //   441: aload 10
    //   443: ldc_w 461
    //   446: iconst_0
    //   447: anewarray 4	java/lang/Object
    //   450: invokestatic 380	com/aps/ai:b	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)I
    //   453: istore 4
    //   455: iload_1
    //   456: istore_2
    //   457: aload 10
    //   459: ldc_w 463
    //   462: iconst_0
    //   463: anewarray 4	java/lang/Object
    //   466: invokestatic 380	com/aps/ai:b	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)I
    //   469: istore 5
    //   471: iload_1
    //   472: istore_2
    //   473: new 236	android/telephony/gsm/GsmCellLocation
    //   476: dup
    //   477: invokespecial 464	android/telephony/gsm/GsmCellLocation:<init>	()V
    //   480: astore 7
    //   482: aload 7
    //   484: iload 4
    //   486: iload 5
    //   488: invokevirtual 468	android/telephony/gsm/GsmCellLocation:setLacAndCid	(II)V
    //   491: iload_1
    //   492: istore_2
    //   493: aload 8
    //   495: astore 10
    //   497: goto +88 -> 585
    //   500: iload_1
    //   501: istore_2
    //   502: aload 8
    //   504: astore 10
    //   506: goto +62 -> 568
    //   509: aload 10
    //   511: ldc_w 469
    //   514: iconst_0
    //   515: anewarray 4	java/lang/Object
    //   518: invokestatic 380	com/aps/ai:b	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)I
    //   521: istore_2
    //   522: aload 10
    //   524: ldc_w 470
    //   527: iconst_0
    //   528: anewarray 4	java/lang/Object
    //   531: invokestatic 380	com/aps/ai:b	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)I
    //   534: istore 4
    //   536: new 236	android/telephony/gsm/GsmCellLocation
    //   539: dup
    //   540: invokespecial 464	android/telephony/gsm/GsmCellLocation:<init>	()V
    //   543: astore 7
    //   545: aload 7
    //   547: iload_2
    //   548: iload 4
    //   550: invokevirtual 468	android/telephony/gsm/GsmCellLocation:setLacAndCid	(II)V
    //   553: iload_1
    //   554: istore_2
    //   555: aload 8
    //   557: astore 10
    //   559: goto +26 -> 585
    //   562: goto +12 -> 574
    //   565: goto +9 -> 574
    //   568: aload 10
    //   570: astore 8
    //   572: iload_2
    //   573: istore_1
    //   574: iload_3
    //   575: iconst_1
    //   576: iadd
    //   577: istore_3
    //   578: aload 7
    //   580: astore 9
    //   582: goto -551 -> 31
    //   585: iload_2
    //   586: iconst_4
    //   587: if_icmpne +6 -> 593
    //   590: aload 10
    //   592: areturn
    //   593: aload 7
    //   595: areturn
    //   596: aconst_null
    //   597: areturn
    //   598: astore 7
    //   600: aload 9
    //   602: astore 7
    //   604: aload 8
    //   606: astore 10
    //   608: goto -40 -> 568
    //   611: astore_0
    //   612: goto -47 -> 565
    //   615: astore 8
    //   617: goto -194 -> 423
    //   620: astore 7
    //   622: aload 9
    //   624: astore 7
    //   626: goto -52 -> 574
    //   629: astore 9
    //   631: goto -131 -> 500
    //   634: astore 9
    //   636: goto -74 -> 562
    //   639: iconst_0
    //   640: istore_1
    //   641: goto -450 -> 191
    //   644: aconst_null
    //   645: astore 7
    //   647: goto -366 -> 281
    // Exception table:
    //   from	to	target	type
    //   77	87	598	java/lang/Exception
    //   89	99	598	java/lang/Exception
    //   101	111	598	java/lang/Exception
    //   113	123	598	java/lang/Exception
    //   125	135	598	java/lang/Exception
    //   142	152	598	java/lang/Exception
    //   159	169	598	java/lang/Exception
    //   176	186	598	java/lang/Exception
    //   212	221	598	java/lang/Exception
    //   231	240	598	java/lang/Exception
    //   250	259	598	java/lang/Exception
    //   269	278	598	java/lang/Exception
    //   283	297	598	java/lang/Exception
    //   322	331	598	java/lang/Exception
    //   457	471	598	java/lang/Exception
    //   473	482	598	java/lang/Exception
    //   331	410	615	java/lang/Exception
    //   441	455	620	java/lang/Exception
    //   509	545	620	java/lang/Exception
    //   482	491	629	java/lang/Exception
    //   545	553	634	java/lang/Exception
  }
  
  private void b(BroadcastReceiver paramBroadcastReceiver)
  {
    if (paramBroadcastReceiver != null) {
      if (this.a == null) {
        return;
      }
    }
    try
    {
      this.a.unregisterReceiver(paramBroadcastReceiver);
      return;
    }
    catch (Exception paramBroadcastReceiver) {}
  }
  
  protected static boolean b(Context paramContext)
  {
    if (paramContext == null) {
      return true;
    }
    boolean bool2 = Settings.Secure.getString(paramContext.getContentResolver(), "mock_location").equals("0");
    boolean bool1 = false;
    PackageManager localPackageManager;
    Object localObject;
    if (!bool2)
    {
      localPackageManager = paramContext.getPackageManager();
      localObject = localPackageManager.getInstalledApplications(128);
      paramContext = paramContext.getPackageName();
      localObject = ((List)localObject).iterator();
      bool1 = false;
    }
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      if (((Iterator)localObject).hasNext())
      {
        localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if (bool1) {}
      }
      try
      {
        String[] arrayOfString = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
        if (arrayOfString != null)
        {
          int i2 = arrayOfString.length;
          int i1 = 0;
          while (i1 < i2)
          {
            if (arrayOfString[i1].equals("android.permission.ACCESS_MOCK_LOCATION"))
            {
              bool2 = localApplicationInfo.packageName.equals(paramContext);
              if (bool2) {
                break;
              }
              bool1 = true;
              break;
            }
            i1 += 1;
          }
          return bool1;
        }
      }
      catch (Exception localException) {}
    }
  }
  
  private static String[] b(TelephonyManager paramTelephonyManager)
  {
    if (paramTelephonyManager != null) {
      paramTelephonyManager = paramTelephonyManager.getNetworkOperator();
    } else {
      paramTelephonyManager = null;
    }
    String[] arrayOfString = new String[2];
    int i1 = 0;
    arrayOfString[0] = "0";
    arrayOfString[1] = "0";
    if ((TextUtils.isDigitsOnly(paramTelephonyManager)) && (paramTelephonyManager.length() > 4))
    {
      arrayOfString[0] = paramTelephonyManager.substring(0, 3);
      char[] arrayOfChar = paramTelephonyManager.substring(3).toCharArray();
      while ((i1 < arrayOfChar.length) && (Character.isDigit(arrayOfChar[i1]))) {
        i1 += 1;
      }
      arrayOfString[1] = paramTelephonyManager.substring(3, i1 + 3);
    }
    return arrayOfString;
  }
  
  private static boolean c(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 4096);
      paramContext = paramContext.requestedPermissions;
      int i1 = 0;
      while (i1 < be.b.length)
      {
        if (!be.a(paramContext, be.b[i1])) {
          return false;
        }
        i1 += 1;
      }
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  protected final String a(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return "null";
    }
    List localList = this.e.getSensorList(-1);
    if ((localList != null) && (localList.get(paramInt) != null) && (((Sensor)localList.get(paramInt)).getName() != null) && (((Sensor)localList.get(paramInt)).getName().length() > 0)) {
      return ((Sensor)localList.get(paramInt)).getName();
    }
    return "null";
  }
  
  protected final List a(float paramFloat)
  {
    ArrayList localArrayList = new ArrayList();
    long l1 = System.currentTimeMillis();
    float f1 = paramFloat;
    if (Math.abs(paramFloat) <= 1.0F) {
      f1 = 1.0F;
    }
    if (c())
    {
      Object localObject = (CellLocation)j().get(1);
      if ((localObject != null) && ((localObject instanceof GsmCellLocation)))
      {
        localObject = (GsmCellLocation)localObject;
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localObject).getLac()));
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localObject).getCid()));
        if (l1 - ((Long)j().get(0)).longValue() <= 50000.0D / f1) {}
        for (localObject = Integer.valueOf(1);; localObject = Integer.valueOf(0))
        {
          localArrayList.add(localObject);
          return localArrayList;
        }
      }
    }
    return localArrayList;
  }
  
  protected final void a()
  {
    b();
    if (this.C != null)
    {
      this.C.quit();
      this.C = null;
    }
    if (this.B != null)
    {
      this.B.interrupt();
      this.B = null;
    }
    this.B = new am(this, "");
    this.B.start();
  }
  
  protected final double b(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return 0.0D;
    }
    List localList = this.e.getSensorList(-1);
    if (localList != null)
    {
      if (localList.get(paramInt) == null) {
        return 0.0D;
      }
      return ((Sensor)localList.get(paramInt)).getMaximumRange();
    }
    return 0.0D;
  }
  
  protected final List b(float paramFloat)
  {
    ArrayList localArrayList = new ArrayList();
    long l1 = System.currentTimeMillis();
    float f1 = paramFloat;
    if (Math.abs(paramFloat) <= 1.0F) {
      f1 = 1.0F;
    }
    if (c())
    {
      Object localObject = (CellLocation)j().get(1);
      if ((localObject != null) && ((localObject instanceof CdmaCellLocation)))
      {
        localObject = (CdmaCellLocation)localObject;
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getSystemId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getNetworkId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationLongitude()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationLatitude()));
        if (l1 - ((Long)j().get(0)).longValue() <= 50000.0D / f1) {}
        for (localObject = Integer.valueOf(1);; localObject = Integer.valueOf(0))
        {
          localArrayList.add(localObject);
          return localArrayList;
        }
      }
    }
    return localArrayList;
  }
  
  protected final void b()
  {
    Object localObject;
    if (this.v != null)
    {
      localObject = this.v;
      if (this.b != null) {
        this.b.listen((PhoneStateListener)localObject, 0);
      }
      this.v = null;
    }
    if (this.w != null)
    {
      localObject = this.w;
      if ((this.c != null) && (localObject != null)) {
        this.c.removeNmeaListener((GpsStatus.NmeaListener)localObject);
      }
      this.w = null;
    }
    if (this.A != null)
    {
      this.A.cancel();
      this.A = null;
    }
    if (this.C != null)
    {
      this.C.quit();
      this.C = null;
    }
    if (this.B != null)
    {
      this.B.interrupt();
      this.B = null;
    }
  }
  
  protected final int c(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return 0;
    }
    List localList = this.e.getSensorList(-1);
    if (localList != null)
    {
      if (localList.get(paramInt) == null) {
        return 0;
      }
      return b(localList.get(paramInt));
    }
    return 0;
  }
  
  protected final boolean c()
  {
    if ((this.b != null) && (this.b.getSimState() == 5) && (this.k)) {
      return true;
    }
    if (this.b != null) {}
    try
    {
      localCellLocation = this.b.getCellLocation();
    }
    catch (Exception localException)
    {
      CellLocation localCellLocation;
      for (;;) {}
    }
    localCellLocation = null;
    if (localCellLocation != null)
    {
      this.t = System.currentTimeMillis();
      this.x = localCellLocation;
      return true;
    }
    return false;
  }
  
  protected final int d(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return 0;
    }
    List localList = this.e.getSensorList(-1);
    if (localList != null)
    {
      if (localList.get(paramInt) == null) {
        return 0;
      }
      return (int)(((Sensor)localList.get(paramInt)).getPower() * 100.0D);
    }
    return 0;
  }
  
  protected final boolean d()
  {
    return (this.d != null) && ((this.d.isWifiEnabled()) || (a(this.d)));
  }
  
  protected final double e(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return 0.0D;
    }
    List localList = this.e.getSensorList(-1);
    if (localList != null)
    {
      if (localList.get(paramInt) == null) {
        return 0.0D;
      }
      return ((Sensor)localList.get(paramInt)).getResolution();
    }
    return 0.0D;
  }
  
  protected final boolean e()
  {
    try
    {
      if (this.c != null)
      {
        boolean bool = this.c.isProviderEnabled("gps");
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  protected final byte f(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return Byte.MAX_VALUE;
    }
    List localList = this.e.getSensorList(-1);
    if ((localList != null) && (localList.get(paramInt) != null))
    {
      if (((Sensor)localList.get(paramInt)).getType() > 127) {
        return Byte.MAX_VALUE;
      }
      return (byte)((Sensor)localList.get(paramInt)).getType();
    }
    return Byte.MAX_VALUE;
  }
  
  protected final String f()
  {
    if (this.f == null) {
      this.f = Build.MODEL;
    }
    if (this.f != null) {
      return this.f;
    }
    return "";
  }
  
  protected final String g()
  {
    if ((this.g == null) && (this.a != null))
    {
      this.b = ((TelephonyManager)this.a.getSystemService("phone"));
      if (this.b == null) {}
    }
    try
    {
      this.g = this.b.getDeviceId();
      if (this.g != null) {
        return this.g;
      }
      return "";
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  protected final String g(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return "null";
    }
    List localList = this.e.getSensorList(-1);
    if ((localList != null) && (localList.get(paramInt) != null) && (((Sensor)localList.get(paramInt)).getVendor() != null) && (((Sensor)localList.get(paramInt)).getVendor().length() > 0)) {
      return ((Sensor)localList.get(paramInt)).getVendor();
    }
    return "null";
  }
  
  protected final byte h(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return Byte.MAX_VALUE;
    }
    List localList = this.e.getSensorList(-1);
    if ((localList != null) && (localList.get(paramInt) != null))
    {
      if (((Sensor)localList.get(paramInt)).getType() > 127) {
        return Byte.MAX_VALUE;
      }
      return (byte)((Sensor)localList.get(paramInt)).getVersion();
    }
    return Byte.MAX_VALUE;
  }
  
  protected final String h()
  {
    if ((this.h == null) && (this.a != null))
    {
      this.b = ((TelephonyManager)this.a.getSystemService("phone"));
      if (this.b != null) {
        this.h = this.b.getSubscriberId();
      }
    }
    if (this.h != null) {
      return this.h;
    }
    return "";
  }
  
  protected final boolean i()
  {
    return this.i;
  }
  
  protected final List j()
  {
    if (Settings.System.getInt(this.a.getContentResolver(), "airplane_mode_on", 0) == 1) {
      return new ArrayList();
    }
    if (c())
    {
      ArrayList localArrayList = new ArrayList();
      if (!a(this.x))
      {
        localCellLocation = B();
        if (a(localCellLocation))
        {
          this.t = System.currentTimeMillis();
          break label79;
        }
      }
      CellLocation localCellLocation = this.x;
      label79:
      localArrayList.add(Long.valueOf(this.t));
      localArrayList.add(localCellLocation);
      return localArrayList;
    }
    return new ArrayList();
  }
  
  protected final List k()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2;
    if (d()) {
      localArrayList2 = new ArrayList();
    }
    for (;;)
    {
      try
      {
        long l1 = System.currentTimeMillis();
        long l2 = this.s;
        int i2 = 0;
        if (l1 - l2 >= 3500L) {
          break label144;
        }
        i1 = 1;
        if (i1 != 0)
        {
          localArrayList2.add(Long.valueOf(this.s));
          i1 = i2;
          if (i1 < this.z.size())
          {
            localArrayList1.add(this.z.get(i1));
            i1 += 1;
            continue;
          }
          localArrayList2.add(localArrayList1);
        }
        return localArrayList2;
      }
      finally {}
      return new ArrayList();
      label144:
      int i1 = 0;
    }
  }
  
  protected final byte l()
  {
    if (c()) {
      return (byte)this.j;
    }
    return Byte.MIN_VALUE;
  }
  
  protected final List m()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.b == null) {
      return localArrayList;
    }
    if (!c()) {
      return localArrayList;
    }
    if (this.b.getSimState() == 1) {
      return localArrayList;
    }
    Iterator localIterator = this.b.getNeighboringCellInfo().iterator();
    int i1 = 0;
    while (localIterator.hasNext())
    {
      NeighboringCellInfo localNeighboringCellInfo = (NeighboringCellInfo)localIterator.next();
      if (i1 > 15) {
        break;
      }
      if ((localNeighboringCellInfo.getLac() != 0) && (localNeighboringCellInfo.getLac() != 65535) && (localNeighboringCellInfo.getCid() != 65535) && (localNeighboringCellInfo.getCid() != 268435455))
      {
        localArrayList.add(localNeighboringCellInfo);
        i1 += 1;
      }
    }
    return localArrayList;
  }
  
  protected final List n()
  {
    ArrayList localArrayList = new ArrayList();
    String str;
    if (e())
    {
      l2 = this.l;
      str = this.m;
    }
    else
    {
      str = "";
      l2 = -1L;
    }
    long l1 = l2;
    if (l2 <= 0L) {
      l1 = System.currentTimeMillis() / 1000L;
    }
    long l2 = l1;
    if (l1 > 2147483647L) {
      l2 = l1 / 1000L;
    }
    localArrayList.add(Long.valueOf(l2));
    localArrayList.add(str);
    return localArrayList;
  }
  
  protected final long o()
  {
    long l2 = this.l;
    long l1 = l2;
    if (l2 <= 0L) {
      return 0L;
    }
    for (;;)
    {
      int i1 = String.valueOf(l1).length();
      if (i1 == 13) {
        break;
      }
      if (i1 > 13) {
        l1 /= 10L;
      } else {
        l1 *= 10L;
      }
    }
    return l1;
  }
  
  protected final String p()
  {
    if ((this.n == null) && (this.a != null))
    {
      this.d = ((WifiManager)this.a.getSystemService("wifi"));
      if ((this.d != null) && (this.d.getConnectionInfo() != null))
      {
        this.n = this.d.getConnectionInfo().getMacAddress();
        if ((this.n != null) && (this.n.length() > 0)) {
          this.n = this.n.replace(":", "");
        }
      }
    }
    if (this.n != null) {
      return this.n;
    }
    return "";
  }
  
  protected final int q()
  {
    return this.o;
  }
  
  protected final int r()
  {
    return this.p;
  }
  
  protected final int s()
  {
    return this.q;
  }
  
  protected final String t()
  {
    if ((this.r == null) && (this.a != null)) {
      this.r = this.a.getPackageName();
    }
    if (this.r != null) {
      return this.r;
    }
    return "";
  }
  
  protected final List u()
  {
    ArrayList localArrayList1 = new ArrayList();
    if (d())
    {
      Object localObject = k();
      List localList = (List)((List)localObject).get(1);
      int i1 = 0;
      long l1 = ((Long)((List)localObject).get(0)).longValue();
      a(localList);
      localArrayList1.add(Long.valueOf(l1));
      if ((localList != null) && (localList.size() > 0)) {
        while (i1 < localList.size())
        {
          localObject = (ScanResult)localList.get(i1);
          if (localArrayList1.size() - 1 >= 40) {
            break;
          }
          if (localObject != null)
          {
            ArrayList localArrayList2 = new ArrayList();
            localArrayList2.add(((ScanResult)localObject).BSSID.replace(":", ""));
            localArrayList2.add(Integer.valueOf(((ScanResult)localObject).level));
            localArrayList2.add(((ScanResult)localObject).SSID);
            localArrayList1.add(localArrayList2);
          }
          i1 += 1;
        }
      }
    }
    return localArrayList1;
  }
  
  protected final void v()
  {
    try
    {
      this.z.clear();
      if (this.y != null)
      {
        b(this.y);
        this.y = null;
      }
      if (this.A != null)
      {
        this.A.cancel();
        this.A = null;
      }
      this.A = new Timer();
      this.y = new ap(this, (byte)0);
      a(this.y);
      A();
      return;
    }
    finally {}
  }
  
  protected final void w()
  {
    try
    {
      this.z.clear();
      if (this.y != null)
      {
        b(this.y);
        this.y = null;
      }
      if (this.A != null)
      {
        this.A.cancel();
        this.A = null;
      }
      return;
    }
    finally {}
  }
  
  protected final byte x()
  {
    new ArrayList();
    if (this.e == null) {
      return 0;
    }
    List localList = this.e.getSensorList(-1);
    if (localList == null) {
      return 0;
    }
    return (byte)localList.size();
  }
  
  protected final Context y()
  {
    return this.a;
  }
}
