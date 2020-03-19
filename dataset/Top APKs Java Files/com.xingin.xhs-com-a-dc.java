package com.a;

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

public final class dc
{
  private static dc D = null;
  private static String[] J = { "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE" };
  static int q = 10000;
  static String r = "";
  protected static boolean s = true;
  protected static boolean t = false;
  private String A = "";
  private long B = 0L;
  private long C = 0L;
  private de E = null;
  private df F = null;
  private CellLocation G = null;
  private Thread H = null;
  private Looper I = null;
  Context a = null;
  TelephonyManager b = null;
  WifiManager c = null;
  SensorManager d = null;
  String e = "";
  String f = "";
  boolean g = false;
  long h = -1L;
  String i = "";
  int j = 0;
  int k = 0;
  int l = 0;
  String m = "";
  dg n = null;
  List o = new ArrayList();
  Timer p = null;
  Object u = new Object();
  boolean v = false;
  private LocationManager w = null;
  private String x = "";
  private int y = 0;
  private boolean z = false;
  
  private dc(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return;
      this.a = paramContext;
      this.e = Build.MODEL;
      this.b = ((TelephonyManager)paramContext.getSystemService("phone"));
      this.w = ((LocationManager)paramContext.getSystemService("location"));
      this.c = ((WifiManager)paramContext.getSystemService("wifi"));
      this.d = ((SensorManager)paramContext.getSystemService("sensor"));
    } while ((this.b == null) || (this.c == null));
    try
    {
      this.x = this.b.getDeviceId();
      this.f = this.b.getSubscriberId();
      if (this.c.getConnectionInfo() != null)
      {
        this.i = this.c.getConnectionInfo().getMacAddress();
        if ((this.i != null) && (this.i.length() > 0)) {
          this.i = this.i.replace(":", "");
        }
      }
      String[] arrayOfString = b(this.b);
      this.j = Integer.parseInt(arrayOfString[0]);
      this.k = Integer.parseInt(arrayOfString[1]);
      this.l = this.b.getNetworkType();
      this.m = paramContext.getPackageName();
      if (this.b.getPhoneType() == 2) {}
      for (boolean bool = true;; bool = false)
      {
        this.g = bool;
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
  
  static int a(Object paramObject)
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
  
  protected static dc a(Context paramContext)
  {
    if ((D == null) && (c(paramContext)))
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
        D = new dc(paramContext);
      }
      return D;
    }
  }
  
