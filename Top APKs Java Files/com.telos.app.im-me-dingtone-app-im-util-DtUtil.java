package me.dingtone.app.im.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.provider.CallLog.Calls;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import me.dingtone.app.im.activity.DTActivity;
import me.dingtone.app.im.activity.MessageChatActivity;
import me.dingtone.app.im.adapter.cv;
import me.dingtone.app.im.adapter.hm;
import me.dingtone.app.im.alarm.f;
import me.dingtone.app.im.database.bd;
import me.dingtone.app.im.datatype.PrivatePhoneItemOfMine;
import me.dingtone.app.im.datatype.message.DTMessage;
import me.dingtone.app.im.dialog.ao;
import me.dingtone.app.im.group.HybridGroup;
import me.dingtone.app.im.group.HybridGroupMember;
import me.dingtone.app.im.group.p;
import me.dingtone.app.im.h.a.g;
import me.dingtone.app.im.h.a.l;
import me.dingtone.app.im.log.DTLog;
import me.dingtone.app.im.manager.AppConnectionManager;
import me.dingtone.app.im.manager.DTApplication;
import me.dingtone.app.im.manager.NetworkMonitor;
import me.dingtone.app.im.manager.el;
import me.dingtone.app.im.manager.w;
import me.dingtone.app.im.privatephone.e;
import me.dingtone.app.im.privatephone.q;
import org.apache.commons.lang.d;
import org.json.JSONException;
import org.json.JSONObject;

public class DtUtil
{
  public static final int PING_TIMEOUT = 100000;
  public static final long UnbindSuspendPrivateNumberTime = 86400000L;
  private static final String tag = "DtUtil";
  
  public DtUtil() {}
  
