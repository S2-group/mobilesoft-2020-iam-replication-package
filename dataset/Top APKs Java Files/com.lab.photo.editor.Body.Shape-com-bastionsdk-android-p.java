package com.bastionsdk.android;

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
import com.bastionsdk.android.a.b;
import com.bastionsdk.android.a.n;
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
    if (!Bastion.1.a("android.permission.BLUETOOTH", paramContext)) {
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
  
  protected static String B(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.I(paramContext).getTypeName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String C(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.I(paramContext).getSubtypeName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static NetworkInfo.State D(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return NetworkInfo.State.UNKNOWN;
    }
    try
    {
      paramContext = Bastion.1.I(paramContext).getState();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return NetworkInfo.State.UNKNOWN;
  }
  
  protected static NetworkInfo.DetailedState E(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.I(paramContext).getDetailedState();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static Boolean F(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
      return null;
    }
    try
    {
      boolean bool = Bastion.1.I(paramContext).isRoaming();
      return Boolean.valueOf(bool);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private static TelephonyManager G(Context paramContext)
  {
    return (TelephonyManager)paramContext.getSystemService("phone");
  }
  
  private static WifiInfo H(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    return ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
  }
  
  private static NetworkInfo I(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_NETWORK_STATE", paramContext)) {
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
  
  private static List<ApplicationInfo> J(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128);
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
    return "ANDROID-" + paramContext.getPackageName();
  }
  
  protected static String b(String paramString, Context paramContext)
  {
    try
    {
      r localR = r.a(paramString);
      switch (q.a[localR.ordinal()])
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
    return paramContext.getPackageName();
    return Bastion.1.b(paramContext);
    return Bastion.1.a();
    return String.valueOf(Bastion.1.c(paramContext));
    return String.valueOf(Bastion.1.d(paramContext));
    return Bastion.1.a(Bastion.1.f(paramContext));
    return Bastion.1.d();
    return Bastion.1.g(paramContext);
    return Bastion.1.h(paramContext);
    return Bastion.1.i(paramContext);
    return Bastion.1.j(paramContext);
    return Bastion.1.k(paramContext);
    return Bastion.1.e();
    return String.valueOf(Bastion.1.f());
    return Bastion.1.g();
    return Bastion.1.h();
    return Bastion.1.i();
    return Bastion.1.j();
    return Bastion.1.k();
    return Bastion.1.l(paramContext);
    return Bastion.1.m(paramContext);
    return String.valueOf(Bastion.1.n(paramContext));
    return Bastion.1.l();
    return String.valueOf(Bastion.1.o(paramContext));
    return Bastion.1.p(paramContext);
    return Bastion.1.q(paramContext);
    return Bastion.1.r(paramContext);
    return Bastion.1.s(paramContext);
    return Bastion.1.t(paramContext);
    return Bastion.1.u(paramContext);
    return Bastion.1.v(paramContext);
    return Bastion.1.w(paramContext);
    return String.valueOf(Bastion.1.x(paramContext));
    return Bastion.1.y(paramContext);
    return Bastion.1.z(paramContext);
    return Bastion.1.A(paramContext);
    return Bastion.1.B(paramContext);
    return Bastion.1.C(paramContext);
    return String.valueOf(Bastion.1.D(paramContext));
    return String.valueOf(Bastion.1.E(paramContext));
    return String.valueOf(Bastion.1.F(paramContext));
    return Locale.getDefault().getLanguage();
    return Locale.getDefault().getCountry();
    return Bastion.1.k();
    return "ANDROID";
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(new Date());
    return "1";
  }
  
  protected static Locale b()
  {
    return Locale.getDefault();
  }
  
  @SuppressLint({"NewApi"})
  protected static Long c(Context paramContext)
  {
    if (Bastion.1.f().intValue() < 9) {
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
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(new Date());
  }
  
  @SuppressLint({"NewApi"})
  protected static Long d(Context paramContext)
  {
    if (Bastion.1.f().intValue() < 9) {
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
  
  @SuppressLint({"NewApi"})
  protected static String d()
  {
    if (Bastion.1.f().intValue() < 9) {
      return null;
    }
    try
    {
      String str = Build.SERIAL;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String e()
  {
    try
    {
      String str = Build.BRAND;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static List<Account> e(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.GET_ACCOUNTS", paramContext)) {
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
  
  protected static Integer f()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return Integer.valueOf(i);
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static List<String> f(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.GET_ACCOUNTS", paramContext)) {
      return null;
    }
    localArrayList = new ArrayList();
    try
    {
      paramContext = Bastion.1.e(paramContext).iterator();
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
      String str = Build.PRODUCT;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String g(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.H(paramContext).getMacAddress();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String h()
  {
    try
    {
      String str = Build.HARDWARE;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String h(Context paramContext)
  {
    paramContext = Bastion.1.g(paramContext);
    if (paramContext == null) {
      return null;
    }
    return b.c(paramContext);
  }
  
  protected static String i()
  {
    try
    {
      String str = Build.USER;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String i(Context paramContext)
  {
    paramContext = Bastion.1.g(paramContext);
    if (paramContext == null) {
      return null;
    }
    return b.d(paramContext);
  }
  
  protected static String j()
  {
    try
    {
      String str = Build.FINGERPRINT;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String j(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.READ_PHONE_STATE", paramContext)) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = Bastion.1.G(paramContext);
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
  
  protected static String k()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected static String k(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.READ_PHONE_STATE", paramContext)) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = Bastion.1.G(paramContext);
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
  
  protected static String l()
  {
    return String.format("Android %s", new Object[] { Build.VERSION.RELEASE });
  }
  
  protected static String l(Context paramContext)
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
  
  protected static String m(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  protected static Integer n(Context paramContext)
  {
    try
    {
      int i = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramContext.getApplicationContext().getPackageName(), 0).versionCode;
      return Integer.valueOf(i);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static Float o(Context paramContext)
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
  
  protected static String p(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.G(paramContext).getSimSerialNumber();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String q(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.G(paramContext).getSimOperatorName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String r(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.G(paramContext).getSimOperator();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String s(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.G(paramContext).getSimCountryIso();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String t(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.G(paramContext).getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String u(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.READ_PHONE_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.G(paramContext).getNetworkCountryIso();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String v(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.H(paramContext).getSSID();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String w(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = Bastion.1.H(paramContext).getBSSID();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static Boolean x(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      boolean bool = Bastion.1.H(paramContext).getHiddenSSID();
      return Boolean.valueOf(bool);
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String y(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.ACCESS_WIFI_STATE", paramContext)) {
      return null;
    }
    try
    {
      paramContext = InetAddress.getByAddress(BigInteger.valueOf(Bastion.1.H(paramContext).getIpAddress()).toByteArray()).toString();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  protected static String z(Context paramContext)
  {
    if (!Bastion.1.a("android.permission.BLUETOOTH", paramContext)) {
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
}
