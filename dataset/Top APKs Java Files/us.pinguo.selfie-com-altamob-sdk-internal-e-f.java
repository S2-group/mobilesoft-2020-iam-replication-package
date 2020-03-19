package com.altamob.sdk.internal.e;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.altamob.sdk.AltamobAdSDK;
import com.altamob.sdk.internal.http.a;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class f
{
  public f() {}
  
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
    //   18: invokevirtual 24	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   21: astore_1
    //   22: aload_3
    //   23: astore_0
    //   24: aload_1
    //   25: ifnull -14 -> 11
    //   28: aload_3
    //   29: astore_0
    //   30: aload_1
    //   31: invokevirtual 30	java/io/File:exists	()Z
    //   34: ifeq -23 -> 11
    //   37: aload_3
    //   38: astore_0
    //   39: aload_1
    //   40: invokevirtual 34	java/io/File:length	()J
    //   43: ldc2_w 35
    //   46: lcmp
    //   47: ifgt -36 -> 11
    //   50: new 38	java/io/ObjectInputStream
    //   53: dup
    //   54: new 40	java/io/FileInputStream
    //   57: dup
    //   58: aload_1
    //   59: invokespecial 43	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   62: invokespecial 46	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   65: astore_1
    //   66: aload_1
    //   67: astore_0
    //   68: aload_1
    //   69: invokevirtual 50	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   72: astore_2
    //   73: aload_2
    //   74: astore_0
    //   75: aload_1
    //   76: ifnull -65 -> 11
    //   79: aload_1
    //   80: invokevirtual 53	java/io/ObjectInputStream:close	()V
    //   83: aload_2
    //   84: astore_0
    //   85: goto -74 -> 11
    //   88: astore_0
    //   89: new 55	java/lang/StringBuilder
    //   92: dup
    //   93: ldc 57
    //   95: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   98: aload_0
    //   99: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   102: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   105: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
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
    //   124: new 55	java/lang/StringBuilder
    //   127: dup
    //   128: ldc 75
    //   130: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   133: aload_2
    //   134: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   137: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   143: aload_3
    //   144: astore_0
    //   145: aload_1
    //   146: ifnull -135 -> 11
    //   149: aload_1
    //   150: invokevirtual 53	java/io/ObjectInputStream:close	()V
    //   153: aload_3
    //   154: astore_0
    //   155: goto -144 -> 11
    //   158: astore_0
    //   159: new 55	java/lang/StringBuilder
    //   162: dup
    //   163: ldc 57
    //   165: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   168: aload_0
    //   169: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   172: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   178: aload_3
    //   179: astore_0
    //   180: goto -169 -> 11
    //   183: astore_2
    //   184: aconst_null
    //   185: astore_1
    //   186: aload_1
    //   187: astore_0
    //   188: new 55	java/lang/StringBuilder
    //   191: dup
    //   192: ldc 75
    //   194: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   197: aload_2
    //   198: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   201: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   204: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   207: aload_3
    //   208: astore_0
    //   209: aload_1
    //   210: ifnull -199 -> 11
    //   213: aload_1
    //   214: invokevirtual 53	java/io/ObjectInputStream:close	()V
    //   217: aload_3
    //   218: astore_0
    //   219: goto -208 -> 11
    //   222: astore_0
    //   223: new 55	java/lang/StringBuilder
    //   226: dup
    //   227: ldc 57
    //   229: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   232: aload_0
    //   233: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   236: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   239: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   242: aload_3
    //   243: astore_0
    //   244: goto -233 -> 11
    //   247: astore_2
    //   248: aconst_null
    //   249: astore_1
    //   250: aload_1
    //   251: astore_0
    //   252: new 55	java/lang/StringBuilder
    //   255: dup
    //   256: ldc 75
    //   258: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   261: aload_2
    //   262: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   265: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   268: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   271: aload_3
    //   272: astore_0
    //   273: aload_1
    //   274: ifnull -263 -> 11
    //   277: aload_1
    //   278: invokevirtual 53	java/io/ObjectInputStream:close	()V
    //   281: aload_3
    //   282: astore_0
    //   283: goto -272 -> 11
    //   286: astore_0
    //   287: new 55	java/lang/StringBuilder
    //   290: dup
    //   291: ldc 57
    //   293: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   296: aload_0
    //   297: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   300: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   306: aload_3
    //   307: astore_0
    //   308: goto -297 -> 11
    //   311: astore_1
    //   312: aconst_null
    //   313: astore_0
    //   314: aload_0
    //   315: ifnull +7 -> 322
    //   318: aload_0
    //   319: invokevirtual 53	java/io/ObjectInputStream:close	()V
    //   322: aload_1
    //   323: athrow
    //   324: astore_0
    //   325: new 55	java/lang/StringBuilder
    //   328: dup
    //   329: ldc 57
    //   331: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   334: aload_0
    //   335: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   338: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   344: goto -22 -> 322
    //   347: astore_1
    //   348: goto -34 -> 314
    //   351: astore_2
    //   352: goto -102 -> 250
    //   355: astore_2
    //   356: goto -170 -> 186
    //   359: astore_2
    //   360: goto -238 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	363	0	paramContext	Context
    //   0	363	1	paramString	String
    //   72	37	2	localObject1	Object
    //   119	15	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   183	15	2	localIOException1	java.io.IOException
    //   247	15	2	localException1	Exception
    //   351	1	2	localException2	Exception
    //   355	1	2	localIOException2	java.io.IOException
    //   359	1	2	localFileNotFoundException2	java.io.FileNotFoundException
    //   1	306	3	localObject2	Object
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
    //   318	322	113	finally
    //   322	324	113	finally
    //   325	344	113	finally
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
    //   16	22	311	finally
    //   30	37	311	finally
    //   39	66	311	finally
    //   318	322	324	java/io/IOException
    //   68	73	347	finally
    //   124	143	347	finally
    //   188	207	347	finally
    //   252	271	347	finally
    //   68	73	351	java/lang/Exception
    //   68	73	355	java/io/IOException
    //   68	73	359	java/io/FileNotFoundException
  }
  
  public static String a(Map<String, String> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {
      return null;
    }
    paramMap = paramMap.entrySet().iterator();
    JSONObject localJSONObject = new JSONObject();
    while (paramMap.hasNext())
    {
      Object localObject = (Map.Entry)paramMap.next();
      String str = (String)((Map.Entry)localObject).getKey();
      localObject = (String)((Map.Entry)localObject).getValue();
      try
      {
        localJSONObject.put(str, localObject);
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localJSONObject.toString();
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
          h.b("key::::::" + (String)localObject + "::::::::::" + str);
          localHashMap.put(localObject, str);
        }
      }
      return localHashMap;
    }
    catch (Exception paramJSONObject) {}
  }
  
  public static boolean a(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.getState() == NetworkInfo.State.CONNECTED)) {
        return true;
      }
    }
    Log.e("isNetworkAvailable", "isNetworkAvailable: " + null);
    return false;
  }
  
  /* Error */
  public static <T> boolean a(Context paramContext, String paramString, T paramT)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: ldc 2
    //   5: monitorenter
    //   6: aload_0
    //   7: ifnonnull +11 -> 18
    //   10: iload 4
    //   12: istore_3
    //   13: ldc 2
    //   15: monitorexit
    //   16: iload_3
    //   17: ireturn
    //   18: new 188	java/io/ObjectOutputStream
    //   21: dup
    //   22: new 190	java/io/FileOutputStream
    //   25: dup
    //   26: aload_0
    //   27: aload_1
    //   28: invokevirtual 24	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   31: invokespecial 191	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   34: invokespecial 194	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   37: astore_1
    //   38: aload_1
    //   39: astore_0
    //   40: aload_1
    //   41: aload_2
    //   42: invokevirtual 197	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   45: aload_1
    //   46: ifnull +7 -> 53
    //   49: aload_1
    //   50: invokevirtual 198	java/io/ObjectOutputStream:close	()V
    //   53: iconst_1
    //   54: istore_3
    //   55: goto -42 -> 13
    //   58: astore_0
    //   59: new 55	java/lang/StringBuilder
    //   62: dup
    //   63: ldc -56
    //   65: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   68: aload_0
    //   69: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   78: goto -25 -> 53
    //   81: astore_0
    //   82: ldc 2
    //   84: monitorexit
    //   85: aload_0
    //   86: athrow
    //   87: astore_2
    //   88: aconst_null
    //   89: astore_1
    //   90: aload_1
    //   91: astore_0
    //   92: new 55	java/lang/StringBuilder
    //   95: dup
    //   96: ldc -54
    //   98: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   101: aload_2
    //   102: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   111: iload 4
    //   113: istore_3
    //   114: aload_1
    //   115: ifnull -102 -> 13
    //   118: aload_1
    //   119: invokevirtual 198	java/io/ObjectOutputStream:close	()V
    //   122: iload 4
    //   124: istore_3
    //   125: goto -112 -> 13
    //   128: astore_0
    //   129: new 55	java/lang/StringBuilder
    //   132: dup
    //   133: ldc -56
    //   135: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   138: aload_0
    //   139: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   148: iload 4
    //   150: istore_3
    //   151: goto -138 -> 13
    //   154: astore_1
    //   155: aconst_null
    //   156: astore_0
    //   157: aload_0
    //   158: ifnull +7 -> 165
    //   161: aload_0
    //   162: invokevirtual 198	java/io/ObjectOutputStream:close	()V
    //   165: aload_1
    //   166: athrow
    //   167: astore_0
    //   168: new 55	java/lang/StringBuilder
    //   171: dup
    //   172: ldc -56
    //   174: invokespecial 60	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   177: aload_0
    //   178: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   181: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   184: invokestatic 73	com/altamob/sdk/internal/e/h:a	(Ljava/lang/Object;)V
    //   187: goto -22 -> 165
    //   190: astore_1
    //   191: goto -34 -> 157
    //   194: astore_2
    //   195: goto -105 -> 90
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	198	0	paramContext	Context
    //   0	198	1	paramString	String
    //   0	198	2	paramT	T
    //   12	139	3	bool1	boolean
    //   1	148	4	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   49	53	58	java/io/IOException
    //   49	53	81	finally
    //   59	78	81	finally
    //   118	122	81	finally
    //   129	148	81	finally
    //   161	165	81	finally
    //   165	167	81	finally
    //   168	187	81	finally
    //   18	38	87	java/lang/Exception
    //   118	122	128	java/io/IOException
    //   18	38	154	finally
    //   161	165	167	java/io/IOException
    //   40	45	190	finally
    //   92	111	190	finally
    //   40	45	194	java/lang/Exception
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
  
  public static boolean a(String paramString)
  {
    return (TextUtils.isEmpty(paramString)) || (paramString.equals("null"));
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
  
  public static String b(String paramString)
  {
    Context localContext = AltamobAdSDK.getInstance().getContext();
    if (localContext == null) {}
    while ((!"http://sdk.api.altamob.com/v4/pkg/crash.php".equals(paramString)) && (!"http://sdk.api.altamob.com/v4/pkg/config.php".equals(paramString)) && (!"http://sdk.api.altamob.com/v4/pkg/aps.php".equals(paramString)) && (!"http://sdk.api.altamob.com/v4/pkg/imp.php".equals(paramString))) {
      return paramString;
    }
    return paramString.replace("pkg", localContext.getPackageName());
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getFileStreamPath(paramString);
      if ((paramContext == null) || (!paramContext.exists())) {
        break label35;
      }
      bool = paramContext.delete();
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        label35:
        boolean bool = false;
      }
    }
    finally {}
    return bool;
  }
  
  public static int c(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramString, 0);
      if (paramContext != null) {
        return localPackageManager.getApplicationEnabledSetting(paramString);
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    return -1;
  }
  
  public static List<PackageInfo> c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if ((paramContext == null) || (paramContext.isEmpty())) {
        break label81;
      }
      Iterator localIterator = paramContext.iterator();
      while (localIterator.hasNext()) {
        if ((((PackageInfo)localIterator.next()).applicationInfo.flags & 0x1) != 0) {
          localIterator.remove();
        }
      }
    }
    finally {}
    return paramContext;
    label81:
    return null;
  }
  
  public static HashSet<String> d(Context paramContext)
  {
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
    try
    {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      localObject = str;
      a.a(paramContext, "android_id", str);
      return str;
    }
    catch (Exception paramContext) {}
    return localObject;
  }
}
