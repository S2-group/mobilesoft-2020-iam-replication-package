package com.bastionsdk.android;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import com.bastionsdk.android.a.q;
import com.bastionsdk.android.a.t;
import com.bastionsdk.android.a.w;
import com.bastionsdk.android.a.z;
import com.bastionsdk.android.d.c;
import com.bastionsdk.android.d.d;
import com.bastionsdk.android.d.e;
import com.bastionsdk.android.d.f;
import java.math.BigInteger;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Bastion
{
  private static final String a = "com.bastionsdk.android.API_KEY";
  private static final String b = "failedSend";
  private static BastionOfferListener c;
  private static BastionURLListener d;
  private static Context e;
  private static String f;
  private static i g;
  private static k h;
  private static s i;
  private static BroadcastReceiver j;
  private static ArrayList<Offer> k;
  private static Intent l;
  private static String m;
  private static d n = new d(new a((byte)0));
  
  public Bastion() {}
  
  protected static i a()
  {
    return g;
  }
  
  protected static void a(Offer paramOffer)
  {
    if ((paramOffer != null) && (!n.b(new com.bastionsdk.android.d.b()
    {
      public final Offer a()
      {
        return this.a;
      }
      
      public final void run()
      {
        Bastion.o().onRedeemOffer(this.a);
      }
    }))) {
      com.bastionsdk.android.a.n.c("dispatchUnlockOffer send fail");
    }
  }
  
  private static void a(boolean paramBoolean)
  {
    if (n.a(e.b, new com.bastionsdk.android.d.a()
    {
      public final e a(e paramAnonymousE)
      {
        com.bastionsdk.android.a.n.c("onStop called with state " + paramAnonymousE);
        if ((!this.a) && (!Bastion.d().c().isFinishing()))
        {
          com.bastionsdk.android.a.n.c("onStop called but activity is not finishing... saving date");
          Bastion.d().b();
          return null;
        }
        Bastion.d().a(null);
        Bastion.a(null);
        Bastion.a(null);
        return e.c;
      }
    }))
    {
      if (!z.a(e).a())
      {
        com.bastionsdk.android.a.n.c("onStop, should stop directly : true");
        q();
      }
    }
    else {
      return;
    }
    com.bastionsdk.android.a.n.c("onStop, should stop directly : false");
  }
  
  protected static k b()
  {
    return h;
  }
  
  protected static s c()
  {
    return i;
  }
  
  public static String getAPIKey()
  {
    return f;
  }
  
  public static boolean isRunningInDevMode()
  {
    AtomicBoolean localAtomicBoolean = new AtomicBoolean(false);
    n.a(new f()
    {
      public final void a(e paramAnonymousE)
      {
        if (paramAnonymousE != e.b)
        {
          com.bastionsdk.android.a.n.a(false, "You must start Bastion before asking isRunningInDevMode, returning default value : false");
          return;
        }
        try
        {
          this.a.set(com.bastionsdk.android.a.a.a(Bastion.g()).a());
          return;
        }
        catch (Exception paramAnonymousE)
        {
          com.bastionsdk.android.a.n.a(false, "An internal Bastion error occured while retreiving run mode, returning default value : false");
          com.bastionsdk.android.a.n.a("Error while retreiving dev mode", paramAnonymousE);
        }
      }
    });
    return localAtomicBoolean.get();
  }
  
  public static void onDestroy(Activity paramActivity)
  {
    a(true);
  }
  
  public static void onNewIntent(Activity paramActivity, Intent paramIntent)
  {
    n.a(new f()
    {
      public final void a(e paramAnonymousE)
      {
        Bastion.a(this.a);
      }
    });
  }
  
  public static void onStart(Activity paramActivity, BastionOfferListener paramBastionOfferListener)
  {
    onStart(paramActivity, paramBastionOfferListener, null);
  }
  
  public static void onStart(final Activity paramActivity, final BastionOfferListener paramBastionOfferListener, BastionURLListener paramBastionURLListener)
  {
    if ((n.a(new com.bastionsdk.android.d.a()
    {
      protected static String A(Context paramAnonymousContext)
      {
        if (!a("android.permission.BLUETOOTH", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = BluetoothAdapter.getDefaultAdapter().getAddress();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String B(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_NETWORK_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = I(paramAnonymousContext).getTypeName();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String C(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_NETWORK_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = I(paramAnonymousContext).getSubtypeName();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static NetworkInfo.State D(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_NETWORK_STATE", paramAnonymousContext)) {
          return NetworkInfo.State.UNKNOWN;
        }
        try
        {
          paramAnonymousContext = I(paramAnonymousContext).getState();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return NetworkInfo.State.UNKNOWN;
      }
      
      protected static NetworkInfo.DetailedState E(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_NETWORK_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = I(paramAnonymousContext).getDetailedState();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static Boolean F(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_NETWORK_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          boolean bool = I(paramAnonymousContext).isRoaming();
          return Boolean.valueOf(bool);
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      private static TelephonyManager G(Context paramAnonymousContext)
      {
        return (TelephonyManager)paramAnonymousContext.getSystemService("phone");
      }
      
      private static WifiInfo H(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_WIFI_STATE", paramAnonymousContext)) {
          return null;
        }
        return ((WifiManager)paramAnonymousContext.getSystemService("wifi")).getConnectionInfo();
      }
      
      private static NetworkInfo I(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_NETWORK_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = ((ConnectivityManager)paramAnonymousContext.getSystemService("connectivity")).getActiveNetworkInfo();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      private static List<ApplicationInfo> J(Context paramAnonymousContext)
      {
        try
        {
          paramAnonymousContext = paramAnonymousContext.getPackageManager().getInstalledApplications(128);
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
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
      
      protected static String a(Context paramAnonymousContext)
      {
        return paramAnonymousContext.getPackageName();
      }
      
      private static String a(List<String> paramAnonymousList)
      {
        StringBuffer localStringBuffer = new StringBuffer();
        int i = 0;
        while (i < paramAnonymousList.size())
        {
          String str = ((String)paramAnonymousList.get(i)).toString();
          if (i > 0) {
            localStringBuffer.append(",");
          }
          localStringBuffer.append(str);
          i += 1;
        }
        return localStringBuffer.toString();
      }
      
      protected static boolean a(String paramAnonymousString, Context paramAnonymousContext)
      {
        return paramAnonymousContext.checkCallingOrSelfPermission(paramAnonymousString) == 0;
      }
      
      protected static String b(Context paramAnonymousContext)
      {
        return "ANDROID-" + paramAnonymousContext.getPackageName();
      }
      
      protected static String b(String paramAnonymousString, Context paramAnonymousContext)
      {
        do
        {
          do
          {
            try
            {
              r localR = r.a(paramAnonymousString);
              switch (q.a[localR.ordinal()])
              {
              default: 
                return null;
              }
            }
            catch (IllegalStateException paramAnonymousContext)
            {
              com.bastionsdk.android.a.n.b("Invalid short name : " + paramAnonymousString);
              return null;
            }
            return paramAnonymousContext.getPackageName();
            return "ANDROID-" + paramAnonymousContext.getPackageName();
            return a();
            return String.valueOf(c(paramAnonymousContext));
            return String.valueOf(d(paramAnonymousContext));
            return a(f(paramAnonymousContext));
            return d();
            return g(paramAnonymousContext);
            paramAnonymousString = g(paramAnonymousContext);
          } while (paramAnonymousString == null);
          return com.bastionsdk.android.a.b.c(paramAnonymousString);
          paramAnonymousString = g(paramAnonymousContext);
        } while (paramAnonymousString == null);
        return com.bastionsdk.android.a.b.d(paramAnonymousString);
        return j(paramAnonymousContext);
        return k(paramAnonymousContext);
        return e();
        return String.valueOf(f());
        return g();
        return h();
        return i();
        return j();
        return k();
        return l(paramAnonymousContext);
        return m(paramAnonymousContext);
        return String.valueOf(n(paramAnonymousContext));
        return l();
        return String.valueOf(o(paramAnonymousContext));
        return p(paramAnonymousContext);
        return q(paramAnonymousContext);
        return r(paramAnonymousContext);
        return s(paramAnonymousContext);
        return t(paramAnonymousContext);
        return u(paramAnonymousContext);
        return v(paramAnonymousContext);
        return w(paramAnonymousContext);
        return String.valueOf(x(paramAnonymousContext));
        return y(paramAnonymousContext);
        return z(paramAnonymousContext);
        return A(paramAnonymousContext);
        return B(paramAnonymousContext);
        return C(paramAnonymousContext);
        return String.valueOf(D(paramAnonymousContext));
        return String.valueOf(E(paramAnonymousContext));
        return String.valueOf(F(paramAnonymousContext));
        return Locale.getDefault().getLanguage();
        return Locale.getDefault().getCountry();
        return k();
        return "ANDROID";
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(new Date());
        return "1";
      }
      
      protected static Locale b()
      {
        return Locale.getDefault();
      }
      
      @SuppressLint({"NewApi"})
      protected static Long c(Context paramAnonymousContext)
      {
        if (f().intValue() < 9) {
          return null;
        }
        try
        {
          long l = paramAnonymousContext.getPackageManager().getPackageInfo(paramAnonymousContext.getPackageName(), 0).firstInstallTime;
          return Long.valueOf(l);
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String c()
      {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(new Date());
      }
      
      @SuppressLint({"NewApi"})
      protected static Long d(Context paramAnonymousContext)
      {
        if (f().intValue() < 9) {
          return null;
        }
        try
        {
          long l = paramAnonymousContext.getPackageManager().getPackageInfo(paramAnonymousContext.getPackageName(), 0).lastUpdateTime;
          return Long.valueOf(l);
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      @SuppressLint({"NewApi"})
      protected static String d()
      {
        if (f().intValue() < 9) {
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
      
      protected static List<Account> e(Context paramAnonymousContext)
      {
        if (!a("android.permission.GET_ACCOUNTS", paramAnonymousContext)) {
          paramAnonymousContext = null;
        }
        for (;;)
        {
          return paramAnonymousContext;
          localArrayList = new ArrayList();
          try
          {
            Pattern localPattern = Patterns.EMAIL_ADDRESS;
            Account[] arrayOfAccount = AccountManager.get(paramAnonymousContext).getAccounts();
            int j = arrayOfAccount.length;
            int i = 0;
            for (;;)
            {
              paramAnonymousContext = localArrayList;
              if (i >= j) {
                break;
              }
              paramAnonymousContext = arrayOfAccount[i];
              if (localPattern.matcher(paramAnonymousContext.name).matches()) {
                localArrayList.add(paramAnonymousContext);
              }
              i += 1;
            }
            return localArrayList;
          }
          catch (Exception paramAnonymousContext) {}
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
      
      protected static List<String> f(Context paramAnonymousContext)
      {
        if (!a("android.permission.GET_ACCOUNTS", paramAnonymousContext)) {
          return null;
        }
        localArrayList = new ArrayList();
        try
        {
          paramAnonymousContext = e(paramAnonymousContext).iterator();
          while (paramAnonymousContext.hasNext())
          {
            Account localAccount = (Account)paramAnonymousContext.next();
            localArrayList.add(localAccount.name + ":" + localAccount.type);
          }
          return localArrayList;
        }
        catch (Exception paramAnonymousContext) {}
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
      
      protected static String g(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_WIFI_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = H(paramAnonymousContext).getMacAddress();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
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
      
      protected static String h(Context paramAnonymousContext)
      {
        paramAnonymousContext = g(paramAnonymousContext);
        if (paramAnonymousContext == null) {
          return null;
        }
        return com.bastionsdk.android.a.b.c(paramAnonymousContext);
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
      
      protected static String i(Context paramAnonymousContext)
      {
        paramAnonymousContext = g(paramAnonymousContext);
        if (paramAnonymousContext == null) {
          return null;
        }
        return com.bastionsdk.android.a.b.d(paramAnonymousContext);
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
      
      protected static String j(Context paramAnonymousContext)
      {
        if (!a("android.permission.READ_PHONE_STATE", paramAnonymousContext)) {}
        for (;;)
        {
          return null;
          try
          {
            paramAnonymousContext = G(paramAnonymousContext);
            if (paramAnonymousContext != null)
            {
              paramAnonymousContext = paramAnonymousContext.getDeviceId();
              return paramAnonymousContext;
            }
          }
          catch (Exception paramAnonymousContext) {}
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
      
      protected static String k(Context paramAnonymousContext)
      {
        if (!a("android.permission.READ_PHONE_STATE", paramAnonymousContext)) {}
        for (;;)
        {
          return null;
          try
          {
            paramAnonymousContext = G(paramAnonymousContext);
            if (paramAnonymousContext != null)
            {
              paramAnonymousContext = paramAnonymousContext.getSubscriberId();
              return paramAnonymousContext;
            }
          }
          catch (Exception paramAnonymousContext) {}
        }
        return null;
      }
      
      protected static String l()
      {
        return String.format("Android %s", new Object[] { Build.VERSION.RELEASE });
      }
      
      protected static String l(Context paramAnonymousContext)
      {
        try
        {
          paramAnonymousContext = Settings.Secure.getString(paramAnonymousContext.getContentResolver(), "android_id");
          boolean bool = paramAnonymousContext.equals("9774d56d682e549c");
          if (bool) {
            return null;
          }
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String m(Context paramAnonymousContext)
      {
        try
        {
          paramAnonymousContext = paramAnonymousContext.getPackageManager().getPackageInfo(paramAnonymousContext.getPackageName(), 0).versionName;
          return paramAnonymousContext;
        }
        catch (PackageManager.NameNotFoundException paramAnonymousContext) {}
        return null;
      }
      
      protected static Integer n(Context paramAnonymousContext)
      {
        try
        {
          int i = paramAnonymousContext.getApplicationContext().getPackageManager().getPackageInfo(paramAnonymousContext.getApplicationContext().getPackageName(), 0).versionCode;
          return Integer.valueOf(i);
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static Float o(Context paramAnonymousContext)
      {
        try
        {
          DisplayMetrics localDisplayMetrics = new DisplayMetrics();
          ((WindowManager)paramAnonymousContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
          float f = localDisplayMetrics.density;
          return Float.valueOf(f);
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String p(Context paramAnonymousContext)
      {
        if (!a("android.permission.READ_PHONE_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = G(paramAnonymousContext).getSimSerialNumber();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String q(Context paramAnonymousContext)
      {
        if (!a("android.permission.READ_PHONE_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = G(paramAnonymousContext).getSimOperatorName();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String r(Context paramAnonymousContext)
      {
        if (!a("android.permission.READ_PHONE_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = G(paramAnonymousContext).getSimOperator();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String s(Context paramAnonymousContext)
      {
        if (!a("android.permission.READ_PHONE_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = G(paramAnonymousContext).getSimCountryIso();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String t(Context paramAnonymousContext)
      {
        if (!a("android.permission.READ_PHONE_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = G(paramAnonymousContext).getNetworkOperatorName();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String u(Context paramAnonymousContext)
      {
        if (!a("android.permission.READ_PHONE_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = G(paramAnonymousContext).getNetworkCountryIso();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String v(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_WIFI_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = H(paramAnonymousContext).getSSID();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String w(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_WIFI_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = H(paramAnonymousContext).getBSSID();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static Boolean x(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_WIFI_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          boolean bool = H(paramAnonymousContext).getHiddenSSID();
          return Boolean.valueOf(bool);
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String y(Context paramAnonymousContext)
      {
        if (!a("android.permission.ACCESS_WIFI_STATE", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = InetAddress.getByAddress(BigInteger.valueOf(H(paramAnonymousContext).getIpAddress()).toByteArray()).toString();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      protected static String z(Context paramAnonymousContext)
      {
        if (!a("android.permission.BLUETOOTH", paramAnonymousContext)) {
          return null;
        }
        try
        {
          paramAnonymousContext = BluetoothAdapter.getDefaultAdapter().getName();
          return paramAnonymousContext;
        }
        catch (Exception paramAnonymousContext) {}
        return null;
      }
      
      public final e a(e paramAnonymousE)
      {
        boolean bool = Bastion.d().a();
        com.bastionsdk.android.a.n.c("onStart called on state " + paramAnonymousE + ", should start : " + bool);
        if (this.a != null) {
          com.bastionsdk.android.a.n.c("onStart called with url listener");
        }
        if (paramActivity == null)
        {
          com.bastionsdk.android.a.n.a(false, "Bastion.onStart called with null activity, abording start");
          return null;
        }
        if (paramBastionOfferListener == null)
        {
          com.bastionsdk.android.a.n.a(false, "Bastion.onStart called with null BastionOfferListener, abording start");
          return null;
        }
        Bastion.d().a(paramActivity);
        Bastion.a(paramBastionOfferListener);
        Bastion.a(this.a);
        if (Bastion.e() != null) {}
        for (String str = new com.bastionsdk.android.a.n(Bastion.e()).a(paramActivity.getApplicationContext());; str = new com.bastionsdk.android.a.n(paramActivity).a(paramActivity.getApplicationContext()))
        {
          Bastion.a(str);
          Bastion.a(null);
          if ((paramAnonymousE != e.b) || (bool)) {
            break;
          }
          return null;
        }
        if (paramAnonymousE == e.a)
        {
          Bastion.a(paramActivity.getApplicationContext());
          Bastion.f();
          if (!a("android.permission.INTERNET", Bastion.g()))
          {
            com.bastionsdk.android.a.n.a(false, "Bastion needs android.permission.INTERNET, please update your manifest, abording start");
            return null;
          }
          try
          {
            Bastion.b((String)paramActivity.getPackageManager().getApplicationInfo(paramActivity.getPackageName(), 128).metaData.get("com.bastionsdk.android.API_KEY"));
            if (Bastion.h() == null)
            {
              com.bastionsdk.android.a.n.a(false, "No API key found in your manifest for Bastion, please update com.bastionsdk.android.API_KEY meta-data, abording start");
              return null;
            }
          }
          catch (Exception paramAnonymousE)
          {
            com.bastionsdk.android.a.n.a(false, "Bastion is unable to retrieve API key from the manifest, abording start", paramAnonymousE);
            return null;
          }
          Bastion.a(new i(Bastion.g()));
          Bastion.a(new k(Bastion.g()));
          Bastion.a(new s(Bastion.g()));
          Bastion.a(new BroadcastReceiver()
          {
            public final void onReceive(Context paramAnonymous2Context, Intent paramAnonymous2Intent)
            {
              if ("com.bastionsdk.android.executor.finished".equals(paramAnonymous2Intent.getAction())) {
                Bastion.i();
              }
            }
          });
          paramAnonymousE = new IntentFilter();
          paramAnonymousE.addAction("com.bastionsdk.android.executor.finished");
          Bastion.g().registerReceiver(Bastion.j(), paramAnonymousE);
        }
        return e.b;
      }
    })) || (m != null))
    {
      n.a(e.b, new f()
      {
        public final void a(e paramAnonymousE)
        {
          if ((Bastion.k() != null) && (Bastion.l() != null)) {
            Bastion.d().b(new Runnable()
            {
              public final void run()
              {
                if (Bastion.l() != null) {
                  Bastion.l().onURLWithCodeFound(Bastion.k());
                }
              }
            });
          }
          try
          {
            z.a(Bastion.g()).a(new n(Bastion.g(), new o(Bastion.d(), Bastion.g(), Bastion.l()), Bastion.k(), Bastion.m()));
            Bastion.a(null);
            return;
          }
          catch (Exception paramAnonymousE)
          {
            for (;;)
            {
              com.bastionsdk.android.a.n.a("Error while initializing StartWebservice", paramAnonymousE);
            }
          }
        }
      });
      if (isRunningInDevMode()) {
        com.bastionsdk.android.a.n.b(false, "Bastion is running in dev mode (your API key is a dev one)");
      }
    }
  }
  
  public static void onStop(Activity paramActivity)
  {
    a(false);
  }
  
  private static void p()
  {
    AtomicBoolean localAtomicBoolean = new AtomicBoolean(false);
    n.a(e.c, new f()
    {
      public final void a(e paramAnonymousE)
      {
        this.a.set(true);
      }
    });
    com.bastionsdk.android.a.n.c("onWebserviceExecutorWorkFinished called, should stop " + localAtomicBoolean);
    if (localAtomicBoolean.get()) {
      q();
    }
  }
  
  private static void q()
  {
    n.a(e.c, new com.bastionsdk.android.d.a()
    {
      public final e a(e paramAnonymousE)
      {
        com.bastionsdk.android.a.n.c("doStop, called with state " + paramAnonymousE);
        if ((Bastion.n() != null) && (Bastion.n().size() > 0))
        {
          com.bastionsdk.android.a.n.c("doStop, failed send : " + Bastion.n().size() + " to save");
          if (!q.a(Bastion.g()).a("failedSend", Bastion.n())) {
            com.bastionsdk.android.a.n.a("Error while saving failedSend features, " + Bastion.n().size() + " features lost");
          }
        }
        for (;;)
        {
          Bastion.g().unregisterReceiver(Bastion.j());
          Bastion.a(null);
          Bastion.b(null);
          Bastion.a(null);
          Bastion.a(null);
          Bastion.a(null);
          Bastion.a(null);
          Bastion.a(null);
          com.bastionsdk.android.a.a.b();
          w.a();
          z.b();
          com.bastionsdk.android.a.g.b();
          q.a();
          return e.a;
          com.bastionsdk.android.a.n.c("doStop, failed send : nothing to save");
        }
      }
    });
  }
  
  private static List<Offer> r()
  {
    try
    {
      Object localObject = q.a(e).b("failedSend");
      if (localObject == null)
      {
        com.bastionsdk.android.a.n.c("handleFailedSend called, nothing retrieved from storage");
        return null;
      }
      localObject = (List)localObject;
      com.bastionsdk.android.a.n.c("handleFailedSend called, " + ((List)localObject).size() + " retrieved");
      q.a(e).c("failedSend");
      int i1 = ((List)localObject).size();
      if (i1 > 0) {
        return ???;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        com.bastionsdk.android.a.n.a("Error while reading failedSend", localException);
      }
      return localException;
    }
    return null;
  }
  
  public static void redeemCode(String paramString, final BastionCodeListener paramBastionCodeListener)
  {
    if (paramBastionCodeListener == null) {
      com.bastionsdk.android.a.n.a(false, "Bastion.redeemCode called with null BastionCodeListener, abording");
    }
    do
    {
      return;
      if (paramString == null)
      {
        com.bastionsdk.android.a.n.a(false, "Bastion.redeemCode called with null code");
        paramBastionCodeListener.onRedeemCodeFailed(null, FailReason.UNEXPECTED_ERROR, null);
        return;
      }
      com.bastionsdk.android.a.n.c("unlockCode called for code : " + paramString);
    } while (n.a(new Runnable()
    {
      public final void run()
      {
        try
        {
          z.a(Bastion.g()).a(new g(Bastion.g(), this.a, new h(Bastion.d(), this.a, paramBastionCodeListener)));
          return;
        }
        catch (Exception localException)
        {
          Bastion.d().b(new Runnable()
          {
            public final void run()
            {
              Bastion.9.this.b.onRedeemCodeFailed(Bastion.9.this.a, FailReason.UNEXPECTED_ERROR, null);
            }
          });
        }
      }
    }));
    com.bastionsdk.android.a.n.a(false, "Bastion.redeemCode called when Bastion is not started, you must call Bastion.onStart before any other action");
    paramBastionCodeListener.onRedeemCodeFailed(paramString, FailReason.UNEXPECTED_ERROR, null);
  }
  
  public static void restore(BastionRestoreListener paramBastionRestoreListener)
  {
    if (paramBastionRestoreListener == null) {
      com.bastionsdk.android.a.n.a(false, "Bastion.restore called with null BastionRestoreListener, abording");
    }
    do
    {
      return;
      com.bastionsdk.android.a.n.c("restore called");
    } while (n.a(new Runnable()
    {
      public final void run()
      {
        try
        {
          z.a(Bastion.g()).a(new l(Bastion.g(), new m(Bastion.d(), this.a)));
          return;
        }
        catch (Exception localException)
        {
          Bastion.d().b(new Runnable()
          {
            public final void run()
            {
              Bastion.10.this.a.onRestoreFailed(FailReason.UNEXPECTED_ERROR);
            }
          });
        }
      }
    }));
    com.bastionsdk.android.a.n.a(false, "Bastion.restore called when Bastion is not started, you must call Bastion.onStart before any other action");
    paramBastionRestoreListener.onRestoreFailed(FailReason.UNEXPECTED_ERROR);
  }
  
  private static void s()
  {
    try
    {
      String str = w.a(e).a("app.version.current");
      if (str == null)
      {
        w.a(e).a("app.version.current", "0.1", true);
        return;
      }
      if (!str.equals("0.1"))
      {
        w.a(e).a("app.version.current", "0.1", true);
        w.a(e).a("app.version.previous", str, true);
        return;
      }
    }
    catch (Exception localException)
    {
      com.bastionsdk.android.a.n.a("Error on updateVersionManagement", localException);
    }
  }
  
  private static void t() {}
  
  static final class a
    implements c
  {
    private a() {}
    
    public final void a(final Offer paramOffer)
    {
      try
      {
        Bastion.d().a(new f()
        {
          public final void a(e paramAnonymousE)
          {
            if (paramAnonymousE == e.a)
            {
              com.bastionsdk.android.a.n.a("Fail send to app while in stop state, unlockables lost");
              return;
            }
            if (Bastion.n() == null) {
              Bastion.a(new ArrayList());
            }
            Bastion.n().add(paramOffer);
          }
        });
        return;
      }
      finally
      {
        paramOffer = finally;
        throw paramOffer;
      }
    }
  }
}
