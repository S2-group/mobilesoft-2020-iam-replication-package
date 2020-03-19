package com.bandwidthx.library;

import android.content.Context;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.json.JSONObject;

public class bd
{
  private static ch a;
  private static Boolean b = Boolean.valueOf(false);
  private static Toast c;
  private static Long d = Long.valueOf(0L);
  private static Long e = Long.valueOf(30000L);
  private static String f = "";
  private static TextToSpeech g;
  private static final Object h = new Object();
  private ArrayList<c> i = new ArrayList();
  private ArrayList<a> j = new ArrayList();
  
  public bd(ch paramCh)
  {
    try
    {
      a = paramCh;
      return;
    }
    catch (Exception paramCh)
    {
      cg.a(paramCh);
    }
  }
  
  public static void a(String paramString)
  {
    try
    {
      if ((a != null) && (a.x() != null) && (a.x().l().booleanValue()))
      {
        v();
        if ((d.longValue() > fi.f().longValue() - e.longValue()) && (f.equals(paramString))) {
          return;
        }
        d = fi.f();
        f = paramString;
        while (a.x().m().booleanValue()) {
          Thread.sleep(100L);
        }
        cg.c(paramString);
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          /* Error */
          public void run()
          {
            // Byte code:
            //   0: new 26	android/text/SpannableStringBuilder
            //   3: dup
            //   4: aload_0
            //   5: getfield 15	com/bandwidthx/library/bd$2:a	Ljava/lang/String;
            //   8: invokestatic 32	android/text/Html:fromHtml	(Ljava/lang/String;)Landroid/text/Spanned;
            //   11: invokespecial 35	android/text/SpannableStringBuilder:<init>	(Ljava/lang/CharSequence;)V
            //   14: astore_1
            //   15: aload_1
            //   16: new 37	android/text/style/RelativeSizeSpan
            //   19: dup
            //   20: ldc 38
            //   22: invokespecial 41	android/text/style/RelativeSizeSpan:<init>	(F)V
            //   25: iconst_0
            //   26: aload_1
            //   27: invokevirtual 45	android/text/SpannableStringBuilder:length	()I
            //   30: iconst_0
            //   31: invokevirtual 49	android/text/SpannableStringBuilder:setSpan	(Ljava/lang/Object;III)V
            //   34: invokestatic 53	com/bandwidthx/library/bd:t	()Lcom/bandwidthx/library/ch;
            //   37: pop
            //   38: invokestatic 59	com/bandwidthx/library/ch:e	()Landroid/content/Context;
            //   41: aload_1
            //   42: iconst_1
            //   43: invokestatic 65	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
            //   46: invokestatic 68	com/bandwidthx/library/bd:a	(Landroid/widget/Toast;)Landroid/widget/Toast;
            //   49: pop
            //   50: invokestatic 72	com/bandwidthx/library/bd:u	()Landroid/widget/Toast;
            //   53: invokevirtual 76	android/widget/Toast:getView	()Landroid/view/View;
            //   56: checkcast 78	android/view/ViewGroup
            //   59: iconst_0
            //   60: invokevirtual 82	android/view/ViewGroup:getChildAt	(I)Landroid/view/View;
            //   63: checkcast 84	android/widget/TextView
            //   66: astore_1
            //   67: aload_1
            //   68: bipush 7
            //   70: invokestatic 90	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
            //   73: invokestatic 95	com/bandwidthx/library/bc:a	(Ljava/lang/Integer;)Ljava/lang/Integer;
            //   76: invokevirtual 98	java/lang/Integer:intValue	()I
            //   79: bipush 7
            //   81: invokestatic 90	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
            //   84: invokestatic 95	com/bandwidthx/library/bc:a	(Ljava/lang/Integer;)Ljava/lang/Integer;
            //   87: invokevirtual 98	java/lang/Integer:intValue	()I
            //   90: bipush 7
            //   92: invokestatic 90	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
            //   95: invokestatic 95	com/bandwidthx/library/bc:a	(Ljava/lang/Integer;)Ljava/lang/Integer;
            //   98: invokevirtual 98	java/lang/Integer:intValue	()I
            //   101: bipush 7
            //   103: invokestatic 90	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
            //   106: invokestatic 95	com/bandwidthx/library/bc:a	(Ljava/lang/Integer;)Ljava/lang/Integer;
            //   109: invokevirtual 98	java/lang/Integer:intValue	()I
            //   112: invokevirtual 102	android/widget/TextView:setPadding	(IIII)V
            //   115: aload_1
            //   116: iconst_1
            //   117: invokevirtual 106	android/widget/TextView:setGravity	(I)V
            //   120: invokestatic 72	com/bandwidthx/library/bd:u	()Landroid/widget/Toast;
            //   123: invokevirtual 109	android/widget/Toast:show	()V
            //   126: goto +12 -> 138
            //   129: astore_1
            //   130: aload_1
            //   131: iconst_0
            //   132: invokestatic 114	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
            //   135: invokestatic 119	com/bandwidthx/library/cg:a	(Ljava/lang/Throwable;Ljava/lang/Boolean;)V
            //   138: invokestatic 123	com/bandwidthx/library/bd:s	()Landroid/speech/tts/TextToSpeech;
            //   141: ifnull +72 -> 213
            //   144: aload_0
            //   145: getfield 15	com/bandwidthx/library/bd$2:a	Ljava/lang/String;
            //   148: ldc 125
            //   150: ldc 127
            //   152: invokevirtual 133	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
            //   155: astore_1
            //   156: getstatic 139	android/os/Build$VERSION:SDK_INT	I
            //   159: bipush 21
            //   161: if_icmplt +26 -> 187
            //   164: invokestatic 123	com/bandwidthx/library/bd:s	()Landroid/speech/tts/TextToSpeech;
            //   167: aload_1
            //   168: invokestatic 32	android/text/Html:fromHtml	(Ljava/lang/String;)Landroid/text/Spanned;
            //   171: invokevirtual 143	java/lang/Object:toString	()Ljava/lang/String;
            //   174: iconst_1
            //   175: aconst_null
            //   176: invokestatic 149	com/bandwidthx/library/fi:f	()Ljava/lang/Long;
            //   179: invokevirtual 152	java/lang/Long:toString	()Ljava/lang/String;
            //   182: invokevirtual 158	android/speech/tts/TextToSpeech:speak	(Ljava/lang/CharSequence;ILandroid/os/Bundle;Ljava/lang/String;)I
            //   185: pop
            //   186: return
            //   187: invokestatic 123	com/bandwidthx/library/bd:s	()Landroid/speech/tts/TextToSpeech;
            //   190: aload_1
            //   191: invokestatic 32	android/text/Html:fromHtml	(Ljava/lang/String;)Landroid/text/Spanned;
            //   194: invokevirtual 143	java/lang/Object:toString	()Ljava/lang/String;
            //   197: iconst_1
            //   198: aconst_null
            //   199: invokevirtual 161	android/speech/tts/TextToSpeech:speak	(Ljava/lang/String;ILjava/util/HashMap;)I
            //   202: pop
            //   203: return
            //   204: astore_1
            //   205: aload_1
            //   206: iconst_0
            //   207: invokestatic 114	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
            //   210: invokestatic 119	com/bandwidthx/library/cg:a	(Ljava/lang/Throwable;Ljava/lang/Boolean;)V
            //   213: return
            //   214: astore_1
            //   215: goto -95 -> 120
            // Local variable table:
            //   start	length	slot	name	signature
            //   0	218	0	this	2
            //   14	102	1	localObject	Object
            //   129	2	1	localThrowable1	Throwable
            //   155	36	1	str	String
            //   204	2	1	localThrowable2	Throwable
            //   214	1	1	localException	Exception
            // Exception table:
            //   from	to	target	type
            //   0	50	129	java/lang/Throwable
            //   50	120	129	java/lang/Throwable
            //   120	126	129	java/lang/Throwable
            //   138	186	204	java/lang/Throwable
            //   187	203	204	java/lang/Throwable
            //   50	120	214	java/lang/Exception
          }
        });
        Intent localIntent = new Intent("com.bandwidthx.DIAGNOSTIC_ALERT");
        localIntent.putExtra("FROM", ac.d());
        localIntent.putExtra("VERSION", ac.e());
        localIntent.putExtra("ID", fi.f());
        localIntent.putExtra("TYPE", "alert");
        localIntent.putExtra("MESSAGE", paramString);
        ch.e().sendBroadcast(localIntent);
        return;
      }
    }
    catch (Exception paramString)
    {
      cg.a(paramString, Boolean.valueOf(false));
      return;
    }
    catch (InterruptedException paramString) {}
  }
  
  public static void n()
  {
    try
    {
      if ((a != null) && (a.f() != null))
      {
        a.f().a("ping");
        return;
      }
    }
    catch (Exception localException)
    {
      cg.a(localException);
    }
  }
  
  private static void v()
  {
    try
    {
      if (g == null)
      {
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            if (bd.s() == null) {}
            try
            {
              bd.t();
              bd.a(new TextToSpeech(ch.e().getApplicationContext(), new TextToSpeech.OnInitListener()
              {
                public void onInit(int paramAnonymous2Int)
                {
                  if (paramAnonymous2Int != -1) {
                    try
                    {
                      bd.s().setLanguage(Locale.US);
                      return;
                    }
                    catch (Throwable localThrowable)
                    {
                      cg.a(localThrowable, Boolean.valueOf(false));
                    }
                  }
                }
              }));
              return;
            }
            catch (Throwable localThrowable)
            {
              cg.a(localThrowable, Boolean.valueOf(false));
              return;
              cg.b("Unable to create text to speech");
              return;
            }
            catch (ReceiverCallNotAllowedException localReceiverCallNotAllowedException)
            {
              for (;;) {}
            }
          }
        });
        return;
      }
    }
    catch (Exception localException)
    {
      cg.a(localException, Boolean.valueOf(false));
    }
  }
  
  private void w()
  {
    b = Boolean.valueOf(false);
    if (!a.X().g().booleanValue()) {
      b = a.X().G();
    }
  }
  
  private void x()
  {
    if (b.booleanValue()) {
      a.X().H();
    }
  }
  
  public void a() {}
  
  public void a(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    a(Integer.valueOf(100), "Ping", r());
  }
  
  public void a(Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, Boolean paramBoolean5)
  {
    a.Q().a(Integer.valueOf(78), Boolean.valueOf(true));
    a.Q().a(Integer.valueOf(139), paramBoolean1);
    a.Q().a(Integer.valueOf(140), paramBoolean2);
    a.Q().a(Integer.valueOf(141), paramBoolean3);
    a.Q().a(Integer.valueOf(142), paramBoolean4);
    a.Q().a(Integer.valueOf(143), paramBoolean5);
  }
  
  public void a(Integer paramInteger, String paramString1, String paramString2)
  {
    a.an().a(paramInteger, paramString1, paramString2);
    a.f().c();
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    synchronized (h)
    {
      if (this.i.size() >= 100) {
        this.i.remove(0);
      }
      Object localObject2 = this.i.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        c localC = (c)((Iterator)localObject2).next();
        if (localC.c.equals(paramString2)) {
          this.i.remove(localC);
        }
      }
      localObject2 = new c();
      fi.f();
      ((c)localObject2).b = paramString1;
      ((c)localObject2).c = paramString2;
      ((c)localObject2).d = paramString3;
      ((c)localObject2).e = paramString4;
      try
      {
        paramString1 = new JSONObject(paramString5);
        ((c)localObject2).f = paramString1.optString("app", "");
        ((c)localObject2).g = paramString1.optString("tok", "");
        ((c)localObject2).h = Long.valueOf(paramString1.optLong("ins", 0L));
        ((c)localObject2).i = Long.valueOf(paramString1.optLong("bld", 0L));
        ((c)localObject2).j = Integer.valueOf(paramString1.optInt("cod", 0));
        ((c)localObject2).k = paramString1.optString("lib", "");
        ((c)localObject2).l = paramString1.optString("nam", "");
        ((c)localObject2).m = paramString1.optString("c2d", "");
        ((c)localObject2).n = paramString1.optString("svc", "");
      }
      catch (Exception paramString1)
      {
        cg.a(paramString1, Boolean.valueOf(false));
      }
      this.i.add(localObject2);
      return;
    }
  }
  
  public void b() {}
  
  /* Error */
  public void b(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_3
    //   4: ldc_w 359
    //   7: iconst_0
    //   8: invokevirtual 334	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
    //   11: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   14: astore_2
    //   15: aload_3
    //   16: ldc_w 361
    //   19: ldc 33
    //   21: invokevirtual 311	org/json/JSONObject:optString	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   24: astore 11
    //   26: aload_3
    //   27: ldc_w 363
    //   30: ldc2_w 364
    //   33: invokevirtual 322	org/json/JSONObject:optLong	(Ljava/lang/String;J)J
    //   36: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   39: astore 9
    //   41: aload_3
    //   42: ldc_w 367
    //   45: lconst_0
    //   46: invokevirtual 322	org/json/JSONObject:optLong	(Ljava/lang/String;J)J
    //   49: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   52: astore 15
    //   54: aload_3
    //   55: ldc_w 369
    //   58: ldc2_w 370
    //   61: invokevirtual 322	org/json/JSONObject:optLong	(Ljava/lang/String;J)J
    //   64: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   67: astore_3
    //   68: aload_2
    //   69: astore_1
    //   70: aload_2
    //   71: invokevirtual 374	java/lang/Integer:intValue	()I
    //   74: ifne +10 -> 84
    //   77: ldc_w 375
    //   80: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   83: astore_1
    //   84: aload 9
    //   86: astore_2
    //   87: aload 9
    //   89: invokevirtual 116	java/lang/Long:longValue	()J
    //   92: lconst_0
    //   93: lcmp
    //   94: ifne +10 -> 104
    //   97: ldc2_w 376
    //   100: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   103: astore_2
    //   104: aload_3
    //   105: astore 14
    //   107: aload_3
    //   108: invokevirtual 116	java/lang/Long:longValue	()J
    //   111: lconst_0
    //   112: lcmp
    //   113: ifne +11 -> 124
    //   116: ldc2_w 370
    //   119: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   122: astore 14
    //   124: iconst_0
    //   125: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   128: invokestatic 380	com/bandwidthx/library/cg:a	(Ljava/lang/Boolean;)Ljava/lang/String;
    //   131: astore 21
    //   133: getstatic 81	com/bandwidthx/library/bd:a	Lcom/bandwidthx/library/ch;
    //   136: invokevirtual 384	com/bandwidthx/library/ch:P	()Lcom/bandwidthx/library/ec;
    //   139: invokevirtual 388	com/bandwidthx/library/ec:k	()Ljava/lang/String;
    //   142: astore_3
    //   143: aload_3
    //   144: astore 12
    //   146: aload_3
    //   147: invokevirtual 391	java/lang/String:length	()I
    //   150: ifne +8 -> 158
    //   153: ldc_w 393
    //   156: astore 12
    //   158: getstatic 81	com/bandwidthx/library/bd:a	Lcom/bandwidthx/library/ch;
    //   161: invokevirtual 397	com/bandwidthx/library/ch:T	()Lcom/bandwidthx/library/fj;
    //   164: invokevirtual 402	com/bandwidthx/library/fj:ag	()Ljava/lang/String;
    //   167: astore_3
    //   168: aload_3
    //   169: astore 13
    //   171: aload_3
    //   172: invokevirtual 391	java/lang/String:length	()I
    //   175: ifne +8 -> 183
    //   178: ldc_w 404
    //   181: astore 13
    //   183: ldc 33
    //   185: astore 10
    //   187: ldc 33
    //   189: astore_3
    //   190: ldc 33
    //   192: astore 9
    //   194: aload 11
    //   196: invokestatic 407	com/bandwidthx/library/cg:f	(Ljava/lang/String;)Ljava/lang/String;
    //   199: ldc_w 409
    //   202: invokevirtual 413	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   205: astore 18
    //   207: aload 18
    //   209: arraylength
    //   210: iconst_1
    //   211: isub
    //   212: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   215: astore 16
    //   217: iconst_0
    //   218: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   221: astore 11
    //   223: aload_2
    //   224: astore 19
    //   226: aload_1
    //   227: astore 17
    //   229: aload 16
    //   231: invokevirtual 374	java/lang/Integer:intValue	()I
    //   234: iflt +859 -> 1093
    //   237: iload 4
    //   239: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   242: astore_2
    //   243: aload 18
    //   245: aload 16
    //   247: invokevirtual 374	java/lang/Integer:intValue	()I
    //   250: aaload
    //   251: astore 20
    //   253: aload 17
    //   255: invokevirtual 374	java/lang/Integer:intValue	()I
    //   258: iconst_m1
    //   259: if_icmpne +11 -> 270
    //   262: iconst_1
    //   263: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   266: astore_1
    //   267: goto +277 -> 544
    //   270: aload 20
    //   272: ldc_w 415
    //   275: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   278: ifne +261 -> 539
    //   281: aload 20
    //   283: ldc_w 421
    //   286: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   289: ifne +250 -> 539
    //   292: aload 20
    //   294: ldc_w 423
    //   297: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   300: ifeq +6 -> 306
    //   303: goto +236 -> 539
    //   306: aload 17
    //   308: invokevirtual 374	java/lang/Integer:intValue	()I
    //   311: iconst_1
    //   312: iand
    //   313: ifle +33 -> 346
    //   316: aload 20
    //   318: ldc_w 425
    //   321: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   324: ifne +14 -> 338
    //   327: aload 20
    //   329: ldc_w 427
    //   332: invokevirtual 431	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   335: ifeq +11 -> 346
    //   338: iconst_1
    //   339: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   342: astore_1
    //   343: goto +201 -> 544
    //   346: aload 17
    //   348: invokevirtual 374	java/lang/Integer:intValue	()I
    //   351: iconst_2
    //   352: iand
    //   353: ifle +33 -> 386
    //   356: aload 20
    //   358: ldc_w 433
    //   361: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   364: ifeq +22 -> 386
    //   367: aload 20
    //   369: ldc_w 435
    //   372: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   375: ifne +11 -> 386
    //   378: iconst_1
    //   379: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   382: astore_1
    //   383: goto +161 -> 544
    //   386: aload 17
    //   388: invokevirtual 374	java/lang/Integer:intValue	()I
    //   391: bipush 16
    //   393: iand
    //   394: ifle +22 -> 416
    //   397: aload 20
    //   399: ldc_w 437
    //   402: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   405: ifeq +11 -> 416
    //   408: iconst_1
    //   409: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   412: astore_1
    //   413: goto +131 -> 544
    //   416: aload 17
    //   418: invokevirtual 374	java/lang/Integer:intValue	()I
    //   421: bipush 32
    //   423: iand
    //   424: ifle +22 -> 446
    //   427: aload 20
    //   429: ldc_w 439
    //   432: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   435: ifeq +11 -> 446
    //   438: iconst_1
    //   439: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   442: astore_1
    //   443: goto +101 -> 544
    //   446: aload 17
    //   448: invokevirtual 374	java/lang/Integer:intValue	()I
    //   451: bipush 64
    //   453: iand
    //   454: ifle +22 -> 476
    //   457: aload 20
    //   459: ldc_w 441
    //   462: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   465: ifeq +11 -> 476
    //   468: iconst_1
    //   469: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   472: astore_1
    //   473: goto +71 -> 544
    //   476: aload 17
    //   478: invokevirtual 374	java/lang/Integer:intValue	()I
    //   481: iconst_4
    //   482: iand
    //   483: ifle +22 -> 505
    //   486: aload 20
    //   488: ldc_w 443
    //   491: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   494: ifeq +11 -> 505
    //   497: iconst_1
    //   498: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   501: astore_1
    //   502: goto +42 -> 544
    //   505: aload_2
    //   506: astore_1
    //   507: aload 17
    //   509: invokevirtual 374	java/lang/Integer:intValue	()I
    //   512: bipush 8
    //   514: iand
    //   515: ifle +29 -> 544
    //   518: aload_2
    //   519: astore_1
    //   520: aload 20
    //   522: ldc_w 445
    //   525: invokevirtual 419	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   528: ifeq +16 -> 544
    //   531: iconst_1
    //   532: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   535: astore_1
    //   536: goto +8 -> 544
    //   539: iconst_1
    //   540: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   543: astore_1
    //   544: aload_1
    //   545: invokevirtual 109	java/lang/Boolean:booleanValue	()Z
    //   548: ifeq +381 -> 929
    //   551: aload 15
    //   553: invokevirtual 116	java/lang/Long:longValue	()J
    //   556: lstore 5
    //   558: lload 5
    //   560: lconst_0
    //   561: lcmp
    //   562: ifne +29 -> 591
    //   565: aload 14
    //   567: invokevirtual 116	java/lang/Long:longValue	()J
    //   570: lstore 5
    //   572: lload 5
    //   574: ldc2_w 370
    //   577: lcmp
    //   578: ifeq +6 -> 584
    //   581: goto +10 -> 591
    //   584: goto +345 -> 929
    //   587: astore_2
    //   588: goto +331 -> 919
    //   591: aload 20
    //   593: invokevirtual 391	java/lang/String:length	()I
    //   596: bipush 10
    //   598: if_icmple -14 -> 584
    //   601: ldc 33
    //   603: astore_2
    //   604: aload 20
    //   606: iconst_0
    //   607: iconst_1
    //   608: invokevirtual 449	java/lang/String:substring	(II)Ljava/lang/String;
    //   611: astore 22
    //   613: aload 22
    //   615: ldc_w 451
    //   618: invokevirtual 129	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   621: istore 4
    //   623: iload 4
    //   625: ifeq +25 -> 650
    //   628: aload 20
    //   630: iconst_1
    //   631: aload 20
    //   633: ldc_w 453
    //   636: invokevirtual 457	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   639: invokevirtual 449	java/lang/String:substring	(II)Ljava/lang/String;
    //   642: astore_2
    //   643: goto +7 -> 650
    //   646: astore_2
    //   647: goto -59 -> 588
    //   650: aload 22
    //   652: ldc_w 459
    //   655: invokevirtual 129	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   658: istore 4
    //   660: iload 4
    //   662: ifeq +18 -> 680
    //   665: aload 20
    //   667: iconst_1
    //   668: aload 20
    //   670: ldc_w 461
    //   673: invokevirtual 457	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   676: invokevirtual 449	java/lang/String:substring	(II)Ljava/lang/String;
    //   679: astore_2
    //   680: aload 22
    //   682: ldc_w 463
    //   685: invokevirtual 129	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   688: istore 4
    //   690: iload 4
    //   692: ifeq +18 -> 710
    //   695: aload 20
    //   697: iconst_1
    //   698: aload 20
    //   700: ldc_w 465
    //   703: invokevirtual 457	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   706: invokevirtual 449	java/lang/String:substring	(II)Ljava/lang/String;
    //   709: astore_2
    //   710: aload 22
    //   712: ldc_w 467
    //   715: invokevirtual 129	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   718: istore 4
    //   720: iload 4
    //   722: ifeq +18 -> 740
    //   725: aload 20
    //   727: iconst_1
    //   728: aload 20
    //   730: ldc_w 469
    //   733: invokevirtual 457	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   736: invokevirtual 449	java/lang/String:substring	(II)Ljava/lang/String;
    //   739: astore_2
    //   740: aload_2
    //   741: invokevirtual 391	java/lang/String:length	()I
    //   744: ifle -160 -> 584
    //   747: new 471	java/util/GregorianCalendar
    //   750: dup
    //   751: invokespecial 472	java/util/GregorianCalendar:<init>	()V
    //   754: astore 22
    //   756: new 474	java/lang/StringBuilder
    //   759: dup
    //   760: invokespecial 475	java/lang/StringBuilder:<init>	()V
    //   763: astore 23
    //   765: aload 23
    //   767: ldc_w 477
    //   770: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   773: pop
    //   774: aload 23
    //   776: aload 22
    //   778: iconst_1
    //   779: invokevirtual 485	java/util/GregorianCalendar:get	(I)I
    //   782: invokestatic 489	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   785: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   788: pop
    //   789: aload 23
    //   791: ldc_w 491
    //   794: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   797: pop
    //   798: aload_2
    //   799: ldc_w 491
    //   802: aload 23
    //   804: invokevirtual 493	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   807: invokevirtual 497	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   810: astore_2
    //   811: new 499	java/text/SimpleDateFormat
    //   814: dup
    //   815: ldc_w 501
    //   818: getstatic 507	java/util/Locale:US	Ljava/util/Locale;
    //   821: invokespecial 510	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   824: aload_2
    //   825: invokevirtual 514	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   828: invokevirtual 519	java/util/Date:getTime	()J
    //   831: ldc2_w 520
    //   834: ldiv
    //   835: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   838: astore_2
    //   839: aload_2
    //   840: invokevirtual 116	java/lang/Long:longValue	()J
    //   843: lstore 5
    //   845: aload 15
    //   847: invokevirtual 116	java/lang/Long:longValue	()J
    //   850: lstore 7
    //   852: lload 5
    //   854: lload 7
    //   856: lcmp
    //   857: iflt +31 -> 888
    //   860: aload_2
    //   861: invokevirtual 116	java/lang/Long:longValue	()J
    //   864: lstore 5
    //   866: aload 14
    //   868: invokevirtual 116	java/lang/Long:longValue	()J
    //   871: lstore 7
    //   873: lload 5
    //   875: lload 7
    //   877: lcmp
    //   878: ifle -294 -> 584
    //   881: goto +7 -> 888
    //   884: astore_2
    //   885: goto -297 -> 588
    //   888: iconst_0
    //   889: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   892: astore_2
    //   893: aload_2
    //   894: astore_1
    //   895: goto +34 -> 929
    //   898: astore_2
    //   899: goto +20 -> 919
    //   902: astore_2
    //   903: goto +12 -> 915
    //   906: astore_2
    //   907: goto +8 -> 915
    //   910: astore_2
    //   911: goto +8 -> 919
    //   914: astore_2
    //   915: goto +4 -> 919
    //   918: astore_2
    //   919: aload_2
    //   920: invokevirtual 522	java/lang/Exception:toString	()Ljava/lang/String;
    //   923: invokestatic 524	com/bandwidthx/library/cg:b	(Ljava/lang/String;)V
    //   926: goto +3 -> 929
    //   929: aload_1
    //   930: invokevirtual 109	java/lang/Boolean:booleanValue	()Z
    //   933: ifeq +142 -> 1075
    //   936: aload 11
    //   938: invokevirtual 374	java/lang/Integer:intValue	()I
    //   941: ifne +10 -> 951
    //   944: aload 20
    //   946: astore 9
    //   948: goto +6 -> 954
    //   951: aload 20
    //   953: astore_3
    //   954: aload 11
    //   956: invokevirtual 374	java/lang/Integer:intValue	()I
    //   959: iconst_1
    //   960: iadd
    //   961: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   964: astore_1
    //   965: new 474	java/lang/StringBuilder
    //   968: dup
    //   969: invokespecial 475	java/lang/StringBuilder:<init>	()V
    //   972: astore 11
    //   974: aload 11
    //   976: aload 20
    //   978: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   981: pop
    //   982: aload 10
    //   984: invokevirtual 391	java/lang/String:length	()I
    //   987: ifle +34 -> 1021
    //   990: new 474	java/lang/StringBuilder
    //   993: dup
    //   994: invokespecial 475	java/lang/StringBuilder:<init>	()V
    //   997: astore_2
    //   998: aload_2
    //   999: ldc_w 409
    //   1002: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1005: pop
    //   1006: aload_2
    //   1007: aload 10
    //   1009: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1012: pop
    //   1013: aload_2
    //   1014: invokevirtual 493	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1017: astore_2
    //   1018: goto +6 -> 1024
    //   1021: ldc 33
    //   1023: astore_2
    //   1024: aload 11
    //   1026: aload_2
    //   1027: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1030: pop
    //   1031: aload 11
    //   1033: invokevirtual 493	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1036: astore 10
    //   1038: aload 19
    //   1040: invokevirtual 116	java/lang/Long:longValue	()J
    //   1043: lconst_1
    //   1044: lsub
    //   1045: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1048: astore 19
    //   1050: aload 19
    //   1052: invokevirtual 116	java/lang/Long:longValue	()J
    //   1055: lconst_0
    //   1056: lcmp
    //   1057: ifgt +12 -> 1069
    //   1060: aload_1
    //   1061: astore 11
    //   1063: aload 9
    //   1065: astore_1
    //   1066: goto +30 -> 1096
    //   1069: aload_1
    //   1070: astore 11
    //   1072: goto +3 -> 1075
    //   1075: aload 16
    //   1077: invokevirtual 374	java/lang/Integer:intValue	()I
    //   1080: iconst_1
    //   1081: isub
    //   1082: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1085: astore 16
    //   1087: iconst_0
    //   1088: istore 4
    //   1090: goto -861 -> 229
    //   1093: aload 9
    //   1095: astore_1
    //   1096: invokestatic 530	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   1099: astore_2
    //   1100: aload_2
    //   1101: bipush 15
    //   1103: invokevirtual 531	java/util/Calendar:get	(I)I
    //   1106: aload_2
    //   1107: bipush 16
    //   1109: invokevirtual 531	java/util/Calendar:get	(I)I
    //   1112: iadd
    //   1113: i2l
    //   1114: ldc2_w 520
    //   1117: ldiv
    //   1118: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1121: invokevirtual 116	java/lang/Long:longValue	()J
    //   1124: ldc2_w 532
    //   1127: ldiv
    //   1128: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1131: astore 9
    //   1133: aload_3
    //   1134: invokestatic 535	com/bandwidthx/library/cg:e	(Ljava/lang/String;)Ljava/lang/String;
    //   1137: astore_3
    //   1138: aload_1
    //   1139: invokestatic 535	com/bandwidthx/library/cg:e	(Ljava/lang/String;)Ljava/lang/String;
    //   1142: astore 14
    //   1144: ldc 33
    //   1146: astore_1
    //   1147: invokestatic 171	com/bandwidthx/library/ac:d	()Ljava/lang/String;
    //   1150: astore_2
    //   1151: aload_2
    //   1152: astore_1
    //   1153: new 474	java/lang/StringBuilder
    //   1156: dup
    //   1157: invokespecial 475	java/lang/StringBuilder:<init>	()V
    //   1160: astore 15
    //   1162: aload 15
    //   1164: ldc_w 537
    //   1167: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1170: pop
    //   1171: aload 15
    //   1173: aload_3
    //   1174: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1177: pop
    //   1178: aload 15
    //   1180: ldc_w 539
    //   1183: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1186: pop
    //   1187: aload 15
    //   1189: aload 14
    //   1191: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1194: pop
    //   1195: aload 15
    //   1197: ldc_w 541
    //   1200: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1203: pop
    //   1204: aload 15
    //   1206: aload 11
    //   1208: invokevirtual 542	java/lang/Integer:toString	()Ljava/lang/String;
    //   1211: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1214: pop
    //   1215: aload 15
    //   1217: ldc_w 544
    //   1220: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1223: pop
    //   1224: aload 9
    //   1226: invokevirtual 116	java/lang/Long:longValue	()J
    //   1229: lconst_0
    //   1230: lcmp
    //   1231: iflt +10 -> 1241
    //   1234: ldc_w 546
    //   1237: astore_2
    //   1238: goto +6 -> 1244
    //   1241: ldc 33
    //   1243: astore_2
    //   1244: aload 15
    //   1246: aload_2
    //   1247: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1250: pop
    //   1251: aload 15
    //   1253: aload 9
    //   1255: invokevirtual 547	java/lang/Long:toString	()Ljava/lang/String;
    //   1258: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1261: pop
    //   1262: aload 15
    //   1264: ldc_w 491
    //   1267: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: pop
    //   1271: aload 15
    //   1273: aload_1
    //   1274: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1277: pop
    //   1278: aload 15
    //   1280: ldc_w 491
    //   1283: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1286: pop
    //   1287: aload 15
    //   1289: invokestatic 179	com/bandwidthx/library/ac:e	()Ljava/lang/String;
    //   1292: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1295: pop
    //   1296: aload 15
    //   1298: ldc_w 409
    //   1301: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1304: pop
    //   1305: aload 15
    //   1307: aload 21
    //   1309: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1312: pop
    //   1313: aload 15
    //   1315: ldc_w 409
    //   1318: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1321: pop
    //   1322: aload 15
    //   1324: aload 12
    //   1326: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1329: pop
    //   1330: aload 15
    //   1332: ldc_w 409
    //   1335: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1338: pop
    //   1339: aload 15
    //   1341: aload 13
    //   1343: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1346: pop
    //   1347: aload 15
    //   1349: ldc_w 409
    //   1352: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1355: pop
    //   1356: aload 15
    //   1358: aload 10
    //   1360: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1363: pop
    //   1364: aload_0
    //   1365: bipush 101
    //   1367: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1370: ldc_w 549
    //   1373: aload 15
    //   1375: invokevirtual 493	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1378: invokevirtual 248	com/bandwidthx/library/bd:a	(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V
    //   1381: return
    //   1382: astore_2
    //   1383: goto -230 -> 1153
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1386	0	this	bd
    //   0	1386	1	paramA	ei.a
    //   0	1386	2	paramString	String
    //   0	1386	3	paramJSONObject	JSONObject
    //   1	1088	4	bool	boolean
    //   556	318	5	l1	long
    //   850	26	7	l2	long
    //   39	1215	9	localObject1	Object
    //   185	1174	10	str1	String
    //   24	1183	11	localObject2	Object
    //   144	1181	12	localObject3	Object
    //   169	1173	13	localObject4	Object
    //   105	1085	14	localObject5	Object
    //   52	1322	15	localObject6	Object
    //   215	871	16	localInteger	Integer
    //   227	281	17	localA	ei.a
    //   205	39	18	arrayOfString	String[]
    //   224	827	19	localObject7	Object
    //   251	726	20	str2	String
    //   131	1177	21	str3	String
    //   611	166	22	localObject8	Object
    //   763	40	23	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   565	572	587	java/lang/Exception
    //   628	643	646	java/lang/Exception
    //   665	680	646	java/lang/Exception
    //   695	710	646	java/lang/Exception
    //   725	740	646	java/lang/Exception
    //   860	873	884	java/lang/Exception
    //   888	893	898	java/lang/Exception
    //   765	852	902	java/lang/Exception
    //   613	623	906	java/lang/Exception
    //   650	660	906	java/lang/Exception
    //   680	690	906	java/lang/Exception
    //   710	720	906	java/lang/Exception
    //   740	765	906	java/lang/Exception
    //   604	613	910	java/lang/Exception
    //   591	601	914	java/lang/Exception
    //   551	558	918	java/lang/Exception
    //   1147	1151	1382	java/lang/Exception
  }
  
  public void b(String paramString1, String paramString2, String paramString3, String paramString4, String arg5)
  {
    synchronized (h)
    {
      if (this.j.size() >= 100) {
        this.j.remove(0);
      }
      a localA = new a();
      fi.f();
      localA.b = paramString1;
      localA.c = paramString2;
      localA.d = paramString3;
      localA.e = paramString4;
      this.j.add(localA);
      return;
    }
  }
  
  public void c()
  {
    a.Q().a(Integer.valueOf(78), Boolean.valueOf(false));
  }
  
  public void c(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    Object localObject3 = new String[6];
    int m = 0;
    localObject3[0] = "Lifetime";
    localObject3[1] = "Yearly";
    localObject3[2] = "Monthly";
    localObject3[3] = "Weekly";
    localObject3[4] = "Daily";
    localObject3[5] = "Hourly";
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    int k;
    for (paramA = Integer.valueOf(0);; paramA = Integer.valueOf(paramA.intValue() + 1))
    {
      k = m;
      if (paramA.intValue() >= 6) {
        break;
      }
      paramString = new StringBuilder();
      paramString.append("MOBILE ");
      paramString.append(localObject3[paramA.intValue()]);
      localLinkedHashMap.put(paramString.toString(), "");
    }
    for (;;)
    {
      paramA = Integer.valueOf(k);
      if (paramA.intValue() >= 6) {
        break;
      }
      paramString = new StringBuilder();
      paramString.append("WIFI ");
      paramString.append(localObject3[paramA.intValue()]);
      localLinkedHashMap.put(paramString.toString(), "");
      k = paramA.intValue() + 1;
    }
    paramString = Long.valueOf(0L);
    paramA = Long.valueOf(0L);
    Object localObject4 = a.al().m().iterator();
    Object localObject5;
    while (((Iterator)localObject4).hasNext())
    {
      localObject5 = (er.a)((Iterator)localObject4).next();
      paramJSONObject = paramA;
      localObject1 = paramString;
      if (((er.a)localObject5).c.intValue() < 6)
      {
        Iterator localIterator = ((er.a)localObject5).e.iterator();
        for (;;)
        {
          paramJSONObject = paramA;
          localObject1 = paramString;
          if (!localIterator.hasNext()) {
            break;
          }
          Object localObject6 = (er.b)localIterator.next();
          paramJSONObject = Long.valueOf(((er.b)localObject6).c.longValue() * 1000L + (((er.b)localObject6).e.longValue() - ((er.b)localObject6).d.longValue()) / 1000L);
          localObject1 = paramA;
          localObject2 = paramString;
          if (((er.a)localObject5).c.intValue() == 4) {
            if (((er.b)localObject6).b.contains("MOBILE"))
            {
              localObject2 = paramJSONObject;
              localObject1 = paramA;
            }
            else
            {
              localObject1 = paramA;
              localObject2 = paramString;
              if (((er.b)localObject6).b.contains("WIFI"))
              {
                localObject1 = paramJSONObject;
                localObject2 = paramString;
              }
            }
          }
          paramA = new StringBuilder();
          paramA.append(((er.b)localObject6).b);
          paramA.append(" ");
          paramA.append(localObject3[localObject5.c.intValue()]);
          paramString = paramA.toString();
          paramA = (String)localLinkedHashMap.get(paramString);
          localObject6 = new StringBuilder();
          ((StringBuilder)localObject6).append(paramA);
          if (paramA.length() > 0) {
            paramA = ", ";
          } else {
            paramA = "";
          }
          ((StringBuilder)localObject6).append(paramA);
          ((StringBuilder)localObject6).append(bg.e(paramJSONObject));
          localLinkedHashMap.put(paramString, ((StringBuilder)localObject6).toString());
          paramA = (ei.a)localObject1;
          paramString = (String)localObject2;
        }
      }
      paramA = paramJSONObject;
      paramString = (String)localObject1;
    }
    Object localObject1 = "";
    paramJSONObject = "";
    Object localObject2 = localLinkedHashMap.keySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = ((Iterator)localObject2).next();
      localObject4 = localLinkedHashMap.get(localObject3);
      if (localObject3.toString().contains("MOBILE"))
      {
        localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append((String)localObject1);
        if (((String)localObject1).length() > 0) {
          localObject1 = "\n";
        } else {
          localObject1 = "";
        }
        ((StringBuilder)localObject5).append((String)localObject1);
        ((StringBuilder)localObject5).append("- ");
        ((StringBuilder)localObject5).append(localObject3.toString().replace("MOBILE ", ""));
        ((StringBuilder)localObject5).append(": ");
        ((StringBuilder)localObject5).append(localObject4.toString());
        localObject1 = ((StringBuilder)localObject5).toString();
      }
      else if (localObject3.toString().contains("WIFI"))
      {
        localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append(paramJSONObject);
        if (paramJSONObject.length() > 0) {
          paramJSONObject = "\n";
        } else {
          paramJSONObject = "";
        }
        ((StringBuilder)localObject5).append(paramJSONObject);
        ((StringBuilder)localObject5).append("- ");
        ((StringBuilder)localObject5).append(localObject3.toString().replace("WIFI ", ""));
        ((StringBuilder)localObject5).append(": ");
        ((StringBuilder)localObject5).append(localObject4.toString());
        paramJSONObject = ((StringBuilder)localObject5).toString();
      }
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Today's usage ");
    ((StringBuilder)localObject2).append(bg.e(paramString));
    ((StringBuilder)localObject2).append(" cellular, ");
    ((StringBuilder)localObject2).append(bg.e(paramA));
    ((StringBuilder)localObject2).append(" wifi");
    paramA = ((StringBuilder)localObject2).toString();
    paramString = new StringBuilder();
    paramString.append(paramA);
    paramString.append("\n\nCELLULAR\n");
    paramString.append((String)localObject1);
    paramA = paramString.toString();
    paramString = new StringBuilder();
    paramString.append(paramA);
    paramString.append("\n\nWIFI\n");
    paramString.append(paramJSONObject);
    a(Integer.valueOf(102), "Statistics", paramString.toString());
  }
  
  public Boolean d()
  {
    return a.Q().a(Integer.valueOf(78));
  }
  
  public void d(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    w();
    paramA = "";
    if (a.X().g().booleanValue())
    {
      paramString = a.X().V().iterator();
      Object localObject;
      while (paramString.hasNext())
      {
        localObject = (fq.c)paramString.next();
        paramJSONObject = new StringBuilder();
        paramJSONObject.append(paramA);
        if (paramA.length() > 0) {
          paramA = "; ";
        } else {
          paramA = "";
        }
        paramJSONObject.append(paramA);
        if (((fq.c)localObject).b.length() > 0) {
          paramA = ((fq.c)localObject).b;
        } else {
          paramA = "[EMPTY]";
        }
        paramJSONObject.append(paramA);
        if (((fq.c)localObject).a.length() > 0)
        {
          paramA = new StringBuilder();
          paramA.append(" ");
          paramA.append(((fq.c)localObject).a);
          paramA = paramA.toString();
        }
        else
        {
          paramA = "";
        }
        paramJSONObject.append(paramA);
        paramA = paramJSONObject.toString();
      }
      paramString = a.ak().l().iterator();
      while (paramString.hasNext())
      {
        localObject = (eq.a)paramString.next();
        paramJSONObject = new StringBuilder();
        paramJSONObject.append(paramA);
        if (paramA.length() > 0) {
          paramA = "; ";
        } else {
          paramA = "";
        }
        paramJSONObject.append(paramA);
        paramJSONObject.append("(");
        paramJSONObject.append(((eq.a)localObject).c);
        if (((eq.a)localObject).b.length() > 0)
        {
          paramA = new StringBuilder();
          paramA.append(" ");
          paramA.append(((eq.a)localObject).b);
          paramA = paramA.toString();
        }
        else
        {
          paramA = "";
        }
        paramJSONObject.append(paramA);
        paramJSONObject.append(")");
        paramA = paramJSONObject.toString();
      }
      paramString = paramA;
      if (paramA.length() == 0) {
        paramString = "None";
      }
    }
    else
    {
      paramString = "Unable to get configureds, wifi disabled";
    }
    a(Integer.valueOf(103), "Configured", paramString);
    x();
  }
  
  public Boolean e()
  {
    boolean bool;
    if ((a.Q().a(Integer.valueOf(78)).booleanValue()) && (a.Q().a(Integer.valueOf(139)).booleanValue())) {
      bool = true;
    } else {
      bool = false;
    }
    return Boolean.valueOf(bool);
  }
  
  public void e(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    w();
    paramString = "USER-ADDED:";
    paramA = Integer.valueOf(0);
    Object localObject3 = a.ar().l().values().iterator();
    label103:
    label177:
    label238:
    label333:
    label353:
    label398:
    while (((Iterator)localObject3).hasNext())
    {
      localObject5 = (ex.b)((Iterator)localObject3).next();
      if (((ex.b)localObject5).b.intValue() == ex.d) {}
      try
      {
        paramJSONObject = Integer.valueOf(paramA.intValue() + 1);
      }
      catch (Exception paramJSONObject)
      {
        Object localObject1;
        Object localObject6;
        Object localObject8;
        for (;;) {}
      }
      try
      {
        paramA = ((ex.b)localObject5).j;
      }
      catch (Exception paramA)
      {
        paramA = paramJSONObject;
        break label353;
      }
      try
      {
        localObject1 = a.r().c(((ex.b)localObject5).j);
        paramA = (ei.a)localObject1;
      }
      catch (Exception localException1)
      {
        break label103;
        paramA = " (Open)";
        break label177;
        paramA = "";
        break label238;
        paramA = "";
        break label333;
        paramString = " (Open)";
        break label711;
        paramString = "";
        break label921;
        Object localObject2 = "";
        break label1614;
        localObject2 = "";
        break label1689;
      }
      localObject1 = paramA;
      if (paramA == null) {
        localObject1 = "";
      }
      localObject6 = new StringBuilder();
      ((StringBuilder)localObject6).append(paramString);
      ((StringBuilder)localObject6).append("\n- \"");
      ((StringBuilder)localObject6).append(((ex.b)localObject5).h);
      ((StringBuilder)localObject6).append("\"");
      if (((ex.b)localObject5).i.length() <= 0) {
        break label2602;
      }
      paramA = " (Secure)";
      ((StringBuilder)localObject6).append(paramA);
      if (((ex.b)localObject5).d.length() <= 0) {
        break label2609;
      }
      paramA = new StringBuilder();
      paramA.append(" ");
      paramA.append(((ex.b)localObject5).d.replace("\n", " "));
      paramA = paramA.toString();
      ((StringBuilder)localObject6).append(paramA);
      ((StringBuilder)localObject6).append(" ");
      ((StringBuilder)localObject6).append(((ex.b)localObject5).k.toString());
      ((StringBuilder)localObject6).append(",");
      ((StringBuilder)localObject6).append(((ex.b)localObject5).l.toString());
      if (((String)localObject1).length() <= 0) {
        break label2615;
      }
      paramA = new StringBuilder();
      paramA.append(" ");
      paramA.append(cg.d((String)localObject1));
      paramA = paramA.toString();
      ((StringBuilder)localObject6).append(paramA);
      paramA = ((StringBuilder)localObject6).toString();
      paramString = paramA;
      paramA = paramJSONObject;
      break label398;
      paramJSONObject = new StringBuilder();
      paramJSONObject.append(paramString);
      paramJSONObject.append("\n- \"");
      paramJSONObject.append(((ex.b)localObject5).h);
      paramJSONObject.append("\" ERROR");
      paramString = paramJSONObject.toString();
    }
    paramJSONObject = paramString;
    if (paramA.intValue() == 0)
    {
      paramJSONObject = new StringBuilder();
      paramJSONObject.append(paramString);
      paramJSONObject.append("\nNone");
      paramJSONObject = paramJSONObject.toString();
    }
    paramString = new StringBuilder();
    paramString.append(paramJSONObject);
    paramString.append("\n\nDOWNLOADED:");
    paramJSONObject = paramString.toString();
    paramString = Integer.valueOf(0);
    localObject6 = a.ar().l().values().iterator();
    label711:
    label921:
    label993:
    while (((Iterator)localObject6).hasNext())
    {
      localObject8 = (ex.b)((Iterator)localObject6).next();
      if (((ex.b)localObject8).b.intValue() == ex.c) {}
      for (;;)
      {
        try
        {
          localObject1 = Integer.valueOf(paramString.intValue() + 1);
        }
        catch (Exception localException2)
        {
          Object localObject9;
          continue;
        }
        try
        {
          paramString = ((ex.b)localObject8).i;
        }
        catch (Exception paramString)
        {
          paramString = localException2;
          continue;
        }
        try
        {
          if (((ex.b)localObject8).i.length() > 0)
          {
            localObject3 = a.r().c(((ex.b)localObject8).i);
            paramString = (String)localObject3;
          }
          else
          {
            paramString = "";
          }
        }
        catch (Exception localException4) {}
      }
      localObject3 = paramString;
      if (paramString == null) {
        localObject3 = "";
      }
      paramString = ((ex.b)localObject8).j;
      try
      {
        localObject5 = a.r().c(((ex.b)localObject8).j);
        paramString = (String)localObject5;
      }
      catch (Exception localException7)
      {
        for (;;) {}
      }
      localObject5 = paramString;
      if (paramString == null) {
        localObject5 = "";
      }
      localObject9 = new StringBuilder();
      ((StringBuilder)localObject9).append(paramJSONObject);
      ((StringBuilder)localObject9).append("\n- \"");
      ((StringBuilder)localObject9).append(((ex.b)localObject8).h);
      ((StringBuilder)localObject9).append("\"");
      if (((String)localObject3).length() <= 0) {
        break label2621;
      }
      paramString = new StringBuilder();
      paramString.append(" ");
      paramString.append(cg.d((String)localObject3));
      paramString = paramString.toString();
      ((StringBuilder)localObject9).append(paramString);
      ((StringBuilder)localObject9).append(" ");
      ((StringBuilder)localObject9).append(((ex.b)localObject8).A.toString());
      ((StringBuilder)localObject9).append(" ");
      ((StringBuilder)localObject9).append(((ex.b)localObject8).y.toString());
      ((StringBuilder)localObject9).append(" ");
      ((StringBuilder)localObject9).append(((ex.b)localObject8).t);
      ((StringBuilder)localObject9).append(" ");
      ((StringBuilder)localObject9).append(((ex.b)localObject8).w);
      ((StringBuilder)localObject9).append(" ");
      ((StringBuilder)localObject9).append(((ex.b)localObject8).d.replace("\n", " "));
      ((StringBuilder)localObject9).append(" ");
      ((StringBuilder)localObject9).append(((ex.b)localObject8).k.toString());
      ((StringBuilder)localObject9).append(",");
      ((StringBuilder)localObject9).append(((ex.b)localObject8).l.toString());
      if (((String)localObject5).length() <= 0) {
        break label2628;
      }
      paramString = new StringBuilder();
      paramString.append(" ");
      paramString.append(cg.d((String)localObject5));
      paramString = paramString.toString();
      ((StringBuilder)localObject9).append(paramString);
      paramString = ((StringBuilder)localObject9).toString();
      paramJSONObject = paramString;
      paramString = (String)localObject1;
      break label993;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramJSONObject);
      ((StringBuilder)localObject1).append("\n- \"");
      ((StringBuilder)localObject1).append(((ex.b)localObject8).h);
      ((StringBuilder)localObject1).append("\" ERROR");
      paramJSONObject = ((StringBuilder)localObject1).toString();
    }
    localObject1 = paramJSONObject;
    if (paramString.intValue() == 0)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramJSONObject);
      ((StringBuilder)localObject1).append("\nNone");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    paramJSONObject = new StringBuilder();
    paramJSONObject.append((String)localObject1);
    paramJSONObject.append("\n\nREGIONS:");
    localObject1 = paramJSONObject.toString();
    paramJSONObject = Integer.valueOf(0);
    Object localObject5 = a.ar().m().iterator();
    while (((Iterator)localObject5).hasNext())
    {
      localObject6 = (ex.b)((Iterator)localObject5).next();
      for (;;)
      {
        try
        {
          localObject3 = Integer.valueOf(paramJSONObject.intValue() + 1);
        }
        catch (Exception localException5)
        {
          Object localObject10;
          continue;
        }
        try
        {
          paramJSONObject = new StringBuilder();
          paramJSONObject.append((String)localObject1);
          paramJSONObject.append("\n- ");
          paramJSONObject.append(((ex.b)localObject6).k.toString());
          paramJSONObject.append(",");
          paramJSONObject.append(((ex.b)localObject6).l.toString());
          paramJSONObject.append(" ");
          paramJSONObject.append(((ex.b)localObject6).n);
          paramJSONObject.append("m");
          paramJSONObject = paramJSONObject.toString();
          localObject1 = paramJSONObject;
          paramJSONObject = (JSONObject)localObject3;
        }
        catch (Exception paramJSONObject)
        {
          paramJSONObject = localException5;
        }
      }
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append((String)localObject1);
      ((StringBuilder)localObject3).append("\n- \"");
      ((StringBuilder)localObject3).append(((ex.b)localObject6).h);
      ((StringBuilder)localObject3).append("\" ERROR");
      localObject1 = ((StringBuilder)localObject3).toString();
    }
    localObject3 = localObject1;
    if (paramJSONObject.intValue() == 0)
    {
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append((String)localObject1);
      ((StringBuilder)localObject3).append("\nNone");
      localObject3 = ((StringBuilder)localObject3).toString();
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append((String)localObject3);
    ((StringBuilder)localObject1).append("\n\nTRADERS:");
    localObject3 = ((StringBuilder)localObject1).toString();
    localObject1 = Integer.valueOf(0);
    localObject9 = a.aw().l().iterator();
    label1614:
    label1689:
    label1768:
    while (((Iterator)localObject9).hasNext())
    {
      localObject10 = (fc.a)((Iterator)localObject9).next();
      for (;;)
      {
        try
        {
          localObject5 = Integer.valueOf(((Integer)localObject1).intValue() + 1);
        }
        catch (Exception localException8)
        {
          StringBuilder localStringBuilder;
          continue;
        }
        try
        {
          localObject1 = ((fc.a)localObject10).l;
        }
        catch (Exception localException3)
        {
          localObject2 = localException8;
          continue;
        }
        try
        {
          if (((fc.a)localObject10).l.length() > 0)
          {
            localObject6 = a.r().c(((fc.a)localObject10).l);
            localObject1 = localObject6;
          }
          else
          {
            localObject1 = "";
          }
        }
        catch (Exception localException9) {}
      }
      localObject6 = localObject1;
      if (localObject1 == null) {
        localObject6 = "";
      }
      localObject1 = ((fc.a)localObject10).h;
      try
      {
        localObject8 = a.r().c(((fc.a)localObject10).h);
        localObject1 = localObject8;
      }
      catch (Exception localException12)
      {
        for (;;) {}
      }
      localObject8 = localObject1;
      if (localObject1 == null) {
        localObject8 = "";
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject3);
      localStringBuilder.append("\n- ");
      localStringBuilder.append(((fc.a)localObject10).b);
      localStringBuilder.append(" \"");
      localStringBuilder.append(((fc.a)localObject10).k);
      localStringBuilder.append("\"");
      if (((String)localObject6).length() <= 0) {
        break label2634;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(" ");
      ((StringBuilder)localObject1).append(cg.d((String)localObject6));
      localObject1 = ((StringBuilder)localObject1).toString();
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(" ");
      localStringBuilder.append(((fc.a)localObject10).c);
      if (((String)localObject8).length() <= 0) {
        break label2641;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(" ");
      ((StringBuilder)localObject1).append(cg.d((String)localObject8));
      localObject1 = ((StringBuilder)localObject1).toString();
      localStringBuilder.append((String)localObject1);
      localObject1 = localStringBuilder.toString();
      localObject3 = localObject1;
      localObject1 = localObject5;
      break label1768;
      localObject5 = new StringBuilder();
      ((StringBuilder)localObject5).append((String)localObject3);
      ((StringBuilder)localObject5).append("\n- \"");
      ((StringBuilder)localObject5).append(((fc.a)localObject10).b);
      ((StringBuilder)localObject5).append("\" ERROR");
      localObject3 = ((StringBuilder)localObject5).toString();
    }
    localObject5 = localObject3;
    if (((Integer)localObject1).intValue() == 0)
    {
      localObject5 = new StringBuilder();
      ((StringBuilder)localObject5).append((String)localObject3);
      ((StringBuilder)localObject5).append("\nNone");
      localObject5 = ((StringBuilder)localObject5).toString();
    }
    localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append((String)localObject5);
    ((StringBuilder)localObject3).append("\n\nSCOUTED:");
    localObject5 = ((StringBuilder)localObject3).toString();
    localObject3 = Integer.valueOf(0);
    localObject8 = a.j().a().l().iterator();
    while (((Iterator)localObject8).hasNext())
    {
      localObject9 = (String)((Iterator)localObject8).next();
      for (;;)
      {
        try
        {
          localObject6 = Integer.valueOf(((Integer)localObject3).intValue() + 1);
        }
        catch (Exception localException10)
        {
          continue;
        }
        try
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject5);
          ((StringBuilder)localObject3).append("\n- \"");
          ((StringBuilder)localObject3).append((String)localObject9);
          ((StringBuilder)localObject3).append("\"");
          localObject3 = ((StringBuilder)localObject3).toString();
          localObject5 = localObject3;
          localObject3 = localObject6;
        }
        catch (Exception localException6)
        {
          localObject4 = localException10;
        }
      }
      localObject6 = new StringBuilder();
      ((StringBuilder)localObject6).append((String)localObject5);
      ((StringBuilder)localObject6).append("\n- ERROR");
      localObject5 = ((StringBuilder)localObject6).toString();
    }
    localObject6 = localObject5;
    if (((Integer)localObject3).intValue() == 0)
    {
      localObject6 = new StringBuilder();
      ((StringBuilder)localObject6).append((String)localObject5);
      ((StringBuilder)localObject6).append("\nNone");
      localObject6 = ((StringBuilder)localObject6).toString();
    }
    localObject5 = new StringBuilder();
    ((StringBuilder)localObject5).append((String)localObject6);
    ((StringBuilder)localObject5).append("\n\nCAPTIVES CONNECTED:");
    localObject5 = ((StringBuilder)localObject5).toString();
    localObject6 = Integer.valueOf(0);
    localObject9 = a.ag().l().iterator();
    while (((Iterator)localObject9).hasNext())
    {
      localObject10 = (em.a)((Iterator)localObject9).next();
      for (;;)
      {
        try
        {
          localObject8 = Integer.valueOf(((Integer)localObject6).intValue() + 1);
        }
        catch (Exception localException13)
        {
          Object localObject4;
          continue;
        }
        try
        {
          localObject6 = new StringBuilder();
          ((StringBuilder)localObject6).append((String)localObject5);
          ((StringBuilder)localObject6).append("\n- \"");
          ((StringBuilder)localObject6).append(((em.a)localObject10).b);
          ((StringBuilder)localObject6).append("\" ");
          ((StringBuilder)localObject6).append(bg.b(Long.valueOf(((em.a)localObject10).c.longValue() * 1000L), Boolean.valueOf(true)));
          ((StringBuilder)localObject6).append(" ");
          ((StringBuilder)localObject6).append(bg.a(Long.valueOf(((em.a)localObject10).c.longValue() * 1000L), Boolean.valueOf(false)));
          localObject6 = ((StringBuilder)localObject6).toString();
          localObject5 = localObject6;
          localObject6 = localObject8;
        }
        catch (Exception localException11)
        {
          Object localObject7 = localException13;
        }
      }
      localObject8 = new StringBuilder();
      ((StringBuilder)localObject8).append((String)localObject5);
      ((StringBuilder)localObject8).append("\n- ERROR");
      localObject5 = ((StringBuilder)localObject8).toString();
    }
    localObject8 = localObject5;
    if (((Integer)localObject6).intValue() == 0)
    {
      localObject8 = new StringBuilder();
      ((StringBuilder)localObject8).append((String)localObject5);
      ((StringBuilder)localObject8).append("\nNone");
      localObject8 = ((StringBuilder)localObject8).toString();
    }
    localObject5 = new StringBuilder();
    ((StringBuilder)localObject5).append(paramA.toString());
    ((StringBuilder)localObject5).append(" user-added, ");
    ((StringBuilder)localObject5).append(paramString.toString());
    ((StringBuilder)localObject5).append(" downloaded, ");
    ((StringBuilder)localObject5).append(paramJSONObject.toString());
    ((StringBuilder)localObject5).append(" regions, ");
    ((StringBuilder)localObject5).append(((Integer)localObject1).toString());
    ((StringBuilder)localObject5).append(" traders, ");
    ((StringBuilder)localObject5).append(((Integer)localObject3).toString());
    ((StringBuilder)localObject5).append(" scouted, ");
    ((StringBuilder)localObject5).append(((Integer)localObject6).toString());
    ((StringBuilder)localObject5).append(" captives connected\n\n");
    ((StringBuilder)localObject5).append((String)localObject8);
    a(Integer.valueOf(104), "Preapproved", ((StringBuilder)localObject5).toString());
    x();
  }
  
  public Boolean f()
  {
    boolean bool;
    if ((a.Q().a(Integer.valueOf(78)).booleanValue()) && (a.Q().a(Integer.valueOf(140)).booleanValue())) {
      bool = true;
    } else {
      bool = false;
    }
    return Boolean.valueOf(bool);
  }
  
  public void f(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    paramA = a.af().l();
    paramString = new StringBuilder();
    paramString.append(Integer.toString(paramA.size()));
    paramString.append(" blacklisted\n");
    paramString = paramString.toString();
    Iterator localIterator = paramA.iterator();
    el.a localA;
    if (localIterator.hasNext())
    {
      localA = (el.a)localIterator.next();
      paramA = "General";
      paramJSONObject = paramString;
    }
    for (;;)
    {
      try
      {
        switch (localA.j.intValue())
        {
        default: 
          paramJSONObject = paramString;
          localObject = new StringBuilder();
          paramJSONObject = paramString;
          ((StringBuilder)localObject).append(paramString);
          paramJSONObject = paramString;
          ((StringBuilder)localObject).append("\n- \"");
          paramJSONObject = paramString;
          ((StringBuilder)localObject).append(localA.c);
          paramJSONObject = paramString;
          ((StringBuilder)localObject).append("\"");
          paramJSONObject = paramString;
          ((StringBuilder)localObject).append(bg.d(Integer.valueOf(30 - localA.c.length())));
          paramJSONObject = paramString;
          localObject = ((StringBuilder)localObject).toString();
          paramString = (String)localObject;
        }
      }
      catch (Exception paramA)
      {
        Object localObject;
        StringBuilder localStringBuilder;
        paramString = paramJSONObject;
      }
      try
      {
        localStringBuilder = new StringBuilder();
        paramString = (String)localObject;
        localStringBuilder.append((String)localObject);
        paramString = (String)localObject;
        localStringBuilder.append(" ");
        paramString = (String)localObject;
        if (localA.b.length() <= 0) {
          break label926;
        }
        paramString = (String)localObject;
        paramJSONObject = localA.b;
        paramString = (String)localObject;
        localStringBuilder.append(paramJSONObject);
        paramString = (String)localObject;
        localStringBuilder.append(bg.d(Integer.valueOf(19 - localA.b.length())));
        paramString = (String)localObject;
        localObject = localStringBuilder.toString();
        paramJSONObject = (JSONObject)localObject;
        paramString = new StringBuilder();
        paramJSONObject = (JSONObject)localObject;
        paramString.append(bg.a(localA.f, Integer.valueOf(6)));
        paramJSONObject = (JSONObject)localObject;
        paramString.append(",");
        paramJSONObject = (JSONObject)localObject;
        paramString.append(bg.a(localA.g, Integer.valueOf(6)));
        paramJSONObject = (JSONObject)localObject;
        paramString = paramString.toString();
        paramJSONObject = (JSONObject)localObject;
        localStringBuilder = new StringBuilder();
        paramJSONObject = (JSONObject)localObject;
        localStringBuilder.append((String)localObject);
        paramJSONObject = (JSONObject)localObject;
        localStringBuilder.append(" ");
        paramJSONObject = (JSONObject)localObject;
        localStringBuilder.append(paramString);
        paramJSONObject = (JSONObject)localObject;
        localStringBuilder.append(bg.d(Integer.valueOf(23 - paramString.length())));
        paramJSONObject = (JSONObject)localObject;
        localObject = localStringBuilder.toString();
        paramString = (String)localObject;
        if (localA.h.longValue() == Long.MAX_VALUE)
        {
          paramJSONObject = "never";
        }
        else
        {
          paramString = (String)localObject;
          paramJSONObject = new StringBuilder();
          paramString = (String)localObject;
          paramJSONObject.append("in ");
          paramString = (String)localObject;
          paramJSONObject.append(bg.d(Long.valueOf(localA.h.longValue() - fi.f().longValue()), Boolean.valueOf(false)));
          paramString = (String)localObject;
          paramJSONObject = paramJSONObject.toString();
        }
        paramString = (String)localObject;
        localStringBuilder = new StringBuilder();
        paramString = (String)localObject;
        localStringBuilder.append((String)localObject);
        paramString = (String)localObject;
        localStringBuilder.append(" Ending ");
        paramString = (String)localObject;
        localStringBuilder.append(paramJSONObject);
        paramString = (String)localObject;
        localStringBuilder.append(bg.d(Integer.valueOf(17 - paramJSONObject.length())));
        paramString = (String)localObject;
        localObject = localStringBuilder.toString();
        paramJSONObject = (JSONObject)localObject;
        paramString = new StringBuilder();
        paramJSONObject = (JSONObject)localObject;
        paramString.append((String)localObject);
        paramJSONObject = (JSONObject)localObject;
        paramString.append(" ");
        paramJSONObject = (JSONObject)localObject;
        paramString.append(paramA);
        paramJSONObject = (JSONObject)localObject;
        paramString.append(bg.d(Integer.valueOf(25 - paramA.length())));
        paramJSONObject = (JSONObject)localObject;
        paramString = paramString.toString();
      }
      catch (Exception paramA)
      {
        continue;
      }
      try
      {
        paramJSONObject = new StringBuilder();
        paramJSONObject.append(paramString);
        paramJSONObject.append(" ");
        if (localA.k.longValue() == 0L) {
          break label932;
        }
        paramA = new StringBuilder();
        paramA.append("Group ");
        paramA.append(localA.k.toString());
        paramA = paramA.toString();
        paramJSONObject.append(paramA);
        paramA = paramJSONObject.toString();
        paramString = paramA;
      }
      catch (Exception paramA)
      {
        continue;
      }
      break;
      break;
      a(Integer.valueOf(105), "Blacklisted", paramString);
      return;
      break;
      continue;
      paramA = "User intervention";
      continue;
      paramA = "Invalid ssl cert";
      continue;
      paramA = "Scout";
      continue;
      paramA = "Poor connection";
      continue;
      paramA = "No approval";
      continue;
      paramA = "Captive portal";
      continue;
      paramA = "Slow internet";
      continue;
      paramA = "No internet";
      continue;
      paramA = "Authentication failure";
      continue;
      paramA = "Network failure";
      continue;
      paramA = "By user";
      continue;
      label926:
      paramJSONObject = "";
      continue;
      label932:
      paramA = "";
    }
  }
  
  public Boolean g()
  {
    boolean bool;
    if ((a.Q().a(Integer.valueOf(78)).booleanValue()) && (a.Q().a(Integer.valueOf(141)).booleanValue())) {
      bool = true;
    } else {
      bool = false;
    }
    return Boolean.valueOf(bool);
  }
  
  public void g(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    paramA = paramJSONObject.optString("fil", "");
    a(Integer.valueOf(106), "Captives", a.n().c().a(paramA));
  }
  
  public Boolean h()
  {
    boolean bool;
    if ((a.Q().a(Integer.valueOf(78)).booleanValue()) && (a.Q().a(Integer.valueOf(142)).booleanValue())) {
      bool = true;
    } else {
      bool = false;
    }
    return Boolean.valueOf(bool);
  }
  
  public void h(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    paramA = a.T().ag();
    paramString = new StringBuilder();
    paramString.append(Integer.toString(paramA.split("\r\n").length));
    paramString.append(" transactions\r\n\r\n");
    paramString.append(paramA);
    a(Integer.valueOf(107), "Transaction", paramString.toString());
  }
  
  public Boolean i()
  {
    boolean bool;
    if ((a.Q().a(Integer.valueOf(78)).booleanValue()) && (a.Q().a(Integer.valueOf(143)).booleanValue())) {
      bool = true;
    } else {
      bool = false;
    }
    return Boolean.valueOf(bool);
  }
  
  public void i(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    ArrayList localArrayList = a.Q().f();
    Collections.sort(localArrayList, new Comparator()
    {
      public int a(eg.b paramAnonymousB1, eg.b paramAnonymousB2)
      {
        return paramAnonymousB1.f.compareTo(paramAnonymousB2.f);
      }
    });
    paramJSONObject = "";
    Integer localInteger = Integer.valueOf(0);
    Iterator localIterator = localArrayList.iterator();
    eg.b localB;
    JSONObject localJSONObject;
    if (localIterator.hasNext())
    {
      localB = (eg.b)localIterator.next();
      paramA = "";
      paramString = "";
      localJSONObject = paramJSONObject;
    }
    for (;;)
    {
      try
      {
        switch (localB.b.intValue())
        {
        case 3: 
          localJSONObject = paramJSONObject;
          paramA = Long.toString(((Long)localB.g).longValue());
          localJSONObject = paramJSONObject;
          paramString = Long.toString(((Long)localB.h).longValue());
          break;
        case 2: 
          localJSONObject = paramJSONObject;
          paramA = Integer.toString(((Integer)localB.g).intValue());
          localJSONObject = paramJSONObject;
          paramString = Integer.toString(((Integer)localB.h).intValue());
          break;
        case 1: 
          localJSONObject = paramJSONObject;
          paramA = new StringBuilder();
          localJSONObject = paramJSONObject;
          paramA.append("\"");
          localJSONObject = paramJSONObject;
          paramA.append((String)localB.g);
          localJSONObject = paramJSONObject;
          paramA.append("\"");
          localJSONObject = paramJSONObject;
          paramA = paramA.toString();
          localJSONObject = paramJSONObject;
          paramString = new StringBuilder();
          localJSONObject = paramJSONObject;
          paramString.append("\"");
          localJSONObject = paramJSONObject;
          paramString.append((String)localB.h);
          localJSONObject = paramJSONObject;
          paramString.append("\"");
          localJSONObject = paramJSONObject;
          paramString = paramString.toString();
          break;
        case 0: 
          localJSONObject = paramJSONObject;
          paramA = Boolean.toString(((Boolean)localB.g).booleanValue());
          localJSONObject = paramJSONObject;
          paramString = Boolean.toString(((Boolean)localB.h).booleanValue());
          localJSONObject = paramJSONObject;
          localBoolean = Boolean.valueOf(paramA.equals(paramString));
          localJSONObject = paramJSONObject;
          StringBuilder localStringBuilder = new StringBuilder();
          localJSONObject = paramJSONObject;
          localStringBuilder.append(paramJSONObject);
          localJSONObject = paramJSONObject;
          localStringBuilder.append("\n");
          localJSONObject = paramJSONObject;
          if (!localBoolean.booleanValue()) {
            break label706;
          }
          str = "-";
          localJSONObject = paramJSONObject;
          localStringBuilder.append(str);
          localJSONObject = paramJSONObject;
          localStringBuilder.append(" ");
          localJSONObject = paramJSONObject;
          localStringBuilder.append(localB.f);
          localJSONObject = paramJSONObject;
          localStringBuilder.append(" (");
          localJSONObject = paramJSONObject;
          localStringBuilder.append(localB.d);
          localJSONObject = paramJSONObject;
          localStringBuilder.append(") ");
          localJSONObject = paramJSONObject;
          localStringBuilder.append(bg.d(Integer.valueOf(70 - localB.f.length() - localB.d.length())));
          localJSONObject = paramJSONObject;
          localStringBuilder.append(" = ");
          localJSONObject = paramJSONObject;
          localStringBuilder.append(paramA);
          localJSONObject = paramJSONObject;
          paramA = localStringBuilder.toString();
        }
      }
      catch (Exception paramA)
      {
        Boolean localBoolean;
        paramJSONObject = localJSONObject;
      }
      try
      {
        if (!localBoolean.booleanValue())
        {
          paramJSONObject = new StringBuilder();
          paramJSONObject.append(paramA);
          paramJSONObject.append(" (default ");
          paramJSONObject.append(paramString);
          paramJSONObject.append(")");
          paramJSONObject = paramJSONObject.toString();
          localJSONObject = paramJSONObject;
          int k = localInteger.intValue();
          localInteger = Integer.valueOf(k + 1);
        }
      }
      catch (Exception paramString)
      {
        continue;
      }
      paramJSONObject = paramA;
      break;
      paramA = new StringBuilder();
      paramA.append(Integer.toString(localArrayList.size()));
      paramA.append(" settings (");
      paramA.append(localInteger.toString());
      paramA.append(" not default)\n");
      paramA.append(paramJSONObject);
      a(Integer.valueOf(108), "Settings", paramA.toString());
      return;
      break;
      continue;
      label706:
      String str = "+";
    }
  }
  
  public void j()
  {
    try
    {
      a.Q().a(Integer.valueOf(144), Boolean.valueOf(true));
      v();
      return;
    }
    catch (Exception localException)
    {
      cg.a(localException, Boolean.valueOf(false));
    }
  }
  
  public void j(ei.a paramA, String paramString, JSONObject paramJSONObject)
  {
    w();
    paramA = "";
    if (a.X().g().booleanValue())
    {
      paramString = a.X().c(Boolean.valueOf(false));
      paramJSONObject = paramString.iterator();
      while (paramJSONObject.hasNext())
      {
        fq.a localA = (fq.a)paramJSONObject.next();
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramA);
        if (paramA.length() > 0) {
          paramA = "\n";
        } else {
          paramA = "";
        }
        ((StringBuilder)localObject).append(paramA);
        ((StringBuilder)localObject).append("- \"");
        ((StringBuilder)localObject).append(localA.b);
        ((StringBuilder)localObject).append("\"");
        ((StringBuilder)localObject).append(bg.d(Integer.valueOf(30 - localA.b.length())));
        localObject = ((StringBuilder)localObject).toString();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localA.d);
        if (localA.i.booleanValue()) {
          paramA = "!";
        } else {
          paramA = "";
        }
        localStringBuilder.append(paramA);
        paramA = localStringBuilder.toString();
        localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)localObject);
        localStringBuilder.append("(");
        localStringBuilder.append(paramA);
        localStringBuilder.append(")");
        localStringBuilder.append(bg.d(Integer.valueOf(7 - paramA.length())));
        paramA = localStringBuilder.toString();
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramA);
        ((StringBuilder)localObject).append(" ");
        ((StringBuilder)localObject).append(localA.a);
        ((StringBuilder)localObject).append(bg.d(Integer.valueOf(19 - localA.a.length())));
        paramA = ((StringBuilder)localObject).toString();
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramA);
        ((StringBuilder)localObject).append(" ");
        ((StringBuilder)localObject).append(localA.c.toString());
        ((StringBuilder)localObject).append(" dBm");
        paramA = ((StringBuilder)localObject).toString();
      }
      if (paramA.length() > 0)
      {
        paramJSONObject = new StringBuilder();
        paramJSONObject.append(Integer.toString(paramString.size()));
        paramJSONObject.append(" availables\n\n");
        paramJSONObject.append(paramA);
        paramA = paramJSONObject.toString();
      }
      else
      {
        paramA = "0 availables";
      }
    }
    else
    {
      paramA = "Unable to get availables, wifi disabled";
    }
    a(Integer.valueOf(109), "Available", paramA);
    x();
  }
  
  public void k()
  {
    a.Q().a(Integer.valueOf(144), Boolean.valueOf(false));
  }
  
  /* Error */
  public void k(ei.a arg1, String arg2, JSONObject arg3)
  {
    // Byte code:
    //   0: getstatic 81	com/bandwidthx/library/bd:a	Lcom/bandwidthx/library/ch;
    //   3: invokevirtual 384	com/bandwidthx/library/ch:P	()Lcom/bandwidthx/library/ec;
    //   6: invokevirtual 388	com/bandwidthx/library/ec:k	()Ljava/lang/String;
    //   9: astore 23
    //   11: lconst_0
    //   12: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   15: astore 20
    //   17: lconst_0
    //   18: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   21: astore 25
    //   23: lconst_0
    //   24: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   27: astore_2
    //   28: lconst_0
    //   29: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   32: astore 21
    //   34: new 74	java/util/ArrayList
    //   37: dup
    //   38: invokespecial 75	java/util/ArrayList:<init>	()V
    //   41: astore 24
    //   43: new 568	java/util/LinkedHashMap
    //   46: dup
    //   47: invokespecial 569	java/util/LinkedHashMap:<init>	()V
    //   50: astore 19
    //   52: new 568	java/util/LinkedHashMap
    //   55: dup
    //   56: invokespecial 569	java/util/LinkedHashMap:<init>	()V
    //   59: astore 22
    //   61: new 568	java/util/LinkedHashMap
    //   64: dup
    //   65: invokespecial 569	java/util/LinkedHashMap:<init>	()V
    //   68: astore 30
    //   70: new 568	java/util/LinkedHashMap
    //   73: dup
    //   74: invokespecial 569	java/util/LinkedHashMap:<init>	()V
    //   77: astore 31
    //   79: ldc 33
    //   81: astore_3
    //   82: aload 23
    //   84: ldc_w 909
    //   87: invokevirtual 413	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   90: astore 18
    //   92: aload 18
    //   94: arraylength
    //   95: iconst_2
    //   96: if_icmplt +89 -> 185
    //   99: new 499	java/text/SimpleDateFormat
    //   102: dup
    //   103: ldc_w 977
    //   106: getstatic 507	java/util/Locale:US	Ljava/util/Locale;
    //   109: invokespecial 510	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   112: astore_1
    //   113: aload 18
    //   115: iconst_0
    //   116: aaload
    //   117: astore 17
    //   119: aload_1
    //   120: aload 17
    //   122: invokevirtual 514	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   125: astore 17
    //   127: aload 17
    //   129: invokevirtual 519	java/util/Date:getTime	()J
    //   132: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   135: astore 17
    //   137: aload_1
    //   138: aload 18
    //   140: aload 18
    //   142: arraylength
    //   143: iconst_1
    //   144: isub
    //   145: aaload
    //   146: invokevirtual 514	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   149: invokevirtual 519	java/util/Date:getTime	()J
    //   152: lstore 8
    //   154: lload 8
    //   156: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   159: astore 21
    //   161: goto +47 -> 208
    //   164: astore_2
    //   165: aload 17
    //   167: astore_1
    //   168: goto +7 -> 175
    //   171: astore_1
    //   172: goto +19 -> 191
    //   175: aload_2
    //   176: astore 17
    //   178: goto +18 -> 196
    //   181: astore_1
    //   182: goto +9 -> 191
    //   185: aload_2
    //   186: astore_1
    //   187: goto +18 -> 205
    //   190: astore_1
    //   191: aload_1
    //   192: astore 17
    //   194: aload_2
    //   195: astore_1
    //   196: aload 17
    //   198: iconst_0
    //   199: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   202: invokestatic 202	com/bandwidthx/library/cg:a	(Ljava/lang/Throwable;Ljava/lang/Boolean;)V
    //   205: aload_1
    //   206: astore 17
    //   208: aload 25
    //   210: astore_1
    //   211: aload 20
    //   213: astore_2
    //   214: aload 18
    //   216: arraylength
    //   217: istore 4
    //   219: iconst_0
    //   220: istore 5
    //   222: aload 17
    //   224: astore 20
    //   226: aload 23
    //   228: astore 17
    //   230: iload 5
    //   232: iload 4
    //   234: if_icmpge +451 -> 685
    //   237: aload 18
    //   239: iload 5
    //   241: aaload
    //   242: astore 25
    //   244: aload 25
    //   246: ldc_w 979
    //   249: invokevirtual 457	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   252: iconst_4
    //   253: iadd
    //   254: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   257: astore 26
    //   259: aload 26
    //   261: invokevirtual 374	java/lang/Integer:intValue	()I
    //   264: istore 6
    //   266: aload 26
    //   268: invokevirtual 374	java/lang/Integer:intValue	()I
    //   271: istore 7
    //   273: aload 25
    //   275: iload 6
    //   277: iload 7
    //   279: iconst_3
    //   280: iadd
    //   281: invokevirtual 449	java/lang/String:substring	(II)Ljava/lang/String;
    //   284: invokevirtual 982	java/lang/String:trim	()Ljava/lang/String;
    //   287: invokestatic 985	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   290: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   293: astore 23
    //   295: aload 24
    //   297: aload 23
    //   299: invokevirtual 987	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   302: ifne +11 -> 313
    //   305: aload 24
    //   307: aload 23
    //   309: invokevirtual 357	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   312: pop
    //   313: aload 22
    //   315: aload 23
    //   317: invokevirtual 990	java/util/LinkedHashMap:containsKey	(Ljava/lang/Object;)Z
    //   320: ifeq +1002 -> 1322
    //   323: aload 22
    //   325: aload 23
    //   327: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   330: checkcast 237	java/lang/Integer
    //   333: invokevirtual 374	java/lang/Integer:intValue	()I
    //   336: iconst_1
    //   337: iadd
    //   338: istore 6
    //   340: goto +3 -> 343
    //   343: aload 22
    //   345: aload 23
    //   347: iload 6
    //   349: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   352: invokevirtual 575	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   355: pop
    //   356: aload 25
    //   358: ldc_w 716
    //   361: aload 26
    //   363: invokevirtual 374	java/lang/Integer:intValue	()I
    //   366: invokevirtual 992	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   369: iconst_1
    //   370: iadd
    //   371: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   374: astore 26
    //   376: aload 25
    //   378: ldc_w 716
    //   381: aload 26
    //   383: invokevirtual 374	java/lang/Integer:intValue	()I
    //   386: invokevirtual 992	java/lang/String:indexOf	(Ljava/lang/String;I)I
    //   389: istore 6
    //   391: aload 19
    //   393: aload 23
    //   395: aload 25
    //   397: aload 26
    //   399: invokevirtual 374	java/lang/Integer:intValue	()I
    //   402: iload 6
    //   404: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   407: invokevirtual 374	java/lang/Integer:intValue	()I
    //   410: invokevirtual 449	java/lang/String:substring	(II)Ljava/lang/String;
    //   413: invokevirtual 575	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   416: pop
    //   417: aload 25
    //   419: ldc_w 994
    //   422: invokevirtual 457	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   425: iconst_5
    //   426: iadd
    //   427: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   430: astore 26
    //   432: aload 25
    //   434: aload 26
    //   436: invokevirtual 374	java/lang/Integer:intValue	()I
    //   439: aload 26
    //   441: invokevirtual 374	java/lang/Integer:intValue	()I
    //   444: bipush 8
    //   446: iadd
    //   447: invokevirtual 449	java/lang/String:substring	(II)Ljava/lang/String;
    //   450: invokevirtual 982	java/lang/String:trim	()Ljava/lang/String;
    //   453: invokestatic 985	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   456: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   459: astore 26
    //   461: aload 30
    //   463: aload 23
    //   465: invokevirtual 990	java/util/LinkedHashMap:containsKey	(Ljava/lang/Object;)Z
    //   468: istore 16
    //   470: iload 16
    //   472: ifeq +39 -> 511
    //   475: aload 30
    //   477: aload 23
    //   479: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   482: checkcast 54	java/lang/Long
    //   485: invokevirtual 116	java/lang/Long:longValue	()J
    //   488: lstore 8
    //   490: aload 26
    //   492: invokevirtual 374	java/lang/Integer:intValue	()I
    //   495: istore 6
    //   497: lload 8
    //   499: iload 6
    //   501: i2l
    //   502: ladd
    //   503: lstore 8
    //   505: goto +14 -> 519
    //   508: goto +165 -> 673
    //   511: aload 26
    //   513: invokevirtual 374	java/lang/Integer:intValue	()I
    //   516: i2l
    //   517: lstore 8
    //   519: aload 30
    //   521: aload 23
    //   523: lload 8
    //   525: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   528: invokevirtual 575	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   531: pop
    //   532: aload_2
    //   533: invokevirtual 116	java/lang/Long:longValue	()J
    //   536: lstore 10
    //   538: aload 26
    //   540: invokevirtual 374	java/lang/Integer:intValue	()I
    //   543: istore 6
    //   545: iload 6
    //   547: i2l
    //   548: lstore 12
    //   550: aload 25
    //   552: aload 25
    //   554: ldc_w 996
    //   557: invokevirtual 457	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   560: bipush 9
    //   562: iadd
    //   563: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   566: invokevirtual 374	java/lang/Integer:intValue	()I
    //   569: invokevirtual 998	java/lang/String:substring	(I)Ljava/lang/String;
    //   572: invokevirtual 982	java/lang/String:trim	()Ljava/lang/String;
    //   575: invokestatic 985	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   578: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   581: astore_2
    //   582: aload 31
    //   584: aload 23
    //   586: invokevirtual 990	java/util/LinkedHashMap:containsKey	(Ljava/lang/Object;)Z
    //   589: ifeq +27 -> 616
    //   592: aload 31
    //   594: aload 23
    //   596: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   599: checkcast 54	java/lang/Long
    //   602: invokevirtual 116	java/lang/Long:longValue	()J
    //   605: aload_2
    //   606: invokevirtual 374	java/lang/Integer:intValue	()I
    //   609: i2l
    //   610: ladd
    //   611: lstore 8
    //   613: goto +10 -> 623
    //   616: aload_2
    //   617: invokevirtual 374	java/lang/Integer:intValue	()I
    //   620: i2l
    //   621: lstore 8
    //   623: aload 31
    //   625: aload 23
    //   627: lload 8
    //   629: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   632: invokevirtual 575	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   635: pop
    //   636: aload_1
    //   637: invokevirtual 116	java/lang/Long:longValue	()J
    //   640: lstore 8
    //   642: aload_2
    //   643: invokevirtual 374	java/lang/Integer:intValue	()I
    //   646: i2l
    //   647: lstore 14
    //   649: lload 8
    //   651: lload 14
    //   653: ladd
    //   654: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   657: astore_1
    //   658: lload 10
    //   660: lload 12
    //   662: ladd
    //   663: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   666: astore_2
    //   667: goto +6 -> 673
    //   670: goto +3 -> 673
    //   673: iload 5
    //   675: iconst_1
    //   676: iadd
    //   677: istore 5
    //   679: goto -449 -> 230
    //   682: goto +399 -> 1081
    //   685: aload 24
    //   687: new 14	com/bandwidthx/library/bd$4
    //   690: dup
    //   691: aload_0
    //   692: aload 22
    //   694: invokespecial 1001	com/bandwidthx/library/bd$4:<init>	(Lcom/bandwidthx/library/bd;Ljava/util/LinkedHashMap;)V
    //   697: invokestatic 922	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   700: aload 24
    //   702: invokevirtual 280	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   705: astore 32
    //   707: aload_3
    //   708: astore 28
    //   710: aload_2
    //   711: astore 29
    //   713: aload_1
    //   714: astore 27
    //   716: aload 17
    //   718: astore 23
    //   720: aload 20
    //   722: astore 26
    //   724: aload 21
    //   726: astore 25
    //   728: aload 18
    //   730: astore 24
    //   732: aload 32
    //   734: invokeinterface 285 1 0
    //   739: ifeq +376 -> 1115
    //   742: aload 32
    //   744: invokeinterface 289 1 0
    //   749: checkcast 237	java/lang/Integer
    //   752: astore 24
    //   754: new 474	java/lang/StringBuilder
    //   757: dup
    //   758: invokespecial 475	java/lang/StringBuilder:<init>	()V
    //   761: astore 25
    //   763: aload 25
    //   765: aload_3
    //   766: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   769: pop
    //   770: aload_3
    //   771: invokevirtual 391	java/lang/String:length	()I
    //   774: ifle +554 -> 1328
    //   777: ldc_w 909
    //   780: astore 23
    //   782: goto +3 -> 785
    //   785: aload 25
    //   787: aload 23
    //   789: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   792: pop
    //   793: aload 25
    //   795: aload 22
    //   797: aload 24
    //   799: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   802: checkcast 237	java/lang/Integer
    //   805: invokevirtual 374	java/lang/Integer:intValue	()I
    //   808: invokestatic 489	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   811: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   814: pop
    //   815: aload 25
    //   817: iconst_4
    //   818: aload 22
    //   820: aload 24
    //   822: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   825: checkcast 237	java/lang/Integer
    //   828: invokevirtual 374	java/lang/Integer:intValue	()I
    //   831: invokestatic 489	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   834: invokevirtual 391	java/lang/String:length	()I
    //   837: isub
    //   838: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   841: invokestatic 848	com/bandwidthx/library/bg:d	(Ljava/lang/Integer;)Ljava/lang/String;
    //   844: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   847: pop
    //   848: aload 25
    //   850: ldc_w 1003
    //   853: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   856: pop
    //   857: aload 25
    //   859: aload 24
    //   861: invokevirtual 542	java/lang/Integer:toString	()Ljava/lang/String;
    //   864: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   867: pop
    //   868: aload 25
    //   870: iconst_4
    //   871: aload 24
    //   873: invokevirtual 542	java/lang/Integer:toString	()Ljava/lang/String;
    //   876: invokevirtual 391	java/lang/String:length	()I
    //   879: isub
    //   880: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   883: invokestatic 848	com/bandwidthx/library/bg:d	(Ljava/lang/Integer;)Ljava/lang/String;
    //   886: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   889: pop
    //   890: aload 25
    //   892: ldc_w 716
    //   895: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   898: pop
    //   899: aload 25
    //   901: aload 19
    //   903: aload 24
    //   905: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   908: checkcast 125	java/lang/String
    //   911: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   914: pop
    //   915: aload 25
    //   917: ldc_w 716
    //   920: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   923: pop
    //   924: aload 25
    //   926: bipush 20
    //   928: aload 19
    //   930: aload 24
    //   932: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   935: checkcast 125	java/lang/String
    //   938: invokevirtual 391	java/lang/String:length	()I
    //   941: isub
    //   942: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   945: invokestatic 848	com/bandwidthx/library/bg:d	(Ljava/lang/Integer;)Ljava/lang/String;
    //   948: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   951: pop
    //   952: aload 25
    //   954: ldc_w 1005
    //   957: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   960: pop
    //   961: aload 25
    //   963: aload 30
    //   965: aload 24
    //   967: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   970: checkcast 54	java/lang/Long
    //   973: invokevirtual 116	java/lang/Long:longValue	()J
    //   976: invokestatic 930	java/lang/Long:toString	(J)Ljava/lang/String;
    //   979: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   982: pop
    //   983: aload 25
    //   985: bipush 10
    //   987: aload 30
    //   989: aload 24
    //   991: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   994: checkcast 54	java/lang/Long
    //   997: invokevirtual 116	java/lang/Long:longValue	()J
    //   1000: invokestatic 930	java/lang/Long:toString	(J)Ljava/lang/String;
    //   1003: invokevirtual 391	java/lang/String:length	()I
    //   1006: isub
    //   1007: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1010: invokestatic 848	com/bandwidthx/library/bg:d	(Ljava/lang/Integer;)Ljava/lang/String;
    //   1013: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1016: pop
    //   1017: aload 25
    //   1019: ldc_w 1007
    //   1022: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1025: pop
    //   1026: aload 25
    //   1028: aload 31
    //   1030: aload 24
    //   1032: invokevirtual 606	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1035: checkcast 54	java/lang/Long
    //   1038: invokevirtual 116	java/lang/Long:longValue	()J
    //   1041: invokestatic 930	java/lang/Long:toString	(J)Ljava/lang/String;
    //   1044: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1047: pop
    //   1048: aload 25
    //   1050: invokevirtual 493	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1053: astore 23
    //   1055: aload 23
    //   1057: astore_3
    //   1058: goto -351 -> 707
    //   1061: astore 19
    //   1063: goto -381 -> 682
    //   1066: astore 19
    //   1068: goto +13 -> 1081
    //   1071: astore 19
    //   1073: aload 17
    //   1075: astore 20
    //   1077: aload 23
    //   1079: astore 17
    //   1081: aload 19
    //   1083: iconst_0
    //   1084: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1087: invokestatic 202	com/bandwidthx/library/cg:a	(Ljava/lang/Throwable;Ljava/lang/Boolean;)V
    //   1090: aload 18
    //   1092: astore 24
    //   1094: aload 21
    //   1096: astore 25
    //   1098: aload 20
    //   1100: astore 26
    //   1102: aload 17
    //   1104: astore 23
    //   1106: aload_1
    //   1107: astore 27
    //   1109: aload_2
    //   1110: astore 29
    //   1112: aload_3
    //   1113: astore 28
    //   1115: new 474	java/lang/StringBuilder
    //   1118: dup
    //   1119: invokespecial 475	java/lang/StringBuilder:<init>	()V
    //   1122: astore_2
    //   1123: aload_2
    //   1124: aload 24
    //   1126: arraylength
    //   1127: invokestatic 489	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   1130: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1133: pop
    //   1134: aload_2
    //   1135: ldc_w 1009
    //   1138: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1141: pop
    //   1142: aload_2
    //   1143: aload 25
    //   1145: invokevirtual 116	java/lang/Long:longValue	()J
    //   1148: aload 26
    //   1150: invokevirtual 116	java/lang/Long:longValue	()J
    //   1153: lsub
    //   1154: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1157: iconst_0
    //   1158: invokestatic 50	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1161: invokestatic 863	com/bandwidthx/library/bg:d	(Ljava/lang/Long;Ljava/lang/Boolean;)Ljava/lang/String;
    //   1164: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1167: pop
    //   1168: aload_2
    //   1169: ldc_w 541
    //   1172: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1175: pop
    //   1176: aload_2
    //   1177: aload 29
    //   1179: invokevirtual 547	java/lang/Long:toString	()Ljava/lang/String;
    //   1182: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1185: pop
    //   1186: aload_2
    //   1187: ldc_w 1011
    //   1190: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1193: pop
    //   1194: aload_2
    //   1195: aload 27
    //   1197: invokevirtual 547	java/lang/Long:toString	()Ljava/lang/String;
    //   1200: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1203: pop
    //   1204: aload_2
    //   1205: ldc_w 1013
    //   1208: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1211: pop
    //   1212: aload 28
    //   1214: invokevirtual 391	java/lang/String:length	()I
    //   1217: ifle +34 -> 1251
    //   1220: new 474	java/lang/StringBuilder
    //   1223: dup
    //   1224: invokespecial 475	java/lang/StringBuilder:<init>	()V
    //   1227: astore_1
    //   1228: aload_1
    //   1229: aload 28
    //   1231: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1234: pop
    //   1235: aload_1
    //   1236: ldc_w 1015
    //   1239: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1242: pop
    //   1243: aload_1
    //   1244: invokevirtual 493	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1247: astore_1
    //   1248: goto +6 -> 1254
    //   1251: ldc 33
    //   1253: astore_1
    //   1254: aload_2
    //   1255: aload_1
    //   1256: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1259: pop
    //   1260: aload_2
    //   1261: aload 23
    //   1263: invokevirtual 481	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1266: pop
    //   1267: aload_0
    //   1268: bipush 108
    //   1270: invokestatic 240	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1273: ldc_w 1017
    //   1276: aload_2
    //   1277: invokevirtual 493	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1280: invokevirtual 248	com/bandwidthx/library/bd:a	(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V
    //   1283: return
    //   1284: astore 23
    //   1286: goto -613 -> 673
    //   1289: astore 23
    //   1291: goto -618 -> 673
    //   1294: astore 23
    //   1296: goto -623 -> 673
    //   1299: astore 23
    //   1301: goto -631 -> 670
    //   1304: astore 23
    //   1306: goto -798 -> 508
    //   1309: astore 23
    //   1311: goto -803 -> 508
    //   1314: astore_1
    //   1315: goto -642 -> 673
    //   1318: astore_2
    //   1319: goto -661 -> 658
    //   1322: iconst_1
    //   1323: istore 6
    //   1325: goto -982 -> 343
    //   1328: ldc 33
    //   1330: astore 23
    //   1332: goto -547 -> 785
    // Exception table:
    //   from	to	target	type
    //   137	154	164	java/lang/Exception
    //   127	137	171	java/lang/Exception
    //   99	113	181	java/lang/Exception
    //   119	127	181	java/lang/Exception
    //   92	99	190	java/lang/Exception
    //   732	777	1061	java/lang/Exception
    //   785	1055	1061	java/lang/Exception
    //   685	707	1066	java/lang/Exception
    //   214	219	1071	java/lang/Exception
    //   244	259	1284	java/lang/Exception
    //   259	273	1289	java/lang/Exception
    //   273	313	1294	java/lang/Exception
    //   313	340	1294	java/lang/Exception
    //   343	376	1294	java/lang/Exception
    //   376	470	1299	java/lang/Exception
    //   475	497	1304	java/lang/Exception
    //   511	519	1309	java/lang/Exception
    //   519	545	1309	java/lang/Exception
    //   550	613	1318	java/lang/Exception
    //   616	623	1318	java/lang/Exception
    //   623	649	1318	java/lang/Exception
  }
  
  public Boolean l()
  {
    return a.Q().a(Integer.valueOf(144));
  }
  
  public Boolean m()
  {
    try
    {
      if ((c != null) && (c.getView() != null) && (c.getView().getWindowVisibility() == 0)) {
        return Boolean.valueOf(true);
      }
      if ((g != null) && (g.isSpeaking())) {
        return Boolean.valueOf(true);
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return Boolean.valueOf(false);
  }
  
  public ArrayList<c> o()
  {
    synchronized (h)
    {
      ArrayList localArrayList = this.i;
      this.i = new ArrayList();
      return localArrayList;
    }
  }
  
  public ArrayList<a> p()
  {
    synchronized (h)
    {
      ArrayList localArrayList = this.j;
      this.j = new ArrayList();
      return localArrayList;
    }
  }
  
  public ArrayList<b> q()
  {
    localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        localObject1 = a;
        localPackageManager = ch.e().getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
        if (localIterator.hasNext())
        {
          localObject1 = (ApplicationInfo)localIterator.next();
          localObject3 = null;
        }
      }
      catch (Exception localException1)
      {
        Object localObject1;
        PackageManager localPackageManager;
        String str;
        PackageInfo localPackageInfo;
        int m;
        int k;
        return localArrayList;
      }
      try
      {
        a.i();
        str = ac.c(Integer.valueOf(((ApplicationInfo)localObject1).uid));
        localPackageInfo = localPackageManager.getPackageInfo(str, 4098);
        localObject1 = localObject3;
        if (localPackageInfo != null)
        {
          ActivityInfo[] arrayOfActivityInfo = localPackageInfo.receivers;
          localObject1 = localObject3;
          if (arrayOfActivityInfo != null)
          {
            m = arrayOfActivityInfo.length;
            k = 0;
            localObject1 = localObject3;
            if (k < m) {
              if (arrayOfActivityInfo[k].name.contains("BxReceiver")) {
                localObject1 = new b();
              }
            }
          }
        }
      }
      catch (Exception localException2)
      {
        Object localObject2 = localObject3;
        continue;
      }
      try
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
        ((StringBuilder)localObject3).append(" ");
        ((StringBuilder)localObject3).append(localPackageInfo.versionName);
        ((b)localObject1).a = ((StringBuilder)localObject3).toString();
        ((b)localObject1).b = str;
        ((b)localObject1).c = Long.valueOf(localPackageInfo.lastUpdateTime);
        if (localPackageInfo.requestedPermissions != null)
        {
          localObject3 = localPackageInfo.requestedPermissions;
          m = localObject3.length;
          k = 0;
          if (k < m)
          {
            str = localObject3[k];
            ((b)localObject1).d.add(str);
            k += 1;
            continue;
          }
        }
      }
      catch (Exception localException3)
      {
        continue;
      }
      k += 1;
      continue;
      if (localObject1 != null) {
        localArrayList.add(localObject1);
      }
    }
    return localArrayList;
  }
  
  public String r()
  {
    Object localObject1 = Long.valueOf((fi.f().longValue() - System.currentTimeMillis()) / 1000L);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(bg.b(fi.f(), Boolean.valueOf(false)).toString());
    ((StringBuilder)localObject2).append(" ");
    ((StringBuilder)localObject2).append(bg.a(fi.f(), Boolean.valueOf(false)));
    if (Math.abs(((Long)localObject1).longValue()) > 1L)
    {
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(" ");
      ((StringBuilder)localObject3).append(((Long)localObject1).toString());
      localObject1 = ((StringBuilder)localObject3).toString();
    }
    else
    {
      localObject1 = "";
    }
    ((StringBuilder)localObject2).append((String)localObject1);
    String str = ((StringBuilder)localObject2).toString();
    if (a.q().e().booleanValue())
    {
      localObject1 = a.q().h();
      if (((Long)localObject1).longValue() == Long.MAX_VALUE)
      {
        localObject1 = " forever";
      }
      else if (((Long)localObject1).longValue() > 1L)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(" for ");
        ((StringBuilder)localObject2).append(bg.d((Long)localObject1, Boolean.valueOf(false)));
        localObject1 = ((StringBuilder)localObject2).toString();
      }
      else
      {
        localObject1 = "";
      }
      localObject3 = a.q().g();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Suspended");
      ((StringBuilder)localObject2).append((String)localObject1);
      if (((String)localObject3).length() > 0)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        if (((String)localObject1).length() > 0) {
          localObject1 = " and";
        } else {
          localObject1 = "";
        }
        localStringBuilder.append((String)localObject1);
        localStringBuilder.append(" as long as \"");
        localStringBuilder.append((String)localObject3);
        localStringBuilder.append("\" is still available");
        localObject1 = localStringBuilder.toString();
      }
      else
      {
        localObject1 = "";
      }
      ((StringBuilder)localObject2).append((String)localObject1);
      localObject1 = ((StringBuilder)localObject2).toString();
    }
    else if (a.X().k().booleanValue())
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Connected to \"");
      ((StringBuilder)localObject1).append(a.X().y());
      ((StringBuilder)localObject1).append("\"");
      localObject2 = ((StringBuilder)localObject1).toString();
      localObject3 = a.aa().l();
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject1 = localObject2;
        if (((fd.a)localObject3).m.size() > 0)
        {
          localObject1 = localObject2;
          if (!((fd.c)((fd.a)localObject3).m.get(0)).S.booleanValue())
          {
            localObject1 = localObject2;
            if (((fd.c)((fd.a)localObject3).m.get(0)).T.intValue() == 0)
            {
              long l1 = ((fd.c)((fd.a)localObject3).m.get(0)).d.longValue();
              long l2 = ((fd.c)((fd.a)localObject3).m.get(0)).c.longValue();
              long l3 = ((fd.c)((fd.a)localObject3).m.get(0)).f.longValue();
              long l4 = ((fd.c)((fd.a)localObject3).m.get(0)).e.longValue();
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append((String)localObject2);
              ((StringBuilder)localObject1).append(" for ");
              ((StringBuilder)localObject1).append(bg.d(Long.valueOf(l1 - l2), Boolean.valueOf(false)));
              ((StringBuilder)localObject1).append(" and ");
              ((StringBuilder)localObject1).append(bg.a(Long.valueOf(l3 - l4), Integer.valueOf(0)));
              localObject1 = ((StringBuilder)localObject1).toString();
            }
          }
        }
      }
    }
    else if (a.X().u().booleanValue())
    {
      localObject1 = "Airplane mode";
    }
    else
    {
      localObject1 = "Not connected";
      a.j().o();
    }
    localObject2 = "";
    try
    {
      localObject3 = ac.d();
      localObject2 = localObject3;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    Object localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append((String)localObject1);
    ((StringBuilder)localObject3).append(" at ");
    ((StringBuilder)localObject3).append(str);
    ((StringBuilder)localObject3).append(", Build ");
    ((StringBuilder)localObject3).append(ac.i());
    ((StringBuilder)localObject3).append(" ");
    ((StringBuilder)localObject3).append(ac.h());
    ((StringBuilder)localObject3).append(" ");
    ((StringBuilder)localObject3).append(ac.j());
    ((StringBuilder)localObject3).append(" ");
    ((StringBuilder)localObject3).append((String)localObject2);
    ((StringBuilder)localObject3).append(", ");
    ((StringBuilder)localObject3).append(a.w().c());
    return ((StringBuilder)localObject3).toString();
  }
  
  public class a
  {
    Long a = Long.valueOf(0L);
    String b = "";
    String c = "";
    String d = "";
    String e = "";
    
    public a() {}
  }
  
  public class b
  {
    String a = "";
    String b = "";
    Long c = Long.valueOf(0L);
    ArrayList<String> d = new ArrayList();
    
    public b() {}
  }
  
  public class c
  {
    Long a = Long.valueOf(0L);
    String b = "";
    String c = "";
    String d = "";
    String e = "";
    String f = "";
    String g = "";
    Long h = Long.valueOf(0L);
    Long i = Long.valueOf(0L);
    Integer j = Integer.valueOf(0);
    String k = "";
    String l = "";
    String m = "";
    String n = "";
    
    public c() {}
  }
}
