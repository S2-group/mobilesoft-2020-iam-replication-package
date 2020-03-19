package com.yxcrop.gifshow.d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Process;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class j
{
  private static final Random a = new Random(System.currentTimeMillis());
  private static String b;
  
  public static String a()
  {
    String str1 = Locale.getDefault().getLanguage();
    String str2 = Locale.getDefault().getCountry();
    Object localObject = str1;
    if (!TextUtils.isEmpty(str2))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append("-");
      ((StringBuilder)localObject).append(str2.toLowerCase());
      localObject = ((StringBuilder)localObject).toString();
    }
    return localObject;
  }
  
  /* Error */
  public static String a(java.io.File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +295 -> 296
    //   4: aload_0
    //   5: invokevirtual 73	java/io/File:isFile	()Z
    //   8: ifeq +288 -> 296
    //   11: aload_0
    //   12: invokevirtual 76	java/io/File:exists	()Z
    //   15: ifne +6 -> 21
    //   18: goto +278 -> 296
    //   21: aconst_null
    //   22: astore 6
    //   24: aconst_null
    //   25: astore 5
    //   27: ldc 78
    //   29: astore 4
    //   31: sipush 8192
    //   34: newarray byte
    //   36: astore 7
    //   38: aload 5
    //   40: astore_3
    //   41: ldc 80
    //   43: invokestatic 86	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   46: astore 8
    //   48: aload 5
    //   50: astore_3
    //   51: new 88	java/io/FileInputStream
    //   54: dup
    //   55: aload_0
    //   56: invokespecial 91	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   59: astore 5
    //   61: aload 4
    //   63: astore_3
    //   64: aload 5
    //   66: aload 7
    //   68: invokevirtual 95	java/io/FileInputStream:read	([B)I
    //   71: istore_2
    //   72: iconst_0
    //   73: istore_1
    //   74: iload_2
    //   75: iconst_m1
    //   76: if_icmpeq +18 -> 94
    //   79: aload 4
    //   81: astore_3
    //   82: aload 8
    //   84: aload 7
    //   86: iconst_0
    //   87: iload_2
    //   88: invokevirtual 99	java/security/MessageDigest:update	([BII)V
    //   91: goto -30 -> 61
    //   94: aload 4
    //   96: astore_3
    //   97: aload 8
    //   99: invokevirtual 103	java/security/MessageDigest:digest	()[B
    //   102: astore 7
    //   104: aload 4
    //   106: astore_3
    //   107: aload 7
    //   109: arraylength
    //   110: istore_2
    //   111: aload 4
    //   113: astore_0
    //   114: iload_1
    //   115: iload_2
    //   116: if_icmpge +92 -> 208
    //   119: aload_0
    //   120: astore_3
    //   121: aload 7
    //   123: iload_1
    //   124: baload
    //   125: sipush 255
    //   128: iand
    //   129: invokestatic 109	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   132: astore 6
    //   134: aload 6
    //   136: astore 4
    //   138: aload_0
    //   139: astore_3
    //   140: aload 6
    //   142: invokevirtual 113	java/lang/String:length	()I
    //   145: iconst_1
    //   146: if_icmpne +17 -> 163
    //   149: aload_0
    //   150: astore_3
    //   151: ldc 115
    //   153: aload 6
    //   155: invokestatic 119	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   158: invokevirtual 123	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   161: astore 4
    //   163: aload_0
    //   164: astore_3
    //   165: new 46	java/lang/StringBuilder
    //   168: dup
    //   169: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   172: astore 6
    //   174: aload_0
    //   175: astore_3
    //   176: aload 6
    //   178: aload_0
    //   179: invokevirtual 52	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: pop
    //   183: aload_0
    //   184: astore_3
    //   185: aload 6
    //   187: aload 4
    //   189: invokevirtual 52	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: pop
    //   193: aload_0
    //   194: astore_3
    //   195: aload 6
    //   197: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: astore_0
    //   201: iload_1
    //   202: iconst_1
    //   203: iadd
    //   204: istore_1
    //   205: goto -91 -> 114
    //   208: aload_0
    //   209: astore_3
    //   210: aload 5
    //   212: invokevirtual 126	java/io/FileInputStream:close	()V
    //   215: aload_0
    //   216: areturn
    //   217: astore_0
    //   218: aload_0
    //   219: invokevirtual 129	java/io/IOException:printStackTrace	()V
    //   222: aload_3
    //   223: areturn
    //   224: astore_0
    //   225: goto +51 -> 276
    //   228: astore 6
    //   230: aload 5
    //   232: astore_0
    //   233: aload_3
    //   234: astore 4
    //   236: aload 6
    //   238: astore 5
    //   240: goto +15 -> 255
    //   243: astore_0
    //   244: aload_3
    //   245: astore 5
    //   247: goto +29 -> 276
    //   250: astore 5
    //   252: aload 6
    //   254: astore_0
    //   255: aload_0
    //   256: astore_3
    //   257: aload 5
    //   259: invokevirtual 130	java/lang/Exception:printStackTrace	()V
    //   262: aload_0
    //   263: ifnull +10 -> 273
    //   266: aload 4
    //   268: astore_3
    //   269: aload_0
    //   270: invokevirtual 126	java/io/FileInputStream:close	()V
    //   273: aload 4
    //   275: areturn
    //   276: aload 5
    //   278: ifnull +16 -> 294
    //   281: aload 5
    //   283: invokevirtual 126	java/io/FileInputStream:close	()V
    //   286: goto +8 -> 294
    //   289: astore_3
    //   290: aload_3
    //   291: invokevirtual 129	java/io/IOException:printStackTrace	()V
    //   294: aload_0
    //   295: athrow
    //   296: ldc 78
    //   298: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	299	0	paramFile	java.io.File
    //   73	132	1	i	int
    //   71	46	2	j	int
    //   40	229	3	localObject1	Object
    //   289	2	3	localIOException	java.io.IOException
    //   29	245	4	localObject2	Object
    //   25	221	5	localObject3	Object
    //   250	32	5	localException1	Exception
    //   22	174	6	localObject4	Object
    //   228	25	6	localException2	Exception
    //   36	86	7	arrayOfByte	byte[]
    //   46	52	8	localMessageDigest	MessageDigest
    // Exception table:
    //   from	to	target	type
    //   210	215	217	java/io/IOException
    //   269	273	217	java/io/IOException
    //   64	72	224	finally
    //   82	91	224	finally
    //   97	104	224	finally
    //   107	111	224	finally
    //   121	134	224	finally
    //   140	149	224	finally
    //   151	163	224	finally
    //   165	174	224	finally
    //   176	183	224	finally
    //   185	193	224	finally
    //   195	201	224	finally
    //   64	72	228	java/lang/Exception
    //   82	91	228	java/lang/Exception
    //   97	104	228	java/lang/Exception
    //   107	111	228	java/lang/Exception
    //   121	134	228	java/lang/Exception
    //   140	149	228	java/lang/Exception
    //   151	163	228	java/lang/Exception
    //   165	174	228	java/lang/Exception
    //   176	183	228	java/lang/Exception
    //   185	193	228	java/lang/Exception
    //   195	201	228	java/lang/Exception
    //   41	48	243	finally
    //   51	61	243	finally
    //   257	262	243	finally
    //   41	48	250	java/lang/Exception
    //   51	61	250	java/lang/Exception
    //   281	286	289	java/io/IOException
  }
  
  public static String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    try
    {
      byte[] arrayOfByte = MessageDigest.getInstance("MD5").digest(paramString.getBytes());
      paramString = "";
      int j = arrayOfByte.length;
      int i = 0;
      while (i < j)
      {
        Object localObject2 = Integer.toHexString(arrayOfByte[i] & 0xFF);
        Object localObject1 = localObject2;
        if (((String)localObject2).length() == 1) {
          localObject1 = "0".concat(String.valueOf(localObject2));
        }
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramString);
        ((StringBuilder)localObject2).append((String)localObject1);
        paramString = ((StringBuilder)localObject2).toString();
        i += 1;
      }
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static void a(Context paramContext, String paramString)
  {
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return;
      }
      paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=".concat(String.valueOf(paramString))));
      paramString.setPackage("com.android.vending");
      paramString.addFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean a(Context paramContext)
  {
    String str = b(paramContext);
    return (!TextUtils.isEmpty(str)) && (str.equals(paramContext.getPackageName()));
  }
  
  public static long b()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ThreadLocalRandom.current().nextLong(8070450532247928831L);
    }
    return (a.nextDouble() * 8.0704505322479288E18D);
  }
  
  private static String b(Context paramContext)
  {
    if (!TextUtils.isEmpty(b)) {
      return b;
    }
    try
    {
      int i = Process.myPid();
      paramContext = (ActivityManager)paramContext.getSystemService("activity");
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.getRunningAppProcesses();
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
          if (localRunningAppProcessInfo.pid == i)
          {
            paramContext = localRunningAppProcessInfo.processName;
            b = paramContext;
            return paramContext;
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void b(Context paramContext, String paramString)
  {
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return;
      }
      paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=".concat(String.valueOf(paramString))));
      paramString.setPackage("com.huawei.appmarket");
      paramString.addFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if (paramContext != null)
      {
        int i = 0;
        while (i < paramContext.size())
        {
          boolean bool = ((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString);
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}
