package io.presage.goto;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.TypedValue;
import b.c;
import b.m;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public class Maxima
{
  public static int a(Context paramContext, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getResources().getDisplayMetrics());
  }
  
  /* Error */
  public static android.graphics.Bitmap a(String paramString, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: aload_3
    //   6: astore 6
    //   8: new 32	java/net/URL
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 36	java/net/URL:<init>	(Ljava/lang/String;)V
    //   16: astore 5
    //   18: aload_3
    //   19: astore 6
    //   21: aload 5
    //   23: invokevirtual 40	java/net/URL:getProtocol	()Ljava/lang/String;
    //   26: ldc 42
    //   28: invokevirtual 48	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   31: ifeq +71 -> 102
    //   34: aload_3
    //   35: astore 6
    //   37: new 50	java/io/FileInputStream
    //   40: dup
    //   41: aload 5
    //   43: invokevirtual 53	java/net/URL:getPath	()Ljava/lang/String;
    //   46: invokespecial 54	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   49: astore 5
    //   51: aconst_null
    //   52: astore_3
    //   53: aload 5
    //   55: astore 4
    //   57: aload 4
    //   59: invokestatic 60	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   62: astore 5
    //   64: iload_1
    //   65: ifle +181 -> 246
    //   68: iload_2
    //   69: ifle +177 -> 246
    //   72: aload 5
    //   74: iload_1
    //   75: iload_1
    //   76: iconst_0
    //   77: invokestatic 66	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   80: astore 4
    //   82: aload 4
    //   84: astore_0
    //   85: aload_0
    //   86: astore 5
    //   88: aload_3
    //   89: ifnull +10 -> 99
    //   92: aload_3
    //   93: invokevirtual 72	java/net/HttpURLConnection:disconnect	()V
    //   96: aload_0
    //   97: astore 5
    //   99: aload 5
    //   101: areturn
    //   102: aload_3
    //   103: astore 6
    //   105: aload 5
    //   107: invokevirtual 76	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   110: checkcast 68	java/net/HttpURLConnection
    //   113: astore_3
    //   114: aload_3
    //   115: iconst_1
    //   116: invokevirtual 80	java/net/HttpURLConnection:setDoInput	(Z)V
    //   119: aload_3
    //   120: invokevirtual 83	java/net/HttpURLConnection:connect	()V
    //   123: aload_3
    //   124: invokevirtual 87	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   127: astore 4
    //   129: goto -72 -> 57
    //   132: astore 5
    //   134: aconst_null
    //   135: astore_3
    //   136: aload 4
    //   138: astore 6
    //   140: ldc 89
    //   142: ldc 91
    //   144: iconst_1
    //   145: anewarray 4	java/lang/Object
    //   148: dup
    //   149: iconst_0
    //   150: aload_0
    //   151: aastore
    //   152: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   155: aload 5
    //   157: invokestatic 101	io/presage/goto/SaishuKusanagi:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   160: pop
    //   161: aload_3
    //   162: astore 5
    //   164: aload 4
    //   166: ifnull -67 -> 99
    //   169: aload 4
    //   171: invokevirtual 72	java/net/HttpURLConnection:disconnect	()V
    //   174: aload_3
    //   175: areturn
    //   176: astore_0
    //   177: aload 6
    //   179: ifnull +8 -> 187
    //   182: aload 6
    //   184: invokevirtual 72	java/net/HttpURLConnection:disconnect	()V
    //   187: aload_0
    //   188: athrow
    //   189: astore_0
    //   190: aload_3
    //   191: astore 6
    //   193: goto -16 -> 177
    //   196: astore_0
    //   197: aload_3
    //   198: astore 6
    //   200: goto -23 -> 177
    //   203: astore 5
    //   205: aconst_null
    //   206: astore 6
    //   208: aload_3
    //   209: astore 4
    //   211: aload 6
    //   213: astore_3
    //   214: goto -78 -> 136
    //   217: astore 5
    //   219: aconst_null
    //   220: astore 6
    //   222: aload_3
    //   223: astore 4
    //   225: aload 6
    //   227: astore_3
    //   228: goto -92 -> 136
    //   231: astore 6
    //   233: aload_3
    //   234: astore 4
    //   236: aload 5
    //   238: astore_3
    //   239: aload 6
    //   241: astore 5
    //   243: goto -107 -> 136
    //   246: aload 5
    //   248: astore_0
    //   249: goto -164 -> 85
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	252	0	paramString	String
    //   0	252	1	paramInt1	int
    //   0	252	2	paramInt2	int
    //   1	238	3	localObject1	Object
    //   3	232	4	localObject2	Object
    //   16	90	5	localObject3	Object
    //   132	24	5	localIOException1	IOException
    //   162	1	5	localObject4	Object
    //   203	1	5	localIOException2	IOException
    //   217	20	5	localIOException3	IOException
    //   241	6	5	localIOException4	IOException
    //   6	220	6	localObject5	Object
    //   231	9	6	localIOException5	IOException
    // Exception table:
    //   from	to	target	type
    //   8	18	132	java/io/IOException
    //   21	34	132	java/io/IOException
    //   37	51	132	java/io/IOException
    //   105	114	132	java/io/IOException
    //   8	18	176	finally
    //   21	34	176	finally
    //   37	51	176	finally
    //   105	114	176	finally
    //   140	161	176	finally
    //   114	129	189	finally
    //   57	64	196	finally
    //   72	82	196	finally
    //   114	129	203	java/io/IOException
    //   57	64	217	java/io/IOException
    //   72	82	231	java/io/IOException
  }
  
  public static String a()
    throws IOException
  {
    c localC = m.a(m.a(new File("/sys/class/net/wlan0/address")));
    String str = localC.i();
    if (localC != null) {
      localC.close();
    }
    return str;
  }
  
  public static String a(String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    int i = 0;
    if (i < paramArrayOfString.length)
    {
      if (i == 0) {
        localStringBuilder.append(paramArrayOfString[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append("," + paramArrayOfString[i]);
      }
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public static boolean a(Context paramContext)
  {
    return ((KeyguardManager)paramContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  public static boolean a(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    int j = paramString.length();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label43;
      }
      int k = paramString.charAt(i);
      if ((k <= 31) || (k >= 127)) {
        break;
      }
      i += 1;
    }
    label43:
    return true;
  }
  
  public static String b()
  {
    try
    {
      Object localObject = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime();
      String str = new SimpleDateFormat("Z").format((Date)localObject);
      localObject = str;
      if (!a(str)) {
        localObject = "";
      }
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      String str = a();
      if ((str != null) && (!str.isEmpty()))
      {
        str = UUID.nameUUIDFromBytes(str.getBytes()).toString();
        return str;
      }
    }
    catch (Exception localException)
    {
      SaishuKusanagi.b("generateFakeAAID", "mac", localException);
      ApplicationInfo localApplicationInfo;
      try
      {
        localApplicationInfo = c(paramContext);
        if (localApplicationInfo == null) {
          return "00000000-0000-0000-0000-000000000000";
        }
      }
      catch (Exception paramContext)
      {
        SaishuKusanagi.b("generateFakeAAID", "firstSystemApp", paramContext);
        return "00000000-0000-0000-0000-000000000000";
      }
      try
      {
        long l = paramContext.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 128).firstInstallTime;
        return UUID.nameUUIDFromBytes((l + "").getBytes()).toString();
      }
      catch (Exception paramContext)
      {
        SaishuKusanagi.b("generateFakeAAID", "firstInstallTime", paramContext);
      }
    }
    return "00000000-0000-0000-0000-000000000000";
  }
  
  private static ApplicationInfo c(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramContext.iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (((localApplicationInfo.flags & 0x1) != 0) && (localApplicationInfo.packageName != null)) {
        localArrayList.add(localApplicationInfo);
      }
    }
    if (paramContext.size() == 0) {
      return null;
    }
    Collections.sort(localArrayList, new Comparator()
    {
      public int a(ApplicationInfo paramAnonymousApplicationInfo1, ApplicationInfo paramAnonymousApplicationInfo2)
      {
        return paramAnonymousApplicationInfo1.packageName.compareTo(paramAnonymousApplicationInfo2.packageName);
      }
    });
    return (ApplicationInfo)localArrayList.get(0);
  }
}
