package com.iflytek.cloud.thirdparty;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.Version;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class cd
{
  private static cd a = null;
  private static Context b = null;
  private static SharedPreferences c;
  private boolean d = false;
  private boolean e = false;
  private boolean f = false;
  private boolean g = false;
  private boolean h = false;
  private long i = 0L;
  private long j = 0L;
  private long k = 0L;
  private long l = 0L;
  private long m = 0L;
  private long n = 43200L;
  private by.a o = new by.a()
  {
    public void a(SpeechError paramAnonymousSpeechError)
    {
      cd.b(cd.this, false);
      cb.d("CollectInfo", "" + paramAnonymousSpeechError.getErrorCode());
    }
    
    /* Error */
    public void a(by paramAnonymousBy, byte[] paramAnonymousArrayOfByte)
    {
      // Byte code:
      //   0: aload_2
      //   1: ifnull +216 -> 217
      //   4: new 58	org/json/JSONObject
      //   7: dup
      //   8: aload_2
      //   9: invokestatic 64	com/iflytek/cloud/thirdparty/bu:c	([B)[B
      //   12: ldc 66
      //   14: invokestatic 72	org/apache/http/util/EncodingUtils:getString	([BLjava/lang/String;)Ljava/lang/String;
      //   17: invokespecial 75	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   20: astore_1
      //   21: ldc 25
      //   23: new 27	java/lang/StringBuilder
      //   26: dup
      //   27: invokespecial 28	java/lang/StringBuilder:<init>	()V
      //   30: ldc 77
      //   32: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   35: aload_1
      //   36: invokevirtual 78	org/json/JSONObject:toString	()Ljava/lang/String;
      //   39: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   42: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   45: invokestatic 53	com/iflytek/cloud/thirdparty/cb:d	(Ljava/lang/String;Ljava/lang/String;)V
      //   48: ldc 80
      //   50: aload_1
      //   51: ldc 82
      //   53: invokevirtual 86	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
      //   56: invokevirtual 92	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   59: ifeq +168 -> 227
      //   62: aload_0
      //   63: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   66: iconst_1
      //   67: invokestatic 94	com/iflytek/cloud/thirdparty/cd:a	(Lcom/iflytek/cloud/thirdparty/cd;Z)Z
      //   70: pop
      //   71: aload_0
      //   72: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   75: aload_1
      //   76: ldc 96
      //   78: invokevirtual 86	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
      //   81: invokestatic 102	java/lang/Double:parseDouble	(Ljava/lang/String;)D
      //   84: ldc2_w 103
      //   87: dmul
      //   88: d2l
      //   89: invokestatic 107	com/iflytek/cloud/thirdparty/cd:a	(Lcom/iflytek/cloud/thirdparty/cd;J)J
      //   92: pop2
      //   93: aload_0
      //   94: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   97: aload_1
      //   98: ldc 109
      //   100: invokevirtual 86	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
      //   103: invokestatic 102	java/lang/Double:parseDouble	(Ljava/lang/String;)D
      //   106: ldc2_w 103
      //   109: dmul
      //   110: d2l
      //   111: invokestatic 111	com/iflytek/cloud/thirdparty/cd:b	(Lcom/iflytek/cloud/thirdparty/cd;J)J
      //   114: pop2
      //   115: aload_0
      //   116: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   119: aload_1
      //   120: ldc 113
      //   122: invokevirtual 86	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
      //   125: invokestatic 102	java/lang/Double:parseDouble	(Ljava/lang/String;)D
      //   128: ldc2_w 103
      //   131: dmul
      //   132: d2l
      //   133: invokestatic 115	com/iflytek/cloud/thirdparty/cd:c	(Lcom/iflytek/cloud/thirdparty/cd;J)J
      //   136: pop2
      //   137: invokestatic 118	com/iflytek/cloud/thirdparty/cd:c	()Landroid/content/SharedPreferences;
      //   140: invokeinterface 124 1 0
      //   145: astore_1
      //   146: aload_1
      //   147: ldc 82
      //   149: aload_0
      //   150: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   153: invokestatic 127	com/iflytek/cloud/thirdparty/cd:c	(Lcom/iflytek/cloud/thirdparty/cd;)Z
      //   156: invokeinterface 133 3 0
      //   161: pop
      //   162: aload_1
      //   163: ldc 96
      //   165: aload_0
      //   166: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   169: invokestatic 136	com/iflytek/cloud/thirdparty/cd:d	(Lcom/iflytek/cloud/thirdparty/cd;)J
      //   172: invokeinterface 140 4 0
      //   177: pop
      //   178: aload_1
      //   179: ldc 109
      //   181: aload_0
      //   182: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   185: invokestatic 143	com/iflytek/cloud/thirdparty/cd:e	(Lcom/iflytek/cloud/thirdparty/cd;)J
      //   188: invokeinterface 140 4 0
      //   193: pop
      //   194: aload_1
      //   195: ldc 113
      //   197: aload_0
      //   198: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   201: invokestatic 146	com/iflytek/cloud/thirdparty/cd:f	(Lcom/iflytek/cloud/thirdparty/cd;)J
      //   204: invokeinterface 140 4 0
      //   209: pop
      //   210: aload_1
      //   211: invokeinterface 150 1 0
      //   216: pop
      //   217: aload_0
      //   218: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   221: iconst_0
      //   222: invokestatic 23	com/iflytek/cloud/thirdparty/cd:b	(Lcom/iflytek/cloud/thirdparty/cd;Z)Z
      //   225: pop
      //   226: return
      //   227: aload_0
      //   228: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   231: iconst_0
      //   232: invokestatic 94	com/iflytek/cloud/thirdparty/cd:a	(Lcom/iflytek/cloud/thirdparty/cd;Z)Z
      //   235: pop
      //   236: goto -165 -> 71
      //   239: astore_1
      //   240: aload_1
      //   241: invokestatic 153	com/iflytek/cloud/thirdparty/cb:b	(Ljava/lang/Throwable;)V
      //   244: aload_0
      //   245: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   248: iconst_0
      //   249: invokestatic 23	com/iflytek/cloud/thirdparty/cd:b	(Lcom/iflytek/cloud/thirdparty/cd;Z)Z
      //   252: pop
      //   253: return
      //   254: astore_1
      //   255: aload_0
      //   256: getfield 14	com/iflytek/cloud/thirdparty/cd$3:a	Lcom/iflytek/cloud/thirdparty/cd;
      //   259: iconst_0
      //   260: invokestatic 23	com/iflytek/cloud/thirdparty/cd:b	(Lcom/iflytek/cloud/thirdparty/cd;Z)Z
      //   263: pop
      //   264: aload_1
      //   265: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	266	0	this	3
      //   0	266	1	paramAnonymousBy	by
      //   0	266	2	paramAnonymousArrayOfByte	byte[]
      // Exception table:
      //   from	to	target	type
      //   4	71	239	java/lang/Throwable
      //   71	217	239	java/lang/Throwable
      //   227	236	239	java/lang/Throwable
      //   4	71	254	finally
      //   71	217	254	finally
      //   227	236	254	finally
      //   240	244	254	finally
    }
  };
  private by.a p = new by.a()
  {
    public void a(SpeechError paramAnonymousSpeechError)
    {
      cd.c(cd.this, false);
      cb.d("CollectInfo", "" + paramAnonymousSpeechError.getErrorCode());
    }
    
    public void a(by paramAnonymousBy, byte[] paramAnonymousArrayOfByte)
    {
      if (paramAnonymousArrayOfByte != null) {}
      try
      {
        paramAnonymousBy = EncodingUtils.getString(bu.c(paramAnonymousArrayOfByte), "utf-8");
        cb.d("CollectInfo", "上传数据结果返回： " + paramAnonymousBy);
        return;
      }
      catch (Throwable paramAnonymousBy)
      {
        cb.b(paramAnonymousBy);
        return;
      }
      finally
      {
        cd.c(cd.this, false);
      }
    }
  };
  
  private cd(Context paramContext)
  {
    if (paramContext != null)
    {
      b = paramContext.getApplicationContext();
      paramContext = new StringBuilder("iflytek_state_");
      paramContext.append(b.getPackageName());
      c = b.getSharedPreferences(paramContext.toString(), 0);
      this.h = c.getBoolean("is_collect", false);
      this.i = c.getLong("ti_request", 0L);
      this.j = c.getLong("ti_app_list", this.n);
      this.l = c.getLong("list_app_time", 0L);
      this.k = c.getLong("ti_app_active", this.n);
      this.m = c.getLong("active_app_time", 0L);
    }
  }
  
  public static cd a(Context paramContext)
  {
    if (a == null) {
      a = new cd(paramContext);
    }
    return a;
  }
  
  private static JSONObject a(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("header", paramJSONObject2);
      localJSONObject.put("body", paramJSONObject1);
      return localJSONObject;
    }
    catch (Throwable paramJSONObject1)
    {
      cb.b(paramJSONObject1);
    }
    return localJSONObject;
  }
  
  private static JSONObject a(boolean paramBoolean, ce paramCe, String paramString)
  {
    JSONObject localJSONObject1 = new JSONObject();
    localJSONObject2 = new JSONObject();
    paramCe = paramCe.c().entrySet().iterator();
    try
    {
      while (paramCe.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramCe.next();
        localJSONObject2.put((String)localEntry.getKey(), localEntry.getValue());
      }
      return localJSONObject2;
    }
    catch (Throwable paramCe)
    {
      cb.b(paramCe);
      while (paramBoolean)
      {
        return localJSONObject1;
        localJSONObject1.put(paramString, localJSONObject2);
      }
    }
  }
  
  private void a(JSONObject paramJSONObject)
  {
    try
    {
      if (bz.b(b))
      {
        paramJSONObject = paramJSONObject.toString().getBytes("utf-8");
        byte[] arrayOfByte = bu.b(paramJSONObject);
        by localBy = new by();
        localBy.b(20000);
        localBy.a(1);
        localBy.a("http://scs.openspeech.cn/scs", "cmd=statsdklog&logver=1.0.2&size=" + paramJSONObject.length, arrayOfByte);
        localBy.a(this.p);
        return;
      }
      this.e = false;
      return;
    }
    catch (Throwable paramJSONObject)
    {
      this.e = false;
      cb.b(paramJSONObject);
    }
  }
  
  private static JSONObject b(Context paramContext)
  {
    Object localObject = bp.b(paramContext).b();
    cg.a(paramContext, (ce)localObject);
    ((ce)localObject).a("appid", cg.a());
    ((ce)localObject).a("unique_id", ca.a(paramContext));
    ((ce)localObject).a("src", "msc");
    ((ce)localObject).a("ver", Version.getVersion());
    ((ce)localObject).a("lang", Locale.getDefault().getLanguage());
    ((ce)localObject).a("logtime", "" + System.currentTimeMillis());
    localObject = a(false, (ce)localObject, "header");
    try
    {
      DecimalFormat localDecimalFormat = new DecimalFormat("#.########");
      ((JSONObject)localObject).put("lat", localDecimalFormat.format(br.a(paramContext).a("msc.lat")));
      ((JSONObject)localObject).put("lng", localDecimalFormat.format(br.a(paramContext).a("msc.lng")));
      return localObject;
    }
    catch (Throwable paramContext)
    {
      cb.b(paramContext);
    }
    return localObject;
  }
  
  private boolean d()
  {
    try
    {
      long l1 = c.getLong("ti_request", 0L);
      long l2 = c.getLong("request_time", 0L);
      long l3 = System.currentTimeMillis() / 1000L;
      return l3 - l2 > l1;
    }
    catch (Throwable localThrowable)
    {
      cb.b(localThrowable);
    }
    return true;
  }
  
  private void e()
  {
    try
    {
      Object localObject = new JSONObject();
      ((JSONObject)localObject).put("pver", "3");
      ((JSONObject)localObject).put("type", "app_list");
      ((JSONObject)localObject).put("appid", cg.a());
      ((JSONObject)localObject).put("src", "msc");
      cb.d("CollectInfo", ((JSONObject)localObject).toString());
      if (bz.b(b))
      {
        localObject = bu.b(((JSONObject)localObject).toString().getBytes("utf-8"));
        by localBy = new by();
        localBy.b(20000);
        localBy.a(1);
        localBy.a("http://data.openspeech.cn/index.php/clientrequest/clientcollect/isCollect", "", (byte[])localObject);
        localBy.a(this.o);
        localObject = c.edit();
        ((SharedPreferences.Editor)localObject).putLong("request_time", System.currentTimeMillis() / 1000L);
        ((SharedPreferences.Editor)localObject).commit();
        return;
      }
      this.d = false;
      return;
    }
    catch (Throwable localThrowable)
    {
      this.d = false;
      cb.b(localThrowable);
    }
  }
  
  private boolean f()
  {
    if (!this.h) {
      return false;
    }
    long l1 = System.currentTimeMillis() / 1000L;
    if (l1 - this.l > this.j)
    {
      bool = true;
      label33:
      this.f = bool;
      if (l1 - this.m <= this.k) {
        break label80;
      }
    }
    label80:
    for (boolean bool = true;; bool = false)
    {
      this.g = bool;
      if ((!this.f) && (!this.g)) {
        break;
      }
      return true;
      bool = false;
      break label33;
    }
  }
  
  private void g()
  {
    Object localObject1 = c.edit();
    if (this.f)
    {
      this.l = (System.currentTimeMillis() / 1000L);
      cb.d("CollectInfo", "lastListAppTime:" + this.l);
      ((SharedPreferences.Editor)localObject1).putLong("list_app_time", this.l);
    }
    if (this.g)
    {
      this.m = (System.currentTimeMillis() / 1000L);
      cb.d("CollectInfo", "lastActiveAppTime:" + this.m);
      ((SharedPreferences.Editor)localObject1).putLong("active_app_time", this.m);
    }
    ((SharedPreferences.Editor)localObject1).commit();
    try
    {
      localObject1 = new JSONArray();
      JSONObject localJSONObject;
      if (this.f)
      {
        localObject2 = h();
        if (localObject2 != null)
        {
          localJSONObject = new JSONObject();
          localJSONObject.put("appinfo", localObject2);
          localJSONObject.put("ts", System.currentTimeMillis());
          ((JSONArray)localObject1).put(localJSONObject);
        }
      }
      if (this.g)
      {
        localObject2 = i();
        if (localObject2 != null)
        {
          localJSONObject = new JSONObject();
          localJSONObject.put("hisinfo", localObject2);
          localJSONObject.put("ts", System.currentTimeMillis());
          ((JSONArray)localObject1).put(localJSONObject);
        }
      }
      Object localObject2 = new JSONObject();
      ((JSONObject)localObject2).put("log", localObject1);
      localObject1 = a((JSONObject)localObject2, b(b));
      cb.d("CollectInfo", ((JSONObject)localObject1).toString());
      a((JSONObject)localObject1);
      return;
    }
    catch (Throwable localThrowable)
    {
      this.e = false;
      cb.b(localThrowable);
    }
  }
  
  private JSONArray h()
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      PackageManager localPackageManager = b.getPackageManager();
      List localList = localPackageManager.getInstalledPackages(0);
      int i2 = localList.size();
      int i1 = 0;
      while (i1 < i2)
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i1);
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put(localPackageInfo.packageName, localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localJSONArray.put(localJSONObject);
        }
        i1 += 1;
      }
      return localThrowable;
    }
    catch (Throwable localThrowable)
    {
      cb.b(localThrowable);
      return null;
    }
  }
  
  private JSONArray i()
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      PackageManager localPackageManager = b.getPackageManager();
      Iterator localIterator = ((ActivityManager)b.getSystemService("activity")).getRecentTasks(20, 1).iterator();
      while (localIterator.hasNext())
      {
        ResolveInfo localResolveInfo = localPackageManager.resolveActivity(((ActivityManager.RecentTaskInfo)localIterator.next()).baseIntent, 0);
        if (localResolveInfo != null)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put(localResolveInfo.activityInfo.packageName, localResolveInfo.loadLabel(localPackageManager).toString());
          localJSONArray.put(localJSONObject);
        }
      }
      return localThrowable;
    }
    catch (Throwable localThrowable)
    {
      cb.b(localThrowable);
      return null;
    }
  }
  
  public void a()
  {
    for (;;)
    {
      try
      {
        boolean bool = this.d;
        if (bool) {
          return;
        }
        this.d = true;
        if (d()) {
          new Thread(new Runnable()
          {
            public void run()
            {
              cd.a(cd.this);
            }
          }).start();
        } else {
          this.d = false;
        }
      }
      finally {}
    }
  }
  
  public void b()
  {
    for (;;)
    {
      try
      {
        boolean bool = this.e;
        if (bool) {
          return;
        }
        this.e = true;
        if (f()) {
          new Thread(new Runnable()
          {
            public void run()
            {
              cd.b(cd.this);
            }
          }).start();
        } else {
          this.e = false;
        }
      }
      finally {}
    }
  }
}
