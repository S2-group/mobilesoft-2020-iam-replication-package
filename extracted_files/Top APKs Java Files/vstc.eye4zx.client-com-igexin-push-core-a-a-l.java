package com.igexin.push.core.a.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.igexin.push.core.a.e;
import com.igexin.push.core.b;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.bean.m;
import com.igexin.push.core.g;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class l
  implements a
{
  public l() {}
  
  private String a(String paramString)
  {
    try
    {
      Object localObject1 = g.g.getPackageManager().getInstalledPackages(4);
      if (localObject1 == null) {
        return null;
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (paramString.equals(((PackageInfo)localObject2).packageName))
        {
          localObject2 = ((PackageInfo)localObject2).services;
          int j = localObject2.length;
          int i = 0;
          while (i < j)
          {
            Object localObject3 = localObject2[i];
            if ((com.igexin.push.core.a.n.equals(localObject3.name)) || (com.igexin.push.core.a.o.equals(localObject3.name)) || (com.igexin.push.core.a.p.equals(localObject3.name)))
            {
              paramString = localObject3.name;
              return paramString;
            }
            i += 1;
          }
        }
      }
      return null;
    }
    catch (Exception paramString) {}
  }
  
  /* Error */
  private List a(int paramInt, String paramString)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic 90	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +7 -> 11
    //   7: aconst_null
    //   8: astore_2
    //   9: aload_2
    //   10: areturn
    //   11: new 92	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   18: ldc 95
    //   20: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_2
    //   24: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc 101
    //   29: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: iload_1
    //   33: invokevirtual 104	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   36: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: invokestatic 113	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   42: new 115	java/io/File
    //   45: dup
    //   46: ldc 117
    //   48: invokespecial 119	java/io/File:<init>	(Ljava/lang/String;)V
    //   51: astore 11
    //   53: aload 11
    //   55: invokevirtual 122	java/io/File:exists	()Z
    //   58: ifne +5 -> 63
    //   61: aconst_null
    //   62: areturn
    //   63: aload 11
    //   65: invokevirtual 126	java/io/File:list	()[Ljava/lang/String;
    //   68: astore 12
    //   70: aload 12
    //   72: ifnonnull +5 -> 77
    //   75: aconst_null
    //   76: areturn
    //   77: aconst_null
    //   78: astore 5
    //   80: iconst_0
    //   81: istore_3
    //   82: iload_3
    //   83: aload 12
    //   85: arraylength
    //   86: if_icmpge +858 -> 944
    //   89: aload 12
    //   91: iload_3
    //   92: aaload
    //   93: ldc -128
    //   95: invokevirtual 132	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   98: ifle +952 -> 1050
    //   101: aload 12
    //   103: iload_3
    //   104: aaload
    //   105: ldc -122
    //   107: invokevirtual 61	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   110: ifne +940 -> 1050
    //   113: aload 12
    //   115: iload_3
    //   116: aaload
    //   117: ldc -120
    //   119: invokevirtual 61	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   122: ifne +928 -> 1050
    //   125: aload 12
    //   127: iload_3
    //   128: aaload
    //   129: ldc -118
    //   131: invokevirtual 61	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   134: ifne +916 -> 1050
    //   137: aload 12
    //   139: iload_3
    //   140: aaload
    //   141: iconst_0
    //   142: aload 12
    //   144: iload_3
    //   145: aaload
    //   146: invokevirtual 142	java/lang/String:length	()I
    //   149: iconst_3
    //   150: isub
    //   151: invokevirtual 146	java/lang/String:substring	(II)Ljava/lang/String;
    //   154: astore 13
    //   156: new 115	java/io/File
    //   159: dup
    //   160: new 92	java/lang/StringBuilder
    //   163: dup
    //   164: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   167: aload 11
    //   169: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   172: ldc -105
    //   174: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: aload 12
    //   179: iload_3
    //   180: aaload
    //   181: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   187: invokespecial 119	java/io/File:<init>	(Ljava/lang/String;)V
    //   190: astore 6
    //   192: sipush 1024
    //   195: newarray byte
    //   197: astore 8
    //   199: new 153	java/io/FileInputStream
    //   202: dup
    //   203: aload 6
    //   205: invokespecial 156	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   208: astore 6
    //   210: new 158	java/io/ByteArrayOutputStream
    //   213: dup
    //   214: invokespecial 159	java/io/ByteArrayOutputStream:<init>	()V
    //   217: astore 9
    //   219: aload 5
    //   221: astore 7
    //   223: aload 6
    //   225: aload 8
    //   227: invokevirtual 165	java/io/InputStream:read	([B)I
    //   230: istore 4
    //   232: iload 4
    //   234: iconst_m1
    //   235: if_icmpeq +95 -> 330
    //   238: aload 5
    //   240: astore 7
    //   242: aload 9
    //   244: aload 8
    //   246: iconst_0
    //   247: iload 4
    //   249: invokevirtual 169	java/io/ByteArrayOutputStream:write	([BII)V
    //   252: goto -33 -> 219
    //   255: astore 8
    //   257: aload 7
    //   259: astore 5
    //   261: aload 9
    //   263: astore 7
    //   265: new 92	java/lang/StringBuilder
    //   268: dup
    //   269: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   272: ldc -85
    //   274: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: aload 8
    //   279: invokevirtual 172	java/lang/Exception:toString	()Ljava/lang/String;
    //   282: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   288: invokestatic 113	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   291: aload 6
    //   293: ifnull +8 -> 301
    //   296: aload 6
    //   298: invokevirtual 175	java/io/InputStream:close	()V
    //   301: aload 5
    //   303: astore 6
    //   305: aload 7
    //   307: ifnull +12 -> 319
    //   310: aload 7
    //   312: invokevirtual 176	java/io/ByteArrayOutputStream:close	()V
    //   315: aload 5
    //   317: astore 6
    //   319: iload_3
    //   320: iconst_1
    //   321: iadd
    //   322: istore_3
    //   323: aload 6
    //   325: astore 5
    //   327: goto -245 -> 82
    //   330: aload 5
    //   332: astore 7
    //   334: aload 9
    //   336: invokevirtual 180	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   339: astore 10
    //   341: aload 5
    //   343: astore 7
    //   345: getstatic 183	com/igexin/push/core/g:u	Ljava/lang/String;
    //   348: ifnonnull +321 -> 669
    //   351: ldc -71
    //   353: astore 8
    //   355: aload 5
    //   357: astore 7
    //   359: new 57	java/lang/String
    //   362: dup
    //   363: aload 10
    //   365: aload 8
    //   367: invokestatic 189	com/igexin/a/b/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   370: invokestatic 194	com/igexin/a/a/a/a:a	([BLjava/lang/String;)[B
    //   373: invokespecial 197	java/lang/String:<init>	([B)V
    //   376: ldc -57
    //   378: invokevirtual 203	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   381: astore 14
    //   383: aload 5
    //   385: astore 7
    //   387: getstatic 209	java/lang/System:out	Ljava/io/PrintStream;
    //   390: new 92	java/lang/StringBuilder
    //   393: dup
    //   394: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   397: ldc -45
    //   399: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: aload 14
    //   404: arraylength
    //   405: invokevirtual 104	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   408: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   411: invokevirtual 216	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   414: aload 5
    //   416: astore 7
    //   418: aload 14
    //   420: iconst_0
    //   421: aaload
    //   422: ldc -38
    //   424: invokevirtual 222	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   427: ifeq +36 -> 463
    //   430: aload 5
    //   432: astore 7
    //   434: aload 14
    //   436: iconst_0
    //   437: aaload
    //   438: ldc -32
    //   440: invokevirtual 132	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   443: iflt +238 -> 681
    //   446: aload 5
    //   448: astore 7
    //   450: aload 14
    //   452: iconst_0
    //   453: aload 14
    //   455: iconst_0
    //   456: aaload
    //   457: bipush 7
    //   459: invokevirtual 227	java/lang/String:substring	(I)Ljava/lang/String;
    //   462: aastore
    //   463: aconst_null
    //   464: astore 10
    //   466: aload 10
    //   468: astore 8
    //   470: aload 14
    //   472: ifnull +81 -> 553
    //   475: aload 5
    //   477: astore 7
    //   479: aload 10
    //   481: astore 8
    //   483: aload 14
    //   485: arraylength
    //   486: iconst_2
    //   487: if_icmple +66 -> 553
    //   490: aload 14
    //   492: iconst_2
    //   493: aaload
    //   494: astore 10
    //   496: aload 10
    //   498: astore 8
    //   500: aload 10
    //   502: ifnull +51 -> 553
    //   505: aload 5
    //   507: astore 7
    //   509: aload 10
    //   511: astore 8
    //   513: aload 10
    //   515: ldc -32
    //   517: invokevirtual 61	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   520: ifeq +6 -> 526
    //   523: aconst_null
    //   524: astore 8
    //   526: aload 5
    //   528: astore 7
    //   530: new 92	java/lang/StringBuilder
    //   533: dup
    //   534: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   537: ldc -27
    //   539: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: aload 8
    //   544: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   547: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   550: invokestatic 113	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   553: aload 8
    //   555: astore 10
    //   557: aload 8
    //   559: ifnonnull +43 -> 602
    //   562: aload 5
    //   564: astore 7
    //   566: aload 14
    //   568: iconst_0
    //   569: aaload
    //   570: invokestatic 189	com/igexin/a/b/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   573: astore 10
    //   575: aload 5
    //   577: astore 7
    //   579: new 92	java/lang/StringBuilder
    //   582: dup
    //   583: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   586: ldc -25
    //   588: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   591: aload 10
    //   593: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   596: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   599: invokestatic 113	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   602: iload_1
    //   603: ifne +141 -> 744
    //   606: aload 5
    //   608: astore 7
    //   610: aload 5
    //   612: astore 8
    //   614: aload_2
    //   615: aload 10
    //   617: invokevirtual 61	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   620: ifeq +225 -> 845
    //   623: aload 5
    //   625: astore 7
    //   627: new 233	java/util/ArrayList
    //   630: dup
    //   631: invokespecial 234	java/util/ArrayList:<init>	()V
    //   634: astore 5
    //   636: aload 5
    //   638: aload 13
    //   640: invokeinterface 237 2 0
    //   645: pop
    //   646: aload 6
    //   648: ifnull +8 -> 656
    //   651: aload 6
    //   653: invokevirtual 175	java/io/InputStream:close	()V
    //   656: aload 9
    //   658: ifnull +8 -> 666
    //   661: aload 9
    //   663: invokevirtual 176	java/io/ByteArrayOutputStream:close	()V
    //   666: aload 5
    //   668: areturn
    //   669: aload 5
    //   671: astore 7
    //   673: getstatic 183	com/igexin/push/core/g:u	Ljava/lang/String;
    //   676: astore 8
    //   678: goto -323 -> 355
    //   681: aload 5
    //   683: astore 7
    //   685: aload 14
    //   687: iconst_0
    //   688: aload 14
    //   690: iconst_0
    //   691: aaload
    //   692: bipush 20
    //   694: invokevirtual 227	java/lang/String:substring	(I)Ljava/lang/String;
    //   697: aastore
    //   698: goto -235 -> 463
    //   701: astore_2
    //   702: aload 9
    //   704: astore 7
    //   706: aload 6
    //   708: ifnull +8 -> 716
    //   711: aload 6
    //   713: invokevirtual 175	java/io/InputStream:close	()V
    //   716: aload 7
    //   718: ifnull +8 -> 726
    //   721: aload 7
    //   723: invokevirtual 176	java/io/ByteArrayOutputStream:close	()V
    //   726: aload_2
    //   727: athrow
    //   728: astore_2
    //   729: aload_2
    //   730: invokevirtual 240	java/io/IOException:printStackTrace	()V
    //   733: goto -77 -> 656
    //   736: astore_2
    //   737: aload_2
    //   738: invokevirtual 240	java/io/IOException:printStackTrace	()V
    //   741: goto -75 -> 666
    //   744: aload 5
    //   746: astore 7
    //   748: aload 5
    //   750: astore 8
    //   752: aload 14
    //   754: arraylength
    //   755: iconst_1
    //   756: if_icmple +89 -> 845
    //   759: aload 5
    //   761: astore 7
    //   763: aload 5
    //   765: astore 8
    //   767: aload_2
    //   768: aload 14
    //   770: iconst_1
    //   771: aaload
    //   772: invokevirtual 61	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   775: ifeq +39 -> 814
    //   778: aload 5
    //   780: astore 8
    //   782: aload 5
    //   784: ifnonnull +16 -> 800
    //   787: aload 5
    //   789: astore 7
    //   791: new 233	java/util/ArrayList
    //   794: dup
    //   795: invokespecial 234	java/util/ArrayList:<init>	()V
    //   798: astore 8
    //   800: aload 8
    //   802: astore 7
    //   804: aload 8
    //   806: aload 13
    //   808: invokeinterface 237 2 0
    //   813: pop
    //   814: aload 8
    //   816: astore 7
    //   818: ldc -85
    //   820: new 92	java/lang/StringBuilder
    //   823: dup
    //   824: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   827: ldc -14
    //   829: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   832: aload 14
    //   834: iconst_1
    //   835: aaload
    //   836: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   839: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   842: invokestatic 245	com/igexin/a/a/c/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   845: aload 6
    //   847: ifnull +8 -> 855
    //   850: aload 6
    //   852: invokevirtual 175	java/io/InputStream:close	()V
    //   855: aload 8
    //   857: astore 6
    //   859: aload 9
    //   861: ifnull -542 -> 319
    //   864: aload 9
    //   866: invokevirtual 176	java/io/ByteArrayOutputStream:close	()V
    //   869: aload 8
    //   871: astore 6
    //   873: goto -554 -> 319
    //   876: astore 5
    //   878: aload 5
    //   880: invokevirtual 240	java/io/IOException:printStackTrace	()V
    //   883: aload 8
    //   885: astore 6
    //   887: goto -568 -> 319
    //   890: astore 5
    //   892: aload 5
    //   894: invokevirtual 240	java/io/IOException:printStackTrace	()V
    //   897: goto -42 -> 855
    //   900: astore 6
    //   902: aload 6
    //   904: invokevirtual 240	java/io/IOException:printStackTrace	()V
    //   907: goto -606 -> 301
    //   910: astore 6
    //   912: aload 6
    //   914: invokevirtual 240	java/io/IOException:printStackTrace	()V
    //   917: aload 5
    //   919: astore 6
    //   921: goto -602 -> 319
    //   924: astore 5
    //   926: aload 5
    //   928: invokevirtual 240	java/io/IOException:printStackTrace	()V
    //   931: goto -215 -> 716
    //   934: astore 5
    //   936: aload 5
    //   938: invokevirtual 240	java/io/IOException:printStackTrace	()V
    //   941: goto -215 -> 726
    //   944: aload 5
    //   946: astore_2
    //   947: aload 5
    //   949: ifnull -940 -> 9
    //   952: aload 5
    //   954: astore_2
    //   955: aload 5
    //   957: invokeinterface 248 1 0
    //   962: iconst_1
    //   963: if_icmpne -954 -> 9
    //   966: new 92	java/lang/StringBuilder
    //   969: dup
    //   970: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   973: ldc -6
    //   975: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   978: aload 5
    //   980: iconst_0
    //   981: invokeinterface 254 2 0
    //   986: checkcast 57	java/lang/String
    //   989: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   992: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   995: invokestatic 113	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   998: aload 5
    //   1000: areturn
    //   1001: astore_2
    //   1002: goto -296 -> 706
    //   1005: astore_2
    //   1006: aconst_null
    //   1007: astore 6
    //   1009: aconst_null
    //   1010: astore 7
    //   1012: goto -306 -> 706
    //   1015: astore_2
    //   1016: aconst_null
    //   1017: astore 7
    //   1019: goto -313 -> 706
    //   1022: astore 8
    //   1024: aconst_null
    //   1025: astore 7
    //   1027: aconst_null
    //   1028: astore 6
    //   1030: goto -765 -> 265
    //   1033: astore 8
    //   1035: aconst_null
    //   1036: astore 7
    //   1038: goto -773 -> 265
    //   1041: astore 8
    //   1043: aload 9
    //   1045: astore 7
    //   1047: goto -782 -> 265
    //   1050: aload 5
    //   1052: astore 6
    //   1054: goto -735 -> 319
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1057	0	this	l
    //   0	1057	1	paramInt	int
    //   0	1057	2	paramString	String
    //   81	242	3	i	int
    //   230	18	4	j	int
    //   78	710	5	localObject1	Object
    //   876	3	5	localIOException1	java.io.IOException
    //   890	28	5	localIOException2	java.io.IOException
    //   924	3	5	localIOException3	java.io.IOException
    //   934	117	5	localIOException4	java.io.IOException
    //   190	696	6	localObject2	Object
    //   900	3	6	localIOException5	java.io.IOException
    //   910	3	6	localIOException6	java.io.IOException
    //   919	134	6	localIOException7	java.io.IOException
    //   221	825	7	localObject3	Object
    //   197	48	8	arrayOfByte	byte[]
    //   255	23	8	localException1	Exception
    //   353	531	8	localObject4	Object
    //   1022	1	8	localException2	Exception
    //   1033	1	8	localException3	Exception
    //   1041	1	8	localException4	Exception
    //   217	827	9	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   339	277	10	localObject5	Object
    //   51	117	11	localFile	java.io.File
    //   68	110	12	arrayOfString1	String[]
    //   154	653	13	str	String
    //   381	452	14	arrayOfString2	String[]
    // Exception table:
    //   from	to	target	type
    //   223	232	255	java/lang/Exception
    //   242	252	255	java/lang/Exception
    //   334	341	255	java/lang/Exception
    //   345	351	255	java/lang/Exception
    //   359	383	255	java/lang/Exception
    //   387	414	255	java/lang/Exception
    //   418	430	255	java/lang/Exception
    //   434	446	255	java/lang/Exception
    //   450	463	255	java/lang/Exception
    //   483	490	255	java/lang/Exception
    //   513	523	255	java/lang/Exception
    //   530	553	255	java/lang/Exception
    //   566	575	255	java/lang/Exception
    //   579	602	255	java/lang/Exception
    //   614	623	255	java/lang/Exception
    //   627	636	255	java/lang/Exception
    //   673	678	255	java/lang/Exception
    //   685	698	255	java/lang/Exception
    //   752	759	255	java/lang/Exception
    //   767	778	255	java/lang/Exception
    //   791	800	255	java/lang/Exception
    //   804	814	255	java/lang/Exception
    //   818	845	255	java/lang/Exception
    //   223	232	701	finally
    //   242	252	701	finally
    //   334	341	701	finally
    //   345	351	701	finally
    //   359	383	701	finally
    //   387	414	701	finally
    //   418	430	701	finally
    //   434	446	701	finally
    //   450	463	701	finally
    //   483	490	701	finally
    //   513	523	701	finally
    //   530	553	701	finally
    //   566	575	701	finally
    //   579	602	701	finally
    //   614	623	701	finally
    //   627	636	701	finally
    //   636	646	701	finally
    //   673	678	701	finally
    //   685	698	701	finally
    //   752	759	701	finally
    //   767	778	701	finally
    //   791	800	701	finally
    //   804	814	701	finally
    //   818	845	701	finally
    //   651	656	728	java/io/IOException
    //   661	666	736	java/io/IOException
    //   864	869	876	java/io/IOException
    //   850	855	890	java/io/IOException
    //   296	301	900	java/io/IOException
    //   310	315	910	java/io/IOException
    //   711	716	924	java/io/IOException
    //   721	726	934	java/io/IOException
    //   265	291	1001	finally
    //   137	210	1005	finally
    //   210	219	1015	finally
    //   137	210	1022	java/lang/Exception
    //   210	219	1033	java/lang/Exception
    //   636	646	1041	java/lang/Exception
  }
  
  private void a(String paramString, boolean paramBoolean)
  {
    com.igexin.a.a.c.a.a("WakeupAction check finished, start service : " + paramString + ", is force start : " + paramBoolean);
    String str = a(paramString);
    if (str != null)
    {
      Intent localIntent;
      try
      {
        if (str.equals(com.igexin.push.core.a.n))
        {
          if (paramBoolean)
          {
            localIntent = new Intent();
            localIntent.setClassName(paramString, str);
            localIntent.putExtra("action", "com.igexin.action.initialize.slave");
            localIntent.putExtra("op_app", g.e);
            localIntent.putExtra("isSlave", true);
            g.g.startService(localIntent);
            return;
          }
          localIntent = new Intent();
          localIntent.setClassName(paramString, str);
          g.g.startService(localIntent);
          return;
        }
      }
      catch (Exception paramString)
      {
        com.igexin.a.a.c.a.a("WakeupAction" + paramString.toString());
        return;
      }
      if ((str.equals(com.igexin.push.core.a.o)) || (str.equals(com.igexin.push.core.a.p)))
      {
        localIntent = new Intent();
        localIntent.setClassName(paramString, str);
        g.g.startService(localIntent);
      }
    }
  }
  
  public b a(PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    return b.a;
  }
  
  public BaseAction a(JSONObject paramJSONObject)
  {
    try
    {
      if ((com.igexin.push.config.l.r) && (paramJSONObject.has("do")) && (paramJSONObject.has("actionid")) && (paramJSONObject.has("type")) && ((paramJSONObject.has("pkgname")) || (paramJSONObject.has("appid")) || (paramJSONObject.has("cid"))))
      {
        m localM = new m();
        localM.setType("wakeupsdk");
        localM.setActionId(paramJSONObject.getString("actionid"));
        localM.setDoActionId(paramJSONObject.getString("do"));
        if ((paramJSONObject.has("pkgname")) && (!TextUtils.isEmpty(paramJSONObject.getString("pkgname")))) {
          localM.a(paramJSONObject.getString("pkgname"));
        }
        while (paramJSONObject.has("is_forcestart"))
        {
          localM.a(paramJSONObject.getBoolean("is_forcestart"));
          return localM;
          if ((paramJSONObject.has("cid")) && (!TextUtils.isEmpty(paramJSONObject.getString("cid")))) {
            localM.c(paramJSONObject.getString("cid"));
          } else if ((paramJSONObject.has("appid")) && (!TextUtils.isEmpty(paramJSONObject.getString("appid")))) {
            localM.b(paramJSONObject.getString("appid"));
          }
        }
        return localM;
      }
    }
    catch (Exception paramJSONObject) {}
    return null;
  }
  
  public boolean b(PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    if ((paramPushTaskBean != null) && (paramBaseAction != null)) {
      try
      {
        m localM = (m)paramBaseAction;
        String str = localM.b();
        Object localObject = str;
        if (TextUtils.isEmpty(str))
        {
          localObject = str;
          if (!TextUtils.isEmpty(localM.d()))
          {
            List localList = a(0, localM.d());
            localObject = str;
            if (localList != null)
            {
              localObject = str;
              if (localList.size() == 1) {
                localObject = (String)localList.get(0);
              }
            }
          }
        }
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          a((String)localObject, localM.a());
        }
        while (!paramBaseAction.getDoActionId().equals(""))
        {
          e.a().a(paramPushTaskBean.getTaskId(), paramPushTaskBean.getMessageId(), paramBaseAction.getDoActionId());
          break;
          if (!TextUtils.isEmpty(localM.c()))
          {
            localObject = a(1, localM.c());
            if ((localObject == null) || (((List)localObject).size() == 0)) {
              break label249;
            }
            localObject = ((List)localObject).iterator();
            while (((Iterator)localObject).hasNext()) {
              a((String)((Iterator)localObject).next(), localM.a());
            }
          }
        }
        return true;
      }
      catch (Exception paramPushTaskBean)
      {
        com.igexin.a.a.c.a.a("WakeupAction" + paramPushTaskBean.toString());
        return false;
      }
    }
    label249:
    return false;
  }
}
