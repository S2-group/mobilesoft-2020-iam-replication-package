package com.tunnelbear.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class ds
{
  private static final File a = new File("/dev/tun");
  private static final File b = new File("/dev/net/tun");
  
  public static int a(int paramInt1, int paramInt2, double paramDouble)
  {
    int i = (int)(256.0D * paramDouble);
    int j = 256 - i;
    return i * (paramInt2 & 0xFF00) + j * (paramInt1 & 0xFF00) >> 8 & 0xFF00 | -16777216 + ((paramInt1 & 0xFF00FF) * j + (paramInt2 & 0xFF00FF) * i >> 8 & 0xFF00FF);
  }
  
  public static long a(Long paramLong)
  {
    if (paramLong == null) {}
    while (paramLong.longValue() < 0L) {
      return 0L;
    }
    return paramLong.longValue() / 1048576L;
  }
  
  public static Animation a(long paramLong)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, -1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(paramLong);
    localTranslateAnimation.setInterpolator(new LinearInterpolator());
    return localTranslateAnimation;
  }
  
  public static File a(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    paramContext = new File(paramContext.getFilesDir(), paramString2 + ".zip");
    paramString2 = new ZipOutputStream(new FileOutputStream(paramContext));
    paramString2.putNextEntry(new ZipEntry("data.txt"));
    paramString1 = paramString1.getBytes();
    paramString2.write(paramString1, 0, paramString1.length);
    paramString2.closeEntry();
    paramString2.close();
    return paramContext;
  }
  
  public static String a(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      int i = paramInputStream.read();
      if (i == -1) {
        break;
      }
      localStringBuilder.append((char)i);
    }
    return localStringBuilder.toString();
  }
  
  public static List<PackageInfo> a(Context paramContext, int paramInt)
  {
    return paramContext.getPackageManager().getInstalledPackages(paramInt);
  }
  
  public static void a() {}
  
  public static void a(Activity paramActivity)
  {
    paramActivity = paramActivity.getResources().getDisplayMetrics();
    int i = paramActivity.widthPixels;
    float f = paramActivity.density;
  }
  
  public static void a(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      TBQuickSettingsTileService.requestListeningState(paramContext, new ComponentName(paramContext, "com.tunnelbear.android.TBQuickSettingsTileService"));
    }
  }
  
  public static void a(Context paramContext, long paramLong)
  {
    if (bi.a(paramContext).a("OPTIONS_HAPTIC")) {
      ((Vibrator)paramContext.getSystemService("vibrator")).vibrate(paramLong);
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    paramContext = Toast.makeText(paramContext, paramString, 0);
    paramContext.setGravity(17, 0, 0);
    paramContext.show();
  }
  
  public static boolean a(Context paramContext, NetworkInfo paramNetworkInfo)
  {
    if ((paramNetworkInfo != null) && (paramNetworkInfo.getType() == 1)) {
      return c(paramContext, c(paramContext));
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      arrayOfString = Build.SUPPORTED_ABIS;
      j = arrayOfString.length;
      i = 0;
      if (i < j) {
        if (!arrayOfString[i].equalsIgnoreCase(paramString)) {}
      }
    }
    while ((Build.CPU_ABI.equalsIgnoreCase(paramString)) || (Build.CPU_ABI2.equalsIgnoreCase(paramString)))
    {
      for (;;)
      {
        String[] arrayOfString;
        int j;
        int i;
        return true;
        i += 1;
      }
      return false;
    }
    return false;
  }
  
  public static int b(Activity paramActivity)
  {
    int i = d.a(paramActivity);
    if (i != 0) {}
    try
    {
      d.a(i, paramActivity).show();
      return i;
    }
    catch (Exception paramActivity)
    {
      Log.e("Error: GooglePlayServiceUtil: ", String.valueOf(paramActivity));
    }
    return i;
  }
  
  public static Animation b(long paramLong)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(paramLong);
    localTranslateAnimation.setInterpolator(new LinearInterpolator());
    return localTranslateAnimation;
  }
  
  public static Boolean b(Context paramContext)
  {
    if (((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo() != null) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    if ((TbearMainActivity.M()) || (RegistrationActivity.a()))
    {
      if (paramString.length() < 28) {
        Toast.makeText(paramContext, paramString, 0).show();
      }
      for (;;)
      {
        ba.h(paramContext);
        return;
        Toast.makeText(paramContext, paramString, 1).show();
      }
    }
    ba.a(paramContext, paramString);
  }
  
  public static boolean b()
  {
    boolean bool = false;
    int i = Character.getDirectionality(Locale.getDefault().getDisplayName().charAt(0));
    if ((i == 1) || (i == 2)) {
      bool = true;
    }
    return bool;
  }
  
  public static Animation c(long paramLong)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(paramLong);
    localTranslateAnimation.setInterpolator(new LinearInterpolator());
    return localTranslateAnimation;
  }
  
  public static String c(Context paramContext)
  {
    return ((WifiManager)paramContext.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getSSID();
  }
  
  public static boolean c()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    paramContext = ((WifiManager)paramContext.getApplicationContext().getSystemService("wifi")).getConfiguredNetworks();
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        WifiConfiguration localWifiConfiguration = (WifiConfiguration)paramContext.next();
        if ((!TextUtils.isEmpty(localWifiConfiguration.SSID)) && (localWifiConfiguration.SSID.equals(paramString))) {
          return (localWifiConfiguration.allowedKeyManagement.get(0)) && ((localWifiConfiguration.allowedAuthAlgorithms.cardinality() == 0) || (localWifiConfiguration.allowedAuthAlgorithms.get(1)));
        }
      }
    }
    return false;
  }
  
  public static NetworkInfo d(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
  }
  
  public static Animation d(long paramLong)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, -1.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(paramLong);
    localTranslateAnimation.setInterpolator(new LinearInterpolator());
    return localTranslateAnimation;
  }
  
  public static String d(Context paramContext, String paramString)
  {
    TextView localTextView = new TextView(paramContext);
    localTextView.setText(paramString);
    localTextView.setTextSize(19.0F);
    Rect localRect = new Rect();
    localTextView.getPaint().getTextBounds(paramString, 0, paramString.length(), localRect);
    int i = localRect.width();
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    float f = i / (localDisplayMetrics.densityDpi / 160.0F);
    paramContext = paramString;
    if (f > 250.0F)
    {
      paramContext = paramString;
      if (!paramString.isEmpty())
      {
        while (f > 230.0F)
        {
          paramString = paramString.substring(0, paramString.length() - 1);
          localTextView.setText(paramString);
          localTextView.getPaint().getTextBounds(paramString, 0, paramString.length(), localRect);
          f = localRect.width() / (localDisplayMetrics.densityDpi / 160.0F);
        }
        paramContext = paramString + "...";
      }
    }
    return paramContext;
  }
  
  public static boolean d()
  {
    return false;
  }
  
  public static View.OnTouchListener e()
  {
    return new dt();
  }
  
  public static boolean e(Context paramContext)
  {
    return (paramContext.getApplicationInfo().flags & 0x40000) != 0;
  }
  
  public static boolean f(Context paramContext)
  {
    String str = ((WifiManager)paramContext.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getSSID();
    if (str.equals("<unknown ssid>")) {
      return true;
    }
    paramContext = bi.a(paramContext).Q().iterator();
    while (paramContext.hasNext()) {
      if (((String)paramContext.next()).equals(str)) {
        return true;
      }
    }
    return false;
  }
}
