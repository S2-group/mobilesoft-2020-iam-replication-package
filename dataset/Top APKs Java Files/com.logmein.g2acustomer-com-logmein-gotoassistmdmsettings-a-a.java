package com.logmein.gotoassistmdmsettings.a;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.logmein.gotoassistmdmsettings.b.c;
import com.logmein.gotoassistmdmsettings.b.d;
import com.logmein.gotoassistmdmsettings.b.e;
import com.logmein.gotoassistmdmsettings.b.f;
import com.logmein.gotoassistmdmsettings.b.h;
import com.logmein.gotoassistmdmsettings.b.j;
import com.logmein.gotoassistmdmsettings.b.l;
import com.logmein.gotoassistmdmsettings.b.r;
import com.logmein.gotoassistmdmsettings.d.a;
import com.logmein.gotoassistmdmsettings.d.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class a
  implements b
{
  private static b a;
  private final int b = 0;
  
  public a() {}
  
  private List<e> a(Context paramContext, BluetoothAdapter paramBluetoothAdapter)
  {
    ArrayList localArrayList = new ArrayList();
    paramBluetoothAdapter = paramBluetoothAdapter.getBondedDevices().iterator();
    while (paramBluetoothAdapter.hasNext())
    {
      BluetoothDevice localBluetoothDevice = (BluetoothDevice)paramBluetoothAdapter.next();
      e localE = new e();
      localE.c(localBluetoothDevice.getName());
      localE.e(com.logmein.gotoassistmdmsettings.g.a.getBluetoothMajorDeviceClass(paramContext, localBluetoothDevice.getBluetoothClass().getMajorDeviceClass()));
      localE.d(com.logmein.gotoassistmdmsettings.g.a.getBluetoothBondState(paramContext, localBluetoothDevice.getBondState()));
      localArrayList.add(localE);
    }
    return localArrayList;
  }
  
  private void a(TelephonyManager paramTelephonyManager, Context paramContext, b.b paramB)
  {
    try
    {
      new b(paramB, paramTelephonyManager, paramContext).a();
      return;
    }
    finally
    {
      paramTelephonyManager = finally;
      throw paramTelephonyManager;
    }
  }
  
  private void a(HashMap<String, l> paramHashMap, Context paramContext)
  {
    try
    {
      Object localObject1 = (ActivityManager)paramContext.getSystemService("activity");
      Object localObject2 = ((ActivityManager)localObject1).getRunningAppProcesses();
      int[] arrayOfInt = new int[((List)localObject2).size()];
      localObject2 = ((List)localObject2).iterator();
      int i = 0;
      while (((Iterator)localObject2).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
        Object localObject3 = Integer.valueOf(localRunningAppProcessInfo.pid).toString();
        if (paramHashMap.containsKey(localObject3))
        {
          localObject3 = (l)paramHashMap.get(localObject3);
          ((l)localObject3).a(Boolean.valueOf(false));
          ((l)localObject3).d(com.logmein.gotoassistmdmsettings.g.a.getProcessImportance(paramContext, localRunningAppProcessInfo.importance));
        }
        arrayOfInt[i] = localRunningAppProcessInfo.pid;
        i += 1;
      }
      paramContext = ((ActivityManager)localObject1).getProcessMemoryInfo(arrayOfInt);
      i = 0;
      while (i < arrayOfInt.length)
      {
        double d = com.logmein.gotoassistmdmsettings.g.a.convertkbToMb(paramContext[i].getTotalPss());
        localObject1 = Integer.valueOf(arrayOfInt[i]).toString();
        if (paramHashMap.containsKey(localObject1)) {
          ((l)paramHashMap.get(localObject1)).a(com.logmein.gotoassistmdmsettings.g.a.formatMemorySize(d));
        }
        i += 1;
      }
      return;
    }
    finally {}
  }
  
  private void b(Context paramContext, b.c paramC)
  {
    try
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
      paramContext.registerReceiver(new c(paramContext, paramC), localIntentFilter);
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static void dispose()
  {
    a = null;
  }
  
  public static b getInstance()
  {
    if (a == null) {
      a = new a();
    }
    return a;
  }
  
  private List<r> l(Context paramContext)
  {
    Object localObject1 = (WifiManager)paramContext.getSystemService("wifi");
    if (localObject1 == null) {
      return null;
    }
    Object localObject2 = ((WifiManager)localObject1).getScanResults();
    localObject1 = com.logmein.gotoassistmdmsettings.g.a.removeQuotesFromStr(((WifiManager)localObject1).getConnectionInfo().getSSID());
    if (localObject2 == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    localObject2 = com.logmein.gotoassistmdmsettings.g.a.removeWifiNetworkDuplicates((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ScanResult localScanResult = (ScanResult)((Iterator)localObject2).next();
      r localR = new r();
      localR.b(localScanResult.SSID);
      localR.c(com.logmein.gotoassistmdmsettings.g.a.getSignalStrength(paramContext, localScanResult.level));
      localR.d(com.logmein.gotoassistmdmsettings.g.a.getWifiEncryptionType(localScanResult.capabilities));
      localR.a(com.logmein.gotoassistmdmsettings.g.a.getWifiNetworkStatus(paramContext, localScanResult.SSID, (String)localObject1));
      localArrayList.add(localR);
    }
    return localArrayList;
  }
  
  /* Error */
  public List<org.apache.http.NameValuePair> a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 272	java/io/RandomAccessFile
    //   5: dup
    //   6: ldc_w 274
    //   9: ldc_w 276
    //   12: invokespecial 279	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   15: astore_1
    //   16: aload_1
    //   17: invokevirtual 282	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   20: astore_2
    //   21: new 27	java/util/ArrayList
    //   24: dup
    //   25: invokespecial 28	java/util/ArrayList:<init>	()V
    //   28: astore_3
    //   29: aload_2
    //   30: ifnull +54 -> 84
    //   33: aload_2
    //   34: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   37: ldc_w 289
    //   40: invokevirtual 293	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   43: astore_2
    //   44: aload_2
    //   45: arraylength
    //   46: iconst_2
    //   47: if_icmpne +29 -> 76
    //   50: aload_3
    //   51: new 295	org/apache/http/message/BasicNameValuePair
    //   54: dup
    //   55: aload_2
    //   56: iconst_0
    //   57: aaload
    //   58: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   61: aload_2
    //   62: iconst_1
    //   63: aaload
    //   64: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   67: invokespecial 296	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   70: invokeinterface 96 2 0
    //   75: pop
    //   76: aload_1
    //   77: invokevirtual 282	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   80: astore_2
    //   81: goto -52 -> 29
    //   84: aload_1
    //   85: invokevirtual 299	java/io/RandomAccessFile:close	()V
    //   88: aload_3
    //   89: astore_1
    //   90: aload_0
    //   91: monitorexit
    //   92: aload_1
    //   93: areturn
    //   94: astore_2
    //   95: aconst_null
    //   96: astore_1
    //   97: aload_2
    //   98: invokevirtual 302	java/io/FileNotFoundException:printStackTrace	()V
    //   101: aload_1
    //   102: invokestatic 306	com/logmein/gotoassistmdmsettings/g/a:closeFile	(Ljava/io/RandomAccessFile;)V
    //   105: aconst_null
    //   106: astore_1
    //   107: goto -17 -> 90
    //   110: aload_2
    //   111: invokevirtual 307	java/io/IOException:printStackTrace	()V
    //   114: goto -13 -> 101
    //   117: astore_1
    //   118: aload_0
    //   119: monitorexit
    //   120: aload_1
    //   121: athrow
    //   122: astore_2
    //   123: goto -13 -> 110
    //   126: astore_2
    //   127: goto -30 -> 97
    //   130: astore_2
    //   131: aconst_null
    //   132: astore_1
    //   133: goto -23 -> 110
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	this	a
    //   15	92	1	localObject1	Object
    //   117	4	1	localObject2	Object
    //   132	1	1	localObject3	Object
    //   20	61	2	localObject4	Object
    //   94	17	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   122	1	2	localIOException1	java.io.IOException
    //   126	1	2	localFileNotFoundException2	java.io.FileNotFoundException
    //   130	1	2	localIOException2	java.io.IOException
    //   28	61	3	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   2	16	94	java/io/FileNotFoundException
    //   2	16	117	finally
    //   16	29	117	finally
    //   33	76	117	finally
    //   76	81	117	finally
    //   84	88	117	finally
    //   97	101	117	finally
    //   101	105	117	finally
    //   110	114	117	finally
    //   16	29	122	java/io/IOException
    //   33	76	122	java/io/IOException
    //   76	81	122	java/io/IOException
    //   84	88	122	java/io/IOException
    //   16	29	126	java/io/FileNotFoundException
    //   33	76	126	java/io/FileNotFoundException
    //   76	81	126	java/io/FileNotFoundException
    //   84	88	126	java/io/FileNotFoundException
    //   2	16	130	java/io/IOException
  }
  
  public void a(Context paramContext)
  {
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    if ((paramContext != null) && (paramContext.isWifiEnabled())) {
      paramContext.startScan();
    }
  }
  
  /* Error */
  public void a(Context paramContext, b.a paramA)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 320	com/logmein/gotoassistmdmsettings/a/a:k	(Landroid/content/Context;)I
    //   7: bipush 10
    //   9: if_icmpne +23 -> 32
    //   12: ldc_w 322
    //   15: ldc_w 324
    //   18: invokestatic 329	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   21: pop
    //   22: aload_2
    //   23: aconst_null
    //   24: invokeinterface 334 2 0
    //   29: aload_0
    //   30: monitorexit
    //   31: return
    //   32: new 336	com/logmein/gotoassistmdmsettings/b/f
    //   35: dup
    //   36: invokespecial 337	com/logmein/gotoassistmdmsettings/b/f:<init>	()V
    //   39: astore_3
    //   40: invokestatic 341	android/bluetooth/BluetoothAdapter:getDefaultAdapter	()Landroid/bluetooth/BluetoothAdapter;
    //   43: astore 4
    //   45: new 192	android/content/IntentFilter
    //   48: dup
    //   49: ldc_w 343
    //   52: invokespecial 345	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   55: astore 5
    //   57: aload 5
    //   59: ldc_w 347
    //   62: invokevirtual 198	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   65: aload 5
    //   67: ldc_w 349
    //   70: invokevirtual 198	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   73: ldc_w 322
    //   76: ldc_w 351
    //   79: invokestatic 329	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   82: pop
    //   83: aload_3
    //   84: aload 4
    //   86: invokevirtual 354	android/bluetooth/BluetoothAdapter:getScanMode	()I
    //   89: invokevirtual 357	com/logmein/gotoassistmdmsettings/b/f:a	(I)V
    //   92: aload_3
    //   93: aload 4
    //   95: invokevirtual 360	android/bluetooth/BluetoothAdapter:getState	()I
    //   98: invokevirtual 362	com/logmein/gotoassistmdmsettings/b/f:b	(I)V
    //   101: new 8	com/logmein/gotoassistmdmsettings/a/a$a
    //   104: dup
    //   105: aload_0
    //   106: aload_1
    //   107: aload_2
    //   108: aload 4
    //   110: aload_3
    //   111: invokespecial 365	com/logmein/gotoassistmdmsettings/a/a$a:<init>	(Lcom/logmein/gotoassistmdmsettings/a/a;Landroid/content/Context;Lcom/logmein/gotoassistmdmsettings/a/b$a;Landroid/bluetooth/BluetoothAdapter;Lcom/logmein/gotoassistmdmsettings/b/f;)V
    //   114: astore 6
    //   116: aload_1
    //   117: aload 6
    //   119: aload 5
    //   121: invokevirtual 205	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   124: pop
    //   125: ldc_w 322
    //   128: ldc_w 367
    //   131: invokestatic 329	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: aload 4
    //   137: invokevirtual 370	android/bluetooth/BluetoothAdapter:startDiscovery	()Z
    //   140: ifne -111 -> 29
    //   143: ldc_w 322
    //   146: ldc_w 372
    //   149: invokestatic 329	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   152: pop
    //   153: aload_3
    //   154: aload_0
    //   155: aload_1
    //   156: aload 4
    //   158: invokespecial 374	com/logmein/gotoassistmdmsettings/a/a:a	(Landroid/content/Context;Landroid/bluetooth/BluetoothAdapter;)Ljava/util/List;
    //   161: invokevirtual 377	com/logmein/gotoassistmdmsettings/b/f:a	(Ljava/util/List;)V
    //   164: aload_3
    //   165: iconst_0
    //   166: invokevirtual 380	com/logmein/gotoassistmdmsettings/b/f:a	(Z)V
    //   169: aload_2
    //   170: aload_3
    //   171: invokeinterface 334 2 0
    //   176: aload_1
    //   177: aload 6
    //   179: invokevirtual 384	android/content/Context:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   182: goto -153 -> 29
    //   185: astore_1
    //   186: aload_0
    //   187: monitorexit
    //   188: aload_1
    //   189: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	190	0	this	a
    //   0	190	1	paramContext	Context
    //   0	190	2	paramA	b.a
    //   39	132	3	localF	f
    //   43	114	4	localBluetoothAdapter	BluetoothAdapter
    //   55	65	5	localIntentFilter	IntentFilter
    //   114	64	6	localA	a
    // Exception table:
    //   from	to	target	type
    //   2	29	185	finally
    //   32	182	185	finally
  }
  
  /* Error */
  public void a(Context paramContext, b.b paramB)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc_w 387
    //   6: invokevirtual 113	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   9: checkcast 389	android/telephony/TelephonyManager
    //   12: astore_3
    //   13: aload_3
    //   14: ifnonnull +13 -> 27
    //   17: aload_2
    //   18: aconst_null
    //   19: invokeinterface 394 2 0
    //   24: aload_0
    //   25: monitorexit
    //   26: return
    //   27: aload_0
    //   28: aload_3
    //   29: aload_1
    //   30: aload_2
    //   31: invokespecial 396	com/logmein/gotoassistmdmsettings/a/a:a	(Landroid/telephony/TelephonyManager;Landroid/content/Context;Lcom/logmein/gotoassistmdmsettings/a/b$b;)V
    //   34: goto -10 -> 24
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	this	a
    //   0	42	1	paramContext	Context
    //   0	42	2	paramB	b.b
    //   12	17	3	localTelephonyManager	TelephonyManager
    // Exception table:
    //   from	to	target	type
    //   2	13	37	finally
    //   17	24	37	finally
    //   27	34	37	finally
  }
  
  /* Error */
  public void a(Context paramContext, b.c paramC)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc -43
    //   5: invokevirtual 113	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   8: checkcast 215	android/net/wifi/WifiManager
    //   11: astore_3
    //   12: aload_3
    //   13: ifnull +17 -> 30
    //   16: aload_3
    //   17: invokevirtual 312	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   20: ifeq +10 -> 30
    //   23: aload_3
    //   24: invokevirtual 315	android/net/wifi/WifiManager:startScan	()Z
    //   27: ifne +13 -> 40
    //   30: aload_2
    //   31: aconst_null
    //   32: invokeinterface 399 2 0
    //   37: aload_0
    //   38: monitorexit
    //   39: return
    //   40: aload_0
    //   41: aload_1
    //   42: aload_2
    //   43: invokespecial 401	com/logmein/gotoassistmdmsettings/a/a:b	(Landroid/content/Context;Lcom/logmein/gotoassistmdmsettings/a/b$c;)V
    //   46: goto -9 -> 37
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	54	0	this	a
    //   0	54	1	paramContext	Context
    //   0	54	2	paramC	b.c
    //   11	13	3	localWifiManager	WifiManager
    // Exception table:
    //   from	to	target	type
    //   2	12	49	finally
    //   16	30	49	finally
    //   30	37	49	finally
    //   40	46	49	finally
  }
  
  public h b(Context paramContext)
  {
    label245:
    for (;;)
    {
      try
      {
        h localH = new h();
        localH.c(Build.VERSION.RELEASE);
        localH.d(Build.DEVICE);
        localH.e(Build.MODEL);
        localH.f(Build.PRODUCT);
        localH.g(Build.BRAND);
        localH.h(Build.DISPLAY);
        localH.i(Build.HARDWARE);
        localH.j(Build.MANUFACTURER);
        localH.k(Build.SERIAL);
        localH.b(Boolean.valueOf(paramContext.getResources().getBoolean(d.a.isTablet)));
        Object localObject = (LocationManager)paramContext.getSystemService("location");
        if (localObject != null)
        {
          if (((LocationManager)localObject).isProviderEnabled("gps")) {
            localH.a(Boolean.valueOf(true));
          }
        }
        else
        {
          localObject = BluetoothAdapter.getDefaultAdapter();
          if (localObject == null) {
            break label245;
          }
          localH.b(((BluetoothAdapter)localObject).getName());
          localH.c(Boolean.valueOf(((BluetoothAdapter)localObject).isEnabled()));
          localH.a(((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId());
          localObject = new DisplayMetrics();
          ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
          localH.l(Integer.valueOf(((DisplayMetrics)localObject).widthPixels).toString());
          localH.m(Integer.valueOf(((DisplayMetrics)localObject).heightPixels).toString());
          return localH;
        }
        localH.a(Boolean.valueOf(false));
        continue;
        localH.c(Boolean.valueOf(false));
      }
      finally {}
    }
  }
  
  /* Error */
  public com.logmein.gotoassistmdmsettings.b.i b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 272	java/io/RandomAccessFile
    //   5: dup
    //   6: ldc_w 530
    //   9: ldc_w 276
    //   12: invokespecial 279	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   15: astore 21
    //   17: aload 21
    //   19: invokevirtual 282	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   22: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   25: ldc_w 289
    //   28: invokevirtual 293	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   31: iconst_1
    //   32: aaload
    //   33: ldc_w 532
    //   36: ldc_w 534
    //   39: invokevirtual 538	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   42: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   45: invokestatic 543	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   48: invokevirtual 547	java/lang/Long:longValue	()J
    //   51: invokestatic 176	com/logmein/gotoassistmdmsettings/g/a:convertkbToMb	(J)D
    //   54: dstore_1
    //   55: aload 21
    //   57: invokevirtual 282	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   60: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   63: ldc_w 289
    //   66: invokevirtual 293	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   69: iconst_1
    //   70: aaload
    //   71: ldc_w 532
    //   74: ldc_w 534
    //   77: invokevirtual 538	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   80: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   83: invokestatic 543	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   86: invokevirtual 547	java/lang/Long:longValue	()J
    //   89: lstore 19
    //   91: lconst_0
    //   92: lstore 17
    //   94: lconst_0
    //   95: lstore 11
    //   97: aload 21
    //   99: invokevirtual 282	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   102: astore 22
    //   104: lload 11
    //   106: lstore 15
    //   108: lload 17
    //   110: lstore 13
    //   112: aload 22
    //   114: ifnull +99 -> 213
    //   117: aload 22
    //   119: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   122: ldc_w 289
    //   125: invokevirtual 293	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   128: astore 22
    //   130: aload 22
    //   132: iconst_0
    //   133: aaload
    //   134: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   137: astore 23
    //   139: aload 23
    //   141: ldc_w 549
    //   144: invokevirtual 552	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   147: ifeq +27 -> 174
    //   150: aload 22
    //   152: iconst_1
    //   153: aaload
    //   154: ldc_w 532
    //   157: ldc_w 534
    //   160: invokevirtual 538	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   163: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   166: invokestatic 543	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   169: invokevirtual 547	java/lang/Long:longValue	()J
    //   172: lstore 11
    //   174: aload 23
    //   176: ldc_w 554
    //   179: invokevirtual 552	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   182: ifeq +216 -> 398
    //   185: aload 22
    //   187: iconst_1
    //   188: aaload
    //   189: ldc_w 532
    //   192: ldc_w 534
    //   195: invokevirtual 538	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   198: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   201: invokestatic 543	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   204: invokevirtual 547	java/lang/Long:longValue	()J
    //   207: lstore 13
    //   209: lload 11
    //   211: lstore 15
    //   213: new 556	android/os/StatFs
    //   216: dup
    //   217: invokestatic 562	android/os/Environment:getRootDirectory	()Ljava/io/File;
    //   220: invokevirtual 567	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   223: invokespecial 568	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   226: astore 22
    //   228: new 556	android/os/StatFs
    //   231: dup
    //   232: invokestatic 571	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   235: invokevirtual 567	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   238: invokespecial 568	android/os/StatFs:<init>	(Ljava/lang/String;)V
    //   241: astore 23
    //   243: aload 22
    //   245: invokevirtual 574	android/os/StatFs:getBlockCount	()I
    //   248: i2l
    //   249: aload 22
    //   251: invokevirtual 577	android/os/StatFs:getBlockSize	()I
    //   254: i2l
    //   255: lmul
    //   256: invokestatic 580	com/logmein/gotoassistmdmsettings/g/a:convertbytesToMb	(J)D
    //   259: dstore_3
    //   260: aload 22
    //   262: invokevirtual 583	android/os/StatFs:getAvailableBlocks	()I
    //   265: i2l
    //   266: aload 22
    //   268: invokevirtual 577	android/os/StatFs:getBlockSize	()I
    //   271: i2l
    //   272: lmul
    //   273: invokestatic 580	com/logmein/gotoassistmdmsettings/g/a:convertbytesToMb	(J)D
    //   276: dstore 5
    //   278: aload 23
    //   280: invokevirtual 574	android/os/StatFs:getBlockCount	()I
    //   283: i2l
    //   284: aload 23
    //   286: invokevirtual 577	android/os/StatFs:getBlockSize	()I
    //   289: i2l
    //   290: lmul
    //   291: invokestatic 580	com/logmein/gotoassistmdmsettings/g/a:convertbytesToMb	(J)D
    //   294: dstore 7
    //   296: aload 23
    //   298: invokevirtual 583	android/os/StatFs:getAvailableBlocks	()I
    //   301: i2l
    //   302: lstore 11
    //   304: aload 23
    //   306: invokevirtual 577	android/os/StatFs:getBlockSize	()I
    //   309: i2l
    //   310: lload 11
    //   312: lmul
    //   313: invokestatic 580	com/logmein/gotoassistmdmsettings/g/a:convertbytesToMb	(J)D
    //   316: dstore 9
    //   318: new 585	com/logmein/gotoassistmdmsettings/b/i
    //   321: dup
    //   322: invokespecial 586	com/logmein/gotoassistmdmsettings/b/i:<init>	()V
    //   325: astore 22
    //   327: aload 22
    //   329: dload_1
    //   330: invokevirtual 589	com/logmein/gotoassistmdmsettings/b/i:a	(D)V
    //   333: aload 22
    //   335: lload 15
    //   337: lload 13
    //   339: lload 19
    //   341: ladd
    //   342: ladd
    //   343: invokestatic 176	com/logmein/gotoassistmdmsettings/g/a:convertkbToMb	(J)D
    //   346: invokevirtual 591	com/logmein/gotoassistmdmsettings/b/i:b	(D)V
    //   349: aload 22
    //   351: dload_3
    //   352: invokevirtual 593	com/logmein/gotoassistmdmsettings/b/i:c	(D)V
    //   355: aload 22
    //   357: dload 5
    //   359: invokevirtual 595	com/logmein/gotoassistmdmsettings/b/i:d	(D)V
    //   362: aload 22
    //   364: dload 7
    //   366: invokevirtual 597	com/logmein/gotoassistmdmsettings/b/i:e	(D)V
    //   369: aload 22
    //   371: dload 9
    //   373: invokevirtual 599	com/logmein/gotoassistmdmsettings/b/i:f	(D)V
    //   376: aload 22
    //   378: invokestatic 602	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   381: invokevirtual 603	com/logmein/gotoassistmdmsettings/b/i:a	(Ljava/lang/String;)V
    //   384: aload 21
    //   386: invokevirtual 299	java/io/RandomAccessFile:close	()V
    //   389: aload 22
    //   391: astore 21
    //   393: aload_0
    //   394: monitorexit
    //   395: aload 21
    //   397: areturn
    //   398: aload 21
    //   400: invokevirtual 282	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   403: astore 22
    //   405: goto -301 -> 104
    //   408: astore 22
    //   410: aconst_null
    //   411: astore 21
    //   413: aload 22
    //   415: invokevirtual 302	java/io/FileNotFoundException:printStackTrace	()V
    //   418: aload 21
    //   420: invokestatic 306	com/logmein/gotoassistmdmsettings/g/a:closeFile	(Ljava/io/RandomAccessFile;)V
    //   423: aconst_null
    //   424: astore 21
    //   426: goto -33 -> 393
    //   429: aload 22
    //   431: invokevirtual 307	java/io/IOException:printStackTrace	()V
    //   434: goto -16 -> 418
    //   437: astore 21
    //   439: aload_0
    //   440: monitorexit
    //   441: aload 21
    //   443: athrow
    //   444: astore 22
    //   446: goto -17 -> 429
    //   449: astore 22
    //   451: goto -38 -> 413
    //   454: astore 22
    //   456: aconst_null
    //   457: astore 21
    //   459: goto -30 -> 429
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	462	0	this	a
    //   54	276	1	d1	double
    //   259	93	3	d2	double
    //   276	82	5	d3	double
    //   294	71	7	d4	double
    //   316	56	9	d5	double
    //   95	216	11	l1	long
    //   110	228	13	l2	long
    //   106	230	15	l3	long
    //   92	17	17	l4	long
    //   89	251	19	l5	long
    //   15	410	21	localObject1	Object
    //   437	5	21	localObject2	Object
    //   457	1	21	localObject3	Object
    //   102	302	22	localObject4	Object
    //   408	22	22	localFileNotFoundException1	java.io.FileNotFoundException
    //   444	1	22	localIOException1	java.io.IOException
    //   449	1	22	localFileNotFoundException2	java.io.FileNotFoundException
    //   454	1	22	localIOException2	java.io.IOException
    //   137	168	23	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   2	17	408	java/io/FileNotFoundException
    //   2	17	437	finally
    //   17	91	437	finally
    //   97	104	437	finally
    //   117	139	437	finally
    //   139	174	437	finally
    //   174	209	437	finally
    //   213	389	437	finally
    //   398	405	437	finally
    //   413	418	437	finally
    //   418	423	437	finally
    //   429	434	437	finally
    //   17	91	444	java/io/IOException
    //   97	104	444	java/io/IOException
    //   117	139	444	java/io/IOException
    //   139	174	444	java/io/IOException
    //   174	209	444	java/io/IOException
    //   213	389	444	java/io/IOException
    //   398	405	444	java/io/IOException
    //   17	91	449	java/io/FileNotFoundException
    //   97	104	449	java/io/FileNotFoundException
    //   117	139	449	java/io/FileNotFoundException
    //   139	174	449	java/io/FileNotFoundException
    //   174	209	449	java/io/FileNotFoundException
    //   213	389	449	java/io/FileNotFoundException
    //   398	405	449	java/io/FileNotFoundException
    //   2	17	454	java/io/IOException
  }
  
  public r c(Context paramContext)
  {
    for (;;)
    {
      try
      {
        int i = j(paramContext);
        if (i == 1)
        {
          paramContext = null;
          return paramContext;
        }
        Object localObject = (WifiManager)paramContext.getSystemService("wifi");
        if ((localObject != null) && (((WifiManager)localObject).getWifiState() == 3))
        {
          WifiInfo localWifiInfo = ((WifiManager)localObject).getConnectionInfo();
          if ((localWifiInfo != null) && (localWifiInfo.getNetworkId() != -1) && (localWifiInfo.getLinkSpeed() > 0))
          {
            localObject = new r();
            ((r)localObject).e(com.logmein.gotoassistmdmsettings.g.a.formatIpAddress(localWifiInfo.getIpAddress()));
            ((r)localObject).b(com.logmein.gotoassistmdmsettings.g.a.removeQuotesFromStr(localWifiInfo.getSSID()));
            ((r)localObject).a(localWifiInfo.getNetworkId());
            ((r)localObject).c(com.logmein.gotoassistmdmsettings.g.a.getSignalStrength(paramContext, localWifiInfo.getRssi()));
            ((r)localObject).b(Integer.valueOf(localWifiInfo.getLinkSpeed()).intValue());
            ((r)localObject).a(paramContext.getResources().getString(d.e.wifi_status_current));
            ((r)localObject).d(com.logmein.gotoassistmdmsettings.g.a.getWifiEncryptionType(paramContext, localWifiInfo.getSSID()));
            paramContext = (Context)localObject;
            continue;
          }
        }
        paramContext = null;
      }
      finally {}
    }
  }
  
  public List<r> d(Context paramContext)
  {
    if (j(paramContext) == 1) {
      return null;
    }
    return l(paramContext);
  }
  
  public List<c> e(Context paramContext)
  {
    ArrayList localArrayList;
    Object localObject;
    HashMap localHashMap;
    for (;;)
    {
      PackageManager localPackageManager;
      ApplicationInfo localApplicationInfo;
      c localC;
      String str;
      try
      {
        localArrayList = new ArrayList();
        localPackageManager = paramContext.getPackageManager();
        localObject = localPackageManager.getInstalledApplications(0);
        localHashMap = new HashMap();
        Iterator localIterator = ((List)localObject).iterator();
        if (!localIterator.hasNext()) {
          break label273;
        }
        localApplicationInfo = (ApplicationInfo)localIterator.next();
        localC = new c();
        if (paramContext.getPackageManager().getLaunchIntentForPackage(localApplicationInfo.packageName) != null)
        {
          bool = true;
          localC.a(Boolean.valueOf(bool));
          if ((localApplicationInfo.flags & 0x81) != 1) {
            break label253;
          }
          bool = true;
          localC.b(Boolean.valueOf(bool));
          localC.d(localApplicationInfo.loadLabel(paramContext.getPackageManager()).toString());
          localC.e(localApplicationInfo.packageName);
          localC.a(localApplicationInfo.processName);
          localObject = null;
        }
      }
      finally {}
      try
      {
        str = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 0).versionName;
        localObject = str;
      }
      catch (Exception localException)
      {
        Log.d("MDS DATA", "Error getting the package name");
        continue;
      }
      localC.c((String)localObject);
      localC.f(Integer.valueOf(localApplicationInfo.uid).toString());
      localC.a(com.logmein.gotoassistmdmsettings.g.a.a.b);
      localHashMap.put(localApplicationInfo.packageName, localC);
      localArrayList.add(localC);
      continue;
      boolean bool = false;
      continue;
      label253:
      bool = false;
    }
    label273:
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    for (;;)
    {
      String[] arrayOfString;
      int i;
      if (paramContext.hasNext())
      {
        localObject = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        arrayOfString = ((ActivityManager.RunningAppProcessInfo)localObject).pkgList;
        if (arrayOfString != null) {
          i = 0;
        }
      }
      else
      {
        while (i < arrayOfString.length)
        {
          if (localHashMap.containsKey(arrayOfString[i]))
          {
            ((c)localHashMap.get(arrayOfString[i])).a(com.logmein.gotoassistmdmsettings.g.a.a.a);
            ((c)localHashMap.get(arrayOfString[i])).b(Integer.valueOf(((ActivityManager.RunningAppProcessInfo)localObject).pid).toString());
            break label401;
            paramContext = com.logmein.gotoassistmdmsettings.g.a.sortInstalledAppsByState(localArrayList);
            return paramContext;
          }
          label401:
          i += 1;
        }
      }
    }
  }
  
  public List<com.logmein.gotoassistmdmsettings.b.a> f(Context paramContext)
  {
    try
    {
      paramContext = AccountManager.get(paramContext).getAccounts();
      ArrayList localArrayList = new ArrayList();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramContext[i];
        com.logmein.gotoassistmdmsettings.b.a localA = new com.logmein.gotoassistmdmsettings.b.a();
        localA.a(localObject.name);
        localA.b(localObject.type);
        localArrayList.add(localA);
        i += 1;
      }
      return localArrayList;
    }
    finally {}
  }
  
  public d g(Context paramContext)
  {
    try
    {
      Intent localIntent = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      d localD = new d();
      localD.a(com.logmein.gotoassistmdmsettings.g.a.getBatteryHealth(paramContext, localIntent.getIntExtra("health", 0)));
      int i = localIntent.getIntExtra("level", 0);
      int j = localIntent.getIntExtra("scale", 0);
      localD.b(com.logmein.gotoassistmdmsettings.g.a.formatBatteryLevel(Integer.valueOf(Math.round(i / j * 100.0F)).toString()));
      localD.c(com.logmein.gotoassistmdmsettings.g.a.getBatterPluggedStatus(paramContext, localIntent.getIntExtra("plugged", 0)));
      localD.a(Boolean.valueOf(localIntent.getExtras().getBoolean("present")));
      localD.d(com.logmein.gotoassistmdmsettings.g.a.getBatteryStatus(paramContext, localIntent.getIntExtra("status", 0)));
      localD.e(localIntent.getExtras().getString("technology"));
      localD.f(Integer.valueOf(localIntent.getIntExtra("temperature", 0) / 10).toString());
      localD.g(Integer.valueOf(localIntent.getIntExtra("voltage", 0)).toString());
      return localD;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  /* Error */
  public f h(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 320	com/logmein/gotoassistmdmsettings/a/a:k	(Landroid/content/Context;)I
    //   7: istore_2
    //   8: iload_2
    //   9: bipush 10
    //   11: if_icmpne +9 -> 20
    //   14: aconst_null
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: areturn
    //   20: new 336	com/logmein/gotoassistmdmsettings/b/f
    //   23: dup
    //   24: invokespecial 337	com/logmein/gotoassistmdmsettings/b/f:<init>	()V
    //   27: astore_3
    //   28: invokestatic 341	android/bluetooth/BluetoothAdapter:getDefaultAdapter	()Landroid/bluetooth/BluetoothAdapter;
    //   31: astore 4
    //   33: aload_3
    //   34: aload 4
    //   36: invokevirtual 354	android/bluetooth/BluetoothAdapter:getScanMode	()I
    //   39: invokevirtual 357	com/logmein/gotoassistmdmsettings/b/f:a	(I)V
    //   42: aload_3
    //   43: aload 4
    //   45: invokevirtual 360	android/bluetooth/BluetoothAdapter:getState	()I
    //   48: invokevirtual 362	com/logmein/gotoassistmdmsettings/b/f:b	(I)V
    //   51: aload_3
    //   52: aload_0
    //   53: aload_1
    //   54: aload 4
    //   56: invokespecial 374	com/logmein/gotoassistmdmsettings/a/a:a	(Landroid/content/Context;Landroid/bluetooth/BluetoothAdapter;)Ljava/util/List;
    //   59: invokevirtual 377	com/logmein/gotoassistmdmsettings/b/f:a	(Ljava/util/List;)V
    //   62: aload_3
    //   63: astore_1
    //   64: goto -48 -> 16
    //   67: astore_1
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_1
    //   71: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	this	a
    //   0	72	1	paramContext	Context
    //   7	5	2	i	int
    //   27	36	3	localF	f
    //   31	24	4	localBluetoothAdapter	BluetoothAdapter
    // Exception table:
    //   from	to	target	type
    //   2	8	67	finally
    //   20	62	67	finally
  }
  
  public List<l> i(Context paramContext)
  {
    long l1 = com.logmein.gotoassistmdmsettings.g.a.readCpuTime();
    HashMap localHashMap1 = com.logmein.gotoassistmdmsettings.g.a.readProcInfo();
    try
    {
      Thread.sleep(3000L);
      long l2 = com.logmein.gotoassistmdmsettings.g.a.readCpuTime();
      HashMap localHashMap2 = com.logmein.gotoassistmdmsettings.g.a.readProcInfo();
      Iterator localIterator = localHashMap1.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (localHashMap2.containsKey(str)) {
          ((l)localHashMap2.get(str)).a(Long.valueOf(com.logmein.gotoassistmdmsettings.g.a.calculateCPUUsage(Long.valueOf(((l)localHashMap1.get(str)).e()), Long.valueOf(((l)localHashMap2.get(str)).e()), Long.valueOf(l2 - l1))).longValue());
        }
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        Log.d("MDS DATA", "gathering processes wait interrupred");
      }
      a(localInterruptedException, paramContext);
      return com.logmein.gotoassistmdmsettings.g.a.sortProcessesByCPUUsage(new ArrayList(localInterruptedException.values()));
    }
  }
  
  /* Error */
  public int j(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc -43
    //   5: invokevirtual 113	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   8: checkcast 215	android/net/wifi/WifiManager
    //   11: astore_1
    //   12: aload_1
    //   13: ifnull +12 -> 25
    //   16: aload_1
    //   17: invokevirtual 609	android/net/wifi/WifiManager:getWifiState	()I
    //   20: istore_2
    //   21: aload_0
    //   22: monitorexit
    //   23: iload_2
    //   24: ireturn
    //   25: iconst_1
    //   26: istore_2
    //   27: goto -6 -> 21
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	35	0	this	a
    //   0	35	1	paramContext	Context
    //   20	7	2	i	int
    // Exception table:
    //   from	to	target	type
    //   2	12	30	finally
    //   16	21	30	finally
  }
  
  /* Error */
  public int k(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 341	android/bluetooth/BluetoothAdapter:getDefaultAdapter	()Landroid/bluetooth/BluetoothAdapter;
    //   5: astore_1
    //   6: aload_1
    //   7: ifnull +12 -> 19
    //   10: aload_1
    //   11: invokevirtual 360	android/bluetooth/BluetoothAdapter:getState	()I
    //   14: istore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: iload_2
    //   18: ireturn
    //   19: bipush 10
    //   21: istore_2
    //   22: goto -7 -> 15
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	30	0	this	a
    //   0	30	1	paramContext	Context
    //   14	8	2	i	int
    // Exception table:
    //   from	to	target	type
    //   2	6	25	finally
    //   10	15	25	finally
  }
  
  private class a
    extends BroadcastReceiver
  {
    private b.a b;
    private Context c;
    private HashMap<String, e> d;
    private BluetoothAdapter e;
    private f f;
    
    public a(Context paramContext, b.a paramA, BluetoothAdapter paramBluetoothAdapter, f paramF)
    {
      this.c = paramContext;
      this.b = paramA;
      this.d = new HashMap();
      this.e = paramBluetoothAdapter;
      this.f = paramF;
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Object localObject1 = paramIntent.getAction();
      Object localObject2;
      if ("android.bluetooth.device.action.FOUND".equals(localObject1))
      {
        localObject2 = (BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        Log.d("MDS DATA", "Bluetooth action found");
        localObject1 = paramIntent.getStringExtra("android.bluetooth.device.extra.NAME");
        String str = com.logmein.gotoassistmdmsettings.g.a.getSignalStrength(paramContext, paramIntent.getShortExtra("android.bluetooth.device.extra.RSSI", (short)Short.MIN_VALUE));
        e localE = new e();
        paramIntent = (Intent)localObject1;
        if (localObject1 == null) {
          paramIntent = ((BluetoothDevice)localObject2).getAddress();
        }
        Log.d("BLUETOOTH", paramIntent);
        localE.c(paramIntent);
        localE.e(com.logmein.gotoassistmdmsettings.g.a.getBluetoothMajorDeviceClass(paramContext, ((BluetoothDevice)localObject2).getBluetoothClass().getMajorDeviceClass()));
        localE.d(com.logmein.gotoassistmdmsettings.g.a.getBluetoothBondState(paramContext, ((BluetoothDevice)localObject2).getBondState()));
        localE.b(str);
        localE.a(((BluetoothDevice)localObject2).getAddress());
        this.d.put(((BluetoothDevice)localObject2).getAddress(), localE);
      }
      do
      {
        return;
        if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(localObject1))
        {
          Log.d("MDS DATA", "bluetooth discovery started");
          return;
        }
      } while (!"android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(localObject1));
      paramIntent = this.e.getBondedDevices().iterator();
      while (paramIntent.hasNext())
      {
        localObject1 = (BluetoothDevice)paramIntent.next();
        if (!this.d.containsKey(((BluetoothDevice)localObject1).getAddress()))
        {
          localObject2 = new e();
          ((e)localObject2).c(((BluetoothDevice)localObject1).getName());
          ((e)localObject2).e(com.logmein.gotoassistmdmsettings.g.a.getBluetoothMajorDeviceClass(paramContext, ((BluetoothDevice)localObject1).getBluetoothClass().getMajorDeviceClass()));
          ((e)localObject2).d(com.logmein.gotoassistmdmsettings.g.a.getBluetoothBondState(paramContext, ((BluetoothDevice)localObject1).getBondState()));
          ((e)localObject2).a(((BluetoothDevice)localObject1).getAddress());
          this.d.put(((BluetoothDevice)localObject1).getAddress(), localObject2);
        }
      }
      this.c.unregisterReceiver(this);
      this.e.cancelDiscovery();
      this.f.a(new ArrayList(this.d.values()));
      this.f.a(true);
      this.b.a(this.f);
      Log.d("MDS DATA", "bluetooth discovery finished");
    }
  }
  
  public class b
    extends PhoneStateListener
  {
    private b.b b;
    private TelephonyManager c;
    private Context d;
    
    public b(b.b paramB, TelephonyManager paramTelephonyManager, Context paramContext)
    {
      this.b = paramB;
      this.c = paramTelephonyManager;
      this.d = paramContext;
    }
    
    private j b()
    {
      j localJ = new j();
      localJ.e(com.logmein.gotoassistmdmsettings.g.a.getPhoneTypeStr(this.c.getPhoneType()));
      localJ.j(this.c.getNetworkCountryIso());
      localJ.g(this.c.getNetworkOperator());
      localJ.f(com.logmein.gotoassistmdmsettings.g.a.getNetworkTypeStr(this.c.getNetworkType()));
      localJ.a(Boolean.valueOf(this.c.isNetworkRoaming()));
      localJ.h(com.logmein.gotoassistmdmsettings.g.a.getApproximateMobileDataSpeed(this.c.getNetworkType()));
      localJ.d(com.logmein.gotoassistmdmsettings.g.a.getMobileDataStateSty(this.c.getDataState()));
      localJ.b(this.c.getNetworkOperatorName());
      localJ.c(this.c.getLine1Number());
      localJ.a(com.logmein.gotoassistmdmsettings.g.a.getSimStateStr(this.d, this.c.getSimState()));
      return localJ;
    }
    
    public void a()
    {
      if (this.c.getSimState() == 5)
      {
        this.c.listen(this, 256);
        return;
      }
      this.b.a(b());
    }
    
    public void onSignalStrengthsChanged(SignalStrength paramSignalStrength)
    {
      super.onSignalStrengthsChanged(paramSignalStrength);
      j localJ = b();
      localJ.i(com.logmein.gotoassistmdmsettings.g.a.getMobileDataSignalStrength(this.d, paramSignalStrength));
      this.b.a(localJ);
      this.c.listen(this, 0);
    }
  }
  
  private class c
    extends BroadcastReceiver
  {
    private b.c b;
    private Context c;
    
    public c(Context paramContext, b.c paramC)
    {
      this.b = paramC;
      this.c = paramContext;
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      this.c.unregisterReceiver(this);
      this.b.a(a.this.l(paramContext));
    }
  }
}
