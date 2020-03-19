package com.bandwidthx.library;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aw
{
  private static bf.b S;
  public static final int d = 0;
  public static final int e = 1;
  public static final int f = 2;
  public static final int g = 3;
  public static final int h = 4;
  public static final int i = 1;
  public static final int j = 2;
  public static final int k = 4;
  public static final int l = 8;
  public static final int m = 16;
  public static final int n = 32;
  public static final int o = 64;
  public static final int p = 128;
  public static final int q = 256;
  public static final int r = 512;
  public static final int s = 1024;
  public static final int t = 2048;
  public static final int u = 4096;
  private static ch v;
  private Long A = Long.valueOf(0L);
  private Long B = Long.valueOf(0L);
  private Boolean C = Boolean.valueOf(false);
  private Boolean D = Boolean.valueOf(false);
  private Boolean E = Boolean.valueOf(false);
  private Boolean F = Boolean.valueOf(false);
  private Boolean G = Boolean.valueOf(false);
  private Boolean H = Boolean.valueOf(false);
  private Boolean I = Boolean.valueOf(false);
  private Boolean J = Boolean.valueOf(false);
  private Boolean K = Boolean.valueOf(false);
  private Boolean L = Boolean.valueOf(false);
  private Boolean M = Boolean.valueOf(false);
  private Boolean N = Boolean.valueOf(false);
  private Boolean O = Boolean.valueOf(false);
  private Boolean P = Boolean.valueOf(true);
  private Boolean Q = Boolean.valueOf(true);
  private Long R = Long.valueOf(0L);
  protected Long a = Long.valueOf(0L);
  protected Long b = Long.valueOf(10000L);
  protected Long c = Long.valueOf(10000L);
  private String w = "";
  private Long x = Long.valueOf(0L);
  private Integer y = Integer.valueOf(0);
  private String z = "";
  
  public aw(ch paramCh)
  {
    try
    {
      v = paramCh;
      F();
      return;
    }
    catch (Exception paramCh)
    {
      cg.a(paramCh);
    }
  }
  
  private void F()
  {
    if (S == null)
    {
      S = new bf.b()
      {
        public void onAppResumed()
        {
          try
          {
            if (aw.E() != null)
            {
              aw.E().q().c();
              return;
            }
          }
          catch (Throwable localThrowable)
          {
            cg.a(localThrowable);
          }
        }
      };
      v.y();
      bf.a(S);
    }
  }
  
  /* Error */
  private Boolean a(Long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 145	com/bandwidthx/library/aw:P	Ljava/lang/Boolean;
    //   4: astore 5
    //   6: aload_0
    //   7: getfield 145	com/bandwidthx/library/aw:P	Ljava/lang/Boolean;
    //   10: astore_3
    //   11: aload_3
    //   12: astore_2
    //   13: aload_0
    //   14: getfield 147	com/bandwidthx/library/aw:a	Ljava/lang/Long;
    //   17: invokevirtual 187	java/lang/Long:longValue	()J
    //   20: aload_1
    //   21: invokevirtual 187	java/lang/Long:longValue	()J
    //   24: aload_0
    //   25: getfield 151	com/bandwidthx/library/aw:b	Ljava/lang/Long;
    //   28: invokevirtual 187	java/lang/Long:longValue	()J
    //   31: lsub
    //   32: lcmp
    //   33: ifge +2250 -> 2283
    //   36: iconst_1
    //   37: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   40: astore 4
    //   42: aload 4
    //   44: astore_2
    //   45: aload_0
    //   46: ldc2_w 188
    //   49: invokestatic 97	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   52: putfield 147	com/bandwidthx/library/aw:a	Ljava/lang/Long;
    //   55: aload 4
    //   57: astore_2
    //   58: aload_1
    //   59: invokevirtual 187	java/lang/Long:longValue	()J
    //   62: ldc2_w 190
    //   65: ldiv
    //   66: ldc2_w 190
    //   69: lmul
    //   70: invokestatic 97	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   73: astore 6
    //   75: aload 4
    //   77: astore_3
    //   78: aload 4
    //   80: astore_2
    //   81: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   84: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   87: bipush 62
    //   89: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   92: invokevirtual 199	com/bandwidthx/library/eg:a	(Ljava/lang/Integer;)Ljava/lang/Boolean;
    //   95: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   98: ifeq +1082 -> 1180
    //   101: aload 4
    //   103: astore_2
    //   104: iconst_0
    //   105: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   108: astore 4
    //   110: aload 4
    //   112: astore_3
    //   113: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   116: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   119: bipush 63
    //   121: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   124: invokevirtual 199	com/bandwidthx/library/eg:a	(Ljava/lang/Integer;)Ljava/lang/Boolean;
    //   127: astore_2
    //   128: aload 4
    //   130: astore_3
    //   131: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   134: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   137: bipush 64
    //   139: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   142: invokevirtual 206	com/bandwidthx/library/eg:b	(Ljava/lang/Integer;)Ljava/lang/Integer;
    //   145: astore 7
    //   147: aload 4
    //   149: astore_3
    //   150: new 208	java/lang/StringBuilder
    //   153: dup
    //   154: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   157: astore 8
    //   159: aload 4
    //   161: astore_3
    //   162: aload 8
    //   164: ldc -45
    //   166: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload 4
    //   172: astore_3
    //   173: aload 8
    //   175: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   178: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   181: bipush 65
    //   183: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   186: invokevirtual 218	com/bandwidthx/library/eg:d	(Ljava/lang/Integer;)Ljava/lang/String;
    //   189: invokevirtual 224	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   192: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: pop
    //   196: aload 4
    //   198: astore_3
    //   199: aload 8
    //   201: ldc -45
    //   203: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: pop
    //   207: aload 4
    //   209: astore_3
    //   210: aload 8
    //   212: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: astore 8
    //   217: aload 4
    //   219: astore_3
    //   220: aload_2
    //   221: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   224: ifeq +54 -> 278
    //   227: aload 4
    //   229: astore_3
    //   230: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   233: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   236: invokevirtual 235	com/bandwidthx/library/do:t	()Ljava/lang/Boolean;
    //   239: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   242: ifne +23 -> 265
    //   245: aload 4
    //   247: astore_3
    //   248: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   251: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   254: invokevirtual 238	com/bandwidthx/library/do:s	()Ljava/lang/Long;
    //   257: invokevirtual 187	java/lang/Long:longValue	()J
    //   260: lconst_0
    //   261: lcmp
    //   262: ifne +16 -> 278
    //   265: aload 4
    //   267: astore_3
    //   268: iconst_1
    //   269: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   272: astore_2
    //   273: aload_2
    //   274: astore_3
    //   275: goto +144 -> 419
    //   278: aload 4
    //   280: astore_3
    //   281: aload 7
    //   283: invokevirtual 242	java/lang/Integer:intValue	()I
    //   286: ifge +39 -> 325
    //   289: aload 4
    //   291: astore_3
    //   292: aload 7
    //   294: invokevirtual 242	java/lang/Integer:intValue	()I
    //   297: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   300: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   303: invokevirtual 245	com/bandwidthx/library/do:x	()Ljava/lang/Integer;
    //   306: invokevirtual 242	java/lang/Integer:intValue	()I
    //   309: if_icmpgt +16 -> 325
    //   312: aload 4
    //   314: astore_3
    //   315: iconst_1
    //   316: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   319: astore_2
    //   320: aload_2
    //   321: astore_3
    //   322: goto +97 -> 419
    //   325: aload 4
    //   327: astore_3
    //   328: aload 8
    //   330: invokevirtual 248	java/lang/String:length	()I
    //   333: iconst_2
    //   334: if_icmple +82 -> 416
    //   337: aload 4
    //   339: astore_3
    //   340: new 208	java/lang/StringBuilder
    //   343: dup
    //   344: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   347: astore_2
    //   348: aload 4
    //   350: astore_3
    //   351: aload_2
    //   352: ldc -45
    //   354: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: pop
    //   358: aload 4
    //   360: astore_3
    //   361: aload_2
    //   362: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   365: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   368: invokevirtual 250	com/bandwidthx/library/do:r	()Ljava/lang/String;
    //   371: invokevirtual 224	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   374: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: pop
    //   378: aload 4
    //   380: astore_3
    //   381: aload_2
    //   382: ldc -45
    //   384: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: pop
    //   388: aload 4
    //   390: astore_3
    //   391: aload 8
    //   393: aload_2
    //   394: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   397: invokevirtual 254	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   400: ifeq +16 -> 416
    //   403: aload 4
    //   405: astore_3
    //   406: iconst_1
    //   407: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   410: astore_2
    //   411: aload_2
    //   412: astore_3
    //   413: goto +6 -> 419
    //   416: aload 4
    //   418: astore_3
    //   419: aload_3
    //   420: astore 4
    //   422: aload_3
    //   423: astore_2
    //   424: aload_3
    //   425: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   428: ifne +348 -> 776
    //   431: aload_3
    //   432: astore_2
    //   433: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   436: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   439: invokevirtual 256	com/bandwidthx/library/do:u	()Ljava/lang/String;
    //   442: astore 7
    //   444: aload_3
    //   445: astore 4
    //   447: aload_3
    //   448: astore_2
    //   449: aload 7
    //   451: invokevirtual 248	java/lang/String:length	()I
    //   454: ifle +322 -> 776
    //   457: aload_3
    //   458: astore_2
    //   459: aload 7
    //   461: ldc_w 258
    //   464: invokevirtual 262	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   467: astore 4
    //   469: aload 4
    //   471: iconst_0
    //   472: aaload
    //   473: astore 7
    //   475: aload 4
    //   477: iconst_1
    //   478: aaload
    //   479: astore 8
    //   481: aload_3
    //   482: astore_2
    //   483: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   486: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   489: bipush 66
    //   491: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   494: invokevirtual 265	com/bandwidthx/library/eg:f	(Ljava/lang/Integer;)Ljava/util/ArrayList;
    //   497: invokevirtual 271	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   500: astore 9
    //   502: aload_3
    //   503: astore 4
    //   505: aload_3
    //   506: astore_2
    //   507: aload 9
    //   509: invokeinterface 276 1 0
    //   514: ifeq +262 -> 776
    //   517: aload_3
    //   518: astore_2
    //   519: aload 9
    //   521: invokeinterface 280 1 0
    //   526: checkcast 10	com/bandwidthx/library/aw$a
    //   529: astore 4
    //   531: aload_3
    //   532: astore_2
    //   533: aload_1
    //   534: invokevirtual 187	java/lang/Long:longValue	()J
    //   537: aload 6
    //   539: invokevirtual 187	java/lang/Long:longValue	()J
    //   542: aload 4
    //   544: getfield 281	com/bandwidthx/library/aw$a:b	Ljava/lang/Long;
    //   547: invokevirtual 187	java/lang/Long:longValue	()J
    //   550: ladd
    //   551: lcmp
    //   552: iflt +1791 -> 2343
    //   555: aload_3
    //   556: astore_2
    //   557: aload_1
    //   558: invokevirtual 187	java/lang/Long:longValue	()J
    //   561: aload 6
    //   563: invokevirtual 187	java/lang/Long:longValue	()J
    //   566: aload 4
    //   568: getfield 282	com/bandwidthx/library/aw$a:c	Ljava/lang/Long;
    //   571: invokevirtual 187	java/lang/Long:longValue	()J
    //   574: ladd
    //   575: lcmp
    //   576: ifgt +1767 -> 2343
    //   579: aload_3
    //   580: astore_2
    //   581: new 208	java/lang/StringBuilder
    //   584: dup
    //   585: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   588: astore 10
    //   590: aload_3
    //   591: astore_2
    //   592: aload 10
    //   594: ldc -45
    //   596: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   599: pop
    //   600: aload_3
    //   601: astore_2
    //   602: aload 10
    //   604: aload 4
    //   606: getfield 284	com/bandwidthx/library/aw$a:a	Ljava/lang/String;
    //   609: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   612: pop
    //   613: aload_3
    //   614: astore_2
    //   615: aload 10
    //   617: ldc -45
    //   619: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   622: pop
    //   623: aload_3
    //   624: astore_2
    //   625: aload 10
    //   627: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   630: astore 4
    //   632: aload_3
    //   633: astore_2
    //   634: new 208	java/lang/StringBuilder
    //   637: dup
    //   638: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   641: astore 10
    //   643: aload_3
    //   644: astore_2
    //   645: aload 10
    //   647: ldc -45
    //   649: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   652: pop
    //   653: aload_3
    //   654: astore_2
    //   655: aload 10
    //   657: aload 7
    //   659: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   662: pop
    //   663: aload_3
    //   664: astore_2
    //   665: aload 10
    //   667: ldc -45
    //   669: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   672: pop
    //   673: aload_3
    //   674: astore_2
    //   675: aload 4
    //   677: aload 10
    //   679: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   682: invokevirtual 254	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   685: ifne +80 -> 765
    //   688: aload_3
    //   689: astore_2
    //   690: new 208	java/lang/StringBuilder
    //   693: dup
    //   694: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   697: astore 10
    //   699: aload_3
    //   700: astore_2
    //   701: aload 10
    //   703: ldc -45
    //   705: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   708: pop
    //   709: aload_3
    //   710: astore_2
    //   711: aload 10
    //   713: aload 7
    //   715: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   718: pop
    //   719: aload_3
    //   720: astore_2
    //   721: aload 10
    //   723: ldc_w 258
    //   726: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   729: pop
    //   730: aload_3
    //   731: astore_2
    //   732: aload 10
    //   734: aload 8
    //   736: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   739: pop
    //   740: aload_3
    //   741: astore_2
    //   742: aload 10
    //   744: ldc -45
    //   746: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: pop
    //   750: aload_3
    //   751: astore_2
    //   752: aload 4
    //   754: aload 10
    //   756: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   759: invokevirtual 254	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   762: ifeq +1581 -> 2343
    //   765: aload_3
    //   766: astore_2
    //   767: iconst_1
    //   768: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   771: astore 4
    //   773: goto +3 -> 776
    //   776: aload 4
    //   778: astore_3
    //   779: aload 4
    //   781: astore_2
    //   782: aload 4
    //   784: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   787: ifne +393 -> 1180
    //   790: aload 4
    //   792: astore_2
    //   793: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   796: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   799: bipush 67
    //   801: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   804: invokevirtual 286	com/bandwidthx/library/eg:g	(Ljava/lang/Integer;)Ljava/util/ArrayList;
    //   807: invokevirtual 271	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   810: astore 7
    //   812: aload 4
    //   814: astore_3
    //   815: aload 4
    //   817: astore_2
    //   818: aload 7
    //   820: invokeinterface 276 1 0
    //   825: ifeq +355 -> 1180
    //   828: aload 4
    //   830: astore_2
    //   831: aload 7
    //   833: invokeinterface 280 1 0
    //   838: checkcast 13	com/bandwidthx/library/aw$b
    //   841: astore_3
    //   842: aload 4
    //   844: astore_2
    //   845: aload_1
    //   846: invokevirtual 187	java/lang/Long:longValue	()J
    //   849: aload 6
    //   851: invokevirtual 187	java/lang/Long:longValue	()J
    //   854: aload_3
    //   855: getfield 288	com/bandwidthx/library/aw$b:i	Ljava/lang/Long;
    //   858: invokevirtual 187	java/lang/Long:longValue	()J
    //   861: ladd
    //   862: lcmp
    //   863: iflt -51 -> 812
    //   866: aload 4
    //   868: astore_2
    //   869: aload_1
    //   870: invokevirtual 187	java/lang/Long:longValue	()J
    //   873: aload 6
    //   875: invokevirtual 187	java/lang/Long:longValue	()J
    //   878: aload_3
    //   879: getfield 290	com/bandwidthx/library/aw$b:j	Ljava/lang/Long;
    //   882: invokevirtual 187	java/lang/Long:longValue	()J
    //   885: ladd
    //   886: lcmp
    //   887: ifgt -75 -> 812
    //   890: aload 4
    //   892: astore_2
    //   893: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   896: invokevirtual 293	com/bandwidthx/library/ch:B	()Lcom/bandwidthx/library/bq;
    //   899: invokevirtual 298	com/bandwidthx/library/bq:l	()Ljava/lang/Double;
    //   902: astore 8
    //   904: aload 4
    //   906: astore_2
    //   907: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   910: invokevirtual 293	com/bandwidthx/library/ch:B	()Lcom/bandwidthx/library/bq;
    //   913: invokevirtual 300	com/bandwidthx/library/bq:m	()Ljava/lang/Double;
    //   916: astore 9
    //   918: aload 4
    //   920: astore_2
    //   921: aload_3
    //   922: getfield 301	com/bandwidthx/library/aw$b:a	Ljava/lang/String;
    //   925: invokevirtual 248	java/lang/String:length	()I
    //   928: ifle +163 -> 1091
    //   931: aload 4
    //   933: astore_2
    //   934: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   937: invokevirtual 293	com/bandwidthx/library/ch:B	()Lcom/bandwidthx/library/bq;
    //   940: aload 8
    //   942: aload 9
    //   944: invokevirtual 304	com/bandwidthx/library/bq:c	(Ljava/lang/Double;Ljava/lang/Double;)Lcom/bandwidthx/library/bq$f;
    //   947: astore 8
    //   949: aload 8
    //   951: ifnull -139 -> 812
    //   954: aload 4
    //   956: astore_2
    //   957: aload_3
    //   958: getfield 301	com/bandwidthx/library/aw$b:a	Ljava/lang/String;
    //   961: invokevirtual 248	java/lang/String:length	()I
    //   964: ifeq +21 -> 985
    //   967: aload 4
    //   969: astore_2
    //   970: aload_3
    //   971: getfield 301	com/bandwidthx/library/aw$b:a	Ljava/lang/String;
    //   974: aload 8
    //   976: getfield 307	com/bandwidthx/library/bq$f:a	Ljava/lang/String;
    //   979: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   982: ifeq -170 -> 812
    //   985: aload 4
    //   987: astore_2
    //   988: aload_3
    //   989: getfield 313	com/bandwidthx/library/aw$b:b	Ljava/lang/String;
    //   992: invokevirtual 248	java/lang/String:length	()I
    //   995: ifeq +21 -> 1016
    //   998: aload 4
    //   1000: astore_2
    //   1001: aload_3
    //   1002: getfield 313	com/bandwidthx/library/aw$b:b	Ljava/lang/String;
    //   1005: aload 8
    //   1007: getfield 314	com/bandwidthx/library/bq$f:b	Ljava/lang/String;
    //   1010: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1013: ifeq -201 -> 812
    //   1016: aload 4
    //   1018: astore_2
    //   1019: aload_3
    //   1020: getfield 316	com/bandwidthx/library/aw$b:c	Ljava/lang/String;
    //   1023: invokevirtual 248	java/lang/String:length	()I
    //   1026: ifeq +21 -> 1047
    //   1029: aload 4
    //   1031: astore_2
    //   1032: aload_3
    //   1033: getfield 316	com/bandwidthx/library/aw$b:c	Ljava/lang/String;
    //   1036: aload 8
    //   1038: getfield 317	com/bandwidthx/library/bq$f:c	Ljava/lang/String;
    //   1041: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1044: ifeq -232 -> 812
    //   1047: aload 4
    //   1049: astore_2
    //   1050: aload_3
    //   1051: getfield 319	com/bandwidthx/library/aw$b:d	Ljava/lang/String;
    //   1054: invokevirtual 248	java/lang/String:length	()I
    //   1057: ifeq +21 -> 1078
    //   1060: aload 4
    //   1062: astore_2
    //   1063: aload_3
    //   1064: getfield 319	com/bandwidthx/library/aw$b:d	Ljava/lang/String;
    //   1067: aload 8
    //   1069: getfield 320	com/bandwidthx/library/bq$f:d	Ljava/lang/String;
    //   1072: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1075: ifeq -263 -> 812
    //   1078: aload 4
    //   1080: astore_2
    //   1081: iconst_1
    //   1082: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1085: astore_3
    //   1086: aload_3
    //   1087: astore_2
    //   1088: goto +1258 -> 2346
    //   1091: aload 4
    //   1093: astore_2
    //   1094: aload 8
    //   1096: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   1099: aload_3
    //   1100: getfield 329	com/bandwidthx/library/aw$b:f	Ljava/lang/Double;
    //   1103: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   1106: dcmpl
    //   1107: iflt -295 -> 812
    //   1110: aload 4
    //   1112: astore_2
    //   1113: aload 8
    //   1115: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   1118: aload_3
    //   1119: getfield 331	com/bandwidthx/library/aw$b:h	Ljava/lang/Double;
    //   1122: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   1125: dcmpg
    //   1126: ifgt -314 -> 812
    //   1129: aload 4
    //   1131: astore_2
    //   1132: aload 9
    //   1134: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   1137: aload_3
    //   1138: getfield 333	com/bandwidthx/library/aw$b:e	Ljava/lang/Double;
    //   1141: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   1144: dcmpl
    //   1145: iflt -333 -> 812
    //   1148: aload 4
    //   1150: astore_2
    //   1151: aload 9
    //   1153: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   1156: aload_3
    //   1157: getfield 335	com/bandwidthx/library/aw$b:g	Ljava/lang/Double;
    //   1160: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   1163: dcmpg
    //   1164: ifgt -352 -> 812
    //   1167: aload 4
    //   1169: astore_2
    //   1170: iconst_1
    //   1171: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1174: astore_3
    //   1175: aload_3
    //   1176: astore_2
    //   1177: goto +1169 -> 2346
    //   1180: aload_3
    //   1181: astore 4
    //   1183: aload_3
    //   1184: astore_2
    //   1185: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1188: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   1191: bipush 68
    //   1193: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1196: invokevirtual 199	com/bandwidthx/library/eg:a	(Ljava/lang/Integer;)Ljava/lang/Boolean;
    //   1199: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   1202: ifeq +1039 -> 2241
    //   1205: aload_3
    //   1206: astore_2
    //   1207: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1210: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   1213: bipush 69
    //   1215: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1218: invokevirtual 199	com/bandwidthx/library/eg:a	(Ljava/lang/Integer;)Ljava/lang/Boolean;
    //   1221: astore 4
    //   1223: aload_3
    //   1224: astore_2
    //   1225: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1228: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   1231: bipush 70
    //   1233: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1236: invokevirtual 206	com/bandwidthx/library/eg:b	(Ljava/lang/Integer;)Ljava/lang/Integer;
    //   1239: astore 7
    //   1241: aload_3
    //   1242: astore_2
    //   1243: new 208	java/lang/StringBuilder
    //   1246: dup
    //   1247: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   1250: astore 8
    //   1252: aload_3
    //   1253: astore_2
    //   1254: aload 8
    //   1256: ldc -45
    //   1258: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1261: pop
    //   1262: aload_3
    //   1263: astore_2
    //   1264: aload 8
    //   1266: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1269: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   1272: bipush 71
    //   1274: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1277: invokevirtual 218	com/bandwidthx/library/eg:d	(Ljava/lang/Integer;)Ljava/lang/String;
    //   1280: invokevirtual 224	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1283: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1286: pop
    //   1287: aload_3
    //   1288: astore_2
    //   1289: aload 8
    //   1291: ldc -45
    //   1293: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1296: pop
    //   1297: aload_3
    //   1298: astore_2
    //   1299: aload 8
    //   1301: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1304: astore 8
    //   1306: aload_3
    //   1307: astore_2
    //   1308: aload 4
    //   1310: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   1313: ifeq +51 -> 1364
    //   1316: aload_3
    //   1317: astore_2
    //   1318: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1321: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   1324: invokevirtual 235	com/bandwidthx/library/do:t	()Ljava/lang/Boolean;
    //   1327: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   1330: ifne +22 -> 1352
    //   1333: aload_3
    //   1334: astore_2
    //   1335: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1338: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   1341: invokevirtual 238	com/bandwidthx/library/do:s	()Ljava/lang/Long;
    //   1344: invokevirtual 187	java/lang/Long:longValue	()J
    //   1347: lconst_0
    //   1348: lcmp
    //   1349: ifne +15 -> 1364
    //   1352: aload_3
    //   1353: astore_2
    //   1354: iconst_0
    //   1355: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1358: astore_3
    //   1359: aload_3
    //   1360: astore_2
    //   1361: goto +138 -> 1499
    //   1364: aload_3
    //   1365: astore_2
    //   1366: aload 7
    //   1368: invokevirtual 242	java/lang/Integer:intValue	()I
    //   1371: ifge +37 -> 1408
    //   1374: aload_3
    //   1375: astore_2
    //   1376: aload 7
    //   1378: invokevirtual 242	java/lang/Integer:intValue	()I
    //   1381: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1384: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   1387: invokevirtual 245	com/bandwidthx/library/do:x	()Ljava/lang/Integer;
    //   1390: invokevirtual 242	java/lang/Integer:intValue	()I
    //   1393: if_icmpgt +15 -> 1408
    //   1396: aload_3
    //   1397: astore_2
    //   1398: iconst_0
    //   1399: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1402: astore_3
    //   1403: aload_3
    //   1404: astore_2
    //   1405: goto +94 -> 1499
    //   1408: aload_3
    //   1409: astore_2
    //   1410: aload 8
    //   1412: invokevirtual 248	java/lang/String:length	()I
    //   1415: iconst_2
    //   1416: if_icmple +81 -> 1497
    //   1419: aload_3
    //   1420: astore_2
    //   1421: new 208	java/lang/StringBuilder
    //   1424: dup
    //   1425: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   1428: astore 4
    //   1430: aload_3
    //   1431: astore_2
    //   1432: aload 4
    //   1434: ldc -45
    //   1436: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1439: pop
    //   1440: aload_3
    //   1441: astore_2
    //   1442: aload 4
    //   1444: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1447: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   1450: invokevirtual 250	com/bandwidthx/library/do:r	()Ljava/lang/String;
    //   1453: invokevirtual 224	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1456: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1459: pop
    //   1460: aload_3
    //   1461: astore_2
    //   1462: aload 4
    //   1464: ldc -45
    //   1466: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1469: pop
    //   1470: aload_3
    //   1471: astore_2
    //   1472: aload 8
    //   1474: aload 4
    //   1476: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1479: invokevirtual 254	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1482: ifeq +15 -> 1497
    //   1485: aload_3
    //   1486: astore_2
    //   1487: iconst_0
    //   1488: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1491: astore_3
    //   1492: aload_3
    //   1493: astore_2
    //   1494: goto +5 -> 1499
    //   1497: aload_3
    //   1498: astore_2
    //   1499: aload_2
    //   1500: astore_3
    //   1501: aload_2
    //   1502: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   1505: ifeq +339 -> 1844
    //   1508: aload_2
    //   1509: astore_3
    //   1510: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1513: invokevirtual 230	com/bandwidthx/library/ch:F	()Lcom/bandwidthx/library/do;
    //   1516: invokevirtual 256	com/bandwidthx/library/do:u	()Ljava/lang/String;
    //   1519: astore 4
    //   1521: aload_2
    //   1522: astore_3
    //   1523: aload 4
    //   1525: invokevirtual 248	java/lang/String:length	()I
    //   1528: ifle +316 -> 1844
    //   1531: aload_2
    //   1532: astore_3
    //   1533: aload 4
    //   1535: ldc_w 258
    //   1538: invokevirtual 262	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   1541: astore 7
    //   1543: aload 7
    //   1545: iconst_0
    //   1546: aaload
    //   1547: astore 4
    //   1549: aload 7
    //   1551: iconst_1
    //   1552: aaload
    //   1553: astore 7
    //   1555: aload_2
    //   1556: astore_3
    //   1557: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1560: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   1563: bipush 72
    //   1565: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1568: invokevirtual 265	com/bandwidthx/library/eg:f	(Ljava/lang/Integer;)Ljava/util/ArrayList;
    //   1571: invokevirtual 271	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   1574: astore 8
    //   1576: aload_2
    //   1577: astore_3
    //   1578: aload 8
    //   1580: invokeinterface 276 1 0
    //   1585: ifeq +259 -> 1844
    //   1588: aload_2
    //   1589: astore_3
    //   1590: aload 8
    //   1592: invokeinterface 280 1 0
    //   1597: checkcast 10	com/bandwidthx/library/aw$a
    //   1600: astore 9
    //   1602: aload_2
    //   1603: astore_3
    //   1604: aload_1
    //   1605: invokevirtual 187	java/lang/Long:longValue	()J
    //   1608: aload 6
    //   1610: invokevirtual 187	java/lang/Long:longValue	()J
    //   1613: aload 9
    //   1615: getfield 281	com/bandwidthx/library/aw$a:b	Ljava/lang/Long;
    //   1618: invokevirtual 187	java/lang/Long:longValue	()J
    //   1621: ladd
    //   1622: lcmp
    //   1623: iflt -47 -> 1576
    //   1626: aload_2
    //   1627: astore_3
    //   1628: aload_1
    //   1629: invokevirtual 187	java/lang/Long:longValue	()J
    //   1632: aload 6
    //   1634: invokevirtual 187	java/lang/Long:longValue	()J
    //   1637: aload 9
    //   1639: getfield 282	com/bandwidthx/library/aw$a:c	Ljava/lang/Long;
    //   1642: invokevirtual 187	java/lang/Long:longValue	()J
    //   1645: ladd
    //   1646: lcmp
    //   1647: ifgt -71 -> 1576
    //   1650: aload_2
    //   1651: astore_3
    //   1652: new 208	java/lang/StringBuilder
    //   1655: dup
    //   1656: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   1659: astore 10
    //   1661: aload_2
    //   1662: astore_3
    //   1663: aload 10
    //   1665: ldc -45
    //   1667: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1670: pop
    //   1671: aload_2
    //   1672: astore_3
    //   1673: aload 10
    //   1675: aload 9
    //   1677: getfield 284	com/bandwidthx/library/aw$a:a	Ljava/lang/String;
    //   1680: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1683: pop
    //   1684: aload_2
    //   1685: astore_3
    //   1686: aload 10
    //   1688: ldc -45
    //   1690: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1693: pop
    //   1694: aload_2
    //   1695: astore_3
    //   1696: aload 10
    //   1698: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1701: astore 9
    //   1703: aload_2
    //   1704: astore_3
    //   1705: new 208	java/lang/StringBuilder
    //   1708: dup
    //   1709: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   1712: astore 10
    //   1714: aload_2
    //   1715: astore_3
    //   1716: aload 10
    //   1718: ldc -45
    //   1720: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1723: pop
    //   1724: aload_2
    //   1725: astore_3
    //   1726: aload 10
    //   1728: aload 4
    //   1730: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1733: pop
    //   1734: aload_2
    //   1735: astore_3
    //   1736: aload 10
    //   1738: ldc -45
    //   1740: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1743: pop
    //   1744: aload_2
    //   1745: astore_3
    //   1746: aload 9
    //   1748: aload 10
    //   1750: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1753: invokevirtual 254	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1756: ifne +80 -> 1836
    //   1759: aload_2
    //   1760: astore_3
    //   1761: new 208	java/lang/StringBuilder
    //   1764: dup
    //   1765: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   1768: astore 10
    //   1770: aload_2
    //   1771: astore_3
    //   1772: aload 10
    //   1774: ldc -45
    //   1776: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1779: pop
    //   1780: aload_2
    //   1781: astore_3
    //   1782: aload 10
    //   1784: aload 4
    //   1786: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1789: pop
    //   1790: aload_2
    //   1791: astore_3
    //   1792: aload 10
    //   1794: ldc_w 258
    //   1797: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1800: pop
    //   1801: aload_2
    //   1802: astore_3
    //   1803: aload 10
    //   1805: aload 7
    //   1807: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1810: pop
    //   1811: aload_2
    //   1812: astore_3
    //   1813: aload 10
    //   1815: ldc -45
    //   1817: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1820: pop
    //   1821: aload_2
    //   1822: astore_3
    //   1823: aload 9
    //   1825: aload 10
    //   1827: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1830: invokevirtual 254	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1833: ifeq -257 -> 1576
    //   1836: iconst_0
    //   1837: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1840: astore_3
    //   1841: goto +5 -> 1846
    //   1844: aload_2
    //   1845: astore_3
    //   1846: aload_3
    //   1847: astore 4
    //   1849: aload_3
    //   1850: astore_2
    //   1851: aload_3
    //   1852: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   1855: ifeq +386 -> 2241
    //   1858: aload_3
    //   1859: astore_2
    //   1860: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1863: invokevirtual 194	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   1866: bipush 73
    //   1868: invokestatic 104	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1871: invokevirtual 286	com/bandwidthx/library/eg:g	(Ljava/lang/Integer;)Ljava/util/ArrayList;
    //   1874: invokevirtual 271	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   1877: astore 7
    //   1879: aload_3
    //   1880: astore 4
    //   1882: aload_3
    //   1883: astore_2
    //   1884: aload 7
    //   1886: invokeinterface 276 1 0
    //   1891: ifeq +350 -> 2241
    //   1894: aload_3
    //   1895: astore_2
    //   1896: aload 7
    //   1898: invokeinterface 280 1 0
    //   1903: checkcast 13	com/bandwidthx/library/aw$b
    //   1906: astore 4
    //   1908: aload_3
    //   1909: astore_2
    //   1910: aload_1
    //   1911: invokevirtual 187	java/lang/Long:longValue	()J
    //   1914: aload 6
    //   1916: invokevirtual 187	java/lang/Long:longValue	()J
    //   1919: aload 4
    //   1921: getfield 288	com/bandwidthx/library/aw$b:i	Ljava/lang/Long;
    //   1924: invokevirtual 187	java/lang/Long:longValue	()J
    //   1927: ladd
    //   1928: lcmp
    //   1929: iflt -50 -> 1879
    //   1932: aload_3
    //   1933: astore_2
    //   1934: aload_1
    //   1935: invokevirtual 187	java/lang/Long:longValue	()J
    //   1938: aload 6
    //   1940: invokevirtual 187	java/lang/Long:longValue	()J
    //   1943: aload 4
    //   1945: getfield 290	com/bandwidthx/library/aw$b:j	Ljava/lang/Long;
    //   1948: invokevirtual 187	java/lang/Long:longValue	()J
    //   1951: ladd
    //   1952: lcmp
    //   1953: ifgt -74 -> 1879
    //   1956: aload_3
    //   1957: astore_2
    //   1958: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1961: invokevirtual 293	com/bandwidthx/library/ch:B	()Lcom/bandwidthx/library/bq;
    //   1964: invokevirtual 298	com/bandwidthx/library/bq:l	()Ljava/lang/Double;
    //   1967: astore 8
    //   1969: aload_3
    //   1970: astore_2
    //   1971: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   1974: invokevirtual 293	com/bandwidthx/library/ch:B	()Lcom/bandwidthx/library/bq;
    //   1977: invokevirtual 300	com/bandwidthx/library/bq:m	()Ljava/lang/Double;
    //   1980: astore 9
    //   1982: aload_3
    //   1983: astore_2
    //   1984: aload 4
    //   1986: getfield 301	com/bandwidthx/library/aw$b:a	Ljava/lang/String;
    //   1989: invokevirtual 248	java/lang/String:length	()I
    //   1992: ifle +161 -> 2153
    //   1995: aload_3
    //   1996: astore_2
    //   1997: getstatic 159	com/bandwidthx/library/aw:v	Lcom/bandwidthx/library/ch;
    //   2000: invokevirtual 293	com/bandwidthx/library/ch:B	()Lcom/bandwidthx/library/bq;
    //   2003: aload 8
    //   2005: aload 9
    //   2007: invokevirtual 304	com/bandwidthx/library/bq:c	(Ljava/lang/Double;Ljava/lang/Double;)Lcom/bandwidthx/library/bq$f;
    //   2010: astore 8
    //   2012: aload 8
    //   2014: ifnull -135 -> 1879
    //   2017: aload_3
    //   2018: astore_2
    //   2019: aload 4
    //   2021: getfield 301	com/bandwidthx/library/aw$b:a	Ljava/lang/String;
    //   2024: invokevirtual 248	java/lang/String:length	()I
    //   2027: ifeq +21 -> 2048
    //   2030: aload_3
    //   2031: astore_2
    //   2032: aload 4
    //   2034: getfield 301	com/bandwidthx/library/aw$b:a	Ljava/lang/String;
    //   2037: aload 8
    //   2039: getfield 307	com/bandwidthx/library/bq$f:a	Ljava/lang/String;
    //   2042: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2045: ifeq -166 -> 1879
    //   2048: aload_3
    //   2049: astore_2
    //   2050: aload 4
    //   2052: getfield 313	com/bandwidthx/library/aw$b:b	Ljava/lang/String;
    //   2055: invokevirtual 248	java/lang/String:length	()I
    //   2058: ifeq +21 -> 2079
    //   2061: aload_3
    //   2062: astore_2
    //   2063: aload 4
    //   2065: getfield 313	com/bandwidthx/library/aw$b:b	Ljava/lang/String;
    //   2068: aload 8
    //   2070: getfield 314	com/bandwidthx/library/bq$f:b	Ljava/lang/String;
    //   2073: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2076: ifeq -197 -> 1879
    //   2079: aload_3
    //   2080: astore_2
    //   2081: aload 4
    //   2083: getfield 316	com/bandwidthx/library/aw$b:c	Ljava/lang/String;
    //   2086: invokevirtual 248	java/lang/String:length	()I
    //   2089: ifeq +21 -> 2110
    //   2092: aload_3
    //   2093: astore_2
    //   2094: aload 4
    //   2096: getfield 316	com/bandwidthx/library/aw$b:c	Ljava/lang/String;
    //   2099: aload 8
    //   2101: getfield 317	com/bandwidthx/library/bq$f:c	Ljava/lang/String;
    //   2104: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2107: ifeq -228 -> 1879
    //   2110: aload_3
    //   2111: astore_2
    //   2112: aload 4
    //   2114: getfield 319	com/bandwidthx/library/aw$b:d	Ljava/lang/String;
    //   2117: invokevirtual 248	java/lang/String:length	()I
    //   2120: ifeq +21 -> 2141
    //   2123: aload_3
    //   2124: astore_2
    //   2125: aload 4
    //   2127: getfield 319	com/bandwidthx/library/aw$b:d	Ljava/lang/String;
    //   2130: aload 8
    //   2132: getfield 320	com/bandwidthx/library/bq$f:d	Ljava/lang/String;
    //   2135: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2138: ifeq -259 -> 1879
    //   2141: aload_3
    //   2142: astore_2
    //   2143: iconst_0
    //   2144: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   2147: astore_3
    //   2148: aload_3
    //   2149: astore_2
    //   2150: goto +94 -> 2244
    //   2153: aload_3
    //   2154: astore_2
    //   2155: aload 8
    //   2157: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   2160: aload 4
    //   2162: getfield 329	com/bandwidthx/library/aw$b:f	Ljava/lang/Double;
    //   2165: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   2168: dcmpl
    //   2169: iflt -290 -> 1879
    //   2172: aload_3
    //   2173: astore_2
    //   2174: aload 8
    //   2176: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   2179: aload 4
    //   2181: getfield 331	com/bandwidthx/library/aw$b:h	Ljava/lang/Double;
    //   2184: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   2187: dcmpg
    //   2188: ifgt -309 -> 1879
    //   2191: aload_3
    //   2192: astore_2
    //   2193: aload 9
    //   2195: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   2198: aload 4
    //   2200: getfield 333	com/bandwidthx/library/aw$b:e	Ljava/lang/Double;
    //   2203: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   2206: dcmpl
    //   2207: iflt -328 -> 1879
    //   2210: aload_3
    //   2211: astore_2
    //   2212: aload 9
    //   2214: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   2217: aload 4
    //   2219: getfield 335	com/bandwidthx/library/aw$b:g	Ljava/lang/Double;
    //   2222: invokevirtual 326	java/lang/Double:doubleValue	()D
    //   2225: dcmpg
    //   2226: ifgt -347 -> 1879
    //   2229: aload_3
    //   2230: astore_2
    //   2231: iconst_0
    //   2232: invokestatic 117	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   2235: astore_3
    //   2236: aload_3
    //   2237: astore_2
    //   2238: goto +6 -> 2244
    //   2241: aload 4
    //   2243: astore_2
    //   2244: aload_2
    //   2245: astore_3
    //   2246: goto +14 -> 2260
    //   2249: astore 4
    //   2251: aload_2
    //   2252: astore_3
    //   2253: aload_3
    //   2254: astore_2
    //   2255: aload 4
    //   2257: invokestatic 166	com/bandwidthx/library/cg:a	(Ljava/lang/Throwable;)V
    //   2260: aload_3
    //   2261: astore_2
    //   2262: aload_0
    //   2263: aload_1
    //   2264: putfield 147	com/bandwidthx/library/aw:a	Ljava/lang/Long;
    //   2267: aload_3
    //   2268: astore_2
    //   2269: goto +14 -> 2283
    //   2272: astore_1
    //   2273: goto +6 -> 2279
    //   2276: astore_1
    //   2277: aload_3
    //   2278: astore_2
    //   2279: aload_1
    //   2280: invokestatic 166	com/bandwidthx/library/cg:a	(Ljava/lang/Throwable;)V
    //   2283: aload 5
    //   2285: aload_2
    //   2286: if_acmpeq +55 -> 2341
    //   2289: aload_0
    //   2290: aload_2
    //   2291: putfield 145	com/bandwidthx/library/aw:P	Ljava/lang/Boolean;
    //   2294: new 208	java/lang/StringBuilder
    //   2297: dup
    //   2298: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   2301: astore_3
    //   2302: aload_3
    //   2303: ldc_w 337
    //   2306: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2309: pop
    //   2310: aload_2
    //   2311: invokevirtual 203	java/lang/Boolean:booleanValue	()Z
    //   2314: ifeq +10 -> 2324
    //   2317: ldc_w 339
    //   2320: astore_1
    //   2321: goto +7 -> 2328
    //   2324: ldc_w 341
    //   2327: astore_1
    //   2328: aload_3
    //   2329: aload_1
    //   2330: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2333: pop
    //   2334: aload_3
    //   2335: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2338: invokestatic 344	com/bandwidthx/library/cg:b	(Ljava/lang/String;)V
    //   2341: aload_2
    //   2342: areturn
    //   2343: goto -1841 -> 502
    //   2346: aload_2
    //   2347: astore_3
    //   2348: goto -1168 -> 1180
    //   2351: astore 4
    //   2353: goto -100 -> 2253
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2356	0	this	aw
    //   0	2356	1	paramLong	Long
    //   12	2335	2	localObject1	Object
    //   10	2338	3	localObject2	Object
    //   40	2202	4	localObject3	Object
    //   2249	7	4	localException1	Exception
    //   2351	1	4	localException2	Exception
    //   4	2280	5	localBoolean	Boolean
    //   73	1866	6	localLong	Long
    //   145	1752	7	localObject4	Object
    //   157	2018	8	localObject5	Object
    //   500	1713	9	localObject6	Object
    //   588	1238	10	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   58	75	2249	java/lang/Exception
    //   81	101	2249	java/lang/Exception
    //   104	110	2249	java/lang/Exception
    //   424	431	2249	java/lang/Exception
    //   433	444	2249	java/lang/Exception
    //   449	457	2249	java/lang/Exception
    //   459	469	2249	java/lang/Exception
    //   483	502	2249	java/lang/Exception
    //   507	517	2249	java/lang/Exception
    //   519	531	2249	java/lang/Exception
    //   533	555	2249	java/lang/Exception
    //   557	579	2249	java/lang/Exception
    //   581	590	2249	java/lang/Exception
    //   592	600	2249	java/lang/Exception
    //   602	613	2249	java/lang/Exception
    //   615	623	2249	java/lang/Exception
    //   625	632	2249	java/lang/Exception
    //   634	643	2249	java/lang/Exception
    //   645	653	2249	java/lang/Exception
    //   655	663	2249	java/lang/Exception
    //   665	673	2249	java/lang/Exception
    //   675	688	2249	java/lang/Exception
    //   690	699	2249	java/lang/Exception
    //   701	709	2249	java/lang/Exception
    //   711	719	2249	java/lang/Exception
    //   721	730	2249	java/lang/Exception
    //   732	740	2249	java/lang/Exception
    //   742	750	2249	java/lang/Exception
    //   752	765	2249	java/lang/Exception
    //   767	773	2249	java/lang/Exception
    //   782	790	2249	java/lang/Exception
    //   793	812	2249	java/lang/Exception
    //   818	828	2249	java/lang/Exception
    //   831	842	2249	java/lang/Exception
    //   845	866	2249	java/lang/Exception
    //   869	890	2249	java/lang/Exception
    //   893	904	2249	java/lang/Exception
    //   907	918	2249	java/lang/Exception
    //   921	931	2249	java/lang/Exception
    //   934	949	2249	java/lang/Exception
    //   957	967	2249	java/lang/Exception
    //   970	985	2249	java/lang/Exception
    //   988	998	2249	java/lang/Exception
    //   1001	1016	2249	java/lang/Exception
    //   1019	1029	2249	java/lang/Exception
    //   1032	1047	2249	java/lang/Exception
    //   1050	1060	2249	java/lang/Exception
    //   1063	1078	2249	java/lang/Exception
    //   1081	1086	2249	java/lang/Exception
    //   1094	1110	2249	java/lang/Exception
    //   1113	1129	2249	java/lang/Exception
    //   1132	1148	2249	java/lang/Exception
    //   1151	1167	2249	java/lang/Exception
    //   1170	1175	2249	java/lang/Exception
    //   1185	1205	2249	java/lang/Exception
    //   1207	1223	2249	java/lang/Exception
    //   1225	1241	2249	java/lang/Exception
    //   1243	1252	2249	java/lang/Exception
    //   1254	1262	2249	java/lang/Exception
    //   1264	1287	2249	java/lang/Exception
    //   1289	1297	2249	java/lang/Exception
    //   1299	1306	2249	java/lang/Exception
    //   1308	1316	2249	java/lang/Exception
    //   1318	1333	2249	java/lang/Exception
    //   1335	1352	2249	java/lang/Exception
    //   1354	1359	2249	java/lang/Exception
    //   1366	1374	2249	java/lang/Exception
    //   1376	1396	2249	java/lang/Exception
    //   1398	1403	2249	java/lang/Exception
    //   1410	1419	2249	java/lang/Exception
    //   1421	1430	2249	java/lang/Exception
    //   1432	1440	2249	java/lang/Exception
    //   1442	1460	2249	java/lang/Exception
    //   1462	1470	2249	java/lang/Exception
    //   1472	1485	2249	java/lang/Exception
    //   1487	1492	2249	java/lang/Exception
    //   1851	1858	2249	java/lang/Exception
    //   1860	1879	2249	java/lang/Exception
    //   1884	1894	2249	java/lang/Exception
    //   1896	1908	2249	java/lang/Exception
    //   1910	1932	2249	java/lang/Exception
    //   1934	1956	2249	java/lang/Exception
    //   1958	1969	2249	java/lang/Exception
    //   1971	1982	2249	java/lang/Exception
    //   1984	1995	2249	java/lang/Exception
    //   1997	2012	2249	java/lang/Exception
    //   2019	2030	2249	java/lang/Exception
    //   2032	2048	2249	java/lang/Exception
    //   2050	2061	2249	java/lang/Exception
    //   2063	2079	2249	java/lang/Exception
    //   2081	2092	2249	java/lang/Exception
    //   2094	2110	2249	java/lang/Exception
    //   2112	2123	2249	java/lang/Exception
    //   2125	2141	2249	java/lang/Exception
    //   2143	2148	2249	java/lang/Exception
    //   2155	2172	2249	java/lang/Exception
    //   2174	2191	2249	java/lang/Exception
    //   2193	2210	2249	java/lang/Exception
    //   2212	2229	2249	java/lang/Exception
    //   2231	2236	2249	java/lang/Exception
    //   45	55	2272	java/lang/Exception
    //   2255	2260	2272	java/lang/Exception
    //   2262	2267	2272	java/lang/Exception
    //   13	42	2276	java/lang/Exception
    //   113	128	2351	java/lang/Exception
    //   131	147	2351	java/lang/Exception
    //   150	159	2351	java/lang/Exception
    //   162	170	2351	java/lang/Exception
    //   173	196	2351	java/lang/Exception
    //   199	207	2351	java/lang/Exception
    //   210	217	2351	java/lang/Exception
    //   220	227	2351	java/lang/Exception
    //   230	245	2351	java/lang/Exception
    //   248	265	2351	java/lang/Exception
    //   268	273	2351	java/lang/Exception
    //   281	289	2351	java/lang/Exception
    //   292	312	2351	java/lang/Exception
    //   315	320	2351	java/lang/Exception
    //   328	337	2351	java/lang/Exception
    //   340	348	2351	java/lang/Exception
    //   351	358	2351	java/lang/Exception
    //   361	378	2351	java/lang/Exception
    //   381	388	2351	java/lang/Exception
    //   391	403	2351	java/lang/Exception
    //   406	411	2351	java/lang/Exception
    //   1501	1508	2351	java/lang/Exception
    //   1510	1521	2351	java/lang/Exception
    //   1523	1531	2351	java/lang/Exception
    //   1533	1543	2351	java/lang/Exception
    //   1557	1576	2351	java/lang/Exception
    //   1578	1588	2351	java/lang/Exception
    //   1590	1602	2351	java/lang/Exception
    //   1604	1626	2351	java/lang/Exception
    //   1628	1650	2351	java/lang/Exception
    //   1652	1661	2351	java/lang/Exception
    //   1663	1671	2351	java/lang/Exception
    //   1673	1684	2351	java/lang/Exception
    //   1686	1694	2351	java/lang/Exception
    //   1696	1703	2351	java/lang/Exception
    //   1705	1714	2351	java/lang/Exception
    //   1716	1724	2351	java/lang/Exception
    //   1726	1734	2351	java/lang/Exception
    //   1736	1744	2351	java/lang/Exception
    //   1746	1759	2351	java/lang/Exception
    //   1761	1770	2351	java/lang/Exception
    //   1772	1780	2351	java/lang/Exception
    //   1782	1790	2351	java/lang/Exception
    //   1792	1801	2351	java/lang/Exception
    //   1803	1811	2351	java/lang/Exception
    //   1813	1821	2351	java/lang/Exception
    //   1823	1836	2351	java/lang/Exception
  }
  
  public b A()
  {
    return new b();
  }
  
  public void B()
  {
    this.P = Boolean.valueOf(true);
    this.a = Long.valueOf(0L);
  }
  
  public Boolean C()
  {
    return a(fi.f());
  }
  
  public Boolean D()
  {
    if (!v.Q().a(Integer.valueOf(155)).booleanValue()) {
      return Boolean.valueOf(false);
    }
    return v.i().o();
  }
  
  public Boolean a(Boolean paramBoolean)
  {
    for (;;)
    {
      Boolean localBoolean;
      Object localObject3;
      Object localObject6;
      try
      {
        localBoolean = this.Q;
        if ((paramBoolean.booleanValue()) && (v.i().o().booleanValue()))
        {
          this.R = Long.valueOf(0L);
          return Boolean.valueOf(true);
        }
        if (v.Q().d(Integer.valueOf(88)).length() > 0)
        {
          Pattern localPattern = Pattern.compile(v.Q().d(Integer.valueOf(88)));
          if (this.R.longValue() >= fi.f().longValue() - this.c.longValue()) {
            continue;
          }
          this.Q = Boolean.valueOf(true);
          this.R = fi.f();
          try
          {
            paramBoolean = Long.valueOf(0L);
            localObject3 = "";
            localObject1 = "";
            localPackageManager = ch.e().getPackageManager();
            Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
            if (localIterator.hasNext()) {
              localObject4 = (ApplicationInfo)localIterator.next();
            }
          }
          catch (Exception paramBoolean)
          {
            Object localObject1;
            PackageManager localPackageManager;
            Object localObject4;
            String str2;
            PackageInfo localPackageInfo;
            int i2;
            int i1;
            cg.a(paramBoolean);
          }
        }
      }
      finally {}
      try
      {
        v.i();
        str2 = ac.c(Integer.valueOf(((ApplicationInfo)localObject4).uid));
        if (!localPattern.matcher(str2).matches()) {
          continue;
        }
        localPackageInfo = localPackageManager.getPackageInfo(str2, 2);
        if (localPackageInfo == null) {
          continue;
        }
        localObject4 = localPackageInfo.receivers;
        if (localObject4 == null) {
          continue;
        }
        i2 = localObject4.length;
        i1 = 0;
        if (i1 >= i2) {
          continue;
        }
        if (!localObject4[i1].name.contains("BxReceiver")) {
          continue;
        }
        if ((!localPackageInfo.applicationInfo.enabled) || ((localPackageInfo.applicationInfo.flags & 0x200000) != 0)) {
          continue;
        }
        if (!BxService.isServiceRunning(ch.e(), str2).booleanValue()) {
          continue;
        }
        localObject6 = new StringBuilder();
        ((StringBuilder)localObject6).append((String)localObject1);
        if (((String)localObject1).length() <= 0) {
          break label754;
        }
        localObject4 = "; ";
      }
      catch (Exception localException2)
      {
        continue;
        String str1 = "";
        continue;
        str1 = "";
        continue;
      }
      ((StringBuilder)localObject6).append((String)localObject4);
      ((StringBuilder)localObject6).append(str2);
      ((StringBuilder)localObject6).append(" (running)");
      String str3 = ((StringBuilder)localObject6).toString();
      try
      {
        l1 = paramBoolean.longValue();
        localObject6 = paramBoolean;
        localObject4 = localObject3;
        localObject1 = str3;
        paramBoolean = (Boolean)localObject6;
      }
      catch (Exception localException1)
      {
        long l1;
        continue;
      }
      try
      {
        if (l1 >= localPackageInfo.lastUpdateTime) {
          continue;
        }
        l1 = localPackageInfo.lastUpdateTime;
        paramBoolean = Long.valueOf(l1);
        localObject4 = str2;
        localObject1 = str3;
      }
      catch (Exception paramBoolean)
      {
        localObject5 = localObject3;
        localObject2 = str3;
        paramBoolean = (Boolean)localObject6;
        continue;
      }
      localObject4 = localObject3;
      localObject1 = str3;
      try
      {
        localObject6 = new StringBuilder();
        ((StringBuilder)localObject6).append((String)localObject1);
        if (((String)localObject1).length() <= 0) {
          break label761;
        }
        localObject4 = "; ";
        ((StringBuilder)localObject6).append((String)localObject4);
        ((StringBuilder)localObject6).append(str2);
        ((StringBuilder)localObject6).append(" (not running)");
        localObject4 = ((StringBuilder)localObject6).toString();
        localObject1 = localObject4;
        localObject4 = localObject3;
        continue;
        i1 += 1;
        continue;
        localObject4 = localObject3;
        localObject3 = localObject4;
      }
      catch (Exception localException3)
      {
        Object localObject5;
        Object localObject2;
      }
    }
    if (((String)localObject3).length() > 0) {
      if (((String)localObject3).equals(ac.d())) {
        this.Q = Boolean.valueOf(true);
      } else {
        this.Q = Boolean.valueOf(false);
      }
    }
    if (localBoolean != this.Q)
    {
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("APP Client is now ");
      if (this.Q.booleanValue())
      {
        paramBoolean = "active";
      }
      else
      {
        paramBoolean = new StringBuilder();
        paramBoolean.append("passive, yielding to ");
        paramBoolean.append((String)localObject3);
        paramBoolean = paramBoolean.toString();
      }
      ((StringBuilder)localObject4).append(paramBoolean);
      ((StringBuilder)localObject4).append(": ");
      ((StringBuilder)localObject4).append((String)localObject1);
      cg.b(((StringBuilder)localObject4).toString());
      break label710;
      this.Q = Boolean.valueOf(true);
      if (!localBoolean.booleanValue()) {
        cg.b("APP Client is now active");
      }
    }
    label710:
    paramBoolean = this.Q;
    return paramBoolean;
  }
  
  public void a()
  {
    this.w = v.I().a("AutoWifiComment", this.w);
    this.y = Integer.valueOf(v.I().a("AutoWifiSuspendedReason", this.y.intValue()));
    this.x = Long.valueOf(v.I().a("AutoWifiSuspendedTicks", this.x.longValue()));
    this.z = v.I().a("XAutoWifiSuspendedSSID", "", Boolean.valueOf(true));
    this.B = Long.valueOf(v.I().a("AutoWifiSuspendedSSIDTicks", this.B.longValue()));
    this.C = v.I().a("AutoWifiSuspendedScreenOn", this.C);
    this.D = v.I().a("AutoWifiSuspendedScreenOff", this.D);
    this.E = v.I().a("AutoWifiSuspendedForeground", this.E);
    this.F = v.I().a("AutoWifiSuspendedRadioOn", this.F);
    this.G = v.I().a("AutoWifiSuspendedRadioOff", this.G);
    this.H = v.I().a("AutoWifiSuspendedConnected", this.H);
    this.I = v.I().a("AutoWifiSuspendedPhoneIdentity", this.I);
    this.J = v.I().a("AutoWifiSuspendedUserIdentity", this.J);
    this.K = v.I().a("AutoWifiSuspendedSimIdentity", this.K);
    this.L = v.I().a("AutoWifiSuspendedSubscriberIdentity", this.L);
    this.M = v.I().a("AutoWifiSuspendedDeviceIdentity", this.M);
    this.N = v.I().a("AutoWifiSuspendedMobile", this.N);
    this.O = v.I().a("AutoWifiSuspendedLocation", this.O);
  }
  
  public void a(Long paramLong, Integer paramInteger)
  {
    a(paramLong, "", paramInteger, "", Boolean.valueOf(true));
  }
  
  public void a(Long paramLong, Integer paramInteger, String paramString)
  {
    a(paramLong, "", paramInteger, paramString, Boolean.valueOf(true));
  }
  
  public void a(Long paramLong, String paramString, Integer paramInteger)
  {
    a(paramLong, paramString, paramInteger, "", Boolean.valueOf(true));
  }
  
  public void a(Long paramLong, String paramString1, Integer paramInteger, String paramString2)
  {
    a(paramLong, paramString1, paramInteger, "", Boolean.valueOf(true));
  }
  
  public void a(Long paramLong, String paramString1, Integer paramInteger, String paramString2, Boolean paramBoolean)
  {
    if ((v.x().i().booleanValue()) && (paramInteger.intValue() != 0))
    {
      cg.b("APP Won't suspend due to test mode");
      return;
    }
    Boolean localBoolean = e();
    this.w = paramString2;
    this.y = paramInteger;
    if (paramLong.longValue() == Long.MAX_VALUE) {
      this.x = paramLong;
    } else {
      this.x = Long.valueOf(fi.f().longValue() + paramLong.longValue());
    }
    this.z = paramString1;
    this.A = Long.valueOf(0L);
    this.B = this.x;
    this.C = Boolean.valueOf(false);
    this.D = Boolean.valueOf(false);
    this.E = Boolean.valueOf(false);
    this.F = Boolean.valueOf(false);
    this.G = Boolean.valueOf(false);
    this.H = Boolean.valueOf(false);
    this.I = Boolean.valueOf(false);
    this.J = Boolean.valueOf(false);
    this.K = Boolean.valueOf(false);
    this.L = Boolean.valueOf(false);
    this.M = Boolean.valueOf(false);
    this.N = Boolean.valueOf(false);
    this.O = Boolean.valueOf(false);
    v.I().c("AutoWifiComment", this.w);
    v.I().c("AutoWifiSuspendedReason", this.y.intValue());
    v.I().c("AutoWifiSuspendedTicks", this.x.longValue());
    v.I().b("XAutoWifiSuspendedSSID", this.z, Boolean.valueOf(true));
    v.I().c("AutoWifiSuspendedSSIDTicks", this.B.longValue());
    v.I().c("AutoWifiSuspendedScreenOn", this.C);
    v.I().c("AutoWifiSuspendedScreenOff", this.D);
    v.I().c("AutoWifiSuspendedForeground", this.E);
    v.I().c("AutoWifiSuspendedRadioOn", this.F);
    v.I().c("AutoWifiSuspendedRadioOff", this.G);
    v.I().c("AutoWifiSuspendedConnected", this.H);
    v.I().c("AutoWifiSuspendedPhoneIdentity", this.I);
    v.I().c("AutoWifiSuspendedUserIdentity", this.J);
    v.I().c("AutoWifiSuspendedSimIdentity", this.K);
    v.I().c("AutoWifiSuspendedSubscriberIdentity", this.L);
    v.I().c("AutoWifiSuspendedDeviceIdentity", this.M);
    v.I().c("AutoWifiSuspendedMobile", this.N);
    v.I().c("AutoWifiSuspendedLocation", this.O);
    v.I().c();
    v.n().z();
    v.n().w();
    if (!localBoolean.booleanValue())
    {
      bf.m();
      cg.b("Contact server after suspend");
      try
      {
        v.P().b(paramBoolean);
      }
      catch (Exception paramLong)
      {
        cg.a(paramLong);
      }
    }
    if (v.g() != null) {
      v.g().l();
    }
  }
  
  public void a(String paramString)
  {
    Boolean localBoolean = e();
    this.w = paramString;
    this.y = Integer.valueOf(0);
    this.x = Long.valueOf(0L);
    this.z = "";
    this.A = Long.valueOf(0L);
    this.B = Long.valueOf(0L);
    this.C = Boolean.valueOf(false);
    this.D = Boolean.valueOf(false);
    this.E = Boolean.valueOf(false);
    this.F = Boolean.valueOf(false);
    this.G = Boolean.valueOf(false);
    this.H = Boolean.valueOf(false);
    this.I = Boolean.valueOf(false);
    this.J = Boolean.valueOf(false);
    this.K = Boolean.valueOf(false);
    this.L = Boolean.valueOf(false);
    this.M = Boolean.valueOf(false);
    this.N = Boolean.valueOf(false);
    this.O = Boolean.valueOf(false);
    v.I().c("AutoWifiComment", this.w);
    v.I().c("AutoWifiSuspendedReason", this.y.intValue());
    v.I().c("AutoWifiSuspendedTicks", this.x.longValue());
    v.I().b("XAutoWifiSuspendedSSID", this.z, Boolean.valueOf(true));
    v.I().c("AutoWifiSuspendedSSIDTicks", this.B.longValue());
    v.I().c("AutoWifiSuspendedScreenOn", this.C);
    v.I().c("AutoWifiSuspendedScreenOff", this.D);
    v.I().c("AutoWifiSuspendedForeground", this.E);
    v.I().c("AutoWifiSuspendedRadioOn", this.F);
    v.I().c("AutoWifiSuspendedRadioOff", this.G);
    v.I().c("AutoWifiSuspendedConnected", this.H);
    v.I().c("AutoWifiSuspendedPhoneIdentity", this.I);
    v.I().c("AutoWifiSuspendedUserIdentity", this.J);
    v.I().c("AutoWifiSuspendedSimIdentity", this.K);
    v.I().c("AutoWifiSuspendedSubscriberIdentity", this.L);
    v.I().c("AutoWifiSuspendedDeviceIdentity", this.M);
    v.I().c("AutoWifiSuspendedMobile", this.N);
    v.I().c("AutoWifiSuspendedLocation", this.O);
    v.I().c();
    if (localBoolean.booleanValue())
    {
      bf.l();
      cg.b("Contact server after resume");
      v.P().b(Boolean.valueOf(true));
    }
    if (v.g() != null) {
      v.g().a(Boolean.valueOf(false));
    }
  }
  
  public void b() {}
  
  public void b(Boolean paramBoolean)
  {
    if (!v.Q().a(Integer.valueOf(155)).equals(paramBoolean))
    {
      v.Q().a(Integer.valueOf(155), paramBoolean);
      BxService.startWorkIfScreenIsOn();
    }
  }
  
  public void c()
  {
    y();
  }
  
  public String d()
  {
    return this.w;
  }
  
  public Boolean e()
  {
    boolean bool;
    if (h().longValue() > 0L) {
      bool = true;
    } else {
      bool = false;
    }
    return Boolean.valueOf(bool);
  }
  
  public Integer f()
  {
    if ((this.x.longValue() == 0L) && (!C().booleanValue())) {
      return Integer.valueOf(4);
    }
    return this.y;
  }
  
  public String g()
  {
    return this.z;
  }
  
  public Long h()
  {
    Long localLong = fi.f();
    x();
    if (this.x.longValue() == Long.MAX_VALUE) {
      return this.x;
    }
    if (this.x.longValue() > localLong.longValue()) {
      return Long.valueOf(this.x.longValue() - localLong.longValue());
    }
    if (!a(localLong).booleanValue()) {
      return Long.valueOf(1L);
    }
    if ((this.z.length() > 0) && (this.x.longValue() > 0L))
    {
      if ((this.A.longValue() > 0L) && (this.A.longValue() < localLong.longValue() - v.Q().c(Integer.valueOf(8)).longValue())) {
        this.A = Long.valueOf(0L);
      }
      if (this.A.longValue() == 0L)
      {
        this.A = localLong;
        this.x = Long.valueOf(localLong.longValue() + v.Q().c(Integer.valueOf(8)).longValue());
        v.I().c("AutoWifiSuspendedTicks", this.x.longValue());
        v.I().c();
        try
        {
          new AsyncTask()
          {
            protected Void a(Void... paramAnonymousVarArgs)
            {
              ah.a(this, "CheckSuspension");
              try
              {
                if (aw.a(aw.this).length() > 0)
                {
                  paramAnonymousVarArgs = new StringBuilder();
                  paramAnonymousVarArgs.append("APP Suspension period has ended, check to see if ");
                  paramAnonymousVarArgs.append(aw.a(aw.this));
                  paramAnonymousVarArgs.append(" is still available");
                  cg.b(paramAnonymousVarArgs.toString());
                  aw.E().j().o();
                }
              }
              catch (Throwable paramAnonymousVarArgs)
              {
                cg.a(paramAnonymousVarArgs, Boolean.valueOf(false));
              }
              ah.a(this);
              return null;
            }
          }.execute(new Void[0]);
        }
        catch (Exception localException)
        {
          cg.a(localException);
        }
      }
      return Long.valueOf(1L);
    }
    if ((this.y.intValue() == 3) && (!v.w().h().booleanValue()) && (v.w().i().intValue() < v.Q().b(Integer.valueOf(4)).intValue()))
    {
      this.x = Long.valueOf(localLong.longValue() + v.Q().c(Integer.valueOf(3)).longValue());
      v.I().c("AutoWifiSuspendedTicks", this.x.longValue());
      v.I().c();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("APP Suspend due to battery level: ");
      localStringBuilder.append(v.w().i().toString());
      String str;
      if (v.w().h().booleanValue()) {
        str = "*";
      } else {
        str = "";
      }
      localStringBuilder.append(str);
      cg.b(localStringBuilder.toString());
      return Long.valueOf(this.x.longValue() - localLong.longValue());
    }
    return Long.valueOf(0L);
  }
  
  public void i()
  {
    a("");
  }
  
  public void j()
  {
    this.E = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedForeground", this.E);
    v.I().c();
  }
  
  public void k()
  {
    this.F = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedRadioOn", this.F);
    v.I().c();
  }
  
  public void l()
  {
    this.G = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedRadioOff", this.G);
    v.I().c();
  }
  
  public void m()
  {
    this.C = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedScreenOn", this.C);
    v.I().c();
  }
  
  public void n()
  {
    this.D = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedScreenOff", this.D);
    v.I().c();
  }
  
  public void o()
  {
    this.H = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedConnected", this.H);
    v.I().c();
  }
  
  public void p()
  {
    this.I = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedPhoneIdentity", this.I);
    v.I().c();
  }
  
  public void q()
  {
    this.J = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedUserIdentity", this.J);
    v.I().c();
  }
  
  public void r()
  {
    this.K = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedSimIdentity", this.K);
    v.I().c();
  }
  
  public void s()
  {
    this.L = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedSubscriberIdentity", this.L);
    v.I().c();
  }
  
  public void t()
  {
    this.M = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedDeviceIdentity", this.M);
    v.I().c();
  }
  
  public void u()
  {
    this.N = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedMobile", this.N);
    v.I().c();
  }
  
  public void v()
  {
    this.O = Boolean.valueOf(true);
    v.I().c("AutoWifiSuspendedLocation", this.O);
    v.I().c();
  }
  
  public void w()
  {
    this.A = Long.valueOf(0L);
  }
  
  public void x()
  {
    try
    {
      if (this.x.longValue() > 0L)
      {
        if (this.B.longValue() == 0L)
        {
          this.B = this.x;
          v.I().c("AutoWifiSuspendedSSIDTicks", this.B.longValue());
          v.I().c();
        }
        if ((this.z.length() > 0) && (v.X().g().booleanValue()) && (v.X().c("", this.z).booleanValue()))
        {
          this.B = fi.f();
          v.I().c("AutoWifiSuspendedSSIDTicks", this.B.longValue());
          v.I().c();
          return;
        }
      }
    }
    catch (Exception localException)
    {
      cg.a(localException, Boolean.valueOf(false));
    }
  }
  
  public void y()
  {
    try
    {
      if (this.x.longValue() > 0L)
      {
        Boolean localBoolean = Boolean.valueOf(false);
        x();
        Object localObject;
        if ((v.X().v().booleanValue()) && (this.y.intValue() == 2))
        {
          cg.b("APP Access point mode, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.C.booleanValue()) && (v.w().k().booleanValue()))
        {
          cg.b("APP Screen is on, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.D.booleanValue()) && (v.w().l().booleanValue()))
        {
          cg.b("APP Screen is off, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.E.booleanValue()) && (v.i().o().booleanValue()))
        {
          cg.b("APP App is in foreground, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.F.booleanValue()) && (v.X().g().booleanValue()))
        {
          cg.b("APP Radio is on, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.G.booleanValue()) && (!v.X().g().booleanValue()))
        {
          cg.b("APP Radio is off, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.H.booleanValue()) && (v.X().k().booleanValue()))
        {
          cg.b("APP Device is connected, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.I.booleanValue()) && (v.F().o().length() > 0) && (v.F().h().length() >= 3))
        {
          cg.b("APP Device has phone identity, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.J.booleanValue()) && (v.U().f().length() > 0))
        {
          cg.b("APP Device has user identity, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.K.booleanValue()) && (v.F().i().length() > 0))
        {
          cg.b("APP Device has sim identity, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.L.booleanValue()) && (v.F().k().length() > 0))
        {
          cg.b("APP Device has subscriber identity, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.M.booleanValue()) && (v.F().j().length() > 0))
        {
          cg.b("APP Device has device identity, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.N.booleanValue()) && (v.F().s().longValue() > 0L))
        {
          cg.b("APP Device has mobile, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((this.O.booleanValue()) && ((v.B().l().doubleValue() != 0.0D) || (v.B().m().doubleValue() != 0.0D)) && (v.B().o().longValue() <= 100L))
        {
          cg.b("APP Device has location, so resume");
          localObject = Boolean.valueOf(true);
        }
        else if ((v.w().h().booleanValue()) && (this.y.intValue() == 3))
        {
          cg.b("APP Device is plugged in, so resume");
          localObject = Boolean.valueOf(true);
        }
        else
        {
          localObject = localBoolean;
          if (this.z.length() > 0)
          {
            localObject = localBoolean;
            if (this.B.longValue() < fi.f().longValue() - v.Q().c(Integer.valueOf(9)).longValue())
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("APP Hotspot ");
              ((StringBuilder)localObject).append(this.z);
              ((StringBuilder)localObject).append(" has not been seen for ");
              ((StringBuilder)localObject).append(bg.d(v.Q().c(Integer.valueOf(9)), Boolean.valueOf(false)));
              ((StringBuilder)localObject).append(", last seen ");
              ((StringBuilder)localObject).append(bg.a(this.B));
              ((StringBuilder)localObject).append(", so resume");
              cg.b(((StringBuilder)localObject).toString());
              localObject = Boolean.valueOf(true);
            }
          }
        }
        if (((Boolean)localObject).booleanValue())
        {
          this.x = Long.valueOf(Long.MAX_VALUE);
          v.I().c("AutoWifiSuspendedTicks", this.x.longValue());
          v.I().c();
          i();
          v.j().a(Long.valueOf(0L));
          return;
        }
      }
    }
    catch (Exception localException)
    {
      cg.a(localException, Boolean.valueOf(false));
    }
  }
  
  public a z()
  {
    return new a();
  }
  
  public class a
  {
    public String a = "";
    public Long b = Long.valueOf(0L);
    public Long c = Long.valueOf(0L);
    
    public a() {}
  }
  
  public class b
  {
    public String a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public Double e = Double.valueOf(0.0D);
    public Double f = Double.valueOf(0.0D);
    public Double g = Double.valueOf(0.0D);
    public Double h = Double.valueOf(0.0D);
    public Long i = Long.valueOf(0L);
    public Long j = Long.valueOf(0L);
    
    public b() {}
  }
}
