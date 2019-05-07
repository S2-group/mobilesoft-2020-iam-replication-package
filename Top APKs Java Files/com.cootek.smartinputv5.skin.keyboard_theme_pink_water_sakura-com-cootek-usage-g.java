package com.cootek.usage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class g
{
  private static final int a = 0;
  private static final int b = 1;
  private static final int c = 2;
  private static final int d = 3;
  private static final int e = 4;
  private static final int f = 5;
  private static final int g = 6;
  private static final String h = "noah_info";
  private AbsUsageAssist i;
  
  g(AbsUsageAssist paramAbsUsageAssist)
  {
    this.i = paramAbsUsageAssist;
  }
  
  static int a()
  {
    return 6;
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
    case 4: 
      return "noah_reserve_04";
    }
    return "noah_reserve_05";
  }
  
  static String b()
  {
    return "noah_info";
  }
  
  /* Error */
  private n c()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: new 74	com/cootek/usage/n
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 77	com/cootek/usage/n:<init>	(Lcom/cootek/usage/g;)V
    //   14: astore 7
    //   16: aload_0
    //   17: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   20: invokevirtual 83	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   23: invokevirtual 89	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   26: astore 9
    //   28: new 91	java/util/Hashtable
    //   31: dup
    //   32: invokespecial 92	java/util/Hashtable:<init>	()V
    //   35: astore 8
    //   37: aload 9
    //   39: getstatic 98	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
    //   42: iconst_2
    //   43: anewarray 100	java/lang/String
    //   46: dup
    //   47: iconst_0
    //   48: ldc 102
    //   50: aastore
    //   51: dup
    //   52: iconst_1
    //   53: ldc 104
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore_3
    //   63: aload_3
    //   64: ifnull +197 -> 261
    //   67: aload_3
    //   68: astore 4
    //   70: aload_3
    //   71: invokeinterface 116 1 0
    //   76: ifeq +185 -> 261
    //   79: aload_3
    //   80: astore 4
    //   82: new 118	com/cootek/usage/j
    //   85: dup
    //   86: aload_0
    //   87: iconst_0
    //   88: invokespecial 121	com/cootek/usage/j:<init>	(Lcom/cootek/usage/g;B)V
    //   91: astore 10
    //   93: aload_3
    //   94: astore 4
    //   96: aload 10
    //   98: aload_3
    //   99: iconst_0
    //   100: invokeinterface 125 2 0
    //   105: putfield 128	com/cootek/usage/j:a	J
    //   108: aload_3
    //   109: astore 4
    //   111: aload 10
    //   113: aload_3
    //   114: iconst_1
    //   115: invokeinterface 131 2 0
    //   120: putfield 133	com/cootek/usage/j:b	Ljava/lang/String;
    //   123: aload_3
    //   124: astore 4
    //   126: aload 10
    //   128: new 135	java/util/HashSet
    //   131: dup
    //   132: invokespecial 136	java/util/HashSet:<init>	()V
    //   135: putfield 139	com/cootek/usage/j:c	Ljava/util/HashSet;
    //   138: aload_3
    //   139: astore 4
    //   141: aload 10
    //   143: new 135	java/util/HashSet
    //   146: dup
    //   147: invokespecial 136	java/util/HashSet:<init>	()V
    //   150: putfield 141	com/cootek/usage/j:d	Ljava/util/HashSet;
    //   153: aload_3
    //   154: astore 4
    //   156: aload 10
    //   158: new 135	java/util/HashSet
    //   161: dup
    //   162: invokespecial 136	java/util/HashSet:<init>	()V
    //   165: putfield 143	com/cootek/usage/j:e	Ljava/util/HashSet;
    //   168: aload_3
    //   169: astore 4
    //   171: aload 10
    //   173: new 135	java/util/HashSet
    //   176: dup
    //   177: invokespecial 136	java/util/HashSet:<init>	()V
    //   180: putfield 145	com/cootek/usage/j:f	Ljava/util/HashSet;
    //   183: aload_3
    //   184: astore 4
    //   186: aload 10
    //   188: new 135	java/util/HashSet
    //   191: dup
    //   192: invokespecial 136	java/util/HashSet:<init>	()V
    //   195: putfield 147	com/cootek/usage/j:g	Ljava/util/HashSet;
    //   198: aload_3
    //   199: astore 4
    //   201: aload 10
    //   203: new 135	java/util/HashSet
    //   206: dup
    //   207: invokespecial 136	java/util/HashSet:<init>	()V
    //   210: putfield 149	com/cootek/usage/j:h	Ljava/util/HashSet;
    //   213: aload_3
    //   214: astore 4
    //   216: aload 10
    //   218: new 135	java/util/HashSet
    //   221: dup
    //   222: invokespecial 136	java/util/HashSet:<init>	()V
    //   225: putfield 151	com/cootek/usage/j:i	Ljava/util/HashSet;
    //   228: aload_3
    //   229: astore 4
    //   231: aload 8
    //   233: aload 10
    //   235: getfield 128	com/cootek/usage/j:a	J
    //   238: invokestatic 157	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   241: aload 10
    //   243: invokevirtual 161	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   246: pop
    //   247: aload_3
    //   248: astore 4
    //   250: aload_3
    //   251: invokeinterface 164 1 0
    //   256: istore_2
    //   257: iload_2
    //   258: ifne -179 -> 79
    //   261: aload_3
    //   262: ifnull +9 -> 271
    //   265: aload_3
    //   266: invokeinterface 167 1 0
    //   271: aload 9
    //   273: getstatic 170	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   276: bipush 12
    //   278: anewarray 100	java/lang/String
    //   281: dup
    //   282: iconst_0
    //   283: ldc -84
    //   285: aastore
    //   286: dup
    //   287: iconst_1
    //   288: ldc -82
    //   290: aastore
    //   291: dup
    //   292: iconst_2
    //   293: ldc -80
    //   295: aastore
    //   296: dup
    //   297: iconst_3
    //   298: ldc -78
    //   300: aastore
    //   301: dup
    //   302: iconst_4
    //   303: ldc -76
    //   305: aastore
    //   306: dup
    //   307: iconst_5
    //   308: ldc -74
    //   310: aastore
    //   311: dup
    //   312: bipush 6
    //   314: ldc -72
    //   316: aastore
    //   317: dup
    //   318: bipush 7
    //   320: ldc -70
    //   322: aastore
    //   323: dup
    //   324: bipush 8
    //   326: ldc -68
    //   328: aastore
    //   329: dup
    //   330: bipush 9
    //   332: ldc -66
    //   334: aastore
    //   335: dup
    //   336: bipush 10
    //   338: ldc -64
    //   340: aastore
    //   341: dup
    //   342: bipush 11
    //   344: ldc -62
    //   346: aastore
    //   347: ldc -60
    //   349: bipush 7
    //   351: anewarray 100	java/lang/String
    //   354: dup
    //   355: iconst_0
    //   356: ldc -58
    //   358: aastore
    //   359: dup
    //   360: iconst_1
    //   361: ldc -56
    //   363: aastore
    //   364: dup
    //   365: iconst_2
    //   366: ldc -54
    //   368: aastore
    //   369: dup
    //   370: iconst_3
    //   371: ldc -52
    //   373: aastore
    //   374: dup
    //   375: iconst_4
    //   376: ldc -50
    //   378: aastore
    //   379: dup
    //   380: iconst_5
    //   381: ldc -48
    //   383: aastore
    //   384: dup
    //   385: bipush 6
    //   387: ldc -46
    //   389: aastore
    //   390: aconst_null
    //   391: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   394: astore 4
    //   396: aload 4
    //   398: ifnull +53 -> 451
    //   401: aload 4
    //   403: invokeinterface 116 1 0
    //   408: istore_2
    //   409: iload_2
    //   410: ifeq +41 -> 451
    //   413: aload 8
    //   415: aload 4
    //   417: iconst_0
    //   418: invokeinterface 125 2 0
    //   423: invokestatic 157	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   426: invokevirtual 214	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   429: checkcast 118	com/cootek/usage/j
    //   432: astore 5
    //   434: aload 5
    //   436: ifnonnull +281 -> 717
    //   439: aload 4
    //   441: invokeinterface 164 1 0
    //   446: istore_2
    //   447: iload_2
    //   448: ifne -35 -> 413
    //   451: aload 4
    //   453: ifnull +10 -> 463
    //   456: aload 4
    //   458: invokeinterface 167 1 0
    //   463: new 216	org/json/JSONArray
    //   466: dup
    //   467: invokespecial 217	org/json/JSONArray:<init>	()V
    //   470: astore_3
    //   471: aload 8
    //   473: invokevirtual 221	java/util/Hashtable:values	()Ljava/util/Collection;
    //   476: invokeinterface 227 1 0
    //   481: astore 4
    //   483: aload 4
    //   485: invokeinterface 232 1 0
    //   490: ifeq +2193 -> 2683
    //   493: aload 4
    //   495: invokeinterface 236 1 0
    //   500: checkcast 118	com/cootek/usage/j
    //   503: astore 6
    //   505: new 238	org/json/JSONObject
    //   508: dup
    //   509: invokespecial 239	org/json/JSONObject:<init>	()V
    //   512: astore 5
    //   514: aload 5
    //   516: ldc -15
    //   518: aload 6
    //   520: getfield 133	com/cootek/usage/j:b	Ljava/lang/String;
    //   523: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   526: pop
    //   527: aload 6
    //   529: getfield 139	com/cootek/usage/j:c	Ljava/util/HashSet;
    //   532: invokevirtual 247	java/util/HashSet:isEmpty	()Z
    //   535: ifne +1213 -> 1748
    //   538: new 216	org/json/JSONArray
    //   541: dup
    //   542: invokespecial 217	org/json/JSONArray:<init>	()V
    //   545: astore 8
    //   547: aload 6
    //   549: getfield 139	com/cootek/usage/j:c	Ljava/util/HashSet;
    //   552: invokevirtual 248	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   555: astore 9
    //   557: aload 9
    //   559: invokeinterface 232 1 0
    //   564: ifeq +1165 -> 1729
    //   567: aload 8
    //   569: aload 9
    //   571: invokeinterface 236 1 0
    //   576: checkcast 100	java/lang/String
    //   579: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   582: pop
    //   583: goto -26 -> 557
    //   586: astore 6
    //   588: aload 6
    //   590: invokevirtual 254	org/json/JSONException:printStackTrace	()V
    //   593: aload_3
    //   594: aload 5
    //   596: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   599: pop
    //   600: aload 7
    //   602: iconst_1
    //   603: putfield 257	com/cootek/usage/n:d	Z
    //   606: goto -123 -> 483
    //   609: astore_3
    //   610: aload_3
    //   611: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   614: goto -343 -> 271
    //   617: astore_3
    //   618: aconst_null
    //   619: astore_3
    //   620: aload 7
    //   622: iconst_0
    //   623: putfield 257	com/cootek/usage/n:d	Z
    //   626: aload_3
    //   627: ifnull +9 -> 636
    //   630: aload_3
    //   631: invokeinterface 167 1 0
    //   636: aload 7
    //   638: areturn
    //   639: astore_3
    //   640: aload_3
    //   641: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   644: goto -8 -> 636
    //   647: astore 5
    //   649: aconst_null
    //   650: astore_3
    //   651: aload_3
    //   652: astore 4
    //   654: aload 5
    //   656: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   659: aload_3
    //   660: astore 4
    //   662: aload 7
    //   664: iconst_0
    //   665: putfield 257	com/cootek/usage/n:d	Z
    //   668: aload_3
    //   669: ifnull +9 -> 678
    //   672: aload_3
    //   673: invokeinterface 167 1 0
    //   678: aload 7
    //   680: areturn
    //   681: astore_3
    //   682: aload_3
    //   683: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   686: goto -8 -> 678
    //   689: astore_3
    //   690: aconst_null
    //   691: astore 4
    //   693: aload 4
    //   695: ifnull +10 -> 705
    //   698: aload 4
    //   700: invokeinterface 167 1 0
    //   705: aload_3
    //   706: athrow
    //   707: astore 4
    //   709: aload 4
    //   711: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   714: goto -9 -> 705
    //   717: aload 4
    //   719: iconst_1
    //   720: invokeinterface 131 2 0
    //   725: astore_3
    //   726: ldc -58
    //   728: aload_3
    //   729: invokevirtual 262	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   732: ifeq +58 -> 790
    //   735: aload 4
    //   737: iconst_2
    //   738: invokeinterface 131 2 0
    //   743: astore_3
    //   744: aload 5
    //   746: getfield 139	com/cootek/usage/j:c	Ljava/util/HashSet;
    //   749: aload_3
    //   750: invokevirtual 265	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   753: pop
    //   754: goto -315 -> 439
    //   757: astore_3
    //   758: aload_3
    //   759: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   762: goto -323 -> 439
    //   765: astore_3
    //   766: aload 4
    //   768: astore_3
    //   769: aload 7
    //   771: iconst_0
    //   772: putfield 257	com/cootek/usage/n:d	Z
    //   775: aload 4
    //   777: ifnull +10 -> 787
    //   780: aload 4
    //   782: invokeinterface 167 1 0
    //   787: aload 7
    //   789: areturn
    //   790: ldc -56
    //   792: aload_3
    //   793: invokevirtual 262	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   796: ifeq +131 -> 927
    //   799: new 267	com/cootek/usage/k
    //   802: dup
    //   803: aload_0
    //   804: iconst_0
    //   805: invokespecial 268	com/cootek/usage/k:<init>	(Lcom/cootek/usage/g;B)V
    //   808: astore 6
    //   810: aload 6
    //   812: aload 4
    //   814: iconst_2
    //   815: invokeinterface 131 2 0
    //   820: putfield 270	com/cootek/usage/k:a	Ljava/lang/String;
    //   823: aload 4
    //   825: iconst_3
    //   826: invokeinterface 274 2 0
    //   831: tableswitch	default:+2052->2883, 0:+2080->2911, 1:+89->920, 2:+2059->2890, 3:+2066->2897, 4:+2073->2904
    //   864: aload 6
    //   866: aload_3
    //   867: putfield 275	com/cootek/usage/k:b	Ljava/lang/String;
    //   870: aload 6
    //   872: getfield 275	com/cootek/usage/k:b	Ljava/lang/String;
    //   875: ifnonnull +16 -> 891
    //   878: aload 6
    //   880: aload 4
    //   882: iconst_4
    //   883: invokeinterface 131 2 0
    //   888: putfield 275	com/cootek/usage/k:b	Ljava/lang/String;
    //   891: aload 5
    //   893: getfield 141	com/cootek/usage/j:d	Ljava/util/HashSet;
    //   896: aload 6
    //   898: invokevirtual 265	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   901: pop
    //   902: goto -463 -> 439
    //   905: astore_3
    //   906: aload 4
    //   908: ifnull +10 -> 918
    //   911: aload 4
    //   913: invokeinterface 167 1 0
    //   918: aload_3
    //   919: athrow
    //   920: ldc_w 277
    //   923: astore_3
    //   924: goto -60 -> 864
    //   927: ldc -54
    //   929: aload_3
    //   930: invokevirtual 262	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   933: ifeq +128 -> 1061
    //   936: new 279	com/cootek/usage/o
    //   939: dup
    //   940: aload_0
    //   941: iconst_0
    //   942: invokespecial 280	com/cootek/usage/o:<init>	(Lcom/cootek/usage/g;B)V
    //   945: astore 6
    //   947: aload 6
    //   949: aload 4
    //   951: iconst_2
    //   952: invokeinterface 131 2 0
    //   957: putfield 281	com/cootek/usage/o:b	Ljava/lang/String;
    //   960: aload 6
    //   962: aload 4
    //   964: iconst_5
    //   965: invokeinterface 131 2 0
    //   970: putfield 283	com/cootek/usage/o:c	Ljava/lang/String;
    //   973: aload 6
    //   975: aload 4
    //   977: bipush 6
    //   979: invokeinterface 131 2 0
    //   984: putfield 285	com/cootek/usage/o:d	Ljava/lang/String;
    //   987: aload 4
    //   989: iconst_3
    //   990: invokeinterface 274 2 0
    //   995: tableswitch	default:+1921->2916, 0:+1942->2937, 1:+1928->2923, 2:+1935->2930
    //   1020: aload 6
    //   1022: aload_3
    //   1023: putfield 286	com/cootek/usage/o:a	Ljava/lang/String;
    //   1026: aload 6
    //   1028: getfield 286	com/cootek/usage/o:a	Ljava/lang/String;
    //   1031: ifnonnull +16 -> 1047
    //   1034: aload 6
    //   1036: aload 4
    //   1038: iconst_4
    //   1039: invokeinterface 131 2 0
    //   1044: putfield 286	com/cootek/usage/o:a	Ljava/lang/String;
    //   1047: aload 5
    //   1049: getfield 143	com/cootek/usage/j:e	Ljava/util/HashSet;
    //   1052: aload 6
    //   1054: invokevirtual 265	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1057: pop
    //   1058: goto -619 -> 439
    //   1061: ldc -52
    //   1063: aload_3
    //   1064: invokevirtual 262	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1067: ifeq +199 -> 1266
    //   1070: new 288	com/cootek/usage/m
    //   1073: dup
    //   1074: aload_0
    //   1075: iconst_0
    //   1076: invokespecial 289	com/cootek/usage/m:<init>	(Lcom/cootek/usage/g;B)V
    //   1079: astore 6
    //   1081: aload 6
    //   1083: aload 4
    //   1085: iconst_2
    //   1086: invokeinterface 131 2 0
    //   1091: putfield 290	com/cootek/usage/m:a	Ljava/lang/String;
    //   1094: aload 4
    //   1096: iconst_3
    //   1097: invokeinterface 274 2 0
    //   1102: tableswitch	default:+1840->2942, 0:+1875->2977, 1:+1854->2956, 2:+1861->2963, 3:+1868->2970
    //   1132: aload 6
    //   1134: aload_3
    //   1135: putfield 291	com/cootek/usage/m:b	Ljava/lang/String;
    //   1138: aload 6
    //   1140: getfield 291	com/cootek/usage/m:b	Ljava/lang/String;
    //   1143: ifnonnull +16 -> 1159
    //   1146: aload 6
    //   1148: aload 4
    //   1150: iconst_4
    //   1151: invokeinterface 131 2 0
    //   1156: putfield 291	com/cootek/usage/m:b	Ljava/lang/String;
    //   1159: aload 4
    //   1161: bipush 6
    //   1163: invokeinterface 274 2 0
    //   1168: tableswitch	default:+1781->2949, -1:+1821->2989, 0:+1814->2982, 1:+1847->3015, 2:+1875->3043, 3:+1868->3036, 4:+1861->3029, 5:+1826->2994, 6:+1833->3001, 7:+1840->3008, 8:+1854->3022
    //   1224: aload 6
    //   1226: aload_3
    //   1227: putfield 292	com/cootek/usage/m:c	Ljava/lang/String;
    //   1230: aload 6
    //   1232: getfield 292	com/cootek/usage/m:c	Ljava/lang/String;
    //   1235: ifnonnull +17 -> 1252
    //   1238: aload 6
    //   1240: aload 4
    //   1242: bipush 7
    //   1244: invokeinterface 131 2 0
    //   1249: putfield 292	com/cootek/usage/m:c	Ljava/lang/String;
    //   1252: aload 5
    //   1254: getfield 145	com/cootek/usage/j:f	Ljava/util/HashSet;
    //   1257: aload 6
    //   1259: invokevirtual 265	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1262: pop
    //   1263: goto -824 -> 439
    //   1266: ldc -50
    //   1268: aload_3
    //   1269: invokevirtual 262	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1272: ifeq +105 -> 1377
    //   1275: new 294	com/cootek/usage/i
    //   1278: dup
    //   1279: aload_0
    //   1280: iconst_0
    //   1281: invokespecial 295	com/cootek/usage/i:<init>	(Lcom/cootek/usage/g;B)V
    //   1284: astore 6
    //   1286: aload 6
    //   1288: aload 4
    //   1290: iconst_2
    //   1291: invokeinterface 131 2 0
    //   1296: putfield 296	com/cootek/usage/i:a	Ljava/lang/String;
    //   1299: aload 4
    //   1301: iconst_3
    //   1302: invokeinterface 274 2 0
    //   1307: tableswitch	default:+1743->3050, 0:+1771->3078, 1:+1750->3057, 2:+1757->3064, 3:+1764->3071
    //   1336: aload 6
    //   1338: aload_3
    //   1339: putfield 297	com/cootek/usage/i:b	Ljava/lang/String;
    //   1342: aload 6
    //   1344: getfield 297	com/cootek/usage/i:b	Ljava/lang/String;
    //   1347: ifnonnull +16 -> 1363
    //   1350: aload 6
    //   1352: aload 4
    //   1354: iconst_4
    //   1355: invokeinterface 131 2 0
    //   1360: putfield 297	com/cootek/usage/i:b	Ljava/lang/String;
    //   1363: aload 5
    //   1365: getfield 147	com/cootek/usage/j:g	Ljava/util/HashSet;
    //   1368: aload 6
    //   1370: invokevirtual 265	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1373: pop
    //   1374: goto -935 -> 439
    //   1377: ldc -48
    //   1379: aload_3
    //   1380: invokevirtual 262	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1383: ifeq +106 -> 1489
    //   1386: new 299	com/cootek/usage/l
    //   1389: dup
    //   1390: aload_0
    //   1391: iconst_0
    //   1392: invokespecial 300	com/cootek/usage/l:<init>	(Lcom/cootek/usage/g;B)V
    //   1395: astore 6
    //   1397: aload 6
    //   1399: aload 4
    //   1401: iconst_2
    //   1402: invokeinterface 131 2 0
    //   1407: putfield 301	com/cootek/usage/l:a	Ljava/lang/String;
    //   1410: aload 4
    //   1412: iconst_3
    //   1413: invokeinterface 274 2 0
    //   1418: tableswitch	default:+1665->3083, 0:+1693->3111, 1:+1672->3090, 2:+1686->3104, 3:+1679->3097
    //   1448: aload 6
    //   1450: aload_3
    //   1451: putfield 302	com/cootek/usage/l:b	Ljava/lang/String;
    //   1454: aload 6
    //   1456: getfield 302	com/cootek/usage/l:b	Ljava/lang/String;
    //   1459: ifnonnull +16 -> 1475
    //   1462: aload 6
    //   1464: aload 4
    //   1466: iconst_4
    //   1467: invokeinterface 131 2 0
    //   1472: putfield 302	com/cootek/usage/l:b	Ljava/lang/String;
    //   1475: aload 5
    //   1477: getfield 149	com/cootek/usage/j:h	Ljava/util/HashSet;
    //   1480: aload 6
    //   1482: invokevirtual 265	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1485: pop
    //   1486: goto -1047 -> 439
    //   1489: ldc -46
    //   1491: aload_3
    //   1492: invokevirtual 262	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1495: ifeq -1056 -> 439
    //   1498: new 304	com/cootek/usage/p
    //   1501: dup
    //   1502: aload_0
    //   1503: iconst_0
    //   1504: invokespecial 305	com/cootek/usage/p:<init>	(Lcom/cootek/usage/g;B)V
    //   1507: astore 6
    //   1509: aload 6
    //   1511: aload 4
    //   1513: iconst_2
    //   1514: invokeinterface 131 2 0
    //   1519: putfield 306	com/cootek/usage/p:a	Ljava/lang/String;
    //   1522: aload 4
    //   1524: iconst_3
    //   1525: invokeinterface 274 2 0
    //   1530: tableswitch	default:+1586->3116, 0:+122->1652, 1:+1593->3123, 2:+1600->3130, 3:+1607->3137, 4:+1614->3144, 5:+1621->3151, 6:+1628->3158, 7:+1635->3165, 8:+1642->3172, 9:+1649->3179, 10:+1656->3186, 11:+1663->3193, 12:+1670->3200, 13:+1677->3207, 14:+115->1645
    //   1604: aload 6
    //   1606: aload_3
    //   1607: putfield 307	com/cootek/usage/p:b	Ljava/lang/String;
    //   1610: aload 6
    //   1612: getfield 307	com/cootek/usage/p:b	Ljava/lang/String;
    //   1615: ifnonnull +16 -> 1631
    //   1618: aload 6
    //   1620: aload 4
    //   1622: iconst_4
    //   1623: invokeinterface 131 2 0
    //   1628: putfield 307	com/cootek/usage/p:b	Ljava/lang/String;
    //   1631: aload 5
    //   1633: getfield 151	com/cootek/usage/j:i	Ljava/util/HashSet;
    //   1636: aload 6
    //   1638: invokevirtual 265	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1641: pop
    //   1642: goto -1203 -> 439
    //   1645: ldc_w 309
    //   1648: astore_3
    //   1649: goto -45 -> 1604
    //   1652: aconst_null
    //   1653: astore_3
    //   1654: goto -50 -> 1604
    //   1657: astore_3
    //   1658: aload_3
    //   1659: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   1662: goto -1199 -> 463
    //   1665: astore_3
    //   1666: aload_3
    //   1667: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   1670: goto -883 -> 787
    //   1673: astore 5
    //   1675: aload 6
    //   1677: astore 4
    //   1679: aload 4
    //   1681: astore_3
    //   1682: aload 5
    //   1684: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   1687: aload 4
    //   1689: astore_3
    //   1690: aload 7
    //   1692: iconst_0
    //   1693: putfield 257	com/cootek/usage/n:d	Z
    //   1696: aload 4
    //   1698: ifnull +10 -> 1708
    //   1701: aload 4
    //   1703: invokeinterface 167 1 0
    //   1708: aload 7
    //   1710: areturn
    //   1711: astore_3
    //   1712: aload_3
    //   1713: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   1716: goto -8 -> 1708
    //   1719: astore 4
    //   1721: aload 4
    //   1723: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   1726: goto -808 -> 918
    //   1729: aload 8
    //   1731: invokevirtual 312	org/json/JSONArray:length	()I
    //   1734: ifle +14 -> 1748
    //   1737: aload 5
    //   1739: ldc_w 314
    //   1742: aload 8
    //   1744: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1747: pop
    //   1748: aload 6
    //   1750: getfield 141	com/cootek/usage/j:d	Ljava/util/HashSet;
    //   1753: invokevirtual 247	java/util/HashSet:isEmpty	()Z
    //   1756: ifne +133 -> 1889
    //   1759: new 216	org/json/JSONArray
    //   1762: dup
    //   1763: invokespecial 217	org/json/JSONArray:<init>	()V
    //   1766: astore 8
    //   1768: aload 6
    //   1770: getfield 141	com/cootek/usage/j:d	Ljava/util/HashSet;
    //   1773: invokevirtual 248	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1776: astore 9
    //   1778: aload 9
    //   1780: invokeinterface 232 1 0
    //   1785: ifeq +85 -> 1870
    //   1788: aload 9
    //   1790: invokeinterface 236 1 0
    //   1795: checkcast 267	com/cootek/usage/k
    //   1798: astore 10
    //   1800: new 238	org/json/JSONObject
    //   1803: dup
    //   1804: invokespecial 239	org/json/JSONObject:<init>	()V
    //   1807: astore 11
    //   1809: aload 10
    //   1811: getfield 270	com/cootek/usage/k:a	Ljava/lang/String;
    //   1814: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1817: ifne -39 -> 1778
    //   1820: aload 11
    //   1822: ldc_w 321
    //   1825: aload 10
    //   1827: getfield 270	com/cootek/usage/k:a	Ljava/lang/String;
    //   1830: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1833: pop
    //   1834: aload 10
    //   1836: getfield 275	com/cootek/usage/k:b	Ljava/lang/String;
    //   1839: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1842: ifne +17 -> 1859
    //   1845: aload 11
    //   1847: ldc_w 323
    //   1850: aload 10
    //   1852: getfield 275	com/cootek/usage/k:b	Ljava/lang/String;
    //   1855: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1858: pop
    //   1859: aload 8
    //   1861: aload 11
    //   1863: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1866: pop
    //   1867: goto -89 -> 1778
    //   1870: aload 8
    //   1872: invokevirtual 312	org/json/JSONArray:length	()I
    //   1875: ifle +14 -> 1889
    //   1878: aload 5
    //   1880: ldc_w 325
    //   1883: aload 8
    //   1885: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1888: pop
    //   1889: aload 6
    //   1891: getfield 143	com/cootek/usage/j:e	Ljava/util/HashSet;
    //   1894: invokevirtual 247	java/util/HashSet:isEmpty	()Z
    //   1897: ifne +195 -> 2092
    //   1900: new 216	org/json/JSONArray
    //   1903: dup
    //   1904: invokespecial 217	org/json/JSONArray:<init>	()V
    //   1907: astore 8
    //   1909: aload 6
    //   1911: getfield 143	com/cootek/usage/j:e	Ljava/util/HashSet;
    //   1914: invokevirtual 248	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1917: astore 9
    //   1919: aload 9
    //   1921: invokeinterface 232 1 0
    //   1926: ifeq +147 -> 2073
    //   1929: aload 9
    //   1931: invokeinterface 236 1 0
    //   1936: checkcast 279	com/cootek/usage/o
    //   1939: astore 10
    //   1941: new 238	org/json/JSONObject
    //   1944: dup
    //   1945: invokespecial 239	org/json/JSONObject:<init>	()V
    //   1948: astore 11
    //   1950: aload 10
    //   1952: getfield 286	com/cootek/usage/o:a	Ljava/lang/String;
    //   1955: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1958: ifne +920 -> 2878
    //   1961: aload 11
    //   1963: ldc_w 323
    //   1966: aload 10
    //   1968: getfield 286	com/cootek/usage/o:a	Ljava/lang/String;
    //   1971: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1974: pop
    //   1975: iconst_1
    //   1976: istore_1
    //   1977: aload 10
    //   1979: getfield 286	com/cootek/usage/o:a	Ljava/lang/String;
    //   1982: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1985: ifne +19 -> 2004
    //   1988: aload 11
    //   1990: ldc_w 327
    //   1993: aload 10
    //   1995: getfield 281	com/cootek/usage/o:b	Ljava/lang/String;
    //   1998: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2001: pop
    //   2002: iconst_1
    //   2003: istore_1
    //   2004: aload 10
    //   2006: getfield 286	com/cootek/usage/o:a	Ljava/lang/String;
    //   2009: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2012: ifne +19 -> 2031
    //   2015: aload 11
    //   2017: ldc_w 329
    //   2020: aload 10
    //   2022: getfield 283	com/cootek/usage/o:c	Ljava/lang/String;
    //   2025: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2028: pop
    //   2029: iconst_1
    //   2030: istore_1
    //   2031: aload 10
    //   2033: getfield 286	com/cootek/usage/o:a	Ljava/lang/String;
    //   2036: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2039: ifne +836 -> 2875
    //   2042: aload 11
    //   2044: ldc_w 331
    //   2047: aload 10
    //   2049: getfield 285	com/cootek/usage/o:d	Ljava/lang/String;
    //   2052: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2055: pop
    //   2056: iconst_1
    //   2057: istore_1
    //   2058: iload_1
    //   2059: ifeq -140 -> 1919
    //   2062: aload 8
    //   2064: aload 11
    //   2066: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2069: pop
    //   2070: goto -151 -> 1919
    //   2073: aload 8
    //   2075: invokevirtual 312	org/json/JSONArray:length	()I
    //   2078: ifle +14 -> 2092
    //   2081: aload 5
    //   2083: ldc_w 333
    //   2086: aload 8
    //   2088: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2091: pop
    //   2092: aload 6
    //   2094: getfield 145	com/cootek/usage/j:f	Ljava/util/HashSet;
    //   2097: invokevirtual 247	java/util/HashSet:isEmpty	()Z
    //   2100: ifne +158 -> 2258
    //   2103: new 216	org/json/JSONArray
    //   2106: dup
    //   2107: invokespecial 217	org/json/JSONArray:<init>	()V
    //   2110: astore 8
    //   2112: aload 6
    //   2114: getfield 145	com/cootek/usage/j:f	Ljava/util/HashSet;
    //   2117: invokevirtual 248	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2120: astore 9
    //   2122: aload 9
    //   2124: invokeinterface 232 1 0
    //   2129: ifeq +118 -> 2247
    //   2132: aload 9
    //   2134: invokeinterface 236 1 0
    //   2139: checkcast 288	com/cootek/usage/m
    //   2142: astore 10
    //   2144: new 238	org/json/JSONObject
    //   2147: dup
    //   2148: invokespecial 239	org/json/JSONObject:<init>	()V
    //   2151: astore 11
    //   2153: aload 10
    //   2155: getfield 290	com/cootek/usage/m:a	Ljava/lang/String;
    //   2158: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2161: ifne -39 -> 2122
    //   2164: aload 11
    //   2166: ldc_w 335
    //   2169: aload 10
    //   2171: getfield 290	com/cootek/usage/m:a	Ljava/lang/String;
    //   2174: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2177: pop
    //   2178: aload 10
    //   2180: getfield 291	com/cootek/usage/m:b	Ljava/lang/String;
    //   2183: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2186: ifne +17 -> 2203
    //   2189: aload 11
    //   2191: ldc_w 323
    //   2194: aload 10
    //   2196: getfield 291	com/cootek/usage/m:b	Ljava/lang/String;
    //   2199: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2202: pop
    //   2203: aload 10
    //   2205: getfield 292	com/cootek/usage/m:c	Ljava/lang/String;
    //   2208: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2211: ifne +17 -> 2228
    //   2214: aload 11
    //   2216: ldc_w 337
    //   2219: aload 10
    //   2221: getfield 292	com/cootek/usage/m:c	Ljava/lang/String;
    //   2224: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2227: pop
    //   2228: aload 8
    //   2230: invokevirtual 312	org/json/JSONArray:length	()I
    //   2233: ifle -111 -> 2122
    //   2236: aload 8
    //   2238: aload 11
    //   2240: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2243: pop
    //   2244: goto -122 -> 2122
    //   2247: aload 5
    //   2249: ldc_w 339
    //   2252: aload 8
    //   2254: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2257: pop
    //   2258: aload 6
    //   2260: getfield 147	com/cootek/usage/j:g	Ljava/util/HashSet;
    //   2263: invokevirtual 247	java/util/HashSet:isEmpty	()Z
    //   2266: ifne +133 -> 2399
    //   2269: new 216	org/json/JSONArray
    //   2272: dup
    //   2273: invokespecial 217	org/json/JSONArray:<init>	()V
    //   2276: astore 8
    //   2278: aload 6
    //   2280: getfield 147	com/cootek/usage/j:g	Ljava/util/HashSet;
    //   2283: invokevirtual 248	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2286: astore 9
    //   2288: aload 9
    //   2290: invokeinterface 232 1 0
    //   2295: ifeq +85 -> 2380
    //   2298: aload 9
    //   2300: invokeinterface 236 1 0
    //   2305: checkcast 294	com/cootek/usage/i
    //   2308: astore 10
    //   2310: new 238	org/json/JSONObject
    //   2313: dup
    //   2314: invokespecial 239	org/json/JSONObject:<init>	()V
    //   2317: astore 11
    //   2319: aload 10
    //   2321: getfield 296	com/cootek/usage/i:a	Ljava/lang/String;
    //   2324: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2327: ifne -39 -> 2288
    //   2330: aload 11
    //   2332: ldc_w 341
    //   2335: aload 10
    //   2337: getfield 296	com/cootek/usage/i:a	Ljava/lang/String;
    //   2340: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2343: pop
    //   2344: aload 10
    //   2346: getfield 297	com/cootek/usage/i:b	Ljava/lang/String;
    //   2349: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2352: ifne +17 -> 2369
    //   2355: aload 11
    //   2357: ldc_w 323
    //   2360: aload 10
    //   2362: getfield 297	com/cootek/usage/i:b	Ljava/lang/String;
    //   2365: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2368: pop
    //   2369: aload 8
    //   2371: aload 11
    //   2373: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2376: pop
    //   2377: goto -89 -> 2288
    //   2380: aload 8
    //   2382: invokevirtual 312	org/json/JSONArray:length	()I
    //   2385: ifle +14 -> 2399
    //   2388: aload 5
    //   2390: ldc_w 321
    //   2393: aload 8
    //   2395: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2398: pop
    //   2399: aload 6
    //   2401: getfield 149	com/cootek/usage/j:h	Ljava/util/HashSet;
    //   2404: invokevirtual 247	java/util/HashSet:isEmpty	()Z
    //   2407: ifne +133 -> 2540
    //   2410: new 216	org/json/JSONArray
    //   2413: dup
    //   2414: invokespecial 217	org/json/JSONArray:<init>	()V
    //   2417: astore 8
    //   2419: aload 6
    //   2421: getfield 149	com/cootek/usage/j:h	Ljava/util/HashSet;
    //   2424: invokevirtual 248	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2427: astore 9
    //   2429: aload 9
    //   2431: invokeinterface 232 1 0
    //   2436: ifeq +85 -> 2521
    //   2439: aload 9
    //   2441: invokeinterface 236 1 0
    //   2446: checkcast 299	com/cootek/usage/l
    //   2449: astore 10
    //   2451: new 238	org/json/JSONObject
    //   2454: dup
    //   2455: invokespecial 239	org/json/JSONObject:<init>	()V
    //   2458: astore 11
    //   2460: aload 10
    //   2462: getfield 301	com/cootek/usage/l:a	Ljava/lang/String;
    //   2465: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2468: ifne -39 -> 2429
    //   2471: aload 11
    //   2473: ldc_w 343
    //   2476: aload 10
    //   2478: getfield 301	com/cootek/usage/l:a	Ljava/lang/String;
    //   2481: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2484: pop
    //   2485: aload 10
    //   2487: getfield 302	com/cootek/usage/l:b	Ljava/lang/String;
    //   2490: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2493: ifne +17 -> 2510
    //   2496: aload 11
    //   2498: ldc_w 323
    //   2501: aload 10
    //   2503: getfield 302	com/cootek/usage/l:b	Ljava/lang/String;
    //   2506: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2509: pop
    //   2510: aload 8
    //   2512: aload 11
    //   2514: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2517: pop
    //   2518: goto -89 -> 2429
    //   2521: aload 8
    //   2523: invokevirtual 312	org/json/JSONArray:length	()I
    //   2526: ifle +14 -> 2540
    //   2529: aload 5
    //   2531: ldc_w 345
    //   2534: aload 8
    //   2536: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2539: pop
    //   2540: aload 6
    //   2542: getfield 151	com/cootek/usage/j:i	Ljava/util/HashSet;
    //   2545: invokevirtual 247	java/util/HashSet:isEmpty	()Z
    //   2548: ifne -1955 -> 593
    //   2551: new 216	org/json/JSONArray
    //   2554: dup
    //   2555: invokespecial 217	org/json/JSONArray:<init>	()V
    //   2558: astore 8
    //   2560: aload 6
    //   2562: getfield 151	com/cootek/usage/j:i	Ljava/util/HashSet;
    //   2565: invokevirtual 248	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2568: astore 6
    //   2570: aload 6
    //   2572: invokeinterface 232 1 0
    //   2577: ifeq +84 -> 2661
    //   2580: aload 6
    //   2582: invokeinterface 236 1 0
    //   2587: checkcast 304	com/cootek/usage/p
    //   2590: astore 9
    //   2592: new 238	org/json/JSONObject
    //   2595: dup
    //   2596: invokespecial 239	org/json/JSONObject:<init>	()V
    //   2599: astore 10
    //   2601: aload 9
    //   2603: getfield 306	com/cootek/usage/p:a	Ljava/lang/String;
    //   2606: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2609: ifne -39 -> 2570
    //   2612: aload 10
    //   2614: ldc -15
    //   2616: aload 9
    //   2618: getfield 306	com/cootek/usage/p:a	Ljava/lang/String;
    //   2621: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2624: pop
    //   2625: aload 9
    //   2627: getfield 307	com/cootek/usage/p:b	Ljava/lang/String;
    //   2630: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2633: ifne +17 -> 2650
    //   2636: aload 10
    //   2638: ldc_w 323
    //   2641: aload 9
    //   2643: getfield 307	com/cootek/usage/p:b	Ljava/lang/String;
    //   2646: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2649: pop
    //   2650: aload 8
    //   2652: aload 10
    //   2654: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2657: pop
    //   2658: goto -88 -> 2570
    //   2661: aload 8
    //   2663: invokevirtual 312	org/json/JSONArray:length	()I
    //   2666: ifle -2073 -> 593
    //   2669: aload 5
    //   2671: ldc_w 347
    //   2674: aload 8
    //   2676: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2679: pop
    //   2680: goto -2087 -> 593
    //   2683: aload_0
    //   2684: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   2687: invokevirtual 350	com/cootek/usage/AbsUsageAssist:getPhoneAccount	()Ljava/lang/String;
    //   2690: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2693: ifne +63 -> 2756
    //   2696: new 238	org/json/JSONObject
    //   2699: dup
    //   2700: invokespecial 239	org/json/JSONObject:<init>	()V
    //   2703: astore 4
    //   2705: aload 4
    //   2707: ldc -15
    //   2709: ldc_w 352
    //   2712: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2715: pop
    //   2716: new 216	org/json/JSONArray
    //   2719: dup
    //   2720: invokespecial 217	org/json/JSONArray:<init>	()V
    //   2723: astore 5
    //   2725: aload 5
    //   2727: aload_0
    //   2728: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   2731: invokevirtual 350	com/cootek/usage/AbsUsageAssist:getPhoneAccount	()Ljava/lang/String;
    //   2734: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2737: pop
    //   2738: aload 4
    //   2740: ldc_w 314
    //   2743: aload 5
    //   2745: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2748: pop
    //   2749: aload_3
    //   2750: aload 4
    //   2752: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2755: pop
    //   2756: new 354	com/cootek/usage/UsageData
    //   2759: dup
    //   2760: invokespecial 355	com/cootek/usage/UsageData:<init>	()V
    //   2763: astore 4
    //   2765: aload 4
    //   2767: ldc 23
    //   2769: putfield 357	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   2772: aload 4
    //   2774: iconst_0
    //   2775: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   2778: putfield 362	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   2781: aload 4
    //   2783: aload_3
    //   2784: invokevirtual 363	org/json/JSONArray:toString	()Ljava/lang/String;
    //   2787: putfield 366	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   2790: aload 7
    //   2792: aload 4
    //   2794: putfield 369	com/cootek/usage/n:a	Lcom/cootek/usage/UsageData;
    //   2797: aload 7
    //   2799: iconst_0
    //   2800: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   2803: putfield 370	com/cootek/usage/n:c	Ljava/lang/String;
    //   2806: aload 7
    //   2808: areturn
    //   2809: astore 5
    //   2811: aload 5
    //   2813: invokevirtual 254	org/json/JSONException:printStackTrace	()V
    //   2816: goto -67 -> 2749
    //   2819: astore_3
    //   2820: aconst_null
    //   2821: astore 4
    //   2823: goto -1917 -> 906
    //   2826: astore 5
    //   2828: aload_3
    //   2829: astore 4
    //   2831: aload 5
    //   2833: astore_3
    //   2834: goto -1928 -> 906
    //   2837: astore 5
    //   2839: goto -1160 -> 1679
    //   2842: astore_3
    //   2843: aload 5
    //   2845: astore 4
    //   2847: goto -2081 -> 766
    //   2850: astore_3
    //   2851: goto -2158 -> 693
    //   2854: astore 5
    //   2856: aload_3
    //   2857: astore 4
    //   2859: aload 5
    //   2861: astore_3
    //   2862: goto -2169 -> 693
    //   2865: astore 5
    //   2867: goto -2216 -> 651
    //   2870: astore 4
    //   2872: goto -2252 -> 620
    //   2875: goto -817 -> 2058
    //   2878: iconst_0
    //   2879: istore_1
    //   2880: goto -903 -> 1977
    //   2883: ldc_w 372
    //   2886: astore_3
    //   2887: goto -2023 -> 864
    //   2890: ldc_w 374
    //   2893: astore_3
    //   2894: goto -2030 -> 864
    //   2897: ldc_w 376
    //   2900: astore_3
    //   2901: goto -2037 -> 864
    //   2904: ldc_w 378
    //   2907: astore_3
    //   2908: goto -2044 -> 864
    //   2911: aconst_null
    //   2912: astore_3
    //   2913: goto -2049 -> 864
    //   2916: ldc_w 372
    //   2919: astore_3
    //   2920: goto -1900 -> 1020
    //   2923: ldc_w 374
    //   2926: astore_3
    //   2927: goto -1907 -> 1020
    //   2930: ldc_w 376
    //   2933: astore_3
    //   2934: goto -1914 -> 1020
    //   2937: aconst_null
    //   2938: astore_3
    //   2939: goto -1919 -> 1020
    //   2942: ldc_w 372
    //   2945: astore_3
    //   2946: goto -1814 -> 1132
    //   2949: ldc_w 372
    //   2952: astore_3
    //   2953: goto -1729 -> 1224
    //   2956: ldc_w 277
    //   2959: astore_3
    //   2960: goto -1828 -> 1132
    //   2963: ldc_w 374
    //   2966: astore_3
    //   2967: goto -1835 -> 1132
    //   2970: ldc_w 376
    //   2973: astore_3
    //   2974: goto -1842 -> 1132
    //   2977: aconst_null
    //   2978: astore_3
    //   2979: goto -1847 -> 1132
    //   2982: ldc_w 380
    //   2985: astore_3
    //   2986: goto -1762 -> 1224
    //   2989: aconst_null
    //   2990: astore_3
    //   2991: goto -1767 -> 1224
    //   2994: ldc_w 382
    //   2997: astore_3
    //   2998: goto -1774 -> 1224
    //   3001: ldc_w 384
    //   3004: astore_3
    //   3005: goto -1781 -> 1224
    //   3008: ldc_w 386
    //   3011: astore_3
    //   3012: goto -1788 -> 1224
    //   3015: ldc_w 388
    //   3018: astore_3
    //   3019: goto -1795 -> 1224
    //   3022: ldc_w 390
    //   3025: astore_3
    //   3026: goto -1802 -> 1224
    //   3029: ldc_w 392
    //   3032: astore_3
    //   3033: goto -1809 -> 1224
    //   3036: ldc_w 394
    //   3039: astore_3
    //   3040: goto -1816 -> 1224
    //   3043: ldc_w 396
    //   3046: astore_3
    //   3047: goto -1823 -> 1224
    //   3050: ldc_w 372
    //   3053: astore_3
    //   3054: goto -1718 -> 1336
    //   3057: ldc_w 277
    //   3060: astore_3
    //   3061: goto -1725 -> 1336
    //   3064: ldc_w 374
    //   3067: astore_3
    //   3068: goto -1732 -> 1336
    //   3071: ldc_w 376
    //   3074: astore_3
    //   3075: goto -1739 -> 1336
    //   3078: aconst_null
    //   3079: astore_3
    //   3080: goto -1744 -> 1336
    //   3083: ldc_w 372
    //   3086: astore_3
    //   3087: goto -1639 -> 1448
    //   3090: ldc_w 398
    //   3093: astore_3
    //   3094: goto -1646 -> 1448
    //   3097: ldc_w 400
    //   3100: astore_3
    //   3101: goto -1653 -> 1448
    //   3104: ldc_w 376
    //   3107: astore_3
    //   3108: goto -1660 -> 1448
    //   3111: aconst_null
    //   3112: astore_3
    //   3113: goto -1665 -> 1448
    //   3116: ldc_w 372
    //   3119: astore_3
    //   3120: goto -1516 -> 1604
    //   3123: ldc_w 402
    //   3126: astore_3
    //   3127: goto -1523 -> 1604
    //   3130: ldc_w 404
    //   3133: astore_3
    //   3134: goto -1530 -> 1604
    //   3137: ldc_w 406
    //   3140: astore_3
    //   3141: goto -1537 -> 1604
    //   3144: ldc_w 408
    //   3147: astore_3
    //   3148: goto -1544 -> 1604
    //   3151: ldc_w 410
    //   3154: astore_3
    //   3155: goto -1551 -> 1604
    //   3158: ldc_w 412
    //   3161: astore_3
    //   3162: goto -1558 -> 1604
    //   3165: ldc_w 414
    //   3168: astore_3
    //   3169: goto -1565 -> 1604
    //   3172: ldc_w 416
    //   3175: astore_3
    //   3176: goto -1572 -> 1604
    //   3179: ldc_w 418
    //   3182: astore_3
    //   3183: goto -1579 -> 1604
    //   3186: ldc_w 420
    //   3189: astore_3
    //   3190: goto -1586 -> 1604
    //   3193: ldc_w 422
    //   3196: astore_3
    //   3197: goto -1593 -> 1604
    //   3200: ldc_w 424
    //   3203: astore_3
    //   3204: goto -1600 -> 1604
    //   3207: ldc_w 426
    //   3210: astore_3
    //   3211: goto -1607 -> 1604
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3214	0	this	g
    //   1976	904	1	j	int
    //   256	192	2	bool	boolean
    //   62	532	3	localObject1	Object
    //   609	2	3	localRuntimeException1	RuntimeException
    //   617	1	3	localSecurityException1	SecurityException
    //   619	12	3	localObject2	Object
    //   639	2	3	localRuntimeException2	RuntimeException
    //   650	23	3	localObject3	Object
    //   681	2	3	localRuntimeException3	RuntimeException
    //   689	17	3	localObject4	Object
    //   725	25	3	str1	String
    //   757	2	3	localRuntimeException4	RuntimeException
    //   765	1	3	localSecurityException2	SecurityException
    //   768	99	3	localObject5	Object
    //   905	14	3	localObject6	Object
    //   923	731	3	str2	String
    //   1657	2	3	localRuntimeException5	RuntimeException
    //   1665	2	3	localRuntimeException6	RuntimeException
    //   1681	9	3	localObject7	Object
    //   1711	1073	3	localRuntimeException7	RuntimeException
    //   2819	10	3	localObject8	Object
    //   2833	1	3	localObject9	Object
    //   2842	1	3	localSecurityException3	SecurityException
    //   2850	7	3	localObject10	Object
    //   2861	350	3	localObject11	Object
    //   68	631	4	localObject12	Object
    //   707	914	4	localRuntimeException8	RuntimeException
    //   1677	25	4	localObject13	Object
    //   1719	3	4	localRuntimeException9	RuntimeException
    //   2703	155	4	localObject14	Object
    //   2870	1	4	localSecurityException4	SecurityException
    //   4	591	5	localObject15	Object
    //   647	985	5	localRuntimeException10	RuntimeException
    //   1673	997	5	localRuntimeException11	RuntimeException
    //   2723	21	5	localJSONArray	JSONArray
    //   2809	3	5	localJSONException1	JSONException
    //   2826	6	5	localObject16	Object
    //   2837	7	5	localRuntimeException12	RuntimeException
    //   2854	6	5	localObject17	Object
    //   2865	1	5	localRuntimeException13	RuntimeException
    //   1	547	6	localJ	j
    //   586	3	6	localJSONException2	JSONException
    //   808	1773	6	localObject18	Object
    //   14	2793	7	localN	n
    //   35	2640	8	localObject19	Object
    //   26	2616	9	localObject20	Object
    //   91	2562	10	localObject21	Object
    //   1807	706	11	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   514	557	586	org/json/JSONException
    //   557	583	586	org/json/JSONException
    //   1729	1748	586	org/json/JSONException
    //   1748	1778	586	org/json/JSONException
    //   1778	1859	586	org/json/JSONException
    //   1859	1867	586	org/json/JSONException
    //   1870	1889	586	org/json/JSONException
    //   1889	1919	586	org/json/JSONException
    //   1919	1975	586	org/json/JSONException
    //   1977	2002	586	org/json/JSONException
    //   2004	2029	586	org/json/JSONException
    //   2031	2056	586	org/json/JSONException
    //   2062	2070	586	org/json/JSONException
    //   2073	2092	586	org/json/JSONException
    //   2092	2122	586	org/json/JSONException
    //   2122	2203	586	org/json/JSONException
    //   2203	2228	586	org/json/JSONException
    //   2228	2244	586	org/json/JSONException
    //   2247	2258	586	org/json/JSONException
    //   2258	2288	586	org/json/JSONException
    //   2288	2369	586	org/json/JSONException
    //   2369	2377	586	org/json/JSONException
    //   2380	2399	586	org/json/JSONException
    //   2399	2429	586	org/json/JSONException
    //   2429	2510	586	org/json/JSONException
    //   2510	2518	586	org/json/JSONException
    //   2521	2540	586	org/json/JSONException
    //   2540	2570	586	org/json/JSONException
    //   2570	2650	586	org/json/JSONException
    //   2650	2658	586	org/json/JSONException
    //   2661	2680	586	org/json/JSONException
    //   265	271	609	java/lang/RuntimeException
    //   37	63	617	java/lang/SecurityException
    //   630	636	639	java/lang/RuntimeException
    //   37	63	647	java/lang/RuntimeException
    //   672	678	681	java/lang/RuntimeException
    //   37	63	689	finally
    //   698	705	707	java/lang/RuntimeException
    //   413	434	757	java/lang/RuntimeException
    //   717	754	757	java/lang/RuntimeException
    //   790	864	757	java/lang/RuntimeException
    //   864	891	757	java/lang/RuntimeException
    //   891	902	757	java/lang/RuntimeException
    //   927	1020	757	java/lang/RuntimeException
    //   1020	1047	757	java/lang/RuntimeException
    //   1047	1058	757	java/lang/RuntimeException
    //   1061	1132	757	java/lang/RuntimeException
    //   1132	1159	757	java/lang/RuntimeException
    //   1159	1224	757	java/lang/RuntimeException
    //   1224	1252	757	java/lang/RuntimeException
    //   1252	1263	757	java/lang/RuntimeException
    //   1266	1336	757	java/lang/RuntimeException
    //   1336	1363	757	java/lang/RuntimeException
    //   1363	1374	757	java/lang/RuntimeException
    //   1377	1448	757	java/lang/RuntimeException
    //   1448	1475	757	java/lang/RuntimeException
    //   1475	1486	757	java/lang/RuntimeException
    //   1489	1604	757	java/lang/RuntimeException
    //   1604	1631	757	java/lang/RuntimeException
    //   1631	1642	757	java/lang/RuntimeException
    //   401	409	765	java/lang/SecurityException
    //   413	434	765	java/lang/SecurityException
    //   439	447	765	java/lang/SecurityException
    //   717	754	765	java/lang/SecurityException
    //   758	762	765	java/lang/SecurityException
    //   790	864	765	java/lang/SecurityException
    //   864	891	765	java/lang/SecurityException
    //   891	902	765	java/lang/SecurityException
    //   927	1020	765	java/lang/SecurityException
    //   1020	1047	765	java/lang/SecurityException
    //   1047	1058	765	java/lang/SecurityException
    //   1061	1132	765	java/lang/SecurityException
    //   1132	1159	765	java/lang/SecurityException
    //   1159	1224	765	java/lang/SecurityException
    //   1224	1252	765	java/lang/SecurityException
    //   1252	1263	765	java/lang/SecurityException
    //   1266	1336	765	java/lang/SecurityException
    //   1336	1363	765	java/lang/SecurityException
    //   1363	1374	765	java/lang/SecurityException
    //   1377	1448	765	java/lang/SecurityException
    //   1448	1475	765	java/lang/SecurityException
    //   1475	1486	765	java/lang/SecurityException
    //   1489	1604	765	java/lang/SecurityException
    //   1604	1631	765	java/lang/SecurityException
    //   1631	1642	765	java/lang/SecurityException
    //   401	409	905	finally
    //   413	434	905	finally
    //   439	447	905	finally
    //   717	754	905	finally
    //   758	762	905	finally
    //   790	864	905	finally
    //   864	891	905	finally
    //   891	902	905	finally
    //   927	1020	905	finally
    //   1020	1047	905	finally
    //   1047	1058	905	finally
    //   1061	1132	905	finally
    //   1132	1159	905	finally
    //   1159	1224	905	finally
    //   1224	1252	905	finally
    //   1252	1263	905	finally
    //   1266	1336	905	finally
    //   1336	1363	905	finally
    //   1363	1374	905	finally
    //   1377	1448	905	finally
    //   1448	1475	905	finally
    //   1475	1486	905	finally
    //   1489	1604	905	finally
    //   1604	1631	905	finally
    //   1631	1642	905	finally
    //   456	463	1657	java/lang/RuntimeException
    //   780	787	1665	java/lang/RuntimeException
    //   271	396	1673	java/lang/RuntimeException
    //   1701	1708	1711	java/lang/RuntimeException
    //   911	918	1719	java/lang/RuntimeException
    //   2705	2749	2809	org/json/JSONException
    //   271	396	2819	finally
    //   769	775	2826	finally
    //   1682	1687	2826	finally
    //   1690	1696	2826	finally
    //   401	409	2837	java/lang/RuntimeException
    //   439	447	2837	java/lang/RuntimeException
    //   758	762	2837	java/lang/RuntimeException
    //   271	396	2842	java/lang/SecurityException
    //   70	79	2850	finally
    //   82	93	2850	finally
    //   96	108	2850	finally
    //   111	123	2850	finally
    //   126	138	2850	finally
    //   141	153	2850	finally
    //   156	168	2850	finally
    //   171	183	2850	finally
    //   186	198	2850	finally
    //   201	213	2850	finally
    //   216	228	2850	finally
    //   231	247	2850	finally
    //   250	257	2850	finally
    //   654	659	2850	finally
    //   662	668	2850	finally
    //   620	626	2854	finally
    //   70	79	2865	java/lang/RuntimeException
    //   82	93	2865	java/lang/RuntimeException
    //   96	108	2865	java/lang/RuntimeException
    //   111	123	2865	java/lang/RuntimeException
    //   126	138	2865	java/lang/RuntimeException
    //   141	153	2865	java/lang/RuntimeException
    //   156	168	2865	java/lang/RuntimeException
    //   171	183	2865	java/lang/RuntimeException
    //   186	198	2865	java/lang/RuntimeException
    //   201	213	2865	java/lang/RuntimeException
    //   216	228	2865	java/lang/RuntimeException
    //   231	247	2865	java/lang/RuntimeException
    //   250	257	2865	java/lang/RuntimeException
    //   70	79	2870	java/lang/SecurityException
    //   82	93	2870	java/lang/SecurityException
    //   96	108	2870	java/lang/SecurityException
    //   111	123	2870	java/lang/SecurityException
    //   126	138	2870	java/lang/SecurityException
    //   141	153	2870	java/lang/SecurityException
    //   156	168	2870	java/lang/SecurityException
    //   171	183	2870	java/lang/SecurityException
    //   186	198	2870	java/lang/SecurityException
    //   201	213	2870	java/lang/SecurityException
    //   216	228	2870	java/lang/SecurityException
    //   231	247	2870	java/lang/SecurityException
    //   250	257	2870	java/lang/SecurityException
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
  private n d()
  {
    // Byte code:
    //   0: new 74	com/cootek/usage/n
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 77	com/cootek/usage/n:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 10
    //   10: aload_0
    //   11: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 83	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 89	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 7
    //   22: new 216	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 217	org/json/JSONArray:<init>	()V
    //   29: astore 11
    //   31: invokestatic 431	com/cootek/usage/u:a	()Lcom/cootek/usage/u;
    //   34: iconst_1
    //   35: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   38: invokevirtual 434	com/cootek/usage/u:c	(Ljava/lang/String;)J
    //   41: lstore_2
    //   42: aload 7
    //   44: getstatic 437	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   47: bipush 6
    //   49: anewarray 100	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: ldc 102
    //   56: aastore
    //   57: dup
    //   58: iconst_1
    //   59: ldc_w 439
    //   62: aastore
    //   63: dup
    //   64: iconst_2
    //   65: ldc_w 343
    //   68: aastore
    //   69: dup
    //   70: iconst_3
    //   71: ldc_w 441
    //   74: aastore
    //   75: dup
    //   76: iconst_4
    //   77: ldc_w 323
    //   80: aastore
    //   81: dup
    //   82: iconst_5
    //   83: ldc -15
    //   85: aastore
    //   86: ldc_w 443
    //   89: iconst_1
    //   90: anewarray 100	java/lang/String
    //   93: dup
    //   94: iconst_0
    //   95: lload_2
    //   96: invokestatic 446	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   99: aastore
    //   100: ldc_w 448
    //   103: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +265 -> 375
    //   113: aload 7
    //   115: astore 8
    //   117: aload 7
    //   119: invokeinterface 116 1 0
    //   124: ifeq +251 -> 375
    //   127: aload 7
    //   129: astore 8
    //   131: aload 10
    //   133: aload 7
    //   135: iconst_0
    //   136: invokeinterface 125 2 0
    //   141: putfield 450	com/cootek/usage/n:b	J
    //   144: aload 7
    //   146: astore 8
    //   148: aload 7
    //   150: iconst_1
    //   151: invokeinterface 131 2 0
    //   156: astore 9
    //   158: aload 7
    //   160: astore 8
    //   162: aload 7
    //   164: iconst_2
    //   165: invokeinterface 125 2 0
    //   170: lstore 4
    //   172: aload 7
    //   174: astore 8
    //   176: aload 7
    //   178: iconst_3
    //   179: invokeinterface 125 2 0
    //   184: lstore_2
    //   185: aload 7
    //   187: astore 8
    //   189: aload 7
    //   191: iconst_4
    //   192: invokeinterface 274 2 0
    //   197: istore_1
    //   198: aload 7
    //   200: astore 8
    //   202: aload 7
    //   204: iconst_5
    //   205: invokeinterface 131 2 0
    //   210: astore 12
    //   212: aload 7
    //   214: astore 8
    //   216: new 238	org/json/JSONObject
    //   219: dup
    //   220: invokespecial 239	org/json/JSONObject:<init>	()V
    //   223: astore 13
    //   225: aload 7
    //   227: astore 8
    //   229: aload 13
    //   231: ldc_w 452
    //   234: aload 9
    //   236: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   239: pop
    //   240: aload 7
    //   242: astore 8
    //   244: aload 13
    //   246: ldc_w 343
    //   249: lload 4
    //   251: ldc2_w 453
    //   254: ldiv
    //   255: invokevirtual 457	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   258: pop
    //   259: iload_1
    //   260: iconst_2
    //   261: if_icmpne +200 -> 461
    //   264: ldc_w 459
    //   267: astore 9
    //   269: aload 7
    //   271: astore 8
    //   273: aload 13
    //   275: ldc_w 323
    //   278: aload 9
    //   280: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   283: pop
    //   284: iconst_3
    //   285: iload_1
    //   286: if_icmpne +5 -> 291
    //   289: lconst_0
    //   290: lstore_2
    //   291: aload 7
    //   293: astore 8
    //   295: aload 13
    //   297: ldc_w 441
    //   300: lload_2
    //   301: invokevirtual 457	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   304: pop
    //   305: aload 7
    //   307: astore 8
    //   309: aload 12
    //   311: invokestatic 319	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   314: ifne +155 -> 469
    //   317: iconst_1
    //   318: istore 6
    //   320: aload 7
    //   322: astore 8
    //   324: aload 13
    //   326: ldc_w 461
    //   329: iload 6
    //   331: invokevirtual 464	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   334: pop
    //   335: aload 7
    //   337: astore 8
    //   339: aload 11
    //   341: aload 13
    //   343: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   346: pop
    //   347: aload 7
    //   349: astore 8
    //   351: aload 10
    //   353: iconst_1
    //   354: putfield 257	com/cootek/usage/n:d	Z
    //   357: aload 7
    //   359: astore 8
    //   361: aload 7
    //   363: invokeinterface 164 1 0
    //   368: istore 6
    //   370: iload 6
    //   372: ifne -228 -> 144
    //   375: aload 7
    //   377: ifnull +10 -> 387
    //   380: aload 7
    //   382: invokeinterface 167 1 0
    //   387: new 238	org/json/JSONObject
    //   390: dup
    //   391: invokespecial 239	org/json/JSONObject:<init>	()V
    //   394: astore 7
    //   396: aload 7
    //   398: ldc_w 466
    //   401: aload 11
    //   403: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   406: pop
    //   407: new 354	com/cootek/usage/UsageData
    //   410: dup
    //   411: invokespecial 355	com/cootek/usage/UsageData:<init>	()V
    //   414: astore 8
    //   416: aload 8
    //   418: ldc 23
    //   420: putfield 357	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   423: aload 8
    //   425: iconst_1
    //   426: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   429: putfield 362	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   432: aload 8
    //   434: aload 7
    //   436: invokevirtual 467	org/json/JSONObject:toString	()Ljava/lang/String;
    //   439: putfield 366	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   442: aload 10
    //   444: aload 8
    //   446: putfield 369	com/cootek/usage/n:a	Lcom/cootek/usage/UsageData;
    //   449: aload 10
    //   451: iconst_1
    //   452: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   455: putfield 370	com/cootek/usage/n:c	Ljava/lang/String;
    //   458: aload 10
    //   460: areturn
    //   461: ldc_w 469
    //   464: astore 9
    //   466: goto -197 -> 269
    //   469: iconst_0
    //   470: istore 6
    //   472: goto -152 -> 320
    //   475: astore 9
    //   477: aload 7
    //   479: astore 8
    //   481: aload 9
    //   483: invokevirtual 254	org/json/JSONException:printStackTrace	()V
    //   486: goto -129 -> 357
    //   489: astore 8
    //   491: aload 10
    //   493: iconst_0
    //   494: putfield 257	com/cootek/usage/n:d	Z
    //   497: aload 7
    //   499: ifnull +10 -> 509
    //   502: aload 7
    //   504: invokeinterface 167 1 0
    //   509: aload 10
    //   511: areturn
    //   512: astore 7
    //   514: aload 7
    //   516: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   519: goto -132 -> 387
    //   522: astore 7
    //   524: aload 7
    //   526: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   529: goto -20 -> 509
    //   532: astore 9
    //   534: aconst_null
    //   535: astore 7
    //   537: aload 7
    //   539: astore 8
    //   541: aload 9
    //   543: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   546: aload 7
    //   548: astore 8
    //   550: aload 10
    //   552: iconst_0
    //   553: putfield 257	com/cootek/usage/n:d	Z
    //   556: aload 7
    //   558: ifnull +10 -> 568
    //   561: aload 7
    //   563: invokeinterface 167 1 0
    //   568: aload 10
    //   570: areturn
    //   571: astore 7
    //   573: aload 7
    //   575: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   578: goto -10 -> 568
    //   581: astore 7
    //   583: aconst_null
    //   584: astore 8
    //   586: aload 8
    //   588: ifnull +10 -> 598
    //   591: aload 8
    //   593: invokeinterface 167 1 0
    //   598: aload 7
    //   600: athrow
    //   601: astore 8
    //   603: aload 8
    //   605: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   608: goto -10 -> 598
    //   611: astore 8
    //   613: aload 8
    //   615: invokevirtual 254	org/json/JSONException:printStackTrace	()V
    //   618: goto -211 -> 407
    //   621: astore 7
    //   623: goto -37 -> 586
    //   626: astore 9
    //   628: aload 7
    //   630: astore 8
    //   632: aload 9
    //   634: astore 7
    //   636: goto -50 -> 586
    //   639: astore 9
    //   641: goto -104 -> 537
    //   644: astore 7
    //   646: aconst_null
    //   647: astore 7
    //   649: goto -158 -> 491
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	652	0	this	g
    //   197	90	1	j	int
    //   41	260	2	l1	long
    //   170	80	4	l2	long
    //   318	153	6	bool	boolean
    //   20	483	7	localObject1	Object
    //   512	3	7	localRuntimeException1	RuntimeException
    //   522	3	7	localRuntimeException2	RuntimeException
    //   535	27	7	localObject2	Object
    //   571	3	7	localRuntimeException3	RuntimeException
    //   581	18	7	localObject3	Object
    //   621	8	7	localObject4	Object
    //   634	1	7	localObject5	Object
    //   644	1	7	localSecurityException1	SecurityException
    //   647	1	7	localObject6	Object
    //   115	365	8	localObject7	Object
    //   489	1	8	localSecurityException2	SecurityException
    //   539	53	8	localObject8	Object
    //   601	3	8	localRuntimeException4	RuntimeException
    //   611	3	8	localJSONException1	JSONException
    //   630	1	8	localObject9	Object
    //   156	309	9	str1	String
    //   475	7	9	localJSONException2	JSONException
    //   532	10	9	localRuntimeException5	RuntimeException
    //   626	7	9	localObject10	Object
    //   639	1	9	localRuntimeException6	RuntimeException
    //   8	561	10	localN	n
    //   29	373	11	localJSONArray	JSONArray
    //   210	100	12	str2	String
    //   223	119	13	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   229	240	475	org/json/JSONException
    //   244	259	475	org/json/JSONException
    //   273	284	475	org/json/JSONException
    //   295	305	475	org/json/JSONException
    //   309	317	475	org/json/JSONException
    //   324	335	475	org/json/JSONException
    //   339	347	475	org/json/JSONException
    //   351	357	475	org/json/JSONException
    //   117	127	489	java/lang/SecurityException
    //   131	144	489	java/lang/SecurityException
    //   148	158	489	java/lang/SecurityException
    //   162	172	489	java/lang/SecurityException
    //   176	185	489	java/lang/SecurityException
    //   189	198	489	java/lang/SecurityException
    //   202	212	489	java/lang/SecurityException
    //   216	225	489	java/lang/SecurityException
    //   229	240	489	java/lang/SecurityException
    //   244	259	489	java/lang/SecurityException
    //   273	284	489	java/lang/SecurityException
    //   295	305	489	java/lang/SecurityException
    //   309	317	489	java/lang/SecurityException
    //   324	335	489	java/lang/SecurityException
    //   339	347	489	java/lang/SecurityException
    //   351	357	489	java/lang/SecurityException
    //   361	370	489	java/lang/SecurityException
    //   481	486	489	java/lang/SecurityException
    //   380	387	512	java/lang/RuntimeException
    //   502	509	522	java/lang/RuntimeException
    //   31	108	532	java/lang/RuntimeException
    //   561	568	571	java/lang/RuntimeException
    //   31	108	581	finally
    //   591	598	601	java/lang/RuntimeException
    //   396	407	611	org/json/JSONException
    //   117	127	621	finally
    //   131	144	621	finally
    //   148	158	621	finally
    //   162	172	621	finally
    //   176	185	621	finally
    //   189	198	621	finally
    //   202	212	621	finally
    //   216	225	621	finally
    //   229	240	621	finally
    //   244	259	621	finally
    //   273	284	621	finally
    //   295	305	621	finally
    //   309	317	621	finally
    //   324	335	621	finally
    //   339	347	621	finally
    //   351	357	621	finally
    //   361	370	621	finally
    //   481	486	621	finally
    //   541	546	621	finally
    //   550	556	621	finally
    //   491	497	626	finally
    //   117	127	639	java/lang/RuntimeException
    //   131	144	639	java/lang/RuntimeException
    //   148	158	639	java/lang/RuntimeException
    //   162	172	639	java/lang/RuntimeException
    //   176	185	639	java/lang/RuntimeException
    //   189	198	639	java/lang/RuntimeException
    //   202	212	639	java/lang/RuntimeException
    //   216	225	639	java/lang/RuntimeException
    //   229	240	639	java/lang/RuntimeException
    //   244	259	639	java/lang/RuntimeException
    //   273	284	639	java/lang/RuntimeException
    //   295	305	639	java/lang/RuntimeException
    //   309	317	639	java/lang/RuntimeException
    //   324	335	639	java/lang/RuntimeException
    //   339	347	639	java/lang/RuntimeException
    //   351	357	639	java/lang/RuntimeException
    //   361	370	639	java/lang/RuntimeException
    //   481	486	639	java/lang/RuntimeException
    //   31	108	644	java/lang/SecurityException
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
  private n e()
  {
    // Byte code:
    //   0: new 74	com/cootek/usage/n
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 77	com/cootek/usage/n:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 9
    //   10: aload_0
    //   11: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 83	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 89	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 6
    //   22: new 216	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 217	org/json/JSONArray:<init>	()V
    //   29: astore 8
    //   31: invokestatic 431	com/cootek/usage/u:a	()Lcom/cootek/usage/u;
    //   34: iconst_2
    //   35: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   38: invokevirtual 434	com/cootek/usage/u:c	(Ljava/lang/String;)J
    //   41: lstore_1
    //   42: aload 6
    //   44: ldc_w 471
    //   47: invokestatic 477	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   50: bipush 6
    //   52: anewarray 100	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: ldc 102
    //   59: aastore
    //   60: dup
    //   61: iconst_1
    //   62: ldc_w 321
    //   65: aastore
    //   66: dup
    //   67: iconst_2
    //   68: ldc_w 479
    //   71: aastore
    //   72: dup
    //   73: iconst_3
    //   74: ldc_w 343
    //   77: aastore
    //   78: dup
    //   79: iconst_4
    //   80: ldc_w 481
    //   83: aastore
    //   84: dup
    //   85: iconst_5
    //   86: ldc_w 483
    //   89: aastore
    //   90: ldc_w 443
    //   93: iconst_1
    //   94: anewarray 100	java/lang/String
    //   97: dup
    //   98: iconst_0
    //   99: lload_1
    //   100: invokestatic 446	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   103: aastore
    //   104: ldc_w 485
    //   107: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   110: astore 6
    //   112: aload 6
    //   114: ifnull +264 -> 378
    //   117: aload 6
    //   119: astore 7
    //   121: aload 6
    //   123: invokeinterface 116 1 0
    //   128: ifeq +250 -> 378
    //   131: aload 6
    //   133: astore 7
    //   135: aload 9
    //   137: aload 6
    //   139: iconst_0
    //   140: invokeinterface 125 2 0
    //   145: putfield 450	com/cootek/usage/n:b	J
    //   148: aload 6
    //   150: astore 7
    //   152: aload 6
    //   154: iconst_1
    //   155: invokeinterface 131 2 0
    //   160: astore 10
    //   162: aload 6
    //   164: astore 7
    //   166: aload 6
    //   168: iconst_2
    //   169: invokeinterface 125 2 0
    //   174: lstore_1
    //   175: lload_1
    //   176: lconst_0
    //   177: lcmp
    //   178: ifgt +182 -> 360
    //   181: aload 6
    //   183: astore 7
    //   185: aload 6
    //   187: iconst_3
    //   188: invokeinterface 125 2 0
    //   193: lstore_3
    //   194: aload 6
    //   196: astore 7
    //   198: aload 6
    //   200: iconst_4
    //   201: invokeinterface 131 2 0
    //   206: astore 11
    //   208: aload 6
    //   210: astore 7
    //   212: aload 6
    //   214: iconst_5
    //   215: invokeinterface 131 2 0
    //   220: astore 12
    //   222: aload 6
    //   224: astore 7
    //   226: new 238	org/json/JSONObject
    //   229: dup
    //   230: invokespecial 239	org/json/JSONObject:<init>	()V
    //   233: astore 13
    //   235: aload 6
    //   237: astore 7
    //   239: aload 13
    //   241: ldc_w 452
    //   244: aload 10
    //   246: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   249: pop
    //   250: aload 6
    //   252: astore 7
    //   254: aload 13
    //   256: ldc_w 343
    //   259: lload_3
    //   260: ldc2_w 453
    //   263: ldiv
    //   264: invokevirtual 457	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   267: pop
    //   268: aload 6
    //   270: astore 7
    //   272: aload 13
    //   274: ldc_w 323
    //   277: ldc_w 469
    //   280: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
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
    //   299: ldc_w 461
    //   302: iload 5
    //   304: invokevirtual 464	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   307: pop
    //   308: aload 6
    //   310: astore 7
    //   312: aload 13
    //   314: ldc_w 487
    //   317: aload 11
    //   319: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   322: pop
    //   323: aload 6
    //   325: astore 7
    //   327: aload 13
    //   329: ldc_w 483
    //   332: aload 12
    //   334: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   337: pop
    //   338: aload 6
    //   340: astore 7
    //   342: aload 8
    //   344: aload 13
    //   346: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   349: pop
    //   350: aload 6
    //   352: astore 7
    //   354: aload 9
    //   356: iconst_1
    //   357: putfield 257	com/cootek/usage/n:d	Z
    //   360: aload 6
    //   362: astore 7
    //   364: aload 6
    //   366: invokeinterface 164 1 0
    //   371: istore 5
    //   373: iload 5
    //   375: ifne -227 -> 148
    //   378: aload 6
    //   380: ifnull +10 -> 390
    //   383: aload 6
    //   385: invokeinterface 167 1 0
    //   390: new 238	org/json/JSONObject
    //   393: dup
    //   394: invokespecial 239	org/json/JSONObject:<init>	()V
    //   397: astore 6
    //   399: aload 6
    //   401: ldc_w 466
    //   404: aload 8
    //   406: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   409: pop
    //   410: new 354	com/cootek/usage/UsageData
    //   413: dup
    //   414: invokespecial 355	com/cootek/usage/UsageData:<init>	()V
    //   417: astore 7
    //   419: aload 7
    //   421: ldc 23
    //   423: putfield 357	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   426: aload 7
    //   428: iconst_2
    //   429: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   432: putfield 362	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   435: aload 7
    //   437: aload 6
    //   439: invokevirtual 467	org/json/JSONObject:toString	()Ljava/lang/String;
    //   442: putfield 366	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   445: aload 9
    //   447: aload 7
    //   449: putfield 369	com/cootek/usage/n:a	Lcom/cootek/usage/UsageData;
    //   452: aload 9
    //   454: iconst_2
    //   455: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   458: putfield 370	com/cootek/usage/n:c	Ljava/lang/String;
    //   461: aload 9
    //   463: areturn
    //   464: iconst_0
    //   465: istore 5
    //   467: goto -174 -> 293
    //   470: astore 10
    //   472: aload 6
    //   474: astore 7
    //   476: aload 10
    //   478: invokevirtual 254	org/json/JSONException:printStackTrace	()V
    //   481: goto -121 -> 360
    //   484: astore 7
    //   486: aload 9
    //   488: iconst_0
    //   489: putfield 257	com/cootek/usage/n:d	Z
    //   492: aload 6
    //   494: ifnull +10 -> 504
    //   497: aload 6
    //   499: invokeinterface 167 1 0
    //   504: aload 9
    //   506: areturn
    //   507: astore 6
    //   509: aload 6
    //   511: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   514: goto -124 -> 390
    //   517: astore 6
    //   519: aload 6
    //   521: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   524: goto -20 -> 504
    //   527: astore 8
    //   529: aconst_null
    //   530: astore 6
    //   532: aload 6
    //   534: astore 7
    //   536: aload 8
    //   538: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   541: aload 6
    //   543: astore 7
    //   545: aload 9
    //   547: iconst_0
    //   548: putfield 257	com/cootek/usage/n:d	Z
    //   551: aload 6
    //   553: ifnull +10 -> 563
    //   556: aload 6
    //   558: invokeinterface 167 1 0
    //   563: aload 9
    //   565: areturn
    //   566: astore 6
    //   568: aload 6
    //   570: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   573: goto -10 -> 563
    //   576: astore 6
    //   578: aconst_null
    //   579: astore 7
    //   581: aload 7
    //   583: ifnull +10 -> 593
    //   586: aload 7
    //   588: invokeinterface 167 1 0
    //   593: aload 6
    //   595: athrow
    //   596: astore 7
    //   598: aload 7
    //   600: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   603: goto -10 -> 593
    //   606: astore 7
    //   608: aload 7
    //   610: invokevirtual 254	org/json/JSONException:printStackTrace	()V
    //   613: goto -203 -> 410
    //   616: astore 6
    //   618: goto -37 -> 581
    //   621: astore 8
    //   623: aload 6
    //   625: astore 7
    //   627: aload 8
    //   629: astore 6
    //   631: goto -50 -> 581
    //   634: astore 8
    //   636: goto -104 -> 532
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
    //   517	3	6	localRuntimeException2	RuntimeException
    //   530	27	6	localObject2	Object
    //   566	3	6	localRuntimeException3	RuntimeException
    //   576	18	6	localObject3	Object
    //   616	8	6	localObject4	Object
    //   629	1	6	localObject5	Object
    //   639	1	6	localSecurityException1	SecurityException
    //   642	1	6	localObject6	Object
    //   119	356	7	localObject7	Object
    //   484	1	7	localSecurityException2	SecurityException
    //   534	53	7	localObject8	Object
    //   596	3	7	localRuntimeException4	RuntimeException
    //   606	3	7	localJSONException1	JSONException
    //   625	1	7	localObject9	Object
    //   29	376	8	localJSONArray	JSONArray
    //   527	10	8	localRuntimeException5	RuntimeException
    //   621	7	8	localObject10	Object
    //   634	1	8	localRuntimeException6	RuntimeException
    //   8	556	9	localN	n
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
    //   383	390	507	java/lang/RuntimeException
    //   497	504	517	java/lang/RuntimeException
    //   31	112	527	java/lang/RuntimeException
    //   556	563	566	java/lang/RuntimeException
    //   31	112	576	finally
    //   586	593	596	java/lang/RuntimeException
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
    //   536	541	616	finally
    //   545	551	616	finally
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
  
  private n f()
  {
    n localN = new n(this);
    Object localObject2 = this.i.getContext().getPackageManager();
    List localList = ((PackageManager)localObject2).getInstalledPackages(0);
    Object localObject1 = new JSONArray();
    int j = 0;
    for (;;)
    {
      if (j < localList.size())
      {
        Object localObject3 = (PackageInfo)localList.get(j);
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
          localN.d = true;
          j += 1;
        }
        catch (JSONException localJSONException2)
        {
          for (;;)
          {
            localJSONException2.printStackTrace();
          }
        }
      }
    }
    localObject2 = new JSONObject();
    try
    {
      ((JSONObject)localObject2).put("data", localObject1);
      localObject1 = new UsageData();
      ((UsageData)localObject1).type = "noah_info";
      ((UsageData)localObject1).path = a(3);
      ((UsageData)localObject1).value = ((JSONObject)localObject2).toString();
      localN.a = ((UsageData)localObject1);
      localN.c = a(3);
      return localN;
    }
    catch (JSONException localJSONException1)
    {
      for (;;)
      {
        localJSONException1.printStackTrace();
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
  private n g()
  {
    // Byte code:
    //   0: new 74	com/cootek/usage/n
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 77	com/cootek/usage/n:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 13
    //   10: aload_0
    //   11: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 83	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 89	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 10
    //   22: new 216	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 217	org/json/JSONArray:<init>	()V
    //   29: astore 14
    //   31: ldc_w 530
    //   34: invokestatic 477	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   37: astore 11
    //   39: invokestatic 431	com/cootek/usage/u:a	()Lcom/cootek/usage/u;
    //   42: iconst_1
    //   43: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   46: invokevirtual 434	com/cootek/usage/u:c	(Ljava/lang/String;)J
    //   49: lstore_3
    //   50: aload 10
    //   52: aload 11
    //   54: bipush 7
    //   56: anewarray 100	java/lang/String
    //   59: dup
    //   60: iconst_0
    //   61: ldc 102
    //   63: aastore
    //   64: dup
    //   65: iconst_1
    //   66: ldc_w 439
    //   69: aastore
    //   70: dup
    //   71: iconst_2
    //   72: ldc_w 343
    //   75: aastore
    //   76: dup
    //   77: iconst_3
    //   78: ldc_w 441
    //   81: aastore
    //   82: dup
    //   83: iconst_4
    //   84: ldc_w 323
    //   87: aastore
    //   88: dup
    //   89: iconst_5
    //   90: ldc -84
    //   92: aastore
    //   93: dup
    //   94: bipush 6
    //   96: ldc_w 532
    //   99: aastore
    //   100: ldc_w 443
    //   103: iconst_1
    //   104: anewarray 100	java/lang/String
    //   107: dup
    //   108: iconst_0
    //   109: lload_3
    //   110: invokestatic 446	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   113: aastore
    //   114: ldc_w 448
    //   117: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   120: astore 10
    //   122: aload 10
    //   124: ifnull +294 -> 418
    //   127: aload 10
    //   129: astore 11
    //   131: aload 10
    //   133: invokeinterface 116 1 0
    //   138: ifeq +280 -> 418
    //   141: aload 10
    //   143: astore 11
    //   145: aload 13
    //   147: aload 10
    //   149: iconst_0
    //   150: invokeinterface 125 2 0
    //   155: putfield 450	com/cootek/usage/n:b	J
    //   158: aload 10
    //   160: astore 11
    //   162: aload 10
    //   164: iconst_1
    //   165: invokeinterface 131 2 0
    //   170: astore 12
    //   172: aload 10
    //   174: astore 11
    //   176: aload 10
    //   178: iconst_2
    //   179: invokeinterface 125 2 0
    //   184: lstore 7
    //   186: aload 10
    //   188: astore 11
    //   190: aload 10
    //   192: iconst_3
    //   193: invokeinterface 125 2 0
    //   198: lstore_3
    //   199: aload 10
    //   201: astore 11
    //   203: aload 10
    //   205: iconst_4
    //   206: invokeinterface 274 2 0
    //   211: istore_1
    //   212: aload 10
    //   214: astore 11
    //   216: aload 10
    //   218: iconst_5
    //   219: invokeinterface 125 2 0
    //   224: lstore 5
    //   226: aload 10
    //   228: astore 11
    //   230: aload 10
    //   232: bipush 6
    //   234: invokeinterface 274 2 0
    //   239: istore_2
    //   240: aload 10
    //   242: astore 11
    //   244: new 238	org/json/JSONObject
    //   247: dup
    //   248: invokespecial 239	org/json/JSONObject:<init>	()V
    //   251: astore 15
    //   253: aload 10
    //   255: astore 11
    //   257: aload 15
    //   259: ldc_w 452
    //   262: aload 12
    //   264: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   267: pop
    //   268: aload 10
    //   270: astore 11
    //   272: aload 15
    //   274: ldc_w 343
    //   277: lload 7
    //   279: ldc2_w 453
    //   282: ldiv
    //   283: invokevirtual 457	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   286: pop
    //   287: iconst_3
    //   288: iload_1
    //   289: if_icmpne +5 -> 294
    //   292: lconst_0
    //   293: lstore_3
    //   294: aload 10
    //   296: astore 11
    //   298: aload 15
    //   300: ldc_w 441
    //   303: lload_3
    //   304: invokevirtual 457	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
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
    //   324: ldc_w 461
    //   327: iload 9
    //   329: invokevirtual 464	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   332: pop
    //   333: iload_1
    //   334: iconst_2
    //   335: if_icmpne +175 -> 510
    //   338: ldc_w 459
    //   341: astore 12
    //   343: aload 10
    //   345: astore 11
    //   347: aload 15
    //   349: ldc_w 323
    //   352: aload 12
    //   354: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   357: pop
    //   358: iload_2
    //   359: ifne +159 -> 518
    //   362: aload 10
    //   364: astore 11
    //   366: aload 15
    //   368: ldc_w 534
    //   371: ldc_w 536
    //   374: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   377: pop
    //   378: aload 10
    //   380: astore 11
    //   382: aload 14
    //   384: aload 15
    //   386: invokevirtual 251	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   389: pop
    //   390: aload 10
    //   392: astore 11
    //   394: aload 13
    //   396: iconst_1
    //   397: putfield 257	com/cootek/usage/n:d	Z
    //   400: aload 10
    //   402: astore 11
    //   404: aload 10
    //   406: invokeinterface 164 1 0
    //   411: istore 9
    //   413: iload 9
    //   415: ifne -257 -> 158
    //   418: aload 10
    //   420: ifnull +10 -> 430
    //   423: aload 10
    //   425: invokeinterface 167 1 0
    //   430: new 238	org/json/JSONObject
    //   433: dup
    //   434: invokespecial 239	org/json/JSONObject:<init>	()V
    //   437: astore 10
    //   439: aload 10
    //   441: ldc_w 466
    //   444: aload 14
    //   446: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   449: pop
    //   450: new 354	com/cootek/usage/UsageData
    //   453: dup
    //   454: invokespecial 355	com/cootek/usage/UsageData:<init>	()V
    //   457: astore 11
    //   459: aload 11
    //   461: ldc 23
    //   463: putfield 357	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   466: aload 11
    //   468: iconst_4
    //   469: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   472: putfield 362	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   475: aload 11
    //   477: aload 10
    //   479: invokevirtual 467	org/json/JSONObject:toString	()Ljava/lang/String;
    //   482: putfield 366	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   485: aload 13
    //   487: aload 11
    //   489: putfield 369	com/cootek/usage/n:a	Lcom/cootek/usage/UsageData;
    //   492: aload 13
    //   494: iconst_4
    //   495: invokestatic 359	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   498: putfield 370	com/cootek/usage/n:c	Ljava/lang/String;
    //   501: aload 13
    //   503: areturn
    //   504: iconst_0
    //   505: istore 9
    //   507: goto -189 -> 318
    //   510: ldc_w 469
    //   513: astore 12
    //   515: goto -172 -> 343
    //   518: iload_2
    //   519: iconst_1
    //   520: if_icmpne +59 -> 579
    //   523: aload 10
    //   525: astore 11
    //   527: aload 15
    //   529: ldc_w 534
    //   532: ldc_w 538
    //   535: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   538: pop
    //   539: goto -161 -> 378
    //   542: astore 12
    //   544: aload 10
    //   546: astore 11
    //   548: aload 12
    //   550: invokevirtual 254	org/json/JSONException:printStackTrace	()V
    //   553: goto -153 -> 400
    //   556: astore 11
    //   558: aload 13
    //   560: iconst_0
    //   561: putfield 257	com/cootek/usage/n:d	Z
    //   564: aload 10
    //   566: ifnull +10 -> 576
    //   569: aload 10
    //   571: invokeinterface 167 1 0
    //   576: aload 13
    //   578: areturn
    //   579: iload_2
    //   580: iconst_2
    //   581: if_icmpne +58 -> 639
    //   584: aload 10
    //   586: astore 11
    //   588: aload 15
    //   590: ldc_w 534
    //   593: ldc_w 540
    //   596: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   599: pop
    //   600: goto -222 -> 378
    //   603: astore 12
    //   605: aload 10
    //   607: astore 11
    //   609: aload 12
    //   611: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   614: aload 10
    //   616: astore 11
    //   618: aload 13
    //   620: iconst_0
    //   621: putfield 257	com/cootek/usage/n:d	Z
    //   624: aload 10
    //   626: ifnull +10 -> 636
    //   629: aload 10
    //   631: invokeinterface 167 1 0
    //   636: aload 13
    //   638: areturn
    //   639: iload_2
    //   640: iconst_3
    //   641: if_icmpne -263 -> 378
    //   644: aload 10
    //   646: astore 11
    //   648: aload 15
    //   650: ldc_w 534
    //   653: ldc_w 542
    //   656: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   659: pop
    //   660: goto -282 -> 378
    //   663: astore 10
    //   665: aload 11
    //   667: ifnull +10 -> 677
    //   670: aload 11
    //   672: invokeinterface 167 1 0
    //   677: aload 10
    //   679: athrow
    //   680: astore 10
    //   682: aload 10
    //   684: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   687: goto -257 -> 430
    //   690: astore 10
    //   692: aload 10
    //   694: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   697: goto -121 -> 576
    //   700: astore 10
    //   702: aload 10
    //   704: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   707: goto -71 -> 636
    //   710: astore 11
    //   712: aload 11
    //   714: invokevirtual 258	java/lang/RuntimeException:printStackTrace	()V
    //   717: goto -40 -> 677
    //   720: astore 11
    //   722: aload 11
    //   724: invokevirtual 254	org/json/JSONException:printStackTrace	()V
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
    //   211	125	1	j	int
    //   239	403	2	k	int
    //   49	255	3	l1	long
    //   224	85	5	l2	long
    //   184	94	7	l3	long
    //   316	190	9	bool	boolean
    //   20	625	10	localObject1	Object
    //   663	15	10	localObject2	Object
    //   680	3	10	localRuntimeException1	RuntimeException
    //   690	3	10	localRuntimeException2	RuntimeException
    //   700	3	10	localRuntimeException3	RuntimeException
    //   730	11	10	localObject3	Object
    //   746	9	10	localObject4	Object
    //   759	1	10	localSecurityException1	SecurityException
    //   762	1	10	localObject5	Object
    //   37	510	11	localObject6	Object
    //   556	1	11	localSecurityException2	SecurityException
    //   586	85	11	localObject7	Object
    //   710	3	11	localRuntimeException4	RuntimeException
    //   720	3	11	localJSONException1	JSONException
    //   733	10	11	localObject8	Object
    //   170	344	12	str	String
    //   542	7	12	localJSONException2	JSONException
    //   603	7	12	localRuntimeException5	RuntimeException
    //   738	7	12	localObject9	Object
    //   751	1	12	localRuntimeException6	RuntimeException
    //   8	629	13	localN	n
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
    //   423	430	680	java/lang/RuntimeException
    //   569	576	690	java/lang/RuntimeException
    //   629	636	700	java/lang/RuntimeException
    //   670	677	710	java/lang/RuntimeException
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
  
  private n h()
  {
    n localN = new n(this);
    Object localObject = new JSONArray();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      String str1 = s.a(UsageRecorder.a.getContext());
      String str2 = s.b(UsageRecorder.a.getContext());
      localJSONObject.put("phone", str1);
      localJSONObject.put("IMSI", str2);
      ((JSONArray)localObject).put(localJSONObject);
      localN.d = true;
      localJSONObject = new JSONObject();
    }
    catch (JSONException localJSONException2)
    {
      try
      {
        localJSONObject.put("data", localObject);
        localObject = new UsageData();
        ((UsageData)localObject).type = "noah_info";
        ((UsageData)localObject).path = a(5);
        ((UsageData)localObject).value = localJSONObject.toString();
        localN.a = ((UsageData)localObject);
        localN.c = a(5);
        return localN;
        localJSONException2 = localJSONException2;
        localJSONException2.printStackTrace();
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
  
  final n b(int paramInt)
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
    case 4: 
      return g();
    }
    return h();
  }
}
