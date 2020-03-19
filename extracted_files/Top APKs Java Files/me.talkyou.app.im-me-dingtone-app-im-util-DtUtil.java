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
import android.provider.ContactsContract.Contacts;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.example.adlibrary.utils.NetUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import me.dingtone.app.im.activity.DTActivity;
import me.dingtone.app.im.activity.LinkEmailAddressActivity;
import me.dingtone.app.im.activity.LinkSecondPhoneActivity;
import me.dingtone.app.im.activity.MessageChatActivity;
import me.dingtone.app.im.adapter.bx;
import me.dingtone.app.im.datatype.PrivatePhoneItemOfMine;
import me.dingtone.app.im.datatype.message.DTMessage;
import me.dingtone.app.im.dialog.q;
import me.dingtone.app.im.entity.AppInviteConfig;
import me.dingtone.app.im.group.HybridGroup;
import me.dingtone.app.im.group.HybridGroupMember;
import me.dingtone.app.im.h.a.b;
import me.dingtone.app.im.h.a.g;
import me.dingtone.app.im.h.a.m;
import me.dingtone.app.im.log.DTLog;
import me.dingtone.app.im.manager.AppConnectionManager;
import me.dingtone.app.im.manager.DTApplication;
import me.dingtone.app.im.manager.NetworkMonitor;
import me.dingtone.app.im.manager.ap;
import me.dingtone.app.im.manager.f;
import me.dingtone.app.im.privatephone.n;
import me.dingtone.app.im.util.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class DtUtil
{
  public static final int PING_TIMEOUT = 100000;
  private static final String SP_USER_AGENT_FILE = "sp_user_agent_file";
  private static final String SP_USER_AGENT_KEY = "sp_user_agent_key";
  private static final String SP_USER_AGENT_SAVE_TIME = "sp_user_agent_save_time";
  public static final long UnbindSuspendPrivateNumberTime = 86400000L;
  static final Long WeekMillis = Long.valueOf(604800000L);
  static List<PackageInfo> pkgInfoList = new ArrayList();
  private static boolean sActivityChatInBackground = false;
  private static boolean sActivityChatInStack = false;
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
        paramString1.setDestinationUri(bh.a(paramContext, new File(paramString2)));
        switch (getDownloadStatus(paramContext, ap.a().C()))
        {
        case 3: 
        case 5: 
        case 6: 
        case 7: 
          deleteFile(paramString2);
          if (ap.a().C() != -1L) {
            localDownloadManager.remove(new long[] { ap.a().C() });
          }
          long l = localDownloadManager.enqueue(paramString1);
          ap.a().g(l);
          ap.a().h(paramString2);
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
  
  public static int calculateDaysBetween(String paramString1, String paramString2)
  {
    if ((org.apache.commons.lang.d.a(paramString1)) || (org.apache.commons.lang.d.a(paramString2))) {
      return -1;
    }
    try
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(localSimpleDateFormat.parse(paramString1));
      long l = localCalendar.getTimeInMillis();
      localCalendar.setTime(localSimpleDateFormat.parse(paramString2));
      int i = Integer.parseInt(String.valueOf((localCalendar.getTimeInMillis() - l) / 86400000L));
      return i;
    }
    catch (ParseException paramString1)
    {
      paramString1.printStackTrace();
    }
    return -1;
  }
  
  public static void checkAndGetUserAgent()
  {
    if (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()) {}
    for (boolean bool = true;; bool = false)
    {
      g.a("getUserAgent shoudl run in main thread", bool);
      if ((ap.a().cP() == null) || ("".equals(ap.a().cP())))
      {
        String str = getUserAgent(DTApplication.g());
        ap.a().al(str);
      }
      return;
    }
  }
  
  public static boolean checkIsVPNOpen()
  {
    if (VPNChecker.a().e())
    {
      boolean bool = checkVPNConnectionByNetworkInterface();
      DTLog.i("DtUtil", "checkIsVPNOpen use network interface method check vpn connection. isOpen: " + bool);
      return bool;
    }
    Object localObject = b.a().e();
    String str;
    if ((localObject != null) && (!((ArrayList)localObject).isEmpty()))
    {
      localObject = (String)((ArrayList)localObject).get(0);
      str = ap.a().cC();
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
        str = ap.a().cC();
        DTLog.d("DtUtil", "checkIsVPNOpen time zone iso cc: " + (String)localObject + ", isoCCServer: " + str);
        if (!((String)localObject).equals(str)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean checkSupportCallVpnBySimCard()
  {
    String str = ((TelephonyManager)DTApplication.g().getSystemService("phone")).getSimCountryIso();
    DTLog.i("DtUtil", "uae vpn has checkSupportCallVpnBySimCard isoCountryCode = " + str);
    ArrayList localArrayList = me.dingtone.app.im.manager.g.c().af();
    if (localArrayList == null) {}
    while (localArrayList.size() == 0) {
      return false;
    }
    if ((str != null) && (!str.isEmpty()))
    {
      DTLog.i("DtUtil", "uae vpn checkSupportCallVpnBySimCard getCountryCode from sim = " + str);
      int i = 0;
      while (i < localArrayList.size())
      {
        if (((String)localArrayList.get(i)).equalsIgnoreCase(str))
        {
          me.dingtone.app.im.tracker.d.a().b("VPNTipV2", "uae_vpn_tip_not_show", null, 0L);
          DTLog.i("DtUtil", "uae vpn checkSupportCallVpnBySimCard return true");
          return true;
        }
        i += 1;
      }
    }
    DTLog.i("DtUtil", "uae vpn checkSupportCallVpnBySimCard return false");
    return false;
  }
  
  public static boolean checkSystemLanguage()
  {
    String str = dp.a();
    return (str.startsWith("zh")) || (str.startsWith("en")) || (str.startsWith("es")) || (str.startsWith("fr")) || (str.startsWith("pt"));
  }
  
  public static boolean checkVPNConnectionByNetworkInterface()
  {
    return NetUtils.checkVPN(DTApplication.g().getApplicationContext());
  }
  
  public static int compareVersion(String paramString1, String paramString2)
  {
    if (paramString1.isEmpty()) {
      return -1;
    }
    if (paramString2.isEmpty()) {
      return 1;
    }
    for (;;)
    {
      try
      {
        paramString1 = paramString1.split("\\.");
        paramString2 = paramString2.split("\\.");
        int m = Math.max(paramString1.length, paramString2.length);
        int i = 0;
        if (i >= m) {
          break label119;
        }
        if (paramString1.length <= i) {
          break label114;
        }
        j = Integer.valueOf(paramString1[i]).intValue();
        if (paramString2.length > i)
        {
          k = Integer.valueOf(paramString2[i]).intValue();
          if (j < k) {
            break;
          }
          if (j > k) {
            return 1;
          }
          i += 1;
          continue;
        }
        int k = 0;
      }
      catch (Exception paramString1)
      {
        return -1;
      }
      continue;
      label114:
      int j = 0;
    }
    label119:
    return 0;
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
  
  public static int currencyCeilToCredits(float paramFloat)
  {
    float f2 = ap.a().cg();
    float f1 = f2;
    if (f2 < 1.0E-6D) {
      f1 = 0.02F;
    }
    return (int)Math.ceil(paramFloat / f1);
  }
  
  public static float currencyToCredits(float paramFloat)
  {
    float f2 = ap.a().cg();
    float f1 = f2;
    if (f2 < 1.0E-6D) {
      f1 = 0.02F;
    }
    return Math.round(paramFloat / f1);
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
    Object localObject = getAESKey();
    IvParameterSpec localIvParameterSpec = new IvParameterSpec(getAESIV().getBytes());
    localObject = new SecretKeySpec(((String)localObject).getBytes(), "AES");
    try
    {
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
      localCipher.init(1, (Key)localObject, localIvParameterSpec);
      paramArrayOfByte = new String(Base64.encode(localCipher.doFinal(paramArrayOfByte), 0), "UTF-8");
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
      paramArrayOfByte = org.apache.commons.lang.exception.a.h(paramArrayOfByte);
      DTLog.e("DtUtil", "encryptAESData e = " + paramArrayOfByte);
    }
    return "";
  }
  
  public static String encryptCreditCardPhotoAESData(byte[] paramArrayOfByte, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = getCreditCardPhotoAESKey(paramString1, paramString3);
    paramString2 = getCreditCardPhotoAESIV(paramString2, paramString3);
    if ((org.apache.commons.lang.d.a(paramString1)) || (org.apache.commons.lang.d.a(paramString2)) || (paramArrayOfByte == null)) {
      return null;
    }
    paramString2 = new IvParameterSpec(paramString2.getBytes());
    paramString1 = new SecretKeySpec(paramString1.getBytes(), "AES");
    try
    {
      paramString3 = Cipher.getInstance("AES/CBC/PKCS5Padding");
      paramString3.init(1, paramString1, paramString2);
      paramArrayOfByte = paramString3.doFinal(paramArrayOfByte);
      DTLog.d("DtUtil", "encryptCreditCardPhotoAESData data length = " + paramArrayOfByte.length);
      paramArrayOfByte = new String(Base64.encode(paramArrayOfByte, 2));
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
      paramArrayOfByte = org.apache.commons.lang.exception.a.h(paramArrayOfByte);
      DTLog.e("DtUtil", "encryptCreditCardPhotoAESData e = " + paramArrayOfByte);
    }
    return null;
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
    throws NoSuchAlgorithmException
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
      if (ap.a().cB() != 0) {
        return (short)ap.a().cB();
      }
      Object localObject = b.a().e();
      if (((ArrayList)localObject).size() == 1)
      {
        localObject = du.c((String)((ArrayList)localObject).get(0));
        if (localObject != null) {
          return Integer.valueOf((String)localObject).intValue();
        }
      }
      if ((ap.a().aU() > 0) && (!"".equals(ap.a().aX()))) {
        return ap.a().aU();
      }
      String str2 = dk.n();
      String str1 = "";
      localObject = str1;
      if (str2 != null)
      {
        localObject = str1;
        if (!str2.isEmpty()) {
          localObject = du.c(str2.toUpperCase());
        }
      }
      if ((localObject != null) && (!((String)localObject).isEmpty()))
      {
        DTLog.d("DtUtil", "getCountryCode from sim = " + (String)localObject);
        return Short.valueOf((String)localObject).shortValue();
      }
      localObject = ap.a().bx();
      if (!"".equals(localObject))
      {
        localObject = du.c(((String)localObject).toUpperCase());
        if (localObject != null) {
          return Short.parseShort((String)localObject);
        }
      }
      localObject = DTApplication.g().getResources().getConfiguration().locale.getCountry();
      if ((localObject != null) && (!((String)localObject).isEmpty()))
      {
        localObject = du.c(((String)localObject).toUpperCase());
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
    String str = ap.a().aM();
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
    String str = ap.a().aM();
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
  
  public static String getAdCountryIso()
  {
    Object localObject1 = "";
    Object localObject2;
    if (!"".equals(ap.a().cC()))
    {
      localObject2 = ap.a().cC();
      localObject1 = localObject2;
      if ("-".equals(localObject2)) {
        localObject1 = "";
      }
    }
    DTLog.d("DtUtil", "getAdCountryIso from login ip code :" + (String)localObject1);
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!"".equals(localObject1)) {}
    }
    else
    {
      int i = ap.a().cB();
      localObject2 = localObject1;
      if (i > 0) {
        localObject2 = du.d(i + "");
      }
    }
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"".equals(localObject2)) {}
    }
    else
    {
      if (!"".equals(ap.a().bx())) {
        localObject2 = ap.a().bx();
      }
      localObject1 = localObject2;
      if ("-".equals(localObject2)) {
        localObject1 = "";
      }
      DTLog.d("DtUtil", "getAdCountryIso iso code from ping " + (String)localObject1);
    }
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!"".equals(localObject1)) {}
    }
    else
    {
      String str = ap.a().aX();
      localObject2 = localObject1;
      if (str != null)
      {
        localObject2 = localObject1;
        if (!str.isEmpty())
        {
          DTLog.i("DtUtil", "getISOCode...mainCountryCode :" + ap.a().aU());
          localObject2 = du.d(getCountryCodeByPhoneNumber(str));
        }
      }
    }
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"".equals(localObject2)) {}
    }
    else
    {
      localObject1 = dk.n();
    }
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!"".equals(localObject1)) {}
    }
    else
    {
      localObject2 = getLocalISOCountryCode();
    }
    return localObject2;
  }
  
  public static String getAdCountryIsoVPN()
  {
    Object localObject1 = "";
    Object localObject2;
    if (!AppConnectionManager.a().d().booleanValue())
    {
      DTLog.i("DtUtil", "app is not logined , use timezone " + getLocalISOCountryCode());
      localObject2 = getLocalISOCountryCode();
    }
    do
    {
      return localObject2;
      if (!"".equals(ap.a().cC()))
      {
        localObject2 = ap.a().cC();
        localObject1 = localObject2;
        if ("-".equals(localObject2)) {
          localObject1 = "";
        }
      }
      DTLog.d("DtUtil", "getAdCountryIso from login ip code :" + (String)localObject1);
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (!"".equals(localObject1)) {}
      }
      else
      {
        int i = ap.a().cB();
        localObject2 = localObject1;
        if (i > 0) {
          localObject2 = du.d(i + "");
        }
      }
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (!"".equals(localObject2)) {}
      }
      else
      {
        if (!"".equals(ap.a().bx())) {
          localObject2 = ap.a().bx();
        }
        localObject1 = localObject2;
        if ("-".equals(localObject2)) {
          localObject1 = "";
        }
        DTLog.d("DtUtil", "getAdCountryIso iso code from ping " + (String)localObject1);
      }
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (!"".equals(localObject1)) {}
      }
      else
      {
        String str = ap.a().aX();
        localObject2 = localObject1;
        if (str != null)
        {
          localObject2 = localObject1;
          if (!str.isEmpty())
          {
            DTLog.i("DtUtil", "getISOCode...mainCountryCode :" + ap.a().aU());
            localObject2 = du.d(getCountryCodeByPhoneNumber(str));
          }
        }
      }
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (!"".equals(localObject2)) {}
      }
      else
      {
        localObject1 = dk.n();
      }
      if (localObject1 == null) {
        break;
      }
      localObject2 = localObject1;
    } while (!"".equals(localObject1));
    return getLocalISOCountryCode();
  }
  
  public static int getAppVersion()
  {
    return 433;
  }
  
  public static String getAppVersionCodeWithBuildNumber()
  {
    return getAppVersionName() + "." + "13812";
  }
  
  public static String getAppVersionName()
  {
    return "3.8.2";
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
    String str2 = ap.a().ay();
    String str3 = ap.a().aa(7);
    String str1 = str3 + str2;
    if ((str3 == null) || ("".equals(str3))) {
      str1 = ap.a().Z(7) + str2;
    }
    return DTApplication.g().getApplicationContext().getString(a.m.conference_call_share_invite_attendees, new Object[] { paramString1, paramString2, ei.a(new Date(1000L * paramLong), false), paramString3, paramString4, str1 });
  }
  
  public static String getCNLinkUrlWithInviteKey(int paramInt)
  {
    String str2 = ap.a().ay();
    String str3 = ap.a().aa(paramInt);
    DTLog.i("DtUtil", "getCNLinkUrlWithInviteKey inviteKey = " + str2 + " inviteUrl " + str3);
    String str1 = str3 + str2;
    if ((str3 == null) || ("".equals(str3))) {
      str1 = ap.a().Z(paramInt) + str2;
    }
    DTLog.d("DtUtil", "getCNLinkUrlWithInviteKey inviteLink=" + str1);
    return str1;
  }
  
  public static String getCNLinkUrlWithoutInviteKey(int paramInt)
  {
    Object localObject2 = ap.a().aa(paramInt);
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"".equals(localObject2)) {}
    }
    else
    {
      localObject1 = ap.a().Z(paramInt);
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
    String str = getCNLinkUrlWithInviteKey(9);
    DTLog.d("DtUtil", "getShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.g().getApplicationContext().getString(a.m.share_usingdingtone_cnqq, new Object[] { str });
  }
  
  public static String getCNQQShareContentWithoutInviteKey()
  {
    String str1 = getCNLinkUrlWithoutInviteKey(9);
    DTLog.d("DtUtil", "getShareContentWithoutInviteKey invite url =" + str1);
    String str2 = by.d();
    DTLog.i("DtUtil", "getCNQQShareContentWithoutInviteKey rewardInviteCode=" + str2);
    return DTApplication.g().getApplicationContext().getString(a.m.share_usingdingtone_cnqq, new Object[] { str1 });
  }
  
  public static String getCNShareContentWithInviteKey()
  {
    String str = getCNLinkUrlWithInviteKey(4);
    DTLog.d("DtUtil", "getCNShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.g().getApplicationContext().getString(a.m.share_usingdingtone, new Object[] { str });
  }
  
  public static String getCNShareContentWithoutInviteKey()
  {
    String str1 = getCNLinkUrlWithoutInviteKey(4);
    DTLog.d("DtUtil", "getCNShareContentWithoutInviteKey invite url =" + str1);
    String str2 = by.d();
    DTLog.i("DtUtil", "getCNShareContentWithoutInviteKey rewardInviteCode=" + str2);
    return DTApplication.g().getApplicationContext().getString(a.m.share_usingdingtone, new Object[] { str1 });
  }
  
  public static String getCNWeChatShareContentWithInviteKey(boolean paramBoolean)
  {
    String str3 = getCNLinkUrlWithInviteKey(7);
    DTLog.d("DtUtil", "getShareContentWithInviteKey inviteKey=" + str3);
    String str2 = DTApplication.g().getApplicationContext().getString(a.m.share_usingdingtone_cnwechat, new Object[] { str3 });
    String str1 = str2;
    if (paramBoolean)
    {
      str1 = str2;
      if (1 == me.dingtone.app.im.manager.g.c().I())
      {
        str1 = by.d();
        DTLog.i("DtUtil", "getCNWeChatShareContentWithInviteKey rewardInviteCode=" + str1);
        str1 = DTApplication.g().getApplicationContext().getString(a.m.share_usingdingtone_cnwechat_with_code, new Object[] { str1, str3 });
      }
    }
    return str1;
  }
  
  public static String getCNWeChatShareContentWithoutInviteKey()
  {
    String str = getLinkUrlWithoutInviteKey(0);
    DTLog.d("DtUtil", "getShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.g().getApplicationContext().getString(a.m.share_usingdingtone_cnwechat, new Object[] { str });
  }
  
  /* Error */
  public static int getCallLogCount(Context paramContext)
  {
    // Byte code:
    //   0: ldc_w 964
    //   3: invokestatic 967	me/dingtone/app/im/util/p:a	(Ljava/lang/String;)Z
    //   6: ifeq +5 -> 11
    //   9: iconst_0
    //   10: ireturn
    //   11: aload_0
    //   12: invokevirtual 971	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   15: getstatic 977	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   18: aconst_null
    //   19: aconst_null
    //   20: aconst_null
    //   21: aconst_null
    //   22: invokevirtual 983	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   25: astore_0
    //   26: aload_0
    //   27: ifnull +61 -> 88
    //   30: aload_0
    //   31: invokeinterface 988 1 0
    //   36: istore_1
    //   37: aload_0
    //   38: invokeinterface 991 1 0
    //   43: iload_1
    //   44: ireturn
    //   45: astore_0
    //   46: iconst_0
    //   47: istore_1
    //   48: aload_0
    //   49: invokevirtual 166	java/lang/Exception:printStackTrace	()V
    //   52: ldc 33
    //   54: new 85	java/lang/StringBuilder
    //   57: dup
    //   58: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   61: ldc_w 993
    //   64: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: aload_0
    //   68: invokevirtual 169	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   71: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: invokestatic 102	me/dingtone/app/im/log/DTLog:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   80: pop
    //   81: goto -38 -> 43
    //   84: astore_0
    //   85: goto -37 -> 48
    //   88: iconst_0
    //   89: istore_1
    //   90: goto -47 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramContext	Context
    //   36	54	1	i	int
    // Exception table:
    //   from	to	target	type
    //   11	26	45	java/lang/Exception
    //   30	37	45	java/lang/Exception
    //   37	43	84	java/lang/Exception
  }
  
  public static String[] getCatalogForSideBar(bx paramBx)
  {
    int m = 0;
    HashMap localHashMap = new HashMap();
    int i = paramBx.getCount();
    if ((paramBx instanceof me.dingtone.app.im.adapter.a)) {
      i = ((me.dingtone.app.im.adapter.a)paramBx).b();
    }
    for (;;)
    {
      int j = i;
      if ((paramBx instanceof me.dingtone.app.im.adapter.y))
      {
        j = i;
        if (((me.dingtone.app.im.adapter.y)paramBx).a() != null) {
          j = i - 1;
        }
      }
      int k = 0;
      i = m;
      Object localObject2;
      Object localObject1;
      if (k < j)
      {
        localObject2 = paramBx.a(k);
        localObject1 = localObject2;
        if (((String)localObject2).equals(DTApplication.g().getResources().getString(a.m.catalog_name_favorites))) {
          localObject1 = "✩";
        }
        if ((!((String)localObject1).equals(DTApplication.g().getResources().getString(a.m.group))) && (!((String)localObject1).equals(DTApplication.g().getResources().getString(a.m.broadcast_list))) && (!((String)localObject1).equals("G20")))
        {
          localObject2 = localObject1;
          if (!((String)localObject1).equals(DTApplication.g().getResources().getString(a.m.conference_call_selected))) {}
        }
        else
        {
          localObject2 = "";
        }
        if ((localObject2 == null) || (((String)localObject2).isEmpty()) || (localHashMap.get(localObject2) != null)) {
          break label317;
        }
        localHashMap.put(localObject2, Integer.valueOf(i));
        i += 1;
      }
      label317:
      for (;;)
      {
        k += 1;
        break;
        paramBx = new String[localHashMap.size()];
        localObject1 = localHashMap.entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          paramBx[((Integer)localObject2.getValue()).intValue()] = ((String)((Map.Entry)localObject2).getKey());
        }
        Arrays.sort(paramBx);
        return paramBx;
      }
    }
  }
  
  public static String[] getCatalogForSideBarFromThreadLocal(bx paramBx)
  {
    int i = 0;
    HashMap localHashMap = new HashMap();
    int j = paramBx.getCount();
    if (((paramBx instanceof me.dingtone.app.im.adapter.y)) && (((me.dingtone.app.im.adapter.y)paramBx).a() != null)) {
      j -= 1;
    }
    for (;;)
    {
      int k = 0;
      Object localObject2;
      Object localObject1;
      if (k < j)
      {
        localObject2 = paramBx.b(k);
        localObject1 = localObject2;
        if (((String)localObject2).equals(DTApplication.g().getResources().getString(a.m.catalog_name_favorites))) {
          localObject1 = "✩";
        }
        if ((!((String)localObject1).equals(DTApplication.g().getResources().getString(a.m.group))) && (!((String)localObject1).equals(DTApplication.g().getResources().getString(a.m.broadcast_list))) && (!((String)localObject1).equals("G20")))
        {
          localObject2 = localObject1;
          if (!((String)localObject1).equals(DTApplication.g().getResources().getString(a.m.conference_call_selected))) {}
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
        paramBx = new String[localHashMap.size()];
        localObject1 = localHashMap.entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          paramBx[((Integer)localObject2.getValue()).intValue()] = ((String)((Map.Entry)localObject2).getKey());
        }
        Arrays.sort(paramBx);
        return paramBx;
      }
    }
  }
  
  public static String getConferenceNotifyContentWithInviteKey(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4, int paramInt)
  {
    String str1 = ap.a().ay();
    String str2 = ap.a().Z(paramInt);
    str1 = str2 + str1;
    return DTApplication.g().getApplicationContext().getString(a.m.conference_call_share_invite_attendees, new Object[] { paramString1, paramString2, ei.a(new Date(1000L * paramLong), false), paramString3, paramString4, str1 });
  }
  
  public static int getContactsCount(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
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
      DTLog.i("DtUtil", "permission_errgetContactsCount:" + paramContext.getMessage());
      return i;
    }
  }
  
  public static String getCountryCode()
  {
    Object localObject2 = "";
    if (("" == null) || ("".equals(""))) {
      localObject2 = dk.n();
    }
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"".equals(localObject2)) {}
    }
    else
    {
      localObject1 = getLocalISOCountryCode();
    }
    localObject2 = localObject1;
    if (org.apache.commons.lang.d.a((String)localObject1)) {
      localObject2 = ap.a().cC();
    }
    return localObject2;
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
      String[] arrayOfString = aw.b;
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
      if ((ap.a().aU() > 0) && (!"".equals(ap.a().aX()))) {
        return ap.a().aU();
      }
      if (ap.a().cB() != 0) {
        return (short)ap.a().cB();
      }
      String str2 = dk.g();
      String str1 = "";
      Object localObject = str1;
      if (str2 != null)
      {
        localObject = str1;
        if (!str2.isEmpty()) {
          localObject = du.c(str2.toUpperCase());
        }
      }
      if ((localObject != null) && (!((String)localObject).isEmpty()))
      {
        DTLog.d("DtUtil", "getCountryCode from sim = " + (String)localObject);
        return Short.valueOf((String)localObject).shortValue();
      }
      localObject = b.a().e();
      if (((ArrayList)localObject).size() == 1)
      {
        localObject = du.c((String)((ArrayList)localObject).get(0));
        if (localObject != null) {
          return Integer.valueOf((String)localObject).intValue();
        }
      }
      localObject = ap.a().bx();
      if (!"".equals(localObject))
      {
        localObject = du.c(((String)localObject).toUpperCase());
        if (localObject != null) {
          return Short.parseShort((String)localObject);
        }
      }
      localObject = DTApplication.g().getResources().getConfiguration().locale.getCountry();
      if ((localObject != null) && (!((String)localObject).isEmpty()))
      {
        localObject = du.c(((String)localObject).toUpperCase());
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
  
  public static String getCountryIsoIfVpnConnected()
  {
    Object localObject2 = "";
    if ("" != null)
    {
      localObject1 = localObject2;
      if (!"".equals("")) {}
    }
    else if ("" != null)
    {
      localObject1 = localObject2;
      if (!"".equals("")) {}
    }
    else
    {
      localObject1 = getLocalISOCountryCode();
    }
    Object localObject3;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!"".equals(localObject1)) {}
    }
    else
    {
      localObject3 = ap.a().aX();
      localObject2 = localObject1;
      if (localObject3 != null)
      {
        localObject2 = localObject1;
        if (!((String)localObject3).isEmpty())
        {
          DTLog.i("DtUtil", "getISOCode...mainCountryCode :" + ap.a().aU());
          localObject2 = du.d(getCountryCodeByPhoneNumber((String)localObject3));
        }
      }
    }
    if (localObject2 != null)
    {
      localObject3 = localObject2;
      if (!"".equals(localObject2)) {}
    }
    else
    {
      localObject3 = dk.n();
    }
    Object localObject1 = localObject3;
    if (TextUtils.isEmpty((CharSequence)localObject3))
    {
      localObject1 = localObject3;
      if (!"".equals(ap.a().cC()))
      {
        localObject2 = ap.a().cC();
        localObject1 = localObject2;
        if ("-".equals(localObject2)) {
          localObject1 = "";
        }
      }
    }
    DTLog.d("DtUtil", "getAdCountryIso from login ip code :" + (String)localObject1);
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (!"".equals(localObject1)) {}
    }
    else
    {
      int i = ap.a().cB();
      localObject2 = localObject1;
      if (i > 0) {
        localObject2 = du.d(i + "");
      }
    }
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!"".equals(localObject2)) {}
    }
    else
    {
      if (!"".equals(ap.a().bx())) {
        localObject2 = ap.a().bx();
      }
      localObject1 = localObject2;
      if ("-".equals(localObject2)) {
        localObject1 = "";
      }
      DTLog.d("DtUtil", "getAdCountryIso iso code from ping " + (String)localObject1);
    }
    return localObject1;
  }
  
  public static String getCreditCardPhotoAESIV(String paramString1, String paramString2)
  {
    if ((org.apache.commons.lang.d.a(paramString1)) || (org.apache.commons.lang.d.a(paramString2))) {
      return null;
    }
    paramString1 = paramString1 + "dingtone!123" + paramString2;
    try
    {
      paramString1 = get32MD5Str(paramString1);
      return paramString1;
    }
    catch (NoSuchAlgorithmException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static String getCreditCardPhotoAESKey(String paramString1, String paramString2)
  {
    if ((org.apache.commons.lang.d.a(paramString1)) || (org.apache.commons.lang.d.a(paramString2))) {
      return null;
    }
    paramString1 = "dingtone@123" + paramString1 + paramString2;
    try
    {
      paramString1 = get32MD5Str(paramString1);
      return paramString1;
    }
    catch (NoSuchAlgorithmException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
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
    String str = dk.q();
    ap.a().F(str);
    paramContext = String.format("%s_%s_%s", new Object[] { do.a(paramContext), Build.MANUFACTURER, Build.MODEL });
    str = String.format("%s_%s", new Object[] { Build.MANUFACTURER, Build.MODEL });
    ap.a().M(str);
    ap.a().L(paramContext);
    str = Build.VERSION.RELEASE;
    paramContext = str;
    if (str.length() > 15) {
      paramContext = str.substring(0, 15);
    }
    ap.a().O(paramContext);
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
  
  private static long getDurationDay(long paramLong)
  {
    long l1 = 0L;
    long l2 = System.currentTimeMillis() - paramLong;
    paramLong = l1;
    if (l2 > 0L) {
      paramLong = l2 / 86400000L;
    }
    return paramLong;
  }
  
  public static String getEncrypt(String paramString1, String paramString2)
  {
    return nativeGetEncrypt(paramString1, paramString2);
  }
  
  public static String getFormatedPhoneNumber(String paramString)
  {
    String str;
    if ((paramString == null) || ("".equals(paramString))) {
      str = "";
    }
    do
    {
      do
      {
        return str;
        if ((DTSystemContext.getCountryCode() != 1) || (paramString.length() < 4)) {
          break;
        }
        str = paramString;
      } while (paramString.length() <= 6);
      if ((paramString.length() >= 5) && (paramString.length() <= 7) && (paramString.startsWith("1"))) {
        return paramString.substring(1);
      }
      str = paramString;
    } while (paramString.length() < 7);
    if ("99999999999".equals(paramString)) {
      return DTApplication.g().getString(a.m.unknown_phone_number);
    }
    return getFormatedPrivatePhoneNumber(paramString);
  }
  
  public static String getFormatedPrivatePhoneNumber(String paramString)
  {
    String str1;
    if ((paramString == null) || ("".equals(paramString))) {
      str1 = "";
    }
    String str3;
    do
    {
      do
      {
        return str1;
        str3 = getCountryCodeByPhoneNumber(paramString);
        str1 = paramString;
      } while (str3 == null);
      str1 = paramString;
    } while (str3.isEmpty());
    if (DTSystemContext.getCountryCode() == Short.parseShort(str3)) {}
    for (String str2 = "";; str2 = "+" + str3 + " ")
    {
      if (!str3.equals("1")) {
        break label163;
      }
      str1 = paramString;
      if (paramString.length() < 11) {
        break;
      }
      return str2 + "(" + paramString.substring(1, 4) + ") " + paramString.substring(4, 7) + "-" + paramString.substring(7);
    }
    label163:
    return str2 + paramString.substring(str3.length());
  }
  
  public static AdvertisingIdClient.Info getGADInfo()
  {
    boolean bool = ct.a();
    DTLog.d("DtUtil", "Violation Terms, getAdvertisingIdInfo isAgree: " + bool);
    if (!bool) {
      return null;
    }
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(DTApplication.g().getApplicationContext());
      return localInfo;
    }
    catch (Throwable localThrowable)
    {
      DTLog.e("DtUtil", "getGADInfo occured exception e =  " + localThrowable.getMessage());
    }
    return null;
  }
  
  public static void getGADInfoAndPingTime()
  {
    y.a().a(new DtUtil.2());
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
    Object localObject1 = getPkgInfoList();
    ArrayList localArrayList = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    label143:
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
      String str = ((PackageInfo)localObject2).packageName;
      int j = paramArrayOfString.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label143;
        }
        if (paramArrayOfString[i].equals(str))
        {
          localObject2 = ((PackageInfo)localObject2).applicationInfo.loadLabel(DTApplication.g().getPackageManager()).toString();
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
    Iterator localIterator = getPkgInfoList().iterator();
    label83:
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      int j = paramArrayOfString.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label83;
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
    Iterator localIterator = getPkgInfoList().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((PackageInfo)localIterator.next()).packageName);
    }
    return localArrayList;
  }
  
  public static String getInviteUrlByLanguage(int paramInt)
  {
    String str1 = ap.a().Z(paramInt);
    String str2 = ap.a().aa(paramInt);
    Locale localLocale = ei.a();
    DTLog.d("DtUtil", "getInviteUrlByLanguage is " + localLocale.getLanguage());
    if ((localLocale.getLanguage().endsWith("zh")) && (str2 != null) && (!"".equals(str2))) {
      return str2;
    }
    return str1;
  }
  
  public static String getLinkUrlWithInviteKey(int paramInt)
  {
    String str1 = ap.a().ay();
    String str2 = ap.a().Z(paramInt);
    str1 = str2 + str1;
    DTLog.d("DtUtil", "getInviteKey inviteKey=" + str1);
    return str1;
  }
  
  public static String getLinkUrlWithoutInviteKey(int paramInt)
  {
    String str2 = ap.a().Z(paramInt);
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
    return ap.a().cW() * 1000 + System.currentTimeMillis();
  }
  
  public static String getLocaleInfo()
  {
    try
    {
      String str1 = DTApplication.g().getApplicationContext().getResources().getConfiguration().locale.getLanguage();
      String str2 = Locale.getDefault().getCountry();
      str1 = str1.toLowerCase() + "_" + str2;
      return str1;
    }
    catch (Exception localException) {}
    return "en_US";
  }
  
  public static String getLotteryWinShareKey(int paramInt1, int paramInt2)
  {
    String str = getLinkUrlWithoutInviteKey(paramInt2);
    return DTApplication.g().getApplicationContext().getString(a.m.lottery_won_super_cool, new Object[] { "" + paramInt1, str });
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
  
  private static List<PackageInfo> getPkgInfoList()
  {
    if ((pkgInfoList == null) || (pkgInfoList.size() == 0)) {
      pkgInfoList = DTApplication.g().getPackageManager().getInstalledPackages(0);
    }
    return pkgInfoList;
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
          paramString = me.dingtone.app.im.group.e.a().c(l);
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
  
  public static String getRealCountryIso()
  {
    if (!checkVPNConnectionByNetworkInterface()) {
      return getAdCountryIso();
    }
    return getCountryIsoIfVpnConnected();
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
    return 0;
  }
  
  public static String getShareContentWithInviteKey()
  {
    String str = getLinkUrlWithInviteKey(12);
    DTLog.d("DtUtil", "getShareContentWithInviteKey inviteKey=" + str);
    return DTApplication.g().getApplicationContext().getString(a.m.share_usingdingtone, new Object[] { str });
  }
  
  public static String getShareContentWithoutInviteKey()
  {
    String str1 = getLinkUrlWithoutInviteKey(12);
    DTLog.d("DtUtil", "getShareContentWithoutInviteKey invite url =" + str1);
    String str2 = by.d();
    DTLog.i("DtUtil", "getShareContentWithoutInviteKey rewardInviteCode=" + str2);
    return DTApplication.g().getApplicationContext().getString(a.m.share_usingdingtone, new Object[] { str1 });
  }
  
  public static String getSharePostContent(int paramInt, boolean paramBoolean)
  {
    String str2 = getLinkUrlWithInviteKey(paramInt) + " ";
    Object localObject = DTApplication.g().getResources().getStringArray(a.b.invite_fb_content);
    switch (paramInt)
    {
    }
    for (;;)
    {
      String str1 = String.format(localObject[new Random().nextInt(localObject.length)], new Object[] { ap.a().aN() });
      localObject = str1;
      if (paramBoolean)
      {
        localObject = str1;
        if (1 == me.dingtone.app.im.manager.g.c().I())
        {
          localObject = by.d();
          localObject = DTApplication.g().getResources().getString(a.m.invite_code_suffix, new Object[] { localObject });
          localObject = str1 + ((String)localObject).substring(1, ((String)localObject).length());
        }
      }
      localObject = (String)localObject + " " + str2 + " ";
      DTLog.d("DtUtil", "getSharePostContent: " + (String)localObject);
      return localObject;
      localObject = DTApplication.g().getResources().getStringArray(a.b.invite_content_for_phone);
      continue;
      localObject = DTApplication.g().getResources().getStringArray(a.b.invite_content_for_phone_and_message);
    }
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
    if (paramArrayList == null) {
      localObject = "";
    }
    Iterator localIterator;
    do
    {
      return localObject;
      localIterator = paramArrayList.iterator();
      paramArrayList = null;
      localObject = paramArrayList;
    } while (!localIterator.hasNext());
    Object localObject = ee.e((Long)localIterator.next());
    if (paramArrayList == null) {}
    for (paramArrayList = (ArrayList<Long>)localObject;; paramArrayList = paramArrayList + paramString + (String)localObject) {
      break;
    }
  }
  
  public static String getTitleOfSubUsersOfGroupWithSplit(ArrayList<String> paramArrayList, String paramString, long paramLong)
  {
    Iterator localIterator = paramArrayList.iterator();
    paramArrayList = null;
    String str1 = "";
    while (localIterator.hasNext())
    {
      Object localObject = (String)localIterator.next();
      if (((String)localObject).equals(ap.a().aM()))
      {
        str1 = DTApplication.g().getString(a.m.you);
      }
      else
      {
        String str2 = ee.d(Long.valueOf((String)localObject));
        localObject = str2;
        if ("".equals(str2)) {
          localObject = DTApplication.g().getString(a.m.unknown);
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
  
  public static String getTwitterPostContent()
  {
    int i = 0;
    String str3 = dj.b(Arrays.asList(DTApplication.g().getApplicationContext().getResources().getStringArray(a.b.share_usingdingtone_twitter)));
    String str4 = ap.a().Z(6);
    DTLog.i("DtUtil", "getTwitterPostContent inviteKey=" + str4);
    ArrayList localArrayList = new ArrayList();
    String str2 = "";
    AppInviteConfig localAppInviteConfig = me.dingtone.app.im.manager.g.c().M();
    String str1 = str2;
    int j;
    if (localAppInviteConfig != null)
    {
      str1 = str2;
      if (dj.a(localAppInviteConfig.twitterHashTag) > 0)
      {
        int k = dj.a(localAppInviteConfig.twitterHashTag);
        if (k >= 2)
        {
          j = new Random().nextInt(k);
          str1 = (String)localAppInviteConfig.twitterHashTag.get(j);
          localArrayList.add(str1);
          j += 1;
          if (j < k) {
            break label489;
          }
        }
      }
    }
    for (;;)
    {
      str2 = (String)localAppInviteConfig.twitterHashTag.get(i);
      localArrayList.add(str2);
      DTLog.d("DtUtil", "getTwitterPostContent tag list:" + dj.c(localArrayList));
      if ((!org.apache.commons.lang.d.a(str3)) && (!org.apache.commons.lang.d.a(str4)))
      {
        i = str3.length() + 1 + str4.length() + 1;
        if (str1.length() + i + 1 + str2.length() + 1 < 140) {
          str1 = str3 + " " + str1 + " " + str2 + " " + str4 + " ";
        }
      }
      for (;;)
      {
        DTLog.i("DtUtil", "getTwitterPostContent shareContent=" + str1);
        return str1;
        str1 = (String)localAppInviteConfig.twitterHashTag.get(0);
        localArrayList.add(str1);
        str2 = "";
        break;
        if (str1.length() + i + 1 < 140)
        {
          str1 = str3 + " " + str1 + " " + str4 + " ";
        }
        else
        {
          str1 = str3 + " " + str4 + " ";
          continue;
          str1 = "";
        }
      }
      label489:
      i = j;
    }
  }
  
  public static String getUserAgent(Context paramContext)
  {
    String str = (String)bz.b(DTApplication.g().getApplicationContext(), "sp_user_agent_file", "sp_user_agent_key", "");
    long l = ((Long)bz.b(DTApplication.g().getApplicationContext(), "sp_user_agent_file", "sp_user_agent_save_time", Long.valueOf(0L))).longValue();
    DTLog.d("DtUtil", "userAgent=" + str);
    DTLog.d("DtUtil", "getDurationDay(saveTime)=" + getDurationDay(l));
    if ((TextUtils.isEmpty(str)) || (getDurationDay(l) > 7L)) {
      try
      {
        DTLog.d("DtUtil", "reget the userAgent");
        if (Build.VERSION.SDK_INT >= 17)
        {
          paramContext = WebSettings.getDefaultUserAgent(paramContext);
          bz.a(DTApplication.g().getApplicationContext(), "sp_user_agent_file", "sp_user_agent_key", paramContext);
          bz.a(DTApplication.g().getApplicationContext(), "sp_user_agent_file", "sp_user_agent_save_time", Long.valueOf(System.currentTimeMillis()));
          return paramContext;
        }
        paramContext = new WebView(paramContext);
        str = paramContext.getSettings().getUserAgentString();
        paramContext.removeAllViews();
        paramContext.destroy();
        bz.a(DTApplication.g().getApplicationContext(), "sp_user_agent_file", "sp_user_agent_key", str);
        bz.a(DTApplication.g().getApplicationContext(), "sp_user_agent_file", "sp_user_agent_save_time", Long.valueOf(System.currentTimeMillis()));
        return str;
      }
      catch (Throwable paramContext)
      {
        return "";
      }
    }
    DTLog.d("DtUtil", "use the cache userAgent");
    return str;
  }
  
  public static String getVPNCountryIso()
  {
    if (!checkVPNConnectionByNetworkInterface())
    {
      DTLog.i("DtUtil", "VPN is not connected ,use getAdCountryIsoVPN");
      return getAdCountryIsoVPN();
    }
    DTLog.i("DtUtil", "VPN is connected ,use getCountryIsoIfVpnConnected");
    return getCountryIsoIfVpnConnected();
  }
  
  public static String getWebHost(String paramString)
  {
    String str1;
    if (paramString.contains(me.dingtone.app.im.u.a.aW)) {
      str1 = me.dingtone.app.im.u.a.aW;
    }
    String str2;
    for (;;)
    {
      DTLog.i("DtUtil", "getWebHost url  " + paramString);
      DTLog.i("DtUtil", "getWebHost key  " + str1);
      str2 = me.dingtone.app.im.manager.g.c().z(str1);
      if (!org.apache.commons.lang.d.a(str2)) {
        break label164;
      }
      DTLog.i("DtUtil", "getWebHost serverUrl is empty ");
      return "";
      if (paramString.contains(me.dingtone.app.im.u.a.aY))
      {
        str1 = me.dingtone.app.im.u.a.aY;
      }
      else if (paramString.contains(me.dingtone.app.im.u.a.ba))
      {
        str1 = me.dingtone.app.im.u.a.ba;
      }
      else if (paramString.contains(me.dingtone.app.im.u.a.aX))
      {
        str1 = me.dingtone.app.im.u.a.aX;
      }
      else
      {
        if (!paramString.contains(me.dingtone.app.im.u.a.aZ)) {
          break;
        }
        str1 = me.dingtone.app.im.u.a.aZ;
      }
    }
    return "";
    label164:
    paramString = paramString.replace(str1, str2);
    DTLog.i("DtUtil", "getWebHost serverUrl  " + str2);
    DTLog.i("DtUtil", "getWebHost replaceUrl  " + paramString);
    me.dingtone.app.im.tracker.d.a().b("VPNTipV2", "web_host_replace", null, 0L);
    return paramString;
  }
  
  /* Error */
  public static int getWeekCallLogCount(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: ldc_w 964
    //   5: invokestatic 967	me/dingtone/app/im/util/p:a	(Ljava/lang/String;)Z
    //   8: ifeq +5 -> 13
    //   11: iconst_0
    //   12: ireturn
    //   13: invokestatic 253	java/lang/System:currentTimeMillis	()J
    //   16: lstore_3
    //   17: getstatic 45	me/dingtone/app/im/util/DtUtil:WeekMillis	Ljava/lang/Long;
    //   20: invokevirtual 1423	java/lang/Long:longValue	()J
    //   23: lstore 5
    //   25: aload_0
    //   26: invokevirtual 971	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   29: getstatic 977	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   32: aconst_null
    //   33: ldc_w 1688
    //   36: iconst_1
    //   37: anewarray 210	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: lload_3
    //   43: lload 5
    //   45: lsub
    //   46: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   49: invokestatic 1691	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   52: aastore
    //   53: aconst_null
    //   54: invokevirtual 983	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   57: astore_0
    //   58: aload_0
    //   59: ifnull +93 -> 152
    //   62: aload_0
    //   63: invokeinterface 988 1 0
    //   68: istore_2
    //   69: iload_2
    //   70: istore_1
    //   71: aload_0
    //   72: invokeinterface 991 1 0
    //   77: iload_1
    //   78: ireturn
    //   79: astore_0
    //   80: aconst_null
    //   81: astore 7
    //   83: aload 7
    //   85: ifnull +10 -> 95
    //   88: aload 7
    //   90: invokeinterface 991 1 0
    //   95: ldc 33
    //   97: new 85	java/lang/StringBuilder
    //   100: dup
    //   101: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   104: ldc_w 1693
    //   107: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: aload_0
    //   111: invokevirtual 169	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   114: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: invokestatic 102	me/dingtone/app/im/log/DTLog:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: iload_1
    //   125: ireturn
    //   126: astore 8
    //   128: aload_0
    //   129: astore 7
    //   131: aload 8
    //   133: astore_0
    //   134: goto -51 -> 83
    //   137: astore 7
    //   139: aload_0
    //   140: astore 8
    //   142: aload 7
    //   144: astore_0
    //   145: aload 8
    //   147: astore 7
    //   149: goto -66 -> 83
    //   152: iconst_0
    //   153: istore_1
    //   154: goto -77 -> 77
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	paramContext	Context
    //   1	153	1	i	int
    //   68	2	2	j	int
    //   16	27	3	l1	long
    //   23	21	5	l2	long
    //   81	49	7	localContext1	Context
    //   137	6	7	localException1	Exception
    //   147	1	7	localObject	Object
    //   126	6	8	localException2	Exception
    //   140	6	8	localContext2	Context
    // Exception table:
    //   from	to	target	type
    //   13	58	79	java/lang/Exception
    //   62	69	126	java/lang/Exception
    //   71	77	137	java/lang/Exception
  }
  
  /* Error */
  public static int getWeekSMSCount(Context paramContext)
  {
    // Byte code:
    //   0: ldc_w 1696
    //   3: invokestatic 967	me/dingtone/app/im/util/p:a	(Ljava/lang/String;)Z
    //   6: ifeq +7 -> 13
    //   9: iconst_0
    //   10: istore_1
    //   11: iload_1
    //   12: ireturn
    //   13: ldc_w 1698
    //   16: ldc -10
    //   18: invokestatic 102	me/dingtone/app/im/log/DTLog:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   21: pop
    //   22: ldc_w 1700
    //   25: invokestatic 76	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   28: astore 7
    //   30: invokestatic 253	java/lang/System:currentTimeMillis	()J
    //   33: lstore_3
    //   34: getstatic 45	me/dingtone/app/im/util/DtUtil:WeekMillis	Ljava/lang/Long;
    //   37: invokevirtual 1423	java/lang/Long:longValue	()J
    //   40: lstore 5
    //   42: aload_0
    //   43: invokevirtual 971	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   46: aload 7
    //   48: aconst_null
    //   49: ldc_w 1688
    //   52: iconst_1
    //   53: anewarray 210	java/lang/String
    //   56: dup
    //   57: iconst_0
    //   58: lload_3
    //   59: lload 5
    //   61: lsub
    //   62: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   65: invokestatic 1691	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   68: aastore
    //   69: aconst_null
    //   70: invokevirtual 983	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   73: astore 7
    //   75: aload 7
    //   77: astore_0
    //   78: aload 7
    //   80: invokeinterface 988 1 0
    //   85: istore_2
    //   86: iload_2
    //   87: istore_1
    //   88: aload 7
    //   90: ifnull -79 -> 11
    //   93: aload 7
    //   95: invokeinterface 991 1 0
    //   100: iload_2
    //   101: ireturn
    //   102: astore 8
    //   104: aconst_null
    //   105: astore 7
    //   107: aload 7
    //   109: astore_0
    //   110: ldc 33
    //   112: new 85	java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   119: ldc_w 1702
    //   122: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload 8
    //   127: invokevirtual 169	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   130: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: invokestatic 102	me/dingtone/app/im/log/DTLog:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   139: pop
    //   140: aload 7
    //   142: ifnull +10 -> 152
    //   145: aload 7
    //   147: invokeinterface 991 1 0
    //   152: iconst_0
    //   153: ireturn
    //   154: astore_0
    //   155: aconst_null
    //   156: astore_0
    //   157: aload_0
    //   158: ifnull +9 -> 167
    //   161: aload_0
    //   162: invokeinterface 991 1 0
    //   167: iconst_0
    //   168: ireturn
    //   169: astore 7
    //   171: goto -14 -> 157
    //   174: astore 8
    //   176: goto -69 -> 107
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	paramContext	Context
    //   10	78	1	i	int
    //   85	16	2	j	int
    //   33	26	3	l1	long
    //   40	20	5	l2	long
    //   28	118	7	localObject1	Object
    //   169	1	7	localObject2	Object
    //   102	24	8	localException1	Exception
    //   174	1	8	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   30	75	102	java/lang/Exception
    //   30	75	154	finally
    //   78	86	169	finally
    //   110	140	169	finally
    //   78	86	174	java/lang/Exception
  }
  
  public static String getWhatsAppShareContentWithInviteKey(boolean paramBoolean)
  {
    String str2 = getLinkUrlWithInviteKey(10) + " ";
    DTLog.d("DtUtil", "getWhatsAppShareContentWithInviteKey inviteKey=" + str2);
    Object localObject = new int[9];
    localObject[0] = a.m.twitter_share_templete_1;
    localObject[1] = a.m.twitter_share_templete_2;
    localObject[2] = a.m.twitter_share_templete_3;
    localObject[3] = a.m.twitter_share_templete_4;
    localObject[4] = a.m.twitter_share_templete_5;
    localObject[5] = a.m.twitter_share_templete_6;
    localObject[6] = a.m.twitter_share_templete_7;
    localObject[7] = a.m.twitter_share_templete_8;
    localObject[8] = a.m.twitter_share_templete_9;
    int i = new Random().nextInt(localObject.length);
    String str1 = DTApplication.g().getApplicationContext().getString(a.m.app_name);
    str1 = DTApplication.g().getApplicationContext().getString(localObject[i], new Object[] { str1, str2 });
    localObject = str1;
    if (paramBoolean)
    {
      localObject = str1;
      if (1 == me.dingtone.app.im.manager.g.c().I())
      {
        localObject = by.d();
        DTLog.i("DtUtil", "getWhatsAppShareContentWithInviteKey rewardInviteCode=" + (String)localObject);
        localObject = DTApplication.g().getApplicationContext().getString(a.m.invite_whatsapp_content_with_code, new Object[] { str2, localObject });
      }
    }
    return localObject;
  }
  
  public static void gotoAppStore(Activity paramActivity)
  {
    if (paramActivity == null) {
      return;
    }
    try
    {
      if (f.d() == f.a)
      {
        gotoAppStore(paramActivity, Uri.parse(me.dingtone.app.im.u.a.ag));
        return;
      }
    }
    catch (Exception paramActivity)
    {
      DTLog.e("DtUtil", org.apache.commons.lang.exception.a.h(paramActivity));
      return;
    }
    String str = f.c();
    if ((str == null) || (str.isEmpty()))
    {
      gotoAppStore(paramActivity, Uri.parse(me.dingtone.app.im.u.a.ag));
      return;
    }
    gotoAppStore(paramActivity, Uri.parse(f.c()));
  }
  
  public static void gotoAppStore(Activity paramActivity, Uri paramUri)
  {
    paramUri = new Intent("android.intent.action.VIEW", paramUri);
    List localList = paramActivity.getPackageManager().queryIntentActivities(paramUri, 32);
    int i = 0;
    for (;;)
    {
      if (i < localList.size())
      {
        if (((ResolveInfo)localList.get(i)).activityInfo.packageName.equals("com.android.vending"))
        {
          paramUri.setClassName(((ResolveInfo)localList.get(i)).activityInfo.packageName, ((ResolveInfo)localList.get(i)).activityInfo.name);
          paramActivity.startActivity(paramUri);
        }
      }
      else {
        return;
      }
      if (i == localList.size() - 1)
      {
        paramUri.setClassName(((ResolveInfo)localList.get(0)).activityInfo.packageName, ((ResolveInfo)localList.get(0)).activityInfo.name);
        paramActivity.startActivity(paramUri);
      }
      i += 1;
    }
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
        paramContext.startActivity(bh.a(paramContext, localFile, localIntent, "application/vnd.android.package-archive"));
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      DTLog.i("tag", paramContext.getMessage());
      return;
    }
    ap.a().g(-1L);
    Download(paramContext, ap.a().H(), paramString);
  }
  
  public static boolean isActivityChatInBackground()
  {
    return sActivityChatInBackground;
  }
  
  public static boolean isActivityChatInStack()
  {
    return sActivityChatInStack;
  }
  
  public static boolean isCallerIdForCallingChinaDisabled()
  {
    return (getCountryCodeByPhoneNumber(ap.a().ai()).equals("86")) && (!ap.a().ah());
  }
  
  public static boolean isCallerIdForCallingChinaEnabled()
  {
    return (getCountryCodeByPhoneNumber(ap.a().ai()).equals("86")) && (ap.a().ah());
  }
  
  public static boolean isCalleridPhoneNumberAnonymous()
  {
    boolean bool2 = false;
    String str = ap.a().ai();
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
    return DTApplication.g().n() instanceof MessageChatActivity;
  }
  
  public static boolean isCurrentBindActivity()
  {
    return ((DTApplication.g().n() instanceof LinkSecondPhoneActivity)) || ((DTApplication.g().n() instanceof LinkEmailAddressActivity));
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
    if (paramContext == null) {}
    while ((paramString == null) || (paramString.isEmpty())) {
      return false;
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (Throwable paramString) {}
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
        g.a("isPureDigtial exception e = " + org.apache.commons.lang.exception.a.h(paramString), false);
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
          paramContext = localContentResolver.query((Uri)localObject, null, "title=?", new String[] { paramContext.getResources().getString(a.m.app_name) }, null);
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
    if (paramContext == null) {}
    while (dk.o() != 5) {
      return false;
    }
    return true;
  }
  
  public static boolean isSmallScreen()
  {
    int i = (int)(au.a / au.c);
    int j = (int)(au.b / au.c);
    return (i < 320) || (j < 480);
  }
  
  public static boolean isUAEuser()
  {
    return isUAEuser(false);
  }
  
  public static boolean isUAEuser(boolean paramBoolean)
  {
    try
    {
      if ((ap.a().cB() != 0) && (ap.a().cB() == 971)) {
        return true;
      }
      if (!paramBoolean)
      {
        localObject = b.a().e();
        if (((ArrayList)localObject).size() == 1)
        {
          localObject = (String)((ArrayList)localObject).get(0);
          if ((localObject != null) && ("AE".equalsIgnoreCase((String)localObject))) {
            return true;
          }
        }
      }
      if ((ap.a().aU() > 0) && (!"".equals(ap.a().aX())) && (ap.a().aU() == 971)) {
        return true;
      }
      Object localObject = dk.n();
      if ((localObject != null) && (!((String)localObject).isEmpty()) && ("AE".equalsIgnoreCase((String)localObject))) {
        return true;
      }
      localObject = ap.a().bx();
      if ((localObject != null) && (!"".equals(localObject)) && ("AE".equalsIgnoreCase((String)localObject))) {
        return true;
      }
      localObject = TimeZone.getDefault().getID();
      if ((localObject != null) && (org.apache.commons.lang.d.e((String)localObject, "Asia/Dubai"))) {
        return true;
      }
      localObject = DTApplication.g().getResources().getConfiguration().locale.getCountry();
      if ((localObject != null) && (!((String)localObject).isEmpty()) && ("AE".equalsIgnoreCase((String)localObject))) {
        return true;
      }
      paramBoolean = "AE".equals(DTSystemContext.getISOLanguageCode());
      if (paramBoolean) {
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
    int i = ap.a().cB();
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
          str = ap.a().bx();
          if (TextUtils.isEmpty(str)) {
            break;
          }
        } while (str.equalsIgnoreCase("US"));
        return false;
        str = dk.g();
        if (str == null) {
          break;
        }
      } while ("US".equalsIgnoreCase(str));
      return false;
      if ((ap.a().aU() <= 0) || ("".equals(ap.a().aX()))) {
        break;
      }
    } while (ap.a().aU() == 1);
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
    int j = 1;
    localEditor = cs.b();
    try
    {
      if ((ap.a().aX() != null) && (!"".equals(ap.a().aX())))
      {
        localObject = getCountryCodeByPhoneNumber(ap.a().aX());
        localEditor.putString((String)localObject, (String)localObject);
      }
      if ((ap.a().bw() != null) && (!"".equals(ap.a().bw())))
      {
        localObject = getCountryCodeByPhoneNumber(ap.a().bw());
        localEditor.putString((String)localObject, (String)localObject);
      }
      String str2 = dk.n();
      String str1 = "";
      Object localObject = str1;
      if (str2 != null)
      {
        localObject = str1;
        if (!str2.isEmpty()) {
          localObject = du.c(str2.toUpperCase());
        }
      }
      if ((localObject != null) && (!((String)localObject).isEmpty()))
      {
        DTLog.d("DtUtil", "getCountryCode from sim = " + (String)localObject);
        localEditor.putString((String)localObject, (String)localObject);
      }
      if (ap.a().cB() != 0) {
        localEditor.putString(String.valueOf(ap.a().cB()), String.valueOf(ap.a().cB()));
      }
      str1 = ap.a().bx();
      if ((!"".equals(str1)) && (du.c(str1.toUpperCase()) != null)) {
        localEditor.putString((String)localObject, (String)localObject);
      }
      localObject = DTApplication.g().getResources().getConfiguration().locale.getCountry();
      int i = j;
      if (localObject != null)
      {
        i = j;
        if (!((String)localObject).isEmpty())
        {
          localObject = du.c(((String)localObject).toUpperCase());
          i = j;
          if (localObject != null) {
            i = Short.parseShort((String)localObject);
          }
        }
      }
      localEditor.putString(String.valueOf(i), String.valueOf(i));
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localEditor.putString("86", "86");
      }
    }
    localEditor.commit();
  }
  
  public static void printProcessInfo()
  {
    ActivityManager localActivityManager = (ActivityManager)DTApplication.g().getSystemService("activity");
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
  
  public static void setActivityChatInBackground(boolean paramBoolean)
  {
    sActivityChatInBackground = paramBoolean;
  }
  
  public static void setActivityChatInStack(boolean paramBoolean)
  {
    sActivityChatInStack = paramBoolean;
  }
  
  public static void showDownloadDialog(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (!DTApplication.g().m().f()) {
      return;
    }
    q.a(paramContext, paramString2, paramString1, null, paramContext.getResources().getString(a.m.update_dialog_btn_download), new DtUtil.1(paramString3, paramContext));
  }
  
  public static void startAppService(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    paramContext.startService(localIntent);
  }
  
  public static void suspendPrivateNumberWhenNotBind(Context paramContext)
  {
    long l1;
    String str;
    int i;
    PrivatePhoneItemOfMine localPrivatePhoneItemOfMine;
    long l2;
    if ((ap.a().w() == l.c) && (AppConnectionManager.a().d().booleanValue()) && (er.g() == 2))
    {
      l1 = ap.a().cW() * 1000;
      long l3 = System.currentTimeMillis() + l1;
      DTLog.i("DtUtil", "suspendPrivateNumberWhenNotBind current:" + ei.e(l3));
      Object localObject = n.a().k();
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
            break label452;
          }
          if (l2 >= 86400000L)
          {
            localPrivatePhoneItemOfMine = (PrivatePhoneItemOfMine)localPrivatePhoneItemOfMine.clone();
            localPrivatePhoneItemOfMine.suspendFlag = true;
            localPrivatePhoneItemOfMine.primaryFlag = false;
            DTLog.d("DtUtil", "suspendPrivateNumberWhenNotBind suspend privateNumber:" + localPrivatePhoneItemOfMine.getPhoneNumber() + " gainTime:" + ei.e(localPrivatePhoneItemOfMine.getGainTime()));
            me.dingtone.app.im.privatephone.k.a().a(localPrivatePhoneItemOfMine.getPhoneNumber(), true);
            me.dingtone.app.im.privatephone.k.a().b(localPrivatePhoneItemOfMine.getPhoneNumber(), true);
            me.dingtone.app.im.privatephone.e.a(localPrivatePhoneItemOfMine.getPhoneNumber(), true);
            n.a().m(localPrivatePhoneItemOfMine);
          }
        }
      }
    }
    label452:
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
            DTLog.i("DtUtil", "suspendPrivateNumberWhenNotBind  createAlarm privatePhoneNumber:" + str + " alarTime:" + ei.e(l1));
            me.dingtone.app.im.alarm.e.a().a(paramContext, l1, str);
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
      Object localObject = n.a().k();
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
            me.dingtone.app.im.privatephone.k.a().b(localPrivatePhoneItemOfMine2.phoneNumber, false);
            me.dingtone.app.im.privatephone.k.a().a(localPrivatePhoneItemOfMine2.phoneNumber, false);
            me.dingtone.app.im.privatephone.e.a(localPrivatePhoneItemOfMine2.getPhoneNumber(), false);
            DTLog.d("DtUtil", "unSuspendAllPrivateNumberWhenBinded privateNumber:" + localPrivatePhoneItemOfMine1.getPhoneNumber());
            n.a().m(localPrivatePhoneItemOfMine2);
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
      localPrivatePhoneItemOfMine = n.a().c(paramString);
      DTLog.i("DtUtil", "updateTheCountOfPrivateNumberSMSMessage prvateNumber:" + paramString + " privateItem:" + localPrivatePhoneItemOfMine);
    } while (localPrivatePhoneItemOfMine == null);
    me.dingtone.app.im.database.k.a().i(paramString);
  }
}
