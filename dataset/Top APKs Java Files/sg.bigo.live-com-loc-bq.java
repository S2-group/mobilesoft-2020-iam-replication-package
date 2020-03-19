package com.loc;

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

public final class bq
{
  private static int G;
  private static String[] H = { "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE" };
  private static bq r = null;
  protected static boolean x;
  protected static boolean y;
  static String z;
  private CellLocation A = null;
  private bu B = null;
  private List C = new ArrayList();
  private Timer D = null;
  private Thread E = null;
  private Looper F = null;
  private WifiManager a = null;
  private SensorManager b = null;
  private String c = "";
  private String d = "";
  private String e = "";
  private boolean f = false;
  private int g = 0;
  private boolean h = false;
  private long i = -1L;
  private String j = "";
  private String k = "";
  private int l = 0;
  private int m = 0;
  private int n = 0;
  private String o = "";
  private long p = 0L;
  private long q = 0L;
  private bs s = null;
  private bt t = null;
  private LocationManager u = null;
  private TelephonyManager v = null;
  private Context w = null;
  
  static
  {
    G = 10000;
    z = "";
    y = true;
    x = false;
  }
  
  private bq(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return;
      this.w = paramContext;
      this.c = Build.MODEL;
      this.v = ((TelephonyManager)paramContext.getSystemService("phone"));
      this.u = ((LocationManager)paramContext.getSystemService("location"));
      this.a = ((WifiManager)paramContext.getSystemService("wifi"));
      this.b = ((SensorManager)paramContext.getSystemService("sensor"));
    } while ((this.v == null) || (this.a == null));
    try
    {
      this.d = this.v.getDeviceId();
      this.e = this.v.getSubscriberId();
      if (this.a.getConnectionInfo() != null)
      {
        this.k = this.a.getConnectionInfo().getMacAddress();
        if ((this.k != null) && (this.k.length() > 0)) {
          this.k = this.k.replace(":", "");
        }
      }
      String[] arrayOfString = y(this.v);
      this.l = Integer.parseInt(arrayOfString[0]);
      this.m = Integer.parseInt(arrayOfString[1]);
      this.n = this.v.getNetworkType();
      this.o = paramContext.getPackageName();
      if (this.v.getPhoneType() == 2) {}
      for (boolean bool = true;; bool = false)
      {
        this.f = bool;
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private CellLocation A()
  {
    if (this.v == null) {
      return null;
    }
    try
    {
      CellLocation localCellLocation = y((List)bp.z(this.v, "getAllCellInfo", new Object[0]));
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
  
  private void t()
  {
    if (this.a == null) {}
    for (;;)
    {
      return;
      try
      {
        if (y)
        {
          z(this.a);
          return;
        }
      }
      catch (Exception localException) {}
    }
  }
  
  private static boolean x(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    try
    {
      paramContext = ((PackageManager)localObject).getPackageInfo(paramContext.getPackageName(), 4096);
      paramContext = paramContext.requestedPermissions;
      int i1 = 0;
      while (i1 < H.length)
      {
        localObject = H[i1];
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
  
  private static int y(Object paramObject)
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
  
  private static CellLocation y(List paramList)
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
            localObject5 = bp.z(localObject4, "getCellIdentity", new Object[0]);
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
        i3 = bp.y(localObject5, "getSystemId", new Object[0]);
        i4 = bp.y(localObject5, "getNetworkId", new Object[0]);
        int i5 = bp.y(localObject5, "getBasestationId", new Object[0]);
        int i6 = bp.y(localObject5, "getLongitude", new Object[0]);
        ((CdmaCellLocation)localObject4).setCellLocationData(i5, bp.y(localObject5, "getLatitude", new Object[0]), i6, i3, i4);
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
        i3 = bp.y(localObject5, "getTac", new Object[0]);
        i4 = bp.y(localObject5, "getCi", new Object[0]);
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
      i3 = bp.y(localObject5, "getLac", new Object[0]);
      i4 = bp.y(localObject5, "getCid", new Object[0]);
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
  
  private void y(BroadcastReceiver paramBroadcastReceiver)
  {
    if ((paramBroadcastReceiver == null) || (this.w == null)) {
      return;
    }
    try
    {
      this.w.unregisterReceiver(paramBroadcastReceiver);
      return;
    }
    catch (Exception paramBroadcastReceiver) {}
  }
  
  protected static boolean y(Context paramContext)
  {
    if (paramContext == null) {
      return true;
    }
    boolean bool2;
    if (!Settings.Secure.getString(paramContext.getContentResolver(), "mock_location").equals("0"))
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      Object localObject = localPackageManager.getInstalledApplications(128);
      paramContext = paramContext.getPackageName();
      localObject = ((List)localObject).iterator();
      boolean bool1 = false;
      bool2 = bool1;
      if (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        bool2 = bool1;
        if (!bool1) {
          for (;;)
          {
            try
            {
              String[] arrayOfString = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
              if (arrayOfString == null) {
                break;
              }
              int i2 = arrayOfString.length;
              i1 = 0;
              if (i1 >= i2) {
                break;
              }
              if (arrayOfString[i1].equals("android.permission.ACCESS_MOCK_LOCATION"))
              {
                bool2 = localApplicationInfo.packageName.equals(paramContext);
                if (bool2) {
                  break;
                }
                bool1 = true;
              }
            }
            catch (Exception localException)
            {
              int i1;
              continue;
            }
            i1 += 1;
          }
        }
      }
    }
    else
    {
      bool2 = false;
    }
    return bool2;
  }
  
  private static String[] y(TelephonyManager paramTelephonyManager)
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
  
  private static int z(CellLocation paramCellLocation, Context paramContext)
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
  
  protected static bq z(Context paramContext)
  {
    if ((r == null) && (x(paramContext)))
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
        r = new bq(paramContext);
      }
      return r;
    }
  }
  
  private void z(BroadcastReceiver paramBroadcastReceiver)
  {
    if ((paramBroadcastReceiver == null) || (this.w == null)) {
      return;
    }
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
    this.w.registerReceiver(paramBroadcastReceiver, localIntentFilter);
  }
  
  private static void z(WifiManager paramWifiManager)
  {
    if (paramWifiManager == null) {
      return;
    }
    try
    {
      bp.z(paramWifiManager, "startScanActive", new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      paramWifiManager.startScan();
    }
  }
  
  private static void z(List paramList)
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
  
  private boolean z(CellLocation paramCellLocation)
  {
    if (paramCellLocation == null) {
      return false;
    }
    switch (z(paramCellLocation, this.w))
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
          if ((bp.y(paramCellLocation, "getSystemId", new Object[0]) <= 0) || (bp.y(paramCellLocation, "getNetworkId", new Object[0]) < 0)) {
            break;
          }
          int i1 = bp.y(paramCellLocation, "getBaseStationId", new Object[0]);
          if (i1 < 0) {
            return false;
          }
        }
        catch (Exception paramCellLocation) {}
      }
    }
  }
  
  private static boolean z(Object paramObject)
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
  
  protected final byte a(int paramInt)
  {
    new ArrayList();
    if (this.b == null) {
      return Byte.MAX_VALUE;
    }
    List localList = this.b.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getType() > 127)) {
      return Byte.MAX_VALUE;
    }
    return (byte)((Sensor)localList.get(paramInt)).getType();
  }
  
