package com.batch.android;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
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
import com.batch.android.a.b;
import com.batch.android.a.n;
import java.math.BigInteger;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class p
{
  p() {}
  
  protected static String A(Context paramContext)
  {
    if (!a("android.permission.BLUETOOTH", paramContext)) {
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
  
  protected static String B(Context paramContext)
  {
    if (!a("android.permission.BLUETOOTH", paramContext)) {
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
  
  protected static String C(Context paramContext)
  {
    if (!a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = K(paramContext).getTypeName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String D(Context paramContext)
  {
    if (!a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = K(paramContext).getSubtypeName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static NetworkInfo.State E(Context paramContext)
  {
    if (!a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return NetworkInfo.State.UNKNOWN;
    }
    try
    {
      paramContext = K(paramContext).getState();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return NetworkInfo.State.UNKNOWN;
  }
  
  protected static NetworkInfo.DetailedState F(Context paramContext)
  {
    if (!a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = K(paramContext).getDetailedState();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static Boolean G(Context paramContext)
  {
    if (!a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      boolean bool = K(paramContext).isRoaming();
      return Boolean.valueOf(bool);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static List<ApplicationInfo> H(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private static TelephonyManager I(Context paramContext)
  {
    return (TelephonyManager)paramContext.getSystemService("phone");
  }
  
  private static WifiInfo J(Context paramContext)
  {
    if (!a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    return ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
  }
  
  private static NetworkInfo K(Context paramContext)
  {
    if (!a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
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
  
  protected static String a()
  {
    try
    {
      String str = TimeZone.getDefault().getID();
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String a(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  protected static String a(Date paramDate)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat.format(paramDate);
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
  
  protected static boolean a(String paramString, Context paramContext)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  protected static String b(Context paramContext)
  {
    return "ANDROID-" + a(paramContext);
  }
  
  protected static String b(String paramString, Context paramContext)
  {
    try
    {
      q localQ = q.a(paramString);
      switch (1.a[localQ.ordinal()])
      {
      default: 
        return null;
      }
    }
    catch (IllegalStateException paramContext)
    {
      n.b("Invalid short name : " + paramString);
      return null;
    }
    return a(paramContext);
    return b(paramContext);
    return a();
    return a(new Date(c(paramContext).longValue()));
    return a(new Date(d(paramContext).longValue()));
    return a(f(paramContext));
    return g(paramContext);
    return h(paramContext);
    return i(paramContext);
    return j(paramContext);
    return k(paramContext);
    return l(paramContext);
    return d();
    return String.valueOf(e());
    return f();
    return g();
    return h();
    return i();
    return j();
    return m(paramContext);
    return n(paramContext);
    return String.valueOf(o(paramContext));
    return k();
    return String.valueOf(p(paramContext));
    return q(paramContext);
    return r(paramContext);
    return s(paramContext);
    return t(paramContext);
    return u(paramContext);
    return v(paramContext);
    return w(paramContext);
    return x(paramContext);
    return String.valueOf(y(paramContext));
    return z(paramContext);
    return A(paramContext);
    return B(paramContext);
    return C(paramContext);
    return D(paramContext);
    return String.valueOf(E(paramContext));
    return String.valueOf(F(paramContext));
    return String.valueOf(G(paramContext));
    return b().getLanguage();
    return b().getCountry();
    return j();
    return "ANDROID";
    return c();
    return String.valueOf(2);
  }
  
  protected static Locale b()
  {
    return Locale.getDefault();
  }
  
  protected static Long c(Context paramContext)
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
  
  protected static String c()
  {
    return a(new Date());
  }
  
  @SuppressLint({"NewApi"})
  protected static Long d(Context paramContext)
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
  
  protected static String d()
  {
    try
    {
      String str = Build.BRAND;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static Integer e()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return Integer.valueOf(i);
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static List<Account> e(Context paramContext)
  {
    if (!a("android.permission.GET_ACCOUNTS", paramContext)) {
      paramContext = null;
    }
    for (;;)
    {
      return paramContext;
      localArrayList = new ArrayList();
      try
      {
        Pattern localPattern = Patterns.EMAIL_ADDRESS;
        Account[] arrayOfAccount = AccountManager.get(paramContext).getAccounts();
        int j = arrayOfAccount.length;
        int i = 0;
        for (;;)
        {
          paramContext = localArrayList;
          if (i >= j) {
            break;
          }
          paramContext = arrayOfAccount[i];
          if (localPattern.matcher(paramContext.name).matches()) {
            localArrayList.add(paramContext);
          }
          i += 1;
        }
        return localArrayList;
      }
      catch (Exception paramContext) {}
    }
  }
  
  protected static String f()
  {
    try
    {
      String str = Build.PRODUCT;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static List<String> f(Context paramContext)
  {
    if (!a("android.permission.GET_ACCOUNTS", paramContext)) {
      return null;
    }
    localArrayList = new ArrayList();
    try
    {
      paramContext = e(paramContext).iterator();
      while (paramContext.hasNext())
      {
        Account localAccount = (Account)paramContext.next();
        localArrayList.add(localAccount.name + ":" + localAccount.type);
      }
      return localArrayList;
    }
    catch (Exception paramContext) {}
  }
  
  protected static String g()
  {
    try
    {
      String str = Build.HARDWARE;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  @SuppressLint({"NewApi"})
  protected static String g(Context paramContext)
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
  
  protected static String h()
  {
    try
    {
      String str = Build.USER;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String h(Context paramContext)
  {
    if (!a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = J(paramContext).getMacAddress();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String i()
  {
    try
    {
      String str = Build.FINGERPRINT;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String i(Context paramContext)
  {
    paramContext = h(paramContext);
    if (paramContext == null) {
      return null;
    }
    return b.c(paramContext);
  }
  
  protected static String j()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String j(Context paramContext)
  {
    paramContext = h(paramContext);
    if (paramContext == null) {
      return null;
    }
    return b.d(paramContext);
  }
  
  protected static String k()
  {
    return String.format("Android %s", new Object[] { Build.VERSION.RELEASE });
  }
  
  protected static String k(Context paramContext)
  {
    if (!a("android.permission.READ_PHONE_STATE", paramContext)) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = I(paramContext);
        if (paramContext != null)
        {
          paramContext = paramContext.getDeviceId();
          return paramContext;
        }
      }
      catch (Exception paramContext) {}
    }
    return null;
  }
  
  protected static String l()
  {
    return System.getProperty("batch.bridge.version", "");
  }
  
  protected static String l(Context paramContext)
  {
    if (!a("android.permission.READ_PHONE_STATE", paramContext)) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = I(paramContext);
        if (paramContext != null)
        {
          paramContext = paramContext.getSubscriberId();
          return paramContext;
        }
      }
      catch (Exception paramContext) {}
    }
    return null;
  }
  
  protected static String m()
  {
    return System.getProperty("batch.plugin.version", "");
  }
  
  protected static String m(Context paramContext)
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
  
  protected static String n(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(a(paramContext), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  protected static Integer o(Context paramContext)
  {
    try
    {
      int i = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramContext.getApplicationContext().getPackageName(), 0).versionCode;
      return Integer.valueOf(i);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static Float p(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      float f = localDisplayMetrics.density;
      return Float.valueOf(f);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String q(Context paramContext)
  {
    if (!a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = I(paramContext).getSimSerialNumber();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String r(Context paramContext)
  {
    if (!a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = I(paramContext).getSimOperatorName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String s(Context paramContext)
  {
    if (!a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = I(paramContext).getSimOperator();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String t(Context paramContext)
  {
    if (!a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = I(paramContext).getSimCountryIso();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String u(Context paramContext)
  {
    if (!a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = I(paramContext).getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String v(Context paramContext)
  {
    if (!a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = I(paramContext).getNetworkCountryIso();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String w(Context paramContext)
  {
    if (!a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = J(paramContext).getSSID();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String x(Context paramContext)
  {
    if (!a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = J(paramContext).getBSSID();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static Boolean y(Context paramContext)
  {
    if (!a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      boolean bool = J(paramContext).getHiddenSSID();
      return Boolean.valueOf(bool);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String z(Context paramContext)
  {
    if (!a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = InetAddress.getByAddress(BigInteger.valueOf(J(paramContext).getIpAddress()).toByteArray()).toString();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
}
