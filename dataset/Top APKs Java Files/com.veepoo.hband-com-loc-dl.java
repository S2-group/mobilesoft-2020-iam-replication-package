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

public final class dl
{
  private static int I;
  private static String[] J = { "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE" };
  static String a;
  protected static boolean b;
  protected static boolean c;
  private static dl z = null;
  private dn A = null;
  private do B = null;
  private CellLocation C = null;
  private dp D = null;
  private List E = new ArrayList();
  private Timer F = null;
  private Thread G = null;
  private Looper H = null;
  Object d = new Object();
  boolean e = false;
  private Context f = null;
  private TelephonyManager g = null;
  private LocationManager h = null;
  private WifiManager i = null;
  private SensorManager j = null;
  private String k = "";
  private String l = "";
  private String m = "";
  private boolean n = false;
  private int o = 0;
  private boolean p = false;
  private long q = -1L;
  private String r = "";
  private String s = "";
  private int t = 0;
  private int u = 0;
  private int v = 0;
  private String w = "";
  private long x = 0L;
  private long y = 0L;
  
  static
  {
    I = 10000;
    a = "";
    b = true;
    c = false;
  }
  
  private dl(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return;
      this.f = paramContext;
      this.k = Build.MODEL;
      this.g = ((TelephonyManager)paramContext.getSystemService("phone"));
      this.h = ((LocationManager)paramContext.getSystemService("location"));
      this.i = ((WifiManager)paramContext.getSystemService("wifi"));
      this.j = ((SensorManager)paramContext.getSystemService("sensor"));
    } while ((this.g == null) || (this.i == null));
    try
    {
      this.l = this.g.getDeviceId();
      this.m = this.g.getSubscriberId();
      if (this.i.getConnectionInfo() != null)
      {
        this.s = this.i.getConnectionInfo().getMacAddress();
        if ((this.s != null) && (this.s.length() > 0)) {
          this.s = this.s.replace(":", "");
        }
      }
      String[] arrayOfString = b(this.g);
      this.t = Integer.parseInt(arrayOfString[0]);
      this.u = Integer.parseInt(arrayOfString[1]);
      this.v = this.g.getNetworkType();
      this.w = paramContext.getPackageName();
      if (this.g.getPhoneType() == 2) {}
      for (boolean bool = true;; bool = false)
      {
        this.n = bool;
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
    if (this.g == null) {
      return null;
    }
    try
    {
      CellLocation localCellLocation = b((List)di.a(this.g, "getAllCellInfo", new Object[0]));
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
  
  protected static dl a(Context paramContext)
  {
    if ((z == null) && (c(paramContext)))
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
        z = new dl(paramContext);
      }
      return z;
    }
  }
  
  private void a(BroadcastReceiver paramBroadcastReceiver)
  {
    if ((paramBroadcastReceiver == null) || (this.f == null)) {
      return;
    }
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
    this.f.registerReceiver(paramBroadcastReceiver, localIntentFilter);
  }
  
  private void a(GpsStatus.NmeaListener paramNmeaListener)
  {
    if ((this.h == null) || (paramNmeaListener == null)) {
      return;
    }
    this.h.removeNmeaListener(paramNmeaListener);
  }
  
  private static void a(WifiManager paramWifiManager)
  {
    if (paramWifiManager == null) {
      return;
    }
    try
    {
      di.a(paramWifiManager, "startScanActive", new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      paramWifiManager.startScan();
    }
  }
  
  private void a(PhoneStateListener paramPhoneStateListener)
  {
    if (this.g == null) {
      return;
    }
    this.g.listen(paramPhoneStateListener, 0);
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
      paramList.add(((TreeMap)localObject).get(localIterator.next()));
    }
    localHashMap.clear();
    ((TreeMap)localObject).clear();
  }
  
  private boolean a(CellLocation paramCellLocation)
  {
    if (paramCellLocation == null) {
      return false;
    }
    switch (a(paramCellLocation, this.f))
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
          if ((di.b(paramCellLocation, "getSystemId", new Object[0]) <= 0) || (di.b(paramCellLocation, "getNetworkId", new Object[0]) < 0)) {
            break;
          }
          int i1 = di.b(paramCellLocation, "getBaseStationId", new Object[0]);
          if (i1 < 0) {
            return false;
          }
        }
        catch (Exception paramCellLocation) {}
      }
    }
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
    catch (Exception paramObject) {}
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
    catch (Exception paramObject) {}
    return 0;
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
            localObject5 = di.a(localObject4, "getCellIdentity", new Object[0]);
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
        i3 = di.b(localObject5, "getSystemId", new Object[0]);
        i4 = di.b(localObject5, "getNetworkId", new Object[0]);
        int i5 = di.b(localObject5, "getBasestationId", new Object[0]);
        int i6 = di.b(localObject5, "getLongitude", new Object[0]);
        ((CdmaCellLocation)localObject4).setCellLocationData(i5, di.b(localObject5, "getLatitude", new Object[0]), i6, i3, i4);
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
        i3 = di.b(localObject5, "getTac", new Object[0]);
        i4 = di.b(localObject5, "getCi", new Object[0]);
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
      i3 = di.b(localObject5, "getLac", new Object[0]);
      i4 = di.b(localObject5, "getCid", new Object[0]);
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
  
  private void b(BroadcastReceiver paramBroadcastReceiver)
  {
    if ((paramBroadcastReceiver == null) || (this.f == null)) {
      return;
    }
    try
    {
      this.f.unregisterReceiver(paramBroadcastReceiver);
      return;
    }
    catch (Exception paramBroadcastReceiver) {}
  }
  
  protected static boolean b(Context paramContext)
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
  
  private void z()
  {
    if (this.i == null) {}
    for (;;)
    {
      return;
      try
      {
        if (b)
        {
          a(this.i);
          return;
        }
      }
      catch (Exception localException) {}
    }
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
      CellLocation localCellLocation = (CellLocation)j().get(1);
      if ((localCellLocation != null) && ((localCellLocation instanceof GsmCellLocation)))
      {
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localCellLocation).getLac()));
        localArrayList.add(Integer.valueOf(((GsmCellLocation)localCellLocation).getCid()));
        if (l1 - ((Long)j().get(0)).longValue() > 50000.0D / f1) {
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
        if (System.currentTimeMillis() - this.x >= 3500L) {
          break label145;
        }
        i1 = 1;
      }
      finally {}
      localArrayList2.add(Long.valueOf(this.x));
      i1 = i2;
      while (i1 < this.E.size())
      {
        localArrayList1.add(this.E.get(i1));
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
    this.e = true;
    this.G = new dm(this, "");
    this.G.start();
  }
  
  protected final void a(int paramInt)
  {
    if (paramInt == I) {}
    for (;;)
    {
      return;
      try
      {
        this.E.clear();
        if (this.D != null)
        {
          b(this.D);
          this.D = null;
        }
        if (this.F != null)
        {
          this.F.cancel();
          this.F = null;
        }
        if (paramInt < 5000) {
          continue;
        }
        I = paramInt;
        this.F = new Timer();
        this.D = new dp(this, (byte)0);
        a(this.D);
        z();
        return;
      }
      finally {}
    }
  }
  
  protected final String b(int paramInt)
  {
    new ArrayList();
    if (this.j == null) {
      return "null";
    }
    List localList = this.j.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getName() == null) || (((Sensor)localList.get(paramInt)).getName().length() <= 0)) {
      return "null";
    }
    return ((Sensor)localList.get(paramInt)).getName();
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
        if (l1 - ((Long)j().get(0)).longValue() > 50000.0D / f1) {
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
    synchronized (this.d)
    {
      this.e = false;
      if (this.A != null)
      {
        a(this.A);
        this.A = null;
      }
      if (this.B != null)
      {
        a(this.B);
        this.B = null;
      }
      if (this.F != null)
      {
        this.F.cancel();
        this.F = null;
      }
      if (this.H != null)
      {
        this.H.quit();
        this.H = null;
      }
      if (this.G != null)
      {
        this.G.interrupt();
        this.G = null;
      }
      return;
    }
  }
  
  protected final double c(int paramInt)
  {
    new ArrayList();
    if (this.j == null) {}
    List localList;
    do
    {
      return 0.0D;
      localList = this.j.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return ((Sensor)localList.get(paramInt)).getMaximumRange();
  }
  
  protected final boolean c()
  {
    Object localObject = null;
    if ((this.g != null) && (this.g.getSimState() == 5) && (this.p)) {
      return true;
    }
    if (this.g != null) {}
    try
    {
      CellLocation localCellLocation = this.g.getCellLocation();
      localObject = localCellLocation;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if (localObject != null)
    {
      this.y = System.currentTimeMillis();
      this.C = localObject;
      return true;
    }
    return false;
  }
  
  protected final int d(int paramInt)
  {
    new ArrayList();
    if (this.j == null) {}
    List localList;
    do
    {
      return 0;
      localList = this.j.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return b(localList.get(paramInt));
  }
  
  protected final boolean d()
  {
    return (this.i != null) && ((this.i.isWifiEnabled()) || (a(this.i)));
  }
  
  protected final int e(int paramInt)
  {
    new ArrayList();
    if (this.j == null) {}
    List localList;
    do
    {
      return 0;
      localList = this.j.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return (int)(((Sensor)localList.get(paramInt)).getPower() * 100.0D);
  }
  
  protected final boolean e()
  {
    try
    {
      if (this.h != null)
      {
        boolean bool = this.h.isProviderEnabled("gps");
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  protected final double f(int paramInt)
  {
    new ArrayList();
    if (this.j == null) {}
    List localList;
    do
    {
      return 0.0D;
      localList = this.j.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return ((Sensor)localList.get(paramInt)).getResolution();
  }
  
  protected final String f()
  {
    if (this.k == null) {
      this.k = Build.MODEL;
    }
    if (this.k != null) {
      return this.k;
    }
    return "";
  }
  
  protected final byte g(int paramInt)
  {
    new ArrayList();
    if (this.j == null) {
      return Byte.MAX_VALUE;
    }
    List localList = this.j.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getType() > 127)) {
      return Byte.MAX_VALUE;
    }
    return (byte)((Sensor)localList.get(paramInt)).getType();
  }
  
  protected final String g()
  {
    if ((this.l == null) && (this.f != null))
    {
      this.g = ((TelephonyManager)this.f.getSystemService("phone"));
      if (this.g == null) {}
    }
    try
    {
      this.l = this.g.getDeviceId();
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
  
  protected final String h()
  {
    if ((this.m == null) && (this.f != null))
    {
      this.g = ((TelephonyManager)this.f.getSystemService("phone"));
      if (this.g != null) {
        this.m = this.g.getSubscriberId();
      }
    }
    if (this.m != null) {
      return this.m;
    }
    return "";
  }
  
  protected final String h(int paramInt)
  {
    new ArrayList();
    if (this.j == null) {
      return "null";
    }
    List localList = this.j.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getVendor() == null) || (((Sensor)localList.get(paramInt)).getVendor().length() <= 0)) {
      return "null";
    }
    return ((Sensor)localList.get(paramInt)).getVendor();
  }
  
  protected final byte i(int paramInt)
  {
    new ArrayList();
    if (this.j == null) {
      return Byte.MAX_VALUE;
    }
    List localList = this.j.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getType() > 127)) {
      return Byte.MAX_VALUE;
    }
    return (byte)((Sensor)localList.get(paramInt)).getVersion();
  }
  
  protected final boolean i()
  {
    return this.n;
  }
  
  protected final List j()
  {
    if (Settings.System.getInt(this.f.getContentResolver(), "airplane_mode_on", 0) == 1) {
      return new ArrayList();
    }
    if (c())
    {
      ArrayList localArrayList = new ArrayList();
      CellLocation localCellLocation;
      if (!a(this.C))
      {
        localCellLocation = A();
        if (a(localCellLocation)) {
          this.y = System.currentTimeMillis();
        }
      }
      for (;;)
      {
        localArrayList.add(Long.valueOf(this.y));
        localArrayList.add(localCellLocation);
        return localArrayList;
        localCellLocation = this.C;
      }
    }
    return new ArrayList();
  }
  
  protected final byte k()
  {
    if (c()) {
      return (byte)this.o;
    }
    return Byte.MIN_VALUE;
  }
  
  protected final List l()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.g == null) {
      return localArrayList;
    }
    if (!c()) {
      return localArrayList;
    }
    if (this.g.getSimState() == 1) {
      return localArrayList;
    }
    Iterator localIterator = this.g.getNeighboringCellInfo().iterator();
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
  
  protected final List m()
  {
    ArrayList localArrayList = new ArrayList();
    long l2;
    if (e()) {
      l2 = this.q;
    }
    for (String str = this.r;; str = "")
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
  
  protected final long n()
  {
    long l2 = 0L;
    long l1 = this.q;
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
  
  protected final String o()
  {
    if ((this.s == null) && (this.f != null))
    {
      this.i = ((WifiManager)this.f.getSystemService("wifi"));
      if ((this.i != null) && (this.i.getConnectionInfo() != null))
      {
        this.s = this.i.getConnectionInfo().getMacAddress();
        if ((this.s != null) && (this.s.length() > 0)) {
          this.s = this.s.replace(":", "");
        }
      }
    }
    if (this.s != null) {
      return this.s;
    }
    return "";
  }
  
  protected final int p()
  {
    return this.t;
  }
  
  protected final int q()
  {
    return this.u;
  }
  
  protected final int r()
  {
    return this.v;
  }
  
  protected final String s()
  {
    if ((this.w == null) && (this.f != null)) {
      this.w = this.f.getPackageName();
    }
    if (this.w != null) {
      return this.w;
    }
    return "";
  }
  
  protected final List t()
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
  
  protected final void u()
  {
    try
    {
      this.E.clear();
      if (this.D != null)
      {
        b(this.D);
        this.D = null;
      }
      if (this.F != null)
      {
        this.F.cancel();
        this.F = null;
      }
      this.F = new Timer();
      this.D = new dp(this, (byte)0);
      a(this.D);
      z();
      return;
    }
    finally {}
  }
  
  protected final void v()
  {
    try
    {
      this.E.clear();
      if (this.D != null)
      {
        b(this.D);
        this.D = null;
      }
      if (this.F != null)
      {
        this.F.cancel();
        this.F = null;
      }
      return;
    }
    finally {}
  }
  
  protected final byte w()
  {
    new ArrayList();
    if (this.j == null) {}
    List localList;
    do
    {
      return 0;
      localList = this.j.getSensorList(-1);
    } while (localList == null);
    return (byte)localList.size();
  }
  
  protected final Context x()
  {
    return this.f;
  }
}
