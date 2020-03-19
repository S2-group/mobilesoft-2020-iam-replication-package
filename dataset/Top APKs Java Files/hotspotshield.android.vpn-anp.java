import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class anp
{
  public anp() {}
  
  public static List<ResolveInfo> a(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localLinkedList = new LinkedList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = b(paramContext).iterator();
      while (paramContext.hasNext())
      {
        localIntent.setPackage((String)paramContext.next());
        ResolveInfo localResolveInfo = localPackageManager.resolveActivity(localIntent, 128);
        if (localResolveInfo != null) {
          localLinkedList.add(localResolveInfo);
        }
      }
      return localLinkedList;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  private static List<String> b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 35	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_0
    //   5: new 28	java/util/LinkedList
    //   8: dup
    //   9: invokespecial 29	java/util/LinkedList:<init>	()V
    //   12: astore_3
    //   13: aload_0
    //   14: iconst_0
    //   15: invokevirtual 80	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   18: invokeinterface 44 1 0
    //   23: astore_0
    //   24: aload_0
    //   25: invokeinterface 50 1 0
    //   30: ifeq +124 -> 154
    //   33: aload_3
    //   34: aload_0
    //   35: invokeinterface 54 1 0
    //   40: checkcast 82	android/content/pm/PackageInfo
    //   43: getfield 86	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   46: invokeinterface 69 2 0
    //   51: pop
    //   52: goto -28 -> 24
    //   55: astore_0
    //   56: aload_0
    //   57: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   60: new 88	java/util/ArrayList
    //   63: dup
    //   64: invokespecial 89	java/util/ArrayList:<init>	()V
    //   67: pop
    //   68: aconst_null
    //   69: astore_0
    //   70: invokestatic 95	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   73: ldc 97
    //   75: invokevirtual 101	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   78: astore_2
    //   79: new 103	java/io/BufferedReader
    //   82: dup
    //   83: new 105	java/io/InputStreamReader
    //   86: dup
    //   87: aload_2
    //   88: invokevirtual 111	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   91: invokespecial 114	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   94: invokespecial 117	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   97: astore_1
    //   98: aload_1
    //   99: astore_0
    //   100: aload_1
    //   101: invokevirtual 121	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   104: astore 4
    //   106: aload 4
    //   108: ifnull +48 -> 156
    //   111: aload_1
    //   112: astore_0
    //   113: aload_3
    //   114: aload 4
    //   116: aload 4
    //   118: bipush 58
    //   120: invokevirtual 125	java/lang/String:indexOf	(I)I
    //   123: iconst_1
    //   124: iadd
    //   125: invokevirtual 129	java/lang/String:substring	(I)Ljava/lang/String;
    //   128: invokeinterface 69 2 0
    //   133: pop
    //   134: goto -36 -> 98
    //   137: astore_2
    //   138: aload_1
    //   139: astore_0
    //   140: aload_2
    //   141: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   144: aload_1
    //   145: ifnull +7 -> 152
    //   148: aload_1
    //   149: invokevirtual 132	java/io/BufferedReader:close	()V
    //   152: aload_3
    //   153: areturn
    //   154: aload_3
    //   155: areturn
    //   156: aload_1
    //   157: astore_0
    //   158: aload_2
    //   159: invokevirtual 136	java/lang/Process:waitFor	()I
    //   162: pop
    //   163: aload_1
    //   164: invokevirtual 132	java/io/BufferedReader:close	()V
    //   167: goto -15 -> 152
    //   170: astore_0
    //   171: aload_0
    //   172: invokevirtual 137	java/io/IOException:printStackTrace	()V
    //   175: goto -23 -> 152
    //   178: astore_0
    //   179: aload_0
    //   180: invokevirtual 137	java/io/IOException:printStackTrace	()V
    //   183: goto -31 -> 152
    //   186: astore_2
    //   187: aload_0
    //   188: astore_1
    //   189: aload_2
    //   190: astore_0
    //   191: aload_1
    //   192: ifnull +7 -> 199
    //   195: aload_1
    //   196: invokevirtual 132	java/io/BufferedReader:close	()V
    //   199: aload_0
    //   200: athrow
    //   201: astore_1
    //   202: aload_1
    //   203: invokevirtual 137	java/io/IOException:printStackTrace	()V
    //   206: goto -7 -> 199
    //   209: astore_2
    //   210: aload_0
    //   211: astore_1
    //   212: aload_2
    //   213: astore_0
    //   214: goto -23 -> 191
    //   217: astore_2
    //   218: aconst_null
    //   219: astore_1
    //   220: goto -82 -> 138
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	223	0	paramContext	Context
    //   97	99	1	localObject1	Object
    //   201	2	1	localIOException	java.io.IOException
    //   211	9	1	localContext	Context
    //   78	10	2	localProcess	Process
    //   137	22	2	localException1	Exception
    //   186	4	2	localObject2	Object
    //   209	4	2	localObject3	Object
    //   217	1	2	localException2	Exception
    //   12	143	3	localLinkedList	LinkedList
    //   104	13	4	str	String
    // Exception table:
    //   from	to	target	type
    //   13	24	55	java/lang/Exception
    //   24	52	55	java/lang/Exception
    //   100	106	137	java/lang/Exception
    //   113	134	137	java/lang/Exception
    //   158	163	137	java/lang/Exception
    //   163	167	170	java/io/IOException
    //   148	152	178	java/io/IOException
    //   70	98	186	finally
    //   195	199	201	java/io/IOException
    //   100	106	209	finally
    //   113	134	209	finally
    //   140	144	209	finally
    //   158	163	209	finally
    //   70	98	217	java/lang/Exception
  }
}
