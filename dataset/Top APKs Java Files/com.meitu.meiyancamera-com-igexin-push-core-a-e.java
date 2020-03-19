package com.igexin.push.core.a;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.a.a.l;
import com.igexin.push.core.b.y;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.bean.n;
import com.igexin.push.d.c.o;
import com.igexin.push.extension.stub.IPushExtension;
import com.igexin.push.util.EncryptUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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

public class e
  extends a
  implements com.igexin.push.e.k
{
  private static Map a;
  private static Map b;
  private static e c;
  private long d;
  
  private e()
  {
    a = new HashMap();
    a.put(Integer.valueOf(0), new i());
    a.put(Integer.valueOf(5), new k());
    a.put(Integer.valueOf(37), new m());
    a.put(Integer.valueOf(9), new q());
    a.put(Integer.valueOf(26), new h());
    a.put(Integer.valueOf(28), new d());
    a.put(Integer.valueOf(97), new j());
    b = new HashMap();
    b.put("goto", new com.igexin.push.core.a.a.g());
    b.put("notification", new com.igexin.push.core.a.a.h());
    b.put("startapp", new com.igexin.push.core.a.a.k());
    b.put("null", new com.igexin.push.core.a.a.f());
    b.put("wakeupsdk", new l());
    b.put("startweb", new com.igexin.push.core.a.a.j());
    b.put("checkapp", new com.igexin.push.core.a.a.b());
    b.put("cleanext", new com.igexin.push.core.a.a.c());
    b.put("enablelog", new com.igexin.push.core.a.a.e());
    b.put("disablelog", new com.igexin.push.core.a.a.d());
    b.put("reportext", new com.igexin.push.core.a.a.i());
  }
  
  private void C()
  {
    com.igexin.push.c.a localA = com.igexin.push.c.i.a().e();
    com.igexin.push.core.i.a().a(com.igexin.push.core.k.c);
    localA.g();
    if ((!com.igexin.push.core.g.j) || (!com.igexin.push.core.g.k))
    {
      if (com.igexin.push.core.g.n)
      {
        com.igexin.push.core.g.n = false;
        com.igexin.a.a.c.a.b("CoreAction|broadcast online state = offline");
        m();
      }
      localA.a(false);
      com.igexin.a.a.c.a.b("CoreAction|isSdkOn = false or isPushOn = false, disconect by user");
    }
    for (;;)
    {
      com.igexin.a.a.c.a.b("CoreAction|TcpException, remove all SocketTask");
      com.igexin.a.a.b.c.b().a(com.igexin.a.a.b.a.a.k.class);
      return;
      if (com.igexin.push.core.g.n)
      {
        com.igexin.push.core.g.n = false;
        com.igexin.a.a.c.a.b("CoreAction|broadcast online state = offline");
        m();
      }
      localA.a(true);
      com.igexin.a.a.c.a.b("CoreAction|disconnect|network, reconnect");
    }
  }
  
  private void D()
  {
    boolean bool2 = false;
    com.igexin.push.core.i.a().a(com.igexin.push.core.k.d);
    Object localObject = com.igexin.push.core.f.a().j();
    boolean bool1;
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).isAvailable()))
      {
        bool1 = true;
        com.igexin.push.core.g.i = bool1;
        label44:
        com.igexin.a.a.c.a.b("CoreAction|network changed, networkAvailable = " + com.igexin.push.core.g.i);
        if ((!com.igexin.push.core.g.n) || (!com.igexin.push.core.g.i)) {
          break label156;
        }
        com.igexin.a.a.c.a.b("CoreAction|network changed, online = true, networkAvailable = true");
        if (System.currentTimeMillis() - com.igexin.push.core.g.Q > 5000L)
        {
          com.igexin.a.a.c.a.b("CoreAction|network changed, online = true, networkAvailable = true, send heart beat ....");
          com.igexin.push.core.g.Q = System.currentTimeMillis();
          if (g() == -2)
          {
            com.igexin.push.core.g.n = false;
            m();
          }
        }
      }
    }
    for (;;)
    {
      if (u())
      {
        com.igexin.a.a.c.a.b("CoreAction|network changed check condition status");
        t();
      }
      return;
      bool1 = false;
      break;
      com.igexin.push.core.g.i = false;
      break label44;
      label156:
      com.igexin.a.a.c.a.b("CoreAction|network changed, disconnect +++");
      com.igexin.push.core.f.a().i().f();
      bool1 = bool2;
      if (com.igexin.push.core.g.i)
      {
        if (com.igexin.push.c.i.a().e().h()) {
          break label211;
        }
        com.igexin.a.a.c.a.b("CoreAction|network changed, domain = backup or trynormal");
      }
      label211:
      for (bool1 = bool2;; bool1 = com.igexin.push.c.i.a().f())
      {
        if (!bool1) {
          break label221;
        }
        com.igexin.a.a.c.a.b("CoreAction|detect result  = true, reconnect will run after detect");
        break;
      }
      label221:
      com.igexin.a.a.c.a.b("CoreAction|reconnect and reset delay = 0");
      com.igexin.a.a.b.a.a.d.a().a(true);
    }
  }
  
  /* Error */
  private boolean E()
  {
    // Byte code:
    //   0: getstatic 283	com/igexin/push/core/g:ah	Ljava/util/Map;
    //   3: invokeinterface 286 1 0
    //   8: ifeq +451 -> 459
    //   11: getstatic 289	com/igexin/push/core/g:p	Z
    //   14: ifeq +445 -> 459
    //   17: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   20: invokevirtual 292	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   23: ldc_w 294
    //   26: iconst_1
    //   27: anewarray 296	java/lang/String
    //   30: dup
    //   31: iconst_0
    //   32: ldc_w 298
    //   35: aastore
    //   36: iconst_1
    //   37: anewarray 296	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: ldc_w 300
    //   45: aastore
    //   46: aconst_null
    //   47: aconst_null
    //   48: invokevirtual 305	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   51: astore_2
    //   52: aload_2
    //   53: ifnull +390 -> 443
    //   56: aload_2
    //   57: astore_1
    //   58: aload_2
    //   59: invokeinterface 310 1 0
    //   64: ifeq +379 -> 443
    //   67: aload_2
    //   68: astore_1
    //   69: aload_2
    //   70: aload_2
    //   71: ldc_w 312
    //   74: invokeinterface 316 2 0
    //   79: invokeinterface 320 2 0
    //   84: astore_3
    //   85: aload_2
    //   86: astore_1
    //   87: aload_2
    //   88: aload_2
    //   89: ldc_w 322
    //   92: invokeinterface 316 2 0
    //   97: invokeinterface 320 2 0
    //   102: astore 4
    //   104: aload_2
    //   105: astore_1
    //   106: new 324	org/json/JSONObject
    //   109: dup
    //   110: new 296	java/lang/String
    //   113: dup
    //   114: aload 4
    //   116: invokestatic 329	com/igexin/a/b/a:c	([B)[B
    //   119: invokespecial 332	java/lang/String:<init>	([B)V
    //   122: invokespecial 334	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   125: astore 4
    //   127: aload_2
    //   128: astore_1
    //   129: aload 4
    //   131: ldc_w 336
    //   134: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   137: astore 5
    //   139: aload_2
    //   140: astore_1
    //   141: aload 4
    //   143: ldc_w 342
    //   146: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   149: astore 6
    //   151: aload_2
    //   152: astore_1
    //   153: aload 4
    //   155: ldc_w 344
    //   158: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   161: astore 7
    //   163: aload_2
    //   164: astore_1
    //   165: aload 4
    //   167: ldc_w 346
    //   170: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   173: astore 8
    //   175: aload_2
    //   176: astore_1
    //   177: aload 4
    //   179: ldc_w 348
    //   182: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   185: astore 9
    //   187: aload_2
    //   188: astore_1
    //   189: aload 4
    //   191: ldc_w 350
    //   194: invokevirtual 354	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   197: astore 10
    //   199: aload_2
    //   200: astore_1
    //   201: invokestatic 356	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   204: aload 8
    //   206: aload 7
    //   208: invokevirtual 359	com/igexin/push/core/a/e:c	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   211: astore 11
    //   213: aload_2
    //   214: astore_1
    //   215: new 361	com/igexin/push/core/bean/PushTaskBean
    //   218: dup
    //   219: invokespecial 362	com/igexin/push/core/bean/PushTaskBean:<init>	()V
    //   222: astore 12
    //   224: aload_2
    //   225: astore_1
    //   226: aload 12
    //   228: aload 6
    //   230: invokevirtual 365	com/igexin/push/core/bean/PushTaskBean:setAppid	(Ljava/lang/String;)V
    //   233: aload_2
    //   234: astore_1
    //   235: aload 12
    //   237: aload 7
    //   239: invokevirtual 368	com/igexin/push/core/bean/PushTaskBean:setMessageId	(Ljava/lang/String;)V
    //   242: aload_2
    //   243: astore_1
    //   244: aload 12
    //   246: aload 8
    //   248: invokevirtual 371	com/igexin/push/core/bean/PushTaskBean:setTaskId	(Ljava/lang/String;)V
    //   251: aload_2
    //   252: astore_1
    //   253: aload 12
    //   255: aload 5
    //   257: invokevirtual 374	com/igexin/push/core/bean/PushTaskBean:setId	(Ljava/lang/String;)V
    //   260: aload_2
    //   261: astore_1
    //   262: aload 12
    //   264: aload 9
    //   266: invokevirtual 377	com/igexin/push/core/bean/PushTaskBean:setAppKey	(Ljava/lang/String;)V
    //   269: aload_2
    //   270: astore_1
    //   271: aload 12
    //   273: iconst_1
    //   274: invokevirtual 381	com/igexin/push/core/bean/PushTaskBean:setCurrentActionid	(I)V
    //   277: aload_2
    //   278: astore_1
    //   279: aload 12
    //   281: aload_2
    //   282: aload_2
    //   283: ldc_w 298
    //   286: invokeinterface 316 2 0
    //   291: invokeinterface 385 2 0
    //   296: invokevirtual 388	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   299: aload_3
    //   300: ifnull +11 -> 311
    //   303: aload_2
    //   304: astore_1
    //   305: aload 12
    //   307: aload_3
    //   308: invokevirtual 391	com/igexin/push/core/bean/PushTaskBean:setMsgExtra	([B)V
    //   311: aload_2
    //   312: astore_1
    //   313: aload 4
    //   315: ldc_w 393
    //   318: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   321: ifeq +18 -> 339
    //   324: aload_2
    //   325: astore_1
    //   326: aload 12
    //   328: aload 4
    //   330: ldc_w 393
    //   333: invokevirtual 400	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   336: invokevirtual 403	com/igexin/push/core/bean/PushTaskBean:setCDNType	(Z)V
    //   339: aload_2
    //   340: astore_1
    //   341: aload 4
    //   343: ldc_w 405
    //   346: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   349: ifeq +13 -> 362
    //   352: aload_2
    //   353: astore_1
    //   354: aload_0
    //   355: aload 4
    //   357: aload 12
    //   359: invokespecial 408	com/igexin/push/core/a/e:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   362: aload 10
    //   364: ifnull +57 -> 421
    //   367: aload_2
    //   368: astore_1
    //   369: aload 10
    //   371: invokevirtual 413	org/json/JSONArray:length	()I
    //   374: ifle +47 -> 421
    //   377: aload_2
    //   378: astore_1
    //   379: invokestatic 356	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   382: aload 4
    //   384: aload 12
    //   386: invokevirtual 416	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)Z
    //   389: ifne +32 -> 421
    //   392: aload_2
    //   393: astore_1
    //   394: new 210	java/lang/StringBuilder
    //   397: dup
    //   398: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   401: ldc_w 418
    //   404: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   407: aload 4
    //   409: invokevirtual 419	org/json/JSONObject:toString	()Ljava/lang/String;
    //   412: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   418: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   421: aload_2
    //   422: astore_1
    //   423: getstatic 283	com/igexin/push/core/g:ah	Ljava/util/Map;
    //   426: aload 11
    //   428: aload 12
    //   430: invokeinterface 37 3 0
    //   435: pop
    //   436: goto -380 -> 56
    //   439: astore_1
    //   440: goto -384 -> 56
    //   443: aload_2
    //   444: astore_1
    //   445: iconst_0
    //   446: putstatic 289	com/igexin/push/core/g:p	Z
    //   449: aload_2
    //   450: ifnull +9 -> 459
    //   453: aload_2
    //   454: invokeinterface 422 1 0
    //   459: getstatic 283	com/igexin/push/core/g:ah	Ljava/util/Map;
    //   462: invokeinterface 286 1 0
    //   467: ireturn
    //   468: astore_3
    //   469: aconst_null
    //   470: astore_2
    //   471: aload_2
    //   472: astore_1
    //   473: new 210	java/lang/StringBuilder
    //   476: dup
    //   477: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   480: ldc_w 424
    //   483: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: aload_3
    //   487: invokevirtual 425	java/lang/Throwable:toString	()Ljava/lang/String;
    //   490: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   493: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   496: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   499: aload_2
    //   500: ifnull -41 -> 459
    //   503: aload_2
    //   504: invokeinterface 422 1 0
    //   509: goto -50 -> 459
    //   512: astore_2
    //   513: aconst_null
    //   514: astore_1
    //   515: aload_1
    //   516: ifnull +9 -> 525
    //   519: aload_1
    //   520: invokeinterface 422 1 0
    //   525: aload_2
    //   526: athrow
    //   527: astore_2
    //   528: goto -13 -> 515
    //   531: astore_3
    //   532: goto -61 -> 471
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	535	0	this	e
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
  
  public static e a()
  {
    if (c == null) {
      c = new e();
    }
    return c;
  }
  
  private void a(int paramInt, String paramString1, String paramString2)
  {
    paramString2 = new ContentValues();
    paramString2.put("status", Integer.valueOf(paramInt));
    com.igexin.push.core.f.a().k().a("message", paramString2, new String[] { "taskid" }, new String[] { paramString1 });
  }
  
  private void a(com.igexin.push.d.c.c paramC, PushTaskBean paramPushTaskBean, String paramString1, String paramString2)
  {
    paramC.a(new com.igexin.push.f.b.b(paramPushTaskBean, paramString1, k()));
    com.igexin.push.core.g.ak.put(paramString2, paramC);
  }
  
  private void a(List paramList)
  {
    f localF = new f(this);
    PackageManager localPackageManager = com.igexin.push.core.g.g.getPackageManager();
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
          n localN = new n();
          localN.b(localApplicationInfo.loadLabel(localPackageManager).toString());
          localN.d(localApplicationInfo.packageName);
          localN.c(String.valueOf(localPackageInfo.versionCode));
          localN.a(localPackageInfo.versionName);
          paramList.add(localN);
        }
        i += 1;
        continue;
        Collections.sort(paramList, localF);
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
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
        String str = ((JSONObject)localObject).getString("duration");
        if (str.contains("-"))
        {
          int i = str.indexOf("-");
          localObject = str.substring(0, i);
          str = str.substring(i + 1, str.length());
          paramJSONObject.put("startTime", localObject);
          paramJSONObject.put("endTime", str);
        }
      }
      paramPushTaskBean.setConditionMap(paramJSONObject);
      return;
    }
    catch (Exception paramJSONObject) {}
  }
  
  private void b(byte[] paramArrayOfByte)
  {
    try
    {
      com.igexin.push.util.e.a(paramArrayOfByte, com.igexin.push.core.g.g.getFilesDir().getPath() + "/" + "conf_n.pid");
      return;
    }
    catch (Exception paramArrayOfByte) {}
  }
  
  private void e(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("package:")))
    {
      paramString = paramString.substring(8);
      if (com.igexin.push.core.b.f.a().d().containsKey(paramString)) {
        com.igexin.push.core.b.f.a().d().remove(paramString);
      }
    }
  }
  
  private void e(String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setPackage(paramString1);
      localIntent.setAction("com.igexin.sdk.action.service.message");
      if (com.igexin.push.util.a.a(localIntent, com.igexin.push.core.g.g))
      {
        com.igexin.push.core.g.g.startService(localIntent);
        com.igexin.a.a.c.a.b("CoreAction|startService by action");
        return;
      }
      localIntent = new Intent();
      localIntent.setClassName(paramString1, paramString2);
      com.igexin.push.core.g.g.startService(localIntent);
      com.igexin.a.a.c.a.b("CoreAction|startService by service name");
      return;
    }
    catch (Throwable localThrowable)
    {
      com.igexin.a.a.c.a.b("CoreAction|startService pkgName = " + paramString1 + " srvName = " + paramString2 + ", exception : " + localThrowable.toString());
    }
  }
  
  private void f(Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("taskid");
    String str2 = paramIntent.getStringExtra("messageid");
    String str3 = paramIntent.getStringExtra("actionid");
    String str4 = paramIntent.getStringExtra("accesstoken");
    int i = paramIntent.getIntExtra("notifID", 0);
    paramIntent = (NotificationManager)com.igexin.push.core.g.g.getSystemService("notification");
    if (i != 0) {
      paramIntent.cancel(i);
    }
    while (!str4.equals(com.igexin.push.core.g.at))
    {
      return;
      if (com.igexin.push.core.g.ai.get(str1) != null) {
        paramIntent.cancel(((Integer)com.igexin.push.core.g.ai.get(str1)).intValue());
      }
    }
    b(str1, str2, str3);
  }
  
  private void f(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("package:"))) {
      paramString = paramString.substring(8);
    }
    try
    {
      Object localObject1 = com.igexin.push.core.g.g.getPackageManager().getPackageInfo(paramString, 4);
      int j;
      int i;
      if (localObject1 != null)
      {
        localObject1 = ((PackageInfo)localObject1).services;
        if (localObject1 != null)
        {
          j = localObject1.length;
          i = 0;
        }
      }
      for (;;)
      {
        if (i < j)
        {
          Object localObject2 = localObject1[i];
          if ((com.igexin.push.core.a.o.equals(localObject2.name)) || (com.igexin.push.core.a.n.equals(localObject2.name)) || (com.igexin.push.core.a.p.equals(localObject2.name))) {
            com.igexin.push.core.b.f.a().d().put(paramString, localObject2.name);
          }
        }
        else
        {
          return;
        }
        i += 1;
      }
      return;
    }
    catch (Exception paramString) {}
  }
  
  private void f(boolean paramBoolean)
  {
    com.igexin.push.d.a.c.b = -1;
    if (EncryptUtils.isLoadSuccess())
    {
      com.igexin.a.a.c.a.b("CoreAction|reInitSo success and reconnect");
      com.igexin.push.core.f.a().g().c(paramBoolean);
      return;
    }
    com.igexin.a.a.c.a.b("CoreAction|reInitSo error ++++++++");
  }
  
  /* Error */
  private void g(Intent paramIntent)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 346
    //   4: invokevirtual 659	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   7: astore 4
    //   9: aload_1
    //   10: ldc_w 344
    //   13: invokevirtual 659	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   16: astore 5
    //   18: aload_1
    //   19: ldc_w 342
    //   22: invokevirtual 659	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   25: astore_3
    //   26: aload_1
    //   27: ldc_w 739
    //   30: invokevirtual 659	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   33: astore 6
    //   35: new 431	android/content/ContentValues
    //   38: dup
    //   39: invokespecial 432	android/content/ContentValues:<init>	()V
    //   42: astore 7
    //   44: new 210	java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   51: ldc_w 741
    //   54: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: aload 4
    //   59: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: astore_1
    //   66: aload 7
    //   68: ldc_w 346
    //   71: aload 4
    //   73: invokevirtual 743	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   76: aload 7
    //   78: ldc_w 342
    //   81: aload_3
    //   82: invokevirtual 743	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   85: aload 7
    //   87: ldc_w 745
    //   90: aload_1
    //   91: invokevirtual 743	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   94: aload 7
    //   96: ldc_w 747
    //   99: invokestatic 232	java/lang/System:currentTimeMillis	()J
    //   102: invokestatic 752	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   105: invokevirtual 755	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   108: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   111: invokevirtual 292	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   114: ldc_w 294
    //   117: iconst_1
    //   118: anewarray 296	java/lang/String
    //   121: dup
    //   122: iconst_0
    //   123: ldc_w 745
    //   126: aastore
    //   127: iconst_1
    //   128: anewarray 296	java/lang/String
    //   131: dup
    //   132: iconst_0
    //   133: aload_1
    //   134: aastore
    //   135: aconst_null
    //   136: aconst_null
    //   137: invokevirtual 305	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   140: astore_3
    //   141: aload_3
    //   142: ifnull +104 -> 246
    //   145: aload_3
    //   146: astore_1
    //   147: aload_3
    //   148: invokeinterface 758 1 0
    //   153: ifne +93 -> 246
    //   156: aload_3
    //   157: astore_1
    //   158: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   161: invokevirtual 292	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   164: ldc_w 294
    //   167: aload 7
    //   169: invokevirtual 761	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   172: aload_3
    //   173: astore_1
    //   174: aload 6
    //   176: getstatic 763	com/igexin/push/core/g:e	Ljava/lang/String;
    //   179: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   182: istore_2
    //   183: iload_2
    //   184: ifeq +62 -> 246
    //   187: aload 5
    //   189: ifnull +8 -> 197
    //   192: aload 4
    //   194: ifnonnull +14 -> 208
    //   197: aload_3
    //   198: ifnull +9 -> 207
    //   201: aload_3
    //   202: invokeinterface 422 1 0
    //   207: return
    //   208: aload_3
    //   209: astore_1
    //   210: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   213: ifnull +33 -> 246
    //   216: aload_3
    //   217: astore_1
    //   218: aload_0
    //   219: aload 4
    //   221: aload 5
    //   223: invokevirtual 766	com/igexin/push/core/a/e:d	(Ljava/lang/String;Ljava/lang/String;)Lcom/igexin/push/core/b;
    //   226: getstatic 771	com/igexin/push/core/b:a	Lcom/igexin/push/core/b;
    //   229: if_acmpne +17 -> 246
    //   232: aload_3
    //   233: astore_1
    //   234: aload_0
    //   235: aload 4
    //   237: aload 5
    //   239: ldc_w 773
    //   242: invokevirtual 775	com/igexin/push/core/a/e:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   245: pop
    //   246: aload_3
    //   247: ifnull -40 -> 207
    //   250: aload_3
    //   251: invokeinterface 422 1 0
    //   256: return
    //   257: astore 4
    //   259: aconst_null
    //   260: astore_3
    //   261: aload_3
    //   262: astore_1
    //   263: new 210	java/lang/StringBuilder
    //   266: dup
    //   267: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   270: ldc_w 777
    //   273: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: aload 4
    //   278: invokevirtual 425	java/lang/Throwable:toString	()Ljava/lang/String;
    //   281: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   287: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   290: aload_3
    //   291: ifnull -84 -> 207
    //   294: aload_3
    //   295: invokeinterface 422 1 0
    //   300: return
    //   301: astore_3
    //   302: aconst_null
    //   303: astore_1
    //   304: aload_1
    //   305: ifnull +9 -> 314
    //   308: aload_1
    //   309: invokeinterface 422 1 0
    //   314: aload_3
    //   315: athrow
    //   316: astore_3
    //   317: goto -13 -> 304
    //   320: astore 4
    //   322: goto -61 -> 261
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	325	0	this	e
    //   0	325	1	paramIntent	Intent
    //   182	2	2	bool	boolean
    //   25	270	3	localObject1	Object
    //   301	14	3	localObject2	Object
    //   316	1	3	localObject3	Object
    //   7	229	4	str1	String
    //   257	20	4	localThrowable1	Throwable
    //   320	1	4	localThrowable2	Throwable
    //   16	222	5	str2	String
    //   33	142	6	str3	String
    //   42	126	7	localContentValues	ContentValues
    // Exception table:
    //   from	to	target	type
    //   108	141	257	java/lang/Throwable
    //   108	141	301	finally
    //   147	156	316	finally
    //   158	172	316	finally
    //   174	183	316	finally
    //   210	216	316	finally
    //   218	232	316	finally
    //   234	246	316	finally
    //   263	290	316	finally
    //   147	156	320	java/lang/Throwable
    //   158	172	320	java/lang/Throwable
    //   174	183	320	java/lang/Throwable
    //   210	216	320	java/lang/Throwable
    //   218	232	320	java/lang/Throwable
    //   234	246	320	java/lang/Throwable
  }
  
  /* Error */
  private void g(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 586	java/io/File
    //   5: dup
    //   6: getstatic 779	com/igexin/push/core/g:Z	Ljava/lang/String;
    //   9: invokespecial 780	java/io/File:<init>	(Ljava/lang/String;)V
    //   12: astore_2
    //   13: aload_2
    //   14: invokevirtual 783	java/io/File:exists	()Z
    //   17: ifne +55 -> 72
    //   20: aload_2
    //   21: invokevirtual 786	java/io/File:createNewFile	()Z
    //   24: ifne +48 -> 72
    //   27: new 210	java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   34: ldc_w 788
    //   37: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_2
    //   41: invokevirtual 789	java/io/File:toString	()Ljava/lang/String;
    //   44: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc_w 791
    //   50: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   59: iconst_0
    //   60: ifeq +11 -> 71
    //   63: new 793	java/lang/NullPointerException
    //   66: dup
    //   67: invokespecial 794	java/lang/NullPointerException:<init>	()V
    //   70: athrow
    //   71: return
    //   72: new 796	java/io/FileOutputStream
    //   75: dup
    //   76: getstatic 779	com/igexin/push/core/g:Z	Ljava/lang/String;
    //   79: invokespecial 797	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   82: astore_2
    //   83: aload_2
    //   84: aload_1
    //   85: invokestatic 799	com/igexin/a/b/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   88: invokevirtual 803	java/lang/String:getBytes	()[B
    //   91: invokevirtual 806	java/io/FileOutputStream:write	([B)V
    //   94: aload_2
    //   95: ifnull -24 -> 71
    //   98: aload_2
    //   99: invokevirtual 807	java/io/FileOutputStream:close	()V
    //   102: return
    //   103: astore_1
    //   104: return
    //   105: astore_1
    //   106: aload_3
    //   107: astore_1
    //   108: aload_1
    //   109: ifnull -38 -> 71
    //   112: aload_1
    //   113: invokevirtual 807	java/io/FileOutputStream:close	()V
    //   116: return
    //   117: astore_1
    //   118: return
    //   119: astore_1
    //   120: aconst_null
    //   121: astore_2
    //   122: aload_2
    //   123: ifnull +7 -> 130
    //   126: aload_2
    //   127: invokevirtual 807	java/io/FileOutputStream:close	()V
    //   130: aload_1
    //   131: athrow
    //   132: astore_1
    //   133: return
    //   134: astore_2
    //   135: goto -5 -> 130
    //   138: astore_1
    //   139: goto -17 -> 122
    //   142: astore_1
    //   143: aload_2
    //   144: astore_1
    //   145: goto -37 -> 108
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	148	0	this	e
    //   0	148	1	paramString	String
    //   12	115	2	localObject1	Object
    //   134	10	2	localException	Exception
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
  
  public void A()
  {
    if (com.igexin.push.core.g.P < System.currentTimeMillis()) {
      com.igexin.push.core.b.f.a().a(false);
    }
  }
  
  public void B()
  {
    if (!com.igexin.push.core.g.ae) {
      com.igexin.push.core.g.ae = com.igexin.a.a.b.c.b().a(com.igexin.push.f.b.c.g(), false, true);
    }
    if (!com.igexin.push.core.g.af) {
      com.igexin.push.core.g.af = com.igexin.a.a.b.c.b().a(com.igexin.push.f.b.e.g(), true, true);
    }
    if (!com.igexin.push.core.g.ag) {
      com.igexin.push.core.f.a().c();
    }
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
        com.igexin.a.a.c.a.b(paramG.toString());
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
    //   6: new 972	java/text/SimpleDateFormat
    //   9: dup
    //   10: ldc_w 974
    //   13: invokestatic 980	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   16: invokespecial 983	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   19: new 985	java/util/Date
    //   22: dup
    //   23: invokespecial 986	java/util/Date:<init>	()V
    //   26: invokevirtual 990	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   29: astore 13
    //   31: iload_2
    //   32: iconst_m1
    //   33: if_icmpne +83 -> 116
    //   36: new 210	java/lang/StringBuilder
    //   39: dup
    //   40: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   43: aload 13
    //   45: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: ldc_w 992
    //   51: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   57: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: ldc_w 992
    //   63: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: ldc_w 996
    //   69: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: ldc_w 992
    //   75: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: getstatic 998	com/igexin/push/core/g:u	Ljava/lang/String;
    //   81: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: astore 13
    //   89: aload 13
    //   91: astore 11
    //   93: aload 11
    //   95: astore 13
    //   97: aload 12
    //   99: ifnull +14 -> 113
    //   102: aload 12
    //   104: invokeinterface 422 1 0
    //   109: aload 11
    //   111: astore 13
    //   113: aload 13
    //   115: areturn
    //   116: iload_2
    //   117: ifne +983 -> 1100
    //   120: iload_1
    //   121: ifeq +578 -> 699
    //   124: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   127: invokevirtual 292	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   130: ldc_w 1000
    //   133: iconst_1
    //   134: anewarray 296	java/lang/String
    //   137: dup
    //   138: iconst_0
    //   139: ldc_w 1002
    //   142: aastore
    //   143: iconst_2
    //   144: anewarray 296	java/lang/String
    //   147: dup
    //   148: iconst_0
    //   149: ldc_w 773
    //   152: aastore
    //   153: dup
    //   154: iconst_1
    //   155: ldc_w 1004
    //   158: aastore
    //   159: aconst_null
    //   160: aconst_null
    //   161: invokevirtual 305	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
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
    //   184: invokeinterface 310 1 0
    //   189: ifeq +900 -> 1089
    //   192: aload 11
    //   194: aload 11
    //   196: ldc_w 1006
    //   199: invokeinterface 1009 2 0
    //   204: invokeinterface 385 2 0
    //   209: istore_2
    //   210: aload 11
    //   212: aload 11
    //   214: ldc_w 1011
    //   217: invokeinterface 1009 2 0
    //   222: invokeinterface 385 2 0
    //   227: istore_3
    //   228: aload 11
    //   230: aload 11
    //   232: ldc_w 1013
    //   235: invokeinterface 1009 2 0
    //   240: invokeinterface 385 2 0
    //   245: istore 4
    //   247: aload 11
    //   249: aload 11
    //   251: ldc_w 1015
    //   254: invokeinterface 1009 2 0
    //   259: invokeinterface 385 2 0
    //   264: istore 5
    //   266: aload 11
    //   268: aload 11
    //   270: ldc_w 1017
    //   273: invokeinterface 1009 2 0
    //   278: invokeinterface 385 2 0
    //   283: istore 6
    //   285: aload 11
    //   287: aload 11
    //   289: ldc_w 1019
    //   292: invokeinterface 1009 2 0
    //   297: invokeinterface 385 2 0
    //   302: istore 7
    //   304: aload 11
    //   306: aload 11
    //   308: ldc_w 1021
    //   311: invokeinterface 1009 2 0
    //   316: invokeinterface 385 2 0
    //   321: istore 8
    //   323: new 210	java/lang/StringBuilder
    //   326: dup
    //   327: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   330: aload 11
    //   332: aload 11
    //   334: ldc_w 1023
    //   337: invokeinterface 1009 2 0
    //   342: invokeinterface 1025 2 0
    //   347: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: ldc_w 1027
    //   353: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: astore 13
    //   361: aload 12
    //   363: ifnonnull +379 -> 742
    //   366: new 210	java/lang/StringBuilder
    //   369: dup
    //   370: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   373: aload 13
    //   375: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: ldc_w 992
    //   381: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   387: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: ldc_w 992
    //   393: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: ldc_w 1029
    //   399: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: ldc_w 992
    //   405: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: iload_2
    //   409: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   412: ldc_w 1034
    //   415: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   418: aload 13
    //   420: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: ldc_w 992
    //   426: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   429: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   432: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: ldc_w 992
    //   438: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: ldc_w 1036
    //   444: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: ldc_w 992
    //   450: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: iload_3
    //   454: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   457: ldc_w 1034
    //   460: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: aload 13
    //   465: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   468: ldc_w 992
    //   471: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   474: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   477: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   480: ldc_w 992
    //   483: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: ldc_w 1038
    //   489: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: ldc_w 992
    //   495: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: iload 4
    //   500: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   503: ldc_w 1034
    //   506: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   509: aload 13
    //   511: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   514: ldc_w 992
    //   517: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   523: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: ldc_w 992
    //   529: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   532: ldc_w 1040
    //   535: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   538: ldc_w 992
    //   541: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   544: iload 5
    //   546: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   549: ldc_w 1034
    //   552: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   555: aload 13
    //   557: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: ldc_w 992
    //   563: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   569: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: ldc_w 992
    //   575: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   578: ldc_w 1042
    //   581: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: ldc_w 992
    //   587: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   590: iload 6
    //   592: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   595: ldc_w 1034
    //   598: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   601: aload 13
    //   603: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   606: ldc_w 992
    //   609: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   612: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   615: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   618: ldc_w 992
    //   621: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   624: ldc_w 1044
    //   627: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: ldc_w 992
    //   633: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: iload 7
    //   638: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   641: ldc_w 1034
    //   644: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   647: aload 13
    //   649: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   652: ldc_w 992
    //   655: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   658: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   661: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: ldc_w 992
    //   667: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: ldc_w 1046
    //   673: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   676: ldc_w 992
    //   679: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   682: iload 8
    //   684: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   687: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   690: astore 13
    //   692: aload 13
    //   694: astore 12
    //   696: goto -518 -> 178
    //   699: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   702: invokevirtual 292	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   705: ldc_w 1000
    //   708: iconst_1
    //   709: anewarray 296	java/lang/String
    //   712: dup
    //   713: iconst_0
    //   714: ldc_w 1002
    //   717: aastore
    //   718: iconst_1
    //   719: anewarray 296	java/lang/String
    //   722: dup
    //   723: iconst_0
    //   724: ldc_w 1004
    //   727: aastore
    //   728: aconst_null
    //   729: aconst_null
    //   730: invokevirtual 305	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   733: astore 12
    //   735: aload 12
    //   737: astore 11
    //   739: goto -569 -> 170
    //   742: new 210	java/lang/StringBuilder
    //   745: dup
    //   746: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   749: aload 12
    //   751: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   754: ldc_w 1034
    //   757: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   760: aload 13
    //   762: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   765: ldc_w 992
    //   768: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   771: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   774: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   777: ldc_w 992
    //   780: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   783: ldc_w 1029
    //   786: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   789: ldc_w 992
    //   792: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   795: iload_2
    //   796: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   799: ldc_w 1034
    //   802: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: aload 13
    //   807: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: ldc_w 992
    //   813: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   816: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   819: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   822: ldc_w 992
    //   825: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   828: ldc_w 1036
    //   831: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   834: ldc_w 992
    //   837: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   840: iload_3
    //   841: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   844: ldc_w 1034
    //   847: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   850: aload 13
    //   852: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   855: ldc_w 992
    //   858: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   861: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   864: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   867: ldc_w 992
    //   870: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   873: ldc_w 1038
    //   876: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   879: ldc_w 992
    //   882: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   885: iload 4
    //   887: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   890: ldc_w 1034
    //   893: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   896: aload 13
    //   898: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   901: ldc_w 992
    //   904: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   907: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   910: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   913: ldc_w 992
    //   916: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: ldc_w 1040
    //   922: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   925: ldc_w 992
    //   928: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   931: iload 5
    //   933: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   936: ldc_w 1034
    //   939: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   942: aload 13
    //   944: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   947: ldc_w 992
    //   950: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   953: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   956: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   959: ldc_w 992
    //   962: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   965: ldc_w 1042
    //   968: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   971: ldc_w 992
    //   974: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   977: iload 6
    //   979: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   982: ldc_w 1034
    //   985: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   988: aload 13
    //   990: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   993: ldc_w 992
    //   996: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   999: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   1002: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1005: ldc_w 992
    //   1008: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1011: ldc_w 1044
    //   1014: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1017: ldc_w 992
    //   1020: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1023: iload 7
    //   1025: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1028: ldc_w 1034
    //   1031: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1034: aload 13
    //   1036: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1039: ldc_w 992
    //   1042: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1045: getstatic 994	com/igexin/push/core/g:B	Ljava/lang/String;
    //   1048: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1051: ldc_w 992
    //   1054: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1057: ldc_w 1046
    //   1060: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1063: ldc_w 992
    //   1066: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1069: iload 8
    //   1071: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1074: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
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
    //   1105: invokestatic 128	com/igexin/push/core/i:a	()Lcom/igexin/push/core/i;
    //   1108: getfield 1048	com/igexin/push/core/i:a	J
    //   1111: lstore 9
    //   1113: getstatic 1052	com/igexin/push/config/m:d	I
    //   1116: ifle +13 -> 1129
    //   1119: getstatic 1052	com/igexin/push/config/m:d	I
    //   1122: sipush 1000
    //   1125: imul
    //   1126: i2l
    //   1127: lstore 9
    //   1129: new 210	java/lang/StringBuilder
    //   1132: dup
    //   1133: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   1136: getstatic 1054	com/igexin/push/config/m:a	I
    //   1139: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1142: ldc_w 962
    //   1145: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1148: getstatic 1055	com/igexin/push/config/m:b	I
    //   1151: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1154: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1157: astore 14
    //   1159: new 210	java/lang/StringBuilder
    //   1162: dup
    //   1163: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   1166: aload 13
    //   1168: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1171: ldc_w 992
    //   1174: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1177: getstatic 1057	com/igexin/push/core/g:t	Ljava/lang/String;
    //   1180: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: ldc_w 992
    //   1186: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1189: getstatic 1059	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1192: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1195: ldc_w 992
    //   1198: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1201: getstatic 147	com/igexin/push/core/g:j	Z
    //   1204: invokevirtual 220	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   1207: ldc_w 992
    //   1210: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1213: aload 14
    //   1215: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1218: ldc_w 992
    //   1221: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1224: lload 9
    //   1226: invokevirtual 1062	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1229: ldc_w 992
    //   1232: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1235: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1238: astore 13
    //   1240: aload 13
    //   1242: astore 11
    //   1244: goto -1151 -> 93
    //   1247: iload_2
    //   1248: iconst_4
    //   1249: if_icmpne +57 -> 1306
    //   1252: new 210	java/lang/StringBuilder
    //   1255: dup
    //   1256: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   1259: aload 13
    //   1261: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1264: ldc_w 992
    //   1267: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: getstatic 1057	com/igexin/push/core/g:t	Ljava/lang/String;
    //   1273: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1276: ldc_w 992
    //   1279: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1282: getstatic 1059	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1285: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1288: ldc_w 992
    //   1291: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1294: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1297: astore 13
    //   1299: aload 13
    //   1301: astore 11
    //   1303: goto -1210 -> 93
    //   1306: iload_2
    //   1307: iconst_5
    //   1308: if_icmpne +105 -> 1413
    //   1311: new 210	java/lang/StringBuilder
    //   1314: dup
    //   1315: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   1318: aload 13
    //   1320: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1323: ldc_w 992
    //   1326: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1329: getstatic 1057	com/igexin/push/core/g:t	Ljava/lang/String;
    //   1332: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1335: ldc_w 992
    //   1338: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1341: getstatic 1059	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1344: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1347: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
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
    //   1375: invokeinterface 422 1 0
    //   1380: aload 12
    //   1382: areturn
    //   1383: astore 12
    //   1385: aconst_null
    //   1386: astore 11
    //   1388: aload 11
    //   1390: ifnull +10 -> 1400
    //   1393: aload 11
    //   1395: invokeinterface 422 1 0
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
    //   0	1419	0	this	e
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
  
  @TargetApi(12)
  public void a(int paramInt)
  {
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT >= 12) {
      localIntent.addFlags(32);
    }
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10008);
    localBundle.putInt("pid", paramInt);
    localIntent.putExtras(localBundle);
    com.igexin.push.core.f.a().a(localIntent);
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
    if (com.igexin.push.core.g.n)
    {
      com.igexin.a.a.c.a.b("setHeartbeatInterval heartbeatReq");
      if (System.currentTimeMillis() - com.igexin.push.core.g.Q > 5000L)
      {
        com.igexin.push.core.g.Q = System.currentTimeMillis();
        g();
      }
    }
  }
  
  public void a(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      com.igexin.push.core.f.a().a(false);
      if (!paramIntent.hasExtra("op_app")) {
        break label51;
      }
    }
    label51:
    for (com.igexin.push.core.g.D = paramIntent.getStringExtra("op_app");; com.igexin.push.core.g.D = "")
    {
      com.igexin.push.core.g.o = false;
      if (com.igexin.push.core.g.n)
      {
        l();
        com.igexin.push.core.g.o = true;
      }
      return;
    }
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
                a(paramBundle.getInt("beginHour", 0), paramBundle.getInt("duration", 0), com.igexin.push.core.g.g.getPackageName());
                return;
                if (!str1.equals("sendMessage")) {
                  break;
                }
                com.igexin.a.a.c.a.b("CoreAction onPushManagerMessage recevie action : sendMessage");
              } while (!com.igexin.push.config.m.i);
              str1 = paramBundle.getString("taskid");
              paramBundle = paramBundle.getByteArray("extraData");
              com.igexin.a.a.c.a.b("CoreAction receive broadcast msg data , task id : " + str1 + " ######@##@@@#");
              a(str1, paramBundle);
              return;
              if (str1.equals("stopService"))
              {
                com.igexin.push.core.f.a().a(com.igexin.push.core.g.g.getPackageName());
                return;
              }
              if (!str1.equals("setHeartbeatInterval")) {
                break;
              }
            } while (!com.igexin.push.config.m.l);
            a(paramBundle.getInt("interval", 0), com.igexin.push.core.g.g.getPackageName());
            return;
            if (!str1.equals("setSocketTimeout")) {
              break;
            }
          } while (!com.igexin.push.config.m.m);
          b(paramBundle.getInt("timeout", 0), com.igexin.push.core.g.g.getPackageName());
          return;
          if (!str1.equals("sendFeedbackMessage")) {
            break;
          }
        } while ((!com.igexin.push.config.m.r) || (com.igexin.push.core.g.am > 200));
        str1 = paramBundle.getString("taskid");
        str2 = paramBundle.getString("messageid");
        paramBundle = paramBundle.getString("actionid");
        str3 = str1 + ":" + str2 + ":" + paramBundle;
      } while (com.igexin.push.core.g.al.get(str3) != null);
      long l = System.currentTimeMillis();
      PushTaskBean localPushTaskBean = new PushTaskBean();
      localPushTaskBean.setTaskId(str1);
      localPushTaskBean.setMessageId(str2);
      localPushTaskBean.setAppid(com.igexin.push.core.g.a);
      localPushTaskBean.setAppKey(com.igexin.push.core.g.b);
      a(localPushTaskBean, paramBundle);
      com.igexin.push.core.g.am += 1;
      com.igexin.push.core.g.al.put(str3, Long.valueOf(l));
      return;
      if (str1.equals("turnOffPush"))
      {
        com.igexin.push.core.f.a().b(com.igexin.push.core.g.g.getPackageName());
        return;
      }
      if (str1.equals("bindAlias"))
      {
        paramBundle = paramBundle.getString("alias");
        com.igexin.a.a.c.a.b("CoreAction|onPushManagerMessage bindAlias...");
        b(paramBundle);
        return;
      }
    } while (!str1.equals("unbindAlias"));
    str1 = paramBundle.getString("alias");
    boolean bool = paramBundle.getBoolean("isSeft");
    com.igexin.a.a.c.a.b("CoreAction|onPushManagerMessage unbindAlias...");
    a(str1, bool);
  }
  
  public void a(PushTaskBean paramPushTaskBean)
  {
    com.igexin.push.d.c.c localC = new com.igexin.push.d.c.c();
    localC.a();
    localC.c = ("RCV" + paramPushTaskBean.getMessageId());
    localC.d = com.igexin.push.core.g.t;
    localC.a = ((int)System.currentTimeMillis());
    com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.t, localC);
    com.igexin.a.a.c.a.b("CoreAction|cdnreceive " + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId());
  }
  
  public void a(PushTaskBean paramPushTaskBean, String paramString)
  {
    if (paramPushTaskBean.isCDNType())
    {
      b(paramPushTaskBean, paramString);
      return;
    }
    a(paramPushTaskBean, paramString, "ok");
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
      ((com.igexin.push.d.c.d)localObject).g = com.igexin.push.core.g.t;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.t, (com.igexin.push.d.c.e)localObject);
      localObject = com.igexin.push.core.b.c.a();
      if (localObject != null) {
        ((com.igexin.push.core.b.c)localObject).a(new com.igexin.push.core.bean.j(l, paramString2, (byte)3, l));
      }
      com.igexin.a.a.c.a.b("feedback|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId() + "|" + paramString1);
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
      ((com.igexin.push.d.c.d)localObject).g = com.igexin.push.core.g.t;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.t, (com.igexin.push.d.c.e)localObject);
      return;
    }
    catch (JSONException paramString)
    {
      for (;;) {}
    }
  }
  
  public void a(String paramString, com.igexin.push.d.c.a paramA, PushTaskBean paramPushTaskBean)
  {
    paramString = new com.igexin.push.f.a.a(new com.igexin.push.core.c.c(paramString, paramA, paramPushTaskBean));
    com.igexin.a.a.b.c.b().a(paramString, false, true);
  }
  
  /* Error */
  public void a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 1057	com/igexin/push/core/g:t	Ljava/lang/String;
    //   3: invokestatic 1136	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifeq +4 -> 10
    //   9: return
    //   10: invokestatic 232	java/lang/System:currentTimeMillis	()J
    //   13: lstore_3
    //   14: new 324	org/json/JSONObject
    //   17: dup
    //   18: invokespecial 897	org/json/JSONObject:<init>	()V
    //   21: astore 5
    //   23: aload 5
    //   25: ldc_w 1081
    //   28: ldc_w 1326
    //   31: invokevirtual 906	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   34: pop
    //   35: aload 5
    //   37: ldc_w 336
    //   40: lload_3
    //   41: invokestatic 1278	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   44: invokevirtual 906	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   47: pop
    //   48: aload 5
    //   50: ldc_w 1328
    //   53: getstatic 1057	com/igexin/push/core/g:t	Ljava/lang/String;
    //   56: invokevirtual 906	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   59: pop
    //   60: aload 5
    //   62: ldc_w 342
    //   65: getstatic 1059	com/igexin/push/core/g:a	Ljava/lang/String;
    //   68: invokevirtual 906	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   71: pop
    //   72: aload 5
    //   74: ldc_w 1141
    //   77: aload_1
    //   78: ldc_w 1330
    //   81: invokestatic 1335	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   84: invokevirtual 906	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   87: pop
    //   88: aload 5
    //   90: ldc_w 1143
    //   93: aload_2
    //   94: invokevirtual 906	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   97: pop
    //   98: aload 5
    //   100: invokevirtual 419	org/json/JSONObject:toString	()Ljava/lang/String;
    //   103: astore_1
    //   104: invokestatic 1302	com/igexin/push/core/b/c:a	()Lcom/igexin/push/core/b/c;
    //   107: astore_2
    //   108: aload_2
    //   109: ifnull +18 -> 127
    //   112: aload_2
    //   113: new 1304	com/igexin/push/core/bean/j
    //   116: dup
    //   117: lload_3
    //   118: aload_1
    //   119: iconst_2
    //   120: lload_3
    //   121: invokespecial 1307	com/igexin/push/core/bean/j:<init>	(JLjava/lang/String;BJ)V
    //   124: invokevirtual 1310	com/igexin/push/core/b/c:a	(Lcom/igexin/push/core/bean/j;)V
    //   127: new 1287	com/igexin/push/d/c/d
    //   130: dup
    //   131: invokespecial 1288	com/igexin/push/d/c/d:<init>	()V
    //   134: astore_2
    //   135: aload_2
    //   136: invokevirtual 1289	com/igexin/push/d/c/d:a	()V
    //   139: aload_2
    //   140: ldc_w 1292
    //   143: putfield 1293	com/igexin/push/d/c/d:d	Ljava/lang/String;
    //   146: aload_2
    //   147: aload_1
    //   148: putfield 1295	com/igexin/push/d/c/d:e	Ljava/lang/Object;
    //   151: invokestatic 175	com/igexin/a/a/b/c:b	()Lcom/igexin/a/a/b/c;
    //   154: invokestatic 1340	com/igexin/push/config/SDKUrlConfig:getCmAddress	()Ljava/lang/String;
    //   157: iconst_3
    //   158: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   161: invokevirtual 1343	com/igexin/push/core/f:f	()Lcom/igexin/a/a/b/b;
    //   164: aload_2
    //   165: iconst_0
    //   166: invokevirtual 1346	com/igexin/a/a/b/c:a	(Ljava/lang/String;ILcom/igexin/a/a/b/b;Ljava/lang/Object;Z)Lcom/igexin/a/a/b/e;
    //   169: pop
    //   170: ldc_w 1348
    //   173: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   176: return
    //   177: astore_1
    //   178: return
    //   179: astore_1
    //   180: goto -82 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	183	0	this	e
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
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramString3 = new Intent("com.igexin.sdk.action.execute");
    paramString3.putExtra("taskid", paramString1);
    paramString3.putExtra("messageid", paramString2);
    paramString3.putExtra("appid", com.igexin.push.core.g.a);
    paramString3.putExtra("pkgname", com.igexin.push.core.g.e);
    com.igexin.push.core.f.a().a(paramString3);
  }
  
  @TargetApi(12)
  public void a(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT >= 12) {
      localIntent.addFlags(32);
    }
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10006);
    localBundle.putString("appid", paramString1);
    localBundle.putString("taskid", paramString2);
    localBundle.putString("actionid", paramString3);
    localBundle.putString("result", paramString4);
    localBundle.putLong("timestamp", paramLong);
    localIntent.putExtras(localBundle);
    com.igexin.push.core.f.a().a(localIntent);
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    if ((paramBoolean) && (TextUtils.isEmpty(com.igexin.push.core.g.t))) {}
    long l;
    do
    {
      do
      {
        return;
        l = System.currentTimeMillis();
      } while (l - com.igexin.push.core.g.S <= 5000L);
      String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(l));
      if (!str.equals(com.igexin.push.core.g.R))
      {
        com.igexin.push.core.b.f.a().d(str);
        com.igexin.push.core.b.f.a().a(0);
      }
    } while (com.igexin.push.core.g.T >= 100);
    com.igexin.push.core.g.S = l;
    com.igexin.push.core.b.f.a().a(com.igexin.push.core.g.T + 1);
    com.igexin.a.a.b.c.b().a(new com.igexin.push.f.a.e(new com.igexin.push.core.c.i(SDKUrlConfig.getAmpServiceUrl(), paramString, paramBoolean)), false, true);
  }
  
  public void a(String paramString, byte[] paramArrayOfByte)
  {
    Object localObject;
    long l;
    if (com.igexin.push.core.g.t != null)
    {
      localObject = new JSONObject();
      l = System.currentTimeMillis();
    }
    try
    {
      ((JSONObject)localObject).put("action", "sendmessage");
      ((JSONObject)localObject).put("id", String.valueOf(l));
      ((JSONObject)localObject).put("cid", com.igexin.push.core.g.t);
      ((JSONObject)localObject).put("appid", com.igexin.push.core.g.a);
      ((JSONObject)localObject).put("taskid", paramString);
      localObject = ((JSONObject)localObject).toString();
      com.igexin.push.d.c.d localD = new com.igexin.push.d.c.d();
      localD.a();
      localD.a = ((int)l);
      localD.d = com.igexin.push.core.g.t;
      localD.e = localObject;
      localD.f = paramArrayOfByte;
      localD.g = com.igexin.push.core.g.t;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.t, localD);
      if ((paramString != null) && (paramString.startsWith("4T5@S_"))) {
        com.igexin.a.a.c.a.b("CoreAction sending lbs report message : " + (String)localObject);
      }
      return;
    }
    catch (Exception paramString)
    {
      com.igexin.a.a.c.a.b("CoreAction" + paramString.toString());
    }
  }
  
  public void a(boolean paramBoolean) {}
  
  /* Error */
  public void a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 324	org/json/JSONObject
    //   5: dup
    //   6: new 296	java/lang/String
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 332	java/lang/String:<init>	([B)V
    //   14: invokespecial 334	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   17: astore_1
    //   18: new 210	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 1404
    //   28: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_1
    //   32: invokevirtual 1407	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   35: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   41: aload_1
    //   42: ldc_w 1283
    //   45: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   48: ifeq +1199 -> 1247
    //   51: ldc_w 1267
    //   54: aload_1
    //   55: ldc_w 1283
    //   58: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   61: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   64: ifeq +1183 -> 1247
    //   67: invokestatic 232	java/lang/System:currentTimeMillis	()J
    //   70: lstore_3
    //   71: invokestatic 610	com/igexin/push/core/b/f:a	()Lcom/igexin/push/core/b/f;
    //   74: lload_3
    //   75: invokevirtual 1410	com/igexin/push/core/b/f:d	(J)Z
    //   78: pop
    //   79: aload_1
    //   80: ldc_w 1412
    //   83: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   86: ifeq +13 -> 99
    //   89: aload_1
    //   90: ldc_w 1412
    //   93: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   96: putstatic 1415	com/igexin/push/config/m:F	Ljava/lang/String;
    //   99: aload_1
    //   100: ldc_w 1417
    //   103: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   106: ifeq +1141 -> 1247
    //   109: new 324	org/json/JSONObject
    //   112: dup
    //   113: aload_1
    //   114: ldc_w 1417
    //   117: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   120: invokespecial 334	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   123: astore_1
    //   124: aload_1
    //   125: ldc_w 1419
    //   128: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   131: ifeq +45 -> 176
    //   134: aload_1
    //   135: ldc_w 1419
    //   138: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   141: astore 5
    //   143: aload 5
    //   145: ldc_w 1421
    //   148: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   151: ifne +14 -> 165
    //   154: aload 5
    //   156: ldc_w 1423
    //   159: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   162: ifeq +14 -> 176
    //   165: aload 5
    //   167: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   170: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   173: putstatic 1433	com/igexin/push/config/m:h	Z
    //   176: aload_1
    //   177: ldc_w 1435
    //   180: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   183: ifeq +45 -> 228
    //   186: aload_1
    //   187: ldc_w 1435
    //   190: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   193: astore 5
    //   195: aload 5
    //   197: ldc_w 1421
    //   200: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   203: ifne +14 -> 217
    //   206: aload 5
    //   208: ldc_w 1423
    //   211: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   214: ifeq +14 -> 228
    //   217: aload 5
    //   219: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   222: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   225: putstatic 1162	com/igexin/push/config/m:i	Z
    //   228: aload_1
    //   229: ldc_w 1437
    //   232: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   235: ifeq +45 -> 280
    //   238: aload_1
    //   239: ldc_w 1437
    //   242: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   245: astore 5
    //   247: aload 5
    //   249: ldc_w 1421
    //   252: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   255: ifne +14 -> 269
    //   258: aload 5
    //   260: ldc_w 1423
    //   263: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   266: ifeq +14 -> 280
    //   269: aload 5
    //   271: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   274: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   277: putstatic 1439	com/igexin/push/config/m:g	Z
    //   280: aload_1
    //   281: ldc_w 1441
    //   284: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   287: ifeq +45 -> 332
    //   290: aload_1
    //   291: ldc_w 1441
    //   294: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   297: astore 5
    //   299: aload 5
    //   301: ldc_w 1421
    //   304: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   307: ifne +14 -> 321
    //   310: aload 5
    //   312: ldc_w 1423
    //   315: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   318: ifeq +14 -> 332
    //   321: aload 5
    //   323: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   326: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   329: putstatic 1442	com/igexin/push/config/m:n	Z
    //   332: aload_1
    //   333: ldc_w 1444
    //   336: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   339: ifeq +45 -> 384
    //   342: aload_1
    //   343: ldc_w 1444
    //   346: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   349: astore 5
    //   351: aload 5
    //   353: ldc_w 1421
    //   356: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   359: ifne +14 -> 373
    //   362: aload 5
    //   364: ldc_w 1423
    //   367: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   370: ifeq +14 -> 384
    //   373: aload 5
    //   375: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   378: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   381: putstatic 1446	com/igexin/push/config/m:f	Z
    //   384: aload_1
    //   385: ldc_w 1448
    //   388: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   391: ifeq +67 -> 458
    //   394: aload_1
    //   395: ldc_w 1448
    //   398: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   401: astore 5
    //   403: aload 5
    //   405: ldc_w 1421
    //   408: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   411: ifne +14 -> 425
    //   414: aload 5
    //   416: ldc_w 1423
    //   419: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   422: ifeq +36 -> 458
    //   425: aload 5
    //   427: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   430: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   433: putstatic 1148	com/igexin/push/config/m:k	Z
    //   436: getstatic 1148	com/igexin/push/config/m:k	Z
    //   439: ifne +19 -> 458
    //   442: getstatic 1055	com/igexin/push/config/m:b	I
    //   445: ifeq +13 -> 458
    //   448: aload_0
    //   449: bipush 12
    //   451: iconst_0
    //   452: ldc_w 1450
    //   455: invokevirtual 1157	com/igexin/push/core/a/e:a	(IILjava/lang/String;)V
    //   458: aload_1
    //   459: ldc_w 1452
    //   462: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   465: ifeq +20 -> 485
    //   468: aload_1
    //   469: ldc_w 1452
    //   472: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   475: astore 5
    //   477: aload 5
    //   479: invokestatic 881	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   482: putstatic 1454	com/igexin/push/config/m:o	J
    //   485: aload_1
    //   486: ldc_w 1456
    //   489: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   492: ifeq +45 -> 537
    //   495: aload_1
    //   496: ldc_w 1456
    //   499: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   502: astore 5
    //   504: aload 5
    //   506: ldc_w 1421
    //   509: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   512: ifne +14 -> 526
    //   515: aload 5
    //   517: ldc_w 1423
    //   520: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   523: ifeq +14 -> 537
    //   526: aload 5
    //   528: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   531: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   534: putstatic 1139	com/igexin/push/config/m:j	Z
    //   537: aload_1
    //   538: ldc_w 1458
    //   541: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   544: ifeq +45 -> 589
    //   547: aload_1
    //   548: ldc_w 1458
    //   551: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   554: astore 5
    //   556: aload 5
    //   558: ldc_w 1421
    //   561: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   564: ifne +14 -> 578
    //   567: aload 5
    //   569: ldc_w 1423
    //   572: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   575: ifeq +14 -> 589
    //   578: aload 5
    //   580: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   583: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   586: putstatic 1183	com/igexin/push/config/m:l	Z
    //   589: aload_1
    //   590: ldc_w 1460
    //   593: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   596: ifeq +45 -> 641
    //   599: aload_1
    //   600: ldc_w 1460
    //   603: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   606: astore 5
    //   608: aload 5
    //   610: ldc_w 1421
    //   613: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   616: ifne +14 -> 630
    //   619: aload 5
    //   621: ldc_w 1423
    //   624: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   627: ifeq +14 -> 641
    //   630: aload 5
    //   632: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   635: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   638: putstatic 1191	com/igexin/push/config/m:m	Z
    //   641: aload_1
    //   642: ldc_w 1462
    //   645: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   648: ifeq +45 -> 693
    //   651: aload_1
    //   652: ldc_w 1462
    //   655: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   658: astore 5
    //   660: aload 5
    //   662: ldc_w 1421
    //   665: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   668: ifne +14 -> 682
    //   671: aload 5
    //   673: ldc_w 1423
    //   676: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   679: ifeq +14 -> 693
    //   682: aload 5
    //   684: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   687: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   690: putstatic 1463	com/igexin/push/config/m:p	Z
    //   693: aload_1
    //   694: ldc_w 1465
    //   697: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   700: ifeq +54 -> 754
    //   703: aload_1
    //   704: ldc_w 1465
    //   707: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   710: astore 5
    //   712: aload 5
    //   714: ldc_w 1421
    //   717: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   720: ifne +14 -> 734
    //   723: aload 5
    //   725: ldc_w 1423
    //   728: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   731: ifeq +23 -> 754
    //   734: aload 5
    //   736: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   739: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   742: putstatic 1468	com/igexin/push/config/m:w	Z
    //   745: aload_0
    //   746: aload 5
    //   748: invokevirtual 803	java/lang/String:getBytes	()[B
    //   751: invokespecial 1470	com/igexin/push/core/a/e:b	([B)V
    //   754: aload_1
    //   755: ldc_w 1472
    //   758: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   761: ifeq +45 -> 806
    //   764: aload_1
    //   765: ldc_w 1472
    //   768: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   771: astore 5
    //   773: aload 5
    //   775: ldc_w 1421
    //   778: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   781: ifne +14 -> 795
    //   784: aload 5
    //   786: ldc_w 1423
    //   789: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   792: ifeq +14 -> 806
    //   795: aload 5
    //   797: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   800: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   803: putstatic 1475	com/igexin/push/config/m:x	Z
    //   806: aload_1
    //   807: ldc_w 1477
    //   810: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   813: ifeq +45 -> 858
    //   816: aload_1
    //   817: ldc_w 1477
    //   820: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   823: astore 5
    //   825: aload 5
    //   827: ldc_w 1421
    //   830: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   833: ifne +14 -> 847
    //   836: aload 5
    //   838: ldc_w 1423
    //   841: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   844: ifeq +14 -> 858
    //   847: aload 5
    //   849: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   852: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   855: putstatic 1480	com/igexin/push/config/m:q	Z
    //   858: aload_1
    //   859: ldc_w 1482
    //   862: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   865: ifeq +45 -> 910
    //   868: aload_1
    //   869: ldc_w 1482
    //   872: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   875: astore 5
    //   877: aload 5
    //   879: ldc_w 1421
    //   882: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   885: ifne +14 -> 899
    //   888: aload 5
    //   890: ldc_w 1423
    //   893: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   896: ifeq +14 -> 910
    //   899: aload 5
    //   901: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   904: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   907: putstatic 1200	com/igexin/push/config/m:r	Z
    //   910: aload_1
    //   911: ldc_w 1484
    //   914: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   917: ifeq +13 -> 930
    //   920: aload_1
    //   921: ldc_w 1484
    //   924: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   927: putstatic 1485	com/igexin/push/config/m:t	Ljava/lang/String;
    //   930: aload_1
    //   931: ldc_w 1487
    //   934: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   937: ifeq +13 -> 950
    //   940: aload_1
    //   941: ldc_w 1487
    //   944: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   947: putstatic 1488	com/igexin/push/config/m:u	Ljava/lang/String;
    //   950: aload_1
    //   951: ldc_w 1490
    //   954: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   957: ifeq +45 -> 1002
    //   960: aload_1
    //   961: ldc_w 1490
    //   964: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   967: astore 5
    //   969: aload 5
    //   971: ldc_w 1421
    //   974: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   977: ifne +14 -> 991
    //   980: aload 5
    //   982: ldc_w 1423
    //   985: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   988: ifeq +14 -> 1002
    //   991: aload 5
    //   993: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   996: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   999: putstatic 1493	com/igexin/push/config/m:v	Z
    //   1002: aload_1
    //   1003: ldc_w 1495
    //   1006: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1009: ifeq +20 -> 1029
    //   1012: aload_1
    //   1013: ldc_w 1495
    //   1016: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1019: astore 5
    //   1021: aload 5
    //   1023: invokestatic 881	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   1026: putstatic 1498	com/igexin/push/config/m:y	J
    //   1029: aload_1
    //   1030: ldc_w 1500
    //   1033: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1036: ifeq +20 -> 1056
    //   1039: aload_1
    //   1040: ldc_w 1500
    //   1043: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1046: astore 5
    //   1048: aload 5
    //   1050: invokestatic 1503	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1053: putstatic 1505	com/igexin/push/config/m:E	I
    //   1056: aload_1
    //   1057: ldc_w 1507
    //   1060: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1063: ifeq +130 -> 1193
    //   1066: aload_1
    //   1067: ldc_w 1507
    //   1070: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1073: astore 5
    //   1075: aload 5
    //   1077: ifnull +116 -> 1193
    //   1080: ldc_w 1130
    //   1083: aload 5
    //   1085: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1088: ifne +105 -> 1193
    //   1091: new 324	org/json/JSONObject
    //   1094: dup
    //   1095: aload 5
    //   1097: invokespecial 334	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   1100: astore 5
    //   1102: aload 5
    //   1104: ldc_w 846
    //   1107: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1110: ifeq +83 -> 1193
    //   1113: aload 5
    //   1115: ldc_w 846
    //   1118: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1121: astore 6
    //   1123: getstatic 1511	com/igexin/push/config/m:s	Lcom/igexin/push/core/bean/g;
    //   1126: ifnull +149 -> 1275
    //   1129: aload 6
    //   1131: getstatic 1511	com/igexin/push/config/m:s	Lcom/igexin/push/core/bean/g;
    //   1134: invokevirtual 899	com/igexin/push/core/bean/g:a	()Ljava/lang/String;
    //   1137: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1140: ifeq +135 -> 1275
    //   1143: iload_2
    //   1144: ifeq +49 -> 1193
    //   1147: aload_0
    //   1148: aload 5
    //   1150: invokevirtual 1513	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;)Lcom/igexin/push/core/bean/g;
    //   1153: astore 5
    //   1155: aload 5
    //   1157: ifnull +36 -> 1193
    //   1160: new 1515	android/os/Message
    //   1163: dup
    //   1164: invokespecial 1516	android/os/Message:<init>	()V
    //   1167: astore 6
    //   1169: aload 6
    //   1171: getstatic 1518	com/igexin/push/core/a:h	I
    //   1174: putfield 1521	android/os/Message:what	I
    //   1177: aload 6
    //   1179: aload 5
    //   1181: putfield 1524	android/os/Message:obj	Ljava/lang/Object;
    //   1184: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   1187: aload 6
    //   1189: invokevirtual 1527	com/igexin/push/core/f:a	(Landroid/os/Message;)Z
    //   1192: pop
    //   1193: aload_1
    //   1194: ldc_w 1529
    //   1197: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1200: ifeq +41 -> 1241
    //   1203: aload_1
    //   1204: ldc_w 1529
    //   1207: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1210: astore_1
    //   1211: aload_1
    //   1212: ldc_w 1421
    //   1215: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1218: ifne +13 -> 1231
    //   1221: aload_1
    //   1222: ldc_w 1423
    //   1225: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1228: ifeq +13 -> 1241
    //   1231: aload_1
    //   1232: invokestatic 1428	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   1235: invokevirtual 1431	java/lang/Boolean:booleanValue	()Z
    //   1238: putstatic 1532	com/igexin/push/config/m:G	Z
    //   1241: invokestatic 1101	com/igexin/push/config/a:a	()Lcom/igexin/push/config/a;
    //   1244: invokevirtual 1533	com/igexin/push/config/a:f	()V
    //   1247: return
    //   1248: astore_1
    //   1249: aload_0
    //   1250: aload_1
    //   1251: invokevirtual 969	java/lang/Exception:toString	()Ljava/lang/String;
    //   1254: invokevirtual 1534	com/igexin/push/core/a/e:c	(Ljava/lang/String;)V
    //   1257: return
    //   1258: astore_1
    //   1259: return
    //   1260: astore 5
    //   1262: goto -206 -> 1056
    //   1265: astore 5
    //   1267: goto -238 -> 1029
    //   1270: astore 5
    //   1272: goto -787 -> 485
    //   1275: iconst_1
    //   1276: istore_2
    //   1277: goto -134 -> 1143
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1280	0	this	e
    //   0	1280	1	paramArrayOfByte	byte[]
    //   1	1276	2	i	int
    //   70	5	3	l	long
    //   141	1039	5	localObject1	Object
    //   1260	1	5	localException1	Exception
    //   1265	1	5	localException2	Exception
    //   1270	1	5	localException3	Exception
    //   1121	67	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	99	1248	java/lang/Exception
    //   99	165	1248	java/lang/Exception
    //   165	176	1248	java/lang/Exception
    //   176	217	1248	java/lang/Exception
    //   217	228	1248	java/lang/Exception
    //   228	269	1248	java/lang/Exception
    //   269	280	1248	java/lang/Exception
    //   280	321	1248	java/lang/Exception
    //   321	332	1248	java/lang/Exception
    //   332	373	1248	java/lang/Exception
    //   373	384	1248	java/lang/Exception
    //   384	425	1248	java/lang/Exception
    //   425	458	1248	java/lang/Exception
    //   458	477	1248	java/lang/Exception
    //   485	526	1248	java/lang/Exception
    //   526	537	1248	java/lang/Exception
    //   537	578	1248	java/lang/Exception
    //   578	589	1248	java/lang/Exception
    //   589	630	1248	java/lang/Exception
    //   630	641	1248	java/lang/Exception
    //   641	682	1248	java/lang/Exception
    //   682	693	1248	java/lang/Exception
    //   693	734	1248	java/lang/Exception
    //   734	754	1248	java/lang/Exception
    //   754	795	1248	java/lang/Exception
    //   795	806	1248	java/lang/Exception
    //   806	847	1248	java/lang/Exception
    //   847	858	1248	java/lang/Exception
    //   858	899	1248	java/lang/Exception
    //   899	910	1248	java/lang/Exception
    //   910	930	1248	java/lang/Exception
    //   930	950	1248	java/lang/Exception
    //   950	991	1248	java/lang/Exception
    //   991	1002	1248	java/lang/Exception
    //   1002	1021	1248	java/lang/Exception
    //   1029	1048	1248	java/lang/Exception
    //   1056	1075	1248	java/lang/Exception
    //   1080	1143	1248	java/lang/Exception
    //   1147	1155	1248	java/lang/Exception
    //   1160	1193	1248	java/lang/Exception
    //   1193	1231	1248	java/lang/Exception
    //   1231	1241	1248	java/lang/Exception
    //   1241	1247	1248	java/lang/Exception
    //   2	99	1258	java/lang/Throwable
    //   99	165	1258	java/lang/Throwable
    //   165	176	1258	java/lang/Throwable
    //   176	217	1258	java/lang/Throwable
    //   217	228	1258	java/lang/Throwable
    //   228	269	1258	java/lang/Throwable
    //   269	280	1258	java/lang/Throwable
    //   280	321	1258	java/lang/Throwable
    //   321	332	1258	java/lang/Throwable
    //   332	373	1258	java/lang/Throwable
    //   373	384	1258	java/lang/Throwable
    //   384	425	1258	java/lang/Throwable
    //   425	458	1258	java/lang/Throwable
    //   458	477	1258	java/lang/Throwable
    //   477	485	1258	java/lang/Throwable
    //   485	526	1258	java/lang/Throwable
    //   526	537	1258	java/lang/Throwable
    //   537	578	1258	java/lang/Throwable
    //   578	589	1258	java/lang/Throwable
    //   589	630	1258	java/lang/Throwable
    //   630	641	1258	java/lang/Throwable
    //   641	682	1258	java/lang/Throwable
    //   682	693	1258	java/lang/Throwable
    //   693	734	1258	java/lang/Throwable
    //   734	754	1258	java/lang/Throwable
    //   754	795	1258	java/lang/Throwable
    //   795	806	1258	java/lang/Throwable
    //   806	847	1258	java/lang/Throwable
    //   847	858	1258	java/lang/Throwable
    //   858	899	1258	java/lang/Throwable
    //   899	910	1258	java/lang/Throwable
    //   910	930	1258	java/lang/Throwable
    //   930	950	1258	java/lang/Throwable
    //   950	991	1258	java/lang/Throwable
    //   991	1002	1258	java/lang/Throwable
    //   1002	1021	1258	java/lang/Throwable
    //   1021	1029	1258	java/lang/Throwable
    //   1029	1048	1258	java/lang/Throwable
    //   1048	1056	1258	java/lang/Throwable
    //   1056	1075	1258	java/lang/Throwable
    //   1080	1143	1258	java/lang/Throwable
    //   1147	1155	1258	java/lang/Throwable
    //   1160	1193	1258	java/lang/Throwable
    //   1193	1231	1258	java/lang/Throwable
    //   1231	1241	1258	java/lang/Throwable
    //   1241	1247	1258	java/lang/Throwable
    //   1048	1056	1260	java/lang/Exception
    //   1021	1029	1265	java/lang/Exception
    //   477	485	1270	java/lang/Exception
  }
  
  public boolean a(long paramLong)
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
  
  public boolean a(com.igexin.a.a.d.d paramD)
  {
    return false;
  }
  
  public boolean a(com.igexin.push.d.c.e paramE)
  {
    if (paramE != null)
    {
      a localA = (a)a.get(Integer.valueOf(paramE.i));
      if (((paramE instanceof com.igexin.push.d.c.j)) || ((paramE instanceof com.igexin.push.d.c.m)) || ((paramE instanceof o)) || ((paramE instanceof com.igexin.push.d.c.q)) || ((paramE instanceof com.igexin.push.d.c.h)))
      {
        com.igexin.a.a.c.a.b("CoreAction|receive : " + paramE.getClass().getName() + " resp ~~~~");
        com.igexin.a.a.b.a.a.d.a().a(paramE.getClass().getName());
      }
      if (((paramE instanceof com.igexin.push.d.c.m)) || ((paramE instanceof o)) || ((paramE instanceof com.igexin.push.d.c.q)))
      {
        com.igexin.push.core.g.E = 0L;
        com.igexin.push.c.i.a().e().a();
      }
      if (localA != null) {
        localA.a(paramE);
      }
      com.igexin.push.f.b.c.g().h();
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
        com.igexin.a.a.b.a.a.d.a().a(false);
        return false;
      }
      if ((paramObject instanceof com.igexin.push.d.b.b))
      {
        com.igexin.a.a.b.a.a.d.a().a(((com.igexin.push.d.b.b)paramObject).a());
        return false;
      }
      if ((paramObject instanceof com.igexin.push.d.b.d))
      {
        com.igexin.a.a.c.a.b("CoreAction doHandleFilter TcpExceptionNotifyType");
        C();
        return false;
      }
    } while (!(paramObject instanceof com.igexin.push.d.b.c));
    f(((com.igexin.push.d.b.c)paramObject).a());
    return false;
  }
  
  public boolean a(String paramString1, String paramString2, String paramString3)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("taskid", paramString1);
    localBundle.putString("messageid", paramString2);
    localBundle.putString("actionid", paramString3);
    paramString1 = new Message();
    paramString1.what = com.igexin.push.core.a.g;
    paramString1.obj = localBundle;
    return com.igexin.push.core.f.a().a(paramString1);
  }
  
  public boolean a(JSONObject paramJSONObject, PushTaskBean paramPushTaskBean)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        localJSONArray = paramJSONObject.getJSONArray("action_chains");
        i = 0;
        if (i >= localJSONArray.length()) {
          break label361;
        }
        paramJSONObject = ((JSONObject)localJSONArray.get(i)).getString("type");
        if (paramJSONObject == null) {
          break label354;
        }
        localObject = com.igexin.push.extension.a.a().c().iterator();
        if (!((Iterator)localObject).hasNext()) {
          break label348;
        }
        if (!((IPushExtension)((Iterator)localObject).next()).isActionSupported(paramJSONObject)) {
          continue;
        }
        j = 1;
        if (j != 0) {
          break label354;
        }
        com.igexin.a.a.c.a.b("CoreAction|extension not suport type = " + paramJSONObject);
        if (b.get(paramJSONObject) != null) {
          break label354;
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
        com.igexin.a.a.c.a.b("CoreAction|" + paramJSONObject.toString());
        paramPushTaskBean.setActionChains(localArrayList);
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
            break label345;
          }
          localObject = (com.igexin.push.core.a.a.a)b.get(str);
          if (localObject == null) {
            break label345;
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
            com.igexin.a.a.c.a.b("CoreAction|action chains can't parse, throw whole ++++++");
            return false;
          }
          localArrayList.add(paramJSONObject);
        }
        i += 1;
      }
      label345:
      continue;
      label348:
      int j = 0;
      continue;
      label354:
      i += 1;
      continue;
      label361:
      int i = 0;
    }
  }
  
  /* Error */
  public boolean a(JSONObject paramJSONObject, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 1081
    //   4: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   7: ifeq +620 -> 627
    //   10: aload_1
    //   11: ldc_w 1081
    //   14: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   17: ldc_w 1647
    //   20: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   23: ifeq +604 -> 627
    //   26: aload_1
    //   27: ldc_w 336
    //   30: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   33: astore 9
    //   35: aload_1
    //   36: ldc_w 342
    //   39: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   42: astore 7
    //   44: aload_1
    //   45: ldc_w 344
    //   48: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   51: astore 4
    //   53: aload_1
    //   54: ldc_w 346
    //   57: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   60: astore 5
    //   62: aload_1
    //   63: ldc_w 348
    //   66: invokevirtual 340	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   69: astore 10
    //   71: aload_1
    //   72: ldc_w 350
    //   75: invokevirtual 354	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   78: astore 8
    //   80: new 210	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   87: ldc_w 1649
    //   90: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: aload 5
    //   95: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: ldc_w 992
    //   101: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: aload 4
    //   106: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: ldc_w 992
    //   112: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: aload 7
    //   117: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: ldc_w 992
    //   123: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: iload_3
    //   127: invokevirtual 220	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   130: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   136: aload 7
    //   138: ifnull +645 -> 783
    //   141: aload 9
    //   143: ifnull +640 -> 783
    //   146: aload 4
    //   148: ifnull +635 -> 783
    //   151: aload 5
    //   153: ifnull +630 -> 783
    //   156: aload 8
    //   158: ifnull +625 -> 783
    //   161: aload 7
    //   163: getstatic 1059	com/igexin/push/core/g:a	Ljava/lang/String;
    //   166: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   169: ifeq +614 -> 783
    //   172: new 361	com/igexin/push/core/bean/PushTaskBean
    //   175: dup
    //   176: invokespecial 362	com/igexin/push/core/bean/PushTaskBean:<init>	()V
    //   179: astore 6
    //   181: aload 6
    //   183: aload 7
    //   185: invokevirtual 365	com/igexin/push/core/bean/PushTaskBean:setAppid	(Ljava/lang/String;)V
    //   188: aload 6
    //   190: aload 4
    //   192: invokevirtual 368	com/igexin/push/core/bean/PushTaskBean:setMessageId	(Ljava/lang/String;)V
    //   195: aload 6
    //   197: aload 5
    //   199: invokevirtual 371	com/igexin/push/core/bean/PushTaskBean:setTaskId	(Ljava/lang/String;)V
    //   202: aload 6
    //   204: aload 9
    //   206: invokevirtual 374	com/igexin/push/core/bean/PushTaskBean:setId	(Ljava/lang/String;)V
    //   209: aload 6
    //   211: aload 10
    //   213: invokevirtual 377	com/igexin/push/core/bean/PushTaskBean:setAppKey	(Ljava/lang/String;)V
    //   216: aload 6
    //   218: iconst_1
    //   219: invokevirtual 381	com/igexin/push/core/bean/PushTaskBean:setCurrentActionid	(I)V
    //   222: aload_1
    //   223: ldc_w 393
    //   226: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   229: ifeq +15 -> 244
    //   232: aload 6
    //   234: aload_1
    //   235: ldc_w 393
    //   238: invokevirtual 400	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   241: invokevirtual 403	com/igexin/push/core/bean/PushTaskBean:setCDNType	(Z)V
    //   244: invokestatic 356	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   247: aload 5
    //   249: aload 4
    //   251: invokevirtual 359	com/igexin/push/core/a/e:c	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   254: astore 9
    //   256: iload_3
    //   257: ifeq +43 -> 300
    //   260: invokestatic 356	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   263: aload 6
    //   265: ldc_w 300
    //   268: invokevirtual 1214	com/igexin/push/core/a/e:a	(Lcom/igexin/push/core/bean/PushTaskBean;Ljava/lang/String;)V
    //   271: invokestatic 356	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   274: invokestatic 232	java/lang/System:currentTimeMillis	()J
    //   277: invokevirtual 1651	com/igexin/push/core/a/e:a	(J)Z
    //   280: ifeq +5 -> 285
    //   283: iconst_1
    //   284: ireturn
    //   285: aload_1
    //   286: invokestatic 1654	com/igexin/push/util/a:a	(Lorg/json/JSONObject;)Z
    //   289: ifeq +11 -> 300
    //   292: ldc_w 1656
    //   295: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   298: iconst_1
    //   299: ireturn
    //   300: new 431	android/content/ContentValues
    //   303: dup
    //   304: invokespecial 432	android/content/ContentValues:<init>	()V
    //   307: astore 10
    //   309: aload 10
    //   311: ldc_w 344
    //   314: aload 4
    //   316: invokevirtual 743	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   319: aload 10
    //   321: ldc_w 346
    //   324: aload 5
    //   326: invokevirtual 743	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   329: aload 10
    //   331: ldc_w 342
    //   334: aload 7
    //   336: invokevirtual 743	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   339: aload 10
    //   341: ldc_w 745
    //   344: new 210	java/lang/StringBuilder
    //   347: dup
    //   348: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   351: ldc_w 1658
    //   354: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: aload 9
    //   359: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   365: invokevirtual 743	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   368: aload 10
    //   370: ldc_w 322
    //   373: aload_1
    //   374: invokevirtual 419	org/json/JSONObject:toString	()Ljava/lang/String;
    //   377: invokevirtual 803	java/lang/String:getBytes	()[B
    //   380: invokestatic 1660	com/igexin/a/b/a:b	([B)[B
    //   383: invokevirtual 1662	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   386: aload 10
    //   388: ldc_w 747
    //   391: invokestatic 232	java/lang/System:currentTimeMillis	()J
    //   394: invokestatic 752	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   397: invokevirtual 755	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   400: aload_2
    //   401: ifnull +18 -> 419
    //   404: aload 10
    //   406: ldc_w 312
    //   409: aload_2
    //   410: invokevirtual 1662	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   413: aload 6
    //   415: aload_2
    //   416: invokevirtual 391	com/igexin/push/core/bean/PushTaskBean:setMsgExtra	([B)V
    //   419: aload 8
    //   421: invokevirtual 413	org/json/JSONArray:length	()I
    //   424: ifle +23 -> 447
    //   427: invokestatic 356	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   430: aload_1
    //   431: aload 6
    //   433: invokevirtual 416	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)Z
    //   436: ifne +11 -> 447
    //   439: ldc_w 1664
    //   442: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   445: iconst_1
    //   446: ireturn
    //   447: iload_3
    //   448: ifeq +294 -> 742
    //   451: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   454: invokevirtual 292	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   457: ldc_w 294
    //   460: iconst_1
    //   461: anewarray 296	java/lang/String
    //   464: dup
    //   465: iconst_0
    //   466: ldc_w 346
    //   469: aastore
    //   470: iconst_1
    //   471: anewarray 296	java/lang/String
    //   474: dup
    //   475: iconst_0
    //   476: aload 5
    //   478: aastore
    //   479: aconst_null
    //   480: aconst_null
    //   481: invokevirtual 305	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   484: astore_2
    //   485: aload_2
    //   486: ifnull +131 -> 617
    //   489: new 210	java/lang/StringBuilder
    //   492: dup
    //   493: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   496: ldc_w 1666
    //   499: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: aload 5
    //   504: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: ldc_w 1668
    //   510: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   513: aload_2
    //   514: invokeinterface 758 1 0
    //   519: invokevirtual 1032	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   522: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   525: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   528: aload_2
    //   529: invokeinterface 758 1 0
    //   534: ifne +196 -> 730
    //   537: aload_1
    //   538: ldc_w 405
    //   541: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   544: ifeq +85 -> 629
    //   547: aload_0
    //   548: aload_1
    //   549: aload 6
    //   551: invokespecial 408	com/igexin/push/core/a/e:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   554: aload 6
    //   556: getstatic 1670	com/igexin/push/core/a:k	I
    //   559: invokevirtual 388	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   562: aload 10
    //   564: ldc_w 298
    //   567: getstatic 1670	com/igexin/push/core/a:k	I
    //   570: invokestatic 28	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   573: invokevirtual 435	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   576: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   579: invokevirtual 292	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   582: ldc_w 294
    //   585: aload 10
    //   587: invokevirtual 761	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   590: getstatic 283	com/igexin/push/core/g:ah	Ljava/util/Map;
    //   593: aload 9
    //   595: aload 6
    //   597: invokeinterface 37 3 0
    //   602: pop
    //   603: aload_1
    //   604: ldc_w 405
    //   607: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   610: ifeq +88 -> 698
    //   613: aload_0
    //   614: invokevirtual 250	com/igexin/push/core/a/e:t	()V
    //   617: aload_2
    //   618: ifnull +9 -> 627
    //   621: aload_2
    //   622: invokeinterface 422 1 0
    //   627: iconst_1
    //   628: ireturn
    //   629: aload 6
    //   631: getstatic 1672	com/igexin/push/core/a:l	I
    //   634: invokevirtual 388	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   637: aload 10
    //   639: ldc_w 298
    //   642: getstatic 1672	com/igexin/push/core/a:l	I
    //   645: invokestatic 28	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   648: invokevirtual 435	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   651: goto -75 -> 576
    //   654: astore_1
    //   655: aload_2
    //   656: ifnull -29 -> 627
    //   659: aload_2
    //   660: invokeinterface 422 1 0
    //   665: goto -38 -> 627
    //   668: astore_1
    //   669: new 210	java/lang/StringBuilder
    //   672: dup
    //   673: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   676: ldc_w 1674
    //   679: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   682: aload_1
    //   683: invokevirtual 969	java/lang/Exception:toString	()Ljava/lang/String;
    //   686: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   689: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   692: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   695: goto -68 -> 627
    //   698: invokestatic 356	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   701: aload 5
    //   703: aload 4
    //   705: getstatic 1059	com/igexin/push/core/g:a	Ljava/lang/String;
    //   708: getstatic 763	com/igexin/push/core/g:e	Ljava/lang/String;
    //   711: invokevirtual 1676	com/igexin/push/core/a/e:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   714: goto -97 -> 617
    //   717: astore_1
    //   718: aload_2
    //   719: ifnull +9 -> 728
    //   722: aload_2
    //   723: invokeinterface 422 1 0
    //   728: aload_1
    //   729: athrow
    //   730: aload_2
    //   731: ifnull +73 -> 804
    //   734: aload_2
    //   735: invokeinterface 422 1 0
    //   740: iconst_1
    //   741: ireturn
    //   742: aload_1
    //   743: ldc_w 405
    //   746: invokevirtual 397	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   749: ifeq +10 -> 759
    //   752: aload_0
    //   753: aload_1
    //   754: aload 6
    //   756: invokespecial 408	com/igexin/push/core/a/e:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   759: aload 6
    //   761: getstatic 1672	com/igexin/push/core/a:l	I
    //   764: invokevirtual 388	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   767: getstatic 283	com/igexin/push/core/g:ah	Ljava/util/Map;
    //   770: aload 9
    //   772: aload 6
    //   774: invokeinterface 37 3 0
    //   779: pop
    //   780: goto -153 -> 627
    //   783: ldc_w 1678
    //   786: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   789: goto -162 -> 627
    //   792: astore_1
    //   793: aconst_null
    //   794: astore_2
    //   795: goto -77 -> 718
    //   798: astore_1
    //   799: aconst_null
    //   800: astore_2
    //   801: goto -146 -> 655
    //   804: iconst_1
    //   805: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	806	0	this	e
    //   0	806	1	paramJSONObject	JSONObject
    //   0	806	2	paramArrayOfByte	byte[]
    //   0	806	3	paramBoolean	boolean
    //   51	653	4	str1	String
    //   60	642	5	str2	String
    //   179	594	6	localPushTaskBean	PushTaskBean
    //   42	293	7	str3	String
    //   78	342	8	localJSONArray	JSONArray
    //   33	738	9	str4	String
    //   69	569	10	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   489	576	654	java/lang/Exception
    //   576	617	654	java/lang/Exception
    //   629	651	654	java/lang/Exception
    //   698	714	654	java/lang/Exception
    //   0	136	668	java/lang/Exception
    //   161	244	668	java/lang/Exception
    //   244	256	668	java/lang/Exception
    //   260	283	668	java/lang/Exception
    //   285	298	668	java/lang/Exception
    //   300	400	668	java/lang/Exception
    //   404	419	668	java/lang/Exception
    //   419	445	668	java/lang/Exception
    //   621	627	668	java/lang/Exception
    //   659	665	668	java/lang/Exception
    //   722	728	668	java/lang/Exception
    //   728	730	668	java/lang/Exception
    //   734	740	668	java/lang/Exception
    //   742	759	668	java/lang/Exception
    //   759	780	668	java/lang/Exception
    //   783	789	668	java/lang/Exception
    //   489	576	717	finally
    //   576	617	717	finally
    //   629	651	717	finally
    //   698	714	717	finally
    //   451	485	792	finally
    //   451	485	798	java/lang/Exception
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
    if ((paramIntent == null) || (!paramIntent.hasExtra("isSlave")) || (!paramIntent.getBooleanExtra("isSlave", false))) {}
    for (;;)
    {
      return;
      com.igexin.push.core.f.a().a(true);
      if (paramIntent.hasExtra("op_app")) {}
      for (com.igexin.push.core.g.D = paramIntent.getStringExtra("op_app"); com.igexin.push.core.g.n; com.igexin.push.core.g.D = "")
      {
        l();
        return;
      }
    }
  }
  
  public void b(PushTaskBean paramPushTaskBean, String paramString)
  {
    String str = paramPushTaskBean.getMessageId() + "|" + paramString;
    com.igexin.push.d.c.c localC;
    if (com.igexin.push.core.g.ak.containsKey(str))
    {
      localC = (com.igexin.push.d.c.c)com.igexin.push.core.g.ak.get(str);
      if (localC.c() < 2)
      {
        com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.t, localC);
        localC.a(localC.c() + 1);
        a(localC, paramPushTaskBean, paramString, str);
      }
    }
    for (;;)
    {
      com.igexin.a.a.c.a.b("cdnfeedback|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId() + "|" + paramString);
      return;
      localC = new com.igexin.push.d.c.c();
      long l = System.currentTimeMillis();
      localC.a();
      localC.c = ("FDB" + paramPushTaskBean.getMessageId() + "|" + paramPushTaskBean.getTaskId() + "|" + paramString + "|" + "ok" + "|" + l);
      localC.d = com.igexin.push.core.g.t;
      localC.a = ((int)l);
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.t, localC);
      a(localC, paramPushTaskBean, paramString, str);
    }
  }
  
  public void b(String paramString)
  {
    long l = System.currentTimeMillis();
    if (l - com.igexin.push.core.g.S > 5000L)
    {
      String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(l));
      if (!str.equals(com.igexin.push.core.g.R))
      {
        com.igexin.push.core.b.f.a().d(str);
        com.igexin.push.core.b.f.a().a(0);
      }
      com.igexin.a.a.c.a.b("-> CoreRuntimeInfo.opAliasTimes:" + com.igexin.push.core.g.T);
      if (com.igexin.push.core.g.T < 100)
      {
        com.igexin.a.a.c.a.b("requestService bindAlias HttpTask url : " + SDKUrlConfig.getAmpServiceUrl());
        com.igexin.push.core.g.S = l;
        com.igexin.push.core.b.f.a().a(com.igexin.push.core.g.T + 1);
        com.igexin.a.a.b.c.b().a(new com.igexin.push.f.a.e(new com.igexin.push.core.c.b(SDKUrlConfig.getAmpServiceUrl(), paramString)), false, true);
      }
    }
  }
  
  public void b(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT >= 12) {
      localIntent.addFlags(32);
    }
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10009);
    localBundle.putString("sn", paramString1);
    localBundle.putString("code", paramString2);
    localIntent.putExtras(localBundle);
    com.igexin.push.core.f.a().a(localIntent);
  }
  
  public void b(boolean paramBoolean)
  {
    com.igexin.a.a.b.c.b().a(new com.igexin.push.d.b.d());
    com.igexin.a.a.b.c.b().c();
    e(paramBoolean);
  }
  
  /* Error */
  public boolean b(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 210	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   7: aload_1
    //   8: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   11: ldc_w 1205
    //   14: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: aload_2
    //   18: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: astore 10
    //   26: getstatic 283	com/igexin/push/core/g:ah	Ljava/util/Map;
    //   29: aload 10
    //   31: invokeinterface 689 2 0
    //   36: checkcast 361	com/igexin/push/core/bean/PushTaskBean
    //   39: astore 6
    //   41: aload 6
    //   43: astore 7
    //   45: aload 6
    //   47: ifnonnull +279 -> 326
    //   50: invokestatic 190	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   53: invokevirtual 292	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   56: ldc_w 294
    //   59: iconst_2
    //   60: anewarray 296	java/lang/String
    //   63: dup
    //   64: iconst_0
    //   65: ldc_w 346
    //   68: aastore
    //   69: dup
    //   70: iconst_1
    //   71: ldc_w 344
    //   74: aastore
    //   75: iconst_2
    //   76: anewarray 296	java/lang/String
    //   79: dup
    //   80: iconst_0
    //   81: aload_1
    //   82: aastore
    //   83: dup
    //   84: iconst_1
    //   85: aload_2
    //   86: aastore
    //   87: aconst_null
    //   88: aconst_null
    //   89: invokevirtual 305	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   92: astore 8
    //   94: aload 8
    //   96: ifnull +25 -> 121
    //   99: aload 8
    //   101: astore 7
    //   103: aload 6
    //   105: astore 9
    //   107: aload 8
    //   109: invokeinterface 758 1 0
    //   114: istore 4
    //   116: iload 4
    //   118: ifgt +17 -> 135
    //   121: aload 8
    //   123: ifnull +10 -> 133
    //   126: aload 8
    //   128: invokeinterface 422 1 0
    //   133: iconst_0
    //   134: ireturn
    //   135: aload 8
    //   137: astore 7
    //   139: aload 6
    //   141: astore 9
    //   143: aload 8
    //   145: invokeinterface 310 1 0
    //   150: ifeq +156 -> 306
    //   153: aload 8
    //   155: astore 7
    //   157: aload 6
    //   159: astore 9
    //   161: aload 8
    //   163: aload 8
    //   165: ldc_w 322
    //   168: invokeinterface 1009 2 0
    //   173: invokeinterface 320 2 0
    //   178: astore 11
    //   180: aload 8
    //   182: astore 7
    //   184: aload 6
    //   186: astore 9
    //   188: aload 8
    //   190: aload 8
    //   192: ldc_w 312
    //   195: invokeinterface 1009 2 0
    //   200: invokeinterface 320 2 0
    //   205: astore 12
    //   207: aload 8
    //   209: astore 7
    //   211: aload 6
    //   213: astore 9
    //   215: aload_0
    //   216: new 324	org/json/JSONObject
    //   219: dup
    //   220: new 296	java/lang/String
    //   223: dup
    //   224: aload 11
    //   226: invokestatic 329	com/igexin/a/b/a:c	([B)[B
    //   229: invokespecial 332	java/lang/String:<init>	([B)V
    //   232: invokespecial 334	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   235: aload 12
    //   237: iconst_0
    //   238: invokevirtual 1716	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;[BZ)Z
    //   241: pop
    //   242: aload 8
    //   244: astore 7
    //   246: aload 6
    //   248: astore 9
    //   250: getstatic 283	com/igexin/push/core/g:ah	Ljava/util/Map;
    //   253: new 210	java/lang/StringBuilder
    //   256: dup
    //   257: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   260: aload_1
    //   261: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: ldc_w 1205
    //   267: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: aload_2
    //   271: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   277: invokeinterface 689 2 0
    //   282: checkcast 361	com/igexin/push/core/bean/PushTaskBean
    //   285: astore 6
    //   287: aload 6
    //   289: ifnonnull -154 -> 135
    //   292: aload 8
    //   294: ifnull +10 -> 304
    //   297: aload 8
    //   299: invokeinterface 422 1 0
    //   304: iconst_0
    //   305: ireturn
    //   306: aload 6
    //   308: astore 7
    //   310: aload 8
    //   312: ifnull +14 -> 326
    //   315: aload 8
    //   317: invokeinterface 422 1 0
    //   322: aload 6
    //   324: astore 7
    //   326: aload 7
    //   328: invokevirtual 1719	com/igexin/push/core/bean/PushTaskBean:getExecuteTimes	()I
    //   331: istore 4
    //   333: iload 4
    //   335: bipush 50
    //   337: if_icmplt +121 -> 458
    //   340: getstatic 283	com/igexin/push/core/g:ah	Ljava/util/Map;
    //   343: aload 10
    //   345: invokeinterface 620 2 0
    //   350: pop
    //   351: iconst_1
    //   352: ireturn
    //   353: astore_1
    //   354: aconst_null
    //   355: astore 8
    //   357: aload 8
    //   359: astore 7
    //   361: new 210	java/lang/StringBuilder
    //   364: dup
    //   365: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   368: ldc_w 777
    //   371: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: aload_1
    //   375: invokevirtual 425	java/lang/Throwable:toString	()Ljava/lang/String;
    //   378: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   384: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   387: aload 6
    //   389: astore 7
    //   391: aload 8
    //   393: ifnull -67 -> 326
    //   396: aload 8
    //   398: invokeinterface 422 1 0
    //   403: aload 6
    //   405: astore 7
    //   407: goto -81 -> 326
    //   410: astore_1
    //   411: aconst_null
    //   412: astore 7
    //   414: aload 7
    //   416: ifnull +10 -> 426
    //   419: aload 7
    //   421: invokeinterface 422 1 0
    //   426: aload_1
    //   427: athrow
    //   428: astore_1
    //   429: new 210	java/lang/StringBuilder
    //   432: dup
    //   433: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   436: ldc_w 777
    //   439: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: aload_1
    //   443: invokevirtual 969	java/lang/Exception:toString	()Ljava/lang/String;
    //   446: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   452: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   455: goto -104 -> 351
    //   458: aload 7
    //   460: iload 4
    //   462: iconst_1
    //   463: iadd
    //   464: invokevirtual 1722	com/igexin/push/core/bean/PushTaskBean:setExecuteTimes	(I)V
    //   467: invokestatic 356	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   470: aload 7
    //   472: aload_3
    //   473: invokevirtual 1214	com/igexin/push/core/a/e:a	(Lcom/igexin/push/core/bean/PushTaskBean;Ljava/lang/String;)V
    //   476: aload 7
    //   478: aload_3
    //   479: invokevirtual 1726	com/igexin/push/core/bean/PushTaskBean:getBaseAction	(Ljava/lang/String;)Lcom/igexin/push/core/bean/BaseAction;
    //   482: astore_1
    //   483: aload_1
    //   484: ifnonnull +5 -> 489
    //   487: iconst_0
    //   488: ireturn
    //   489: aload_1
    //   490: invokevirtual 1729	com/igexin/push/core/bean/BaseAction:isSupportExt	()Z
    //   493: ifeq +46 -> 539
    //   496: invokestatic 1615	com/igexin/push/extension/a:a	()Lcom/igexin/push/extension/a;
    //   499: invokevirtual 1618	com/igexin/push/extension/a:c	()Ljava/util/List;
    //   502: invokeinterface 1619 1 0
    //   507: astore_2
    //   508: aload_2
    //   509: invokeinterface 924 1 0
    //   514: ifeq +25 -> 539
    //   517: aload_2
    //   518: invokeinterface 928 1 0
    //   523: checkcast 1621	com/igexin/push/extension/stub/IPushExtension
    //   526: aload 7
    //   528: aload_1
    //   529: invokeinterface 1733 3 0
    //   534: ifeq -26 -> 508
    //   537: iconst_1
    //   538: ireturn
    //   539: getstatic 57	com/igexin/push/core/a/e:b	Ljava/util/Map;
    //   542: aload_1
    //   543: invokevirtual 1736	com/igexin/push/core/bean/BaseAction:getType	()Ljava/lang/String;
    //   546: invokeinterface 689 2 0
    //   551: checkcast 1632	com/igexin/push/core/a/a/a
    //   554: astore_2
    //   555: aload_2
    //   556: ifnull +69 -> 625
    //   559: aload 7
    //   561: invokevirtual 1739	com/igexin/push/core/bean/PushTaskBean:isStop	()Z
    //   564: ifeq +6 -> 570
    //   567: goto +58 -> 625
    //   570: aload_2
    //   571: aload 7
    //   573: aload_1
    //   574: invokeinterface 1741 3 0
    //   579: istore 5
    //   581: iload 5
    //   583: ireturn
    //   584: astore_1
    //   585: new 210	java/lang/StringBuilder
    //   588: dup
    //   589: invokespecial 211	java/lang/StringBuilder:<init>	()V
    //   592: ldc_w 777
    //   595: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   598: aload_1
    //   599: invokevirtual 425	java/lang/Throwable:toString	()Ljava/lang/String;
    //   602: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   605: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   608: invokestatic 160	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   611: iconst_0
    //   612: ireturn
    //   613: astore_1
    //   614: goto -200 -> 414
    //   617: astore_1
    //   618: aload 9
    //   620: astore 6
    //   622: goto -265 -> 357
    //   625: iconst_0
    //   626: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	627	0	this	e
    //   0	627	1	paramString1	String
    //   0	627	2	paramString2	String
    //   0	627	3	paramString3	String
    //   114	350	4	i	int
    //   579	3	5	bool	boolean
    //   39	582	6	localObject1	Object
    //   43	529	7	localObject2	Object
    //   92	305	8	localCursor	android.database.Cursor
    //   105	514	9	localObject3	Object
    //   24	320	10	str	String
    //   178	47	11	arrayOfByte1	byte[]
    //   205	31	12	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   50	94	353	java/lang/Throwable
    //   50	94	410	finally
    //   340	351	428	java/lang/Exception
    //   476	483	584	java/lang/Throwable
    //   489	508	584	java/lang/Throwable
    //   508	537	584	java/lang/Throwable
    //   539	555	584	java/lang/Throwable
    //   559	567	584	java/lang/Throwable
    //   570	581	584	java/lang/Throwable
    //   107	116	613	finally
    //   143	153	613	finally
    //   161	180	613	finally
    //   188	207	613	finally
    //   215	242	613	finally
    //   250	287	613	finally
    //   361	387	613	finally
    //   107	116	617	java/lang/Throwable
    //   143	153	617	java/lang/Throwable
    //   161	180	617	java/lang/Throwable
    //   188	207	617	java/lang/Throwable
    //   215	242	617	java/lang/Throwable
    //   250	287	617	java/lang/Throwable
  }
  
  @TargetApi(12)
  public boolean b(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    com.igexin.a.a.c.a.b("startapp|broadcastPayload");
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT >= 12) {
      localIntent.addFlags(32);
    }
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10001);
    localBundle.putString("taskid", paramString1);
    localBundle.putString("messageid", paramString2);
    localBundle.putString("appid", paramString3);
    localBundle.putString("payloadid", paramString2 + ":" + paramString1);
    localBundle.putString("packagename", com.igexin.push.core.g.e);
    localIntent.setAction("com.igexin.sdk.action." + paramString3);
    if (paramString4 != null) {
      paramString1 = paramString4.getBytes();
    }
    for (;;)
    {
      if (paramString1 != null) {
        com.igexin.a.a.c.a.b("startapp|broadcast|payload is " + new String(paramString1));
      }
      for (;;)
      {
        localBundle.putByteArray("payload", paramString1);
        localIntent.putExtras(localBundle);
        if (paramString1 != null) {}
        try
        {
          com.igexin.a.a.c.a.b("startapp|broadcast|" + paramString3 + "|" + new String(paramString1, "utf-8"));
          com.igexin.push.core.g.g.sendBroadcast(localIntent);
          return true;
        }
        catch (Exception paramString1)
        {
          com.igexin.a.a.c.a.b("startapp|broadcast|error|" + paramString1.toString());
          return false;
        }
        paramString1 = c(paramString1, paramString2);
        paramString1 = (PushTaskBean)com.igexin.push.core.g.ah.get(paramString1);
        if (paramString1 == null) {
          break label336;
        }
        paramString1 = paramString1.getMsgExtra();
        break;
        com.igexin.a.a.c.a.b("startapp|broadcast|payload is empty!");
      }
      label336:
      paramString1 = null;
    }
  }
  
  /* Error */
  public com.igexin.push.d.c.k c()
  {
    // Byte code:
    //   0: new 1772	com/igexin/push/d/c/k
    //   3: dup
    //   4: invokespecial 1773	com/igexin/push/d/c/k:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: getstatic 1775	com/igexin/push/core/g:s	J
    //   12: putfield 1776	com/igexin/push/d/c/k:a	J
    //   15: aload_2
    //   16: iconst_0
    //   17: putfield 1778	com/igexin/push/d/c/k:b	B
    //   20: aload_2
    //   21: ldc_w 1779
    //   24: putfield 1781	com/igexin/push/d/c/k:c	I
    //   27: aload_2
    //   28: getstatic 1059	com/igexin/push/core/g:a	Ljava/lang/String;
    //   31: putfield 1782	com/igexin/push/d/c/k:d	Ljava/lang/String;
    //   34: invokestatic 1783	com/igexin/push/util/a:a	()Z
    //   37: ifeq +208 -> 245
    //   40: new 1609	java/util/ArrayList
    //   43: dup
    //   44: invokespecial 1610	java/util/ArrayList:<init>	()V
    //   47: astore_3
    //   48: getstatic 471	com/igexin/push/core/g:g	Landroid/content/Context;
    //   51: ldc_w 1785
    //   54: invokevirtual 673	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   57: checkcast 195	android/net/ConnectivityManager
    //   60: astore 4
    //   62: aload 4
    //   64: ifnull +203 -> 267
    //   67: aload 4
    //   69: invokevirtual 199	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   72: astore 4
    //   74: aload 4
    //   76: ifnull +191 -> 267
    //   79: aload 4
    //   81: invokevirtual 1787	android/net/NetworkInfo:getType	()I
    //   84: istore_1
    //   85: new 1789	com/igexin/push/d/c/l
    //   88: dup
    //   89: invokespecial 1790	com/igexin/push/d/c/l:<init>	()V
    //   92: astore 4
    //   94: aload 4
    //   96: iconst_2
    //   97: putfield 1792	com/igexin/push/d/c/l:a	B
    //   100: aload 4
    //   102: iload_1
    //   103: invokestatic 527	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   106: putfield 1794	com/igexin/push/d/c/l:b	Ljava/lang/Object;
    //   109: aload_3
    //   110: aload 4
    //   112: invokeinterface 538 2 0
    //   117: pop
    //   118: iload_1
    //   119: iconst_1
    //   120: if_icmpne +111 -> 231
    //   123: getstatic 471	com/igexin/push/core/g:g	Landroid/content/Context;
    //   126: ldc_w 550
    //   129: invokevirtual 673	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   132: checkcast 1796	android/net/wifi/WifiManager
    //   135: invokevirtual 1800	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   138: astore 5
    //   140: aload 5
    //   142: ifnull +89 -> 231
    //   145: aload 5
    //   147: invokevirtual 1805	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   150: astore 4
    //   152: aload 5
    //   154: invokevirtual 1808	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   157: astore 5
    //   159: aload 4
    //   161: ifnull +34 -> 195
    //   164: new 1789	com/igexin/push/d/c/l
    //   167: dup
    //   168: invokespecial 1790	com/igexin/push/d/c/l:<init>	()V
    //   171: astore 6
    //   173: aload 6
    //   175: iconst_1
    //   176: putfield 1792	com/igexin/push/d/c/l:a	B
    //   179: aload 6
    //   181: aload 4
    //   183: putfield 1794	com/igexin/push/d/c/l:b	Ljava/lang/Object;
    //   186: aload_3
    //   187: aload 6
    //   189: invokeinterface 538 2 0
    //   194: pop
    //   195: aload 5
    //   197: ifnull +34 -> 231
    //   200: new 1789	com/igexin/push/d/c/l
    //   203: dup
    //   204: invokespecial 1790	com/igexin/push/d/c/l:<init>	()V
    //   207: astore 4
    //   209: aload 4
    //   211: iconst_4
    //   212: putfield 1792	com/igexin/push/d/c/l:a	B
    //   215: aload 4
    //   217: aload 5
    //   219: putfield 1794	com/igexin/push/d/c/l:b	Ljava/lang/Object;
    //   222: aload_3
    //   223: aload 4
    //   225: invokeinterface 538 2 0
    //   230: pop
    //   231: aload_3
    //   232: invokeinterface 1809 1 0
    //   237: ifne +8 -> 245
    //   240: aload_2
    //   241: aload_3
    //   242: putfield 1812	com/igexin/push/d/c/k:e	Ljava/util/List;
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
    //   0	272	0	this	e
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
  
  public String c(String paramString1, String paramString2)
  {
    return paramString1 + ":" + paramString2;
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
                if (com.igexin.a.a.b.c.b() == null) {
                  continue;
                }
                D();
              }
            }
            catch (Throwable paramIntent)
            {
              com.igexin.a.a.c.a.b("CoreAction" + paramIntent.toString());
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
        com.igexin.push.core.g.r = 1;
      } while (!u());
      t();
      return;
      if ("android.intent.action.SCREEN_OFF".equals(str))
      {
        com.igexin.push.core.g.r = 0;
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
  
  public void c(String paramString)
  {
    String str = a(true, 4);
    str = str + "2.9.0.0|sdkconfig-error|";
    paramString = str + paramString;
    paramString = new com.igexin.push.f.a.c(new com.igexin.push.core.c.j(SDKUrlConfig.getBiUploadServiceUrl(), paramString.getBytes(), 0, true));
    com.igexin.a.a.b.c.b().a(paramString, false, true);
  }
  
  public void c(boolean paramBoolean)
  {
    f();
    d(paramBoolean);
  }
  
  public int d()
  {
    int j = 1;
    if ((!com.igexin.push.core.g.j) || (!com.igexin.push.core.g.k) || (a(System.currentTimeMillis())) || (!com.igexin.push.util.a.b()))
    {
      com.igexin.a.a.c.a.b("CoreAction|keyNegotiate stop ++++++++++");
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
  
  public com.igexin.push.core.b d(String paramString1, String paramString2)
  {
    Object localObject1 = com.igexin.push.core.b.a;
    paramString2 = paramString1 + ":" + paramString2;
    PushTaskBean localPushTaskBean = (PushTaskBean)com.igexin.push.core.g.ah.get(paramString2);
    if (localPushTaskBean == null)
    {
      localObject1 = com.igexin.push.core.b.c;
      return localObject1;
    }
    Iterator localIterator1 = localPushTaskBean.getActionChains().iterator();
    int i = 0;
    paramString2 = (String)localObject1;
    BaseAction localBaseAction;
    Object localObject2;
    if (localIterator1.hasNext())
    {
      localBaseAction = (BaseAction)localIterator1.next();
      localObject1 = com.igexin.push.core.b.c;
      if (localBaseAction == null) {
        return localObject1;
      }
      Iterator localIterator2 = com.igexin.push.extension.a.a().c().iterator();
      while (localIterator2.hasNext())
      {
        localObject2 = ((IPushExtension)localIterator2.next()).prepareExecuteAction(localPushTaskBean, localBaseAction);
        localObject1 = localObject2;
        if (localObject2 != com.igexin.push.core.b.c) {
          localObject1 = localObject2;
        }
      }
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (localObject1 == com.igexin.push.core.b.c)
      {
        localObject2 = (com.igexin.push.core.a.a.a)b.get(localBaseAction.getType());
        if (localObject2 == null) {
          break;
        }
        localObject2 = ((com.igexin.push.core.a.a.a)localObject2).a(localPushTaskBean, localBaseAction);
        localObject1 = localObject2;
        if (localObject2 == com.igexin.push.core.b.c) {
          break;
        }
      }
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
  }
  
  public String d(String paramString)
  {
    if (com.igexin.push.core.g.c() == null) {
      return null;
    }
    return (String)com.igexin.push.core.g.c().get(paramString);
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
        com.igexin.push.core.g.aq += 1;
        if (bool)
        {
          if (paramIntent.getBooleanExtra("isReload", false))
          {
            Process.killProcess(Process.myPid());
            return;
          }
          com.igexin.push.core.g.ap += 1;
          if (com.igexin.push.core.g.ar == null) {
            break label443;
          }
        }
        label438:
        label443:
        for (Object localObject1 = com.igexin.push.core.g.ar.b(); localObject1 != null; localObject1 = null)
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
              com.igexin.push.util.e.b(((com.igexin.push.core.bean.f)localObject2).c());
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
              if (com.igexin.push.core.g.ap == com.igexin.push.core.g.ao) {
                com.igexin.push.config.m.s.a(com.igexin.push.core.g.ar.a());
              }
              if ((i == 0) && (com.igexin.push.extension.a.a().a(com.igexin.push.core.g.g, (String)localObject2, ((com.igexin.push.core.bean.f)localObject1).d(), ((com.igexin.push.core.bean.f)localObject1).j(), ((com.igexin.push.core.bean.f)localObject1).c())))
              {
                com.igexin.a.a.c.a.b("CoreAction load " + ((com.igexin.push.core.bean.f)localObject1).d() + " success");
                ((com.igexin.push.core.bean.f)localObject1).b(System.currentTimeMillis());
                if (((com.igexin.push.core.bean.f)localObject1).g())
                {
                  com.igexin.push.util.e.b(((com.igexin.push.core.bean.f)localObject1).c());
                  paramIntent.remove(Integer.valueOf(j));
                }
              }
              com.igexin.push.config.a.a().g();
            }
            if ((com.igexin.push.core.g.aq != com.igexin.push.core.g.ao) || (!com.igexin.push.core.g.as)) {
              break;
            }
            com.igexin.a.a.c.a.b("CoreActiondownload ext success, restart service ###");
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
      catch (Exception paramIntent) {}
    }
  }
  
  public void d(boolean paramBoolean)
  {
    com.igexin.a.a.b.c.b().a(new com.igexin.push.d.b.b(paramBoolean));
    com.igexin.a.a.b.c.b().c();
  }
  
  public void e()
  {
    int i = 0;
    boolean bool2 = false;
    Object localObject;
    if (com.igexin.push.core.g.l)
    {
      if (!com.igexin.push.core.g.l)
      {
        bool1 = true;
        com.igexin.push.core.g.l = bool1;
        com.igexin.push.core.g.L = Math.abs(new Random().nextInt() % 24) * 3600000L + System.currentTimeMillis();
      }
    }
    else
    {
      com.igexin.push.c.i.a().e().e();
      if (com.igexin.push.core.g.s != 0L) {
        break label174;
      }
      com.igexin.a.a.c.a.b("registerReq #####");
      localObject = new com.igexin.push.d.c.f(com.igexin.push.core.g.v, com.igexin.push.core.g.w, com.igexin.push.core.g.B, com.igexin.push.core.g.a);
      if (com.igexin.push.core.f.a().g().a("R-" + com.igexin.push.core.g.B, (com.igexin.push.d.c.e)localObject, true) >= 0) {
        break label282;
      }
    }
    label174:
    label282:
    for (boolean bool1 = bool2;; bool1 = true)
    {
      com.igexin.a.a.c.a.b("registerReq|" + bool1 + "|" + com.igexin.push.core.g.B);
      for (;;)
      {
        return;
        bool1 = false;
        break;
        localObject = c();
        com.igexin.a.a.c.a.b("loginReqBefore|" + ((com.igexin.push.d.c.k)localObject).a);
        if (com.igexin.push.core.f.a().g().a("S-" + String.valueOf(com.igexin.push.core.g.s), (com.igexin.push.d.c.e)localObject, true) < 0) {}
        while (i != 0)
        {
          com.igexin.a.a.c.a.b("CoreAction|loginReq|" + com.igexin.push.core.g.t);
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
        com.igexin.a.a.c.a.b("CoreAction|doThirdGuardSt from or did is empty");
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str).append("|").append(com.igexin.push.core.g.g.getPackageName()).append("|").append(paramIntent).append("|").append(com.igexin.push.core.g.A).append("|").append(com.igexin.push.core.g.a).append("|").append(com.igexin.push.core.g.t).append("|").append(System.currentTimeMillis());
      y.a().a("21", localStringBuilder.toString());
      return;
    }
    catch (Throwable paramIntent)
    {
      com.igexin.a.a.c.a.b("CoreAction|doThirdGuardSt exception: " + paramIntent.toString());
    }
  }
  
  public void e(boolean paramBoolean)
  {
    com.igexin.a.a.b.c.b().a(new com.igexin.push.d.b.c(paramBoolean));
    com.igexin.a.a.b.c.b().c();
  }
  
  public void f()
  {
    com.igexin.a.a.b.a.a.d.a().b();
  }
  
  public int g()
  {
    com.igexin.a.a.c.a.a("CoreAction|send heart beat data ........");
    return com.igexin.push.core.f.a().g().a("H-" + com.igexin.push.core.g.t, new com.igexin.push.d.c.h(), true);
  }
  
  public void h()
  {
    Object localObject = com.igexin.push.core.b.c.a().b().iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.igexin.push.core.bean.j localJ = (com.igexin.push.core.bean.j)((Iterator)localObject).next();
      if (localJ.d() + 10000L <= System.currentTimeMillis())
      {
        long l = System.currentTimeMillis();
        localObject = new com.igexin.push.d.c.d();
        ((com.igexin.push.d.c.d)localObject).a();
        ((com.igexin.push.d.c.d)localObject).a = ((int)l);
        ((com.igexin.push.d.c.d)localObject).d = "17258000";
        ((com.igexin.push.d.c.d)localObject).e = localJ.b();
        ((com.igexin.push.d.c.d)localObject).g = com.igexin.push.core.g.t;
        com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.t, (com.igexin.push.d.c.e)localObject);
        com.igexin.a.a.c.a.b("freshral|" + localJ.b());
        com.igexin.push.core.b.c.a().a(localJ.a());
        localJ.a(localJ.d() + 10000L);
        com.igexin.push.core.b.c.a().a(localJ);
      }
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
      localD.g = com.igexin.push.core.g.t;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.t, localD);
      com.igexin.a.a.c.a.b("CoreAction|deviceidReq");
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public void j()
  {
    Object localObject1 = null;
    l2 = -1L;
    l1 = l2;
    try
    {
      localObject2 = new com.igexin.push.core.bean.a();
      l1 = l2;
      l2 = ((com.igexin.push.core.bean.a)localObject2).l;
      l1 = l2;
      localObject2 = com.igexin.push.core.bean.a.a((com.igexin.push.core.bean.a)localObject2);
      localObject1 = localObject2;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Object localObject2;
        l2 = l1;
      }
    }
    if (localObject1 != null)
    {
      com.igexin.a.a.c.a.b("addphoneinfo");
      localObject2 = com.igexin.push.core.b.c.a();
      if (localObject2 != null) {
        ((com.igexin.push.core.b.c)localObject2).a(new com.igexin.push.core.bean.j(l2, localObject1, (byte)5, l2));
      }
      localObject2 = new com.igexin.push.d.c.d();
      ((com.igexin.push.d.c.d)localObject2).a();
      ((com.igexin.push.d.c.d)localObject2).a = ((int)l2);
      ((com.igexin.push.d.c.d)localObject2).d = "17258000";
      ((com.igexin.push.d.c.d)localObject2).e = localObject1;
      ((com.igexin.push.d.c.d)localObject2).g = com.igexin.push.core.g.t;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.t, (com.igexin.push.d.c.e)localObject2);
    }
  }
  
  public long k()
  {
    return (new Random().nextInt(6) + 2) * 60000L;
  }
  
  @TargetApi(12)
  public void l()
  {
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT >= 12) {
      localIntent.addFlags(32);
    }
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10002);
    localBundle.putString("clientid", com.igexin.push.core.g.t);
    localIntent.putExtras(localBundle);
    com.igexin.a.a.c.a.b("broadcastClientid|" + com.igexin.push.core.g.t);
    com.igexin.push.core.f.a().a(localIntent);
    Log.d("PushService", "clientid is " + com.igexin.push.core.g.t);
  }
  
  @TargetApi(12)
  public void m()
  {
    Intent localIntent = new Intent();
    if (Build.VERSION.SDK_INT >= 12) {
      localIntent.addFlags(32);
    }
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10007);
    localBundle.putBoolean("onlineState", com.igexin.push.core.g.n);
    localIntent.putExtras(localBundle);
    com.igexin.push.core.f.a().a(localIntent);
  }
  
  /* Error */
  public String n()
  {
    // Byte code:
    //   0: new 586	java/io/File
    //   3: dup
    //   4: getstatic 779	com/igexin/push/core/g:Z	Ljava/lang/String;
    //   7: invokespecial 780	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: invokevirtual 783	java/io/File:exists	()Z
    //   13: ifeq +191 -> 204
    //   16: sipush 1024
    //   19: newarray byte
    //   21: astore_3
    //   22: new 2074	java/io/FileInputStream
    //   25: dup
    //   26: getstatic 779	com/igexin/push/core/g:Z	Ljava/lang/String;
    //   29: invokespecial 2075	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   32: astore_2
    //   33: new 2077	java/io/ByteArrayOutputStream
    //   36: dup
    //   37: invokespecial 2078	java/io/ByteArrayOutputStream:<init>	()V
    //   40: astore 4
    //   42: aload_2
    //   43: aload_3
    //   44: invokevirtual 2082	java/io/FileInputStream:read	([B)I
    //   47: istore_1
    //   48: iload_1
    //   49: iconst_m1
    //   50: if_icmpeq +40 -> 90
    //   53: aload 4
    //   55: aload_3
    //   56: iconst_0
    //   57: iload_1
    //   58: invokevirtual 2085	java/io/ByteArrayOutputStream:write	([BII)V
    //   61: goto -19 -> 42
    //   64: astore_3
    //   65: aload_2
    //   66: astore_3
    //   67: aload 4
    //   69: astore_2
    //   70: aload_3
    //   71: ifnull +7 -> 78
    //   74: aload_3
    //   75: invokevirtual 2086	java/io/FileInputStream:close	()V
    //   78: aload_2
    //   79: ifnull +125 -> 204
    //   82: aload_2
    //   83: invokevirtual 2087	java/io/ByteArrayOutputStream:close	()V
    //   86: aconst_null
    //   87: astore_2
    //   88: aload_2
    //   89: areturn
    //   90: new 296	java/lang/String
    //   93: dup
    //   94: aload 4
    //   96: invokevirtual 2090	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   99: invokespecial 332	java/lang/String:<init>	([B)V
    //   102: astore_3
    //   103: aload_2
    //   104: ifnull +7 -> 111
    //   107: aload_2
    //   108: invokevirtual 2086	java/io/FileInputStream:close	()V
    //   111: aload_3
    //   112: astore_2
    //   113: aload 4
    //   115: ifnull -27 -> 88
    //   118: aload 4
    //   120: invokevirtual 2087	java/io/ByteArrayOutputStream:close	()V
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
    //   142: invokevirtual 2086	java/io/FileInputStream:close	()V
    //   145: aload 4
    //   147: ifnull +8 -> 155
    //   150: aload 4
    //   152: invokevirtual 2087	java/io/ByteArrayOutputStream:close	()V
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
    //   0	206	0	this	e
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
  
  public void o()
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
      ((JSONObject)localObject).put("session_last", com.igexin.push.core.g.s);
      JSONArray localJSONArray = new JSONArray();
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("appid", ((n)localArrayList.get(i)).d());
        localJSONObject.put("name", ((n)localArrayList.get(i)).b());
        localJSONObject.put("version", ((n)localArrayList.get(i)).c());
        localJSONObject.put("versionName", ((n)localArrayList.get(i)).a());
        localJSONArray.put(localJSONObject);
        i += 1;
      }
      ((JSONObject)localObject).put("applist", localJSONArray);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    localObject = ((JSONObject)localObject).toString().getBytes();
    localObject = new com.igexin.push.f.a.c(new com.igexin.push.core.c.a(SDKUrlConfig.getBiUploadServiceUrl(), (byte[])localObject));
    com.igexin.a.a.b.c.b().a((com.igexin.a.a.d.d)localObject, false, true);
    g(p());
    com.igexin.a.a.c.a.b("reportapplist");
  }
  
  public String p()
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
        localArrayList1.add(((n)localArrayList2.get(i)).d());
        i += 1;
      }
    }
    return localArrayList1.toString();
  }
  
  public boolean q()
  {
    if (com.igexin.push.core.g.g == null) {}
    Object localObject1 = com.igexin.push.core.g.g.getApplicationContext().getPackageName();
    for (;;)
    {
      int j;
      int i;
      int k;
      try
      {
        localObject2 = com.igexin.push.core.g.g.getPackageManager().getPackageInfo((String)localObject1, 4).services;
        if (localObject2 == null) {
          break label273;
        }
        j = localObject2.length;
        i = 0;
        if (i >= j) {
          break label273;
        }
        bool = localObject2[i].name.contains("DownloadService");
        if (bool) {
          i = 1;
        }
      }
      catch (Exception localException1)
      {
        Object localObject2;
        boolean bool;
        i = 0;
        j = 0;
        int m = j;
        k = 0;
        j = i;
        i = m;
        continue;
      }
      try
      {
        localObject2 = com.igexin.push.core.g.g.getPackageManager().getPackageInfo((String)localObject1, 8).providers;
        if (localObject2 == null) {
          break label268;
        }
        k = localObject2.length;
        j = 0;
        if (j >= k) {
          break label268;
        }
        bool = localObject2[j].name.contains("DownloadProvider");
        if (bool) {
          j = 1;
        }
      }
      catch (Exception localException2)
      {
        k = 0;
        j = i;
        i = k;
        continue;
        label263:
        j = 0;
        continue;
      }
      try
      {
        localObject1 = com.igexin.push.core.g.g.getPackageManager().getPackageInfo((String)localObject1, 2).receivers;
        if (localObject1 == null) {
          break label263;
        }
        m = localObject1.length;
        k = 0;
        if (k >= m) {
          break label263;
        }
        bool = localObject1[k].name.contains("DownloadReceiver");
        if (!bool) {
          continue;
        }
        k = 1;
      }
      catch (Exception localException3)
      {
        k = i;
        i = j;
        j = k;
        continue;
        k = 0;
        continue;
      }
      if ((i != 0) && (j != 0) && (k != 0))
      {
        return true;
        i += 1;
        continue;
        j += 1;
        continue;
        k += 1;
      }
      else
      {
        return false;
        label268:
        label273:
        i = 0;
      }
    }
  }
  
  public void r()
  {
    long l = System.currentTimeMillis();
    com.igexin.push.core.f.a().k().a("message", "createtime <= " + (l - 604800000L));
  }
  
  public void s()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    String str = localSimpleDateFormat.format(new Date());
    Object localObject3 = new File("/sdcard/libs/");
    Object localObject2 = com.igexin.push.core.g.e;
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "unknowPacageName";
    }
    if (!((File)localObject3).exists()) {
      return;
    }
    localObject2 = ((File)localObject3).list();
    int j = localObject2.length;
    int i = 0;
    label82:
    int k;
    if (i < j)
    {
      k = localObject2[i].length();
      if ((localObject2[i].startsWith((String)localObject1)) && (localObject2[i].endsWith(".log")) && (k > ((String)localObject1).length() + 14) && (((String)localObject1).equals(localObject2[i].substring(0, k - 15)))) {
        break label159;
      }
    }
    for (;;)
    {
      i += 1;
      break label82;
      break;
      label159:
      localObject3 = localObject2[i].substring(((String)localObject1).length() + 1, k - 4);
      try
      {
        localObject3 = localSimpleDateFormat.parse((String)localObject3);
        if (Math.abs((localSimpleDateFormat.parse(str).getTime() - ((Date)localObject3).getTime()) / 86400000L) > 6L)
        {
          localObject3 = new File("/sdcard/libs/" + localObject2[i]);
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
      String str2;
      PushTaskBean localPushTaskBean;
      String str3;
      Map localMap;
      try
      {
        if (E()) {
          return;
        }
        Iterator localIterator = com.igexin.push.core.g.ah.entrySet().iterator();
        if (!localIterator.hasNext()) {
          break;
        }
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
          break;
        }
        if ((localMap.containsKey("endTime")) && (Long.valueOf((String)localMap.get("endTime")).longValue() < System.currentTimeMillis()))
        {
          a(com.igexin.push.core.a.m, str3, str2);
          localPushTaskBean.setStatus(com.igexin.push.core.a.l);
          continue;
        }
        if (!localMap.containsKey("wifi")) {
          break label235;
        }
      }
      catch (Exception localException)
      {
        com.igexin.a.a.c.a.b("CoreAction|" + localException.toString());
        return;
      }
      int i = Integer.valueOf((String)localMap.get("wifi")).intValue();
      w();
      if (i == com.igexin.push.core.g.q) {
        label235:
        if (localMap.containsKey("screenOn"))
        {
          i = Integer.valueOf((String)localMap.get("screenOn")).intValue();
          v();
          if (i != com.igexin.push.core.g.r) {}
        }
        else
        {
          String str1;
          if (localMap.containsKey("ssid"))
          {
            str1 = (String)localMap.get("ssid");
            x();
            if (!com.igexin.push.core.g.an.containsValue(str1)) {}
          }
          else if (localMap.containsKey("bssid"))
          {
            String str4 = (String)localMap.get("bssid");
            if ((!com.igexin.push.core.g.an.containsKey(str4)) || (!((String)com.igexin.push.core.g.an.get(str4)).equals(str1))) {}
          }
          else if ((!localMap.containsKey("startTime")) || (Long.valueOf((String)localMap.get("startTime")).longValue() <= System.currentTimeMillis()))
          {
            str1 = localPushTaskBean.getMessageId();
            a().a(str3, str1, com.igexin.push.core.g.a, com.igexin.push.core.g.e);
            a(com.igexin.push.core.a.l, str3, str2);
            localPushTaskBean.setStatus(com.igexin.push.core.a.l);
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
    if (((PowerManager)com.igexin.push.core.g.g.getSystemService("power")).isScreenOn())
    {
      com.igexin.push.core.g.r = 1;
      return;
    }
    com.igexin.push.core.g.r = 0;
  }
  
  public void w()
  {
    NetworkInfo.State localState = ((ConnectivityManager)com.igexin.push.core.g.g.getSystemService("connectivity")).getNetworkInfo(1).getState();
    if ((localState == NetworkInfo.State.CONNECTED) || (localState == NetworkInfo.State.CONNECTING))
    {
      com.igexin.push.core.g.q = 1;
      return;
    }
    com.igexin.push.core.g.q = 0;
  }
  
  public void x()
  {
    try
    {
      List localList = ((WifiManager)com.igexin.push.core.g.g.getSystemService("wifi")).getScanResults();
      com.igexin.push.core.g.an.clear();
      if ((localList != null) && (!localList.isEmpty()))
      {
        int i = 0;
        while (i < localList.size())
        {
          com.igexin.push.core.g.an.put(((ScanResult)localList.get(i)).BSSID, ((ScanResult)localList.get(i)).SSID);
          i += 1;
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      com.igexin.a.a.c.a.b("CoreAction|" + localThrowable.toString());
    }
  }
  
  public void y()
  {
    if ((!com.igexin.push.config.m.p) || (System.currentTimeMillis() - this.d < 300000L)) {}
    do
    {
      return;
      this.d = System.currentTimeMillis();
      localObject1 = com.igexin.push.core.b.f.a().d();
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
        localObject2 = new g(this, (new Random().nextInt(6) + 1) * 1000, (String)localObject2, (String)localObject3);
        com.igexin.push.core.f.a().a((com.igexin.push.f.b.f)localObject2);
        for (;;)
        {
          i += 1;
          break;
          e((String)localObject2, (String)localObject3);
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
    int i = com.igexin.push.core.g.am - 100;
    if (i < 0) {}
    for (com.igexin.push.core.g.am = 0;; com.igexin.push.core.g.am = i)
    {
      long l = System.currentTimeMillis();
      Iterator localIterator = com.igexin.push.core.g.al.entrySet().iterator();
      while (localIterator.hasNext()) {
        if (l - ((Long)((Map.Entry)localIterator.next()).getValue()).longValue() > 3600000L) {
          localIterator.remove();
        }
      }
    }
  }
}
