package com.bytedance.common.antifraud.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.common.antifraud.util.appops.AppOpsManagerCompat;
import com.bytedance.common.utility.StringUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils
{
  private static boolean sShouldAskPermissionGranted = true;
  
  public Utils() {}
  
  public static String URLEncode(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return "";
    }
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static JSONArray arrayToJson(Object paramObject)
    throws JSONException
  {
    if (!paramObject.getClass().isArray()) {
      throw new JSONException("Not a primitive data: " + paramObject.getClass());
    }
    int j = Array.getLength(paramObject);
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    while (i < j)
    {
      localJSONArray.put(wrap(Array.get(paramObject, i)));
      i += 1;
    }
    return localJSONArray;
  }
  
  public static byte[] base64Decode(String paramString)
    throws IOException
  {
    try
    {
      paramString = Base64.decode(paramString.getBytes("utf-8"), 0);
      return paramString;
    }
    catch (Exception paramString)
    {
      throw new IOException(paramString);
    }
  }
  
  public static String base64Encode(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      paramArrayOfByte = Base64.encodeToString(paramArrayOfByte, 0);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      throw new IOException(paramArrayOfByte);
    }
  }
  
  private static boolean checkPermissionGranted(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {}
    for (;;)
    {
      return false;
      try
      {
        int i = Process.myPid();
        int j = Process.myUid();
        String str1 = paramContext.getPackageName();
        if (paramContext.checkPermission(paramString, i, j) != -1)
        {
          String str2 = AppOpsManagerCompat.permissionToOp(paramString);
          if (str2 == null) {
            return true;
          }
          paramString = str1;
          if (str1 == null)
          {
            paramString = paramContext.getPackageManager().getPackagesForUid(j);
            if ((paramString != null) && (paramString.length > 0)) {
              paramString = paramString[0];
            }
          }
          else
          {
            i = AppOpsManagerCompat.noteProxyOp(paramContext, str2, paramString);
            if (i != 0) {}
          }
        }
      }
      catch (Throwable paramContext)
      {
        for (;;)
        {
          ThrowableExtension.printStackTrace(paramContext);
        }
      }
    }
    return true;
  }
  
  public static void close(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Exception paramCloseable) {}
  }
  
  public static void close(HttpURLConnection paramHttpURLConnection)
  {
    if (paramHttpURLConnection != null) {}
    try
    {
      paramHttpURLConnection.disconnect();
      return;
    }
    catch (Exception paramHttpURLConnection) {}
  }
  
  public static JSONArray collectionToJson(Collection paramCollection)
  {
    JSONArray localJSONArray = new JSONArray();
    if (paramCollection != null)
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        localJSONArray.put(wrap(paramCollection.next()));
      }
    }
    return localJSONArray;
  }
  
  public static String formatMacAddress(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return "";
    }
    return paramString.replaceAll(":", "").toLowerCase();
  }
  
  public static boolean isAbsoluteFileExist(String paramString)
  {
    try
    {
      boolean bool = new File(paramString).exists();
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int isInstalledApp(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        boolean bool = paramString.equals(((PackageInfo)paramContext.next()).packageName);
        if (bool) {
          return 1;
        }
      }
      return 0;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static boolean isSdcardFileExist(String paramString)
  {
    try
    {
      boolean bool = new File(Environment.getExternalStorageDirectory() + "/" + paramString).exists();
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static String macBytesToString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfByte[i];
      if (localStringBuffer.length() > 0) {
        localStringBuffer.append(":");
      }
      String str2 = Integer.toHexString(k & 0xFF);
      String str1 = str2;
      if (str2.length() == 1) {
        str1 = "0" + str2;
      }
      localStringBuffer.append(str1);
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static JSONObject mapToJson(Map<?, ?> paramMap)
  {
    localJSONObject = new JSONObject();
    try
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        if ((localEntry != null) && (localEntry.getKey() != null))
        {
          String str = (String)localEntry.getKey();
          try
          {
            localJSONObject.put(str, wrap(localEntry.getValue()));
          }
          catch (JSONException localJSONException)
          {
            ThrowableExtension.printStackTrace(localJSONException);
          }
        }
      }
      return localJSONObject;
    }
    catch (Exception paramMap)
    {
      ThrowableExtension.printStackTrace(paramMap);
    }
  }
  
  public static String md5(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    try
    {
      paramString = md5(paramString.getBytes("utf-8"));
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static String md5(byte[] paramArrayOfByte)
    throws IOException
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
      return "";
    }
    try
    {
      paramArrayOfByte = MessageDigest.getInstance("MD5").digest(paramArrayOfByte);
      StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
      int j = paramArrayOfByte.length;
      int i = 0;
      while (i < j)
      {
        int k = paramArrayOfByte[i];
        if ((k & 0xFF) < 16) {
          localStringBuilder.append("0");
        }
        localStringBuilder.append(Integer.toHexString(k & 0xFF));
        i += 1;
      }
      paramArrayOfByte = localStringBuilder.toString();
      return paramArrayOfByte;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      ThrowableExtension.printStackTrace(paramArrayOfByte);
      throw new IOException("fail to md5 data");
    }
  }
  
  /* Error */
  public static String readFile(File paramFile)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +10 -> 11
    //   4: aload_0
    //   5: invokevirtual 211	java/io/File:exists	()Z
    //   8: ifne +14 -> 22
    //   11: new 92	java/io/IOException
    //   14: dup
    //   15: ldc_w 310
    //   18: invokespecial 306	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   21: athrow
    //   22: new 312	java/io/FileInputStream
    //   25: dup
    //   26: aload_0
    //   27: invokespecial 315	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   30: astore_2
    //   31: aload_2
    //   32: invokevirtual 319	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   35: astore_1
    //   36: aload_1
    //   37: lconst_0
    //   38: ldc2_w 320
    //   41: iconst_1
    //   42: invokevirtual 327	java/nio/channels/FileChannel:lock	(JJZ)Ljava/nio/channels/FileLock;
    //   45: astore 4
    //   47: new 20	java/lang/String
    //   50: dup
    //   51: aload_1
    //   52: invokestatic 330	com/bytedance/common/antifraud/util/Utils:readFile	(Ljava/nio/channels/FileChannel;)[B
    //   55: ldc 94
    //   57: invokespecial 333	java/lang/String:<init>	([BLjava/lang/String;)V
    //   60: astore_0
    //   61: aload 4
    //   63: ifnull +8 -> 71
    //   66: aload 4
    //   68: invokevirtual 338	java/nio/channels/FileLock:release	()V
    //   71: aload_1
    //   72: ifnull +7 -> 79
    //   75: aload_1
    //   76: invokevirtual 339	java/nio/channels/FileChannel:close	()V
    //   79: aload_2
    //   80: invokestatic 341	com/bytedance/common/antifraud/util/Utils:close	(Ljava/io/Closeable;)V
    //   83: aload_0
    //   84: areturn
    //   85: astore_3
    //   86: aconst_null
    //   87: astore_0
    //   88: aconst_null
    //   89: astore_2
    //   90: aconst_null
    //   91: astore_1
    //   92: new 92	java/io/IOException
    //   95: dup
    //   96: aload_3
    //   97: invokespecial 106	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   100: athrow
    //   101: astore 5
    //   103: aload_1
    //   104: astore 4
    //   106: aload_0
    //   107: astore_1
    //   108: aload_2
    //   109: astore_3
    //   110: aload 5
    //   112: astore_0
    //   113: aload_1
    //   114: ifnull +7 -> 121
    //   117: aload_1
    //   118: invokevirtual 338	java/nio/channels/FileLock:release	()V
    //   121: aload_3
    //   122: ifnull +7 -> 129
    //   125: aload_3
    //   126: invokevirtual 339	java/nio/channels/FileChannel:close	()V
    //   129: aload 4
    //   131: invokestatic 341	com/bytedance/common/antifraud/util/Utils:close	(Ljava/io/Closeable;)V
    //   134: aload_0
    //   135: athrow
    //   136: astore_0
    //   137: aconst_null
    //   138: astore_1
    //   139: aconst_null
    //   140: astore_3
    //   141: aconst_null
    //   142: astore 4
    //   144: goto -31 -> 113
    //   147: astore_0
    //   148: aconst_null
    //   149: astore_1
    //   150: aconst_null
    //   151: astore_3
    //   152: aload_2
    //   153: astore 4
    //   155: goto -42 -> 113
    //   158: astore_0
    //   159: aconst_null
    //   160: astore 4
    //   162: aload_1
    //   163: astore_3
    //   164: aload 4
    //   166: astore_1
    //   167: aload_2
    //   168: astore 4
    //   170: goto -57 -> 113
    //   173: astore_0
    //   174: aload_1
    //   175: astore_3
    //   176: aload 4
    //   178: astore_1
    //   179: aload_2
    //   180: astore 4
    //   182: goto -69 -> 113
    //   185: astore_3
    //   186: aconst_null
    //   187: astore_0
    //   188: aconst_null
    //   189: astore 4
    //   191: aload_2
    //   192: astore_1
    //   193: aload 4
    //   195: astore_2
    //   196: goto -104 -> 92
    //   199: astore_3
    //   200: aconst_null
    //   201: astore_0
    //   202: aload_2
    //   203: astore 4
    //   205: aload_1
    //   206: astore_2
    //   207: aload 4
    //   209: astore_1
    //   210: goto -118 -> 92
    //   213: astore_3
    //   214: aload_2
    //   215: astore 5
    //   217: aload_1
    //   218: astore_2
    //   219: aload 4
    //   221: astore_0
    //   222: aload 5
    //   224: astore_1
    //   225: goto -133 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	paramFile	File
    //   35	190	1	localObject1	Object
    //   30	189	2	localObject2	Object
    //   85	12	3	localException1	Exception
    //   109	67	3	localObject3	Object
    //   185	1	3	localException2	Exception
    //   199	1	3	localException3	Exception
    //   213	1	3	localException4	Exception
    //   45	175	4	localObject4	Object
    //   101	10	5	localObject5	Object
    //   215	8	5	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   22	31	85	java/lang/Exception
    //   92	101	101	finally
    //   22	31	136	finally
    //   31	36	147	finally
    //   36	47	158	finally
    //   47	61	173	finally
    //   31	36	185	java/lang/Exception
    //   36	47	199	java/lang/Exception
    //   47	61	213	java/lang/Exception
  }
  
  public static String readFile(String paramString)
    throws IOException
  {
    try
    {
      paramString = readFile(new File(paramString));
      return paramString;
    }
    catch (Exception paramString)
    {
      throw new IOException(paramString);
    }
  }
  
  /* Error */
  private static byte[] readFile(java.nio.channels.FileChannel paramFileChannel)
    throws IOException
  {
    // Byte code:
    //   0: new 345	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 346	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore 5
    //   9: aload 5
    //   11: astore 4
    //   13: bipush 100
    //   15: invokestatic 352	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   18: astore 6
    //   20: iconst_0
    //   21: istore_2
    //   22: iconst_0
    //   23: istore_1
    //   24: aload 5
    //   26: astore 4
    //   28: aload_0
    //   29: aload 6
    //   31: iload_1
    //   32: i2l
    //   33: invokevirtual 356	java/nio/channels/FileChannel:read	(Ljava/nio/ByteBuffer;J)I
    //   36: istore_3
    //   37: iload_3
    //   38: ifle +14 -> 52
    //   41: iload_2
    //   42: iload_3
    //   43: iadd
    //   44: istore_2
    //   45: iload_1
    //   46: iload_3
    //   47: iadd
    //   48: istore_1
    //   49: goto -25 -> 24
    //   52: aload 5
    //   54: astore 4
    //   56: aload 6
    //   58: invokevirtual 360	java/nio/ByteBuffer:array	()[B
    //   61: astore_0
    //   62: iload_2
    //   63: iconst_4
    //   64: if_icmplt +80 -> 144
    //   67: aload_0
    //   68: iconst_0
    //   69: baload
    //   70: sipush 255
    //   73: iand
    //   74: ifne +70 -> 144
    //   77: aload_0
    //   78: iconst_1
    //   79: baload
    //   80: sipush 255
    //   83: iand
    //   84: ifne +60 -> 144
    //   87: aload_0
    //   88: iconst_2
    //   89: baload
    //   90: sipush 255
    //   93: iand
    //   94: ifne +50 -> 144
    //   97: aload_0
    //   98: iconst_3
    //   99: baload
    //   100: sipush 255
    //   103: iand
    //   104: ifne +40 -> 144
    //   107: aload 5
    //   109: astore 4
    //   111: new 92	java/io/IOException
    //   114: dup
    //   115: ldc_w 362
    //   118: invokespecial 306	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   121: athrow
    //   122: astore_0
    //   123: aload 5
    //   125: astore 4
    //   127: new 92	java/io/IOException
    //   130: dup
    //   131: aload_0
    //   132: invokespecial 106	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   135: athrow
    //   136: astore_0
    //   137: aload 4
    //   139: invokestatic 341	com/bytedance/common/antifraud/util/Utils:close	(Ljava/io/Closeable;)V
    //   142: aload_0
    //   143: athrow
    //   144: aload 5
    //   146: astore 4
    //   148: aload 5
    //   150: aload_0
    //   151: iconst_0
    //   152: iload_2
    //   153: invokevirtual 366	java/io/ByteArrayOutputStream:write	([BII)V
    //   156: aload 5
    //   158: astore 4
    //   160: aload 5
    //   162: invokevirtual 369	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   165: astore_0
    //   166: aload 5
    //   168: invokestatic 341	com/bytedance/common/antifraud/util/Utils:close	(Ljava/io/Closeable;)V
    //   171: aload_0
    //   172: areturn
    //   173: astore_0
    //   174: aconst_null
    //   175: astore 4
    //   177: goto -40 -> 137
    //   180: astore_0
    //   181: aconst_null
    //   182: astore 4
    //   184: goto -57 -> 127
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	187	0	paramFileChannel	java.nio.channels.FileChannel
    //   23	26	1	i	int
    //   21	132	2	j	int
    //   36	12	3	k	int
    //   11	172	4	localByteArrayOutputStream1	java.io.ByteArrayOutputStream
    //   7	160	5	localByteArrayOutputStream2	java.io.ByteArrayOutputStream
    //   18	39	6	localByteBuffer	java.nio.ByteBuffer
    // Exception table:
    //   from	to	target	type
    //   13	20	122	java/lang/Exception
    //   28	37	122	java/lang/Exception
    //   56	62	122	java/lang/Exception
    //   111	122	122	java/lang/Exception
    //   148	156	122	java/lang/Exception
    //   160	166	122	java/lang/Exception
    //   13	20	136	finally
    //   28	37	136	finally
    //   56	62	136	finally
    //   111	122	136	finally
    //   127	136	136	finally
    //   148	156	136	finally
    //   160	166	136	finally
    //   0	9	173	finally
    //   0	9	180	java/lang/Exception
  }
  
  /* Error */
  public static List<String> readFileLines(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: new 373	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 374	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: new 207	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 208	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: astore_0
    //   17: new 376	java/io/BufferedReader
    //   20: dup
    //   21: new 378	java/io/FileReader
    //   24: dup
    //   25: aload_0
    //   26: invokespecial 379	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   29: invokespecial 382	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   32: astore_1
    //   33: aload_1
    //   34: astore_0
    //   35: aload_1
    //   36: invokevirtual 385	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   39: astore_3
    //   40: aload_3
    //   41: ifnull +50 -> 91
    //   44: aload_1
    //   45: astore_0
    //   46: aload_3
    //   47: invokestatic 389	com/bytedance/common/utility/StringUtils:isEmpty	(Ljava/lang/String;)Z
    //   50: ifne -17 -> 33
    //   53: aload_1
    //   54: astore_0
    //   55: aload_2
    //   56: aload_3
    //   57: invokeinterface 392 2 0
    //   62: pop
    //   63: goto -30 -> 33
    //   66: astore_2
    //   67: aload_1
    //   68: astore_0
    //   69: aload_2
    //   70: astore_1
    //   71: new 92	java/io/IOException
    //   74: dup
    //   75: aload_1
    //   76: invokespecial 106	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   79: athrow
    //   80: astore_2
    //   81: aload_0
    //   82: astore_1
    //   83: aload_2
    //   84: astore_0
    //   85: aload_1
    //   86: invokestatic 341	com/bytedance/common/antifraud/util/Utils:close	(Ljava/io/Closeable;)V
    //   89: aload_0
    //   90: athrow
    //   91: aload_1
    //   92: invokestatic 341	com/bytedance/common/antifraud/util/Utils:close	(Ljava/io/Closeable;)V
    //   95: aload_2
    //   96: areturn
    //   97: astore_0
    //   98: aconst_null
    //   99: astore_1
    //   100: goto -15 -> 85
    //   103: astore_1
    //   104: aconst_null
    //   105: astore_0
    //   106: goto -35 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	paramString	String
    //   32	68	1	localObject	Object
    //   103	1	1	localException1	Exception
    //   7	49	2	localArrayList	java.util.ArrayList
    //   66	4	2	localException2	Exception
    //   80	16	2	localList	List<String>
    //   39	18	3	str	String
    // Exception table:
    //   from	to	target	type
    //   35	40	66	java/lang/Exception
    //   46	53	66	java/lang/Exception
    //   55	63	66	java/lang/Exception
    //   35	40	80	finally
    //   46	53	80	finally
    //   55	63	80	finally
    //   71	80	80	finally
    //   17	33	97	finally
    //   17	33	103	java/lang/Exception
  }
  
  public static boolean selfPermissionGranted(Context paramContext, String paramString)
  {
    if (!sShouldAskPermissionGranted) {}
    for (;;)
    {
      return true;
      if (Build.VERSION.SDK_INT >= 23) {
        try
        {
          i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.targetSdkVersion;
          if (i >= 23)
          {
            if (paramContext.checkSelfPermission(paramString) == 0) {
              continue;
            }
            return false;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            ThrowableExtension.printStackTrace(localNameNotFoundException);
            int i = 22;
          }
        }
      }
    }
    return checkPermissionGranted(paramContext, paramString);
  }
  
  public static void setShouldAskPermissionGranted(boolean paramBoolean)
  {
    sShouldAskPermissionGranted = paramBoolean;
  }
  
  private static Object wrap(Object paramObject)
  {
    Object localObject;
    if (paramObject == null) {
      localObject = null;
    }
    for (;;)
    {
      return localObject;
      localObject = paramObject;
      if (!(paramObject instanceof JSONArray))
      {
        localObject = paramObject;
        if (!(paramObject instanceof JSONObject)) {
          try
          {
            if ((paramObject instanceof Collection)) {
              return collectionToJson((Collection)paramObject);
            }
            if (paramObject.getClass().isArray()) {
              return arrayToJson(paramObject);
            }
            if ((paramObject instanceof Map)) {
              return mapToJson((Map)paramObject);
            }
            localObject = paramObject;
            if (!(paramObject instanceof Boolean))
            {
              localObject = paramObject;
              if (!(paramObject instanceof Byte))
              {
                localObject = paramObject;
                if (!(paramObject instanceof Character))
                {
                  localObject = paramObject;
                  if (!(paramObject instanceof Double))
                  {
                    localObject = paramObject;
                    if (!(paramObject instanceof Float))
                    {
                      localObject = paramObject;
                      if (!(paramObject instanceof Integer))
                      {
                        localObject = paramObject;
                        if (!(paramObject instanceof Long))
                        {
                          localObject = paramObject;
                          if (!(paramObject instanceof Short))
                          {
                            localObject = paramObject;
                            if (!(paramObject instanceof String)) {
                              if (paramObject.getClass().getPackage().getName().startsWith("java."))
                              {
                                paramObject = paramObject.toString();
                                return paramObject;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          catch (Exception paramObject) {}
        }
      }
    }
    return null;
  }
  
  public static void writeFile(File paramFile, String paramString)
    throws Exception
  {
    if ((paramFile == null) || (StringUtils.isEmpty(paramString))) {
      throw new IOException("file or bytes empty");
    }
    writeFile(paramFile, paramString.getBytes("utf-8"));
  }
  
  /* Error */
  public static void writeFile(File paramFile, byte[] paramArrayOfByte)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: aload_1
    //   5: ifnonnull +14 -> 19
    //   8: new 92	java/io/IOException
    //   11: dup
    //   12: ldc_w 462
    //   15: invokespecial 306	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   18: athrow
    //   19: new 467	java/io/FileOutputStream
    //   22: dup
    //   23: aload_0
    //   24: invokespecial 468	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   27: astore_3
    //   28: aload_3
    //   29: invokevirtual 469	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   32: astore_2
    //   33: aload_2
    //   34: invokevirtual 472	java/nio/channels/FileChannel:lock	()Ljava/nio/channels/FileLock;
    //   37: astore 5
    //   39: aload 5
    //   41: astore_0
    //   42: aload_2
    //   43: astore 4
    //   45: aload_3
    //   46: astore 6
    //   48: aload_1
    //   49: invokestatic 475	java/nio/ByteBuffer:wrap	([B)Ljava/nio/ByteBuffer;
    //   52: astore_1
    //   53: aload 5
    //   55: astore_0
    //   56: aload_2
    //   57: astore 4
    //   59: aload_3
    //   60: astore 6
    //   62: aload_1
    //   63: invokevirtual 478	java/nio/ByteBuffer:hasRemaining	()Z
    //   66: ifeq +75 -> 141
    //   69: aload 5
    //   71: astore_0
    //   72: aload_2
    //   73: astore 4
    //   75: aload_3
    //   76: astore 6
    //   78: aload_2
    //   79: aload_1
    //   80: invokevirtual 481	java/nio/channels/FileChannel:write	(Ljava/nio/ByteBuffer;)I
    //   83: pop
    //   84: goto -31 -> 53
    //   87: astore_1
    //   88: aload 5
    //   90: astore_0
    //   91: aload_2
    //   92: astore 4
    //   94: aload_3
    //   95: astore 6
    //   97: new 92	java/io/IOException
    //   100: dup
    //   101: aload_1
    //   102: invokespecial 106	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   105: athrow
    //   106: astore 5
    //   108: aload 6
    //   110: astore_3
    //   111: aload 4
    //   113: astore_2
    //   114: aload_0
    //   115: astore_1
    //   116: aload 5
    //   118: astore_0
    //   119: aload_1
    //   120: ifnull +7 -> 127
    //   123: aload_1
    //   124: invokevirtual 338	java/nio/channels/FileLock:release	()V
    //   127: aload_2
    //   128: ifnull +7 -> 135
    //   131: aload_2
    //   132: invokevirtual 339	java/nio/channels/FileChannel:close	()V
    //   135: aload_3
    //   136: invokestatic 341	com/bytedance/common/antifraud/util/Utils:close	(Ljava/io/Closeable;)V
    //   139: aload_0
    //   140: athrow
    //   141: aload 5
    //   143: astore_0
    //   144: aload_2
    //   145: astore 4
    //   147: aload_3
    //   148: astore 6
    //   150: aload_3
    //   151: invokevirtual 484	java/io/FileOutputStream:flush	()V
    //   154: aload 5
    //   156: ifnull +8 -> 164
    //   159: aload 5
    //   161: invokevirtual 338	java/nio/channels/FileLock:release	()V
    //   164: aload_2
    //   165: ifnull +7 -> 172
    //   168: aload_2
    //   169: invokevirtual 339	java/nio/channels/FileChannel:close	()V
    //   172: aload_3
    //   173: invokestatic 341	com/bytedance/common/antifraud/util/Utils:close	(Ljava/io/Closeable;)V
    //   176: return
    //   177: astore_0
    //   178: aconst_null
    //   179: astore_1
    //   180: aconst_null
    //   181: astore_2
    //   182: aconst_null
    //   183: astore_3
    //   184: goto -65 -> 119
    //   187: astore_0
    //   188: aconst_null
    //   189: astore_1
    //   190: aconst_null
    //   191: astore_2
    //   192: goto -73 -> 119
    //   195: astore_0
    //   196: aconst_null
    //   197: astore_1
    //   198: goto -79 -> 119
    //   201: astore_1
    //   202: aconst_null
    //   203: astore_0
    //   204: aconst_null
    //   205: astore_2
    //   206: aconst_null
    //   207: astore_3
    //   208: goto -117 -> 91
    //   211: astore_1
    //   212: aconst_null
    //   213: astore_0
    //   214: aconst_null
    //   215: astore_2
    //   216: goto -125 -> 91
    //   219: astore_1
    //   220: aconst_null
    //   221: astore_0
    //   222: goto -131 -> 91
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	paramFile	File
    //   0	225	1	paramArrayOfByte	byte[]
    //   32	184	2	localObject1	Object
    //   27	181	3	localObject2	Object
    //   43	103	4	localObject3	Object
    //   37	52	5	localFileLock	java.nio.channels.FileLock
    //   106	54	5	localObject4	Object
    //   46	103	6	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   48	53	87	java/lang/Exception
    //   62	69	87	java/lang/Exception
    //   78	84	87	java/lang/Exception
    //   150	154	87	java/lang/Exception
    //   48	53	106	finally
    //   62	69	106	finally
    //   78	84	106	finally
    //   97	106	106	finally
    //   150	154	106	finally
    //   19	28	177	finally
    //   28	33	187	finally
    //   33	39	195	finally
    //   19	28	201	java/lang/Exception
    //   28	33	211	java/lang/Exception
    //   33	39	219	java/lang/Exception
  }
  
  public static void writeFile(String paramString1, String paramString2)
    throws Exception
  {
    if ((StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {
      throw new IOException("file or bytes empty");
    }
    writeFile(paramString1, paramString2.getBytes("utf-8"));
  }
  
  private static void writeFile(String paramString, byte[] paramArrayOfByte)
    throws IOException
  {
    if ((StringUtils.isEmpty(paramString)) || (paramArrayOfByte == null)) {
      throw new IOException("filename or byes empty");
    }
    try
    {
      writeFile(new File(paramString), paramArrayOfByte);
      return;
    }
    catch (Exception paramString)
    {
      throw new IOException(paramString);
    }
  }
}
