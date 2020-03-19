package c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.calldorado.android.CalldoradoApplication;
import com.calldorado.android.ClientConfig;
import com.calldorado.android.e8T;
import com.calldorado.data.CUM;
import com.calldorado.data.Dnc;
import com.calldorado.data.ICd;
import com.calldorado.util.cAJ;
import java.lang.reflect.Method;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MnS
{
  private static final byte[] DAm;
  private static final String e8T = "MnS";
  private static int le9;
  
  static {}
  
  public MnS() {}
  
  private static String DAm(byte paramByte, int paramInt1, int paramInt2)
  {
    int i = 6 * paramInt2 + 97;
    byte[] arrayOfByte1 = DAm;
    paramByte = 9 * paramByte + 14;
    paramInt1 = 26 - 22 * paramInt1;
    byte[] arrayOfByte2 = new byte[paramByte];
    paramByte -= 1;
    int j;
    int k;
    if (arrayOfByte1 == null)
    {
      paramInt2 = paramByte;
      int m = 0;
      j = i;
      k = paramByte;
      i = paramInt1;
      paramInt1 = m;
    }
    else
    {
      j = 0;
      paramInt2 = paramInt1;
      paramInt1 = paramByte;
      paramByte = i;
    }
    for (;;)
    {
      arrayOfByte2[j] = ((byte)paramByte);
      if (j == paramInt1) {
        return new String(arrayOfByte2, 0);
      }
      k = arrayOfByte1[paramInt2];
      j += 1;
      i = paramInt2;
      paramInt2 = paramInt1;
      paramInt1 = j;
      j = paramByte;
      paramByte = j + -k - 8;
      i += 1;
      j = paramInt1;
      paramInt1 = paramInt2;
      paramInt2 = i;
    }
  }
  
  /* Error */
  private static ArrayList<String> DAm()
  {
    // Byte code:
    //   0: new 35	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 36	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore_1
    //   13: aload_1
    //   14: astore_0
    //   15: invokestatic 42	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   18: ldc 44
    //   20: invokevirtual 48	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   23: astore_2
    //   24: aload_1
    //   25: astore_0
    //   26: new 50	java/io/BufferedReader
    //   29: dup
    //   30: new 52	java/io/InputStreamReader
    //   33: dup
    //   34: aload_2
    //   35: invokevirtual 58	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   38: invokespecial 61	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   41: invokespecial 64	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   44: astore_1
    //   45: aload_1
    //   46: invokevirtual 68	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   49: astore_0
    //   50: aload_0
    //   51: ifnull +24 -> 75
    //   54: aload 4
    //   56: aload_0
    //   57: aload_0
    //   58: bipush 58
    //   60: invokevirtual 72	java/lang/String:indexOf	(I)I
    //   63: iconst_1
    //   64: iadd
    //   65: invokevirtual 76	java/lang/String:substring	(I)Ljava/lang/String;
    //   68: invokevirtual 82	java/util/AbstractCollection:add	(Ljava/lang/Object;)Z
    //   71: pop
    //   72: goto -27 -> 45
    //   75: aload_2
    //   76: invokevirtual 86	java/lang/Process:waitFor	()I
    //   79: pop
    //   80: aload_1
    //   81: invokevirtual 91	java/io/Reader:close	()V
    //   84: aload 4
    //   86: areturn
    //   87: astore_2
    //   88: aload_1
    //   89: astore_0
    //   90: aload_2
    //   91: astore_1
    //   92: goto +41 -> 133
    //   95: astore_0
    //   96: aload_0
    //   97: astore_2
    //   98: goto +10 -> 108
    //   101: astore_1
    //   102: goto +31 -> 133
    //   105: astore_2
    //   106: aload_3
    //   107: astore_1
    //   108: aload_1
    //   109: astore_0
    //   110: aload_2
    //   111: invokevirtual 96	java/lang/Throwable:printStackTrace	()V
    //   114: aload_1
    //   115: ifnull +15 -> 130
    //   118: aload_1
    //   119: invokevirtual 91	java/io/Reader:close	()V
    //   122: aload 4
    //   124: areturn
    //   125: astore_0
    //   126: aload_0
    //   127: invokevirtual 96	java/lang/Throwable:printStackTrace	()V
    //   130: aload 4
    //   132: areturn
    //   133: aload_0
    //   134: ifnull +15 -> 149
    //   137: aload_0
    //   138: invokevirtual 91	java/io/Reader:close	()V
    //   141: goto +8 -> 149
    //   144: astore_0
    //   145: aload_0
    //   146: invokevirtual 96	java/lang/Throwable:printStackTrace	()V
    //   149: aload_1
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   14	76	0	localObject1	Object
    //   95	2	0	localException1	Exception
    //   109	1	0	localObject2	Object
    //   125	13	0	localIOException1	java.io.IOException
    //   144	2	0	localIOException2	java.io.IOException
    //   12	80	1	localObject3	Object
    //   101	1	1	localObject4	Object
    //   107	43	1	localObject5	Object
    //   23	53	2	localProcess	Process
    //   87	4	2	localObject6	Object
    //   97	1	2	localException2	Exception
    //   105	6	2	localException3	Exception
    //   10	97	3	localObject7	Object
    //   7	124	4	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   45	50	87	finally
    //   54	72	87	finally
    //   75	80	87	finally
    //   45	50	95	java/lang/Exception
    //   54	72	95	java/lang/Exception
    //   75	80	95	java/lang/Exception
    //   15	24	101	finally
    //   26	45	101	finally
    //   110	114	101	finally
    //   15	24	105	java/lang/Exception
    //   26	45	105	java/lang/Exception
    //   80	84	125	java/io/IOException
    //   118	122	125	java/io/IOException
    //   137	141	144	java/io/IOException
  }
  
  private static ArrayList<String> DAm(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
      paramContext = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramContext.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
      }
      localObject = e8T;
      StringBuilder localStringBuilder = new StringBuilder("Packages installed: ");
      localStringBuilder.append(paramContext.toString());
      cAJ.b((String)localObject, localStringBuilder.toString());
      return paramContext;
    }
    catch (Exception paramContext)
    {
      e8T.e(e8T, "Using fallback package getter");
      paramContext.printStackTrace();
    }
    return DAm();
  }
  
  public static void F9o(Context paramContext, ICd paramICd)
  {
    ClientConfig localClientConfig = CalldoradoApplication.b(paramContext).h();
    long l1 = localClientConfig.j();
    long l2 = localClientConfig.k();
    long l3 = System.currentTimeMillis();
    Object localObject4 = DAm(paramContext);
    if (((AbstractCollection)localObject4).size() == 0) {
      return;
    }
    Object localObject1 = localClientConfig.aj();
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = new Dnc();
    }
    ArrayList localArrayList = new ArrayList();
    localObject1 = paramICd.b().iterator();
    Object localObject3 = localObject2;
    localObject2 = localObject4;
    int j;
    int i;
    byte b;
    for (;;)
    {
      boolean bool = ((Iterator)localObject1).hasNext();
      j = 0;
      i = 0;
      if (!bool) {
        break;
      }
      localObject4 = (CUM)((Iterator)localObject1).next();
      Object localObject5 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject5).hasNext()) {
        if (((String)((Iterator)localObject5).next()).equalsIgnoreCase(((CUM)localObject4).a()))
        {
          i = 1;
          break;
        }
      }
      if (i == 0)
      {
        localObject5 = ((CUM)localObject4).a();
        try
        {
          b = (byte)(le9 & 0x5);
          Object localObject6 = Class.forName(DAm(b, b, (byte)DAm[38]));
          b = (byte)DAm[38];
          i = b;
          localObject6 = ((Class)localObject6).getMethod(DAm(b, i, (byte)(i + 1)), null).invoke(paramContext, null);
          if (!localObject5.equals(localObject6)) {
            localArrayList.add(((CUM)localObject4).a());
          }
        }
        finally
        {
          paramICd = paramContext.getCause();
          if (paramICd != null) {
            throw paramICd;
          }
        }
      }
    }
    if (l1 == 0L)
    {
      i = 0;
    }
    else if (l3 - l2 > l1)
    {
      e8T.a(e8T, "over maxtimeout, cant determine dead clients");
      localClientConfig.b(l3);
      if ((paramICd != null) && (paramICd.b() != null))
      {
        localObject1 = paramICd.b().iterator();
        while (((Iterator)localObject1).hasNext()) {
          ((CUM)((Iterator)localObject1).next()).a(l3);
        }
        localClientConfig.a(paramICd);
      }
      i = 0;
    }
    else
    {
      i = 1;
    }
    if (i != 0)
    {
      localClientConfig.b(l3);
      paramICd = paramICd.b().iterator();
      while (paramICd.hasNext())
      {
        localObject1 = (CUM)paramICd.next();
        localObject2 = ((CUM)localObject1).a();
        try
        {
          b = (byte)(le9 & 0x5);
          localObject4 = Class.forName(DAm(b, b, (byte)DAm[38]));
          b = (byte)DAm[38];
          i = b;
          localObject4 = ((Class)localObject4).getMethod(DAm(b, i, (byte)(i + 1)), null).invoke(paramContext, null);
          if ((!localObject2.equals(localObject4)) && (((CUM)localObject1).d() != 0L) && (l3 - ((CUM)localObject1).d() > l1))
          {
            localArrayList.add(((CUM)localObject1).a());
            localObject2 = e8T;
            localObject4 = new StringBuilder("Client timeout -  pack.getId()");
            ((StringBuilder)localObject4).append(((CUM)localObject1).a());
            ((StringBuilder)localObject4).append(", currentTime ");
            ((StringBuilder)localObject4).append(l3);
            ((StringBuilder)localObject4).append(", pack.getTimeStamp(): ");
            ((StringBuilder)localObject4).append(((CUM)localObject1).d());
            ((StringBuilder)localObject4).append(", MAX_TIMEOUT: ");
            ((StringBuilder)localObject4).append(l1);
            e8T.e((String)localObject2, localObject4.toString());
          }
        }
        finally
        {
          paramICd = paramContext.getCause();
          if (paramICd != null) {
            throw paramICd;
          }
        }
      }
    }
    if (localArrayList.size() != localObject3.a().size()) {}
    do
    {
      i = 1;
      break;
      paramContext = new ArrayList(localArrayList);
      paramContext.removeAll(localObject3.a());
      i = j;
    } while (paramContext.size() != 0);
    if (i != 0)
    {
      localObject3.a(localArrayList);
      localObject3.a(localObject3.b() + 1);
      localClientConfig.a(localObject3);
    }
  }
  
  private static void e8T()
  {
    DAm = new byte[] { 104, -42, 28, 110, -21, 2, -22, -5, -2, -3, 46, -61, -20, -7, -14, 7, -17, -14, 62, -29, -52, -7, -14, 7, -27, -4, -6, -23, 28, -25, -10, -16, 2, -14, -6, 15, -27, -20, 0 };
    le9 = 203;
  }
}
