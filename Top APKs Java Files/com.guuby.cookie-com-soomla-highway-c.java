package com.soomla.highway;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.soomla.highway.core.HighwayBusProvider;
import com.soomla.highway.core.KeyValueStorage;
import com.soomla.highway.core.a;
import com.soomla.highway.events.AppToBackgroundEvent;
import com.soomla.highway.events.AppToForegroundEvent;
import com.soomla.highway.events.HighwayNetworkConnectedEvent;
import com.soomla.highway.events.HighwayNetworkDisconnectedEvent;
import com.soomla.highway.events.SendServerNeededMetaDataEvent;
import com.squareup.otto.Subscribe;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c
  extends GrowHighway
{
  private static c a = null;
  private boolean b = false;
  private Timer c = null;
  private Thread.UncaughtExceptionHandler d;
  private com.soomla.highway.core.b e;
  private Context f;
  private List<HighwayEvent> g;
  private Handler h = new Handler(Looper.getMainLooper());
  private int i = 2000;
  private int j = 40;
  private Thread k;
  private Thread l;
  private BlockingQueue<HighwayEvent> m = new PriorityBlockingQueue();
  private b n = null;
  private boolean o = false;
  private String p = null;
  private HighwayUidType q = null;
  private long r = 0L;
  private boolean s = false;
  private boolean t = false;
  private boolean u = false;
  private boolean v = false;
  private boolean w = false;
  private boolean x = false;
  private KeyValueStorage y;
  private d z = new d();
  
  private c() {}
  
  public static c a()
  {
    try
    {
      if (a == null) {
        a = new c();
      }
      return a;
    }
    finally {}
  }
  
  private void a(Activity paramActivity)
  {
    this.f = paramActivity.getApplicationContext();
    try
    {
      Class.forName("android.os.AsyncTask");
      this.e = com.soomla.highway.core.b.a(paramActivity.getApplication());
      this.d = Thread.getDefaultUncaughtExceptionHandler();
      Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
      {
        public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
        {
          HighwayBusProvider.getInstance().post(new AppToBackgroundEvent());
          c.a(c.this).uncaughtException(paramAnonymousThread, paramAnonymousThrowable);
        }
      });
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  private void a(HighwayEvent paramHighwayEvent)
  {
    HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Processing event to send: " + paramHighwayEvent.getName());
    if (paramHighwayEvent.getName().equals("pr_logout_finished")) {
      c(null);
    }
    a(paramHighwayEvent, true);
    a(paramHighwayEvent.getName());
  }
  
  private void a(HighwayEvent paramHighwayEvent, boolean paramBoolean)
  {
    if (this.n.d()) {}
    do
    {
      return;
      this.n.a(paramHighwayEvent);
    } while (!paramBoolean);
    v();
  }
  
  private void a(String paramString)
  {
    if (((paramString.equals("pr_login_finished")) && (this.q != HighwayUidType.SOCIAL)) || (paramString.equals("pr_logout_finished")))
    {
      HighwayLogUtils.LogDebug("SOOMLA GrowHighway", paramString + " connect");
      if (paramString.equals("pr_login_finished")) {
        this.s = true;
      }
      o();
    }
  }
  
  private void a(HttpResponse paramHttpResponse)
  {
    if (!this.o) {
      t();
    }
    if ((paramHttpResponse != null) && (paramHttpResponse.getStatusLine().getStatusCode() == 401)) {
      s();
    }
  }
  
  private void a(JSONObject paramJSONObject)
  {
    for (;;)
    {
      JSONObject localJSONObject2;
      try
      {
        paramJSONObject.put("hv", "3.1.5");
        b(paramJSONObject);
        localJSONObject2 = new JSONObject();
        Map localMap = GrowIntegrationVersions.getIntegrations();
        Iterator localIterator = localMap.keySet().iterator();
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          if (!str.startsWith("soomla.")) {
            break label152;
          }
          localJSONObject1 = paramJSONObject;
          localJSONObject1.put(str.substring(str.lastIndexOf('.') + 1), localMap.get(str));
          continue;
        }
        GrowIntegrationVersions.clearIntegrations();
      }
      catch (JSONException paramJSONObject)
      {
        HighwayLogUtils.LogError("SOOMLA GrowHighway", "Couldn't create extra info json. error: " + paramJSONObject.getLocalizedMessage());
        return;
      }
      paramJSONObject.put("iv", localJSONObject2);
      return;
      label152:
      JSONObject localJSONObject1 = localJSONObject2;
    }
  }
  
  @TargetApi(3)
  private AsyncTask<Object, Object, Object> b(final String paramString)
  {
    new AsyncTask()
    {
      /* Error */
      private void a()
      {
        // Byte code:
        //   0: ldc 29
        //   2: ldc 31
        //   4: invokestatic 37	com/soomla/highway/HighwayLogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   7: aload_0
        //   8: getfield 19	com/soomla/highway/c$7:a	Ljava/lang/String;
        //   11: invokestatic 42	com/soomla/highway/e:a	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   14: astore 4
        //   16: aload 4
        //   18: ifnonnull +29 -> 47
        //   21: ldc 29
        //   23: ldc 44
        //   25: invokestatic 47	com/soomla/highway/HighwayLogUtils:LogError	(Ljava/lang/String;Ljava/lang/String;)V
        //   28: aload_0
        //   29: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   32: iconst_0
        //   33: invokestatic 50	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Z)Z
        //   36: pop
        //   37: aload_0
        //   38: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   41: iconst_0
        //   42: invokestatic 52	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   45: pop
        //   46: return
        //   47: new 54	org/json/JSONObject
        //   50: dup
        //   51: invokespecial 55	org/json/JSONObject:<init>	()V
        //   54: astore 5
        //   56: invokestatic 59	com/soomla/highway/e:e	()Ljava/lang/String;
        //   59: astore 6
        //   61: aload 6
        //   63: ifnull +13 -> 76
        //   66: aload 5
        //   68: ldc 61
        //   70: aload 6
        //   72: invokevirtual 65	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   75: pop
        //   76: aload 5
        //   78: ldc 67
        //   80: invokestatic 70	com/soomla/highway/e:c	()Ljava/lang/String;
        //   83: invokevirtual 65	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   86: pop
        //   87: invokestatic 73	com/soomla/highway/e:f	()Ljava/lang/String;
        //   90: astore 6
        //   92: aload 6
        //   94: invokestatic 79	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   97: ifne +13 -> 110
        //   100: aload 5
        //   102: ldc 81
        //   104: aload 6
        //   106: invokevirtual 65	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   109: pop
        //   110: new 54	org/json/JSONObject
        //   113: dup
        //   114: invokespecial 55	org/json/JSONObject:<init>	()V
        //   117: astore 6
        //   119: invokestatic 87	com/soomla/highway/Social$Provider:values	()[Lcom/soomla/highway/Social$Provider;
        //   122: astore 7
        //   124: aload 7
        //   126: arraylength
        //   127: istore_2
        //   128: iconst_0
        //   129: istore_1
        //   130: iload_1
        //   131: iload_2
        //   132: if_icmpge +54 -> 186
        //   135: aload 7
        //   137: iload_1
        //   138: aaload
        //   139: astore 8
        //   141: aload_0
        //   142: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   145: invokestatic 91	com/soomla/highway/c:l	(Lcom/soomla/highway/c;)Lcom/soomla/highway/core/KeyValueStorage;
        //   148: aload_0
        //   149: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   152: aload 8
        //   154: invokevirtual 94	com/soomla/highway/Social$Provider:toString	()Ljava/lang/String;
        //   157: invokevirtual 98	com/soomla/highway/c:getDBKeyForProvider	(Ljava/lang/String;)Ljava/lang/String;
        //   160: invokevirtual 103	com/soomla/highway/core/KeyValueStorage:get	(Ljava/lang/String;)Ljava/lang/String;
        //   163: astore 9
        //   165: aload 9
        //   167: ifnull +559 -> 726
        //   170: aload 6
        //   172: aload 8
        //   174: invokevirtual 94	com/soomla/highway/Social$Provider:toString	()Ljava/lang/String;
        //   177: aload 9
        //   179: invokevirtual 65	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   182: pop
        //   183: goto +543 -> 726
        //   186: aload 5
        //   188: ldc 105
        //   190: aload 6
        //   192: invokevirtual 65	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   195: pop
        //   196: aload 4
        //   198: ldc 107
        //   200: aload 5
        //   202: invokevirtual 65	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   205: pop
        //   206: ldc 29
        //   208: new 109	java/lang/StringBuilder
        //   211: dup
        //   212: invokespecial 110	java/lang/StringBuilder:<init>	()V
        //   215: ldc 112
        //   217: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: invokestatic 122	com/soomla/highway/HighwayConfig:getInstance	()Lcom/soomla/highway/HighwayConfig;
        //   223: ldc 124
        //   225: invokevirtual 127	com/soomla/highway/HighwayConfig:getFullHighwayUrl	(Ljava/lang/String;)Ljava/lang/String;
        //   228: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   231: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   234: invokestatic 37	com/soomla/highway/HighwayLogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   237: aload 4
        //   239: invokestatic 122	com/soomla/highway/HighwayConfig:getInstance	()Lcom/soomla/highway/HighwayConfig;
        //   242: ldc 124
        //   244: invokevirtual 127	com/soomla/highway/HighwayConfig:getFullHighwayUrl	(Ljava/lang/String;)Ljava/lang/String;
        //   247: invokestatic 133	com/soomla/highway/a/c:a	(Lorg/json/JSONObject;Ljava/lang/String;)Lorg/apache/http/HttpResponse;
        //   250: astore 4
        //   252: aload 4
        //   254: ifnull +252 -> 506
        //   257: aload 4
        //   259: invokeinterface 139 1 0
        //   264: invokeinterface 145 1 0
        //   269: sipush 200
        //   272: if_icmplt +310 -> 582
        //   275: aload 4
        //   277: invokeinterface 139 1 0
        //   282: invokeinterface 145 1 0
        //   287: sipush 299
        //   290: if_icmpgt +292 -> 582
        //   293: ldc 29
        //   295: ldc -109
        //   297: invokestatic 37	com/soomla/highway/HighwayLogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   300: new 54	org/json/JSONObject
        //   303: dup
        //   304: aload 4
        //   306: invokestatic 150	com/soomla/highway/a/c:a	(Lorg/apache/http/HttpResponse;)Ljava/lang/String;
        //   309: invokespecial 153	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   312: astore 5
        //   314: aload_0
        //   315: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   318: invokestatic 158	java/util/Calendar:getInstance	()Ljava/util/Calendar;
        //   321: invokevirtual 162	java/util/Calendar:getTimeInMillis	()J
        //   324: aload 5
        //   326: ldc -92
        //   328: invokevirtual 168	org/json/JSONObject:getLong	(Ljava/lang/String;)J
        //   331: lsub
        //   332: invokestatic 171	com/soomla/highway/c:a	(Lcom/soomla/highway/c;J)J
        //   335: pop2
        //   336: aload_0
        //   337: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   340: aload 5
        //   342: ldc -83
        //   344: invokevirtual 176	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   347: invokestatic 178	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Ljava/lang/String;)V
        //   350: ldc 29
        //   352: new 109	java/lang/StringBuilder
        //   355: dup
        //   356: invokespecial 110	java/lang/StringBuilder:<init>	()V
        //   359: ldc -76
        //   361: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: aload_0
        //   365: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   368: invokestatic 184	com/soomla/highway/c:m	(Lcom/soomla/highway/c;)Ljava/lang/String;
        //   371: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   377: invokestatic 37	com/soomla/highway/HighwayLogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   380: aload 5
        //   382: ldc -70
        //   384: invokevirtual 190	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
        //   387: istore_3
        //   388: aload_0
        //   389: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   392: astore 6
        //   394: iload_3
        //   395: ifeq +179 -> 574
        //   398: getstatic 196	com/soomla/highway/HighwayUidType:DEVICE_ONLY	Lcom/soomla/highway/HighwayUidType;
        //   401: astore 4
        //   403: aload 6
        //   405: aload 4
        //   407: invokestatic 199	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Lcom/soomla/highway/HighwayUidType;)Lcom/soomla/highway/HighwayUidType;
        //   410: pop
        //   411: invokestatic 204	com/soomla/highway/core/HighwayBusProvider:getInstance	()Lcom/soomla/highway/core/a;
        //   414: new 206	com/soomla/highway/events/ProcessServerNeededMetaEvent
        //   417: dup
        //   418: aload 5
        //   420: ldc -48
        //   422: invokevirtual 212	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
        //   425: invokespecial 215	com/soomla/highway/events/ProcessServerNeededMetaEvent:<init>	(Lorg/json/JSONArray;)V
        //   428: invokevirtual 221	com/soomla/highway/core/a:post	(Ljava/lang/Object;)V
        //   431: aload_0
        //   432: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   435: iconst_1
        //   436: invokestatic 223	com/soomla/highway/c:c	(Lcom/soomla/highway/c;Z)Z
        //   439: pop
        //   440: aload_0
        //   441: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   444: invokestatic 227	com/soomla/highway/c:j	(Lcom/soomla/highway/c;)Lcom/soomla/highway/b;
        //   447: invokevirtual 231	com/soomla/highway/b:b	()V
        //   450: invokestatic 204	com/soomla/highway/core/HighwayBusProvider:getInstance	()Lcom/soomla/highway/core/a;
        //   453: new 233	com/soomla/highway/events/HighwayUidChangedEvent
        //   456: dup
        //   457: invokespecial 234	com/soomla/highway/events/HighwayUidChangedEvent:<init>	()V
        //   460: invokevirtual 221	com/soomla/highway/core/a:post	(Ljava/lang/Object;)V
        //   463: invokestatic 204	com/soomla/highway/core/HighwayBusProvider:getInstance	()Lcom/soomla/highway/core/a;
        //   466: new 236	com/soomla/highway/events/SendServerNeededMetaDataEvent
        //   469: dup
        //   470: invokespecial 237	com/soomla/highway/events/SendServerNeededMetaDataEvent:<init>	()V
        //   473: invokevirtual 221	com/soomla/highway/core/a:post	(Ljava/lang/Object;)V
        //   476: aload_0
        //   477: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   480: invokestatic 241	com/soomla/highway/c:k	(Lcom/soomla/highway/c;)V
        //   483: aload_0
        //   484: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   487: iconst_1
        //   488: invokestatic 244	com/soomla/highway/c:d	(Lcom/soomla/highway/c;Z)Z
        //   491: pop
        //   492: aload_0
        //   493: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   496: invokestatic 247	com/soomla/highway/c:n	(Lcom/soomla/highway/c;)V
        //   499: aload_0
        //   500: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   503: invokestatic 250	com/soomla/highway/c:o	(Lcom/soomla/highway/c;)V
        //   506: aload_0
        //   507: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   510: iconst_0
        //   511: invokestatic 50	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Z)Z
        //   514: pop
        //   515: aload_0
        //   516: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   519: iconst_0
        //   520: invokestatic 52	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   523: pop
        //   524: return
        //   525: astore 4
        //   527: ldc 29
        //   529: new 109	java/lang/StringBuilder
        //   532: dup
        //   533: invokespecial 110	java/lang/StringBuilder:<init>	()V
        //   536: ldc -4
        //   538: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   541: aload 4
        //   543: invokevirtual 255	org/json/JSONException:getLocalizedMessage	()Ljava/lang/String;
        //   546: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   549: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   552: invokestatic 47	com/soomla/highway/HighwayLogUtils:LogError	(Ljava/lang/String;Ljava/lang/String;)V
        //   555: aload_0
        //   556: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   559: iconst_0
        //   560: invokestatic 50	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Z)Z
        //   563: pop
        //   564: aload_0
        //   565: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   568: iconst_0
        //   569: invokestatic 52	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   572: pop
        //   573: return
        //   574: getstatic 258	com/soomla/highway/HighwayUidType:SOCIAL	Lcom/soomla/highway/HighwayUidType;
        //   577: astore 4
        //   579: goto -176 -> 403
        //   582: ldc 29
        //   584: new 109	java/lang/StringBuilder
        //   587: dup
        //   588: invokespecial 110	java/lang/StringBuilder:<init>	()V
        //   591: ldc_w 260
        //   594: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   597: aload 4
        //   599: invokeinterface 139 1 0
        //   604: invokeinterface 145 1 0
        //   609: invokevirtual 263	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   612: ldc_w 265
        //   615: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   618: aload 4
        //   620: invokeinterface 139 1 0
        //   625: invokeinterface 268 1 0
        //   630: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   633: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   636: invokestatic 37	com/soomla/highway/HighwayLogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   639: aload_0
        //   640: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   643: invokestatic 184	com/soomla/highway/c:m	(Lcom/soomla/highway/c;)Ljava/lang/String;
        //   646: ifnonnull +14 -> 660
        //   649: aload_0
        //   650: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   653: aload_0
        //   654: getfield 19	com/soomla/highway/c$7:a	Ljava/lang/String;
        //   657: invokestatic 178	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Ljava/lang/String;)V
        //   660: aload_0
        //   661: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   664: aload 4
        //   666: invokestatic 271	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Lorg/apache/http/HttpResponse;)V
        //   669: goto -163 -> 506
        //   672: astore 4
        //   674: ldc 29
        //   676: aload 4
        //   678: invokevirtual 272	java/lang/Exception:toString	()Ljava/lang/String;
        //   681: invokestatic 47	com/soomla/highway/HighwayLogUtils:LogError	(Ljava/lang/String;Ljava/lang/String;)V
        //   684: aload_0
        //   685: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   688: iconst_0
        //   689: invokestatic 50	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Z)Z
        //   692: pop
        //   693: aload_0
        //   694: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   697: iconst_0
        //   698: invokestatic 52	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   701: pop
        //   702: return
        //   703: astore 4
        //   705: aload_0
        //   706: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   709: iconst_0
        //   710: invokestatic 50	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Z)Z
        //   713: pop
        //   714: aload_0
        //   715: getfield 17	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   718: iconst_0
        //   719: invokestatic 52	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   722: pop
        //   723: aload 4
        //   725: athrow
        //   726: iload_1
        //   727: iconst_1
        //   728: iadd
        //   729: istore_1
        //   730: goto -600 -> 130
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	733	0	this	7
        //   129	601	1	i	int
        //   127	6	2	j	int
        //   387	8	3	bool	boolean
        //   14	392	4	localObject1	Object
        //   525	17	4	localJSONException	JSONException
        //   577	88	4	localHighwayUidType	HighwayUidType
        //   672	5	4	localException	Exception
        //   703	21	4	localObject2	Object
        //   54	365	5	localJSONObject	JSONObject
        //   59	345	6	localObject3	Object
        //   122	14	7	arrayOfProvider	Social.Provider[]
        //   139	34	8	localProvider	Social.Provider
        //   163	15	9	str	String
        // Exception table:
        //   from	to	target	type
        //   7	16	525	org/json/JSONException
        //   21	46	525	org/json/JSONException
        //   47	61	525	org/json/JSONException
        //   66	76	525	org/json/JSONException
        //   76	110	525	org/json/JSONException
        //   110	128	525	org/json/JSONException
        //   141	165	525	org/json/JSONException
        //   170	183	525	org/json/JSONException
        //   186	206	525	org/json/JSONException
        //   237	252	672	java/lang/Exception
        //   257	394	672	java/lang/Exception
        //   398	403	672	java/lang/Exception
        //   403	506	672	java/lang/Exception
        //   574	579	672	java/lang/Exception
        //   582	660	672	java/lang/Exception
        //   660	669	672	java/lang/Exception
        //   237	252	703	finally
        //   257	394	703	finally
        //   398	403	703	finally
        //   403	506	703	finally
        //   574	579	703	finally
        //   582	660	703	finally
        //   660	669	703	finally
        //   674	684	703	finally
      }
      
      protected Object doInBackground(Object... paramAnonymousVarArgs)
      {
        a();
        return null;
      }
    };
  }
  
  private HighwayEvent b(String paramString1, JSONObject paramJSONObject, String paramString2)
  {
    JSONObject localJSONObject = paramJSONObject;
    if (paramJSONObject == null)
    {
      localJSONObject = new JSONObject();
      HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "No extra info for event: " + paramString1);
    }
    try
    {
      paramJSONObject = e.a();
      if (paramJSONObject == null)
      {
        HighwayLogUtils.LogError("SOOMLA GrowHighway", "Couldn't build event postData.");
        return null;
      }
      if (paramString1.equals("hw_init"))
      {
        a(localJSONObject);
        HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Added extra info to hw_init event: " + localJSONObject.toString());
      }
      paramJSONObject.put("name", paramString1);
      paramJSONObject.put("extra", localJSONObject);
      paramString1 = paramString2;
      if (TextUtils.isEmpty(paramString2)) {
        paramString1 = "none";
      }
      paramJSONObject.put("intg", paramString1);
      paramString1 = new HighwayEvent(this.n.a(paramJSONObject), false);
      return paramString1;
    }
    catch (JSONException paramString1)
    {
      HighwayLogUtils.LogError("SOOMLA GrowHighway", "Couldn't build event postData. error: " + paramString1.getLocalizedMessage());
    }
    return null;
  }
  
  private void b(JSONObject paramJSONObject)
  {
    String str = a().b().getPackageName();
    try
    {
      paramJSONObject.put("pn", str);
      localPackageManager = a().b().getPackageManager();
    }
    catch (JSONException localJSONException1)
    {
      try
      {
        PackageManager localPackageManager;
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo(str, 0);
        paramJSONObject.put("vc", localPackageInfo.versionCode);
        paramJSONObject.put("vn", localPackageInfo.versionName);
        try
        {
          paramJSONObject.put("dn", localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(str, 0)).toString());
          return;
        }
        catch (PackageManager.NameNotFoundException paramJSONObject)
        {
          HighwayLogUtils.LogError("SOOMLA GrowHighway", "Couldn't get package manager for " + str + " - error: " + paramJSONObject.getLocalizedMessage());
          return;
        }
        catch (JSONException paramJSONObject)
        {
          HighwayLogUtils.LogError("SOOMLA GrowHighway", "Couldn't add app name info to json. error: " + paramJSONObject.getLocalizedMessage());
        }
        localJSONException1 = localJSONException1;
        HighwayLogUtils.LogError("SOOMLA GrowHighway", "Couldn't add bundleId info to json. error: " + localJSONException1.getLocalizedMessage());
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          HighwayLogUtils.LogError("SOOMLA GrowHighway", "Couldn't get package manager for " + str + " - error: " + localNameNotFoundException.getLocalizedMessage());
        }
      }
      catch (JSONException localJSONException2)
      {
        for (;;)
        {
          HighwayLogUtils.LogError("SOOMLA GrowHighway", "Couldn't add app version info to json. error: " + localJSONException2.getLocalizedMessage());
        }
      }
    }
  }
  
  private boolean b(HighwayEvent paramHighwayEvent)
  {
    if (!c(paramHighwayEvent)) {
      return false;
    }
    HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Preparing cached event: " + paramHighwayEvent);
    e.a(paramHighwayEvent);
    a(paramHighwayEvent, false);
    return d(paramHighwayEvent);
  }
  
  private JSONObject c(JSONObject paramJSONObject)
    throws JSONException
  {
    JSONArray localJSONArray = paramJSONObject.getJSONArray("apps");
    if (localJSONArray.length() == 0) {
      return null;
    }
    Object localObject = q();
    int i1 = 0;
    if (i1 < localJSONArray.length())
    {
      if (((Set)localObject).contains(localJSONArray.getString(i1))) {}
      for (int i2 = 1;; i2 = 0)
      {
        localJSONArray.put(i1, i2);
        i1 += 1;
        break;
      }
    }
    localObject = e.a();
    ((JSONObject)localObject).put("apps", localJSONArray);
    ((JSONObject)localObject).put("listId", paramJSONObject.getString("listId"));
    return localObject;
  }
  
  /* Error */
  private void c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 413	com/soomla/highway/c:a	()Lcom/soomla/highway/c;
    //   5: invokevirtual 415	com/soomla/highway/c:b	()Landroid/content/Context;
    //   8: ldc_w 511
    //   11: iconst_0
    //   12: invokevirtual 515	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   15: invokeinterface 521 1 0
    //   20: astore_2
    //   21: aload_0
    //   22: aload_1
    //   23: putfield 113	com/soomla/highway/c:p	Ljava/lang/String;
    //   26: aload_1
    //   27: invokestatic 395	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   30: ifeq +33 -> 63
    //   33: aload_0
    //   34: getfield 523	com/soomla/highway/c:y	Lcom/soomla/highway/core/KeyValueStorage;
    //   37: ldc_w 525
    //   40: invokevirtual 530	com/soomla/highway/core/KeyValueStorage:remove	(Ljava/lang/String;)V
    //   43: aload_2
    //   44: ldc_w 532
    //   47: invokeinterface 537 2 0
    //   52: pop
    //   53: aload_2
    //   54: invokeinterface 540 1 0
    //   59: pop
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: aload_0
    //   64: getfield 523	com/soomla/highway/c:y	Lcom/soomla/highway/core/KeyValueStorage;
    //   67: ldc_w 525
    //   70: aload_1
    //   71: invokevirtual 542	com/soomla/highway/core/KeyValueStorage:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   74: aload_2
    //   75: ldc_w 532
    //   78: aload_1
    //   79: invokeinterface 546 3 0
    //   84: pop
    //   85: goto -32 -> 53
    //   88: astore_1
    //   89: aload_0
    //   90: monitorexit
    //   91: aload_1
    //   92: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	c
    //   0	93	1	paramString	String
    //   20	55	2	localEditor	SharedPreferences.Editor
    // Exception table:
    //   from	to	target	type
    //   2	53	88	finally
    //   53	60	88	finally
    //   63	85	88	finally
  }
  
  private boolean c(HighwayEvent paramHighwayEvent)
  {
    if ((!this.o) || ((!paramHighwayEvent.hasUid()) && (TextUtils.isEmpty(c()))))
    {
      HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Not processing cached event: " + paramHighwayEvent.getEventJSON());
      HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Reason: mHwServerConnected=" + this.o + " eventJsonContainsUid=" + paramHighwayEvent.hasUid() + " getSoomlaUID()=" + c());
      if (this.o)
      {
        HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Reconnecting");
        o();
      }
      return false;
    }
    return true;
  }
  
  private boolean d(HighwayEvent paramHighwayEvent)
  {
    return (this.o) && (paramHighwayEvent != null) && (paramHighwayEvent.hasUid());
  }
  
  private boolean g()
  {
    return TextUtils.isEmpty(this.y.get("grow.highway.migrated"));
  }
  
  private void h()
  {
    for (;;)
    {
      try
      {
        if (this.u)
        {
          HighwayLogUtils.LogError("SOOMLA GrowHighway", "Highway already started. Can't start it twice.");
          return;
        }
        if (!HighwayConfig.getInstance().isInitialized())
        {
          HighwayLogUtils.LogError("SOOMLA GrowHighway", "The service has not been configured. Did you call initialize... ?");
          continue;
        }
        this.u = true;
      }
      finally {}
      HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "GrowHighway starting for url: " + HighwayConfig.getInstance().getHighwayUrl());
      HighwayBusProvider.getInstance().register(this);
      i();
      j();
      o();
    }
  }
  
  private void i()
  {
    try
    {
      if (this.k == null)
      {
        this.k = new Thread(new Runnable()
        {
          public void run()
          {
            HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Event consumer thread started listening to events...");
            while (!Thread.currentThread().isInterrupted()) {
              try
              {
                c.a(c.this, (HighwayEvent)c.b(c.this).take());
              }
              catch (InterruptedException localInterruptedException)
              {
                HighwayLogUtils.LogError("SOOMLA GrowHighway", "Event consumer thread was interrupted! Terminating its work...");
                synchronized (c.this)
                {
                  c.a(c.this, null);
                  return;
                }
              }
            }
          }
        });
        this.k.start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void j()
  {
    try
    {
      if (this.l == null)
      {
        this.l = new Thread(new Runnable()
        {
          public void run()
          {
            HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Cache processor thread started listening to cache...");
            for (;;)
            {
              if (!Thread.currentThread().isInterrupted()) {
                try
                {
                  synchronized (c.this)
                  {
                    c.this.wait();
                    if (c.c(c.this)) {}
                  }
                  synchronized (c.this)
                  {
                    c.b(c.this, null);
                    return;
                    c.d(c.this).removeCallbacksAndMessages(null);
                    Handler localHandler = c.d(c.this);
                    Runnable local1 = new Runnable()
                    {
                      public void run()
                      {
                        synchronized (c.this)
                        {
                          c.e(c.this);
                          return;
                        }
                      }
                    };
                    if (c.f(c.this))
                    {
                      l = c.g(c.this);
                      localHandler.postDelayed(local1, l);
                      continue;
                    }
                    long l = 0L;
                  }
                }
                catch (InterruptedException localInterruptedException)
                {
                  HighwayLogUtils.LogError("SOOMLA GrowHighway", "Cache processor thread was interrupted! Terminating its work...");
                }
              }
            }
          }
        });
        this.l.start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private boolean k()
  {
    return this.n.a() < this.j;
  }
  
  private void l()
  {
    HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Sending events from cache");
    Queue localQueue = this.n.a(this.j);
    if (localQueue != null) {}
    try
    {
      if ((localQueue.isEmpty()) || (this.g != null)) {
        return;
      }
      this.g = new ArrayList();
      for (HighwayEvent localHighwayEvent = (HighwayEvent)localQueue.poll(); (localHighwayEvent != null) && (b(localHighwayEvent)); localHighwayEvent = (HighwayEvent)localQueue.poll()) {
        this.g.add(localHighwayEvent);
      }
    }
    finally {}
    try
    {
      if (this.g.isEmpty())
      {
        this.g = null;
        return;
      }
    }
    finally {}
    m();
  }
  
  private void m()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        boolean bool = c.h(c.this);
        if (bool)
        {
          ??? = c.i(c.this).iterator();
          while (((Iterator)???).hasNext())
          {
            HighwayEvent localHighwayEvent = (HighwayEvent)((Iterator)???).next();
            c.j(c.this).b(localHighwayEvent);
          }
        }
        synchronized (c.this)
        {
          c.a(c.this, null);
          if (bool) {
            c.k(c.this);
          }
          return;
        }
      }
    }).start();
  }
  
  private boolean n()
  {
    Object localObject;
    try
    {
      JSONArray localJSONArray = new JSONArray();
      localObject = this.g.iterator();
      while (((Iterator)localObject).hasNext())
      {
        localJSONArray.put(((HighwayEvent)((Iterator)localObject).next()).getPostData());
        continue;
        return false;
      }
    }
    catch (Exception localException)
    {
      HighwayLogUtils.LogError("SOOMLA GrowHighway", "Unable to send Highway event: " + localException.toString());
    }
    for (;;)
    {
      localObject = e.a();
      ((JSONObject)localObject).put("events", localException);
      localObject = com.soomla.highway.a.c.a((JSONObject)localObject, HighwayConfig.getInstance().getFullHighwayUrl("carpool"));
      if (localObject != null)
      {
        if ((((HttpResponse)localObject).getStatusLine().getStatusCode() >= 200) && (((HttpResponse)localObject).getStatusLine().getStatusCode() <= 299))
        {
          HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Got a success response for sendEvent! " + localException);
          return true;
        }
        HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Something went wrong with sendEvent. Got response: " + ((HttpResponse)localObject).getStatusLine().getStatusCode() + " " + ((HttpResponse)localObject).getStatusLine().getReasonPhrase());
        a((HttpResponse)localObject);
      }
    }
  }
  
  /* Error */
  @TargetApi(3)
  private void o()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 121	com/soomla/highway/c:t	Z
    //   6: ifeq +14 -> 20
    //   9: ldc -52
    //   11: ldc_w 694
    //   14: invokestatic 228	com/soomla/highway/HighwayLogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: aload_0
    //   21: iconst_1
    //   22: putfield 121	com/soomla/highway/c:t	Z
    //   25: aload_0
    //   26: iconst_0
    //   27: putfield 111	com/soomla/highway/c:o	Z
    //   30: aload_0
    //   31: getfield 113	com/soomla/highway/c:p	Ljava/lang/String;
    //   34: astore_1
    //   35: aload_0
    //   36: aconst_null
    //   37: invokespecial 239	com/soomla/highway/c:c	(Ljava/lang/String;)V
    //   40: aload_0
    //   41: aload_1
    //   42: invokespecial 696	com/soomla/highway/c:b	(Ljava/lang/String;)Landroid/os/AsyncTask;
    //   45: iconst_1
    //   46: anewarray 698	java/lang/Object
    //   49: dup
    //   50: iconst_0
    //   51: ldc_w 700
    //   54: aastore
    //   55: invokestatic 703	com/soomla/highway/e:a	(Landroid/os/AsyncTask;[Ljava/lang/Object;)V
    //   58: goto -41 -> 17
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	c
    //   34	8	1	str	String
    //   61	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	17	61	finally
    //   20	58	61	finally
  }
  
  /* Error */
  private void p()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 129	com/soomla/highway/c:x	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 129	com/soomla/highway/c:x	Z
    //   19: new 190	java/lang/Thread
    //   22: dup
    //   23: new 24	com/soomla/highway/c$8
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 706	com/soomla/highway/c$8:<init>	(Lcom/soomla/highway/c;)V
    //   31: invokespecial 618	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   34: invokevirtual 621	java/lang/Thread:start	()V
    //   37: goto -26 -> 11
    //   40: astore_2
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_2
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	c
    //   6	2	1	bool	boolean
    //   40	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	40	finally
    //   14	37	40	finally
  }
  
  private Set<String> q()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = a().b().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ApplicationInfo)localIterator.next()).packageName);
    }
    return localHashSet;
  }
  
  private void r()
  {
    HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Setting up broadcast receiver.");
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    a().b().registerReceiver(new com.soomla.highway.a.d(), localIntentFilter);
  }
  
  /* Error */
  private void s()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 123	com/soomla/highway/c:u	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 123	com/soomla/highway/c:u	Z
    //   19: aload_0
    //   20: getfield 109	com/soomla/highway/c:n	Lcom/soomla/highway/b;
    //   23: invokevirtual 739	com/soomla/highway/b:c	()V
    //   26: aload_0
    //   27: invokespecial 655	com/soomla/highway/c:u	()V
    //   30: invokestatic 601	com/soomla/highway/core/HighwayBusProvider:getInstance	()Lcom/soomla/highway/core/a;
    //   33: new 741	com/soomla/highway/events/GrowHighwayShutdownEvent
    //   36: dup
    //   37: invokespecial 742	com/soomla/highway/events/GrowHighwayShutdownEvent:<init>	()V
    //   40: invokevirtual 745	com/soomla/highway/core/a:post	(Ljava/lang/Object;)V
    //   43: invokestatic 601	com/soomla/highway/core/HighwayBusProvider:getInstance	()Lcom/soomla/highway/core/a;
    //   46: aload_0
    //   47: invokevirtual 748	com/soomla/highway/core/a:unregister	(Ljava/lang/Object;)V
    //   50: ldc -52
    //   52: ldc_w 750
    //   55: invokestatic 228	com/soomla/highway/HighwayLogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: goto -47 -> 11
    //   61: astore_2
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_2
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	c
    //   6	2	1	bool	boolean
    //   61	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	61	finally
    //   14	58	61	finally
  }
  
  private void t()
  {
    if (com.soomla.highway.a.d.a())
    {
      u();
      this.c = new Timer();
      this.c.schedule(new TimerTask()
      {
        public void run()
        {
          c.p(c.this);
        }
      }, 2000L);
    }
  }
  
  private void u()
  {
    if (this.c != null)
    {
      this.c.cancel();
      this.c = null;
    }
  }
  
  private void v()
  {
    try
    {
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  private void w()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 523	com/soomla/highway/c:y	Lcom/soomla/highway/core/KeyValueStorage;
    //   6: ldc_w 525
    //   9: invokevirtual 578	com/soomla/highway/core/KeyValueStorage:get	(Ljava/lang/String;)Ljava/lang/String;
    //   12: astore_1
    //   13: aload_1
    //   14: invokestatic 395	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   17: ifeq +11 -> 28
    //   20: aload_0
    //   21: aconst_null
    //   22: putfield 113	com/soomla/highway/c:p	Ljava/lang/String;
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: aload_0
    //   29: aload_1
    //   30: putfield 113	com/soomla/highway/c:p	Ljava/lang/String;
    //   33: goto -8 -> 25
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	c
    //   12	18	1	str	String
    //   36	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	25	36	finally
    //   28	33	36	finally
  }
  
  private void x()
  {
    try
    {
      String str = KeyValueStorage.getValue("soomla.highway.uid");
      if (!TextUtils.isEmpty(str))
      {
        c(str);
        KeyValueStorage.deleteKeyValue("soomla.highway.uid");
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  private void y()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 127	com/soomla/highway/c:w	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: ldc -52
    //   16: ldc_w 778
    //   19: invokestatic 228	com/soomla/highway/HighwayLogUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   22: new 190	java/lang/Thread
    //   25: dup
    //   26: new 10	com/soomla/highway/c$2
    //   29: dup
    //   30: aload_0
    //   31: aload_0
    //   32: getfield 113	com/soomla/highway/c:p	Ljava/lang/String;
    //   35: invokespecial 779	com/soomla/highway/c$2:<init>	(Lcom/soomla/highway/c;Ljava/lang/String;)V
    //   38: invokespecial 618	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   41: invokevirtual 621	java/lang/Thread:start	()V
    //   44: goto -33 -> 11
    //   47: astore_2
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_2
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	c
    //   6	2	1	bool	boolean
    //   47	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	47	finally
    //   14	44	47	finally
  }
  
  public void a(String paramString, JSONObject paramJSONObject)
  {
    a(paramString, paramJSONObject, "grow");
  }
  
  public void a(final String paramString1, final JSONObject paramJSONObject, final String paramString2)
  {
    HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Trying to send event: " + paramString1 + " with extra info: " + paramJSONObject.toString());
    new Thread(new Runnable()
    {
      public void run()
      {
        HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "Trying to send event with name: " + paramString1);
        HighwayEvent localHighwayEvent = c.a(c.this, paramString1, paramJSONObject, paramString2);
        if (localHighwayEvent == null)
        {
          HighwayLogUtils.LogError("SOOMLA GrowHighway", "(sendEvent) Couldn't build event dictionary. Stopping send!");
          return;
        }
        c.b(c.this).add(localHighwayEvent);
      }
    }).start();
  }
  
  public Context b()
  {
    return this.f;
  }
  
  public String c()
  {
    try
    {
      String str = this.p;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public HighwayUidType d()
  {
    try
    {
      HighwayUidType localHighwayUidType = this.q;
      return localHighwayUidType;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public boolean e()
  {
    try
    {
      boolean bool = this.s;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public long f()
  {
    return this.r;
  }
  
  public String getDBKeyForProvider(String paramString)
  {
    return "grow.highway." + paramString + "." + "profileId";
  }
  
  public KeyValueStorage getStorage()
  {
    return this.y;
  }
  
  public void initialize(Activity paramActivity, String paramString1, String paramString2)
  {
    if (this.b)
    {
      HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "GrowHighway is already initialized.");
      return;
    }
    HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "GrowHighway initializing...");
    try
    {
      Class.forName("android.os.AsyncTask");
      HighwayConfig.getInstance().initialize(paramString1, paramString2);
      HighwayBusProvider.getInstance().register(this.z);
      a(paramActivity);
      this.y = new KeyValueStorage(HighwayConfig.getInstance().getDbName(), new String(new char[] { 66, 48, 114, 49, 115, 87, 64, 115, 72, 51, 114, 101 }));
      try
      {
        paramActivity = Class.forName("com.soomla.highway.bridge.GrowHighwayBridge").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        paramActivity.getClass().getDeclaredMethod("initialize", new Class[] { Boolean.TYPE }).invoke(paramActivity, new Object[] { Boolean.valueOf(g()) });
        this.n = new b("HWEvents", "event.", this.y, g());
        if (g()) {
          x();
        }
        w();
        paramActivity = a().b().getSharedPreferences("store.verification.prefs", 0).edit();
        paramActivity.putString("gameKey", paramString1);
        paramActivity.commit();
        r();
        this.b = true;
        a("hw_init", new JSONObject());
        a("hw_sess_start", new JSONObject());
        h();
        this.y.put("grow.highway.migrated", "YES");
        return;
      }
      catch (ClassNotFoundException paramActivity)
      {
        for (;;)
        {
          HighwayLogUtils.LogDebug("SOOMLA GrowHighway", "GrowHighwayBridge class not found - skipping soomla components.");
        }
      }
      catch (Exception paramActivity)
      {
        for (;;)
        {
          HighwayLogUtils.LogError("SOOMLA GrowHighway", "Error setting up highway components: " + paramActivity.getLocalizedMessage());
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  @Subscribe
  public void onAppToBackgroundEvent(AppToBackgroundEvent paramAppToBackgroundEvent)
  {
    a("hw_sess_end", new JSONObject());
    try
    {
      this.h.removeCallbacksAndMessages(null);
      l();
      y();
      this.v = true;
      return;
    }
    finally {}
  }
  
  @Subscribe
  public void onAppToForegroundEvent(AppToForegroundEvent paramAppToForegroundEvent)
  {
    if (this.v)
    {
      i();
      j();
      a("hw_sess_start", new JSONObject());
      o();
    }
    this.v = false;
  }
  
  @Subscribe
  @TargetApi(3)
  public void onHighwayStatusNetworkConnectedEvent(HighwayNetworkConnectedEvent paramHighwayNetworkConnectedEvent)
  {
    if (this.e.a()) {
      return;
    }
    e.a(new AsyncTask()
    {
      protected Object doInBackground(Object... paramAnonymousVarArgs)
      {
        if (!c.c(c.this)) {
          c.p(c.this);
        }
        for (;;)
        {
          return null;
          HighwayBusProvider.getInstance().post(new SendServerNeededMetaDataEvent());
          c.k(c.this);
        }
      }
    }, new Object[] { "" });
  }
  
  @Subscribe
  public void onHighwayStatusNetworkDisconnectedEvent(HighwayNetworkDisconnectedEvent paramHighwayNetworkDisconnectedEvent)
  {
    this.o = false;
  }
}
