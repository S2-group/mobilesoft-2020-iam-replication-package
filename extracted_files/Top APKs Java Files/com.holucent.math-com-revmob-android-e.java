package com.revmob.android;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.revmob.RevMobAdsListener;
import com.revmob.RevMobTestingMode;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.client.RevMobClient;
import com.revmob.internal.RMLog;
import com.revmob.internal.c;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class e
{
  public static Context a;
  public static RevMobAdsListener b;
  public static boolean c;
  public static boolean d;
  public static boolean e;
  public static boolean f;
  public static Thread g;
  private static DisplayMetrics h = new DisplayMetrics();
  private static String i;
  private static String j;
  private static String k;
  private static com.revmob.client.f l;
  private static int m;
  private static int n;
  private static int o = 0;
  private static JSONArray p;
  private static JSONArray q;
  
  public static String a()
  {
    ((Activity)a).getWindowManager().getDefaultDisplay().getMetrics(h);
    return e().toString();
  }
  
  private static JSONArray a(int paramInt, JSONArray paramJSONArray)
  {
    JSONArray localJSONArray = new JSONArray();
    int i1 = paramInt;
    if (paramJSONArray.length() < paramInt) {
      i1 = paramJSONArray.length();
    }
    paramInt = 0;
    for (;;)
    {
      if (paramInt < i1) {
        try
        {
          localJSONArray.put(paramJSONArray.get(paramInt));
          paramInt += 1;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            localJSONException.printStackTrace();
          }
        }
      }
    }
    return localJSONArray;
  }
  
  private static JSONArray a(JSONArray paramJSONArray, int paramInt)
  {
    JSONArray localJSONArray = new JSONArray();
    int i2 = paramJSONArray.length();
    if (paramJSONArray != null)
    {
      int i1 = 0;
      for (;;)
      {
        if (i1 < i2)
        {
          if (i1 != paramInt) {}
          try
          {
            localJSONArray.put(paramJSONArray.get(i1));
            i1 += 1;
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              localJSONException.printStackTrace();
            }
          }
        }
      }
    }
    return localJSONArray;
  }
  
  private static JSONArray a(JSONArray paramJSONArray, Object paramObject)
  {
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put(paramObject);
    int i1 = 0;
    for (;;)
    {
      if (i1 < paramJSONArray.length()) {}
      try
      {
        localJSONArray.put(paramJSONArray.get(i1));
        i1 += 1;
        continue;
        return localJSONArray;
      }
      catch (JSONException paramObject)
      {
        for (;;) {}
      }
    }
  }
  
  public static void a(int paramInt1, int paramInt2, int paramInt3)
  {
    m = paramInt2;
    n = paramInt3;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(a);
    if (paramInt1 > 0) {}
    try
    {
      p = new JSONArray(localSharedPreferences.getString("sessions", "[]"));
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("date", (int)(System.currentTimeMillis() / 1000L));
      p = a(p, localJSONObject);
      p = a(paramInt1, p);
      if (m <= 0) {}
    }
    catch (JSONException localJSONException2)
    {
      for (;;)
      {
        try
        {
          q = new JSONArray(localSharedPreferences.getString("history", "[]"));
          q = a(m, q);
          return;
        }
        catch (JSONException localJSONException1)
        {
          localJSONException1.printStackTrace();
        }
        localJSONException2 = localJSONException2;
        localJSONException2.printStackTrace();
      }
    }
  }
  
  public static void a(String paramString, Activity paramActivity)
  {
    a = paramActivity;
    if (RevMobClient.c != null)
    {
      RMLog.i("RevMob SDK Version: " + RevMobClient.b + " (" + RevMobClient.c + "-" + RevMobClient.d + ")");
      RMLog.i("App ID: " + paramString);
      RMLog.i("IP Address: " + c.b());
      paramString = new StringBuilder("Simulator: ");
      if ((!Build.MODEL.contains("sdk")) && (!Build.MODEL.contains("Emulator"))) {
        break label481;
      }
    }
    label481:
    for (boolean bool = true;; bool = false)
    {
      RMLog.i(bool);
      RMLog.i("OS Version: " + Build.VERSION.RELEASE);
      RMLog.i("Android API: " + Build.VERSION.SDK_INT);
      RMLog.i("Manufacturer: " + Build.MANUFACTURER);
      RMLog.i("Model: " + Build.MODEL);
      RMLog.i("Android ID: ");
      RMLog.i("Serial number: ");
      RMLog.i("ID for Advertising: " + i);
      RMLog.i("Limit Ad Tracking: " + j);
      RMLog.i("Language: " + Locale.getDefault().getLanguage());
      RMLog.i("Locale: " + j());
      RMLog.i("User Agent: " + c.a());
      RMLog.i("Screen size: " + h.widthPixels + "," + h.heightPixels);
      RMLog.i("Density scale: " + h.density);
      RMLog.i("Density dpi: " + h.densityDpi);
      try
      {
        RMLog.i("User Location: " + g());
        return;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
      RMLog.i("RevMob SDK Version: " + RevMobClient.b);
      break;
    }
  }
  
  public static void a(String paramString, com.revmob.client.f paramF, RevMobAdsListener paramRevMobAdsListener, Activity paramActivity)
  {
    try
    {
      k = paramString;
      l = paramF;
      b = paramRevMobAdsListener;
      a = paramActivity;
      if (g == null)
      {
        paramString = new Thread(new f());
        g = paramString;
        paramString.start();
      }
      return;
    }
    catch (Exception paramString)
    {
      RMLog.e("Error loading advertising info: " + paramString.getMessage());
    }
  }
  
  /* Error */
  public static void a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +484 -> 485
    //   4: aload_0
    //   5: ldc_w 316
    //   8: invokevirtual 320	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifne +474 -> 485
    //   14: aload_1
    //   15: ldc_w 316
    //   18: invokevirtual 320	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   21: ifne +464 -> 485
    //   24: getstatic 41	com/revmob/android/e:o	I
    //   27: iconst_1
    //   28: iadd
    //   29: putstatic 41	com/revmob/android/e:o	I
    //   32: getstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   35: ifnull +104 -> 139
    //   38: iconst_0
    //   39: istore_2
    //   40: aconst_null
    //   41: astore 4
    //   43: aload 4
    //   45: astore 5
    //   47: iload_2
    //   48: getstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   51: invokevirtual 84	org/json/JSONArray:length	()I
    //   54: if_icmpge +40 -> 94
    //   57: getstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   60: iload_2
    //   61: invokevirtual 324	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   64: astore 5
    //   66: aload 5
    //   68: invokevirtual 328	org/json/JSONObject:names	()Lorg/json/JSONArray;
    //   71: iconst_0
    //   72: invokevirtual 331	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   75: aload_0
    //   76: invokevirtual 320	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   79: istore_3
    //   80: iload_3
    //   81: ifeq +249 -> 330
    //   84: getstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   87: iload_2
    //   88: invokestatic 333	com/revmob/android/e:a	(Lorg/json/JSONArray;I)Lorg/json/JSONArray;
    //   91: putstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   94: aload 5
    //   96: ifnonnull +241 -> 337
    //   99: new 68	org/json/JSONObject
    //   102: dup
    //   103: invokespecial 124	org/json/JSONObject:<init>	()V
    //   106: astore 5
    //   108: aload 5
    //   110: aload_0
    //   111: iconst_1
    //   112: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   115: pop
    //   116: getstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   119: aload 5
    //   121: invokestatic 139	com/revmob/android/e:a	(Lorg/json/JSONArray;Ljava/lang/Object;)Lorg/json/JSONArray;
    //   124: putstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   127: getstatic 100	com/revmob/android/e:m	I
    //   130: getstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   133: invokestatic 141	com/revmob/android/e:a	(ILorg/json/JSONArray;)Lorg/json/JSONArray;
    //   136: putstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   139: getstatic 123	com/revmob/android/e:p	Lorg/json/JSONArray;
    //   142: ifnull +103 -> 245
    //   145: getstatic 123	com/revmob/android/e:p	Lorg/json/JSONArray;
    //   148: iconst_0
    //   149: invokevirtual 324	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   152: aload_1
    //   153: invokevirtual 336	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   156: astore 4
    //   158: aload 4
    //   160: ifnonnull +364 -> 524
    //   163: new 68	org/json/JSONObject
    //   166: dup
    //   167: invokespecial 124	org/json/JSONObject:<init>	()V
    //   170: astore 4
    //   172: getstatic 123	com/revmob/android/e:p	Lorg/json/JSONArray;
    //   175: iconst_0
    //   176: invokevirtual 324	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   179: aload_1
    //   180: aload 4
    //   182: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   185: pop
    //   186: aload 4
    //   188: astore_1
    //   189: aload_1
    //   190: aload_0
    //   191: invokevirtual 342	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   194: astore 4
    //   196: aload 4
    //   198: ifnonnull +203 -> 401
    //   201: getstatic 102	com/revmob/android/e:n	I
    //   204: ifeq +183 -> 387
    //   207: aload_1
    //   208: aload_0
    //   209: new 79	org/json/JSONArray
    //   212: dup
    //   213: new 152	java/lang/StringBuilder
    //   216: dup
    //   217: ldc_w 344
    //   220: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   223: getstatic 41	com/revmob/android/e:o	I
    //   226: invokevirtual 217	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   229: ldc_w 346
    //   232: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   238: invokespecial 121	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   241: invokevirtual 339	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   244: pop
    //   245: getstatic 45	com/revmob/android/e:a	Landroid/content/Context;
    //   248: invokestatic 108	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   251: invokeinterface 350 1 0
    //   256: astore_0
    //   257: getstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   260: ifnull +18 -> 278
    //   263: aload_0
    //   264: ldc -113
    //   266: getstatic 145	com/revmob/android/e:q	Lorg/json/JSONArray;
    //   269: invokevirtual 351	org/json/JSONArray:toString	()Ljava/lang/String;
    //   272: invokeinterface 357 3 0
    //   277: pop
    //   278: getstatic 123	com/revmob/android/e:p	Lorg/json/JSONArray;
    //   281: ifnull +18 -> 299
    //   284: aload_0
    //   285: ldc 110
    //   287: getstatic 123	com/revmob/android/e:p	Lorg/json/JSONArray;
    //   290: invokevirtual 351	org/json/JSONArray:toString	()Ljava/lang/String;
    //   293: invokeinterface 357 3 0
    //   298: pop
    //   299: aload_0
    //   300: invokeinterface 361 1 0
    //   305: pop
    //   306: return
    //   307: astore 5
    //   309: aconst_null
    //   310: astore 5
    //   312: goto -246 -> 66
    //   315: astore 6
    //   317: aload 5
    //   319: astore 4
    //   321: aload 6
    //   323: astore 5
    //   325: aload 5
    //   327: invokevirtual 95	org/json/JSONException:printStackTrace	()V
    //   330: iload_2
    //   331: iconst_1
    //   332: iadd
    //   333: istore_2
    //   334: goto -291 -> 43
    //   337: aload 5
    //   339: aload_0
    //   340: aload 5
    //   342: aload_0
    //   343: invokevirtual 365	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   346: iconst_1
    //   347: iadd
    //   348: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   351: pop
    //   352: goto -236 -> 116
    //   355: astore 4
    //   357: goto -241 -> 116
    //   360: astore 4
    //   362: aconst_null
    //   363: astore 4
    //   365: goto -207 -> 158
    //   368: astore_1
    //   369: aload_1
    //   370: invokevirtual 95	org/json/JSONException:printStackTrace	()V
    //   373: aload 4
    //   375: astore_1
    //   376: goto -187 -> 189
    //   379: astore 4
    //   381: aconst_null
    //   382: astore 4
    //   384: goto -188 -> 196
    //   387: aload_1
    //   388: aload_0
    //   389: iconst_1
    //   390: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   393: pop
    //   394: goto -149 -> 245
    //   397: astore_0
    //   398: goto -153 -> 245
    //   401: aload 4
    //   403: instanceof 79
    //   406: ifeq +56 -> 462
    //   409: aload 4
    //   411: checkcast 79	org/json/JSONArray
    //   414: getstatic 41	com/revmob/android/e:o	I
    //   417: invokevirtual 368	org/json/JSONArray:put	(I)Lorg/json/JSONArray;
    //   420: pop
    //   421: aload 4
    //   423: checkcast 79	org/json/JSONArray
    //   426: invokevirtual 84	org/json/JSONArray:length	()I
    //   429: getstatic 102	com/revmob/android/e:n	I
    //   432: if_icmple -187 -> 245
    //   435: aload_1
    //   436: aload_0
    //   437: invokevirtual 371	org/json/JSONObject:remove	(Ljava/lang/String;)Ljava/lang/Object;
    //   440: pop
    //   441: aload_1
    //   442: aload_0
    //   443: aload 4
    //   445: checkcast 79	org/json/JSONArray
    //   448: invokevirtual 84	org/json/JSONArray:length	()I
    //   451: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   454: pop
    //   455: goto -210 -> 245
    //   458: astore_0
    //   459: goto -214 -> 245
    //   462: aload_1
    //   463: aload_0
    //   464: aload 4
    //   466: checkcast 373	java/lang/Integer
    //   469: invokevirtual 376	java/lang/Integer:intValue	()I
    //   472: iconst_1
    //   473: iadd
    //   474: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   477: pop
    //   478: goto -233 -> 245
    //   481: astore_0
    //   482: goto -237 -> 245
    //   485: new 152	java/lang/StringBuilder
    //   488: dup
    //   489: ldc_w 378
    //   492: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   495: aload_1
    //   496: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   499: aload_0
    //   500: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   503: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   506: invokestatic 380	com/revmob/internal/RMLog:d	(Ljava/lang/String;)V
    //   509: return
    //   510: astore 4
    //   512: goto -396 -> 116
    //   515: astore_0
    //   516: goto -271 -> 245
    //   519: astore 5
    //   521: goto -196 -> 325
    //   524: aload 4
    //   526: astore_1
    //   527: goto -338 -> 189
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	530	0	paramString1	String
    //   0	530	1	paramString2	String
    //   39	295	2	i1	int
    //   79	2	3	bool	boolean
    //   41	279	4	localObject1	Object
    //   355	1	4	localJSONException1	JSONException
    //   360	1	4	localJSONException2	JSONException
    //   363	11	4	localObject2	Object
    //   379	1	4	localException	Exception
    //   382	83	4	localObject3	Object
    //   510	15	4	localJSONException3	JSONException
    //   45	75	5	localObject4	Object
    //   307	1	5	localJSONException4	JSONException
    //   310	31	5	localObject5	Object
    //   519	1	5	localJSONException5	JSONException
    //   315	7	6	localJSONException6	JSONException
    // Exception table:
    //   from	to	target	type
    //   57	66	307	org/json/JSONException
    //   84	94	315	org/json/JSONException
    //   337	352	355	org/json/JSONException
    //   145	158	360	org/json/JSONException
    //   172	186	368	org/json/JSONException
    //   189	196	379	java/lang/Exception
    //   387	394	397	org/json/JSONException
    //   441	455	458	org/json/JSONException
    //   462	478	481	org/json/JSONException
    //   108	116	510	org/json/JSONException
    //   207	245	515	org/json/JSONException
    //   66	80	519	org/json/JSONException
  }
  
  private static void a(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.equals(""))) {
      paramJSONObject.put(paramString1, paramString2);
    }
  }
  
  private static String d()
  {
    try
    {
      Object localObject = Class.forName("com.revmob.internal.RevMobSocialInfo").getConstructor(new Class[0]).newInstance(new Object[0]);
      localObject = (String)Class.forName("com.revmob.internal.RevMobSocialInfo").getMethod("getFacebookToken", new Class[] { Context.class }).invoke(localObject, new Object[] { a.getApplicationContext() });
      return localObject;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private static JSONObject e()
  {
    int i1;
    try
    {
      localJSONObject1 = new JSONObject();
      localJSONObject2 = new JSONObject();
      localObject = new JSONObject();
      ((JSONObject)localObject).put("width", h.widthPixels);
      ((JSONObject)localObject).put("height", h.heightPixels);
      ((JSONObject)localObject).put("scale", h.density);
      ((JSONObject)localObject).put("density_dpi", h.densityDpi);
      localJSONObject2.put("screen", localObject);
      a(localJSONObject2, "model", Build.MODEL);
      a(localJSONObject2, "api", Build.VERSION.SDK_INT);
      a(localJSONObject2, "manufacturer", Build.MANUFACTURER);
      a(localJSONObject2, "os_version", Build.VERSION.RELEASE);
      localObject = ((WindowManager)a.getSystemService("window")).getDefaultDisplay();
      if ((Build.VERSION.RELEASE.startsWith("1.")) || (Build.VERSION.RELEASE.startsWith("2.0"))) {
        break label727;
      }
      if (!Build.VERSION.RELEASE.startsWith("2.1")) {
        break label746;
      }
    }
    catch (JSONException localJSONException)
    {
      JSONObject localJSONObject1;
      JSONObject localJSONObject2;
      Object localObject;
      label229:
      return null;
    }
    if (i1 != 0) {
      i1 = ((Display)localObject).getRotation();
    }
    for (;;)
    {
      a(localJSONObject2, "orientation", (String)localObject);
      a(localJSONObject2, "locale", j());
      if (c.c()) {
        localJSONObject2.put("location", g());
      }
      a(localJSONObject2, "android_id", "");
      a(localJSONObject2, "serial", "");
      a(localJSONObject2, "identifier_for_advertising", i);
      a(localJSONObject2, "limit_ad_tracking", j);
      localJSONObject1.put("device", localJSONObject2);
      localObject = new JSONObject();
      ((JSONObject)localObject).put("name", RevMobClient.a);
      ((JSONObject)localObject).put("version", RevMobClient.b);
      ((JSONObject)localObject).put("testing_mode", RevMobClient.a().b().getValue());
      localJSONObject1.put("sdk", localObject);
      localJSONObject1.put("app", i());
      localJSONObject1.put("social", f());
      if (c) {
        localJSONObject1.put("installedApps", k());
      }
      if (d) {
        localJSONObject1.put("runningApps", l());
      }
      if ((e) && (RevMobClient.e != 0L) && (RevMobClient.f != 0L) && (RevMobClient.g != 0L) && (RevMobClient.h != 0L))
      {
        localObject = new JSONObject();
        double d1 = RevMobClient.f - RevMobClient.e;
        double d2 = RevMobClient.g - RevMobClient.f;
        double d3 = RevMobClient.h - RevMobClient.g;
        ((JSONObject)localObject).put("fetchTime", d1 / 1000.0D);
        ((JSONObject)localObject).put("sdkTime", d2 / 1000.0D);
        ((JSONObject)localObject).put("creativeTime", d3 / 1000.0D);
        localJSONObject1.put("time", localObject);
      }
      if (RevMobBanner.isBannerImpression)
      {
        localJSONObject1.put("bannerImpressions", h());
        RevMobBanner.setBannerImpression(false);
      }
      if (f)
      {
        if (q != null) {
          localJSONObject1.put("campaigns", q);
        }
        if (p != null) {
          localJSONObject1.put("sessions", p);
        }
      }
      if (RevMobClient.a().b() != RevMobTestingMode.DISABLED)
      {
        localObject = new JSONObject();
        ((JSONObject)localObject).put("response", RevMobClient.a().b().getValue());
        localJSONObject1.put("testing", localObject);
      }
      c = false;
      d = false;
      e = false;
      f = false;
      return localJSONObject1;
      i1 = ((Display)localObject).getOrientation();
      label727:
      label746:
      do
      {
        localObject = "-1";
        break label229;
        i1 = 0;
        break;
        if (i1 == 0)
        {
          str = "0";
          break label229;
          i1 = 1;
          break;
        }
        if (i1 == 1)
        {
          str = "90";
          break label229;
        }
        if (i1 == 2)
        {
          str = "180";
          break label229;
        }
      } while (i1 != 3);
      String str = "270";
    }
  }
  
  private static JSONObject f()
  {
    JSONObject localJSONObject = new JSONObject();
    h.a(localJSONObject);
    try
    {
      Class.forName("com.facebook.a");
      localJSONObject.put("facebook_token", d());
      return localJSONObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      RMLog.d("Facebook SDK not found.");
    }
    return localJSONObject;
  }
  
  private static JSONObject g()
  {
    JSONObject localJSONObject = new JSONObject();
    Object localObject;
    try
    {
      localObject = (LocationManager)a.getSystemService("location");
      if ((localObject == null) || ((a.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) && (a.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0))) {
        return localJSONObject;
      }
      Location localLocation = ((LocationManager)localObject).getLastKnownLocation("gps");
      localObject = ((LocationManager)localObject).getLastKnownLocation("network");
      if ((localLocation != null) && (localObject != null))
      {
        if (localLocation.getTime() > ((Location)localObject).getTime())
        {
          localJSONObject.put("latitude", localLocation.getLatitude());
          localJSONObject.put("longitude", localLocation.getLongitude());
          localJSONObject.put("accuracy", localLocation.getAccuracy());
          return localJSONObject;
        }
        localJSONObject.put("latitude", ((Location)localObject).getLatitude());
        localJSONObject.put("longitude", ((Location)localObject).getLongitude());
        localJSONObject.put("accuracy", ((Location)localObject).getAccuracy());
        return localJSONObject;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return localJSONObject;
    }
    if (localException != null)
    {
      localJSONObject.put("latitude", localException.getLatitude());
      localJSONObject.put("longitude", localException.getLongitude());
      localJSONObject.put("accuracy", localException.getAccuracy());
      return localJSONObject;
    }
    if (localObject != null)
    {
      localJSONObject.put("latitude", ((Location)localObject).getLatitude());
      localJSONObject.put("longitude", ((Location)localObject).getLongitude());
      localJSONObject.put("accuracy", ((Location)localObject).getAccuracy());
    }
    return localJSONObject;
  }
  
  private static JSONObject h()
  {
    JSONObject localJSONObject1 = new JSONObject();
    localJSONObject1.put("bannerCount", RevMobBanner.bannerCount);
    JSONObject localJSONObject2 = new JSONObject();
    int i1 = 0;
    while (i1 < RevMobBanner.usedCampaigns.size())
    {
      localJSONObject2.put(String.valueOf(i1 + 1), RevMobBanner.usedCampaigns.get(i1));
      i1 += 1;
    }
    localJSONObject1.put("campaigns", localJSONObject2);
    return localJSONObject1;
  }
  
  private static JSONObject i()
  {
    int i1 = 0;
    JSONObject localJSONObject = new JSONObject();
    a(localJSONObject, "bundle_identifier", a.getPackageName());
    try
    {
      Object localObject = a.getResources();
      a(localJSONObject, "app_name", ((Resources)localObject).getText(((Resources)localObject).getIdentifier("app_name", "string", a.getPackageName())).toString());
      try
      {
        localObject = a.getPackageManager().getPackageInfo(a.getPackageName(), 0);
        a(localJSONObject, "app_version", String.valueOf(((PackageInfo)localObject).versionCode));
        a(localJSONObject, "app_version_name", ((PackageInfo)localObject).versionName);
        if (!new g(a).b()) {
          i1 = 1;
        }
        if (i1 != 0) {
          a(localJSONObject, "install_not_registered", "true");
        }
        return localJSONObject;
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  private static String j()
  {
    return Locale.getDefault().toString().replace('_', '-');
  }
  
  private static JSONArray k()
  {
    JSONArray localJSONArray = new JSONArray();
    c.b(true);
    if (c.d())
    {
      PackageManager localPackageManager = a.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if ((localApplicationInfo.flags & 0x1) != 1)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("packageName", localApplicationInfo.packageName);
          localJSONObject.put("name", localApplicationInfo.loadLabel(localPackageManager));
          localJSONArray.put(localJSONObject);
        }
      }
      return localJSONArray;
    }
    return null;
  }
  
  private static JSONArray l()
  {
    List localList = ((ActivityManager)a.getSystemService("activity")).getRunningAppProcesses();
    JSONArray localJSONArray = new JSONArray();
    int i1 = 0;
    while (i1 < localList.size())
    {
      localJSONArray.put(((ActivityManager.RunningAppProcessInfo)localList.get(i1)).processName);
      i1 += 1;
    }
    return localJSONArray;
  }
}
