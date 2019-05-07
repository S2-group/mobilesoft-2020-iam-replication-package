package com.eco.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class b
{
  public b() {}
  
  public static int a()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static int a(int paramInt)
  {
    if (paramInt < 480) {
      return 30;
    }
    if (paramInt <= 720) {
      return 40;
    }
    if (paramInt < 1080) {
      return 50;
    }
    return 60;
  }
  
  public static int a(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      Log.e("VersionInfo", "Exception", paramContext);
    }
    return 0;
  }
  
  public static String a(Context paramContext, Class<? extends Application> paramClass, String paramString)
  {
    return q.a(paramContext, paramClass, paramString);
  }
  
  public static void a(Activity paramActivity)
  {
    View localView = paramActivity.getCurrentFocus();
    if (localView != null) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).hideSoftInputFromWindow(localView.getWindowToken(), 0);
    }
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {
      return;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(paramUri, "application/vnd.android.package-archive");
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static void a(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static String b()
  {
    return Build.MODEL;
  }
  
  public static String b(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(paramInt >> 8 & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(paramInt >> 16 & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(paramInt >> 24 & 0xFF);
    return localStringBuilder.toString();
  }
  
  /* Error */
  public static String b(Context paramContext)
  {
    // Byte code:
    //   0: ldc -92
    //   2: astore_2
    //   3: aload_0
    //   4: invokevirtual 27	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: aload_0
    //   8: invokevirtual 31	android/content/Context:getPackageName	()Ljava/lang/String;
    //   11: iconst_0
    //   12: invokevirtual 37	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   15: getfield 167	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   18: astore_0
    //   19: aload_0
    //   20: ifnull +15 -> 35
    //   23: aload_0
    //   24: astore_1
    //   25: aload_0
    //   26: invokevirtual 170	java/lang/String:length	()I
    //   29: ifgt +23 -> 52
    //   32: goto +3 -> 35
    //   35: ldc -84
    //   37: areturn
    //   38: astore_1
    //   39: aload_2
    //   40: astore_0
    //   41: ldc 44
    //   43: ldc 46
    //   45: aload_1
    //   46: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   49: pop
    //   50: aload_0
    //   51: astore_1
    //   52: aload_1
    //   53: areturn
    //   54: astore_1
    //   55: goto -14 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	paramContext	Context
    //   24	1	1	localContext1	Context
    //   38	8	1	localException1	Exception
    //   51	2	1	localContext2	Context
    //   54	1	1	localException2	Exception
    //   2	38	2	str	String
    // Exception table:
    //   from	to	target	type
    //   3	19	38	java/lang/Exception
    //   25	32	54	java/lang/Exception
  }
  
  public static void b(Activity paramActivity)
  {
    View localView = paramActivity.getCurrentFocus();
    if (localView != null) {
      ((InputMethodManager)paramActivity.getSystemService("input_method")).showSoftInput(localView, 0);
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramString.startsWith("http")) {
        return;
      }
      a(paramContext, Uri.fromFile(new File(paramString)));
      return;
    }
  }
  
  public static String c()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String c(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return null;
    }
    return paramContext.getDeviceId();
  }
  
  public static float d(Context paramContext)
  {
    return paramContext.getApplicationContext().getResources().getDisplayMetrics().density;
  }
  
  public static String d()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static final Display e(Context paramContext)
  {
    return ((Activity)paramContext).getWindowManager().getDefaultDisplay();
  }
  
  public static String e()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static DisplayMetrics f(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static void g(Context paramContext)
  {
    CookieManager localCookieManager = CookieManager.getInstance();
    if (Build.VERSION.SDK_INT >= 21)
    {
      localCookieManager.removeAllCookies(null);
      return;
    }
    CookieSyncManager.createInstance(paramContext);
    localCookieManager.removeAllCookie();
  }
  
  public static String h(Context paramContext)
  {
    Object localObject = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localObject != null) && (((NetworkInfo)localObject).isConnected()))
    {
      if (((NetworkInfo)localObject).getType() == 0) {
        try
        {
          InetAddress localInetAddress;
          do
          {
            paramContext = NetworkInterface.getNetworkInterfaces();
            while (!((Enumeration)localObject).hasMoreElements())
            {
              if (!paramContext.hasMoreElements()) {
                break;
              }
              localObject = ((NetworkInterface)paramContext.nextElement()).getInetAddresses();
            }
            localInetAddress = (InetAddress)((Enumeration)localObject).nextElement();
          } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
          paramContext = localInetAddress.getHostAddress();
          return paramContext;
        }
        catch (SocketException paramContext)
        {
          ThrowableExtension.printStackTrace(paramContext);
        }
      }
      if (((NetworkInfo)localObject).getType() == 1) {
        return b(((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getIpAddress());
      }
    }
    return null;
  }
  
  public static boolean i(Context paramContext)
  {
    return NotificationManagerCompat.from(paramContext).areNotificationsEnabled();
  }
  
  public static boolean j(Context paramContext)
  {
    paramContext = (PowerManager)paramContext.getSystemService("power");
    if (paramContext != null) {
      return paramContext.isScreenOn();
    }
    return false;
  }
}
