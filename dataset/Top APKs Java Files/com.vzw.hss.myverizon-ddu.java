import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.verizon.mips.selfdiagnostic.receiver.NotificationActionReceiver;
import com.verizon.mips.selfdiagnostic.receiver.NotificationAlarmReceiver;
import com.verizon.mips.selfdiagnostic.receiver.PoundDiagOutgoignCallReceiver;
import com.verizon.mips.selfdiagnostic.receiver.SelfLauncherReceiver;
import com.verizon.mips.selfdiagnostic.receiver.SelfStoreResponseReceiver;
import com.verizon.mips.selfdiagnostic.uploadtable.UploadReceiverPluginPlugOut;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONObject;

public class ddu
{
  private static int aPK = 0;
  static PackageManager beB = null;
  private static String beC = "";
  static Long beD = Long.valueOf(0L);
  static boolean beE;
  private static long cacheSize = 0L;
  
  public static long IN()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static long IO()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
  }
  
  @SuppressLint({"NewApi"})
  public static long IP()
  {
    if (externalMemoryAvailable())
    {
      for (;;)
      {
        try
        {
          Object localObject = IR();
          if (((ddy)localObject).getPath() != null)
          {
            ddn.d("getPath " + ((ddy)localObject).getPath());
            localObject = ((ddy)localObject).getPath().split("/");
            if (TextUtils.isEmpty(localObject[(localObject.length - 1)]))
            {
              ddn.d("" + localObject[(localObject.length - 2)]);
              localObject = new StatFs("/storage/" + localObject[(localObject.length - 2)]);
              if (localObject == null) {
                break;
              }
            }
            try
            {
              long l = ((StatFs)localObject).getBlockSize();
              int i = ((StatFs)localObject).getAvailableBlocks();
              return i * l;
            }
            catch (Exception localException5)
            {
              StatFs localStatFs1;
              StatFs localStatFs2;
              StatFs localStatFs3;
              StatFs localStatFs4;
              return 0L;
            }
            ddn.d("" + localObject[(localObject.length - 1)]);
            localObject = new StatFs("/storage/" + localObject[(localObject.length - 1)]);
            continue;
          }
        }
        catch (Exception localException1)
        {
          try
          {
            localStatFs1 = new StatFs("/storage/extSdCard");
            ddn.d("/storage/extSdCard");
          }
          catch (Exception localException2)
          {
            try
            {
              localStatFs2 = new StatFs("/storage/ext_sd");
              ddn.d("/storage/ext_sd/");
            }
            catch (Exception localException3)
            {
              try
              {
                localStatFs3 = new StatFs("/mnt/external_sd");
                ddn.d("/mnt/external_sd/");
              }
              catch (Exception localException4)
              {
                localStatFs4 = new StatFs("/mnt/extSdCard");
                ddn.d("/mnt/extSdCard/");
              }
            }
          }
          continue;
        }
        StatFs localStatFs5 = new StatFs("null");
      }
      return 0L;
    }
    return 0L;
  }
  
  @SuppressLint({"NewApi"})
  public static long IQ()
  {
    if (externalMemoryAvailable())
    {
      for (;;)
      {
        try
        {
          Object localObject = IR();
          if (((ddy)localObject).getPath() != null)
          {
            ddn.d("getPath " + ((ddy)localObject).getPath());
            localObject = ((ddy)localObject).getPath().split("/");
            if (TextUtils.isEmpty(localObject[(localObject.length - 1)]))
            {
              ddn.d("" + localObject[(localObject.length - 2)]);
              localObject = new StatFs("/storage/" + localObject[(localObject.length - 2)]);
              if (localObject == null) {
                break;
              }
            }
            try
            {
              long l = ((StatFs)localObject).getBlockSize();
              int i = ((StatFs)localObject).getBlockCount();
              return i * l;
            }
            catch (Exception localException5)
            {
              StatFs localStatFs1;
              StatFs localStatFs2;
              StatFs localStatFs3;
              StatFs localStatFs4;
              return 0L;
            }
            ddn.d("" + localObject[(localObject.length - 1)]);
            localObject = new StatFs("/storage/" + localObject[(localObject.length - 1)]);
            continue;
          }
        }
        catch (Exception localException1)
        {
          try
          {
            localStatFs1 = new StatFs("/storage/extSdCard");
          }
          catch (Exception localException2)
          {
            try
            {
              localStatFs2 = new StatFs("/storage/ext_sd");
            }
            catch (Exception localException3)
            {
              try
              {
                localStatFs3 = new StatFs("mnt/external_sd");
              }
              catch (Exception localException4)
              {
                localStatFs4 = new StatFs("mnt/extSdCard");
              }
            }
          }
          continue;
        }
        StatFs localStatFs5 = new StatFs("null");
      }
      return 0L;
    }
    return 0L;
  }
  
  /* Error */
  public static ddy IR()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 147	java/util/HashSet
    //   6: dup
    //   7: invokespecial 148	java/util/HashSet:<init>	()V
    //   10: astore 5
    //   12: new 84	ddy
    //   15: dup
    //   16: invokespecial 149	ddy:<init>	()V
    //   19: astore_2
    //   20: iconst_0
    //   21: istore_0
    //   22: new 151	java/io/BufferedReader
    //   25: dup
    //   26: new 153	java/io/FileReader
    //   29: dup
    //   30: ldc -101
    //   32: invokespecial 156	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   35: invokespecial 159	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   38: astore_3
    //   39: aload_3
    //   40: invokevirtual 162	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   43: astore 4
    //   45: aload 4
    //   47: ifnull +180 -> 227
    //   50: aload 4
    //   52: ldc -92
    //   54: invokevirtual 167	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   57: ifne +13 -> 70
    //   60: aload 4
    //   62: ldc -87
    //   64: invokevirtual 167	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   67: ifeq -28 -> 39
    //   70: new 171	java/util/StringTokenizer
    //   73: dup
    //   74: aload 4
    //   76: ldc -83
    //   78: invokespecial 176	java/util/StringTokenizer:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   81: astore 6
    //   83: aload 6
    //   85: invokevirtual 179	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   88: pop
    //   89: aload 6
    //   91: invokevirtual 179	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   94: astore 7
    //   96: aload 5
    //   98: aload 7
    //   100: invokevirtual 182	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   103: ifne -64 -> 39
    //   106: aload 6
    //   108: invokevirtual 179	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   111: pop
    //   112: aload 6
    //   114: invokevirtual 179	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   117: ldc -72
    //   119: invokevirtual 111	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   122: invokestatic 190	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   125: ldc -64
    //   127: invokeinterface 195 2 0
    //   132: istore_1
    //   133: aload 4
    //   135: ldc -59
    //   137: invokevirtual 167	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   140: ifeq +177 -> 317
    //   143: aload 4
    //   145: ldc -57
    //   147: invokevirtual 167	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   150: ifne +167 -> 317
    //   153: aload 4
    //   155: ldc -55
    //   157: invokevirtual 167	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   160: ifne +157 -> 317
    //   163: aload 4
    //   165: ldc -53
    //   167: invokevirtual 167	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   170: ifne +147 -> 317
    //   173: aload 4
    //   175: ldc -51
    //   177: invokevirtual 167	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   180: ifne +137 -> 317
    //   183: aload 4
    //   185: ldc -49
    //   187: invokevirtual 167	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   190: ifne +127 -> 317
    //   193: aload 5
    //   195: aload 7
    //   197: invokevirtual 210	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   200: pop
    //   201: new 84	ddy
    //   204: dup
    //   205: ldc -44
    //   207: aload 7
    //   209: iload_1
    //   210: iconst_1
    //   211: iload_0
    //   212: invokespecial 215	ddy:<init>	(Ljava/lang/String;Ljava/lang/String;ZZI)V
    //   215: astore 4
    //   217: iload_0
    //   218: iconst_1
    //   219: iadd
    //   220: istore_0
    //   221: aload 4
    //   223: astore_2
    //   224: goto -185 -> 39
    //   227: aload_2
    //   228: astore 4
    //   230: aload_3
    //   231: ifnull +10 -> 241
    //   234: aload_3
    //   235: invokevirtual 218	java/io/BufferedReader:close	()V
    //   238: aload_2
    //   239: astore 4
    //   241: aload 4
    //   243: areturn
    //   244: astore_3
    //   245: aload 4
    //   247: astore_3
    //   248: aload_2
    //   249: astore 4
    //   251: aload_3
    //   252: ifnull -11 -> 241
    //   255: aload_3
    //   256: invokevirtual 218	java/io/BufferedReader:close	()V
    //   259: aload_2
    //   260: areturn
    //   261: astore_3
    //   262: aload_2
    //   263: areturn
    //   264: astore_3
    //   265: aconst_null
    //   266: astore_3
    //   267: aload_2
    //   268: astore 4
    //   270: aload_3
    //   271: ifnull -30 -> 241
    //   274: aload_3
    //   275: invokevirtual 218	java/io/BufferedReader:close	()V
    //   278: aload_2
    //   279: areturn
    //   280: astore_3
    //   281: aload_2
    //   282: areturn
    //   283: astore_2
    //   284: aconst_null
    //   285: astore_3
    //   286: aload_3
    //   287: ifnull +7 -> 294
    //   290: aload_3
    //   291: invokevirtual 218	java/io/BufferedReader:close	()V
    //   294: aload_2
    //   295: athrow
    //   296: astore_3
    //   297: aload_2
    //   298: areturn
    //   299: astore_3
    //   300: goto -6 -> 294
    //   303: astore_2
    //   304: goto -18 -> 286
    //   307: astore 4
    //   309: goto -42 -> 267
    //   312: astore 4
    //   314: goto -66 -> 248
    //   317: goto -93 -> 224
    // Local variable table:
    //   start	length	slot	name	signature
    //   21	200	0	i	int
    //   132	78	1	bool	boolean
    //   19	263	2	localObject1	Object
    //   283	15	2	localDdy	ddy
    //   303	1	2	localObject2	Object
    //   38	197	3	localBufferedReader	BufferedReader
    //   244	1	3	localFileNotFoundException1	java.io.FileNotFoundException
    //   247	9	3	localObject3	Object
    //   261	1	3	localIOException1	IOException
    //   264	1	3	localIOException2	IOException
    //   266	9	3	localObject4	Object
    //   280	1	3	localIOException3	IOException
    //   285	6	3	localObject5	Object
    //   296	1	3	localIOException4	IOException
    //   299	1	3	localIOException5	IOException
    //   1	268	4	localObject6	Object
    //   307	1	4	localIOException6	IOException
    //   312	1	4	localFileNotFoundException2	java.io.FileNotFoundException
    //   10	184	5	localHashSet	java.util.HashSet
    //   81	32	6	localStringTokenizer	java.util.StringTokenizer
    //   94	114	7	str	String
    // Exception table:
    //   from	to	target	type
    //   22	39	244	java/io/FileNotFoundException
    //   255	259	261	java/io/IOException
    //   22	39	264	java/io/IOException
    //   274	278	280	java/io/IOException
    //   22	39	283	finally
    //   234	238	296	java/io/IOException
    //   290	294	299	java/io/IOException
    //   39	45	303	finally
    //   50	70	303	finally
    //   70	217	303	finally
    //   39	45	307	java/io/IOException
    //   50	70	307	java/io/IOException
    //   70	217	307	java/io/IOException
    //   39	45	312	java/io/FileNotFoundException
    //   50	70	312	java/io/FileNotFoundException
    //   70	217	312	java/io/FileNotFoundException
  }
  
  public static long Y(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    try
    {
      localCalendar.setTime(new Date(paramLong));
      localCalendar.set(9, 0);
      localCalendar.set(10, 0);
      localCalendar.set(12, 0);
      localCalendar.set(13, 0);
      localCalendar.set(14, 0);
      paramLong = localCalendar.getTimeInMillis();
      return paramLong;
    }
    catch (Exception localException)
    {
      ddn.e("Exception in getTodayStartTime " + localException.getMessage());
    }
    return -2L;
  }
  
  public static <T> T a(String paramString, Class<T> paramClass)
  {
    try
    {
      paramString = new Gson().fromJson(paramString, paramClass);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo != null) && ((paramApplicationInfo.flags & 0x1) != 0);
  }
  
  public static boolean a(ctl paramCtl)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    if (paramCtl != null)
    {
      paramCtl = paramCtl.Fy();
      int i = 0;
      bool1 = bool2;
      while (i < paramCtl.size())
      {
        bool1 = j((File)paramCtl.get(i));
        i += 1;
      }
    }
    return bool1;
  }
  
  public static boolean b(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo != null) && ((paramApplicationInfo.flags & 0x80) != 0);
  }
  
  public static ded bA(Context paramContext)
  {
    return new ded(paramContext);
  }
  
  public static boolean bB(Context paramContext)
  {
    return bA(paramContext).IX();
  }
  
  private static String bC(int paramInt1, int paramInt2)
  {
    return paramInt1 * 100 / paramInt2 + "%";
  }
  
  public static JSONObject bC(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    bA(paramContext).a(false, new ddw(localJSONArray, localJSONObject));
    return localJSONObject;
  }
  
  public static boolean bD(Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      localPackageManager.setComponentEnabledSetting(new ComponentName(paramContext, SelfLauncherReceiver.class), 2, 1);
      ddn.d("SelfLauncherReceiver is disabled");
      localPackageManager.setComponentEnabledSetting(new ComponentName(paramContext, NotificationAlarmReceiver.class), 2, 1);
      ddn.d("NotificationAlarmReceiver is disabled");
      localPackageManager.setComponentEnabledSetting(new ComponentName(paramContext, NotificationActionReceiver.class), 2, 1);
      ddn.d("NotificationActionReceiver is disabled");
      localPackageManager.setComponentEnabledSetting(new ComponentName(paramContext, PoundDiagOutgoignCallReceiver.class), 2, 1);
      localPackageManager.setComponentEnabledSetting(new ComponentName(paramContext, UploadReceiverPluginPlugOut.class), 2, 1);
      ddn.d("PoundDiagOutgoignCallReceiver is disabled");
      localPackageManager.setComponentEnabledSetting(new ComponentName(paramContext, SelfStoreResponseReceiver.class), 2, 1);
      return true;
    }
    catch (Exception paramContext)
    {
      ddn.e("Exception " + paramContext.getMessage());
    }
    return false;
  }
  
  public static int bE(Context paramContext)
  {
    try
    {
      int i = Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on");
      return i;
    }
    catch (Settings.SettingNotFoundException paramContext)
    {
      ddn.e(paramContext.getMessage());
    }
    return 0;
  }
  
  @SuppressLint({"NewApi"})
  public static int bF(Context paramContext)
  {
    int i;
    if (Build.VERSION.SDK_INT >= 17) {
      try
      {
        i = Settings.Global.getInt(paramContext.getContentResolver(), "preferred_network_mode");
        ddn.d("RDD", "preferred_network_mode : " + i);
        return i;
      }
      catch (NoClassDefFoundError localNoClassDefFoundError)
      {
        try
        {
          i = Settings.Secure.getInt(paramContext.getContentResolver(), "preferred_network_mode");
          ddn.d("RDD", "preferred_network_mode : " + i);
          return i;
        }
        catch (Settings.SettingNotFoundException paramContext)
        {
          ddn.d(paramContext.getMessage());
          return 0;
        }
      }
      catch (Exception paramContext)
      {
        return 0;
      }
    }
    try
    {
      i = Settings.Secure.getInt(paramContext.getContentResolver(), "preferred_network_mode");
      ddn.d("RDD", "preferred_network_mode : " + i);
      return i;
    }
    catch (Settings.SettingNotFoundException paramContext)
    {
      ddn.d(paramContext.getMessage());
    }
    return 0;
  }
  
  public static int bG(Context paramContext)
  {
    switch (((TelephonyManager)paramContext.getSystemService("phone")).getSimState())
    {
    default: 
      return 0;
    case 5: 
      return 0;
    case 1: 
      return 1;
    case 4: 
      return 2;
    case 2: 
      return 2;
    case 3: 
      return 2;
    }
    return 3;
  }
  
  public static boolean bH(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      if (paramContext != null)
      {
        int i = paramContext.length();
        if (i > 0) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean bI(Context paramContext)
  {
    return ((WifiManager)paramContext.getSystemService("wifi")).isWifiEnabled();
  }
  
  public static ctl bJ(Context paramContext)
  {
    long l1 = 0L;
    ArrayList localArrayList = new ArrayList();
    ctl localCtl = new ctl();
    File[] arrayOfFile = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "Android" + File.separator + "data").listFiles();
    long l2 = l1;
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      int i = 0;
      for (;;)
      {
        l2 = l1;
        if (i >= j) {
          break;
        }
        File localFile = arrayOfFile[i];
        l2 = l1;
        if (c(localFile.getName(), paramContext).equals("Unknown"))
        {
          StatFs localStatFs = new StatFs(localFile.getPath());
          l2 = localStatFs.getBlockSize();
          l2 = localStatFs.getBlockCount();
          l2 = l1 + localFile.length();
          localArrayList.add(localFile);
        }
        i += 1;
        l1 = l2;
      }
    }
    ddn.d("getResidualFileDetails totalSize " + l2);
    localCtl.R(l2);
    localCtl.d(localArrayList);
    return localCtl;
  }
  
  public static long bK(Context paramContext)
  {
    long l1 = 0L;
    try
    {
      long l2 = paramContext.getDatabasePath("/data/data/com.android.providers.telephony/databases/mmssms.db").length();
      l1 = l2;
      ddn.d("getSMSDbSize " + l2);
      return l2;
    }
    catch (Exception paramContext) {}
    return l1;
  }
  
  public static ctl bL(Context paramContext)
  {
    paramContext = new ArrayList();
    ctl localCtl = new ctl();
    File[] arrayOfFile = new File(Environment.getExternalStorageDirectory().getPath()).listFiles();
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      long l1 = 0L;
      int i = 0;
      while (i < j)
      {
        File localFile = arrayOfFile[i];
        long l2 = l1;
        if (localFile.isHidden())
        {
          l2 = l1 + localFile.length();
          paramContext.add(localFile);
        }
        i += 1;
        l1 = l2;
      }
      ddn.d("getAdvstisementFileDetails totalSize " + l1);
      localCtl.R(l1);
      localCtl.d(paramContext);
    }
    return localCtl;
  }
  
  public static ctl bM(Context paramContext)
  {
    paramContext = new ArrayList();
    ctl localCtl = new ctl();
    long l1 = 0L;
    File[] arrayOfFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).listFiles();
    long l2 = l1;
    if (arrayOfFile != null)
    {
      int i = 0;
      for (;;)
      {
        l2 = l1;
        if (i >= arrayOfFile.length) {
          break;
        }
        l2 = l1;
        if (arrayOfFile[i].isHidden())
        {
          l2 = l1 + arrayOfFile[i].length();
          paramContext.add(arrayOfFile[i]);
        }
        i += 1;
        l1 = l2;
      }
    }
    ddn.d("getThumbnailFileSize totalSize " + l2);
    localCtl.R(l2);
    localCtl.d(paramContext);
    return localCtl;
  }
  
  public static boolean bN(Context paramContext)
  {
    long l = System.currentTimeMillis();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = localPackageManager.getInstalledPackages(0);
    if (dds.IA() != null)
    {
      dds.IA().X(0L);
      if (dds.IA().FO() != null) {
        dds.IA().FO().clear();
      }
    }
    cacheSize = 0L;
    beE = true;
    aPK = 0;
    for (;;)
    {
      try
      {
        localMethod = localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
        Iterator localIterator = paramContext.iterator();
        if (localIterator.hasNext())
        {
          localObject1 = (PackageInfo)localIterator.next();
          if (((PackageInfo)localObject1).packageName != null)
          {
            paramContext = ((PackageInfo)localObject1).packageName;
            if (paramContext.equalsIgnoreCase("NOT RETRIEVABLE")) {
              continue;
            }
            aPK += 1;
            beE = false;
            localCtm = new ctm();
          }
        }
      }
      catch (NoSuchMethodException paramContext)
      {
        try
        {
          Method localMethod;
          ctm localCtm;
          if (!paramContext.equalsIgnoreCase("com.android.keyguard"))
          {
            if ((((PackageInfo)localObject1).applicationInfo == null) || (((PackageInfo)localObject1).applicationInfo.loadLabel(localPackageManager) == null) || (((PackageInfo)localObject1).applicationInfo.loadLabel(localPackageManager).length() <= 0)) {
              break label412;
            }
            localObject1 = ((PackageInfo)localObject1).applicationInfo.loadLabel(localPackageManager).toString();
            localCtm.cm((String)localObject1);
            localCtm.ck(paramContext);
            localMethod.invoke(localPackageManager, new Object[] { paramContext, new ddx(localCtm) });
            Thread.sleep(5L);
            continue;
            paramContext = paramContext;
            ddn.d(paramContext.getMessage());
            return true;
            paramContext = "NOT RETRIEVABLE";
            continue;
          }
          Object localObject1 = paramContext;
          continue;
        }
        catch (Exception localException)
        {
          localObject2 = paramContext;
          continue;
        }
        if ((System.currentTimeMillis() - l < 15000L) && ((aPK > 0) || (beE)))
        {
          ddn.d("getTotalCachesize inside while counter " + aPK);
          Thread.sleep(200L);
          continue;
        }
      }
      catch (Exception paramContext)
      {
        ddn.e("Exception : getApplicationJSONArray : " + paramContext.getMessage());
        return true;
      }
      ddn.d("cacheSize " + cacheSize);
      return true;
      label412:
      Object localObject2 = "NOT RETRIEVABLE";
    }
  }
  
  /* Error */
  @SuppressLint({"NewApi"})
  public static boolean bm(Context paramContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: ldc_w 627
    //   6: invokevirtual 413	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   9: checkcast 629	android/location/LocationManager
    //   12: astore 5
    //   14: aload 5
    //   16: ifnull +165 -> 181
    //   19: aload 5
    //   21: ldc_w 631
    //   24: invokevirtual 634	android/location/LocationManager:isProviderEnabled	(Ljava/lang/String;)Z
    //   27: istore 4
    //   29: new 87	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   36: ldc_w 636
    //   39: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: iload 4
    //   44: invokevirtual 639	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   47: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokestatic 103	ddn:d	(Ljava/lang/String;)V
    //   53: iload 4
    //   55: ifne +236 -> 291
    //   58: iload 4
    //   60: istore_2
    //   61: iload 4
    //   63: istore_3
    //   64: aload_0
    //   65: invokevirtual 375	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   68: ldc_w 641
    //   71: invokestatic 406	android/provider/Settings$Secure:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   74: istore_1
    //   75: iload 4
    //   77: istore_2
    //   78: iload 4
    //   80: istore_3
    //   81: new 87	java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   88: ldc_w 643
    //   91: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: iload_1
    //   95: invokevirtual 310	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   98: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: invokestatic 103	ddn:d	(Ljava/lang/String;)V
    //   104: iload_1
    //   105: ifle +5 -> 110
    //   108: iconst_1
    //   109: ireturn
    //   110: iload 4
    //   112: istore_2
    //   113: iload 4
    //   115: istore_3
    //   116: aload_0
    //   117: invokevirtual 375	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   120: ldc_w 631
    //   123: invokestatic 647	android/provider/Settings$Secure:isLocationProviderEnabled	(Landroid/content/ContentResolver;Ljava/lang/String;)Z
    //   126: istore 4
    //   128: iload 4
    //   130: istore_2
    //   131: iload 4
    //   133: istore_3
    //   134: new 87	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   141: ldc_w 649
    //   144: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: iload 4
    //   149: invokevirtual 639	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   152: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   155: invokestatic 103	ddn:d	(Ljava/lang/String;)V
    //   158: iload 4
    //   160: ireturn
    //   161: astore_0
    //   162: aload_0
    //   163: invokevirtual 650	java/lang/Error:getMessage	()Ljava/lang/String;
    //   166: invokestatic 252	ddn:e	(Ljava/lang/String;)V
    //   169: iload_2
    //   170: ireturn
    //   171: astore_0
    //   172: aload_0
    //   173: invokevirtual 249	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   176: invokestatic 252	ddn:e	(Ljava/lang/String;)V
    //   179: iload_3
    //   180: ireturn
    //   181: aload_0
    //   182: invokevirtual 375	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   185: ldc_w 641
    //   188: invokestatic 406	android/provider/Settings$Secure:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   191: istore_1
    //   192: new 87	java/lang/StringBuilder
    //   195: dup
    //   196: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   199: ldc_w 652
    //   202: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: iload_1
    //   206: invokevirtual 310	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   209: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   212: invokestatic 103	ddn:d	(Ljava/lang/String;)V
    //   215: iload_1
    //   216: ifle +6 -> 222
    //   219: goto +75 -> 294
    //   222: aload_0
    //   223: invokevirtual 375	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   226: ldc_w 631
    //   229: invokestatic 647	android/provider/Settings$Secure:isLocationProviderEnabled	(Landroid/content/ContentResolver;Ljava/lang/String;)Z
    //   232: istore_2
    //   233: new 87	java/lang/StringBuilder
    //   236: dup
    //   237: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   240: ldc_w 654
    //   243: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: iload_2
    //   247: invokevirtual 639	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   250: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   253: invokestatic 103	ddn:d	(Ljava/lang/String;)V
    //   256: goto +38 -> 294
    //   259: astore_0
    //   260: aload_0
    //   261: invokevirtual 650	java/lang/Error:getMessage	()Ljava/lang/String;
    //   264: invokestatic 252	ddn:e	(Ljava/lang/String;)V
    //   267: iload_2
    //   268: ireturn
    //   269: astore_0
    //   270: iconst_0
    //   271: istore_2
    //   272: aload_0
    //   273: invokevirtual 249	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   276: invokestatic 252	ddn:e	(Ljava/lang/String;)V
    //   279: iload_2
    //   280: ireturn
    //   281: astore_0
    //   282: goto -10 -> 272
    //   285: astore_0
    //   286: iconst_0
    //   287: istore_2
    //   288: goto -28 -> 260
    //   291: iload 4
    //   293: ireturn
    //   294: iload_2
    //   295: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	296	0	paramContext	Context
    //   74	142	1	i	int
    //   1	294	2	bool1	boolean
    //   63	117	3	bool2	boolean
    //   27	265	4	bool3	boolean
    //   12	8	5	localLocationManager	android.location.LocationManager
    // Exception table:
    //   from	to	target	type
    //   64	75	161	java/lang/Error
    //   81	104	161	java/lang/Error
    //   116	128	161	java/lang/Error
    //   134	158	161	java/lang/Error
    //   64	75	171	java/lang/Exception
    //   81	104	171	java/lang/Exception
    //   116	128	171	java/lang/Exception
    //   134	158	171	java/lang/Exception
    //   233	256	259	java/lang/Error
    //   181	215	269	java/lang/Exception
    //   222	233	269	java/lang/Exception
    //   233	256	281	java/lang/Exception
    //   181	215	285	java/lang/Error
    //   222	233	285	java/lang/Error
  }
  
  @SuppressLint({"NewApi"})
  public static boolean bn(Context paramContext)
  {
    for (boolean bool1 = true;; bool1 = false) {
      try
      {
        if (Build.VERSION.SDK_INT < 17)
        {
          ddn.d("Roaming value Settings.Secure.DATA_ROAMING " + Settings.Secure.getInt(paramContext.getContentResolver(), "data_roaming"));
          if (Settings.Secure.getInt(paramContext.getContentResolver(), "data_roaming") != 1) {}
        }
        else
        {
          boolean bool2;
          for (;;)
          {
            bool2 = bool1;
            if (!bool1) {
              break;
            }
            bool2 = bool1;
            if (!getManufacturer().equalsIgnoreCase("motorola")) {
              break;
            }
            return bo(paramContext);
            ddn.d("Roaming value Settings.Global.DATA_ROAMING " + Settings.Secure.getInt(paramContext.getContentResolver(), "data_roaming"));
            int i = Settings.Global.getInt(paramContext.getContentResolver(), "data_roaming");
            if (i != 1) {
              bool1 = false;
            }
          }
          return bool2;
        }
      }
      catch (Settings.SettingNotFoundException paramContext)
      {
        bool2 = false;
      }
    }
  }
  
  /* Error */
  public static boolean bo(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: iconst_0
    //   4: istore_2
    //   5: getstatic 392	android/os/Build$VERSION:SDK_INT	I
    //   8: bipush 17
    //   10: if_icmplt +112 -> 122
    //   13: ldc_w 671
    //   16: invokestatic 675	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   19: astore_3
    //   20: ldc_w 677
    //   23: invokestatic 675	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   26: astore 5
    //   28: aload 5
    //   30: astore 4
    //   32: aload 4
    //   34: astore 5
    //   36: aload 4
    //   38: ifnonnull +10 -> 48
    //   41: aload_3
    //   42: ifnull +78 -> 120
    //   45: aload_3
    //   46: astore 5
    //   48: aload 5
    //   50: ldc_w 678
    //   53: iconst_2
    //   54: anewarray 540	java/lang/Class
    //   57: dup
    //   58: iconst_0
    //   59: ldc_w 680
    //   62: aastore
    //   63: dup
    //   64: iconst_1
    //   65: ldc 107
    //   67: aastore
    //   68: invokevirtual 683	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   71: aconst_null
    //   72: iconst_2
    //   73: anewarray 4	java/lang/Object
    //   76: dup
    //   77: iconst_0
    //   78: aload_0
    //   79: invokevirtual 375	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   82: aastore
    //   83: dup
    //   84: iconst_1
    //   85: aload_3
    //   86: ldc_w 685
    //   89: invokevirtual 689	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   92: aconst_null
    //   93: invokevirtual 694	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   96: checkcast 107	java/lang/String
    //   99: aastore
    //   100: invokevirtual 604	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   103: checkcast 696	java/lang/Integer
    //   106: checkcast 696	java/lang/Integer
    //   109: invokevirtual 699	java/lang/Integer:intValue	()I
    //   112: istore_1
    //   113: iload_1
    //   114: iconst_1
    //   115: if_icmpne +23 -> 138
    //   118: iconst_1
    //   119: istore_2
    //   120: iload_2
    //   121: ireturn
    //   122: ldc_w 701
    //   125: invokestatic 675	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   128: astore_3
    //   129: goto -109 -> 20
    //   132: astore_3
    //   133: aconst_null
    //   134: astore_3
    //   135: goto -115 -> 20
    //   138: iconst_0
    //   139: istore_2
    //   140: goto -20 -> 120
    //   143: astore_0
    //   144: aload_0
    //   145: invokevirtual 704	java/lang/Exception:printStackTrace	()V
    //   148: iconst_0
    //   149: ireturn
    //   150: astore 5
    //   152: goto -120 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	155	0	paramContext	Context
    //   112	4	1	i	int
    //   4	136	2	bool	boolean
    //   19	110	3	localClass	Class
    //   132	1	3	localException1	Exception
    //   134	1	3	localObject1	Object
    //   1	36	4	localObject2	Object
    //   26	23	5	localObject3	Object
    //   150	1	5	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   5	20	132	java/lang/Exception
    //   122	129	132	java/lang/Exception
    //   48	113	143	java/lang/Exception
    //   20	28	150	java/lang/Exception
  }
  
  public static boolean bp(Context paramContext)
  {
    try
    {
      paramContext = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("su -c ls").getErrorStream()));
      char[] arrayOfChar = new char["permission denied".length()];
      if (paramContext.read(arrayOfChar) == arrayOfChar.length)
      {
        paramContext = new String(arrayOfChar, 0, arrayOfChar.length);
        if (paramContext != null)
        {
          boolean bool = paramContext.trim().equalsIgnoreCase("permission denied");
          if (bool) {
            return false;
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    return true;
  }
  
  public static boolean bq(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1);
      if ((i == 1) || (i == 2)) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static HashMap<String, String> br(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("ISCHARGING", String.valueOf(bq(paramContext)));
    paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    if (paramContext != null) {}
    try
    {
      localHashMap.put("CHARGINGSOURCE", jx(paramContext.getIntExtra("plugged", 0)));
      localHashMap.put("TEMPERATURE", jw(paramContext.getIntExtra("temperature", 0)));
      localHashMap.put("HEALTH", jv(paramContext.getIntExtra("health", 1)));
      localHashMap.put("VOLTAGE", ju(paramContext.getIntExtra("voltage", 0)));
      localHashMap.put("TECHNOLOGY", cK(paramContext.getStringExtra("technology")));
      localHashMap.put("PERCENTAGE", bC(paramContext.getIntExtra("level", 0), paramContext.getIntExtra("scale", 100)));
      return localHashMap;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  @SuppressLint({"ServiceCast"})
  public static boolean bs(Context paramContext)
  {
    boolean bool;
    try
    {
      paramContext = ((WallpaperManager)paramContext.getSystemService("wallpaper")).getWallpaperInfo();
      if (paramContext != null) {
        bool = true;
      }
    }
    catch (Exception paramContext)
    {
      bool = false;
    }
    try
    {
      ddn.d("Live Wall Paper enabled");
      return true;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    ddn.d("Live Wall Paper not enabled");
    return false;
    ddn.e("Exception in getLiveWallPaperStatus : " + paramContext.getMessage());
    return bool;
  }
  
  public static int bt(Context paramContext)
  {
    if (bu(paramContext) == 1) {
      return -1;
    }
    try
    {
      int i = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness");
      return i;
    }
    catch (Exception paramContext)
    {
      ddn.e("Exception in getBrightness " + paramContext.getMessage());
    }
    return 0;
  }
  
  public static int bu(Context paramContext)
  {
    try
    {
      int i = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness_mode");
      return i;
    }
    catch (Exception paramContext)
    {
      ddn.e("Exception in getBrightness " + paramContext.getMessage());
    }
    return -1;
  }
  
  public static int bv(Context paramContext)
  {
    int i;
    try
    {
      i = Settings.System.getInt(paramContext.getContentResolver(), "screen_off_timeout");
      if (i == Integer.MAX_VALUE) {
        return -1;
      }
    }
    catch (Exception paramContext)
    {
      ddn.e("Exception in getScreenTimeOut " + paramContext.getMessage());
      return -1;
    }
    return i;
  }
  
  public static int bw(Context paramContext)
  {
    try
    {
      if (Build.VERSION.SDK_INT < 17) {
        return Settings.System.getInt(paramContext.getContentResolver(), "auto_time");
      }
      int i = Settings.Global.getInt(paramContext.getContentResolver(), "auto_time");
      return i;
    }
    catch (Settings.SettingNotFoundException paramContext)
    {
      ddn.e("Exception in getNetworkTimeStatus " + paramContext.getMessage());
    }
    return -1;
  }
  
  @SuppressLint({"NewApi"})
  public static ArrayList<ctm> bx(Context paramContext)
  {
    if (beB == null) {
      beB = paramContext.getPackageManager();
    }
    ArrayList localArrayList = new ArrayList();
    try
    {
      Method localMethod = beB.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
        while (localIterator.hasNext())
        {
          ctm localCtm = new ctm();
          Object localObject3 = (PackageInfo)localIterator.next();
          if (((PackageInfo)localObject3).packageName == null) {
            break label321;
          }
          str1 = ((PackageInfo)localObject3).packageName;
          String str2 = c(str1, paramContext);
          if (((str2.equals("Sideloaded")) || (str2.equals("Unknown"))) && (((PackageInfo)localObject3).applicationInfo.loadLabel(localPackageManager) != null) && (((PackageInfo)localObject3).applicationInfo.loadLabel(localPackageManager).length() > 0))
          {
            str2 = ((PackageInfo)localObject3).applicationInfo.loadLabel(localPackageManager).toString();
            if (((PackageInfo)localObject3).versionName != null) {
              localObject3 = ((PackageInfo)localObject3).versionName;
            }
            localCtm.cm(str2);
            localCtm.ck(str1);
            localObject3 = new CountDownLatch(1);
            localMethod.invoke(localPackageManager, new Object[] { str1, new ddv(localCtm, localArrayList, (CountDownLatch)localObject3) });
            ((CountDownLatch)localObject3).await();
          }
        }
        return localArrayList;
      }
      catch (Exception paramContext)
      {
        ddn.e("Exception : getApplicationJSONArray : " + paramContext.getMessage());
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        ddn.d(localNoSuchMethodException.getMessage());
        Object localObject1 = null;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ddn.d(localException.getMessage());
        Object localObject2 = null;
        continue;
        label321:
        String str1 = "NOT RETRIEVABLE";
      }
    }
  }
  
  public static boolean by(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    try
    {
      localMethod = Class.forName(localConnectivityManager.getClass().getName()).getDeclaredMethod("getMobileDataEnabled", new Class[0]);
      localMethod.setAccessible(true);
      bool = ((Boolean)localMethod.invoke(localConnectivityManager, new Object[0])).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      try
      {
        boolean bool;
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        Method localMethod = paramContext.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
        if (localMethod != null)
        {
          bool = ((Boolean)localMethod.invoke(paramContext, new Object[0])).booleanValue();
          return bool;
        }
        return false;
      }
      catch (Exception paramContext)
      {
        ddn.d("Exception in getting MobileData: " + localException.getMessage());
      }
    }
    return false;
  }
  
  public static boolean bz(Context paramContext)
  {
    boolean bool2 = false;
    ddn.d("getWifiApState : " + bA(paramContext).IW().toString());
    boolean bool1 = bool2;
    if (bA(paramContext).IX())
    {
      paramContext = bA(paramContext).IY().allowedKeyManagement.toString().replace("{", "").replace("}", "");
      ddn.d("allowedKeyManagement : " + android.net.wifi.WifiConfiguration.KeyMgmt.strings[Integer.valueOf(paramContext).intValue()]);
      bool1 = bool2;
      if (android.net.wifi.WifiConfiguration.KeyMgmt.strings[Integer.valueOf(paramContext).intValue()].equalsIgnoreCase("NONE")) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static String c(String paramString, Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    ddn.d("SELF", "getSource packageName " + paramString);
    try
    {
      localObject = ((PackageManager)localObject).getApplicationInfo(paramString, 0);
      if (a((ApplicationInfo)localObject)) {
        return "Preloaded";
      }
      if (b((ApplicationInfo)localObject)) {
        return "Preloaded Update";
      }
      if (g(paramString, paramContext)) {
        return "Google Play Store";
      }
      if (f(paramString, paramContext)) {
        return "Amazon AppStore";
      }
      if (e(paramString, paramContext)) {
        return "Samsung AppStore";
      }
      if (d(paramString, paramContext)) {
        return "DT AppStore";
      }
      return "Sideloaded";
    }
    catch (Exception paramString)
    {
      ddn.d("Exception, set source to Unknown");
    }
    return "Unknown";
  }
  
  private static String cK(String paramString)
  {
    if (paramString == null) {
      return "NOT_RETRIEVABLE";
    }
    return paramString.toUpperCase();
  }
  
  public static boolean d(Context paramContext, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 1) {}
    try
    {
      Settings.System.putInt(paramContext.getContentResolver(), "screen_brightness_mode", 1);
      return true;
    }
    catch (Exception paramContext)
    {
      ddn.e("Exception in setScreenBrightness " + paramContext.getMessage());
    }
    Settings.System.putInt(paramContext.getContentResolver(), "screen_brightness_mode", 0);
    Settings.System.putInt(paramContext.getContentResolver(), "screen_brightness", paramInt2);
    return true;
    return false;
  }
  
  public static boolean d(Context paramContext, boolean paramBoolean)
  {
    boolean bool3 = true;
    if (paramBoolean) {}
    label151:
    for (;;)
    {
      try
      {
        Thread.sleep(1000L);
        boolean bool2;
        boolean bool1;
        if (Runtime.getRuntime().exec("ping -c 1 www.google.com").waitFor() == 0)
        {
          bool2 = true;
          bool1 = bool2;
          if (!paramBoolean) {
            break label151;
          }
          bool1 = bool2;
          if (bool2) {
            break label151;
          }
          Thread.sleep(2000L);
          if (Runtime.getRuntime().exec("ping -c 1 www.google.com").waitFor() == 0)
          {
            paramBoolean = true;
            bool1 = paramBoolean;
            if (paramBoolean) {
              break label151;
            }
            bool1 = paramBoolean;
            if (!isNetworkConnected(paramContext)) {
              break label151;
            }
            Thread.sleep(4000L);
            if (Runtime.getRuntime().exec("ping -c 1 www.google.com").waitFor() != 0) {
              continue;
            }
            paramBoolean = bool3;
            dck.bz(paramBoolean);
            ddn.d("reachable " + paramBoolean);
            return paramBoolean;
          }
        }
        else
        {
          bool2 = false;
          continue;
        }
        paramBoolean = false;
        continue;
        paramBoolean = false;
        continue;
        paramBoolean = bool1;
      }
      catch (Exception paramContext)
      {
        return false;
      }
    }
  }
  
  public static boolean d(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstallerPackageName(paramString);
    ddn.d("SELF", "pkgName " + paramString + " isDTStore ipn " + paramContext);
    return "com.LogiaGroup.LogiaDeck".equalsIgnoreCase(paramContext);
  }
  
  public static boolean e(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstallerPackageName(paramString);
    ddn.d("SELF", "pkgName " + paramString + " isSamsungStore ipn " + paramContext);
    if (paramContext != null)
    {
      if (!"com.sec.android.app.samsungapps".equalsIgnoreCase(paramContext)) {}
    }
    else {
      while ((paramString.contains("com.sec.")) || (paramString.contains("com.samsung."))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean externalMemoryAvailable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static void f(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (new File("/data/data/" + paramContext.getApplicationContext().getPackageName(), paramString1).exists())
      {
        ddn.d("fileNames[i] exists " + paramString1);
        new File("/data/data/" + paramContext.getApplicationContext().getPackageName(), paramString1).delete();
        if (!new File("/data/data/" + paramContext.getApplicationContext().getPackageName(), paramString1).exists()) {
          break label221;
        }
        ddn.d("fileName exists after delete " + paramString1);
      }
      for (;;)
      {
        label221:
        try
        {
          paramContext = new FileWriter("/data/data/" + paramContext.getApplicationContext().getPackageName() + "/" + paramString1);
          paramContext.write(paramString2);
          paramContext.flush();
          paramContext.close();
          return;
        }
        catch (Exception paramContext) {}
        ddn.d("fileName not exists after delete " + paramString1);
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
  }
  
  public static boolean f(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstallerPackageName(paramString);
    ddn.d("SELF", "pkgName " + paramString + " isAmazonStore ipn " + paramContext);
    return ("com.amazon.venezia".equalsIgnoreCase(paramContext)) || ("com.amazon.mShop.android".equalsIgnoreCase(paramContext));
  }
  
  public static String formatSize(long paramLong)
  {
    double d = paramLong;
    if (d / 1.073741824E9D >= 1.0D)
    {
      beC = String.format("%.2f", new Object[] { Double.valueOf(d / 1.073741824E9D) });
      beC += " GB";
    }
    for (;;)
    {
      return beC;
      if (d / 1048576.0D >= 1.0D)
      {
        beC = String.format("%.2f", new Object[] { Double.valueOf(d / 1048576.0D) });
        beC += " MB";
      }
      else if (d / 1024.0D >= 1.0D)
      {
        beC = String.format("%.2f", new Object[] { Double.valueOf(d / 1024.0D) });
        beC += " KB";
      }
      else
      {
        beC = String.format("%.2f", new Object[] { Double.valueOf(d) });
        beC += " B";
      }
    }
  }
  
  public static boolean g(String paramString, Context paramContext)
  {
    paramString = paramContext.getPackageManager().getInstallerPackageName(paramString);
    return ("com.android.vending".equalsIgnoreCase(paramString)) || ("com.google.android.feedback".equalsIgnoreCase(paramString));
  }
  
  public static String getManufacturer()
  {
    String str = Build.MANUFACTURER;
    ddn.d("manufacturer " + Build.MANUFACTURER);
    if (str != null) {
      return str;
    }
    return "";
  }
  
  public static boolean isNetworkConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean isRemovableStoragePresent()
  {
    ddy localDdy = IR();
    return (localDdy.name != null) && (!localDdy.name.equals("null"));
  }
  
  public static boolean isWifiConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1);
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  private static boolean j(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        j(arrayOfFile[i]);
        i += 1;
      }
    }
    return paramFile.delete();
  }
  
  private static String ju(int paramInt)
  {
    int i;
    if (paramInt > 1000000) {
      i = paramInt / 1000;
    }
    for (;;)
    {
      return i + " mV";
      i = paramInt;
      if (paramInt < 10) {
        i = paramInt * 1000;
      }
    }
  }
  
  private static String jv(int paramInt)
  {
    switch (paramInt)
    {
    case 6: 
    default: 
      return "NOT_RETRIEVABLE";
    case 7: 
      return "COLD";
    case 4: 
      return "DEAD";
    case 2: 
      return "GOOD";
    case 3: 
      return "OVERHEAT";
    }
    return "OVER VOLTAGE";
  }
  
  private static String jw(int paramInt)
  {
    paramInt = (int)(paramInt / 10.0D);
    int i = (int)(paramInt * 1.8D + 32.0D);
    return i + " F" + " / " + paramInt + " C";
  }
  
  private static String jx(int paramInt)
  {
    switch (paramInt)
    {
    case 3: 
    default: 
      return "NOT PLUGGED";
    case 0: 
      return "NOT PLUGGED";
    case 1: 
      return "AC";
    case 2: 
      return "USB";
    }
    return "WIRELESS";
  }
  
  public static long jy(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(5, -paramInt);
    localCalendar.set(9, 0);
    localCalendar.set(10, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    return Y(localCalendar.getTimeInMillis());
  }
  
  public static boolean s(Context paramContext, int paramInt)
  {
    try
    {
      boolean bool = Settings.System.putInt(paramContext.getContentResolver(), "screen_off_timeout", paramInt);
      return bool;
    }
    catch (Exception paramContext)
    {
      ddn.e("Exception : setScreenTimeout " + paramContext.getMessage());
    }
    return false;
  }
  
  @TargetApi(9)
  public static boolean t(Context paramContext, String paramString)
  {
    try
    {
      Object localObject = paramContext.getPackageManager();
      paramContext = ((PackageManager)localObject).getPackageInfo(paramString, 0);
      localObject = ((PackageManager)localObject).getApplicationInfo(paramString, 0);
      ddn.d("packageName " + paramString + " packageInfo.installLocation " + paramContext.installLocation);
      if ((((ApplicationInfo)localObject).sourceDir.startsWith("/data/")) && ((paramContext.installLocation == 0) || (paramContext.installLocation == 2)))
      {
        ddn.d("Move to sd card is supported");
        return true;
      }
    }
    catch (Exception paramContext)
    {
      ddn.e("Exception : getApplicationJSONArray : " + paramContext.getMessage());
      ddn.d("Move to sd card is not supported");
    }
    return false;
  }
  
  public static Drawable u(Context paramContext, String paramString)
  {
    if (paramString != null) {}
    try
    {
      if (!paramString.equalsIgnoreCase("N/A")) {
        return paramContext.getPackageManager().getApplicationIcon(paramString);
      }
      return paramContext.getResources().getDrawable(dab.ic_launcher);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      return paramContext.getResources().getDrawable(dab.uninstall);
    }
    catch (Exception paramContext) {}
    paramString = paramContext.getResources().getDrawable(dab.ic_launcher);
    return paramString;
    return null;
  }
  
  public static void v(Context paramContext, String paramString)
  {
    try
    {
      if (paramContext.getPackageManager().getApplicationInfo(paramString, 0) != null)
      {
        Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.addCategory("android.intent.category.DEFAULT");
        localIntent.setData(Uri.parse("package:" + paramString));
        paramContext.startActivity(localIntent);
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static String w(Context paramContext, String paramString)
  {
    try
    {
      paramContext = new FileInputStream(new File("/data/data/" + paramContext.getPackageName() + "/" + paramString));
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString);
      ddn.d("highRiskJson " + paramContext);
      return paramContext;
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
  }
}
