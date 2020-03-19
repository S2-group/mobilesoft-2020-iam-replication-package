package com.mobisage.android;

import android.content.pm.ApplicationInfo;
import java.util.ArrayList;
import java.util.List;

final class MobiSageHtmlUtility
{
  private static String[][] a;
  
  static
  {
    String[] arrayOfString1 = { ".avi", "video/x-msvideo" };
    String[] arrayOfString2 = { ".bmp", "image/bmp" };
    String[] arrayOfString3 = { ".jpeg", "image/jpeg" };
    String[] arrayOfString4 = { ".jpg", "image/jpeg" };
    String[] arrayOfString5 = { ".m4v", "video/x-m4v" };
    String[] arrayOfString6 = { ".mov", "video/quicktime" };
    String[] arrayOfString7 = { ".mp2", "audio/x-mpeg" };
    String[] arrayOfString8 = { ".mpeg", "video/mpeg" };
    String[] arrayOfString9 = { ".mpg", "video/mpeg" };
    String[] arrayOfString10 = { ".mpg4", "video/mp4" };
    String[] arrayOfString11 = { ".png", "image/png" };
    String[] arrayOfString12 = { ".rmvb", "audio/x-pn-realaudio" };
    String[] arrayOfString13 = { ".wmv", "audio/x-ms-wmv" };
    a = new String[][] { { ".3gp", "video/3gpp" }, { ".asf", "video/x-ms-asf" }, arrayOfString1, arrayOfString2, { ".gif", "image/gif" }, { ".htm", "text/html" }, { ".html", "text/html" }, arrayOfString3, arrayOfString4, arrayOfString5, arrayOfString6, arrayOfString7, { ".mp3", "audio/x-mpeg" }, { ".mp4", "video/mp4" }, { ".mpe", "video/mpeg" }, arrayOfString8, arrayOfString9, arrayOfString10, { ".mpga", "audio/mpeg" }, { ".ogg", "audio/ogg" }, arrayOfString11, arrayOfString12, { ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" }, arrayOfString13, { "", "*/*" } };
  }
  
  /* Error */
  public static String a(android.content.Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 117	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 120	org/json/JSONObject:<init>	()V
    //   7: astore 6
    //   9: aload_0
    //   10: invokevirtual 126	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore_0
    //   14: new 128	android/content/Intent
    //   17: dup
    //   18: ldc -126
    //   20: invokespecial 133	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   23: astore 7
    //   25: aload 7
    //   27: ldc -121
    //   29: invokevirtual 139	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   32: pop
    //   33: aload_0
    //   34: aload 7
    //   36: iconst_0
    //   37: invokevirtual 145	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   40: astore 7
    //   42: aload 7
    //   44: invokeinterface 151 1 0
    //   49: istore 4
    //   51: aload_0
    //   52: iconst_0
    //   53: invokevirtual 155	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   56: invokestatic 158	com/mobisage/android/MobiSageHtmlUtility:a	(Ljava/util/List;)Ljava/util/List;
    //   59: astore 8
    //   61: new 160	android/text/format/Time
    //   64: dup
    //   65: invokespecial 161	android/text/format/Time:<init>	()V
    //   68: astore 9
    //   70: aload 8
    //   72: invokeinterface 151 1 0
    //   77: istore 5
    //   79: iconst_0
    //   80: istore_2
    //   81: iload_2
    //   82: iload 5
    //   84: if_icmpge +249 -> 333
    //   87: aload 8
    //   89: iload_2
    //   90: invokeinterface 165 2 0
    //   95: checkcast 167	android/content/pm/ApplicationInfo
    //   98: astore 10
    //   100: aload_0
    //   101: aload 10
    //   103: getfield 171	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   106: iconst_0
    //   107: invokevirtual 175	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   110: astore 10
    //   112: aload 10
    //   114: getfield 178	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   117: astore 11
    //   119: aload 11
    //   121: aload_1
    //   122: invokevirtual 182	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   125: ifeq +214 -> 339
    //   128: aload 6
    //   130: ldc -72
    //   132: aload 11
    //   134: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   137: pop
    //   138: aload 6
    //   140: ldc -66
    //   142: aload 10
    //   144: getfield 194	android/content/pm/PackageInfo:versionCode	I
    //   147: invokestatic 198	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   150: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   153: pop
    //   154: aload 10
    //   156: getfield 202	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   159: getfield 205	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   162: ifnull +123 -> 285
    //   165: aload 9
    //   167: new 207	java/io/File
    //   170: dup
    //   171: aload 10
    //   173: getfield 202	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   176: getfield 205	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   179: invokespecial 208	java/io/File:<init>	(Ljava/lang/String;)V
    //   182: invokevirtual 212	java/io/File:lastModified	()J
    //   185: invokevirtual 216	android/text/format/Time:set	(J)V
    //   188: aload 6
    //   190: ldc -38
    //   192: aload 9
    //   194: ldc -36
    //   196: invokevirtual 224	android/text/format/Time:format	(Ljava/lang/String;)Ljava/lang/String;
    //   199: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   202: pop
    //   203: aload 6
    //   205: ldc -30
    //   207: aload 10
    //   209: getfield 202	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   212: aload_0
    //   213: invokevirtual 230	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   216: invokevirtual 234	java/lang/Object:toString	()Ljava/lang/String;
    //   219: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   222: pop
    //   223: iconst_0
    //   224: istore_3
    //   225: iload_3
    //   226: iload 4
    //   228: if_icmpge +51 -> 279
    //   231: aload 7
    //   233: iload_3
    //   234: invokeinterface 165 2 0
    //   239: checkcast 236	android/content/pm/ResolveInfo
    //   242: astore 11
    //   244: aload 10
    //   246: getfield 178	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   249: aload 11
    //   251: getfield 240	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   254: getfield 243	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   257: invokevirtual 247	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   260: ifeq +86 -> 346
    //   263: aload 6
    //   265: ldc -7
    //   267: aload 11
    //   269: getfield 240	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   272: getfield 252	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   275: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   278: pop
    //   279: aload 6
    //   281: invokevirtual 253	org/json/JSONObject:toString	()Ljava/lang/String;
    //   284: areturn
    //   285: aload 9
    //   287: invokevirtual 256	android/text/format/Time:setToNow	()V
    //   290: aload 6
    //   292: ldc -38
    //   294: aload 9
    //   296: ldc -36
    //   298: invokevirtual 224	android/text/format/Time:format	(Ljava/lang/String;)Ljava/lang/String;
    //   301: invokevirtual 188	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   304: pop
    //   305: goto -102 -> 203
    //   308: astore 10
    //   310: aload 10
    //   312: invokevirtual 259	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   315: goto +24 -> 339
    //   318: astore 10
    //   320: aload 10
    //   322: invokevirtual 260	java/lang/Exception:printStackTrace	()V
    //   325: goto +14 -> 339
    //   328: astore_0
    //   329: aload_0
    //   330: invokevirtual 260	java/lang/Exception:printStackTrace	()V
    //   333: aload 6
    //   335: invokevirtual 253	org/json/JSONObject:toString	()Ljava/lang/String;
    //   338: areturn
    //   339: iload_2
    //   340: iconst_1
    //   341: iadd
    //   342: istore_2
    //   343: goto -262 -> 81
    //   346: iload_3
    //   347: iconst_1
    //   348: iadd
    //   349: istore_3
    //   350: goto -125 -> 225
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	353	0	paramContext	android.content.Context
    //   0	353	1	paramString	String
    //   80	263	2	i	int
    //   224	126	3	j	int
    //   49	180	4	k	int
    //   77	8	5	m	int
    //   7	327	6	localJSONObject	org.json.JSONObject
    //   23	209	7	localObject1	Object
    //   59	29	8	localList	List
    //   68	227	9	localTime	android.text.format.Time
    //   98	147	10	localObject2	Object
    //   308	3	10	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   318	3	10	localException	Exception
    //   117	151	11	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   100	203	308	android/content/pm/PackageManager$NameNotFoundException
    //   203	223	308	android/content/pm/PackageManager$NameNotFoundException
    //   231	279	308	android/content/pm/PackageManager$NameNotFoundException
    //   279	285	308	android/content/pm/PackageManager$NameNotFoundException
    //   285	305	308	android/content/pm/PackageManager$NameNotFoundException
    //   100	203	318	java/lang/Exception
    //   203	223	318	java/lang/Exception
    //   231	279	318	java/lang/Exception
    //   279	285	318	java/lang/Exception
    //   285	305	318	java/lang/Exception
    //   9	79	328	java/lang/Exception
    //   87	100	328	java/lang/Exception
    //   310	315	328	java/lang/Exception
    //   320	325	328	java/lang/Exception
  }
  
  /* Error */
  public static String a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: ldc 2
    //   4: monitorenter
    //   5: new 266	org/apache/http/client/methods/HttpGet
    //   8: dup
    //   9: aload_0
    //   10: invokespecial 267	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   13: astore_0
    //   14: new 269	org/apache/http/impl/client/DefaultHttpClient
    //   17: dup
    //   18: invokespecial 270	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   21: astore_2
    //   22: aload_2
    //   23: invokeinterface 276 1 0
    //   28: ldc_w 278
    //   31: sipush 10000
    //   34: invokestatic 283	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   37: invokeinterface 289 3 0
    //   42: pop
    //   43: aload_2
    //   44: invokeinterface 276 1 0
    //   49: ldc_w 291
    //   52: sipush 5000
    //   55: invokestatic 283	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   58: invokeinterface 289 3 0
    //   63: pop
    //   64: aload_0
    //   65: ldc_w 293
    //   68: ldc_w 295
    //   71: invokevirtual 299	org/apache/http/client/methods/HttpGet:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   74: aload_2
    //   75: aload_0
    //   76: invokeinterface 303 2 0
    //   81: astore_2
    //   82: aload_1
    //   83: astore_0
    //   84: aload_2
    //   85: invokeinterface 309 1 0
    //   90: invokeinterface 314 1 0
    //   95: sipush 200
    //   98: if_icmpne +16 -> 114
    //   101: aload_2
    //   102: invokeinterface 318 1 0
    //   107: ldc_w 320
    //   110: invokestatic 325	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   113: astore_0
    //   114: ldc 2
    //   116: monitorexit
    //   117: aload_0
    //   118: areturn
    //   119: astore_0
    //   120: ldc 2
    //   122: monitorexit
    //   123: aload_0
    //   124: athrow
    //   125: astore_0
    //   126: aload_1
    //   127: astore_0
    //   128: goto -14 -> 114
    //   131: astore_0
    //   132: aload_1
    //   133: astore_0
    //   134: goto -20 -> 114
    //   137: astore_0
    //   138: aload_1
    //   139: astore_0
    //   140: goto -26 -> 114
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	143	0	paramString	String
    //   1	138	1	localObject1	Object
    //   21	81	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   5	82	119	finally
    //   84	114	119	finally
    //   5	82	125	java/lang/Exception
    //   84	114	125	java/lang/Exception
    //   5	82	131	java/io/IOException
    //   84	114	131	java/io/IOException
    //   5	82	137	org/apache/http/client/ClientProtocolException
    //   84	114	137	org/apache/http/client/ClientProtocolException
  }
  
  /* Error */
  public static List<AppInfoHolder> a(android.content.Context paramContext)
  {
    // Byte code:
    //   0: new 328	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 329	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: invokevirtual 126	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore_0
    //   14: new 128	android/content/Intent
    //   17: dup
    //   18: ldc -126
    //   20: invokespecial 133	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   23: astore 6
    //   25: aload 6
    //   27: ldc -121
    //   29: invokevirtual 139	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   32: pop
    //   33: aload_0
    //   34: aload 6
    //   36: iconst_0
    //   37: invokevirtual 145	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   40: astore 6
    //   42: aload 6
    //   44: invokeinterface 151 1 0
    //   49: istore_3
    //   50: aload_0
    //   51: iconst_0
    //   52: invokevirtual 155	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   55: invokestatic 158	com/mobisage/android/MobiSageHtmlUtility:a	(Ljava/util/List;)Ljava/util/List;
    //   58: astore 7
    //   60: new 160	android/text/format/Time
    //   63: dup
    //   64: invokespecial 161	android/text/format/Time:<init>	()V
    //   67: astore 8
    //   69: aload 7
    //   71: invokeinterface 151 1 0
    //   76: istore 4
    //   78: iconst_0
    //   79: istore_1
    //   80: iload_1
    //   81: iload 4
    //   83: if_icmpge +243 -> 326
    //   86: aload 7
    //   88: iload_1
    //   89: invokeinterface 165 2 0
    //   94: checkcast 167	android/content/pm/ApplicationInfo
    //   97: astore 10
    //   99: new 6	com/mobisage/android/MobiSageHtmlUtility$AppInfoHolder
    //   102: dup
    //   103: invokespecial 330	com/mobisage/android/MobiSageHtmlUtility$AppInfoHolder:<init>	()V
    //   106: astore 9
    //   108: aload_0
    //   109: aload 10
    //   111: getfield 171	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   114: iconst_0
    //   115: invokevirtual 175	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   118: astore 10
    //   120: aload 9
    //   122: aload 10
    //   124: getfield 178	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   127: invokestatic 335	com/mobisage/android/MobiSageURIUtility:encrypt	(Ljava/lang/String;)Ljava/lang/String;
    //   130: putfield 337	com/mobisage/android/MobiSageHtmlUtility$AppInfoHolder:a	Ljava/lang/String;
    //   133: aload 9
    //   135: aload 10
    //   137: getfield 194	android/content/pm/PackageInfo:versionCode	I
    //   140: invokestatic 198	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   143: invokestatic 335	com/mobisage/android/MobiSageURIUtility:encrypt	(Ljava/lang/String;)Ljava/lang/String;
    //   146: putfield 340	com/mobisage/android/MobiSageHtmlUtility$AppInfoHolder:c	Ljava/lang/String;
    //   149: aload 10
    //   151: getfield 202	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   154: getfield 205	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   157: ifnull +133 -> 290
    //   160: aload 8
    //   162: new 207	java/io/File
    //   165: dup
    //   166: aload 10
    //   168: getfield 202	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   171: getfield 205	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   174: invokespecial 208	java/io/File:<init>	(Ljava/lang/String;)V
    //   177: invokevirtual 212	java/io/File:lastModified	()J
    //   180: invokevirtual 216	android/text/format/Time:set	(J)V
    //   183: aload 9
    //   185: aload 8
    //   187: ldc -36
    //   189: invokevirtual 224	android/text/format/Time:format	(Ljava/lang/String;)Ljava/lang/String;
    //   192: invokestatic 335	com/mobisage/android/MobiSageURIUtility:encrypt	(Ljava/lang/String;)Ljava/lang/String;
    //   195: putfield 343	com/mobisage/android/MobiSageHtmlUtility$AppInfoHolder:e	Ljava/lang/String;
    //   198: aload 9
    //   200: aload 10
    //   202: getfield 202	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   205: aload_0
    //   206: invokevirtual 230	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   209: invokevirtual 234	java/lang/Object:toString	()Ljava/lang/String;
    //   212: invokestatic 335	com/mobisage/android/MobiSageURIUtility:encrypt	(Ljava/lang/String;)Ljava/lang/String;
    //   215: putfield 346	com/mobisage/android/MobiSageHtmlUtility$AppInfoHolder:d	Ljava/lang/String;
    //   218: iconst_0
    //   219: istore_2
    //   220: iload_2
    //   221: iload_3
    //   222: if_icmpge +51 -> 273
    //   225: aload 6
    //   227: iload_2
    //   228: invokeinterface 165 2 0
    //   233: checkcast 236	android/content/pm/ResolveInfo
    //   236: astore 11
    //   238: aload 10
    //   240: getfield 178	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   243: aload 11
    //   245: getfield 240	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   248: getfield 243	android/content/pm/ActivityInfo:packageName	Ljava/lang/String;
    //   251: invokevirtual 247	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   254: ifeq +64 -> 318
    //   257: aload 9
    //   259: aload 11
    //   261: getfield 240	android/content/pm/ResolveInfo:activityInfo	Landroid/content/pm/ActivityInfo;
    //   264: getfield 252	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   267: invokestatic 335	com/mobisage/android/MobiSageURIUtility:encrypt	(Ljava/lang/String;)Ljava/lang/String;
    //   270: putfield 349	com/mobisage/android/MobiSageHtmlUtility$AppInfoHolder:b	Ljava/lang/String;
    //   273: aload 5
    //   275: aload 9
    //   277: invokeinterface 352 2 0
    //   282: pop
    //   283: iload_1
    //   284: iconst_1
    //   285: iadd
    //   286: istore_1
    //   287: goto -207 -> 80
    //   290: aload 8
    //   292: invokevirtual 256	android/text/format/Time:setToNow	()V
    //   295: aload 9
    //   297: aload 8
    //   299: ldc -36
    //   301: invokevirtual 224	android/text/format/Time:format	(Ljava/lang/String;)Ljava/lang/String;
    //   304: invokestatic 335	com/mobisage/android/MobiSageURIUtility:encrypt	(Ljava/lang/String;)Ljava/lang/String;
    //   307: putfield 343	com/mobisage/android/MobiSageHtmlUtility$AppInfoHolder:e	Ljava/lang/String;
    //   310: goto -112 -> 198
    //   313: astore 10
    //   315: goto -42 -> 273
    //   318: iload_2
    //   319: iconst_1
    //   320: iadd
    //   321: istore_2
    //   322: goto -102 -> 220
    //   325: astore_0
    //   326: aload 5
    //   328: areturn
    //   329: astore 10
    //   331: goto -58 -> 273
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	334	0	paramContext	android.content.Context
    //   79	208	1	i	int
    //   219	103	2	j	int
    //   49	174	3	k	int
    //   76	8	4	m	int
    //   7	320	5	localArrayList	ArrayList
    //   23	203	6	localObject1	Object
    //   58	29	7	localList	List
    //   67	231	8	localTime	android.text.format.Time
    //   106	190	9	localAppInfoHolder	AppInfoHolder
    //   97	142	10	localObject2	Object
    //   313	1	10	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   329	1	10	localException	Exception
    //   236	24	11	localResolveInfo	android.content.pm.ResolveInfo
    // Exception table:
    //   from	to	target	type
    //   108	198	313	android/content/pm/PackageManager$NameNotFoundException
    //   198	218	313	android/content/pm/PackageManager$NameNotFoundException
    //   225	273	313	android/content/pm/PackageManager$NameNotFoundException
    //   290	310	313	android/content/pm/PackageManager$NameNotFoundException
    //   9	78	325	java/lang/Exception
    //   86	108	325	java/lang/Exception
    //   273	283	325	java/lang/Exception
    //   108	198	329	java/lang/Exception
    //   198	218	329	java/lang/Exception
    //   225	273	329	java/lang/Exception
    //   290	310	329	java/lang/Exception
  }
  
  private static List<ApplicationInfo> a(List<ApplicationInfo> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return paramList;
    }
    ArrayList localArrayList = new ArrayList();
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramList.get(i);
      if ((localApplicationInfo.flags & 0x1) == 0) {
        localArrayList.add(localApplicationInfo);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  protected static String b(String paramString)
  {
    String str1 = "*/*";
    int i = paramString.lastIndexOf(".");
    String str2;
    if (i < 0) {
      str2 = str1;
    }
    String str3;
    do
    {
      return str2;
      str3 = paramString.substring(i, paramString.length()).toLowerCase();
      str2 = str1;
    } while (str3 == "");
    i = 0;
    paramString = str1;
    for (;;)
    {
      str2 = paramString;
      if (i >= a.length) {
        break;
      }
      if (str3.equals(a[i][0])) {
        paramString = a[i][1];
      }
      i += 1;
    }
  }
  
  public static final class AppInfoHolder
  {
    String a;
    String b;
    String c;
    String d;
    String e;
    
    AppInfoHolder() {}
    
    public final boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof AppInfoHolder)) {
        return false;
      }
      paramObject = (AppInfoHolder)paramObject;
      return this.a.equals(paramObject.a);
    }
  }
}
