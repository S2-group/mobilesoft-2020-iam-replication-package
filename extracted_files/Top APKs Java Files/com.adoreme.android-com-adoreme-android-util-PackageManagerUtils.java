package com.adoreme.android.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Iterator;
import java.util.List;

public class PackageManagerUtils
{
  public PackageManagerUtils() {}
  
  /* Error */
  public static List<PackageInfo> getInstalledPackages(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 21	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: getstatic 27	android/os/Build$VERSION:SDK_INT	I
    //   8: bipush 22
    //   10: if_icmplt +9 -> 19
    //   13: aload_3
    //   14: iload_1
    //   15: invokevirtual 32	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   18: areturn
    //   19: aload_3
    //   20: iload_1
    //   21: invokevirtual 32	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   24: astore_0
    //   25: aload_0
    //   26: areturn
    //   27: new 34	java/util/ArrayList
    //   30: dup
    //   31: invokespecial 35	java/util/ArrayList:<init>	()V
    //   34: astore 5
    //   36: aconst_null
    //   37: astore 4
    //   39: aconst_null
    //   40: astore_2
    //   41: aload_2
    //   42: astore_0
    //   43: invokestatic 41	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   46: ldc 43
    //   48: invokevirtual 47	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   51: astore 6
    //   53: aload_2
    //   54: astore_0
    //   55: new 49	java/io/BufferedReader
    //   58: dup
    //   59: new 51	java/io/InputStreamReader
    //   62: dup
    //   63: aload 6
    //   65: invokevirtual 57	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   68: invokespecial 60	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   71: invokespecial 63	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   74: astore_2
    //   75: aload_2
    //   76: invokevirtual 67	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   79: astore_0
    //   80: aload_0
    //   81: ifnull +31 -> 112
    //   84: aload 5
    //   86: aload_3
    //   87: aload_0
    //   88: aload_0
    //   89: bipush 58
    //   91: invokevirtual 73	java/lang/String:indexOf	(I)I
    //   94: iconst_1
    //   95: iadd
    //   96: invokevirtual 77	java/lang/String:substring	(I)Ljava/lang/String;
    //   99: iload_1
    //   100: invokevirtual 81	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   103: invokeinterface 87 2 0
    //   108: pop
    //   109: goto -34 -> 75
    //   112: aload 6
    //   114: invokevirtual 91	java/lang/Process:waitFor	()I
    //   117: pop
    //   118: aload_2
    //   119: invokevirtual 94	java/io/BufferedReader:close	()V
    //   122: aload 5
    //   124: areturn
    //   125: astore_3
    //   126: aload_2
    //   127: astore_0
    //   128: aload_3
    //   129: astore_2
    //   130: goto +40 -> 170
    //   133: astore_3
    //   134: goto +11 -> 145
    //   137: astore_2
    //   138: goto +32 -> 170
    //   141: astore_3
    //   142: aload 4
    //   144: astore_2
    //   145: aload_2
    //   146: astore_0
    //   147: aload_3
    //   148: invokevirtual 97	java/lang/Exception:printStackTrace	()V
    //   151: aload_2
    //   152: ifnull +15 -> 167
    //   155: aload_2
    //   156: invokevirtual 94	java/io/BufferedReader:close	()V
    //   159: aload 5
    //   161: areturn
    //   162: astore_0
    //   163: aload_0
    //   164: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   167: aload 5
    //   169: areturn
    //   170: aload_0
    //   171: ifnull +15 -> 186
    //   174: aload_0
    //   175: invokevirtual 94	java/io/BufferedReader:close	()V
    //   178: goto +8 -> 186
    //   181: astore_0
    //   182: aload_0
    //   183: invokevirtual 98	java/io/IOException:printStackTrace	()V
    //   186: aload_2
    //   187: athrow
    //   188: astore_0
    //   189: goto -162 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	paramContext	Context
    //   0	192	1	paramInt	int
    //   40	90	2	localObject1	Object
    //   137	1	2	localObject2	Object
    //   144	43	2	localObject3	Object
    //   4	83	3	localPackageManager	android.content.pm.PackageManager
    //   125	4	3	localObject4	Object
    //   133	1	3	localException1	Exception
    //   141	7	3	localException2	Exception
    //   37	106	4	localObject5	Object
    //   34	134	5	localArrayList	java.util.ArrayList
    //   51	62	6	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   75	80	125	finally
    //   84	109	125	finally
    //   112	118	125	finally
    //   75	80	133	java/lang/Exception
    //   84	109	133	java/lang/Exception
    //   112	118	133	java/lang/Exception
    //   43	53	137	finally
    //   55	75	137	finally
    //   147	151	137	finally
    //   43	53	141	java/lang/Exception
    //   55	75	141	java/lang/Exception
    //   118	122	162	java/io/IOException
    //   155	159	162	java/io/IOException
    //   174	178	181	java/io/IOException
    //   19	25	188	java/lang/Exception
  }
  
  public static boolean isPackageInstalled(Context paramContext, String paramString)
  {
    paramContext = getInstalledPackages(paramContext, 1);
    if (CollectionUtils.isEmpty(paramContext)) {
      return false;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext()) {
      if (paramString.equals(((PackageInfo)paramContext.next()).packageName)) {
        return true;
      }
    }
    return false;
  }
}
