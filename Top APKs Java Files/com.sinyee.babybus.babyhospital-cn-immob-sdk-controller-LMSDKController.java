package cn.immob.sdk.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import cn.immob.sdk.ImmobView;
import cn.immob.sdk.LMAdListener;
import cn.immob.sdk.ay;
import cn.immob.sdk.be;
import cn.immob.sdk.bf;
import cn.immob.sdk.bh;
import cn.immob.sdk.bo;
import cn.immob.sdk.bq;
import cn.immob.sdk.l;
import cn.immob.sdk.net.DownloadService;
import java.io.File;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import javax.crypto.Cipher;

public class LMSDKController
{
  public static final String ADWALL_APPSTATUS_DOWNLOADED = "downloaded";
  public static final String ADWALL_APPSTATUS_DOWNLOADING = "downloading";
  public static final String ADWALL_APPSTATUS_INQUEUE = "inqueue";
  public static final String ADWALL_APPSTATUS_INSTALLED = "installed";
  public static final String ADWALL_APPSTATUS_NOT_INSTALLED = "notInstalled";
  private static Cipher a;
  private Context b = null;
  private LMAdListener c;
  private ImmobView d;
  private String e = "LMSDKController";
  private int f = 4;
  private int g = 9;
  private String h = "0001";
  
  public LMSDKController(Context paramContext)
  {
    this.b = paramContext;
  }
  
