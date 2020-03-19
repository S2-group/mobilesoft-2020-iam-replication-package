package com.c.f;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import com.c.d.c;
import com.c.d.d;
import com.c.h.j.b;
import com.c.h.j.c;
import com.c.h.j.d;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONObject;

public class b
{
  static final char[] a = "0123456789ABCDEF".toCharArray();
  
  /* Error */
  public static JSONObject A(Activity paramActivity)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 39	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: aload_3
    //   6: ldc 41
    //   8: aload_0
    //   9: invokevirtual 45	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   12: invokevirtual 51	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   15: ifeq +24 -> 39
    //   18: aload_3
    //   19: ldc 53
    //   21: aload_0
    //   22: invokevirtual 45	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   25: invokevirtual 51	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   28: ifne +6 -> 34
    //   31: goto +8 -> 39
    //   34: aconst_null
    //   35: astore_3
    //   36: goto +88 -> 124
    //   39: aload_0
    //   40: ldc 55
    //   42: invokevirtual 59	android/app/Activity:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   45: checkcast 61	android/location/LocationManager
    //   48: astore 4
    //   50: aload 4
    //   52: invokevirtual 65	android/location/LocationManager:getAllProviders	()Ljava/util/List;
    //   55: invokeinterface 71 1 0
    //   60: astore 5
    //   62: aconst_null
    //   63: astore_0
    //   64: aload_0
    //   65: astore_3
    //   66: aload 5
    //   68: invokeinterface 77 1 0
    //   73: ifeq +51 -> 124
    //   76: aload 4
    //   78: aload 5
    //   80: invokeinterface 81 1 0
    //   85: checkcast 20	java/lang/String
    //   88: invokevirtual 85	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   91: astore_3
    //   92: aload_3
    //   93: ifnonnull +6 -> 99
    //   96: goto -32 -> 64
    //   99: aload_0
    //   100: ifnull +19 -> 119
    //   103: aload_3
    //   104: invokevirtual 91	android/location/Location:getAccuracy	()F
    //   107: fstore_1
    //   108: aload_0
    //   109: invokevirtual 91	android/location/Location:getAccuracy	()F
    //   112: fstore_2
    //   113: fload_1
    //   114: fload_2
    //   115: fcmpg
    //   116: ifge -52 -> 64
    //   119: aload_3
    //   120: astore_0
    //   121: goto -57 -> 64
    //   124: aload_3
    //   125: ifnonnull +5 -> 130
    //   128: aconst_null
    //   129: areturn
    //   130: new 93	org/json/JSONObject
    //   133: dup
    //   134: invokespecial 96	org/json/JSONObject:<init>	()V
    //   137: astore_0
    //   138: aload_0
    //   139: ldc 98
    //   141: aload_3
    //   142: invokevirtual 102	android/location/Location:getLatitude	()D
    //   145: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   148: pop
    //   149: aload_0
    //   150: ldc 108
    //   152: aload_3
    //   153: invokevirtual 111	android/location/Location:getLongitude	()D
    //   156: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   159: pop
    //   160: aload_0
    //   161: ldc 113
    //   163: aload_3
    //   164: invokevirtual 91	android/location/Location:getAccuracy	()F
    //   167: f2d
    //   168: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   171: pop
    //   172: aload_0
    //   173: ldc 115
    //   175: aload_3
    //   176: invokevirtual 118	android/location/Location:getAltitude	()D
    //   179: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   182: pop
    //   183: aload_0
    //   184: ldc 120
    //   186: aload_3
    //   187: invokevirtual 123	android/location/Location:getBearing	()F
    //   190: f2d
    //   191: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   194: pop
    //   195: aload_0
    //   196: ldc 125
    //   198: aload_3
    //   199: invokevirtual 129	android/location/Location:getElapsedRealtimeNanos	()J
    //   202: invokevirtual 132	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   205: pop
    //   206: aload_3
    //   207: invokevirtual 135	android/location/Location:getProvider	()Ljava/lang/String;
    //   210: ifnull +14 -> 224
    //   213: aload_0
    //   214: ldc -119
    //   216: aload_3
    //   217: invokevirtual 135	android/location/Location:getProvider	()Ljava/lang/String;
    //   220: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   223: pop
    //   224: aload_0
    //   225: ldc -114
    //   227: aload_3
    //   228: invokevirtual 145	android/location/Location:getSpeed	()F
    //   231: f2d
    //   232: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   235: pop
    //   236: aload_0
    //   237: ldc -109
    //   239: aload_3
    //   240: invokevirtual 150	android/location/Location:getTime	()J
    //   243: invokevirtual 132	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   246: pop
    //   247: goto +5 -> 252
    //   250: aconst_null
    //   251: astore_0
    //   252: aload_0
    //   253: ifnull +5 -> 258
    //   256: aload_0
    //   257: areturn
    //   258: aconst_null
    //   259: areturn
    //   260: astore_0
    //   261: goto -227 -> 34
    //   264: astore_3
    //   265: aload_0
    //   266: astore_3
    //   267: goto -143 -> 124
    //   270: astore_0
    //   271: goto -21 -> 250
    //   274: astore_3
    //   275: goto -23 -> 252
    //   278: astore 4
    //   280: goto -74 -> 206
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	283	0	paramActivity	Activity
    //   107	7	1	f1	float
    //   112	3	2	f2	float
    //   4	236	3	localObject	Object
    //   264	1	3	localException1	Exception
    //   266	1	3	localActivity	Activity
    //   274	1	3	localException2	Exception
    //   48	29	4	localLocationManager	android.location.LocationManager
    //   278	1	4	localNoSuchMethodError	NoSuchMethodError
    //   60	19	5	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   50	62	260	java/lang/Exception
    //   66	92	264	java/lang/Exception
    //   103	113	264	java/lang/Exception
    //   130	138	270	java/lang/Exception
    //   138	195	274	java/lang/Exception
    //   206	224	274	java/lang/Exception
    //   224	247	274	java/lang/Exception
    //   195	206	278	java/lang/NoSuchMethodError
    //   195	206	278	java/lang/Exception
  }
  
  public static Point B(Activity paramActivity)
  {
    try
    {
      paramActivity = ((WindowManager)paramActivity.getSystemService("window")).getDefaultDisplay();
      localPoint = new Point();
    }
    catch (Exception paramActivity)
    {
      Point localPoint;
      label30:
      label48:
      for (;;) {}
    }
    try
    {
      paramActivity.getSize(localPoint);
      return localPoint;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      break label48;
    }
    catch (Exception localException)
    {
      break label30;
    }
    for (localPoint.x = paramActivity.getWidth();; localPoint.x = paramActivity.getWidth())
    {
      localPoint.y = paramActivity.getHeight();
      return localPoint;
    }
    return null;
  }
  
  public static String C(Activity paramActivity)
  {
    for (;;)
    {
      try
      {
        localDisplayMetrics = new DisplayMetrics();
        paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
        paramActivity.getMetrics(localDisplayMetrics);
        localPoint = new Point();
        k = localDisplayMetrics.widthPixels;
        i = localDisplayMetrics.heightPixels;
      }
      catch (Exception paramActivity)
      {
        DisplayMetrics localDisplayMetrics;
        Point localPoint;
        int k;
        int i;
        int j;
        double d2;
        double d1;
        continue;
      }
      try
      {
        paramActivity.getSize(localPoint);
        j = localPoint.x;
      }
      catch (NoSuchMethodError|Exception paramActivity)
      {
        continue;
      }
      try
      {
        k = localPoint.y;
        i = k;
      }
      catch (NoSuchMethodError|Exception paramActivity) {}
    }
    j = k;
    k = localDisplayMetrics.densityDpi;
    d2 = j;
    d1 = k;
    d2 /= d1;
    d1 = i / d1;
    d1 = Math.sqrt(Math.pow(d2, 2.0D) + Math.pow(d1, 2.0D));
    return String.valueOf(d1);
    return "unknown";
  }
  
  public static float a(float paramFloat, Context paramContext)
  {
    try
    {
      int i = paramContext.getResources().getDisplayMetrics().densityDpi;
      return paramFloat * (i / 160.0F);
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return 0.0F;
  }
  
  public static int a(int paramInt, Context paramContext)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * f + 0.5F);
  }
  
  public static int a(Activity paramActivity, String paramString)
  {
    paramActivity = paramActivity.getBaseContext().getSharedPreferences("pollfish_pref", 0);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("pollfish_pref");
    localStringBuilder.append(paramString);
    return paramActivity.getInt(localStringBuilder.toString(), 0);
  }
  
  public static int a(Bitmap paramBitmap, String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBitmap.getWidth() + paramBitmap.getHeight());
      localStringBuilder.append(paramString);
      localStringBuilder.append(paramBitmap.getPixel(0, 0) + (paramBitmap.getPixel(paramBitmap.getWidth() / 2 - 1, paramBitmap.getHeight() / 3 - 1) + paramBitmap.getPixel(paramBitmap.getWidth() - 1, paramBitmap.getHeight() - 1)));
      paramBitmap = localStringBuilder.toString();
    }
    catch (Exception paramBitmap)
    {
      for (;;) {}
    }
    paramBitmap = "";
    return paramBitmap.hashCode();
  }
  
  public static Application.ActivityLifecycleCallbacks a(Activity paramActivity, final BluetoothAdapter.LeScanCallback paramLeScanCallback, final ScanCallback paramScanCallback, BluetoothAdapter paramBluetoothAdapter)
  {
    paramActivity = (Application)paramActivity.getApplicationContext();
    if ((paramActivity != null) && ((paramLeScanCallback != null) || (paramScanCallback != null)) && (paramBluetoothAdapter != null))
    {
      paramLeScanCallback = new Application.ActivityLifecycleCallbacks()
      {
        public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
        
        public void onActivityDestroyed(Activity paramAnonymousActivity) {}
        
        public void onActivityPaused(Activity paramAnonymousActivity)
        {
          paramAnonymousActivity = (Application)paramAnonymousActivity.getApplicationContext();
          if (paramAnonymousActivity != null)
          {
            paramAnonymousActivity.unregisterActivityLifecycleCallbacks(this);
            if (Build.VERSION.SDK_INT < 21)
            {
              if ((this.a != null) && (paramLeScanCallback != null)) {
                this.a.stopLeScan(paramLeScanCallback);
              }
            }
            else if ((this.a != null) && (paramScanCallback != null)) {
              this.a.getBluetoothLeScanner().stopScan(paramScanCallback);
            }
          }
        }
        
        public void onActivityResumed(Activity paramAnonymousActivity) {}
        
        public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
        
        public void onActivityStarted(Activity paramAnonymousActivity) {}
        
        public void onActivityStopped(Activity paramAnonymousActivity) {}
      };
      paramActivity.registerActivityLifecycleCallbacks(paramLeScanCallback);
      return paramLeScanCallback;
    }
    return null;
  }
  
  public static Animation a(View paramView, com.c.e.a paramA, int paramInt, long paramLong)
  {
    if (paramView != null)
    {
      if (paramA == com.c.e.a.a) {
        paramA = new TranslateAnimation(-(paramView.getWidth() * 2), 0.0F, 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.b) {
        paramA = new TranslateAnimation(-(paramView.getWidth() * 2), 0.0F, 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.c) {
        paramA = new TranslateAnimation(paramView.getWidth() * 2, 0.0F, 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.d) {
        paramA = new TranslateAnimation(paramView.getWidth() * 2, 0.0F, 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.f) {
        paramA = new TranslateAnimation(paramView.getWidth() * 2, 0.0F, 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.e) {
        paramA = new TranslateAnimation(-(paramView.getWidth() * 2), 0.0F, 0.0F, 0.0F);
      } else {
        paramA = new TranslateAnimation(-(paramView.getWidth() * 2), 0.0F, 0.0F, 0.0F);
      }
      paramA.setDuration(paramLong);
      paramA.setStartOffset(paramInt);
      paramView.startAnimation(paramA);
      return paramA;
    }
    return null;
  }
  
  public static c a(String paramString, d paramD, j.d paramD1, j.b paramB, Activity paramActivity, boolean paramBoolean)
  {
    if (paramString == null) {
      return null;
    }
    for (;;)
    {
      try
      {
        localObject = new JSONObject(paramString);
        str8 = ((JSONObject)localObject).getString("response_type");
        n = ((JSONObject)localObject).getInt("intrusion");
        i1 = ((JSONObject)localObject).getInt("width_percentage");
        i2 = ((JSONObject)localObject).getInt("height_percentage");
        str9 = ((JSONObject)localObject).getString("content");
        str10 = ((JSONObject)localObject).getString("s_id");
        bool6 = ((JSONObject)localObject).getBoolean("custom_indicator");
        str11 = ((JSONObject)localObject).getString("indicator_image_left");
        str12 = ((JSONObject)localObject).getString("indicator_image_right");
      }
      catch (Exception paramString)
      {
        Object localObject;
        String str8;
        int n;
        int i1;
        int i2;
        String str9;
        String str10;
        boolean bool6;
        String str11;
        String str12;
        boolean bool2;
        boolean bool4;
        boolean bool3;
        String str13;
        int i;
        int j;
        String str1;
        int k;
        String str14;
        String str15;
        return null;
      }
      try
      {
        bool2 = ((JSONObject)localObject).getBoolean("close_on_touch");
      }
      catch (Exception paramString) {}
    }
    bool2 = true;
    bool4 = false;
    try
    {
      bool3 = ((JSONObject)localObject).getBoolean("clear_cache");
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    bool3 = false;
    str13 = ((JSONObject)localObject).getString("mobile_data");
    try
    {
      i = ((JSONObject)localObject).getInt("survey_loi");
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    i = -1;
    try
    {
      j = ((JSONObject)localObject).getInt("survey_ir");
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    j = -1;
    try
    {
      paramString = ((JSONObject)localObject).getString("survey_class");
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    paramString = null;
    try
    {
      str1 = ((JSONObject)localObject).getString("reward_name");
    }
    catch (Exception localException1)
    {
      for (;;) {}
    }
    str1 = null;
    try
    {
      k = ((JSONObject)localObject).getInt("reward_value");
    }
    catch (Exception localException3)
    {
      for (;;) {}
    }
    k = -1;
    str14 = ((JSONObject)localObject).getString("assets");
    str15 = ((JSONObject)localObject).getString("background_color");
    for (;;)
    {
      try
      {
        bool1 = ((JSONObject)localObject).getBoolean("short_survey");
      }
      catch (Exception localException4)
      {
        boolean bool1;
        int m;
        com.c.d.a localA;
        continue;
      }
      try
      {
        m = ((JSONObject)localObject).getInt("survey_price");
      }
      catch (Exception localException5) {}
    }
    bool1 = false;
    m = 0;
    localA = new com.c.d.a(m, j, i, paramString, str1, k);
    for (;;)
    {
      try
      {
        boolean bool5 = ((JSONObject)localObject).getBoolean("video_enabled");
        bool4 = bool5;
      }
      catch (Exception paramString)
      {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        continue;
      }
      try
      {
        paramString = ((JSONObject)localObject).getString("video_color");
      }
      catch (Exception paramString) {}
    }
    paramString = "#ff000000";
    try
    {
      str1 = ((JSONObject)localObject).getString("med_top_view_bg");
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
    str1 = "#FFFFFFFF";
    try
    {
      str2 = ((JSONObject)localObject).getString("med_top_view_sep_bg");
    }
    catch (Exception localException6)
    {
      for (;;) {}
    }
    str2 = "#FFE7EBEF";
    try
    {
      str3 = ((JSONObject)localObject).getString("med_top_view_txt_color");
    }
    catch (Exception localException7)
    {
      for (;;) {}
    }
    str3 = "#FF000000";
    try
    {
      str4 = ((JSONObject)localObject).getString("med_top_view_logo");
    }
    catch (Exception localException8)
    {
      for (;;) {}
    }
    str4 = "/img/pollfishlogo.png";
    try
    {
      str5 = ((JSONObject)localObject).getString("med_bot_view_bg");
    }
    catch (Exception localException9)
    {
      for (;;) {}
    }
    str5 = "#FFFFFFFF";
    try
    {
      str6 = ((JSONObject)localObject).getString("med_bot_view_txt_color");
    }
    catch (Exception localException10)
    {
      for (;;) {}
    }
    str6 = "#FF000000";
    try
    {
      str7 = ((JSONObject)localObject).getString("med_bot_view_sep_bg");
    }
    catch (Exception localException11)
    {
      for (;;) {}
    }
    str7 = "#FFE7EBEF";
    try
    {
      localObject = ((JSONObject)localObject).getString("med_top_progr_bg ");
    }
    catch (Exception localException12)
    {
      for (;;) {}
    }
    localObject = "#FFE44044";
    paramString = new c(str10, str8, n, str9, bool6, str11, str12, i1, i2, paramD.z(), paramD1, str13, str14, paramB, paramActivity, str15, paramBoolean, bool1, m, paramString, bool4, bool2, bool3, str1, str2, str3, str4, str5, str6, str7, (String)localObject, localA);
    return paramString;
  }
  
  public static String a()
  {
    try
    {
      String str = a(Runtime.getRuntime().exec("cat /proc/cpuinfo").getInputStream());
      return str;
    }
    catch (IOException|Exception localIOException)
    {
      for (;;) {}
    }
    return null;
  }
  
  /* Error */
  private static String a(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 256	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 257	java/lang/StringBuilder:<init>	()V
    //   7: astore_1
    //   8: new 470	java/io/BufferedReader
    //   11: dup
    //   12: new 472	java/io/InputStreamReader
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 475	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   20: invokespecial 478	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   23: astore_0
    //   24: aload_0
    //   25: invokevirtual 481	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_2
    //   29: aload_2
    //   30: ifnull +20 -> 50
    //   33: aload_1
    //   34: aload_2
    //   35: invokevirtual 261	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload_1
    //   40: ldc_w 483
    //   43: invokevirtual 261	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: goto -23 -> 24
    //   50: aload_0
    //   51: ifnull +28 -> 79
    //   54: aload_0
    //   55: invokevirtual 486	java/io/BufferedReader:close	()V
    //   58: goto +21 -> 79
    //   61: astore_1
    //   62: aload_0
    //   63: ifnull +7 -> 70
    //   66: aload_0
    //   67: invokevirtual 486	java/io/BufferedReader:close	()V
    //   70: aload_1
    //   71: athrow
    //   72: aload_0
    //   73: ifnull +6 -> 79
    //   76: goto -22 -> 54
    //   79: aload_1
    //   80: invokevirtual 264	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: areturn
    //   84: astore_2
    //   85: goto -13 -> 72
    //   88: astore_0
    //   89: goto -10 -> 79
    //   92: astore_0
    //   93: goto -23 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	paramInputStream	java.io.InputStream
    //   7	33	1	localStringBuilder	StringBuilder
    //   61	19	1	localObject	Object
    //   28	7	2	str	String
    //   84	1	2	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   24	29	61	finally
    //   33	47	61	finally
    //   24	29	84	java/io/IOException
    //   33	47	84	java/io/IOException
    //   54	58	88	java/io/IOException
    //   66	70	92	java/io/IOException
  }
  
  public static String a(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = new SecretKeySpec(paramString2.getBytes(), "HmacSHA256");
      Mac localMac = Mac.getInstance(paramString2.getAlgorithm());
      localMac.init(paramString2);
      paramString1 = a(localMac.doFinal(paramString1.getBytes()));
      return paramString1;
    }
    catch (NoSuchAlgorithmException paramString1)
    {
      for (;;) {}
    }
    catch (InvalidKeyException paramString1)
    {
      for (;;) {}
    }
    throw new SignatureException("error building signature, invalid key HmacSHA256");
    throw new SignatureException("error building signature, no such algorithm in device HmacSHA256");
  }
  
  /* Error */
  public static String a(java.net.HttpURLConnection paramHttpURLConnection)
  {
    // Byte code:
    //   0: new 534	java/lang/StringBuffer
    //   3: dup
    //   4: invokespecial 535	java/lang/StringBuffer:<init>	()V
    //   7: astore_1
    //   8: new 537	java/io/BufferedInputStream
    //   11: dup
    //   12: aload_0
    //   13: invokevirtual 540	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   16: invokespecial 541	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   19: astore_0
    //   20: new 470	java/io/BufferedReader
    //   23: dup
    //   24: new 472	java/io/InputStreamReader
    //   27: dup
    //   28: aload_0
    //   29: invokespecial 475	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   32: invokespecial 478	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   35: astore_2
    //   36: aload_2
    //   37: invokevirtual 481	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   40: astore_3
    //   41: aload_3
    //   42: ifnull +12 -> 54
    //   45: aload_1
    //   46: aload_3
    //   47: invokevirtual 544	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   50: pop
    //   51: goto -15 -> 36
    //   54: aload_1
    //   55: invokevirtual 545	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   58: astore_2
    //   59: aload_2
    //   60: astore_1
    //   61: aload_0
    //   62: ifnull +42 -> 104
    //   65: aload_0
    //   66: invokevirtual 548	java/io/InputStream:close	()V
    //   69: aload_2
    //   70: areturn
    //   71: astore_2
    //   72: aload_0
    //   73: astore_1
    //   74: aload_2
    //   75: astore_0
    //   76: goto +6 -> 82
    //   79: astore_0
    //   80: aconst_null
    //   81: astore_1
    //   82: aload_1
    //   83: ifnull +7 -> 90
    //   86: aload_1
    //   87: invokevirtual 548	java/io/InputStream:close	()V
    //   90: aload_0
    //   91: athrow
    //   92: aconst_null
    //   93: astore_0
    //   94: aload_0
    //   95: ifnull +7 -> 102
    //   98: aload_0
    //   99: invokevirtual 548	java/io/InputStream:close	()V
    //   102: aconst_null
    //   103: astore_1
    //   104: aload_1
    //   105: areturn
    //   106: astore_0
    //   107: goto -15 -> 92
    //   110: astore_1
    //   111: goto -17 -> 94
    //   114: astore_0
    //   115: aload_2
    //   116: areturn
    //   117: astore_1
    //   118: goto -28 -> 90
    //   121: astore_0
    //   122: goto -20 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	paramHttpURLConnection	java.net.HttpURLConnection
    //   7	98	1	localObject1	Object
    //   110	1	1	localException	Exception
    //   117	1	1	localIOException	IOException
    //   35	35	2	localObject2	Object
    //   71	45	2	str1	String
    //   40	7	3	str2	String
    // Exception table:
    //   from	to	target	type
    //   20	36	71	finally
    //   36	41	71	finally
    //   45	51	71	finally
    //   54	59	71	finally
    //   8	20	79	finally
    //   8	20	106	java/lang/Exception
    //   20	36	110	java/lang/Exception
    //   36	41	110	java/lang/Exception
    //   45	51	110	java/lang/Exception
    //   54	59	110	java/lang/Exception
    //   65	69	114	java/io/IOException
    //   86	90	117	java/io/IOException
    //   98	102	121	java/io/IOException
  }
  
  public static String a(List<Pair<String, String>> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = 1;
    while (i < paramList.size())
    {
      Pair localPair = (Pair)paramList.get(i);
      if (j != 0) {
        j = 0;
      } else {
        localStringBuilder.append("&");
      }
      localStringBuilder.append(URLEncoder.encode((String)localPair.first, "UTF-8"));
      localStringBuilder.append("=");
      localStringBuilder.append(URLEncoder.encode((String)localPair.second, "UTF-8"));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
    Formatter localFormatter = new Formatter(localStringBuilder);
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      localFormatter.format("%02x", new Object[] { Byte.valueOf(paramArrayOfByte[i]) });
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  /* Error */
  public static JSONObject a(Activity paramActivity, final j.c paramC, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 39	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore 7
    //   6: aload 7
    //   8: ldc_w 602
    //   11: aload_0
    //   12: invokevirtual 45	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   15: invokevirtual 51	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   18: ifne +558 -> 576
    //   21: aload 7
    //   23: ldc_w 604
    //   26: aload_0
    //   27: invokevirtual 45	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   30: invokevirtual 51	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   33: istore_3
    //   34: iload_3
    //   35: ifne +541 -> 576
    //   38: iconst_0
    //   39: istore 5
    //   41: getstatic 609	android/os/Build$VERSION:SDK_INT	I
    //   44: bipush 18
    //   46: if_icmplt +21 -> 67
    //   49: aload_0
    //   50: ldc_w 611
    //   53: invokevirtual 59	android/app/Activity:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   56: checkcast 613	android/bluetooth/BluetoothManager
    //   59: invokevirtual 617	android/bluetooth/BluetoothManager:getAdapter	()Landroid/bluetooth/BluetoothAdapter;
    //   62: astore 9
    //   64: goto +8 -> 72
    //   67: invokestatic 622	android/bluetooth/BluetoothAdapter:getDefaultAdapter	()Landroid/bluetooth/BluetoothAdapter;
    //   70: astore 9
    //   72: iload 5
    //   74: istore 4
    //   76: aload 9
    //   78: astore 6
    //   80: aload 9
    //   82: ifnull +272 -> 354
    //   85: aload 9
    //   87: invokevirtual 625	android/bluetooth/BluetoothAdapter:isEnabled	()Z
    //   90: istore 4
    //   92: aload 7
    //   94: ldc_w 627
    //   97: invokevirtual 630	android/content/pm/PackageManager:hasSystemFeature	(Ljava/lang/String;)Z
    //   100: istore 5
    //   102: iload 4
    //   104: ifeq +236 -> 340
    //   107: getstatic 609	android/os/Build$VERSION:SDK_INT	I
    //   110: bipush 18
    //   112: if_icmplt +228 -> 340
    //   115: iload 5
    //   117: ifeq +223 -> 340
    //   120: iload_2
    //   121: ifeq +219 -> 340
    //   124: new 632	java/util/concurrent/ConcurrentHashMap
    //   127: dup
    //   128: invokespecial 633	java/util/concurrent/ConcurrentHashMap:<init>	()V
    //   131: astore 10
    //   133: getstatic 609	android/os/Build$VERSION:SDK_INT	I
    //   136: bipush 21
    //   138: if_icmpge +68 -> 206
    //   141: new 8	com/c/f/b$2
    //   144: dup
    //   145: aload 10
    //   147: invokespecial 636	com/c/f/b$2:<init>	(Ljava/util/concurrent/ConcurrentHashMap;)V
    //   150: astore 6
    //   152: aload 9
    //   154: ifnull +43 -> 197
    //   157: aload 6
    //   159: ifnull +38 -> 197
    //   162: aload 9
    //   164: aload 6
    //   166: invokevirtual 640	android/bluetooth/BluetoothAdapter:startLeScan	(Landroid/bluetooth/BluetoothAdapter$LeScanCallback;)Z
    //   169: pop
    //   170: getstatic 609	android/os/Build$VERSION:SDK_INT	I
    //   173: istore_3
    //   174: iload_3
    //   175: bipush 14
    //   177: if_icmplt +20 -> 197
    //   180: aload_0
    //   181: aload 6
    //   183: aconst_null
    //   184: aload 9
    //   186: invokestatic 642	com/c/f/b:a	(Landroid/app/Activity;Landroid/bluetooth/BluetoothAdapter$LeScanCallback;Landroid/bluetooth/le/ScanCallback;Landroid/bluetooth/BluetoothAdapter;)Landroid/app/Application$ActivityLifecycleCallbacks;
    //   189: astore 8
    //   191: aconst_null
    //   192: astore 7
    //   194: goto +107 -> 301
    //   197: aconst_null
    //   198: astore 7
    //   200: aconst_null
    //   201: astore 8
    //   203: goto +98 -> 301
    //   206: aload 7
    //   208: ldc 41
    //   210: aload_0
    //   211: invokevirtual 45	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   214: invokevirtual 51	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   217: ifeq +20 -> 237
    //   220: aload 7
    //   222: ldc 53
    //   224: aload_0
    //   225: invokevirtual 45	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   228: invokevirtual 51	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   231: ifne +404 -> 635
    //   234: goto +3 -> 237
    //   237: new 10	com/c/f/b$3
    //   240: dup
    //   241: aload 10
    //   243: invokespecial 643	com/c/f/b$3:<init>	(Ljava/util/concurrent/ConcurrentHashMap;)V
    //   246: astore 7
    //   248: new 645	android/bluetooth/le/ScanSettings$Builder
    //   251: dup
    //   252: invokespecial 646	android/bluetooth/le/ScanSettings$Builder:<init>	()V
    //   255: iconst_0
    //   256: invokevirtual 650	android/bluetooth/le/ScanSettings$Builder:setScanMode	(I)Landroid/bluetooth/le/ScanSettings$Builder;
    //   259: invokevirtual 654	android/bluetooth/le/ScanSettings$Builder:build	()Landroid/bluetooth/le/ScanSettings;
    //   262: astore 6
    //   264: aload 9
    //   266: invokevirtual 658	android/bluetooth/BluetoothAdapter:getBluetoothLeScanner	()Landroid/bluetooth/le/BluetoothLeScanner;
    //   269: aconst_null
    //   270: aload 6
    //   272: aload 7
    //   274: invokevirtual 664	android/bluetooth/le/BluetoothLeScanner:startScan	(Ljava/util/List;Landroid/bluetooth/le/ScanSettings;Landroid/bluetooth/le/ScanCallback;)V
    //   277: aload_0
    //   278: aconst_null
    //   279: aload 7
    //   281: aload 9
    //   283: invokestatic 642	com/c/f/b:a	(Landroid/app/Activity;Landroid/bluetooth/BluetoothAdapter$LeScanCallback;Landroid/bluetooth/le/ScanCallback;Landroid/bluetooth/BluetoothAdapter;)Landroid/app/Application$ActivityLifecycleCallbacks;
    //   286: astore 8
    //   288: aconst_null
    //   289: astore 6
    //   291: goto +10 -> 301
    //   294: aconst_null
    //   295: astore 6
    //   297: aload 6
    //   299: astore 8
    //   301: new 666	android/os/Handler
    //   304: dup
    //   305: invokestatic 672	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   308: invokespecial 675	android/os/Handler:<init>	(Landroid/os/Looper;)V
    //   311: new 12	com/c/f/b$4
    //   314: dup
    //   315: aload 10
    //   317: aload_1
    //   318: invokespecial 678	com/c/f/b$4:<init>	(Ljava/util/concurrent/ConcurrentHashMap;Lcom/c/h/j$c;)V
    //   321: aload_0
    //   322: aload 9
    //   324: aload 6
    //   326: aload 7
    //   328: aload 8
    //   330: invokevirtual 681	com/c/f/b$4:a	(Landroid/app/Activity;Landroid/bluetooth/BluetoothAdapter;Landroid/bluetooth/BluetoothAdapter$LeScanCallback;Landroid/bluetooth/le/ScanCallback;Landroid/app/Application$ActivityLifecycleCallbacks;)Ljava/lang/Runnable;
    //   333: ldc2_w 682
    //   336: invokevirtual 687	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   339: pop
    //   340: aload 9
    //   342: astore 6
    //   344: iload 5
    //   346: istore_2
    //   347: goto +22 -> 369
    //   350: aload 9
    //   352: astore 6
    //   354: iconst_0
    //   355: istore_2
    //   356: goto +13 -> 369
    //   359: aconst_null
    //   360: astore 6
    //   362: iload 5
    //   364: istore 4
    //   366: goto -12 -> 354
    //   369: new 93	org/json/JSONObject
    //   372: dup
    //   373: invokespecial 96	org/json/JSONObject:<init>	()V
    //   376: astore_1
    //   377: aload_1
    //   378: ldc_w 689
    //   381: iload 4
    //   383: invokevirtual 692	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   386: pop
    //   387: aload_1
    //   388: astore_0
    //   389: aload 6
    //   391: ifnull +187 -> 578
    //   394: aload 6
    //   396: invokevirtual 695	android/bluetooth/BluetoothAdapter:getAddress	()Ljava/lang/String;
    //   399: astore_0
    //   400: aload_0
    //   401: ifnull +12 -> 413
    //   404: aload_1
    //   405: ldc_w 697
    //   408: aload_0
    //   409: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   412: pop
    //   413: aload 6
    //   415: invokevirtual 700	android/bluetooth/BluetoothAdapter:getName	()Ljava/lang/String;
    //   418: astore_0
    //   419: aload_0
    //   420: ifnull +12 -> 432
    //   423: aload_1
    //   424: ldc_w 702
    //   427: aload_0
    //   428: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   431: pop
    //   432: aload 6
    //   434: invokevirtual 706	android/bluetooth/BluetoothAdapter:getBondedDevices	()Ljava/util/Set;
    //   437: astore_0
    //   438: aload_0
    //   439: ifnull +21 -> 460
    //   442: aload_0
    //   443: invokeinterface 709 1 0
    //   448: ifle +12 -> 460
    //   451: aload_1
    //   452: ldc_w 711
    //   455: aload_0
    //   456: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   459: pop
    //   460: getstatic 609	android/os/Build$VERSION:SDK_INT	I
    //   463: bipush 21
    //   465: if_icmplt +16 -> 481
    //   468: aload_1
    //   469: ldc_w 713
    //   472: aload 6
    //   474: invokevirtual 716	android/bluetooth/BluetoothAdapter:isOffloadedFilteringSupported	()Z
    //   477: invokevirtual 692	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   480: pop
    //   481: getstatic 609	android/os/Build$VERSION:SDK_INT	I
    //   484: bipush 21
    //   486: if_icmplt +16 -> 502
    //   489: aload_1
    //   490: ldc_w 718
    //   493: aload 6
    //   495: invokevirtual 721	android/bluetooth/BluetoothAdapter:isOffloadedScanBatchingSupported	()Z
    //   498: invokevirtual 692	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   501: pop
    //   502: getstatic 609	android/os/Build$VERSION:SDK_INT	I
    //   505: bipush 21
    //   507: if_icmplt +16 -> 523
    //   510: aload_1
    //   511: ldc_w 723
    //   514: aload 6
    //   516: invokevirtual 726	android/bluetooth/BluetoothAdapter:isMultipleAdvertisementSupported	()Z
    //   519: invokevirtual 692	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   522: pop
    //   523: aload_1
    //   524: ldc_w 728
    //   527: aload 6
    //   529: invokevirtual 731	android/bluetooth/BluetoothAdapter:getState	()I
    //   532: invokevirtual 734	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   535: pop
    //   536: aload_1
    //   537: ldc_w 736
    //   540: aload 6
    //   542: invokevirtual 739	android/bluetooth/BluetoothAdapter:getScanMode	()I
    //   545: invokevirtual 734	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   548: pop
    //   549: aload_1
    //   550: ldc_w 741
    //   553: aload 6
    //   555: invokevirtual 744	android/bluetooth/BluetoothAdapter:isDiscovering	()Z
    //   558: invokevirtual 692	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   561: pop
    //   562: aload_1
    //   563: ldc_w 746
    //   566: iload_2
    //   567: invokevirtual 692	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   570: pop
    //   571: aload_1
    //   572: astore_0
    //   573: goto +5 -> 578
    //   576: aconst_null
    //   577: astore_0
    //   578: aload_0
    //   579: ifnull +5 -> 584
    //   582: aload_0
    //   583: areturn
    //   584: aconst_null
    //   585: areturn
    //   586: astore_0
    //   587: aconst_null
    //   588: areturn
    //   589: astore_0
    //   590: goto -231 -> 359
    //   593: astore_0
    //   594: iload 5
    //   596: istore 4
    //   598: aload 9
    //   600: astore 6
    //   602: goto -248 -> 354
    //   605: astore_0
    //   606: goto -256 -> 350
    //   609: astore_0
    //   610: goto -270 -> 340
    //   613: astore 7
    //   615: goto -418 -> 197
    //   618: astore 6
    //   620: goto -326 -> 294
    //   623: astore_0
    //   624: goto -143 -> 481
    //   627: astore_0
    //   628: goto -126 -> 502
    //   631: astore_0
    //   632: goto -109 -> 523
    //   635: aconst_null
    //   636: astore 6
    //   638: goto -441 -> 197
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	641	0	paramActivity	Activity
    //   0	641	1	paramC	j.c
    //   0	641	2	paramBoolean	boolean
    //   33	145	3	i	int
    //   74	523	4	bool1	boolean
    //   39	556	5	bool2	boolean
    //   78	523	6	localObject1	Object
    //   618	1	6	localException1	Exception
    //   636	1	6	localObject2	Object
    //   4	323	7	localObject3	Object
    //   613	1	7	localException2	Exception
    //   189	140	8	localObject4	Object
    //   62	537	9	localBluetoothAdapter	BluetoothAdapter
    //   131	185	10	localConcurrentHashMap	ConcurrentHashMap
    // Exception table:
    //   from	to	target	type
    //   0	34	586	java/lang/Exception
    //   0	34	586	java/lang/NoSuchMethodError
    //   0	34	586	java/lang/NoClassDefFoundError
    //   369	387	586	java/lang/Exception
    //   369	387	586	java/lang/NoSuchMethodError
    //   369	387	586	java/lang/NoClassDefFoundError
    //   394	400	586	java/lang/Exception
    //   394	400	586	java/lang/NoSuchMethodError
    //   394	400	586	java/lang/NoClassDefFoundError
    //   404	413	586	java/lang/Exception
    //   404	413	586	java/lang/NoSuchMethodError
    //   404	413	586	java/lang/NoClassDefFoundError
    //   413	419	586	java/lang/Exception
    //   413	419	586	java/lang/NoSuchMethodError
    //   413	419	586	java/lang/NoClassDefFoundError
    //   423	432	586	java/lang/Exception
    //   423	432	586	java/lang/NoSuchMethodError
    //   423	432	586	java/lang/NoClassDefFoundError
    //   432	438	586	java/lang/Exception
    //   432	438	586	java/lang/NoSuchMethodError
    //   432	438	586	java/lang/NoClassDefFoundError
    //   442	460	586	java/lang/Exception
    //   442	460	586	java/lang/NoSuchMethodError
    //   442	460	586	java/lang/NoClassDefFoundError
    //   460	481	586	java/lang/Exception
    //   460	481	586	java/lang/NoClassDefFoundError
    //   481	502	586	java/lang/Exception
    //   481	502	586	java/lang/NoClassDefFoundError
    //   502	523	586	java/lang/Exception
    //   502	523	586	java/lang/NoClassDefFoundError
    //   523	571	586	java/lang/Exception
    //   523	571	586	java/lang/NoSuchMethodError
    //   523	571	586	java/lang/NoClassDefFoundError
    //   41	64	589	java/lang/Exception
    //   41	64	589	java/lang/NoSuchMethodError
    //   41	64	589	java/lang/NoClassDefFoundError
    //   67	72	589	java/lang/Exception
    //   67	72	589	java/lang/NoSuchMethodError
    //   67	72	589	java/lang/NoClassDefFoundError
    //   85	92	593	java/lang/Exception
    //   85	92	593	java/lang/NoSuchMethodError
    //   85	92	593	java/lang/NoClassDefFoundError
    //   92	102	605	java/lang/Exception
    //   92	102	605	java/lang/NoSuchMethodError
    //   92	102	605	java/lang/NoClassDefFoundError
    //   107	115	609	java/lang/Exception
    //   107	115	609	java/lang/NoSuchMethodError
    //   107	115	609	java/lang/NoClassDefFoundError
    //   124	152	609	java/lang/Exception
    //   124	152	609	java/lang/NoSuchMethodError
    //   124	152	609	java/lang/NoClassDefFoundError
    //   162	174	609	java/lang/Exception
    //   162	174	609	java/lang/NoSuchMethodError
    //   162	174	609	java/lang/NoClassDefFoundError
    //   180	191	609	java/lang/NoSuchMethodError
    //   180	191	609	java/lang/NoClassDefFoundError
    //   206	234	609	java/lang/Exception
    //   206	234	609	java/lang/NoSuchMethodError
    //   206	234	609	java/lang/NoClassDefFoundError
    //   237	277	609	java/lang/Exception
    //   237	277	609	java/lang/NoSuchMethodError
    //   237	277	609	java/lang/NoClassDefFoundError
    //   277	288	609	java/lang/NoSuchMethodError
    //   277	288	609	java/lang/NoClassDefFoundError
    //   301	340	609	java/lang/Exception
    //   301	340	609	java/lang/NoSuchMethodError
    //   301	340	609	java/lang/NoClassDefFoundError
    //   180	191	613	java/lang/Exception
    //   277	288	618	java/lang/Exception
    //   460	481	623	java/lang/NoSuchMethodError
    //   481	502	627	java/lang/NoSuchMethodError
    //   502	523	631	java/lang/NoSuchMethodError
  }
  
  /* Error */
  public static JSONObject a(d paramD)
  {
    // Byte code:
    //   0: new 93	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 96	org/json/JSONObject:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: ifnull +579 -> 588
    //   12: aload_2
    //   13: ldc_w 749
    //   16: aload_0
    //   17: invokevirtual 751	com/c/d/d:d	()Ljava/lang/String;
    //   20: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   23: pop
    //   24: aload_0
    //   25: invokevirtual 754	com/c/d/d:r	()Ljava/lang/String;
    //   28: ifnull +15 -> 43
    //   31: aload_2
    //   32: ldc_w 756
    //   35: aload_0
    //   36: invokevirtual 754	com/c/d/d:r	()Ljava/lang/String;
    //   39: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   42: pop
    //   43: aload_0
    //   44: invokevirtual 758	com/c/d/d:e	()Ljava/lang/String;
    //   47: ifnull +15 -> 62
    //   50: aload_2
    //   51: ldc_w 760
    //   54: aload_0
    //   55: invokevirtual 758	com/c/d/d:e	()Ljava/lang/String;
    //   58: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   61: pop
    //   62: aload_0
    //   63: invokevirtual 762	com/c/d/d:f	()Ljava/lang/String;
    //   66: ifnull +15 -> 81
    //   69: aload_2
    //   70: ldc_w 764
    //   73: aload_0
    //   74: invokevirtual 762	com/c/d/d:f	()Ljava/lang/String;
    //   77: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   80: pop
    //   81: aload_0
    //   82: invokevirtual 767	com/c/d/d:q	()Ljava/lang/String;
    //   85: ifnull +15 -> 100
    //   88: aload_2
    //   89: ldc_w 769
    //   92: aload_0
    //   93: invokevirtual 767	com/c/d/d:q	()Ljava/lang/String;
    //   96: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   99: pop
    //   100: aload_0
    //   101: invokevirtual 442	com/c/d/d:z	()Lcom/c/e/a;
    //   104: ifnull +50 -> 154
    //   107: iconst_0
    //   108: istore_1
    //   109: aload_0
    //   110: invokevirtual 442	com/c/d/d:z	()Lcom/c/e/a;
    //   113: getstatic 320	com/c/e/a:c	Lcom/c/e/a;
    //   116: if_acmpeq +501 -> 617
    //   119: aload_0
    //   120: invokevirtual 442	com/c/d/d:z	()Lcom/c/e/a;
    //   123: getstatic 323	com/c/e/a:d	Lcom/c/e/a;
    //   126: if_acmpeq +491 -> 617
    //   129: aload_0
    //   130: invokevirtual 442	com/c/d/d:z	()Lcom/c/e/a;
    //   133: getstatic 326	com/c/e/a:f	Lcom/c/e/a;
    //   136: if_acmpne +6 -> 142
    //   139: goto +478 -> 617
    //   142: aload_2
    //   143: ldc_w 771
    //   146: iload_1
    //   147: invokestatic 774	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   150: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   153: pop
    //   154: aload_0
    //   155: invokevirtual 777	com/c/d/d:K	()Ljava/lang/String;
    //   158: astore_3
    //   159: aload_3
    //   160: ifnull +18 -> 178
    //   163: aload_2
    //   164: ldc_w 779
    //   167: aload_0
    //   168: invokevirtual 777	com/c/d/d:K	()Ljava/lang/String;
    //   171: invokestatic 782	org/json/JSONObject:quote	(Ljava/lang/String;)Ljava/lang/String;
    //   174: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   177: pop
    //   178: aload_2
    //   179: ldc_w 784
    //   182: aload_0
    //   183: invokevirtual 786	com/c/d/d:y	()Z
    //   186: invokestatic 789	java/lang/String:valueOf	(Z)Ljava/lang/String;
    //   189: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   192: pop
    //   193: aload_0
    //   194: invokevirtual 792	com/c/d/d:p	()Ljava/lang/String;
    //   197: ifnull +15 -> 212
    //   200: aload_2
    //   201: ldc_w 794
    //   204: aload_0
    //   205: invokevirtual 792	com/c/d/d:p	()Ljava/lang/String;
    //   208: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   211: pop
    //   212: aload_0
    //   213: invokevirtual 798	com/c/d/d:V	()Lorg/json/JSONArray;
    //   216: ifnull +15 -> 231
    //   219: aload_2
    //   220: ldc_w 800
    //   223: aload_0
    //   224: invokevirtual 798	com/c/d/d:V	()Lorg/json/JSONArray;
    //   227: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   230: pop
    //   231: aload_0
    //   232: invokevirtual 802	com/c/d/d:I	()Ljava/lang/String;
    //   235: ifnull +15 -> 250
    //   238: aload_2
    //   239: ldc_w 804
    //   242: aload_0
    //   243: invokevirtual 802	com/c/d/d:I	()Ljava/lang/String;
    //   246: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   249: pop
    //   250: aload_0
    //   251: invokevirtual 807	com/c/d/d:R	()Ljava/lang/String;
    //   254: astore_3
    //   255: aload_3
    //   256: ifnull +18 -> 274
    //   259: aload_2
    //   260: ldc_w 809
    //   263: aload_0
    //   264: invokevirtual 807	com/c/d/d:R	()Ljava/lang/String;
    //   267: invokestatic 782	org/json/JSONObject:quote	(Ljava/lang/String;)Ljava/lang/String;
    //   270: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   273: pop
    //   274: aload_0
    //   275: invokevirtual 812	com/c/d/d:S	()Ljava/lang/String;
    //   278: astore_3
    //   279: aload_3
    //   280: ifnull +18 -> 298
    //   283: aload_2
    //   284: ldc_w 814
    //   287: aload_0
    //   288: invokevirtual 812	com/c/d/d:S	()Ljava/lang/String;
    //   291: invokestatic 782	org/json/JSONObject:quote	(Ljava/lang/String;)Ljava/lang/String;
    //   294: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   297: pop
    //   298: aload_0
    //   299: invokevirtual 817	com/c/d/d:O	()I
    //   302: istore_1
    //   303: iload_1
    //   304: iconst_m1
    //   305: if_icmpeq +18 -> 323
    //   308: aload_2
    //   309: ldc_w 819
    //   312: aload_0
    //   313: invokevirtual 817	com/c/d/d:O	()I
    //   316: invokestatic 774	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   319: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   322: pop
    //   323: aload_2
    //   324: ldc_w 821
    //   327: aload_0
    //   328: invokevirtual 824	com/c/d/d:g	()Ljava/lang/String;
    //   331: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   334: pop
    //   335: aload_2
    //   336: ldc_w 826
    //   339: aload_0
    //   340: invokevirtual 829	com/c/d/d:h	()Ljava/lang/String;
    //   343: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   346: pop
    //   347: aload_0
    //   348: invokevirtual 832	com/c/d/d:v	()Ljava/lang/String;
    //   351: ifnull +15 -> 366
    //   354: aload_2
    //   355: ldc_w 834
    //   358: aload_0
    //   359: invokevirtual 832	com/c/d/d:v	()Ljava/lang/String;
    //   362: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   365: pop
    //   366: aload_0
    //   367: invokevirtual 837	com/c/d/d:w	()Ljava/lang/String;
    //   370: ifnull +15 -> 385
    //   373: aload_2
    //   374: ldc_w 839
    //   377: aload_0
    //   378: invokevirtual 837	com/c/d/d:w	()Ljava/lang/String;
    //   381: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   384: pop
    //   385: aload_2
    //   386: ldc_w 841
    //   389: aload_0
    //   390: invokevirtual 844	com/c/d/d:j	()Ljava/lang/String;
    //   393: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   396: pop
    //   397: aload_0
    //   398: invokevirtual 847	com/c/d/d:E	()Ljava/lang/String;
    //   401: ifnull +15 -> 416
    //   404: aload_2
    //   405: ldc_w 849
    //   408: aload_0
    //   409: invokevirtual 847	com/c/d/d:E	()Ljava/lang/String;
    //   412: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   415: pop
    //   416: aload_0
    //   417: invokevirtual 851	com/c/d/d:F	()Ljava/lang/String;
    //   420: ifnull +15 -> 435
    //   423: aload_2
    //   424: ldc_w 853
    //   427: aload_0
    //   428: invokevirtual 851	com/c/d/d:F	()Ljava/lang/String;
    //   431: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   434: pop
    //   435: aload_2
    //   436: ldc_w 855
    //   439: aload_0
    //   440: invokevirtual 858	com/c/d/d:U	()Z
    //   443: invokestatic 789	java/lang/String:valueOf	(Z)Ljava/lang/String;
    //   446: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   449: pop
    //   450: aload_0
    //   451: invokevirtual 862	com/c/d/d:t	()Lorg/json/JSONObject;
    //   454: ifnull +15 -> 469
    //   457: aload_2
    //   458: ldc_w 864
    //   461: aload_0
    //   462: invokevirtual 862	com/c/d/d:t	()Lorg/json/JSONObject;
    //   465: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   468: pop
    //   469: aload_0
    //   470: invokevirtual 867	com/c/d/d:i	()Ljava/lang/String;
    //   473: ifnull +15 -> 488
    //   476: aload_2
    //   477: ldc_w 869
    //   480: aload_0
    //   481: invokevirtual 867	com/c/d/d:i	()Ljava/lang/String;
    //   484: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   487: pop
    //   488: aload_2
    //   489: ldc_w 871
    //   492: aload_0
    //   493: invokevirtual 874	com/c/d/d:s	()Ljava/lang/String;
    //   496: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   499: pop
    //   500: aload_0
    //   501: invokevirtual 877	com/c/d/d:k	()Ljava/lang/String;
    //   504: ifnull +15 -> 519
    //   507: aload_2
    //   508: ldc_w 879
    //   511: aload_0
    //   512: invokevirtual 877	com/c/d/d:k	()Ljava/lang/String;
    //   515: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   518: pop
    //   519: aload_0
    //   520: invokevirtual 882	com/c/d/d:m	()Ljava/lang/String;
    //   523: ifnull +15 -> 538
    //   526: aload_2
    //   527: ldc_w 884
    //   530: aload_0
    //   531: invokevirtual 882	com/c/d/d:m	()Ljava/lang/String;
    //   534: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   537: pop
    //   538: aload_0
    //   539: invokevirtual 887	com/c/d/d:n	()Ljava/lang/String;
    //   542: ifnull +15 -> 557
    //   545: aload_2
    //   546: ldc_w 889
    //   549: aload_0
    //   550: invokevirtual 887	com/c/d/d:n	()Ljava/lang/String;
    //   553: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   556: pop
    //   557: aload_0
    //   558: invokevirtual 892	com/c/d/d:o	()Ljava/lang/String;
    //   561: ifnull +15 -> 576
    //   564: aload_2
    //   565: ldc_w 894
    //   568: aload_0
    //   569: invokevirtual 892	com/c/d/d:o	()Ljava/lang/String;
    //   572: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   575: pop
    //   576: aload_2
    //   577: ldc_w 896
    //   580: aload_0
    //   581: invokevirtual 898	com/c/d/d:C	()Ljava/lang/String;
    //   584: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   587: pop
    //   588: aload_2
    //   589: areturn
    //   590: astore_0
    //   591: aload_2
    //   592: areturn
    //   593: astore_3
    //   594: goto -416 -> 178
    //   597: astore_3
    //   598: goto -405 -> 193
    //   601: astore_3
    //   602: goto -328 -> 274
    //   605: astore_3
    //   606: goto -308 -> 298
    //   609: astore_3
    //   610: goto -287 -> 323
    //   613: astore_3
    //   614: goto -164 -> 450
    //   617: iconst_1
    //   618: istore_1
    //   619: goto -477 -> 142
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	622	0	paramD	d
    //   108	511	1	i	int
    //   7	585	2	localJSONObject	JSONObject
    //   158	122	3	str	String
    //   593	1	3	localException1	Exception
    //   597	1	3	localException2	Exception
    //   601	1	3	localException3	Exception
    //   605	1	3	localException4	Exception
    //   609	1	3	localException5	Exception
    //   613	1	3	localException6	Exception
    // Exception table:
    //   from	to	target	type
    //   12	43	590	java/lang/Exception
    //   43	62	590	java/lang/Exception
    //   62	81	590	java/lang/Exception
    //   81	100	590	java/lang/Exception
    //   100	107	590	java/lang/Exception
    //   109	129	590	java/lang/Exception
    //   129	139	590	java/lang/Exception
    //   142	154	590	java/lang/Exception
    //   154	159	590	java/lang/Exception
    //   193	212	590	java/lang/Exception
    //   212	231	590	java/lang/Exception
    //   231	250	590	java/lang/Exception
    //   250	255	590	java/lang/Exception
    //   274	279	590	java/lang/Exception
    //   298	303	590	java/lang/Exception
    //   323	366	590	java/lang/Exception
    //   366	385	590	java/lang/Exception
    //   385	416	590	java/lang/Exception
    //   416	435	590	java/lang/Exception
    //   450	469	590	java/lang/Exception
    //   469	488	590	java/lang/Exception
    //   488	519	590	java/lang/Exception
    //   519	538	590	java/lang/Exception
    //   538	557	590	java/lang/Exception
    //   557	576	590	java/lang/Exception
    //   576	588	590	java/lang/Exception
    //   163	178	593	java/lang/Exception
    //   178	193	597	java/lang/Exception
    //   259	274	601	java/lang/Exception
    //   283	298	605	java/lang/Exception
    //   308	323	609	java/lang/Exception
    //   435	450	613	java/lang/Exception
  }
  
  public static void a(Activity paramActivity, String paramString, int paramInt)
  {
    paramActivity = paramActivity.getBaseContext().getSharedPreferences("pollfish_pref", 0).edit();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("pollfish_pref");
    localStringBuilder.append(paramString);
    paramActivity.putInt(localStringBuilder.toString(), paramInt);
    paramActivity.commit();
  }
  
  public static void a(Context paramContext, Runnable paramRunnable, int paramInt)
  {
    try
    {
      new Handler(paramContext.getMainLooper()).postDelayed(paramRunnable, paramInt);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean a(Activity paramActivity)
  {
    boolean bool2 = false;
    try
    {
      PackageManager localPackageManager = paramActivity.getPackageManager();
      boolean bool1 = bool2;
      if (localPackageManager.checkPermission("android.permission.INTERNET", paramActivity.getPackageName()) == 0)
      {
        int i = localPackageManager.checkPermission("android.permission.ACCESS_WIFI_STATE", paramActivity.getPackageName());
        bool1 = bool2;
        if (i == 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramActivity) {}
    return false;
  }
  
  public static boolean a(View paramView)
  {
    try
    {
      boolean bool = paramView.isHardwareAccelerated();
      return bool;
    }
    catch (NoSuchMethodError paramView)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean a(String paramString, int paramInt)
  {
    int i = paramString.length();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramInt < i)
    {
      bool1 = bool2;
      if (paramString.charAt(paramInt) == '1') {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static Animation b(View paramView, com.c.e.a paramA, int paramInt, long paramLong)
  {
    if (paramView != null)
    {
      if (paramA == com.c.e.a.a) {
        paramA = new TranslateAnimation(0.0F, -(paramView.getWidth() * 2), 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.b) {
        paramA = new TranslateAnimation(0.0F, -(paramView.getWidth() * 2), 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.c) {
        paramA = new TranslateAnimation(0.0F, paramView.getWidth() * 2, 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.d) {
        paramA = new TranslateAnimation(0.0F, paramView.getWidth() * 2, 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.f) {
        paramA = new TranslateAnimation(0.0F, paramView.getWidth() * 2, 0.0F, 0.0F);
      } else if (paramA == com.c.e.a.b) {
        paramA = new TranslateAnimation(0.0F, -(paramView.getWidth() * 2), 0.0F, 0.0F);
      } else {
        paramA = new TranslateAnimation(0.0F, -(paramView.getWidth() * 2), 0.0F, 0.0F);
      }
      paramA.setDuration(paramLong);
      paramA.setStartOffset(paramInt);
      paramView.startAnimation(paramA);
      return paramA;
    }
    return null;
  }
  
  public static String b()
  {
    try
    {
      String str = a(Runtime.getRuntime().exec("cat /proc/meminfo").getInputStream());
      return str;
    }
    catch (IOException|Exception localIOException)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static void b(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfByte, ConcurrentHashMap<String, JSONObject> paramConcurrentHashMap)
  {
    try
    {
      paramArrayOfByte = Base64.encodeToString(paramArrayOfByte, 2);
    }
    catch (Exception|AssertionError paramArrayOfByte)
    {
      for (;;)
      {
        try
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("rec", paramArrayOfByte);
          localJSONObject.put("rssi", paramInt);
          if (paramBluetoothDevice != null)
          {
            String str = paramBluetoothDevice.getAddress();
            if (str != null) {
              localJSONObject.put("addr", str);
            }
            paramBluetoothDevice = paramBluetoothDevice.getName();
            if (paramBluetoothDevice != null) {
              localJSONObject.put("name", paramBluetoothDevice);
            }
          }
          if ((localJSONObject != null) && (localJSONObject.length() > 0)) {
            paramConcurrentHashMap.put(paramArrayOfByte, localJSONObject);
          }
          return;
        }
        catch (Exception paramBluetoothDevice) {}
        paramArrayOfByte = paramArrayOfByte;
      }
    }
    paramArrayOfByte = null;
    if ((paramArrayOfByte != null) && (!paramConcurrentHashMap.containsKey(paramArrayOfByte))) {}
  }
  
  private static void b(ConcurrentHashMap<String, JSONObject> paramConcurrentHashMap, j.c paramC)
  {
    if (paramConcurrentHashMap != null) {}
    try
    {
      if (paramConcurrentHashMap.size() > 0)
      {
        JSONArray localJSONArray = new JSONArray();
        Iterator localIterator = paramConcurrentHashMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          JSONObject localJSONObject = (JSONObject)paramConcurrentHashMap.get((String)localIterator.next());
          if (localJSONObject != null) {
            localJSONArray.put(localJSONObject);
          }
        }
        paramConcurrentHashMap.clear();
        if ((paramC != null) && (localJSONArray != null)) {
          paramC.a(localJSONArray);
        }
      }
      return;
    }
    catch (Exception paramConcurrentHashMap) {}
  }
  
  public static boolean b(Activity paramActivity)
  {
    boolean bool2 = false;
    for (;;)
    {
      int i;
      try
      {
        ViewGroup localViewGroup = (ViewGroup)paramActivity.getWindow().getDecorView();
        Object localObject = null;
        paramActivity = localObject;
        if (localViewGroup != null)
        {
          i = 0;
          paramActivity = localObject;
          if (i < localViewGroup.getChildCount())
          {
            if (!(localViewGroup.getChildAt(i) instanceof ViewGroup)) {
              break label87;
            }
            paramActivity = localViewGroup.getChildAt(i);
          }
        }
        boolean bool1 = bool2;
        if (paramActivity != null)
        {
          boolean bool3 = paramActivity.isHardwareAccelerated();
          bool1 = bool2;
          if (bool3) {
            bool1 = true;
          }
        }
        return bool1;
      }
      catch (NoSuchMethodError|ClassCastException|Exception paramActivity)
      {
        return false;
      }
      label87:
      i += 1;
    }
  }
  
  public static String c()
  {
    try
    {
      String str = System.getProperty("http.agent");
      return str;
    }
    catch (Exception|NoSuchMethodError localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String c(Activity paramActivity)
  {
    try
    {
      paramActivity = ((TelephonyManager)paramActivity.getSystemService("phone")).getNetworkOperator();
      if ((paramActivity != null) && (paramActivity.length() >= 3))
      {
        paramActivity = paramActivity.substring(0, 3);
        return paramActivity;
      }
      return null;
    }
    catch (Exception paramActivity) {}
    return null;
  }
  
  public static String d()
  {
    try
    {
      String str = Build.BRAND;
      if (str != null)
      {
        int i = str.length();
        if (i > 0) {
          return str;
        }
      }
    }
    catch (Exception|NoSuchFieldError localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String d(Activity paramActivity)
  {
    try
    {
      paramActivity = ((TelephonyManager)paramActivity.getSystemService("phone")).getNetworkOperator();
      if ((paramActivity != null) && (paramActivity.length() >= 3))
      {
        paramActivity = paramActivity.substring(3);
        return paramActivity;
      }
      return null;
    }
    catch (Exception paramActivity) {}
    return null;
  }
  
  public static String e()
  {
    try
    {
      String str = Build.BOARD;
      if (str != null)
      {
        int i = str.length();
        if (i > 0) {
          return str;
        }
      }
    }
    catch (Exception|NoSuchFieldError localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String e(Activity paramActivity)
  {
    try
    {
      if (((TelephonyManager)paramActivity.getSystemService("phone")).isNetworkRoaming()) {
        return "true";
      }
      return "false";
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String f()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return String.valueOf(i);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "unknown";
  }
  
  public static String f(Activity paramActivity)
  {
    try
    {
      if (Settings.Secure.getInt(paramActivity.getContentResolver(), "accessibility_enabled") == 1) {
        return "true";
      }
      return "false";
    }
    catch (Settings.SettingNotFoundException|Exception paramActivity) {}
    return null;
  }
  
  public static String g()
  {
    try
    {
      String str = Build.MANUFACTURER;
      if (str != null) {
        return str;
      }
      return "unknown";
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "unknown";
  }
  
  public static String g(Activity paramActivity)
  {
    for (;;)
    {
      try
      {
        if (Settings.Global.getInt(paramActivity.getContentResolver(), "adb_enabled") == 1) {
          return "true";
        }
        return "false";
      }
      catch (Exception|NoClassDefFoundError localException)
      {
        continue;
      }
      try
      {
        if (Settings.Secure.getInt(paramActivity.getContentResolver(), "adb_enabled") == 1) {
          return "true";
        }
        return "false";
      }
      catch (Settings.SettingNotFoundException paramActivity) {}
    }
    return null;
  }
  
  public static String h()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Build.MODEL);
      ((StringBuilder)localObject).append(" (");
      ((StringBuilder)localObject).append(Build.PRODUCT);
      ((StringBuilder)localObject).append(")");
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "unknown";
  }
  
  public static String h(Activity paramActivity)
  {
    for (;;)
    {
      try
      {
        if (Settings.Global.getInt(paramActivity.getContentResolver(), "install_non_market_apps") == 1) {
          return "true";
        }
        return "false";
      }
      catch (Exception|NoClassDefFoundError localException)
      {
        continue;
      }
      try
      {
        if (Settings.Secure.getInt(paramActivity.getContentResolver(), "install_non_market_apps") == 1) {
          return "true";
        }
        return "false";
      }
      catch (Settings.SettingNotFoundException paramActivity) {}
    }
    return null;
  }
  
  public static String i(Activity paramActivity)
  {
    try
    {
      if (((NfcManager)paramActivity.getSystemService("nfc")).getDefaultAdapter() != null) {
        return "true";
      }
      return "false";
    }
    catch (NoSuchMethodError paramActivity)
    {
      for (;;) {}
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    catch (NoClassDefFoundError paramActivity)
    {
      for (;;) {}
    }
    return "false";
    return "false";
    return "false";
  }
  
  public static String j(Activity paramActivity)
  {
    try
    {
      paramActivity = ((NfcManager)paramActivity.getSystemService("nfc")).getDefaultAdapter();
      if ((paramActivity != null) && (paramActivity.isEnabled())) {
        return "true";
      }
      return "false";
    }
    catch (NoSuchMethodError paramActivity)
    {
      for (;;) {}
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    catch (NoClassDefFoundError paramActivity)
    {
      for (;;) {}
    }
    return "false";
    return "false";
    return "false";
  }
  
  public static String k(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getApplicationContext().getPackageName();
      return paramActivity;
    }
    catch (Exception|NoClassDefFoundError paramActivity)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static void l(Activity paramActivity)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)paramActivity.getSystemService("input_method");
    View localView2 = paramActivity.getCurrentFocus();
    View localView1 = localView2;
    if (localView2 == null) {
      localView1 = new View(paramActivity);
    }
    localInputMethodManager.hideSoftInputFromWindow(localView1.getWindowToken(), 0);
  }
  
  public static JSONArray m(Activity paramActivity)
  {
    try
    {
      Object localObject = ((ActivityManager)paramActivity.getSystemService("activity")).getRunningAppProcesses();
      if ((localObject != null) && (((List)localObject).size() > 1))
      {
        paramActivity = new HashSet();
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
          if ((!localRunningAppProcessInfo.processName.startsWith("com.google")) && (!localRunningAppProcessInfo.processName.startsWith("com.android"))) {
            paramActivity.add(localRunningAppProcessInfo.processName);
          }
        }
        if (paramActivity.size() > 0)
        {
          paramActivity = new JSONArray(paramActivity);
          return paramActivity;
        }
      }
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static JSONArray n(Activity paramActivity)
  {
    try
    {
      PackageManager localPackageManager = paramActivity.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      Object localObject1 = null;
      while (localIterator.hasNext())
      {
        Object localObject2 = (ApplicationInfo)localIterator.next();
        if ((((ApplicationInfo)localObject2).flags & 0x1) != 1)
        {
          String str = ((ApplicationInfo)localObject2).packageName;
          if (str != null)
          {
            paramActivity = (Activity)localObject1;
            if (localObject1 == null) {
              paramActivity = new JSONArray();
            }
            localObject1 = new JSONObject();
            localObject2 = localPackageManager.getPackageInfo(((ApplicationInfo)localObject2).packageName, 0);
            ((JSONObject)localObject1).put("pn", str);
            if (Build.VERSION.SDK_INT >= 9)
            {
              long l1 = ((PackageInfo)localObject2).firstInstallTime;
              long l2 = ((PackageInfo)localObject2).lastUpdateTime;
              ((JSONObject)localObject1).put("fi", l1);
              ((JSONObject)localObject1).put("lm", l2);
            }
            paramActivity.put(localObject1);
            localObject1 = paramActivity;
          }
        }
      }
      if (localObject1 != null) {
        return localObject1;
      }
      return null;
    }
    catch (Exception|NoClassDefFoundError paramActivity) {}
    return null;
  }
  
  public static JSONArray o(Activity paramActivity)
  {
    try
    {
      if (paramActivity.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", paramActivity.getPackageName()) == 0)
      {
        paramActivity = ((WifiManager)paramActivity.getSystemService("wifi")).getScanResults();
        if ((paramActivity != null) && (paramActivity.size() > 0))
        {
          Iterator localIterator = paramActivity.iterator();
          for (Object localObject = null; localIterator.hasNext(); localObject = paramActivity)
          {
            android.net.wifi.ScanResult localScanResult = (android.net.wifi.ScanResult)localIterator.next();
            paramActivity = (Activity)localObject;
            if (localObject == null) {
              paramActivity = new JSONArray();
            }
            localObject = new JSONObject();
            ((JSONObject)localObject).put("bssid", localScanResult.BSSID);
            ((JSONObject)localObject).put("ssid", localScanResult.SSID);
            ((JSONObject)localObject).put("capab", localScanResult.capabilities);
            ((JSONObject)localObject).put("freq", localScanResult.frequency);
            ((JSONObject)localObject).put("level", localScanResult.level);
            if (Build.VERSION.SDK_INT >= 17) {
              ((JSONObject)localObject).put("time", localScanResult.timestamp);
            }
            paramActivity.put(localObject);
          }
          if (localObject != null) {
            return localObject;
          }
        }
      }
      return null;
    }
    catch (Exception|NoClassDefFoundError|NoSuchFieldError paramActivity) {}
    return null;
  }
  
  public static String p(Activity paramActivity)
  {
    if (paramActivity.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", paramActivity.getPackageName()) == 0) {}
    try
    {
      String str = ((WifiManager)paramActivity.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      paramActivity = str;
      if (str == null) {
        paramActivity = "noMac";
      }
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    return "noMac";
    return "noMac";
  }
  
  public static String q(Activity paramActivity)
  {
    if (paramActivity.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", paramActivity.getPackageName()) == 0) {}
    for (;;)
    {
      String str;
      Object localObject;
      try
      {
        str = ((WifiManager)paramActivity.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if (str != null)
        {
          localObject = str;
          if (str == null) {
            continue;
          }
          localObject = str;
        }
      }
      catch (Exception paramActivity)
      {
        continue;
      }
      try
      {
        if (str.equalsIgnoreCase("02:00:00:00:00:00"))
        {
          paramActivity = Settings.Secure.getString(paramActivity.getContentResolver(), "bluetooth_address");
          localObject = str;
          if (paramActivity != null) {
            localObject = paramActivity;
          }
        }
      }
      catch (Exception|NoSuchMethodError paramActivity)
      {
        localObject = str;
      }
    }
    if (localObject == null) {
      return "noMac";
    }
    return localObject;
    return "noMac";
    return "noMac";
  }
  
  public static int r(Activity paramActivity)
  {
    int j = -1;
    try
    {
      PackageManager localPackageManager = paramActivity.getPackageManager();
      int i = j;
      if (localPackageManager != null)
      {
        paramActivity = localPackageManager.getApplicationInfo(paramActivity.getPackageName(), 0);
        i = j;
        if (paramActivity != null) {
          i = paramActivity.targetSdkVersion;
        }
      }
      return i;
    }
    catch (Exception paramActivity) {}
    return -1;
  }
  
  public static String s(Activity paramActivity)
  {
    try
    {
      paramActivity = ((TelephonyManager)paramActivity.getSystemService("phone")).getNetworkOperatorName();
      if (paramActivity == null) {
        return null;
      }
      return paramActivity;
    }
    catch (Exception paramActivity) {}
    return null;
  }
  
  public static String t(Activity paramActivity)
  {
    if (paramActivity != null) {}
    try
    {
      if (Build.VERSION.SDK_INT >= 24) {}
      for (paramActivity = paramActivity.getResources().getConfiguration().getLocales().get(0);; paramActivity = paramActivity.getResources().getConfiguration().locale)
      {
        paramActivity = paramActivity.toLanguageTag();
        break label79;
        if (Build.VERSION.SDK_INT < 21) {
          break;
        }
      }
      paramActivity = Locale.getDefault().getLanguage();
    }
    catch (NoSuchMethodError paramActivity)
    {
      for (;;) {}
    }
    paramActivity = Locale.getDefault().getLanguage();
    break label79;
    paramActivity = null;
    label79:
    if (paramActivity != null)
    {
      if (paramActivity.equalsIgnoreCase("")) {
        return null;
      }
      return paramActivity;
    }
    return null;
  }
  
  public static JSONArray u(Activity paramActivity)
  {
    try
    {
      localJSONArray = new JSONArray();
    }
    catch (NoSuchMethodError paramActivity)
    {
      label81:
      label124:
      for (;;) {}
    }
    localObject = null;
    break label124;
    localObject = localJSONArray;
    if (paramActivity != null) {}
    try
    {
      if (Build.VERSION.SDK_INT < 24) {
        break label81;
      }
      paramActivity = paramActivity.getResources().getConfiguration().getLocales();
      localObject = localJSONArray;
      if (paramActivity == null) {
        break label124;
      }
      i = 0;
    }
    catch (NoSuchMethodError paramActivity)
    {
      for (;;)
      {
        int i;
        localObject = localJSONArray;
        continue;
        i += 1;
      }
    }
    localObject = localJSONArray;
    if (i < paramActivity.size())
    {
      localObject = paramActivity.get(i);
      if (localObject != null)
      {
        localJSONArray.put(((Locale)localObject).toLanguageTag());
        break label151;
        if (Build.VERSION.SDK_INT >= 21) {}
        for (paramActivity = paramActivity.getResources().getConfiguration().locale.toLanguageTag();; paramActivity = Locale.getDefault().getLanguage())
        {
          localJSONArray.put(paramActivity);
          localObject = localJSONArray;
          break;
        }
      }
    }
    else
    {
      if (localObject != null)
      {
        if (((JSONArray)localObject).length() == 0) {
          return null;
        }
        return localObject;
      }
      return null;
    }
  }
  
  public static String v(Activity paramActivity)
  {
    try
    {
      Object localObject = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0);
      paramActivity = ((PackageInfo)localObject).versionName;
      int i = ((PackageInfo)localObject).versionCode;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramActivity);
      ((StringBuilder)localObject).append(i);
      paramActivity = ((StringBuilder)localObject).toString();
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String w(Activity paramActivity)
  {
    try
    {
      if (paramActivity.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramActivity.getPackageName()) == 0)
      {
        paramActivity = ((TelephonyManager)paramActivity.getSystemService("phone")).getDeviceId();
        return paramActivity;
      }
      return null;
    }
    catch (Exception paramActivity) {}
    return null;
  }
  
  public static String x(Activity paramActivity)
  {
    for (;;)
    {
      int i;
      try
      {
        if (paramActivity.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramActivity.getPackageName()) == 0)
        {
          paramActivity = (ConnectivityManager)paramActivity.getSystemService("connectivity");
          int k = Build.VERSION.SDK_INT;
          int j = 0;
          i = 0;
          Network[] arrayOfNetwork;
          if (k >= 21)
          {
            arrayOfNetwork = paramActivity.getAllNetworks();
            j = arrayOfNetwork.length;
            if (i < j)
            {
              NetworkInfo localNetworkInfo = paramActivity.getNetworkInfo(arrayOfNetwork[i]);
              if (!localNetworkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                break label135;
              }
              return localNetworkInfo.getTypeName();
            }
          }
          else
          {
            paramActivity = paramActivity.getAllNetworkInfo();
            k = paramActivity.length;
            i = j;
            if (i < k)
            {
              arrayOfNetwork = paramActivity[i];
              if (arrayOfNetwork.isConnected())
              {
                paramActivity = arrayOfNetwork.getTypeName();
                return paramActivity;
              }
              i += 1;
              continue;
            }
          }
        }
        return null;
      }
      catch (Exception paramActivity)
      {
        return null;
      }
      label135:
      i += 1;
    }
  }
  
  public static String y(Activity paramActivity)
  {
    try
    {
      if (Build.VERSION.SDK_INT > 8)
      {
        paramActivity = Build.class.getField("SERIAL").get(null).toString();
        if (paramActivity != null)
        {
          int i = paramActivity.length();
          if (i > 0) {
            return paramActivity;
          }
        }
      }
      return null;
    }
    catch (Exception|NoSuchFieldError paramActivity) {}
    return null;
  }
  
  public static String z(Activity paramActivity)
  {
    try
    {
      paramActivity = Settings.Secure.getString(paramActivity.getApplicationContext().getContentResolver(), "android_id");
      if (paramActivity != null)
      {
        int i = paramActivity.length();
        if (i > 0) {
          return paramActivity;
        }
      }
    }
    catch (Exception|NoSuchFieldError paramActivity)
    {
      for (;;) {}
    }
    return null;
  }
}
