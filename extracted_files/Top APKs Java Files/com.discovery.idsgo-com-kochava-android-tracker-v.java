package com.kochava.android.tracker;

final class v
  implements Runnable
{
  v(c paramC) {}
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  public final void run()
  {
    // Byte code:
    //   0: ldc 32
    //   2: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   5: invokestatic 42	android/os/Looper:prepare	()V
    //   8: lconst_0
    //   9: lstore 5
    //   11: invokestatic 48	java/lang/System:currentTimeMillis	()J
    //   14: lstore 7
    //   16: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   19: ldc 56
    //   21: invokestatic 48	java/lang/System:currentTimeMillis	()J
    //   24: invokeinterface 62 4 0
    //   29: lstore 9
    //   31: lload 7
    //   33: lload 9
    //   35: lsub
    //   36: lstore 5
    //   38: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   41: ldc 64
    //   43: invokeinterface 68 2 0
    //   48: ifeq +4025 -> 4073
    //   51: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   54: ldc 64
    //   56: ldc 70
    //   58: invokeinterface 74 3 0
    //   63: aload_0
    //   64: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   67: invokestatic 77	com/kochava/android/tracker/c:a	(Lcom/kochava/android/tracker/c;)Ljava/lang/String;
    //   70: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   73: ifne +4000 -> 4073
    //   76: new 85	java/lang/StringBuilder
    //   79: dup
    //   80: ldc 87
    //   82: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   85: aload_0
    //   86: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   89: invokestatic 77	com/kochava/android/tracker/c:a	(Lcom/kochava/android/tracker/c;)Ljava/lang/String;
    //   92: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: ldc 95
    //   97: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   106: iconst_1
    //   107: istore_1
    //   108: iload_1
    //   109: ifne +29 -> 138
    //   112: lload 5
    //   114: lconst_0
    //   115: lcmp
    //   116: ifle +22 -> 138
    //   119: lload 5
    //   121: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   124: ldc 101
    //   126: ldc2_w 102
    //   129: invokeinterface 62 4 0
    //   134: lcmp
    //   135: ifle +3552 -> 3687
    //   138: aconst_null
    //   139: astore 12
    //   141: iconst_0
    //   142: istore_1
    //   143: new 85	java/lang/StringBuilder
    //   146: dup
    //   147: ldc 105
    //   149: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   152: aload_0
    //   153: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   156: getfield 109	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   159: invokevirtual 112	org/json/JSONObject:toString	()Ljava/lang/String;
    //   162: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   171: invokestatic 115	com/kochava/android/tracker/c:d	()Ljava/lang/String;
    //   174: ifnull +15 -> 189
    //   177: invokestatic 115	com/kochava/android/tracker/c:d	()Ljava/lang/String;
    //   180: invokevirtual 118	java/lang/String:trim	()Ljava/lang/String;
    //   183: invokevirtual 122	java/lang/String:isEmpty	()Z
    //   186: ifeq +14 -> 200
    //   189: ldc 124
    //   191: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   194: ldc 126
    //   196: invokestatic 130	com/kochava/android/tracker/c:b	(Ljava/lang/String;)Ljava/lang/String;
    //   199: pop
    //   200: new 85	java/lang/StringBuilder
    //   203: dup
    //   204: ldc -124
    //   206: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   209: invokestatic 115	com/kochava/android/tracker/c:d	()Ljava/lang/String;
    //   212: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: ldc -122
    //   217: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   223: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   226: new 136	java/net/URL
    //   229: dup
    //   230: new 85	java/lang/StringBuilder
    //   233: dup
    //   234: ldc -118
    //   236: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   239: invokestatic 115	com/kochava/android/tracker/c:d	()Ljava/lang/String;
    //   242: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: ldc -122
    //   247: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   253: invokespecial 139	java/net/URL:<init>	(Ljava/lang/String;)V
    //   256: invokevirtual 143	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   259: checkcast 145	javax/net/ssl/HttpsURLConnection
    //   262: astore 13
    //   264: aload 13
    //   266: ldc -109
    //   268: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   271: ldc -107
    //   273: ldc 70
    //   275: invokeinterface 74 3 0
    //   280: invokevirtual 153	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   283: aload 13
    //   285: ldc -101
    //   287: ldc -99
    //   289: invokevirtual 153	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   292: aload 13
    //   294: ldc -97
    //   296: invokevirtual 162	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   299: aload 13
    //   301: sipush 30000
    //   304: invokevirtual 166	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
    //   307: aload 13
    //   309: sipush 30000
    //   312: invokevirtual 169	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
    //   315: aload 13
    //   317: iconst_1
    //   318: invokevirtual 173	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
    //   321: aload 13
    //   323: iconst_1
    //   324: invokevirtual 176	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
    //   327: aload 13
    //   329: invokevirtual 179	javax/net/ssl/HttpsURLConnection:connect	()V
    //   332: aload_0
    //   333: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   336: getfield 109	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   339: invokevirtual 112	org/json/JSONObject:toString	()Ljava/lang/String;
    //   342: astore 14
    //   344: new 85	java/lang/StringBuilder
    //   347: dup
    //   348: ldc -75
    //   350: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   353: aload 14
    //   355: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   361: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   364: ldc -73
    //   366: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   369: new 185	java/io/OutputStreamWriter
    //   372: dup
    //   373: aload 13
    //   375: invokevirtual 189	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   378: invokespecial 192	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   381: astore 15
    //   383: aload 15
    //   385: aload 14
    //   387: invokevirtual 195	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   390: aload 15
    //   392: invokevirtual 198	java/io/OutputStreamWriter:close	()V
    //   395: ldc -56
    //   397: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   400: new 202	java/lang/StringBuffer
    //   403: dup
    //   404: ldc 70
    //   406: invokespecial 203	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   409: astore 14
    //   411: new 205	java/io/BufferedReader
    //   414: dup
    //   415: new 207	java/io/InputStreamReader
    //   418: dup
    //   419: aload 13
    //   421: invokevirtual 211	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
    //   424: invokespecial 214	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   427: invokespecial 217	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   430: astore 13
    //   432: aload 13
    //   434: invokevirtual 220	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   437: astore 15
    //   439: aload 15
    //   441: ifnull +83 -> 524
    //   444: aload 14
    //   446: aload 15
    //   448: invokevirtual 223	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   451: pop
    //   452: goto -20 -> 432
    //   455: astore 12
    //   457: ldc -31
    //   459: aload 12
    //   461: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
    //   464: invokevirtual 235	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   467: ifeq +3198 -> 3665
    //   470: new 85	java/lang/StringBuilder
    //   473: dup
    //   474: ldc -19
    //   476: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   479: aload 12
    //   481: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   484: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   487: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   490: aload 12
    //   492: invokestatic 245	com/kochava/android/tracker/c:a	(Ljava/lang/Exception;)V
    //   495: return
    //   496: astore 12
    //   498: new 85	java/lang/StringBuilder
    //   501: dup
    //   502: ldc -9
    //   504: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   507: aload 12
    //   509: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   512: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   515: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   518: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   521: goto -483 -> 38
    //   524: aload 14
    //   526: invokevirtual 249	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   529: astore 13
    //   531: new 85	java/lang/StringBuilder
    //   534: dup
    //   535: ldc -5
    //   537: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   540: aload 13
    //   542: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   548: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   551: new 111	org/json/JSONObject
    //   554: dup
    //   555: aload 13
    //   557: invokespecial 252	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   560: astore 13
    //   562: aload 13
    //   564: astore 12
    //   566: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   569: ldc -2
    //   571: ldc 70
    //   573: invokeinterface 74 3 0
    //   578: ldc_w 256
    //   581: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   584: istore 11
    //   586: iload 11
    //   588: ifne +3482 -> 4070
    //   591: aload 12
    //   593: ifnull +3477 -> 4070
    //   596: aload 12
    //   598: ldc_w 258
    //   601: invokevirtual 262	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   604: astore 13
    //   606: iconst_0
    //   607: istore_2
    //   608: iload_2
    //   609: aload 13
    //   611: invokevirtual 268	org/json/JSONArray:length	()I
    //   614: if_icmpge +3451 -> 4065
    //   617: ldc_w 270
    //   620: aload 13
    //   622: iload_2
    //   623: invokevirtual 273	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   626: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   629: ifeq +988 -> 1617
    //   632: iconst_1
    //   633: istore_3
    //   634: iload_1
    //   635: istore_2
    //   636: iload_1
    //   637: iconst_4
    //   638: if_icmpge +7 -> 645
    //   641: iload_1
    //   642: iconst_1
    //   643: iadd
    //   644: istore_2
    //   645: new 85	java/lang/StringBuilder
    //   648: dup
    //   649: ldc_w 275
    //   652: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   655: invokestatic 278	com/kochava/android/tracker/c:e	()Ljava/util/HashMap;
    //   658: iload_2
    //   659: invokestatic 284	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   662: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   665: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   668: ldc_w 292
    //   671: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   674: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   677: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   680: invokestatic 278	com/kochava/android/tracker/c:e	()Ljava/util/HashMap;
    //   683: iload_2
    //   684: invokestatic 284	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   687: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   690: checkcast 280	java/lang/Integer
    //   693: invokevirtual 295	java/lang/Integer:intValue	()I
    //   696: i2l
    //   697: invokestatic 301	java/lang/Thread:sleep	(J)V
    //   700: iload_2
    //   701: istore_1
    //   702: iload_3
    //   703: istore_2
    //   704: iload_2
    //   705: ifne +5 -> 710
    //   708: iconst_0
    //   709: istore_1
    //   710: iload_1
    //   711: ifne +3351 -> 4062
    //   714: aload 12
    //   716: ifnull +1001 -> 1717
    //   719: new 85	java/lang/StringBuilder
    //   722: dup
    //   723: ldc_w 303
    //   726: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   729: aload 12
    //   731: invokevirtual 112	org/json/JSONObject:toString	()Ljava/lang/String;
    //   734: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   737: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   740: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   743: aconst_null
    //   744: astore 13
    //   746: aload 12
    //   748: ldc_w 305
    //   751: invokevirtual 309	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   754: astore 14
    //   756: aload 14
    //   758: astore 13
    //   760: new 85	java/lang/StringBuilder
    //   763: dup
    //   764: ldc_w 311
    //   767: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   770: aload 14
    //   772: invokevirtual 112	org/json/JSONObject:toString	()Ljava/lang/String;
    //   775: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   778: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   781: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   784: aload 14
    //   786: astore 13
    //   788: aload 13
    //   790: ifnull +705 -> 1495
    //   793: aload 13
    //   795: ldc_w 313
    //   798: invokevirtual 315	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   801: astore 14
    //   803: new 85	java/lang/StringBuilder
    //   806: dup
    //   807: ldc_w 317
    //   810: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   813: aload 14
    //   815: invokevirtual 318	java/lang/String:toString	()Ljava/lang/String;
    //   818: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   821: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   824: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   827: aload 14
    //   829: invokestatic 320	com/kochava/android/tracker/c:c	(Ljava/lang/String;)Ljava/lang/String;
    //   832: pop
    //   833: aload 13
    //   835: ldc_w 322
    //   838: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   841: ldc_w 327
    //   844: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   847: ifeq +7 -> 854
    //   850: invokestatic 331	com/kochava/android/tracker/c:f	()Z
    //   853: pop
    //   854: aload 13
    //   856: ldc_w 333
    //   859: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   862: checkcast 79	java/lang/String
    //   865: invokevirtual 336	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   868: astore 14
    //   870: new 85	java/lang/StringBuilder
    //   873: dup
    //   874: ldc_w 338
    //   877: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   880: aload 14
    //   882: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   885: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   888: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   891: aload 14
    //   893: invokestatic 340	com/kochava/android/tracker/c:d	(Ljava/lang/String;)V
    //   896: aload 13
    //   898: ldc_w 342
    //   901: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   904: ldc_w 256
    //   907: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   910: ifeq +46 -> 956
    //   913: ldc_w 344
    //   916: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   919: getstatic 347	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   922: ldc_w 349
    //   925: iconst_0
    //   926: invokevirtual 355	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   929: invokestatic 358	com/kochava/android/tracker/c:a	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
    //   932: pop
    //   933: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   936: invokeinterface 362 1 0
    //   941: ldc -2
    //   943: ldc_w 364
    //   946: invokeinterface 370 3 0
    //   951: invokeinterface 373 1 0
    //   956: aload 13
    //   958: ldc_w 375
    //   961: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   964: checkcast 280	java/lang/Integer
    //   967: invokevirtual 295	java/lang/Integer:intValue	()I
    //   970: invokestatic 378	com/kochava/android/tracker/c:b	(I)I
    //   973: pop
    //   974: invokestatic 381	com/kochava/android/tracker/c:g	()I
    //   977: ifge +904 -> 1881
    //   980: new 85	java/lang/StringBuilder
    //   983: dup
    //   984: ldc_w 383
    //   987: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   990: invokestatic 381	com/kochava/android/tracker/c:g	()I
    //   993: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   996: ldc_w 388
    //   999: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1005: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1008: iconst_0
    //   1009: invokestatic 378	com/kochava/android/tracker/c:b	(I)I
    //   1012: pop
    //   1013: invokestatic 391	com/kochava/android/tracker/c:h	()Z
    //   1016: ifne +85 -> 1101
    //   1019: aload 13
    //   1021: ldc_w 393
    //   1024: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1027: ifnull +74 -> 1101
    //   1030: aload 13
    //   1032: ldc_w 393
    //   1035: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1038: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1041: ldc_w 280
    //   1044: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1047: ifeq +54 -> 1101
    //   1050: aload 13
    //   1052: ldc_w 393
    //   1055: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1058: checkcast 280	java/lang/Integer
    //   1061: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1064: istore_1
    //   1065: iload_1
    //   1066: bipush 60
    //   1068: if_icmpge +883 -> 1951
    //   1071: new 85	java/lang/StringBuilder
    //   1074: dup
    //   1075: ldc_w 395
    //   1078: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1081: iload_1
    //   1082: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1085: ldc_w 397
    //   1088: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1091: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1094: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1097: invokestatic 400	com/kochava/android/tracker/c:i	()Z
    //   1100: pop
    //   1101: aload 13
    //   1103: ldc 101
    //   1105: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1108: ifnull +89 -> 1197
    //   1111: aload 13
    //   1113: ldc 101
    //   1115: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1118: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1121: ldc_w 280
    //   1124: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1127: ifeq +70 -> 1197
    //   1130: aload 13
    //   1132: ldc 101
    //   1134: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1137: checkcast 280	java/lang/Integer
    //   1140: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1143: istore_1
    //   1144: iload_1
    //   1145: ifge +887 -> 2032
    //   1148: new 85	java/lang/StringBuilder
    //   1151: dup
    //   1152: ldc_w 402
    //   1155: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1158: iload_1
    //   1159: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1162: ldc_w 404
    //   1165: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1168: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1171: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1174: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   1177: invokeinterface 362 1 0
    //   1182: ldc 101
    //   1184: ldc2_w 102
    //   1187: invokeinterface 408 4 0
    //   1192: invokeinterface 373 1 0
    //   1197: aload 13
    //   1199: ldc_w 410
    //   1202: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1205: ifnull +73 -> 1278
    //   1208: aload 13
    //   1210: ldc_w 410
    //   1213: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1216: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1219: ldc_w 280
    //   1222: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1225: ifeq +53 -> 1278
    //   1228: aload 13
    //   1230: ldc_w 410
    //   1233: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1236: checkcast 280	java/lang/Integer
    //   1239: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1242: istore_1
    //   1243: iload_1
    //   1244: ifgt +902 -> 2146
    //   1247: new 85	java/lang/StringBuilder
    //   1250: dup
    //   1251: ldc_w 412
    //   1254: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1257: iload_1
    //   1258: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1261: ldc_w 414
    //   1264: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1267: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1270: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1273: iconst_1
    //   1274: invokestatic 416	com/kochava/android/tracker/c:d	(I)I
    //   1277: pop
    //   1278: aload 13
    //   1280: ldc_w 418
    //   1283: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1286: checkcast 280	java/lang/Integer
    //   1289: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1292: istore_2
    //   1293: iload_2
    //   1294: bipush 10
    //   1296: if_icmpge +925 -> 2221
    //   1299: new 85	java/lang/StringBuilder
    //   1302: dup
    //   1303: ldc_w 420
    //   1306: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1309: iload_2
    //   1310: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1313: ldc_w 422
    //   1316: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1319: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1322: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1325: bipush 10
    //   1327: istore_1
    //   1328: new 85	java/lang/StringBuilder
    //   1331: dup
    //   1332: ldc_w 424
    //   1335: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1338: iload_1
    //   1339: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1342: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1345: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1348: iload_1
    //   1349: putstatic 429	com/kochava/android/tracker/af:a	I
    //   1352: aload 13
    //   1354: ldc_w 431
    //   1357: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1360: checkcast 280	java/lang/Integer
    //   1363: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1366: istore_2
    //   1367: iload_2
    //   1368: iconst_3
    //   1369: if_icmpge +894 -> 2263
    //   1372: new 85	java/lang/StringBuilder
    //   1375: dup
    //   1376: ldc_w 433
    //   1379: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1382: iload_2
    //   1383: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1386: ldc_w 435
    //   1389: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1392: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1395: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1398: iconst_3
    //   1399: istore_1
    //   1400: new 85	java/lang/StringBuilder
    //   1403: dup
    //   1404: ldc_w 437
    //   1407: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1410: iload_1
    //   1411: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1414: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1417: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1420: iload_1
    //   1421: putstatic 439	com/kochava/android/tracker/af:c	I
    //   1424: aload 13
    //   1426: ldc_w 441
    //   1429: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1432: checkcast 280	java/lang/Integer
    //   1435: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1438: istore_2
    //   1439: iload_2
    //   1440: ifgt +863 -> 2303
    //   1443: new 85	java/lang/StringBuilder
    //   1446: dup
    //   1447: ldc_w 443
    //   1450: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1453: iload_2
    //   1454: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1457: ldc_w 445
    //   1460: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1463: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1466: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1469: iconst_1
    //   1470: istore_1
    //   1471: new 85	java/lang/StringBuilder
    //   1474: dup
    //   1475: ldc_w 447
    //   1478: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1481: iload_1
    //   1482: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1485: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1488: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1491: iload_1
    //   1492: putstatic 449	com/kochava/android/tracker/af:b	I
    //   1495: aload 12
    //   1497: ldc_w 451
    //   1500: invokevirtual 262	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   1503: astore 14
    //   1505: new 85	java/lang/StringBuilder
    //   1508: dup
    //   1509: ldc_w 453
    //   1512: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1515: aload 14
    //   1517: invokevirtual 454	org/json/JSONArray:toString	()Ljava/lang/String;
    //   1520: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1523: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1526: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1529: iconst_0
    //   1530: istore_1
    //   1531: iload_1
    //   1532: aload 14
    //   1534: invokevirtual 268	org/json/JSONArray:length	()I
    //   1537: if_icmpge +871 -> 2408
    //   1540: aload 14
    //   1542: iload_1
    //   1543: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   1546: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   1549: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1552: ldc_w 463
    //   1555: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1558: ifeq +787 -> 2345
    //   1561: ldc_w 465
    //   1564: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1567: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   1570: ldc_w 463
    //   1573: iconst_0
    //   1574: invokestatic 473	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1577: invokevirtual 477	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1580: pop
    //   1581: iload_1
    //   1582: iconst_1
    //   1583: iadd
    //   1584: istore_1
    //   1585: goto -54 -> 1531
    //   1588: astore 13
    //   1590: new 85	java/lang/StringBuilder
    //   1593: dup
    //   1594: ldc_w 479
    //   1597: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1600: aload 13
    //   1602: invokevirtual 480	org/json/JSONException:toString	()Ljava/lang/String;
    //   1605: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1608: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1611: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1614: goto -1048 -> 566
    //   1617: iload_2
    //   1618: iconst_1
    //   1619: iadd
    //   1620: istore_2
    //   1621: goto -1013 -> 608
    //   1624: astore 13
    //   1626: iconst_0
    //   1627: istore_1
    //   1628: goto -918 -> 710
    //   1631: astore 14
    //   1633: ldc_w 482
    //   1636: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1639: goto -851 -> 788
    //   1642: astore 14
    //   1644: ldc_w 484
    //   1647: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   1650: goto -817 -> 833
    //   1653: astore 12
    //   1655: aload 12
    //   1657: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1660: ldc -31
    //   1662: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1665: ifeq +1978 -> 3643
    //   1668: new 85	java/lang/StringBuilder
    //   1671: dup
    //   1672: ldc -19
    //   1674: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1677: aload 12
    //   1679: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1682: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1685: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   1688: aload 12
    //   1690: invokestatic 245	com/kochava/android/tracker/c:a	(Ljava/lang/Exception;)V
    //   1693: return
    //   1694: astore 12
    //   1696: new 85	java/lang/StringBuilder
    //   1699: dup
    //   1700: ldc_w 486
    //   1703: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1706: aload 12
    //   1708: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1711: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1714: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   1717: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   1720: invokeinterface 362 1 0
    //   1725: ldc 56
    //   1727: invokestatic 48	java/lang/System:currentTimeMillis	()J
    //   1730: invokeinterface 408 4 0
    //   1735: invokeinterface 373 1 0
    //   1740: ldc_w 488
    //   1743: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1746: iconst_0
    //   1747: istore_1
    //   1748: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   1751: ldc -2
    //   1753: ldc 70
    //   1755: invokeinterface 74 3 0
    //   1760: ldc_w 256
    //   1763: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1766: ifne +2188 -> 3954
    //   1769: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   1772: ldc_w 490
    //   1775: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1778: checkcast 470	java/lang/Boolean
    //   1781: invokevirtual 493	java/lang/Boolean:booleanValue	()Z
    //   1784: ifne +1937 -> 3721
    //   1787: iconst_1
    //   1788: istore_2
    //   1789: invokestatic 496	com/kochava/android/tracker/c:s	()Z
    //   1792: istore 11
    //   1794: iload_1
    //   1795: invokestatic 381	com/kochava/android/tracker/c:g	()I
    //   1798: if_icmpge +1965 -> 3763
    //   1801: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   1804: ldc_w 498
    //   1807: ldc_w 500
    //   1810: invokeinterface 74 3 0
    //   1815: ldc_w 500
    //   1818: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1821: ifne +1905 -> 3726
    //   1824: iconst_1
    //   1825: istore_3
    //   1826: invokestatic 503	com/kochava/android/tracker/c:t	()Ljava/lang/String;
    //   1829: ifnull +1902 -> 3731
    //   1832: iconst_1
    //   1833: istore 4
    //   1835: iload_3
    //   1836: ifeq +12 -> 1848
    //   1839: iload_2
    //   1840: ifne +1923 -> 3763
    //   1843: iload 11
    //   1845: ifne +1918 -> 3763
    //   1848: iload_3
    //   1849: ifeq +8 -> 1857
    //   1852: iload 4
    //   1854: ifne +1909 -> 3763
    //   1857: ldc2_w 504
    //   1860: invokestatic 301	java/lang/Thread:sleep	(J)V
    //   1863: iload_1
    //   1864: iconst_1
    //   1865: iadd
    //   1866: istore_1
    //   1867: goto -73 -> 1794
    //   1870: astore 14
    //   1872: ldc_w 482
    //   1875: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   1878: goto -1024 -> 854
    //   1881: invokestatic 381	com/kochava/android/tracker/c:g	()I
    //   1884: bipush 120
    //   1886: if_icmple +40 -> 1926
    //   1889: new 85	java/lang/StringBuilder
    //   1892: dup
    //   1893: ldc_w 507
    //   1896: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1899: invokestatic 381	com/kochava/android/tracker/c:g	()I
    //   1902: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1905: ldc_w 509
    //   1908: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1911: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1914: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1917: bipush 120
    //   1919: invokestatic 378	com/kochava/android/tracker/c:b	(I)I
    //   1922: pop
    //   1923: goto -910 -> 1013
    //   1926: new 85	java/lang/StringBuilder
    //   1929: dup
    //   1930: ldc_w 511
    //   1933: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1936: invokestatic 381	com/kochava/android/tracker/c:g	()I
    //   1939: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1942: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1945: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1948: goto -935 -> 1013
    //   1951: iload_1
    //   1952: ldc_w 512
    //   1955: if_icmple +39 -> 1994
    //   1958: new 85	java/lang/StringBuilder
    //   1961: dup
    //   1962: ldc_w 514
    //   1965: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1968: iload_1
    //   1969: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1972: ldc_w 516
    //   1975: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1978: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1981: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1984: ldc_w 517
    //   1987: invokestatic 519	com/kochava/android/tracker/c:c	(I)I
    //   1990: pop
    //   1991: goto -894 -> 1097
    //   1994: iload_1
    //   1995: sipush 1000
    //   1998: imul
    //   1999: invokestatic 519	com/kochava/android/tracker/c:c	(I)I
    //   2002: pop
    //   2003: new 85	java/lang/StringBuilder
    //   2006: dup
    //   2007: ldc_w 521
    //   2010: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2013: iload_1
    //   2014: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2017: ldc_w 523
    //   2020: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2023: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2026: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2029: goto -932 -> 1097
    //   2032: iload_1
    //   2033: ldc_w 512
    //   2036: if_icmple +55 -> 2091
    //   2039: new 85	java/lang/StringBuilder
    //   2042: dup
    //   2043: ldc_w 402
    //   2046: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2049: iload_1
    //   2050: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2053: ldc_w 525
    //   2056: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2059: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2062: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2065: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   2068: invokeinterface 362 1 0
    //   2073: ldc 101
    //   2075: ldc2_w 526
    //   2078: invokeinterface 408 4 0
    //   2083: invokeinterface 373 1 0
    //   2088: goto -891 -> 1197
    //   2091: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   2094: invokeinterface 362 1 0
    //   2099: ldc 101
    //   2101: iload_1
    //   2102: sipush 1000
    //   2105: imul
    //   2106: i2l
    //   2107: invokeinterface 408 4 0
    //   2112: invokeinterface 373 1 0
    //   2117: new 85	java/lang/StringBuilder
    //   2120: dup
    //   2121: ldc_w 529
    //   2124: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2127: iload_1
    //   2128: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2131: ldc_w 523
    //   2134: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2137: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2140: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2143: goto -946 -> 1197
    //   2146: iload_1
    //   2147: bipush 30
    //   2149: if_icmple +38 -> 2187
    //   2152: new 85	java/lang/StringBuilder
    //   2155: dup
    //   2156: ldc_w 412
    //   2159: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2162: iload_1
    //   2163: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2166: ldc_w 531
    //   2169: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2172: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2175: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2178: bipush 30
    //   2180: invokestatic 416	com/kochava/android/tracker/c:d	(I)I
    //   2183: pop
    //   2184: goto -906 -> 1278
    //   2187: new 85	java/lang/StringBuilder
    //   2190: dup
    //   2191: ldc_w 533
    //   2194: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2197: iload_1
    //   2198: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2201: ldc_w 523
    //   2204: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2207: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2210: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2213: iload_1
    //   2214: invokestatic 416	com/kochava/android/tracker/c:d	(I)I
    //   2217: pop
    //   2218: goto -940 -> 1278
    //   2221: iload_2
    //   2222: istore_1
    //   2223: iload_2
    //   2224: sipush 5000
    //   2227: if_icmple -899 -> 1328
    //   2230: new 85	java/lang/StringBuilder
    //   2233: dup
    //   2234: ldc_w 420
    //   2237: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2240: iload_2
    //   2241: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2244: ldc_w 422
    //   2247: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2250: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2253: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2256: sipush 5000
    //   2259: istore_1
    //   2260: goto -932 -> 1328
    //   2263: iload_2
    //   2264: istore_1
    //   2265: iload_2
    //   2266: bipush 60
    //   2268: if_icmple -868 -> 1400
    //   2271: new 85	java/lang/StringBuilder
    //   2274: dup
    //   2275: ldc_w 433
    //   2278: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2281: iload_2
    //   2282: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2285: ldc_w 435
    //   2288: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2291: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2294: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2297: bipush 60
    //   2299: istore_1
    //   2300: goto -900 -> 1400
    //   2303: iload_2
    //   2304: istore_1
    //   2305: iload_2
    //   2306: sipush 10080
    //   2309: if_icmple -838 -> 1471
    //   2312: new 85	java/lang/StringBuilder
    //   2315: dup
    //   2316: ldc_w 443
    //   2319: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2322: iload_2
    //   2323: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2326: ldc_w 445
    //   2329: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2332: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2335: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2338: sipush 10080
    //   2341: istore_1
    //   2342: goto -871 -> 1471
    //   2345: aload 14
    //   2347: iload_1
    //   2348: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   2351: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   2354: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2357: ldc_w 535
    //   2360: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2363: ifeq +511 -> 2874
    //   2366: ldc_w 537
    //   2369: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2372: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   2375: ldc_w 535
    //   2378: iconst_0
    //   2379: invokestatic 473	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   2382: invokevirtual 477	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2385: pop
    //   2386: goto -805 -> 1581
    //   2389: astore 14
    //   2391: ldc_w 539
    //   2394: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2397: getstatic 544	com/kochava/android/tracker/ae:b	Z
    //   2400: ifeq +8 -> 2408
    //   2403: aload 14
    //   2405: invokevirtual 547	java/lang/Exception:printStackTrace	()V
    //   2408: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   2411: ldc_w 549
    //   2414: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2417: checkcast 470	java/lang/Boolean
    //   2420: invokevirtual 493	java/lang/Boolean:booleanValue	()Z
    //   2423: istore 11
    //   2425: iload 11
    //   2427: ifeq +740 -> 3167
    //   2430: getstatic 347	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   2433: ldc_w 551
    //   2436: invokevirtual 554	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2439: checkcast 556	android/telephony/TelephonyManager
    //   2442: invokevirtual 559	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   2445: invokestatic 561	com/kochava/android/tracker/c:e	(Ljava/lang/String;)Ljava/lang/String;
    //   2448: pop
    //   2449: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   2452: ldc_w 563
    //   2455: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2458: checkcast 470	java/lang/Boolean
    //   2461: invokevirtual 493	java/lang/Boolean:booleanValue	()Z
    //   2464: istore 11
    //   2466: iload 11
    //   2468: ifeq +737 -> 3205
    //   2471: getstatic 347	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   2474: ldc_w 565
    //   2477: invokevirtual 554	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2480: checkcast 567	android/net/wifi/WifiManager
    //   2483: invokevirtual 571	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   2486: invokevirtual 576	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   2489: invokestatic 578	com/kochava/android/tracker/c:f	(Ljava/lang/String;)Ljava/lang/String;
    //   2492: pop
    //   2493: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   2496: ldc_w 580
    //   2499: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2502: checkcast 470	java/lang/Boolean
    //   2505: invokevirtual 493	java/lang/Boolean:booleanValue	()Z
    //   2508: istore 11
    //   2510: iload 11
    //   2512: ifeq +748 -> 3260
    //   2515: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   2518: ldc -2
    //   2520: ldc 70
    //   2522: invokeinterface 74 3 0
    //   2527: ldc_w 256
    //   2530: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2533: ifne +144 -> 2677
    //   2536: getstatic 347	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   2539: invokevirtual 584	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2542: astore 15
    //   2544: aload 15
    //   2546: sipush 128
    //   2549: invokevirtual 590	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2552: invokeinterface 596 1 0
    //   2557: astore 16
    //   2559: aload 16
    //   2561: invokeinterface 601 1 0
    //   2566: ifeq +111 -> 2677
    //   2569: aload 16
    //   2571: invokeinterface 605 1 0
    //   2576: checkcast 607	android/content/pm/ApplicationInfo
    //   2579: astore 17
    //   2581: aload 17
    //   2583: invokestatic 610	com/kochava/android/tracker/c:a	(Landroid/content/pm/ApplicationInfo;)Z
    //   2586: istore 11
    //   2588: iload 11
    //   2590: ifne -31 -> 2559
    //   2593: aload 15
    //   2595: aload 17
    //   2597: getfield 614	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2600: iconst_0
    //   2601: invokevirtual 618	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2604: astore 14
    //   2606: aload 14
    //   2608: ifnull -49 -> 2559
    //   2611: invokestatic 622	com/kochava/android/tracker/c:k	()Ljava/util/List;
    //   2614: new 624	com/kochava/android/tracker/aa
    //   2617: dup
    //   2618: aload_0
    //   2619: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   2622: aload 14
    //   2624: getfield 627	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2627: new 85	java/lang/StringBuilder
    //   2630: dup
    //   2631: invokespecial 628	java/lang/StringBuilder:<init>	()V
    //   2634: aload 14
    //   2636: getfield 632	android/content/pm/PackageInfo:firstInstallTime	J
    //   2639: invokevirtual 635	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2642: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2645: new 85	java/lang/StringBuilder
    //   2648: dup
    //   2649: invokespecial 628	java/lang/StringBuilder:<init>	()V
    //   2652: aload 14
    //   2654: getfield 638	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2657: invokevirtual 635	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2660: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2663: invokespecial 641	com/kochava/android/tracker/aa:<init>	(Lcom/kochava/android/tracker/c;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2666: invokeinterface 644 2 0
    //   2671: pop
    //   2672: goto -113 -> 2559
    //   2675: astore 14
    //   2677: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   2680: ldc_w 646
    //   2683: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2686: checkcast 470	java/lang/Boolean
    //   2689: invokevirtual 493	java/lang/Boolean:booleanValue	()Z
    //   2692: istore 11
    //   2694: iload 11
    //   2696: ifeq +602 -> 3298
    //   2699: getstatic 347	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   2702: ldc_w 648
    //   2705: invokevirtual 554	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2708: checkcast 650	android/view/WindowManager
    //   2711: astore 14
    //   2713: aload_0
    //   2714: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   2717: aload 14
    //   2719: invokeinterface 654 1 0
    //   2724: invokevirtual 659	android/view/Display:getHeight	()I
    //   2727: invokestatic 662	com/kochava/android/tracker/c:a	(Lcom/kochava/android/tracker/c;I)I
    //   2730: pop
    //   2731: aload_0
    //   2732: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   2735: aload 14
    //   2737: invokeinterface 654 1 0
    //   2742: invokevirtual 665	android/view/Display:getWidth	()I
    //   2745: invokestatic 667	com/kochava/android/tracker/c:b	(Lcom/kochava/android/tracker/c;I)I
    //   2748: pop
    //   2749: new 85	java/lang/StringBuilder
    //   2752: dup
    //   2753: ldc_w 669
    //   2756: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2759: aload_0
    //   2760: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   2763: invokestatic 672	com/kochava/android/tracker/c:b	(Lcom/kochava/android/tracker/c;)I
    //   2766: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2769: ldc_w 674
    //   2772: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2775: aload_0
    //   2776: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   2779: invokestatic 676	com/kochava/android/tracker/c:c	(Lcom/kochava/android/tracker/c;)I
    //   2782: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2785: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2788: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2791: aload 12
    //   2793: ldc_w 678
    //   2796: invokevirtual 262	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   2799: astore 14
    //   2801: new 85	java/lang/StringBuilder
    //   2804: dup
    //   2805: ldc_w 680
    //   2808: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2811: aload 14
    //   2813: invokevirtual 454	org/json/JSONArray:toString	()Ljava/lang/String;
    //   2816: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2819: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2822: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2825: iconst_0
    //   2826: istore_1
    //   2827: iload_1
    //   2828: aload 14
    //   2830: invokevirtual 268	org/json/JSONArray:length	()I
    //   2833: if_icmpge +534 -> 3367
    //   2836: aload 14
    //   2838: iload_1
    //   2839: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   2842: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   2845: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2848: ldc_w 682
    //   2851: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2854: ifeq +453 -> 3307
    //   2857: ldc_w 684
    //   2860: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2863: invokestatic 687	com/kochava/android/tracker/c:l	()Z
    //   2866: pop
    //   2867: iload_1
    //   2868: iconst_1
    //   2869: iadd
    //   2870: istore_1
    //   2871: goto -44 -> 2827
    //   2874: aload 14
    //   2876: iload_1
    //   2877: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   2880: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   2883: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2886: ldc_w 490
    //   2889: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2892: ifeq +26 -> 2918
    //   2895: ldc_w 689
    //   2898: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2901: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   2904: ldc_w 490
    //   2907: iconst_0
    //   2908: invokestatic 473	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   2911: invokevirtual 477	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2914: pop
    //   2915: goto -1334 -> 1581
    //   2918: aload 14
    //   2920: iload_1
    //   2921: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   2924: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   2927: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2930: ldc_w 563
    //   2933: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2936: ifeq +26 -> 2962
    //   2939: ldc_w 691
    //   2942: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2945: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   2948: ldc_w 563
    //   2951: iconst_0
    //   2952: invokestatic 473	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   2955: invokevirtual 477	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2958: pop
    //   2959: goto -1378 -> 1581
    //   2962: aload 14
    //   2964: iload_1
    //   2965: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   2968: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   2971: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2974: ldc_w 549
    //   2977: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2980: ifeq +26 -> 3006
    //   2983: ldc_w 693
    //   2986: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2989: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   2992: ldc_w 549
    //   2995: iconst_0
    //   2996: invokestatic 473	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   2999: invokevirtual 477	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3002: pop
    //   3003: goto -1422 -> 1581
    //   3006: aload 14
    //   3008: iload_1
    //   3009: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3012: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   3015: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3018: ldc_w 646
    //   3021: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3024: ifeq +26 -> 3050
    //   3027: ldc_w 695
    //   3030: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3033: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   3036: ldc_w 646
    //   3039: iconst_0
    //   3040: invokestatic 473	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   3043: invokevirtual 477	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3046: pop
    //   3047: goto -1466 -> 1581
    //   3050: aload 14
    //   3052: iload_1
    //   3053: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3056: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   3059: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3062: ldc_w 580
    //   3065: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3068: ifeq +26 -> 3094
    //   3071: ldc_w 697
    //   3074: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3077: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   3080: ldc_w 580
    //   3083: iconst_0
    //   3084: invokestatic 473	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   3087: invokevirtual 477	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3090: pop
    //   3091: goto -1510 -> 1581
    //   3094: aload 14
    //   3096: iload_1
    //   3097: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3100: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   3103: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3106: ldc_w 699
    //   3109: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3112: ifeq -1531 -> 1581
    //   3115: ldc_w 701
    //   3118: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3121: invokestatic 468	com/kochava/android/tracker/c:j	()Ljava/util/HashMap;
    //   3124: ldc_w 699
    //   3127: iconst_0
    //   3128: invokestatic 473	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   3131: invokevirtual 477	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3134: pop
    //   3135: goto -1554 -> 1581
    //   3138: astore 14
    //   3140: new 85	java/lang/StringBuilder
    //   3143: dup
    //   3144: ldc_w 703
    //   3147: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3150: aload 14
    //   3152: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   3155: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3158: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3161: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3164: goto -715 -> 2449
    //   3167: ldc_w 705
    //   3170: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3173: goto -724 -> 2449
    //   3176: astore 14
    //   3178: new 85	java/lang/StringBuilder
    //   3181: dup
    //   3182: ldc_w 707
    //   3185: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3188: aload 14
    //   3190: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   3193: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3196: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3199: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3202: goto -709 -> 2493
    //   3205: ldc_w 709
    //   3208: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3211: goto -718 -> 2493
    //   3214: astore 14
    //   3216: new 85	java/lang/StringBuilder
    //   3219: dup
    //   3220: ldc_w 711
    //   3223: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3226: aload 17
    //   3228: getfield 614	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3231: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3234: ldc_w 713
    //   3237: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3240: aload 14
    //   3242: invokevirtual 714	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3245: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3248: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3251: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3254: aconst_null
    //   3255: astore 14
    //   3257: goto -651 -> 2606
    //   3260: ldc_w 716
    //   3263: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3266: goto -589 -> 2677
    //   3269: astore 14
    //   3271: new 85	java/lang/StringBuilder
    //   3274: dup
    //   3275: ldc_w 718
    //   3278: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3281: aload 14
    //   3283: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   3286: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3289: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3292: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3295: goto -504 -> 2791
    //   3298: ldc_w 720
    //   3301: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3304: goto -513 -> 2791
    //   3307: aload 14
    //   3309: iload_1
    //   3310: invokevirtual 457	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3313: invokevirtual 458	java/lang/Object:toString	()Ljava/lang/String;
    //   3316: invokevirtual 461	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3319: ldc_w 722
    //   3322: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3325: ifeq -458 -> 2867
    //   3328: ldc_w 724
    //   3331: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3334: invokestatic 727	com/kochava/android/tracker/c:m	()Z
    //   3337: pop
    //   3338: goto -471 -> 2867
    //   3341: astore 14
    //   3343: new 85	java/lang/StringBuilder
    //   3346: dup
    //   3347: ldc_w 729
    //   3350: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3353: aload 14
    //   3355: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   3358: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3361: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3364: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3367: aload 12
    //   3369: ldc_w 731
    //   3372: invokevirtual 309	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   3375: ldc_w 733
    //   3378: invokevirtual 309	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   3381: invokestatic 736	com/kochava/android/tracker/c:a	(Lorg/json/JSONObject;)V
    //   3384: invokestatic 739	com/kochava/android/tracker/c:n	()Z
    //   3387: ifeq +18 -> 3405
    //   3390: invokestatic 742	com/kochava/android/tracker/c:o	()Z
    //   3393: ifeq +12 -> 3405
    //   3396: getstatic 347	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   3399: invokestatic 745	com/kochava/android/tracker/af:a	(Landroid/content/Context;)Lcom/kochava/android/tracker/af;
    //   3402: invokevirtual 747	com/kochava/android/tracker/af:a	()V
    //   3405: aload 12
    //   3407: ldc_w 749
    //   3410: invokevirtual 262	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   3413: invokestatic 752	com/kochava/android/tracker/c:a	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
    //   3416: pop
    //   3417: new 85	java/lang/StringBuilder
    //   3420: dup
    //   3421: ldc_w 754
    //   3424: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3427: invokestatic 758	com/kochava/android/tracker/c:p	()Lorg/json/JSONArray;
    //   3430: invokevirtual 454	org/json/JSONArray:toString	()Ljava/lang/String;
    //   3433: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3436: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3439: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3442: invokestatic 54	com/kochava/android/tracker/c:c	()Landroid/content/SharedPreferences;
    //   3445: invokeinterface 362 1 0
    //   3450: ldc_w 760
    //   3453: invokestatic 758	com/kochava/android/tracker/c:p	()Lorg/json/JSONArray;
    //   3456: invokevirtual 454	org/json/JSONArray:toString	()Ljava/lang/String;
    //   3459: invokeinterface 370 3 0
    //   3464: invokeinterface 373 1 0
    //   3469: aload 13
    //   3471: ldc_w 762
    //   3474: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3477: ifnull +87 -> 3564
    //   3480: aload 13
    //   3482: ldc_w 762
    //   3485: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3488: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
    //   3491: ldc_w 470
    //   3494: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   3497: ifeq +136 -> 3633
    //   3500: aload 13
    //   3502: ldc_w 762
    //   3505: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3508: checkcast 470	java/lang/Boolean
    //   3511: invokevirtual 493	java/lang/Boolean:booleanValue	()Z
    //   3514: ifeq +119 -> 3633
    //   3517: iconst_1
    //   3518: istore_1
    //   3519: aload 13
    //   3521: ldc_w 762
    //   3524: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3527: invokevirtual 229	java/lang/Object:getClass	()Ljava/lang/Class;
    //   3530: ldc 79
    //   3532: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   3535: ifeq +103 -> 3638
    //   3538: ldc_w 256
    //   3541: aload 13
    //   3543: ldc_w 762
    //   3546: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3549: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3552: ifeq +86 -> 3638
    //   3555: iconst_1
    //   3556: istore_2
    //   3557: goto +541 -> 4098
    //   3560: invokestatic 765	com/kochava/android/tracker/c:q	()Z
    //   3563: pop
    //   3564: aload 12
    //   3566: ldc_w 767
    //   3569: invokevirtual 315	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   3572: astore 12
    //   3574: new 85	java/lang/StringBuilder
    //   3577: dup
    //   3578: ldc_w 769
    //   3581: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3584: aload 12
    //   3586: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3589: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3592: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3595: aload 12
    //   3597: ldc_w 771
    //   3600: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3603: ifeq -1886 -> 1717
    //   3606: invokestatic 774	com/kochava/android/tracker/c:r	()Z
    //   3609: pop
    //   3610: return
    //   3611: astore 12
    //   3613: ldc_w 776
    //   3616: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3619: goto -1902 -> 1717
    //   3622: astore 14
    //   3624: ldc_w 778
    //   3627: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3630: goto -161 -> 3469
    //   3633: iconst_0
    //   3634: istore_1
    //   3635: goto -116 -> 3519
    //   3638: iconst_0
    //   3639: istore_2
    //   3640: goto +458 -> 4098
    //   3643: new 85	java/lang/StringBuilder
    //   3646: dup
    //   3647: ldc_w 780
    //   3650: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3653: aload 12
    //   3655: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   3658: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3661: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3664: return
    //   3665: new 85	java/lang/StringBuilder
    //   3668: dup
    //   3669: ldc_w 780
    //   3672: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3675: aload 12
    //   3677: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   3680: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3683: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3686: return
    //   3687: new 85	java/lang/StringBuilder
    //   3690: dup
    //   3691: ldc_w 782
    //   3694: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3697: lload 5
    //   3699: ldc2_w 504
    //   3702: ldiv
    //   3703: invokevirtual 635	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   3706: ldc_w 784
    //   3709: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3712: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3715: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3718: goto -1978 -> 1740
    //   3721: iconst_0
    //   3722: istore_2
    //   3723: goto -1934 -> 1789
    //   3726: iconst_0
    //   3727: istore_3
    //   3728: goto -1902 -> 1826
    //   3731: iconst_0
    //   3732: istore 4
    //   3734: goto -1899 -> 1835
    //   3737: astore 12
    //   3739: new 85	java/lang/StringBuilder
    //   3742: dup
    //   3743: ldc_w 786
    //   3746: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3749: aload 12
    //   3751: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   3754: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3757: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3760: goto -1897 -> 1863
    //   3763: new 85	java/lang/StringBuilder
    //   3766: dup
    //   3767: ldc_w 788
    //   3770: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3773: iload_1
    //   3774: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   3777: ldc_w 523
    //   3780: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3783: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3786: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3789: iload_1
    //   3790: istore_2
    //   3791: iload_1
    //   3792: iconst_2
    //   3793: if_icmpge +96 -> 3889
    //   3796: aload_0
    //   3797: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   3800: invokestatic 790	com/kochava/android/tracker/c:d	(Lcom/kochava/android/tracker/c;)Ljava/lang/String;
    //   3803: ifnull +49 -> 3852
    //   3806: iconst_1
    //   3807: istore_3
    //   3808: invokestatic 503	com/kochava/android/tracker/c:t	()Ljava/lang/String;
    //   3811: ifnull +46 -> 3857
    //   3814: invokestatic 503	com/kochava/android/tracker/c:t	()Ljava/lang/String;
    //   3817: invokevirtual 122	java/lang/String:isEmpty	()Z
    //   3820: ifne +37 -> 3857
    //   3823: iconst_1
    //   3824: istore 4
    //   3826: iload_1
    //   3827: istore_2
    //   3828: iload 4
    //   3830: ifne +59 -> 3889
    //   3833: iload_1
    //   3834: istore_2
    //   3835: iload_3
    //   3836: ifne +53 -> 3889
    //   3839: ldc2_w 504
    //   3842: invokestatic 301	java/lang/Thread:sleep	(J)V
    //   3845: iload_1
    //   3846: iconst_1
    //   3847: iadd
    //   3848: istore_1
    //   3849: goto -60 -> 3789
    //   3852: iconst_0
    //   3853: istore_3
    //   3854: goto -46 -> 3808
    //   3857: iconst_0
    //   3858: istore 4
    //   3860: goto -34 -> 3826
    //   3863: astore 12
    //   3865: new 85	java/lang/StringBuilder
    //   3868: dup
    //   3869: ldc_w 786
    //   3872: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3875: aload 12
    //   3877: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   3880: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3883: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3886: goto -41 -> 3845
    //   3889: iload_2
    //   3890: iconst_2
    //   3891: if_icmpge +69 -> 3960
    //   3894: aload_0
    //   3895: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   3898: invokestatic 792	com/kochava/android/tracker/c:e	(Lcom/kochava/android/tracker/c;)Ljava/lang/String;
    //   3901: ifnull +22 -> 3923
    //   3904: iconst_1
    //   3905: istore_1
    //   3906: iload_1
    //   3907: ifne +53 -> 3960
    //   3910: ldc2_w 504
    //   3913: invokestatic 301	java/lang/Thread:sleep	(J)V
    //   3916: iload_2
    //   3917: iconst_1
    //   3918: iadd
    //   3919: istore_2
    //   3920: goto -31 -> 3889
    //   3923: iconst_0
    //   3924: istore_1
    //   3925: goto -19 -> 3906
    //   3928: astore 12
    //   3930: new 85	java/lang/StringBuilder
    //   3933: dup
    //   3934: ldc_w 786
    //   3937: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3940: aload 12
    //   3942: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   3945: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3948: invokestatic 242	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3951: goto -35 -> 3916
    //   3954: ldc_w 794
    //   3957: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3960: invokestatic 800	android/os/Message:obtain	()Landroid/os/Message;
    //   3963: astore 12
    //   3965: new 802	android/os/Bundle
    //   3968: dup
    //   3969: invokespecial 803	android/os/Bundle:<init>	()V
    //   3972: astore 13
    //   3974: aload 13
    //   3976: ldc_w 805
    //   3979: invokestatic 808	com/kochava/android/tracker/c:u	()Z
    //   3982: invokevirtual 812	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   3985: aload 12
    //   3987: aload 13
    //   3989: invokevirtual 816	android/os/Message:setData	(Landroid/os/Bundle;)V
    //   3992: aload_0
    //   3993: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   3996: invokestatic 819	com/kochava/android/tracker/c:f	(Lcom/kochava/android/tracker/c;)Landroid/os/Handler;
    //   3999: ifnull -3504 -> 495
    //   4002: ldc_w 821
    //   4005: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   4008: aload_0
    //   4009: getfield 12	com/kochava/android/tracker/v:a	Lcom/kochava/android/tracker/c;
    //   4012: invokestatic 819	com/kochava/android/tracker/c:f	(Lcom/kochava/android/tracker/c;)Landroid/os/Handler;
    //   4015: aload 12
    //   4017: invokevirtual 827	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   4020: pop
    //   4021: return
    //   4022: astore 13
    //   4024: goto -460 -> 3564
    //   4027: astore 14
    //   4029: goto -560 -> 3469
    //   4032: astore 14
    //   4034: goto -650 -> 3384
    //   4037: astore 14
    //   4039: goto -2544 -> 1495
    //   4042: astore 14
    //   4044: goto -2620 -> 1424
    //   4047: astore 14
    //   4049: goto -2697 -> 1352
    //   4052: astore 14
    //   4054: goto -3098 -> 956
    //   4057: astore 14
    //   4059: goto -3163 -> 896
    //   4062: goto -3919 -> 143
    //   4065: iconst_0
    //   4066: istore_2
    //   4067: goto -3363 -> 704
    //   4070: goto -3360 -> 710
    //   4073: iconst_0
    //   4074: istore_1
    //   4075: goto -3967 -> 108
    //   4078: astore 14
    //   4080: goto -3067 -> 1013
    //   4083: astore 14
    //   4085: goto -2984 -> 1101
    //   4088: astore 14
    //   4090: goto -2893 -> 1197
    //   4093: astore 14
    //   4095: goto -2817 -> 1278
    //   4098: iload_1
    //   4099: ifne -539 -> 3560
    //   4102: iload_2
    //   4103: ifeq -539 -> 3564
    //   4106: goto -546 -> 3560
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	4109	0	this	v
    //   107	3992	1	i	int
    //   607	3496	2	j	int
    //   633	3221	3	k	int
    //   1833	2026	4	m	int
    //   9	3689	5	l1	long
    //   14	18	7	l2	long
    //   29	5	9	l3	long
    //   584	2111	11	bool	boolean
    //   139	1	12	localObject1	Object
    //   455	36	12	localIOException	java.io.IOException
    //   496	12	12	localException1	Exception
    //   564	932	12	localObject2	Object
    //   1653	36	12	localException2	Exception
    //   1694	1871	12	localException3	Exception
    //   3572	24	12	str	String
    //   3611	65	12	localException4	Exception
    //   3737	13	12	localInterruptedException1	InterruptedException
    //   3863	13	12	localInterruptedException2	InterruptedException
    //   3928	13	12	localInterruptedException3	InterruptedException
    //   3963	53	12	localMessage	android.os.Message
    //   262	1163	13	localObject3	Object
    //   1588	13	13	localJSONException1	org.json.JSONException
    //   1624	1918	13	localJSONException2	org.json.JSONException
    //   3972	16	13	localBundle	android.os.Bundle
    //   4022	1	13	localException5	Exception
    //   342	1199	14	localObject4	Object
    //   1631	1	14	localJSONException3	org.json.JSONException
    //   1642	1	14	localJSONException4	org.json.JSONException
    //   1870	476	14	localJSONException5	org.json.JSONException
    //   2389	15	14	localException6	Exception
    //   2604	49	14	localPackageInfo	android.content.pm.PackageInfo
    //   2675	1	14	localException7	Exception
    //   2711	384	14	localObject5	Object
    //   3138	13	14	localException8	Exception
    //   3176	13	14	localException9	Exception
    //   3214	27	14	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   3255	1	14	localObject6	Object
    //   3269	39	14	localException10	Exception
    //   3341	13	14	localException11	Exception
    //   3622	1	14	localException12	Exception
    //   4027	1	14	localJSONException6	org.json.JSONException
    //   4032	1	14	localException13	Exception
    //   4037	1	14	localException14	Exception
    //   4042	1	14	localException15	Exception
    //   4047	1	14	localException16	Exception
    //   4052	1	14	localException17	Exception
    //   4057	1	14	localException18	Exception
    //   4078	1	14	localException19	Exception
    //   4083	1	14	localException20	Exception
    //   4088	1	14	localException21	Exception
    //   4093	1	14	localException22	Exception
    //   381	2213	15	localObject7	Object
    //   2557	13	16	localIterator	java.util.Iterator
    //   2579	648	17	localApplicationInfo	android.content.pm.ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   143	189	455	java/io/IOException
    //   189	200	455	java/io/IOException
    //   200	432	455	java/io/IOException
    //   432	439	455	java/io/IOException
    //   444	452	455	java/io/IOException
    //   524	551	455	java/io/IOException
    //   551	562	455	java/io/IOException
    //   566	586	455	java/io/IOException
    //   596	606	455	java/io/IOException
    //   608	632	455	java/io/IOException
    //   645	700	455	java/io/IOException
    //   719	743	455	java/io/IOException
    //   746	756	455	java/io/IOException
    //   760	784	455	java/io/IOException
    //   793	833	455	java/io/IOException
    //   833	854	455	java/io/IOException
    //   854	896	455	java/io/IOException
    //   896	956	455	java/io/IOException
    //   956	1013	455	java/io/IOException
    //   1013	1065	455	java/io/IOException
    //   1071	1097	455	java/io/IOException
    //   1097	1101	455	java/io/IOException
    //   1101	1144	455	java/io/IOException
    //   1148	1197	455	java/io/IOException
    //   1197	1243	455	java/io/IOException
    //   1247	1278	455	java/io/IOException
    //   1278	1293	455	java/io/IOException
    //   1299	1325	455	java/io/IOException
    //   1328	1352	455	java/io/IOException
    //   1352	1367	455	java/io/IOException
    //   1372	1398	455	java/io/IOException
    //   1400	1424	455	java/io/IOException
    //   1424	1439	455	java/io/IOException
    //   1443	1469	455	java/io/IOException
    //   1471	1495	455	java/io/IOException
    //   1495	1529	455	java/io/IOException
    //   1531	1581	455	java/io/IOException
    //   1590	1614	455	java/io/IOException
    //   1633	1639	455	java/io/IOException
    //   1644	1650	455	java/io/IOException
    //   1655	1693	455	java/io/IOException
    //   1872	1878	455	java/io/IOException
    //   1881	1923	455	java/io/IOException
    //   1926	1948	455	java/io/IOException
    //   1958	1991	455	java/io/IOException
    //   1994	2029	455	java/io/IOException
    //   2039	2088	455	java/io/IOException
    //   2091	2143	455	java/io/IOException
    //   2152	2184	455	java/io/IOException
    //   2187	2218	455	java/io/IOException
    //   2230	2256	455	java/io/IOException
    //   2271	2297	455	java/io/IOException
    //   2312	2338	455	java/io/IOException
    //   2345	2386	455	java/io/IOException
    //   2391	2408	455	java/io/IOException
    //   2408	2425	455	java/io/IOException
    //   2430	2449	455	java/io/IOException
    //   2449	2466	455	java/io/IOException
    //   2471	2493	455	java/io/IOException
    //   2493	2510	455	java/io/IOException
    //   2515	2559	455	java/io/IOException
    //   2559	2588	455	java/io/IOException
    //   2593	2606	455	java/io/IOException
    //   2611	2672	455	java/io/IOException
    //   2677	2694	455	java/io/IOException
    //   2699	2791	455	java/io/IOException
    //   2791	2825	455	java/io/IOException
    //   2827	2867	455	java/io/IOException
    //   2874	2915	455	java/io/IOException
    //   2918	2959	455	java/io/IOException
    //   2962	3003	455	java/io/IOException
    //   3006	3047	455	java/io/IOException
    //   3050	3091	455	java/io/IOException
    //   3094	3135	455	java/io/IOException
    //   3140	3164	455	java/io/IOException
    //   3167	3173	455	java/io/IOException
    //   3178	3202	455	java/io/IOException
    //   3205	3211	455	java/io/IOException
    //   3216	3254	455	java/io/IOException
    //   3260	3266	455	java/io/IOException
    //   3271	3295	455	java/io/IOException
    //   3298	3304	455	java/io/IOException
    //   3307	3338	455	java/io/IOException
    //   3343	3367	455	java/io/IOException
    //   3367	3384	455	java/io/IOException
    //   3384	3405	455	java/io/IOException
    //   3405	3469	455	java/io/IOException
    //   3469	3517	455	java/io/IOException
    //   3519	3555	455	java/io/IOException
    //   3560	3564	455	java/io/IOException
    //   3564	3610	455	java/io/IOException
    //   3613	3619	455	java/io/IOException
    //   3624	3630	455	java/io/IOException
    //   3643	3664	455	java/io/IOException
    //   11	31	496	java/lang/Exception
    //   551	562	1588	org/json/JSONException
    //   596	606	1624	org/json/JSONException
    //   608	632	1624	org/json/JSONException
    //   645	700	1624	org/json/JSONException
    //   746	756	1631	org/json/JSONException
    //   760	784	1631	org/json/JSONException
    //   793	833	1642	org/json/JSONException
    //   719	743	1653	java/lang/Exception
    //   746	756	1653	java/lang/Exception
    //   760	784	1653	java/lang/Exception
    //   793	833	1653	java/lang/Exception
    //   833	854	1653	java/lang/Exception
    //   1633	1639	1653	java/lang/Exception
    //   1644	1650	1653	java/lang/Exception
    //   1872	1878	1653	java/lang/Exception
    //   2391	2408	1653	java/lang/Exception
    //   2408	2425	1653	java/lang/Exception
    //   2449	2466	1653	java/lang/Exception
    //   2493	2510	1653	java/lang/Exception
    //   2677	2694	1653	java/lang/Exception
    //   3140	3164	1653	java/lang/Exception
    //   3167	3173	1653	java/lang/Exception
    //   3178	3202	1653	java/lang/Exception
    //   3205	3211	1653	java/lang/Exception
    //   3260	3266	1653	java/lang/Exception
    //   3271	3295	1653	java/lang/Exception
    //   3298	3304	1653	java/lang/Exception
    //   3343	3367	1653	java/lang/Exception
    //   3384	3405	1653	java/lang/Exception
    //   3613	3619	1653	java/lang/Exception
    //   3624	3630	1653	java/lang/Exception
    //   143	189	1694	java/lang/Exception
    //   189	200	1694	java/lang/Exception
    //   200	432	1694	java/lang/Exception
    //   432	439	1694	java/lang/Exception
    //   444	452	1694	java/lang/Exception
    //   524	551	1694	java/lang/Exception
    //   551	562	1694	java/lang/Exception
    //   566	586	1694	java/lang/Exception
    //   596	606	1694	java/lang/Exception
    //   608	632	1694	java/lang/Exception
    //   645	700	1694	java/lang/Exception
    //   1590	1614	1694	java/lang/Exception
    //   1655	1693	1694	java/lang/Exception
    //   3643	3664	1694	java/lang/Exception
    //   833	854	1870	org/json/JSONException
    //   1495	1529	2389	java/lang/Exception
    //   1531	1581	2389	java/lang/Exception
    //   2345	2386	2389	java/lang/Exception
    //   2874	2915	2389	java/lang/Exception
    //   2918	2959	2389	java/lang/Exception
    //   2962	3003	2389	java/lang/Exception
    //   3006	3047	2389	java/lang/Exception
    //   3050	3091	2389	java/lang/Exception
    //   3094	3135	2389	java/lang/Exception
    //   2515	2559	2675	java/lang/Exception
    //   2559	2588	2675	java/lang/Exception
    //   2593	2606	2675	java/lang/Exception
    //   2611	2672	2675	java/lang/Exception
    //   3216	3254	2675	java/lang/Exception
    //   2430	2449	3138	java/lang/Exception
    //   2471	2493	3176	java/lang/Exception
    //   2593	2606	3214	android/content/pm/PackageManager$NameNotFoundException
    //   2699	2791	3269	java/lang/Exception
    //   2791	2825	3341	java/lang/Exception
    //   2827	2867	3341	java/lang/Exception
    //   3307	3338	3341	java/lang/Exception
    //   3564	3610	3611	java/lang/Exception
    //   3405	3469	3622	java/lang/Exception
    //   1857	1863	3737	java/lang/InterruptedException
    //   3839	3845	3863	java/lang/InterruptedException
    //   3910	3916	3928	java/lang/InterruptedException
    //   3469	3517	4022	java/lang/Exception
    //   3519	3555	4022	java/lang/Exception
    //   3560	3564	4022	java/lang/Exception
    //   3405	3469	4027	org/json/JSONException
    //   3367	3384	4032	java/lang/Exception
    //   1424	1439	4037	java/lang/Exception
    //   1443	1469	4037	java/lang/Exception
    //   1471	1495	4037	java/lang/Exception
    //   2312	2338	4037	java/lang/Exception
    //   1352	1367	4042	java/lang/Exception
    //   1372	1398	4042	java/lang/Exception
    //   1400	1424	4042	java/lang/Exception
    //   2271	2297	4042	java/lang/Exception
    //   1278	1293	4047	java/lang/Exception
    //   1299	1325	4047	java/lang/Exception
    //   1328	1352	4047	java/lang/Exception
    //   2230	2256	4047	java/lang/Exception
    //   896	956	4052	java/lang/Exception
    //   854	896	4057	java/lang/Exception
    //   956	1013	4078	java/lang/Exception
    //   1881	1923	4078	java/lang/Exception
    //   1926	1948	4078	java/lang/Exception
    //   1013	1065	4083	java/lang/Exception
    //   1071	1097	4083	java/lang/Exception
    //   1097	1101	4083	java/lang/Exception
    //   1958	1991	4083	java/lang/Exception
    //   1994	2029	4083	java/lang/Exception
    //   1101	1144	4088	java/lang/Exception
    //   1148	1197	4088	java/lang/Exception
    //   2039	2088	4088	java/lang/Exception
    //   2091	2143	4088	java/lang/Exception
    //   1197	1243	4093	java/lang/Exception
    //   1247	1278	4093	java/lang/Exception
    //   2152	2184	4093	java/lang/Exception
    //   2187	2218	4093	java/lang/Exception
  }
}