  private boolean c(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString.trim())) || (this.b == null)) {}
    for (;;)
    {
      return false;
      try
      {
        int i = this.b.checkCallingOrSelfPermission(paramString);
        if (i == 0) {
          return true;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return false;
  }
  
  private String d(String paramString)
  {
    SharedPreferences localSharedPreferences = this.b.getSharedPreferences("AdProperties", 2);
    paramString = paramString + ImmobView.adUnitID;
    bo.b(this.e, "getClickUrl() -- key is:" + paramString);
    paramString = localSharedPreferences.getString(paramString, null);
    bo.b(this.e, "getClickUrl() -- b is:" + paramString);
    return paramString;
  }
  
  public static List getAllInstallSoft(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    bo.a("tag", "getAllInstallSoft");
    return paramContext.getPackageManager().getInstalledPackages(0);
  }
  
  public static File getFile(String paramString, boolean paramBoolean)
  {
    if ((paramString == null) && ("".equals(paramString))) {
      paramString = null;
    }
    File localFile;
    do
    {
      do
      {
        return paramString;
        localFile = new File(paramString);
        paramString = localFile;
      } while (localFile.exists());
      paramString = localFile;
    } while (!paramBoolean);
    localFile.mkdirs();
    return localFile;
  }
  
  public static String getUUID()
  {
    String str = UUID.randomUUID().toString();
    return "Lmmob" + str.replaceAll("-", "");
  }
  
  public void FWReady()
  {
    if (this.d != null) {
      this.d.fwR();
    }
  }
  
  protected void a(String paramString)
  {
    SharedPreferences.Editor localEditor = this.b.getSharedPreferences("SDKMAC", 2).edit();
    localEditor.putString("mac", paramString);
    localEditor.commit();
  }
  
  protected void b(String paramString)
  {
    SharedPreferences.Editor localEditor = this.b.getSharedPreferences("SDKIMEI", 2).edit();
    localEditor.putString("imei", paramString);
    localEditor.commit();
  }
  
  public void fireAdReceiveEvent(String paramString)
  {
    bo.a(this.e, "fireAdReceiveEvent");
    if (this.d != null) {
      this.d.adReceive(paramString);
    }
  }
  
  public void fireCPAAction(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    bo.a(this.e, "fireCPAAction() -- the packagename:" + paramString2);
    if (!c("android.permission.WRITE_EXTERNAL_STORAGE")) {
      Log.e(this.e, "don't have the android.Manifest.permission.WRITE_EXTERNAL_STORAGE permission so can't request server to get the apk!");
    }
    while (this.d == null) {
      return;
    }
    this.d.downloadSoft(paramString1, paramString2, paramString3, paramString4);
  }
  
  public void fireInstallAction(String paramString1, String paramString2)
  {
    bo.a(this.e, "fireInstallAction");
    if (this.d != null) {
      this.d.installDownloadSoft(paramString1, paramString2);
    }
  }
  
  public void fireTurnOffADUnit()
  {
    bo.a(this.e, "fireTurnOffADUnit");
    if (this.d != null)
    {
      bo.b(this.e, "fireTurnOffADUnit has appear");
      this.d.turnOffADUnit();
    }
  }
  
  public void fireUpdateFramework(String paramString)
  {
    bo.a(this.e, "fireUpdateFramework");
    if (this.d == null)
    {
      bo.b(this.e, "view is null");
      return;
    }
    this.d.setLoadWebView(false);
    this.d.requestFramework(paramString);
  }
  
  public void fireUploadDeviceInfo(String paramString)
  {
    bo.a(this.e, "fireUploadDeviceInfo");
    if (this.d == null)
    {
      bo.b(this.e, "view is null");
      return;
    }
    this.d.uploadDevices(paramString);
  }
  
  public void fireUploadSWList(String paramString)
  {
    bo.a(this.e, "fireUploadSWList");
    if (this.d == null)
    {
      bo.b(this.e, "view is null");
      return;
    }
    this.d.uploadSWList(paramString);
  }
  
  public String getADUnitID()
  {
    bo.a(this.e, "getADUnitID");
    if (this.d != null) {
      return this.d.getAdUnitId();
    }
    return "";
  }
  
  public String getAppState(String paramString)
  {
    bo.a(this.e, "getAppState() -- packagename is:" + paramString);
    if (!c("android.permission.GET_TASKS"))
    {
      Log.e(this.e, "don't have the android.Manifest.permission.GET_TASKS permission,so can't query the app state...");
      return null;
    }
    if ((paramString == null) || ("".equals(paramString.trim())))
    {
      Log.e(this.e, "the packagename is null,so can't query the app state...");
      return null;
    }
    if (this.b == null)
    {
      Log.e(this.e, "the context object is null,so can't query the app state...");
      return null;
    }
    String[] arrayOfString = paramString.split(",");
    if ((arrayOfString == null) || (arrayOfString.length <= 0)) {
      arrayOfString = new String[] { paramString };
    }
    for (;;)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("{");
      int i = 0;
      int j;
      if (i < arrayOfString.length)
      {
        try
        {
          ((Activity)this.b).getPackageManager().getApplicationInfo(arrayOfString[i], 8192);
          paramString = "installed";
        }
        catch (PackageManager.NameNotFoundException paramString)
        {
          for (;;)
          {
            paramString = "notInstalled";
            continue;
            paramString = "downloading";
            continue;
            paramString = "inqueue";
            continue;
            paramString = "downloaded";
          }
        }
        if (paramString != "notInstalled") {
          break label434;
        }
        j = DownloadService.queryIsDownloadBy(this.b, arrayOfString[i]);
        bo.a(this.e, "getAppState() -- installValue:" + j);
      }
      for (;;)
      {
        switch (j)
        {
        default: 
          bo.a(this.e, "getAppState() -- the packageName is:" + arrayOfString[i] + "; the state is:" + paramString);
          localStringBuilder.append("\"").append(arrayOfString[i]).append("\":").append("\"").append(paramString).append("\",");
          if (i == arrayOfString.length - 1) {
            localStringBuilder.deleteCharAt(localStringBuilder.lastIndexOf(","));
          }
          i += 1;
          break;
        case 1: 
        case 2: 
        case 3: 
          localStringBuilder.append("}");
          DownloadService.clearRubish();
          bo.a(this.e, "getAppState() -- the sb is:" + localStringBuilder.toString());
          return localStringBuilder.toString();
          label434:
          j = 0;
        }
      }
    }
  }
  
  public String getAppVersion()
  {
    return l.Q;
  }
  
  public String getBase64Payload(String paramString)
  {
    if ((paramString != null) && (!"".equals(paramString.trim()))) {
      return be.a(paramString.getBytes());
    }
    return null;
  }
  
  public String getBrand()
  {
    if (!c("android.permission.READ_PHONE_STATE"))
    {
      Log.e(bf.class.getSimpleName(), "don't have the android.Manifest.permission.READ_PHONE_STATE permission so can't read get the Build.BRAND info!");
      return null;
    }
    return Build.BRAND;
  }
  
  public String getBundleID()
  {
    return this.b.getApplicationInfo().packageName;
  }
  
  public String getChannelID()
  {
    return ImmobView.channelID;
  }
  
  public float getDensity()
  {
    return l.n;
  }
  
  public String getIMEI()
  {
    bo.a(this.e, "getIMEI");
    Object localObject;
    if (this.b == null) {
      localObject = getUUID();
    }
    String str2;
    do
    {
      do
      {
        do
        {
          return localObject;
          if (!c("android.permission.READ_PHONE_STATE"))
          {
            Log.e(bf.class.getSimpleName(), "don't have the android.Manifest.permission.READ_PHONE_STATE permission so can't read get the imei info!");
            return getUUID();
          }
          str2 = this.b.getSharedPreferences("SDKIMEI", 2).getString("imei", null);
          bo.b(this.e, "getIMEI111111=" + str2);
          if (str2 == null) {
            break;
          }
          localObject = str2;
        } while (!"".equals(str2.trim()));
        str2 = ((TelephonyManager)this.b.getSystemService("phone")).getDeviceId();
        localObject = str2;
      } while (str2 == null);
      localObject = str2;
    } while (str2.length() <= 0);
    for (;;)
    {
      try
      {
        str2 = str2.toLowerCase();
        if (str2 != null)
        {
          localObject = str2;
          if (!"".equals(str2.trim())) {}
        }
        else
        {
          localObject = getUUID();
          if ((localObject == null) || (((String)localObject).equals(""))) {
            break label201;
          }
        }
        b((String)localObject);
        return localObject;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        bo.a(localNumberFormatException);
        return "";
      }
      label201:
      String str1 = getUUID();
    }
  }
  
  public String getIMSI()
  {
    bo.a(this.e, "getIMSI");
    if (this.b == null) {
      return "null";
    }
    if (!c("android.permission.READ_PHONE_STATE"))
    {
      Log.e(bf.class.getSimpleName(), "don't have the android.Manifest.permission.READ_PHONE_STATE permission so can't read get the imsi info!");
      return "null";
    }
    String str2 = ((TelephonyManager)this.b.getSystemService("phone")).getSubscriberId();
    String str1 = str2;
    if (str2 == null) {
      str1 = "null";
    }
    bo.a(this.e, "getIMSI=" + str1);
    return str1;
  }
  
  public String getInterstitialAdsURL()
  {
    return d("ImmobHeader_");
  }
  
  public String getLanguage()
  {
    String str1 = Locale.getDefault().getLanguage();
    String str2 = Locale.getDefault().getCountry().toLowerCase();
    return str1 + "_" + str2;
  }
  
  public String getMAC()
  {
    bo.a(this.e, "getMAC");
    Object localObject2;
    if (this.b == null) {
      localObject2 = getUUID();
    }
    Object localObject1;
    do
    {
      do
      {
        return localObject2;
        if (!c("android.permission.READ_PHONE_STATE"))
        {
          Log.e(bf.class.getSimpleName(), "don't have the android.Manifest.permission.READ_PHONE_STATE permission so can't read get the mac info!");
          return getUUID();
        }
        localObject1 = this.b.getSharedPreferences("SDKMAC", 2).getString("mac", null);
        bo.b(this.e, "getMAC1111=" + (String)localObject1);
        if ((localObject1 != null) && (!"".equals(((String)localObject1).trim()))) {
          return localObject1;
        }
        try
        {
          localObject2 = ((WifiManager)this.b.getSystemService("wifi")).getConnectionInfo().getMacAddress();
          localObject1 = localObject2;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        }
        localObject2 = localObject1;
      } while (localObject1 == null);
      localObject2 = localObject1;
    } while (((String)localObject1).length() <= 0);
    a((String)localObject1);
    return localObject1;
    return localObject1;
  }
  
  public String getModel()
  {
    if (!c("android.permission.READ_PHONE_STATE"))
    {
      Log.e(bf.class.getSimpleName(), "don't have the android.Manifest.permission.READ_PHONE_STATE permission so can't read get the Build.MODEL info!");
      return null;
    }
    return Build.MODEL;
  }
  
  public String getOSVersion()
  {
    if (!c("android.permission.READ_PHONE_STATE"))
    {
      Log.e(bf.class.getSimpleName(), "don't have the android.Manifest.permission.READ_PHONE_STATE permission so can't read get the Build.VERSION.RELEASE info!");
      return null;
    }
    return Build.VERSION.RELEASE;
  }
  
  public String getOpenaUDID(Context paramContext)
  {
    bq.a(paramContext);
    boolean bool = bq.b();
    paramContext = "";
    if (bool) {
      paramContext = bq.a();
    }
    return paramContext;
  }
  
  public int getPlacementType()
  {
    int i = -1;
    if (this.d != null) {
      i = this.d.getAdtype();
    }
    return i;
  }
  
  public String getRSAEncryptedPaylod(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString.trim()))) {
      return null;
    }
    bo.a(this.e, "getRSAEncryptedPaylod");
    Object localObject = new RSAPublicKeySpec(new BigInteger(""), new BigInteger(""));
    try
    {
      KeyPairGenerator.getInstance("RSA").initialize(2048);
      localObject = KeyFactory.getInstance("RSA").generatePublic((KeySpec)localObject);
      try
      {
        a = Cipher.getInstance("RSA");
        a.init(1, (Key)localObject);
        paramString = be.a(a.doFinal(paramString.getBytes()));
        return paramString;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        bo.c("LMSDKController", "LMSDKController=" + paramString);
        return null;
      }
      return null;
    }
    catch (InvalidKeySpecException paramString)
    {
      paramString.printStackTrace();
      bo.c("LMSDKController", "LMSDKController=" + paramString);
    }
  }
  
  public String getSDKVersion()
  {
    bo.a(this.e, "getSDKVersion");
    return l.r;
  }
  
  public float getScreenDensity()
  {
    bo.a(this.e, "getScreenDensity");
    if (this.b == null) {
      throw new RuntimeException("the arguments is null");
    }
    return this.b.getApplicationContext().getResources().getDisplayMetrics().density;
  }
  
  public int getScreenHeight()
  {
    bo.a(this.e, "getScreenHeight");
    if (this.b == null) {
      throw new RuntimeException("the arguments is null");
    }
    DisplayMetrics localDisplayMetrics = this.b.getApplicationContext().getResources().getDisplayMetrics();
    int i = localDisplayMetrics.heightPixels;
    float f1 = localDisplayMetrics.density;
    if (this.b.getApplicationContext().getResources().getConfiguration().orientation == 2) {
      i = localDisplayMetrics.widthPixels;
    }
    return i;
  }
  
  public int getScreenWidth()
  {
    bo.a(this.e, "getScreenWidth");
    if (this.b == null) {
      throw new RuntimeException("the arguments is null");
    }
    DisplayMetrics localDisplayMetrics = this.b.getApplicationContext().getResources().getDisplayMetrics();
    int i = localDisplayMetrics.widthPixels;
    if (this.b.getApplicationContext().getResources().getConfiguration().orientation == 2) {
      i = localDisplayMetrics.heightPixels;
    }
    return i;
  }
  
  public String getSeckey(Context paramContext, String paramString1, String paramString2)
  {
    int k = 0;
    if (paramString1 != null)
    {
      str = paramString1;
      if (!"".equals(paramString1.trim())) {}
    }
    else
    {
      str = "000000000000";
    }
    if (paramString2 != null)
    {
      paramString1 = paramString2;
      if (!paramString2.trim().equals("")) {}
    }
    else
    {
      paramString1 = "000000000000000";
    }
    if (paramContext == null) {
      return "";
    }
    paramString2 = str.replace(":", "");
    paramContext = paramString2 + paramString1;
    String str = loadData("sk");
    bo.c(this.e, "mac:" + paramString2 + "; imei:" + paramString1 + "; sk:" + str);
    if ((str != null) && (!"".equals(str.trim())))
    {
      paramString1 = ay.a(str);
      bo.c(this.e, "sk:" + paramString1);
      paramString2 = bh.d(paramString1);
      paramString1 = paramString2.split(",");
      bo.c(this.e, "sk:" + paramString2 + ";  data.length:" + paramString1.length);
      if ((paramString1 == null) || (paramString1.length < 3)) {}
    }
    int j;
    try
    {
      this.f = Integer.valueOf(paramString1[0]).intValue();
    }
    catch (NumberFormatException paramString2)
    {
      try
      {
        for (;;)
        {
          this.g = Integer.valueOf(paramString1[1]).intValue();
          this.h = paramString1[2];
          paramString1 = new ArrayList();
          if (this.f > 0) {
            break;
          }
          return "";
          paramString2 = paramString2;
          bo.a(paramString2);
        }
      }
      catch (NumberFormatException paramString2)
      {
        for (;;)
        {
          bo.a(paramString2);
        }
        j = paramContext.length() / this.f;
        if (paramContext.length() % this.f == 0) {
          break label718;
        }
      }
    }
    int i = 0;
    while (i < paramContext.length() % this.f)
    {
      paramContext = paramContext + "0";
      i += 1;
    }
    label718:
    for (i = j + 1;; i = j)
    {
      j = 0;
      while (j < i)
      {
        paramString1.add(paramContext.substring(this.f * j, this.f + this.f * j));
        j += 1;
      }
      paramContext = Integer.toBinaryString(this.g);
      paramString2 = new StringBuffer();
      i = 0;
      while (i < paramContext.length())
      {
        if (paramContext.substring(i, i + 1).equals("1")) {
          paramString2.append(i).append(",");
        }
        i += 1;
      }
      if (paramString2.toString().lastIndexOf(",") == paramString2.length() - 1) {
        paramString2.deleteCharAt(paramString2.length() - 1);
      }
      paramContext = paramString2.toString().split(",");
      paramString2 = new StringBuffer();
      i = 0;
      while (i < paramString1.size())
      {
        j = 0;
        while (j < paramContext.length)
        {
          int m = Integer.valueOf(paramContext[j]).intValue();
          paramString2.append(((String)paramString1.get(i)).substring(m, m + 1));
          j += 1;
        }
        i += 1;
      }
      paramContext = paramString2.toString().getBytes();
      i = k;
      while (i < paramContext.length)
      {
        paramContext[i] = ((byte)(paramContext[i] ^ 0xFFFFFFFF));
        i += 1;
      }
      paramContext = bh.a(paramContext) + this.h;
      bo.a(this.e, paramContext);
      return paramContext;
    }
  }
  
  public int getStreenWidth()
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)this.b.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.widthPixels;
      return i;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public String getTimeZone()
  {
    bo.a(this.e, "getTimeZone");
    int j = TimeZone.getDefault().getRawOffset();
    bo.b("LMSDKController_offset", "LMSDKController_offset=" + j);
    if (j < 0) {}
    for (int i = Math.abs(j);; i = j)
    {
      i = i / 1000 / 60 / 60;
      if (j >= 0) {
        break;
      }
      return "-" + i;
    }
    if (i == 0) {
      return "" + i;
    }
    return "+" + i;
  }
  
  public String getUA()
  {
    bo.a(this.e, "getUA");
    return l.L;
  }
  
  public String getUDID()
  {
    bo.a(this.e, "getUDID");
    return getMAC() + "#" + getIMEI();
  }
  
  public String getUserProperties()
  {
    if (this.d != null) {
      return this.d.getUserProperties();
    }
    return null;
  }
  
  public float getWindowManagerDensity()
  {
    if (this.b != null)
    {
      new DisplayMetrics();
      return this.b.getApplicationContext().getResources().getDisplayMetrics().density;
    }
    return 0.0F;
  }
  
  public void halfBannerReady(boolean paramBoolean)
  {
    if (this.d != null) {
      this.d.jar(paramBoolean);
    }
  }
  
  public boolean isAppOnTop()
  {
    if (this.d == null) {
      return false;
    }
    return this.d.isTopActivy();
  }
  
  public String loadData(String paramString)
  {
    paramString = this.b.getSharedPreferences("LMmobData", 0).getString(paramString, "");
    bo.a(this.e, "getdata:" + paramString);
    return paramString;
  }
  
  public void log(String paramString1, String paramString2) {}
  
  public boolean nativeExecute(String paramString)
  {
    bo.a(this.e, "nativeExecute");
    if ((paramString == null) || ("".equals(paramString.trim()))) {
      return false;
    }
    if (this.b == null) {
      return false;
    }
    Activity localActivity = (Activity)this.b;
    try
    {
      localActivity.getPackageManager().getApplicationInfo(paramString, 8192);
      new Intent();
      localActivity.startActivity(localActivity.getPackageManager().getLaunchIntentForPackage(paramString));
      if ((this.c != null) && (this.d != null)) {
        this.c.onLeaveApplication(this.d);
      }
      bo.c("LMSDKController_listener", "LMSDKController_listener=" + this.c + " " + this.d);
      return true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      bo.a(localNameNotFoundException);
      Log.e(this.e, "the application not found,packagename is:" + paramString);
      return false;
    }
    catch (NullPointerException paramString)
    {
      Log.e(this.e, "NullPointerException....");
      return false;
    }
    catch (Exception paramString)
    {
      bo.a(paramString);
      Log.e(this.e, "Unkonw exception...");
    }
    return false;
  }
  
  public void saveData(String paramString1, String paramString2)
  {
    bo.a(this.e, "key:" + paramString1 + ";  data:" + paramString2);
    SharedPreferences localSharedPreferences = this.b.getSharedPreferences("LMmobData", 0);
    if (paramString2 == null)
    {
      localSharedPreferences.edit().remove(paramString1).commit();
      return;
    }
    localSharedPreferences.edit().putString(paramString1, paramString2).commit();
  }
  
  public void setLMAdListener(LMAdListener paramLMAdListener)
  {
    this.c = paramLMAdListener;
  }
  
  public void setLmmobView(ImmobView paramImmobView)
  {
    this.d = paramImmobView;
  }
  
  public void setViewScrollable(boolean paramBoolean)
  {
    bo.c(this.e, "setViewScrollable() -- scrollable:" + paramBoolean + "; the view is:" + this.d);
    if (this.d != null) {
      this.d.setScrollAble(paramBoolean);
    }
  }
}
