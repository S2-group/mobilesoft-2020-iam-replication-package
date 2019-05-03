package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import org.json.JSONObject;

public class q
{
  private final a a;
  private final Context b;
  private final int c;
  private final String d;
  private final String e;
  private final String f;
  private final String g;
  private final JSONObject h;
  
  q(Activity paramActivity, a paramA, int paramInt, String paramString1, String paramString2)
  {
    this(paramActivity, paramA, paramInt, paramString1, paramString2, null, null, null);
  }
  
  q(Activity paramActivity, a paramA, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this(paramActivity, paramA, paramInt, paramString1, paramString2, paramString3, null, null);
  }
  
  q(Activity paramActivity, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this(paramActivity, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, null);
  }
  
  q(Activity paramActivity, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, JSONObject paramJSONObject)
  {
    this.a = paramA;
    this.b = paramActivity;
    this.c = paramInt;
    this.d = paramString1;
    this.e = paramString2;
    this.f = paramString3;
    this.g = paramString4;
    this.h = paramJSONObject;
    if ((AppodealSettings.a) && (this.a == null) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")))) {
      return;
    }
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (Build.VERSION.SDK_INT >= 11)
        {
          new q.b(q.this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
          return;
        }
        new q.b(q.this, null).execute(new Void[0]);
      }
    });
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(JSONObject paramJSONObject, int paramInt, String paramString);
  }
  
  private class b
    extends AsyncTask
  {
    private b() {}
    
    /* Error */
    protected JSONObject a(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   7: invokestatic 38	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
      //   10: astore 12
      //   12: aload_0
      //   13: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   16: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   19: invokevirtual 44	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   22: astore 13
      //   24: aload 12
      //   26: ldc 46
      //   28: aconst_null
      //   29: invokeinterface 52 3 0
      //   34: astore 15
      //   36: aload 15
      //   38: ifnonnull +5 -> 43
      //   41: aconst_null
      //   42: areturn
      //   43: aload 12
      //   45: ldc 54
      //   47: aconst_null
      //   48: invokeinterface 52 3 0
      //   53: astore_1
      //   54: aload 12
      //   56: ldc 56
      //   58: aconst_null
      //   59: invokeinterface 52 3 0
      //   64: astore 14
      //   66: aload_1
      //   67: ifnonnull +1811 -> 1878
      //   70: aload_0
      //   71: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   74: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   77: invokestatic 62	com/appodeal/ads/r:i	(Landroid/content/Context;)Ljava/lang/String;
      //   80: astore_1
      //   81: new 64	org/json/JSONObject
      //   84: dup
      //   85: invokespecial 65	org/json/JSONObject:<init>	()V
      //   88: astore 11
      //   90: aload_0
      //   91: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   94: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   97: ldc 70
      //   99: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   102: ifne +1782 -> 1884
      //   105: aload_0
      //   106: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   109: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   112: ldc 78
      //   114: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   117: ifeq +1115 -> 1232
      //   120: goto +1764 -> 1884
      //   123: aload_0
      //   124: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   127: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   130: ldc 80
      //   132: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   135: ifne +1754 -> 1889
      //   138: aload_0
      //   139: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   142: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   145: ldc 82
      //   147: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   150: ifeq +1087 -> 1237
      //   153: goto +1736 -> 1889
      //   156: aload_0
      //   157: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   160: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   163: ldc 84
      //   165: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   168: ifne +1726 -> 1894
      //   171: aload_0
      //   172: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   175: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   178: ldc 86
      //   180: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   183: ifeq +1059 -> 1242
      //   186: goto +1708 -> 1894
      //   189: aload_0
      //   190: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   193: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   196: ldc 78
      //   198: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   201: ifne +37 -> 238
      //   204: aload_0
      //   205: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   208: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   211: ldc 82
      //   213: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   216: ifne +22 -> 238
      //   219: aload_0
      //   220: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   223: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   226: ldc 86
      //   228: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   231: istore 6
      //   233: iload 6
      //   235: ifeq +1013 -> 1248
      //   238: iconst_1
      //   239: istore 5
      //   241: aload 11
      //   243: ldc 88
      //   245: aload 15
      //   247: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   250: pop
      //   251: iload_2
      //   252: ifeq +13 -> 265
      //   255: aload 11
      //   257: ldc 94
      //   259: ldc 70
      //   261: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   264: pop
      //   265: iload_3
      //   266: ifeq +13 -> 279
      //   269: aload 11
      //   271: ldc 94
      //   273: ldc 80
      //   275: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   278: pop
      //   279: iload 4
      //   281: ifeq +13 -> 294
      //   284: aload 11
      //   286: ldc 94
      //   288: ldc 84
      //   290: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   293: pop
      //   294: iload 5
      //   296: ifeq +12 -> 308
      //   299: aload 11
      //   301: ldc 78
      //   303: iconst_1
      //   304: invokevirtual 97	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   307: pop
      //   308: getstatic 102	com/appodeal/ads/AppodealSettings:a	Z
      //   311: ifeq +12 -> 323
      //   314: aload 11
      //   316: ldc 104
      //   318: iconst_1
      //   319: invokevirtual 97	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   322: pop
      //   323: aload 11
      //   325: ldc 106
      //   327: getstatic 112	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   330: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   333: pop
      //   334: aload 11
      //   336: ldc 114
      //   338: getstatic 118	android/os/Build$VERSION:SDK_INT	I
      //   341: invokevirtual 121	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   344: pop
      //   345: aload 11
      //   347: ldc 123
      //   349: ldc 125
      //   351: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   354: pop
      //   355: aload_0
      //   356: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   359: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   362: invokevirtual 129	android/content/Context:getPackageName	()Ljava/lang/String;
      //   365: astore 15
      //   367: aload 11
      //   369: ldc -125
      //   371: aload 15
      //   373: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   376: pop
      //   377: aload 11
      //   379: ldc -123
      //   381: aload 13
      //   383: aload 15
      //   385: iconst_0
      //   386: invokevirtual 139	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   389: getfield 144	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   392: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   395: pop
      //   396: aload 11
      //   398: ldc -110
      //   400: aload 13
      //   402: aload 15
      //   404: sipush 128
      //   407: invokevirtual 150	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
      //   410: getfield 156	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
      //   413: ldc -98
      //   415: invokevirtual 163	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   418: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   421: pop
      //   422: aload 11
      //   424: ldc -91
      //   426: aload_1
      //   427: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   430: pop
      //   431: aload 11
      //   433: ldc -89
      //   435: aload 14
      //   437: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   440: pop
      //   441: aload_0
      //   442: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   445: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   448: invokestatic 171	com/appodeal/ads/r:d	(Landroid/content/Context;)Landroid/util/Pair;
      //   451: astore_1
      //   452: aload 11
      //   454: ldc -83
      //   456: aload_1
      //   457: getfield 179	android/util/Pair:first	Ljava/lang/Object;
      //   460: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   463: pop
      //   464: aload 11
      //   466: ldc -75
      //   468: aload_1
      //   469: getfield 184	android/util/Pair:second	Ljava/lang/Object;
      //   472: checkcast 175	android/util/Pair
      //   475: getfield 179	android/util/Pair:first	Ljava/lang/Object;
      //   478: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   481: pop
      //   482: aload 11
      //   484: ldc -70
      //   486: aload_1
      //   487: getfield 184	android/util/Pair:second	Ljava/lang/Object;
      //   490: checkcast 175	android/util/Pair
      //   493: getfield 184	android/util/Pair:second	Ljava/lang/Object;
      //   496: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   499: pop
      //   500: aload_0
      //   501: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   504: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   507: invokestatic 189	com/appodeal/ads/r:b	(Landroid/content/Context;)Lcom/appodeal/ads/r$a;
      //   510: astore 14
      //   512: aload 11
      //   514: ldc -65
      //   516: aload 14
      //   518: getfield 195	com/appodeal/ads/r$a:a	Ljava/lang/String;
      //   521: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   524: pop
      //   525: iload_2
      //   526: ifne +12 -> 538
      //   529: iload 4
      //   531: ifne +7 -> 538
      //   534: iload_3
      //   535: ifeq +917 -> 1452
      //   538: getstatic 200	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   541: ldc -54
      //   543: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   546: ifeq +1354 -> 1900
      //   549: ldc -52
      //   551: astore_1
      //   552: aload 11
      //   554: ldc -50
      //   556: aload_1
      //   557: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   560: pop
      //   561: aload 11
      //   563: ldc -48
      //   565: ldc -46
      //   567: iconst_2
      //   568: anewarray 212	java/lang/Object
      //   571: dup
      //   572: iconst_0
      //   573: getstatic 200	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   576: aastore
      //   577: dup
      //   578: iconst_1
      //   579: getstatic 215	android/os/Build:MODEL	Ljava/lang/String;
      //   582: aastore
      //   583: invokestatic 219	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   586: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   589: pop
      //   590: aload 11
      //   592: ldc -35
      //   594: aload 14
      //   596: getfield 223	com/appodeal/ads/r$a:b	Ljava/lang/String;
      //   599: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   602: pop
      //   603: aload 11
      //   605: ldc -31
      //   607: aload 14
      //   609: getfield 228	com/appodeal/ads/r$a:c	Z
      //   612: invokevirtual 97	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   615: pop
      //   616: aload 11
      //   618: ldc -26
      //   620: aload_0
      //   621: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   624: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   627: invokestatic 232	com/appodeal/ads/r:c	(Landroid/content/Context;)Ljava/lang/String;
      //   630: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   633: pop
      //   634: aload 11
      //   636: ldc -22
      //   638: invokestatic 240	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   641: invokevirtual 243	java/util/Locale:toString	()Ljava/lang/String;
      //   644: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   647: pop
      //   648: ldc -11
      //   650: invokestatic 251	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
      //   653: getstatic 255	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   656: invokestatic 261	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
      //   659: astore_1
      //   660: aload 11
      //   662: ldc_w 263
      //   665: new 265	java/text/SimpleDateFormat
      //   668: dup
      //   669: ldc_w 266
      //   672: getstatic 255	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   675: invokespecial 269	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
      //   678: aload_1
      //   679: invokevirtual 273	java/util/Calendar:getTime	()Ljava/util/Date;
      //   682: invokevirtual 276	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   685: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   688: pop
      //   689: aload 11
      //   691: ldc_w 278
      //   694: ldc_w 280
      //   697: invokestatic 285	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
      //   700: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   703: pop
      //   704: new 287	java/util/HashSet
      //   707: dup
      //   708: invokespecial 288	java/util/HashSet:<init>	()V
      //   711: astore_1
      //   712: aload_0
      //   713: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   716: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   719: invokestatic 293	com/appodeal/ads/k:a	(Landroid/content/Context;)Ljava/util/List;
      //   722: invokeinterface 299 1 0
      //   727: astore 14
      //   729: aload 14
      //   731: invokeinterface 305 1 0
      //   736: ifeq +538 -> 1274
      //   739: aload 14
      //   741: invokeinterface 309 1 0
      //   746: checkcast 311	com/appodeal/ads/l
      //   749: astore 15
      //   751: aload 15
      //   753: invokevirtual 314	com/appodeal/ads/l:d	()Lcom/appodeal/ads/o;
      //   756: ifnull -27 -> 729
      //   759: aload_1
      //   760: aload 15
      //   762: invokevirtual 316	com/appodeal/ads/l:a	()Ljava/lang/String;
      //   765: invokeinterface 321 2 0
      //   770: pop
      //   771: goto -42 -> 729
      //   774: astore_1
      //   775: aload_1
      //   776: invokestatic 326	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   779: invokestatic 329	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   782: astore_1
      //   783: aload_1
      //   784: aload 12
      //   786: ldc_w 331
      //   789: lconst_0
      //   790: invokeinterface 335 4 0
      //   795: invokevirtual 339	java/util/Calendar:setTimeInMillis	(J)V
      //   798: aload_1
      //   799: iconst_5
      //   800: iconst_1
      //   801: invokevirtual 342	java/util/Calendar:add	(II)V
      //   804: iload_2
      //   805: ifne +12 -> 817
      //   808: iload 4
      //   810: ifne +7 -> 817
      //   813: iload_3
      //   814: ifeq +118 -> 932
      //   817: aload_1
      //   818: invokevirtual 346	java/util/Calendar:getTimeInMillis	()J
      //   821: lstore 7
      //   823: invokestatic 349	java/lang/System:currentTimeMillis	()J
      //   826: lstore 9
      //   828: lload 7
      //   830: lload 9
      //   832: lcmp
      //   833: ifge +99 -> 932
      //   836: new 351	org/json/JSONArray
      //   839: dup
      //   840: invokespecial 352	org/json/JSONArray:<init>	()V
      //   843: astore_1
      //   844: aload 13
      //   846: iconst_0
      //   847: invokevirtual 356	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   850: astore 14
      //   852: ldc_w 358
      //   855: invokestatic 364	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
      //   858: astore 13
      //   860: aload 14
      //   862: invokeinterface 299 1 0
      //   867: astore 14
      //   869: aload 14
      //   871: invokeinterface 305 1 0
      //   876: ifeq +674 -> 1550
      //   879: aload 14
      //   881: invokeinterface 309 1 0
      //   886: checkcast 152	android/content/pm/ApplicationInfo
      //   889: getfield 367	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   892: astore 15
      //   894: aload 13
      //   896: aload 15
      //   898: invokevirtual 371	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   901: invokevirtual 376	java/util/regex/Matcher:matches	()Z
      //   904: ifne -35 -> 869
      //   907: aload 15
      //   909: ldc 106
      //   911: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   914: ifne -45 -> 869
      //   917: aload_1
      //   918: aload 15
      //   920: invokevirtual 379	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   923: pop
      //   924: goto -55 -> 869
      //   927: astore_1
      //   928: aload_1
      //   929: invokestatic 326	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   932: aload_0
      //   933: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   936: invokestatic 383	com/appodeal/ads/q:f	(Lcom/appodeal/ads/q;)Lorg/json/JSONObject;
      //   939: ifnull +19 -> 958
      //   942: aload 11
      //   944: ldc_w 385
      //   947: aload_0
      //   948: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   951: invokestatic 383	com/appodeal/ads/q:f	(Lcom/appodeal/ads/q;)Lorg/json/JSONObject;
      //   954: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   957: pop
      //   958: new 387	org/apache/http/params/BasicHttpParams
      //   961: dup
      //   962: invokespecial 388	org/apache/http/params/BasicHttpParams:<init>	()V
      //   965: astore_1
      //   966: aload_1
      //   967: sipush 20000
      //   970: invokestatic 394	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
      //   973: aload_1
      //   974: sipush 20000
      //   977: invokestatic 397	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
      //   980: new 399	org/apache/http/impl/client/DefaultHttpClient
      //   983: dup
      //   984: aload_1
      //   985: invokespecial 402	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
      //   988: astore 12
      //   990: aload_0
      //   991: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   994: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   997: ldc_w 404
      //   1000: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1003: ifne +617 -> 1620
      //   1006: aload_0
      //   1007: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1010: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1013: ldc_w 406
      //   1016: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1019: ifne +601 -> 1620
      //   1022: aload_0
      //   1023: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1026: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1029: ldc_w 408
      //   1032: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1035: ifne +585 -> 1620
      //   1038: iconst_1
      //   1039: istore_2
      //   1040: iload_2
      //   1041: ifeq +584 -> 1625
      //   1044: new 410	org/apache/http/client/methods/HttpPost
      //   1047: dup
      //   1048: ldc_w 412
      //   1051: iconst_3
      //   1052: anewarray 212	java/lang/Object
      //   1055: dup
      //   1056: iconst_0
      //   1057: invokestatic 416	com/appodeal/ads/utils/b:b	()Ljava/lang/String;
      //   1060: aastore
      //   1061: dup
      //   1062: iconst_1
      //   1063: invokestatic 419	com/appodeal/ads/utils/b:c	()I
      //   1066: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1069: aastore
      //   1070: dup
      //   1071: iconst_2
      //   1072: ldc_w 427
      //   1075: aastore
      //   1076: invokestatic 219	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   1079: invokespecial 430	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
      //   1082: astore_1
      //   1083: new 432	java/io/ByteArrayOutputStream
      //   1086: dup
      //   1087: invokespecial 433	java/io/ByteArrayOutputStream:<init>	()V
      //   1090: astore 14
      //   1092: new 435	java/util/zip/GZIPOutputStream
      //   1095: dup
      //   1096: aload 14
      //   1098: invokespecial 438	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   1101: astore 13
      //   1103: aload 13
      //   1105: aload 11
      //   1107: invokevirtual 439	org/json/JSONObject:toString	()Ljava/lang/String;
      //   1110: ldc_w 441
      //   1113: invokevirtual 445	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   1116: invokevirtual 449	java/util/zip/GZIPOutputStream:write	([B)V
      //   1119: aload 13
      //   1121: invokevirtual 452	java/util/zip/GZIPOutputStream:close	()V
      //   1124: aload_1
      //   1125: new 454	org/apache/http/entity/StringEntity
      //   1128: dup
      //   1129: aload 14
      //   1131: invokevirtual 458	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   1134: iconst_0
      //   1135: invokestatic 464	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   1138: invokespecial 465	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;)V
      //   1141: invokevirtual 469	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
      //   1144: aload 12
      //   1146: aload_1
      //   1147: invokevirtual 473	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
      //   1150: astore_1
      //   1151: aload_0
      //   1152: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1155: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1158: ldc_w 475
      //   1161: iconst_0
      //   1162: invokevirtual 479	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   1165: astore 11
      //   1167: aload_1
      //   1168: ifnonnull +521 -> 1689
      //   1171: iload_2
      //   1172: ifeq +735 -> 1907
      //   1175: aload 11
      //   1177: aload_0
      //   1178: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1181: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1184: invokeinterface 483 2 0
      //   1189: ifeq +718 -> 1907
      //   1192: ldc_w 485
      //   1195: invokestatic 487	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   1198: new 64	org/json/JSONObject
      //   1201: dup
      //   1202: aload 11
      //   1204: aload_0
      //   1205: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1208: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1211: ldc_w 489
      //   1214: invokeinterface 52 3 0
      //   1219: invokespecial 490	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1222: astore_1
      //   1223: aload_1
      //   1224: areturn
      //   1225: astore_1
      //   1226: aload_1
      //   1227: invokestatic 326	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1230: aconst_null
      //   1231: areturn
      //   1232: iconst_0
      //   1233: istore_2
      //   1234: goto -1111 -> 123
      //   1237: iconst_0
      //   1238: istore_3
      //   1239: goto -1083 -> 156
      //   1242: iconst_0
      //   1243: istore 4
      //   1245: goto -1056 -> 189
      //   1248: iconst_0
      //   1249: istore 5
      //   1251: goto -1010 -> 241
      //   1254: astore 16
      //   1256: aload 16
      //   1258: invokestatic 326	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1261: goto -865 -> 396
      //   1264: astore 15
      //   1266: aload 15
      //   1268: invokestatic 326	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1271: goto -849 -> 422
      //   1274: aload_0
      //   1275: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1278: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1281: invokestatic 493	com/appodeal/ads/s:a	(Landroid/content/Context;)Ljava/util/List;
      //   1284: invokeinterface 299 1 0
      //   1289: astore 14
      //   1291: aload 14
      //   1293: invokeinterface 305 1 0
      //   1298: ifeq +38 -> 1336
      //   1301: aload 14
      //   1303: invokeinterface 309 1 0
      //   1308: checkcast 495	com/appodeal/ads/t
      //   1311: astore 15
      //   1313: aload 15
      //   1315: invokevirtual 498	com/appodeal/ads/t:d	()Lcom/appodeal/ads/w;
      //   1318: ifnull -27 -> 1291
      //   1321: aload_1
      //   1322: aload 15
      //   1324: invokevirtual 499	com/appodeal/ads/t:a	()Ljava/lang/String;
      //   1327: invokeinterface 321 2 0
      //   1332: pop
      //   1333: goto -42 -> 1291
      //   1336: aload_0
      //   1337: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1340: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1343: invokestatic 502	com/appodeal/ads/c:a	(Landroid/content/Context;)Ljava/util/List;
      //   1346: invokeinterface 299 1 0
      //   1351: astore 14
      //   1353: aload 14
      //   1355: invokeinterface 305 1 0
      //   1360: ifeq +38 -> 1398
      //   1363: aload 14
      //   1365: invokeinterface 309 1 0
      //   1370: checkcast 504	com/appodeal/ads/e
      //   1373: astore 15
      //   1375: aload 15
      //   1377: invokevirtual 507	com/appodeal/ads/e:c	()Lcom/appodeal/ads/h;
      //   1380: ifnull -27 -> 1353
      //   1383: aload_1
      //   1384: aload 15
      //   1386: invokevirtual 508	com/appodeal/ads/e:a	()Ljava/lang/String;
      //   1389: invokeinterface 321 2 0
      //   1394: pop
      //   1395: goto -42 -> 1353
      //   1398: new 351	org/json/JSONArray
      //   1401: dup
      //   1402: invokespecial 352	org/json/JSONArray:<init>	()V
      //   1405: astore 14
      //   1407: aload_1
      //   1408: invokeinterface 509 1 0
      //   1413: astore_1
      //   1414: aload_1
      //   1415: invokeinterface 305 1 0
      //   1420: ifeq +21 -> 1441
      //   1423: aload 14
      //   1425: aload_1
      //   1426: invokeinterface 309 1 0
      //   1431: checkcast 72	java/lang/String
      //   1434: invokevirtual 379	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   1437: pop
      //   1438: goto -24 -> 1414
      //   1441: aload 11
      //   1443: ldc_w 511
      //   1446: aload 14
      //   1448: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1451: pop
      //   1452: aload 11
      //   1454: ldc_w 513
      //   1457: aload_0
      //   1458: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1461: invokestatic 515	com/appodeal/ads/q:c	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1464: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1467: pop
      //   1468: aload 11
      //   1470: ldc_w 517
      //   1473: aload_0
      //   1474: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1477: invokestatic 519	com/appodeal/ads/q:d	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1480: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1483: pop
      //   1484: aload_0
      //   1485: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1488: invokestatic 522	com/appodeal/ads/q:e	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1491: ifnull +19 -> 1510
      //   1494: aload 11
      //   1496: ldc_w 524
      //   1499: aload_0
      //   1500: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1503: invokestatic 522	com/appodeal/ads/q:e	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1506: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1509: pop
      //   1510: aload_0
      //   1511: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1514: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1517: invokestatic 526	com/appodeal/ads/r:f	(Landroid/content/Context;)Landroid/util/Pair;
      //   1520: astore_1
      //   1521: aload 11
      //   1523: ldc_w 528
      //   1526: aload_1
      //   1527: getfield 179	android/util/Pair:first	Ljava/lang/Object;
      //   1530: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1533: pop
      //   1534: aload 11
      //   1536: ldc_w 530
      //   1539: aload_1
      //   1540: getfield 184	android/util/Pair:second	Ljava/lang/Object;
      //   1543: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1546: pop
      //   1547: goto -768 -> 779
      //   1550: aload 11
      //   1552: ldc_w 532
      //   1555: aload_1
      //   1556: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1559: pop
      //   1560: aload 11
      //   1562: ldc_w 534
      //   1565: aload_0
      //   1566: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1569: invokestatic 32	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1572: invokestatic 538	com/appodeal/ads/Appodeal:getUserSettings	(Landroid/content/Context;)Lcom/appodeal/ads/UserSettings;
      //   1575: invokevirtual 543	com/appodeal/ads/UserSettings:a	()Lorg/json/JSONObject;
      //   1578: invokevirtual 92	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1581: pop
      //   1582: aload 12
      //   1584: invokeinterface 547 1 0
      //   1589: astore_1
      //   1590: aload_1
      //   1591: ldc_w 331
      //   1594: invokestatic 349	java/lang/System:currentTimeMillis	()J
      //   1597: invokeinterface 553 4 0
      //   1602: pop
      //   1603: aload_1
      //   1604: invokeinterface 556 1 0
      //   1609: goto -677 -> 932
      //   1612: astore_1
      //   1613: aload_1
      //   1614: invokestatic 326	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1617: goto -35 -> 1582
      //   1620: iconst_0
      //   1621: istore_2
      //   1622: goto -582 -> 1040
      //   1625: new 410	org/apache/http/client/methods/HttpPost
      //   1628: dup
      //   1629: ldc_w 412
      //   1632: iconst_3
      //   1633: anewarray 212	java/lang/Object
      //   1636: dup
      //   1637: iconst_0
      //   1638: invokestatic 416	com/appodeal/ads/utils/b:b	()Ljava/lang/String;
      //   1641: aastore
      //   1642: dup
      //   1643: iconst_1
      //   1644: invokestatic 419	com/appodeal/ads/utils/b:c	()I
      //   1647: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1650: aastore
      //   1651: dup
      //   1652: iconst_2
      //   1653: aload_0
      //   1654: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1657: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1660: aastore
      //   1661: invokestatic 219	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   1664: invokespecial 430	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
      //   1667: astore_1
      //   1668: goto -585 -> 1083
      //   1671: astore_1
      //   1672: aload 13
      //   1674: invokevirtual 452	java/util/zip/GZIPOutputStream:close	()V
      //   1677: aload_1
      //   1678: athrow
      //   1679: astore_1
      //   1680: aload_1
      //   1681: invokestatic 326	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1684: aconst_null
      //   1685: astore_1
      //   1686: goto -535 -> 1151
      //   1689: aload_1
      //   1690: invokeinterface 562 1 0
      //   1695: invokeinterface 567 1 0
      //   1700: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1703: astore 12
      //   1705: aload 12
      //   1707: invokevirtual 570	java/lang/Integer:intValue	()I
      //   1710: sipush 200
      //   1713: if_icmplt +14 -> 1727
      //   1716: aload 12
      //   1718: invokevirtual 570	java/lang/Integer:intValue	()I
      //   1721: sipush 300
      //   1724: if_icmplt +59 -> 1783
      //   1727: iload_2
      //   1728: ifeq +53 -> 1781
      //   1731: aload 11
      //   1733: aload_0
      //   1734: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1737: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1740: invokeinterface 483 2 0
      //   1745: ifeq +36 -> 1781
      //   1748: ldc_w 485
      //   1751: invokestatic 487	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   1754: new 64	org/json/JSONObject
      //   1757: dup
      //   1758: aload 11
      //   1760: aload_0
      //   1761: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1764: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1767: ldc_w 489
      //   1770: invokeinterface 52 3 0
      //   1775: invokespecial 490	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1778: astore_1
      //   1779: aload_1
      //   1780: areturn
      //   1781: aconst_null
      //   1782: areturn
      //   1783: aload_1
      //   1784: invokeinterface 574 1 0
      //   1789: invokestatic 579	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;)Ljava/lang/String;
      //   1792: astore_1
      //   1793: aload_1
      //   1794: ifnull +115 -> 1909
      //   1797: aload_1
      //   1798: invokevirtual 582	java/lang/String:isEmpty	()Z
      //   1801: ifne +108 -> 1909
      //   1804: aload_1
      //   1805: ldc_w 584
      //   1808: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1811: ifeq +6 -> 1817
      //   1814: goto +95 -> 1909
      //   1817: new 64	org/json/JSONObject
      //   1820: dup
      //   1821: aload_1
      //   1822: invokespecial 490	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1825: astore 12
      //   1827: aload 12
      //   1829: invokestatic 587	com/appodeal/ads/Appodeal:a	(Lorg/json/JSONObject;)V
      //   1832: iload_2
      //   1833: ifeq +48 -> 1881
      //   1836: aload 11
      //   1838: invokeinterface 547 1 0
      //   1843: astore 11
      //   1845: aload 11
      //   1847: aload_0
      //   1848: getfield 13	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1851: invokestatic 68	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   1854: aload_1
      //   1855: invokeinterface 591 3 0
      //   1860: pop
      //   1861: aload 11
      //   1863: invokeinterface 556 1 0
      //   1868: aload 12
      //   1870: areturn
      //   1871: astore_1
      //   1872: aload_1
      //   1873: invokestatic 326	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1876: aconst_null
      //   1877: areturn
      //   1878: goto -1797 -> 81
      //   1881: aload 12
      //   1883: areturn
      //   1884: iconst_1
      //   1885: istore_2
      //   1886: goto -1763 -> 123
      //   1889: iconst_1
      //   1890: istore_3
      //   1891: goto -1735 -> 156
      //   1894: iconst_1
      //   1895: istore 4
      //   1897: goto -1708 -> 189
      //   1900: ldc_w 593
      //   1903: astore_1
      //   1904: goto -1352 -> 552
      //   1907: aconst_null
      //   1908: areturn
      //   1909: aconst_null
      //   1910: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	1911	0	this	b
      //   0	1911	1	paramVarArgs	Void[]
      //   251	1635	2	i	int
      //   265	1626	3	j	int
      //   279	1617	4	k	int
      //   239	1011	5	m	int
      //   231	3	6	bool	boolean
      //   821	8	7	l1	long
      //   826	5	9	l2	long
      //   88	1774	11	localObject1	Object
      //   10	1872	12	localObject2	Object
      //   22	1651	13	localObject3	Object
      //   64	1383	14	localObject4	Object
      //   34	885	15	localObject5	Object
      //   1264	3	15	localException	Exception
      //   1311	74	15	localObject6	Object
      //   1254	3	16	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      // Exception table:
      //   from	to	target	type
      //   241	251	774	org/json/JSONException
      //   255	265	774	org/json/JSONException
      //   269	279	774	org/json/JSONException
      //   284	294	774	org/json/JSONException
      //   299	308	774	org/json/JSONException
      //   308	323	774	org/json/JSONException
      //   323	377	774	org/json/JSONException
      //   377	396	774	org/json/JSONException
      //   396	422	774	org/json/JSONException
      //   422	525	774	org/json/JSONException
      //   538	549	774	org/json/JSONException
      //   552	729	774	org/json/JSONException
      //   729	771	774	org/json/JSONException
      //   1256	1261	774	org/json/JSONException
      //   1266	1271	774	org/json/JSONException
      //   1274	1291	774	org/json/JSONException
      //   1291	1333	774	org/json/JSONException
      //   1336	1353	774	org/json/JSONException
      //   1353	1395	774	org/json/JSONException
      //   1398	1414	774	org/json/JSONException
      //   1414	1438	774	org/json/JSONException
      //   1441	1452	774	org/json/JSONException
      //   1452	1510	774	org/json/JSONException
      //   1510	1547	774	org/json/JSONException
      //   836	869	927	java/lang/Exception
      //   869	924	927	java/lang/Exception
      //   1550	1560	927	java/lang/Exception
      //   1582	1609	927	java/lang/Exception
      //   1613	1617	927	java/lang/Exception
      //   0	36	1225	java/lang/Exception
      //   43	66	1225	java/lang/Exception
      //   70	81	1225	java/lang/Exception
      //   81	120	1225	java/lang/Exception
      //   123	153	1225	java/lang/Exception
      //   156	186	1225	java/lang/Exception
      //   189	233	1225	java/lang/Exception
      //   241	251	1225	java/lang/Exception
      //   255	265	1225	java/lang/Exception
      //   269	279	1225	java/lang/Exception
      //   284	294	1225	java/lang/Exception
      //   299	308	1225	java/lang/Exception
      //   308	323	1225	java/lang/Exception
      //   323	377	1225	java/lang/Exception
      //   377	396	1225	java/lang/Exception
      //   422	525	1225	java/lang/Exception
      //   538	549	1225	java/lang/Exception
      //   552	729	1225	java/lang/Exception
      //   729	771	1225	java/lang/Exception
      //   775	779	1225	java/lang/Exception
      //   779	804	1225	java/lang/Exception
      //   817	828	1225	java/lang/Exception
      //   928	932	1225	java/lang/Exception
      //   932	958	1225	java/lang/Exception
      //   958	1038	1225	java/lang/Exception
      //   1044	1083	1225	java/lang/Exception
      //   1083	1103	1225	java/lang/Exception
      //   1119	1151	1225	java/lang/Exception
      //   1151	1167	1225	java/lang/Exception
      //   1175	1223	1225	java/lang/Exception
      //   1256	1261	1225	java/lang/Exception
      //   1266	1271	1225	java/lang/Exception
      //   1274	1291	1225	java/lang/Exception
      //   1291	1333	1225	java/lang/Exception
      //   1336	1353	1225	java/lang/Exception
      //   1353	1395	1225	java/lang/Exception
      //   1398	1414	1225	java/lang/Exception
      //   1414	1438	1225	java/lang/Exception
      //   1441	1452	1225	java/lang/Exception
      //   1452	1510	1225	java/lang/Exception
      //   1510	1547	1225	java/lang/Exception
      //   1625	1668	1225	java/lang/Exception
      //   1672	1679	1225	java/lang/Exception
      //   1680	1684	1225	java/lang/Exception
      //   1689	1727	1225	java/lang/Exception
      //   1731	1779	1225	java/lang/Exception
      //   1783	1793	1225	java/lang/Exception
      //   1797	1814	1225	java/lang/Exception
      //   1817	1832	1225	java/lang/Exception
      //   1836	1868	1225	java/lang/Exception
      //   1872	1876	1225	java/lang/Exception
      //   377	396	1254	android/content/pm/PackageManager$NameNotFoundException
      //   396	422	1264	java/lang/Exception
      //   1560	1582	1612	java/lang/Exception
      //   1103	1119	1671	finally
      //   1083	1103	1679	java/io/IOException
      //   1119	1151	1679	java/io/IOException
      //   1672	1679	1679	java/io/IOException
      //   1783	1793	1871	org/json/JSONException
      //   1797	1814	1871	org/json/JSONException
      //   1817	1832	1871	org/json/JSONException
      //   1836	1868	1871	org/json/JSONException
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      super.onPostExecute(paramJSONObject);
      try
      {
        if (q.g(q.this) != null)
        {
          if (paramJSONObject == null)
          {
            q.g(q.this).a(q.h(q.this));
            return;
          }
          q.g(q.this).a(paramJSONObject, q.h(q.this), q.b(q.this));
          return;
        }
      }
      catch (Exception paramJSONObject)
      {
        Appodeal.a(paramJSONObject);
      }
    }
  }
}
