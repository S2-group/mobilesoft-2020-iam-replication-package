package com.igexin.push.core.a;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.igexin.assist.sdk.AssistPushManager;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.a.a.n;
import com.igexin.push.core.b.af;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.bean.p;
import com.igexin.push.d.c.o;
import com.igexin.push.extension.stub.IPushExtension;
import com.igexin.push.util.EncryptUtils;
import com.igexin.push.util.r;
import com.igexin.sdk.GTServiceManager;
import com.igexin.sdk.message.BindAliasCmdMessage;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
import com.igexin.sdk.message.UnBindAliasCmdMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f
  extends a
  implements com.igexin.push.e.k
{
  private static SparseArray<a> a;
  private static Map<String, com.igexin.push.core.a.a.a> b;
  private static Set<String> c;
  private static f d;
  private long e;
  
  private f()
  {
    a = new SparseArray();
    a.put(0, new k());
    a.put(5, new m());
    a.put(37, new q());
    a.put(9, new u());
    a.put(26, new j());
    a.put(28, new e());
    a.put(97, new l());
    c = new HashSet();
    b = new HashMap();
    c.add("goto");
    c.add("notification");
    c.add("terminatetask");
    c.add("startmyactivity");
    c.add("startapp");
    c.add("null");
    c.add("wakeupsdk");
    c.add("startweb");
    c.add("checkapp");
    c.add("cleanext");
    c.add("enablelog");
    c.add("disablelog");
    c.add("reportext");
  }
  
  private void E()
  {
    if (com.igexin.push.core.g.l)
    {
      com.igexin.push.core.g.l = false;
      com.igexin.b.a.c.b.a("CoreAction|broadcast online state = offline");
      l();
    }
    com.igexin.push.c.a localA = com.igexin.push.c.i.a().e();
    com.igexin.push.core.i.a().a(com.igexin.push.core.k.c);
    localA.i();
    if (C()) {
      com.igexin.b.a.c.b.a("CoreAction|sdkOn = false or pushOn = false, disconect|user");
    }
    for (;;)
    {
      com.igexin.b.a.b.c.b().a(com.igexin.b.a.b.a.a.k.class);
      return;
      com.igexin.b.a.c.b.a("CoreAction|disconnect|network");
    }
  }
  
  private void F()
  {
    com.igexin.push.core.i.a().a(com.igexin.push.core.k.d);
    com.igexin.push.core.g.h = com.igexin.push.util.a.g();
    com.igexin.b.a.c.b.a("CoreAction|network changed, available = " + com.igexin.push.core.g.h);
    com.igexin.push.d.b.a().b();
    com.igexin.b.a.c.b.a("CoreAction|network changed, disconnect +++");
    com.igexin.push.core.f.a().i().f();
    if (!com.igexin.push.core.g.h) {}
    label121:
    label129:
    for (;;)
    {
      return;
      boolean bool;
      if (!com.igexin.push.c.i.a().e().j())
      {
        com.igexin.b.a.c.b.a("CoreAction|network changed, domain = backup or trynormal");
        bool = false;
        if (!bool) {
          break label121;
        }
        com.igexin.b.a.c.b.a("CoreAction|detect result  = true, reconnect will run after detect");
      }
      for (;;)
      {
        if (!u()) {
          break label129;
        }
        com.igexin.b.a.c.b.a("CoreAction|network changed check condition status");
        t();
        return;
        bool = com.igexin.push.c.i.a().f();
        break;
        com.igexin.b.a.b.a.a.d.a().a(true);
      }
    }
  }
  
  @TargetApi(12)
  private Intent G()
  {
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT >= 12) {
      localIntent.addFlags(32);
    }
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    return localIntent;
  }
  
  /* Error */
  private boolean H()
  {
    // Byte code:
    //   0: getstatic 264	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   3: invokeinterface 269 1 0
    //   8: ifeq +451 -> 459
    //   11: getstatic 272	com/igexin/push/core/g:n	Z
    //   14: ifeq +445 -> 459
    //   17: invokestatic 197	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   20: invokevirtual 276	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   23: ldc_w 278
    //   26: iconst_1
    //   27: anewarray 280	java/lang/String
    //   30: dup
    //   31: iconst_0
    //   32: ldc_w 282
    //   35: aastore
    //   36: iconst_1
    //   37: anewarray 280	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: ldc_w 284
    //   45: aastore
    //   46: aconst_null
    //   47: aconst_null
    //   48: invokevirtual 289	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   51: astore_2
    //   52: aload_2
    //   53: ifnull +390 -> 443
    //   56: aload_2
    //   57: astore_1
    //   58: aload_2
    //   59: invokeinterface 294 1 0
    //   64: ifeq +379 -> 443
    //   67: aload_2
    //   68: astore_1
    //   69: aload_2
    //   70: aload_2
    //   71: ldc_w 296
    //   74: invokeinterface 300 2 0
    //   79: invokeinterface 304 2 0
    //   84: astore_3
    //   85: aload_2
    //   86: astore_1
    //   87: aload_2
    //   88: aload_2
    //   89: ldc_w 306
    //   92: invokeinterface 300 2 0
    //   97: invokeinterface 304 2 0
    //   102: astore 4
    //   104: aload_2
    //   105: astore_1
    //   106: new 308	org/json/JSONObject
    //   109: dup
    //   110: new 280	java/lang/String
    //   113: dup
    //   114: aload 4
    //   116: invokestatic 313	com/igexin/b/b/a:c	([B)[B
    //   119: invokespecial 316	java/lang/String:<init>	([B)V
    //   122: invokespecial 318	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   125: astore 4
    //   127: aload_2
    //   128: astore_1
    //   129: aload 4
    //   131: ldc_w 320
    //   134: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   137: astore 5
    //   139: aload_2
    //   140: astore_1
    //   141: aload 4
    //   143: ldc_w 326
    //   146: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   149: astore 6
    //   151: aload_2
    //   152: astore_1
    //   153: aload 4
    //   155: ldc_w 328
    //   158: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   161: astore 7
    //   163: aload_2
    //   164: astore_1
    //   165: aload 4
    //   167: ldc_w 330
    //   170: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   173: astore 8
    //   175: aload_2
    //   176: astore_1
    //   177: aload 4
    //   179: ldc_w 332
    //   182: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   185: astore 9
    //   187: aload_2
    //   188: astore_1
    //   189: aload 4
    //   191: ldc_w 334
    //   194: invokevirtual 338	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   197: astore 10
    //   199: aload_2
    //   200: astore_1
    //   201: invokestatic 340	com/igexin/push/core/a/f:a	()Lcom/igexin/push/core/a/f;
    //   204: aload 8
    //   206: aload 7
    //   208: invokevirtual 343	com/igexin/push/core/a/f:f	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   211: astore 11
    //   213: aload_2
    //   214: astore_1
    //   215: new 345	com/igexin/push/core/bean/PushTaskBean
    //   218: dup
    //   219: invokespecial 346	com/igexin/push/core/bean/PushTaskBean:<init>	()V
    //   222: astore 12
    //   224: aload_2
    //   225: astore_1
    //   226: aload 12
    //   228: aload 6
    //   230: invokevirtual 349	com/igexin/push/core/bean/PushTaskBean:setAppid	(Ljava/lang/String;)V
    //   233: aload_2
    //   234: astore_1
    //   235: aload 12
    //   237: aload 7
    //   239: invokevirtual 352	com/igexin/push/core/bean/PushTaskBean:setMessageId	(Ljava/lang/String;)V
    //   242: aload_2
    //   243: astore_1
    //   244: aload 12
    //   246: aload 8
    //   248: invokevirtual 355	com/igexin/push/core/bean/PushTaskBean:setTaskId	(Ljava/lang/String;)V
    //   251: aload_2
    //   252: astore_1
    //   253: aload 12
    //   255: aload 5
    //   257: invokevirtual 358	com/igexin/push/core/bean/PushTaskBean:setId	(Ljava/lang/String;)V
    //   260: aload_2
    //   261: astore_1
    //   262: aload 12
    //   264: aload 9
    //   266: invokevirtual 361	com/igexin/push/core/bean/PushTaskBean:setAppKey	(Ljava/lang/String;)V
    //   269: aload_2
    //   270: astore_1
    //   271: aload 12
    //   273: iconst_1
    //   274: invokevirtual 365	com/igexin/push/core/bean/PushTaskBean:setCurrentActionid	(I)V
    //   277: aload_2
    //   278: astore_1
    //   279: aload 12
    //   281: aload_2
    //   282: aload_2
    //   283: ldc_w 282
    //   286: invokeinterface 300 2 0
    //   291: invokeinterface 369 2 0
    //   296: invokevirtual 372	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   299: aload_3
    //   300: ifnull +11 -> 311
    //   303: aload_2
    //   304: astore_1
    //   305: aload 12
    //   307: aload_3
    //   308: invokevirtual 375	com/igexin/push/core/bean/PushTaskBean:setMsgExtra	([B)V
    //   311: aload_2
    //   312: astore_1
    //   313: aload 4
    //   315: ldc_w 377
    //   318: invokevirtual 381	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   321: ifeq +18 -> 339
    //   324: aload_2
    //   325: astore_1
    //   326: aload 12
    //   328: aload 4
    //   330: ldc_w 377
    //   333: invokevirtual 384	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   336: invokevirtual 387	com/igexin/push/core/bean/PushTaskBean:setCDNType	(Z)V
    //   339: aload_2
    //   340: astore_1
    //   341: aload 4
    //   343: ldc_w 389
    //   346: invokevirtual 381	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   349: ifeq +13 -> 362
    //   352: aload_2
    //   353: astore_1
    //   354: aload_0
    //   355: aload 4
    //   357: aload 12
    //   359: invokespecial 392	com/igexin/push/core/a/f:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   362: aload 10
    //   364: ifnull +57 -> 421
    //   367: aload_2
    //   368: astore_1
    //   369: aload 10
    //   371: invokevirtual 398	org/json/JSONArray:length	()I
    //   374: ifle +47 -> 421
    //   377: aload_2
    //   378: astore_1
    //   379: invokestatic 340	com/igexin/push/core/a/f:a	()Lcom/igexin/push/core/a/f;
    //   382: aload 4
    //   384: aload 12
    //   386: invokevirtual 401	com/igexin/push/core/a/f:a	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)Z
    //   389: ifne +32 -> 421
    //   392: aload_2
    //   393: astore_1
    //   394: new 169	java/lang/StringBuilder
    //   397: dup
    //   398: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   401: ldc_w 403
    //   404: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   407: aload 4
    //   409: invokevirtual 404	org/json/JSONObject:toString	()Ljava/lang/String;
    //   412: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   418: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   421: aload_2
    //   422: astore_1
    //   423: getstatic 264	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   426: aload 11
    //   428: aload 12
    //   430: invokeinterface 407 3 0
    //   435: pop
    //   436: goto -380 -> 56
    //   439: astore_1
    //   440: goto -384 -> 56
    //   443: aload_2
    //   444: astore_1
    //   445: iconst_0
    //   446: putstatic 272	com/igexin/push/core/g:n	Z
    //   449: aload_2
    //   450: ifnull +9 -> 459
    //   453: aload_2
    //   454: invokeinterface 410 1 0
    //   459: getstatic 264	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   462: invokeinterface 269 1 0
    //   467: ireturn
    //   468: astore_3
    //   469: aconst_null
    //   470: astore_2
    //   471: aload_2
    //   472: astore_1
    //   473: new 169	java/lang/StringBuilder
    //   476: dup
    //   477: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   480: ldc_w 412
    //   483: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: aload_3
    //   487: invokevirtual 413	java/lang/Throwable:toString	()Ljava/lang/String;
    //   490: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   493: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   496: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   499: aload_2
    //   500: ifnull -41 -> 459
    //   503: aload_2
    //   504: invokeinterface 410 1 0
    //   509: goto -50 -> 459
    //   512: astore_2
    //   513: aconst_null
    //   514: astore_1
    //   515: aload_1
    //   516: ifnull +9 -> 525
    //   519: aload_1
    //   520: invokeinterface 410 1 0
    //   525: aload_2
    //   526: athrow
    //   527: astore_2
    //   528: goto -13 -> 515
    //   531: astore_3
    //   532: goto -61 -> 471
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	535	0	this	f
    //   57	366	1	localCursor1	android.database.Cursor
    //   439	1	1	localJSONException	JSONException
    //   444	76	1	localCursor2	android.database.Cursor
    //   51	453	2	localCursor3	android.database.Cursor
    //   512	14	2	localObject1	Object
    //   527	1	2	localObject2	Object
    //   84	224	3	arrayOfByte	byte[]
    //   468	19	3	localThrowable1	Throwable
    //   531	1	3	localThrowable2	Throwable
    //   102	306	4	localObject3	Object
    //   137	119	5	str1	String
    //   149	80	6	str2	String
    //   161	77	7	str3	String
    //   173	74	8	str4	String
    //   185	80	9	str5	String
    //   197	173	10	localJSONArray	JSONArray
    //   211	216	11	str6	String
    //   222	207	12	localPushTaskBean	PushTaskBean
    // Exception table:
    //   from	to	target	type
    //   106	127	439	org/json/JSONException
    //   129	139	439	org/json/JSONException
    //   141	151	439	org/json/JSONException
    //   153	163	439	org/json/JSONException
    //   165	175	439	org/json/JSONException
    //   177	187	439	org/json/JSONException
    //   189	199	439	org/json/JSONException
    //   201	213	439	org/json/JSONException
    //   215	224	439	org/json/JSONException
    //   226	233	439	org/json/JSONException
    //   235	242	439	org/json/JSONException
    //   244	251	439	org/json/JSONException
    //   253	260	439	org/json/JSONException
    //   262	269	439	org/json/JSONException
    //   271	277	439	org/json/JSONException
    //   279	299	439	org/json/JSONException
    //   305	311	439	org/json/JSONException
    //   313	324	439	org/json/JSONException
    //   326	339	439	org/json/JSONException
    //   341	352	439	org/json/JSONException
    //   354	362	439	org/json/JSONException
    //   369	377	439	org/json/JSONException
    //   379	392	439	org/json/JSONException
    //   394	421	439	org/json/JSONException
    //   423	436	439	org/json/JSONException
    //   17	52	468	java/lang/Throwable
    //   17	52	512	finally
    //   58	67	527	finally
    //   69	85	527	finally
    //   87	104	527	finally
    //   106	127	527	finally
    //   129	139	527	finally
    //   141	151	527	finally
    //   153	163	527	finally
    //   165	175	527	finally
    //   177	187	527	finally
    //   189	199	527	finally
    //   201	213	527	finally
    //   215	224	527	finally
    //   226	233	527	finally
    //   235	242	527	finally
    //   244	251	527	finally
    //   253	260	527	finally
    //   262	269	527	finally
    //   271	277	527	finally
    //   279	299	527	finally
    //   305	311	527	finally
    //   313	324	527	finally
    //   326	339	527	finally
    //   341	352	527	finally
    //   354	362	527	finally
    //   369	377	527	finally
    //   379	392	527	finally
    //   394	421	527	finally
    //   423	436	527	finally
    //   445	449	527	finally
    //   473	499	527	finally
    //   58	67	531	java/lang/Throwable
    //   69	85	531	java/lang/Throwable
    //   87	104	531	java/lang/Throwable
    //   106	127	531	java/lang/Throwable
    //   129	139	531	java/lang/Throwable
    //   141	151	531	java/lang/Throwable
    //   153	163	531	java/lang/Throwable
    //   165	175	531	java/lang/Throwable
    //   177	187	531	java/lang/Throwable
    //   189	199	531	java/lang/Throwable
    //   201	213	531	java/lang/Throwable
    //   215	224	531	java/lang/Throwable
    //   226	233	531	java/lang/Throwable
    //   235	242	531	java/lang/Throwable
    //   244	251	531	java/lang/Throwable
    //   253	260	531	java/lang/Throwable
    //   262	269	531	java/lang/Throwable
    //   271	277	531	java/lang/Throwable
    //   279	299	531	java/lang/Throwable
    //   305	311	531	java/lang/Throwable
    //   313	324	531	java/lang/Throwable
    //   326	339	531	java/lang/Throwable
    //   341	352	531	java/lang/Throwable
    //   354	362	531	java/lang/Throwable
    //   369	377	531	java/lang/Throwable
    //   379	392	531	java/lang/Throwable
    //   394	421	531	java/lang/Throwable
    //   423	436	531	java/lang/Throwable
    //   445	449	531	java/lang/Throwable
  }
  
  public static f a()
  {
    if (d == null) {
      d = new f();
    }
    return d;
  }
  
  private void a(int paramInt, String paramString1, String paramString2)
  {
    paramString2 = new ContentValues();
    paramString2.put("status", Integer.valueOf(paramInt));
    com.igexin.push.core.f.a().k().a("message", paramString2, new String[] { "taskid" }, new String[] { paramString1 });
  }
  
  private void a(com.igexin.push.d.c.c paramC, PushTaskBean paramPushTaskBean, String paramString1, String paramString2)
  {
    paramC.a(new com.igexin.push.f.b.b(paramPushTaskBean, paramString1, r.a()));
    com.igexin.push.core.g.am.put(paramString2, paramC);
  }
  
  private void a(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte)
  {
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT >= 12) {
      localIntent.addFlags(32);
    }
    localIntent.setAction("com.igexin.sdk.action." + paramString3);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10001);
    localBundle.putString("taskid", paramString1);
    localBundle.putString("messageid", paramString2);
    localBundle.putString("appid", paramString3);
    localBundle.putString("payloadid", paramString2 + ":" + paramString1);
    localBundle.putString("packagename", com.igexin.push.core.g.e);
    localBundle.putByteArray("payload", paramArrayOfByte);
    localIntent.putExtras(localBundle);
    com.igexin.push.core.g.f.sendBroadcast(localIntent);
  }
  
  private void a(List<p> paramList)
  {
    h localH = new h(this);
    PackageManager localPackageManager = com.igexin.push.core.g.f.getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i < localList.size()) {}
      try
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((localApplicationInfo.flags & 0x1) <= 0)
        {
          p localP = new p();
          localP.b(localApplicationInfo.loadLabel(localPackageManager).toString());
          localP.d(localApplicationInfo.packageName);
          localP.c(String.valueOf(localPackageInfo.versionCode));
          localP.a(localPackageInfo.versionName);
          paramList.add(localP);
        }
        i += 1;
        continue;
        Collections.sort(paramList, localH);
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public static boolean a(long paramLong)
  {
    Date localDate = new Date(paramLong);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    int k = localCalendar.get(11);
    int j = com.igexin.push.config.m.a + com.igexin.push.config.m.b;
    int i = j;
    if (j >= 24) {
      i = j - 24;
    }
    if (com.igexin.push.config.m.b == 0) {
      return false;
    }
    if (com.igexin.push.config.m.a < i)
    {
      if ((k >= com.igexin.push.config.m.a) && (k < i)) {
        return true;
      }
    }
    else if (com.igexin.push.config.m.a > i)
    {
      if ((k >= 0) && (k < i)) {
        return true;
      }
      if ((k >= com.igexin.push.config.m.a) && (k < 24)) {
        return true;
      }
    }
    return false;
  }
  
  private void b(JSONObject paramJSONObject, PushTaskBean paramPushTaskBean)
  {
    try
    {
      Object localObject = paramJSONObject.getJSONObject("condition");
      paramJSONObject = new HashMap();
      if (((JSONObject)localObject).has("wifi")) {
        paramJSONObject.put("wifi", ((JSONObject)localObject).getString("wifi"));
      }
      if (((JSONObject)localObject).has("screenOn")) {
        paramJSONObject.put("screenOn", ((JSONObject)localObject).getString("screenOn"));
      }
      if (((JSONObject)localObject).has("ssid"))
      {
        paramJSONObject.put("ssid", ((JSONObject)localObject).getString("ssid"));
        if (((JSONObject)localObject).has("bssid")) {
          paramJSONObject.put("bssid", ((JSONObject)localObject).getString("bssid"));
        }
      }
      if (((JSONObject)localObject).has("duration"))
      {
        String str2 = ((JSONObject)localObject).getString("duration");
        if (str2.contains("-"))
        {
          int i = str2.indexOf("-");
          String str1 = str2.substring(0, i);
          str2 = str2.substring(i + 1, str2.length());
          paramJSONObject.put("startTime", str1);
          paramJSONObject.put("endTime", str2);
        }
      }
      if (((JSONObject)localObject).has("netConnected")) {
        paramJSONObject.put("netConnected", ((JSONObject)localObject).getString("netConnected"));
      }
      if (((JSONObject)localObject).has("expiredTime"))
      {
        localObject = ((JSONObject)localObject).getString("expiredTime");
        if ((!TextUtils.isEmpty((CharSequence)localObject)) && (TextUtils.isDigitsOnly((CharSequence)localObject))) {
          paramJSONObject.put("expiredTime", localObject);
        }
      }
      paramPushTaskBean.setConditionMap(paramJSONObject);
      return;
    }
    catch (Exception paramJSONObject) {}
  }
  
  private boolean b(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = paramJSONObject.getJSONObject("condition");
      if ((!paramJSONObject.has("wifi")) && (!paramJSONObject.has("screenOn")) && (!paramJSONObject.has("ssid")) && (!paramJSONObject.has("duration")))
      {
        boolean bool = paramJSONObject.has("netConnected");
        if (!bool) {}
      }
      else
      {
        return false;
      }
    }
    catch (Exception paramJSONObject) {}
    return true;
  }
  
  private void e(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("package:")))
    {
      paramString = paramString.substring(8);
      if (com.igexin.push.core.b.g.a().d().containsKey(paramString)) {
        com.igexin.push.core.b.g.a().d().remove(paramString);
      }
    }
  }
  
  private void f(Intent paramIntent)
  {
    String str3 = paramIntent.getStringExtra("taskid");
    String str4 = paramIntent.getStringExtra("messageid");
    String str5 = paramIntent.getStringExtra("actionid");
    String str6 = paramIntent.getStringExtra("accesstoken");
    if (paramIntent.hasExtra("title")) {}
    for (String str1 = paramIntent.getStringExtra("title");; str1 = "")
    {
      if (paramIntent.hasExtra("content")) {}
      for (String str2 = paramIntent.getStringExtra("content");; str2 = "")
      {
        int i = paramIntent.getIntExtra("notifID", 0);
        paramIntent = (NotificationManager)com.igexin.push.core.g.f.getSystemService("notification");
        if (i != 0)
        {
          paramIntent.cancel(i);
          com.igexin.push.core.g.ak.remove(str3);
        }
        while (!str6.equals(com.igexin.push.core.g.av))
        {
          return;
          if (com.igexin.push.core.g.aj.get(str3) != null)
          {
            paramIntent.cancel(((Integer)com.igexin.push.core.g.aj.get(str3)).intValue());
            com.igexin.push.core.g.ak.remove(str3);
          }
        }
        c(str3, str4, str1, str2);
        com.igexin.push.core.g.ak.remove(str3);
        b(str3, str4, str5);
        return;
      }
    }
  }
  
  private void f(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (!paramString.startsWith("package:"))) {
      return;
    }
    for (;;)
    {
      int i;
      try
      {
        paramString = paramString.substring(8);
        Object localObject = new ArrayList();
        if (!com.igexin.push.config.m.H.equals("none")) {
          ((List)localObject).addAll(Arrays.asList(com.igexin.push.config.m.H.split(",")));
        }
        if (com.igexin.push.util.a.a(paramString, (List)localObject)) {
          break;
        }
        localObject = com.igexin.push.core.g.f.getPackageManager().getPackageInfo(paramString, 4);
        if ((localObject == null) || (((PackageInfo)localObject).services == null)) {
          break;
        }
        ServiceInfo[] arrayOfServiceInfo = ((PackageInfo)localObject).services;
        int j = arrayOfServiceInfo.length;
        i = 0;
        if (i >= j) {
          break;
        }
        ServiceInfo localServiceInfo = arrayOfServiceInfo[i];
        if (com.igexin.push.util.a.a(localServiceInfo, (PackageInfo)localObject))
        {
          com.igexin.push.core.b.g.a().d().put(paramString, localServiceInfo.name);
          return;
        }
      }
      catch (Throwable paramString)
      {
        return;
      }
      i += 1;
    }
  }
  
  private void f(boolean paramBoolean)
  {
    com.igexin.push.d.a.b.b = -1;
    if (EncryptUtils.isLoadSuccess())
    {
      if (com.igexin.push.core.g.aC)
      {
        com.igexin.push.core.f.a().g().c(paramBoolean);
        return;
      }
      com.igexin.b.a.c.b.a("CoreAction|autoReconnect CoreRuntimeInfo.initSuccess = false");
      return;
    }
    com.igexin.b.a.c.b.a("CoreAction|so error ++++++++");
  }
  
  /* Error */
  private void g(Intent paramIntent)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 330
    //   4: invokevirtual 670	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   7: astore 4
    //   9: aload_1
    //   10: ldc_w 328
    //   13: invokevirtual 670	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   16: astore 5
    //   18: aload_1
    //   19: ldc_w 326
    //   22: invokevirtual 670	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   25: astore_3
    //   26: aload_1
    //   27: ldc_w 789
    //   30: invokevirtual 670	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   33: astore 6
    //   35: ldc_w 791
    //   38: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   41: new 419	android/content/ContentValues
    //   44: dup
    //   45: invokespecial 420	android/content/ContentValues:<init>	()V
    //   48: astore 7
    //   50: new 169	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   57: ldc_w 793
    //   60: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload 4
    //   65: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: astore_1
    //   72: aload 7
    //   74: ldc_w 330
    //   77: aload 4
    //   79: invokevirtual 795	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   82: aload 7
    //   84: ldc_w 326
    //   87: aload_3
    //   88: invokevirtual 795	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   91: aload 7
    //   93: ldc_w 797
    //   96: aload_1
    //   97: invokevirtual 795	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   100: aload 7
    //   102: ldc_w 799
    //   105: invokestatic 804	java/lang/System:currentTimeMillis	()J
    //   108: invokestatic 809	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   111: invokevirtual 812	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   114: invokestatic 197	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   117: invokevirtual 276	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   120: ldc_w 278
    //   123: iconst_1
    //   124: anewarray 280	java/lang/String
    //   127: dup
    //   128: iconst_0
    //   129: ldc_w 797
    //   132: aastore
    //   133: iconst_1
    //   134: anewarray 280	java/lang/String
    //   137: dup
    //   138: iconst_0
    //   139: aload_1
    //   140: aastore
    //   141: aconst_null
    //   142: aconst_null
    //   143: invokevirtual 289	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   146: astore_3
    //   147: aload_3
    //   148: ifnull +96 -> 244
    //   151: aload_3
    //   152: astore_1
    //   153: aload_3
    //   154: invokeinterface 815 1 0
    //   159: ifne +85 -> 244
    //   162: aload_3
    //   163: astore_1
    //   164: aload_0
    //   165: aload 7
    //   167: invokevirtual 818	com/igexin/push/core/a/f:a	(Landroid/content/ContentValues;)V
    //   170: aload_3
    //   171: astore_1
    //   172: aload 6
    //   174: getstatic 478	com/igexin/push/core/g:e	Ljava/lang/String;
    //   177: invokevirtual 707	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   180: istore_2
    //   181: iload_2
    //   182: ifeq +62 -> 244
    //   185: aload 5
    //   187: ifnull +8 -> 195
    //   190: aload 4
    //   192: ifnonnull +14 -> 206
    //   195: aload_3
    //   196: ifnull +9 -> 205
    //   199: aload_3
    //   200: invokeinterface 410 1 0
    //   205: return
    //   206: aload_3
    //   207: astore_1
    //   208: invokestatic 197	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   211: ifnull +33 -> 244
    //   214: aload_3
    //   215: astore_1
    //   216: aload_0
    //   217: aload 4
    //   219: aload 5
    //   221: invokevirtual 821	com/igexin/push/core/a/f:g	(Ljava/lang/String;Ljava/lang/String;)Lcom/igexin/push/core/b;
    //   224: getstatic 826	com/igexin/push/core/b:a	Lcom/igexin/push/core/b;
    //   227: if_acmpne +17 -> 244
    //   230: aload_3
    //   231: astore_1
    //   232: aload_0
    //   233: aload 4
    //   235: aload 5
    //   237: ldc_w 828
    //   240: invokevirtual 830	com/igexin/push/core/a/f:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   243: pop
    //   244: aload_3
    //   245: ifnull -40 -> 205
    //   248: aload_3
    //   249: invokeinterface 410 1 0
    //   254: return
    //   255: astore 4
    //   257: aconst_null
    //   258: astore_3
    //   259: aload_3
    //   260: astore_1
    //   261: new 169	java/lang/StringBuilder
    //   264: dup
    //   265: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   268: ldc_w 832
    //   271: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: aload 4
    //   276: invokevirtual 413	java/lang/Throwable:toString	()Ljava/lang/String;
    //   279: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   285: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   288: aload_3
    //   289: ifnull -84 -> 205
    //   292: aload_3
    //   293: invokeinterface 410 1 0
    //   298: return
    //   299: astore_3
    //   300: aconst_null
    //   301: astore_1
    //   302: aload_1
    //   303: ifnull +9 -> 312
    //   306: aload_1
    //   307: invokeinterface 410 1 0
    //   312: aload_3
    //   313: athrow
    //   314: astore_3
    //   315: goto -13 -> 302
    //   318: astore 4
    //   320: goto -61 -> 259
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	323	0	this	f
    //   0	323	1	paramIntent	Intent
    //   180	2	2	bool	boolean
    //   25	268	3	localObject1	Object
    //   299	14	3	localObject2	Object
    //   314	1	3	localObject3	Object
    //   7	227	4	str1	String
    //   255	20	4	localThrowable1	Throwable
    //   318	1	4	localThrowable2	Throwable
    //   16	220	5	str2	String
    //   33	140	6	str3	String
    //   48	118	7	localContentValues	ContentValues
    // Exception table:
    //   from	to	target	type
    //   114	147	255	java/lang/Throwable
    //   114	147	299	finally
    //   153	162	314	finally
    //   164	170	314	finally
    //   172	181	314	finally
    //   208	214	314	finally
    //   216	230	314	finally
    //   232	244	314	finally
    //   261	288	314	finally
    //   153	162	318	java/lang/Throwable
    //   164	170	318	java/lang/Throwable
    //   172	181	318	java/lang/Throwable
    //   208	214	318	java/lang/Throwable
    //   216	230	318	java/lang/Throwable
    //   232	244	318	java/lang/Throwable
  }
  
  /* Error */
  private void g(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 834	java/io/File
    //   5: dup
    //   6: getstatic 836	com/igexin/push/core/g:Z	Ljava/lang/String;
    //   9: invokespecial 837	java/io/File:<init>	(Ljava/lang/String;)V
    //   12: astore_2
    //   13: aload_2
    //   14: invokevirtual 840	java/io/File:exists	()Z
    //   17: ifne +55 -> 72
    //   20: aload_2
    //   21: invokevirtual 843	java/io/File:createNewFile	()Z
    //   24: ifne +48 -> 72
    //   27: new 169	java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   34: ldc_w 845
    //   37: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_2
    //   41: invokevirtual 846	java/io/File:toString	()Ljava/lang/String;
    //   44: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc_w 848
    //   50: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   59: iconst_0
    //   60: ifeq +11 -> 71
    //   63: new 850	java/lang/NullPointerException
    //   66: dup
    //   67: invokespecial 851	java/lang/NullPointerException:<init>	()V
    //   70: athrow
    //   71: return
    //   72: new 853	java/io/FileOutputStream
    //   75: dup
    //   76: getstatic 836	com/igexin/push/core/g:Z	Ljava/lang/String;
    //   79: invokespecial 854	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   82: astore_2
    //   83: aload_2
    //   84: aload_1
    //   85: invokestatic 856	com/igexin/b/b/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   88: invokevirtual 860	java/lang/String:getBytes	()[B
    //   91: invokevirtual 863	java/io/FileOutputStream:write	([B)V
    //   94: aload_2
    //   95: ifnull -24 -> 71
    //   98: aload_2
    //   99: invokevirtual 864	java/io/FileOutputStream:close	()V
    //   102: return
    //   103: astore_1
    //   104: return
    //   105: astore_1
    //   106: aload_3
    //   107: astore_2
    //   108: aload_2
    //   109: ifnull -38 -> 71
    //   112: aload_2
    //   113: invokevirtual 864	java/io/FileOutputStream:close	()V
    //   116: return
    //   117: astore_1
    //   118: return
    //   119: astore_1
    //   120: aconst_null
    //   121: astore_2
    //   122: aload_2
    //   123: ifnull +7 -> 130
    //   126: aload_2
    //   127: invokevirtual 864	java/io/FileOutputStream:close	()V
    //   130: aload_1
    //   131: athrow
    //   132: astore_1
    //   133: return
    //   134: astore_2
    //   135: goto -5 -> 130
    //   138: astore_1
    //   139: goto -17 -> 122
    //   142: astore_1
    //   143: goto -35 -> 108
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	146	0	this	f
    //   0	146	1	paramString	String
    //   12	115	2	localObject1	Object
    //   134	1	2	localException	Exception
    //   1	106	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   98	102	103	java/lang/Exception
    //   2	59	105	java/lang/Exception
    //   72	83	105	java/lang/Exception
    //   112	116	117	java/lang/Exception
    //   2	59	119	finally
    //   72	83	119	finally
    //   63	71	132	java/lang/Exception
    //   126	130	134	java/lang/Exception
    //   83	94	138	finally
    //   83	94	142	java/lang/Exception
  }
  
  private com.igexin.push.core.a.a.a h(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (!c.contains(paramString))) {
      return null;
    }
    if ((b.containsKey(paramString)) && (b.get(paramString) != null)) {
      return (com.igexin.push.core.a.a.a)b.get(paramString);
    }
    if (paramString.equals("goto")) {
      b.put("goto", new com.igexin.push.core.a.a.g());
    }
    for (;;)
    {
      return (com.igexin.push.core.a.a.a)b.get(paramString);
      if (paramString.equals("notification")) {
        b.put("notification", new com.igexin.push.core.a.a.h());
      } else if (paramString.equals("terminatetask")) {
        b.put("terminatetask", new com.igexin.push.core.a.a.m());
      } else if (paramString.equals("startmyactivity")) {
        b.put("startmyactivity", new com.igexin.push.core.a.a.j());
      } else if (paramString.equals("startapp")) {
        b.put("startapp", new com.igexin.push.core.a.a.l());
      } else if (paramString.equals("null")) {
        b.put("null", new com.igexin.push.core.a.a.f());
      } else if (paramString.equals("wakeupsdk")) {
        b.put("wakeupsdk", new n());
      } else if (paramString.equals("startweb")) {
        b.put("startweb", new com.igexin.push.core.a.a.k());
      } else if (paramString.equals("checkapp")) {
        b.put("checkapp", new com.igexin.push.core.a.a.b());
      } else if (paramString.equals("cleanext")) {
        b.put("cleanext", new com.igexin.push.core.a.a.c());
      } else if (paramString.equals("enablelog")) {
        b.put("enablelog", new com.igexin.push.core.a.a.e());
      } else if (paramString.equals("disablelog")) {
        b.put("disablelog", new com.igexin.push.core.a.a.d());
      } else if (paramString.equals("reportext")) {
        b.put("reportext", new com.igexin.push.core.a.a.i());
      }
    }
  }
  
  private void h(String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setPackage(paramString1);
      localIntent.setAction("com.igexin.sdk.action.service.message");
      if (com.igexin.push.util.a.a(localIntent, com.igexin.push.core.g.f))
      {
        com.igexin.push.core.g.f.startService(localIntent);
        com.igexin.b.a.c.b.a("CoreAction|startService by action");
        return;
      }
      localIntent = new Intent();
      localIntent.setClassName(paramString1, paramString2);
      com.igexin.push.core.g.f.startService(localIntent);
      com.igexin.b.a.c.b.a("CoreAction|startService by service name");
      return;
    }
    catch (Throwable localThrowable)
    {
      com.igexin.b.a.c.b.a("CoreAction|startService pkgName = " + paramString1 + " srvName = " + paramString2 + ", exception : " + localThrowable.toString());
    }
  }
  
  public void A()
  {
    if (com.igexin.push.core.g.P < System.currentTimeMillis()) {
      com.igexin.push.core.b.g.a().a(false);
    }
  }
  
  public void B()
  {
    if (!com.igexin.push.core.g.af) {
      com.igexin.push.core.g.af = com.igexin.b.a.b.c.b().a(com.igexin.push.f.b.c.i(), false, true);
    }
    if (!com.igexin.push.core.g.ag) {
      com.igexin.push.core.g.ag = com.igexin.b.a.b.c.b().a(com.igexin.push.f.b.g.i(), true, true);
    }
    if (!com.igexin.push.core.g.ah) {
      com.igexin.push.core.f.a().c();
    }
  }
  
  public boolean C()
  {
    return (!com.igexin.push.core.g.i) || (!com.igexin.push.core.g.j);
  }
  
  public void D()
  {
    try
    {
      if (!TextUtils.isEmpty(com.igexin.push.config.m.N))
      {
        if ("none".equals(com.igexin.push.config.m.N)) {
          return;
        }
        Object localObject1 = Arrays.asList(com.igexin.push.config.m.N.split(","));
        if (!((List)localObject1).isEmpty())
        {
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator1 = com.igexin.push.core.g.ai.entrySet().iterator();
          while (localIterator1.hasNext())
          {
            Object localObject2 = (Map.Entry)localIterator1.next();
            String str1 = (String)((Map.Entry)localObject2).getKey();
            localObject2 = (PushTaskBean)((Map.Entry)localObject2).getValue();
            if (!TextUtils.isEmpty(str1))
            {
              Iterator localIterator2 = ((List)localObject1).iterator();
              while (localIterator2.hasNext())
              {
                String str2 = (String)localIterator2.next();
                if ((!TextUtils.isEmpty(str2)) && (str1.startsWith(str2)))
                {
                  localArrayList.add(((PushTaskBean)localObject2).getTaskId());
                  localIterator1.remove();
                }
              }
            }
          }
          if (!localArrayList.isEmpty())
          {
            localObject1 = new String[localArrayList.size()];
            int i = 0;
            while (i < localArrayList.size())
            {
              localObject1[i] = ((String)localArrayList.get(i));
              i += 1;
            }
            com.igexin.push.core.f.a().k().a("message", new String[] { "taskid" }, (String[])localObject1);
          }
        }
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public com.igexin.push.core.bean.g a(JSONObject paramJSONObject)
  {
    com.igexin.push.core.bean.g localG = new com.igexin.push.core.bean.g();
    localG.a(paramJSONObject.getString("version"));
    paramJSONObject = paramJSONObject.getJSONArray("extensions");
    if ((paramJSONObject != null) && (paramJSONObject.length() > 0))
    {
      HashMap localHashMap = new HashMap();
      int i = 0;
      while (i < paramJSONObject.length())
      {
        JSONObject localJSONObject = (JSONObject)paramJSONObject.get(i);
        com.igexin.push.core.bean.f localF = new com.igexin.push.core.bean.f();
        localF.a(localJSONObject.getInt("id"));
        localF.a(localJSONObject.getString("version"));
        localF.b(localJSONObject.getString("name"));
        localF.c(localJSONObject.getString("cls_name"));
        localF.d(localJSONObject.getString("url"));
        localF.e(localJSONObject.getString("checksum"));
        localF.f(localJSONObject.getString("key"));
        if (localJSONObject.has("isdestroy")) {
          localF.a(localJSONObject.getBoolean("isdestroy"));
        }
        if (localJSONObject.has("effective"))
        {
          String str = localJSONObject.getString("effective");
          long l2 = 0L;
          long l1 = l2;
          if (str != null)
          {
            l1 = l2;
            if (str.length() <= 13) {
              l1 = Long.parseLong(str);
            }
          }
          localF.a(l1);
        }
        if (localJSONObject.has("loadTime")) {
          localF.b(localJSONObject.getLong("loadTime"));
        }
        localHashMap.put(Integer.valueOf(localF.a()), localF);
        i += 1;
      }
      localG.a(localHashMap);
      return localG;
    }
    localG.a(new HashMap());
    return localG;
  }
  
  public Class a(Context paramContext)
  {
    return GTServiceManager.getInstance().getUserPushService(paramContext);
  }
  
  public String a(com.igexin.push.core.bean.g paramG)
  {
    JSONObject localJSONObject = new JSONObject();
    label375:
    for (;;)
    {
      try
      {
        Object localObject3 = paramG.a();
        Object localObject2 = paramG.b();
        Object localObject1 = "[]";
        if (localObject3 != null) {
          localJSONObject.put("version", localObject3);
        }
        paramG = (com.igexin.push.core.bean.g)localObject1;
        if (localObject2 != null)
        {
          paramG = (com.igexin.push.core.bean.g)localObject1;
          if (((Map)localObject2).size() > 0)
          {
            paramG = "[";
            localObject1 = ((Map)localObject2).entrySet().iterator();
            if (((Iterator)localObject1).hasNext())
            {
              localObject2 = (com.igexin.push.core.bean.f)((Map.Entry)((Iterator)localObject1).next()).getValue();
              localObject3 = new JSONObject();
              ((JSONObject)localObject3).put("id", ((com.igexin.push.core.bean.f)localObject2).a());
              ((JSONObject)localObject3).put("version", ((com.igexin.push.core.bean.f)localObject2).b());
              ((JSONObject)localObject3).put("name", ((com.igexin.push.core.bean.f)localObject2).c());
              ((JSONObject)localObject3).put("cls_name", ((com.igexin.push.core.bean.f)localObject2).d());
              ((JSONObject)localObject3).put("url", ((com.igexin.push.core.bean.f)localObject2).e());
              ((JSONObject)localObject3).put("checksum", ((com.igexin.push.core.bean.f)localObject2).f());
              ((JSONObject)localObject3).put("isdestroy", ((com.igexin.push.core.bean.f)localObject2).g());
              ((JSONObject)localObject3).put("effective", ((com.igexin.push.core.bean.f)localObject2).h());
              ((JSONObject)localObject3).put("loadTime", ((com.igexin.push.core.bean.f)localObject2).i());
              ((JSONObject)localObject3).put("key", ((com.igexin.push.core.bean.f)localObject2).j());
              paramG = paramG + ((JSONObject)localObject3).toString();
              paramG = paramG + ",";
              continue;
            }
            if (!paramG.endsWith(",")) {
              break label375;
            }
            paramG = paramG.substring(0, paramG.length() - 1);
            paramG = paramG + "]";
          }
        }
        localJSONObject.put("extensions", new JSONArray(paramG));
        paramG = localJSONObject.toString();
        return paramG;
      }
      catch (Exception paramG)
      {
        com.igexin.b.a.c.b.a(paramG.toString());
        return null;
      }
    }
  }
  
  /* Error */
  public String a(boolean paramBoolean, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: aconst_null
    //   4: astore 12
    //   6: new 1123	java/text/SimpleDateFormat
    //   9: dup
    //   10: ldc_w 1125
    //   13: invokestatic 1131	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   16: invokespecial 1134	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   19: new 576	java/util/Date
    //   22: dup
    //   23: invokespecial 1135	java/util/Date:<init>	()V
    //   26: invokevirtual 1139	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   29: astore 13
    //   31: iload_2
    //   32: iconst_m1
    //   33: if_icmpne +83 -> 116
    //   36: new 169	java/lang/StringBuilder
    //   39: dup
    //   40: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   43: aload 13
    //   45: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: ldc_w 1141
    //   51: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   57: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: ldc_w 1141
    //   63: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: ldc_w 1145
    //   69: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: ldc_w 1141
    //   75: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: getstatic 1148	com/igexin/push/core/g:s	Ljava/lang/String;
    //   81: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: astore 13
    //   89: aload 13
    //   91: astore 11
    //   93: aload 11
    //   95: astore 13
    //   97: aload 12
    //   99: ifnull +14 -> 113
    //   102: aload 12
    //   104: invokeinterface 410 1 0
    //   109: aload 11
    //   111: astore 13
    //   113: aload 13
    //   115: areturn
    //   116: iload_2
    //   117: ifne +983 -> 1100
    //   120: iload_1
    //   121: ifeq +578 -> 699
    //   124: invokestatic 197	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   127: invokevirtual 276	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   130: ldc_w 1150
    //   133: iconst_1
    //   134: anewarray 280	java/lang/String
    //   137: dup
    //   138: iconst_0
    //   139: ldc_w 1152
    //   142: aastore
    //   143: iconst_2
    //   144: anewarray 280	java/lang/String
    //   147: dup
    //   148: iconst_0
    //   149: ldc_w 828
    //   152: aastore
    //   153: dup
    //   154: iconst_1
    //   155: ldc_w 1154
    //   158: aastore
    //   159: aconst_null
    //   160: aconst_null
    //   161: invokevirtual 289	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   164: astore 12
    //   166: aload 12
    //   168: astore 11
    //   170: aload 11
    //   172: ifnull +914 -> 1086
    //   175: aconst_null
    //   176: astore 12
    //   178: aload 12
    //   180: astore 13
    //   182: aload 11
    //   184: invokeinterface 294 1 0
    //   189: ifeq +900 -> 1089
    //   192: aload 11
    //   194: aload 11
    //   196: ldc_w 1156
    //   199: invokeinterface 1159 2 0
    //   204: invokeinterface 369 2 0
    //   209: istore_2
    //   210: aload 11
    //   212: aload 11
    //   214: ldc_w 1161
    //   217: invokeinterface 1159 2 0
    //   222: invokeinterface 369 2 0
    //   227: istore_3
    //   228: aload 11
    //   230: aload 11
    //   232: ldc_w 1163
    //   235: invokeinterface 1159 2 0
    //   240: invokeinterface 369 2 0
    //   245: istore 4
    //   247: aload 11
    //   249: aload 11
    //   251: ldc_w 1165
    //   254: invokeinterface 1159 2 0
    //   259: invokeinterface 369 2 0
    //   264: istore 5
    //   266: aload 11
    //   268: aload 11
    //   270: ldc_w 1167
    //   273: invokeinterface 1159 2 0
    //   278: invokeinterface 369 2 0
    //   283: istore 6
    //   285: aload 11
    //   287: aload 11
    //   289: ldc_w 1169
    //   292: invokeinterface 1159 2 0
    //   297: invokeinterface 369 2 0
    //   302: istore 7
    //   304: aload 11
    //   306: aload 11
    //   308: ldc_w 1171
    //   311: invokeinterface 1159 2 0
    //   316: invokeinterface 369 2 0
    //   321: istore 8
    //   323: new 169	java/lang/StringBuilder
    //   326: dup
    //   327: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   330: aload 11
    //   332: aload 11
    //   334: ldc_w 1173
    //   337: invokeinterface 1159 2 0
    //   342: invokeinterface 1175 2 0
    //   347: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: ldc_w 1177
    //   353: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: astore 13
    //   361: aload 12
    //   363: ifnonnull +379 -> 742
    //   366: new 169	java/lang/StringBuilder
    //   369: dup
    //   370: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   373: aload 13
    //   375: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: ldc_w 1141
    //   381: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   387: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: ldc_w 1141
    //   393: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: ldc_w 1179
    //   399: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: ldc_w 1141
    //   405: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: iload_2
    //   409: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   412: ldc_w 1184
    //   415: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   418: aload 13
    //   420: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: ldc_w 1141
    //   426: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   429: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   432: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: ldc_w 1141
    //   438: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: ldc_w 1186
    //   444: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: ldc_w 1141
    //   450: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: iload_3
    //   454: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   457: ldc_w 1184
    //   460: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: aload 13
    //   465: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   468: ldc_w 1141
    //   471: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   474: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   477: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   480: ldc_w 1141
    //   483: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: ldc_w 1188
    //   489: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: ldc_w 1141
    //   495: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: iload 4
    //   500: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   503: ldc_w 1184
    //   506: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   509: aload 13
    //   511: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   514: ldc_w 1141
    //   517: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   523: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: ldc_w 1141
    //   529: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   532: ldc_w 1190
    //   535: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   538: ldc_w 1141
    //   541: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   544: iload 5
    //   546: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   549: ldc_w 1184
    //   552: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   555: aload 13
    //   557: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: ldc_w 1141
    //   563: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   569: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: ldc_w 1141
    //   575: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   578: ldc_w 1192
    //   581: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: ldc_w 1141
    //   587: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   590: iload 6
    //   592: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   595: ldc_w 1184
    //   598: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   601: aload 13
    //   603: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   606: ldc_w 1141
    //   609: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   612: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   615: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   618: ldc_w 1141
    //   621: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   624: ldc_w 1194
    //   627: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: ldc_w 1141
    //   633: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: iload 7
    //   638: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   641: ldc_w 1184
    //   644: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   647: aload 13
    //   649: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   652: ldc_w 1141
    //   655: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   658: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   661: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: ldc_w 1141
    //   667: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: ldc_w 1196
    //   673: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   676: ldc_w 1141
    //   679: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   682: iload 8
    //   684: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   687: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   690: astore 13
    //   692: aload 13
    //   694: astore 12
    //   696: goto -518 -> 178
    //   699: invokestatic 197	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   702: invokevirtual 276	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   705: ldc_w 1150
    //   708: iconst_1
    //   709: anewarray 280	java/lang/String
    //   712: dup
    //   713: iconst_0
    //   714: ldc_w 1152
    //   717: aastore
    //   718: iconst_1
    //   719: anewarray 280	java/lang/String
    //   722: dup
    //   723: iconst_0
    //   724: ldc_w 1154
    //   727: aastore
    //   728: aconst_null
    //   729: aconst_null
    //   730: invokevirtual 289	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   733: astore 12
    //   735: aload 12
    //   737: astore 11
    //   739: goto -569 -> 170
    //   742: new 169	java/lang/StringBuilder
    //   745: dup
    //   746: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   749: aload 12
    //   751: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   754: ldc_w 1184
    //   757: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   760: aload 13
    //   762: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   765: ldc_w 1141
    //   768: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   771: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   774: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   777: ldc_w 1141
    //   780: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   783: ldc_w 1179
    //   786: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   789: ldc_w 1141
    //   792: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   795: iload_2
    //   796: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   799: ldc_w 1184
    //   802: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: aload 13
    //   807: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: ldc_w 1141
    //   813: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   816: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   819: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   822: ldc_w 1141
    //   825: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   828: ldc_w 1186
    //   831: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   834: ldc_w 1141
    //   837: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   840: iload_3
    //   841: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   844: ldc_w 1184
    //   847: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   850: aload 13
    //   852: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   855: ldc_w 1141
    //   858: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   861: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   864: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   867: ldc_w 1141
    //   870: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   873: ldc_w 1188
    //   876: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   879: ldc_w 1141
    //   882: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   885: iload 4
    //   887: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   890: ldc_w 1184
    //   893: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   896: aload 13
    //   898: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   901: ldc_w 1141
    //   904: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   907: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   910: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   913: ldc_w 1141
    //   916: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: ldc_w 1190
    //   922: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   925: ldc_w 1141
    //   928: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   931: iload 5
    //   933: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   936: ldc_w 1184
    //   939: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   942: aload 13
    //   944: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   947: ldc_w 1141
    //   950: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   953: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   956: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   959: ldc_w 1141
    //   962: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   965: ldc_w 1192
    //   968: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   971: ldc_w 1141
    //   974: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   977: iload 6
    //   979: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   982: ldc_w 1184
    //   985: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   988: aload 13
    //   990: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   993: ldc_w 1141
    //   996: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   999: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   1002: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1005: ldc_w 1141
    //   1008: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1011: ldc_w 1194
    //   1014: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1017: ldc_w 1141
    //   1020: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1023: iload 7
    //   1025: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1028: ldc_w 1184
    //   1031: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1034: aload 13
    //   1036: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1039: ldc_w 1141
    //   1042: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1045: getstatic 1143	com/igexin/push/core/g:B	Ljava/lang/String;
    //   1048: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1051: ldc_w 1141
    //   1054: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1057: ldc_w 1196
    //   1060: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1063: ldc_w 1141
    //   1066: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1069: iload 8
    //   1071: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1074: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1077: astore 13
    //   1079: aload 13
    //   1081: astore 12
    //   1083: goto -905 -> 178
    //   1086: aconst_null
    //   1087: astore 13
    //   1089: aload 11
    //   1091: astore 12
    //   1093: aload 13
    //   1095: astore 11
    //   1097: goto -1004 -> 93
    //   1100: iload_2
    //   1101: iconst_1
    //   1102: if_icmpne +145 -> 1247
    //   1105: invokestatic 125	com/igexin/push/core/i:a	()Lcom/igexin/push/core/i;
    //   1108: getfield 1198	com/igexin/push/core/i:a	J
    //   1111: lstore 9
    //   1113: getstatic 1200	com/igexin/push/config/m:d	I
    //   1116: ifle +13 -> 1129
    //   1119: getstatic 1200	com/igexin/push/config/m:d	I
    //   1122: sipush 1000
    //   1125: imul
    //   1126: i2l
    //   1127: lstore 9
    //   1129: new 169	java/lang/StringBuilder
    //   1132: dup
    //   1133: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1136: getstatic 595	com/igexin/push/config/m:a	I
    //   1139: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1142: ldc_w 732
    //   1145: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1148: getstatic 597	com/igexin/push/config/m:b	I
    //   1151: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1154: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1157: astore 14
    //   1159: new 169	java/lang/StringBuilder
    //   1162: dup
    //   1163: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1166: aload 13
    //   1168: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1171: ldc_w 1141
    //   1174: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1177: getstatic 1203	com/igexin/push/core/g:r	Ljava/lang/String;
    //   1180: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: ldc_w 1141
    //   1186: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1189: getstatic 251	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1192: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1195: ldc_w 1141
    //   1198: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1201: getstatic 969	com/igexin/push/core/g:i	Z
    //   1204: invokevirtual 179	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   1207: ldc_w 1141
    //   1210: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1213: aload 14
    //   1215: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1218: ldc_w 1141
    //   1221: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1224: lload 9
    //   1226: invokevirtual 1206	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1229: ldc_w 1141
    //   1232: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1235: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1238: astore 13
    //   1240: aload 13
    //   1242: astore 11
    //   1244: goto -1151 -> 93
    //   1247: iload_2
    //   1248: iconst_4
    //   1249: if_icmpne +57 -> 1306
    //   1252: new 169	java/lang/StringBuilder
    //   1255: dup
    //   1256: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1259: aload 13
    //   1261: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1264: ldc_w 1141
    //   1267: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: getstatic 1203	com/igexin/push/core/g:r	Ljava/lang/String;
    //   1273: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1276: ldc_w 1141
    //   1279: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1282: getstatic 251	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1285: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1288: ldc_w 1141
    //   1291: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1294: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1297: astore 13
    //   1299: aload 13
    //   1301: astore 11
    //   1303: goto -1210 -> 93
    //   1306: iload_2
    //   1307: iconst_5
    //   1308: if_icmpne +105 -> 1413
    //   1311: new 169	java/lang/StringBuilder
    //   1314: dup
    //   1315: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1318: aload 13
    //   1320: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1323: ldc_w 1141
    //   1326: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1329: getstatic 1203	com/igexin/push/core/g:r	Ljava/lang/String;
    //   1332: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1335: ldc_w 1141
    //   1338: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1341: getstatic 251	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1344: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1347: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1350: astore 13
    //   1352: aload 13
    //   1354: astore 11
    //   1356: goto -1263 -> 93
    //   1359: astore 12
    //   1361: aconst_null
    //   1362: astore 12
    //   1364: aload 12
    //   1366: astore 13
    //   1368: aload 11
    //   1370: ifnull -1257 -> 113
    //   1373: aload 11
    //   1375: invokeinterface 410 1 0
    //   1380: aload 12
    //   1382: areturn
    //   1383: astore 12
    //   1385: aconst_null
    //   1386: astore 11
    //   1388: aload 11
    //   1390: ifnull +10 -> 1400
    //   1393: aload 11
    //   1395: invokeinterface 410 1 0
    //   1400: aload 12
    //   1402: athrow
    //   1403: astore 12
    //   1405: goto -17 -> 1388
    //   1408: astore 13
    //   1410: goto -46 -> 1364
    //   1413: aconst_null
    //   1414: astore 11
    //   1416: goto -1323 -> 93
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1419	0	this	f
    //   0	1419	1	paramBoolean	boolean
    //   0	1419	2	paramInt	int
    //   227	614	3	i	int
    //   245	641	4	j	int
    //   264	668	5	k	int
    //   283	695	6	m	int
    //   302	722	7	n	int
    //   321	749	8	i1	int
    //   1111	114	9	l	long
    //   1	1414	11	localObject1	Object
    //   4	1088	12	localObject2	Object
    //   1359	1	12	localException1	Exception
    //   1362	19	12	str1	String
    //   1383	18	12	localObject3	Object
    //   1403	1	12	localObject4	Object
    //   29	1338	13	localObject5	Object
    //   1408	1	13	localException2	Exception
    //   1157	57	14	str2	String
    // Exception table:
    //   from	to	target	type
    //   36	89	1359	java/lang/Exception
    //   124	166	1359	java/lang/Exception
    //   699	735	1359	java/lang/Exception
    //   1105	1113	1359	java/lang/Exception
    //   1113	1129	1359	java/lang/Exception
    //   1129	1240	1359	java/lang/Exception
    //   1252	1299	1359	java/lang/Exception
    //   1311	1352	1359	java/lang/Exception
    //   36	89	1383	finally
    //   124	166	1383	finally
    //   699	735	1383	finally
    //   1105	1113	1383	finally
    //   1113	1129	1383	finally
    //   1129	1240	1383	finally
    //   1252	1299	1383	finally
    //   1311	1352	1383	finally
    //   182	361	1403	finally
    //   366	692	1403	finally
    //   742	1079	1403	finally
    //   182	361	1408	java/lang/Exception
    //   366	692	1408	java/lang/Exception
    //   742	1079	1408	java/lang/Exception
  }
  
  public void a(int paramInt)
  {
    if (com.igexin.push.core.g.f == null) {
      return;
    }
    try
    {
      localObject = b(com.igexin.push.core.g.f);
      if (localObject != null)
      {
        localObject = new Intent(com.igexin.push.core.g.f, (Class)localObject);
        localBundle = new Bundle();
        localBundle.putInt("action", 10008);
        localBundle.putInt("pid", paramInt);
        ((Intent)localObject).putExtras(localBundle);
        com.igexin.push.core.g.f.startService((Intent)localObject);
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Object localObject;
        Bundle localBundle;
        com.igexin.b.a.c.b.a("CoreAction|" + localThrowable.toString());
      }
    }
    localObject = G();
    localBundle = new Bundle();
    localBundle.putInt("action", 10008);
    localBundle.putInt("pid", paramInt);
    ((Intent)localObject).putExtras(localBundle);
    com.igexin.push.core.g.f.sendBroadcast((Intent)localObject);
  }
  
  public void a(int paramInt1, int paramInt2, String paramString)
  {
    com.igexin.push.config.m.a = paramInt1;
    com.igexin.push.config.m.b = paramInt2;
    com.igexin.push.config.a.a().b();
    com.igexin.push.a.a.c.c().d();
  }
  
  public void a(int paramInt, String paramString)
  {
    com.igexin.push.config.m.d = paramInt;
    com.igexin.push.config.a.a().c();
    if (com.igexin.push.core.g.l)
    {
      com.igexin.b.a.c.b.a("setHeartbeatInterval heartbeatReq");
      if (System.currentTimeMillis() - com.igexin.push.core.g.Q > 5000L)
      {
        com.igexin.push.core.g.Q = System.currentTimeMillis();
        g();
      }
    }
  }
  
  public void a(ContentValues paramContentValues)
  {
    try
    {
      if (com.igexin.push.core.g.aE < 2000)
      {
        if (com.igexin.push.core.f.a().k().a("message", paramContentValues)) {
          com.igexin.push.core.g.aE += 1;
        }
      }
      else
      {
        int i = com.igexin.push.core.f.a().k().a("message", "id IN (SELECT id from message where status IS NULL or status=1 or status=2 order by id asc limit 500)");
        com.igexin.push.core.g.aE -= i;
        if (i < 500)
        {
          String str = "id IN (SELECT id from message where status=0 order by id asc limit " + (500 - i) + ")";
          i = com.igexin.push.core.f.a().k().a("message", str);
          com.igexin.push.core.g.aE -= i;
        }
        if (com.igexin.push.core.f.a().k().a("message", paramContentValues))
        {
          com.igexin.push.core.g.aE += 1;
          return;
        }
      }
    }
    catch (Throwable paramContentValues) {}
  }
  
  public void a(Intent paramIntent)
  {
    com.igexin.b.a.c.b.a("CoreAction|onServiceInitialize ##");
    if (paramIntent != null)
    {
      com.igexin.push.core.f.a().a(false);
      if (!paramIntent.hasExtra("op_app")) {
        break label122;
      }
      com.igexin.push.core.g.D = paramIntent.getStringExtra("op_app");
      com.igexin.push.core.g.m = false;
      if (com.igexin.push.core.g.l)
      {
        k();
        com.igexin.push.core.g.m = true;
      }
      if ((GTServiceManager.getInstance().isUserPushServiceSet(com.igexin.push.core.g.f)) && (com.igexin.push.core.g.ad != null))
      {
        paramIntent = GTServiceManager.getInstance().getUserPushService(com.igexin.push.core.g.f).getName();
        if ((paramIntent == null) || (paramIntent.equals(com.igexin.push.core.a.n))) {
          break label131;
        }
        paramIntent = com.igexin.b.b.a.b(paramIntent.getBytes());
        if (paramIntent != null) {
          com.igexin.push.util.f.a(paramIntent, com.igexin.push.core.g.ad, false);
        }
      }
    }
    label122:
    label131:
    while (!new File(com.igexin.push.core.g.ad).delete())
    {
      return;
      com.igexin.push.core.g.D = "";
      break;
    }
    com.igexin.b.a.c.b.a("del " + com.igexin.push.core.g.ad + " success ~~~");
  }
  
  public void a(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("action");
    if (TextUtils.isEmpty(str1)) {}
    do
    {
      String str2;
      String str3;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return;
                    if (!str1.equals("setTag")) {
                      break;
                    }
                  } while (!com.igexin.push.config.m.j);
                  a(paramBundle.getString("tags"), paramBundle.getString("sn"));
                  return;
                  if (!str1.equals("setSilentTime")) {
                    break;
                  }
                } while (!com.igexin.push.config.m.k);
                int i = paramBundle.getInt("beginHour", 0);
                int j = paramBundle.getInt("duration", 0);
                a(i, j, com.igexin.push.core.g.f.getPackageName());
                AssistPushManager.getInstance().setSilentTime(com.igexin.push.core.g.f, i, j);
                return;
                if (!str1.equals("sendMessage")) {
                  break;
                }
                com.igexin.b.a.c.b.a("CoreAction onPushManagerMessage recevie action : sendMessage");
              } while (!com.igexin.push.config.m.i);
              str1 = paramBundle.getString("taskid");
              paramBundle = paramBundle.getByteArray("extraData");
              com.igexin.b.a.c.b.a("CoreAction receive broadcast msg data , task id : " + str1 + " ######@##@@@#");
              a(str1, paramBundle);
              return;
              if (str1.equals("stopService"))
              {
                com.igexin.push.core.f.a().a(com.igexin.push.core.g.f.getPackageName());
                return;
              }
              if (!str1.equals("setHeartbeatInterval")) {
                break;
              }
            } while (!com.igexin.push.config.m.l);
            a(paramBundle.getInt("interval", 0), com.igexin.push.core.g.f.getPackageName());
            return;
            if (!str1.equals("setSocketTimeout")) {
              break;
            }
          } while (!com.igexin.push.config.m.m);
          b(paramBundle.getInt("timeout", 0), com.igexin.push.core.g.f.getPackageName());
          return;
          if (!str1.equals("sendFeedbackMessage")) {
            break;
          }
        } while ((!com.igexin.push.config.m.r) || (com.igexin.push.core.g.ao > 200));
        str1 = paramBundle.getString("taskid");
        str2 = paramBundle.getString("messageid");
        paramBundle = paramBundle.getString("actionid");
        str3 = str1 + ":" + str2 + ":" + paramBundle;
      } while (com.igexin.push.core.g.an.get(str3) != null);
      long l = System.currentTimeMillis();
      PushTaskBean localPushTaskBean = new PushTaskBean();
      localPushTaskBean.setTaskId(str1);
      localPushTaskBean.setMessageId(str2);
      localPushTaskBean.setAppid(com.igexin.push.core.g.a);
      localPushTaskBean.setAppKey(com.igexin.push.core.g.b);
      b(localPushTaskBean, paramBundle);
      com.igexin.push.core.g.ao += 1;
      com.igexin.push.core.g.an.put(str3, Long.valueOf(l));
      return;
      if (str1.equals("turnOffPush"))
      {
        com.igexin.push.core.f.a().b(com.igexin.push.core.g.f.getPackageName());
        AssistPushManager.getInstance().turnOffPush(com.igexin.push.core.g.f);
        return;
      }
      if (str1.equals("bindAlias"))
      {
        str1 = paramBundle.getString("alias");
        paramBundle = paramBundle.getString("sn");
        com.igexin.b.a.c.b.a("CoreAction|onPushManagerMessage bindAlias...");
        b(str1, paramBundle);
        return;
      }
      if (str1.equals("unbindAlias"))
      {
        str1 = paramBundle.getString("alias");
        str2 = paramBundle.getString("sn");
        boolean bool = paramBundle.getBoolean("isSeft");
        com.igexin.b.a.c.b.a("CoreAction|onPushManagerMessage unbindAlias...");
        a(str1, str2, bool);
        return;
      }
    } while (!str1.equals("sendApplinkFeedback"));
    b(paramBundle.getString("url"));
  }
  
  public void a(PushTaskBean paramPushTaskBean)
  {
    com.igexin.push.d.c.c localC = new com.igexin.push.d.c.c();
    localC.a();
    localC.c = ("RCV" + paramPushTaskBean.getMessageId());
    localC.d = com.igexin.push.core.g.r;
    localC.a = ((int)System.currentTimeMillis());
    com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.r, localC);
    com.igexin.b.a.c.b.a("CoreAction|cdnreceive " + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId());
  }
  
  public void a(PushTaskBean paramPushTaskBean, String paramString)
  {
    a(paramPushTaskBean, "405" + paramString, "ok");
  }
  
  public void a(PushTaskBean paramPushTaskBean, String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis();
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("action", "pushmessage_feedback");
      ((JSONObject)localObject).put("appid", paramPushTaskBean.getAppid());
      ((JSONObject)localObject).put("id", String.valueOf(l));
      ((JSONObject)localObject).put("appkey", paramPushTaskBean.getAppKey());
      ((JSONObject)localObject).put("messageid", paramPushTaskBean.getMessageId());
      ((JSONObject)localObject).put("taskid", paramPushTaskBean.getTaskId());
      ((JSONObject)localObject).put("actionid", paramString1);
      ((JSONObject)localObject).put("result", paramString2);
      ((JSONObject)localObject).put("timestamp", String.valueOf(System.currentTimeMillis()));
      paramString2 = ((JSONObject)localObject).toString();
      localObject = new com.igexin.push.d.c.d();
      ((com.igexin.push.d.c.d)localObject).a();
      ((com.igexin.push.d.c.d)localObject).a = ((int)l);
      ((com.igexin.push.d.c.d)localObject).d = "17258000";
      ((com.igexin.push.d.c.d)localObject).e = paramString2;
      ((com.igexin.push.d.c.d)localObject).g = com.igexin.push.core.g.r;
      if (com.igexin.push.core.f.a().g() != null) {
        com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.r, (com.igexin.push.d.c.e)localObject);
      }
      localObject = com.igexin.push.core.b.d.a();
      if (localObject != null) {
        ((com.igexin.push.core.b.d)localObject).a(new com.igexin.push.core.bean.j(l, paramString2, (byte)3, l));
      }
      com.igexin.b.a.c.b.a("feedback|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId() + "|" + paramString1);
      return;
    }
    catch (Exception paramString2)
    {
      for (;;) {}
    }
  }
  
  public void a(String paramString)
  {
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("action", "received");
      ((JSONObject)localObject).put("id", paramString);
      paramString = ((JSONObject)localObject).toString();
      localObject = new com.igexin.push.d.c.d();
      ((com.igexin.push.d.c.d)localObject).a();
      ((com.igexin.push.d.c.d)localObject).a = ((int)System.currentTimeMillis());
      ((com.igexin.push.d.c.d)localObject).d = "17258000";
      ((com.igexin.push.d.c.d)localObject).e = paramString;
      ((com.igexin.push.d.c.d)localObject).g = com.igexin.push.core.g.r;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.r, (com.igexin.push.d.c.e)localObject);
      return;
    }
    catch (JSONException paramString)
    {
      for (;;) {}
    }
  }
  
  public void a(String paramString, com.igexin.push.d.c.a paramA, PushTaskBean paramPushTaskBean)
  {
    paramString = new com.igexin.push.f.a.a(new com.igexin.push.core.c.b(paramString, paramA, paramPushTaskBean));
    com.igexin.b.a.b.c.b().a(paramString, false, true);
  }
  
  /* Error */
  public void a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 1203	com/igexin/push/core/g:r	Ljava/lang/String;
    //   3: invokestatic 637	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifeq +4 -> 10
    //   9: return
    //   10: invokestatic 804	java/lang/System:currentTimeMillis	()J
    //   13: lstore_3
    //   14: new 308	org/json/JSONObject
    //   17: dup
    //   18: invokespecial 1075	org/json/JSONObject:<init>	()V
    //   21: astore 5
    //   23: aload 5
    //   25: ldc_w 463
    //   28: ldc_w 1494
    //   31: invokevirtual 1084	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   34: pop
    //   35: aload 5
    //   37: ldc_w 320
    //   40: lload_3
    //   41: invokestatic 1446	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   44: invokevirtual 1084	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   47: pop
    //   48: aload 5
    //   50: ldc_w 1496
    //   53: getstatic 1203	com/igexin/push/core/g:r	Ljava/lang/String;
    //   56: invokevirtual 1084	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   59: pop
    //   60: aload 5
    //   62: ldc_w 326
    //   65: getstatic 251	com/igexin/push/core/g:a	Ljava/lang/String;
    //   68: invokevirtual 1084	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   71: pop
    //   72: aload 5
    //   74: ldc_w 1305
    //   77: aload_1
    //   78: ldc_w 1498
    //   81: invokestatic 1503	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   84: invokevirtual 1084	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   87: pop
    //   88: aload 5
    //   90: ldc_w 1307
    //   93: aload_2
    //   94: invokevirtual 1084	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   97: pop
    //   98: aload 5
    //   100: invokevirtual 404	org/json/JSONObject:toString	()Ljava/lang/String;
    //   103: astore_1
    //   104: invokestatic 1470	com/igexin/push/core/b/d:a	()Lcom/igexin/push/core/b/d;
    //   107: astore_2
    //   108: aload_2
    //   109: ifnull +18 -> 127
    //   112: aload_2
    //   113: new 1472	com/igexin/push/core/bean/j
    //   116: dup
    //   117: lload_3
    //   118: aload_1
    //   119: iconst_2
    //   120: lload_3
    //   121: invokespecial 1475	com/igexin/push/core/bean/j:<init>	(JLjava/lang/String;BJ)V
    //   124: invokevirtual 1478	com/igexin/push/core/b/d:a	(Lcom/igexin/push/core/bean/j;)V
    //   127: new 1455	com/igexin/push/d/c/d
    //   130: dup
    //   131: invokespecial 1456	com/igexin/push/d/c/d:<init>	()V
    //   134: astore_2
    //   135: aload_2
    //   136: invokevirtual 1457	com/igexin/push/d/c/d:a	()V
    //   139: aload_2
    //   140: ldc_w 1460
    //   143: putfield 1461	com/igexin/push/d/c/d:d	Ljava/lang/String;
    //   146: aload_2
    //   147: aload_1
    //   148: putfield 1463	com/igexin/push/d/c/d:e	Ljava/lang/Object;
    //   151: invokestatic 149	com/igexin/b/a/b/c:b	()Lcom/igexin/b/a/b/c;
    //   154: invokestatic 1508	com/igexin/push/config/SDKUrlConfig:getCmAddress	()Ljava/lang/String;
    //   157: iconst_3
    //   158: invokestatic 197	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   161: invokevirtual 1511	com/igexin/push/core/f:f	()Lcom/igexin/b/a/b/b;
    //   164: aload_2
    //   165: iconst_0
    //   166: invokevirtual 1514	com/igexin/b/a/b/c:a	(Ljava/lang/String;ILcom/igexin/b/a/b/b;Ljava/lang/Object;Z)Lcom/igexin/b/a/b/e;
    //   169: pop
    //   170: ldc_w 1516
    //   173: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   176: return
    //   177: astore_1
    //   178: return
    //   179: astore_1
    //   180: goto -82 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	183	0	this	f
    //   0	183	1	paramString1	String
    //   0	183	2	paramString2	String
    //   13	108	3	l	long
    //   21	78	5	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   10	23	177	java/lang/Exception
    //   98	108	177	java/lang/Exception
    //   112	127	177	java/lang/Exception
    //   127	176	177	java/lang/Exception
    //   23	98	179	java/lang/Exception
  }
  
  @TargetApi(12)
  public void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (com.igexin.push.core.g.f == null) {
      return;
    }
    com.igexin.b.a.c.b.a("startapp|broadcastPayload");
    Object localObject1 = null;
    if (paramString4 != null) {
      paramString4 = paramString4.getBytes();
    }
    while (paramString4 != null)
    {
      com.igexin.b.a.c.b.a("startapp|broadcast|payload = " + new String(paramString4));
      try
      {
        localObject1 = b(com.igexin.push.core.g.f);
        if ((localObject1 != null) && (com.igexin.push.core.g.a != null) && (com.igexin.push.core.g.a.equals(paramString3)))
        {
          localObject1 = new Intent(com.igexin.push.core.g.f, (Class)localObject1);
          localObject2 = new Bundle();
          ((Bundle)localObject2).putInt("action", 10001);
          ((Bundle)localObject2).putSerializable("transmit_data", new GTTransmitMessage(paramString1, paramString2, paramString2 + ":" + paramString1, paramString4));
          ((Intent)localObject1).putExtras((Bundle)localObject2);
          com.igexin.push.core.g.f.startService((Intent)localObject1);
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          Object localObject2;
          com.igexin.b.a.c.b.a("CoreAction|" + localThrowable.toString());
        }
      }
      a(paramString1, paramString2, paramString3, paramString4);
      return;
      paramString4 = f(paramString1, paramString2);
      localObject2 = (PushTaskBean)com.igexin.push.core.g.ai.get(paramString4);
      paramString4 = (String)localObject1;
      if (localObject2 != null) {
        paramString4 = ((PushTaskBean)localObject2).getMsgExtra();
      }
    }
    com.igexin.b.a.c.b.a("startapp|broadcast|payload is empty!");
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    if (com.igexin.push.core.g.f == null) {
      return;
    }
    try
    {
      localObject = b(com.igexin.push.core.g.f);
      if ((localObject != null) && (com.igexin.push.core.g.a != null) && (com.igexin.push.core.g.a.equals(paramString1)))
      {
        localObject = new Intent(com.igexin.push.core.g.f, (Class)localObject);
        localBundle = new Bundle();
        localBundle.putInt("action", 10010);
        localBundle.putSerializable("cmd_msg", new FeedbackCmdMessage(paramString2, paramString3, paramString4, paramLong, 10006));
        ((Intent)localObject).putExtras(localBundle);
        com.igexin.push.core.g.f.startService((Intent)localObject);
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Object localObject;
        Bundle localBundle;
        com.igexin.b.a.c.b.a("CoreAction|" + localThrowable.toString());
      }
    }
    localObject = G();
    localBundle = new Bundle();
    localBundle.putInt("action", 10006);
    localBundle.putString("appid", paramString1);
    localBundle.putString("taskid", paramString2);
    localBundle.putString("actionid", paramString3);
    localBundle.putString("result", paramString4);
    localBundle.putLong("timestamp", paramLong);
    ((Intent)localObject).putExtras(localBundle);
    com.igexin.push.core.g.f.sendBroadcast((Intent)localObject);
  }
  
  public void a(String paramString1, String paramString2, boolean paramBoolean)
  {
    if ((paramBoolean) && (TextUtils.isEmpty(com.igexin.push.core.g.r))) {
      return;
    }
    long l = System.currentTimeMillis();
    if (l - com.igexin.push.core.g.S > 5000L)
    {
      String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(l));
      if (!str.equals(com.igexin.push.core.g.R))
      {
        com.igexin.push.core.b.g.a().f(str);
        com.igexin.push.core.b.g.a().a(0);
      }
      if (com.igexin.push.core.g.T < 100)
      {
        com.igexin.b.a.c.b.a("start unbindAlias ###");
        com.igexin.push.core.g.S = l;
        com.igexin.push.core.b.g.a().a(com.igexin.push.core.g.T + 1);
        a(paramString1, paramString2, true, paramBoolean);
        return;
      }
      com.igexin.b.a.c.b.a("CoreAction|unbindAlias times exceed");
      return;
    }
    com.igexin.b.a.c.b.a("CoreAction|unbindAlias frequently called");
  }
  
  public void a(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (TextUtils.isEmpty(com.igexin.push.core.g.r)) {
      return;
    }
    try
    {
      long l = System.currentTimeMillis();
      JSONObject localJSONObject = new JSONObject();
      String str;
      if (paramBoolean1)
      {
        str = "unbind_alias";
        if (!paramBoolean1) {
          break label237;
        }
      }
      for (byte b1 = 8;; b1 = 7)
      {
        try
        {
          localJSONObject.put("action", str);
          localJSONObject.put("id", String.valueOf(l));
          localJSONObject.put("cid", com.igexin.push.core.g.r);
          localJSONObject.put("appid", com.igexin.push.core.g.a);
          localJSONObject.put("alias", paramString1);
          localJSONObject.put("sn", paramString2);
          if (paramBoolean1) {
            localJSONObject.put("is_self", paramBoolean2);
          }
        }
        catch (Exception paramString1)
        {
          label237:
          for (;;) {}
        }
        paramString1 = localJSONObject.toString();
        paramString2 = com.igexin.push.core.b.d.a();
        if (paramString2 != null) {
          paramString2.a(new com.igexin.push.core.bean.j(l, paramString1, b1, l));
        }
        paramString2 = new com.igexin.push.d.c.d();
        paramString2.a();
        paramString2.d = "17258000";
        paramString2.e = paramString1;
        com.igexin.b.a.b.c.b().a(SDKUrlConfig.getCmAddress(), 3, com.igexin.push.core.f.a().f(), paramString2, false);
        com.igexin.b.a.c.b.a(str + " = " + paramString1);
        return;
        str = "bind_alias";
        break;
      }
      return;
    }
    catch (Exception paramString1) {}
  }
  
  public void a(String paramString, byte[] paramArrayOfByte)
  {
    Object localObject;
    long l;
    if (com.igexin.push.core.g.r != null)
    {
      localObject = new JSONObject();
      l = System.currentTimeMillis();
    }
    try
    {
      ((JSONObject)localObject).put("action", "sendmessage");
      ((JSONObject)localObject).put("id", String.valueOf(l));
      ((JSONObject)localObject).put("cid", com.igexin.push.core.g.r);
      ((JSONObject)localObject).put("appid", com.igexin.push.core.g.a);
      ((JSONObject)localObject).put("taskid", paramString);
      ((JSONObject)localObject).put("extraData", com.igexin.push.util.i.b(paramArrayOfByte, 0));
      localObject = ((JSONObject)localObject).toString();
      com.igexin.push.core.b.d.a().a(new com.igexin.push.core.bean.j(l, (String)localObject, (byte)6, l));
      com.igexin.push.d.c.d localD = new com.igexin.push.d.c.d();
      localD.a();
      localD.a = ((int)l);
      localD.d = com.igexin.push.core.g.r;
      localD.e = localObject;
      localD.f = paramArrayOfByte;
      localD.g = com.igexin.push.core.g.r;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.r, localD);
      if ((paramString != null) && (paramString.startsWith("4T5@S_"))) {
        com.igexin.b.a.c.b.a("CoreAction sending lbs report message : " + (String)localObject);
      }
      return;
    }
    catch (Throwable paramString)
    {
      com.igexin.b.a.c.b.a("CoreAction|" + paramString.toString());
    }
  }
  
  public void a(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      PushTaskBean localPushTaskBean = new PushTaskBean();
      localPushTaskBean.parse(paramJSONObject);
      a(localPushTaskBean, paramString);
      return;
    }
    catch (Exception paramJSONObject)
    {
      com.igexin.b.a.c.b.a("CoreAction " + paramJSONObject.toString());
    }
  }
  
  public void a(boolean paramBoolean) {}
  
  public boolean a(com.igexin.b.a.d.e paramE)
  {
    return false;
  }
  
  public boolean a(com.igexin.push.d.c.e paramE)
  {
    if (paramE != null)
    {
      a localA = (a)a.get(paramE.i);
      if (((paramE instanceof com.igexin.push.d.c.j)) || ((paramE instanceof com.igexin.push.d.c.m)) || ((paramE instanceof o)) || ((paramE instanceof com.igexin.push.d.c.q)) || ((paramE instanceof com.igexin.push.d.c.h)))
      {
        com.igexin.b.a.c.b.a("CoreAction|receive : " + paramE.getClass().getName() + " resp ~~~~");
        com.igexin.b.a.b.a.a.d.a().a(paramE.getClass().getName());
      }
      if (((paramE instanceof com.igexin.push.d.c.m)) || ((paramE instanceof o)) || ((paramE instanceof com.igexin.push.d.c.q)))
      {
        com.igexin.push.core.g.E = 0L;
        com.igexin.push.c.i.a().e().b();
      }
      if (localA != null) {
        localA.a(paramE);
      }
      com.igexin.push.f.b.c.i().j();
      return true;
    }
    return false;
  }
  
  public boolean a(Object paramObject)
  {
    com.igexin.push.e.j localJ = com.igexin.push.core.f.a().g();
    if (((paramObject instanceof com.igexin.push.d.c.e)) && (localJ != null)) {
      localJ.a((com.igexin.push.d.c.e)paramObject);
    }
    do
    {
      return false;
      if ((paramObject instanceof com.igexin.push.d.b.a))
      {
        com.igexin.b.a.b.a.a.d.a().a(false);
        return false;
      }
      if ((paramObject instanceof com.igexin.push.d.b.b))
      {
        com.igexin.b.a.c.b.a("CoreAction|ReconnectCheckNotifyType ###");
        com.igexin.b.a.b.a.a.d.a().a(((com.igexin.push.d.b.b)paramObject).a());
        return false;
      }
      if ((paramObject instanceof com.igexin.push.d.b.d))
      {
        E();
        return false;
      }
    } while (!(paramObject instanceof com.igexin.push.d.b.c));
    com.igexin.b.a.c.b.a("CoreAction|ReconnectNotifyType ###");
    f(((com.igexin.push.d.b.c)paramObject).a());
    return false;
  }
  
  public boolean a(String paramString1, String paramString2, String paramString3)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("taskid", paramString1);
    localBundle.putString("messageid", paramString2);
    localBundle.putString("actionid", paramString3);
    paramString1 = Message.obtain();
    paramString1.what = com.igexin.push.core.a.g;
    paramString1.obj = localBundle;
    return com.igexin.push.core.f.a().a(paramString1);
  }
  
  public boolean a(JSONObject paramJSONObject, PushTaskBean paramPushTaskBean)
  {
    com.igexin.b.a.c.b.a("CoreAction------parse pushmessage actionchain json start-------");
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        localJSONArray = paramJSONObject.getJSONArray("action_chains");
        i = 0;
        if (i >= localJSONArray.length()) {
          break label366;
        }
        paramJSONObject = ((JSONObject)localJSONArray.get(i)).getString("type");
        if (paramJSONObject == null) {
          break label359;
        }
        localObject = com.igexin.push.extension.a.a().c().iterator();
        if (!((Iterator)localObject).hasNext()) {
          break label353;
        }
        if (!((IPushExtension)((Iterator)localObject).next()).isActionSupported(paramJSONObject)) {
          continue;
        }
        j = 1;
        if (j != 0) {
          break label359;
        }
        com.igexin.b.a.c.b.a("CoreAction|extension not suport type = " + paramJSONObject);
        if (c.contains(paramJSONObject)) {
          break label359;
        }
        return false;
      }
      catch (Throwable paramJSONObject)
      {
        JSONArray localJSONArray;
        Object localObject;
        JSONObject localJSONObject;
        String str;
        Iterator localIterator;
        com.igexin.b.a.c.b.a("CoreAction|" + paramJSONObject.toString());
        paramPushTaskBean.setActionChains(localArrayList);
        com.igexin.b.a.c.b.a("CoreAction------parse pushmessage actionchain json end-------");
        return true;
      }
      if (i < localJSONArray.length())
      {
        localJSONObject = (JSONObject)localJSONArray.get(i);
        str = localJSONObject.getString("type");
        if (str != null)
        {
          localObject = com.igexin.push.extension.a.a().c();
          paramJSONObject = null;
          localIterator = ((List)localObject).iterator();
          if (localIterator.hasNext())
          {
            localObject = ((IPushExtension)localIterator.next()).parseAction(localJSONObject);
            paramJSONObject = (JSONObject)localObject;
            if (localObject == null) {
              continue;
            }
            paramJSONObject = (JSONObject)localObject;
          }
          if (paramJSONObject != null) {
            break label350;
          }
          localObject = h(str);
          if (localObject == null) {
            break label350;
          }
          localObject = ((com.igexin.push.core.a.a.a)localObject).a(localJSONObject);
          paramJSONObject = (JSONObject)localObject;
          if (localObject != null)
          {
            ((BaseAction)localObject).setSupportExt(false);
            paramJSONObject = (JSONObject)localObject;
          }
          if (paramJSONObject == null)
          {
            com.igexin.b.a.c.b.a("CoreAction|action chains can't parse, throw whole ++++++");
            return false;
          }
          localArrayList.add(paramJSONObject);
        }
        i += 1;
      }
      label350:
      continue;
      label353:
      int j = 0;
      continue;
      label359:
      i += 1;
      continue;
      label366:
      int i = 0;
    }
  }
  
  /* Error */
  public boolean a(JSONObject paramJSONObject, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 463
    //   4: invokevirtual 381	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   7: ifeq +649 -> 656
    //   10: aload_1
    //   11: ldc_w 463
    //   14: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   17: ldc_w 1718
    //   20: invokevirtual 707	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   23: ifeq +633 -> 656
    //   26: aload_1
    //   27: ldc_w 320
    //   30: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   33: astore 9
    //   35: aload_1
    //   36: ldc_w 326
    //   39: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   42: astore 7
    //   44: aload_1
    //   45: ldc_w 328
    //   48: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   51: astore 4
    //   53: aload_1
    //   54: ldc_w 330
    //   57: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   60: astore 5
    //   62: aload_1
    //   63: ldc_w 332
    //   66: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   69: astore 10
    //   71: aload_1
    //   72: ldc_w 334
    //   75: invokevirtual 338	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   78: astore 8
    //   80: new 169	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   87: ldc_w 1720
    //   90: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: aload 5
    //   95: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: ldc_w 1141
    //   101: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: aload 4
    //   106: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: ldc_w 1141
    //   112: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: aload 7
    //   117: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: ldc_w 1141
    //   123: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: iload_3
    //   127: invokevirtual 179	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   130: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   136: aload 7
    //   138: ifnull +674 -> 812
    //   141: aload 9
    //   143: ifnull +669 -> 812
    //   146: aload 4
    //   148: ifnull +664 -> 812
    //   151: aload 5
    //   153: ifnull +659 -> 812
    //   156: aload 8
    //   158: ifnull +654 -> 812
    //   161: aload 7
    //   163: getstatic 251	com/igexin/push/core/g:a	Ljava/lang/String;
    //   166: invokevirtual 707	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   169: ifeq +643 -> 812
    //   172: new 345	com/igexin/push/core/bean/PushTaskBean
    //   175: dup
    //   176: invokespecial 346	com/igexin/push/core/bean/PushTaskBean:<init>	()V
    //   179: astore 6
    //   181: aload 6
    //   183: aload 7
    //   185: invokevirtual 349	com/igexin/push/core/bean/PushTaskBean:setAppid	(Ljava/lang/String;)V
    //   188: aload 6
    //   190: aload 4
    //   192: invokevirtual 352	com/igexin/push/core/bean/PushTaskBean:setMessageId	(Ljava/lang/String;)V
    //   195: aload 6
    //   197: aload 5
    //   199: invokevirtual 355	com/igexin/push/core/bean/PushTaskBean:setTaskId	(Ljava/lang/String;)V
    //   202: aload 6
    //   204: aload 9
    //   206: invokevirtual 358	com/igexin/push/core/bean/PushTaskBean:setId	(Ljava/lang/String;)V
    //   209: aload 6
    //   211: aload 10
    //   213: invokevirtual 361	com/igexin/push/core/bean/PushTaskBean:setAppKey	(Ljava/lang/String;)V
    //   216: aload 6
    //   218: iconst_1
    //   219: invokevirtual 365	com/igexin/push/core/bean/PushTaskBean:setCurrentActionid	(I)V
    //   222: aload_1
    //   223: ldc_w 377
    //   226: invokevirtual 381	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   229: ifeq +15 -> 244
    //   232: aload 6
    //   234: aload_1
    //   235: ldc_w 377
    //   238: invokevirtual 384	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   241: invokevirtual 387	com/igexin/push/core/bean/PushTaskBean:setCDNType	(Z)V
    //   244: invokestatic 340	com/igexin/push/core/a/f:a	()Lcom/igexin/push/core/a/f;
    //   247: aload 5
    //   249: aload 4
    //   251: invokevirtual 343	com/igexin/push/core/a/f:f	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   254: astore 9
    //   256: iload_3
    //   257: ifeq +80 -> 337
    //   260: invokestatic 340	com/igexin/push/core/a/f:a	()Lcom/igexin/push/core/a/f;
    //   263: aload 6
    //   265: ldc_w 284
    //   268: invokevirtual 1381	com/igexin/push/core/a/f:b	(Lcom/igexin/push/core/bean/PushTaskBean;Ljava/lang/String;)V
    //   271: aload 5
    //   273: invokestatic 1721	com/igexin/push/util/a:b	(Ljava/lang/String;)Z
    //   276: ifeq +35 -> 311
    //   279: new 169	java/lang/StringBuilder
    //   282: dup
    //   283: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   286: ldc_w 832
    //   289: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: aload 5
    //   294: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: ldc_w 1723
    //   300: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   306: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   309: iconst_1
    //   310: ireturn
    //   311: invokestatic 804	java/lang/System:currentTimeMillis	()J
    //   314: invokestatic 1725	com/igexin/push/util/a:a	(J)Z
    //   317: ifeq +5 -> 322
    //   320: iconst_1
    //   321: ireturn
    //   322: aload_1
    //   323: invokestatic 1727	com/igexin/push/util/a:a	(Lorg/json/JSONObject;)Z
    //   326: ifeq +11 -> 337
    //   329: ldc_w 1729
    //   332: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   335: iconst_1
    //   336: ireturn
    //   337: new 419	android/content/ContentValues
    //   340: dup
    //   341: invokespecial 420	android/content/ContentValues:<init>	()V
    //   344: astore 10
    //   346: aload 10
    //   348: ldc_w 328
    //   351: aload 4
    //   353: invokevirtual 795	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   356: aload 10
    //   358: ldc_w 330
    //   361: aload 5
    //   363: invokevirtual 795	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   366: aload 10
    //   368: ldc_w 326
    //   371: aload 7
    //   373: invokevirtual 795	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   376: aload 10
    //   378: ldc_w 797
    //   381: new 169	java/lang/StringBuilder
    //   384: dup
    //   385: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   388: ldc_w 1731
    //   391: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   394: aload 9
    //   396: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   402: invokevirtual 795	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   405: aload 10
    //   407: ldc_w 306
    //   410: aload_1
    //   411: invokevirtual 404	org/json/JSONObject:toString	()Ljava/lang/String;
    //   414: invokevirtual 860	java/lang/String:getBytes	()[B
    //   417: invokestatic 1286	com/igexin/b/b/a:b	([B)[B
    //   420: invokevirtual 1733	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   423: aload 10
    //   425: ldc_w 799
    //   428: invokestatic 804	java/lang/System:currentTimeMillis	()J
    //   431: invokestatic 809	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   434: invokevirtual 812	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   437: aload_2
    //   438: ifnull +18 -> 456
    //   441: aload 10
    //   443: ldc_w 296
    //   446: aload_2
    //   447: invokevirtual 1733	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   450: aload 6
    //   452: aload_2
    //   453: invokevirtual 375	com/igexin/push/core/bean/PushTaskBean:setMsgExtra	([B)V
    //   456: aload 8
    //   458: invokevirtual 398	org/json/JSONArray:length	()I
    //   461: ifle +23 -> 484
    //   464: invokestatic 340	com/igexin/push/core/a/f:a	()Lcom/igexin/push/core/a/f;
    //   467: aload_1
    //   468: aload 6
    //   470: invokevirtual 401	com/igexin/push/core/a/f:a	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)Z
    //   473: ifne +11 -> 484
    //   476: ldc_w 1735
    //   479: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   482: iconst_1
    //   483: ireturn
    //   484: iload_3
    //   485: ifeq +286 -> 771
    //   488: invokestatic 197	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   491: invokevirtual 276	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   494: ldc_w 278
    //   497: iconst_1
    //   498: anewarray 280	java/lang/String
    //   501: dup
    //   502: iconst_0
    //   503: ldc_w 330
    //   506: aastore
    //   507: iconst_1
    //   508: anewarray 280	java/lang/String
    //   511: dup
    //   512: iconst_0
    //   513: aload 5
    //   515: aastore
    //   516: aconst_null
    //   517: aconst_null
    //   518: invokevirtual 289	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   521: astore_2
    //   522: aload_2
    //   523: ifnull +123 -> 646
    //   526: new 169	java/lang/StringBuilder
    //   529: dup
    //   530: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   533: ldc_w 1737
    //   536: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: aload 5
    //   541: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   544: ldc_w 1739
    //   547: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: aload_2
    //   551: invokeinterface 815 1 0
    //   556: invokevirtual 1182	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   559: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   562: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   565: aload_2
    //   566: invokeinterface 815 1 0
    //   571: ifne +188 -> 759
    //   574: aload_1
    //   575: ldc_w 389
    //   578: invokevirtual 381	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   581: ifeq +77 -> 658
    //   584: aload_0
    //   585: aload_1
    //   586: aload 6
    //   588: invokespecial 392	com/igexin/push/core/a/f:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   591: aload 6
    //   593: getstatic 1741	com/igexin/push/core/a:k	I
    //   596: invokevirtual 372	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   599: aload 10
    //   601: ldc_w 282
    //   604: getstatic 1741	com/igexin/push/core/a:k	I
    //   607: invokestatic 426	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   610: invokevirtual 429	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   613: aload_0
    //   614: aload 10
    //   616: invokevirtual 818	com/igexin/push/core/a/f:a	(Landroid/content/ContentValues;)V
    //   619: getstatic 264	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   622: aload 9
    //   624: aload 6
    //   626: invokeinterface 407 3 0
    //   631: pop
    //   632: aload_1
    //   633: ldc_w 389
    //   636: invokevirtual 381	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   639: ifeq +88 -> 727
    //   642: aload_0
    //   643: invokevirtual 218	com/igexin/push/core/a/f:t	()V
    //   646: aload_2
    //   647: ifnull +9 -> 656
    //   650: aload_2
    //   651: invokeinterface 410 1 0
    //   656: iconst_1
    //   657: ireturn
    //   658: aload 6
    //   660: getstatic 1743	com/igexin/push/core/a:l	I
    //   663: invokevirtual 372	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   666: aload 10
    //   668: ldc_w 282
    //   671: getstatic 1743	com/igexin/push/core/a:l	I
    //   674: invokestatic 426	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   677: invokevirtual 429	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   680: goto -67 -> 613
    //   683: astore_1
    //   684: aload_2
    //   685: ifnull -29 -> 656
    //   688: aload_2
    //   689: invokeinterface 410 1 0
    //   694: goto -38 -> 656
    //   697: astore_1
    //   698: new 169	java/lang/StringBuilder
    //   701: dup
    //   702: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   705: ldc_w 1604
    //   708: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   711: aload_1
    //   712: invokevirtual 1120	java/lang/Exception:toString	()Ljava/lang/String;
    //   715: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   718: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   721: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   724: goto -68 -> 656
    //   727: invokestatic 340	com/igexin/push/core/a/f:a	()Lcom/igexin/push/core/a/f;
    //   730: aload 5
    //   732: aload 4
    //   734: getstatic 251	com/igexin/push/core/g:a	Ljava/lang/String;
    //   737: getstatic 478	com/igexin/push/core/g:e	Ljava/lang/String;
    //   740: invokevirtual 1745	com/igexin/push/core/a/f:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   743: goto -97 -> 646
    //   746: astore_1
    //   747: aload_2
    //   748: ifnull +9 -> 757
    //   751: aload_2
    //   752: invokeinterface 410 1 0
    //   757: aload_1
    //   758: athrow
    //   759: aload_2
    //   760: ifnull +73 -> 833
    //   763: aload_2
    //   764: invokeinterface 410 1 0
    //   769: iconst_1
    //   770: ireturn
    //   771: aload_1
    //   772: ldc_w 389
    //   775: invokevirtual 381	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   778: ifeq +10 -> 788
    //   781: aload_0
    //   782: aload_1
    //   783: aload 6
    //   785: invokespecial 392	com/igexin/push/core/a/f:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   788: aload 6
    //   790: getstatic 1743	com/igexin/push/core/a:l	I
    //   793: invokevirtual 372	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   796: getstatic 264	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   799: aload 9
    //   801: aload 6
    //   803: invokeinterface 407 3 0
    //   808: pop
    //   809: goto -153 -> 656
    //   812: ldc_w 1747
    //   815: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   818: goto -162 -> 656
    //   821: astore_1
    //   822: aconst_null
    //   823: astore_2
    //   824: goto -77 -> 747
    //   827: astore_1
    //   828: aconst_null
    //   829: astore_2
    //   830: goto -146 -> 684
    //   833: iconst_1
    //   834: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	835	0	this	f
    //   0	835	1	paramJSONObject	JSONObject
    //   0	835	2	paramArrayOfByte	byte[]
    //   0	835	3	paramBoolean	boolean
    //   51	682	4	str1	String
    //   60	671	5	str2	String
    //   179	623	6	localPushTaskBean	PushTaskBean
    //   42	330	7	str3	String
    //   78	379	8	localJSONArray	JSONArray
    //   33	767	9	str4	String
    //   69	598	10	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   526	613	683	java/lang/Exception
    //   613	646	683	java/lang/Exception
    //   658	680	683	java/lang/Exception
    //   727	743	683	java/lang/Exception
    //   0	136	697	java/lang/Exception
    //   161	244	697	java/lang/Exception
    //   244	256	697	java/lang/Exception
    //   260	309	697	java/lang/Exception
    //   311	320	697	java/lang/Exception
    //   322	335	697	java/lang/Exception
    //   337	437	697	java/lang/Exception
    //   441	456	697	java/lang/Exception
    //   456	482	697	java/lang/Exception
    //   650	656	697	java/lang/Exception
    //   688	694	697	java/lang/Exception
    //   751	757	697	java/lang/Exception
    //   757	759	697	java/lang/Exception
    //   763	769	697	java/lang/Exception
    //   771	788	697	java/lang/Exception
    //   788	809	697	java/lang/Exception
    //   812	818	697	java/lang/Exception
    //   526	613	746	finally
    //   613	646	746	finally
    //   658	680	746	finally
    //   727	743	746	finally
    //   488	522	821	finally
    //   488	522	827	java/lang/Exception
  }
  
  public Class b(Context paramContext)
  {
    return GTServiceManager.getInstance().getUserIntentService(paramContext);
  }
  
  public void b()
  {
    d();
  }
  
  public void b(int paramInt, String paramString)
  {
    com.igexin.push.config.m.e = paramInt;
    com.igexin.push.config.a.a().d();
  }
  
  public void b(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.hasExtra("isSlave")))
    {
      boolean bool = paramIntent.getBooleanExtra("isSlave", false);
      com.igexin.b.a.c.b.a("CoreAction|onServiceInitializeForSlave isSlave =" + bool);
      if (bool) {
        break label51;
      }
    }
    for (;;)
    {
      return;
      label51:
      com.igexin.push.core.f.a().a(true);
      if (paramIntent.hasExtra("op_app")) {}
      for (com.igexin.push.core.g.D = paramIntent.getStringExtra("op_app"); com.igexin.push.core.g.l; com.igexin.push.core.g.D = "")
      {
        k();
        return;
      }
    }
  }
  
  public void b(PushTaskBean paramPushTaskBean, String paramString)
  {
    if (paramPushTaskBean.isCDNType())
    {
      c(paramPushTaskBean, paramString);
      return;
    }
    a(paramPushTaskBean, paramString, "ok");
  }
  
  public void b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    String str2;
    String str1;
    try
    {
      Uri localUri = Uri.parse(paramString);
      str2 = localUri.getHost();
      str1 = localUri.getQueryParameter("p");
      if ((localUri == null) || (TextUtils.isEmpty(str2)) || (TextUtils.isEmpty(str1)))
      {
        com.igexin.b.a.c.b.a("CoreAction|url " + paramString + " is invalid");
        return;
      }
    }
    catch (Exception paramString)
    {
      com.igexin.b.a.c.b.a("CoreAction|" + paramString.toString());
      return;
    }
    if (!com.igexin.push.config.m.O)
    {
      com.igexin.b.a.c.b.a("CoreAction|isApplinkFeedback is false, not feedback");
      return;
    }
    if (!com.igexin.push.util.a.c(str2))
    {
      com.igexin.b.a.c.b.a("CoreAction|checkIsWhiteApplinkDomain is false, not feedback");
      return;
    }
    com.igexin.b.a.c.b.a("CoreAction|isApplinkFeedback is true and checkIsWhiteApplinkDomain is true, to feedback");
    paramString = new PushTaskBean();
    paramString.setTaskId("getuiapplinkup");
    paramString.setMessageId(str1);
    paramString.setAppid(com.igexin.push.core.g.a);
    paramString.setAppKey(com.igexin.push.core.g.b);
    b(paramString, "20000");
  }
  
  public void b(String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis();
    if (l - com.igexin.push.core.g.S > 5000L)
    {
      String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(l));
      if (!str.equals(com.igexin.push.core.g.R))
      {
        com.igexin.push.core.b.g.a().f(str);
        com.igexin.push.core.b.g.a().a(0);
      }
      com.igexin.b.a.c.b.a("-> CoreRuntimeInfo.opAliasTimes:" + com.igexin.push.core.g.T);
      if (com.igexin.push.core.g.T < 100)
      {
        com.igexin.b.a.c.b.a("start bindAlias ###");
        com.igexin.push.core.g.S = l;
        com.igexin.push.core.b.g.a().a(com.igexin.push.core.g.T + 1);
        a(paramString1, paramString2, false, true);
        return;
      }
      com.igexin.b.a.c.b.a("CoreAction|bindAlias times exceed");
      return;
    }
    com.igexin.b.a.c.b.a("CoreAction|bindAlias frequently called");
  }
  
  public void b(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (com.igexin.push.core.g.f == null) {}
    for (;;)
    {
      return;
      try
      {
        Object localObject = b(com.igexin.push.core.g.f);
        if ((localObject != null) && (com.igexin.push.core.g.a != null))
        {
          localObject = new Intent(com.igexin.push.core.g.f, (Class)localObject);
          Bundle localBundle = new Bundle();
          localBundle.putInt("action", 10011);
          localBundle.putSerializable("notification_arrived", new GTNotificationMessage(paramString1, paramString2, paramString3, paramString4));
          ((Intent)localObject).putExtras(localBundle);
          com.igexin.push.core.g.f.startService((Intent)localObject);
          return;
        }
      }
      catch (Throwable paramString1)
      {
        com.igexin.b.a.c.b.a("CoreAction|" + paramString1.toString());
      }
    }
  }
  
  public void b(boolean paramBoolean)
  {
    com.igexin.b.a.b.c.b().a(new com.igexin.push.d.b.d());
    com.igexin.b.a.b.c.b().c();
    e(paramBoolean);
  }
  
  /* Error */
  public boolean b(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokevirtual 343	com/igexin/push/core/a/f:f	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   6: astore 10
    //   8: getstatic 264	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   11: aload 10
    //   13: invokeinterface 712 2 0
    //   18: checkcast 345	com/igexin/push/core/bean/PushTaskBean
    //   21: astore 6
    //   23: aload 6
    //   25: astore 7
    //   27: aload 6
    //   29: ifnonnull +279 -> 308
    //   32: invokestatic 197	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   35: invokevirtual 276	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   38: ldc_w 278
    //   41: iconst_2
    //   42: anewarray 280	java/lang/String
    //   45: dup
    //   46: iconst_0
    //   47: ldc_w 330
    //   50: aastore
    //   51: dup
    //   52: iconst_1
    //   53: ldc_w 328
    //   56: aastore
    //   57: iconst_2
    //   58: anewarray 280	java/lang/String
    //   61: dup
    //   62: iconst_0
    //   63: aload_1
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: aload_2
    //   68: aastore
    //   69: aconst_null
    //   70: aconst_null
    //   71: invokevirtual 289	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   74: astore 8
    //   76: aload 8
    //   78: ifnull +25 -> 103
    //   81: aload 8
    //   83: astore 7
    //   85: aload 6
    //   87: astore 9
    //   89: aload 8
    //   91: invokeinterface 815 1 0
    //   96: istore 4
    //   98: iload 4
    //   100: ifgt +17 -> 117
    //   103: aload 8
    //   105: ifnull +10 -> 115
    //   108: aload 8
    //   110: invokeinterface 410 1 0
    //   115: iconst_0
    //   116: ireturn
    //   117: aload 8
    //   119: astore 7
    //   121: aload 6
    //   123: astore 9
    //   125: aload 8
    //   127: invokeinterface 294 1 0
    //   132: ifeq +156 -> 288
    //   135: aload 8
    //   137: astore 7
    //   139: aload 6
    //   141: astore 9
    //   143: aload 8
    //   145: aload 8
    //   147: ldc_w 306
    //   150: invokeinterface 1159 2 0
    //   155: invokeinterface 304 2 0
    //   160: astore 11
    //   162: aload 8
    //   164: astore 7
    //   166: aload 6
    //   168: astore 9
    //   170: aload 8
    //   172: aload 8
    //   174: ldc_w 296
    //   177: invokeinterface 1159 2 0
    //   182: invokeinterface 304 2 0
    //   187: astore 12
    //   189: aload 8
    //   191: astore 7
    //   193: aload 6
    //   195: astore 9
    //   197: aload_0
    //   198: new 308	org/json/JSONObject
    //   201: dup
    //   202: new 280	java/lang/String
    //   205: dup
    //   206: aload 11
    //   208: invokestatic 313	com/igexin/b/b/a:c	([B)[B
    //   211: invokespecial 316	java/lang/String:<init>	([B)V
    //   214: invokespecial 318	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   217: aload 12
    //   219: iconst_0
    //   220: invokevirtual 1821	com/igexin/push/core/a/f:a	(Lorg/json/JSONObject;[BZ)Z
    //   223: pop
    //   224: aload 8
    //   226: astore 7
    //   228: aload 6
    //   230: astore 9
    //   232: getstatic 264	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   235: new 169	java/lang/StringBuilder
    //   238: dup
    //   239: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   242: aload_1
    //   243: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: ldc_w 474
    //   249: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: aload_2
    //   253: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   259: invokeinterface 712 2 0
    //   264: checkcast 345	com/igexin/push/core/bean/PushTaskBean
    //   267: astore 6
    //   269: aload 6
    //   271: ifnonnull -154 -> 117
    //   274: aload 8
    //   276: ifnull +10 -> 286
    //   279: aload 8
    //   281: invokeinterface 410 1 0
    //   286: iconst_0
    //   287: ireturn
    //   288: aload 6
    //   290: astore 7
    //   292: aload 8
    //   294: ifnull +14 -> 308
    //   297: aload 8
    //   299: invokeinterface 410 1 0
    //   304: aload 6
    //   306: astore 7
    //   308: aload 7
    //   310: invokevirtual 1824	com/igexin/push/core/bean/PushTaskBean:getExecuteTimes	()I
    //   313: istore 4
    //   315: iload 4
    //   317: bipush 50
    //   319: if_icmplt +121 -> 440
    //   322: getstatic 264	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   325: aload 10
    //   327: invokeinterface 667 2 0
    //   332: pop
    //   333: iconst_1
    //   334: ireturn
    //   335: astore_1
    //   336: aconst_null
    //   337: astore 8
    //   339: aload 8
    //   341: astore 7
    //   343: new 169	java/lang/StringBuilder
    //   346: dup
    //   347: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   350: ldc_w 832
    //   353: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: aload_1
    //   357: invokevirtual 413	java/lang/Throwable:toString	()Ljava/lang/String;
    //   360: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   366: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   369: aload 6
    //   371: astore 7
    //   373: aload 8
    //   375: ifnull -67 -> 308
    //   378: aload 8
    //   380: invokeinterface 410 1 0
    //   385: aload 6
    //   387: astore 7
    //   389: goto -81 -> 308
    //   392: astore_1
    //   393: aconst_null
    //   394: astore 7
    //   396: aload 7
    //   398: ifnull +10 -> 408
    //   401: aload 7
    //   403: invokeinterface 410 1 0
    //   408: aload_1
    //   409: athrow
    //   410: astore_1
    //   411: new 169	java/lang/StringBuilder
    //   414: dup
    //   415: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   418: ldc_w 832
    //   421: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: aload_1
    //   425: invokevirtual 1120	java/lang/Exception:toString	()Ljava/lang/String;
    //   428: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   431: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   434: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   437: goto -104 -> 333
    //   440: aload 7
    //   442: iload 4
    //   444: iconst_1
    //   445: iadd
    //   446: invokevirtual 1827	com/igexin/push/core/bean/PushTaskBean:setExecuteTimes	(I)V
    //   449: invokestatic 340	com/igexin/push/core/a/f:a	()Lcom/igexin/push/core/a/f;
    //   452: aload 7
    //   454: aload_3
    //   455: invokevirtual 1381	com/igexin/push/core/a/f:b	(Lcom/igexin/push/core/bean/PushTaskBean;Ljava/lang/String;)V
    //   458: aload 7
    //   460: aload_3
    //   461: invokevirtual 1831	com/igexin/push/core/bean/PushTaskBean:getBaseAction	(Ljava/lang/String;)Lcom/igexin/push/core/bean/BaseAction;
    //   464: astore_1
    //   465: aload_1
    //   466: ifnonnull +5 -> 471
    //   469: iconst_0
    //   470: ireturn
    //   471: aload_1
    //   472: invokevirtual 1834	com/igexin/push/core/bean/BaseAction:isSupportExt	()Z
    //   475: ifeq +46 -> 521
    //   478: invokestatic 1685	com/igexin/push/extension/a:a	()Lcom/igexin/push/extension/a;
    //   481: invokevirtual 1688	com/igexin/push/extension/a:c	()Ljava/util/List;
    //   484: invokeinterface 1002 1 0
    //   489: astore_2
    //   490: aload_2
    //   491: invokeinterface 989 1 0
    //   496: ifeq +25 -> 521
    //   499: aload_2
    //   500: invokeinterface 993 1 0
    //   505: checkcast 1690	com/igexin/push/extension/stub/IPushExtension
    //   508: aload 7
    //   510: aload_1
    //   511: invokeinterface 1838 3 0
    //   516: ifeq -26 -> 490
    //   519: iconst_1
    //   520: ireturn
    //   521: aload_0
    //   522: aload_1
    //   523: invokevirtual 1841	com/igexin/push/core/bean/BaseAction:getType	()Ljava/lang/String;
    //   526: invokespecial 1701	com/igexin/push/core/a/f:h	(Ljava/lang/String;)Lcom/igexin/push/core/a/a/a;
    //   529: astore_2
    //   530: aload_2
    //   531: ifnull +69 -> 600
    //   534: aload 7
    //   536: invokevirtual 1844	com/igexin/push/core/bean/PushTaskBean:isStop	()Z
    //   539: ifeq +6 -> 545
    //   542: goto +58 -> 600
    //   545: aload_2
    //   546: aload 7
    //   548: aload_1
    //   549: invokeinterface 1846 3 0
    //   554: istore 5
    //   556: iload 5
    //   558: ireturn
    //   559: astore_1
    //   560: new 169	java/lang/StringBuilder
    //   563: dup
    //   564: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   567: ldc_w 832
    //   570: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: aload_1
    //   574: invokevirtual 413	java/lang/Throwable:toString	()Ljava/lang/String;
    //   577: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   580: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   583: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   586: iconst_0
    //   587: ireturn
    //   588: astore_1
    //   589: goto -193 -> 396
    //   592: astore_1
    //   593: aload 9
    //   595: astore 6
    //   597: goto -258 -> 339
    //   600: iconst_0
    //   601: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	602	0	this	f
    //   0	602	1	paramString1	String
    //   0	602	2	paramString2	String
    //   0	602	3	paramString3	String
    //   96	350	4	i	int
    //   554	3	5	bool	boolean
    //   21	575	6	localObject1	Object
    //   25	522	7	localObject2	Object
    //   74	305	8	localCursor	android.database.Cursor
    //   87	507	9	localObject3	Object
    //   6	320	10	str	String
    //   160	47	11	arrayOfByte1	byte[]
    //   187	31	12	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   32	76	335	java/lang/Throwable
    //   32	76	392	finally
    //   322	333	410	java/lang/Exception
    //   458	465	559	java/lang/Throwable
    //   471	490	559	java/lang/Throwable
    //   490	519	559	java/lang/Throwable
    //   521	530	559	java/lang/Throwable
    //   534	542	559	java/lang/Throwable
    //   545	556	559	java/lang/Throwable
    //   89	98	588	finally
    //   125	135	588	finally
    //   143	162	588	finally
    //   170	189	588	finally
    //   197	224	588	finally
    //   232	269	588	finally
    //   343	369	588	finally
    //   89	98	592	java/lang/Throwable
    //   125	135	592	java/lang/Throwable
    //   143	162	592	java/lang/Throwable
    //   170	189	592	java/lang/Throwable
    //   197	224	592	java/lang/Throwable
    //   232	269	592	java/lang/Throwable
  }
  
  /* Error */
  public com.igexin.push.d.c.k c()
  {
    // Byte code:
    //   0: new 1849	com/igexin/push/d/c/k
    //   3: dup
    //   4: invokespecial 1850	com/igexin/push/d/c/k:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: getstatic 1853	com/igexin/push/core/g:q	J
    //   12: putfield 1854	com/igexin/push/d/c/k:a	J
    //   15: aload_2
    //   16: iconst_0
    //   17: putfield 1856	com/igexin/push/d/c/k:b	B
    //   20: aload_2
    //   21: ldc_w 1857
    //   24: putfield 1859	com/igexin/push/d/c/k:c	I
    //   27: aload_2
    //   28: getstatic 251	com/igexin/push/core/g:a	Ljava/lang/String;
    //   31: putfield 1860	com/igexin/push/d/c/k:d	Ljava/lang/String;
    //   34: invokestatic 1861	com/igexin/push/util/a:a	()Z
    //   37: ifeq +208 -> 245
    //   40: new 725	java/util/ArrayList
    //   43: dup
    //   44: invokespecial 726	java/util/ArrayList:<init>	()V
    //   47: astore_3
    //   48: getstatic 491	com/igexin/push/core/g:f	Landroid/content/Context;
    //   51: ldc_w 1863
    //   54: invokevirtual 691	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   57: checkcast 1865	android/net/ConnectivityManager
    //   60: astore 4
    //   62: aload 4
    //   64: ifnull +203 -> 267
    //   67: aload 4
    //   69: invokevirtual 1869	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   72: astore 4
    //   74: aload 4
    //   76: ifnull +191 -> 267
    //   79: aload 4
    //   81: invokevirtual 1873	android/net/NetworkInfo:getType	()I
    //   84: istore_1
    //   85: new 1875	com/igexin/push/d/c/l
    //   88: dup
    //   89: invokespecial 1876	com/igexin/push/d/c/l:<init>	()V
    //   92: astore 4
    //   94: aload 4
    //   96: iconst_2
    //   97: putfield 1878	com/igexin/push/d/c/l:a	B
    //   100: aload 4
    //   102: iload_1
    //   103: invokestatic 558	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   106: putfield 1880	com/igexin/push/d/c/l:b	Ljava/lang/Object;
    //   109: aload_3
    //   110: aload 4
    //   112: invokeinterface 565 2 0
    //   117: pop
    //   118: iload_1
    //   119: iconst_1
    //   120: if_icmpne +111 -> 231
    //   123: getstatic 491	com/igexin/push/core/g:f	Landroid/content/Context;
    //   126: ldc_w 603
    //   129: invokevirtual 691	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   132: checkcast 1882	android/net/wifi/WifiManager
    //   135: invokevirtual 1886	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   138: astore 5
    //   140: aload 5
    //   142: ifnull +89 -> 231
    //   145: aload 5
    //   147: invokevirtual 1891	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   150: astore 4
    //   152: aload 5
    //   154: invokevirtual 1894	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   157: astore 5
    //   159: aload 4
    //   161: ifnull +34 -> 195
    //   164: new 1875	com/igexin/push/d/c/l
    //   167: dup
    //   168: invokespecial 1876	com/igexin/push/d/c/l:<init>	()V
    //   171: astore 6
    //   173: aload 6
    //   175: iconst_1
    //   176: putfield 1878	com/igexin/push/d/c/l:a	B
    //   179: aload 6
    //   181: aload 4
    //   183: putfield 1880	com/igexin/push/d/c/l:b	Ljava/lang/Object;
    //   186: aload_3
    //   187: aload 6
    //   189: invokeinterface 565 2 0
    //   194: pop
    //   195: aload 5
    //   197: ifnull +34 -> 231
    //   200: new 1875	com/igexin/push/d/c/l
    //   203: dup
    //   204: invokespecial 1876	com/igexin/push/d/c/l:<init>	()V
    //   207: astore 4
    //   209: aload 4
    //   211: iconst_4
    //   212: putfield 1878	com/igexin/push/d/c/l:a	B
    //   215: aload 4
    //   217: aload 5
    //   219: putfield 1880	com/igexin/push/d/c/l:b	Ljava/lang/Object;
    //   222: aload_3
    //   223: aload 4
    //   225: invokeinterface 565 2 0
    //   230: pop
    //   231: aload_3
    //   232: invokeinterface 976 1 0
    //   237: ifne +8 -> 245
    //   240: aload_2
    //   241: aload_3
    //   242: putfield 1897	com/igexin/push/d/c/k:e	Ljava/util/List;
    //   245: aload_2
    //   246: areturn
    //   247: astore_3
    //   248: aload_2
    //   249: areturn
    //   250: astore 4
    //   252: goto -21 -> 231
    //   255: astore 4
    //   257: iconst_m1
    //   258: istore_1
    //   259: goto -141 -> 118
    //   262: astore 4
    //   264: goto -146 -> 118
    //   267: iconst_m1
    //   268: istore_1
    //   269: goto -151 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	272	0	this	f
    //   84	185	1	i	int
    //   7	242	2	localK	com.igexin.push.d.c.k
    //   47	195	3	localArrayList	ArrayList
    //   247	1	3	localThrowable1	Throwable
    //   60	164	4	localObject1	Object
    //   250	1	4	localThrowable2	Throwable
    //   255	1	4	localThrowable3	Throwable
    //   262	1	4	localThrowable4	Throwable
    //   138	80	5	localObject2	Object
    //   171	17	6	localL	com.igexin.push.d.c.l
    // Exception table:
    //   from	to	target	type
    //   34	48	247	java/lang/Throwable
    //   231	245	247	java/lang/Throwable
    //   123	140	250	java/lang/Throwable
    //   145	159	250	java/lang/Throwable
    //   164	195	250	java/lang/Throwable
    //   200	231	250	java/lang/Throwable
    //   48	62	255	java/lang/Throwable
    //   67	74	255	java/lang/Throwable
    //   79	85	255	java/lang/Throwable
    //   85	118	262	java/lang/Throwable
  }
  
  public void c(Intent paramIntent)
  {
    if ((paramIntent == null) || (paramIntent.getAction() == null)) {}
    String str;
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            return;
            try
            {
              str = paramIntent.getAction();
              if ("android.net.conn.CONNECTIVITY_CHANGE".equals(str))
              {
                if (com.igexin.b.a.b.c.b() == null) {
                  continue;
                }
                F();
              }
            }
            catch (Throwable paramIntent)
            {
              com.igexin.b.a.c.b.a("CoreAction" + paramIntent.toString());
              return;
            }
          }
          if (("com.igexin.sdk.action.snlrefresh".equals(str)) || (com.igexin.push.core.g.V.equals(str)) || ("com.igexin.sdk.action.snlretire".equals(str)))
          {
            com.igexin.push.core.f.a().h().a(paramIntent);
            return;
          }
          if ("com.igexin.sdk.action.execute".equals(str))
          {
            g(paramIntent);
            return;
          }
          if ("com.igexin.sdk.action.doaction".equals(str))
          {
            f(paramIntent);
            return;
          }
          if (!"android.intent.action.TIME_SET".equals(str)) {
            break;
          }
        } while (com.igexin.push.config.m.b == 0);
        com.igexin.push.a.a.c.c().d();
        return;
        if (!"android.intent.action.SCREEN_ON".equals(str)) {
          break;
        }
        com.igexin.push.core.g.p = 1;
      } while (!u());
      t();
      return;
      if ("android.intent.action.SCREEN_OFF".equals(str))
      {
        com.igexin.push.core.g.p = 0;
        return;
      }
      if ("android.intent.action.PACKAGE_ADDED".equals(str))
      {
        f(paramIntent.getDataString());
        return;
      }
      if ("android.intent.action.PACKAGE_REMOVED".equals(str))
      {
        e(paramIntent.getDataString());
        return;
      }
      if ("com.igexin.sdk.action.core.clearmsg".equals(str))
      {
        com.igexin.push.core.f.a().k().a("message", null);
        return;
      }
    } while ((!"android.net.wifi.WIFI_STATE_CHANGED".equals(str)) || (paramIntent.getIntExtra("wifi_state", 0) != 3));
    com.igexin.push.core.f.a().d();
  }
  
  public void c(PushTaskBean paramPushTaskBean, String paramString)
  {
    if ((paramPushTaskBean == null) || (TextUtils.isEmpty(paramPushTaskBean.getMessageId()))) {
      return;
    }
    String str = paramPushTaskBean.getMessageId() + "|" + paramString;
    com.igexin.push.d.c.c localC;
    if (com.igexin.push.core.g.am.containsKey(str))
    {
      localC = (com.igexin.push.d.c.c)com.igexin.push.core.g.am.get(str);
      if (localC.c() < 2)
      {
        com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.r, localC);
        localC.a(localC.c() + 1);
        a(localC, paramPushTaskBean, paramString, str);
      }
    }
    for (;;)
    {
      com.igexin.b.a.c.b.a("cdnfeedback|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId() + "|" + paramString);
      return;
      localC = new com.igexin.push.d.c.c();
      long l = System.currentTimeMillis();
      localC.a();
      localC.c = ("FDB" + paramPushTaskBean.getMessageId() + "|" + paramPushTaskBean.getTaskId() + "|" + paramString + "|" + "ok" + "|" + l);
      localC.d = com.igexin.push.core.g.r;
      localC.a = ((int)l);
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.r, localC);
      a(localC, paramPushTaskBean, paramString, str);
    }
  }
  
  public void c(String paramString)
  {
    String str = a(true, 4);
    str = str + "2.12.5.0|sdkconfig-error|";
    paramString = str + paramString;
    paramString = new com.igexin.push.f.a.c(new com.igexin.push.core.c.i(SDKUrlConfig.getBiUploadServiceUrl(), paramString.getBytes(), 0, true));
    com.igexin.b.a.b.c.b().a(paramString, false, true);
  }
  
  public void c(String paramString1, String paramString2)
  {
    if (com.igexin.push.core.g.f == null) {
      return;
    }
    try
    {
      localObject = b(com.igexin.push.core.g.f);
      if (localObject != null)
      {
        localObject = new Intent(com.igexin.push.core.g.f, (Class)localObject);
        localBundle = new Bundle();
        localBundle.putInt("action", 10010);
        localBundle.putSerializable("cmd_msg", new SetTagCmdMessage(paramString1, paramString2, 10009));
        ((Intent)localObject).putExtras(localBundle);
        com.igexin.push.core.g.f.startService((Intent)localObject);
        return;
      }
    }
    catch (Throwable paramString1)
    {
      com.igexin.b.a.c.b.a("CoreAction|" + paramString1.toString());
      return;
    }
    Object localObject = G();
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10009);
    localBundle.putString("sn", paramString1);
    localBundle.putString("code", paramString2);
    ((Intent)localObject).putExtras(localBundle);
    com.igexin.push.core.g.f.sendBroadcast((Intent)localObject);
  }
  
  public void c(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (com.igexin.push.core.g.f == null) {}
    for (;;)
    {
      return;
      try
      {
        Object localObject = b(com.igexin.push.core.g.f);
        if ((localObject != null) && (com.igexin.push.core.g.a != null))
        {
          localObject = new Intent(com.igexin.push.core.g.f, (Class)localObject);
          Bundle localBundle = new Bundle();
          localBundle.putInt("action", 10012);
          localBundle.putSerializable("notification_clicked", new GTNotificationMessage(paramString1, paramString2, paramString3, paramString4));
          ((Intent)localObject).putExtras(localBundle);
          com.igexin.push.core.g.f.startService((Intent)localObject);
          return;
        }
      }
      catch (Throwable paramString1)
      {
        com.igexin.b.a.c.b.a("CoreAction|" + paramString1.toString());
      }
    }
  }
  
  public void c(boolean paramBoolean)
  {
    f();
    d(paramBoolean);
  }
  
  public int d()
  {
    int j = 1;
    if ((!com.igexin.push.core.g.i) || (!com.igexin.push.core.g.j) || (com.igexin.push.util.a.a(System.currentTimeMillis())) || (!com.igexin.push.util.a.b()))
    {
      com.igexin.b.a.c.b.a("CoreAction|keyNegotiate stop ++++++++++");
      j = -1;
    }
    for (;;)
    {
      return j;
      com.igexin.push.d.c.i localI = new com.igexin.push.d.c.i();
      localI.a = com.igexin.push.core.g.a;
      if (com.igexin.push.core.f.a().g().a("K-", localI, true) < 0) {}
      for (int i = 0; i == 0; i = 1) {
        return 0;
      }
    }
  }
  
  public String d(String paramString)
  {
    if (com.igexin.push.core.g.b() == null) {
      return null;
    }
    return (String)com.igexin.push.core.g.b().get(paramString);
  }
  
  public void d(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (;;)
    {
      return;
      try
      {
        int j = paramIntent.getIntExtra("id", -1);
        boolean bool = paramIntent.getBooleanExtra("result", false);
        if (j == -1) {
          continue;
        }
        com.igexin.push.core.g.as += 1;
        if (bool)
        {
          if (paramIntent.getBooleanExtra("isReload", false))
          {
            Process.killProcess(Process.myPid());
            return;
          }
          com.igexin.push.core.g.ar += 1;
          if (com.igexin.push.core.g.at == null) {
            break label443;
          }
        }
        label438:
        label443:
        for (Object localObject1 = com.igexin.push.core.g.at.b(); localObject1 != null; localObject1 = null)
        {
          int i;
          Object localObject2;
          if (com.igexin.push.config.m.s != null)
          {
            paramIntent = com.igexin.push.config.m.s.b();
            if (paramIntent == null) {
              break;
            }
            if (!paramIntent.containsKey(Integer.valueOf(j))) {
              break label438;
            }
            i = 1;
            localObject2 = (com.igexin.push.core.bean.f)paramIntent.get(Integer.valueOf(j));
            if (localObject2 != null) {
              com.igexin.push.util.f.b(((com.igexin.push.core.bean.f)localObject2).c());
            }
            paramIntent.remove(Integer.valueOf(j));
          }
          for (;;)
          {
            localObject1 = (com.igexin.push.core.bean.f)((Map)localObject1).get(Integer.valueOf(j));
            if (localObject1 == null) {
              break;
            }
            localObject2 = com.igexin.push.core.g.ac + "/" + ((com.igexin.push.core.bean.f)localObject1).c();
            if (new File((String)localObject2).exists())
            {
              paramIntent.put(Integer.valueOf(j), localObject1);
              if (com.igexin.push.core.g.ar == com.igexin.push.core.g.aq) {
                com.igexin.push.config.m.s.a(com.igexin.push.core.g.at.a());
              }
              if ((i == 0) && (com.igexin.push.extension.a.a().a(com.igexin.push.core.g.f, (String)localObject2, ((com.igexin.push.core.bean.f)localObject1).d(), ((com.igexin.push.core.bean.f)localObject1).j(), ((com.igexin.push.core.bean.f)localObject1).c())))
              {
                com.igexin.b.a.c.b.a("CoreAction|load " + ((com.igexin.push.core.bean.f)localObject1).d() + " success");
                ((com.igexin.push.core.bean.f)localObject1).b(System.currentTimeMillis());
                if (((com.igexin.push.core.bean.f)localObject1).g())
                {
                  com.igexin.push.util.f.b(((com.igexin.push.core.bean.f)localObject1).c());
                  paramIntent.remove(Integer.valueOf(j));
                }
              }
              com.igexin.push.config.a.a().g();
            }
            if ((com.igexin.push.core.g.as != com.igexin.push.core.g.aq) || (!com.igexin.push.core.g.au)) {
              break;
            }
            com.igexin.b.a.c.b.a("CoreActiondownload ext success, restart service ###");
            Process.killProcess(Process.myPid());
            return;
            paramIntent = new HashMap();
            localObject2 = new com.igexin.push.core.bean.g();
            ((com.igexin.push.core.bean.g)localObject2).a("0");
            ((com.igexin.push.core.bean.g)localObject2).a(paramIntent);
            com.igexin.push.config.m.s = (com.igexin.push.core.bean.g)localObject2;
            i = 0;
            continue;
            i = 0;
          }
        }
        return;
      }
      catch (Throwable paramIntent) {}
    }
  }
  
  public void d(String paramString1, String paramString2)
  {
    if (com.igexin.push.core.g.f == null) {
      return;
    }
    try
    {
      localObject = b(com.igexin.push.core.g.f);
      if (localObject != null)
      {
        localObject = new Intent(com.igexin.push.core.g.f, (Class)localObject);
        localBundle = new Bundle();
        localBundle.putInt("action", 10010);
        localBundle.putSerializable("cmd_msg", new BindAliasCmdMessage(paramString1, paramString2, 10010));
        ((Intent)localObject).putExtras(localBundle);
        com.igexin.push.core.g.f.startService((Intent)localObject);
        return;
      }
    }
    catch (Throwable paramString1)
    {
      com.igexin.b.a.c.b.a("CoreAction|" + paramString1.toString());
      return;
    }
    Object localObject = G();
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10010);
    localBundle.putString("sn", paramString1);
    localBundle.putString("code", paramString2);
    ((Intent)localObject).putExtras(localBundle);
    com.igexin.push.core.g.f.sendBroadcast((Intent)localObject);
  }
  
  public void d(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    com.igexin.b.a.c.b.a("CoreAction start broadcastExecute");
    if (com.igexin.push.core.g.f == null) {
      return;
    }
    paramString3 = new Intent("com.igexin.sdk.action.execute");
    paramString3.putExtra("taskid", paramString1);
    paramString3.putExtra("messageid", paramString2);
    paramString3.putExtra("appid", com.igexin.push.core.g.a);
    paramString3.putExtra("pkgname", com.igexin.push.core.g.e);
    com.igexin.push.core.g.f.sendBroadcast(paramString3);
  }
  
  public void d(boolean paramBoolean)
  {
    com.igexin.b.a.b.c.b().a(new com.igexin.push.d.b.b(paramBoolean));
    com.igexin.b.a.b.c.b().c();
  }
  
  public void e()
  {
    int i = 0;
    boolean bool2 = false;
    Object localObject;
    if (com.igexin.push.core.g.k)
    {
      if (!com.igexin.push.core.g.k)
      {
        bool1 = true;
        com.igexin.push.core.g.k = bool1;
        com.igexin.push.core.g.L = Math.abs(new Random().nextInt() % 24) * 3600000L + System.currentTimeMillis();
      }
    }
    else
    {
      com.igexin.push.c.i.a().e().g();
      if (com.igexin.push.core.g.q != 0L) {
        break label174;
      }
      com.igexin.b.a.c.b.a("registerReq #####");
      localObject = new com.igexin.push.d.c.f(com.igexin.push.core.g.t, com.igexin.push.core.g.u, com.igexin.push.core.g.B, com.igexin.push.core.g.a);
      if (com.igexin.push.core.f.a().g().a("R-" + com.igexin.push.core.g.B, (com.igexin.push.d.c.e)localObject, true) >= 0) {
        break label282;
      }
    }
    label174:
    label282:
    for (boolean bool1 = bool2;; bool1 = true)
    {
      com.igexin.b.a.c.b.a("registerReq|" + bool1 + "|" + com.igexin.push.core.g.B);
      for (;;)
      {
        return;
        bool1 = false;
        break;
        localObject = c();
        com.igexin.b.a.c.b.a("loginReqBefore|" + ((com.igexin.push.d.c.k)localObject).a);
        if (com.igexin.push.core.f.a().g().a("S-" + String.valueOf(com.igexin.push.core.g.q), (com.igexin.push.d.c.e)localObject, true) < 0) {}
        while (i != 0)
        {
          com.igexin.b.a.c.b.a("CoreAction|loginReq|" + com.igexin.push.core.g.r);
          return;
          i = 1;
        }
      }
    }
  }
  
  public void e(Intent paramIntent)
  {
    try
    {
      String str = paramIntent.getStringExtra("from");
      paramIntent = paramIntent.getStringExtra("did");
      if ((TextUtils.isEmpty(str)) || (TextUtils.isEmpty(paramIntent)))
      {
        com.igexin.b.a.c.b.a("CoreAction|doThirdGuardSt from or did is empty");
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str).append("|").append(com.igexin.push.core.g.f.getPackageName()).append("|").append(paramIntent).append("|").append(com.igexin.push.core.g.y).append("|").append(com.igexin.push.core.g.a).append("|").append(com.igexin.push.core.g.r).append("|").append(System.currentTimeMillis());
      af.a().a("21", localStringBuilder.toString());
      return;
    }
    catch (Throwable paramIntent)
    {
      com.igexin.b.a.c.b.a("CoreAction|doThirdGuardSt exception: " + paramIntent.toString());
    }
  }
  
  public void e(String paramString1, String paramString2)
  {
    if (com.igexin.push.core.g.f == null) {
      return;
    }
    try
    {
      localObject = b(com.igexin.push.core.g.f);
      if (localObject != null)
      {
        localObject = new Intent(com.igexin.push.core.g.f, (Class)localObject);
        localBundle = new Bundle();
        localBundle.putInt("action", 10010);
        localBundle.putSerializable("cmd_msg", new UnBindAliasCmdMessage(paramString1, paramString2, 10011));
        ((Intent)localObject).putExtras(localBundle);
        com.igexin.push.core.g.f.startService((Intent)localObject);
        return;
      }
    }
    catch (Throwable paramString1)
    {
      com.igexin.b.a.c.b.a("CoreAction|" + paramString1.toString());
      return;
    }
    Object localObject = G();
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10011);
    localBundle.putString("sn", paramString1);
    localBundle.putString("code", paramString2);
    ((Intent)localObject).putExtras(localBundle);
    com.igexin.push.core.g.f.sendBroadcast((Intent)localObject);
  }
  
  public void e(boolean paramBoolean)
  {
    com.igexin.b.a.b.c.b().a(new com.igexin.push.d.b.c(paramBoolean));
    com.igexin.b.a.b.c.b().c();
  }
  
  public String f(String paramString1, String paramString2)
  {
    return paramString1 + ":" + paramString2;
  }
  
  public void f()
  {
    com.igexin.b.a.b.a.a.d.a().b();
  }
  
  public int g()
  {
    com.igexin.b.a.c.b.a("CoreAction|send heart beat data ........");
    return com.igexin.push.core.f.a().g().a("H-" + com.igexin.push.core.g.r, new com.igexin.push.d.c.h(), true);
  }
  
  public com.igexin.push.core.b g(String paramString1, String paramString2)
  {
    Object localObject1 = com.igexin.push.core.b.a;
    paramString2 = f(paramString1, paramString2);
    PushTaskBean localPushTaskBean = (PushTaskBean)com.igexin.push.core.g.ai.get(paramString2);
    if (localPushTaskBean == null) {
      localObject1 = com.igexin.push.core.b.c;
    }
    int i;
    Object localObject2;
    do
    {
      BaseAction localBaseAction;
      do
      {
        do
        {
          return localObject1;
          Iterator localIterator1 = localPushTaskBean.getActionChains().iterator();
          i = 0;
          paramString2 = (String)localObject1;
          if (!localIterator1.hasNext()) {
            break;
          }
          localBaseAction = (BaseAction)localIterator1.next();
          localObject2 = com.igexin.push.core.b.c;
          localObject1 = localObject2;
        } while (localBaseAction == null);
        Iterator localIterator2 = com.igexin.push.extension.a.a().c().iterator();
        localObject1 = localObject2;
        while (localIterator2.hasNext())
        {
          localObject2 = ((IPushExtension)localIterator2.next()).prepareExecuteAction(localPushTaskBean, localBaseAction);
          localObject1 = localObject2;
          if (localObject2 != com.igexin.push.core.b.c) {
            localObject1 = localObject2;
          }
        }
        localObject2 = localObject1;
        if (localObject1 != com.igexin.push.core.b.c) {
          break;
        }
        localObject2 = h(localBaseAction.getType());
      } while (localObject2 == null);
      localObject2 = ((com.igexin.push.core.a.a.a)localObject2).a(localPushTaskBean, localBaseAction);
      localObject1 = localObject2;
    } while (localObject2 == com.igexin.push.core.b.c);
    if (paramString2 == com.igexin.push.core.b.a) {
      paramString2 = (String)localObject2;
    }
    for (;;)
    {
      if (localObject2 == com.igexin.push.core.b.b) {
        i += 1;
      }
      for (;;)
      {
        break;
        localObject1 = paramString2;
        if (i != 0)
        {
          localObject1 = paramString2;
          if (!com.igexin.push.core.g.a(paramString1, Integer.valueOf(i), true)) {
            localObject1 = com.igexin.push.core.b.a;
          }
        }
        return localObject1;
      }
    }
  }
  
  public void h()
  {
    try
    {
      Object localObject = com.igexin.push.core.b.d.a().b().iterator();
      while (((Iterator)localObject).hasNext())
      {
        com.igexin.push.core.bean.j localJ = (com.igexin.push.core.bean.j)((Iterator)localObject).next();
        if (localJ.d() + 20000L <= System.currentTimeMillis())
        {
          long l = System.currentTimeMillis();
          localObject = new JSONObject(localJ.b());
          com.igexin.push.d.c.d localD = new com.igexin.push.d.c.d();
          localD.a();
          localD.a = ((int)l);
          localD.d = "17258000";
          if (((JSONObject)localObject).has("extraData"))
          {
            localD.f = com.igexin.push.util.i.a(((JSONObject)localObject).optString("extraData").getBytes(), 0);
            ((JSONObject)localObject).remove("extraData");
          }
          localD.e = localJ.b();
          localD.g = com.igexin.push.core.g.r;
          com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.r, localD);
          com.igexin.b.a.c.b.a("freshral|" + localJ.b());
          com.igexin.push.core.b.d.a().a(localJ.a());
          localJ.a(System.currentTimeMillis() + 20000L);
          com.igexin.push.core.b.d.a().a(localJ);
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      com.igexin.b.a.c.b.a("CoreActionfreshRAL error :" + localThrowable.toString());
    }
  }
  
  public void i()
  {
    long l = System.currentTimeMillis();
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("action", "request_deviceid");
      ((JSONObject)localObject).put("id", String.valueOf(l));
      localObject = ((JSONObject)localObject).toString();
      com.igexin.push.d.c.d localD = new com.igexin.push.d.c.d();
      localD.a();
      localD.a = ((int)l);
      localD.d = "17258000";
      localD.e = localObject;
      localD.g = com.igexin.push.core.g.r;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.r, localD);
      com.igexin.b.a.c.b.a("CoreAction|deviceidReq");
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public void j()
  {
    int i = 1;
    try
    {
      if (System.currentTimeMillis() - com.igexin.push.core.g.G - 86400000L > 0L) {}
      for (;;)
      {
        if (i != 0)
        {
          com.igexin.push.core.b.g.a().b(0);
          com.igexin.push.core.b.g.a().h(System.currentTimeMillis());
        }
        if (com.igexin.push.core.g.aD <= 5)
        {
          com.igexin.push.core.b.g.a().b(com.igexin.push.core.g.aD + 1);
          com.igexin.b.a.b.c.b().a(new g(this), false, true);
        }
        return;
        i = 0;
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public void k()
  {
    if (com.igexin.push.core.g.f == null) {
      return;
    }
    Log.d("PushService", "clientid is " + com.igexin.push.core.g.r);
    com.igexin.b.a.c.b.a("broadcastClientid|" + com.igexin.push.core.g.r);
    try
    {
      localObject = b(com.igexin.push.core.g.f);
      if (localObject != null)
      {
        localObject = new Intent(com.igexin.push.core.g.f, (Class)localObject);
        localBundle = new Bundle();
        localBundle.putInt("action", 10002);
        localBundle.putString("clientid", com.igexin.push.core.g.r);
        ((Intent)localObject).putExtras(localBundle);
        com.igexin.push.core.g.f.startService((Intent)localObject);
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Object localObject;
        Bundle localBundle;
        com.igexin.b.a.c.b.a("CoreAction|" + localThrowable.toString());
      }
    }
    localObject = G();
    localBundle = new Bundle();
    localBundle.putInt("action", 10002);
    localBundle.putString("clientid", com.igexin.push.core.g.r);
    ((Intent)localObject).putExtras(localBundle);
    com.igexin.push.core.g.f.sendBroadcast((Intent)localObject);
  }
  
  public void l()
  {
    if (com.igexin.push.core.g.f == null) {
      return;
    }
    try
    {
      localObject = b(com.igexin.push.core.g.f);
      if (localObject != null)
      {
        localObject = new Intent(com.igexin.push.core.g.f, (Class)localObject);
        localBundle = new Bundle();
        localBundle.putInt("action", 10007);
        localBundle.putBoolean("onlineState", com.igexin.push.core.g.l);
        ((Intent)localObject).putExtras(localBundle);
        com.igexin.push.core.g.f.startService((Intent)localObject);
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Object localObject;
        Bundle localBundle;
        com.igexin.b.a.c.b.a("CoreAction|" + localThrowable.toString());
      }
    }
    localObject = G();
    localBundle = new Bundle();
    localBundle.putInt("action", 10007);
    localBundle.putBoolean("onlineState", com.igexin.push.core.g.l);
    ((Intent)localObject).putExtras(localBundle);
    com.igexin.push.core.g.f.sendBroadcast((Intent)localObject);
  }
  
  /* Error */
  public String m()
  {
    // Byte code:
    //   0: new 834	java/io/File
    //   3: dup
    //   4: getstatic 836	com/igexin/push/core/g:Z	Ljava/lang/String;
    //   7: invokespecial 837	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: invokevirtual 840	java/io/File:exists	()Z
    //   13: ifeq +191 -> 204
    //   16: sipush 1024
    //   19: newarray byte
    //   21: astore_3
    //   22: new 2204	java/io/FileInputStream
    //   25: dup
    //   26: getstatic 836	com/igexin/push/core/g:Z	Ljava/lang/String;
    //   29: invokespecial 2205	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   32: astore_2
    //   33: new 2207	java/io/ByteArrayOutputStream
    //   36: dup
    //   37: invokespecial 2208	java/io/ByteArrayOutputStream:<init>	()V
    //   40: astore 4
    //   42: aload_2
    //   43: aload_3
    //   44: invokevirtual 2212	java/io/FileInputStream:read	([B)I
    //   47: istore_1
    //   48: iload_1
    //   49: iconst_m1
    //   50: if_icmpeq +40 -> 90
    //   53: aload 4
    //   55: aload_3
    //   56: iconst_0
    //   57: iload_1
    //   58: invokevirtual 2215	java/io/ByteArrayOutputStream:write	([BII)V
    //   61: goto -19 -> 42
    //   64: astore_3
    //   65: aload_2
    //   66: astore_3
    //   67: aload 4
    //   69: astore_2
    //   70: aload_3
    //   71: ifnull +7 -> 78
    //   74: aload_3
    //   75: invokevirtual 2216	java/io/FileInputStream:close	()V
    //   78: aload_2
    //   79: ifnull +125 -> 204
    //   82: aload_2
    //   83: invokevirtual 2217	java/io/ByteArrayOutputStream:close	()V
    //   86: aconst_null
    //   87: astore_2
    //   88: aload_2
    //   89: areturn
    //   90: new 280	java/lang/String
    //   93: dup
    //   94: aload 4
    //   96: invokevirtual 2220	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   99: invokespecial 316	java/lang/String:<init>	([B)V
    //   102: astore_3
    //   103: aload_2
    //   104: ifnull +7 -> 111
    //   107: aload_2
    //   108: invokevirtual 2216	java/io/FileInputStream:close	()V
    //   111: aload_3
    //   112: astore_2
    //   113: aload 4
    //   115: ifnull -27 -> 88
    //   118: aload 4
    //   120: invokevirtual 2217	java/io/ByteArrayOutputStream:close	()V
    //   123: aload_3
    //   124: areturn
    //   125: astore_2
    //   126: aload_3
    //   127: areturn
    //   128: astore_2
    //   129: aconst_null
    //   130: areturn
    //   131: astore_3
    //   132: aconst_null
    //   133: astore 4
    //   135: aconst_null
    //   136: astore_2
    //   137: aload_2
    //   138: ifnull +7 -> 145
    //   141: aload_2
    //   142: invokevirtual 2216	java/io/FileInputStream:close	()V
    //   145: aload 4
    //   147: ifnull +8 -> 155
    //   150: aload 4
    //   152: invokevirtual 2217	java/io/ByteArrayOutputStream:close	()V
    //   155: aload_3
    //   156: athrow
    //   157: astore_2
    //   158: goto -47 -> 111
    //   161: astore_3
    //   162: goto -84 -> 78
    //   165: astore_2
    //   166: goto -21 -> 145
    //   169: astore_2
    //   170: goto -15 -> 155
    //   173: astore_3
    //   174: aconst_null
    //   175: astore 4
    //   177: goto -40 -> 137
    //   180: astore_3
    //   181: goto -44 -> 137
    //   184: astore_2
    //   185: aconst_null
    //   186: astore_2
    //   187: aconst_null
    //   188: astore_3
    //   189: goto -119 -> 70
    //   192: astore_3
    //   193: aconst_null
    //   194: astore 4
    //   196: aload_2
    //   197: astore_3
    //   198: aload 4
    //   200: astore_2
    //   201: goto -131 -> 70
    //   204: aconst_null
    //   205: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	this	f
    //   47	11	1	i	int
    //   32	81	2	localObject1	Object
    //   125	1	2	localException1	Exception
    //   128	1	2	localException2	Exception
    //   136	6	2	localObject2	Object
    //   157	1	2	localException3	Exception
    //   165	1	2	localException4	Exception
    //   169	1	2	localException5	Exception
    //   184	1	2	localException6	Exception
    //   186	15	2	localObject3	Object
    //   21	35	3	arrayOfByte	byte[]
    //   64	1	3	localException7	Exception
    //   66	61	3	localObject4	Object
    //   131	25	3	localObject5	Object
    //   161	1	3	localException8	Exception
    //   173	1	3	localObject6	Object
    //   180	1	3	localObject7	Object
    //   188	1	3	localObject8	Object
    //   192	1	3	localException9	Exception
    //   197	1	3	localObject9	Object
    //   40	159	4	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   42	48	64	java/lang/Exception
    //   53	61	64	java/lang/Exception
    //   90	103	64	java/lang/Exception
    //   118	123	125	java/lang/Exception
    //   82	86	128	java/lang/Exception
    //   22	33	131	finally
    //   107	111	157	java/lang/Exception
    //   74	78	161	java/lang/Exception
    //   141	145	165	java/lang/Exception
    //   150	155	169	java/lang/Exception
    //   33	42	173	finally
    //   42	48	180	finally
    //   53	61	180	finally
    //   90	103	180	finally
    //   22	33	184	java/lang/Exception
    //   33	42	192	java/lang/Exception
  }
  
  public void n()
  {
    ArrayList localArrayList = new ArrayList();
    a(localArrayList);
    if (localArrayList.isEmpty()) {
      return;
    }
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("action", "reportapplist");
      ((JSONObject)localObject).put("session_last", com.igexin.push.core.g.q);
      JSONArray localJSONArray = new JSONArray();
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("appid", ((p)localArrayList.get(i)).d());
        localJSONObject.put("name", ((p)localArrayList.get(i)).b());
        localJSONObject.put("version", ((p)localArrayList.get(i)).c());
        localJSONObject.put("versionName", ((p)localArrayList.get(i)).a());
        localJSONArray.put(localJSONObject);
        i += 1;
      }
      ((JSONObject)localObject).put("applist", localJSONArray);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    localObject = new com.igexin.push.f.a.c(new com.igexin.push.core.c.a(SDKUrlConfig.getBiUploadServiceUrl(), ((JSONObject)localObject).toString().getBytes()));
    com.igexin.b.a.b.c.b().a((com.igexin.b.a.d.e)localObject, false, true);
    g(o());
    com.igexin.b.a.c.b.a("reportapplist");
  }
  
  public String o()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    a(localArrayList2);
    int j = localArrayList2.size();
    if (j > 0)
    {
      int i = 0;
      while (i < j)
      {
        localArrayList1.add(((p)localArrayList2.get(i)).d());
        i += 1;
      }
    }
    return localArrayList1.toString();
  }
  
  public boolean p()
  {
    return false;
  }
  
  public void q()
  {
    long l = System.currentTimeMillis();
    com.igexin.push.core.f.a().k().a("message", "createtime <= " + (l - 604800000L));
  }
  
  /* Error */
  public void r()
  {
    // Byte code:
    //   0: invokestatic 197	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   3: invokevirtual 276	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   6: astore 4
    //   8: aload 4
    //   10: ldc_w 278
    //   13: iconst_1
    //   14: anewarray 280	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: ldc_w 282
    //   22: aastore
    //   23: iconst_1
    //   24: anewarray 280	java/lang/String
    //   27: dup
    //   28: iconst_0
    //   29: ldc_w 284
    //   32: aastore
    //   33: aconst_null
    //   34: aconst_null
    //   35: invokevirtual 289	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   38: astore_3
    //   39: aload_3
    //   40: ifnull +234 -> 274
    //   43: aload_3
    //   44: invokeinterface 294 1 0
    //   49: ifeq +225 -> 274
    //   52: aload_3
    //   53: aload_3
    //   54: ldc_w 306
    //   57: invokeinterface 300 2 0
    //   62: invokeinterface 304 2 0
    //   67: astore 5
    //   69: aload_3
    //   70: aload_3
    //   71: ldc_w 799
    //   74: invokeinterface 300 2 0
    //   79: invokeinterface 2255 2 0
    //   84: lstore_1
    //   85: new 308	org/json/JSONObject
    //   88: dup
    //   89: new 280	java/lang/String
    //   92: dup
    //   93: aload 5
    //   95: invokestatic 313	com/igexin/b/b/a:c	([B)[B
    //   98: invokespecial 316	java/lang/String:<init>	([B)V
    //   101: invokespecial 318	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   104: astore 5
    //   106: aload 5
    //   108: ldc_w 330
    //   111: invokevirtual 324	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   114: astore 6
    //   116: aload 5
    //   118: ldc_w 389
    //   121: invokevirtual 381	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   124: ifeq -81 -> 43
    //   127: aload_0
    //   128: aload 5
    //   130: invokespecial 2257	com/igexin/push/core/a/f:b	(Lorg/json/JSONObject;)Z
    //   133: ifne -90 -> 43
    //   136: invokestatic 804	java/lang/System:currentTimeMillis	()J
    //   139: lload_1
    //   140: lsub
    //   141: ldc2_w 2258
    //   144: lcmp
    //   145: ifle -102 -> 43
    //   148: new 169	java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   155: ldc_w 2261
    //   158: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: aload 6
    //   163: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   169: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   172: aload 4
    //   174: ldc_w 278
    //   177: iconst_1
    //   178: anewarray 280	java/lang/String
    //   181: dup
    //   182: iconst_0
    //   183: ldc_w 330
    //   186: aastore
    //   187: iconst_1
    //   188: anewarray 280	java/lang/String
    //   191: dup
    //   192: iconst_0
    //   193: aload 6
    //   195: aastore
    //   196: invokevirtual 1010	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V
    //   199: goto -156 -> 43
    //   202: astore 5
    //   204: new 169	java/lang/StringBuilder
    //   207: dup
    //   208: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   211: ldc_w 2263
    //   214: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: aload 5
    //   219: invokevirtual 413	java/lang/Throwable:toString	()Ljava/lang/String;
    //   222: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   231: goto -188 -> 43
    //   234: astore 4
    //   236: new 169	java/lang/StringBuilder
    //   239: dup
    //   240: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   243: ldc_w 2263
    //   246: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: aload 4
    //   251: invokevirtual 413	java/lang/Throwable:toString	()Ljava/lang/String;
    //   254: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   260: invokestatic 110	com/igexin/b/a/c/b:a	(Ljava/lang/String;)V
    //   263: aload_3
    //   264: ifnull +9 -> 273
    //   267: aload_3
    //   268: invokeinterface 410 1 0
    //   273: return
    //   274: aload_3
    //   275: ifnull -2 -> 273
    //   278: aload_3
    //   279: invokeinterface 410 1 0
    //   284: return
    //   285: astore 4
    //   287: aconst_null
    //   288: astore_3
    //   289: aload_3
    //   290: ifnull +9 -> 299
    //   293: aload_3
    //   294: invokeinterface 410 1 0
    //   299: aload 4
    //   301: athrow
    //   302: astore 4
    //   304: goto -15 -> 289
    //   307: astore 4
    //   309: goto -20 -> 289
    //   312: astore 4
    //   314: aconst_null
    //   315: astore_3
    //   316: goto -80 -> 236
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	319	0	this	f
    //   84	56	1	l	long
    //   38	278	3	localCursor	android.database.Cursor
    //   6	167	4	localB	com.igexin.push.b.b
    //   234	16	4	localThrowable1	Throwable
    //   285	15	4	localObject1	Object
    //   302	1	4	localObject2	Object
    //   307	1	4	localObject3	Object
    //   312	1	4	localThrowable2	Throwable
    //   67	62	5	localObject4	Object
    //   202	16	5	localThrowable3	Throwable
    //   114	80	6	str	String
    // Exception table:
    //   from	to	target	type
    //   85	199	202	java/lang/Throwable
    //   43	85	234	java/lang/Throwable
    //   204	231	234	java/lang/Throwable
    //   0	39	285	finally
    //   43	85	302	finally
    //   85	199	302	finally
    //   204	231	302	finally
    //   236	263	307	finally
    //   0	39	312	java/lang/Throwable
  }
  
  public void s()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    String str = localSimpleDateFormat.format(new Date());
    Object localObject3 = new File("/sdcard/libs//");
    Object localObject2 = com.igexin.push.core.g.e;
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "unknowPacageName";
    }
    if (!((File)localObject3).exists()) {}
    do
    {
      return;
      localObject2 = ((File)localObject3).list();
    } while (localObject2 == null);
    int j = localObject2.length;
    int i = 0;
    label87:
    int k;
    if (i < j)
    {
      k = localObject2[i].length();
      if ((localObject2[i].startsWith((String)localObject1)) && (localObject2[i].endsWith(".log")) && (k > ((String)localObject1).length() + 14) && (((String)localObject1).equals(localObject2[i].substring(0, k - 15)))) {
        break label164;
      }
    }
    for (;;)
    {
      i += 1;
      break label87;
      break;
      label164:
      localObject3 = localObject2[i].substring(((String)localObject1).length() + 1, k - 4);
      try
      {
        localObject3 = localSimpleDateFormat.parse((String)localObject3);
        if (Math.abs((localSimpleDateFormat.parse(str).getTime() - ((Date)localObject3).getTime()) / 86400000L) > 6L)
        {
          localObject3 = new File("/sdcard/libs//" + localObject2[i]);
          if (((File)localObject3).exists()) {
            ((File)localObject3).delete();
          }
        }
      }
      catch (Exception localException) {}
    }
  }
  
  public void t()
  {
    for (;;)
    {
      boolean bool;
      String str2;
      PushTaskBean localPushTaskBean;
      String str3;
      Map localMap;
      try
      {
        if (H()) {
          return;
        }
        com.igexin.b.a.c.b.a("CoreAction--------checkConditionStatus start to read pushMessageMap data...");
        Iterator localIterator = com.igexin.push.core.g.ai.entrySet().iterator();
        bool = localIterator.hasNext();
        if (!bool) {
          return;
        }
        try
        {
          Object localObject = (Map.Entry)localIterator.next();
          str2 = (String)((Map.Entry)localObject).getKey();
          localPushTaskBean = (PushTaskBean)((Map.Entry)localObject).getValue();
          localObject = "";
          if ((localPushTaskBean == null) || (localPushTaskBean.getStatus() != com.igexin.push.core.a.k)) {
            continue;
          }
          str3 = localPushTaskBean.getTaskId();
          localMap = localPushTaskBean.getConditionMap();
          if (localMap == null) {
            return;
          }
          if (!com.igexin.push.util.a.b(str3)) {
            break label209;
          }
          a(com.igexin.push.core.a.m, str3, str2);
          localPushTaskBean.setStatus(com.igexin.push.core.a.l);
        }
        catch (Exception localException1)
        {
          com.igexin.b.a.c.b.a("CoreAction|" + localException1.toString());
        }
        continue;
        if (!localMap.containsKey("expiredTime")) {
          break label270;
        }
      }
      catch (Exception localException2)
      {
        com.igexin.b.a.c.b.a("CoreAction|" + localException2.toString());
        return;
      }
      label209:
      if (Long.valueOf((String)localMap.get("expiredTime")).longValue() < System.currentTimeMillis())
      {
        a(com.igexin.push.core.a.m, str3, str2);
        localPushTaskBean.setStatus(com.igexin.push.core.a.l);
      }
      else
      {
        label270:
        if ((localMap.containsKey("endTime")) && (Long.valueOf((String)localMap.get("endTime")).longValue() < System.currentTimeMillis()))
        {
          a(com.igexin.push.core.a.m, str3, str2);
          localPushTaskBean.setStatus(com.igexin.push.core.a.l);
        }
        else
        {
          int i;
          if (localMap.containsKey("wifi"))
          {
            i = Integer.valueOf((String)localMap.get("wifi")).intValue();
            w();
            if (i != com.igexin.push.core.g.o) {}
          }
          else if (localMap.containsKey("screenOn"))
          {
            i = Integer.valueOf((String)localMap.get("screenOn")).intValue();
            v();
            if (i != com.igexin.push.core.g.p) {}
          }
          else
          {
            String str1;
            if (localMap.containsKey("ssid"))
            {
              str1 = (String)localMap.get("ssid");
              x();
              if (!com.igexin.push.core.g.ap.containsValue(str1)) {}
            }
            else if (localMap.containsKey("bssid"))
            {
              String str4 = (String)localMap.get("bssid");
              if ((!com.igexin.push.core.g.ap.containsKey(str4)) || (!((String)com.igexin.push.core.g.ap.get(str4)).equals(str1))) {}
            }
            else if ((!localMap.containsKey("startTime")) || (Long.valueOf((String)localMap.get("startTime")).longValue() <= System.currentTimeMillis()))
            {
              bool = localMap.containsKey("netConnected");
              if (bool) {}
              try
              {
                int j = Integer.valueOf((String)localMap.get("netConnected")).intValue();
                bool = com.igexin.push.util.a.g();
                if (bool) {}
                for (i = 1; j == i; i = 0)
                {
                  str1 = localPushTaskBean.getMessageId();
                  a().d(str3, str1, com.igexin.push.core.g.a, com.igexin.push.core.g.e);
                  a(com.igexin.push.core.a.l, str3, str2);
                  localPushTaskBean.setStatus(com.igexin.push.core.a.l);
                  break;
                }
              }
              catch (Exception localException3) {}
            }
          }
        }
      }
    }
  }
  
  public boolean u()
  {
    long l = System.currentTimeMillis();
    if (com.igexin.push.core.g.I > 0L)
    {
      if (l - com.igexin.push.core.g.I > 60000L)
      {
        com.igexin.push.core.g.I = l;
        return true;
      }
    }
    else
    {
      com.igexin.push.core.g.I = l - 60000L;
      return true;
    }
    return false;
  }
  
  public void v()
  {
    if (((PowerManager)com.igexin.push.core.g.f.getSystemService("power")).isScreenOn())
    {
      com.igexin.push.core.g.p = 1;
      return;
    }
    com.igexin.push.core.g.p = 0;
  }
  
  public void w()
  {
    NetworkInfo.State localState = ((ConnectivityManager)com.igexin.push.core.g.f.getSystemService("connectivity")).getNetworkInfo(1).getState();
    if ((localState == NetworkInfo.State.CONNECTED) || (localState == NetworkInfo.State.CONNECTING))
    {
      com.igexin.push.core.g.o = 1;
      return;
    }
    com.igexin.push.core.g.o = 0;
  }
  
  public void x()
  {
    try
    {
      List localList = ((WifiManager)com.igexin.push.core.g.f.getSystemService("wifi")).getScanResults();
      com.igexin.push.core.g.ap.clear();
      if ((localList != null) && (!localList.isEmpty()))
      {
        int i = 0;
        while (i < localList.size())
        {
          com.igexin.push.core.g.ap.put(((ScanResult)localList.get(i)).BSSID, ((ScanResult)localList.get(i)).SSID);
          i += 1;
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      com.igexin.b.a.c.b.a("CoreAction|" + localThrowable.toString());
    }
  }
  
  public void y()
  {
    if ((!com.igexin.push.config.m.p) || (System.currentTimeMillis() - this.e < 300000L)) {}
    do
    {
      return;
      this.e = System.currentTimeMillis();
      localObject1 = com.igexin.push.core.b.g.a().d();
    } while ((((Map)localObject1).isEmpty()) || (com.igexin.push.config.m.E <= 0));
    Object localObject1 = ((Map)localObject1).entrySet().iterator();
    int i = 0;
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (Map.Entry)((Iterator)localObject1).next();
      if (i >= com.igexin.push.config.m.E) {
        break;
      }
      Object localObject2 = (String)((Map.Entry)localObject3).getKey();
      localObject3 = (String)((Map.Entry)localObject3).getValue();
      if ((com.igexin.push.config.m.G) && (com.igexin.push.util.a.a((String)localObject2, "com.igexin.sdk.GActivity"))) {}
      try
      {
        localObject2 = new i(this, (new Random().nextInt(6) + 1) * 1000, (String)localObject2, (String)localObject3);
        com.igexin.push.core.f.a().a((com.igexin.push.f.b.h)localObject2);
        for (;;)
        {
          i += 1;
          break;
          h((String)localObject2, (String)localObject3);
        }
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public void z()
  {
    int i = com.igexin.push.core.g.ao - 100;
    if (i < 0) {}
    for (com.igexin.push.core.g.ao = 0;; com.igexin.push.core.g.ao = i)
    {
      long l = System.currentTimeMillis();
      Iterator localIterator = com.igexin.push.core.g.an.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        String str = (String)localEntry.getKey();
        if (l - ((Long)localEntry.getValue()).longValue() > 3600000L) {
          localIterator.remove();
        }
      }
    }
  }
}
