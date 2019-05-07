package com.tencent.beacon.e;

import android.content.Context;
import com.tencent.beacon.b.a;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

public final class f
{
  private static f a = null;
  private String b = "";
  private String c = "";
  private Boolean d = Boolean.valueOf(false);
  private int e = 0;
  private Context f;
  
  private f(Context paramContext)
  {
    b.b("start excute SimHashUtils method...", new Object[0]);
    this.f = paramContext;
    paramContext = e();
    if (paramContext.size() > 0)
    {
      Collections.sort(paramContext, String.CASE_INSENSITIVE_ORDER);
      paramContext = a(paramContext);
      String str = a.d(paramContext);
      this.c = str;
      b.b("get appList md5 Str:" + str, new Object[0]);
      paramContext = new a(paramContext, 64);
      this.b = String.valueOf(paramContext.a);
      b.b("get appList finger:" + String.valueOf(paramContext.a), new Object[0]);
    }
  }
  
  public static f a(Context paramContext)
  {
    try
    {
      if (a == null) {
        a = new f(paramContext);
      }
      paramContext = a;
      return paramContext;
    }
    finally {}
  }
  
  private static String a(List<String> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < paramList.size())
    {
      if (i == paramList.size() - 1) {
        localStringBuilder.append((String)paramList.get(i));
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append((String)paramList.get(i)).append(" ");
      }
    }
    return localStringBuilder.toString();
  }
  
  /* Error */
  private List<String> e()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: getfield 51	com/tencent/beacon/e/f:f	Landroid/content/Context;
    //   6: ifnonnull +14 -> 20
    //   9: ldc 122
    //   11: iconst_0
    //   12: anewarray 4	java/lang/Object
    //   15: invokestatic 124	com/tencent/beacon/e/b:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   18: aconst_null
    //   19: areturn
    //   20: aload_0
    //   21: getfield 51	com/tencent/beacon/e/f:f	Landroid/content/Context;
    //   24: invokevirtual 130	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   27: astore_2
    //   28: aload_2
    //   29: ifnull -11 -> 18
    //   32: aload_2
    //   33: iconst_0
    //   34: invokevirtual 136	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   37: astore_3
    //   38: aload_3
    //   39: ifnull -21 -> 18
    //   42: aload_3
    //   43: invokeinterface 60 1 0
    //   48: ifle -30 -> 18
    //   51: aload_0
    //   52: iconst_1
    //   53: invokestatic 38	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   56: putfield 40	com/tencent/beacon/e/f:d	Ljava/lang/Boolean;
    //   59: aload_0
    //   60: aload_3
    //   61: invokeinterface 60 1 0
    //   66: putfield 42	com/tencent/beacon/e/f:e	I
    //   69: new 82	java/lang/StringBuilder
    //   72: dup
    //   73: ldc -118
    //   75: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   78: aload_3
    //   79: invokeinterface 60 1 0
    //   84: invokevirtual 141	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   87: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   90: iconst_0
    //   91: anewarray 4	java/lang/Object
    //   94: invokestatic 49	com/tencent/beacon/e/b:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   97: new 143	java/util/ArrayList
    //   100: dup
    //   101: invokespecial 144	java/util/ArrayList:<init>	()V
    //   104: astore_2
    //   105: aload_3
    //   106: invokeinterface 148 1 0
    //   111: astore_1
    //   112: aload_1
    //   113: invokeinterface 154 1 0
    //   118: ifeq +49 -> 167
    //   121: aload_2
    //   122: aload_1
    //   123: invokeinterface 158 1 0
    //   128: checkcast 160	android/content/pm/PackageInfo
    //   131: getfield 163	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   134: invokeinterface 167 2 0
    //   139: pop
    //   140: goto -28 -> 112
    //   143: astore_3
    //   144: aload_2
    //   145: astore_1
    //   146: aload_3
    //   147: astore_2
    //   148: aload_2
    //   149: invokevirtual 170	java/lang/Exception:printStackTrace	()V
    //   152: ldc -84
    //   154: iconst_0
    //   155: anewarray 4	java/lang/Object
    //   158: invokestatic 124	com/tencent/beacon/e/b:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   161: aload_1
    //   162: areturn
    //   163: astore_2
    //   164: goto -16 -> 148
    //   167: aload_2
    //   168: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	this	f
    //   1	161	1	localObject1	Object
    //   27	122	2	localObject2	Object
    //   163	5	2	localException1	Exception
    //   37	69	3	localList	List
    //   143	4	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   105	112	143	java/lang/Exception
    //   112	140	143	java/lang/Exception
    //   20	28	163	java/lang/Exception
    //   32	38	163	java/lang/Exception
    //   42	105	163	java/lang/Exception
  }
  
  public final String a()
  {
    return this.b;
  }
  
  public final int b()
  {
    return this.e;
  }
  
  public final String c()
  {
    return this.c;
  }
  
  public final Boolean d()
  {
    return this.d;
  }
  
  final class a
  {
    public BigInteger a;
    private String b;
    private int c = 64;
    
    public a(String paramString, int paramInt)
    {
      this.b = paramString;
      this.c = 64;
      this.a = a();
    }
    
    private BigInteger a()
    {
      int k = 0;
      int[] arrayOfInt = new int[this.c];
      Object localObject1 = this.b;
      Object localObject2 = this.b.split(" ");
      StringBuilder localStringBuilder;
      int i;
      if (localObject2.length <= 3)
      {
        localStringBuilder = new StringBuilder();
        i = 0;
        if (i < localObject2.length)
        {
          if (i == localObject2.length - 1) {
            localStringBuilder.append(localObject2[i]);
          }
          for (;;)
          {
            i += 1;
            break;
            localStringBuilder.append(localObject2[i]).append(" ");
          }
        }
        localObject1 = new String[1];
        localObject1[0] = localStringBuilder.toString();
      }
      for (;;)
      {
        i = 0;
        while (i < localObject1.length)
        {
          localObject2 = a(localObject1[i]);
          int j = 0;
          if (j < this.c)
          {
            if (((BigInteger)localObject2).and(new BigInteger("1").shiftLeft(j)).signum() != 0) {
              arrayOfInt[j] += 1;
            }
            for (;;)
            {
              j += 1;
              break;
              localObject1 = new String[localObject2.length - 3 + 1];
              i = 0;
              while (i <= localObject2.length - 3)
              {
                localStringBuilder = new StringBuilder();
                j = 0;
                if (j < 3)
                {
                  if (j == 2) {
                    localStringBuilder.append(localObject2[(i + j)]);
                  }
                  for (;;)
                  {
                    j += 1;
                    break;
                    localStringBuilder.append(localObject2[(i + j)]).append(" ");
                  }
                }
                localObject1[i] = localStringBuilder.toString();
                i += 1;
              }
              arrayOfInt[j] -= 1;
            }
          }
          i += 1;
        }
        localObject1 = new BigInteger("0");
        localObject2 = new StringBuffer();
        i = k;
        if (i < this.c)
        {
          if (arrayOfInt[i] >= 0)
          {
            localObject1 = ((BigInteger)localObject1).add(new BigInteger("1").shiftLeft(i));
            ((StringBuffer)localObject2).append("1");
          }
          for (;;)
          {
            i += 1;
            break;
            ((StringBuffer)localObject2).append("0");
          }
        }
        ((StringBuffer)localObject2).toString();
        return localObject1;
      }
    }
    
    private BigInteger a(String paramString)
    {
      int i = 0;
      if ((paramString == null) || (paramString.length() == 0)) {
        paramString = new BigInteger("0");
      }
      BigInteger localBigInteger1;
      do
      {
        return paramString;
        char[] arrayOfChar = paramString.toCharArray();
        localBigInteger1 = BigInteger.valueOf(arrayOfChar[0] << 7);
        BigInteger localBigInteger2 = new BigInteger("1000003");
        BigInteger localBigInteger3 = new BigInteger("2").pow(this.c).subtract(new BigInteger("1"));
        int j = arrayOfChar.length;
        while (i < j)
        {
          BigInteger localBigInteger4 = BigInteger.valueOf(arrayOfChar[i]);
          localBigInteger1 = localBigInteger1.multiply(localBigInteger2).xor(localBigInteger4).and(localBigInteger3);
          i += 1;
        }
        localBigInteger1 = localBigInteger1.xor(new BigInteger(String.valueOf(paramString.length())));
        paramString = localBigInteger1;
      } while (!localBigInteger1.equals(new BigInteger("-1")));
      return new BigInteger("-2");
    }
  }
}
