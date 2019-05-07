package com.embee.core.model;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class EMTDeviceInfoContainer
  extends EMTDeviceInformation
{
  final String TAG = "EMTDeviceInfoContainer";
  public String mBSSID;
  public String mIsWifiAvailable;
  List<EMTInstalledAppInfo> mListInstalledApps;
  public String mSSID;
  public boolean mScreenOn;
  public String mTimeZone;
  
  public EMTDeviceInfoContainer()
  {
    if (this.mListInstalledApps == null) {
      this.mListInstalledApps = new ArrayList();
    }
  }
  
  public static EMTDeviceInfoContainer getDeviceInfo(Context paramContext)
  {
    EMTDeviceInfoContainer localEMTDeviceInfoContainer = new EMTDeviceInfoContainer();
    localEMTDeviceInfoContainer.populateInstalledApps(paramContext);
    return localEMTDeviceInfoContainer;
  }
  
  public void clear()
  {
    this.mListInstalledApps.clear();
  }
  
  /* Error */
  public void populateInstalledApps(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 25	com/embee/core/model/EMTDeviceInfoContainer:mListInstalledApps	Ljava/util/List;
    //   4: invokeinterface 41 1 0
    //   9: aload_1
    //   10: invokevirtual 49	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore 8
    //   15: aload 8
    //   17: sipush 128
    //   20: invokevirtual 55	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   23: astore_1
    //   24: iconst_0
    //   25: istore_3
    //   26: iconst_0
    //   27: istore_2
    //   28: iconst_0
    //   29: istore 5
    //   31: iconst_0
    //   32: istore 4
    //   34: aload_1
    //   35: invokeinterface 59 1 0
    //   40: astore 9
    //   42: aconst_null
    //   43: astore_1
    //   44: iload 4
    //   46: istore_3
    //   47: iload_2
    //   48: istore 5
    //   50: iload_3
    //   51: istore 6
    //   53: aload 9
    //   55: invokeinterface 65 1 0
    //   60: ifeq +341 -> 401
    //   63: iload_2
    //   64: istore 5
    //   66: iload_3
    //   67: istore 6
    //   69: aload 9
    //   71: invokeinterface 69 1 0
    //   76: checkcast 71	android/content/pm/ApplicationInfo
    //   79: astore 10
    //   81: iload_3
    //   82: iconst_1
    //   83: iadd
    //   84: istore 4
    //   86: aload 10
    //   88: ifnonnull +179 -> 267
    //   91: iload_2
    //   92: iconst_1
    //   93: iadd
    //   94: istore 7
    //   96: iload 7
    //   98: istore_2
    //   99: iload 4
    //   101: istore_3
    //   102: iload 7
    //   104: istore 5
    //   106: iload 4
    //   108: istore 6
    //   110: getstatic 76	com/embee/constants/EMCoreConstant:DEBUG	Z
    //   113: ifeq -66 -> 47
    //   116: iload 7
    //   118: istore 5
    //   120: iload 4
    //   122: istore 6
    //   124: ldc 21
    //   126: new 78	java/lang/StringBuilder
    //   129: dup
    //   130: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   133: ldc 81
    //   135: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: iload 4
    //   140: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   143: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: invokestatic 98	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   149: pop
    //   150: iload 7
    //   152: istore_2
    //   153: iload 4
    //   155: istore_3
    //   156: goto -109 -> 47
    //   159: astore_1
    //   160: iload 5
    //   162: istore_3
    //   163: iload_3
    //   164: istore_2
    //   165: iload 6
    //   167: istore 4
    //   169: getstatic 76	com/embee/constants/EMCoreConstant:DEBUG	Z
    //   172: ifeq +37 -> 209
    //   175: ldc 100
    //   177: new 78	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   184: ldc 102
    //   186: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aload_1
    //   190: invokevirtual 105	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   193: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokestatic 108	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   202: pop
    //   203: iload 6
    //   205: istore 4
    //   207: iload_3
    //   208: istore_2
    //   209: getstatic 76	com/embee/constants/EMCoreConstant:DEBUG	Z
    //   212: ifeq +54 -> 266
    //   215: ldc 21
    //   217: new 78	java/lang/StringBuilder
    //   220: dup
    //   221: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   224: ldc 110
    //   226: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: iload_2
    //   230: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   233: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   236: invokestatic 98	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   239: pop
    //   240: ldc 21
    //   242: new 78	java/lang/StringBuilder
    //   245: dup
    //   246: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   249: ldc 112
    //   251: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: iload 4
    //   256: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   259: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   262: invokestatic 98	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   265: pop
    //   266: return
    //   267: iload_2
    //   268: istore 5
    //   270: iload 4
    //   272: istore 6
    //   274: new 114	com/embee/core/model/EMTInstalledAppInfo
    //   277: dup
    //   278: invokespecial 115	com/embee/core/model/EMTInstalledAppInfo:<init>	()V
    //   281: astore_1
    //   282: iload_2
    //   283: istore_3
    //   284: iload 4
    //   286: istore 5
    //   288: aload_1
    //   289: aload 10
    //   291: getfield 118	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   294: putfield 119	com/embee/core/model/EMTInstalledAppInfo:packageName	Ljava/lang/String;
    //   297: iload_2
    //   298: istore_3
    //   299: iload 4
    //   301: istore 5
    //   303: aload_1
    //   304: aload 8
    //   306: aload_1
    //   307: getfield 119	com/embee/core/model/EMTInstalledAppInfo:packageName	Ljava/lang/String;
    //   310: invokestatic 125	com/embee/core/util/EMMasterUtil:getAppName	(Landroid/content/pm/PackageManager;Ljava/lang/String;)Ljava/lang/String;
    //   313: putfield 128	com/embee/core/model/EMTInstalledAppInfo:appName	Ljava/lang/String;
    //   316: iload_2
    //   317: istore_3
    //   318: iload 4
    //   320: istore 5
    //   322: aload_1
    //   323: aload 10
    //   325: getfield 132	android/content/pm/ApplicationInfo:uid	I
    //   328: putfield 133	com/embee/core/model/EMTInstalledAppInfo:uid	I
    //   331: iload_2
    //   332: istore_3
    //   333: iload 4
    //   335: istore 5
    //   337: aload_1
    //   338: aload_1
    //   339: getfield 133	com/embee/core/model/EMTInstalledAppInfo:uid	I
    //   342: invokestatic 139	android/net/TrafficStats:getUidTxBytes	(I)J
    //   345: putfield 143	com/embee/core/model/EMTInstalledAppInfo:startTxBytes	J
    //   348: iload_2
    //   349: istore_3
    //   350: iload 4
    //   352: istore 5
    //   354: aload_1
    //   355: aload_1
    //   356: getfield 133	com/embee/core/model/EMTInstalledAppInfo:uid	I
    //   359: invokestatic 146	android/net/TrafficStats:getUidRxBytes	(I)J
    //   362: putfield 149	com/embee/core/model/EMTInstalledAppInfo:startRxBytes	J
    //   365: iload_2
    //   366: istore_3
    //   367: iload 4
    //   369: istore 5
    //   371: aload_1
    //   372: invokestatic 155	java/lang/System:currentTimeMillis	()J
    //   375: putfield 158	com/embee/core/model/EMTInstalledAppInfo:sampleTime	J
    //   378: iload_2
    //   379: istore_3
    //   380: iload 4
    //   382: istore 5
    //   384: aload_0
    //   385: getfield 25	com/embee/core/model/EMTDeviceInfoContainer:mListInstalledApps	Ljava/util/List;
    //   388: aload_1
    //   389: invokeinterface 162 2 0
    //   394: pop
    //   395: iload 4
    //   397: istore_3
    //   398: goto -351 -> 47
    //   401: iload_3
    //   402: istore 4
    //   404: goto -195 -> 209
    //   407: astore_1
    //   408: aload_1
    //   409: athrow
    //   410: astore_1
    //   411: goto -3 -> 408
    //   414: astore_1
    //   415: iload 5
    //   417: istore 6
    //   419: goto -256 -> 163
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	422	0	this	EMTDeviceInfoContainer
    //   0	422	1	paramContext	Context
    //   27	352	2	i	int
    //   25	377	3	j	int
    //   32	371	4	k	int
    //   29	387	5	m	int
    //   51	367	6	n	int
    //   94	57	7	i1	int
    //   13	292	8	localPackageManager	android.content.pm.PackageManager
    //   40	30	9	localIterator	java.util.Iterator
    //   79	245	10	localApplicationInfo	android.content.pm.ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   53	63	159	java/lang/Exception
    //   69	81	159	java/lang/Exception
    //   110	116	159	java/lang/Exception
    //   124	150	159	java/lang/Exception
    //   274	282	159	java/lang/Exception
    //   34	42	407	finally
    //   169	203	407	finally
    //   288	297	407	finally
    //   303	316	407	finally
    //   322	331	407	finally
    //   337	348	407	finally
    //   354	365	407	finally
    //   371	378	407	finally
    //   384	395	407	finally
    //   53	63	410	finally
    //   69	81	410	finally
    //   110	116	410	finally
    //   124	150	410	finally
    //   274	282	410	finally
    //   34	42	414	java/lang/Exception
    //   288	297	414	java/lang/Exception
    //   303	316	414	java/lang/Exception
    //   322	331	414	java/lang/Exception
    //   337	348	414	java/lang/Exception
    //   354	365	414	java/lang/Exception
    //   371	378	414	java/lang/Exception
    //   384	395	414	java/lang/Exception
  }
}
