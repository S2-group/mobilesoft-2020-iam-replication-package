package com.pollfish.util;

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
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
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
import com.pollfish.classes.c;
import com.pollfish.constants.Position;
import com.pollfish.interfaces.a.b;
import com.pollfish.interfaces.a.c;
import com.pollfish.interfaces.a.d;
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
  
  public static String A(Activity paramActivity)
  {
    int m;
    int i;
    int j;
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
      paramActivity.getMetrics(localDisplayMetrics);
      Point localPoint = new Point();
      int k = localDisplayMetrics.widthPixels;
      m = localDisplayMetrics.heightPixels;
      i = k;
      j = k;
      try
      {
        paramActivity.getSize(localPoint);
        i = k;
        j = k;
        k = localPoint.x;
        i = k;
        j = k;
        int n = localPoint.y;
        j = k;
        i = n;
      }
      catch (NoSuchMethodError paramActivity)
      {
        for (;;)
        {
          double d2;
          double d1;
          j = i;
          i = m;
        }
      }
      catch (Exception paramActivity)
      {
        for (;;)
        {
          i = m;
        }
      }
      k = localDisplayMetrics.densityDpi;
      d2 = j / k;
      d1 = i / k;
      d2 = Math.pow(d2, 2.0D);
      d1 = Math.sqrt(Math.pow(d1, 2.0D) + d2);
      return String.valueOf(d1);
    }
    catch (Exception paramActivity) {}
    return "unknown";
  }
  
  public static float a(float paramFloat, Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi / 160.0F * paramFloat;
  }
  
  public static int a(int paramInt, Context paramContext)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramInt + 0.5F);
  }
  
  public static int a(Activity paramActivity, String paramString)
  {
    return paramActivity.getBaseContext().getSharedPreferences("pollfish_pref", 0).getInt("pollfish_pref" + paramString, 0);
  }
  
  public static int a(Bitmap paramBitmap, String paramString)
  {
    String str = "";
    try
    {
      paramBitmap = paramBitmap.getWidth() + paramBitmap.getHeight() + paramString + (paramBitmap.getPixel(0, 0) + (paramBitmap.getPixel(paramBitmap.getWidth() / 2 - 1, paramBitmap.getHeight() / 3 - 1) + paramBitmap.getPixel(paramBitmap.getWidth() - 1, paramBitmap.getHeight() - 1)));
      return paramBitmap.hashCode();
    }
    catch (Exception paramBitmap)
    {
      for (;;)
      {
        paramBitmap = str;
      }
    }
  }
  
  public static Application.ActivityLifecycleCallbacks a(Activity paramActivity, final BluetoothAdapter.LeScanCallback paramLeScanCallback, final ScanCallback paramScanCallback, BluetoothAdapter paramBluetoothAdapter)
  {
    paramActivity = (Application)paramActivity.getApplicationContext();
    if ((paramActivity != null) && ((paramLeScanCallback != null) || (paramScanCallback != null)) && (paramBluetoothAdapter != null))
    {
      paramLeScanCallback = new Application.ActivityLifecycleCallbacks()
      {
        public final void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
        
        public final void onActivityDestroyed(Activity paramAnonymousActivity) {}
        
        public final void onActivityPaused(Activity paramAnonymousActivity)
        {
          paramAnonymousActivity = (Application)paramAnonymousActivity.getApplicationContext();
          if (paramAnonymousActivity != null)
          {
            paramAnonymousActivity.unregisterActivityLifecycleCallbacks(this);
            if (Build.VERSION.SDK_INT >= 21) {
              break label51;
            }
            if ((b.this != null) && (paramLeScanCallback != null)) {
              b.this.stopLeScan(paramLeScanCallback);
            }
          }
          label51:
          while ((b.this == null) || (paramScanCallback == null)) {
            return;
          }
          b.this.getBluetoothLeScanner().stopScan(paramScanCallback);
        }
        
        public final void onActivityResumed(Activity paramAnonymousActivity) {}
        
        public final void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
        
        public final void onActivityStarted(Activity paramAnonymousActivity) {}
        
        public final void onActivityStopped(Activity paramAnonymousActivity) {}
      };
      paramActivity.registerActivityLifecycleCallbacks(paramLeScanCallback);
      return paramLeScanCallback;
    }
    return null;
  }
  
  public static Animation a(View paramView, Position paramPosition, int paramInt, long paramLong)
  {
    if (paramView != null)
    {
      if (paramPosition == Position.TOP_LEFT) {
        paramPosition = new TranslateAnimation(-(paramView.getWidth() * 2), 0.0F, 0.0F, 0.0F);
      }
      for (;;)
      {
        paramPosition.setDuration(paramLong);
        paramPosition.setStartOffset(paramInt);
        paramView.startAnimation(paramPosition);
        return paramPosition;
        if (paramPosition == Position.BOTTOM_LEFT) {
          paramPosition = new TranslateAnimation(-(paramView.getWidth() * 2), 0.0F, 0.0F, 0.0F);
        } else if (paramPosition == Position.TOP_RIGHT) {
          paramPosition = new TranslateAnimation(paramView.getWidth() * 2, 0.0F, 0.0F, 0.0F);
        } else if (paramPosition == Position.BOTTOM_RIGHT) {
          paramPosition = new TranslateAnimation(paramView.getWidth() * 2, 0.0F, 0.0F, 0.0F);
        } else if (paramPosition == Position.MIDDLE_RIGHT) {
          paramPosition = new TranslateAnimation(paramView.getWidth() * 2, 0.0F, 0.0F, 0.0F);
        } else if (paramPosition == Position.MIDDLE_LEFT) {
          paramPosition = new TranslateAnimation(-(paramView.getWidth() * 2), 0.0F, 0.0F, 0.0F);
        } else {
          paramPosition = new TranslateAnimation(-(paramView.getWidth() * 2), 0.0F, 0.0F, 0.0F);
        }
      }
    }
    return null;
  }
  
  public static com.pollfish.classes.b a(String paramString, c paramC, a.d paramD, a.b paramB, Activity paramActivity, boolean paramBoolean)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      localObject = new JSONObject(paramString);
      str1 = ((JSONObject)localObject).getString("response_type");
      k = ((JSONObject)localObject).getInt("intrusion");
      m = ((JSONObject)localObject).getInt("width_percentage");
      n = ((JSONObject)localObject).getInt("height_percentage");
      str2 = ((JSONObject)localObject).getString("content");
      str3 = ((JSONObject)localObject).getString("s_id");
      bool4 = ((JSONObject)localObject).getBoolean("custom_indicator");
      str4 = ((JSONObject)localObject).getString("indicator_image_left");
      str5 = ((JSONObject)localObject).getString("indicator_image_right");
      str6 = ((JSONObject)localObject).getString("mobile_data");
      str7 = ((JSONObject)localObject).getString("assets");
      str8 = ((JSONObject)localObject).getString("background_color");
      i = 0;
      bool1 = false;
    }
    catch (Exception paramString)
    {
      Object localObject;
      String str1;
      int k;
      int m;
      int n;
      String str2;
      String str3;
      boolean bool4;
      String str4;
      String str5;
      String str6;
      String str7;
      String str8;
      int i;
      boolean bool1;
      boolean bool2;
      int j;
      label166:
      return null;
    }
    try
    {
      bool2 = ((JSONObject)localObject).getBoolean("short_survey");
      bool1 = bool2;
      j = ((JSONObject)localObject).getInt("survey_price");
      i = j;
      bool1 = bool2;
    }
    catch (Exception paramString)
    {
      break label166;
    }
    bool2 = false;
    try
    {
      boolean bool3 = ((JSONObject)localObject).getBoolean("video_enabled");
      bool2 = bool3;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    paramString = "#ff000000";
    try
    {
      localObject = ((JSONObject)localObject).getString("video_color");
      paramString = (String)localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    paramString = new com.pollfish.classes.b(str3, str1, k, str2, bool4, str4, str5, m, n, paramC.z(), paramD, str6, str7, paramB, paramActivity, str8, paramBoolean, bool1, i, paramString, bool2);
    return paramString;
  }
  
  public static String a()
  {
    try
    {
      String str = a(Runtime.getRuntime().exec("cat /proc/cpuinfo").getInputStream());
      return str;
    }
    catch (Exception localException)
    {
      return null;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  private static String a(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 130	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 151	java/lang/StringBuilder:<init>	()V
    //   7: astore_1
    //   8: new 309	java/io/BufferedReader
    //   11: dup
    //   12: new 311	java/io/InputStreamReader
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 314	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   20: invokespecial 317	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   23: astore_0
    //   24: aload_0
    //   25: invokevirtual 320	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_2
    //   29: aload_2
    //   30: ifnull +30 -> 60
    //   33: aload_1
    //   34: aload_2
    //   35: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload_1
    //   40: ldc_w 322
    //   43: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: goto -23 -> 24
    //   50: astore_2
    //   51: aload_0
    //   52: invokevirtual 325	java/io/BufferedReader:close	()V
    //   55: aload_1
    //   56: invokevirtual 141	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   59: areturn
    //   60: aload_0
    //   61: invokevirtual 325	java/io/BufferedReader:close	()V
    //   64: goto -9 -> 55
    //   67: astore_0
    //   68: goto -13 -> 55
    //   71: astore_1
    //   72: aload_0
    //   73: invokevirtual 325	java/io/BufferedReader:close	()V
    //   76: aload_1
    //   77: athrow
    //   78: astore_0
    //   79: goto -24 -> 55
    //   82: astore_0
    //   83: goto -7 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	paramInputStream	java.io.InputStream
    //   7	49	1	localStringBuilder	StringBuilder
    //   71	6	1	localObject	Object
    //   28	7	2	str	String
    //   50	1	2	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   24	29	50	java/io/IOException
    //   33	47	50	java/io/IOException
    //   60	64	67	java/io/IOException
    //   24	29	71	finally
    //   33	47	71	finally
    //   51	55	78	java/io/IOException
    //   72	76	82	java/io/IOException
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
      throw new SignatureException("error building signature, no such algorithm in device HmacSHA256");
    }
    catch (InvalidKeyException paramString1)
    {
      throw new SignatureException("error building signature, invalid key HmacSHA256");
    }
  }
  
  /* Error */
  public static String a(java.net.HttpURLConnection paramHttpURLConnection)
  {
    // Byte code:
    //   0: new 373	java/lang/StringBuffer
    //   3: dup
    //   4: invokespecial 374	java/lang/StringBuffer:<init>	()V
    //   7: astore_1
    //   8: new 376	java/io/BufferedInputStream
    //   11: dup
    //   12: aload_0
    //   13: invokevirtual 379	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   16: invokespecial 380	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   19: astore_0
    //   20: new 309	java/io/BufferedReader
    //   23: dup
    //   24: new 311	java/io/InputStreamReader
    //   27: dup
    //   28: aload_0
    //   29: invokespecial 314	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   32: invokespecial 317	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   35: astore_2
    //   36: aload_2
    //   37: invokevirtual 320	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   40: astore_3
    //   41: aload_3
    //   42: ifnull +23 -> 65
    //   45: aload_1
    //   46: aload_3
    //   47: invokevirtual 383	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   50: pop
    //   51: goto -15 -> 36
    //   54: astore_1
    //   55: aload_0
    //   56: ifnull +7 -> 63
    //   59: aload_0
    //   60: invokevirtual 386	java/io/InputStream:close	()V
    //   63: aconst_null
    //   64: areturn
    //   65: aload_1
    //   66: invokevirtual 387	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   69: astore_1
    //   70: aload_0
    //   71: invokevirtual 386	java/io/InputStream:close	()V
    //   74: aload_1
    //   75: areturn
    //   76: astore_0
    //   77: aload_1
    //   78: areturn
    //   79: astore_1
    //   80: aconst_null
    //   81: astore_0
    //   82: aload_0
    //   83: ifnull +7 -> 90
    //   86: aload_0
    //   87: invokevirtual 386	java/io/InputStream:close	()V
    //   90: aload_1
    //   91: athrow
    //   92: astore_0
    //   93: aconst_null
    //   94: areturn
    //   95: astore_0
    //   96: goto -6 -> 90
    //   99: astore_1
    //   100: goto -18 -> 82
    //   103: astore_0
    //   104: aconst_null
    //   105: astore_0
    //   106: goto -51 -> 55
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	paramHttpURLConnection	java.net.HttpURLConnection
    //   7	39	1	localStringBuffer	StringBuffer
    //   54	12	1	localException	Exception
    //   69	9	1	str1	String
    //   79	12	1	localObject1	Object
    //   99	1	1	localObject2	Object
    //   35	2	2	localBufferedReader	java.io.BufferedReader
    //   40	7	3	str2	String
    // Exception table:
    //   from	to	target	type
    //   20	36	54	java/lang/Exception
    //   36	41	54	java/lang/Exception
    //   45	51	54	java/lang/Exception
    //   65	70	54	java/lang/Exception
    //   70	74	76	java/io/IOException
    //   8	20	79	finally
    //   59	63	92	java/io/IOException
    //   86	90	95	java/io/IOException
    //   20	36	99	finally
    //   36	41	99	finally
    //   45	51	99	finally
    //   65	70	99	finally
    //   8	20	103	java/lang/Exception
  }
  
  public static String a(List paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = 0;
    int i = 1;
    if (j < paramList.size())
    {
      Pair localPair = (Pair)paramList.get(j);
      if (i != 0) {
        i = 0;
      }
      for (;;)
      {
        localStringBuilder.append(URLEncoder.encode((String)localPair.first, "UTF-8"));
        localStringBuilder.append("=");
        localStringBuilder.append(URLEncoder.encode((String)localPair.second, "UTF-8"));
        j += 1;
        break;
        localStringBuilder.append("&");
      }
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
  public static JSONObject a(Activity paramActivity, final a.c paramC, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: aload_0
    //   4: invokevirtual 443	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: astore 6
    //   9: aload 6
    //   11: ldc_w 445
    //   14: aload_0
    //   15: invokevirtual 448	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   18: invokevirtual 454	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   21: ifne +721 -> 742
    //   24: aload 6
    //   26: ldc_w 456
    //   29: aload_0
    //   30: invokevirtual 448	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   33: invokevirtual 454	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   36: istore_3
    //   37: iload_3
    //   38: ifne +704 -> 742
    //   41: getstatic 461	android/os/Build$VERSION:SDK_INT	I
    //   44: bipush 18
    //   46: if_icmplt +400 -> 446
    //   49: aload_0
    //   50: ldc_w 463
    //   53: invokevirtual 467	android/app/Activity:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   56: checkcast 469	android/bluetooth/BluetoothManager
    //   59: invokevirtual 473	android/bluetooth/BluetoothManager:getAdapter	()Landroid/bluetooth/BluetoothAdapter;
    //   62: astore 9
    //   64: aload 9
    //   66: ifnull +667 -> 733
    //   69: aload 9
    //   71: invokevirtual 479	android/bluetooth/BluetoothAdapter:isEnabled	()Z
    //   74: istore 4
    //   76: aload 6
    //   78: ldc_w 481
    //   81: invokevirtual 484	android/content/pm/PackageManager:hasSystemFeature	(Ljava/lang/String;)Z
    //   84: istore 5
    //   86: iload 4
    //   88: ifeq +124 -> 212
    //   91: getstatic 461	android/os/Build$VERSION:SDK_INT	I
    //   94: bipush 18
    //   96: if_icmplt +116 -> 212
    //   99: iload 5
    //   101: ifeq +111 -> 212
    //   104: iload_2
    //   105: ifeq +107 -> 212
    //   108: new 486	java/util/concurrent/ConcurrentHashMap
    //   111: dup
    //   112: invokespecial 487	java/util/concurrent/ConcurrentHashMap:<init>	()V
    //   115: astore 10
    //   117: getstatic 461	android/os/Build$VERSION:SDK_INT	I
    //   120: bipush 21
    //   122: if_icmpge +343 -> 465
    //   125: new 8	com/pollfish/util/b$2
    //   128: dup
    //   129: aload 10
    //   131: invokespecial 490	com/pollfish/util/b$2:<init>	(Ljava/util/concurrent/ConcurrentHashMap;)V
    //   134: astore 8
    //   136: aload 9
    //   138: ifnull +586 -> 724
    //   141: aload 9
    //   143: aload 8
    //   145: invokevirtual 494	android/bluetooth/BluetoothAdapter:startLeScan	(Landroid/bluetooth/BluetoothAdapter$LeScanCallback;)Z
    //   148: pop
    //   149: getstatic 461	android/os/Build$VERSION:SDK_INT	I
    //   152: istore_3
    //   153: iload_3
    //   154: bipush 14
    //   156: if_icmplt +568 -> 724
    //   159: aload_0
    //   160: aload 8
    //   162: aconst_null
    //   163: aload 9
    //   165: invokestatic 496	com/pollfish/util/b:a	(Landroid/app/Activity;Landroid/bluetooth/BluetoothAdapter$LeScanCallback;Landroid/bluetooth/le/ScanCallback;Landroid/bluetooth/BluetoothAdapter;)Landroid/app/Application$ActivityLifecycleCallbacks;
    //   168: astore 6
    //   170: aconst_null
    //   171: astore 7
    //   173: new 498	android/os/Handler
    //   176: dup
    //   177: invokestatic 504	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   180: invokespecial 507	android/os/Handler:<init>	(Landroid/os/Looper;)V
    //   183: new 12	com/pollfish/util/b$4
    //   186: dup
    //   187: aload 10
    //   189: aload_1
    //   190: invokespecial 510	com/pollfish/util/b$4:<init>	(Ljava/util/concurrent/ConcurrentHashMap;Lcom/pollfish/interfaces/a$c;)V
    //   193: aload_0
    //   194: aload 9
    //   196: aload 8
    //   198: aload 7
    //   200: aload 6
    //   202: invokevirtual 513	com/pollfish/util/b$4:a	(Landroid/app/Activity;Landroid/bluetooth/BluetoothAdapter;Landroid/bluetooth/BluetoothAdapter$LeScanCallback;Landroid/bluetooth/le/ScanCallback;Landroid/app/Application$ActivityLifecycleCallbacks;)Ljava/lang/Runnable;
    //   205: ldc2_w 514
    //   208: invokevirtual 519	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   211: pop
    //   212: iload 4
    //   214: istore_2
    //   215: iload 5
    //   217: istore 4
    //   219: iload 4
    //   221: istore 5
    //   223: iload_2
    //   224: istore 4
    //   226: iload 5
    //   228: istore_2
    //   229: aload 9
    //   231: astore_0
    //   232: new 227	org/json/JSONObject
    //   235: dup
    //   236: invokespecial 520	org/json/JSONObject:<init>	()V
    //   239: astore 6
    //   241: aload 6
    //   243: ldc_w 522
    //   246: iload 4
    //   248: invokevirtual 526	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   251: pop
    //   252: aload 6
    //   254: astore_1
    //   255: aload_0
    //   256: ifnull +184 -> 440
    //   259: aload_0
    //   260: invokevirtual 529	android/bluetooth/BluetoothAdapter:getAddress	()Ljava/lang/String;
    //   263: astore_1
    //   264: aload_1
    //   265: ifnull +13 -> 278
    //   268: aload 6
    //   270: ldc_w 531
    //   273: aload_1
    //   274: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   277: pop
    //   278: aload_0
    //   279: invokevirtual 537	android/bluetooth/BluetoothAdapter:getName	()Ljava/lang/String;
    //   282: astore_1
    //   283: aload_1
    //   284: ifnull +13 -> 297
    //   287: aload 6
    //   289: ldc_w 539
    //   292: aload_1
    //   293: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   296: pop
    //   297: aload_0
    //   298: invokevirtual 543	android/bluetooth/BluetoothAdapter:getBondedDevices	()Ljava/util/Set;
    //   301: astore_1
    //   302: aload_1
    //   303: ifnull +22 -> 325
    //   306: aload_1
    //   307: invokeinterface 546 1 0
    //   312: ifle +13 -> 325
    //   315: aload 6
    //   317: ldc_w 548
    //   320: aload_1
    //   321: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   324: pop
    //   325: getstatic 461	android/os/Build$VERSION:SDK_INT	I
    //   328: bipush 21
    //   330: if_icmplt +16 -> 346
    //   333: aload 6
    //   335: ldc_w 550
    //   338: aload_0
    //   339: invokevirtual 553	android/bluetooth/BluetoothAdapter:isOffloadedFilteringSupported	()Z
    //   342: invokevirtual 526	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   345: pop
    //   346: getstatic 461	android/os/Build$VERSION:SDK_INT	I
    //   349: bipush 21
    //   351: if_icmplt +16 -> 367
    //   354: aload 6
    //   356: ldc_w 555
    //   359: aload_0
    //   360: invokevirtual 558	android/bluetooth/BluetoothAdapter:isOffloadedScanBatchingSupported	()Z
    //   363: invokevirtual 526	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   366: pop
    //   367: getstatic 461	android/os/Build$VERSION:SDK_INT	I
    //   370: bipush 21
    //   372: if_icmplt +16 -> 388
    //   375: aload 6
    //   377: ldc_w 560
    //   380: aload_0
    //   381: invokevirtual 563	android/bluetooth/BluetoothAdapter:isMultipleAdvertisementSupported	()Z
    //   384: invokevirtual 526	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   387: pop
    //   388: aload 6
    //   390: ldc_w 565
    //   393: aload_0
    //   394: invokevirtual 568	android/bluetooth/BluetoothAdapter:getState	()I
    //   397: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   400: pop
    //   401: aload 6
    //   403: ldc_w 573
    //   406: aload_0
    //   407: invokevirtual 576	android/bluetooth/BluetoothAdapter:getScanMode	()I
    //   410: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   413: pop
    //   414: aload 6
    //   416: ldc_w 578
    //   419: aload_0
    //   420: invokevirtual 581	android/bluetooth/BluetoothAdapter:isDiscovering	()Z
    //   423: invokevirtual 526	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   426: pop
    //   427: aload 6
    //   429: ldc_w 583
    //   432: iload_2
    //   433: invokevirtual 526	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   436: pop
    //   437: aload 6
    //   439: astore_1
    //   440: aload_1
    //   441: ifnull +123 -> 564
    //   444: aload_1
    //   445: areturn
    //   446: invokestatic 586	android/bluetooth/BluetoothAdapter:getDefaultAdapter	()Landroid/bluetooth/BluetoothAdapter;
    //   449: astore 9
    //   451: goto -387 -> 64
    //   454: astore 6
    //   456: aconst_null
    //   457: astore 6
    //   459: aconst_null
    //   460: astore 7
    //   462: goto -289 -> 173
    //   465: aload 6
    //   467: ldc_w 588
    //   470: aload_0
    //   471: invokevirtual 448	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   474: invokevirtual 454	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   477: ifeq +18 -> 495
    //   480: aload 6
    //   482: ldc_w 590
    //   485: aload_0
    //   486: invokevirtual 448	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   489: invokevirtual 454	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   492: ifne +220 -> 712
    //   495: new 10	com/pollfish/util/b$3
    //   498: dup
    //   499: aload 10
    //   501: invokespecial 591	com/pollfish/util/b$3:<init>	(Ljava/util/concurrent/ConcurrentHashMap;)V
    //   504: astore 7
    //   506: new 593	android/bluetooth/le/ScanSettings$Builder
    //   509: dup
    //   510: invokespecial 594	android/bluetooth/le/ScanSettings$Builder:<init>	()V
    //   513: iconst_0
    //   514: invokevirtual 598	android/bluetooth/le/ScanSettings$Builder:setScanMode	(I)Landroid/bluetooth/le/ScanSettings$Builder;
    //   517: invokevirtual 602	android/bluetooth/le/ScanSettings$Builder:build	()Landroid/bluetooth/le/ScanSettings;
    //   520: astore 6
    //   522: aload 9
    //   524: invokevirtual 606	android/bluetooth/BluetoothAdapter:getBluetoothLeScanner	()Landroid/bluetooth/le/BluetoothLeScanner;
    //   527: aconst_null
    //   528: aload 6
    //   530: aload 7
    //   532: invokevirtual 612	android/bluetooth/le/BluetoothLeScanner:startScan	(Ljava/util/List;Landroid/bluetooth/le/ScanSettings;Landroid/bluetooth/le/ScanCallback;)V
    //   535: aload_0
    //   536: aconst_null
    //   537: aload 7
    //   539: aload 9
    //   541: invokestatic 496	com/pollfish/util/b:a	(Landroid/app/Activity;Landroid/bluetooth/BluetoothAdapter$LeScanCallback;Landroid/bluetooth/le/ScanCallback;Landroid/bluetooth/BluetoothAdapter;)Landroid/app/Application$ActivityLifecycleCallbacks;
    //   544: astore 6
    //   546: aconst_null
    //   547: astore 8
    //   549: goto -376 -> 173
    //   552: astore 6
    //   554: aconst_null
    //   555: astore 6
    //   557: aconst_null
    //   558: astore 8
    //   560: goto -387 -> 173
    //   563: astore_0
    //   564: aconst_null
    //   565: areturn
    //   566: astore_0
    //   567: goto -3 -> 564
    //   570: astore_0
    //   571: goto -7 -> 564
    //   574: astore_1
    //   575: goto -187 -> 388
    //   578: astore_1
    //   579: goto -212 -> 367
    //   582: astore_1
    //   583: goto -237 -> 346
    //   586: astore_0
    //   587: iconst_0
    //   588: istore_2
    //   589: iconst_0
    //   590: istore 4
    //   592: aconst_null
    //   593: astore_0
    //   594: goto -362 -> 232
    //   597: astore_0
    //   598: iconst_0
    //   599: istore_2
    //   600: iconst_0
    //   601: istore 4
    //   603: aload 9
    //   605: astore_0
    //   606: goto -374 -> 232
    //   609: astore_0
    //   610: iconst_0
    //   611: istore_2
    //   612: aload 9
    //   614: astore_0
    //   615: goto -383 -> 232
    //   618: astore_0
    //   619: aload 9
    //   621: astore_0
    //   622: iload 5
    //   624: istore_2
    //   625: goto -393 -> 232
    //   628: astore_0
    //   629: iconst_0
    //   630: istore_2
    //   631: iconst_0
    //   632: istore 4
    //   634: aconst_null
    //   635: astore_0
    //   636: goto -404 -> 232
    //   639: astore_0
    //   640: iconst_0
    //   641: istore_2
    //   642: iconst_0
    //   643: istore 4
    //   645: aload 9
    //   647: astore_0
    //   648: goto -416 -> 232
    //   651: astore_0
    //   652: iconst_0
    //   653: istore_2
    //   654: aload 9
    //   656: astore_0
    //   657: goto -425 -> 232
    //   660: astore_0
    //   661: aload 9
    //   663: astore_0
    //   664: iload 5
    //   666: istore_2
    //   667: goto -435 -> 232
    //   670: astore_0
    //   671: iconst_0
    //   672: istore_2
    //   673: iconst_0
    //   674: istore 4
    //   676: aconst_null
    //   677: astore_0
    //   678: goto -446 -> 232
    //   681: astore_0
    //   682: iconst_0
    //   683: istore_2
    //   684: iconst_0
    //   685: istore 4
    //   687: aload 9
    //   689: astore_0
    //   690: goto -458 -> 232
    //   693: astore_0
    //   694: iconst_0
    //   695: istore_2
    //   696: aload 9
    //   698: astore_0
    //   699: goto -467 -> 232
    //   702: astore_0
    //   703: aload 9
    //   705: astore_0
    //   706: iload 5
    //   708: istore_2
    //   709: goto -477 -> 232
    //   712: aconst_null
    //   713: astore 6
    //   715: aconst_null
    //   716: astore 7
    //   718: aconst_null
    //   719: astore 8
    //   721: goto -548 -> 173
    //   724: aconst_null
    //   725: astore 6
    //   727: aconst_null
    //   728: astore 7
    //   730: goto -557 -> 173
    //   733: iconst_0
    //   734: istore 4
    //   736: iload 5
    //   738: istore_2
    //   739: goto -520 -> 219
    //   742: aconst_null
    //   743: astore_1
    //   744: goto -304 -> 440
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	747	0	paramActivity	Activity
    //   0	747	1	paramC	a.c
    //   0	747	2	paramBoolean	boolean
    //   36	121	3	i	int
    //   74	661	4	bool1	boolean
    //   1	736	5	bool2	boolean
    //   7	431	6	localObject1	Object
    //   454	1	6	localException1	Exception
    //   457	88	6	localObject2	Object
    //   552	1	6	localException2	Exception
    //   555	171	6	localObject3	Object
    //   171	558	7	localObject4	Object
    //   134	586	8	local2	2
    //   62	642	9	localBluetoothAdapter	BluetoothAdapter
    //   115	385	10	localConcurrentHashMap	ConcurrentHashMap
    // Exception table:
    //   from	to	target	type
    //   159	170	454	java/lang/Exception
    //   535	546	552	java/lang/Exception
    //   3	37	563	java/lang/NoClassDefFoundError
    //   232	252	563	java/lang/NoClassDefFoundError
    //   259	264	563	java/lang/NoClassDefFoundError
    //   268	278	563	java/lang/NoClassDefFoundError
    //   278	283	563	java/lang/NoClassDefFoundError
    //   287	297	563	java/lang/NoClassDefFoundError
    //   297	302	563	java/lang/NoClassDefFoundError
    //   306	325	563	java/lang/NoClassDefFoundError
    //   325	346	563	java/lang/NoClassDefFoundError
    //   346	367	563	java/lang/NoClassDefFoundError
    //   367	388	563	java/lang/NoClassDefFoundError
    //   388	437	563	java/lang/NoClassDefFoundError
    //   3	37	566	java/lang/NoSuchMethodError
    //   232	252	566	java/lang/NoSuchMethodError
    //   259	264	566	java/lang/NoSuchMethodError
    //   268	278	566	java/lang/NoSuchMethodError
    //   278	283	566	java/lang/NoSuchMethodError
    //   287	297	566	java/lang/NoSuchMethodError
    //   297	302	566	java/lang/NoSuchMethodError
    //   306	325	566	java/lang/NoSuchMethodError
    //   388	437	566	java/lang/NoSuchMethodError
    //   3	37	570	java/lang/Exception
    //   232	252	570	java/lang/Exception
    //   259	264	570	java/lang/Exception
    //   268	278	570	java/lang/Exception
    //   278	283	570	java/lang/Exception
    //   287	297	570	java/lang/Exception
    //   297	302	570	java/lang/Exception
    //   306	325	570	java/lang/Exception
    //   325	346	570	java/lang/Exception
    //   346	367	570	java/lang/Exception
    //   367	388	570	java/lang/Exception
    //   388	437	570	java/lang/Exception
    //   367	388	574	java/lang/NoSuchMethodError
    //   346	367	578	java/lang/NoSuchMethodError
    //   325	346	582	java/lang/NoSuchMethodError
    //   41	64	586	java/lang/NoClassDefFoundError
    //   446	451	586	java/lang/NoClassDefFoundError
    //   69	76	597	java/lang/NoClassDefFoundError
    //   76	86	609	java/lang/NoClassDefFoundError
    //   91	99	618	java/lang/NoClassDefFoundError
    //   108	136	618	java/lang/NoClassDefFoundError
    //   141	153	618	java/lang/NoClassDefFoundError
    //   159	170	618	java/lang/NoClassDefFoundError
    //   173	212	618	java/lang/NoClassDefFoundError
    //   465	495	618	java/lang/NoClassDefFoundError
    //   495	535	618	java/lang/NoClassDefFoundError
    //   535	546	618	java/lang/NoClassDefFoundError
    //   41	64	628	java/lang/NoSuchMethodError
    //   446	451	628	java/lang/NoSuchMethodError
    //   69	76	639	java/lang/NoSuchMethodError
    //   76	86	651	java/lang/NoSuchMethodError
    //   91	99	660	java/lang/NoSuchMethodError
    //   108	136	660	java/lang/NoSuchMethodError
    //   141	153	660	java/lang/NoSuchMethodError
    //   159	170	660	java/lang/NoSuchMethodError
    //   173	212	660	java/lang/NoSuchMethodError
    //   465	495	660	java/lang/NoSuchMethodError
    //   495	535	660	java/lang/NoSuchMethodError
    //   535	546	660	java/lang/NoSuchMethodError
    //   41	64	670	java/lang/Exception
    //   446	451	670	java/lang/Exception
    //   69	76	681	java/lang/Exception
    //   76	86	693	java/lang/Exception
    //   91	99	702	java/lang/Exception
    //   108	136	702	java/lang/Exception
    //   141	153	702	java/lang/Exception
    //   173	212	702	java/lang/Exception
    //   465	495	702	java/lang/Exception
    //   495	535	702	java/lang/Exception
  }
  
  /* Error */
  public static JSONObject a(c paramC)
  {
    // Byte code:
    //   0: new 227	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 520	org/json/JSONObject:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: ifnull +495 -> 504
    //   12: aload_1
    //   13: ldc_w 615
    //   16: aload_0
    //   17: invokevirtual 618	com/pollfish/classes/c:d	()Ljava/lang/String;
    //   20: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   23: pop
    //   24: aload_0
    //   25: invokevirtual 621	com/pollfish/classes/c:r	()Ljava/lang/String;
    //   28: ifnull +15 -> 43
    //   31: aload_1
    //   32: ldc_w 623
    //   35: aload_0
    //   36: invokevirtual 621	com/pollfish/classes/c:r	()Ljava/lang/String;
    //   39: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   42: pop
    //   43: aload_0
    //   44: invokevirtual 626	com/pollfish/classes/c:e	()Ljava/lang/String;
    //   47: ifnull +15 -> 62
    //   50: aload_1
    //   51: ldc_w 628
    //   54: aload_0
    //   55: invokevirtual 626	com/pollfish/classes/c:e	()Ljava/lang/String;
    //   58: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   61: pop
    //   62: aload_0
    //   63: invokevirtual 631	com/pollfish/classes/c:f	()Ljava/lang/String;
    //   66: ifnull +15 -> 81
    //   69: aload_1
    //   70: ldc_w 633
    //   73: aload_0
    //   74: invokevirtual 631	com/pollfish/classes/c:f	()Ljava/lang/String;
    //   77: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   80: pop
    //   81: aload_0
    //   82: invokevirtual 636	com/pollfish/classes/c:q	()Ljava/lang/String;
    //   85: ifnull +15 -> 100
    //   88: aload_1
    //   89: ldc_w 638
    //   92: aload_0
    //   93: invokevirtual 636	com/pollfish/classes/c:q	()Ljava/lang/String;
    //   96: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   99: pop
    //   100: aload_0
    //   101: invokevirtual 641	com/pollfish/classes/c:K	()Ljava/lang/String;
    //   104: astore_2
    //   105: aload_2
    //   106: ifnull +18 -> 124
    //   109: aload_1
    //   110: ldc_w 643
    //   113: aload_0
    //   114: invokevirtual 641	com/pollfish/classes/c:K	()Ljava/lang/String;
    //   117: invokestatic 646	org/json/JSONObject:quote	(Ljava/lang/String;)Ljava/lang/String;
    //   120: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   123: pop
    //   124: aload_1
    //   125: ldc_w 648
    //   128: aload_0
    //   129: invokevirtual 650	com/pollfish/classes/c:y	()Z
    //   132: invokevirtual 526	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   135: pop
    //   136: aload_0
    //   137: invokevirtual 653	com/pollfish/classes/c:p	()Ljava/lang/String;
    //   140: ifnull +15 -> 155
    //   143: aload_1
    //   144: ldc_w 655
    //   147: aload_0
    //   148: invokevirtual 653	com/pollfish/classes/c:p	()Ljava/lang/String;
    //   151: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   154: pop
    //   155: aload_0
    //   156: invokevirtual 657	com/pollfish/classes/c:I	()Ljava/lang/String;
    //   159: ifnull +15 -> 174
    //   162: aload_1
    //   163: ldc_w 659
    //   166: aload_0
    //   167: invokevirtual 657	com/pollfish/classes/c:I	()Ljava/lang/String;
    //   170: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   173: pop
    //   174: aload_0
    //   175: invokevirtual 662	com/pollfish/classes/c:R	()Ljava/lang/String;
    //   178: astore_2
    //   179: aload_2
    //   180: ifnull +18 -> 198
    //   183: aload_1
    //   184: ldc_w 664
    //   187: aload_0
    //   188: invokevirtual 662	com/pollfish/classes/c:R	()Ljava/lang/String;
    //   191: invokestatic 646	org/json/JSONObject:quote	(Ljava/lang/String;)Ljava/lang/String;
    //   194: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   197: pop
    //   198: aload_0
    //   199: invokevirtual 667	com/pollfish/classes/c:S	()Ljava/lang/String;
    //   202: astore_2
    //   203: aload_2
    //   204: ifnull +18 -> 222
    //   207: aload_1
    //   208: ldc_w 669
    //   211: aload_0
    //   212: invokevirtual 667	com/pollfish/classes/c:S	()Ljava/lang/String;
    //   215: invokestatic 646	org/json/JSONObject:quote	(Ljava/lang/String;)Ljava/lang/String;
    //   218: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   221: pop
    //   222: aload_0
    //   223: invokevirtual 672	com/pollfish/classes/c:O	()I
    //   226: iconst_m1
    //   227: if_icmpeq +15 -> 242
    //   230: aload_1
    //   231: ldc_w 674
    //   234: aload_0
    //   235: invokevirtual 672	com/pollfish/classes/c:O	()I
    //   238: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   241: pop
    //   242: aload_1
    //   243: ldc_w 676
    //   246: aload_0
    //   247: invokevirtual 679	com/pollfish/classes/c:g	()Ljava/lang/String;
    //   250: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   253: pop
    //   254: aload_1
    //   255: ldc_w 681
    //   258: aload_0
    //   259: invokevirtual 684	com/pollfish/classes/c:h	()Ljava/lang/String;
    //   262: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   265: pop
    //   266: aload_0
    //   267: invokevirtual 687	com/pollfish/classes/c:v	()Ljava/lang/String;
    //   270: ifnull +15 -> 285
    //   273: aload_1
    //   274: ldc_w 689
    //   277: aload_0
    //   278: invokevirtual 687	com/pollfish/classes/c:v	()Ljava/lang/String;
    //   281: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   284: pop
    //   285: aload_0
    //   286: invokevirtual 692	com/pollfish/classes/c:w	()Ljava/lang/String;
    //   289: ifnull +15 -> 304
    //   292: aload_1
    //   293: ldc_w 694
    //   296: aload_0
    //   297: invokevirtual 692	com/pollfish/classes/c:w	()Ljava/lang/String;
    //   300: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   303: pop
    //   304: aload_1
    //   305: ldc_w 696
    //   308: aload_0
    //   309: invokevirtual 699	com/pollfish/classes/c:j	()Ljava/lang/String;
    //   312: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   315: pop
    //   316: aload_0
    //   317: invokevirtual 702	com/pollfish/classes/c:E	()Ljava/lang/String;
    //   320: ifnull +15 -> 335
    //   323: aload_1
    //   324: ldc_w 704
    //   327: aload_0
    //   328: invokevirtual 702	com/pollfish/classes/c:E	()Ljava/lang/String;
    //   331: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   334: pop
    //   335: aload_0
    //   336: invokevirtual 706	com/pollfish/classes/c:F	()Ljava/lang/String;
    //   339: ifnull +15 -> 354
    //   342: aload_1
    //   343: ldc_w 708
    //   346: aload_0
    //   347: invokevirtual 706	com/pollfish/classes/c:F	()Ljava/lang/String;
    //   350: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   353: pop
    //   354: aload_1
    //   355: ldc_w 710
    //   358: aload_0
    //   359: invokevirtual 713	com/pollfish/classes/c:U	()Z
    //   362: invokevirtual 526	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   365: pop
    //   366: aload_0
    //   367: invokevirtual 717	com/pollfish/classes/c:t	()Lorg/json/JSONObject;
    //   370: ifnull +15 -> 385
    //   373: aload_1
    //   374: ldc_w 719
    //   377: aload_0
    //   378: invokevirtual 717	com/pollfish/classes/c:t	()Lorg/json/JSONObject;
    //   381: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   384: pop
    //   385: aload_0
    //   386: invokevirtual 722	com/pollfish/classes/c:i	()Ljava/lang/String;
    //   389: ifnull +15 -> 404
    //   392: aload_1
    //   393: ldc_w 724
    //   396: aload_0
    //   397: invokevirtual 722	com/pollfish/classes/c:i	()Ljava/lang/String;
    //   400: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   403: pop
    //   404: aload_1
    //   405: ldc_w 726
    //   408: aload_0
    //   409: invokevirtual 729	com/pollfish/classes/c:s	()Ljava/lang/String;
    //   412: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   415: pop
    //   416: aload_0
    //   417: invokevirtual 732	com/pollfish/classes/c:k	()Ljava/lang/String;
    //   420: ifnull +15 -> 435
    //   423: aload_1
    //   424: ldc_w 734
    //   427: aload_0
    //   428: invokevirtual 732	com/pollfish/classes/c:k	()Ljava/lang/String;
    //   431: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   434: pop
    //   435: aload_0
    //   436: invokevirtual 737	com/pollfish/classes/c:m	()Ljava/lang/String;
    //   439: ifnull +15 -> 454
    //   442: aload_1
    //   443: ldc_w 739
    //   446: aload_0
    //   447: invokevirtual 737	com/pollfish/classes/c:m	()Ljava/lang/String;
    //   450: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   453: pop
    //   454: aload_0
    //   455: invokevirtual 742	com/pollfish/classes/c:n	()Ljava/lang/String;
    //   458: ifnull +15 -> 473
    //   461: aload_1
    //   462: ldc_w 744
    //   465: aload_0
    //   466: invokevirtual 742	com/pollfish/classes/c:n	()Ljava/lang/String;
    //   469: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   472: pop
    //   473: aload_0
    //   474: invokevirtual 747	com/pollfish/classes/c:o	()Ljava/lang/String;
    //   477: ifnull +15 -> 492
    //   480: aload_1
    //   481: ldc_w 749
    //   484: aload_0
    //   485: invokevirtual 747	com/pollfish/classes/c:o	()Ljava/lang/String;
    //   488: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   491: pop
    //   492: aload_1
    //   493: ldc_w 751
    //   496: aload_0
    //   497: invokevirtual 754	com/pollfish/classes/c:C	()Ljava/lang/String;
    //   500: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   503: pop
    //   504: aload_1
    //   505: areturn
    //   506: astore_0
    //   507: aload_1
    //   508: areturn
    //   509: astore_2
    //   510: goto -288 -> 222
    //   513: astore_2
    //   514: goto -316 -> 198
    //   517: astore_2
    //   518: goto -394 -> 124
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	521	0	paramC	c
    //   7	501	1	localJSONObject	JSONObject
    //   104	100	2	str	String
    //   509	1	2	localException1	Exception
    //   513	1	2	localException2	Exception
    //   517	1	2	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   12	43	506	java/lang/Exception
    //   43	62	506	java/lang/Exception
    //   62	81	506	java/lang/Exception
    //   81	100	506	java/lang/Exception
    //   100	105	506	java/lang/Exception
    //   124	155	506	java/lang/Exception
    //   155	174	506	java/lang/Exception
    //   174	179	506	java/lang/Exception
    //   198	203	506	java/lang/Exception
    //   222	242	506	java/lang/Exception
    //   242	285	506	java/lang/Exception
    //   285	304	506	java/lang/Exception
    //   304	335	506	java/lang/Exception
    //   335	354	506	java/lang/Exception
    //   354	385	506	java/lang/Exception
    //   385	404	506	java/lang/Exception
    //   404	435	506	java/lang/Exception
    //   435	454	506	java/lang/Exception
    //   454	473	506	java/lang/Exception
    //   473	492	506	java/lang/Exception
    //   492	504	506	java/lang/Exception
    //   207	222	509	java/lang/Exception
    //   183	198	513	java/lang/Exception
    //   109	124	517	java/lang/Exception
  }
  
  public static void a(Activity paramActivity, String paramString, int paramInt)
  {
    paramActivity = paramActivity.getBaseContext().getSharedPreferences("pollfish_pref", 0).edit();
    paramActivity.putInt("pollfish_pref" + paramString, paramInt);
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
    boolean bool = false;
    try
    {
      int i = paramActivity.getPackageManager().checkPermission("android.permission.INTERNET", paramActivity.getPackageName());
      if (i == 0) {
        bool = true;
      }
      return bool;
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
    catch (NoSuchMethodError paramView) {}
    return false;
  }
  
  public static boolean a(String paramString, int paramInt)
  {
    return (paramInt < paramString.length()) && (paramString.charAt(paramInt) == '1');
  }
  
  public static Animation b(View paramView, Position paramPosition, int paramInt, long paramLong)
  {
    if (paramView != null)
    {
      if (paramPosition == Position.TOP_LEFT) {
        paramPosition = new TranslateAnimation(0.0F, -(paramView.getWidth() * 2), 0.0F, 0.0F);
      }
      for (;;)
      {
        paramPosition.setDuration(paramLong);
        paramPosition.setStartOffset(paramInt);
        paramView.startAnimation(paramPosition);
        return paramPosition;
        if (paramPosition == Position.BOTTOM_LEFT) {
          paramPosition = new TranslateAnimation(0.0F, -(paramView.getWidth() * 2), 0.0F, 0.0F);
        } else if (paramPosition == Position.TOP_RIGHT) {
          paramPosition = new TranslateAnimation(0.0F, paramView.getWidth() * 2, 0.0F, 0.0F);
        } else if (paramPosition == Position.BOTTOM_RIGHT) {
          paramPosition = new TranslateAnimation(0.0F, paramView.getWidth() * 2, 0.0F, 0.0F);
        } else if (paramPosition == Position.MIDDLE_RIGHT) {
          paramPosition = new TranslateAnimation(0.0F, paramView.getWidth() * 2, 0.0F, 0.0F);
        } else if (paramPosition == Position.BOTTOM_LEFT) {
          paramPosition = new TranslateAnimation(0.0F, -(paramView.getWidth() * 2), 0.0F, 0.0F);
        } else {
          paramPosition = new TranslateAnimation(0.0F, -(paramView.getWidth() * 2), 0.0F, 0.0F);
        }
      }
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
    catch (Exception localException)
    {
      return null;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  private static void b(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfByte, ConcurrentHashMap paramConcurrentHashMap)
  {
    JSONObject localJSONObject = null;
    try
    {
      paramArrayOfByte = Base64.encodeToString(paramArrayOfByte, 2);
      if ((paramArrayOfByte == null) || (paramConcurrentHashMap.containsKey(paramArrayOfByte))) {}
    }
    catch (Exception paramArrayOfByte)
    {
      for (;;)
      {
        try
        {
          localJSONObject = new JSONObject();
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
          if (localJSONObject.length() > 0) {
            paramConcurrentHashMap.put(paramArrayOfByte, localJSONObject);
          }
          return;
        }
        catch (Exception paramBluetoothDevice) {}
        paramArrayOfByte = paramArrayOfByte;
        paramArrayOfByte = localJSONObject;
      }
    }
    catch (AssertionError paramArrayOfByte)
    {
      for (;;)
      {
        paramArrayOfByte = localJSONObject;
      }
    }
  }
  
  private static void b(ConcurrentHashMap paramConcurrentHashMap, a.c paramC)
  {
    if (paramConcurrentHashMap != null) {
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
          if (paramC != null)
          {
            paramC.a(localJSONArray);
            return;
          }
        }
      }
      catch (Exception paramConcurrentHashMap) {}
    }
  }
  
  public static boolean b(Activity paramActivity)
  {
    try
    {
      paramActivity = Build.MODEL;
      String str1 = Build.PRODUCT;
      String str2 = Build.MANUFACTURER;
      String str3 = Build.FINGERPRINT;
      String str4 = Build.DEVICE;
      String str5 = Build.BRAND;
      String str6 = Build.HARDWARE;
      boolean bool;
      if ((!str3.startsWith("generic")) && (!str3.startsWith("unknown")) && (!paramActivity.contains("google_sdk")) && (!paramActivity.contains("Emulator")) && (!paramActivity.contains("Android SDK built for x86")) && (!str2.contains("Genymotion")) && (!str1.equals("sdk")) && (!str1.contains("_sdk")) && (!str1.contains("sdk_")) && (!str1.matches(".*_?sdk_?.*")) && (!str1.contains("google_sdk")) && (!str5.startsWith("generic")) && (!str4.startsWith("generic"))) {
        bool = str6.equalsIgnoreCase("goldfish");
      }
      return bool;
    }
    catch (Exception paramActivity) {}
    return true;
  }
  
  public static String c()
  {
    String str2 = Locale.getDefault().getLanguage();
    String str1;
    if (!str2.equalsIgnoreCase(""))
    {
      str1 = str2;
      if (str2 != null) {}
    }
    else
    {
      str1 = null;
    }
    return str1;
  }
  
  public static boolean c(Activity paramActivity)
  {
    for (;;)
    {
      try
      {
        paramActivity = (ViewGroup)paramActivity.getWindow().getDecorView();
        if (paramActivity != null)
        {
          int i = 0;
          if (i < paramActivity.getChildCount())
          {
            if ((paramActivity.getChildAt(i) instanceof ViewGroup))
            {
              paramActivity = paramActivity.getChildAt(i);
              if (paramActivity != null)
              {
                boolean bool = paramActivity.isHardwareAccelerated();
                return bool;
              }
            }
            else
            {
              i += 1;
              continue;
            }
            return false;
          }
        }
      }
      catch (NoSuchMethodError paramActivity)
      {
        return false;
      }
      catch (ClassCastException paramActivity)
      {
        return false;
      }
      catch (Exception paramActivity)
      {
        return false;
      }
      paramActivity = null;
    }
  }
  
  public static String d()
  {
    try
    {
      String str = System.getProperty("http.agent");
      return str;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      return null;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static String d(Activity paramActivity)
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
  
  public static String e()
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
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      return null;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static String e(Activity paramActivity)
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
  
  public static String f()
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
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      return null;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static String f(Activity paramActivity)
  {
    try
    {
      if (((TelephonyManager)paramActivity.getSystemService("phone")).isNetworkRoaming()) {
        return "true";
      }
      return "false";
    }
    catch (Exception paramActivity) {}
    return null;
  }
  
  public static String g()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return String.valueOf(i);
    }
    catch (Exception localException) {}
    return "unknown";
  }
  
  public static String g(Activity paramActivity)
  {
    try
    {
      if (Settings.Secure.getInt(paramActivity.getContentResolver(), "accessibility_enabled") == 1) {
        return "true";
      }
      return "false";
    }
    catch (Exception paramActivity)
    {
      return null;
    }
    catch (Settings.SettingNotFoundException paramActivity) {}
    return null;
  }
  
  public static String h()
  {
    try
    {
      String str = Build.MANUFACTURER;
      if (str != null) {
        return str;
      }
      return "unknown";
    }
    catch (Exception localException) {}
    return "unknown";
  }
  
  public static String h(Activity paramActivity)
  {
    try
    {
      if (Settings.Global.getInt(paramActivity.getContentResolver(), "adb_enabled") == 1) {
        return "true";
      }
      return "false";
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      try
      {
        if (Settings.Secure.getInt(paramActivity.getContentResolver(), "adb_enabled") == 1) {
          return "true";
        }
        return "false";
      }
      catch (Settings.SettingNotFoundException paramActivity)
      {
        return null;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static String i()
  {
    try
    {
      String str = Build.MODEL + " (" + Build.PRODUCT + ")";
      return str;
    }
    catch (Exception localException) {}
    return "unknown";
  }
  
  public static String i(Activity paramActivity)
  {
    try
    {
      if (Settings.Global.getInt(paramActivity.getContentResolver(), "install_non_market_apps") == 1) {
        return "true";
      }
      return "false";
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      try
      {
        if (Settings.Secure.getInt(paramActivity.getContentResolver(), "install_non_market_apps") == 1) {
          return "true";
        }
        return "false";
      }
      catch (Settings.SettingNotFoundException paramActivity)
      {
        return null;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static String j(Activity paramActivity)
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
      return "false";
    }
    catch (Exception paramActivity)
    {
      return "false";
    }
    catch (NoClassDefFoundError paramActivity) {}
    return "false";
  }
  
  public static String k(Activity paramActivity)
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
      return "false";
    }
    catch (Exception paramActivity)
    {
      return "false";
    }
    catch (NoClassDefFoundError paramActivity) {}
    return "false";
  }
  
  public static String l(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getApplicationContext().getPackageName();
      return paramActivity;
    }
    catch (NoClassDefFoundError paramActivity)
    {
      return null;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
  }
  
  public static void m(Activity paramActivity)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)paramActivity.getSystemService("input_method");
    View localView2 = paramActivity.getCurrentFocus();
    View localView1 = localView2;
    if (localView2 == null) {
      localView1 = new View(paramActivity);
    }
    localInputMethodManager.hideSoftInputFromWindow(localView1.getWindowToken(), 0);
  }
  
  public static JSONArray n(Activity paramActivity)
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
          if ((!localRunningAppProcessInfo.processName.toString().startsWith("com.google")) && (!localRunningAppProcessInfo.processName.toString().startsWith("com.android"))) {
            paramActivity.add(localRunningAppProcessInfo.processName.toString());
          }
        }
        int i = paramActivity.size();
        if (i > 0) {
          try
          {
            paramActivity = new JSONArray(paramActivity);
            return paramActivity;
          }
          catch (Exception paramActivity) {}
        }
      }
      return null;
    }
    catch (Exception paramActivity) {}
  }
  
  public static JSONArray o(Activity paramActivity)
  {
    try
    {
      PackageManager localPackageManager = paramActivity.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      Object localObject1;
      for (paramActivity = null; localIterator.hasNext(); paramActivity = (Activity)localObject1)
      {
        Object localObject2 = (ApplicationInfo)localIterator.next();
        localObject1 = paramActivity;
        if ((((ApplicationInfo)localObject2).flags & 0x1) != 1)
        {
          String str = ((ApplicationInfo)localObject2).packageName.toString();
          localObject1 = paramActivity;
          if (str != null)
          {
            localObject1 = paramActivity;
            if (paramActivity == null) {
              localObject1 = new JSONArray();
            }
            paramActivity = new JSONObject();
            localObject2 = localPackageManager.getPackageInfo(((ApplicationInfo)localObject2).packageName, 0);
            paramActivity.put("pn", str);
            if (Build.VERSION.SDK_INT >= 9)
            {
              long l1 = ((PackageInfo)localObject2).firstInstallTime;
              long l2 = ((PackageInfo)localObject2).lastUpdateTime;
              paramActivity.put("fi", l1);
              paramActivity.put("lm", l2);
            }
            ((JSONArray)localObject1).put(paramActivity);
          }
        }
      }
      if (paramActivity != null) {
        return paramActivity;
      }
    }
    catch (NoClassDefFoundError paramActivity)
    {
      return null;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  public static JSONArray p(Activity paramActivity)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 443	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: ldc_w 1095
    //   7: aload_0
    //   8: invokevirtual 448	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   11: invokevirtual 454	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   14: ifne +172 -> 186
    //   17: aload_0
    //   18: ldc_w 1097
    //   21: invokevirtual 467	android/app/Activity:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   24: checkcast 1099	android/net/wifi/WifiManager
    //   27: invokevirtual 1102	android/net/wifi/WifiManager:getScanResults	()Ljava/util/List;
    //   30: astore_0
    //   31: aload_0
    //   32: ifnull +154 -> 186
    //   35: aload_0
    //   36: invokeinterface 393 1 0
    //   41: ifle +145 -> 186
    //   44: aload_0
    //   45: invokeinterface 1043 1 0
    //   50: astore_2
    //   51: aconst_null
    //   52: astore_1
    //   53: aload_2
    //   54: invokeinterface 835 1 0
    //   59: ifeq +129 -> 188
    //   62: aload_2
    //   63: invokeinterface 839 1 0
    //   68: checkcast 1104	android/net/wifi/ScanResult
    //   71: astore_3
    //   72: aload_1
    //   73: astore_0
    //   74: aload_1
    //   75: ifnonnull +11 -> 86
    //   78: new 822	org/json/JSONArray
    //   81: dup
    //   82: invokespecial 823	org/json/JSONArray:<init>	()V
    //   85: astore_0
    //   86: new 227	org/json/JSONObject
    //   89: dup
    //   90: invokespecial 520	org/json/JSONObject:<init>	()V
    //   93: astore_1
    //   94: aload_1
    //   95: ldc_w 1106
    //   98: aload_3
    //   99: getfield 1109	android/net/wifi/ScanResult:BSSID	Ljava/lang/String;
    //   102: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   105: pop
    //   106: aload_1
    //   107: ldc_w 1111
    //   110: aload_3
    //   111: getfield 1114	android/net/wifi/ScanResult:SSID	Ljava/lang/String;
    //   114: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   117: pop
    //   118: aload_1
    //   119: ldc_w 1116
    //   122: aload_3
    //   123: getfield 1119	android/net/wifi/ScanResult:capabilities	Ljava/lang/String;
    //   126: invokevirtual 534	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   129: pop
    //   130: aload_1
    //   131: ldc_w 1121
    //   134: aload_3
    //   135: getfield 1124	android/net/wifi/ScanResult:frequency	I
    //   138: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   141: pop
    //   142: aload_1
    //   143: ldc_w 1126
    //   146: aload_3
    //   147: getfield 1128	android/net/wifi/ScanResult:level	I
    //   150: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   153: pop
    //   154: getstatic 461	android/os/Build$VERSION:SDK_INT	I
    //   157: bipush 17
    //   159: if_icmplt +15 -> 174
    //   162: aload_1
    //   163: ldc_w 1130
    //   166: aload_3
    //   167: getfield 1133	android/net/wifi/ScanResult:timestamp	J
    //   170: invokevirtual 1091	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   173: pop
    //   174: aload_0
    //   175: aload_1
    //   176: invokevirtual 845	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   179: pop
    //   180: aload_0
    //   181: astore_1
    //   182: goto -129 -> 53
    //   185: astore_0
    //   186: aconst_null
    //   187: areturn
    //   188: aload_1
    //   189: ifnull -3 -> 186
    //   192: aload_1
    //   193: areturn
    //   194: astore_0
    //   195: goto -9 -> 186
    //   198: astore_0
    //   199: goto -13 -> 186
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	202	0	paramActivity	Activity
    //   52	141	1	localObject	Object
    //   50	13	2	localIterator	Iterator
    //   71	96	3	localScanResult	android.net.wifi.ScanResult
    // Exception table:
    //   from	to	target	type
    //   0	31	185	java/lang/Exception
    //   35	51	185	java/lang/Exception
    //   53	72	185	java/lang/Exception
    //   78	86	185	java/lang/Exception
    //   86	174	185	java/lang/Exception
    //   174	180	185	java/lang/Exception
    //   0	31	194	java/lang/NoSuchFieldError
    //   35	51	194	java/lang/NoSuchFieldError
    //   53	72	194	java/lang/NoSuchFieldError
    //   78	86	194	java/lang/NoSuchFieldError
    //   86	174	194	java/lang/NoSuchFieldError
    //   174	180	194	java/lang/NoSuchFieldError
    //   0	31	198	java/lang/NoClassDefFoundError
    //   35	51	198	java/lang/NoClassDefFoundError
    //   53	72	198	java/lang/NoClassDefFoundError
    //   78	86	198	java/lang/NoClassDefFoundError
    //   86	174	198	java/lang/NoClassDefFoundError
    //   174	180	198	java/lang/NoClassDefFoundError
  }
  
  public static String q(Activity paramActivity)
  {
    if (paramActivity.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", paramActivity.getPackageName()) == 0) {
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
        return "noMac";
      }
    }
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
      Activity localActivity = paramActivity;
      if (paramActivity == null) {
        localActivity = null;
      }
      return localActivity;
    }
    catch (Exception paramActivity) {}
    return null;
  }
  
  public static String t(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0);
      String str = paramActivity.versionName;
      int i = paramActivity.versionCode;
      paramActivity = str + i;
      return paramActivity;
    }
    catch (Exception paramActivity) {}
    return null;
  }
  
  public static String u(Activity paramActivity)
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
  
  public static String v(Activity paramActivity)
  {
    int i = 0;
    for (;;)
    {
      try
      {
        if (paramActivity.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramActivity.getPackageName()) == 0)
        {
          paramActivity = (ConnectivityManager)paramActivity.getSystemService("connectivity");
          Network[] arrayOfNetwork;
          int j;
          if (Build.VERSION.SDK_INT >= 21)
          {
            arrayOfNetwork = paramActivity.getAllNetworks();
            j = arrayOfNetwork.length;
            if (i < j)
            {
              NetworkInfo localNetworkInfo = paramActivity.getNetworkInfo(arrayOfNetwork[i]);
              if (!localNetworkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                break label127;
              }
              return localNetworkInfo.getTypeName();
            }
          }
          else
          {
            paramActivity = paramActivity.getAllNetworkInfo();
            j = paramActivity.length;
            i = 0;
            if (i < j)
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
          return null;
        }
        else
        {
          return null;
        }
      }
      catch (Exception paramActivity)
      {
        return null;
      }
      label127:
      i += 1;
    }
  }
  
  public static String w(Activity paramActivity)
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
    }
    catch (NoSuchFieldError paramActivity)
    {
      return null;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
  }
  
  public static String x(Activity paramActivity)
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
    catch (NoSuchFieldError paramActivity)
    {
      return null;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
  }
  
  public static JSONObject y(Activity paramActivity)
  {
    Object localObject = paramActivity.getPackageManager();
    if ((((PackageManager)localObject).checkPermission("android.permission.ACCESS_COARSE_LOCATION", paramActivity.getPackageName()) == 0) || (((PackageManager)localObject).checkPermission("android.permission.ACCESS_FINE_LOCATION", paramActivity.getPackageName()) == 0))
    {
      LocationManager localLocationManager = (LocationManager)paramActivity.getSystemService("location");
      for (;;)
      {
        try
        {
          localIterator = localLocationManager.getAllProviders().iterator();
          paramActivity = null;
          localObject = paramActivity;
        }
        catch (Exception paramActivity)
        {
          Iterator localIterator;
          Location localLocation;
          localObject = null;
        }
        try
        {
          if (!localIterator.hasNext()) {
            break;
          }
          localLocation = localLocationManager.getLastKnownLocation((String)localIterator.next());
          if (localLocation == null) {
            continue;
          }
          localObject = localLocation;
          if (paramActivity != null)
          {
            float f1 = localLocation.getAccuracy();
            float f2 = paramActivity.getAccuracy();
            if (f1 >= f2) {
              break label296;
            }
            localObject = localLocation;
          }
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            localActivity = paramActivity;
          }
          localActivity = paramActivity;
          continue;
        }
        paramActivity = (Activity)localObject;
      }
    }
    for (;;)
    {
      if (localObject == null) {
        localObject = null;
      }
      for (;;)
      {
        return localObject;
        try
        {
          paramActivity = new JSONObject();
        }
        catch (Exception localException1)
        {
          try
          {
            paramActivity.put("lat", ((Location)localObject).getLatitude());
            paramActivity.put("lon", ((Location)localObject).getLongitude());
            paramActivity.put("acc", ((Location)localObject).getAccuracy());
            paramActivity.put("alt", ((Location)localObject).getAltitude());
            paramActivity.put("bear", ((Location)localObject).getBearing());
          }
          catch (Exception localException1)
          {
            try
            {
              paramActivity.put("ela", ((Location)localObject).getElapsedRealtimeNanos());
              if (((Location)localObject).getProvider() != null) {
                paramActivity.put("prv", ((Location)localObject).getProvider());
              }
              paramActivity.put("speed", ((Location)localObject).getSpeed());
              paramActivity.put("time", ((Location)localObject).getTime());
              for (;;)
              {
                localObject = paramActivity;
                if (paramActivity != null) {
                  break;
                }
                return null;
                paramActivity = paramActivity;
                paramActivity = null;
                continue;
                localException1 = localException1;
              }
            }
            catch (Exception localException3)
            {
              for (;;) {}
            }
            catch (NoSuchMethodError localNoSuchMethodError)
            {
              for (;;) {}
            }
          }
        }
      }
      label296:
      Activity localActivity = null;
    }
  }
  
  public static Point z(Activity paramActivity)
  {
    try
    {
      paramActivity = ((WindowManager)paramActivity.getSystemService("window")).getDefaultDisplay();
      Point localPoint = new Point();
      try
      {
        paramActivity.getSize(localPoint);
        return localPoint;
      }
      catch (NoSuchMethodError localNoSuchMethodError)
      {
        localPoint.x = paramActivity.getWidth();
        localPoint.y = paramActivity.getHeight();
        return localPoint;
      }
      catch (Exception localException)
      {
        localPoint.x = paramActivity.getWidth();
        localPoint.y = paramActivity.getHeight();
        return localPoint;
      }
      return null;
    }
    catch (Exception paramActivity) {}
  }
}
