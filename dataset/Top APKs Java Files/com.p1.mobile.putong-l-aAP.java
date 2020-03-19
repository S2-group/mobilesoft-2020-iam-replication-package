package l;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class aAP
{
  public aAP() {}
  
  /* Error */
  public static String ˊі()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore_2
    //   7: new 19	java/io/BufferedReader
    //   10: dup
    //   11: new 21	java/io/FileReader
    //   14: dup
    //   15: new 23	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   22: ldc 26
    //   24: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokestatic 36	android/os/Process:myPid	()I
    //   30: invokevirtual 39	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   33: ldc 41
    //   35: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: invokespecial 47	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   44: invokespecial 50	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   47: astore_1
    //   48: aload_1
    //   49: astore_2
    //   50: aload_1
    //   51: astore_3
    //   52: aload_1
    //   53: astore 4
    //   55: new 23	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   62: astore 5
    //   64: aload_1
    //   65: astore_2
    //   66: aload_1
    //   67: astore_3
    //   68: aload_1
    //   69: astore 4
    //   71: aload_1
    //   72: invokevirtual 53	java/io/BufferedReader:read	()I
    //   75: istore_0
    //   76: iload_0
    //   77: ifle +21 -> 98
    //   80: aload_1
    //   81: astore_2
    //   82: aload_1
    //   83: astore_3
    //   84: aload_1
    //   85: astore 4
    //   87: aload 5
    //   89: iload_0
    //   90: i2c
    //   91: invokevirtual 56	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   94: pop
    //   95: goto -31 -> 64
    //   98: aload_1
    //   99: astore_2
    //   100: aload_1
    //   101: astore_3
    //   102: aload_1
    //   103: astore 4
    //   105: aload 5
    //   107: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: astore 5
    //   112: aload_1
    //   113: ifnull +7 -> 120
    //   116: aload_1
    //   117: invokevirtual 59	java/io/BufferedReader:close	()V
    //   120: aload 5
    //   122: areturn
    //   123: aload_3
    //   124: ifnull +7 -> 131
    //   127: aload_3
    //   128: invokevirtual 59	java/io/BufferedReader:close	()V
    //   131: goto +33 -> 164
    //   134: goto +30 -> 164
    //   137: aload 4
    //   139: ifnull +8 -> 147
    //   142: aload 4
    //   144: invokevirtual 59	java/io/BufferedReader:close	()V
    //   147: goto +17 -> 164
    //   150: goto +14 -> 164
    //   153: astore_1
    //   154: aload_2
    //   155: ifnull +7 -> 162
    //   158: aload_2
    //   159: invokevirtual 59	java/io/BufferedReader:close	()V
    //   162: aload_1
    //   163: athrow
    //   164: invokestatic 36	android/os/Process:myPid	()I
    //   167: istore_0
    //   168: getstatic 65	l/avf:bUZ	Landroid/app/Application;
    //   171: ldc 67
    //   173: invokevirtual 73	android/app/Application:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   176: checkcast 75	android/app/ActivityManager
    //   179: invokevirtual 79	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   182: invokeinterface 85 1 0
    //   187: astore_1
    //   188: aload_1
    //   189: invokeinterface 91 1 0
    //   194: ifeq +29 -> 223
    //   197: aload_1
    //   198: invokeinterface 95 1 0
    //   203: checkcast 97	android/app/ActivityManager$RunningAppProcessInfo
    //   206: astore_2
    //   207: aload_2
    //   208: getfield 101	android/app/ActivityManager$RunningAppProcessInfo:pid	I
    //   211: iload_0
    //   212: if_icmpne +8 -> 220
    //   215: aload_2
    //   216: getfield 105	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   219: areturn
    //   220: goto -32 -> 188
    //   223: aconst_null
    //   224: areturn
    //   225: astore_1
    //   226: goto -103 -> 123
    //   229: astore_1
    //   230: goto -93 -> 137
    //   233: astore_1
    //   234: aload 5
    //   236: areturn
    //   237: astore_1
    //   238: goto -104 -> 134
    //   241: astore_1
    //   242: goto -92 -> 150
    //   245: astore_2
    //   246: goto -84 -> 162
    // Local variable table:
    //   start	length	slot	name	signature
    //   75	138	0	i	int
    //   47	70	1	localBufferedReader	java.io.BufferedReader
    //   153	10	1	localObject1	Object
    //   187	11	1	localIterator	Iterator
    //   225	1	1	localFileNotFoundException	java.io.FileNotFoundException
    //   229	1	1	localIOException1	java.io.IOException
    //   233	1	1	localIOException2	java.io.IOException
    //   237	1	1	localIOException3	java.io.IOException
    //   241	1	1	localIOException4	java.io.IOException
    //   6	210	2	localObject2	Object
    //   245	1	2	localIOException5	java.io.IOException
    //   1	127	3	localObject3	Object
    //   3	140	4	localObject4	Object
    //   62	173	5	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   7	48	153	finally
    //   55	64	153	finally
    //   71	76	153	finally
    //   87	95	153	finally
    //   105	112	153	finally
    //   7	48	225	java/io/FileNotFoundException
    //   55	64	225	java/io/FileNotFoundException
    //   71	76	225	java/io/FileNotFoundException
    //   87	95	225	java/io/FileNotFoundException
    //   105	112	225	java/io/FileNotFoundException
    //   7	48	229	java/io/IOException
    //   55	64	229	java/io/IOException
    //   71	76	229	java/io/IOException
    //   87	95	229	java/io/IOException
    //   105	112	229	java/io/IOException
    //   116	120	233	java/io/IOException
    //   127	131	237	java/io/IOException
    //   142	147	241	java/io/IOException
    //   158	162	245	java/io/IOException
  }
  
  public static Pair<List<aAP.ˊ>, List<aAP.ˊ>> ˏˌ(Context paramContext)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      aAP.ˊ localˊ;
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        localˊ = new aAP.ˊ();
        localˊ.chp = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
        localˊ.packageName = localPackageInfo.packageName;
        localArrayList1.add(localˊ);
      }
      else
      {
        localˊ = new aAP.ˊ();
        localˊ.chp = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
        localˊ.packageName = localPackageInfo.packageName;
        localArrayList2.add(localˊ);
      }
    }
    return new Pair(localArrayList1, localArrayList2);
  }
  
  public static List<aAP.ˊ> ˏـ(Context paramContext)
  {
    Object localObject = ato.getRunningAppProcesses();
    PackageManager localPackageManager = paramContext.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    for (;;)
    {
      atq localAtq;
      aAP.ˊ localˊ;
      if (((Iterator)localObject).hasNext())
      {
        localAtq = (atq)((Iterator)localObject).next();
        localˊ = new aAP.ˊ();
      }
      try
      {
        localˊ.chp = localAtq.ˌ(paramContext, 0).applicationInfo.loadLabel(localPackageManager).toString();
        localˊ.packageName = localAtq.getPackageName();
        localˊ.pid = localAtq.pid;
        localˊ.processName = localAtq.name;
        localArrayList.add(localˊ);
        continue;
        return localArrayList;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;) {}
      }
    }
  }
  
  public static Intent ॱι(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static boolean ᐝᶫ(String paramString)
  {
    try
    {
      avf.bUZ.getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return false;
  }
}
