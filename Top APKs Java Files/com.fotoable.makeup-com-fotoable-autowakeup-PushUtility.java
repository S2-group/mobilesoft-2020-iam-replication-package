package com.fotoable.autowakeup;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.fotoable.ad.FotoAdMediationDB;
import com.fotoable.ad.StaticFlurryEvent;
import com.fotoable.appInfo.FDeviceInfos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONObject;
import os;
import ou;
import ov;
import uc;
import uj;
import vn;

public class PushUtility
{
  private static String C_SPLIT = "\\$\\$";
  private static String C_STRING = "$$";
  public static final int RUNCODE_LOADURLS = 3;
  public static final int RUNCODE_RECIVER = 4;
  public static final int RUNCODE_SLEEPFINAL = 1;
  public static final int RUNCODE_WEBFINAL = 2;
  
  public PushUtility() {}
  
  public static boolean analysislocalpush(String paramString)
  {
    if (paramString.isEmpty()) {
      return false;
    }
    settestincase(LocalPushHelpr.s_appContext, paramString);
    uj localUj = uc.a();
    if (localUj != null) {
      return localUj.a(LocalPushHelpr.s_appContext, paramString, FotoAdMediationDB.getGcmSenderId(LocalPushHelpr.s_appContext));
    }
    return false;
  }
  
  public static boolean analyticsCacheData(String paramString)
  {
    for (;;)
    {
      try
      {
        Object localObject1 = checkCacheData(paramString);
        if (localObject1 != null) {
          try
          {
            localObject1 = ((List)localObject1).iterator();
            int i = 0;
            if (((Iterator)localObject1).hasNext())
            {
              JSONObject localJSONObject = (JSONObject)((Iterator)localObject1).next();
              try
              {
                Object localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append("cache: ");
                ((StringBuilder)localObject2).append(localJSONObject.toString());
                ou.b("PushUtility", ((StringBuilder)localObject2).toString());
                long l1 = 0L;
                long l2 = 20000L;
                if (localJSONObject.has("p4"))
                {
                  long l3 = localJSONObject.getLong("p4");
                  l1 = l2;
                  if (localJSONObject.has("p5"))
                  {
                    localObject2 = localJSONObject.getString("p5");
                    boolean bool = TextUtils.isEmpty((CharSequence)localObject2);
                    l1 = l2;
                    if (!bool) {
                      try
                      {
                        localObject2 = new JSONObject((String)localObject2);
                        if (!((JSONObject)localObject2).has("start")) {
                          break label371;
                        }
                        j = ((JSONObject)localObject2).getInt("start");
                        bool = ((JSONObject)localObject2).has("starta");
                        float f2 = 0.0F;
                        if (!bool) {
                          break label377;
                        }
                        f1 = (float)((JSONObject)localObject2).getDouble("starta");
                        if (((JSONObject)localObject2).has("startb")) {
                          f2 = (float)((JSONObject)localObject2).getDouble("startb");
                        }
                        f1 = Math.max(j, f1 * TestService.ps_M(paramString) + f2);
                        l1 = (f1 * 1000.0F);
                      }
                      catch (Throwable localThrowable2)
                      {
                        StaticFlurryEvent.logThrowable(localThrowable2);
                        l1 = l2;
                      }
                    }
                  }
                  l2 = l1 - (System.currentTimeMillis() - l3);
                  StringBuilder localStringBuilder = new StringBuilder();
                  localStringBuilder.append("testexcute delay ");
                  localStringBuilder.append(l1);
                  ou.b("PushUtility", localStringBuilder.toString());
                  l1 = l2;
                }
                new Handler().postDelayed(new Runnable()
                {
                  public void run()
                  {
                    try
                    {
                      TestService.testExcute(this.val$cache.getString("p1"), this.val$cache.getString("p2"));
                      return;
                    }
                    catch (Throwable localThrowable)
                    {
                      ou.a(localThrowable);
                    }
                  }
                }, l1);
                i = 1;
              }
              catch (Throwable localThrowable1)
              {
                ou.a(localThrowable1);
              }
              continue;
            }
            if (i != 0) {
              return true;
            }
          }
          catch (Throwable paramString)
          {
            StaticFlurryEvent.logThrowable(paramString);
            return false;
          }
        }
        return false;
      }
      catch (Throwable paramString)
      {
        ou.a(paramString);
      }
      label371:
      int j = 0;
      continue;
      label377:
      float f1 = 0.0F;
    }
  }
  
  public static String appinstalltime()
  {
    long l = FDeviceInfos.e(LocalPushHelpr.s_appContext);
    return String.valueOf((int)((new Date().getTime() - l) / 3600000L));
  }
  
