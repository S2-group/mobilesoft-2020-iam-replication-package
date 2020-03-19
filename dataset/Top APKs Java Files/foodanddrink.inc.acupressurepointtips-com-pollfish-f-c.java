package com.pollfish.f;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import com.pollfish.constants.Position;
import com.pollfish.interfaces.a.b;
import com.pollfish.interfaces.a.d;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

public class c
{
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
    int i = paramActivity.getBaseContext().getSharedPreferences("pollfish_pref", 0).getInt("pollfish_pref" + paramString, 0);
    b.a("PollfishUtilities", "retrieveKeyFromPref: " + i);
    return i;
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
        com.pollfish.f.b.a.a(paramBitmap);
        paramBitmap = str;
      }
    }
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
  
  public static com.pollfish.a.a a(String paramString, com.pollfish.a.b paramB, a.d paramD, a.b paramB1, Activity paramActivity, boolean paramBoolean)
  {
    b.a("PollfishUtilities", "parse Register Response ");
    b.a("PollfishUtilities", "hasAccepted: " + paramBoolean);
    if (paramString == null) {
      return null;
    }
    try
    {
      paramString = new JSONObject(paramString);
      String str1 = paramString.getString("response_type");
      int i = paramString.getInt("intrusion");
      int j = paramString.getInt("width_percentage");
      int k = paramString.getInt("height_percentage");
      String str2 = paramString.getString("content");
      String str3 = paramString.getString("s_id");
      boolean bool1 = paramString.getBoolean("custom_indicator");
      String str4 = paramString.getString("indicator_image_left");
      String str5 = paramString.getString("indicator_image_right");
      String str6 = paramString.getString("mobile_data");
      String str7 = paramString.getString("assets");
      String str8 = paramString.getString("background_color");
      boolean bool2 = paramString.getBoolean("short_survey");
      paramString = new com.pollfish.a.a(str3, str1, i, str2, bool1, str4, str5, j, k, paramB.C(), paramD, str6, str7, paramB1, paramActivity, str8, paramBoolean, bool2);
      return paramString;
    }
    catch (Exception paramString)
    {
      b.b("PollfishUtilities", "error while parsing server register response: " + paramString);
      com.pollfish.f.b.a.a(paramString);
    }
    return null;
  }
  
  public static String a()
  {
    try
    {
      String str = Build.VERSION.RELEASE;
      return str;
    }
    catch (Exception localException)
    {
      com.pollfish.f.b.a.a(localException);
      b.b("PollfishUtilities", "getOSversion:" + localException);
    }
    return "unknown";
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
      com.pollfish.f.b.a.a(paramString1);
      throw new SignatureException("error building signature, no such algorithm in device HmacSHA256");
    }
    catch (InvalidKeyException paramString1)
    {
      com.pollfish.f.b.a.a(paramString1);
      throw new SignatureException("error building signature, invalid key HmacSHA256");
    }
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
  
  public static JSONObject a(com.pollfish.a.b paramB)
  {
    JSONObject localJSONObject = new JSONObject();
    if (paramB != null) {}
    try
    {
      localJSONObject.put("device_descr", paramB.d());
      if (paramB.r() != null) {
        localJSONObject.put("provider", paramB.r());
      }
      if (paramB.e() != null) {
        localJSONObject.put("provider_mcc", paramB.e());
      }
      if (paramB.f() != null) {
        localJSONObject.put("provider_mnc", paramB.f());
      }
      if (paramB.q() != null) {
        localJSONObject.put("nfc_enabled", paramB.q());
      }
      if (paramB.p() != null) {
        localJSONObject.put("nfc_exists", paramB.p());
      }
      localJSONObject.put("os", paramB.g());
      localJSONObject.put("os_ver", paramB.h());
      if (paramB.v() != null) {
        localJSONObject.put("scr_h", paramB.v());
      }
      if (paramB.w() != null) {
        localJSONObject.put("scr_w", paramB.w());
      }
      localJSONObject.put("manufacturer", paramB.j());
      if (paramB.H() != null) {
        localJSONObject.put("app_version", paramB.H());
      }
      if (paramB.I() != null) {
        localJSONObject.put("con_type", paramB.I());
      }
      if (paramB.t() != null)
      {
        if (paramB.x() != null) {
          localJSONObject.put("lat", paramB.x());
        }
        if (paramB.y() != null) {
          localJSONObject.put("lon", paramB.y());
        }
        if (paramB.z() != null) {
          localJSONObject.put("accuracy", paramB.z());
        }
      }
      if (paramB.i() != null) {
        localJSONObject.put("locale", paramB.i());
      }
      localJSONObject.put("scr_size", paramB.s());
      if (paramB.k() != null) {
        localJSONObject.put("is_roaming", paramB.k());
      }
      if (paramB.m() != null) {
        localJSONObject.put("accessibility_enabled", paramB.m());
      }
      if (paramB.n() != null) {
        localJSONObject.put("developer_enabled", paramB.n());
      }
      if (paramB.o() != null) {
        localJSONObject.put("install_non_market_apps", paramB.o());
      }
      localJSONObject.put("hardware_accelerated", paramB.F());
      return localJSONObject;
    }
    catch (Exception paramB)
    {
      b.b("PollfishUtilities", "error: " + paramB);
      com.pollfish.f.b.a.a(paramB);
    }
    return localJSONObject;
  }
  
  public static void a(Activity paramActivity, String paramString, int paramInt)
  {
    b.a("PollfishUtilities", "saveKeyInPref: " + paramInt);
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
    catch (Exception paramContext)
    {
      com.pollfish.f.b.a.a(paramContext);
      b.b("PollfishUtilities", "postRunnableInMainThread:" + paramContext);
    }
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
    catch (Exception paramActivity)
    {
      com.pollfish.f.b.a.a(paramActivity);
      b.b("PollfishUtilities", "permissionsGranted:" + paramActivity);
    }
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
  
  public static boolean b(Activity paramActivity)
  {
    bool2 = true;
    b.a("PollfishUtilities", "--isRunningOnEmulator()--");
    for (;;)
    {
      try
      {
        paramActivity = Build.MODEL;
        b.a("PollfishUtilities", "model :" + paramActivity);
        String str1 = Build.PRODUCT;
        b.a("PollfishUtilities", "product: " + str1);
        String str2 = Build.MANUFACTURER;
        b.a("PollfishUtilities", "manufacturer: " + str2);
        String str3 = Build.FINGERPRINT;
        b.a("PollfishUtilities", "fingerprint: " + str2);
        String str4 = Build.DEVICE;
        b.a("PollfishUtilities", "Device: " + str4);
        String str5 = Build.BRAND;
        b.a("PollfishUtilities", "Brand: " + str5);
        String str6 = Build.HARDWARE;
        b.a("PollfishUtilities", "Hardware: " + str6);
        bool1 = bool2;
        if (!str3.startsWith("generic"))
        {
          bool1 = bool2;
          if (!str3.startsWith("unknown"))
          {
            bool1 = bool2;
            if (!paramActivity.contains("google_sdk"))
            {
              bool1 = bool2;
              if (!paramActivity.contains("Emulator"))
              {
                bool1 = bool2;
                if (!paramActivity.contains("Android SDK built for x86"))
                {
                  bool1 = bool2;
                  if (!str2.contains("Genymotion"))
                  {
                    bool1 = bool2;
                    if (!str1.equals("sdk"))
                    {
                      bool1 = bool2;
                      if (!str1.contains("_sdk"))
                      {
                        bool1 = bool2;
                        if (!str1.contains("sdk_"))
                        {
                          bool1 = bool2;
                          if (!str1.matches(".*_?sdk_?.*"))
                          {
                            bool1 = bool2;
                            if (!str1.contains("google_sdk"))
                            {
                              bool1 = bool2;
                              if (!str5.startsWith("generic"))
                              {
                                bool1 = bool2;
                                if (!str4.startsWith("generic"))
                                {
                                  bool1 = str6.equalsIgnoreCase("goldfish");
                                  if (!bool1) {
                                    continue;
                                  }
                                  bool1 = bool2;
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      catch (Exception paramActivity)
      {
        b.b("PollfishUtilities", "isRunningOnEmulator exception: " + paramActivity);
        boolean bool1 = bool2;
        continue;
      }
      b.a("PollfishUtilities", "runningOnEmulator: " + bool1);
      return bool1;
      bool1 = false;
    }
  }
  
  public static String c()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return String.valueOf(i);
    }
    catch (Exception localException)
    {
      b.b("PollfishUtilities", "getAPIVersion e:" + localException);
      com.pollfish.f.b.a.a(localException);
    }
    return "unknown";
  }
  
  public static boolean c(Activity paramActivity)
  {
    try
    {
      boolean bool = ((ViewGroup)((ViewGroup)paramActivity.getWindow().getDecorView()).getChildAt(0)).isHardwareAccelerated();
      return bool;
    }
    catch (NoSuchMethodError paramActivity) {}
    return false;
  }
  
  public static String d()
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
      b.b("PollfishUtilities", "getManufacturer e:" + localException);
      com.pollfish.f.b.a.a(localException);
    }
    return "unknown";
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
    catch (Exception paramActivity)
    {
      com.pollfish.f.b.a.a(paramActivity);
      b.b("PollfishUtilities", "getMCC:" + paramActivity);
    }
    return null;
  }
  
  public static String e()
  {
    try
    {
      String str = Build.MODEL + " (" + Build.PRODUCT + ")";
      return str;
    }
    catch (Exception localException)
    {
      b.b("PollfishUtilities", "getModelProductName e:" + localException);
      com.pollfish.f.b.a.a(localException);
    }
    return "unknown";
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
    catch (Exception paramActivity)
    {
      com.pollfish.f.b.a.a(paramActivity);
      b.b("PollfishUtilities", "getMNC:" + paramActivity);
    }
    return null;
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
    catch (Exception paramActivity)
    {
      com.pollfish.f.b.a.a(paramActivity);
      b.b("PollfishUtilities", "isRoaming:" + paramActivity);
    }
    return null;
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
      b.c("PollfishUtilities", "isAccessibilityEnabled:" + paramActivity);
    }
    return null;
  }
  
  /* Error */
  public static String h(Activity paramActivity)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 637	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   4: ldc_w 652
    //   7: invokestatic 655	android/provider/Settings$Global:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   10: iconst_1
    //   11: if_icmpne +7 -> 18
    //   14: ldc_w 629
    //   17: areturn
    //   18: ldc_w 631
    //   21: areturn
    //   22: astore_1
    //   23: ldc 67
    //   25: new 47	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   32: ldc_w 657
    //   35: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_1
    //   39: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokestatic 646	com/pollfish/f/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   48: aload_0
    //   49: invokevirtual 637	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   52: ldc_w 652
    //   55: invokestatic 642	android/provider/Settings$Secure:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   58: iconst_1
    //   59: if_icmpne +36 -> 95
    //   62: ldc_w 629
    //   65: areturn
    //   66: astore_1
    //   67: ldc 67
    //   69: new 47	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   76: ldc_w 657
    //   79: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: aload_1
    //   83: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokestatic 646	com/pollfish/f/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   92: goto -44 -> 48
    //   95: ldc_w 631
    //   98: areturn
    //   99: astore_0
    //   100: ldc 67
    //   102: new 47	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   109: ldc_w 657
    //   112: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: aload_0
    //   116: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   119: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   122: invokestatic 215	com/pollfish/f/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   125: aconst_null
    //   126: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	127	0	paramActivity	Activity
    //   22	17	1	localException	Exception
    //   66	17	1	localNoClassDefFoundError	NoClassDefFoundError
    // Exception table:
    //   from	to	target	type
    //   0	14	22	java/lang/Exception
    //   0	14	66	java/lang/NoClassDefFoundError
    //   48	62	99	android/provider/Settings$SettingNotFoundException
  }
  
  /* Error */
  public static String i(Activity paramActivity)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 637	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   4: ldc_w 409
    //   7: invokestatic 655	android/provider/Settings$Global:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   10: iconst_1
    //   11: if_icmpne +7 -> 18
    //   14: ldc_w 629
    //   17: areturn
    //   18: ldc_w 631
    //   21: areturn
    //   22: astore_1
    //   23: ldc 67
    //   25: new 47	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   32: ldc_w 659
    //   35: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_1
    //   39: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokestatic 646	com/pollfish/f/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   48: aload_0
    //   49: invokevirtual 637	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   52: ldc_w 409
    //   55: invokestatic 642	android/provider/Settings$Secure:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   58: iconst_1
    //   59: if_icmpne +36 -> 95
    //   62: ldc_w 629
    //   65: areturn
    //   66: astore_1
    //   67: ldc 67
    //   69: new 47	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   76: ldc_w 659
    //   79: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: aload_1
    //   83: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   86: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: invokestatic 646	com/pollfish/f/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   92: goto -44 -> 48
    //   95: ldc_w 631
    //   98: areturn
    //   99: astore_0
    //   100: ldc 67
    //   102: new 47	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   109: ldc_w 659
    //   112: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: aload_0
    //   116: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   119: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   122: invokestatic 215	com/pollfish/f/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   125: aconst_null
    //   126: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	127	0	paramActivity	Activity
    //   22	17	1	localException	Exception
    //   66	17	1	localNoClassDefFoundError	NoClassDefFoundError
    // Exception table:
    //   from	to	target	type
    //   0	14	22	java/lang/Exception
    //   0	14	66	java/lang/NoClassDefFoundError
    //   48	62	99	android/provider/Settings$SettingNotFoundException
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
      b.c("PollfishUtilities", "getIfNFCExists:" + paramActivity);
      return "false";
    }
    catch (Exception paramActivity)
    {
      b.b("PollfishUtilities", "getIfNFCExists:" + paramActivity);
      return "false";
    }
    catch (NoClassDefFoundError paramActivity)
    {
      b.b("PollfishUtilities", "getIfNFCExists:" + paramActivity);
    }
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
      b.b("PollfishUtilities", "getIfNFCExists:" + paramActivity);
      return "false";
    }
    catch (Exception paramActivity)
    {
      b.b("PollfishUtilities", "getIfNFCEnabled:" + paramActivity);
      return "false";
    }
    catch (NoClassDefFoundError paramActivity)
    {
      b.b("PollfishUtilities", "getIfNFCExists:" + paramActivity);
    }
    return "false";
  }
  
  public static String l(Activity paramActivity)
  {
    b.a("PollfishUtilities", "getInstalledApps");
    try
    {
      paramActivity = paramActivity.getPackageManager();
      b.a("PollfishUtilities", "getInstalledApps begin");
      Object localObject = paramActivity.getInstalledApplications(128);
      paramActivity = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if ((localApplicationInfo.flags & 0x1) != 1) {
          paramActivity.add(localApplicationInfo.packageName.toString());
        }
      }
      return null;
    }
    catch (Exception paramActivity)
    {
      com.pollfish.f.b.a.a(paramActivity);
      b.b("PollfishUtilities", "getInstalledApps:" + paramActivity);
      return null;
      paramActivity = paramActivity.toString();
      return paramActivity;
    }
    catch (NoClassDefFoundError paramActivity)
    {
      com.pollfish.f.b.a.a(paramActivity);
      b.b("PollfishUtilities", "getInstalledApps:" + paramActivity);
    }
  }
  
  public static String m(Activity paramActivity)
  {
    try
    {
      paramActivity = ((WifiManager)paramActivity.getSystemService("wifi")).getScanResults().toString();
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      b.c("PollfishUtilities", "getWifis:" + paramActivity);
      return null;
    }
    catch (NoClassDefFoundError paramActivity)
    {
      com.pollfish.f.b.a.a(paramActivity);
      b.b("PollfishUtilities", "getWifis:" + paramActivity);
    }
    return null;
  }
  
  public static String n(Activity paramActivity)
  {
    b.a("PollfishUtilities", "getMacAddress");
    if (paramActivity.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", paramActivity.getPackageName()) == 0)
    {
      b.a("PollfishUtilities", "ACCESS_WIFI_STATE permission in place");
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
        b.b("PollfishUtilities", "noMac exception: " + paramActivity);
        return "noMac";
      }
    }
    b.a("PollfishUtilities", "No ACCESS_WIFI_STATE permission in place");
    return "noMac";
  }
  
  public static String o(Activity paramActivity)
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
    catch (Exception paramActivity)
    {
      b.b("PollfishUtilities", "getNetworkOperatorName e:" + paramActivity);
      com.pollfish.f.b.a.a(paramActivity);
    }
    return null;
  }
  
  public static String p(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0);
      String str = paramActivity.versionName;
      int i = paramActivity.versionCode;
      b.a("PollfishUtilities", "versionName:" + str);
      b.a("PollfishUtilities", "versionCode:" + i);
      b.a("PollfishUtilities", "getAppVersion:" + str + i);
      paramActivity = str + i;
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      b.a("PollfishUtilities", "getAppVersion exception:" + paramActivity);
    }
    return null;
  }
  
  public static String q(Activity paramActivity)
  {
    b.a("PollfishUtilities", "getDeviceIMEI");
    try
    {
      if (paramActivity.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramActivity.getPackageName()) == 0)
      {
        b.a("PollfishUtilities", "READ_PHONE_STATE permission at place");
        paramActivity = ((TelephonyManager)paramActivity.getSystemService("phone")).getDeviceId();
        b.a("PollfishUtilities", "getDeviceIME:" + paramActivity);
        return paramActivity;
      }
      b.a("PollfishUtilities", "READ_PHONE_STATE permission not at place");
      return null;
    }
    catch (Exception paramActivity)
    {
      b.a("PollfishUtilities", "getDeviceIME exception:" + paramActivity);
    }
    return null;
  }
  
  public static String r(Activity paramActivity)
  {
    b.a("PollfishUtilities", "getConnectionType");
    for (;;)
    {
      int i;
      try
      {
        if (paramActivity.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramActivity.getPackageName()) == 0)
        {
          b.a("PollfishUtilities", "ACCESS_NETWORK_STATE permission at place");
          NetworkInfo[] arrayOfNetworkInfo = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getAllNetworkInfo();
          int j = arrayOfNetworkInfo.length;
          i = 0;
          paramActivity = null;
          if (i < j)
          {
            NetworkInfo localNetworkInfo = arrayOfNetworkInfo[i];
            if (localNetworkInfo.isConnected()) {
              paramActivity = localNetworkInfo.getTypeName();
            }
          }
          else
          {
            b.a("PollfishUtilities", "getConnectionType:" + paramActivity);
            return paramActivity;
          }
        }
        else
        {
          b.a("PollfishUtilities", "ACCESS_NETWORK_STATE permission not at place");
          return null;
        }
      }
      catch (Exception paramActivity)
      {
        b.a("PollfishUtilities", "getDeviceIME exception:" + paramActivity);
        return null;
      }
      i += 1;
    }
  }
  
  /* Error */
  public static android.location.Location s(Activity paramActivity)
  {
    // Byte code:
    //   0: ldc 67
    //   2: ldc_w 817
    //   5: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   8: aload_0
    //   9: invokevirtual 453	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   12: astore_1
    //   13: aload_1
    //   14: ldc_w 819
    //   17: aload_0
    //   18: invokevirtual 458	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   21: invokevirtual 464	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   24: ifeq +17 -> 41
    //   27: aload_1
    //   28: ldc_w 821
    //   31: aload_0
    //   32: invokevirtual 458	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   35: invokevirtual 464	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   38: ifne +140 -> 178
    //   41: aload_0
    //   42: ldc_w 823
    //   45: invokevirtual 603	android/app/Activity:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   48: checkcast 825	android/location/LocationManager
    //   51: astore_2
    //   52: ldc 67
    //   54: ldc_w 827
    //   57: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   60: iconst_0
    //   61: ifne +86 -> 147
    //   64: ldc 67
    //   66: ldc_w 829
    //   69: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   72: aload_2
    //   73: ldc_w 831
    //   76: invokevirtual 835	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   79: astore_1
    //   80: aload_1
    //   81: astore_0
    //   82: aload_1
    //   83: ifnonnull +19 -> 102
    //   86: ldc 67
    //   88: ldc_w 837
    //   91: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   94: aload_2
    //   95: ldc_w 839
    //   98: invokevirtual 835	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   101: astore_0
    //   102: aload_0
    //   103: ifnonnull +131 -> 234
    //   106: ldc 67
    //   108: ldc_w 841
    //   111: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   114: aload_2
    //   115: ldc_w 843
    //   118: invokevirtual 835	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   121: astore_1
    //   122: aload_1
    //   123: astore_0
    //   124: aload_0
    //   125: ifnonnull +66 -> 191
    //   128: ldc 67
    //   130: ldc_w 845
    //   133: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   136: aconst_null
    //   137: areturn
    //   138: astore_0
    //   139: ldc 67
    //   141: ldc_w 847
    //   144: invokestatic 215	com/pollfish/f/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   147: aconst_null
    //   148: astore_1
    //   149: goto -69 -> 80
    //   152: astore_0
    //   153: ldc 67
    //   155: ldc_w 849
    //   158: invokestatic 215	com/pollfish/f/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   161: aload_1
    //   162: astore_0
    //   163: goto -61 -> 102
    //   166: astore_1
    //   167: ldc 67
    //   169: ldc_w 851
    //   172: invokestatic 215	com/pollfish/f/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   175: goto -51 -> 124
    //   178: ldc 67
    //   180: ldc_w 853
    //   183: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   186: aconst_null
    //   187: astore_0
    //   188: goto -64 -> 124
    //   191: ldc 67
    //   193: new 47	java/lang/StringBuilder
    //   196: dup
    //   197: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   200: ldc_w 855
    //   203: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload_0
    //   207: invokevirtual 861	android/location/Location:getLatitude	()D
    //   210: invokevirtual 864	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   213: ldc_w 866
    //   216: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: aload_0
    //   220: invokevirtual 869	android/location/Location:getLongitude	()D
    //   223: invokevirtual 864	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   226: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   232: aload_0
    //   233: areturn
    //   234: goto -110 -> 124
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	237	0	paramActivity	Activity
    //   12	150	1	localObject	Object
    //   166	1	1	localException	Exception
    //   51	64	2	localLocationManager	android.location.LocationManager
    // Exception table:
    //   from	to	target	type
    //   64	80	138	java/lang/Exception
    //   86	102	152	java/lang/Exception
    //   106	122	166	java/lang/Exception
  }
  
  public static Point t(Activity paramActivity)
  {
    try
    {
      paramActivity = ((WindowManager)paramActivity.getSystemService("window")).getDefaultDisplay();
      Point localPoint = new Point();
      try
      {
        paramActivity.getSize(localPoint);
        b.a("PollfishUtilities", "getScreenDimensions screen width:" + localPoint.x + "  screen height:" + localPoint.y);
        return localPoint;
      }
      catch (NoSuchMethodError localNoSuchMethodError)
      {
        for (;;)
        {
          localPoint.x = paramActivity.getWidth();
          localPoint.y = paramActivity.getHeight();
        }
      }
      return null;
    }
    catch (Exception paramActivity)
    {
      b.b("PollfishUtilities", "getScreenDimensions e:" + paramActivity);
      com.pollfish.f.b.a.a(paramActivity);
    }
  }
  
  public static String u(Activity paramActivity)
  {
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
          b.c("PollfishUtilities", "NoSuchMethodError getScreenInch from metrics");
          j = i;
          i = m;
        }
      }
      catch (Exception paramActivity)
      {
        for (;;)
        {
          b.c("PollfishUtilities", "Exception get screen from metrics: " + paramActivity);
          i = m;
        }
      }
      b.a("PollfishUtilities", "Screen width  : " + j);
      b.a("PollfishUtilities", "Screen height  : " + i);
      k = localDisplayMetrics.densityDpi;
      d2 = j / k;
      d1 = i / k;
      d2 = Math.pow(d2, 2.0D);
      d1 = Math.sqrt(Math.pow(d1, 2.0D) + d2);
      b.a("PollfishUtilities", "Screen dm.xdpi  : " + localDisplayMetrics.xdpi);
      b.a("PollfishUtilities", "Screen dm.ydpi  : " + localDisplayMetrics.ydpi);
      b.a("PollfishUtilities", "Screen dm.density : " + localDisplayMetrics.density);
      b.a("PollfishUtilities", "Screen inches : " + d1);
      return String.valueOf(d1);
    }
    catch (Exception paramActivity)
    {
      int m;
      int i;
      int j;
      b.b("PollfishUtilities", "getScreenInch e:" + paramActivity);
      com.pollfish.f.b.a.a(paramActivity);
    }
    return "unknown";
  }
}
