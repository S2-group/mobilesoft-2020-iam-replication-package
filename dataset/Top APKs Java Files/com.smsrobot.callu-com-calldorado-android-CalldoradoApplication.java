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
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
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
  public static String ˊ = "https://traffic.calldorado.com";
  public static String ˋ;
  private static final String ˎ = "CalldoradoApplication";
  private static CalldoradoApplication ˏ;
  private static final byte[] ՙ = { 50, 100, -43, 12, 9, 8, -12, 3, 2, -10, 21, -4, 5, -5, -56, 53, -50, 48, 9, 10, -67, 63, 6, -8, -57, 55, 12, -14, 6, 35, -48, 52, -68, 20, 18, 25, 6, -46, 50, -34, 20, 19, -17, 10, -57, 68, -19, 18, -42, 13, -11, -8, 11, 0, -1, 0, 27, 5, 9, -11, -39, 34, -3, 1, 2, -12, -3, 35, -66, 2, 19, -11, 10, 11, 23 };
  private static int י = 131;
  private final BroadcastReceiver ʹ = new BroadcastReceiver()
  {
    public void onReceive(final Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      try
      {
        if (paramAnonymousIntent.getBooleanExtra("tutelaSdkDeploymentKeyStatusExtra", false))
        {
          D7P.ˊ(CalldoradoApplication.ՙ(), "Tutela SDK successfully initialized. Setting ID");
          new HT(paramAnonymousContext, new HT._3()
          {
            public void ˊ(String paramAnonymous2String)
            {
              if (paramAnonymousContext != null) {
                TutelaSDKFactory.getTheSDK().setAaid(paramAnonymous2String, paramAnonymousContext);
              }
            }
          }).execute(new Void[0]);
        }
        else
        {
          D7P.ˊ(CalldoradoApplication.ՙ(), "Tutela SDK not successfully initialized.");
        }
      }
      catch (Exception paramAnonymousIntent)
      {
        paramAnonymousIntent.printStackTrace();
      }
      TutelaSDKFactory.getTheSDK().unRegisterReceiver(paramAnonymousContext, CalldoradoApplication.ˊ(CalldoradoApplication.this));
    }
  };
  private UF1 ʻ = null;
  private FI7 ʼ = null;
  private WICController ʽ = null;
  private R1D ʾ = null;
  private RT ʿ = null;
  private Typeface ˈ;
  private CalldoradoCustomView ˉ;
  private CalldoradoCustomView ˌ;
  private CalldoradoCustomView ˍ;
  private SU_ ˑ;
  private R9 ͺ = null;
  private boolean ـ;
  private ClientConfig ᐝ = null;
  private final String ᐧ = "SEC_SERVICE_PASS";
  private final String ᐨ = "SEC_SERVICE_SALT";
  private MRO ι = null;
  private FirebaseJobDispatcher ﹳ;
  private String ﾞ;
  
  private CalldoradoApplication(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    D7P.ˊ("calldoradoApp", "Application CalldoradoApplication");
    ʽ(paramContext);
    this.ᐝ = new ClientConfig(paramContext);
    this.ʻ = new UF1(paramContext);
    this.ʼ = new FI7(paramContext);
    this.ʿ = new RT(paramContext);
    this.ʽ = new WICController(paramContext);
    this.ͺ = new R9(paramContext);
    this.ι = new MRO(paramContext);
    this.ʾ = new R1D();
    this.ˈ = RZ.ι(paramContext);
    boolean bool;
    if ((RZ.ᐝ(paramContext)) && (RZ.ʻ(paramContext))) {
      bool = true;
    } else {
      bool = false;
    }
    this.ـ = bool;
    this.ﹳ = new FirebaseJobDispatcher(new GooglePlayDriver(paramContext));
    this.ˑ = new SU_(paramContext, this.ᐝ);
    if (!RZ.ʼ(paramContext)) {
      if ((this.ᐝ.ᔉ()) && (RZ.ˎ(this.ᐝ.ᵘ(), this.ᐝ.ⁿ())))
      {
        if (Build.VERSION.SDK_INT >= 14) {
          try
          {
            InsightCore.init(paramContext.getApplicationContext(), XRU.ˊ());
            String str = ˎ;
            StringBuilder localStringBuilder = new StringBuilder("P3 GUID: ");
            localStringBuilder.append(InsightCore.getGUID());
            D7P.ˊ(str, localStringBuilder.toString());
            if (!InsightCore.isInitialized()) {
              break label452;
            }
            D7P.ˊ(ˎ, "P3 IS initialized!!");
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
        D7P.ˊ(ˎ, "Deactivating P3");
      }
    }
    label452:
    if ((this.ᐝ.ᔊ()) && (RZ.ˎ(this.ᐝ.ᵘ(), this.ᐝ.ᵤ())) && (Build.VERSION.SDK_INT >= 17) && (!RZ.ʼ(paramContext)))
    {
      D7P.ˊ(ˎ, "Mars Media is initialized!");
      HL.ˊ(paramContext.getApplicationContext());
      HL.ˊ("e111e0e82cd9d0d047c02375b5f26422");
    }
    if ((this.ᐝ.ﺒ()) && (RZ.ˎ(this.ᐝ.ᵘ(), this.ᐝ.ﺛ())) && (Build.VERSION.SDK_INT >= 17))
    {
      IntentFilter localIntentFilter = new IntentFilter("tutelaSdkDeploymentKeyBroadcast");
      TutelaSDKFactory.getTheSDK().registerReceiver(paramContext, this.ʹ, localIntentFilter);
      try
      {
        TutelaSDKFactory.getTheSDK().initializeWithApiKey(ˊ((byte)ՙ[35], (byte)ՙ[17], (byte)ՙ[53]).intern(), paramContext.getApplicationContext());
        TutelaSDKFactory.getTheSDK().setAaid(ˊ((byte)ՙ[35], (byte)ՙ[17], (byte)ՙ[53]).intern(), paramContext.getApplicationContext());
        D7P.ˊ(ˎ, "Tutela is initialized!");
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
      public void ˊ(String paramAnonymousString)
      {
        CalldoradoApplication.ˊ(CalldoradoApplication.this, paramAnonymousString);
      }
    }).execute(new Void[0]);
    paramContext = new Bundle();
    paramContext.putString("SEC_SERVICE_PASS", ˊ((byte)(ՙ[5] - 1), (byte)ՙ[74], (byte)ՙ[63]).intern());
    int i = (byte)ՙ[53];
    paramContext.putString("SEC_SERVICE_SALT", ˊ(i, (byte)i, (byte)ՙ[63]).intern());
    this.ᐝ.ˊ(paramContext);
  }
  
  private static void ʽ(Context paramContext)
  {
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      Object localObject3;
      boolean bool;
      try
      {
        D7P.ˊ("calldoradoApp", "renameOldSharedPrefs run ");
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
          D7P.ˊ("calldoradoApp", localStringBuilder.toString());
          bool = ((File)localObject2).renameTo((File)localObject3);
          localObject2 = new StringBuilder("shared_prefs1 renamed OK: ");
          ((StringBuilder)localObject2).append(bool);
          D7P.ˊ("calldoradoApp", ((StringBuilder)localObject2).toString());
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
        D7P.ˊ("calldoradoApp", ((StringBuilder)localObject2).toString());
        bool = paramContext.renameTo((File)localObject1);
        paramContext = new StringBuilder("shared_prefs2 renamed OK: ");
        paramContext.append(bool);
        D7P.ˊ("calldoradoApp", paramContext.toString());
      }
      catch (NullPointerException paramContext) {}
    }
    return;
  }
  
  public static CalldoradoApplication ˊ(Context paramContext)
  {
    if ((ˏ == null) && (paramContext != null)) {
      try
      {
        if ((ˏ == null) && (paramContext != null))
        {
          D7P.ˊ("calldoradoApp", "********** Application Is Null Create a New ************");
          ˏ = new CalldoradoApplication(paramContext);
        }
      }
      finally {}
    }
    return ˏ;
  }
  
  private static String ˊ(int paramInt1, short paramShort, int paramInt2)
  {
    paramInt2 = 26 - 2 * paramInt2;
    Object localObject1 = new java/lang/String;
    Object localObject4 = ՙ;
    paramShort = 52 - paramShort;
    byte[] arrayOfByte = new byte[paramInt2];
    int j;
    Object localObject3;
    int i;
    Object localObject2;
    if (localObject4 == null)
    {
      j = paramShort;
      paramInt1 = 0;
      localObject3 = localObject1;
      i = paramInt2;
      localObject2 = localObject1;
      localObject1 = localObject4;
    }
    else
    {
      i = paramShort;
      localObject3 = localObject4;
      paramShort = paramInt1 + 78;
      localObject2 = localObject1;
      j = 0;
      paramInt1 = i;
    }
    for (;;)
    {
      int k = j + 1;
      arrayOfByte[j] = ((byte)paramShort);
      if (k == paramInt2)
      {
        ((String)localObject1).<init>(arrayOfByte, 0);
        return localObject2;
      }
      int m = localObject3[paramInt1];
      j = paramShort;
      localObject4 = localObject1;
      i = paramInt2;
      paramInt2 = m;
      paramShort = paramInt1;
      paramInt1 = k;
      localObject1 = localObject3;
      localObject3 = localObject4;
      localObject4 = localObject1;
      paramShort += 1;
      paramInt2 = j + paramInt2 - 1;
      j = paramInt1;
      localObject1 = localObject3;
      localObject3 = localObject4;
      paramInt1 = paramShort;
      paramShort = paramInt2;
      paramInt2 = i;
    }
  }
  
  public Typeface ʹ()
  {
    return this.ˈ;
  }
  
  public CalldoradoCustomView ʻ()
  {
    return this.ˍ;
  }
  
  public String ʻ(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration().locale.getCountry().toLowerCase();
    if (PF.ˊ == null) {
      PF.ˊ = new PF();
    }
    return String.valueOf(PF.ˊ.ˊ().get(paramContext));
  }
  
  public CalldoradoCustomView ʼ()
  {
    return this.ˌ;
  }
  
  public String ʼ(Context paramContext)
  {
    try
    {
      Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(128).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (localPackageInfo.packageName == paramContext.getPackageName())
        {
          paramContext = ZW8.ˊ(localPackageInfo.applicationInfo);
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      D7P.ˋ(ˎ, "Exception getInstallerName", paramContext);
    }
    return "";
  }
  
  public CalldoradoCustomView ʽ()
  {
    return this.ˉ;
  }
  
  public MRO ʾ()
  {
    return this.ι;
  }
  
  public String ʿ()
  {
    return this.ﾞ;
  }
  
  public String ˈ()
  {
    D7P.ˊ(ˎ, "BNID = apk-5.0.27.1606");
    return "apk-5.0.27.1606";
  }
  
  public String ˉ()
  {
    String[] arrayOfString = "5.0.27.1606".split("\\.");
    if (arrayOfString != null)
    {
      String str = ˎ;
      StringBuilder localStringBuilder = new StringBuilder("getVersion() array length: ");
      localStringBuilder.append(arrayOfString.length);
      D7P.ˊ(str, localStringBuilder.toString());
    }
    if ((arrayOfString != null) && (arrayOfString.length == 4)) {
      return "5.0.27.1606".substring(0, "5.0.27.1606".lastIndexOf("."));
    }
    return "5.0.27.1606";
  }
  
  public void ˊ(CalldoradoCustomView paramCalldoradoCustomView)
  {
    if (ͺ().ﹻ())
    {
      this.ˍ = paramCalldoradoCustomView;
      return;
    }
    this.ˍ = null;
  }
  
  public boolean ˊ()
  {
    String str = ˎ;
    StringBuilder localStringBuilder = new StringBuilder("isEEA=");
    localStringBuilder.append(this.ـ);
    D7P.ˊ(str, localStringBuilder.toString());
    return this.ـ;
  }
  
  public SU_ ˋ()
  {
    return this.ˑ;
  }
  
  public void ˋ(Context paramContext)
  {
    this.ᐝ = new ClientConfig(paramContext);
    this.ʻ = new UF1(paramContext);
    this.ʼ = new FI7(paramContext);
    this.ʿ = new RT(paramContext);
    this.ʽ = new WICController(paramContext);
    this.ͺ = new R9(paramContext);
    this.ι = new MRO(paramContext);
    this.ʾ = new R1D();
  }
  
  public void ˋ(CalldoradoCustomView paramCalldoradoCustomView)
  {
    if (ͺ().ﹻ())
    {
      this.ˌ = paramCalldoradoCustomView;
      return;
    }
    this.ˌ = null;
  }
  
  public String ˌ()
  {
    D7P.ˊ(ˎ, "fullver = 5.0.27.1606");
    return "5.0.27.1606";
  }
  
  public String ˍ()
  {
    Object localObject = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)").matcher(ˌ());
    ((Matcher)localObject).find();
    String str = ˌ();
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
      str = ˎ;
      localStringBuilder = new StringBuilder("getStrippedVersion = ");
      localStringBuilder.append((String)localObject);
      D7P.ˊ(str, localStringBuilder.toString());
      return localObject;
    }
    catch (Exception localException1)
    {
      Exception localException2 = localException3;
      break label114;
    }
    localObject = ˎ;
    localStringBuilder = new StringBuilder("getStrippedVersion failed = ");
    localStringBuilder.append(str);
    D7P.ˊ((String)localObject, localStringBuilder.toString());
    return str;
  }
  
  public FirebaseJobDispatcher ˎ()
  {
    return this.ﹳ;
  }
  
  public String ˎ(Context paramContext)
  {
    if ((ͺ().וּ() == null) && (Build.VERSION.SDK_INT < 26))
    {
      ͺ().ʹ(Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
      paramContext = ˎ;
      StringBuilder localStringBuilder = new StringBuilder("Android device ID: ");
      localStringBuilder.append(ͺ().וּ());
      D7P.ˎ(paramContext, localStringBuilder.toString());
    }
    return ͺ().וּ();
  }
  
  public void ˎ(CalldoradoCustomView paramCalldoradoCustomView)
  {
    if (ͺ().ﹻ())
    {
      this.ˉ = paramCalldoradoCustomView;
      return;
    }
    this.ˉ = null;
  }
  
  public String ˏ(Context paramContext)
  {
    if (ˋ == null) {
      ˋ = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
    }
    if ((ˋ != null) && (ˋ.length() > 3)) {
      localObject2 = ˋ.substring(0, 3);
    } else {
      localObject2 = null;
    }
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (Build.VERSION.SDK_INT >= 22) {
        if (RZ_.ˊ(paramContext, "android.permission.READ_PHONE_STATE"))
        {
          D7P.ˎ(ˎ, "GRANTED MCC");
          localObject1 = new R1D().ˊ(paramContext, 0);
        }
        else
        {
          D7P.ᐝ(ˎ, "DENIED MCC - tryin fallback");
          localObject1 = localObject2;
        }
      }
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = ʻ(paramContext);
    }
    paramContext = ˎ;
    localObject1 = new StringBuilder("MCC: ");
    ((StringBuilder)localObject1).append((String)localObject2);
    D7P.ˊ(paramContext, ((StringBuilder)localObject1).toString());
    return localObject2;
  }
  
  public void ˏ()
  {
    ˎ().cancel("calldoradoApp");
    D7P.ˊ(ˎ, "Cancelling JobScheduler");
  }
  
  public String ˑ()
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
      D7P.ˋ(ˎ, "Exception getAndroidModelManufacturer", localException2);
    }
    catch (Exception localException2)
    {
      str = "";
    }
    return str;
  }
  
  public ClientConfig ͺ()
  {
    return this.ᐝ;
  }
  
  public String ـ()
  {
    try
    {
      String str = Build.VERSION.RELEASE;
      return str;
    }
    catch (Exception localException)
    {
      D7P.ˋ(ˎ, "Exception getAndroidVersion", localException);
    }
    return "";
  }
  
  public RT ᐝ()
  {
    return this.ʿ;
  }
  
  public String ᐝ(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 22) {
      if (RZ_.ˊ(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        D7P.ˎ(ˎ, "GRANTED MNC");
        if (this.ʾ != null)
        {
          localObject2 = this.ʾ.ˊ(paramContext, 1);
          localObject1 = localObject2;
          if (this.ʾ.ˊ()) {
            break label91;
          }
          localObject1 = localObject2;
          if (Looper.myLooper() != Looper.getMainLooper()) {
            break label91;
          }
          this.ʾ.ˊ(paramContext);
          localObject1 = localObject2;
          break label91;
        }
      }
      else
      {
        D7P.ᐝ(ˎ, "DENIED MNC - tryin fallback");
      }
    }
    Object localObject1 = null;
    label91:
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      if (ˋ == null) {
        ˋ = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
      }
      localObject2 = localObject1;
      if (ˋ != null)
      {
        localObject2 = localObject1;
        if (ˋ.length() > 3) {
          localObject2 = ˋ.substring(3);
        }
      }
    }
    paramContext = ˎ;
    localObject1 = new StringBuilder("MNC: ");
    ((StringBuilder)localObject1).append((String)localObject2);
    D7P.ˊ(paramContext, ((StringBuilder)localObject1).toString());
    return localObject2;
  }
  
  public int ᐧ()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return i;
    }
    catch (Exception localException)
    {
      D7P.ˋ(ˎ, "Exception getAndroidSdk", localException);
    }
    return 0;
  }
  
  public UF1 ᐨ()
  {
    return this.ʻ;
  }
  
  public R9 ι()
  {
    return this.ͺ;
  }
  
  public WICController ﹳ()
  {
    return this.ʽ;
  }
  
  public FI7 ﾞ()
  {
    return this.ʼ;
  }
}
