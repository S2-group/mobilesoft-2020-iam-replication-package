package com.igexin.push.core.a.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.igexin.push.config.m;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.bean.q;
import com.igexin.push.core.g;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class n
  implements a
{
  private static final String b = com.igexin.push.core.a.n;
  private static final String c = com.igexin.push.core.a.p;
  private static final String d = com.igexin.push.core.a.o;
  private PackageManager a;
  
  public n() {}
  
  private String a(String paramString)
  {
    try
    {
      Object localObject1 = g.f.getPackageManager().getInstalledPackages(4);
      if (localObject1 != null)
      {
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
              if ((b.equals(localObject3.name)) || (d.equals(localObject3.name)) || (c.equals(localObject3.name)))
              {
                paramString = localObject3.name;
                return paramString;
              }
              i += 1;
            }
          }
        }
      }
      return null;
    }
    catch (Exception paramString)
    {
      com.igexin.b.a.c.b.a(paramString.toString());
    }
  }
  
  /* Error */
  private List<String> a(int paramInt, String paramString)
  {
    // Byte code:
    //   0: new 109	java/io/File
    //   3: dup
    //   4: ldc 111
    //   6: invokespecial 113	java/io/File:<init>	(Ljava/lang/String;)V
    //   9: astore 11
    //   11: aload 11
    //   13: invokevirtual 116	java/io/File:exists	()Z
    //   16: ifne +7 -> 23
    //   19: aconst_null
    //   20: astore_2
    //   21: aload_2
    //   22: areturn
    //   23: aload 11
    //   25: invokevirtual 120	java/io/File:list	()[Ljava/lang/String;
    //   28: astore 12
    //   30: aload 12
    //   32: ifnonnull +5 -> 37
    //   35: aconst_null
    //   36: areturn
    //   37: aconst_null
    //   38: astore 5
    //   40: iconst_0
    //   41: istore_3
    //   42: iload_3
    //   43: aload 12
    //   45: arraylength
    //   46: if_icmpge +845 -> 891
    //   49: aload 12
    //   51: iload_3
    //   52: aaload
    //   53: ldc 122
    //   55: invokevirtual 126	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   58: ifle +940 -> 998
    //   61: aload 12
    //   63: iload_3
    //   64: aaload
    //   65: ldc -128
    //   67: invokevirtual 86	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   70: ifne +928 -> 998
    //   73: aload 12
    //   75: iload_3
    //   76: aaload
    //   77: ldc -126
    //   79: invokevirtual 86	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   82: ifne +916 -> 998
    //   85: aload 12
    //   87: iload_3
    //   88: aaload
    //   89: ldc -124
    //   91: invokevirtual 86	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   94: ifne +904 -> 998
    //   97: aload 12
    //   99: iload_3
    //   100: aaload
    //   101: iconst_0
    //   102: aload 12
    //   104: iload_3
    //   105: aaload
    //   106: invokevirtual 136	java/lang/String:length	()I
    //   109: iconst_3
    //   110: isub
    //   111: invokevirtual 140	java/lang/String:substring	(II)Ljava/lang/String;
    //   114: astore 13
    //   116: new 109	java/io/File
    //   119: dup
    //   120: new 142	java/lang/StringBuilder
    //   123: dup
    //   124: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   127: aload 11
    //   129: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   132: ldc -107
    //   134: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload 12
    //   139: iload_3
    //   140: aaload
    //   141: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: invokespecial 113	java/io/File:<init>	(Ljava/lang/String;)V
    //   150: astore 6
    //   152: sipush 1024
    //   155: newarray byte
    //   157: astore 8
    //   159: new 155	java/io/FileInputStream
    //   162: dup
    //   163: aload 6
    //   165: invokespecial 158	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   168: astore 6
    //   170: new 160	java/io/ByteArrayOutputStream
    //   173: dup
    //   174: invokespecial 161	java/io/ByteArrayOutputStream:<init>	()V
    //   177: astore 9
    //   179: aload 5
    //   181: astore 7
    //   183: aload 6
    //   185: aload 8
    //   187: invokevirtual 167	java/io/InputStream:read	([B)I
    //   190: istore 4
    //   192: iload 4
    //   194: iconst_m1
    //   195: if_icmpeq +95 -> 290
    //   198: aload 5
    //   200: astore 7
    //   202: aload 9
    //   204: aload 8
    //   206: iconst_0
    //   207: iload 4
    //   209: invokevirtual 171	java/io/ByteArrayOutputStream:write	([BII)V
    //   212: goto -33 -> 179
    //   215: astore 8
    //   217: aload 7
    //   219: astore 5
    //   221: aload 9
    //   223: astore 7
    //   225: new 142	java/lang/StringBuilder
    //   228: dup
    //   229: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   232: ldc -83
    //   234: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: aload 8
    //   239: invokevirtual 99	java/lang/Exception:toString	()Ljava/lang/String;
    //   242: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   248: invokestatic 104	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   251: aload 6
    //   253: ifnull +8 -> 261
    //   256: aload 6
    //   258: invokevirtual 176	java/io/InputStream:close	()V
    //   261: aload 5
    //   263: astore 6
    //   265: aload 7
    //   267: ifnull +12 -> 279
    //   270: aload 7
    //   272: invokevirtual 177	java/io/ByteArrayOutputStream:close	()V
    //   275: aload 5
    //   277: astore 6
    //   279: iload_3
    //   280: iconst_1
    //   281: iadd
    //   282: istore_3
    //   283: aload 6
    //   285: astore 5
    //   287: goto -245 -> 42
    //   290: aload 5
    //   292: astore 7
    //   294: aload 9
    //   296: invokevirtual 181	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   299: astore 10
    //   301: aload 5
    //   303: astore 7
    //   305: getstatic 184	com/igexin/push/core/g:t	Ljava/lang/String;
    //   308: ifnonnull +308 -> 616
    //   311: ldc -70
    //   313: astore 8
    //   315: aload 5
    //   317: astore 7
    //   319: new 82	java/lang/String
    //   322: dup
    //   323: aload 10
    //   325: aload 8
    //   327: invokestatic 190	com/igexin/b/b/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   330: invokestatic 195	com/igexin/b/a/a/a:c	([BLjava/lang/String;)[B
    //   333: invokespecial 198	java/lang/String:<init>	([B)V
    //   336: ldc -56
    //   338: invokevirtual 204	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   341: astore 14
    //   343: aload 5
    //   345: astore 7
    //   347: getstatic 210	java/lang/System:out	Ljava/io/PrintStream;
    //   350: new 142	java/lang/StringBuilder
    //   353: dup
    //   354: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   357: ldc -44
    //   359: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: aload 14
    //   364: arraylength
    //   365: invokevirtual 215	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   368: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   371: invokevirtual 220	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   374: aload 5
    //   376: astore 7
    //   378: aload 14
    //   380: iconst_0
    //   381: aaload
    //   382: ldc -34
    //   384: invokevirtual 226	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   387: ifeq +36 -> 423
    //   390: aload 5
    //   392: astore 7
    //   394: aload 14
    //   396: iconst_0
    //   397: aaload
    //   398: ldc -28
    //   400: invokevirtual 232	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   403: ifeq +225 -> 628
    //   406: aload 5
    //   408: astore 7
    //   410: aload 14
    //   412: iconst_0
    //   413: aload 14
    //   415: iconst_0
    //   416: aaload
    //   417: bipush 7
    //   419: invokevirtual 235	java/lang/String:substring	(I)Ljava/lang/String;
    //   422: aastore
    //   423: aconst_null
    //   424: astore 8
    //   426: aload 5
    //   428: astore 7
    //   430: aload 14
    //   432: arraylength
    //   433: iconst_2
    //   434: if_icmple +66 -> 500
    //   437: aload 14
    //   439: iconst_2
    //   440: aaload
    //   441: astore 10
    //   443: aload 10
    //   445: astore 8
    //   447: aload 10
    //   449: ifnull +51 -> 500
    //   452: aload 5
    //   454: astore 7
    //   456: aload 10
    //   458: astore 8
    //   460: aload 10
    //   462: ldc -28
    //   464: invokevirtual 86	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   467: ifeq +6 -> 473
    //   470: aconst_null
    //   471: astore 8
    //   473: aload 5
    //   475: astore 7
    //   477: new 142	java/lang/StringBuilder
    //   480: dup
    //   481: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   484: ldc -19
    //   486: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   489: aload 8
    //   491: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   494: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   497: invokestatic 104	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   500: aload 8
    //   502: astore 10
    //   504: aload 8
    //   506: ifnonnull +43 -> 549
    //   509: aload 5
    //   511: astore 7
    //   513: aload 14
    //   515: iconst_0
    //   516: aaload
    //   517: invokestatic 190	com/igexin/b/b/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   520: astore 10
    //   522: aload 5
    //   524: astore 7
    //   526: new 142	java/lang/StringBuilder
    //   529: dup
    //   530: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   533: ldc -17
    //   535: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   538: aload 10
    //   540: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   546: invokestatic 104	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   549: iload_1
    //   550: ifne +141 -> 691
    //   553: aload 5
    //   555: astore 7
    //   557: aload 5
    //   559: astore 8
    //   561: aload_2
    //   562: aload 10
    //   564: invokevirtual 86	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   567: ifeq +225 -> 792
    //   570: aload 5
    //   572: astore 7
    //   574: new 241	java/util/ArrayList
    //   577: dup
    //   578: invokespecial 242	java/util/ArrayList:<init>	()V
    //   581: astore 5
    //   583: aload 5
    //   585: aload 13
    //   587: invokeinterface 245 2 0
    //   592: pop
    //   593: aload 6
    //   595: ifnull +8 -> 603
    //   598: aload 6
    //   600: invokevirtual 176	java/io/InputStream:close	()V
    //   603: aload 9
    //   605: ifnull +8 -> 613
    //   608: aload 9
    //   610: invokevirtual 177	java/io/ByteArrayOutputStream:close	()V
    //   613: aload 5
    //   615: areturn
    //   616: aload 5
    //   618: astore 7
    //   620: getstatic 184	com/igexin/push/core/g:t	Ljava/lang/String;
    //   623: astore 8
    //   625: goto -310 -> 315
    //   628: aload 5
    //   630: astore 7
    //   632: aload 14
    //   634: iconst_0
    //   635: aload 14
    //   637: iconst_0
    //   638: aaload
    //   639: bipush 20
    //   641: invokevirtual 235	java/lang/String:substring	(I)Ljava/lang/String;
    //   644: aastore
    //   645: goto -222 -> 423
    //   648: astore_2
    //   649: aload 9
    //   651: astore 7
    //   653: aload 6
    //   655: ifnull +8 -> 663
    //   658: aload 6
    //   660: invokevirtual 176	java/io/InputStream:close	()V
    //   663: aload 7
    //   665: ifnull +8 -> 673
    //   668: aload 7
    //   670: invokevirtual 177	java/io/ByteArrayOutputStream:close	()V
    //   673: aload_2
    //   674: athrow
    //   675: astore_2
    //   676: aload_2
    //   677: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   680: goto -77 -> 603
    //   683: astore_2
    //   684: aload_2
    //   685: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   688: goto -75 -> 613
    //   691: aload 5
    //   693: astore 7
    //   695: aload 5
    //   697: astore 8
    //   699: aload 14
    //   701: arraylength
    //   702: iconst_1
    //   703: if_icmple +89 -> 792
    //   706: aload 5
    //   708: astore 7
    //   710: aload 5
    //   712: astore 8
    //   714: aload_2
    //   715: aload 14
    //   717: iconst_1
    //   718: aaload
    //   719: invokevirtual 86	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   722: ifeq +39 -> 761
    //   725: aload 5
    //   727: astore 8
    //   729: aload 5
    //   731: ifnonnull +16 -> 747
    //   734: aload 5
    //   736: astore 7
    //   738: new 241	java/util/ArrayList
    //   741: dup
    //   742: invokespecial 242	java/util/ArrayList:<init>	()V
    //   745: astore 8
    //   747: aload 8
    //   749: astore 7
    //   751: aload 8
    //   753: aload 13
    //   755: invokeinterface 245 2 0
    //   760: pop
    //   761: aload 8
    //   763: astore 7
    //   765: ldc -83
    //   767: new 142	java/lang/StringBuilder
    //   770: dup
    //   771: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   774: ldc -6
    //   776: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   779: aload 14
    //   781: iconst_1
    //   782: aaload
    //   783: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   786: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   789: invokestatic 253	com/igexin/b/a/c/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   792: aload 6
    //   794: ifnull +8 -> 802
    //   797: aload 6
    //   799: invokevirtual 176	java/io/InputStream:close	()V
    //   802: aload 8
    //   804: astore 6
    //   806: aload 9
    //   808: ifnull -529 -> 279
    //   811: aload 9
    //   813: invokevirtual 177	java/io/ByteArrayOutputStream:close	()V
    //   816: aload 8
    //   818: astore 6
    //   820: goto -541 -> 279
    //   823: astore 5
    //   825: aload 5
    //   827: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   830: aload 8
    //   832: astore 6
    //   834: goto -555 -> 279
    //   837: astore 5
    //   839: aload 5
    //   841: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   844: goto -42 -> 802
    //   847: astore 6
    //   849: aload 6
    //   851: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   854: goto -593 -> 261
    //   857: astore 6
    //   859: aload 6
    //   861: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   864: aload 5
    //   866: astore 6
    //   868: goto -589 -> 279
    //   871: astore 5
    //   873: aload 5
    //   875: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   878: goto -215 -> 663
    //   881: astore 5
    //   883: aload 5
    //   885: invokevirtual 248	java/io/IOException:printStackTrace	()V
    //   888: goto -215 -> 673
    //   891: aload 5
    //   893: astore_2
    //   894: aload 5
    //   896: ifnull -875 -> 21
    //   899: aload 5
    //   901: astore_2
    //   902: aload 5
    //   904: invokeinterface 256 1 0
    //   909: iconst_1
    //   910: if_icmpne -889 -> 21
    //   913: new 142	java/lang/StringBuilder
    //   916: dup
    //   917: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   920: ldc_w 258
    //   923: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   926: aload 5
    //   928: iconst_0
    //   929: invokeinterface 262 2 0
    //   934: checkcast 82	java/lang/String
    //   937: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   940: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   943: invokestatic 104	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   946: aload 5
    //   948: areturn
    //   949: astore_2
    //   950: goto -297 -> 653
    //   953: astore_2
    //   954: aconst_null
    //   955: astore 6
    //   957: aconst_null
    //   958: astore 7
    //   960: goto -307 -> 653
    //   963: astore_2
    //   964: aconst_null
    //   965: astore 7
    //   967: goto -314 -> 653
    //   970: astore 8
    //   972: aconst_null
    //   973: astore 7
    //   975: aconst_null
    //   976: astore 6
    //   978: goto -753 -> 225
    //   981: astore 8
    //   983: aconst_null
    //   984: astore 7
    //   986: goto -761 -> 225
    //   989: astore 8
    //   991: aload 9
    //   993: astore 7
    //   995: goto -770 -> 225
    //   998: aload 5
    //   1000: astore 6
    //   1002: goto -723 -> 279
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1005	0	this	n
    //   0	1005	1	paramInt	int
    //   0	1005	2	paramString	String
    //   41	242	3	i	int
    //   190	18	4	j	int
    //   38	697	5	localObject1	Object
    //   823	3	5	localIOException1	java.io.IOException
    //   837	28	5	localIOException2	java.io.IOException
    //   871	3	5	localIOException3	java.io.IOException
    //   881	118	5	localIOException4	java.io.IOException
    //   150	683	6	localObject2	Object
    //   847	3	6	localIOException5	java.io.IOException
    //   857	3	6	localIOException6	java.io.IOException
    //   866	135	6	localIOException7	java.io.IOException
    //   181	813	7	localObject3	Object
    //   157	48	8	arrayOfByte	byte[]
    //   215	23	8	localException1	Exception
    //   313	518	8	localObject4	Object
    //   970	1	8	localException2	Exception
    //   981	1	8	localException3	Exception
    //   989	1	8	localException4	Exception
    //   177	815	9	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   299	264	10	localObject5	Object
    //   9	119	11	localFile	java.io.File
    //   28	110	12	arrayOfString1	String[]
    //   114	640	13	str	String
    //   341	439	14	arrayOfString2	String[]
    // Exception table:
    //   from	to	target	type
    //   183	192	215	java/lang/Exception
    //   202	212	215	java/lang/Exception
    //   294	301	215	java/lang/Exception
    //   305	311	215	java/lang/Exception
    //   319	343	215	java/lang/Exception
    //   347	374	215	java/lang/Exception
    //   378	390	215	java/lang/Exception
    //   394	406	215	java/lang/Exception
    //   410	423	215	java/lang/Exception
    //   430	437	215	java/lang/Exception
    //   460	470	215	java/lang/Exception
    //   477	500	215	java/lang/Exception
    //   513	522	215	java/lang/Exception
    //   526	549	215	java/lang/Exception
    //   561	570	215	java/lang/Exception
    //   574	583	215	java/lang/Exception
    //   620	625	215	java/lang/Exception
    //   632	645	215	java/lang/Exception
    //   699	706	215	java/lang/Exception
    //   714	725	215	java/lang/Exception
    //   738	747	215	java/lang/Exception
    //   751	761	215	java/lang/Exception
    //   765	792	215	java/lang/Exception
    //   183	192	648	finally
    //   202	212	648	finally
    //   294	301	648	finally
    //   305	311	648	finally
    //   319	343	648	finally
    //   347	374	648	finally
    //   378	390	648	finally
    //   394	406	648	finally
    //   410	423	648	finally
    //   430	437	648	finally
    //   460	470	648	finally
    //   477	500	648	finally
    //   513	522	648	finally
    //   526	549	648	finally
    //   561	570	648	finally
    //   574	583	648	finally
    //   583	593	648	finally
    //   620	625	648	finally
    //   632	645	648	finally
    //   699	706	648	finally
    //   714	725	648	finally
    //   738	747	648	finally
    //   751	761	648	finally
    //   765	792	648	finally
    //   598	603	675	java/io/IOException
    //   608	613	683	java/io/IOException
    //   811	816	823	java/io/IOException
    //   797	802	837	java/io/IOException
    //   256	261	847	java/io/IOException
    //   270	275	857	java/io/IOException
    //   658	663	871	java/io/IOException
    //   668	673	881	java/io/IOException
    //   225	251	949	finally
    //   97	170	953	finally
    //   170	179	963	finally
    //   97	170	970	java/lang/Exception
    //   170	179	981	java/lang/Exception
    //   583	593	989	java/lang/Exception
  }
  
  private void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(g.f.getPackageName());
    localStringBuilder.append("#");
    localStringBuilder.append(paramString4);
    localStringBuilder.append("#");
    localStringBuilder.append(paramString5);
    localStringBuilder.append("#");
    localStringBuilder.append("-1");
    b("30025", localStringBuilder.toString(), paramString1, paramString2, paramString3);
    com.igexin.b.a.c.b.a("feedback actionId=30025 result=" + localStringBuilder.toString());
  }
  
  private void a(String paramString, boolean paramBoolean, PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    String str1;
    String str2;
    String str3;
    for (;;)
    {
      String str4;
      try
      {
        str4 = a(paramString);
        str1 = paramPushTaskBean.getMessageId();
        str2 = paramPushTaskBean.getTaskId();
        str3 = ((q)paramBaseAction).a();
        b(paramString);
        if (str4 == null) {
          break;
        }
        paramBaseAction = new HashMap();
        paramBaseAction.put("messageId", str1);
        paramBaseAction.put("taskId", str2);
        paramBaseAction.put("id", str3);
        paramBaseAction.put("pkgName", paramString);
        paramPushTaskBean = new StringBuffer();
        paramPushTaskBean.append(g.f.getPackageName());
        paramPushTaskBean.append("#");
        paramPushTaskBean.append(d(paramString));
        paramPushTaskBean.append("#");
        paramPushTaskBean.append(paramString);
        paramPushTaskBean.append("/");
        if (!str4.equals(b)) {
          break label412;
        }
        paramPushTaskBean.append(b);
        paramPushTaskBean.append("#");
        if (!a(paramString, b)) {
          continue;
        }
        paramPushTaskBean.append("0");
      }
      catch (Throwable paramString)
      {
        try
        {
          Intent localIntent = new Intent();
          localIntent.setClassName(paramString, str4);
          localIntent.putExtra("action", "com.igexin.action.initialize.slave");
          localIntent.putExtra("op_app", g.e);
          localIntent.putExtra("isSlave", true);
          g.f.startService(localIntent);
          paramBaseAction.put("serviceName", b);
          a(paramBaseAction);
          paramPushTaskBean.append("1");
        }
        catch (Exception paramString)
        {
          com.igexin.b.a.c.b.a(paramString.toString());
          a(paramPushTaskBean, str1, str2, str3);
          return;
        }
        paramString = paramString;
        com.igexin.b.a.c.b.a("WakeupAction|" + paramString.toString());
        return;
      }
      b("30025", paramPushTaskBean.toString(), str1, str2, str3);
      com.igexin.b.a.c.b.a("feedback actionId=30025 result=" + paramPushTaskBean.toString());
      return;
      if ((!paramBoolean) || (!b(paramString, str4)))
      {
        a(paramPushTaskBean, str1, str2, str3);
        return;
        label412:
        if (str4.equals(d))
        {
          paramPushTaskBean.append(d);
          paramPushTaskBean.append("#");
          if (a(paramString, d))
          {
            paramPushTaskBean.append("0");
          }
          else
          {
            if (!b(paramString, str4))
            {
              a(paramPushTaskBean, str1, str2, str3);
              return;
            }
            paramBaseAction.put("serviceName", d);
            a(paramBaseAction);
            paramPushTaskBean.append("1");
          }
        }
        else if (str4.equals(c))
        {
          paramPushTaskBean.append(c);
          paramPushTaskBean.append("#");
          if (a(paramString, c))
          {
            paramPushTaskBean.append("0");
          }
          else
          {
            if (!b(paramString, str4))
            {
              a(paramPushTaskBean, str1, str2, str3);
              return;
            }
            paramBaseAction.put("serviceName", c);
            a(paramBaseAction);
            paramPushTaskBean.append("1");
          }
        }
      }
    }
    if (((q)paramBaseAction).d() != null) {}
    for (paramString = ((q)paramBaseAction).d();; paramString = "")
    {
      if (((q)paramBaseAction).c() != null) {}
      for (paramPushTaskBean = ((q)paramBaseAction).c();; paramPushTaskBean = "")
      {
        a(str1, str2, str3, paramString, paramPushTaskBean);
        return;
      }
    }
  }
  
  private void a(StringBuffer paramStringBuffer, String paramString1, String paramString2, String paramString3)
  {
    paramStringBuffer.append("-1");
    b("30025", paramStringBuffer.toString(), paramString1, paramString2, paramString3);
    com.igexin.b.a.c.b.a("feedback actionId=30025 result=" + paramStringBuffer.toString());
  }
  
  private void a(Map<String, String> paramMap)
  {
    com.igexin.push.core.f.a().a(new o(this, 180000L, paramMap));
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    List localList = ((ActivityManager)g.f.getSystemService("activity")).getRunningServices(2000);
    if (localList.size() <= 0) {}
    for (;;)
    {
      return false;
      int i = 0;
      while (i < localList.size())
      {
        if ((((ActivityManager.RunningServiceInfo)localList.get(i)).service.getClassName().equals(paramString2)) && (((ActivityManager.RunningServiceInfo)localList.get(i)).service.getPackageName().equals(paramString1))) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  private void b(String paramString)
  {
    if (c(paramString)) {}
    try
    {
      paramString = g.f.getContentResolver().query(Uri.parse("content://downloads." + paramString + "/download"), null, null, null, null);
      if (paramString != null) {
        paramString.close();
      }
      return;
    }
    catch (Exception paramString)
    {
      com.igexin.b.a.c.b.a(paramString.toString());
    }
  }
  
  private void b(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    PushTaskBean localPushTaskBean = new PushTaskBean();
    localPushTaskBean.setAppid(g.a);
    localPushTaskBean.setMessageId(paramString3);
    localPushTaskBean.setTaskId(paramString4);
    localPushTaskBean.setId(paramString5);
    localPushTaskBean.setAppKey(g.b);
    com.igexin.push.core.a.f.a().a(localPushTaskBean, paramString1, paramString2);
  }
  
  private boolean b(String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClassName(paramString1, paramString2);
      g.f.startService(localIntent);
      return true;
    }
    catch (Exception paramString1)
    {
      com.igexin.b.a.c.b.a("WakeupAction|" + paramString1.toString());
    }
    return false;
  }
  
  private boolean c(String paramString)
  {
    try
    {
      this.a = g.f.getPackageManager();
      Object localObject1 = this.a.getPackageInfo(paramString, 8);
      if (localObject1 == null) {
        return false;
      }
      localObject1 = ((PackageInfo)localObject1).providers;
      if ((localObject1 != null) && (localObject1.length != 0))
      {
        int j = localObject1.length;
        int i = 0;
        while (i < j)
        {
          Object localObject2 = localObject1[i];
          if (localObject2.name.equals("com.igexin.download.DownloadProvider"))
          {
            boolean bool = localObject2.authority.equals("downloads." + paramString);
            if (bool) {
              return true;
            }
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Exception paramString) {}
  }
  
  private String d(String paramString)
  {
    try
    {
      this.a = g.f.getPackageManager();
      paramString = this.a.getApplicationInfo(paramString, 128).metaData;
      if (paramString != null)
      {
        Iterator localIterator = paramString.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          if (str.equals("PUSH_APPID"))
          {
            paramString = paramString.get(str).toString();
            return paramString;
          }
        }
      }
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public com.igexin.push.core.b a(PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    return com.igexin.push.core.b.a;
  }
  
  public BaseAction a(JSONObject paramJSONObject)
  {
    q localQ;
    for (;;)
    {
      try
      {
        if ((m.q) && (paramJSONObject.has("do")) && (paramJSONObject.has("actionid")) && (paramJSONObject.has("type")) && ((paramJSONObject.has("pkgname")) || (paramJSONObject.has("appid")) || (paramJSONObject.has("cid"))))
        {
          localQ = new q();
          localQ.setType("wakeupsdk");
          localQ.setActionId(paramJSONObject.getString("actionid"));
          localQ.setDoActionId(paramJSONObject.getString("do"));
          if (paramJSONObject.has("pkgname"))
          {
            localQ.b(paramJSONObject.getString("pkgname"));
            if (paramJSONObject.has("is_forcestart")) {
              localQ.a(paramJSONObject.getBoolean("is_forcestart"));
            }
            if (!paramJSONObject.has("id")) {
              break;
            }
            localQ.a(paramJSONObject.getString("id"));
            return localQ;
          }
          if (paramJSONObject.has("cid"))
          {
            localQ.d(paramJSONObject.getString("cid"));
            continue;
          }
        }
        else
        {
          return null;
        }
      }
      catch (JSONException paramJSONObject)
      {
        com.igexin.b.a.c.b.a(paramJSONObject.toString());
      }
      if (paramJSONObject.has("appid")) {
        localQ.c(paramJSONObject.getString("appid"));
      }
    }
    return localQ;
  }
  
  public boolean b(PushTaskBean paramPushTaskBean, BaseAction paramBaseAction)
  {
    Object localObject2;
    Object localObject1;
    Object localObject3;
    int i;
    if ((paramPushTaskBean != null) && (paramBaseAction != null))
    {
      localObject2 = (q)paramBaseAction;
      localObject1 = ((q)localObject2).c();
      if ((localObject1 != null) || (((q)localObject2).e() == null)) {
        break label316;
      }
      localObject3 = a(0, ((q)localObject2).e());
      if ((localObject3 == null) || (((List)localObject3).size() != 1)) {
        break label206;
      }
      localObject1 = (String)((List)localObject3).get(0);
      i = 1;
    }
    for (;;)
    {
      if (localObject1 != null) {
        a((String)localObject1, ((q)localObject2).b(), paramPushTaskBean, paramBaseAction);
      }
      label95:
      label139:
      label206:
      label297:
      label305:
      label313:
      for (;;)
      {
        String str1;
        String str2;
        if (i == 0)
        {
          localObject3 = paramPushTaskBean.getMessageId();
          str1 = paramPushTaskBean.getTaskId();
          str2 = ((q)paramBaseAction).a();
          if (((q)paramBaseAction).d() == null) {
            break label297;
          }
          localObject1 = ((q)paramBaseAction).d();
          if (((q)paramBaseAction).c() == null) {
            break label305;
          }
        }
        for (localObject2 = ((q)paramBaseAction).c();; localObject2 = "")
        {
          a((String)localObject3, str1, str2, (String)localObject1, (String)localObject2);
          if (!paramBaseAction.getDoActionId().equals("")) {
            com.igexin.push.core.a.f.a().a(paramPushTaskBean.getTaskId(), paramPushTaskBean.getMessageId(), paramBaseAction.getDoActionId());
          }
          return true;
          i = 0;
          break;
          if (((q)localObject2).d() == null) {
            break label313;
          }
          localObject1 = a(1, ((q)localObject2).d());
          if ((localObject1 != null) && (((List)localObject1).size() > 0))
          {
            localObject1 = ((List)localObject1).iterator();
            while (((Iterator)localObject1).hasNext()) {
              a((String)((Iterator)localObject1).next(), ((q)localObject2).b(), paramPushTaskBean, paramBaseAction);
            }
            break label95;
          }
          i = 0;
          break label95;
          localObject1 = "";
          break label139;
        }
      }
      label316:
      i = 1;
    }
  }
}