  public static void Download(Context paramContext, String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        DownloadManager localDownloadManager = (DownloadManager)paramContext.getSystemService("download");
        paramString1 = new DownloadManager.Request(Uri.parse(paramString1));
        paramString1.setAllowedNetworkTypes(3);
        DTLog.i("DtUtil", "dwonload localPath = " + paramString2);
        paramString1.setDestinationUri(Uri.fromFile(new File(paramString2)));
        switch (getDownloadStatus(paramContext, el.a().C()))
        {
        case 3: 
        case 5: 
        case 6: 
        case 7: 
          deleteFile(paramString2);
          if (el.a().C() != -1L) {
            localDownloadManager.remove(new long[] { el.a().C() });
          }
          long l = localDownloadManager.enqueue(paramString1);
          el.a().g(l);
          el.a().i(paramString2);
          Log.v("DtUtil", "STATUS_FAILED");
          return;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        DTLog.i("DtUtil", paramContext.getMessage());
        return;
      }
      Log.v("DtUtil", "STATUS_PAUSED");
      Log.v("DtUtil", "STATUS_PENDING");
      Log.v("DtUtil", "STATUS_RUNNING");
      return;
      installApp(paramContext, paramString2);
      Log.v("DtUtil", "下载完成");
      return;
    }
  }
  
  public static DTMessage JsonRepToDTMessage(String paramString)
  {
    try
    {
      int i = new JSONObject(paramString).getInt("k1");
      paramString = nativeJson2TDmsg(i, paramString);
      if (paramString != null) {
        paramString.setMsgFlag(0);
      }
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static String SHA1Hash(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("SHA-1");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer(paramString.length * 2);
      int i = 0;
      while (i < paramString.length)
      {
        if ((paramString[i] & 0xFF) < 16) {
          ((StringBuffer)localObject).append("0");
        }
        ((StringBuffer)localObject).append(Integer.toString(paramString[i] & 0xFF, 16));
        i += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      DTLog.e("DtUtil", paramString.getStackTrace().toString());
    }
    return "";
  }
  
  public static boolean areInSamePeriod(int paramInt, long paramLong)
  {
    paramLong = System.currentTimeMillis() - paramLong;
    DTLog.d("DtUtil", "time is " + paramLong + " hour is " + paramInt * 3600000L);
    return paramLong < paramInt * 3600000L;
  }
  
  public static boolean areSameDay(long paramLong1, long paramLong2)
  {
    Object localObject = new Date(paramLong1);
    Date localDate = new Date(paramLong2);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime((Date)localObject);
    localObject = Calendar.getInstance();
    ((Calendar)localObject).setTime(localDate);
    return (localCalendar.get(1) == ((Calendar)localObject).get(1)) && (localCalendar.get(2) == ((Calendar)localObject).get(2)) && (localCalendar.get(5) == ((Calendar)localObject).get(5));
  }
  
  public static boolean areSameDay(Date paramDate1, Date paramDate2)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate1);
    paramDate1 = Calendar.getInstance();
    paramDate1.setTime(paramDate2);
    return (localCalendar.get(1) == paramDate1.get(1)) && (localCalendar.get(2) == paramDate1.get(2)) && (localCalendar.get(5) == paramDate1.get(5));
  }
  
  public static String byteArrayToHex(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      if ((paramArrayOfByte[i] & 0xFF) < 16) {
        localStringBuffer.append("0");
      }
      localStringBuffer.append(Integer.toString(paramArrayOfByte[i] & 0xFF, 16));
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static void checkAndGetUserAgent()
  {
    if (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()) {}
    for (boolean bool = true;; bool = false)
    {
      i.a("getUserAgent shoudl run in main thread", bool);
      DTLog.d("DtUtil", "checkAndGetUserAgent begin");
      if ((el.a().cX() == null) || ("".equals(el.a().cX())))
      {
        String str = getUserAgent(DTApplication.f());
        el.a().al(str);
      }
      DTLog.d("DtUtil", "checkAndGetUserAgent end sdk int " + Build.VERSION.SDK_INT);
      return;
    }
  }
  
  public static boolean checkIsVPNOpen()
  {
    if (VPNChecker.a().d())
    {
      boolean bool = checkVPNConnectionByNetworkInterface();
      DTLog.i("DtUtil", "checkIsVPNOpen use network interface method check vpn connection. isOpen: " + bool);
      return bool;
    }
    Object localObject = LocationHelper.a().b();
    String str;
    if ((localObject != null) && (!((ArrayList)localObject).isEmpty()))
    {
      localObject = (String)((ArrayList)localObject).get(0);
      str = el.a().cH();
      DTLog.d("DtUtil", "checkIsVPNOpen geos is not empty, isoCC: " + (String)localObject + ", isoCCServer: " + str);
      if ((localObject != null) && (!((String)localObject).equals(str))) {
        return true;
      }
    }
    localObject = DTSystemContext.getTimeZone();
    if (localObject != null)
    {
      localObject = DTSystemContext.getCountryCodeByTimezoneID((String)localObject);
      if (localObject != null)
      {
        str = el.a().cH();
        DTLog.d("DtUtil", "checkIsVPNOpen time zone iso cc: " + (String)localObject + ", isoCCServer: " + str);
        if (!((String)localObject).equals(str)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean checkNeedToTransferData(Context paramContext)
  {
    if ((!el.a().x()) && (!paramContext.getPackageName().equals("me.dingtone.app.im")) && (!paramContext.getPackageName().equals("me.talkyou.app.im")) && (isPackageInstalled("me.dingtone.app.im", paramContext)) && (!el.a().bi().booleanValue()) && (getPackageInfo("me.dingtone.app.im", paramContext).versionCode >= 134)) {}
    return false;
  }
  
  public static boolean checkVPNConnectionByNetworkInterface()
  {
    try
    {
      Object localObject = NetworkInterface.getNetworkInterfaces();
      if (localObject != null)
      {
        localObject = Collections.list((Enumeration)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          NetworkInterface localNetworkInterface = (NetworkInterface)((Iterator)localObject).next();
          if ((localNetworkInterface.isUp()) && (localNetworkInterface.getInterfaceAddresses().size() != 0))
          {
            DTLog.d("DtUtil", "checkIsVPNOpen NetworkInterface name: " + localNetworkInterface.getName());
            if (!"tun0".equals(localNetworkInterface.getName()))
            {
              boolean bool = "ppp0".equals(localNetworkInterface.getName());
              if (!bool) {
                break;
              }
            }
            else
            {
              return true;
            }
          }
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return false;
  }
  
  public static int compareVersion(String paramString1, String paramString2)
  {
    int n = 1;
    DTLog.d("DtUtil", "compare Version ver1=" + paramString1 + " ver2=" + paramString2);
    if (paramString1.isEmpty()) {
      return -1;
    }
    if (paramString2.isEmpty()) {
      return 1;
    }
    for (;;)
    {
      int k;
      int m;
      try
      {
        String[] arrayOfString1 = paramString1.split("\\.");
        String[] arrayOfString2 = paramString2.split("\\.");
        int i1 = Math.max(arrayOfString1.length, arrayOfString2.length);
        j = 0;
        if (j >= i1) {
          break label242;
        }
        if (arrayOfString1.length <= j) {
          break label236;
        }
        k = Integer.valueOf(arrayOfString1[j]).intValue();
        if (arrayOfString2.length <= j) {
          break label230;
        }
        m = Integer.valueOf(arrayOfString2[j]).intValue();
      }
      catch (Exception paramString1)
      {
        int j;
        i.a("compare version exception " + org.apache.commons.lang.exception.a.h(paramString1), false);
        return -1;
      }
      DTLog.d("DtUtil", "compare Version ver1=" + paramString1 + " ver2=" + paramString2 + " result=" + i);
      return i;
      int i = n;
      if (k <= m)
      {
        j += 1;
        continue;
        label230:
        m = 0;
        break label247;
        label236:
        k = 0;
        continue;
        label242:
        i = 0;
        continue;
        label247:
        if (k < m) {
          i = -1;
        }
      }
    }
  }
  
  public static String convertDecimalToAbc(long paramLong)
  {
    char[] arrayOfChar = new char[62];
    char[] tmp6_5 = arrayOfChar;
    tmp6_5[0] = 48;
    char[] tmp12_6 = tmp6_5;
    tmp12_6[1] = 49;
    char[] tmp18_12 = tmp12_6;
    tmp18_12[2] = 50;
    char[] tmp24_18 = tmp18_12;
    tmp24_18[3] = 51;
    char[] tmp30_24 = tmp24_18;
    tmp30_24[4] = 52;
    char[] tmp36_30 = tmp30_24;
    tmp36_30[5] = 53;
    char[] tmp42_36 = tmp36_30;
    tmp42_36[6] = 54;
    char[] tmp49_42 = tmp42_36;
    tmp49_42[7] = 55;
    char[] tmp56_49 = tmp49_42;
    tmp56_49[8] = 56;
    char[] tmp63_56 = tmp56_49;
    tmp63_56[9] = 57;
    char[] tmp70_63 = tmp63_56;
    tmp70_63[10] = 65;
    char[] tmp77_70 = tmp70_63;
    tmp77_70[11] = 66;
    char[] tmp84_77 = tmp77_70;
    tmp84_77[12] = 67;
    char[] tmp91_84 = tmp84_77;
    tmp91_84[13] = 68;
    char[] tmp98_91 = tmp91_84;
    tmp98_91[14] = 69;
    char[] tmp105_98 = tmp98_91;
    tmp105_98[15] = 70;
    char[] tmp112_105 = tmp105_98;
    tmp112_105[16] = 71;
    char[] tmp119_112 = tmp112_105;
    tmp119_112[17] = 72;
    char[] tmp126_119 = tmp119_112;
    tmp126_119[18] = 73;
    char[] tmp133_126 = tmp126_119;
    tmp133_126[19] = 74;
    char[] tmp140_133 = tmp133_126;
    tmp140_133[20] = 75;
    char[] tmp147_140 = tmp140_133;
    tmp147_140[21] = 76;
    char[] tmp154_147 = tmp147_140;
    tmp154_147[22] = 77;
    char[] tmp161_154 = tmp154_147;
    tmp161_154[23] = 78;
    char[] tmp168_161 = tmp161_154;
    tmp168_161[24] = 79;
    char[] tmp175_168 = tmp168_161;
    tmp175_168[25] = 80;
    char[] tmp182_175 = tmp175_168;
    tmp182_175[26] = 81;
    char[] tmp189_182 = tmp182_175;
    tmp189_182[27] = 82;
    char[] tmp196_189 = tmp189_182;
    tmp196_189[28] = 83;
    char[] tmp203_196 = tmp196_189;
    tmp203_196[29] = 84;
    char[] tmp210_203 = tmp203_196;
    tmp210_203[30] = 85;
    char[] tmp217_210 = tmp210_203;
    tmp217_210[31] = 86;
    char[] tmp224_217 = tmp217_210;
    tmp224_217[32] = 87;
    char[] tmp231_224 = tmp224_217;
    tmp231_224[33] = 88;
    char[] tmp238_231 = tmp231_224;
    tmp238_231[34] = 89;
    char[] tmp245_238 = tmp238_231;
    tmp245_238[35] = 90;
    char[] tmp252_245 = tmp245_238;
    tmp252_245[36] = 97;
    char[] tmp259_252 = tmp252_245;
    tmp259_252[37] = 98;
    char[] tmp266_259 = tmp259_252;
    tmp266_259[38] = 99;
    char[] tmp273_266 = tmp266_259;
    tmp273_266[39] = 100;
    char[] tmp280_273 = tmp273_266;
    tmp280_273[40] = 101;
    char[] tmp287_280 = tmp280_273;
    tmp287_280[41] = 102;
    char[] tmp294_287 = tmp287_280;
    tmp294_287[42] = 103;
    char[] tmp301_294 = tmp294_287;
    tmp301_294[43] = 104;
    char[] tmp308_301 = tmp301_294;
    tmp308_301[44] = 105;
    char[] tmp315_308 = tmp308_301;
    tmp315_308[45] = 106;
    char[] tmp322_315 = tmp315_308;
    tmp322_315[46] = 107;
    char[] tmp329_322 = tmp322_315;
    tmp329_322[47] = 108;
    char[] tmp336_329 = tmp329_322;
    tmp336_329[48] = 109;
    char[] tmp343_336 = tmp336_329;
    tmp343_336[49] = 110;
    char[] tmp350_343 = tmp343_336;
    tmp350_343[50] = 111;
    char[] tmp357_350 = tmp350_343;
    tmp357_350[51] = 112;
    char[] tmp364_357 = tmp357_350;
    tmp364_357[52] = 113;
    char[] tmp371_364 = tmp364_357;
    tmp371_364[53] = 114;
    char[] tmp378_371 = tmp371_364;
    tmp378_371[54] = 115;
    char[] tmp385_378 = tmp378_371;
    tmp385_378[55] = 116;
    char[] tmp392_385 = tmp385_378;
    tmp392_385[56] = 117;
    char[] tmp399_392 = tmp392_385;
    tmp399_392[57] = 118;
    char[] tmp406_399 = tmp399_392;
    tmp406_399[58] = 119;
    char[] tmp413_406 = tmp406_399;
    tmp413_406[59] = 120;
    char[] tmp420_413 = tmp413_406;
    tmp420_413[60] = 121;
    char[] tmp427_420 = tmp420_413;
    tmp427_420[61] = 122;
    tmp427_420;
    StringBuffer localStringBuffer = new StringBuffer();
    while (paramLong != 0L)
    {
      localStringBuffer.append(arrayOfChar[((int)(paramLong % arrayOfChar.length))]);
      paramLong /= arrayOfChar.length;
    }
    return localStringBuffer.toString();
  }
  
  public static float currencyToCredits(float paramFloat)
  {
    float f2 = el.a().cj();
    float f1 = f2;
    if (f2 < 1.0E-6D) {
      f1 = 0.02F;
    }
    return paramFloat / f1;
  }
  
  public static int daysBetween(long paramLong1, long paramLong2)
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    return (int)Math.abs((localTimeZone.getOffset(paramLong1) + paramLong1) / 86400000L - (localTimeZone.getOffset(paramLong2) + paramLong2) / 86400000L);
  }
  
  public static byte[] decryptString(String paramString)
  {
    try
    {
      paramString = Base64.decode(paramString, 0);
      Object localObject = getAESKey();
      IvParameterSpec localIvParameterSpec = new IvParameterSpec(getAESIV().getBytes());
      localObject = new SecretKeySpec(((String)localObject).getBytes(), "AES");
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
      localCipher.init(2, (Key)localObject, localIvParameterSpec);
      paramString = localCipher.doFinal(paramString);
      DTLog.d("DtUtil", "decryptData length = " + paramString.length);
      return paramString;
    }
    catch (Throwable paramString)
    {
      DTLog.e("DtUtil", "decryptString exception e = " + org.apache.commons.lang.exception.a.h(paramString));
    }
    return null;
  }
  
  public static String decryptText(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return "";
    }
    paramString = decryptString(paramString);
    if (paramString == null) {
      return "";
    }
    return new String(paramString);
  }
  
  public static void deleteFile(String paramString)
  {
    paramString = new File(paramString);
    if (paramString.exists()) {
      paramString.delete();
    }
  }
  
  public static String encryptAESData(byte[] paramArrayOfByte)
  {
    Object localObject2 = getAESKey();
    Object localObject1 = new IvParameterSpec(getAESIV().getBytes());
    localObject2 = new SecretKeySpec(((String)localObject2).getBytes(), "AES");
    try
    {
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
      localCipher.init(1, (Key)localObject2, (AlgorithmParameterSpec)localObject1);
      paramArrayOfByte = localCipher.doFinal(paramArrayOfByte);
      localObject1 = new String(Base64.encode(paramArrayOfByte, 0), "UTF-8");
      DTLog.d("DtUtil", "encryptAESData data length = " + paramArrayOfByte.length);
      return localObject1;
    }
    catch (Throwable paramArrayOfByte)
    {
      paramArrayOfByte = org.apache.commons.lang.exception.a.h(paramArrayOfByte);
      DTLog.e("DtUtil", "encryptAESData e = " + paramArrayOfByte);
    }
    return "";
  }
  
  public static String encryptText(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return "";
    }
    return encryptAESData(paramString.getBytes());
  }
  
  public static void exit()
  {
    try
    {
      DTActivity.t();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    finally
    {
      android.os.Process.killProcess(android.os.Process.myPid());
      System.exit(0);
    }
  }
  
  public static String get32MD5Str(String paramString)
  {
    Object localObject = MessageDigest.getInstance("MD5");
    ((MessageDigest)localObject).update(paramString.getBytes());
    paramString = ((MessageDigest)localObject).digest();
    localObject = new StringBuffer();
    int i = 0;
    if (i < paramString.length)
    {
      String str = Integer.toHexString(paramString[i] & 0xFF);
      if (str.length() == 1) {
        ((StringBuffer)localObject).append("0").append(str);
      }
      for (;;)
      {
        i += 1;
        break;
        ((StringBuffer)localObject).append(str);
      }
    }
    return ((StringBuffer)localObject).toString().substring(0, 16);
  }
  
  public static int getADCountryCode()
  {
    try
    {
      if (el.a().cG() != 0) {
        return (short)el.a().cG();
      }
      Object localObject = LocationHelper.a().b();
      if (((ArrayList)localObject).size() == 1)
      {
        localObject = ks.c((String)((ArrayList)localObject).get(0));
        if (localObject != null) {
          return Integer.valueOf((String)localObject).intValue();
        }
      }
      if ((el.a().aV() > 0) && (!"".equals(el.a().aY()))) {
        return el.a().aV();
      }
      String str2 = ((TelephonyManager)DTApplication.f().getSystemService("phone")).getSimCountryIso();
      String str1 = "";
      localObject = str1;
      if (str2 != null)
      {
        localObject = str1;
        if (!str2.isEmpty()) {
          localObject = ks.c(str2.toUpperCase());
        }
      }
      if ((localObject != null) && (!((String)localObject).isEmpty()))
      {
        DTLog.d("DtUtil", "getCountryCode from sim = " + (String)localObject);
        return Short.valueOf((String)localObject).shortValue();
      }
      localObject = el.a().by();
      if (!"".equals(localObject))
      {
        localObject = ks.c(((String)localObject).toUpperCase());
        if (localObject != null) {
          return Short.parseShort((String)localObject);
        }
      }
      localObject = DTApplication.f().getResources().getConfiguration().locale.getCountry();
      if ((localObject != null) && (!((String)localObject).isEmpty()))
      {
        localObject = ks.c(((String)localObject).toUpperCase());
        if (localObject != null)
        {
          int i = Short.parseShort((String)localObject);
          return i;
        }
        return 1;
      }
    }
    catch (Exception localException)
    {
      return 1;
    }
    return 1;
  }
  
  public static String getAESIV()
  {
    String str = el.a().aN();
    str = str + "dingtone!123";
    try
    {
      str = get32MD5Str(str);
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return null;
  }
  
  public static String getAESKey()
  {
    String str = el.a().aN();
    str = "dingtone@123" + str;
    try
    {
      str = get32MD5Str(str);
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return null;
  }
  
  public static int getAppVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0;
  }
  
  public static String getAppVersionCodeWithBuildNumber()
  {
    return getAppVersionName() + "." + "7193";
  }
  
  public static String getAppVersionCodeWithBuildNumber(Context paramContext)
  {
    return getAppVersionName(paramContext) + "." + "7193";
  }
  
  public static String getAppVersionName()
  {
    return getAppVersionName(DTApplication.f());
  }
  
  /* Error */
  public static String getAppVersionName(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 753	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_0
    //   5: invokevirtual 368	android/content/Context:getPackageName	()Ljava/lang/String;
    //   8: iconst_0
    //   9: invokevirtual 758	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   12: astore_0
    //   13: aload_0
    //   14: getfield 394	android/content/pm/PackageInfo:versionCode	I
    //   17: istore_1
    //   18: aload_0
    //   19: getfield 771	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   22: astore_0
    //   23: ldc 15
    //   25: ldc_w 773
    //   28: iconst_2
    //   29: anewarray 4	java/lang/Object
    //   32: dup
    //   33: iconst_0
    //   34: iload_1
    //   35: invokestatic 776	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   38: aastore
    //   39: dup
    //   40: iconst_1
    //   41: aload_0
    //   42: aastore
    //   43: invokestatic 780	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   46: invokestatic 781	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   49: pop
    //   50: aload_0
    //   51: areturn
    //   52: astore_2
    //   53: ldc -46
    //   55: astore_0
    //   56: aload_2
    //   57: invokevirtual 130	java/lang/Exception:printStackTrace	()V
    //   60: aload_0
    //   61: areturn
    //   62: astore_2
    //   63: goto -7 -> 56
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	paramContext	Context
    //   17	18	1	i	int
    //   52	5	2	localException1	Exception
    //   62	1	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	23	52	java/lang/Exception
    //   23	50	62	java/lang/Exception
  }
  
  public static String getAuthorityFromPermission(Context paramContext)
  {
    String str2 = getAuthorityFromPermissionDefault(paramContext);
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.trim().equals("")) {}
    }
    else
    {
      str1 = getCurrentLauncherPackageName(paramContext);
      str1 = getThirdAuthorityFromPermission(paramContext, str1 + ".permission.READ_SETTINGS");
    }
    paramContext = str1;
    int i;
    if (TextUtils.isEmpty(str1))
    {
      i = Build.VERSION.SDK_INT;
      if (i >= 8) {
        break label103;
      }
      paramContext = "com.android.launcher.settings";
    }
    for (;;)
    {
      return "content://" + paramContext + "/favorites?notify=true";
      label103:
      if (i < 19) {
        paramContext = "com.android.launcher2.settings";
      } else {
        paramContext = "com.android.launcher3.settings";
      }
    }
  }
  
  public static String getAuthorityFromPermissionDefault(Context paramContext)
  {
    return getThirdAuthorityFromPermission(paramContext, "com.android.launcher.permission.READ_SETTINGS");
  }
  
  public static String getCNConferenceNotifyContentWithInviteKey(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4)
  {
    String str2 = el.a().aA();
    String str3 = el.a().ay();
    String str1 = str3 + str2;
    if ((str3 == null) || ("".equals(str3))) {
      str1 = el.a().ax() + str2;
    }
    return DTApplication.f().getApplicationContext().getString(a.l.conference_call_share_invite_attendees, new Object[] { paramString1, paramString2, ln.a(new Date(1000L * paramLong), false), paramString3, paramString4, str1 });
  }
  
  public static String getCNLinkUrlWithInviteKey()
  {
    String str2 = el.a().aA();
    String str3 = el.a().ay();
    DTLog.i("DtUtil", "getCNLinkUrlWithInviteKey inviteKey = " + str2 + " inviteUrl " + str3);
    String str1 = str3 + str2;
    if ((str3 == null) || ("".equals(str3))) {
      str1 = el.a().ax() + str2;
    }
    DTLog.d("DtUtil", "getCNLinkUrlWithInviteKey inviteLink=" + str1);
    return str1;
  }
  
  public static String getCNLinkUrlWithoutInviteKey()
  {
    Object localObject2 = el.a().ay();
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"".equals(localObject2)) {}
    }
    else
    {
      localObject1 = el.a().ax();
    }
    localObject2 = localObject1;
    try
    {
      localObject1 = ((String)localObject1).substring(0, ((String)localObject1).indexOf("?"));
      localObject2 = localObject1;
      DTLog.d("DtUtil", "getCNLinkUrlWithoutInviteKey invite url =" + (String)localObject1);
      return localObject1;
    }
    catch (Exception localException) {}
    return localObject2;
  }
  
  public static String getCNQQShareContentWithInviteKey()
  {
    String str = getCNLinkUrlWithInviteKey();
    DTLog.d("DtUtil", "getShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone_cnqq, new Object[] { str });
  }
  
  public static String getCNQQShareContentWithoutInviteKey()
  {
    String str = getCNLinkUrlWithoutInviteKey();
    DTLog.d("DtUtil", "getShareContentWithoutInviteKey invite url =" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone_cnqq, new Object[] { str });
  }
  
  public static String getCNShareContentWithInviteKey()
  {
    String str = getCNLinkUrlWithInviteKey();
    DTLog.d("DtUtil", "getCNShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone, new Object[] { str });
  }
  
  public static String getCNShareContentWithoutInviteKey()
  {
    String str = getCNLinkUrlWithoutInviteKey();
    DTLog.d("DtUtil", "getCNShareContentWithoutInviteKey invite url =" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone, new Object[] { str });
  }
  
  public static String getCNWeChatShareContentWithInviteKey()
  {
    String str = getCNLinkUrlWithInviteKey();
    DTLog.d("DtUtil", "getShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone_cnwechat, new Object[] { str });
  }
  
  public static String getCNWeChatShareContentWithoutInviteKey()
  {
    String str = getLinkUrlWithoutInviteKey();
    DTLog.d("DtUtil", "getShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone_cnwechat, new Object[] { str });
  }
  
  public static int getCallLogCount(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
      int i;
      if (paramContext != null) {
        i = paramContext.getCount();
      }
      return 0;
    }
    catch (Exception paramContext)
    {
      try
      {
        paramContext.close();
        return i;
      }
      catch (Exception paramContext)
      {
        for (;;) {}
      }
      paramContext = paramContext;
      i = 0;
      paramContext.printStackTrace();
      return i;
    }
  }
  
  public static String[] getCatalogForSideBar(hm paramHm)
  {
    int i = 0;
    HashMap localHashMap = new HashMap();
    int j = paramHm.getCount();
    if (((paramHm instanceof cv)) && (((cv)paramHm).a() != null)) {
      j -= 1;
    }
    for (;;)
    {
      int k = 0;
      Object localObject2;
      Object localObject1;
      if (k < j)
      {
        localObject2 = paramHm.a(k);
        localObject1 = localObject2;
        if (((String)localObject2).equals(DTApplication.f().getResources().getString(a.l.catalog_name_favorites))) {
          localObject1 = "✩";
        }
        if ((!((String)localObject1).equals(DTApplication.f().getResources().getString(a.l.group))) && (!((String)localObject1).equals(DTApplication.f().getResources().getString(a.l.broadcast_list))) && (!((String)localObject1).equals("G20")))
        {
          localObject2 = localObject1;
          if (!((String)localObject1).equals(DTApplication.f().getResources().getString(a.l.conference_call_selected))) {}
        }
        else
        {
          localObject2 = "";
        }
        if ((localObject2 == null) || (((String)localObject2).isEmpty()) || (localHashMap.get(localObject2) != null)) {
          break label294;
        }
        localHashMap.put(localObject2, Integer.valueOf(i));
        i += 1;
      }
      label294:
      for (;;)
      {
        k += 1;
        break;
        paramHm = new String[localHashMap.size()];
        localObject1 = localHashMap.entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          paramHm[((Integer)localObject2.getValue()).intValue()] = ((String)((Map.Entry)localObject2).getKey());
        }
        Arrays.sort(paramHm);
        return paramHm;
      }
    }
  }
  
  public static String[] getCatalogForSideBarFromThreadLocal(hm paramHm)
  {
    int i = 0;
    HashMap localHashMap = new HashMap();
    int j = paramHm.getCount();
    if (((paramHm instanceof cv)) && (((cv)paramHm).a() != null)) {
      j -= 1;
    }
    for (;;)
    {
      int k = 0;
      Object localObject2;
      Object localObject1;
      if (k < j)
      {
        localObject2 = paramHm.b(k);
        localObject1 = localObject2;
        if (((String)localObject2).equals(DTApplication.f().getResources().getString(a.l.catalog_name_favorites))) {
          localObject1 = "✩";
        }
        if ((!((String)localObject1).equals(DTApplication.f().getResources().getString(a.l.group))) && (!((String)localObject1).equals(DTApplication.f().getResources().getString(a.l.broadcast_list))) && (!((String)localObject1).equals("G20")))
        {
          localObject2 = localObject1;
          if (!((String)localObject1).equals(DTApplication.f().getResources().getString(a.l.conference_call_selected))) {}
        }
        else
        {
          localObject2 = "";
        }
        if ((localObject2 == null) || (((String)localObject2).isEmpty()) || (localHashMap.get(localObject2) != null)) {
          break label294;
        }
        localHashMap.put(localObject2, Integer.valueOf(i));
        i += 1;
      }
      label294:
      for (;;)
      {
        k += 1;
        break;
        paramHm = new String[localHashMap.size()];
        localObject1 = localHashMap.entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          paramHm[((Integer)localObject2.getValue()).intValue()] = ((String)((Map.Entry)localObject2).getKey());
        }
        Arrays.sort(paramHm);
        return paramHm;
      }
    }
  }
  
  public static String getConferenceNotifyContentWithInviteKey(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4)
  {
    String str1 = el.a().aA();
    String str2 = el.a().ax();
    str1 = str2 + str1;
    return DTApplication.f().getApplicationContext().getString(a.l.conference_call_share_invite_attendees, new Object[] { paramString1, paramString2, ln.a(new Date(1000L * paramLong), false), paramString3, paramString4, str1 });
  }
  
  /* Error */
  public static int getContactsCount(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 895	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: getstatic 987	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
    //   7: aconst_null
    //   8: aconst_null
    //   9: aconst_null
    //   10: aconst_null
    //   11: invokevirtual 907	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   14: astore_0
    //   15: aload_0
    //   16: ifnull +24 -> 40
    //   19: aload_0
    //   20: invokeinterface 912 1 0
    //   25: istore_1
    //   26: aload_0
    //   27: invokeinterface 915 1 0
    //   32: iload_1
    //   33: ireturn
    //   34: astore_0
    //   35: iconst_0
    //   36: ireturn
    //   37: astore_0
    //   38: iload_1
    //   39: ireturn
    //   40: iconst_0
    //   41: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	paramContext	Context
    //   25	14	1	i	int
    // Exception table:
    //   from	to	target	type
    //   0	15	34	java/lang/Exception
    //   19	26	34	java/lang/Exception
    //   26	32	37	java/lang/Exception
  }
  
  public static String getCountryCodeByPhoneNumber(String paramString)
  {
    Object localObject;
    if ((paramString == null) || (paramString.isEmpty())) {
      localObject = "";
    }
    for (;;)
    {
      return localObject;
      String[] arrayOfString = hv.b;
      int i = Math.min(paramString.length(), 4);
      if (i >= 1) {
        try
        {
          String str = paramString.substring(0, i);
          int j = 0;
          for (;;)
          {
            if (j >= arrayOfString.length) {
              break label79;
            }
            boolean bool = arrayOfString[j].equals(str);
            localObject = str;
            if (bool) {
              break;
            }
            j += 1;
          }
          label79:
          i -= 1;
        }
        catch (Exception paramString)
        {
          DTLog.e("DtUtil", "getCountryCodeByPhoneNumber exception");
        }
      }
    }
    return "";
  }
  
  public static int getCountryCodeForSMS()
  {
    try
    {
      if ((el.a().aV() > 0) && (!"".equals(el.a().aY()))) {
        return el.a().aV();
      }
      if (el.a().cG() != 0) {
        return (short)el.a().cG();
      }
      String str2 = kd.e();
      String str1 = "";
      Object localObject = str1;
      if (str2 != null)
      {
        localObject = str1;
        if (!str2.isEmpty()) {
          localObject = ks.c(str2.toUpperCase());
        }
      }
      if ((localObject != null) && (!((String)localObject).isEmpty()))
      {
        DTLog.d("DtUtil", "getCountryCode from sim = " + (String)localObject);
        return Short.valueOf((String)localObject).shortValue();
      }
      localObject = LocationHelper.a().b();
      if (((ArrayList)localObject).size() == 1)
      {
        localObject = ks.c((String)((ArrayList)localObject).get(0));
        if (localObject != null) {
          return Integer.valueOf((String)localObject).intValue();
        }
      }
      localObject = el.a().by();
      if (!"".equals(localObject))
      {
        localObject = ks.c(((String)localObject).toUpperCase());
        if (localObject != null) {
          return Short.parseShort((String)localObject);
        }
      }
      localObject = DTApplication.f().getResources().getConfiguration().locale.getCountry();
      if ((localObject != null) && (!((String)localObject).isEmpty()))
      {
        localObject = ks.c(((String)localObject).toUpperCase());
        if (localObject != null)
        {
          int i = Short.parseShort((String)localObject);
          return i;
        }
        return 1;
      }
    }
    catch (Exception localException)
    {
      return 86;
    }
    return 1;
  }
  
  public static String getCurrentLauncherPackageName(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if ((paramContext == null) || (paramContext.activityInfo == null)) {
      return "";
    }
    if (paramContext.activityInfo.packageName.equals("android")) {
      return "";
    }
    return paramContext.activityInfo.packageName;
  }
  
  public static String getDecryptPhoneNumber(String paramString1, String paramString2)
  {
    return nativeGetDecryptPhoneNumber(paramString1, paramString2);
  }
  
  public static String getDeviceId()
  {
    return nativeGetDeviceId();
  }
  
  public static void getDeviceInfo(Context paramContext)
  {
    String str1 = ((TelephonyManager)paramContext.getSystemService("phone")).getSimOperator();
    el.a().F(str1);
    String str2 = String.format("%s_%s_%s", new Object[] { kl.a(paramContext), Build.MANUFACTURER, Build.MODEL });
    paramContext = String.format("%s_%s", new Object[] { Build.MANUFACTURER, Build.MODEL });
    el.a().M(paramContext);
    el.a().L(str2);
    str1 = Build.VERSION.RELEASE;
    paramContext = str1;
    if (str1.length() > 15) {
      paramContext = str1.substring(0, 15);
    }
    el.a().O(paramContext);
    DTLog.d("DtUtil", "device os ver " + Build.VERSION.RELEASE);
    DTLog.d("DtUtil", String.format("DeviceModel is %s", new Object[] { str2 }));
  }
  
  public static int getDownloadStatus(Context paramContext, long paramLong)
  {
    if (paramLong == -1L) {
      return -1;
    }
    paramContext = (DownloadManager)paramContext.getSystemService("download");
    DownloadManager.Query localQuery = new DownloadManager.Query();
    localQuery.setFilterById(new long[] { paramLong });
    paramContext = paramContext.query(localQuery);
    if ((paramContext != null) && (paramContext.moveToFirst())) {
      return paramContext.getInt(paramContext.getColumnIndex("status"));
    }
    return -1;
  }
  
  public static String getEncrypt(String paramString1, String paramString2)
  {
    return nativeGetEncrypt(paramString1, paramString2);
  }
  
  public static Date getEndOfDay(Date paramDate)
  {
    return org.apache.commons.lang.time.a.a(org.apache.commons.lang.time.a.c(paramDate, 5), -1);
  }
  
  public static String getFormatedPhoneNumber(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      str1 = "";
    }
    do
    {
      do
      {
        return str1;
        if ((DTSystemContext.getCountryCode() != 1) || (paramString.length() < 4)) {
          break;
        }
        str1 = paramString;
      } while (paramString.length() <= 6);
      if ((paramString.length() >= 5) && (paramString.length() <= 7) && (paramString.startsWith("1"))) {
        return paramString.substring(1);
      }
      str1 = paramString;
    } while (paramString.length() < 7);
    if ("99999999999".equals(paramString)) {
      return DTApplication.f().getString(a.l.unknown_phone_number);
    }
    String str1 = getFormatedUSPhoneNumber(paramString);
    String str2 = getCountryCodeByPhoneNumber(paramString);
    if ("1".equals(str2))
    {
      paramString = str1;
      if (!str2.equals(String.valueOf(DTSystemContext.getCountryCode()))) {}
    }
    for (paramString = str1.substring(2);; paramString = paramString.substring(String.valueOf(str2).length()))
    {
      return paramString;
      if (!str2.equals(String.valueOf(DTSystemContext.getCountryCode()))) {
        break;
      }
    }
    return getFormatedPrivatePhoneNumber(paramString);
  }
  
  public static String getFormatedPrivatePhoneNumber(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      str1 = "";
    }
    String str2;
    do
    {
      do
      {
        return str1;
        str2 = getCountryCodeByPhoneNumber(paramString);
        str1 = paramString;
      } while (str2 == null);
      str1 = paramString;
    } while (str2.isEmpty());
    if (str2.equals("1")) {
      return getFormatedUSPrivatePhoneNumber(paramString);
    }
    String str1 = getCountryCodeByPhoneNumber(el.a().aY());
    if ((!d.a(str1)) && (!d.a(str2)) && (str1.equals(str2))) {
      return paramString.substring(str2.length());
    }
    return "+" + str2 + " " + paramString.substring(str2.length());
  }
  
  public static String getFormatedUSPhoneNumber(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {}
    for (;;)
    {
      return paramString;
      if (paramString.length() < 11)
      {
        DTLog.e("DtUtil", "getFormatedUSPhoneNumber phoneNumber = " + paramString + " length < 11");
        return "+" + paramString;
      }
      try
      {
        String str1 = paramString.substring(0, 1);
        if (str1.equals("1"))
        {
          String str2 = paramString.substring(1, 4);
          String str3 = paramString.substring(4, 7);
          String str4 = paramString.substring(7);
          str1 = "+" + str1 + "(" + str2 + ") " + str3 + "-" + str4;
          return str1;
        }
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
    }
    return paramString;
  }
  
  public static String getFormatedUSPrivatePhoneNumber(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return paramString;
      if (paramString.length() < 11)
      {
        DTLog.e("DtUtil", "getFormatedUSPhoneNumber phoneNumber = " + paramString + " length < 11");
        return paramString;
      }
      try
      {
        if ("1".equals(paramString.substring(0, 1)))
        {
          String str2 = paramString.substring(1, 4);
          String str3 = paramString.substring(4, 7);
          String str4 = paramString.substring(7);
          String str1 = "";
          if (!el.a().cv()) {
            str1 = "+1";
          }
          str1 = str1 + " (" + str2 + ") " + str3 + "-" + str4;
          return str1;
        }
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
    }
    return paramString;
  }
  
  public static AdvertisingIdClient.Info getGADInfo()
  {
    DTLog.d("DtUtil", "begin getGADInfo");
    Object localObject = null;
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(DTApplication.f().getApplicationContext());
      localObject = localInfo;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        DTLog.e("DtUtil", "getGADInfo occured exception e =  " + localThrowable.getMessage());
      }
    }
    DTLog.d("DtUtil", "end getGADInfo");
    return localObject;
  }
  
  public static void getGADInfoAndPingTime()
  {
    ai.a().a(new hg());
  }
  
  public static int getIndexBarNumber(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return 0;
    }
    if ((paramArrayOfString[0].equals("#")) && (paramArrayOfString[(paramArrayOfString.length - 1)].equals("✩"))) {
      return paramArrayOfString.length - 2;
    }
    if ((paramArrayOfString[0].equals("#")) || (paramArrayOfString[(paramArrayOfString.length - 1)].equals("✩"))) {
      return paramArrayOfString.length - 1;
    }
    return paramArrayOfString.length;
  }
  
  public static ArrayList<String> getInstallAppName(String[] paramArrayOfString)
  {
    new ArrayList();
    Object localObject1 = DTApplication.f().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    label150:
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
      String str = ((PackageInfo)localObject2).packageName;
      int j = paramArrayOfString.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label150;
        }
        if (paramArrayOfString[i].equals(str))
        {
          localObject2 = ((PackageInfo)localObject2).applicationInfo.loadLabel(DTApplication.f().getPackageManager()).toString();
          localArrayList.add(localObject2);
          DTLog.d("DtUtil", "getInstallAppName app = " + (String)localObject2);
          break;
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public static ArrayList<String> getInstalledPackageName(String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = DTApplication.f().getPackageManager().getInstalledPackages(0).iterator();
    label90:
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      int j = paramArrayOfString.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label90;
        }
        String str = paramArrayOfString[i];
        if (str.equals(localPackageInfo.packageName))
        {
          localArrayList.add(str);
          break;
        }
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public static ArrayList<String> getInstalledPackagesName()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = DTApplication.f().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((PackageInfo)localIterator.next()).packageName);
    }
    return localArrayList;
  }
  
  public static String getInviteUrlByLanguage()
  {
    String str1 = el.a().ax();
    String str2 = el.a().ay();
    Locale localLocale = ln.a();
    DTLog.d("DtUtil", "getInviteUrlByLanguage is " + localLocale.getLanguage());
    if ((localLocale.getLanguage().endsWith("zh")) && (str2 != null) && (!"".equals(str2))) {
      return str2;
    }
    return str1;
  }
  
  public static String getLinkUrlWithInviteKey()
  {
    String str1 = el.a().aA();
    String str2 = el.a().ax();
    str1 = str2 + str1;
    DTLog.d("DtUtil", "getInviteKey inviteKey=" + str1);
    return str1;
  }
  
  public static String getLinkUrlWithoutInviteKey()
  {
    String str2 = el.a().ax();
    String str1 = str2;
    try
    {
      str2 = str2.substring(0, str2.indexOf("?"));
      str1 = str2;
      DTLog.d("DtUtil", "getShareContentWithoutInviteKey invite url =" + str2);
      return str2;
    }
    catch (Exception localException) {}
    return str1;
  }
  
  public static String getLocalISOCountryCode()
  {
    return DTSystemContext.getCountryCodeByTimezoneID(DTSystemContext.getTimeZone());
  }
  
  public static long getLocalTimeVerifyWithServer()
  {
    return el.a().de() * 1000 + System.currentTimeMillis();
  }
  
  public static String getLocaleInfo()
  {
    try
    {
      String str1 = DTApplication.f().getApplicationContext().getResources().getConfiguration().locale.getLanguage();
      String str2 = Locale.getDefault().getCountry();
      str1 = str1.toLowerCase() + "_" + str2;
      return str1;
    }
    catch (Exception localException) {}
    return "en_US";
  }
  
  public static String getMd5(String paramString)
  {
    paramString = paramString.trim();
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      paramString = byteArrayToHex(localMessageDigest.digest());
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static int getNavigationBarHeight(Activity paramActivity)
  {
    paramActivity = paramActivity.getResources();
    int j = paramActivity.getIdentifier("navigation_bar_height", "dimen", "android");
    int i = 0;
    if (j > 0) {
      i = paramActivity.getDimensionPixelSize(j);
    }
    return i;
  }
  
  public static PackageInfo getPackageInfo(String paramString, Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      paramContext.getPackageInfo(paramString, 1);
      DTLog.d("DtUtil", "isPackageInstalled packageName = " + paramString + " installed ");
      paramContext = paramContext.getPackageInfo(paramString, 1);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      DTLog.d("DtUtil", "isPackageInstalled packageName = " + paramString + " not installed");
    }
    return null;
  }
  
  public static String getPackageSigninfo(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures;
      paramContext = getMd5(new String(((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramContext[0].toByteArray()))).getSignature()));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int getPingEastTime()
  {
    return pingServer("es0.gsedge.net", 5);
  }
  
  public static int getPintWestTime()
  {
    return pingServer("es1.gsedge.net", 5);
  }
  
  public static String getPrivateNumberFromSmsGroupID(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {}
    for (;;)
    {
      return "";
      try
      {
        l = Long.valueOf(paramString).longValue();
        if (l != 0L)
        {
          paramString = p.a().c(l);
          if (paramString != null)
          {
            paramString = paramString.getSelf();
            if (paramString != null)
            {
              paramString = paramString.getRawId();
              if (paramString == null) {
                return "";
              }
            }
          }
        }
      }
      catch (NumberFormatException paramString)
      {
        for (;;)
        {
          long l = 0L;
        }
        DTLog.d("DtUtil", "getPrivateNumberFromSmsGroupID group privateNumber:" + paramString);
      }
    }
    return paramString;
  }
  
  public static long getSDFreeSize()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l2;
    if (Build.VERSION.SDK_INT >= 18) {
      l2 = localStatFs.getBlockSizeLong();
    }
    for (long l1 = localStatFs.getAvailableBlocksLong();; l1 = localStatFs.getAvailableBlocks())
    {
      return l1 * l2;
      l2 = localStatFs.getBlockSize();
    }
  }
  
  public static int getSMSCount(Context paramContext)
  {
    try
    {
      Uri localUri = Uri.parse("content://sms");
      paramContext = paramContext.getContentResolver().query(localUri, null, null, null, null);
      int i;
      if (paramContext != null) {
        i = paramContext.getCount();
      }
      return 0;
    }
    catch (Exception paramContext)
    {
      try
      {
        paramContext.close();
        return i;
      }
      catch (Exception paramContext)
      {
        return i;
      }
      paramContext = paramContext;
      return 0;
    }
  }
  
  public static String getShareContentWithInviteKey()
  {
    String str = getLinkUrlWithInviteKey();
    DTLog.d("DtUtil", "getShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone, new Object[] { str });
  }
  
  public static String getShareContentWithoutInviteKey()
  {
    String str = getLinkUrlWithoutInviteKey();
    DTLog.d("DtUtil", "getShareContentWithoutInviteKey invite url =" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone, new Object[] { str });
  }
  
  public static Date getStartOfDay(Date paramDate)
  {
    return org.apache.commons.lang.time.a.b(paramDate, 5);
  }
  
  public static float getStringWidth(String paramString, float paramFloat)
  {
    Paint localPaint = new Paint();
    localPaint.setTextSize(paramFloat);
    return localPaint.measureText(paramString);
  }
  
  public static String getThirdAuthorityFromPermission(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if (((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission))) && (!TextUtils.isEmpty(localProviderInfo.authority)))
            {
              paramContext = localProviderInfo.authority;
              return paramContext;
            }
            i += 1;
          }
        }
      }
      return "";
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String getTitleByUsersWithSplit(ArrayList<Long> paramArrayList, String paramString)
  {
    Iterator localIterator = paramArrayList.iterator();
    paramArrayList = null;
    if (localIterator.hasNext())
    {
      String str = lf.e((Long)localIterator.next());
      if (paramArrayList == null) {}
      for (paramArrayList = str;; paramArrayList = paramArrayList + paramString + str) {
        break;
      }
    }
    return paramArrayList;
  }
  
  public static String getTitleOfSubUsersOfGroupWithSplit(ArrayList<String> paramArrayList, String paramString, long paramLong)
  {
    Iterator localIterator = paramArrayList.iterator();
    paramArrayList = null;
    String str1 = "";
    while (localIterator.hasNext())
    {
      Object localObject = (String)localIterator.next();
      if (((String)localObject).equals(el.a().aN()))
      {
        str1 = DTApplication.f().getString(a.l.you);
      }
      else
      {
        String str2 = lf.d(Long.valueOf((String)localObject));
        localObject = str2;
        if ("".equals(str2)) {
          localObject = DTApplication.f().getString(a.l.unknown);
        }
        if (paramArrayList == null) {}
        for (;;)
        {
          paramArrayList = (ArrayList<String>)localObject;
          break;
          localObject = paramArrayList + paramString + (String)localObject;
        }
      }
    }
    paramString = paramArrayList;
    if (!"".equals(str1)) {
      paramString = str1 + ", " + paramArrayList;
    }
    return paramString;
  }
  
  public static String getTwitterShareContentWithInviteKey()
  {
    String str = getLinkUrlWithInviteKey();
    DTLog.i("DtUtil", "getTwitterShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone_twitter, new Object[] { str });
  }
  
  public static String getTwitterShareContentWithoutInviteKey()
  {
    String str = getLinkUrlWithoutInviteKey();
    DTLog.d("DtUtil", "getTwitterShareContentWithoutInviteKey invite url =" + str);
    return DTApplication.f().getApplicationContext().getString(a.l.share_usingdingtone_twitter, new Object[] { str });
  }
  
  public static String getUserAgent(Context paramContext)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 17) {
        return WebSettings.getDefaultUserAgent(paramContext);
      }
      paramContext = new WebView(paramContext);
      String str = paramContext.getSettings().getUserAgentString();
      paramContext.removeAllViews();
      paramContext.destroy();
      return str;
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static void gotoAppStore(Activity paramActivity)
  {
    if (paramActivity == null) {
      return;
    }
    try
    {
      if (w.d() == w.a)
      {
        paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(me.dingtone.app.im.u.a.V)));
        return;
      }
    }
    catch (Exception paramActivity)
    {
      com.crashlytics.android.a.a(paramActivity);
      DTLog.e("DtUtil", org.apache.commons.lang.exception.a.h(paramActivity));
      return;
    }
    String str = w.c();
    if ((str == null) || (str.isEmpty()))
    {
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(me.dingtone.app.im.u.a.V)));
      return;
    }
    paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(w.c())));
  }
  
  public static boolean hasFroyo()
  {
    return Build.VERSION.SDK_INT >= 8;
  }
  
  public static boolean hasGingerbread()
  {
    return Build.VERSION.SDK_INT >= 9;
  }
  
  public static boolean hasHoneycomb()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean hasHoneycombMR1()
  {
    return Build.VERSION.SDK_INT >= 12;
  }
  
  public static boolean hasIcecreamsandwitch()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  public static boolean hasJellyBean()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static String hashKey(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("SHA-1");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer(paramString.length * 2);
      int i = 0;
      while (i < paramString.length)
      {
        if ((paramString[i] & 0xFF) < 16) {
          ((StringBuffer)localObject).append("0");
        }
        ((StringBuffer)localObject).append(Integer.toString(paramString[i] & 0xFF, 16));
        i += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      DTLog.e("DtUtil", "hashKey exception e= " + paramString.getMessage());
    }
    return "";
  }
  
  public static void installApp(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      File localFile = new File(paramString);
      if (localFile.exists())
      {
        localIntent.setDataAndType(Uri.fromFile(localFile), "application/vnd.android.package-archive");
        paramContext.startActivity(localIntent);
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      DTLog.i("tag", paramContext.getMessage());
      return;
    }
    el.a().g(-1L);
    Download(paramContext, el.a().H(), paramString);
  }
  
  public static boolean isCallerIdForCallingChinaDisabled()
  {
    return (getCountryCodeByPhoneNumber(el.a().aj()).equals("86")) && (!el.a().ai());
  }
  
  public static boolean isCallerIdForCallingChinaEnabled()
  {
    return (getCountryCodeByPhoneNumber(el.a().aj()).equals("86")) && (el.a().ai());
  }
  
  public static boolean isCalleridPhoneNumberAnonymous()
  {
    boolean bool2 = false;
    String str = el.a().aj();
    boolean bool1 = bool2;
    if (str != null)
    {
      bool1 = bool2;
      if (!str.isEmpty())
      {
        bool1 = bool2;
        if (str.equals("anonymous")) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean isCurrentActivityChat()
  {
    return DTApplication.f().k() instanceof MessageChatActivity;
  }
  
  public static boolean isEmailValid(String paramString)
  {
    boolean bool = false;
    if (Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(paramString).matches()) {
      bool = true;
    }
    return bool;
  }
  
  @TargetApi(9)
  public static boolean isExternalStorageRemovable()
  {
    if (hasGingerbread()) {
      return Environment.isExternalStorageRemovable();
    }
    return true;
  }
  
  public static boolean isMiddleEastUser()
  {
    int i = getADCountryCode();
    return (i == 966) || (i == 971) || (i == 968) || (i == 965) || (i == 974) || (i == 967);
  }
  
  public static boolean isPackageInstalled(String paramString, Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    if ((paramString == null) || (paramString.isEmpty()))
    {
      DTLog.e("DtUtil", "package name is null or empty");
      return false;
    }
    DTLog.d("DtUtil", "isPackageInstalled packageName = " + paramString);
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      DTLog.d("DtUtil", "isPackageInstalled packageName = " + paramString + " installed ");
      return true;
    }
    catch (Throwable paramContext)
    {
      DTLog.d("DtUtil", "isPackageInstalled packageName = " + paramString + " not installed");
    }
    return false;
  }
  
  public static boolean isPhoneNumberLengthValid(String paramString)
  {
    boolean bool = true;
    if (paramString == null) {
      return false;
    }
    if (getCountryCodeForSMS() == 1) {
      if (paramString.startsWith("+")) {
        if (paramString.length() < 5) {}
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      if (paramString.length() < 4)
      {
        bool = false;
        continue;
        if (paramString.startsWith("+"))
        {
          if (paramString.length() < 8) {
            bool = false;
          }
        }
        else if (paramString.length() < 7) {
          bool = false;
        }
      }
    }
  }
  
  public static boolean isPhoneNumberStartWithPlus(String paramString)
  {
    if (paramString == null) {}
    while ((paramString.length() < 3) || (!paramString.startsWith("+"))) {
      return false;
    }
    return isPureDigital(paramString.substring(1));
  }
  
  public static boolean isPureDigital(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {}
    for (;;)
    {
      return false;
      try
      {
        boolean bool = Pattern.compile("^[0-9]*$", 2).matcher(paramString).matches();
        if (bool) {
          return true;
        }
      }
      catch (Exception paramString)
      {
        i.a("isPureDigtial exception e = " + org.apache.commons.lang.exception.a.h(paramString), false);
      }
    }
    return false;
  }
  
  public static boolean isPureDigitalOrStartWithPlus(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    if (paramString.startsWith("+")) {
      return isPureDigital(paramString.substring(1));
    }
    return isPureDigital(paramString);
  }
  
  public static boolean isRunningOnEmulator()
  {
    int k = 0;
    DTLog.i("DtUtil", "isRunningOnEmulator fingerprint " + Build.FINGERPRINT + " model " + Build.MODEL + " manufacture " + Build.MANUFACTURER + " brand " + Build.BRAND + " product " + Build.PRODUCT);
    int i;
    if ((Build.FINGERPRINT.startsWith("generic")) || (Build.MODEL.contains("google_sdk")) || (Build.MODEL.contains("Emulator")) || (Build.MODEL.contains("Android SDK built for x86")) || (Build.MANUFACTURER.contains("Genymotion")))
    {
      i = 1;
      if (i == 0) {
        break label151;
      }
    }
    label151:
    do
    {
      return true;
      i = 0;
      break;
      int j = k;
      if (Build.BRAND.startsWith("generic"))
      {
        j = k;
        if (Build.DEVICE.startsWith("generic")) {
          j = 1;
        }
      }
      i = j | i;
    } while (i != 0);
    return "google_sdk".equals(Build.PRODUCT) | i;
  }
  
  public static boolean isShortCutExist(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    if (TextUtils.isEmpty("")) {}
    for (Object localObject = getAuthorityFromPermission(paramContext);; localObject = "")
    {
      DTLog.i("DtUtil", "AUTHORITY...." + (String)localObject);
      ContentResolver localContentResolver = paramContext.getContentResolver();
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        try
        {
          localObject = Uri.parse((String)localObject);
          Intent.ShortcutIconResource.fromContext(paramContext, a.g.icon);
          paramContext = localContentResolver.query((Uri)localObject, null, "title=?", new String[] { paramContext.getResources().getString(a.l.app_name) }, null);
          if (paramContext == null) {
            break label173;
          }
          int i = paramContext.getCount();
          if (i <= 0) {
            break label173;
          }
          bool1 = true;
        }
        catch (Exception paramContext)
        {
          for (;;)
          {
            try
            {
              if (!paramContext.isClosed())
              {
                paramContext.close();
                bool2 = bool1;
              }
              return bool2;
            }
            catch (Exception paramContext)
            {
              continue;
            }
            paramContext = paramContext;
            boolean bool1 = false;
            DTLog.e("isShortCutExist", paramContext.getMessage());
            bool2 = bool1;
            continue;
            bool1 = false;
          }
        }
        bool2 = bool1;
        if (paramContext == null) {}
      }
      label173:
      for (boolean bool2 = bool1;; bool2 = false) {}
    }
  }
  
  public static boolean isSimReady(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return false;
    }
    return paramContext.getSimState() == 5;
  }
  
  public static boolean isSmallScreen()
  {
    int i = (int)(ht.a / ht.c);
    int j = (int)(ht.b / ht.c);
    return (i < 320) || (j < 480);
  }
  
  public static boolean isUAEuser()
  {
    try
    {
      if ((el.a().cG() != 0) && (el.a().cG() == 971)) {
        return true;
      }
      Object localObject = LocationHelper.a().b();
      if (((ArrayList)localObject).size() == 1)
      {
        localObject = (String)((ArrayList)localObject).get(0);
        if ((localObject != null) && ("AE".equalsIgnoreCase((String)localObject))) {
          return true;
        }
      }
      if ((el.a().aV() > 0) && (!"".equals(el.a().aY())) && (el.a().aV() == 971)) {
        return true;
      }
      localObject = ((TelephonyManager)DTApplication.f().getSystemService("phone")).getSimCountryIso();
      if ((localObject != null) && (!((String)localObject).isEmpty()) && ("AE".equalsIgnoreCase((String)localObject))) {
        return true;
      }
      localObject = el.a().by();
      if ((localObject != null) && (!"".equals(localObject)) && ("AE".equalsIgnoreCase((String)localObject))) {
        return true;
      }
      localObject = TimeZone.getDefault().getID();
      if ((localObject != null) && (d.d((String)localObject, "Asia/Dubai")))
      {
        DTLog.d("DtUtil", "isUAEuser timezon is " + (String)localObject);
        return true;
      }
      localObject = DTApplication.f().getResources().getConfiguration().locale.getCountry();
      if ((localObject != null) && (!((String)localObject).isEmpty()) && ("AE".equalsIgnoreCase((String)localObject))) {
        return true;
      }
      boolean bool = "AE".equals(DTSystemContext.getISOLanguageCode());
      if (bool) {
        return true;
      }
    }
    catch (Exception localException)
    {
      return false;
    }
    return false;
  }
  
  public static boolean isUSUser()
  {
    int i = el.a().cG();
    if (i != 0) {
      if (i != 1) {}
    }
    do
    {
      String str;
      do
      {
        do
        {
          return true;
          return false;
          str = el.a().by();
          if ("".equals(str)) {
            break;
          }
        } while (str.equalsIgnoreCase("US"));
        return false;
        str = kd.e();
        if (str == null) {
          break;
        }
      } while ("US".equalsIgnoreCase(str));
      return false;
      if ((el.a().aV() <= 0) || ("".equals(el.a().aY()))) {
        break;
      }
    } while (el.a().aV() == 1);
    return false;
    return false;
  }
  
  public static String md5HexDigest(String paramString)
  {
    return nativeMd5HexDigest(paramString);
  }
  
  private static native String nativeGetDecryptPhoneNumber(String paramString1, String paramString2);
  
  private static native String nativeGetDeviceId();
  
  private static native String nativeGetEncrypt(String paramString1, String paramString2);
  
  private static native DTMessage nativeJson2TDmsg(int paramInt, String paramString);
  
  private static native String nativeMd5HexDigest(String paramString);
  
  public static boolean parsePrivateNumberSMSExtraInfo(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return false;
    }
    try
    {
      Object localObject = new JSONObject(paramString).getJSONObject("private_numbers");
      paramString = ((JSONObject)localObject).getString("pn");
      localObject = ((JSONObject)localObject).getString("uid");
      DTLog.i("DtUtil", "parsePrivateNumberSMSExtraInfo parsed phoneNumber:" + paramString + " uid:" + (String)localObject);
      return true;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
      DTLog.i("DtUtil", "parsePrivateNumberSMSExtraInfo parsed error");
    }
    return false;
  }
  
  public static int pingServer(String paramString, int paramInt)
  {
    int j = 0;
    if ((paramString == null) || (paramString.equals(""))) {
      return 0;
    }
    int i = paramInt;
    if (paramInt <= 0) {
      i = 3;
    }
    for (;;)
    {
      try
      {
        localObject = Runtime.getRuntime().exec("/system/bin/ping -c " + i + " " + paramString);
        if (((Process)localObject).waitFor() == 0)
        {
          DTLog.i("DtUtil", String.format("ping %s success.", new Object[] { paramString }));
          paramString = new BufferedReader(new InputStreamReader(((Process)localObject).getInputStream()));
          new String();
          paramInt = 0;
          i = j;
          localObject = paramString.readLine();
          if (localObject == null) {
            break label257;
          }
          if ((!((String)localObject).contains("time=")) || (!((String)localObject).contains(" ms"))) {
            continue;
          }
          localObject = ((String)localObject).substring(((String)localObject).indexOf("time=") + 5, ((String)localObject).indexOf(" ms"));
          f1 = i;
        }
      }
      catch (Exception paramString)
      {
        Object localObject;
        float f1;
        float f2;
        paramString.printStackTrace();
        return 100000;
      }
      try
      {
        f2 = Float.parseFloat((String)localObject);
        i = (int)(f2 + f1);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        DTLog.e("DtUtil", localNumberFormatException.toString());
        continue;
      }
      paramInt += 1;
      continue;
      DTLog.i("DtUtil", String.format("ping %s failed.", new Object[] { paramString }));
    }
    label257:
    if (paramInt != 0)
    {
      paramInt = i / paramInt;
      return paramInt;
    }
    return 100000;
  }
  
  public static void preSaveCountryCode()
  {
    localEditor = jo.b();
    for (;;)
    {
      try
      {
        if ((el.a().aY() != null) && (!"".equals(el.a().aY())))
        {
          localObject = getCountryCodeByPhoneNumber(el.a().aY());
          localEditor.putString((String)localObject, (String)localObject);
        }
        if ((el.a().bx() != null) && (!"".equals(el.a().bx())))
        {
          localObject = getCountryCodeByPhoneNumber(el.a().bx());
          localEditor.putString((String)localObject, (String)localObject);
        }
        String str2 = ((TelephonyManager)DTApplication.f().getSystemService("phone")).getSimCountryIso();
        String str1 = "";
        Object localObject = str1;
        if (str2 != null)
        {
          localObject = str1;
          if (!str2.isEmpty()) {
            localObject = ks.c(str2.toUpperCase());
          }
        }
        if ((localObject != null) && (!((String)localObject).isEmpty()))
        {
          DTLog.d("DtUtil", "getCountryCode from sim = " + (String)localObject);
          localEditor.putString((String)localObject, (String)localObject);
        }
        if (el.a().cG() != 0) {
          localEditor.putString(String.valueOf(el.a().cG()), String.valueOf(el.a().cG()));
        }
        str1 = el.a().by();
        if ((!"".equals(str1)) && (ks.c(str1.toUpperCase()) != null)) {
          localEditor.putString((String)localObject, (String)localObject);
        }
        localObject = DTApplication.f().getResources().getConfiguration().locale.getCountry();
        if ((localObject == null) || (((String)localObject).isEmpty())) {
          continue;
        }
        localObject = ks.c(((String)localObject).toUpperCase());
        if (localObject == null) {
          continue;
        }
        i = Short.parseShort((String)localObject);
      }
      catch (Exception localException)
      {
        localEditor.putString("86", "86");
        continue;
        int i = 1;
        continue;
      }
      localEditor.putString(String.valueOf(i), String.valueOf(i));
      localEditor.commit();
      return;
      i = 1;
    }
  }
  
  public static void printProcessInfo()
  {
    ActivityManager localActivityManager = (ActivityManager)DTApplication.f().getSystemService("activity");
    Iterator localIterator = localActivityManager.getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      int i = localRunningAppProcessInfo.pid;
      int j = localRunningAppProcessInfo.uid;
      String str = localRunningAppProcessInfo.processName;
      int k = localActivityManager.getProcessMemoryInfo(new int[] { i })[0].dalvikPrivateDirty;
      DTLog.i("DtUtil", String.format("processName(%s),pid(%d),uid(%d),pkgList(%s),memory size(%dkb)\n", new Object[] { str, Integer.valueOf(i), Integer.valueOf(j), Arrays.toString(localRunningAppProcessInfo.pkgList), Integer.valueOf(k) }));
    }
  }
  
  public static String removePrefixZeroAndNonDigialCharacterOfPhoneNumber(String paramString)
  {
    return paramString.replaceAll("^0+(?!$)", "").replaceAll("[^\\d]*", "");
  }
  
  public static void restartApplication(Context paramContext)
  {
    Object localObject = new Intent();
    ((Intent)localObject).setClassName(paramContext.getPackageName(), me.dingtone.app.im.u.a.b.getName());
    localObject = PendingIntent.getActivity(paramContext.getApplicationContext(), 0, (Intent)localObject, 268435456);
    ((AlarmManager)paramContext.getSystemService("alarm")).set(1, System.currentTimeMillis() + 1000L, (PendingIntent)localObject);
    exit();
  }
  
  public static boolean runningOnJellyBeanMR1OrHigher()
  {
    return Build.VERSION.SDK_INT >= 17;
  }
  
  public static String secondsToCallTimeFormat(int paramInt)
  {
    int i = paramInt / 3600;
    int j = (paramInt - i * 3600) / 60;
    paramInt = paramInt - i * 3600 - j * 60;
    String str1 = null;
    String str2;
    if ((i > 0) && (i < 10))
    {
      str1 = String.format("0%d", new Object[] { Integer.valueOf(i) });
      if ((j < 0) || (j >= 10)) {
        break label170;
      }
      str2 = String.format("0%d", new Object[] { Integer.valueOf(j) });
      label89:
      if ((paramInt < 0) || (paramInt >= 10)) {
        break label192;
      }
    }
    label170:
    label192:
    for (String str3 = String.format("0%d", new Object[] { Integer.valueOf(paramInt) });; str3 = String.format("%d", new Object[] { Integer.valueOf(paramInt) }))
    {
      if (str1 != null) {
        break label214;
      }
      return String.format("%s:%s", new Object[] { str2, str3 });
      if (i < 10) {
        break;
      }
      str1 = String.format("%d", new Object[] { Integer.valueOf(i) });
      break;
      str2 = String.format("%d", new Object[] { Integer.valueOf(j) });
      break label89;
    }
    label214:
    return String.format("%s:%s:%s", new Object[] { str1, str2, str3 });
  }
  
  public static void showDownloadDialog(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (!DTApplication.f().j().f()) {
      return;
    }
    ao.a(paramContext, paramString2, paramString1, null, paramContext.getResources().getString(a.l.update_dialog_btn_download), new hf(paramString3, paramContext));
  }
  
  public static void startAppService(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    paramContext.startService(localIntent);
  }
  
  public static String stringListToStringWithSplit(ArrayList<String> paramArrayList, String paramString)
  {
    Iterator localIterator = paramArrayList.iterator();
    paramArrayList = null;
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (paramArrayList == null) {}
      for (paramArrayList = str;; paramArrayList = paramArrayList + paramString + str) {
        break;
      }
    }
    return paramArrayList;
  }
  
  public static List<String> stringsToList(String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      localArrayList.add(paramArrayOfString[i]);
      i += 1;
    }
    return localArrayList;
  }
  
  public static void suspendPrivateNumberWhenNotBind(Context paramContext)
  {
    long l1;
    String str;
    int i;
    PrivatePhoneItemOfMine localPrivatePhoneItemOfMine;
    long l2;
    if ((el.a().w() == s.c) && (AppConnectionManager.a().d().booleanValue()))
    {
      l1 = el.a().de() * 1000;
      long l3 = System.currentTimeMillis() + l1;
      DTLog.i("DtUtil", "suspendPrivateNumberWhenNotBind current:" + ln.e(l3));
      Object localObject = me.dingtone.app.im.privatephone.ai.a().j();
      str = "";
      DTLog.d("DtUtil", "suspendPrivateNumberWhenNotBind item list:" + localObject);
      if (localObject != null)
      {
        DTLog.d("DtUtil", "suspendPrivateNumberWhenNotBind item list size:" + ((ArrayList)localObject).size());
        i = 0;
        l1 = 0L;
        localObject = ((ArrayList)localObject).iterator();
        if (((Iterator)localObject).hasNext())
        {
          localPrivatePhoneItemOfMine = (PrivatePhoneItemOfMine)((Iterator)localObject).next();
          l2 = l3 - localPrivatePhoneItemOfMine.getGainTime();
          if ((l2 <= 0L) || (localPrivatePhoneItemOfMine.isSuspendFlag())) {
            break label445;
          }
          if (l2 >= 86400000L)
          {
            localPrivatePhoneItemOfMine = (PrivatePhoneItemOfMine)localPrivatePhoneItemOfMine.clone();
            localPrivatePhoneItemOfMine.suspendFlag = true;
            localPrivatePhoneItemOfMine.primaryFlag = false;
            DTLog.d("DtUtil", "suspendPrivateNumberWhenNotBind suspend privateNumber:" + localPrivatePhoneItemOfMine.getPhoneNumber() + " gainTime:" + ln.e(localPrivatePhoneItemOfMine.getGainTime()));
            q.a().a(localPrivatePhoneItemOfMine.getPhoneNumber(), true);
            q.a().b(localPrivatePhoneItemOfMine.getPhoneNumber(), true);
            e.a(localPrivatePhoneItemOfMine.getPhoneNumber(), true);
            me.dingtone.app.im.privatephone.ai.a().l(localPrivatePhoneItemOfMine);
          }
        }
      }
    }
    label445:
    for (;;)
    {
      break;
      if (i == 0)
      {
        l1 = System.currentTimeMillis();
        str = localPrivatePhoneItemOfMine.getPhoneNumber();
        i = 1;
        l1 += 86400000L - l2;
      }
      else
      {
        l2 = 86400000L - l2 + System.currentTimeMillis();
        if (l1 > l2)
        {
          str = localPrivatePhoneItemOfMine.getPhoneNumber();
          l1 = l2;
          continue;
          if (i != 0)
          {
            DTLog.i("DtUtil", "suspendPrivateNumberWhenNotBind  createAlarm privatePhoneNumber:" + str + " alarTime:" + ln.e(l1));
            f.a().a(paramContext, l1, str);
          }
          return;
        }
      }
    }
  }
  
  public static void unSuspendAllPrivateNumberWhenBinded()
  {
    if (AppConnectionManager.a().d().booleanValue())
    {
      Object localObject = me.dingtone.app.im.privatephone.ai.a().j();
      if (localObject != null)
      {
        localObject = ((ArrayList)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          PrivatePhoneItemOfMine localPrivatePhoneItemOfMine1 = (PrivatePhoneItemOfMine)((Iterator)localObject).next();
          if ((localPrivatePhoneItemOfMine1.isSuspendFlag()) && (localPrivatePhoneItemOfMine1.mBAutoSuspend))
          {
            PrivatePhoneItemOfMine localPrivatePhoneItemOfMine2 = (PrivatePhoneItemOfMine)localPrivatePhoneItemOfMine1.clone();
            localPrivatePhoneItemOfMine2.suspendFlag = false;
            q.a().b(localPrivatePhoneItemOfMine2.phoneNumber, false);
            q.a().a(localPrivatePhoneItemOfMine2.phoneNumber, false);
            e.a(localPrivatePhoneItemOfMine2.getPhoneNumber(), false);
            DTLog.d("DtUtil", "unSuspendAllPrivateNumberWhenBinded privateNumber:" + localPrivatePhoneItemOfMine1.getPhoneNumber());
            me.dingtone.app.im.privatephone.ai.a().l(localPrivatePhoneItemOfMine2);
          }
        }
      }
    }
  }
  
  public static void updateTheCountOfPrivateNumberSMSMessage(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {}
    PrivatePhoneItemOfMine localPrivatePhoneItemOfMine;
    do
    {
      return;
      localPrivatePhoneItemOfMine = me.dingtone.app.im.privatephone.ai.a().c(paramString);
      DTLog.i("DtUtil", "updateTheCountOfPrivateNumberSMSMessage prvateNumber:" + paramString + " privateItem:" + localPrivatePhoneItemOfMine);
    } while (localPrivatePhoneItemOfMine == null);
    bd.a().i(paramString);
  }
}
