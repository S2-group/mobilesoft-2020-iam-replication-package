package com.kochava.android.tracker;

class w
  implements Runnable
{
  w(c paramC) {}
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  public void run()
  {
    // Byte code:
    //   0: ldc 32
    //   2: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   5: invokestatic 42	android/os/Looper:prepare	()V
    //   8: lconst_0
    //   9: lstore 5
    //   11: invokestatic 48	java/lang/System:currentTimeMillis	()J
    //   14: lstore 7
    //   16: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   19: ldc 56
    //   21: invokestatic 48	java/lang/System:currentTimeMillis	()J
    //   24: invokeinterface 62 4 0
    //   29: lstore 9
    //   31: lload 7
    //   33: lload 9
    //   35: lsub
    //   36: lstore 5
    //   38: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   41: ldc 64
    //   43: invokeinterface 68 2 0
    //   48: ifeq +4220 -> 4268
    //   51: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   54: ldc 64
    //   56: ldc 70
    //   58: invokeinterface 74 3 0
    //   63: aload_0
    //   64: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   67: invokestatic 77	com/kochava/android/tracker/c:b	(Lcom/kochava/android/tracker/c;)Ljava/lang/String;
    //   70: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   73: ifne +4195 -> 4268
    //   76: new 85	java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   83: ldc 88
    //   85: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload_0
    //   89: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   92: invokestatic 77	com/kochava/android/tracker/c:b	(Lcom/kochava/android/tracker/c;)Ljava/lang/String;
    //   95: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: ldc 94
    //   100: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   109: iconst_1
    //   110: istore_1
    //   111: iload_1
    //   112: ifne +29 -> 141
    //   115: lload 5
    //   117: lconst_0
    //   118: lcmp
    //   119: ifle +22 -> 141
    //   122: lload 5
    //   124: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   127: ldc 100
    //   129: ldc2_w 101
    //   132: invokeinterface 62 4 0
    //   137: lcmp
    //   138: ifle +3866 -> 4004
    //   141: aconst_null
    //   142: astore 12
    //   144: iconst_0
    //   145: istore_1
    //   146: new 85	java/lang/StringBuilder
    //   149: dup
    //   150: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   153: ldc 104
    //   155: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload_0
    //   159: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   162: getfield 108	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   165: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
    //   168: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   177: invokestatic 114	com/kochava/android/tracker/c:c	()Ljava/lang/String;
    //   180: ifnull +15 -> 195
    //   183: invokestatic 114	com/kochava/android/tracker/c:c	()Ljava/lang/String;
    //   186: invokevirtual 117	java/lang/String:trim	()Ljava/lang/String;
    //   189: invokevirtual 121	java/lang/String:isEmpty	()Z
    //   192: ifeq +14 -> 206
    //   195: ldc 123
    //   197: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   200: ldc 125
    //   202: invokestatic 128	com/kochava/android/tracker/c:c	(Ljava/lang/String;)Ljava/lang/String;
    //   205: pop
    //   206: new 85	java/lang/StringBuilder
    //   209: dup
    //   210: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   213: ldc -126
    //   215: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: ldc -124
    //   220: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: invokestatic 114	com/kochava/android/tracker/c:c	()Ljava/lang/String;
    //   226: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: ldc -122
    //   231: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   237: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   240: new 136	java/net/URL
    //   243: dup
    //   244: new 85	java/lang/StringBuilder
    //   247: dup
    //   248: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   251: ldc -124
    //   253: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: invokestatic 114	com/kochava/android/tracker/c:c	()Ljava/lang/String;
    //   259: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: ldc -122
    //   264: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   270: invokespecial 138	java/net/URL:<init>	(Ljava/lang/String;)V
    //   273: invokevirtual 142	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   276: checkcast 144	javax/net/ssl/HttpsURLConnection
    //   279: astore 13
    //   281: aload 13
    //   283: ldc -110
    //   285: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   288: ldc -108
    //   290: ldc 70
    //   292: invokeinterface 74 3 0
    //   297: invokevirtual 152	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   300: aload 13
    //   302: ldc -102
    //   304: ldc -100
    //   306: invokevirtual 152	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   309: aload 13
    //   311: ldc -98
    //   313: invokevirtual 161	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   316: aload 13
    //   318: sipush 30000
    //   321: invokevirtual 165	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
    //   324: aload 13
    //   326: sipush 30000
    //   329: invokevirtual 168	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
    //   332: aload 13
    //   334: iconst_1
    //   335: invokevirtual 172	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
    //   338: aload 13
    //   340: iconst_1
    //   341: invokevirtual 175	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
    //   344: aload 13
    //   346: invokevirtual 178	javax/net/ssl/HttpsURLConnection:connect	()V
    //   349: aload_0
    //   350: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   353: getfield 108	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   356: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
    //   359: astore 14
    //   361: new 85	java/lang/StringBuilder
    //   364: dup
    //   365: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   368: ldc -76
    //   370: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   373: aload 14
    //   375: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   381: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   384: ldc -74
    //   386: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   389: new 184	java/io/OutputStreamWriter
    //   392: dup
    //   393: aload 13
    //   395: invokevirtual 188	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   398: invokespecial 191	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   401: astore 15
    //   403: aload 15
    //   405: aload 14
    //   407: invokevirtual 194	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   410: aload 15
    //   412: invokevirtual 197	java/io/OutputStreamWriter:close	()V
    //   415: ldc -57
    //   417: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   420: new 201	java/lang/StringBuffer
    //   423: dup
    //   424: ldc 70
    //   426: invokespecial 202	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   429: astore 14
    //   431: new 204	java/io/BufferedReader
    //   434: dup
    //   435: new 206	java/io/InputStreamReader
    //   438: dup
    //   439: aload 13
    //   441: invokevirtual 210	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
    //   444: invokespecial 213	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   447: invokespecial 216	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   450: astore 13
    //   452: aload 13
    //   454: invokevirtual 219	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   457: astore 15
    //   459: aload 15
    //   461: ifnull +89 -> 550
    //   464: aload 14
    //   466: aload 15
    //   468: invokevirtual 222	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   471: pop
    //   472: goto -20 -> 452
    //   475: astore 12
    //   477: ldc -32
    //   479: aload 12
    //   481: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
    //   484: invokevirtual 234	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   487: ifeq +3492 -> 3979
    //   490: new 85	java/lang/StringBuilder
    //   493: dup
    //   494: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   497: ldc -20
    //   499: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: aload 12
    //   504: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   507: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   510: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   513: aload 12
    //   515: invokestatic 244	com/kochava/android/tracker/c:a	(Ljava/lang/Exception;)V
    //   518: return
    //   519: astore 12
    //   521: new 85	java/lang/StringBuilder
    //   524: dup
    //   525: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   528: ldc -10
    //   530: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   533: aload 12
    //   535: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
    //   538: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   544: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   547: goto -509 -> 38
    //   550: aload 14
    //   552: invokevirtual 248	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   555: astore 13
    //   557: new 85	java/lang/StringBuilder
    //   560: dup
    //   561: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   564: ldc -6
    //   566: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   569: aload 13
    //   571: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   574: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   577: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   580: new 110	org/json/JSONObject
    //   583: dup
    //   584: aload 13
    //   586: invokespecial 251	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   589: astore 13
    //   591: aload 13
    //   593: astore 12
    //   595: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   598: ldc -3
    //   600: ldc 70
    //   602: invokeinterface 74 3 0
    //   607: ldc -1
    //   609: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   612: istore 11
    //   614: iload 11
    //   616: ifne +3649 -> 4265
    //   619: aload 12
    //   621: ifnull +3644 -> 4265
    //   624: aload 12
    //   626: ldc_w 257
    //   629: invokevirtual 261	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   632: astore 13
    //   634: iconst_0
    //   635: istore_2
    //   636: iload_2
    //   637: aload 13
    //   639: invokevirtual 267	org/json/JSONArray:length	()I
    //   642: if_icmpge +3618 -> 4260
    //   645: ldc_w 269
    //   648: aload 13
    //   650: iload_2
    //   651: invokevirtual 272	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   654: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   657: ifeq +1046 -> 1703
    //   660: iconst_1
    //   661: istore_3
    //   662: iload_1
    //   663: istore_2
    //   664: iload_1
    //   665: iconst_4
    //   666: if_icmpge +7 -> 673
    //   669: iload_1
    //   670: iconst_1
    //   671: iadd
    //   672: istore_2
    //   673: new 85	java/lang/StringBuilder
    //   676: dup
    //   677: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   680: ldc_w 274
    //   683: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   686: invokestatic 278	com/kochava/android/tracker/c:d	()Ljava/util/HashMap;
    //   689: iload_2
    //   690: invokestatic 284	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   693: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   696: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   699: ldc_w 292
    //   702: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   705: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   708: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   711: invokestatic 278	com/kochava/android/tracker/c:d	()Ljava/util/HashMap;
    //   714: iload_2
    //   715: invokestatic 284	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   718: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   721: checkcast 280	java/lang/Integer
    //   724: invokevirtual 295	java/lang/Integer:intValue	()I
    //   727: i2l
    //   728: invokestatic 301	java/lang/Thread:sleep	(J)V
    //   731: iload_2
    //   732: istore_1
    //   733: iload_3
    //   734: istore_2
    //   735: iload_2
    //   736: ifne +5 -> 741
    //   739: iconst_0
    //   740: istore_1
    //   741: iload_1
    //   742: ifne +3515 -> 4257
    //   745: aload 12
    //   747: ifnull +1062 -> 1809
    //   750: new 85	java/lang/StringBuilder
    //   753: dup
    //   754: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   757: ldc_w 303
    //   760: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   763: aload 12
    //   765: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
    //   768: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   771: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   774: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   777: aconst_null
    //   778: astore 13
    //   780: aload 12
    //   782: ldc_w 305
    //   785: invokevirtual 309	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   788: astore 14
    //   790: aload 14
    //   792: astore 13
    //   794: new 85	java/lang/StringBuilder
    //   797: dup
    //   798: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   801: ldc_w 311
    //   804: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   807: aload 14
    //   809: invokevirtual 111	org/json/JSONObject:toString	()Ljava/lang/String;
    //   812: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   815: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   818: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   821: aload 14
    //   823: astore 13
    //   825: aload 13
    //   827: ifnull +748 -> 1575
    //   830: aload 13
    //   832: ldc_w 313
    //   835: invokevirtual 315	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   838: astore 14
    //   840: new 85	java/lang/StringBuilder
    //   843: dup
    //   844: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   847: ldc_w 317
    //   850: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   853: aload 14
    //   855: invokevirtual 318	java/lang/String:toString	()Ljava/lang/String;
    //   858: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   861: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   864: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   867: aload 14
    //   869: invokestatic 320	com/kochava/android/tracker/c:d	(Ljava/lang/String;)Ljava/lang/String;
    //   872: pop
    //   873: aload 13
    //   875: ldc_w 322
    //   878: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   881: ldc_w 327
    //   884: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   887: ifeq +8 -> 895
    //   890: iconst_0
    //   891: invokestatic 331	com/kochava/android/tracker/c:c	(Z)Z
    //   894: pop
    //   895: aload 13
    //   897: ldc_w 333
    //   900: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   903: checkcast 79	java/lang/String
    //   906: invokevirtual 336	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   909: astore 14
    //   911: new 85	java/lang/StringBuilder
    //   914: dup
    //   915: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   918: ldc_w 338
    //   921: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   924: aload 14
    //   926: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   929: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   932: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   935: aload_0
    //   936: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   939: aload 14
    //   941: invokestatic 341	com/kochava/android/tracker/c:c	(Lcom/kochava/android/tracker/c;Ljava/lang/String;)V
    //   944: aload 13
    //   946: ldc_w 343
    //   949: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   952: ldc -1
    //   954: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   957: ifeq +46 -> 1003
    //   960: ldc_w 345
    //   963: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   966: getstatic 348	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   969: ldc_w 350
    //   972: iconst_0
    //   973: invokevirtual 356	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   976: invokestatic 359	com/kochava/android/tracker/c:a	(Landroid/content/SharedPreferences;)Landroid/content/SharedPreferences;
    //   979: pop
    //   980: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   983: invokeinterface 363 1 0
    //   988: ldc -3
    //   990: ldc_w 365
    //   993: invokeinterface 371 3 0
    //   998: invokeinterface 374 1 0
    //   1003: aload 13
    //   1005: ldc_w 376
    //   1008: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1011: checkcast 280	java/lang/Integer
    //   1014: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1017: invokestatic 379	com/kochava/android/tracker/c:b	(I)I
    //   1020: pop
    //   1021: invokestatic 381	com/kochava/android/tracker/c:e	()I
    //   1024: ifge +1088 -> 2112
    //   1027: new 85	java/lang/StringBuilder
    //   1030: dup
    //   1031: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1034: ldc_w 383
    //   1037: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1040: invokestatic 381	com/kochava/android/tracker/c:e	()I
    //   1043: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1046: ldc_w 388
    //   1049: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1052: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1055: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1058: iconst_0
    //   1059: invokestatic 379	com/kochava/android/tracker/c:b	(I)I
    //   1062: pop
    //   1063: invokestatic 391	com/kochava/android/tracker/c:f	()Z
    //   1066: ifne +89 -> 1155
    //   1069: aload 13
    //   1071: ldc_w 393
    //   1074: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1077: ifnull +78 -> 1155
    //   1080: aload 13
    //   1082: ldc_w 393
    //   1085: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1088: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1091: ldc_w 280
    //   1094: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1097: ifeq +58 -> 1155
    //   1100: aload 13
    //   1102: ldc_w 393
    //   1105: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1108: checkcast 280	java/lang/Integer
    //   1111: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1114: istore_1
    //   1115: iload_1
    //   1116: bipush 60
    //   1118: if_icmpge +1070 -> 2188
    //   1121: new 85	java/lang/StringBuilder
    //   1124: dup
    //   1125: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1128: ldc_w 395
    //   1131: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1134: iload_1
    //   1135: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1138: ldc_w 397
    //   1141: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1144: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1147: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1150: iconst_1
    //   1151: invokestatic 399	com/kochava/android/tracker/c:d	(Z)Z
    //   1154: pop
    //   1155: aload 13
    //   1157: ldc 100
    //   1159: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1162: ifnull +92 -> 1254
    //   1165: aload 13
    //   1167: ldc 100
    //   1169: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1172: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1175: ldc_w 280
    //   1178: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1181: ifeq +73 -> 1254
    //   1184: aload 13
    //   1186: ldc 100
    //   1188: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1191: checkcast 280	java/lang/Integer
    //   1194: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1197: istore_1
    //   1198: iload_1
    //   1199: ifge +1076 -> 2275
    //   1202: new 85	java/lang/StringBuilder
    //   1205: dup
    //   1206: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1209: ldc_w 401
    //   1212: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1215: iload_1
    //   1216: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1219: ldc_w 403
    //   1222: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1225: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1228: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1231: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   1234: invokeinterface 363 1 0
    //   1239: ldc 100
    //   1241: ldc2_w 101
    //   1244: invokeinterface 407 4 0
    //   1249: invokeinterface 374 1 0
    //   1254: aload 13
    //   1256: ldc_w 409
    //   1259: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1262: ifnull +77 -> 1339
    //   1265: aload 13
    //   1267: ldc_w 409
    //   1270: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1273: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1276: ldc_w 280
    //   1279: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1282: ifeq +57 -> 1339
    //   1285: aload 13
    //   1287: ldc_w 409
    //   1290: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1293: checkcast 280	java/lang/Integer
    //   1296: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1299: istore_1
    //   1300: iload_1
    //   1301: iconst_1
    //   1302: if_icmpge +1093 -> 2395
    //   1305: new 85	java/lang/StringBuilder
    //   1308: dup
    //   1309: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1312: ldc_w 411
    //   1315: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1318: iload_1
    //   1319: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1322: ldc_w 413
    //   1325: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1328: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1331: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1334: iconst_1
    //   1335: invokestatic 415	com/kochava/android/tracker/c:d	(I)I
    //   1338: pop
    //   1339: aload 13
    //   1341: ldc_w 417
    //   1344: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1347: checkcast 280	java/lang/Integer
    //   1350: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1353: istore_2
    //   1354: iload_2
    //   1355: bipush 10
    //   1357: if_icmpge +1119 -> 2476
    //   1360: new 85	java/lang/StringBuilder
    //   1363: dup
    //   1364: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1367: ldc_w 419
    //   1370: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1373: iload_2
    //   1374: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1377: ldc_w 421
    //   1380: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1383: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1386: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1389: bipush 10
    //   1391: istore_1
    //   1392: new 85	java/lang/StringBuilder
    //   1395: dup
    //   1396: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1399: ldc_w 423
    //   1402: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1405: iload_1
    //   1406: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1409: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1412: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1415: iload_1
    //   1416: putstatic 428	com/kochava/android/tracker/ag:a	I
    //   1419: aload 13
    //   1421: ldc_w 430
    //   1424: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1427: checkcast 280	java/lang/Integer
    //   1430: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1433: istore_2
    //   1434: iload_2
    //   1435: iconst_3
    //   1436: if_icmpge +1085 -> 2521
    //   1439: new 85	java/lang/StringBuilder
    //   1442: dup
    //   1443: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1446: ldc_w 432
    //   1449: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1452: iload_2
    //   1453: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1456: ldc_w 434
    //   1459: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1462: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1465: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1468: iconst_3
    //   1469: istore_1
    //   1470: new 85	java/lang/StringBuilder
    //   1473: dup
    //   1474: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1477: ldc_w 436
    //   1480: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1483: iload_1
    //   1484: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1487: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1490: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1493: iload_1
    //   1494: putstatic 438	com/kochava/android/tracker/ag:c	I
    //   1497: aload 13
    //   1499: ldc_w 440
    //   1502: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   1505: checkcast 280	java/lang/Integer
    //   1508: invokevirtual 295	java/lang/Integer:intValue	()I
    //   1511: istore_2
    //   1512: iload_2
    //   1513: iconst_1
    //   1514: if_icmpge +1050 -> 2564
    //   1517: new 85	java/lang/StringBuilder
    //   1520: dup
    //   1521: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1524: ldc_w 442
    //   1527: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1530: iload_2
    //   1531: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1534: ldc_w 444
    //   1537: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1540: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1543: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1546: iconst_1
    //   1547: istore_1
    //   1548: new 85	java/lang/StringBuilder
    //   1551: dup
    //   1552: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1555: ldc_w 446
    //   1558: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1561: iload_1
    //   1562: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1565: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1568: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1571: iload_1
    //   1572: putstatic 448	com/kochava/android/tracker/ag:b	I
    //   1575: aload 12
    //   1577: ldc_w 450
    //   1580: invokevirtual 261	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   1583: astore 14
    //   1585: new 85	java/lang/StringBuilder
    //   1588: dup
    //   1589: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1592: ldc_w 452
    //   1595: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1598: aload 14
    //   1600: invokevirtual 453	org/json/JSONArray:toString	()Ljava/lang/String;
    //   1603: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1606: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1609: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1612: iconst_0
    //   1613: istore_1
    //   1614: iload_1
    //   1615: aload 14
    //   1617: invokevirtual 267	org/json/JSONArray:length	()I
    //   1620: if_icmpge +1052 -> 2672
    //   1623: aload 14
    //   1625: iload_1
    //   1626: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   1629: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   1632: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1635: ldc_w 462
    //   1638: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1641: ifeq +968 -> 2609
    //   1644: ldc_w 464
    //   1647: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1650: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   1653: ldc_w 462
    //   1656: iconst_0
    //   1657: invokestatic 472	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1660: invokevirtual 476	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1663: pop
    //   1664: iload_1
    //   1665: iconst_1
    //   1666: iadd
    //   1667: istore_1
    //   1668: goto -54 -> 1614
    //   1671: astore 13
    //   1673: new 85	java/lang/StringBuilder
    //   1676: dup
    //   1677: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1680: ldc_w 478
    //   1683: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1686: aload 13
    //   1688: invokevirtual 479	org/json/JSONException:toString	()Ljava/lang/String;
    //   1691: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1694: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1697: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1700: goto -1105 -> 595
    //   1703: iload_2
    //   1704: iconst_1
    //   1705: iadd
    //   1706: istore_2
    //   1707: goto -1071 -> 636
    //   1710: astore 13
    //   1712: iconst_0
    //   1713: istore_1
    //   1714: goto -973 -> 741
    //   1717: astore 14
    //   1719: ldc_w 481
    //   1722: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1725: goto -900 -> 825
    //   1728: astore 14
    //   1730: ldc_w 483
    //   1733: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   1736: goto -863 -> 873
    //   1739: astore 12
    //   1741: aload 12
    //   1743: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1746: ldc -32
    //   1748: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1751: ifeq +2203 -> 3954
    //   1754: new 85	java/lang/StringBuilder
    //   1757: dup
    //   1758: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1761: ldc -20
    //   1763: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1766: aload 12
    //   1768: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1771: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1774: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   1777: aload 12
    //   1779: invokestatic 244	com/kochava/android/tracker/c:a	(Ljava/lang/Exception;)V
    //   1782: return
    //   1783: astore 12
    //   1785: new 85	java/lang/StringBuilder
    //   1788: dup
    //   1789: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1792: ldc_w 485
    //   1795: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1798: aload 12
    //   1800: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1803: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1806: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   1809: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   1812: invokeinterface 363 1 0
    //   1817: ldc 56
    //   1819: invokestatic 48	java/lang/System:currentTimeMillis	()J
    //   1822: invokeinterface 407 4 0
    //   1827: invokeinterface 374 1 0
    //   1832: ldc_w 487
    //   1835: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1838: iconst_0
    //   1839: istore_1
    //   1840: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   1843: ldc -3
    //   1845: ldc 70
    //   1847: invokeinterface 74 3 0
    //   1852: ldc -1
    //   1854: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1857: ifne +2351 -> 4208
    //   1860: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   1863: ldc_w 489
    //   1866: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1869: checkcast 469	java/lang/Boolean
    //   1872: invokevirtual 492	java/lang/Boolean:booleanValue	()Z
    //   1875: ifne +2166 -> 4041
    //   1878: iconst_1
    //   1879: istore_2
    //   1880: invokestatic 495	com/kochava/android/tracker/c:l	()Z
    //   1883: istore 11
    //   1885: iload_1
    //   1886: invokestatic 381	com/kochava/android/tracker/c:e	()I
    //   1889: if_icmpge +50 -> 1939
    //   1892: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   1895: ldc_w 497
    //   1898: ldc_w 499
    //   1901: invokeinterface 74 3 0
    //   1906: ldc_w 499
    //   1909: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1912: ifne +2134 -> 4046
    //   1915: iconst_1
    //   1916: istore_3
    //   1917: invokestatic 502	com/kochava/android/tracker/c:m	()Ljava/lang/String;
    //   1920: ifnull +2131 -> 4051
    //   1923: iconst_1
    //   1924: istore 4
    //   1926: iload_3
    //   1927: ifeq +2130 -> 4057
    //   1930: iload_2
    //   1931: ifne +8 -> 1939
    //   1934: iload 11
    //   1936: ifeq +2121 -> 4057
    //   1939: new 85	java/lang/StringBuilder
    //   1942: dup
    //   1943: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   1946: ldc_w 504
    //   1949: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1952: iload_1
    //   1953: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1956: ldc_w 506
    //   1959: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1962: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1965: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1968: iload_1
    //   1969: istore_2
    //   1970: iload_1
    //   1971: iconst_2
    //   1972: if_icmpge +46 -> 2018
    //   1975: aload_0
    //   1976: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   1979: invokestatic 508	com/kochava/android/tracker/c:e	(Lcom/kochava/android/tracker/c;)Ljava/lang/String;
    //   1982: ifnull +2126 -> 4108
    //   1985: iconst_1
    //   1986: istore_3
    //   1987: invokestatic 502	com/kochava/android/tracker/c:m	()Ljava/lang/String;
    //   1990: ifnull +2123 -> 4113
    //   1993: invokestatic 502	com/kochava/android/tracker/c:m	()Ljava/lang/String;
    //   1996: invokevirtual 121	java/lang/String:isEmpty	()Z
    //   1999: ifne +2114 -> 4113
    //   2002: iconst_1
    //   2003: istore 4
    //   2005: iload_1
    //   2006: istore_2
    //   2007: iload 4
    //   2009: ifne +9 -> 2018
    //   2012: iload_3
    //   2013: ifeq +2106 -> 4119
    //   2016: iload_1
    //   2017: istore_2
    //   2018: iload_2
    //   2019: iconst_2
    //   2020: if_icmpge +19 -> 2039
    //   2023: aload_0
    //   2024: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   2027: invokestatic 510	com/kochava/android/tracker/c:f	(Lcom/kochava/android/tracker/c;)Ljava/lang/String;
    //   2030: ifnull +2131 -> 4161
    //   2033: iconst_1
    //   2034: istore_1
    //   2035: iload_1
    //   2036: ifeq +2130 -> 4166
    //   2039: invokestatic 516	android/os/Message:obtain	()Landroid/os/Message;
    //   2042: astore 12
    //   2044: new 518	android/os/Bundle
    //   2047: dup
    //   2048: invokespecial 519	android/os/Bundle:<init>	()V
    //   2051: astore 13
    //   2053: aload 13
    //   2055: ldc_w 521
    //   2058: invokestatic 524	com/kochava/android/tracker/c:n	()Z
    //   2061: invokevirtual 528	android/os/Bundle:putBoolean	(Ljava/lang/String;Z)V
    //   2064: aload 12
    //   2066: aload 13
    //   2068: invokevirtual 532	android/os/Message:setData	(Landroid/os/Bundle;)V
    //   2071: aload_0
    //   2072: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   2075: invokestatic 535	com/kochava/android/tracker/c:g	(Lcom/kochava/android/tracker/c;)Landroid/os/Handler;
    //   2078: ifnull -1560 -> 518
    //   2081: ldc_w 537
    //   2084: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2087: aload_0
    //   2088: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   2091: invokestatic 535	com/kochava/android/tracker/c:g	(Lcom/kochava/android/tracker/c;)Landroid/os/Handler;
    //   2094: aload 12
    //   2096: invokevirtual 543	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   2099: pop
    //   2100: return
    //   2101: astore 14
    //   2103: ldc_w 481
    //   2106: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2109: goto -1214 -> 895
    //   2112: invokestatic 381	com/kochava/android/tracker/c:e	()I
    //   2115: bipush 120
    //   2117: if_icmple +43 -> 2160
    //   2120: new 85	java/lang/StringBuilder
    //   2123: dup
    //   2124: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2127: ldc_w 545
    //   2130: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2133: invokestatic 381	com/kochava/android/tracker/c:e	()I
    //   2136: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2139: ldc_w 547
    //   2142: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2145: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2148: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2151: bipush 120
    //   2153: invokestatic 379	com/kochava/android/tracker/c:b	(I)I
    //   2156: pop
    //   2157: goto -1094 -> 1063
    //   2160: new 85	java/lang/StringBuilder
    //   2163: dup
    //   2164: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2167: ldc_w 549
    //   2170: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2173: invokestatic 381	com/kochava/android/tracker/c:e	()I
    //   2176: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2179: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2182: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2185: goto -1122 -> 1063
    //   2188: iload_1
    //   2189: ldc_w 550
    //   2192: if_icmple +42 -> 2234
    //   2195: new 85	java/lang/StringBuilder
    //   2198: dup
    //   2199: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2202: ldc_w 552
    //   2205: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2208: iload_1
    //   2209: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2212: ldc_w 554
    //   2215: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2218: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2221: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2224: ldc_w 555
    //   2227: invokestatic 557	com/kochava/android/tracker/c:c	(I)I
    //   2230: pop
    //   2231: goto -1081 -> 1150
    //   2234: iload_1
    //   2235: sipush 1000
    //   2238: imul
    //   2239: invokestatic 557	com/kochava/android/tracker/c:c	(I)I
    //   2242: pop
    //   2243: new 85	java/lang/StringBuilder
    //   2246: dup
    //   2247: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2250: ldc_w 559
    //   2253: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2256: iload_1
    //   2257: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2260: ldc_w 506
    //   2263: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2266: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2269: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2272: goto -1122 -> 1150
    //   2275: iload_1
    //   2276: ldc_w 550
    //   2279: if_icmple +58 -> 2337
    //   2282: new 85	java/lang/StringBuilder
    //   2285: dup
    //   2286: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2289: ldc_w 401
    //   2292: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2295: iload_1
    //   2296: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2299: ldc_w 561
    //   2302: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2305: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2308: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2311: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   2314: invokeinterface 363 1 0
    //   2319: ldc 100
    //   2321: ldc2_w 562
    //   2324: invokeinterface 407 4 0
    //   2329: invokeinterface 374 1 0
    //   2334: goto -1080 -> 1254
    //   2337: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   2340: invokeinterface 363 1 0
    //   2345: ldc 100
    //   2347: iload_1
    //   2348: sipush 1000
    //   2351: imul
    //   2352: i2l
    //   2353: invokeinterface 407 4 0
    //   2358: invokeinterface 374 1 0
    //   2363: new 85	java/lang/StringBuilder
    //   2366: dup
    //   2367: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2370: ldc_w 565
    //   2373: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2376: iload_1
    //   2377: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2380: ldc_w 506
    //   2383: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2386: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2389: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2392: goto -1138 -> 1254
    //   2395: iload_1
    //   2396: bipush 30
    //   2398: if_icmple +41 -> 2439
    //   2401: new 85	java/lang/StringBuilder
    //   2404: dup
    //   2405: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2408: ldc_w 411
    //   2411: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2414: iload_1
    //   2415: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2418: ldc_w 567
    //   2421: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2424: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2427: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2430: bipush 30
    //   2432: invokestatic 415	com/kochava/android/tracker/c:d	(I)I
    //   2435: pop
    //   2436: goto -1097 -> 1339
    //   2439: new 85	java/lang/StringBuilder
    //   2442: dup
    //   2443: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2446: ldc_w 569
    //   2449: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2452: iload_1
    //   2453: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2456: ldc_w 506
    //   2459: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2462: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2465: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2468: iload_1
    //   2469: invokestatic 415	com/kochava/android/tracker/c:d	(I)I
    //   2472: pop
    //   2473: goto -1134 -> 1339
    //   2476: iload_2
    //   2477: istore_1
    //   2478: iload_2
    //   2479: sipush 5000
    //   2482: if_icmple -1090 -> 1392
    //   2485: new 85	java/lang/StringBuilder
    //   2488: dup
    //   2489: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2492: ldc_w 419
    //   2495: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2498: iload_2
    //   2499: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2502: ldc_w 421
    //   2505: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2508: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2511: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2514: sipush 5000
    //   2517: istore_1
    //   2518: goto -1126 -> 1392
    //   2521: iload_2
    //   2522: istore_1
    //   2523: iload_2
    //   2524: bipush 60
    //   2526: if_icmple -1056 -> 1470
    //   2529: new 85	java/lang/StringBuilder
    //   2532: dup
    //   2533: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2536: ldc_w 432
    //   2539: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2542: iload_2
    //   2543: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2546: ldc_w 434
    //   2549: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2552: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2555: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2558: bipush 60
    //   2560: istore_1
    //   2561: goto -1091 -> 1470
    //   2564: iload_2
    //   2565: istore_1
    //   2566: iload_2
    //   2567: sipush 10080
    //   2570: if_icmple -1022 -> 1548
    //   2573: new 85	java/lang/StringBuilder
    //   2576: dup
    //   2577: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2580: ldc_w 442
    //   2583: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2586: iload_2
    //   2587: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2590: ldc_w 444
    //   2593: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2596: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2599: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2602: sipush 10080
    //   2605: istore_1
    //   2606: goto -1058 -> 1548
    //   2609: aload 14
    //   2611: iload_1
    //   2612: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   2615: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   2618: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2621: ldc_w 571
    //   2624: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2627: ifeq +527 -> 3154
    //   2630: ldc_w 573
    //   2633: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2636: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   2639: ldc_w 571
    //   2642: iconst_0
    //   2643: invokestatic 472	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   2646: invokevirtual 476	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2649: pop
    //   2650: goto -986 -> 1664
    //   2653: astore 14
    //   2655: ldc_w 575
    //   2658: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2661: getstatic 580	com/kochava/android/tracker/af:b	Z
    //   2664: ifeq +8 -> 2672
    //   2667: aload 14
    //   2669: invokevirtual 583	java/lang/Exception:printStackTrace	()V
    //   2672: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   2675: ldc_w 585
    //   2678: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2681: checkcast 469	java/lang/Boolean
    //   2684: invokevirtual 492	java/lang/Boolean:booleanValue	()Z
    //   2687: istore 11
    //   2689: iload 11
    //   2691: ifeq +759 -> 3450
    //   2694: getstatic 348	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   2697: ldc_w 587
    //   2700: invokevirtual 590	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2703: checkcast 592	android/telephony/TelephonyManager
    //   2706: invokevirtual 595	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   2709: invokestatic 597	com/kochava/android/tracker/c:e	(Ljava/lang/String;)Ljava/lang/String;
    //   2712: pop
    //   2713: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   2716: ldc_w 599
    //   2719: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2722: checkcast 469	java/lang/Boolean
    //   2725: invokevirtual 492	java/lang/Boolean:booleanValue	()Z
    //   2728: istore 11
    //   2730: iload 11
    //   2732: ifeq +759 -> 3491
    //   2735: getstatic 348	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   2738: ldc_w 601
    //   2741: invokevirtual 590	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2744: checkcast 603	android/net/wifi/WifiManager
    //   2747: invokevirtual 607	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   2750: invokevirtual 612	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   2753: invokestatic 614	com/kochava/android/tracker/c:f	(Ljava/lang/String;)Ljava/lang/String;
    //   2756: pop
    //   2757: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   2760: ldc_w 616
    //   2763: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2766: checkcast 469	java/lang/Boolean
    //   2769: invokevirtual 492	java/lang/Boolean:booleanValue	()Z
    //   2772: istore 11
    //   2774: iload 11
    //   2776: ifeq +773 -> 3549
    //   2779: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   2782: ldc -3
    //   2784: ldc 70
    //   2786: invokeinterface 74 3 0
    //   2791: ldc -1
    //   2793: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2796: ifne +154 -> 2950
    //   2799: getstatic 348	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   2802: invokevirtual 620	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2805: astore 15
    //   2807: aload 15
    //   2809: sipush 128
    //   2812: invokevirtual 626	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2815: invokeinterface 632 1 0
    //   2820: astore 16
    //   2822: aload 16
    //   2824: invokeinterface 637 1 0
    //   2829: ifeq +121 -> 2950
    //   2832: aload 16
    //   2834: invokeinterface 641 1 0
    //   2839: checkcast 643	android/content/pm/ApplicationInfo
    //   2842: astore 17
    //   2844: aload 17
    //   2846: invokestatic 646	com/kochava/android/tracker/c:a	(Landroid/content/pm/ApplicationInfo;)Z
    //   2849: istore 11
    //   2851: iload 11
    //   2853: ifne -31 -> 2822
    //   2856: aload 15
    //   2858: aload 17
    //   2860: getfield 650	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2863: iconst_0
    //   2864: invokevirtual 654	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2867: astore 14
    //   2869: aload 14
    //   2871: ifnull -49 -> 2822
    //   2874: invokestatic 658	com/kochava/android/tracker/c:h	()Ljava/util/List;
    //   2877: new 660	com/kochava/android/tracker/ab
    //   2880: dup
    //   2881: aload_0
    //   2882: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   2885: aload 14
    //   2887: getfield 663	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2890: new 85	java/lang/StringBuilder
    //   2893: dup
    //   2894: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2897: aload 14
    //   2899: getfield 667	android/content/pm/PackageInfo:firstInstallTime	J
    //   2902: invokevirtual 670	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2905: ldc 70
    //   2907: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2910: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2913: new 85	java/lang/StringBuilder
    //   2916: dup
    //   2917: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   2920: aload 14
    //   2922: getfield 673	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2925: invokevirtual 670	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2928: ldc 70
    //   2930: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2933: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2936: invokespecial 676	com/kochava/android/tracker/ab:<init>	(Lcom/kochava/android/tracker/c;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2939: invokeinterface 679 2 0
    //   2944: pop
    //   2945: goto -123 -> 2822
    //   2948: astore 14
    //   2950: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   2953: ldc_w 681
    //   2956: invokevirtual 290	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2959: checkcast 469	java/lang/Boolean
    //   2962: invokevirtual 492	java/lang/Boolean:booleanValue	()Z
    //   2965: istore 11
    //   2967: iload 11
    //   2969: ifeq +621 -> 3590
    //   2972: getstatic 348	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   2975: ldc_w 683
    //   2978: invokevirtual 590	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2981: checkcast 685	android/view/WindowManager
    //   2984: astore 14
    //   2986: aload_0
    //   2987: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   2990: aload 14
    //   2992: invokeinterface 689 1 0
    //   2997: invokevirtual 694	android/view/Display:getHeight	()I
    //   3000: invokestatic 697	com/kochava/android/tracker/c:a	(Lcom/kochava/android/tracker/c;I)I
    //   3003: pop
    //   3004: aload_0
    //   3005: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   3008: aload 14
    //   3010: invokeinterface 689 1 0
    //   3015: invokevirtual 700	android/view/Display:getWidth	()I
    //   3018: invokestatic 702	com/kochava/android/tracker/c:b	(Lcom/kochava/android/tracker/c;I)I
    //   3021: pop
    //   3022: new 85	java/lang/StringBuilder
    //   3025: dup
    //   3026: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3029: ldc_w 704
    //   3032: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3035: aload_0
    //   3036: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   3039: invokestatic 707	com/kochava/android/tracker/c:c	(Lcom/kochava/android/tracker/c;)I
    //   3042: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   3045: ldc_w 709
    //   3048: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3051: aload_0
    //   3052: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   3055: invokestatic 711	com/kochava/android/tracker/c:d	(Lcom/kochava/android/tracker/c;)I
    //   3058: invokevirtual 386	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   3061: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3064: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3067: aload 12
    //   3069: ldc_w 713
    //   3072: invokevirtual 261	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   3075: astore 14
    //   3077: new 85	java/lang/StringBuilder
    //   3080: dup
    //   3081: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3084: ldc_w 715
    //   3087: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3090: aload 14
    //   3092: invokevirtual 453	org/json/JSONArray:toString	()Ljava/lang/String;
    //   3095: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3098: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3101: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3104: iconst_0
    //   3105: istore_1
    //   3106: iload_1
    //   3107: aload 14
    //   3109: invokevirtual 267	org/json/JSONArray:length	()I
    //   3112: if_icmpge +551 -> 3663
    //   3115: aload 14
    //   3117: iload_1
    //   3118: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3121: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   3124: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3127: ldc_w 717
    //   3130: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3133: ifeq +466 -> 3599
    //   3136: ldc_w 719
    //   3139: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3142: iconst_1
    //   3143: invokestatic 721	com/kochava/android/tracker/c:e	(Z)Z
    //   3146: pop
    //   3147: iload_1
    //   3148: iconst_1
    //   3149: iadd
    //   3150: istore_1
    //   3151: goto -45 -> 3106
    //   3154: aload 14
    //   3156: iload_1
    //   3157: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3160: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   3163: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3166: ldc_w 489
    //   3169: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3172: ifeq +26 -> 3198
    //   3175: ldc_w 723
    //   3178: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3181: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   3184: ldc_w 489
    //   3187: iconst_0
    //   3188: invokestatic 472	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   3191: invokevirtual 476	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3194: pop
    //   3195: goto -1531 -> 1664
    //   3198: aload 14
    //   3200: iload_1
    //   3201: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3204: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   3207: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3210: ldc_w 599
    //   3213: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3216: ifeq +26 -> 3242
    //   3219: ldc_w 725
    //   3222: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3225: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   3228: ldc_w 599
    //   3231: iconst_0
    //   3232: invokestatic 472	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   3235: invokevirtual 476	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3238: pop
    //   3239: goto -1575 -> 1664
    //   3242: aload 14
    //   3244: iload_1
    //   3245: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3248: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   3251: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3254: ldc_w 585
    //   3257: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3260: ifeq +26 -> 3286
    //   3263: ldc_w 727
    //   3266: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3269: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   3272: ldc_w 585
    //   3275: iconst_0
    //   3276: invokestatic 472	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   3279: invokevirtual 476	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3282: pop
    //   3283: goto -1619 -> 1664
    //   3286: aload 14
    //   3288: iload_1
    //   3289: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3292: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   3295: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3298: ldc_w 681
    //   3301: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3304: ifeq +26 -> 3330
    //   3307: ldc_w 729
    //   3310: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3313: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   3316: ldc_w 681
    //   3319: iconst_0
    //   3320: invokestatic 472	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   3323: invokevirtual 476	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3326: pop
    //   3327: goto -1663 -> 1664
    //   3330: aload 14
    //   3332: iload_1
    //   3333: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3336: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   3339: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3342: ldc_w 616
    //   3345: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3348: ifeq +26 -> 3374
    //   3351: ldc_w 731
    //   3354: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3357: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   3360: ldc_w 616
    //   3363: iconst_0
    //   3364: invokestatic 472	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   3367: invokevirtual 476	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3370: pop
    //   3371: goto -1707 -> 1664
    //   3374: aload 14
    //   3376: iload_1
    //   3377: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3380: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   3383: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3386: ldc_w 733
    //   3389: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3392: ifeq -1728 -> 1664
    //   3395: ldc_w 735
    //   3398: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3401: invokestatic 467	com/kochava/android/tracker/c:g	()Ljava/util/HashMap;
    //   3404: ldc_w 733
    //   3407: iconst_0
    //   3408: invokestatic 472	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   3411: invokevirtual 476	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3414: pop
    //   3415: goto -1751 -> 1664
    //   3418: astore 14
    //   3420: new 85	java/lang/StringBuilder
    //   3423: dup
    //   3424: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3427: ldc_w 737
    //   3430: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3433: aload 14
    //   3435: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
    //   3438: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3441: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3444: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3447: goto -734 -> 2713
    //   3450: ldc_w 739
    //   3453: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3456: goto -743 -> 2713
    //   3459: astore 14
    //   3461: new 85	java/lang/StringBuilder
    //   3464: dup
    //   3465: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3468: ldc_w 741
    //   3471: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3474: aload 14
    //   3476: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
    //   3479: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3482: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3485: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3488: goto -731 -> 2757
    //   3491: ldc_w 743
    //   3494: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3497: goto -740 -> 2757
    //   3500: astore 14
    //   3502: new 85	java/lang/StringBuilder
    //   3505: dup
    //   3506: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3509: ldc_w 745
    //   3512: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3515: aload 17
    //   3517: getfield 650	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3520: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3523: ldc_w 747
    //   3526: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3529: aload 14
    //   3531: invokevirtual 748	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3534: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3537: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3540: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3543: aconst_null
    //   3544: astore 14
    //   3546: goto -677 -> 2869
    //   3549: ldc_w 750
    //   3552: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3555: goto -605 -> 2950
    //   3558: astore 14
    //   3560: new 85	java/lang/StringBuilder
    //   3563: dup
    //   3564: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3567: ldc_w 752
    //   3570: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3573: aload 14
    //   3575: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
    //   3578: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3581: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3584: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3587: goto -520 -> 3067
    //   3590: ldc_w 754
    //   3593: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3596: goto -529 -> 3067
    //   3599: aload 14
    //   3601: iload_1
    //   3602: invokevirtual 456	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   3605: invokevirtual 457	java/lang/Object:toString	()Ljava/lang/String;
    //   3608: invokevirtual 460	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   3611: ldc_w 756
    //   3614: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3617: ifeq -470 -> 3147
    //   3620: ldc_w 758
    //   3623: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3626: iconst_1
    //   3627: invokestatic 760	com/kochava/android/tracker/c:f	(Z)Z
    //   3630: pop
    //   3631: goto -484 -> 3147
    //   3634: astore 14
    //   3636: new 85	java/lang/StringBuilder
    //   3639: dup
    //   3640: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3643: ldc_w 762
    //   3646: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3649: aload 14
    //   3651: invokevirtual 247	java/lang/Exception:toString	()Ljava/lang/String;
    //   3654: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3657: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3660: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3663: aload 12
    //   3665: ldc_w 764
    //   3668: invokevirtual 309	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   3671: ldc_w 766
    //   3674: invokevirtual 309	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   3677: astore 14
    //   3679: aload_0
    //   3680: getfield 12	com/kochava/android/tracker/w:a	Lcom/kochava/android/tracker/c;
    //   3683: aload 14
    //   3685: invokestatic 769	com/kochava/android/tracker/c:a	(Lcom/kochava/android/tracker/c;Lorg/json/JSONObject;)V
    //   3688: invokestatic 772	com/kochava/android/tracker/c:i	()Z
    //   3691: ifeq +18 -> 3709
    //   3694: invokestatic 775	com/kochava/android/tracker/c:j	()Z
    //   3697: ifeq +12 -> 3709
    //   3700: getstatic 348	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   3703: invokestatic 778	com/kochava/android/tracker/ag:a	(Landroid/content/Context;)Lcom/kochava/android/tracker/ag;
    //   3706: invokevirtual 780	com/kochava/android/tracker/ag:a	()V
    //   3709: aload 12
    //   3711: ldc_w 782
    //   3714: invokevirtual 261	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   3717: invokestatic 785	com/kochava/android/tracker/c:a	(Lorg/json/JSONArray;)Lorg/json/JSONArray;
    //   3720: pop
    //   3721: new 85	java/lang/StringBuilder
    //   3724: dup
    //   3725: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3728: ldc_w 787
    //   3731: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3734: invokestatic 791	com/kochava/android/tracker/c:k	()Lorg/json/JSONArray;
    //   3737: invokevirtual 453	org/json/JSONArray:toString	()Ljava/lang/String;
    //   3740: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3743: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3746: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3749: invokestatic 54	com/kochava/android/tracker/c:b	()Landroid/content/SharedPreferences;
    //   3752: invokeinterface 363 1 0
    //   3757: ldc_w 793
    //   3760: invokestatic 791	com/kochava/android/tracker/c:k	()Lorg/json/JSONArray;
    //   3763: invokevirtual 453	org/json/JSONArray:toString	()Ljava/lang/String;
    //   3766: invokeinterface 371 3 0
    //   3771: invokeinterface 374 1 0
    //   3776: aload 13
    //   3778: ldc_w 795
    //   3781: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3784: ifnull +87 -> 3871
    //   3787: aload 13
    //   3789: ldc_w 795
    //   3792: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3795: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
    //   3798: ldc_w 469
    //   3801: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   3804: ifeq +140 -> 3944
    //   3807: aload 13
    //   3809: ldc_w 795
    //   3812: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3815: checkcast 469	java/lang/Boolean
    //   3818: invokevirtual 492	java/lang/Boolean:booleanValue	()Z
    //   3821: ifeq +123 -> 3944
    //   3824: iconst_1
    //   3825: istore_1
    //   3826: aload 13
    //   3828: ldc_w 795
    //   3831: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3834: invokevirtual 228	java/lang/Object:getClass	()Ljava/lang/Class;
    //   3837: ldc 79
    //   3839: invokevirtual 328	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   3842: ifeq +107 -> 3949
    //   3845: ldc -1
    //   3847: aload 13
    //   3849: ldc_w 795
    //   3852: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   3855: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3858: ifeq +91 -> 3949
    //   3861: iconst_1
    //   3862: istore_2
    //   3863: goto +430 -> 4293
    //   3866: iconst_1
    //   3867: invokestatic 797	com/kochava/android/tracker/c:g	(Z)Z
    //   3870: pop
    //   3871: aload 12
    //   3873: ldc_w 799
    //   3876: invokevirtual 315	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   3879: astore 12
    //   3881: new 85	java/lang/StringBuilder
    //   3884: dup
    //   3885: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3888: ldc_w 801
    //   3891: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3894: aload 12
    //   3896: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3899: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3902: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3905: aload 12
    //   3907: ldc_w 803
    //   3910: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3913: ifeq -2104 -> 1809
    //   3916: iconst_1
    //   3917: invokestatic 805	com/kochava/android/tracker/c:h	(Z)Z
    //   3920: pop
    //   3921: return
    //   3922: astore 12
    //   3924: ldc_w 807
    //   3927: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3930: goto -2121 -> 1809
    //   3933: astore 14
    //   3935: ldc_w 809
    //   3938: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   3941: goto -165 -> 3776
    //   3944: iconst_0
    //   3945: istore_1
    //   3946: goto -120 -> 3826
    //   3949: iconst_0
    //   3950: istore_2
    //   3951: goto +342 -> 4293
    //   3954: new 85	java/lang/StringBuilder
    //   3957: dup
    //   3958: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3961: ldc_w 811
    //   3964: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3967: aload 12
    //   3969: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   3972: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3975: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3978: return
    //   3979: new 85	java/lang/StringBuilder
    //   3982: dup
    //   3983: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   3986: ldc_w 811
    //   3989: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3992: aload 12
    //   3994: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   3997: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4000: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   4003: return
    //   4004: new 85	java/lang/StringBuilder
    //   4007: dup
    //   4008: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   4011: ldc_w 813
    //   4014: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4017: lload 5
    //   4019: ldc2_w 814
    //   4022: ldiv
    //   4023: invokevirtual 670	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   4026: ldc_w 817
    //   4029: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4032: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4035: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   4038: goto -2206 -> 1832
    //   4041: iconst_0
    //   4042: istore_2
    //   4043: goto -2163 -> 1880
    //   4046: iconst_0
    //   4047: istore_3
    //   4048: goto -2131 -> 1917
    //   4051: iconst_0
    //   4052: istore 4
    //   4054: goto -2128 -> 1926
    //   4057: iload_3
    //   4058: ifeq +8 -> 4066
    //   4061: iload 4
    //   4063: ifne -2124 -> 1939
    //   4066: ldc2_w 814
    //   4069: invokestatic 301	java/lang/Thread:sleep	(J)V
    //   4072: iload_1
    //   4073: iconst_1
    //   4074: iadd
    //   4075: istore_1
    //   4076: goto -2191 -> 1885
    //   4079: astore 12
    //   4081: new 85	java/lang/StringBuilder
    //   4084: dup
    //   4085: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   4088: ldc_w 819
    //   4091: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4094: aload 12
    //   4096: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   4099: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4102: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   4105: goto -33 -> 4072
    //   4108: iconst_0
    //   4109: istore_3
    //   4110: goto -2123 -> 1987
    //   4113: iconst_0
    //   4114: istore 4
    //   4116: goto -2111 -> 2005
    //   4119: ldc2_w 814
    //   4122: invokestatic 301	java/lang/Thread:sleep	(J)V
    //   4125: iload_1
    //   4126: iconst_1
    //   4127: iadd
    //   4128: istore_1
    //   4129: goto -2161 -> 1968
    //   4132: astore 12
    //   4134: new 85	java/lang/StringBuilder
    //   4137: dup
    //   4138: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   4141: ldc_w 819
    //   4144: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4147: aload 12
    //   4149: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   4152: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4155: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   4158: goto -33 -> 4125
    //   4161: iconst_0
    //   4162: istore_1
    //   4163: goto -2128 -> 2035
    //   4166: ldc2_w 814
    //   4169: invokestatic 301	java/lang/Thread:sleep	(J)V
    //   4172: iload_2
    //   4173: iconst_1
    //   4174: iadd
    //   4175: istore_2
    //   4176: goto -2158 -> 2018
    //   4179: astore 12
    //   4181: new 85	java/lang/StringBuilder
    //   4184: dup
    //   4185: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   4188: ldc_w 819
    //   4191: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4194: aload 12
    //   4196: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   4199: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4202: invokestatic 241	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   4205: goto -33 -> 4172
    //   4208: ldc_w 821
    //   4211: invokestatic 37	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   4214: goto -2175 -> 2039
    //   4217: astore 13
    //   4219: goto -348 -> 3871
    //   4222: astore 14
    //   4224: goto -448 -> 3776
    //   4227: astore 14
    //   4229: goto -541 -> 3688
    //   4232: astore 14
    //   4234: goto -2659 -> 1575
    //   4237: astore 14
    //   4239: goto -2742 -> 1497
    //   4242: astore 14
    //   4244: goto -2825 -> 1419
    //   4247: astore 14
    //   4249: goto -3246 -> 1003
    //   4252: astore 14
    //   4254: goto -3310 -> 944
    //   4257: goto -4111 -> 146
    //   4260: iconst_0
    //   4261: istore_2
    //   4262: goto -3527 -> 735
    //   4265: goto -3524 -> 741
    //   4268: iconst_0
    //   4269: istore_1
    //   4270: goto -4159 -> 111
    //   4273: astore 14
    //   4275: goto -3212 -> 1063
    //   4278: astore 14
    //   4280: goto -3125 -> 1155
    //   4283: astore 14
    //   4285: goto -3031 -> 1254
    //   4288: astore 14
    //   4290: goto -2951 -> 1339
    //   4293: iload_1
    //   4294: ifne -428 -> 3866
    //   4297: iload_2
    //   4298: ifeq -427 -> 3871
    //   4301: goto -435 -> 3866
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	4304	0	this	w
    //   110	4184	1	i	int
    //   635	3663	2	j	int
    //   661	3449	3	k	int
    //   1924	2191	4	m	int
    //   9	4009	5	l1	long
    //   14	18	7	l2	long
    //   29	5	9	l3	long
    //   612	2356	11	bool	boolean
    //   142	1	12	localObject1	Object
    //   475	39	12	localIOException	java.io.IOException
    //   519	15	12	localException1	Exception
    //   593	983	12	localObject2	Object
    //   1739	39	12	localException2	Exception
    //   1783	16	12	localException3	Exception
    //   2042	1864	12	localObject3	Object
    //   3922	71	12	localException4	Exception
    //   4079	16	12	localInterruptedException1	InterruptedException
    //   4132	16	12	localInterruptedException2	InterruptedException
    //   4179	16	12	localInterruptedException3	InterruptedException
    //   279	1219	13	localObject4	Object
    //   1671	16	13	localJSONException1	org.json.JSONException
    //   1710	1	13	localJSONException2	org.json.JSONException
    //   2051	1797	13	localBundle	android.os.Bundle
    //   4217	1	13	localException5	Exception
    //   359	1265	14	localObject5	Object
    //   1717	1	14	localJSONException3	org.json.JSONException
    //   1728	1	14	localJSONException4	org.json.JSONException
    //   2101	509	14	localJSONException5	org.json.JSONException
    //   2653	15	14	localException6	Exception
    //   2867	54	14	localPackageInfo	android.content.pm.PackageInfo
    //   2948	1	14	localException7	Exception
    //   2984	391	14	localObject6	Object
    //   3418	16	14	localException8	Exception
    //   3459	16	14	localException9	Exception
    //   3500	30	14	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   3544	1	14	localObject7	Object
    //   3558	42	14	localException10	Exception
    //   3634	16	14	localException11	Exception
    //   3677	7	14	localJSONObject	org.json.JSONObject
    //   3933	1	14	localException12	Exception
    //   4222	1	14	localJSONException6	org.json.JSONException
    //   4227	1	14	localException13	Exception
    //   4232	1	14	localException14	Exception
    //   4237	1	14	localException15	Exception
    //   4242	1	14	localException16	Exception
    //   4247	1	14	localException17	Exception
    //   4252	1	14	localException18	Exception
    //   4273	1	14	localException19	Exception
    //   4278	1	14	localException20	Exception
    //   4283	1	14	localException21	Exception
    //   4288	1	14	localException22	Exception
    //   401	2456	15	localObject8	Object
    //   2820	13	16	localIterator	java.util.Iterator
    //   2842	674	17	localApplicationInfo	android.content.pm.ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   146	195	475	java/io/IOException
    //   195	206	475	java/io/IOException
    //   206	452	475	java/io/IOException
    //   452	459	475	java/io/IOException
    //   464	472	475	java/io/IOException
    //   550	580	475	java/io/IOException
    //   580	591	475	java/io/IOException
    //   595	614	475	java/io/IOException
    //   624	634	475	java/io/IOException
    //   636	660	475	java/io/IOException
    //   673	731	475	java/io/IOException
    //   750	777	475	java/io/IOException
    //   780	790	475	java/io/IOException
    //   794	821	475	java/io/IOException
    //   830	873	475	java/io/IOException
    //   873	895	475	java/io/IOException
    //   895	944	475	java/io/IOException
    //   944	1003	475	java/io/IOException
    //   1003	1063	475	java/io/IOException
    //   1063	1115	475	java/io/IOException
    //   1121	1150	475	java/io/IOException
    //   1150	1155	475	java/io/IOException
    //   1155	1198	475	java/io/IOException
    //   1202	1254	475	java/io/IOException
    //   1254	1300	475	java/io/IOException
    //   1305	1339	475	java/io/IOException
    //   1339	1354	475	java/io/IOException
    //   1360	1389	475	java/io/IOException
    //   1392	1419	475	java/io/IOException
    //   1419	1434	475	java/io/IOException
    //   1439	1468	475	java/io/IOException
    //   1470	1497	475	java/io/IOException
    //   1497	1512	475	java/io/IOException
    //   1517	1546	475	java/io/IOException
    //   1548	1575	475	java/io/IOException
    //   1575	1612	475	java/io/IOException
    //   1614	1664	475	java/io/IOException
    //   1673	1700	475	java/io/IOException
    //   1719	1725	475	java/io/IOException
    //   1730	1736	475	java/io/IOException
    //   1741	1782	475	java/io/IOException
    //   2103	2109	475	java/io/IOException
    //   2112	2157	475	java/io/IOException
    //   2160	2185	475	java/io/IOException
    //   2195	2231	475	java/io/IOException
    //   2234	2272	475	java/io/IOException
    //   2282	2334	475	java/io/IOException
    //   2337	2392	475	java/io/IOException
    //   2401	2436	475	java/io/IOException
    //   2439	2473	475	java/io/IOException
    //   2485	2514	475	java/io/IOException
    //   2529	2558	475	java/io/IOException
    //   2573	2602	475	java/io/IOException
    //   2609	2650	475	java/io/IOException
    //   2655	2672	475	java/io/IOException
    //   2672	2689	475	java/io/IOException
    //   2694	2713	475	java/io/IOException
    //   2713	2730	475	java/io/IOException
    //   2735	2757	475	java/io/IOException
    //   2757	2774	475	java/io/IOException
    //   2779	2822	475	java/io/IOException
    //   2822	2851	475	java/io/IOException
    //   2856	2869	475	java/io/IOException
    //   2874	2945	475	java/io/IOException
    //   2950	2967	475	java/io/IOException
    //   2972	3067	475	java/io/IOException
    //   3067	3104	475	java/io/IOException
    //   3106	3147	475	java/io/IOException
    //   3154	3195	475	java/io/IOException
    //   3198	3239	475	java/io/IOException
    //   3242	3283	475	java/io/IOException
    //   3286	3327	475	java/io/IOException
    //   3330	3371	475	java/io/IOException
    //   3374	3415	475	java/io/IOException
    //   3420	3447	475	java/io/IOException
    //   3450	3456	475	java/io/IOException
    //   3461	3488	475	java/io/IOException
    //   3491	3497	475	java/io/IOException
    //   3502	3543	475	java/io/IOException
    //   3549	3555	475	java/io/IOException
    //   3560	3587	475	java/io/IOException
    //   3590	3596	475	java/io/IOException
    //   3599	3631	475	java/io/IOException
    //   3636	3663	475	java/io/IOException
    //   3663	3688	475	java/io/IOException
    //   3688	3709	475	java/io/IOException
    //   3709	3776	475	java/io/IOException
    //   3776	3824	475	java/io/IOException
    //   3826	3861	475	java/io/IOException
    //   3866	3871	475	java/io/IOException
    //   3871	3921	475	java/io/IOException
    //   3924	3930	475	java/io/IOException
    //   3935	3941	475	java/io/IOException
    //   3954	3978	475	java/io/IOException
    //   11	31	519	java/lang/Exception
    //   580	591	1671	org/json/JSONException
    //   624	634	1710	org/json/JSONException
    //   636	660	1710	org/json/JSONException
    //   673	731	1710	org/json/JSONException
    //   780	790	1717	org/json/JSONException
    //   794	821	1717	org/json/JSONException
    //   830	873	1728	org/json/JSONException
    //   750	777	1739	java/lang/Exception
    //   780	790	1739	java/lang/Exception
    //   794	821	1739	java/lang/Exception
    //   830	873	1739	java/lang/Exception
    //   873	895	1739	java/lang/Exception
    //   1719	1725	1739	java/lang/Exception
    //   1730	1736	1739	java/lang/Exception
    //   2103	2109	1739	java/lang/Exception
    //   2655	2672	1739	java/lang/Exception
    //   2672	2689	1739	java/lang/Exception
    //   2713	2730	1739	java/lang/Exception
    //   2757	2774	1739	java/lang/Exception
    //   2950	2967	1739	java/lang/Exception
    //   3420	3447	1739	java/lang/Exception
    //   3450	3456	1739	java/lang/Exception
    //   3461	3488	1739	java/lang/Exception
    //   3491	3497	1739	java/lang/Exception
    //   3549	3555	1739	java/lang/Exception
    //   3560	3587	1739	java/lang/Exception
    //   3590	3596	1739	java/lang/Exception
    //   3636	3663	1739	java/lang/Exception
    //   3688	3709	1739	java/lang/Exception
    //   3924	3930	1739	java/lang/Exception
    //   3935	3941	1739	java/lang/Exception
    //   146	195	1783	java/lang/Exception
    //   195	206	1783	java/lang/Exception
    //   206	452	1783	java/lang/Exception
    //   452	459	1783	java/lang/Exception
    //   464	472	1783	java/lang/Exception
    //   550	580	1783	java/lang/Exception
    //   580	591	1783	java/lang/Exception
    //   595	614	1783	java/lang/Exception
    //   624	634	1783	java/lang/Exception
    //   636	660	1783	java/lang/Exception
    //   673	731	1783	java/lang/Exception
    //   1673	1700	1783	java/lang/Exception
    //   1741	1782	1783	java/lang/Exception
    //   3954	3978	1783	java/lang/Exception
    //   873	895	2101	org/json/JSONException
    //   1575	1612	2653	java/lang/Exception
    //   1614	1664	2653	java/lang/Exception
    //   2609	2650	2653	java/lang/Exception
    //   3154	3195	2653	java/lang/Exception
    //   3198	3239	2653	java/lang/Exception
    //   3242	3283	2653	java/lang/Exception
    //   3286	3327	2653	java/lang/Exception
    //   3330	3371	2653	java/lang/Exception
    //   3374	3415	2653	java/lang/Exception
    //   2779	2822	2948	java/lang/Exception
    //   2822	2851	2948	java/lang/Exception
    //   2856	2869	2948	java/lang/Exception
    //   2874	2945	2948	java/lang/Exception
    //   3502	3543	2948	java/lang/Exception
    //   2694	2713	3418	java/lang/Exception
    //   2735	2757	3459	java/lang/Exception
    //   2856	2869	3500	android/content/pm/PackageManager$NameNotFoundException
    //   2972	3067	3558	java/lang/Exception
    //   3067	3104	3634	java/lang/Exception
    //   3106	3147	3634	java/lang/Exception
    //   3599	3631	3634	java/lang/Exception
    //   3871	3921	3922	java/lang/Exception
    //   3709	3776	3933	java/lang/Exception
    //   4066	4072	4079	java/lang/InterruptedException
    //   4119	4125	4132	java/lang/InterruptedException
    //   4166	4172	4179	java/lang/InterruptedException
    //   3776	3824	4217	java/lang/Exception
    //   3826	3861	4217	java/lang/Exception
    //   3866	3871	4217	java/lang/Exception
    //   3709	3776	4222	org/json/JSONException
    //   3663	3688	4227	java/lang/Exception
    //   1497	1512	4232	java/lang/Exception
    //   1517	1546	4232	java/lang/Exception
    //   1548	1575	4232	java/lang/Exception
    //   2573	2602	4232	java/lang/Exception
    //   1419	1434	4237	java/lang/Exception
    //   1439	1468	4237	java/lang/Exception
    //   1470	1497	4237	java/lang/Exception
    //   2529	2558	4237	java/lang/Exception
    //   1339	1354	4242	java/lang/Exception
    //   1360	1389	4242	java/lang/Exception
    //   1392	1419	4242	java/lang/Exception
    //   2485	2514	4242	java/lang/Exception
    //   944	1003	4247	java/lang/Exception
    //   895	944	4252	java/lang/Exception
    //   1003	1063	4273	java/lang/Exception
    //   2112	2157	4273	java/lang/Exception
    //   2160	2185	4273	java/lang/Exception
    //   1063	1115	4278	java/lang/Exception
    //   1121	1150	4278	java/lang/Exception
    //   1150	1155	4278	java/lang/Exception
    //   2195	2231	4278	java/lang/Exception
    //   2234	2272	4278	java/lang/Exception
    //   1155	1198	4283	java/lang/Exception
    //   1202	1254	4283	java/lang/Exception
    //   2282	2334	4283	java/lang/Exception
    //   2337	2392	4283	java/lang/Exception
    //   1254	1300	4288	java/lang/Exception
    //   1305	1339	4288	java/lang/Exception
    //   2401	2436	4288	java/lang/Exception
    //   2439	2473	4288	java/lang/Exception
  }
}
