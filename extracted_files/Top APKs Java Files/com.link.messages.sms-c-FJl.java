package c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FJl
{
  private static final String a = FJl.class.getSimpleName();
  
  public FJl() {}
  
  private ArrayList<String> a(Context paramContext)
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
        FFl.a(a, "get Local Package: " + localApplicationInfo.packageName);
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      FFl.e(a, "Using fallback package getter");
      paramContext.printStackTrace();
      return a();
    }
  }
  
  /* Error */
  public ArrayList<String> a()
  {
    // Byte code:
    //   0: new 37	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 38	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_1
    //   11: invokestatic 103	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   14: ldc 105
    //   16: invokevirtual 109	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   19: astore_3
    //   20: new 111	java/io/BufferedReader
    //   23: dup
    //   24: new 113	java/io/InputStreamReader
    //   27: dup
    //   28: aload_3
    //   29: invokevirtual 119	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   32: invokespecial 122	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   35: invokespecial 125	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   38: astore_2
    //   39: aload_2
    //   40: astore_1
    //   41: aload_2
    //   42: invokevirtual 128	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   45: astore 5
    //   47: aload 5
    //   49: ifnull +46 -> 95
    //   52: aload_2
    //   53: astore_1
    //   54: aload 4
    //   56: aload 5
    //   58: aload 5
    //   60: bipush 58
    //   62: invokevirtual 134	java/lang/String:indexOf	(I)I
    //   65: iconst_1
    //   66: iadd
    //   67: invokevirtual 138	java/lang/String:substring	(I)Ljava/lang/String;
    //   70: invokevirtual 63	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   73: pop
    //   74: goto -35 -> 39
    //   77: astore_3
    //   78: aload_2
    //   79: astore_1
    //   80: aload_3
    //   81: invokevirtual 90	java/lang/Exception:printStackTrace	()V
    //   84: aload_2
    //   85: ifnull +7 -> 92
    //   88: aload_2
    //   89: invokevirtual 141	java/io/BufferedReader:close	()V
    //   92: aload 4
    //   94: areturn
    //   95: aload_2
    //   96: astore_1
    //   97: aload_3
    //   98: invokevirtual 145	java/lang/Process:waitFor	()I
    //   101: pop
    //   102: aload_2
    //   103: invokevirtual 141	java/io/BufferedReader:close	()V
    //   106: aload 4
    //   108: areturn
    //   109: astore_1
    //   110: aload_1
    //   111: invokevirtual 146	java/io/IOException:printStackTrace	()V
    //   114: aload 4
    //   116: areturn
    //   117: astore_1
    //   118: aload_1
    //   119: invokevirtual 146	java/io/IOException:printStackTrace	()V
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
    //   135: invokevirtual 141	java/io/BufferedReader:close	()V
    //   138: aload_1
    //   139: athrow
    //   140: astore_2
    //   141: aload_2
    //   142: invokevirtual 146	java/io/IOException:printStackTrace	()V
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
    //   0	162	0	this	FJl
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
  
  public void a(Context paramContext, F62 paramF62)
  {
    FF8 localFF8 = FFq.a(paramContext).b();
    long l1 = localFF8.t();
    long l2 = localFF8.u();
    long l3 = System.currentTimeMillis();
    Object localObject = a(paramContext);
    if (((ArrayList)localObject).size() == 0) {
      return;
    }
    F6U localF6U = localFF8.an();
    if (localF6U == null) {
      localF6U = new F6U();
    }
    for (;;)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator1 = paramF62.b().iterator();
      label83:
      label515:
      for (;;)
      {
        F6L localF6L;
        if (localIterator1.hasNext())
        {
          localF6L = (F6L)localIterator1.next();
          Iterator localIterator2 = ((ArrayList)localObject).iterator();
          do
          {
            if (!localIterator2.hasNext()) {
              break;
            }
          } while (!((String)localIterator2.next()).equalsIgnoreCase(localF6L.a()));
        }
        for (int i = 1;; i = 0)
        {
          if ((i != 0) || (localF6L.a().equals(paramContext.getPackageName()))) {
            break label515;
          }
          localArrayList.add(localF6L.a());
          break label83;
          i = 1;
          if (l1 == 0L) {
            i = 0;
          }
          while (i != 0)
          {
            localFF8.b(l3);
            paramF62 = paramF62.b().iterator();
            while (paramF62.hasNext())
            {
              localObject = (F6L)paramF62.next();
              if ((!((F6L)localObject).a().equals(paramContext.getPackageName())) && (((F6L)localObject).d() != 0L) && (l3 - ((F6L)localObject).d() > l1))
              {
                localArrayList.add(((F6L)localObject).a());
                FFl.e(a, "Client timeout -  pack.getName()" + ((F6L)localObject).a() + ", currentTime " + l3 + ", pack.getTimeStamp(): " + ((F6L)localObject).d() + ", MAX_TIMEOUT: " + l1);
              }
            }
            if (l3 - l2 > l1)
            {
              FFl.a(a, "over maxtimeout, cant determine dead clients");
              localFF8.b(l3);
              if ((paramF62 != null) && (paramF62.b() != null))
              {
                localObject = paramF62.b().iterator();
                while (((Iterator)localObject).hasNext()) {
                  ((F6L)((Iterator)localObject).next()).a(l3);
                }
                localFF8.a(paramF62);
              }
              i = 0;
            }
          }
          i = 0;
          if (localArrayList.size() != localF6U.a().size()) {
            i = 1;
          }
          while (i != 0)
          {
            localF6U.a(localArrayList);
            localF6U.a(localF6U.b() + 1);
            localFF8.a(localF6U);
            return;
            paramContext = new ArrayList(localArrayList);
            paramContext.removeAll(localF6U.a());
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
