package com;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public final class bhw
{
  private static bho a;
  private static String b = "";
  
  static bho a(Context paramContext)
  {
    if (a != null) {
      return a;
    }
    String str2 = bht.a(paramContext);
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!"".equals(str2)) {}
    }
    else
    {
      str1 = b(paramContext);
    }
    try
    {
      a = (bho)new Gson().fromJson(str1, bho.class);
      return a;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static String a()
  {
    Object localObject1 = bhn.c();
    try
    {
      Object localObject2 = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject2).update(((String)localObject1).getBytes("UTF-8"));
      localObject1 = ((MessageDigest)localObject2).digest();
      localObject2 = new StringBuilder();
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        String str = Integer.toHexString(localObject1[i] & 0xFF);
        if (str.length() == 1) {
          ((StringBuilder)localObject2).append("0");
        }
        ((StringBuilder)localObject2).append(str);
        i += 1;
      }
      localObject1 = ((StringBuilder)localObject2).toString();
      return localObject1;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;) {}
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;) {}
    }
    return "";
    return "";
  }
  
  public static void a(String paramString)
  {
    b = paramString;
  }
  
  static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  static String b(Context paramContext)
  {
    Object localObject = d(paramContext);
    if ((localObject != null) && (!"".equals(localObject)))
    {
      paramContext = a();
      localObject = ((String)localObject).toCharArray();
      paramContext = paramContext.toCharArray();
      int j = 0;
      int i = j;
      while (j < localObject.length)
      {
        localObject[j] = ((char)(localObject[j] ^ paramContext[i]));
        int k = i + 1;
        i = k;
        if (k == paramContext.length) {
          i = 0;
        }
        j += 1;
      }
      return String.valueOf((char[])localObject);
    }
    return null;
  }
  
  static boolean c(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null) {
        return paramContext.isAvailable();
      }
    }
    return false;
  }
  
  /* Error */
  private static String d(Context paramContext)
  {
    // Byte code:
    //   0: new 171	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 172	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore 4
    //   9: sipush 1024
    //   12: newarray byte
    //   14: astore 5
    //   16: aconst_null
    //   17: astore_3
    //   18: aconst_null
    //   19: astore_2
    //   20: aload_0
    //   21: invokevirtual 176	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   24: getstatic 99	com/bhw:b	Ljava/lang/String;
    //   27: invokevirtual 182	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   30: astore_0
    //   31: aload_0
    //   32: aload 5
    //   34: invokevirtual 188	java/io/InputStream:read	([B)I
    //   37: istore_1
    //   38: iload_1
    //   39: iconst_m1
    //   40: if_icmpeq +15 -> 55
    //   43: aload 4
    //   45: aload 5
    //   47: iconst_0
    //   48: iload_1
    //   49: invokevirtual 192	java/io/ByteArrayOutputStream:write	([BII)V
    //   52: goto -21 -> 31
    //   55: aload 4
    //   57: ldc 62
    //   59: invokevirtual 195	java/io/ByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
    //   62: astore_2
    //   63: aload 4
    //   65: invokevirtual 198	java/io/ByteArrayOutputStream:close	()V
    //   68: aload_0
    //   69: ifnull +14 -> 83
    //   72: aload_0
    //   73: invokevirtual 199	java/io/InputStream:close	()V
    //   76: aload_2
    //   77: areturn
    //   78: astore_0
    //   79: aload_0
    //   80: invokevirtual 202	java/io/IOException:printStackTrace	()V
    //   83: aload_2
    //   84: areturn
    //   85: astore_2
    //   86: goto +50 -> 136
    //   89: astore_3
    //   90: goto +16 -> 106
    //   93: astore_3
    //   94: aload_2
    //   95: astore_0
    //   96: aload_3
    //   97: astore_2
    //   98: goto +38 -> 136
    //   101: astore_2
    //   102: aload_3
    //   103: astore_0
    //   104: aload_2
    //   105: astore_3
    //   106: aload_0
    //   107: astore_2
    //   108: aload_3
    //   109: invokevirtual 202	java/io/IOException:printStackTrace	()V
    //   112: aload 4
    //   114: invokevirtual 198	java/io/ByteArrayOutputStream:close	()V
    //   117: aload_0
    //   118: ifnull +15 -> 133
    //   121: aload_0
    //   122: invokevirtual 199	java/io/InputStream:close	()V
    //   125: goto +8 -> 133
    //   128: astore_0
    //   129: aload_0
    //   130: invokevirtual 202	java/io/IOException:printStackTrace	()V
    //   133: ldc 10
    //   135: areturn
    //   136: aload 4
    //   138: invokevirtual 198	java/io/ByteArrayOutputStream:close	()V
    //   141: aload_0
    //   142: ifnull +15 -> 157
    //   145: aload_0
    //   146: invokevirtual 199	java/io/InputStream:close	()V
    //   149: goto +8 -> 157
    //   152: astore_0
    //   153: aload_0
    //   154: invokevirtual 202	java/io/IOException:printStackTrace	()V
    //   157: aload_2
    //   158: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	paramContext	Context
    //   37	12	1	i	int
    //   19	65	2	str	String
    //   85	10	2	localObject1	Object
    //   97	1	2	localObject2	Object
    //   101	4	2	localIOException1	java.io.IOException
    //   107	51	2	localContext	Context
    //   17	1	3	localObject3	Object
    //   89	1	3	localIOException2	java.io.IOException
    //   93	10	3	localObject4	Object
    //   105	4	3	localObject5	Object
    //   7	130	4	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   14	32	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   63	68	78	java/io/IOException
    //   72	76	78	java/io/IOException
    //   31	38	85	finally
    //   43	52	85	finally
    //   55	63	85	finally
    //   31	38	89	java/io/IOException
    //   43	52	89	java/io/IOException
    //   55	63	89	java/io/IOException
    //   20	31	93	finally
    //   108	112	93	finally
    //   20	31	101	java/io/IOException
    //   112	117	128	java/io/IOException
    //   121	125	128	java/io/IOException
    //   136	141	152	java/io/IOException
    //   145	149	152	java/io/IOException
  }
}
