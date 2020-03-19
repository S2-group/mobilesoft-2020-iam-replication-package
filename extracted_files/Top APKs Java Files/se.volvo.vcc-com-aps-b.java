package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

public final class b
{
  private static int C = 10000;
  private static b t = null;
  private Thread A = null;
  private Looper B = null;
  private Context a = null;
  private TelephonyManager b = null;
  private LocationManager c = null;
  private WifiManager d = null;
  private String e = "";
  private String f = "";
  private String g = "";
  private boolean h = false;
  private int i = 0;
  private boolean j = false;
  private long k = -1L;
  private String l = "";
  private String m = "";
  private int n = 0;
  private int o = 0;
  private int p = 0;
  private String q = "";
  private long r = 0L;
  private long s = 0L;
  private d u = null;
  private e v = null;
  private CellLocation w = null;
  private f x = null;
  private List y = new ArrayList();
  private Timer z = null;
  
  private b(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return;
      this.a = paramContext;
      this.e = Build.MODEL;
      this.b = ((TelephonyManager)paramContext.getSystemService("phone"));
      this.c = ((LocationManager)paramContext.getSystemService("location"));
      this.d = ((WifiManager)paramContext.getSystemService("wifi"));
    } while ((this.b == null) || (this.d == null));
    try
    {
      this.f = this.b.getDeviceId();
      this.g = this.b.getSubscriberId();
      if (this.d.getConnectionInfo() != null)
      {
        this.m = this.d.getConnectionInfo().getMacAddress();
        if ((this.m != null) && (this.m.length() > 0)) {
          this.m = this.m.replace(":", "");
        }
      }
      String[] arrayOfString = b(this.b);
      this.n = Integer.parseInt(arrayOfString[0]);
      this.o = Integer.parseInt(arrayOfString[1]);
      this.p = this.b.getNetworkType();
      this.q = paramContext.getPackageName();
      if (this.b.getPhoneType() == 2) {}
      for (boolean bool = true;; bool = false)
      {
        this.h = bool;
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  protected static b a(Context paramContext)
  {
    if ((t == null) && (c(paramContext)))
    {
      Object localObject = (LocationManager)paramContext.getSystemService("location");
      if (localObject == null) {
        break label95;
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
    label95:
    for (int i1 = 1;; i1 = 0)
    {
      if (i1 != 0) {
        t = new b(paramContext);
      }
      return t;
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
  
  private void b(BroadcastReceiver paramBroadcastReceiver)
  {
    if ((paramBroadcastReceiver == null) || (this.a == null)) {
      return;
    }
    this.a.unregisterReceiver(paramBroadcastReceiver);
  }
  
  protected static boolean b(Context paramContext)
  {
    if (paramContext == null) {
      return false;
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
      while (i1 < ac.a.length)
      {
        if (!ac.a(paramContext, ac.a[i1])) {
          return false;
        }
        i1 += 1;
      }
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private void z()
  {
    if (this.d == null) {
      return;
    }
    this.d.startScan();
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
    if (this.B != null)
    {
      this.B.quit();
      this.B = null;
    }
    if (this.A != null)
    {
      this.A.interrupt();
      this.A = null;
    }
    this.A = new c(this, "");
    this.A.start();
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
    if (this.u != null)
    {
      localObject = this.u;
      if (this.b != null) {
        this.b.listen((PhoneStateListener)localObject, 0);
      }
      this.u = null;
    }
    if (this.v != null)
    {
      localObject = this.v;
      if ((this.c != null) && (localObject != null)) {
        break label119;
      }
    }
    for (;;)
    {
      this.v = null;
      if (this.z != null)
      {
        this.z.cancel();
        this.z = null;
      }
      if (this.B != null)
      {
        this.B.quit();
        this.B = null;
      }
      if (this.A != null)
      {
        this.A.interrupt();
        this.A = null;
      }
      return;
      label119:
      this.c.removeNmeaListener((GpsStatus.NmeaListener)localObject);
    }
  }
  
  protected final boolean c()
  {
    if ((this.b != null) && (this.b.getSimState() == 5) && (this.j)) {
      return true;
    }
    if (this.b != null)
    {
      CellLocation localCellLocation = this.b.getCellLocation();
      if (localCellLocation != null)
      {
        this.s = System.currentTimeMillis();
        this.w = localCellLocation;
        return true;
      }
    }
    return false;
  }
  
  protected final boolean d()
  {
    return (this.d != null) && (this.d.isWifiEnabled());
  }
  
  protected final boolean e()
  {
    return (this.c != null) && (this.c.isProviderEnabled("gps"));
  }
  
  protected final String f()
  {
    if (this.e == null) {
      this.e = Build.MODEL;
    }
    if (this.e != null) {
      return this.e;
    }
    return "";
  }
  
  protected final String g()
  {
    if ((this.f == null) && (this.a != null))
    {
      this.b = ((TelephonyManager)this.a.getSystemService("phone"));
      if (this.b != null) {
        this.f = this.b.getDeviceId();
      }
    }
    if (this.f != null) {
      return this.f;
    }
    return "";
  }
  
  protected final String h()
  {
    if ((this.g == null) && (this.a != null))
    {
      this.b = ((TelephonyManager)this.a.getSystemService("phone"));
      if (this.b != null) {
        this.g = this.b.getSubscriberId();
      }
    }
    if (this.g != null) {
      return this.g;
    }
    return "";
  }
  
  protected final boolean i()
  {
    return this.h;
  }
  
  protected final List j()
  {
    if (Settings.System.getInt(this.a.getContentResolver(), "airplane_mode_on", 0) == 1) {
      return new ArrayList();
    }
    if (c())
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(Long.valueOf(this.s));
      localArrayList.add(this.w);
      return localArrayList;
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
        if (System.currentTimeMillis() - this.r >= 3500L) {
          break label130;
        }
        i1 = 1;
        if (i1 != 0)
        {
          localArrayList2.add(Long.valueOf(this.r));
          i1 = i2;
          if (i1 < this.y.size())
          {
            localArrayList1.add(this.y.get(i1));
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
      return (byte)this.i;
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
      l2 = this.k;
    }
    for (String str = this.l;; str = "")
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
    long l1 = this.k;
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
    if ((this.m == null) && (this.a != null))
    {
      this.d = ((WifiManager)this.a.getSystemService("wifi"));
      if ((this.d != null) && (this.d.getConnectionInfo() != null))
      {
        this.m = this.d.getConnectionInfo().getMacAddress();
        if ((this.m != null) && (this.m.length() > 0)) {
          this.m = this.m.replace(":", "");
        }
      }
    }
    if (this.m != null) {
      return this.m;
    }
    return "";
  }
  
  protected final int q()
  {
    return this.n;
  }
  
  protected final int r()
  {
    return this.o;
  }
  
  protected final int s()
  {
    return this.p;
  }
  
  protected final String t()
  {
    if ((this.q == null) && (this.a != null)) {
      this.q = this.a.getPackageName();
    }
    if (this.q != null) {
      return this.q;
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
      localArrayList1.add(Long.valueOf(((Long)((List)localObject).get(0)).longValue()));
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
      this.y.clear();
      if (this.x != null)
      {
        b(this.x);
        this.x = null;
      }
      if (this.z != null)
      {
        this.z.cancel();
        this.z = null;
      }
      this.z = new Timer();
      this.x = new f(this, (byte)0);
      a(this.x);
      z();
      return;
    }
    finally {}
  }
  
  protected final void w()
  {
    try
    {
      this.y.clear();
      if (this.x != null)
      {
        b(this.x);
        this.x = null;
      }
      if (this.z != null)
      {
        this.z.cancel();
        this.z = null;
      }
      return;
    }
    finally {}
  }
  
  protected final Context x()
  {
    return this.a;
  }
}
