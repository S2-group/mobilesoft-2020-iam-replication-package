package com.ihs.b.d;

import android.content.Context;
import com.ihs.commons.i.g;
import com.ihs.commons.i.n;
import java.util.Formatter;
import java.util.Random;

public class b
  extends d
{
  private static final long g;
  private com.ihs.b.b.a f;
  
  static
  {
    if (g.a()) {}
    for (long l = 120L;; l = 86400L)
    {
      g = l;
      return;
    }
  }
  
  public b(Context paramContext, a paramA)
  {
    super(paramContext, paramA);
  }
  
  private long a(String paramString)
  {
    return this.c.a(c(paramString), 0L);
  }
  
  private String a(byte[] paramArrayOfByte)
  {
    Formatter localFormatter = new Formatter();
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      localFormatter.format("%02x", new Object[] { Byte.valueOf(paramArrayOfByte[i]) });
      i += 1;
    }
    paramArrayOfByte = localFormatter.toString();
    localFormatter.close();
    return paramArrayOfByte;
  }
  
  /* Error */
  private org.json.JSONObject a(java.util.Map paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 78	com/ihs/b/d/b:e	Landroid/content/Context;
    //   4: invokevirtual 84	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: sipush 128
    //   10: invokevirtual 90	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   13: astore_2
    //   14: new 92	org/json/JSONObject
    //   17: dup
    //   18: invokespecial 93	org/json/JSONObject:<init>	()V
    //   21: astore 4
    //   23: aload_2
    //   24: invokeinterface 99 1 0
    //   29: astore 5
    //   31: aload 5
    //   33: invokeinterface 104 1 0
    //   38: ifeq +438 -> 476
    //   41: aload 5
    //   43: invokeinterface 108 1 0
    //   48: checkcast 110	android/content/pm/PackageInfo
    //   51: astore_3
    //   52: aload_3
    //   53: getfield 114	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   56: astore_2
    //   57: aload_2
    //   58: getfield 120	android/content/pm/ApplicationInfo:uid	I
    //   61: sipush 10000
    //   64: if_icmplt -33 -> 31
    //   67: aload_2
    //   68: getfield 124	android/content/pm/ApplicationInfo:enabled	Z
    //   71: ifeq -40 -> 31
    //   74: invokestatic 130	java/lang/System:currentTimeMillis	()J
    //   77: ldc2_w 131
    //   80: ldiv
    //   81: aload_3
    //   82: getfield 135	android/content/pm/PackageInfo:lastUpdateTime	J
    //   85: lsub
    //   86: ldc2_w 136
    //   89: lcmp
    //   90: ifgt -59 -> 31
    //   93: ldc -117
    //   95: invokestatic 145	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   98: astore_2
    //   99: aload_2
    //   100: new 147	java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   107: aload_3
    //   108: getfield 152	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   111: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: ldc -98
    //   116: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   122: invokevirtual 165	java/lang/String:getBytes	()[B
    //   125: invokevirtual 169	java/security/MessageDigest:update	([B)V
    //   128: aload_0
    //   129: aload_2
    //   130: invokevirtual 172	java/security/MessageDigest:digest	()[B
    //   133: invokespecial 174	com/ihs/b/d/b:a	([B)Ljava/lang/String;
    //   136: astore_2
    //   137: aload_2
    //   138: ifnull -107 -> 31
    //   141: aload_1
    //   142: iconst_1
    //   143: anewarray 161	java/lang/String
    //   146: dup
    //   147: iconst_0
    //   148: aload_2
    //   149: aastore
    //   150: invokestatic 179	com/ihs/commons/i/i:f	(Ljava/util/Map;[Ljava/lang/String;)Ljava/util/Map;
    //   153: astore 6
    //   155: aload 6
    //   157: ifnull -126 -> 31
    //   160: ldc -75
    //   162: new 147	java/lang/StringBuilder
    //   165: dup
    //   166: ldc -73
    //   168: invokespecial 186	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   171: aload_3
    //   172: getfield 152	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   175: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: invokestatic 190	com/ihs/commons/i/g:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   184: aload 6
    //   186: invokeinterface 196 1 0
    //   191: invokeinterface 199 1 0
    //   196: astore 7
    //   198: aload 7
    //   200: invokeinterface 104 1 0
    //   205: ifeq -174 -> 31
    //   208: aload 7
    //   210: invokeinterface 108 1 0
    //   215: checkcast 161	java/lang/String
    //   218: astore 8
    //   220: aload 4
    //   222: aload 8
    //   224: invokevirtual 203	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   227: astore_2
    //   228: aload_2
    //   229: astore_3
    //   230: aload_2
    //   231: ifnonnull +20 -> 251
    //   234: new 92	org/json/JSONObject
    //   237: dup
    //   238: invokespecial 93	org/json/JSONObject:<init>	()V
    //   241: astore_3
    //   242: aload 4
    //   244: aload 8
    //   246: aload_3
    //   247: invokevirtual 207	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   250: pop
    //   251: aload 6
    //   253: aload 8
    //   255: invokeinterface 211 2 0
    //   260: astore 8
    //   262: aload 8
    //   264: instanceof 95
    //   267: ifeq +170 -> 437
    //   270: new 213	java/util/ArrayList
    //   273: dup
    //   274: invokespecial 214	java/util/ArrayList:<init>	()V
    //   277: astore_2
    //   278: aload 8
    //   280: checkcast 95	java/util/List
    //   283: invokeinterface 99 1 0
    //   288: astore 8
    //   290: aload 8
    //   292: invokeinterface 104 1 0
    //   297: ifeq +73 -> 370
    //   300: aload 8
    //   302: invokeinterface 108 1 0
    //   307: astore 9
    //   309: aload 9
    //   311: instanceof 161
    //   314: ifeq -24 -> 290
    //   317: aload_2
    //   318: aload 9
    //   320: checkcast 161	java/lang/String
    //   323: invokeinterface 218 2 0
    //   328: pop
    //   329: goto -39 -> 290
    //   332: astore_2
    //   333: aload_2
    //   334: invokevirtual 221	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   337: invokestatic 16	com/ihs/commons/i/g:a	()Z
    //   340: ifeq +172 -> 512
    //   343: new 223	java/lang/AssertionError
    //   346: dup
    //   347: invokespecial 224	java/lang/AssertionError:<init>	()V
    //   350: athrow
    //   351: astore_2
    //   352: aload_2
    //   353: invokevirtual 225	org/json/JSONException:printStackTrace	()V
    //   356: invokestatic 16	com/ihs/commons/i/g:a	()Z
    //   359: ifeq -161 -> 198
    //   362: new 223	java/lang/AssertionError
    //   365: dup
    //   366: invokespecial 224	java/lang/AssertionError:<init>	()V
    //   369: athrow
    //   370: aload_2
    //   371: invokeinterface 99 1 0
    //   376: astore_2
    //   377: aload_2
    //   378: invokeinterface 104 1 0
    //   383: ifeq -185 -> 198
    //   386: aload_2
    //   387: invokeinterface 108 1 0
    //   392: checkcast 161	java/lang/String
    //   395: astore 8
    //   397: aload_3
    //   398: aload 8
    //   400: aload_3
    //   401: aload 8
    //   403: iconst_0
    //   404: invokevirtual 229	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
    //   407: iconst_1
    //   408: iadd
    //   409: invokevirtual 232	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   412: pop
    //   413: goto -36 -> 377
    //   416: astore 8
    //   418: aload 8
    //   420: invokevirtual 225	org/json/JSONException:printStackTrace	()V
    //   423: invokestatic 16	com/ihs/commons/i/g:a	()Z
    //   426: ifeq -49 -> 377
    //   429: new 223	java/lang/AssertionError
    //   432: dup
    //   433: invokespecial 224	java/lang/AssertionError:<init>	()V
    //   436: athrow
    //   437: aload 8
    //   439: instanceof 161
    //   442: ifne +11 -> 453
    //   445: aload 8
    //   447: instanceof 234
    //   450: ifeq +15 -> 465
    //   453: aload 8
    //   455: invokevirtual 235	java/lang/Object:toString	()Ljava/lang/String;
    //   458: invokestatic 241	java/util/Collections:singletonList	(Ljava/lang/Object;)Ljava/util/List;
    //   461: astore_2
    //   462: goto -92 -> 370
    //   465: new 213	java/util/ArrayList
    //   468: dup
    //   469: invokespecial 214	java/util/ArrayList:<init>	()V
    //   472: astore_2
    //   473: goto -103 -> 370
    //   476: aload 4
    //   478: invokevirtual 245	org/json/JSONObject:length	()I
    //   481: ifle +29 -> 510
    //   484: new 92	org/json/JSONObject
    //   487: dup
    //   488: invokespecial 93	org/json/JSONObject:<init>	()V
    //   491: astore_1
    //   492: aload_1
    //   493: ldc -9
    //   495: aload 4
    //   497: invokevirtual 207	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   500: pop
    //   501: aload_1
    //   502: areturn
    //   503: astore_1
    //   504: aload_1
    //   505: invokevirtual 225	org/json/JSONException:printStackTrace	()V
    //   508: aconst_null
    //   509: areturn
    //   510: aconst_null
    //   511: areturn
    //   512: aconst_null
    //   513: astore_2
    //   514: goto -377 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	517	0	this	b
    //   0	517	1	paramMap	java.util.Map
    //   13	305	2	localObject1	Object
    //   332	2	2	localNoSuchAlgorithmException	java.security.NoSuchAlgorithmException
    //   351	20	2	localJSONException1	org.json.JSONException
    //   376	138	2	localObject2	Object
    //   51	350	3	localObject3	Object
    //   21	475	4	localJSONObject	org.json.JSONObject
    //   29	13	5	localIterator1	java.util.Iterator
    //   153	99	6	localMap	java.util.Map
    //   196	13	7	localIterator2	java.util.Iterator
    //   218	184	8	localObject4	Object
    //   416	38	8	localJSONException2	org.json.JSONException
    //   307	12	9	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   93	137	332	java/security/NoSuchAlgorithmException
    //   242	251	351	org/json/JSONException
    //   397	413	416	org/json/JSONException
    //   492	501	503	org/json/JSONException
  }
  
  private void a(String paramString, long paramLong)
  {
    this.c.b(e(paramString), paramLong);
  }
  
  private void b(String paramString)
  {
    this.c.b(c(paramString), System.currentTimeMillis() / 1000L);
  }
  
  private String c(String paramString)
  {
    return "Figure_LastGatherTime_" + paramString;
  }
  
  private long d(String paramString)
  {
    return this.c.a(e(paramString), 0L);
  }
  
  private String e(String paramString)
  {
    return "Figure_FirstGatherTime_" + paramString;
  }
  
  public void a(f paramF)
  {
    if (!com.ihs.commons.b.d.a(false, new String[] { "libAdReport", "FigureReportEnabled" }))
    {
      g.b("HSAdCaffe", "FigureReport is disabled");
      paramF.a(null);
      return;
    }
    long l2 = d(this.d);
    long l1 = l2;
    int j;
    if (l2 == 0L)
    {
      j = new Random(System.currentTimeMillis()).nextInt(7) + 1;
      l1 = System.currentTimeMillis() / 1000L;
      if (!g.a()) {
        break label166;
      }
    }
    label166:
    for (int i = 10;; i = 86400)
    {
      l1 = i * j + l1;
      g.b("HSAdCaffe", "random number : " + j);
      a(this.d, l1);
      if (System.currentTimeMillis() / 1000L >= l1) {
        break;
      }
      g.b("HSAdCaffe", "not reach first gather, return");
      paramF.a(null);
      return;
    }
    if (System.currentTimeMillis() / 1000L - a(this.d) < g)
    {
      g.b("HSAdCaffe", "The interval since last gather is not long enough, return");
      paramF.a(null);
      return;
    }
    if ((this.f != null) && (this.f.a() == com.ihs.a.a.c.b))
    {
      g.b("HSAdCaffe", "The startGather should not be called before last report finish");
      if (g.a()) {
        throw new AssertionError();
      }
      paramF.a(null);
      return;
    }
    b(this.d);
    this.f = new com.ihs.b.b.a(new c(this, paramF));
    this.f.b();
  }
}