  protected final String a()
  {
    if ((this.d == null) && (this.w != null))
    {
      this.v = ((TelephonyManager)this.w.getSystemService("phone"));
      if (this.v == null) {}
    }
    try
    {
      this.d = this.v.getDeviceId();
      if (this.d != null) {
        return this.d;
      }
      return "";
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  protected final String b()
  {
    if ((this.e == null) && (this.w != null))
    {
      this.v = ((TelephonyManager)this.w.getSystemService("phone"));
      if (this.v != null) {
        this.e = this.v.getSubscriberId();
      }
    }
    if (this.e != null) {
      return this.e;
    }
    return "";
  }
  
  protected final String b(int paramInt)
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
  
  protected final byte c(int paramInt)
  {
    new ArrayList();
    if (this.b == null) {
      return Byte.MAX_VALUE;
    }
    List localList = this.b.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getType() > 127)) {
      return Byte.MAX_VALUE;
    }
    return (byte)((Sensor)localList.get(paramInt)).getVersion();
  }
  
  protected final boolean c()
  {
    return this.f;
  }
  
  protected final List d()
  {
    if (Settings.System.getInt(this.w.getContentResolver(), "airplane_mode_on", 0) == 1) {
      return new ArrayList();
    }
    if (x())
    {
      ArrayList localArrayList = new ArrayList();
      CellLocation localCellLocation;
      if (!z(this.A))
      {
        localCellLocation = A();
        if (z(localCellLocation)) {
          this.q = System.currentTimeMillis();
        }
      }
      for (;;)
      {
        localArrayList.add(Long.valueOf(this.q));
        localArrayList.add(localCellLocation);
        return localArrayList;
        localCellLocation = this.A;
      }
    }
    return new ArrayList();
  }
  
