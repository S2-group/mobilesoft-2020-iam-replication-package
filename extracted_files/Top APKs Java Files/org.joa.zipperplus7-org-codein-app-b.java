package org.codein.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class b
{
  private static int a = 0;
  
  public static long a(String paramString)
  {
    if (paramString != null)
    {
      int i = paramString.indexOf(':');
      if (i == -1) {
        break label180;
      }
      paramString = paramString.substring(i + 1).trim();
      i = paramString.lastIndexOf(' ');
      if (i == -1) {
        break label152;
      }
      String str = paramString.substring(i + 1);
      try
      {
        long l = Long.parseLong(paramString.substring(0, i).trim());
        if ("kb".equalsIgnoreCase(str)) {
          return l * 1024L;
        }
        if ("mb".equalsIgnoreCase(str)) {
          return l * 1048576L;
        }
        if ("gb".equalsIgnoreCase(str)) {
          return l * 1073741824L;
        }
        Log.w("SysInfoManager", "Unexpected mem unit format: " + paramString);
        return l;
      }
      catch (Exception paramString)
      {
        Log.e("SysInfoManager", paramString.getLocalizedMessage(), paramString);
      }
    }
    for (;;)
    {
      return -1L;
      label152:
      Log.e("SysInfoManager", "Unexpected mem value format: " + paramString);
      continue;
      label180:
      Log.e("SysInfoManager", "Unexpected mem format: " + paramString);
    }
  }
  
  public static String a()
  {
    String str;
    boolean bool;
    do
    {
      for (;;)
      {
        try
        {
          StringBuilder localStringBuilder = new StringBuilder();
          Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
          if (!localEnumeration1.hasMoreElements()) {
            break;
          }
          Enumeration localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
          Object localObject;
          if (localEnumeration2.hasMoreElements())
          {
            localObject = (InetAddress)localEnumeration2.nextElement();
            if (((InetAddress)localObject).isLoopbackAddress()) {
              continue;
            }
            localObject = ((InetAddress)localObject).getHostAddress();
            if (TextUtils.isEmpty((CharSequence)localObject)) {
              continue;
            }
            if (localStringBuilder.length() == 0) {
              localStringBuilder.append((String)localObject);
            }
          }
          else
          {
            continue;
          }
          localSocketException.append(", ").append((String)localObject);
        }
        catch (SocketException localSocketException)
        {
          Log.e("SysInfoManager", localSocketException.getLocalizedMessage(), localSocketException);
          return null;
        }
      }
      str = localSocketException.toString();
      bool = TextUtils.isEmpty(str);
    } while (bool);
    return str;
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    if (paramInt == -1) {
      return paramContext.getString(2131559661);
    }
    if (paramInt > 1) {
      return paramContext.getString(2131560060, new Object[] { Integer.valueOf(paramInt) });
    }
    return paramContext.getString(2131560059, new Object[] { Integer.valueOf(paramInt) });
  }
  
  public static String a(Context paramContext, String[] paramArrayOfString)
  {
    String[] arrayOfString = e(paramContext);
    if ((arrayOfString != null) && (arrayOfString.length == 2))
    {
      paramArrayOfString[0] = arrayOfString[0];
      paramArrayOfString[1] = arrayOfString[1];
      if (arrayOfString[1] == null) {
        return arrayOfString[0];
      }
      return arrayOfString[0] + " " + arrayOfString[1];
    }
    return paramContext.getResources().getString(2131559661);
  }
  
  /* Error */
  public static String a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 170	java/io/File
    //   6: dup
    //   7: aload_0
    //   8: invokespecial 173	java/io/File:<init>	(Ljava/lang/String;)V
    //   11: astore_2
    //   12: aload_2
    //   13: invokevirtual 176	java/io/File:exists	()Z
    //   16: ifeq +251 -> 267
    //   19: aload_2
    //   20: invokevirtual 179	java/io/File:isFile	()Z
    //   23: ifeq +244 -> 267
    //   26: aload_2
    //   27: invokevirtual 182	java/io/File:canRead	()Z
    //   30: ifeq +237 -> 267
    //   33: new 184	java/io/BufferedReader
    //   36: dup
    //   37: new 186	java/io/InputStreamReader
    //   40: dup
    //   41: new 188	java/io/FileInputStream
    //   44: dup
    //   45: aload_2
    //   46: invokespecial 191	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   49: invokespecial 194	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   52: bipush 32
    //   54: invokespecial 197	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   57: astore_2
    //   58: aload_2
    //   59: astore_0
    //   60: aload_2
    //   61: invokevirtual 200	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   64: astore_3
    //   65: aload_3
    //   66: ifnull +103 -> 169
    //   69: iload_1
    //   70: ifeq +66 -> 136
    //   73: aload_2
    //   74: astore_0
    //   75: new 59	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   82: aload_3
    //   83: invokevirtual 28	java/lang/String:trim	()Ljava/lang/String;
    //   86: invokestatic 39	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   89: ldc2_w 201
    //   92: ldiv
    //   93: invokestatic 205	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   96: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: ldc -49
    //   101: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: astore_3
    //   108: aload_3
    //   109: astore_0
    //   110: aload_2
    //   111: ifnull +9 -> 120
    //   114: aload_2
    //   115: invokevirtual 210	java/io/BufferedReader:close	()V
    //   118: aload_3
    //   119: astore_0
    //   120: aload_0
    //   121: areturn
    //   122: astore_0
    //   123: ldc 57
    //   125: aload_0
    //   126: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   129: aload_0
    //   130: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   133: pop
    //   134: aload_3
    //   135: areturn
    //   136: aload_2
    //   137: astore_0
    //   138: aload_3
    //   139: invokevirtual 28	java/lang/String:trim	()Ljava/lang/String;
    //   142: astore_3
    //   143: aload_3
    //   144: astore_0
    //   145: aload_2
    //   146: ifnull -26 -> 120
    //   149: aload_2
    //   150: invokevirtual 210	java/io/BufferedReader:close	()V
    //   153: aload_3
    //   154: areturn
    //   155: astore_0
    //   156: ldc 57
    //   158: aload_0
    //   159: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   162: aload_0
    //   163: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   166: pop
    //   167: aload_3
    //   168: areturn
    //   169: aload 4
    //   171: astore_0
    //   172: aload_2
    //   173: ifnull -53 -> 120
    //   176: aload_2
    //   177: invokevirtual 210	java/io/BufferedReader:close	()V
    //   180: aconst_null
    //   181: areturn
    //   182: astore_0
    //   183: ldc 57
    //   185: aload_0
    //   186: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   189: aload_0
    //   190: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   193: pop
    //   194: aconst_null
    //   195: areturn
    //   196: astore_3
    //   197: aconst_null
    //   198: astore_2
    //   199: aload_2
    //   200: astore_0
    //   201: ldc 57
    //   203: aload_3
    //   204: invokevirtual 80	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   207: aload_3
    //   208: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   211: pop
    //   212: aload 4
    //   214: astore_0
    //   215: aload_2
    //   216: ifnull -96 -> 120
    //   219: aload_2
    //   220: invokevirtual 210	java/io/BufferedReader:close	()V
    //   223: aconst_null
    //   224: areturn
    //   225: astore_0
    //   226: ldc 57
    //   228: aload_0
    //   229: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   232: aload_0
    //   233: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   236: pop
    //   237: aconst_null
    //   238: areturn
    //   239: astore_2
    //   240: aconst_null
    //   241: astore_0
    //   242: aload_0
    //   243: ifnull +7 -> 250
    //   246: aload_0
    //   247: invokevirtual 210	java/io/BufferedReader:close	()V
    //   250: aload_2
    //   251: athrow
    //   252: astore_0
    //   253: ldc 57
    //   255: aload_0
    //   256: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   259: aload_0
    //   260: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   263: pop
    //   264: goto -14 -> 250
    //   267: ldc 57
    //   269: new 59	java/lang/StringBuilder
    //   272: dup
    //   273: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   276: ldc -43
    //   278: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: aload_0
    //   282: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   288: invokestatic 216	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   291: pop
    //   292: aconst_null
    //   293: areturn
    //   294: astore_2
    //   295: goto -53 -> 242
    //   298: astore_3
    //   299: goto -100 -> 199
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	302	0	paramString	String
    //   0	302	1	paramBoolean	boolean
    //   11	209	2	localObject1	Object
    //   239	12	2	localObject2	Object
    //   294	1	2	localObject3	Object
    //   64	104	3	str	String
    //   196	12	3	localException1	Exception
    //   298	1	3	localException2	Exception
    //   1	212	4	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   114	118	122	java/io/IOException
    //   149	153	155	java/io/IOException
    //   176	180	182	java/io/IOException
    //   33	58	196	java/lang/Exception
    //   219	223	225	java/io/IOException
    //   33	58	239	finally
    //   246	250	252	java/io/IOException
    //   60	65	294	finally
    //   75	108	294	finally
    //   138	143	294	finally
    //   201	212	294	finally
    //   60	65	298	java/lang/Exception
    //   75	108	298	java/lang/Exception
    //   138	143	298	java/lang/Exception
  }
  
  /* Error */
  static long[] a(Context paramContext)
  {
    // Byte code:
    //   0: new 184	java/io/BufferedReader
    //   3: dup
    //   4: new 186	java/io/InputStreamReader
    //   7: dup
    //   8: new 188	java/io/FileInputStream
    //   11: dup
    //   12: new 170	java/io/File
    //   15: dup
    //   16: ldc -37
    //   18: invokespecial 173	java/io/File:<init>	(Ljava/lang/String;)V
    //   21: invokespecial 191	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   24: invokespecial 194	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   27: sipush 1024
    //   30: invokespecial 197	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   33: astore 12
    //   35: aconst_null
    //   36: astore 8
    //   38: aconst_null
    //   39: astore 9
    //   41: aload 12
    //   43: invokevirtual 200	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   46: astore 13
    //   48: aload 13
    //   50: ifnull +246 -> 296
    //   53: aload 13
    //   55: ldc -35
    //   57: invokevirtual 224	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   60: ifeq +85 -> 145
    //   63: aload 13
    //   65: astore 11
    //   67: aload 8
    //   69: astore 10
    //   71: goto +228 -> 299
    //   74: aload 9
    //   76: invokestatic 226	org/codein/app/b:a	(Ljava/lang/String;)J
    //   79: lstore_1
    //   80: aload 8
    //   82: invokestatic 226	org/codein/app/b:a	(Ljava/lang/String;)J
    //   85: lstore_3
    //   86: aload_0
    //   87: ldc -28
    //   89: invokevirtual 232	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   92: checkcast 234	android/app/ActivityManager
    //   95: astore_0
    //   96: new 236	android/app/ActivityManager$MemoryInfo
    //   99: dup
    //   100: invokespecial 237	android/app/ActivityManager$MemoryInfo:<init>	()V
    //   103: astore 8
    //   105: aload_0
    //   106: aload 8
    //   108: invokevirtual 241	android/app/ActivityManager:getMemoryInfo	(Landroid/app/ActivityManager$MemoryInfo;)V
    //   111: aload 8
    //   113: getfield 245	android/app/ActivityManager$MemoryInfo:availMem	J
    //   116: lstore 5
    //   118: aload 12
    //   120: ifnull +8 -> 128
    //   123: aload 12
    //   125: invokevirtual 210	java/io/BufferedReader:close	()V
    //   128: iconst_3
    //   129: newarray long
    //   131: dup
    //   132: iconst_0
    //   133: lload_1
    //   134: lastore
    //   135: dup
    //   136: iconst_1
    //   137: lload_3
    //   138: lastore
    //   139: dup
    //   140: iconst_2
    //   141: lload 5
    //   143: lastore
    //   144: areturn
    //   145: aload 13
    //   147: ldc -9
    //   149: invokevirtual 224	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   152: istore 7
    //   154: aload 8
    //   156: astore 10
    //   158: aload 9
    //   160: astore 11
    //   162: iload 7
    //   164: ifeq +135 -> 299
    //   167: aload 13
    //   169: astore 10
    //   171: aload 9
    //   173: astore 11
    //   175: goto +124 -> 299
    //   178: astore_0
    //   179: ldc 57
    //   181: aload_0
    //   182: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   185: aload_0
    //   186: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   189: pop
    //   190: goto -62 -> 128
    //   193: astore 8
    //   195: aconst_null
    //   196: astore_0
    //   197: ldc 57
    //   199: aload 8
    //   201: invokevirtual 80	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   204: aload 8
    //   206: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   209: pop
    //   210: aload_0
    //   211: ifnull +7 -> 218
    //   214: aload_0
    //   215: invokevirtual 210	java/io/BufferedReader:close	()V
    //   218: aconst_null
    //   219: areturn
    //   220: astore_0
    //   221: ldc 57
    //   223: aload_0
    //   224: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   227: aload_0
    //   228: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   231: pop
    //   232: goto -14 -> 218
    //   235: astore_0
    //   236: aconst_null
    //   237: astore 8
    //   239: aload 8
    //   241: ifnull +8 -> 249
    //   244: aload 8
    //   246: invokevirtual 210	java/io/BufferedReader:close	()V
    //   249: aload_0
    //   250: athrow
    //   251: astore 8
    //   253: ldc 57
    //   255: aload 8
    //   257: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   260: aload 8
    //   262: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   265: pop
    //   266: goto -17 -> 249
    //   269: astore_0
    //   270: aload 12
    //   272: astore 8
    //   274: goto -35 -> 239
    //   277: astore 9
    //   279: aload_0
    //   280: astore 8
    //   282: aload 9
    //   284: astore_0
    //   285: goto -46 -> 239
    //   288: astore 8
    //   290: aload 12
    //   292: astore_0
    //   293: goto -96 -> 197
    //   296: goto -222 -> 74
    //   299: aload 10
    //   301: astore 8
    //   303: aload 11
    //   305: astore 9
    //   307: aload 11
    //   309: ifnull -268 -> 41
    //   312: aload 10
    //   314: astore 8
    //   316: aload 11
    //   318: astore 9
    //   320: aload 10
    //   322: ifnull -281 -> 41
    //   325: aload 10
    //   327: astore 8
    //   329: aload 11
    //   331: astore 9
    //   333: goto -259 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	336	0	paramContext	Context
    //   79	55	1	l1	long
    //   85	53	3	l2	long
    //   116	26	5	l3	long
    //   152	11	7	bool	boolean
    //   36	119	8	localObject1	Object
    //   193	12	8	localException1	Exception
    //   237	8	8	localObject2	Object
    //   251	10	8	localIOException	java.io.IOException
    //   272	9	8	localObject3	Object
    //   288	1	8	localException2	Exception
    //   301	27	8	localObject4	Object
    //   39	133	9	str1	String
    //   277	6	9	localObject5	Object
    //   305	27	9	localObject6	Object
    //   69	257	10	localObject7	Object
    //   65	265	11	str2	String
    //   33	258	12	localBufferedReader	java.io.BufferedReader
    //   46	122	13	str3	String
    // Exception table:
    //   from	to	target	type
    //   123	128	178	java/io/IOException
    //   0	35	193	java/lang/Exception
    //   214	218	220	java/io/IOException
    //   0	35	235	finally
    //   244	249	251	java/io/IOException
    //   41	48	269	finally
    //   53	63	269	finally
    //   74	118	269	finally
    //   145	154	269	finally
    //   197	210	277	finally
    //   41	48	288	java/lang/Exception
    //   53	63	288	java/lang/Exception
    //   74	118	288	java/lang/Exception
    //   145	154	288	java/lang/Exception
  }
  
  private static long[] a(Context paramContext, File paramFile)
  {
    if (paramFile != null) {
      try
      {
        paramContext = new long[2];
        StatFs localStatFs = new StatFs(paramFile.getAbsolutePath());
        if (Build.VERSION.SDK_INT >= 18)
        {
          l = localStatFs.getBlockSizeLong();
          paramContext[0] = (localStatFs.getBlockCountLong() * l);
          paramContext[1] = (l * localStatFs.getAvailableBlocksLong());
          return paramContext;
        }
        long l = localStatFs.getBlockSize();
        paramContext[0] = (localStatFs.getBlockCount() * l);
        paramContext[1] = (l * localStatFs.getAvailableBlocks());
        return paramContext;
      }
      catch (Exception paramContext)
      {
        Log.e("SysInfoManager", "Cannot access path: " + paramFile.getAbsolutePath(), paramContext);
      }
    }
    return null;
  }
  
  public static String b()
  {
    if (a == 0) {
      a = c();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    switch (a)
    {
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("Dual-Core");
      continue;
      localStringBuilder.append("Triple-Core");
      continue;
      localStringBuilder.append("Quad-Core");
      continue;
      localStringBuilder.append("Hexa-Core");
      continue;
      localStringBuilder.append("Octa-Core");
    }
  }
  
  public static long[] b(Context paramContext)
  {
    return a(paramContext, Environment.getRootDirectory());
  }
  
  /* Error */
  private static int c()
  {
    // Byte code:
    //   0: new 184	java/io/BufferedReader
    //   3: dup
    //   4: new 186	java/io/InputStreamReader
    //   7: dup
    //   8: invokestatic 308	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   11: ldc_w 310
    //   14: invokevirtual 314	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   17: invokevirtual 320	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   20: invokespecial 194	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   23: invokespecial 323	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   26: astore_3
    //   27: iconst_0
    //   28: istore_0
    //   29: aload_3
    //   30: invokevirtual 200	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   33: astore 4
    //   35: iload_0
    //   36: istore_1
    //   37: aload 4
    //   39: ifnull +41 -> 80
    //   42: aload 4
    //   44: invokevirtual 324	java/lang/String:length	()I
    //   47: iconst_4
    //   48: if_icmpgt -19 -> 29
    //   51: aload 4
    //   53: ldc_w 326
    //   56: invokevirtual 329	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   59: istore_2
    //   60: iload_2
    //   61: ifeq -32 -> 29
    //   64: iload_0
    //   65: iconst_1
    //   66: iadd
    //   67: istore_0
    //   68: goto -39 -> 29
    //   71: astore_3
    //   72: iconst_0
    //   73: istore_0
    //   74: aload_3
    //   75: invokevirtual 332	java/lang/Exception:printStackTrace	()V
    //   78: iload_0
    //   79: istore_1
    //   80: iload_1
    //   81: ireturn
    //   82: astore_3
    //   83: goto -9 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   28	51	0	i	int
    //   36	45	1	j	int
    //   59	2	2	bool	boolean
    //   26	4	3	localBufferedReader	java.io.BufferedReader
    //   71	4	3	localException1	Exception
    //   82	1	3	localException2	Exception
    //   33	19	4	str	String
    // Exception table:
    //   from	to	target	type
    //   0	27	71	java/lang/Exception
    //   29	35	82	java/lang/Exception
    //   42	60	82	java/lang/Exception
  }
  
  public static long[] c(Context paramContext)
  {
    return a(paramContext, Environment.getDownloadCacheDirectory());
  }
  
  /* Error */
  public static long[] d(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: invokestatic 338	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   9: astore_2
    //   10: ldc_w 340
    //   13: aload_2
    //   14: invokevirtual 344	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   17: ifne +13 -> 30
    //   20: ldc_w 346
    //   23: aload_2
    //   24: invokevirtual 344	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   27: ifeq +266 -> 293
    //   30: new 170	java/io/File
    //   33: dup
    //   34: ldc_w 348
    //   37: invokespecial 173	java/io/File:<init>	(Ljava/lang/String;)V
    //   40: invokevirtual 176	java/io/File:exists	()Z
    //   43: ifeq +250 -> 293
    //   46: new 184	java/io/BufferedReader
    //   49: dup
    //   50: new 186	java/io/InputStreamReader
    //   53: dup
    //   54: new 188	java/io/FileInputStream
    //   57: dup
    //   58: ldc_w 350
    //   61: invokespecial 351	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   64: invokespecial 194	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   67: sipush 1024
    //   70: invokespecial 197	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   73: astore_3
    //   74: aload_3
    //   75: astore_2
    //   76: aload_3
    //   77: invokevirtual 200	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   80: astore 6
    //   82: aload 4
    //   84: astore_2
    //   85: aload 6
    //   87: ifnull +54 -> 141
    //   90: aload_3
    //   91: astore_2
    //   92: aload 6
    //   94: ldc_w 353
    //   97: invokevirtual 224	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   100: ifeq -26 -> 74
    //   103: aload_3
    //   104: astore_2
    //   105: aload 6
    //   107: bipush 32
    //   109: bipush 21
    //   111: invokevirtual 356	java/lang/String:indexOf	(II)I
    //   114: istore_1
    //   115: aload 4
    //   117: astore_2
    //   118: iload_1
    //   119: iconst_m1
    //   120: if_icmpeq +21 -> 141
    //   123: aload_3
    //   124: astore_2
    //   125: aload 6
    //   127: bipush 21
    //   129: iload_1
    //   130: invokevirtual 34	java/lang/String:substring	(II)Ljava/lang/String;
    //   133: invokevirtual 28	java/lang/String:trim	()Ljava/lang/String;
    //   136: astore 4
    //   138: aload 4
    //   140: astore_2
    //   141: aload_2
    //   142: astore 4
    //   144: aload_3
    //   145: ifnull +10 -> 155
    //   148: aload_3
    //   149: invokevirtual 210	java/io/BufferedReader:close	()V
    //   152: aload_2
    //   153: astore 4
    //   155: aload 4
    //   157: ifnull +136 -> 293
    //   160: new 170	java/io/File
    //   163: dup
    //   164: aload 4
    //   166: invokespecial 173	java/io/File:<init>	(Ljava/lang/String;)V
    //   169: astore_2
    //   170: aload_2
    //   171: invokevirtual 176	java/io/File:exists	()Z
    //   174: ifeq +119 -> 293
    //   177: aload_2
    //   178: invokevirtual 359	java/io/File:isDirectory	()Z
    //   181: ifeq +112 -> 293
    //   184: aload_0
    //   185: aload_2
    //   186: invokestatic 302	org/codein/app/b:a	(Landroid/content/Context;Ljava/io/File;)[J
    //   189: areturn
    //   190: astore_3
    //   191: ldc 57
    //   193: aload_3
    //   194: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   197: aload_3
    //   198: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   201: pop
    //   202: aload_2
    //   203: astore 4
    //   205: goto -50 -> 155
    //   208: astore 4
    //   210: aconst_null
    //   211: astore_3
    //   212: aload_3
    //   213: astore_2
    //   214: ldc 57
    //   216: aload 4
    //   218: invokevirtual 80	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   221: aload 4
    //   223: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   226: pop
    //   227: aload 5
    //   229: astore 4
    //   231: aload_3
    //   232: ifnull -77 -> 155
    //   235: aload_3
    //   236: invokevirtual 210	java/io/BufferedReader:close	()V
    //   239: aload 5
    //   241: astore 4
    //   243: goto -88 -> 155
    //   246: astore_2
    //   247: ldc 57
    //   249: aload_2
    //   250: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   253: aload_2
    //   254: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   257: pop
    //   258: aload 5
    //   260: astore 4
    //   262: goto -107 -> 155
    //   265: astore_0
    //   266: aconst_null
    //   267: astore_2
    //   268: aload_2
    //   269: ifnull +7 -> 276
    //   272: aload_2
    //   273: invokevirtual 210	java/io/BufferedReader:close	()V
    //   276: aload_0
    //   277: athrow
    //   278: astore_2
    //   279: ldc 57
    //   281: aload_2
    //   282: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   285: aload_2
    //   286: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   289: pop
    //   290: goto -14 -> 276
    //   293: aload_0
    //   294: invokestatic 362	org/codein/app/b:h	(Landroid/content/Context;)[J
    //   297: areturn
    //   298: astore_0
    //   299: goto -31 -> 268
    //   302: astore 4
    //   304: goto -92 -> 212
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	307	0	paramContext	Context
    //   114	16	1	i	int
    //   9	205	2	localObject1	Object
    //   246	8	2	localIOException1	java.io.IOException
    //   267	6	2	localObject2	Object
    //   278	8	2	localIOException2	java.io.IOException
    //   73	76	3	localBufferedReader	java.io.BufferedReader
    //   190	8	3	localIOException3	java.io.IOException
    //   211	25	3	localObject3	Object
    //   4	200	4	localObject4	Object
    //   208	14	4	localException1	Exception
    //   229	32	4	localObject5	Object
    //   302	1	4	localException2	Exception
    //   1	258	5	localObject6	Object
    //   80	46	6	str	String
    // Exception table:
    //   from	to	target	type
    //   148	152	190	java/io/IOException
    //   46	74	208	java/lang/Exception
    //   235	239	246	java/io/IOException
    //   46	74	265	finally
    //   272	276	278	java/io/IOException
    //   76	82	298	finally
    //   92	103	298	finally
    //   105	115	298	finally
    //   125	138	298	finally
    //   214	227	298	finally
    //   76	82	302	java/lang/Exception
    //   92	103	302	java/lang/Exception
    //   105	115	302	java/lang/Exception
    //   125	138	302	java/lang/Exception
  }
  
  /* Error */
  static String[] e(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 15
    //   3: getstatic 10	org/codein/app/b:a	I
    //   6: ifne +9 -> 15
    //   9: invokestatic 284	org/codein/app/b:c	()I
    //   12: putstatic 10	org/codein/app/b:a	I
    //   15: new 170	java/io/File
    //   18: dup
    //   19: ldc_w 364
    //   22: invokespecial 173	java/io/File:<init>	(Ljava/lang/String;)V
    //   25: astore_0
    //   26: aload_0
    //   27: invokevirtual 176	java/io/File:exists	()Z
    //   30: istore_2
    //   31: iload_2
    //   32: ifeq +517 -> 549
    //   35: new 184	java/io/BufferedReader
    //   38: dup
    //   39: new 186	java/io/InputStreamReader
    //   42: dup
    //   43: new 188	java/io/FileInputStream
    //   46: dup
    //   47: aload_0
    //   48: invokespecial 191	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   51: invokespecial 194	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   54: bipush 32
    //   56: invokespecial 197	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   59: astore 5
    //   61: aload 5
    //   63: invokevirtual 200	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   66: astore_0
    //   67: aload_0
    //   68: ifnull +1152 -> 1220
    //   71: aload_0
    //   72: invokevirtual 28	java/lang/String:trim	()Ljava/lang/String;
    //   75: invokestatic 39	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   78: ldc2_w 201
    //   81: ldiv
    //   82: lstore_3
    //   83: lload_3
    //   84: invokestatic 205	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   87: astore 7
    //   89: aload 7
    //   91: astore 6
    //   93: aload 5
    //   95: astore_0
    //   96: aload 5
    //   98: ifnull +21 -> 119
    //   101: aload 5
    //   103: astore 6
    //   105: aload 5
    //   107: astore_0
    //   108: aload 5
    //   110: invokevirtual 210	java/io/BufferedReader:close	()V
    //   113: aconst_null
    //   114: astore_0
    //   115: aload 7
    //   117: astore 6
    //   119: new 184	java/io/BufferedReader
    //   122: dup
    //   123: new 186	java/io/InputStreamReader
    //   126: dup
    //   127: new 188	java/io/FileInputStream
    //   130: dup
    //   131: new 170	java/io/File
    //   134: dup
    //   135: ldc_w 366
    //   138: invokespecial 173	java/io/File:<init>	(Ljava/lang/String;)V
    //   141: invokespecial 191	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   144: invokespecial 194	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   147: sipush 1024
    //   150: invokespecial 197	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   153: astore 14
    //   155: aconst_null
    //   156: astore 9
    //   158: aconst_null
    //   159: astore 10
    //   161: aload 6
    //   163: astore 11
    //   165: aload 14
    //   167: astore 6
    //   169: aload 14
    //   171: astore_0
    //   172: aload 14
    //   174: invokevirtual 200	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   177: astore 12
    //   179: aload 10
    //   181: astore 6
    //   183: aload 11
    //   185: astore_0
    //   186: aload 9
    //   188: astore 13
    //   190: aload 12
    //   192: ifnull +1008 -> 1200
    //   195: aload 9
    //   197: ifnonnull +369 -> 566
    //   200: aload 14
    //   202: astore 6
    //   204: aload 14
    //   206: astore_0
    //   207: aload 12
    //   209: ldc_w 368
    //   212: invokevirtual 224	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   215: ifeq +351 -> 566
    //   218: aload 12
    //   220: astore 7
    //   222: aload 11
    //   224: astore 5
    //   226: aload 10
    //   228: astore 8
    //   230: aload 10
    //   232: ifnonnull +994 -> 1226
    //   235: aload 14
    //   237: astore 6
    //   239: aload 14
    //   241: astore_0
    //   242: aload 10
    //   244: astore 8
    //   246: aload 12
    //   248: ldc_w 370
    //   251: invokevirtual 224	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   254: ifeq +972 -> 1226
    //   257: aload 12
    //   259: astore 8
    //   261: goto +965 -> 1226
    //   264: aload 8
    //   266: ifnull +430 -> 696
    //   269: aload 14
    //   271: astore 6
    //   273: aload 14
    //   275: astore_0
    //   276: aload 8
    //   278: bipush 58
    //   280: invokevirtual 20	java/lang/String:indexOf	(I)I
    //   283: istore_1
    //   284: iload_1
    //   285: iconst_m1
    //   286: if_icmpeq +347 -> 633
    //   289: aload 14
    //   291: astore 6
    //   293: aload 14
    //   295: astore_0
    //   296: aload 8
    //   298: iload_1
    //   299: iconst_1
    //   300: iadd
    //   301: invokevirtual 24	java/lang/String:substring	(I)Ljava/lang/String;
    //   304: invokevirtual 28	java/lang/String:trim	()Ljava/lang/String;
    //   307: astore 5
    //   309: aload 14
    //   311: ifnull +8 -> 319
    //   314: aload 14
    //   316: invokevirtual 210	java/io/BufferedReader:close	()V
    //   319: iconst_2
    //   320: anewarray 16	java/lang/String
    //   323: dup
    //   324: iconst_0
    //   325: aload 5
    //   327: aastore
    //   328: dup
    //   329: iconst_1
    //   330: aconst_null
    //   331: aastore
    //   332: astore_0
    //   333: aload_0
    //   334: areturn
    //   335: astore 8
    //   337: aload 5
    //   339: astore 6
    //   341: aload 5
    //   343: astore_0
    //   344: ldc 57
    //   346: aload 8
    //   348: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   351: aload 8
    //   353: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   356: pop
    //   357: aload 7
    //   359: astore 6
    //   361: aload 5
    //   363: astore_0
    //   364: goto -245 -> 119
    //   367: astore 5
    //   369: aload 6
    //   371: astore_0
    //   372: ldc 57
    //   374: aload 5
    //   376: invokevirtual 80	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   379: aload 5
    //   381: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   384: pop
    //   385: aload 15
    //   387: astore_0
    //   388: aload 6
    //   390: ifnull -57 -> 333
    //   393: aload 6
    //   395: invokevirtual 210	java/io/BufferedReader:close	()V
    //   398: aconst_null
    //   399: areturn
    //   400: astore_0
    //   401: ldc 57
    //   403: aload_0
    //   404: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   407: aload_0
    //   408: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   411: pop
    //   412: aconst_null
    //   413: areturn
    //   414: astore 5
    //   416: aconst_null
    //   417: astore_0
    //   418: ldc 57
    //   420: aload 5
    //   422: invokevirtual 80	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   425: aload 5
    //   427: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   430: pop
    //   431: aload_0
    //   432: ifnull +782 -> 1214
    //   435: aload_0
    //   436: invokevirtual 210	java/io/BufferedReader:close	()V
    //   439: aconst_null
    //   440: astore 6
    //   442: aconst_null
    //   443: astore_0
    //   444: goto -325 -> 119
    //   447: astore 5
    //   449: ldc 57
    //   451: aload 5
    //   453: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   456: aload 5
    //   458: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   461: pop
    //   462: aconst_null
    //   463: astore 6
    //   465: goto -346 -> 119
    //   468: astore 7
    //   470: aconst_null
    //   471: astore 5
    //   473: aload 5
    //   475: astore_0
    //   476: aload 5
    //   478: ifnull +17 -> 495
    //   481: aload 5
    //   483: astore 6
    //   485: aload 5
    //   487: astore_0
    //   488: aload 5
    //   490: invokevirtual 210	java/io/BufferedReader:close	()V
    //   493: aconst_null
    //   494: astore_0
    //   495: aload_0
    //   496: astore 6
    //   498: aload 7
    //   500: athrow
    //   501: astore 6
    //   503: aload_0
    //   504: astore 5
    //   506: aload 6
    //   508: astore_0
    //   509: aload 5
    //   511: ifnull +8 -> 519
    //   514: aload 5
    //   516: invokevirtual 210	java/io/BufferedReader:close	()V
    //   519: aload_0
    //   520: athrow
    //   521: astore 8
    //   523: aload 5
    //   525: astore 6
    //   527: aload 5
    //   529: astore_0
    //   530: ldc 57
    //   532: aload 8
    //   534: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   537: aload 8
    //   539: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   542: pop
    //   543: aload 5
    //   545: astore_0
    //   546: goto -51 -> 495
    //   549: ldc 57
    //   551: ldc_w 372
    //   554: invokestatic 216	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   557: pop
    //   558: aconst_null
    //   559: astore 6
    //   561: aconst_null
    //   562: astore_0
    //   563: goto -444 -> 119
    //   566: aload 11
    //   568: astore 5
    //   570: aload 9
    //   572: astore 7
    //   574: aload 11
    //   576: ifnonnull -350 -> 226
    //   579: aload 14
    //   581: astore 6
    //   583: aload 14
    //   585: astore_0
    //   586: aload 12
    //   588: ldc_w 374
    //   591: invokevirtual 224	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   594: istore_2
    //   595: aload 11
    //   597: astore 5
    //   599: aload 9
    //   601: astore 7
    //   603: iload_2
    //   604: ifeq -378 -> 226
    //   607: aload 12
    //   609: astore 5
    //   611: aload 9
    //   613: astore 7
    //   615: goto -389 -> 226
    //   618: astore_0
    //   619: ldc 57
    //   621: aload_0
    //   622: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   625: aload_0
    //   626: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   629: pop
    //   630: goto -311 -> 319
    //   633: aload 14
    //   635: astore 6
    //   637: aload 14
    //   639: astore_0
    //   640: ldc 57
    //   642: new 59	java/lang/StringBuilder
    //   645: dup
    //   646: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   649: ldc_w 376
    //   652: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: aload 8
    //   657: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   660: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   663: invokestatic 90	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   666: pop
    //   667: aload 15
    //   669: astore_0
    //   670: aload 14
    //   672: ifnull -339 -> 333
    //   675: aload 14
    //   677: invokevirtual 210	java/io/BufferedReader:close	()V
    //   680: aconst_null
    //   681: areturn
    //   682: astore_0
    //   683: ldc 57
    //   685: aload_0
    //   686: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   689: aload_0
    //   690: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   693: pop
    //   694: aconst_null
    //   695: areturn
    //   696: aload 7
    //   698: ifnull +380 -> 1078
    //   701: aload 5
    //   703: ifnull +375 -> 1078
    //   706: aload 14
    //   708: astore 6
    //   710: aload 14
    //   712: astore_0
    //   713: aload 7
    //   715: bipush 58
    //   717: invokevirtual 20	java/lang/String:indexOf	(I)I
    //   720: istore_1
    //   721: iload_1
    //   722: iconst_m1
    //   723: if_icmpeq +318 -> 1041
    //   726: aload 14
    //   728: astore 6
    //   730: aload 14
    //   732: astore_0
    //   733: aload 7
    //   735: iload_1
    //   736: iconst_1
    //   737: iadd
    //   738: invokevirtual 24	java/lang/String:substring	(I)Ljava/lang/String;
    //   741: invokevirtual 28	java/lang/String:trim	()Ljava/lang/String;
    //   744: astore 8
    //   746: aload 14
    //   748: astore 6
    //   750: aload 14
    //   752: astore_0
    //   753: aload 5
    //   755: bipush 58
    //   757: invokevirtual 20	java/lang/String:indexOf	(I)I
    //   760: istore_1
    //   761: iload_1
    //   762: iconst_m1
    //   763: if_icmpeq +430 -> 1193
    //   766: aload 14
    //   768: astore 6
    //   770: aload 14
    //   772: astore_0
    //   773: aload 5
    //   775: iload_1
    //   776: iconst_1
    //   777: iadd
    //   778: invokevirtual 24	java/lang/String:substring	(I)Ljava/lang/String;
    //   781: invokevirtual 28	java/lang/String:trim	()Ljava/lang/String;
    //   784: astore 7
    //   786: aload 14
    //   788: astore 6
    //   790: aload 14
    //   792: astore_0
    //   793: getstatic 10	org/codein/app/b:a	I
    //   796: tableswitch	default:+483->1279, 2:+98->894, 3:+131->927, 4:+164->960, 5:+483->1279, 6:+483->1279, 7:+483->1279, 8:+197->993
    //   840: aload 14
    //   842: astore 6
    //   844: aload 14
    //   846: astore_0
    //   847: new 59	java/lang/StringBuilder
    //   850: dup
    //   851: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   854: aload 7
    //   856: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   859: ldc -49
    //   861: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   864: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   867: astore 7
    //   869: aload 14
    //   871: ifnull +8 -> 879
    //   874: aload 14
    //   876: invokevirtual 210	java/io/BufferedReader:close	()V
    //   879: iconst_2
    //   880: anewarray 16	java/lang/String
    //   883: dup
    //   884: iconst_0
    //   885: aload 5
    //   887: aastore
    //   888: dup
    //   889: iconst_1
    //   890: aload 7
    //   892: aastore
    //   893: areturn
    //   894: aload 14
    //   896: astore 6
    //   898: aload 14
    //   900: astore_0
    //   901: new 59	java/lang/StringBuilder
    //   904: dup
    //   905: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   908: aload 8
    //   910: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   913: ldc_w 378
    //   916: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   922: astore 5
    //   924: goto -84 -> 840
    //   927: aload 14
    //   929: astore 6
    //   931: aload 14
    //   933: astore_0
    //   934: new 59	java/lang/StringBuilder
    //   937: dup
    //   938: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   941: aload 8
    //   943: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   946: ldc_w 380
    //   949: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   952: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   955: astore 5
    //   957: goto -117 -> 840
    //   960: aload 14
    //   962: astore 6
    //   964: aload 14
    //   966: astore_0
    //   967: new 59	java/lang/StringBuilder
    //   970: dup
    //   971: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   974: aload 8
    //   976: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: ldc_w 382
    //   982: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   985: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   988: astore 5
    //   990: goto -150 -> 840
    //   993: aload 14
    //   995: astore 6
    //   997: aload 14
    //   999: astore_0
    //   1000: new 59	java/lang/StringBuilder
    //   1003: dup
    //   1004: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   1007: aload 8
    //   1009: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1012: ldc_w 384
    //   1015: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1018: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1021: astore 5
    //   1023: goto -183 -> 840
    //   1026: astore_0
    //   1027: ldc 57
    //   1029: aload_0
    //   1030: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   1033: aload_0
    //   1034: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1037: pop
    //   1038: goto -159 -> 879
    //   1041: aload 14
    //   1043: astore 6
    //   1045: aload 14
    //   1047: astore_0
    //   1048: ldc 57
    //   1050: new 59	java/lang/StringBuilder
    //   1053: dup
    //   1054: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   1057: ldc_w 376
    //   1060: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1063: aload 7
    //   1065: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1068: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1071: invokestatic 90	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1074: pop
    //   1075: goto -408 -> 667
    //   1078: aload 14
    //   1080: astore 6
    //   1082: aload 14
    //   1084: astore_0
    //   1085: ldc 57
    //   1087: ldc_w 386
    //   1090: invokestatic 90	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1093: pop
    //   1094: goto -427 -> 667
    //   1097: astore 5
    //   1099: ldc 57
    //   1101: aload 5
    //   1103: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   1106: aload 5
    //   1108: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1111: pop
    //   1112: goto -593 -> 519
    //   1115: astore_0
    //   1116: aconst_null
    //   1117: astore 5
    //   1119: goto -610 -> 509
    //   1122: astore 6
    //   1124: aload_0
    //   1125: astore 5
    //   1127: aload 6
    //   1129: astore_0
    //   1130: goto -621 -> 509
    //   1133: astore 6
    //   1135: aload_0
    //   1136: astore 5
    //   1138: aload 6
    //   1140: astore_0
    //   1141: goto -632 -> 509
    //   1144: astore 5
    //   1146: aconst_null
    //   1147: astore 6
    //   1149: goto -780 -> 369
    //   1152: astore 5
    //   1154: aload_0
    //   1155: astore 6
    //   1157: goto -788 -> 369
    //   1160: astore 5
    //   1162: aload_0
    //   1163: astore 6
    //   1165: goto -796 -> 369
    //   1168: astore 7
    //   1170: goto -697 -> 473
    //   1173: astore 7
    //   1175: aload_0
    //   1176: astore 5
    //   1178: goto -705 -> 473
    //   1181: astore 6
    //   1183: aload 5
    //   1185: astore_0
    //   1186: aload 6
    //   1188: astore 5
    //   1190: goto -772 -> 418
    //   1193: aload 5
    //   1195: astore 7
    //   1197: goto -411 -> 786
    //   1200: aload 13
    //   1202: astore 7
    //   1204: aload_0
    //   1205: astore 5
    //   1207: aload 6
    //   1209: astore 8
    //   1211: goto -947 -> 264
    //   1214: aconst_null
    //   1215: astore 6
    //   1217: goto -1098 -> 119
    //   1220: aconst_null
    //   1221: astore 7
    //   1223: goto -1134 -> 89
    //   1226: aload 8
    //   1228: astore 6
    //   1230: aload 5
    //   1232: astore_0
    //   1233: aload 7
    //   1235: astore 13
    //   1237: aload 8
    //   1239: ifnonnull -39 -> 1200
    //   1242: aload 8
    //   1244: astore 10
    //   1246: aload 5
    //   1248: astore 11
    //   1250: aload 7
    //   1252: astore 9
    //   1254: aload 7
    //   1256: ifnull -1091 -> 165
    //   1259: aload 8
    //   1261: astore 10
    //   1263: aload 5
    //   1265: astore 11
    //   1267: aload 7
    //   1269: astore 9
    //   1271: aload 5
    //   1273: ifnull -1108 -> 165
    //   1276: goto -1012 -> 264
    //   1279: aload 8
    //   1281: astore 5
    //   1283: goto -443 -> 840
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1286	0	paramContext	Context
    //   283	495	1	i	int
    //   30	574	2	bool	boolean
    //   82	2	3	l	long
    //   59	303	5	localObject1	Object
    //   367	13	5	localException1	Exception
    //   414	12	5	localException2	Exception
    //   447	10	5	localIOException1	java.io.IOException
    //   471	551	5	localObject2	Object
    //   1097	10	5	localIOException2	java.io.IOException
    //   1117	20	5	localContext1	Context
    //   1144	1	5	localException3	Exception
    //   1152	1	5	localException4	Exception
    //   1160	1	5	localException5	Exception
    //   1176	106	5	localObject3	Object
    //   91	406	6	localObject4	Object
    //   501	6	6	localObject5	Object
    //   525	556	6	localObject6	Object
    //   1122	6	6	localObject7	Object
    //   1133	6	6	localObject8	Object
    //   1147	17	6	localContext2	Context
    //   1181	27	6	localException6	Exception
    //   1215	14	6	localObject9	Object
    //   87	271	7	localObject10	Object
    //   468	31	7	localObject11	Object
    //   572	492	7	localObject12	Object
    //   1168	1	7	localObject13	Object
    //   1173	1	7	localObject14	Object
    //   1195	73	7	localObject15	Object
    //   228	69	8	localObject16	Object
    //   335	17	8	localIOException3	java.io.IOException
    //   521	135	8	localIOException4	java.io.IOException
    //   744	536	8	localObject17	Object
    //   156	1114	9	localObject18	Object
    //   159	1103	10	localObject19	Object
    //   163	1103	11	localObject20	Object
    //   177	431	12	str	String
    //   188	1048	13	localObject21	Object
    //   153	930	14	localBufferedReader	java.io.BufferedReader
    //   1	667	15	localObject22	Object
    // Exception table:
    //   from	to	target	type
    //   108	113	335	java/io/IOException
    //   108	113	367	java/lang/Exception
    //   172	179	367	java/lang/Exception
    //   207	218	367	java/lang/Exception
    //   246	257	367	java/lang/Exception
    //   276	284	367	java/lang/Exception
    //   296	309	367	java/lang/Exception
    //   344	357	367	java/lang/Exception
    //   488	493	367	java/lang/Exception
    //   498	501	367	java/lang/Exception
    //   530	543	367	java/lang/Exception
    //   586	595	367	java/lang/Exception
    //   640	667	367	java/lang/Exception
    //   713	721	367	java/lang/Exception
    //   733	746	367	java/lang/Exception
    //   753	761	367	java/lang/Exception
    //   773	786	367	java/lang/Exception
    //   793	840	367	java/lang/Exception
    //   847	869	367	java/lang/Exception
    //   901	924	367	java/lang/Exception
    //   934	957	367	java/lang/Exception
    //   967	990	367	java/lang/Exception
    //   1000	1023	367	java/lang/Exception
    //   1048	1075	367	java/lang/Exception
    //   1085	1094	367	java/lang/Exception
    //   393	398	400	java/io/IOException
    //   35	61	414	java/lang/Exception
    //   435	439	447	java/io/IOException
    //   35	61	468	finally
    //   108	113	501	finally
    //   172	179	501	finally
    //   207	218	501	finally
    //   246	257	501	finally
    //   276	284	501	finally
    //   296	309	501	finally
    //   344	357	501	finally
    //   372	385	501	finally
    //   488	493	501	finally
    //   498	501	501	finally
    //   530	543	501	finally
    //   586	595	501	finally
    //   640	667	501	finally
    //   713	721	501	finally
    //   733	746	501	finally
    //   753	761	501	finally
    //   773	786	501	finally
    //   793	840	501	finally
    //   847	869	501	finally
    //   901	924	501	finally
    //   934	957	501	finally
    //   967	990	501	finally
    //   1000	1023	501	finally
    //   1048	1075	501	finally
    //   1085	1094	501	finally
    //   488	493	521	java/io/IOException
    //   314	319	618	java/io/IOException
    //   675	680	682	java/io/IOException
    //   874	879	1026	java/io/IOException
    //   514	519	1097	java/io/IOException
    //   15	31	1115	finally
    //   549	558	1115	finally
    //   435	439	1122	finally
    //   449	462	1122	finally
    //   119	155	1133	finally
    //   15	31	1144	java/lang/Exception
    //   549	558	1144	java/lang/Exception
    //   435	439	1152	java/lang/Exception
    //   449	462	1152	java/lang/Exception
    //   119	155	1160	java/lang/Exception
    //   61	67	1168	finally
    //   71	83	1168	finally
    //   418	431	1173	finally
    //   61	67	1181	java/lang/Exception
    //   71	83	1181	java/lang/Exception
  }
  
  /* Error */
  public static java.util.ArrayList<String> f(Context paramContext)
  {
    // Byte code:
    //   0: new 390	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 391	java/util/ArrayList:<init>	()V
    //   7: astore 8
    //   9: getstatic 10	org/codein/app/b:a	I
    //   12: ifne +9 -> 21
    //   15: invokestatic 284	org/codein/app/b:c	()I
    //   18: putstatic 10	org/codein/app/b:a	I
    //   21: bipush 8
    //   23: anewarray 16	java/lang/String
    //   26: dup
    //   27: iconst_0
    //   28: ldc_w 364
    //   31: aastore
    //   32: dup
    //   33: iconst_1
    //   34: ldc_w 393
    //   37: aastore
    //   38: dup
    //   39: iconst_2
    //   40: ldc_w 395
    //   43: aastore
    //   44: dup
    //   45: iconst_3
    //   46: ldc_w 397
    //   49: aastore
    //   50: dup
    //   51: iconst_4
    //   52: ldc_w 399
    //   55: aastore
    //   56: dup
    //   57: iconst_5
    //   58: ldc_w 401
    //   61: aastore
    //   62: dup
    //   63: bipush 6
    //   65: ldc_w 403
    //   68: aastore
    //   69: dup
    //   70: bipush 7
    //   72: ldc_w 405
    //   75: aastore
    //   76: invokestatic 411	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   79: astore 9
    //   81: iconst_0
    //   82: istore_1
    //   83: iload_1
    //   84: getstatic 10	org/codein/app/b:a	I
    //   87: if_icmpge +322 -> 409
    //   90: iload_1
    //   91: aload 9
    //   93: invokeinterface 416 1 0
    //   98: if_icmpge +311 -> 409
    //   101: new 170	java/io/File
    //   104: dup
    //   105: aload 9
    //   107: iload_1
    //   108: invokeinterface 420 2 0
    //   113: checkcast 16	java/lang/String
    //   116: invokespecial 173	java/io/File:<init>	(Ljava/lang/String;)V
    //   119: astore_0
    //   120: ldc_w 422
    //   123: astore 5
    //   125: aload 5
    //   127: astore 6
    //   129: aload_0
    //   130: invokevirtual 176	java/io/File:exists	()Z
    //   133: istore_2
    //   134: aload 5
    //   136: astore 6
    //   138: iload_2
    //   139: ifeq +112 -> 251
    //   142: new 184	java/io/BufferedReader
    //   145: dup
    //   146: new 186	java/io/InputStreamReader
    //   149: dup
    //   150: new 188	java/io/FileInputStream
    //   153: dup
    //   154: aload_0
    //   155: invokespecial 191	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   158: invokespecial 194	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   161: bipush 32
    //   163: invokespecial 197	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   166: astore 7
    //   168: aload 7
    //   170: astore_0
    //   171: aload 7
    //   173: invokevirtual 200	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   176: astore 6
    //   178: aload 5
    //   180: astore_0
    //   181: aload 6
    //   183: ifnull +49 -> 232
    //   186: aload 7
    //   188: astore_0
    //   189: aload 6
    //   191: invokevirtual 28	java/lang/String:trim	()Ljava/lang/String;
    //   194: invokestatic 39	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   197: lstore_3
    //   198: aload 7
    //   200: astore_0
    //   201: new 59	java/lang/StringBuilder
    //   204: dup
    //   205: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   208: lload_3
    //   209: ldc2_w 201
    //   212: ldiv
    //   213: invokestatic 205	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   216: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: ldc -49
    //   221: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: astore 6
    //   229: aload 6
    //   231: astore_0
    //   232: aload_0
    //   233: astore 6
    //   235: aload 7
    //   237: ifnull +14 -> 251
    //   240: aload_0
    //   241: astore 6
    //   243: aload 7
    //   245: invokevirtual 210	java/io/BufferedReader:close	()V
    //   248: aload_0
    //   249: astore 6
    //   251: aload 8
    //   253: aload 6
    //   255: invokevirtual 425	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   258: pop
    //   259: iload_1
    //   260: iconst_1
    //   261: iadd
    //   262: istore_1
    //   263: goto -180 -> 83
    //   266: astore 5
    //   268: aload_0
    //   269: astore 6
    //   271: ldc 57
    //   273: aload 5
    //   275: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   278: aload 5
    //   280: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   283: pop
    //   284: aload_0
    //   285: astore 6
    //   287: goto -36 -> 251
    //   290: astore_0
    //   291: aload_0
    //   292: invokevirtual 332	java/lang/Exception:printStackTrace	()V
    //   295: goto -44 -> 251
    //   298: astore 6
    //   300: aconst_null
    //   301: astore 7
    //   303: aload 7
    //   305: astore_0
    //   306: ldc 57
    //   308: aload 6
    //   310: invokevirtual 80	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   313: aload 6
    //   315: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   318: pop
    //   319: aload 5
    //   321: astore 6
    //   323: aload 7
    //   325: ifnull -74 -> 251
    //   328: aload 5
    //   330: astore 6
    //   332: aload 7
    //   334: invokevirtual 210	java/io/BufferedReader:close	()V
    //   337: aload 5
    //   339: astore 6
    //   341: goto -90 -> 251
    //   344: astore_0
    //   345: aload 5
    //   347: astore 6
    //   349: ldc 57
    //   351: aload_0
    //   352: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   355: aload_0
    //   356: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   359: pop
    //   360: aload 5
    //   362: astore 6
    //   364: goto -113 -> 251
    //   367: astore 7
    //   369: aconst_null
    //   370: astore_0
    //   371: aload_0
    //   372: ifnull +11 -> 383
    //   375: aload 5
    //   377: astore 6
    //   379: aload_0
    //   380: invokevirtual 210	java/io/BufferedReader:close	()V
    //   383: aload 5
    //   385: astore 6
    //   387: aload 7
    //   389: athrow
    //   390: astore_0
    //   391: aload 5
    //   393: astore 6
    //   395: ldc 57
    //   397: aload_0
    //   398: invokevirtual 211	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   401: aload_0
    //   402: invokestatic 84	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   405: pop
    //   406: goto -23 -> 383
    //   409: aload 8
    //   411: areturn
    //   412: astore 7
    //   414: goto -43 -> 371
    //   417: astore 6
    //   419: goto -116 -> 303
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	422	0	paramContext	Context
    //   82	181	1	i	int
    //   133	6	2	bool	boolean
    //   197	12	3	l	long
    //   123	56	5	str	String
    //   266	126	5	localIOException1	java.io.IOException
    //   127	159	6	localObject1	Object
    //   298	16	6	localException1	Exception
    //   321	73	6	localIOException2	java.io.IOException
    //   417	1	6	localException2	Exception
    //   166	167	7	localBufferedReader	java.io.BufferedReader
    //   367	21	7	localObject2	Object
    //   412	1	7	localObject3	Object
    //   7	403	8	localArrayList	java.util.ArrayList
    //   79	27	9	localList	List
    // Exception table:
    //   from	to	target	type
    //   243	248	266	java/io/IOException
    //   129	134	290	java/lang/Exception
    //   243	248	290	java/lang/Exception
    //   271	284	290	java/lang/Exception
    //   332	337	290	java/lang/Exception
    //   349	360	290	java/lang/Exception
    //   379	383	290	java/lang/Exception
    //   387	390	290	java/lang/Exception
    //   395	406	290	java/lang/Exception
    //   142	168	298	java/lang/Exception
    //   332	337	344	java/io/IOException
    //   142	168	367	finally
    //   379	383	390	java/io/IOException
    //   171	178	412	finally
    //   189	198	412	finally
    //   201	229	412	finally
    //   306	319	412	finally
    //   171	178	417	java/lang/Exception
    //   189	198	417	java/lang/Exception
    //   201	229	417	java/lang/Exception
  }
  
  public static List<Sensor> g(Context paramContext)
  {
    paramContext = (SensorManager)paramContext.getSystemService("sensor");
    if (paramContext != null) {
      return paramContext.getSensorList(-1);
    }
    return null;
  }
  
  private static long[] h(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    long l2 = 0L;
    long l3 = 0L;
    int j = paramContext.size();
    int i = 0;
    for (;;)
    {
      long l1;
      if (i < j)
      {
        Object localObject = (ApplicationInfo)paramContext.get(i);
        long l4 = l2;
        if ((((ApplicationInfo)localObject).flags & 0x40000) != 0)
        {
          localObject = ((ApplicationInfo)localObject).sourceDir;
          l4 = l2;
          if (localObject != null)
          {
            localObject = new File((String)localObject);
            l4 = l2;
            if (((File)localObject).canRead())
            {
              l1 = l2;
              try
              {
                StatFs localStatFs = new StatFs(((File)localObject).getAbsolutePath());
                l1 = l2;
                if (Build.VERSION.SDK_INT >= 18)
                {
                  l1 = l2;
                  l4 = localStatFs.getBlockSizeLong();
                  l1 = l2;
                  l2 += localStatFs.getBlockCountLong() * l4;
                  l1 = l2;
                  l4 = localStatFs.getAvailableBlocksLong() * l4 + l3;
                  l1 = l4;
                }
                else
                {
                  l1 = l2;
                  l4 = localStatFs.getBlockSize();
                  l1 = l2;
                  l2 += localStatFs.getBlockCount() * l4;
                  l1 = l2;
                  int k = localStatFs.getAvailableBlocks();
                  l1 = k * l4 + l3;
                }
              }
              catch (Exception localException)
              {
                Log.e("SysInfoManager", "Cannot access path: " + ((File)localObject).getAbsolutePath(), localException);
                l4 = l1;
              }
            }
          }
        }
        l1 = l3;
        l2 = l4;
      }
      else
      {
        if (l2 > 0L) {
          return new long[] { l2, l3 };
        }
        return null;
      }
      i += 1;
      l3 = l1;
    }
  }
}
