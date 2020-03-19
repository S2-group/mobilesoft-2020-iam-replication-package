package mobi.infolife.j;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import java.util.List;

public class c
{
  public static void a(Context paramContext)
  {
    new Thread(new c.1(paramContext)).start();
  }
  
  /* Error */
  private static String b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 31	java/io/FileReader
    //   5: dup
    //   6: ldc 33
    //   8: invokespecial 36	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   11: astore_2
    //   12: new 38	java/io/BufferedReader
    //   15: dup
    //   16: aload_2
    //   17: invokespecial 41	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   20: astore_0
    //   21: aload_0
    //   22: invokevirtual 44	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   25: ldc 46
    //   27: iconst_2
    //   28: invokevirtual 52	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   31: iconst_1
    //   32: aaload
    //   33: astore_1
    //   34: aload_2
    //   35: ifnull +7 -> 42
    //   38: aload_2
    //   39: invokevirtual 55	java/io/FileReader:close	()V
    //   42: aload_0
    //   43: ifnull +7 -> 50
    //   46: aload_0
    //   47: invokevirtual 56	java/io/BufferedReader:close	()V
    //   50: aload_1
    //   51: areturn
    //   52: astore_0
    //   53: aload_0
    //   54: invokevirtual 59	java/io/IOException:printStackTrace	()V
    //   57: aload_1
    //   58: areturn
    //   59: astore_2
    //   60: aconst_null
    //   61: astore_0
    //   62: aload_2
    //   63: invokevirtual 60	java/lang/Exception:printStackTrace	()V
    //   66: aload_1
    //   67: ifnull +7 -> 74
    //   70: aload_1
    //   71: invokevirtual 55	java/io/FileReader:close	()V
    //   74: aload_0
    //   75: ifnull +7 -> 82
    //   78: aload_0
    //   79: invokevirtual 56	java/io/BufferedReader:close	()V
    //   82: ldc 62
    //   84: areturn
    //   85: astore_0
    //   86: aload_0
    //   87: invokevirtual 59	java/io/IOException:printStackTrace	()V
    //   90: goto -8 -> 82
    //   93: astore_1
    //   94: aconst_null
    //   95: astore_0
    //   96: aconst_null
    //   97: astore_2
    //   98: aload_2
    //   99: ifnull +7 -> 106
    //   102: aload_2
    //   103: invokevirtual 55	java/io/FileReader:close	()V
    //   106: aload_0
    //   107: ifnull +7 -> 114
    //   110: aload_0
    //   111: invokevirtual 56	java/io/BufferedReader:close	()V
    //   114: aload_1
    //   115: athrow
    //   116: astore_0
    //   117: aload_0
    //   118: invokevirtual 59	java/io/IOException:printStackTrace	()V
    //   121: goto -7 -> 114
    //   124: astore_1
    //   125: aconst_null
    //   126: astore_0
    //   127: goto -29 -> 98
    //   130: astore_1
    //   131: goto -33 -> 98
    //   134: astore_3
    //   135: aload_1
    //   136: astore_2
    //   137: aload_3
    //   138: astore_1
    //   139: goto -41 -> 98
    //   142: astore_3
    //   143: aconst_null
    //   144: astore_0
    //   145: aload_2
    //   146: astore_1
    //   147: aload_3
    //   148: astore_2
    //   149: goto -87 -> 62
    //   152: astore_3
    //   153: aload_2
    //   154: astore_1
    //   155: aload_3
    //   156: astore_2
    //   157: goto -95 -> 62
    // Local variable table:
    //   start	length	slot	name	signature
    //   20	27	0	localBufferedReader	java.io.BufferedReader
    //   52	2	0	localIOException1	java.io.IOException
    //   61	18	0	localObject1	Object
    //   85	2	0	localIOException2	java.io.IOException
    //   95	16	0	localObject2	Object
    //   116	2	0	localIOException3	java.io.IOException
    //   126	19	0	localObject3	Object
    //   1	70	1	str	String
    //   93	22	1	localObject4	Object
    //   124	1	1	localObject5	Object
    //   130	6	1	localObject6	Object
    //   138	17	1	localObject7	Object
    //   11	28	2	localFileReader	java.io.FileReader
    //   59	4	2	localException1	Exception
    //   97	60	2	localObject8	Object
    //   134	4	3	localObject9	Object
    //   142	6	3	localException2	Exception
    //   152	4	3	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   38	42	52	java/io/IOException
    //   46	50	52	java/io/IOException
    //   2	12	59	java/lang/Exception
    //   70	74	85	java/io/IOException
    //   78	82	85	java/io/IOException
    //   2	12	93	finally
    //   102	106	116	java/io/IOException
    //   110	114	116	java/io/IOException
    //   12	21	124	finally
    //   21	34	130	finally
    //   62	66	134	finally
    //   12	21	142	java/lang/Exception
    //   21	34	152	java/lang/Exception
  }
  
  public static String b(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      if ((localMemoryInfo.totalMem < 1825361100L) || (paramContext.widthPixels < 720)) {
        return "low_machine";
      }
      if ((localMemoryInfo.totalMem > 1825361100L) && (localMemoryInfo.totalMem <= 2147483648L) && (paramContext.widthPixels >= 720) && (paramContext.widthPixels <= 1080)) {
        return "middle_machine";
      }
      if ((localMemoryInfo.totalMem > 2147483648L) && (paramContext.widthPixels >= 1080)) {
        return "high_machine";
      }
    }
    return "";
  }
  
  public static String[] c(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = paramContext.getResources().getStringArray(2131296283);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "";
    arrayOfString[1] = "";
    localObject = ((PackageManager)localObject).getInstalledPackages(8192);
    int j = 0;
    int i = 0;
    int k;
    if (j < ((List)localObject).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(j);
      k = 0;
      label74:
      if (k < paramContext.length)
      {
        if (!localPackageInfo.packageName.equals(paramContext[k])) {
          break label157;
        }
        i += 1;
        arrayOfString[0] = (arrayOfString[0] + localPackageInfo.packageName + "|");
      }
    }
    label157:
    for (;;)
    {
      k += 1;
      break label74;
      j += 1;
      break;
      arrayOfString[1] = String.valueOf(i);
      return arrayOfString;
    }
  }
}
