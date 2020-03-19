package com.yiba.sdk.a;

import android.content.Context;
import com.yiba.sdk.DownloadConfig;
import com.yiba.sdk.R.string;
import org.json.JSONObject;

public final class e
  extends g
{
  boolean a = false;
  private final String g;
  private JSONObject h;
  private int i;
  private DownloadConfig j;
  
  public e(Context paramContext)
  {
    super(paramContext);
    this.g = paramContext.getApplicationContext().getPackageName();
    this.h = null;
    this.j = new DownloadConfig("", 0);
    this.c = R.string.yiba_CheckWifiSecurity;
  }
  
  /* Error */
  protected final Integer a()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: invokestatic 65	java/lang/System:currentTimeMillis	()J
    //   5: lstore_3
    //   6: aload_0
    //   7: getfield 69	com/yiba/sdk/a/e:f	Landroid/content/Context;
    //   10: invokestatic 74	com/yiba/c/a:c	(Landroid/content/Context;)Z
    //   13: ifeq +399 -> 412
    //   16: aload_0
    //   17: getfield 69	com/yiba/sdk/a/e:f	Landroid/content/Context;
    //   20: invokevirtual 78	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   23: invokevirtual 84	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   26: getfield 90	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   29: invokevirtual 95	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   32: astore 6
    //   34: ldc 97
    //   36: iconst_1
    //   37: anewarray 99	java/lang/Object
    //   40: dup
    //   41: iconst_0
    //   42: aload_0
    //   43: getfield 32	com/yiba/sdk/a/e:g	Ljava/lang/String;
    //   46: aastore
    //   47: invokestatic 105	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   50: invokestatic 110	com/yiba/b/a:a	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   53: astore 9
    //   55: aload 9
    //   57: ifnull +268 -> 325
    //   60: aload_0
    //   61: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   64: astore 8
    //   66: aload 8
    //   68: monitorenter
    //   69: aload 9
    //   71: aload 6
    //   73: invokevirtual 115	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   76: astore 7
    //   78: aload 7
    //   80: astore 6
    //   82: aload 7
    //   84: ifnonnull +12 -> 96
    //   87: aload 9
    //   89: ldc 117
    //   91: invokevirtual 115	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   94: astore 6
    //   96: aload 6
    //   98: ifnull +224 -> 322
    //   101: aload 6
    //   103: ldc 119
    //   105: invokevirtual 123	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   108: istore_1
    //   109: aload 6
    //   111: ldc 125
    //   113: invokevirtual 129	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   116: astore 7
    //   118: aload_0
    //   119: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   122: aload 7
    //   124: putfield 132	com/yiba/sdk/DownloadConfig:d	Ljava/lang/String;
    //   127: aload_0
    //   128: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   131: iload_1
    //   132: putfield 133	com/yiba/sdk/DownloadConfig:c	I
    //   135: aload_0
    //   136: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   139: aload 6
    //   141: ldc -121
    //   143: invokevirtual 129	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   146: putfield 137	com/yiba/sdk/DownloadConfig:a	Ljava/lang/String;
    //   149: aload_0
    //   150: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   153: aload 6
    //   155: ldc -117
    //   157: invokevirtual 142	org/json/JSONObject:optInt	(Ljava/lang/String;)I
    //   160: putfield 145	com/yiba/sdk/DownloadConfig:b	I
    //   163: aload_0
    //   164: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   167: aload 6
    //   169: ldc -109
    //   171: invokevirtual 150	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   174: putfield 152	com/yiba/sdk/DownloadConfig:h	Ljava/lang/String;
    //   177: aload_0
    //   178: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   181: aload 6
    //   183: ldc -102
    //   185: invokevirtual 150	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   188: putfield 156	com/yiba/sdk/DownloadConfig:i	Ljava/lang/String;
    //   191: aload_0
    //   192: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   195: iconst_0
    //   196: putfield 158	com/yiba/sdk/DownloadConfig:g	Z
    //   199: iload_1
    //   200: ifle +122 -> 322
    //   203: aload_0
    //   204: getfield 69	com/yiba/sdk/a/e:f	Landroid/content/Context;
    //   207: ldc -109
    //   209: invokevirtual 162	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   212: checkcast 164	android/app/ActivityManager
    //   215: invokevirtual 168	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   218: astore 6
    //   220: aload 6
    //   222: ifnull +16 -> 238
    //   225: iconst_0
    //   226: istore_1
    //   227: iload_1
    //   228: aload 6
    //   230: invokeinterface 174 1 0
    //   235: if_icmplt +207 -> 442
    //   238: aload_0
    //   239: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   242: getfield 132	com/yiba/sdk/DownloadConfig:d	Ljava/lang/String;
    //   245: astore 9
    //   247: aload_0
    //   248: getfield 69	com/yiba/sdk/a/e:f	Landroid/content/Context;
    //   251: invokevirtual 178	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   254: iconst_0
    //   255: invokevirtual 184	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   258: astore 10
    //   260: iconst_0
    //   261: istore_1
    //   262: iload_1
    //   263: aload 10
    //   265: invokeinterface 174 1 0
    //   270: if_icmplt +250 -> 520
    //   273: aconst_null
    //   274: astore 6
    //   276: aload 6
    //   278: ifnull +283 -> 561
    //   281: aload_0
    //   282: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   285: iconst_1
    //   286: putfield 187	com/yiba/sdk/DownloadConfig:e	Z
    //   289: aload_0
    //   290: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   293: getfield 145	com/yiba/sdk/DownloadConfig:b	I
    //   296: ifle +26 -> 322
    //   299: aload_0
    //   300: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   303: getfield 145	com/yiba/sdk/DownloadConfig:b	I
    //   306: aload 6
    //   308: getfield 192	android/content/pm/PackageInfo:versionCode	I
    //   311: if_icmple +11 -> 322
    //   314: aload_0
    //   315: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   318: iconst_1
    //   319: putfield 194	com/yiba/sdk/DownloadConfig:f	Z
    //   322: aload 8
    //   324: monitorexit
    //   325: aload_0
    //   326: getfield 69	com/yiba/sdk/a/e:f	Landroid/content/Context;
    //   329: invokestatic 197	com/yiba/c/a:b	(Landroid/content/Context;)Landroid/net/wifi/WifiManager;
    //   332: invokestatic 200	com/yiba/c/a:a	(Landroid/net/wifi/WifiManager;)Ljava/util/List;
    //   335: astore 7
    //   337: aload_0
    //   338: getfield 69	com/yiba/sdk/a/e:f	Landroid/content/Context;
    //   341: invokestatic 203	com/yiba/c/a:a	(Landroid/content/Context;)Lcom/yiba/c/a$a;
    //   344: astore 6
    //   346: aload 7
    //   348: invokeinterface 207 1 0
    //   353: astore 7
    //   355: aload 7
    //   357: invokeinterface 213 1 0
    //   362: ifne +210 -> 572
    //   365: iconst_0
    //   366: istore_1
    //   367: goto +330 -> 697
    //   370: aload_0
    //   371: iload_1
    //   372: putfield 215	com/yiba/sdk/a/e:i	I
    //   375: aload_0
    //   376: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   379: astore 6
    //   381: aload 6
    //   383: monitorenter
    //   384: aload_0
    //   385: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   388: getfield 133	com/yiba/sdk/DownloadConfig:c	I
    //   391: ifle +18 -> 409
    //   394: aload_0
    //   395: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   398: getfield 158	com/yiba/sdk/DownloadConfig:g	Z
    //   401: ifne +8 -> 409
    //   404: aload_0
    //   405: iconst_m1
    //   406: putfield 215	com/yiba/sdk/a/e:i	I
    //   409: aload 6
    //   411: monitorexit
    //   412: invokestatic 65	java/lang/System:currentTimeMillis	()J
    //   415: lload_3
    //   416: lsub
    //   417: lstore_3
    //   418: lload_3
    //   419: ldc2_w 216
    //   422: lcmp
    //   423: ifge +11 -> 434
    //   426: ldc2_w 216
    //   429: lload_3
    //   430: lsub
    //   431: invokestatic 223	java/lang/Thread:sleep	(J)V
    //   434: aload_0
    //   435: getfield 215	com/yiba/sdk/a/e:i	I
    //   438: invokestatic 229	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   441: areturn
    //   442: aload 6
    //   444: iload_1
    //   445: invokeinterface 233 2 0
    //   450: checkcast 235	android/app/ActivityManager$RunningAppProcessInfo
    //   453: getfield 238	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   456: aload_0
    //   457: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   460: getfield 132	com/yiba/sdk/DownloadConfig:d	Ljava/lang/String;
    //   463: invokevirtual 242	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   466: ifeq +47 -> 513
    //   469: aload_0
    //   470: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   473: iconst_1
    //   474: putfield 158	com/yiba/sdk/DownloadConfig:g	Z
    //   477: goto -239 -> 238
    //   480: astore 6
    //   482: aload 6
    //   484: invokevirtual 246	org/json/JSONException:printStackTrace	()V
    //   487: goto -165 -> 322
    //   490: astore 6
    //   492: aload 8
    //   494: monitorexit
    //   495: aload 6
    //   497: athrow
    //   498: astore 6
    //   500: aload 6
    //   502: invokevirtual 247	java/lang/Exception:printStackTrace	()V
    //   505: aload_0
    //   506: iconst_0
    //   507: putfield 215	com/yiba/sdk/a/e:i	I
    //   510: goto -98 -> 412
    //   513: iload_1
    //   514: iconst_1
    //   515: iadd
    //   516: istore_1
    //   517: goto -290 -> 227
    //   520: aload 10
    //   522: iload_1
    //   523: invokeinterface 233 2 0
    //   528: checkcast 189	android/content/pm/PackageInfo
    //   531: astore 7
    //   533: aload 7
    //   535: getfield 250	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   538: ifnull +168 -> 706
    //   541: aload 7
    //   543: astore 6
    //   545: aload 7
    //   547: getfield 253	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   550: aload 9
    //   552: invokevirtual 242	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   555: ifne -279 -> 276
    //   558: goto +148 -> 706
    //   561: aload_0
    //   562: getfield 43	com/yiba/sdk/a/e:j	Lcom/yiba/sdk/DownloadConfig;
    //   565: iconst_1
    //   566: putfield 194	com/yiba/sdk/DownloadConfig:f	Z
    //   569: goto -247 -> 322
    //   572: aload 7
    //   574: invokeinterface 257 1 0
    //   579: checkcast 259	com/yiba/c/a$a
    //   582: astore 8
    //   584: aload 8
    //   586: getfield 261	com/yiba/c/a$a:b	Ljava/lang/String;
    //   589: aload 6
    //   591: getfield 261	com/yiba/c/a$a:b	Ljava/lang/String;
    //   594: invokevirtual 242	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   597: ifeq -242 -> 355
    //   600: aload 8
    //   602: getfield 263	com/yiba/c/a$a:c	Ljava/lang/String;
    //   605: ifnull +64 -> 669
    //   608: aload 8
    //   610: getfield 263	com/yiba/c/a$a:c	Ljava/lang/String;
    //   613: ldc_w 265
    //   616: invokevirtual 269	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   619: ifeq +8 -> 627
    //   622: iconst_1
    //   623: istore_1
    //   624: goto +73 -> 697
    //   627: aload 8
    //   629: getfield 263	com/yiba/c/a$a:c	Ljava/lang/String;
    //   632: ldc_w 271
    //   635: invokevirtual 269	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   638: ifeq +8 -> 646
    //   641: iconst_2
    //   642: istore_1
    //   643: goto +54 -> 697
    //   646: aload 8
    //   648: getfield 263	com/yiba/c/a$a:c	Ljava/lang/String;
    //   651: ldc_w 273
    //   654: invokevirtual 269	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   657: istore 5
    //   659: iload 5
    //   661: ifeq +8 -> 669
    //   664: iconst_3
    //   665: istore_1
    //   666: goto +31 -> 697
    //   669: iconst_0
    //   670: istore_1
    //   671: goto +26 -> 697
    //   674: iconst_m1
    //   675: istore_1
    //   676: goto -306 -> 370
    //   679: astore 7
    //   681: aload 6
    //   683: monitorexit
    //   684: aload 7
    //   686: athrow
    //   687: astore 6
    //   689: aload 6
    //   691: invokevirtual 274	java/lang/InterruptedException:printStackTrace	()V
    //   694: goto -260 -> 434
    //   697: iload_1
    //   698: ifle -24 -> 674
    //   701: iload_2
    //   702: istore_1
    //   703: goto -333 -> 370
    //   706: iload_1
    //   707: iconst_1
    //   708: iadd
    //   709: istore_1
    //   710: goto -448 -> 262
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	713	0	this	e
    //   108	602	1	k	int
    //   1	701	2	m	int
    //   5	425	3	l	long
    //   657	3	5	bool	boolean
    //   480	3	6	localJSONException	org.json.JSONException
    //   490	6	6	localObject2	Object
    //   498	3	6	localException	Exception
    //   543	139	6	localObject3	Object
    //   687	3	6	localInterruptedException	InterruptedException
    //   76	497	7	localObject4	Object
    //   679	6	7	localObject5	Object
    //   64	583	8	localObject6	Object
    //   53	498	9	localObject7	Object
    //   258	263	10	localList	java.util.List
    // Exception table:
    //   from	to	target	type
    //   69	78	480	org/json/JSONException
    //   87	96	480	org/json/JSONException
    //   101	199	480	org/json/JSONException
    //   203	220	480	org/json/JSONException
    //   227	238	480	org/json/JSONException
    //   238	260	480	org/json/JSONException
    //   262	273	480	org/json/JSONException
    //   281	322	480	org/json/JSONException
    //   442	477	480	org/json/JSONException
    //   520	541	480	org/json/JSONException
    //   545	558	480	org/json/JSONException
    //   561	569	480	org/json/JSONException
    //   69	78	490	finally
    //   87	96	490	finally
    //   101	199	490	finally
    //   203	220	490	finally
    //   227	238	490	finally
    //   238	260	490	finally
    //   262	273	490	finally
    //   281	322	490	finally
    //   322	325	490	finally
    //   442	477	490	finally
    //   482	487	490	finally
    //   492	495	490	finally
    //   520	541	490	finally
    //   545	558	490	finally
    //   561	569	490	finally
    //   6	55	498	java/lang/Exception
    //   60	69	498	java/lang/Exception
    //   325	355	498	java/lang/Exception
    //   355	365	498	java/lang/Exception
    //   370	384	498	java/lang/Exception
    //   495	498	498	java/lang/Exception
    //   572	622	498	java/lang/Exception
    //   627	641	498	java/lang/Exception
    //   646	659	498	java/lang/Exception
    //   684	687	498	java/lang/Exception
    //   384	409	679	finally
    //   409	412	679	finally
    //   681	684	679	finally
    //   426	434	687	java/lang/InterruptedException
  }
  
  protected final void a(Integer paramInteger)
  {
    super.a(paramInteger);
  }
  
  public final DownloadConfig b()
  {
    return this.j;
  }
}
