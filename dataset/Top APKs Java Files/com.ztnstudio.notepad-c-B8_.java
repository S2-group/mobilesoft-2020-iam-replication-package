package c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.calldorado.android.CalldoradoApplication;
import com.calldorado.android.ClientConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class B8_
{
  private static final String a = "B8_";
  
  public B8_() {}
  
  /* Error */
  private ArrayList<String> a()
  {
    // Byte code:
    //   0: new 21	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 22	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aconst_null
    //   10: astore 4
    //   12: aconst_null
    //   13: astore_2
    //   14: aload_2
    //   15: astore_1
    //   16: invokestatic 28	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   19: ldc 30
    //   21: invokevirtual 34	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   24: astore_3
    //   25: aload_2
    //   26: astore_1
    //   27: new 36	java/io/BufferedReader
    //   30: dup
    //   31: new 38	java/io/InputStreamReader
    //   34: dup
    //   35: aload_3
    //   36: invokevirtual 44	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   39: invokespecial 47	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   42: invokespecial 50	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   45: astore_2
    //   46: aload_2
    //   47: invokevirtual 54	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   50: astore_1
    //   51: aload_1
    //   52: ifnull +24 -> 76
    //   55: aload 5
    //   57: aload_1
    //   58: aload_1
    //   59: bipush 58
    //   61: invokevirtual 60	java/lang/String:indexOf	(I)I
    //   64: iconst_1
    //   65: iadd
    //   66: invokevirtual 64	java/lang/String:substring	(I)Ljava/lang/String;
    //   69: invokevirtual 68	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   72: pop
    //   73: goto -27 -> 46
    //   76: aload_3
    //   77: invokevirtual 72	java/lang/Process:waitFor	()I
    //   80: pop
    //   81: aload_2
    //   82: invokevirtual 75	java/io/BufferedReader:close	()V
    //   85: aload 5
    //   87: areturn
    //   88: astore_3
    //   89: aload_2
    //   90: astore_1
    //   91: aload_3
    //   92: astore_2
    //   93: goto +42 -> 135
    //   96: astore_1
    //   97: aload_1
    //   98: astore_3
    //   99: goto +11 -> 110
    //   102: astore_2
    //   103: goto +32 -> 135
    //   106: astore_3
    //   107: aload 4
    //   109: astore_2
    //   110: aload_2
    //   111: astore_1
    //   112: aload_3
    //   113: invokevirtual 78	java/lang/Exception:printStackTrace	()V
    //   116: aload_2
    //   117: ifnull +15 -> 132
    //   120: aload_2
    //   121: invokevirtual 75	java/io/BufferedReader:close	()V
    //   124: aload 5
    //   126: areturn
    //   127: astore_1
    //   128: aload_1
    //   129: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   132: aload 5
    //   134: areturn
    //   135: aload_1
    //   136: ifnull +15 -> 151
    //   139: aload_1
    //   140: invokevirtual 75	java/io/BufferedReader:close	()V
    //   143: goto +8 -> 151
    //   146: astore_1
    //   147: aload_1
    //   148: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   151: aload_2
    //   152: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	153	0	this	B8_
    //   15	76	1	localObject1	Object
    //   96	2	1	localException1	Exception
    //   111	1	1	localObject2	Object
    //   127	13	1	localIOException1	java.io.IOException
    //   146	2	1	localIOException2	java.io.IOException
    //   13	80	2	localObject3	Object
    //   102	1	2	localObject4	Object
    //   109	43	2	localObject5	Object
    //   24	53	3	localProcess	Process
    //   88	4	3	localObject6	Object
    //   98	1	3	localException2	Exception
    //   106	7	3	localException3	Exception
    //   10	98	4	localObject7	Object
    //   7	126	5	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   46	51	88	finally
    //   55	73	88	finally
    //   76	81	88	finally
    //   46	51	96	java/lang/Exception
    //   55	73	96	java/lang/Exception
    //   76	81	96	java/lang/Exception
    //   16	25	102	finally
    //   27	46	102	finally
    //   112	116	102	finally
    //   16	25	106	java/lang/Exception
    //   27	46	106	java/lang/Exception
    //   81	85	127	java/io/IOException
    //   120	124	127	java/io/IOException
    //   139	143	146	java/io/IOException
  }
  
  private ArrayList<String> a(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
      paramContext = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramContext.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
      }
      localObject = a;
      StringBuilder localStringBuilder = new StringBuilder("Packages installed: ");
      localStringBuilder.append(paramContext.toString());
      LZU.a((String)localObject, localStringBuilder.toString());
      return paramContext;
    }
    catch (Exception paramContext)
    {
      LZU.e(a, "Using fallback package getter");
      paramContext.printStackTrace();
    }
    return a();
  }
  
  public void a(Context paramContext, OY paramOY)
  {
    ClientConfig localClientConfig = CalldoradoApplication.a(paramContext).i();
    long l1 = localClientConfig.k();
    long l2 = localClientConfig.l();
    long l3 = System.currentTimeMillis();
    Object localObject3 = a(paramContext);
    if (((ArrayList)localObject3).size() == 0) {
      return;
    }
    Object localObject2 = localClientConfig.ao();
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new WFM();
    }
    localObject2 = new ArrayList();
    Object localObject4 = paramOY.b().iterator();
    Object localObject5;
    while (((Iterator)localObject4).hasNext())
    {
      localObject5 = (OOS)((Iterator)localObject4).next();
      Iterator localIterator = ((ArrayList)localObject3).iterator();
      while (localIterator.hasNext()) {
        if (((String)localIterator.next()).equalsIgnoreCase(((OOS)localObject5).a()))
        {
          i = 1;
          break label154;
        }
      }
      i = 0;
      label154:
      if ((i == 0) && (!((OOS)localObject5).a().equals(paramContext.getPackageName()))) {
        ((ArrayList)localObject2).add(((OOS)localObject5).a());
      }
    }
    if (l1 == 0L) {}
    for (;;)
    {
      i = 0;
      break label284;
      if (l3 - l2 <= l1) {
        break;
      }
      LZU.a(a, "over maxtimeout, cant determine dead clients");
      localClientConfig.b(l3);
      if ((paramOY != null) && (paramOY.b() != null))
      {
        localObject3 = paramOY.b().iterator();
        while (((Iterator)localObject3).hasNext()) {
          ((OOS)((Iterator)localObject3).next()).a(l3);
        }
        localClientConfig.a(paramOY);
      }
    }
    int i = 1;
    label284:
    if (i != 0)
    {
      localClientConfig.b(l3);
      paramOY = paramOY.b().iterator();
      while (paramOY.hasNext())
      {
        localObject3 = (OOS)paramOY.next();
        if ((!((OOS)localObject3).a().equals(paramContext.getPackageName())) && (((OOS)localObject3).d() != 0L) && (l3 - ((OOS)localObject3).d() > l1))
        {
          ((ArrayList)localObject2).add(((OOS)localObject3).a());
          localObject4 = a;
          localObject5 = new StringBuilder("Client timeout -  pack.getId()");
          ((StringBuilder)localObject5).append(((OOS)localObject3).a());
          ((StringBuilder)localObject5).append(", currentTime ");
          ((StringBuilder)localObject5).append(l3);
          ((StringBuilder)localObject5).append(", pack.getTimeStamp(): ");
          ((StringBuilder)localObject5).append(((OOS)localObject3).d());
          ((StringBuilder)localObject5).append(", MAX_TIMEOUT: ");
          ((StringBuilder)localObject5).append(l1);
          LZU.e((String)localObject4, ((StringBuilder)localObject5).toString());
        }
      }
    }
    if (((ArrayList)localObject2).size() != ((WFM)localObject1).a().size()) {}
    do
    {
      i = 1;
      break;
      paramContext = new ArrayList((Collection)localObject2);
      paramContext.removeAll(((WFM)localObject1).a());
    } while (paramContext.size() != 0);
    i = 0;
    if (i != 0)
    {
      ((WFM)localObject1).a((ArrayList)localObject2);
      ((WFM)localObject1).a(((WFM)localObject1).b() + 1);
      localClientConfig.a((WFM)localObject1);
    }
  }
}
