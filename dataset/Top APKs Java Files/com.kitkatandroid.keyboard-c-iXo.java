package c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class iXo
{
  private static final String a = iXo.class.getSimpleName();
  
  public iXo() {}
  
  /* Error */
  private static ArrayList<String> a()
  {
    // Byte code:
    //   0: new 27	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 28	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: aconst_null
    //   9: astore_0
    //   10: invokestatic 34	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: ldc 36
    //   15: invokevirtual 40	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   18: astore_2
    //   19: new 42	java/io/BufferedReader
    //   22: dup
    //   23: new 44	java/io/InputStreamReader
    //   26: dup
    //   27: aload_2
    //   28: invokevirtual 50	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   31: invokespecial 53	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   34: invokespecial 56	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   37: astore_1
    //   38: aload_1
    //   39: astore_0
    //   40: aload_1
    //   41: invokevirtual 59	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   44: astore 4
    //   46: aload 4
    //   48: ifnull +44 -> 92
    //   51: aload_1
    //   52: astore_0
    //   53: aload_3
    //   54: aload 4
    //   56: aload 4
    //   58: bipush 58
    //   60: invokevirtual 65	java/lang/String:indexOf	(I)I
    //   63: iconst_1
    //   64: iadd
    //   65: invokevirtual 69	java/lang/String:substring	(I)Ljava/lang/String;
    //   68: invokevirtual 73	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   71: pop
    //   72: goto -34 -> 38
    //   75: astore_2
    //   76: aload_1
    //   77: astore_0
    //   78: aload_2
    //   79: invokevirtual 76	java/lang/Exception:printStackTrace	()V
    //   82: aload_1
    //   83: ifnull +7 -> 90
    //   86: aload_1
    //   87: invokevirtual 79	java/io/BufferedReader:close	()V
    //   90: aload_3
    //   91: areturn
    //   92: aload_1
    //   93: astore_0
    //   94: aload_2
    //   95: invokevirtual 83	java/lang/Process:waitFor	()I
    //   98: pop
    //   99: aload_1
    //   100: invokevirtual 79	java/io/BufferedReader:close	()V
    //   103: aload_3
    //   104: areturn
    //   105: astore_0
    //   106: aload_0
    //   107: invokevirtual 84	java/io/IOException:printStackTrace	()V
    //   110: aload_3
    //   111: areturn
    //   112: astore_0
    //   113: aload_0
    //   114: invokevirtual 84	java/io/IOException:printStackTrace	()V
    //   117: aload_3
    //   118: areturn
    //   119: astore_2
    //   120: aload_0
    //   121: astore_1
    //   122: aload_2
    //   123: astore_0
    //   124: aload_1
    //   125: ifnull +7 -> 132
    //   128: aload_1
    //   129: invokevirtual 79	java/io/BufferedReader:close	()V
    //   132: aload_0
    //   133: athrow
    //   134: astore_1
    //   135: aload_1
    //   136: invokevirtual 84	java/io/IOException:printStackTrace	()V
    //   139: goto -7 -> 132
    //   142: astore_2
    //   143: aload_0
    //   144: astore_1
    //   145: aload_2
    //   146: astore_0
    //   147: goto -23 -> 124
    //   150: astore_2
    //   151: aconst_null
    //   152: astore_1
    //   153: goto -77 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   9	85	0	localObject1	Object
    //   105	2	0	localIOException1	java.io.IOException
    //   112	9	0	localIOException2	java.io.IOException
    //   123	24	0	localObject2	Object
    //   37	92	1	localObject3	Object
    //   134	2	1	localIOException3	java.io.IOException
    //   144	9	1	localObject4	Object
    //   18	10	2	localProcess	Process
    //   75	20	2	localException1	Exception
    //   119	4	2	localObject5	Object
    //   142	4	2	localObject6	Object
    //   150	1	2	localException2	Exception
    //   7	111	3	localArrayList	ArrayList
    //   44	13	4	str	String
    // Exception table:
    //   from	to	target	type
    //   40	46	75	java/lang/Exception
    //   53	72	75	java/lang/Exception
    //   94	99	75	java/lang/Exception
    //   99	103	105	java/io/IOException
    //   86	90	112	java/io/IOException
    //   10	38	119	finally
    //   128	132	134	java/io/IOException
    //   40	46	142	finally
    //   53	72	142	finally
    //   78	82	142	finally
    //   94	99	142	finally
    //   10	38	150	java/lang/Exception
  }
  
  private static ArrayList<String> a(Context paramContext)
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
        iio.a(a, "get Local Package: " + localApplicationInfo.packageName);
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      iio.e(a, "Using fallback package getter");
      paramContext.printStackTrace();
      return a();
    }
  }
  
  public static void a(Context paramContext, iwB paramIwB)
  {
    iiC localIiC = iiH.a(paramContext).b;
    long l1 = localIiC.Q;
    long l2 = localIiC.R;
    long l3 = System.currentTimeMillis();
    Object localObject = a(paramContext);
    if (((ArrayList)localObject).size() == 0) {
      return;
    }
    iwe localIwe = localIiC.k();
    if (localIwe == null) {
      localIwe = new iwe();
    }
    for (;;)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator1 = paramIwB.a().iterator();
      label81:
      label509:
      for (;;)
      {
        iwQ localIwQ;
        if (localIterator1.hasNext())
        {
          localIwQ = (iwQ)localIterator1.next();
          Iterator localIterator2 = ((ArrayList)localObject).iterator();
          do
          {
            if (!localIterator2.hasNext()) {
              break;
            }
          } while (!((String)localIterator2.next()).equalsIgnoreCase(localIwQ.a));
        }
        for (int i = 1;; i = 0)
        {
          if ((i != 0) || (localIwQ.a.equals(paramContext.getPackageName()))) {
            break label509;
          }
          localArrayList.add(localIwQ.a);
          break label81;
          i = 1;
          if (l1 == 0L) {
            i = 0;
          }
          while (i != 0)
          {
            localIiC.a(l3);
            paramIwB = paramIwB.a().iterator();
            while (paramIwB.hasNext())
            {
              localObject = (iwQ)paramIwB.next();
              if ((!((iwQ)localObject).a.equals(paramContext.getPackageName())) && (((iwQ)localObject).e != 0L) && (l3 - ((iwQ)localObject).e > l1))
              {
                localArrayList.add(((iwQ)localObject).a);
                iio.e(a, "Client timeout -  pack.getName()" + ((iwQ)localObject).a + ", currentTime " + l3 + ", pack.getTimeStamp(): " + ((iwQ)localObject).e + ", MAX_TIMEOUT: " + l1);
              }
            }
            if (l3 - l2 > l1)
            {
              iio.a(a, "over maxtimeout, cant determine dead clients");
              localIiC.a(l3);
              if ((paramIwB != null) && (paramIwB.a() != null))
              {
                localObject = paramIwB.a().iterator();
                while (((Iterator)localObject).hasNext()) {
                  ((iwQ)((Iterator)localObject).next()).e = l3;
                }
                localIiC.a(paramIwB);
              }
              i = 0;
            }
          }
          i = 0;
          if (localArrayList.size() != localIwe.a.size()) {
            i = 1;
          }
          while (i != 0)
          {
            localIwe.a = localArrayList;
            localIwe.b += 1;
            localIiC.a(localIwe);
            return;
            paramContext = new ArrayList(localArrayList);
            paramContext.removeAll(localIwe.a);
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
