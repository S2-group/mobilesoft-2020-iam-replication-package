package c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.calldorado.android.ClientConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CYW
{
  private static final String ˊ = CYW.class.getSimpleName();
  
  public CYW() {}
  
  /* Error */
  private ArrayList<String> ˊ()
  {
    // Byte code:
    //   0: new 27	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 28	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_1
    //   11: invokestatic 34	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   14: ldc 36
    //   16: invokevirtual 40	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   19: astore_3
    //   20: new 42	java/io/BufferedReader
    //   23: dup
    //   24: new 44	java/io/InputStreamReader
    //   27: dup
    //   28: aload_3
    //   29: invokevirtual 50	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   32: invokespecial 53	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   35: invokespecial 56	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   38: astore_2
    //   39: aload_2
    //   40: astore_1
    //   41: aload_2
    //   42: invokevirtual 59	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   45: astore 5
    //   47: aload 5
    //   49: ifnull +46 -> 95
    //   52: aload_2
    //   53: astore_1
    //   54: aload 4
    //   56: aload 5
    //   58: aload 5
    //   60: bipush 58
    //   62: invokevirtual 65	java/lang/String:indexOf	(I)I
    //   65: iconst_1
    //   66: iadd
    //   67: invokevirtual 69	java/lang/String:substring	(I)Ljava/lang/String;
    //   70: invokevirtual 73	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   73: pop
    //   74: goto -35 -> 39
    //   77: astore_3
    //   78: aload_2
    //   79: astore_1
    //   80: aload_3
    //   81: invokevirtual 76	java/lang/Exception:printStackTrace	()V
    //   84: aload_2
    //   85: ifnull +7 -> 92
    //   88: aload_2
    //   89: invokevirtual 79	java/io/BufferedReader:close	()V
    //   92: aload 4
    //   94: areturn
    //   95: aload_2
    //   96: astore_1
    //   97: aload_3
    //   98: invokevirtual 83	java/lang/Process:waitFor	()I
    //   101: pop
    //   102: aload_2
    //   103: invokevirtual 79	java/io/BufferedReader:close	()V
    //   106: aload 4
    //   108: areturn
    //   109: astore_1
    //   110: aload_1
    //   111: invokevirtual 84	java/io/IOException:printStackTrace	()V
    //   114: aload 4
    //   116: areturn
    //   117: astore_1
    //   118: aload_1
    //   119: invokevirtual 84	java/io/IOException:printStackTrace	()V
    //   122: aload 4
    //   124: areturn
    //   125: astore_3
    //   126: aload_1
    //   127: astore_2
    //   128: aload_3
    //   129: astore_1
    //   130: aload_2
    //   131: ifnull +7 -> 138
    //   134: aload_2
    //   135: invokevirtual 79	java/io/BufferedReader:close	()V
    //   138: aload_1
    //   139: athrow
    //   140: astore_2
    //   141: aload_2
    //   142: invokevirtual 84	java/io/IOException:printStackTrace	()V
    //   145: goto -7 -> 138
    //   148: astore_3
    //   149: aload_1
    //   150: astore_2
    //   151: aload_3
    //   152: astore_1
    //   153: goto -23 -> 130
    //   156: astore_3
    //   157: aconst_null
    //   158: astore_2
    //   159: goto -81 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	this	CYW
    //   10	87	1	localObject1	Object
    //   109	2	1	localIOException1	java.io.IOException
    //   117	10	1	localIOException2	java.io.IOException
    //   129	24	1	localObject2	Object
    //   38	97	2	localObject3	Object
    //   140	2	2	localIOException3	java.io.IOException
    //   150	9	2	localObject4	Object
    //   19	10	3	localProcess	Process
    //   77	21	3	localException1	Exception
    //   125	4	3	localObject5	Object
    //   148	4	3	localObject6	Object
    //   156	1	3	localException2	Exception
    //   7	116	4	localArrayList	ArrayList
    //   45	14	5	str	String
    // Exception table:
    //   from	to	target	type
    //   41	47	77	java/lang/Exception
    //   54	74	77	java/lang/Exception
    //   97	102	77	java/lang/Exception
    //   102	106	109	java/io/IOException
    //   88	92	117	java/io/IOException
    //   11	39	125	finally
    //   134	138	140	java/io/IOException
    //   41	47	148	finally
    //   54	74	148	finally
    //   80	84	148	finally
    //   97	102	148	finally
    //   11	39	156	java/lang/Exception
  }
  
  private ArrayList<String> ˊ(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
      paramContext = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        paramContext.add(localApplicationInfo.packageName);
        C2Y.ˊ(ˊ, "get Local Package: " + localApplicationInfo.packageName);
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      C2Y.ᐝ(ˊ, "Using fallback package getter");
      paramContext.printStackTrace();
      return ˊ();
    }
  }
  
  public void ˊ(Context paramContext, HU paramHU)
  {
    ClientConfig localClientConfig = KH.ˊ(paramContext).ʼ();
    long l1 = localClientConfig.ͺ();
    long l2 = localClientConfig.ι();
    long l3 = System.currentTimeMillis();
    Object localObject = ˊ(paramContext);
    if (((ArrayList)localObject).size() == 0) {
      return;
    }
    A4R localA4R = localClientConfig.ᐟ();
    if (localA4R == null) {
      localA4R = new A4R();
    }
    for (;;)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator1 = paramHU.ˋ().iterator();
      label83:
      label515:
      for (;;)
      {
        ZFP localZFP;
        if (localIterator1.hasNext())
        {
          localZFP = (ZFP)localIterator1.next();
          Iterator localIterator2 = ((ArrayList)localObject).iterator();
          do
          {
            if (!localIterator2.hasNext()) {
              break;
            }
          } while (!((String)localIterator2.next()).equalsIgnoreCase(localZFP.ˊ()));
        }
        for (int i = 1;; i = 0)
        {
          if ((i != 0) || (localZFP.ˊ().equals(paramContext.getPackageName()))) {
            break label515;
          }
          localArrayList.add(localZFP.ˊ());
          break label83;
          i = 1;
          if (l1 == 0L) {
            i = 0;
          }
          while (i != 0)
          {
            localClientConfig.ˋ(l3);
            paramHU = paramHU.ˋ().iterator();
            while (paramHU.hasNext())
            {
              localObject = (ZFP)paramHU.next();
              if ((!((ZFP)localObject).ˊ().equals(paramContext.getPackageName())) && (((ZFP)localObject).ˏ() != 0L) && (l3 - ((ZFP)localObject).ˏ() > l1))
              {
                localArrayList.add(((ZFP)localObject).ˊ());
                C2Y.ᐝ(ˊ, "Client timeout -  pack.getName()" + ((ZFP)localObject).ˊ() + ", currentTime " + l3 + ", pack.getTimeStamp(): " + ((ZFP)localObject).ˏ() + ", MAX_TIMEOUT: " + l1);
              }
            }
            if (l3 - l2 > l1)
            {
              C2Y.ˊ(ˊ, "over maxtimeout, cant determine dead clients");
              localClientConfig.ˋ(l3);
              if ((paramHU != null) && (paramHU.ˋ() != null))
              {
                localObject = paramHU.ˋ().iterator();
                while (((Iterator)localObject).hasNext()) {
                  ((ZFP)((Iterator)localObject).next()).ˊ(l3);
                }
                localClientConfig.ˊ(paramHU);
              }
              i = 0;
            }
          }
          i = 0;
          if (localArrayList.size() != localA4R.ˊ().size()) {
            i = 1;
          }
          while (i != 0)
          {
            localA4R.ˊ(localArrayList);
            localA4R.ˊ(localA4R.ˋ() + 1);
            localClientConfig.ˊ(localA4R);
            return;
            paramContext = new ArrayList(localArrayList);
            paramContext.removeAll(localA4R.ˊ());
            if (paramContext.size() != 0) {
              i = 1;
            }
          }
          break;
        }
      }
    }
  }
}
