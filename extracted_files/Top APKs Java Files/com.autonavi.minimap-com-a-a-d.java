package com.a.a;

import aa;
import android.annotation.SuppressLint;
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
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
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
import java.util.List<*>;
import java.util.Set;
import java.util.TreeMap;
import p;

public final class d
{
  private static int C = 10000;
  private static d u = null;
  private Thread A = null;
  private Looper B = null;
  private Runnable D = null;
  private Handler E = null;
  public Context a = null;
  public SensorManager b = null;
  public String c = "";
  public boolean d = false;
  public long e = -1L;
  public int f = 0;
  public int g = 0;
  public int h = 0;
  private TelephonyManager i = null;
  private LocationManager j = null;
  private WifiManager k = null;
  private String l = "";
  private String m = "";
  private int n = 0;
  private boolean o = false;
  private String p = "";
  private String q = "";
  private String r = "";
  private long s = 0L;
  private long t = 0L;
  private a v = null;
  private b w = null;
  private CellLocation x = null;
  private c y = null;
  private List<ScanResult> z = new ArrayList();
  
  private d(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return;
      this.a = paramContext;
      this.c = Build.MODEL;
      this.i = ((TelephonyManager)paramContext.getSystemService("phone"));
      this.j = ((LocationManager)paramContext.getSystemService("location"));
      this.k = ((WifiManager)paramContext.getSystemService("wifi"));
      this.b = ((SensorManager)paramContext.getSystemService("sensor"));
    } while ((this.i == null) || (this.k == null));
    try
    {
      this.l = this.i.getDeviceId();
      this.m = this.i.getSubscriberId();
      if (this.k.getConnectionInfo() != null)
      {
        this.q = this.k.getConnectionInfo().getMacAddress();
        if ((this.q != null) && (this.q.length() > 0)) {
          this.q = this.q.replace(":", "");
        }
      }
      String[] arrayOfString = b(this.i);
      this.f = Integer.parseInt(arrayOfString[0]);
      this.g = Integer.parseInt(arrayOfString[1]);
      this.h = this.i.getNetworkType();
      this.r = paramContext.getPackageName();
      if (this.i.getPhoneType() == 2) {}
      for (boolean bool = true;; bool = false)
      {
        this.d = bool;
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private static int a(CellLocation paramCellLocation, Context paramContext)
  {
    if (Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) == 1) {}
    while (paramCellLocation == null) {
      return 9;
    }
    if ((paramCellLocation instanceof GsmCellLocation)) {
      return 1;
    }
    try
    {
      Class.forName("android.telephony.cdma.CdmaCellLocation");
      return 2;
    }
    catch (Exception paramCellLocation) {}
    return 9;
  }
  
  public static int a(Object paramObject)
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
    catch (Exception paramObject) {}
    return 0;
  }
  
