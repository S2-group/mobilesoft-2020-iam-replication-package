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

class g
{
  a a;
  
  g(a paramA)
  {
    this.a = paramA;
  }
  
  /* Error */
  private f c()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 7
    //   9: new 23	com/cootek/usage/g$f
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 49	com/cootek/usage/g$f:<init>	(Lcom/cootek/usage/g;)V
    //   17: astore 8
    //   19: aload_0
    //   20: getfield 38	com/cootek/usage/g:a	Lcom/cootek/usage/a;
    //   23: invokevirtual 55	com/cootek/usage/a:getContext	()Landroid/content/Context;
    //   26: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   29: astore 10
    //   31: new 63	java/util/Hashtable
    //   34: dup
    //   35: invokespecial 64	java/util/Hashtable:<init>	()V
    //   38: astore 9
    //   40: aload 10
    //   42: getstatic 70	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
    //   45: iconst_2
    //   46: anewarray 72	java/lang/String
    //   49: dup
    //   50: iconst_0
    //   51: ldc 74
    //   53: aastore
    //   54: dup
    //   55: iconst_1
    //   56: ldc 76
    //   58: aastore
    //   59: aconst_null
    //   60: aconst_null
    //   61: aconst_null
    //   62: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   65: astore_3
    //   66: aload_3
    //   67: ifnull +197 -> 264
    //   70: aload_3
    //   71: astore 4
    //   73: aload_3
    //   74: invokeinterface 88 1 0
    //   79: ifeq +185 -> 264
    //   82: aload_3
    //   83: astore 4
    //   85: new 11	com/cootek/usage/g$b
    //   88: dup
    //   89: aload_0
    //   90: aconst_null
    //   91: invokespecial 91	com/cootek/usage/g$b:<init>	(Lcom/cootek/usage/g;Lcom/cootek/usage/g$1;)V
    //   94: astore 11
    //   96: aload_3
    //   97: astore 4
    //   99: aload 11
    //   101: aload_3
    //   102: iconst_0
    //   103: invokeinterface 95 2 0
    //   108: putfield 98	com/cootek/usage/g$b:a	J
    //   111: aload_3
    //   112: astore 4
    //   114: aload 11
    //   116: aload_3
    //   117: iconst_1
    //   118: invokeinterface 102 2 0
    //   123: putfield 105	com/cootek/usage/g$b:b	Ljava/lang/String;
    //   126: aload_3
    //   127: astore 4
    //   129: aload 11
    //   131: new 107	java/util/HashSet
    //   134: dup
    //   135: invokespecial 108	java/util/HashSet:<init>	()V
    //   138: putfield 111	com/cootek/usage/g$b:c	Ljava/util/HashSet;
    //   141: aload_3
    //   142: astore 4
    //   144: aload 11
    //   146: new 107	java/util/HashSet
    //   149: dup
    //   150: invokespecial 108	java/util/HashSet:<init>	()V
    //   153: putfield 113	com/cootek/usage/g$b:d	Ljava/util/HashSet;
    //   156: aload_3
    //   157: astore 4
    //   159: aload 11
    //   161: new 107	java/util/HashSet
    //   164: dup
    //   165: invokespecial 108	java/util/HashSet:<init>	()V
    //   168: putfield 115	com/cootek/usage/g$b:e	Ljava/util/HashSet;
    //   171: aload_3
    //   172: astore 4
    //   174: aload 11
    //   176: new 107	java/util/HashSet
    //   179: dup
    //   180: invokespecial 108	java/util/HashSet:<init>	()V
    //   183: putfield 117	com/cootek/usage/g$b:f	Ljava/util/HashSet;
    //   186: aload_3
    //   187: astore 4
    //   189: aload 11
    //   191: new 107	java/util/HashSet
    //   194: dup
    //   195: invokespecial 108	java/util/HashSet:<init>	()V
    //   198: putfield 119	com/cootek/usage/g$b:g	Ljava/util/HashSet;
    //   201: aload_3
    //   202: astore 4
    //   204: aload 11
    //   206: new 107	java/util/HashSet
    //   209: dup
    //   210: invokespecial 108	java/util/HashSet:<init>	()V
    //   213: putfield 121	com/cootek/usage/g$b:h	Ljava/util/HashSet;
    //   216: aload_3
    //   217: astore 4
    //   219: aload 11
    //   221: new 107	java/util/HashSet
    //   224: dup
    //   225: invokespecial 108	java/util/HashSet:<init>	()V
    //   228: putfield 124	com/cootek/usage/g$b:i	Ljava/util/HashSet;
    //   231: aload_3
    //   232: astore 4
    //   234: aload 9
    //   236: aload 11
    //   238: getfield 98	com/cootek/usage/g$b:a	J
    //   241: invokestatic 130	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   244: aload 11
    //   246: invokevirtual 134	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   249: pop
    //   250: aload_3
    //   251: astore 4
    //   253: aload_3
    //   254: invokeinterface 137 1 0
    //   259: istore_2
    //   260: iload_2
    //   261: ifne -179 -> 82
    //   264: aload_3
    //   265: ifnull +9 -> 274
    //   268: aload_3
    //   269: invokeinterface 140 1 0
    //   274: aload 7
    //   276: astore 4
    //   278: aload 5
    //   280: astore_3
    //   281: aload 10
    //   283: getstatic 143	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   286: bipush 12
    //   288: anewarray 72	java/lang/String
    //   291: dup
    //   292: iconst_0
    //   293: ldc -111
    //   295: aastore
    //   296: dup
    //   297: iconst_1
    //   298: ldc -109
    //   300: aastore
    //   301: dup
    //   302: iconst_2
    //   303: ldc -107
    //   305: aastore
    //   306: dup
    //   307: iconst_3
    //   308: ldc -105
    //   310: aastore
    //   311: dup
    //   312: iconst_4
    //   313: ldc -103
    //   315: aastore
    //   316: dup
    //   317: iconst_5
    //   318: ldc -101
    //   320: aastore
    //   321: dup
    //   322: bipush 6
    //   324: ldc -99
    //   326: aastore
    //   327: dup
    //   328: bipush 7
    //   330: ldc -97
    //   332: aastore
    //   333: dup
    //   334: bipush 8
    //   336: ldc -95
    //   338: aastore
    //   339: dup
    //   340: bipush 9
    //   342: ldc -93
    //   344: aastore
    //   345: dup
    //   346: bipush 10
    //   348: ldc -91
    //   350: aastore
    //   351: dup
    //   352: bipush 11
    //   354: ldc -89
    //   356: aastore
    //   357: ldc -87
    //   359: bipush 7
    //   361: anewarray 72	java/lang/String
    //   364: dup
    //   365: iconst_0
    //   366: ldc -85
    //   368: aastore
    //   369: dup
    //   370: iconst_1
    //   371: ldc -83
    //   373: aastore
    //   374: dup
    //   375: iconst_2
    //   376: ldc -81
    //   378: aastore
    //   379: dup
    //   380: iconst_3
    //   381: ldc -79
    //   383: aastore
    //   384: dup
    //   385: iconst_4
    //   386: ldc -77
    //   388: aastore
    //   389: dup
    //   390: iconst_5
    //   391: ldc -75
    //   393: aastore
    //   394: dup
    //   395: bipush 6
    //   397: ldc -73
    //   399: aastore
    //   400: aconst_null
    //   401: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   404: astore 5
    //   406: aload 5
    //   408: ifnull +82 -> 490
    //   411: aload 5
    //   413: astore 4
    //   415: aload 5
    //   417: astore_3
    //   418: aload 5
    //   420: astore 6
    //   422: aload 5
    //   424: invokeinterface 88 1 0
    //   429: istore_2
    //   430: iload_2
    //   431: ifeq +59 -> 490
    //   434: aload 5
    //   436: astore 4
    //   438: aload 5
    //   440: astore_3
    //   441: aload 9
    //   443: aload 5
    //   445: iconst_0
    //   446: invokeinterface 95 2 0
    //   451: invokestatic 130	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   454: invokevirtual 187	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   457: checkcast 11	com/cootek/usage/g$b
    //   460: astore 6
    //   462: aload 6
    //   464: ifnonnull +292 -> 756
    //   467: aload 5
    //   469: astore 4
    //   471: aload 5
    //   473: astore_3
    //   474: aload 5
    //   476: astore 6
    //   478: aload 5
    //   480: invokeinterface 137 1 0
    //   485: istore_2
    //   486: iload_2
    //   487: ifne -53 -> 434
    //   490: aload 5
    //   492: ifnull +10 -> 502
    //   495: aload 5
    //   497: invokeinterface 140 1 0
    //   502: new 189	org/json/JSONArray
    //   505: dup
    //   506: invokespecial 190	org/json/JSONArray:<init>	()V
    //   509: astore_3
    //   510: aload 9
    //   512: invokevirtual 194	java/util/Hashtable:values	()Ljava/util/Collection;
    //   515: invokeinterface 200 1 0
    //   520: astore 4
    //   522: aload 4
    //   524: invokeinterface 205 1 0
    //   529: ifeq +2292 -> 2821
    //   532: aload 4
    //   534: invokeinterface 209 1 0
    //   539: checkcast 11	com/cootek/usage/g$b
    //   542: astore 6
    //   544: new 211	org/json/JSONObject
    //   547: dup
    //   548: invokespecial 212	org/json/JSONObject:<init>	()V
    //   551: astore 5
    //   553: aload 5
    //   555: ldc -42
    //   557: aload 6
    //   559: getfield 105	com/cootek/usage/g$b:b	Ljava/lang/String;
    //   562: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   565: pop
    //   566: aload 6
    //   568: getfield 111	com/cootek/usage/g$b:c	Ljava/util/HashSet;
    //   571: invokevirtual 220	java/util/HashSet:isEmpty	()Z
    //   574: ifne +1312 -> 1886
    //   577: new 189	org/json/JSONArray
    //   580: dup
    //   581: invokespecial 190	org/json/JSONArray:<init>	()V
    //   584: astore 7
    //   586: aload 6
    //   588: getfield 111	com/cootek/usage/g$b:c	Ljava/util/HashSet;
    //   591: invokevirtual 221	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   594: astore 9
    //   596: aload 9
    //   598: invokeinterface 205 1 0
    //   603: ifeq +1264 -> 1867
    //   606: aload 7
    //   608: aload 9
    //   610: invokeinterface 209 1 0
    //   615: checkcast 72	java/lang/String
    //   618: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   621: pop
    //   622: goto -26 -> 596
    //   625: astore 6
    //   627: aload 6
    //   629: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   632: aload_3
    //   633: aload 5
    //   635: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   638: pop
    //   639: aload 8
    //   641: iconst_1
    //   642: putfield 232	com/cootek/usage/g$f:d	Z
    //   645: goto -123 -> 522
    //   648: astore_3
    //   649: aload_3
    //   650: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   653: goto -379 -> 274
    //   656: astore_3
    //   657: aconst_null
    //   658: astore_3
    //   659: aload 8
    //   661: iconst_0
    //   662: putfield 232	com/cootek/usage/g$f:d	Z
    //   665: aload_3
    //   666: ifnull +9 -> 675
    //   669: aload_3
    //   670: invokeinterface 140 1 0
    //   675: aload 8
    //   677: areturn
    //   678: astore_3
    //   679: aload_3
    //   680: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   683: goto -8 -> 675
    //   686: astore 5
    //   688: aconst_null
    //   689: astore_3
    //   690: aload_3
    //   691: astore 4
    //   693: aload 5
    //   695: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   698: aload_3
    //   699: astore 4
    //   701: aload 8
    //   703: iconst_0
    //   704: putfield 232	com/cootek/usage/g$f:d	Z
    //   707: aload_3
    //   708: ifnull +9 -> 717
    //   711: aload_3
    //   712: invokeinterface 140 1 0
    //   717: aload 8
    //   719: areturn
    //   720: astore_3
    //   721: aload_3
    //   722: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   725: goto -8 -> 717
    //   728: astore_3
    //   729: aconst_null
    //   730: astore 4
    //   732: aload 4
    //   734: ifnull +10 -> 744
    //   737: aload 4
    //   739: invokeinterface 140 1 0
    //   744: aload_3
    //   745: athrow
    //   746: astore 4
    //   748: aload 4
    //   750: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   753: goto -9 -> 744
    //   756: aload 5
    //   758: astore 4
    //   760: aload 5
    //   762: astore_3
    //   763: aload 5
    //   765: iconst_1
    //   766: invokeinterface 102 2 0
    //   771: astore 7
    //   773: aload 5
    //   775: astore 4
    //   777: aload 5
    //   779: astore_3
    //   780: ldc -85
    //   782: aload 7
    //   784: invokevirtual 236	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   787: ifeq +87 -> 874
    //   790: aload 5
    //   792: astore 4
    //   794: aload 5
    //   796: astore_3
    //   797: aload 5
    //   799: iconst_2
    //   800: invokeinterface 102 2 0
    //   805: astore 7
    //   807: aload 5
    //   809: astore 4
    //   811: aload 5
    //   813: astore_3
    //   814: aload 6
    //   816: getfield 111	com/cootek/usage/g$b:c	Ljava/util/HashSet;
    //   819: aload 7
    //   821: invokevirtual 239	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   824: pop
    //   825: goto -358 -> 467
    //   828: astore 7
    //   830: aload 5
    //   832: astore 4
    //   834: aload 5
    //   836: astore_3
    //   837: aload 5
    //   839: astore 6
    //   841: aload 7
    //   843: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   846: goto -379 -> 467
    //   849: astore_3
    //   850: aload 4
    //   852: astore_3
    //   853: aload 8
    //   855: iconst_0
    //   856: putfield 232	com/cootek/usage/g$f:d	Z
    //   859: aload 4
    //   861: ifnull +10 -> 871
    //   864: aload 4
    //   866: invokeinterface 140 1 0
    //   871: aload 8
    //   873: areturn
    //   874: aload 5
    //   876: astore 4
    //   878: aload 5
    //   880: astore_3
    //   881: ldc -83
    //   883: aload 7
    //   885: invokevirtual 236	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   888: ifeq +136 -> 1024
    //   891: aload 5
    //   893: astore 4
    //   895: aload 5
    //   897: astore_3
    //   898: new 14	com/cootek/usage/g$c
    //   901: dup
    //   902: aload_0
    //   903: aconst_null
    //   904: invokespecial 240	com/cootek/usage/g$c:<init>	(Lcom/cootek/usage/g;Lcom/cootek/usage/g$1;)V
    //   907: astore 7
    //   909: aload 5
    //   911: astore 4
    //   913: aload 5
    //   915: astore_3
    //   916: aload 7
    //   918: aload 5
    //   920: iconst_2
    //   921: invokeinterface 102 2 0
    //   926: putfield 242	com/cootek/usage/g$c:a	Ljava/lang/String;
    //   929: aload 5
    //   931: astore 4
    //   933: aload 5
    //   935: astore_3
    //   936: aload 7
    //   938: aload_0
    //   939: aload 5
    //   941: iconst_3
    //   942: invokeinterface 246 2 0
    //   947: invokespecial 248	com/cootek/usage/g:c	(I)Ljava/lang/String;
    //   950: putfield 249	com/cootek/usage/g$c:b	Ljava/lang/String;
    //   953: aload 5
    //   955: astore 4
    //   957: aload 5
    //   959: astore_3
    //   960: aload 7
    //   962: getfield 249	com/cootek/usage/g$c:b	Ljava/lang/String;
    //   965: ifnonnull +23 -> 988
    //   968: aload 5
    //   970: astore 4
    //   972: aload 5
    //   974: astore_3
    //   975: aload 7
    //   977: aload 5
    //   979: iconst_4
    //   980: invokeinterface 102 2 0
    //   985: putfield 249	com/cootek/usage/g$c:b	Ljava/lang/String;
    //   988: aload 5
    //   990: astore 4
    //   992: aload 5
    //   994: astore_3
    //   995: aload 6
    //   997: getfield 113	com/cootek/usage/g$b:d	Ljava/util/HashSet;
    //   1000: aload 7
    //   1002: invokevirtual 239	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1005: pop
    //   1006: goto -539 -> 467
    //   1009: astore 4
    //   1011: aload_3
    //   1012: ifnull +9 -> 1021
    //   1015: aload_3
    //   1016: invokeinterface 140 1 0
    //   1021: aload 4
    //   1023: athrow
    //   1024: aload 5
    //   1026: astore 4
    //   1028: aload 5
    //   1030: astore_3
    //   1031: ldc -81
    //   1033: aload 7
    //   1035: invokevirtual 236	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1038: ifeq +162 -> 1200
    //   1041: aload 5
    //   1043: astore 4
    //   1045: aload 5
    //   1047: astore_3
    //   1048: new 26	com/cootek/usage/g$g
    //   1051: dup
    //   1052: aload_0
    //   1053: aconst_null
    //   1054: invokespecial 250	com/cootek/usage/g$g:<init>	(Lcom/cootek/usage/g;Lcom/cootek/usage/g$1;)V
    //   1057: astore 7
    //   1059: aload 5
    //   1061: astore 4
    //   1063: aload 5
    //   1065: astore_3
    //   1066: aload 7
    //   1068: aload 5
    //   1070: iconst_2
    //   1071: invokeinterface 102 2 0
    //   1076: putfield 251	com/cootek/usage/g$g:b	Ljava/lang/String;
    //   1079: aload 5
    //   1081: astore 4
    //   1083: aload 5
    //   1085: astore_3
    //   1086: aload 7
    //   1088: aload 5
    //   1090: iconst_5
    //   1091: invokeinterface 102 2 0
    //   1096: putfield 253	com/cootek/usage/g$g:c	Ljava/lang/String;
    //   1099: aload 5
    //   1101: astore 4
    //   1103: aload 5
    //   1105: astore_3
    //   1106: aload 7
    //   1108: aload 5
    //   1110: bipush 6
    //   1112: invokeinterface 102 2 0
    //   1117: putfield 255	com/cootek/usage/g$g:d	Ljava/lang/String;
    //   1120: aload 5
    //   1122: astore 4
    //   1124: aload 5
    //   1126: astore_3
    //   1127: aload 7
    //   1129: aload_0
    //   1130: aload 5
    //   1132: iconst_3
    //   1133: invokeinterface 246 2 0
    //   1138: invokespecial 257	com/cootek/usage/g:d	(I)Ljava/lang/String;
    //   1141: putfield 258	com/cootek/usage/g$g:a	Ljava/lang/String;
    //   1144: aload 5
    //   1146: astore 4
    //   1148: aload 5
    //   1150: astore_3
    //   1151: aload 7
    //   1153: getfield 258	com/cootek/usage/g$g:a	Ljava/lang/String;
    //   1156: ifnonnull +23 -> 1179
    //   1159: aload 5
    //   1161: astore 4
    //   1163: aload 5
    //   1165: astore_3
    //   1166: aload 7
    //   1168: aload 5
    //   1170: iconst_4
    //   1171: invokeinterface 102 2 0
    //   1176: putfield 258	com/cootek/usage/g$g:a	Ljava/lang/String;
    //   1179: aload 5
    //   1181: astore 4
    //   1183: aload 5
    //   1185: astore_3
    //   1186: aload 6
    //   1188: getfield 115	com/cootek/usage/g$b:e	Ljava/util/HashSet;
    //   1191: aload 7
    //   1193: invokevirtual 239	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1196: pop
    //   1197: goto -730 -> 467
    //   1200: aload 5
    //   1202: astore 4
    //   1204: aload 5
    //   1206: astore_3
    //   1207: ldc -79
    //   1209: aload 7
    //   1211: invokevirtual 236	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1214: ifeq +182 -> 1396
    //   1217: aload 5
    //   1219: astore 4
    //   1221: aload 5
    //   1223: astore_3
    //   1224: new 20	com/cootek/usage/g$e
    //   1227: dup
    //   1228: aload_0
    //   1229: aconst_null
    //   1230: invokespecial 259	com/cootek/usage/g$e:<init>	(Lcom/cootek/usage/g;Lcom/cootek/usage/g$1;)V
    //   1233: astore 7
    //   1235: aload 5
    //   1237: astore 4
    //   1239: aload 5
    //   1241: astore_3
    //   1242: aload 7
    //   1244: aload 5
    //   1246: iconst_2
    //   1247: invokeinterface 102 2 0
    //   1252: putfield 260	com/cootek/usage/g$e:a	Ljava/lang/String;
    //   1255: aload 5
    //   1257: astore 4
    //   1259: aload 5
    //   1261: astore_3
    //   1262: aload 7
    //   1264: aload_0
    //   1265: aload 5
    //   1267: iconst_3
    //   1268: invokeinterface 246 2 0
    //   1273: invokespecial 262	com/cootek/usage/g:e	(I)Ljava/lang/String;
    //   1276: putfield 263	com/cootek/usage/g$e:b	Ljava/lang/String;
    //   1279: aload 5
    //   1281: astore 4
    //   1283: aload 5
    //   1285: astore_3
    //   1286: aload 7
    //   1288: getfield 263	com/cootek/usage/g$e:b	Ljava/lang/String;
    //   1291: ifnonnull +23 -> 1314
    //   1294: aload 5
    //   1296: astore 4
    //   1298: aload 5
    //   1300: astore_3
    //   1301: aload 7
    //   1303: aload 5
    //   1305: iconst_4
    //   1306: invokeinterface 102 2 0
    //   1311: putfield 263	com/cootek/usage/g$e:b	Ljava/lang/String;
    //   1314: aload 5
    //   1316: astore 4
    //   1318: aload 5
    //   1320: astore_3
    //   1321: aload 7
    //   1323: aload_0
    //   1324: aload 5
    //   1326: bipush 6
    //   1328: invokeinterface 246 2 0
    //   1333: invokespecial 265	com/cootek/usage/g:g	(I)Ljava/lang/String;
    //   1336: putfield 266	com/cootek/usage/g$e:c	Ljava/lang/String;
    //   1339: aload 5
    //   1341: astore 4
    //   1343: aload 5
    //   1345: astore_3
    //   1346: aload 7
    //   1348: getfield 266	com/cootek/usage/g$e:c	Ljava/lang/String;
    //   1351: ifnonnull +24 -> 1375
    //   1354: aload 5
    //   1356: astore 4
    //   1358: aload 5
    //   1360: astore_3
    //   1361: aload 7
    //   1363: aload 5
    //   1365: bipush 7
    //   1367: invokeinterface 102 2 0
    //   1372: putfield 266	com/cootek/usage/g$e:c	Ljava/lang/String;
    //   1375: aload 5
    //   1377: astore 4
    //   1379: aload 5
    //   1381: astore_3
    //   1382: aload 6
    //   1384: getfield 117	com/cootek/usage/g$b:f	Ljava/util/HashSet;
    //   1387: aload 7
    //   1389: invokevirtual 239	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1392: pop
    //   1393: goto -926 -> 467
    //   1396: aload 5
    //   1398: astore 4
    //   1400: aload 5
    //   1402: astore_3
    //   1403: ldc -77
    //   1405: aload 7
    //   1407: invokevirtual 236	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1410: ifeq +121 -> 1531
    //   1413: aload 5
    //   1415: astore 4
    //   1417: aload 5
    //   1419: astore_3
    //   1420: new 8	com/cootek/usage/g$a
    //   1423: dup
    //   1424: aload_0
    //   1425: aconst_null
    //   1426: invokespecial 267	com/cootek/usage/g$a:<init>	(Lcom/cootek/usage/g;Lcom/cootek/usage/g$1;)V
    //   1429: astore 7
    //   1431: aload 5
    //   1433: astore 4
    //   1435: aload 5
    //   1437: astore_3
    //   1438: aload 7
    //   1440: aload 5
    //   1442: iconst_2
    //   1443: invokeinterface 102 2 0
    //   1448: putfield 268	com/cootek/usage/g$a:a	Ljava/lang/String;
    //   1451: aload 5
    //   1453: astore 4
    //   1455: aload 5
    //   1457: astore_3
    //   1458: aload 7
    //   1460: aload_0
    //   1461: aload 5
    //   1463: iconst_3
    //   1464: invokeinterface 246 2 0
    //   1469: invokespecial 270	com/cootek/usage/g:f	(I)Ljava/lang/String;
    //   1472: putfield 271	com/cootek/usage/g$a:b	Ljava/lang/String;
    //   1475: aload 5
    //   1477: astore 4
    //   1479: aload 5
    //   1481: astore_3
    //   1482: aload 7
    //   1484: getfield 271	com/cootek/usage/g$a:b	Ljava/lang/String;
    //   1487: ifnonnull +23 -> 1510
    //   1490: aload 5
    //   1492: astore 4
    //   1494: aload 5
    //   1496: astore_3
    //   1497: aload 7
    //   1499: aload 5
    //   1501: iconst_4
    //   1502: invokeinterface 102 2 0
    //   1507: putfield 271	com/cootek/usage/g$a:b	Ljava/lang/String;
    //   1510: aload 5
    //   1512: astore 4
    //   1514: aload 5
    //   1516: astore_3
    //   1517: aload 6
    //   1519: getfield 119	com/cootek/usage/g$b:g	Ljava/util/HashSet;
    //   1522: aload 7
    //   1524: invokevirtual 239	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1527: pop
    //   1528: goto -1061 -> 467
    //   1531: aload 5
    //   1533: astore 4
    //   1535: aload 5
    //   1537: astore_3
    //   1538: ldc -75
    //   1540: aload 7
    //   1542: invokevirtual 236	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1545: ifeq +121 -> 1666
    //   1548: aload 5
    //   1550: astore 4
    //   1552: aload 5
    //   1554: astore_3
    //   1555: new 17	com/cootek/usage/g$d
    //   1558: dup
    //   1559: aload_0
    //   1560: aconst_null
    //   1561: invokespecial 272	com/cootek/usage/g$d:<init>	(Lcom/cootek/usage/g;Lcom/cootek/usage/g$1;)V
    //   1564: astore 7
    //   1566: aload 5
    //   1568: astore 4
    //   1570: aload 5
    //   1572: astore_3
    //   1573: aload 7
    //   1575: aload 5
    //   1577: iconst_2
    //   1578: invokeinterface 102 2 0
    //   1583: putfield 273	com/cootek/usage/g$d:a	Ljava/lang/String;
    //   1586: aload 5
    //   1588: astore 4
    //   1590: aload 5
    //   1592: astore_3
    //   1593: aload 7
    //   1595: aload_0
    //   1596: aload 5
    //   1598: iconst_3
    //   1599: invokeinterface 246 2 0
    //   1604: invokespecial 275	com/cootek/usage/g:h	(I)Ljava/lang/String;
    //   1607: putfield 276	com/cootek/usage/g$d:b	Ljava/lang/String;
    //   1610: aload 5
    //   1612: astore 4
    //   1614: aload 5
    //   1616: astore_3
    //   1617: aload 7
    //   1619: getfield 276	com/cootek/usage/g$d:b	Ljava/lang/String;
    //   1622: ifnonnull +23 -> 1645
    //   1625: aload 5
    //   1627: astore 4
    //   1629: aload 5
    //   1631: astore_3
    //   1632: aload 7
    //   1634: aload 5
    //   1636: iconst_4
    //   1637: invokeinterface 102 2 0
    //   1642: putfield 276	com/cootek/usage/g$d:b	Ljava/lang/String;
    //   1645: aload 5
    //   1647: astore 4
    //   1649: aload 5
    //   1651: astore_3
    //   1652: aload 6
    //   1654: getfield 121	com/cootek/usage/g$b:h	Ljava/util/HashSet;
    //   1657: aload 7
    //   1659: invokevirtual 239	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1662: pop
    //   1663: goto -1196 -> 467
    //   1666: aload 5
    //   1668: astore 4
    //   1670: aload 5
    //   1672: astore_3
    //   1673: ldc -73
    //   1675: aload 7
    //   1677: invokevirtual 236	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1680: ifeq -1213 -> 467
    //   1683: aload 5
    //   1685: astore 4
    //   1687: aload 5
    //   1689: astore_3
    //   1690: new 29	com/cootek/usage/g$h
    //   1693: dup
    //   1694: aload_0
    //   1695: aconst_null
    //   1696: invokespecial 277	com/cootek/usage/g$h:<init>	(Lcom/cootek/usage/g;Lcom/cootek/usage/g$1;)V
    //   1699: astore 7
    //   1701: aload 5
    //   1703: astore 4
    //   1705: aload 5
    //   1707: astore_3
    //   1708: aload 7
    //   1710: aload 5
    //   1712: iconst_2
    //   1713: invokeinterface 102 2 0
    //   1718: putfield 278	com/cootek/usage/g$h:a	Ljava/lang/String;
    //   1721: aload 5
    //   1723: astore 4
    //   1725: aload 5
    //   1727: astore_3
    //   1728: aload 7
    //   1730: aload_0
    //   1731: aload 5
    //   1733: iconst_3
    //   1734: invokeinterface 246 2 0
    //   1739: invokespecial 280	com/cootek/usage/g:i	(I)Ljava/lang/String;
    //   1742: putfield 281	com/cootek/usage/g$h:b	Ljava/lang/String;
    //   1745: aload 5
    //   1747: astore 4
    //   1749: aload 5
    //   1751: astore_3
    //   1752: aload 7
    //   1754: getfield 281	com/cootek/usage/g$h:b	Ljava/lang/String;
    //   1757: ifnonnull +23 -> 1780
    //   1760: aload 5
    //   1762: astore 4
    //   1764: aload 5
    //   1766: astore_3
    //   1767: aload 7
    //   1769: aload 5
    //   1771: iconst_4
    //   1772: invokeinterface 102 2 0
    //   1777: putfield 281	com/cootek/usage/g$h:b	Ljava/lang/String;
    //   1780: aload 5
    //   1782: astore 4
    //   1784: aload 5
    //   1786: astore_3
    //   1787: aload 6
    //   1789: getfield 124	com/cootek/usage/g$b:i	Ljava/util/HashSet;
    //   1792: aload 7
    //   1794: invokevirtual 239	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1797: pop
    //   1798: goto -1331 -> 467
    //   1801: astore_3
    //   1802: aload_3
    //   1803: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   1806: goto -1304 -> 502
    //   1809: astore_3
    //   1810: aload_3
    //   1811: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   1814: goto -943 -> 871
    //   1817: astore 4
    //   1819: aload 6
    //   1821: astore_3
    //   1822: aload 4
    //   1824: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   1827: aload 6
    //   1829: astore_3
    //   1830: aload 8
    //   1832: iconst_0
    //   1833: putfield 232	com/cootek/usage/g$f:d	Z
    //   1836: aload 6
    //   1838: ifnull +10 -> 1848
    //   1841: aload 6
    //   1843: invokeinterface 140 1 0
    //   1848: aload 8
    //   1850: areturn
    //   1851: astore_3
    //   1852: aload_3
    //   1853: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   1856: goto -8 -> 1848
    //   1859: astore_3
    //   1860: aload_3
    //   1861: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   1864: goto -843 -> 1021
    //   1867: aload 7
    //   1869: invokevirtual 285	org/json/JSONArray:length	()I
    //   1872: ifle +14 -> 1886
    //   1875: aload 5
    //   1877: ldc_w 287
    //   1880: aload 7
    //   1882: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1885: pop
    //   1886: aload 6
    //   1888: getfield 113	com/cootek/usage/g$b:d	Ljava/util/HashSet;
    //   1891: invokevirtual 220	java/util/HashSet:isEmpty	()Z
    //   1894: ifne +133 -> 2027
    //   1897: new 189	org/json/JSONArray
    //   1900: dup
    //   1901: invokespecial 190	org/json/JSONArray:<init>	()V
    //   1904: astore 7
    //   1906: aload 6
    //   1908: getfield 113	com/cootek/usage/g$b:d	Ljava/util/HashSet;
    //   1911: invokevirtual 221	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1914: astore 9
    //   1916: aload 9
    //   1918: invokeinterface 205 1 0
    //   1923: ifeq +85 -> 2008
    //   1926: aload 9
    //   1928: invokeinterface 209 1 0
    //   1933: checkcast 14	com/cootek/usage/g$c
    //   1936: astore 10
    //   1938: new 211	org/json/JSONObject
    //   1941: dup
    //   1942: invokespecial 212	org/json/JSONObject:<init>	()V
    //   1945: astore 11
    //   1947: aload 10
    //   1949: getfield 242	com/cootek/usage/g$c:a	Ljava/lang/String;
    //   1952: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1955: ifne -39 -> 1916
    //   1958: aload 11
    //   1960: ldc_w 294
    //   1963: aload 10
    //   1965: getfield 242	com/cootek/usage/g$c:a	Ljava/lang/String;
    //   1968: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1971: pop
    //   1972: aload 10
    //   1974: getfield 249	com/cootek/usage/g$c:b	Ljava/lang/String;
    //   1977: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1980: ifne +17 -> 1997
    //   1983: aload 11
    //   1985: ldc_w 296
    //   1988: aload 10
    //   1990: getfield 249	com/cootek/usage/g$c:b	Ljava/lang/String;
    //   1993: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1996: pop
    //   1997: aload 7
    //   1999: aload 11
    //   2001: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2004: pop
    //   2005: goto -89 -> 1916
    //   2008: aload 7
    //   2010: invokevirtual 285	org/json/JSONArray:length	()I
    //   2013: ifle +14 -> 2027
    //   2016: aload 5
    //   2018: ldc_w 298
    //   2021: aload 7
    //   2023: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2026: pop
    //   2027: aload 6
    //   2029: getfield 115	com/cootek/usage/g$b:e	Ljava/util/HashSet;
    //   2032: invokevirtual 220	java/util/HashSet:isEmpty	()Z
    //   2035: ifne +195 -> 2230
    //   2038: new 189	org/json/JSONArray
    //   2041: dup
    //   2042: invokespecial 190	org/json/JSONArray:<init>	()V
    //   2045: astore 7
    //   2047: aload 6
    //   2049: getfield 115	com/cootek/usage/g$b:e	Ljava/util/HashSet;
    //   2052: invokevirtual 221	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2055: astore 9
    //   2057: aload 9
    //   2059: invokeinterface 205 1 0
    //   2064: ifeq +147 -> 2211
    //   2067: aload 9
    //   2069: invokeinterface 209 1 0
    //   2074: checkcast 26	com/cootek/usage/g$g
    //   2077: astore 10
    //   2079: new 211	org/json/JSONObject
    //   2082: dup
    //   2083: invokespecial 212	org/json/JSONObject:<init>	()V
    //   2086: astore 11
    //   2088: aload 10
    //   2090: getfield 258	com/cootek/usage/g$g:a	Ljava/lang/String;
    //   2093: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2096: ifne +893 -> 2989
    //   2099: aload 11
    //   2101: ldc_w 296
    //   2104: aload 10
    //   2106: getfield 258	com/cootek/usage/g$g:a	Ljava/lang/String;
    //   2109: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2112: pop
    //   2113: iconst_1
    //   2114: istore_1
    //   2115: aload 10
    //   2117: getfield 258	com/cootek/usage/g$g:a	Ljava/lang/String;
    //   2120: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2123: ifne +19 -> 2142
    //   2126: aload 11
    //   2128: ldc_w 300
    //   2131: aload 10
    //   2133: getfield 251	com/cootek/usage/g$g:b	Ljava/lang/String;
    //   2136: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2139: pop
    //   2140: iconst_1
    //   2141: istore_1
    //   2142: aload 10
    //   2144: getfield 258	com/cootek/usage/g$g:a	Ljava/lang/String;
    //   2147: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2150: ifne +19 -> 2169
    //   2153: aload 11
    //   2155: ldc_w 302
    //   2158: aload 10
    //   2160: getfield 253	com/cootek/usage/g$g:c	Ljava/lang/String;
    //   2163: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2166: pop
    //   2167: iconst_1
    //   2168: istore_1
    //   2169: aload 10
    //   2171: getfield 258	com/cootek/usage/g$g:a	Ljava/lang/String;
    //   2174: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2177: ifne +809 -> 2986
    //   2180: aload 11
    //   2182: ldc_w 304
    //   2185: aload 10
    //   2187: getfield 255	com/cootek/usage/g$g:d	Ljava/lang/String;
    //   2190: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2193: pop
    //   2194: iconst_1
    //   2195: istore_1
    //   2196: iload_1
    //   2197: ifeq -140 -> 2057
    //   2200: aload 7
    //   2202: aload 11
    //   2204: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2207: pop
    //   2208: goto -151 -> 2057
    //   2211: aload 7
    //   2213: invokevirtual 285	org/json/JSONArray:length	()I
    //   2216: ifle +14 -> 2230
    //   2219: aload 5
    //   2221: ldc_w 306
    //   2224: aload 7
    //   2226: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2229: pop
    //   2230: aload 6
    //   2232: getfield 117	com/cootek/usage/g$b:f	Ljava/util/HashSet;
    //   2235: invokevirtual 220	java/util/HashSet:isEmpty	()Z
    //   2238: ifne +158 -> 2396
    //   2241: new 189	org/json/JSONArray
    //   2244: dup
    //   2245: invokespecial 190	org/json/JSONArray:<init>	()V
    //   2248: astore 7
    //   2250: aload 6
    //   2252: getfield 117	com/cootek/usage/g$b:f	Ljava/util/HashSet;
    //   2255: invokevirtual 221	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2258: astore 9
    //   2260: aload 9
    //   2262: invokeinterface 205 1 0
    //   2267: ifeq +118 -> 2385
    //   2270: aload 9
    //   2272: invokeinterface 209 1 0
    //   2277: checkcast 20	com/cootek/usage/g$e
    //   2280: astore 10
    //   2282: new 211	org/json/JSONObject
    //   2285: dup
    //   2286: invokespecial 212	org/json/JSONObject:<init>	()V
    //   2289: astore 11
    //   2291: aload 10
    //   2293: getfield 260	com/cootek/usage/g$e:a	Ljava/lang/String;
    //   2296: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2299: ifne -39 -> 2260
    //   2302: aload 11
    //   2304: ldc_w 308
    //   2307: aload 10
    //   2309: getfield 260	com/cootek/usage/g$e:a	Ljava/lang/String;
    //   2312: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2315: pop
    //   2316: aload 10
    //   2318: getfield 263	com/cootek/usage/g$e:b	Ljava/lang/String;
    //   2321: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2324: ifne +17 -> 2341
    //   2327: aload 11
    //   2329: ldc_w 296
    //   2332: aload 10
    //   2334: getfield 263	com/cootek/usage/g$e:b	Ljava/lang/String;
    //   2337: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2340: pop
    //   2341: aload 10
    //   2343: getfield 266	com/cootek/usage/g$e:c	Ljava/lang/String;
    //   2346: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2349: ifne +17 -> 2366
    //   2352: aload 11
    //   2354: ldc_w 310
    //   2357: aload 10
    //   2359: getfield 266	com/cootek/usage/g$e:c	Ljava/lang/String;
    //   2362: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2365: pop
    //   2366: aload 7
    //   2368: invokevirtual 285	org/json/JSONArray:length	()I
    //   2371: ifle -111 -> 2260
    //   2374: aload 7
    //   2376: aload 11
    //   2378: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2381: pop
    //   2382: goto -122 -> 2260
    //   2385: aload 5
    //   2387: ldc_w 312
    //   2390: aload 7
    //   2392: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2395: pop
    //   2396: aload 6
    //   2398: getfield 119	com/cootek/usage/g$b:g	Ljava/util/HashSet;
    //   2401: invokevirtual 220	java/util/HashSet:isEmpty	()Z
    //   2404: ifne +133 -> 2537
    //   2407: new 189	org/json/JSONArray
    //   2410: dup
    //   2411: invokespecial 190	org/json/JSONArray:<init>	()V
    //   2414: astore 7
    //   2416: aload 6
    //   2418: getfield 119	com/cootek/usage/g$b:g	Ljava/util/HashSet;
    //   2421: invokevirtual 221	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2424: astore 9
    //   2426: aload 9
    //   2428: invokeinterface 205 1 0
    //   2433: ifeq +85 -> 2518
    //   2436: aload 9
    //   2438: invokeinterface 209 1 0
    //   2443: checkcast 8	com/cootek/usage/g$a
    //   2446: astore 10
    //   2448: new 211	org/json/JSONObject
    //   2451: dup
    //   2452: invokespecial 212	org/json/JSONObject:<init>	()V
    //   2455: astore 11
    //   2457: aload 10
    //   2459: getfield 268	com/cootek/usage/g$a:a	Ljava/lang/String;
    //   2462: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2465: ifne -39 -> 2426
    //   2468: aload 11
    //   2470: ldc_w 314
    //   2473: aload 10
    //   2475: getfield 268	com/cootek/usage/g$a:a	Ljava/lang/String;
    //   2478: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2481: pop
    //   2482: aload 10
    //   2484: getfield 271	com/cootek/usage/g$a:b	Ljava/lang/String;
    //   2487: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2490: ifne +17 -> 2507
    //   2493: aload 11
    //   2495: ldc_w 296
    //   2498: aload 10
    //   2500: getfield 271	com/cootek/usage/g$a:b	Ljava/lang/String;
    //   2503: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2506: pop
    //   2507: aload 7
    //   2509: aload 11
    //   2511: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2514: pop
    //   2515: goto -89 -> 2426
    //   2518: aload 7
    //   2520: invokevirtual 285	org/json/JSONArray:length	()I
    //   2523: ifle +14 -> 2537
    //   2526: aload 5
    //   2528: ldc_w 294
    //   2531: aload 7
    //   2533: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2536: pop
    //   2537: aload 6
    //   2539: getfield 121	com/cootek/usage/g$b:h	Ljava/util/HashSet;
    //   2542: invokevirtual 220	java/util/HashSet:isEmpty	()Z
    //   2545: ifne +133 -> 2678
    //   2548: new 189	org/json/JSONArray
    //   2551: dup
    //   2552: invokespecial 190	org/json/JSONArray:<init>	()V
    //   2555: astore 7
    //   2557: aload 6
    //   2559: getfield 121	com/cootek/usage/g$b:h	Ljava/util/HashSet;
    //   2562: invokevirtual 221	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2565: astore 9
    //   2567: aload 9
    //   2569: invokeinterface 205 1 0
    //   2574: ifeq +85 -> 2659
    //   2577: aload 9
    //   2579: invokeinterface 209 1 0
    //   2584: checkcast 17	com/cootek/usage/g$d
    //   2587: astore 10
    //   2589: new 211	org/json/JSONObject
    //   2592: dup
    //   2593: invokespecial 212	org/json/JSONObject:<init>	()V
    //   2596: astore 11
    //   2598: aload 10
    //   2600: getfield 273	com/cootek/usage/g$d:a	Ljava/lang/String;
    //   2603: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2606: ifne -39 -> 2567
    //   2609: aload 11
    //   2611: ldc_w 316
    //   2614: aload 10
    //   2616: getfield 273	com/cootek/usage/g$d:a	Ljava/lang/String;
    //   2619: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2622: pop
    //   2623: aload 10
    //   2625: getfield 276	com/cootek/usage/g$d:b	Ljava/lang/String;
    //   2628: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2631: ifne +17 -> 2648
    //   2634: aload 11
    //   2636: ldc_w 296
    //   2639: aload 10
    //   2641: getfield 276	com/cootek/usage/g$d:b	Ljava/lang/String;
    //   2644: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2647: pop
    //   2648: aload 7
    //   2650: aload 11
    //   2652: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2655: pop
    //   2656: goto -89 -> 2567
    //   2659: aload 7
    //   2661: invokevirtual 285	org/json/JSONArray:length	()I
    //   2664: ifle +14 -> 2678
    //   2667: aload 5
    //   2669: ldc_w 318
    //   2672: aload 7
    //   2674: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2677: pop
    //   2678: aload 6
    //   2680: getfield 124	com/cootek/usage/g$b:i	Ljava/util/HashSet;
    //   2683: invokevirtual 220	java/util/HashSet:isEmpty	()Z
    //   2686: ifne -2054 -> 632
    //   2689: new 189	org/json/JSONArray
    //   2692: dup
    //   2693: invokespecial 190	org/json/JSONArray:<init>	()V
    //   2696: astore 7
    //   2698: aload 6
    //   2700: getfield 124	com/cootek/usage/g$b:i	Ljava/util/HashSet;
    //   2703: invokevirtual 221	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2706: astore 6
    //   2708: aload 6
    //   2710: invokeinterface 205 1 0
    //   2715: ifeq +84 -> 2799
    //   2718: aload 6
    //   2720: invokeinterface 209 1 0
    //   2725: checkcast 29	com/cootek/usage/g$h
    //   2728: astore 9
    //   2730: new 211	org/json/JSONObject
    //   2733: dup
    //   2734: invokespecial 212	org/json/JSONObject:<init>	()V
    //   2737: astore 10
    //   2739: aload 9
    //   2741: getfield 278	com/cootek/usage/g$h:a	Ljava/lang/String;
    //   2744: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2747: ifne -39 -> 2708
    //   2750: aload 10
    //   2752: ldc -42
    //   2754: aload 9
    //   2756: getfield 278	com/cootek/usage/g$h:a	Ljava/lang/String;
    //   2759: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2762: pop
    //   2763: aload 9
    //   2765: getfield 281	com/cootek/usage/g$h:b	Ljava/lang/String;
    //   2768: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2771: ifne +17 -> 2788
    //   2774: aload 10
    //   2776: ldc_w 296
    //   2779: aload 9
    //   2781: getfield 281	com/cootek/usage/g$h:b	Ljava/lang/String;
    //   2784: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2787: pop
    //   2788: aload 7
    //   2790: aload 10
    //   2792: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2795: pop
    //   2796: goto -88 -> 2708
    //   2799: aload 7
    //   2801: invokevirtual 285	org/json/JSONArray:length	()I
    //   2804: ifle -2172 -> 632
    //   2807: aload 5
    //   2809: ldc_w 320
    //   2812: aload 7
    //   2814: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2817: pop
    //   2818: goto -2186 -> 632
    //   2821: aload_0
    //   2822: getfield 38	com/cootek/usage/g:a	Lcom/cootek/usage/a;
    //   2825: invokevirtual 324	com/cootek/usage/a:getPhoneAccount	()Ljava/lang/String;
    //   2828: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2831: ifne +63 -> 2894
    //   2834: new 211	org/json/JSONObject
    //   2837: dup
    //   2838: invokespecial 212	org/json/JSONObject:<init>	()V
    //   2841: astore 4
    //   2843: aload 4
    //   2845: ldc -42
    //   2847: ldc_w 326
    //   2850: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2853: pop
    //   2854: new 189	org/json/JSONArray
    //   2857: dup
    //   2858: invokespecial 190	org/json/JSONArray:<init>	()V
    //   2861: astore 5
    //   2863: aload 5
    //   2865: aload_0
    //   2866: getfield 38	com/cootek/usage/g:a	Lcom/cootek/usage/a;
    //   2869: invokevirtual 324	com/cootek/usage/a:getPhoneAccount	()Ljava/lang/String;
    //   2872: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2875: pop
    //   2876: aload 4
    //   2878: ldc_w 287
    //   2881: aload 5
    //   2883: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2886: pop
    //   2887: aload_3
    //   2888: aload 4
    //   2890: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2893: pop
    //   2894: new 328	com/cootek/usage/UsageData
    //   2897: dup
    //   2898: invokespecial 329	com/cootek/usage/UsageData:<init>	()V
    //   2901: astore 4
    //   2903: aload 4
    //   2905: aload_0
    //   2906: invokevirtual 331	com/cootek/usage/g:b	()Ljava/lang/String;
    //   2909: putfield 333	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   2912: aload 4
    //   2914: aload_0
    //   2915: iconst_0
    //   2916: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   2919: putfield 338	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   2922: aload 4
    //   2924: aload_3
    //   2925: invokevirtual 341	org/json/JSONArray:toString	()Ljava/lang/String;
    //   2928: putfield 344	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   2931: aload 8
    //   2933: aload 4
    //   2935: putfield 347	com/cootek/usage/g$f:a	Lcom/cootek/usage/UsageData;
    //   2938: aload 8
    //   2940: aload_0
    //   2941: iconst_0
    //   2942: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   2945: putfield 348	com/cootek/usage/g$f:c	Ljava/lang/String;
    //   2948: aload 8
    //   2950: areturn
    //   2951: astore 5
    //   2953: aload 5
    //   2955: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2958: goto -71 -> 2887
    //   2961: astore_3
    //   2962: goto -2230 -> 732
    //   2965: astore 5
    //   2967: aload_3
    //   2968: astore 4
    //   2970: aload 5
    //   2972: astore_3
    //   2973: goto -2241 -> 732
    //   2976: astore 5
    //   2978: goto -2288 -> 690
    //   2981: astore 4
    //   2983: goto -2324 -> 659
    //   2986: goto -790 -> 2196
    //   2989: iconst_0
    //   2990: istore_1
    //   2991: goto -876 -> 2115
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2994	0	this	g
    //   2114	877	1	i	int
    //   259	228	2	bool	boolean
    //   65	568	3	localObject1	Object
    //   648	2	3	localRuntimeException1	RuntimeException
    //   656	1	3	localSecurityException1	SecurityException
    //   658	12	3	localObject2	Object
    //   678	2	3	localRuntimeException2	RuntimeException
    //   689	23	3	localObject3	Object
    //   720	2	3	localRuntimeException3	RuntimeException
    //   728	17	3	localObject4	Object
    //   762	75	3	localRuntimeException4	RuntimeException
    //   849	1	3	localSecurityException2	SecurityException
    //   852	935	3	localObject5	Object
    //   1801	2	3	localRuntimeException5	RuntimeException
    //   1809	2	3	localRuntimeException6	RuntimeException
    //   1821	9	3	localObject6	Object
    //   1851	2	3	localRuntimeException7	RuntimeException
    //   1859	1066	3	localRuntimeException8	RuntimeException
    //   2961	7	3	localObject7	Object
    //   2972	1	3	localObject8	Object
    //   71	667	4	localObject9	Object
    //   746	3	4	localRuntimeException9	RuntimeException
    //   758	233	4	localRuntimeException10	RuntimeException
    //   1009	13	4	localObject10	Object
    //   1026	757	4	localRuntimeException11	RuntimeException
    //   1817	6	4	localRuntimeException12	RuntimeException
    //   2841	128	4	localObject11	Object
    //   2981	1	4	localSecurityException3	SecurityException
    //   1	633	5	localObject12	Object
    //   686	2122	5	localRuntimeException13	RuntimeException
    //   2861	21	5	localJSONArray	JSONArray
    //   2951	3	5	localJSONException1	JSONException
    //   2965	6	5	localObject13	Object
    //   2976	1	5	localRuntimeException14	RuntimeException
    //   4	583	6	localObject14	Object
    //   625	190	6	localJSONException2	JSONException
    //   839	1880	6	localObject15	Object
    //   7	813	7	localObject16	Object
    //   828	56	7	localRuntimeException15	RuntimeException
    //   907	1906	7	localObject17	Object
    //   17	2932	8	localF	f
    //   38	2742	9	localObject18	Object
    //   29	2762	10	localObject19	Object
    //   94	2557	11	localObject20	Object
    // Exception table:
    //   from	to	target	type
    //   553	596	625	org/json/JSONException
    //   596	622	625	org/json/JSONException
    //   1867	1886	625	org/json/JSONException
    //   1886	1916	625	org/json/JSONException
    //   1916	1997	625	org/json/JSONException
    //   1997	2005	625	org/json/JSONException
    //   2008	2027	625	org/json/JSONException
    //   2027	2057	625	org/json/JSONException
    //   2057	2113	625	org/json/JSONException
    //   2115	2140	625	org/json/JSONException
    //   2142	2167	625	org/json/JSONException
    //   2169	2194	625	org/json/JSONException
    //   2200	2208	625	org/json/JSONException
    //   2211	2230	625	org/json/JSONException
    //   2230	2260	625	org/json/JSONException
    //   2260	2341	625	org/json/JSONException
    //   2341	2366	625	org/json/JSONException
    //   2366	2382	625	org/json/JSONException
    //   2385	2396	625	org/json/JSONException
    //   2396	2426	625	org/json/JSONException
    //   2426	2507	625	org/json/JSONException
    //   2507	2515	625	org/json/JSONException
    //   2518	2537	625	org/json/JSONException
    //   2537	2567	625	org/json/JSONException
    //   2567	2648	625	org/json/JSONException
    //   2648	2656	625	org/json/JSONException
    //   2659	2678	625	org/json/JSONException
    //   2678	2708	625	org/json/JSONException
    //   2708	2788	625	org/json/JSONException
    //   2788	2796	625	org/json/JSONException
    //   2799	2818	625	org/json/JSONException
    //   268	274	648	java/lang/RuntimeException
    //   40	66	656	java/lang/SecurityException
    //   669	675	678	java/lang/RuntimeException
    //   40	66	686	java/lang/RuntimeException
    //   711	717	720	java/lang/RuntimeException
    //   40	66	728	finally
    //   737	744	746	java/lang/RuntimeException
    //   441	462	828	java/lang/RuntimeException
    //   763	773	828	java/lang/RuntimeException
    //   780	790	828	java/lang/RuntimeException
    //   797	807	828	java/lang/RuntimeException
    //   814	825	828	java/lang/RuntimeException
    //   881	891	828	java/lang/RuntimeException
    //   898	909	828	java/lang/RuntimeException
    //   916	929	828	java/lang/RuntimeException
    //   936	953	828	java/lang/RuntimeException
    //   960	968	828	java/lang/RuntimeException
    //   975	988	828	java/lang/RuntimeException
    //   995	1006	828	java/lang/RuntimeException
    //   1031	1041	828	java/lang/RuntimeException
    //   1048	1059	828	java/lang/RuntimeException
    //   1066	1079	828	java/lang/RuntimeException
    //   1086	1099	828	java/lang/RuntimeException
    //   1106	1120	828	java/lang/RuntimeException
    //   1127	1144	828	java/lang/RuntimeException
    //   1151	1159	828	java/lang/RuntimeException
    //   1166	1179	828	java/lang/RuntimeException
    //   1186	1197	828	java/lang/RuntimeException
    //   1207	1217	828	java/lang/RuntimeException
    //   1224	1235	828	java/lang/RuntimeException
    //   1242	1255	828	java/lang/RuntimeException
    //   1262	1279	828	java/lang/RuntimeException
    //   1286	1294	828	java/lang/RuntimeException
    //   1301	1314	828	java/lang/RuntimeException
    //   1321	1339	828	java/lang/RuntimeException
    //   1346	1354	828	java/lang/RuntimeException
    //   1361	1375	828	java/lang/RuntimeException
    //   1382	1393	828	java/lang/RuntimeException
    //   1403	1413	828	java/lang/RuntimeException
    //   1420	1431	828	java/lang/RuntimeException
    //   1438	1451	828	java/lang/RuntimeException
    //   1458	1475	828	java/lang/RuntimeException
    //   1482	1490	828	java/lang/RuntimeException
    //   1497	1510	828	java/lang/RuntimeException
    //   1517	1528	828	java/lang/RuntimeException
    //   1538	1548	828	java/lang/RuntimeException
    //   1555	1566	828	java/lang/RuntimeException
    //   1573	1586	828	java/lang/RuntimeException
    //   1593	1610	828	java/lang/RuntimeException
    //   1617	1625	828	java/lang/RuntimeException
    //   1632	1645	828	java/lang/RuntimeException
    //   1652	1663	828	java/lang/RuntimeException
    //   1673	1683	828	java/lang/RuntimeException
    //   1690	1701	828	java/lang/RuntimeException
    //   1708	1721	828	java/lang/RuntimeException
    //   1728	1745	828	java/lang/RuntimeException
    //   1752	1760	828	java/lang/RuntimeException
    //   1767	1780	828	java/lang/RuntimeException
    //   1787	1798	828	java/lang/RuntimeException
    //   281	406	849	java/lang/SecurityException
    //   422	430	849	java/lang/SecurityException
    //   441	462	849	java/lang/SecurityException
    //   478	486	849	java/lang/SecurityException
    //   763	773	849	java/lang/SecurityException
    //   780	790	849	java/lang/SecurityException
    //   797	807	849	java/lang/SecurityException
    //   814	825	849	java/lang/SecurityException
    //   841	846	849	java/lang/SecurityException
    //   881	891	849	java/lang/SecurityException
    //   898	909	849	java/lang/SecurityException
    //   916	929	849	java/lang/SecurityException
    //   936	953	849	java/lang/SecurityException
    //   960	968	849	java/lang/SecurityException
    //   975	988	849	java/lang/SecurityException
    //   995	1006	849	java/lang/SecurityException
    //   1031	1041	849	java/lang/SecurityException
    //   1048	1059	849	java/lang/SecurityException
    //   1066	1079	849	java/lang/SecurityException
    //   1086	1099	849	java/lang/SecurityException
    //   1106	1120	849	java/lang/SecurityException
    //   1127	1144	849	java/lang/SecurityException
    //   1151	1159	849	java/lang/SecurityException
    //   1166	1179	849	java/lang/SecurityException
    //   1186	1197	849	java/lang/SecurityException
    //   1207	1217	849	java/lang/SecurityException
    //   1224	1235	849	java/lang/SecurityException
    //   1242	1255	849	java/lang/SecurityException
    //   1262	1279	849	java/lang/SecurityException
    //   1286	1294	849	java/lang/SecurityException
    //   1301	1314	849	java/lang/SecurityException
    //   1321	1339	849	java/lang/SecurityException
    //   1346	1354	849	java/lang/SecurityException
    //   1361	1375	849	java/lang/SecurityException
    //   1382	1393	849	java/lang/SecurityException
    //   1403	1413	849	java/lang/SecurityException
    //   1420	1431	849	java/lang/SecurityException
    //   1438	1451	849	java/lang/SecurityException
    //   1458	1475	849	java/lang/SecurityException
    //   1482	1490	849	java/lang/SecurityException
    //   1497	1510	849	java/lang/SecurityException
    //   1517	1528	849	java/lang/SecurityException
    //   1538	1548	849	java/lang/SecurityException
    //   1555	1566	849	java/lang/SecurityException
    //   1573	1586	849	java/lang/SecurityException
    //   1593	1610	849	java/lang/SecurityException
    //   1617	1625	849	java/lang/SecurityException
    //   1632	1645	849	java/lang/SecurityException
    //   1652	1663	849	java/lang/SecurityException
    //   1673	1683	849	java/lang/SecurityException
    //   1690	1701	849	java/lang/SecurityException
    //   1708	1721	849	java/lang/SecurityException
    //   1728	1745	849	java/lang/SecurityException
    //   1752	1760	849	java/lang/SecurityException
    //   1767	1780	849	java/lang/SecurityException
    //   1787	1798	849	java/lang/SecurityException
    //   281	406	1009	finally
    //   422	430	1009	finally
    //   441	462	1009	finally
    //   478	486	1009	finally
    //   763	773	1009	finally
    //   780	790	1009	finally
    //   797	807	1009	finally
    //   814	825	1009	finally
    //   841	846	1009	finally
    //   853	859	1009	finally
    //   881	891	1009	finally
    //   898	909	1009	finally
    //   916	929	1009	finally
    //   936	953	1009	finally
    //   960	968	1009	finally
    //   975	988	1009	finally
    //   995	1006	1009	finally
    //   1031	1041	1009	finally
    //   1048	1059	1009	finally
    //   1066	1079	1009	finally
    //   1086	1099	1009	finally
    //   1106	1120	1009	finally
    //   1127	1144	1009	finally
    //   1151	1159	1009	finally
    //   1166	1179	1009	finally
    //   1186	1197	1009	finally
    //   1207	1217	1009	finally
    //   1224	1235	1009	finally
    //   1242	1255	1009	finally
    //   1262	1279	1009	finally
    //   1286	1294	1009	finally
    //   1301	1314	1009	finally
    //   1321	1339	1009	finally
    //   1346	1354	1009	finally
    //   1361	1375	1009	finally
    //   1382	1393	1009	finally
    //   1403	1413	1009	finally
    //   1420	1431	1009	finally
    //   1438	1451	1009	finally
    //   1458	1475	1009	finally
    //   1482	1490	1009	finally
    //   1497	1510	1009	finally
    //   1517	1528	1009	finally
    //   1538	1548	1009	finally
    //   1555	1566	1009	finally
    //   1573	1586	1009	finally
    //   1593	1610	1009	finally
    //   1617	1625	1009	finally
    //   1632	1645	1009	finally
    //   1652	1663	1009	finally
    //   1673	1683	1009	finally
    //   1690	1701	1009	finally
    //   1708	1721	1009	finally
    //   1728	1745	1009	finally
    //   1752	1760	1009	finally
    //   1767	1780	1009	finally
    //   1787	1798	1009	finally
    //   1822	1827	1009	finally
    //   1830	1836	1009	finally
    //   495	502	1801	java/lang/RuntimeException
    //   864	871	1809	java/lang/RuntimeException
    //   281	406	1817	java/lang/RuntimeException
    //   422	430	1817	java/lang/RuntimeException
    //   478	486	1817	java/lang/RuntimeException
    //   841	846	1817	java/lang/RuntimeException
    //   1841	1848	1851	java/lang/RuntimeException
    //   1015	1021	1859	java/lang/RuntimeException
    //   2843	2887	2951	org/json/JSONException
    //   73	82	2961	finally
    //   85	96	2961	finally
    //   99	111	2961	finally
    //   114	126	2961	finally
    //   129	141	2961	finally
    //   144	156	2961	finally
    //   159	171	2961	finally
    //   174	186	2961	finally
    //   189	201	2961	finally
    //   204	216	2961	finally
    //   219	231	2961	finally
    //   234	250	2961	finally
    //   253	260	2961	finally
    //   693	698	2961	finally
    //   701	707	2961	finally
    //   659	665	2965	finally
    //   73	82	2976	java/lang/RuntimeException
    //   85	96	2976	java/lang/RuntimeException
    //   99	111	2976	java/lang/RuntimeException
    //   114	126	2976	java/lang/RuntimeException
    //   129	141	2976	java/lang/RuntimeException
    //   144	156	2976	java/lang/RuntimeException
    //   159	171	2976	java/lang/RuntimeException
    //   174	186	2976	java/lang/RuntimeException
    //   189	201	2976	java/lang/RuntimeException
    //   204	216	2976	java/lang/RuntimeException
    //   219	231	2976	java/lang/RuntimeException
    //   234	250	2976	java/lang/RuntimeException
    //   253	260	2976	java/lang/RuntimeException
    //   73	82	2981	java/lang/SecurityException
    //   85	96	2981	java/lang/SecurityException
    //   99	111	2981	java/lang/SecurityException
    //   114	126	2981	java/lang/SecurityException
    //   129	141	2981	java/lang/SecurityException
    //   144	156	2981	java/lang/SecurityException
    //   159	171	2981	java/lang/SecurityException
    //   174	186	2981	java/lang/SecurityException
    //   189	201	2981	java/lang/SecurityException
    //   204	216	2981	java/lang/SecurityException
    //   219	231	2981	java/lang/SecurityException
    //   234	250	2981	java/lang/SecurityException
    //   253	260	2981	java/lang/SecurityException
  }
  
  private String c(int paramInt)
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
  private f d()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/g$f
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 49	com/cootek/usage/g$f:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 10
    //   10: aload_0
    //   11: getfield 38	com/cootek/usage/g:a	Lcom/cootek/usage/a;
    //   14: invokevirtual 55	com/cootek/usage/a:getContext	()Landroid/content/Context;
    //   17: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 7
    //   22: new 189	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 190	org/json/JSONArray:<init>	()V
    //   29: astore 11
    //   31: invokestatic 363	com/cootek/usage/m:a	()Lcom/cootek/usage/m;
    //   34: aload_0
    //   35: iconst_1
    //   36: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   39: invokevirtual 366	com/cootek/usage/m:c	(Ljava/lang/String;)J
    //   42: lstore_2
    //   43: aload 7
    //   45: getstatic 369	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   48: bipush 6
    //   50: anewarray 72	java/lang/String
    //   53: dup
    //   54: iconst_0
    //   55: ldc 74
    //   57: aastore
    //   58: dup
    //   59: iconst_1
    //   60: ldc_w 371
    //   63: aastore
    //   64: dup
    //   65: iconst_2
    //   66: ldc_w 316
    //   69: aastore
    //   70: dup
    //   71: iconst_3
    //   72: ldc_w 373
    //   75: aastore
    //   76: dup
    //   77: iconst_4
    //   78: ldc_w 296
    //   81: aastore
    //   82: dup
    //   83: iconst_5
    //   84: ldc -42
    //   86: aastore
    //   87: ldc_w 375
    //   90: iconst_1
    //   91: anewarray 72	java/lang/String
    //   94: dup
    //   95: iconst_0
    //   96: lload_2
    //   97: invokestatic 378	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   100: aastore
    //   101: ldc_w 380
    //   104: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   107: astore 7
    //   109: aload 7
    //   111: ifnull +265 -> 376
    //   114: aload 7
    //   116: astore 8
    //   118: aload 7
    //   120: invokeinterface 88 1 0
    //   125: ifeq +251 -> 376
    //   128: aload 7
    //   130: astore 8
    //   132: aload 10
    //   134: aload 7
    //   136: iconst_0
    //   137: invokeinterface 95 2 0
    //   142: putfield 382	com/cootek/usage/g$f:b	J
    //   145: aload 7
    //   147: astore 8
    //   149: aload 7
    //   151: iconst_1
    //   152: invokeinterface 102 2 0
    //   157: astore 9
    //   159: aload 7
    //   161: astore 8
    //   163: aload 7
    //   165: iconst_2
    //   166: invokeinterface 95 2 0
    //   171: lstore 4
    //   173: aload 7
    //   175: astore 8
    //   177: aload 7
    //   179: iconst_3
    //   180: invokeinterface 95 2 0
    //   185: lstore_2
    //   186: aload 7
    //   188: astore 8
    //   190: aload 7
    //   192: iconst_4
    //   193: invokeinterface 246 2 0
    //   198: istore_1
    //   199: aload 7
    //   201: astore 8
    //   203: aload 7
    //   205: iconst_5
    //   206: invokeinterface 102 2 0
    //   211: astore 12
    //   213: aload 7
    //   215: astore 8
    //   217: new 211	org/json/JSONObject
    //   220: dup
    //   221: invokespecial 212	org/json/JSONObject:<init>	()V
    //   224: astore 13
    //   226: aload 7
    //   228: astore 8
    //   230: aload 13
    //   232: ldc_w 384
    //   235: aload 9
    //   237: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   240: pop
    //   241: aload 7
    //   243: astore 8
    //   245: aload 13
    //   247: ldc_w 316
    //   250: lload 4
    //   252: ldc2_w 385
    //   255: ldiv
    //   256: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   259: pop
    //   260: iload_1
    //   261: iconst_2
    //   262: if_icmpne +204 -> 466
    //   265: ldc_w 391
    //   268: astore 9
    //   270: aload 7
    //   272: astore 8
    //   274: aload 13
    //   276: ldc_w 296
    //   279: aload 9
    //   281: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   284: pop
    //   285: iconst_3
    //   286: iload_1
    //   287: if_icmpne +5 -> 292
    //   290: lconst_0
    //   291: lstore_2
    //   292: aload 7
    //   294: astore 8
    //   296: aload 13
    //   298: ldc_w 373
    //   301: lload_2
    //   302: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   305: pop
    //   306: aload 7
    //   308: astore 8
    //   310: aload 12
    //   312: invokestatic 292	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   315: ifne +159 -> 474
    //   318: iconst_1
    //   319: istore 6
    //   321: aload 7
    //   323: astore 8
    //   325: aload 13
    //   327: ldc_w 393
    //   330: iload 6
    //   332: invokevirtual 396	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   335: pop
    //   336: aload 7
    //   338: astore 8
    //   340: aload 11
    //   342: aload 13
    //   344: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   347: pop
    //   348: aload 7
    //   350: astore 8
    //   352: aload 10
    //   354: iconst_1
    //   355: putfield 232	com/cootek/usage/g$f:d	Z
    //   358: aload 7
    //   360: astore 8
    //   362: aload 7
    //   364: invokeinterface 137 1 0
    //   369: istore 6
    //   371: iload 6
    //   373: ifne -228 -> 145
    //   376: aload 7
    //   378: ifnull +10 -> 388
    //   381: aload 7
    //   383: invokeinterface 140 1 0
    //   388: new 211	org/json/JSONObject
    //   391: dup
    //   392: invokespecial 212	org/json/JSONObject:<init>	()V
    //   395: astore 7
    //   397: aload 7
    //   399: ldc_w 398
    //   402: aload 11
    //   404: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   407: pop
    //   408: new 328	com/cootek/usage/UsageData
    //   411: dup
    //   412: invokespecial 329	com/cootek/usage/UsageData:<init>	()V
    //   415: astore 8
    //   417: aload 8
    //   419: aload_0
    //   420: invokevirtual 331	com/cootek/usage/g:b	()Ljava/lang/String;
    //   423: putfield 333	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   426: aload 8
    //   428: aload_0
    //   429: iconst_1
    //   430: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   433: putfield 338	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   436: aload 8
    //   438: aload 7
    //   440: invokevirtual 399	org/json/JSONObject:toString	()Ljava/lang/String;
    //   443: putfield 344	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   446: aload 10
    //   448: aload 8
    //   450: putfield 347	com/cootek/usage/g$f:a	Lcom/cootek/usage/UsageData;
    //   453: aload 10
    //   455: aload_0
    //   456: iconst_1
    //   457: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   460: putfield 348	com/cootek/usage/g$f:c	Ljava/lang/String;
    //   463: aload 10
    //   465: areturn
    //   466: ldc_w 401
    //   469: astore 9
    //   471: goto -201 -> 270
    //   474: iconst_0
    //   475: istore 6
    //   477: goto -156 -> 321
    //   480: astore 9
    //   482: aload 7
    //   484: astore 8
    //   486: aload 9
    //   488: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   491: goto -133 -> 358
    //   494: astore 8
    //   496: aload 10
    //   498: iconst_0
    //   499: putfield 232	com/cootek/usage/g$f:d	Z
    //   502: aload 7
    //   504: ifnull +10 -> 514
    //   507: aload 7
    //   509: invokeinterface 140 1 0
    //   514: aload 10
    //   516: areturn
    //   517: astore 7
    //   519: aload 7
    //   521: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   524: goto -136 -> 388
    //   527: astore 7
    //   529: aload 7
    //   531: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   534: goto -20 -> 514
    //   537: astore 9
    //   539: aconst_null
    //   540: astore 7
    //   542: aload 7
    //   544: astore 8
    //   546: aload 9
    //   548: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   551: aload 7
    //   553: astore 8
    //   555: aload 10
    //   557: iconst_0
    //   558: putfield 232	com/cootek/usage/g$f:d	Z
    //   561: aload 7
    //   563: ifnull +10 -> 573
    //   566: aload 7
    //   568: invokeinterface 140 1 0
    //   573: aload 10
    //   575: areturn
    //   576: astore 7
    //   578: aload 7
    //   580: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   583: goto -10 -> 573
    //   586: astore 7
    //   588: aconst_null
    //   589: astore 8
    //   591: aload 8
    //   593: ifnull +10 -> 603
    //   596: aload 8
    //   598: invokeinterface 140 1 0
    //   603: aload 7
    //   605: athrow
    //   606: astore 8
    //   608: aload 8
    //   610: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   613: goto -10 -> 603
    //   616: astore 8
    //   618: aload 8
    //   620: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   623: goto -215 -> 408
    //   626: astore 7
    //   628: goto -37 -> 591
    //   631: astore 9
    //   633: aload 7
    //   635: astore 8
    //   637: aload 9
    //   639: astore 7
    //   641: goto -50 -> 591
    //   644: astore 9
    //   646: goto -104 -> 542
    //   649: astore 7
    //   651: aconst_null
    //   652: astore 7
    //   654: goto -158 -> 496
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	657	0	this	g
    //   198	90	1	i	int
    //   42	260	2	l1	long
    //   171	80	4	l2	long
    //   319	157	6	bool	boolean
    //   20	488	7	localObject1	Object
    //   517	3	7	localRuntimeException1	RuntimeException
    //   527	3	7	localRuntimeException2	RuntimeException
    //   540	27	7	localObject2	Object
    //   576	3	7	localRuntimeException3	RuntimeException
    //   586	18	7	localObject3	Object
    //   626	8	7	localObject4	Object
    //   639	1	7	localObject5	Object
    //   649	1	7	localSecurityException1	SecurityException
    //   652	1	7	localObject6	Object
    //   116	369	8	localObject7	Object
    //   494	1	8	localSecurityException2	SecurityException
    //   544	53	8	localObject8	Object
    //   606	3	8	localRuntimeException4	RuntimeException
    //   616	3	8	localJSONException1	JSONException
    //   635	1	8	localObject9	Object
    //   157	313	9	str1	String
    //   480	7	9	localJSONException2	JSONException
    //   537	10	9	localRuntimeException5	RuntimeException
    //   631	7	9	localObject10	Object
    //   644	1	9	localRuntimeException6	RuntimeException
    //   8	566	10	localF	f
    //   29	374	11	localJSONArray	JSONArray
    //   211	100	12	str2	String
    //   224	119	13	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   230	241	480	org/json/JSONException
    //   245	260	480	org/json/JSONException
    //   274	285	480	org/json/JSONException
    //   296	306	480	org/json/JSONException
    //   310	318	480	org/json/JSONException
    //   325	336	480	org/json/JSONException
    //   340	348	480	org/json/JSONException
    //   352	358	480	org/json/JSONException
    //   118	128	494	java/lang/SecurityException
    //   132	145	494	java/lang/SecurityException
    //   149	159	494	java/lang/SecurityException
    //   163	173	494	java/lang/SecurityException
    //   177	186	494	java/lang/SecurityException
    //   190	199	494	java/lang/SecurityException
    //   203	213	494	java/lang/SecurityException
    //   217	226	494	java/lang/SecurityException
    //   230	241	494	java/lang/SecurityException
    //   245	260	494	java/lang/SecurityException
    //   274	285	494	java/lang/SecurityException
    //   296	306	494	java/lang/SecurityException
    //   310	318	494	java/lang/SecurityException
    //   325	336	494	java/lang/SecurityException
    //   340	348	494	java/lang/SecurityException
    //   352	358	494	java/lang/SecurityException
    //   362	371	494	java/lang/SecurityException
    //   486	491	494	java/lang/SecurityException
    //   381	388	517	java/lang/RuntimeException
    //   507	514	527	java/lang/RuntimeException
    //   31	109	537	java/lang/RuntimeException
    //   566	573	576	java/lang/RuntimeException
    //   31	109	586	finally
    //   596	603	606	java/lang/RuntimeException
    //   397	408	616	org/json/JSONException
    //   118	128	626	finally
    //   132	145	626	finally
    //   149	159	626	finally
    //   163	173	626	finally
    //   177	186	626	finally
    //   190	199	626	finally
    //   203	213	626	finally
    //   217	226	626	finally
    //   230	241	626	finally
    //   245	260	626	finally
    //   274	285	626	finally
    //   296	306	626	finally
    //   310	318	626	finally
    //   325	336	626	finally
    //   340	348	626	finally
    //   352	358	626	finally
    //   362	371	626	finally
    //   486	491	626	finally
    //   546	551	626	finally
    //   555	561	626	finally
    //   496	502	631	finally
    //   118	128	644	java/lang/RuntimeException
    //   132	145	644	java/lang/RuntimeException
    //   149	159	644	java/lang/RuntimeException
    //   163	173	644	java/lang/RuntimeException
    //   177	186	644	java/lang/RuntimeException
    //   190	199	644	java/lang/RuntimeException
    //   203	213	644	java/lang/RuntimeException
    //   217	226	644	java/lang/RuntimeException
    //   230	241	644	java/lang/RuntimeException
    //   245	260	644	java/lang/RuntimeException
    //   274	285	644	java/lang/RuntimeException
    //   296	306	644	java/lang/RuntimeException
    //   310	318	644	java/lang/RuntimeException
    //   325	336	644	java/lang/RuntimeException
    //   340	348	644	java/lang/RuntimeException
    //   352	358	644	java/lang/RuntimeException
    //   362	371	644	java/lang/RuntimeException
    //   486	491	644	java/lang/RuntimeException
    //   31	109	649	java/lang/SecurityException
  }
  
  private String d(int paramInt)
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
  private f e()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/g$f
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 49	com/cootek/usage/g$f:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 9
    //   10: aload_0
    //   11: getfield 38	com/cootek/usage/g:a	Lcom/cootek/usage/a;
    //   14: invokevirtual 55	com/cootek/usage/a:getContext	()Landroid/content/Context;
    //   17: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 6
    //   22: new 189	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 190	org/json/JSONArray:<init>	()V
    //   29: astore 8
    //   31: invokestatic 363	com/cootek/usage/m:a	()Lcom/cootek/usage/m;
    //   34: aload_0
    //   35: iconst_2
    //   36: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   39: invokevirtual 366	com/cootek/usage/m:c	(Ljava/lang/String;)J
    //   42: lstore_1
    //   43: aload 6
    //   45: ldc_w 403
    //   48: invokestatic 409	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   51: bipush 6
    //   53: anewarray 72	java/lang/String
    //   56: dup
    //   57: iconst_0
    //   58: ldc 74
    //   60: aastore
    //   61: dup
    //   62: iconst_1
    //   63: ldc_w 294
    //   66: aastore
    //   67: dup
    //   68: iconst_2
    //   69: ldc_w 411
    //   72: aastore
    //   73: dup
    //   74: iconst_3
    //   75: ldc_w 316
    //   78: aastore
    //   79: dup
    //   80: iconst_4
    //   81: ldc_w 413
    //   84: aastore
    //   85: dup
    //   86: iconst_5
    //   87: ldc_w 415
    //   90: aastore
    //   91: ldc_w 375
    //   94: iconst_1
    //   95: anewarray 72	java/lang/String
    //   98: dup
    //   99: iconst_0
    //   100: lload_1
    //   101: invokestatic 378	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   104: aastore
    //   105: ldc_w 417
    //   108: invokevirtual 82	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   111: astore 6
    //   113: aload 6
    //   115: ifnull +85 -> 200
    //   118: aload 6
    //   120: astore 7
    //   122: aload 6
    //   124: invokeinterface 88 1 0
    //   129: ifeq +71 -> 200
    //   132: aload 6
    //   134: astore 7
    //   136: aload 9
    //   138: aload 6
    //   140: iconst_0
    //   141: invokeinterface 95 2 0
    //   146: putfield 382	com/cootek/usage/g$f:b	J
    //   149: aload 6
    //   151: astore 7
    //   153: aload 6
    //   155: iconst_1
    //   156: invokeinterface 102 2 0
    //   161: astore 10
    //   163: aload 6
    //   165: astore 7
    //   167: aload 6
    //   169: iconst_2
    //   170: invokeinterface 95 2 0
    //   175: lstore_1
    //   176: lload_1
    //   177: lconst_0
    //   178: lcmp
    //   179: ifle +111 -> 290
    //   182: aload 6
    //   184: astore 7
    //   186: aload 6
    //   188: invokeinterface 137 1 0
    //   193: istore 5
    //   195: iload 5
    //   197: ifne -48 -> 149
    //   200: aload 6
    //   202: ifnull +10 -> 212
    //   205: aload 6
    //   207: invokeinterface 140 1 0
    //   212: new 211	org/json/JSONObject
    //   215: dup
    //   216: invokespecial 212	org/json/JSONObject:<init>	()V
    //   219: astore 6
    //   221: aload 6
    //   223: ldc_w 398
    //   226: aload 8
    //   228: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   231: pop
    //   232: new 328	com/cootek/usage/UsageData
    //   235: dup
    //   236: invokespecial 329	com/cootek/usage/UsageData:<init>	()V
    //   239: astore 7
    //   241: aload 7
    //   243: aload_0
    //   244: invokevirtual 331	com/cootek/usage/g:b	()Ljava/lang/String;
    //   247: putfield 333	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   250: aload 7
    //   252: aload_0
    //   253: iconst_2
    //   254: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   257: putfield 338	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   260: aload 7
    //   262: aload 6
    //   264: invokevirtual 399	org/json/JSONObject:toString	()Ljava/lang/String;
    //   267: putfield 344	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   270: aload 9
    //   272: aload 7
    //   274: putfield 347	com/cootek/usage/g$f:a	Lcom/cootek/usage/UsageData;
    //   277: aload 9
    //   279: aload_0
    //   280: iconst_2
    //   281: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   284: putfield 348	com/cootek/usage/g$f:c	Ljava/lang/String;
    //   287: aload 9
    //   289: areturn
    //   290: aload 6
    //   292: astore 7
    //   294: aload 6
    //   296: iconst_3
    //   297: invokeinterface 95 2 0
    //   302: lstore_3
    //   303: aload 6
    //   305: astore 7
    //   307: aload 6
    //   309: iconst_4
    //   310: invokeinterface 102 2 0
    //   315: astore 11
    //   317: aload 6
    //   319: astore 7
    //   321: aload 6
    //   323: iconst_5
    //   324: invokeinterface 102 2 0
    //   329: astore 12
    //   331: aload 6
    //   333: astore 7
    //   335: new 211	org/json/JSONObject
    //   338: dup
    //   339: invokespecial 212	org/json/JSONObject:<init>	()V
    //   342: astore 13
    //   344: aload 6
    //   346: astore 7
    //   348: aload 13
    //   350: ldc_w 384
    //   353: aload 10
    //   355: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   358: pop
    //   359: aload 6
    //   361: astore 7
    //   363: aload 13
    //   365: ldc_w 316
    //   368: lload_3
    //   369: ldc2_w 385
    //   372: ldiv
    //   373: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   376: pop
    //   377: aload 6
    //   379: astore 7
    //   381: aload 13
    //   383: ldc_w 296
    //   386: ldc_w 401
    //   389: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   392: pop
    //   393: lload_1
    //   394: lconst_0
    //   395: lcmp
    //   396: ifle +113 -> 509
    //   399: iconst_1
    //   400: istore 5
    //   402: aload 6
    //   404: astore 7
    //   406: aload 13
    //   408: ldc_w 393
    //   411: iload 5
    //   413: invokevirtual 396	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   416: pop
    //   417: aload 6
    //   419: astore 7
    //   421: aload 13
    //   423: ldc_w 419
    //   426: aload 11
    //   428: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   431: pop
    //   432: aload 6
    //   434: astore 7
    //   436: aload 13
    //   438: ldc_w 415
    //   441: aload 12
    //   443: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   446: pop
    //   447: aload 6
    //   449: astore 7
    //   451: aload 8
    //   453: aload 13
    //   455: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   458: pop
    //   459: aload 6
    //   461: astore 7
    //   463: aload 9
    //   465: iconst_1
    //   466: putfield 232	com/cootek/usage/g$f:d	Z
    //   469: goto -287 -> 182
    //   472: astore 10
    //   474: aload 6
    //   476: astore 7
    //   478: aload 10
    //   480: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   483: goto -301 -> 182
    //   486: astore 7
    //   488: aload 9
    //   490: iconst_0
    //   491: putfield 232	com/cootek/usage/g$f:d	Z
    //   494: aload 6
    //   496: ifnull +10 -> 506
    //   499: aload 6
    //   501: invokeinterface 140 1 0
    //   506: aload 9
    //   508: areturn
    //   509: iconst_0
    //   510: istore 5
    //   512: goto -110 -> 402
    //   515: astore 6
    //   517: aload 6
    //   519: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   522: goto -310 -> 212
    //   525: astore 6
    //   527: aload 6
    //   529: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   532: goto -26 -> 506
    //   535: astore 8
    //   537: aconst_null
    //   538: astore 6
    //   540: aload 6
    //   542: astore 7
    //   544: aload 8
    //   546: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   549: aload 6
    //   551: astore 7
    //   553: aload 9
    //   555: iconst_0
    //   556: putfield 232	com/cootek/usage/g$f:d	Z
    //   559: aload 6
    //   561: ifnull +10 -> 571
    //   564: aload 6
    //   566: invokeinterface 140 1 0
    //   571: aload 9
    //   573: areturn
    //   574: astore 6
    //   576: aload 6
    //   578: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   581: goto -10 -> 571
    //   584: astore 6
    //   586: aconst_null
    //   587: astore 7
    //   589: aload 7
    //   591: ifnull +10 -> 601
    //   594: aload 7
    //   596: invokeinterface 140 1 0
    //   601: aload 6
    //   603: athrow
    //   604: astore 7
    //   606: aload 7
    //   608: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   611: goto -10 -> 601
    //   614: astore 7
    //   616: aload 7
    //   618: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   621: goto -389 -> 232
    //   624: astore 6
    //   626: goto -37 -> 589
    //   629: astore 8
    //   631: aload 6
    //   633: astore 7
    //   635: aload 8
    //   637: astore 6
    //   639: goto -50 -> 589
    //   642: astore 8
    //   644: goto -104 -> 540
    //   647: astore 6
    //   649: aconst_null
    //   650: astore 6
    //   652: goto -164 -> 488
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	655	0	this	g
    //   42	352	1	l1	long
    //   302	67	3	l2	long
    //   193	318	5	bool	boolean
    //   20	480	6	localObject1	Object
    //   515	3	6	localRuntimeException1	RuntimeException
    //   525	3	6	localRuntimeException2	RuntimeException
    //   538	27	6	localObject2	Object
    //   574	3	6	localRuntimeException3	RuntimeException
    //   584	18	6	localObject3	Object
    //   624	8	6	localObject4	Object
    //   637	1	6	localObject5	Object
    //   647	1	6	localSecurityException1	SecurityException
    //   650	1	6	localObject6	Object
    //   120	357	7	localObject7	Object
    //   486	1	7	localSecurityException2	SecurityException
    //   542	53	7	localObject8	Object
    //   604	3	7	localRuntimeException4	RuntimeException
    //   614	3	7	localJSONException1	JSONException
    //   633	1	7	localObject9	Object
    //   29	423	8	localJSONArray	JSONArray
    //   535	10	8	localRuntimeException5	RuntimeException
    //   629	7	8	localObject10	Object
    //   642	1	8	localRuntimeException6	RuntimeException
    //   8	564	9	localF	f
    //   161	193	10	str1	String
    //   472	7	10	localJSONException2	JSONException
    //   315	112	11	str2	String
    //   329	113	12	str3	String
    //   342	112	13	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   348	359	472	org/json/JSONException
    //   363	377	472	org/json/JSONException
    //   381	393	472	org/json/JSONException
    //   406	417	472	org/json/JSONException
    //   421	432	472	org/json/JSONException
    //   436	447	472	org/json/JSONException
    //   451	459	472	org/json/JSONException
    //   463	469	472	org/json/JSONException
    //   122	132	486	java/lang/SecurityException
    //   136	149	486	java/lang/SecurityException
    //   153	163	486	java/lang/SecurityException
    //   167	176	486	java/lang/SecurityException
    //   186	195	486	java/lang/SecurityException
    //   294	303	486	java/lang/SecurityException
    //   307	317	486	java/lang/SecurityException
    //   321	331	486	java/lang/SecurityException
    //   335	344	486	java/lang/SecurityException
    //   348	359	486	java/lang/SecurityException
    //   363	377	486	java/lang/SecurityException
    //   381	393	486	java/lang/SecurityException
    //   406	417	486	java/lang/SecurityException
    //   421	432	486	java/lang/SecurityException
    //   436	447	486	java/lang/SecurityException
    //   451	459	486	java/lang/SecurityException
    //   463	469	486	java/lang/SecurityException
    //   478	483	486	java/lang/SecurityException
    //   205	212	515	java/lang/RuntimeException
    //   499	506	525	java/lang/RuntimeException
    //   31	113	535	java/lang/RuntimeException
    //   564	571	574	java/lang/RuntimeException
    //   31	113	584	finally
    //   594	601	604	java/lang/RuntimeException
    //   221	232	614	org/json/JSONException
    //   122	132	624	finally
    //   136	149	624	finally
    //   153	163	624	finally
    //   167	176	624	finally
    //   186	195	624	finally
    //   294	303	624	finally
    //   307	317	624	finally
    //   321	331	624	finally
    //   335	344	624	finally
    //   348	359	624	finally
    //   363	377	624	finally
    //   381	393	624	finally
    //   406	417	624	finally
    //   421	432	624	finally
    //   436	447	624	finally
    //   451	459	624	finally
    //   463	469	624	finally
    //   478	483	624	finally
    //   544	549	624	finally
    //   553	559	624	finally
    //   488	494	629	finally
    //   122	132	642	java/lang/RuntimeException
    //   136	149	642	java/lang/RuntimeException
    //   153	163	642	java/lang/RuntimeException
    //   167	176	642	java/lang/RuntimeException
    //   186	195	642	java/lang/RuntimeException
    //   294	303	642	java/lang/RuntimeException
    //   307	317	642	java/lang/RuntimeException
    //   321	331	642	java/lang/RuntimeException
    //   335	344	642	java/lang/RuntimeException
    //   348	359	642	java/lang/RuntimeException
    //   363	377	642	java/lang/RuntimeException
    //   381	393	642	java/lang/RuntimeException
    //   406	417	642	java/lang/RuntimeException
    //   421	432	642	java/lang/RuntimeException
    //   436	447	642	java/lang/RuntimeException
    //   451	459	642	java/lang/RuntimeException
    //   463	469	642	java/lang/RuntimeException
    //   478	483	642	java/lang/RuntimeException
    //   31	113	647	java/lang/SecurityException
  }
  
  private String e(int paramInt)
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
  
  private f f()
  {
    f localF = new f();
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
          localF.d = true;
          i += 1;
        }
        catch (JSONException localJSONException2)
        {
          for (;;)
          {
            com.google.a.a.a.a.a.a.a(localJSONException2);
          }
        }
      }
    }
    localObject2 = new JSONObject();
    try
    {
      ((JSONObject)localObject2).put("data", localObject1);
      localObject1 = new UsageData();
      ((UsageData)localObject1).type = b();
      ((UsageData)localObject1).path = a(3);
      ((UsageData)localObject1).value = ((JSONObject)localObject2).toString();
      localF.a = ((UsageData)localObject1);
      localF.c = a(3);
      return localF;
    }
    catch (JSONException localJSONException1)
    {
      for (;;)
      {
        com.google.a.a.a.a.a.a.a(localJSONException1);
      }
    }
  }
  
  private String f(int paramInt)
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
  private f g()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/g$f
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 49	com/cootek/usage/g$f:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 13
    //   10: aload_0
    //   11: getfield 38	com/cootek/usage/g:a	Lcom/cootek/usage/a;
    //   14: invokevirtual 55	com/cootek/usage/a:getContext	()Landroid/content/Context;
    //   17: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 10
    //   22: new 189	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 190	org/json/JSONArray:<init>	()V
    //   29: astore 14
    //   31: ldc_w 463
    //   34: invokestatic 409	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   37: astore 11
    //   39: invokestatic 363	com/cootek/usage/m:a	()Lcom/cootek/usage/m;
    //   42: aload_0
    //   43: iconst_1
    //   44: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   47: invokevirtual 366	com/cootek/usage/m:c	(Ljava/lang/String;)J
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
    //   73: ldc_w 316
    //   76: aastore
    //   77: dup
    //   78: iconst_3
    //   79: ldc_w 373
    //   82: aastore
    //   83: dup
    //   84: iconst_4
    //   85: ldc_w 296
    //   88: aastore
    //   89: dup
    //   90: iconst_5
    //   91: ldc -111
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
    //   125: ifnull +294 -> 419
    //   128: aload 10
    //   130: astore 11
    //   132: aload 10
    //   134: invokeinterface 88 1 0
    //   139: ifeq +280 -> 419
    //   142: aload 10
    //   144: astore 11
    //   146: aload 13
    //   148: aload 10
    //   150: iconst_0
    //   151: invokeinterface 95 2 0
    //   156: putfield 382	com/cootek/usage/g$f:b	J
    //   159: aload 10
    //   161: astore 11
    //   163: aload 10
    //   165: iconst_1
    //   166: invokeinterface 102 2 0
    //   171: astore 12
    //   173: aload 10
    //   175: astore 11
    //   177: aload 10
    //   179: iconst_2
    //   180: invokeinterface 95 2 0
    //   185: lstore 7
    //   187: aload 10
    //   189: astore 11
    //   191: aload 10
    //   193: iconst_3
    //   194: invokeinterface 95 2 0
    //   199: lstore_3
    //   200: aload 10
    //   202: astore 11
    //   204: aload 10
    //   206: iconst_4
    //   207: invokeinterface 246 2 0
    //   212: istore_1
    //   213: aload 10
    //   215: astore 11
    //   217: aload 10
    //   219: iconst_5
    //   220: invokeinterface 95 2 0
    //   225: lstore 5
    //   227: aload 10
    //   229: astore 11
    //   231: aload 10
    //   233: bipush 6
    //   235: invokeinterface 246 2 0
    //   240: istore_2
    //   241: aload 10
    //   243: astore 11
    //   245: new 211	org/json/JSONObject
    //   248: dup
    //   249: invokespecial 212	org/json/JSONObject:<init>	()V
    //   252: astore 15
    //   254: aload 10
    //   256: astore 11
    //   258: aload 15
    //   260: ldc_w 384
    //   263: aload 12
    //   265: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   268: pop
    //   269: aload 10
    //   271: astore 11
    //   273: aload 15
    //   275: ldc_w 316
    //   278: lload 7
    //   280: ldc2_w 385
    //   283: ldiv
    //   284: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   287: pop
    //   288: iconst_3
    //   289: iload_1
    //   290: if_icmpne +5 -> 295
    //   293: lconst_0
    //   294: lstore_3
    //   295: aload 10
    //   297: astore 11
    //   299: aload 15
    //   301: ldc_w 373
    //   304: lload_3
    //   305: invokevirtual 389	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   308: pop
    //   309: lload 5
    //   311: lconst_0
    //   312: lcmp
    //   313: ifeq +196 -> 509
    //   316: iconst_1
    //   317: istore 9
    //   319: aload 10
    //   321: astore 11
    //   323: aload 15
    //   325: ldc_w 393
    //   328: iload 9
    //   330: invokevirtual 396	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   333: pop
    //   334: iload_1
    //   335: iconst_2
    //   336: if_icmpne +179 -> 515
    //   339: ldc_w 391
    //   342: astore 12
    //   344: aload 10
    //   346: astore 11
    //   348: aload 15
    //   350: ldc_w 296
    //   353: aload 12
    //   355: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   358: pop
    //   359: iload_2
    //   360: ifne +163 -> 523
    //   363: aload 10
    //   365: astore 11
    //   367: aload 15
    //   369: ldc_w 467
    //   372: ldc_w 469
    //   375: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   378: pop
    //   379: aload 10
    //   381: astore 11
    //   383: aload 14
    //   385: aload 15
    //   387: invokevirtual 224	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   390: pop
    //   391: aload 10
    //   393: astore 11
    //   395: aload 13
    //   397: iconst_1
    //   398: putfield 232	com/cootek/usage/g$f:d	Z
    //   401: aload 10
    //   403: astore 11
    //   405: aload 10
    //   407: invokeinterface 137 1 0
    //   412: istore 9
    //   414: iload 9
    //   416: ifne -257 -> 159
    //   419: aload 10
    //   421: ifnull +10 -> 431
    //   424: aload 10
    //   426: invokeinterface 140 1 0
    //   431: new 211	org/json/JSONObject
    //   434: dup
    //   435: invokespecial 212	org/json/JSONObject:<init>	()V
    //   438: astore 10
    //   440: aload 10
    //   442: ldc_w 398
    //   445: aload 14
    //   447: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   450: pop
    //   451: new 328	com/cootek/usage/UsageData
    //   454: dup
    //   455: invokespecial 329	com/cootek/usage/UsageData:<init>	()V
    //   458: astore 11
    //   460: aload 11
    //   462: aload_0
    //   463: invokevirtual 331	com/cootek/usage/g:b	()Ljava/lang/String;
    //   466: putfield 333	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   469: aload 11
    //   471: aload_0
    //   472: iconst_4
    //   473: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   476: putfield 338	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   479: aload 11
    //   481: aload 10
    //   483: invokevirtual 399	org/json/JSONObject:toString	()Ljava/lang/String;
    //   486: putfield 344	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   489: aload 13
    //   491: aload 11
    //   493: putfield 347	com/cootek/usage/g$f:a	Lcom/cootek/usage/UsageData;
    //   496: aload 13
    //   498: aload_0
    //   499: iconst_4
    //   500: invokevirtual 335	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   503: putfield 348	com/cootek/usage/g$f:c	Ljava/lang/String;
    //   506: aload 13
    //   508: areturn
    //   509: iconst_0
    //   510: istore 9
    //   512: goto -193 -> 319
    //   515: ldc_w 401
    //   518: astore 12
    //   520: goto -176 -> 344
    //   523: iload_2
    //   524: iconst_1
    //   525: if_icmpne +59 -> 584
    //   528: aload 10
    //   530: astore 11
    //   532: aload 15
    //   534: ldc_w 467
    //   537: ldc_w 471
    //   540: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   543: pop
    //   544: goto -165 -> 379
    //   547: astore 12
    //   549: aload 10
    //   551: astore 11
    //   553: aload 12
    //   555: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   558: goto -157 -> 401
    //   561: astore 11
    //   563: aload 13
    //   565: iconst_0
    //   566: putfield 232	com/cootek/usage/g$f:d	Z
    //   569: aload 10
    //   571: ifnull +10 -> 581
    //   574: aload 10
    //   576: invokeinterface 140 1 0
    //   581: aload 13
    //   583: areturn
    //   584: iload_2
    //   585: iconst_2
    //   586: if_icmpne +58 -> 644
    //   589: aload 10
    //   591: astore 11
    //   593: aload 15
    //   595: ldc_w 467
    //   598: ldc_w 473
    //   601: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   604: pop
    //   605: goto -226 -> 379
    //   608: astore 12
    //   610: aload 10
    //   612: astore 11
    //   614: aload 12
    //   616: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   619: aload 10
    //   621: astore 11
    //   623: aload 13
    //   625: iconst_0
    //   626: putfield 232	com/cootek/usage/g$f:d	Z
    //   629: aload 10
    //   631: ifnull +10 -> 641
    //   634: aload 10
    //   636: invokeinterface 140 1 0
    //   641: aload 13
    //   643: areturn
    //   644: iload_2
    //   645: iconst_3
    //   646: if_icmpne -267 -> 379
    //   649: aload 10
    //   651: astore 11
    //   653: aload 15
    //   655: ldc_w 467
    //   658: ldc_w 475
    //   661: invokevirtual 217	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   664: pop
    //   665: goto -286 -> 379
    //   668: astore 10
    //   670: aload 11
    //   672: ifnull +10 -> 682
    //   675: aload 11
    //   677: invokeinterface 140 1 0
    //   682: aload 10
    //   684: athrow
    //   685: astore 10
    //   687: aload 10
    //   689: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   692: goto -261 -> 431
    //   695: astore 10
    //   697: aload 10
    //   699: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   702: goto -121 -> 581
    //   705: astore 10
    //   707: aload 10
    //   709: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   712: goto -71 -> 641
    //   715: astore 11
    //   717: aload 11
    //   719: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   722: goto -40 -> 682
    //   725: astore 11
    //   727: aload 11
    //   729: invokestatic 229	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   732: goto -281 -> 451
    //   735: astore 10
    //   737: aconst_null
    //   738: astore 11
    //   740: goto -70 -> 670
    //   743: astore 12
    //   745: aload 10
    //   747: astore 11
    //   749: aload 12
    //   751: astore 10
    //   753: goto -83 -> 670
    //   756: astore 12
    //   758: aconst_null
    //   759: astore 10
    //   761: goto -151 -> 610
    //   764: astore 10
    //   766: aconst_null
    //   767: astore 10
    //   769: goto -206 -> 563
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	772	0	this	g
    //   212	125	1	i	int
    //   240	407	2	j	int
    //   50	255	3	l1	long
    //   225	85	5	l2	long
    //   185	94	7	l3	long
    //   317	194	9	bool	boolean
    //   20	630	10	localObject1	Object
    //   668	15	10	localObject2	Object
    //   685	3	10	localRuntimeException1	RuntimeException
    //   695	3	10	localRuntimeException2	RuntimeException
    //   705	3	10	localRuntimeException3	RuntimeException
    //   735	11	10	localObject3	Object
    //   751	9	10	localObject4	Object
    //   764	1	10	localSecurityException1	SecurityException
    //   767	1	10	localObject5	Object
    //   37	515	11	localObject6	Object
    //   561	1	11	localSecurityException2	SecurityException
    //   591	85	11	localObject7	Object
    //   715	3	11	localRuntimeException4	RuntimeException
    //   725	3	11	localJSONException1	JSONException
    //   738	10	11	localObject8	Object
    //   171	348	12	str	String
    //   547	7	12	localJSONException2	JSONException
    //   608	7	12	localRuntimeException5	RuntimeException
    //   743	7	12	localObject9	Object
    //   756	1	12	localRuntimeException6	RuntimeException
    //   8	634	13	localF	f
    //   29	417	14	localJSONArray	JSONArray
    //   252	402	15	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   258	269	547	org/json/JSONException
    //   273	288	547	org/json/JSONException
    //   299	309	547	org/json/JSONException
    //   323	334	547	org/json/JSONException
    //   348	359	547	org/json/JSONException
    //   367	379	547	org/json/JSONException
    //   383	391	547	org/json/JSONException
    //   395	401	547	org/json/JSONException
    //   532	544	547	org/json/JSONException
    //   593	605	547	org/json/JSONException
    //   653	665	547	org/json/JSONException
    //   132	142	561	java/lang/SecurityException
    //   146	159	561	java/lang/SecurityException
    //   163	173	561	java/lang/SecurityException
    //   177	187	561	java/lang/SecurityException
    //   191	200	561	java/lang/SecurityException
    //   204	213	561	java/lang/SecurityException
    //   217	227	561	java/lang/SecurityException
    //   231	241	561	java/lang/SecurityException
    //   245	254	561	java/lang/SecurityException
    //   258	269	561	java/lang/SecurityException
    //   273	288	561	java/lang/SecurityException
    //   299	309	561	java/lang/SecurityException
    //   323	334	561	java/lang/SecurityException
    //   348	359	561	java/lang/SecurityException
    //   367	379	561	java/lang/SecurityException
    //   383	391	561	java/lang/SecurityException
    //   395	401	561	java/lang/SecurityException
    //   405	414	561	java/lang/SecurityException
    //   532	544	561	java/lang/SecurityException
    //   553	558	561	java/lang/SecurityException
    //   593	605	561	java/lang/SecurityException
    //   653	665	561	java/lang/SecurityException
    //   132	142	608	java/lang/RuntimeException
    //   146	159	608	java/lang/RuntimeException
    //   163	173	608	java/lang/RuntimeException
    //   177	187	608	java/lang/RuntimeException
    //   191	200	608	java/lang/RuntimeException
    //   204	213	608	java/lang/RuntimeException
    //   217	227	608	java/lang/RuntimeException
    //   231	241	608	java/lang/RuntimeException
    //   245	254	608	java/lang/RuntimeException
    //   258	269	608	java/lang/RuntimeException
    //   273	288	608	java/lang/RuntimeException
    //   299	309	608	java/lang/RuntimeException
    //   323	334	608	java/lang/RuntimeException
    //   348	359	608	java/lang/RuntimeException
    //   367	379	608	java/lang/RuntimeException
    //   383	391	608	java/lang/RuntimeException
    //   395	401	608	java/lang/RuntimeException
    //   405	414	608	java/lang/RuntimeException
    //   532	544	608	java/lang/RuntimeException
    //   553	558	608	java/lang/RuntimeException
    //   593	605	608	java/lang/RuntimeException
    //   653	665	608	java/lang/RuntimeException
    //   132	142	668	finally
    //   146	159	668	finally
    //   163	173	668	finally
    //   177	187	668	finally
    //   191	200	668	finally
    //   204	213	668	finally
    //   217	227	668	finally
    //   231	241	668	finally
    //   245	254	668	finally
    //   258	269	668	finally
    //   273	288	668	finally
    //   299	309	668	finally
    //   323	334	668	finally
    //   348	359	668	finally
    //   367	379	668	finally
    //   383	391	668	finally
    //   395	401	668	finally
    //   405	414	668	finally
    //   532	544	668	finally
    //   553	558	668	finally
    //   593	605	668	finally
    //   614	619	668	finally
    //   623	629	668	finally
    //   653	665	668	finally
    //   424	431	685	java/lang/RuntimeException
    //   574	581	695	java/lang/RuntimeException
    //   634	641	705	java/lang/RuntimeException
    //   675	682	715	java/lang/RuntimeException
    //   440	451	725	org/json/JSONException
    //   31	123	735	finally
    //   563	569	743	finally
    //   31	123	756	java/lang/RuntimeException
    //   31	123	764	java/lang/SecurityException
  }
  
  private String g(int paramInt)
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
  
  private f h()
  {
    f localF = new f();
    Object localObject = new JSONArray();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      String str1 = k.a(c.a.getContext());
      String str2 = k.b(c.a.getContext());
      localJSONObject.put("phone", str1);
      localJSONObject.put("IMSI", str2);
      ((JSONArray)localObject).put(localJSONObject);
      localF.d = true;
      localJSONObject = new JSONObject();
    }
    catch (JSONException localJSONException2)
    {
      try
      {
        localJSONObject.put("data", localObject);
        localObject = new UsageData();
        ((UsageData)localObject).type = b();
        ((UsageData)localObject).path = a(5);
        ((UsageData)localObject).value = localJSONObject.toString();
        localF.a = ((UsageData)localObject);
        localF.c = a(5);
        return localF;
        localJSONException2 = localJSONException2;
        com.google.a.a.a.a.a.a.a(localJSONException2);
      }
      catch (JSONException localJSONException1)
      {
        for (;;)
        {
          com.google.a.a.a.a.a.a.a(localJSONException1);
        }
      }
    }
  }
  
  private String h(int paramInt)
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
  
  private String i(int paramInt)
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
  
  int a()
  {
    return 6;
  }
  
  String a(int paramInt)
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
  
  f b(int paramInt)
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
    HashSet<g.c> d;
    HashSet<g.g> e;
    HashSet<g.e> f;
    HashSet<g.a> g;
    HashSet<g.d> h;
    HashSet<g.h> i;
    
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
