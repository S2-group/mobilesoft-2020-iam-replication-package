package ctrip.android.view.push;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import ctrip.android.view.h5.activity.i;
import ctrip.base.logical.component.CtripBaseApplication;
import ctrip.base.logical.component.b.g;
import ctrip.business.controller.BusinessController;
import ctrip.business.other.MessagePushingRequest;
import ctrip.business.util.CtripActionCodeLogUtil;
import ctrip.business.util.DateUtil;
import ctrip.business.util.DeviceUtil;
import ctrip.business.util.FileUtil;
import ctrip.business.util.LogUtil;
import ctrip.business.util.StringUtil;
import ctrip.business.util.VersionControlUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class PushService
  extends Service
  implements ConnectCallBack
{
  public static boolean IS_TEST = false;
  public static int KeepLiveInterval;
  public static String PREFS_NAME;
  public static boolean background = false;
  public static long backgroundData = 0L;
  public static Handler handler = new Handler(Looper.getMainLooper());
  public static int heartBreakBeCloseCount;
  public static int heartBreakCount;
  public static int heartBreakPageCount;
  public static int heartBreakReConnectCount;
  public static int heartBreakRegCount;
  public static int heartBreakResetCount;
  public static boolean inBumping;
  public static int logCount = 0;
  public static boolean registSuccess;
  public static boolean updatingDex;
  public static boolean updatingSo;
  public AlarmManager alarmManager;
  private int lastNetworkType = -2;
  private long lastRxBytes = 0L;
  private int lastState = -2;
  private long lastTxBytes = 0L;
  public LongConnectSocketInterface longConnectSocket;
  public Runnable pushRunnable = new a(this);
  public String startLogTime = "";
  
  static
  {
    PREFS_NAME = "ConfigSetting";
    heartBreakCount = 0;
    heartBreakRegCount = 0;
    heartBreakPageCount = 0;
    heartBreakReConnectCount = 0;
    heartBreakResetCount = 0;
    heartBreakBeCloseCount = 0;
    registSuccess = false;
    KeepLiveInterval = 180;
    updatingDex = false;
    updatingSo = false;
    inBumping = false;
  }
  
  public PushService() {}
  
  private boolean RootCommand(String paramString)
  {
    try
    {
      Process localProcess = Runtime.getRuntime().exec("sh");
      DataInputStream localDataInputStream = new DataInputStream(localProcess.getInputStream());
      DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
      localDataOutputStream.writeBytes("cd /data/data/" + getPackageName() + "\n");
      localDataOutputStream.writeBytes(paramString + " &\n");
      localDataOutputStream.writeBytes("exit\n");
      localDataOutputStream.flush();
      localProcess.waitFor();
      localDataInputStream.read(new byte[localDataInputStream.available()]);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private float byteToM(long paramLong)
  {
    return new BigDecimal(Long.toString(paramLong)).divide(new BigDecimal(1048576), 2, 0).floatValue();
  }
  
  private void checkDexUpdate()
  {
    if ((updatingDex) || (backgroundData > 1048576L)) {}
    SharedPreferences localSharedPreferences;
    do
    {
      return;
      localSharedPreferences = BusinessController.getApplication().getSharedPreferences(PREFS_NAME, 0);
    } while (!localSharedPreferences.getBoolean("needincrementData", false));
    int i = localSharedPreferences.getInt("incrementDataVersion", -1);
    new Thread(new d(this, localSharedPreferences.getString("downLoadURL", ""), localSharedPreferences.getString("datahashCode", ""), i)).start();
  }
  
  private void checkSoUpdate() {}
  
  private int getSwitchedType(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
    default: 
      return paramInt;
    }
    return 0;
  }
  
  private String getUserSerial()
  {
    Object localObject1 = getSystemService("user");
    if (localObject1 == null) {
      return null;
    }
    try
    {
      Object localObject2 = android.os.Process.class.getMethod("myUserHandle", (Class[])null).invoke(android.os.Process.class, (Object[])null);
      long l = ((Long)localObject1.getClass().getMethod("getSerialNumberForUser", new Class[] { localObject2.getClass() }).invoke(localObject1, new Object[] { localObject2 })).longValue();
      return String.valueOf(l);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
  }
  
  private void initNewWorkLog()
  {
    backgroundData = 0L;
    Object localObject1 = getSharedPreferences(PREFS_NAME, 0).edit();
    ((SharedPreferences.Editor)localObject1).putLong("2G/3G_up", 0L);
    ((SharedPreferences.Editor)localObject1).putLong("2G/3G_down", 0L);
    ((SharedPreferences.Editor)localObject1).putLong("2G_up", 0L);
    ((SharedPreferences.Editor)localObject1).putLong("2G_down", 0L);
    ((SharedPreferences.Editor)localObject1).putLong("3G_up", 0L);
    ((SharedPreferences.Editor)localObject1).putLong("3G_down", 0L);
    ((SharedPreferences.Editor)localObject1).putLong("WIFI_up", 0L);
    ((SharedPreferences.Editor)localObject1).putLong("WIFI_down", 0L);
    ((SharedPreferences.Editor)localObject1).putLong("unkown_up", 0L);
    ((SharedPreferences.Editor)localObject1).putLong("unkown_down", 0L);
    ((SharedPreferences.Editor)localObject1).commit();
    getData();
    localObject1 = (ConnectivityManager)getSystemService("connectivity");
    try
    {
      localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
      if (localObject1 != null)
      {
        this.lastState = getSwitchedType(((NetworkInfo)localObject1).getType());
        localObject1 = (TelephonyManager)getSystemService("phone");
        if (localObject1 != null)
        {
          i = ((TelephonyManager)localObject1).getNetworkType();
          this.lastNetworkType = i;
        }
      }
      else
      {
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        Object localObject2 = null;
        continue;
        int i = 0;
      }
    }
  }
  
  /* Error */
  private void initParameter()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore_2
    //   8: aconst_null
    //   9: astore_3
    //   10: aconst_null
    //   11: astore 7
    //   13: aconst_null
    //   14: astore 8
    //   16: aconst_null
    //   17: astore 4
    //   19: new 399	java/util/Properties
    //   22: dup
    //   23: invokespecial 400	java/util/Properties:<init>	()V
    //   26: astore 9
    //   28: aload_0
    //   29: invokevirtual 404	ctrip/android/view/push/PushService:getAssets	()Landroid/content/res/AssetManager;
    //   32: ldc_w 406
    //   35: invokevirtual 412	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   38: astore_1
    //   39: aload 4
    //   41: astore_2
    //   42: aload_1
    //   43: ifnull +627 -> 670
    //   46: aload_3
    //   47: astore_2
    //   48: aload_1
    //   49: astore 4
    //   51: aload 7
    //   53: astore 6
    //   55: aload 8
    //   57: astore 5
    //   59: aload 9
    //   61: aload_1
    //   62: invokevirtual 415	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   65: aload 9
    //   67: ifnull +24 -> 91
    //   70: aload_3
    //   71: astore_2
    //   72: aload_1
    //   73: astore 4
    //   75: aload 7
    //   77: astore 6
    //   79: aload 8
    //   81: astore 5
    //   83: aload 9
    //   85: invokevirtual 418	java/util/Properties:size	()I
    //   88: ifne +22 -> 110
    //   91: aload_3
    //   92: astore_2
    //   93: aload_1
    //   94: astore 4
    //   96: aload 7
    //   98: astore 6
    //   100: aload 8
    //   102: astore 5
    //   104: ldc_w 420
    //   107: invokestatic 425	ctrip/business/util/LogUtil:e	(Ljava/lang/String;)V
    //   110: aload_3
    //   111: astore_2
    //   112: aload_1
    //   113: astore 4
    //   115: aload 7
    //   117: astore 6
    //   119: aload 8
    //   121: astore 5
    //   123: aload 9
    //   125: ldc_w 427
    //   128: invokevirtual 431	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   131: invokevirtual 434	java/lang/String:trim	()Ljava/lang/String;
    //   134: astore 9
    //   136: aload_3
    //   137: astore_2
    //   138: aload_1
    //   139: astore 4
    //   141: aload 7
    //   143: astore 6
    //   145: aload 8
    //   147: astore 5
    //   149: new 399	java/util/Properties
    //   152: dup
    //   153: invokespecial 400	java/util/Properties:<init>	()V
    //   156: astore 10
    //   158: aload_3
    //   159: astore_2
    //   160: aload_1
    //   161: astore 4
    //   163: aload 7
    //   165: astore 6
    //   167: aload 8
    //   169: astore 5
    //   171: aload_0
    //   172: invokevirtual 438	ctrip/android/view/push/PushService:getResources	()Landroid/content/res/Resources;
    //   175: ldc_w 439
    //   178: invokevirtual 445	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   181: astore_3
    //   182: aload_3
    //   183: astore_2
    //   184: aload_3
    //   185: ifnull +485 -> 670
    //   188: aload_3
    //   189: astore_2
    //   190: aload_1
    //   191: astore 4
    //   193: aload_3
    //   194: astore 6
    //   196: aload_3
    //   197: astore 5
    //   199: aload 10
    //   201: aload_3
    //   202: invokevirtual 415	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   205: aload 10
    //   207: ifnull +22 -> 229
    //   210: aload_3
    //   211: astore_2
    //   212: aload_1
    //   213: astore 4
    //   215: aload_3
    //   216: astore 6
    //   218: aload_3
    //   219: astore 5
    //   221: aload 10
    //   223: invokevirtual 418	java/util/Properties:size	()I
    //   226: ifne +20 -> 246
    //   229: aload_3
    //   230: astore_2
    //   231: aload_1
    //   232: astore 4
    //   234: aload_3
    //   235: astore 6
    //   237: aload_3
    //   238: astore 5
    //   240: ldc_w 447
    //   243: invokestatic 425	ctrip/business/util/LogUtil:e	(Ljava/lang/String;)V
    //   246: aload_3
    //   247: astore_2
    //   248: aload_1
    //   249: astore 4
    //   251: aload_3
    //   252: astore 6
    //   254: aload_3
    //   255: astore 5
    //   257: aload 10
    //   259: ldc_w 449
    //   262: invokevirtual 431	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   265: astore 7
    //   267: aload_3
    //   268: astore_2
    //   269: aload_1
    //   270: astore 4
    //   272: aload_3
    //   273: astore 6
    //   275: aload_3
    //   276: astore 5
    //   278: aload 10
    //   280: ldc_w 451
    //   283: invokevirtual 431	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   286: astore 8
    //   288: aload_3
    //   289: astore_2
    //   290: aload_1
    //   291: astore 4
    //   293: aload_3
    //   294: astore 6
    //   296: aload_3
    //   297: astore 5
    //   299: aload 10
    //   301: ldc_w 453
    //   304: invokevirtual 431	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   307: astore 11
    //   309: aload_3
    //   310: astore_2
    //   311: aload_1
    //   312: astore 4
    //   314: aload_3
    //   315: astore 6
    //   317: aload_3
    //   318: astore 5
    //   320: aload 10
    //   322: ldc_w 455
    //   325: invokevirtual 431	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   328: astore 12
    //   330: aload_3
    //   331: astore_2
    //   332: aload_1
    //   333: astore 4
    //   335: aload_3
    //   336: astore 6
    //   338: aload_3
    //   339: astore 5
    //   341: aload 10
    //   343: ldc_w 457
    //   346: invokevirtual 431	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   349: astore 10
    //   351: aload_3
    //   352: astore_2
    //   353: aload_1
    //   354: astore 4
    //   356: aload_3
    //   357: astore 6
    //   359: aload_3
    //   360: astore 5
    //   362: aload 7
    //   364: invokestatic 462	ctrip/business/util/StringUtil:emptyOrNull	(Ljava/lang/String;)Z
    //   367: ifne +98 -> 465
    //   370: aload_3
    //   371: astore_2
    //   372: aload_1
    //   373: astore 4
    //   375: aload_3
    //   376: astore 6
    //   378: aload_3
    //   379: astore 5
    //   381: aload 8
    //   383: invokestatic 462	ctrip/business/util/StringUtil:emptyOrNull	(Ljava/lang/String;)Z
    //   386: ifne +79 -> 465
    //   389: aload_3
    //   390: astore_2
    //   391: aload_1
    //   392: astore 4
    //   394: aload_3
    //   395: astore 6
    //   397: aload_3
    //   398: astore 5
    //   400: aload 11
    //   402: invokestatic 462	ctrip/business/util/StringUtil:emptyOrNull	(Ljava/lang/String;)Z
    //   405: ifne +60 -> 465
    //   408: aload_3
    //   409: astore_2
    //   410: aload_1
    //   411: astore 4
    //   413: aload_3
    //   414: astore 6
    //   416: aload_3
    //   417: astore 5
    //   419: aload 9
    //   421: invokestatic 462	ctrip/business/util/StringUtil:emptyOrNull	(Ljava/lang/String;)Z
    //   424: ifne +41 -> 465
    //   427: aload_3
    //   428: astore_2
    //   429: aload_1
    //   430: astore 4
    //   432: aload_3
    //   433: astore 6
    //   435: aload_3
    //   436: astore 5
    //   438: aload 12
    //   440: invokestatic 462	ctrip/business/util/StringUtil:emptyOrNull	(Ljava/lang/String;)Z
    //   443: ifne +22 -> 465
    //   446: aload_3
    //   447: astore_2
    //   448: aload_1
    //   449: astore 4
    //   451: aload_3
    //   452: astore 6
    //   454: aload_3
    //   455: astore 5
    //   457: aload 10
    //   459: invokestatic 462	ctrip/business/util/StringUtil:emptyOrNull	(Ljava/lang/String;)Z
    //   462: ifeq +20 -> 482
    //   465: aload_3
    //   466: astore_2
    //   467: aload_1
    //   468: astore 4
    //   470: aload_3
    //   471: astore 6
    //   473: aload_3
    //   474: astore 5
    //   476: ldc_w 464
    //   479: invokestatic 425	ctrip/business/util/LogUtil:e	(Ljava/lang/String;)V
    //   482: aload_3
    //   483: astore_2
    //   484: aload_1
    //   485: astore 4
    //   487: aload_3
    //   488: astore 6
    //   490: aload_3
    //   491: astore 5
    //   493: aload 7
    //   495: invokevirtual 434	java/lang/String:trim	()Ljava/lang/String;
    //   498: putstatic 469	ctrip/business/controller/a:b	Ljava/lang/String;
    //   501: aload_3
    //   502: astore_2
    //   503: aload_1
    //   504: astore 4
    //   506: aload_3
    //   507: astore 6
    //   509: aload_3
    //   510: astore 5
    //   512: aload 8
    //   514: invokevirtual 434	java/lang/String:trim	()Ljava/lang/String;
    //   517: putstatic 472	ctrip/business/controller/a:c	Ljava/lang/String;
    //   520: aload_3
    //   521: astore_2
    //   522: aload_1
    //   523: astore 4
    //   525: aload_3
    //   526: astore 6
    //   528: aload_3
    //   529: astore 5
    //   531: aload 11
    //   533: invokevirtual 434	java/lang/String:trim	()Ljava/lang/String;
    //   536: putstatic 475	ctrip/business/controller/a:d	Ljava/lang/String;
    //   539: aload_3
    //   540: astore_2
    //   541: aload_1
    //   542: astore 4
    //   544: aload_3
    //   545: astore 6
    //   547: aload_3
    //   548: astore 5
    //   550: aload 9
    //   552: putstatic 477	ctrip/business/controller/a:e	Ljava/lang/String;
    //   555: aload_3
    //   556: astore_2
    //   557: aload_1
    //   558: astore 4
    //   560: aload_3
    //   561: astore 6
    //   563: aload_3
    //   564: astore 5
    //   566: aload 12
    //   568: putstatic 480	ctrip/business/controller/a:a	Ljava/lang/String;
    //   571: aload_3
    //   572: astore_2
    //   573: aload_1
    //   574: astore 4
    //   576: aload_3
    //   577: astore 6
    //   579: aload_3
    //   580: astore 5
    //   582: aload 9
    //   584: invokevirtual 434	java/lang/String:trim	()Ljava/lang/String;
    //   587: invokestatic 484	ctrip/business/util/StringUtil:toInt	(Ljava/lang/String;)I
    //   590: putstatic 487	ctrip/business/controller/a:f	I
    //   593: aload_3
    //   594: astore_2
    //   595: aload_1
    //   596: astore 4
    //   598: aload_3
    //   599: astore 6
    //   601: aload_3
    //   602: astore 5
    //   604: new 149	java/lang/StringBuilder
    //   607: dup
    //   608: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   611: invokestatic 493	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   614: invokevirtual 498	java/io/File:getPath	()Ljava/lang/String;
    //   617: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   620: ldc_w 500
    //   623: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   626: aload 10
    //   628: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   631: ldc_w 500
    //   634: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   640: putstatic 505	ctrip/business/util/FileUtil:FOLDER	Ljava/lang/String;
    //   643: aload_3
    //   644: astore_2
    //   645: aload_1
    //   646: astore 4
    //   648: aload_3
    //   649: astore 6
    //   651: aload_3
    //   652: astore 5
    //   654: new 495	java/io/File
    //   657: dup
    //   658: getstatic 505	ctrip/business/util/FileUtil:FOLDER	Ljava/lang/String;
    //   661: invokespecial 506	java/io/File:<init>	(Ljava/lang/String;)V
    //   664: invokevirtual 509	java/io/File:mkdirs	()Z
    //   667: pop
    //   668: aload_3
    //   669: astore_2
    //   670: aload_1
    //   671: ifnull +7 -> 678
    //   674: aload_1
    //   675: invokevirtual 514	java/io/InputStream:close	()V
    //   678: aload_2
    //   679: ifnull +7 -> 686
    //   682: aload_2
    //   683: invokevirtual 514	java/io/InputStream:close	()V
    //   686: return
    //   687: astore_1
    //   688: aload_1
    //   689: invokevirtual 515	java/io/IOException:printStackTrace	()V
    //   692: return
    //   693: astore_3
    //   694: aconst_null
    //   695: astore_1
    //   696: aload 5
    //   698: astore_2
    //   699: aload_1
    //   700: astore 4
    //   702: aload_3
    //   703: invokevirtual 515	java/io/IOException:printStackTrace	()V
    //   706: aload_1
    //   707: ifnull +7 -> 714
    //   710: aload_1
    //   711: invokevirtual 514	java/io/InputStream:close	()V
    //   714: aload 5
    //   716: ifnull -30 -> 686
    //   719: aload 5
    //   721: invokevirtual 514	java/io/InputStream:close	()V
    //   724: return
    //   725: astore_1
    //   726: aload_1
    //   727: invokevirtual 515	java/io/IOException:printStackTrace	()V
    //   730: return
    //   731: astore_1
    //   732: aconst_null
    //   733: astore_1
    //   734: aload_1
    //   735: ifnull +7 -> 742
    //   738: aload_1
    //   739: invokevirtual 514	java/io/InputStream:close	()V
    //   742: aload 6
    //   744: ifnull -58 -> 686
    //   747: aload 6
    //   749: invokevirtual 514	java/io/InputStream:close	()V
    //   752: return
    //   753: astore_1
    //   754: aload_1
    //   755: invokevirtual 515	java/io/IOException:printStackTrace	()V
    //   758: return
    //   759: astore_1
    //   760: aconst_null
    //   761: astore 4
    //   763: aload 4
    //   765: ifnull +8 -> 773
    //   768: aload 4
    //   770: invokevirtual 514	java/io/InputStream:close	()V
    //   773: aload_2
    //   774: ifnull +7 -> 781
    //   777: aload_2
    //   778: invokevirtual 514	java/io/InputStream:close	()V
    //   781: aload_1
    //   782: athrow
    //   783: astore_2
    //   784: aload_2
    //   785: invokevirtual 515	java/io/IOException:printStackTrace	()V
    //   788: goto -7 -> 781
    //   791: astore_1
    //   792: goto -29 -> 763
    //   795: astore_2
    //   796: goto -62 -> 734
    //   799: astore_3
    //   800: goto -104 -> 696
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	803	0	this	PushService
    //   38	637	1	localInputStream1	java.io.InputStream
    //   687	2	1	localIOException1	IOException
    //   695	16	1	localObject1	Object
    //   725	2	1	localIOException2	IOException
    //   731	1	1	localException1	Exception
    //   733	6	1	localObject2	Object
    //   753	2	1	localIOException3	IOException
    //   759	23	1	localObject3	Object
    //   791	1	1	localObject4	Object
    //   7	771	2	localObject5	Object
    //   783	2	2	localIOException4	IOException
    //   795	1	2	localException2	Exception
    //   9	660	3	localInputStream2	java.io.InputStream
    //   693	10	3	localIOException5	IOException
    //   799	1	3	localIOException6	IOException
    //   17	752	4	localObject6	Object
    //   1	719	5	localObject7	Object
    //   4	744	6	localObject8	Object
    //   11	483	7	str1	String
    //   14	499	8	str2	String
    //   26	557	9	localObject9	Object
    //   156	471	10	localObject10	Object
    //   307	225	11	str3	String
    //   328	239	12	str4	String
    // Exception table:
    //   from	to	target	type
    //   674	678	687	java/io/IOException
    //   682	686	687	java/io/IOException
    //   19	39	693	java/io/IOException
    //   710	714	725	java/io/IOException
    //   719	724	725	java/io/IOException
    //   19	39	731	java/lang/Exception
    //   738	742	753	java/io/IOException
    //   747	752	753	java/io/IOException
    //   19	39	759	finally
    //   768	773	783	java/io/IOException
    //   777	781	783	java/io/IOException
    //   59	65	791	finally
    //   83	91	791	finally
    //   104	110	791	finally
    //   123	136	791	finally
    //   149	158	791	finally
    //   171	182	791	finally
    //   199	205	791	finally
    //   221	229	791	finally
    //   240	246	791	finally
    //   257	267	791	finally
    //   278	288	791	finally
    //   299	309	791	finally
    //   320	330	791	finally
    //   341	351	791	finally
    //   362	370	791	finally
    //   381	389	791	finally
    //   400	408	791	finally
    //   419	427	791	finally
    //   438	446	791	finally
    //   457	465	791	finally
    //   476	482	791	finally
    //   493	501	791	finally
    //   512	520	791	finally
    //   531	539	791	finally
    //   550	555	791	finally
    //   566	571	791	finally
    //   582	593	791	finally
    //   604	643	791	finally
    //   654	668	791	finally
    //   702	706	791	finally
    //   59	65	795	java/lang/Exception
    //   83	91	795	java/lang/Exception
    //   104	110	795	java/lang/Exception
    //   123	136	795	java/lang/Exception
    //   149	158	795	java/lang/Exception
    //   171	182	795	java/lang/Exception
    //   199	205	795	java/lang/Exception
    //   221	229	795	java/lang/Exception
    //   240	246	795	java/lang/Exception
    //   257	267	795	java/lang/Exception
    //   278	288	795	java/lang/Exception
    //   299	309	795	java/lang/Exception
    //   320	330	795	java/lang/Exception
    //   341	351	795	java/lang/Exception
    //   362	370	795	java/lang/Exception
    //   381	389	795	java/lang/Exception
    //   400	408	795	java/lang/Exception
    //   419	427	795	java/lang/Exception
    //   438	446	795	java/lang/Exception
    //   457	465	795	java/lang/Exception
    //   476	482	795	java/lang/Exception
    //   493	501	795	java/lang/Exception
    //   512	520	795	java/lang/Exception
    //   531	539	795	java/lang/Exception
    //   550	555	795	java/lang/Exception
    //   566	571	795	java/lang/Exception
    //   582	593	795	java/lang/Exception
    //   604	643	795	java/lang/Exception
    //   654	668	795	java/lang/Exception
    //   59	65	799	java/io/IOException
    //   83	91	799	java/io/IOException
    //   104	110	799	java/io/IOException
    //   123	136	799	java/io/IOException
    //   149	158	799	java/io/IOException
    //   171	182	799	java/io/IOException
    //   199	205	799	java/io/IOException
    //   221	229	799	java/io/IOException
    //   240	246	799	java/io/IOException
    //   257	267	799	java/io/IOException
    //   278	288	799	java/io/IOException
    //   299	309	799	java/io/IOException
    //   320	330	799	java/io/IOException
    //   341	351	799	java/io/IOException
    //   362	370	799	java/io/IOException
    //   381	389	799	java/io/IOException
    //   400	408	799	java/io/IOException
    //   419	427	799	java/io/IOException
    //   438	446	799	java/io/IOException
    //   457	465	799	java/io/IOException
    //   476	482	799	java/io/IOException
    //   493	501	799	java/io/IOException
    //   512	520	799	java/io/IOException
    //   531	539	799	java/io/IOException
    //   550	555	799	java/io/IOException
    //   566	571	799	java/io/IOException
    //   582	593	799	java/io/IOException
    //   604	643	799	java/io/IOException
    //   654	668	799	java/io/IOException
  }
  
  private void startDaemon()
  {
    String str = "/data/data/" + getPackageName();
    str = str + "/daemon";
    RootCommand("chmod 777 " + str);
    RootCommand(str);
  }
  
  private void updateNewWorkLog(int paramInt)
  {
    long l2 = this.lastTxBytes;
    long l1 = this.lastRxBytes;
    getData();
    l2 = this.lastTxBytes - l2;
    long l3 = this.lastRxBytes;
    String str = "unkown";
    if (paramInt == 0) {
      switch (this.lastNetworkType)
      {
      case 3: 
      case 5: 
      case 6: 
      default: 
        str = "3G";
      }
    }
    for (;;)
    {
      Object localObject = getSharedPreferences(PREFS_NAME, 0);
      long l4 = ((SharedPreferences)localObject).getLong(str + "_up", 0L);
      long l5 = ((SharedPreferences)localObject).getLong(str + "_down", 0L);
      localObject = ((SharedPreferences)localObject).edit();
      ((SharedPreferences.Editor)localObject).putLong(str + "_up", l4 + l2);
      ((SharedPreferences.Editor)localObject).putLong(str + "_down", l3 - l1 + l5);
      if ((background) && (paramInt == 0)) {
        backgroundData += l2;
      }
      ((SharedPreferences.Editor)localObject).commit();
      return;
      str = "2G/3G";
      continue;
      str = "2G";
      continue;
      if (paramInt == 1) {
        str = "WIFI";
      }
    }
  }
  
  public void changeHeartBreak()
  {
    this.alarmManager = ((AlarmManager)getSystemService("alarm"));
    handler.removeCallbacks(this.pushRunnable);
    long l = SystemClock.uptimeMillis();
    handler.postAtTime(this.pushRunnable, l + KeepLiveInterval * 1000);
  }
  
  public void dispatchMessage(MessagePushingRequest paramMessagePushingRequest)
  {
    if ((paramMessagePushingRequest != null) && (paramMessagePushingRequest.deviceToken.equals(BusinessController.getAttribute(ctrip.business.controller.d.f))))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("ctrip.android.view.push.receiver");
      localIntent.putExtra(PushReceiver.TYPE, paramMessagePushingRequest.businessType);
      localIntent.putExtra(PushReceiver.MESSAGE, paramMessagePushingRequest.message);
      localIntent.putExtra(PushReceiver.UNREADCOUNT, paramMessagePushingRequest.unreadCount);
      localIntent.putExtra(PushReceiver.ADDITION, paramMessagePushingRequest.addtionalData);
      localIntent.putExtra(PushReceiver.ADDITION, paramMessagePushingRequest.addtionalData);
      sendBroadcast(localIntent);
    }
  }
  
  public void getClientID()
  {
    if (DeviceUtil.getSDKVersionInt() >= 16) {
      localObject = new File(getFilesDir().getParentFile().getAbsolutePath() + File.separator + "ClientId_Location");
    }
    while ((localObject != null) && (((File)localObject).exists()))
    {
      localObject = (String)FileUtil.readObjectFromLocation("ClientId_Location");
      if (!StringUtil.emptyOrNull((String)localObject))
      {
        BusinessController.setAttribute(ctrip.business.controller.d.f, (String)localObject);
        LogUtil.i("push:clientID:" + (String)localObject);
        return;
        localObject = new File("/data/data/ctrip.android.view/ClientId_Location");
      }
      else
      {
        if (new File(FileUtil.FOLDER + "clientID").exists())
        {
          localObject = (String)FileUtil.readObjectFromSdcard("clientID");
          BusinessController.setAttribute(ctrip.business.controller.d.f, (String)localObject);
          LogUtil.i("push:clientID:" + (String)localObject);
          return;
        }
        String str = VersionControlUtil.getClientID("");
        BusinessController.setAttribute(ctrip.business.controller.d.f, str);
        LogUtil.i("push:clientID:" + (String)localObject);
        return;
      }
    }
    if (new File(FileUtil.FOLDER + "clientID").exists())
    {
      localObject = (String)FileUtil.readObjectFromSdcard("clientID");
      BusinessController.setAttribute(ctrip.business.controller.d.f, (String)localObject);
      LogUtil.i("push:clientID:" + (String)localObject);
      return;
    }
    Object localObject = VersionControlUtil.getClientID("");
    BusinessController.setAttribute(ctrip.business.controller.d.f, (String)localObject);
    LogUtil.i("push:clientID:" + (String)localObject);
  }
  
  public void getData()
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    ApplicationInfo localApplicationInfo;
    int i;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localApplicationInfo = (ApplicationInfo)localIterator.next();
      i = localApplicationInfo.uid;
    } while (!localApplicationInfo.packageName.equals(getPackageName()));
    for (;;)
    {
      this.lastRxBytes = TrafficStats.getUidRxBytes(i);
      this.lastTxBytes = TrafficStats.getUidTxBytes(i);
      return;
      i = 0;
    }
  }
  
  public String getNetworkType()
  {
    Object localObject1 = (ConnectivityManager)getSystemService("connectivity");
    try
    {
      localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
      if (localObject1 != null) {
        if (((NetworkInfo)localObject1).getType() == 1) {
          return "WIFI";
        }
      }
    }
    catch (Exception localException)
    {
      Object localObject2;
      for (;;)
      {
        localException.printStackTrace();
        localObject2 = null;
      }
      if (localObject2.getType() == 0)
      {
        if (StringUtil.emptyOrNull(g.s())) {
          return "CELL";
        }
        return g.s();
      }
    }
    return "";
  }
  
  public boolean isSameType(int paramInt1, int paramInt2)
  {
    return getSwitchedType(paramInt1) == getSwitchedType(paramInt2);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    if (BusinessController.getApplication() == null) {
      BusinessController.setApplication(getApplication());
    }
    initParameter();
    Object localObject1 = i.a + File.separator + "third_jar";
    Object localObject2 = new File((String)localObject1);
    if (!((File)localObject2).exists()) {
      ((File)localObject2).mkdirs();
    }
    localObject1 = new File((String)localObject1 + File.separator + "connect.jar");
    if (!((File)localObject1).exists()) {
      localObject2 = CtripBaseApplication.a().getAssets();
    }
    try
    {
      i.a(((AssetManager)localObject2).open("third_jar" + File.separator + "connect.jar"), (File)localObject1);
      new Thread(new b(this)).start();
      this.alarmManager = ((AlarmManager)getSystemService("alarm"));
      getClientID();
      initNewWorkLog();
      localObject1 = new IntentFilter();
      ((IntentFilter)localObject1).addAction("android.net.conn.CONNECTIVITY_CHANGE");
      registerReceiver(new c(this), (IntentFilter)localObject1);
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  public void onDestroy()
  {
    LogUtil.e("PushService onDestroy");
    if (this.longConnectSocket != null) {
      this.longConnectSocket.closeConnection();
    }
    this.longConnectSocket = null;
    super.onDestroy();
    Object localObject = ((ActivityManager)getSystemService("activity")).getRunningAppProcesses();
    String str = getPackageName();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if ((localRunningAppProcessInfo.processName.startsWith(str)) && (localRunningAppProcessInfo.processName.endsWith(":push"))) {
        android.os.Process.killProcess(localRunningAppProcessInfo.pid);
      }
    }
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (BusinessController.getApplication() == null) {
      BusinessController.setApplication(getApplication());
    }
    Object localObject = getSharedPreferences(PREFS_NAME, 0);
    if (background != ((SharedPreferences)localObject).getBoolean("Background", false))
    {
      background = ((SharedPreferences)localObject).getBoolean("Background", false);
      updateNewWorkLog(this.lastState);
    }
    long l;
    if (paramIntent != null)
    {
      if (paramIntent.getBooleanExtra("HeartBreak", false)) {
        startHeartBreak();
      }
    }
    else
    {
      this.alarmManager = ((AlarmManager)getSystemService("alarm"));
      if (inBumping) {
        break label315;
      }
      inBumping = true;
      handler.removeCallbacks(this.pushRunnable);
      l = SystemClock.uptimeMillis();
      handler.postAtTime(this.pushRunnable, l + 3000L);
    }
    for (;;)
    {
      return super.onStartCommand(paramIntent, 2, paramInt2);
      if (paramIntent.getBooleanExtra("SendLog", false))
      {
        sendPushLog();
        localObject = new Intent(getApplicationContext(), PushService.class);
        ((Intent)localObject).putExtra("SendLog", true);
        localObject = PendingIntent.getService(getApplicationContext(), 32769, (Intent)localObject, 0);
        this.alarmManager.cancel((PendingIntent)localObject);
        l = SystemClock.elapsedRealtime();
        this.alarmManager.set(2, l + 3600000L, (PendingIntent)localObject);
        break;
      }
      if (!paramIntent.getBooleanExtra("Stop", false)) {
        break;
      }
      sendPushLog();
      handler.removeCallbacks(this.pushRunnable);
      if (this.alarmManager != null)
      {
        paramIntent = new Intent(getApplicationContext(), PushService.class);
        paramIntent.putExtra("SendLog", true);
        paramIntent = PendingIntent.getService(getApplicationContext(), 32769, paramIntent, 0);
        this.alarmManager.cancel(paramIntent);
      }
      stopSelf();
      return 2;
      label315:
      handler.removeCallbacks(this.pushRunnable);
      l = SystemClock.uptimeMillis();
      handler.postAtTime(this.pushRunnable, l + KeepLiveInterval * 1000);
    }
  }
  
  public void reStartHeartBreak()
  {
    this.alarmManager = ((AlarmManager)getSystemService("alarm"));
    handler.removeCallbacks(this.pushRunnable);
    long l = SystemClock.uptimeMillis();
    handler.postAtTime(this.pushRunnable, l);
  }
  
  public void sendPushLog()
  {
    logCount += 1;
    String str = this.startLogTime;
    this.startLogTime = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date(DateUtil.getCurrentCalendar().getTimeInMillis()));
    SharedPreferences localSharedPreferences = getSharedPreferences(PREFS_NAME, 0);
    long l1 = localSharedPreferences.getLong("2G/3G_up", 0L);
    long l2 = localSharedPreferences.getLong("2G/3G_down", 0L);
    long l3 = localSharedPreferences.getLong("2G_up", 0L);
    long l4 = localSharedPreferences.getLong("2G_down", 0L);
    long l5 = localSharedPreferences.getLong("3G_up", 0L);
    long l6 = localSharedPreferences.getLong("3G_down", 0L);
    long l7 = localSharedPreferences.getLong("WIFI_up", 0L);
    long l8 = localSharedPreferences.getLong("WIFI_down", 0L);
    long l9 = localSharedPreferences.getLong("unkown_up", 0L);
    long l10 = localSharedPreferences.getLong("unkown_down", 0L);
    ctrip.base.logical.util.c.b("P9997", new String[] { getNetworkType() + "|" + "ST:" + str + " ET:" + this.startLogTime + " Bump:" + KeepLiveInterval + "s" + " TBT:" + heartBreakCount + "SBT:" + heartBreakPageCount + "RBT:" + heartBreakRegCount + "RCBT:" + heartBreakReConnectCount + "CRBT:" + heartBreakResetCount + "CCBT:" + heartBreakBeCloseCount + " Network: 2G/3G_up:" + byteToM(l1) + "M 2G/3G_down:" + byteToM(l2) + "M 2G_up:" + byteToM(l3) + "M 2G_down:" + byteToM(l4) + "M 3G_up:" + byteToM(l5) + "M 3G_down:" + byteToM(l6) + "M WIFI_up:" + byteToM(l7) + "M WIFI_down:" + byteToM(l8) + "M unkown_up:" + byteToM(l9) + "M unkown_down:" + byteToM(l10) + "M background_up:" + byteToM(backgroundData) + "M " });
    initNewWorkLog();
    if (logCount % 8 == 0)
    {
      if (background) {
        break label564;
      }
      CtripActionCodeLogUtil.getInstance().sendActionCode(true);
    }
    for (;;)
    {
      heartBreakCount = 0;
      heartBreakRegCount = 0;
      heartBreakPageCount = 0;
      heartBreakReConnectCount = 0;
      heartBreakResetCount = 0;
      heartBreakBeCloseCount = 0;
      checkDexUpdate();
      checkSoUpdate();
      return;
      label564:
      if (backgroundData < 1048576L) {
        CtripActionCodeLogUtil.getInstance().sendActionCode(true);
      }
    }
  }
  
  /* Error */
  public void startHeartBreak()
  {
    // Byte code:
    //   0: getstatic 51	ctrip/android/view/push/PushService:heartBreakCount	I
    //   3: iconst_1
    //   4: iadd
    //   5: putstatic 51	ctrip/android/view/push/PushService:heartBreakCount	I
    //   8: aload_0
    //   9: getstatic 49	ctrip/android/view/push/PushService:PREFS_NAME	Ljava/lang/String;
    //   12: iconst_0
    //   13: invokevirtual 334	ctrip/android/view/push/PushService:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   16: ldc_w 986
    //   19: iconst_1
    //   20: invokeinterface 252 3 0
    //   25: ifeq +309 -> 334
    //   28: aload_0
    //   29: getfield 778	ctrip/android/view/push/PushService:longConnectSocket	Lctrip/android/view/push/LongConnectSocketInterface;
    //   32: ifnonnull +484 -> 516
    //   35: new 988	ctrip/base/a/a/b/b
    //   38: dup
    //   39: aload_0
    //   40: invokevirtual 837	ctrip/android/view/push/PushService:getApplicationContext	()Landroid/content/Context;
    //   43: invokespecial 991	ctrip/base/a/a/b/b:<init>	(Landroid/content/Context;)V
    //   46: astore_3
    //   47: new 149	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   54: aload_0
    //   55: invokevirtual 837	ctrip/android/view/push/PushService:getApplicationContext	()Landroid/content/Context;
    //   58: ldc_w 993
    //   61: iconst_0
    //   62: invokevirtual 999	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   65: invokevirtual 498	java/io/File:getPath	()Ljava/lang/String;
    //   68: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: getstatic 644	java/io/File:separator	Ljava/lang/String;
    //   74: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: ldc_w 1001
    //   80: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: astore 4
    //   88: new 495	java/io/File
    //   91: dup
    //   92: aload 4
    //   94: invokespecial 506	java/io/File:<init>	(Ljava/lang/String;)V
    //   97: astore 5
    //   99: aload 5
    //   101: invokevirtual 649	java/io/File:exists	()Z
    //   104: ifne +9 -> 113
    //   107: aload 5
    //   109: invokevirtual 509	java/io/File:mkdirs	()Z
    //   112: pop
    //   113: aload_3
    //   114: new 149	java/lang/StringBuilder
    //   117: dup
    //   118: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   121: getstatic 738	ctrip/android/view/h5/activity/i:a	Ljava/lang/String;
    //   124: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: getstatic 644	java/io/File:separator	Ljava/lang/String;
    //   130: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: ldc_w 740
    //   136: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: getstatic 644	java/io/File:separator	Ljava/lang/String;
    //   142: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc_w 742
    //   148: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: aload 4
    //   156: invokevirtual 1004	ctrip/base/a/a/b/b:a	(Ljava/lang/String;Ljava/lang/String;)Lctrip/base/a/a/b/a;
    //   159: ldc_w 1006
    //   162: invokevirtual 1012	dalvik/system/DexClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   165: iconst_0
    //   166: anewarray 303	java/lang/Class
    //   169: invokevirtual 1016	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   172: astore_3
    //   173: aload_3
    //   174: iconst_1
    //   175: invokevirtual 1021	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   178: aload_3
    //   179: iconst_0
    //   180: anewarray 317	java/lang/Object
    //   183: invokevirtual 1025	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   186: astore_3
    //   187: aload_3
    //   188: ifnull +10 -> 198
    //   191: aload_3
    //   192: instanceof 780
    //   195: ifne +218 -> 413
    //   198: new 149	java/lang/StringBuilder
    //   201: dup
    //   202: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   205: getstatic 738	ctrip/android/view/h5/activity/i:a	Ljava/lang/String;
    //   208: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: getstatic 644	java/io/File:separator	Ljava/lang/String;
    //   214: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: ldc_w 740
    //   220: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   226: astore_3
    //   227: new 495	java/io/File
    //   230: dup
    //   231: aload_3
    //   232: invokespecial 506	java/io/File:<init>	(Ljava/lang/String;)V
    //   235: astore 4
    //   237: aload 4
    //   239: invokevirtual 649	java/io/File:exists	()Z
    //   242: ifne +9 -> 251
    //   245: aload 4
    //   247: invokevirtual 509	java/io/File:mkdirs	()Z
    //   250: pop
    //   251: new 495	java/io/File
    //   254: dup
    //   255: new 149	java/lang/StringBuilder
    //   258: dup
    //   259: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   262: aload_3
    //   263: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: getstatic 644	java/io/File:separator	Ljava/lang/String;
    //   269: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: ldc_w 742
    //   275: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   281: invokespecial 506	java/io/File:<init>	(Ljava/lang/String;)V
    //   284: astore_3
    //   285: invokestatic 747	ctrip/base/logical/component/CtripBaseApplication:a	()Lctrip/base/logical/component/CtripBaseApplication;
    //   288: invokevirtual 748	ctrip/base/logical/component/CtripBaseApplication:getAssets	()Landroid/content/res/AssetManager;
    //   291: astore 4
    //   293: aload 4
    //   295: new 149	java/lang/StringBuilder
    //   298: dup
    //   299: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   302: ldc_w 740
    //   305: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: getstatic 644	java/io/File:separator	Ljava/lang/String;
    //   311: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   314: ldc_w 742
    //   317: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: invokevirtual 412	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   326: aload_3
    //   327: invokestatic 751	ctrip/android/view/h5/activity/i:a	(Ljava/io/InputStream;Ljava/io/File;)V
    //   330: aload_0
    //   331: invokevirtual 868	ctrip/android/view/push/PushService:stopSelf	()V
    //   334: return
    //   335: astore_3
    //   336: aload_3
    //   337: invokevirtual 1026	java/lang/ClassNotFoundException:printStackTrace	()V
    //   340: aconst_null
    //   341: astore_3
    //   342: goto -155 -> 187
    //   345: astore_3
    //   346: aload_3
    //   347: invokevirtual 1027	java/lang/SecurityException:printStackTrace	()V
    //   350: aconst_null
    //   351: astore_3
    //   352: goto -165 -> 187
    //   355: astore_3
    //   356: aload_3
    //   357: invokevirtual 1028	java/lang/NoSuchMethodException:printStackTrace	()V
    //   360: aconst_null
    //   361: astore_3
    //   362: goto -175 -> 187
    //   365: astore_3
    //   366: aload_3
    //   367: invokevirtual 1029	java/lang/IllegalArgumentException:printStackTrace	()V
    //   370: aconst_null
    //   371: astore_3
    //   372: goto -185 -> 187
    //   375: astore_3
    //   376: aload_3
    //   377: invokevirtual 1030	java/lang/InstantiationException:printStackTrace	()V
    //   380: aconst_null
    //   381: astore_3
    //   382: goto -195 -> 187
    //   385: astore_3
    //   386: aload_3
    //   387: invokevirtual 1031	java/lang/IllegalAccessException:printStackTrace	()V
    //   390: aconst_null
    //   391: astore_3
    //   392: goto -205 -> 187
    //   395: astore_3
    //   396: aload_3
    //   397: invokevirtual 1032	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   400: aconst_null
    //   401: astore_3
    //   402: goto -215 -> 187
    //   405: astore_3
    //   406: aload_3
    //   407: invokevirtual 515	java/io/IOException:printStackTrace	()V
    //   410: goto -80 -> 330
    //   413: aload_0
    //   414: aload_3
    //   415: checkcast 780	ctrip/android/view/push/LongConnectSocketInterface
    //   418: putfield 778	ctrip/android/view/push/PushService:longConnectSocket	Lctrip/android/view/push/LongConnectSocketInterface;
    //   421: aload_0
    //   422: new 871	java/text/SimpleDateFormat
    //   425: dup
    //   426: ldc_w 873
    //   429: invokestatic 879	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   432: invokespecial 882	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   435: new 884	java/util/Date
    //   438: dup
    //   439: invokestatic 890	ctrip/business/util/DateUtil:getCurrentCalendar	()Ljava/util/Calendar;
    //   442: invokevirtual 895	java/util/Calendar:getTimeInMillis	()J
    //   445: invokespecial 898	java/util/Date:<init>	(J)V
    //   448: invokevirtual 902	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   451: putfield 104	ctrip/android/view/push/PushService:startLogTime	Ljava/lang/String;
    //   454: new 582	android/content/Intent
    //   457: dup
    //   458: aload_0
    //   459: invokevirtual 837	ctrip/android/view/push/PushService:getApplicationContext	()Landroid/content/Context;
    //   462: ldc 2
    //   464: invokespecial 840	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   467: astore_3
    //   468: aload_3
    //   469: ldc_w 830
    //   472: iconst_1
    //   473: invokevirtual 843	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   476: pop
    //   477: aload_0
    //   478: invokevirtual 837	ctrip/android/view/push/PushService:getApplicationContext	()Landroid/content/Context;
    //   481: ldc_w 844
    //   484: aload_3
    //   485: iconst_0
    //   486: invokestatic 850	android/app/PendingIntent:getService	(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   489: astore_3
    //   490: aload_0
    //   491: getfield 548	ctrip/android/view/push/PushService:alarmManager	Landroid/app/AlarmManager;
    //   494: aload_3
    //   495: invokevirtual 854	android/app/AlarmManager:cancel	(Landroid/app/PendingIntent;)V
    //   498: invokestatic 857	android/os/SystemClock:elapsedRealtime	()J
    //   501: lstore_1
    //   502: aload_0
    //   503: getfield 548	ctrip/android/view/push/PushService:alarmManager	Landroid/app/AlarmManager;
    //   506: iconst_2
    //   507: lload_1
    //   508: ldc2_w 858
    //   511: ladd
    //   512: aload_3
    //   513: invokevirtual 863	android/app/AlarmManager:set	(IJLandroid/app/PendingIntent;)V
    //   516: aload_0
    //   517: getfield 778	ctrip/android/view/push/PushService:longConnectSocket	Lctrip/android/view/push/LongConnectSocketInterface;
    //   520: ifnull -186 -> 334
    //   523: aload_0
    //   524: getfield 778	ctrip/android/view/push/PushService:longConnectSocket	Lctrip/android/view/push/LongConnectSocketInterface;
    //   527: aload_0
    //   528: invokeinterface 1036 2 0
    //   533: aload_0
    //   534: getfield 778	ctrip/android/view/push/PushService:longConnectSocket	Lctrip/android/view/push/LongConnectSocketInterface;
    //   537: invokeinterface 1037 1 0
    //   542: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	543	0	this	PushService
    //   501	7	1	l	long
    //   46	281	3	localObject1	Object
    //   335	2	3	localClassNotFoundException	ClassNotFoundException
    //   341	1	3	localObject2	Object
    //   345	2	3	localSecurityException	SecurityException
    //   351	1	3	localObject3	Object
    //   355	2	3	localNoSuchMethodException	NoSuchMethodException
    //   361	1	3	localObject4	Object
    //   365	2	3	localIllegalArgumentException	IllegalArgumentException
    //   371	1	3	localObject5	Object
    //   375	2	3	localInstantiationException	InstantiationException
    //   381	1	3	localObject6	Object
    //   385	2	3	localIllegalAccessException	IllegalAccessException
    //   391	1	3	localObject7	Object
    //   395	2	3	localInvocationTargetException	InvocationTargetException
    //   401	1	3	localObject8	Object
    //   405	10	3	localIOException	IOException
    //   467	46	3	localObject9	Object
    //   86	208	4	localObject10	Object
    //   97	11	5	localFile	File
    // Exception table:
    //   from	to	target	type
    //   35	113	335	java/lang/ClassNotFoundException
    //   113	187	335	java/lang/ClassNotFoundException
    //   35	113	345	java/lang/SecurityException
    //   113	187	345	java/lang/SecurityException
    //   35	113	355	java/lang/NoSuchMethodException
    //   113	187	355	java/lang/NoSuchMethodException
    //   35	113	365	java/lang/IllegalArgumentException
    //   113	187	365	java/lang/IllegalArgumentException
    //   35	113	375	java/lang/InstantiationException
    //   113	187	375	java/lang/InstantiationException
    //   35	113	385	java/lang/IllegalAccessException
    //   113	187	385	java/lang/IllegalAccessException
    //   35	113	395	java/lang/reflect/InvocationTargetException
    //   113	187	395	java/lang/reflect/InvocationTargetException
    //   293	330	405	java/io/IOException
  }
}
