package ru.mail.android.mytarget.core.async.http;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.android.mytarget.Tracer;
import ru.mail.android.mytarget.core.async.c;

public final class b
  extends a
{
  private ru.mail.android.mytarget.core.b c;
  private String d = "";
  private String e;
  private ru.mail.android.mytarget.core.models.b f;
  private boolean g;
  
  public b(String paramString, ru.mail.android.mytarget.core.b paramB, Map<String, String> paramMap, boolean paramBoolean)
  {
    super(paramString, paramMap);
    this.c = paramB;
    this.g = paramBoolean;
  }
  
  private boolean a(String paramString, Context paramContext)
  {
    if (paramString != null) {
      paramString = paramString.trim();
    }
    for (;;)
    {
      if ((paramString != null) && (!paramString.equals("")))
      {
        String str = paramString;
        if (paramString.indexOf("<!doctype html>") == 0)
        {
          int i = paramString.indexOf("bannersJSON:");
          str = paramString;
          if (i >= 0)
          {
            i += 12;
            int j = paramString.indexOf("{", i);
            str = paramString;
            if (j >= i)
            {
              int k = paramString.indexOf("};", j);
              str = paramString;
              if (k >= j + 1)
              {
                int m = paramString.indexOf("</script>", k);
                str = paramString;
                if (m >= k)
                {
                  str = (paramString.substring(0, i) + "''};" + paramString.substring(m)).replace("\"", "'");
                  str = "{\"html_wrapper\":\"" + str + "\"," + paramString.substring(j + 1, k);
                }
              }
            }
          }
        }
        Tracer.d("Converting to JSON...");
        try
        {
          paramString = new JSONObject(str);
          Tracer.d("done");
          if (ru.mail.android.mytarget.core.parsers.a.a(paramString))
          {
            this.f = new ru.mail.android.mytarget.core.models.b(this.c.d());
            this.f.a(this.e);
            Tracer.d("parse json");
            try
            {
              ru.mail.android.mytarget.core.parsers.a.a(paramString, this.f, this.c.b(), d(paramContext), paramContext);
              if (this.c.e()) {
                this.f.h();
              }
              Tracer.d("json parsed successfully");
              return true;
            }
            catch (JSONException paramString)
            {
              Tracer.d("parse json error. message: " + paramString.getMessage());
              c.a("Parse error", getClass().getName(), 40, paramString.getClass().getSimpleName(), this.f.b(), paramContext);
              this.f = null;
              this.d = paramString.getMessage();
              return true;
            }
          }
          Tracer.d("invalid json version");
          this.d = "Invalid json version";
          return false;
        }
        catch (Exception paramString)
        {
          this.f = null;
          Tracer.d("convert to JSON error: " + paramString.getMessage());
          c.a("Convert to JSON error", getClass().getName(), 40, paramString.getClass().getSimpleName(), this.a, paramContext);
          this.d = paramString.getMessage();
          return false;
        }
      }
      Tracer.d("data is empty");
      this.d = "Empty response";
      return false;
    }
  }
  
  private static ArrayList<String> d(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((localApplicationInfo.flags & 0x1) == 0) {
        paramContext.add(localApplicationInfo.packageName);
      }
    }
    return paramContext;
  }
  
  /* Error */
  public final void a(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: aload_1
    //   5: invokespecial 241	ru/mail/android/mytarget/core/async/http/a:a	(Landroid/content/Context;)V
    //   8: aload_0
    //   9: aconst_null
    //   10: putfield 126	ru/mail/android/mytarget/core/async/http/b:f	Lru/mail/android/mytarget/core/models/b;
    //   13: aload_0
    //   14: getfield 26	ru/mail/android/mytarget/core/async/http/b:g	Z
    //   17: ifne +62 -> 79
    //   20: ldc -13
    //   22: invokestatic 104	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   25: aload_1
    //   26: invokestatic 248	ru/mail/android/mytarget/core/utils/b:a	(Landroid/content/Context;)Lru/mail/android/mytarget/core/utils/b;
    //   29: astore_3
    //   30: aload_3
    //   31: ifnull +500 -> 531
    //   34: aload_3
    //   35: aload_0
    //   36: getfield 24	ru/mail/android/mytarget/core/async/http/b:c	Lru/mail/android/mytarget/core/b;
    //   39: invokevirtual 251	ru/mail/android/mytarget/core/b:a	()I
    //   42: invokestatic 255	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   45: aload_0
    //   46: getfield 24	ru/mail/android/mytarget/core/async/http/b:c	Lru/mail/android/mytarget/core/b;
    //   49: invokevirtual 121	ru/mail/android/mytarget/core/b:d	()J
    //   52: invokevirtual 258	ru/mail/android/mytarget/core/utils/b:a	(Ljava/lang/String;J)Ljava/lang/String;
    //   55: astore_3
    //   56: aload_3
    //   57: ifnull +22 -> 79
    //   60: ldc_w 260
    //   63: invokestatic 104	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   66: aload_0
    //   67: aload_3
    //   68: aload_1
    //   69: invokespecial 262	ru/mail/android/mytarget/core/async/http/b:a	(Ljava/lang/String;Landroid/content/Context;)Z
    //   72: pop
    //   73: aload_0
    //   74: iconst_1
    //   75: invokevirtual 265	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   78: return
    //   79: aload_0
    //   80: getfield 24	ru/mail/android/mytarget/core/async/http/b:c	Lru/mail/android/mytarget/core/b;
    //   83: invokevirtual 145	ru/mail/android/mytarget/core/b:e	()Z
    //   86: ifeq +131 -> 217
    //   89: invokestatic 268	ru/mail/android/mytarget/core/models/b:a	()Lru/mail/android/mytarget/core/utils/h;
    //   92: invokevirtual 273	ru/mail/android/mytarget/core/utils/h:a	()Ljava/util/Set;
    //   95: astore_3
    //   96: aload_3
    //   97: ifnull +120 -> 217
    //   100: aload_3
    //   101: invokeinterface 278 1 0
    //   106: ifle +111 -> 217
    //   109: aload_3
    //   110: invokeinterface 279 1 0
    //   115: astore 5
    //   117: ldc 20
    //   119: astore_3
    //   120: aload 5
    //   122: invokeinterface 219 1 0
    //   127: ifeq +44 -> 171
    //   130: aload 5
    //   132: invokeinterface 223 1 0
    //   137: checkcast 37	java/lang/String
    //   140: astore 6
    //   142: new 64	java/lang/StringBuilder
    //   145: dup
    //   146: invokespecial 67	java/lang/StringBuilder:<init>	()V
    //   149: aload_3
    //   150: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: aload 6
    //   155: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: ldc_w 281
    //   161: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: astore_3
    //   168: goto -48 -> 120
    //   171: aload_3
    //   172: iconst_0
    //   173: aload_3
    //   174: invokevirtual 284	java/lang/String:length	()I
    //   177: iconst_1
    //   178: isub
    //   179: invokevirtual 71	java/lang/String:substring	(II)Ljava/lang/String;
    //   182: astore_3
    //   183: aload_0
    //   184: getfield 287	ru/mail/android/mytarget/core/async/http/b:b	Ljava/util/Map;
    //   187: ldc_w 289
    //   190: aload_3
    //   191: invokeinterface 295 3 0
    //   196: pop
    //   197: new 64	java/lang/StringBuilder
    //   200: dup
    //   201: ldc_w 297
    //   204: invokespecial 96	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   207: aload_3
    //   208: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokestatic 104	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   217: aload_0
    //   218: aload_0
    //   219: aload_1
    //   220: invokevirtual 300	ru/mail/android/mytarget/core/async/http/b:c	(Landroid/content/Context;)Ljava/lang/String;
    //   223: putfield 128	ru/mail/android/mytarget/core/async/http/b:e	Ljava/lang/String;
    //   226: new 64	java/lang/StringBuilder
    //   229: dup
    //   230: ldc_w 302
    //   233: invokespecial 96	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   236: aload_0
    //   237: getfield 128	ru/mail/android/mytarget/core/async/http/b:e	Ljava/lang/String;
    //   240: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   246: invokestatic 104	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   249: new 304	java/net/URL
    //   252: dup
    //   253: aload_0
    //   254: getfield 128	ru/mail/android/mytarget/core/async/http/b:e	Ljava/lang/String;
    //   257: invokespecial 305	java/net/URL:<init>	(Ljava/lang/String;)V
    //   260: invokevirtual 309	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   263: checkcast 311	java/net/HttpURLConnection
    //   266: astore_3
    //   267: aload_3
    //   268: sipush 20000
    //   271: invokevirtual 315	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   274: aload_3
    //   275: sipush 20000
    //   278: invokevirtual 318	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   281: aload_3
    //   282: iconst_1
    //   283: invokevirtual 321	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   286: aload_3
    //   287: ldc_w 323
    //   290: ldc_w 325
    //   293: invokevirtual 329	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   296: aload_3
    //   297: invokevirtual 332	java/net/HttpURLConnection:connect	()V
    //   300: aload_3
    //   301: invokevirtual 335	java/net/HttpURLConnection:getResponseCode	()I
    //   304: istore_2
    //   305: iload_2
    //   306: sipush 200
    //   309: if_icmpne +137 -> 446
    //   312: new 337	java/io/BufferedReader
    //   315: dup
    //   316: new 339	java/io/InputStreamReader
    //   319: dup
    //   320: aload_3
    //   321: invokevirtual 343	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   324: invokespecial 346	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   327: invokespecial 349	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   330: astore 4
    //   332: new 64	java/lang/StringBuilder
    //   335: dup
    //   336: invokespecial 67	java/lang/StringBuilder:<init>	()V
    //   339: astore 5
    //   341: aload 4
    //   343: invokevirtual 352	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   346: astore 6
    //   348: aload 6
    //   350: ifnull +66 -> 416
    //   353: aload 5
    //   355: aload 6
    //   357: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   360: pop
    //   361: goto -20 -> 341
    //   364: astore 4
    //   366: aload_3
    //   367: astore_1
    //   368: aload 4
    //   370: astore_3
    //   371: aload_0
    //   372: aload_3
    //   373: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   376: putfield 22	ru/mail/android/mytarget/core/async/http/b:d	Ljava/lang/String;
    //   379: new 64	java/lang/StringBuilder
    //   382: dup
    //   383: ldc_w 355
    //   386: invokespecial 96	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   389: aload_0
    //   390: getfield 22	ru/mail/android/mytarget/core/async/http/b:d	Ljava/lang/String;
    //   393: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   399: invokestatic 104	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   402: aload_0
    //   403: iconst_0
    //   404: invokevirtual 265	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   407: aload_1
    //   408: ifnull -330 -> 78
    //   411: aload_1
    //   412: invokevirtual 358	java/net/HttpURLConnection:disconnect	()V
    //   415: return
    //   416: aload 4
    //   418: invokevirtual 360	java/io/BufferedReader:close	()V
    //   421: aload_0
    //   422: aload 5
    //   424: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: aload_1
    //   428: invokespecial 262	ru/mail/android/mytarget/core/async/http/b:a	(Ljava/lang/String;Landroid/content/Context;)Z
    //   431: pop
    //   432: aload_0
    //   433: iconst_1
    //   434: invokevirtual 265	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   437: aload_3
    //   438: ifnull -360 -> 78
    //   441: aload_3
    //   442: invokevirtual 358	java/net/HttpURLConnection:disconnect	()V
    //   445: return
    //   446: iload_2
    //   447: sipush 204
    //   450: if_icmpne +22 -> 472
    //   453: aload_0
    //   454: iconst_1
    //   455: invokevirtual 265	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   458: goto -21 -> 437
    //   461: astore_1
    //   462: aload_3
    //   463: ifnull +7 -> 470
    //   466: aload_3
    //   467: invokevirtual 358	java/net/HttpURLConnection:disconnect	()V
    //   470: aload_1
    //   471: athrow
    //   472: aload_0
    //   473: new 64	java/lang/StringBuilder
    //   476: dup
    //   477: ldc_w 362
    //   480: invokespecial 96	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   483: iload_2
    //   484: invokevirtual 365	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   487: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   490: putfield 22	ru/mail/android/mytarget/core/async/http/b:d	Ljava/lang/String;
    //   493: aload_0
    //   494: getfield 22	ru/mail/android/mytarget/core/async/http/b:d	Ljava/lang/String;
    //   497: invokestatic 104	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   500: aload_0
    //   501: iconst_0
    //   502: invokevirtual 265	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   505: goto -68 -> 437
    //   508: astore_1
    //   509: aload 4
    //   511: astore_3
    //   512: goto -50 -> 462
    //   515: astore 4
    //   517: aload_1
    //   518: astore_3
    //   519: aload 4
    //   521: astore_1
    //   522: goto -60 -> 462
    //   525: astore_3
    //   526: aconst_null
    //   527: astore_1
    //   528: goto -157 -> 371
    //   531: aconst_null
    //   532: astore_3
    //   533: goto -477 -> 56
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	536	0	this	b
    //   0	536	1	paramContext	Context
    //   304	180	2	i	int
    //   29	490	3	localObject1	Object
    //   525	1	3	localThrowable1	Throwable
    //   532	1	3	localObject2	Object
    //   1	341	4	localBufferedReader	java.io.BufferedReader
    //   364	146	4	localThrowable2	Throwable
    //   515	5	4	localObject3	Object
    //   115	308	5	localObject4	Object
    //   140	216	6	str	String
    // Exception table:
    //   from	to	target	type
    //   267	305	364	java/lang/Throwable
    //   312	341	364	java/lang/Throwable
    //   341	348	364	java/lang/Throwable
    //   353	361	364	java/lang/Throwable
    //   416	437	364	java/lang/Throwable
    //   453	458	364	java/lang/Throwable
    //   472	505	364	java/lang/Throwable
    //   267	305	461	finally
    //   312	341	461	finally
    //   341	348	461	finally
    //   353	361	461	finally
    //   416	437	461	finally
    //   453	458	461	finally
    //   472	505	461	finally
    //   217	267	508	finally
    //   371	407	515	finally
    //   217	267	525	java/lang/Throwable
  }
  
  public final String e()
  {
    return this.d;
  }
  
  public final ru.mail.android.mytarget.core.models.b f()
  {
    return this.f;
  }
}
