package com.microsoft.launcher.next.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.microsoft.launcher.utils.ah;
import com.microsoft.launcher.utils.g;
import java.util.HashMap;
import java.util.Map;

public class w
{
  /* Error */
  public static java.util.List<android.content.pm.PackageInfo> a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 16	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: aload_3
    //   6: iload_1
    //   7: invokevirtual 22	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: astore_0
    //   14: ldc 24
    //   16: iconst_1
    //   17: anewarray 4	java/lang/Object
    //   20: dup
    //   21: iconst_0
    //   22: aload_0
    //   23: invokevirtual 28	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   26: aastore
    //   27: invokestatic 34	com/microsoft/launcher/utils/g:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   30: ldc 36
    //   32: invokestatic 40	com/microsoft/launcher/utils/g:f	(Ljava/lang/String;)V
    //   35: new 42	java/util/ArrayList
    //   38: dup
    //   39: invokespecial 46	java/util/ArrayList:<init>	()V
    //   42: astore 4
    //   44: invokestatic 52	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   47: ldc 54
    //   49: invokevirtual 58	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   52: astore 5
    //   54: new 60	java/io/BufferedReader
    //   57: dup
    //   58: new 62	java/io/InputStreamReader
    //   61: dup
    //   62: aload 5
    //   64: invokevirtual 68	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   67: invokespecial 71	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   70: invokespecial 74	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   73: astore_2
    //   74: aload_2
    //   75: astore_0
    //   76: aload_2
    //   77: invokevirtual 77	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   80: astore 6
    //   82: aload 6
    //   84: ifnull +78 -> 162
    //   87: aload_2
    //   88: astore_0
    //   89: aload 4
    //   91: aload_3
    //   92: aload 6
    //   94: aload 6
    //   96: bipush 58
    //   98: invokevirtual 83	java/lang/String:indexOf	(I)I
    //   101: iconst_1
    //   102: iadd
    //   103: invokevirtual 87	java/lang/String:substring	(I)Ljava/lang/String;
    //   106: iload_1
    //   107: invokevirtual 91	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   110: invokeinterface 97 2 0
    //   115: pop
    //   116: goto -42 -> 74
    //   119: astore_3
    //   120: aload_2
    //   121: astore_0
    //   122: ldc 99
    //   124: iconst_1
    //   125: anewarray 4	java/lang/Object
    //   128: dup
    //   129: iconst_0
    //   130: aload_3
    //   131: invokevirtual 28	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   134: aastore
    //   135: invokestatic 34	com/microsoft/launcher/utils/g:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   138: aload_2
    //   139: astore_0
    //   140: aload_3
    //   141: invokevirtual 102	java/lang/Exception:printStackTrace	()V
    //   144: aload 4
    //   146: astore_0
    //   147: aload_2
    //   148: ifnull -137 -> 11
    //   151: aload_2
    //   152: invokevirtual 105	java/io/BufferedReader:close	()V
    //   155: aload 4
    //   157: areturn
    //   158: astore_0
    //   159: aload 4
    //   161: areturn
    //   162: aload_2
    //   163: astore_0
    //   164: aload 5
    //   166: invokevirtual 109	java/lang/Process:waitFor	()I
    //   169: pop
    //   170: aload 4
    //   172: astore_0
    //   173: aload_2
    //   174: ifnull -163 -> 11
    //   177: aload_2
    //   178: invokevirtual 105	java/io/BufferedReader:close	()V
    //   181: aload 4
    //   183: areturn
    //   184: astore_0
    //   185: aload 4
    //   187: areturn
    //   188: astore_2
    //   189: aconst_null
    //   190: astore_0
    //   191: aload_0
    //   192: ifnull +7 -> 199
    //   195: aload_0
    //   196: invokevirtual 105	java/io/BufferedReader:close	()V
    //   199: aload_2
    //   200: athrow
    //   201: astore_0
    //   202: goto -3 -> 199
    //   205: astore_2
    //   206: goto -15 -> 191
    //   209: astore_3
    //   210: aconst_null
    //   211: astore_2
    //   212: goto -92 -> 120
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	215	0	paramContext	Context
    //   0	215	1	paramInt	int
    //   73	105	2	localBufferedReader	java.io.BufferedReader
    //   188	12	2	localObject1	Object
    //   205	1	2	localObject2	Object
    //   211	1	2	localObject3	Object
    //   4	88	3	localPackageManager	android.content.pm.PackageManager
    //   119	22	3	localException1	Exception
    //   209	1	3	localException2	Exception
    //   42	144	4	localArrayList	java.util.ArrayList
    //   52	113	5	localProcess	Process
    //   80	15	6	str	String
    // Exception table:
    //   from	to	target	type
    //   5	11	13	java/lang/Exception
    //   76	82	119	java/lang/Exception
    //   89	116	119	java/lang/Exception
    //   164	170	119	java/lang/Exception
    //   151	155	158	java/io/IOException
    //   177	181	184	java/io/IOException
    //   44	74	188	finally
    //   195	199	201	java/io/IOException
    //   76	82	205	finally
    //   89	116	205	finally
    //   122	138	205	finally
    //   140	144	205	finally
    //   164	170	205	finally
    //   44	74	209	java/lang/Exception
  }
  
  public static boolean a()
  {
    return Build.VERSION.SDK_INT >= 18;
  }
  
  public static boolean a(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
  
  public static boolean a(int paramInt1, int paramInt2)
  {
    return (Build.VERSION.SDK_INT >= paramInt1) && (Build.VERSION.SDK_INT <= paramInt2);
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean b()
  {
    return Build.VERSION.SDK_INT >= 19;
  }
  
  public static boolean b(int paramInt)
  {
    return Build.VERSION.SDK_INT <= paramInt;
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1);
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean c()
  {
    return Build.VERSION.SDK_INT > 19;
  }
  
  public static boolean d()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static Map e()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("MANUFACTURER", Build.MANUFACTURER);
    localHashMap.put("MODEL", Build.MODEL);
    localHashMap.put("BRAND", Build.BRAND);
    localHashMap.put("DEVICE", Build.DEVICE);
    localHashMap.put("BOARD", Build.BOARD);
    localHashMap.put("SDK_INT", String.valueOf(Build.VERSION.SDK_INT));
    localHashMap.put("RELEASE", Build.VERSION.RELEASE);
    localHashMap.put("os.version", System.getProperty("os.version"));
    if (ah.a)
    {
      g.a("getSystemInfo: MANUFACTURER: %s", new Object[] { Build.MANUFACTURER });
      g.a("getSystemInfo: MODEL: %s", new Object[] { Build.MODEL });
      g.a("getSystemInfo: BRAND: %s", new Object[] { Build.BRAND });
      g.a("getSystemInfo: DEVICE: %s", new Object[] { Build.DEVICE });
      g.a("getSystemInfo: BOARD: %s", new Object[] { Build.BOARD });
      g.a("getSystemInfo: SDK_INT: %s", new Object[] { String.valueOf(Build.VERSION.SDK_INT) });
      g.a("getSystemInfo: RELEASE: %s", new Object[] { Build.VERSION.RELEASE });
      g.a("getSystemInfo: os.version: %s", new Object[] { System.getProperty("os.version") });
    }
    return localHashMap;
  }
  
  public static boolean f()
  {
    return ("samsung".equalsIgnoreCase(Build.MANUFACTURER)) || ("samsung".equalsIgnoreCase(Build.BRAND));
  }
}
