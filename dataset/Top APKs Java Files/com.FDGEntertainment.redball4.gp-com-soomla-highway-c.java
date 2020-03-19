package com.soomla.highway;

import android.annotation.TargetApi;
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
import com.soomla.AndroidBus;
import com.soomla.BusProvider;
import com.soomla.Foreground;
import com.soomla.SoomlaApp;
import com.soomla.SoomlaUtils;
import com.soomla.data.KeyValueStorage;
import com.soomla.events.AppToBackgroundEvent;
import com.soomla.events.AppToForegroundEvent;
import com.soomla.highway.a.a;
import com.soomla.highway.events.HighwayNetworkConnectedEvent;
import com.soomla.highway.events.HighwayNetworkDisconnectedEvent;
import com.soomla.highway.events.HighwaySentMetaEvent;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
  private static c b = null;
  private f A = new f();
  private Runnable a = new Runnable()
  {
    private void a()
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          boolean bool = c.f(c.this);
          e localE;
          if (bool)
          {
            ??? = c.g(c.this).iterator();
            while (((Iterator)???).hasNext())
            {
              localE = (e)((Iterator)???).next();
              if (localE.e()) {
                c.h(c.this).b(localE);
              }
            }
          }
          ??? = c.g(c.this).iterator();
          while (((Iterator)???).hasNext())
          {
            localE = (e)((Iterator)???).next();
            if (localE.e()) {
              localE.a(false);
            }
            c.a(c.this, localE, false);
          }
          c.g(c.this).clear();
          synchronized (c.this)
          {
            c.a(c.this, false);
            if (bool) {
              c.i(c.this);
            }
            return;
          }
        }
      }).start();
    }
    
    public void run()
    {
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Trying to send events to hw");
      if (!c.e(c.this)) {
        return;
      }
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Sending events to hw");
      a();
    }
  };
  private boolean c = false;
  private Timer d = null;
  private List<e> e = Collections.synchronizedList(new ArrayList());
  private Handler f = new Handler(Looper.getMainLooper());
  private int g = 2000;
  private int h = 40;
  private boolean i = false;
  private Thread j;
  private Thread k;
  private BlockingQueue<e> l = new PriorityBlockingQueue();
  private b m = null;
  private boolean n = false;
  private String o = null;
  private g p = null;
  private long q = 0L;
  private boolean r = false;
  private boolean s = false;
  private boolean t = false;
  private boolean u = false;
  private boolean v = false;
  private boolean w = false;
  private boolean x = false;
  private KeyValueStorage y = new KeyValueStorage(HighwayConfig.getInstance().getDbName(), new String(new char[] { 66, 48, 114, 49, 115, 87, 64, 115, 72, 51, 114, 101 }));
  private Set<d> z = new HashSet();
  
  private c()
  {
    if (g()) {
      A();
    }
    z();
  }
  
  private void A()
  {
    try
    {
      String str = KeyValueStorage.getValue("soomla.highway.uid");
      if (!TextUtils.isEmpty(str))
      {
        d(str);
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
  private void B()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 152	com/soomla/highway/c:w	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: ldc -23
    //   16: ldc -21
    //   18: invokestatic 240	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   21: new 242	java/lang/Thread
    //   24: dup
    //   25: new 14	com/soomla/highway/c$3
    //   28: dup
    //   29: aload_0
    //   30: aload_0
    //   31: getfield 136	com/soomla/highway/c:o	Ljava/lang/String;
    //   34: invokespecial 245	com/soomla/highway/c$3:<init>	(Lcom/soomla/highway/c;Ljava/lang/String;)V
    //   37: invokespecial 248	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   40: invokevirtual 251	java/lang/Thread:start	()V
    //   43: goto -32 -> 11
    //   46: astore_2
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_2
    //   50: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	this	c
    //   6	2	1	bool	boolean
    //   46	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	46	finally
    //   14	43	46	finally
  }
  
  public static c a()
  {
    try
    {
      if (b == null) {
        b = new c();
      }
      return b;
    }
    finally {}
  }
  
  private void a(d paramD)
  {
    this.z.add(paramD);
  }
  
  private void a(e paramE)
  {
    SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Processing event to send: " + paramE.f());
    if (paramE.f().equals("pr_logout_finished")) {
      d(null);
    }
    a(paramE, true);
  }
  
  private void a(e paramE, boolean paramBoolean)
  {
    if (this.m.f()) {}
    do
    {
      return;
      this.m.a(paramE);
    } while (!paramBoolean);
    y();
  }
  
  private void a(HttpResponse paramHttpResponse)
  {
    if (!this.n) {
      u();
    }
    if ((paramHttpResponse != null) && (paramHttpResponse.getStatusLine().getStatusCode() == 401)) {
      t();
    }
  }
  
  private void a(JSONArray paramJSONArray)
  {
    if (paramJSONArray == null) {}
    for (;;)
    {
      return;
      ArrayList localArrayList = new ArrayList();
      int i1 = 0;
      for (;;)
      {
        if (i1 < paramJSONArray.length()) {
          try
          {
            localArrayList.add(paramJSONArray.getString(i1));
            i1 += 1;
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't get needed element from neededData. index: " + i1);
            }
          }
        }
      }
      paramJSONArray = b().iterator();
      while (paramJSONArray.hasNext()) {
        ((d)paramJSONArray.next()).a(localArrayList);
      }
    }
  }
  
  private void a(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("hv", "3.0.3");
      localObject = b().iterator();
      while (((Iterator)localObject).hasNext()) {
        ((d)((Iterator)localObject).next()).a(paramJSONObject);
      }
      b(paramJSONObject);
    }
    catch (JSONException paramJSONObject)
    {
      SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't create extra info json. error: " + paramJSONObject.getLocalizedMessage());
      return;
    }
    Object localObject = new JSONObject();
    HashMap localHashMap = this.y.getForNonEncryptedQuery(getDBKeyForIntegration("*"));
    Iterator localIterator = localHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      ((JSONObject)localObject).put(str.substring(str.lastIndexOf('.') + 1), localHashMap.get(str));
      this.y.removeForNonEncryptedKey(str);
    }
    paramJSONObject.put("iv", localObject);
  }
  
  private e b(String paramString1, JSONObject paramJSONObject, String paramString2)
  {
    JSONObject localJSONObject = paramJSONObject;
    if (paramJSONObject == null)
    {
      localJSONObject = new JSONObject();
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "No extra info for event: " + paramString1);
    }
    try
    {
      paramJSONObject = h.d();
      if (paramJSONObject == null)
      {
        SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't build event postData.");
        return null;
      }
      if (paramString1.equals("hw_init"))
      {
        a(localJSONObject);
        SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Added extra info to hw_init event: " + localJSONObject.toString());
      }
      paramJSONObject.put("name", paramString1);
      paramJSONObject.put("extra", localJSONObject);
      paramString1 = paramString2;
      if (TextUtils.isEmpty(paramString2)) {
        paramString1 = "none";
      }
      paramJSONObject.put("intg", paramString1);
      paramString1 = new e(this.m.a(paramJSONObject), false);
      return paramString1;
    }
    catch (JSONException paramString1)
    {
      SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't build event postData. error: " + paramString1.getLocalizedMessage());
    }
    return null;
  }
  
  private void b(String paramString)
  {
    if (((paramString.equals("pr_login_finished")) && (this.p != g.b)) || (paramString.equals("pr_logout_finished")))
    {
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", paramString + " connect");
      if (paramString.equals("pr_login_finished")) {
        this.r = true;
      }
      p();
    }
  }
  
  private void b(JSONObject paramJSONObject)
  {
    String str = SoomlaApp.getAppContext().getPackageName();
    try
    {
      paramJSONObject.put("pn", str);
      localPackageManager = SoomlaApp.getAppContext().getPackageManager();
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
          SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't get package manager for " + str + " - error: " + paramJSONObject.getLocalizedMessage());
          return;
        }
        catch (JSONException paramJSONObject)
        {
          SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't add app name info to json. error: " + paramJSONObject.getLocalizedMessage());
        }
        localJSONException1 = localJSONException1;
        SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't add bundleId info to json. error: " + localJSONException1.getLocalizedMessage());
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't get package manager for " + str + " - error: " + localNameNotFoundException.getLocalizedMessage());
        }
      }
      catch (JSONException localJSONException2)
      {
        for (;;)
        {
          SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't add app version info to json. error: " + localJSONException2.getLocalizedMessage());
        }
      }
    }
  }
  
  private boolean b(e paramE)
  {
    if ((!c(paramE)) || (!l())) {
      return false;
    }
    SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Processing cached event: " + paramE);
    if (paramE.i())
    {
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Event already processed. Skipping: " + paramE);
      return true;
    }
    h.a(paramE);
    a(paramE, false);
    return d(paramE);
  }
  
  @TargetApi(3)
  private AsyncTask<Object, Object, Object> c(final String paramString)
  {
    new AsyncTask()
    {
      /* Error */
      private void a()
      {
        // Byte code:
        //   0: ldc 30
        //   2: ldc 32
        //   4: invokestatic 38	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   7: aload_0
        //   8: getfield 20	com/soomla/highway/c$7:a	Ljava/lang/String;
        //   11: invokestatic 43	com/soomla/highway/h:a	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   14: astore 4
        //   16: aload 4
        //   18: ifnonnull +29 -> 47
        //   21: ldc 30
        //   23: ldc 45
        //   25: invokestatic 48	com/soomla/SoomlaUtils:LogError	(Ljava/lang/String;Ljava/lang/String;)V
        //   28: aload_0
        //   29: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   32: iconst_0
        //   33: invokestatic 51	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   36: pop
        //   37: aload_0
        //   38: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   41: iconst_0
        //   42: invokestatic 53	com/soomla/highway/c:c	(Lcom/soomla/highway/c;Z)Z
        //   45: pop
        //   46: return
        //   47: new 55	org/json/JSONObject
        //   50: dup
        //   51: invokespecial 56	org/json/JSONObject:<init>	()V
        //   54: astore 5
        //   56: invokestatic 60	com/soomla/highway/h:h	()Ljava/lang/String;
        //   59: astore 6
        //   61: aload 6
        //   63: ifnull +13 -> 76
        //   66: aload 5
        //   68: ldc 62
        //   70: aload 6
        //   72: invokevirtual 66	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   75: pop
        //   76: aload 5
        //   78: ldc 68
        //   80: invokestatic 71	com/soomla/SoomlaUtils:deviceId	()Ljava/lang/String;
        //   83: invokevirtual 66	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   86: pop
        //   87: invokestatic 74	com/soomla/highway/h:e	()Ljava/lang/String;
        //   90: astore 6
        //   92: aload 6
        //   94: invokestatic 80	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   97: ifne +13 -> 110
        //   100: aload 5
        //   102: ldc 82
        //   104: aload 6
        //   106: invokevirtual 66	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   109: pop
        //   110: new 55	org/json/JSONObject
        //   113: dup
        //   114: invokespecial 56	org/json/JSONObject:<init>	()V
        //   117: astore 6
        //   119: invokestatic 88	com/soomla/highway/i$b:values	()[Lcom/soomla/highway/i$b;
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
        //   142: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   145: invokestatic 92	com/soomla/highway/c:j	(Lcom/soomla/highway/c;)Lcom/soomla/data/KeyValueStorage;
        //   148: aload_0
        //   149: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   152: aload 8
        //   154: invokevirtual 95	com/soomla/highway/i$b:toString	()Ljava/lang/String;
        //   157: invokevirtual 98	com/soomla/highway/c:a	(Ljava/lang/String;)Ljava/lang/String;
        //   160: invokevirtual 103	com/soomla/data/KeyValueStorage:get	(Ljava/lang/String;)Ljava/lang/String;
        //   163: astore 9
        //   165: aload 9
        //   167: ifnull +525 -> 692
        //   170: aload 6
        //   172: aload 8
        //   174: invokevirtual 95	com/soomla/highway/i$b:toString	()Ljava/lang/String;
        //   177: aload 9
        //   179: invokevirtual 66	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   182: pop
        //   183: goto +509 -> 692
        //   186: aload 5
        //   188: ldc 105
        //   190: aload 6
        //   192: invokevirtual 66	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   195: pop
        //   196: aload 4
        //   198: ldc 107
        //   200: aload 5
        //   202: invokevirtual 66	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   205: pop
        //   206: ldc 30
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
        //   234: invokestatic 38	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   237: aload 4
        //   239: invokestatic 122	com/soomla/highway/HighwayConfig:getInstance	()Lcom/soomla/highway/HighwayConfig;
        //   242: ldc 124
        //   244: invokevirtual 127	com/soomla/highway/HighwayConfig:getFullHighwayUrl	(Ljava/lang/String;)Ljava/lang/String;
        //   247: invokestatic 133	com/soomla/highway/b/c:a	(Lorg/json/JSONObject;Ljava/lang/String;)Lorg/apache/http/HttpResponse;
        //   250: astore 4
        //   252: aload 4
        //   254: ifnull +240 -> 494
        //   257: aload 4
        //   259: invokeinterface 139 1 0
        //   264: invokeinterface 145 1 0
        //   269: sipush 200
        //   272: if_icmplt +298 -> 570
        //   275: aload 4
        //   277: invokeinterface 139 1 0
        //   282: invokeinterface 145 1 0
        //   287: sipush 299
        //   290: if_icmpgt +280 -> 570
        //   293: ldc 30
        //   295: ldc -109
        //   297: invokestatic 38	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   300: new 55	org/json/JSONObject
        //   303: dup
        //   304: aload 4
        //   306: invokestatic 150	com/soomla/highway/b/c:a	(Lorg/apache/http/HttpResponse;)Ljava/lang/String;
        //   309: invokespecial 153	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   312: astore 5
        //   314: aload_0
        //   315: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   318: invokestatic 158	java/util/Calendar:getInstance	()Ljava/util/Calendar;
        //   321: invokevirtual 162	java/util/Calendar:getTimeInMillis	()J
        //   324: aload 5
        //   326: ldc -92
        //   328: invokevirtual 168	org/json/JSONObject:getLong	(Ljava/lang/String;)J
        //   331: lsub
        //   332: invokestatic 171	com/soomla/highway/c:a	(Lcom/soomla/highway/c;J)J
        //   335: pop2
        //   336: aload_0
        //   337: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   340: aload 5
        //   342: ldc -83
        //   344: invokevirtual 176	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   347: invokestatic 178	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Ljava/lang/String;)V
        //   350: ldc 30
        //   352: new 109	java/lang/StringBuilder
        //   355: dup
        //   356: invokespecial 110	java/lang/StringBuilder:<init>	()V
        //   359: ldc -76
        //   361: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: aload_0
        //   365: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   368: invokestatic 184	com/soomla/highway/c:k	(Lcom/soomla/highway/c;)Ljava/lang/String;
        //   371: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   377: invokestatic 38	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   380: aload 5
        //   382: ldc -70
        //   384: invokevirtual 190	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
        //   387: istore_3
        //   388: aload_0
        //   389: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   392: astore 6
        //   394: iload_3
        //   395: ifeq +167 -> 562
        //   398: getstatic 195	com/soomla/highway/g:a	Lcom/soomla/highway/g;
        //   401: astore 4
        //   403: aload 6
        //   405: aload 4
        //   407: invokestatic 198	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Lcom/soomla/highway/g;)Lcom/soomla/highway/g;
        //   410: pop
        //   411: aload_0
        //   412: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   415: aload 5
        //   417: ldc -56
        //   419: invokevirtual 204	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
        //   422: invokestatic 207	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Lorg/json/JSONArray;)V
        //   425: aload_0
        //   426: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   429: iconst_1
        //   430: invokestatic 210	com/soomla/highway/c:d	(Lcom/soomla/highway/c;Z)Z
        //   433: pop
        //   434: aload_0
        //   435: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   438: invokestatic 213	com/soomla/highway/c:h	(Lcom/soomla/highway/c;)Lcom/soomla/highway/b;
        //   441: invokevirtual 217	com/soomla/highway/b:d	()V
        //   444: invokestatic 222	com/soomla/BusProvider:getInstance	()Lcom/soomla/AndroidBus;
        //   447: new 224	com/soomla/highway/events/HighwayUidChangedEvent
        //   450: dup
        //   451: invokespecial 225	com/soomla/highway/events/HighwayUidChangedEvent:<init>	()V
        //   454: invokevirtual 231	com/soomla/AndroidBus:post	(Ljava/lang/Object;)V
        //   457: aload_0
        //   458: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   461: invokestatic 235	com/soomla/highway/c:l	(Lcom/soomla/highway/c;)V
        //   464: aload_0
        //   465: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   468: invokestatic 238	com/soomla/highway/c:i	(Lcom/soomla/highway/c;)V
        //   471: aload_0
        //   472: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   475: iconst_1
        //   476: invokestatic 240	com/soomla/highway/c:e	(Lcom/soomla/highway/c;Z)Z
        //   479: pop
        //   480: aload_0
        //   481: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   484: invokestatic 243	com/soomla/highway/c:m	(Lcom/soomla/highway/c;)V
        //   487: aload_0
        //   488: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   491: invokestatic 246	com/soomla/highway/c:n	(Lcom/soomla/highway/c;)V
        //   494: aload_0
        //   495: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   498: iconst_0
        //   499: invokestatic 51	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   502: pop
        //   503: aload_0
        //   504: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   507: iconst_0
        //   508: invokestatic 53	com/soomla/highway/c:c	(Lcom/soomla/highway/c;Z)Z
        //   511: pop
        //   512: return
        //   513: astore 4
        //   515: ldc 30
        //   517: new 109	java/lang/StringBuilder
        //   520: dup
        //   521: invokespecial 110	java/lang/StringBuilder:<init>	()V
        //   524: ldc -8
        //   526: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   529: aload 4
        //   531: invokevirtual 251	org/json/JSONException:getLocalizedMessage	()Ljava/lang/String;
        //   534: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   537: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   540: invokestatic 48	com/soomla/SoomlaUtils:LogError	(Ljava/lang/String;Ljava/lang/String;)V
        //   543: aload_0
        //   544: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   547: iconst_0
        //   548: invokestatic 51	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   551: pop
        //   552: aload_0
        //   553: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   556: iconst_0
        //   557: invokestatic 53	com/soomla/highway/c:c	(Lcom/soomla/highway/c;Z)Z
        //   560: pop
        //   561: return
        //   562: getstatic 253	com/soomla/highway/g:b	Lcom/soomla/highway/g;
        //   565: astore 4
        //   567: goto -164 -> 403
        //   570: ldc 30
        //   572: new 109	java/lang/StringBuilder
        //   575: dup
        //   576: invokespecial 110	java/lang/StringBuilder:<init>	()V
        //   579: ldc -1
        //   581: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   584: aload 4
        //   586: invokeinterface 139 1 0
        //   591: invokeinterface 145 1 0
        //   596: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   599: ldc_w 260
        //   602: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   605: aload 4
        //   607: invokeinterface 139 1 0
        //   612: invokeinterface 263 1 0
        //   617: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   620: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   623: invokestatic 38	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
        //   626: aload_0
        //   627: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   630: aload 4
        //   632: invokestatic 266	com/soomla/highway/c:a	(Lcom/soomla/highway/c;Lorg/apache/http/HttpResponse;)V
        //   635: goto -141 -> 494
        //   638: astore 4
        //   640: ldc 30
        //   642: aload 4
        //   644: invokevirtual 267	java/lang/Exception:toString	()Ljava/lang/String;
        //   647: invokestatic 48	com/soomla/SoomlaUtils:LogError	(Ljava/lang/String;Ljava/lang/String;)V
        //   650: aload_0
        //   651: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   654: iconst_0
        //   655: invokestatic 51	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   658: pop
        //   659: aload_0
        //   660: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   663: iconst_0
        //   664: invokestatic 53	com/soomla/highway/c:c	(Lcom/soomla/highway/c;Z)Z
        //   667: pop
        //   668: return
        //   669: astore 4
        //   671: aload_0
        //   672: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   675: iconst_0
        //   676: invokestatic 51	com/soomla/highway/c:b	(Lcom/soomla/highway/c;Z)Z
        //   679: pop
        //   680: aload_0
        //   681: getfield 18	com/soomla/highway/c$7:b	Lcom/soomla/highway/c;
        //   684: iconst_0
        //   685: invokestatic 53	com/soomla/highway/c:c	(Lcom/soomla/highway/c;Z)Z
        //   688: pop
        //   689: aload 4
        //   691: athrow
        //   692: iload_1
        //   693: iconst_1
        //   694: iadd
        //   695: istore_1
        //   696: goto -566 -> 130
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	699	0	this	7
        //   129	567	1	i	int
        //   127	6	2	j	int
        //   387	8	3	bool	boolean
        //   14	392	4	localObject1	Object
        //   513	17	4	localJSONException	JSONException
        //   565	66	4	localG	g
        //   638	5	4	localException	Exception
        //   669	21	4	localObject2	Object
        //   54	362	5	localJSONObject	JSONObject
        //   59	345	6	localObject3	Object
        //   122	14	7	arrayOfB	i.b[]
        //   139	34	8	localB	i.b
        //   163	15	9	str	String
        // Exception table:
        //   from	to	target	type
        //   7	16	513	org/json/JSONException
        //   21	46	513	org/json/JSONException
        //   47	61	513	org/json/JSONException
        //   66	76	513	org/json/JSONException
        //   76	110	513	org/json/JSONException
        //   110	128	513	org/json/JSONException
        //   141	165	513	org/json/JSONException
        //   170	183	513	org/json/JSONException
        //   186	206	513	org/json/JSONException
        //   237	252	638	java/lang/Exception
        //   257	394	638	java/lang/Exception
        //   398	403	638	java/lang/Exception
        //   403	494	638	java/lang/Exception
        //   562	567	638	java/lang/Exception
        //   570	635	638	java/lang/Exception
        //   237	252	669	finally
        //   257	394	669	finally
        //   398	403	669	finally
        //   403	494	669	finally
        //   562	567	669	finally
        //   570	635	669	finally
        //   640	650	669	finally
      }
      
      protected Object doInBackground(Object... paramAnonymousVarArgs)
      {
        a();
        return null;
      }
    };
  }
  
  private JSONObject c(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.getJSONArray("apps");
    if (localJSONArray.length() == 0) {
      return null;
    }
    Object localObject = r();
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
    localObject = h.d();
    ((JSONObject)localObject).put("apps", localJSONArray);
    ((JSONObject)localObject).put("listId", paramJSONObject.getString("listId"));
    return localObject;
  }
  
  private boolean c(e paramE)
  {
    if ((!this.n) || ((!paramE.d()) && (TextUtils.isEmpty(c()))))
    {
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Not processing cached event: " + paramE.a());
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Reason: mHwServerConnected=" + this.n + " eventJsonContainsUid=" + paramE.d() + " getSoomlaUID()=" + c());
      return false;
    }
    return true;
  }
  
  /* Error */
  private void d(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 481	com/soomla/SoomlaApp:getAppContext	()Landroid/content/Context;
    //   5: ldc_w 602
    //   8: iconst_0
    //   9: invokevirtual 606	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   12: invokeinterface 612 1 0
    //   17: astore_2
    //   18: aload_0
    //   19: aload_1
    //   20: putfield 136	com/soomla/highway/c:o	Ljava/lang/String;
    //   23: aload_1
    //   24: invokestatic 224	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   27: ifeq +33 -> 60
    //   30: aload_0
    //   31: getfield 196	com/soomla/highway/c:y	Lcom/soomla/data/KeyValueStorage;
    //   34: ldc_w 614
    //   37: invokevirtual 617	com/soomla/data/KeyValueStorage:remove	(Ljava/lang/String;)V
    //   40: aload_2
    //   41: ldc_w 619
    //   44: invokeinterface 624 2 0
    //   49: pop
    //   50: aload_2
    //   51: invokeinterface 627 1 0
    //   56: pop
    //   57: aload_0
    //   58: monitorexit
    //   59: return
    //   60: aload_0
    //   61: getfield 196	com/soomla/highway/c:y	Lcom/soomla/data/KeyValueStorage;
    //   64: ldc_w 614
    //   67: aload_1
    //   68: invokevirtual 629	com/soomla/data/KeyValueStorage:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   71: aload_2
    //   72: ldc_w 619
    //   75: aload_1
    //   76: invokeinterface 633 3 0
    //   81: pop
    //   82: goto -32 -> 50
    //   85: astore_1
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_1
    //   89: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	c
    //   0	90	1	paramString	String
    //   17	55	2	localEditor	SharedPreferences.Editor
    // Exception table:
    //   from	to	target	type
    //   2	50	85	finally
    //   50	57	85	finally
    //   60	82	85	finally
  }
  
  private boolean d(e paramE)
  {
    if ((e(paramE)) && (k()))
    {
      b(paramE.f());
      f(paramE);
      return true;
    }
    return false;
  }
  
  private boolean e(e paramE)
  {
    return (this.n) && (l()) && (paramE != null) && (paramE.d());
  }
  
  private void f(e paramE)
  {
    for (;;)
    {
      try
      {
        SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Sending HighwayEvent for " + paramE.f());
        if (!this.i)
        {
          SoomlaUtils.LogError("SOOMLA GrowHighway", "Must call sendHighwayEvent after locking mSomeoneIsSendingEvents");
          return;
        }
        this.f.removeCallbacksAndMessages(null);
        if (!this.e.contains(paramE)) {
          this.e.add(paramE);
        }
        paramE = this.f;
        Runnable localRunnable = this.a;
        long l1;
        if (l())
        {
          l1 = this.g;
          paramE.postDelayed(localRunnable, l1);
          this.i = false;
        }
        else
        {
          l1 = 0L;
        }
      }
      finally {}
    }
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
        if (this.t)
        {
          SoomlaUtils.LogError("SOOMLA GrowHighway", "Highway already started. Can't start it twice.");
          return;
        }
        if (!HighwayConfig.getInstance().isInitialized())
        {
          SoomlaUtils.LogError("SOOMLA GrowHighway", "The service has not been configured. Did you call initialize... ?");
          continue;
        }
        this.t = true;
      }
      finally {}
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "GrowHighway starting for url: " + HighwayConfig.getInstance().getHighwayUrl());
      boolean bool = g();
      Iterator localIterator = b().iterator();
      while (localIterator.hasNext()) {
        ((d)localIterator.next()).a(bool);
      }
      BusProvider.getInstance().register(this);
      i();
      j();
      p();
    }
  }
  
  private void i()
  {
    try
    {
      if (this.j == null)
      {
        this.j = new Thread(new Runnable()
        {
          public void run()
          {
            SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Event consumer thread started listening to events...");
            while (!Thread.currentThread().isInterrupted()) {
              try
              {
                c.a(c.this, (e)c.a(c.this).take());
              }
              catch (InterruptedException localInterruptedException)
              {
                SoomlaUtils.LogError("SOOMLA GrowHighway", "Event consumer thread was interrupted! Terminating its work...");
                synchronized (c.this)
                {
                  c.a(c.this, null);
                  return;
                }
              }
            }
          }
        });
        this.j.start();
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
      if (this.k == null)
      {
        this.k = new Thread(new Runnable()
        {
          public void run()
          {
            SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Cache processor thread started listening to cache...");
            if (!Thread.currentThread().isInterrupted()) {
              try
              {
                synchronized (c.this)
                {
                  c.this.wait();
                  if (!c.b(c.this)) {
                    if (c.c(c.this)) {
                      break label87;
                    }
                  }
                }
                synchronized (c.this)
                {
                  c.b(c.this, null);
                  return;
                  c.d(c.this);
                }
              }
              catch (InterruptedException localInterruptedException)
              {
                SoomlaUtils.LogError("SOOMLA GrowHighway", "Cache processor thread was interrupted! Terminating its work...");
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
  
  /* Error */
  private boolean k()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: ldc -23
    //   6: ldc_w 697
    //   9: invokestatic 240	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   12: aload_0
    //   13: getfield 125	com/soomla/highway/c:i	Z
    //   16: ifne +20 -> 36
    //   19: aload_0
    //   20: iconst_1
    //   21: putfield 125	com/soomla/highway/c:i	Z
    //   24: ldc -23
    //   26: ldc_w 699
    //   29: invokestatic 240	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   32: aload_0
    //   33: monitorexit
    //   34: iload_1
    //   35: ireturn
    //   36: ldc -23
    //   38: ldc_w 701
    //   41: invokestatic 240	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   44: iconst_0
    //   45: istore_1
    //   46: goto -14 -> 32
    //   49: astore_2
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_2
    //   53: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	54	0	this	c
    //   1	45	1	bool	boolean
    //   49	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   4	32	49	finally
    //   36	44	49	finally
  }
  
  private boolean l()
  {
    return this.e.size() < this.h;
  }
  
  private void m()
  {
    SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Processing cached events");
    Queue localQueue = this.m.a(this.h);
    if (localQueue == null) {}
    for (;;)
    {
      return;
      for (e localE = (e)localQueue.poll(); (localE != null) && (b(localE)); localE = (e)localQueue.poll()) {}
    }
  }
  
  private boolean n()
  {
    Object localObject;
    try
    {
      JSONArray localJSONArray = new JSONArray();
      localObject = this.e.iterator();
      while (((Iterator)localObject).hasNext())
      {
        localJSONArray.put(((e)((Iterator)localObject).next()).b());
        continue;
        return false;
      }
    }
    catch (Exception localException)
    {
      SoomlaUtils.LogError("SOOMLA GrowHighway", "Unable to send Highway event: " + localException.toString());
    }
    for (;;)
    {
      localObject = h.d();
      ((JSONObject)localObject).put("events", localException);
      localObject = com.soomla.highway.b.c.a((JSONObject)localObject, HighwayConfig.getInstance().getFullHighwayUrl("carpool"));
      if (localObject != null)
      {
        if ((((HttpResponse)localObject).getStatusLine().getStatusCode() >= 200) && (((HttpResponse)localObject).getStatusLine().getStatusCode() <= 299))
        {
          SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Got a success response for sendEvent! " + localException);
          return true;
        }
        SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Something went wrong with sendEvent. Got response: " + ((HttpResponse)localObject).getStatusLine().getStatusCode() + " " + ((HttpResponse)localObject).getStatusLine().getReasonPhrase());
        a((HttpResponse)localObject);
      }
    }
  }
  
  private void o()
  {
    SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Adding valid Highway components");
    a(new com.soomla.highway.a.b());
    if (h.a()) {
      a(new com.soomla.highway.a.e());
    }
    if (h.b()) {
      a(new com.soomla.highway.a.d());
    }
    if (h.c()) {
      a(new com.soomla.highway.a.c());
    }
  }
  
  /* Error */
  @TargetApi(3)
  private void p()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 144	com/soomla/highway/c:s	Z
    //   6: ifeq +14 -> 20
    //   9: ldc -23
    //   11: ldc_w 779
    //   14: invokestatic 240	com/soomla/SoomlaUtils:LogDebug	(Ljava/lang/String;Ljava/lang/String;)V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: aload_0
    //   21: iconst_1
    //   22: putfield 144	com/soomla/highway/c:s	Z
    //   25: aload_0
    //   26: iconst_0
    //   27: putfield 134	com/soomla/highway/c:n	Z
    //   30: aload_0
    //   31: getfield 136	com/soomla/highway/c:o	Ljava/lang/String;
    //   34: astore_1
    //   35: aload_0
    //   36: aconst_null
    //   37: invokespecial 227	com/soomla/highway/c:d	(Ljava/lang/String;)V
    //   40: aload_0
    //   41: aload_1
    //   42: invokespecial 781	com/soomla/highway/c:c	(Ljava/lang/String;)Landroid/os/AsyncTask;
    //   45: iconst_1
    //   46: anewarray 783	java/lang/Object
    //   49: dup
    //   50: iconst_0
    //   51: ldc_w 785
    //   54: aastore
    //   55: invokestatic 788	com/soomla/highway/h:a	(Landroid/os/AsyncTask;[Ljava/lang/Object;)V
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
  private void q()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 154	com/soomla/highway/c:x	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 154	com/soomla/highway/c:x	Z
    //   19: new 242	java/lang/Thread
    //   22: dup
    //   23: new 26	com/soomla/highway/c$8
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 791	com/soomla/highway/c$8:<init>	(Lcom/soomla/highway/c;)V
    //   31: invokespecial 248	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   34: invokevirtual 251	java/lang/Thread:start	()V
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
  
  private Set<String> r()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = SoomlaApp.getAppContext().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((ApplicationInfo)localIterator.next()).packageName);
    }
    return localHashSet;
  }
  
  private void s()
  {
    SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Setting up broadcast receiver.");
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    SoomlaApp.getAppContext().registerReceiver(new com.soomla.highway.b.d(), localIntentFilter);
  }
  
  private void t()
  {
    for (;;)
    {
      try
      {
        boolean bool = this.t;
        if (!bool) {
          return;
        }
        this.t = false;
        this.m.e();
        v();
        Iterator localIterator = b().iterator();
        if (localIterator.hasNext())
        {
          ((d)localIterator.next()).a();
          continue;
        }
        BusProvider.getInstance().unregister(this);
      }
      finally {}
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Highway client was shutdown");
    }
  }
  
  private void u()
  {
    if (com.soomla.highway.b.d.a())
    {
      v();
      this.d = new Timer();
      this.d.schedule(new TimerTask()
      {
        public void run()
        {
          c.o(c.this);
        }
      }, 2000L);
    }
  }
  
  private void v()
  {
    if (this.d != null)
    {
      this.d.cancel();
      this.d = null;
    }
  }
  
  private boolean w()
  {
    Iterator localIterator = b().iterator();
    while (localIterator.hasNext()) {
      if (((a)localIterator.next()).b()) {
        return false;
      }
    }
    return true;
  }
  
  private void x()
  {
    Iterator localIterator = b().iterator();
    while (localIterator.hasNext()) {
      ((d)localIterator.next()).a(new Runnable()
      {
        public void run()
        {
          if ((c.p(c.this)) && (!c.q(c.this)))
          {
            c.f(c.this, true);
            BusProvider.getInstance().post(new HighwaySentMetaEvent());
          }
        }
      });
    }
  }
  
  private void y()
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
  private void z()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 196	com/soomla/highway/c:y	Lcom/soomla/data/KeyValueStorage;
    //   6: ldc_w 614
    //   9: invokevirtual 661	com/soomla/data/KeyValueStorage:get	(Ljava/lang/String;)Ljava/lang/String;
    //   12: astore_1
    //   13: aload_1
    //   14: invokestatic 224	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   17: ifeq +11 -> 28
    //   20: aload_0
    //   21: aconst_null
    //   22: putfield 136	com/soomla/highway/c:o	Ljava/lang/String;
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: aload_0
    //   29: aload_1
    //   30: putfield 136	com/soomla/highway/c:o	Ljava/lang/String;
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
  
  public String a(String paramString)
  {
    return "grow.highway." + paramString + "." + "profileId";
  }
  
  public void a(String paramString, JSONObject paramJSONObject)
  {
    a(paramString, paramJSONObject, "grow");
  }
  
  public void a(final String paramString1, final JSONObject paramJSONObject, final String paramString2)
  {
    SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Trying to send event: " + paramString1 + " with extra info: " + paramJSONObject.toString());
    new Thread(new Runnable()
    {
      public void run()
      {
        SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Trying to send event with name: " + paramString1);
        e localE = c.a(c.this, paramString1, paramJSONObject, paramString2);
        if (localE == null)
        {
          SoomlaUtils.LogError("SOOMLA GrowHighway", "(sendEvent) Couldn't build event dictionary. Stopping send!");
          return;
        }
        c.a(c.this).add(localE);
      }
    }).start();
  }
  
  @TargetApi(3)
  public void a(JSONObject paramJSONObject, a paramA)
  {
    if (!this.n)
    {
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Not sending metadata b/c HW is not connected!");
      return;
    }
    h.a(new AsyncTask()
    {
      private void a(JSONObject paramAnonymousJSONObject, c.a paramAnonymousA)
      {
        SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Trying to send meta-data");
        try
        {
          JSONObject localJSONObject = h.d();
          if (localJSONObject == null)
          {
            SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't send meta data because couldn't generate postData.");
            return;
          }
          Iterator localIterator = paramAnonymousJSONObject.keys();
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            localJSONObject.put(str, paramAnonymousJSONObject.get(str));
          }
          try
          {
            paramAnonymousJSONObject = com.soomla.highway.b.c.a(localJSONObject, HighwayConfig.getInstance().getFullHighwayUrl("metaAndState"));
            if (paramAnonymousJSONObject == null) {
              return;
            }
            if ((paramAnonymousJSONObject.getStatusLine().getStatusCode() >= 200) && (paramAnonymousJSONObject.getStatusLine().getStatusCode() <= 299))
            {
              SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Got a success response to sending meta data");
              paramAnonymousA.a();
              c.i(c.this);
              return;
            }
          }
          catch (Exception paramAnonymousJSONObject)
          {
            SoomlaUtils.LogError("SOOMLA GrowHighway", paramAnonymousJSONObject.toString());
            return;
          }
        }
        catch (JSONException paramAnonymousJSONObject)
        {
          SoomlaUtils.LogError("SOOMLA GrowHighway", "Couldn't generate HW JSON for sending meta-data. error: " + paramAnonymousJSONObject.getLocalizedMessage());
          return;
        }
        SoomlaUtils.LogDebug("SOOMLA GrowHighway", "Something went wrong with sending meta data got response: " + paramAnonymousJSONObject.getStatusLine().getStatusCode() + " " + paramAnonymousJSONObject.getStatusLine().getReasonPhrase());
        c.a(c.this, paramAnonymousJSONObject);
        paramAnonymousA.b();
      }
      
      protected Object doInBackground(Object... paramAnonymousVarArgs)
      {
        a((JSONObject)paramAnonymousVarArgs[0], (c.a)paramAnonymousVarArgs[1]);
        return null;
      }
    }, new Object[] { paramJSONObject, paramA });
  }
  
  public Collection<d> b()
  {
    return Collections.unmodifiableCollection(this.z);
  }
  
  public String c()
  {
    try
    {
      String str = this.o;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public g d()
  {
    try
    {
      g localG = this.p;
      return localG;
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
      boolean bool = this.r;
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
    return this.q;
  }
  
  public String getDBKeyForIntegration(String paramString)
  {
    return "grow.highway.integration.version." + paramString;
  }
  
  public KeyValueStorage getStorage()
  {
    return this.y;
  }
  
  public void initialize(String paramString1, String paramString2)
  {
    if (this.c)
    {
      SoomlaUtils.LogDebug("SOOMLA GrowHighway", "GrowHighway is already initialized.");
      return;
    }
    SoomlaUtils.LogDebug("SOOMLA GrowHighway", "GrowHighway initializing...");
    try
    {
      Class.forName("android.os.AsyncTask");
      HighwayConfig.getInstance().initialize(paramString1, paramString2);
      paramString2 = SoomlaApp.getAppContext().getSharedPreferences("store.verification.prefs", 0).edit();
      paramString2.putString("gameKey", paramString1);
      paramString2.commit();
      BusProvider.getInstance().register(this.A);
      o();
      s();
      this.c = true;
      a("hw_init", new JSONObject());
      a("hw_sess_start", new JSONObject());
      h();
      this.y.put("grow.highway.migrated", "YES");
      return;
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
      this.f.removeCallbacksAndMessages(null);
      if (!this.e.isEmpty()) {
        this.a.run();
      }
      B();
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
      p();
      a("hw_sess_start", new JSONObject());
    }
    this.v = false;
  }
  
  @Subscribe
  @TargetApi(3)
  public void onHighwayStatusNetworkConnectedEvent(HighwayNetworkConnectedEvent paramHighwayNetworkConnectedEvent)
  {
    if (SoomlaApp.ForegroundService.isBackground()) {
      return;
    }
    h.a(new AsyncTask()
    {
      protected Object doInBackground(Object... paramAnonymousVarArgs)
      {
        if (!c.c(c.this)) {
          c.o(c.this);
        }
        for (;;)
        {
          return null;
          c.l(c.this);
          c.i(c.this);
        }
      }
    }, new Object[] { "" });
  }
  
  @Subscribe
  public void onHighwayStatusNetworkDisconnectedEvent(HighwayNetworkDisconnectedEvent paramHighwayNetworkDisconnectedEvent)
  {
    this.n = false;
  }
  
  public static abstract interface a
  {
    public abstract void a();
    
    public abstract void b();
  }
}
