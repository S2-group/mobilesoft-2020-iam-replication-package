package com;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.ArrayList;
import java.util.List;
import promote.core.ConfigBean;

public final class acv
{
  private static ConfigBean a;
  private static String b = "";
  
  /* Error */
  public static ConfigBean a(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 20	com/acv:a	Lpromote/core/ConfigBean;
    //   3: ifnull +7 -> 10
    //   6: getstatic 20	com/acv:a	Lpromote/core/ConfigBean;
    //   9: areturn
    //   10: aload_0
    //   11: invokestatic 25	com/act:a	(Landroid/content/Context;)Ljava/lang/String;
    //   14: astore 5
    //   16: aload 5
    //   18: ifnull +16 -> 34
    //   21: aload 5
    //   23: astore_1
    //   24: ldc 12
    //   26: aload 5
    //   28: invokevirtual 31	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   31: ifeq +106 -> 137
    //   34: aload_0
    //   35: invokevirtual 37	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   38: getstatic 14	com/acv:b	Ljava/lang/String;
    //   41: invokevirtual 43	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   44: astore_0
    //   45: new 45	java/io/BufferedReader
    //   48: dup
    //   49: new 47	java/io/InputStreamReader
    //   52: dup
    //   53: aload_0
    //   54: invokespecial 51	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   57: invokespecial 54	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   60: astore_1
    //   61: aload_1
    //   62: astore_3
    //   63: aload_0
    //   64: astore_2
    //   65: new 56	java/lang/StringBuilder
    //   68: dup
    //   69: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   72: astore 4
    //   74: aload_1
    //   75: astore_3
    //   76: aload_0
    //   77: astore_2
    //   78: aload_1
    //   79: invokevirtual 62	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   82: astore 6
    //   84: aload 6
    //   86: ifnull +74 -> 160
    //   89: aload_1
    //   90: astore_3
    //   91: aload_0
    //   92: astore_2
    //   93: aload 4
    //   95: aload 6
    //   97: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: pop
    //   101: goto -27 -> 74
    //   104: astore 4
    //   106: aload_1
    //   107: astore_3
    //   108: aload_0
    //   109: astore_2
    //   110: aload 4
    //   112: invokevirtual 69	java/io/IOException:printStackTrace	()V
    //   115: aload_1
    //   116: ifnull +7 -> 123
    //   119: aload_1
    //   120: invokevirtual 72	java/io/BufferedReader:close	()V
    //   123: aload 5
    //   125: astore_1
    //   126: aload_0
    //   127: ifnull +10 -> 137
    //   130: aload_0
    //   131: invokevirtual 75	java/io/InputStream:close	()V
    //   134: aload 5
    //   136: astore_1
    //   137: new 77	com/google/gson/Gson
    //   140: dup
    //   141: invokespecial 78	com/google/gson/Gson:<init>	()V
    //   144: aload_1
    //   145: ldc 80
    //   147: invokevirtual 84	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   150: checkcast 80	promote/core/ConfigBean
    //   153: astore_0
    //   154: aload_0
    //   155: putstatic 20	com/acv:a	Lpromote/core/ConfigBean;
    //   158: aload_0
    //   159: areturn
    //   160: aload_1
    //   161: astore_3
    //   162: aload_0
    //   163: astore_2
    //   164: aload 4
    //   166: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   169: ldc 89
    //   171: invokestatic 95	java/net/URLDecoder:decode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   174: astore 4
    //   176: aload 4
    //   178: astore_2
    //   179: aload_2
    //   180: ifnonnull +25 -> 205
    //   183: aload_1
    //   184: invokevirtual 72	java/io/BufferedReader:close	()V
    //   187: aload_0
    //   188: ifnull +7 -> 195
    //   191: aload_0
    //   192: invokevirtual 75	java/io/InputStream:close	()V
    //   195: aconst_null
    //   196: areturn
    //   197: astore_0
    //   198: aload_0
    //   199: invokevirtual 69	java/io/IOException:printStackTrace	()V
    //   202: goto -7 -> 195
    //   205: aload_1
    //   206: invokevirtual 72	java/io/BufferedReader:close	()V
    //   209: aload_2
    //   210: astore_1
    //   211: aload_0
    //   212: ifnull -75 -> 137
    //   215: aload_0
    //   216: invokevirtual 75	java/io/InputStream:close	()V
    //   219: aload_2
    //   220: astore_1
    //   221: goto -84 -> 137
    //   224: astore_0
    //   225: aload_0
    //   226: invokevirtual 69	java/io/IOException:printStackTrace	()V
    //   229: aload_2
    //   230: astore_1
    //   231: goto -94 -> 137
    //   234: astore_0
    //   235: aload_0
    //   236: invokevirtual 69	java/io/IOException:printStackTrace	()V
    //   239: aload 5
    //   241: astore_1
    //   242: goto -105 -> 137
    //   245: astore_1
    //   246: aconst_null
    //   247: astore_3
    //   248: aconst_null
    //   249: astore_0
    //   250: aload_3
    //   251: ifnull +7 -> 258
    //   254: aload_3
    //   255: invokevirtual 72	java/io/BufferedReader:close	()V
    //   258: aload_0
    //   259: ifnull +7 -> 266
    //   262: aload_0
    //   263: invokevirtual 75	java/io/InputStream:close	()V
    //   266: aload_1
    //   267: athrow
    //   268: astore_0
    //   269: aload_0
    //   270: invokevirtual 69	java/io/IOException:printStackTrace	()V
    //   273: goto -7 -> 266
    //   276: astore_1
    //   277: aconst_null
    //   278: astore_3
    //   279: goto -29 -> 250
    //   282: astore_1
    //   283: aload_2
    //   284: astore_0
    //   285: goto -35 -> 250
    //   288: astore 4
    //   290: aconst_null
    //   291: astore_0
    //   292: aconst_null
    //   293: astore_1
    //   294: goto -188 -> 106
    //   297: astore 4
    //   299: aconst_null
    //   300: astore_1
    //   301: goto -195 -> 106
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	304	0	paramContext	Context
    //   23	219	1	localObject1	Object
    //   245	22	1	localObject2	Object
    //   276	1	1	localObject3	Object
    //   282	1	1	localObject4	Object
    //   293	8	1	localObject5	Object
    //   64	220	2	localObject6	Object
    //   62	217	3	localObject7	Object
    //   72	22	4	localStringBuilder	StringBuilder
    //   104	61	4	localIOException1	java.io.IOException
    //   174	3	4	str1	String
    //   288	1	4	localIOException2	java.io.IOException
    //   297	1	4	localIOException3	java.io.IOException
    //   14	226	5	str2	String
    //   82	14	6	str3	String
    // Exception table:
    //   from	to	target	type
    //   65	74	104	java/io/IOException
    //   78	84	104	java/io/IOException
    //   93	101	104	java/io/IOException
    //   164	176	104	java/io/IOException
    //   183	187	197	java/io/IOException
    //   191	195	197	java/io/IOException
    //   205	209	224	java/io/IOException
    //   215	219	224	java/io/IOException
    //   119	123	234	java/io/IOException
    //   130	134	234	java/io/IOException
    //   34	45	245	finally
    //   254	258	268	java/io/IOException
    //   262	266	268	java/io/IOException
    //   45	61	276	finally
    //   65	74	282	finally
    //   78	84	282	finally
    //   93	101	282	finally
    //   110	115	282	finally
    //   164	176	282	finally
    //   34	45	288	java/io/IOException
    //   45	61	297	java/io/IOException
  }
  
  public static void a(String paramString)
  {
    b = paramString;
  }
  
  static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static boolean b(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null) {
        return paramContext.isAvailable();
      }
    }
    return false;
  }
}
