package com.cootek.usage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class k
{
  a a;
  
  k(a paramA)
  {
    this.a = paramA;
  }
  
  /* Error */
  private f c()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/k$f
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 49	com/cootek/usage/k$f:<init>	(Lcom/cootek/usage/k;)V
    //   8: astore 10
    //   10: aload_0
    //   11: getfield 38	com/cootek/usage/k:a	Lcom/cootek/usage/a;
    //   14: invokevirtual 55	com/cootek/usage/a:getContext	()Landroid/content/Context;
    //   17: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 12
    //   22: new 63	java/util/Hashtable
    //   25: dup
    //   26: invokespecial 64	java/util/Hashtable:<init>	()V
    //   29: astore 11
    //   31: aconst_null
    //   32: astore 6
    //   34: aconst_null
    //   35: astore 7
    //   37: aconst_null
    //   38: astore_3
    //   39: aconst_null
    //   40: astore 8
    //   42: aconst_null
    //   43: astore 9
    //   45: aconst_null
    //   46: astore 5
    //   48: aload 12
    //   50: getstatic 70	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
    //   53: iconst_2
    //   54: anewarray 72	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: ldc 74
    //   61: aastore
    //   62: dup
    //   63: iconst_1
    //   64: ldc 76
    //   66: aastore
    //   67: aconst_null
    //   68: aconst_null
    //   69: aconst_null
    //   70: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   73: astore 4
    //   75: aload 4
    //   77: ifnull +165 -> 242
    //   80: aload 4
    //   82: invokeinterface 88 1 0
    //   87: ifeq +155 -> 242
    //   90: new 11	com/cootek/usage/k$b
    //   93: dup
    //   94: aload_0
    //   95: aconst_null
    //   96: invokespecial 91	com/cootek/usage/k$b:<init>	(Lcom/cootek/usage/k;Lcom/cootek/usage/k$1;)V
    //   99: astore_3
    //   100: aload_3
    //   101: aload 4
    //   103: iconst_0
    //   104: invokeinterface 95 2 0
    //   109: putfield 98	com/cootek/usage/k$b:a	J
    //   112: aload_3
    //   113: aload 4
    //   115: iconst_1
    //   116: invokeinterface 102 2 0
    //   121: putfield 105	com/cootek/usage/k$b:b	Ljava/lang/String;
    //   124: aload_3
    //   125: new 107	java/util/HashSet
    //   128: dup
    //   129: invokespecial 108	java/util/HashSet:<init>	()V
    //   132: putfield 111	com/cootek/usage/k$b:c	Ljava/util/HashSet;
    //   135: aload_3
    //   136: new 107	java/util/HashSet
    //   139: dup
    //   140: invokespecial 108	java/util/HashSet:<init>	()V
    //   143: putfield 113	com/cootek/usage/k$b:d	Ljava/util/HashSet;
    //   146: aload_3
    //   147: new 107	java/util/HashSet
    //   150: dup
    //   151: invokespecial 108	java/util/HashSet:<init>	()V
    //   154: putfield 115	com/cootek/usage/k$b:e	Ljava/util/HashSet;
    //   157: aload_3
    //   158: new 107	java/util/HashSet
    //   161: dup
    //   162: invokespecial 108	java/util/HashSet:<init>	()V
    //   165: putfield 117	com/cootek/usage/k$b:f	Ljava/util/HashSet;
    //   168: aload_3
    //   169: new 107	java/util/HashSet
    //   172: dup
    //   173: invokespecial 108	java/util/HashSet:<init>	()V
    //   176: putfield 119	com/cootek/usage/k$b:g	Ljava/util/HashSet;
    //   179: aload_3
    //   180: new 107	java/util/HashSet
    //   183: dup
    //   184: invokespecial 108	java/util/HashSet:<init>	()V
    //   187: putfield 121	com/cootek/usage/k$b:h	Ljava/util/HashSet;
    //   190: aload_3
    //   191: new 107	java/util/HashSet
    //   194: dup
    //   195: invokespecial 108	java/util/HashSet:<init>	()V
    //   198: putfield 124	com/cootek/usage/k$b:i	Ljava/util/HashSet;
    //   201: aload 11
    //   203: aload_3
    //   204: getfield 98	com/cootek/usage/k$b:a	J
    //   207: invokestatic 130	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   210: aload_3
    //   211: invokevirtual 134	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   214: pop
    //   215: aload 4
    //   217: invokeinterface 137 1 0
    //   222: istore_2
    //   223: iload_2
    //   224: ifne -134 -> 90
    //   227: goto +15 -> 242
    //   230: astore_3
    //   231: goto +2349 -> 2580
    //   234: astore 5
    //   236: goto +2272 -> 2508
    //   239: goto +2309 -> 2548
    //   242: aload 4
    //   244: ifnull +18 -> 262
    //   247: aload 4
    //   249: invokeinterface 140 1 0
    //   254: goto +8 -> 262
    //   257: astore_3
    //   258: aload_3
    //   259: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   262: aload 5
    //   264: astore_3
    //   265: aload 12
    //   267: getstatic 148	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   270: bipush 12
    //   272: anewarray 72	java/lang/String
    //   275: dup
    //   276: iconst_0
    //   277: ldc -106
    //   279: aastore
    //   280: dup
    //   281: iconst_1
    //   282: ldc -104
    //   284: aastore
    //   285: dup
    //   286: iconst_2
    //   287: ldc -102
    //   289: aastore
    //   290: dup
    //   291: iconst_3
    //   292: ldc -100
    //   294: aastore
    //   295: dup
    //   296: iconst_4
    //   297: ldc -98
    //   299: aastore
    //   300: dup
    //   301: iconst_5
    //   302: ldc -96
    //   304: aastore
    //   305: dup
    //   306: bipush 6
    //   308: ldc -94
    //   310: aastore
    //   311: dup
    //   312: bipush 7
    //   314: ldc -92
    //   316: aastore
    //   317: dup
    //   318: bipush 8
    //   320: ldc -90
    //   322: aastore
    //   323: dup
    //   324: bipush 9
    //   326: ldc -88
    //   328: aastore
    //   329: dup
    //   330: bipush 10
    //   332: ldc -86
    //   334: aastore
    //   335: dup
    //   336: bipush 11
    //   338: ldc -84
    //   340: aastore
    //   341: ldc -82
    //   343: bipush 7
    //   345: anewarray 72	java/lang/String
    //   348: dup
    //   349: iconst_0
    //   350: ldc -80
    //   352: aastore
    //   353: dup
    //   354: iconst_1
    //   355: ldc -78
    //   357: aastore
    //   358: dup
    //   359: iconst_2
    //   360: ldc -76
    //   362: aastore
    //   363: dup
    //   364: iconst_3
    //   365: ldc -74
    //   367: aastore
    //   368: dup
    //   369: iconst_4
    //   370: ldc -72
    //   372: aastore
    //   373: dup
    //   374: iconst_5
    //   375: ldc -70
    //   377: aastore
    //   378: dup
    //   379: bipush 6
    //   381: ldc -68
    //   383: aastore
    //   384: aconst_null
    //   385: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   388: astore 5
    //   390: aload 5
    //   392: ifnull +716 -> 1108
    //   395: aload 5
    //   397: invokeinterface 88 1 0
    //   402: istore_2
    //   403: iload_2
    //   404: ifeq +704 -> 1108
    //   407: aload 11
    //   409: aload 5
    //   411: iconst_0
    //   412: invokeinterface 95 2 0
    //   417: invokestatic 130	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   420: invokevirtual 192	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   423: checkcast 11	com/cootek/usage/k$b
    //   426: astore_3
    //   427: aload_3
    //   428: ifnonnull +6 -> 434
    //   431: goto +2197 -> 2628
    //   434: aload 5
    //   436: iconst_1
    //   437: invokeinterface 102 2 0
    //   442: astore 4
    //   444: ldc -80
    //   446: aload 4
    //   448: invokevirtual 196	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   451: istore_2
    //   452: iload_2
    //   453: ifeq +32 -> 485
    //   456: aload 5
    //   458: iconst_2
    //   459: invokeinterface 102 2 0
    //   464: astore 4
    //   466: aload_3
    //   467: getfield 111	com/cootek/usage/k$b:c	Ljava/util/HashSet;
    //   470: astore_3
    //   471: aload_3
    //   472: aload 4
    //   474: invokevirtual 199	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   477: pop
    //   478: goto +2150 -> 2628
    //   481: astore_3
    //   482: goto +585 -> 1067
    //   485: ldc -78
    //   487: aload 4
    //   489: invokevirtual 196	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   492: istore_2
    //   493: iload_2
    //   494: ifeq +73 -> 567
    //   497: new 14	com/cootek/usage/k$c
    //   500: dup
    //   501: aload_0
    //   502: aconst_null
    //   503: invokespecial 200	com/cootek/usage/k$c:<init>	(Lcom/cootek/usage/k;Lcom/cootek/usage/k$1;)V
    //   506: astore 4
    //   508: aload 4
    //   510: aload 5
    //   512: iconst_2
    //   513: invokeinterface 102 2 0
    //   518: putfield 202	com/cootek/usage/k$c:a	Ljava/lang/String;
    //   521: aload 4
    //   523: aload_0
    //   524: aload 5
    //   526: iconst_3
    //   527: invokeinterface 206 2 0
    //   532: invokespecial 208	com/cootek/usage/k:c	(I)Ljava/lang/String;
    //   535: putfield 209	com/cootek/usage/k$c:b	Ljava/lang/String;
    //   538: aload 4
    //   540: getfield 209	com/cootek/usage/k$c:b	Ljava/lang/String;
    //   543: ifnonnull +16 -> 559
    //   546: aload 4
    //   548: aload 5
    //   550: iconst_4
    //   551: invokeinterface 102 2 0
    //   556: putfield 209	com/cootek/usage/k$c:b	Ljava/lang/String;
    //   559: aload_3
    //   560: getfield 113	com/cootek/usage/k$b:d	Ljava/util/HashSet;
    //   563: astore_3
    //   564: goto -93 -> 471
    //   567: ldc -76
    //   569: aload 4
    //   571: invokevirtual 196	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   574: istore_2
    //   575: iload_2
    //   576: ifeq +108 -> 684
    //   579: new 26	com/cootek/usage/k$g
    //   582: dup
    //   583: aload_0
    //   584: aconst_null
    //   585: invokespecial 210	com/cootek/usage/k$g:<init>	(Lcom/cootek/usage/k;Lcom/cootek/usage/k$1;)V
    //   588: astore 4
    //   590: aload 4
    //   592: aload 5
    //   594: iconst_2
    //   595: invokeinterface 102 2 0
    //   600: putfield 211	com/cootek/usage/k$g:b	Ljava/lang/String;
    //   603: aload 4
    //   605: aload 5
    //   607: iconst_5
    //   608: invokeinterface 102 2 0
    //   613: putfield 213	com/cootek/usage/k$g:c	Ljava/lang/String;
    //   616: aload 5
    //   618: bipush 6
    //   620: invokeinterface 102 2 0
    //   625: astore 6
    //   627: aload 4
    //   629: aload 6
    //   631: putfield 215	com/cootek/usage/k$g:d	Ljava/lang/String;
    //   634: aload 4
    //   636: aload_0
    //   637: aload 5
    //   639: iconst_3
    //   640: invokeinterface 206 2 0
    //   645: invokespecial 217	com/cootek/usage/k:d	(I)Ljava/lang/String;
    //   648: putfield 218	com/cootek/usage/k$g:a	Ljava/lang/String;
    //   651: aload 4
    //   653: getfield 218	com/cootek/usage/k$g:a	Ljava/lang/String;
    //   656: ifnonnull +16 -> 672
    //   659: aload 4
    //   661: aload 5
    //   663: iconst_4
    //   664: invokeinterface 102 2 0
    //   669: putfield 218	com/cootek/usage/k$g:a	Ljava/lang/String;
    //   672: aload_3
    //   673: getfield 115	com/cootek/usage/k$b:e	Ljava/util/HashSet;
    //   676: astore_3
    //   677: goto -206 -> 471
    //   680: astore_3
    //   681: goto -199 -> 482
    //   684: ldc -74
    //   686: aload 4
    //   688: invokevirtual 196	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   691: ifeq +131 -> 822
    //   694: new 20	com/cootek/usage/k$e
    //   697: dup
    //   698: aload_0
    //   699: aconst_null
    //   700: invokespecial 219	com/cootek/usage/k$e:<init>	(Lcom/cootek/usage/k;Lcom/cootek/usage/k$1;)V
    //   703: astore 4
    //   705: aload 4
    //   707: aload 5
    //   709: iconst_2
    //   710: invokeinterface 102 2 0
    //   715: putfield 220	com/cootek/usage/k$e:a	Ljava/lang/String;
    //   718: aload 4
    //   720: aload_0
    //   721: aload 5
    //   723: iconst_3
    //   724: invokeinterface 206 2 0
    //   729: invokespecial 222	com/cootek/usage/k:e	(I)Ljava/lang/String;
    //   732: putfield 223	com/cootek/usage/k$e:b	Ljava/lang/String;
    //   735: aload 4
    //   737: getfield 223	com/cootek/usage/k$e:b	Ljava/lang/String;
    //   740: astore 6
    //   742: aload 6
    //   744: ifnonnull +16 -> 760
    //   747: aload 4
    //   749: aload 5
    //   751: iconst_4
    //   752: invokeinterface 102 2 0
    //   757: putfield 223	com/cootek/usage/k$e:b	Ljava/lang/String;
    //   760: aload 4
    //   762: aload_0
    //   763: aload 5
    //   765: bipush 6
    //   767: invokeinterface 206 2 0
    //   772: invokespecial 225	com/cootek/usage/k:g	(I)Ljava/lang/String;
    //   775: putfield 226	com/cootek/usage/k$e:c	Ljava/lang/String;
    //   778: aload 4
    //   780: getfield 226	com/cootek/usage/k$e:c	Ljava/lang/String;
    //   783: astore 6
    //   785: aload 6
    //   787: ifnonnull +1844 -> 2631
    //   790: aload 4
    //   792: aload 5
    //   794: bipush 7
    //   796: invokeinterface 102 2 0
    //   801: putfield 226	com/cootek/usage/k$e:c	Ljava/lang/String;
    //   804: goto +3 -> 807
    //   807: aload_3
    //   808: getfield 117	com/cootek/usage/k$b:f	Ljava/util/HashSet;
    //   811: astore_3
    //   812: aload_3
    //   813: aload 4
    //   815: invokevirtual 199	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   818: pop
    //   819: goto +252 -> 1071
    //   822: ldc -72
    //   824: aload 4
    //   826: invokevirtual 196	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   829: ifeq +73 -> 902
    //   832: new 8	com/cootek/usage/k$a
    //   835: dup
    //   836: aload_0
    //   837: aconst_null
    //   838: invokespecial 227	com/cootek/usage/k$a:<init>	(Lcom/cootek/usage/k;Lcom/cootek/usage/k$1;)V
    //   841: astore 4
    //   843: aload 4
    //   845: aload 5
    //   847: iconst_2
    //   848: invokeinterface 102 2 0
    //   853: putfield 228	com/cootek/usage/k$a:a	Ljava/lang/String;
    //   856: aload 4
    //   858: aload_0
    //   859: aload 5
    //   861: iconst_3
    //   862: invokeinterface 206 2 0
    //   867: invokespecial 230	com/cootek/usage/k:f	(I)Ljava/lang/String;
    //   870: putfield 231	com/cootek/usage/k$a:b	Ljava/lang/String;
    //   873: aload 4
    //   875: getfield 231	com/cootek/usage/k$a:b	Ljava/lang/String;
    //   878: ifnonnull +16 -> 894
    //   881: aload 4
    //   883: aload 5
    //   885: iconst_4
    //   886: invokeinterface 102 2 0
    //   891: putfield 231	com/cootek/usage/k$a:b	Ljava/lang/String;
    //   894: aload_3
    //   895: getfield 119	com/cootek/usage/k$b:g	Ljava/util/HashSet;
    //   898: astore_3
    //   899: goto -87 -> 812
    //   902: ldc -70
    //   904: aload 4
    //   906: invokevirtual 196	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   909: ifeq +73 -> 982
    //   912: new 17	com/cootek/usage/k$d
    //   915: dup
    //   916: aload_0
    //   917: aconst_null
    //   918: invokespecial 232	com/cootek/usage/k$d:<init>	(Lcom/cootek/usage/k;Lcom/cootek/usage/k$1;)V
    //   921: astore 4
    //   923: aload 4
    //   925: aload 5
    //   927: iconst_2
    //   928: invokeinterface 102 2 0
    //   933: putfield 233	com/cootek/usage/k$d:a	Ljava/lang/String;
    //   936: aload 4
    //   938: aload_0
    //   939: aload 5
    //   941: iconst_3
    //   942: invokeinterface 206 2 0
    //   947: invokespecial 235	com/cootek/usage/k:h	(I)Ljava/lang/String;
    //   950: putfield 236	com/cootek/usage/k$d:b	Ljava/lang/String;
    //   953: aload 4
    //   955: getfield 236	com/cootek/usage/k$d:b	Ljava/lang/String;
    //   958: ifnonnull +16 -> 974
    //   961: aload 4
    //   963: aload 5
    //   965: iconst_4
    //   966: invokeinterface 102 2 0
    //   971: putfield 236	com/cootek/usage/k$d:b	Ljava/lang/String;
    //   974: aload_3
    //   975: getfield 121	com/cootek/usage/k$b:h	Ljava/util/HashSet;
    //   978: astore_3
    //   979: goto -167 -> 812
    //   982: ldc -68
    //   984: aload 4
    //   986: invokevirtual 196	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   989: ifeq +82 -> 1071
    //   992: new 29	com/cootek/usage/k$h
    //   995: dup
    //   996: aload_0
    //   997: aconst_null
    //   998: invokespecial 237	com/cootek/usage/k$h:<init>	(Lcom/cootek/usage/k;Lcom/cootek/usage/k$1;)V
    //   1001: astore 4
    //   1003: aload 4
    //   1005: aload 5
    //   1007: iconst_2
    //   1008: invokeinterface 102 2 0
    //   1013: putfield 238	com/cootek/usage/k$h:a	Ljava/lang/String;
    //   1016: aload 4
    //   1018: aload_0
    //   1019: aload 5
    //   1021: iconst_3
    //   1022: invokeinterface 206 2 0
    //   1027: invokespecial 240	com/cootek/usage/k:i	(I)Ljava/lang/String;
    //   1030: putfield 241	com/cootek/usage/k$h:b	Ljava/lang/String;
    //   1033: aload 4
    //   1035: getfield 241	com/cootek/usage/k$h:b	Ljava/lang/String;
    //   1038: ifnonnull +16 -> 1054
    //   1041: aload 4
    //   1043: aload 5
    //   1045: iconst_4
    //   1046: invokeinterface 102 2 0
    //   1051: putfield 241	com/cootek/usage/k$h:b	Ljava/lang/String;
    //   1054: aload_3
    //   1055: getfield 124	com/cootek/usage/k$b:i	Ljava/util/HashSet;
    //   1058: astore_3
    //   1059: goto -247 -> 812
    //   1062: astore_3
    //   1063: goto +4 -> 1067
    //   1066: astore_3
    //   1067: aload_3
    //   1068: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   1071: aload 5
    //   1073: invokeinterface 137 1 0
    //   1078: istore_2
    //   1079: iload_2
    //   1080: ifne -673 -> 407
    //   1083: goto +25 -> 1108
    //   1086: astore_3
    //   1087: goto +1376 -> 2463
    //   1090: astore_3
    //   1091: aload 5
    //   1093: astore 4
    //   1095: aload_3
    //   1096: astore 5
    //   1098: goto +1293 -> 2391
    //   1101: aload 5
    //   1103: astore 4
    //   1105: goto +1326 -> 2431
    //   1108: aload 5
    //   1110: ifnull +18 -> 1128
    //   1113: aload 5
    //   1115: invokeinterface 140 1 0
    //   1120: goto +8 -> 1128
    //   1123: astore_3
    //   1124: aload_3
    //   1125: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   1128: new 243	org/json/JSONArray
    //   1131: dup
    //   1132: invokespecial 244	org/json/JSONArray:<init>	()V
    //   1135: astore_3
    //   1136: aload 11
    //   1138: invokevirtual 248	java/util/Hashtable:values	()Ljava/util/Collection;
    //   1141: invokeinterface 254 1 0
    //   1146: astore 4
    //   1148: aload 4
    //   1150: invokeinterface 259 1 0
    //   1155: ifeq +1078 -> 2233
    //   1158: aload 4
    //   1160: invokeinterface 263 1 0
    //   1165: checkcast 11	com/cootek/usage/k$b
    //   1168: astore 6
    //   1170: new 265	org/json/JSONObject
    //   1173: dup
    //   1174: invokespecial 266	org/json/JSONObject:<init>	()V
    //   1177: astore 5
    //   1179: aload 5
    //   1181: ldc_w 268
    //   1184: aload 6
    //   1186: getfield 105	com/cootek/usage/k$b:b	Ljava/lang/String;
    //   1189: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1192: pop
    //   1193: aload 6
    //   1195: getfield 111	com/cootek/usage/k$b:c	Ljava/util/HashSet;
    //   1198: invokevirtual 274	java/util/HashSet:isEmpty	()Z
    //   1201: ifne +70 -> 1271
    //   1204: new 243	org/json/JSONArray
    //   1207: dup
    //   1208: invokespecial 244	org/json/JSONArray:<init>	()V
    //   1211: astore 7
    //   1213: aload 6
    //   1215: getfield 111	com/cootek/usage/k$b:c	Ljava/util/HashSet;
    //   1218: invokevirtual 275	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1221: astore 8
    //   1223: aload 8
    //   1225: invokeinterface 259 1 0
    //   1230: ifeq +22 -> 1252
    //   1233: aload 7
    //   1235: aload 8
    //   1237: invokeinterface 263 1 0
    //   1242: checkcast 72	java/lang/String
    //   1245: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1248: pop
    //   1249: goto -26 -> 1223
    //   1252: aload 7
    //   1254: invokevirtual 282	org/json/JSONArray:length	()I
    //   1257: ifle +14 -> 1271
    //   1260: aload 5
    //   1262: ldc_w 284
    //   1265: aload 7
    //   1267: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1270: pop
    //   1271: aload 6
    //   1273: getfield 113	com/cootek/usage/k$b:d	Ljava/util/HashSet;
    //   1276: invokevirtual 274	java/util/HashSet:isEmpty	()Z
    //   1279: ifne +133 -> 1412
    //   1282: new 243	org/json/JSONArray
    //   1285: dup
    //   1286: invokespecial 244	org/json/JSONArray:<init>	()V
    //   1289: astore 7
    //   1291: aload 6
    //   1293: getfield 113	com/cootek/usage/k$b:d	Ljava/util/HashSet;
    //   1296: invokevirtual 275	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1299: astore 8
    //   1301: aload 8
    //   1303: invokeinterface 259 1 0
    //   1308: ifeq +85 -> 1393
    //   1311: aload 8
    //   1313: invokeinterface 263 1 0
    //   1318: checkcast 14	com/cootek/usage/k$c
    //   1321: astore 9
    //   1323: new 265	org/json/JSONObject
    //   1326: dup
    //   1327: invokespecial 266	org/json/JSONObject:<init>	()V
    //   1330: astore 11
    //   1332: aload 9
    //   1334: getfield 202	com/cootek/usage/k$c:a	Ljava/lang/String;
    //   1337: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1340: ifne -39 -> 1301
    //   1343: aload 11
    //   1345: ldc_w 291
    //   1348: aload 9
    //   1350: getfield 202	com/cootek/usage/k$c:a	Ljava/lang/String;
    //   1353: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1356: pop
    //   1357: aload 9
    //   1359: getfield 209	com/cootek/usage/k$c:b	Ljava/lang/String;
    //   1362: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1365: ifne +17 -> 1382
    //   1368: aload 11
    //   1370: ldc_w 293
    //   1373: aload 9
    //   1375: getfield 209	com/cootek/usage/k$c:b	Ljava/lang/String;
    //   1378: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1381: pop
    //   1382: aload 7
    //   1384: aload 11
    //   1386: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1389: pop
    //   1390: goto -89 -> 1301
    //   1393: aload 7
    //   1395: invokevirtual 282	org/json/JSONArray:length	()I
    //   1398: ifle +14 -> 1412
    //   1401: aload 5
    //   1403: ldc_w 295
    //   1406: aload 7
    //   1408: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1411: pop
    //   1412: aload 6
    //   1414: getfield 115	com/cootek/usage/k$b:e	Ljava/util/HashSet;
    //   1417: invokevirtual 274	java/util/HashSet:isEmpty	()Z
    //   1420: ifne +198 -> 1618
    //   1423: new 243	org/json/JSONArray
    //   1426: dup
    //   1427: invokespecial 244	org/json/JSONArray:<init>	()V
    //   1430: astore 7
    //   1432: aload 6
    //   1434: getfield 115	com/cootek/usage/k$b:e	Ljava/util/HashSet;
    //   1437: invokevirtual 275	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1440: astore 8
    //   1442: aload 8
    //   1444: invokeinterface 259 1 0
    //   1449: ifeq +150 -> 1599
    //   1452: aload 8
    //   1454: invokeinterface 263 1 0
    //   1459: checkcast 26	com/cootek/usage/k$g
    //   1462: astore 9
    //   1464: new 265	org/json/JSONObject
    //   1467: dup
    //   1468: invokespecial 266	org/json/JSONObject:<init>	()V
    //   1471: astore 11
    //   1473: aload 9
    //   1475: getfield 218	com/cootek/usage/k$g:a	Ljava/lang/String;
    //   1478: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1481: ifne +1157 -> 2638
    //   1484: aload 11
    //   1486: ldc_w 293
    //   1489: aload 9
    //   1491: getfield 218	com/cootek/usage/k$g:a	Ljava/lang/String;
    //   1494: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1497: pop
    //   1498: iconst_1
    //   1499: istore_1
    //   1500: goto +3 -> 1503
    //   1503: aload 9
    //   1505: getfield 218	com/cootek/usage/k$g:a	Ljava/lang/String;
    //   1508: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1511: ifne +19 -> 1530
    //   1514: aload 11
    //   1516: ldc_w 297
    //   1519: aload 9
    //   1521: getfield 211	com/cootek/usage/k$g:b	Ljava/lang/String;
    //   1524: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1527: pop
    //   1528: iconst_1
    //   1529: istore_1
    //   1530: aload 9
    //   1532: getfield 218	com/cootek/usage/k$g:a	Ljava/lang/String;
    //   1535: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1538: ifne +19 -> 1557
    //   1541: aload 11
    //   1543: ldc_w 299
    //   1546: aload 9
    //   1548: getfield 213	com/cootek/usage/k$g:c	Ljava/lang/String;
    //   1551: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1554: pop
    //   1555: iconst_1
    //   1556: istore_1
    //   1557: aload 9
    //   1559: getfield 218	com/cootek/usage/k$g:a	Ljava/lang/String;
    //   1562: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1565: ifne +19 -> 1584
    //   1568: aload 11
    //   1570: ldc_w 301
    //   1573: aload 9
    //   1575: getfield 215	com/cootek/usage/k$g:d	Ljava/lang/String;
    //   1578: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1581: pop
    //   1582: iconst_1
    //   1583: istore_1
    //   1584: iload_1
    //   1585: ifeq -143 -> 1442
    //   1588: aload 7
    //   1590: aload 11
    //   1592: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1595: pop
    //   1596: goto -154 -> 1442
    //   1599: aload 7
    //   1601: invokevirtual 282	org/json/JSONArray:length	()I
    //   1604: ifle +14 -> 1618
    //   1607: aload 5
    //   1609: ldc_w 303
    //   1612: aload 7
    //   1614: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1617: pop
    //   1618: aload 6
    //   1620: getfield 117	com/cootek/usage/k$b:f	Ljava/util/HashSet;
    //   1623: invokevirtual 274	java/util/HashSet:isEmpty	()Z
    //   1626: ifne +158 -> 1784
    //   1629: new 243	org/json/JSONArray
    //   1632: dup
    //   1633: invokespecial 244	org/json/JSONArray:<init>	()V
    //   1636: astore 7
    //   1638: aload 6
    //   1640: getfield 117	com/cootek/usage/k$b:f	Ljava/util/HashSet;
    //   1643: invokevirtual 275	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1646: astore 8
    //   1648: aload 8
    //   1650: invokeinterface 259 1 0
    //   1655: ifeq +118 -> 1773
    //   1658: aload 8
    //   1660: invokeinterface 263 1 0
    //   1665: checkcast 20	com/cootek/usage/k$e
    //   1668: astore 9
    //   1670: new 265	org/json/JSONObject
    //   1673: dup
    //   1674: invokespecial 266	org/json/JSONObject:<init>	()V
    //   1677: astore 11
    //   1679: aload 9
    //   1681: getfield 220	com/cootek/usage/k$e:a	Ljava/lang/String;
    //   1684: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1687: ifne -39 -> 1648
    //   1690: aload 11
    //   1692: ldc_w 305
    //   1695: aload 9
    //   1697: getfield 220	com/cootek/usage/k$e:a	Ljava/lang/String;
    //   1700: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1703: pop
    //   1704: aload 9
    //   1706: getfield 223	com/cootek/usage/k$e:b	Ljava/lang/String;
    //   1709: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1712: ifne +17 -> 1729
    //   1715: aload 11
    //   1717: ldc_w 293
    //   1720: aload 9
    //   1722: getfield 223	com/cootek/usage/k$e:b	Ljava/lang/String;
    //   1725: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1728: pop
    //   1729: aload 9
    //   1731: getfield 226	com/cootek/usage/k$e:c	Ljava/lang/String;
    //   1734: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1737: ifne +17 -> 1754
    //   1740: aload 11
    //   1742: ldc_w 307
    //   1745: aload 9
    //   1747: getfield 226	com/cootek/usage/k$e:c	Ljava/lang/String;
    //   1750: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1753: pop
    //   1754: aload 7
    //   1756: invokevirtual 282	org/json/JSONArray:length	()I
    //   1759: ifle -111 -> 1648
    //   1762: aload 7
    //   1764: aload 11
    //   1766: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1769: pop
    //   1770: goto -122 -> 1648
    //   1773: aload 5
    //   1775: ldc_w 309
    //   1778: aload 7
    //   1780: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1783: pop
    //   1784: aload 6
    //   1786: getfield 119	com/cootek/usage/k$b:g	Ljava/util/HashSet;
    //   1789: invokevirtual 274	java/util/HashSet:isEmpty	()Z
    //   1792: ifne +133 -> 1925
    //   1795: new 243	org/json/JSONArray
    //   1798: dup
    //   1799: invokespecial 244	org/json/JSONArray:<init>	()V
    //   1802: astore 7
    //   1804: aload 6
    //   1806: getfield 119	com/cootek/usage/k$b:g	Ljava/util/HashSet;
    //   1809: invokevirtual 275	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1812: astore 8
    //   1814: aload 8
    //   1816: invokeinterface 259 1 0
    //   1821: ifeq +85 -> 1906
    //   1824: aload 8
    //   1826: invokeinterface 263 1 0
    //   1831: checkcast 8	com/cootek/usage/k$a
    //   1834: astore 9
    //   1836: new 265	org/json/JSONObject
    //   1839: dup
    //   1840: invokespecial 266	org/json/JSONObject:<init>	()V
    //   1843: astore 11
    //   1845: aload 9
    //   1847: getfield 228	com/cootek/usage/k$a:a	Ljava/lang/String;
    //   1850: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1853: ifne -39 -> 1814
    //   1856: aload 11
    //   1858: ldc_w 311
    //   1861: aload 9
    //   1863: getfield 228	com/cootek/usage/k$a:a	Ljava/lang/String;
    //   1866: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1869: pop
    //   1870: aload 9
    //   1872: getfield 231	com/cootek/usage/k$a:b	Ljava/lang/String;
    //   1875: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1878: ifne +17 -> 1895
    //   1881: aload 11
    //   1883: ldc_w 293
    //   1886: aload 9
    //   1888: getfield 231	com/cootek/usage/k$a:b	Ljava/lang/String;
    //   1891: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1894: pop
    //   1895: aload 7
    //   1897: aload 11
    //   1899: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1902: pop
    //   1903: goto -89 -> 1814
    //   1906: aload 7
    //   1908: invokevirtual 282	org/json/JSONArray:length	()I
    //   1911: ifle +14 -> 1925
    //   1914: aload 5
    //   1916: ldc_w 291
    //   1919: aload 7
    //   1921: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1924: pop
    //   1925: aload 6
    //   1927: getfield 121	com/cootek/usage/k$b:h	Ljava/util/HashSet;
    //   1930: invokevirtual 274	java/util/HashSet:isEmpty	()Z
    //   1933: ifne +133 -> 2066
    //   1936: new 243	org/json/JSONArray
    //   1939: dup
    //   1940: invokespecial 244	org/json/JSONArray:<init>	()V
    //   1943: astore 7
    //   1945: aload 6
    //   1947: getfield 121	com/cootek/usage/k$b:h	Ljava/util/HashSet;
    //   1950: invokevirtual 275	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1953: astore 8
    //   1955: aload 8
    //   1957: invokeinterface 259 1 0
    //   1962: ifeq +85 -> 2047
    //   1965: aload 8
    //   1967: invokeinterface 263 1 0
    //   1972: checkcast 17	com/cootek/usage/k$d
    //   1975: astore 9
    //   1977: new 265	org/json/JSONObject
    //   1980: dup
    //   1981: invokespecial 266	org/json/JSONObject:<init>	()V
    //   1984: astore 11
    //   1986: aload 9
    //   1988: getfield 233	com/cootek/usage/k$d:a	Ljava/lang/String;
    //   1991: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1994: ifne -39 -> 1955
    //   1997: aload 11
    //   1999: ldc_w 313
    //   2002: aload 9
    //   2004: getfield 233	com/cootek/usage/k$d:a	Ljava/lang/String;
    //   2007: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2010: pop
    //   2011: aload 9
    //   2013: getfield 236	com/cootek/usage/k$d:b	Ljava/lang/String;
    //   2016: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2019: ifne +17 -> 2036
    //   2022: aload 11
    //   2024: ldc_w 293
    //   2027: aload 9
    //   2029: getfield 236	com/cootek/usage/k$d:b	Ljava/lang/String;
    //   2032: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2035: pop
    //   2036: aload 7
    //   2038: aload 11
    //   2040: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2043: pop
    //   2044: goto -89 -> 1955
    //   2047: aload 7
    //   2049: invokevirtual 282	org/json/JSONArray:length	()I
    //   2052: ifle +14 -> 2066
    //   2055: aload 5
    //   2057: ldc_w 315
    //   2060: aload 7
    //   2062: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2065: pop
    //   2066: aload 6
    //   2068: getfield 124	com/cootek/usage/k$b:i	Ljava/util/HashSet;
    //   2071: invokevirtual 274	java/util/HashSet:isEmpty	()Z
    //   2074: ifne +143 -> 2217
    //   2077: new 243	org/json/JSONArray
    //   2080: dup
    //   2081: invokespecial 244	org/json/JSONArray:<init>	()V
    //   2084: astore 7
    //   2086: aload 6
    //   2088: getfield 124	com/cootek/usage/k$b:i	Ljava/util/HashSet;
    //   2091: invokevirtual 275	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2094: astore 6
    //   2096: aload 6
    //   2098: invokeinterface 259 1 0
    //   2103: ifeq +85 -> 2188
    //   2106: aload 6
    //   2108: invokeinterface 263 1 0
    //   2113: checkcast 29	com/cootek/usage/k$h
    //   2116: astore 8
    //   2118: new 265	org/json/JSONObject
    //   2121: dup
    //   2122: invokespecial 266	org/json/JSONObject:<init>	()V
    //   2125: astore 9
    //   2127: aload 8
    //   2129: getfield 238	com/cootek/usage/k$h:a	Ljava/lang/String;
    //   2132: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2135: ifne -39 -> 2096
    //   2138: aload 9
    //   2140: ldc_w 268
    //   2143: aload 8
    //   2145: getfield 238	com/cootek/usage/k$h:a	Ljava/lang/String;
    //   2148: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2151: pop
    //   2152: aload 8
    //   2154: getfield 241	com/cootek/usage/k$h:b	Ljava/lang/String;
    //   2157: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2160: ifne +17 -> 2177
    //   2163: aload 9
    //   2165: ldc_w 293
    //   2168: aload 8
    //   2170: getfield 241	com/cootek/usage/k$h:b	Ljava/lang/String;
    //   2173: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2176: pop
    //   2177: aload 7
    //   2179: aload 9
    //   2181: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2184: pop
    //   2185: goto -89 -> 2096
    //   2188: aload 7
    //   2190: invokevirtual 282	org/json/JSONArray:length	()I
    //   2193: ifle +24 -> 2217
    //   2196: aload 5
    //   2198: ldc_w 317
    //   2201: aload 7
    //   2203: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2206: pop
    //   2207: goto +10 -> 2217
    //   2210: astore 6
    //   2212: aload 6
    //   2214: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2217: aload_3
    //   2218: aload 5
    //   2220: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2223: pop
    //   2224: aload 10
    //   2226: iconst_1
    //   2227: putfield 320	com/cootek/usage/k$f:d	Z
    //   2230: goto -1082 -> 1148
    //   2233: aload_0
    //   2234: getfield 38	com/cootek/usage/k:a	Lcom/cootek/usage/a;
    //   2237: invokevirtual 324	com/cootek/usage/a:getPhoneAccount	()Ljava/lang/String;
    //   2240: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2243: ifne +74 -> 2317
    //   2246: new 265	org/json/JSONObject
    //   2249: dup
    //   2250: invokespecial 266	org/json/JSONObject:<init>	()V
    //   2253: astore 4
    //   2255: aload 4
    //   2257: ldc_w 268
    //   2260: ldc_w 326
    //   2263: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2266: pop
    //   2267: new 243	org/json/JSONArray
    //   2270: dup
    //   2271: invokespecial 244	org/json/JSONArray:<init>	()V
    //   2274: astore 5
    //   2276: aload 5
    //   2278: aload_0
    //   2279: getfield 38	com/cootek/usage/k:a	Lcom/cootek/usage/a;
    //   2282: invokevirtual 324	com/cootek/usage/a:getPhoneAccount	()Ljava/lang/String;
    //   2285: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2288: pop
    //   2289: aload 4
    //   2291: ldc_w 284
    //   2294: aload 5
    //   2296: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2299: pop
    //   2300: goto +10 -> 2310
    //   2303: astore 5
    //   2305: aload 5
    //   2307: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2310: aload_3
    //   2311: aload 4
    //   2313: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2316: pop
    //   2317: new 328	com/cootek/usage/UsageData
    //   2320: dup
    //   2321: invokespecial 329	com/cootek/usage/UsageData:<init>	()V
    //   2324: astore 4
    //   2326: aload 4
    //   2328: aload_0
    //   2329: invokevirtual 331	com/cootek/usage/k:b	()Ljava/lang/String;
    //   2332: putfield 333	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   2335: aload 4
    //   2337: aload_0
    //   2338: iconst_0
    //   2339: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   2342: putfield 338	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   2345: aload 4
    //   2347: aload_3
    //   2348: invokevirtual 341	org/json/JSONArray:toString	()Ljava/lang/String;
    //   2351: putfield 344	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   2354: aload 10
    //   2356: aload 4
    //   2358: putfield 347	com/cootek/usage/k$f:a	Lcom/cootek/usage/UsageData;
    //   2361: aload 10
    //   2363: aload_0
    //   2364: iconst_0
    //   2365: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   2368: putfield 348	com/cootek/usage/k$f:c	Ljava/lang/String;
    //   2371: aload 10
    //   2373: areturn
    //   2374: astore 4
    //   2376: aload_3
    //   2377: astore 5
    //   2379: aload 4
    //   2381: astore_3
    //   2382: goto +81 -> 2463
    //   2385: astore 5
    //   2387: aload 6
    //   2389: astore 4
    //   2391: aload 4
    //   2393: astore_3
    //   2394: aload 5
    //   2396: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2399: aload 4
    //   2401: astore_3
    //   2402: aload 10
    //   2404: iconst_0
    //   2405: putfield 320	com/cootek/usage/k$f:d	Z
    //   2408: aload 4
    //   2410: ifnull +18 -> 2428
    //   2413: aload 4
    //   2415: invokeinterface 140 1 0
    //   2420: aload 10
    //   2422: areturn
    //   2423: astore_3
    //   2424: aload_3
    //   2425: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2428: aload 10
    //   2430: areturn
    //   2431: aload 4
    //   2433: astore_3
    //   2434: aload 10
    //   2436: iconst_0
    //   2437: putfield 320	com/cootek/usage/k$f:d	Z
    //   2440: aload 4
    //   2442: ifnull +18 -> 2460
    //   2445: aload 4
    //   2447: invokeinterface 140 1 0
    //   2452: aload 10
    //   2454: areturn
    //   2455: astore_3
    //   2456: aload_3
    //   2457: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2460: aload 10
    //   2462: areturn
    //   2463: aload 5
    //   2465: ifnull +20 -> 2485
    //   2468: aload 5
    //   2470: invokeinterface 140 1 0
    //   2475: goto +10 -> 2485
    //   2478: astore 4
    //   2480: aload 4
    //   2482: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2485: aload_3
    //   2486: athrow
    //   2487: astore 4
    //   2489: aload_3
    //   2490: astore 5
    //   2492: aload 4
    //   2494: astore_3
    //   2495: aload 5
    //   2497: astore 4
    //   2499: goto +81 -> 2580
    //   2502: astore 5
    //   2504: aload 8
    //   2506: astore 4
    //   2508: aload 4
    //   2510: astore_3
    //   2511: aload 5
    //   2513: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2516: aload 4
    //   2518: astore_3
    //   2519: aload 10
    //   2521: iconst_0
    //   2522: putfield 320	com/cootek/usage/k$f:d	Z
    //   2525: aload 4
    //   2527: ifnull +18 -> 2545
    //   2530: aload 4
    //   2532: invokeinterface 140 1 0
    //   2537: aload 10
    //   2539: areturn
    //   2540: astore_3
    //   2541: aload_3
    //   2542: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2545: aload 10
    //   2547: areturn
    //   2548: aload 4
    //   2550: astore_3
    //   2551: aload 10
    //   2553: iconst_0
    //   2554: putfield 320	com/cootek/usage/k$f:d	Z
    //   2557: aload 4
    //   2559: ifnull +18 -> 2577
    //   2562: aload 4
    //   2564: invokeinterface 140 1 0
    //   2569: aload 10
    //   2571: areturn
    //   2572: astore_3
    //   2573: aload_3
    //   2574: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2577: aload 10
    //   2579: areturn
    //   2580: aload 4
    //   2582: ifnull +20 -> 2602
    //   2585: aload 4
    //   2587: invokeinterface 140 1 0
    //   2592: goto +10 -> 2602
    //   2595: astore 4
    //   2597: aload 4
    //   2599: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2602: aload_3
    //   2603: athrow
    //   2604: astore_3
    //   2605: aload 9
    //   2607: astore 4
    //   2609: goto -61 -> 2548
    //   2612: astore_3
    //   2613: goto -2374 -> 239
    //   2616: astore_3
    //   2617: aload 7
    //   2619: astore 4
    //   2621: goto -190 -> 2431
    //   2624: astore_3
    //   2625: goto -1524 -> 1101
    //   2628: goto -1557 -> 1071
    //   2631: goto -1824 -> 807
    //   2634: astore_3
    //   2635: goto -1568 -> 1067
    //   2638: iconst_0
    //   2639: istore_1
    //   2640: goto -1137 -> 1503
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2643	0	this	k
    //   1499	1141	1	i	int
    //   222	858	2	bool	boolean
    //   38	173	3	localB	b
    //   230	1	3	localObject1	Object
    //   257	2	3	localRuntimeException1	RuntimeException
    //   264	208	3	localObject2	Object
    //   481	79	3	localRuntimeException2	RuntimeException
    //   563	114	3	localHashSet1	HashSet
    //   680	128	3	localRuntimeException3	RuntimeException
    //   811	248	3	localHashSet2	HashSet
    //   1062	1	3	localRuntimeException4	RuntimeException
    //   1066	2	3	localRuntimeException5	RuntimeException
    //   1086	1	3	localObject3	Object
    //   1090	6	3	localRuntimeException6	RuntimeException
    //   1123	2	3	localRuntimeException7	RuntimeException
    //   1135	1267	3	localObject4	Object
    //   2423	2	3	localRuntimeException8	RuntimeException
    //   2433	1	3	localObject5	Object
    //   2455	35	3	localRuntimeException9	RuntimeException
    //   2494	25	3	localObject6	Object
    //   2540	2	3	localRuntimeException10	RuntimeException
    //   2550	1	3	localObject7	Object
    //   2572	31	3	localRuntimeException11	RuntimeException
    //   2604	1	3	localSecurityException1	SecurityException
    //   2612	1	3	localSecurityException2	SecurityException
    //   2616	1	3	localSecurityException3	SecurityException
    //   2624	1	3	localSecurityException4	SecurityException
    //   2634	1	3	localRuntimeException12	RuntimeException
    //   73	2284	4	localObject8	Object
    //   2374	6	4	localObject9	Object
    //   2389	57	4	localJSONException1	JSONException
    //   2478	3	4	localRuntimeException13	RuntimeException
    //   2487	6	4	localObject10	Object
    //   2497	89	4	localObject11	Object
    //   2595	3	4	localRuntimeException14	RuntimeException
    //   2607	13	4	localObject12	Object
    //   46	1	5	localObject13	Object
    //   234	29	5	localRuntimeException15	RuntimeException
    //   388	1907	5	localObject14	Object
    //   2303	3	5	localJSONException2	JSONException
    //   2377	1	5	localObject15	Object
    //   2385	84	5	localRuntimeException16	RuntimeException
    //   2490	6	5	localRuntimeException17	RuntimeException
    //   2502	10	5	localRuntimeException18	RuntimeException
    //   32	2075	6	localObject16	Object
    //   2210	178	6	localJSONException3	JSONException
    //   35	2583	7	localJSONArray	JSONArray
    //   40	2465	8	localObject17	Object
    //   43	2563	9	localObject18	Object
    //   8	2570	10	localF	f
    //   29	2010	11	localObject19	Object
    //   20	246	12	localContentResolver	android.content.ContentResolver
    // Exception table:
    //   from	to	target	type
    //   80	90	230	finally
    //   90	223	230	finally
    //   80	90	234	java/lang/RuntimeException
    //   90	223	234	java/lang/RuntimeException
    //   247	254	257	java/lang/RuntimeException
    //   456	471	481	java/lang/RuntimeException
    //   471	478	481	java/lang/RuntimeException
    //   497	559	481	java/lang/RuntimeException
    //   559	564	481	java/lang/RuntimeException
    //   579	616	481	java/lang/RuntimeException
    //   627	672	481	java/lang/RuntimeException
    //   672	677	481	java/lang/RuntimeException
    //   747	760	481	java/lang/RuntimeException
    //   616	627	680	java/lang/RuntimeException
    //   790	804	1062	java/lang/RuntimeException
    //   807	812	1062	java/lang/RuntimeException
    //   812	819	1062	java/lang/RuntimeException
    //   822	894	1062	java/lang/RuntimeException
    //   894	899	1062	java/lang/RuntimeException
    //   902	974	1062	java/lang/RuntimeException
    //   974	979	1062	java/lang/RuntimeException
    //   982	1054	1062	java/lang/RuntimeException
    //   1054	1059	1062	java/lang/RuntimeException
    //   407	427	1066	java/lang/RuntimeException
    //   434	452	1066	java/lang/RuntimeException
    //   485	493	1066	java/lang/RuntimeException
    //   567	575	1066	java/lang/RuntimeException
    //   684	742	1066	java/lang/RuntimeException
    //   395	403	1086	finally
    //   407	427	1086	finally
    //   434	452	1086	finally
    //   456	471	1086	finally
    //   471	478	1086	finally
    //   485	493	1086	finally
    //   497	559	1086	finally
    //   559	564	1086	finally
    //   567	575	1086	finally
    //   579	616	1086	finally
    //   616	627	1086	finally
    //   627	672	1086	finally
    //   672	677	1086	finally
    //   684	742	1086	finally
    //   747	760	1086	finally
    //   760	785	1086	finally
    //   790	804	1086	finally
    //   807	812	1086	finally
    //   812	819	1086	finally
    //   822	894	1086	finally
    //   894	899	1086	finally
    //   902	974	1086	finally
    //   974	979	1086	finally
    //   982	1054	1086	finally
    //   1054	1059	1086	finally
    //   1067	1071	1086	finally
    //   1071	1079	1086	finally
    //   395	403	1090	java/lang/RuntimeException
    //   1067	1071	1090	java/lang/RuntimeException
    //   1071	1079	1090	java/lang/RuntimeException
    //   1113	1120	1123	java/lang/RuntimeException
    //   1179	1223	2210	org/json/JSONException
    //   1223	1249	2210	org/json/JSONException
    //   1252	1271	2210	org/json/JSONException
    //   1271	1301	2210	org/json/JSONException
    //   1301	1382	2210	org/json/JSONException
    //   1382	1390	2210	org/json/JSONException
    //   1393	1412	2210	org/json/JSONException
    //   1412	1442	2210	org/json/JSONException
    //   1442	1498	2210	org/json/JSONException
    //   1503	1528	2210	org/json/JSONException
    //   1530	1555	2210	org/json/JSONException
    //   1557	1582	2210	org/json/JSONException
    //   1588	1596	2210	org/json/JSONException
    //   1599	1618	2210	org/json/JSONException
    //   1618	1648	2210	org/json/JSONException
    //   1648	1729	2210	org/json/JSONException
    //   1729	1754	2210	org/json/JSONException
    //   1754	1770	2210	org/json/JSONException
    //   1773	1784	2210	org/json/JSONException
    //   1784	1814	2210	org/json/JSONException
    //   1814	1895	2210	org/json/JSONException
    //   1895	1903	2210	org/json/JSONException
    //   1906	1925	2210	org/json/JSONException
    //   1925	1955	2210	org/json/JSONException
    //   1955	2036	2210	org/json/JSONException
    //   2036	2044	2210	org/json/JSONException
    //   2047	2066	2210	org/json/JSONException
    //   2066	2096	2210	org/json/JSONException
    //   2096	2177	2210	org/json/JSONException
    //   2177	2185	2210	org/json/JSONException
    //   2188	2207	2210	org/json/JSONException
    //   2255	2300	2303	org/json/JSONException
    //   265	390	2374	finally
    //   2394	2399	2374	finally
    //   2402	2408	2374	finally
    //   2434	2440	2374	finally
    //   265	390	2385	java/lang/RuntimeException
    //   2413	2420	2423	java/lang/RuntimeException
    //   2445	2452	2455	java/lang/RuntimeException
    //   2468	2475	2478	java/lang/RuntimeException
    //   48	75	2487	finally
    //   2511	2516	2487	finally
    //   2519	2525	2487	finally
    //   2551	2557	2487	finally
    //   48	75	2502	java/lang/RuntimeException
    //   2530	2537	2540	java/lang/RuntimeException
    //   2562	2569	2572	java/lang/RuntimeException
    //   2585	2592	2595	java/lang/RuntimeException
    //   48	75	2604	java/lang/SecurityException
    //   80	90	2612	java/lang/SecurityException
    //   90	223	2612	java/lang/SecurityException
    //   265	390	2616	java/lang/SecurityException
    //   395	403	2624	java/lang/SecurityException
    //   407	427	2624	java/lang/SecurityException
    //   434	452	2624	java/lang/SecurityException
    //   456	471	2624	java/lang/SecurityException
    //   471	478	2624	java/lang/SecurityException
    //   485	493	2624	java/lang/SecurityException
    //   497	559	2624	java/lang/SecurityException
    //   559	564	2624	java/lang/SecurityException
    //   567	575	2624	java/lang/SecurityException
    //   579	616	2624	java/lang/SecurityException
    //   616	627	2624	java/lang/SecurityException
    //   627	672	2624	java/lang/SecurityException
    //   672	677	2624	java/lang/SecurityException
    //   684	742	2624	java/lang/SecurityException
    //   747	760	2624	java/lang/SecurityException
    //   760	785	2624	java/lang/SecurityException
    //   790	804	2624	java/lang/SecurityException
    //   807	812	2624	java/lang/SecurityException
    //   812	819	2624	java/lang/SecurityException
    //   822	894	2624	java/lang/SecurityException
    //   894	899	2624	java/lang/SecurityException
    //   902	974	2624	java/lang/SecurityException
    //   974	979	2624	java/lang/SecurityException
    //   982	1054	2624	java/lang/SecurityException
    //   1054	1059	2624	java/lang/SecurityException
    //   1067	1071	2624	java/lang/SecurityException
    //   1071	1079	2624	java/lang/SecurityException
    //   760	785	2634	java/lang/RuntimeException
  }
  
  private String c(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 4: 
      return "MOBILE";
    case 3: 
      return "OTHER";
    case 2: 
      return "WORK";
    case 1: 
      return "HOME";
    }
    return null;
  }
  
  /* Error */
  private f d()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/k$f
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 49	com/cootek/usage/k$f:<init>	(Lcom/cootek/usage/k;)V
    //   8: astore 11
    //   10: aload_0
    //   11: getfield 38	com/cootek/usage/k:a	Lcom/cootek/usage/a;
    //   14: invokevirtual 55	com/cootek/usage/a:getContext	()Landroid/content/Context;
    //   17: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 13
    //   22: new 243	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 244	org/json/JSONArray:<init>	()V
    //   29: astore 12
    //   31: aconst_null
    //   32: astore 10
    //   34: aconst_null
    //   35: astore 9
    //   37: aconst_null
    //   38: astore 8
    //   40: aload 8
    //   42: astore 7
    //   44: invokestatic 363	com/cootek/usage/q:a	()Lcom/cootek/usage/q;
    //   47: aload_0
    //   48: iconst_1
    //   49: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   52: invokevirtual 366	com/cootek/usage/q:c	(Ljava/lang/String;)J
    //   55: lstore_2
    //   56: aload 8
    //   58: astore 7
    //   60: aload 13
    //   62: getstatic 369	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   65: bipush 6
    //   67: anewarray 72	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: ldc 74
    //   74: aastore
    //   75: dup
    //   76: iconst_1
    //   77: ldc_w 371
    //   80: aastore
    //   81: dup
    //   82: iconst_2
    //   83: ldc_w 313
    //   86: aastore
    //   87: dup
    //   88: iconst_3
    //   89: ldc_w 373
    //   92: aastore
    //   93: dup
    //   94: iconst_4
    //   95: ldc_w 293
    //   98: aastore
    //   99: dup
    //   100: iconst_5
    //   101: ldc_w 268
    //   104: aastore
    //   105: ldc_w 375
    //   108: iconst_1
    //   109: anewarray 72	java/lang/String
    //   112: dup
    //   113: iconst_0
    //   114: lload_2
    //   115: invokestatic 378	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   118: aastore
    //   119: ldc_w 380
    //   122: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   125: astore 8
    //   127: aload 8
    //   129: ifnull +233 -> 362
    //   132: aload 8
    //   134: invokeinterface 88 1 0
    //   139: ifeq +223 -> 362
    //   142: aload 11
    //   144: aload 8
    //   146: iconst_0
    //   147: invokeinterface 95 2 0
    //   152: putfield 382	com/cootek/usage/k$f:b	J
    //   155: aload 8
    //   157: iconst_1
    //   158: invokeinterface 102 2 0
    //   163: astore 7
    //   165: aload 8
    //   167: iconst_2
    //   168: invokeinterface 95 2 0
    //   173: lstore 4
    //   175: aload 8
    //   177: iconst_3
    //   178: invokeinterface 95 2 0
    //   183: lstore_2
    //   184: aload 8
    //   186: iconst_4
    //   187: invokeinterface 206 2 0
    //   192: istore_1
    //   193: aload 8
    //   195: iconst_5
    //   196: invokeinterface 102 2 0
    //   201: astore 9
    //   203: new 265	org/json/JSONObject
    //   206: dup
    //   207: invokespecial 266	org/json/JSONObject:<init>	()V
    //   210: astore 10
    //   212: aload 10
    //   214: ldc_w 384
    //   217: aload 7
    //   219: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   222: pop
    //   223: aload 10
    //   225: ldc_w 313
    //   228: lload 4
    //   230: ldc2_w 385
    //   233: ldiv
    //   234: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   237: pop
    //   238: iload_1
    //   239: iconst_2
    //   240: if_icmpne +11 -> 251
    //   243: ldc_w 391
    //   246: astore 7
    //   248: goto +8 -> 256
    //   251: ldc_w 393
    //   254: astore 7
    //   256: aload 10
    //   258: ldc_w 293
    //   261: aload 7
    //   263: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   266: pop
    //   267: iconst_3
    //   268: iload_1
    //   269: if_icmpne +5 -> 274
    //   272: lconst_0
    //   273: lstore_2
    //   274: aload 10
    //   276: ldc_w 373
    //   279: lload_2
    //   280: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   283: pop
    //   284: aload 10
    //   286: ldc_w 395
    //   289: aload 9
    //   291: invokestatic 289	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   294: iconst_1
    //   295: ixor
    //   296: invokevirtual 398	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   299: pop
    //   300: aload 12
    //   302: aload 10
    //   304: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   307: pop
    //   308: aload 11
    //   310: iconst_1
    //   311: putfield 320	com/cootek/usage/k$f:d	Z
    //   314: goto +15 -> 329
    //   317: astore 7
    //   319: goto +5 -> 324
    //   322: astore 7
    //   324: aload 7
    //   326: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   329: aload 8
    //   331: invokeinterface 137 1 0
    //   336: istore 6
    //   338: iload 6
    //   340: ifne +6 -> 346
    //   343: goto +19 -> 362
    //   346: goto -191 -> 155
    //   349: astore 7
    //   351: goto +223 -> 574
    //   354: astore 9
    //   356: goto +139 -> 495
    //   359: goto +180 -> 539
    //   362: aload 8
    //   364: ifnull +20 -> 384
    //   367: aload 8
    //   369: invokeinterface 140 1 0
    //   374: goto +10 -> 384
    //   377: astore 7
    //   379: aload 7
    //   381: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   384: new 265	org/json/JSONObject
    //   387: dup
    //   388: invokespecial 266	org/json/JSONObject:<init>	()V
    //   391: astore 7
    //   393: aload 7
    //   395: ldc_w 400
    //   398: aload 12
    //   400: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   403: pop
    //   404: goto +10 -> 414
    //   407: astore 8
    //   409: aload 8
    //   411: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   414: new 328	com/cootek/usage/UsageData
    //   417: dup
    //   418: invokespecial 329	com/cootek/usage/UsageData:<init>	()V
    //   421: astore 8
    //   423: aload 8
    //   425: aload_0
    //   426: invokevirtual 331	com/cootek/usage/k:b	()Ljava/lang/String;
    //   429: putfield 333	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   432: aload 8
    //   434: aload_0
    //   435: iconst_1
    //   436: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   439: putfield 338	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   442: aload 8
    //   444: aload 7
    //   446: invokevirtual 401	org/json/JSONObject:toString	()Ljava/lang/String;
    //   449: putfield 344	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   452: aload 11
    //   454: aload 8
    //   456: putfield 347	com/cootek/usage/k$f:a	Lcom/cootek/usage/UsageData;
    //   459: aload 11
    //   461: aload_0
    //   462: iconst_1
    //   463: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   466: putfield 348	com/cootek/usage/k$f:c	Ljava/lang/String;
    //   469: aload 11
    //   471: areturn
    //   472: astore 8
    //   474: aload 7
    //   476: astore 9
    //   478: aload 8
    //   480: astore 7
    //   482: aload 9
    //   484: astore 8
    //   486: goto +88 -> 574
    //   489: astore 9
    //   491: aload 10
    //   493: astore 8
    //   495: aload 8
    //   497: astore 7
    //   499: aload 9
    //   501: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   504: aload 8
    //   506: astore 7
    //   508: aload 11
    //   510: iconst_0
    //   511: putfield 320	com/cootek/usage/k$f:d	Z
    //   514: aload 8
    //   516: ifnull +20 -> 536
    //   519: aload 8
    //   521: invokeinterface 140 1 0
    //   526: aload 11
    //   528: areturn
    //   529: astore 7
    //   531: aload 7
    //   533: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   536: aload 11
    //   538: areturn
    //   539: aload 8
    //   541: astore 7
    //   543: aload 11
    //   545: iconst_0
    //   546: putfield 320	com/cootek/usage/k$f:d	Z
    //   549: aload 8
    //   551: ifnull +20 -> 571
    //   554: aload 8
    //   556: invokeinterface 140 1 0
    //   561: aload 11
    //   563: areturn
    //   564: astore 7
    //   566: aload 7
    //   568: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   571: aload 11
    //   573: areturn
    //   574: aload 8
    //   576: ifnull +20 -> 596
    //   579: aload 8
    //   581: invokeinterface 140 1 0
    //   586: goto +10 -> 596
    //   589: astore 8
    //   591: aload 8
    //   593: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   596: aload 7
    //   598: athrow
    //   599: astore 7
    //   601: aload 9
    //   603: astore 8
    //   605: goto -66 -> 539
    //   608: astore 7
    //   610: goto -251 -> 359
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	613	0	this	k
    //   192	78	1	i	int
    //   55	225	2	l1	long
    //   173	56	4	l2	long
    //   336	3	6	bool	boolean
    //   42	220	7	localObject1	Object
    //   317	1	7	localJSONException1	JSONException
    //   322	3	7	localJSONException2	JSONException
    //   349	1	7	localObject2	Object
    //   377	3	7	localRuntimeException1	RuntimeException
    //   391	116	7	localObject3	Object
    //   529	3	7	localRuntimeException2	RuntimeException
    //   541	1	7	localObject4	Object
    //   564	33	7	localRuntimeException3	RuntimeException
    //   599	1	7	localSecurityException1	SecurityException
    //   608	1	7	localSecurityException2	SecurityException
    //   38	330	8	localCursor	android.database.Cursor
    //   407	3	8	localJSONException3	JSONException
    //   421	34	8	localUsageData	UsageData
    //   472	7	8	localObject5	Object
    //   484	96	8	localObject6	Object
    //   589	3	8	localRuntimeException4	RuntimeException
    //   603	1	8	localRuntimeException5	RuntimeException
    //   35	255	9	str	String
    //   354	1	9	localRuntimeException6	RuntimeException
    //   476	7	9	localObject7	Object
    //   489	113	9	localRuntimeException7	RuntimeException
    //   32	460	10	localJSONObject	JSONObject
    //   8	564	11	localF	f
    //   29	370	12	localJSONArray	JSONArray
    //   20	41	13	localContentResolver	android.content.ContentResolver
    // Exception table:
    //   from	to	target	type
    //   274	314	317	org/json/JSONException
    //   212	238	322	org/json/JSONException
    //   256	267	322	org/json/JSONException
    //   132	155	349	finally
    //   155	212	349	finally
    //   212	238	349	finally
    //   256	267	349	finally
    //   274	314	349	finally
    //   324	329	349	finally
    //   329	338	349	finally
    //   132	155	354	java/lang/RuntimeException
    //   155	212	354	java/lang/RuntimeException
    //   212	238	354	java/lang/RuntimeException
    //   256	267	354	java/lang/RuntimeException
    //   274	314	354	java/lang/RuntimeException
    //   324	329	354	java/lang/RuntimeException
    //   329	338	354	java/lang/RuntimeException
    //   367	374	377	java/lang/RuntimeException
    //   393	404	407	org/json/JSONException
    //   44	56	472	finally
    //   60	127	472	finally
    //   499	504	472	finally
    //   508	514	472	finally
    //   543	549	472	finally
    //   44	56	489	java/lang/RuntimeException
    //   60	127	489	java/lang/RuntimeException
    //   519	526	529	java/lang/RuntimeException
    //   554	561	564	java/lang/RuntimeException
    //   579	586	589	java/lang/RuntimeException
    //   44	56	599	java/lang/SecurityException
    //   60	127	599	java/lang/SecurityException
    //   132	155	608	java/lang/SecurityException
    //   155	212	608	java/lang/SecurityException
    //   212	238	608	java/lang/SecurityException
    //   256	267	608	java/lang/SecurityException
    //   274	314	608	java/lang/SecurityException
    //   324	329	608	java/lang/SecurityException
    //   329	338	608	java/lang/SecurityException
  }
  
  private String d(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 2: 
      return "OTHER";
    case 1: 
      return "WORK";
    }
    return null;
  }
  
  /* Error */
  private f e()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/k$f
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 49	com/cootek/usage/k$f:<init>	(Lcom/cootek/usage/k;)V
    //   8: astore 10
    //   10: aload_0
    //   11: getfield 38	com/cootek/usage/k:a	Lcom/cootek/usage/a;
    //   14: invokevirtual 55	com/cootek/usage/a:getContext	()Landroid/content/Context;
    //   17: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 12
    //   22: new 243	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 244	org/json/JSONArray:<init>	()V
    //   29: astore 11
    //   31: aconst_null
    //   32: astore 9
    //   34: aconst_null
    //   35: astore 8
    //   37: aconst_null
    //   38: astore 7
    //   40: aload 7
    //   42: astore 6
    //   44: invokestatic 363	com/cootek/usage/q:a	()Lcom/cootek/usage/q;
    //   47: aload_0
    //   48: iconst_2
    //   49: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   52: invokevirtual 366	com/cootek/usage/q:c	(Ljava/lang/String;)J
    //   55: lstore_1
    //   56: aload 7
    //   58: astore 6
    //   60: aload 12
    //   62: ldc_w 403
    //   65: invokestatic 409	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   68: bipush 6
    //   70: anewarray 72	java/lang/String
    //   73: dup
    //   74: iconst_0
    //   75: ldc 74
    //   77: aastore
    //   78: dup
    //   79: iconst_1
    //   80: ldc_w 291
    //   83: aastore
    //   84: dup
    //   85: iconst_2
    //   86: ldc_w 411
    //   89: aastore
    //   90: dup
    //   91: iconst_3
    //   92: ldc_w 313
    //   95: aastore
    //   96: dup
    //   97: iconst_4
    //   98: ldc_w 413
    //   101: aastore
    //   102: dup
    //   103: iconst_5
    //   104: ldc_w 415
    //   107: aastore
    //   108: ldc_w 375
    //   111: iconst_1
    //   112: anewarray 72	java/lang/String
    //   115: dup
    //   116: iconst_0
    //   117: lload_1
    //   118: invokestatic 378	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   121: aastore
    //   122: ldc_w 417
    //   125: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   128: astore 7
    //   130: aload 7
    //   132: ifnull +236 -> 368
    //   135: aload 7
    //   137: invokeinterface 88 1 0
    //   142: ifeq +226 -> 368
    //   145: aload 10
    //   147: aload 7
    //   149: iconst_0
    //   150: invokeinterface 95 2 0
    //   155: putfield 382	com/cootek/usage/k$f:b	J
    //   158: aload 7
    //   160: iconst_1
    //   161: invokeinterface 102 2 0
    //   166: astore 6
    //   168: aload 7
    //   170: iconst_2
    //   171: invokeinterface 95 2 0
    //   176: lstore_1
    //   177: lload_1
    //   178: lconst_0
    //   179: lcmp
    //   180: ifle +6 -> 186
    //   183: goto +152 -> 335
    //   186: aload 7
    //   188: iconst_3
    //   189: invokeinterface 95 2 0
    //   194: lstore_3
    //   195: aload 7
    //   197: iconst_4
    //   198: invokeinterface 102 2 0
    //   203: astore 8
    //   205: aload 7
    //   207: iconst_5
    //   208: invokeinterface 102 2 0
    //   213: astore 9
    //   215: new 265	org/json/JSONObject
    //   218: dup
    //   219: invokespecial 266	org/json/JSONObject:<init>	()V
    //   222: astore 12
    //   224: aload 12
    //   226: ldc_w 384
    //   229: aload 6
    //   231: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   234: pop
    //   235: aload 12
    //   237: ldc_w 313
    //   240: lload_3
    //   241: ldc2_w 385
    //   244: ldiv
    //   245: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   248: pop
    //   249: aload 12
    //   251: ldc_w 293
    //   254: ldc_w 393
    //   257: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   260: pop
    //   261: lload_1
    //   262: lconst_0
    //   263: lcmp
    //   264: ifle +355 -> 619
    //   267: iconst_1
    //   268: istore 5
    //   270: goto +3 -> 273
    //   273: aload 12
    //   275: ldc_w 395
    //   278: iload 5
    //   280: invokevirtual 398	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   283: pop
    //   284: aload 12
    //   286: ldc_w 419
    //   289: aload 8
    //   291: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   294: pop
    //   295: aload 12
    //   297: ldc_w 415
    //   300: aload 9
    //   302: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   305: pop
    //   306: aload 11
    //   308: aload 12
    //   310: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   313: pop
    //   314: aload 10
    //   316: iconst_1
    //   317: putfield 320	com/cootek/usage/k$f:d	Z
    //   320: goto +15 -> 335
    //   323: astore 6
    //   325: goto +5 -> 330
    //   328: astore 6
    //   330: aload 6
    //   332: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   335: aload 7
    //   337: invokeinterface 137 1 0
    //   342: istore 5
    //   344: iload 5
    //   346: ifne +6 -> 352
    //   349: goto +19 -> 368
    //   352: goto -194 -> 158
    //   355: astore 6
    //   357: goto +223 -> 580
    //   360: astore 8
    //   362: goto +139 -> 501
    //   365: goto +180 -> 545
    //   368: aload 7
    //   370: ifnull +20 -> 390
    //   373: aload 7
    //   375: invokeinterface 140 1 0
    //   380: goto +10 -> 390
    //   383: astore 6
    //   385: aload 6
    //   387: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   390: new 265	org/json/JSONObject
    //   393: dup
    //   394: invokespecial 266	org/json/JSONObject:<init>	()V
    //   397: astore 6
    //   399: aload 6
    //   401: ldc_w 400
    //   404: aload 11
    //   406: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   409: pop
    //   410: goto +10 -> 420
    //   413: astore 7
    //   415: aload 7
    //   417: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   420: new 328	com/cootek/usage/UsageData
    //   423: dup
    //   424: invokespecial 329	com/cootek/usage/UsageData:<init>	()V
    //   427: astore 7
    //   429: aload 7
    //   431: aload_0
    //   432: invokevirtual 331	com/cootek/usage/k:b	()Ljava/lang/String;
    //   435: putfield 333	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   438: aload 7
    //   440: aload_0
    //   441: iconst_2
    //   442: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   445: putfield 338	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   448: aload 7
    //   450: aload 6
    //   452: invokevirtual 401	org/json/JSONObject:toString	()Ljava/lang/String;
    //   455: putfield 344	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   458: aload 10
    //   460: aload 7
    //   462: putfield 347	com/cootek/usage/k$f:a	Lcom/cootek/usage/UsageData;
    //   465: aload 10
    //   467: aload_0
    //   468: iconst_2
    //   469: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   472: putfield 348	com/cootek/usage/k$f:c	Ljava/lang/String;
    //   475: aload 10
    //   477: areturn
    //   478: astore 7
    //   480: aload 6
    //   482: astore 8
    //   484: aload 7
    //   486: astore 6
    //   488: aload 8
    //   490: astore 7
    //   492: goto +88 -> 580
    //   495: astore 8
    //   497: aload 9
    //   499: astore 7
    //   501: aload 7
    //   503: astore 6
    //   505: aload 8
    //   507: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   510: aload 7
    //   512: astore 6
    //   514: aload 10
    //   516: iconst_0
    //   517: putfield 320	com/cootek/usage/k$f:d	Z
    //   520: aload 7
    //   522: ifnull +20 -> 542
    //   525: aload 7
    //   527: invokeinterface 140 1 0
    //   532: aload 10
    //   534: areturn
    //   535: astore 6
    //   537: aload 6
    //   539: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   542: aload 10
    //   544: areturn
    //   545: aload 7
    //   547: astore 6
    //   549: aload 10
    //   551: iconst_0
    //   552: putfield 320	com/cootek/usage/k$f:d	Z
    //   555: aload 7
    //   557: ifnull +20 -> 577
    //   560: aload 7
    //   562: invokeinterface 140 1 0
    //   567: aload 10
    //   569: areturn
    //   570: astore 6
    //   572: aload 6
    //   574: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   577: aload 10
    //   579: areturn
    //   580: aload 7
    //   582: ifnull +20 -> 602
    //   585: aload 7
    //   587: invokeinterface 140 1 0
    //   592: goto +10 -> 602
    //   595: astore 7
    //   597: aload 7
    //   599: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   602: aload 6
    //   604: athrow
    //   605: astore 6
    //   607: aload 8
    //   609: astore 7
    //   611: goto -66 -> 545
    //   614: astore 6
    //   616: goto -251 -> 365
    //   619: iconst_0
    //   620: istore 5
    //   622: goto -349 -> 273
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	625	0	this	k
    //   55	207	1	l1	long
    //   194	47	3	l2	long
    //   268	353	5	bool	boolean
    //   42	188	6	localObject1	Object
    //   323	1	6	localJSONException1	JSONException
    //   328	3	6	localJSONException2	JSONException
    //   355	1	6	localObject2	Object
    //   383	3	6	localRuntimeException1	RuntimeException
    //   397	116	6	localObject3	Object
    //   535	3	6	localRuntimeException2	RuntimeException
    //   547	1	6	localObject4	Object
    //   570	33	6	localRuntimeException3	RuntimeException
    //   605	1	6	localSecurityException1	SecurityException
    //   614	1	6	localSecurityException2	SecurityException
    //   38	336	7	localCursor	android.database.Cursor
    //   413	3	7	localJSONException3	JSONException
    //   427	34	7	localUsageData	UsageData
    //   478	7	7	localObject5	Object
    //   490	96	7	localObject6	Object
    //   595	3	7	localRuntimeException4	RuntimeException
    //   609	1	7	localRuntimeException5	RuntimeException
    //   35	255	8	str1	String
    //   360	1	8	localRuntimeException6	RuntimeException
    //   482	7	8	localObject7	Object
    //   495	113	8	localRuntimeException7	RuntimeException
    //   32	466	9	str2	String
    //   8	570	10	localF	f
    //   29	376	11	localJSONArray	JSONArray
    //   20	289	12	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   314	320	323	org/json/JSONException
    //   224	261	328	org/json/JSONException
    //   273	314	328	org/json/JSONException
    //   135	158	355	finally
    //   158	177	355	finally
    //   186	224	355	finally
    //   224	261	355	finally
    //   273	314	355	finally
    //   314	320	355	finally
    //   330	335	355	finally
    //   335	344	355	finally
    //   135	158	360	java/lang/RuntimeException
    //   158	177	360	java/lang/RuntimeException
    //   186	224	360	java/lang/RuntimeException
    //   224	261	360	java/lang/RuntimeException
    //   273	314	360	java/lang/RuntimeException
    //   314	320	360	java/lang/RuntimeException
    //   330	335	360	java/lang/RuntimeException
    //   335	344	360	java/lang/RuntimeException
    //   373	380	383	java/lang/RuntimeException
    //   399	410	413	org/json/JSONException
    //   44	56	478	finally
    //   60	130	478	finally
    //   505	510	478	finally
    //   514	520	478	finally
    //   549	555	478	finally
    //   44	56	495	java/lang/RuntimeException
    //   60	130	495	java/lang/RuntimeException
    //   525	532	535	java/lang/RuntimeException
    //   560	567	570	java/lang/RuntimeException
    //   585	592	595	java/lang/RuntimeException
    //   44	56	605	java/lang/SecurityException
    //   60	130	605	java/lang/SecurityException
    //   135	158	614	java/lang/SecurityException
    //   158	177	614	java/lang/SecurityException
    //   186	224	614	java/lang/SecurityException
    //   224	261	614	java/lang/SecurityException
    //   273	314	614	java/lang/SecurityException
    //   314	320	614	java/lang/SecurityException
    //   330	335	614	java/lang/SecurityException
    //   335	344	614	java/lang/SecurityException
  }
  
  private String e(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 3: 
      return "OTHER";
    case 2: 
      return "WORK";
    case 1: 
      return "HOME";
    }
    return null;
  }
  
  private f f()
  {
    f localF = new f();
    Object localObject1 = this.a.getContext().getPackageManager();
    int i = 0;
    List localList = ((PackageManager)localObject1).getInstalledPackages(0);
    JSONArray localJSONArray = new JSONArray();
    while (i < localList.size())
    {
      Object localObject2 = (PackageInfo)localList.get(i);
      if (((((PackageInfo)localObject2).applicationInfo.flags & 0x1) == 0) || ((((PackageInfo)localObject2).applicationInfo.flags & 0x80) != 0))
      {
        String str = ((PackageInfo)localObject2).applicationInfo.loadLabel((PackageManager)localObject1).toString();
        localObject2 = ((PackageInfo)localObject2).packageName;
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("name", str);
          localJSONObject.put("package_name", localObject2);
          localJSONArray.put(localJSONObject);
          localF.d = true;
        }
        catch (JSONException localJSONException2)
        {
          com.google.a.a.a.a.a.a.a(localJSONException2);
        }
      }
      i += 1;
    }
    localObject1 = new JSONObject();
    try
    {
      ((JSONObject)localObject1).put("data", localJSONArray);
    }
    catch (JSONException localJSONException1)
    {
      com.google.a.a.a.a.a.a.a(localJSONException1);
    }
    UsageData localUsageData = new UsageData();
    localUsageData.type = b();
    localUsageData.path = a(3);
    localUsageData.value = ((JSONObject)localObject1).toString();
    localF.a = localUsageData;
    localF.c = a(3);
    return localF;
  }
  
  private String f(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 3: 
      return "OTHER";
    case 2: 
      return "WORK";
    case 1: 
      return "HOME";
    }
    return null;
  }
  
  /* Error */
  private f g()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/k$f
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 49	com/cootek/usage/k$f:<init>	(Lcom/cootek/usage/k;)V
    //   8: astore 13
    //   10: aload_0
    //   11: getfield 38	com/cootek/usage/k:a	Lcom/cootek/usage/a;
    //   14: invokevirtual 55	com/cootek/usage/a:getContext	()Landroid/content/Context;
    //   17: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 10
    //   22: new 243	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 244	org/json/JSONArray:<init>	()V
    //   29: astore 12
    //   31: ldc_w 463
    //   34: invokestatic 409	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   37: astore 11
    //   39: invokestatic 363	com/cootek/usage/q:a	()Lcom/cootek/usage/q;
    //   42: aload_0
    //   43: iconst_1
    //   44: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   47: invokevirtual 366	com/cootek/usage/q:c	(Ljava/lang/String;)J
    //   50: lstore_3
    //   51: aload 10
    //   53: aload 11
    //   55: bipush 7
    //   57: anewarray 72	java/lang/String
    //   60: dup
    //   61: iconst_0
    //   62: ldc 74
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: ldc_w 371
    //   70: aastore
    //   71: dup
    //   72: iconst_2
    //   73: ldc_w 313
    //   76: aastore
    //   77: dup
    //   78: iconst_3
    //   79: ldc_w 373
    //   82: aastore
    //   83: dup
    //   84: iconst_4
    //   85: ldc_w 293
    //   88: aastore
    //   89: dup
    //   90: iconst_5
    //   91: ldc -106
    //   93: aastore
    //   94: dup
    //   95: bipush 6
    //   97: ldc_w 465
    //   100: aastore
    //   101: ldc_w 375
    //   104: iconst_1
    //   105: anewarray 72	java/lang/String
    //   108: dup
    //   109: iconst_0
    //   110: lload_3
    //   111: invokestatic 378	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   114: aastore
    //   115: ldc_w 380
    //   118: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   121: astore 10
    //   123: aload 10
    //   125: ifnull +318 -> 443
    //   128: aload 10
    //   130: invokeinterface 88 1 0
    //   135: ifeq +308 -> 443
    //   138: aload 13
    //   140: aload 10
    //   142: iconst_0
    //   143: invokeinterface 95 2 0
    //   148: putfield 382	com/cootek/usage/k$f:b	J
    //   151: aload 10
    //   153: iconst_1
    //   154: invokeinterface 102 2 0
    //   159: astore 11
    //   161: aload 10
    //   163: iconst_2
    //   164: invokeinterface 95 2 0
    //   169: lstore 7
    //   171: aload 10
    //   173: iconst_3
    //   174: invokeinterface 95 2 0
    //   179: lstore_3
    //   180: aload 10
    //   182: iconst_4
    //   183: invokeinterface 206 2 0
    //   188: istore_1
    //   189: aload 10
    //   191: iconst_5
    //   192: invokeinterface 95 2 0
    //   197: lstore 5
    //   199: aload 10
    //   201: bipush 6
    //   203: invokeinterface 206 2 0
    //   208: istore_2
    //   209: new 265	org/json/JSONObject
    //   212: dup
    //   213: invokespecial 266	org/json/JSONObject:<init>	()V
    //   216: astore 14
    //   218: aload 14
    //   220: ldc_w 384
    //   223: aload 11
    //   225: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   228: pop
    //   229: aload 14
    //   231: ldc_w 313
    //   234: lload 7
    //   236: ldc2_w 385
    //   239: ldiv
    //   240: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   243: pop
    //   244: iconst_3
    //   245: iload_1
    //   246: if_icmpne +439 -> 685
    //   249: lconst_0
    //   250: lstore_3
    //   251: goto +3 -> 254
    //   254: aload 14
    //   256: ldc_w 373
    //   259: lload_3
    //   260: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   263: pop
    //   264: lload 5
    //   266: lconst_0
    //   267: lcmp
    //   268: ifeq +420 -> 688
    //   271: iconst_1
    //   272: istore 9
    //   274: goto +3 -> 277
    //   277: aload 14
    //   279: ldc_w 395
    //   282: iload 9
    //   284: invokevirtual 398	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   287: pop
    //   288: iload_1
    //   289: iconst_2
    //   290: if_icmpne +16 -> 306
    //   293: ldc_w 391
    //   296: astore 11
    //   298: goto +13 -> 311
    //   301: astore 11
    //   303: goto +94 -> 397
    //   306: ldc_w 393
    //   309: astore 11
    //   311: aload 14
    //   313: ldc_w 293
    //   316: aload 11
    //   318: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   321: pop
    //   322: iload_2
    //   323: ifne +371 -> 694
    //   326: ldc_w 467
    //   329: astore 11
    //   331: aload 14
    //   333: ldc_w 469
    //   336: aload 11
    //   338: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   341: pop
    //   342: goto +36 -> 378
    //   345: iload_2
    //   346: iconst_2
    //   347: if_icmpne +11 -> 358
    //   350: ldc_w 471
    //   353: astore 11
    //   355: goto -24 -> 331
    //   358: iload_2
    //   359: iconst_3
    //   360: if_icmpne +18 -> 378
    //   363: aload 14
    //   365: ldc_w 469
    //   368: ldc_w 473
    //   371: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   374: pop
    //   375: goto +3 -> 378
    //   378: aload 12
    //   380: aload 14
    //   382: invokevirtual 278	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   385: pop
    //   386: aload 13
    //   388: iconst_1
    //   389: putfield 320	com/cootek/usage/k$f:d	Z
    //   392: goto +10 -> 402
    //   395: astore 11
    //   397: aload 11
    //   399: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   402: aload 10
    //   404: invokeinterface 137 1 0
    //   409: istore 9
    //   411: iload 9
    //   413: ifne +6 -> 419
    //   416: goto +27 -> 443
    //   419: goto -268 -> 151
    //   422: astore 11
    //   424: goto +226 -> 650
    //   427: astore 12
    //   429: aload 10
    //   431: astore 11
    //   433: goto +133 -> 566
    //   436: aload 10
    //   438: astore 11
    //   440: goto +173 -> 613
    //   443: aload 10
    //   445: ifnull +20 -> 465
    //   448: aload 10
    //   450: invokeinterface 140 1 0
    //   455: goto +10 -> 465
    //   458: astore 10
    //   460: aload 10
    //   462: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   465: new 265	org/json/JSONObject
    //   468: dup
    //   469: invokespecial 266	org/json/JSONObject:<init>	()V
    //   472: astore 10
    //   474: aload 10
    //   476: ldc_w 400
    //   479: aload 12
    //   481: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   484: pop
    //   485: goto +10 -> 495
    //   488: astore 11
    //   490: aload 11
    //   492: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   495: new 328	com/cootek/usage/UsageData
    //   498: dup
    //   499: invokespecial 329	com/cootek/usage/UsageData:<init>	()V
    //   502: astore 11
    //   504: aload 11
    //   506: aload_0
    //   507: invokevirtual 331	com/cootek/usage/k:b	()Ljava/lang/String;
    //   510: putfield 333	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   513: aload 11
    //   515: aload_0
    //   516: iconst_4
    //   517: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   520: putfield 338	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   523: aload 11
    //   525: aload 10
    //   527: invokevirtual 401	org/json/JSONObject:toString	()Ljava/lang/String;
    //   530: putfield 344	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   533: aload 13
    //   535: aload 11
    //   537: putfield 347	com/cootek/usage/k$f:a	Lcom/cootek/usage/UsageData;
    //   540: aload 13
    //   542: aload_0
    //   543: iconst_4
    //   544: invokevirtual 335	com/cootek/usage/k:a	(I)Ljava/lang/String;
    //   547: putfield 348	com/cootek/usage/k$f:c	Ljava/lang/String;
    //   550: aload 13
    //   552: areturn
    //   553: astore 11
    //   555: aconst_null
    //   556: astore 10
    //   558: goto +92 -> 650
    //   561: astore 12
    //   563: aconst_null
    //   564: astore 11
    //   566: aload 11
    //   568: astore 10
    //   570: aload 12
    //   572: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   575: aload 11
    //   577: astore 10
    //   579: aload 13
    //   581: iconst_0
    //   582: putfield 320	com/cootek/usage/k$f:d	Z
    //   585: aload 11
    //   587: ifnull +20 -> 607
    //   590: aload 11
    //   592: invokeinterface 140 1 0
    //   597: aload 13
    //   599: areturn
    //   600: astore 10
    //   602: aload 10
    //   604: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   607: aload 13
    //   609: areturn
    //   610: aconst_null
    //   611: astore 11
    //   613: aload 11
    //   615: astore 10
    //   617: aload 13
    //   619: iconst_0
    //   620: putfield 320	com/cootek/usage/k$f:d	Z
    //   623: aload 11
    //   625: ifnull +20 -> 645
    //   628: aload 11
    //   630: invokeinterface 140 1 0
    //   635: aload 13
    //   637: areturn
    //   638: astore 10
    //   640: aload 10
    //   642: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   645: aload 13
    //   647: areturn
    //   648: astore 11
    //   650: aload 10
    //   652: ifnull +20 -> 672
    //   655: aload 10
    //   657: invokeinterface 140 1 0
    //   662: goto +10 -> 672
    //   665: astore 10
    //   667: aload 10
    //   669: invokestatic 145	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   672: aload 11
    //   674: athrow
    //   675: astore 10
    //   677: goto -67 -> 610
    //   680: astore 11
    //   682: goto -246 -> 436
    //   685: goto -431 -> 254
    //   688: iconst_0
    //   689: istore 9
    //   691: goto -414 -> 277
    //   694: iload_2
    //   695: iconst_1
    //   696: if_icmpne -351 -> 345
    //   699: ldc_w 475
    //   702: astore 11
    //   704: goto -373 -> 331
    //   707: astore 11
    //   709: goto -312 -> 397
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	712	0	this	k
    //   188	103	1	i	int
    //   208	489	2	j	int
    //   50	210	3	l1	long
    //   197	68	5	l2	long
    //   169	66	7	l3	long
    //   272	418	9	bool	boolean
    //   20	429	10	localObject1	Object
    //   458	3	10	localRuntimeException1	RuntimeException
    //   472	106	10	localObject2	Object
    //   600	3	10	localRuntimeException2	RuntimeException
    //   615	1	10	localObject3	Object
    //   638	18	10	localRuntimeException3	RuntimeException
    //   665	3	10	localRuntimeException4	RuntimeException
    //   675	1	10	localSecurityException1	SecurityException
    //   37	260	11	localObject4	Object
    //   301	1	11	localJSONException1	JSONException
    //   309	45	11	str1	String
    //   395	3	11	localJSONException2	JSONException
    //   422	1	11	localObject5	Object
    //   431	8	11	localObject6	Object
    //   488	3	11	localJSONException3	JSONException
    //   502	34	11	localUsageData	UsageData
    //   553	1	11	localObject7	Object
    //   564	65	11	localObject8	Object
    //   648	25	11	localObject9	Object
    //   680	1	11	localSecurityException2	SecurityException
    //   702	1	11	str2	String
    //   707	1	11	localJSONException4	JSONException
    //   29	350	12	localJSONArray	JSONArray
    //   427	53	12	localRuntimeException5	RuntimeException
    //   561	10	12	localRuntimeException6	RuntimeException
    //   8	638	13	localF	f
    //   216	165	14	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   331	342	301	org/json/JSONException
    //   218	244	395	org/json/JSONException
    //   254	264	395	org/json/JSONException
    //   277	288	395	org/json/JSONException
    //   311	322	395	org/json/JSONException
    //   128	151	422	finally
    //   151	218	422	finally
    //   218	244	422	finally
    //   254	264	422	finally
    //   277	288	422	finally
    //   311	322	422	finally
    //   331	342	422	finally
    //   363	375	422	finally
    //   378	392	422	finally
    //   397	402	422	finally
    //   402	411	422	finally
    //   128	151	427	java/lang/RuntimeException
    //   151	218	427	java/lang/RuntimeException
    //   218	244	427	java/lang/RuntimeException
    //   254	264	427	java/lang/RuntimeException
    //   277	288	427	java/lang/RuntimeException
    //   311	322	427	java/lang/RuntimeException
    //   331	342	427	java/lang/RuntimeException
    //   363	375	427	java/lang/RuntimeException
    //   378	392	427	java/lang/RuntimeException
    //   397	402	427	java/lang/RuntimeException
    //   402	411	427	java/lang/RuntimeException
    //   448	455	458	java/lang/RuntimeException
    //   474	485	488	org/json/JSONException
    //   31	123	553	finally
    //   31	123	561	java/lang/RuntimeException
    //   590	597	600	java/lang/RuntimeException
    //   628	635	638	java/lang/RuntimeException
    //   570	575	648	finally
    //   579	585	648	finally
    //   617	623	648	finally
    //   655	662	665	java/lang/RuntimeException
    //   31	123	675	java/lang/SecurityException
    //   128	151	680	java/lang/SecurityException
    //   151	218	680	java/lang/SecurityException
    //   218	244	680	java/lang/SecurityException
    //   254	264	680	java/lang/SecurityException
    //   277	288	680	java/lang/SecurityException
    //   311	322	680	java/lang/SecurityException
    //   331	342	680	java/lang/SecurityException
    //   363	375	680	java/lang/SecurityException
    //   378	392	680	java/lang/SecurityException
    //   397	402	680	java/lang/SecurityException
    //   402	411	680	java/lang/SecurityException
    //   363	375	707	org/json/JSONException
    //   378	392	707	org/json/JSONException
  }
  
  private String g(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 8: 
      return "NETMEETING";
    case 7: 
      return "JABBER";
    case 6: 
      return "ICQ";
    case 5: 
      return "GOOGLE_TALK";
    case 4: 
      return "QQ";
    case 3: 
      return "SKYPE";
    case 2: 
      return "YAHOO";
    case 1: 
      return "MSN";
    case 0: 
      return "AIM";
    }
    return null;
  }
  
  private f h()
  {
    f localF = new f();
    JSONArray localJSONArray = new JSONArray();
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      String str1 = o.a(c.a.getContext());
      String str2 = o.b(c.a.getContext());
      localJSONObject1.put("phone", str1);
      localJSONObject1.put("IMSI", str2);
      localJSONArray.put(localJSONObject1);
      localF.d = true;
    }
    catch (JSONException localJSONException2)
    {
      com.google.a.a.a.a.a.a.a(localJSONException2);
    }
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      localJSONObject2.put("data", localJSONArray);
    }
    catch (JSONException localJSONException1)
    {
      com.google.a.a.a.a.a.a.a(localJSONException1);
    }
    UsageData localUsageData = new UsageData();
    localUsageData.type = b();
    localUsageData.path = a(5);
    localUsageData.value = localJSONObject2.toString();
    localF.a = localUsageData;
    localF.c = a(5);
    return localF;
  }
  
  private String h(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 3: 
      return "BIRTHDAY";
    case 2: 
      return "OTHER";
    case 1: 
      return "ANNIVERSARY";
    }
    return null;
  }
  
  private String i(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 14: 
      return "SPOUSE";
    case 13: 
      return "SISTER";
    case 12: 
      return "RELATIVE";
    case 11: 
      return "REFERRED_BY";
    case 10: 
      return "PARTNER";
    case 9: 
      return "PARENT";
    case 8: 
      return "MOTHER";
    case 7: 
      return "MANAGER";
    case 6: 
      return "FRIEND";
    case 5: 
      return "FATHER";
    case 4: 
      return "DOMESTIC_PARTNER";
    case 3: 
      return "CHILD";
    case 2: 
      return "BROTHER";
    case 1: 
      return "ASSISTANT";
    }
    return null;
  }
  
  int a()
  {
    return 6;
  }
  
  String a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("error info value: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 5: 
      return "noah_reserve_05";
    case 4: 
      return "noah_reserve_04";
    case 3: 
      return "noah_reserve_03";
    case 2: 
      return "noah_reserve_02";
    case 1: 
      return "noah_reserve_01";
    }
    return "noah_reserve_00";
  }
  
  f b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("error info value: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 5: 
      return h();
    case 4: 
      return g();
    case 3: 
      return f();
    case 2: 
      return e();
    case 1: 
      return d();
    }
    return c();
  }
  
  String b()
  {
    return "noah_info";
  }
  
  private class a
  {
    String a;
    String b;
    
    private a() {}
  }
  
  private class b
  {
    long a;
    String b;
    HashSet<String> c;
    HashSet<k.c> d;
    HashSet<k.g> e;
    HashSet<k.e> f;
    HashSet<k.a> g;
    HashSet<k.d> h;
    HashSet<k.h> i;
    
    private b() {}
  }
  
  private class c
  {
    String a;
    String b;
    
    private c() {}
  }
  
  private class d
  {
    String a;
    String b;
    
    private d() {}
  }
  
  private class e
  {
    String a;
    String b;
    String c;
    
    private e() {}
  }
  
  class f
  {
    UsageData a;
    long b;
    String c;
    boolean d;
    
    f() {}
  }
  
  private class g
  {
    String a;
    String b;
    String c;
    String d;
    
    private g() {}
  }
  
  private class h
  {
    String a;
    String b;
    
    private h() {}
  }
}