  protected final byte e()
  {
    if (x()) {
      return (byte)this.g;
    }
    return Byte.MIN_VALUE;
  }
  
  protected final List f()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.v == null) {
      return localArrayList;
    }
    if (!x()) {
      return localArrayList;
    }
    if (this.v.getSimState() == 1) {
      return localArrayList;
    }
    Iterator localIterator = this.v.getNeighboringCellInfo().iterator();
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
  
  protected final List g()
  {
    ArrayList localArrayList = new ArrayList();
    long l2;
    if (v()) {
      l2 = this.i;
    }
    for (String str = this.j;; str = "")
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
  
  protected final long h()
  {
    long l2 = 0L;
    long l1 = this.i;
    if (l1 <= 0L) {}
    int i1;
    do
    {
      return l2;
      i1 = String.valueOf(l1).length();
      l2 = l1;
    } while (i1 == 13);
    if (i1 > 13) {}
    for (l1 /= 10L;; l1 *= 10L)
    {
      i1 = String.valueOf(l1).length();
      break;
    }
  }
  
  protected final String i()
  {
    if ((this.k == null) && (this.w != null))
    {
      this.a = ((WifiManager)this.w.getSystemService("wifi"));
      if ((this.a != null) && (this.a.getConnectionInfo() != null))
      {
        this.k = this.a.getConnectionInfo().getMacAddress();
        if ((this.k != null) && (this.k.length() > 0)) {
          this.k = this.k.replace(":", "");
        }
      }
    }
    if (this.k != null) {
      return this.k;
    }
    return "";
  }
  
  protected final int j()
  {
    return this.l;
  }
  
  protected final int k()
  {
    return this.m;
  }
  
  protected final int l()
  {
    return this.n;
  }
  
  protected final String m()
  {
    if ((this.o == null) && (this.w != null)) {
      this.o = this.w.getPackageName();
    }
    if (this.o != null) {
      return this.o;
    }
    return "";
  }
  
  protected final List n()
  {
    int i1 = 0;
    ArrayList localArrayList1 = new ArrayList();
    if (w())
    {
      Object localObject = z(true);
      List localList = (List)((List)localObject).get(1);
      long l1 = ((Long)((List)localObject).get(0)).longValue();
      z(localList);
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
  
  protected final void o()
  {
    try
    {
      this.C.clear();
      if (this.B != null)
      {
        y(this.B);
        this.B = null;
      }
      if (this.D != null)
      {
        this.D.cancel();
        this.D = null;
      }
      this.D = new Timer();
      this.B = new bu(this, (byte)0);
      z(this.B);
      t();
      return;
    }
    finally {}
  }
  
  protected final void p()
  {
    try
    {
      this.C.clear();
      if (this.B != null)
      {
        y(this.B);
        this.B = null;
      }
      if (this.D != null)
      {
        this.D.cancel();
        this.D = null;
      }
      return;
    }
    finally {}
  }
  
  protected final byte q()
  {
    new ArrayList();
    if (this.b == null) {}
    List localList;
    do
    {
      return 0;
      localList = this.b.getSensorList(-1);
    } while (localList == null);
    return (byte)localList.size();
  }
  
  protected final Context r()
  {
    return this.w;
  }
  
  protected final double u(int paramInt)
  {
    new ArrayList();
    if (this.b == null) {}
    List localList;
    do
    {
      return 0.0D;
      localList = this.b.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return ((Sensor)localList.get(paramInt)).getResolution();
  }
  
  protected final String u()
  {
    if (this.c == null) {
      this.c = Build.MODEL;
    }
    if (this.c != null) {
      return this.c;
    }
    return "";
  }
  
  protected final int v(int paramInt)
  {
    new ArrayList();
    if (this.b == null) {}
    List localList;
    do
    {
      return 0;
      localList = this.b.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return (int)(((Sensor)localList.get(paramInt)).getPower() * 100.0D);
  }
  
  protected final boolean v()
  {
    try
    {
      if (this.u != null)
      {
        boolean bool = this.u.isProviderEnabled("gps");
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  protected final int w(int paramInt)
  {
    new ArrayList();
    if (this.b == null) {}
    List localList;
    do
    {
      return 0;
      localList = this.b.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return y(localList.get(paramInt));
  }
  
  protected final boolean w()
  {
    return (this.a != null) && ((this.a.isWifiEnabled()) || (z(this.a)));
  }
  
  protected final double x(int paramInt)
  {
    new ArrayList();
    if (this.b == null) {}
    List localList;
    do
    {
      return 0.0D;
      localList = this.b.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return ((Sensor)localList.get(paramInt)).getMaximumRange();
  }
  
  protected final boolean x()
  {
    Object localObject = null;
    if ((this.v != null) && (this.v.getSimState() == 5) && (this.h)) {
      return true;
    }
    if (this.v != null) {}
    try
    {
      CellLocation localCellLocation = this.v.getCellLocation();
      localObject = localCellLocation;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if (localObject != null)
    {
      this.q = System.currentTimeMillis();
      this.A = localObject;
      return true;
    }
    return false;
  }
  
  protected final String y(int paramInt)
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
  
  protected final List y(float paramFloat)
  {
    ArrayList localArrayList = new ArrayList();
    long l1 = System.currentTimeMillis();
    float f1 = paramFloat;
    if (Math.abs(paramFloat) <= 1.0F) {
      f1 = 1.0F;
    }
    if (x())
    {
      Object localObject = (CellLocation)d().get(1);
      if ((localObject != null) && ((localObject instanceof CdmaCellLocation)))
      {
        localObject = (CdmaCellLocation)localObject;
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getSystemId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getNetworkId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationId()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationLongitude()));
        localArrayList.add(Integer.valueOf(((CdmaCellLocation)localObject).getBaseStationLatitude()));
        if (l1 - ((Long)d().get(0)).longValue() > 50000.0D / f1) {
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
  
  protected final void y()
  {
    Object localObject;
    if (this.s != null)
    {
      localObject = this.s;
      if (this.v != null) {
        this.v.listen((PhoneStateListener)localObject, 0);
      }
      this.s = null;
    }
    if (this.t != null)
    {
      localObject = this.t;
      if ((this.u != null) && (localObject != null)) {
        break label119;
      }
    }
    for (;;)
    {
      this.t = null;
      if (this.D != null)
      {
        this.D.cancel();
        this.D = null;
      }
      if (this.F != null)
      {
        this.F.quit();
        this.F = null;
      }
      if (this.E != null)
      {
        this.E.interrupt();
        this.E = null;
      }
      return;
      label119:
      this.u.removeNmeaListener((GpsStatus.NmeaListener)localObject);
    }
  }
  
  protected final List z(float paramFloat)
  {
    ArrayList localArrayList = new ArrayList();
    long l1 = System.currentTimeMillis();
    float f1 = paramFloat;
    if (Math.abs(paramFloat) <= 1.0F) {
      f1 = 1.0F;
    }
    if (x())
    {
      CellLocation localCellLocation = (CellLocation)d().get(1);
      if ((localCellLocation != null) && ((localCellLocation instanceof GsmCellLocation)))
      {
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localCellLocation).getLac()));
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localCellLocation).getCid()));
        if (l1 - ((Long)d().get(0)).longValue() > 50000.0D / f1) {
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
  
  protected final List z(boolean paramBoolean)
  {
    int i2 = 0;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2;
    int i1;
    if (w())
    {
      localArrayList2 = new ArrayList();
      if (!paramBoolean) {}
      try
      {
        if (System.currentTimeMillis() - this.p >= 3500L) {
          break label145;
        }
        i1 = 1;
      }
      finally {}
      localArrayList2.add(Long.valueOf(this.p));
      i1 = i2;
      while (i1 < this.C.size())
      {
        localArrayList1.add(this.C.get(i1));
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
  
  protected final void z()
  {
    y();
    if (this.F != null)
    {
      this.F.quit();
      this.F = null;
    }
    if (this.E != null)
    {
      this.E.interrupt();
      this.E = null;
    }
    this.E = new br(this, "");
    this.E.start();
  }
  
  protected final void z(int paramInt)
  {
    if (paramInt == G) {}
    for (;;)
    {
      return;
      try
      {
        this.C.clear();
        if (this.B != null)
        {
          y(this.B);
          this.B = null;
        }
        if (this.D != null)
        {
          this.D.cancel();
          this.D = null;
        }
        if (paramInt < 5000) {
          continue;
        }
        G = paramInt;
        this.D = new Timer();
        this.B = new bu(this, (byte)0);
        z(this.B);
        t();
        return;
      }
      finally {}
    }
  }
}
