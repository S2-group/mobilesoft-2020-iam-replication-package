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

public class Whip
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
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload 5
    //   8: astore_3
    //   9: new 32	java/net/URL
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 36	java/net/URL:<init>	(Ljava/lang/String;)V
    //   17: astore 6
    //   19: aload 5
    //   21: astore_3
    //   22: aload 6
    //   24: invokevirtual 40	java/net/URL:getProtocol	()Ljava/lang/String;
    //   27: ldc 42
    //   29: invokevirtual 48	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   32: ifeq +25 -> 57
    //   35: aload 5
    //   37: astore_3
    //   38: new 50	java/io/FileInputStream
    //   41: dup
    //   42: aload 6
    //   44: invokevirtual 53	java/net/URL:getPath	()Ljava/lang/String;
    //   47: invokespecial 54	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   50: astore 7
    //   52: aconst_null
    //   53: astore_3
    //   54: goto +61 -> 115
    //   57: aload 5
    //   59: astore_3
    //   60: aload 6
    //   62: invokevirtual 58	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   65: checkcast 60	java/net/HttpURLConnection
    //   68: astore 6
    //   70: aload 6
    //   72: astore 5
    //   74: aload 6
    //   76: astore 4
    //   78: aload 6
    //   80: iconst_1
    //   81: invokevirtual 64	java/net/HttpURLConnection:setDoInput	(Z)V
    //   84: aload 6
    //   86: astore 5
    //   88: aload 6
    //   90: astore 4
    //   92: aload 6
    //   94: invokevirtual 68	java/net/HttpURLConnection:connect	()V
    //   97: aload 6
    //   99: astore 5
    //   101: aload 6
    //   103: astore 4
    //   105: aload 6
    //   107: invokevirtual 72	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   110: astore 7
    //   112: aload 6
    //   114: astore_3
    //   115: aload_3
    //   116: astore 5
    //   118: aload_3
    //   119: astore 4
    //   121: aload 7
    //   123: invokestatic 78	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   126: astore 6
    //   128: iload_1
    //   129: ifle +41 -> 170
    //   132: iload_2
    //   133: ifle +37 -> 170
    //   136: aload_3
    //   137: astore 5
    //   139: aload 6
    //   141: iload_1
    //   142: iload_1
    //   143: iconst_0
    //   144: invokestatic 84	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   147: astore 4
    //   149: aload 4
    //   151: astore_0
    //   152: goto +21 -> 173
    //   155: astore 7
    //   157: aload_3
    //   158: astore 4
    //   160: aload 6
    //   162: astore 5
    //   164: aload 7
    //   166: astore_3
    //   167: goto +30 -> 197
    //   170: aload 6
    //   172: astore_0
    //   173: aload_0
    //   174: astore 4
    //   176: aload_3
    //   177: ifnull +73 -> 250
    //   180: aload_3
    //   181: invokevirtual 87	java/net/HttpURLConnection:disconnect	()V
    //   184: aload_0
    //   185: areturn
    //   186: astore_0
    //   187: aload 5
    //   189: astore_3
    //   190: goto +63 -> 253
    //   193: astore_3
    //   194: aconst_null
    //   195: astore 5
    //   197: aload_3
    //   198: astore 6
    //   200: goto +12 -> 212
    //   203: astore_0
    //   204: goto +49 -> 253
    //   207: astore 6
    //   209: aconst_null
    //   210: astore 5
    //   212: aload 4
    //   214: astore_3
    //   215: ldc 89
    //   217: ldc 91
    //   219: iconst_1
    //   220: anewarray 4	java/lang/Object
    //   223: dup
    //   224: iconst_0
    //   225: aload_0
    //   226: aastore
    //   227: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   230: aload 6
    //   232: invokestatic 101	io/presage/goto/SaishuKusanagi:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   235: pop
    //   236: aload 4
    //   238: ifnull +8 -> 246
    //   241: aload 4
    //   243: invokevirtual 87	java/net/HttpURLConnection:disconnect	()V
    //   246: aload 5
    //   248: astore 4
    //   250: aload 4
    //   252: areturn
    //   253: aload_3
    //   254: ifnull +7 -> 261
    //   257: aload_3
    //   258: invokevirtual 87	java/net/HttpURLConnection:disconnect	()V
    //   261: aload_0
    //   262: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	263	0	paramString	String
    //   0	263	1	paramInt1	int
    //   0	263	2	paramInt2	int
    //   8	182	3	localObject1	Object
    //   193	5	3	localIOException1	java.io.IOException
    //   214	44	3	localObject2	Object
    //   1	250	4	localObject3	Object
    //   4	243	5	localObject4	Object
    //   17	182	6	localObject5	Object
    //   207	24	6	localIOException2	java.io.IOException
    //   50	72	7	localObject6	Object
    //   155	10	7	localIOException3	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   139	149	155	java/io/IOException
    //   78	84	186	finally
    //   92	97	186	finally
    //   105	112	186	finally
    //   121	128	186	finally
    //   139	149	186	finally
    //   78	84	193	java/io/IOException
    //   92	97	193	java/io/IOException
    //   105	112	193	java/io/IOException
    //   121	128	193	java/io/IOException
    //   9	19	203	finally
    //   22	35	203	finally
    //   38	52	203	finally
    //   60	70	203	finally
    //   215	236	203	finally
    //   9	19	207	java/io/IOException
    //   22	35	207	java/io/IOException
    //   38	52	207	java/io/IOException
    //   60	70	207	java/io/IOException
  }
  
  public static String a()
  {
    c localC = m.a(m.a(new File("/sys/class/net/wlan0/address")));
    String str = localC.j();
    if (localC != null) {
      localC.close();
    }
    return str;
  }
  
  public static String a(String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("[");
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      if (i == 0)
      {
        localStringBuilder1.append(paramArrayOfString[i]);
      }
      else
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(",");
        localStringBuilder2.append(paramArrayOfString[i]);
        localStringBuilder1.append(localStringBuilder2.toString());
      }
      i += 1;
    }
    localStringBuilder1.append("]");
    return localStringBuilder1.toString();
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
    while (i < j)
    {
      int k = paramString.charAt(i);
      if (k > 31)
      {
        if (k >= 127) {
          return false;
        }
        i += 1;
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  public static String b()
  {
    try
    {
      Object localObject = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime();
      String str2 = new SimpleDateFormat("Z").format((Date)localObject);
      localObject = str2;
      if (!a(str2)) {
        return "";
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      String str1 = "";
      return str1;
    }
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
      try
      {
        ApplicationInfo localApplicationInfo = c(paramContext);
        if (localApplicationInfo == null) {
          return "00000000-0000-0000-0000-000000000000";
        }
        try
        {
          long l = paramContext.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 128).firstInstallTime;
          paramContext = new StringBuilder();
          paramContext.append(l);
          paramContext.append("");
          return UUID.nameUUIDFromBytes(paramContext.toString().getBytes()).toString();
        }
        catch (Exception paramContext)
        {
          SaishuKusanagi.b("generateFakeAAID", "firstInstallTime", paramContext);
          return "00000000-0000-0000-0000-000000000000";
        }
        return "00000000-0000-0000-0000-000000000000";
      }
      catch (Exception paramContext)
      {
        SaishuKusanagi.b("generateFakeAAID", "firstSystemApp", paramContext);
      }
    }
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
