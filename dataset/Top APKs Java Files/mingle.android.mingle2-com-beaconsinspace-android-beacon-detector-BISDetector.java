package com.beaconsinspace.android.beacon.detector;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;

public class BISDetector
  extends Service
  implements c
{
  public static String SDK_VERSION = "1.3.9";
  static String a = "";
  static String b = "";
  static UUID c;
  static BISDetector d = new BISDetector();
  static d e = new d();
  static BISDetectorDelegate f;
  static Context g;
  private static Thread i;
  private static Thread j;
  private Thread h;
  private HandlerThread k;
  private Handler l;
  private BISProcessManager m;
  
  public BISDetector() {}
  
  static void a() {}
  
  static void b() {}
  
  private static boolean c()
  {
    Object localObject = new ArrayList();
    try
    {
      JSONArray localJSONArray = new JSONArray(a.a("unsupportedAndroidModels"));
      int n = 0;
      while (n < localJSONArray.length())
      {
        ((List)localObject).add(localJSONArray.getString(n));
        n += 1;
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
  
  public static void configure(String paramString, Context paramContext, BISDetectorDelegate paramBISDetectorDelegate)
  {
    new StringBuilder("Configuring BISDetector SDK ").append(SDK_VERSION);
    a = paramString;
    g = paramContext.getApplicationContext();
    f = paramBISDetectorDelegate;
    i.a("BIS_API_KEY", paramString);
    g.getPackageManager().getInstalledApplications(128);
    if (!i.a().getBoolean("KEY_IS_DEVICE_METADATA_COLLECTED", false))
    {
      paramString = new Intent(g, BISDeviceAtlas.class);
      paramString.setFlags(268435456);
      g.startActivity(paramString);
    }
    g.sendBroadcast(new Intent("BootstrapBeaconsInSpace"));
  }
  
  private static void d()
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
      if (c()) {
        startRanging();
      }
      if (i == null)
      {
        localObject = new Thread()
        {
          public final void run()
          {
            try
            {
              for (;;)
              {
                String str = a.a("locationMonitoringInterval");
                if ((str == null) || (str.equals("0"))) {
                  break;
                }
                int i = Integer.parseInt(str);
                h.a("CONTINUOUS_PROCESSING");
                Thread.sleep(60000L);
                e.a(h.b("CONTINUOUS_PROCESSING"));
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
        i = (Thread)localObject;
        ((Thread)localObject).start();
      }
    } while (j != null);
    Object localObject = new Thread()
    {
      public final void run()
      {
        try
        {
          for (;;)
          {
            Thread.sleep(10800000L);
            a.a();
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
    j = (Thread)localObject;
    ((Thread)localObject).start();
  }
  
  private void e()
  {
    this.l.postDelayed(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: getstatic 30	com/beaconsinspace/android/beacon/detector/BISDetector:d	Lcom/beaconsinspace/android/beacon/detector/BISDetector;
        //   3: invokevirtual 34	com/beaconsinspace/android/beacon/detector/BISDetector:getBisProcessManager	()Lcom/beaconsinspace/android/beacon/detector/BISProcessManager;
        //   6: astore 4
        //   8: aload 4
        //   10: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   13: astore_3
        //   14: aload_3
        //   15: monitorenter
        //   16: aload 4
        //   18: invokevirtual 44	com/beaconsinspace/android/beacon/detector/BISProcessManager:getJsonFromProcFile	()Ljava/lang/String;
        //   21: astore 5
        //   23: new 46	com/fasterxml/jackson/databind/ObjectMapper
        //   26: dup
        //   27: invokespecial 47	com/fasterxml/jackson/databind/ObjectMapper:<init>	()V
        //   30: astore 6
        //   32: aload 6
        //   34: aload 5
        //   36: invokevirtual 51	com/fasterxml/jackson/databind/ObjectMapper:readTree	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonNode;
        //   39: astore 5
        //   41: aload 5
        //   43: invokevirtual 57	com/fasterxml/jackson/databind/JsonNode:fields	()Ljava/util/Iterator;
        //   46: astore 5
        //   48: aload 5
        //   50: invokeinterface 63 1 0
        //   55: ifeq +243 -> 298
        //   58: aload 5
        //   60: invokeinterface 67 1 0
        //   65: checkcast 69	java/util/Map$Entry
        //   68: astore 8
        //   70: aload 8
        //   72: invokeinterface 72 1 0
        //   77: checkcast 74	java/lang/String
        //   80: astore 6
        //   82: new 76	com/beaconsinspace/android/beacon/detector/BISProcessManager$a
        //   85: dup
        //   86: invokespecial 77	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:<init>	()V
        //   89: astore 7
        //   91: aload 8
        //   93: invokeinterface 80 1 0
        //   98: checkcast 53	com/fasterxml/jackson/databind/JsonNode
        //   101: astore 8
        //   103: aload 8
        //   105: ldc 82
        //   107: invokevirtual 85	com/fasterxml/jackson/databind/JsonNode:get	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonNode;
        //   110: astore 10
        //   112: aload 10
        //   114: ifnull +56 -> 170
        //   117: aload 10
        //   119: invokevirtual 88	com/fasterxml/jackson/databind/JsonNode:toString	()Ljava/lang/String;
        //   122: astore 9
        //   124: aload 10
        //   126: invokevirtual 91	com/fasterxml/jackson/databind/JsonNode:isArray	()Z
        //   129: istore_2
        //   130: iload_2
        //   131: ifeq +39 -> 170
        //   134: new 46	com/fasterxml/jackson/databind/ObjectMapper
        //   137: dup
        //   138: invokespecial 47	com/fasterxml/jackson/databind/ObjectMapper:<init>	()V
        //   141: astore 10
        //   143: aload 7
        //   145: aload 10
        //   147: aload 9
        //   149: aload 10
        //   151: invokevirtual 95	com/fasterxml/jackson/databind/ObjectMapper:getTypeFactory	()Lcom/fasterxml/jackson/databind/type/TypeFactory;
        //   154: ldc 97
        //   156: ldc 99
        //   158: invokevirtual 105	com/fasterxml/jackson/databind/type/TypeFactory:constructCollectionType	(Ljava/lang/Class;Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/type/CollectionType;
        //   161: invokevirtual 109	com/fasterxml/jackson/databind/ObjectMapper:readValue	(Ljava/lang/String;Lcom/fasterxml/jackson/databind/JavaType;)Ljava/lang/Object;
        //   164: checkcast 97	java/util/ArrayList
        //   167: putfield 112	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:a	Ljava/util/ArrayList;
        //   170: aload 8
        //   172: ldc 114
        //   174: invokevirtual 85	com/fasterxml/jackson/databind/JsonNode:get	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonNode;
        //   177: astore 9
        //   179: aload 9
        //   181: ifnull +56 -> 237
        //   184: aload 9
        //   186: invokevirtual 88	com/fasterxml/jackson/databind/JsonNode:toString	()Ljava/lang/String;
        //   189: astore 8
        //   191: aload 9
        //   193: invokevirtual 91	com/fasterxml/jackson/databind/JsonNode:isArray	()Z
        //   196: istore_2
        //   197: iload_2
        //   198: ifeq +39 -> 237
        //   201: new 46	com/fasterxml/jackson/databind/ObjectMapper
        //   204: dup
        //   205: invokespecial 47	com/fasterxml/jackson/databind/ObjectMapper:<init>	()V
        //   208: astore 9
        //   210: aload 7
        //   212: aload 9
        //   214: aload 8
        //   216: aload 9
        //   218: invokevirtual 95	com/fasterxml/jackson/databind/ObjectMapper:getTypeFactory	()Lcom/fasterxml/jackson/databind/type/TypeFactory;
        //   221: ldc 97
        //   223: ldc 99
        //   225: invokevirtual 105	com/fasterxml/jackson/databind/type/TypeFactory:constructCollectionType	(Ljava/lang/Class;Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/type/CollectionType;
        //   228: invokevirtual 109	com/fasterxml/jackson/databind/ObjectMapper:readValue	(Ljava/lang/String;Lcom/fasterxml/jackson/databind/JavaType;)Ljava/lang/Object;
        //   231: checkcast 97	java/util/ArrayList
        //   234: putfield 116	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:b	Ljava/util/ArrayList;
        //   237: aload 4
        //   239: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   242: aload 6
        //   244: aload 7
        //   246: invokevirtual 122	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   249: pop
        //   250: goto -202 -> 48
        //   253: astore 4
        //   255: aload_3
        //   256: monitorexit
        //   257: aload 4
        //   259: athrow
        //   260: astore_3
        //   261: ldc 124
        //   263: ldc 126
        //   265: aload_3
        //   266: invokestatic 131	com/beaconsinspace/android/beacon/detector/BISLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
        //   269: return
        //   270: astore 5
        //   272: getstatic 137	java/lang/System:out	Ljava/io/PrintStream;
        //   275: new 139	java/lang/StringBuilder
        //   278: dup
        //   279: ldc -115
        //   281: invokespecial 144	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   284: aload 5
        //   286: invokevirtual 147	java/io/IOException:getMessage	()Ljava/lang/String;
        //   289: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   292: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   295: invokevirtual 157	java/io/PrintStream:println	(Ljava/lang/String;)V
        //   298: invokestatic 162	com/beaconsinspace/android/beacon/detector/fgchecker/Utils:postLollipop	()Z
        //   301: ifeq +14 -> 315
        //   304: aload 4
        //   306: getfield 165	com/beaconsinspace/android/beacon/detector/BISProcessManager:a	Landroid/content/Context;
        //   309: invokestatic 169	com/beaconsinspace/android/beacon/detector/fgchecker/Utils:hasUsageStatsPermission	(Landroid/content/Context;)Z
        //   312: ifeq +169 -> 481
        //   315: aload 4
        //   317: getfield 173	com/beaconsinspace/android/beacon/detector/BISProcessManager:c	Lcom/beaconsinspace/android/beacon/detector/fgchecker/AppChecker;
        //   320: aload 4
        //   322: getfield 165	com/beaconsinspace/android/beacon/detector/BISProcessManager:a	Landroid/content/Context;
        //   325: invokevirtual 179	com/beaconsinspace/android/beacon/detector/fgchecker/AppChecker:getForegroundApp	(Landroid/content/Context;)Ljava/lang/String;
        //   328: astore 5
        //   330: aload 4
        //   332: getfield 182	com/beaconsinspace/android/beacon/detector/BISProcessManager:f	Ljava/lang/String;
        //   335: aload 5
        //   337: invokevirtual 186	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
        //   340: ifne +134 -> 474
        //   343: aload 4
        //   345: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   348: aload 4
        //   350: getfield 182	com/beaconsinspace/android/beacon/detector/BISProcessManager:f	Ljava/lang/String;
        //   353: invokevirtual 189	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   356: checkcast 76	com/beaconsinspace/android/beacon/detector/BISProcessManager$a
        //   359: astore 6
        //   361: aload 6
        //   363: getfield 116	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:b	Ljava/util/ArrayList;
        //   366: invokevirtual 193	java/util/ArrayList:size	()I
        //   369: istore_1
        //   370: aload 6
        //   372: getfield 116	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:b	Ljava/util/ArrayList;
        //   375: iload_1
        //   376: iconst_1
        //   377: isub
        //   378: invokevirtual 196	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   381: checkcast 99	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a
        //   384: invokestatic 200	java/lang/System:currentTimeMillis	()J
        //   387: invokestatic 204	com/beaconsinspace/android/beacon/detector/BISProcessManager:millisToBISFormat	(J)Ljava/lang/String;
        //   390: putfield 206	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a:b	Ljava/lang/String;
        //   393: aload 4
        //   395: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   398: aload 4
        //   400: getfield 182	com/beaconsinspace/android/beacon/detector/BISProcessManager:f	Ljava/lang/String;
        //   403: aload 6
        //   405: invokevirtual 122	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   408: pop
        //   409: aload 4
        //   411: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   414: aload 5
        //   416: invokevirtual 210	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
        //   419: ifeq +173 -> 592
        //   422: aload 4
        //   424: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   427: aload 5
        //   429: invokevirtual 189	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   432: checkcast 76	com/beaconsinspace/android/beacon/detector/BISProcessManager$a
        //   435: astore 6
        //   437: aload 6
        //   439: getfield 116	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:b	Ljava/util/ArrayList;
        //   442: new 99	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a
        //   445: dup
        //   446: invokestatic 200	java/lang/System:currentTimeMillis	()J
        //   449: invokestatic 204	com/beaconsinspace/android/beacon/detector/BISProcessManager:millisToBISFormat	(J)Ljava/lang/String;
        //   452: ldc -44
        //   454: invokespecial 215	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   457: invokevirtual 218	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   460: pop
        //   461: aload 4
        //   463: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   466: aload 5
        //   468: aload 6
        //   470: invokevirtual 122	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   473: pop
        //   474: aload 4
        //   476: aload 5
        //   478: putfield 182	com/beaconsinspace/android/beacon/detector/BISProcessManager:f	Ljava/lang/String;
        //   481: aload 4
        //   483: new 220	java/util/HashSet
        //   486: dup
        //   487: aload 4
        //   489: getfield 223	com/beaconsinspace/android/beacon/detector/BISProcessManager:d	Ljava/util/Set;
        //   492: invokespecial 226	java/util/HashSet:<init>	(Ljava/util/Collection;)V
        //   495: putfield 228	com/beaconsinspace/android/beacon/detector/BISProcessManager:e	Ljava/util/Set;
        //   498: invokestatic 234	com/beaconsinspace/android/beacon/detector/processes/AndroidProcesses:getRunningAppProcesses	()Ljava/util/List;
        //   501: checkcast 97	java/util/ArrayList
        //   504: astore 5
        //   506: aload 4
        //   508: getfield 223	com/beaconsinspace/android/beacon/detector/BISProcessManager:d	Ljava/util/Set;
        //   511: invokeinterface 239 1 0
        //   516: aload 5
        //   518: invokevirtual 242	java/util/ArrayList:iterator	()Ljava/util/Iterator;
        //   521: astore 5
        //   523: aload 5
        //   525: invokeinterface 63 1 0
        //   530: ifeq +111 -> 641
        //   533: aload 5
        //   535: invokeinterface 67 1 0
        //   540: checkcast 244	com/beaconsinspace/android/beacon/detector/processes/models/AndroidAppProcess
        //   543: astore 6
        //   545: aload 4
        //   547: getfield 223	com/beaconsinspace/android/beacon/detector/BISProcessManager:d	Ljava/util/Set;
        //   550: aload 6
        //   552: invokevirtual 247	com/beaconsinspace/android/beacon/detector/processes/models/AndroidAppProcess:getPackageName	()Ljava/lang/String;
        //   555: invokeinterface 248 2 0
        //   560: pop
        //   561: goto -38 -> 523
        //   564: astore 9
        //   566: ldc -6
        //   568: ldc -4
        //   570: aload 9
        //   572: invokestatic 131	com/beaconsinspace/android/beacon/detector/BISLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
        //   575: goto -405 -> 170
        //   578: astore 8
        //   580: ldc -6
        //   582: ldc -2
        //   584: aload 8
        //   586: invokestatic 131	com/beaconsinspace/android/beacon/detector/BISLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
        //   589: goto -352 -> 237
        //   592: new 76	com/beaconsinspace/android/beacon/detector/BISProcessManager$a
        //   595: dup
        //   596: invokespecial 77	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:<init>	()V
        //   599: astore 6
        //   601: aload 6
        //   603: getfield 116	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:b	Ljava/util/ArrayList;
        //   606: new 99	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a
        //   609: dup
        //   610: invokestatic 200	java/lang/System:currentTimeMillis	()J
        //   613: invokestatic 204	com/beaconsinspace/android/beacon/detector/BISProcessManager:millisToBISFormat	(J)Ljava/lang/String;
        //   616: ldc -44
        //   618: invokespecial 215	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   621: invokevirtual 218	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   624: pop
        //   625: aload 4
        //   627: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   630: aload 5
        //   632: aload 6
        //   634: invokevirtual 122	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   637: pop
        //   638: goto -164 -> 474
        //   641: new 220	java/util/HashSet
        //   644: dup
        //   645: invokespecial 255	java/util/HashSet:<init>	()V
        //   648: astore 5
        //   650: aload 4
        //   652: getfield 228	com/beaconsinspace/android/beacon/detector/BISProcessManager:e	Ljava/util/Set;
        //   655: invokeinterface 256 1 0
        //   660: astore 6
        //   662: aload 6
        //   664: invokeinterface 63 1 0
        //   669: ifeq +43 -> 712
        //   672: aload 6
        //   674: invokeinterface 67 1 0
        //   679: checkcast 74	java/lang/String
        //   682: astore 7
        //   684: aload 4
        //   686: getfield 223	com/beaconsinspace/android/beacon/detector/BISProcessManager:d	Ljava/util/Set;
        //   689: aload 7
        //   691: invokeinterface 259 2 0
        //   696: ifne -34 -> 662
        //   699: aload 5
        //   701: aload 7
        //   703: invokeinterface 248 2 0
        //   708: pop
        //   709: goto -47 -> 662
        //   712: new 220	java/util/HashSet
        //   715: dup
        //   716: invokespecial 255	java/util/HashSet:<init>	()V
        //   719: astore 6
        //   721: aload 4
        //   723: getfield 223	com/beaconsinspace/android/beacon/detector/BISProcessManager:d	Ljava/util/Set;
        //   726: invokeinterface 256 1 0
        //   731: astore 7
        //   733: aload 7
        //   735: invokeinterface 63 1 0
        //   740: ifeq +43 -> 783
        //   743: aload 7
        //   745: invokeinterface 67 1 0
        //   750: checkcast 74	java/lang/String
        //   753: astore 8
        //   755: aload 4
        //   757: getfield 228	com/beaconsinspace/android/beacon/detector/BISProcessManager:e	Ljava/util/Set;
        //   760: aload 8
        //   762: invokeinterface 259 2 0
        //   767: ifne -34 -> 733
        //   770: aload 6
        //   772: aload 8
        //   774: invokeinterface 248 2 0
        //   779: pop
        //   780: goto -47 -> 733
        //   783: aload 6
        //   785: invokeinterface 256 1 0
        //   790: astore 6
        //   792: aload 6
        //   794: invokeinterface 63 1 0
        //   799: ifeq +160 -> 959
        //   802: aload 6
        //   804: invokeinterface 67 1 0
        //   809: checkcast 74	java/lang/String
        //   812: astore 7
        //   814: aload 4
        //   816: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   819: aload 7
        //   821: invokevirtual 210	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
        //   824: ifeq +73 -> 897
        //   827: aload 4
        //   829: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   832: aload 7
        //   834: invokevirtual 189	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   837: checkcast 76	com/beaconsinspace/android/beacon/detector/BISProcessManager$a
        //   840: astore 8
        //   842: aload 8
        //   844: getfield 112	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:a	Ljava/util/ArrayList;
        //   847: new 99	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a
        //   850: dup
        //   851: invokestatic 200	java/lang/System:currentTimeMillis	()J
        //   854: invokestatic 204	com/beaconsinspace/android/beacon/detector/BISProcessManager:millisToBISFormat	(J)Ljava/lang/String;
        //   857: ldc -44
        //   859: invokespecial 215	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   862: invokevirtual 218	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   865: pop
        //   866: aload 4
        //   868: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   871: aload 7
        //   873: aload 8
        //   875: invokevirtual 122	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   878: pop
        //   879: goto -87 -> 792
        //   882: astore 7
        //   884: ldc -6
        //   886: ldc_w 261
        //   889: aload 7
        //   891: invokestatic 131	com/beaconsinspace/android/beacon/detector/BISLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
        //   894: goto -102 -> 792
        //   897: new 76	com/beaconsinspace/android/beacon/detector/BISProcessManager$a
        //   900: dup
        //   901: invokespecial 77	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:<init>	()V
        //   904: astore 8
        //   906: aload 8
        //   908: getfield 112	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:a	Ljava/util/ArrayList;
        //   911: new 99	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a
        //   914: dup
        //   915: invokestatic 200	java/lang/System:currentTimeMillis	()J
        //   918: invokestatic 204	com/beaconsinspace/android/beacon/detector/BISProcessManager:millisToBISFormat	(J)Ljava/lang/String;
        //   921: ldc -44
        //   923: invokespecial 215	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   926: invokevirtual 218	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   929: pop
        //   930: aload 4
        //   932: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   935: aload 7
        //   937: aload 8
        //   939: invokevirtual 122	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   942: pop
        //   943: goto -151 -> 792
        //   946: astore 7
        //   948: ldc -6
        //   950: ldc_w 263
        //   953: invokestatic 265	com/beaconsinspace/android/beacon/detector/BISLog:e	(Ljava/lang/String;Ljava/lang/String;)V
        //   956: goto -164 -> 792
        //   959: aload 5
        //   961: invokeinterface 256 1 0
        //   966: astore 5
        //   968: aload 5
        //   970: invokeinterface 63 1 0
        //   975: ifeq +166 -> 1141
        //   978: aload 5
        //   980: invokeinterface 67 1 0
        //   985: checkcast 74	java/lang/String
        //   988: astore 6
        //   990: aload 4
        //   992: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   995: aload 6
        //   997: invokevirtual 210	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
        //   1000: ifeq +79 -> 1079
        //   1003: aload 4
        //   1005: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   1008: aload 6
        //   1010: invokevirtual 189	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   1013: checkcast 76	com/beaconsinspace/android/beacon/detector/BISProcessManager$a
        //   1016: getfield 112	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:a	Ljava/util/ArrayList;
        //   1019: invokevirtual 193	java/util/ArrayList:size	()I
        //   1022: istore_1
        //   1023: iload_1
        //   1024: ifle -56 -> 968
        //   1027: aload 4
        //   1029: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   1032: aload 6
        //   1034: invokevirtual 189	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
        //   1037: checkcast 76	com/beaconsinspace/android/beacon/detector/BISProcessManager$a
        //   1040: getfield 112	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:a	Ljava/util/ArrayList;
        //   1043: iload_1
        //   1044: iconst_1
        //   1045: isub
        //   1046: invokevirtual 196	java/util/ArrayList:get	(I)Ljava/lang/Object;
        //   1049: checkcast 99	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a
        //   1052: invokestatic 200	java/lang/System:currentTimeMillis	()J
        //   1055: invokestatic 204	com/beaconsinspace/android/beacon/detector/BISProcessManager:millisToBISFormat	(J)Ljava/lang/String;
        //   1058: putfield 206	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a:b	Ljava/lang/String;
        //   1061: goto -93 -> 968
        //   1064: astore 6
        //   1066: ldc -6
        //   1068: ldc_w 261
        //   1071: aload 6
        //   1073: invokestatic 131	com/beaconsinspace/android/beacon/detector/BISLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
        //   1076: goto -108 -> 968
        //   1079: new 76	com/beaconsinspace/android/beacon/detector/BISProcessManager$a
        //   1082: dup
        //   1083: invokespecial 77	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:<init>	()V
        //   1086: astore 7
        //   1088: aload 7
        //   1090: getfield 112	com/beaconsinspace/android/beacon/detector/BISProcessManager$a:a	Ljava/util/ArrayList;
        //   1093: new 99	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a
        //   1096: dup
        //   1097: ldc -44
        //   1099: invokestatic 200	java/lang/System:currentTimeMillis	()J
        //   1102: invokestatic 204	com/beaconsinspace/android/beacon/detector/BISProcessManager:millisToBISFormat	(J)Ljava/lang/String;
        //   1105: invokespecial 215	com/beaconsinspace/android/beacon/detector/BISProcessManager$a$a:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   1108: invokevirtual 218	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   1111: pop
        //   1112: aload 4
        //   1114: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   1117: aload 6
        //   1119: aload 7
        //   1121: invokevirtual 122	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   1124: pop
        //   1125: goto -157 -> 968
        //   1128: astore 6
        //   1130: ldc -6
        //   1132: ldc_w 263
        //   1135: invokestatic 265	com/beaconsinspace/android/beacon/detector/BISLog:e	(Ljava/lang/String;Ljava/lang/String;)V
        //   1138: goto -170 -> 968
        //   1141: aload 4
        //   1143: aload 4
        //   1145: getfield 40	com/beaconsinspace/android/beacon/detector/BISProcessManager:b	Ljava/util/HashMap;
        //   1148: invokestatic 268	com/beaconsinspace/android/beacon/detector/BISProcessManager:a	(Ljava/lang/Object;)Ljava/lang/String;
        //   1151: invokevirtual 270	com/beaconsinspace/android/beacon/detector/BISProcessManager:a	(Ljava/lang/String;)V
        //   1154: aload_3
        //   1155: monitorexit
        //   1156: aload_0
        //   1157: getfield 17	com/beaconsinspace/android/beacon/detector/BISDetector$5:a	Lcom/beaconsinspace/android/beacon/detector/BISDetector;
        //   1160: invokestatic 272	com/beaconsinspace/android/beacon/detector/BISDetector:a	(Lcom/beaconsinspace/android/beacon/detector/BISDetector;)V
        //   1163: return
        //   1164: astore_3
        //   1165: ldc 124
        //   1167: ldc_w 274
        //   1170: invokestatic 265	com/beaconsinspace/android/beacon/detector/BISLog:e	(Ljava/lang/String;Ljava/lang/String;)V
        //   1173: return
        //   1174: astore 6
        //   1176: goto -767 -> 409
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	1179	0	this	5
        //   369	677	1	i	int
        //   129	69	2	bool	boolean
        //   13	243	3	localHashMap	java.util.HashMap
        //   260	895	3	localException1	Exception
        //   1164	1	3	localThrowable1	Throwable
        //   6	232	4	localBISProcessManager	BISProcessManager
        //   253	891	4	localObject1	Object
        //   21	38	5	localObject2	Object
        //   270	15	5	localIOException1	java.io.IOException
        //   328	651	5	localObject3	Object
        //   30	1003	6	localObject4	Object
        //   1064	54	6	localException2	Exception
        //   1128	1	6	localThrowable2	Throwable
        //   1174	1	6	localException3	Exception
        //   89	783	7	localObject5	Object
        //   882	54	7	localException4	Exception
        //   946	1	7	localThrowable3	Throwable
        //   1086	34	7	localA	BISProcessManager.a
        //   68	147	8	localObject6	Object
        //   578	7	8	localIOException2	java.io.IOException
        //   753	185	8	localObject7	Object
        //   122	95	9	localObject8	Object
        //   564	7	9	localIOException3	java.io.IOException
        //   110	40	10	localObject9	Object
        // Exception table:
        //   from	to	target	type
        //   16	32	253	finally
        //   32	41	253	finally
        //   41	48	253	finally
        //   48	112	253	finally
        //   117	130	253	finally
        //   134	170	253	finally
        //   170	179	253	finally
        //   184	197	253	finally
        //   201	237	253	finally
        //   237	250	253	finally
        //   255	257	253	finally
        //   272	298	253	finally
        //   298	315	253	finally
        //   315	361	253	finally
        //   361	409	253	finally
        //   409	474	253	finally
        //   474	481	253	finally
        //   481	523	253	finally
        //   523	561	253	finally
        //   566	575	253	finally
        //   580	589	253	finally
        //   592	638	253	finally
        //   641	662	253	finally
        //   662	709	253	finally
        //   712	733	253	finally
        //   733	780	253	finally
        //   783	792	253	finally
        //   792	814	253	finally
        //   814	879	253	finally
        //   884	894	253	finally
        //   897	943	253	finally
        //   948	956	253	finally
        //   959	968	253	finally
        //   968	990	253	finally
        //   990	1023	253	finally
        //   1027	1061	253	finally
        //   1066	1076	253	finally
        //   1079	1125	253	finally
        //   1130	1138	253	finally
        //   1141	1156	253	finally
        //   0	16	260	java/lang/Exception
        //   257	260	260	java/lang/Exception
        //   1156	1163	260	java/lang/Exception
        //   32	41	270	java/io/IOException
        //   134	170	564	java/io/IOException
        //   201	237	578	java/io/IOException
        //   814	879	882	java/lang/Exception
        //   897	943	882	java/lang/Exception
        //   814	879	946	java/lang/Throwable
        //   897	943	946	java/lang/Throwable
        //   990	1023	1064	java/lang/Exception
        //   1027	1061	1064	java/lang/Exception
        //   1079	1125	1064	java/lang/Exception
        //   990	1023	1128	java/lang/Throwable
        //   1027	1061	1128	java/lang/Throwable
        //   1079	1125	1128	java/lang/Throwable
        //   0	16	1164	java/lang/Throwable
        //   257	260	1164	java/lang/Throwable
        //   1156	1163	1164	java/lang/Throwable
        //   361	409	1174	java/lang/Exception
      }
    }, 60000L);
  }
  
  public static Context getContext()
  {
    return g;
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
    do
    {
      for (;;)
      {
        return false;
        if (Build.VERSION.SDK_INT >= 19) {
          try
          {
            int n = Settings.Secure.getInt(g.getContentResolver(), "location_mode");
            if (n != 0) {
              return true;
            }
          }
          catch (Settings.SettingNotFoundException localSettingNotFoundException)
          {
            Log.e("BIS_API", "Failed to check for location services: " + localSettingNotFoundException.getMessage());
            return false;
          }
        }
      }
      LocationManager localLocationManager = (LocationManager)g.getSystemService("location");
      int i1 = 0;
      int i2 = 0;
      for (;;)
      {
        try
        {
          bool = localLocationManager.isProviderEnabled("gps");
          i1 = bool;
        }
        catch (Exception localException2)
        {
          boolean bool;
          continue;
        }
        try
        {
          bool = localLocationManager.isProviderEnabled("network");
          i2 = bool;
        }
        catch (Exception localException1) {}
      }
    } while ((i1 == 0) && (i2 == 0));
    return true;
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
    d localD = e;
    try
    {
      localD.b();
      localD.c();
      return;
    }
    catch (Exception localException)
    {
      Log.e("BIS_BEACONS_MANAGER", "Exception: " + localException.toString());
    }
  }
  
  public static void stopRanging()
  {
    e.b();
  }
  
  public BISProcessManager getBisProcessManager()
  {
    return this.m;
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
    a = i.a("BIS_API_KEY");
    if (this.h == null)
    {
      this.h = new Thread()
      {
        public final void run()
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
      this.h.start();
    }
    return 1;
  }
}
