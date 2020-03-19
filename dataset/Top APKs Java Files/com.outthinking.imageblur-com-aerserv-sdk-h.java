package com.aerserv.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.aerserv.sdk.k.j;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h
{
  private static final String a = h.class.getSimpleName();
  private static final Object b = new Object();
  private static JSONObject c = null;
  private static Boolean d = null;
  private static Boolean e = null;
  private static Integer f = null;
  private static Boolean g = null;
  private static int h = 6000;
  private static Integer i = null;
  private static Integer j = null;
  private static Boolean k = null;
  private static String l = null;
  private static String m = null;
  private static AtomicBoolean n = new AtomicBoolean(false);
  
  public h() {}
  
  public static int a(Integer paramInteger)
  {
    k();
    int i1;
    if (paramInteger != null) {
      i1 = paramInteger.intValue();
    }
    for (;;)
    {
      return Math.max(1000, i1);
      if (i != null) {
        i1 = i.intValue();
      } else {
        i1 = 15000;
      }
    }
  }
  
  public static int a(Long paramLong)
  {
    int i1;
    if (paramLong != null) {
      i1 = paramLong.intValue();
    }
    for (;;)
    {
      return Math.max(1000, i1);
      k();
      if (j != null) {
        i1 = j.intValue();
      } else {
        i1 = 8000;
      }
    }
  }
  
  public static JSONObject a(Activity paramActivity, String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      l = paramString;
    }
    if (paramActivity != null) {}
    try
    {
      m = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0).packageName;
      if (c != null) {
        return c;
      }
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        com.aerserv.sdk.k.a.c(a, "Exception setting applicatioon ID: " + paramActivity.getMessage());
      }
      c = a(paramString);
      if (c == null) {
        c = new JSONObject();
      }
      if (c.has("analytics"))
      {
        paramActivity = c.optJSONObject("analytics");
        if (paramActivity == null) {}
      }
    }
    try
    {
      d = Boolean.valueOf(paramActivity.getBoolean("enabled"));
      paramActivity = c.optJSONObject("centralLogging");
      if (paramActivity == null) {}
    }
    catch (JSONException paramActivity)
    {
      try
      {
        e = Boolean.valueOf(paramActivity.getBoolean("enabled"));
      }
      catch (JSONException paramActivity)
      {
        try
        {
          f = Integer.valueOf(paramActivity.getInt("lineCount"));
        }
        catch (JSONException paramActivity)
        {
          try
          {
            g = Boolean.valueOf(paramActivity.getBoolean("sendStackTrace"));
            if (!c.has("loadTimeout")) {}
          }
          catch (JSONException paramActivity)
          {
            try
            {
              i = Integer.valueOf(c.getInt("loadTimeout"));
              if (!c.has("showTimeout")) {}
            }
            catch (JSONException paramActivity)
            {
              try
              {
                for (;;)
                {
                  j = Integer.valueOf(c.getInt("showTimeout"));
                  if (c.has("enableSimultaneous")) {
                    k = Boolean.valueOf(c.optBoolean("enableSimultaneous", true));
                  }
                  if (c.has("requestTimeout")) {
                    h = Math.min(i.intValue(), c.optInt("requestTimeout", h));
                  }
                  return c;
                  paramActivity = paramActivity;
                  com.aerserv.sdk.k.a.d(a, "Error reading analytics enabled setting: " + paramActivity.getMessage());
                  continue;
                  paramString = paramString;
                  com.aerserv.sdk.k.a.d(a, "Error reading Central Logging enabled setting: " + paramString.getMessage());
                  continue;
                  paramString = paramString;
                  com.aerserv.sdk.k.a.d(a, "Error reading Central Logging line count setting: " + paramString.getMessage());
                  continue;
                  paramActivity = paramActivity;
                  com.aerserv.sdk.k.a.d(a, "Error reading Central Logging send stack trace setting: " + paramActivity.getMessage());
                }
                paramActivity = paramActivity;
                com.aerserv.sdk.k.a.d(a, "Error reading loadAdTimeout: " + paramActivity.getMessage());
              }
              catch (JSONException paramActivity)
              {
                for (;;)
                {
                  com.aerserv.sdk.k.a.d(a, "Error reading showAdTimeout: " + paramActivity.getMessage());
                }
              }
            }
          }
        }
      }
    }
  }
  
  /* Error */
  @android.annotation.TargetApi(14)
  private static JSONObject a(String paramString)
  {
    // Byte code:
    //   0: bipush 14
    //   2: invokestatic 224	com/aerserv/sdk/k/q:a	(I)Z
    //   5: ifne +17 -> 22
    //   8: new 148	org/json/JSONObject
    //   11: dup
    //   12: invokespecial 149	org/json/JSONObject:<init>	()V
    //   15: putstatic 44	com/aerserv/sdk/h:c	Lorg/json/JSONObject;
    //   18: getstatic 44	com/aerserv/sdk/h:c	Lorg/json/JSONObject;
    //   21: areturn
    //   22: getstatic 42	com/aerserv/sdk/h:b	Ljava/lang/Object;
    //   25: astore_2
    //   26: aload_2
    //   27: monitorenter
    //   28: getstatic 44	com/aerserv/sdk/h:c	Lorg/json/JSONObject;
    //   31: ifnull +16 -> 47
    //   34: getstatic 44	com/aerserv/sdk/h:c	Lorg/json/JSONObject;
    //   37: astore_0
    //   38: aload_2
    //   39: monitorexit
    //   40: aload_0
    //   41: areturn
    //   42: astore_0
    //   43: aload_2
    //   44: monitorexit
    //   45: aload_0
    //   46: athrow
    //   47: new 148	org/json/JSONObject
    //   50: dup
    //   51: invokespecial 149	org/json/JSONObject:<init>	()V
    //   54: putstatic 44	com/aerserv/sdk/h:c	Lorg/json/JSONObject;
    //   57: new 148	org/json/JSONObject
    //   60: dup
    //   61: invokespecial 149	org/json/JSONObject:<init>	()V
    //   64: astore_3
    //   65: aload_3
    //   66: ldc -30
    //   68: ldc -28
    //   70: invokevirtual 232	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   73: pop
    //   74: aload_3
    //   75: ldc -22
    //   77: ldc -20
    //   79: invokevirtual 232	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   82: pop
    //   83: aload_0
    //   84: ifnull +23 -> 107
    //   87: ldc -18
    //   89: aload_0
    //   90: invokevirtual 243	java/lang/String:trim	()Ljava/lang/String;
    //   93: invokevirtual 247	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   96: ifne +11 -> 107
    //   99: aload_3
    //   100: ldc -7
    //   102: aload_0
    //   103: invokevirtual 232	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   106: pop
    //   107: new 251	com/aerserv/sdk/f/a
    //   110: dup
    //   111: ldc -3
    //   113: aload_3
    //   114: invokevirtual 254	org/json/JSONObject:toString	()Ljava/lang/String;
    //   117: invokespecial 256	com/aerserv/sdk/f/a:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: astore_0
    //   121: aload_0
    //   122: getstatic 262	android/os/AsyncTask:THREAD_POOL_EXECUTOR	Ljava/util/concurrent/Executor;
    //   125: iconst_0
    //   126: anewarray 264	java/lang/Void
    //   129: invokevirtual 268	com/aerserv/sdk/f/a:executeOnExecutor	(Ljava/util/concurrent/Executor;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   132: pop
    //   133: aload_0
    //   134: invokevirtual 272	com/aerserv/sdk/f/a:get	()Ljava/lang/Object;
    //   137: checkcast 240	java/lang/String
    //   140: astore_0
    //   141: aload_0
    //   142: invokestatic 103	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   145: istore_1
    //   146: iload_1
    //   147: ifne +14 -> 161
    //   150: new 148	org/json/JSONObject
    //   153: dup
    //   154: aload_0
    //   155: invokespecial 275	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   158: putstatic 44	com/aerserv/sdk/h:c	Lorg/json/JSONObject;
    //   161: getstatic 44	com/aerserv/sdk/h:c	Lorg/json/JSONObject;
    //   164: astore_0
    //   165: aload_2
    //   166: monitorexit
    //   167: aload_0
    //   168: areturn
    //   169: astore_0
    //   170: getstatic 37	com/aerserv/sdk/h:a	Ljava/lang/String;
    //   173: new 125	java/lang/StringBuilder
    //   176: dup
    //   177: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   180: ldc_w 277
    //   183: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload_0
    //   187: invokevirtual 135	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   190: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 138	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: aload_0
    //   197: invokestatic 280	com/aerserv/sdk/k/a:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   200: getstatic 44	com/aerserv/sdk/h:c	Lorg/json/JSONObject;
    //   203: astore_0
    //   204: aload_2
    //   205: monitorexit
    //   206: aload_0
    //   207: areturn
    //   208: astore_0
    //   209: getstatic 37	com/aerserv/sdk/h:a	Ljava/lang/String;
    //   212: new 125	java/lang/StringBuilder
    //   215: dup
    //   216: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   219: ldc_w 282
    //   222: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: aload_0
    //   226: invokevirtual 204	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   229: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: invokevirtual 138	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: invokestatic 206	com/aerserv/sdk/k/a:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   238: goto -77 -> 161
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	241	0	paramString	String
    //   145	2	1	bool	boolean
    //   25	180	2	localObject	Object
    //   64	50	3	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   28	40	42	finally
    //   43	45	42	finally
    //   47	57	42	finally
    //   57	83	42	finally
    //   87	107	42	finally
    //   107	141	42	finally
    //   141	146	42	finally
    //   150	161	42	finally
    //   161	167	42	finally
    //   170	206	42	finally
    //   209	238	42	finally
    //   57	83	169	java/lang/Exception
    //   87	107	169	java/lang/Exception
    //   107	141	169	java/lang/Exception
    //   150	161	208	org/json/JSONException
  }
  
  public static void a(Context paramContext)
  {
    if (((paramContext instanceof Activity)) && (n.compareAndSet(false, true)))
    {
      k();
      paramContext = (Activity)paramContext;
      final JSONArray localJSONArray = c.optJSONArray("update");
      if ((localJSONArray != null) && (localJSONArray.length() > 0)) {
        new Thread(new Runnable()
        {
          private List<PackageInfo> a()
          {
            return this.a.getPackageManager().getInstalledPackages(0);
          }
          
          private JSONObject a(PackageInfo paramAnonymousPackageInfo)
          {
            JSONObject localJSONObject = new JSONObject();
            localJSONObject.put("bundleId", paramAnonymousPackageInfo.applicationInfo.packageName);
            return localJSONObject;
          }
          
          private JSONObject a(JSONObject paramAnonymousJSONObject)
          {
            com.aerserv.sdk.k.a.a(h.j(), "Getting device information.");
            JSONArray localJSONArray = new JSONArray();
            Iterator localIterator = a().iterator();
            while (localIterator.hasNext())
            {
              PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
              if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
                localJSONArray.put(a(localPackageInfo));
              }
            }
            paramAnonymousJSONObject.put("installed", localJSONArray);
            return paramAnonymousJSONObject;
          }
          
          public void run()
          {
            for (;;)
            {
              String str;
              JSONObject localJSONObject;
              int i;
              try
              {
                h.a(new String[] { "com.google.android.gms.ads.identifier.AdvertisingIdClient", "com.google.android.gms.ads.identifier.AdvertisingIdClient$Info" });
                Object localObject = AdvertisingIdClient.getAdvertisingIdInfo(this.a);
                if (((AdvertisingIdClient.Info)localObject).isLimitAdTrackingEnabled())
                {
                  com.aerserv.sdk.k.a.a(h.j(), "Updating information is disabled.");
                  return;
                }
                str = ((AdvertisingIdClient.Info)localObject).getId();
                localJSONObject = new JSONObject();
                HashSet localHashSet = new HashSet();
                i = 0;
                if (i < localJSONArray.length())
                {
                  if (localJSONArray.isNull(i))
                  {
                    localObject = null;
                    if ((TextUtils.isEmpty((CharSequence)localObject)) || (localHashSet.contains(localObject))) {
                      break label254;
                    }
                    localHashSet.add(localObject);
                    if (((String)localObject).equals("apps"))
                    {
                      com.aerserv.sdk.k.a.a(h.j(), "Updating device informations for apps.");
                      localObject = new JSONObject();
                      a((JSONObject)localObject);
                      localJSONObject.put("apps", localObject);
                      break label254;
                    }
                  }
                  else
                  {
                    localObject = localJSONArray.optString(i);
                    continue;
                  }
                  com.aerserv.sdk.k.a.c(h.j(), "Unsupported field to update: " + (String)localObject);
                }
              }
              catch (Exception localException)
              {
                com.aerserv.sdk.k.a.b(h.j(), "Error getting device information.", localException);
                return;
              }
              if (localJSONObject.length() > 0)
              {
                localJSONObject.put("adId", str);
                new com.aerserv.sdk.f.a("https://dmp.aerserv.com/update", localJSONObject.toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
              }
              return;
              label254:
              i += 1;
            }
          }
        }).start();
      }
    }
    else
    {
      return;
    }
    com.aerserv.sdk.k.a.a(a, "Cannot update advertising information.");
  }
  
  public static boolean a()
  {
    k();
    if (d != null) {
      return d.booleanValue();
    }
    return false;
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    k();
    if (!a()) {
      return false;
    }
    Object localObject1 = c.optJSONObject("analytics");
    if (localObject1 != null)
    {
      localObject1 = ((JSONObject)localObject1).optJSONArray("disabledEvents");
      if (localObject1 != null)
      {
        int i1 = 0;
        for (;;)
        {
          if (i1 >= ((JSONArray)localObject1).length()) {
            break label103;
          }
          Object localObject2 = ((JSONArray)localObject1).optJSONObject(i1);
          String str = ((JSONObject)localObject2).optString("category", "");
          localObject2 = ((JSONObject)localObject2).optString("action", "");
          if ((str.equals(paramString1)) && (((String)localObject2).equals(paramString2))) {
            break;
          }
          i1 += 1;
        }
      }
    }
    label103:
    return true;
  }
  
  private static void b(String... paramVarArgs)
  {
    int i2 = paramVarArgs.length;
    int i1 = 0;
    while (i1 < i2)
    {
      String str = paramVarArgs[i1];
      if (!j.a(str)) {
        throw new IllegalStateException("Could not find library: " + str + ".");
      }
      i1 += 1;
    }
  }
  
  public static boolean b()
  {
    if (e != null) {
      return e.booleanValue();
    }
    return true;
  }
  
  public static int c()
  {
    if (f != null) {
      return f.intValue();
    }
    return 500;
  }
  
  public static boolean d()
  {
    if (g != null) {
      return g.booleanValue();
    }
    return true;
  }
  
  public static int e()
  {
    return h;
  }
  
  public static boolean f()
  {
    if (k == null) {
      return true;
    }
    return k.booleanValue();
  }
  
  public static JSONObject g()
  {
    return c.optJSONObject("vpaid");
  }
  
  public static String h()
  {
    return l;
  }
  
  public static String i()
  {
    return m;
  }
  
  private static JSONObject k()
  {
    return a(null, "");
  }
}
