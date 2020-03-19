package com.mobi.sdk;

import android.content.Context;
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
      if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 0))
      {
        int i = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType();
        switch (i)
        {
        case 0: 
        default: 
          return -1;
        case -1: 
          return -1;
        case 1: 
        case 2: 
        case 4: 
        case 7: 
        case 11: 
          return 2;
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
        return 4;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return -1;
    }
    return -1;
  }
  
  public static int jdMethod_do(Context paramContext, float paramFloat)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
  }
  
  public static int jdMethod_do(Context paramContext, String paramString)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return -1;
      PackageManager localPackageManager = paramContext.getPackageManager();
      try
      {
        paramContext = localPackageManager.getPackageInfo(paramString, 0);
        if (paramContext == null) {
          continue;
        }
        return localPackageManager.getApplicationEnabledSetting(paramString);
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        for (;;)
        {
          paramContext = null;
        }
      }
    }
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
      if ((!paramContext.isDirectory()) && (paramContext.exists())) {}
    }
    else
    {
      return -1L;
    }
    return paramContext.length();
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
    //   0: aconst_null
    //   1: astore_3
    //   2: ldc 2
    //   4: monitorenter
    //   5: aload_0
    //   6: ifnonnull +10 -> 16
    //   9: aload_3
    //   10: astore_0
    //   11: ldc 2
    //   13: monitorexit
    //   14: aload_0
    //   15: areturn
    //   16: aload_0
    //   17: aload_1
    //   18: invokevirtual 107	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   21: astore_1
    //   22: aload_3
    //   23: astore_0
    //   24: aload_1
    //   25: ifnull -14 -> 11
    //   28: aload_3
    //   29: astore_0
    //   30: aload_1
    //   31: invokevirtual 116	java/io/File:exists	()Z
    //   34: ifeq -23 -> 11
    //   37: aload_3
    //   38: astore_0
    //   39: aload_1
    //   40: invokevirtual 121	java/io/File:length	()J
    //   43: ldc2_w 197
    //   46: lcmp
    //   47: ifgt -36 -> 11
    //   50: new 200	java/io/ObjectInputStream
    //   53: dup
    //   54: new 202	java/io/FileInputStream
    //   57: dup
    //   58: aload_1
    //   59: invokespecial 205	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   62: invokespecial 208	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   65: astore_1
    //   66: aload_1
    //   67: astore_0
    //   68: aload_1
    //   69: invokevirtual 212	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   72: astore_2
    //   73: aload_2
    //   74: astore_0
    //   75: aload_1
    //   76: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   79: goto -68 -> 11
    //   82: astore_1
    //   83: new 217	java/lang/StringBuilder
    //   86: dup
    //   87: ldc -37
    //   89: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   92: aload_1
    //   93: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   102: goto -91 -> 11
    //   105: astore_0
    //   106: ldc 2
    //   108: monitorexit
    //   109: aload_0
    //   110: athrow
    //   111: astore_2
    //   112: aconst_null
    //   113: astore_1
    //   114: aload_1
    //   115: astore_0
    //   116: new 217	java/lang/StringBuilder
    //   119: dup
    //   120: ldc -18
    //   122: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   125: aload_2
    //   126: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   129: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   135: aload_3
    //   136: astore_0
    //   137: aload_1
    //   138: ifnull -127 -> 11
    //   141: aload_1
    //   142: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   145: aload_3
    //   146: astore_0
    //   147: goto -136 -> 11
    //   150: astore_0
    //   151: new 217	java/lang/StringBuilder
    //   154: dup
    //   155: ldc -37
    //   157: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   160: aload_0
    //   161: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   164: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   170: aload_3
    //   171: astore_0
    //   172: goto -161 -> 11
    //   175: astore_2
    //   176: aconst_null
    //   177: astore_1
    //   178: aload_1
    //   179: astore_0
    //   180: new 217	java/lang/StringBuilder
    //   183: dup
    //   184: ldc -18
    //   186: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   189: aload_2
    //   190: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   199: aload_3
    //   200: astore_0
    //   201: aload_1
    //   202: ifnull -191 -> 11
    //   205: aload_1
    //   206: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   209: aload_3
    //   210: astore_0
    //   211: goto -200 -> 11
    //   214: astore_0
    //   215: new 217	java/lang/StringBuilder
    //   218: dup
    //   219: ldc -37
    //   221: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   224: aload_0
    //   225: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   228: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   234: aload_3
    //   235: astore_0
    //   236: goto -225 -> 11
    //   239: astore_2
    //   240: aconst_null
    //   241: astore_1
    //   242: aload_1
    //   243: astore_0
    //   244: new 217	java/lang/StringBuilder
    //   247: dup
    //   248: ldc -18
    //   250: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   253: aload_2
    //   254: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   257: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   260: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   263: aload_3
    //   264: astore_0
    //   265: aload_1
    //   266: ifnull -255 -> 11
    //   269: aload_1
    //   270: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   273: aload_3
    //   274: astore_0
    //   275: goto -264 -> 11
    //   278: astore_0
    //   279: new 217	java/lang/StringBuilder
    //   282: dup
    //   283: ldc -37
    //   285: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   288: aload_0
    //   289: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   292: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   295: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   298: aload_3
    //   299: astore_0
    //   300: goto -289 -> 11
    //   303: astore_2
    //   304: aconst_null
    //   305: astore_1
    //   306: aload_1
    //   307: astore_0
    //   308: new 217	java/lang/StringBuilder
    //   311: dup
    //   312: ldc -18
    //   314: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   317: aload_2
    //   318: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   321: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   324: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   327: aload_3
    //   328: astore_0
    //   329: aload_1
    //   330: ifnull -319 -> 11
    //   333: aload_1
    //   334: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   337: aload_3
    //   338: astore_0
    //   339: goto -328 -> 11
    //   342: astore_0
    //   343: new 217	java/lang/StringBuilder
    //   346: dup
    //   347: ldc -37
    //   349: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   352: aload_0
    //   353: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   356: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   362: aload_3
    //   363: astore_0
    //   364: goto -353 -> 11
    //   367: astore_1
    //   368: aconst_null
    //   369: astore_0
    //   370: aload_0
    //   371: ifnull +7 -> 378
    //   374: aload_0
    //   375: invokevirtual 215	java/io/ObjectInputStream:close	()V
    //   378: aload_1
    //   379: athrow
    //   380: astore_0
    //   381: new 217	java/lang/StringBuilder
    //   384: dup
    //   385: ldc -37
    //   387: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   390: aload_0
    //   391: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   394: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   397: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   400: goto -22 -> 378
    //   403: astore_1
    //   404: goto -34 -> 370
    //   407: astore_2
    //   408: goto -102 -> 306
    //   411: astore_2
    //   412: goto -170 -> 242
    //   415: astore_2
    //   416: goto -238 -> 178
    //   419: astore_2
    //   420: goto -306 -> 114
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	423	0	paramContext	Context
    //   0	423	1	paramString	String
    //   72	2	2	localObject1	Object
    //   111	15	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   175	15	2	localIOException1	java.io.IOException
    //   239	15	2	localException1	Exception
    //   303	15	2	localError1	Error
    //   407	1	2	localError2	Error
    //   411	1	2	localException2	Exception
    //   415	1	2	localIOException2	java.io.IOException
    //   419	1	2	localFileNotFoundException2	java.io.FileNotFoundException
    //   1	362	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   75	79	82	java/io/IOException
    //   75	79	105	finally
    //   83	102	105	finally
    //   141	145	105	finally
    //   151	170	105	finally
    //   205	209	105	finally
    //   215	234	105	finally
    //   269	273	105	finally
    //   279	298	105	finally
    //   333	337	105	finally
    //   343	362	105	finally
    //   374	378	105	finally
    //   378	380	105	finally
    //   381	400	105	finally
    //   16	22	111	java/io/FileNotFoundException
    //   30	37	111	java/io/FileNotFoundException
    //   39	66	111	java/io/FileNotFoundException
    //   141	145	150	java/io/IOException
    //   16	22	175	java/io/IOException
    //   30	37	175	java/io/IOException
    //   39	66	175	java/io/IOException
    //   205	209	214	java/io/IOException
    //   16	22	239	java/lang/Exception
    //   30	37	239	java/lang/Exception
    //   39	66	239	java/lang/Exception
    //   269	273	278	java/io/IOException
    //   16	22	303	java/lang/Error
    //   30	37	303	java/lang/Error
    //   39	66	303	java/lang/Error
    //   333	337	342	java/io/IOException
    //   16	22	367	finally
    //   30	37	367	finally
    //   39	66	367	finally
    //   374	378	380	java/io/IOException
    //   68	73	403	finally
    //   116	135	403	finally
    //   180	199	403	finally
    //   244	263	403	finally
    //   308	327	403	finally
    //   68	73	407	java/lang/Error
    //   68	73	411	java/lang/Exception
    //   68	73	415	java/io/IOException
    //   68	73	419	java/io/FileNotFoundException
  }
  
  public static Object jdMethod_do(BridgeModel paramBridgeModel, String paramString1, String paramString2)
  {
    if (paramBridgeModel == null) {
      return null;
    }
    try
    {
      escape.jdMethod_int(" reflex -------------- " + paramBridgeModel.className + " :::: " + paramBridgeModel.actionName + "   " + paramBridgeModel.putName + " ::::" + paramBridgeModel.flagName + ":::::" + paramBridgeModel.flagParames + " ---- " + paramBridgeModel.setName);
      Class localClass = Class.forName(paramBridgeModel.className);
      Object localObject = localClass.getConstructor(new Class[] { String.class }).newInstance(new Object[] { paramBridgeModel.actionName });
      localClass.getMethod(paramBridgeModel.putName, new Class[] { String.class, String.class }).invoke(localObject, new Object[] { paramBridgeModel.putParames, paramString2 });
      localClass.getMethod(paramBridgeModel.flagName, new Class[] { Integer.TYPE }).invoke(localObject, new Object[] { Integer.valueOf(paramBridgeModel.flagParames) });
      localClass.getMethod(paramBridgeModel.setName, new Class[] { String.class }).invoke(localObject, new Object[] { paramString1 });
      return localObject;
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
    Object localObject = null;
    if (paramContext == null) {
      return null;
    }
    try
    {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      localObject = str;
      resetting.jdMethod_do(paramContext, "android_id", str);
      return str;
    }
    catch (Exception paramContext) {}
    return localObject;
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
    if ((paramString != null) && (paramString.contains("\n"))) {
      paramString = paramString.replace("\n", "");
    }
    for (;;)
    {
      String str = paramString;
      if (paramString != null)
      {
        str = paramString;
        if (paramString.contains("\r")) {
          str = paramString.replace("\r", "");
        }
      }
      return str;
    }
  }
  
  public static String jdMethod_do(byte[] paramArrayOfByte)
  {
    int i = 0;
    label1054:
    for (;;)
    {
      int k;
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      int j;
      try
      {
        k = paramArrayOfByte.length;
        arrayOfByte1 = new byte[k * 3 / 4];
        arrayOfByte2 = new byte[''];
        tmp23_21 = arrayOfByte2;
        tmp23_21[0] = 0;
        tmp29_23 = tmp23_21;
        tmp29_23[1] = 0;
        tmp35_29 = tmp29_23;
        tmp35_29[2] = 0;
        tmp41_35 = tmp35_29;
        tmp41_35[3] = 0;
        tmp47_41 = tmp41_35;
        tmp47_41[4] = 0;
        tmp53_47 = tmp47_41;
        tmp53_47[5] = 0;
        tmp59_53 = tmp53_47;
        tmp59_53[6] = 0;
        tmp66_59 = tmp59_53;
        tmp66_59[7] = 0;
        tmp73_66 = tmp66_59;
        tmp73_66[8] = 0;
        tmp80_73 = tmp73_66;
        tmp80_73[9] = 0;
        tmp87_80 = tmp80_73;
        tmp87_80[10] = 0;
        tmp94_87 = tmp87_80;
        tmp94_87[11] = 0;
        tmp101_94 = tmp94_87;
        tmp101_94[12] = 0;
        tmp108_101 = tmp101_94;
        tmp108_101[13] = 0;
        tmp115_108 = tmp108_101;
        tmp115_108[14] = 0;
        tmp122_115 = tmp115_108;
        tmp122_115[15] = 0;
        tmp129_122 = tmp122_115;
        tmp129_122[16] = 0;
        tmp136_129 = tmp129_122;
        tmp136_129[17] = 0;
        tmp143_136 = tmp136_129;
        tmp143_136[18] = 0;
        tmp150_143 = tmp143_136;
        tmp150_143[19] = 0;
        tmp157_150 = tmp150_143;
        tmp157_150[20] = 0;
        tmp164_157 = tmp157_150;
        tmp164_157[21] = 0;
        tmp171_164 = tmp164_157;
        tmp171_164[22] = 0;
        tmp178_171 = tmp171_164;
        tmp178_171[23] = 0;
        tmp185_178 = tmp178_171;
        tmp185_178[24] = 0;
        tmp192_185 = tmp185_178;
        tmp192_185[25] = 0;
        tmp199_192 = tmp192_185;
        tmp199_192[26] = 0;
        tmp206_199 = tmp199_192;
        tmp206_199[27] = 0;
        tmp213_206 = tmp206_199;
        tmp213_206[28] = 0;
        tmp220_213 = tmp213_206;
        tmp220_213[29] = 0;
        tmp227_220 = tmp220_213;
        tmp227_220[30] = 0;
        tmp234_227 = tmp227_220;
        tmp234_227[31] = 0;
        tmp241_234 = tmp234_227;
        tmp241_234[32] = 0;
        tmp248_241 = tmp241_234;
        tmp248_241[33] = 0;
        tmp255_248 = tmp248_241;
        tmp255_248[34] = 0;
        tmp262_255 = tmp255_248;
        tmp262_255[35] = 0;
        tmp269_262 = tmp262_255;
        tmp269_262[36] = 0;
        tmp276_269 = tmp269_262;
        tmp276_269[37] = 0;
        tmp283_276 = tmp276_269;
        tmp283_276[38] = 0;
        tmp290_283 = tmp283_276;
        tmp290_283[39] = 0;
        tmp297_290 = tmp290_283;
        tmp297_290[40] = 0;
        tmp304_297 = tmp297_290;
        tmp304_297[41] = 0;
        tmp311_304 = tmp304_297;
        tmp311_304[42] = 0;
        tmp318_311 = tmp311_304;
        tmp318_311[43] = 0;
        tmp325_318 = tmp318_311;
        tmp325_318[44] = 0;
        tmp332_325 = tmp325_318;
        tmp332_325[45] = 36;
        tmp339_332 = tmp332_325;
        tmp339_332[46] = 0;
        tmp346_339 = tmp339_332;
        tmp346_339[47] = 13;
        tmp353_346 = tmp346_339;
        tmp353_346[48] = 42;
        tmp360_353 = tmp353_346;
        tmp360_353[49] = 5;
        tmp367_360 = tmp360_353;
        tmp367_360[50] = 58;
        tmp374_367 = tmp367_360;
        tmp374_367[51] = 21;
        tmp381_374 = tmp374_367;
        tmp381_374[52] = 62;
        tmp388_381 = tmp381_374;
        tmp388_381[53] = 63;
        tmp395_388 = tmp388_381;
        tmp395_388[54] = 15;
        tmp402_395 = tmp395_388;
        tmp402_395[55] = 38;
        tmp409_402 = tmp402_395;
        tmp409_402[56] = 32;
        tmp416_409 = tmp409_402;
        tmp416_409[57] = 7;
        tmp423_416 = tmp416_409;
        tmp423_416[58] = 0;
        tmp430_423 = tmp423_416;
        tmp430_423[59] = 0;
        tmp437_430 = tmp430_423;
        tmp437_430[60] = 0;
        tmp444_437 = tmp437_430;
        tmp444_437[61] = 0;
        tmp451_444 = tmp444_437;
        tmp451_444[62] = 0;
        tmp458_451 = tmp451_444;
        tmp458_451[63] = 0;
        tmp465_458 = tmp458_451;
        tmp465_458[64] = 0;
        tmp472_465 = tmp465_458;
        tmp472_465[65] = 61;
        tmp479_472 = tmp472_465;
        tmp479_472[66] = 39;
        tmp486_479 = tmp479_472;
        tmp486_479[67] = 35;
        tmp493_486 = tmp486_479;
        tmp493_486[68] = 11;
        tmp500_493 = tmp493_486;
        tmp500_493[69] = 46;
        tmp507_500 = tmp500_493;
        tmp507_500[70] = 9;
        tmp514_507 = tmp507_500;
        tmp514_507[71] = 56;
        tmp521_514 = tmp514_507;
        tmp521_514[72] = 27;
        tmp528_521 = tmp521_514;
        tmp528_521[73] = 48;
        tmp535_528 = tmp528_521;
        tmp535_528[74] = 33;
        tmp542_535 = tmp535_528;
        tmp542_535[75] = 52;
        tmp549_542 = tmp542_535;
        tmp549_542[76] = 34;
        tmp556_549 = tmp549_542;
        tmp556_549[77] = 54;
        tmp563_556 = tmp556_549;
        tmp563_556[78] = 19;
        tmp570_563 = tmp563_556;
        tmp570_563[79] = 44;
        tmp577_570 = tmp570_563;
        tmp577_570[80] = 47;
        tmp584_577 = tmp577_570;
        tmp584_577[81] = 40;
        tmp591_584 = tmp584_577;
        tmp591_584[82] = 31;
        tmp598_591 = tmp591_584;
        tmp598_591[83] = 4;
        tmp605_598 = tmp598_591;
        tmp605_598[84] = 8;
        tmp612_605 = tmp605_598;
        tmp612_605[85] = 6;
        tmp619_612 = tmp612_605;
        tmp619_612[86] = 2;
        tmp626_619 = tmp619_612;
        tmp626_619[87] = 29;
        tmp633_626 = tmp626_619;
        tmp633_626[88] = 0;
        tmp640_633 = tmp633_626;
        tmp640_633[89] = 57;
        tmp647_640 = tmp640_633;
        tmp647_640[90] = 59;
        tmp654_647 = tmp647_640;
        tmp654_647[91] = 0;
        tmp661_654 = tmp654_647;
        tmp661_654[92] = 0;
        tmp668_661 = tmp661_654;
        tmp668_661[93] = 0;
        tmp675_668 = tmp668_661;
        tmp675_668[94] = 0;
        tmp682_675 = tmp675_668;
        tmp682_675[95] = 0;
        tmp689_682 = tmp682_675;
        tmp689_682[96] = 0;
        tmp696_689 = tmp689_682;
        tmp696_689[97] = 23;
        tmp703_696 = tmp696_689;
        tmp703_696[98] = 17;
        tmp710_703 = tmp703_696;
        tmp710_703[99] = 12;
        tmp717_710 = tmp710_703;
        tmp717_710[100] = 49;
        tmp724_717 = tmp717_710;
        tmp724_717[101] = 20;
        tmp731_724 = tmp724_717;
        tmp731_724[102] = 55;
        tmp738_731 = tmp731_724;
        tmp738_731[103] = 50;
        tmp745_738 = tmp738_731;
        tmp745_738[104] = 43;
        tmp752_745 = tmp745_738;
        tmp752_745[105] = 51;
        tmp759_752 = tmp752_745;
        tmp759_752[106] = 41;
        tmp766_759 = tmp759_752;
        tmp766_759[107] = 25;
        tmp773_766 = tmp766_759;
        tmp773_766[108] = 16;
        tmp780_773 = tmp773_766;
        tmp780_773[109] = 53;
        tmp787_780 = tmp780_773;
        tmp787_780[110] = 18;
        tmp794_787 = tmp787_780;
        tmp794_787[111] = 26;
        tmp801_794 = tmp794_787;
        tmp801_794[112] = 30;
        tmp808_801 = tmp801_794;
        tmp808_801[113] = 28;
        tmp815_808 = tmp808_801;
        tmp815_808[114] = 24;
        tmp822_815 = tmp815_808;
        tmp822_815[115] = 3;
        tmp829_822 = tmp822_815;
        tmp829_822[116] = 22;
        tmp836_829 = tmp829_822;
        tmp836_829[117] = 1;
        tmp843_836 = tmp836_829;
        tmp843_836[118] = 60;
        tmp850_843 = tmp843_836;
        tmp850_843[119] = 45;
        tmp857_850 = tmp850_843;
        tmp857_850[120] = 10;
        tmp864_857 = tmp857_850;
        tmp864_857[121] = 14;
        tmp871_864 = tmp864_857;
        tmp871_864[122] = 37;
        tmp878_871 = tmp871_864;
        tmp878_871[123] = 0;
        tmp885_878 = tmp878_871;
        tmp885_878[124] = 0;
        tmp892_885 = tmp885_878;
        tmp892_885[125] = 0;
        tmp899_892 = tmp892_885;
        tmp899_892[126] = 0;
        tmp906_899 = tmp899_892;
        tmp906_899[127] = 0;
        tmp906_899;
        j = 0;
      }
      catch (Exception paramArrayOfByte)
      {
        return "";
      }
      paramArrayOfByte = new String(arrayOfByte1, "US-ASCII").trim();
      return paramArrayOfByte;
      for (;;)
      {
        if (j + 4 > k) {
          break label1054;
        }
        int m = arrayOfByte2[(paramArrayOfByte[j] & 0xFF)] << 18 | arrayOfByte2[(paramArrayOfByte[(j + 1)] & 0xFF)] << 12 | arrayOfByte2[(paramArrayOfByte[(j + 2)] & 0xFF)] << 6 | arrayOfByte2[(paramArrayOfByte[(j + 3)] & 0xFF)];
        if (m < 0) {
          break;
        }
        arrayOfByte1[(i + 2)] = ((byte)m);
        arrayOfByte1[(i + 1)] = ((byte)(m >> 8));
        arrayOfByte1[i] = ((byte)(m >> 16));
        i += 3;
        j += 4;
      }
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
    if (paramContext != null) {}
    for (int j = paramContext.size();; j = 0)
    {
      if (i >= j) {
        return localHashSet;
      }
      localHashSet.add(((PackageInfo)paramContext.get(i)).packageName);
      i += 1;
      break;
    }
    return localHashSet;
  }
  
  /* Error */
  public static List<PackageInfo> jdMethod_do(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: ldc 2
    //   8: monitorenter
    //   9: aload_0
    //   10: invokevirtual 68	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: iconst_0
    //   14: invokevirtual 444	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   17: astore_0
    //   18: aload_0
    //   19: ifnull +75 -> 94
    //   22: aload_0
    //   23: invokeinterface 446 1 0
    //   28: ifne +66 -> 94
    //   31: aload_0
    //   32: invokeinterface 450 1 0
    //   37: astore_1
    //   38: aload_1
    //   39: invokeinterface 455 1 0
    //   44: ifeq +45 -> 89
    //   47: aload_1
    //   48: invokeinterface 458 1 0
    //   53: checkcast 431	android/content/pm/PackageInfo
    //   56: getfield 462	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   59: getfield 467	android/content/pm/ApplicationInfo:flags	I
    //   62: iconst_1
    //   63: iand
    //   64: ifeq -26 -> 38
    //   67: aload_1
    //   68: invokeinterface 470 1 0
    //   73: goto -35 -> 38
    //   76: astore_0
    //   77: ldc 2
    //   79: monitorexit
    //   80: aload_0
    //   81: athrow
    //   82: astore_0
    //   83: aload_0
    //   84: invokevirtual 43	java/lang/Exception:printStackTrace	()V
    //   87: aconst_null
    //   88: areturn
    //   89: ldc 2
    //   91: monitorexit
    //   92: aload_0
    //   93: areturn
    //   94: ldc 2
    //   96: monitorexit
    //   97: goto -10 -> 87
    //   100: astore_0
    //   101: aload_0
    //   102: invokevirtual 471	java/lang/OutOfMemoryError:printStackTrace	()V
    //   105: goto -18 -> 87
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	paramContext	Context
    //   37	31	1	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   9	18	76	finally
    //   22	38	76	finally
    //   38	73	76	finally
    //   77	80	76	finally
    //   89	92	76	finally
    //   94	97	76	finally
    //   6	9	82	java/lang/Exception
    //   80	82	82	java/lang/Exception
    //   6	9	100	java/lang/OutOfMemoryError
    //   80	82	100	java/lang/OutOfMemoryError
  }
  
  public static Map<String, String> jdMethod_do(JSONObject paramJSONObject)
  {
    localHashMap = new HashMap();
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
          escape.jdMethod_int("key::::::" + (String)localObject + "::::::::::" + str);
          localHashMap.put(localObject, str);
        }
      }
      return localHashMap;
    }
    catch (Exception paramJSONObject) {}
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
  
  public static void jdMethod_do(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getFileStreamPath(paramString);
      if ((paramContext != null) && (paramContext.exists())) {
        paramContext.delete();
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    finally {}
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
    //   12: astore_3
    //   13: aconst_null
    //   14: astore 4
    //   16: new 509	java/io/ObjectOutputStream
    //   19: dup
    //   20: new 511	java/io/FileOutputStream
    //   23: dup
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual 107	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   29: invokespecial 512	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   32: invokespecial 515	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   35: astore_0
    //   36: aload_0
    //   37: astore_1
    //   38: aload_0
    //   39: aload_2
    //   40: invokevirtual 518	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   43: aload_0
    //   44: invokevirtual 519	java/io/ObjectOutputStream:close	()V
    //   47: goto -40 -> 7
    //   50: astore_0
    //   51: new 217	java/lang/StringBuilder
    //   54: dup
    //   55: ldc_w 521
    //   58: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   61: aload_0
    //   62: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   71: goto -64 -> 7
    //   74: astore_0
    //   75: ldc 2
    //   77: monitorexit
    //   78: aload_0
    //   79: athrow
    //   80: astore_2
    //   81: aconst_null
    //   82: astore_0
    //   83: aload_0
    //   84: astore_1
    //   85: new 217	java/lang/StringBuilder
    //   88: dup
    //   89: ldc_w 523
    //   92: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   95: aload_2
    //   96: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   99: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   105: aload_0
    //   106: ifnull -99 -> 7
    //   109: aload_0
    //   110: invokevirtual 519	java/io/ObjectOutputStream:close	()V
    //   113: goto -106 -> 7
    //   116: astore_0
    //   117: new 217	java/lang/StringBuilder
    //   120: dup
    //   121: ldc_w 521
    //   124: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   127: aload_0
    //   128: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   137: goto -130 -> 7
    //   140: astore_1
    //   141: aload 4
    //   143: astore_0
    //   144: aload_0
    //   145: astore_3
    //   146: new 217	java/lang/StringBuilder
    //   149: dup
    //   150: ldc_w 523
    //   153: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   156: aload_1
    //   157: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   160: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   166: aload_0
    //   167: ifnull -160 -> 7
    //   170: aload_0
    //   171: invokevirtual 519	java/io/ObjectOutputStream:close	()V
    //   174: goto -167 -> 7
    //   177: astore_0
    //   178: new 217	java/lang/StringBuilder
    //   181: dup
    //   182: ldc_w 521
    //   185: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   188: aload_0
    //   189: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   192: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   195: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   198: goto -191 -> 7
    //   201: astore_0
    //   202: aload_3
    //   203: ifnull +7 -> 210
    //   206: aload_3
    //   207: invokevirtual 519	java/io/ObjectOutputStream:close	()V
    //   210: aload_0
    //   211: athrow
    //   212: astore_1
    //   213: new 217	java/lang/StringBuilder
    //   216: dup
    //   217: ldc_w 521
    //   220: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   223: aload_1
    //   224: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   227: invokevirtual 230	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   230: invokestatic 236	com/mobi/sdk/escape:for	(Ljava/lang/Object;)V
    //   233: goto -23 -> 210
    //   236: astore_0
    //   237: aload_1
    //   238: astore_3
    //   239: goto -37 -> 202
    //   242: astore_1
    //   243: goto -99 -> 144
    //   246: astore_2
    //   247: goto -164 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	250	0	paramContext	Context
    //   0	250	1	paramString	String
    //   0	250	2	paramT	T
    //   12	227	3	localObject1	Object
    //   14	128	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   43	47	50	java/io/IOException
    //   43	47	74	finally
    //   51	71	74	finally
    //   109	113	74	finally
    //   117	137	74	finally
    //   170	174	74	finally
    //   178	198	74	finally
    //   206	210	74	finally
    //   210	212	74	finally
    //   213	233	74	finally
    //   16	36	80	java/lang/Exception
    //   109	113	116	java/io/IOException
    //   16	36	140	java/lang/Error
    //   170	174	177	java/io/IOException
    //   16	36	201	finally
    //   146	166	201	finally
    //   206	210	212	java/io/IOException
    //   38	43	236	finally
    //   85	105	236	finally
    //   38	43	242	java/lang/Error
    //   38	43	246	java/lang/Exception
  }
  
  public static void jdMethod_do(Context paramContext, String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return;
    }
    resetting.jdMethod_do(paramContext, paramString1, jdMethod_for(paramString2));
  }
  
  public static void jdMethod_do(String[] paramArrayOfString)
  {
    paramArrayOfString = jdMethod_int(jdMethod_for("adserver/v1/promote/ads/sdk/repair"));
    System.out.println(paramArrayOfString);
    PrintStream localPrintStream = System.out;
    if ("final result:" + paramArrayOfString != null) {}
    for (paramArrayOfString = Boolean.valueOf(paramArrayOfString.equals("adserver/v1/promote/ads/sdk/repair"));; paramArrayOfString = "false")
    {
      localPrintStream.println(paramArrayOfString);
      return;
    }
  }
  
  public static boolean jdMethod_do()
  {
    if (Looper.myLooper() == null) {}
    while (!Looper.myLooper().equals(Looper.getMainLooper())) {
      return true;
    }
    return false;
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
      Log.e("isNetworkAvailable", "isNetworkAvailable: " + null);
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean jdMethod_do(File paramFile)
  {
    if (paramFile != null) {
      try
      {
        if (!paramFile.exists()) {
          return false;
        }
        if (paramFile.isDirectory())
        {
          String[] arrayOfString = paramFile.list();
          if ((arrayOfString != null) && (arrayOfString.length >= 0))
          {
            int i = 0;
            while (i < arrayOfString.length)
            {
              if (!jdMethod_do(new File(paramFile, arrayOfString[i]))) {
                break label74;
              }
              i += 1;
            }
          }
        }
        else
        {
          boolean bool = paramFile.delete();
          return bool;
        }
      }
      catch (Exception paramFile) {}
    }
    label74:
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
    int j = 0;
    Object localObject;
    int m;
    int k;
    int i;
    byte[] arrayOfByte;
    int[] arrayOfInt;
    for (;;)
    {
      try
      {
        localObject = paramString.getBytes();
        m = localObject.length;
        k = m / 3 * 4;
        i = k;
        if (m % 3 > 0) {
          i = k + 4;
        }
        arrayOfByte = new byte[i];
        arrayOfInt = new int[64];
        int[] tmp46_44 = arrayOfInt;
        tmp46_44[0] = 88;
        int[] tmp51_46 = tmp46_44;
        tmp51_46[1] = 117;
        int[] tmp56_51 = tmp51_46;
        tmp56_51[2] = 86;
        int[] tmp61_56 = tmp56_51;
        tmp61_56[3] = 115;
        int[] tmp66_61 = tmp61_56;
        tmp66_61[4] = 83;
        int[] tmp71_66 = tmp66_61;
        tmp71_66[5] = 49;
        int[] tmp76_71 = tmp71_66;
        tmp76_71[6] = 85;
        int[] tmp82_76 = tmp76_71;
        tmp82_76[7] = 57;
        int[] tmp88_82 = tmp82_76;
        tmp88_82[8] = 84;
        int[] tmp94_88 = tmp88_82;
        tmp94_88[9] = 70;
        int[] tmp100_94 = tmp94_88;
        tmp100_94[10] = 120;
        int[] tmp106_100 = tmp100_94;
        tmp106_100[11] = 68;
        int[] tmp112_106 = tmp106_100;
        tmp112_106[12] = 99;
        int[] tmp118_112 = tmp112_106;
        tmp118_112[13] = 47;
        int[] tmp124_118 = tmp118_112;
        tmp124_118[14] = 121;
        int[] tmp130_124 = tmp124_118;
        tmp130_124[15] = 54;
        int[] tmp136_130 = tmp130_124;
        tmp136_130[16] = 108;
        int[] tmp142_136 = tmp136_130;
        tmp142_136[17] = 98;
        int[] tmp148_142 = tmp142_136;
        tmp148_142[18] = 110;
        int[] tmp154_148 = tmp148_142;
        tmp154_148[19] = 78;
        int[] tmp160_154 = tmp154_148;
        tmp160_154[20] = 101;
        int[] tmp166_160 = tmp160_154;
        tmp166_160[21] = 51;
        int[] tmp172_166 = tmp166_160;
        tmp172_166[22] = 116;
        int[] tmp178_172 = tmp172_166;
        tmp178_172[23] = 97;
        int[] tmp184_178 = tmp178_172;
        tmp184_178[24] = 114;
        int[] tmp190_184 = tmp184_178;
        tmp190_184[25] = 107;
        int[] tmp196_190 = tmp190_184;
        tmp196_190[26] = 111;
        int[] tmp202_196 = tmp196_190;
        tmp202_196[27] = 72;
        int[] tmp208_202 = tmp202_196;
        tmp208_202[28] = 113;
        int[] tmp214_208 = tmp208_202;
        tmp214_208[29] = 87;
        int[] tmp220_214 = tmp214_208;
        tmp220_214[30] = 112;
        int[] tmp226_220 = tmp220_214;
        tmp226_220[31] = 82;
        int[] tmp232_226 = tmp226_220;
        tmp232_226[32] = 56;
        int[] tmp238_232 = tmp232_226;
        tmp238_232[33] = 74;
        int[] tmp244_238 = tmp238_232;
        tmp244_238[34] = 76;
        int[] tmp250_244 = tmp244_238;
        tmp250_244[35] = 67;
        int[] tmp256_250 = tmp250_244;
        tmp256_250[36] = 45;
        int[] tmp262_256 = tmp256_250;
        tmp262_256[37] = 122;
        int[] tmp268_262 = tmp262_256;
        tmp268_262[38] = 55;
        int[] tmp274_268 = tmp268_262;
        tmp274_268[39] = 66;
        int[] tmp280_274 = tmp274_268;
        tmp280_274[40] = 81;
        int[] tmp286_280 = tmp280_274;
        tmp286_280[41] = 106;
        int[] tmp292_286 = tmp286_280;
        tmp292_286[42] = 48;
        int[] tmp298_292 = tmp292_286;
        tmp298_292[43] = 104;
        int[] tmp304_298 = tmp298_292;
        tmp304_298[44] = 79;
        int[] tmp310_304 = tmp304_298;
        tmp310_304[45] = 119;
        int[] tmp316_310 = tmp310_304;
        tmp316_310[46] = 69;
        int[] tmp322_316 = tmp316_310;
        tmp322_316[47] = 80;
        int[] tmp328_322 = tmp322_316;
        tmp328_322[48] = 73;
        int[] tmp334_328 = tmp328_322;
        tmp334_328[49] = 100;
        int[] tmp340_334 = tmp334_328;
        tmp340_334[50] = 103;
        int[] tmp346_340 = tmp340_334;
        tmp346_340[51] = 105;
        int[] tmp352_346 = tmp346_340;
        tmp352_346[52] = 75;
        int[] tmp358_352 = tmp352_346;
        tmp358_352[53] = 109;
        int[] tmp364_358 = tmp358_352;
        tmp364_358[54] = 77;
        int[] tmp370_364 = tmp364_358;
        tmp370_364[55] = 102;
        int[] tmp376_370 = tmp370_364;
        tmp376_370[56] = 71;
        int[] tmp382_376 = tmp376_370;
        tmp382_376[57] = 89;
        int[] tmp388_382 = tmp382_376;
        tmp388_382[58] = 50;
        int[] tmp394_388 = tmp388_382;
        tmp394_388[59] = 90;
        int[] tmp400_394 = tmp394_388;
        tmp400_394[60] = 118;
        int[] tmp406_400 = tmp400_394;
        tmp406_400[61] = 65;
        int[] tmp412_406 = tmp406_400;
        tmp412_406[62] = 52;
        int[] tmp418_412 = tmp412_406;
        tmp418_412[63] = 53;
        tmp418_412;
        i = 0;
      }
      catch (Exception paramString)
      {
        label429:
        paramString.printStackTrace();
        return null;
      }
      localObject = new String(arrayOfByte, "US-ASCII").trim();
      System.out.println(paramString + "\n" + (String)localObject);
      return localObject;
      if (m - 2 == j)
      {
        j = (localObject[j] & 0xFF) << 10 | (localObject[(j + 1)] & 0xFF) << 2;
        k = i + 1;
        arrayOfByte[i] = ((byte)arrayOfInt[(j >> 12 & 0x3F)]);
        i = k + 1;
        arrayOfByte[k] = ((byte)arrayOfInt[(j >> 6 & 0x3F)]);
        arrayOfByte[i] = ((byte)arrayOfInt[(j & 0x3F)]);
        arrayOfByte[(i + 1)] = 61;
        continue;
        while (j + 3 <= m)
        {
          k = (localObject[j] & 0xFF) << 16 | (localObject[(j + 1)] & 0xFF) << 8 | localObject[(j + 2)] & 0xFF;
          arrayOfByte[i] = ((byte)arrayOfInt[(k >> 18 & 0x3F)]);
          arrayOfByte[(i + 1)] = ((byte)arrayOfInt[(k >> 12 & 0x3F)]);
          arrayOfByte[(i + 2)] = ((byte)arrayOfInt[(k >> 6 & 0x3F)]);
          arrayOfByte[(i + 3)] = ((byte)arrayOfInt[(k & 0x3F)]);
          j += 3;
          i += 4;
        }
        if (m - 1 != j) {
          break label429;
        }
        j = (localObject[j] & 0xFF) << 4;
        k = i + 1;
        arrayOfByte[i] = ((byte)arrayOfInt[(j >> 6 & 0x3F)]);
        i = k + 1;
        arrayOfByte[k] = ((byte)arrayOfInt[(j & 0x3F)]);
        arrayOfByte[i] = 61;
        arrayOfByte[(i + 1)] = 61;
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