  public static d a(Context paramContext)
  {
    if ((u == null) && (c(paramContext)))
    {
      Object localObject = (LocationManager)paramContext.getSystemService("location");
      if (localObject == null) {
        break label97;
      }
      localObject = ((LocationManager)localObject).getAllProviders().iterator();
      String str;
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        str = (String)((Iterator)localObject).next();
      } while ((!str.equals("passive")) && (!str.equals("gps")));
    }
    label97:
    for (int i1 = 1;; i1 = 0)
    {
      if (i1 != 0) {
        u = new d(paramContext);
      }
      return u;
    }
  }
  
  public static void a(int paramInt)
  {
    if (paramInt == C) {}
    while (paramInt < 5000) {
      return;
    }
    C = paramInt;
  }
  
  private void a(BroadcastReceiver paramBroadcastReceiver)
  {
    if ((paramBroadcastReceiver == null) || (this.a == null)) {
      return;
    }
    try
    {
      this.a.unregisterReceiver(paramBroadcastReceiver);
      return;
    }
    catch (Exception paramBroadcastReceiver) {}
  }
  
  private static void a(List<ScanResult> paramList)
  {
    if ((paramList == null) || (paramList.size() <= 0)) {
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
      paramList.add(((TreeMap)localObject).get(localIterator.next()));
    }
    localHashMap.clear();
    ((TreeMap)localObject).clear();
  }
  
  public static boolean a(Location paramLocation)
  {
    if (paramLocation == null) {
      return false;
    }
    for (;;)
    {
      try
      {
        Method localMethod = Location.class.getMethod("isFromMockProvider", null);
        if (localMethod != null)
        {
          bool = ((Boolean)localMethod.invoke(paramLocation, null)).booleanValue();
          return bool;
        }
      }
      catch (Exception paramLocation)
      {
        return false;
      }
      boolean bool = false;
    }
  }
  
  private boolean a(CellLocation paramCellLocation)
  {
    if (paramCellLocation == null) {
      return false;
    }
    switch (a(paramCellLocation, this.a))
    {
    }
    for (;;)
    {
      return true;
      paramCellLocation = (GsmCellLocation)paramCellLocation;
      if ((paramCellLocation.getLac() == -1) || (paramCellLocation.getLac() == 0) || (paramCellLocation.getLac() > 65535) || (paramCellLocation.getCid() == -1) || (paramCellLocation.getCid() == 0) || (paramCellLocation.getCid() == 65535)) {
        break;
      }
      if (paramCellLocation.getCid() >= 268435455)
      {
        return false;
        try
        {
          if ((aa.b(paramCellLocation, "getSystemId", new Object[0]) <= 0) || (aa.b(paramCellLocation, "getNetworkId", new Object[0]) < 0)) {
            break;
          }
          int i1 = aa.b(paramCellLocation, "getBaseStationId", new Object[0]);
          if (i1 < 0) {
            return false;
          }
        }
        catch (Exception paramCellLocation) {}
      }
    }
  }
  
  private static CellLocation b(List<?> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty()))
    {
      paramList = null;
      return paramList;
    }
    ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
    Object localObject1 = null;
    int i2 = 0;
    int i1 = 0;
    Object localObject2 = null;
    Object localObject5;
    if (i2 < paramList.size())
    {
      localObject5 = paramList.get(i2);
      if (localObject5 != null)
      {
        for (;;)
        {
          try
          {
            localClass2 = localClassLoader.loadClass("android.telephony.CellInfoGsm");
            localClass3 = localClassLoader.loadClass("android.telephony.CellInfoWcdma");
            localClass4 = localClassLoader.loadClass("android.telephony.CellInfoLte");
            localClass1 = localClassLoader.loadClass("android.telephony.CellInfoCdma");
            bool = localClass2.isInstance(localObject5);
            if (bool)
            {
              i1 = 1;
              if (i1 <= 0) {
                continue;
              }
              localObject4 = null;
              if (i1 != 1) {
                continue;
              }
            }
          }
          catch (Exception localException2)
          {
            Class localClass2;
            Class localClass3;
            Class localClass4;
            Class localClass1;
            boolean bool;
            Object localObject4;
            label152:
            continue;
          }
          try
          {
            localObject4 = localClass2.cast(localObject5);
            localObject5 = aa.a(localObject4, "getCellIdentity", new Object[0]);
            if (localObject5 != null) {
              continue;
            }
          }
          catch (Exception localException3)
          {
            continue;
            continue;
            continue;
          }
          i2 += 1;
          break;
          if (localClass3.isInstance(localObject5))
          {
            i1 = 2;
          }
          else if (localClass4.isInstance(localObject5))
          {
            i1 = 3;
          }
          else
          {
            bool = localClass1.isInstance(localObject5);
            if (bool)
            {
              i1 = 4;
            }
            else
            {
              i1 = 0;
              continue;
              if (i1 == 2) {
                localObject4 = localClass3.cast(localObject5);
              } else if (i1 == 3) {
                localObject4 = localClass4.cast(localObject5);
              } else if (i1 == 4) {
                localObject4 = localClass1.cast(localObject5);
              }
            }
          }
        }
        if (i1 == 4) {
          localObject4 = new CdmaCellLocation();
        }
      }
    }
    for (;;)
    {
      Object localObject3;
      try
      {
        i3 = aa.b(localObject5, "getSystemId", new Object[0]);
        i4 = aa.b(localObject5, "getNetworkId", new Object[0]);
        int i5 = aa.b(localObject5, "getBasestationId", new Object[0]);
        int i6 = aa.b(localObject5, "getLongitude", new Object[0]);
        ((CdmaCellLocation)localObject4).setCellLocationData(i5, aa.b(localObject5, "getLatitude", new Object[0]), i6, i3, i4);
        paramList = (List<?>)localObject4;
        if (i1 == 4) {
          break;
        }
        return localObject1;
      }
      catch (Exception localException1)
      {
        int i3;
        int i4;
        localObject3 = localException3;
      }
      if (i1 == 3)
      {
        i3 = aa.b(localObject5, "getTac", new Object[0]);
        i4 = aa.b(localObject5, "getCi", new Object[0]);
        localObject4 = new GsmCellLocation();
        localObject1 = localObject4;
      }
      try
      {
        ((GsmCellLocation)localObject4).setLacAndCid(i3, i4);
        paramList = localObject2;
        localObject1 = localObject4;
      }
      catch (Exception localException4) {}
      i3 = aa.b(localObject5, "getLac", new Object[0]);
      i4 = aa.b(localObject5, "getCid", new Object[0]);
      localObject4 = new GsmCellLocation();
      localObject1 = localObject4;
      ((GsmCellLocation)localObject4).setLacAndCid(i3, i4);
      paramList = localObject2;
      localObject1 = localObject4;
      continue;
      break label152;
      break label152;
      paramList = localObject3;
    }
  }
  
  private static void b(WifiManager paramWifiManager)
  {
    if (paramWifiManager == null) {}
    while (!paramWifiManager.isWifiEnabled()) {
      return;
    }
    try
    {
      aa.a(paramWifiManager, "startScanActive", new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      paramWifiManager.startScan();
    }
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext == null) {
      return true;
    }
    PackageManager localPackageManager;
    Object localObject;
    boolean bool1;
    if (!Settings.Secure.getString(paramContext.getContentResolver(), "mock_location").equals("0"))
    {
      localPackageManager = paramContext.getPackageManager();
      localObject = localPackageManager.getInstalledApplications(128);
      paramContext = paramContext.getPackageName();
      localObject = ((List)localObject).iterator();
      bool1 = false;
    }
    for (;;)
    {
      boolean bool2 = bool1;
      ApplicationInfo localApplicationInfo;
      if (((Iterator)localObject).hasNext())
      {
        localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        bool2 = bool1;
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
          bool2 = false;
          return bool2;
        }
      }
      catch (Exception localException) {}
    }
  }
  
  private static boolean b(Object paramObject)
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
    catch (Exception paramObject) {}
    return false;
  }
  
  private static String[] b(TelephonyManager paramTelephonyManager)
  {
    int i1 = 0;
    String str = null;
    if (paramTelephonyManager != null) {
      str = paramTelephonyManager.getNetworkOperator();
    }
    paramTelephonyManager = new String[2];
    paramTelephonyManager[0] = "0";
    paramTelephonyManager[1] = "0";
    if ((TextUtils.isDigitsOnly(str)) && (str.length() > 4))
    {
      paramTelephonyManager[0] = str.substring(0, 3);
      char[] arrayOfChar = str.substring(3).toCharArray();
      while ((i1 < arrayOfChar.length) && (Character.isDigit(arrayOfChar[i1]))) {
        i1 += 1;
      }
      paramTelephonyManager[1] = str.substring(3, i1 + 3);
    }
    return paramTelephonyManager;
  }
  
  private static boolean c(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 4096);
      paramContext = paramContext.requestedPermissions;
      int i1 = 0;
      while (i1 < p.e.length)
      {
        if (!p.a(paramContext, p.e[i1])) {
          return false;
        }
        i1 += 1;
      }
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private CellLocation r()
  {
    if (this.i == null) {
      return null;
    }
    try
    {
      CellLocation localCellLocation = b((List)aa.a(this.i, "getAllCellInfo", new Object[0]));
      return localCellLocation;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        Object localObject1 = null;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2 = null;
      }
    }
  }
  
  public final List<Object> a(float paramFloat)
  {
    ArrayList localArrayList = new ArrayList();
    long l1 = System.currentTimeMillis();
    float f1 = paramFloat;
    if (Math.abs(paramFloat) <= 1.0F) {
      f1 = 1.0F;
    }
    if (c())
    {
      CellLocation localCellLocation = (CellLocation)h().get(1);
      if ((localCellLocation != null) && ((localCellLocation instanceof GsmCellLocation)))
      {
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localCellLocation).getLac()));
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localCellLocation).getCid()));
        if (l1 - ((Long)h().get(0)).longValue() > 50000.0D / f1) {
          break label143;
        }
        localArrayList.add(Integer.valueOf(1));
      }
    }
    return localArrayList;
    label143:
    localArrayList.add(Integer.valueOf(0));
    return localArrayList;
  }
  
  public final List<Object> a(boolean paramBoolean)
  {
    int i2 = 0;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2;
    int i1;
    if (d())
    {
      localArrayList2 = new ArrayList();
      if (!paramBoolean) {}
      try
      {
        if (System.currentTimeMillis() - this.s >= 3500L) {
          break label145;
        }
        i1 = 1;
      }
      finally {}
      localArrayList2.add(Long.valueOf(this.s));
      i1 = i2;
      while (i1 < this.z.size())
      {
        localArrayList1.add(this.z.get(i1));
        i1 += 1;
      }
      localArrayList2.add(localArrayList1);
    }
    label145:
    label148:
    for (;;)
    {
      return localArrayList2;
      return new ArrayList();
      for (;;)
      {
        if (i1 == 0) {
          break label148;
        }
        break;
        i1 = 0;
      }
    }
  }
  
  public final void a()
  {
    b();
    this.A = new Thread("startListenThread")
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: invokestatic 26	android/os/Looper:prepare	()V
        //   3: aload_0
        //   4: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   7: invokestatic 30	android/os/Looper:myLooper	()Landroid/os/Looper;
        //   10: invokestatic 33	com/a/a/d:a	(Lcom/a/a/d;Landroid/os/Looper;)Landroid/os/Looper;
        //   13: pop
        //   14: aload_0
        //   15: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   18: new 35	com/a/a/d$a
        //   21: dup
        //   22: aload_0
        //   23: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   26: iconst_0
        //   27: invokespecial 38	com/a/a/d$a:<init>	(Lcom/a/a/d;B)V
        //   30: invokestatic 41	com/a/a/d:a	(Lcom/a/a/d;Lcom/a/a/d$a;)Lcom/a/a/d$a;
        //   33: pop
        //   34: aload_0
        //   35: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   38: aload_0
        //   39: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   42: invokestatic 44	com/a/a/d:a	(Lcom/a/a/d;)Lcom/a/a/d$a;
        //   45: invokestatic 47	com/a/a/d:a	(Lcom/a/a/d;Landroid/telephony/PhoneStateListener;)V
        //   48: aload_0
        //   49: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   52: new 49	com/a/a/d$b
        //   55: dup
        //   56: aload_0
        //   57: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   60: iconst_0
        //   61: invokespecial 50	com/a/a/d$b:<init>	(Lcom/a/a/d;B)V
        //   64: invokestatic 53	com/a/a/d:a	(Lcom/a/a/d;Lcom/a/a/d$b;)Lcom/a/a/d$b;
        //   67: pop
        //   68: aload_0
        //   69: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   72: aload_0
        //   73: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   76: invokestatic 57	com/a/a/d:b	(Lcom/a/a/d;)Lcom/a/a/d$b;
        //   79: invokestatic 60	com/a/a/d:a	(Lcom/a/a/d;Landroid/location/GpsStatus$NmeaListener;)V
        //   82: invokestatic 63	android/os/Looper:loop	()V
        //   85: aload_0
        //   86: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   89: invokevirtual 65	com/a/a/d:b	()V
        //   92: return
        //   93: astore_1
        //   94: aload_0
        //   95: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   98: invokevirtual 65	com/a/a/d:b	()V
        //   101: return
        //   102: astore_1
        //   103: aload_0
        //   104: getfield 14	com/a/a/d$1:a	Lcom/a/a/d;
        //   107: invokevirtual 65	com/a/a/d:b	()V
        //   110: aload_1
        //   111: athrow
        //   112: astore_1
        //   113: goto -31 -> 82
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	116	0	this	1
        //   93	1	1	localException1	Exception
        //   102	9	1	localObject	Object
        //   112	1	1	localException2	Exception
        // Exception table:
        //   from	to	target	type
        //   0	68	93	java/lang/Exception
        //   82	85	93	java/lang/Exception
        //   0	68	102	finally
        //   68	82	102	finally
        //   82	85	102	finally
        //   68	82	112	java/lang/Exception
      }
    };
    this.A.start();
  }
  
  public final String b(int paramInt)
  {
    new ArrayList();
    if (this.b == null) {
      return "null";
    }
    List localList = this.b.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getName() == null) || (((Sensor)localList.get(paramInt)).getName().length() <= 0)) {
      return "null";
    }
    return ((Sensor)localList.get(paramInt)).getName();
  }
  
  public final List<Integer> b(float paramFloat)
  {
    ArrayList localArrayList = new ArrayList();
    long l1 = System.currentTimeMillis();
    float f1 = paramFloat;
    if (Math.abs(paramFloat) <= 1.0F) {
      f1 = 1.0F;
    }
    if (c())
    {
      Object localObject = (CellLocation)h().get(1);
      if ((localObject != null) && ((localObject instanceof CdmaCellLocation)))
      {
        localObject = (CdmaCellLocation)localObject;
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getSystemId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getNetworkId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationLongitude()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationLatitude()));
        if (l1 - ((Long)h().get(0)).longValue() > 50000.0D / f1) {
          break label192;
        }
        localArrayList.add(Integer.valueOf(1));
      }
    }
    return localArrayList;
    label192:
    localArrayList.add(Integer.valueOf(0));
    return localArrayList;
  }
  
  public final void b()
  {
    try
    {
      Object localObject;
      if (this.v != null)
      {
        localObject = this.v;
        if (this.i != null) {
          this.i.listen((PhoneStateListener)localObject, 0);
        }
        this.v = null;
      }
      if (this.w != null)
      {
        localObject = this.w;
        if ((this.j != null) && (localObject != null)) {
          break label98;
        }
      }
      for (;;)
      {
        this.w = null;
        localObject = this.B;
        if (localObject != null)
        {
          ((Looper)localObject).quit();
          this.B = null;
        }
        localObject = this.A;
        if (localObject == null) {
          break;
        }
        ((Thread)localObject).interrupt();
        this.A = null;
        return;
        label98:
        this.j.removeNmeaListener((GpsStatus.NmeaListener)localObject);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public final String c(int paramInt)
  {
    new ArrayList();
    if (this.b == null) {
      return "null";
    }
    List localList = this.b.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getVendor() == null) || (((Sensor)localList.get(paramInt)).getVendor().length() <= 0)) {
      return "null";
    }
    return ((Sensor)localList.get(paramInt)).getVendor();
  }
  
  public final boolean c()
  {
    Object localObject = null;
    if ((this.i != null) && (this.i.getSimState() == 5) && (this.o)) {
      return true;
    }
    if (this.i != null) {}
    try
    {
      CellLocation localCellLocation = this.i.getCellLocation();
      localObject = localCellLocation;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if (localObject != null)
    {
      this.t = System.currentTimeMillis();
      this.x = localObject;
      return true;
    }
    return false;
  }
  
  public final boolean d()
  {
    return (this.k != null) && ((this.k.isWifiEnabled()) || (b(this.k)));
  }
  
  public final boolean e()
  {
    try
    {
      if (this.j != null)
      {
        boolean bool = this.j.isProviderEnabled("gps");
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  public final String f()
  {
    if ((this.l == null) && (this.a != null))
    {
      this.i = ((TelephonyManager)this.a.getSystemService("phone"));
      if (this.i == null) {}
    }
    try
    {
      this.l = this.i.getDeviceId();
      if (this.l != null) {
        return this.l;
      }
      return "";
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public final String g()
  {
    if ((this.m == null) && (this.a != null))
    {
      this.i = ((TelephonyManager)this.a.getSystemService("phone"));
      if (this.i != null) {
        this.m = this.i.getSubscriberId();
      }
    }
    if (this.m != null) {
      return this.m;
    }
    return "";
  }
  
  public final List<Object> h()
  {
    if (Settings.System.getInt(this.a.getContentResolver(), "airplane_mode_on", 0) == 1) {
      return new ArrayList();
    }
    if (c())
    {
      ArrayList localArrayList = new ArrayList();
      CellLocation localCellLocation;
      if (!a(this.x))
      {
        localCellLocation = r();
        if (a(localCellLocation)) {
          this.t = System.currentTimeMillis();
        }
      }
      for (;;)
      {
        localArrayList.add(Long.valueOf(this.t));
        localArrayList.add(localCellLocation);
        return localArrayList;
        localCellLocation = this.x;
      }
    }
    return new ArrayList();
  }
  
  public final byte i()
  {
    if (c()) {
      return (byte)this.n;
    }
    return Byte.MIN_VALUE;
  }
  
  public final List<NeighboringCellInfo> j()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.i == null) {
      return localArrayList;
    }
    if (!c()) {
      return localArrayList;
    }
    if (this.i.getSimState() == 1) {
      return localArrayList;
    }
    Iterator localIterator = this.i.getNeighboringCellInfo().iterator();
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
  
  public final List<Object> k()
  {
    ArrayList localArrayList = new ArrayList();
    long l2;
    if (e()) {
      l2 = this.e;
    }
    for (String str = this.p;; str = "")
    {
      long l1 = l2;
      if (l2 <= 0L) {
        l1 = System.currentTimeMillis() / 1000L;
      }
      l2 = l1;
      if (l1 > 2147483647L) {
        l2 = l1 / 1000L;
      }
      localArrayList.add(Long.valueOf(l2));
      localArrayList.add(str);
      return localArrayList;
      l2 = -1L;
    }
  }
  
  public final String l()
  {
    if ((this.q == null) && (this.a != null))
    {
      this.k = ((WifiManager)this.a.getSystemService("wifi"));
      if ((this.k != null) && (this.k.getConnectionInfo() != null))
      {
        this.q = this.k.getConnectionInfo().getMacAddress();
        if ((this.q != null) && (this.q.length() > 0)) {
          this.q = this.q.replace(":", "");
        }
      }
    }
    if (this.q != null) {
      return this.q;
    }
    return "";
  }
  
  public final String m()
  {
    if ((this.r == null) && (this.a != null)) {
      this.r = this.a.getPackageName();
    }
    if (this.r != null) {
      return this.r;
    }
    return "";
  }
  
  @SuppressLint({"NewApi"})
  public final List<Object> n()
  {
    ArrayList localArrayList1 = new ArrayList();
    int i2 = Build.VERSION.SDK_INT;
    Object localObject;
    int i1;
    ArrayList localArrayList2;
    if (d())
    {
      localObject = a(true);
      List localList = (List)((List)localObject).get(1);
      long l1 = ((Long)((List)localObject).get(0)).longValue();
      a(localList);
      localArrayList1.add(Long.valueOf(l1));
      if ((localList != null) && (localList.size() > 0))
      {
        i1 = 0;
        if (i1 < localList.size())
        {
          localObject = (ScanResult)localList.get(i1);
          if (localArrayList1.size() - 1 < 40) {
            if (localObject != null)
            {
              localArrayList2 = new ArrayList();
              localArrayList2.add(((ScanResult)localObject).BSSID.replace(":", ""));
              localArrayList2.add(Integer.valueOf(((ScanResult)localObject).level));
              localArrayList2.add(((ScanResult)localObject).SSID);
              if (i2 <= 17) {
                break label278;
              }
              short s2 = (short)(int)(SystemClock.elapsedRealtimeNanos() / 1000000000L - ((ScanResult)localObject).timestamp / 1000000L + 1L);
              s1 = s2;
              if (s2 >= 0) {}
            }
          }
        }
      }
    }
    label278:
    for (short s1 = 0;; s1 = 0)
    {
      localArrayList2.add(Short.valueOf(s1));
      localArrayList2.add(Integer.valueOf(((ScanResult)localObject).frequency));
      localArrayList1.add(localArrayList2);
      i1 += 1;
      break;
      return localArrayList1;
    }
  }
  
  public final void o()
  {
    for (;;)
    {
      try
      {
        this.z.clear();
        if (this.y != null)
        {
          a(this.y);
          this.y = null;
        }
        if (this.E != null)
        {
          this.E.removeCallbacks(this.D);
          this.E = null;
        }
        this.y = new c((byte)0);
        c localC = this.y;
        if (((localC != null) && (this.a != null)) || (this.k != null)) {}
        localIntentFilter = new IntentFilter();
      }
      finally
      {
        try
        {
          if (p.a) {
            b(this.k);
          }
          return;
        }
        catch (Exception localException) {}
        localBroadcastReceiver = finally;
      }
      IntentFilter localIntentFilter;
      localIntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
      this.a.registerReceiver(localBroadcastReceiver, localIntentFilter);
    }
  }
  
  public final void p()
  {
    try
    {
      this.z.clear();
      if (this.y != null)
      {
        a(this.y);
        this.y = null;
      }
      if (this.E != null)
      {
        this.E.removeCallbacks(this.D);
        this.E = null;
      }
      return;
    }
    finally {}
  }
  
  final class a
    extends PhoneStateListener
  {
    private a() {}
    
    public final void onCellLocationChanged(CellLocation paramCellLocation)
    {
      try
      {
        d.b(d.this, System.currentTimeMillis());
        d.a(d.this, paramCellLocation);
        super.onCellLocationChanged(paramCellLocation);
        return;
      }
      catch (Exception paramCellLocation) {}
    }
    
    public final void onServiceStateChanged(ServiceState paramServiceState)
    {
      try
      {
        if (paramServiceState.getState() == 0)
        {
          d.a(d.this, true);
          String[] arrayOfString = d.a(d.g(d.this));
          d.a(d.this, Integer.parseInt(arrayOfString[0]));
          d.b(d.this, Integer.parseInt(arrayOfString[1]));
        }
        for (;;)
        {
          super.onServiceStateChanged(paramServiceState);
          return;
          d.a(d.this, false);
        }
        return;
      }
      catch (Exception paramServiceState) {}
    }
    
    public final void onSignalStrengthsChanged(SignalStrength paramSignalStrength)
    {
      try
      {
        if (d.h(d.this)) {
          d.c(d.this, paramSignalStrength.getCdmaDbm());
        }
        for (;;)
        {
          super.onSignalStrengthsChanged(paramSignalStrength);
          return;
          d.c(d.this, paramSignalStrength.getGsmSignalStrength());
          if (d.i(d.this) == 99) {
            d.c(d.this, -1);
          } else {
            d.c(d.this, d.i(d.this) * 2 - 113);
          }
        }
        return;
      }
      catch (Exception paramSignalStrength) {}
    }
  }
  
  final class b
    implements GpsStatus.NmeaListener
  {
    private b() {}
    
    public final void onNmeaReceived(long paramLong, String paramString)
    {
      try
      {
        d.c(d.this, paramLong);
        d.a(d.this, paramString);
        return;
      }
      catch (Exception paramString) {}
    }
  }
  
  class c
    extends BroadcastReceiver
  {
    private c() {}
    
    /* Error */
    public void onReceive(Context paramContext, android.content.Intent paramIntent)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +249 -> 250
      //   4: aload_2
      //   5: ifnull +245 -> 250
      //   8: aload_0
      //   9: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   12: invokestatic 29	com/a/a/d:c	(Lcom/a/a/d;)Landroid/net/wifi/WifiManager;
      //   15: ifnull +235 -> 250
      //   18: aload_0
      //   19: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   22: invokestatic 33	com/a/a/d:d	(Lcom/a/a/d;)Ljava/util/List;
      //   25: ifnull +225 -> 250
      //   28: aload_2
      //   29: invokevirtual 39	android/content/Intent:getAction	()Ljava/lang/String;
      //   32: ifnull +218 -> 250
      //   35: ldc 41
      //   37: aload_2
      //   38: invokevirtual 39	android/content/Intent:getAction	()Ljava/lang/String;
      //   41: invokevirtual 47	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   44: ifeq +206 -> 250
      //   47: aload_0
      //   48: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   51: invokestatic 29	com/a/a/d:c	(Lcom/a/a/d;)Landroid/net/wifi/WifiManager;
      //   54: invokevirtual 53	android/net/wifi/WifiManager:getScanResults	()Ljava/util/List;
      //   57: astore_1
      //   58: aload_0
      //   59: monitorenter
      //   60: aload_0
      //   61: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   64: invokestatic 33	com/a/a/d:d	(Lcom/a/a/d;)Ljava/util/List;
      //   67: invokeinterface 58 1 0
      //   72: aload_0
      //   73: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   76: invokestatic 64	java/lang/System:currentTimeMillis	()J
      //   79: invokestatic 67	com/a/a/d:a	(Lcom/a/a/d;J)J
      //   82: pop2
      //   83: aload_1
      //   84: ifnull +56 -> 140
      //   87: aload_1
      //   88: invokeinterface 71 1 0
      //   93: ifle +47 -> 140
      //   96: iconst_0
      //   97: istore_3
      //   98: iload_3
      //   99: aload_1
      //   100: invokeinterface 71 1 0
      //   105: if_icmpge +35 -> 140
      //   108: aload_1
      //   109: iload_3
      //   110: invokeinterface 75 2 0
      //   115: checkcast 77	android/net/wifi/ScanResult
      //   118: astore_2
      //   119: aload_0
      //   120: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   123: invokestatic 33	com/a/a/d:d	(Lcom/a/a/d;)Ljava/util/List;
      //   126: aload_2
      //   127: invokeinterface 80 2 0
      //   132: pop
      //   133: iload_3
      //   134: iconst_1
      //   135: iadd
      //   136: istore_3
      //   137: goto -39 -> 98
      //   140: aload_0
      //   141: monitorexit
      //   142: aload_0
      //   143: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   146: invokestatic 84	com/a/a/d:e	(Lcom/a/a/d;)Landroid/os/Handler;
      //   149: ifnull +24 -> 173
      //   152: aload_0
      //   153: monitorenter
      //   154: aload_0
      //   155: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   158: invokestatic 84	com/a/a/d:e	(Lcom/a/a/d;)Landroid/os/Handler;
      //   161: aload_0
      //   162: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   165: invokestatic 88	com/a/a/d:f	(Lcom/a/a/d;)Ljava/lang/Runnable;
      //   168: invokevirtual 94	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
      //   171: aload_0
      //   172: monitorexit
      //   173: aload_0
      //   174: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   177: invokestatic 88	com/a/a/d:f	(Lcom/a/a/d;)Ljava/lang/Runnable;
      //   180: ifnonnull +19 -> 199
      //   183: aload_0
      //   184: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   187: new 9	com/a/a/d$c$1
      //   190: dup
      //   191: aload_0
      //   192: invokespecial 97	com/a/a/d$c$1:<init>	(Lcom/a/a/d$c;)V
      //   195: invokestatic 100	com/a/a/d:a	(Lcom/a/a/d;Ljava/lang/Runnable;)Ljava/lang/Runnable;
      //   198: pop
      //   199: aload_0
      //   200: monitorenter
      //   201: aload_0
      //   202: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   205: invokestatic 84	com/a/a/d:e	(Lcom/a/a/d;)Landroid/os/Handler;
      //   208: ifnonnull +18 -> 226
      //   211: aload_0
      //   212: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   215: new 90	android/os/Handler
      //   218: dup
      //   219: invokespecial 101	android/os/Handler:<init>	()V
      //   222: invokestatic 104	com/a/a/d:a	(Lcom/a/a/d;Landroid/os/Handler;)Landroid/os/Handler;
      //   225: pop
      //   226: aload_0
      //   227: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   230: invokestatic 84	com/a/a/d:e	(Lcom/a/a/d;)Landroid/os/Handler;
      //   233: aload_0
      //   234: getfield 15	com/a/a/d$c:a	Lcom/a/a/d;
      //   237: invokestatic 88	com/a/a/d:f	(Lcom/a/a/d;)Ljava/lang/Runnable;
      //   240: invokestatic 107	com/a/a/d:q	()I
      //   243: i2l
      //   244: invokevirtual 111	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
      //   247: pop
      //   248: aload_0
      //   249: monitorexit
      //   250: return
      //   251: astore_1
      //   252: aload_0
      //   253: monitorexit
      //   254: aload_1
      //   255: athrow
      //   256: astore_1
      //   257: return
      //   258: astore_1
      //   259: aload_0
      //   260: monitorexit
      //   261: aload_1
      //   262: athrow
      //   263: astore_1
      //   264: aload_0
      //   265: monitorexit
      //   266: aload_1
      //   267: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	268	0	this	c
      //   0	268	1	paramContext	Context
      //   0	268	2	paramIntent	android.content.Intent
      //   97	40	3	i	int
      // Exception table:
      //   from	to	target	type
      //   60	83	251	finally
      //   87	96	251	finally
      //   98	133	251	finally
      //   140	142	251	finally
      //   252	254	251	finally
      //   8	60	256	java/lang/Exception
      //   142	154	256	java/lang/Exception
      //   173	199	256	java/lang/Exception
      //   199	201	256	java/lang/Exception
      //   254	256	256	java/lang/Exception
      //   261	263	256	java/lang/Exception
      //   266	268	256	java/lang/Exception
      //   154	173	258	finally
      //   259	261	258	finally
      //   201	226	263	finally
      //   226	250	263	finally
      //   264	266	263	finally
    }
  }
}
