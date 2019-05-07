package com.beaconsinspace.android.beacon.detector;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import com.beaconsinspace.android.beacon.detector.fgchecker.Utils;
import donthackbro.p;
import donthackbro.r;
import donthackbro.s;
import donthackbro.t;
import donthackbro.v;
import donthackbro.x;
import donthackbro.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;

public class BISDetector
  extends Service
  implements r
{
  public static String SDK_VERSION = "1.3.9.4";
  public static String a = "";
  public static String b = "";
  public static UUID c;
  public static BISDetector d = new BISDetector();
  static s e = new s();
  static BISDetectorDelegate f;
  public static Context g;
  private static final String j = "BIS_API";
  private static Thread l;
  private static Thread m;
  private Thread k;
  private HandlerThread n;
  private Handler o;
  private BISProcessManager p;
  
  public BISDetector() {}
  
  static void a()
  {
    if (!isLocationServiceEnabled())
    {
      Log.e("BIS_API", "Location Services are not enabled. Please enable them in Settings.");
      if (f != null) {
        f.onBISError(409, "Location Services are not enabled. Please enable them in Settings.");
      }
    }
    do
    {
      do
      {
        return;
        if (isInternetAvailable()) {
          break;
        }
        Log.e("BIS_API", "Network Services are not enabled. Please enable them in Settings.");
      } while (f == null);
      f.onBISError(409, "Network Services are not enabled. Please enable them in Settings.");
      return;
      if (isBluetoothEnabled()) {
        break;
      }
      Log.e("BIS_API", "Bluetooth is not enabled. Please turn it on to proceed");
    } while (f == null);
    f.onBISError(409, "Bluetooth is not enabled. Please turn it on to proceed");
    return;
    if (g()) {
      startRanging();
    }
    h();
    i();
    Log.i("BIS_API", "BeaconsInSpace has bootstrapped successfully");
  }
  
  private void a(Context paramContext)
  {
    paramContext.getPackageManager().getInstalledApplications(128);
  }
  
  private void a(String paramString, Context paramContext)
  {
    if (paramString != null) {}
    for (;;)
    {
      a = paramString;
      p.a();
      paramString = p.a("collectForegroundProcess");
      BISLog.i("BIS_API", "processCollectionConfig: " + paramString);
      if (paramString.equals("1")) {
        d.l();
      }
      if (f()) {
        break;
      }
      Log.d("BIS_API", "This device is not supported. BeaconsInSpace Detector shutting down");
      return;
      paramString = "";
    }
    c = new v(paramContext).a();
    c();
    e.a(paramContext);
    if (!paramString.equals("-1")) {
      BISLog.i("BIS_API", "Starting background process collection");
    }
    try
    {
      d.p = new BISProcessManager(g);
      this.n = new HandlerThread("ActiveProcessesThread");
      this.n.start();
      this.o = new Handler(this.n.getLooper());
      k();
      paramString = d;
      t.a(paramString);
      BISDetectorServicesListener.setDelegate(paramString);
      s.a(paramString);
      return;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        BISLog.e("BIS_API", "Error starting Process Collection", paramString);
      }
    }
  }
  
  static s b()
  {
    return e;
  }
  
  static void c()
  {
    if (g == null) {
      return;
    }
    new Thread()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: getstatic 28	com/beaconsinspace/android/beacon/detector/BISDetector:g	Landroid/content/Context;
        //   3: invokestatic 34	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
        //   6: astore_2
        //   7: aload_2
        //   8: ifnonnull +7 -> 15
        //   11: invokestatic 37	com/beaconsinspace/android/beacon/detector/BISDetector:e	()V
        //   14: return
        //   15: aload_2
        //   16: invokevirtual 43	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
        //   19: ifeq +51 -> 70
        //   22: ldc 45
        //   24: astore_2
        //   25: aload_2
        //   26: putstatic 49	com/beaconsinspace/android/beacon/detector/BISDetector:b	Ljava/lang/String;
        //   29: getstatic 49	com/beaconsinspace/android/beacon/detector/BISDetector:b	Ljava/lang/String;
        //   32: ifnull +46 -> 78
        //   35: getstatic 49	com/beaconsinspace/android/beacon/detector/BISDetector:b	Ljava/lang/String;
        //   38: invokevirtual 55	java/lang/String:length	()I
        //   41: ifle +37 -> 78
        //   44: iconst_1
        //   45: istore_1
        //   46: new 57	android/os/Handler
        //   49: dup
        //   50: invokestatic 63	android/os/Looper:getMainLooper	()Landroid/os/Looper;
        //   53: invokespecial 66	android/os/Handler:<init>	(Landroid/os/Looper;)V
        //   56: new 11	com/beaconsinspace/android/beacon/detector/BISDetector$2$1
        //   59: dup
        //   60: aload_0
        //   61: iload_1
        //   62: invokespecial 69	com/beaconsinspace/android/beacon/detector/BISDetector$2$1:<init>	(Lcom/beaconsinspace/android/beacon/detector/BISDetector$2;Z)V
        //   65: invokevirtual 73	android/os/Handler:post	(Ljava/lang/Runnable;)Z
        //   68: pop
        //   69: return
        //   70: aload_2
        //   71: invokevirtual 77	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
        //   74: astore_2
        //   75: goto -50 -> 25
        //   78: iconst_0
        //   79: istore_1
        //   80: goto -34 -> 46
        //   83: astore_2
        //   84: ldc 79
        //   86: new 81	java/lang/StringBuilder
        //   89: dup
        //   90: invokespecial 82	java/lang/StringBuilder:<init>	()V
        //   93: ldc 84
        //   95: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   98: aload_2
        //   99: invokevirtual 91	java/io/IOException:toString	()Ljava/lang/String;
        //   102: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   108: invokestatic 97	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   111: pop
        //   112: iconst_0
        //   113: ifne +7 -> 120
        //   116: invokestatic 37	com/beaconsinspace/android/beacon/detector/BISDetector:e	()V
        //   119: return
        //   120: new 99	java/lang/NullPointerException
        //   123: dup
        //   124: invokespecial 100	java/lang/NullPointerException:<init>	()V
        //   127: athrow
        //   128: astore_2
        //   129: ldc 79
        //   131: new 81	java/lang/StringBuilder
        //   134: dup
        //   135: invokespecial 82	java/lang/StringBuilder:<init>	()V
        //   138: ldc 84
        //   140: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: aload_2
        //   144: invokevirtual 101	com/google/android/gms/common/GooglePlayServicesRepairableException:toString	()Ljava/lang/String;
        //   147: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   153: invokestatic 97	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   156: pop
        //   157: iconst_0
        //   158: ifne +7 -> 165
        //   161: invokestatic 37	com/beaconsinspace/android/beacon/detector/BISDetector:e	()V
        //   164: return
        //   165: new 99	java/lang/NullPointerException
        //   168: dup
        //   169: invokespecial 100	java/lang/NullPointerException:<init>	()V
        //   172: athrow
        //   173: astore_2
        //   174: ldc 79
        //   176: new 81	java/lang/StringBuilder
        //   179: dup
        //   180: invokespecial 82	java/lang/StringBuilder:<init>	()V
        //   183: ldc 84
        //   185: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   188: aload_2
        //   189: invokevirtual 102	com/google/android/gms/common/GooglePlayServicesNotAvailableException:toString	()Ljava/lang/String;
        //   192: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   198: invokestatic 97	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   201: pop
        //   202: iconst_0
        //   203: ifne +7 -> 210
        //   206: invokestatic 37	com/beaconsinspace/android/beacon/detector/BISDetector:e	()V
        //   209: return
        //   210: new 99	java/lang/NullPointerException
        //   213: dup
        //   214: invokespecial 100	java/lang/NullPointerException:<init>	()V
        //   217: athrow
        //   218: astore_2
        //   219: ldc 79
        //   221: new 81	java/lang/StringBuilder
        //   224: dup
        //   225: invokespecial 82	java/lang/StringBuilder:<init>	()V
        //   228: ldc 84
        //   230: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   233: aload_2
        //   234: invokevirtual 103	java/lang/Exception:toString	()Ljava/lang/String;
        //   237: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   243: invokestatic 97	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   246: pop
        //   247: iconst_0
        //   248: ifne +7 -> 255
        //   251: invokestatic 37	com/beaconsinspace/android/beacon/detector/BISDetector:e	()V
        //   254: return
        //   255: new 99	java/lang/NullPointerException
        //   258: dup
        //   259: invokespecial 100	java/lang/NullPointerException:<init>	()V
        //   262: athrow
        //   263: astore_2
        //   264: iconst_0
        //   265: ifne +8 -> 273
        //   268: invokestatic 37	com/beaconsinspace/android/beacon/detector/BISDetector:e	()V
        //   271: aload_2
        //   272: athrow
        //   273: new 99	java/lang/NullPointerException
        //   276: dup
        //   277: invokespecial 100	java/lang/NullPointerException:<init>	()V
        //   280: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	281	0	this	2
        //   45	35	1	bool	boolean
        //   6	69	2	localObject1	Object
        //   83	16	2	localIOException	java.io.IOException
        //   128	16	2	localGooglePlayServicesRepairableException	com.google.android.gms.common.GooglePlayServicesRepairableException
        //   173	16	2	localGooglePlayServicesNotAvailableException	com.google.android.gms.common.GooglePlayServicesNotAvailableException
        //   218	16	2	localException	Exception
        //   263	9	2	localObject2	Object
        // Exception table:
        //   from	to	target	type
        //   0	7	83	java/io/IOException
        //   0	7	128	com/google/android/gms/common/GooglePlayServicesRepairableException
        //   0	7	173	com/google/android/gms/common/GooglePlayServicesNotAvailableException
        //   0	7	218	java/lang/Exception
        //   0	7	263	finally
        //   84	112	263	finally
        //   129	157	263	finally
        //   174	202	263	finally
        //   219	247	263	finally
      }
    }.start();
  }
  
  public static void configure(String paramString, Context paramContext, BISDetectorDelegate paramBISDetectorDelegate)
  {
    Log.d("BIS_API", "Configuring BISDetector SDK " + SDK_VERSION);
    a = paramString;
    g = paramContext.getApplicationContext();
    f = paramBISDetectorDelegate;
    y.a("BIS_API_KEY", paramString);
    d.a(g);
    if (!y.b()) {
      d.j();
    }
    g.sendBroadcast(new Intent("BootstrapBeaconsInSpace"));
  }
  
  static void d() {}
  
  static void e() {}
  
  private static boolean f()
  {
    if (Build.VERSION.SDK_INT < 18)
    {
      Log.e("BIS_API", "BeaconsInSpace Detector Library does not run on Android: " + Build.VERSION.SDK_INT);
      return false;
    }
    return true;
  }
  
  private static boolean g()
  {
    Object localObject = new ArrayList();
    try
    {
      JSONArray localJSONArray = new JSONArray(p.a("unsupportedAndroidModels"));
      int i = 0;
      while (i < localJSONArray.length())
      {
        ((List)localObject).add(localJSONArray.getString(i));
        i += 1;
      }
      String str1;
      String str2;
      return true;
    }
    catch (JSONException localJSONException)
    {
      BISLog.e("BIS_API", "JsonException occurred while retrieving unsupportedDeviceModels.", localJSONException);
      str1 = Build.MODEL;
      localObject = ((List)localObject).iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        str2 = (String)((Iterator)localObject).next();
      } while (!str1.equals(str2));
      BISLog.e("BIS_API", "BeaconsInSpace Detector Library does not run on Android Model " + str2 + " due to Networking/Bluetooth collision issues.");
      return false;
    }
  }
  
  public static Context getContext()
  {
    return g;
  }
  
  private static void h()
  {
    if (l == null)
    {
      l = new Thread()
      {
        public void run()
        {
          try
          {
            for (;;)
            {
              String str = p.a("locationMonitoringInterval");
              if (str == null) {
                break;
              }
              if (str.equals("0")) {
                return;
              }
              int i = Integer.parseInt(str);
              x.a("CONTINUOUS_PROCESSING");
              Thread.sleep(60000L);
              t.a(x.b("CONTINUOUS_PROCESSING"), true);
              Thread.sleep(i);
            }
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      };
      l.start();
    }
  }
  
  private static void i()
  {
    if (m == null)
    {
      m = new Thread()
      {
        public void run()
        {
          try
          {
            for (;;)
            {
              Thread.sleep(10800000L);
              p.a();
              BISDetector.e.a();
            }
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      };
      m.start();
    }
  }
  
  public static boolean isBluetoothEnabled()
  {
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (localBluetoothAdapter == null) {
      return false;
    }
    return localBluetoothAdapter.isEnabled();
  }
  
  public static boolean isInternetAvailable()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)g.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public static boolean isLocationServiceEnabled()
  {
    if (g == null) {}
    for (;;)
    {
      return false;
      boolean bool1;
      if (Build.VERSION.SDK_INT >= 19) {
        for (;;)
        {
          try
          {
            int i = Settings.Secure.getInt(g.getContentResolver(), "location_mode");
            if (i != 0)
            {
              bool1 = true;
              return bool1;
            }
          }
          catch (Settings.SettingNotFoundException localSettingNotFoundException)
          {
            Log.e("BIS_API", "Failed to check for location services: " + localSettingNotFoundException.getMessage());
            return false;
          }
          bool1 = false;
        }
      }
      LocationManager localLocationManager = (LocationManager)g.getSystemService("location");
      try
      {
        bool1 = localLocationManager.isProviderEnabled("gps");
      }
      catch (Exception localException2)
      {
        try
        {
          for (;;)
          {
            bool2 = localLocationManager.isProviderEnabled("network");
            if ((!bool1) && (!bool2)) {
              break;
            }
            return true;
            localException2 = localException2;
            bool1 = false;
          }
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            boolean bool2 = false;
          }
        }
      }
    }
  }
  
  private void j()
  {
    Intent localIntent = new Intent(g, BISDeviceAtlas.class);
    localIntent.setFlags(268435456);
    g.startActivity(localIntent);
  }
  
  private void k()
  {
    this.o.postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          BISDetector.d.getBisProcessManager().a();
          BISDetector.a(BISDetector.this);
          return;
        }
        catch (Exception localException)
        {
          BISLog.e("BIS_API", "Exception thrown in process collection", localException);
          return;
        }
        catch (Throwable localThrowable)
        {
          BISLog.e("BIS_API", "Throwable Exception thrown in process collection");
        }
      }
    }, 60000L);
  }
  
  private void l()
  {
    if ((Build.VERSION.SDK_INT >= 21) && (!Utils.hasUsageStatsPermission(g)))
    {
      Intent localIntent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
      localIntent.setFlags(268435456);
      g.startActivity(localIntent);
    }
  }
  
  public static void setBeaconManagerScanPeriods(Long paramLong1, Long paramLong2, Long paramLong3, Long paramLong4)
  {
    e.a(paramLong1, paramLong2, paramLong3, paramLong4);
  }
  
  public static void setDebug(boolean paramBoolean)
  {
    BISLog.a = paramBoolean;
  }
  
  public static void startRanging()
  {
    e.a(g);
    e.b();
    Log.d("BIS_API", "BLE ranging has begun");
  }
  
  public static void stopRanging()
  {
    e.c();
  }
  
  public BISProcessManager getBisProcessManager()
  {
    return this.p;
  }
  
  public void onBeaconEnter(String paramString)
  {
    if (f != null) {
      f.didEnterBISRegion(paramString);
    }
  }
  
  public void onBeaconExit(String paramString)
  {
    if (f != null) {
      f.didExitBISRegion(paramString);
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onDestroy()
  {
    sendBroadcast(new Intent("BootstrapBeaconsInSpace"));
    super.onDestroy();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    g = getApplicationContext();
    a = y.a("BIS_API_KEY");
    if (this.k == null)
    {
      this.k = new Thread()
      {
        public void run()
        {
          try
          {
            BISDetector.a(BISDetector.this, BISDetector.a, BISDetector.g);
            return;
          }
          catch (Throwable localThrowable)
          {
            BISLog.e("BIS_API", localThrowable.getMessage());
          }
        }
      };
      this.k.start();
    }
    return 1;
  }
}
