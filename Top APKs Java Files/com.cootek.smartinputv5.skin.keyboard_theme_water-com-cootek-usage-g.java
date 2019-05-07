package com.cootek.usage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class g
{
  private static final int a = 0;
  private static final int b = 1;
  private static final int c = 2;
  private static final int d = 3;
  private static final int e = 4;
  private static final int f = 5;
  private static final String g = "noah_info";
  private AbsUsageAssist h;
  
  g() {}
  
  g(AbsUsageAssist paramAbsUsageAssist)
  {
    this.h = paramAbsUsageAssist;
  }
  
  static int a()
  {
    return 5;
  }
  
  static String a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("error info value: " + paramInt);
    case 0: 
      return "noah_reserve_00";
    case 1: 
      return "noah_reserve_01";
    case 2: 
      return "noah_reserve_02";
    case 3: 
      return "noah_reserve_03";
    }
    return "noah_reserve_04";
  }
  
  static boolean a(Context paramContext)
  {
    if (ConnectivityManager.isNetworkTypeValid(1)) {
      return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
    return false;
  }
  
  static String b()
  {
    return "noah_info";
  }
  
  /* Error */
  private m c()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: new 95	com/cootek/usage/m
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 98	com/cootek/usage/m:<init>	(Lcom/cootek/usage/g;)V
    //   14: astore 7
    //   16: aload_0
    //   17: getfield 31	com/cootek/usage/g:h	Lcom/cootek/usage/AbsUsageAssist;
    //   20: invokevirtual 104	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   23: invokevirtual 108	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   26: astore 9
    //   28: new 110	java/util/Hashtable
    //   31: dup
    //   32: invokespecial 111	java/util/Hashtable:<init>	()V
    //   35: astore 8
    //   37: aload 9
    //   39: getstatic 117	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
    //   42: iconst_2
    //   43: anewarray 119	java/lang/String
    //   46: dup
    //   47: iconst_0
    //   48: ldc 121
    //   50: aastore
    //   51: dup
    //   52: iconst_1
    //   53: ldc 123
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore_3
    //   63: aload_3
    //   64: ifnull +197 -> 261
    //   67: aload_3
    //   68: astore 4
    //   70: aload_3
    //   71: invokeinterface 134 1 0
    //   76: ifeq +185 -> 261
    //   79: aload_3
    //   80: astore 4
    //   82: new 136	com/cootek/usage/i
    //   85: dup
    //   86: aload_0
    //   87: iconst_0
    //   88: invokespecial 139	com/cootek/usage/i:<init>	(Lcom/cootek/usage/g;B)V
    //   91: astore 10
    //   93: aload_3
    //   94: astore 4
    //   96: aload 10
    //   98: aload_3
    //   99: iconst_0
    //   100: invokeinterface 143 2 0
    //   105: putfield 146	com/cootek/usage/i:a	J
    //   108: aload_3
    //   109: astore 4
    //   111: aload 10
    //   113: aload_3
    //   114: iconst_1
    //   115: invokeinterface 149 2 0
    //   120: putfield 151	com/cootek/usage/i:b	Ljava/lang/String;
    //   123: aload_3
    //   124: astore 4
    //   126: aload 10
    //   128: new 153	java/util/HashSet
    //   131: dup
    //   132: invokespecial 154	java/util/HashSet:<init>	()V
    //   135: putfield 157	com/cootek/usage/i:c	Ljava/util/HashSet;
    //   138: aload_3
    //   139: astore 4
    //   141: aload 10
    //   143: new 153	java/util/HashSet
    //   146: dup
    //   147: invokespecial 154	java/util/HashSet:<init>	()V
    //   150: putfield 159	com/cootek/usage/i:d	Ljava/util/HashSet;
    //   153: aload_3
    //   154: astore 4
    //   156: aload 10
    //   158: new 153	java/util/HashSet
    //   161: dup
    //   162: invokespecial 154	java/util/HashSet:<init>	()V
    //   165: putfield 161	com/cootek/usage/i:e	Ljava/util/HashSet;
    //   168: aload_3
    //   169: astore 4
    //   171: aload 10
    //   173: new 153	java/util/HashSet
    //   176: dup
    //   177: invokespecial 154	java/util/HashSet:<init>	()V
    //   180: putfield 163	com/cootek/usage/i:f	Ljava/util/HashSet;
    //   183: aload_3
    //   184: astore 4
    //   186: aload 10
    //   188: new 153	java/util/HashSet
    //   191: dup
    //   192: invokespecial 154	java/util/HashSet:<init>	()V
    //   195: putfield 165	com/cootek/usage/i:g	Ljava/util/HashSet;
    //   198: aload_3
    //   199: astore 4
    //   201: aload 10
    //   203: new 153	java/util/HashSet
    //   206: dup
    //   207: invokespecial 154	java/util/HashSet:<init>	()V
    //   210: putfield 167	com/cootek/usage/i:h	Ljava/util/HashSet;
    //   213: aload_3
    //   214: astore 4
    //   216: aload 10
    //   218: new 153	java/util/HashSet
    //   221: dup
    //   222: invokespecial 154	java/util/HashSet:<init>	()V
    //   225: putfield 170	com/cootek/usage/i:i	Ljava/util/HashSet;
    //   228: aload_3
    //   229: astore 4
    //   231: aload 8
    //   233: aload 10
    //   235: getfield 146	com/cootek/usage/i:a	J
    //   238: invokestatic 176	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   241: aload 10
    //   243: invokevirtual 180	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   246: pop
    //   247: aload_3
    //   248: astore 4
    //   250: aload_3
    //   251: invokeinterface 183 1 0
    //   256: istore_2
    //   257: iload_2
    //   258: ifne -179 -> 79
    //   261: aload_3
    //   262: ifnull +9 -> 271
    //   265: aload_3
    //   266: invokeinterface 186 1 0
    //   271: aload 9
    //   273: getstatic 189	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   276: bipush 12
    //   278: anewarray 119	java/lang/String
    //   281: dup
    //   282: iconst_0
    //   283: ldc -65
    //   285: aastore
    //   286: dup
    //   287: iconst_1
    //   288: ldc -63
    //   290: aastore
    //   291: dup
    //   292: iconst_2
    //   293: ldc -61
    //   295: aastore
    //   296: dup
    //   297: iconst_3
    //   298: ldc -59
    //   300: aastore
    //   301: dup
    //   302: iconst_4
    //   303: ldc -57
    //   305: aastore
    //   306: dup
    //   307: iconst_5
    //   308: ldc -55
    //   310: aastore
    //   311: dup
    //   312: bipush 6
    //   314: ldc -53
    //   316: aastore
    //   317: dup
    //   318: bipush 7
    //   320: ldc -51
    //   322: aastore
    //   323: dup
    //   324: bipush 8
    //   326: ldc -49
    //   328: aastore
    //   329: dup
    //   330: bipush 9
    //   332: ldc -47
    //   334: aastore
    //   335: dup
    //   336: bipush 10
    //   338: ldc -45
    //   340: aastore
    //   341: dup
    //   342: bipush 11
    //   344: ldc -43
    //   346: aastore
    //   347: ldc -41
    //   349: bipush 7
    //   351: anewarray 119	java/lang/String
    //   354: dup
    //   355: iconst_0
    //   356: ldc -39
    //   358: aastore
    //   359: dup
    //   360: iconst_1
    //   361: ldc -37
    //   363: aastore
    //   364: dup
    //   365: iconst_2
    //   366: ldc -35
    //   368: aastore
    //   369: dup
    //   370: iconst_3
    //   371: ldc -33
    //   373: aastore
    //   374: dup
    //   375: iconst_4
    //   376: ldc -31
    //   378: aastore
    //   379: dup
    //   380: iconst_5
    //   381: ldc -29
    //   383: aastore
    //   384: dup
    //   385: bipush 6
    //   387: ldc -27
    //   389: aastore
    //   390: aconst_null
    //   391: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   394: astore 4
    //   396: aload 4
    //   398: ifnull +53 -> 451
    //   401: aload 4
    //   403: invokeinterface 134 1 0
    //   408: istore_2
    //   409: iload_2
    //   410: ifeq +41 -> 451
    //   413: aload 8
    //   415: aload 4
    //   417: iconst_0
    //   418: invokeinterface 143 2 0
    //   423: invokestatic 176	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   426: invokevirtual 233	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   429: checkcast 136	com/cootek/usage/i
    //   432: astore 5
    //   434: aload 5
    //   436: ifnonnull +218 -> 654
    //   439: aload 4
    //   441: invokeinterface 183 1 0
    //   446: istore_2
    //   447: iload_2
    //   448: ifne -35 -> 413
    //   451: aload 4
    //   453: ifnull +10 -> 463
    //   456: aload 4
    //   458: invokeinterface 186 1 0
    //   463: new 235	org/json/JSONArray
    //   466: dup
    //   467: invokespecial 236	org/json/JSONArray:<init>	()V
    //   470: astore_3
    //   471: aload 8
    //   473: invokevirtual 240	java/util/Hashtable:values	()Ljava/util/Collection;
    //   476: invokeinterface 246 1 0
    //   481: astore 4
    //   483: aload 4
    //   485: invokeinterface 251 1 0
    //   490: ifne +1179 -> 1669
    //   493: new 253	com/cootek/usage/UsageData
    //   496: dup
    //   497: invokespecial 254	com/cootek/usage/UsageData:<init>	()V
    //   500: astore 4
    //   502: aload 4
    //   504: ldc 21
    //   506: putfield 257	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   509: aload 4
    //   511: iconst_0
    //   512: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   515: putfield 262	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   518: aload 4
    //   520: aload_3
    //   521: invokevirtual 263	org/json/JSONArray:toString	()Ljava/lang/String;
    //   524: putfield 266	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   527: aload 7
    //   529: aload 4
    //   531: putfield 269	com/cootek/usage/m:a	Lcom/cootek/usage/UsageData;
    //   534: aload 7
    //   536: iconst_0
    //   537: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   540: putfield 271	com/cootek/usage/m:c	Ljava/lang/String;
    //   543: aload 7
    //   545: areturn
    //   546: astore_3
    //   547: aconst_null
    //   548: astore_3
    //   549: aload 7
    //   551: iconst_0
    //   552: putfield 274	com/cootek/usage/m:d	Z
    //   555: aload_3
    //   556: ifnull +9 -> 565
    //   559: aload_3
    //   560: invokeinterface 186 1 0
    //   565: aload 7
    //   567: areturn
    //   568: astore_3
    //   569: aload_3
    //   570: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   573: goto -8 -> 565
    //   576: astore 5
    //   578: aconst_null
    //   579: astore_3
    //   580: aload_3
    //   581: astore 4
    //   583: aload 5
    //   585: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   588: aload_3
    //   589: astore 4
    //   591: aload 7
    //   593: iconst_0
    //   594: putfield 274	com/cootek/usage/m:d	Z
    //   597: aload_3
    //   598: ifnull +9 -> 607
    //   601: aload_3
    //   602: invokeinterface 186 1 0
    //   607: aload 7
    //   609: areturn
    //   610: astore_3
    //   611: aload_3
    //   612: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   615: goto -8 -> 607
    //   618: astore_3
    //   619: aconst_null
    //   620: astore 4
    //   622: aload 4
    //   624: ifnull +10 -> 634
    //   627: aload 4
    //   629: invokeinterface 186 1 0
    //   634: aload_3
    //   635: athrow
    //   636: astore 4
    //   638: aload 4
    //   640: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   643: goto -9 -> 634
    //   646: astore_3
    //   647: aload_3
    //   648: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   651: goto -380 -> 271
    //   654: aload 4
    //   656: iconst_1
    //   657: invokeinterface 149 2 0
    //   662: astore_3
    //   663: ldc -39
    //   665: aload_3
    //   666: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   669: ifeq +58 -> 727
    //   672: aload 4
    //   674: iconst_2
    //   675: invokeinterface 149 2 0
    //   680: astore_3
    //   681: aload 5
    //   683: getfield 157	com/cootek/usage/i:c	Ljava/util/HashSet;
    //   686: aload_3
    //   687: invokevirtual 284	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   690: pop
    //   691: goto -252 -> 439
    //   694: astore_3
    //   695: aload_3
    //   696: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   699: goto -260 -> 439
    //   702: astore_3
    //   703: aload 4
    //   705: astore_3
    //   706: aload 7
    //   708: iconst_0
    //   709: putfield 274	com/cootek/usage/m:d	Z
    //   712: aload 4
    //   714: ifnull +10 -> 724
    //   717: aload 4
    //   719: invokeinterface 186 1 0
    //   724: aload 7
    //   726: areturn
    //   727: ldc -37
    //   729: aload_3
    //   730: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   733: ifeq +134 -> 867
    //   736: new 286	com/cootek/usage/j
    //   739: dup
    //   740: aload_0
    //   741: iconst_0
    //   742: invokespecial 287	com/cootek/usage/j:<init>	(Lcom/cootek/usage/g;B)V
    //   745: astore 6
    //   747: aload 6
    //   749: aload 4
    //   751: iconst_2
    //   752: invokeinterface 149 2 0
    //   757: putfield 289	com/cootek/usage/j:a	Ljava/lang/String;
    //   760: aload 4
    //   762: iconst_3
    //   763: invokeinterface 293 2 0
    //   768: tableswitch	default:+2037->2805, 0:+2065->2833, 1:+92->860, 2:+2044->2812, 3:+2051->2819, 4:+2058->2826
    //   804: aload 6
    //   806: aload_3
    //   807: putfield 294	com/cootek/usage/j:b	Ljava/lang/String;
    //   810: aload 6
    //   812: getfield 294	com/cootek/usage/j:b	Ljava/lang/String;
    //   815: ifnonnull +16 -> 831
    //   818: aload 6
    //   820: aload 4
    //   822: iconst_4
    //   823: invokeinterface 149 2 0
    //   828: putfield 294	com/cootek/usage/j:b	Ljava/lang/String;
    //   831: aload 5
    //   833: getfield 159	com/cootek/usage/i:d	Ljava/util/HashSet;
    //   836: aload 6
    //   838: invokevirtual 284	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   841: pop
    //   842: goto -403 -> 439
    //   845: astore_3
    //   846: aload 4
    //   848: ifnull +10 -> 858
    //   851: aload 4
    //   853: invokeinterface 186 1 0
    //   858: aload_3
    //   859: athrow
    //   860: ldc_w 296
    //   863: astore_3
    //   864: goto -60 -> 804
    //   867: ldc -35
    //   869: aload_3
    //   870: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   873: ifeq +128 -> 1001
    //   876: new 298	com/cootek/usage/n
    //   879: dup
    //   880: aload_0
    //   881: iconst_0
    //   882: invokespecial 299	com/cootek/usage/n:<init>	(Lcom/cootek/usage/g;B)V
    //   885: astore 6
    //   887: aload 6
    //   889: aload 4
    //   891: iconst_2
    //   892: invokeinterface 149 2 0
    //   897: putfield 300	com/cootek/usage/n:b	Ljava/lang/String;
    //   900: aload 6
    //   902: aload 4
    //   904: iconst_5
    //   905: invokeinterface 149 2 0
    //   910: putfield 301	com/cootek/usage/n:c	Ljava/lang/String;
    //   913: aload 6
    //   915: aload 4
    //   917: bipush 6
    //   919: invokeinterface 149 2 0
    //   924: putfield 303	com/cootek/usage/n:d	Ljava/lang/String;
    //   927: aload 4
    //   929: iconst_3
    //   930: invokeinterface 293 2 0
    //   935: tableswitch	default:+1903->2838, 0:+1924->2859, 1:+1910->2845, 2:+1917->2852
    //   960: aload 6
    //   962: aload_3
    //   963: putfield 304	com/cootek/usage/n:a	Ljava/lang/String;
    //   966: aload 6
    //   968: getfield 304	com/cootek/usage/n:a	Ljava/lang/String;
    //   971: ifnonnull +16 -> 987
    //   974: aload 6
    //   976: aload 4
    //   978: iconst_4
    //   979: invokeinterface 149 2 0
    //   984: putfield 304	com/cootek/usage/n:a	Ljava/lang/String;
    //   987: aload 5
    //   989: getfield 161	com/cootek/usage/i:e	Ljava/util/HashSet;
    //   992: aload 6
    //   994: invokevirtual 284	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   997: pop
    //   998: goto -559 -> 439
    //   1001: ldc -33
    //   1003: aload_3
    //   1004: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1007: ifeq +199 -> 1206
    //   1010: new 306	com/cootek/usage/l
    //   1013: dup
    //   1014: aload_0
    //   1015: iconst_0
    //   1016: invokespecial 307	com/cootek/usage/l:<init>	(Lcom/cootek/usage/g;B)V
    //   1019: astore 6
    //   1021: aload 6
    //   1023: aload 4
    //   1025: iconst_2
    //   1026: invokeinterface 149 2 0
    //   1031: putfield 308	com/cootek/usage/l:a	Ljava/lang/String;
    //   1034: aload 4
    //   1036: iconst_3
    //   1037: invokeinterface 293 2 0
    //   1042: tableswitch	default:+1822->2864, 0:+1857->2899, 1:+1836->2878, 2:+1843->2885, 3:+1850->2892
    //   1072: aload 6
    //   1074: aload_3
    //   1075: putfield 309	com/cootek/usage/l:b	Ljava/lang/String;
    //   1078: aload 6
    //   1080: getfield 309	com/cootek/usage/l:b	Ljava/lang/String;
    //   1083: ifnonnull +16 -> 1099
    //   1086: aload 6
    //   1088: aload 4
    //   1090: iconst_4
    //   1091: invokeinterface 149 2 0
    //   1096: putfield 309	com/cootek/usage/l:b	Ljava/lang/String;
    //   1099: aload 4
    //   1101: bipush 6
    //   1103: invokeinterface 293 2 0
    //   1108: tableswitch	default:+1763->2871, -1:+1803->2911, 0:+1796->2904, 1:+1829->2937, 2:+1857->2965, 3:+1850->2958, 4:+1843->2951, 5:+1808->2916, 6:+1815->2923, 7:+1822->2930, 8:+1836->2944
    //   1164: aload 6
    //   1166: aload_3
    //   1167: putfield 310	com/cootek/usage/l:c	Ljava/lang/String;
    //   1170: aload 6
    //   1172: getfield 310	com/cootek/usage/l:c	Ljava/lang/String;
    //   1175: ifnonnull +17 -> 1192
    //   1178: aload 6
    //   1180: aload 4
    //   1182: bipush 7
    //   1184: invokeinterface 149 2 0
    //   1189: putfield 310	com/cootek/usage/l:c	Ljava/lang/String;
    //   1192: aload 5
    //   1194: getfield 163	com/cootek/usage/i:f	Ljava/util/HashSet;
    //   1197: aload 6
    //   1199: invokevirtual 284	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1202: pop
    //   1203: goto -764 -> 439
    //   1206: ldc -31
    //   1208: aload_3
    //   1209: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1212: ifeq +105 -> 1317
    //   1215: new 312	com/cootek/usage/h
    //   1218: dup
    //   1219: aload_0
    //   1220: iconst_0
    //   1221: invokespecial 313	com/cootek/usage/h:<init>	(Lcom/cootek/usage/g;B)V
    //   1224: astore 6
    //   1226: aload 6
    //   1228: aload 4
    //   1230: iconst_2
    //   1231: invokeinterface 149 2 0
    //   1236: putfield 314	com/cootek/usage/h:a	Ljava/lang/String;
    //   1239: aload 4
    //   1241: iconst_3
    //   1242: invokeinterface 293 2 0
    //   1247: tableswitch	default:+1725->2972, 0:+1753->3000, 1:+1732->2979, 2:+1739->2986, 3:+1746->2993
    //   1276: aload 6
    //   1278: aload_3
    //   1279: putfield 315	com/cootek/usage/h:b	Ljava/lang/String;
    //   1282: aload 6
    //   1284: getfield 315	com/cootek/usage/h:b	Ljava/lang/String;
    //   1287: ifnonnull +16 -> 1303
    //   1290: aload 6
    //   1292: aload 4
    //   1294: iconst_4
    //   1295: invokeinterface 149 2 0
    //   1300: putfield 315	com/cootek/usage/h:b	Ljava/lang/String;
    //   1303: aload 5
    //   1305: getfield 165	com/cootek/usage/i:g	Ljava/util/HashSet;
    //   1308: aload 6
    //   1310: invokevirtual 284	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1313: pop
    //   1314: goto -875 -> 439
    //   1317: ldc -29
    //   1319: aload_3
    //   1320: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1323: ifeq +106 -> 1429
    //   1326: new 317	com/cootek/usage/k
    //   1329: dup
    //   1330: aload_0
    //   1331: iconst_0
    //   1332: invokespecial 318	com/cootek/usage/k:<init>	(Lcom/cootek/usage/g;B)V
    //   1335: astore 6
    //   1337: aload 6
    //   1339: aload 4
    //   1341: iconst_2
    //   1342: invokeinterface 149 2 0
    //   1347: putfield 319	com/cootek/usage/k:a	Ljava/lang/String;
    //   1350: aload 4
    //   1352: iconst_3
    //   1353: invokeinterface 293 2 0
    //   1358: tableswitch	default:+1647->3005, 0:+1675->3033, 1:+1654->3012, 2:+1668->3026, 3:+1661->3019
    //   1388: aload 6
    //   1390: aload_3
    //   1391: putfield 320	com/cootek/usage/k:b	Ljava/lang/String;
    //   1394: aload 6
    //   1396: getfield 320	com/cootek/usage/k:b	Ljava/lang/String;
    //   1399: ifnonnull +16 -> 1415
    //   1402: aload 6
    //   1404: aload 4
    //   1406: iconst_4
    //   1407: invokeinterface 149 2 0
    //   1412: putfield 320	com/cootek/usage/k:b	Ljava/lang/String;
    //   1415: aload 5
    //   1417: getfield 167	com/cootek/usage/i:h	Ljava/util/HashSet;
    //   1420: aload 6
    //   1422: invokevirtual 284	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1425: pop
    //   1426: goto -987 -> 439
    //   1429: ldc -27
    //   1431: aload_3
    //   1432: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1435: ifeq -996 -> 439
    //   1438: new 322	com/cootek/usage/o
    //   1441: dup
    //   1442: aload_0
    //   1443: iconst_0
    //   1444: invokespecial 323	com/cootek/usage/o:<init>	(Lcom/cootek/usage/g;B)V
    //   1447: astore 6
    //   1449: aload 6
    //   1451: aload 4
    //   1453: iconst_2
    //   1454: invokeinterface 149 2 0
    //   1459: putfield 324	com/cootek/usage/o:a	Ljava/lang/String;
    //   1462: aload 4
    //   1464: iconst_3
    //   1465: invokeinterface 293 2 0
    //   1470: tableswitch	default:+1568->3038, 0:+122->1592, 1:+1575->3045, 2:+1582->3052, 3:+1589->3059, 4:+1596->3066, 5:+1603->3073, 6:+1610->3080, 7:+1617->3087, 8:+1624->3094, 9:+1631->3101, 10:+1638->3108, 11:+1645->3115, 12:+1652->3122, 13:+1659->3129, 14:+115->1585
    //   1544: aload 6
    //   1546: aload_3
    //   1547: putfield 325	com/cootek/usage/o:b	Ljava/lang/String;
    //   1550: aload 6
    //   1552: getfield 325	com/cootek/usage/o:b	Ljava/lang/String;
    //   1555: ifnonnull +16 -> 1571
    //   1558: aload 6
    //   1560: aload 4
    //   1562: iconst_4
    //   1563: invokeinterface 149 2 0
    //   1568: putfield 325	com/cootek/usage/o:b	Ljava/lang/String;
    //   1571: aload 5
    //   1573: getfield 170	com/cootek/usage/i:i	Ljava/util/HashSet;
    //   1576: aload 6
    //   1578: invokevirtual 284	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1581: pop
    //   1582: goto -1143 -> 439
    //   1585: ldc_w 327
    //   1588: astore_3
    //   1589: goto -45 -> 1544
    //   1592: aconst_null
    //   1593: astore_3
    //   1594: goto -50 -> 1544
    //   1597: astore_3
    //   1598: aload_3
    //   1599: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   1602: goto -878 -> 724
    //   1605: astore 5
    //   1607: aload 6
    //   1609: astore 4
    //   1611: aload 4
    //   1613: astore_3
    //   1614: aload 5
    //   1616: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   1619: aload 4
    //   1621: astore_3
    //   1622: aload 7
    //   1624: iconst_0
    //   1625: putfield 274	com/cootek/usage/m:d	Z
    //   1628: aload 4
    //   1630: ifnull +10 -> 1640
    //   1633: aload 4
    //   1635: invokeinterface 186 1 0
    //   1640: aload 7
    //   1642: areturn
    //   1643: astore_3
    //   1644: aload_3
    //   1645: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   1648: goto -8 -> 1640
    //   1651: astore 4
    //   1653: aload 4
    //   1655: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   1658: goto -800 -> 858
    //   1661: astore_3
    //   1662: aload_3
    //   1663: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   1666: goto -1203 -> 463
    //   1669: aload 4
    //   1671: invokeinterface 331 1 0
    //   1676: checkcast 136	com/cootek/usage/i
    //   1679: astore 6
    //   1681: new 333	org/json/JSONObject
    //   1684: dup
    //   1685: invokespecial 334	org/json/JSONObject:<init>	()V
    //   1688: astore 5
    //   1690: aload 5
    //   1692: ldc_w 336
    //   1695: aload 6
    //   1697: getfield 151	com/cootek/usage/i:b	Ljava/lang/String;
    //   1700: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1703: pop
    //   1704: aload 6
    //   1706: getfield 157	com/cootek/usage/i:c	Ljava/util/HashSet;
    //   1709: invokevirtual 342	java/util/HashSet:isEmpty	()Z
    //   1712: ifne +51 -> 1763
    //   1715: new 235	org/json/JSONArray
    //   1718: dup
    //   1719: invokespecial 236	org/json/JSONArray:<init>	()V
    //   1722: astore 8
    //   1724: aload 6
    //   1726: getfield 157	com/cootek/usage/i:c	Ljava/util/HashSet;
    //   1729: invokevirtual 343	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1732: astore 9
    //   1734: aload 9
    //   1736: invokeinterface 251 1 0
    //   1741: ifne +384 -> 2125
    //   1744: aload 8
    //   1746: invokevirtual 346	org/json/JSONArray:length	()I
    //   1749: ifle +14 -> 1763
    //   1752: aload 5
    //   1754: ldc_w 348
    //   1757: aload 8
    //   1759: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1762: pop
    //   1763: aload 6
    //   1765: getfield 159	com/cootek/usage/i:d	Ljava/util/HashSet;
    //   1768: invokevirtual 342	java/util/HashSet:isEmpty	()Z
    //   1771: ifne +51 -> 1822
    //   1774: new 235	org/json/JSONArray
    //   1777: dup
    //   1778: invokespecial 236	org/json/JSONArray:<init>	()V
    //   1781: astore 8
    //   1783: aload 6
    //   1785: getfield 159	com/cootek/usage/i:d	Ljava/util/HashSet;
    //   1788: invokevirtual 343	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1791: astore 9
    //   1793: aload 9
    //   1795: invokeinterface 251 1 0
    //   1800: ifne +354 -> 2154
    //   1803: aload 8
    //   1805: invokevirtual 346	org/json/JSONArray:length	()I
    //   1808: ifle +14 -> 1822
    //   1811: aload 5
    //   1813: ldc_w 350
    //   1816: aload 8
    //   1818: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1821: pop
    //   1822: aload 6
    //   1824: getfield 161	com/cootek/usage/i:e	Ljava/util/HashSet;
    //   1827: invokevirtual 342	java/util/HashSet:isEmpty	()Z
    //   1830: ifne +51 -> 1881
    //   1833: new 235	org/json/JSONArray
    //   1836: dup
    //   1837: invokespecial 236	org/json/JSONArray:<init>	()V
    //   1840: astore 8
    //   1842: aload 6
    //   1844: getfield 161	com/cootek/usage/i:e	Ljava/util/HashSet;
    //   1847: invokevirtual 343	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1850: astore 9
    //   1852: aload 9
    //   1854: invokeinterface 251 1 0
    //   1859: ifne +377 -> 2236
    //   1862: aload 8
    //   1864: invokevirtual 346	org/json/JSONArray:length	()I
    //   1867: ifle +14 -> 1881
    //   1870: aload 5
    //   1872: ldc_w 352
    //   1875: aload 8
    //   1877: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1880: pop
    //   1881: aload 6
    //   1883: getfield 163	com/cootek/usage/i:f	Ljava/util/HashSet;
    //   1886: invokevirtual 342	java/util/HashSet:isEmpty	()Z
    //   1889: ifne +43 -> 1932
    //   1892: new 235	org/json/JSONArray
    //   1895: dup
    //   1896: invokespecial 236	org/json/JSONArray:<init>	()V
    //   1899: astore 8
    //   1901: aload 6
    //   1903: getfield 163	com/cootek/usage/i:f	Ljava/util/HashSet;
    //   1906: invokevirtual 343	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1909: astore 9
    //   1911: aload 9
    //   1913: invokeinterface 251 1 0
    //   1918: ifne +462 -> 2380
    //   1921: aload 5
    //   1923: ldc_w 354
    //   1926: aload 8
    //   1928: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1931: pop
    //   1932: aload 6
    //   1934: getfield 165	com/cootek/usage/i:g	Ljava/util/HashSet;
    //   1937: invokevirtual 342	java/util/HashSet:isEmpty	()Z
    //   1940: ifne +51 -> 1991
    //   1943: new 235	org/json/JSONArray
    //   1946: dup
    //   1947: invokespecial 236	org/json/JSONArray:<init>	()V
    //   1950: astore 8
    //   1952: aload 6
    //   1954: getfield 165	com/cootek/usage/i:g	Ljava/util/HashSet;
    //   1957: invokevirtual 343	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1960: astore 9
    //   1962: aload 9
    //   1964: invokeinterface 251 1 0
    //   1969: ifne +526 -> 2495
    //   1972: aload 8
    //   1974: invokevirtual 346	org/json/JSONArray:length	()I
    //   1977: ifle +14 -> 1991
    //   1980: aload 5
    //   1982: ldc_w 356
    //   1985: aload 8
    //   1987: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1990: pop
    //   1991: aload 6
    //   1993: getfield 167	com/cootek/usage/i:h	Ljava/util/HashSet;
    //   1996: invokevirtual 342	java/util/HashSet:isEmpty	()Z
    //   1999: ifne +51 -> 2050
    //   2002: new 235	org/json/JSONArray
    //   2005: dup
    //   2006: invokespecial 236	org/json/JSONArray:<init>	()V
    //   2009: astore 8
    //   2011: aload 6
    //   2013: getfield 167	com/cootek/usage/i:h	Ljava/util/HashSet;
    //   2016: invokevirtual 343	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2019: astore 9
    //   2021: aload 9
    //   2023: invokeinterface 251 1 0
    //   2028: ifne +549 -> 2577
    //   2031: aload 8
    //   2033: invokevirtual 346	org/json/JSONArray:length	()I
    //   2036: ifle +14 -> 2050
    //   2039: aload 5
    //   2041: ldc_w 358
    //   2044: aload 8
    //   2046: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2049: pop
    //   2050: aload 6
    //   2052: getfield 170	com/cootek/usage/i:i	Ljava/util/HashSet;
    //   2055: invokevirtual 342	java/util/HashSet:isEmpty	()Z
    //   2058: ifne +51 -> 2109
    //   2061: new 235	org/json/JSONArray
    //   2064: dup
    //   2065: invokespecial 236	org/json/JSONArray:<init>	()V
    //   2068: astore 8
    //   2070: aload 6
    //   2072: getfield 170	com/cootek/usage/i:i	Ljava/util/HashSet;
    //   2075: invokevirtual 343	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2078: astore 6
    //   2080: aload 6
    //   2082: invokeinterface 251 1 0
    //   2087: ifne +572 -> 2659
    //   2090: aload 8
    //   2092: invokevirtual 346	org/json/JSONArray:length	()I
    //   2095: ifle +14 -> 2109
    //   2098: aload 5
    //   2100: ldc_w 360
    //   2103: aload 8
    //   2105: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2108: pop
    //   2109: aload_3
    //   2110: aload 5
    //   2112: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2115: pop
    //   2116: aload 7
    //   2118: iconst_1
    //   2119: putfield 274	com/cootek/usage/m:d	Z
    //   2122: goto -1639 -> 483
    //   2125: aload 8
    //   2127: aload 9
    //   2129: invokeinterface 331 1 0
    //   2134: checkcast 119	java/lang/String
    //   2137: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2140: pop
    //   2141: goto -407 -> 1734
    //   2144: astore 6
    //   2146: aload 6
    //   2148: invokevirtual 364	org/json/JSONException:printStackTrace	()V
    //   2151: goto -42 -> 2109
    //   2154: aload 9
    //   2156: invokeinterface 331 1 0
    //   2161: checkcast 286	com/cootek/usage/j
    //   2164: astore 10
    //   2166: new 333	org/json/JSONObject
    //   2169: dup
    //   2170: invokespecial 334	org/json/JSONObject:<init>	()V
    //   2173: astore 11
    //   2175: aload 10
    //   2177: getfield 289	com/cootek/usage/j:a	Ljava/lang/String;
    //   2180: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2183: ifne -390 -> 1793
    //   2186: aload 11
    //   2188: ldc_w 356
    //   2191: aload 10
    //   2193: getfield 289	com/cootek/usage/j:a	Ljava/lang/String;
    //   2196: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2199: pop
    //   2200: aload 10
    //   2202: getfield 294	com/cootek/usage/j:b	Ljava/lang/String;
    //   2205: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2208: ifne +17 -> 2225
    //   2211: aload 11
    //   2213: ldc_w 370
    //   2216: aload 10
    //   2218: getfield 294	com/cootek/usage/j:b	Ljava/lang/String;
    //   2221: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2224: pop
    //   2225: aload 8
    //   2227: aload 11
    //   2229: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2232: pop
    //   2233: goto -440 -> 1793
    //   2236: aload 9
    //   2238: invokeinterface 331 1 0
    //   2243: checkcast 298	com/cootek/usage/n
    //   2246: astore 10
    //   2248: new 333	org/json/JSONObject
    //   2251: dup
    //   2252: invokespecial 334	org/json/JSONObject:<init>	()V
    //   2255: astore 11
    //   2257: aload 10
    //   2259: getfield 304	com/cootek/usage/n:a	Ljava/lang/String;
    //   2262: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2265: ifne +535 -> 2800
    //   2268: aload 11
    //   2270: ldc_w 370
    //   2273: aload 10
    //   2275: getfield 304	com/cootek/usage/n:a	Ljava/lang/String;
    //   2278: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2281: pop
    //   2282: iconst_1
    //   2283: istore_1
    //   2284: aload 10
    //   2286: getfield 304	com/cootek/usage/n:a	Ljava/lang/String;
    //   2289: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2292: ifne +19 -> 2311
    //   2295: aload 11
    //   2297: ldc_w 372
    //   2300: aload 10
    //   2302: getfield 300	com/cootek/usage/n:b	Ljava/lang/String;
    //   2305: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2308: pop
    //   2309: iconst_1
    //   2310: istore_1
    //   2311: aload 10
    //   2313: getfield 304	com/cootek/usage/n:a	Ljava/lang/String;
    //   2316: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2319: ifne +19 -> 2338
    //   2322: aload 11
    //   2324: ldc_w 374
    //   2327: aload 10
    //   2329: getfield 301	com/cootek/usage/n:c	Ljava/lang/String;
    //   2332: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2335: pop
    //   2336: iconst_1
    //   2337: istore_1
    //   2338: aload 10
    //   2340: getfield 304	com/cootek/usage/n:a	Ljava/lang/String;
    //   2343: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2346: ifne +451 -> 2797
    //   2349: aload 11
    //   2351: ldc_w 376
    //   2354: aload 10
    //   2356: getfield 303	com/cootek/usage/n:d	Ljava/lang/String;
    //   2359: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2362: pop
    //   2363: iconst_1
    //   2364: istore_1
    //   2365: iload_1
    //   2366: ifeq -514 -> 1852
    //   2369: aload 8
    //   2371: aload 11
    //   2373: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2376: pop
    //   2377: goto -525 -> 1852
    //   2380: aload 9
    //   2382: invokeinterface 331 1 0
    //   2387: checkcast 306	com/cootek/usage/l
    //   2390: astore 10
    //   2392: new 333	org/json/JSONObject
    //   2395: dup
    //   2396: invokespecial 334	org/json/JSONObject:<init>	()V
    //   2399: astore 11
    //   2401: aload 10
    //   2403: getfield 308	com/cootek/usage/l:a	Ljava/lang/String;
    //   2406: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2409: ifne -498 -> 1911
    //   2412: aload 11
    //   2414: ldc_w 378
    //   2417: aload 10
    //   2419: getfield 308	com/cootek/usage/l:a	Ljava/lang/String;
    //   2422: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2425: pop
    //   2426: aload 10
    //   2428: getfield 309	com/cootek/usage/l:b	Ljava/lang/String;
    //   2431: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2434: ifne +17 -> 2451
    //   2437: aload 11
    //   2439: ldc_w 370
    //   2442: aload 10
    //   2444: getfield 309	com/cootek/usage/l:b	Ljava/lang/String;
    //   2447: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2450: pop
    //   2451: aload 10
    //   2453: getfield 310	com/cootek/usage/l:c	Ljava/lang/String;
    //   2456: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2459: ifne +17 -> 2476
    //   2462: aload 11
    //   2464: ldc_w 380
    //   2467: aload 10
    //   2469: getfield 310	com/cootek/usage/l:c	Ljava/lang/String;
    //   2472: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2475: pop
    //   2476: aload 8
    //   2478: invokevirtual 346	org/json/JSONArray:length	()I
    //   2481: ifle -570 -> 1911
    //   2484: aload 8
    //   2486: aload 11
    //   2488: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2491: pop
    //   2492: goto -581 -> 1911
    //   2495: aload 9
    //   2497: invokeinterface 331 1 0
    //   2502: checkcast 312	com/cootek/usage/h
    //   2505: astore 10
    //   2507: new 333	org/json/JSONObject
    //   2510: dup
    //   2511: invokespecial 334	org/json/JSONObject:<init>	()V
    //   2514: astore 11
    //   2516: aload 10
    //   2518: getfield 314	com/cootek/usage/h:a	Ljava/lang/String;
    //   2521: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2524: ifne -562 -> 1962
    //   2527: aload 11
    //   2529: ldc_w 382
    //   2532: aload 10
    //   2534: getfield 314	com/cootek/usage/h:a	Ljava/lang/String;
    //   2537: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2540: pop
    //   2541: aload 10
    //   2543: getfield 315	com/cootek/usage/h:b	Ljava/lang/String;
    //   2546: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2549: ifne +17 -> 2566
    //   2552: aload 11
    //   2554: ldc_w 370
    //   2557: aload 10
    //   2559: getfield 315	com/cootek/usage/h:b	Ljava/lang/String;
    //   2562: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2565: pop
    //   2566: aload 8
    //   2568: aload 11
    //   2570: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2573: pop
    //   2574: goto -612 -> 1962
    //   2577: aload 9
    //   2579: invokeinterface 331 1 0
    //   2584: checkcast 317	com/cootek/usage/k
    //   2587: astore 10
    //   2589: new 333	org/json/JSONObject
    //   2592: dup
    //   2593: invokespecial 334	org/json/JSONObject:<init>	()V
    //   2596: astore 11
    //   2598: aload 10
    //   2600: getfield 319	com/cootek/usage/k:a	Ljava/lang/String;
    //   2603: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2606: ifne -585 -> 2021
    //   2609: aload 11
    //   2611: ldc_w 384
    //   2614: aload 10
    //   2616: getfield 319	com/cootek/usage/k:a	Ljava/lang/String;
    //   2619: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2622: pop
    //   2623: aload 10
    //   2625: getfield 320	com/cootek/usage/k:b	Ljava/lang/String;
    //   2628: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2631: ifne +17 -> 2648
    //   2634: aload 11
    //   2636: ldc_w 370
    //   2639: aload 10
    //   2641: getfield 320	com/cootek/usage/k:b	Ljava/lang/String;
    //   2644: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2647: pop
    //   2648: aload 8
    //   2650: aload 11
    //   2652: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2655: pop
    //   2656: goto -635 -> 2021
    //   2659: aload 6
    //   2661: invokeinterface 331 1 0
    //   2666: checkcast 322	com/cootek/usage/o
    //   2669: astore 9
    //   2671: new 333	org/json/JSONObject
    //   2674: dup
    //   2675: invokespecial 334	org/json/JSONObject:<init>	()V
    //   2678: astore 10
    //   2680: aload 9
    //   2682: getfield 324	com/cootek/usage/o:a	Ljava/lang/String;
    //   2685: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2688: ifne -608 -> 2080
    //   2691: aload 10
    //   2693: ldc_w 336
    //   2696: aload 9
    //   2698: getfield 324	com/cootek/usage/o:a	Ljava/lang/String;
    //   2701: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2704: pop
    //   2705: aload 9
    //   2707: getfield 325	com/cootek/usage/o:b	Ljava/lang/String;
    //   2710: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2713: ifne +17 -> 2730
    //   2716: aload 10
    //   2718: ldc_w 370
    //   2721: aload 9
    //   2723: getfield 325	com/cootek/usage/o:b	Ljava/lang/String;
    //   2726: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2729: pop
    //   2730: aload 8
    //   2732: aload 10
    //   2734: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2737: pop
    //   2738: goto -658 -> 2080
    //   2741: astore_3
    //   2742: aconst_null
    //   2743: astore 4
    //   2745: goto -1899 -> 846
    //   2748: astore 5
    //   2750: aload_3
    //   2751: astore 4
    //   2753: aload 5
    //   2755: astore_3
    //   2756: goto -1910 -> 846
    //   2759: astore 5
    //   2761: goto -1150 -> 1611
    //   2764: astore_3
    //   2765: aload 5
    //   2767: astore 4
    //   2769: goto -2066 -> 703
    //   2772: astore_3
    //   2773: goto -2151 -> 622
    //   2776: astore 5
    //   2778: aload_3
    //   2779: astore 4
    //   2781: aload 5
    //   2783: astore_3
    //   2784: goto -2162 -> 622
    //   2787: astore 5
    //   2789: goto -2209 -> 580
    //   2792: astore 4
    //   2794: goto -2245 -> 549
    //   2797: goto -432 -> 2365
    //   2800: iconst_0
    //   2801: istore_1
    //   2802: goto -518 -> 2284
    //   2805: ldc_w 386
    //   2808: astore_3
    //   2809: goto -2005 -> 804
    //   2812: ldc_w 388
    //   2815: astore_3
    //   2816: goto -2012 -> 804
    //   2819: ldc_w 390
    //   2822: astore_3
    //   2823: goto -2019 -> 804
    //   2826: ldc_w 392
    //   2829: astore_3
    //   2830: goto -2026 -> 804
    //   2833: aconst_null
    //   2834: astore_3
    //   2835: goto -2031 -> 804
    //   2838: ldc_w 386
    //   2841: astore_3
    //   2842: goto -1882 -> 960
    //   2845: ldc_w 388
    //   2848: astore_3
    //   2849: goto -1889 -> 960
    //   2852: ldc_w 390
    //   2855: astore_3
    //   2856: goto -1896 -> 960
    //   2859: aconst_null
    //   2860: astore_3
    //   2861: goto -1901 -> 960
    //   2864: ldc_w 386
    //   2867: astore_3
    //   2868: goto -1796 -> 1072
    //   2871: ldc_w 386
    //   2874: astore_3
    //   2875: goto -1711 -> 1164
    //   2878: ldc_w 296
    //   2881: astore_3
    //   2882: goto -1810 -> 1072
    //   2885: ldc_w 388
    //   2888: astore_3
    //   2889: goto -1817 -> 1072
    //   2892: ldc_w 390
    //   2895: astore_3
    //   2896: goto -1824 -> 1072
    //   2899: aconst_null
    //   2900: astore_3
    //   2901: goto -1829 -> 1072
    //   2904: ldc_w 394
    //   2907: astore_3
    //   2908: goto -1744 -> 1164
    //   2911: aconst_null
    //   2912: astore_3
    //   2913: goto -1749 -> 1164
    //   2916: ldc_w 396
    //   2919: astore_3
    //   2920: goto -1756 -> 1164
    //   2923: ldc_w 398
    //   2926: astore_3
    //   2927: goto -1763 -> 1164
    //   2930: ldc_w 400
    //   2933: astore_3
    //   2934: goto -1770 -> 1164
    //   2937: ldc_w 402
    //   2940: astore_3
    //   2941: goto -1777 -> 1164
    //   2944: ldc_w 404
    //   2947: astore_3
    //   2948: goto -1784 -> 1164
    //   2951: ldc_w 406
    //   2954: astore_3
    //   2955: goto -1791 -> 1164
    //   2958: ldc_w 408
    //   2961: astore_3
    //   2962: goto -1798 -> 1164
    //   2965: ldc_w 410
    //   2968: astore_3
    //   2969: goto -1805 -> 1164
    //   2972: ldc_w 386
    //   2975: astore_3
    //   2976: goto -1700 -> 1276
    //   2979: ldc_w 296
    //   2982: astore_3
    //   2983: goto -1707 -> 1276
    //   2986: ldc_w 388
    //   2989: astore_3
    //   2990: goto -1714 -> 1276
    //   2993: ldc_w 390
    //   2996: astore_3
    //   2997: goto -1721 -> 1276
    //   3000: aconst_null
    //   3001: astore_3
    //   3002: goto -1726 -> 1276
    //   3005: ldc_w 386
    //   3008: astore_3
    //   3009: goto -1621 -> 1388
    //   3012: ldc_w 412
    //   3015: astore_3
    //   3016: goto -1628 -> 1388
    //   3019: ldc_w 414
    //   3022: astore_3
    //   3023: goto -1635 -> 1388
    //   3026: ldc_w 390
    //   3029: astore_3
    //   3030: goto -1642 -> 1388
    //   3033: aconst_null
    //   3034: astore_3
    //   3035: goto -1647 -> 1388
    //   3038: ldc_w 386
    //   3041: astore_3
    //   3042: goto -1498 -> 1544
    //   3045: ldc_w 416
    //   3048: astore_3
    //   3049: goto -1505 -> 1544
    //   3052: ldc_w 418
    //   3055: astore_3
    //   3056: goto -1512 -> 1544
    //   3059: ldc_w 420
    //   3062: astore_3
    //   3063: goto -1519 -> 1544
    //   3066: ldc_w 422
    //   3069: astore_3
    //   3070: goto -1526 -> 1544
    //   3073: ldc_w 424
    //   3076: astore_3
    //   3077: goto -1533 -> 1544
    //   3080: ldc_w 426
    //   3083: astore_3
    //   3084: goto -1540 -> 1544
    //   3087: ldc_w 428
    //   3090: astore_3
    //   3091: goto -1547 -> 1544
    //   3094: ldc_w 430
    //   3097: astore_3
    //   3098: goto -1554 -> 1544
    //   3101: ldc_w 432
    //   3104: astore_3
    //   3105: goto -1561 -> 1544
    //   3108: ldc_w 434
    //   3111: astore_3
    //   3112: goto -1568 -> 1544
    //   3115: ldc_w 436
    //   3118: astore_3
    //   3119: goto -1575 -> 1544
    //   3122: ldc_w 438
    //   3125: astore_3
    //   3126: goto -1582 -> 1544
    //   3129: ldc_w 440
    //   3132: astore_3
    //   3133: goto -1589 -> 1544
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3136	0	this	g
    //   2283	519	1	i	int
    //   256	192	2	bool	boolean
    //   62	459	3	localObject1	Object
    //   546	1	3	localSecurityException1	SecurityException
    //   548	12	3	localObject2	Object
    //   568	2	3	localRuntimeException1	RuntimeException
    //   579	23	3	localObject3	Object
    //   610	2	3	localRuntimeException2	RuntimeException
    //   618	17	3	localObject4	Object
    //   646	2	3	localRuntimeException3	RuntimeException
    //   662	25	3	str1	String
    //   694	2	3	localRuntimeException4	RuntimeException
    //   702	1	3	localSecurityException2	SecurityException
    //   705	102	3	localObject5	Object
    //   845	14	3	localObject6	Object
    //   863	731	3	str2	String
    //   1597	2	3	localRuntimeException5	RuntimeException
    //   1613	9	3	localObject7	Object
    //   1643	2	3	localRuntimeException6	RuntimeException
    //   1661	449	3	localRuntimeException7	RuntimeException
    //   2741	10	3	localObject8	Object
    //   2755	1	3	localObject9	Object
    //   2764	1	3	localSecurityException3	SecurityException
    //   2772	7	3	localObject10	Object
    //   2783	350	3	localObject11	Object
    //   68	560	4	localObject12	Object
    //   636	925	4	localRuntimeException8	RuntimeException
    //   1609	25	4	localObject13	Object
    //   1651	19	4	localRuntimeException9	RuntimeException
    //   2743	37	4	localObject14	Object
    //   2792	1	4	localSecurityException4	SecurityException
    //   4	431	5	localI	i
    //   576	996	5	localRuntimeException10	RuntimeException
    //   1605	10	5	localRuntimeException11	RuntimeException
    //   1688	423	5	localJSONObject1	JSONObject
    //   2748	6	5	localObject15	Object
    //   2759	7	5	localRuntimeException12	RuntimeException
    //   2776	6	5	localObject16	Object
    //   2787	1	5	localRuntimeException13	RuntimeException
    //   1	2080	6	localObject17	Object
    //   2144	516	6	localJSONException	JSONException
    //   14	2103	7	localM	m
    //   35	2696	8	localObject18	Object
    //   26	2696	9	localObject19	Object
    //   91	2642	10	localObject20	Object
    //   2173	478	11	localJSONObject2	JSONObject
    // Exception table:
    //   from	to	target	type
    //   37	63	546	java/lang/SecurityException
    //   559	565	568	java/lang/RuntimeException
    //   37	63	576	java/lang/RuntimeException
    //   601	607	610	java/lang/RuntimeException
    //   37	63	618	finally
    //   627	634	636	java/lang/RuntimeException
    //   265	271	646	java/lang/RuntimeException
    //   413	434	694	java/lang/RuntimeException
    //   654	691	694	java/lang/RuntimeException
    //   727	804	694	java/lang/RuntimeException
    //   804	831	694	java/lang/RuntimeException
    //   831	842	694	java/lang/RuntimeException
    //   867	960	694	java/lang/RuntimeException
    //   960	987	694	java/lang/RuntimeException
    //   987	998	694	java/lang/RuntimeException
    //   1001	1072	694	java/lang/RuntimeException
    //   1072	1099	694	java/lang/RuntimeException
    //   1099	1164	694	java/lang/RuntimeException
    //   1164	1192	694	java/lang/RuntimeException
    //   1192	1203	694	java/lang/RuntimeException
    //   1206	1276	694	java/lang/RuntimeException
    //   1276	1303	694	java/lang/RuntimeException
    //   1303	1314	694	java/lang/RuntimeException
    //   1317	1388	694	java/lang/RuntimeException
    //   1388	1415	694	java/lang/RuntimeException
    //   1415	1426	694	java/lang/RuntimeException
    //   1429	1544	694	java/lang/RuntimeException
    //   1544	1571	694	java/lang/RuntimeException
    //   1571	1582	694	java/lang/RuntimeException
    //   401	409	702	java/lang/SecurityException
    //   413	434	702	java/lang/SecurityException
    //   439	447	702	java/lang/SecurityException
    //   654	691	702	java/lang/SecurityException
    //   695	699	702	java/lang/SecurityException
    //   727	804	702	java/lang/SecurityException
    //   804	831	702	java/lang/SecurityException
    //   831	842	702	java/lang/SecurityException
    //   867	960	702	java/lang/SecurityException
    //   960	987	702	java/lang/SecurityException
    //   987	998	702	java/lang/SecurityException
    //   1001	1072	702	java/lang/SecurityException
    //   1072	1099	702	java/lang/SecurityException
    //   1099	1164	702	java/lang/SecurityException
    //   1164	1192	702	java/lang/SecurityException
    //   1192	1203	702	java/lang/SecurityException
    //   1206	1276	702	java/lang/SecurityException
    //   1276	1303	702	java/lang/SecurityException
    //   1303	1314	702	java/lang/SecurityException
    //   1317	1388	702	java/lang/SecurityException
    //   1388	1415	702	java/lang/SecurityException
    //   1415	1426	702	java/lang/SecurityException
    //   1429	1544	702	java/lang/SecurityException
    //   1544	1571	702	java/lang/SecurityException
    //   1571	1582	702	java/lang/SecurityException
    //   401	409	845	finally
    //   413	434	845	finally
    //   439	447	845	finally
    //   654	691	845	finally
    //   695	699	845	finally
    //   727	804	845	finally
    //   804	831	845	finally
    //   831	842	845	finally
    //   867	960	845	finally
    //   960	987	845	finally
    //   987	998	845	finally
    //   1001	1072	845	finally
    //   1072	1099	845	finally
    //   1099	1164	845	finally
    //   1164	1192	845	finally
    //   1192	1203	845	finally
    //   1206	1276	845	finally
    //   1276	1303	845	finally
    //   1303	1314	845	finally
    //   1317	1388	845	finally
    //   1388	1415	845	finally
    //   1415	1426	845	finally
    //   1429	1544	845	finally
    //   1544	1571	845	finally
    //   1571	1582	845	finally
    //   717	724	1597	java/lang/RuntimeException
    //   271	396	1605	java/lang/RuntimeException
    //   1633	1640	1643	java/lang/RuntimeException
    //   851	858	1651	java/lang/RuntimeException
    //   456	463	1661	java/lang/RuntimeException
    //   1690	1734	2144	org/json/JSONException
    //   1734	1763	2144	org/json/JSONException
    //   1763	1793	2144	org/json/JSONException
    //   1793	1822	2144	org/json/JSONException
    //   1822	1852	2144	org/json/JSONException
    //   1852	1881	2144	org/json/JSONException
    //   1881	1911	2144	org/json/JSONException
    //   1911	1932	2144	org/json/JSONException
    //   1932	1962	2144	org/json/JSONException
    //   1962	1991	2144	org/json/JSONException
    //   1991	2021	2144	org/json/JSONException
    //   2021	2050	2144	org/json/JSONException
    //   2050	2080	2144	org/json/JSONException
    //   2080	2109	2144	org/json/JSONException
    //   2125	2141	2144	org/json/JSONException
    //   2154	2225	2144	org/json/JSONException
    //   2225	2233	2144	org/json/JSONException
    //   2236	2282	2144	org/json/JSONException
    //   2284	2309	2144	org/json/JSONException
    //   2311	2336	2144	org/json/JSONException
    //   2338	2363	2144	org/json/JSONException
    //   2369	2377	2144	org/json/JSONException
    //   2380	2451	2144	org/json/JSONException
    //   2451	2476	2144	org/json/JSONException
    //   2476	2492	2144	org/json/JSONException
    //   2495	2566	2144	org/json/JSONException
    //   2566	2574	2144	org/json/JSONException
    //   2577	2648	2144	org/json/JSONException
    //   2648	2656	2144	org/json/JSONException
    //   2659	2730	2144	org/json/JSONException
    //   2730	2738	2144	org/json/JSONException
    //   271	396	2741	finally
    //   706	712	2748	finally
    //   1614	1619	2748	finally
    //   1622	1628	2748	finally
    //   401	409	2759	java/lang/RuntimeException
    //   439	447	2759	java/lang/RuntimeException
    //   695	699	2759	java/lang/RuntimeException
    //   271	396	2764	java/lang/SecurityException
    //   70	79	2772	finally
    //   82	93	2772	finally
    //   96	108	2772	finally
    //   111	123	2772	finally
    //   126	138	2772	finally
    //   141	153	2772	finally
    //   156	168	2772	finally
    //   171	183	2772	finally
    //   186	198	2772	finally
    //   201	213	2772	finally
    //   216	228	2772	finally
    //   231	247	2772	finally
    //   250	257	2772	finally
    //   583	588	2772	finally
    //   591	597	2772	finally
    //   549	555	2776	finally
    //   70	79	2787	java/lang/RuntimeException
    //   82	93	2787	java/lang/RuntimeException
    //   96	108	2787	java/lang/RuntimeException
    //   111	123	2787	java/lang/RuntimeException
    //   126	138	2787	java/lang/RuntimeException
    //   141	153	2787	java/lang/RuntimeException
    //   156	168	2787	java/lang/RuntimeException
    //   171	183	2787	java/lang/RuntimeException
    //   186	198	2787	java/lang/RuntimeException
    //   201	213	2787	java/lang/RuntimeException
    //   216	228	2787	java/lang/RuntimeException
    //   231	247	2787	java/lang/RuntimeException
    //   250	257	2787	java/lang/RuntimeException
    //   70	79	2792	java/lang/SecurityException
    //   82	93	2792	java/lang/SecurityException
    //   96	108	2792	java/lang/SecurityException
    //   111	123	2792	java/lang/SecurityException
    //   126	138	2792	java/lang/SecurityException
    //   141	153	2792	java/lang/SecurityException
    //   156	168	2792	java/lang/SecurityException
    //   171	183	2792	java/lang/SecurityException
    //   186	198	2792	java/lang/SecurityException
    //   201	213	2792	java/lang/SecurityException
    //   216	228	2792	java/lang/SecurityException
    //   231	247	2792	java/lang/SecurityException
    //   250	257	2792	java/lang/SecurityException
  }
  
  private static String c(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return "HOME";
    case 2: 
      return "WORK";
    case 3: 
      return "OTHER";
    case 4: 
      return "MOBILE";
    }
    return null;
  }
  
  /* Error */
  private m d()
  {
    // Byte code:
    //   0: new 95	com/cootek/usage/m
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 98	com/cootek/usage/m:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 10
    //   10: aload_0
    //   11: getfield 31	com/cootek/usage/g:h	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 104	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 108	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 7
    //   22: new 235	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 236	org/json/JSONArray:<init>	()V
    //   29: astore 11
    //   31: invokestatic 445	com/cootek/usage/r:a	()Lcom/cootek/usage/r;
    //   34: iconst_1
    //   35: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   38: invokevirtual 448	com/cootek/usage/r:c	(Ljava/lang/String;)J
    //   41: lstore_2
    //   42: aload 7
    //   44: getstatic 451	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   47: bipush 6
    //   49: anewarray 119	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: ldc 121
    //   56: aastore
    //   57: dup
    //   58: iconst_1
    //   59: ldc_w 453
    //   62: aastore
    //   63: dup
    //   64: iconst_2
    //   65: ldc_w 384
    //   68: aastore
    //   69: dup
    //   70: iconst_3
    //   71: ldc_w 455
    //   74: aastore
    //   75: dup
    //   76: iconst_4
    //   77: ldc_w 370
    //   80: aastore
    //   81: dup
    //   82: iconst_5
    //   83: ldc_w 336
    //   86: aastore
    //   87: ldc_w 457
    //   90: iconst_1
    //   91: anewarray 119	java/lang/String
    //   94: dup
    //   95: iconst_0
    //   96: lload_2
    //   97: invokestatic 460	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   100: aastore
    //   101: ldc_w 462
    //   104: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   107: astore 7
    //   109: aload 7
    //   111: ifnull +265 -> 376
    //   114: aload 7
    //   116: astore 8
    //   118: aload 7
    //   120: invokeinterface 134 1 0
    //   125: ifeq +251 -> 376
    //   128: aload 7
    //   130: astore 8
    //   132: aload 10
    //   134: aload 7
    //   136: iconst_0
    //   137: invokeinterface 143 2 0
    //   142: putfield 464	com/cootek/usage/m:b	J
    //   145: aload 7
    //   147: astore 8
    //   149: aload 7
    //   151: iconst_1
    //   152: invokeinterface 149 2 0
    //   157: astore 9
    //   159: aload 7
    //   161: astore 8
    //   163: aload 7
    //   165: iconst_2
    //   166: invokeinterface 143 2 0
    //   171: lstore 4
    //   173: aload 7
    //   175: astore 8
    //   177: aload 7
    //   179: iconst_3
    //   180: invokeinterface 143 2 0
    //   185: lstore_2
    //   186: aload 7
    //   188: astore 8
    //   190: aload 7
    //   192: iconst_4
    //   193: invokeinterface 293 2 0
    //   198: istore_1
    //   199: aload 7
    //   201: astore 8
    //   203: aload 7
    //   205: iconst_5
    //   206: invokeinterface 149 2 0
    //   211: astore 12
    //   213: aload 7
    //   215: astore 8
    //   217: new 333	org/json/JSONObject
    //   220: dup
    //   221: invokespecial 334	org/json/JSONObject:<init>	()V
    //   224: astore 13
    //   226: aload 7
    //   228: astore 8
    //   230: aload 13
    //   232: ldc_w 466
    //   235: aload 9
    //   237: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   240: pop
    //   241: aload 7
    //   243: astore 8
    //   245: aload 13
    //   247: ldc_w 384
    //   250: lload 4
    //   252: ldc2_w 467
    //   255: ldiv
    //   256: invokevirtual 471	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   259: pop
    //   260: iload_1
    //   261: iconst_2
    //   262: if_icmpne +200 -> 462
    //   265: ldc_w 473
    //   268: astore 9
    //   270: aload 7
    //   272: astore 8
    //   274: aload 13
    //   276: ldc_w 370
    //   279: aload 9
    //   281: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   284: pop
    //   285: iconst_3
    //   286: iload_1
    //   287: if_icmpne +5 -> 292
    //   290: lconst_0
    //   291: lstore_2
    //   292: aload 7
    //   294: astore 8
    //   296: aload 13
    //   298: ldc_w 455
    //   301: lload_2
    //   302: invokevirtual 471	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   305: pop
    //   306: aload 7
    //   308: astore 8
    //   310: aload 12
    //   312: invokestatic 369	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   315: ifeq +155 -> 470
    //   318: iconst_0
    //   319: istore 6
    //   321: aload 7
    //   323: astore 8
    //   325: aload 13
    //   327: ldc_w 475
    //   330: iload 6
    //   332: invokevirtual 478	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   335: pop
    //   336: aload 7
    //   338: astore 8
    //   340: aload 11
    //   342: aload 13
    //   344: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   347: pop
    //   348: aload 7
    //   350: astore 8
    //   352: aload 10
    //   354: iconst_1
    //   355: putfield 274	com/cootek/usage/m:d	Z
    //   358: aload 7
    //   360: astore 8
    //   362: aload 7
    //   364: invokeinterface 183 1 0
    //   369: istore 6
    //   371: iload 6
    //   373: ifne -228 -> 145
    //   376: aload 7
    //   378: ifnull +10 -> 388
    //   381: aload 7
    //   383: invokeinterface 186 1 0
    //   388: new 333	org/json/JSONObject
    //   391: dup
    //   392: invokespecial 334	org/json/JSONObject:<init>	()V
    //   395: astore 7
    //   397: aload 7
    //   399: ldc_w 480
    //   402: aload 11
    //   404: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   407: pop
    //   408: new 253	com/cootek/usage/UsageData
    //   411: dup
    //   412: invokespecial 254	com/cootek/usage/UsageData:<init>	()V
    //   415: astore 8
    //   417: aload 8
    //   419: ldc 21
    //   421: putfield 257	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   424: aload 8
    //   426: iconst_1
    //   427: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   430: putfield 262	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   433: aload 8
    //   435: aload 7
    //   437: invokevirtual 481	org/json/JSONObject:toString	()Ljava/lang/String;
    //   440: putfield 266	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   443: aload 10
    //   445: aload 8
    //   447: putfield 269	com/cootek/usage/m:a	Lcom/cootek/usage/UsageData;
    //   450: aload 10
    //   452: iconst_1
    //   453: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   456: putfield 271	com/cootek/usage/m:c	Ljava/lang/String;
    //   459: aload 10
    //   461: areturn
    //   462: ldc_w 483
    //   465: astore 9
    //   467: goto -197 -> 270
    //   470: iconst_1
    //   471: istore 6
    //   473: goto -152 -> 321
    //   476: astore 9
    //   478: aload 7
    //   480: astore 8
    //   482: aload 9
    //   484: invokevirtual 364	org/json/JSONException:printStackTrace	()V
    //   487: goto -129 -> 358
    //   490: astore 8
    //   492: aload 10
    //   494: iconst_0
    //   495: putfield 274	com/cootek/usage/m:d	Z
    //   498: aload 7
    //   500: ifnull +10 -> 510
    //   503: aload 7
    //   505: invokeinterface 186 1 0
    //   510: aload 10
    //   512: areturn
    //   513: astore 7
    //   515: aload 7
    //   517: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   520: goto -10 -> 510
    //   523: astore 9
    //   525: aconst_null
    //   526: astore 7
    //   528: aload 7
    //   530: astore 8
    //   532: aload 9
    //   534: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   537: aload 7
    //   539: astore 8
    //   541: aload 10
    //   543: iconst_0
    //   544: putfield 274	com/cootek/usage/m:d	Z
    //   547: aload 7
    //   549: ifnull +10 -> 559
    //   552: aload 7
    //   554: invokeinterface 186 1 0
    //   559: aload 10
    //   561: areturn
    //   562: astore 7
    //   564: aload 7
    //   566: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   569: goto -10 -> 559
    //   572: astore 7
    //   574: aconst_null
    //   575: astore 8
    //   577: aload 8
    //   579: ifnull +10 -> 589
    //   582: aload 8
    //   584: invokeinterface 186 1 0
    //   589: aload 7
    //   591: athrow
    //   592: astore 8
    //   594: aload 8
    //   596: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   599: goto -10 -> 589
    //   602: astore 7
    //   604: aload 7
    //   606: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   609: goto -221 -> 388
    //   612: astore 8
    //   614: aload 8
    //   616: invokevirtual 364	org/json/JSONException:printStackTrace	()V
    //   619: goto -211 -> 408
    //   622: astore 7
    //   624: goto -47 -> 577
    //   627: astore 9
    //   629: aload 7
    //   631: astore 8
    //   633: aload 9
    //   635: astore 7
    //   637: goto -60 -> 577
    //   640: astore 9
    //   642: goto -114 -> 528
    //   645: astore 7
    //   647: aconst_null
    //   648: astore 7
    //   650: goto -158 -> 492
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	653	0	this	g
    //   198	90	1	i	int
    //   41	261	2	l1	long
    //   171	80	4	l2	long
    //   319	153	6	bool	boolean
    //   20	484	7	localObject1	Object
    //   513	3	7	localRuntimeException1	RuntimeException
    //   526	27	7	localObject2	Object
    //   562	3	7	localRuntimeException2	RuntimeException
    //   572	18	7	localObject3	Object
    //   602	3	7	localRuntimeException3	RuntimeException
    //   622	8	7	localObject4	Object
    //   635	1	7	localObject5	Object
    //   645	1	7	localSecurityException1	SecurityException
    //   648	1	7	localObject6	Object
    //   116	365	8	localObject7	Object
    //   490	1	8	localSecurityException2	SecurityException
    //   530	53	8	localObject8	Object
    //   592	3	8	localRuntimeException4	RuntimeException
    //   612	3	8	localJSONException1	JSONException
    //   631	1	8	localObject9	Object
    //   157	309	9	str1	String
    //   476	7	9	localJSONException2	JSONException
    //   523	10	9	localRuntimeException5	RuntimeException
    //   627	7	9	localObject10	Object
    //   640	1	9	localRuntimeException6	RuntimeException
    //   8	552	10	localM	m
    //   29	374	11	localJSONArray	JSONArray
    //   211	100	12	str2	String
    //   224	119	13	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   230	241	476	org/json/JSONException
    //   245	260	476	org/json/JSONException
    //   274	285	476	org/json/JSONException
    //   296	306	476	org/json/JSONException
    //   310	318	476	org/json/JSONException
    //   325	336	476	org/json/JSONException
    //   340	348	476	org/json/JSONException
    //   352	358	476	org/json/JSONException
    //   118	128	490	java/lang/SecurityException
    //   132	145	490	java/lang/SecurityException
    //   149	159	490	java/lang/SecurityException
    //   163	173	490	java/lang/SecurityException
    //   177	186	490	java/lang/SecurityException
    //   190	199	490	java/lang/SecurityException
    //   203	213	490	java/lang/SecurityException
    //   217	226	490	java/lang/SecurityException
    //   230	241	490	java/lang/SecurityException
    //   245	260	490	java/lang/SecurityException
    //   274	285	490	java/lang/SecurityException
    //   296	306	490	java/lang/SecurityException
    //   310	318	490	java/lang/SecurityException
    //   325	336	490	java/lang/SecurityException
    //   340	348	490	java/lang/SecurityException
    //   352	358	490	java/lang/SecurityException
    //   362	371	490	java/lang/SecurityException
    //   482	487	490	java/lang/SecurityException
    //   503	510	513	java/lang/RuntimeException
    //   31	109	523	java/lang/RuntimeException
    //   552	559	562	java/lang/RuntimeException
    //   31	109	572	finally
    //   582	589	592	java/lang/RuntimeException
    //   381	388	602	java/lang/RuntimeException
    //   397	408	612	org/json/JSONException
    //   118	128	622	finally
    //   132	145	622	finally
    //   149	159	622	finally
    //   163	173	622	finally
    //   177	186	622	finally
    //   190	199	622	finally
    //   203	213	622	finally
    //   217	226	622	finally
    //   230	241	622	finally
    //   245	260	622	finally
    //   274	285	622	finally
    //   296	306	622	finally
    //   310	318	622	finally
    //   325	336	622	finally
    //   340	348	622	finally
    //   352	358	622	finally
    //   362	371	622	finally
    //   482	487	622	finally
    //   532	537	622	finally
    //   541	547	622	finally
    //   492	498	627	finally
    //   118	128	640	java/lang/RuntimeException
    //   132	145	640	java/lang/RuntimeException
    //   149	159	640	java/lang/RuntimeException
    //   163	173	640	java/lang/RuntimeException
    //   177	186	640	java/lang/RuntimeException
    //   190	199	640	java/lang/RuntimeException
    //   203	213	640	java/lang/RuntimeException
    //   217	226	640	java/lang/RuntimeException
    //   230	241	640	java/lang/RuntimeException
    //   245	260	640	java/lang/RuntimeException
    //   274	285	640	java/lang/RuntimeException
    //   296	306	640	java/lang/RuntimeException
    //   310	318	640	java/lang/RuntimeException
    //   325	336	640	java/lang/RuntimeException
    //   340	348	640	java/lang/RuntimeException
    //   352	358	640	java/lang/RuntimeException
    //   362	371	640	java/lang/RuntimeException
    //   482	487	640	java/lang/RuntimeException
    //   31	109	645	java/lang/SecurityException
  }
  
  private static String d(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return "WORK";
    case 2: 
      return "OTHER";
    }
    return null;
  }
  
  /* Error */
  private m e()
  {
    // Byte code:
    //   0: new 95	com/cootek/usage/m
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 98	com/cootek/usage/m:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 9
    //   10: aload_0
    //   11: getfield 31	com/cootek/usage/g:h	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 104	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 108	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 6
    //   22: new 235	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 236	org/json/JSONArray:<init>	()V
    //   29: astore 8
    //   31: invokestatic 445	com/cootek/usage/r:a	()Lcom/cootek/usage/r;
    //   34: iconst_2
    //   35: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   38: invokevirtual 448	com/cootek/usage/r:c	(Ljava/lang/String;)J
    //   41: lstore_1
    //   42: aload 6
    //   44: ldc_w 485
    //   47: invokestatic 491	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   50: bipush 6
    //   52: anewarray 119	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: ldc 121
    //   59: aastore
    //   60: dup
    //   61: iconst_1
    //   62: ldc_w 356
    //   65: aastore
    //   66: dup
    //   67: iconst_2
    //   68: ldc_w 493
    //   71: aastore
    //   72: dup
    //   73: iconst_3
    //   74: ldc_w 384
    //   77: aastore
    //   78: dup
    //   79: iconst_4
    //   80: ldc_w 495
    //   83: aastore
    //   84: dup
    //   85: iconst_5
    //   86: ldc_w 497
    //   89: aastore
    //   90: ldc_w 457
    //   93: iconst_1
    //   94: anewarray 119	java/lang/String
    //   97: dup
    //   98: iconst_0
    //   99: lload_1
    //   100: invokestatic 460	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   103: aastore
    //   104: ldc_w 499
    //   107: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   110: astore 6
    //   112: aload 6
    //   114: ifnull +264 -> 378
    //   117: aload 6
    //   119: astore 7
    //   121: aload 6
    //   123: invokeinterface 134 1 0
    //   128: ifeq +250 -> 378
    //   131: aload 6
    //   133: astore 7
    //   135: aload 9
    //   137: aload 6
    //   139: iconst_0
    //   140: invokeinterface 143 2 0
    //   145: putfield 464	com/cootek/usage/m:b	J
    //   148: aload 6
    //   150: astore 7
    //   152: aload 6
    //   154: iconst_1
    //   155: invokeinterface 149 2 0
    //   160: astore 10
    //   162: aload 6
    //   164: astore 7
    //   166: aload 6
    //   168: iconst_2
    //   169: invokeinterface 143 2 0
    //   174: lstore_1
    //   175: lload_1
    //   176: lconst_0
    //   177: lcmp
    //   178: ifgt +182 -> 360
    //   181: aload 6
    //   183: astore 7
    //   185: aload 6
    //   187: iconst_3
    //   188: invokeinterface 143 2 0
    //   193: lstore_3
    //   194: aload 6
    //   196: astore 7
    //   198: aload 6
    //   200: iconst_4
    //   201: invokeinterface 149 2 0
    //   206: astore 11
    //   208: aload 6
    //   210: astore 7
    //   212: aload 6
    //   214: iconst_5
    //   215: invokeinterface 149 2 0
    //   220: astore 12
    //   222: aload 6
    //   224: astore 7
    //   226: new 333	org/json/JSONObject
    //   229: dup
    //   230: invokespecial 334	org/json/JSONObject:<init>	()V
    //   233: astore 13
    //   235: aload 6
    //   237: astore 7
    //   239: aload 13
    //   241: ldc_w 466
    //   244: aload 10
    //   246: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   249: pop
    //   250: aload 6
    //   252: astore 7
    //   254: aload 13
    //   256: ldc_w 384
    //   259: lload_3
    //   260: ldc2_w 467
    //   263: ldiv
    //   264: invokevirtual 471	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   267: pop
    //   268: aload 6
    //   270: astore 7
    //   272: aload 13
    //   274: ldc_w 370
    //   277: ldc_w 483
    //   280: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   283: pop
    //   284: lload_1
    //   285: lconst_0
    //   286: lcmp
    //   287: ifle +177 -> 464
    //   290: iconst_1
    //   291: istore 5
    //   293: aload 6
    //   295: astore 7
    //   297: aload 13
    //   299: ldc_w 475
    //   302: iload 5
    //   304: invokevirtual 478	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   307: pop
    //   308: aload 6
    //   310: astore 7
    //   312: aload 13
    //   314: ldc_w 501
    //   317: aload 11
    //   319: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   322: pop
    //   323: aload 6
    //   325: astore 7
    //   327: aload 13
    //   329: ldc_w 497
    //   332: aload 12
    //   334: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   337: pop
    //   338: aload 6
    //   340: astore 7
    //   342: aload 8
    //   344: aload 13
    //   346: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   349: pop
    //   350: aload 6
    //   352: astore 7
    //   354: aload 9
    //   356: iconst_1
    //   357: putfield 274	com/cootek/usage/m:d	Z
    //   360: aload 6
    //   362: astore 7
    //   364: aload 6
    //   366: invokeinterface 183 1 0
    //   371: istore 5
    //   373: iload 5
    //   375: ifne -227 -> 148
    //   378: aload 6
    //   380: ifnull +10 -> 390
    //   383: aload 6
    //   385: invokeinterface 186 1 0
    //   390: new 333	org/json/JSONObject
    //   393: dup
    //   394: invokespecial 334	org/json/JSONObject:<init>	()V
    //   397: astore 6
    //   399: aload 6
    //   401: ldc_w 480
    //   404: aload 8
    //   406: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   409: pop
    //   410: new 253	com/cootek/usage/UsageData
    //   413: dup
    //   414: invokespecial 254	com/cootek/usage/UsageData:<init>	()V
    //   417: astore 7
    //   419: aload 7
    //   421: ldc 21
    //   423: putfield 257	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   426: aload 7
    //   428: iconst_2
    //   429: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   432: putfield 262	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   435: aload 7
    //   437: aload 6
    //   439: invokevirtual 481	org/json/JSONObject:toString	()Ljava/lang/String;
    //   442: putfield 266	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   445: aload 9
    //   447: aload 7
    //   449: putfield 269	com/cootek/usage/m:a	Lcom/cootek/usage/UsageData;
    //   452: aload 9
    //   454: iconst_2
    //   455: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   458: putfield 271	com/cootek/usage/m:c	Ljava/lang/String;
    //   461: aload 9
    //   463: areturn
    //   464: iconst_0
    //   465: istore 5
    //   467: goto -174 -> 293
    //   470: astore 10
    //   472: aload 6
    //   474: astore 7
    //   476: aload 10
    //   478: invokevirtual 364	org/json/JSONException:printStackTrace	()V
    //   481: goto -121 -> 360
    //   484: astore 7
    //   486: aload 9
    //   488: iconst_0
    //   489: putfield 274	com/cootek/usage/m:d	Z
    //   492: aload 6
    //   494: ifnull +10 -> 504
    //   497: aload 6
    //   499: invokeinterface 186 1 0
    //   504: aload 9
    //   506: areturn
    //   507: astore 6
    //   509: aload 6
    //   511: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   514: goto -10 -> 504
    //   517: astore 8
    //   519: aconst_null
    //   520: astore 6
    //   522: aload 6
    //   524: astore 7
    //   526: aload 8
    //   528: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   531: aload 6
    //   533: astore 7
    //   535: aload 9
    //   537: iconst_0
    //   538: putfield 274	com/cootek/usage/m:d	Z
    //   541: aload 6
    //   543: ifnull +10 -> 553
    //   546: aload 6
    //   548: invokeinterface 186 1 0
    //   553: aload 9
    //   555: areturn
    //   556: astore 6
    //   558: aload 6
    //   560: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   563: goto -10 -> 553
    //   566: astore 6
    //   568: aconst_null
    //   569: astore 7
    //   571: aload 7
    //   573: ifnull +10 -> 583
    //   576: aload 7
    //   578: invokeinterface 186 1 0
    //   583: aload 6
    //   585: athrow
    //   586: astore 7
    //   588: aload 7
    //   590: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   593: goto -10 -> 583
    //   596: astore 6
    //   598: aload 6
    //   600: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   603: goto -213 -> 390
    //   606: astore 7
    //   608: aload 7
    //   610: invokevirtual 364	org/json/JSONException:printStackTrace	()V
    //   613: goto -203 -> 410
    //   616: astore 6
    //   618: goto -47 -> 571
    //   621: astore 8
    //   623: aload 6
    //   625: astore 7
    //   627: aload 8
    //   629: astore 6
    //   631: goto -60 -> 571
    //   634: astore 8
    //   636: goto -114 -> 522
    //   639: astore 6
    //   641: aconst_null
    //   642: astore 6
    //   644: goto -158 -> 486
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	647	0	this	g
    //   41	244	1	l1	long
    //   193	67	3	l2	long
    //   291	175	5	bool	boolean
    //   20	478	6	localObject1	Object
    //   507	3	6	localRuntimeException1	RuntimeException
    //   520	27	6	localObject2	Object
    //   556	3	6	localRuntimeException2	RuntimeException
    //   566	18	6	localObject3	Object
    //   596	3	6	localRuntimeException3	RuntimeException
    //   616	8	6	localObject4	Object
    //   629	1	6	localObject5	Object
    //   639	1	6	localSecurityException1	SecurityException
    //   642	1	6	localObject6	Object
    //   119	356	7	localObject7	Object
    //   484	1	7	localSecurityException2	SecurityException
    //   524	53	7	localObject8	Object
    //   586	3	7	localRuntimeException4	RuntimeException
    //   606	3	7	localJSONException1	JSONException
    //   625	1	7	localObject9	Object
    //   29	376	8	localJSONArray	JSONArray
    //   517	10	8	localRuntimeException5	RuntimeException
    //   621	7	8	localObject10	Object
    //   634	1	8	localRuntimeException6	RuntimeException
    //   8	546	9	localM	m
    //   160	85	10	str1	String
    //   470	7	10	localJSONException2	JSONException
    //   206	112	11	str2	String
    //   220	113	12	str3	String
    //   233	112	13	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   239	250	470	org/json/JSONException
    //   254	268	470	org/json/JSONException
    //   272	284	470	org/json/JSONException
    //   297	308	470	org/json/JSONException
    //   312	323	470	org/json/JSONException
    //   327	338	470	org/json/JSONException
    //   342	350	470	org/json/JSONException
    //   354	360	470	org/json/JSONException
    //   121	131	484	java/lang/SecurityException
    //   135	148	484	java/lang/SecurityException
    //   152	162	484	java/lang/SecurityException
    //   166	175	484	java/lang/SecurityException
    //   185	194	484	java/lang/SecurityException
    //   198	208	484	java/lang/SecurityException
    //   212	222	484	java/lang/SecurityException
    //   226	235	484	java/lang/SecurityException
    //   239	250	484	java/lang/SecurityException
    //   254	268	484	java/lang/SecurityException
    //   272	284	484	java/lang/SecurityException
    //   297	308	484	java/lang/SecurityException
    //   312	323	484	java/lang/SecurityException
    //   327	338	484	java/lang/SecurityException
    //   342	350	484	java/lang/SecurityException
    //   354	360	484	java/lang/SecurityException
    //   364	373	484	java/lang/SecurityException
    //   476	481	484	java/lang/SecurityException
    //   497	504	507	java/lang/RuntimeException
    //   31	112	517	java/lang/RuntimeException
    //   546	553	556	java/lang/RuntimeException
    //   31	112	566	finally
    //   576	583	586	java/lang/RuntimeException
    //   383	390	596	java/lang/RuntimeException
    //   399	410	606	org/json/JSONException
    //   121	131	616	finally
    //   135	148	616	finally
    //   152	162	616	finally
    //   166	175	616	finally
    //   185	194	616	finally
    //   198	208	616	finally
    //   212	222	616	finally
    //   226	235	616	finally
    //   239	250	616	finally
    //   254	268	616	finally
    //   272	284	616	finally
    //   297	308	616	finally
    //   312	323	616	finally
    //   327	338	616	finally
    //   342	350	616	finally
    //   354	360	616	finally
    //   364	373	616	finally
    //   476	481	616	finally
    //   526	531	616	finally
    //   535	541	616	finally
    //   486	492	621	finally
    //   121	131	634	java/lang/RuntimeException
    //   135	148	634	java/lang/RuntimeException
    //   152	162	634	java/lang/RuntimeException
    //   166	175	634	java/lang/RuntimeException
    //   185	194	634	java/lang/RuntimeException
    //   198	208	634	java/lang/RuntimeException
    //   212	222	634	java/lang/RuntimeException
    //   226	235	634	java/lang/RuntimeException
    //   239	250	634	java/lang/RuntimeException
    //   254	268	634	java/lang/RuntimeException
    //   272	284	634	java/lang/RuntimeException
    //   297	308	634	java/lang/RuntimeException
    //   312	323	634	java/lang/RuntimeException
    //   327	338	634	java/lang/RuntimeException
    //   342	350	634	java/lang/RuntimeException
    //   354	360	634	java/lang/RuntimeException
    //   364	373	634	java/lang/RuntimeException
    //   476	481	634	java/lang/RuntimeException
    //   31	112	639	java/lang/SecurityException
  }
  
  private static String e(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return "HOME";
    case 2: 
      return "WORK";
    case 3: 
      return "OTHER";
    }
    return null;
  }
  
  private m f()
  {
    m localM = new m(this);
    Object localObject2 = this.h.getContext().getPackageManager();
    List localList = ((PackageManager)localObject2).getInstalledPackages(0);
    Object localObject1 = new JSONArray();
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        localObject2 = new JSONObject();
      }
      try
      {
        ((JSONObject)localObject2).put("data", localObject1);
        localObject1 = new UsageData();
        ((UsageData)localObject1).type = "noah_info";
        ((UsageData)localObject1).path = a(3);
        ((UsageData)localObject1).value = ((JSONObject)localObject2).toString();
        localM.a = ((UsageData)localObject1);
        localM.c = a(3);
        return localM;
        Object localObject3 = (PackageInfo)localList.get(i);
        String str;
        JSONObject localJSONObject;
        if (((((PackageInfo)localObject3).applicationInfo.flags & 0x1) == 0) || ((((PackageInfo)localObject3).applicationInfo.flags & 0x80) != 0))
        {
          str = ((PackageInfo)localObject3).applicationInfo.loadLabel((PackageManager)localObject2).toString();
          localObject3 = ((PackageInfo)localObject3).packageName;
          localJSONObject = new JSONObject();
        }
        try
        {
          localJSONObject.put("name", str);
          localJSONObject.put("package_name", localObject3);
          ((JSONArray)localObject1).put(localJSONObject);
          localM.d = true;
          i += 1;
        }
        catch (JSONException localJSONException2)
        {
          for (;;)
          {
            localJSONException2.printStackTrace();
          }
        }
      }
      catch (JSONException localJSONException1)
      {
        for (;;)
        {
          localJSONException1.printStackTrace();
        }
      }
    }
  }
  
  private static String f(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return "HOME";
    case 2: 
      return "WORK";
    case 3: 
      return "OTHER";
    }
    return null;
  }
  
  /* Error */
  private m g()
  {
    // Byte code:
    //   0: new 95	com/cootek/usage/m
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 98	com/cootek/usage/m:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 13
    //   10: aload_0
    //   11: getfield 31	com/cootek/usage/g:h	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 104	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 108	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 10
    //   22: new 235	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 236	org/json/JSONArray:<init>	()V
    //   29: astore 14
    //   31: ldc_w 544
    //   34: invokestatic 491	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   37: astore 11
    //   39: invokestatic 445	com/cootek/usage/r:a	()Lcom/cootek/usage/r;
    //   42: iconst_1
    //   43: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   46: invokevirtual 448	com/cootek/usage/r:c	(Ljava/lang/String;)J
    //   49: lstore_3
    //   50: aload 10
    //   52: aload 11
    //   54: bipush 7
    //   56: anewarray 119	java/lang/String
    //   59: dup
    //   60: iconst_0
    //   61: ldc 121
    //   63: aastore
    //   64: dup
    //   65: iconst_1
    //   66: ldc_w 453
    //   69: aastore
    //   70: dup
    //   71: iconst_2
    //   72: ldc_w 384
    //   75: aastore
    //   76: dup
    //   77: iconst_3
    //   78: ldc_w 455
    //   81: aastore
    //   82: dup
    //   83: iconst_4
    //   84: ldc_w 370
    //   87: aastore
    //   88: dup
    //   89: iconst_5
    //   90: ldc -65
    //   92: aastore
    //   93: dup
    //   94: bipush 6
    //   96: ldc_w 546
    //   99: aastore
    //   100: ldc_w 457
    //   103: iconst_1
    //   104: anewarray 119	java/lang/String
    //   107: dup
    //   108: iconst_0
    //   109: lload_3
    //   110: invokestatic 460	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   113: aastore
    //   114: ldc_w 462
    //   117: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   120: astore 10
    //   122: aload 10
    //   124: ifnull +294 -> 418
    //   127: aload 10
    //   129: astore 11
    //   131: aload 10
    //   133: invokeinterface 134 1 0
    //   138: ifeq +280 -> 418
    //   141: aload 10
    //   143: astore 11
    //   145: aload 13
    //   147: aload 10
    //   149: iconst_0
    //   150: invokeinterface 143 2 0
    //   155: putfield 464	com/cootek/usage/m:b	J
    //   158: aload 10
    //   160: astore 11
    //   162: aload 10
    //   164: iconst_1
    //   165: invokeinterface 149 2 0
    //   170: astore 12
    //   172: aload 10
    //   174: astore 11
    //   176: aload 10
    //   178: iconst_2
    //   179: invokeinterface 143 2 0
    //   184: lstore 7
    //   186: aload 10
    //   188: astore 11
    //   190: aload 10
    //   192: iconst_3
    //   193: invokeinterface 143 2 0
    //   198: lstore_3
    //   199: aload 10
    //   201: astore 11
    //   203: aload 10
    //   205: iconst_4
    //   206: invokeinterface 293 2 0
    //   211: istore_1
    //   212: aload 10
    //   214: astore 11
    //   216: aload 10
    //   218: iconst_5
    //   219: invokeinterface 143 2 0
    //   224: lstore 5
    //   226: aload 10
    //   228: astore 11
    //   230: aload 10
    //   232: bipush 6
    //   234: invokeinterface 293 2 0
    //   239: istore_2
    //   240: aload 10
    //   242: astore 11
    //   244: new 333	org/json/JSONObject
    //   247: dup
    //   248: invokespecial 334	org/json/JSONObject:<init>	()V
    //   251: astore 15
    //   253: aload 10
    //   255: astore 11
    //   257: aload 15
    //   259: ldc_w 466
    //   262: aload 12
    //   264: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   267: pop
    //   268: aload 10
    //   270: astore 11
    //   272: aload 15
    //   274: ldc_w 384
    //   277: lload 7
    //   279: ldc2_w 467
    //   282: ldiv
    //   283: invokevirtual 471	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   286: pop
    //   287: iconst_3
    //   288: iload_1
    //   289: if_icmpne +5 -> 294
    //   292: lconst_0
    //   293: lstore_3
    //   294: aload 10
    //   296: astore 11
    //   298: aload 15
    //   300: ldc_w 455
    //   303: lload_3
    //   304: invokevirtual 471	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   307: pop
    //   308: lload 5
    //   310: lconst_0
    //   311: lcmp
    //   312: ifeq +192 -> 504
    //   315: iconst_1
    //   316: istore 9
    //   318: aload 10
    //   320: astore 11
    //   322: aload 15
    //   324: ldc_w 475
    //   327: iload 9
    //   329: invokevirtual 478	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   332: pop
    //   333: iload_1
    //   334: iconst_2
    //   335: if_icmpne +175 -> 510
    //   338: ldc_w 473
    //   341: astore 12
    //   343: aload 10
    //   345: astore 11
    //   347: aload 15
    //   349: ldc_w 370
    //   352: aload 12
    //   354: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   357: pop
    //   358: iload_2
    //   359: ifne +159 -> 518
    //   362: aload 10
    //   364: astore 11
    //   366: aload 15
    //   368: ldc_w 548
    //   371: ldc_w 550
    //   374: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   377: pop
    //   378: aload 10
    //   380: astore 11
    //   382: aload 14
    //   384: aload 15
    //   386: invokevirtual 363	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   389: pop
    //   390: aload 10
    //   392: astore 11
    //   394: aload 13
    //   396: iconst_1
    //   397: putfield 274	com/cootek/usage/m:d	Z
    //   400: aload 10
    //   402: astore 11
    //   404: aload 10
    //   406: invokeinterface 183 1 0
    //   411: istore 9
    //   413: iload 9
    //   415: ifne -257 -> 158
    //   418: aload 10
    //   420: ifnull +10 -> 430
    //   423: aload 10
    //   425: invokeinterface 186 1 0
    //   430: new 333	org/json/JSONObject
    //   433: dup
    //   434: invokespecial 334	org/json/JSONObject:<init>	()V
    //   437: astore 10
    //   439: aload 10
    //   441: ldc_w 480
    //   444: aload 14
    //   446: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   449: pop
    //   450: new 253	com/cootek/usage/UsageData
    //   453: dup
    //   454: invokespecial 254	com/cootek/usage/UsageData:<init>	()V
    //   457: astore 11
    //   459: aload 11
    //   461: ldc 21
    //   463: putfield 257	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   466: aload 11
    //   468: iconst_4
    //   469: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   472: putfield 262	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   475: aload 11
    //   477: aload 10
    //   479: invokevirtual 481	org/json/JSONObject:toString	()Ljava/lang/String;
    //   482: putfield 266	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   485: aload 13
    //   487: aload 11
    //   489: putfield 269	com/cootek/usage/m:a	Lcom/cootek/usage/UsageData;
    //   492: aload 13
    //   494: iconst_4
    //   495: invokestatic 259	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   498: putfield 271	com/cootek/usage/m:c	Ljava/lang/String;
    //   501: aload 13
    //   503: areturn
    //   504: iconst_0
    //   505: istore 9
    //   507: goto -189 -> 318
    //   510: ldc_w 483
    //   513: astore 12
    //   515: goto -172 -> 343
    //   518: iload_2
    //   519: iconst_1
    //   520: if_icmpne +59 -> 579
    //   523: aload 10
    //   525: astore 11
    //   527: aload 15
    //   529: ldc_w 548
    //   532: ldc_w 552
    //   535: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   538: pop
    //   539: goto -161 -> 378
    //   542: astore 12
    //   544: aload 10
    //   546: astore 11
    //   548: aload 12
    //   550: invokevirtual 364	org/json/JSONException:printStackTrace	()V
    //   553: goto -153 -> 400
    //   556: astore 11
    //   558: aload 13
    //   560: iconst_0
    //   561: putfield 274	com/cootek/usage/m:d	Z
    //   564: aload 10
    //   566: ifnull +10 -> 576
    //   569: aload 10
    //   571: invokeinterface 186 1 0
    //   576: aload 13
    //   578: areturn
    //   579: iload_2
    //   580: iconst_2
    //   581: if_icmpne +58 -> 639
    //   584: aload 10
    //   586: astore 11
    //   588: aload 15
    //   590: ldc_w 548
    //   593: ldc_w 554
    //   596: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   599: pop
    //   600: goto -222 -> 378
    //   603: astore 12
    //   605: aload 10
    //   607: astore 11
    //   609: aload 12
    //   611: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   614: aload 10
    //   616: astore 11
    //   618: aload 13
    //   620: iconst_0
    //   621: putfield 274	com/cootek/usage/m:d	Z
    //   624: aload 10
    //   626: ifnull +10 -> 636
    //   629: aload 10
    //   631: invokeinterface 186 1 0
    //   636: aload 13
    //   638: areturn
    //   639: iload_2
    //   640: iconst_3
    //   641: if_icmpne -263 -> 378
    //   644: aload 10
    //   646: astore 11
    //   648: aload 15
    //   650: ldc_w 548
    //   653: ldc_w 556
    //   656: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   659: pop
    //   660: goto -282 -> 378
    //   663: astore 10
    //   665: aload 11
    //   667: ifnull +10 -> 677
    //   670: aload 11
    //   672: invokeinterface 186 1 0
    //   677: aload 10
    //   679: athrow
    //   680: astore 10
    //   682: aload 10
    //   684: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   687: goto -111 -> 576
    //   690: astore 10
    //   692: aload 10
    //   694: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   697: goto -61 -> 636
    //   700: astore 11
    //   702: aload 11
    //   704: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   707: goto -30 -> 677
    //   710: astore 10
    //   712: aload 10
    //   714: invokevirtual 277	java/lang/RuntimeException:printStackTrace	()V
    //   717: goto -287 -> 430
    //   720: astore 11
    //   722: aload 11
    //   724: invokevirtual 364	org/json/JSONException:printStackTrace	()V
    //   727: goto -277 -> 450
    //   730: astore 10
    //   732: aconst_null
    //   733: astore 11
    //   735: goto -70 -> 665
    //   738: astore 12
    //   740: aload 10
    //   742: astore 11
    //   744: aload 12
    //   746: astore 10
    //   748: goto -83 -> 665
    //   751: astore 12
    //   753: aconst_null
    //   754: astore 10
    //   756: goto -151 -> 605
    //   759: astore 10
    //   761: aconst_null
    //   762: astore 10
    //   764: goto -206 -> 558
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	767	0	this	g
    //   211	125	1	i	int
    //   239	403	2	j	int
    //   49	255	3	l1	long
    //   224	85	5	l2	long
    //   184	94	7	l3	long
    //   316	190	9	bool	boolean
    //   20	625	10	localObject1	Object
    //   663	15	10	localObject2	Object
    //   680	3	10	localRuntimeException1	RuntimeException
    //   690	3	10	localRuntimeException2	RuntimeException
    //   710	3	10	localRuntimeException3	RuntimeException
    //   730	11	10	localObject3	Object
    //   746	9	10	localObject4	Object
    //   759	1	10	localSecurityException1	SecurityException
    //   762	1	10	localObject5	Object
    //   37	510	11	localObject6	Object
    //   556	1	11	localSecurityException2	SecurityException
    //   586	85	11	localObject7	Object
    //   700	3	11	localRuntimeException4	RuntimeException
    //   720	3	11	localJSONException1	JSONException
    //   733	10	11	localObject8	Object
    //   170	344	12	str	String
    //   542	7	12	localJSONException2	JSONException
    //   603	7	12	localRuntimeException5	RuntimeException
    //   738	7	12	localObject9	Object
    //   751	1	12	localRuntimeException6	RuntimeException
    //   8	629	13	localM	m
    //   29	416	14	localJSONArray	JSONArray
    //   251	398	15	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   257	268	542	org/json/JSONException
    //   272	287	542	org/json/JSONException
    //   298	308	542	org/json/JSONException
    //   322	333	542	org/json/JSONException
    //   347	358	542	org/json/JSONException
    //   366	378	542	org/json/JSONException
    //   382	390	542	org/json/JSONException
    //   394	400	542	org/json/JSONException
    //   527	539	542	org/json/JSONException
    //   588	600	542	org/json/JSONException
    //   648	660	542	org/json/JSONException
    //   131	141	556	java/lang/SecurityException
    //   145	158	556	java/lang/SecurityException
    //   162	172	556	java/lang/SecurityException
    //   176	186	556	java/lang/SecurityException
    //   190	199	556	java/lang/SecurityException
    //   203	212	556	java/lang/SecurityException
    //   216	226	556	java/lang/SecurityException
    //   230	240	556	java/lang/SecurityException
    //   244	253	556	java/lang/SecurityException
    //   257	268	556	java/lang/SecurityException
    //   272	287	556	java/lang/SecurityException
    //   298	308	556	java/lang/SecurityException
    //   322	333	556	java/lang/SecurityException
    //   347	358	556	java/lang/SecurityException
    //   366	378	556	java/lang/SecurityException
    //   382	390	556	java/lang/SecurityException
    //   394	400	556	java/lang/SecurityException
    //   404	413	556	java/lang/SecurityException
    //   527	539	556	java/lang/SecurityException
    //   548	553	556	java/lang/SecurityException
    //   588	600	556	java/lang/SecurityException
    //   648	660	556	java/lang/SecurityException
    //   131	141	603	java/lang/RuntimeException
    //   145	158	603	java/lang/RuntimeException
    //   162	172	603	java/lang/RuntimeException
    //   176	186	603	java/lang/RuntimeException
    //   190	199	603	java/lang/RuntimeException
    //   203	212	603	java/lang/RuntimeException
    //   216	226	603	java/lang/RuntimeException
    //   230	240	603	java/lang/RuntimeException
    //   244	253	603	java/lang/RuntimeException
    //   257	268	603	java/lang/RuntimeException
    //   272	287	603	java/lang/RuntimeException
    //   298	308	603	java/lang/RuntimeException
    //   322	333	603	java/lang/RuntimeException
    //   347	358	603	java/lang/RuntimeException
    //   366	378	603	java/lang/RuntimeException
    //   382	390	603	java/lang/RuntimeException
    //   394	400	603	java/lang/RuntimeException
    //   404	413	603	java/lang/RuntimeException
    //   527	539	603	java/lang/RuntimeException
    //   548	553	603	java/lang/RuntimeException
    //   588	600	603	java/lang/RuntimeException
    //   648	660	603	java/lang/RuntimeException
    //   131	141	663	finally
    //   145	158	663	finally
    //   162	172	663	finally
    //   176	186	663	finally
    //   190	199	663	finally
    //   203	212	663	finally
    //   216	226	663	finally
    //   230	240	663	finally
    //   244	253	663	finally
    //   257	268	663	finally
    //   272	287	663	finally
    //   298	308	663	finally
    //   322	333	663	finally
    //   347	358	663	finally
    //   366	378	663	finally
    //   382	390	663	finally
    //   394	400	663	finally
    //   404	413	663	finally
    //   527	539	663	finally
    //   548	553	663	finally
    //   588	600	663	finally
    //   609	614	663	finally
    //   618	624	663	finally
    //   648	660	663	finally
    //   569	576	680	java/lang/RuntimeException
    //   629	636	690	java/lang/RuntimeException
    //   670	677	700	java/lang/RuntimeException
    //   423	430	710	java/lang/RuntimeException
    //   439	450	720	org/json/JSONException
    //   31	122	730	finally
    //   558	564	738	finally
    //   31	122	751	java/lang/RuntimeException
    //   31	122	759	java/lang/SecurityException
  }
  
  private static String g(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 0: 
      return "AIM";
    case -1: 
      return null;
    case 5: 
      return "GOOGLE_TALK";
    case 6: 
      return "ICQ";
    case 7: 
      return "JABBER";
    case 1: 
      return "MSN";
    case 8: 
      return "NETMEETING";
    case 4: 
      return "QQ";
    case 3: 
      return "SKYPE";
    }
    return "YAHOO";
  }
  
  private static String h(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return "ANNIVERSARY";
    case 3: 
      return "BIRTHDAY";
    case 2: 
      return "OTHER";
    }
    return null;
  }
  
  private static String i(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return "ASSISTANT";
    case 2: 
      return "BROTHER";
    case 3: 
      return "CHILD";
    case 4: 
      return "DOMESTIC_PARTNER";
    case 5: 
      return "FATHER";
    case 6: 
      return "FRIEND";
    case 7: 
      return "MANAGER";
    case 8: 
      return "MOTHER";
    case 9: 
      return "PARENT";
    case 10: 
      return "PARTNER";
    case 11: 
      return "REFERRED_BY";
    case 12: 
      return "RELATIVE";
    case 13: 
      return "SISTER";
    case 14: 
      return "SPOUSE";
    }
    return null;
  }
  
  final m b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("error info value: " + paramInt);
    case 0: 
      return c();
    case 1: 
      return d();
    case 2: 
      return e();
    case 3: 
      return f();
    }
    return g();
  }
}
