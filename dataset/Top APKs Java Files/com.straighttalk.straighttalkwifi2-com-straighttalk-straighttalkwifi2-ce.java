package com.straighttalk.straighttalkwifi2;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class ce
{
  private static ch a = null;
  private static HashMap b = new HashMap();
  private static Long c = Long.valueOf(0L);
  private static LinkedList d = new LinkedList();
  private static Boolean e = Boolean.valueOf(false);
  private static String f = "BANDWIDTHX";
  private static Boolean g = Boolean.valueOf(false);
  private static LinkedList h = new LinkedList();
  private static FileOutputStream i = null;
  private static OutputStreamWriter j = null;
  private static Long k = Long.valueOf(0L);
  private static Long l = Long.valueOf(86400000L);
  private static Long m = Long.valueOf(0L);
  private static Long n = Long.valueOf(5L);
  private static Boolean o = Boolean.valueOf(false);
  private static Boolean p = Boolean.valueOf(false);
  private static String q = "";
  
  public ce(ch paramCh)
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
    Object localObject2 = a.E().c();
    Object localObject1 = localObject2;
    try
    {
      localObject2 = ((String)localObject2).substring(((String)localObject2).indexOf("//") + 2);
      localObject1 = localObject2;
      localObject2 = ((String)localObject2).substring(0, ((String)localObject2).indexOf("/"));
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      int i1;
      StringBuilder localStringBuilder2;
      for (;;) {}
    }
    localObject2 = Calendar.getInstance();
    i1 = ((Calendar)localObject2).get(15);
    localObject2 = Long.valueOf(Long.valueOf((((Calendar)localObject2).get(16) + i1) / 1000L).longValue() / 3600L);
    localStringBuilder1.append("Date: " + bi.c(hk.e(), Boolean.valueOf(false)));
    localStringBuilder1.append("\r\nBuild: " + h.d() + " " + h.f() + "." + h.e() + " " + h.g());
    localStringBuilder1.append("\r\nToken: " + a.E().d());
    if (!paramBoolean.booleanValue())
    {
      localStringBuilder1.append("\r\nApps: " + h());
      localStringBuilder1.append("\r\nUser: " + a.H().f());
      localStringBuilder1.append("\r\nManufacturer: " + Build.MANUFACTURER);
      localStringBuilder1.append("\r\nModel: " + Build.MODEL);
      localStringBuilder1.append("\r\nVersion: Android " + Build.VERSION.RELEASE);
      localStringBuilder2 = new StringBuilder().append("\r\nTime Zone: GMT");
      if (((Long)localObject2).longValue() < 0L) {
        break label709;
      }
    }
    label709:
    for (paramBoolean = "+";; paramBoolean = "")
    {
      localStringBuilder1.append(paramBoolean + ((Long)localObject2).toString());
      localStringBuilder1.append("\r\nLocale: " + h.m() + " (setting " + Locale.getDefault().toString() + ")");
      localStringBuilder1.append("\r\nMobile Home: " + a.x().e() + " " + a.x().f());
      localStringBuilder1.append("\r\nMobile ID: " + a.x().l());
      localStringBuilder1.append("\r\nMobile Number: " + a.x().m());
      localStringBuilder1.append("\r\nDevice ID: " + a.x().k());
      localStringBuilder1.append("\r\nWifi MAC: " + a.J().c());
      localStringBuilder1.append("\r\nServices: " + a.r().b());
      localStringBuilder1.append("\r\nServer: " + localObject1);
      return localStringBuilder1.toString();
    }
  }
  
  /* Error */
  private static String a(String paramString, OutputStreamWriter paramOutputStreamWriter)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: new 103	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   10: astore 5
    //   12: aload_0
    //   13: invokevirtual 295	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   16: astore 8
    //   18: aload 8
    //   20: ldc_w 297
    //   23: invokevirtual 301	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   26: astore 9
    //   28: new 303	java/util/ArrayList
    //   31: dup
    //   32: invokespecial 304	java/util/ArrayList:<init>	()V
    //   35: astore 6
    //   37: invokestatic 305	com/straighttalk/straighttalkwifi2/ce:g	()Ljava/lang/String;
    //   40: astore_0
    //   41: aload 6
    //   43: new 103	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   50: aload_0
    //   51: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: ldc_w 307
    //   57: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokevirtual 311	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   66: pop
    //   67: aload 6
    //   69: new 103	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   76: aload_0
    //   77: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: ldc_w 313
    //   83: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokevirtual 311	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   92: pop
    //   93: aload 6
    //   95: new 103	java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   102: aload_0
    //   103: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: ldc_w 315
    //   109: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   115: invokevirtual 311	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   118: pop
    //   119: aload 6
    //   121: new 103	java/lang/StringBuilder
    //   124: dup
    //   125: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   128: aload_0
    //   129: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: ldc_w 317
    //   135: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   141: invokevirtual 311	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   144: pop
    //   145: aload 6
    //   147: invokevirtual 321	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   150: astore_0
    //   151: iconst_0
    //   152: istore_2
    //   153: aload_0
    //   154: invokeinterface 326 1 0
    //   159: ifeq +42 -> 201
    //   162: new 328	java/io/File
    //   165: dup
    //   166: aload_0
    //   167: invokeinterface 332 1 0
    //   172: checkcast 119	java/lang/String
    //   175: invokespecial 335	java/io/File:<init>	(Ljava/lang/String;)V
    //   178: astore 10
    //   180: aload 10
    //   182: invokevirtual 338	java/io/File:exists	()Z
    //   185: ifeq +544 -> 729
    //   188: iload_2
    //   189: i2l
    //   190: aload 10
    //   192: invokevirtual 341	java/io/File:length	()J
    //   195: ladd
    //   196: l2i
    //   197: istore_2
    //   198: goto +531 -> 729
    //   201: new 103	java/lang/StringBuilder
    //   204: dup
    //   205: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   208: ldc_w 343
    //   211: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: iload_2
    //   215: invokestatic 347	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   218: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: invokestatic 349	com/straighttalk/straighttalkwifi2/ce:b	(Ljava/lang/String;)V
    //   227: aload_1
    //   228: ifnonnull +495 -> 723
    //   231: new 103	java/lang/StringBuilder
    //   234: dup
    //   235: iload_2
    //   236: invokespecial 352	java/lang/StringBuilder:<init>	(I)V
    //   239: astore_0
    //   240: aload 6
    //   242: invokevirtual 321	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   245: astore 10
    //   247: aload 10
    //   249: invokeinterface 326 1 0
    //   254: ifeq +384 -> 638
    //   257: new 328	java/io/File
    //   260: dup
    //   261: aload 10
    //   263: invokeinterface 332 1 0
    //   268: checkcast 119	java/lang/String
    //   271: invokespecial 335	java/io/File:<init>	(Ljava/lang/String;)V
    //   274: astore 5
    //   276: aload 5
    //   278: invokevirtual 338	java/io/File:exists	()Z
    //   281: istore 4
    //   283: iload 4
    //   285: ifeq -38 -> 247
    //   288: new 354	java/io/FileReader
    //   291: dup
    //   292: aload 5
    //   294: invokespecial 357	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   297: astore 5
    //   299: new 359	java/io/BufferedReader
    //   302: dup
    //   303: aload 5
    //   305: invokespecial 362	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   308: astore 6
    //   310: aload 8
    //   312: invokevirtual 365	java/lang/String:length	()I
    //   315: ifne +185 -> 500
    //   318: aload 6
    //   320: invokevirtual 368	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   323: astore 11
    //   325: aload 11
    //   327: ifnull +283 -> 610
    //   330: aload_1
    //   331: ifnonnull +141 -> 472
    //   334: aload_0
    //   335: new 103	java/lang/StringBuilder
    //   338: dup
    //   339: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   342: aload 11
    //   344: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: ldc_w 370
    //   350: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   356: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: pop
    //   360: goto -42 -> 318
    //   363: astore 8
    //   365: aload 5
    //   367: astore 7
    //   369: aload 8
    //   371: astore 5
    //   373: aload 6
    //   375: ifnull +8 -> 383
    //   378: aload 6
    //   380: invokevirtual 373	java/io/BufferedReader:close	()V
    //   383: aload 7
    //   385: ifnull +8 -> 393
    //   388: aload 7
    //   390: invokevirtual 374	java/io/FileReader:close	()V
    //   393: aload 5
    //   395: athrow
    //   396: astore 6
    //   398: new 103	java/lang/StringBuilder
    //   401: dup
    //   402: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   405: ldc_w 376
    //   408: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: aload 6
    //   413: invokestatic 379	com/straighttalk/straighttalkwifi2/bi:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   416: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   419: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   422: iconst_0
    //   423: invokestatic 58	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   426: iconst_0
    //   427: invokestatic 382	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   430: aconst_null
    //   431: invokestatic 385	com/straighttalk/straighttalkwifi2/ce:a	(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;)V
    //   434: aload_1
    //   435: ifnonnull +206 -> 641
    //   438: aload_0
    //   439: new 103	java/lang/StringBuilder
    //   442: dup
    //   443: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   446: ldc_w 376
    //   449: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   452: aload 6
    //   454: invokestatic 379	com/straighttalk/straighttalkwifi2/bi:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   457: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   460: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   463: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   466: pop
    //   467: aload_0
    //   468: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   471: areturn
    //   472: aload_1
    //   473: new 103	java/lang/StringBuilder
    //   476: dup
    //   477: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   480: aload 11
    //   482: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: ldc_w 370
    //   488: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   494: invokevirtual 390	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   497: goto -179 -> 318
    //   500: aload 6
    //   502: invokevirtual 368	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   505: astore 11
    //   507: aload 11
    //   509: ifnull +101 -> 610
    //   512: aload 9
    //   514: arraylength
    //   515: istore_3
    //   516: iconst_0
    //   517: istore_2
    //   518: iload_2
    //   519: iload_3
    //   520: if_icmpge -20 -> 500
    //   523: aload 9
    //   525: iload_2
    //   526: aaload
    //   527: astore 12
    //   529: aload 11
    //   531: invokevirtual 295	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   534: aload 12
    //   536: invokevirtual 394	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   539: ifeq +64 -> 603
    //   542: aload_1
    //   543: ifnonnull +32 -> 575
    //   546: aload_0
    //   547: new 103	java/lang/StringBuilder
    //   550: dup
    //   551: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   554: aload 11
    //   556: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   559: ldc_w 396
    //   562: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   565: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   568: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: pop
    //   572: goto -72 -> 500
    //   575: aload_1
    //   576: new 103	java/lang/StringBuilder
    //   579: dup
    //   580: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   583: aload 11
    //   585: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   588: ldc_w 396
    //   591: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   594: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   597: invokevirtual 390	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   600: goto -100 -> 500
    //   603: iload_2
    //   604: iconst_1
    //   605: iadd
    //   606: istore_2
    //   607: goto -89 -> 518
    //   610: aload 6
    //   612: ifnull +8 -> 620
    //   615: aload 6
    //   617: invokevirtual 373	java/io/BufferedReader:close	()V
    //   620: aload 5
    //   622: ifnull -375 -> 247
    //   625: aload 5
    //   627: invokevirtual 374	java/io/FileReader:close	()V
    //   630: goto -383 -> 247
    //   633: astore 5
    //   635: goto -388 -> 247
    //   638: goto -171 -> 467
    //   641: aload_1
    //   642: new 103	java/lang/StringBuilder
    //   645: dup
    //   646: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   649: ldc_w 376
    //   652: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: aload 6
    //   657: invokestatic 379	com/straighttalk/straighttalkwifi2/bi:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   660: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   663: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   666: invokevirtual 390	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   669: goto -202 -> 467
    //   672: astore_1
    //   673: goto -206 -> 467
    //   676: astore 6
    //   678: goto -58 -> 620
    //   681: astore 6
    //   683: goto -300 -> 383
    //   686: astore 6
    //   688: goto -295 -> 393
    //   691: astore 6
    //   693: aload 5
    //   695: astore_0
    //   696: goto -298 -> 398
    //   699: astore 5
    //   701: aconst_null
    //   702: astore 6
    //   704: goto -331 -> 373
    //   707: astore 8
    //   709: aconst_null
    //   710: astore 6
    //   712: aload 5
    //   714: astore 7
    //   716: aload 8
    //   718: astore 5
    //   720: goto -347 -> 373
    //   723: aload 5
    //   725: astore_0
    //   726: goto -486 -> 240
    //   729: goto -576 -> 153
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	732	0	paramString	String
    //   0	732	1	paramOutputStreamWriter	OutputStreamWriter
    //   152	455	2	i1	int
    //   515	6	3	i2	int
    //   281	3	4	bool	boolean
    //   10	616	5	localObject1	Object
    //   633	61	5	localException1	Exception
    //   699	14	5	localObject2	Object
    //   718	6	5	localObject3	Object
    //   35	344	6	localObject4	Object
    //   396	260	6	localException2	Exception
    //   676	1	6	localException3	Exception
    //   681	1	6	localException4	Exception
    //   686	1	6	localException5	Exception
    //   691	1	6	localException6	Exception
    //   702	9	6	localObject5	Object
    //   1	714	7	localObject6	Object
    //   16	295	8	str1	String
    //   363	7	8	localObject7	Object
    //   707	10	8	localObject8	Object
    //   26	498	9	arrayOfString	String[]
    //   178	84	10	localObject9	Object
    //   323	261	11	str2	String
    //   527	8	12	str3	String
    // Exception table:
    //   from	to	target	type
    //   310	318	363	finally
    //   318	325	363	finally
    //   334	360	363	finally
    //   472	497	363	finally
    //   500	507	363	finally
    //   512	516	363	finally
    //   529	542	363	finally
    //   546	572	363	finally
    //   575	600	363	finally
    //   240	247	396	java/lang/Exception
    //   247	283	396	java/lang/Exception
    //   393	396	396	java/lang/Exception
    //   625	630	633	java/lang/Exception
    //   398	434	672	java/lang/Exception
    //   438	467	672	java/lang/Exception
    //   641	669	672	java/lang/Exception
    //   615	620	676	java/lang/Exception
    //   378	383	681	java/lang/Exception
    //   388	393	686	java/lang/Exception
    //   28	151	691	java/lang/Exception
    //   153	198	691	java/lang/Exception
    //   201	227	691	java/lang/Exception
    //   231	240	691	java/lang/Exception
    //   288	299	699	finally
    //   299	310	707	finally
  }
  
  public static void a(Long paramLong)
  {
    c = Long.valueOf(hk.e().longValue() + paramLong.longValue());
  }
  
  public static void a(String paramString)
  {
    if (b.get(Integer.valueOf(Process.myTid())) == null) {
      b.put(Integer.valueOf(Process.myTid()), paramString);
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
    //   3: invokestatic 402	android/os/Process:myTid	()I
    //   6: invokestatic 347	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   9: astore 4
    //   11: getstatic 40	com/straighttalk/straighttalkwifi2/ce:b	Ljava/util/HashMap;
    //   14: invokestatic 402	android/os/Process:myTid	()I
    //   17: invokestatic 382	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   20: invokevirtual 405	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   23: ifnull +20 -> 43
    //   26: getstatic 40	com/straighttalk/straighttalkwifi2/ce:b	Ljava/util/HashMap;
    //   29: invokestatic 402	android/os/Process:myTid	()I
    //   32: invokestatic 382	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   35: invokevirtual 405	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   38: checkcast 119	java/lang/String
    //   41: astore 4
    //   43: invokestatic 417	com/straighttalk/straighttalkwifi2/cd:f	()Ljava/lang/Boolean;
    //   46: invokevirtual 196	java/lang/Boolean:booleanValue	()Z
    //   49: ifeq +749 -> 798
    //   52: new 103	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   59: aload 4
    //   61: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: ldc_w 419
    //   67: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore 7
    //   75: ldc 90
    //   77: astore 5
    //   79: invokestatic 422	com/straighttalk/straighttalkwifi2/ch:d	()Landroid/content/Context;
    //   82: ldc_w 424
    //   85: invokevirtual 430	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   88: checkcast 432	android/net/wifi/WifiManager
    //   91: astore 8
    //   93: aload 5
    //   95: astore 4
    //   97: aload 8
    //   99: invokevirtual 435	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   102: ifeq +252 -> 354
    //   105: aload 8
    //   107: invokevirtual 439	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   110: astore 6
    //   112: aload 5
    //   114: astore 4
    //   116: aload 6
    //   118: invokevirtual 444	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   121: ifnull +233 -> 354
    //   124: aload 5
    //   126: astore 4
    //   128: aload 6
    //   130: invokevirtual 448	android/net/wifi/WifiInfo:getSupplicantState	()Landroid/net/wifi/SupplicantState;
    //   133: ifnull +221 -> 354
    //   136: aload 5
    //   138: astore 4
    //   140: aload 6
    //   142: invokevirtual 451	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   145: ifnull +209 -> 354
    //   148: aload 5
    //   150: astore 4
    //   152: aload 6
    //   154: invokevirtual 444	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   157: invokevirtual 365	java/lang/String:length	()I
    //   160: ifle +194 -> 354
    //   163: aload 5
    //   165: astore 4
    //   167: aload 6
    //   169: invokevirtual 451	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   172: invokevirtual 365	java/lang/String:length	()I
    //   175: ifle +179 -> 354
    //   178: aload 6
    //   180: invokevirtual 448	android/net/wifi/WifiInfo:getSupplicantState	()Landroid/net/wifi/SupplicantState;
    //   183: getstatic 457	android/net/wifi/SupplicantState:ASSOCIATED	Landroid/net/wifi/SupplicantState;
    //   186: if_acmpeq +18 -> 204
    //   189: aload 5
    //   191: astore 4
    //   193: aload 6
    //   195: invokevirtual 448	android/net/wifi/WifiInfo:getSupplicantState	()Landroid/net/wifi/SupplicantState;
    //   198: getstatic 460	android/net/wifi/SupplicantState:COMPLETED	Landroid/net/wifi/SupplicantState;
    //   201: if_acmpne +153 -> 354
    //   204: aload 6
    //   206: invokevirtual 451	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   209: ldc_w 462
    //   212: invokevirtual 466	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   215: ifeq +344 -> 559
    //   218: aload 6
    //   220: invokevirtual 451	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   223: iconst_1
    //   224: aload 6
    //   226: invokevirtual 451	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   229: invokevirtual 365	java/lang/String:length	()I
    //   232: iconst_1
    //   233: isub
    //   234: invokevirtual 132	java/lang/String:substring	(II)Ljava/lang/String;
    //   237: astore 4
    //   239: aload 4
    //   241: astore 6
    //   243: invokestatic 422	com/straighttalk/straighttalkwifi2/ch:d	()Landroid/content/Context;
    //   246: ldc_w 468
    //   249: invokevirtual 430	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   252: checkcast 470	android/net/ConnectivityManager
    //   255: astore 9
    //   257: aload 4
    //   259: astore 5
    //   261: aload 9
    //   263: ifnull +65 -> 328
    //   266: aload 4
    //   268: astore 6
    //   270: aload 9
    //   272: iconst_1
    //   273: invokevirtual 474	android/net/ConnectivityManager:getNetworkInfo	(I)Landroid/net/NetworkInfo;
    //   276: astore 5
    //   278: aload 5
    //   280: ifnull +289 -> 569
    //   283: aload 4
    //   285: astore 6
    //   287: aload 5
    //   289: invokevirtual 479	android/net/NetworkInfo:isAvailable	()Z
    //   292: ifeq +277 -> 569
    //   295: aload 4
    //   297: astore 6
    //   299: new 103	java/lang/StringBuilder
    //   302: dup
    //   303: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   306: ldc_w 462
    //   309: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: aload 4
    //   314: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: ldc_w 462
    //   320: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   326: astore 5
    //   328: aload 5
    //   330: astore 6
    //   332: new 103	java/lang/StringBuilder
    //   335: dup
    //   336: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   339: ldc -79
    //   341: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: aload 5
    //   346: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: astore 4
    //   354: aload 4
    //   356: astore 6
    //   358: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   361: invokevirtual 482	com/straighttalk/straighttalkwifi2/ch:h	()Lcom/straighttalk/straighttalkwifi2/BxApproval;
    //   364: invokevirtual 488	com/straighttalk/straighttalkwifi2/BxApproval:ad	()Lcom/straighttalk/straighttalkwifi2/BxApproval$ConnectedState;
    //   367: astore 4
    //   369: new 103	java/lang/StringBuilder
    //   372: dup
    //   373: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   376: ldc -79
    //   378: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: astore 5
    //   383: aload 8
    //   385: invokevirtual 435	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   388: ifeq +226 -> 614
    //   391: aload 4
    //   393: invokevirtual 491	com/straighttalk/straighttalkwifi2/BxApproval$ConnectedState:toString	()Ljava/lang/String;
    //   396: astore 4
    //   398: aload 5
    //   400: aload 4
    //   402: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   408: astore 4
    //   410: aload 4
    //   412: astore 5
    //   414: invokestatic 493	com/straighttalk/straighttalkwifi2/cd:g	()Ljava/lang/Boolean;
    //   417: invokevirtual 196	java/lang/Boolean:booleanValue	()Z
    //   420: ifeq +26 -> 446
    //   423: new 103	java/lang/StringBuilder
    //   426: dup
    //   427: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   430: aload 4
    //   432: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: ldc_w 419
    //   438: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: astore 5
    //   446: aload 5
    //   448: astore 4
    //   450: invokestatic 161	com/straighttalk/straighttalkwifi2/hk:e	()Ljava/lang/Long;
    //   453: invokevirtual 148	java/lang/Long:longValue	()J
    //   456: getstatic 48	com/straighttalk/straighttalkwifi2/ce:c	Ljava/lang/Long;
    //   459: invokevirtual 148	java/lang/Long:longValue	()J
    //   462: lcmp
    //   463: ifgt +23 -> 486
    //   466: invokestatic 161	com/straighttalk/straighttalkwifi2/hk:e	()Ljava/lang/Long;
    //   469: invokevirtual 148	java/lang/Long:longValue	()J
    //   472: getstatic 48	com/straighttalk/straighttalkwifi2/ce:c	Ljava/lang/Long;
    //   475: invokevirtual 148	java/lang/Long:longValue	()J
    //   478: ldc2_w 494
    //   481: lsub
    //   482: lcmp
    //   483: ifge +194 -> 677
    //   486: getstatic 53	com/straighttalk/straighttalkwifi2/ce:d	Ljava/util/LinkedList;
    //   489: invokevirtual 498	java/util/LinkedList:poll	()Ljava/lang/Object;
    //   492: checkcast 500	com/straighttalk/straighttalkwifi2/cg
    //   495: astore 5
    //   497: aload 5
    //   499: ifnull +158 -> 657
    //   502: aload 5
    //   504: getfield 502	com/straighttalk/straighttalkwifi2/cg:a	Ljava/lang/String;
    //   507: aload 5
    //   509: getfield 504	com/straighttalk/straighttalkwifi2/cg:b	Ljava/lang/String;
    //   512: aload 5
    //   514: getfield 506	com/straighttalk/straighttalkwifi2/cg:c	Ljava/lang/String;
    //   517: aload 5
    //   519: getfield 508	com/straighttalk/straighttalkwifi2/cg:d	Ljava/lang/String;
    //   522: aload 5
    //   524: getfield 509	com/straighttalk/straighttalkwifi2/cg:e	Ljava/lang/Boolean;
    //   527: aload 5
    //   529: getfield 512	com/straighttalk/straighttalkwifi2/cg:f	Ljava/lang/Integer;
    //   532: aload 5
    //   534: getfield 514	com/straighttalk/straighttalkwifi2/cg:g	Ljava/lang/String;
    //   537: aload 5
    //   539: getfield 516	com/straighttalk/straighttalkwifi2/cg:h	Ljava/lang/Long;
    //   542: invokestatic 519	com/straighttalk/straighttalkwifi2/ce:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V
    //   545: getstatic 53	com/straighttalk/straighttalkwifi2/ce:d	Ljava/util/LinkedList;
    //   548: invokevirtual 498	java/util/LinkedList:poll	()Ljava/lang/Object;
    //   551: checkcast 500	com/straighttalk/straighttalkwifi2/cg
    //   554: astore 5
    //   556: goto -59 -> 497
    //   559: aload 6
    //   561: invokevirtual 451	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   564: astore 4
    //   566: goto -327 -> 239
    //   569: aload 4
    //   571: astore 6
    //   573: new 103	java/lang/StringBuilder
    //   576: dup
    //   577: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   580: ldc_w 521
    //   583: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   586: aload 4
    //   588: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   591: ldc_w 521
    //   594: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   597: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   600: astore 5
    //   602: goto -274 -> 328
    //   605: astore 4
    //   607: ldc 90
    //   609: astore 6
    //   611: goto -253 -> 358
    //   614: new 103	java/lang/StringBuilder
    //   617: dup
    //   618: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   621: ldc_w 523
    //   624: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: aload 4
    //   629: invokevirtual 491	com/straighttalk/straighttalkwifi2/BxApproval$ConnectedState:toString	()Ljava/lang/String;
    //   632: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   635: ldc -8
    //   637: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   640: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   643: astore 4
    //   645: goto -247 -> 398
    //   648: astore 4
    //   650: ldc 90
    //   652: astore 4
    //   654: goto -204 -> 450
    //   657: aload 7
    //   659: aload 6
    //   661: aload 4
    //   663: aload_0
    //   664: aload_1
    //   665: aload_2
    //   666: aload_3
    //   667: invokestatic 161	com/straighttalk/straighttalkwifi2/hk:e	()Ljava/lang/Long;
    //   670: invokestatic 519	com/straighttalk/straighttalkwifi2/ce:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V
    //   673: ldc 2
    //   675: monitorexit
    //   676: return
    //   677: new 500	com/straighttalk/straighttalkwifi2/cg
    //   680: dup
    //   681: invokespecial 524	com/straighttalk/straighttalkwifi2/cg:<init>	()V
    //   684: astore 5
    //   686: aload 5
    //   688: aload 7
    //   690: putfield 502	com/straighttalk/straighttalkwifi2/cg:a	Ljava/lang/String;
    //   693: aload 5
    //   695: aload 6
    //   697: putfield 504	com/straighttalk/straighttalkwifi2/cg:b	Ljava/lang/String;
    //   700: aload 5
    //   702: aload 4
    //   704: putfield 506	com/straighttalk/straighttalkwifi2/cg:c	Ljava/lang/String;
    //   707: aload 5
    //   709: aload_0
    //   710: putfield 508	com/straighttalk/straighttalkwifi2/cg:d	Ljava/lang/String;
    //   713: aload 5
    //   715: aload_1
    //   716: putfield 509	com/straighttalk/straighttalkwifi2/cg:e	Ljava/lang/Boolean;
    //   719: aload 5
    //   721: aload_2
    //   722: putfield 512	com/straighttalk/straighttalkwifi2/cg:f	Ljava/lang/Integer;
    //   725: aload 5
    //   727: aload_3
    //   728: putfield 514	com/straighttalk/straighttalkwifi2/cg:g	Ljava/lang/String;
    //   731: aload 5
    //   733: invokestatic 161	com/straighttalk/straighttalkwifi2/hk:e	()Ljava/lang/Long;
    //   736: putfield 516	com/straighttalk/straighttalkwifi2/cg:h	Ljava/lang/Long;
    //   739: getstatic 53	com/straighttalk/straighttalkwifi2/ce:d	Ljava/util/LinkedList;
    //   742: aload 5
    //   744: invokevirtual 525	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   747: pop
    //   748: goto -75 -> 673
    //   751: astore_0
    //   752: aload_0
    //   753: invokestatic 379	com/straighttalk/straighttalkwifi2/bi:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   756: invokestatic 527	com/straighttalk/straighttalkwifi2/ce:c	(Ljava/lang/String;)V
    //   759: goto -86 -> 673
    //   762: astore_0
    //   763: ldc 2
    //   765: monitorexit
    //   766: aload_0
    //   767: athrow
    //   768: astore_0
    //   769: aload_0
    //   770: invokestatic 379	com/straighttalk/straighttalkwifi2/bi:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   773: invokestatic 527	com/straighttalk/straighttalkwifi2/ce:c	(Ljava/lang/String;)V
    //   776: goto -103 -> 673
    //   779: astore 5
    //   781: goto -127 -> 654
    //   784: astore 4
    //   786: goto -175 -> 611
    //   789: astore 5
    //   791: aload 4
    //   793: astore 5
    //   795: goto -467 -> 328
    //   798: aload 4
    //   800: astore 7
    //   802: goto -727 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	805	0	paramString1	String
    //   0	805	1	paramBoolean	Boolean
    //   0	805	2	paramInteger	Integer
    //   0	805	3	paramString2	String
    //   9	578	4	localObject1	Object
    //   605	23	4	localThrowable1	Throwable
    //   643	1	4	str1	String
    //   648	1	4	localException1	Exception
    //   652	51	4	str2	String
    //   784	15	4	localThrowable2	Throwable
    //   77	666	5	localObject2	Object
    //   779	1	5	localException2	Exception
    //   789	1	5	localException3	Exception
    //   793	1	5	localThrowable3	Throwable
    //   110	586	6	localObject3	Object
    //   73	728	7	localObject4	Object
    //   91	293	8	localWifiManager	android.net.wifi.WifiManager
    //   255	16	9	localConnectivityManager	android.net.ConnectivityManager
    // Exception table:
    //   from	to	target	type
    //   97	112	605	java/lang/Throwable
    //   116	124	605	java/lang/Throwable
    //   128	136	605	java/lang/Throwable
    //   140	148	605	java/lang/Throwable
    //   152	163	605	java/lang/Throwable
    //   167	189	605	java/lang/Throwable
    //   193	204	605	java/lang/Throwable
    //   204	239	605	java/lang/Throwable
    //   559	566	605	java/lang/Throwable
    //   358	398	648	java/lang/Exception
    //   398	410	648	java/lang/Exception
    //   614	645	648	java/lang/Exception
    //   3	11	751	java/lang/Exception
    //   11	43	751	java/lang/Exception
    //   43	75	751	java/lang/Exception
    //   79	93	751	java/lang/Exception
    //   97	112	751	java/lang/Exception
    //   116	124	751	java/lang/Exception
    //   128	136	751	java/lang/Exception
    //   140	148	751	java/lang/Exception
    //   152	163	751	java/lang/Exception
    //   167	189	751	java/lang/Exception
    //   193	204	751	java/lang/Exception
    //   204	239	751	java/lang/Exception
    //   332	354	751	java/lang/Exception
    //   450	486	751	java/lang/Exception
    //   486	497	751	java/lang/Exception
    //   502	556	751	java/lang/Exception
    //   559	566	751	java/lang/Exception
    //   657	673	751	java/lang/Exception
    //   677	748	751	java/lang/Exception
    //   3	11	762	finally
    //   11	43	762	finally
    //   43	75	762	finally
    //   79	93	762	finally
    //   97	112	762	finally
    //   116	124	762	finally
    //   128	136	762	finally
    //   140	148	762	finally
    //   152	163	762	finally
    //   167	189	762	finally
    //   193	204	762	finally
    //   204	239	762	finally
    //   243	257	762	finally
    //   270	278	762	finally
    //   287	295	762	finally
    //   299	328	762	finally
    //   332	354	762	finally
    //   358	398	762	finally
    //   398	410	762	finally
    //   414	446	762	finally
    //   450	486	762	finally
    //   486	497	762	finally
    //   502	556	762	finally
    //   559	566	762	finally
    //   573	602	762	finally
    //   614	645	762	finally
    //   657	673	762	finally
    //   677	748	762	finally
    //   752	759	762	finally
    //   769	776	762	finally
    //   3	11	768	java/lang/Throwable
    //   11	43	768	java/lang/Throwable
    //   43	75	768	java/lang/Throwable
    //   79	93	768	java/lang/Throwable
    //   358	398	768	java/lang/Throwable
    //   398	410	768	java/lang/Throwable
    //   414	446	768	java/lang/Throwable
    //   450	486	768	java/lang/Throwable
    //   486	497	768	java/lang/Throwable
    //   502	556	768	java/lang/Throwable
    //   614	645	768	java/lang/Throwable
    //   657	673	768	java/lang/Throwable
    //   677	748	768	java/lang/Throwable
    //   414	446	779	java/lang/Exception
    //   243	257	784	java/lang/Throwable
    //   270	278	784	java/lang/Throwable
    //   287	295	784	java/lang/Throwable
    //   299	328	784	java/lang/Throwable
    //   332	354	784	java/lang/Throwable
    //   573	602	784	java/lang/Throwable
    //   243	257	789	java/lang/Exception
    //   270	278	789	java/lang/Exception
    //   287	295	789	java/lang/Exception
    //   299	328	789	java/lang/Exception
    //   573	602	789	java/lang/Exception
  }
  
  public static void a(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, Boolean.valueOf(false));
  }
  
  public static void a(String paramString1, String paramString2, Boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        if (!o.booleanValue())
        {
          if (a.D().b("bx_syslog").equalsIgnoreCase("true")) {
            p = Boolean.valueOf(true);
          }
          o = Boolean.valueOf(true);
        }
      }
      catch (Throwable localThrowable)
      {
        continue;
      }
      catch (Exception localException)
      {
        continue;
      }
      try
      {
        if (p.booleanValue())
        {
          Log.i(paramString1, paramString2);
          return;
        }
        if ((paramBoolean.booleanValue()) || (h.i().booleanValue()) || (a.H().e().booleanValue()))
        {
          Log.i(paramString1, paramString2);
          return;
        }
      }
      catch (Exception paramString1) {}catch (Throwable paramString1) {}
    }
  }
  
  /* Error */
  private static void a(String paramString1, String paramString2, String paramString3, String paramString4, Boolean paramBoolean, Integer paramInteger, String paramString5, Long paramLong)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 161	com/straighttalk/straighttalkwifi2/hk:e	()Ljava/lang/Long;
    //   6: invokevirtual 148	java/lang/Long:longValue	()J
    //   9: invokestatic 561	java/lang/System:currentTimeMillis	()J
    //   12: lsub
    //   13: ldc2_w 143
    //   16: ldiv
    //   17: invokestatic 46	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   20: astore 9
    //   22: getstatic 60	com/straighttalk/straighttalkwifi2/ce:e	Ljava/lang/Boolean;
    //   25: invokevirtual 196	java/lang/Boolean:booleanValue	()Z
    //   28: istore 8
    //   30: iload 8
    //   32: ifeq +7 -> 39
    //   35: ldc 2
    //   37: monitorexit
    //   38: return
    //   39: iconst_1
    //   40: invokestatic 58	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   43: putstatic 60	com/straighttalk/straighttalkwifi2/ce:e	Ljava/lang/Boolean;
    //   46: new 103	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   53: ldc_w 563
    //   56: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload 7
    //   61: iconst_0
    //   62: invokestatic 58	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   65: invokestatic 565	com/straighttalk/straighttalkwifi2/bi:b	(Ljava/lang/Long;Ljava/lang/Boolean;)Ljava/lang/String;
    //   68: invokevirtual 566	java/lang/String:toString	()Ljava/lang/String;
    //   71: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: ldc -79
    //   76: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: aload 7
    //   81: iconst_0
    //   82: invokestatic 58	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   85: invokestatic 568	com/straighttalk/straighttalkwifi2/bi:a	(Ljava/lang/Long;Ljava/lang/Boolean;)Ljava/lang/String;
    //   88: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: astore 10
    //   93: aload 9
    //   95: invokevirtual 148	java/lang/Long:longValue	()J
    //   98: invokestatic 574	java/lang/Math:abs	(J)J
    //   101: lconst_1
    //   102: lcmp
    //   103: ifle +654 -> 757
    //   106: new 103	java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   113: ldc -79
    //   115: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aload 9
    //   120: invokevirtual 233	java/lang/Long:toString	()Ljava/lang/String;
    //   123: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   129: astore 9
    //   131: aload 10
    //   133: aload 9
    //   135: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: ldc_w 576
    //   141: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: astore 11
    //   149: aload 11
    //   151: astore 10
    //   153: invokestatic 579	com/straighttalk/straighttalkwifi2/ch:b	()Lcom/straighttalk/straighttalkwifi2/ch;
    //   156: ifnull +146 -> 302
    //   159: aload 11
    //   161: astore 9
    //   163: invokestatic 579	com/straighttalk/straighttalkwifi2/ch:b	()Lcom/straighttalk/straighttalkwifi2/ch;
    //   166: invokevirtual 582	com/straighttalk/straighttalkwifi2/ch:q	()Lcom/straighttalk/straighttalkwifi2/au;
    //   169: ifnull +61 -> 230
    //   172: aload 11
    //   174: astore 9
    //   176: invokestatic 579	com/straighttalk/straighttalkwifi2/ch:b	()Lcom/straighttalk/straighttalkwifi2/ch;
    //   179: invokevirtual 585	com/straighttalk/straighttalkwifi2/ch:g	()Lcom/straighttalk/straighttalkwifi2/h;
    //   182: invokevirtual 587	com/straighttalk/straighttalkwifi2/h:j	()Ljava/lang/Boolean;
    //   185: invokevirtual 196	java/lang/Boolean:booleanValue	()Z
    //   188: ifne +42 -> 230
    //   191: new 103	java/lang/StringBuilder
    //   194: dup
    //   195: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   198: ldc_w 523
    //   201: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: aload 11
    //   206: iconst_1
    //   207: aload 11
    //   209: invokevirtual 365	java/lang/String:length	()I
    //   212: iconst_1
    //   213: isub
    //   214: invokevirtual 132	java/lang/String:substring	(II)Ljava/lang/String;
    //   217: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: ldc -8
    //   222: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: astore 9
    //   230: aload 9
    //   232: astore 10
    //   234: invokestatic 579	com/straighttalk/straighttalkwifi2/ch:b	()Lcom/straighttalk/straighttalkwifi2/ch;
    //   237: invokevirtual 285	com/straighttalk/straighttalkwifi2/ch:r	()Lcom/straighttalk/straighttalkwifi2/be;
    //   240: ifnull +62 -> 302
    //   243: aload 9
    //   245: astore 10
    //   247: invokestatic 579	com/straighttalk/straighttalkwifi2/ch:b	()Lcom/straighttalk/straighttalkwifi2/ch;
    //   250: invokevirtual 285	com/straighttalk/straighttalkwifi2/ch:r	()Lcom/straighttalk/straighttalkwifi2/be;
    //   253: invokevirtual 588	com/straighttalk/straighttalkwifi2/be:i	()Ljava/lang/Boolean;
    //   256: invokevirtual 196	java/lang/Boolean:booleanValue	()Z
    //   259: ifeq +43 -> 302
    //   262: new 103	java/lang/StringBuilder
    //   265: dup
    //   266: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   269: ldc_w 590
    //   272: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload 9
    //   277: iconst_1
    //   278: aload 9
    //   280: invokevirtual 365	java/lang/String:length	()I
    //   283: iconst_1
    //   284: isub
    //   285: invokevirtual 132	java/lang/String:substring	(II)Ljava/lang/String;
    //   288: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: ldc_w 592
    //   294: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   300: astore 10
    //   302: new 103	java/lang/StringBuilder
    //   305: dup
    //   306: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   309: aload 10
    //   311: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   314: ldc -79
    //   316: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: aload_0
    //   320: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: aload_1
    //   324: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   327: aload_2
    //   328: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: ldc_w 594
    //   334: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: aload_3
    //   338: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   344: astore_0
    //   345: aload 4
    //   347: invokevirtual 196	java/lang/Boolean:booleanValue	()Z
    //   350: ifeq +42 -> 392
    //   353: getstatic 68	com/straighttalk/straighttalkwifi2/ce:h	Ljava/util/LinkedList;
    //   356: aload_0
    //   357: invokevirtual 525	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   360: pop
    //   361: getstatic 66	com/straighttalk/straighttalkwifi2/ce:g	Ljava/lang/Boolean;
    //   364: invokevirtual 196	java/lang/Boolean:booleanValue	()Z
    //   367: ifne +25 -> 392
    //   370: iconst_1
    //   371: invokestatic 58	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   374: putstatic 66	com/straighttalk/straighttalkwifi2/ce:g	Ljava/lang/Boolean;
    //   377: new 596	com/straighttalk/straighttalkwifi2/cf
    //   380: dup
    //   381: invokespecial 597	com/straighttalk/straighttalkwifi2/cf:<init>	()V
    //   384: iconst_0
    //   385: anewarray 599	java/lang/Void
    //   388: invokevirtual 603	com/straighttalk/straighttalkwifi2/cf:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   391: pop
    //   392: aload 6
    //   394: ifnull +259 -> 653
    //   397: aload 6
    //   399: invokevirtual 365	java/lang/String:length	()I
    //   402: ifle +251 -> 653
    //   405: lconst_1
    //   406: invokestatic 46	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   409: invokestatic 608	com/straighttalk/straighttalkwifi2/dn:a	(Ljava/lang/Long;)Ljava/lang/Boolean;
    //   412: invokevirtual 196	java/lang/Boolean:booleanValue	()Z
    //   415: ifne +238 -> 653
    //   418: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   421: invokevirtual 612	com/straighttalk/straighttalkwifi2/ch:A	()Lcom/straighttalk/straighttalkwifi2/dw;
    //   424: ldc_w 614
    //   427: ldc 90
    //   429: invokevirtual 619	com/straighttalk/straighttalkwifi2/dw:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   432: astore_1
    //   433: aload_1
    //   434: ldc_w 621
    //   437: invokevirtual 547	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   440: ifeq +76 -> 516
    //   443: lconst_0
    //   444: invokestatic 46	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   447: putstatic 74	com/straighttalk/straighttalkwifi2/ce:k	Ljava/lang/Long;
    //   450: lconst_0
    //   451: invokestatic 46	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   454: putstatic 80	com/straighttalk/straighttalkwifi2/ce:m	Ljava/lang/Long;
    //   457: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   460: invokevirtual 612	com/straighttalk/straighttalkwifi2/ch:A	()Lcom/straighttalk/straighttalkwifi2/dw;
    //   463: ldc_w 623
    //   466: getstatic 74	com/straighttalk/straighttalkwifi2/ce:k	Ljava/lang/Long;
    //   469: invokevirtual 148	java/lang/Long:longValue	()J
    //   472: invokevirtual 626	com/straighttalk/straighttalkwifi2/dw:b	(Ljava/lang/String;J)V
    //   475: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   478: invokevirtual 612	com/straighttalk/straighttalkwifi2/ch:A	()Lcom/straighttalk/straighttalkwifi2/dw;
    //   481: ldc_w 628
    //   484: getstatic 80	com/straighttalk/straighttalkwifi2/ce:m	Ljava/lang/Long;
    //   487: invokevirtual 148	java/lang/Long:longValue	()J
    //   490: invokevirtual 626	com/straighttalk/straighttalkwifi2/dw:b	(Ljava/lang/String;J)V
    //   493: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   496: invokevirtual 612	com/straighttalk/straighttalkwifi2/ch:A	()Lcom/straighttalk/straighttalkwifi2/dw;
    //   499: ldc_w 614
    //   502: ldc 90
    //   504: invokevirtual 630	com/straighttalk/straighttalkwifi2/dw:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   507: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   510: invokevirtual 612	com/straighttalk/straighttalkwifi2/ch:A	()Lcom/straighttalk/straighttalkwifi2/dw;
    //   513: invokevirtual 632	com/straighttalk/straighttalkwifi2/dw:b	()V
    //   516: aload_1
    //   517: invokestatic 189	com/straighttalk/straighttalkwifi2/h:g	()Ljava/lang/String;
    //   520: invokevirtual 547	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   523: ifne +130 -> 653
    //   526: getstatic 74	com/straighttalk/straighttalkwifi2/ce:k	Ljava/lang/Long;
    //   529: invokevirtual 148	java/lang/Long:longValue	()J
    //   532: aload 7
    //   534: invokevirtual 148	java/lang/Long:longValue	()J
    //   537: getstatic 78	com/straighttalk/straighttalkwifi2/ce:l	Ljava/lang/Long;
    //   540: invokevirtual 148	java/lang/Long:longValue	()J
    //   543: lsub
    //   544: lcmp
    //   545: ifge +15 -> 560
    //   548: aload 7
    //   550: putstatic 74	com/straighttalk/straighttalkwifi2/ce:k	Ljava/lang/Long;
    //   553: lconst_0
    //   554: invokestatic 46	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   557: putstatic 80	com/straighttalk/straighttalkwifi2/ce:m	Ljava/lang/Long;
    //   560: getstatic 80	com/straighttalk/straighttalkwifi2/ce:m	Ljava/lang/Long;
    //   563: astore_1
    //   564: getstatic 80	com/straighttalk/straighttalkwifi2/ce:m	Ljava/lang/Long;
    //   567: invokevirtual 148	java/lang/Long:longValue	()J
    //   570: lconst_1
    //   571: ladd
    //   572: invokestatic 46	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   575: putstatic 80	com/straighttalk/straighttalkwifi2/ce:m	Ljava/lang/Long;
    //   578: getstatic 80	com/straighttalk/straighttalkwifi2/ce:m	Ljava/lang/Long;
    //   581: invokevirtual 148	java/lang/Long:longValue	()J
    //   584: getstatic 84	com/straighttalk/straighttalkwifi2/ce:n	Ljava/lang/Long;
    //   587: invokevirtual 148	java/lang/Long:longValue	()J
    //   590: lcmp
    //   591: ifgt +17 -> 608
    //   594: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   597: invokevirtual 636	com/straighttalk/straighttalkwifi2/ch:W	()Lcom/straighttalk/straighttalkwifi2/gh;
    //   600: aload 5
    //   602: aload 6
    //   604: aload_0
    //   605: invokevirtual 641	com/straighttalk/straighttalkwifi2/gh:a	(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V
    //   608: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   611: invokevirtual 612	com/straighttalk/straighttalkwifi2/ch:A	()Lcom/straighttalk/straighttalkwifi2/dw;
    //   614: ldc_w 623
    //   617: getstatic 74	com/straighttalk/straighttalkwifi2/ce:k	Ljava/lang/Long;
    //   620: invokevirtual 148	java/lang/Long:longValue	()J
    //   623: invokevirtual 626	com/straighttalk/straighttalkwifi2/dw:b	(Ljava/lang/String;J)V
    //   626: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   629: invokevirtual 612	com/straighttalk/straighttalkwifi2/ch:A	()Lcom/straighttalk/straighttalkwifi2/dw;
    //   632: ldc_w 628
    //   635: getstatic 80	com/straighttalk/straighttalkwifi2/ce:m	Ljava/lang/Long;
    //   638: invokevirtual 148	java/lang/Long:longValue	()J
    //   641: invokevirtual 626	com/straighttalk/straighttalkwifi2/dw:b	(Ljava/lang/String;J)V
    //   644: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   647: invokevirtual 612	com/straighttalk/straighttalkwifi2/ch:A	()Lcom/straighttalk/straighttalkwifi2/dw;
    //   650: invokevirtual 632	com/straighttalk/straighttalkwifi2/dw:b	()V
    //   653: aload_0
    //   654: invokevirtual 365	java/lang/String:length	()I
    //   657: sipush 3000
    //   660: if_icmple +55 -> 715
    //   663: aload_0
    //   664: iconst_0
    //   665: sipush 3000
    //   668: invokevirtual 132	java/lang/String:substring	(II)Ljava/lang/String;
    //   671: invokestatic 527	com/straighttalk/straighttalkwifi2/ce:c	(Ljava/lang/String;)V
    //   674: aload_0
    //   675: sipush 3000
    //   678: invokevirtual 127	java/lang/String:substring	(I)Ljava/lang/String;
    //   681: astore_0
    //   682: goto -29 -> 653
    //   685: astore_1
    //   686: new 103	java/lang/StringBuilder
    //   689: dup
    //   690: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   693: ldc_w 643
    //   696: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   699: aload_1
    //   700: invokevirtual 644	java/lang/Exception:toString	()Ljava/lang/String;
    //   703: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   706: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   709: invokestatic 527	com/straighttalk/straighttalkwifi2/ce:c	(Ljava/lang/String;)V
    //   712: goto -59 -> 653
    //   715: aload_0
    //   716: invokestatic 527	com/straighttalk/straighttalkwifi2/ce:c	(Ljava/lang/String;)V
    //   719: iconst_0
    //   720: invokestatic 58	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   723: putstatic 60	com/straighttalk/straighttalkwifi2/ce:e	Ljava/lang/Boolean;
    //   726: goto -691 -> 35
    //   729: astore_0
    //   730: ldc 2
    //   732: monitorexit
    //   733: aload_0
    //   734: athrow
    //   735: astore_0
    //   736: aload_0
    //   737: invokestatic 379	com/straighttalk/straighttalkwifi2/bi:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   740: invokestatic 527	com/straighttalk/straighttalkwifi2/ce:c	(Ljava/lang/String;)V
    //   743: goto -24 -> 719
    //   746: astore_0
    //   747: aload_0
    //   748: invokestatic 379	com/straighttalk/straighttalkwifi2/bi:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   751: invokestatic 527	com/straighttalk/straighttalkwifi2/ce:c	(Ljava/lang/String;)V
    //   754: goto -35 -> 719
    //   757: ldc 90
    //   759: astore 9
    //   761: goto -630 -> 131
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	764	0	paramString1	String
    //   0	764	1	paramString2	String
    //   0	764	2	paramString3	String
    //   0	764	3	paramString4	String
    //   0	764	4	paramBoolean	Boolean
    //   0	764	5	paramInteger	Integer
    //   0	764	6	paramString5	String
    //   0	764	7	paramLong	Long
    //   28	3	8	bool	boolean
    //   20	740	9	localObject1	Object
    //   91	219	10	localObject2	Object
    //   147	61	11	str	String
    // Exception table:
    //   from	to	target	type
    //   397	516	685	java/lang/Exception
    //   516	560	685	java/lang/Exception
    //   560	608	685	java/lang/Exception
    //   608	653	685	java/lang/Exception
    //   3	30	729	finally
    //   39	131	729	finally
    //   131	149	729	finally
    //   153	159	729	finally
    //   163	172	729	finally
    //   176	230	729	finally
    //   234	243	729	finally
    //   247	302	729	finally
    //   302	392	729	finally
    //   397	516	729	finally
    //   516	560	729	finally
    //   560	608	729	finally
    //   608	653	729	finally
    //   653	682	729	finally
    //   686	712	729	finally
    //   715	719	729	finally
    //   719	726	729	finally
    //   736	743	729	finally
    //   747	754	729	finally
    //   3	30	735	java/lang/Exception
    //   39	131	735	java/lang/Exception
    //   131	149	735	java/lang/Exception
    //   153	159	735	java/lang/Exception
    //   163	172	735	java/lang/Exception
    //   176	230	735	java/lang/Exception
    //   234	243	735	java/lang/Exception
    //   247	302	735	java/lang/Exception
    //   302	392	735	java/lang/Exception
    //   653	682	735	java/lang/Exception
    //   686	712	735	java/lang/Exception
    //   715	719	735	java/lang/Exception
    //   3	30	746	java/lang/Throwable
    //   39	131	746	java/lang/Throwable
    //   131	149	746	java/lang/Throwable
    //   153	159	746	java/lang/Throwable
    //   163	172	746	java/lang/Throwable
    //   176	230	746	java/lang/Throwable
    //   234	243	746	java/lang/Throwable
    //   247	302	746	java/lang/Throwable
    //   302	392	746	java/lang/Throwable
    //   397	516	746	java/lang/Throwable
    //   516	560	746	java/lang/Throwable
    //   560	608	746	java/lang/Throwable
    //   608	653	746	java/lang/Throwable
    //   653	682	746	java/lang/Throwable
    //   686	712	746	java/lang/Throwable
    //   715	719	746	java/lang/Throwable
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    a(paramString, paramThrowable, Boolean.valueOf(true));
  }
  
  public static void a(String paramString, Throwable paramThrowable, Boolean paramBoolean)
  {
    String str2 = bi.a(paramThrowable);
    String str1 = "at UNKNOWN";
    localObject = str1;
    try
    {
      Integer localInteger1 = Integer.valueOf(str2.indexOf("at " + h.b()));
      paramThrowable = str1;
      localObject = str1;
      if (localInteger1.intValue() > 0)
      {
        localObject = str1;
        Integer localInteger2 = Integer.valueOf(str2.indexOf(")", localInteger1.intValue()));
        paramThrowable = str1;
        localObject = str1;
        if (localInteger2.intValue() > 0)
        {
          paramThrowable = str1;
          localObject = str1;
          if (localInteger1.intValue() < localInteger2.intValue())
          {
            localObject = str1;
            str1 = str2.substring(localInteger1.intValue(), localInteger2.intValue() + 1);
            localObject = str1;
            localInteger1 = Integer.valueOf(str2.lastIndexOf("(", localInteger1.intValue()));
            paramThrowable = str1;
            localObject = str1;
            if (localInteger1.intValue() > 0)
            {
              localObject = str1;
              localInteger2 = Integer.valueOf(str2.lastIndexOf("at ", localInteger1.intValue()));
              paramThrowable = str1;
              localObject = str1;
              if (localInteger2.intValue() > 0)
              {
                paramThrowable = str1;
                localObject = str1;
                if (localInteger2.intValue() < localInteger1.intValue())
                {
                  localObject = str1;
                  paramThrowable = str1 + " calling " + str2.substring(localInteger2.intValue() + 3, localInteger1.intValue());
                }
              }
            }
          }
        }
      }
    }
    catch (Exception paramThrowable)
    {
      for (;;)
      {
        paramThrowable = (Throwable)localObject;
      }
    }
    localObject = new StringBuilder().append("UNEXPECTED EXCEPTION");
    if (paramString.length() > 0) {}
    for (paramString = " " + paramString;; paramString = "")
    {
      paramString = paramString + " (Build: " + h.f() + " " + h.e() + " " + h.g() + ") " + paramThrowable + " ::: " + str2;
      if (str2.contains("OutOfMemory")) {
        paramBoolean = Boolean.valueOf(false);
      }
      if (!paramBoolean.booleanValue()) {
        break;
      }
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
  
  public static void b(String paramString)
  {
    a(paramString, Boolean.valueOf(true), Integer.valueOf(0), null);
  }
  
  public static void c()
  {
    if (b.get(Integer.valueOf(Process.myTid())) != null) {
      b.remove(Integer.valueOf(Process.myTid()));
    }
  }
  
  public static void c(String paramString)
  {
    a(f, paramString, Boolean.valueOf(false));
  }
  
  public static String d()
  {
    return a("", null);
  }
  
  public static String d(String paramString)
  {
    try
    {
      paramString = bf.a(f(), paramString, Boolean.valueOf(false));
      return paramString;
    }
    catch (Exception paramString) {}
    return "(UNABLE TO ENCRYPT)";
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
  
  private static String f()
  {
    if (q.length() == 0)
    {
      q = "l" + Integer.toString(0) + "G";
      q += "Forb";
      q = q + Integer.toString(4) + "ndW";
      q += "!";
      q = q + "dth" + Character.toString('X');
    }
    return q;
  }
  
  public static String f(String paramString)
  {
    return a(paramString, null);
  }
  
  private static String g()
  {
    String str = fg.b() + "/" + h.b();
    File localFile = new File(str);
    if (!localFile.exists()) {
      localFile.mkdir();
    }
    return str;
  }
  
  /* Error */
  private static String h()
  {
    // Byte code:
    //   0: ldc 90
    //   2: astore_2
    //   3: aload_2
    //   4: astore_3
    //   5: invokestatic 422	com/straighttalk/straighttalkwifi2/ch:d	()Landroid/content/Context;
    //   8: invokevirtual 727	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   11: astore 5
    //   13: aload_2
    //   14: astore_3
    //   15: aload 5
    //   17: iconst_0
    //   18: invokevirtual 733	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   21: invokeinterface 736 1 0
    //   26: astore 6
    //   28: aload_2
    //   29: astore_3
    //   30: aload_2
    //   31: astore 4
    //   33: aload 6
    //   35: invokeinterface 326 1 0
    //   40: ifeq +262 -> 302
    //   43: aload_2
    //   44: astore_3
    //   45: aload 6
    //   47: invokeinterface 332 1 0
    //   52: checkcast 738	android/content/pm/ApplicationInfo
    //   55: astore 4
    //   57: getstatic 33	com/straighttalk/straighttalkwifi2/ce:a	Lcom/straighttalk/straighttalkwifi2/ch;
    //   60: invokevirtual 585	com/straighttalk/straighttalkwifi2/ch:g	()Lcom/straighttalk/straighttalkwifi2/h;
    //   63: pop
    //   64: aload 4
    //   66: getfield 742	android/content/pm/ApplicationInfo:uid	I
    //   69: invokestatic 382	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   72: invokestatic 745	com/straighttalk/straighttalkwifi2/h:a	(Ljava/lang/Integer;)Ljava/lang/String;
    //   75: astore 7
    //   77: aload 5
    //   79: aload 7
    //   81: iconst_2
    //   82: invokevirtual 749	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   85: astore 4
    //   87: aload 4
    //   89: ifnull +216 -> 305
    //   92: aload 4
    //   94: getfield 755	android/content/pm/PackageInfo:receivers	[Landroid/content/pm/ActivityInfo;
    //   97: astore_3
    //   98: aload_3
    //   99: ifnull +206 -> 305
    //   102: aload_3
    //   103: arraylength
    //   104: istore_1
    //   105: iconst_0
    //   106: istore_0
    //   107: iload_0
    //   108: iload_1
    //   109: if_icmpge +196 -> 305
    //   112: aload_3
    //   113: iload_0
    //   114: aaload
    //   115: getfield 760	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   118: ldc_w 762
    //   121: invokevirtual 394	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   124: ifeq +163 -> 287
    //   127: new 103	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   134: aload_2
    //   135: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: astore 8
    //   140: aload_2
    //   141: invokevirtual 365	java/lang/String:length	()I
    //   144: ifle +164 -> 308
    //   147: ldc_w 764
    //   150: astore_3
    //   151: aload 8
    //   153: aload_3
    //   154: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: aload 4
    //   159: getfield 768	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   162: aload 5
    //   164: invokevirtual 772	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   167: invokeinterface 775 1 0
    //   172: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: ldc -79
    //   177: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: aload 4
    //   182: getfield 778	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   185: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: ldc_w 780
    //   191: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: aload 7
    //   196: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: ldc_w 670
    //   202: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: aload 4
    //   207: getfield 783	android/content/pm/PackageInfo:lastUpdateTime	J
    //   210: ldc2_w 143
    //   213: ldiv
    //   214: invokestatic 46	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   217: invokestatic 786	com/straighttalk/straighttalkwifi2/bi:f	(Ljava/lang/Long;)Ljava/lang/String;
    //   220: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: astore 7
    //   225: aload 4
    //   227: getfield 768	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   230: getfield 790	android/content/pm/ApplicationInfo:enabled	Z
    //   233: ifeq +81 -> 314
    //   236: ldc 90
    //   238: astore_3
    //   239: aload 7
    //   241: aload_3
    //   242: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: astore 7
    //   247: aload 4
    //   249: getfield 768	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   252: getfield 793	android/content/pm/ApplicationInfo:flags	I
    //   255: ldc_w 794
    //   258: iand
    //   259: ifeq +22 -> 281
    //   262: ldc_w 796
    //   265: astore_3
    //   266: aload 7
    //   268: aload_3
    //   269: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: invokevirtual 169	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   275: astore_3
    //   276: aload_3
    //   277: astore_2
    //   278: goto +27 -> 305
    //   281: ldc 90
    //   283: astore_3
    //   284: goto -18 -> 266
    //   287: iload_0
    //   288: iconst_1
    //   289: iadd
    //   290: istore_0
    //   291: goto -184 -> 107
    //   294: astore_3
    //   295: goto +10 -> 305
    //   298: astore_2
    //   299: aload_3
    //   300: astore 4
    //   302: aload 4
    //   304: areturn
    //   305: goto -277 -> 28
    //   308: ldc 90
    //   310: astore_3
    //   311: goto -160 -> 151
    //   314: ldc_w 798
    //   317: astore_3
    //   318: goto -79 -> 239
    // Local variable table:
    //   start	length	slot	name	signature
    //   106	185	0	i1	int
    //   104	6	1	i2	int
    //   2	276	2	localObject1	Object
    //   298	1	2	localException1	Exception
    //   4	280	3	localObject2	Object
    //   294	6	3	localException2	Exception
    //   310	8	3	str	String
    //   31	272	4	localObject3	Object
    //   11	152	5	localPackageManager	android.content.pm.PackageManager
    //   26	20	6	localIterator	java.util.Iterator
    //   75	192	7	localObject4	Object
    //   138	14	8	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   57	87	294	java/lang/Exception
    //   92	98	294	java/lang/Exception
    //   102	105	294	java/lang/Exception
    //   112	147	294	java/lang/Exception
    //   151	236	294	java/lang/Exception
    //   239	262	294	java/lang/Exception
    //   266	276	294	java/lang/Exception
    //   5	13	298	java/lang/Exception
    //   15	28	298	java/lang/Exception
    //   33	43	298	java/lang/Exception
    //   45	57	298	java/lang/Exception
  }
  
  private static void h(String paramString)
  {
    try
    {
      Object localObject1 = g();
      String str = (String)localObject1 + "/log1.txt";
      Object localObject4 = (String)localObject1 + "/log2.txt";
      Object localObject2 = (String)localObject1 + "/log3.txt";
      Object localObject3 = (String)localObject1 + "/log4.txt";
      localObject1 = new File(str);
      localObject4 = new File((String)localObject4);
      localObject2 = new File((String)localObject2);
      localObject3 = new File((String)localObject3);
      if ((((File)localObject1).exists()) && (((File)localObject1).length() > 300000L))
      {
        if (j != null) {
          j.close();
        }
        j = null;
        if (i != null) {
          i.close();
        }
        i = null;
        if (((File)localObject3).exists()) {
          ((File)localObject3).delete();
        }
        if (((File)localObject2).exists()) {
          ((File)localObject2).renameTo((File)localObject3);
        }
        if (((File)localObject4).exists()) {
          ((File)localObject4).renameTo((File)localObject2);
        }
        ((File)localObject1).renameTo((File)localObject4);
      }
      if (i == null) {
        i = new FileOutputStream(str, true);
      }
      if (j == null) {
        j = new OutputStreamWriter(i);
      }
      j.write(paramString + "\n\n");
      j.flush();
      return;
    }
    catch (Throwable paramString) {}catch (Exception paramString) {}
  }
  
  public void a()
  {
    try
    {
      if (j != null) {
        j.close();
      }
      try
      {
        if (i != null) {
          i.close();
        }
        j = null;
        i = null;
        return;
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public void b()
  {
    k = Long.valueOf(a.A().a("FeedbackTicks", k.longValue()));
    m = Long.valueOf(a.A().a("FeedbackCount", m.longValue()));
  }
}
