package com.antivirus.a;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import com.avg.toolkit.f;
import com.avg.toolkit.recurringTasks.AlarmReceiver;
import com.avg.tuneup.i;
import com.avg.tuneup.traffic.s;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class a
  implements f
{
  Context a;
  com.avg.toolkit.uid.a b;
  
  public a(Context paramContext, com.avg.toolkit.uid.a paramA)
  {
    this.a = paramContext;
    this.b = paramA;
  }
  
  private String a(Map paramMap, int paramInt, long paramLong, String paramString1, String paramString2)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    Map.Entry localEntry;
    for (paramMap = "{\"usage\":["; localIterator.hasNext(); paramMap = paramMap + ((com.avg.d.a.b.a)localEntry.getValue()).a(paramInt, paramLong) + ",") {
      localEntry = (Map.Entry)localIterator.next();
    }
    paramMap = paramMap.substring(0, paramMap.length() - 1);
    paramMap = paramMap + "],\"hmid\":\"" + paramString1 + "\",";
    return paramMap + "\"hwid\":\"" + paramString2 + "\"}";
  }
  
  public static void a(Context paramContext)
  {
    Object localObject = new Intent(paramContext, AlarmReceiver.class);
    ((Intent)localObject).putExtra("alarm_code", 21000);
    ((Intent)localObject).putExtra("alarm_code2", 1);
    ((Intent)localObject).setAction("AppUsageReporter");
    localObject = PendingIntent.getBroadcast(paramContext, 1, (Intent)localObject, 134217728);
    paramContext = (AlarmManager)paramContext.getSystemService("alarm");
    paramContext.cancel((PendingIntent)localObject);
    paramContext.setInexactRepeating(1, b(), 86400000L, (PendingIntent)localObject);
  }
  
  /* Error */
  private boolean a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: new 49	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   13: aload_1
    //   14: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: ldc -107
    //   19: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokevirtual 153	java/lang/String:getBytes	()[B
    //   28: invokestatic 159	a/a/a/a/b/a:f	([B)Ljava/lang/String;
    //   31: astore 4
    //   33: new 49	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   40: ldc -95
    //   42: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: aload 4
    //   47: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: astore 4
    //   55: new 163	java/net/URL
    //   58: dup
    //   59: aload 4
    //   61: invokespecial 166	java/net/URL:<init>	(Ljava/lang/String;)V
    //   64: invokevirtual 170	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   67: checkcast 172	java/net/HttpURLConnection
    //   70: astore 4
    //   72: aload 4
    //   74: iconst_0
    //   75: invokevirtual 176	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   78: aload 4
    //   80: iconst_1
    //   81: invokevirtual 179	java/net/HttpURLConnection:setDoInput	(Z)V
    //   84: aload 4
    //   86: iconst_1
    //   87: invokevirtual 182	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   90: aload 4
    //   92: ldc -72
    //   94: invokevirtual 187	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   97: aload 4
    //   99: sipush 10000
    //   102: invokevirtual 191	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   105: aload 4
    //   107: sipush 15000
    //   110: invokevirtual 194	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   113: aload 4
    //   115: ldc -60
    //   117: ldc -58
    //   119: invokevirtual 202	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   122: aload 4
    //   124: invokevirtual 205	java/net/HttpURLConnection:connect	()V
    //   127: new 207	java/io/DataOutputStream
    //   130: dup
    //   131: aload 4
    //   133: invokevirtual 211	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   136: invokespecial 214	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   139: astore 5
    //   141: aload 5
    //   143: aload_1
    //   144: invokevirtual 217	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   147: aload 4
    //   149: invokevirtual 220	java/net/HttpURLConnection:getResponseCode	()I
    //   152: istore_2
    //   153: aload 4
    //   155: invokevirtual 223	java/net/HttpURLConnection:getContentLength	()I
    //   158: pop
    //   159: iload_2
    //   160: sipush 200
    //   163: if_icmpeq +155 -> 318
    //   166: iconst_0
    //   167: istore_3
    //   168: aload 5
    //   170: ifnull +13 -> 183
    //   173: aload 5
    //   175: invokevirtual 226	java/io/DataOutputStream:flush	()V
    //   178: aload 5
    //   180: invokevirtual 229	java/io/DataOutputStream:close	()V
    //   183: iload_3
    //   184: ireturn
    //   185: astore_1
    //   186: aload_1
    //   187: invokevirtual 232	java/io/IOException:printStackTrace	()V
    //   190: iload_3
    //   191: ireturn
    //   192: astore 4
    //   194: aconst_null
    //   195: astore_1
    //   196: aload 5
    //   198: ifnull +8 -> 206
    //   201: aload 5
    //   203: invokevirtual 235	java/net/HttpURLConnection:disconnect	()V
    //   206: aload 4
    //   208: invokestatic 240	com/avg/toolkit/g/a:a	(Ljava/lang/Exception;)V
    //   211: aload_1
    //   212: ifnull +104 -> 316
    //   215: aload_1
    //   216: invokevirtual 226	java/io/DataOutputStream:flush	()V
    //   219: aload_1
    //   220: invokevirtual 229	java/io/DataOutputStream:close	()V
    //   223: iconst_0
    //   224: ireturn
    //   225: astore_1
    //   226: aload_1
    //   227: invokevirtual 232	java/io/IOException:printStackTrace	()V
    //   230: iconst_0
    //   231: ireturn
    //   232: astore_1
    //   233: aload 6
    //   235: astore 4
    //   237: aload 4
    //   239: ifnull +13 -> 252
    //   242: aload 4
    //   244: invokevirtual 226	java/io/DataOutputStream:flush	()V
    //   247: aload 4
    //   249: invokevirtual 229	java/io/DataOutputStream:close	()V
    //   252: aload_1
    //   253: athrow
    //   254: astore 4
    //   256: aload 4
    //   258: invokevirtual 232	java/io/IOException:printStackTrace	()V
    //   261: goto -9 -> 252
    //   264: astore_1
    //   265: aload 5
    //   267: astore 4
    //   269: goto -32 -> 237
    //   272: astore 5
    //   274: aload_1
    //   275: astore 4
    //   277: aload 5
    //   279: astore_1
    //   280: goto -43 -> 237
    //   283: astore 6
    //   285: aconst_null
    //   286: astore_1
    //   287: aload 4
    //   289: astore 5
    //   291: aload 6
    //   293: astore 4
    //   295: goto -99 -> 196
    //   298: astore_1
    //   299: aload 4
    //   301: astore 6
    //   303: aload_1
    //   304: astore 4
    //   306: aload 5
    //   308: astore_1
    //   309: aload 6
    //   311: astore 5
    //   313: goto -117 -> 196
    //   316: iconst_0
    //   317: ireturn
    //   318: iconst_1
    //   319: istore_3
    //   320: goto -152 -> 168
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	323	0	this	a
    //   0	323	1	paramString	String
    //   152	12	2	i	int
    //   167	153	3	bool	boolean
    //   31	123	4	localObject1	Object
    //   192	15	4	localException1	Exception
    //   235	13	4	localObject2	Object
    //   254	3	4	localIOException	java.io.IOException
    //   267	38	4	localObject3	Object
    //   4	262	5	localDataOutputStream	java.io.DataOutputStream
    //   272	6	5	localObject4	Object
    //   289	23	5	localObject5	Object
    //   1	233	6	localObject6	Object
    //   283	9	6	localException2	Exception
    //   301	9	6	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   173	183	185	java/io/IOException
    //   55	72	192	java/lang/Exception
    //   215	223	225	java/io/IOException
    //   55	72	232	finally
    //   72	141	232	finally
    //   242	252	254	java/io/IOException
    //   141	159	264	finally
    //   201	206	272	finally
    //   206	211	272	finally
    //   72	141	283	java/lang/Exception
    //   141	159	298	java/lang/Exception
  }
  
  public static long b()
  {
    long l2 = 0L;
    new Date();
    long l1 = l2;
    try
    {
      Calendar localCalendar = Calendar.getInstance();
      l1 = l2;
      long l3 = System.currentTimeMillis();
      l1 = l2;
      localCalendar.setTime(new Date(l3));
      l1 = l2;
      localCalendar.set(11, 3);
      l1 = l2;
      localCalendar.set(12, 0);
      l1 = l2;
      localCalendar.set(13, 0);
      l1 = l2;
      localCalendar.set(14, 0);
      l1 = l2;
      if (l3 > localCalendar.getTimeInMillis())
      {
        l1 = l2;
        localCalendar.set(5, localCalendar.get(5) + 1);
      }
      l1 = l2;
      l3 = Math.round(new Random(l3).nextDouble() * 9000000.0D);
      l1 = l2;
      l2 = localCalendar.getTimeInMillis() + l3;
      l1 = l2;
      new Date(l2);
      return l2;
    }
    catch (Exception localException) {}
    return l1;
  }
  
  private void c()
  {
    String str2 = a.a.a.a.b.a.e(this.b.b().getBytes());
    String str1 = str2.substring(32);
    str2 = str2.substring(0, 32);
    Map localMap = d();
    if (localMap.isEmpty()) {}
    label155:
    label160:
    for (;;)
    {
      return;
      new com.avg.d.a.c.a().a(this.a, localMap);
      com.avg.d.a.a.a.a(this.a, localMap);
      int i;
      if (s.f())
      {
        i = 1;
        if (!s.f()) {
          break label155;
        }
      }
      for (long l = System.currentTimeMillis() - i.l();; l = SystemClock.elapsedRealtime())
      {
        if (!a(a(localMap, i, l, str2, str1))) {
          break label160;
        }
        this.a.getSharedPreferences("HB", 0).edit().putBoolean("SEND_APP_USAGE", false).commit();
        return;
        i = 2;
        break;
      }
    }
  }
  
  private Map d()
  {
    HashMap localHashMap = new HashMap();
    Object localObject1 = this.a.getPackageManager();
    if (localObject1 != null) {
      try
      {
        localObject1 = ((PackageManager)localObject1).getInstalledApplications(0);
        if (localObject1 != null)
        {
          int i = 0;
          for (;;)
          {
            if (i >= ((List)localObject1).size()) {
              break label238;
            }
            localObject3 = (ApplicationInfo)((List)localObject1).get(i);
            if (!((ApplicationInfo)localObject3).sourceDir.contains("/system"))
            {
              com.avg.d.a.b.a localA = new com.avg.d.a.b.a();
              localA.c = ((ApplicationInfo)localObject3).packageName;
              localA.m = ((ApplicationInfo)localObject3).uid;
              if (!s.f()) {
                break;
              }
              localObject3 = s.a(this.a).a(3, ((ApplicationInfo)localObject3).packageName);
              if (localObject3 != null)
              {
                if (localObject3[0] >= 0L) {
                  break label221;
                }
                l1 = 0L;
                if (localObject3[1] >= 0L) {
                  break label229;
                }
                l2 = 0L;
                localA.o = (l1 + l2);
              }
              localHashMap.put(localA.c, localA);
            }
            i += 1;
          }
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          com.avg.toolkit.g.a.a(localException);
          Object localObject2 = null;
          continue;
          Object localObject3 = s.a(this.a).a(((ApplicationInfo)localObject3).uid);
          continue;
          label221:
          long l1 = localObject3[0];
          continue;
          label229:
          long l2 = localObject3[1];
        }
      }
    }
    label238:
    return localHashMap;
  }
  
  public int a()
  {
    return 21000;
  }
  
  public void a(Bundle paramBundle)
  {
    int j = 0;
    if (this.a.getSharedPreferences("HB", 0).getBoolean("SEND_APP_USAGE", false))
    {
      paramBundle = new c();
      int i = j;
      if (paramBundle.a(this.a))
      {
        i = j;
        if (paramBundle.b(this.a)) {
          i = 1;
        }
      }
      if (i != 0) {
        new Thread(new b(this)).start();
      }
    }
  }
  
  public void a(com.avg.toolkit.license.a paramA) {}
  
  public void a(List paramList) {}
  
  public void a(boolean paramBoolean)
  {
    a(this.a);
  }
  
  public void b(Bundle paramBundle) {}
  
  public void b(com.avg.toolkit.license.a paramA) {}
  
  public void onDestroy() {}
}
