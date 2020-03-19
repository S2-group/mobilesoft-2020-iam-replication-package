package com.avg.tuneup.traffic;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.am;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import com.avg.c.d;
import com.avg.c.g;
import com.avg.toolkit.g.a;
import com.avg.toolkit.h;
import com.avg.toolkit.recurringTasks.AlarmReceiver;
import com.avg.toolkit.zen.tasks.ZENCommManager;
import com.avg.tuneup.i;
import com.avg.tuneup.traffic.widget.TrafficWidgetPlugin;
import com.avg.ui.general.b;
import com.avg.ui.general.r;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class s
{
  public static final int[] a = { 30000, 60000, 300000, 900000, 1800000 };
  private static final String[] b = { "rmnet0", "pdp0", "ppp0", "rmnet_sdio0", "rmnet_sdio1", "rmnet_sdio2", "ppp1", "pdp_ip0", "rmnet1", "rmnet2", "rmnet3", "cdma_rmnet4" };
  private static volatile s c = null;
  private static long[] e = { 0L, 0L };
  private x d;
  private Context f;
  private Boolean g = Boolean.valueOf(false);
  private boolean h = false;
  private ApplicationInfo i;
  private w j;
  private Runnable k = new t(this);
  
  private s(Context paramContext)
  {
    this.d = new x(paramContext, "tdb", null, 1);
    this.f = paramContext;
    this.g = Boolean.valueOf(false);
    this.h = p();
    this.i = new ApplicationInfo();
    this.i.uid = 1013;
    this.i.packageName = "android.process.media";
    this.i.name = paramContext.getString(g.traffic_media_streaming);
    this.j = new w(paramContext, null);
    a();
  }
  
  private long a(String paramString)
  {
    long l2 = -1L;
    String str2 = null;
    String str1 = null;
    do
    {
      try
      {
        paramString = b(paramString);
        if (paramString == null) {
          continue;
        }
        str1 = paramString;
        str2 = paramString;
        l1 = Long.valueOf(paramString.readLine()).longValue();
        l2 = l1;
      }
      catch (Exception paramString)
      {
        long l1;
        while (str1 == null) {}
        try
        {
          str1.close();
          return -1L;
        }
        catch (IOException paramString)
        {
          a.a(paramString);
          return -1L;
        }
      }
      finally
      {
        if (str2 == null) {
          break label118;
        }
      }
      try
      {
        paramString.close();
        l2 = l1;
        return l2;
      }
      catch (IOException paramString)
      {
        a.a(paramString);
        return l1;
      }
    } while (paramString == null);
    try
    {
      paramString.close();
      return -1L;
    }
    catch (IOException paramString)
    {
      a.a(paramString);
      return -1L;
    }
    try
    {
      str2.close();
      label118:
      throw paramString;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        a.a(localIOException);
      }
    }
  }
  
  private long a(String paramString, int paramInt, boolean paramBoolean)
  {
    if ((!paramBoolean) && (Build.VERSION.SDK_INT >= 8)) {
      return TrafficStats.getUidRxBytes(paramInt);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString).append(paramInt).append("/tcp_rcv");
    return a(localStringBuilder.toString());
  }
  
  private Notification a(String paramString1, PendingIntent paramPendingIntent, String paramString2, int paramInt)
  {
    am localAm = new am(this.f);
    localAm.a(paramString2).b(paramString1).a(paramPendingIntent).a(d.notification_traffic).a(0L);
    paramString1 = localAm.a();
    paramString1.flags = paramInt;
    return paramString1;
  }
  
  public static s a(Context paramContext)
  {
    Object localObject = c;
    if (localObject == null) {
      try
      {
        s localS = c;
        localObject = localS;
        if (localS == null)
        {
          localObject = new s(paramContext.getApplicationContext());
          c = (s)localObject;
        }
        return localObject;
      }
      finally {}
    }
    return localObject;
  }
  
  private void a(double[] paramArrayOfDouble, boolean paramBoolean)
  {
    Object localObject1 = new DecimalFormat("#.#");
    if (paramArrayOfDouble[0] >= 100.0D) {
      ((DecimalFormat)localObject1).applyPattern("#");
    }
    localObject1 = this.f.getString(g.traffic_network_usage).replace("[number]", ((DecimalFormat)localObject1).format(paramArrayOfDouble[0]));
    String str1 = (String)localObject1 + " " + this.f.getString(g.warning);
    String str2 = this.f.getString(g.traffic_network_tap_for_details);
    Object localObject2 = new Bundle();
    if (r.c())
    {
      localObject1 = "com.antivirus.ui.tablet.DualPaneActivity";
      ((Bundle)localObject2).putInt("EXTRA_GOTO", 3);
    }
    try
    {
      for (;;)
      {
        localObject1 = Class.forName((String)localObject1);
        localObject1 = new Intent(this.f, (Class)localObject1);
        ((Intent)localObject1).setAction("antivirus.notification.DATA_USAGE");
        ((Intent)localObject1).putExtras((Bundle)localObject2);
        ((Intent)localObject1).setFlags(603979776);
        ((Intent)localObject1).putExtra("NOTIFICATION_EXTRA_TOP", 13);
        ((Intent)localObject1).putExtra("NOTIFICATION_EXTRA_AFTER", 14);
        if (paramBoolean) {
          break;
        }
        ((Intent)localObject1).putExtra("EXTRA_NOTIFICATION_FROM", "Data_plan");
        localObject1 = PendingIntent.getActivity(this.f, 123214, (Intent)localObject1, 268435456);
        localObject2 = new Notification(d.notification_traffic_warning, str1, 0L);
        ((Notification)localObject2).defaults |= 0x4;
        if (!paramBoolean) {
          break label358;
        }
        ((Notification)localObject2).flags = 10;
        ((Notification)localObject2).setLatestEventInfo(this.f, str1, str2, (PendingIntent)localObject1);
        ((NotificationManager)this.f.getSystemService("notification")).notify(12, (Notification)localObject2);
        if (!paramBoolean) {
          b.a(this.f, "Data_plan");
        }
        if ((paramArrayOfDouble[0] < i.k()) || (paramArrayOfDouble[0] >= 100.0D)) {
          break label368;
        }
        i.g(1);
        return;
        localObject1 = "com.antivirus.ui.performance.traffic.AvTrafficMeterActivity";
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      label358:
      label368:
      do
      {
        for (;;)
        {
          TrafficMeterActivity localTrafficMeterActivity = TrafficMeterActivity.class;
          continue;
          localTrafficMeterActivity.putExtra("EXTRA_NOTIFICATION_FROM", "Data_plan_on_going");
          continue;
          ((Notification)localObject2).flags = 16;
        }
      } while (paramArrayOfDouble[0] < 100.0D);
      i.g(2);
    }
  }
  
  private boolean a(double paramDouble, boolean paramBoolean)
  {
    int m = i.k();
    int n = i.m();
    return ((paramDouble >= m) && (paramDouble < 100.0D) && (n == 0)) || ((paramDouble >= 100.0D) && (n != 2)) || ((paramDouble >= m) && (paramBoolean));
  }
  
  private long b(String paramString, int paramInt, boolean paramBoolean)
  {
    long l;
    if ((!paramBoolean) && (Build.VERSION.SDK_INT >= 8)) {
      l = TrafficStats.getUidTxBytes(paramInt);
    }
    StringBuilder localStringBuilder;
    do
    {
      return l;
      l = 0L;
      localStringBuilder = new StringBuilder();
    } while (paramString == null);
    localStringBuilder.append(paramString).append(paramInt).append("/tcp_snd");
    return a(localStringBuilder.toString());
  }
  
  private RandomAccessFile b(String paramString)
  {
    paramString = new File(paramString);
    if ((!paramString.exists()) || (!paramString.canRead())) {
      return null;
    }
    try
    {
      paramString = new RandomAccessFile(paramString, "r");
      return paramString;
    }
    catch (IOException paramString)
    {
      a.a(paramString);
    }
    return null;
  }
  
  /* Error */
  private void b(boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: invokevirtual 419	com/avg/tuneup/traffic/s:e	()Ljava/util/List;
    //   6: astore 7
    //   8: aconst_null
    //   9: astore 5
    //   11: aload_0
    //   12: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   15: invokevirtual 423	com/avg/tuneup/traffic/x:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   18: astore 6
    //   20: aload 6
    //   22: astore 5
    //   24: aload 5
    //   26: ifnull +8 -> 34
    //   29: aload 7
    //   31: ifnonnull +36 -> 67
    //   34: aload 5
    //   36: ifnonnull +9 -> 45
    //   39: ldc_w 425
    //   42: invokestatic 427	com/avg/toolkit/g/a:b	(Ljava/lang/String;)V
    //   45: aload 7
    //   47: ifnonnull +9 -> 56
    //   50: ldc_w 429
    //   53: invokestatic 427	com/avg/toolkit/g/a:b	(Ljava/lang/String;)V
    //   56: aload 5
    //   58: ifnull +8 -> 66
    //   61: aload 5
    //   63: invokevirtual 434	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   66: return
    //   67: aload 5
    //   69: invokevirtual 437	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   72: aload 7
    //   74: invokeinterface 442 1 0
    //   79: iconst_1
    //   80: isub
    //   81: istore_2
    //   82: iload_2
    //   83: iflt +77 -> 160
    //   86: aload_0
    //   87: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   90: aload 7
    //   92: iload_2
    //   93: invokeinterface 446 2 0
    //   98: checkcast 105	android/content/pm/ApplicationInfo
    //   101: getfield 118	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   104: aload_0
    //   105: ldc_w 448
    //   108: aload 7
    //   110: iload_2
    //   111: invokeinterface 446 2 0
    //   116: checkcast 105	android/content/pm/ApplicationInfo
    //   119: getfield 112	android/content/pm/ApplicationInfo:uid	I
    //   122: invokevirtual 451	com/avg/tuneup/traffic/s:a	(Ljava/lang/String;I)J
    //   125: aload_0
    //   126: ldc_w 448
    //   129: aload 7
    //   131: iload_2
    //   132: invokeinterface 446 2 0
    //   137: checkcast 105	android/content/pm/ApplicationInfo
    //   140: getfield 112	android/content/pm/ApplicationInfo:uid	I
    //   143: invokevirtual 453	com/avg/tuneup/traffic/s:b	(Ljava/lang/String;I)J
    //   146: aload_0
    //   147: getfield 81	com/avg/tuneup/traffic/s:h	Z
    //   150: invokevirtual 456	com/avg/tuneup/traffic/x:a	(Ljava/lang/String;JJZ)V
    //   153: iload_2
    //   154: iconst_1
    //   155: isub
    //   156: istore_2
    //   157: goto -75 -> 82
    //   160: iload_1
    //   161: ifeq +30 -> 191
    //   164: getstatic 183	android/os/Build$VERSION:SDK_INT	I
    //   167: bipush 8
    //   169: if_icmplt +41 -> 210
    //   172: aload_0
    //   173: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   176: getstatic 62	com/avg/tuneup/traffic/s:b	[Ljava/lang/String;
    //   179: iconst_0
    //   180: aaload
    //   181: invokestatic 459	android/net/TrafficStats:getMobileRxBytes	()J
    //   184: invokestatic 462	android/net/TrafficStats:getMobileTxBytes	()J
    //   187: iconst_1
    //   188: invokevirtual 456	com/avg/tuneup/traffic/x:a	(Ljava/lang/String;JJZ)V
    //   191: aload 5
    //   193: invokevirtual 465	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   196: aload 5
    //   198: ifnull -132 -> 66
    //   201: aload 5
    //   203: invokevirtual 434	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   206: return
    //   207: astore 5
    //   209: return
    //   210: getstatic 62	com/avg/tuneup/traffic/s:b	[Ljava/lang/String;
    //   213: astore 6
    //   215: aload 6
    //   217: arraylength
    //   218: istore 4
    //   220: iload_3
    //   221: istore_2
    //   222: iload_2
    //   223: iload 4
    //   225: if_icmpge -34 -> 191
    //   228: aload 6
    //   230: iload_2
    //   231: aaload
    //   232: astore 7
    //   234: aload_0
    //   235: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   238: aload 7
    //   240: aload_0
    //   241: ldc_w 467
    //   244: aload 7
    //   246: ldc_w 469
    //   249: invokevirtual 472	com/avg/tuneup/traffic/s:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)J
    //   252: aload_0
    //   253: ldc_w 467
    //   256: aload 7
    //   258: ldc_w 474
    //   261: invokevirtual 472	com/avg/tuneup/traffic/s:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)J
    //   264: iconst_1
    //   265: invokevirtual 456	com/avg/tuneup/traffic/x:a	(Ljava/lang/String;JJZ)V
    //   268: iload_2
    //   269: iconst_1
    //   270: iadd
    //   271: istore_2
    //   272: goto -50 -> 222
    //   275: astore 6
    //   277: aload 6
    //   279: invokestatic 178	com/avg/toolkit/g/a:a	(Ljava/lang/Exception;)V
    //   282: aload 5
    //   284: ifnull -218 -> 66
    //   287: aload 5
    //   289: invokevirtual 434	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   292: return
    //   293: astore 5
    //   295: return
    //   296: astore 6
    //   298: aconst_null
    //   299: astore 5
    //   301: aload 5
    //   303: ifnull +8 -> 311
    //   306: aload 5
    //   308: invokevirtual 434	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   311: aload 6
    //   313: athrow
    //   314: astore 5
    //   316: return
    //   317: astore 5
    //   319: goto -8 -> 311
    //   322: astore 6
    //   324: goto -23 -> 301
    //   327: astore 6
    //   329: goto -28 -> 301
    //   332: astore 6
    //   334: goto -57 -> 277
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	337	0	this	s
    //   0	337	1	paramBoolean	boolean
    //   81	191	2	m	int
    //   1	220	3	n	int
    //   218	8	4	i1	int
    //   9	193	5	localObject1	Object
    //   207	81	5	localException1	Exception
    //   293	1	5	localException2	Exception
    //   299	8	5	localObject2	Object
    //   314	1	5	localException3	Exception
    //   317	1	5	localException4	Exception
    //   18	211	6	localObject3	Object
    //   275	3	6	localException5	Exception
    //   296	16	6	localObject4	Object
    //   322	1	6	localObject5	Object
    //   327	1	6	localObject6	Object
    //   332	1	6	localException6	Exception
    //   6	251	7	localList	List
    // Exception table:
    //   from	to	target	type
    //   201	206	207	java/lang/Exception
    //   11	20	275	java/lang/Exception
    //   287	292	293	java/lang/Exception
    //   11	20	296	finally
    //   61	66	314	java/lang/Exception
    //   306	311	317	java/lang/Exception
    //   39	45	322	finally
    //   50	56	322	finally
    //   67	82	322	finally
    //   86	153	322	finally
    //   164	191	322	finally
    //   191	196	322	finally
    //   210	220	322	finally
    //   234	268	322	finally
    //   277	282	327	finally
    //   39	45	332	java/lang/Exception
    //   50	56	332	java/lang/Exception
    //   67	82	332	java/lang/Exception
    //   86	153	332	java/lang/Exception
    //   164	191	332	java/lang/Exception
    //   191	196	332	java/lang/Exception
    //   210	220	332	java/lang/Exception
    //   234	268	332	java/lang/Exception
  }
  
  public static boolean b(Context paramContext)
  {
    String str1;
    String str2;
    try
    {
      int m = paramContext.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramContext.getPackageName());
      if (m == -1) {
        return false;
      }
    }
    catch (Exception localException)
    {
      a.b("Error occurred when accessing package manager. Continue further check for 'has mobile data' sequence");
      str1 = ((TelephonyManager)paramContext.getSystemService("phone")).getLine1Number();
      str2 = ((TelephonyManager)paramContext.getSystemService("phone")).getSimSerialNumber();
      if (f()) {
        break label107;
      }
    }
    if ((paramContext.getPackageManager().hasSystemFeature("android.hardware.telephony")) || ((str2 != null) && (!"".equals(str2))) || ((str1 != null) && (!"".equals(str1)))) {}
    label107:
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private double[] c(boolean paramBoolean)
  {
    for (;;)
    {
      synchronized (this.g)
      {
        if (this.g.booleanValue()) {
          return null;
        }
        this.g = Boolean.valueOf(true);
        b(paramBoolean);
        ??? = new double[2];
        if (paramBoolean)
        {
          paramBoolean = i.n();
          double[] arrayOfDouble1 = c();
          ??? = arrayOfDouble1;
          if (arrayOfDouble1 != null)
          {
            if ((!i.j()) || (!a(arrayOfDouble1[0], paramBoolean))) {
              break label319;
            }
            a(arrayOfDouble1, paramBoolean);
            ZENCommManager.a(this.f, "DataPlanWarning");
            if ((i.j()) && (a(arrayOfDouble1[0] + 3.0D, paramBoolean)))
            {
              ??? = new Bundle();
              ((Bundle)???).putInt("EVENT", 13);
              ((Bundle)???).putInt("OVERLAY_LOAD_TYPE", 0);
              h.a(this.f, 27000, 0, (Bundle)???);
              ??? = new Bundle();
              ((Bundle)???).putInt("EVENT", 14);
              ((Bundle)???).putInt("OVERLAY_LOAD_TYPE", 0);
              h.a(this.f, 27000, 0, (Bundle)???);
            }
            if ((i.j()) && (i.b()) && (arrayOfDouble1[0] + 3.0D >= 100.0D))
            {
              ??? = new Bundle();
              ((Bundle)???).putInt("EVENT", 15);
              ((Bundle)???).putInt("OVERLAY_LOAD_TYPE", 0);
              h.a(this.f, 27000, 0, (Bundle)???);
              ??? = new Bundle();
              ((Bundle)???).putInt("EVENT", 16);
              ((Bundle)???).putInt("OVERLAY_LOAD_TYPE", 0);
              h.a(this.f, 27000, 0, (Bundle)???);
            }
            h.a(this.f, 25000, 8, TrafficWidgetPlugin.k());
            ??? = arrayOfDouble1;
          }
        }
        this.g = Boolean.valueOf(false);
        return ???;
      }
      label319:
      if ((paramBoolean) && (i.b())) {
        a(arrayOfDouble2);
      }
    }
  }
  
  public static boolean f()
  {
    return i.b();
  }
  
  private boolean p()
  {
    this.h = false;
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.f.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        localNetworkInfo.isConnected();
        if (localNetworkInfo.getType() == 0) {
          this.h = true;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a.b("Error while tring to create ConnectivityManager");
      }
    }
    return this.h;
  }
  
  public long a(String paramString, int paramInt)
  {
    return a(paramString, paramInt, w.a());
  }
  
  public long a(String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1).append(paramString2).append(paramString3);
    return a(localStringBuilder.toString());
  }
  
  public void a()
  {
    if (!w.a(this.j)) {
      new Thread(this.k).start();
    }
  }
  
  public void a(long paramLong)
  {
    i.b(paramLong);
    i.g(0);
    d();
  }
  
  public void a(double[] paramArrayOfDouble)
  {
    int m = h();
    Object localObject;
    if (m <= 0) {
      localObject = "" + this.f.getString(g.traffic_network_usage_notification_midnight).replace("[used]", Formatter.formatFileSize(this.f, paramArrayOfDouble[1]));
    }
    for (;;)
    {
      String str = ((String)localObject).replace("[total]", Formatter.formatFileSize(this.f, i.d())).replace("[number]", h() + "");
      DecimalFormat localDecimalFormat = new DecimalFormat("#.#");
      Bundle localBundle = new Bundle();
      if (r.c())
      {
        localObject = "com.antivirus.ui.tablet.DualPaneActivity";
        localBundle.putInt("EXTRA_GOTO", 3);
      }
      try
      {
        for (;;)
        {
          localObject = Class.forName((String)localObject);
          localObject = new Intent(this.f, (Class)localObject);
          ((Intent)localObject).setAction("antivirus.notification.DATA_USAGE");
          ((Intent)localObject).putExtras(localBundle);
          ((Intent)localObject).setFlags(608305152);
          ((Intent)localObject).putExtra("NOTIFICATION_EXTRA_TOP", 15);
          ((Intent)localObject).putExtra("NOTIFICATION_EXTRA_AFTER", 16);
          localObject = PendingIntent.getActivity(this.f, 0, (Intent)localObject, 268435456);
          paramArrayOfDouble = this.f.getString(g.traffic_network_usage).replace("[number]", localDecimalFormat.format(paramArrayOfDouble[0]));
          new Notification();
          paramArrayOfDouble = a(str, (PendingIntent)localObject, paramArrayOfDouble, 10);
          ((NotificationManager)this.f.getSystemService("notification")).notify(12, paramArrayOfDouble);
          return;
          if (m == 1)
          {
            localObject = "" + this.f.getString(g.traffic_network_usage_notification_day).replace("[used]", Formatter.formatFileSize(this.f, paramArrayOfDouble[1]));
            break;
          }
          localObject = "" + this.f.getString(g.traffic_network_usage_notification).replace("[used]", Formatter.formatFileSize(this.f, paramArrayOfDouble[1]));
          break;
          localObject = "com.antivirus.ui.performance.traffic.AvTrafficMeterActivity";
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        for (;;)
        {
          TrafficMeterActivity localTrafficMeterActivity = TrafficMeterActivity.class;
        }
      }
    }
  }
  
  public double[] a(boolean paramBoolean)
  {
    return a(paramBoolean, 5);
  }
  
  public double[] a(boolean paramBoolean, int paramInt)
  {
    int m = 0;
    double[] arrayOfDouble = c(paramBoolean);
    for (;;)
    {
      if ((arrayOfDouble == null) && (m < paramInt)) {
        try
        {
          Thread.sleep(400L);
          m += 1;
          arrayOfDouble = c(paramBoolean);
        }
        catch (Exception localException)
        {
          for (;;)
          {
            a.a(localException);
          }
        }
      }
    }
    return localException;
  }
  
  public long[] a(int paramInt)
  {
    e[0] = a("/proc/uid_stat/", paramInt, w.a());
    e[1] = b("/proc/uid_stat/", paramInt, w.a());
    return e;
  }
  
  public long[] a(int paramInt, String paramString)
  {
    switch (paramInt)
    {
    case 2: 
    default: 
      return null;
    case 0: 
      return e;
    case 1: 
      return e;
    }
    e = this.d.b(paramString);
    return e;
  }
  
  public long b(String paramString, int paramInt)
  {
    return b(paramString, paramInt, w.a());
  }
  
  public void b()
  {
    this.d = null;
  }
  
  public long[] b(int paramInt)
  {
    long[] arrayOfLong2 = new long[2];
    arrayOfLong2[0] = 0L;
    arrayOfLong2[1] = 0L;
    long[] arrayOfLong1 = arrayOfLong2;
    switch (paramInt)
    {
    case 2: 
    default: 
      arrayOfLong1 = null;
    case 0: 
    case 1: 
      return arrayOfLong1;
    }
    String[] arrayOfString = b;
    int m = arrayOfString.length;
    paramInt = 0;
    for (;;)
    {
      arrayOfLong1 = arrayOfLong2;
      if (paramInt >= m) {
        break;
      }
      arrayOfLong1 = arrayOfString[paramInt];
      if (this.d == null) {
        return null;
      }
      arrayOfLong1 = this.d.b(arrayOfLong1);
      arrayOfLong2[0] += arrayOfLong1[0];
      arrayOfLong2[1] += arrayOfLong1[1];
      paramInt += 1;
    }
  }
  
  public double[] c()
  {
    Object localObject = b(3);
    long l;
    if (localObject != null) {
      l = localObject[0];
    }
    for (double d1 = localObject[1] + l;; d1 = 0.0D)
    {
      double d2 = i.d();
      localObject = new double[2];
      localObject[0] = 0.0D;
      if (d2 != 0.0D) {
        localObject[0] = (100.0D * d1 / d2);
      }
      localObject[1] = d1;
      return localObject;
    }
  }
  
  /* Error */
  public void d()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   6: invokevirtual 423	com/avg/tuneup/traffic/x:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 4
    //   11: aload 4
    //   13: astore_3
    //   14: aload_3
    //   15: invokevirtual 437	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   18: aload_0
    //   19: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   22: ldc_w 644
    //   25: invokevirtual 646	com/avg/tuneup/traffic/x:a	(Ljava/lang/String;)V
    //   28: aload_0
    //   29: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   32: aload_3
    //   33: invokevirtual 649	com/avg/tuneup/traffic/x:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   36: getstatic 183	android/os/Build$VERSION:SDK_INT	I
    //   39: bipush 8
    //   41: if_icmplt +116 -> 157
    //   44: aload_0
    //   45: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   48: getstatic 62	com/avg/tuneup/traffic/s:b	[Ljava/lang/String;
    //   51: iconst_0
    //   52: aaload
    //   53: invokestatic 459	android/net/TrafficStats:getMobileRxBytes	()J
    //   56: invokestatic 462	android/net/TrafficStats:getMobileTxBytes	()J
    //   59: invokevirtual 652	com/avg/tuneup/traffic/x:a	(Ljava/lang/String;JJ)V
    //   62: aload_0
    //   63: invokevirtual 419	com/avg/tuneup/traffic/s:e	()Ljava/util/List;
    //   66: astore 4
    //   68: aload 4
    //   70: ifnull +149 -> 219
    //   73: aload 4
    //   75: invokeinterface 442 1 0
    //   80: iconst_1
    //   81: isub
    //   82: istore_1
    //   83: iload_1
    //   84: iflt +135 -> 219
    //   87: aload_0
    //   88: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   91: aload 4
    //   93: iload_1
    //   94: invokeinterface 446 2 0
    //   99: checkcast 105	android/content/pm/ApplicationInfo
    //   102: getfield 118	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   105: aload_0
    //   106: ldc_w 448
    //   109: aload 4
    //   111: iload_1
    //   112: invokeinterface 446 2 0
    //   117: checkcast 105	android/content/pm/ApplicationInfo
    //   120: getfield 112	android/content/pm/ApplicationInfo:uid	I
    //   123: invokevirtual 451	com/avg/tuneup/traffic/s:a	(Ljava/lang/String;I)J
    //   126: aload_0
    //   127: ldc_w 448
    //   130: aload 4
    //   132: iload_1
    //   133: invokeinterface 446 2 0
    //   138: checkcast 105	android/content/pm/ApplicationInfo
    //   141: getfield 112	android/content/pm/ApplicationInfo:uid	I
    //   144: invokevirtual 453	com/avg/tuneup/traffic/s:b	(Ljava/lang/String;I)J
    //   147: invokevirtual 652	com/avg/tuneup/traffic/x:a	(Ljava/lang/String;JJ)V
    //   150: iload_1
    //   151: iconst_1
    //   152: isub
    //   153: istore_1
    //   154: goto -71 -> 83
    //   157: getstatic 62	com/avg/tuneup/traffic/s:b	[Ljava/lang/String;
    //   160: astore 4
    //   162: aload 4
    //   164: arraylength
    //   165: istore_2
    //   166: iconst_0
    //   167: istore_1
    //   168: iload_1
    //   169: iload_2
    //   170: if_icmpge -108 -> 62
    //   173: aload 4
    //   175: iload_1
    //   176: aaload
    //   177: astore 5
    //   179: aload_0
    //   180: getfield 97	com/avg/tuneup/traffic/s:d	Lcom/avg/tuneup/traffic/x;
    //   183: aload 5
    //   185: aload_0
    //   186: ldc_w 467
    //   189: aload 5
    //   191: ldc_w 469
    //   194: invokevirtual 472	com/avg/tuneup/traffic/s:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)J
    //   197: aload_0
    //   198: ldc_w 467
    //   201: aload 5
    //   203: ldc_w 474
    //   206: invokevirtual 472	com/avg/tuneup/traffic/s:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)J
    //   209: invokevirtual 652	com/avg/tuneup/traffic/x:a	(Ljava/lang/String;JJ)V
    //   212: iload_1
    //   213: iconst_1
    //   214: iadd
    //   215: istore_1
    //   216: goto -48 -> 168
    //   219: aload_3
    //   220: invokevirtual 465	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   223: aload_3
    //   224: ifnull +7 -> 231
    //   227: aload_3
    //   228: invokevirtual 434	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   231: return
    //   232: astore 4
    //   234: aload 4
    //   236: invokestatic 178	com/avg/toolkit/g/a:a	(Ljava/lang/Exception;)V
    //   239: aload_3
    //   240: ifnull -9 -> 231
    //   243: aload_3
    //   244: invokevirtual 434	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   247: return
    //   248: astore 4
    //   250: aconst_null
    //   251: astore_3
    //   252: aload_3
    //   253: ifnull +7 -> 260
    //   256: aload_3
    //   257: invokevirtual 434	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   260: aload 4
    //   262: athrow
    //   263: astore 4
    //   265: goto -13 -> 252
    //   268: astore 4
    //   270: goto -18 -> 252
    //   273: astore 4
    //   275: goto -41 -> 234
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	278	0	this	s
    //   82	134	1	m	int
    //   165	6	2	n	int
    //   1	256	3	localObject1	Object
    //   9	165	4	localObject2	Object
    //   232	3	4	localException1	Exception
    //   248	13	4	localObject3	Object
    //   263	1	4	localObject4	Object
    //   268	1	4	localObject5	Object
    //   273	1	4	localException2	Exception
    //   177	25	5	str	String
    // Exception table:
    //   from	to	target	type
    //   2	11	232	java/lang/Exception
    //   2	11	248	finally
    //   14	62	263	finally
    //   62	68	263	finally
    //   73	83	263	finally
    //   87	150	263	finally
    //   157	166	263	finally
    //   179	212	263	finally
    //   219	223	263	finally
    //   234	239	268	finally
    //   14	62	273	java/lang/Exception
    //   62	68	273	java/lang/Exception
    //   73	83	273	java/lang/Exception
    //   87	150	273	java/lang/Exception
    //   157	166	273	java/lang/Exception
    //   179	212	273	java/lang/Exception
    //   219	223	273	java/lang/Exception
  }
  
  public List e()
  {
    Object localObject2 = null;
    Object localObject1 = null;
    if (this.f.getPackageManager() != null) {}
    try
    {
      localObject2 = this.f.getPackageManager().getInstalledApplications(0);
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a.a(localException);
      }
    }
    localObject2 = localObject1;
    if (localObject1 != null)
    {
      localObject1.add(this.i);
      localObject2 = localObject1;
    }
    return localObject2;
  }
  
  public Calendar g()
  {
    int n = 2;
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).setTimeInMillis(i.l());
    localObject = new GregorianCalendar(((Calendar)localObject).get(1), ((Calendar)localObject).get(2), ((Calendar)localObject).get(5));
    int i1 = i.g();
    int m = n;
    switch (i.f())
    {
    default: 
      m = n;
    }
    for (;;)
    {
      ((GregorianCalendar)localObject).add(m, i1);
      return localObject;
      m = 6;
      continue;
      m = 3;
    }
  }
  
  public int h()
  {
    int n = (int)((g().getTimeInMillis() - System.currentTimeMillis()) / 1000L / 60L / 60L / 24L);
    int m = n;
    if (n < 0) {
      m = 0;
    }
    return m;
  }
  
  public void i()
  {
    i.a(false);
    i.b(-1L);
    ((NotificationManager)this.f.getSystemService("notification")).cancel(12);
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("__SAD", false);
    h.a(this.f, 11000, 2, localBundle);
    h.a(this.f, 25000, 8, TrafficWidgetPlugin.k());
    ZENCommManager.a(this.f, "DataPlanRemoved");
  }
  
  public void j()
  {
    new Thread(new u(this)).start();
  }
  
  public void k()
  {
    new Thread(new v(this)).start();
  }
  
  public void l()
  {
    n();
    m();
  }
  
  public void m()
  {
    Object localObject = new Intent(this.f, AlarmReceiver.class);
    ((Intent)localObject).putExtra("alarm_code", 11000);
    ((Intent)localObject).putExtra("alarm_code2", 2);
    localObject = PendingIntent.getBroadcast(this.f, 2, (Intent)localObject, 134217728);
    Calendar localCalendar = g();
    AlarmManager localAlarmManager = (AlarmManager)this.f.getSystemService("alarm");
    localAlarmManager.cancel((PendingIntent)localObject);
    localAlarmManager.set(0, localCalendar.getTimeInMillis(), (PendingIntent)localObject);
  }
  
  public void n()
  {
    int n = i.i();
    if (n >= 0)
    {
      m = n;
      if (n < a.length) {}
    }
    else
    {
      m = 1;
    }
    n = a[m];
    int m = n;
    if (n < 30000) {
      m = 30000;
    }
    this.h = p();
    if (!this.h) {
      if (m <= 600000) {}
    }
    for (;;)
    {
      Object localObject = new Intent(this.f, AlarmReceiver.class);
      ((Intent)localObject).putExtra("alarm_code", 11000);
      ((Intent)localObject).putExtra("alarm_code2", 1);
      localObject = PendingIntent.getBroadcast(this.f, 1, (Intent)localObject, 134217728);
      AlarmManager localAlarmManager = (AlarmManager)this.f.getSystemService("alarm");
      localAlarmManager.cancel((PendingIntent)localObject);
      localAlarmManager.setRepeating(3, SystemClock.elapsedRealtime(), m, (PendingIntent)localObject);
      return;
      m = 600000;
    }
  }
  
  public void o()
  {
    Object localObject1 = new Intent(this.f, AlarmReceiver.class);
    ((Intent)localObject1).putExtra("alarm_code", 11000);
    ((Intent)localObject1).putExtra("alarm_code2", 1);
    localObject1 = PendingIntent.getBroadcast(this.f, 1, (Intent)localObject1, 134217728);
    Object localObject2 = new Intent(this.f, AlarmReceiver.class);
    ((Intent)localObject2).putExtra("alarm_code", 11000);
    ((Intent)localObject2).putExtra("alarm_code2", 2);
    localObject2 = PendingIntent.getBroadcast(this.f, 2, (Intent)localObject2, 134217728);
    ((NotificationManager)this.f.getSystemService("notification")).cancel(12);
    AlarmManager localAlarmManager = (AlarmManager)this.f.getSystemService("alarm");
    localAlarmManager.cancel((PendingIntent)localObject1);
    localAlarmManager.cancel((PendingIntent)localObject2);
  }
}
