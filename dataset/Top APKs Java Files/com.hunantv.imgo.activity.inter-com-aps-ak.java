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

public final class ak
{
  private static int D = 10000;
  private static ak u = null;
  private Timer A = null;
  private Thread B = null;
  private Looper C = null;
  private Context a = null;
  private TelephonyManager b = null;
  private LocationManager c = null;
  private WifiManager d = null;
  private SensorManager e = null;
  private String f = "";
  private String g = "";
  private String h = "";
  private boolean i = false;
  private int j = 0;
  private boolean k = false;
  private long l = -1L;
  private String m = "";
  private String n = "";
  private int o = 0;
  private int p = 0;
  private int q = 0;
  private String r = "";
  private long s = 0L;
  private long t = 0L;
  private am v = null;
  private an w = null;
  private CellLocation x = null;
  private ao y = null;
  private List z = new ArrayList();
  
  private ak(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return;
      this.a = paramContext;
      this.f = Build.MODEL;
      this.b = ((TelephonyManager)paramContext.getSystemService("phone"));
      this.c = ((LocationManager)paramContext.getSystemService("location"));
      this.d = ((WifiManager)paramContext.getSystemService("wifi"));
      this.e = ((SensorManager)paramContext.getSystemService("sensor"));
    } while ((this.b == null) || (this.d == null));
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
      if (this.b.getPhoneType() == 2) {}
      for (boolean bool = true;; bool = false)
      {
        this.i = bool;
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private void A()
  {
    if (this.d == null) {}
    for (;;)
    {
      return;
      try
      {
        if (bd.a)
        {
          this.d.startScan();
          return;
        }
      }
      catch (Exception localException) {}
    }
  }
  
  private CellLocation B()
  {
    if (this.b == null) {
      return null;
    }
    try
    {
      CellLocation localCellLocation = b((List)ah.a(this.b, "getAllCellInfo", new Object[0]));
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
  
  protected static ak a(Context paramContext)
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
        u = new ak(paramContext);
      }
      return u;
    }
  }
  
  private void a(BroadcastReceiver paramBroadcastReceiver)
  {
    if ((paramBroadcastReceiver == null) || (this.a == null)) {
      return;
    }
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
    this.a.registerReceiver(paramBroadcastReceiver, localIntentFilter);
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
          if ((ah.b(paramCellLocation, "getSystemId", new Object[0]) <= 0) || (ah.b(paramCellLocation, "getNetworkId", new Object[0]) < 0)) {
            break;
          }
          int i1 = ah.b(paramCellLocation, "getBaseStationId", new Object[0]);
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
            localObject5 = ah.a(localObject4, "getCellIdentity", new Object[0]);
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
        i3 = ah.b(localObject5, "getSystemId", new Object[0]);
        i4 = ah.b(localObject5, "getNetworkId", new Object[0]);
        int i5 = ah.b(localObject5, "getBasestationId", new Object[0]);
        int i6 = ah.b(localObject5, "getLongitude", new Object[0]);
        ((CdmaCellLocation)localObject4).setCellLocationData(i5, ah.b(localObject5, "getLatitude", new Object[0]), i6, i3, i4);
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
        i3 = ah.b(localObject5, "getTac", new Object[0]);
        i4 = ah.b(localObject5, "getCi", new Object[0]);
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
      i3 = ah.b(localObject5, "getLac", new Object[0]);
      i4 = ah.b(localObject5, "getCid", new Object[0]);
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
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 4096);
      paramContext = paramContext.requestedPermissions;
      int i1 = 0;
      while (i1 < bd.b.length)
      {
        if (!bd.a(paramContext, bd.b[i1])) {
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
    this.B = new al(this, "");
    this.B.start();
  }
  
  protected final double b(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {}
    List localList;
    do
    {
      return 0.0D;
      localList = this.e.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return ((Sensor)localList.get(paramInt)).getMaximumRange();
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
        break label119;
      }
    }
    for (;;)
    {
      this.w = null;
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
      return;
      label119:
      this.c.removeNmeaListener((GpsStatus.NmeaListener)localObject);
    }
  }
  
  protected final int c(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {}
    List localList;
    do
    {
      return 0;
      localList = this.e.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return b(localList.get(paramInt));
  }
  
  protected final boolean c()
  {
    Object localObject = null;
    if ((this.b != null) && (this.b.getSimState() == 5) && (this.k)) {
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
      this.t = System.currentTimeMillis();
      this.x = localObject;
      return true;
    }
    return false;
  }
  
  protected final int d(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {}
    List localList;
    do
    {
      return 0;
      localList = this.e.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return (int)(((Sensor)localList.get(paramInt)).getPower() * 100.0D);
  }
  
  protected final boolean d()
  {
    return (this.d != null) && ((this.d.isWifiEnabled()) || (a(this.d)));
  }
  
  protected final double e(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {}
    List localList;
    do
    {
      return 0.0D;
      localList = this.e.getSensorList(-1);
    } while ((localList == null) || (localList.get(paramInt) == null));
    return ((Sensor)localList.get(paramInt)).getResolution();
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
    catch (Exception localException) {}
    return false;
  }
  
  protected final byte f(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return Byte.MAX_VALUE;
    }
    List localList = this.e.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getType() > 127)) {
      return Byte.MAX_VALUE;
    }
    return (byte)((Sensor)localList.get(paramInt)).getType();
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
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getVendor() == null) || (((Sensor)localList.get(paramInt)).getVendor().length() <= 0)) {
      return "null";
    }
    return ((Sensor)localList.get(paramInt)).getVendor();
  }
  
  protected final byte h(int paramInt)
  {
    new ArrayList();
    if (this.e == null) {
      return Byte.MAX_VALUE;
    }
    List localList = this.e.getSensorList(-1);
    if ((localList == null) || (localList.get(paramInt) == null) || (((Sensor)localList.get(paramInt)).getType() > 127)) {
      return Byte.MAX_VALUE;
    }
    return (byte)((Sensor)localList.get(paramInt)).getVersion();
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
      CellLocation localCellLocation;
      if (!a(this.x))
      {
        localCellLocation = B();
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
  
  protected final List k()
  {
    int i2 = 0;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2;
    if (d()) {
      localArrayList2 = new ArrayList();
    }
    for (;;)
    {
      try
      {
        if (System.currentTimeMillis() - this.s >= 3500L) {
          break label130;
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
      label130:
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
    long l2;
    if (e()) {
      l2 = this.l;
    }
    for (String str = this.m;; str = "")
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
  
  protected final long o()
  {
    long l2 = 0L;
    long l1 = this.l;
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
    int i1 = 0;
    ArrayList localArrayList1 = new ArrayList();
    if (d())
    {
      Object localObject = k();
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
      this.y = new ao(this, (byte)0);
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
    if (this.e == null) {}
    List localList;
    do
    {
      return 0;
      localList = this.e.getSensorList(-1);
    } while (localList == null);
    return (byte)localList.size();
  }
  
  protected final Context y()
  {
    return this.a;
  }
}
