package c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.calldorado.android.CalldoradoApplication;
import com.calldorado.android.ClientConfig;
import com.calldorado.android.s7b;
import com.calldorado.data.F4;
import com.calldorado.data._L;
import com.calldorado.data.pCi;
import com.calldorado.util.mQb;
import java.lang.reflect.Method;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClM
{
  private static final String a = ClM.class.getSimpleName();
  private static final byte[] b;
  private static int c;
  
  static {}
  
  public ClM() {}
  
  private static String a(short paramShort, int paramInt, byte paramByte)
  {
    short s1 = 25 - paramShort * 22;
    int j = paramInt * 9 + 14;
    byte[] arrayOfByte1 = b;
    paramByte = 103 - paramByte * 6;
    byte[] arrayOfByte2 = new byte[j];
    if (arrayOfByte1 == null)
    {
      paramInt = 0;
      paramShort = s1;
      paramByte = -paramByte + s1 - 8;
      s1 = paramShort;
      paramShort = paramByte;
    }
    for (;;)
    {
      short s2 = s1 + 1;
      int i = paramInt + 1;
      arrayOfByte2[paramInt] = ((byte)paramShort);
      if (i == j) {
        return new String(arrayOfByte2, 0);
      }
      paramByte = arrayOfByte1[s2];
      s1 = paramShort;
      paramInt = i;
      paramShort = s2;
      break;
      paramShort = paramByte;
      paramInt = 0;
    }
  }
  
  /* Error */
  private static ArrayList<String> a()
  {
    // Byte code:
    //   0: new 41	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 42	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: aconst_null
    //   9: astore_0
    //   10: invokestatic 48	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: ldc 50
    //   15: invokevirtual 54	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   18: astore_2
    //   19: new 56	java/io/BufferedReader
    //   22: dup
    //   23: new 58	java/io/InputStreamReader
    //   26: dup
    //   27: aload_2
    //   28: invokevirtual 64	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   31: invokespecial 67	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   34: invokespecial 70	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   37: astore_1
    //   38: aload_1
    //   39: astore_0
    //   40: aload_1
    //   41: invokevirtual 73	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   44: astore 4
    //   46: aload 4
    //   48: ifnull +44 -> 92
    //   51: aload_1
    //   52: astore_0
    //   53: aload_3
    //   54: aload 4
    //   56: aload 4
    //   58: bipush 58
    //   60: invokevirtual 77	java/lang/String:indexOf	(I)I
    //   63: iconst_1
    //   64: iadd
    //   65: invokevirtual 81	java/lang/String:substring	(I)Ljava/lang/String;
    //   68: invokevirtual 87	java/util/AbstractCollection:add	(Ljava/lang/Object;)Z
    //   71: pop
    //   72: goto -34 -> 38
    //   75: astore_2
    //   76: aload_1
    //   77: astore_0
    //   78: aload_2
    //   79: invokevirtual 92	java/lang/Throwable:printStackTrace	()V
    //   82: aload_1
    //   83: ifnull +7 -> 90
    //   86: aload_1
    //   87: invokevirtual 97	java/io/Reader:close	()V
    //   90: aload_3
    //   91: areturn
    //   92: aload_1
    //   93: astore_0
    //   94: aload_2
    //   95: invokevirtual 101	java/lang/Process:waitFor	()I
    //   98: pop
    //   99: aload_1
    //   100: invokevirtual 97	java/io/Reader:close	()V
    //   103: aload_3
    //   104: areturn
    //   105: astore_0
    //   106: aload_0
    //   107: invokevirtual 92	java/lang/Throwable:printStackTrace	()V
    //   110: aload_3
    //   111: areturn
    //   112: astore_0
    //   113: aload_0
    //   114: invokevirtual 92	java/lang/Throwable:printStackTrace	()V
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
    //   129: invokevirtual 97	java/io/Reader:close	()V
    //   132: aload_0
    //   133: athrow
    //   134: astore_1
    //   135: aload_1
    //   136: invokevirtual 92	java/lang/Throwable:printStackTrace	()V
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
      while (((Iterator)localObject).hasNext()) {
        paramContext.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
      }
      mQb.b(a, new StringBuilder("Packages installed: ").append(paramContext.toString()).toString());
    }
    catch (Exception paramContext)
    {
      s7b.e(a, "Using fallback package getter");
      paramContext.printStackTrace();
      return a();
    }
    return paramContext;
  }
  
  public static void a(Context paramContext, F4 paramF4)
  {
    ClientConfig localClientConfig = CalldoradoApplication.b(paramContext).h();
    long l1 = localClientConfig.j();
    long l2 = localClientConfig.k();
    long l3 = System.currentTimeMillis();
    Object localObject1 = a(paramContext);
    if (((AbstractCollection)localObject1).size() == 0) {
      return;
    }
    pCi localPCi = localClientConfig.aj();
    if (localPCi == null) {
      localPCi = new pCi();
    }
    for (;;)
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject2 = paramF4.b().iterator();
      label82:
      label562:
      label564:
      label654:
      for (;;)
      {
        Object localObject3;
        Object localObject4;
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (_L)((Iterator)localObject2).next();
          localObject4 = ((ArrayList)localObject1).iterator();
          do
          {
            if (!((Iterator)localObject4).hasNext()) {
              break;
            }
          } while (!((String)((Iterator)localObject4).next()).equalsIgnoreCase(((_L)localObject3).a()));
        }
        for (int i = 1;; i = 0)
        {
          if (i != 0) {
            break label654;
          }
          localObject4 = ((_L)localObject3).a();
          short s;
          try
          {
            s = (byte)(c & 0x3);
            i = (byte)s;
            Object localObject5 = Class.forName(a(s, i, (byte)i));
            s = (byte)b[38];
            i = (byte)s;
            localObject5 = ((Class)localObject5).getMethod(a(s, i, (byte)i), null).invoke(paramContext, null);
            if (localObject4.equals(localObject5)) {
              break label82;
            }
            localArrayList.add(((_L)localObject3).a());
            break label82;
          }
          finally
          {
            paramF4 = paramContext.getCause();
            if (paramF4 != null) {
              throw paramF4;
            }
          }
          i = 1;
          if (l1 == 0L) {
            i = 0;
          }
          for (;;)
          {
            if (i == 0) {
              break label564;
            }
            localClientConfig.b(l3);
            paramF4 = paramF4.b().iterator();
            while (paramF4.hasNext())
            {
              localObject1 = (_L)paramF4.next();
              localObject2 = ((_L)localObject1).a();
              try
              {
                s = (byte)(c & 0x3);
                i = (byte)s;
                localObject3 = Class.forName(a(s, i, (byte)i));
                s = (byte)b[38];
                i = (byte)s;
                localObject3 = ((Class)localObject3).getMethod(a(s, i, (byte)i), null).invoke(paramContext, null);
                if ((!localObject2.equals(localObject3)) && (((_L)localObject1).d() != 0L) && (l3 - ((_L)localObject1).d() > l1))
                {
                  localArrayList.add(((_L)localObject1).a());
                  s7b.e(a, new StringBuilder("Client timeout -  pack.getId()").append(((_L)localObject1).a()).append(", currentTime ").append(l3).append(", pack.getTimeStamp(): ").append(((_L)localObject1).d()).append(", MAX_TIMEOUT: ").append(l1).toString());
                }
              }
              finally
              {
                paramF4 = paramContext.getCause();
                if (paramF4 == null) {
                  break label562;
                }
                throw paramF4;
              }
            }
            if (l3 - l2 > l1)
            {
              s7b.a(a, "over maxtimeout, cant determine dead clients");
              localClientConfig.b(l3);
              if ((paramF4 != null) && (paramF4.b() != null))
              {
                localObject1 = paramF4.b().iterator();
                while (((Iterator)localObject1).hasNext()) {
                  ((_L)((Iterator)localObject1).next()).a(l3);
                }
                localClientConfig.a(paramF4);
              }
              i = 0;
            }
          }
          i = 0;
          if (localArrayList.size() != localPCi.a().size()) {
            i = 1;
          }
          while (i != 0)
          {
            localPCi.a(localArrayList);
            localPCi.a(localPCi.b() + 1);
            localClientConfig.a(localPCi);
            return;
            paramContext = new ArrayList(localArrayList);
            paramContext.removeAll(localPCi.a());
            if (paramContext.size() != 0) {
              i = 1;
            }
          }
          break;
        }
      }
    }
  }
  
  private static void b()
  {
    b = new byte[] { 67, 105, 99, 84, -21, 2, -22, -5, -2, -3, 46, -61, -20, -7, -14, 7, -17, -14, 62, -29, -52, -7, -14, 7, -27, -4, -6, -23, 28, -25, -10, -16, 2, -14, -6, 15, -27, -20, 0 };
    c = 253;
  }
}
