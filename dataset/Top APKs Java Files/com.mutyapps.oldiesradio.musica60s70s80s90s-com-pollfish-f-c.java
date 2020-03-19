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
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
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
    return paramFloat * (paramContext.getResources().getDisplayMetrics().densityDpi / 160.0F);
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
    int i = paramActivity.getInt(localStringBuilder.toString(), 0);
    paramActivity = new StringBuilder();
    paramActivity.append("retrieveKeyFromPref: ");
    paramActivity.append(i);
    b.a("PollfishUtilities", paramActivity.toString());
    return i;
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
      paramString = new StringBuilder();
      paramString.append("getBitmapSecurityHash Exception: ");
      paramString.append(paramBitmap);
      b.b("PollfishUtilities", paramString.toString());
      paramBitmap = "";
    }
    return paramBitmap.hashCode();
  }
  
  public static Animation a(View paramView, Position paramPosition, int paramInt, long paramLong)
  {
    if (paramView != null)
    {
      if (paramPosition == Position.TOP_LEFT) {
        paramPosition = new TranslateAnimation(-(paramView.getWidth() * 2), 0.0F, 0.0F, 0.0F);
      } else if (paramPosition == Position.BOTTOM_LEFT) {
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
      paramPosition.setDuration(paramLong);
      paramPosition.setStartOffset(paramInt);
      paramView.startAnimation(paramPosition);
      return paramPosition;
    }
    return null;
  }
  
  public static com.pollfish.a.b a(String paramString, com.pollfish.a.c paramC, a.d paramD, a.b paramB, Activity paramActivity, boolean paramBoolean)
  {
    b.a("PollfishUtilities", "parse Register Response ");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("hasAccepted: ");
    ((StringBuilder)localObject).append(paramBoolean);
    b.a("PollfishUtilities", ((StringBuilder)localObject).toString());
    if (paramString == null) {
      return null;
    }
    try
    {
      paramString = new JSONObject(paramString);
      localObject = paramString.getString("response_type");
      int j = paramString.getInt("intrusion");
      int k = paramString.getInt("width_percentage");
      int m = paramString.getInt("height_percentage");
      String str1 = paramString.getString("content");
      String str2 = paramString.getString("s_id");
      boolean bool2 = paramString.getBoolean("custom_indicator");
      String str3 = paramString.getString("indicator_image_left");
      String str4 = paramString.getString("indicator_image_right");
      String str5 = paramString.getString("mobile_data");
      String str6 = paramString.getString("assets");
      String str7 = paramString.getString("background_color");
      boolean bool1;
      try
      {
        bool1 = paramString.getBoolean("short_survey");
        try
        {
          i = paramString.getInt("survey_price");
        }
        catch (Exception paramString) {}
        localStringBuilder = new StringBuilder();
      }
      catch (Exception paramString)
      {
        bool1 = false;
      }
      StringBuilder localStringBuilder;
      localStringBuilder.append("error while parsing short surveys pair: ");
      localStringBuilder.append(paramString);
      b.b("PollfishUtilities", localStringBuilder.toString());
      int i = 0;
      paramString = new com.pollfish.a.b(str2, (String)localObject, j, str1, bool2, str3, str4, k, m, paramC.C(), paramD, str5, str6, paramB, paramActivity, str7, paramBoolean, bool1, i);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramC = new StringBuilder();
      paramC.append("error while parsing server register response: ");
      paramC.append(paramString);
      b.b("PollfishUtilities", paramC.toString());
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getOSversion:");
      localStringBuilder.append(localException);
      b.b("PollfishUtilities", localStringBuilder.toString());
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
      for (;;) {}
    }
    catch (InvalidKeyException paramString1)
    {
      for (;;) {}
    }
    throw new SignatureException("error building signature, invalid key HmacSHA256");
    throw new SignatureException("error building signature, no such algorithm in device HmacSHA256");
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
  
  public static JSONObject a(com.pollfish.a.c paramC)
  {
    JSONObject localJSONObject = new JSONObject();
    if (paramC != null) {
      try
      {
        localJSONObject.put("device_descr", paramC.d());
        if (paramC.r() != null) {
          localJSONObject.put("provider", paramC.r());
        }
        if (paramC.e() != null) {
          localJSONObject.put("provider_mcc", paramC.e());
        }
        if (paramC.f() != null) {
          localJSONObject.put("provider_mnc", paramC.f());
        }
        if (paramC.q() != null) {
          localJSONObject.put("nfc_enabled", paramC.q());
        }
        if (paramC.p() != null) {
          localJSONObject.put("nfc_exists", paramC.p());
        }
        if (paramC.L() != null) {
          localJSONObject.put("app_id", paramC.L());
        }
        localJSONObject.put("os", paramC.g());
        localJSONObject.put("os_ver", paramC.h());
        if (paramC.v() != null) {
          localJSONObject.put("scr_h", paramC.v());
        }
        if (paramC.w() != null) {
          localJSONObject.put("scr_w", paramC.w());
        }
        localJSONObject.put("manufacturer", paramC.j());
        if (paramC.H() != null) {
          localJSONObject.put("app_version", paramC.H());
        }
        if (paramC.I() != null) {
          localJSONObject.put("con_type", paramC.I());
        }
        if (paramC.t() != null)
        {
          if (paramC.x() != null) {
            localJSONObject.put("lat", paramC.x());
          }
          if (paramC.y() != null) {
            localJSONObject.put("lon", paramC.y());
          }
          if (paramC.z() != null) {
            localJSONObject.put("accuracy", paramC.z());
          }
        }
        if (paramC.i() != null) {
          localJSONObject.put("locale", paramC.i());
        }
        localJSONObject.put("scr_size", paramC.s());
        if (paramC.k() != null) {
          localJSONObject.put("is_roaming", paramC.k());
        }
        if (paramC.m() != null) {
          localJSONObject.put("accessibility_enabled", paramC.m());
        }
        if (paramC.n() != null) {
          localJSONObject.put("developer_enabled", paramC.n());
        }
        if (paramC.o() != null) {
          localJSONObject.put("install_non_market_apps", paramC.o());
        }
        localJSONObject.put("hardware_accelerated", paramC.F());
        return localJSONObject;
      }
      catch (Exception paramC)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("error: ");
        localStringBuilder.append(paramC);
        b.b("PollfishUtilities", localStringBuilder.toString());
      }
    }
    return localJSONObject;
  }
  
  public static void a(Activity paramActivity, String paramString, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("saveKeyInPref: ");
    localStringBuilder.append(paramInt);
    b.a("PollfishUtilities", localStringBuilder.toString());
    paramActivity = paramActivity.getBaseContext().getSharedPreferences("pollfish_pref", 0).edit();
    localStringBuilder = new StringBuilder();
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
    catch (Exception paramContext)
    {
      paramRunnable = new StringBuilder();
      paramRunnable.append("postRunnableInMainThread:");
      paramRunnable.append(paramContext);
      b.b("PollfishUtilities", paramRunnable.toString());
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("permissionsGranted:");
      localStringBuilder.append(paramActivity);
      b.b("PollfishUtilities", localStringBuilder.toString());
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
    catch (NoSuchMethodError paramView)
    {
      for (;;) {}
    }
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
      } else if (paramPosition == Position.BOTTOM_LEFT) {
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
      paramPosition.setDuration(paramLong);
      paramPosition.setStartOffset(paramInt);
      paramView.startAnimation(paramPosition);
      return paramPosition;
    }
    return null;
  }
  
  public static String b()
  {
    String str = Locale.getDefault().getLanguage();
    if ((!str.equalsIgnoreCase("")) && (str != null)) {
      return str;
    }
    return null;
  }
  
  public static boolean b(Activity paramActivity)
  {
    b.a("PollfishUtilities", "--isRunningOnEmulator()--");
    boolean bool2 = true;
    boolean bool1;
    try
    {
      paramActivity = Build.MODEL;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("model :");
      ((StringBuilder)localObject1).append(paramActivity);
      b.a("PollfishUtilities", ((StringBuilder)localObject1).toString());
      localObject1 = Build.PRODUCT;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("product: ");
      ((StringBuilder)localObject2).append((String)localObject1);
      b.a("PollfishUtilities", ((StringBuilder)localObject2).toString());
      localObject2 = Build.MANUFACTURER;
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("manufacturer: ");
      ((StringBuilder)localObject3).append((String)localObject2);
      b.a("PollfishUtilities", ((StringBuilder)localObject3).toString());
      localObject3 = Build.FINGERPRINT;
      Object localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("fingerprint: ");
      ((StringBuilder)localObject4).append((String)localObject2);
      b.a("PollfishUtilities", ((StringBuilder)localObject4).toString());
      localObject4 = Build.DEVICE;
      Object localObject5 = new StringBuilder();
      ((StringBuilder)localObject5).append("Device: ");
      ((StringBuilder)localObject5).append((String)localObject4);
      b.a("PollfishUtilities", ((StringBuilder)localObject5).toString());
      localObject5 = Build.BRAND;
      Object localObject6 = new StringBuilder();
      ((StringBuilder)localObject6).append("Brand: ");
      ((StringBuilder)localObject6).append((String)localObject5);
      b.a("PollfishUtilities", ((StringBuilder)localObject6).toString());
      localObject6 = Build.HARDWARE;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Hardware: ");
      localStringBuilder.append((String)localObject6);
      b.a("PollfishUtilities", localStringBuilder.toString());
      bool1 = bool2;
      if (!((String)localObject3).startsWith("generic"))
      {
        bool1 = bool2;
        if (!((String)localObject3).startsWith("unknown"))
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
                if (!((String)localObject2).contains("Genymotion"))
                {
                  bool1 = bool2;
                  if (!((String)localObject1).equals("sdk"))
                  {
                    bool1 = bool2;
                    if (!((String)localObject1).contains("_sdk"))
                    {
                      bool1 = bool2;
                      if (!((String)localObject1).contains("sdk_"))
                      {
                        bool1 = bool2;
                        if (!((String)localObject1).matches(".*_?sdk_?.*"))
                        {
                          bool1 = bool2;
                          if (!((String)localObject1).contains("google_sdk"))
                          {
                            bool1 = bool2;
                            if (!((String)localObject5).startsWith("generic"))
                            {
                              bool1 = bool2;
                              if (!((String)localObject4).startsWith("generic"))
                              {
                                bool1 = ((String)localObject6).equalsIgnoreCase("goldfish");
                                if (bool1) {
                                  bool1 = bool2;
                                } else {
                                  bool1 = false;
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
    }
    catch (Exception paramActivity)
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("isRunningOnEmulator exception: ");
      ((StringBuilder)localObject1).append(paramActivity);
      b.b("PollfishUtilities", ((StringBuilder)localObject1).toString());
      bool1 = bool2;
    }
    paramActivity = new StringBuilder();
    paramActivity.append("runningOnEmulator: ");
    paramActivity.append(bool1);
    b.a("PollfishUtilities", paramActivity.toString());
    return bool1;
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getAPIVersion e:");
      localStringBuilder.append(localException);
      b.b("PollfishUtilities", localStringBuilder.toString());
    }
    return "unknown";
  }
  
  public static boolean c(Activity paramActivity)
  {
    for (;;)
    {
      int i;
      try
      {
        ViewGroup localViewGroup = (ViewGroup)paramActivity.getWindow().getDecorView();
        localStringBuilder = null;
        paramActivity = localStringBuilder;
        if (localViewGroup != null)
        {
          paramActivity = new StringBuilder();
          paramActivity.append("DecorView getChildCount(): ");
          paramActivity.append(localViewGroup.getChildCount());
          b.a("PollfishUtilities", paramActivity.toString());
          i = 0;
          paramActivity = localStringBuilder;
          if (i < localViewGroup.getChildCount())
          {
            if (!(localViewGroup.getChildAt(i) instanceof ViewGroup)) {
              break label214;
            }
            b.a("PollfishUtilities", "found a child view with type ViewGroup");
            paramActivity = localViewGroup.getChildAt(i);
          }
        }
        if (paramActivity != null)
        {
          boolean bool = paramActivity.isHardwareAccelerated();
          return bool;
        }
        return false;
      }
      catch (Exception paramActivity)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("isTopViewHardwareAccelerated - Exception:");
        localStringBuilder.append(paramActivity);
        b.b("PollfishUtilities", localStringBuilder.toString());
        return false;
      }
      catch (ClassCastException paramActivity)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("isTopViewHardwareAccelerated - ClassCastException:");
        localStringBuilder.append(paramActivity);
        b.b("PollfishUtilities", localStringBuilder.toString());
        return false;
      }
      catch (NoSuchMethodError paramActivity)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("isTopViewHardwareAccelerated - NoSuchMethodError:");
        localStringBuilder.append(paramActivity);
        b.c("PollfishUtilities", localStringBuilder.toString());
        return false;
      }
      label214:
      i += 1;
    }
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getManufacturer e:");
      localStringBuilder.append(localException);
      b.b("PollfishUtilities", localStringBuilder.toString());
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getMCC:");
      localStringBuilder.append(paramActivity);
      b.b("PollfishUtilities", localStringBuilder.toString());
    }
    return null;
  }
  
  public static String e()
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getModelProductName e:");
      localStringBuilder.append(localException);
      b.b("PollfishUtilities", localStringBuilder.toString());
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getMNC:");
      localStringBuilder.append(paramActivity);
      b.b("PollfishUtilities", localStringBuilder.toString());
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("isRoaming:");
      localStringBuilder.append(paramActivity);
      b.b("PollfishUtilities", localStringBuilder.toString());
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("isAccessibilityEnabled:");
      localStringBuilder.append(paramActivity);
      b.c("PollfishUtilities", localStringBuilder.toString());
    }
    return null;
  }
  
  public static String h(Activity paramActivity)
  {
    StringBuilder localStringBuilder2;
    try
    {
      if (Settings.Global.getInt(paramActivity.getContentResolver(), "adb_enabled") == 1) {
        return "true";
      }
      return "false";
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      localStringBuilder2 = new StringBuilder();
    }
    catch (Exception localException)
    {
      localStringBuilder2 = new StringBuilder();
    }
    localStringBuilder2.append("isUsbDebuggindEnabled:");
    localStringBuilder2.append(localException);
    b.c("PollfishUtilities", localStringBuilder2.toString());
    try
    {
      if (Settings.Secure.getInt(paramActivity.getContentResolver(), "adb_enabled") == 1) {
        return "true";
      }
      return "false";
    }
    catch (Settings.SettingNotFoundException paramActivity)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("isUsbDebuggindEnabled:");
      localStringBuilder1.append(paramActivity);
      b.b("PollfishUtilities", localStringBuilder1.toString());
    }
    return null;
  }
  
  public static String i(Activity paramActivity)
  {
    StringBuilder localStringBuilder2;
    try
    {
      if (Settings.Global.getInt(paramActivity.getContentResolver(), "install_non_market_apps") == 1) {
        return "true";
      }
      return "false";
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      localStringBuilder2 = new StringBuilder();
    }
    catch (Exception localException)
    {
      localStringBuilder2 = new StringBuilder();
    }
    localStringBuilder2.append("isInstallNonMarkerAppsEnabled:");
    localStringBuilder2.append(localException);
    b.c("PollfishUtilities", localStringBuilder2.toString());
    try
    {
      if (Settings.Secure.getInt(paramActivity.getContentResolver(), "install_non_market_apps") == 1) {
        return "true";
      }
      return "false";
    }
    catch (Settings.SettingNotFoundException paramActivity)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("isInstallNonMarkerAppsEnabled:");
      localStringBuilder1.append(paramActivity);
      b.b("PollfishUtilities", localStringBuilder1.toString());
    }
    return null;
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
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      paramActivity = new StringBuilder();
      paramActivity.append("getIfNFCExists:");
      paramActivity.append(localNoClassDefFoundError);
      b.b("PollfishUtilities", paramActivity.toString());
      return "false";
    }
    catch (Exception localException)
    {
      for (;;)
      {
        paramActivity = new StringBuilder();
      }
    }
    catch (NoSuchMethodError paramActivity)
    {
      for (;;)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("getIfNFCExists:");
        localStringBuilder.append(paramActivity);
        b.c("PollfishUtilities", localStringBuilder.toString());
      }
    }
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
    catch (NoClassDefFoundError localNoClassDefFoundError1)
    {
      paramActivity = new StringBuilder();
      String str = "getIfNFCExists:";
      NoClassDefFoundError localNoClassDefFoundError2 = localNoClassDefFoundError1;
      localObject = str;
      paramActivity.append((String)localObject);
      paramActivity.append(localNoClassDefFoundError2);
      b.b("PollfishUtilities", paramActivity.toString());
      return "false";
    }
    catch (Exception localException)
    {
      for (;;)
      {
        paramActivity = new StringBuilder();
        Object localObject = "getIfNFCEnabled:";
      }
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;)
      {
        paramActivity = new StringBuilder();
      }
    }
  }
  
  public static String l(Activity paramActivity)
  {
    b.a("PollfishUtilities", "getAppPackageName");
    try
    {
      paramActivity = paramActivity.getApplicationContext().getPackageName();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("packageName: ");
      localStringBuilder.append(paramActivity);
      b.a("PollfishUtilities", localStringBuilder.toString());
      return paramActivity;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      paramActivity = new StringBuilder();
    }
    catch (Exception localException)
    {
      paramActivity = new StringBuilder();
    }
    paramActivity.append("getAppPackageName:");
    paramActivity.append(localException);
    b.b("PollfishUtilities", paramActivity.toString());
    return null;
  }
  
  public static String m(Activity paramActivity)
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
      paramActivity = paramActivity.toString();
      return paramActivity;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      paramActivity = new StringBuilder();
      paramActivity.append("getInstalledApps:");
      paramActivity.append(localNoClassDefFoundError);
      b.b("PollfishUtilities", paramActivity.toString());
      return null;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        paramActivity = new StringBuilder();
      }
    }
  }
  
  public static String n(Activity paramActivity)
  {
    try
    {
      paramActivity = ((WifiManager)paramActivity.getSystemService("wifi")).getScanResults().toString();
      return paramActivity;
    }
    catch (NoClassDefFoundError paramActivity)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("getWifis:");
      localStringBuilder.append(paramActivity);
      b.b("PollfishUtilities", localStringBuilder.toString());
      return null;
    }
    catch (Exception paramActivity)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getWifis:");
      localStringBuilder.append(paramActivity);
      b.c("PollfishUtilities", localStringBuilder.toString());
    }
    return null;
  }
  
  public static String o(Activity paramActivity)
  {
    b.a("PollfishUtilities", "getMacAddress");
    if (paramActivity.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", paramActivity.getPackageName()) == 0)
    {
      b.a("PollfishUtilities", "ACCESS_WIFI_STATE permission in place");
      try
      {
        localObject = ((WifiManager)paramActivity.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        paramActivity = (Activity)localObject;
        if (localObject == null) {
          paramActivity = "noMac";
        }
        return paramActivity;
      }
      catch (Exception paramActivity)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("noMac exception: ");
        ((StringBuilder)localObject).append(paramActivity);
        b.b("PollfishUtilities", ((StringBuilder)localObject).toString());
      }
    }
    for (;;)
    {
      return "noMac";
      b.a("PollfishUtilities", "No ACCESS_WIFI_STATE permission in place");
    }
  }
  
  public static String p(Activity paramActivity)
  {
    try
    {
      paramActivity = ((TelephonyManager)paramActivity.getSystemService("phone")).getNetworkOperatorName();
      if (paramActivity == null) {
        return null;
      }
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getNetworkOperatorName e:");
      localStringBuilder.append(paramActivity);
      b.b("PollfishUtilities", localStringBuilder.toString());
    }
    return null;
  }
  
  public static String q(Activity paramActivity)
  {
    try
    {
      localObject = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0);
      paramActivity = ((PackageInfo)localObject).versionName;
      int i = ((PackageInfo)localObject).versionCode;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("versionName:");
      ((StringBuilder)localObject).append(paramActivity);
      b.a("PollfishUtilities", ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("versionCode:");
      ((StringBuilder)localObject).append(i);
      b.a("PollfishUtilities", ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("getAppVersion:");
      ((StringBuilder)localObject).append(paramActivity);
      ((StringBuilder)localObject).append(i);
      b.a("PollfishUtilities", ((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramActivity);
      ((StringBuilder)localObject).append(i);
      paramActivity = ((StringBuilder)localObject).toString();
      return paramActivity;
    }
    catch (Exception paramActivity)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("getAppVersion exception:");
      ((StringBuilder)localObject).append(paramActivity);
      b.a("PollfishUtilities", ((StringBuilder)localObject).toString());
    }
    return null;
  }
  
  public static String r(Activity paramActivity)
  {
    b.a("PollfishUtilities", "getDeviceIMEI");
    try
    {
      if (paramActivity.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramActivity.getPackageName()) == 0)
      {
        b.a("PollfishUtilities", "READ_PHONE_STATE permission at place");
        paramActivity = ((TelephonyManager)paramActivity.getSystemService("phone")).getDeviceId();
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("getDeviceIME:");
        localStringBuilder.append(paramActivity);
        b.a("PollfishUtilities", localStringBuilder.toString());
        return paramActivity;
      }
      b.a("PollfishUtilities", "READ_PHONE_STATE permission not at place");
      return null;
    }
    catch (Exception paramActivity)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("getDeviceIME exception:");
      localStringBuilder.append(paramActivity);
      b.a("PollfishUtilities", localStringBuilder.toString());
    }
    return null;
  }
  
  public static String s(Activity paramActivity)
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
          localObject1 = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getAllNetworkInfo();
          int j = localObject1.length;
          i = 0;
          paramActivity = null;
          if (i < j)
          {
            Object localObject2 = localObject1[i];
            if (localObject2.isConnected()) {
              paramActivity = localObject2.getTypeName();
            }
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("getConnectionType:");
            ((StringBuilder)localObject1).append(paramActivity);
            b.a("PollfishUtilities", ((StringBuilder)localObject1).toString());
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
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("getDeviceIME exception:");
        ((StringBuilder)localObject1).append(paramActivity);
        b.a("PollfishUtilities", ((StringBuilder)localObject1).toString());
        return null;
      }
      i += 1;
    }
  }
  
  public static Location t(Activity paramActivity)
  {
    b.a("PollfishUtilities", "getUserLocationFromManager");
    Object localObject = paramActivity.getPackageManager();
    LocationManager localLocationManager;
    if ((((PackageManager)localObject).checkPermission("android.permission.ACCESS_COARSE_LOCATION", paramActivity.getPackageName()) != 0) && (((PackageManager)localObject).checkPermission("android.permission.ACCESS_FINE_LOCATION", paramActivity.getPackageName()) != 0))
    {
      b.a("PollfishUtilities", "No location permission at place");
      paramActivity = null;
    }
    else
    {
      localLocationManager = (LocationManager)paramActivity.getSystemService("location");
      b.a("PollfishUtilities", "At least one location permission at place");
    }
    try
    {
      b.a("PollfishUtilities", "getting last known location from LocationManager.GPS_PROVIDER");
      paramActivity = localLocationManager.getLastKnownLocation("gps");
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    b.b("PollfishUtilities", "Exception in getting last known location from LocationManager.GPS_PROVIDER");
    paramActivity = null;
    localObject = paramActivity;
    if (paramActivity == null) {}
    try
    {
      b.a("PollfishUtilities", "getting last known location from LocationManager.NETWORK_PROVIDER");
      localObject = localLocationManager.getLastKnownLocation("network");
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    b.b("PollfishUtilities", "Exception in getting known knwon location from LocationManager.NETWORK_PROVIDER");
    localObject = paramActivity;
    if (localObject == null) {}
    try
    {
      b.a("PollfishUtilities", "getting last known location from LocationManager.PASSIVE_PROVIDER");
      paramActivity = localLocationManager.getLastKnownLocation("passive");
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    b.b("PollfishUtilities", "Exception in getting last knwon location from LocationManager.NETWORK_PROVIDER");
    paramActivity = (Activity)localObject;
    if (paramActivity == null)
    {
      b.a("PollfishUtilities", "Location == null");
      return null;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Location-> lat: ");
    ((StringBuilder)localObject).append(paramActivity.getLatitude());
    ((StringBuilder)localObject).append(" lon: ");
    ((StringBuilder)localObject).append(paramActivity.getLongitude());
    b.a("PollfishUtilities", ((StringBuilder)localObject).toString());
    return paramActivity;
  }
  
  public static Point u(Activity paramActivity)
  {
    for (;;)
    {
      try
      {
        localObject = ((WindowManager)paramActivity.getSystemService("window")).getDefaultDisplay();
        paramActivity = new Point();
      }
      catch (Exception paramActivity)
      {
        int i;
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("getScreenDimensions e:");
        ((StringBuilder)localObject).append(paramActivity);
        b.b("PollfishUtilities", ((StringBuilder)localObject).toString());
        return null;
      }
      try
      {
        ((Display)localObject).getSize(paramActivity);
      }
      catch (NoSuchMethodError localNoSuchMethodError) {}catch (Exception localException) {}
    }
    paramActivity.x = ((Display)localObject).getWidth();
    for (i = ((Display)localObject).getHeight();; i = ((Display)localObject).getHeight())
    {
      paramActivity.y = i;
      break;
      paramActivity.x = ((Display)localObject).getWidth();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("getScreenDimensions screen width:");
    ((StringBuilder)localObject).append(paramActivity.x);
    ((StringBuilder)localObject).append("  screen height:");
    ((StringBuilder)localObject).append(paramActivity.y);
    b.a("PollfishUtilities", ((StringBuilder)localObject).toString());
    return paramActivity;
  }
  
  /* Error */
  public static String v(Activity paramActivity)
  {
    // Byte code:
    //   0: new 20	android/util/DisplayMetrics
    //   3: dup
    //   4: invokespecial 932	android/util/DisplayMetrics:<init>	()V
    //   7: astore 10
    //   9: aload_0
    //   10: invokevirtual 936	android/app/Activity:getWindowManager	()Landroid/view/WindowManager;
    //   13: invokeinterface 910 1 0
    //   18: astore_0
    //   19: aload_0
    //   20: aload 10
    //   22: invokevirtual 940	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   25: new 912	android/graphics/Point
    //   28: dup
    //   29: invokespecial 913	android/graphics/Point:<init>	()V
    //   32: astore 11
    //   34: aload 10
    //   36: getfield 943	android/util/DisplayMetrics:widthPixels	I
    //   39: istore 8
    //   41: aload 10
    //   43: getfield 946	android/util/DisplayMetrics:heightPixels	I
    //   46: istore 9
    //   48: aload_0
    //   49: aload 11
    //   51: invokevirtual 919	android/view/Display:getSize	(Landroid/graphics/Point;)V
    //   54: aload 11
    //   56: getfield 922	android/graphics/Point:x	I
    //   59: istore 7
    //   61: aload 11
    //   63: getfield 925	android/graphics/Point:y	I
    //   66: istore 8
    //   68: goto +66 -> 134
    //   71: astore_0
    //   72: goto +8 -> 80
    //   75: astore_0
    //   76: iload 8
    //   78: istore 7
    //   80: new 47	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   87: astore 11
    //   89: aload 11
    //   91: ldc_w 948
    //   94: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: pop
    //   98: aload 11
    //   100: aload_0
    //   101: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: ldc 72
    //   107: aload 11
    //   109: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: invokestatic 617	com/pollfish/f/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   115: iload 9
    //   117: istore 8
    //   119: goto +15 -> 134
    //   122: ldc 72
    //   124: ldc_w 950
    //   127: invokestatic 617	com/pollfish/f/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   130: iload 9
    //   132: istore 8
    //   134: new 47	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   141: astore_0
    //   142: aload_0
    //   143: ldc_w 952
    //   146: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: pop
    //   150: aload_0
    //   151: iload 7
    //   153: invokevirtual 70	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: ldc 72
    //   159: aload_0
    //   160: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: new 47	java/lang/StringBuilder
    //   169: dup
    //   170: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   173: astore_0
    //   174: aload_0
    //   175: ldc_w 954
    //   178: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: pop
    //   182: aload_0
    //   183: iload 8
    //   185: invokevirtual 70	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   188: pop
    //   189: ldc 72
    //   191: aload_0
    //   192: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   195: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   198: aload 10
    //   200: getfield 24	android/util/DisplayMetrics:densityDpi	I
    //   203: istore 9
    //   205: iload 7
    //   207: i2d
    //   208: dstore_3
    //   209: iload 9
    //   211: i2d
    //   212: dstore_1
    //   213: dload_3
    //   214: invokestatic 960	java/lang/Double:isNaN	(D)Z
    //   217: pop
    //   218: dload_1
    //   219: invokestatic 960	java/lang/Double:isNaN	(D)Z
    //   222: pop
    //   223: dload_3
    //   224: dload_1
    //   225: ddiv
    //   226: dstore_3
    //   227: iload 8
    //   229: i2d
    //   230: dstore 5
    //   232: dload 5
    //   234: invokestatic 960	java/lang/Double:isNaN	(D)Z
    //   237: pop
    //   238: dload_1
    //   239: invokestatic 960	java/lang/Double:isNaN	(D)Z
    //   242: pop
    //   243: dload 5
    //   245: dload_1
    //   246: ddiv
    //   247: dstore_1
    //   248: dload_3
    //   249: ldc2_w 961
    //   252: invokestatic 968	java/lang/Math:pow	(DD)D
    //   255: dload_1
    //   256: ldc2_w 961
    //   259: invokestatic 968	java/lang/Math:pow	(DD)D
    //   262: dadd
    //   263: invokestatic 972	java/lang/Math:sqrt	(D)D
    //   266: dstore_1
    //   267: new 47	java/lang/StringBuilder
    //   270: dup
    //   271: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   274: astore_0
    //   275: aload_0
    //   276: ldc_w 974
    //   279: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: aload_0
    //   284: aload 10
    //   286: getfield 977	android/util/DisplayMetrics:xdpi	F
    //   289: invokevirtual 980	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   292: pop
    //   293: ldc 72
    //   295: aload_0
    //   296: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   299: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   302: new 47	java/lang/StringBuilder
    //   305: dup
    //   306: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   309: astore_0
    //   310: aload_0
    //   311: ldc_w 982
    //   314: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: pop
    //   318: aload_0
    //   319: aload 10
    //   321: getfield 985	android/util/DisplayMetrics:ydpi	F
    //   324: invokevirtual 980	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   327: pop
    //   328: ldc 72
    //   330: aload_0
    //   331: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   334: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   337: new 47	java/lang/StringBuilder
    //   340: dup
    //   341: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   344: astore_0
    //   345: aload_0
    //   346: ldc_w 987
    //   349: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: pop
    //   353: aload_0
    //   354: aload 10
    //   356: getfield 31	android/util/DisplayMetrics:density	F
    //   359: invokevirtual 980	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   362: pop
    //   363: ldc 72
    //   365: aload_0
    //   366: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   369: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   372: new 47	java/lang/StringBuilder
    //   375: dup
    //   376: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   379: astore_0
    //   380: aload_0
    //   381: ldc_w 989
    //   384: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: pop
    //   388: aload_0
    //   389: dload_1
    //   390: invokevirtual 895	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   393: pop
    //   394: ldc 72
    //   396: aload_0
    //   397: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   400: invokestatic 77	com/pollfish/f/b:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   403: dload_1
    //   404: invokestatic 992	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   407: areturn
    //   408: astore_0
    //   409: new 47	java/lang/StringBuilder
    //   412: dup
    //   413: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   416: astore 10
    //   418: aload 10
    //   420: ldc_w 994
    //   423: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: pop
    //   427: aload 10
    //   429: aload_0
    //   430: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   433: pop
    //   434: ldc 72
    //   436: aload 10
    //   438: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   441: invokestatic 101	com/pollfish/f/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   444: ldc -30
    //   446: areturn
    //   447: astore_0
    //   448: goto +7 -> 455
    //   451: astore_0
    //   452: goto -330 -> 122
    //   455: iload 8
    //   457: istore 7
    //   459: goto -337 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	462	0	paramActivity	Activity
    //   212	192	1	d1	double
    //   208	41	3	d2	double
    //   230	14	5	d3	double
    //   59	399	7	i	int
    //   39	417	8	j	int
    //   46	164	9	k	int
    //   7	430	10	localObject1	Object
    //   32	76	11	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   61	68	71	java/lang/Exception
    //   48	61	75	java/lang/Exception
    //   0	48	408	java/lang/Exception
    //   80	115	408	java/lang/Exception
    //   122	130	408	java/lang/Exception
    //   134	205	408	java/lang/Exception
    //   248	403	408	java/lang/Exception
    //   48	61	447	java/lang/NoSuchMethodError
    //   61	68	451	java/lang/NoSuchMethodError
  }
}
