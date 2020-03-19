package com.bandwidthx.library;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

public class cg
{
  private static ch a;
  private static String b = "";
  private static HashMap<Integer, String> c = new HashMap();
  private static Long d = Long.valueOf(0L);
  private static LinkedList<a> e = new LinkedList();
  private static Boolean f = Boolean.valueOf(false);
  private static String g = "BANDWIDTHX";
  private static Boolean h = Boolean.valueOf(false);
  private static LinkedList<String> i = new LinkedList();
  private static FileOutputStream j;
  private static OutputStreamWriter k;
  private static Long l = Long.valueOf(0L);
  private static Long m = Long.valueOf(86400000L);
  private static Long n = Long.valueOf(0L);
  private static Long o = Long.valueOf(5L);
  private static Boolean p = Boolean.valueOf(false);
  private static Boolean q = Boolean.valueOf(false);
  private static String r = "";
  
  public cg(ch paramCh)
  {
    try
    {
      a = paramCh;
      return;
    }
    catch (Exception paramCh)
    {
      a(paramCh);
    }
  }
  
  public static String a(Boolean paramBoolean)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localObject3 = a.P().a(Long.valueOf(0L));
    for (;;)
    {
      try
      {
        localObject1 = ((String)localObject3).substring(((String)localObject3).indexOf("//") + 2);
      }
      catch (Throwable localThrowable1)
      {
        Object localObject1;
        StringBuilder localStringBuilder2;
        Object localObject2 = localObject3;
        continue;
      }
      try
      {
        localObject3 = ((String)localObject1).substring(0, ((String)localObject1).indexOf("/"));
        localObject1 = localObject3;
      }
      catch (Throwable localThrowable2) {}
    }
    localObject3 = Calendar.getInstance();
    localObject3 = Long.valueOf(Long.valueOf((((Calendar)localObject3).get(15) + ((Calendar)localObject3).get(16)) / 1000L).longValue() / 3600L);
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Date: ");
    localStringBuilder2.append(bg.c(fi.f(), Boolean.valueOf(false)));
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("\r\nBuild: ");
    localStringBuilder2.append(ac.g());
    localStringBuilder2.append(" ");
    localStringBuilder2.append(ac.i());
    localStringBuilder2.append(".");
    localStringBuilder2.append(ac.h());
    localStringBuilder2.append(" ");
    localStringBuilder2.append(ac.j());
    localStringBuilder1.append(localStringBuilder2.toString());
    try
    {
      localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("\r\nApp Token: ");
      localStringBuilder2.append(a.P().c());
      localStringBuilder1.append(localStringBuilder2.toString());
    }
    catch (Throwable localThrowable3)
    {
      for (;;) {}
    }
    localStringBuilder1.append("\r\nToken: None");
    if (!paramBoolean.booleanValue())
    {
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nApps: ");
      paramBoolean.append(j());
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nUser: ");
      paramBoolean.append(a.U().f());
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nManufacturer: ");
      paramBoolean.append(Build.MANUFACTURER);
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nModel: ");
      paramBoolean.append(Build.MODEL);
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nVersion: Android ");
      paramBoolean.append(Build.VERSION.RELEASE);
      localStringBuilder1.append(paramBoolean.toString());
      localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("\r\nTime Zone: GMT");
      if (((Long)localObject3).longValue() >= 0L) {
        paramBoolean = "+";
      } else {
        paramBoolean = "";
      }
      localStringBuilder2.append(paramBoolean);
      localStringBuilder2.append(((Long)localObject3).toString());
      localStringBuilder1.append(localStringBuilder2.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nLocale: ");
      paramBoolean.append(ac.r());
      paramBoolean.append(" (setting ");
      paramBoolean.append(Locale.getDefault().toString());
      paramBoolean.append(")");
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nSIM Operator: ");
      paramBoolean.append(a.F().g());
      paramBoolean.append(" ");
      paramBoolean.append(a.F().h());
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nIMSI: ");
      paramBoolean.append(a.F().k());
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nPhone Number: ");
      paramBoolean.append(a.F().o());
      paramBoolean.append(" (SIM: ");
      paramBoolean.append(a.F().n());
      paramBoolean.append(") (HOST: ");
      paramBoolean.append(a.F().m());
      paramBoolean.append(")");
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nIMEI: ");
      paramBoolean.append(a.F().j());
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nWifi MAC: ");
      paramBoolean.append(a.X().f());
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nInstall ID: ");
      paramBoolean.append(a.i().c());
      localStringBuilder1.append(paramBoolean.toString());
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("\r\nPermissions: Phone ");
      if (a.F().c().booleanValue()) {
        paramBoolean = "allowed";
      } else {
        paramBoolean = "NOT allowed";
      }
      ((StringBuilder)localObject3).append(paramBoolean);
      ((StringBuilder)localObject3).append(", Wifi ");
      if (a.X().w().booleanValue()) {
        paramBoolean = "allowed";
      } else {
        paramBoolean = "NOT allowed";
      }
      ((StringBuilder)localObject3).append(paramBoolean);
      localStringBuilder1.append(((StringBuilder)localObject3).toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nServices: ");
      paramBoolean.append(a.w().c());
      localStringBuilder1.append(paramBoolean.toString());
      paramBoolean = new StringBuilder();
      paramBoolean.append("\r\nServer: ");
      paramBoolean.append((String)localObject1);
      localStringBuilder1.append(paramBoolean.toString());
    }
    return localStringBuilder1.toString();
  }
  
  /* Error */
  private static String a(String paramString, OutputStreamWriter paramOutputStreamWriter)
  {
    // Byte code:
    //   0: new 105	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   7: astore 6
    //   9: aload_0
    //   10: invokevirtual 327	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   13: astore 8
    //   15: aload 8
    //   17: ldc_w 329
    //   20: invokevirtual 333	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   23: astore 9
    //   25: aload 6
    //   27: astore 5
    //   29: new 335	java/util/ArrayList
    //   32: dup
    //   33: invokespecial 336	java/util/ArrayList:<init>	()V
    //   36: astore 7
    //   38: aload 6
    //   40: astore 5
    //   42: invokestatic 337	com/bandwidthx/library/cg:i	()Ljava/lang/String;
    //   45: astore_0
    //   46: aload 6
    //   48: astore 5
    //   50: new 105	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   57: astore 10
    //   59: aload 6
    //   61: astore 5
    //   63: aload 10
    //   65: aload_0
    //   66: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: pop
    //   70: aload 6
    //   72: astore 5
    //   74: aload 10
    //   76: ldc_w 339
    //   79: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: aload 6
    //   85: astore 5
    //   87: aload 7
    //   89: aload 10
    //   91: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: invokevirtual 343	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   97: pop
    //   98: aload 6
    //   100: astore 5
    //   102: new 105	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   109: astore 10
    //   111: aload 6
    //   113: astore 5
    //   115: aload 10
    //   117: aload_0
    //   118: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: pop
    //   122: aload 6
    //   124: astore 5
    //   126: aload 10
    //   128: ldc_w 345
    //   131: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload 6
    //   137: astore 5
    //   139: aload 7
    //   141: aload 10
    //   143: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: invokevirtual 343	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   149: pop
    //   150: aload 6
    //   152: astore 5
    //   154: new 105	java/lang/StringBuilder
    //   157: dup
    //   158: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   161: astore 10
    //   163: aload 6
    //   165: astore 5
    //   167: aload 10
    //   169: aload_0
    //   170: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload 6
    //   176: astore 5
    //   178: aload 10
    //   180: ldc_w 347
    //   183: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: aload 6
    //   189: astore 5
    //   191: aload 7
    //   193: aload 10
    //   195: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   198: invokevirtual 343	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   201: pop
    //   202: aload 6
    //   204: astore 5
    //   206: new 105	java/lang/StringBuilder
    //   209: dup
    //   210: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   213: astore 10
    //   215: aload 6
    //   217: astore 5
    //   219: aload 10
    //   221: aload_0
    //   222: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: aload 6
    //   228: astore 5
    //   230: aload 10
    //   232: ldc_w 349
    //   235: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: pop
    //   239: aload 6
    //   241: astore 5
    //   243: aload 7
    //   245: aload 10
    //   247: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   250: invokevirtual 343	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   253: pop
    //   254: aload 6
    //   256: astore 5
    //   258: aload 7
    //   260: invokevirtual 353	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   263: astore_0
    //   264: iconst_0
    //   265: istore_2
    //   266: aload 6
    //   268: astore 5
    //   270: aload_0
    //   271: invokeinterface 358 1 0
    //   276: ifeq +54 -> 330
    //   279: aload 6
    //   281: astore 5
    //   283: new 360	java/io/File
    //   286: dup
    //   287: aload_0
    //   288: invokeinterface 364 1 0
    //   293: checkcast 121	java/lang/String
    //   296: invokespecial 367	java/io/File:<init>	(Ljava/lang/String;)V
    //   299: astore 10
    //   301: aload 6
    //   303: astore 5
    //   305: aload 10
    //   307: invokevirtual 370	java/io/File:exists	()Z
    //   310: ifeq -44 -> 266
    //   313: aload 6
    //   315: astore 5
    //   317: iload_2
    //   318: i2l
    //   319: aload 10
    //   321: invokevirtual 373	java/io/File:length	()J
    //   324: ladd
    //   325: l2i
    //   326: istore_2
    //   327: goto -61 -> 266
    //   330: aload 6
    //   332: astore 5
    //   334: new 105	java/lang/StringBuilder
    //   337: dup
    //   338: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   341: astore_0
    //   342: aload 6
    //   344: astore 5
    //   346: aload_0
    //   347: ldc_w 375
    //   350: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: pop
    //   354: aload 6
    //   356: astore 5
    //   358: aload_0
    //   359: iload_2
    //   360: invokestatic 379	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   363: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   366: pop
    //   367: aload 6
    //   369: astore 5
    //   371: aload_0
    //   372: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   375: invokestatic 381	com/bandwidthx/library/cg:b	(Ljava/lang/String;)V
    //   378: aload 6
    //   380: astore_0
    //   381: aload_1
    //   382: ifnonnull +16 -> 398
    //   385: aload 6
    //   387: astore 5
    //   389: new 105	java/lang/StringBuilder
    //   392: dup
    //   393: iload_2
    //   394: invokespecial 384	java/lang/StringBuilder:<init>	(I)V
    //   397: astore_0
    //   398: aload_0
    //   399: astore 5
    //   401: aload 7
    //   403: invokevirtual 353	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   406: astore 10
    //   408: aload_0
    //   409: astore 5
    //   411: aload_0
    //   412: astore 6
    //   414: aload 10
    //   416: invokeinterface 358 1 0
    //   421: ifeq +497 -> 918
    //   424: aload_0
    //   425: astore 5
    //   427: new 360	java/io/File
    //   430: dup
    //   431: aload 10
    //   433: invokeinterface 364 1 0
    //   438: checkcast 121	java/lang/String
    //   441: invokespecial 367	java/io/File:<init>	(Ljava/lang/String;)V
    //   444: astore 6
    //   446: aload_0
    //   447: astore 5
    //   449: aload 6
    //   451: invokevirtual 370	java/io/File:exists	()Z
    //   454: istore 4
    //   456: iload 4
    //   458: ifeq -50 -> 408
    //   461: new 386	java/io/FileReader
    //   464: dup
    //   465: aload 6
    //   467: invokespecial 389	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   470: astore 7
    //   472: new 391	java/io/BufferedReader
    //   475: dup
    //   476: aload 7
    //   478: invokespecial 394	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   481: astore 5
    //   483: aload 8
    //   485: invokevirtual 397	java/lang/String:length	()I
    //   488: ifne +96 -> 584
    //   491: aload 5
    //   493: invokevirtual 400	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   496: astore 6
    //   498: aload 6
    //   500: ifnull +214 -> 714
    //   503: aload_1
    //   504: ifnonnull +42 -> 546
    //   507: new 105	java/lang/StringBuilder
    //   510: dup
    //   511: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   514: astore 11
    //   516: aload 11
    //   518: aload 6
    //   520: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: pop
    //   524: aload 11
    //   526: ldc_w 402
    //   529: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   532: pop
    //   533: aload_0
    //   534: aload 11
    //   536: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   539: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: pop
    //   543: goto -52 -> 491
    //   546: new 105	java/lang/StringBuilder
    //   549: dup
    //   550: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   553: astore 11
    //   555: aload 11
    //   557: aload 6
    //   559: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   562: pop
    //   563: aload 11
    //   565: ldc_w 402
    //   568: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: pop
    //   572: aload_1
    //   573: aload 11
    //   575: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   578: invokevirtual 407	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   581: goto -90 -> 491
    //   584: aload 5
    //   586: invokevirtual 400	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   589: astore 6
    //   591: aload 6
    //   593: ifnull +121 -> 714
    //   596: aload 9
    //   598: arraylength
    //   599: istore_3
    //   600: iconst_0
    //   601: istore_2
    //   602: iload_2
    //   603: iload_3
    //   604: if_icmpge -20 -> 584
    //   607: aload 9
    //   609: iload_2
    //   610: aaload
    //   611: astore 11
    //   613: aload 6
    //   615: invokevirtual 327	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   618: aload 11
    //   620: invokevirtual 411	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   623: ifeq +84 -> 707
    //   626: aload_1
    //   627: ifnonnull +42 -> 669
    //   630: new 105	java/lang/StringBuilder
    //   633: dup
    //   634: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   637: astore 11
    //   639: aload 11
    //   641: aload 6
    //   643: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   646: pop
    //   647: aload 11
    //   649: ldc_w 413
    //   652: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: pop
    //   656: aload_0
    //   657: aload 11
    //   659: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   665: pop
    //   666: goto -82 -> 584
    //   669: new 105	java/lang/StringBuilder
    //   672: dup
    //   673: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   676: astore 11
    //   678: aload 11
    //   680: aload 6
    //   682: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   685: pop
    //   686: aload 11
    //   688: ldc_w 413
    //   691: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   694: pop
    //   695: aload_1
    //   696: aload 11
    //   698: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   701: invokevirtual 407	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   704: goto -120 -> 584
    //   707: iload_2
    //   708: iconst_1
    //   709: iadd
    //   710: istore_2
    //   711: goto -109 -> 602
    //   714: aload 5
    //   716: ifnull +8 -> 724
    //   719: aload 5
    //   721: invokevirtual 416	java/io/BufferedReader:close	()V
    //   724: aload 7
    //   726: ifnull -318 -> 408
    //   729: aload 7
    //   731: invokevirtual 417	java/io/FileReader:close	()V
    //   734: goto -326 -> 408
    //   737: astore 6
    //   739: goto +20 -> 759
    //   742: astore 6
    //   744: aconst_null
    //   745: astore 5
    //   747: goto +12 -> 759
    //   750: astore 6
    //   752: aconst_null
    //   753: astore 7
    //   755: aload 7
    //   757: astore 5
    //   759: aload 5
    //   761: ifnull +8 -> 769
    //   764: aload 5
    //   766: invokevirtual 416	java/io/BufferedReader:close	()V
    //   769: aload 7
    //   771: ifnull +8 -> 779
    //   774: aload 7
    //   776: invokevirtual 417	java/io/FileReader:close	()V
    //   779: aload_0
    //   780: astore 5
    //   782: aload 6
    //   784: athrow
    //   785: astore_0
    //   786: new 105	java/lang/StringBuilder
    //   789: dup
    //   790: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   793: astore 6
    //   795: aload 6
    //   797: ldc_w 419
    //   800: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   803: pop
    //   804: aload 6
    //   806: aload_0
    //   807: invokestatic 422	com/bandwidthx/library/bg:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   810: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   813: pop
    //   814: aload 6
    //   816: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   819: iconst_0
    //   820: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   823: iconst_0
    //   824: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   827: aconst_null
    //   828: invokestatic 428	com/bandwidthx/library/cg:a	(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;)V
    //   831: aload_1
    //   832: ifnonnull +45 -> 877
    //   835: new 105	java/lang/StringBuilder
    //   838: dup
    //   839: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   842: astore_1
    //   843: aload_1
    //   844: ldc_w 419
    //   847: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   850: pop
    //   851: aload_1
    //   852: aload_0
    //   853: invokestatic 422	com/bandwidthx/library/bg:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   856: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   859: pop
    //   860: aload 5
    //   862: aload_1
    //   863: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   866: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   869: pop
    //   870: aload 5
    //   872: astore 6
    //   874: goto +44 -> 918
    //   877: new 105	java/lang/StringBuilder
    //   880: dup
    //   881: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   884: astore 6
    //   886: aload 6
    //   888: ldc_w 419
    //   891: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   894: pop
    //   895: aload 6
    //   897: aload_0
    //   898: invokestatic 422	com/bandwidthx/library/bg:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   901: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   904: pop
    //   905: aload_1
    //   906: aload 6
    //   908: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   911: invokevirtual 407	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   914: aload 5
    //   916: astore 6
    //   918: aload 6
    //   920: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   923: areturn
    //   924: astore 5
    //   926: goto -202 -> 724
    //   929: astore 5
    //   931: goto -523 -> 408
    //   934: astore 5
    //   936: goto -167 -> 769
    //   939: astore 5
    //   941: goto -162 -> 779
    //   944: astore_0
    //   945: aload 5
    //   947: astore 6
    //   949: goto -31 -> 918
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	952	0	paramString	String
    //   0	952	1	paramOutputStreamWriter	OutputStreamWriter
    //   265	446	2	i1	int
    //   599	6	3	i2	int
    //   454	3	4	bool	boolean
    //   27	888	5	localObject1	Object
    //   924	1	5	localThrowable1	Throwable
    //   929	1	5	localThrowable2	Throwable
    //   934	1	5	localThrowable3	Throwable
    //   939	7	5	localThrowable4	Throwable
    //   7	674	6	localObject2	Object
    //   737	1	6	localObject3	Object
    //   742	1	6	localObject4	Object
    //   750	33	6	localObject5	Object
    //   793	155	6	localObject6	Object
    //   36	739	7	localObject7	Object
    //   13	471	8	str	String
    //   23	585	9	arrayOfString	String[]
    //   57	375	10	localObject8	Object
    //   514	183	11	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   483	491	737	finally
    //   491	498	737	finally
    //   507	543	737	finally
    //   546	581	737	finally
    //   584	591	737	finally
    //   596	600	737	finally
    //   613	626	737	finally
    //   630	666	737	finally
    //   669	704	737	finally
    //   472	483	742	finally
    //   461	472	750	finally
    //   29	38	785	java/lang/Throwable
    //   42	46	785	java/lang/Throwable
    //   50	59	785	java/lang/Throwable
    //   63	70	785	java/lang/Throwable
    //   74	83	785	java/lang/Throwable
    //   87	98	785	java/lang/Throwable
    //   102	111	785	java/lang/Throwable
    //   115	122	785	java/lang/Throwable
    //   126	135	785	java/lang/Throwable
    //   139	150	785	java/lang/Throwable
    //   154	163	785	java/lang/Throwable
    //   167	174	785	java/lang/Throwable
    //   178	187	785	java/lang/Throwable
    //   191	202	785	java/lang/Throwable
    //   206	215	785	java/lang/Throwable
    //   219	226	785	java/lang/Throwable
    //   230	239	785	java/lang/Throwable
    //   243	254	785	java/lang/Throwable
    //   258	264	785	java/lang/Throwable
    //   270	279	785	java/lang/Throwable
    //   283	301	785	java/lang/Throwable
    //   305	313	785	java/lang/Throwable
    //   317	327	785	java/lang/Throwable
    //   334	342	785	java/lang/Throwable
    //   346	354	785	java/lang/Throwable
    //   358	367	785	java/lang/Throwable
    //   371	378	785	java/lang/Throwable
    //   389	398	785	java/lang/Throwable
    //   401	408	785	java/lang/Throwable
    //   414	424	785	java/lang/Throwable
    //   427	446	785	java/lang/Throwable
    //   449	456	785	java/lang/Throwable
    //   782	785	785	java/lang/Throwable
    //   719	724	924	java/lang/Throwable
    //   729	734	929	java/lang/Throwable
    //   764	769	934	java/lang/Throwable
    //   774	779	939	java/lang/Throwable
    //   786	831	944	java/lang/Throwable
    //   835	870	944	java/lang/Throwable
    //   877	914	944	java/lang/Throwable
  }
  
  private static void a(OutputStreamWriter paramOutputStreamWriter)
  {
    try
    {
      a(paramOutputStreamWriter, "### INFORMATION ############################################################\r\n\r\n");
      a(paramOutputStreamWriter, a(Boolean.valueOf(false)));
      a(paramOutputStreamWriter, "\r\n\r\n\r\n### VISITEDS ###############################################################\r\n\r\n");
      a(paramOutputStreamWriter, a.ax().a(a.ax().o()));
      a(paramOutputStreamWriter, "\r\n\r\n\r\n### PREAPPROVEDS ##############################################################\r\n\r\n");
      a(paramOutputStreamWriter, a.ar().a(a.ar().l(), Boolean.valueOf(true), Boolean.valueOf(true), ""));
      a(paramOutputStreamWriter, "\r\n\r\n\r\n### FREQUENTS ##############################################################\r\n\r\n");
      a(paramOutputStreamWriter, a.ap().a(a.ap().n(), Boolean.valueOf(false)));
      a(paramOutputStreamWriter, "\r\n\r\n\r\n### BLACKLISTEDS ###########################################################\r\n\r\n");
      a(paramOutputStreamWriter, a.af().b(a.af().l(), Boolean.valueOf(false)));
      a(paramOutputStreamWriter, "\r\n\r\n\r\n### ASSETS ###############################################################\r\n\r\n");
      a(paramOutputStreamWriter, a.ae().b(a.ae().l()));
      paramOutputStreamWriter.write("\r\n\r\n\r\n### TRADERS ###############################################################\r\n\r\n");
      paramOutputStreamWriter.write(a.aw().a(a.aw().l(), Boolean.valueOf(false)));
      paramOutputStreamWriter.write("\r\n\r\n\r\n### CAPTIVES ###############################################################\r\n\r\n");
      paramOutputStreamWriter.write(a.n().c().a(""));
      a(paramOutputStreamWriter, "\r\n\r\n\r\n### THREADS ###############################################################\r\n\r\n");
      a(paramOutputStreamWriter, k());
      paramOutputStreamWriter.write("\r\n\r\n\r\n### LOG ####################################################################\r\n\r\n");
      b(paramOutputStreamWriter);
      return;
    }
    catch (Throwable paramOutputStreamWriter)
    {
      a(paramOutputStreamWriter);
    }
  }
  
  private static void a(OutputStreamWriter paramOutputStreamWriter, String paramString)
    throws IOException
  {
    paramOutputStreamWriter.write(paramString);
    for (;;)
    {
      paramOutputStreamWriter = paramString;
      if (!paramString.startsWith(" \r\n")) {
        break;
      }
      paramString = paramString.substring(3);
    }
    for (;;)
    {
      paramString = paramOutputStreamWriter;
      if (!paramOutputStreamWriter.startsWith("\r\n")) {
        break;
      }
      paramOutputStreamWriter = paramOutputStreamWriter.substring(2);
    }
    for (;;)
    {
      paramOutputStreamWriter = paramString;
      if (!paramString.endsWith("\r\n")) {
        break;
      }
      paramString = paramString.substring(0, paramString.length() - 2);
    }
    for (;;)
    {
      paramString = paramOutputStreamWriter;
      if (!paramOutputStreamWriter.contains("\r\n\r\n")) {
        break;
      }
      paramOutputStreamWriter = paramOutputStreamWriter.replace("\r\n\r\n", "\r\n");
    }
    while (paramString.contains("\r\n")) {
      paramString = paramString.replace("\r\n", "\n");
    }
    c(paramString);
  }
  
  public static void a(Long paramLong)
  {
    d = Long.valueOf(fi.f().longValue() + paramLong.longValue());
  }
  
  public static void a(String paramString)
  {
    label239:
    for (;;)
    {
      try
      {
        synchronized (c)
        {
          Integer localInteger3 = Integer.valueOf(Process.myTid());
          if (c.get(localInteger3) == null)
          {
            Integer localInteger1 = Integer.valueOf(1);
            Integer localInteger2 = localInteger1;
            StringBuilder localStringBuilder;
            try
            {
              Iterator localIterator = c.entrySet().iterator();
              localInteger2 = localInteger1;
              Object localObject = localInteger1;
              if (localIterator.hasNext())
              {
                localInteger2 = localInteger1;
                localObject = (String)((Map.Entry)localIterator.next()).getValue();
                localInteger2 = localInteger1;
                if (((String)localObject).length() > 0)
                {
                  localInteger2 = localInteger1;
                  if (Character.isDigit(((String)localObject).charAt(((String)localObject).length() - 1)))
                  {
                    localInteger2 = localInteger1;
                    localObject = ((String)localObject).substring(0, ((String)localObject).length() - 1);
                    continue;
                  }
                }
                localInteger2 = localInteger1;
                if (!((String)localObject).equals(paramString)) {
                  continue;
                }
                localInteger2 = localInteger1;
                int i1 = localInteger1.intValue();
                localInteger1 = Integer.valueOf(i1 + 1);
                continue;
              }
              paramString = ((Integer)localObject).toString();
            }
            catch (Exception localException)
            {
              a(localException, Boolean.valueOf(false));
              localObject = localInteger2;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append(paramString);
              if (((Integer)localObject).intValue() <= 1) {
                break label239;
              }
            }
            localStringBuilder.append(paramString);
            paramString = localStringBuilder.toString();
            c.put(localInteger3, paramString);
          }
          return;
        }
        paramString = "";
      }
      catch (Throwable paramString)
      {
        return;
      }
    }
  }
  
  public static void a(String paramString, Boolean paramBoolean)
  {
    a(paramString, paramBoolean, Integer.valueOf(0), null);
  }
  
  /* Error */
  public static void a(String paramString1, Boolean paramBoolean, Integer paramInteger, String paramString2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 602	com/bandwidthx/library/ch:e	()Landroid/content/Context;
    //   6: ifnull +938 -> 944
    //   9: invokestatic 559	android/os/Process:myTid	()I
    //   12: invokestatic 379	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   15: astore 4
    //   17: getstatic 50	com/bandwidthx/library/cg:c	Ljava/util/HashMap;
    //   20: invokestatic 559	android/os/Process:myTid	()I
    //   23: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   26: invokevirtual 562	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   29: ifnull +20 -> 49
    //   32: getstatic 50	com/bandwidthx/library/cg:c	Ljava/util/HashMap;
    //   35: invokestatic 559	android/os/Process:myTid	()I
    //   38: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   41: invokevirtual 562	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   44: checkcast 121	java/lang/String
    //   47: astore 4
    //   49: aload 4
    //   51: astore 6
    //   53: invokestatic 606	com/bandwidthx/library/cf:g	()Ljava/lang/Boolean;
    //   56: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   59: ifeq +36 -> 95
    //   62: new 105	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   69: astore 5
    //   71: aload 5
    //   73: aload 4
    //   75: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload 5
    //   81: ldc_w 608
    //   84: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload 5
    //   90: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: astore 6
    //   95: ldc 14
    //   97: astore 7
    //   99: invokestatic 602	com/bandwidthx/library/ch:e	()Landroid/content/Context;
    //   102: ldc_w 610
    //   105: invokevirtual 616	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   108: checkcast 618	android/net/wifi/WifiManager
    //   111: astore 10
    //   113: aload 10
    //   115: invokevirtual 621	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   118: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   121: astore 9
    //   123: aload 7
    //   125: astore 4
    //   127: aload 9
    //   129: astore 8
    //   131: aload 7
    //   133: astore 5
    //   135: aload 9
    //   137: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   140: ifeq +399 -> 539
    //   143: aload 7
    //   145: astore 5
    //   147: aload 10
    //   149: invokevirtual 625	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   152: astore 4
    //   154: aload 7
    //   156: astore 5
    //   158: aload 4
    //   160: invokevirtual 630	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   163: astore 11
    //   165: aload 7
    //   167: astore 5
    //   169: aload 4
    //   171: invokevirtual 633	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   174: astore 10
    //   176: aload 7
    //   178: astore 5
    //   180: aload 4
    //   182: invokevirtual 637	android/net/wifi/WifiInfo:getSupplicantState	()Landroid/net/wifi/SupplicantState;
    //   185: astore 12
    //   187: aload 7
    //   189: astore 4
    //   191: aload 9
    //   193: astore 8
    //   195: aload 11
    //   197: ifnull +342 -> 539
    //   200: aload 7
    //   202: astore 4
    //   204: aload 9
    //   206: astore 8
    //   208: aload 10
    //   210: ifnull +329 -> 539
    //   213: aload 7
    //   215: astore 4
    //   217: aload 9
    //   219: astore 8
    //   221: aload 12
    //   223: ifnull +316 -> 539
    //   226: aload 7
    //   228: astore 4
    //   230: aload 9
    //   232: astore 8
    //   234: aload 7
    //   236: astore 5
    //   238: aload 11
    //   240: invokevirtual 397	java/lang/String:length	()I
    //   243: ifle +296 -> 539
    //   246: aload 7
    //   248: astore 4
    //   250: aload 9
    //   252: astore 8
    //   254: aload 7
    //   256: astore 5
    //   258: aload 10
    //   260: invokevirtual 397	java/lang/String:length	()I
    //   263: ifle +276 -> 539
    //   266: aload 7
    //   268: astore 5
    //   270: aload 12
    //   272: getstatic 643	android/net/wifi/SupplicantState:ASSOCIATED	Landroid/net/wifi/SupplicantState;
    //   275: if_acmpeq +23 -> 298
    //   278: aload 7
    //   280: astore 4
    //   282: aload 9
    //   284: astore 8
    //   286: aload 7
    //   288: astore 5
    //   290: aload 12
    //   292: getstatic 646	android/net/wifi/SupplicantState:COMPLETED	Landroid/net/wifi/SupplicantState;
    //   295: if_acmpne +244 -> 539
    //   298: aload 10
    //   300: astore 4
    //   302: aload 7
    //   304: astore 5
    //   306: aload 10
    //   308: ldc_w 648
    //   311: invokevirtual 541	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   314: ifeq +22 -> 336
    //   317: aload 7
    //   319: astore 5
    //   321: aload 10
    //   323: iconst_1
    //   324: aload 10
    //   326: invokevirtual 397	java/lang/String:length	()I
    //   329: iconst_1
    //   330: isub
    //   331: invokevirtual 134	java/lang/String:substring	(II)Ljava/lang/String;
    //   334: astore 4
    //   336: invokestatic 602	com/bandwidthx/library/ch:e	()Landroid/content/Context;
    //   339: ldc_w 650
    //   342: invokevirtual 616	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   345: checkcast 652	android/net/ConnectivityManager
    //   348: astore 5
    //   350: aload 5
    //   352: ifnull +122 -> 474
    //   355: aload 5
    //   357: iconst_1
    //   358: invokevirtual 656	android/net/ConnectivityManager:getNetworkInfo	(I)Landroid/net/NetworkInfo;
    //   361: astore 5
    //   363: aload 5
    //   365: ifnull +60 -> 425
    //   368: aload 5
    //   370: invokevirtual 661	android/net/NetworkInfo:isAvailable	()Z
    //   373: ifeq +52 -> 425
    //   376: new 105	java/lang/StringBuilder
    //   379: dup
    //   380: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   383: astore 5
    //   385: aload 5
    //   387: ldc_w 648
    //   390: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   393: pop
    //   394: aload 5
    //   396: aload 4
    //   398: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: pop
    //   402: aload 5
    //   404: ldc_w 648
    //   407: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: pop
    //   411: aload 5
    //   413: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   416: astore 5
    //   418: aload 5
    //   420: astore 4
    //   422: goto +52 -> 474
    //   425: new 105	java/lang/StringBuilder
    //   428: dup
    //   429: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   432: astore 5
    //   434: aload 5
    //   436: ldc_w 663
    //   439: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: pop
    //   443: aload 5
    //   445: aload 4
    //   447: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   450: pop
    //   451: aload 5
    //   453: ldc_w 663
    //   456: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: pop
    //   460: aload 5
    //   462: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   465: astore 5
    //   467: aload 5
    //   469: astore 4
    //   471: goto +3 -> 474
    //   474: aload 4
    //   476: astore 5
    //   478: new 105	java/lang/StringBuilder
    //   481: dup
    //   482: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   485: astore 7
    //   487: aload 4
    //   489: astore 5
    //   491: aload 7
    //   493: ldc -76
    //   495: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: pop
    //   499: aload 4
    //   501: astore 5
    //   503: aload 7
    //   505: aload 4
    //   507: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: pop
    //   511: aload 4
    //   513: astore 5
    //   515: aload 7
    //   517: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   520: astore 4
    //   522: aload 9
    //   524: astore 8
    //   526: goto +13 -> 539
    //   529: iconst_0
    //   530: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   533: astore 8
    //   535: aload 7
    //   537: astore 4
    //   539: ldc 14
    //   541: astore 7
    //   543: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   546: invokevirtual 667	com/bandwidthx/library/ch:s	()Lcom/bandwidthx/library/ax;
    //   549: invokevirtual 671	com/bandwidthx/library/ax:F	()V
    //   552: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   555: invokevirtual 674	com/bandwidthx/library/ch:j	()Lcom/bandwidthx/library/BxApproval;
    //   558: iconst_0
    //   559: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   562: invokevirtual 679	com/bandwidthx/library/BxApproval:c	(Ljava/lang/Boolean;)Lcom/bandwidthx/library/BxApproval$ConnectedState;
    //   565: astore 5
    //   567: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   570: invokevirtual 667	com/bandwidthx/library/ch:s	()Lcom/bandwidthx/library/ax;
    //   573: invokevirtual 682	com/bandwidthx/library/ax:G	()V
    //   576: new 105	java/lang/StringBuilder
    //   579: dup
    //   580: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   583: astore 9
    //   585: aload 9
    //   587: ldc -76
    //   589: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   592: pop
    //   593: aload 8
    //   595: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   598: ifeq +13 -> 611
    //   601: aload 5
    //   603: invokevirtual 685	com/bandwidthx/library/BxApproval$ConnectedState:toString	()Ljava/lang/String;
    //   606: astore 5
    //   608: goto +47 -> 655
    //   611: new 105	java/lang/StringBuilder
    //   614: dup
    //   615: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   618: astore 8
    //   620: aload 8
    //   622: ldc_w 687
    //   625: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: pop
    //   629: aload 8
    //   631: aload 5
    //   633: invokevirtual 685	com/bandwidthx/library/BxApproval$ConnectedState:toString	()Ljava/lang/String;
    //   636: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   639: pop
    //   640: aload 8
    //   642: ldc -2
    //   644: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   647: pop
    //   648: aload 8
    //   650: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   653: astore 5
    //   655: aload 9
    //   657: aload 5
    //   659: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   662: pop
    //   663: aload 9
    //   665: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   668: astore 5
    //   670: invokestatic 689	com/bandwidthx/library/cf:h	()Ljava/lang/Boolean;
    //   673: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   676: ifeq +43 -> 719
    //   679: new 105	java/lang/StringBuilder
    //   682: dup
    //   683: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   686: astore 7
    //   688: aload 7
    //   690: aload 5
    //   692: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   695: pop
    //   696: aload 7
    //   698: ldc_w 608
    //   701: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   704: pop
    //   705: aload 7
    //   707: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   710: astore 7
    //   712: aload 7
    //   714: astore 5
    //   716: goto +3 -> 719
    //   719: invokestatic 163	com/bandwidthx/library/fi:f	()Ljava/lang/Long;
    //   722: invokevirtual 150	java/lang/Long:longValue	()J
    //   725: getstatic 58	com/bandwidthx/library/cg:d	Ljava/lang/Long;
    //   728: invokevirtual 150	java/lang/Long:longValue	()J
    //   731: lcmp
    //   732: ifgt +100 -> 832
    //   735: invokestatic 163	com/bandwidthx/library/fi:f	()Ljava/lang/Long;
    //   738: invokevirtual 150	java/lang/Long:longValue	()J
    //   741: getstatic 58	com/bandwidthx/library/cg:d	Ljava/lang/Long;
    //   744: invokevirtual 150	java/lang/Long:longValue	()J
    //   747: ldc2_w 690
    //   750: lsub
    //   751: lcmp
    //   752: ifge +6 -> 758
    //   755: goto +77 -> 832
    //   758: new 8	com/bandwidthx/library/cg$a
    //   761: dup
    //   762: invokespecial 692	com/bandwidthx/library/cg$a:<init>	()V
    //   765: astore 7
    //   767: aload 7
    //   769: aload 6
    //   771: putfield 694	com/bandwidthx/library/cg$a:a	Ljava/lang/String;
    //   774: aload 7
    //   776: aload 4
    //   778: putfield 696	com/bandwidthx/library/cg$a:b	Ljava/lang/String;
    //   781: aload 7
    //   783: aload 5
    //   785: putfield 698	com/bandwidthx/library/cg$a:c	Ljava/lang/String;
    //   788: aload 7
    //   790: aload_0
    //   791: putfield 700	com/bandwidthx/library/cg$a:d	Ljava/lang/String;
    //   794: aload 7
    //   796: aload_1
    //   797: putfield 702	com/bandwidthx/library/cg$a:e	Ljava/lang/Boolean;
    //   800: aload 7
    //   802: aload_2
    //   803: putfield 705	com/bandwidthx/library/cg$a:f	Ljava/lang/Integer;
    //   806: aload 7
    //   808: aload_3
    //   809: putfield 707	com/bandwidthx/library/cg$a:g	Ljava/lang/String;
    //   812: aload 7
    //   814: invokestatic 163	com/bandwidthx/library/fi:f	()Ljava/lang/Long;
    //   817: putfield 709	com/bandwidthx/library/cg$a:h	Ljava/lang/Long;
    //   820: getstatic 63	com/bandwidthx/library/cg:e	Ljava/util/LinkedList;
    //   823: aload 7
    //   825: invokevirtual 710	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   828: pop
    //   829: goto +115 -> 944
    //   832: getstatic 63	com/bandwidthx/library/cg:e	Ljava/util/LinkedList;
    //   835: invokevirtual 713	java/util/LinkedList:poll	()Ljava/lang/Object;
    //   838: checkcast 8	com/bandwidthx/library/cg$a
    //   841: astore 7
    //   843: aload 7
    //   845: ifnull +60 -> 905
    //   848: aload 7
    //   850: getfield 694	com/bandwidthx/library/cg$a:a	Ljava/lang/String;
    //   853: aload 7
    //   855: getfield 696	com/bandwidthx/library/cg$a:b	Ljava/lang/String;
    //   858: aload 7
    //   860: getfield 698	com/bandwidthx/library/cg$a:c	Ljava/lang/String;
    //   863: aload 7
    //   865: getfield 700	com/bandwidthx/library/cg$a:d	Ljava/lang/String;
    //   868: aload 7
    //   870: getfield 702	com/bandwidthx/library/cg$a:e	Ljava/lang/Boolean;
    //   873: aload 7
    //   875: getfield 705	com/bandwidthx/library/cg$a:f	Ljava/lang/Integer;
    //   878: aload 7
    //   880: getfield 707	com/bandwidthx/library/cg$a:g	Ljava/lang/String;
    //   883: aload 7
    //   885: getfield 709	com/bandwidthx/library/cg$a:h	Ljava/lang/Long;
    //   888: invokestatic 716	com/bandwidthx/library/cg:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V
    //   891: getstatic 63	com/bandwidthx/library/cg:e	Ljava/util/LinkedList;
    //   894: invokevirtual 713	java/util/LinkedList:poll	()Ljava/lang/Object;
    //   897: checkcast 8	com/bandwidthx/library/cg$a
    //   900: astore 7
    //   902: goto -59 -> 843
    //   905: aload 6
    //   907: aload 4
    //   909: aload 5
    //   911: aload_0
    //   912: aload_1
    //   913: aload_2
    //   914: aload_3
    //   915: invokestatic 163	com/bandwidthx/library/fi:f	()Ljava/lang/Long;
    //   918: invokestatic 716	com/bandwidthx/library/cg:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V
    //   921: goto +23 -> 944
    //   924: astore_0
    //   925: goto +14 -> 939
    //   928: astore_0
    //   929: aload_0
    //   930: invokestatic 422	com/bandwidthx/library/bg:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   933: invokestatic 552	com/bandwidthx/library/cg:c	(Ljava/lang/String;)V
    //   936: goto +8 -> 944
    //   939: ldc 2
    //   941: monitorexit
    //   942: aload_0
    //   943: athrow
    //   944: ldc 2
    //   946: monitorexit
    //   947: return
    //   948: astore 4
    //   950: goto -421 -> 529
    //   953: astore 4
    //   955: aload 5
    //   957: astore 4
    //   959: aload 9
    //   961: astore 8
    //   963: goto -424 -> 539
    //   966: astore 5
    //   968: goto -494 -> 474
    //   971: astore 5
    //   973: aload 7
    //   975: astore 5
    //   977: goto -258 -> 719
    //   980: astore 7
    //   982: goto -263 -> 719
    //   985: astore_0
    //   986: goto -42 -> 944
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	989	0	paramString1	String
    //   0	989	1	paramBoolean	Boolean
    //   0	989	2	paramInteger	Integer
    //   0	989	3	paramString2	String
    //   15	893	4	localObject1	Object
    //   948	1	4	localThrowable1	Throwable
    //   953	1	4	localThrowable2	Throwable
    //   957	1	4	localObject2	Object
    //   69	887	5	localObject3	Object
    //   966	1	5	localThrowable3	Throwable
    //   971	1	5	localThrowable4	Throwable
    //   975	1	5	localObject4	Object
    //   51	855	6	localObject5	Object
    //   97	877	7	localObject6	Object
    //   980	1	7	localThrowable5	Throwable
    //   129	833	8	localObject7	Object
    //   121	839	9	localObject8	Object
    //   111	214	10	localObject9	Object
    //   163	76	11	str	String
    //   185	106	12	localSupplicantState	android.net.wifi.SupplicantState
    // Exception table:
    //   from	to	target	type
    //   3	17	924	finally
    //   17	49	924	finally
    //   53	95	924	finally
    //   99	113	924	finally
    //   113	123	924	finally
    //   135	143	924	finally
    //   147	154	924	finally
    //   158	165	924	finally
    //   169	176	924	finally
    //   180	187	924	finally
    //   238	246	924	finally
    //   258	266	924	finally
    //   270	278	924	finally
    //   290	298	924	finally
    //   306	317	924	finally
    //   321	336	924	finally
    //   336	350	924	finally
    //   355	363	924	finally
    //   368	418	924	finally
    //   425	467	924	finally
    //   478	487	924	finally
    //   491	499	924	finally
    //   503	511	924	finally
    //   515	522	924	finally
    //   543	608	924	finally
    //   611	655	924	finally
    //   655	670	924	finally
    //   670	712	924	finally
    //   719	755	924	finally
    //   758	829	924	finally
    //   832	843	924	finally
    //   848	902	924	finally
    //   905	921	924	finally
    //   929	936	924	finally
    //   3	17	928	java/lang/Throwable
    //   17	49	928	java/lang/Throwable
    //   53	95	928	java/lang/Throwable
    //   99	113	928	java/lang/Throwable
    //   719	755	928	java/lang/Throwable
    //   758	829	928	java/lang/Throwable
    //   832	843	928	java/lang/Throwable
    //   848	902	928	java/lang/Throwable
    //   905	921	928	java/lang/Throwable
    //   113	123	948	java/lang/Throwable
    //   135	143	953	java/lang/Throwable
    //   147	154	953	java/lang/Throwable
    //   158	165	953	java/lang/Throwable
    //   169	176	953	java/lang/Throwable
    //   180	187	953	java/lang/Throwable
    //   238	246	953	java/lang/Throwable
    //   258	266	953	java/lang/Throwable
    //   270	278	953	java/lang/Throwable
    //   290	298	953	java/lang/Throwable
    //   306	317	953	java/lang/Throwable
    //   321	336	953	java/lang/Throwable
    //   478	487	953	java/lang/Throwable
    //   491	499	953	java/lang/Throwable
    //   503	511	953	java/lang/Throwable
    //   515	522	953	java/lang/Throwable
    //   336	350	966	java/lang/Throwable
    //   355	363	966	java/lang/Throwable
    //   368	418	966	java/lang/Throwable
    //   425	467	966	java/lang/Throwable
    //   543	608	971	java/lang/Throwable
    //   611	655	971	java/lang/Throwable
    //   655	670	971	java/lang/Throwable
    //   670	712	980	java/lang/Throwable
    //   929	936	985	java/lang/Throwable
  }
  
  public static void a(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, Boolean.valueOf(false));
  }
  
