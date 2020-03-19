package com.mobi.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class public
{
  public public() {}
  
  public static int jdMethod_do(Context paramContext)
  {
    if (paramContext == null) {
      return -1;
    }
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 1)) {
        return 1;
      }
      if (localNetworkInfo != null)
      {
        if (localNetworkInfo.getType() != 0) {
          return -1;
        }
        int i = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType();
        if (i != -1)
        {
          switch (i)
          {
          default: 
            return -1;
          case 13: 
            return 4;
          case 3: 
          case 5: 
          case 6: 
          case 8: 
          case 9: 
          case 10: 
          case 12: 
          case 14: 
          case 15: 
            return 3;
          }
          return 2;
        }
        return -1;
      }
      return -1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  public static int jdMethod_do(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static int jdMethod_do(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return -1;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramString, 0);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    paramContext = null;
    if (paramContext != null) {
      return localPackageManager.getApplicationEnabledSetting(paramString);
    }
    return -1;
  }
  
  public static long jdMethod_do()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(14, -(localCalendar.get(15) + localCalendar.get(16)));
    return localCalendar.getTimeInMillis();
  }
  
  public static long jdMethod_do(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramContext = paramContext.getFileStreamPath(paramString);
      if (!paramContext.isDirectory())
      {
        if (!paramContext.exists()) {
          return -1L;
        }
        return paramContext.length();
      }
      return -1L;
    }
    return -1L;
  }
  
  public static BridgeModel jdMethod_do(Context paramContext)
  {
    BridgeModel localBridgeModel = new BridgeModel();
    localBridgeModel.className = jdMethod_do(paramContext, "c_n", "rtY-q7AjkVYCHMYKktYKD-zEWU3EWX==");
    localBridgeModel.actionName = jdMethod_do(paramContext, "c_ac", "rMAwD71Ek9FPotlEW73EkUzEkgYFNz/eledcamF1b-3ne-3n");
    localBridgeModel.flagName = jdMethod_do(paramContext, "c_f", "rtb-b7dJkfc=");
    localBridgeModel.flagParames = resetting.jdMethod_do(paramContext, "c_fp", 32);
    localBridgeModel.setName = jdMethod_do(paramContext, "c_set", "qM3KeU1CoM1Bkl==");
    localBridgeModel.putName = jdMethod_do(paramContext, "c_put", "q93KbaJKq7S=");
    localBridgeModel.putParames = jdMethod_do(paramContext, "c_pp", "q737kaFgkaT=");
    localBridgeModel.send = jdMethod_do(paramContext, "c_s", "qM3EkSFgHM1-rM1iWX==");
    return localBridgeModel;
  }
  
  /* Error */
  public static <T> T jdMethod_do(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ifnonnull +8 -> 12
    //   7: ldc 2
    //   9: monitorexit
    //   10: aconst_null
    //   11: areturn
    //   12: aload_0
    //   13: aload_1
    //   14: invokevirtual 107	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   17: astore_0
    //   18: aload_0
    //   19: ifnull +121 -> 140
    //   22: aload_0
    //   23: invokevirtual 116	java/io/File:exists	()Z
    //   26: ifne +6 -> 32
    //   29: goto +111 -> 140
    //   32: aload_0
    //   33: invokevirtual 121	java/io/File:length	()J
    //   36: lstore_2
    //   37: lload_2
    //   38: ldc2_w 197
    //   41: lcmp
    //   42: ifle +8 -> 50
    //   45: ldc 2
    //   47: monitorexit
    //   48: aconst_null
    //   49: areturn
    //   50: new 200	java/io/ObjectInputStream
    //   53: dup
    //   54: new 202	java/io/FileInputStream
    //   57: dup
    //   58: aload_0
    //   59: invokespecial 205	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   62: invokespecial 208	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   65: astore_1
    //   66: aload_1
    //   67: astore_0
    //   68: aload_1
    //   69: invokevirtual 212	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   72: astore 4
    //   74: aload_1
    //   75: ifnull +39 -> 114
    //   78: aload_1
    //   79: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   82: goto +32 -> 114
    //   85: astore_0
    //   86: new 217	java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   93: astore_1
    //   94: aload_1
    //   95: ldc -36
    //   97: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: pop
    //   101: aload_1
    //   102: aload_0
    //   103: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: aload_1
    //   108: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   111: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   114: ldc 2
    //   116: monitorexit
    //   117: aload 4
    //   119: areturn
    //   120: astore 4
    //   122: goto +33 -> 155
    //   125: astore 4
    //   127: goto +118 -> 245
    //   130: astore 4
    //   132: goto +199 -> 331
    //   135: astore 4
    //   137: goto +280 -> 417
    //   140: ldc 2
    //   142: monitorexit
    //   143: aconst_null
    //   144: areturn
    //   145: astore_0
    //   146: aconst_null
    //   147: astore_1
    //   148: goto +363 -> 511
    //   151: astore 4
    //   153: aconst_null
    //   154: astore_1
    //   155: aload_1
    //   156: astore_0
    //   157: new 217	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   164: astore 5
    //   166: aload_1
    //   167: astore_0
    //   168: aload 5
    //   170: ldc -17
    //   172: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: pop
    //   176: aload_1
    //   177: astore_0
    //   178: aload 5
    //   180: aload 4
    //   182: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   185: pop
    //   186: aload_1
    //   187: astore_0
    //   188: aload 5
    //   190: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   196: aload_1
    //   197: ifnull +302 -> 499
    //   200: aload_1
    //   201: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   204: goto +295 -> 499
    //   207: astore_0
    //   208: new 217	java/lang/StringBuilder
    //   211: dup
    //   212: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   215: astore_1
    //   216: aload_1
    //   217: ldc -36
    //   219: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: pop
    //   223: aload_1
    //   224: aload_0
    //   225: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   228: pop
    //   229: aload_1
    //   230: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: astore_0
    //   234: aload_0
    //   235: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   238: goto +261 -> 499
    //   241: astore 4
    //   243: aconst_null
    //   244: astore_1
    //   245: aload_1
    //   246: astore_0
    //   247: new 217	java/lang/StringBuilder
    //   250: dup
    //   251: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   254: astore 5
    //   256: aload_1
    //   257: astore_0
    //   258: aload 5
    //   260: ldc -17
    //   262: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: pop
    //   266: aload_1
    //   267: astore_0
    //   268: aload 5
    //   270: aload 4
    //   272: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   275: pop
    //   276: aload_1
    //   277: astore_0
    //   278: aload 5
    //   280: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   283: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   286: aload_1
    //   287: ifnull +212 -> 499
    //   290: aload_1
    //   291: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   294: goto +205 -> 499
    //   297: astore_0
    //   298: new 217	java/lang/StringBuilder
    //   301: dup
    //   302: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   305: astore_1
    //   306: aload_1
    //   307: ldc -36
    //   309: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: pop
    //   313: aload_1
    //   314: aload_0
    //   315: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: aload_1
    //   320: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: astore_0
    //   324: goto -90 -> 234
    //   327: astore 4
    //   329: aconst_null
    //   330: astore_1
    //   331: aload_1
    //   332: astore_0
    //   333: new 217	java/lang/StringBuilder
    //   336: dup
    //   337: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   340: astore 5
    //   342: aload_1
    //   343: astore_0
    //   344: aload 5
    //   346: ldc -17
    //   348: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: pop
    //   352: aload_1
    //   353: astore_0
    //   354: aload 5
    //   356: aload 4
    //   358: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   361: pop
    //   362: aload_1
    //   363: astore_0
    //   364: aload 5
    //   366: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   369: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   372: aload_1
    //   373: ifnull +126 -> 499
    //   376: aload_1
    //   377: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   380: goto +119 -> 499
    //   383: astore_0
    //   384: new 217	java/lang/StringBuilder
    //   387: dup
    //   388: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   391: astore_1
    //   392: aload_1
    //   393: ldc -36
    //   395: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   398: pop
    //   399: aload_1
    //   400: aload_0
    //   401: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   404: pop
    //   405: aload_1
    //   406: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   409: astore_0
    //   410: goto -176 -> 234
    //   413: astore 4
    //   415: aconst_null
    //   416: astore_1
    //   417: aload_1
    //   418: astore_0
    //   419: new 217	java/lang/StringBuilder
    //   422: dup
    //   423: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   426: astore 5
    //   428: aload_1
    //   429: astore_0
    //   430: aload 5
    //   432: ldc -17
    //   434: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: pop
    //   438: aload_1
    //   439: astore_0
    //   440: aload 5
    //   442: aload 4
    //   444: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   447: pop
    //   448: aload_1
    //   449: astore_0
    //   450: aload 5
    //   452: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   455: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   458: aload_1
    //   459: ifnull +40 -> 499
    //   462: aload_1
    //   463: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   466: goto +33 -> 499
    //   469: astore_0
    //   470: new 217	java/lang/StringBuilder
    //   473: dup
    //   474: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   477: astore_1
    //   478: aload_1
    //   479: ldc -36
    //   481: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   484: pop
    //   485: aload_1
    //   486: aload_0
    //   487: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   490: pop
    //   491: aload_1
    //   492: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   495: astore_0
    //   496: goto -262 -> 234
    //   499: ldc 2
    //   501: monitorexit
    //   502: aconst_null
    //   503: areturn
    //   504: astore 4
    //   506: aload_0
    //   507: astore_1
    //   508: aload 4
    //   510: astore_0
    //   511: aload_1
    //   512: ifnull +47 -> 559
    //   515: aload_1
    //   516: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   519: goto +40 -> 559
    //   522: astore_0
    //   523: goto +38 -> 561
    //   526: astore_1
    //   527: new 217	java/lang/StringBuilder
    //   530: dup
    //   531: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   534: astore 4
    //   536: aload 4
    //   538: ldc -36
    //   540: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: pop
    //   544: aload 4
    //   546: aload_1
    //   547: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   550: pop
    //   551: aload 4
    //   553: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   556: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   559: aload_0
    //   560: athrow
    //   561: ldc 2
    //   563: monitorexit
    //   564: aload_0
    //   565: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	566	0	paramContext	Context
    //   0	566	1	paramString	String
    //   36	2	2	l	long
    //   72	46	4	localObject1	Object
    //   120	1	4	localError1	Error
    //   125	1	4	localException1	Exception
    //   130	1	4	localIOException1	java.io.IOException
    //   135	1	4	localFileNotFoundException1	java.io.FileNotFoundException
    //   151	30	4	localError2	Error
    //   241	30	4	localException2	Exception
    //   327	30	4	localIOException2	java.io.IOException
    //   413	30	4	localFileNotFoundException2	java.io.FileNotFoundException
    //   504	5	4	localObject2	Object
    //   534	18	4	localStringBuilder1	StringBuilder
    //   164	287	5	localStringBuilder2	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   78	82	85	java/io/IOException
    //   68	74	120	java/lang/Error
    //   68	74	125	java/lang/Exception
    //   68	74	130	java/io/IOException
    //   68	74	135	java/io/FileNotFoundException
    //   12	18	145	finally
    //   22	29	145	finally
    //   32	37	145	finally
    //   50	66	145	finally
    //   12	18	151	java/lang/Error
    //   22	29	151	java/lang/Error
    //   32	37	151	java/lang/Error
    //   50	66	151	java/lang/Error
    //   200	204	207	java/io/IOException
    //   12	18	241	java/lang/Exception
    //   22	29	241	java/lang/Exception
    //   32	37	241	java/lang/Exception
    //   50	66	241	java/lang/Exception
    //   290	294	297	java/io/IOException
    //   12	18	327	java/io/IOException
    //   22	29	327	java/io/IOException
    //   32	37	327	java/io/IOException
    //   50	66	327	java/io/IOException
    //   376	380	383	java/io/IOException
    //   12	18	413	java/io/FileNotFoundException
    //   22	29	413	java/io/FileNotFoundException
    //   32	37	413	java/io/FileNotFoundException
    //   50	66	413	java/io/FileNotFoundException
    //   462	466	469	java/io/IOException
    //   68	74	504	finally
    //   157	166	504	finally
    //   168	176	504	finally
    //   178	186	504	finally
    //   188	196	504	finally
    //   247	256	504	finally
    //   258	266	504	finally
    //   268	276	504	finally
    //   278	286	504	finally
    //   333	342	504	finally
    //   344	352	504	finally
    //   354	362	504	finally
    //   364	372	504	finally
    //   419	428	504	finally
    //   430	438	504	finally
    //   440	448	504	finally
    //   450	458	504	finally
    //   78	82	522	finally
    //   86	114	522	finally
    //   200	204	522	finally
    //   208	234	522	finally
    //   234	238	522	finally
    //   290	294	522	finally
    //   298	324	522	finally
    //   376	380	522	finally
    //   384	410	522	finally
    //   462	466	522	finally
    //   470	496	522	finally
    //   515	519	522	finally
    //   527	559	522	finally
    //   559	561	522	finally
    //   515	519	526	java/io/IOException
  }
  
  public static Object jdMethod_do(BridgeModel paramBridgeModel, String paramString1, String paramString2)
  {
    if (paramBridgeModel == null) {
      return null;
    }
    try
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(" reflex -------------- ");
      ((StringBuilder)localObject1).append(paramBridgeModel.className);
      ((StringBuilder)localObject1).append(" :::: ");
      ((StringBuilder)localObject1).append(paramBridgeModel.actionName);
      ((StringBuilder)localObject1).append("   ");
      ((StringBuilder)localObject1).append(paramBridgeModel.putName);
      ((StringBuilder)localObject1).append(" ::::");
      ((StringBuilder)localObject1).append(paramBridgeModel.flagName);
      ((StringBuilder)localObject1).append(":::::");
      ((StringBuilder)localObject1).append(paramBridgeModel.flagParames);
      ((StringBuilder)localObject1).append(" ---- ");
      ((StringBuilder)localObject1).append(paramBridgeModel.setName);
      escape.jdMethod_int(((StringBuilder)localObject1).toString());
      localObject1 = Class.forName(paramBridgeModel.className);
      Object localObject2 = ((Class)localObject1).getConstructor(new Class[] { String.class }).newInstance(new Object[] { paramBridgeModel.actionName });
      ((Class)localObject1).getMethod(paramBridgeModel.putName, new Class[] { String.class, String.class }).invoke(localObject2, new Object[] { paramBridgeModel.putParames, paramString2 });
      ((Class)localObject1).getMethod(paramBridgeModel.flagName, new Class[] { Integer.TYPE }).invoke(localObject2, new Object[] { Integer.valueOf(paramBridgeModel.flagParames) });
      ((Class)localObject1).getMethod(paramBridgeModel.setName, new Class[] { String.class }).invoke(localObject2, new Object[] { paramString1 });
      return localObject2;
    }
    catch (Exception paramBridgeModel)
    {
      paramBridgeModel.printStackTrace();
    }
    return null;
  }
  
  public static String jdMethod_do()
  {
    return jdMethod_int("rtY-q7AjkVYjHBbzHBlErt/KotAEDzuulKwubK3RlebSbel=");
  }
  
  public static String jdMethod_do(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    String str;
    try
    {
      str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    try
    {
      resetting.jdMethod_do(paramContext, "android_id", str);
      return str;
    }
    catch (Exception paramContext) {}
    return null;
    return str;
  }
  
  public static String jdMethod_do(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return null;
    }
    return paramString.replace("pkg", paramContext.getPackageName());
  }
  
  public static String jdMethod_do(Context paramContext, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return null;
    }
    return jdMethod_int(resetting.jdMethod_do(paramContext, paramString1, paramString2));
  }
  
  public static String jdMethod_do(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.contains("\n")) {
        str = paramString.replace("\n", "");
      }
    }
    paramString = str;
    if (str != null)
    {
      paramString = str;
      if (str.contains("\r")) {
        paramString = str.replace("\r", "");
      }
    }
    return paramString;
  }
  
  public static String jdMethod_do(byte[] paramArrayOfByte)
  {
    int m;
    byte[] arrayOfByte1;
    byte[] arrayOfByte2;
    int i;
    int j;
    try
    {
      m = paramArrayOfByte.length;
      arrayOfByte1 = new byte[m * 3 / 4];
      arrayOfByte2 = new byte[''];
      byte[] tmp23_21 = arrayOfByte2;
      tmp23_21[0] = 0;
      byte[] tmp29_23 = tmp23_21;
      tmp29_23[1] = 0;
      byte[] tmp35_29 = tmp29_23;
      tmp35_29[2] = 0;
      byte[] tmp41_35 = tmp35_29;
      tmp41_35[3] = 0;
      byte[] tmp47_41 = tmp41_35;
      tmp47_41[4] = 0;
      byte[] tmp53_47 = tmp47_41;
      tmp53_47[5] = 0;
      byte[] tmp59_53 = tmp53_47;
      tmp59_53[6] = 0;
      byte[] tmp66_59 = tmp59_53;
      tmp66_59[7] = 0;
      byte[] tmp73_66 = tmp66_59;
      tmp73_66[8] = 0;
      byte[] tmp80_73 = tmp73_66;
      tmp80_73[9] = 0;
      byte[] tmp87_80 = tmp80_73;
      tmp87_80[10] = 0;
      byte[] tmp94_87 = tmp87_80;
      tmp94_87[11] = 0;
      byte[] tmp101_94 = tmp94_87;
      tmp101_94[12] = 0;
      byte[] tmp108_101 = tmp101_94;
      tmp108_101[13] = 0;
      byte[] tmp115_108 = tmp108_101;
      tmp115_108[14] = 0;
      byte[] tmp122_115 = tmp115_108;
      tmp122_115[15] = 0;
      byte[] tmp129_122 = tmp122_115;
      tmp129_122[16] = 0;
      byte[] tmp136_129 = tmp129_122;
      tmp136_129[17] = 0;
      byte[] tmp143_136 = tmp136_129;
      tmp143_136[18] = 0;
      byte[] tmp150_143 = tmp143_136;
      tmp150_143[19] = 0;
      byte[] tmp157_150 = tmp150_143;
      tmp157_150[20] = 0;
      byte[] tmp164_157 = tmp157_150;
      tmp164_157[21] = 0;
      byte[] tmp171_164 = tmp164_157;
      tmp171_164[22] = 0;
      byte[] tmp178_171 = tmp171_164;
      tmp178_171[23] = 0;
      byte[] tmp185_178 = tmp178_171;
      tmp185_178[24] = 0;
      byte[] tmp192_185 = tmp185_178;
      tmp192_185[25] = 0;
      byte[] tmp199_192 = tmp192_185;
      tmp199_192[26] = 0;
      byte[] tmp206_199 = tmp199_192;
      tmp206_199[27] = 0;
      byte[] tmp213_206 = tmp206_199;
      tmp213_206[28] = 0;
      byte[] tmp220_213 = tmp213_206;
      tmp220_213[29] = 0;
      byte[] tmp227_220 = tmp220_213;
      tmp227_220[30] = 0;
      byte[] tmp234_227 = tmp227_220;
      tmp234_227[31] = 0;
      byte[] tmp241_234 = tmp234_227;
      tmp241_234[32] = 0;
      byte[] tmp248_241 = tmp241_234;
      tmp248_241[33] = 0;
      byte[] tmp255_248 = tmp248_241;
      tmp255_248[34] = 0;
      byte[] tmp262_255 = tmp255_248;
      tmp262_255[35] = 0;
      byte[] tmp269_262 = tmp262_255;
      tmp269_262[36] = 0;
      byte[] tmp276_269 = tmp269_262;
      tmp276_269[37] = 0;
      byte[] tmp283_276 = tmp276_269;
      tmp283_276[38] = 0;
      byte[] tmp290_283 = tmp283_276;
      tmp290_283[39] = 0;
      byte[] tmp297_290 = tmp290_283;
      tmp297_290[40] = 0;
      byte[] tmp304_297 = tmp297_290;
      tmp304_297[41] = 0;
      byte[] tmp311_304 = tmp304_297;
      tmp311_304[42] = 0;
      byte[] tmp318_311 = tmp311_304;
      tmp318_311[43] = 0;
      byte[] tmp325_318 = tmp318_311;
      tmp325_318[44] = 0;
      byte[] tmp332_325 = tmp325_318;
      tmp332_325[45] = 36;
      byte[] tmp339_332 = tmp332_325;
      tmp339_332[46] = 0;
      byte[] tmp346_339 = tmp339_332;
      tmp346_339[47] = 13;
      byte[] tmp353_346 = tmp346_339;
      tmp353_346[48] = 42;
      byte[] tmp360_353 = tmp353_346;
      tmp360_353[49] = 5;
      byte[] tmp367_360 = tmp360_353;
      tmp367_360[50] = 58;
      byte[] tmp374_367 = tmp367_360;
      tmp374_367[51] = 21;
      byte[] tmp381_374 = tmp374_367;
      tmp381_374[52] = 62;
      byte[] tmp388_381 = tmp381_374;
      tmp388_381[53] = 63;
      byte[] tmp395_388 = tmp388_381;
      tmp395_388[54] = 15;
      byte[] tmp402_395 = tmp395_388;
      tmp402_395[55] = 38;
      byte[] tmp409_402 = tmp402_395;
      tmp409_402[56] = 32;
      byte[] tmp416_409 = tmp409_402;
      tmp416_409[57] = 7;
      byte[] tmp423_416 = tmp416_409;
      tmp423_416[58] = 0;
      byte[] tmp430_423 = tmp423_416;
      tmp430_423[59] = 0;
      byte[] tmp437_430 = tmp430_423;
      tmp437_430[60] = 0;
      byte[] tmp444_437 = tmp437_430;
      tmp444_437[61] = 0;
      byte[] tmp451_444 = tmp444_437;
      tmp451_444[62] = 0;
      byte[] tmp458_451 = tmp451_444;
      tmp458_451[63] = 0;
      byte[] tmp465_458 = tmp458_451;
      tmp465_458[64] = 0;
      byte[] tmp472_465 = tmp465_458;
      tmp472_465[65] = 61;
      byte[] tmp479_472 = tmp472_465;
      tmp479_472[66] = 39;
      byte[] tmp486_479 = tmp479_472;
      tmp486_479[67] = 35;
      byte[] tmp493_486 = tmp486_479;
      tmp493_486[68] = 11;
      byte[] tmp500_493 = tmp493_486;
      tmp500_493[69] = 46;
      byte[] tmp507_500 = tmp500_493;
      tmp507_500[70] = 9;
      byte[] tmp514_507 = tmp507_500;
      tmp514_507[71] = 56;
      byte[] tmp521_514 = tmp514_507;
      tmp521_514[72] = 27;
      byte[] tmp528_521 = tmp521_514;
      tmp528_521[73] = 48;
      byte[] tmp535_528 = tmp528_521;
      tmp535_528[74] = 33;
      byte[] tmp542_535 = tmp535_528;
      tmp542_535[75] = 52;
      byte[] tmp549_542 = tmp542_535;
      tmp549_542[76] = 34;
      byte[] tmp556_549 = tmp549_542;
      tmp556_549[77] = 54;
      byte[] tmp563_556 = tmp556_549;
      tmp563_556[78] = 19;
      byte[] tmp570_563 = tmp563_556;
      tmp570_563[79] = 44;
      byte[] tmp577_570 = tmp570_563;
      tmp577_570[80] = 47;
      byte[] tmp584_577 = tmp577_570;
      tmp584_577[81] = 40;
      byte[] tmp591_584 = tmp584_577;
      tmp591_584[82] = 31;
      byte[] tmp598_591 = tmp591_584;
      tmp598_591[83] = 4;
      byte[] tmp605_598 = tmp598_591;
      tmp605_598[84] = 8;
      byte[] tmp612_605 = tmp605_598;
      tmp612_605[85] = 6;
      byte[] tmp619_612 = tmp612_605;
      tmp619_612[86] = 2;
      byte[] tmp626_619 = tmp619_612;
      tmp626_619[87] = 29;
      byte[] tmp633_626 = tmp626_619;
      tmp633_626[88] = 0;
      byte[] tmp640_633 = tmp633_626;
      tmp640_633[89] = 57;
      byte[] tmp647_640 = tmp640_633;
      tmp647_640[90] = 59;
      byte[] tmp654_647 = tmp647_640;
      tmp654_647[91] = 0;
      byte[] tmp661_654 = tmp654_647;
      tmp661_654[92] = 0;
      byte[] tmp668_661 = tmp661_654;
      tmp668_661[93] = 0;
      byte[] tmp675_668 = tmp668_661;
      tmp675_668[94] = 0;
      byte[] tmp682_675 = tmp675_668;
      tmp682_675[95] = 0;
      byte[] tmp689_682 = tmp682_675;
      tmp689_682[96] = 0;
      byte[] tmp696_689 = tmp689_682;
      tmp696_689[97] = 23;
      byte[] tmp703_696 = tmp696_689;
      tmp703_696[98] = 17;
      byte[] tmp710_703 = tmp703_696;
      tmp710_703[99] = 12;
      byte[] tmp717_710 = tmp710_703;
      tmp717_710[100] = 49;
      byte[] tmp724_717 = tmp717_710;
      tmp724_717[101] = 20;
      byte[] tmp731_724 = tmp724_717;
      tmp731_724[102] = 55;
      byte[] tmp738_731 = tmp731_724;
      tmp738_731[103] = 50;
      byte[] tmp745_738 = tmp738_731;
      tmp745_738[104] = 43;
      byte[] tmp752_745 = tmp745_738;
      tmp752_745[105] = 51;
      byte[] tmp759_752 = tmp752_745;
      tmp759_752[106] = 41;
      byte[] tmp766_759 = tmp759_752;
      tmp766_759[107] = 25;
      byte[] tmp773_766 = tmp766_759;
      tmp773_766[108] = 16;
      byte[] tmp780_773 = tmp773_766;
      tmp780_773[109] = 53;
      byte[] tmp787_780 = tmp780_773;
      tmp787_780[110] = 18;
      byte[] tmp794_787 = tmp787_780;
      tmp794_787[111] = 26;
      byte[] tmp801_794 = tmp794_787;
      tmp801_794[112] = 30;
      byte[] tmp808_801 = tmp801_794;
      tmp808_801[113] = 28;
      byte[] tmp815_808 = tmp808_801;
      tmp815_808[114] = 24;
      byte[] tmp822_815 = tmp815_808;
      tmp822_815[115] = 3;
      byte[] tmp829_822 = tmp822_815;
      tmp829_822[116] = 22;
      byte[] tmp836_829 = tmp829_822;
      tmp836_829[117] = 1;
      byte[] tmp843_836 = tmp836_829;
      tmp843_836[118] = 60;
      byte[] tmp850_843 = tmp843_836;
      tmp850_843[119] = 45;
      byte[] tmp857_850 = tmp850_843;
      tmp857_850[120] = 10;
      byte[] tmp864_857 = tmp857_850;
      tmp864_857[121] = 14;
      byte[] tmp871_864 = tmp864_857;
      tmp871_864[122] = 37;
      byte[] tmp878_871 = tmp871_864;
      tmp878_871[123] = 0;
      byte[] tmp885_878 = tmp878_871;
      tmp885_878[124] = 0;
      byte[] tmp892_885 = tmp885_878;
      tmp892_885[125] = 0;
      byte[] tmp899_892 = tmp892_885;
      tmp899_892[126] = 0;
      byte[] tmp906_899 = tmp899_892;
      tmp906_899[127] = 0;
      tmp906_899;
      i = 0;
      j = 0;
    }
    catch (Exception paramArrayOfByte)
    {
      for (;;) {}
    }
    paramArrayOfByte = new String(arrayOfByte1, "US-ASCII").trim();
    return paramArrayOfByte;
    return "";
    for (;;)
    {
      int k = i + 4;
      if (k > m) {
        break;
      }
      int n = arrayOfByte2[(paramArrayOfByte[i] & 0xFF)];
      int i1 = arrayOfByte2[(paramArrayOfByte[(i + 1)] & 0xFF)];
      int i2 = arrayOfByte2[(paramArrayOfByte[(i + 2)] & 0xFF)];
      i = arrayOfByte2[(paramArrayOfByte[(i + 3)] & 0xFF)] | n << 18 | i1 << 12 | i2 << 6;
      if (i < 0) {
        break;
      }
      arrayOfByte1[(j + 2)] = ((byte)i);
      arrayOfByte1[(j + 1)] = ((byte)(i >> 8));
      arrayOfByte1[j] = ((byte)(i >> 16));
      j += 3;
      i = k;
    }
  }
  
  public static HashSet<String> jdMethod_do(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    HashSet localHashSet = new HashSet();
    paramContext = jdMethod_do(paramContext);
    int i = 0;
    for (;;)
    {
      int j;
      if (paramContext != null) {
        j = paramContext.size();
      } else {
        j = 0;
      }
      if (i >= j) {
        break;
      }
      localHashSet.add(((PackageInfo)paramContext.get(i)).packageName);
      i += 1;
    }
    return localHashSet;
  }
  
  public static List<PackageInfo> jdMethod_do(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    try
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        if ((paramContext != null) && (!paramContext.isEmpty()))
        {
          Iterator localIterator = paramContext.iterator();
          while (localIterator.hasNext()) {
            if ((((PackageInfo)localIterator.next()).applicationInfo.flags & 0x1) != 0) {
              localIterator.remove();
            }
          }
          return paramContext;
        }
        return null;
      }
      finally {}
      return null;
    }
    catch (OutOfMemoryError paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static Map<String, String> jdMethod_do(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if ((localObject instanceof String))
        {
          localObject = (String)localObject;
          String str = paramJSONObject.getString((String)localObject);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("key::::::");
          localStringBuilder.append((String)localObject);
          localStringBuilder.append("::::::::::");
          localStringBuilder.append(str);
          escape.jdMethod_int(localStringBuilder.toString());
          localHashMap.put(localObject, str);
        }
      }
      return localHashMap;
    }
    catch (Exception paramJSONObject) {}
    return localHashMap;
  }
  
  public static void jdMethod_do(Context paramContext, BridgeModel paramBridgeModel)
  {
    if (paramBridgeModel == null) {
      return;
    }
    jdMethod_do(paramContext, "c_n", paramBridgeModel.className);
    jdMethod_do(paramContext, "c_ac", paramBridgeModel.actionName);
    jdMethod_do(paramContext, "c_f", paramBridgeModel.flagName);
    resetting.jdMethod_do(paramContext, "c_fp", paramBridgeModel.flagParames);
    jdMethod_do(paramContext, "c_set", paramBridgeModel.setName);
    jdMethod_do(paramContext, "c_put", paramBridgeModel.putName);
    jdMethod_do(paramContext, "c_pp", paramBridgeModel.putParames);
    jdMethod_do(paramContext, "c_s", paramBridgeModel.send);
  }
  
  /* Error */
  public static void jdMethod_do(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual 107	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   8: astore_0
    //   9: aload_0
    //   10: ifnull +27 -> 37
    //   13: aload_0
    //   14: invokevirtual 116	java/io/File:exists	()Z
    //   17: ifeq +20 -> 37
    //   20: aload_0
    //   21: invokevirtual 501	java/io/File:delete	()Z
    //   24: pop
    //   25: goto +12 -> 37
    //   28: astore_0
    //   29: goto +12 -> 41
    //   32: astore_0
    //   33: aload_0
    //   34: invokevirtual 43	java/lang/Exception:printStackTrace	()V
    //   37: ldc 2
    //   39: monitorexit
    //   40: return
    //   41: ldc 2
    //   43: monitorexit
    //   44: aload_0
    //   45: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	46	0	paramContext	Context
    //   0	46	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   3	9	28	finally
    //   13	25	28	finally
    //   33	37	28	finally
    //   3	9	32	java/lang/Exception
    //   13	25	32	java/lang/Exception
  }
  
  public static void jdMethod_do(Context paramContext, String paramString, Class paramClass, Object paramObject)
  {
    try
    {
      Class localClass = Class.forName("android.content.ContextWrapper");
      paramContext = localClass.getConstructor(new Class[] { Context.class }).newInstance(new Object[] { paramContext });
      localClass.getMethod(paramString, new Class[] { paramClass }).invoke(paramContext, new Object[] { paramObject });
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  public static <T> void jdMethod_do(Context paramContext, String paramString, T paramT)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ifnonnull +7 -> 11
    //   7: ldc 2
    //   9: monitorexit
    //   10: return
    //   11: aconst_null
    //   12: astore 4
    //   14: aconst_null
    //   15: astore 5
    //   17: aconst_null
    //   18: astore_3
    //   19: new 507	java/io/ObjectOutputStream
    //   22: dup
    //   23: new 509	java/io/FileOutputStream
    //   26: dup
    //   27: aload_0
    //   28: aload_1
    //   29: invokevirtual 107	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   32: invokespecial 510	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   35: invokespecial 513	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   38: astore_0
    //   39: aload_0
    //   40: aload_2
    //   41: invokevirtual 516	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   44: aload_0
    //   45: ifnull +231 -> 276
    //   48: aload_0
    //   49: invokevirtual 517	java/io/ObjectOutputStream:close	()V
    //   52: goto +224 -> 276
    //   55: astore_0
    //   56: new 217	java/lang/StringBuilder
    //   59: dup
    //   60: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   63: astore_1
    //   64: aload_1
    //   65: ldc_w 519
    //   68: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: aload_1
    //   73: aload_0
    //   74: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: aload_1
    //   79: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: astore_0
    //   83: aload_0
    //   84: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   87: goto +189 -> 276
    //   90: astore_1
    //   91: aload_0
    //   92: astore_3
    //   93: aload_1
    //   94: astore_0
    //   95: goto +185 -> 280
    //   98: astore_1
    //   99: goto +15 -> 114
    //   102: astore_1
    //   103: goto +94 -> 197
    //   106: astore_0
    //   107: goto +173 -> 280
    //   110: astore_1
    //   111: aload 4
    //   113: astore_0
    //   114: aload_0
    //   115: astore_3
    //   116: new 217	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   123: astore_2
    //   124: aload_0
    //   125: astore_3
    //   126: aload_2
    //   127: ldc_w 521
    //   130: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: pop
    //   134: aload_0
    //   135: astore_3
    //   136: aload_2
    //   137: aload_1
    //   138: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   141: pop
    //   142: aload_0
    //   143: astore_3
    //   144: aload_2
    //   145: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   151: aload_0
    //   152: ifnull +124 -> 276
    //   155: aload_0
    //   156: invokevirtual 517	java/io/ObjectOutputStream:close	()V
    //   159: goto +117 -> 276
    //   162: astore_0
    //   163: new 217	java/lang/StringBuilder
    //   166: dup
    //   167: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   170: astore_1
    //   171: aload_1
    //   172: ldc_w 519
    //   175: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: aload_1
    //   180: aload_0
    //   181: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   184: pop
    //   185: aload_1
    //   186: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   189: astore_0
    //   190: goto -107 -> 83
    //   193: astore_1
    //   194: aload 5
    //   196: astore_0
    //   197: aload_0
    //   198: astore_3
    //   199: new 217	java/lang/StringBuilder
    //   202: dup
    //   203: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   206: astore_2
    //   207: aload_0
    //   208: astore_3
    //   209: aload_2
    //   210: ldc_w 521
    //   213: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: pop
    //   217: aload_0
    //   218: astore_3
    //   219: aload_2
    //   220: aload_1
    //   221: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   224: pop
    //   225: aload_0
    //   226: astore_3
    //   227: aload_2
    //   228: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   234: aload_0
    //   235: ifnull +41 -> 276
    //   238: aload_0
    //   239: invokevirtual 517	java/io/ObjectOutputStream:close	()V
    //   242: goto +34 -> 276
    //   245: astore_0
    //   246: new 217	java/lang/StringBuilder
    //   249: dup
    //   250: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   253: astore_1
    //   254: aload_1
    //   255: ldc_w 519
    //   258: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: pop
    //   262: aload_1
    //   263: aload_0
    //   264: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload_1
    //   269: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   272: astore_0
    //   273: goto -190 -> 83
    //   276: ldc 2
    //   278: monitorexit
    //   279: return
    //   280: aload_3
    //   281: ifnull +44 -> 325
    //   284: aload_3
    //   285: invokevirtual 517	java/io/ObjectOutputStream:close	()V
    //   288: goto +37 -> 325
    //   291: astore_0
    //   292: goto +35 -> 327
    //   295: astore_1
    //   296: new 217	java/lang/StringBuilder
    //   299: dup
    //   300: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   303: astore_2
    //   304: aload_2
    //   305: ldc_w 519
    //   308: invokevirtual 224	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: pop
    //   312: aload_2
    //   313: aload_1
    //   314: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   317: pop
    //   318: aload_2
    //   319: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   322: invokestatic 237	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   325: aload_0
    //   326: athrow
    //   327: ldc 2
    //   329: monitorexit
    //   330: aload_0
    //   331: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	332	0	paramContext	Context
    //   0	332	1	paramString	String
    //   0	332	2	paramT	T
    //   18	267	3	localContext	Context
    //   12	100	4	localObject1	Object
    //   15	180	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   48	52	55	java/io/IOException
    //   39	44	90	finally
    //   39	44	98	java/lang/Error
    //   39	44	102	java/lang/Exception
    //   19	39	106	finally
    //   116	124	106	finally
    //   126	134	106	finally
    //   136	142	106	finally
    //   144	151	106	finally
    //   199	207	106	finally
    //   209	217	106	finally
    //   219	225	106	finally
    //   227	234	106	finally
    //   19	39	110	java/lang/Error
    //   155	159	162	java/io/IOException
    //   19	39	193	java/lang/Exception
    //   238	242	245	java/io/IOException
    //   48	52	291	finally
    //   56	83	291	finally
    //   83	87	291	finally
    //   155	159	291	finally
    //   163	190	291	finally
    //   238	242	291	finally
    //   246	273	291	finally
    //   284	288	291	finally
    //   296	325	291	finally
    //   325	327	291	finally
    //   284	288	295	java/io/IOException
  }
  
  public static void jdMethod_do(Context paramContext, String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1))
    {
      if (TextUtils.isEmpty(paramString2)) {
        return;
      }
      resetting.jdMethod_do(paramContext, paramString1, jdMethod_for(paramString2));
      return;
    }
  }
  
  public static void jdMethod_do(String[] paramArrayOfString)
  {
    paramArrayOfString = jdMethod_int(jdMethod_for("adserver/v1/promote/ads/sdk/repair"));
    System.out.println(paramArrayOfString);
    PrintStream localPrintStream = System.out;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("final result:");
    localStringBuilder.append(paramArrayOfString);
    if (localStringBuilder.toString() != null) {
      paramArrayOfString = Boolean.valueOf(paramArrayOfString.equals("adserver/v1/promote/ads/sdk/repair"));
    } else {
      paramArrayOfString = "false";
    }
    localPrintStream.println(paramArrayOfString);
  }
  
  public static boolean jdMethod_do()
  {
    if (Looper.myLooper() == null) {
      return true;
    }
    return Looper.myLooper().equals(Looper.getMainLooper()) ^ true;
  }
  
  public static boolean jdMethod_do(Context paramContext)
  {
    if (paramContext != null) {}
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.getState() == NetworkInfo.State.CONNECTED)) {
        return true;
      }
      paramContext = new StringBuilder();
      paramContext.append("isNetworkAvailable: ");
      paramContext.append(null);
      Log.e("isNetworkAvailable", paramContext.toString());
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    paramContext.printStackTrace();
    return false;
  }
  
  public static boolean jdMethod_do(File paramFile)
  {
    if (paramFile != null) {}
    for (;;)
    {
      try
      {
        if (!paramFile.exists()) {
          return false;
        }
        if (paramFile.isDirectory())
        {
          String[] arrayOfString = paramFile.list();
          if (arrayOfString == null) {
            break;
          }
          if (arrayOfString.length >= 0) {
            break label73;
          }
          return false;
          if (i < arrayOfString.length)
          {
            if (jdMethod_do(new File(paramFile, arrayOfString[i]))) {
              break label78;
            }
            return false;
          }
        }
        boolean bool = paramFile.delete();
        return bool;
      }
      catch (Exception paramFile)
      {
        return false;
      }
      return false;
      label73:
      int i = 0;
      continue;
      label78:
      i += 1;
    }
    return false;
  }
  
  public static boolean jdMethod_do(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (!paramString.equals("null"));
  }
  
  public static String jdMethod_for()
  {
    return jdMethod_int("WClPrf3KafriDBuQqX==");
  }
  
  public static String jdMethod_for(Context paramContext)
  {
    return resetting.jdMethod_do(paramContext, "clk", "");
  }
  
  public static String jdMethod_for(String paramString)
  {
    for (;;)
    {
      int m;
      int j;
      int i;
      try
      {
        localObject1 = paramString.getBytes();
        m = localObject1.length;
        j = m / 3 * 4;
        i = j;
        if (m % 3 > 0) {
          i = j + 4;
        }
        localObject2 = new byte[i];
        localObject3 = new int[64];
        Object tmp44_42 = localObject3;
        tmp44_42[0] = 88;
        Object tmp49_44 = tmp44_42;
        tmp49_44[1] = 117;
        Object tmp54_49 = tmp49_44;
        tmp54_49[2] = 86;
        Object tmp59_54 = tmp54_49;
        tmp59_54[3] = 115;
        Object tmp64_59 = tmp59_54;
        tmp64_59[4] = 83;
        Object tmp69_64 = tmp64_59;
        tmp69_64[5] = 49;
        Object tmp74_69 = tmp69_64;
        tmp74_69[6] = 85;
        Object tmp80_74 = tmp74_69;
        tmp80_74[7] = 57;
        Object tmp86_80 = tmp80_74;
        tmp86_80[8] = 84;
        Object tmp92_86 = tmp86_80;
        tmp92_86[9] = 70;
        Object tmp98_92 = tmp92_86;
        tmp98_92[10] = 120;
        Object tmp104_98 = tmp98_92;
        tmp104_98[11] = 68;
        Object tmp110_104 = tmp104_98;
        tmp110_104[12] = 99;
        Object tmp116_110 = tmp110_104;
        tmp116_110[13] = 47;
        Object tmp122_116 = tmp116_110;
        tmp122_116[14] = 121;
        Object tmp128_122 = tmp122_116;
        tmp128_122[15] = 54;
        Object tmp134_128 = tmp128_122;
        tmp134_128[16] = 108;
        Object tmp140_134 = tmp134_128;
        tmp140_134[17] = 98;
        Object tmp146_140 = tmp140_134;
        tmp146_140[18] = 110;
        Object tmp152_146 = tmp146_140;
        tmp152_146[19] = 78;
        Object tmp158_152 = tmp152_146;
        tmp158_152[20] = 101;
        Object tmp164_158 = tmp158_152;
        tmp164_158[21] = 51;
        Object tmp170_164 = tmp164_158;
        tmp170_164[22] = 116;
        Object tmp176_170 = tmp170_164;
        tmp176_170[23] = 97;
        Object tmp182_176 = tmp176_170;
        tmp182_176[24] = 114;
        Object tmp188_182 = tmp182_176;
        tmp188_182[25] = 107;
        Object tmp194_188 = tmp188_182;
        tmp194_188[26] = 111;
        Object tmp200_194 = tmp194_188;
        tmp200_194[27] = 72;
        Object tmp206_200 = tmp200_194;
        tmp206_200[28] = 113;
        Object tmp212_206 = tmp206_200;
        tmp212_206[29] = 87;
        Object tmp218_212 = tmp212_206;
        tmp218_212[30] = 112;
        Object tmp224_218 = tmp218_212;
        tmp224_218[31] = 82;
        Object tmp230_224 = tmp224_218;
        tmp230_224[32] = 56;
        Object tmp236_230 = tmp230_224;
        tmp236_230[33] = 74;
        Object tmp242_236 = tmp236_230;
        tmp242_236[34] = 76;
        Object tmp248_242 = tmp242_236;
        tmp248_242[35] = 67;
        Object tmp254_248 = tmp248_242;
        tmp254_248[36] = 45;
        Object tmp260_254 = tmp254_248;
        tmp260_254[37] = 122;
        Object tmp266_260 = tmp260_254;
        tmp266_260[38] = 55;
        Object tmp272_266 = tmp266_260;
        tmp272_266[39] = 66;
        Object tmp278_272 = tmp272_266;
        tmp278_272[40] = 81;
        Object tmp284_278 = tmp278_272;
        tmp284_278[41] = 106;
        Object tmp290_284 = tmp284_278;
        tmp290_284[42] = 48;
        Object tmp296_290 = tmp290_284;
        tmp296_290[43] = 104;
        Object tmp302_296 = tmp296_290;
        tmp302_296[44] = 79;
        Object tmp308_302 = tmp302_296;
        tmp308_302[45] = 119;
        Object tmp314_308 = tmp308_302;
        tmp314_308[46] = 69;
        Object tmp320_314 = tmp314_308;
        tmp320_314[47] = 80;
        Object tmp326_320 = tmp320_314;
        tmp326_320[48] = 73;
        Object tmp332_326 = tmp326_320;
        tmp332_326[49] = 100;
        Object tmp338_332 = tmp332_326;
        tmp338_332[50] = 103;
        Object tmp344_338 = tmp338_332;
        tmp344_338[51] = 105;
        Object tmp350_344 = tmp344_338;
        tmp350_344[52] = 75;
        Object tmp356_350 = tmp350_344;
        tmp356_350[53] = 109;
        Object tmp362_356 = tmp356_350;
        tmp362_356[54] = 77;
        Object tmp368_362 = tmp362_356;
        tmp368_362[55] = 102;
        Object tmp374_368 = tmp368_362;
        tmp374_368[56] = 71;
        Object tmp380_374 = tmp374_368;
        tmp380_374[57] = 89;
        Object tmp386_380 = tmp380_374;
        tmp386_380[58] = 50;
        Object tmp392_386 = tmp386_380;
        tmp392_386[59] = 90;
        Object tmp398_392 = tmp392_386;
        tmp398_392[60] = 118;
        Object tmp404_398 = tmp398_392;
        tmp404_398[61] = 65;
        Object tmp410_404 = tmp404_398;
        tmp410_404[62] = 52;
        Object tmp416_410 = tmp410_404;
        tmp416_410[63] = 53;
        tmp416_410;
        j = 0;
        i = 0;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      Object localObject1 = new String((byte[])localObject2, "US-ASCII").trim();
      Object localObject2 = System.out;
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(paramString);
      ((StringBuilder)localObject3).append("\n");
      ((StringBuilder)localObject3).append((String)localObject1);
      ((PrintStream)localObject2).println(((StringBuilder)localObject3).toString());
      return localObject1;
      int k;
      for (;;)
      {
        k = j + 3;
        if (k > m) {
          break;
        }
        int n = localObject1[j];
        int i1 = localObject1[(j + 1)];
        j = localObject1[(j + 2)] & 0xFF | (n & 0xFF) << 16 | (i1 & 0xFF) << 8;
        localObject2[i] = ((byte)localObject3[(j >> 18 & 0x3F)]);
        localObject2[(i + 1)] = ((byte)localObject3[(j >> 12 & 0x3F)]);
        localObject2[(i + 2)] = ((byte)localObject3[(j >> 6 & 0x3F)]);
        localObject2[(i + 3)] = ((byte)localObject3[(j & 0x3F)]);
        i += 4;
        j = k;
      }
      if (m - 1 == j)
      {
        j = (localObject1[j] & 0xFF) << 4;
        k = i + 1;
        localObject2[i] = ((byte)localObject3[(j >> 6 & 0x3F)]);
        i = k + 1;
        localObject2[k] = ((byte)localObject3[(j & 0x3F)]);
        localObject2[i] = 61;
        localObject2[(i + 1)] = 61;
      }
      else if (m - 2 == j)
      {
        k = localObject1[j];
        j = (localObject1[(j + 1)] & 0xFF) << 2 | (k & 0xFF) << 10;
        k = i + 1;
        localObject2[i] = ((byte)localObject3[(j >> 12 & 0x3F)]);
        i = k + 1;
        localObject2[k] = ((byte)localObject3[(j >> 6 & 0x3F)]);
        localObject2[i] = ((byte)localObject3[(j & 0x3F)]);
        localObject2[(i + 1)] = 61;
      }
    }
  }
  
  public static int jdMethod_if(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density - 0.5F);
  }
  
  public static String jdMethod_if()
  {
    return jdMethod_int("rtY-q7AjkVYjHBbzHBlErt/KotAEDzuulKwubK3Re-3/Nmk1bX==");
  }
  
  public static String jdMethod_if(Context paramContext)
  {
    return resetting.jdMethod_do(paramContext, "ad", "");
  }
  
  public static String jdMethod_if(String paramString)
  {
    return jdMethod_do(ADSDK.getContext(), paramString);
  }
  
  public static boolean jdMethod_if(Context paramContext)
  {
    if (TextUtils.isEmpty(integer.super)) {
      integer.super = resetting.jdMethod_do(paramContext, "ad", "");
    }
    if (TextUtils.isEmpty(integer.throw)) {
      integer.throw = resetting.jdMethod_do(paramContext, "clk", "");
    }
    if (TextUtils.isEmpty(integer.while)) {
      integer.while = resetting.jdMethod_do(paramContext, "php", "");
    }
    return (!TextUtils.isEmpty(integer.super)) && (!TextUtils.isEmpty(integer.throw)) && (!TextUtils.isEmpty(integer.while));
  }
  
  public static String jdMethod_int()
  {
    return jdMethod_int("WClPHNTEqUJI");
  }
  
  public static String jdMethod_int(Context paramContext)
  {
    return resetting.jdMethod_do(paramContext, "php", "");
  }
  
  public static String jdMethod_int(String paramString)
  {
    return jdMethod_do(paramString.getBytes());
  }
  
  public static String jdMethod_new()
  {
    return "rMAEWU3EWsQPDMbPWMYOHM1-qI==";
  }
  
  public static String jdMethod_try()
  {
    return "rMAEWU3EWsQPDMbPWMYOHM1-qgAIWtFOot/RkUAfH7dPrtbiDI==";
  }
}
