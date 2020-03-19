package com.mobi.sdk;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class invoke
{
  public invoke() {}
  
  public static String jdMethod_byte()
  {
    return "rMAEWU3EWsQPDMbPWMYOHM1-qgAIWtFOot/RkUAfH7dPrtbiDI==";
  }
  
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
  
  public static Activity jdMethod_do(View paramView)
  {
    for (paramView = paramView.getContext(); (paramView instanceof ContextWrapper); paramView = ((ContextWrapper)paramView).getBaseContext()) {
      if ((paramView instanceof Activity)) {
        return (Activity)paramView;
      }
    }
    return null;
  }
  
  public static BridgeModel jdMethod_do(Context paramContext)
  {
    BridgeModel localBridgeModel = new BridgeModel();
    localBridgeModel.className = jdMethod_do(paramContext, "c_n", "rtY-q7AjkVYCHMYKktYKD-zEWU3EWX==");
    localBridgeModel.actionName = jdMethod_do(paramContext, "c_ac", "rMAwD71Ek9FPotlEW73EkUzEkgYFNz/eledcamF1b-3ne-3n");
    localBridgeModel.flagName = jdMethod_do(paramContext, "c_f", "rtb-b7dJkfc=");
    localBridgeModel.flagParames = ax.jdMethod_do(paramContext, "c_fp", 32);
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
    //   1: astore 6
    //   3: ldc 2
    //   5: monitorenter
    //   6: aload_0
    //   7: ifnonnull +11 -> 18
    //   10: aload 6
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: aload_0
    //   19: aload_1
    //   20: invokevirtual 157	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   23: astore_0
    //   24: aload_0
    //   25: ifnull +12 -> 37
    //   28: aload_0
    //   29: invokevirtual 163	java/io/File:exists	()Z
    //   32: istore_2
    //   33: iload_2
    //   34: ifne +53 -> 87
    //   37: aload 6
    //   39: astore_0
    //   40: iconst_0
    //   41: ifeq -28 -> 13
    //   44: new 165	java/lang/NullPointerException
    //   47: dup
    //   48: invokespecial 166	java/lang/NullPointerException:<init>	()V
    //   51: athrow
    //   52: astore_0
    //   53: new 168	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   60: ldc -85
    //   62: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: aload_0
    //   66: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   69: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   75: aload 6
    //   77: astore_0
    //   78: goto -65 -> 13
    //   81: astore_0
    //   82: ldc 2
    //   84: monitorexit
    //   85: aload_0
    //   86: athrow
    //   87: aload_0
    //   88: invokevirtual 190	java/io/File:length	()J
    //   91: lstore_3
    //   92: lload_3
    //   93: ldc2_w 191
    //   96: lcmp
    //   97: ifle +47 -> 144
    //   100: aload 6
    //   102: astore_0
    //   103: iconst_0
    //   104: ifeq -91 -> 13
    //   107: new 165	java/lang/NullPointerException
    //   110: dup
    //   111: invokespecial 166	java/lang/NullPointerException:<init>	()V
    //   114: athrow
    //   115: astore_0
    //   116: new 168	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   123: ldc -85
    //   125: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: aload_0
    //   129: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   132: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   138: aload 6
    //   140: astore_0
    //   141: goto -128 -> 13
    //   144: new 194	java/io/ObjectInputStream
    //   147: dup
    //   148: new 196	java/io/FileInputStream
    //   151: dup
    //   152: aload_0
    //   153: invokespecial 199	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   156: invokespecial 202	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   159: astore_1
    //   160: aload_1
    //   161: astore_0
    //   162: aload_1
    //   163: invokevirtual 206	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   166: astore 5
    //   168: aload 5
    //   170: astore_0
    //   171: aload_1
    //   172: ifnull -159 -> 13
    //   175: aload_1
    //   176: invokevirtual 209	java/io/ObjectInputStream:close	()V
    //   179: aload 5
    //   181: astore_0
    //   182: goto -169 -> 13
    //   185: astore_0
    //   186: new 168	java/lang/StringBuilder
    //   189: dup
    //   190: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   193: ldc -85
    //   195: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: aload_0
    //   199: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   202: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   208: aload 5
    //   210: astore_0
    //   211: goto -198 -> 13
    //   214: astore 5
    //   216: aconst_null
    //   217: astore_1
    //   218: aload_1
    //   219: astore_0
    //   220: new 168	java/lang/StringBuilder
    //   223: dup
    //   224: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   227: ldc -45
    //   229: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: aload 5
    //   234: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   237: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   240: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   243: aload 6
    //   245: astore_0
    //   246: aload_1
    //   247: ifnull -234 -> 13
    //   250: aload_1
    //   251: invokevirtual 209	java/io/ObjectInputStream:close	()V
    //   254: aload 6
    //   256: astore_0
    //   257: goto -244 -> 13
    //   260: astore_0
    //   261: new 168	java/lang/StringBuilder
    //   264: dup
    //   265: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   268: ldc -85
    //   270: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: aload_0
    //   274: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   277: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   280: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   283: aload 6
    //   285: astore_0
    //   286: goto -273 -> 13
    //   289: astore 5
    //   291: aconst_null
    //   292: astore_1
    //   293: aload_1
    //   294: astore_0
    //   295: new 168	java/lang/StringBuilder
    //   298: dup
    //   299: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   302: ldc -45
    //   304: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: aload 5
    //   309: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   312: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   315: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   318: aload 6
    //   320: astore_0
    //   321: aload_1
    //   322: ifnull -309 -> 13
    //   325: aload_1
    //   326: invokevirtual 209	java/io/ObjectInputStream:close	()V
    //   329: aload 6
    //   331: astore_0
    //   332: goto -319 -> 13
    //   335: astore_0
    //   336: new 168	java/lang/StringBuilder
    //   339: dup
    //   340: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   343: ldc -85
    //   345: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: aload_0
    //   349: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   352: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   355: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   358: aload 6
    //   360: astore_0
    //   361: goto -348 -> 13
    //   364: astore 5
    //   366: aconst_null
    //   367: astore_1
    //   368: aload_1
    //   369: astore_0
    //   370: new 168	java/lang/StringBuilder
    //   373: dup
    //   374: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   377: ldc -45
    //   379: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: aload 5
    //   384: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   387: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   390: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   393: aload 6
    //   395: astore_0
    //   396: aload_1
    //   397: ifnull -384 -> 13
    //   400: aload_1
    //   401: invokevirtual 209	java/io/ObjectInputStream:close	()V
    //   404: aload 6
    //   406: astore_0
    //   407: goto -394 -> 13
    //   410: astore_0
    //   411: new 168	java/lang/StringBuilder
    //   414: dup
    //   415: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   418: ldc -85
    //   420: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: aload_0
    //   424: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   427: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   430: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   433: aload 6
    //   435: astore_0
    //   436: goto -423 -> 13
    //   439: astore 5
    //   441: aconst_null
    //   442: astore_1
    //   443: aload_1
    //   444: astore_0
    //   445: new 168	java/lang/StringBuilder
    //   448: dup
    //   449: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   452: ldc -45
    //   454: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   457: aload 5
    //   459: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   462: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   465: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   468: aload 6
    //   470: astore_0
    //   471: aload_1
    //   472: ifnull -459 -> 13
    //   475: aload_1
    //   476: invokevirtual 209	java/io/ObjectInputStream:close	()V
    //   479: aload 6
    //   481: astore_0
    //   482: goto -469 -> 13
    //   485: astore_0
    //   486: new 168	java/lang/StringBuilder
    //   489: dup
    //   490: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   493: ldc -85
    //   495: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: aload_0
    //   499: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   502: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   505: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   508: aload 6
    //   510: astore_0
    //   511: goto -498 -> 13
    //   514: astore_1
    //   515: aconst_null
    //   516: astore_0
    //   517: aload_0
    //   518: ifnull +7 -> 525
    //   521: aload_0
    //   522: invokevirtual 209	java/io/ObjectInputStream:close	()V
    //   525: aload_1
    //   526: athrow
    //   527: astore_0
    //   528: new 168	java/lang/StringBuilder
    //   531: dup
    //   532: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   535: ldc -85
    //   537: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   540: aload_0
    //   541: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   544: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   547: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   550: goto -25 -> 525
    //   553: astore_1
    //   554: goto -37 -> 517
    //   557: astore 5
    //   559: goto -116 -> 443
    //   562: astore 5
    //   564: goto -196 -> 368
    //   567: astore 5
    //   569: goto -276 -> 293
    //   572: astore 5
    //   574: goto -356 -> 218
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	577	0	paramContext	Context
    //   0	577	1	paramString	String
    //   32	2	2	bool	boolean
    //   91	2	3	l	long
    //   166	43	5	localObject1	Object
    //   214	19	5	localFileNotFoundException1	java.io.FileNotFoundException
    //   289	19	5	localIOException1	java.io.IOException
    //   364	19	5	localException1	Exception
    //   439	19	5	localError1	Error
    //   557	1	5	localError2	Error
    //   562	1	5	localException2	Exception
    //   567	1	5	localIOException2	java.io.IOException
    //   572	1	5	localFileNotFoundException2	java.io.FileNotFoundException
    //   1	508	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   44	52	52	java/io/IOException
    //   44	52	81	finally
    //   53	75	81	finally
    //   107	115	81	finally
    //   116	138	81	finally
    //   175	179	81	finally
    //   186	208	81	finally
    //   250	254	81	finally
    //   261	283	81	finally
    //   325	329	81	finally
    //   336	358	81	finally
    //   400	404	81	finally
    //   411	433	81	finally
    //   475	479	81	finally
    //   486	508	81	finally
    //   521	525	81	finally
    //   525	527	81	finally
    //   528	550	81	finally
    //   107	115	115	java/io/IOException
    //   175	179	185	java/io/IOException
    //   18	24	214	java/io/FileNotFoundException
    //   28	33	214	java/io/FileNotFoundException
    //   87	92	214	java/io/FileNotFoundException
    //   144	160	214	java/io/FileNotFoundException
    //   250	254	260	java/io/IOException
    //   18	24	289	java/io/IOException
    //   28	33	289	java/io/IOException
    //   87	92	289	java/io/IOException
    //   144	160	289	java/io/IOException
    //   325	329	335	java/io/IOException
    //   18	24	364	java/lang/Exception
    //   28	33	364	java/lang/Exception
    //   87	92	364	java/lang/Exception
    //   144	160	364	java/lang/Exception
    //   400	404	410	java/io/IOException
    //   18	24	439	java/lang/Error
    //   28	33	439	java/lang/Error
    //   87	92	439	java/lang/Error
    //   144	160	439	java/lang/Error
    //   475	479	485	java/io/IOException
    //   18	24	514	finally
    //   28	33	514	finally
    //   87	92	514	finally
    //   144	160	514	finally
    //   521	525	527	java/io/IOException
    //   162	168	553	finally
    //   220	243	553	finally
    //   295	318	553	finally
    //   370	393	553	finally
    //   445	468	553	finally
    //   162	168	557	java/lang/Error
    //   162	168	562	java/lang/Exception
    //   162	168	567	java/io/IOException
    //   162	168	572	java/io/FileNotFoundException
  }
  
  public static Object jdMethod_do(BridgeModel paramBridgeModel, String paramString1, String paramString2)
  {
    if (paramBridgeModel == null) {
      return null;
    }
    try
    {
      y.jdMethod_if(" reflex -------------- " + paramBridgeModel.className + " :::: " + paramBridgeModel.actionName + "   " + paramBridgeModel.putName + " ::::" + paramBridgeModel.flagName + ":::::" + paramBridgeModel.flagParames + " ---- " + paramBridgeModel.setName);
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
    return jdMethod_for("rtY-q7AjkVYjHBbzHBlErt/KotAEDzuulKwubK3RlebSbel=");
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
      ax.jdMethod_do(paramContext, "android_id", str);
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
    return jdMethod_for(ax.jdMethod_do(paramContext, paramString1, paramString2));
  }
  
  public static String jdMethod_do(String paramString)
  {
    return jdMethod_do(ADSDK.getContext(), paramString);
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
    //   10: invokevirtual 54	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: iconst_0
    //   14: invokevirtual 421	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   17: astore_0
    //   18: aload_0
    //   19: ifnull +75 -> 94
    //   22: aload_0
    //   23: invokeinterface 423 1 0
    //   28: ifne +66 -> 94
    //   31: aload_0
    //   32: invokeinterface 427 1 0
    //   37: astore_1
    //   38: aload_1
    //   39: invokeinterface 432 1 0
    //   44: ifeq +45 -> 89
    //   47: aload_1
    //   48: invokeinterface 435 1 0
    //   53: checkcast 407	android/content/pm/PackageInfo
    //   56: getfield 439	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   59: getfield 444	android/content/pm/ApplicationInfo:flags	I
    //   62: iconst_1
    //   63: iand
    //   64: ifeq -26 -> 38
    //   67: aload_1
    //   68: invokeinterface 447 1 0
    //   73: goto -35 -> 38
    //   76: astore_0
    //   77: ldc 2
    //   79: monitorexit
    //   80: aload_0
    //   81: athrow
    //   82: astore_0
    //   83: aload_0
    //   84: invokevirtual 47	java/lang/Exception:printStackTrace	()V
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
    //   102: invokevirtual 448	java/lang/OutOfMemoryError:printStackTrace	()V
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
  
  public static Map<String, Object> jdMethod_do(JSONArray paramJSONArray)
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localHashMap.putAll(jdMethod_do((JSONObject)paramJSONArray.get(i)));
      i += 1;
    }
    return localHashMap;
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
          y.jdMethod_if("key::::::" + (String)localObject + "::::::::::" + str);
          localHashMap.put(localObject, str);
        }
      }
      return localHashMap;
    }
    catch (Exception paramJSONObject) {}
  }
  
  public static JSONObject jdMethod_do(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("conf_version", "7");
      localJSONObject.put("app_pkg", at.jdField_do_of_type_JavaLangString);
      localJSONObject.put("app_version", at.jdField_if_of_type_JavaLangString);
      localJSONObject.put("sdk_version", "5.2.0.7");
      localJSONObject.put("aid", jdMethod_do(paramContext));
      localJSONObject.put("gaid_encrypted", 1);
      localJSONObject.put("gaid", case.jdMethod_do(ax.jdMethod_do(paramContext, "android_adid", "")));
      localJSONObject.put("os_version", Build.VERSION.RELEASE);
      localJSONObject.put("country", Locale.getDefault().getCountry());
      localJSONObject.put("kernel_version", ax.jdMethod_do(paramContext, "kernel_version", ""));
      String str2 = Locale.getDefault().getLanguage();
      String str1 = str2;
      if ("in".equals(str2)) {
        str1 = "id";
      }
      localJSONObject.put("language", str1);
      localJSONObject.put("network_type", "" + jdMethod_do(paramContext));
      localJSONObject.put("user_agent", jdMethod_if(paramContext));
      jdMethod_do(paramContext, localJSONObject);
      return localJSONObject;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return localJSONObject;
  }
  
  public static void jdMethod_do(Context paramContext, BridgeModel paramBridgeModel)
  {
    if (paramBridgeModel == null) {
      return;
    }
    jdMethod_do(paramContext, "c_n", paramBridgeModel.className);
    jdMethod_do(paramContext, "c_ac", paramBridgeModel.actionName);
    jdMethod_do(paramContext, "c_f", paramBridgeModel.flagName);
    ax.jdMethod_do(paramContext, "c_fp", paramBridgeModel.flagParames);
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
    //   16: new 585	java/io/ObjectOutputStream
    //   19: dup
    //   20: new 587	java/io/FileOutputStream
    //   23: dup
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual 157	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   29: invokespecial 588	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   32: invokespecial 591	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   35: astore_0
    //   36: aload_0
    //   37: astore_1
    //   38: aload_0
    //   39: aload_2
    //   40: invokevirtual 594	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   43: aload_0
    //   44: ifnull -37 -> 7
    //   47: aload_0
    //   48: invokevirtual 595	java/io/ObjectOutputStream:close	()V
    //   51: goto -44 -> 7
    //   54: astore_0
    //   55: new 168	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   62: ldc_w 597
    //   65: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: aload_0
    //   69: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   78: goto -71 -> 7
    //   81: astore_0
    //   82: ldc 2
    //   84: monitorexit
    //   85: aload_0
    //   86: athrow
    //   87: astore_2
    //   88: aconst_null
    //   89: astore_0
    //   90: aload_0
    //   91: astore_1
    //   92: new 168	java/lang/StringBuilder
    //   95: dup
    //   96: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   99: ldc_w 599
    //   102: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: aload_2
    //   106: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   115: aload_0
    //   116: ifnull -109 -> 7
    //   119: aload_0
    //   120: invokevirtual 595	java/io/ObjectOutputStream:close	()V
    //   123: goto -116 -> 7
    //   126: astore_0
    //   127: new 168	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   134: ldc_w 597
    //   137: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload_0
    //   141: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   150: goto -143 -> 7
    //   153: astore_1
    //   154: aload 4
    //   156: astore_0
    //   157: aload_0
    //   158: astore_3
    //   159: new 168	java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   166: ldc_w 599
    //   169: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: aload_1
    //   173: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   176: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   179: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   182: aload_0
    //   183: ifnull -176 -> 7
    //   186: aload_0
    //   187: invokevirtual 595	java/io/ObjectOutputStream:close	()V
    //   190: goto -183 -> 7
    //   193: astore_0
    //   194: new 168	java/lang/StringBuilder
    //   197: dup
    //   198: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   201: ldc_w 597
    //   204: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload_0
    //   208: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   211: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   217: goto -210 -> 7
    //   220: astore_0
    //   221: aload_3
    //   222: ifnull +7 -> 229
    //   225: aload_3
    //   226: invokevirtual 595	java/io/ObjectOutputStream:close	()V
    //   229: aload_0
    //   230: athrow
    //   231: astore_1
    //   232: new 168	java/lang/StringBuilder
    //   235: dup
    //   236: invokespecial 169	java/lang/StringBuilder:<init>	()V
    //   239: ldc_w 597
    //   242: invokevirtual 175	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: aload_1
    //   246: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   249: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   252: invokestatic 186	com/mobi/sdk/y:do	(Ljava/lang/Object;)V
    //   255: goto -26 -> 229
    //   258: astore_0
    //   259: aload_1
    //   260: astore_3
    //   261: goto -40 -> 221
    //   264: astore_1
    //   265: goto -108 -> 157
    //   268: astore_2
    //   269: goto -179 -> 90
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	272	0	paramContext	Context
    //   0	272	1	paramString	String
    //   0	272	2	paramT	T
    //   12	249	3	localObject1	Object
    //   14	141	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   47	51	54	java/io/IOException
    //   47	51	81	finally
    //   55	78	81	finally
    //   119	123	81	finally
    //   127	150	81	finally
    //   186	190	81	finally
    //   194	217	81	finally
    //   225	229	81	finally
    //   229	231	81	finally
    //   232	255	81	finally
    //   16	36	87	java/lang/Exception
    //   119	123	126	java/io/IOException
    //   16	36	153	java/lang/Error
    //   186	190	193	java/io/IOException
    //   16	36	220	finally
    //   159	182	220	finally
    //   225	229	231	java/io/IOException
    //   38	43	258	finally
    //   92	115	258	finally
    //   38	43	264	java/lang/Error
    //   38	43	268	java/lang/Exception
  }
  
  public static void jdMethod_do(Context paramContext, String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return;
    }
    ax.jdMethod_do(paramContext, paramString1, jdMethod_if(paramString2));
  }
  
  public static void jdMethod_do(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString3))) {
      return;
    }
    ax.jdMethod_do(paramContext, "tid_" + paramString1, paramString2);
    ax.jdMethod_do(paramContext, "clk_time_" + paramString1, paramString3);
  }
  
  public static void jdMethod_do(Context paramContext, JSONObject paramJSONObject)
  {
    if ((paramContext == null) || (paramJSONObject == null)) {}
    for (;;)
    {
      return;
      try
      {
        JSONObject localJSONObject = new JSONObject();
        DeviceInfo localDeviceInfo = (DeviceInfo)jdMethod_do(paramContext, "mobi_device");
        if (localDeviceInfo != null)
        {
          localJSONObject.put("GoogleAdvertiserID", case.jdMethod_do(ax.jdMethod_do(paramContext, "android_adid", "")));
          localJSONObject.put("android_id", case.jdMethod_do(ax.jdMethod_do(paramContext, "android_id", "")));
          localJSONObject.put("brand", localDeviceInfo.getProductBrand());
          localJSONObject.put("device", localDeviceInfo.getDevice());
          localJSONObject.put("product", localDeviceInfo.getProductName());
          localJSONObject.put("sdk_init", localDeviceInfo.getSdkInit());
          localJSONObject.put("deviceType", localDeviceInfo.getDeviceType());
          localJSONObject.put("packagename", paramContext.getPackageName());
          localJSONObject.put("model", localDeviceInfo.getProductModel());
          localJSONObject.put("manufacturer", localDeviceInfo.getManufacturer());
          localJSONObject.put("cpu_abi", localDeviceInfo.getCpuabi());
          localJSONObject.put("buildName", localDeviceInfo.getBuildName());
          localJSONObject.put("display_id", localDeviceInfo.getDisplay_id());
          localJSONObject.put("abi", localDeviceInfo.getAbi());
          localJSONObject.put("abi2", localDeviceInfo.getAbi2());
          localJSONObject.put("arch", localDeviceInfo.getArch());
          localJSONObject.put("latitude", localDeviceInfo.getLatitude());
          localJSONObject.put("longitude", localDeviceInfo.getLongitude());
          localJSONObject.put("timestamp", localDeviceInfo.getTimestap());
          localJSONObject.put("lang_code", Locale.getDefault().getLanguage());
          localJSONObject.put("lang", localDeviceInfo.getLanDisplay());
          localJSONObject.put("operator", localDeviceInfo.getOperator());
          localJSONObject.put("carrier", localDeviceInfo.getCarrier());
          localJSONObject.put("net_work", localDeviceInfo.getNet_Work());
          localJSONObject.put("app_version_code", at.else);
          localJSONObject.put("screen_size", localDeviceInfo.getScreenSize());
          localJSONObject.put("screen_format", localDeviceInfo.getScreenFormat());
          localJSONObject.put("screenDensity", localDeviceInfo.getScreenDensity());
          localJSONObject.put("displayWidth", localDeviceInfo.getScreenWidth());
          localJSONObject.put("displayHeight", localDeviceInfo.getScreenHeight());
          localJSONObject.put("macaddress", localDeviceInfo.getMac());
          localJSONObject.put("mcc", localDeviceInfo.getMcc());
          localJSONObject.put("mnc", localDeviceInfo.getMnc());
          localJSONObject.put("device_carrier", localDeviceInfo.getDeviceCarrier());
          localJSONObject.put("country_code", localDeviceInfo.getCountryCode());
          localJSONObject.put("sim_code", localDeviceInfo.getSimCode());
          localJSONObject.put("art", ax.jdMethod_do(paramContext, "art", ""));
          localJSONObject.put("meids", localDeviceInfo.getMeids());
          localJSONObject.put("ip", localDeviceInfo.getIp());
          localJSONObject.put("sensors", else.jdMethod_do(paramContext).jdMethod_do());
          paramJSONObject.put("extras", localJSONObject);
          return;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
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
  
  public static boolean jdMethod_do(Context paramContext, int paramInt, String paramString)
  {
    try
    {
      boolean bool = array.jdMethod_do(paramContext).jdMethod_do(paramContext, "mt", jdMethod_if(paramString));
      if (bool) {
        return true;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    for (;;)
    {
      try
      {
        HotServerAdEntity localHotServerAdEntity = array.jdMethod_do(paramContext).jdMethod_do(paramString);
        StringBuilder localStringBuilder = new StringBuilder().append("checkPkg in DB ---------------------- ");
        if (localHotServerAdEntity == null) {
          break label252;
        }
        localObject = "" + localHotServerAdEntity.getPackage_name() + " :::: " + localHotServerAdEntity.getRv();
        y.jdMethod_if((String)localObject);
        if ((localHotServerAdEntity != null) && (!TextUtils.isEmpty(localHotServerAdEntity.getRv())))
        {
          localObject = localHotServerAdEntity.getCreate_time();
          if (array.jdMethod_do(paramContext).jdMethod_do(paramContext, (String)localObject))
          {
            array.jdMethod_do(paramContext).jdMethod_do(paramString);
            return false;
          }
          if (localHotServerAdEntity.getClick_status() == 1) {
            break;
          }
          localObject = new aa();
          ((aa)localObject).jdField_do_of_type_JavaLangString = jdMethod_if(paramString);
          ((aa)localObject).jdField_do_of_type_Int = paramInt;
          ((aa)localObject).jdField_for = localHotServerAdEntity.getRv();
          ((aa)localObject).jdField_if_of_type_Int = 1;
          ((aa)localObject).jdField_new = "";
          ((aa)localObject).jdField_if_of_type_JavaLangString = localHotServerAdEntity.getClick_temp();
          ((aa)localObject).jdField_int = localHotServerAdEntity.getCreate_time();
          array.jdMethod_do(paramContext).jdMethod_do((aa)localObject);
          return true;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return false;
      label252:
      Object localObject = "";
    }
  }
  
  public static boolean jdMethod_do(Context paramContext, Class<?> paramClass)
  {
    if (paramContext == null) {
      return false;
    }
    for (;;)
    {
      try
      {
        List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if ((localList == null) || (localList.isEmpty())) {
          break;
        }
        int i = 0;
        boolean bool;
        if (i < localList.size())
        {
          ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localList.get(i);
          if (TextUtils.equals(localRunningServiceInfo.service.getPackageName(), paramContext.getPackageName()))
          {
            bool = TextUtils.equals(localRunningServiceInfo.service.getClassName(), paramClass.getName());
            if (bool)
            {
              bool = true;
              return bool;
            }
          }
          i += 1;
        }
        else
        {
          bool = false;
        }
      }
      catch (SecurityException paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
    }
  }
  
  public static boolean jdMethod_do(Context paramContext, String paramString)
  {
    paramContext = jdMethod_do(paramContext);
    if ((paramContext != null) && (!paramContext.isEmpty()))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (localPackageInfo == null) {
          return false;
        }
        if (localPackageInfo.packageName.equals(paramString)) {
          return true;
        }
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
  
  public static String jdMethod_for()
  {
    return jdMethod_for("WClPrf3KafriDBuQqX==");
  }
  
  public static String jdMethod_for(Context paramContext)
  {
    if (!TextUtils.isEmpty(at.int)) {
      return at.int;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString("appKey");
      try
      {
        at.int = paramContext;
        return paramContext;
      }
      catch (Exception localException1) {}
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    localException1.printStackTrace();
    return paramContext;
  }
  
  public static String jdMethod_for(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return "";
    }
    return ax.jdMethod_do(paramContext, "clk_time_" + paramString, "");
  }
  
  public static String jdMethod_for(String paramString)
  {
    return jdMethod_do(paramString.getBytes());
  }
  
  public static String jdMethod_if()
  {
    return jdMethod_for("rtY-q7AjkVYjHBbzHBlErt/KotAEDzuulKwubK3Re-3/Nmk1bX==");
  }
  
  public static String jdMethod_if(Context paramContext)
  {
    String str = ax.jdMethod_do(paramContext, "user_agent", "");
    if (TextUtils.isEmpty(str)) {
      new Handler(paramContext.getMainLooper()).post(initialize.jdMethod_do(paramContext));
    }
    return str;
  }
  
  public static String jdMethod_if(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "null";
    }
    y.jdMethod_if("getAppVersion --------------  " + paramString);
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "null";
  }
  
  public static String jdMethod_if(String paramString)
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
        k = j + 1;
        j = (localObject[j] & 0xFF) << 10 | (localObject[k] & 0xFF) << 2;
        k = i + 1;
        arrayOfByte[i] = ((byte)arrayOfInt[(j >> 12 & 0x3F)]);
        i = k + 1;
        arrayOfByte[k] = ((byte)arrayOfInt[(j >> 6 & 0x3F)]);
        k = i + 1;
        arrayOfByte[i] = ((byte)arrayOfInt[(j & 0x3F)]);
        arrayOfByte[k] = 61;
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
        j = i + 1;
        arrayOfByte[i] = 61;
        arrayOfByte[j] = 61;
      }
    }
  }
  
  public static Map<String, Map<String, String>> jdMethod_if(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return null;
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramJSONObject.keys();
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject2 = paramJSONObject.get(str);
      Object localObject1;
      if ((localObject2 instanceof JSONArray)) {
        localObject1 = jdMethod_do((JSONArray)localObject2);
      }
      for (;;)
      {
        localHashMap.put(str, (Map)localObject1);
        break;
        localObject1 = localObject2;
        if ((localObject2 instanceof JSONObject)) {
          localObject1 = jdMethod_if((JSONObject)localObject2);
        }
      }
    }
    return localHashMap;
  }
  
  public static boolean jdMethod_if(Context paramContext)
  {
    if (TextUtils.isEmpty(at.new)) {
      at.new = ax.jdMethod_do(paramContext, "ad", "");
    }
    if (TextUtils.isEmpty(at.try)) {
      at.try = ax.jdMethod_do(paramContext, "clk", "");
    }
    if (TextUtils.isEmpty(at.byte)) {
      at.byte = ax.jdMethod_do(paramContext, "php", "");
    }
    if (TextUtils.isEmpty(at.char)) {
      at.char = ax.jdMethod_do(paramContext, "ins", "");
    }
    return (!TextUtils.isEmpty(at.new)) && (!TextUtils.isEmpty(at.try)) && (!TextUtils.isEmpty(at.byte));
  }
  
  public static String jdMethod_int()
  {
    return jdMethod_for("WClPHNTEqUJI");
  }
  
  public static String jdMethod_new()
  {
    return jdMethod_for("rtbikaFMkaTPWCcPWUzKHUe=");
  }
  
  public static String jdMethod_try()
  {
    return "rMAEWU3EWsQPDMbPWMYOHM1-qI==";
  }
}