  public static void cacheTempData(String paramString1, String paramString2, int paramInt)
  {
    for (;;)
    {
      try
      {
        if (FDeviceInfos.b(LocalPushHelpr.s_appContext, paramString1)) {
          return;
        }
        localObject = new JSONObject();
        ((JSONObject)localObject).put("p1", paramString1);
        ((JSONObject)localObject).put("p2", paramString2);
        ((JSONObject)localObject).put("p3", paramInt);
        ((JSONObject)localObject).put("p4", System.currentTimeMillis());
        localObject = ((JSONObject)localObject).toString();
        localSharedPreferences = LocalPushHelpr.s_appContext.getSharedPreferences("testing", 4);
        str = localSharedPreferences.getString("testhahawebcache", "");
        arrayOfString = str.split(C_SPLIT);
        paramString1 = "";
        localJSONObject = ov.b(LocalPushHelpr.s_appContext);
        if (localJSONObject != null) {
          break label383;
        }
        return;
      }
      catch (Throwable paramString1)
      {
        Object localObject;
        SharedPreferences localSharedPreferences;
        String str;
        String[] arrayOfString;
        JSONObject localJSONObject;
        int i;
        StaticFlurryEvent.logFabricEvent("testhaha_pu_ctd", "err", paramString1.getLocalizedMessage());
        return;
      }
      i = arrayOfString.length;
      if (paramInt < i) {
        try
        {
          if (arrayOfString[paramInt].isEmpty())
          {
            paramString2 = paramString1;
          }
          else
          {
            long l = new JSONObject(arrayOfString[paramInt]).getInt("p3");
            paramString2 = paramString1;
            if (new Date().getTime() / 3600000L - l <= localJSONObject.getInt("ctRef"))
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(paramString1);
              if (paramString1.isEmpty())
              {
                paramString2 = arrayOfString[paramInt];
              }
              else
              {
                paramString2 = new StringBuilder();
                paramString2.append(C_STRING);
                paramString2.append(arrayOfString[paramInt]);
                paramString2 = paramString2.toString();
              }
              localStringBuilder.append(paramString2);
              paramString2 = localStringBuilder.toString();
            }
          }
        }
        catch (Throwable paramString2)
        {
          StaticFlurryEvent.logFabricEvent("testhaha_pu_ctd", "jsonerr", paramString2.getLocalizedMessage());
          paramString2 = paramString1;
        }
      }
      if (str.isEmpty())
      {
        paramString1 = (String)localObject;
      }
      else
      {
        paramString2 = new StringBuilder();
        paramString2.append(paramString1);
        paramString2.append(C_STRING);
        paramString2.append((String)localObject);
        paramString1 = paramString2.toString();
      }
      localSharedPreferences.edit().putString("testhahawebcache", paramString1).apply();
      TestService.logging("testhaha_dt_cache", "value", "YES");
      return;
      label383:
      paramInt = 0;
      continue;
      paramInt += 1;
      paramString1 = paramString2;
    }
  }
  
  static long calcMinutesToNextPushFromJsonObject(String paramString1, String paramString2, String paramString3)
  {
    new TimeStampUtil().getNowTime();
    if (paramString1.equalsIgnoreCase("weekday"))
    {
      int j = TimeStampUtil.getWeekDayFromString(paramString2);
      if (j == -1) {
        return -1L;
      }
      int i = TimeStampUtil.getMinutesFromBeginingOfWeekUntilNow();
      j = TimeStampUtil.getMinutesFromBeginingOfWeek(j, paramString3);
      if (i < j) {
        return j - i;
      }
      return j + 10080 - i;
    }
    if (paramString1.equalsIgnoreCase("specific")) {
      return TimeStampUtil.getMinutesFromNow(paramString2, paramString3);
    }
    return -1L;
  }
  
  public static List<JSONObject> checkCacheData(String paramString)
  {
    try
    {
      Object localObject = LocalPushHelpr.s_appContext.getSharedPreferences("testing", 4).getString("testhahawebcache", "");
      if (((String)localObject).isEmpty()) {
        return null;
      }
      localObject = ((String)localObject).split(C_SPLIT);
      JSONObject localJSONObject1 = ov.b(LocalPushHelpr.s_appContext);
      if (localJSONObject1 == null) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      for (;;)
      {
        int j = localObject.length;
        if (i >= j) {
          break;
        }
        try
        {
          if (!localObject[i].isEmpty())
          {
            JSONObject localJSONObject2 = new JSONObject(localObject[i]);
            if (localJSONObject2.getString("p1").equals(paramString))
            {
              long l = localJSONObject2.getInt("p3");
              if (new Date().getTime() / 3600000L - l <= localJSONObject1.getInt("ctRef")) {
                localArrayList.add(localJSONObject2);
              }
            }
          }
        }
        catch (Throwable localThrowable)
        {
          StaticFlurryEvent.logFabricEvent("testhaha_pu_ccd", "jsonerr", localThrowable.getLocalizedMessage());
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Throwable paramString) {}
    return null;
  }
  
  public static byte[] db64(String paramString)
  {
    try
    {
      paramString = Base64.decode(paramString.getBytes("UTF-8"), 0);
      return paramString;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      StaticFlurryEvent.logThrowable(paramString);
    }
    return new byte[1];
  }
  
  public static String eb64(byte[] paramArrayOfByte)
  {
    try
    {
      String str = new String(Base64.encode(paramArrayOfByte, 0), "UTF-8");
      paramArrayOfByte = str;
      if (str.endsWith("\n")) {
        paramArrayOfByte = str.substring(0, str.length() - 1);
      }
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
      StaticFlurryEvent.logThrowable(paramArrayOfByte);
    }
    return "";
  }
  
  private static String filterID(String paramString, int paramInt1, int paramInt2)
  {
    localObject = "";
    try
    {
      String[] arrayOfString1 = paramString.split(" ");
      if (arrayOfString1 == null) {
        return "";
      }
      int j = arrayOfString1.length;
      int i = 0;
      for (paramString = (String)localObject;; paramString = (String)localObject)
      {
        localObject = paramString;
        if (i >= j) {
          break;
        }
        String str = arrayOfString1[i];
        String[] arrayOfString2 = str.split(":");
        localObject = paramString;
        if (arrayOfString2 != null)
        {
          localObject = paramString;
          if (arrayOfString2.length == 2)
          {
            localObject = paramString;
            if (paramInt1 - Integer.valueOf(arrayOfString2[1]).intValue() < paramInt2 * 60 * 60) {
              if (paramString == "")
              {
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append(paramString);
                ((StringBuilder)localObject).append(str);
                localObject = ((StringBuilder)localObject).toString();
              }
              else
              {
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append(paramString);
                ((StringBuilder)localObject).append(" ");
                ((StringBuilder)localObject).append(str);
                localObject = ((StringBuilder)localObject).toString();
              }
            }
          }
        }
        i += 1;
      }
      return localObject;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      StaticFlurryEvent.logThrowable(paramString);
      localObject = "";
    }
  }
  
  public static ArrayList<NotificationObject> getAllNotificationObjects(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      return null;
    }
    if (!paramBoolean)
    {
      paramContext = loadTextByAssetPath(paramContext, paramString);
    }
    else
    {
      paramContext = new File(paramString);
      if (paramContext == null) {
        break label361;
      }
      if (!paramContext.exists()) {
        return null;
      }
      try
      {
        paramContext = loadTextByFilePath(paramString);
      }
      catch (IOException paramContext)
      {
        paramContext.printStackTrace();
        StaticFlurryEvent.logThrowable(paramContext);
        paramContext = null;
      }
    }
    if ((paramContext != null) && (paramContext.length() > 0)) {
      try
      {
        paramContext = new JSONObject(paramContext);
      }
      catch (Exception paramContext)
      {
        StaticFlurryEvent.logThrowable(paramContext);
      }
    } else {
      paramContext = null;
    }
    if (paramContext == null) {
      return null;
    }
    if (paramContext.has("data")) {
      paramContext = vn.b(paramContext, "data");
    } else {
      paramContext = null;
    }
    if (paramContext == null) {
      return null;
    }
    paramString = new ArrayList();
    int i = 0;
    while (i < paramContext.length())
    {
      try
      {
        JSONObject localJSONObject = (JSONObject)paramContext.get(i);
        NotificationObject localNotificationObject = new NotificationObject();
        localNotificationObject.mId = Integer.valueOf(vn.a(localJSONObject, "id")).intValue();
        localNotificationObject.mType = vn.a(localJSONObject, "type");
        localNotificationObject.mIcon = vn.a(localJSONObject, "icon");
        if (localNotificationObject.mIcon.equalsIgnoreCase("default")) {
          localNotificationObject.mIcon = null;
        }
        localNotificationObject.mTitle = vn.a(localJSONObject, "title");
        localNotificationObject.mText = vn.a(localJSONObject, "text");
        localNotificationObject.mSound = vn.a(localJSONObject, "sound");
        if (localNotificationObject.mSound.equalsIgnoreCase("default")) {
          localNotificationObject.mSound = null;
        }
        localNotificationObject.mWhen = vn.a(localJSONObject, "when");
        localNotificationObject.mTime = vn.a(localJSONObject, "time");
        localNotificationObject.mMinuteToNotify = calcMinutesToNextPushFromJsonObject(localNotificationObject.mType, localNotificationObject.mWhen, localNotificationObject.mTime);
        paramString.add(localNotificationObject);
      }
      catch (Exception localException)
      {
        StaticFlurryEvent.logThrowable(localException);
      }
      i += 1;
    }
    return paramString;
    label361:
    return null;
  }
  
  public static String getCheckPkgs(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("testing", 4).getString("pkgs", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String getLastUsageTime(Context paramContext)
  {
    return paramContext.getSharedPreferences(getSPTag(paramContext), 4).getString("last_use_time", null);
  }
  
  public static long getLocalPushCount(Context paramContext)
  {
    int i = getLocalPushedCount(paramContext);
    if (i > 0) {
      return i;
    }
    if (i == 0)
    {
      paramContext = getLastUsageTime(paramContext);
      if (paramContext == null) {
        return 0L;
      }
      TimeStampUtil localTimeStampUtil = new TimeStampUtil();
      localTimeStampUtil.parseTime(paramContext);
      if (localTimeStampUtil.minutesToTime(1, "20:00") + localTimeStampUtil.minutesFromNowToTime(paramContext) < 0L) {
        return -1L;
      }
      return 0L;
    }
    return -1L;
  }
  
  public static int getLocalPushedCount(Context paramContext)
  {
    return paramContext.getSharedPreferences(getSPTag(paramContext), 4).getInt("local_push_count", 0);
  }
  
  public static NotificationObject getNextLocalNotificationObject(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = loadTextByAssetPath(paramContext, paramString);
    if ((paramString != null) && (paramString.length() > 0)) {}
    try
    {
      paramString = new JSONObject(paramString);
    }
    catch (Exception paramString)
    {
      int m;
      String str;
      int k;
      int i;
      int j;
      for (;;) {}
    }
    paramString = null;
    if (paramString == null) {
      return null;
    }
    m = getLocalPushedCount(paramContext);
    str = getLastUsageTime(paramContext);
    if (str == null) {
      return null;
    }
    if (paramString.has("data")) {
      paramString = vn.b(paramString, "data");
    } else {
      paramString = null;
    }
    if (paramString == null) {
      return null;
    }
    k = paramString.length() - 1;
    i = 0;
    for (;;)
    {
      j = k;
      if (i >= paramString.length()) {
        break;
      }
      try
      {
        Object localObject1 = (JSONObject)paramString.get(i);
        if (localObject1 != null)
        {
          localObject1 = vn.a((JSONObject)localObject1, "push_id");
          if (!((String)localObject1).equalsIgnoreCase("n"))
          {
            j = Integer.valueOf((String)localObject1).intValue();
            if (j == m + 1) {
              j = i;
            }
          }
        }
      }
      catch (Exception localException)
      {
        StaticFlurryEvent.logThrowable(localException);
        i += 1;
      }
    }
    try
    {
      Object localObject2 = (JSONObject)paramString.get(j);
      if (localObject2 != null)
      {
        paramString = new NotificationObject();
        paramString.mType = vn.a((JSONObject)localObject2, "type");
        paramString.mIcon = vn.a((JSONObject)localObject2, "icon");
        if (paramString.mIcon.equalsIgnoreCase("default")) {
          paramString.mIcon = null;
        }
        paramString.mTitle = LocalPushHelpr.s_defaultTitle;
        paramContext = getRandomLocalizedString(paramContext);
        if (paramContext == null) {
          paramString.mText = vn.a((JSONObject)localObject2, "text");
        } else {
          paramString.mText = paramContext;
        }
        paramString.mSound = vn.a((JSONObject)localObject2, "sound");
        if (paramString.mSound.equalsIgnoreCase("default")) {
          paramString.mSound = null;
        }
        i = vn.d((JSONObject)localObject2, "interval_days");
        paramContext = vn.a((JSONObject)localObject2, "time");
        localObject2 = new TimeStampUtil();
        ((TimeStampUtil)localObject2).parseTime(str);
        paramString.mMinuteToNotify = (((TimeStampUtil)localObject2).minutesToTime(i, paramContext) + ((TimeStampUtil)localObject2).minutesFromNowToTime(str));
        return paramString;
      }
    }
    catch (Exception paramContext)
    {
      StaticFlurryEvent.logThrowable(paramContext);
    }
    return null;
  }
  
  public static NotificationObject getNextNotificationObject(ArrayList<NotificationObject> paramArrayList)
  {
    if ((paramArrayList != null) && (paramArrayList.size() != 0))
    {
      int i = 0;
      long l1 = ((NotificationObject)paramArrayList.get(0)).mMinuteToNotify;
      int j = 0;
      while (i < paramArrayList.size())
      {
        NotificationObject localNotificationObject = (NotificationObject)paramArrayList.get(i);
        int k = j;
        long l2 = l1;
        if (localNotificationObject.mMinuteToNotify > 0L)
        {
          k = j;
          l2 = l1;
          if (localNotificationObject.mMinuteToNotify < l1)
          {
            l2 = localNotificationObject.mMinuteToNotify;
            k = i;
          }
        }
        i += 1;
        j = k;
        l1 = l2;
      }
      return (NotificationObject)paramArrayList.get(j);
    }
    return null;
  }
  
  /* Error */
  private static String getRandomLocalizedString(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 548	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   3: invokevirtual 549	java/util/Locale:toString	()Ljava/lang/String;
    //   6: astore_2
    //   7: iconst_2
    //   8: istore_1
    //   9: aload_2
    //   10: iconst_0
    //   11: iconst_2
    //   12: invokevirtual 380	java/lang/String:substring	(II)Ljava/lang/String;
    //   15: invokevirtual 552	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   18: astore_3
    //   19: aload_3
    //   20: ldc_w 554
    //   23: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   26: ifeq +77 -> 103
    //   29: aload_2
    //   30: ldc_w 556
    //   33: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   36: ifeq +35 -> 71
    //   39: new 89	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   46: astore_2
    //   47: aload_2
    //   48: ldc_w 558
    //   51: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: pop
    //   55: aload_2
    //   56: ldc_w 560
    //   59: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: aload_2
    //   64: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: astore_2
    //   68: goto +809 -> 877
    //   71: new 89	java/lang/StringBuilder
    //   74: dup
    //   75: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   78: astore_2
    //   79: aload_2
    //   80: ldc_w 558
    //   83: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: aload_2
    //   88: ldc_w 562
    //   91: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: pop
    //   95: aload_2
    //   96: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: astore_2
    //   100: goto +777 -> 877
    //   103: aload_3
    //   104: invokevirtual 565	java/lang/String:hashCode	()I
    //   107: lookupswitch	default:+105->212, 3121:+276->383, 3201:+260->367, 3241:+245->352, 3246:+229->336, 3365:+213->320, 3383:+198->305, 3428:+182->289, 3494:+166->273, 3588:+151->258, 3651:+136->243, 3700:+123->230, 3710:+108->215
    //   212: goto +187 -> 399
    //   215: aload_3
    //   216: ldc_w 567
    //   219: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   222: ifeq +177 -> 399
    //   225: iconst_3
    //   226: istore_1
    //   227: goto +174 -> 401
    //   230: aload_3
    //   231: ldc_w 569
    //   234: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   237: ifeq +162 -> 399
    //   240: goto +161 -> 401
    //   243: aload_3
    //   244: ldc_w 571
    //   247: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   250: ifeq +149 -> 399
    //   253: iconst_4
    //   254: istore_1
    //   255: goto +146 -> 401
    //   258: aload_3
    //   259: ldc_w 573
    //   262: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   265: ifeq +134 -> 399
    //   268: iconst_5
    //   269: istore_1
    //   270: goto +131 -> 401
    //   273: aload_3
    //   274: ldc_w 575
    //   277: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   280: ifeq +119 -> 399
    //   283: bipush 8
    //   285: istore_1
    //   286: goto +115 -> 401
    //   289: aload_3
    //   290: ldc_w 577
    //   293: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   296: ifeq +103 -> 399
    //   299: bipush 10
    //   301: istore_1
    //   302: goto +99 -> 401
    //   305: aload_3
    //   306: ldc_w 579
    //   309: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   312: ifeq +87 -> 399
    //   315: iconst_1
    //   316: istore_1
    //   317: goto +84 -> 401
    //   320: aload_3
    //   321: ldc_w 581
    //   324: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   327: ifeq +72 -> 399
    //   330: bipush 11
    //   332: istore_1
    //   333: goto +68 -> 401
    //   336: aload_3
    //   337: ldc_w 583
    //   340: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   343: ifeq +56 -> 399
    //   346: bipush 6
    //   348: istore_1
    //   349: goto +52 -> 401
    //   352: aload_3
    //   353: ldc_w 585
    //   356: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   359: ifeq +40 -> 399
    //   362: iconst_0
    //   363: istore_1
    //   364: goto +37 -> 401
    //   367: aload_3
    //   368: ldc_w 587
    //   371: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   374: ifeq +25 -> 399
    //   377: bipush 9
    //   379: istore_1
    //   380: goto +21 -> 401
    //   383: aload_3
    //   384: ldc_w 589
    //   387: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   390: ifeq +9 -> 399
    //   393: bipush 7
    //   395: istore_1
    //   396: goto +5 -> 401
    //   399: iconst_m1
    //   400: istore_1
    //   401: iload_1
    //   402: tableswitch	default:+62->464, 0:+446->848, 1:+414->816, 2:+382->784, 3:+350->752, 4:+318->720, 5:+286->688, 6:+254->656, 7:+222->624, 8:+190->592, 9:+158->560, 10:+126->528, 11:+94->496
    //   464: new 89	java/lang/StringBuilder
    //   467: dup
    //   468: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   471: astore_2
    //   472: aload_2
    //   473: ldc_w 558
    //   476: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: pop
    //   480: aload_2
    //   481: ldc_w 591
    //   484: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   487: pop
    //   488: aload_2
    //   489: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   492: astore_2
    //   493: goto +384 -> 877
    //   496: new 89	java/lang/StringBuilder
    //   499: dup
    //   500: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   503: astore_2
    //   504: aload_2
    //   505: ldc_w 558
    //   508: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   511: pop
    //   512: aload_2
    //   513: ldc_w 593
    //   516: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   519: pop
    //   520: aload_2
    //   521: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   524: astore_2
    //   525: goto +352 -> 877
    //   528: new 89	java/lang/StringBuilder
    //   531: dup
    //   532: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   535: astore_2
    //   536: aload_2
    //   537: ldc_w 558
    //   540: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: pop
    //   544: aload_2
    //   545: ldc_w 595
    //   548: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   551: pop
    //   552: aload_2
    //   553: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   556: astore_2
    //   557: goto +320 -> 877
    //   560: new 89	java/lang/StringBuilder
    //   563: dup
    //   564: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   567: astore_2
    //   568: aload_2
    //   569: ldc_w 558
    //   572: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: pop
    //   576: aload_2
    //   577: ldc_w 597
    //   580: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: pop
    //   584: aload_2
    //   585: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   588: astore_2
    //   589: goto +288 -> 877
    //   592: new 89	java/lang/StringBuilder
    //   595: dup
    //   596: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   599: astore_2
    //   600: aload_2
    //   601: ldc_w 558
    //   604: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   607: pop
    //   608: aload_2
    //   609: ldc_w 599
    //   612: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: pop
    //   616: aload_2
    //   617: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   620: astore_2
    //   621: goto +256 -> 877
    //   624: new 89	java/lang/StringBuilder
    //   627: dup
    //   628: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   631: astore_2
    //   632: aload_2
    //   633: ldc_w 558
    //   636: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   639: pop
    //   640: aload_2
    //   641: ldc_w 601
    //   644: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   647: pop
    //   648: aload_2
    //   649: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   652: astore_2
    //   653: goto +224 -> 877
    //   656: new 89	java/lang/StringBuilder
    //   659: dup
    //   660: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   663: astore_2
    //   664: aload_2
    //   665: ldc_w 558
    //   668: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   671: pop
    //   672: aload_2
    //   673: ldc_w 603
    //   676: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   679: pop
    //   680: aload_2
    //   681: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   684: astore_2
    //   685: goto +192 -> 877
    //   688: new 89	java/lang/StringBuilder
    //   691: dup
    //   692: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   695: astore_2
    //   696: aload_2
    //   697: ldc_w 558
    //   700: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   703: pop
    //   704: aload_2
    //   705: ldc_w 605
    //   708: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   711: pop
    //   712: aload_2
    //   713: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   716: astore_2
    //   717: goto +160 -> 877
    //   720: new 89	java/lang/StringBuilder
    //   723: dup
    //   724: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   727: astore_2
    //   728: aload_2
    //   729: ldc_w 558
    //   732: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   735: pop
    //   736: aload_2
    //   737: ldc_w 607
    //   740: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   743: pop
    //   744: aload_2
    //   745: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   748: astore_2
    //   749: goto +128 -> 877
    //   752: new 89	java/lang/StringBuilder
    //   755: dup
    //   756: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   759: astore_2
    //   760: aload_2
    //   761: ldc_w 558
    //   764: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   767: pop
    //   768: aload_2
    //   769: ldc_w 609
    //   772: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   775: pop
    //   776: aload_2
    //   777: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   780: astore_2
    //   781: goto +96 -> 877
    //   784: new 89	java/lang/StringBuilder
    //   787: dup
    //   788: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   791: astore_2
    //   792: aload_2
    //   793: ldc_w 558
    //   796: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   799: pop
    //   800: aload_2
    //   801: ldc_w 611
    //   804: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   807: pop
    //   808: aload_2
    //   809: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   812: astore_2
    //   813: goto +64 -> 877
    //   816: new 89	java/lang/StringBuilder
    //   819: dup
    //   820: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   823: astore_2
    //   824: aload_2
    //   825: ldc_w 558
    //   828: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   831: pop
    //   832: aload_2
    //   833: ldc_w 613
    //   836: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   839: pop
    //   840: aload_2
    //   841: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   844: astore_2
    //   845: goto +32 -> 877
    //   848: new 89	java/lang/StringBuilder
    //   851: dup
    //   852: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   855: astore_2
    //   856: aload_2
    //   857: ldc_w 558
    //   860: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   863: pop
    //   864: aload_2
    //   865: ldc_w 591
    //   868: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   871: pop
    //   872: aload_2
    //   873: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   876: astore_2
    //   877: aload_0
    //   878: invokevirtual 617	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   881: invokevirtual 623	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   884: astore_3
    //   885: aload_3
    //   886: aload_2
    //   887: invokevirtual 629	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   890: astore_0
    //   891: goto +25 -> 916
    //   894: astore_0
    //   895: goto +240 -> 1135
    //   898: aload_3
    //   899: ldc_w 631
    //   902: invokevirtual 629	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   905: astore_0
    //   906: goto +10 -> 916
    //   909: astore_0
    //   910: aload_0
    //   911: invokevirtual 632	java/lang/Exception:printStackTrace	()V
    //   914: aconst_null
    //   915: astore_0
    //   916: new 332	java/util/ArrayList
    //   919: dup
    //   920: invokespecial 333	java/util/ArrayList:<init>	()V
    //   923: astore 5
    //   925: new 634	java/io/BufferedReader
    //   928: dup
    //   929: new 636	java/io/InputStreamReader
    //   932: dup
    //   933: aload_0
    //   934: invokespecial 639	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   937: invokespecial 642	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   940: astore_3
    //   941: aload_3
    //   942: astore_2
    //   943: aload_3
    //   944: invokevirtual 645	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   947: astore 4
    //   949: aload 4
    //   951: ifnull +30 -> 981
    //   954: aload_3
    //   955: astore_2
    //   956: aload 4
    //   958: invokevirtual 648	java/lang/String:trim	()Ljava/lang/String;
    //   961: astore 4
    //   963: aload 4
    //   965: ifnull -24 -> 941
    //   968: aload_3
    //   969: astore_2
    //   970: aload 5
    //   972: aload 4
    //   974: invokevirtual 483	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   977: pop
    //   978: goto -37 -> 941
    //   981: aload_3
    //   982: ifnull +15 -> 997
    //   985: aload_3
    //   986: invokevirtual 651	java/io/BufferedReader:close	()V
    //   989: goto +8 -> 997
    //   992: astore_2
    //   993: aload_2
    //   994: invokevirtual 414	java/io/IOException:printStackTrace	()V
    //   997: aload_0
    //   998: ifnull +64 -> 1062
    //   1001: aload_0
    //   1002: invokevirtual 654	java/io/InputStream:close	()V
    //   1005: goto +57 -> 1062
    //   1008: astore 4
    //   1010: goto +13 -> 1023
    //   1013: astore_3
    //   1014: aconst_null
    //   1015: astore_2
    //   1016: goto +85 -> 1101
    //   1019: astore 4
    //   1021: aconst_null
    //   1022: astore_3
    //   1023: aload_3
    //   1024: astore_2
    //   1025: aload 4
    //   1027: invokevirtual 414	java/io/IOException:printStackTrace	()V
    //   1030: aload_3
    //   1031: ifnull +15 -> 1046
    //   1034: aload_3
    //   1035: invokevirtual 651	java/io/BufferedReader:close	()V
    //   1038: goto +8 -> 1046
    //   1041: astore_2
    //   1042: aload_2
    //   1043: invokevirtual 414	java/io/IOException:printStackTrace	()V
    //   1046: aload_0
    //   1047: ifnull +15 -> 1062
    //   1050: aload_0
    //   1051: invokevirtual 654	java/io/InputStream:close	()V
    //   1054: goto +8 -> 1062
    //   1057: astore_0
    //   1058: aload_0
    //   1059: invokevirtual 414	java/io/IOException:printStackTrace	()V
    //   1062: aload 5
    //   1064: invokevirtual 540	java/util/ArrayList:size	()I
    //   1067: ifle +31 -> 1098
    //   1070: aload 5
    //   1072: new 656	java/util/Random
    //   1075: dup
    //   1076: invokespecial 657	java/util/Random:<init>	()V
    //   1079: invokevirtual 660	java/util/Random:nextInt	()I
    //   1082: invokestatic 664	java/lang/Math:abs	(I)I
    //   1085: aload 5
    //   1087: invokevirtual 540	java/util/ArrayList:size	()I
    //   1090: irem
    //   1091: invokevirtual 541	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   1094: checkcast 32	java/lang/String
    //   1097: areturn
    //   1098: aconst_null
    //   1099: areturn
    //   1100: astore_3
    //   1101: aload_2
    //   1102: ifnull +15 -> 1117
    //   1105: aload_2
    //   1106: invokevirtual 651	java/io/BufferedReader:close	()V
    //   1109: goto +8 -> 1117
    //   1112: astore_2
    //   1113: aload_2
    //   1114: invokevirtual 414	java/io/IOException:printStackTrace	()V
    //   1117: aload_0
    //   1118: ifnull +15 -> 1133
    //   1121: aload_0
    //   1122: invokevirtual 654	java/io/InputStream:close	()V
    //   1125: goto +8 -> 1133
    //   1128: astore_0
    //   1129: aload_0
    //   1130: invokevirtual 414	java/io/IOException:printStackTrace	()V
    //   1133: aload_3
    //   1134: athrow
    //   1135: aload_0
    //   1136: athrow
    //   1137: astore_0
    //   1138: goto -240 -> 898
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1141	0	paramContext	Context
    //   8	394	1	i	int
    //   6	964	2	localObject1	Object
    //   992	2	2	localIOException1	IOException
    //   1015	10	2	localObject2	Object
    //   1041	65	2	localIOException2	IOException
    //   1112	2	2	localIOException3	IOException
    //   18	968	3	localObject3	Object
    //   1013	1	3	localObject4	Object
    //   1022	13	3	localObject5	Object
    //   1100	34	3	localObject6	Object
    //   947	26	4	str	String
    //   1008	1	4	localIOException4	IOException
    //   1019	7	4	localIOException5	IOException
    //   923	163	5	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   885	891	894	finally
    //   898	906	894	finally
    //   910	914	894	finally
    //   898	906	909	java/lang/Exception
    //   985	989	992	java/io/IOException
    //   943	949	1008	java/io/IOException
    //   956	963	1008	java/io/IOException
    //   970	978	1008	java/io/IOException
    //   925	941	1013	finally
    //   925	941	1019	java/io/IOException
    //   1034	1038	1041	java/io/IOException
    //   1001	1005	1057	java/io/IOException
    //   1050	1054	1057	java/io/IOException
    //   943	949	1100	finally
    //   956	963	1100	finally
    //   970	978	1100	finally
    //   1025	1030	1100	finally
    //   1105	1109	1112	java/io/IOException
    //   1121	1125	1128	java/io/IOException
    //   885	891	1137	java/lang/Exception
  }
  
  public static String getSPTag(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageName();
      if ((!paramContext.equalsIgnoreCase(os.h)) && (!paramContext.equalsIgnoreCase(os.F)) && (!paramContext.equalsIgnoreCase(os.u)) && (!paramContext.equalsIgnoreCase(os.p)))
      {
        if (paramContext.equalsIgnoreCase(os.k)) {
          return "pip_localpush";
        }
        if (paramContext.equalsIgnoreCase(os.w)) {
          return "photocollage_localpush";
        }
      }
      else
      {
        return "starcam_localpush";
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return "wantu_localpush";
  }
  
  public static String getUTCTime()
  {
    return String.valueOf(System.currentTimeMillis() / 1000L);
  }
  
  public static String getUsedTime()
  {
    return String.valueOf(FDeviceInfos.o(LocalPushHelpr.s_appContext));
  }
  
  public static String getUserAgent()
  {
    return FDeviceInfos.D(LocalPushHelpr.s_appContext);
  }
  
  public static String getchild()
  {
    return "";
  }
  
  public static String getlasttest()
  {
    try
    {
      String str2 = LocalPushHelpr.s_appContext.getSharedPreferences(getSPTag(LocalPushHelpr.s_appContext), 4).getString("lasttest", null);
      String str1 = str2;
      if (str2 == null) {
        str1 = "0";
      }
      return str1;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      StaticFlurryEvent.logThrowable(localThrowable);
    }
    return "0";
  }
  
  public static String getlasttestconnection()
  {
    try
    {
      String str = LocalPushHelpr.s_appContext.getSharedPreferences(getSPTag(LocalPushHelpr.s_appContext), 4).getString("lasttestconnection", "0");
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      StaticFlurryEvent.logThrowable(localThrowable);
    }
    return "0";
  }
  
  public static String getparent()
  {
    Object localObject = LocalPushHelpr.s_appContext.getExternalFilesDir(null);
    if ((localObject != null) && (((File)localObject).exists())) {
      return ((File)localObject).getAbsolutePath();
    }
    try
    {
      localObject = Environment.getDataDirectory().getAbsolutePath();
      if (localObject != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)localObject);
        localStringBuilder.append("/Download/");
        return localStringBuilder.toString();
      }
      return "";
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      StaticFlurryEvent.logThrowable(localThrowable);
    }
    return "";
  }
  
  public static String getrunabletestid()
  {
    return gettestincase(LocalPushHelpr.s_appContext);
  }
  
  public static String gettestid()
  {
    return FDeviceInfos.f;
  }
  
  private static String gettestincase(Context paramContext)
  {
    try
    {
      String str = paramContext.getSharedPreferences(getSPTag(paramContext), 4).getString("testin_case", null);
      paramContext = str;
      if (str == null) {
        paramContext = "";
      }
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
      StaticFlurryEvent.logThrowable(paramContext);
    }
    return "";
  }
  
  public static String getua()
  {
    return LocalPushHelpr.s_appContext.getSharedPreferences(getSPTag(LocalPushHelpr.s_appContext), 4).getString("local_push_ua", "");
  }
  
  public static boolean hasPushNotificationInLastXXHours(Context paramContext, int paramInt)
  {
    paramContext = getLastUsageTime(paramContext);
    if (paramContext == null) {
      return false;
    }
    return new TimeStampUtil().minutesFromTimeToNow(paramContext) <= paramInt * 60;
  }
  
  public static String isCHLang()
  {
    if (FDeviceInfos.d().equalsIgnoreCase("CN")) {
      return "YES";
    }
    if (Locale.getDefault().toString().substring(0, 2).toLowerCase().equalsIgnoreCase("zh")) {
      return "YES";
    }
    return "NO";
  }
  
  public static boolean isDevAdb()
  {
    return FDeviceInfos.y(LocalPushHelpr.s_appContext);
  }
  
  public static boolean isDevBlack()
  {
    return FDeviceInfos.l();
  }
  
  public static String isDevMode()
  {
    if ((!isDevAdb()) && (!isDevBlack()) && (!isDevRoot()) && (!isDevNetwork())) {
      return "NO";
    }
    return "YES";
  }
  
  public static boolean isDevNetwork()
  {
    for (;;)
    {
      try
      {
        if (Build.VERSION.SDK_INT >= 14)
        {
          i = 1;
          String str1;
          if (i != 0)
          {
            String str3 = System.getProperty("http.proxyHost");
            str1 = System.getProperty("http.proxyPort");
            if (str1 == null) {
              break label88;
            }
            i = Integer.parseInt(str1);
            str1 = str3;
          }
          else
          {
            str1 = Proxy.getHost(LocalPushHelpr.s_appContext);
            i = Proxy.getPort(LocalPushHelpr.s_appContext);
          }
          boolean bool = TextUtils.isEmpty(str1);
          return (!bool) && (i != -1);
        }
      }
      catch (Throwable localThrowable)
      {
        return true;
      }
      int i = 0;
      continue;
      label88:
      String str2 = "-1";
    }
  }
  
  public static boolean isDevRoot()
  {
    return FDeviceInfos.A(LocalPushHelpr.s_appContext);
  }
  
  public static String isForgroundApp()
  {
    Object localObject = ((ActivityManager)LocalPushHelpr.s_appContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject == null) {
      return "NO";
    }
    String str = LocalPushHelpr.s_appContext.getPackageName();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if ((localRunningAppProcessInfo.importance == 100) && (localRunningAppProcessInfo.processName.equals(str))) {
        return "YES";
      }
    }
    return "NO";
  }
  
  static boolean jsonExist(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    try
    {
      paramString = new File(paramString);
      if (paramString != null)
      {
        boolean bool = paramString.exists();
        if (bool) {}
      }
      else
      {
        return false;
      }
    }
    catch (Exception paramString)
    {
      StaticFlurryEvent.logThrowable(paramString);
    }
    return true;
  }
  
  public static String loadTextByAssetPath(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString);
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString, "UTF-8");
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      StaticFlurryEvent.logThrowable(paramContext);
    }
    return null;
  }
  
  public static String loadTextByFilePath(String paramString)
  {
    paramString = new FileReader(paramString);
    BufferedReader localBufferedReader = new BufferedReader(paramString);
    StringBuffer localStringBuffer = new StringBuffer();
    for (;;)
    {
      String str = localBufferedReader.readLine();
      if (str == null) {
        break;
      }
      localStringBuffer.append(str);
    }
    localBufferedReader.close();
    paramString.close();
    return localStringBuffer.toString();
  }
  
  public static String retrytime1()
  {
    return "24";
  }
  
  public static String retrytime2()
  {
    return "672";
  }
  
  public static String retrytime3()
  {
    long l = LocalPushHelpr.s_appContext.getSharedPreferences("testing", 4).getLong("pi", 86400L) / 3600L;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(l);
    return localStringBuilder.toString();
  }
  
  public static String retrytime4()
  {
    return "24";
  }
  
  public static int runcode()
  {
    return LocalPushHelpr.s_appContext.getSharedPreferences("testing", 4).getInt("runcode", 0);
  }
  
  public static void setLastUsageTime(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences(getSPTag(paramContext), 4).edit();
    paramContext.putString("last_use_time", paramString);
    try
    {
      paramContext.apply();
      return;
    }
    catch (AbstractMethodError paramString)
    {
      for (;;) {}
    }
    paramContext.commit();
  }
  
  public static void setLocalPushedCount(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences(getSPTag(paramContext), 4).edit();
    paramContext.putInt("local_push_count", paramInt);
    try
    {
      paramContext.apply();
      return;
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;) {}
    }
    paramContext.commit();
  }
  
  public static boolean setlasttest(String paramString)
  {
    for (;;)
    {
      try
      {
        localEditor = LocalPushHelpr.s_appContext.getSharedPreferences(getSPTag(LocalPushHelpr.s_appContext), 4).edit();
        localEditor.putString("lasttest", paramString);
      }
      catch (Throwable paramString)
      {
        SharedPreferences.Editor localEditor;
        paramString.printStackTrace();
        StaticFlurryEvent.logThrowable(paramString);
        return false;
      }
      try
      {
        localEditor.apply();
      }
      catch (AbstractMethodError paramString) {}
    }
    localEditor.commit();
    return true;
  }
  
  public static boolean setlasttestconnection()
  {
    for (;;)
    {
      try
      {
        localEditor = LocalPushHelpr.s_appContext.getSharedPreferences(getSPTag(LocalPushHelpr.s_appContext), 4).edit();
        localEditor.putString("lasttestconnection", getUTCTime());
      }
      catch (Throwable localThrowable)
      {
        SharedPreferences.Editor localEditor;
        localThrowable.printStackTrace();
        StaticFlurryEvent.logThrowable(localThrowable);
        return false;
      }
      try
      {
        localEditor.apply();
      }
      catch (AbstractMethodError localAbstractMethodError) {}
    }
    localEditor.commit();
    return true;
  }
  
  public static boolean settestconf(String paramString)
  {
    ou.a("test", paramString);
    try
    {
      paramString = new JSONObject(paramString);
      SharedPreferences.Editor localEditor = LocalPushHelpr.s_appContext.getSharedPreferences("testing", 4).edit();
      if (paramString.has("start")) {
        localEditor.putInt("lv", paramString.getInt("start"));
      }
      if (paramString.has("starta")) {
        localEditor.putFloat("lav", (float)paramString.getDouble("starta"));
      }
      if (paramString.has("startb")) {
        localEditor.putFloat("lbv", (float)paramString.getDouble("startb"));
      }
      if (paramString.has("count")) {
        localEditor.putInt("cv", paramString.getInt("count"));
      }
      if (paramString.has("interval")) {
        localEditor.putInt("sv", paramString.getInt("interval"));
      }
      if (paramString.has("fcount")) {
        localEditor.putInt("fc", paramString.getInt("fcount"));
      }
      if (paramString.has("icount")) {
        localEditor.putInt("ic", paramString.getInt("icount"));
      }
      if (paramString.has("postInterval")) {
        localEditor.putLong("pi", paramString.getLong("postInterval"));
      }
      if (paramString.has("clickV")) {
        localEditor.putInt("postI", paramString.getInt("clickV"));
      }
      if (paramString.has("runcode")) {
        localEditor.putInt("runcode", paramString.getInt("runcode"));
      }
      if (paramString.has("onlyFirst")) {
        localEditor.putBoolean("onlyFirst", paramString.getBoolean("onlyFirst"));
      }
      if (paramString.has("directSB2")) {
        localEditor.putBoolean("directSB2", paramString.getBoolean("directSB2"));
      }
      if (paramString.has("useFBDB"))
      {
        boolean bool = paramString.getBoolean("useFBDB");
        LocalPushHelpr.checkUseFBDB(bool);
        localEditor.putBoolean("useFBDB", bool);
      }
      if (paramString.has("id")) {
        localEditor.putString("id", paramString.getString("id"));
      }
      if (paramString.has("pkgs")) {
        localEditor.putString("pkgs", paramString.getString("pkgs"));
      }
      localEditor.apply();
    }
    catch (Throwable paramString)
    {
      StaticFlurryEvent.logThrowable(paramString);
    }
    return true;
  }
  
  /* Error */
  private static void settestincase(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: invokestatic 173	java/lang/System:currentTimeMillis	()J
    //   3: ldc2_w 697
    //   6: ldiv
    //   7: l2i
    //   8: istore_3
    //   9: aload_0
    //   10: aload_0
    //   11: invokestatic 491	com/fotoable/autowakeup/PushUtility:getSPTag	(Landroid/content/Context;)Ljava/lang/String;
    //   14: iconst_4
    //   15: invokevirtual 239	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   18: invokeinterface 278 1 0
    //   23: astore 5
    //   25: aload_0
    //   26: invokestatic 738	com/fotoable/autowakeup/PushUtility:gettestincase	(Landroid/content/Context;)Ljava/lang/String;
    //   29: astore_0
    //   30: aload_1
    //   31: ldc_w 941
    //   34: invokevirtual 254	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   37: astore_1
    //   38: aload_1
    //   39: arraylength
    //   40: istore 4
    //   42: iconst_0
    //   43: istore_2
    //   44: iload_2
    //   45: iload 4
    //   47: if_icmpge +115 -> 162
    //   50: aload_1
    //   51: iload_2
    //   52: aaload
    //   53: astore 6
    //   55: aload_0
    //   56: ldc -13
    //   58: if_acmpne +43 -> 101
    //   61: new 89	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   68: astore_0
    //   69: aload_0
    //   70: aload 6
    //   72: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: aload_0
    //   77: ldc_w 386
    //   80: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: aload_0
    //   85: iload_3
    //   86: invokestatic 705	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   89: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: pop
    //   93: aload_0
    //   94: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: astore_0
    //   98: goto +113 -> 211
    //   101: new 89	java/lang/StringBuilder
    //   104: dup
    //   105: invokespecial 90	java/lang/StringBuilder:<init>	()V
    //   108: astore 7
    //   110: aload 7
    //   112: aload_0
    //   113: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: pop
    //   117: aload 7
    //   119: ldc_w 384
    //   122: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: pop
    //   126: aload 7
    //   128: aload 6
    //   130: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: pop
    //   134: aload 7
    //   136: ldc_w 386
    //   139: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: pop
    //   143: aload 7
    //   145: iload_3
    //   146: invokestatic 705	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   149: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: pop
    //   153: aload 7
    //   155: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   158: astore_0
    //   159: goto +52 -> 211
    //   162: aload 5
    //   164: ldc_w 744
    //   167: aload_0
    //   168: iload_3
    //   169: bipush 24
    //   171: invokestatic 943	com/fotoable/autowakeup/PushUtility:filterID	(Ljava/lang/String;II)Ljava/lang/String;
    //   174: invokeinterface 284 3 0
    //   179: pop
    //   180: aload 5
    //   182: invokeinterface 287 1 0
    //   187: return
    //   188: aload 5
    //   190: invokeinterface 871 1 0
    //   195: pop
    //   196: return
    //   197: astore_0
    //   198: aload_0
    //   199: invokevirtual 360	java/lang/Throwable:printStackTrace	()V
    //   202: aload_0
    //   203: invokestatic 167	com/fotoable/ad/StaticFlurryEvent:logThrowable	(Ljava/lang/Throwable;)V
    //   206: return
    //   207: astore_0
    //   208: goto -20 -> 188
    //   211: iload_2
    //   212: iconst_1
    //   213: iadd
    //   214: istore_2
    //   215: goto -171 -> 44
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	218	0	paramContext	Context
    //   0	218	1	paramString	String
    //   43	172	2	i	int
    //   8	161	3	j	int
    //   40	8	4	k	int
    //   23	166	5	localEditor	SharedPreferences.Editor
    //   53	76	6	str	String
    //   108	46	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   0	42	197	java/lang/Throwable
    //   61	98	197	java/lang/Throwable
    //   101	159	197	java/lang/Throwable
    //   162	180	197	java/lang/Throwable
    //   180	187	197	java/lang/Throwable
    //   188	196	197	java/lang/Throwable
    //   180	187	207	java/lang/AbstractMethodError
  }
  
  public static void setua(Context paramContext)
  {
    Object localObject = "";
    try
    {
      String str = new WebView(paramContext).getSettings().getUserAgentString();
      localObject = str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      StaticFlurryEvent.logThrowable(localThrowable);
    }
    paramContext = paramContext.getSharedPreferences(getSPTag(paramContext), 4).edit();
    paramContext.putString("local_push_ua", (String)localObject);
    try
    {
      paramContext.apply();
      return;
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;) {}
    }
    paramContext.commit();
  }
  
  public static void starttest() {}
  
  public static String testdecrypt(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = paramString2.getBytes("UTF-8");
      Object localObject = KeyGenerator.getInstance("AES");
      SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG");
      localSecureRandom.setSeed(paramString2);
      ((KeyGenerator)localObject).init(256, localSecureRandom);
      ((KeyGenerator)localObject).generateKey();
      paramString2 = new SecretKeySpec(paramString2, "AES");
      localObject = Cipher.getInstance("AES/ECB/PKCS7Padding");
      ((Cipher)localObject).init(2, paramString2);
      paramString2 = new String(((Cipher)localObject).doFinal(db64(paramString1)), "UTF-8");
      return paramString2;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      StaticFlurryEvent.logThrowable(paramString1);
      return "";
    }
    catch (IllegalBlockSizeException paramString2)
    {
      StaticFlurryEvent.logThrowable(new Exception(paramString1));
      StaticFlurryEvent.logThrowable(paramString2);
    }
    return "";
  }
  
  public static String testencrypt(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = paramString2.getBytes("UTF-8");
      Object localObject = KeyGenerator.getInstance("AES");
      SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG");
      localSecureRandom.setSeed(paramString2);
      ((KeyGenerator)localObject).init(256, localSecureRandom);
      ((KeyGenerator)localObject).generateKey();
      paramString2 = new SecretKeySpec(paramString2, "AES");
      localObject = Cipher.getInstance("AES/ECB/PKCS7Padding");
      ((Cipher)localObject).init(1, paramString2);
      paramString1 = eb64(((Cipher)localObject).doFinal(paramString1.getBytes("UTF-8")));
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      StaticFlurryEvent.logThrowable(paramString1);
    }
    return "";
  }
  
  public static boolean testfb(String paramString)
  {
    return false;
  }
  
  public static boolean testnodata(String paramString)
  {
    if (paramString.contains("log"))
    {
      ou.a("test jni", paramString);
      return true;
    }
    TestService.logging(paramString, "check", "YES");
    return true;
  }
  
  public static boolean testurl(String paramString1, String paramString2)
  {
    return true;
  }
  
  public static boolean testurl0(String paramString)
  {
    return true;
  }
  
  public static boolean testurl1(String paramString1, String paramString2, boolean paramBoolean)
  {
    return true;
  }
  
  private static void updateTimestamp(Context paramContext)
  {
    TimeStampUtil localTimeStampUtil = new TimeStampUtil();
    localTimeStampUtil.getNowTime();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(String.valueOf(localTimeStampUtil.mYear));
    localStringBuilder.append("-");
    localStringBuilder.append(String.valueOf(localTimeStampUtil.mMonth));
    localStringBuilder.append("-");
    localStringBuilder.append(String.valueOf(localTimeStampUtil.mDay));
    localStringBuilder.append(" ");
    localStringBuilder.append(String.valueOf(localTimeStampUtil.mHour));
    localStringBuilder.append(":");
    localStringBuilder.append(String.valueOf(localTimeStampUtil.mMinute));
    localStringBuilder.append(":");
    localStringBuilder.append(String.valueOf(localTimeStampUtil.mSecond));
    setLastUsageTime(paramContext, localStringBuilder.toString());
  }
  
  public static void updateTimestampAfterAppUsed(Context paramContext)
  {
    try
    {
      updateTimestamp(paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
      StaticFlurryEvent.logThrowable(paramContext);
    }
  }
  
  public static void updateTimestampAfterNotified(Context paramContext)
  {
    setLocalPushedCount(paramContext, getLocalPushedCount(paramContext) + 1);
    updateTimestamp(paramContext);
  }
  
  public static String userinstalledapps()
  {
    int j = 0;
    int i;
    try
    {
      Iterator localIterator = LocalPushHelpr.s_appContext.getPackageManager().getInstalledApplications(128).iterator();
      i = 0;
      while (localIterator.hasNext())
      {
        int k = ((ApplicationInfo)localIterator.next()).flags;
        if ((k & 0x1) == 0) {
          i += 1;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      StaticFlurryEvent.logThrowable(localThrowable);
      i = j;
    }
    return String.valueOf(i);
  }
  
  static boolean validateOnlineJson(String paramString)
  {
    return true;
  }
}
