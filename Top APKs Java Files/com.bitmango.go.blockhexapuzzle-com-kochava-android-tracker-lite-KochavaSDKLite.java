package com.kochava.android.tracker.lite;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.unity3d.player.UnityPlayer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KochavaSDKLite
{
  protected static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
  protected static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
  private static final String KOCHAVA_DEVICE_ID_GENERATED = "kochava_app_id_generated";
  protected static final String PREF_INIT_DATA = "shimInitData";
  protected static final String PREF_NAME = "initPrefs";
  private static final String TAG = "KochavaSDKLite";
  protected static String adid;
  private static String advertisingID;
  private static boolean device_limit_tracking;
  protected static boolean gotadid;
  protected static String height;
  protected static boolean initialized;
  static boolean isFinished;
  protected static String kochava_device_strat = "";
  static Object lock = new Object();
  private static String mAppName;
  private static String mAppPackageName;
  private static String mAppVersionCode;
  private static String mAppVersionName;
  protected static Context mContext;
  private static HashMap<String, Boolean> paramRestrictions = new HashMap() {};
  protected static SharedPreferences prefs;
  private static int test;
  protected static String userAgent;
  private static String userAgentResult;
  protected static String width;
  
  static
  {
    height = "";
    width = "";
    device_limit_tracking = false;
    initialized = false;
    test = 2;
    gotadid = false;
    userAgent = "";
    mAppName = "";
    mAppVersionCode = "";
    mAppVersionName = "";
    mAppPackageName = "";
    adid = "";
    userAgentResult = "";
  }
  
  public KochavaSDKLite() {}
  
  protected static String AndroidID()
  {
    Object localObject2 = "";
    try
    {
      localObject1 = Settings.Secure.getString(mContext.getContentResolver(), "android_id");
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "";
      }
      return localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject1 = localObject2;
        if (Global.DEBUGERROR)
        {
          Log.e("KochavaSDKLite", "Error getting Android ID" + localException.toString());
          localObject1 = localObject2;
        }
      }
    }
  }
  
  protected static String Env_Hash()
  {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
  
  protected static String FacebookAttributionId()
    throws IllegalStateException
  {
    Object localObject = mContext.getContentResolver().query(ATTRIBUTION_ID_CONTENT_URI, new String[] { "aid" }, null, null, null);
    if ((localObject == null) || (!((Cursor)localObject).moveToFirst())) {
      localObject = "";
    }
    String str;
    do
    {
      return localObject;
      str = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("aid"));
      localObject = str;
    } while (str != null);
    return "";
  }
  
  public static String GetExternalKochavaDeviceIdentifiers_Android(Context paramContext, boolean paramBoolean)
  {
    if (!initialized) {
      init(paramContext, paramBoolean);
    }
    return new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("{")).append(formatJsonPair("user_agent", userAgent)).append(",").toString())).append(formatJsonPair("kochava_device_id", KochavaDeviceId())).append(",").toString())).append(formatJsonPair("unity_shim_version", "AndroidLite20151110")).toString() + "}";
  }
  
  /* Error */
  public static String GetExternalKochavaInfo_Android(Context paramContext, String paramString1, int paramInt, String paramString2, final boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 8
    //   3: iconst_0
    //   4: istore 10
    //   6: iconst_0
    //   7: istore 9
    //   9: ldc 36
    //   11: new 141	java/lang/StringBuilder
    //   14: dup
    //   15: ldc -23
    //   17: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   20: aload_1
    //   21: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   30: pop
    //   31: iload 8
    //   33: istore 7
    //   35: aload_1
    //   36: ifnull +75 -> 111
    //   39: iload 8
    //   41: istore 7
    //   43: iload 10
    //   45: istore 8
    //   47: aload_1
    //   48: invokevirtual 239	java/lang/String:isEmpty	()Z
    //   51: ifne +60 -> 111
    //   54: iload 10
    //   56: istore 8
    //   58: aload_1
    //   59: aload_1
    //   60: ldc -49
    //   62: invokevirtual 242	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   65: iconst_1
    //   66: iadd
    //   67: aload_1
    //   68: ldc -27
    //   70: invokevirtual 242	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   73: invokevirtual 246	java/lang/String:substring	(II)Ljava/lang/String;
    //   76: ldc -8
    //   78: invokevirtual 252	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   81: invokestatic 258	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   84: astore_1
    //   85: iload 10
    //   87: istore 8
    //   89: aload_1
    //   90: invokeinterface 264 1 0
    //   95: istore 6
    //   97: iconst_0
    //   98: istore 5
    //   100: iload 9
    //   102: istore 7
    //   104: iload 5
    //   106: iload 6
    //   108: if_icmplt +112 -> 220
    //   111: ldc 36
    //   113: new 141	java/lang/StringBuilder
    //   116: dup
    //   117: ldc_w 266
    //   120: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   123: aload_3
    //   124: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   133: pop
    //   134: aload_3
    //   135: ifnull +59 -> 194
    //   138: aload_3
    //   139: invokevirtual 239	java/lang/String:isEmpty	()Z
    //   142: ifne +52 -> 194
    //   145: new 268	org/json/JSONArray
    //   148: dup
    //   149: aload_3
    //   150: invokespecial 269	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   153: astore_1
    //   154: aload_1
    //   155: invokevirtual 272	org/json/JSONArray:length	()I
    //   158: istore 6
    //   160: ldc 36
    //   162: new 141	java/lang/StringBuilder
    //   165: dup
    //   166: ldc_w 274
    //   169: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   172: iload 6
    //   174: invokevirtual 277	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   177: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   183: pop
    //   184: iconst_0
    //   185: istore 5
    //   187: iload 5
    //   189: iload 6
    //   191: if_icmplt +116 -> 307
    //   194: invokestatic 279	com/kochava/android/tracker/lite/KochavaSDKLite:FacebookAttributionId	()Ljava/lang/String;
    //   197: pop
    //   198: aload_0
    //   199: ifnonnull +450 -> 649
    //   202: getstatic 139	com/kochava/android/tracker/lite/Global:DEBUGERROR	Z
    //   205: ifeq +12 -> 217
    //   208: ldc 36
    //   210: ldc_w 281
    //   213: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   216: pop
    //   217: ldc 77
    //   219: areturn
    //   220: iload 7
    //   222: istore 8
    //   224: aload_1
    //   225: iload 5
    //   227: invokeinterface 285 2 0
    //   232: checkcast 172	java/lang/String
    //   235: invokevirtual 288	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   238: ldc_w 290
    //   241: invokevirtual 294	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   244: istore 9
    //   246: iload 9
    //   248: ifeq +6 -> 254
    //   251: iconst_1
    //   252: istore 7
    //   254: iload 5
    //   256: iconst_1
    //   257: iadd
    //   258: istore 5
    //   260: goto -156 -> 104
    //   263: astore_1
    //   264: iload 8
    //   266: istore 7
    //   268: getstatic 139	com/kochava/android/tracker/lite/Global:DEBUGERROR	Z
    //   271: ifeq -160 -> 111
    //   274: ldc 36
    //   276: new 141	java/lang/StringBuilder
    //   279: dup
    //   280: ldc_w 296
    //   283: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   286: aload_1
    //   287: invokevirtual 299	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   290: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   296: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   299: pop
    //   300: iload 8
    //   302: istore 7
    //   304: goto -193 -> 111
    //   307: aload_1
    //   308: iload 5
    //   310: invokevirtual 300	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   313: invokevirtual 288	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   316: ldc -128
    //   318: invokevirtual 294	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   321: ifeq +28 -> 349
    //   324: ldc 36
    //   326: ldc_w 302
    //   329: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   332: pop
    //   333: getstatic 113	com/kochava/android/tracker/lite/KochavaSDKLite:paramRestrictions	Ljava/util/HashMap;
    //   336: ldc -128
    //   338: iconst_0
    //   339: invokestatic 307	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   342: invokevirtual 313	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   345: pop
    //   346: goto +337 -> 683
    //   349: aload_1
    //   350: iload 5
    //   352: invokevirtual 300	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   355: invokevirtual 288	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   358: ldc_w 315
    //   361: invokevirtual 294	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   364: ifeq +65 -> 429
    //   367: ldc 36
    //   369: ldc_w 317
    //   372: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   375: pop
    //   376: getstatic 113	com/kochava/android/tracker/lite/KochavaSDKLite:paramRestrictions	Ljava/util/HashMap;
    //   379: ldc_w 315
    //   382: iconst_0
    //   383: invokestatic 307	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   386: invokevirtual 313	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   389: pop
    //   390: goto +293 -> 683
    //   393: astore_1
    //   394: getstatic 139	com/kochava/android/tracker/lite/Global:DEBUGERROR	Z
    //   397: ifeq -203 -> 194
    //   400: ldc 36
    //   402: new 141	java/lang/StringBuilder
    //   405: dup
    //   406: ldc_w 319
    //   409: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   412: aload_1
    //   413: invokevirtual 299	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   416: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   419: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   422: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   425: pop
    //   426: goto -232 -> 194
    //   429: aload_1
    //   430: iload 5
    //   432: invokevirtual 300	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   435: invokevirtual 288	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   438: ldc_w 320
    //   441: invokevirtual 294	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   444: ifeq +29 -> 473
    //   447: ldc 36
    //   449: ldc_w 322
    //   452: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   455: pop
    //   456: getstatic 113	com/kochava/android/tracker/lite/KochavaSDKLite:paramRestrictions	Ljava/util/HashMap;
    //   459: ldc_w 320
    //   462: iconst_0
    //   463: invokestatic 307	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   466: invokevirtual 313	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   469: pop
    //   470: goto +213 -> 683
    //   473: aload_1
    //   474: iload 5
    //   476: invokevirtual 300	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   479: invokevirtual 288	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   482: ldc_w 324
    //   485: invokevirtual 294	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   488: ifeq +29 -> 517
    //   491: ldc 36
    //   493: ldc_w 326
    //   496: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   499: pop
    //   500: getstatic 113	com/kochava/android/tracker/lite/KochavaSDKLite:paramRestrictions	Ljava/util/HashMap;
    //   503: ldc_w 324
    //   506: iconst_0
    //   507: invokestatic 307	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   510: invokevirtual 313	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   513: pop
    //   514: goto +169 -> 683
    //   517: aload_1
    //   518: iload 5
    //   520: invokevirtual 300	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   523: invokevirtual 288	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   526: ldc_w 328
    //   529: invokevirtual 294	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   532: ifeq +29 -> 561
    //   535: ldc 36
    //   537: ldc_w 330
    //   540: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   543: pop
    //   544: getstatic 113	com/kochava/android/tracker/lite/KochavaSDKLite:paramRestrictions	Ljava/util/HashMap;
    //   547: ldc_w 328
    //   550: iconst_0
    //   551: invokestatic 307	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   554: invokevirtual 313	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   557: pop
    //   558: goto +125 -> 683
    //   561: aload_1
    //   562: iload 5
    //   564: invokevirtual 300	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   567: invokevirtual 288	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   570: ldc_w 332
    //   573: invokevirtual 294	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   576: ifeq +29 -> 605
    //   579: ldc 36
    //   581: ldc_w 334
    //   584: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   587: pop
    //   588: getstatic 113	com/kochava/android/tracker/lite/KochavaSDKLite:paramRestrictions	Ljava/util/HashMap;
    //   591: ldc_w 332
    //   594: iconst_0
    //   595: invokestatic 307	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   598: invokevirtual 313	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   601: pop
    //   602: goto +81 -> 683
    //   605: aload_1
    //   606: iload 5
    //   608: invokevirtual 300	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   611: invokevirtual 288	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   614: ldc_w 336
    //   617: invokevirtual 294	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   620: ifeq +63 -> 683
    //   623: ldc 36
    //   625: ldc_w 338
    //   628: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   631: pop
    //   632: getstatic 113	com/kochava/android/tracker/lite/KochavaSDKLite:paramRestrictions	Ljava/util/HashMap;
    //   635: ldc_w 336
    //   638: iconst_0
    //   639: invokestatic 307	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   642: invokevirtual 313	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   645: pop
    //   646: goto +37 -> 683
    //   649: getstatic 87	com/kochava/android/tracker/lite/KochavaSDKLite:initialized	Z
    //   652: ifne +9 -> 661
    //   655: aload_0
    //   656: iload 4
    //   658: invokestatic 205	com/kochava/android/tracker/lite/KochavaSDKLite:init	(Landroid/content/Context;Z)V
    //   661: new 10	com/kochava/android/tracker/lite/KochavaSDKLite$3
    //   664: dup
    //   665: iload_2
    //   666: iload 4
    //   668: iload 7
    //   670: invokespecial 341	com/kochava/android/tracker/lite/KochavaSDKLite$3:<init>	(IZZ)V
    //   673: invokevirtual 346	java/lang/Thread:start	()V
    //   676: ldc 77
    //   678: areturn
    //   679: astore_1
    //   680: goto -482 -> 198
    //   683: iload 5
    //   685: iconst_1
    //   686: iadd
    //   687: istore 5
    //   689: goto -502 -> 187
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	692	0	paramContext	Context
    //   0	692	1	paramString1	String
    //   0	692	2	paramInt	int
    //   0	692	3	paramString2	String
    //   0	692	4	paramBoolean	boolean
    //   98	590	5	i	int
    //   95	97	6	j	int
    //   33	636	7	bool1	boolean
    //   1	300	8	bool2	boolean
    //   7	240	9	bool3	boolean
    //   4	82	10	bool4	boolean
    // Exception table:
    //   from	to	target	type
    //   47	54	263	java/lang/Exception
    //   58	85	263	java/lang/Exception
    //   89	97	263	java/lang/Exception
    //   224	246	263	java/lang/Exception
    //   138	184	393	java/lang/Exception
    //   307	346	393	java/lang/Exception
    //   349	390	393	java/lang/Exception
    //   429	470	393	java/lang/Exception
    //   473	514	393	java/lang/Exception
    //   517	558	393	java/lang/Exception
    //   561	602	393	java/lang/Exception
    //   605	646	393	java/lang/Exception
    //   194	198	679	java/lang/Exception
  }
  
  public static String GetExternalLocationReport_Android(int paramInt1, final int paramInt2, final int paramInt3)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        LocationDirector.getInstance(KochavaSDKLite.mContext).getLocation(this.val$locationAccuracy, paramInt2, paramInt3);
      }
    }).start();
    return "";
  }
  
  protected static String KochavaDeviceId()
  {
    return "";
  }
  
  protected static String PackageName()
  {
    Object localObject2 = "";
    try
    {
      localObject1 = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).packageName;
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "";
      }
      return localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject1 = localObject2;
        if (Global.DEBUGERROR)
        {
          Log.e("KochavaSDKLite", "Error getting Package Name" + localException.toString());
          localObject1 = localObject2;
        }
      }
    }
  }
  
  public static void enableDebug(boolean paramBoolean)
  {
    Global.DEBUGERROR = paramBoolean;
  }
  
  private static String formatJsonPair(String paramString1, String paramString2)
  {
    return "\"" + paramString1 + "\":\"" + paramString2 + "\"";
  }
  
  private static String getAdvertisingId()
  {
    for (;;)
    {
      int i;
      try
      {
        i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
        if (i != 0) {}
        switch (i)
        {
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
          if (Global.DEBUGERROR) {
            Log.i("KochavaSDKLite", "Google Play Services check returned unknown error code (" + i + ").");
          }
          if (Global.DEBUGERROR) {
            Log.e("KochavaSDKLite", "Problem getting Advertising ID " + GooglePlayServicesUtil.getErrorString(i));
          }
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(mContext);
          String str = localInfo.getId();
          device_limit_tracking = localInfo.isLimitAdTrackingEnabled();
          return str;
        }
      }
      catch (Exception localException)
      {
        if (!Global.DEBUGERROR) {
          continue;
        }
        Log.e("KochavaSDKLite", "Problem getting Advertising ID (catch): " + localException.toString());
        return "";
      }
      if (Global.DEBUGERROR)
      {
        Log.i("KochavaSDKLite", "Google Play Services check returned ConnectionResult.SUCCESS (" + i + ").");
        continue;
        if (Global.DEBUGERROR)
        {
          Log.i("KochavaSDKLite", "Google Play Services check returned ConnectionResult.SERVICE_MISSING (" + i + ").");
          continue;
          if (Global.DEBUGERROR)
          {
            Log.i("KochavaSDKLite", "Google Play Services check returned ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED (" + i + ").");
            continue;
            if (Global.DEBUGERROR)
            {
              Log.i("KochavaSDKLite", "Google Play Services check returned ConnectionResult.SERVICE_DISABLED (" + i + ").");
              continue;
              if (Global.DEBUGERROR) {
                Log.i("KochavaSDKLite", "Google Play Services check returned ConnectionResult.SERVICE_INVALID (" + i + ").");
              }
            }
          }
        }
      }
    }
  }
  
  private static JSONArray getAffinityGroup()
  {
    localObject3 = new ArrayList();
    for (;;)
    {
      try
      {
        localObject4 = mContext.getPackageManager();
        localIterator = ((PackageManager)localObject4).getInstalledApplications(128).iterator();
        bool = localIterator.hasNext();
        if (bool) {
          continue;
        }
      }
      catch (Exception localException)
      {
        Iterator localIterator;
        boolean bool;
        Object localObject1;
        Object localObject2;
        ApplicationInfo localApplicationInfo;
        if (!Global.DEBUGERROR) {
          continue;
        }
        Log.e("KochavaSDKLite", "Error gathering affinity group: " + localException.toString());
        continue;
        localObject3 = (ApplicationInfoHolder)localNameNotFoundException.next();
        Object localObject4 = new JSONObject();
        try
        {
          ((JSONObject)localObject4).put("app_name", ((ApplicationInfoHolder)localObject3).appPackage);
          ((JSONObject)localObject4).put("mtime", ((ApplicationInfoHolder)localObject3).appInstallTime);
          ((JSONObject)localObject4).put("utime", ((ApplicationInfoHolder)localObject3).appUpdateTime);
          localException.put(localObject4);
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
        continue;
      }
      localObject1 = new JSONArray();
      localObject2 = ((List)localObject3).iterator();
      if (((Iterator)localObject2).hasNext()) {
        continue;
      }
      Log.i("KochavaSDKLite", "affinity group: " + ((JSONArray)localObject1).toString());
      return localObject1;
      localApplicationInfo = (ApplicationInfo)localIterator.next();
      bool = isSystemPackage(localApplicationInfo);
      if (!bool) {
        localObject1 = null;
      }
      try
      {
        localObject2 = ((PackageManager)localObject4).getPackageInfo(localApplicationInfo.packageName, 0);
        localObject1 = localObject2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Log.i("KochavaSDKLite", "error gathering package info for " + localApplicationInfo.packageName + ". " + localNameNotFoundException.toString());
        continue;
      }
      if (localObject1 != null) {
        ((List)localObject3).add(new ApplicationInfoHolder(((PackageInfo)localObject1).packageName, ((PackageInfo)localObject1).firstInstallTime, ((PackageInfo)localObject1).lastUpdateTime));
      }
    }
  }
  
  private static String getAppShortString()
  {
    localObject1 = "";
    localObject2 = localObject1;
    try
    {
      mAppVersionName = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
      localObject2 = localObject1;
      String str = mAppVersionName;
      localObject1 = str;
      localObject2 = str;
      if (Global.DEBUGERROR)
      {
        localObject2 = str;
        Log.i("KochavaSDKLite", "mAppVersionName now: " + mAppVersionName);
        localObject1 = str;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localObject1 = localObject2;
        if (Global.DEBUGERROR)
        {
          Log.e("KochavaSDKLite", "Error gathering app version name " + localException.toString());
          localObject1 = localObject2;
        }
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = "";
    }
    return localObject2;
  }
  
  /* Error */
  private static String getAppVersion()
  {
    // Byte code:
    //   0: ldc_w 567
    //   3: putstatic 95	com/kochava/android/tracker/lite/KochavaSDKLite:mAppName	Ljava/lang/String;
    //   6: ldc_w 569
    //   9: putstatic 97	com/kochava/android/tracker/lite/KochavaSDKLite:mAppVersionCode	Ljava/lang/String;
    //   12: ldc 77
    //   14: putstatic 99	com/kochava/android/tracker/lite/KochavaSDKLite:mAppVersionName	Ljava/lang/String;
    //   17: getstatic 120	com/kochava/android/tracker/lite/KochavaSDKLite:mContext	Landroid/content/Context;
    //   20: invokevirtual 573	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   23: invokevirtual 359	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   26: astore_2
    //   27: aload_2
    //   28: getstatic 120	com/kochava/android/tracker/lite/KochavaSDKLite:mContext	Landroid/content/Context;
    //   31: invokevirtual 362	android/content/Context:getPackageName	()Ljava/lang/String;
    //   34: iconst_0
    //   35: invokevirtual 577	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   38: astore_0
    //   39: aload_0
    //   40: ifnull +234 -> 274
    //   43: aload_2
    //   44: aload_0
    //   45: invokevirtual 581	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   48: astore_0
    //   49: aload_0
    //   50: checkcast 172	java/lang/String
    //   53: putstatic 95	com/kochava/android/tracker/lite/KochavaSDKLite:mAppName	Ljava/lang/String;
    //   56: getstatic 139	com/kochava/android/tracker/lite/Global:DEBUGERROR	Z
    //   59: ifeq +28 -> 87
    //   62: ldc 36
    //   64: new 141	java/lang/StringBuilder
    //   67: dup
    //   68: ldc_w 583
    //   71: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   74: getstatic 95	com/kochava/android/tracker/lite/KochavaSDKLite:mAppName	Ljava/lang/String;
    //   77: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   86: pop
    //   87: new 141	java/lang/StringBuilder
    //   90: dup
    //   91: getstatic 120	com/kochava/android/tracker/lite/KochavaSDKLite:mContext	Landroid/content/Context;
    //   94: invokevirtual 359	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   97: getstatic 120	com/kochava/android/tracker/lite/KochavaSDKLite:mContext	Landroid/content/Context;
    //   100: invokevirtual 362	android/content/Context:getPackageName	()Ljava/lang/String;
    //   103: iconst_0
    //   104: invokevirtual 368	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   107: getfield 586	android/content/pm/PackageInfo:versionCode	I
    //   110: invokestatic 588	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   113: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   116: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: putstatic 97	com/kochava/android/tracker/lite/KochavaSDKLite:mAppVersionCode	Ljava/lang/String;
    //   122: getstatic 139	com/kochava/android/tracker/lite/Global:DEBUGERROR	Z
    //   125: ifeq +28 -> 153
    //   128: ldc 36
    //   130: new 141	java/lang/StringBuilder
    //   133: dup
    //   134: ldc_w 590
    //   137: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   140: getstatic 97	com/kochava/android/tracker/lite/KochavaSDKLite:mAppVersionCode	Ljava/lang/String;
    //   143: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokestatic 236	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   152: pop
    //   153: new 141	java/lang/StringBuilder
    //   156: dup
    //   157: getstatic 95	com/kochava/android/tracker/lite/KochavaSDKLite:mAppName	Ljava/lang/String;
    //   160: invokestatic 211	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   163: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   166: ldc_w 592
    //   169: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: getstatic 97	com/kochava/android/tracker/lite/KochavaSDKLite:mAppVersionCode	Ljava/lang/String;
    //   175: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: areturn
    //   182: astore_3
    //   183: aconst_null
    //   184: astore_1
    //   185: aload_1
    //   186: astore_0
    //   187: getstatic 139	com/kochava/android/tracker/lite/Global:DEBUGERROR	Z
    //   190: ifeq -151 -> 39
    //   193: ldc 36
    //   195: new 141	java/lang/StringBuilder
    //   198: dup
    //   199: ldc_w 594
    //   202: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   205: aload_3
    //   206: invokevirtual 531	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   209: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: aload_3
    //   213: invokevirtual 598	android/content/pm/PackageManager$NameNotFoundException:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   216: invokevirtual 601	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   219: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   222: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   225: pop
    //   226: aload_1
    //   227: astore_0
    //   228: goto -189 -> 39
    //   231: astore_0
    //   232: getstatic 139	com/kochava/android/tracker/lite/Global:DEBUGERROR	Z
    //   235: ifeq -148 -> 87
    //   238: ldc 36
    //   240: new 141	java/lang/StringBuilder
    //   243: dup
    //   244: ldc_w 594
    //   247: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   250: aload_0
    //   251: invokevirtual 149	java/lang/Exception:toString	()Ljava/lang/String;
    //   254: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: aload_0
    //   258: invokevirtual 602	java/lang/Exception:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   261: invokevirtual 601	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   264: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   267: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   270: pop
    //   271: goto -184 -> 87
    //   274: ldc_w 604
    //   277: astore_0
    //   278: goto -229 -> 49
    //   281: astore_0
    //   282: getstatic 139	com/kochava/android/tracker/lite/Global:DEBUGERROR	Z
    //   285: ifeq -132 -> 153
    //   288: ldc 36
    //   290: new 141	java/lang/StringBuilder
    //   293: dup
    //   294: ldc_w 606
    //   297: invokespecial 146	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   300: aload_0
    //   301: invokevirtual 149	java/lang/Exception:toString	()Ljava/lang/String;
    //   304: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: aload_0
    //   308: invokevirtual 602	java/lang/Exception:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   311: invokevirtual 601	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   314: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   317: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   320: pop
    //   321: goto -168 -> 153
    // Local variable table:
    //   start	length	slot	name	signature
    //   38	190	0	localObject1	Object
    //   231	27	0	localException1	Exception
    //   277	1	0	str	String
    //   281	27	0	localException2	Exception
    //   184	43	1	localObject2	Object
    //   26	18	2	localPackageManager	PackageManager
    //   182	31	3	localNameNotFoundException	PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   27	39	182	android/content/pm/PackageManager$NameNotFoundException
    //   17	27	231	java/lang/Exception
    //   27	39	231	java/lang/Exception
    //   43	49	231	java/lang/Exception
    //   49	87	231	java/lang/Exception
    //   187	226	231	java/lang/Exception
    //   87	153	281	java/lang/Exception
  }
  
  private static String getBSSID()
  {
    Object localObject = null;
    if (0 == 0) {}
    try
    {
      localObject = (WifiManager)mContext.getSystemService("wifi");
      localObject = ((WifiManager)localObject).getConnectionInfo().getBSSID();
      return localObject;
    }
    catch (Exception localException)
    {
      Log.i("KochavaSDKLite", "BSSID: ERROR" + localException.toString());
    }
    return "";
  }
  
  private static String getCarrier()
  {
    Object localObject = "";
    try
    {
      String str = ((TelephonyManager)mContext.getSystemService("phone")).getNetworkOperatorName();
      localObject = str;
    }
    catch (Exception localException)
    {
      while (!Global.DEBUGERROR) {}
      Log.e("KochavaSDKLite", "Couldn't gather Carrier: " + localException.toString());
    }
    return localObject;
    return "";
  }
  
  private static String getEmailAccounts()
  {
    Object localObject1 = "";
    Account[] arrayOfAccount;
    int i;
    if (mContext.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0)
    {
      arrayOfAccount = AccountManager.get(mContext).getAccounts();
      int j = arrayOfAccount.length;
      i = 0;
      if (i >= j)
      {
        if (((String)localObject1).length() <= 0) {
          break label141;
        }
        localObject1 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
      }
    }
    for (;;)
    {
      return "[" + (String)localObject1 + "]";
      Account localAccount = arrayOfAccount[i];
      Object localObject2 = localObject1;
      if (isEmailValid(localAccount.name))
      {
        localObject2 = localAccount.name.toLowerCase();
        localObject2 = localObject1 + (String)localObject2 + ",";
      }
      i += 1;
      localObject1 = localObject2;
      break;
      label141:
      localObject1 = "";
      continue;
      localObject2 = "";
      localObject1 = localObject2;
      if (Global.DEBUGERROR)
      {
        Log.e("KochavaSDKLite", "****NOTICE**** Gathering of emails was whitelisted, but android.permission.GET_ACCOUNTS declaration was missing from manifest.");
        localObject1 = localObject2;
      }
    }
  }
  
  private static String getLanguage()
  {
    Object localObject2 = "";
    try
    {
      localObject1 = Locale.getDefault().getLanguage();
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "";
      }
      return localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject1 = localObject2;
        if (Global.DEBUGERROR)
        {
          Log.e("KochavaSDKLite", "Error gathering language code: " + localException.toString());
          localObject1 = localObject2;
        }
      }
    }
  }
  
  public static String getUserAgentLog()
  {
    return userAgentResult;
  }
  
  private static String getVolume()
  {
    try
    {
      String str = ((AudioManager)mContext.getSystemService("audio")).getStreamVolume(3);
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private static String hideString(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = new char[paramString.length()];
    Arrays.fill(paramString, '*');
    return new String(paramString);
  }
  
  protected static void init(Context paramContext, boolean paramBoolean)
  {
    mContext = paramContext.getApplicationContext();
    prefs = mContext.getSharedPreferences("initPrefs", 0);
    setUserAgent(paramContext.getApplicationContext());
    if (!paramBoolean) {
      new Thread()
      {
        public void run()
        {
          Object localObject2 = null;
          try
          {
            String str = KochavaSDKLite.access$0();
            KochavaSDKLite.advertisingID = str;
            return;
          }
          catch (Error localError)
          {
            for (;;)
            {
              Object localObject1 = localObject2;
              if (Global.DEBUGERROR)
              {
                Log.e("KochavaSDKLite", "*****NOTICE***** \nAn error has occured when trying to gather the Google Advertising Id. Please make sure you have the Google Play Services Library integrated in your app! \n*****NOTICE*****");
                localObject1 = localObject2;
              }
            }
          }
        }
      }.start();
    }
    initialized = true;
  }
  
  private static boolean isEmailValid(String paramString)
  {
    boolean bool = false;
    if (Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(paramString).matches()) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean isSystemPackage(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  private static String parseInput(HashMap<String, String> paramHashMap)
  {
    String str2 = (String)paramHashMap.get("partner_id");
    String str3 = (String)paramHashMap.get("partner_name");
    String str4 = (String)paramHashMap.get("kochava_app_id");
    String str1 = (String)paramHashMap.get("currency");
    if (((str2 == null) || (str2.trim().length() == 0)) && ((str3 == null) || (str3.trim().length() == 0)))
    {
      if ((str4 == null) || (str4.trim().length() == 0))
      {
        if (Global.DEBUGERROR) {
          Log.e("KochavaSDKLite", "Kochava requires that you pass either a kochava app id, or a partner id and a partner name into the datamap during initialization.");
        }
        return null;
      }
      paramHashMap = "" + formatJsonPair("kochava_app_id", str4) + ",";
      label138:
      if ((str1 == null) || (str1.length() == 0)) {
        break label395;
      }
    }
    label395:
    for (paramHashMap = paramHashMap + "\"currency\":\"" + str1 + "\"";; paramHashMap = paramHashMap + "\"currency\":\"USD\"")
    {
      return paramHashMap;
      if ((str2 == null) || (str2.trim().length() == 0) || (str3 == null) || (str3.trim().length() == 0))
      {
        if (!Global.DEBUGERROR) {
          break;
        }
        Log.e("KochavaSDKLite", "Please make sure that you have passed both a partner id and a partner name.");
        return null;
      }
      paramHashMap = new StringBuilder(String.valueOf("")).append(formatJsonPair("partner_id", str2)).append(",").toString() + formatJsonPair("partner_name", str3) + ",";
      if ((str4 != null) && (str4.trim().length() != 0))
      {
        paramHashMap = paramHashMap + formatJsonPair("kochava_app_id", str4) + ",";
        break label138;
      }
      paramHashMap = paramHashMap + formatJsonPair("kochava_app_id", new StringBuilder("_p:").append(str3).append(":").append(str2).toString()) + ",";
      break label138;
    }
  }
  
  @SuppressLint({"NewApi"})
  protected static void setUserAgent(Context paramContext)
  {
    paramContext = "";
    int i = 1;
    int j = 1;
    try
    {
      localObject1 = "" + "\nTrying user agent method 1";
      paramContext = (Context)localObject1;
      userAgent = System.getProperty("http.agent");
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        Object localObject1;
        localObject2 = new StringWriter();
        localException1.printStackTrace(new PrintWriter((Writer)localObject2));
        if (Global.DEBUGERROR) {
          Log.e("KochavaSDKLite", ((StringWriter)localObject2).toString());
        }
        str1 = paramContext + "\nError with user agent first method: " + localException1.toString() + "\n" + ((StringWriter)localObject2).toString();
      }
    }
    if (userAgent.trim().isEmpty()) {
      i = 0;
    }
    paramContext = (Context)localObject1;
    if (i == 0) {
      paramContext = (Context)localObject1;
    }
    try
    {
      localObject1 = localObject1 + "\nTrying user agent method 2";
      paramContext = (Context)localObject1;
      userAgent = new WebView(mContext).getSettings().getUserAgentString();
      paramContext = (Context)localObject1;
      localObject1 = localObject1 + "\nMethod 2 successful";
      paramContext = (Context)localObject1;
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        Object localObject5;
        localObject2 = new StringWriter();
        localException2.printStackTrace(new PrintWriter((Writer)localObject2));
        if (Global.DEBUGERROR) {
          Log.e("KochavaSDKLite", ((StringWriter)localObject2).toString());
        }
        paramContext = paramContext + "\nError with user agent second method: " + localException2.toString() + "\n" + ((StringWriter)localObject2).toString() + "\n userAgent = error.";
      }
    }
    i = j;
    if (userAgent.trim().isEmpty()) {
      i = 0;
    }
    localObject2 = paramContext;
    if (i == 0)
    {
      localObject5 = null;
      localObject4 = null;
      localObject1 = localObject4;
      localObject3 = paramContext;
      localObject2 = localObject5;
    }
    try
    {
      String str2 = paramContext + "\nTrying user agent method 3";
      localObject1 = localObject4;
      localObject3 = str2;
      localObject2 = localObject5;
      paramContext = WebSettings.class.getDeclaredConstructor(new Class[] { Context.class, WebView.class });
      localObject1 = paramContext;
      localObject3 = str2;
      localObject2 = paramContext;
      paramContext.setAccessible(true);
      localObject1 = paramContext;
      localObject3 = str2;
      localObject2 = paramContext;
      userAgent = ((WebSettings)paramContext.newInstance(new Object[] { mContext, null })).getUserAgentString();
      localObject1 = paramContext;
      localObject3 = str2;
      localObject2 = paramContext;
      localObject4 = str2 + "\nMethod 3 successful.";
      localObject1 = localObject4;
      localObject2 = localObject1;
      if (paramContext != null)
      {
        paramContext.setAccessible(false);
        localObject2 = localObject1;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        String str1;
        localObject2 = localException2;
        localObject4 = new StringWriter();
        localObject2 = localException2;
        paramContext.printStackTrace(new PrintWriter((Writer)localObject4));
        localObject2 = localException2;
        if (Global.DEBUGERROR)
        {
          localObject2 = localException2;
          Log.e("KochavaSDKLite", ((StringWriter)localObject4).toString());
        }
        localObject2 = localException2;
        paramContext = localObject3 + "\nError with user agent third method: " + paramContext.toString() + "\n" + ((StringWriter)localObject4).toString() + "\n userAgent = error.";
        localObject2 = paramContext;
        if (localException2 != null)
        {
          localException2.setAccessible(false);
          localObject2 = paramContext;
        }
      }
    }
    finally
    {
      if (localObject2 == null) {
        break label664;
      }
      ((Constructor)localObject2).setAccessible(false);
    }
    if (Global.DEBUGERROR) {
      Log.i("KochavaSDKLite", "user agent result: " + (String)localObject2);
    }
  }
  
  public final class INPUTITEMS
  {
    public static final String CURRENCY = "currency";
    public static final String KOCHAVA_APP_ID = "kochava_app_id";
    public static final String PARTNER_ID = "partner_id";
    public static final String PARTNER_NAME = "partner_name";
    
    private INPUTITEMS() {}
  }
  
  public final class PARAMS
  {
    public static final String ADID = "adid";
    public static final String AFFINITY_GROUP = "affinity_group";
    public static final String ANDROID_ID = "android_id";
    public static final String BSSID = "bssid";
    public static final String CARRIER_NAME = "carrier_name";
    public static final String FB_ATTRIBUTION_ID = "fb_attribution_id";
    public static final String OPEN_UDID = "open_udid";
    public static final String VOLUME = "volume";
    
    private PARAMS() {}
  }
}