  private static void a(List paramList)
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
      paramList.add(((TreeMap)localObject).get((Integer)localIterator.next()));
    }
    localHashMap.clear();
    ((TreeMap)localObject).clear();
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
          if ((cz.b(paramCellLocation, "getSystemId", new Object[0]) <= 0) || (cz.b(paramCellLocation, "getNetworkId", new Object[0]) < 0)) {
            break;
          }
          int i1 = cz.b(paramCellLocation, "getBaseStationId", new Object[0]);
          if (i1 < 0) {
            return false;
          }
        }
        catch (Exception paramCellLocation) {}
      }
    }
  }
  
  private static CellLocation b(List paramList)
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
            localObject5 = cz.a(localObject4, "getCellIdentity", new Object[0]);
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
        i3 = cz.b(localObject5, "getSystemId", new Object[0]);
        i4 = cz.b(localObject5, "getNetworkId", new Object[0]);
        int i5 = cz.b(localObject5, "getBasestationId", new Object[0]);
        int i6 = cz.b(localObject5, "getLongitude", new Object[0]);
        ((CdmaCellLocation)localObject4).setCellLocationData(i5, cz.b(localObject5, "getLatitude", new Object[0]), i6, i3, i4);
        paramList = (List)localObject4;
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
        i3 = cz.b(localObject5, "getTac", new Object[0]);
        i4 = cz.b(localObject5, "getCi", new Object[0]);
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
      i3 = cz.b(localObject5, "getLac", new Object[0]);
      i4 = cz.b(localObject5, "getCid", new Object[0]);
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
    if (paramWifiManager == null) {
      return;
    }
    try
    {
      cz.a(paramWifiManager, "startScanActive", new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      paramWifiManager.startScan();
    }
  }
  
  protected static boolean b(Context paramContext)
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
    Object localObject = paramContext.getPackageManager();
    try
    {
      paramContext = ((PackageManager)localObject).getPackageInfo(paramContext.getPackageName(), 4096);
      paramContext = paramContext.requestedPermissions;
      int i1 = 0;
      while (i1 < J.length)
      {
        localObject = J[i1];
        if ((paramContext != null) && (localObject != null))
        {
          i2 = 0;
          if (i2 < paramContext.length) {
            if (!paramContext[i2].equals(localObject)) {}
          }
        }
        for (int i2 = 1;; i2 = 0)
        {
          if (i2 != 0) {
            break label84;
          }
          return false;
          i2 += 1;
          break;
        }
        label84:
        i1 += 1;
      }
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private CellLocation p()
  {
    if (this.b == null) {
      return null;
    }
    try
    {
      CellLocation localCellLocation = b((List)cz.a(this.b, "getAllCellInfo", new Object[0]));
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
  
  protected final String a(int paramInt)
  {
    new ArrayList();
    if (this.d == null) {
      return "null";
    }
    List localList = this.d.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getName() == null) || (((Sensor)localList.get(paramInt)).getName().length() <= 0)) {
      return "null";
    }
    return ((Sensor)localList.get(paramInt)).getName();
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
      CellLocation localCellLocation = (CellLocation)g().get(1);
      if ((localCellLocation != null) && ((localCellLocation instanceof GsmCellLocation)))
      {
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localCellLocation).getLac()));
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localCellLocation).getCid()));
        if (l1 - ((Long)g().get(0)).longValue() > 50000.0D / f1) {
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
  
  protected final List a(boolean paramBoolean)
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
        if (System.currentTimeMillis() - this.B >= 3500L) {
          break label145;
        }
        i1 = 1;
      }
      finally {}
      localArrayList2.add(Long.valueOf(this.B));
      i1 = i2;
      while (i1 < this.o.size())
      {
        localArrayList1.add(this.o.get(i1));
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
  
  protected final void a()
  {
    b();
    this.v = true;
    this.H = new dd(this, "");
    this.H.start();
  }
  
  final void a(BroadcastReceiver paramBroadcastReceiver)
  {
    if ((paramBroadcastReceiver == null) || (this.a == null)) {
      return;
    }
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
    this.a.registerReceiver(paramBroadcastReceiver, localIntentFilter);
  }
  
  protected final String b(int paramInt)
  {
    new ArrayList();
    if (this.d == null) {
      return "null";
    }
    List localList = this.d.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getVendor() == null) || (((Sensor)localList.get(paramInt)).getVendor().length() <= 0)) {
      return "null";
    }
    return ((Sensor)localList.get(paramInt)).getVendor();
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
      Object localObject = (CellLocation)g().get(1);
      if ((localObject != null) && ((localObject instanceof CdmaCellLocation)))
      {
        localObject = (CdmaCellLocation)localObject;
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getSystemId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getNetworkId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationLongitude()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationLatitude()));
        if (l1 - ((Long)g().get(0)).longValue() > 50000.0D / f1) {
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
  
  protected final void b()
  {
    synchronized (this.u)
    {
      this.v = false;
      Object localObject2;
      if (this.E != null)
      {
        localObject2 = this.E;
        if (this.b != null) {
          this.b.listen((PhoneStateListener)localObject2, 0);
        }
        this.E = null;
      }
      if (this.F != null)
      {
        localObject2 = this.F;
        if ((this.w == null) || (localObject2 == null)) {
          this.F = null;
        }
      }
      else
      {
        if (this.p != null)
        {
          this.p.cancel();
          this.p = null;
        }
        if (this.I != null)
        {
          this.I.quit();
          this.I = null;
        }
        if (this.H != null)
        {
          this.H.interrupt();
          this.H = null;
        }
        return;
      }
      this.w.removeNmeaListener((GpsStatus.NmeaListener)localObject2);
    }
  }
  
  final void b(BroadcastReceiver paramBroadcastReceiver)
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
  
  protected final boolean c()
  {
    Object localObject = null;
    if ((this.b != null) && (this.b.getSimState() == 5) && (this.z)) {
      return true;
    }
    if (this.b != null) {}
    try
    {
      CellLocation localCellLocation = this.b.getCellLocation();
      localObject = localCellLocation;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if (localObject != null)
    {
      this.C = System.currentTimeMillis();
      this.G = localObject;
      return true;
    }
    return false;
  }
  
  protected final boolean d()
  {
    return (this.c != null) && ((this.c.isWifiEnabled()) || (b(this.c)));
  }
  
  protected final boolean e()
  {
    try
    {
      if (this.w != null)
      {
        boolean bool = this.w.isProviderEnabled("gps");
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  protected final String f()
  {
    if ((this.x == null) && (this.a != null))
    {
      this.b = ((TelephonyManager)this.a.getSystemService("phone"));
      if (this.b == null) {}
    }
    try
    {
      this.x = this.b.getDeviceId();
      if (this.x != null) {
        return this.x;
      }
      return "";
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  protected final List g()
  {
    if (Settings.System.getInt(this.a.getContentResolver(), "airplane_mode_on", 0) == 1) {
      return new ArrayList();
    }
    if (c())
    {
      ArrayList localArrayList = new ArrayList();
      CellLocation localCellLocation;
      if (!a(this.G))
      {
        localCellLocation = p();
        if (a(localCellLocation)) {
          this.C = System.currentTimeMillis();
        }
      }
      for (;;)
      {
        localArrayList.add(Long.valueOf(this.C));
        localArrayList.add(localCellLocation);
        return localArrayList;
        localCellLocation = this.G;
      }
    }
    return new ArrayList();
  }
  
  protected final byte h()
  {
    if (c()) {
      return (byte)this.y;
    }
    return Byte.MIN_VALUE;
  }
  
  protected final List i()
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
  
  protected final List j()
  {
    ArrayList localArrayList = new ArrayList();
    long l2;
    if (e()) {
      l2 = this.h;
    }
    for (String str = this.A;; str = "")
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
  
  protected final List k()
  {
    int i1 = 0;
    ArrayList localArrayList1 = new ArrayList();
    if (d())
    {
      Object localObject = a(true);
      List localList = (List)((List)localObject).get(1);
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
  
  protected final void l()
  {
    try
    {
      this.o.clear();
      if (this.n != null)
      {
        b(this.n);
        this.n = null;
      }
      if (this.p != null)
      {
        this.p.cancel();
        this.p = null;
      }
      this.p = new Timer();
      this.n = new dg(this, (byte)0);
      a(this.n);
      n();
      return;
    }
    finally {}
  }
  
  protected final void m()
  {
    try
    {
      this.o.clear();
      if (this.n != null)
      {
        b(this.n);
        this.n = null;
      }
      if (this.p != null)
      {
        this.p.cancel();
        this.p = null;
      }
      return;
    }
    finally {}
  }
  
  final void n()
  {
    if (this.c == null) {}
    for (;;)
    {
      return;
      try
      {
        if (s)
        {
          b(this.c);
          return;
        }
      }
      catch (Exception localException) {}
    }
  }
}
