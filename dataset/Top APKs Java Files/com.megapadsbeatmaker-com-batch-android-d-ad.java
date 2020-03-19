package com.batch.android.d;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ad
{
  public ad() {}
  
  public static String A(Context paramContext)
  {
    if (!l.a("android.permission.BLUETOOTH", paramContext)) {
      return null;
    }
    try
    {
      paramContext = BluetoothAdapter.getDefaultAdapter().getName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String B(Context paramContext)
  {
    if (!l.a("android.permission.BLUETOOTH", paramContext)) {
      return null;
    }
    try
    {
      paramContext = BluetoothAdapter.getDefaultAdapter().getAddress();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String C(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = P(paramContext).getTypeName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String D(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = P(paramContext).getSubtypeName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static NetworkInfo.State E(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return NetworkInfo.State.UNKNOWN;
    }
    try
    {
      paramContext = P(paramContext).getState();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return NetworkInfo.State.UNKNOWN;
  }
  
  public static NetworkInfo.DetailedState F(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = P(paramContext).getDetailedState();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static Boolean G(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      boolean bool = P(paramContext).isRoaming();
      return Boolean.valueOf(bool);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static List<ApplicationInfo> H(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static int I(Context paramContext)
  {
    try
    {
      int i = Q(paramContext).y;
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return 0;
  }
  
  public static int J(Context paramContext)
  {
    try
    {
      int i = Q(paramContext).x;
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return 0;
  }
  
  public static int K(Context paramContext)
  {
    try
    {
      int i = paramContext.getResources().getConfiguration().orientation;
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return 0;
  }
  
  public static Integer L(Context paramContext)
  {
    try
    {
      paramContext = M(paramContext);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.getActiveNetworkInfo();
      if (paramContext != null)
      {
        if (!paramContext.isConnected()) {
          return null;
        }
        int i = paramContext.getType();
        if ((i != 1) && (i != 8) && (i != 9) && (i != 6)) {
          return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
      }
      return null;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private static ConnectivityManager M(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    return (ConnectivityManager)paramContext.getSystemService("connectivity");
  }
  
  private static TelephonyManager N(Context paramContext)
  {
    return (TelephonyManager)paramContext.getSystemService("phone");
  }
  
  private static WifiInfo O(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    return ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
  }
  
  private static NetworkInfo P(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  @SuppressLint({"NewApi"})
  private static Point Q(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    if (Build.VERSION.SDK_INT >= 13)
    {
      paramContext.getSize(localPoint);
    }
    else
    {
      localPoint.x = paramContext.getWidth();
      localPoint.y = paramContext.getHeight();
    }
    if (localPoint.x >= localPoint.y)
    {
      int i = localPoint.y;
      localPoint.y = localPoint.x;
      localPoint.x = i;
    }
    return localPoint;
  }
  
  public static String a()
  {
    try
    {
      String str = TimeZone.getDefault().getID();
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String a(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static String a(String paramString, Context paramContext)
  {
    try
    {
      ae localAe = ae.a(paramString);
      int i;
      switch (1.a[localAe.ordinal()])
      {
      default: 
        return null;
      case 51: 
        paramString = L(paramContext);
        if (paramString == null) {
          return null;
        }
        return String.valueOf(paramString);
      case 50: 
        i = K(paramContext);
        if (i == 2) {
          return "L";
        }
        if (i == 1) {
          return "P";
        }
        return "U";
      case 49: 
        i = J(paramContext);
        break;
      case 48: 
        i = I(paramContext);
        break;
      case 47: 
        i = 11;
        return String.valueOf(i);
      case 46: 
        return c();
      case 45: 
        return "ANDROID";
      case 43: 
        return b().getCountry();
      case 42: 
        return b().getLanguage();
      case 41: 
        paramString = G(paramContext);
        break;
      case 40: 
        paramString = F(paramContext);
        break;
      case 39: 
        paramString = E(paramContext);
        break;
      case 38: 
        return D(paramContext);
      case 37: 
        return C(paramContext);
      case 36: 
        return B(paramContext);
      case 35: 
        return A(paramContext);
      case 34: 
        return z(paramContext);
      case 33: 
        paramString = y(paramContext);
        break;
      case 32: 
        return x(paramContext);
      case 31: 
        return w(paramContext);
      case 30: 
        return v(paramContext);
      case 29: 
        return u(paramContext);
      case 28: 
        return t(paramContext);
      case 27: 
        return s(paramContext);
      case 26: 
        return r(paramContext);
      case 25: 
        return q(paramContext);
      case 24: 
        paramString = p(paramContext);
        break;
      case 23: 
        return k();
      case 22: 
        paramString = o(paramContext);
        break;
      case 21: 
        return n(paramContext);
      case 20: 
        return m(paramContext);
      case 19: 
      case 44: 
        return j();
      case 18: 
        return i();
      case 17: 
        return h();
      case 16: 
        return g();
      case 15: 
        return f();
      case 14: 
        paramString = e();
        return String.valueOf(paramString);
      case 13: 
        return d();
      case 12: 
        return l(paramContext);
      case 11: 
        return k(paramContext);
      case 10: 
        return j(paramContext);
      case 9: 
        return i(paramContext);
      case 8: 
        return h(paramContext);
      case 7: 
        return g(paramContext);
      case 6: 
        return a(f(paramContext));
      case 5: 
        paramString = new Date(d(paramContext).longValue());
        break;
      case 4: 
        paramString = new Date(c(paramContext).longValue());
        return aj.a(paramString);
      case 3: 
        return a();
      case 2: 
        return b(paramContext);
      }
      return a(paramContext);
    }
    catch (IllegalStateException paramContext)
    {
      for (;;) {}
    }
    paramContext = new StringBuilder();
    paramContext.append("Invalid short name : ");
    paramContext.append(paramString);
    q.b(paramContext.toString());
    return null;
  }
  
  private static String a(List<String> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramList.size())
    {
      String str = ((String)paramList.get(i)).toString();
      if (i > 0) {
        localStringBuffer.append(",");
      }
      localStringBuffer.append(str);
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static String b(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ANDROID-");
    localStringBuilder.append(a(paramContext));
    return localStringBuilder.toString();
  }
  
  public static Locale b()
  {
    return Locale.getDefault();
  }
  
  public static Long c(Context paramContext)
  {
    if (e().intValue() < 9) {
      return null;
    }
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).firstInstallTime;
      return Long.valueOf(l);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String c()
  {
    return aj.a(new Date());
  }
  
  @SuppressLint({"NewApi"})
  public static Long d(Context paramContext)
  {
    if (e().intValue() < 9) {
      return null;
    }
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).lastUpdateTime;
      return Long.valueOf(l);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String d()
  {
    try
    {
      String str = Build.BRAND;
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Integer e()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return Integer.valueOf(i);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static List<Account> e(Context paramContext)
  {
    if (!l.a("android.permission.GET_ACCOUNTS", paramContext)) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    try
    {
      Pattern localPattern = Patterns.EMAIL_ADDRESS;
      paramContext = AccountManager.get(paramContext).getAccounts();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramContext[i];
        if (("com.google".equals(localObject.name)) && (localPattern.matcher(localObject.name).matches())) {
          localArrayList.add(localObject);
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
    return localArrayList;
  }
  
  public static String f()
  {
    try
    {
      String str = Build.PRODUCT;
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static List<String> f(Context paramContext)
  {
    if (!l.a("android.permission.GET_ACCOUNTS", paramContext)) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramContext = e(paramContext).iterator();
      while (paramContext.hasNext())
      {
        Account localAccount = (Account)paramContext.next();
        if ("com.google".equals(localAccount.name))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(localAccount.name);
          localStringBuilder.append(":");
          localStringBuilder.append(localAccount.type);
          localArrayList.add(localStringBuilder.toString());
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
    return localArrayList;
  }
  
  public static String g()
  {
    try
    {
      String str = Build.HARDWARE;
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  @SuppressLint({"NewApi"})
  public static String g(Context paramContext)
  {
    if (e().intValue() < 9) {
      return null;
    }
    try
    {
      paramContext = Build.SERIAL;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String h()
  {
    try
    {
      String str = Build.USER;
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String h(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = O(paramContext).getMacAddress();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String i()
  {
    try
    {
      String str = Build.FINGERPRINT;
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String i(Context paramContext)
  {
    paramContext = h(paramContext);
    if (paramContext == null) {
      return null;
    }
    return b.c(paramContext);
  }
  
  public static String j()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String j(Context paramContext)
  {
    paramContext = h(paramContext);
    if (paramContext == null) {
      return null;
    }
    return b.d(paramContext);
  }
  
  public static String k()
  {
    return String.format("Android %s", new Object[] { Build.VERSION.RELEASE });
  }
  
  public static String k(Context paramContext)
  {
    if (!l.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = N(paramContext);
      if (paramContext != null)
      {
        paramContext = paramContext.getDeviceId();
        return paramContext;
      }
      return null;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String l()
  {
    return System.getProperty("batch.bridge.version", "");
  }
  
  public static String l(Context paramContext)
  {
    if (!l.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = N(paramContext);
      if (paramContext != null)
      {
        paramContext = paramContext.getSubscriberId();
        return paramContext;
      }
      return null;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String m()
  {
    return System.getProperty("batch.plugin.version", "");
  }
  
  public static String m(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      boolean bool = paramContext.equals("9774d56d682e549c");
      if (bool) {
        return null;
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String n(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(a(paramContext), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Integer o(Context paramContext)
  {
    try
    {
      int i = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramContext.getApplicationContext().getPackageName(), 0).versionCode;
      return Integer.valueOf(i);
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Float p(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      float f = localDisplayMetrics.density;
      return Float.valueOf(f);
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String q(Context paramContext)
  {
    if (!l.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = N(paramContext).getSimSerialNumber();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String r(Context paramContext)
  {
    if (!l.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = N(paramContext).getSimOperatorName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String s(Context paramContext)
  {
    try
    {
      paramContext = N(paramContext).getSimOperator();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String t(Context paramContext)
  {
    if (!l.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = N(paramContext).getSimCountryIso();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String u(Context paramContext)
  {
    if (!l.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = N(paramContext).getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String v(Context paramContext)
  {
    if (!l.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = N(paramContext).getNetworkCountryIso();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String w(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = O(paramContext).getSSID();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String x(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = O(paramContext).getBSSID();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static Boolean y(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      boolean bool = O(paramContext).getHiddenSSID();
      return Boolean.valueOf(bool);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String z(Context paramContext)
  {
    if (!l.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = InetAddress.getByAddress(BigInteger.valueOf(O(paramContext).getIpAddress()).toByteArray()).toString();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
}
