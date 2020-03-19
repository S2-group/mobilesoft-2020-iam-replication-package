package com.altamob.sdk.internal.g;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.altamob.sdk.AltamobAdSDK;
import com.altamob.sdk.internal.b.b;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class h
{
  public h() {}
  
  /* Error */
  public static <T> T a(Context paramContext, String paramString)
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
    //   18: invokevirtual 26	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   21: astore_1
    //   22: aload_3
    //   23: astore_0
    //   24: aload_1
    //   25: ifnull -14 -> 11
    //   28: aload_3
    //   29: astore_0
    //   30: aload_1
    //   31: invokevirtual 32	java/io/File:exists	()Z
    //   34: ifeq -23 -> 11
    //   37: aload_3
    //   38: astore_0
    //   39: aload_1
    //   40: invokevirtual 36	java/io/File:length	()J
    //   43: ldc2_w 37
    //   46: lcmp
    //   47: ifgt -36 -> 11
    //   50: new 40	java/io/ObjectInputStream
    //   53: dup
    //   54: new 42	java/io/FileInputStream
    //   57: dup
    //   58: aload_1
    //   59: invokespecial 45	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   62: invokespecial 48	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   65: astore_1
    //   66: aload_1
    //   67: astore_0
    //   68: aload_1
    //   69: invokevirtual 52	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   72: astore_2
    //   73: aload_2
    //   74: astore_0
    //   75: aload_1
    //   76: ifnull -65 -> 11
    //   79: aload_1
    //   80: invokevirtual 55	java/io/ObjectInputStream:close	()V
    //   83: aload_2
    //   84: astore_0
    //   85: goto -74 -> 11
    //   88: astore_0
    //   89: new 57	java/lang/StringBuilder
    //   92: dup
    //   93: ldc 59
    //   95: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   98: aload_0
    //   99: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   102: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   105: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   108: aload_2
    //   109: astore_0
    //   110: goto -99 -> 11
    //   113: astore_0
    //   114: ldc 2
    //   116: monitorexit
    //   117: aload_0
    //   118: athrow
    //   119: astore_2
    //   120: aconst_null
    //   121: astore_1
    //   122: aload_1
    //   123: astore_0
    //   124: new 57	java/lang/StringBuilder
    //   127: dup
    //   128: ldc 78
    //   130: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   133: aload_2
    //   134: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   137: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   143: aload_3
    //   144: astore_0
    //   145: aload_1
    //   146: ifnull -135 -> 11
    //   149: aload_1
    //   150: invokevirtual 55	java/io/ObjectInputStream:close	()V
    //   153: aload_3
    //   154: astore_0
    //   155: goto -144 -> 11
    //   158: astore_0
    //   159: new 57	java/lang/StringBuilder
    //   162: dup
    //   163: ldc 59
    //   165: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   168: aload_0
    //   169: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   172: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   178: aload_3
    //   179: astore_0
    //   180: goto -169 -> 11
    //   183: astore_2
    //   184: aconst_null
    //   185: astore_1
    //   186: aload_1
    //   187: astore_0
    //   188: new 57	java/lang/StringBuilder
    //   191: dup
    //   192: ldc 78
    //   194: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   197: aload_2
    //   198: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   201: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   204: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   207: aload_3
    //   208: astore_0
    //   209: aload_1
    //   210: ifnull -199 -> 11
    //   213: aload_1
    //   214: invokevirtual 55	java/io/ObjectInputStream:close	()V
    //   217: aload_3
    //   218: astore_0
    //   219: goto -208 -> 11
    //   222: astore_0
    //   223: new 57	java/lang/StringBuilder
    //   226: dup
    //   227: ldc 59
    //   229: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   232: aload_0
    //   233: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   236: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   242: aload_3
    //   243: astore_0
    //   244: goto -233 -> 11
    //   247: astore_2
    //   248: aconst_null
    //   249: astore_1
    //   250: aload_1
    //   251: astore_0
    //   252: new 57	java/lang/StringBuilder
    //   255: dup
    //   256: ldc 78
    //   258: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   261: aload_2
    //   262: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   265: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   268: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   271: aload_3
    //   272: astore_0
    //   273: aload_1
    //   274: ifnull -263 -> 11
    //   277: aload_1
    //   278: invokevirtual 55	java/io/ObjectInputStream:close	()V
    //   281: aload_3
    //   282: astore_0
    //   283: goto -272 -> 11
    //   286: astore_0
    //   287: new 57	java/lang/StringBuilder
    //   290: dup
    //   291: ldc 59
    //   293: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   296: aload_0
    //   297: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   300: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   306: aload_3
    //   307: astore_0
    //   308: goto -297 -> 11
    //   311: astore_2
    //   312: aconst_null
    //   313: astore_1
    //   314: aload_1
    //   315: astore_0
    //   316: new 57	java/lang/StringBuilder
    //   319: dup
    //   320: ldc 78
    //   322: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   325: aload_2
    //   326: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   329: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   332: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   335: aload_3
    //   336: astore_0
    //   337: aload_1
    //   338: ifnull -327 -> 11
    //   341: aload_1
    //   342: invokevirtual 55	java/io/ObjectInputStream:close	()V
    //   345: aload_3
    //   346: astore_0
    //   347: goto -336 -> 11
    //   350: astore_0
    //   351: new 57	java/lang/StringBuilder
    //   354: dup
    //   355: ldc 59
    //   357: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   360: aload_0
    //   361: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   364: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   367: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   370: aload_3
    //   371: astore_0
    //   372: goto -361 -> 11
    //   375: astore_1
    //   376: aconst_null
    //   377: astore_0
    //   378: aload_0
    //   379: ifnull +7 -> 386
    //   382: aload_0
    //   383: invokevirtual 55	java/io/ObjectInputStream:close	()V
    //   386: aload_1
    //   387: athrow
    //   388: astore_0
    //   389: new 57	java/lang/StringBuilder
    //   392: dup
    //   393: ldc 59
    //   395: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   398: aload_0
    //   399: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   402: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   405: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   408: goto -22 -> 386
    //   411: astore_1
    //   412: goto -34 -> 378
    //   415: astore_2
    //   416: goto -102 -> 314
    //   419: astore_2
    //   420: goto -170 -> 250
    //   423: astore_2
    //   424: goto -238 -> 186
    //   427: astore_2
    //   428: goto -306 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	431	0	paramContext	Context
    //   0	431	1	paramString	String
    //   72	37	2	localObject1	Object
    //   119	15	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   183	15	2	localIOException1	java.io.IOException
    //   247	15	2	localException1	Exception
    //   311	15	2	localError1	Error
    //   415	1	2	localError2	Error
    //   419	1	2	localException2	Exception
    //   423	1	2	localIOException2	java.io.IOException
    //   427	1	2	localFileNotFoundException2	java.io.FileNotFoundException
    //   1	370	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   79	83	88	java/io/IOException
    //   79	83	113	finally
    //   89	108	113	finally
    //   149	153	113	finally
    //   159	178	113	finally
    //   213	217	113	finally
    //   223	242	113	finally
    //   277	281	113	finally
    //   287	306	113	finally
    //   341	345	113	finally
    //   351	370	113	finally
    //   382	386	113	finally
    //   386	388	113	finally
    //   389	408	113	finally
    //   16	22	119	java/io/FileNotFoundException
    //   30	37	119	java/io/FileNotFoundException
    //   39	66	119	java/io/FileNotFoundException
    //   149	153	158	java/io/IOException
    //   16	22	183	java/io/IOException
    //   30	37	183	java/io/IOException
    //   39	66	183	java/io/IOException
    //   213	217	222	java/io/IOException
    //   16	22	247	java/lang/Exception
    //   30	37	247	java/lang/Exception
    //   39	66	247	java/lang/Exception
    //   277	281	286	java/io/IOException
    //   16	22	311	java/lang/Error
    //   30	37	311	java/lang/Error
    //   39	66	311	java/lang/Error
    //   341	345	350	java/io/IOException
    //   16	22	375	finally
    //   30	37	375	finally
    //   39	66	375	finally
    //   382	386	388	java/io/IOException
    //   68	73	411	finally
    //   124	143	411	finally
    //   188	207	411	finally
    //   252	271	411	finally
    //   316	335	411	finally
    //   68	73	415	java/lang/Error
    //   68	73	419	java/lang/Exception
    //   68	73	423	java/io/IOException
    //   68	73	427	java/io/FileNotFoundException
  }
  
  public static String a(String paramString)
  {
    Context localContext = AltamobAdSDK.getInstance().getContext();
    if (localContext == null) {}
    while ((!"http://sdk.api.kaffnet.com/v4/pkg/crash.php".equals(paramString)) && (!"http://sdk.api.kaffnet.com/v4/pkg/config.php".equals(paramString)) && (!"http://sdk.api.kaffnet.com/v4/pkg/aps.php".equals(paramString)) && (!"http://sdk.api.kaffnet.com/v4/pkg/imp.php".equals(paramString))) {
      return paramString;
    }
    return paramString.replace("pkg", localContext.getPackageName());
  }
  
  public static Map<String, String> a(JSONObject paramJSONObject)
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
          j.c("key::::::" + (String)localObject + "::::::::::" + str);
          localHashMap.put(localObject, str);
        }
      }
      return localHashMap;
    }
    catch (Exception paramJSONObject) {}
  }
  
  /* Error */
  public static <T> void a(Context paramContext, String paramString, T paramT)
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
    //   16: new 155	java/io/ObjectOutputStream
    //   19: dup
    //   20: new 157	java/io/FileOutputStream
    //   23: dup
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual 26	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   29: invokespecial 158	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   32: invokespecial 161	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   35: astore_0
    //   36: aload_0
    //   37: astore_1
    //   38: aload_0
    //   39: aload_2
    //   40: invokevirtual 164	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   43: aload_0
    //   44: ifnull -37 -> 7
    //   47: aload_0
    //   48: invokevirtual 165	java/io/ObjectOutputStream:close	()V
    //   51: goto -44 -> 7
    //   54: astore_0
    //   55: new 57	java/lang/StringBuilder
    //   58: dup
    //   59: ldc -89
    //   61: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   64: aload_0
    //   65: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   68: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   74: goto -67 -> 7
    //   77: astore_0
    //   78: ldc 2
    //   80: monitorexit
    //   81: aload_0
    //   82: athrow
    //   83: astore_2
    //   84: aconst_null
    //   85: astore_0
    //   86: aload_0
    //   87: astore_1
    //   88: new 57	java/lang/StringBuilder
    //   91: dup
    //   92: ldc -87
    //   94: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   97: aload_2
    //   98: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   101: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   107: aload_0
    //   108: ifnull -101 -> 7
    //   111: aload_0
    //   112: invokevirtual 165	java/io/ObjectOutputStream:close	()V
    //   115: goto -108 -> 7
    //   118: astore_0
    //   119: new 57	java/lang/StringBuilder
    //   122: dup
    //   123: ldc -89
    //   125: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   128: aload_0
    //   129: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   132: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   138: goto -131 -> 7
    //   141: astore_1
    //   142: aload 4
    //   144: astore_0
    //   145: aload_0
    //   146: astore_3
    //   147: new 57	java/lang/StringBuilder
    //   150: dup
    //   151: ldc -87
    //   153: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   156: aload_1
    //   157: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   160: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   166: aload_0
    //   167: ifnull -160 -> 7
    //   170: aload_0
    //   171: invokevirtual 165	java/io/ObjectOutputStream:close	()V
    //   174: goto -167 -> 7
    //   177: astore_0
    //   178: new 57	java/lang/StringBuilder
    //   181: dup
    //   182: ldc -89
    //   184: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   187: aload_0
    //   188: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   191: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   194: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   197: goto -190 -> 7
    //   200: astore_0
    //   201: aload_3
    //   202: ifnull +7 -> 209
    //   205: aload_3
    //   206: invokevirtual 165	java/io/ObjectOutputStream:close	()V
    //   209: aload_0
    //   210: athrow
    //   211: astore_1
    //   212: new 57	java/lang/StringBuilder
    //   215: dup
    //   216: ldc -89
    //   218: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   221: aload_1
    //   222: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   225: invokevirtual 70	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokestatic 76	com/altamob/sdk/internal/g/j:b	(Ljava/lang/Object;)V
    //   231: goto -22 -> 209
    //   234: astore_0
    //   235: aload_1
    //   236: astore_3
    //   237: goto -36 -> 201
    //   240: astore_1
    //   241: goto -96 -> 145
    //   244: astore_2
    //   245: goto -159 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	248	0	paramContext	Context
    //   0	248	1	paramString	String
    //   0	248	2	paramT	T
    //   12	225	3	localObject1	Object
    //   14	129	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   47	51	54	java/io/IOException
    //   47	51	77	finally
    //   55	74	77	finally
    //   111	115	77	finally
    //   119	138	77	finally
    //   170	174	77	finally
    //   178	197	77	finally
    //   205	209	77	finally
    //   209	211	77	finally
    //   212	231	77	finally
    //   16	36	83	java/lang/Exception
    //   111	115	118	java/io/IOException
    //   16	36	141	java/lang/Error
    //   170	174	177	java/io/IOException
    //   16	36	200	finally
    //   147	166	200	finally
    //   205	209	211	java/io/IOException
    //   38	43	234	finally
    //   88	107	234	finally
    //   38	43	240	java/lang/Error
    //   38	43	244	java/lang/Exception
  }
  
  public static boolean a()
  {
    if (Looper.myLooper() == null) {}
    while (!Looper.myLooper().equals(Looper.getMainLooper())) {
      return true;
    }
    return false;
  }
  
  public static boolean a(Context paramContext)
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
  
  public static boolean a(File paramFile)
  {
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
            if (!a(new File(paramFile, arrayOfString[i]))) {
              break label70;
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
    label70:
    return false;
  }
  
  public static int b(Context paramContext)
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
  
  public static void b(Context paramContext, String paramString)
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
  
  public static int c(Context paramContext, String paramString)
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
  
  /* Error */
  public static List<PackageInfo> c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: ldc 2
    //   8: monitorenter
    //   9: aload_0
    //   10: invokevirtual 254	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: iconst_0
    //   14: invokevirtual 271	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   17: astore_0
    //   18: aload_0
    //   19: ifnull +75 -> 94
    //   22: aload_0
    //   23: invokeinterface 276 1 0
    //   28: ifne +66 -> 94
    //   31: aload_0
    //   32: invokeinterface 279 1 0
    //   37: astore_1
    //   38: aload_1
    //   39: invokeinterface 129 1 0
    //   44: ifeq +45 -> 89
    //   47: aload_1
    //   48: invokeinterface 132 1 0
    //   53: checkcast 281	android/content/pm/PackageInfo
    //   56: getfield 285	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   59: getfield 291	android/content/pm/ApplicationInfo:flags	I
    //   62: iconst_1
    //   63: iand
    //   64: ifeq -26 -> 38
    //   67: aload_1
    //   68: invokeinterface 294 1 0
    //   73: goto -35 -> 38
    //   76: astore_0
    //   77: ldc 2
    //   79: monitorexit
    //   80: aload_0
    //   81: athrow
    //   82: astore_0
    //   83: aload_0
    //   84: invokevirtual 218	java/lang/Exception:printStackTrace	()V
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
    //   102: invokevirtual 295	java/lang/OutOfMemoryError:printStackTrace	()V
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
  
  public static HashSet<String> d(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    HashSet localHashSet = new HashSet();
    paramContext = c(paramContext);
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
  
  public static String e(Context paramContext)
  {
    Object localObject = null;
    if (paramContext == null) {
      return null;
    }
    try
    {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      localObject = str;
      b.a(paramContext, "android_id", str);
      return str;
    }
    catch (Exception paramContext) {}
    return localObject;
  }
}
