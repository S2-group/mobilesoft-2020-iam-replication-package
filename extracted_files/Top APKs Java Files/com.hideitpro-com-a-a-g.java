package com.a.a;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.text.TextUtils;
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

public class g
{
  private static final String a = g.class.getSimpleName();
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
  
  public g() {}
  
  public static int a(Integer paramInteger)
  {
    a("");
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
      a("");
      if (j != null) {
        i1 = j.intValue();
      } else {
        i1 = 8000;
      }
    }
  }
  
  private static JSONObject a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      l = paramString;
    }
    if (c != null) {
      return c;
    }
    paramString = b(paramString);
    c = paramString;
    if (paramString == null) {
      c = new JSONObject();
    }
    if (c.has("analytics"))
    {
      paramString = c.optJSONObject("analytics");
      if (paramString == null) {}
    }
    try
    {
      d = Boolean.valueOf(paramString.getBoolean("enabled"));
      paramString = c.optJSONObject("centralLogging");
      if (paramString == null) {}
    }
    catch (JSONException paramString)
    {
      try
      {
        e = Boolean.valueOf(paramString.getBoolean("enabled"));
      }
      catch (JSONException paramString)
      {
        try
        {
          f = Integer.valueOf(paramString.getInt("lineCount"));
        }
        catch (JSONException paramString)
        {
          try
          {
            g = Boolean.valueOf(paramString.getBoolean("sendStackTrace"));
            if (!c.has("loadTimeout")) {}
          }
          catch (JSONException paramString)
          {
            try
            {
              i = Integer.valueOf(c.getInt("loadTimeout"));
              if (!c.has("showTimeout")) {}
            }
            catch (JSONException paramString)
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
                  paramString = paramString;
                  com.a.a.j.a.d(a, "Error reading analytics enabled setting: " + paramString.getMessage());
                  continue;
                  localJSONException1 = localJSONException1;
                  com.a.a.j.a.d(a, "Error reading Central Logging enabled setting: " + localJSONException1.getMessage());
                  continue;
                  localJSONException2 = localJSONException2;
                  com.a.a.j.a.d(a, "Error reading Central Logging line count setting: " + localJSONException2.getMessage());
                  continue;
                  paramString = paramString;
                  com.a.a.j.a.d(a, "Error reading Central Logging send stack trace setting: " + paramString.getMessage());
                }
                paramString = paramString;
                com.a.a.j.a.d(a, "Error reading loadAdTimeout: " + paramString.getMessage());
              }
              catch (JSONException paramString)
              {
                for (;;)
                {
                  com.a.a.j.a.d(a, "Error reading showAdTimeout: " + paramString.getMessage());
                }
              }
            }
          }
        }
      }
    }
  }
  
  public static void a(Context paramContext)
  {
    if (((paramContext instanceof Activity)) && (n.compareAndSet(false, true)))
    {
      a("");
      paramContext = (Activity)paramContext;
      final JSONArray localJSONArray = c.optJSONArray("update");
      if ((localJSONArray != null) && (localJSONArray.length() > 0)) {
        new Thread(new Runnable()
        {
          private JSONObject a(JSONObject paramAnonymousJSONObject)
          {
            com.a.a.j.a.a(g.j(), "Getting device information.");
            JSONArray localJSONArray = new JSONArray();
            Iterator localIterator = this.a.getPackageManager().getInstalledPackages(0).iterator();
            while (localIterator.hasNext())
            {
              PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
              if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
              {
                JSONObject localJSONObject = new JSONObject();
                localJSONObject.put("bundleId", localPackageInfo.applicationInfo.packageName);
                localJSONArray.put(localJSONObject);
              }
            }
            paramAnonymousJSONObject.put("installed", localJSONArray);
            return paramAnonymousJSONObject;
          }
          
          public final void run()
          {
            for (;;)
            {
              String str;
              JSONObject localJSONObject;
              int i;
              try
              {
                g.a(new String[] { "com.google.android.gms.ads.identifier.AdvertisingIdClient", "com.google.android.gms.ads.identifier.AdvertisingIdClient$Info" });
                Object localObject = AdvertisingIdClient.getAdvertisingIdInfo(this.a);
                if (((AdvertisingIdClient.Info)localObject).isLimitAdTrackingEnabled())
                {
                  com.a.a.j.a.a(g.j(), "Updating information is disabled.");
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
                      break label251;
                    }
                    localHashSet.add(localObject);
                    if (((String)localObject).equals("apps"))
                    {
                      com.a.a.j.a.a(g.j(), "Updating device informations for apps.");
                      localObject = new JSONObject();
                      a((JSONObject)localObject);
                      localJSONObject.put("apps", localObject);
                      break label251;
                    }
                  }
                  else
                  {
                    localObject = localJSONArray.optString(i);
                    continue;
                  }
                  com.a.a.j.a.c(g.j(), "Unsupported field to update: " + (String)localObject);
                }
              }
              catch (Exception localException)
              {
                com.a.a.j.a.b(g.j(), "Error getting device information.", localException);
                return;
              }
              if (localJSONObject.length() > 0)
              {
                localJSONObject.put("adId", str);
                new com.a.a.f.a("https://dmp.aerserv.com/update", localJSONObject.toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
              }
              return;
              label251:
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
    com.a.a.j.a.a(a, "Cannot update advertising information.");
  }
  
  public static boolean a()
  {
    a("");
    if (d != null) {
      return d.booleanValue();
    }
    return false;
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    a("");
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
  
  /* Error */
  @android.annotation.TargetApi(14)
  private static JSONObject b(String paramString)
  {
    // Byte code:
    //   0: bipush 14
    //   2: invokestatic 272	com/a/a/j/o:a	(I)Z
    //   5: ifne +17 -> 22
    //   8: new 106	org/json/JSONObject
    //   11: dup
    //   12: invokespecial 107	org/json/JSONObject:<init>	()V
    //   15: astore_0
    //   16: aload_0
    //   17: putstatic 44	com/a/a/g:c	Lorg/json/JSONObject;
    //   20: aload_0
    //   21: areturn
    //   22: getstatic 42	com/a/a/g:b	Ljava/lang/Object;
    //   25: astore_2
    //   26: aload_2
    //   27: monitorenter
    //   28: getstatic 44	com/a/a/g:c	Lorg/json/JSONObject;
    //   31: ifnull +16 -> 47
    //   34: getstatic 44	com/a/a/g:c	Lorg/json/JSONObject;
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
    //   47: new 106	org/json/JSONObject
    //   50: dup
    //   51: invokespecial 107	org/json/JSONObject:<init>	()V
    //   54: putstatic 44	com/a/a/g:c	Lorg/json/JSONObject;
    //   57: new 106	org/json/JSONObject
    //   60: dup
    //   61: invokespecial 107	org/json/JSONObject:<init>	()V
    //   64: astore_3
    //   65: aload_3
    //   66: ldc_w 274
    //   69: ldc_w 276
    //   72: invokevirtual 280	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   75: pop
    //   76: aload_3
    //   77: ldc_w 282
    //   80: ldc_w 284
    //   83: invokevirtual 280	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   86: pop
    //   87: aload_0
    //   88: ifnull +24 -> 112
    //   91: ldc 75
    //   93: aload_0
    //   94: invokevirtual 287	java/lang/String:trim	()Ljava/lang/String;
    //   97: invokevirtual 262	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   100: ifne +12 -> 112
    //   103: aload_3
    //   104: ldc_w 289
    //   107: aload_0
    //   108: invokevirtual 280	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   111: pop
    //   112: new 291	com/a/a/f/a
    //   115: dup
    //   116: ldc_w 293
    //   119: aload_3
    //   120: invokevirtual 294	org/json/JSONObject:toString	()Ljava/lang/String;
    //   123: invokespecial 296	com/a/a/f/a:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   126: astore_0
    //   127: aload_0
    //   128: getstatic 302	android/os/AsyncTask:THREAD_POOL_EXECUTOR	Ljava/util/concurrent/Executor;
    //   131: iconst_0
    //   132: anewarray 304	java/lang/Void
    //   135: invokevirtual 308	com/a/a/f/a:executeOnExecutor	(Ljava/util/concurrent/Executor;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   138: pop
    //   139: aload_0
    //   140: invokevirtual 312	com/a/a/f/a:get	()Ljava/lang/Object;
    //   143: checkcast 258	java/lang/String
    //   146: astore_0
    //   147: aload_0
    //   148: invokestatic 102	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   151: istore_1
    //   152: iload_1
    //   153: ifne +14 -> 167
    //   156: new 106	org/json/JSONObject
    //   159: dup
    //   160: aload_0
    //   161: invokespecial 313	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   164: putstatic 44	com/a/a/g:c	Lorg/json/JSONObject;
    //   167: getstatic 44	com/a/a/g:c	Lorg/json/JSONObject;
    //   170: astore_0
    //   171: aload_2
    //   172: monitorexit
    //   173: aload_0
    //   174: areturn
    //   175: astore_0
    //   176: getstatic 37	com/a/a/g:a	Ljava/lang/String;
    //   179: new 161	java/lang/StringBuilder
    //   182: dup
    //   183: ldc_w 315
    //   186: invokespecial 166	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   189: aload_0
    //   190: invokevirtual 316	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   193: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: aload_0
    //   200: invokestatic 319	com/a/a/j/a:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   203: getstatic 44	com/a/a/g:c	Lorg/json/JSONObject;
    //   206: astore_0
    //   207: aload_2
    //   208: monitorexit
    //   209: aload_0
    //   210: areturn
    //   211: astore_0
    //   212: getstatic 37	com/a/a/g:a	Ljava/lang/String;
    //   215: new 161	java/lang/StringBuilder
    //   218: dup
    //   219: ldc_w 321
    //   222: invokespecial 166	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   225: aload_0
    //   226: invokevirtual 169	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   229: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: invokestatic 181	com/a/a/j/a:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   238: goto -71 -> 167
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	241	0	paramString	String
    //   151	2	1	bool	boolean
    //   25	183	2	localObject	Object
    //   64	56	3	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   28	40	42	finally
    //   43	45	42	finally
    //   47	57	42	finally
    //   57	87	42	finally
    //   91	112	42	finally
    //   112	147	42	finally
    //   147	152	42	finally
    //   156	167	42	finally
    //   167	173	42	finally
    //   176	209	42	finally
    //   212	238	42	finally
    //   57	87	175	java/lang/Exception
    //   91	112	175	java/lang/Exception
    //   112	147	175	java/lang/Exception
    //   156	167	211	org/json/JSONException
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
}
