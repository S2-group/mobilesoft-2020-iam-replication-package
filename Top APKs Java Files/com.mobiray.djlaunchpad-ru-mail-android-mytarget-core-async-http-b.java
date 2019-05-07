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
import ru.mail.android.mytarget.core.parsers.b.a;
import ru.mail.android.mytarget.core.parsers.rb.d;

public final class b
  extends a
{
  private ru.mail.android.mytarget.core.models.c c;
  private ru.mail.android.mytarget.core.b d;
  private String e = "";
  private String f;
  private ru.mail.android.mytarget.core.models.b g;
  private boolean h;
  
  public b(String paramString, ru.mail.android.mytarget.core.b paramB, Map<String, String> paramMap, boolean paramBoolean)
  {
    super(paramString, paramMap);
    this.d = paramB;
    this.h = paramBoolean;
  }
  
  public b(ru.mail.android.mytarget.core.models.b paramB, ru.mail.android.mytarget.core.b paramB1, ru.mail.android.mytarget.core.models.c paramC)
  {
    this(paramC.a(), paramB1, null, false);
    this.c = paramC;
    this.f = paramC.a();
    this.g = paramB;
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
        if (paramString.indexOf("<!doctype html>") == 0)
        {
          int i = paramString.indexOf("bannersJSON:");
          if (i >= 0)
          {
            i += 12;
            int j = paramString.indexOf("{", i);
            if (j >= i)
            {
              int k = paramString.indexOf("};", j);
              if (k >= j + 1)
              {
                int m = paramString.indexOf("</script>", k);
                if (m >= k)
                {
                  String str = (paramString.substring(0, i) + "''};" + paramString.substring(m)).replace("\"", "'");
                  paramString = "{\"html_wrapper\":\"" + str + "\"," + paramString.substring(j + 1, k);
                }
              }
            }
          }
        }
        for (;;)
        {
          if (this.g == null) {
            this.g = new ru.mail.android.mytarget.core.models.b(this.d.d());
          }
          if (!ru.mail.android.mytarget.core.parsers.b.a(paramString)) {
            break;
          }
          Tracer.d("Parsing XML...");
          this.g.a(this.f);
          b(paramString, paramContext);
          return true;
        }
        Tracer.d("Converting to JSON...");
        try
        {
          paramString = new JSONObject(paramString);
          Tracer.d("done");
          if (d.a(paramString))
          {
            this.g.a(this.f);
            Tracer.d("parse json");
            try
            {
              d.a(paramString, this.g, this.d.b(), c(paramContext), paramContext, this.c);
              if (this.d.e()) {
                this.g.i();
              }
              Tracer.d("json parsed successfully");
              return true;
            }
            catch (JSONException paramString)
            {
              Tracer.d("parse json error. message: " + paramString.getMessage());
              ru.mail.android.mytarget.core.async.c.a("Parse error", getClass().getName(), 40, paramString.getClass().getSimpleName(), this.g.b(), paramContext);
              this.e = paramString.getMessage();
              return true;
            }
          }
          Tracer.d("invalid json version");
          this.e = "Invalid json version";
          return false;
        }
        catch (Exception paramString)
        {
          Tracer.d("convert to JSON error: " + paramString.getMessage());
          ru.mail.android.mytarget.core.async.c.a("Convert to JSON error", getClass().getName(), 40, paramString.getClass().getSimpleName(), this.a, paramContext);
          this.e = paramString.getMessage();
          return false;
        }
      }
      Tracer.d("data is empty");
      this.e = "Empty response";
      return false;
    }
  }
  
  private void b(String paramString, Context paramContext)
  {
    Tracer.d("parse VAST");
    try
    {
      Object localObject = new ArrayList();
      ru.mail.android.mytarget.core.parsers.b.a(paramString, this.g, (ArrayList)localObject, this.c);
      paramString = ((ArrayList)localObject).iterator();
      while (paramString.hasNext())
      {
        localObject = (String)paramString.next();
        Tracer.d("parse VAST exception. message: " + (String)localObject);
        ru.mail.android.mytarget.core.async.c.a("Parse exception", getClass().getName(), 30, ru.mail.android.mytarget.core.parsers.b.class.getSimpleName(), this.g.b(), paramContext);
      }
      return;
    }
    catch (b.a paramString)
    {
      Tracer.d("parse VAST error. message: " + paramString.getMessage());
      ru.mail.android.mytarget.core.async.c.a("Parse error", getClass().getName(), 40, paramString.getClass().getSimpleName(), this.g.b(), paramContext);
      this.e = paramString.getMessage();
      if (paramString.a() != null)
      {
        paramString = paramString.a().iterator();
        while (paramString.hasNext()) {
          ru.mail.android.mytarget.core.async.c.a((String)paramString.next(), paramContext);
        }
      }
    }
  }
  
  private static ArrayList<String> c(Context paramContext)
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
    //   5: invokespecial 282	ru/mail/android/mytarget/core/async/http/a:a	(Landroid/content/Context;)V
    //   8: aload_0
    //   9: getfield 28	ru/mail/android/mytarget/core/async/http/b:h	Z
    //   12: ifne +63 -> 75
    //   15: ldc_w 284
    //   18: invokestatic 134	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   21: aload_1
    //   22: invokestatic 289	ru/mail/android/mytarget/core/utils/c:a	(Landroid/content/Context;)Lru/mail/android/mytarget/core/utils/c;
    //   25: astore_3
    //   26: aload_3
    //   27: ifnull +520 -> 547
    //   30: aload_3
    //   31: aload_0
    //   32: getfield 26	ru/mail/android/mytarget/core/async/http/b:d	Lru/mail/android/mytarget/core/b;
    //   35: invokevirtual 292	ru/mail/android/mytarget/core/b:a	()I
    //   38: invokestatic 296	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   41: aload_0
    //   42: getfield 26	ru/mail/android/mytarget/core/async/http/b:d	Lru/mail/android/mytarget/core/b;
    //   45: invokevirtual 120	ru/mail/android/mytarget/core/b:d	()J
    //   48: invokevirtual 299	ru/mail/android/mytarget/core/utils/c:a	(Ljava/lang/String;J)Ljava/lang/String;
    //   51: astore_3
    //   52: aload_3
    //   53: ifnull +22 -> 75
    //   56: ldc_w 301
    //   59: invokestatic 134	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   62: aload_0
    //   63: aload_3
    //   64: aload_1
    //   65: invokespecial 303	ru/mail/android/mytarget/core/async/http/b:a	(Ljava/lang/String;Landroid/content/Context;)Z
    //   68: pop
    //   69: aload_0
    //   70: iconst_1
    //   71: invokevirtual 306	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   74: return
    //   75: aload_0
    //   76: getfield 44	ru/mail/android/mytarget/core/async/http/b:f	Ljava/lang/String;
    //   79: invokestatic 312	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   82: ifeq +141 -> 223
    //   85: aload_0
    //   86: getfield 26	ru/mail/android/mytarget/core/async/http/b:d	Lru/mail/android/mytarget/core/b;
    //   89: invokevirtual 166	ru/mail/android/mytarget/core/b:e	()Z
    //   92: ifeq +131 -> 223
    //   95: invokestatic 315	ru/mail/android/mytarget/core/models/b:a	()Lru/mail/android/mytarget/core/utils/j;
    //   98: invokevirtual 320	ru/mail/android/mytarget/core/utils/j:a	()Ljava/util/Set;
    //   101: astore_3
    //   102: aload_3
    //   103: ifnull +120 -> 223
    //   106: aload_3
    //   107: invokeinterface 325 1 0
    //   112: ifle +111 -> 223
    //   115: aload_3
    //   116: invokeinterface 326 1 0
    //   121: astore 5
    //   123: ldc 22
    //   125: astore_3
    //   126: aload 5
    //   128: invokeinterface 233 1 0
    //   133: ifeq +44 -> 177
    //   136: aload 5
    //   138: invokeinterface 237 1 0
    //   143: checkcast 53	java/lang/String
    //   146: astore 6
    //   148: new 79	java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial 82	java/lang/StringBuilder:<init>	()V
    //   155: aload_3
    //   156: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: aload 6
    //   161: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: ldc_w 328
    //   167: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: astore_3
    //   174: goto -48 -> 126
    //   177: aload_3
    //   178: iconst_0
    //   179: aload_3
    //   180: invokevirtual 331	java/lang/String:length	()I
    //   183: iconst_1
    //   184: isub
    //   185: invokevirtual 86	java/lang/String:substring	(II)Ljava/lang/String;
    //   188: astore_3
    //   189: aload_0
    //   190: getfield 334	ru/mail/android/mytarget/core/async/http/b:b	Ljava/util/Map;
    //   193: ldc_w 336
    //   196: aload_3
    //   197: invokeinterface 342 3 0
    //   202: pop
    //   203: new 79	java/lang/StringBuilder
    //   206: dup
    //   207: ldc_w 344
    //   210: invokespecial 111	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   213: aload_3
    //   214: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   220: invokestatic 134	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   223: aload_0
    //   224: getfield 44	ru/mail/android/mytarget/core/async/http/b:f	Ljava/lang/String;
    //   227: invokestatic 312	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   230: ifeq +12 -> 242
    //   233: aload_0
    //   234: aload_0
    //   235: aload_1
    //   236: invokevirtual 347	ru/mail/android/mytarget/core/async/http/b:b	(Landroid/content/Context;)Ljava/lang/String;
    //   239: putfield 44	ru/mail/android/mytarget/core/async/http/b:f	Ljava/lang/String;
    //   242: new 79	java/lang/StringBuilder
    //   245: dup
    //   246: ldc_w 349
    //   249: invokespecial 111	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   252: aload_0
    //   253: getfield 44	ru/mail/android/mytarget/core/async/http/b:f	Ljava/lang/String;
    //   256: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   262: invokestatic 134	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   265: new 351	java/net/URL
    //   268: dup
    //   269: aload_0
    //   270: getfield 44	ru/mail/android/mytarget/core/async/http/b:f	Ljava/lang/String;
    //   273: invokespecial 352	java/net/URL:<init>	(Ljava/lang/String;)V
    //   276: invokevirtual 356	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   279: checkcast 358	java/net/HttpURLConnection
    //   282: astore_3
    //   283: aload_3
    //   284: sipush 20000
    //   287: invokevirtual 362	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   290: aload_3
    //   291: sipush 20000
    //   294: invokevirtual 365	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   297: aload_3
    //   298: iconst_1
    //   299: invokevirtual 368	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   302: aload_3
    //   303: ldc_w 370
    //   306: ldc_w 372
    //   309: invokevirtual 376	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   312: aload_3
    //   313: invokevirtual 379	java/net/HttpURLConnection:connect	()V
    //   316: aload_3
    //   317: invokevirtual 382	java/net/HttpURLConnection:getResponseCode	()I
    //   320: istore_2
    //   321: iload_2
    //   322: sipush 200
    //   325: if_icmpne +137 -> 462
    //   328: new 384	java/io/BufferedReader
    //   331: dup
    //   332: new 386	java/io/InputStreamReader
    //   335: dup
    //   336: aload_3
    //   337: invokevirtual 390	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   340: invokespecial 393	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   343: invokespecial 396	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   346: astore 4
    //   348: new 79	java/lang/StringBuilder
    //   351: dup
    //   352: invokespecial 82	java/lang/StringBuilder:<init>	()V
    //   355: astore 5
    //   357: aload 4
    //   359: invokevirtual 399	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   362: astore 6
    //   364: aload 6
    //   366: ifnull +66 -> 432
    //   369: aload 5
    //   371: aload 6
    //   373: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: pop
    //   377: goto -20 -> 357
    //   380: astore 4
    //   382: aload_3
    //   383: astore_1
    //   384: aload 4
    //   386: astore_3
    //   387: aload_0
    //   388: aload_3
    //   389: invokevirtual 400	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   392: putfield 24	ru/mail/android/mytarget/core/async/http/b:e	Ljava/lang/String;
    //   395: new 79	java/lang/StringBuilder
    //   398: dup
    //   399: ldc_w 402
    //   402: invokespecial 111	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   405: aload_0
    //   406: getfield 24	ru/mail/android/mytarget/core/async/http/b:e	Ljava/lang/String;
    //   409: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   412: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   415: invokestatic 134	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   418: aload_0
    //   419: iconst_0
    //   420: invokevirtual 306	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   423: aload_1
    //   424: ifnull -350 -> 74
    //   427: aload_1
    //   428: invokevirtual 405	java/net/HttpURLConnection:disconnect	()V
    //   431: return
    //   432: aload 4
    //   434: invokevirtual 407	java/io/BufferedReader:close	()V
    //   437: aload_0
    //   438: aload 5
    //   440: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   443: aload_1
    //   444: invokespecial 303	ru/mail/android/mytarget/core/async/http/b:a	(Ljava/lang/String;Landroid/content/Context;)Z
    //   447: pop
    //   448: aload_0
    //   449: iconst_1
    //   450: invokevirtual 306	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   453: aload_3
    //   454: ifnull -380 -> 74
    //   457: aload_3
    //   458: invokevirtual 405	java/net/HttpURLConnection:disconnect	()V
    //   461: return
    //   462: iload_2
    //   463: sipush 204
    //   466: if_icmpne +22 -> 488
    //   469: aload_0
    //   470: iconst_1
    //   471: invokevirtual 306	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   474: goto -21 -> 453
    //   477: astore_1
    //   478: aload_3
    //   479: ifnull +7 -> 486
    //   482: aload_3
    //   483: invokevirtual 405	java/net/HttpURLConnection:disconnect	()V
    //   486: aload_1
    //   487: athrow
    //   488: aload_0
    //   489: new 79	java/lang/StringBuilder
    //   492: dup
    //   493: ldc_w 409
    //   496: invokespecial 111	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   499: iload_2
    //   500: invokevirtual 412	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   503: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   506: putfield 24	ru/mail/android/mytarget/core/async/http/b:e	Ljava/lang/String;
    //   509: aload_0
    //   510: getfield 24	ru/mail/android/mytarget/core/async/http/b:e	Ljava/lang/String;
    //   513: invokestatic 134	ru/mail/android/mytarget/Tracer:d	(Ljava/lang/String;)V
    //   516: aload_0
    //   517: iconst_0
    //   518: invokevirtual 306	ru/mail/android/mytarget/core/async/http/b:a	(Z)V
    //   521: goto -68 -> 453
    //   524: astore_1
    //   525: aload 4
    //   527: astore_3
    //   528: goto -50 -> 478
    //   531: astore 4
    //   533: aload_1
    //   534: astore_3
    //   535: aload 4
    //   537: astore_1
    //   538: goto -60 -> 478
    //   541: astore_3
    //   542: aconst_null
    //   543: astore_1
    //   544: goto -157 -> 387
    //   547: aconst_null
    //   548: astore_3
    //   549: goto -497 -> 52
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	552	0	this	b
    //   0	552	1	paramContext	Context
    //   320	180	2	i	int
    //   25	510	3	localObject1	Object
    //   541	1	3	localThrowable1	Throwable
    //   548	1	3	localObject2	Object
    //   1	357	4	localBufferedReader	java.io.BufferedReader
    //   380	146	4	localThrowable2	Throwable
    //   531	5	4	localObject3	Object
    //   121	318	5	localObject4	Object
    //   146	226	6	str	String
    // Exception table:
    //   from	to	target	type
    //   283	321	380	java/lang/Throwable
    //   328	357	380	java/lang/Throwable
    //   357	364	380	java/lang/Throwable
    //   369	377	380	java/lang/Throwable
    //   432	453	380	java/lang/Throwable
    //   469	474	380	java/lang/Throwable
    //   488	521	380	java/lang/Throwable
    //   283	321	477	finally
    //   328	357	477	finally
    //   357	364	477	finally
    //   369	377	477	finally
    //   432	453	477	finally
    //   469	474	477	finally
    //   488	521	477	finally
    //   223	242	524	finally
    //   242	283	524	finally
    //   387	423	531	finally
    //   223	242	541	java/lang/Throwable
    //   242	283	541	java/lang/Throwable
  }
  
  public final String e()
  {
    return this.e;
  }
  
  public final ru.mail.android.mytarget.core.models.b f()
  {
    return this.g;
  }
}
