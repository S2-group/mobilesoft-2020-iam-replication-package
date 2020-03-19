package com.calldorado.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import c.D7P;
import c.FI7;
import c.HL;
import c.HT;
import c.HT._3;
import c.MRO;
import c.PF;
import c.R;
import c.R._3;
import c.R1D;
import c.R9;
import c.RT;
import c.RZ;
import c.RZ_;
import c.SU_;
import c.UF1;
import c.XRU;
import c.ZW8;
import com.calldorado.android.ui.views.custom.CalldoradoCustomView;
import com.calldorado.android.ui.wic.WICController;
import com.firebase.jobdispatcher.e;
import com.firebase.jobdispatcher.g;
import com.p3group.insight.InsightCore;
import com.tutelatechnologies.c1o.sdk.framework.TutelaSDK;
import com.tutelatechnologies.c1o.sdk.framework.TutelaSDKFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalldoradoApplication
{
  public static String a = "https://traffic.calldorado.com";
  public static String b;
  private static final String c = "CalldoradoApplication";
  private static CalldoradoApplication d;
  private static final byte[] x = { 50, 100, -43, 12, 9, 8, -12, 3, 2, -10, 21, -4, 5, -5, -56, 53, -50, 48, 9, 10, -67, 63, 6, -8, -57, 55, 12, -14, 6, 35, -48, 52, -68, 20, 18, 25, 6, -46, 50, -34, 20, 19, -17, 10, -57, 68, -19, 18, -42, 13, -11, -8, 11, 0, -1, 0, 27, 5, 9, -11, -39, 34, -3, 1, 2, -12, -3, 35, -66, 2, 19, -11, 10, 11, 23 };
  private static int y = 131;
  private ClientConfig e = null;
  private UF1 f = null;
  private FI7 g = null;
  private WICController h = null;
  private R9 i = null;
  private MRO j = null;
  private R1D k = null;
  private RT l = null;
  private Typeface m;
  private CalldoradoCustomView n;
  private CalldoradoCustomView o;
  private CalldoradoCustomView p;
  private SU_ q;
  private boolean r;
  private final String s = "SEC_SERVICE_PASS";
  private final String t = "SEC_SERVICE_SALT";
  private e u;
  private String v;
  private final BroadcastReceiver w = new BroadcastReceiver()
  {
    public void onReceive(final Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      try
      {
        if (paramAnonymousIntent.getBooleanExtra("tutelaSdkDeploymentKeyStatusExtra", false))
        {
          D7P.a(CalldoradoApplication.x(), "Tutela SDK successfully initialized. Setting ID");
          new HT(paramAnonymousContext, new HT._3()
          {
            public void a(String paramAnonymous2String)
            {
              if (paramAnonymousContext != null) {
                TutelaSDKFactory.getTheSDK().setAaid(paramAnonymous2String, paramAnonymousContext);
              }
            }
          }).execute(new Void[0]);
        }
        else
        {
          D7P.a(CalldoradoApplication.x(), "Tutela SDK not successfully initialized.");
        }
      }
      catch (Exception paramAnonymousIntent)
      {
        paramAnonymousIntent.printStackTrace();
      }
      TutelaSDKFactory.getTheSDK().unRegisterReceiver(paramAnonymousContext, CalldoradoApplication.a(CalldoradoApplication.this));
    }
  };
  
  private CalldoradoApplication(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    D7P.a("calldoradoApp", "Application CalldoradoApplication");
    h(paramContext);
    this.e = new ClientConfig(paramContext);
    this.f = new UF1(paramContext);
    this.g = new FI7(paramContext);
    this.l = new RT(paramContext);
    this.h = new WICController(paramContext);
    this.i = new R9(paramContext);
    this.j = new MRO(paramContext);
    this.k = new R1D();
    this.m = RZ.j(paramContext);
    boolean bool;
    if ((RZ.e(paramContext)) && (RZ.f(paramContext))) {
      bool = true;
    } else {
      bool = false;
    }
    this.r = bool;
    this.u = new e(new g(paramContext));
    this.q = new SU_(paramContext, this.e);
    if (!RZ.g(paramContext)) {
      if ((this.e.aT()) && (RZ.c(this.e.bB(), this.e.bD())))
      {
        if (Build.VERSION.SDK_INT >= 14) {
          try
          {
            InsightCore.init(paramContext.getApplicationContext(), XRU.a());
            String str = c;
            StringBuilder localStringBuilder = new StringBuilder("P3 GUID: ");
            localStringBuilder.append(InsightCore.getGUID());
            D7P.a(str, localStringBuilder.toString());
            if (!InsightCore.isInitialized()) {
              break label452;
            }
            D7P.a(c, "P3 IS initialized!!");
            if (!InsightCore.getConnectivityTestEnabled()) {
              InsightCore.setConnectivityTestEnabled(true);
            }
            if (!InsightCore.getCoverageMapperServiceEnabled()) {
              InsightCore.setCoverageMapperServiceEnabled(true);
            }
            if (!InsightCore.getVoiceServiceEnabled()) {
              InsightCore.setVoiceServiceEnabled(true);
            }
            if (!InsightCore.getAppUsageServiceEnabled()) {
              InsightCore.setAppUsageServiceEnabled(true);
            }
            if (InsightCore.getTrafficAnalyzerEnabled()) {
              break label452;
            }
            InsightCore.setTrafficAnalyzerEnabled(true);
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
          }
        }
      }
      else if (InsightCore.isInitialized())
      {
        InsightCore.setConnectivityTestEnabled(false);
        InsightCore.setCoverageMapperServiceEnabled(false);
        InsightCore.setVoiceServiceEnabled(false);
        InsightCore.setAppUsageServiceEnabled(false);
        InsightCore.setTrafficAnalyzerEnabled(false);
        D7P.a(c, "Deactivating P3");
      }
    }
    label452:
    if ((this.e.aU()) && (RZ.c(this.e.bB(), this.e.bC())) && (Build.VERSION.SDK_INT >= 17) && (!RZ.g(paramContext)))
    {
      D7P.a(c, "Mars Media is initialized!");
      HL.a(paramContext.getApplicationContext());
      HL.a("e111e0e82cd9d0d047c02375b5f26422");
    }
    if ((this.e.cp()) && (RZ.c(this.e.bB(), this.e.cq())) && (Build.VERSION.SDK_INT >= 17))
    {
      IntentFilter localIntentFilter = new IntentFilter("tutelaSdkDeploymentKeyBroadcast");
      TutelaSDKFactory.getTheSDK().registerReceiver(paramContext, this.w, localIntentFilter);
      try
      {
        TutelaSDKFactory.getTheSDK().initializeWithApiKey(a((byte)x[35], (byte)x[17], (byte)x[53]).intern(), paramContext.getApplicationContext());
        TutelaSDKFactory.getTheSDK().setAaid(a((byte)x[35], (byte)x[17], (byte)x[53]).intern(), paramContext.getApplicationContext());
        D7P.a(c, "Tutela is initialized!");
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    else if (TutelaSDKFactory.getTheSDK().isTutelaServiceActive(paramContext))
    {
      TutelaSDKFactory.getTheSDK().stopTutelaService(paramContext);
    }
    new R(paramContext, new R._3()
    {
      public void a(String paramAnonymousString)
      {
        CalldoradoApplication.a(CalldoradoApplication.this, paramAnonymousString);
      }
    }).execute(new Void[0]);
    paramContext = new Bundle();
    paramContext.putString("SEC_SERVICE_PASS", a((byte)(x[5] - 1), (byte)x[74], (byte)x[63]).intern());
    int i1 = (byte)x[53];
    paramContext.putString("SEC_SERVICE_SALT", a(i1, (byte)i1, (byte)x[63]).intern());
    this.e.a(paramContext);
  }
  
  public static CalldoradoApplication a(Context paramContext)
  {
    if ((d == null) && (paramContext != null)) {
      try
      {
        if ((d == null) && (paramContext != null))
        {
          D7P.a("calldoradoApp", "********** Application Is Null Create a New ************");
          d = new CalldoradoApplication(paramContext);
        }
      }
      finally {}
    }
    return d;
  }
  
  private static String a(int paramInt1, short paramShort, int paramInt2)
  {
    paramInt2 = 26 - 2 * paramInt2;
    Object localObject1 = new java/lang/String;
    Object localObject4 = x;
    paramShort = 52 - paramShort;
    byte[] arrayOfByte = new byte[paramInt2];
    int i2;
    Object localObject3;
    int i1;
    Object localObject2;
    if (localObject4 == null)
    {
      i2 = paramShort;
      paramInt1 = 0;
      localObject3 = localObject1;
      i1 = paramInt2;
      localObject2 = localObject1;
      localObject1 = localObject4;
    }
    else
    {
      i1 = paramShort;
      localObject3 = localObject4;
      paramShort = paramInt1 + 78;
      localObject2 = localObject1;
      i2 = 0;
      paramInt1 = i1;
    }
    for (;;)
    {
      int i3 = i2 + 1;
      arrayOfByte[i2] = ((byte)paramShort);
      if (i3 == paramInt2)
      {
        ((String)localObject1).<init>(arrayOfByte, 0);
        return localObject2;
      }
      int i4 = localObject3[paramInt1];
      i2 = paramShort;
      localObject4 = localObject1;
      i1 = paramInt2;
      paramInt2 = i4;
      paramShort = paramInt1;
      paramInt1 = i3;
      localObject1 = localObject3;
      localObject3 = localObject4;
      localObject4 = localObject1;
      paramShort += 1;
      paramInt2 = i2 + paramInt2 - 1;
      i2 = paramInt1;
      localObject1 = localObject3;
      localObject3 = localObject4;
      paramInt1 = paramShort;
      paramShort = paramInt2;
      paramInt2 = i1;
    }
  }
  
  private static void h(Context paramContext)
  {
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      Object localObject3;
      boolean bool;
      try
      {
        D7P.a("calldoradoApp", "renameOldSharedPrefs run ");
        if (paramContext == null) {}
      }
      finally {}
      try
      {
        if ((paramContext.getFilesDir() == null) || (paramContext.getFilesDir().getPath() == null)) {
          continue;
        }
        localObject1 = paramContext.getFilesDir().getPath();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(((String)localObject1).replace("files", "shared_prefs"));
        ((StringBuilder)localObject2).append("/");
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("adaffix.xml");
        localObject2 = new File(((StringBuilder)localObject2).toString());
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append((String)localObject1);
        ((StringBuilder)localObject3).append("adContainer.xml");
        localObject3 = new File(((StringBuilder)localObject3).toString());
        if ((((File)localObject2).exists()) && (!((File)localObject3).exists()))
        {
          StringBuilder localStringBuilder = new StringBuilder("old shared_prefs path1: ");
          localStringBuilder.append(localObject2);
          D7P.a("calldoradoApp", localStringBuilder.toString());
          bool = ((File)localObject2).renameTo((File)localObject3);
          localObject2 = new StringBuilder("shared_prefs1 renamed OK: ");
          ((StringBuilder)localObject2).append(bool);
          D7P.a("calldoradoApp", ((StringBuilder)localObject2).toString());
        }
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(paramContext.getPackageName());
        ((StringBuilder)localObject2).append("adaffix.xml");
        paramContext = new File(((StringBuilder)localObject2).toString());
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("calldorado.xml");
        localObject1 = new File(((StringBuilder)localObject2).toString());
        if ((!paramContext.exists()) || (((File)localObject1).exists())) {
          continue;
        }
        localObject2 = new StringBuilder("old shared_prefs path2: ");
        ((StringBuilder)localObject2).append(paramContext);
        D7P.a("calldoradoApp", ((StringBuilder)localObject2).toString());
        bool = paramContext.renameTo((File)localObject1);
        paramContext = new StringBuilder("shared_prefs2 renamed OK: ");
        paramContext.append(bool);
        D7P.a("calldoradoApp", paramContext.toString());
      }
      catch (NullPointerException paramContext) {}
    }
    return;
  }
  
  public boolean a()
  {
    String str = c;
    StringBuilder localStringBuilder = new StringBuilder("isEEA=");
    localStringBuilder.append(this.r);
    D7P.a(str, localStringBuilder.toString());
    return this.r;
  }
  
  public SU_ b()
  {
    return this.q;
  }
  
  public void b(Context paramContext)
  {
    this.e = new ClientConfig(paramContext);
    this.f = new UF1(paramContext);
    this.g = new FI7(paramContext);
    this.l = new RT(paramContext);
    this.h = new WICController(paramContext);
    this.i = new R9(paramContext);
    this.j = new MRO(paramContext);
    this.k = new R1D();
  }
  
  public e c()
  {
    return this.u;
  }
  
  public String c(Context paramContext)
  {
    if ((i().bd() == null) && (Build.VERSION.SDK_INT < 26))
    {
      i().t(Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
      paramContext = c;
      StringBuilder localStringBuilder = new StringBuilder("Android device ID: ");
      localStringBuilder.append(i().bd());
      D7P.c(paramContext, localStringBuilder.toString());
    }
    return i().bd();
  }
  
  public String d(Context paramContext)
  {
    if (b == null) {
      b = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
    }
    if ((b != null) && (b.length() > 3)) {
      localObject2 = b.substring(0, 3);
    } else {
      localObject2 = null;
    }
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (Build.VERSION.SDK_INT >= 22) {
        if (RZ_.a(paramContext, "android.permission.READ_PHONE_STATE"))
        {
          D7P.c(c, "GRANTED MCC");
          localObject1 = new R1D().a(paramContext, 0);
        }
        else
        {
          D7P.e(c, "DENIED MCC - tryin fallback");
          localObject1 = localObject2;
        }
      }
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = f(paramContext);
    }
    paramContext = c;
    localObject1 = new StringBuilder("MCC: ");
    ((StringBuilder)localObject1).append((String)localObject2);
    D7P.a(paramContext, ((StringBuilder)localObject1).toString());
    return localObject2;
  }
  
  public void d()
  {
    c().a("calldoradoApp");
    D7P.a(c, "Cancelling JobScheduler");
  }
  
  public RT e()
  {
    return this.l;
  }
  
  public String e(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 22) {
      if (RZ_.a(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        D7P.c(c, "GRANTED MNC");
        if (this.k != null)
        {
          localObject2 = this.k.a(paramContext, 1);
          localObject1 = localObject2;
          if (this.k.a()) {
            break label91;
          }
          localObject1 = localObject2;
          if (Looper.myLooper() != Looper.getMainLooper()) {
            break label91;
          }
          this.k.a(paramContext);
          localObject1 = localObject2;
          break label91;
        }
      }
      else
      {
        D7P.e(c, "DENIED MNC - tryin fallback");
      }
    }
    Object localObject1 = null;
    label91:
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      if (b == null) {
        b = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
      }
      localObject2 = localObject1;
      if (b != null)
      {
        localObject2 = localObject1;
        if (b.length() > 3) {
          localObject2 = b.substring(3);
        }
      }
    }
    paramContext = c;
    localObject1 = new StringBuilder("MNC: ");
    ((StringBuilder)localObject1).append((String)localObject2);
    D7P.a(paramContext, ((StringBuilder)localObject1).toString());
    return localObject2;
  }
  
  public CalldoradoCustomView f()
  {
    return this.p;
  }
  
  public String f(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration().locale.getCountry().toLowerCase();
    if (PF.a == null) {
      PF.a = new PF();
    }
    return String.valueOf(PF.a.a().get(paramContext));
  }
  
  public CalldoradoCustomView g()
  {
    return this.o;
  }
  
  public String g(Context paramContext)
  {
    try
    {
      Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(128).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (localPackageInfo.packageName == paramContext.getPackageName())
        {
          paramContext = ZW8.a(localPackageInfo.applicationInfo);
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      D7P.b(c, "Exception getInstallerName", paramContext);
    }
    return "";
  }
  
  public CalldoradoCustomView h()
  {
    return this.n;
  }
  
  public ClientConfig i()
  {
    return this.e;
  }
  
  public R9 j()
  {
    return this.i;
  }
  
  public MRO k()
  {
    return this.j;
  }
  
  public String l()
  {
    return this.v;
  }
  
  public String m()
  {
    D7P.a(c, "BNID = apk-5.0.27.1606");
    return "apk-5.0.27.1606";
  }
  
  public String n()
  {
    String[] arrayOfString = "5.0.27.1606".split("\\.");
    if (arrayOfString != null)
    {
      String str = c;
      StringBuilder localStringBuilder = new StringBuilder("getVersion() array length: ");
      localStringBuilder.append(arrayOfString.length);
      D7P.a(str, localStringBuilder.toString());
    }
    if ((arrayOfString != null) && (arrayOfString.length == 4)) {
      return "5.0.27.1606".substring(0, "5.0.27.1606".lastIndexOf("."));
    }
    return "5.0.27.1606";
  }
  
  public String o()
  {
    D7P.a(c, "fullver = 5.0.27.1606");
    return "5.0.27.1606";
  }
  
  public String p()
  {
    Object localObject = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)").matcher(o());
    ((Matcher)localObject).find();
    String str = o();
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(((Matcher)localObject).group(1));
      localStringBuilder.append(".");
      localStringBuilder.append(((Matcher)localObject).group(2));
      localStringBuilder.append(".");
      localStringBuilder.append(((Matcher)localObject).group(3));
      localObject = localStringBuilder.toString();
    }
    catch (Exception localException3)
    {
      StringBuilder localStringBuilder;
      label114:
      for (;;) {}
    }
    try
    {
      str = c;
      localStringBuilder = new StringBuilder("getStrippedVersion = ");
      localStringBuilder.append((String)localObject);
      D7P.a(str, localStringBuilder.toString());
      return localObject;
    }
    catch (Exception localException1)
    {
      Exception localException2 = localException3;
      break label114;
    }
    localObject = c;
    localStringBuilder = new StringBuilder("getStrippedVersion failed = ");
    localStringBuilder.append(str);
    D7P.a((String)localObject, localStringBuilder.toString());
    return str;
  }
  
  public String q()
  {
    String str;
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append("model=");
      ((StringBuilder)localObject).append(Build.MODEL);
      str = ((StringBuilder)localObject).toString();
      try
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(str);
        ((StringBuilder)localObject).append(",manufacturer=");
        ((StringBuilder)localObject).append(Build.MANUFACTURER);
        localObject = ((StringBuilder)localObject).toString();
        return localObject;
      }
      catch (Exception localException1) {}
      D7P.b(c, "Exception getAndroidModelManufacturer", localException2);
    }
    catch (Exception localException2)
    {
      str = "";
    }
    return str;
  }
  
  public String r()
  {
    try
    {
      String str = Build.VERSION.RELEASE;
      return str;
    }
    catch (Exception localException)
    {
      D7P.b(c, "Exception getAndroidVersion", localException);
    }
    return "";
  }
  
  public int s()
  {
    try
    {
      int i1 = Build.VERSION.SDK_INT;
      return i1;
    }
    catch (Exception localException)
    {
      D7P.b(c, "Exception getAndroidSdk", localException);
    }
    return 0;
  }
  
  public UF1 t()
  {
    return this.f;
  }
  
  public WICController u()
  {
    return this.h;
  }
  
  public FI7 v()
  {
    return this.g;
  }
  
  public Typeface w()
  {
    return this.m;
  }
}
