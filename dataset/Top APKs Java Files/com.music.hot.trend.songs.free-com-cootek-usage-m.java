package com.cootek.usage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class m
{
  private b a;
  
  m(b paramB)
  {
    this.a = paramB;
  }
  
  /* Error */
  private s a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: new 23	com/cootek/usage/s
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 26	com/cootek/usage/s:<init>	(Lcom/cootek/usage/m;)V
    //   14: astore 7
    //   16: aload_0
    //   17: getfield 13	com/cootek/usage/m:a	Lcom/cootek/usage/b;
    //   20: invokevirtual 32	com/cootek/usage/b:getContext	()Landroid/content/Context;
    //   23: invokevirtual 38	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   26: astore 9
    //   28: new 40	java/util/Hashtable
    //   31: dup
    //   32: invokespecial 41	java/util/Hashtable:<init>	()V
    //   35: astore 8
    //   37: aload 9
    //   39: getstatic 47	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
    //   42: iconst_2
    //   43: anewarray 49	java/lang/String
    //   46: dup
    //   47: iconst_0
    //   48: ldc 51
    //   50: aastore
    //   51: dup
    //   52: iconst_1
    //   53: ldc 53
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 59	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore_3
    //   63: aload_3
    //   64: ifnull +197 -> 261
    //   67: aload_3
    //   68: astore 4
    //   70: aload_3
    //   71: invokeinterface 65 1 0
    //   76: ifeq +185 -> 261
    //   79: aload_3
    //   80: astore 4
    //   82: new 67	com/cootek/usage/o
    //   85: dup
    //   86: aload_0
    //   87: iconst_0
    //   88: invokespecial 70	com/cootek/usage/o:<init>	(Lcom/cootek/usage/m;B)V
    //   91: astore 10
    //   93: aload_3
    //   94: astore 4
    //   96: aload 10
    //   98: aload_3
    //   99: iconst_0
    //   100: invokeinterface 74 2 0
    //   105: putfield 77	com/cootek/usage/o:a	J
    //   108: aload_3
    //   109: astore 4
    //   111: aload 10
    //   113: aload_3
    //   114: iconst_1
    //   115: invokeinterface 81 2 0
    //   120: putfield 85	com/cootek/usage/o:b	Ljava/lang/String;
    //   123: aload_3
    //   124: astore 4
    //   126: aload 10
    //   128: new 87	java/util/HashSet
    //   131: dup
    //   132: invokespecial 88	java/util/HashSet:<init>	()V
    //   135: putfield 92	com/cootek/usage/o:c	Ljava/util/HashSet;
    //   138: aload_3
    //   139: astore 4
    //   141: aload 10
    //   143: new 87	java/util/HashSet
    //   146: dup
    //   147: invokespecial 88	java/util/HashSet:<init>	()V
    //   150: putfield 95	com/cootek/usage/o:d	Ljava/util/HashSet;
    //   153: aload_3
    //   154: astore 4
    //   156: aload 10
    //   158: new 87	java/util/HashSet
    //   161: dup
    //   162: invokespecial 88	java/util/HashSet:<init>	()V
    //   165: putfield 98	com/cootek/usage/o:e	Ljava/util/HashSet;
    //   168: aload_3
    //   169: astore 4
    //   171: aload 10
    //   173: new 87	java/util/HashSet
    //   176: dup
    //   177: invokespecial 88	java/util/HashSet:<init>	()V
    //   180: putfield 101	com/cootek/usage/o:f	Ljava/util/HashSet;
    //   183: aload_3
    //   184: astore 4
    //   186: aload 10
    //   188: new 87	java/util/HashSet
    //   191: dup
    //   192: invokespecial 88	java/util/HashSet:<init>	()V
    //   195: putfield 104	com/cootek/usage/o:g	Ljava/util/HashSet;
    //   198: aload_3
    //   199: astore 4
    //   201: aload 10
    //   203: new 87	java/util/HashSet
    //   206: dup
    //   207: invokespecial 88	java/util/HashSet:<init>	()V
    //   210: putfield 107	com/cootek/usage/o:h	Ljava/util/HashSet;
    //   213: aload_3
    //   214: astore 4
    //   216: aload 10
    //   218: new 87	java/util/HashSet
    //   221: dup
    //   222: invokespecial 88	java/util/HashSet:<init>	()V
    //   225: putfield 110	com/cootek/usage/o:i	Ljava/util/HashSet;
    //   228: aload_3
    //   229: astore 4
    //   231: aload 8
    //   233: aload 10
    //   235: getfield 77	com/cootek/usage/o:a	J
    //   238: invokestatic 116	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   241: aload 10
    //   243: invokevirtual 120	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   246: pop
    //   247: aload_3
    //   248: astore 4
    //   250: aload_3
    //   251: invokeinterface 123 1 0
    //   256: istore_2
    //   257: iload_2
    //   258: ifne -179 -> 79
    //   261: aload_3
    //   262: ifnull +9 -> 271
    //   265: aload_3
    //   266: invokeinterface 126 1 0
    //   271: aload 9
    //   273: getstatic 129	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   276: bipush 12
    //   278: anewarray 49	java/lang/String
    //   281: dup
    //   282: iconst_0
    //   283: ldc -125
    //   285: aastore
    //   286: dup
    //   287: iconst_1
    //   288: ldc -123
    //   290: aastore
    //   291: dup
    //   292: iconst_2
    //   293: ldc -121
    //   295: aastore
    //   296: dup
    //   297: iconst_3
    //   298: ldc -119
    //   300: aastore
    //   301: dup
    //   302: iconst_4
    //   303: ldc -117
    //   305: aastore
    //   306: dup
    //   307: iconst_5
    //   308: ldc -115
    //   310: aastore
    //   311: dup
    //   312: bipush 6
    //   314: ldc -113
    //   316: aastore
    //   317: dup
    //   318: bipush 7
    //   320: ldc -111
    //   322: aastore
    //   323: dup
    //   324: bipush 8
    //   326: ldc -109
    //   328: aastore
    //   329: dup
    //   330: bipush 9
    //   332: ldc -107
    //   334: aastore
    //   335: dup
    //   336: bipush 10
    //   338: ldc -105
    //   340: aastore
    //   341: dup
    //   342: bipush 11
    //   344: ldc -103
    //   346: aastore
    //   347: ldc -101
    //   349: bipush 7
    //   351: anewarray 49	java/lang/String
    //   354: dup
    //   355: iconst_0
    //   356: ldc -99
    //   358: aastore
    //   359: dup
    //   360: iconst_1
    //   361: ldc -97
    //   363: aastore
    //   364: dup
    //   365: iconst_2
    //   366: ldc -95
    //   368: aastore
    //   369: dup
    //   370: iconst_3
    //   371: ldc -93
    //   373: aastore
    //   374: dup
    //   375: iconst_4
    //   376: ldc -91
    //   378: aastore
    //   379: dup
    //   380: iconst_5
    //   381: ldc -89
    //   383: aastore
    //   384: dup
    //   385: bipush 6
    //   387: ldc -87
    //   389: aastore
    //   390: aconst_null
    //   391: invokevirtual 59	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   394: astore 4
    //   396: aload 4
    //   398: ifnull +53 -> 451
    //   401: aload 4
    //   403: invokeinterface 65 1 0
    //   408: istore_2
    //   409: iload_2
    //   410: ifeq +41 -> 451
    //   413: aload 8
    //   415: aload 4
    //   417: iconst_0
    //   418: invokeinterface 74 2 0
    //   423: invokestatic 116	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   426: invokevirtual 173	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   429: checkcast 67	com/cootek/usage/o
    //   432: astore 5
    //   434: aload 5
    //   436: ifnonnull +281 -> 717
    //   439: aload 4
    //   441: invokeinterface 123 1 0
    //   446: istore_2
    //   447: iload_2
    //   448: ifne -35 -> 413
    //   451: aload 4
    //   453: ifnull +10 -> 463
    //   456: aload 4
    //   458: invokeinterface 126 1 0
    //   463: new 175	org/json/JSONArray
    //   466: dup
    //   467: invokespecial 176	org/json/JSONArray:<init>	()V
    //   470: astore_3
    //   471: aload 8
    //   473: invokevirtual 180	java/util/Hashtable:values	()Ljava/util/Collection;
    //   476: invokeinterface 186 1 0
    //   481: astore 4
    //   483: aload 4
    //   485: invokeinterface 191 1 0
    //   490: ifeq +2193 -> 2683
    //   493: aload 4
    //   495: invokeinterface 195 1 0
    //   500: checkcast 67	com/cootek/usage/o
    //   503: astore 6
    //   505: new 197	org/json/JSONObject
    //   508: dup
    //   509: invokespecial 198	org/json/JSONObject:<init>	()V
    //   512: astore 5
    //   514: aload 5
    //   516: ldc -56
    //   518: aload 6
    //   520: getfield 85	com/cootek/usage/o:b	Ljava/lang/String;
    //   523: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   526: pop
    //   527: aload 6
    //   529: getfield 92	com/cootek/usage/o:c	Ljava/util/HashSet;
    //   532: invokevirtual 206	java/util/HashSet:isEmpty	()Z
    //   535: ifne +1213 -> 1748
    //   538: new 175	org/json/JSONArray
    //   541: dup
    //   542: invokespecial 176	org/json/JSONArray:<init>	()V
    //   545: astore 8
    //   547: aload 6
    //   549: getfield 92	com/cootek/usage/o:c	Ljava/util/HashSet;
    //   552: invokevirtual 207	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   555: astore 9
    //   557: aload 9
    //   559: invokeinterface 191 1 0
    //   564: ifeq +1165 -> 1729
    //   567: aload 8
    //   569: aload 9
    //   571: invokeinterface 195 1 0
    //   576: checkcast 49	java/lang/String
    //   579: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   582: pop
    //   583: goto -26 -> 557
    //   586: astore 6
    //   588: aload 6
    //   590: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   593: aload_3
    //   594: aload 5
    //   596: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   599: pop
    //   600: aload 7
    //   602: iconst_1
    //   603: putfield 219	com/cootek/usage/s:d	Z
    //   606: goto -123 -> 483
    //   609: astore_3
    //   610: aload_3
    //   611: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   614: goto -343 -> 271
    //   617: astore_3
    //   618: aconst_null
    //   619: astore_3
    //   620: aload 7
    //   622: iconst_0
    //   623: putfield 219	com/cootek/usage/s:d	Z
    //   626: aload_3
    //   627: ifnull +9 -> 636
    //   630: aload_3
    //   631: invokeinterface 126 1 0
    //   636: aload 7
    //   638: areturn
    //   639: astore_3
    //   640: aload_3
    //   641: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   644: goto -8 -> 636
    //   647: astore 5
    //   649: aconst_null
    //   650: astore_3
    //   651: aload_3
    //   652: astore 4
    //   654: aload 5
    //   656: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   659: aload_3
    //   660: astore 4
    //   662: aload 7
    //   664: iconst_0
    //   665: putfield 219	com/cootek/usage/s:d	Z
    //   668: aload_3
    //   669: ifnull +9 -> 678
    //   672: aload_3
    //   673: invokeinterface 126 1 0
    //   678: aload 7
    //   680: areturn
    //   681: astore_3
    //   682: aload_3
    //   683: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   686: goto -8 -> 678
    //   689: astore_3
    //   690: aconst_null
    //   691: astore 4
    //   693: aload 4
    //   695: ifnull +10 -> 705
    //   698: aload 4
    //   700: invokeinterface 126 1 0
    //   705: aload_3
    //   706: athrow
    //   707: astore 4
    //   709: aload 4
    //   711: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   714: goto -9 -> 705
    //   717: aload 4
    //   719: iconst_1
    //   720: invokeinterface 81 2 0
    //   725: astore_3
    //   726: ldc -99
    //   728: aload_3
    //   729: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   732: ifeq +58 -> 790
    //   735: aload 4
    //   737: iconst_2
    //   738: invokeinterface 81 2 0
    //   743: astore_3
    //   744: aload 5
    //   746: getfield 92	com/cootek/usage/o:c	Ljava/util/HashSet;
    //   749: aload_3
    //   750: invokevirtual 226	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   753: pop
    //   754: goto -315 -> 439
    //   757: astore_3
    //   758: aload_3
    //   759: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   762: goto -323 -> 439
    //   765: astore_3
    //   766: aload 4
    //   768: astore_3
    //   769: aload 7
    //   771: iconst_0
    //   772: putfield 219	com/cootek/usage/s:d	Z
    //   775: aload 4
    //   777: ifnull +10 -> 787
    //   780: aload 4
    //   782: invokeinterface 126 1 0
    //   787: aload 7
    //   789: areturn
    //   790: ldc -97
    //   792: aload_3
    //   793: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   796: ifeq +130 -> 926
    //   799: new 228	com/cootek/usage/p
    //   802: dup
    //   803: aload_0
    //   804: iconst_0
    //   805: invokespecial 229	com/cootek/usage/p:<init>	(Lcom/cootek/usage/m;B)V
    //   808: astore 6
    //   810: aload 6
    //   812: aload 4
    //   814: iconst_2
    //   815: invokeinterface 81 2 0
    //   820: putfield 231	com/cootek/usage/p:a	Ljava/lang/String;
    //   823: aload 4
    //   825: iconst_3
    //   826: invokeinterface 235 2 0
    //   831: tableswitch	default:+2053->2884, 0:+2081->2912, 1:+89->920, 2:+2060->2891, 3:+2067->2898, 4:+2074->2905
    //   864: aload 6
    //   866: aload_3
    //   867: putfield 236	com/cootek/usage/p:b	Ljava/lang/String;
    //   870: aload 6
    //   872: getfield 236	com/cootek/usage/p:b	Ljava/lang/String;
    //   875: ifnonnull +16 -> 891
    //   878: aload 6
    //   880: aload 4
    //   882: iconst_4
    //   883: invokeinterface 81 2 0
    //   888: putfield 236	com/cootek/usage/p:b	Ljava/lang/String;
    //   891: aload 5
    //   893: getfield 95	com/cootek/usage/o:d	Ljava/util/HashSet;
    //   896: aload 6
    //   898: invokevirtual 226	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   901: pop
    //   902: goto -463 -> 439
    //   905: astore_3
    //   906: aload 4
    //   908: ifnull +10 -> 918
    //   911: aload 4
    //   913: invokeinterface 126 1 0
    //   918: aload_3
    //   919: athrow
    //   920: ldc -18
    //   922: astore_3
    //   923: goto -59 -> 864
    //   926: ldc -95
    //   928: aload_3
    //   929: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   932: ifeq +129 -> 1061
    //   935: new 240	com/cootek/usage/t
    //   938: dup
    //   939: aload_0
    //   940: iconst_0
    //   941: invokespecial 241	com/cootek/usage/t:<init>	(Lcom/cootek/usage/m;B)V
    //   944: astore 6
    //   946: aload 6
    //   948: aload 4
    //   950: iconst_2
    //   951: invokeinterface 81 2 0
    //   956: putfield 242	com/cootek/usage/t:b	Ljava/lang/String;
    //   959: aload 6
    //   961: aload 4
    //   963: iconst_5
    //   964: invokeinterface 81 2 0
    //   969: putfield 244	com/cootek/usage/t:c	Ljava/lang/String;
    //   972: aload 6
    //   974: aload 4
    //   976: bipush 6
    //   978: invokeinterface 81 2 0
    //   983: putfield 246	com/cootek/usage/t:d	Ljava/lang/String;
    //   986: aload 4
    //   988: iconst_3
    //   989: invokeinterface 235 2 0
    //   994: tableswitch	default:+1923->2917, 0:+1944->2938, 1:+1930->2924, 2:+1937->2931
    //   1020: aload 6
    //   1022: aload_3
    //   1023: putfield 247	com/cootek/usage/t:a	Ljava/lang/String;
    //   1026: aload 6
    //   1028: getfield 247	com/cootek/usage/t:a	Ljava/lang/String;
    //   1031: ifnonnull +16 -> 1047
    //   1034: aload 6
    //   1036: aload 4
    //   1038: iconst_4
    //   1039: invokeinterface 81 2 0
    //   1044: putfield 247	com/cootek/usage/t:a	Ljava/lang/String;
    //   1047: aload 5
    //   1049: getfield 98	com/cootek/usage/o:e	Ljava/util/HashSet;
    //   1052: aload 6
    //   1054: invokevirtual 226	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1057: pop
    //   1058: goto -619 -> 439
    //   1061: ldc -93
    //   1063: aload_3
    //   1064: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1067: ifeq +199 -> 1266
    //   1070: new 249	com/cootek/usage/r
    //   1073: dup
    //   1074: aload_0
    //   1075: iconst_0
    //   1076: invokespecial 250	com/cootek/usage/r:<init>	(Lcom/cootek/usage/m;B)V
    //   1079: astore 6
    //   1081: aload 6
    //   1083: aload 4
    //   1085: iconst_2
    //   1086: invokeinterface 81 2 0
    //   1091: putfield 251	com/cootek/usage/r:a	Ljava/lang/String;
    //   1094: aload 4
    //   1096: iconst_3
    //   1097: invokeinterface 235 2 0
    //   1102: tableswitch	default:+1841->2943, 0:+1875->2977, 1:+1855->2957, 2:+1861->2963, 3:+1868->2970
    //   1132: aload 6
    //   1134: aload_3
    //   1135: putfield 252	com/cootek/usage/r:b	Ljava/lang/String;
    //   1138: aload 6
    //   1140: getfield 252	com/cootek/usage/r:b	Ljava/lang/String;
    //   1143: ifnonnull +16 -> 1159
    //   1146: aload 6
    //   1148: aload 4
    //   1150: iconst_4
    //   1151: invokeinterface 81 2 0
    //   1156: putfield 252	com/cootek/usage/r:b	Ljava/lang/String;
    //   1159: aload 4
    //   1161: bipush 6
    //   1163: invokeinterface 235 2 0
    //   1168: tableswitch	default:+1782->2950, -1:+1821->2989, 0:+1814->2982, 1:+1847->3015, 2:+1875->3043, 3:+1868->3036, 4:+1861->3029, 5:+1826->2994, 6:+1833->3001, 7:+1840->3008, 8:+1854->3022
    //   1224: aload 6
    //   1226: aload_3
    //   1227: putfield 253	com/cootek/usage/r:c	Ljava/lang/String;
    //   1230: aload 6
    //   1232: getfield 253	com/cootek/usage/r:c	Ljava/lang/String;
    //   1235: ifnonnull +17 -> 1252
    //   1238: aload 6
    //   1240: aload 4
    //   1242: bipush 7
    //   1244: invokeinterface 81 2 0
    //   1249: putfield 253	com/cootek/usage/r:c	Ljava/lang/String;
    //   1252: aload 5
    //   1254: getfield 101	com/cootek/usage/o:f	Ljava/util/HashSet;
    //   1257: aload 6
    //   1259: invokevirtual 226	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1262: pop
    //   1263: goto -824 -> 439
    //   1266: ldc -91
    //   1268: aload_3
    //   1269: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1272: ifeq +105 -> 1377
    //   1275: new 255	com/cootek/usage/n
    //   1278: dup
    //   1279: aload_0
    //   1280: iconst_0
    //   1281: invokespecial 256	com/cootek/usage/n:<init>	(Lcom/cootek/usage/m;B)V
    //   1284: astore 6
    //   1286: aload 6
    //   1288: aload 4
    //   1290: iconst_2
    //   1291: invokeinterface 81 2 0
    //   1296: putfield 257	com/cootek/usage/n:a	Ljava/lang/String;
    //   1299: aload 4
    //   1301: iconst_3
    //   1302: invokeinterface 235 2 0
    //   1307: tableswitch	default:+1743->3050, 0:+1770->3077, 1:+1750->3057, 2:+1756->3063, 3:+1763->3070
    //   1336: aload 6
    //   1338: aload_3
    //   1339: putfield 258	com/cootek/usage/n:b	Ljava/lang/String;
    //   1342: aload 6
    //   1344: getfield 258	com/cootek/usage/n:b	Ljava/lang/String;
    //   1347: ifnonnull +16 -> 1363
    //   1350: aload 6
    //   1352: aload 4
    //   1354: iconst_4
    //   1355: invokeinterface 81 2 0
    //   1360: putfield 258	com/cootek/usage/n:b	Ljava/lang/String;
    //   1363: aload 5
    //   1365: getfield 104	com/cootek/usage/o:g	Ljava/util/HashSet;
    //   1368: aload 6
    //   1370: invokevirtual 226	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1373: pop
    //   1374: goto -935 -> 439
    //   1377: ldc -89
    //   1379: aload_3
    //   1380: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1383: ifeq +106 -> 1489
    //   1386: new 260	com/cootek/usage/q
    //   1389: dup
    //   1390: aload_0
    //   1391: iconst_0
    //   1392: invokespecial 261	com/cootek/usage/q:<init>	(Lcom/cootek/usage/m;B)V
    //   1395: astore 6
    //   1397: aload 6
    //   1399: aload 4
    //   1401: iconst_2
    //   1402: invokeinterface 81 2 0
    //   1407: putfield 262	com/cootek/usage/q:a	Ljava/lang/String;
    //   1410: aload 4
    //   1412: iconst_3
    //   1413: invokeinterface 235 2 0
    //   1418: tableswitch	default:+1664->3082, 0:+1692->3110, 1:+1671->3089, 2:+1685->3103, 3:+1678->3096
    //   1448: aload 6
    //   1450: aload_3
    //   1451: putfield 263	com/cootek/usage/q:b	Ljava/lang/String;
    //   1454: aload 6
    //   1456: getfield 263	com/cootek/usage/q:b	Ljava/lang/String;
    //   1459: ifnonnull +16 -> 1475
    //   1462: aload 6
    //   1464: aload 4
    //   1466: iconst_4
    //   1467: invokeinterface 81 2 0
    //   1472: putfield 263	com/cootek/usage/q:b	Ljava/lang/String;
    //   1475: aload 5
    //   1477: getfield 107	com/cootek/usage/o:h	Ljava/util/HashSet;
    //   1480: aload 6
    //   1482: invokevirtual 226	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1485: pop
    //   1486: goto -1047 -> 439
    //   1489: ldc -87
    //   1491: aload_3
    //   1492: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1495: ifeq -1056 -> 439
    //   1498: new 265	com/cootek/usage/u
    //   1501: dup
    //   1502: aload_0
    //   1503: iconst_0
    //   1504: invokespecial 266	com/cootek/usage/u:<init>	(Lcom/cootek/usage/m;B)V
    //   1507: astore 6
    //   1509: aload 6
    //   1511: aload 4
    //   1513: iconst_2
    //   1514: invokeinterface 81 2 0
    //   1519: putfield 267	com/cootek/usage/u:a	Ljava/lang/String;
    //   1522: aload 4
    //   1524: iconst_3
    //   1525: invokeinterface 235 2 0
    //   1530: tableswitch	default:+1585->3115, 0:+122->1652, 1:+1592->3122, 2:+1599->3129, 3:+1606->3136, 4:+1613->3143, 5:+1620->3150, 6:+1627->3157, 7:+1634->3164, 8:+1641->3171, 9:+1648->3178, 10:+1655->3185, 11:+1662->3192, 12:+1669->3199, 13:+1676->3206, 14:+115->1645
    //   1604: aload 6
    //   1606: aload_3
    //   1607: putfield 268	com/cootek/usage/u:b	Ljava/lang/String;
    //   1610: aload 6
    //   1612: getfield 268	com/cootek/usage/u:b	Ljava/lang/String;
    //   1615: ifnonnull +16 -> 1631
    //   1618: aload 6
    //   1620: aload 4
    //   1622: iconst_4
    //   1623: invokeinterface 81 2 0
    //   1628: putfield 268	com/cootek/usage/u:b	Ljava/lang/String;
    //   1631: aload 5
    //   1633: getfield 110	com/cootek/usage/o:i	Ljava/util/HashSet;
    //   1636: aload 6
    //   1638: invokevirtual 226	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1641: pop
    //   1642: goto -1203 -> 439
    //   1645: ldc_w 270
    //   1648: astore_3
    //   1649: goto -45 -> 1604
    //   1652: aconst_null
    //   1653: astore_3
    //   1654: goto -50 -> 1604
    //   1657: astore_3
    //   1658: aload_3
    //   1659: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   1662: goto -1199 -> 463
    //   1665: astore_3
    //   1666: aload_3
    //   1667: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   1670: goto -883 -> 787
    //   1673: astore 5
    //   1675: aload 6
    //   1677: astore 4
    //   1679: aload 4
    //   1681: astore_3
    //   1682: aload 5
    //   1684: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   1687: aload 4
    //   1689: astore_3
    //   1690: aload 7
    //   1692: iconst_0
    //   1693: putfield 219	com/cootek/usage/s:d	Z
    //   1696: aload 4
    //   1698: ifnull +10 -> 1708
    //   1701: aload 4
    //   1703: invokeinterface 126 1 0
    //   1708: aload 7
    //   1710: areturn
    //   1711: astore_3
    //   1712: aload_3
    //   1713: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   1716: goto -8 -> 1708
    //   1719: astore 4
    //   1721: aload 4
    //   1723: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   1726: goto -808 -> 918
    //   1729: aload 8
    //   1731: invokevirtual 274	org/json/JSONArray:length	()I
    //   1734: ifle +14 -> 1748
    //   1737: aload 5
    //   1739: ldc_w 276
    //   1742: aload 8
    //   1744: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1747: pop
    //   1748: aload 6
    //   1750: getfield 95	com/cootek/usage/o:d	Ljava/util/HashSet;
    //   1753: invokevirtual 206	java/util/HashSet:isEmpty	()Z
    //   1756: ifne +133 -> 1889
    //   1759: new 175	org/json/JSONArray
    //   1762: dup
    //   1763: invokespecial 176	org/json/JSONArray:<init>	()V
    //   1766: astore 8
    //   1768: aload 6
    //   1770: getfield 95	com/cootek/usage/o:d	Ljava/util/HashSet;
    //   1773: invokevirtual 207	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1776: astore 9
    //   1778: aload 9
    //   1780: invokeinterface 191 1 0
    //   1785: ifeq +85 -> 1870
    //   1788: aload 9
    //   1790: invokeinterface 195 1 0
    //   1795: checkcast 228	com/cootek/usage/p
    //   1798: astore 10
    //   1800: new 197	org/json/JSONObject
    //   1803: dup
    //   1804: invokespecial 198	org/json/JSONObject:<init>	()V
    //   1807: astore 11
    //   1809: aload 10
    //   1811: getfield 231	com/cootek/usage/p:a	Ljava/lang/String;
    //   1814: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1817: ifne -39 -> 1778
    //   1820: aload 11
    //   1822: ldc_w 283
    //   1825: aload 10
    //   1827: getfield 231	com/cootek/usage/p:a	Ljava/lang/String;
    //   1830: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1833: pop
    //   1834: aload 10
    //   1836: getfield 236	com/cootek/usage/p:b	Ljava/lang/String;
    //   1839: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1842: ifne +17 -> 1859
    //   1845: aload 11
    //   1847: ldc_w 285
    //   1850: aload 10
    //   1852: getfield 236	com/cootek/usage/p:b	Ljava/lang/String;
    //   1855: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1858: pop
    //   1859: aload 8
    //   1861: aload 11
    //   1863: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1866: pop
    //   1867: goto -89 -> 1778
    //   1870: aload 8
    //   1872: invokevirtual 274	org/json/JSONArray:length	()I
    //   1875: ifle +14 -> 1889
    //   1878: aload 5
    //   1880: ldc_w 287
    //   1883: aload 8
    //   1885: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1888: pop
    //   1889: aload 6
    //   1891: getfield 98	com/cootek/usage/o:e	Ljava/util/HashSet;
    //   1894: invokevirtual 206	java/util/HashSet:isEmpty	()Z
    //   1897: ifne +195 -> 2092
    //   1900: new 175	org/json/JSONArray
    //   1903: dup
    //   1904: invokespecial 176	org/json/JSONArray:<init>	()V
    //   1907: astore 8
    //   1909: aload 6
    //   1911: getfield 98	com/cootek/usage/o:e	Ljava/util/HashSet;
    //   1914: invokevirtual 207	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1917: astore 9
    //   1919: aload 9
    //   1921: invokeinterface 191 1 0
    //   1926: ifeq +147 -> 2073
    //   1929: aload 9
    //   1931: invokeinterface 195 1 0
    //   1936: checkcast 240	com/cootek/usage/t
    //   1939: astore 10
    //   1941: new 197	org/json/JSONObject
    //   1944: dup
    //   1945: invokespecial 198	org/json/JSONObject:<init>	()V
    //   1948: astore 11
    //   1950: aload 10
    //   1952: getfield 247	com/cootek/usage/t:a	Ljava/lang/String;
    //   1955: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1958: ifne +921 -> 2879
    //   1961: aload 11
    //   1963: ldc_w 285
    //   1966: aload 10
    //   1968: getfield 247	com/cootek/usage/t:a	Ljava/lang/String;
    //   1971: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1974: pop
    //   1975: iconst_1
    //   1976: istore_1
    //   1977: aload 10
    //   1979: getfield 247	com/cootek/usage/t:a	Ljava/lang/String;
    //   1982: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1985: ifne +19 -> 2004
    //   1988: aload 11
    //   1990: ldc_w 289
    //   1993: aload 10
    //   1995: getfield 242	com/cootek/usage/t:b	Ljava/lang/String;
    //   1998: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2001: pop
    //   2002: iconst_1
    //   2003: istore_1
    //   2004: aload 10
    //   2006: getfield 247	com/cootek/usage/t:a	Ljava/lang/String;
    //   2009: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2012: ifne +19 -> 2031
    //   2015: aload 11
    //   2017: ldc_w 291
    //   2020: aload 10
    //   2022: getfield 244	com/cootek/usage/t:c	Ljava/lang/String;
    //   2025: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2028: pop
    //   2029: iconst_1
    //   2030: istore_1
    //   2031: aload 10
    //   2033: getfield 247	com/cootek/usage/t:a	Ljava/lang/String;
    //   2036: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2039: ifne +837 -> 2876
    //   2042: aload 11
    //   2044: ldc_w 293
    //   2047: aload 10
    //   2049: getfield 246	com/cootek/usage/t:d	Ljava/lang/String;
    //   2052: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2055: pop
    //   2056: iconst_1
    //   2057: istore_1
    //   2058: iload_1
    //   2059: ifeq -140 -> 1919
    //   2062: aload 8
    //   2064: aload 11
    //   2066: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2069: pop
    //   2070: goto -151 -> 1919
    //   2073: aload 8
    //   2075: invokevirtual 274	org/json/JSONArray:length	()I
    //   2078: ifle +14 -> 2092
    //   2081: aload 5
    //   2083: ldc_w 295
    //   2086: aload 8
    //   2088: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2091: pop
    //   2092: aload 6
    //   2094: getfield 101	com/cootek/usage/o:f	Ljava/util/HashSet;
    //   2097: invokevirtual 206	java/util/HashSet:isEmpty	()Z
    //   2100: ifne +158 -> 2258
    //   2103: new 175	org/json/JSONArray
    //   2106: dup
    //   2107: invokespecial 176	org/json/JSONArray:<init>	()V
    //   2110: astore 8
    //   2112: aload 6
    //   2114: getfield 101	com/cootek/usage/o:f	Ljava/util/HashSet;
    //   2117: invokevirtual 207	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2120: astore 9
    //   2122: aload 9
    //   2124: invokeinterface 191 1 0
    //   2129: ifeq +118 -> 2247
    //   2132: aload 9
    //   2134: invokeinterface 195 1 0
    //   2139: checkcast 249	com/cootek/usage/r
    //   2142: astore 10
    //   2144: new 197	org/json/JSONObject
    //   2147: dup
    //   2148: invokespecial 198	org/json/JSONObject:<init>	()V
    //   2151: astore 11
    //   2153: aload 10
    //   2155: getfield 251	com/cootek/usage/r:a	Ljava/lang/String;
    //   2158: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2161: ifne -39 -> 2122
    //   2164: aload 11
    //   2166: ldc_w 297
    //   2169: aload 10
    //   2171: getfield 251	com/cootek/usage/r:a	Ljava/lang/String;
    //   2174: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2177: pop
    //   2178: aload 10
    //   2180: getfield 252	com/cootek/usage/r:b	Ljava/lang/String;
    //   2183: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2186: ifne +17 -> 2203
    //   2189: aload 11
    //   2191: ldc_w 285
    //   2194: aload 10
    //   2196: getfield 252	com/cootek/usage/r:b	Ljava/lang/String;
    //   2199: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2202: pop
    //   2203: aload 10
    //   2205: getfield 253	com/cootek/usage/r:c	Ljava/lang/String;
    //   2208: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2211: ifne +17 -> 2228
    //   2214: aload 11
    //   2216: ldc_w 299
    //   2219: aload 10
    //   2221: getfield 253	com/cootek/usage/r:c	Ljava/lang/String;
    //   2224: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2227: pop
    //   2228: aload 8
    //   2230: invokevirtual 274	org/json/JSONArray:length	()I
    //   2233: ifle -111 -> 2122
    //   2236: aload 8
    //   2238: aload 11
    //   2240: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2243: pop
    //   2244: goto -122 -> 2122
    //   2247: aload 5
    //   2249: ldc_w 301
    //   2252: aload 8
    //   2254: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2257: pop
    //   2258: aload 6
    //   2260: getfield 104	com/cootek/usage/o:g	Ljava/util/HashSet;
    //   2263: invokevirtual 206	java/util/HashSet:isEmpty	()Z
    //   2266: ifne +133 -> 2399
    //   2269: new 175	org/json/JSONArray
    //   2272: dup
    //   2273: invokespecial 176	org/json/JSONArray:<init>	()V
    //   2276: astore 8
    //   2278: aload 6
    //   2280: getfield 104	com/cootek/usage/o:g	Ljava/util/HashSet;
    //   2283: invokevirtual 207	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2286: astore 9
    //   2288: aload 9
    //   2290: invokeinterface 191 1 0
    //   2295: ifeq +85 -> 2380
    //   2298: aload 9
    //   2300: invokeinterface 195 1 0
    //   2305: checkcast 255	com/cootek/usage/n
    //   2308: astore 10
    //   2310: new 197	org/json/JSONObject
    //   2313: dup
    //   2314: invokespecial 198	org/json/JSONObject:<init>	()V
    //   2317: astore 11
    //   2319: aload 10
    //   2321: getfield 257	com/cootek/usage/n:a	Ljava/lang/String;
    //   2324: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2327: ifne -39 -> 2288
    //   2330: aload 11
    //   2332: ldc_w 303
    //   2335: aload 10
    //   2337: getfield 257	com/cootek/usage/n:a	Ljava/lang/String;
    //   2340: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2343: pop
    //   2344: aload 10
    //   2346: getfield 258	com/cootek/usage/n:b	Ljava/lang/String;
    //   2349: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2352: ifne +17 -> 2369
    //   2355: aload 11
    //   2357: ldc_w 285
    //   2360: aload 10
    //   2362: getfield 258	com/cootek/usage/n:b	Ljava/lang/String;
    //   2365: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2368: pop
    //   2369: aload 8
    //   2371: aload 11
    //   2373: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2376: pop
    //   2377: goto -89 -> 2288
    //   2380: aload 8
    //   2382: invokevirtual 274	org/json/JSONArray:length	()I
    //   2385: ifle +14 -> 2399
    //   2388: aload 5
    //   2390: ldc_w 283
    //   2393: aload 8
    //   2395: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2398: pop
    //   2399: aload 6
    //   2401: getfield 107	com/cootek/usage/o:h	Ljava/util/HashSet;
    //   2404: invokevirtual 206	java/util/HashSet:isEmpty	()Z
    //   2407: ifne +133 -> 2540
    //   2410: new 175	org/json/JSONArray
    //   2413: dup
    //   2414: invokespecial 176	org/json/JSONArray:<init>	()V
    //   2417: astore 8
    //   2419: aload 6
    //   2421: getfield 107	com/cootek/usage/o:h	Ljava/util/HashSet;
    //   2424: invokevirtual 207	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2427: astore 9
    //   2429: aload 9
    //   2431: invokeinterface 191 1 0
    //   2436: ifeq +85 -> 2521
    //   2439: aload 9
    //   2441: invokeinterface 195 1 0
    //   2446: checkcast 260	com/cootek/usage/q
    //   2449: astore 10
    //   2451: new 197	org/json/JSONObject
    //   2454: dup
    //   2455: invokespecial 198	org/json/JSONObject:<init>	()V
    //   2458: astore 11
    //   2460: aload 10
    //   2462: getfield 262	com/cootek/usage/q:a	Ljava/lang/String;
    //   2465: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2468: ifne -39 -> 2429
    //   2471: aload 11
    //   2473: ldc_w 305
    //   2476: aload 10
    //   2478: getfield 262	com/cootek/usage/q:a	Ljava/lang/String;
    //   2481: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2484: pop
    //   2485: aload 10
    //   2487: getfield 263	com/cootek/usage/q:b	Ljava/lang/String;
    //   2490: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2493: ifne +17 -> 2510
    //   2496: aload 11
    //   2498: ldc_w 285
    //   2501: aload 10
    //   2503: getfield 263	com/cootek/usage/q:b	Ljava/lang/String;
    //   2506: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2509: pop
    //   2510: aload 8
    //   2512: aload 11
    //   2514: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2517: pop
    //   2518: goto -89 -> 2429
    //   2521: aload 8
    //   2523: invokevirtual 274	org/json/JSONArray:length	()I
    //   2526: ifle +14 -> 2540
    //   2529: aload 5
    //   2531: ldc_w 307
    //   2534: aload 8
    //   2536: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2539: pop
    //   2540: aload 6
    //   2542: getfield 110	com/cootek/usage/o:i	Ljava/util/HashSet;
    //   2545: invokevirtual 206	java/util/HashSet:isEmpty	()Z
    //   2548: ifne -1955 -> 593
    //   2551: new 175	org/json/JSONArray
    //   2554: dup
    //   2555: invokespecial 176	org/json/JSONArray:<init>	()V
    //   2558: astore 8
    //   2560: aload 6
    //   2562: getfield 110	com/cootek/usage/o:i	Ljava/util/HashSet;
    //   2565: invokevirtual 207	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2568: astore 6
    //   2570: aload 6
    //   2572: invokeinterface 191 1 0
    //   2577: ifeq +84 -> 2661
    //   2580: aload 6
    //   2582: invokeinterface 195 1 0
    //   2587: checkcast 265	com/cootek/usage/u
    //   2590: astore 9
    //   2592: new 197	org/json/JSONObject
    //   2595: dup
    //   2596: invokespecial 198	org/json/JSONObject:<init>	()V
    //   2599: astore 10
    //   2601: aload 9
    //   2603: getfield 267	com/cootek/usage/u:a	Ljava/lang/String;
    //   2606: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2609: ifne -39 -> 2570
    //   2612: aload 10
    //   2614: ldc -56
    //   2616: aload 9
    //   2618: getfield 267	com/cootek/usage/u:a	Ljava/lang/String;
    //   2621: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2624: pop
    //   2625: aload 9
    //   2627: getfield 268	com/cootek/usage/u:b	Ljava/lang/String;
    //   2630: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2633: ifne +17 -> 2650
    //   2636: aload 10
    //   2638: ldc_w 285
    //   2641: aload 9
    //   2643: getfield 268	com/cootek/usage/u:b	Ljava/lang/String;
    //   2646: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2649: pop
    //   2650: aload 8
    //   2652: aload 10
    //   2654: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2657: pop
    //   2658: goto -88 -> 2570
    //   2661: aload 8
    //   2663: invokevirtual 274	org/json/JSONArray:length	()I
    //   2666: ifle -2073 -> 593
    //   2669: aload 5
    //   2671: ldc_w 309
    //   2674: aload 8
    //   2676: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2679: pop
    //   2680: goto -2087 -> 593
    //   2683: aload_0
    //   2684: getfield 13	com/cootek/usage/m:a	Lcom/cootek/usage/b;
    //   2687: invokevirtual 313	com/cootek/usage/b:getPhoneAccount	()Ljava/lang/String;
    //   2690: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2693: ifne +63 -> 2756
    //   2696: new 197	org/json/JSONObject
    //   2699: dup
    //   2700: invokespecial 198	org/json/JSONObject:<init>	()V
    //   2703: astore 4
    //   2705: aload 4
    //   2707: ldc -56
    //   2709: ldc_w 315
    //   2712: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2715: pop
    //   2716: new 175	org/json/JSONArray
    //   2719: dup
    //   2720: invokespecial 176	org/json/JSONArray:<init>	()V
    //   2723: astore 5
    //   2725: aload 5
    //   2727: aload_0
    //   2728: getfield 13	com/cootek/usage/m:a	Lcom/cootek/usage/b;
    //   2731: invokevirtual 313	com/cootek/usage/b:getPhoneAccount	()Ljava/lang/String;
    //   2734: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2737: pop
    //   2738: aload 4
    //   2740: ldc_w 276
    //   2743: aload 5
    //   2745: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2748: pop
    //   2749: aload_3
    //   2750: aload 4
    //   2752: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2755: pop
    //   2756: new 317	com/cootek/usage/UsageData
    //   2759: dup
    //   2760: invokespecial 318	com/cootek/usage/UsageData:<init>	()V
    //   2763: astore 4
    //   2765: aload 4
    //   2767: ldc_w 320
    //   2770: putfield 322	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   2773: aload 4
    //   2775: iconst_0
    //   2776: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   2779: putfield 327	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   2782: aload 4
    //   2784: aload_3
    //   2785: invokevirtual 330	org/json/JSONArray:toString	()Ljava/lang/String;
    //   2788: putfield 333	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   2791: aload 7
    //   2793: aload 4
    //   2795: putfield 336	com/cootek/usage/s:a	Lcom/cootek/usage/UsageData;
    //   2798: aload 7
    //   2800: iconst_0
    //   2801: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   2804: putfield 337	com/cootek/usage/s:c	Ljava/lang/String;
    //   2807: aload 7
    //   2809: areturn
    //   2810: astore 5
    //   2812: aload 5
    //   2814: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   2817: goto -68 -> 2749
    //   2820: astore_3
    //   2821: aconst_null
    //   2822: astore 4
    //   2824: goto -1918 -> 906
    //   2827: astore 5
    //   2829: aload_3
    //   2830: astore 4
    //   2832: aload 5
    //   2834: astore_3
    //   2835: goto -1929 -> 906
    //   2838: astore 5
    //   2840: goto -1161 -> 1679
    //   2843: astore_3
    //   2844: aload 5
    //   2846: astore 4
    //   2848: goto -2082 -> 766
    //   2851: astore_3
    //   2852: goto -2159 -> 693
    //   2855: astore 5
    //   2857: aload_3
    //   2858: astore 4
    //   2860: aload 5
    //   2862: astore_3
    //   2863: goto -2170 -> 693
    //   2866: astore 5
    //   2868: goto -2217 -> 651
    //   2871: astore 4
    //   2873: goto -2253 -> 620
    //   2876: goto -818 -> 2058
    //   2879: iconst_0
    //   2880: istore_1
    //   2881: goto -904 -> 1977
    //   2884: ldc_w 339
    //   2887: astore_3
    //   2888: goto -2024 -> 864
    //   2891: ldc_w 341
    //   2894: astore_3
    //   2895: goto -2031 -> 864
    //   2898: ldc_w 343
    //   2901: astore_3
    //   2902: goto -2038 -> 864
    //   2905: ldc_w 345
    //   2908: astore_3
    //   2909: goto -2045 -> 864
    //   2912: aconst_null
    //   2913: astore_3
    //   2914: goto -2050 -> 864
    //   2917: ldc_w 339
    //   2920: astore_3
    //   2921: goto -1901 -> 1020
    //   2924: ldc_w 341
    //   2927: astore_3
    //   2928: goto -1908 -> 1020
    //   2931: ldc_w 343
    //   2934: astore_3
    //   2935: goto -1915 -> 1020
    //   2938: aconst_null
    //   2939: astore_3
    //   2940: goto -1920 -> 1020
    //   2943: ldc_w 339
    //   2946: astore_3
    //   2947: goto -1815 -> 1132
    //   2950: ldc_w 339
    //   2953: astore_3
    //   2954: goto -1730 -> 1224
    //   2957: ldc -18
    //   2959: astore_3
    //   2960: goto -1828 -> 1132
    //   2963: ldc_w 341
    //   2966: astore_3
    //   2967: goto -1835 -> 1132
    //   2970: ldc_w 343
    //   2973: astore_3
    //   2974: goto -1842 -> 1132
    //   2977: aconst_null
    //   2978: astore_3
    //   2979: goto -1847 -> 1132
    //   2982: ldc_w 347
    //   2985: astore_3
    //   2986: goto -1762 -> 1224
    //   2989: aconst_null
    //   2990: astore_3
    //   2991: goto -1767 -> 1224
    //   2994: ldc_w 349
    //   2997: astore_3
    //   2998: goto -1774 -> 1224
    //   3001: ldc_w 351
    //   3004: astore_3
    //   3005: goto -1781 -> 1224
    //   3008: ldc_w 353
    //   3011: astore_3
    //   3012: goto -1788 -> 1224
    //   3015: ldc_w 355
    //   3018: astore_3
    //   3019: goto -1795 -> 1224
    //   3022: ldc_w 357
    //   3025: astore_3
    //   3026: goto -1802 -> 1224
    //   3029: ldc_w 359
    //   3032: astore_3
    //   3033: goto -1809 -> 1224
    //   3036: ldc_w 361
    //   3039: astore_3
    //   3040: goto -1816 -> 1224
    //   3043: ldc_w 363
    //   3046: astore_3
    //   3047: goto -1823 -> 1224
    //   3050: ldc_w 339
    //   3053: astore_3
    //   3054: goto -1718 -> 1336
    //   3057: ldc -18
    //   3059: astore_3
    //   3060: goto -1724 -> 1336
    //   3063: ldc_w 341
    //   3066: astore_3
    //   3067: goto -1731 -> 1336
    //   3070: ldc_w 343
    //   3073: astore_3
    //   3074: goto -1738 -> 1336
    //   3077: aconst_null
    //   3078: astore_3
    //   3079: goto -1743 -> 1336
    //   3082: ldc_w 339
    //   3085: astore_3
    //   3086: goto -1638 -> 1448
    //   3089: ldc_w 365
    //   3092: astore_3
    //   3093: goto -1645 -> 1448
    //   3096: ldc_w 367
    //   3099: astore_3
    //   3100: goto -1652 -> 1448
    //   3103: ldc_w 343
    //   3106: astore_3
    //   3107: goto -1659 -> 1448
    //   3110: aconst_null
    //   3111: astore_3
    //   3112: goto -1664 -> 1448
    //   3115: ldc_w 339
    //   3118: astore_3
    //   3119: goto -1515 -> 1604
    //   3122: ldc_w 369
    //   3125: astore_3
    //   3126: goto -1522 -> 1604
    //   3129: ldc_w 371
    //   3132: astore_3
    //   3133: goto -1529 -> 1604
    //   3136: ldc_w 373
    //   3139: astore_3
    //   3140: goto -1536 -> 1604
    //   3143: ldc_w 375
    //   3146: astore_3
    //   3147: goto -1543 -> 1604
    //   3150: ldc_w 377
    //   3153: astore_3
    //   3154: goto -1550 -> 1604
    //   3157: ldc_w 379
    //   3160: astore_3
    //   3161: goto -1557 -> 1604
    //   3164: ldc_w 381
    //   3167: astore_3
    //   3168: goto -1564 -> 1604
    //   3171: ldc_w 383
    //   3174: astore_3
    //   3175: goto -1571 -> 1604
    //   3178: ldc_w 385
    //   3181: astore_3
    //   3182: goto -1578 -> 1604
    //   3185: ldc_w 387
    //   3188: astore_3
    //   3189: goto -1585 -> 1604
    //   3192: ldc_w 389
    //   3195: astore_3
    //   3196: goto -1592 -> 1604
    //   3199: ldc_w 391
    //   3202: astore_3
    //   3203: goto -1599 -> 1604
    //   3206: ldc_w 393
    //   3209: astore_3
    //   3210: goto -1606 -> 1604
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3213	0	this	m
    //   1976	905	1	i	int
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
    //   922	732	3	str2	String
    //   1657	2	3	localRuntimeException5	RuntimeException
    //   1665	2	3	localRuntimeException6	RuntimeException
    //   1681	9	3	localObject7	Object
    //   1711	1074	3	localRuntimeException7	RuntimeException
    //   2820	10	3	localObject8	Object
    //   2834	1	3	localObject9	Object
    //   2843	1	3	localSecurityException3	SecurityException
    //   2851	7	3	localObject10	Object
    //   2862	348	3	localObject11	Object
    //   68	631	4	localObject12	Object
    //   707	914	4	localRuntimeException8	RuntimeException
    //   1677	25	4	localObject13	Object
    //   1719	3	4	localRuntimeException9	RuntimeException
    //   2703	156	4	localObject14	Object
    //   2871	1	4	localSecurityException4	SecurityException
    //   4	591	5	localObject15	Object
    //   647	985	5	localRuntimeException10	RuntimeException
    //   1673	997	5	localRuntimeException11	RuntimeException
    //   2723	21	5	localJSONArray	JSONArray
    //   2810	3	5	localJSONException1	JSONException
    //   2827	6	5	localObject16	Object
    //   2838	7	5	localRuntimeException12	RuntimeException
    //   2855	6	5	localObject17	Object
    //   2866	1	5	localRuntimeException13	RuntimeException
    //   1	547	6	localO	o
    //   586	3	6	localJSONException2	JSONException
    //   808	1773	6	localObject18	Object
    //   14	2794	7	localS	s
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
    //   926	1020	757	java/lang/RuntimeException
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
    //   926	1020	765	java/lang/SecurityException
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
    //   926	1020	905	finally
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
    //   2705	2749	2810	org/json/JSONException
    //   271	396	2820	finally
    //   769	775	2827	finally
    //   1682	1687	2827	finally
    //   1690	1696	2827	finally
    //   401	409	2838	java/lang/RuntimeException
    //   439	447	2838	java/lang/RuntimeException
    //   758	762	2838	java/lang/RuntimeException
    //   271	396	2843	java/lang/SecurityException
    //   70	79	2851	finally
    //   82	93	2851	finally
    //   96	108	2851	finally
    //   111	123	2851	finally
    //   126	138	2851	finally
    //   141	153	2851	finally
    //   156	168	2851	finally
    //   171	183	2851	finally
    //   186	198	2851	finally
    //   201	213	2851	finally
    //   216	228	2851	finally
    //   231	247	2851	finally
    //   250	257	2851	finally
    //   654	659	2851	finally
    //   662	668	2851	finally
    //   620	626	2855	finally
    //   70	79	2866	java/lang/RuntimeException
    //   82	93	2866	java/lang/RuntimeException
    //   96	108	2866	java/lang/RuntimeException
    //   111	123	2866	java/lang/RuntimeException
    //   126	138	2866	java/lang/RuntimeException
    //   141	153	2866	java/lang/RuntimeException
    //   156	168	2866	java/lang/RuntimeException
    //   171	183	2866	java/lang/RuntimeException
    //   186	198	2866	java/lang/RuntimeException
    //   201	213	2866	java/lang/RuntimeException
    //   216	228	2866	java/lang/RuntimeException
    //   231	247	2866	java/lang/RuntimeException
    //   250	257	2866	java/lang/RuntimeException
    //   70	79	2871	java/lang/SecurityException
    //   82	93	2871	java/lang/SecurityException
    //   96	108	2871	java/lang/SecurityException
    //   111	123	2871	java/lang/SecurityException
    //   126	138	2871	java/lang/SecurityException
    //   141	153	2871	java/lang/SecurityException
    //   156	168	2871	java/lang/SecurityException
    //   171	183	2871	java/lang/SecurityException
    //   186	198	2871	java/lang/SecurityException
    //   201	213	2871	java/lang/SecurityException
    //   216	228	2871	java/lang/SecurityException
    //   231	247	2871	java/lang/SecurityException
    //   250	257	2871	java/lang/SecurityException
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
  
  /* Error */
  private s b()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/s
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 26	com/cootek/usage/s:<init>	(Lcom/cootek/usage/m;)V
    //   8: astore 10
    //   10: aload_0
    //   11: getfield 13	com/cootek/usage/m:a	Lcom/cootek/usage/b;
    //   14: invokevirtual 32	com/cootek/usage/b:getContext	()Landroid/content/Context;
    //   17: invokevirtual 38	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 7
    //   22: new 175	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 176	org/json/JSONArray:<init>	()V
    //   29: astore 11
    //   31: invokestatic 425	com/cootek/usage/z:a	()Lcom/cootek/usage/z;
    //   34: iconst_1
    //   35: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   38: invokevirtual 428	com/cootek/usage/z:c	(Ljava/lang/String;)J
    //   41: lstore_2
    //   42: aload 7
    //   44: getstatic 431	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   47: bipush 6
    //   49: anewarray 49	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: ldc 51
    //   56: aastore
    //   57: dup
    //   58: iconst_1
    //   59: ldc_w 433
    //   62: aastore
    //   63: dup
    //   64: iconst_2
    //   65: ldc_w 305
    //   68: aastore
    //   69: dup
    //   70: iconst_3
    //   71: ldc_w 435
    //   74: aastore
    //   75: dup
    //   76: iconst_4
    //   77: ldc_w 285
    //   80: aastore
    //   81: dup
    //   82: iconst_5
    //   83: ldc -56
    //   85: aastore
    //   86: ldc_w 437
    //   89: iconst_1
    //   90: anewarray 49	java/lang/String
    //   93: dup
    //   94: iconst_0
    //   95: lload_2
    //   96: invokestatic 440	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   99: aastore
    //   100: ldc_w 442
    //   103: invokevirtual 59	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +265 -> 375
    //   113: aload 7
    //   115: astore 8
    //   117: aload 7
    //   119: invokeinterface 65 1 0
    //   124: ifeq +251 -> 375
    //   127: aload 7
    //   129: astore 8
    //   131: aload 10
    //   133: aload 7
    //   135: iconst_0
    //   136: invokeinterface 74 2 0
    //   141: putfield 444	com/cootek/usage/s:b	J
    //   144: aload 7
    //   146: astore 8
    //   148: aload 7
    //   150: iconst_1
    //   151: invokeinterface 81 2 0
    //   156: astore 9
    //   158: aload 7
    //   160: astore 8
    //   162: aload 7
    //   164: iconst_2
    //   165: invokeinterface 74 2 0
    //   170: lstore 4
    //   172: aload 7
    //   174: astore 8
    //   176: aload 7
    //   178: iconst_3
    //   179: invokeinterface 74 2 0
    //   184: lstore_2
    //   185: aload 7
    //   187: astore 8
    //   189: aload 7
    //   191: iconst_4
    //   192: invokeinterface 235 2 0
    //   197: istore_1
    //   198: aload 7
    //   200: astore 8
    //   202: aload 7
    //   204: iconst_5
    //   205: invokeinterface 81 2 0
    //   210: astore 12
    //   212: aload 7
    //   214: astore 8
    //   216: new 197	org/json/JSONObject
    //   219: dup
    //   220: invokespecial 198	org/json/JSONObject:<init>	()V
    //   223: astore 13
    //   225: aload 7
    //   227: astore 8
    //   229: aload 13
    //   231: ldc_w 446
    //   234: aload 9
    //   236: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   239: pop
    //   240: aload 7
    //   242: astore 8
    //   244: aload 13
    //   246: ldc_w 305
    //   249: lload 4
    //   251: ldc2_w 447
    //   254: ldiv
    //   255: invokevirtual 451	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   258: pop
    //   259: iload_1
    //   260: iconst_2
    //   261: if_icmpne +201 -> 462
    //   264: ldc_w 453
    //   267: astore 9
    //   269: aload 7
    //   271: astore 8
    //   273: aload 13
    //   275: ldc_w 285
    //   278: aload 9
    //   280: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   283: pop
    //   284: iconst_3
    //   285: iload_1
    //   286: if_icmpne +5 -> 291
    //   289: lconst_0
    //   290: lstore_2
    //   291: aload 7
    //   293: astore 8
    //   295: aload 13
    //   297: ldc_w 435
    //   300: lload_2
    //   301: invokevirtual 451	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   304: pop
    //   305: aload 7
    //   307: astore 8
    //   309: aload 12
    //   311: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   314: ifne +156 -> 470
    //   317: iconst_1
    //   318: istore 6
    //   320: aload 7
    //   322: astore 8
    //   324: aload 13
    //   326: ldc_w 455
    //   329: iload 6
    //   331: invokevirtual 458	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   334: pop
    //   335: aload 7
    //   337: astore 8
    //   339: aload 11
    //   341: aload 13
    //   343: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   346: pop
    //   347: aload 7
    //   349: astore 8
    //   351: aload 10
    //   353: iconst_1
    //   354: putfield 219	com/cootek/usage/s:d	Z
    //   357: aload 7
    //   359: astore 8
    //   361: aload 7
    //   363: invokeinterface 123 1 0
    //   368: istore 6
    //   370: iload 6
    //   372: ifne -228 -> 144
    //   375: aload 7
    //   377: ifnull +10 -> 387
    //   380: aload 7
    //   382: invokeinterface 126 1 0
    //   387: new 197	org/json/JSONObject
    //   390: dup
    //   391: invokespecial 198	org/json/JSONObject:<init>	()V
    //   394: astore 7
    //   396: aload 7
    //   398: ldc_w 460
    //   401: aload 11
    //   403: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   406: pop
    //   407: new 317	com/cootek/usage/UsageData
    //   410: dup
    //   411: invokespecial 318	com/cootek/usage/UsageData:<init>	()V
    //   414: astore 8
    //   416: aload 8
    //   418: ldc_w 320
    //   421: putfield 322	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   424: aload 8
    //   426: iconst_1
    //   427: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   430: putfield 327	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   433: aload 8
    //   435: aload 7
    //   437: invokevirtual 461	org/json/JSONObject:toString	()Ljava/lang/String;
    //   440: putfield 333	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   443: aload 10
    //   445: aload 8
    //   447: putfield 336	com/cootek/usage/s:a	Lcom/cootek/usage/UsageData;
    //   450: aload 10
    //   452: iconst_1
    //   453: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   456: putfield 337	com/cootek/usage/s:c	Ljava/lang/String;
    //   459: aload 10
    //   461: areturn
    //   462: ldc_w 463
    //   465: astore 9
    //   467: goto -198 -> 269
    //   470: iconst_0
    //   471: istore 6
    //   473: goto -153 -> 320
    //   476: astore 9
    //   478: aload 7
    //   480: astore 8
    //   482: aload 9
    //   484: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   487: goto -130 -> 357
    //   490: astore 8
    //   492: aload 10
    //   494: iconst_0
    //   495: putfield 219	com/cootek/usage/s:d	Z
    //   498: aload 7
    //   500: ifnull +10 -> 510
    //   503: aload 7
    //   505: invokeinterface 126 1 0
    //   510: aload 10
    //   512: areturn
    //   513: astore 7
    //   515: aload 7
    //   517: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   520: goto -133 -> 387
    //   523: astore 7
    //   525: aload 7
    //   527: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   530: goto -20 -> 510
    //   533: astore 9
    //   535: aconst_null
    //   536: astore 7
    //   538: aload 7
    //   540: astore 8
    //   542: aload 9
    //   544: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   547: aload 7
    //   549: astore 8
    //   551: aload 10
    //   553: iconst_0
    //   554: putfield 219	com/cootek/usage/s:d	Z
    //   557: aload 7
    //   559: ifnull +10 -> 569
    //   562: aload 7
    //   564: invokeinterface 126 1 0
    //   569: aload 10
    //   571: areturn
    //   572: astore 7
    //   574: aload 7
    //   576: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   579: goto -10 -> 569
    //   582: astore 7
    //   584: aconst_null
    //   585: astore 8
    //   587: aload 8
    //   589: ifnull +10 -> 599
    //   592: aload 8
    //   594: invokeinterface 126 1 0
    //   599: aload 7
    //   601: athrow
    //   602: astore 8
    //   604: aload 8
    //   606: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   609: goto -10 -> 599
    //   612: astore 8
    //   614: aload 8
    //   616: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   619: goto -212 -> 407
    //   622: astore 7
    //   624: goto -37 -> 587
    //   627: astore 9
    //   629: aload 7
    //   631: astore 8
    //   633: aload 9
    //   635: astore 7
    //   637: goto -50 -> 587
    //   640: astore 9
    //   642: goto -104 -> 538
    //   645: astore 7
    //   647: aconst_null
    //   648: astore 7
    //   650: goto -158 -> 492
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	653	0	this	m
    //   197	90	1	i	int
    //   41	260	2	l1	long
    //   170	80	4	l2	long
    //   318	154	6	bool	boolean
    //   20	484	7	localObject1	Object
    //   513	3	7	localRuntimeException1	RuntimeException
    //   523	3	7	localRuntimeException2	RuntimeException
    //   536	27	7	localObject2	Object
    //   572	3	7	localRuntimeException3	RuntimeException
    //   582	18	7	localObject3	Object
    //   622	8	7	localObject4	Object
    //   635	1	7	localObject5	Object
    //   645	1	7	localSecurityException1	SecurityException
    //   648	1	7	localObject6	Object
    //   115	366	8	localObject7	Object
    //   490	1	8	localSecurityException2	SecurityException
    //   540	53	8	localObject8	Object
    //   602	3	8	localRuntimeException4	RuntimeException
    //   612	3	8	localJSONException1	JSONException
    //   631	1	8	localObject9	Object
    //   156	310	9	str1	String
    //   476	7	9	localJSONException2	JSONException
    //   533	10	9	localRuntimeException5	RuntimeException
    //   627	7	9	localObject10	Object
    //   640	1	9	localRuntimeException6	RuntimeException
    //   8	562	10	localS	s
    //   29	373	11	localJSONArray	JSONArray
    //   210	100	12	str2	String
    //   223	119	13	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   229	240	476	org/json/JSONException
    //   244	259	476	org/json/JSONException
    //   273	284	476	org/json/JSONException
    //   295	305	476	org/json/JSONException
    //   309	317	476	org/json/JSONException
    //   324	335	476	org/json/JSONException
    //   339	347	476	org/json/JSONException
    //   351	357	476	org/json/JSONException
    //   117	127	490	java/lang/SecurityException
    //   131	144	490	java/lang/SecurityException
    //   148	158	490	java/lang/SecurityException
    //   162	172	490	java/lang/SecurityException
    //   176	185	490	java/lang/SecurityException
    //   189	198	490	java/lang/SecurityException
    //   202	212	490	java/lang/SecurityException
    //   216	225	490	java/lang/SecurityException
    //   229	240	490	java/lang/SecurityException
    //   244	259	490	java/lang/SecurityException
    //   273	284	490	java/lang/SecurityException
    //   295	305	490	java/lang/SecurityException
    //   309	317	490	java/lang/SecurityException
    //   324	335	490	java/lang/SecurityException
    //   339	347	490	java/lang/SecurityException
    //   351	357	490	java/lang/SecurityException
    //   361	370	490	java/lang/SecurityException
    //   482	487	490	java/lang/SecurityException
    //   380	387	513	java/lang/RuntimeException
    //   503	510	523	java/lang/RuntimeException
    //   31	108	533	java/lang/RuntimeException
    //   562	569	572	java/lang/RuntimeException
    //   31	108	582	finally
    //   592	599	602	java/lang/RuntimeException
    //   396	407	612	org/json/JSONException
    //   117	127	622	finally
    //   131	144	622	finally
    //   148	158	622	finally
    //   162	172	622	finally
    //   176	185	622	finally
    //   189	198	622	finally
    //   202	212	622	finally
    //   216	225	622	finally
    //   229	240	622	finally
    //   244	259	622	finally
    //   273	284	622	finally
    //   295	305	622	finally
    //   309	317	622	finally
    //   324	335	622	finally
    //   339	347	622	finally
    //   351	357	622	finally
    //   361	370	622	finally
    //   482	487	622	finally
    //   542	547	622	finally
    //   551	557	622	finally
    //   492	498	627	finally
    //   117	127	640	java/lang/RuntimeException
    //   131	144	640	java/lang/RuntimeException
    //   148	158	640	java/lang/RuntimeException
    //   162	172	640	java/lang/RuntimeException
    //   176	185	640	java/lang/RuntimeException
    //   189	198	640	java/lang/RuntimeException
    //   202	212	640	java/lang/RuntimeException
    //   216	225	640	java/lang/RuntimeException
    //   229	240	640	java/lang/RuntimeException
    //   244	259	640	java/lang/RuntimeException
    //   273	284	640	java/lang/RuntimeException
    //   295	305	640	java/lang/RuntimeException
    //   309	317	640	java/lang/RuntimeException
    //   324	335	640	java/lang/RuntimeException
    //   339	347	640	java/lang/RuntimeException
    //   351	357	640	java/lang/RuntimeException
    //   361	370	640	java/lang/RuntimeException
    //   482	487	640	java/lang/RuntimeException
    //   31	108	645	java/lang/SecurityException
  }
  
  /* Error */
  private s c()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/s
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 26	com/cootek/usage/s:<init>	(Lcom/cootek/usage/m;)V
    //   8: astore 9
    //   10: aload_0
    //   11: getfield 13	com/cootek/usage/m:a	Lcom/cootek/usage/b;
    //   14: invokevirtual 32	com/cootek/usage/b:getContext	()Landroid/content/Context;
    //   17: invokevirtual 38	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 6
    //   22: new 175	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 176	org/json/JSONArray:<init>	()V
    //   29: astore 8
    //   31: invokestatic 425	com/cootek/usage/z:a	()Lcom/cootek/usage/z;
    //   34: iconst_2
    //   35: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   38: invokevirtual 428	com/cootek/usage/z:c	(Ljava/lang/String;)J
    //   41: lstore_1
    //   42: aload 6
    //   44: ldc_w 465
    //   47: invokestatic 471	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   50: bipush 6
    //   52: anewarray 49	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: ldc 51
    //   59: aastore
    //   60: dup
    //   61: iconst_1
    //   62: ldc_w 283
    //   65: aastore
    //   66: dup
    //   67: iconst_2
    //   68: ldc_w 473
    //   71: aastore
    //   72: dup
    //   73: iconst_3
    //   74: ldc_w 305
    //   77: aastore
    //   78: dup
    //   79: iconst_4
    //   80: ldc_w 475
    //   83: aastore
    //   84: dup
    //   85: iconst_5
    //   86: ldc_w 477
    //   89: aastore
    //   90: ldc_w 437
    //   93: iconst_1
    //   94: anewarray 49	java/lang/String
    //   97: dup
    //   98: iconst_0
    //   99: lload_1
    //   100: invokestatic 440	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   103: aastore
    //   104: ldc_w 479
    //   107: invokevirtual 59	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   110: astore 6
    //   112: aload 6
    //   114: ifnull +264 -> 378
    //   117: aload 6
    //   119: astore 7
    //   121: aload 6
    //   123: invokeinterface 65 1 0
    //   128: ifeq +250 -> 378
    //   131: aload 6
    //   133: astore 7
    //   135: aload 9
    //   137: aload 6
    //   139: iconst_0
    //   140: invokeinterface 74 2 0
    //   145: putfield 444	com/cootek/usage/s:b	J
    //   148: aload 6
    //   150: astore 7
    //   152: aload 6
    //   154: iconst_1
    //   155: invokeinterface 81 2 0
    //   160: astore 10
    //   162: aload 6
    //   164: astore 7
    //   166: aload 6
    //   168: iconst_2
    //   169: invokeinterface 74 2 0
    //   174: lstore_1
    //   175: lload_1
    //   176: lconst_0
    //   177: lcmp
    //   178: ifgt +182 -> 360
    //   181: aload 6
    //   183: astore 7
    //   185: aload 6
    //   187: iconst_3
    //   188: invokeinterface 74 2 0
    //   193: lstore_3
    //   194: aload 6
    //   196: astore 7
    //   198: aload 6
    //   200: iconst_4
    //   201: invokeinterface 81 2 0
    //   206: astore 11
    //   208: aload 6
    //   210: astore 7
    //   212: aload 6
    //   214: iconst_5
    //   215: invokeinterface 81 2 0
    //   220: astore 12
    //   222: aload 6
    //   224: astore 7
    //   226: new 197	org/json/JSONObject
    //   229: dup
    //   230: invokespecial 198	org/json/JSONObject:<init>	()V
    //   233: astore 13
    //   235: aload 6
    //   237: astore 7
    //   239: aload 13
    //   241: ldc_w 446
    //   244: aload 10
    //   246: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   249: pop
    //   250: aload 6
    //   252: astore 7
    //   254: aload 13
    //   256: ldc_w 305
    //   259: lload_3
    //   260: ldc2_w 447
    //   263: ldiv
    //   264: invokevirtual 451	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   267: pop
    //   268: aload 6
    //   270: astore 7
    //   272: aload 13
    //   274: ldc_w 285
    //   277: ldc_w 463
    //   280: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   283: pop
    //   284: lload_1
    //   285: lconst_0
    //   286: lcmp
    //   287: ifle +178 -> 465
    //   290: iconst_1
    //   291: istore 5
    //   293: aload 6
    //   295: astore 7
    //   297: aload 13
    //   299: ldc_w 455
    //   302: iload 5
    //   304: invokevirtual 458	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   307: pop
    //   308: aload 6
    //   310: astore 7
    //   312: aload 13
    //   314: ldc_w 481
    //   317: aload 11
    //   319: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   322: pop
    //   323: aload 6
    //   325: astore 7
    //   327: aload 13
    //   329: ldc_w 477
    //   332: aload 12
    //   334: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   337: pop
    //   338: aload 6
    //   340: astore 7
    //   342: aload 8
    //   344: aload 13
    //   346: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   349: pop
    //   350: aload 6
    //   352: astore 7
    //   354: aload 9
    //   356: iconst_1
    //   357: putfield 219	com/cootek/usage/s:d	Z
    //   360: aload 6
    //   362: astore 7
    //   364: aload 6
    //   366: invokeinterface 123 1 0
    //   371: istore 5
    //   373: iload 5
    //   375: ifne -227 -> 148
    //   378: aload 6
    //   380: ifnull +10 -> 390
    //   383: aload 6
    //   385: invokeinterface 126 1 0
    //   390: new 197	org/json/JSONObject
    //   393: dup
    //   394: invokespecial 198	org/json/JSONObject:<init>	()V
    //   397: astore 6
    //   399: aload 6
    //   401: ldc_w 460
    //   404: aload 8
    //   406: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   409: pop
    //   410: new 317	com/cootek/usage/UsageData
    //   413: dup
    //   414: invokespecial 318	com/cootek/usage/UsageData:<init>	()V
    //   417: astore 7
    //   419: aload 7
    //   421: ldc_w 320
    //   424: putfield 322	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   427: aload 7
    //   429: iconst_2
    //   430: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   433: putfield 327	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   436: aload 7
    //   438: aload 6
    //   440: invokevirtual 461	org/json/JSONObject:toString	()Ljava/lang/String;
    //   443: putfield 333	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   446: aload 9
    //   448: aload 7
    //   450: putfield 336	com/cootek/usage/s:a	Lcom/cootek/usage/UsageData;
    //   453: aload 9
    //   455: iconst_2
    //   456: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   459: putfield 337	com/cootek/usage/s:c	Ljava/lang/String;
    //   462: aload 9
    //   464: areturn
    //   465: iconst_0
    //   466: istore 5
    //   468: goto -175 -> 293
    //   471: astore 10
    //   473: aload 6
    //   475: astore 7
    //   477: aload 10
    //   479: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   482: goto -122 -> 360
    //   485: astore 7
    //   487: aload 9
    //   489: iconst_0
    //   490: putfield 219	com/cootek/usage/s:d	Z
    //   493: aload 6
    //   495: ifnull +10 -> 505
    //   498: aload 6
    //   500: invokeinterface 126 1 0
    //   505: aload 9
    //   507: areturn
    //   508: astore 6
    //   510: aload 6
    //   512: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   515: goto -125 -> 390
    //   518: astore 6
    //   520: aload 6
    //   522: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   525: goto -20 -> 505
    //   528: astore 8
    //   530: aconst_null
    //   531: astore 6
    //   533: aload 6
    //   535: astore 7
    //   537: aload 8
    //   539: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   542: aload 6
    //   544: astore 7
    //   546: aload 9
    //   548: iconst_0
    //   549: putfield 219	com/cootek/usage/s:d	Z
    //   552: aload 6
    //   554: ifnull +10 -> 564
    //   557: aload 6
    //   559: invokeinterface 126 1 0
    //   564: aload 9
    //   566: areturn
    //   567: astore 6
    //   569: aload 6
    //   571: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   574: goto -10 -> 564
    //   577: astore 6
    //   579: aconst_null
    //   580: astore 7
    //   582: aload 7
    //   584: ifnull +10 -> 594
    //   587: aload 7
    //   589: invokeinterface 126 1 0
    //   594: aload 6
    //   596: athrow
    //   597: astore 7
    //   599: aload 7
    //   601: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   604: goto -10 -> 594
    //   607: astore 7
    //   609: aload 7
    //   611: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   614: goto -204 -> 410
    //   617: astore 6
    //   619: goto -37 -> 582
    //   622: astore 8
    //   624: aload 6
    //   626: astore 7
    //   628: aload 8
    //   630: astore 6
    //   632: goto -50 -> 582
    //   635: astore 8
    //   637: goto -104 -> 533
    //   640: astore 6
    //   642: aconst_null
    //   643: astore 6
    //   645: goto -158 -> 487
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	648	0	this	m
    //   41	244	1	l1	long
    //   193	67	3	l2	long
    //   291	176	5	bool	boolean
    //   20	479	6	localObject1	Object
    //   508	3	6	localRuntimeException1	RuntimeException
    //   518	3	6	localRuntimeException2	RuntimeException
    //   531	27	6	localObject2	Object
    //   567	3	6	localRuntimeException3	RuntimeException
    //   577	18	6	localObject3	Object
    //   617	8	6	localObject4	Object
    //   630	1	6	localObject5	Object
    //   640	1	6	localSecurityException1	SecurityException
    //   643	1	6	localObject6	Object
    //   119	357	7	localObject7	Object
    //   485	1	7	localSecurityException2	SecurityException
    //   535	53	7	localObject8	Object
    //   597	3	7	localRuntimeException4	RuntimeException
    //   607	3	7	localJSONException1	JSONException
    //   626	1	7	localObject9	Object
    //   29	376	8	localJSONArray	JSONArray
    //   528	10	8	localRuntimeException5	RuntimeException
    //   622	7	8	localObject10	Object
    //   635	1	8	localRuntimeException6	RuntimeException
    //   8	557	9	localS	s
    //   160	85	10	str1	String
    //   471	7	10	localJSONException2	JSONException
    //   206	112	11	str2	String
    //   220	113	12	str3	String
    //   233	112	13	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   239	250	471	org/json/JSONException
    //   254	268	471	org/json/JSONException
    //   272	284	471	org/json/JSONException
    //   297	308	471	org/json/JSONException
    //   312	323	471	org/json/JSONException
    //   327	338	471	org/json/JSONException
    //   342	350	471	org/json/JSONException
    //   354	360	471	org/json/JSONException
    //   121	131	485	java/lang/SecurityException
    //   135	148	485	java/lang/SecurityException
    //   152	162	485	java/lang/SecurityException
    //   166	175	485	java/lang/SecurityException
    //   185	194	485	java/lang/SecurityException
    //   198	208	485	java/lang/SecurityException
    //   212	222	485	java/lang/SecurityException
    //   226	235	485	java/lang/SecurityException
    //   239	250	485	java/lang/SecurityException
    //   254	268	485	java/lang/SecurityException
    //   272	284	485	java/lang/SecurityException
    //   297	308	485	java/lang/SecurityException
    //   312	323	485	java/lang/SecurityException
    //   327	338	485	java/lang/SecurityException
    //   342	350	485	java/lang/SecurityException
    //   354	360	485	java/lang/SecurityException
    //   364	373	485	java/lang/SecurityException
    //   477	482	485	java/lang/SecurityException
    //   383	390	508	java/lang/RuntimeException
    //   498	505	518	java/lang/RuntimeException
    //   31	112	528	java/lang/RuntimeException
    //   557	564	567	java/lang/RuntimeException
    //   31	112	577	finally
    //   587	594	597	java/lang/RuntimeException
    //   399	410	607	org/json/JSONException
    //   121	131	617	finally
    //   135	148	617	finally
    //   152	162	617	finally
    //   166	175	617	finally
    //   185	194	617	finally
    //   198	208	617	finally
    //   212	222	617	finally
    //   226	235	617	finally
    //   239	250	617	finally
    //   254	268	617	finally
    //   272	284	617	finally
    //   297	308	617	finally
    //   312	323	617	finally
    //   327	338	617	finally
    //   342	350	617	finally
    //   354	360	617	finally
    //   364	373	617	finally
    //   477	482	617	finally
    //   537	542	617	finally
    //   546	552	617	finally
    //   487	493	622	finally
    //   121	131	635	java/lang/RuntimeException
    //   135	148	635	java/lang/RuntimeException
    //   152	162	635	java/lang/RuntimeException
    //   166	175	635	java/lang/RuntimeException
    //   185	194	635	java/lang/RuntimeException
    //   198	208	635	java/lang/RuntimeException
    //   212	222	635	java/lang/RuntimeException
    //   226	235	635	java/lang/RuntimeException
    //   239	250	635	java/lang/RuntimeException
    //   254	268	635	java/lang/RuntimeException
    //   272	284	635	java/lang/RuntimeException
    //   297	308	635	java/lang/RuntimeException
    //   312	323	635	java/lang/RuntimeException
    //   327	338	635	java/lang/RuntimeException
    //   342	350	635	java/lang/RuntimeException
    //   354	360	635	java/lang/RuntimeException
    //   364	373	635	java/lang/RuntimeException
    //   477	482	635	java/lang/RuntimeException
    //   31	112	640	java/lang/SecurityException
  }
  
  private s d()
  {
    s localS = new s(this);
    Object localObject2 = this.a.getContext().getPackageManager();
    List localList = ((PackageManager)localObject2).getInstalledPackages(0);
    Object localObject1 = new JSONArray();
    int i = 0;
    for (;;)
    {
      if (i < localList.size())
      {
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
          localS.d = true;
          i += 1;
        }
        catch (JSONException localJSONException2)
        {
          for (;;)
          {
            ThrowableExtension.printStackTrace(localJSONException2);
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
      localS.a = ((UsageData)localObject1);
      localS.c = a(3);
      return localS;
    }
    catch (JSONException localJSONException1)
    {
      for (;;)
      {
        ThrowableExtension.printStackTrace(localJSONException1);
      }
    }
  }
  
  /* Error */
  private s e()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/s
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 26	com/cootek/usage/s:<init>	(Lcom/cootek/usage/m;)V
    //   8: astore 13
    //   10: aload_0
    //   11: getfield 13	com/cootek/usage/m:a	Lcom/cootek/usage/b;
    //   14: invokevirtual 32	com/cootek/usage/b:getContext	()Landroid/content/Context;
    //   17: invokevirtual 38	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 10
    //   22: new 175	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 176	org/json/JSONArray:<init>	()V
    //   29: astore 14
    //   31: ldc_w 525
    //   34: invokestatic 471	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   37: astore 11
    //   39: invokestatic 425	com/cootek/usage/z:a	()Lcom/cootek/usage/z;
    //   42: iconst_1
    //   43: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   46: invokevirtual 428	com/cootek/usage/z:c	(Ljava/lang/String;)J
    //   49: lstore_3
    //   50: aload 10
    //   52: aload 11
    //   54: bipush 7
    //   56: anewarray 49	java/lang/String
    //   59: dup
    //   60: iconst_0
    //   61: ldc 51
    //   63: aastore
    //   64: dup
    //   65: iconst_1
    //   66: ldc_w 433
    //   69: aastore
    //   70: dup
    //   71: iconst_2
    //   72: ldc_w 305
    //   75: aastore
    //   76: dup
    //   77: iconst_3
    //   78: ldc_w 435
    //   81: aastore
    //   82: dup
    //   83: iconst_4
    //   84: ldc_w 285
    //   87: aastore
    //   88: dup
    //   89: iconst_5
    //   90: ldc -125
    //   92: aastore
    //   93: dup
    //   94: bipush 6
    //   96: ldc_w 527
    //   99: aastore
    //   100: ldc_w 437
    //   103: iconst_1
    //   104: anewarray 49	java/lang/String
    //   107: dup
    //   108: iconst_0
    //   109: lload_3
    //   110: invokestatic 440	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   113: aastore
    //   114: ldc_w 442
    //   117: invokevirtual 59	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   120: astore 10
    //   122: aload 10
    //   124: ifnull +294 -> 418
    //   127: aload 10
    //   129: astore 11
    //   131: aload 10
    //   133: invokeinterface 65 1 0
    //   138: ifeq +280 -> 418
    //   141: aload 10
    //   143: astore 11
    //   145: aload 13
    //   147: aload 10
    //   149: iconst_0
    //   150: invokeinterface 74 2 0
    //   155: putfield 444	com/cootek/usage/s:b	J
    //   158: aload 10
    //   160: astore 11
    //   162: aload 10
    //   164: iconst_1
    //   165: invokeinterface 81 2 0
    //   170: astore 12
    //   172: aload 10
    //   174: astore 11
    //   176: aload 10
    //   178: iconst_2
    //   179: invokeinterface 74 2 0
    //   184: lstore 7
    //   186: aload 10
    //   188: astore 11
    //   190: aload 10
    //   192: iconst_3
    //   193: invokeinterface 74 2 0
    //   198: lstore_3
    //   199: aload 10
    //   201: astore 11
    //   203: aload 10
    //   205: iconst_4
    //   206: invokeinterface 235 2 0
    //   211: istore_1
    //   212: aload 10
    //   214: astore 11
    //   216: aload 10
    //   218: iconst_5
    //   219: invokeinterface 74 2 0
    //   224: lstore 5
    //   226: aload 10
    //   228: astore 11
    //   230: aload 10
    //   232: bipush 6
    //   234: invokeinterface 235 2 0
    //   239: istore_2
    //   240: aload 10
    //   242: astore 11
    //   244: new 197	org/json/JSONObject
    //   247: dup
    //   248: invokespecial 198	org/json/JSONObject:<init>	()V
    //   251: astore 15
    //   253: aload 10
    //   255: astore 11
    //   257: aload 15
    //   259: ldc_w 446
    //   262: aload 12
    //   264: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   267: pop
    //   268: aload 10
    //   270: astore 11
    //   272: aload 15
    //   274: ldc_w 305
    //   277: lload 7
    //   279: ldc2_w 447
    //   282: ldiv
    //   283: invokevirtual 451	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   286: pop
    //   287: iconst_3
    //   288: iload_1
    //   289: if_icmpne +5 -> 294
    //   292: lconst_0
    //   293: lstore_3
    //   294: aload 10
    //   296: astore 11
    //   298: aload 15
    //   300: ldc_w 435
    //   303: lload_3
    //   304: invokevirtual 451	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   307: pop
    //   308: lload 5
    //   310: lconst_0
    //   311: lcmp
    //   312: ifeq +193 -> 505
    //   315: iconst_1
    //   316: istore 9
    //   318: aload 10
    //   320: astore 11
    //   322: aload 15
    //   324: ldc_w 455
    //   327: iload 9
    //   329: invokevirtual 458	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   332: pop
    //   333: iload_1
    //   334: iconst_2
    //   335: if_icmpne +176 -> 511
    //   338: ldc_w 453
    //   341: astore 12
    //   343: aload 10
    //   345: astore 11
    //   347: aload 15
    //   349: ldc_w 285
    //   352: aload 12
    //   354: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   357: pop
    //   358: iload_2
    //   359: ifne +160 -> 519
    //   362: aload 10
    //   364: astore 11
    //   366: aload 15
    //   368: ldc_w 529
    //   371: ldc_w 531
    //   374: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   377: pop
    //   378: aload 10
    //   380: astore 11
    //   382: aload 14
    //   384: aload 15
    //   386: invokevirtual 210	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   389: pop
    //   390: aload 10
    //   392: astore 11
    //   394: aload 13
    //   396: iconst_1
    //   397: putfield 219	com/cootek/usage/s:d	Z
    //   400: aload 10
    //   402: astore 11
    //   404: aload 10
    //   406: invokeinterface 123 1 0
    //   411: istore 9
    //   413: iload 9
    //   415: ifne -257 -> 158
    //   418: aload 10
    //   420: ifnull +10 -> 430
    //   423: aload 10
    //   425: invokeinterface 126 1 0
    //   430: new 197	org/json/JSONObject
    //   433: dup
    //   434: invokespecial 198	org/json/JSONObject:<init>	()V
    //   437: astore 10
    //   439: aload 10
    //   441: ldc_w 460
    //   444: aload 14
    //   446: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   449: pop
    //   450: new 317	com/cootek/usage/UsageData
    //   453: dup
    //   454: invokespecial 318	com/cootek/usage/UsageData:<init>	()V
    //   457: astore 11
    //   459: aload 11
    //   461: ldc_w 320
    //   464: putfield 322	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   467: aload 11
    //   469: iconst_4
    //   470: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   473: putfield 327	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   476: aload 11
    //   478: aload 10
    //   480: invokevirtual 461	org/json/JSONObject:toString	()Ljava/lang/String;
    //   483: putfield 333	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   486: aload 13
    //   488: aload 11
    //   490: putfield 336	com/cootek/usage/s:a	Lcom/cootek/usage/UsageData;
    //   493: aload 13
    //   495: iconst_4
    //   496: invokestatic 324	com/cootek/usage/m:a	(I)Ljava/lang/String;
    //   499: putfield 337	com/cootek/usage/s:c	Ljava/lang/String;
    //   502: aload 13
    //   504: areturn
    //   505: iconst_0
    //   506: istore 9
    //   508: goto -190 -> 318
    //   511: ldc_w 463
    //   514: astore 12
    //   516: goto -173 -> 343
    //   519: iload_2
    //   520: iconst_1
    //   521: if_icmpne +59 -> 580
    //   524: aload 10
    //   526: astore 11
    //   528: aload 15
    //   530: ldc_w 529
    //   533: ldc_w 533
    //   536: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   539: pop
    //   540: goto -162 -> 378
    //   543: astore 12
    //   545: aload 10
    //   547: astore 11
    //   549: aload 12
    //   551: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   554: goto -154 -> 400
    //   557: astore 11
    //   559: aload 13
    //   561: iconst_0
    //   562: putfield 219	com/cootek/usage/s:d	Z
    //   565: aload 10
    //   567: ifnull +10 -> 577
    //   570: aload 10
    //   572: invokeinterface 126 1 0
    //   577: aload 13
    //   579: areturn
    //   580: iload_2
    //   581: iconst_2
    //   582: if_icmpne +58 -> 640
    //   585: aload 10
    //   587: astore 11
    //   589: aload 15
    //   591: ldc_w 529
    //   594: ldc_w 535
    //   597: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   600: pop
    //   601: goto -223 -> 378
    //   604: astore 12
    //   606: aload 10
    //   608: astore 11
    //   610: aload 12
    //   612: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   615: aload 10
    //   617: astore 11
    //   619: aload 13
    //   621: iconst_0
    //   622: putfield 219	com/cootek/usage/s:d	Z
    //   625: aload 10
    //   627: ifnull +10 -> 637
    //   630: aload 10
    //   632: invokeinterface 126 1 0
    //   637: aload 13
    //   639: areturn
    //   640: iload_2
    //   641: iconst_3
    //   642: if_icmpne -264 -> 378
    //   645: aload 10
    //   647: astore 11
    //   649: aload 15
    //   651: ldc_w 529
    //   654: ldc_w 537
    //   657: invokevirtual 203	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   660: pop
    //   661: goto -283 -> 378
    //   664: astore 10
    //   666: aload 11
    //   668: ifnull +10 -> 678
    //   671: aload 11
    //   673: invokeinterface 126 1 0
    //   678: aload 10
    //   680: athrow
    //   681: astore 10
    //   683: aload 10
    //   685: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   688: goto -258 -> 430
    //   691: astore 10
    //   693: aload 10
    //   695: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   698: goto -121 -> 577
    //   701: astore 10
    //   703: aload 10
    //   705: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   708: goto -71 -> 637
    //   711: astore 11
    //   713: aload 11
    //   715: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   718: goto -40 -> 678
    //   721: astore 11
    //   723: aload 11
    //   725: invokestatic 216	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   728: goto -278 -> 450
    //   731: astore 10
    //   733: aconst_null
    //   734: astore 11
    //   736: goto -70 -> 666
    //   739: astore 12
    //   741: aload 10
    //   743: astore 11
    //   745: aload 12
    //   747: astore 10
    //   749: goto -83 -> 666
    //   752: astore 12
    //   754: aconst_null
    //   755: astore 10
    //   757: goto -151 -> 606
    //   760: astore 10
    //   762: aconst_null
    //   763: astore 10
    //   765: goto -206 -> 559
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	768	0	this	m
    //   211	125	1	i	int
    //   239	404	2	j	int
    //   49	255	3	l1	long
    //   224	85	5	l2	long
    //   184	94	7	l3	long
    //   316	191	9	bool	boolean
    //   20	626	10	localObject1	Object
    //   664	15	10	localObject2	Object
    //   681	3	10	localRuntimeException1	RuntimeException
    //   691	3	10	localRuntimeException2	RuntimeException
    //   701	3	10	localRuntimeException3	RuntimeException
    //   731	11	10	localObject3	Object
    //   747	9	10	localObject4	Object
    //   760	1	10	localSecurityException1	SecurityException
    //   763	1	10	localObject5	Object
    //   37	511	11	localObject6	Object
    //   557	1	11	localSecurityException2	SecurityException
    //   587	85	11	localObject7	Object
    //   711	3	11	localRuntimeException4	RuntimeException
    //   721	3	11	localJSONException1	JSONException
    //   734	10	11	localObject8	Object
    //   170	345	12	str	String
    //   543	7	12	localJSONException2	JSONException
    //   604	7	12	localRuntimeException5	RuntimeException
    //   739	7	12	localObject9	Object
    //   752	1	12	localRuntimeException6	RuntimeException
    //   8	630	13	localS	s
    //   29	416	14	localJSONArray	JSONArray
    //   251	399	15	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   257	268	543	org/json/JSONException
    //   272	287	543	org/json/JSONException
    //   298	308	543	org/json/JSONException
    //   322	333	543	org/json/JSONException
    //   347	358	543	org/json/JSONException
    //   366	378	543	org/json/JSONException
    //   382	390	543	org/json/JSONException
    //   394	400	543	org/json/JSONException
    //   528	540	543	org/json/JSONException
    //   589	601	543	org/json/JSONException
    //   649	661	543	org/json/JSONException
    //   131	141	557	java/lang/SecurityException
    //   145	158	557	java/lang/SecurityException
    //   162	172	557	java/lang/SecurityException
    //   176	186	557	java/lang/SecurityException
    //   190	199	557	java/lang/SecurityException
    //   203	212	557	java/lang/SecurityException
    //   216	226	557	java/lang/SecurityException
    //   230	240	557	java/lang/SecurityException
    //   244	253	557	java/lang/SecurityException
    //   257	268	557	java/lang/SecurityException
    //   272	287	557	java/lang/SecurityException
    //   298	308	557	java/lang/SecurityException
    //   322	333	557	java/lang/SecurityException
    //   347	358	557	java/lang/SecurityException
    //   366	378	557	java/lang/SecurityException
    //   382	390	557	java/lang/SecurityException
    //   394	400	557	java/lang/SecurityException
    //   404	413	557	java/lang/SecurityException
    //   528	540	557	java/lang/SecurityException
    //   549	554	557	java/lang/SecurityException
    //   589	601	557	java/lang/SecurityException
    //   649	661	557	java/lang/SecurityException
    //   131	141	604	java/lang/RuntimeException
    //   145	158	604	java/lang/RuntimeException
    //   162	172	604	java/lang/RuntimeException
    //   176	186	604	java/lang/RuntimeException
    //   190	199	604	java/lang/RuntimeException
    //   203	212	604	java/lang/RuntimeException
    //   216	226	604	java/lang/RuntimeException
    //   230	240	604	java/lang/RuntimeException
    //   244	253	604	java/lang/RuntimeException
    //   257	268	604	java/lang/RuntimeException
    //   272	287	604	java/lang/RuntimeException
    //   298	308	604	java/lang/RuntimeException
    //   322	333	604	java/lang/RuntimeException
    //   347	358	604	java/lang/RuntimeException
    //   366	378	604	java/lang/RuntimeException
    //   382	390	604	java/lang/RuntimeException
    //   394	400	604	java/lang/RuntimeException
    //   404	413	604	java/lang/RuntimeException
    //   528	540	604	java/lang/RuntimeException
    //   549	554	604	java/lang/RuntimeException
    //   589	601	604	java/lang/RuntimeException
    //   649	661	604	java/lang/RuntimeException
    //   131	141	664	finally
    //   145	158	664	finally
    //   162	172	664	finally
    //   176	186	664	finally
    //   190	199	664	finally
    //   203	212	664	finally
    //   216	226	664	finally
    //   230	240	664	finally
    //   244	253	664	finally
    //   257	268	664	finally
    //   272	287	664	finally
    //   298	308	664	finally
    //   322	333	664	finally
    //   347	358	664	finally
    //   366	378	664	finally
    //   382	390	664	finally
    //   394	400	664	finally
    //   404	413	664	finally
    //   528	540	664	finally
    //   549	554	664	finally
    //   589	601	664	finally
    //   610	615	664	finally
    //   619	625	664	finally
    //   649	661	664	finally
    //   423	430	681	java/lang/RuntimeException
    //   570	577	691	java/lang/RuntimeException
    //   630	637	701	java/lang/RuntimeException
    //   671	678	711	java/lang/RuntimeException
    //   439	450	721	org/json/JSONException
    //   31	122	731	finally
    //   559	565	739	finally
    //   31	122	752	java/lang/RuntimeException
    //   31	122	760	java/lang/SecurityException
  }
  
  private s f()
  {
    s localS = new s(this);
    Object localObject = new JSONArray();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      String str1 = x.a(f.a.getContext());
      String str2 = x.b(f.a.getContext());
      localJSONObject.put("phone", str1);
      localJSONObject.put("IMSI", str2);
      ((JSONArray)localObject).put(localJSONObject);
      localS.d = true;
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
        localS.a = ((UsageData)localObject);
        localS.c = a(5);
        return localS;
        localJSONException2 = localJSONException2;
        ThrowableExtension.printStackTrace(localJSONException2);
      }
      catch (JSONException localJSONException1)
      {
        for (;;)
        {
          ThrowableExtension.printStackTrace(localJSONException1);
        }
      }
    }
  }
  
  final s b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("error info value: " + paramInt);
    case 0: 
      return a();
    case 1: 
      return b();
    case 2: 
      return c();
    case 3: 
      return d();
    case 4: 
      return e();
    }
    return f();
  }
}
