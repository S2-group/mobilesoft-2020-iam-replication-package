package com.mob.tools.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.tools.e;
import com.mob.tools.log.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class c
{
  private static c b;
  public Context a;
  
  private c(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
  }
  
  public static c a(Context paramContext)
  {
    if ((b == null) && (paramContext != null)) {
      b = new c(paramContext);
    }
    return b;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Base64.encodeToString(b.a(paramString2, paramString1), 0);
      paramString2 = paramString1;
      e.a().w(paramString2);
    }
    catch (Throwable paramString2)
    {
      try
      {
        if (paramString1.contains("\n")) {
          paramString2 = paramString1.replace("\n", "");
        }
        return paramString2;
      }
      catch (Throwable paramString2)
      {
        for (;;) {}
      }
      paramString2 = paramString2;
      paramString1 = null;
    }
    return paramString1;
  }
  
  /* Error */
  private static String c(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 72	java/io/BufferedReader
    //   5: dup
    //   6: new 74	java/io/InputStreamReader
    //   9: dup
    //   10: invokestatic 80	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: new 82	java/lang/StringBuilder
    //   16: dup
    //   17: ldc 84
    //   19: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   22: aload_0
    //   23: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: ldc 93
    //   28: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   34: invokevirtual 101	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   37: invokevirtual 107	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   40: invokespecial 110	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   43: invokespecial 113	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   46: astore_1
    //   47: aload_1
    //   48: astore_0
    //   49: aload_1
    //   50: invokevirtual 116	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   53: astore_2
    //   54: aload_2
    //   55: astore_0
    //   56: aload_1
    //   57: invokevirtual 119	java/io/BufferedReader:close	()V
    //   60: aload_0
    //   61: invokestatic 124	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   64: ifeq +50 -> 114
    //   67: aconst_null
    //   68: areturn
    //   69: astore_2
    //   70: aconst_null
    //   71: astore_1
    //   72: aload_1
    //   73: astore_0
    //   74: invokestatic 62	com/mob/tools/e:a	()Lcom/mob/tools/log/d;
    //   77: aload_2
    //   78: invokevirtual 127	com/mob/tools/log/d:d	(Ljava/lang/Throwable;)I
    //   81: pop
    //   82: aload_1
    //   83: ifnull +53 -> 136
    //   86: aload_1
    //   87: invokevirtual 119	java/io/BufferedReader:close	()V
    //   90: aconst_null
    //   91: astore_0
    //   92: goto -32 -> 60
    //   95: astore_0
    //   96: aconst_null
    //   97: astore_0
    //   98: goto -38 -> 60
    //   101: astore_0
    //   102: aload_2
    //   103: astore_1
    //   104: aload_1
    //   105: ifnull +7 -> 112
    //   108: aload_1
    //   109: invokevirtual 119	java/io/BufferedReader:close	()V
    //   112: aload_0
    //   113: athrow
    //   114: aload_0
    //   115: areturn
    //   116: astore_1
    //   117: goto -57 -> 60
    //   120: astore_1
    //   121: goto -9 -> 112
    //   124: astore_2
    //   125: aload_0
    //   126: astore_1
    //   127: aload_2
    //   128: astore_0
    //   129: goto -25 -> 104
    //   132: astore_2
    //   133: goto -61 -> 72
    //   136: aconst_null
    //   137: astore_0
    //   138: goto -78 -> 60
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	paramString	String
    //   46	63	1	localObject1	Object
    //   116	1	1	localThrowable1	Throwable
    //   120	1	1	localThrowable2	Throwable
    //   126	1	1	str1	String
    //   1	54	2	str2	String
    //   69	34	2	localThrowable3	Throwable
    //   124	4	2	localObject2	Object
    //   132	1	2	localThrowable4	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	47	69	java/lang/Throwable
    //   86	90	95	java/lang/Throwable
    //   2	47	101	finally
    //   56	60	116	java/lang/Throwable
    //   108	112	120	java/lang/Throwable
    //   49	54	124	finally
    //   74	82	124	finally
    //   49	54	132	java/lang/Throwable
  }
  
  private static String[] s()
    throws Throwable
  {
    int j = 0;
    Object localObject1 = NetworkInterface.getNetworkInterfaces();
    if (localObject1 == null) {
      return null;
    }
    Object localObject2 = Collections.list((Enumeration)localObject1);
    localObject1 = new HashMap();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (NetworkInterface)((Iterator)localObject2).next();
      localObject4 = ((NetworkInterface)localObject3).getHardwareAddress();
      if (localObject4 != null)
      {
        localObject5 = new StringBuilder();
        int k = localObject4.length;
        i = 0;
        while (i < k)
        {
          ((StringBuilder)localObject5).append(String.format("%02x:", new Object[] { Byte.valueOf(localObject4[i]) }));
          i += 1;
        }
        if (((StringBuilder)localObject5).length() > 0) {
          ((StringBuilder)localObject5).deleteCharAt(((StringBuilder)localObject5).length() - 1);
        }
        ((HashMap)localObject1).put(((NetworkInterface)localObject3).getName(), ((StringBuilder)localObject5).toString());
      }
    }
    localObject2 = new ArrayList(((HashMap)localObject1).keySet());
    Object localObject3 = new ArrayList();
    Object localObject4 = new ArrayList();
    Object localObject5 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    ArrayList localArrayList4 = new ArrayList();
    while (((ArrayList)localObject2).size() > 0)
    {
      String str = (String)((ArrayList)localObject2).remove(0);
      if (str.startsWith("wlan")) {
        ((ArrayList)localObject3).add(str);
      } else if (str.startsWith("eth")) {
        ((ArrayList)localObject4).add(str);
      } else if (str.startsWith("rev_rmnet")) {
        ((ArrayList)localObject5).add(str);
      } else if (str.startsWith("dummy")) {
        localArrayList1.add(str);
      } else if (str.startsWith("usbnet")) {
        localArrayList2.add(str);
      } else if (str.startsWith("rmnet_usb")) {
        localArrayList3.add(str);
      } else {
        localArrayList4.add(str);
      }
    }
    Collections.sort((List)localObject3);
    Collections.sort((List)localObject4);
    Collections.sort((List)localObject5);
    Collections.sort(localArrayList1);
    Collections.sort(localArrayList2);
    Collections.sort(localArrayList3);
    Collections.sort(localArrayList4);
    ((ArrayList)localObject2).addAll((Collection)localObject3);
    ((ArrayList)localObject2).addAll((Collection)localObject4);
    ((ArrayList)localObject2).addAll((Collection)localObject5);
    ((ArrayList)localObject2).addAll(localArrayList1);
    ((ArrayList)localObject2).addAll(localArrayList2);
    ((ArrayList)localObject2).addAll(localArrayList3);
    ((ArrayList)localObject2).addAll(localArrayList4);
    localObject3 = new String[((ArrayList)localObject2).size()];
    int i = j;
    while (i < localObject3.length)
    {
      localObject3[i] = ((String)((HashMap)localObject1).get(((ArrayList)localObject2).get(i)));
      i += 1;
    }
    return localObject3;
  }
  
  private String t()
  {
    Object localObject1 = (TelephonyManager)a("phone");
    if (localObject1 == null) {}
    for (;;)
    {
      return null;
      try
      {
        if (b("android.permission.READ_PHONE_STATE"))
        {
          localObject1 = ((TelephonyManager)localObject1).getDeviceId();
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            continue;
          }
          return localObject1;
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          e.a().w(localThrowable);
          Object localObject2 = null;
        }
      }
    }
  }
  
  private static String u()
  {
    if (Build.VERSION.SDK_INT >= 9) {
      try
      {
        Object localObject = Class.forName("android.os.SystemProperties");
        localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        e.a().d(localThrowable);
        return null;
      }
    }
    return null;
  }
  
  private String v()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)a("connectivity");
    if (localConnectivityManager == null) {
      return "none";
    }
    try
    {
      if (!b("android.permission.ACCESS_NETWORK_STATE")) {
        return "none";
      }
    }
    catch (Throwable localThrowable)
    {
      e.a().w(localThrowable);
      return "none";
    }
    Object localObject = localThrowable.getActiveNetworkInfo();
    if ((localObject == null) || (!((NetworkInfo)localObject).isAvailable())) {
      return "none";
    }
    int i = ((NetworkInfo)localObject).getType();
    switch (i)
    {
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    default: 
      return String.valueOf(i);
    case 1: 
      return "wifi";
    case 0: 
      localObject = (TelephonyManager)a("phone");
      if ((localObject != null) && (((TelephonyManager)localObject).getNetworkType() == 13)) {}
      for (i = 1; i != 0; i = 0) {
        return "4G";
      }
      localObject = (TelephonyManager)a("phone");
      if (localObject != null) {}
      switch (((TelephonyManager)localObject).getNetworkType())
      {
      default: 
        i = 0;
      }
      while (i != 0)
      {
        return "3G";
        i = 0;
        continue;
        i = 0;
        continue;
        i = 0;
        continue;
        i = 1;
        continue;
        i = 1;
        continue;
        i = 0;
        continue;
        i = 1;
        continue;
        i = 1;
        continue;
        i = 1;
        continue;
        i = 1;
        continue;
        i = 1;
        continue;
        i = 1;
        continue;
        i = 1;
        continue;
        i = 0;
        continue;
        i = 1;
        continue;
        i = 0;
      }
      return "2G";
    case 7: 
      return "bluetooth";
    case 8: 
      return "dummy";
    case 9: 
      return "ethernet";
    }
    return "wimax";
  }
  
  private static String w()
  {
    long l1 = System.currentTimeMillis();
    long l2 = SystemClock.elapsedRealtime();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(l1 ^ l2);
    Random localRandom = new Random();
    int i = 0;
    if (i < 40)
    {
      String str;
      if (localRandom.nextInt(2) % 2 == 0)
      {
        str = "char";
        label59:
        if (!"char".equalsIgnoreCase(str)) {
          break label105;
        }
        localStringBuffer.insert(i + 1, (char)(localRandom.nextInt(26) + 97));
      }
      for (;;)
      {
        i += 1;
        break;
        str = "num";
        break label59;
        label105:
        localStringBuffer.insert(localStringBuffer.length(), localRandom.nextInt(10));
      }
    }
    return localStringBuffer.toString().substring(0, 40);
  }
  
  public final Object a(String paramString)
  {
    try
    {
      paramString = this.a.getSystemService(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      e.a().w(paramString);
    }
    return null;
  }
  
  /* Error */
  public final String a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: getstatic 266	android/os/Build$VERSION:SDK_INT	I
    //   6: bipush 23
    //   8: if_icmplt +290 -> 298
    //   11: ldc_w 379
    //   14: invokestatic 381	com/mob/tools/utils/c:c	(Ljava/lang/String;)Ljava/lang/String;
    //   17: astore 4
    //   19: aload 4
    //   21: astore_3
    //   22: aload 4
    //   24: ifnonnull +203 -> 227
    //   27: invokestatic 135	java/net/NetworkInterface:getNetworkInterfaces	()Ljava/util/Enumeration;
    //   30: astore_3
    //   31: aload_3
    //   32: ifnull +232 -> 264
    //   35: aload_3
    //   36: invokestatic 141	java/util/Collections:list	(Ljava/util/Enumeration;)Ljava/util/ArrayList;
    //   39: invokeinterface 150 1 0
    //   44: astore_3
    //   45: aload_3
    //   46: invokeinterface 156 1 0
    //   51: ifeq +213 -> 264
    //   54: aload_3
    //   55: invokeinterface 160 1 0
    //   60: checkcast 131	java/net/NetworkInterface
    //   63: astore 4
    //   65: aload 4
    //   67: invokevirtual 384	java/net/NetworkInterface:getInetAddresses	()Ljava/util/Enumeration;
    //   70: astore 6
    //   72: aload 6
    //   74: ifnull -29 -> 45
    //   77: aload 6
    //   79: invokestatic 141	java/util/Collections:list	(Ljava/util/Enumeration;)Ljava/util/ArrayList;
    //   82: invokeinterface 150 1 0
    //   87: astore 6
    //   89: aload 6
    //   91: invokeinterface 156 1 0
    //   96: ifeq -51 -> 45
    //   99: aload 6
    //   101: invokeinterface 160 1 0
    //   106: checkcast 386	java/net/InetAddress
    //   109: astore 7
    //   111: aload 7
    //   113: invokevirtual 389	java/net/InetAddress:isLoopbackAddress	()Z
    //   116: ifne -27 -> 89
    //   119: aload 7
    //   121: instanceof 391
    //   124: ifeq -35 -> 89
    //   127: aload 4
    //   129: invokevirtual 164	java/net/NetworkInterface:getHardwareAddress	()[B
    //   132: astore 7
    //   134: aload 7
    //   136: ifnull -47 -> 89
    //   139: new 82	java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   146: astore_3
    //   147: aload 7
    //   149: arraylength
    //   150: istore_2
    //   151: iconst_0
    //   152: istore_1
    //   153: iload_1
    //   154: iload_2
    //   155: if_icmpge +49 -> 204
    //   158: aload_3
    //   159: ldc -89
    //   161: iconst_1
    //   162: anewarray 4	java/lang/Object
    //   165: dup
    //   166: iconst_0
    //   167: aload 7
    //   169: iload_1
    //   170: baload
    //   171: invokestatic 173	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   174: aastore
    //   175: invokestatic 177	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   178: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: pop
    //   182: iload_1
    //   183: iconst_1
    //   184: iadd
    //   185: istore_1
    //   186: goto -33 -> 153
    //   189: astore_3
    //   190: invokestatic 62	com/mob/tools/e:a	()Lcom/mob/tools/log/d;
    //   193: aload_3
    //   194: invokevirtual 127	com/mob/tools/log/d:d	(Ljava/lang/Throwable;)I
    //   197: pop
    //   198: aconst_null
    //   199: astore 4
    //   201: goto -182 -> 19
    //   204: aload_3
    //   205: invokevirtual 181	java/lang/StringBuilder:length	()I
    //   208: ifle +14 -> 222
    //   211: aload_3
    //   212: aload_3
    //   213: invokevirtual 181	java/lang/StringBuilder:length	()I
    //   216: iconst_1
    //   217: isub
    //   218: invokevirtual 185	java/lang/StringBuilder:deleteCharAt	(I)Ljava/lang/StringBuilder;
    //   221: pop
    //   222: aload_3
    //   223: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   226: astore_3
    //   227: aload_3
    //   228: astore 4
    //   230: aload_3
    //   231: ifnonnull +23 -> 254
    //   234: invokestatic 393	com/mob/tools/utils/c:s	()[Ljava/lang/String;
    //   237: astore 6
    //   239: aload_3
    //   240: astore 4
    //   242: aload 6
    //   244: arraylength
    //   245: ifle +9 -> 254
    //   248: aload 6
    //   250: iconst_0
    //   251: aaload
    //   252: astore 4
    //   254: aload 4
    //   256: ifnull +42 -> 298
    //   259: aload 4
    //   261: astore_3
    //   262: aload_3
    //   263: areturn
    //   264: aconst_null
    //   265: astore_3
    //   266: goto -39 -> 227
    //   269: astore_3
    //   270: invokestatic 62	com/mob/tools/e:a	()Lcom/mob/tools/log/d;
    //   273: aload_3
    //   274: invokevirtual 127	com/mob/tools/log/d:d	(Ljava/lang/Throwable;)I
    //   277: pop
    //   278: aconst_null
    //   279: astore_3
    //   280: goto -53 -> 227
    //   283: astore_3
    //   284: invokestatic 62	com/mob/tools/e:a	()Lcom/mob/tools/log/d;
    //   287: aload_3
    //   288: invokevirtual 127	com/mob/tools/log/d:d	(Ljava/lang/Throwable;)I
    //   291: pop
    //   292: aconst_null
    //   293: astore 4
    //   295: goto -41 -> 254
    //   298: aload_0
    //   299: ldc_w 315
    //   302: invokevirtual 250	com/mob/tools/utils/c:a	(Ljava/lang/String;)Ljava/lang/Object;
    //   305: checkcast 395	android/net/wifi/WifiManager
    //   308: astore 4
    //   310: aload 5
    //   312: astore_3
    //   313: aload 4
    //   315: ifnull -53 -> 262
    //   318: aload 4
    //   320: invokevirtual 399	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   323: astore 4
    //   325: aload 5
    //   327: astore_3
    //   328: aload 4
    //   330: ifnull -68 -> 262
    //   333: aload 4
    //   335: invokevirtual 404	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   338: astore 4
    //   340: aload 5
    //   342: astore_3
    //   343: aload 4
    //   345: ifnull -83 -> 262
    //   348: aload 4
    //   350: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	351	0	this	c
    //   152	34	1	i	int
    //   150	6	2	j	int
    //   21	138	3	localObject1	Object
    //   189	34	3	localThrowable1	Throwable
    //   226	40	3	localObject2	Object
    //   269	5	3	localThrowable2	Throwable
    //   279	1	3	localObject3	Object
    //   283	5	3	localThrowable3	Throwable
    //   312	31	3	localObject4	Object
    //   17	332	4	localObject5	Object
    //   1	340	5	localObject6	Object
    //   70	179	6	localObject7	Object
    //   109	59	7	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   11	19	189	java/lang/Throwable
    //   27	31	269	java/lang/Throwable
    //   35	45	269	java/lang/Throwable
    //   45	72	269	java/lang/Throwable
    //   77	89	269	java/lang/Throwable
    //   89	134	269	java/lang/Throwable
    //   139	151	269	java/lang/Throwable
    //   158	182	269	java/lang/Throwable
    //   204	222	269	java/lang/Throwable
    //   222	227	269	java/lang/Throwable
    //   234	239	283	java/lang/Throwable
    //   242	248	283	java/lang/Throwable
  }
  
  public final String b()
  {
    String str2 = t();
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      str1 = str2;
      if (Build.VERSION.SDK_INT >= 9) {
        str1 = u();
      }
    }
    return str1;
  }
  
  public final boolean b(String paramString)
    throws Throwable
  {
    if (Build.VERSION.SDK_INT >= 23) {
      try
      {
        g.a("android.content.Context");
        paramString = (Integer)g.a(this.a, "checkSelfPermission", new Object[] { paramString });
        if (paramString == null) {
          i = -1;
        } else {
          i = paramString.intValue();
        }
      }
      catch (Throwable paramString)
      {
        e.a().w(paramString);
        i = -1;
      }
    }
    this.a.checkPermission(paramString, Process.myPid(), Process.myUid());
    int i = this.a.getPackageManager().checkPermission(paramString, this.a.getPackageName());
    while (i != 0) {
      return false;
    }
    return true;
  }
  
  public final String c()
  {
    int[] arrayOfInt = R.getScreenSize(this.a);
    if (this.a.getResources().getConfiguration().orientation == 1) {
      return arrayOfInt[0] + "x" + arrayOfInt[1];
    }
    return arrayOfInt[1] + "x" + arrayOfInt[0];
  }
  
  public final String d()
  {
    Object localObject = (TelephonyManager)a("phone");
    if (localObject == null) {
      localObject = "-1";
    }
    String str;
    do
    {
      return localObject;
      str = ((TelephonyManager)localObject).getSimOperator();
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return "-1";
  }
  
  public final String e()
  {
    Object localObject = (TelephonyManager)a("phone");
    if (localObject == null) {}
    for (;;)
    {
      return null;
      try
      {
        if (b("android.permission.READ_PHONE_STATE"))
        {
          localObject = ((TelephonyManager)localObject).getSimOperatorName();
          boolean bool = TextUtils.isEmpty((CharSequence)localObject);
          if (bool) {
            localObject = null;
          }
          return localObject;
        }
      }
      catch (Throwable localThrowable)
      {
        e.a().w(localThrowable);
      }
    }
    return null;
  }
  
  public final String f()
  {
    try
    {
      Object localObject = BluetoothAdapter.getDefaultAdapter();
      if ((localObject != null) && (b("android.permission.BLUETOOTH")))
      {
        localObject = ((BluetoothAdapter)localObject).getName();
        return localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      e.a().d(localThrowable);
    }
    return null;
  }
  
  public final String g()
  {
    try
    {
      String str = b.b(this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 64).signatures[0].toByteArray());
      return str;
    }
    catch (Exception localException)
    {
      e.a().w(localException);
    }
    return null;
  }
  
  public final String h()
  {
    String str2 = v().toLowerCase();
    String str1;
    if ((TextUtils.isEmpty(str2)) || ("none".equals(str2))) {
      str1 = "none";
    }
    do
    {
      return str1;
      if (str2.startsWith("wifi")) {
        return "wifi";
      }
      if (str2.startsWith("4g")) {
        return "4g";
      }
      if (str2.startsWith("3g")) {
        return "3g";
      }
      if (str2.startsWith("2g")) {
        return "2g";
      }
      str1 = str2;
    } while (!str2.startsWith("bluetooth"));
    return "bluetooth";
  }
  
  public final String i()
  {
    localObject3 = null;
    for (;;)
    {
      try
      {
        boolean bool = o();
        if (bool) {
          continue;
        }
        localObject1 = null;
      }
      catch (Throwable localThrowable1)
      {
        Object localObject1;
        e.a().w(localThrowable1);
        Object localObject2 = null;
        continue;
        try
        {
          localObject2 = a();
          localObject4 = b();
          String str = Build.MODEL;
          localObject2 = (String)localObject2 + ":" + (String)localObject4 + ":" + str;
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            continue;
          }
          localObject2 = null;
          localObject2 = b.a((byte[])localObject2);
        }
        catch (Throwable localThrowable2)
        {
          e.a().d(localThrowable2);
          localThrowable3 = localThrowable4;
          continue;
          continue;
        }
        if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (((String)localObject2).length() >= 40)) {
          continue;
        }
        localObject2 = w();
        localObject3 = localObject2;
        if (localObject2 == null) {
          continue;
        }
        localObject3 = localObject2;
        try
        {
          if (!o()) {
            continue;
          }
          localObject3 = new File(R.getCacheRoot(this.a), ".dk");
          if (((File)localObject3).exists()) {
            ((File)localObject3).delete();
          }
          localObject3 = new ObjectOutputStream(new FileOutputStream((File)localObject3));
          ((ObjectOutputStream)localObject3).writeObject(((String)localObject2).toCharArray());
          ((ObjectOutputStream)localObject3).flush();
          ((ObjectOutputStream)localObject3).close();
          return localObject2;
        }
        catch (Throwable localThrowable4)
        {
          e.a().w(localThrowable4);
          return localObject2;
        }
        localObject2 = ((String)localObject2).getBytes("utf-8");
        Object localObject4 = MessageDigest.getInstance("SHA-1");
        ((MessageDigest)localObject4).update((byte[])localObject2);
        localObject2 = ((MessageDigest)localObject4).digest();
        continue;
        Throwable localThrowable3 = null;
        continue;
      }
      if ((TextUtils.isEmpty((CharSequence)localObject1)) || (((String)localObject1).length() < 40)) {
        continue;
      }
      localObject3 = localObject1;
      return localObject3;
      localObject1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "ShareSDK");
      if (((File)localObject1).exists())
      {
        localObject1 = new File((File)localObject1, ".dk");
        if ((((File)localObject1).exists()) && (((File)localObject1).renameTo(new File(R.getCacheRoot(this.a), ".dk")))) {
          ((File)localObject1).delete();
        }
      }
      localObject1 = new File(R.getCacheRoot(this.a), ".dk");
      if (!((File)localObject1).exists())
      {
        localObject1 = null;
      }
      else
      {
        localObject4 = new ObjectInputStream(new FileInputStream((File)localObject1));
        localObject1 = ((ObjectInputStream)localObject4).readObject();
        if ((localObject1 == null) || (!(localObject1 instanceof char[]))) {
          continue;
        }
        localObject1 = String.valueOf((char[])localObject1);
        ((ObjectInputStream)localObject4).close();
      }
    }
  }
  
  public final String j()
  {
    String str = this.a.getApplicationInfo().name;
    if (str != null) {
      return str;
    }
    int i = this.a.getApplicationInfo().labelRes;
    if (i > 0) {
      return this.a.getString(i);
    }
    return String.valueOf(this.a.getApplicationInfo().nonLocalizedLabel);
  }
  
  public final int k()
  {
    try
    {
      int i = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Throwable localThrowable)
    {
      e.a().d(localThrowable);
    }
    return 0;
  }
  
  public final String l()
  {
    try
    {
      String str = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionName;
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.a().d(localThrowable);
    }
    return "1.0";
  }
  
  public final ArrayList<HashMap<String, String>> m()
  {
    for (;;)
    {
      ArrayList localArrayList;
      int i;
      int j;
      try
      {
        localPackageManager = this.a.getPackageManager();
        Object localObject = localPackageManager.getInstalledPackages(0);
        localArrayList = new ArrayList();
        localObject = ((List)localObject).iterator();
        if (!((Iterator)localObject).hasNext()) {
          break label199;
        }
        localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) != 1) {
          break label184;
        }
        i = 1;
        if ((localPackageInfo.applicationInfo.flags & 0x80) != 1) {
          break label189;
        }
        j = 1;
      }
      catch (Throwable localThrowable)
      {
        PackageManager localPackageManager;
        PackageInfo localPackageInfo;
        HashMap localHashMap;
        e.a().w(localThrowable);
        return new ArrayList();
      }
      if (i == 0)
      {
        localHashMap = new HashMap();
        localHashMap.put("pkg", localPackageInfo.packageName);
        localHashMap.put("name", localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
        localHashMap.put("version", localPackageInfo.versionName);
        localArrayList.add(localHashMap);
        continue;
        label184:
        i = 0;
        continue;
        label189:
        j = 0;
        label199:
        while ((i == 0) && (j == 0))
        {
          i = 0;
          break;
          return localArrayList;
        }
        i = 1;
      }
    }
  }
  
  /* Error */
  public final String n()
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 708
    //   4: invokevirtual 256	com/mob/tools/utils/c:b	(Ljava/lang/String;)Z
    //   7: istore_1
    //   8: iload_1
    //   9: ifeq +99 -> 108
    //   12: aload_0
    //   13: ldc_w 710
    //   16: invokevirtual 250	com/mob/tools/utils/c:a	(Ljava/lang/String;)Ljava/lang/Object;
    //   19: checkcast 712	android/app/ActivityManager
    //   22: astore_2
    //   23: aload_2
    //   24: ifnonnull +19 -> 43
    //   27: aconst_null
    //   28: areturn
    //   29: astore_2
    //   30: invokestatic 62	com/mob/tools/e:a	()Lcom/mob/tools/log/d;
    //   33: aload_2
    //   34: invokevirtual 68	com/mob/tools/log/d:w	(Ljava/lang/Throwable;)I
    //   37: pop
    //   38: iconst_0
    //   39: istore_1
    //   40: goto -32 -> 8
    //   43: getstatic 266	android/os/Build$VERSION:SDK_INT	I
    //   46: bipush 20
    //   48: if_icmpgt +24 -> 72
    //   51: aload_2
    //   52: iconst_1
    //   53: invokevirtual 715	android/app/ActivityManager:getRunningTasks	(I)Ljava/util/List;
    //   56: iconst_0
    //   57: invokeinterface 716 2 0
    //   62: checkcast 718	android/app/ActivityManager$RunningTaskInfo
    //   65: getfield 722	android/app/ActivityManager$RunningTaskInfo:topActivity	Landroid/content/ComponentName;
    //   68: invokevirtual 725	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   71: areturn
    //   72: aload_2
    //   73: invokevirtual 729	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   76: iconst_0
    //   77: invokeinterface 716 2 0
    //   82: checkcast 731	android/app/ActivityManager$RunningAppProcessInfo
    //   85: getfield 734	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   88: ldc_w 597
    //   91: invokevirtual 738	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   94: iconst_0
    //   95: aaload
    //   96: astore_2
    //   97: aload_2
    //   98: areturn
    //   99: astore_2
    //   100: invokestatic 62	com/mob/tools/e:a	()Lcom/mob/tools/log/d;
    //   103: aload_2
    //   104: invokevirtual 68	com/mob/tools/log/d:w	(Ljava/lang/Throwable;)I
    //   107: pop
    //   108: aconst_null
    //   109: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	110	0	this	c
    //   7	33	1	bool	boolean
    //   22	2	2	localActivityManager	android.app.ActivityManager
    //   29	44	2	localThrowable1	Throwable
    //   96	2	2	str	String
    //   99	5	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	8	29	java/lang/Throwable
    //   12	23	99	java/lang/Throwable
    //   43	72	99	java/lang/Throwable
    //   72	97	99	java/lang/Throwable
  }
  
  public final boolean o()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if (b("android.permission.WRITE_EXTERNAL_STORAGE"))
      {
        boolean bool3 = "mounted".equals(Environment.getExternalStorageState());
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Throwable localThrowable)
    {
      e.a().w(localThrowable);
    }
    return false;
  }
  
  public final String p()
  {
    try
    {
      Intent localIntent = new Intent("com.google.android.gms.ads.identifier.service.START");
      localIntent.setPackage("com.google.android.gms");
      localObject1 = new a((byte)0);
      this.a.bindService(localIntent, (ServiceConnection)localObject1, 1);
      if (((a)localObject1).a) {
        throw new IllegalStateException();
      }
    }
    catch (Throwable localThrowable)
    {
      e.a().d(localThrowable);
      return null;
    }
    ((a)localObject1).a = true;
    Object localObject2 = (IBinder)((a)localObject1).b.poll(1500L, TimeUnit.MILLISECONDS);
    Parcel localParcel = Parcel.obtain();
    Object localObject1 = Parcel.obtain();
    localParcel.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    ((IBinder)localObject2).transact(1, localParcel, (Parcel)localObject1, 0);
    ((Parcel)localObject1).readException();
    localObject2 = ((Parcel)localObject1).readString();
    ((Parcel)localObject1).recycle();
    localParcel.recycle();
    e.a().i("getAdvertisingID === " + (String)localObject2, new Object[0]);
    return localObject2;
  }
  
  public final int q()
  {
    try
    {
      if (b("android.permission.ACCESS_COARSE_LOCATION"))
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)a("phone");
        if (localTelephonyManager != null)
        {
          int i = ((GsmCellLocation)localTelephonyManager.getCellLocation()).getCid();
          return i;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      e.a().d(localThrowable);
    }
    return -1;
  }
  
  public final int r()
  {
    try
    {
      if (b("android.permission.ACCESS_COARSE_LOCATION"))
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)a("phone");
        if (localTelephonyManager != null)
        {
          int i = ((GsmCellLocation)localTelephonyManager.getCellLocation()).getLac();
          return i;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      e.a().d(localThrowable);
    }
    return -1;
  }
  
  private final class a
    implements ServiceConnection
  {
    boolean a = false;
    final BlockingQueue<IBinder> b = new LinkedBlockingQueue();
    
    private a() {}
    
    public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      try
      {
        this.b.put(paramIBinder);
        return;
      }
      catch (Throwable paramComponentName)
      {
        e.a().w(paramComponentName);
      }
    }
    
    public final void onServiceDisconnected(ComponentName paramComponentName) {}
  }
}
