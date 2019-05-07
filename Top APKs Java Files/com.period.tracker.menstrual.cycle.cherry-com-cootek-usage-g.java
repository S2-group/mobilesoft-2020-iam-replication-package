package com.cootek.usage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.a.a.a.a.a.a;
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
      StringBuilder localStringBuilder = new StringBuilder("error info value: ");
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
  
  static String b()
  {
    return "noah_info";
  }
  
  /* Error */
  private n c()
  {
    // Byte code:
    //   0: new 74	com/cootek/usage/n
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 77	com/cootek/usage/n:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 6
    //   10: aload_0
    //   11: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 83	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 89	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 4
    //   22: new 91	java/util/Hashtable
    //   25: dup
    //   26: invokespecial 92	java/util/Hashtable:<init>	()V
    //   29: astore 7
    //   31: aload 4
    //   33: getstatic 98	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
    //   36: iconst_2
    //   37: anewarray 100	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: ldc 102
    //   44: aastore
    //   45: dup
    //   46: iconst_1
    //   47: ldc 104
    //   49: aastore
    //   50: aconst_null
    //   51: aconst_null
    //   52: aconst_null
    //   53: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   56: astore_3
    //   57: aload_3
    //   58: ifnull +180 -> 238
    //   61: aload_3
    //   62: invokeinterface 116 1 0
    //   67: ifeq +171 -> 238
    //   70: new 118	com/cootek/usage/j
    //   73: dup
    //   74: aload_0
    //   75: iconst_0
    //   76: invokespecial 121	com/cootek/usage/j:<init>	(Lcom/cootek/usage/g;B)V
    //   79: astore 5
    //   81: aload 5
    //   83: aload_3
    //   84: iconst_0
    //   85: invokeinterface 125 2 0
    //   90: putfield 128	com/cootek/usage/j:a	J
    //   93: aload 5
    //   95: aload_3
    //   96: iconst_1
    //   97: invokeinterface 131 2 0
    //   102: putfield 133	com/cootek/usage/j:b	Ljava/lang/String;
    //   105: aload 5
    //   107: new 135	java/util/HashSet
    //   110: dup
    //   111: invokespecial 136	java/util/HashSet:<init>	()V
    //   114: putfield 139	com/cootek/usage/j:c	Ljava/util/HashSet;
    //   117: aload 5
    //   119: new 135	java/util/HashSet
    //   122: dup
    //   123: invokespecial 136	java/util/HashSet:<init>	()V
    //   126: putfield 141	com/cootek/usage/j:d	Ljava/util/HashSet;
    //   129: aload 5
    //   131: new 135	java/util/HashSet
    //   134: dup
    //   135: invokespecial 136	java/util/HashSet:<init>	()V
    //   138: putfield 143	com/cootek/usage/j:e	Ljava/util/HashSet;
    //   141: aload 5
    //   143: new 135	java/util/HashSet
    //   146: dup
    //   147: invokespecial 136	java/util/HashSet:<init>	()V
    //   150: putfield 145	com/cootek/usage/j:f	Ljava/util/HashSet;
    //   153: aload 5
    //   155: new 135	java/util/HashSet
    //   158: dup
    //   159: invokespecial 136	java/util/HashSet:<init>	()V
    //   162: putfield 147	com/cootek/usage/j:g	Ljava/util/HashSet;
    //   165: aload 5
    //   167: new 135	java/util/HashSet
    //   170: dup
    //   171: invokespecial 136	java/util/HashSet:<init>	()V
    //   174: putfield 149	com/cootek/usage/j:h	Ljava/util/HashSet;
    //   177: aload 5
    //   179: new 135	java/util/HashSet
    //   182: dup
    //   183: invokespecial 136	java/util/HashSet:<init>	()V
    //   186: putfield 151	com/cootek/usage/j:i	Ljava/util/HashSet;
    //   189: aload 7
    //   191: aload 5
    //   193: getfield 128	com/cootek/usage/j:a	J
    //   196: invokestatic 157	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   199: aload 5
    //   201: invokevirtual 161	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   204: pop
    //   205: aload_3
    //   206: invokeinterface 164 1 0
    //   211: istore_2
    //   212: iload_2
    //   213: ifne -143 -> 70
    //   216: goto +22 -> 238
    //   219: astore 4
    //   221: goto +2651 -> 2872
    //   224: astore 5
    //   226: aload_3
    //   227: astore 4
    //   229: goto +2566 -> 2795
    //   232: aload_3
    //   233: astore 4
    //   235: goto +2603 -> 2838
    //   238: aload_3
    //   239: ifnull +17 -> 256
    //   242: aload_3
    //   243: invokeinterface 167 1 0
    //   248: goto +8 -> 256
    //   251: astore_3
    //   252: aload_3
    //   253: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   256: aload 4
    //   258: getstatic 175	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   261: bipush 12
    //   263: anewarray 100	java/lang/String
    //   266: dup
    //   267: iconst_0
    //   268: ldc -79
    //   270: aastore
    //   271: dup
    //   272: iconst_1
    //   273: ldc -77
    //   275: aastore
    //   276: dup
    //   277: iconst_2
    //   278: ldc -75
    //   280: aastore
    //   281: dup
    //   282: iconst_3
    //   283: ldc -73
    //   285: aastore
    //   286: dup
    //   287: iconst_4
    //   288: ldc -71
    //   290: aastore
    //   291: dup
    //   292: iconst_5
    //   293: ldc -69
    //   295: aastore
    //   296: dup
    //   297: bipush 6
    //   299: ldc -67
    //   301: aastore
    //   302: dup
    //   303: bipush 7
    //   305: ldc -65
    //   307: aastore
    //   308: dup
    //   309: bipush 8
    //   311: ldc -63
    //   313: aastore
    //   314: dup
    //   315: bipush 9
    //   317: ldc -61
    //   319: aastore
    //   320: dup
    //   321: bipush 10
    //   323: ldc -59
    //   325: aastore
    //   326: dup
    //   327: bipush 11
    //   329: ldc -57
    //   331: aastore
    //   332: ldc -55
    //   334: bipush 7
    //   336: anewarray 100	java/lang/String
    //   339: dup
    //   340: iconst_0
    //   341: ldc -53
    //   343: aastore
    //   344: dup
    //   345: iconst_1
    //   346: ldc -51
    //   348: aastore
    //   349: dup
    //   350: iconst_2
    //   351: ldc -49
    //   353: aastore
    //   354: dup
    //   355: iconst_3
    //   356: ldc -47
    //   358: aastore
    //   359: dup
    //   360: iconst_4
    //   361: ldc -45
    //   363: aastore
    //   364: dup
    //   365: iconst_5
    //   366: ldc -43
    //   368: aastore
    //   369: dup
    //   370: bipush 6
    //   372: ldc -41
    //   374: aastore
    //   375: aconst_null
    //   376: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   379: astore 5
    //   381: aload 5
    //   383: ifnull +1019 -> 1402
    //   386: aload 5
    //   388: invokeinterface 116 1 0
    //   393: istore_2
    //   394: iload_2
    //   395: ifeq +1007 -> 1402
    //   398: aload 7
    //   400: aload 5
    //   402: iconst_0
    //   403: invokeinterface 125 2 0
    //   408: invokestatic 157	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   411: invokevirtual 219	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   414: checkcast 118	com/cootek/usage/j
    //   417: astore 8
    //   419: aload 8
    //   421: ifnonnull +6 -> 427
    //   424: goto +2486 -> 2910
    //   427: aload 5
    //   429: iconst_1
    //   430: invokeinterface 131 2 0
    //   435: astore_3
    //   436: ldc -53
    //   438: aload_3
    //   439: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   442: istore_2
    //   443: iload_2
    //   444: ifeq +33 -> 477
    //   447: aload 5
    //   449: iconst_2
    //   450: invokeinterface 131 2 0
    //   455: astore 4
    //   457: aload 8
    //   459: getfield 139	com/cootek/usage/j:c	Ljava/util/HashSet;
    //   462: astore_3
    //   463: aload_3
    //   464: aload 4
    //   466: invokevirtual 226	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   469: pop
    //   470: goto +2440 -> 2910
    //   473: astore_3
    //   474: goto +887 -> 1361
    //   477: ldc -51
    //   479: aload_3
    //   480: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   483: istore_2
    //   484: iload_2
    //   485: ifeq +107 -> 592
    //   488: new 228	com/cootek/usage/k
    //   491: dup
    //   492: aload_0
    //   493: iconst_0
    //   494: invokespecial 229	com/cootek/usage/k:<init>	(Lcom/cootek/usage/g;B)V
    //   497: astore 4
    //   499: aload 4
    //   501: aload 5
    //   503: iconst_2
    //   504: invokeinterface 131 2 0
    //   509: putfield 231	com/cootek/usage/k:a	Ljava/lang/String;
    //   512: aload 5
    //   514: iconst_3
    //   515: invokeinterface 235 2 0
    //   520: tableswitch	default:+2393->2913, 0:+2425->2945, 1:+2419->2939, 2:+2412->2932, 3:+2406->2926, 4:+2399->2919
    //   556: aload 4
    //   558: aload_3
    //   559: putfield 236	com/cootek/usage/k:b	Ljava/lang/String;
    //   562: aload 4
    //   564: getfield 236	com/cootek/usage/k:b	Ljava/lang/String;
    //   567: ifnonnull +16 -> 583
    //   570: aload 4
    //   572: aload 5
    //   574: iconst_4
    //   575: invokeinterface 131 2 0
    //   580: putfield 236	com/cootek/usage/k:b	Ljava/lang/String;
    //   583: aload 8
    //   585: getfield 141	com/cootek/usage/j:d	Ljava/util/HashSet;
    //   588: astore_3
    //   589: goto -126 -> 463
    //   592: ldc -49
    //   594: aload_3
    //   595: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   598: istore_2
    //   599: iload_2
    //   600: ifeq +132 -> 732
    //   603: new 238	com/cootek/usage/o
    //   606: dup
    //   607: aload_0
    //   608: iconst_0
    //   609: invokespecial 239	com/cootek/usage/o:<init>	(Lcom/cootek/usage/g;B)V
    //   612: astore 4
    //   614: aload 4
    //   616: aload 5
    //   618: iconst_2
    //   619: invokeinterface 131 2 0
    //   624: putfield 240	com/cootek/usage/o:b	Ljava/lang/String;
    //   627: aload 4
    //   629: aload 5
    //   631: iconst_5
    //   632: invokeinterface 131 2 0
    //   637: putfield 242	com/cootek/usage/o:c	Ljava/lang/String;
    //   640: aload 5
    //   642: bipush 6
    //   644: invokeinterface 131 2 0
    //   649: astore_3
    //   650: aload 4
    //   652: aload_3
    //   653: putfield 244	com/cootek/usage/o:d	Ljava/lang/String;
    //   656: aload 5
    //   658: iconst_3
    //   659: invokeinterface 235 2 0
    //   664: tableswitch	default:+2286->2950, 0:+2305->2969, 1:+2298->2962, 2:+2292->2956
    //   692: aload 4
    //   694: aload_3
    //   695: putfield 245	com/cootek/usage/o:a	Ljava/lang/String;
    //   698: aload 4
    //   700: getfield 245	com/cootek/usage/o:a	Ljava/lang/String;
    //   703: ifnonnull +16 -> 719
    //   706: aload 4
    //   708: aload 5
    //   710: iconst_4
    //   711: invokeinterface 131 2 0
    //   716: putfield 245	com/cootek/usage/o:a	Ljava/lang/String;
    //   719: aload 8
    //   721: getfield 143	com/cootek/usage/j:e	Ljava/util/HashSet;
    //   724: astore_3
    //   725: goto -262 -> 463
    //   728: astore_3
    //   729: goto -255 -> 474
    //   732: ldc -47
    //   734: aload_3
    //   735: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   738: ifeq +249 -> 987
    //   741: new 247	com/cootek/usage/m
    //   744: dup
    //   745: aload_0
    //   746: iconst_0
    //   747: invokespecial 248	com/cootek/usage/m:<init>	(Lcom/cootek/usage/g;B)V
    //   750: astore 4
    //   752: aload 4
    //   754: aload 5
    //   756: iconst_2
    //   757: invokeinterface 131 2 0
    //   762: putfield 249	com/cootek/usage/m:a	Ljava/lang/String;
    //   765: aload 5
    //   767: iconst_3
    //   768: invokeinterface 235 2 0
    //   773: tableswitch	default:+2201->2974, 0:+49->822, 1:+43->816, 2:+2204->2977, 3:+37->810
    //   804: ldc -5
    //   806: astore_3
    //   807: goto +17 -> 824
    //   810: ldc -3
    //   812: astore_3
    //   813: goto +11 -> 824
    //   816: ldc -1
    //   818: astore_3
    //   819: goto +5 -> 824
    //   822: aconst_null
    //   823: astore_3
    //   824: aload 4
    //   826: aload_3
    //   827: putfield 256	com/cootek/usage/m:b	Ljava/lang/String;
    //   830: aload 4
    //   832: getfield 256	com/cootek/usage/m:b	Ljava/lang/String;
    //   835: astore_3
    //   836: aload_3
    //   837: ifnonnull +16 -> 853
    //   840: aload 4
    //   842: aload 5
    //   844: iconst_4
    //   845: invokeinterface 131 2 0
    //   850: putfield 256	com/cootek/usage/m:b	Ljava/lang/String;
    //   853: aload 5
    //   855: bipush 6
    //   857: invokeinterface 235 2 0
    //   862: tableswitch	default:+2122->2984, -1:+74->936, 0:+67->929, 1:+2167->3029, 2:+2160->3022, 3:+2153->3015, 4:+2146->3008, 5:+2139->3001, 6:+2132->2994, 7:+2125->2987, 8:+60->922
    //   916: ldc -5
    //   918: astore_3
    //   919: goto +19 -> 938
    //   922: ldc_w 258
    //   925: astore_3
    //   926: goto +12 -> 938
    //   929: ldc_w 260
    //   932: astore_3
    //   933: goto +5 -> 938
    //   936: aconst_null
    //   937: astore_3
    //   938: aload 4
    //   940: aload_3
    //   941: putfield 261	com/cootek/usage/m:c	Ljava/lang/String;
    //   944: aload 4
    //   946: getfield 261	com/cootek/usage/m:c	Ljava/lang/String;
    //   949: astore_3
    //   950: aload_3
    //   951: ifnonnull +2085 -> 3036
    //   954: aload 4
    //   956: aload 5
    //   958: bipush 7
    //   960: invokeinterface 131 2 0
    //   965: putfield 261	com/cootek/usage/m:c	Ljava/lang/String;
    //   968: goto +3 -> 971
    //   971: aload 8
    //   973: getfield 145	com/cootek/usage/j:f	Ljava/util/HashSet;
    //   976: astore_3
    //   977: aload_3
    //   978: aload 4
    //   980: invokevirtual 226	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   983: pop
    //   984: goto +381 -> 1365
    //   987: ldc -45
    //   989: aload_3
    //   990: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   993: ifeq +103 -> 1096
    //   996: new 263	com/cootek/usage/i
    //   999: dup
    //   1000: aload_0
    //   1001: iconst_0
    //   1002: invokespecial 264	com/cootek/usage/i:<init>	(Lcom/cootek/usage/g;B)V
    //   1005: astore 4
    //   1007: aload 4
    //   1009: aload 5
    //   1011: iconst_2
    //   1012: invokeinterface 131 2 0
    //   1017: putfield 265	com/cootek/usage/i:a	Ljava/lang/String;
    //   1020: aload 5
    //   1022: iconst_3
    //   1023: invokeinterface 235 2 0
    //   1028: tableswitch	default:+2015->3043, 0:+2040->3068, 1:+2034->3062, 2:+2027->3055, 3:+2021->3049
    //   1060: aload 4
    //   1062: aload_3
    //   1063: putfield 266	com/cootek/usage/i:b	Ljava/lang/String;
    //   1066: aload 4
    //   1068: getfield 266	com/cootek/usage/i:b	Ljava/lang/String;
    //   1071: ifnonnull +16 -> 1087
    //   1074: aload 4
    //   1076: aload 5
    //   1078: iconst_4
    //   1079: invokeinterface 131 2 0
    //   1084: putfield 266	com/cootek/usage/i:b	Ljava/lang/String;
    //   1087: aload 8
    //   1089: getfield 147	com/cootek/usage/j:g	Ljava/util/HashSet;
    //   1092: astore_3
    //   1093: goto -116 -> 977
    //   1096: ldc -43
    //   1098: aload_3
    //   1099: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1102: ifeq +102 -> 1204
    //   1105: new 268	com/cootek/usage/l
    //   1108: dup
    //   1109: aload_0
    //   1110: iconst_0
    //   1111: invokespecial 269	com/cootek/usage/l:<init>	(Lcom/cootek/usage/g;B)V
    //   1114: astore 4
    //   1116: aload 4
    //   1118: aload 5
    //   1120: iconst_2
    //   1121: invokeinterface 131 2 0
    //   1126: putfield 270	com/cootek/usage/l:a	Ljava/lang/String;
    //   1129: aload 5
    //   1131: iconst_3
    //   1132: invokeinterface 235 2 0
    //   1137: tableswitch	default:+1936->3073, 0:+1962->3099, 1:+1955->3092, 2:+1949->3086, 3:+1942->3079
    //   1168: aload 4
    //   1170: aload_3
    //   1171: putfield 271	com/cootek/usage/l:b	Ljava/lang/String;
    //   1174: aload 4
    //   1176: getfield 271	com/cootek/usage/l:b	Ljava/lang/String;
    //   1179: ifnonnull +16 -> 1195
    //   1182: aload 4
    //   1184: aload 5
    //   1186: iconst_4
    //   1187: invokeinterface 131 2 0
    //   1192: putfield 271	com/cootek/usage/l:b	Ljava/lang/String;
    //   1195: aload 8
    //   1197: getfield 149	com/cootek/usage/j:h	Ljava/util/HashSet;
    //   1200: astore_3
    //   1201: goto -224 -> 977
    //   1204: ldc -41
    //   1206: aload_3
    //   1207: invokevirtual 223	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1210: ifeq +155 -> 1365
    //   1213: new 273	com/cootek/usage/p
    //   1216: dup
    //   1217: aload_0
    //   1218: iconst_0
    //   1219: invokespecial 274	com/cootek/usage/p:<init>	(Lcom/cootek/usage/g;B)V
    //   1222: astore 4
    //   1224: aload 4
    //   1226: aload 5
    //   1228: iconst_2
    //   1229: invokeinterface 131 2 0
    //   1234: putfield 275	com/cootek/usage/p:a	Ljava/lang/String;
    //   1237: aload 5
    //   1239: iconst_3
    //   1240: invokeinterface 235 2 0
    //   1245: tableswitch	default:+1859->3104, 0:+1963->3208, 1:+1956->3201, 2:+1949->3194, 3:+1942->3187, 4:+1935->3180, 5:+1928->3173, 6:+1921->3166, 7:+1914->3159, 8:+1907->3152, 9:+1900->3145, 10:+1893->3138, 11:+1886->3131, 12:+1879->3124, 13:+1872->3117, 14:+1865->3110
    //   1320: aload 4
    //   1322: aload_3
    //   1323: putfield 276	com/cootek/usage/p:b	Ljava/lang/String;
    //   1326: aload 4
    //   1328: getfield 276	com/cootek/usage/p:b	Ljava/lang/String;
    //   1331: ifnonnull +16 -> 1347
    //   1334: aload 4
    //   1336: aload 5
    //   1338: iconst_4
    //   1339: invokeinterface 131 2 0
    //   1344: putfield 276	com/cootek/usage/p:b	Ljava/lang/String;
    //   1347: aload 8
    //   1349: getfield 151	com/cootek/usage/j:i	Ljava/util/HashSet;
    //   1352: astore_3
    //   1353: goto -376 -> 977
    //   1356: astore_3
    //   1357: goto +4 -> 1361
    //   1360: astore_3
    //   1361: aload_3
    //   1362: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   1365: aload 5
    //   1367: invokeinterface 164 1 0
    //   1372: istore_2
    //   1373: iload_2
    //   1374: ifne -976 -> 398
    //   1377: goto +25 -> 1402
    //   1380: astore_3
    //   1381: goto +1378 -> 2759
    //   1384: astore_3
    //   1385: aload 5
    //   1387: astore 4
    //   1389: aload_3
    //   1390: astore 5
    //   1392: goto +1284 -> 2676
    //   1395: aload 5
    //   1397: astore 4
    //   1399: goto +1320 -> 2719
    //   1402: aload 5
    //   1404: ifnull +18 -> 1422
    //   1407: aload 5
    //   1409: invokeinterface 167 1 0
    //   1414: goto +8 -> 1422
    //   1417: astore_3
    //   1418: aload_3
    //   1419: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   1422: new 278	org/json/JSONArray
    //   1425: dup
    //   1426: invokespecial 279	org/json/JSONArray:<init>	()V
    //   1429: astore_3
    //   1430: aload 7
    //   1432: invokevirtual 283	java/util/Hashtable:values	()Ljava/util/Collection;
    //   1435: invokeinterface 289 1 0
    //   1440: astore 4
    //   1442: aload 4
    //   1444: invokeinterface 294 1 0
    //   1449: ifeq +1078 -> 2527
    //   1452: aload 4
    //   1454: invokeinterface 298 1 0
    //   1459: checkcast 118	com/cootek/usage/j
    //   1462: astore 7
    //   1464: new 300	org/json/JSONObject
    //   1467: dup
    //   1468: invokespecial 301	org/json/JSONObject:<init>	()V
    //   1471: astore 5
    //   1473: aload 5
    //   1475: ldc_w 303
    //   1478: aload 7
    //   1480: getfield 133	com/cootek/usage/j:b	Ljava/lang/String;
    //   1483: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1486: pop
    //   1487: aload 7
    //   1489: getfield 139	com/cootek/usage/j:c	Ljava/util/HashSet;
    //   1492: invokevirtual 309	java/util/HashSet:isEmpty	()Z
    //   1495: ifne +70 -> 1565
    //   1498: new 278	org/json/JSONArray
    //   1501: dup
    //   1502: invokespecial 279	org/json/JSONArray:<init>	()V
    //   1505: astore 8
    //   1507: aload 7
    //   1509: getfield 139	com/cootek/usage/j:c	Ljava/util/HashSet;
    //   1512: invokevirtual 310	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1515: astore 9
    //   1517: aload 9
    //   1519: invokeinterface 294 1 0
    //   1524: ifeq +22 -> 1546
    //   1527: aload 8
    //   1529: aload 9
    //   1531: invokeinterface 298 1 0
    //   1536: checkcast 100	java/lang/String
    //   1539: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1542: pop
    //   1543: goto -26 -> 1517
    //   1546: aload 8
    //   1548: invokevirtual 316	org/json/JSONArray:length	()I
    //   1551: ifle +14 -> 1565
    //   1554: aload 5
    //   1556: ldc_w 318
    //   1559: aload 8
    //   1561: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1564: pop
    //   1565: aload 7
    //   1567: getfield 141	com/cootek/usage/j:d	Ljava/util/HashSet;
    //   1570: invokevirtual 309	java/util/HashSet:isEmpty	()Z
    //   1573: ifne +133 -> 1706
    //   1576: new 278	org/json/JSONArray
    //   1579: dup
    //   1580: invokespecial 279	org/json/JSONArray:<init>	()V
    //   1583: astore 8
    //   1585: aload 7
    //   1587: getfield 141	com/cootek/usage/j:d	Ljava/util/HashSet;
    //   1590: invokevirtual 310	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1593: astore 9
    //   1595: aload 9
    //   1597: invokeinterface 294 1 0
    //   1602: ifeq +85 -> 1687
    //   1605: aload 9
    //   1607: invokeinterface 298 1 0
    //   1612: checkcast 228	com/cootek/usage/k
    //   1615: astore 10
    //   1617: new 300	org/json/JSONObject
    //   1620: dup
    //   1621: invokespecial 301	org/json/JSONObject:<init>	()V
    //   1624: astore 11
    //   1626: aload 10
    //   1628: getfield 231	com/cootek/usage/k:a	Ljava/lang/String;
    //   1631: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1634: ifne -39 -> 1595
    //   1637: aload 11
    //   1639: ldc_w 325
    //   1642: aload 10
    //   1644: getfield 231	com/cootek/usage/k:a	Ljava/lang/String;
    //   1647: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1650: pop
    //   1651: aload 10
    //   1653: getfield 236	com/cootek/usage/k:b	Ljava/lang/String;
    //   1656: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1659: ifne +17 -> 1676
    //   1662: aload 11
    //   1664: ldc_w 327
    //   1667: aload 10
    //   1669: getfield 236	com/cootek/usage/k:b	Ljava/lang/String;
    //   1672: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1675: pop
    //   1676: aload 8
    //   1678: aload 11
    //   1680: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1683: pop
    //   1684: goto -89 -> 1595
    //   1687: aload 8
    //   1689: invokevirtual 316	org/json/JSONArray:length	()I
    //   1692: ifle +14 -> 1706
    //   1695: aload 5
    //   1697: ldc_w 329
    //   1700: aload 8
    //   1702: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1705: pop
    //   1706: aload 7
    //   1708: getfield 143	com/cootek/usage/j:e	Ljava/util/HashSet;
    //   1711: invokevirtual 309	java/util/HashSet:isEmpty	()Z
    //   1714: ifne +198 -> 1912
    //   1717: new 278	org/json/JSONArray
    //   1720: dup
    //   1721: invokespecial 279	org/json/JSONArray:<init>	()V
    //   1724: astore 8
    //   1726: aload 7
    //   1728: getfield 143	com/cootek/usage/j:e	Ljava/util/HashSet;
    //   1731: invokevirtual 310	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1734: astore 9
    //   1736: aload 9
    //   1738: invokeinterface 294 1 0
    //   1743: ifeq +150 -> 1893
    //   1746: aload 9
    //   1748: invokeinterface 298 1 0
    //   1753: checkcast 238	com/cootek/usage/o
    //   1756: astore 10
    //   1758: new 300	org/json/JSONObject
    //   1761: dup
    //   1762: invokespecial 301	org/json/JSONObject:<init>	()V
    //   1765: astore 11
    //   1767: aload 10
    //   1769: getfield 245	com/cootek/usage/o:a	Ljava/lang/String;
    //   1772: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1775: ifne +1438 -> 3213
    //   1778: aload 11
    //   1780: ldc_w 327
    //   1783: aload 10
    //   1785: getfield 245	com/cootek/usage/o:a	Ljava/lang/String;
    //   1788: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1791: pop
    //   1792: iconst_1
    //   1793: istore_1
    //   1794: goto +3 -> 1797
    //   1797: aload 10
    //   1799: getfield 245	com/cootek/usage/o:a	Ljava/lang/String;
    //   1802: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1805: ifne +19 -> 1824
    //   1808: aload 11
    //   1810: ldc_w 331
    //   1813: aload 10
    //   1815: getfield 240	com/cootek/usage/o:b	Ljava/lang/String;
    //   1818: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1821: pop
    //   1822: iconst_1
    //   1823: istore_1
    //   1824: aload 10
    //   1826: getfield 245	com/cootek/usage/o:a	Ljava/lang/String;
    //   1829: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1832: ifne +19 -> 1851
    //   1835: aload 11
    //   1837: ldc_w 333
    //   1840: aload 10
    //   1842: getfield 242	com/cootek/usage/o:c	Ljava/lang/String;
    //   1845: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1848: pop
    //   1849: iconst_1
    //   1850: istore_1
    //   1851: aload 10
    //   1853: getfield 245	com/cootek/usage/o:a	Ljava/lang/String;
    //   1856: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1859: ifne +19 -> 1878
    //   1862: aload 11
    //   1864: ldc_w 335
    //   1867: aload 10
    //   1869: getfield 244	com/cootek/usage/o:d	Ljava/lang/String;
    //   1872: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1875: pop
    //   1876: iconst_1
    //   1877: istore_1
    //   1878: iload_1
    //   1879: ifeq -143 -> 1736
    //   1882: aload 8
    //   1884: aload 11
    //   1886: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1889: pop
    //   1890: goto -154 -> 1736
    //   1893: aload 8
    //   1895: invokevirtual 316	org/json/JSONArray:length	()I
    //   1898: ifle +14 -> 1912
    //   1901: aload 5
    //   1903: ldc_w 337
    //   1906: aload 8
    //   1908: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1911: pop
    //   1912: aload 7
    //   1914: getfield 145	com/cootek/usage/j:f	Ljava/util/HashSet;
    //   1917: invokevirtual 309	java/util/HashSet:isEmpty	()Z
    //   1920: ifne +158 -> 2078
    //   1923: new 278	org/json/JSONArray
    //   1926: dup
    //   1927: invokespecial 279	org/json/JSONArray:<init>	()V
    //   1930: astore 8
    //   1932: aload 7
    //   1934: getfield 145	com/cootek/usage/j:f	Ljava/util/HashSet;
    //   1937: invokevirtual 310	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1940: astore 9
    //   1942: aload 9
    //   1944: invokeinterface 294 1 0
    //   1949: ifeq +118 -> 2067
    //   1952: aload 9
    //   1954: invokeinterface 298 1 0
    //   1959: checkcast 247	com/cootek/usage/m
    //   1962: astore 10
    //   1964: new 300	org/json/JSONObject
    //   1967: dup
    //   1968: invokespecial 301	org/json/JSONObject:<init>	()V
    //   1971: astore 11
    //   1973: aload 10
    //   1975: getfield 249	com/cootek/usage/m:a	Ljava/lang/String;
    //   1978: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1981: ifne -39 -> 1942
    //   1984: aload 11
    //   1986: ldc_w 339
    //   1989: aload 10
    //   1991: getfield 249	com/cootek/usage/m:a	Ljava/lang/String;
    //   1994: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1997: pop
    //   1998: aload 10
    //   2000: getfield 256	com/cootek/usage/m:b	Ljava/lang/String;
    //   2003: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2006: ifne +17 -> 2023
    //   2009: aload 11
    //   2011: ldc_w 327
    //   2014: aload 10
    //   2016: getfield 256	com/cootek/usage/m:b	Ljava/lang/String;
    //   2019: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2022: pop
    //   2023: aload 10
    //   2025: getfield 261	com/cootek/usage/m:c	Ljava/lang/String;
    //   2028: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2031: ifne +17 -> 2048
    //   2034: aload 11
    //   2036: ldc_w 341
    //   2039: aload 10
    //   2041: getfield 261	com/cootek/usage/m:c	Ljava/lang/String;
    //   2044: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2047: pop
    //   2048: aload 8
    //   2050: invokevirtual 316	org/json/JSONArray:length	()I
    //   2053: ifle -111 -> 1942
    //   2056: aload 8
    //   2058: aload 11
    //   2060: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2063: pop
    //   2064: goto -122 -> 1942
    //   2067: aload 5
    //   2069: ldc_w 343
    //   2072: aload 8
    //   2074: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2077: pop
    //   2078: aload 7
    //   2080: getfield 147	com/cootek/usage/j:g	Ljava/util/HashSet;
    //   2083: invokevirtual 309	java/util/HashSet:isEmpty	()Z
    //   2086: ifne +133 -> 2219
    //   2089: new 278	org/json/JSONArray
    //   2092: dup
    //   2093: invokespecial 279	org/json/JSONArray:<init>	()V
    //   2096: astore 8
    //   2098: aload 7
    //   2100: getfield 147	com/cootek/usage/j:g	Ljava/util/HashSet;
    //   2103: invokevirtual 310	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2106: astore 9
    //   2108: aload 9
    //   2110: invokeinterface 294 1 0
    //   2115: ifeq +85 -> 2200
    //   2118: aload 9
    //   2120: invokeinterface 298 1 0
    //   2125: checkcast 263	com/cootek/usage/i
    //   2128: astore 10
    //   2130: new 300	org/json/JSONObject
    //   2133: dup
    //   2134: invokespecial 301	org/json/JSONObject:<init>	()V
    //   2137: astore 11
    //   2139: aload 10
    //   2141: getfield 265	com/cootek/usage/i:a	Ljava/lang/String;
    //   2144: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2147: ifne -39 -> 2108
    //   2150: aload 11
    //   2152: ldc_w 345
    //   2155: aload 10
    //   2157: getfield 265	com/cootek/usage/i:a	Ljava/lang/String;
    //   2160: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2163: pop
    //   2164: aload 10
    //   2166: getfield 266	com/cootek/usage/i:b	Ljava/lang/String;
    //   2169: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2172: ifne +17 -> 2189
    //   2175: aload 11
    //   2177: ldc_w 327
    //   2180: aload 10
    //   2182: getfield 266	com/cootek/usage/i:b	Ljava/lang/String;
    //   2185: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2188: pop
    //   2189: aload 8
    //   2191: aload 11
    //   2193: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2196: pop
    //   2197: goto -89 -> 2108
    //   2200: aload 8
    //   2202: invokevirtual 316	org/json/JSONArray:length	()I
    //   2205: ifle +14 -> 2219
    //   2208: aload 5
    //   2210: ldc_w 325
    //   2213: aload 8
    //   2215: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2218: pop
    //   2219: aload 7
    //   2221: getfield 149	com/cootek/usage/j:h	Ljava/util/HashSet;
    //   2224: invokevirtual 309	java/util/HashSet:isEmpty	()Z
    //   2227: ifne +133 -> 2360
    //   2230: new 278	org/json/JSONArray
    //   2233: dup
    //   2234: invokespecial 279	org/json/JSONArray:<init>	()V
    //   2237: astore 8
    //   2239: aload 7
    //   2241: getfield 149	com/cootek/usage/j:h	Ljava/util/HashSet;
    //   2244: invokevirtual 310	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2247: astore 9
    //   2249: aload 9
    //   2251: invokeinterface 294 1 0
    //   2256: ifeq +85 -> 2341
    //   2259: aload 9
    //   2261: invokeinterface 298 1 0
    //   2266: checkcast 268	com/cootek/usage/l
    //   2269: astore 10
    //   2271: new 300	org/json/JSONObject
    //   2274: dup
    //   2275: invokespecial 301	org/json/JSONObject:<init>	()V
    //   2278: astore 11
    //   2280: aload 10
    //   2282: getfield 270	com/cootek/usage/l:a	Ljava/lang/String;
    //   2285: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2288: ifne -39 -> 2249
    //   2291: aload 11
    //   2293: ldc_w 347
    //   2296: aload 10
    //   2298: getfield 270	com/cootek/usage/l:a	Ljava/lang/String;
    //   2301: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2304: pop
    //   2305: aload 10
    //   2307: getfield 271	com/cootek/usage/l:b	Ljava/lang/String;
    //   2310: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2313: ifne +17 -> 2330
    //   2316: aload 11
    //   2318: ldc_w 327
    //   2321: aload 10
    //   2323: getfield 271	com/cootek/usage/l:b	Ljava/lang/String;
    //   2326: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2329: pop
    //   2330: aload 8
    //   2332: aload 11
    //   2334: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2337: pop
    //   2338: goto -89 -> 2249
    //   2341: aload 8
    //   2343: invokevirtual 316	org/json/JSONArray:length	()I
    //   2346: ifle +14 -> 2360
    //   2349: aload 5
    //   2351: ldc_w 349
    //   2354: aload 8
    //   2356: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2359: pop
    //   2360: aload 7
    //   2362: getfield 151	com/cootek/usage/j:i	Ljava/util/HashSet;
    //   2365: invokevirtual 309	java/util/HashSet:isEmpty	()Z
    //   2368: ifne +143 -> 2511
    //   2371: new 278	org/json/JSONArray
    //   2374: dup
    //   2375: invokespecial 279	org/json/JSONArray:<init>	()V
    //   2378: astore 8
    //   2380: aload 7
    //   2382: getfield 151	com/cootek/usage/j:i	Ljava/util/HashSet;
    //   2385: invokevirtual 310	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2388: astore 7
    //   2390: aload 7
    //   2392: invokeinterface 294 1 0
    //   2397: ifeq +85 -> 2482
    //   2400: aload 7
    //   2402: invokeinterface 298 1 0
    //   2407: checkcast 273	com/cootek/usage/p
    //   2410: astore 9
    //   2412: new 300	org/json/JSONObject
    //   2415: dup
    //   2416: invokespecial 301	org/json/JSONObject:<init>	()V
    //   2419: astore 10
    //   2421: aload 9
    //   2423: getfield 275	com/cootek/usage/p:a	Ljava/lang/String;
    //   2426: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2429: ifne -39 -> 2390
    //   2432: aload 10
    //   2434: ldc_w 303
    //   2437: aload 9
    //   2439: getfield 275	com/cootek/usage/p:a	Ljava/lang/String;
    //   2442: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2445: pop
    //   2446: aload 9
    //   2448: getfield 276	com/cootek/usage/p:b	Ljava/lang/String;
    //   2451: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2454: ifne +17 -> 2471
    //   2457: aload 10
    //   2459: ldc_w 327
    //   2462: aload 9
    //   2464: getfield 276	com/cootek/usage/p:b	Ljava/lang/String;
    //   2467: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2470: pop
    //   2471: aload 8
    //   2473: aload 10
    //   2475: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2478: pop
    //   2479: goto -89 -> 2390
    //   2482: aload 8
    //   2484: invokevirtual 316	org/json/JSONArray:length	()I
    //   2487: ifle +24 -> 2511
    //   2490: aload 5
    //   2492: ldc_w 351
    //   2495: aload 8
    //   2497: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2500: pop
    //   2501: goto +10 -> 2511
    //   2504: astore 7
    //   2506: aload 7
    //   2508: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2511: aload_3
    //   2512: aload 5
    //   2514: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2517: pop
    //   2518: aload 6
    //   2520: iconst_1
    //   2521: putfield 354	com/cootek/usage/n:d	Z
    //   2524: goto -1082 -> 1442
    //   2527: aload_0
    //   2528: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   2531: invokevirtual 357	com/cootek/usage/AbsUsageAssist:getPhoneAccount	()Ljava/lang/String;
    //   2534: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2537: ifne +74 -> 2611
    //   2540: new 300	org/json/JSONObject
    //   2543: dup
    //   2544: invokespecial 301	org/json/JSONObject:<init>	()V
    //   2547: astore 4
    //   2549: aload 4
    //   2551: ldc_w 303
    //   2554: ldc_w 359
    //   2557: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2560: pop
    //   2561: new 278	org/json/JSONArray
    //   2564: dup
    //   2565: invokespecial 279	org/json/JSONArray:<init>	()V
    //   2568: astore 5
    //   2570: aload 5
    //   2572: aload_0
    //   2573: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   2576: invokevirtual 357	com/cootek/usage/AbsUsageAssist:getPhoneAccount	()Ljava/lang/String;
    //   2579: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2582: pop
    //   2583: aload 4
    //   2585: ldc_w 318
    //   2588: aload 5
    //   2590: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2593: pop
    //   2594: goto +10 -> 2604
    //   2597: astore 5
    //   2599: aload 5
    //   2601: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2604: aload_3
    //   2605: aload 4
    //   2607: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2610: pop
    //   2611: new 361	com/cootek/usage/UsageData
    //   2614: dup
    //   2615: invokespecial 362	com/cootek/usage/UsageData:<init>	()V
    //   2618: astore 4
    //   2620: aload 4
    //   2622: ldc 23
    //   2624: putfield 364	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   2627: aload 4
    //   2629: iconst_0
    //   2630: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   2633: putfield 369	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   2636: aload 4
    //   2638: aload_3
    //   2639: invokevirtual 370	org/json/JSONArray:toString	()Ljava/lang/String;
    //   2642: putfield 373	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   2645: aload 6
    //   2647: aload 4
    //   2649: putfield 376	com/cootek/usage/n:a	Lcom/cootek/usage/UsageData;
    //   2652: aload 6
    //   2654: iconst_0
    //   2655: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   2658: putfield 377	com/cootek/usage/n:c	Ljava/lang/String;
    //   2661: aload 6
    //   2663: areturn
    //   2664: astore_3
    //   2665: aconst_null
    //   2666: astore 5
    //   2668: goto +91 -> 2759
    //   2671: astore 5
    //   2673: aconst_null
    //   2674: astore 4
    //   2676: aload 4
    //   2678: astore_3
    //   2679: aload 5
    //   2681: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2684: aload 4
    //   2686: astore_3
    //   2687: aload 6
    //   2689: iconst_0
    //   2690: putfield 354	com/cootek/usage/n:d	Z
    //   2693: aload 4
    //   2695: ifnull +18 -> 2713
    //   2698: aload 4
    //   2700: invokeinterface 167 1 0
    //   2705: aload 6
    //   2707: areturn
    //   2708: astore_3
    //   2709: aload_3
    //   2710: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2713: aload 6
    //   2715: areturn
    //   2716: aconst_null
    //   2717: astore 4
    //   2719: aload 4
    //   2721: astore_3
    //   2722: aload 6
    //   2724: iconst_0
    //   2725: putfield 354	com/cootek/usage/n:d	Z
    //   2728: aload 4
    //   2730: ifnull +18 -> 2748
    //   2733: aload 4
    //   2735: invokeinterface 167 1 0
    //   2740: aload 6
    //   2742: areturn
    //   2743: astore_3
    //   2744: aload_3
    //   2745: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2748: aload 6
    //   2750: areturn
    //   2751: astore 4
    //   2753: aload_3
    //   2754: astore 5
    //   2756: aload 4
    //   2758: astore_3
    //   2759: aload 5
    //   2761: ifnull +20 -> 2781
    //   2764: aload 5
    //   2766: invokeinterface 167 1 0
    //   2771: goto +10 -> 2781
    //   2774: astore 4
    //   2776: aload 4
    //   2778: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2781: aload_3
    //   2782: athrow
    //   2783: astore 4
    //   2785: aconst_null
    //   2786: astore_3
    //   2787: goto +85 -> 2872
    //   2790: astore 5
    //   2792: aconst_null
    //   2793: astore 4
    //   2795: aload 4
    //   2797: astore_3
    //   2798: aload 5
    //   2800: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2803: aload 4
    //   2805: astore_3
    //   2806: aload 6
    //   2808: iconst_0
    //   2809: putfield 354	com/cootek/usage/n:d	Z
    //   2812: aload 4
    //   2814: ifnull +18 -> 2832
    //   2817: aload 4
    //   2819: invokeinterface 167 1 0
    //   2824: aload 6
    //   2826: areturn
    //   2827: astore_3
    //   2828: aload_3
    //   2829: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2832: aload 6
    //   2834: areturn
    //   2835: aconst_null
    //   2836: astore 4
    //   2838: aload 4
    //   2840: astore_3
    //   2841: aload 6
    //   2843: iconst_0
    //   2844: putfield 354	com/cootek/usage/n:d	Z
    //   2847: aload 4
    //   2849: ifnull +18 -> 2867
    //   2852: aload 4
    //   2854: invokeinterface 167 1 0
    //   2859: aload 6
    //   2861: areturn
    //   2862: astore_3
    //   2863: aload_3
    //   2864: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2867: aload 6
    //   2869: areturn
    //   2870: astore 4
    //   2872: aload_3
    //   2873: ifnull +17 -> 2890
    //   2876: aload_3
    //   2877: invokeinterface 167 1 0
    //   2882: goto +8 -> 2890
    //   2885: astore_3
    //   2886: aload_3
    //   2887: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   2890: aload 4
    //   2892: athrow
    //   2893: astore_3
    //   2894: goto -59 -> 2835
    //   2897: astore 4
    //   2899: goto -2667 -> 232
    //   2902: astore_3
    //   2903: goto -187 -> 2716
    //   2906: astore_3
    //   2907: goto -1512 -> 1395
    //   2910: goto -1545 -> 1365
    //   2913: ldc -5
    //   2915: astore_3
    //   2916: goto -2360 -> 556
    //   2919: ldc_w 379
    //   2922: astore_3
    //   2923: goto -2367 -> 556
    //   2926: ldc -3
    //   2928: astore_3
    //   2929: goto -2373 -> 556
    //   2932: ldc_w 381
    //   2935: astore_3
    //   2936: goto -2380 -> 556
    //   2939: ldc -1
    //   2941: astore_3
    //   2942: goto -2386 -> 556
    //   2945: aconst_null
    //   2946: astore_3
    //   2947: goto -2391 -> 556
    //   2950: ldc -5
    //   2952: astore_3
    //   2953: goto -2261 -> 692
    //   2956: ldc -3
    //   2958: astore_3
    //   2959: goto -2267 -> 692
    //   2962: ldc_w 381
    //   2965: astore_3
    //   2966: goto -2274 -> 692
    //   2969: aconst_null
    //   2970: astore_3
    //   2971: goto -2279 -> 692
    //   2974: goto -2170 -> 804
    //   2977: ldc_w 381
    //   2980: astore_3
    //   2981: goto -2157 -> 824
    //   2984: goto -2068 -> 916
    //   2987: ldc_w 383
    //   2990: astore_3
    //   2991: goto -2053 -> 938
    //   2994: ldc_w 385
    //   2997: astore_3
    //   2998: goto -2060 -> 938
    //   3001: ldc_w 387
    //   3004: astore_3
    //   3005: goto -2067 -> 938
    //   3008: ldc_w 389
    //   3011: astore_3
    //   3012: goto -2074 -> 938
    //   3015: ldc_w 391
    //   3018: astore_3
    //   3019: goto -2081 -> 938
    //   3022: ldc_w 393
    //   3025: astore_3
    //   3026: goto -2088 -> 938
    //   3029: ldc_w 395
    //   3032: astore_3
    //   3033: goto -2095 -> 938
    //   3036: goto -2065 -> 971
    //   3039: astore_3
    //   3040: goto -1679 -> 1361
    //   3043: ldc -5
    //   3045: astore_3
    //   3046: goto -1986 -> 1060
    //   3049: ldc -3
    //   3051: astore_3
    //   3052: goto -1992 -> 1060
    //   3055: ldc_w 381
    //   3058: astore_3
    //   3059: goto -1999 -> 1060
    //   3062: ldc -1
    //   3064: astore_3
    //   3065: goto -2005 -> 1060
    //   3068: aconst_null
    //   3069: astore_3
    //   3070: goto -2010 -> 1060
    //   3073: ldc -5
    //   3075: astore_3
    //   3076: goto -1908 -> 1168
    //   3079: ldc_w 397
    //   3082: astore_3
    //   3083: goto -1915 -> 1168
    //   3086: ldc -3
    //   3088: astore_3
    //   3089: goto -1921 -> 1168
    //   3092: ldc_w 399
    //   3095: astore_3
    //   3096: goto -1928 -> 1168
    //   3099: aconst_null
    //   3100: astore_3
    //   3101: goto -1933 -> 1168
    //   3104: ldc -5
    //   3106: astore_3
    //   3107: goto -1787 -> 1320
    //   3110: ldc_w 401
    //   3113: astore_3
    //   3114: goto -1794 -> 1320
    //   3117: ldc_w 403
    //   3120: astore_3
    //   3121: goto -1801 -> 1320
    //   3124: ldc_w 405
    //   3127: astore_3
    //   3128: goto -1808 -> 1320
    //   3131: ldc_w 407
    //   3134: astore_3
    //   3135: goto -1815 -> 1320
    //   3138: ldc_w 409
    //   3141: astore_3
    //   3142: goto -1822 -> 1320
    //   3145: ldc_w 411
    //   3148: astore_3
    //   3149: goto -1829 -> 1320
    //   3152: ldc_w 413
    //   3155: astore_3
    //   3156: goto -1836 -> 1320
    //   3159: ldc_w 415
    //   3162: astore_3
    //   3163: goto -1843 -> 1320
    //   3166: ldc_w 417
    //   3169: astore_3
    //   3170: goto -1850 -> 1320
    //   3173: ldc_w 419
    //   3176: astore_3
    //   3177: goto -1857 -> 1320
    //   3180: ldc_w 421
    //   3183: astore_3
    //   3184: goto -1864 -> 1320
    //   3187: ldc_w 423
    //   3190: astore_3
    //   3191: goto -1871 -> 1320
    //   3194: ldc_w 425
    //   3197: astore_3
    //   3198: goto -1878 -> 1320
    //   3201: ldc_w 427
    //   3204: astore_3
    //   3205: goto -1885 -> 1320
    //   3208: aconst_null
    //   3209: astore_3
    //   3210: goto -1890 -> 1320
    //   3213: iconst_0
    //   3214: istore_1
    //   3215: goto -1418 -> 1797
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3218	0	this	g
    //   1793	1422	1	j	int
    //   211	1163	2	bool	boolean
    //   56	187	3	localCursor	android.database.Cursor
    //   251	2	3	localRuntimeException1	RuntimeException
    //   435	29	3	localObject1	Object
    //   473	86	3	localRuntimeException2	RuntimeException
    //   588	137	3	localObject2	Object
    //   728	7	3	localRuntimeException3	RuntimeException
    //   806	547	3	localObject3	Object
    //   1356	1	3	localRuntimeException4	RuntimeException
    //   1360	2	3	localRuntimeException5	RuntimeException
    //   1380	1	3	localObject4	Object
    //   1384	6	3	localRuntimeException6	RuntimeException
    //   1417	2	3	localRuntimeException7	RuntimeException
    //   1429	1210	3	localJSONArray	JSONArray
    //   2664	1	3	localObject5	Object
    //   2678	9	3	localObject6	Object
    //   2708	2	3	localRuntimeException8	RuntimeException
    //   2721	1	3	localObject7	Object
    //   2743	11	3	localRuntimeException9	RuntimeException
    //   2758	48	3	localObject8	Object
    //   2827	2	3	localRuntimeException10	RuntimeException
    //   2840	1	3	localObject9	Object
    //   2862	15	3	localRuntimeException11	RuntimeException
    //   2885	2	3	localRuntimeException12	RuntimeException
    //   2893	1	3	localSecurityException1	SecurityException
    //   2902	1	3	localSecurityException2	SecurityException
    //   2906	1	3	localSecurityException3	SecurityException
    //   2915	118	3	str1	String
    //   3039	1	3	localRuntimeException13	RuntimeException
    //   3045	165	3	str2	String
    //   20	12	4	localContentResolver	android.content.ContentResolver
    //   219	1	4	localObject10	Object
    //   227	2507	4	localObject11	Object
    //   2751	6	4	localObject12	Object
    //   2774	3	4	localRuntimeException14	RuntimeException
    //   2783	1	4	localObject13	Object
    //   2793	60	4	localObject14	Object
    //   2870	21	4	localObject15	Object
    //   2897	1	4	localSecurityException4	SecurityException
    //   79	121	5	localJ	j
    //   224	1	5	localRuntimeException15	RuntimeException
    //   379	2210	5	localObject16	Object
    //   2597	3	5	localJSONException1	JSONException
    //   2666	1	5	localObject17	Object
    //   2671	9	5	localRuntimeException16	RuntimeException
    //   2754	11	5	localRuntimeException17	RuntimeException
    //   2790	9	5	localRuntimeException18	RuntimeException
    //   8	2860	6	localN	n
    //   29	2372	7	localObject18	Object
    //   2504	3	7	localJSONException2	JSONException
    //   417	2079	8	localObject19	Object
    //   1515	948	9	localObject20	Object
    //   1615	859	10	localObject21	Object
    //   1624	709	11	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   61	70	219	finally
    //   70	212	219	finally
    //   61	70	224	java/lang/RuntimeException
    //   70	212	224	java/lang/RuntimeException
    //   242	248	251	java/lang/RuntimeException
    //   447	463	473	java/lang/RuntimeException
    //   463	470	473	java/lang/RuntimeException
    //   488	556	473	java/lang/RuntimeException
    //   556	583	473	java/lang/RuntimeException
    //   583	589	473	java/lang/RuntimeException
    //   603	640	473	java/lang/RuntimeException
    //   650	692	473	java/lang/RuntimeException
    //   692	719	473	java/lang/RuntimeException
    //   719	725	473	java/lang/RuntimeException
    //   840	853	473	java/lang/RuntimeException
    //   640	650	728	java/lang/RuntimeException
    //   954	968	1356	java/lang/RuntimeException
    //   971	977	1356	java/lang/RuntimeException
    //   977	984	1356	java/lang/RuntimeException
    //   987	1060	1356	java/lang/RuntimeException
    //   1060	1087	1356	java/lang/RuntimeException
    //   1087	1093	1356	java/lang/RuntimeException
    //   1096	1168	1356	java/lang/RuntimeException
    //   1168	1195	1356	java/lang/RuntimeException
    //   1195	1201	1356	java/lang/RuntimeException
    //   1204	1320	1356	java/lang/RuntimeException
    //   1320	1347	1356	java/lang/RuntimeException
    //   1347	1353	1356	java/lang/RuntimeException
    //   398	419	1360	java/lang/RuntimeException
    //   427	443	1360	java/lang/RuntimeException
    //   477	484	1360	java/lang/RuntimeException
    //   592	599	1360	java/lang/RuntimeException
    //   732	804	1360	java/lang/RuntimeException
    //   824	836	1360	java/lang/RuntimeException
    //   386	394	1380	finally
    //   398	419	1380	finally
    //   427	443	1380	finally
    //   447	463	1380	finally
    //   463	470	1380	finally
    //   477	484	1380	finally
    //   488	556	1380	finally
    //   556	583	1380	finally
    //   583	589	1380	finally
    //   592	599	1380	finally
    //   603	640	1380	finally
    //   640	650	1380	finally
    //   650	692	1380	finally
    //   692	719	1380	finally
    //   719	725	1380	finally
    //   732	804	1380	finally
    //   824	836	1380	finally
    //   840	853	1380	finally
    //   853	916	1380	finally
    //   938	950	1380	finally
    //   954	968	1380	finally
    //   971	977	1380	finally
    //   977	984	1380	finally
    //   987	1060	1380	finally
    //   1060	1087	1380	finally
    //   1087	1093	1380	finally
    //   1096	1168	1380	finally
    //   1168	1195	1380	finally
    //   1195	1201	1380	finally
    //   1204	1320	1380	finally
    //   1320	1347	1380	finally
    //   1347	1353	1380	finally
    //   1361	1365	1380	finally
    //   1365	1373	1380	finally
    //   386	394	1384	java/lang/RuntimeException
    //   1361	1365	1384	java/lang/RuntimeException
    //   1365	1373	1384	java/lang/RuntimeException
    //   1407	1414	1417	java/lang/RuntimeException
    //   1473	1517	2504	org/json/JSONException
    //   1517	1543	2504	org/json/JSONException
    //   1546	1565	2504	org/json/JSONException
    //   1565	1595	2504	org/json/JSONException
    //   1595	1676	2504	org/json/JSONException
    //   1676	1684	2504	org/json/JSONException
    //   1687	1706	2504	org/json/JSONException
    //   1706	1736	2504	org/json/JSONException
    //   1736	1792	2504	org/json/JSONException
    //   1797	1822	2504	org/json/JSONException
    //   1824	1849	2504	org/json/JSONException
    //   1851	1876	2504	org/json/JSONException
    //   1882	1890	2504	org/json/JSONException
    //   1893	1912	2504	org/json/JSONException
    //   1912	1942	2504	org/json/JSONException
    //   1942	2023	2504	org/json/JSONException
    //   2023	2048	2504	org/json/JSONException
    //   2048	2064	2504	org/json/JSONException
    //   2067	2078	2504	org/json/JSONException
    //   2078	2108	2504	org/json/JSONException
    //   2108	2189	2504	org/json/JSONException
    //   2189	2197	2504	org/json/JSONException
    //   2200	2219	2504	org/json/JSONException
    //   2219	2249	2504	org/json/JSONException
    //   2249	2330	2504	org/json/JSONException
    //   2330	2338	2504	org/json/JSONException
    //   2341	2360	2504	org/json/JSONException
    //   2360	2390	2504	org/json/JSONException
    //   2390	2471	2504	org/json/JSONException
    //   2471	2479	2504	org/json/JSONException
    //   2482	2501	2504	org/json/JSONException
    //   2549	2594	2597	org/json/JSONException
    //   256	381	2664	finally
    //   256	381	2671	java/lang/RuntimeException
    //   2698	2705	2708	java/lang/RuntimeException
    //   2733	2740	2743	java/lang/RuntimeException
    //   2679	2684	2751	finally
    //   2687	2693	2751	finally
    //   2722	2728	2751	finally
    //   2764	2771	2774	java/lang/RuntimeException
    //   31	57	2783	finally
    //   31	57	2790	java/lang/RuntimeException
    //   2817	2824	2827	java/lang/RuntimeException
    //   2852	2859	2862	java/lang/RuntimeException
    //   2798	2803	2870	finally
    //   2806	2812	2870	finally
    //   2841	2847	2870	finally
    //   2876	2882	2885	java/lang/RuntimeException
    //   31	57	2893	java/lang/SecurityException
    //   61	70	2897	java/lang/SecurityException
    //   70	212	2897	java/lang/SecurityException
    //   256	381	2902	java/lang/SecurityException
    //   386	394	2906	java/lang/SecurityException
    //   398	419	2906	java/lang/SecurityException
    //   427	443	2906	java/lang/SecurityException
    //   447	463	2906	java/lang/SecurityException
    //   463	470	2906	java/lang/SecurityException
    //   477	484	2906	java/lang/SecurityException
    //   488	556	2906	java/lang/SecurityException
    //   556	583	2906	java/lang/SecurityException
    //   583	589	2906	java/lang/SecurityException
    //   592	599	2906	java/lang/SecurityException
    //   603	640	2906	java/lang/SecurityException
    //   640	650	2906	java/lang/SecurityException
    //   650	692	2906	java/lang/SecurityException
    //   692	719	2906	java/lang/SecurityException
    //   719	725	2906	java/lang/SecurityException
    //   732	804	2906	java/lang/SecurityException
    //   824	836	2906	java/lang/SecurityException
    //   840	853	2906	java/lang/SecurityException
    //   853	916	2906	java/lang/SecurityException
    //   938	950	2906	java/lang/SecurityException
    //   954	968	2906	java/lang/SecurityException
    //   971	977	2906	java/lang/SecurityException
    //   977	984	2906	java/lang/SecurityException
    //   987	1060	2906	java/lang/SecurityException
    //   1060	1087	2906	java/lang/SecurityException
    //   1087	1093	2906	java/lang/SecurityException
    //   1096	1168	2906	java/lang/SecurityException
    //   1168	1195	2906	java/lang/SecurityException
    //   1195	1201	2906	java/lang/SecurityException
    //   1204	1320	2906	java/lang/SecurityException
    //   1320	1347	2906	java/lang/SecurityException
    //   1347	1353	2906	java/lang/SecurityException
    //   1361	1365	2906	java/lang/SecurityException
    //   1365	1373	2906	java/lang/SecurityException
    //   853	916	3039	java/lang/RuntimeException
    //   938	950	3039	java/lang/RuntimeException
  }
  
  private static String c(int paramInt)
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
  private n d()
  {
    // Byte code:
    //   0: new 74	com/cootek/usage/n
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 77	com/cootek/usage/n:<init>	(Lcom/cootek/usage/g;)V
    //   8: astore 11
    //   10: aload_0
    //   11: getfield 32	com/cootek/usage/g:i	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 83	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 89	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 13
    //   22: new 278	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 279	org/json/JSONArray:<init>	()V
    //   29: astore 12
    //   31: aconst_null
    //   32: astore 10
    //   34: aconst_null
    //   35: astore 9
    //   37: aconst_null
    //   38: astore 8
    //   40: aload 8
    //   42: astore 7
    //   44: invokestatic 432	com/cootek/usage/u:a	()Lcom/cootek/usage/u;
    //   47: iconst_1
    //   48: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   51: invokevirtual 435	com/cootek/usage/u:c	(Ljava/lang/String;)J
    //   54: lstore_2
    //   55: aload 8
    //   57: astore 7
    //   59: aload 13
    //   61: getstatic 438	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   64: bipush 6
    //   66: anewarray 100	java/lang/String
    //   69: dup
    //   70: iconst_0
    //   71: ldc 102
    //   73: aastore
    //   74: dup
    //   75: iconst_1
    //   76: ldc_w 440
    //   79: aastore
    //   80: dup
    //   81: iconst_2
    //   82: ldc_w 347
    //   85: aastore
    //   86: dup
    //   87: iconst_3
    //   88: ldc_w 442
    //   91: aastore
    //   92: dup
    //   93: iconst_4
    //   94: ldc_w 327
    //   97: aastore
    //   98: dup
    //   99: iconst_5
    //   100: ldc_w 303
    //   103: aastore
    //   104: ldc_w 444
    //   107: iconst_1
    //   108: anewarray 100	java/lang/String
    //   111: dup
    //   112: iconst_0
    //   113: lload_2
    //   114: invokestatic 447	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   117: aastore
    //   118: ldc_w 449
    //   121: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   124: astore 8
    //   126: aload 8
    //   128: ifnull +233 -> 361
    //   131: aload 8
    //   133: invokeinterface 116 1 0
    //   138: ifeq +223 -> 361
    //   141: aload 11
    //   143: aload 8
    //   145: iconst_0
    //   146: invokeinterface 125 2 0
    //   151: putfield 451	com/cootek/usage/n:b	J
    //   154: aload 8
    //   156: iconst_1
    //   157: invokeinterface 131 2 0
    //   162: astore 7
    //   164: aload 8
    //   166: iconst_2
    //   167: invokeinterface 125 2 0
    //   172: lstore 4
    //   174: aload 8
    //   176: iconst_3
    //   177: invokeinterface 125 2 0
    //   182: lstore_2
    //   183: aload 8
    //   185: iconst_4
    //   186: invokeinterface 235 2 0
    //   191: istore_1
    //   192: aload 8
    //   194: iconst_5
    //   195: invokeinterface 131 2 0
    //   200: astore 9
    //   202: new 300	org/json/JSONObject
    //   205: dup
    //   206: invokespecial 301	org/json/JSONObject:<init>	()V
    //   209: astore 10
    //   211: aload 10
    //   213: ldc_w 453
    //   216: aload 7
    //   218: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   221: pop
    //   222: aload 10
    //   224: ldc_w 347
    //   227: lload 4
    //   229: ldc2_w 454
    //   232: ldiv
    //   233: invokevirtual 458	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   236: pop
    //   237: iload_1
    //   238: iconst_2
    //   239: if_icmpne +11 -> 250
    //   242: ldc_w 460
    //   245: astore 7
    //   247: goto +8 -> 255
    //   250: ldc_w 462
    //   253: astore 7
    //   255: aload 10
    //   257: ldc_w 327
    //   260: aload 7
    //   262: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   265: pop
    //   266: iconst_3
    //   267: iload_1
    //   268: if_icmpne +5 -> 273
    //   271: lconst_0
    //   272: lstore_2
    //   273: aload 10
    //   275: ldc_w 442
    //   278: lload_2
    //   279: invokevirtual 458	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   282: pop
    //   283: aload 10
    //   285: ldc_w 464
    //   288: aload 9
    //   290: invokestatic 323	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   293: iconst_1
    //   294: ixor
    //   295: invokevirtual 467	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   298: pop
    //   299: aload 12
    //   301: aload 10
    //   303: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   306: pop
    //   307: aload 11
    //   309: iconst_1
    //   310: putfield 354	com/cootek/usage/n:d	Z
    //   313: goto +15 -> 328
    //   316: astore 7
    //   318: goto +5 -> 323
    //   321: astore 7
    //   323: aload 7
    //   325: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   328: aload 8
    //   330: invokeinterface 164 1 0
    //   335: istore 6
    //   337: iload 6
    //   339: ifne +6 -> 345
    //   342: goto +19 -> 361
    //   345: goto -191 -> 154
    //   348: astore 7
    //   350: goto +219 -> 569
    //   353: astore 9
    //   355: goto +135 -> 490
    //   358: goto +176 -> 534
    //   361: aload 8
    //   363: ifnull +20 -> 383
    //   366: aload 8
    //   368: invokeinterface 167 1 0
    //   373: goto +10 -> 383
    //   376: astore 7
    //   378: aload 7
    //   380: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   383: new 300	org/json/JSONObject
    //   386: dup
    //   387: invokespecial 301	org/json/JSONObject:<init>	()V
    //   390: astore 7
    //   392: aload 7
    //   394: ldc_w 469
    //   397: aload 12
    //   399: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   402: pop
    //   403: goto +10 -> 413
    //   406: astore 8
    //   408: aload 8
    //   410: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   413: new 361	com/cootek/usage/UsageData
    //   416: dup
    //   417: invokespecial 362	com/cootek/usage/UsageData:<init>	()V
    //   420: astore 8
    //   422: aload 8
    //   424: ldc 23
    //   426: putfield 364	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   429: aload 8
    //   431: iconst_1
    //   432: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   435: putfield 369	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   438: aload 8
    //   440: aload 7
    //   442: invokevirtual 470	org/json/JSONObject:toString	()Ljava/lang/String;
    //   445: putfield 373	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   448: aload 11
    //   450: aload 8
    //   452: putfield 376	com/cootek/usage/n:a	Lcom/cootek/usage/UsageData;
    //   455: aload 11
    //   457: iconst_1
    //   458: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   461: putfield 377	com/cootek/usage/n:c	Ljava/lang/String;
    //   464: aload 11
    //   466: areturn
    //   467: astore 8
    //   469: aload 7
    //   471: astore 9
    //   473: aload 8
    //   475: astore 7
    //   477: aload 9
    //   479: astore 8
    //   481: goto +88 -> 569
    //   484: astore 9
    //   486: aload 10
    //   488: astore 8
    //   490: aload 8
    //   492: astore 7
    //   494: aload 9
    //   496: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   499: aload 8
    //   501: astore 7
    //   503: aload 11
    //   505: iconst_0
    //   506: putfield 354	com/cootek/usage/n:d	Z
    //   509: aload 8
    //   511: ifnull +20 -> 531
    //   514: aload 8
    //   516: invokeinterface 167 1 0
    //   521: aload 11
    //   523: areturn
    //   524: astore 7
    //   526: aload 7
    //   528: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   531: aload 11
    //   533: areturn
    //   534: aload 8
    //   536: astore 7
    //   538: aload 11
    //   540: iconst_0
    //   541: putfield 354	com/cootek/usage/n:d	Z
    //   544: aload 8
    //   546: ifnull +20 -> 566
    //   549: aload 8
    //   551: invokeinterface 167 1 0
    //   556: aload 11
    //   558: areturn
    //   559: astore 7
    //   561: aload 7
    //   563: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   566: aload 11
    //   568: areturn
    //   569: aload 8
    //   571: ifnull +20 -> 591
    //   574: aload 8
    //   576: invokeinterface 167 1 0
    //   581: goto +10 -> 591
    //   584: astore 8
    //   586: aload 8
    //   588: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   591: aload 7
    //   593: athrow
    //   594: astore 7
    //   596: aload 9
    //   598: astore 8
    //   600: goto -66 -> 534
    //   603: astore 7
    //   605: goto -247 -> 358
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	608	0	this	g
    //   191	78	1	j	int
    //   54	225	2	l1	long
    //   172	56	4	l2	long
    //   335	3	6	bool	boolean
    //   42	219	7	localObject1	Object
    //   316	1	7	localJSONException1	JSONException
    //   321	3	7	localJSONException2	JSONException
    //   348	1	7	localObject2	Object
    //   376	3	7	localRuntimeException1	RuntimeException
    //   390	112	7	localObject3	Object
    //   524	3	7	localRuntimeException2	RuntimeException
    //   536	1	7	localObject4	Object
    //   559	33	7	localRuntimeException3	RuntimeException
    //   594	1	7	localSecurityException1	SecurityException
    //   603	1	7	localSecurityException2	SecurityException
    //   38	329	8	localCursor	android.database.Cursor
    //   406	3	8	localJSONException3	JSONException
    //   420	31	8	localUsageData	UsageData
    //   467	7	8	localObject5	Object
    //   479	96	8	localObject6	Object
    //   584	3	8	localRuntimeException4	RuntimeException
    //   598	1	8	localRuntimeException5	RuntimeException
    //   35	254	9	str	String
    //   353	1	9	localRuntimeException6	RuntimeException
    //   471	7	9	localObject7	Object
    //   484	113	9	localRuntimeException7	RuntimeException
    //   32	455	10	localJSONObject	JSONObject
    //   8	559	11	localN	n
    //   29	369	12	localJSONArray	JSONArray
    //   20	40	13	localContentResolver	android.content.ContentResolver
    // Exception table:
    //   from	to	target	type
    //   273	313	316	org/json/JSONException
    //   211	237	321	org/json/JSONException
    //   255	266	321	org/json/JSONException
    //   131	154	348	finally
    //   154	211	348	finally
    //   211	237	348	finally
    //   255	266	348	finally
    //   273	313	348	finally
    //   323	328	348	finally
    //   328	337	348	finally
    //   131	154	353	java/lang/RuntimeException
    //   154	211	353	java/lang/RuntimeException
    //   211	237	353	java/lang/RuntimeException
    //   255	266	353	java/lang/RuntimeException
    //   273	313	353	java/lang/RuntimeException
    //   323	328	353	java/lang/RuntimeException
    //   328	337	353	java/lang/RuntimeException
    //   366	373	376	java/lang/RuntimeException
    //   392	403	406	org/json/JSONException
    //   44	55	467	finally
    //   59	126	467	finally
    //   494	499	467	finally
    //   503	509	467	finally
    //   538	544	467	finally
    //   44	55	484	java/lang/RuntimeException
    //   59	126	484	java/lang/RuntimeException
    //   514	521	524	java/lang/RuntimeException
    //   549	556	559	java/lang/RuntimeException
    //   574	581	584	java/lang/RuntimeException
    //   44	55	594	java/lang/SecurityException
    //   59	126	594	java/lang/SecurityException
    //   131	154	603	java/lang/SecurityException
    //   154	211	603	java/lang/SecurityException
    //   211	237	603	java/lang/SecurityException
    //   255	266	603	java/lang/SecurityException
    //   273	313	603	java/lang/SecurityException
    //   323	328	603	java/lang/SecurityException
    //   328	337	603	java/lang/SecurityException
  }
  
  private static String d(int paramInt)
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
  private n e()
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
    //   20: astore 12
    //   22: new 278	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 279	org/json/JSONArray:<init>	()V
    //   29: astore 11
    //   31: aconst_null
    //   32: astore 9
    //   34: aconst_null
    //   35: astore 8
    //   37: aconst_null
    //   38: astore 7
    //   40: aload 7
    //   42: astore 6
    //   44: invokestatic 432	com/cootek/usage/u:a	()Lcom/cootek/usage/u;
    //   47: iconst_2
    //   48: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   51: invokevirtual 435	com/cootek/usage/u:c	(Ljava/lang/String;)J
    //   54: lstore_1
    //   55: aload 7
    //   57: astore 6
    //   59: aload 12
    //   61: ldc_w 472
    //   64: invokestatic 478	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   67: bipush 6
    //   69: anewarray 100	java/lang/String
    //   72: dup
    //   73: iconst_0
    //   74: ldc 102
    //   76: aastore
    //   77: dup
    //   78: iconst_1
    //   79: ldc_w 325
    //   82: aastore
    //   83: dup
    //   84: iconst_2
    //   85: ldc_w 480
    //   88: aastore
    //   89: dup
    //   90: iconst_3
    //   91: ldc_w 347
    //   94: aastore
    //   95: dup
    //   96: iconst_4
    //   97: ldc_w 482
    //   100: aastore
    //   101: dup
    //   102: iconst_5
    //   103: ldc_w 484
    //   106: aastore
    //   107: ldc_w 444
    //   110: iconst_1
    //   111: anewarray 100	java/lang/String
    //   114: dup
    //   115: iconst_0
    //   116: lload_1
    //   117: invokestatic 447	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   120: aastore
    //   121: ldc_w 486
    //   124: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   127: astore 7
    //   129: aload 7
    //   131: ifnull +236 -> 367
    //   134: aload 7
    //   136: invokeinterface 116 1 0
    //   141: ifeq +226 -> 367
    //   144: aload 10
    //   146: aload 7
    //   148: iconst_0
    //   149: invokeinterface 125 2 0
    //   154: putfield 451	com/cootek/usage/n:b	J
    //   157: aload 7
    //   159: iconst_1
    //   160: invokeinterface 131 2 0
    //   165: astore 6
    //   167: aload 7
    //   169: iconst_2
    //   170: invokeinterface 125 2 0
    //   175: lstore_1
    //   176: lload_1
    //   177: lconst_0
    //   178: lcmp
    //   179: ifgt +441 -> 620
    //   182: aload 7
    //   184: iconst_3
    //   185: invokeinterface 125 2 0
    //   190: lstore_3
    //   191: aload 7
    //   193: iconst_4
    //   194: invokeinterface 131 2 0
    //   199: astore 8
    //   201: aload 7
    //   203: iconst_5
    //   204: invokeinterface 131 2 0
    //   209: astore 9
    //   211: new 300	org/json/JSONObject
    //   214: dup
    //   215: invokespecial 301	org/json/JSONObject:<init>	()V
    //   218: astore 12
    //   220: aload 12
    //   222: ldc_w 453
    //   225: aload 6
    //   227: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   230: pop
    //   231: aload 12
    //   233: ldc_w 347
    //   236: lload_3
    //   237: ldc2_w 454
    //   240: ldiv
    //   241: invokevirtual 458	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   244: pop
    //   245: aload 12
    //   247: ldc_w 327
    //   250: ldc_w 462
    //   253: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   256: pop
    //   257: lload_1
    //   258: lconst_0
    //   259: lcmp
    //   260: ifle +354 -> 614
    //   263: iconst_1
    //   264: istore 5
    //   266: goto +3 -> 269
    //   269: aload 12
    //   271: ldc_w 464
    //   274: iload 5
    //   276: invokevirtual 467	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   279: pop
    //   280: aload 12
    //   282: ldc_w 488
    //   285: aload 8
    //   287: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   290: pop
    //   291: aload 12
    //   293: ldc_w 484
    //   296: aload 9
    //   298: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   301: pop
    //   302: aload 11
    //   304: aload 12
    //   306: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   309: pop
    //   310: aload 10
    //   312: iconst_1
    //   313: putfield 354	com/cootek/usage/n:d	Z
    //   316: goto +18 -> 334
    //   319: astore 6
    //   321: goto +5 -> 326
    //   324: astore 6
    //   326: aload 6
    //   328: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   331: goto +3 -> 334
    //   334: aload 7
    //   336: invokeinterface 164 1 0
    //   341: istore 5
    //   343: iload 5
    //   345: ifne +6 -> 351
    //   348: goto +19 -> 367
    //   351: goto -194 -> 157
    //   354: astore 6
    //   356: goto +219 -> 575
    //   359: astore 8
    //   361: goto +135 -> 496
    //   364: goto +176 -> 540
    //   367: aload 7
    //   369: ifnull +20 -> 389
    //   372: aload 7
    //   374: invokeinterface 167 1 0
    //   379: goto +10 -> 389
    //   382: astore 6
    //   384: aload 6
    //   386: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   389: new 300	org/json/JSONObject
    //   392: dup
    //   393: invokespecial 301	org/json/JSONObject:<init>	()V
    //   396: astore 6
    //   398: aload 6
    //   400: ldc_w 469
    //   403: aload 11
    //   405: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   408: pop
    //   409: goto +10 -> 419
    //   412: astore 7
    //   414: aload 7
    //   416: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   419: new 361	com/cootek/usage/UsageData
    //   422: dup
    //   423: invokespecial 362	com/cootek/usage/UsageData:<init>	()V
    //   426: astore 7
    //   428: aload 7
    //   430: ldc 23
    //   432: putfield 364	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   435: aload 7
    //   437: iconst_2
    //   438: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   441: putfield 369	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   444: aload 7
    //   446: aload 6
    //   448: invokevirtual 470	org/json/JSONObject:toString	()Ljava/lang/String;
    //   451: putfield 373	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   454: aload 10
    //   456: aload 7
    //   458: putfield 376	com/cootek/usage/n:a	Lcom/cootek/usage/UsageData;
    //   461: aload 10
    //   463: iconst_2
    //   464: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   467: putfield 377	com/cootek/usage/n:c	Ljava/lang/String;
    //   470: aload 10
    //   472: areturn
    //   473: astore 7
    //   475: aload 6
    //   477: astore 8
    //   479: aload 7
    //   481: astore 6
    //   483: aload 8
    //   485: astore 7
    //   487: goto +88 -> 575
    //   490: astore 8
    //   492: aload 9
    //   494: astore 7
    //   496: aload 7
    //   498: astore 6
    //   500: aload 8
    //   502: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   505: aload 7
    //   507: astore 6
    //   509: aload 10
    //   511: iconst_0
    //   512: putfield 354	com/cootek/usage/n:d	Z
    //   515: aload 7
    //   517: ifnull +20 -> 537
    //   520: aload 7
    //   522: invokeinterface 167 1 0
    //   527: aload 10
    //   529: areturn
    //   530: astore 6
    //   532: aload 6
    //   534: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   537: aload 10
    //   539: areturn
    //   540: aload 7
    //   542: astore 6
    //   544: aload 10
    //   546: iconst_0
    //   547: putfield 354	com/cootek/usage/n:d	Z
    //   550: aload 7
    //   552: ifnull +20 -> 572
    //   555: aload 7
    //   557: invokeinterface 167 1 0
    //   562: aload 10
    //   564: areturn
    //   565: astore 6
    //   567: aload 6
    //   569: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   572: aload 10
    //   574: areturn
    //   575: aload 7
    //   577: ifnull +20 -> 597
    //   580: aload 7
    //   582: invokeinterface 167 1 0
    //   587: goto +10 -> 597
    //   590: astore 7
    //   592: aload 7
    //   594: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   597: aload 6
    //   599: athrow
    //   600: astore 6
    //   602: aload 8
    //   604: astore 7
    //   606: goto -66 -> 540
    //   609: astore 6
    //   611: goto -247 -> 364
    //   614: iconst_0
    //   615: istore 5
    //   617: goto -348 -> 269
    //   620: goto -286 -> 334
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	623	0	this	g
    //   54	204	1	l1	long
    //   190	47	3	l2	long
    //   264	352	5	bool	boolean
    //   42	184	6	localObject1	Object
    //   319	1	6	localJSONException1	JSONException
    //   324	3	6	localJSONException2	JSONException
    //   354	1	6	localObject2	Object
    //   382	3	6	localRuntimeException1	RuntimeException
    //   396	112	6	localObject3	Object
    //   530	3	6	localRuntimeException2	RuntimeException
    //   542	1	6	localObject4	Object
    //   565	33	6	localRuntimeException3	RuntimeException
    //   600	1	6	localSecurityException1	SecurityException
    //   609	1	6	localSecurityException2	SecurityException
    //   38	335	7	localCursor	android.database.Cursor
    //   412	3	7	localJSONException3	JSONException
    //   426	31	7	localUsageData	UsageData
    //   473	7	7	localObject5	Object
    //   485	96	7	localObject6	Object
    //   590	3	7	localRuntimeException4	RuntimeException
    //   604	1	7	localRuntimeException5	RuntimeException
    //   35	251	8	str1	String
    //   359	1	8	localRuntimeException6	RuntimeException
    //   477	7	8	localObject7	Object
    //   490	113	8	localRuntimeException7	RuntimeException
    //   32	461	9	str2	String
    //   8	565	10	localN	n
    //   29	375	11	localJSONArray	JSONArray
    //   20	285	12	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   310	316	319	org/json/JSONException
    //   220	257	324	org/json/JSONException
    //   269	310	324	org/json/JSONException
    //   134	157	354	finally
    //   157	176	354	finally
    //   182	220	354	finally
    //   220	257	354	finally
    //   269	310	354	finally
    //   310	316	354	finally
    //   326	331	354	finally
    //   334	343	354	finally
    //   134	157	359	java/lang/RuntimeException
    //   157	176	359	java/lang/RuntimeException
    //   182	220	359	java/lang/RuntimeException
    //   220	257	359	java/lang/RuntimeException
    //   269	310	359	java/lang/RuntimeException
    //   310	316	359	java/lang/RuntimeException
    //   326	331	359	java/lang/RuntimeException
    //   334	343	359	java/lang/RuntimeException
    //   372	379	382	java/lang/RuntimeException
    //   398	409	412	org/json/JSONException
    //   44	55	473	finally
    //   59	129	473	finally
    //   500	505	473	finally
    //   509	515	473	finally
    //   544	550	473	finally
    //   44	55	490	java/lang/RuntimeException
    //   59	129	490	java/lang/RuntimeException
    //   520	527	530	java/lang/RuntimeException
    //   555	562	565	java/lang/RuntimeException
    //   580	587	590	java/lang/RuntimeException
    //   44	55	600	java/lang/SecurityException
    //   59	129	600	java/lang/SecurityException
    //   134	157	609	java/lang/SecurityException
    //   157	176	609	java/lang/SecurityException
    //   182	220	609	java/lang/SecurityException
    //   220	257	609	java/lang/SecurityException
    //   269	310	609	java/lang/SecurityException
    //   310	316	609	java/lang/SecurityException
    //   326	331	609	java/lang/SecurityException
    //   334	343	609	java/lang/SecurityException
  }
  
  private static String e(int paramInt)
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
  
  private n f()
  {
    n localN = new n(this);
    Object localObject1 = this.i.getContext().getPackageManager();
    int j = 0;
    List localList = ((PackageManager)localObject1).getInstalledPackages(0);
    JSONArray localJSONArray = new JSONArray();
    while (j < localList.size())
    {
      Object localObject2 = (PackageInfo)localList.get(j);
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
          localN.d = true;
        }
        catch (JSONException localJSONException2)
        {
          a.a(localJSONException2);
        }
      }
      j += 1;
    }
    localObject1 = new JSONObject();
    try
    {
      ((JSONObject)localObject1).put("data", localJSONArray);
    }
    catch (JSONException localJSONException1)
    {
      a.a(localJSONException1);
    }
    UsageData localUsageData = new UsageData();
    localUsageData.type = "noah_info";
    localUsageData.path = a(3);
    localUsageData.value = ((JSONObject)localObject1).toString();
    localN.a = localUsageData;
    localN.c = a(3);
    return localN;
  }
  
  private static String f(int paramInt)
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
    //   22: new 278	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 279	org/json/JSONArray:<init>	()V
    //   29: astore 12
    //   31: ldc_w 531
    //   34: invokestatic 478	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   37: astore 11
    //   39: invokestatic 432	com/cootek/usage/u:a	()Lcom/cootek/usage/u;
    //   42: iconst_1
    //   43: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   46: invokevirtual 435	com/cootek/usage/u:c	(Ljava/lang/String;)J
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
    //   66: ldc_w 440
    //   69: aastore
    //   70: dup
    //   71: iconst_2
    //   72: ldc_w 347
    //   75: aastore
    //   76: dup
    //   77: iconst_3
    //   78: ldc_w 442
    //   81: aastore
    //   82: dup
    //   83: iconst_4
    //   84: ldc_w 327
    //   87: aastore
    //   88: dup
    //   89: iconst_5
    //   90: ldc -79
    //   92: aastore
    //   93: dup
    //   94: bipush 6
    //   96: ldc_w 533
    //   99: aastore
    //   100: ldc_w 444
    //   103: iconst_1
    //   104: anewarray 100	java/lang/String
    //   107: dup
    //   108: iconst_0
    //   109: lload_3
    //   110: invokestatic 447	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   113: aastore
    //   114: ldc_w 449
    //   117: invokevirtual 110	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   120: astore 10
    //   122: aload 10
    //   124: ifnull +318 -> 442
    //   127: aload 10
    //   129: invokeinterface 116 1 0
    //   134: ifeq +308 -> 442
    //   137: aload 13
    //   139: aload 10
    //   141: iconst_0
    //   142: invokeinterface 125 2 0
    //   147: putfield 451	com/cootek/usage/n:b	J
    //   150: aload 10
    //   152: iconst_1
    //   153: invokeinterface 131 2 0
    //   158: astore 11
    //   160: aload 10
    //   162: iconst_2
    //   163: invokeinterface 125 2 0
    //   168: lstore 7
    //   170: aload 10
    //   172: iconst_3
    //   173: invokeinterface 125 2 0
    //   178: lstore_3
    //   179: aload 10
    //   181: iconst_4
    //   182: invokeinterface 235 2 0
    //   187: istore_1
    //   188: aload 10
    //   190: iconst_5
    //   191: invokeinterface 125 2 0
    //   196: lstore 5
    //   198: aload 10
    //   200: bipush 6
    //   202: invokeinterface 235 2 0
    //   207: istore_2
    //   208: new 300	org/json/JSONObject
    //   211: dup
    //   212: invokespecial 301	org/json/JSONObject:<init>	()V
    //   215: astore 14
    //   217: aload 14
    //   219: ldc_w 453
    //   222: aload 11
    //   224: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   227: pop
    //   228: aload 14
    //   230: ldc_w 347
    //   233: lload 7
    //   235: ldc2_w 454
    //   238: ldiv
    //   239: invokevirtual 458	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   242: pop
    //   243: iconst_3
    //   244: iload_1
    //   245: if_icmpne +435 -> 680
    //   248: lconst_0
    //   249: lstore_3
    //   250: goto +3 -> 253
    //   253: aload 14
    //   255: ldc_w 442
    //   258: lload_3
    //   259: invokevirtual 458	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   262: pop
    //   263: lload 5
    //   265: lconst_0
    //   266: lcmp
    //   267: ifeq +416 -> 683
    //   270: iconst_1
    //   271: istore 9
    //   273: goto +3 -> 276
    //   276: aload 14
    //   278: ldc_w 464
    //   281: iload 9
    //   283: invokevirtual 467	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   286: pop
    //   287: iload_1
    //   288: iconst_2
    //   289: if_icmpne +16 -> 305
    //   292: ldc_w 460
    //   295: astore 11
    //   297: goto +13 -> 310
    //   300: astore 11
    //   302: goto +94 -> 396
    //   305: ldc_w 462
    //   308: astore 11
    //   310: aload 14
    //   312: ldc_w 327
    //   315: aload 11
    //   317: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   320: pop
    //   321: iload_2
    //   322: ifne +367 -> 689
    //   325: ldc_w 535
    //   328: astore 11
    //   330: aload 14
    //   332: ldc_w 537
    //   335: aload 11
    //   337: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   340: pop
    //   341: goto +36 -> 377
    //   344: iload_2
    //   345: iconst_2
    //   346: if_icmpne +11 -> 357
    //   349: ldc_w 539
    //   352: astore 11
    //   354: goto -24 -> 330
    //   357: iload_2
    //   358: iconst_3
    //   359: if_icmpne +18 -> 377
    //   362: aload 14
    //   364: ldc_w 537
    //   367: ldc_w 541
    //   370: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   373: pop
    //   374: goto +3 -> 377
    //   377: aload 12
    //   379: aload 14
    //   381: invokevirtual 313	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   384: pop
    //   385: aload 13
    //   387: iconst_1
    //   388: putfield 354	com/cootek/usage/n:d	Z
    //   391: goto +10 -> 401
    //   394: astore 11
    //   396: aload 11
    //   398: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   401: aload 10
    //   403: invokeinterface 164 1 0
    //   408: istore 9
    //   410: iload 9
    //   412: ifne +6 -> 418
    //   415: goto +27 -> 442
    //   418: goto -268 -> 150
    //   421: astore 11
    //   423: goto +222 -> 645
    //   426: astore 12
    //   428: aload 10
    //   430: astore 11
    //   432: goto +129 -> 561
    //   435: aload 10
    //   437: astore 11
    //   439: goto +169 -> 608
    //   442: aload 10
    //   444: ifnull +20 -> 464
    //   447: aload 10
    //   449: invokeinterface 167 1 0
    //   454: goto +10 -> 464
    //   457: astore 10
    //   459: aload 10
    //   461: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   464: new 300	org/json/JSONObject
    //   467: dup
    //   468: invokespecial 301	org/json/JSONObject:<init>	()V
    //   471: astore 10
    //   473: aload 10
    //   475: ldc_w 469
    //   478: aload 12
    //   480: invokevirtual 306	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   483: pop
    //   484: goto +10 -> 494
    //   487: astore 11
    //   489: aload 11
    //   491: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   494: new 361	com/cootek/usage/UsageData
    //   497: dup
    //   498: invokespecial 362	com/cootek/usage/UsageData:<init>	()V
    //   501: astore 11
    //   503: aload 11
    //   505: ldc 23
    //   507: putfield 364	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   510: aload 11
    //   512: iconst_4
    //   513: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   516: putfield 369	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   519: aload 11
    //   521: aload 10
    //   523: invokevirtual 470	org/json/JSONObject:toString	()Ljava/lang/String;
    //   526: putfield 373	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   529: aload 13
    //   531: aload 11
    //   533: putfield 376	com/cootek/usage/n:a	Lcom/cootek/usage/UsageData;
    //   536: aload 13
    //   538: iconst_4
    //   539: invokestatic 366	com/cootek/usage/g:a	(I)Ljava/lang/String;
    //   542: putfield 377	com/cootek/usage/n:c	Ljava/lang/String;
    //   545: aload 13
    //   547: areturn
    //   548: astore 11
    //   550: aconst_null
    //   551: astore 10
    //   553: goto +92 -> 645
    //   556: astore 12
    //   558: aconst_null
    //   559: astore 11
    //   561: aload 11
    //   563: astore 10
    //   565: aload 12
    //   567: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   570: aload 11
    //   572: astore 10
    //   574: aload 13
    //   576: iconst_0
    //   577: putfield 354	com/cootek/usage/n:d	Z
    //   580: aload 11
    //   582: ifnull +20 -> 602
    //   585: aload 11
    //   587: invokeinterface 167 1 0
    //   592: aload 13
    //   594: areturn
    //   595: astore 10
    //   597: aload 10
    //   599: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   602: aload 13
    //   604: areturn
    //   605: aconst_null
    //   606: astore 11
    //   608: aload 11
    //   610: astore 10
    //   612: aload 13
    //   614: iconst_0
    //   615: putfield 354	com/cootek/usage/n:d	Z
    //   618: aload 11
    //   620: ifnull +20 -> 640
    //   623: aload 11
    //   625: invokeinterface 167 1 0
    //   630: aload 13
    //   632: areturn
    //   633: astore 10
    //   635: aload 10
    //   637: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   640: aload 13
    //   642: areturn
    //   643: astore 11
    //   645: aload 10
    //   647: ifnull +20 -> 667
    //   650: aload 10
    //   652: invokeinterface 167 1 0
    //   657: goto +10 -> 667
    //   660: astore 10
    //   662: aload 10
    //   664: invokestatic 172	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   667: aload 11
    //   669: athrow
    //   670: astore 10
    //   672: goto -67 -> 605
    //   675: astore 11
    //   677: goto -242 -> 435
    //   680: goto -427 -> 253
    //   683: iconst_0
    //   684: istore 9
    //   686: goto -410 -> 276
    //   689: iload_2
    //   690: iconst_1
    //   691: if_icmpne -347 -> 344
    //   694: ldc_w 543
    //   697: astore 11
    //   699: goto -369 -> 330
    //   702: astore 11
    //   704: goto -308 -> 396
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	707	0	this	g
    //   187	103	1	j	int
    //   207	485	2	k	int
    //   49	210	3	l1	long
    //   196	68	5	l2	long
    //   168	66	7	l3	long
    //   271	414	9	bool	boolean
    //   20	428	10	localObject1	Object
    //   457	3	10	localRuntimeException1	RuntimeException
    //   471	102	10	localObject2	Object
    //   595	3	10	localRuntimeException2	RuntimeException
    //   610	1	10	localObject3	Object
    //   633	18	10	localRuntimeException3	RuntimeException
    //   660	3	10	localRuntimeException4	RuntimeException
    //   670	1	10	localSecurityException1	SecurityException
    //   37	259	11	localObject4	Object
    //   300	1	11	localJSONException1	JSONException
    //   308	45	11	str1	String
    //   394	3	11	localJSONException2	JSONException
    //   421	1	11	localObject5	Object
    //   430	8	11	localObject6	Object
    //   487	3	11	localJSONException3	JSONException
    //   501	31	11	localUsageData	UsageData
    //   548	1	11	localObject7	Object
    //   559	65	11	localObject8	Object
    //   643	25	11	localObject9	Object
    //   675	1	11	localSecurityException2	SecurityException
    //   697	1	11	str2	String
    //   702	1	11	localJSONException4	JSONException
    //   29	349	12	localJSONArray	JSONArray
    //   426	53	12	localRuntimeException5	RuntimeException
    //   556	10	12	localRuntimeException6	RuntimeException
    //   8	633	13	localN	n
    //   215	165	14	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   330	341	300	org/json/JSONException
    //   217	243	394	org/json/JSONException
    //   253	263	394	org/json/JSONException
    //   276	287	394	org/json/JSONException
    //   310	321	394	org/json/JSONException
    //   127	150	421	finally
    //   150	217	421	finally
    //   217	243	421	finally
    //   253	263	421	finally
    //   276	287	421	finally
    //   310	321	421	finally
    //   330	341	421	finally
    //   362	374	421	finally
    //   377	391	421	finally
    //   396	401	421	finally
    //   401	410	421	finally
    //   127	150	426	java/lang/RuntimeException
    //   150	217	426	java/lang/RuntimeException
    //   217	243	426	java/lang/RuntimeException
    //   253	263	426	java/lang/RuntimeException
    //   276	287	426	java/lang/RuntimeException
    //   310	321	426	java/lang/RuntimeException
    //   330	341	426	java/lang/RuntimeException
    //   362	374	426	java/lang/RuntimeException
    //   377	391	426	java/lang/RuntimeException
    //   396	401	426	java/lang/RuntimeException
    //   401	410	426	java/lang/RuntimeException
    //   447	454	457	java/lang/RuntimeException
    //   473	484	487	org/json/JSONException
    //   31	122	548	finally
    //   31	122	556	java/lang/RuntimeException
    //   585	592	595	java/lang/RuntimeException
    //   623	630	633	java/lang/RuntimeException
    //   565	570	643	finally
    //   574	580	643	finally
    //   612	618	643	finally
    //   650	657	660	java/lang/RuntimeException
    //   31	122	670	java/lang/SecurityException
    //   127	150	675	java/lang/SecurityException
    //   150	217	675	java/lang/SecurityException
    //   217	243	675	java/lang/SecurityException
    //   253	263	675	java/lang/SecurityException
    //   276	287	675	java/lang/SecurityException
    //   310	321	675	java/lang/SecurityException
    //   330	341	675	java/lang/SecurityException
    //   362	374	675	java/lang/SecurityException
    //   377	391	675	java/lang/SecurityException
    //   396	401	675	java/lang/SecurityException
    //   401	410	675	java/lang/SecurityException
    //   362	374	702	org/json/JSONException
    //   377	391	702	org/json/JSONException
  }
  
  private static String g(int paramInt)
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
  
  private n h()
  {
    n localN = new n(this);
    JSONArray localJSONArray = new JSONArray();
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      String str1 = s.a(UsageRecorder.a.getContext());
      String str2 = s.b(UsageRecorder.a.getContext());
      localJSONObject1.put("phone", str1);
      localJSONObject1.put("IMSI", str2);
      localJSONArray.put(localJSONObject1);
      localN.d = true;
    }
    catch (JSONException localJSONException2)
    {
      a.a(localJSONException2);
    }
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      localJSONObject2.put("data", localJSONArray);
    }
    catch (JSONException localJSONException1)
    {
      a.a(localJSONException1);
    }
    UsageData localUsageData = new UsageData();
    localUsageData.type = "noah_info";
    localUsageData.path = a(5);
    localUsageData.value = localJSONObject2.toString();
    localN.a = localUsageData;
    localN.c = a(5);
    return localN;
  }
  
  private static String h(int paramInt)
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
  
  private static String i(int paramInt)
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
  
  final n b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder("error info value: ");
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
}