  public static void a(String paramString1, String paramString2, Boolean paramBoolean)
  {
    try
    {
      if (!p.booleanValue())
      {
        if (a.O().c("bx_syslog").equalsIgnoreCase("true")) {
          q = Boolean.valueOf(true);
        }
        p = Boolean.valueOf(true);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        try
        {
          if (q.booleanValue())
          {
            Log.i(paramString1, paramString2);
            return;
          }
          if ((paramBoolean.booleanValue()) || (ac.n().booleanValue()) || (a.U().e().booleanValue())) {
            Log.i(paramString1, paramString2);
          }
          return;
        }
        catch (Throwable paramString1) {}
        localThrowable = localThrowable;
      }
    }
  }
  
  /* Error */
  private static void a(String paramString1, String paramString2, String paramString3, String paramString4, Boolean paramBoolean, Integer paramInteger, String paramString5, Long paramLong)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 163	com/bandwidthx/library/fi:f	()Ljava/lang/Long;
    //   6: invokevirtual 150	java/lang/Long:longValue	()J
    //   9: invokestatic 749	java/lang/System:currentTimeMillis	()J
    //   12: lsub
    //   13: ldc2_w 145
    //   16: ldiv
    //   17: invokestatic 56	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   20: astore 9
    //   22: getstatic 70	com/bandwidthx/library/cg:f	Ljava/lang/Boolean;
    //   25: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   28: istore 8
    //   30: iload 8
    //   32: ifeq +7 -> 39
    //   35: ldc 2
    //   37: monitorexit
    //   38: return
    //   39: iconst_1
    //   40: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   43: putstatic 70	com/bandwidthx/library/cg:f	Ljava/lang/Boolean;
    //   46: new 105	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   53: astore 10
    //   55: aload 10
    //   57: ldc_w 751
    //   60: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: pop
    //   64: aload 10
    //   66: aload 7
    //   68: iconst_0
    //   69: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   72: invokestatic 753	com/bandwidthx/library/bg:b	(Ljava/lang/Long;Ljava/lang/Boolean;)Ljava/lang/String;
    //   75: invokevirtual 754	java/lang/String:toString	()Ljava/lang/String;
    //   78: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload 10
    //   84: ldc -76
    //   86: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: pop
    //   90: aload 10
    //   92: aload 7
    //   94: iconst_0
    //   95: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   98: invokestatic 756	com/bandwidthx/library/bg:a	(Ljava/lang/Long;Ljava/lang/Boolean;)Ljava/lang/String;
    //   101: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: aload 9
    //   107: invokevirtual 150	java/lang/Long:longValue	()J
    //   110: invokestatic 762	java/lang/Math:abs	(J)J
    //   113: lconst_1
    //   114: lcmp
    //   115: ifle +797 -> 912
    //   118: new 105	java/lang/StringBuilder
    //   121: dup
    //   122: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   125: astore 11
    //   127: aload 11
    //   129: ldc -76
    //   131: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload 11
    //   137: aload 9
    //   139: invokevirtual 239	java/lang/Long:toString	()Ljava/lang/String;
    //   142: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: aload 11
    //   148: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: astore 9
    //   153: goto +3 -> 156
    //   156: aload 10
    //   158: aload 9
    //   160: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload 10
    //   166: ldc_w 764
    //   169: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: pop
    //   173: aload 10
    //   175: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: astore 10
    //   180: aload 10
    //   182: astore 9
    //   184: invokestatic 767	com/bandwidthx/library/ch:b	()Lcom/bandwidthx/library/ch;
    //   187: ifnull +167 -> 354
    //   190: invokestatic 767	com/bandwidthx/library/ch:b	()Lcom/bandwidthx/library/ch;
    //   193: invokevirtual 318	com/bandwidthx/library/ch:w	()Lcom/bandwidthx/library/bc;
    //   196: ifnull +74 -> 270
    //   199: invokestatic 767	com/bandwidthx/library/ch:b	()Lcom/bandwidthx/library/ch;
    //   202: invokevirtual 318	com/bandwidthx/library/ch:w	()Lcom/bandwidthx/library/bc;
    //   205: invokevirtual 769	com/bandwidthx/library/bc:l	()Ljava/lang/Boolean;
    //   208: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   211: ifeq +59 -> 270
    //   214: new 105	java/lang/StringBuilder
    //   217: dup
    //   218: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   221: astore 9
    //   223: aload 9
    //   225: ldc_w 771
    //   228: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: aload 9
    //   234: aload 10
    //   236: iconst_1
    //   237: aload 10
    //   239: invokevirtual 397	java/lang/String:length	()I
    //   242: iconst_1
    //   243: isub
    //   244: invokevirtual 134	java/lang/String:substring	(II)Ljava/lang/String;
    //   247: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: pop
    //   251: aload 9
    //   253: ldc_w 773
    //   256: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: pop
    //   260: aload 9
    //   262: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   265: astore 9
    //   267: goto +87 -> 354
    //   270: aload 10
    //   272: astore 9
    //   274: invokestatic 767	com/bandwidthx/library/ch:b	()Lcom/bandwidthx/library/ch;
    //   277: invokevirtual 298	com/bandwidthx/library/ch:i	()Lcom/bandwidthx/library/ac;
    //   280: ifnull +74 -> 354
    //   283: aload 10
    //   285: astore 9
    //   287: invokestatic 767	com/bandwidthx/library/ch:b	()Lcom/bandwidthx/library/ch;
    //   290: invokevirtual 298	com/bandwidthx/library/ch:i	()Lcom/bandwidthx/library/ac;
    //   293: invokevirtual 775	com/bandwidthx/library/ac:o	()Ljava/lang/Boolean;
    //   296: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   299: ifne +55 -> 354
    //   302: new 105	java/lang/StringBuilder
    //   305: dup
    //   306: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   309: astore 9
    //   311: aload 9
    //   313: ldc_w 687
    //   316: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: pop
    //   320: aload 9
    //   322: aload 10
    //   324: iconst_1
    //   325: aload 10
    //   327: invokevirtual 397	java/lang/String:length	()I
    //   330: iconst_1
    //   331: isub
    //   332: invokevirtual 134	java/lang/String:substring	(II)Ljava/lang/String;
    //   335: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: pop
    //   339: aload 9
    //   341: ldc -2
    //   343: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: pop
    //   347: aload 9
    //   349: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: astore 9
    //   354: new 105	java/lang/StringBuilder
    //   357: dup
    //   358: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   361: astore 10
    //   363: aload 10
    //   365: aload 9
    //   367: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: pop
    //   371: aload 10
    //   373: ldc -76
    //   375: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: pop
    //   379: aload 10
    //   381: aload_0
    //   382: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   385: pop
    //   386: aload 10
    //   388: aload_1
    //   389: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: pop
    //   393: aload 10
    //   395: aload_2
    //   396: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: pop
    //   400: aload 10
    //   402: ldc_w 777
    //   405: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: pop
    //   409: aload 10
    //   411: aload_3
    //   412: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: pop
    //   416: aload 10
    //   418: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   421: astore_1
    //   422: aload 4
    //   424: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   427: ifeq +42 -> 469
    //   430: getstatic 74	com/bandwidthx/library/cg:i	Ljava/util/LinkedList;
    //   433: aload_1
    //   434: invokevirtual 710	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   437: pop
    //   438: getstatic 72	com/bandwidthx/library/cg:h	Ljava/lang/Boolean;
    //   441: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   444: ifne +25 -> 469
    //   447: iconst_1
    //   448: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   451: putstatic 72	com/bandwidthx/library/cg:h	Ljava/lang/Boolean;
    //   454: new 6	com/bandwidthx/library/cg$1
    //   457: dup
    //   458: invokespecial 778	com/bandwidthx/library/cg$1:<init>	()V
    //   461: iconst_0
    //   462: anewarray 780	java/lang/Void
    //   465: invokevirtual 784	com/bandwidthx/library/cg$1:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   468: pop
    //   469: aload_1
    //   470: astore_0
    //   471: aload 6
    //   473: ifnull +372 -> 845
    //   476: aload_1
    //   477: astore_0
    //   478: aload 6
    //   480: invokevirtual 397	java/lang/String:length	()I
    //   483: ifle +362 -> 845
    //   486: aload_1
    //   487: astore_0
    //   488: lconst_1
    //   489: invokestatic 56	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   492: invokestatic 789	com/bandwidthx/library/dq:a	(Ljava/lang/Long;)Ljava/lang/Boolean;
    //   495: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   498: ifne +347 -> 845
    //   501: aload_1
    //   502: astore_0
    //   503: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   506: invokevirtual 793	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   509: sipush 145
    //   512: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   515: invokevirtual 798	com/bandwidthx/library/eg:a	(Ljava/lang/Integer;)Ljava/lang/Boolean;
    //   518: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   521: ifeq +324 -> 845
    //   524: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   527: invokevirtual 298	com/bandwidthx/library/ch:i	()Lcom/bandwidthx/library/ac;
    //   530: pop
    //   531: aload_1
    //   532: astore_0
    //   533: invokestatic 799	com/bandwidthx/library/ac:f	()Ljava/lang/Long;
    //   536: invokevirtual 150	java/lang/Long:longValue	()J
    //   539: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   542: invokevirtual 793	com/bandwidthx/library/ch:Q	()Lcom/bandwidthx/library/eg;
    //   545: sipush 176
    //   548: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   551: invokevirtual 802	com/bandwidthx/library/eg:c	(Ljava/lang/Integer;)Ljava/lang/Long;
    //   554: invokevirtual 150	java/lang/Long:longValue	()J
    //   557: lcmp
    //   558: ifgt +287 -> 845
    //   561: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   564: invokevirtual 806	com/bandwidthx/library/ch:I	()Lcom/bandwidthx/library/dr;
    //   567: ldc_w 808
    //   570: ldc 14
    //   572: invokevirtual 813	com/bandwidthx/library/dr:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   575: astore_2
    //   576: aload_2
    //   577: ldc_w 815
    //   580: invokevirtual 735	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   583: ifeq +76 -> 659
    //   586: lconst_0
    //   587: invokestatic 56	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   590: putstatic 76	com/bandwidthx/library/cg:l	Ljava/lang/Long;
    //   593: lconst_0
    //   594: invokestatic 56	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   597: putstatic 82	com/bandwidthx/library/cg:n	Ljava/lang/Long;
    //   600: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   603: invokevirtual 806	com/bandwidthx/library/ch:I	()Lcom/bandwidthx/library/dr;
    //   606: ldc_w 817
    //   609: getstatic 76	com/bandwidthx/library/cg:l	Ljava/lang/Long;
    //   612: invokevirtual 150	java/lang/Long:longValue	()J
    //   615: invokevirtual 820	com/bandwidthx/library/dr:c	(Ljava/lang/String;J)V
    //   618: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   621: invokevirtual 806	com/bandwidthx/library/ch:I	()Lcom/bandwidthx/library/dr;
    //   624: ldc_w 822
    //   627: getstatic 82	com/bandwidthx/library/cg:n	Ljava/lang/Long;
    //   630: invokevirtual 150	java/lang/Long:longValue	()J
    //   633: invokevirtual 820	com/bandwidthx/library/dr:c	(Ljava/lang/String;J)V
    //   636: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   639: invokevirtual 806	com/bandwidthx/library/ch:I	()Lcom/bandwidthx/library/dr;
    //   642: ldc_w 808
    //   645: ldc 14
    //   647: invokevirtual 824	com/bandwidthx/library/dr:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   650: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   653: invokevirtual 806	com/bandwidthx/library/ch:I	()Lcom/bandwidthx/library/dr;
    //   656: invokevirtual 826	com/bandwidthx/library/dr:c	()V
    //   659: aload_2
    //   660: invokevirtual 397	java/lang/String:length	()I
    //   663: ifeq +15 -> 678
    //   666: aload_1
    //   667: astore_0
    //   668: aload_2
    //   669: invokestatic 828	com/bandwidthx/library/ac:e	()Ljava/lang/String;
    //   672: invokevirtual 735	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   675: ifne +170 -> 845
    //   678: getstatic 76	com/bandwidthx/library/cg:l	Ljava/lang/Long;
    //   681: invokevirtual 150	java/lang/Long:longValue	()J
    //   684: aload 7
    //   686: invokevirtual 150	java/lang/Long:longValue	()J
    //   689: getstatic 80	com/bandwidthx/library/cg:m	Ljava/lang/Long;
    //   692: invokevirtual 150	java/lang/Long:longValue	()J
    //   695: lsub
    //   696: lcmp
    //   697: ifge +15 -> 712
    //   700: aload 7
    //   702: putstatic 76	com/bandwidthx/library/cg:l	Ljava/lang/Long;
    //   705: lconst_0
    //   706: invokestatic 56	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   709: putstatic 82	com/bandwidthx/library/cg:n	Ljava/lang/Long;
    //   712: getstatic 82	com/bandwidthx/library/cg:n	Ljava/lang/Long;
    //   715: astore_0
    //   716: getstatic 82	com/bandwidthx/library/cg:n	Ljava/lang/Long;
    //   719: invokevirtual 150	java/lang/Long:longValue	()J
    //   722: lconst_1
    //   723: ladd
    //   724: invokestatic 56	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   727: putstatic 82	com/bandwidthx/library/cg:n	Ljava/lang/Long;
    //   730: getstatic 82	com/bandwidthx/library/cg:n	Ljava/lang/Long;
    //   733: invokevirtual 150	java/lang/Long:longValue	()J
    //   736: getstatic 86	com/bandwidthx/library/cg:o	Ljava/lang/Long;
    //   739: invokevirtual 150	java/lang/Long:longValue	()J
    //   742: lcmp
    //   743: ifgt +17 -> 760
    //   746: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   749: invokevirtual 832	com/bandwidthx/library/ch:an	()Lcom/bandwidthx/library/et;
    //   752: aload 5
    //   754: aload 6
    //   756: aload_1
    //   757: invokevirtual 837	com/bandwidthx/library/et:a	(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V
    //   760: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   763: invokevirtual 806	com/bandwidthx/library/ch:I	()Lcom/bandwidthx/library/dr;
    //   766: ldc_w 817
    //   769: getstatic 76	com/bandwidthx/library/cg:l	Ljava/lang/Long;
    //   772: invokevirtual 150	java/lang/Long:longValue	()J
    //   775: invokevirtual 820	com/bandwidthx/library/dr:c	(Ljava/lang/String;J)V
    //   778: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   781: invokevirtual 806	com/bandwidthx/library/ch:I	()Lcom/bandwidthx/library/dr;
    //   784: ldc_w 822
    //   787: getstatic 82	com/bandwidthx/library/cg:n	Ljava/lang/Long;
    //   790: invokevirtual 150	java/lang/Long:longValue	()J
    //   793: invokevirtual 820	com/bandwidthx/library/dr:c	(Ljava/lang/String;J)V
    //   796: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   799: invokevirtual 806	com/bandwidthx/library/ch:I	()Lcom/bandwidthx/library/dr;
    //   802: invokevirtual 826	com/bandwidthx/library/dr:c	()V
    //   805: aload_1
    //   806: astore_0
    //   807: goto +38 -> 845
    //   810: astore_0
    //   811: new 105	java/lang/StringBuilder
    //   814: dup
    //   815: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   818: astore_2
    //   819: aload_2
    //   820: ldc_w 839
    //   823: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   826: pop
    //   827: aload_2
    //   828: aload_0
    //   829: invokevirtual 840	java/lang/Throwable:toString	()Ljava/lang/String;
    //   832: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   835: pop
    //   836: aload_2
    //   837: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   840: invokestatic 552	com/bandwidthx/library/cg:c	(Ljava/lang/String;)V
    //   843: aload_1
    //   844: astore_0
    //   845: aload_0
    //   846: invokevirtual 397	java/lang/String:length	()I
    //   849: sipush 3000
    //   852: if_icmple +25 -> 877
    //   855: aload_0
    //   856: iconst_0
    //   857: sipush 3000
    //   860: invokevirtual 134	java/lang/String:substring	(II)Ljava/lang/String;
    //   863: invokestatic 552	com/bandwidthx/library/cg:c	(Ljava/lang/String;)V
    //   866: aload_0
    //   867: sipush 3000
    //   870: invokevirtual 129	java/lang/String:substring	(I)Ljava/lang/String;
    //   873: astore_0
    //   874: goto -29 -> 845
    //   877: aload_0
    //   878: invokestatic 552	com/bandwidthx/library/cg:c	(Ljava/lang/String;)V
    //   881: goto +15 -> 896
    //   884: astore_0
    //   885: goto +22 -> 907
    //   888: astore_0
    //   889: aload_0
    //   890: invokestatic 422	com/bandwidthx/library/bg:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   893: invokestatic 552	com/bandwidthx/library/cg:c	(Ljava/lang/String;)V
    //   896: iconst_0
    //   897: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   900: putstatic 70	com/bandwidthx/library/cg:f	Ljava/lang/Boolean;
    //   903: ldc 2
    //   905: monitorexit
    //   906: return
    //   907: ldc 2
    //   909: monitorexit
    //   910: aload_0
    //   911: athrow
    //   912: ldc 14
    //   914: astore 9
    //   916: goto -760 -> 156
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	919	0	paramString1	String
    //   0	919	1	paramString2	String
    //   0	919	2	paramString3	String
    //   0	919	3	paramString4	String
    //   0	919	4	paramBoolean	Boolean
    //   0	919	5	paramInteger	Integer
    //   0	919	6	paramString5	String
    //   0	919	7	paramLong	Long
    //   28	3	8	bool	boolean
    //   20	895	9	localObject1	Object
    //   53	364	10	localObject2	Object
    //   125	22	11	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   478	486	810	java/lang/Throwable
    //   488	501	810	java/lang/Throwable
    //   503	531	810	java/lang/Throwable
    //   533	659	810	java/lang/Throwable
    //   659	666	810	java/lang/Throwable
    //   668	678	810	java/lang/Throwable
    //   678	712	810	java/lang/Throwable
    //   712	760	810	java/lang/Throwable
    //   760	805	810	java/lang/Throwable
    //   3	30	884	finally
    //   39	153	884	finally
    //   156	180	884	finally
    //   184	267	884	finally
    //   274	283	884	finally
    //   287	354	884	finally
    //   354	469	884	finally
    //   478	486	884	finally
    //   488	501	884	finally
    //   503	531	884	finally
    //   533	659	884	finally
    //   659	666	884	finally
    //   668	678	884	finally
    //   678	712	884	finally
    //   712	760	884	finally
    //   760	805	884	finally
    //   811	843	884	finally
    //   845	874	884	finally
    //   877	881	884	finally
    //   889	896	884	finally
    //   896	903	884	finally
    //   3	30	888	java/lang/Throwable
    //   39	153	888	java/lang/Throwable
    //   156	180	888	java/lang/Throwable
    //   184	267	888	java/lang/Throwable
    //   274	283	888	java/lang/Throwable
    //   287	354	888	java/lang/Throwable
    //   354	469	888	java/lang/Throwable
    //   811	843	888	java/lang/Throwable
    //   845	874	888	java/lang/Throwable
    //   877	881	888	java/lang/Throwable
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    a(paramString, paramThrowable, Boolean.valueOf(true));
  }
  
  public static void a(String paramString, Throwable paramThrowable, Boolean paramBoolean)
  {
    if ((paramThrowable instanceof OutOfMemoryError)) {
      paramBoolean = Boolean.valueOf(false);
    } else if ((paramThrowable instanceof StackOverflowError)) {
      paramBoolean = Boolean.valueOf(false);
    } else if ((paramThrowable instanceof InterruptedException)) {
      paramBoolean = Boolean.valueOf(false);
    }
    String str = bg.a(paramThrowable);
    localObject2 = "at UNKNOWN";
    for (;;)
    {
      try
      {
        paramThrowable = new StringBuilder();
        paramThrowable.append("at ");
        paramThrowable.append(ac.d());
        paramThrowable = Integer.valueOf(str.indexOf(paramThrowable.toString()));
        localObject1 = paramThrowable;
        if (paramThrowable.intValue() <= 0) {
          localObject1 = Integer.valueOf(str.indexOf("at com.bandwidthx"));
        }
        paramThrowable = (Throwable)localObject2;
        if (((Integer)localObject1).intValue() > 0)
        {
          localObject3 = Integer.valueOf(str.indexOf(")", ((Integer)localObject1).intValue()));
          paramThrowable = (Throwable)localObject2;
          if (((Integer)localObject3).intValue() > 0)
          {
            paramThrowable = (Throwable)localObject2;
            if (((Integer)localObject1).intValue() < ((Integer)localObject3).intValue()) {
              paramThrowable = str.substring(((Integer)localObject1).intValue(), ((Integer)localObject3).intValue() + 1);
            }
          }
        }
      }
      catch (Throwable paramThrowable)
      {
        Object localObject1;
        Object localObject3;
        paramThrowable = (Throwable)localObject2;
        continue;
      }
      try
      {
        localObject1 = Integer.valueOf(str.lastIndexOf("(", ((Integer)localObject1).intValue()));
        if (((Integer)localObject1).intValue() > 0)
        {
          localObject2 = Integer.valueOf(str.lastIndexOf("at ", ((Integer)localObject1).intValue()));
          if ((((Integer)localObject2).intValue() > 0) && (((Integer)localObject2).intValue() < ((Integer)localObject1).intValue()))
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(paramThrowable);
            ((StringBuilder)localObject3).append(" calling ");
            ((StringBuilder)localObject3).append(str.substring(((Integer)localObject2).intValue() + 3, ((Integer)localObject1).intValue()));
            localObject1 = ((StringBuilder)localObject3).toString();
            paramThrowable = (Throwable)localObject1;
          }
        }
      }
      catch (Throwable localThrowable) {}
    }
    localObject1 = paramBoolean;
    if (paramString.contains("UNCAUGHT"))
    {
      localObject1 = paramBoolean;
      if (paramThrowable.contains("UNKNOWN")) {
        localObject1 = Boolean.valueOf(false);
      }
    }
    paramBoolean = new StringBuilder();
    paramBoolean.append("UNEXPECTED EXCEPTION");
    if (paramString.length() > 0)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(" ");
      ((StringBuilder)localObject2).append(paramString);
      paramString = ((StringBuilder)localObject2).toString();
    }
    else
    {
      paramString = "";
    }
    paramBoolean.append(paramString);
    paramBoolean.append(" (Build: ");
    paramBoolean.append(ac.i());
    paramBoolean.append(" ");
    paramBoolean.append(ac.h());
    paramBoolean.append(" ");
    paramBoolean.append(ac.j());
    paramBoolean.append(" ");
    paramBoolean.append(ac.d());
    paramBoolean.append(" ");
    paramBoolean.append(ac.e());
    paramBoolean.append(") ");
    paramBoolean.append(paramThrowable);
    paramBoolean.append(" ::: ");
    paramBoolean.append(str);
    paramString = paramBoolean.toString();
    if (str.contains("OutOfMemory")) {
      localObject1 = Boolean.valueOf(false);
    }
    if (((Boolean)localObject1).booleanValue())
    {
      a(paramString, Boolean.valueOf(true), Integer.valueOf(1), "Exception");
      return;
    }
    a(paramString, Boolean.valueOf(true), Integer.valueOf(0), null);
  }
  
  public static void a(Throwable paramThrowable)
  {
    a("", paramThrowable, Boolean.valueOf(true));
  }
  
  public static void a(Throwable paramThrowable, Boolean paramBoolean)
  {
    a("", paramThrowable, paramBoolean);
  }
  
  private static void b(OutputStreamWriter paramOutputStreamWriter)
  {
    a("", paramOutputStreamWriter);
  }
  
  public static void b(String paramString)
  {
    a(paramString, Boolean.valueOf(true), Integer.valueOf(0), null);
  }
  
  public static void c(String paramString)
  {
    a(g, paramString, Boolean.valueOf(false));
  }
  
  public static String d(String paramString)
  {
    try
    {
      paramString = be.a(h(), paramString, Boolean.valueOf(false));
      return paramString;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    return "(UNABLE TO ENCRYPT)";
  }
  
  public static void d()
  {
    try
    {
      synchronized (c)
      {
        if (c.get(Integer.valueOf(Process.myTid())) != null) {
          c.remove(Integer.valueOf(Process.myTid()));
        }
        return;
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  /* Error */
  public static String e()
  {
    // Byte code:
    //   0: new 105	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   7: astore_0
    //   8: aload_0
    //   9: invokestatic 902	com/bandwidthx/library/eh:d	()Ljava/lang/String;
    //   12: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: pop
    //   16: aload_0
    //   17: ldc -125
    //   19: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: pop
    //   23: aload_0
    //   24: invokestatic 856	com/bandwidthx/library/ac:d	()Ljava/lang/String;
    //   27: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_0
    //   32: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: astore_0
    //   36: new 105	java/lang/StringBuilder
    //   39: dup
    //   40: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   43: astore_1
    //   44: aload_1
    //   45: aload_0
    //   46: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: aload_1
    //   51: ldc_w 904
    //   54: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: aload_1
    //   59: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: astore_0
    //   63: new 360	java/io/File
    //   66: dup
    //   67: aload_0
    //   68: invokespecial 367	java/io/File:<init>	(Ljava/lang/String;)V
    //   71: astore_1
    //   72: aload_1
    //   73: invokevirtual 370	java/io/File:exists	()Z
    //   76: ifeq +8 -> 84
    //   79: aload_1
    //   80: invokevirtual 907	java/io/File:delete	()Z
    //   83: pop
    //   84: new 909	java/io/FileOutputStream
    //   87: dup
    //   88: aload_0
    //   89: invokespecial 910	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   92: astore_2
    //   93: new 404	java/io/OutputStreamWriter
    //   96: dup
    //   97: aload_2
    //   98: invokespecial 913	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   101: astore_1
    //   102: aload_1
    //   103: invokestatic 915	com/bandwidthx/library/cg:a	(Ljava/io/OutputStreamWriter;)V
    //   106: aload_1
    //   107: invokevirtual 918	java/io/OutputStreamWriter:flush	()V
    //   110: aload_1
    //   111: ifnull +7 -> 118
    //   114: aload_1
    //   115: invokevirtual 919	java/io/OutputStreamWriter:close	()V
    //   118: aload_2
    //   119: ifnull +7 -> 126
    //   122: aload_2
    //   123: invokevirtual 920	java/io/FileOutputStream:close	()V
    //   126: aload_0
    //   127: ldc -125
    //   129: invokevirtual 411	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   132: ifeq +6 -> 138
    //   135: goto +203 -> 338
    //   138: new 105	java/lang/StringBuilder
    //   141: dup
    //   142: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   145: astore_1
    //   146: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   149: astore_2
    //   150: aload_1
    //   151: invokestatic 602	com/bandwidthx/library/ch:e	()Landroid/content/Context;
    //   154: invokevirtual 924	android/content/Context:getFilesDir	()Ljava/io/File;
    //   157: invokevirtual 925	java/io/File:toString	()Ljava/lang/String;
    //   160: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload_1
    //   165: ldc -125
    //   167: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: pop
    //   171: aload_1
    //   172: aload_0
    //   173: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: pop
    //   177: aload_1
    //   178: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: astore_0
    //   182: goto +156 -> 338
    //   185: aload_1
    //   186: ifnull +7 -> 193
    //   189: aload_1
    //   190: invokevirtual 919	java/io/OutputStreamWriter:close	()V
    //   193: aload_2
    //   194: ifnull +7 -> 201
    //   197: aload_2
    //   198: invokevirtual 920	java/io/FileOutputStream:close	()V
    //   201: aload_0
    //   202: athrow
    //   203: astore_0
    //   204: new 105	java/lang/StringBuilder
    //   207: dup
    //   208: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   211: astore_1
    //   212: aload_1
    //   213: ldc_w 419
    //   216: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: pop
    //   220: aload_1
    //   221: aload_0
    //   222: invokestatic 422	com/bandwidthx/library/bg:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   225: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: pop
    //   229: aload_1
    //   230: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: iconst_0
    //   234: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   237: iconst_0
    //   238: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   241: aconst_null
    //   242: invokestatic 428	com/bandwidthx/library/cg:a	(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;)V
    //   245: ldc 14
    //   247: areturn
    //   248: astore_0
    //   249: new 105	java/lang/StringBuilder
    //   252: dup
    //   253: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   256: astore_1
    //   257: aload_1
    //   258: ldc_w 419
    //   261: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: pop
    //   265: aload_1
    //   266: aload_0
    //   267: invokestatic 422	com/bandwidthx/library/bg:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   270: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: pop
    //   274: aload_1
    //   275: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   278: iconst_0
    //   279: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   282: iconst_0
    //   283: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   286: aconst_null
    //   287: invokestatic 428	com/bandwidthx/library/cg:a	(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;)V
    //   290: ldc 14
    //   292: areturn
    //   293: astore_0
    //   294: new 105	java/lang/StringBuilder
    //   297: dup
    //   298: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   301: astore_1
    //   302: aload_1
    //   303: ldc_w 419
    //   306: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: pop
    //   310: aload_1
    //   311: aload_0
    //   312: invokestatic 422	com/bandwidthx/library/bg:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   315: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: aload_1
    //   320: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: iconst_0
    //   324: invokestatic 68	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   327: iconst_0
    //   328: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   331: aconst_null
    //   332: invokestatic 428	com/bandwidthx/library/cg:a	(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;)V
    //   335: ldc 14
    //   337: areturn
    //   338: aload_0
    //   339: areturn
    //   340: astore_0
    //   341: goto -156 -> 185
    //   344: astore_0
    //   345: aconst_null
    //   346: astore_1
    //   347: goto -162 -> 185
    //   350: astore_0
    //   351: aconst_null
    //   352: astore_2
    //   353: aload_2
    //   354: astore_1
    //   355: goto -170 -> 185
    // Local variable table:
    //   start	length	slot	name	signature
    //   7	195	0	localObject1	Object
    //   203	19	0	localThrowable	Throwable
    //   248	19	0	localIOException	IOException
    //   293	46	0	localFileNotFoundException	java.io.FileNotFoundException
    //   340	1	0	localObject2	Object
    //   344	1	0	localObject3	Object
    //   350	1	0	localObject4	Object
    //   43	312	1	localObject5	Object
    //   92	262	2	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   0	84	203	java/lang/Throwable
    //   114	118	203	java/lang/Throwable
    //   122	126	203	java/lang/Throwable
    //   126	135	203	java/lang/Throwable
    //   138	182	203	java/lang/Throwable
    //   189	193	203	java/lang/Throwable
    //   197	201	203	java/lang/Throwable
    //   201	203	203	java/lang/Throwable
    //   0	84	248	java/io/IOException
    //   114	118	248	java/io/IOException
    //   122	126	248	java/io/IOException
    //   126	135	248	java/io/IOException
    //   138	182	248	java/io/IOException
    //   189	193	248	java/io/IOException
    //   197	201	248	java/io/IOException
    //   201	203	248	java/io/IOException
    //   0	84	293	java/io/FileNotFoundException
    //   114	118	293	java/io/FileNotFoundException
    //   122	126	293	java/io/FileNotFoundException
    //   126	135	293	java/io/FileNotFoundException
    //   138	182	293	java/io/FileNotFoundException
    //   189	193	293	java/io/FileNotFoundException
    //   197	201	293	java/io/FileNotFoundException
    //   201	203	293	java/io/FileNotFoundException
    //   102	110	340	finally
    //   93	102	344	finally
    //   84	93	350	finally
  }
  
  public static String e(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString.length() > 10)
    {
      String str3 = paramString.substring(0, 1);
      str1 = str2;
      if (str3.equals("[")) {
        str1 = paramString.substring(1, paramString.indexOf("]"));
      }
      if (str3.equals("(")) {
        str1 = paramString.substring(1, paramString.indexOf(")"));
      }
      if (str3.equals("<")) {
        str1 = paramString.substring(1, paramString.indexOf(">"));
      }
      if (str3.equals("{")) {
        str1 = paramString.substring(1, paramString.indexOf("}"));
      }
    }
    return str1;
  }
  
  public static String f()
  {
    return a("", null);
  }
  
  public static String f(String paramString)
  {
    return a(paramString, null);
  }
  
  private static String h()
  {
    if (r.length() == 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("l");
      localStringBuilder.append(Integer.toString(0));
      localStringBuilder.append("G");
      r = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(r);
      localStringBuilder.append("Forb");
      r = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(r);
      localStringBuilder.append(Integer.toString(4));
      localStringBuilder.append("ndW");
      r = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(r);
      localStringBuilder.append("!");
      r = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(r);
      localStringBuilder.append("dth");
      localStringBuilder.append(Character.toString('X'));
      r = localStringBuilder.toString();
    }
    return r;
  }
  
  private static void h(String paramString)
  {
    try
    {
      Object localObject2 = i();
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("/log1.txt");
      localObject1 = ((StringBuilder)localObject1).toString();
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append((String)localObject2);
      ((StringBuilder)localObject3).append("/log2.txt");
      localObject3 = ((StringBuilder)localObject3).toString();
      Object localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append((String)localObject2);
      ((StringBuilder)localObject4).append("/log3.txt");
      localObject4 = ((StringBuilder)localObject4).toString();
      Object localObject5 = new StringBuilder();
      ((StringBuilder)localObject5).append((String)localObject2);
      ((StringBuilder)localObject5).append("/log4.txt");
      localObject5 = ((StringBuilder)localObject5).toString();
      localObject2 = new File((String)localObject1);
      if (((File)localObject2).length() > 300000L)
      {
        if (k != null) {
          k.close();
        }
        k = null;
        if (j != null) {
          j.close();
        }
        j = null;
        localObject3 = new File((String)localObject3);
        localObject4 = new File((String)localObject4);
        localObject5 = new File((String)localObject5);
        if (((File)localObject5).exists()) {
          ((File)localObject5).delete();
        }
        if (((File)localObject4).exists()) {
          ((File)localObject4).renameTo((File)localObject5);
        }
        if (((File)localObject3).exists()) {
          ((File)localObject3).renameTo((File)localObject4);
        }
        ((File)localObject2).renameTo((File)localObject3);
      }
      if (j == null) {
        j = new FileOutputStream((String)localObject1, true);
      }
      if (k == null) {
        k = new OutputStreamWriter(j);
      }
      localObject1 = k;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("\n\n");
      ((OutputStreamWriter)localObject1).write(((StringBuilder)localObject2).toString());
      k.flush();
      return;
    }
    catch (Throwable paramString) {}
  }
  
  private static String i()
  {
    if (b.length() == 0)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(eh.c());
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(ac.d());
      b = ((StringBuilder)localObject).toString();
      localObject = new File(b);
      if (!((File)localObject).exists()) {
        ((File)localObject).mkdir();
      }
    }
    return b;
  }
  
  /* Error */
  private static String j()
  {
    // Byte code:
    //   0: ldc 14
    //   2: astore_2
    //   3: aload_2
    //   4: astore_3
    //   5: invokestatic 602	com/bandwidthx/library/ch:e	()Landroid/content/Context;
    //   8: invokevirtual 971	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   11: astore 4
    //   13: aload_2
    //   14: astore_3
    //   15: aload 4
    //   17: iconst_0
    //   18: invokevirtual 977	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   21: invokeinterface 980 1 0
    //   26: astore 5
    //   28: aload_2
    //   29: astore_3
    //   30: aload 5
    //   32: invokeinterface 358 1 0
    //   37: ifeq +282 -> 319
    //   40: aload_2
    //   41: astore_3
    //   42: aload 5
    //   44: invokeinterface 364 1 0
    //   49: checkcast 982	android/content/pm/ApplicationInfo
    //   52: astore 6
    //   54: getstatic 97	com/bandwidthx/library/cg:a	Lcom/bandwidthx/library/ch;
    //   57: invokevirtual 298	com/bandwidthx/library/ch:i	()Lcom/bandwidthx/library/ac;
    //   60: pop
    //   61: aload 6
    //   63: getfield 985	android/content/pm/ApplicationInfo:uid	I
    //   66: invokestatic 425	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   69: invokestatic 988	com/bandwidthx/library/ac:c	(Ljava/lang/Integer;)Ljava/lang/String;
    //   72: astore 8
    //   74: aload 4
    //   76: aload 8
    //   78: iconst_2
    //   79: invokevirtual 992	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   82: astore 7
    //   84: aload 7
    //   86: ifnull -58 -> 28
    //   89: aload 7
    //   91: getfield 998	android/content/pm/PackageInfo:receivers	[Landroid/content/pm/ActivityInfo;
    //   94: astore_3
    //   95: aload_3
    //   96: ifnull -68 -> 28
    //   99: aload_3
    //   100: arraylength
    //   101: istore_1
    //   102: iconst_0
    //   103: istore_0
    //   104: iload_0
    //   105: iload_1
    //   106: if_icmpge -78 -> 28
    //   109: aload_3
    //   110: iload_0
    //   111: aaload
    //   112: getfield 1003	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   115: ldc_w 1005
    //   118: invokevirtual 411	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   121: ifeq +191 -> 312
    //   124: new 105	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   131: astore 6
    //   133: aload 6
    //   135: aload_2
    //   136: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload_2
    //   141: invokevirtual 397	java/lang/String:length	()I
    //   144: ifle +184 -> 328
    //   147: ldc_w 1007
    //   150: astore_3
    //   151: goto +3 -> 154
    //   154: aload 6
    //   156: aload_3
    //   157: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: pop
    //   161: aload 6
    //   163: aload 7
    //   165: getfield 1011	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   168: aload 4
    //   170: invokevirtual 1015	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   173: invokeinterface 1018 1 0
    //   178: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: pop
    //   182: aload 6
    //   184: ldc -76
    //   186: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: pop
    //   190: aload 6
    //   192: aload 7
    //   194: getfield 1021	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   197: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: pop
    //   201: aload 6
    //   203: ldc_w 1023
    //   206: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: pop
    //   210: aload 6
    //   212: aload 8
    //   214: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: pop
    //   218: aload 6
    //   220: ldc_w 876
    //   223: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload 6
    //   229: aload 7
    //   231: getfield 1027	android/content/pm/PackageInfo:lastUpdateTime	J
    //   234: ldc2_w 145
    //   237: ldiv
    //   238: invokestatic 56	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   241: invokestatic 1029	com/bandwidthx/library/bg:g	(Ljava/lang/Long;)Ljava/lang/String;
    //   244: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: pop
    //   248: aload 7
    //   250: getfield 1011	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   253: getfield 1033	android/content/pm/ApplicationInfo:enabled	Z
    //   256: ifeq +78 -> 334
    //   259: ldc 14
    //   261: astore_3
    //   262: goto +3 -> 265
    //   265: aload 6
    //   267: aload_3
    //   268: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: pop
    //   272: aload 7
    //   274: getfield 1011	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   277: getfield 1036	android/content/pm/ApplicationInfo:flags	I
    //   280: ldc_w 1037
    //   283: iand
    //   284: ifeq +57 -> 341
    //   287: ldc_w 1039
    //   290: astore_3
    //   291: goto +3 -> 294
    //   294: aload 6
    //   296: aload_3
    //   297: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: pop
    //   301: aload 6
    //   303: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   306: astore_3
    //   307: aload_3
    //   308: astore_2
    //   309: goto -281 -> 28
    //   312: iload_0
    //   313: iconst_1
    //   314: iadd
    //   315: istore_0
    //   316: goto -212 -> 104
    //   319: aload_2
    //   320: areturn
    //   321: astore_2
    //   322: aload_3
    //   323: areturn
    //   324: astore_3
    //   325: goto -297 -> 28
    //   328: ldc 14
    //   330: astore_3
    //   331: goto -177 -> 154
    //   334: ldc_w 1041
    //   337: astore_3
    //   338: goto -73 -> 265
    //   341: ldc 14
    //   343: astore_3
    //   344: goto -50 -> 294
    // Local variable table:
    //   start	length	slot	name	signature
    //   103	213	0	i1	int
    //   101	6	1	i2	int
    //   2	318	2	localObject1	Object
    //   321	1	2	localException1	Exception
    //   4	319	3	localObject2	Object
    //   324	1	3	localException2	Exception
    //   330	14	3	str1	String
    //   11	158	4	localPackageManager	android.content.pm.PackageManager
    //   26	17	5	localIterator	Iterator
    //   52	250	6	localObject3	Object
    //   82	191	7	localPackageInfo	android.content.pm.PackageInfo
    //   72	141	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   5	13	321	java/lang/Exception
    //   15	28	321	java/lang/Exception
    //   30	40	321	java/lang/Exception
    //   42	54	321	java/lang/Exception
    //   54	84	324	java/lang/Exception
    //   89	95	324	java/lang/Exception
    //   99	102	324	java/lang/Exception
    //   109	147	324	java/lang/Exception
    //   154	259	324	java/lang/Exception
    //   265	287	324	java/lang/Exception
    //   294	307	324	java/lang/Exception
  }
  
  /* Error */
  private static String k()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: invokestatic 1047	java/lang/Thread:getAllStackTraces	()Ljava/util/Map;
    //   5: invokeinterface 1052 1 0
    //   10: astore_3
    //   11: aload_3
    //   12: aload_3
    //   13: invokeinterface 1055 1 0
    //   18: anewarray 1043	java/lang/Thread
    //   21: invokeinterface 1059 2 0
    //   26: checkcast 1061	[Ljava/lang/Thread;
    //   29: astore 5
    //   31: aload 5
    //   33: arraylength
    //   34: istore_2
    //   35: ldc 14
    //   37: astore_3
    //   38: iconst_0
    //   39: istore_0
    //   40: aload_3
    //   41: astore 4
    //   43: iload_0
    //   44: iload_2
    //   45: if_icmpge +257 -> 302
    //   48: aload 5
    //   50: iload_0
    //   51: aaload
    //   52: astore 6
    //   54: new 105	java/lang/StringBuilder
    //   57: dup
    //   58: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   61: astore 7
    //   63: aload 7
    //   65: aload_3
    //   66: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: pop
    //   70: aload_3
    //   71: invokevirtual 397	java/lang/String:length	()I
    //   74: ifle +519 -> 593
    //   77: ldc_w 402
    //   80: astore 4
    //   82: goto +3 -> 85
    //   85: aload 7
    //   87: aload 4
    //   89: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: pop
    //   93: aload 7
    //   95: ldc_w 1063
    //   98: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload 7
    //   104: aload 6
    //   106: invokevirtual 1066	java/lang/Thread:getId	()J
    //   109: invokestatic 1069	java/lang/Long:toString	(J)Ljava/lang/String;
    //   112: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: aload 7
    //   118: ldc_w 1071
    //   121: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: pop
    //   125: aload 7
    //   127: aload 6
    //   129: invokevirtual 1074	java/lang/Thread:getName	()Ljava/lang/String;
    //   132: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: pop
    //   136: aload 7
    //   138: ldc_w 1071
    //   141: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: pop
    //   145: aload 7
    //   147: aload 6
    //   149: invokevirtual 1078	java/lang/Thread:getState	()Ljava/lang/Thread$State;
    //   152: invokevirtual 1081	java/lang/Thread$State:toString	()Ljava/lang/String;
    //   155: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload 7
    //   161: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: astore 4
    //   166: getstatic 50	com/bandwidthx/library/cg:c	Ljava/util/HashMap;
    //   169: aload 6
    //   171: invokevirtual 1066	java/lang/Thread:getId	()J
    //   174: invokestatic 56	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   177: invokevirtual 562	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   180: ifnull +56 -> 236
    //   183: new 105	java/lang/StringBuilder
    //   186: dup
    //   187: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   190: astore_3
    //   191: aload_3
    //   192: aload 4
    //   194: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: pop
    //   198: aload_3
    //   199: ldc_w 1071
    //   202: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: pop
    //   206: aload_3
    //   207: getstatic 50	com/bandwidthx/library/cg:c	Ljava/util/HashMap;
    //   210: aload 6
    //   212: invokevirtual 1066	java/lang/Thread:getId	()J
    //   215: invokestatic 56	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   218: invokevirtual 562	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   221: checkcast 121	java/lang/String
    //   224: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: pop
    //   228: aload_3
    //   229: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   232: astore_3
    //   233: goto +6 -> 239
    //   236: aload 4
    //   238: astore_3
    //   239: iload_0
    //   240: iconst_1
    //   241: iadd
    //   242: istore_0
    //   243: goto -203 -> 40
    //   246: astore 5
    //   248: aload 4
    //   250: astore_3
    //   251: aload 5
    //   253: astore 4
    //   255: goto +13 -> 268
    //   258: astore 4
    //   260: goto +8 -> 268
    //   263: astore 4
    //   265: ldc 14
    //   267: astore_3
    //   268: new 105	java/lang/StringBuilder
    //   271: dup
    //   272: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   275: astore 5
    //   277: aload 5
    //   279: aload_3
    //   280: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: pop
    //   284: aload 5
    //   286: aload 4
    //   288: invokevirtual 1082	java/lang/Exception:toString	()Ljava/lang/String;
    //   291: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: pop
    //   295: aload 5
    //   297: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   300: astore 4
    //   302: new 105	java/lang/StringBuilder
    //   305: dup
    //   306: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   309: astore 5
    //   311: aload 5
    //   313: aload 4
    //   315: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: aload 4
    //   321: invokevirtual 397	java/lang/String:length	()I
    //   324: ifle +10 -> 334
    //   327: ldc_w 402
    //   330: astore_3
    //   331: goto +6 -> 337
    //   334: ldc 14
    //   336: astore_3
    //   337: aload 5
    //   339: aload_3
    //   340: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: pop
    //   344: aload 5
    //   346: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: astore_3
    //   350: aload_3
    //   351: astore 4
    //   353: getstatic 50	com/bandwidthx/library/cg:c	Ljava/util/HashMap;
    //   356: invokevirtual 1083	java/util/HashMap:keySet	()Ljava/util/Set;
    //   359: astore 5
    //   361: aload_3
    //   362: astore 4
    //   364: aload 5
    //   366: aload 5
    //   368: invokeinterface 1055 1 0
    //   373: anewarray 377	java/lang/Integer
    //   376: invokeinterface 1059 2 0
    //   381: checkcast 1085	[Ljava/lang/Integer;
    //   384: astore 6
    //   386: aload_3
    //   387: astore 4
    //   389: aload 6
    //   391: arraylength
    //   392: istore_2
    //   393: iload_1
    //   394: istore_0
    //   395: aload_3
    //   396: astore 4
    //   398: iload_0
    //   399: iload_2
    //   400: if_icmpge +190 -> 590
    //   403: aload 6
    //   405: iload_0
    //   406: aaload
    //   407: astore 7
    //   409: aload_3
    //   410: astore 5
    //   412: aload_3
    //   413: astore 4
    //   415: getstatic 50	com/bandwidthx/library/cg:c	Ljava/util/HashMap;
    //   418: aload 7
    //   420: invokevirtual 562	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   423: ifnull +122 -> 545
    //   426: aload_3
    //   427: astore 4
    //   429: new 105	java/lang/StringBuilder
    //   432: dup
    //   433: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   436: astore 8
    //   438: aload_3
    //   439: astore 4
    //   441: aload 8
    //   443: aload_3
    //   444: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: pop
    //   448: aload_3
    //   449: astore 4
    //   451: aload_3
    //   452: invokevirtual 397	java/lang/String:length	()I
    //   455: ifle +145 -> 600
    //   458: ldc_w 402
    //   461: astore 5
    //   463: goto +3 -> 466
    //   466: aload_3
    //   467: astore 4
    //   469: aload 8
    //   471: aload 5
    //   473: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   476: pop
    //   477: aload_3
    //   478: astore 4
    //   480: aload 8
    //   482: ldc_w 1087
    //   485: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   488: pop
    //   489: aload_3
    //   490: astore 4
    //   492: aload 8
    //   494: aload 7
    //   496: invokevirtual 594	java/lang/Integer:toString	()Ljava/lang/String;
    //   499: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: pop
    //   503: aload_3
    //   504: astore 4
    //   506: aload 8
    //   508: ldc_w 1071
    //   511: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   514: pop
    //   515: aload_3
    //   516: astore 4
    //   518: aload 8
    //   520: getstatic 50	com/bandwidthx/library/cg:c	Ljava/util/HashMap;
    //   523: aload 7
    //   525: invokevirtual 562	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   528: checkcast 121	java/lang/String
    //   531: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: pop
    //   535: aload_3
    //   536: astore 4
    //   538: aload 8
    //   540: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   543: astore 5
    //   545: iload_0
    //   546: iconst_1
    //   547: iadd
    //   548: istore_0
    //   549: aload 5
    //   551: astore_3
    //   552: goto -157 -> 395
    //   555: astore_3
    //   556: new 105	java/lang/StringBuilder
    //   559: dup
    //   560: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   563: astore 5
    //   565: aload 5
    //   567: aload 4
    //   569: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: pop
    //   573: aload 5
    //   575: aload_3
    //   576: invokevirtual 1082	java/lang/Exception:toString	()Ljava/lang/String;
    //   579: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   582: pop
    //   583: aload 5
    //   585: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   588: astore 4
    //   590: aload 4
    //   592: areturn
    //   593: ldc 14
    //   595: astore 4
    //   597: goto -512 -> 85
    //   600: ldc 14
    //   602: astore 5
    //   604: goto -138 -> 466
    // Local variable table:
    //   start	length	slot	name	signature
    //   39	510	0	i1	int
    //   1	393	1	i2	int
    //   34	367	2	i3	int
    //   10	542	3	localObject1	Object
    //   555	21	3	localException1	Exception
    //   41	213	4	localObject2	Object
    //   258	1	4	localException2	Exception
    //   263	24	4	localException3	Exception
    //   300	296	4	localObject3	Object
    //   29	20	5	arrayOfThread	Thread[]
    //   246	6	5	localException4	Exception
    //   275	328	5	localObject4	Object
    //   52	352	6	localObject5	Object
    //   61	463	7	localStringBuilder1	StringBuilder
    //   436	103	8	localStringBuilder2	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   166	233	246	java/lang/Exception
    //   54	77	258	java/lang/Exception
    //   85	166	258	java/lang/Exception
    //   2	35	263	java/lang/Exception
    //   353	361	555	java/lang/Exception
    //   364	386	555	java/lang/Exception
    //   389	393	555	java/lang/Exception
    //   415	426	555	java/lang/Exception
    //   429	438	555	java/lang/Exception
    //   441	448	555	java/lang/Exception
    //   451	458	555	java/lang/Exception
    //   469	477	555	java/lang/Exception
    //   480	489	555	java/lang/Exception
    //   492	503	555	java/lang/Exception
    //   506	515	555	java/lang/Exception
    //   518	535	555	java/lang/Exception
    //   538	545	555	java/lang/Exception
  }
  
  public void a()
  {
    try
    {
      if (k != null) {
        k.close();
      }
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          if (j != null) {
            j.close();
          }
          k = null;
          j = null;
          return;
          localException1 = localException1;
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  public void b()
  {
    l = Long.valueOf(a.I().a("ExceptionDiagnosticsTicks", l.longValue()));
    n = Long.valueOf(a.I().a("ExceptionDiagnosticsCount", n.longValue()));
  }
  
  public void c()
  {
    a.I().c("ExceptionDiagnosticsTicks", l.longValue());
    a.I().c("ExceptionDiagnosticsCount", n.longValue());
    a.I().c();
  }
  
  public static class a
  {
    String a = "";
    String b = "";
    String c = "";
    String d = "";
    Boolean e = Boolean.valueOf(false);
    Integer f = Integer.valueOf(0);
    String g = "";
    Long h = Long.valueOf(0L);
    
    public a() {}
  }
}
