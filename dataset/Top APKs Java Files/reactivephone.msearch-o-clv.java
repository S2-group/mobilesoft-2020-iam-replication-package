package o;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class clv
{
  private static String a = "ru, az, be, ka, kk, lt, lv, tk, uk, uz";
  private static Boolean b = null;
  
  public static int a(int paramInt)
  {
    float[] arrayOfFloat = new float[3];
    Color.colorToHSV(paramInt, arrayOfFloat);
    arrayOfFloat[2] *= 0.75F;
    return Color.HSVToColor(arrayOfFloat);
  }
  
  public static int a(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static int a(Context paramContext, int paramInt)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * paramInt);
  }
  
  public static String a()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 24) {
        return paramContext.getResources().getConfiguration().getLocales().get(0).toString();
      }
      paramContext = paramContext.getResources().getConfiguration().locale.toString();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "en_US";
  }
  
  private static String a(String paramString)
  {
    String str;
    if ((paramString == null) || (paramString.length() == 0)) {
      str = "";
    }
    char c;
    do
    {
      return str;
      c = paramString.charAt(0);
      str = paramString;
    } while (Character.isUpperCase(c));
    return Character.toUpperCase(c) + paramString.substring(1);
  }
  
  public static boolean a(Application paramApplication)
  {
    if (paramApplication == null) {
      return false;
    }
    if (b == null)
    {
      paramApplication = paramApplication.getPackageManager();
      if (paramApplication == null) {
        return false;
      }
      paramApplication = paramApplication.getInstalledPackages(0).iterator();
      while (paramApplication.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramApplication.next();
        if ((localPackageInfo != null) && (("com.google.market".equals(localPackageInfo.packageName)) || ("com.android.vending".equals(localPackageInfo.packageName)))) {
          b = Boolean.valueOf(true);
        }
      }
    }
    return (b != null) && (b.booleanValue());
  }
  
  public static int b(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static String b()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return a(str2);
    }
    return a(str1) + " " + str2;
  }
  
  public static String b(Context paramContext)
  {
    String str = a(paramContext);
    if (clq.b(paramContext)) {
      str = "en_IN";
    }
    return str;
  }
  
  public static String c(Context paramContext)
  {
    final SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    String str = localSharedPreferences.getString("pref_google_ads_id", cmm.a);
    if (cmm.i(str)) {
      AsyncTask.execute(new Runnable()
      {
        public final void run()
        {
          try
          {
            AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(clv.this);
            if ((localInfo != null) && (!cmm.i(localInfo.getId())))
            {
              localSharedPreferences.edit().putString("pref_google_ads_id", localInfo.getId()).apply();
              return;
            }
            localSharedPreferences.edit().putString("pref_google_ads_id", "undefined").apply();
            return;
          }
          catch (Exception localException)
          {
            localSharedPreferences.edit().putString("pref_google_ads_id", "undefined").apply();
          }
        }
      });
    }
    while (!str.equals("undefined")) {
      return str;
    }
    return cmm.a;
  }
  
  public static String d(Context paramContext)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    String str = localSharedPreferences.getString("android_device_id", cmm.a);
    Object localObject = str;
    if (cmm.i(str)) {}
    try
    {
      localObject = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      paramContext = (Context)localObject;
      if (cmm.i((String)localObject)) {
        paramContext = "undefined";
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = "undefined";
      }
    }
    localSharedPreferences.edit().putString("android_device_id", paramContext).commit();
    localObject = paramContext;
    paramContext = (Context)localObject;
    if (((String)localObject).equals("undefined")) {
      paramContext = cmm.a;
    }
    return paramContext;
  }
  
  public static boolean e(Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      boolean bool1 = bool2;
      if (i != -1)
      {
        bool1 = bool2;
        if (i > PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_last_version_code", 0)) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        int i = -1;
      }
    }
  }
  
  public static boolean f(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration().locale.getLanguage();
    return a.contains(paramContext);
  }
  
  public static boolean g(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration().locale.getCountry().toUpperCase();
    return (!cmm.i(paramContext)) && ((paramContext.equals("RU")) || (paramContext.equals("KK")) || (paramContext.equals("BY")));
  }
  
  public static boolean h(Context paramContext)
  {
    return paramContext.getResources().getBoolean(2131492866);
  }
  
  public static boolean i(Context paramContext)
  {
    return paramContext.getResources().getBoolean(2131492865);
  }
  
  public static String j(Context paramContext)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    String str = localSharedPreferences.getString("user_uuid", "");
    paramContext = str;
    if (cmm.i(str))
    {
      paramContext = UUID.randomUUID().toString();
      localSharedPreferences.edit().putString("user_uuid", paramContext).apply();
    }
    return paramContext;
  }
  
  public static String k(Context paramContext)
  {
    Object localObject = "1.9.8";
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      localObject = paramContext;
      if (cmm.i(paramContext)) {
        localObject = "1.9.8";
      }
      return localObject;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = (Context)localObject;
      }
    }
  }
}
