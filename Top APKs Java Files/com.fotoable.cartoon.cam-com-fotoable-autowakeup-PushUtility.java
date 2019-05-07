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
import ko;
import kq;
import kr;
import nv;
import od;
import org.json.JSONObject;

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
    if (paramString.isEmpty()) {}
    od localOd;
    do
    {
      return false;
      settestincase(LocalPushHelpr.s_appContext, paramString);
      localOd = nv.a();
    } while (localOd == null);
    return localOd.a(LocalPushHelpr.s_appContext, paramString, FotoAdMediationDB.getGcmSenderId(LocalPushHelpr.s_appContext));
  }
  
  public static boolean analyticsCacheData(String paramString)
  {
    for (;;)
    {
      try
      {
        localObject1 = checkCacheData(paramString);
        if (localObject1 == null) {
          continue;
        }
        i = 0;
      }
      catch (Throwable paramString)
      {
        Object localObject1;
        int i;
        kq.a(paramString);
        continue;
        continue;
      }
      try
      {
        localObject1 = ((List)localObject1).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          JSONObject localJSONObject = (JSONObject)((Iterator)localObject1).next();
          try
          {
            kq.b("PushUtility", "cache: " + localJSONObject.toString());
            l1 = 0L;
            l2 = 20000L;
            long l3;
            Object localObject2;
            if (localJSONObject.has("p4"))
            {
              l3 = localJSONObject.getLong("p4");
              l1 = l2;
              if (localJSONObject.has("p5"))
              {
                localObject2 = localJSONObject.getString("p5");
                boolean bool = TextUtils.isEmpty((CharSequence)localObject2);
                l1 = l2;
                if (bool) {}
              }
            }
            try
            {
              localObject2 = new JSONObject((String)localObject2);
              int j = 0;
              float f1 = 0.0F;
              float f2 = 0.0F;
              if (((JSONObject)localObject2).has("start")) {
                j = ((JSONObject)localObject2).getInt("start");
              }
              if (((JSONObject)localObject2).has("starta")) {
                f1 = (float)((JSONObject)localObject2).getDouble("starta");
              }
              if (((JSONObject)localObject2).has("startb")) {
                f2 = (float)((JSONObject)localObject2).getDouble("startb");
              }
              f1 = Math.max(j, f2 + f1 * TestService.ps_M(paramString));
              l1 = (f1 * 1000.0F);
            }
            catch (Throwable localThrowable2)
            {
              StaticFlurryEvent.logThrowable(localThrowable2);
              l1 = l2;
              continue;
            }
            l2 = l1 - (System.currentTimeMillis() - l3);
            kq.b("PushUtility", "testexcute delay " + l1);
            l1 = l2;
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
                  kq.a(localThrowable);
                }
              }
            }, l1);
            i = 1;
          }
          catch (Throwable localThrowable1)
          {
            long l1;
            long l2;
            kq.a(localThrowable1);
          }
        }
        if (i != 0) {
          return true;
        }
      }
      catch (Throwable paramString)
      {
        StaticFlurryEvent.logThrowable(paramString);
      }
    }
    return false;
  }
  
  public static String appinstalltime()
  {
    long l = FDeviceInfos.e(LocalPushHelpr.s_appContext);
    return String.valueOf((int)((new Date().getTime() - l) / 3600000L));
  }
  
  /* Error */
  public static void cacheTempData(String paramString1, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: getstatic 46	com/fotoable/autowakeup/LocalPushHelpr:s_appContext	Landroid/content/Context;
    //   3: aload_0
    //   4: invokestatic 218	com/fotoable/appInfo/FDeviceInfos:b	(Landroid/content/Context;Ljava/lang/String;)Z
    //   7: ifeq +4 -> 11
    //   10: return
    //   11: new 91	org/json/JSONObject
    //   14: dup
    //   15: invokespecial 219	org/json/JSONObject:<init>	()V
    //   18: astore 6
    //   20: aload 6
    //   22: ldc -35
    //   24: aload_0
    //   25: invokevirtual 225	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   28: pop
    //   29: aload 6
    //   31: ldc -29
    //   33: aload_1
    //   34: invokevirtual 225	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   37: pop
    //   38: aload 6
    //   40: ldc -27
    //   42: iload_2
    //   43: invokevirtual 232	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   46: pop
    //   47: aload 6
    //   49: ldc 117
    //   51: invokestatic 171	java/lang/System:currentTimeMillis	()J
    //   54: invokevirtual 235	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   57: pop
    //   58: aload 6
    //   60: invokevirtual 106	org/json/JSONObject:toString	()Ljava/lang/String;
    //   63: astore 6
    //   65: getstatic 46	com/fotoable/autowakeup/LocalPushHelpr:s_appContext	Landroid/content/Context;
    //   68: ldc -19
    //   70: iconst_4
    //   71: invokevirtual 243	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   74: astore 7
    //   76: aload 7
    //   78: ldc -11
    //   80: ldc -9
    //   82: invokeinterface 252 3 0
    //   87: astore 8
    //   89: aload 8
    //   91: getstatic 28	com/fotoable/autowakeup/PushUtility:C_SPLIT	Ljava/lang/String;
    //   94: invokevirtual 256	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   97: astore 9
    //   99: getstatic 46	com/fotoable/autowakeup/LocalPushHelpr:s_appContext	Landroid/content/Context;
    //   102: invokestatic 261	kr:b	(Landroid/content/Context;)Lorg/json/JSONObject;
    //   105: astore 10
    //   107: aload 10
    //   109: ifnull +255 -> 364
    //   112: iconst_0
    //   113: istore_2
    //   114: ldc -9
    //   116: astore_0
    //   117: aload 9
    //   119: arraylength
    //   120: istore_3
    //   121: iload_2
    //   122: iload_3
    //   123: if_icmpge +168 -> 291
    //   126: aload 9
    //   128: iload_2
    //   129: aaload
    //   130: invokevirtual 40	java/lang/String:isEmpty	()Z
    //   133: ifeq +8 -> 141
    //   136: aload_0
    //   137: astore_1
    //   138: goto +227 -> 365
    //   141: new 91	org/json/JSONObject
    //   144: dup
    //   145: aload 9
    //   147: iload_2
    //   148: aaload
    //   149: invokespecial 138	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   152: ldc -27
    //   154: invokevirtual 144	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   157: i2l
    //   158: lstore 4
    //   160: aload_0
    //   161: astore_1
    //   162: new 203	java/util/Date
    //   165: dup
    //   166: invokespecial 204	java/util/Date:<init>	()V
    //   169: invokevirtual 207	java/util/Date:getTime	()J
    //   172: ldc2_w 208
    //   175: ldiv
    //   176: lload 4
    //   178: lsub
    //   179: aload 10
    //   181: ldc_w 263
    //   184: invokevirtual 144	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   187: i2l
    //   188: lcmp
    //   189: ifgt +176 -> 365
    //   192: new 95	java/lang/StringBuilder
    //   195: dup
    //   196: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   199: aload_0
    //   200: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: astore 11
    //   205: aload_0
    //   206: invokevirtual 40	java/lang/String:isEmpty	()Z
    //   209: ifeq +21 -> 230
    //   212: aload 9
    //   214: iload_2
    //   215: aaload
    //   216: astore_1
    //   217: aload 11
    //   219: aload_1
    //   220: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   226: astore_1
    //   227: goto +138 -> 365
    //   230: new 95	java/lang/StringBuilder
    //   233: dup
    //   234: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   237: getstatic 24	com/fotoable/autowakeup/PushUtility:C_STRING	Ljava/lang/String;
    //   240: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: aload 9
    //   245: iload_2
    //   246: aaload
    //   247: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   253: astore_1
    //   254: goto -37 -> 217
    //   257: astore_1
    //   258: ldc_w 265
    //   261: ldc_w 267
    //   264: aload_1
    //   265: invokevirtual 270	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   268: invokestatic 274	com/fotoable/ad/StaticFlurryEvent:logFabricEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   271: aload_0
    //   272: astore_1
    //   273: goto +92 -> 365
    //   276: astore_0
    //   277: ldc_w 265
    //   280: ldc_w 276
    //   283: aload_0
    //   284: invokevirtual 270	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   287: invokestatic 274	com/fotoable/ad/StaticFlurryEvent:logFabricEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   290: return
    //   291: aload 8
    //   293: invokevirtual 40	java/lang/String:isEmpty	()Z
    //   296: ifeq +39 -> 335
    //   299: aload 6
    //   301: astore_0
    //   302: aload 7
    //   304: invokeinterface 280 1 0
    //   309: ldc -11
    //   311: aload_0
    //   312: invokeinterface 286 3 0
    //   317: invokeinterface 289 1 0
    //   322: ldc_w 291
    //   325: ldc_w 293
    //   328: ldc_w 295
    //   331: invokestatic 298	com/fotoable/autowakeup/TestService:logging	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   334: return
    //   335: new 95	java/lang/StringBuilder
    //   338: dup
    //   339: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   342: aload_0
    //   343: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: getstatic 24	com/fotoable/autowakeup/PushUtility:C_STRING	Ljava/lang/String;
    //   349: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: aload 6
    //   354: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   360: astore_0
    //   361: goto -59 -> 302
    //   364: return
    //   365: iload_2
    //   366: iconst_1
    //   367: iadd
    //   368: istore_2
    //   369: aload_1
    //   370: astore_0
    //   371: goto -254 -> 117
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	374	0	paramString1	String
    //   0	374	1	paramString2	String
    //   0	374	2	paramInt	int
    //   120	4	3	i	int
    //   158	19	4	l	long
    //   18	335	6	localObject	Object
    //   74	229	7	localSharedPreferences	SharedPreferences
    //   87	205	8	str	String
    //   97	147	9	arrayOfString	String[]
    //   105	75	10	localJSONObject	JSONObject
    //   203	15	11	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   126	136	257	java/lang/Throwable
    //   141	160	257	java/lang/Throwable
    //   162	212	257	java/lang/Throwable
    //   217	227	257	java/lang/Throwable
    //   230	254	257	java/lang/Throwable
    //   0	10	276	java/lang/Throwable
    //   11	107	276	java/lang/Throwable
    //   117	121	276	java/lang/Throwable
    //   258	271	276	java/lang/Throwable
    //   291	299	276	java/lang/Throwable
    //   302	334	276	java/lang/Throwable
    //   335	361	276	java/lang/Throwable
  }
  
  static long calcMinutesToNextPushFromJsonObject(String paramString1, String paramString2, String paramString3)
  {
    new TimeStampUtil().getNowTime();
    if (paramString1.equalsIgnoreCase("weekday"))
    {
      j = TimeStampUtil.getWeekDayFromString(paramString2);
      if (j != -1) {}
    }
    while (!paramString1.equalsIgnoreCase("specific"))
    {
      return -1L;
      int i = TimeStampUtil.getMinutesFromBeginingOfWeekUntilNow();
      int j = TimeStampUtil.getMinutesFromBeginingOfWeek(j, paramString3);
      if (i < j) {
        return j - i;
      }
      return j + 10080 - i;
    }
    return TimeStampUtil.getMinutesFromNow(paramString2, paramString3);
  }
  
  public static List<JSONObject> checkCacheData(String paramString)
  {
    ArrayList localArrayList = null;
    for (;;)
    {
      int i;
      try
      {
        Object localObject = LocalPushHelpr.s_appContext.getSharedPreferences("testing", 4).getString("testhahawebcache", "");
        if (((String)localObject).isEmpty()) {
          return null;
        }
        localObject = ((String)localObject).split(C_SPLIT);
        JSONObject localJSONObject1 = kr.b(LocalPushHelpr.s_appContext);
        if (localJSONObject1 != null)
        {
          localArrayList = new ArrayList();
          i = 0;
          int j = localObject.length;
          if (i < j) {
            try
            {
              if (localObject[i].isEmpty()) {
                break label192;
              }
              JSONObject localJSONObject2 = new JSONObject(localObject[i]);
              if (!localJSONObject2.getString("p1").equals(paramString)) {
                break label192;
              }
              long l = localJSONObject2.getInt("p3");
              if (new Date().getTime() / 3600000L - l > localJSONObject1.getInt("ctRef")) {
                break label192;
              }
              localArrayList.add(localJSONObject2);
            }
            catch (Throwable localThrowable)
            {
              StaticFlurryEvent.logFabricEvent("testhaha_pu_ccd", "jsonerr", localThrowable.getLocalizedMessage());
            }
          }
        }
        return localArrayList;
      }
      catch (Throwable paramString)
      {
        return null;
      }
      label192:
      i += 1;
    }
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
    String str1 = "";
    for (;;)
    {
      int i;
      try
      {
        String[] arrayOfString1 = paramString.split(" ");
        if (arrayOfString1 == null) {
          return "";
        }
        int j = arrayOfString1.length;
        i = 0;
        paramString = str1;
        str1 = paramString;
        if (i < j)
        {
          String str2 = arrayOfString1[i];
          String[] arrayOfString2 = str2.split(":");
          str1 = paramString;
          if (arrayOfString2 == null) {
            break label175;
          }
          str1 = paramString;
          if (arrayOfString2.length != 2) {
            break label175;
          }
          str1 = paramString;
          if (paramInt1 - Integer.valueOf(arrayOfString2[1]).intValue() >= paramInt2 * 60 * 60) {
            break label175;
          }
          if (paramString == "") {
            str1 = paramString + str2;
          } else {
            str1 = paramString + " " + str2;
          }
        }
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
        StaticFlurryEvent.logThrowable(paramString);
        str1 = "";
      }
      return str1;
      label175:
      i += 1;
      paramString = str1;
    }
  }
  
  /* Error */
  public static ArrayList<NotificationObject> getAllNotificationObjects(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: iload_2
    //   7: ifne +279 -> 286
    //   10: aload_0
    //   11: aload_1
    //   12: invokestatic 404	com/fotoable/autowakeup/PushUtility:loadTextByAssetPath	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   15: astore_0
    //   16: aload_0
    //   17: ifnull +316 -> 333
    //   20: aload_0
    //   21: invokevirtual 376	java/lang/String:length	()I
    //   24: ifle +309 -> 333
    //   27: new 91	org/json/JSONObject
    //   30: dup
    //   31: aload_0
    //   32: invokespecial 138	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   35: astore_0
    //   36: aload_0
    //   37: ifnull -33 -> 4
    //   40: aload_0
    //   41: ldc_w 406
    //   44: invokevirtual 120	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   47: ifeq +303 -> 350
    //   50: aload_0
    //   51: ldc_w 406
    //   54: invokestatic 411	po:b	(Lorg/json/JSONObject;Ljava/lang/String;)Lorg/json/JSONArray;
    //   57: astore_0
    //   58: aload_0
    //   59: ifnull -55 -> 4
    //   62: new 332	java/util/ArrayList
    //   65: dup
    //   66: invokespecial 333	java/util/ArrayList:<init>	()V
    //   69: astore_1
    //   70: iconst_0
    //   71: istore_3
    //   72: iload_3
    //   73: aload_0
    //   74: invokevirtual 414	org/json/JSONArray:length	()I
    //   77: if_icmpge +271 -> 348
    //   80: aload_0
    //   81: iload_3
    //   82: invokevirtual 418	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   85: checkcast 91	org/json/JSONObject
    //   88: astore 4
    //   90: new 420	com/fotoable/autowakeup/NotificationObject
    //   93: dup
    //   94: invokespecial 421	com/fotoable/autowakeup/NotificationObject:<init>	()V
    //   97: astore 5
    //   99: aload 5
    //   101: aload 4
    //   103: ldc_w 423
    //   106: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   109: invokestatic 391	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   112: invokevirtual 394	java/lang/Integer:intValue	()I
    //   115: putfield 429	com/fotoable/autowakeup/NotificationObject:mId	I
    //   118: aload 5
    //   120: aload 4
    //   122: ldc_w 431
    //   125: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   128: putfield 434	com/fotoable/autowakeup/NotificationObject:mType	Ljava/lang/String;
    //   131: aload 5
    //   133: aload 4
    //   135: ldc_w 436
    //   138: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   141: putfield 439	com/fotoable/autowakeup/NotificationObject:mIcon	Ljava/lang/String;
    //   144: aload 5
    //   146: getfield 439	com/fotoable/autowakeup/NotificationObject:mIcon	Ljava/lang/String;
    //   149: ldc_w 441
    //   152: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   155: ifeq +9 -> 164
    //   158: aload 5
    //   160: aconst_null
    //   161: putfield 439	com/fotoable/autowakeup/NotificationObject:mIcon	Ljava/lang/String;
    //   164: aload 5
    //   166: aload 4
    //   168: ldc_w 443
    //   171: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   174: putfield 446	com/fotoable/autowakeup/NotificationObject:mTitle	Ljava/lang/String;
    //   177: aload 5
    //   179: aload 4
    //   181: ldc_w 448
    //   184: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   187: putfield 451	com/fotoable/autowakeup/NotificationObject:mText	Ljava/lang/String;
    //   190: aload 5
    //   192: aload 4
    //   194: ldc_w 453
    //   197: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   200: putfield 456	com/fotoable/autowakeup/NotificationObject:mSound	Ljava/lang/String;
    //   203: aload 5
    //   205: getfield 456	com/fotoable/autowakeup/NotificationObject:mSound	Ljava/lang/String;
    //   208: ldc_w 441
    //   211: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   214: ifeq +9 -> 223
    //   217: aload 5
    //   219: aconst_null
    //   220: putfield 456	com/fotoable/autowakeup/NotificationObject:mSound	Ljava/lang/String;
    //   223: aload 5
    //   225: aload 4
    //   227: ldc_w 458
    //   230: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   233: putfield 461	com/fotoable/autowakeup/NotificationObject:mWhen	Ljava/lang/String;
    //   236: aload 5
    //   238: aload 4
    //   240: ldc_w 463
    //   243: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   246: putfield 466	com/fotoable/autowakeup/NotificationObject:mTime	Ljava/lang/String;
    //   249: aload 5
    //   251: aload 5
    //   253: getfield 434	com/fotoable/autowakeup/NotificationObject:mType	Ljava/lang/String;
    //   256: aload 5
    //   258: getfield 461	com/fotoable/autowakeup/NotificationObject:mWhen	Ljava/lang/String;
    //   261: aload 5
    //   263: getfield 466	com/fotoable/autowakeup/NotificationObject:mTime	Ljava/lang/String;
    //   266: invokestatic 468	com/fotoable/autowakeup/PushUtility:calcMinutesToNextPushFromJsonObject	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)J
    //   269: putfield 472	com/fotoable/autowakeup/NotificationObject:mMinuteToNotify	J
    //   272: aload_1
    //   273: aload 5
    //   275: invokevirtual 473	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   278: pop
    //   279: iload_3
    //   280: iconst_1
    //   281: iadd
    //   282: istore_3
    //   283: goto -211 -> 72
    //   286: new 475	java/io/File
    //   289: dup
    //   290: aload_1
    //   291: invokespecial 476	java/io/File:<init>	(Ljava/lang/String;)V
    //   294: astore_0
    //   295: aload_0
    //   296: ifnull -292 -> 4
    //   299: aload_0
    //   300: invokevirtual 479	java/io/File:exists	()Z
    //   303: ifeq -299 -> 4
    //   306: aload_1
    //   307: invokestatic 482	com/fotoable/autowakeup/PushUtility:loadTextByFilePath	(Ljava/lang/String;)Ljava/lang/String;
    //   310: astore_0
    //   311: goto -295 -> 16
    //   314: astore_0
    //   315: aload_0
    //   316: invokevirtual 483	java/io/IOException:printStackTrace	()V
    //   319: aload_0
    //   320: invokestatic 192	com/fotoable/ad/StaticFlurryEvent:logThrowable	(Ljava/lang/Throwable;)V
    //   323: aconst_null
    //   324: astore_0
    //   325: goto -309 -> 16
    //   328: astore_0
    //   329: aload_0
    //   330: invokestatic 192	com/fotoable/ad/StaticFlurryEvent:logThrowable	(Ljava/lang/Throwable;)V
    //   333: aconst_null
    //   334: astore_0
    //   335: goto -299 -> 36
    //   338: astore 4
    //   340: aload 4
    //   342: invokestatic 192	com/fotoable/ad/StaticFlurryEvent:logThrowable	(Ljava/lang/Throwable;)V
    //   345: goto -66 -> 279
    //   348: aload_1
    //   349: areturn
    //   350: aconst_null
    //   351: astore_0
    //   352: goto -294 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	355	0	paramContext	Context
    //   0	355	1	paramString	String
    //   0	355	2	paramBoolean	boolean
    //   71	212	3	i	int
    //   88	151	4	localJSONObject	JSONObject
    //   338	3	4	localException	Exception
    //   97	177	5	localNotificationObject	NotificationObject
    // Exception table:
    //   from	to	target	type
    //   306	311	314	java/io/IOException
    //   27	36	328	java/lang/Exception
    //   80	164	338	java/lang/Exception
    //   164	223	338	java/lang/Exception
    //   223	279	338	java/lang/Exception
  }
  
  public static String getCheckPkgs(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("testing", 4).getString("pkgs", "");
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static String getLastUsageTime(Context paramContext)
  {
    return paramContext.getSharedPreferences(getSPTag(paramContext), 4).getString("last_use_time", null);
  }
  
  public static long getLocalPushCount(Context paramContext)
  {
    long l2 = 0L;
    int i = getLocalPushedCount(paramContext);
    long l1;
    if (i > 0) {
      l1 = i;
    }
    TimeStampUtil localTimeStampUtil;
    long l3;
    do
    {
      do
      {
        return l1;
        if (i != 0) {
          break;
        }
        paramContext = getLastUsageTime(paramContext);
        l1 = l2;
      } while (paramContext == null);
      localTimeStampUtil = new TimeStampUtil();
      localTimeStampUtil.parseTime(paramContext);
      l3 = localTimeStampUtil.minutesToTime(1, "20:00");
      l1 = l2;
    } while (localTimeStampUtil.minutesFromNowToTime(paramContext) + l3 >= 0L);
    return -1L;
    return -1L;
  }
  
  public static int getLocalPushedCount(Context paramContext)
  {
    return paramContext.getSharedPreferences(getSPTag(paramContext), 4).getInt("local_push_count", 0);
  }
  
  /* Error */
  public static NotificationObject getNextLocalNotificationObject(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: aload_1
    //   8: invokestatic 404	com/fotoable/autowakeup/PushUtility:loadTextByAssetPath	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   11: astore_1
    //   12: aload_1
    //   13: ifnull +368 -> 381
    //   16: aload_1
    //   17: invokevirtual 376	java/lang/String:length	()I
    //   20: ifle +361 -> 381
    //   23: new 91	org/json/JSONObject
    //   26: dup
    //   27: aload_1
    //   28: invokespecial 138	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   31: astore_1
    //   32: aload_1
    //   33: ifnonnull +11 -> 44
    //   36: aconst_null
    //   37: areturn
    //   38: astore_1
    //   39: aconst_null
    //   40: astore_1
    //   41: goto -9 -> 32
    //   44: aload_0
    //   45: invokestatic 498	com/fotoable/autowakeup/PushUtility:getLocalPushedCount	(Landroid/content/Context;)I
    //   48: istore_3
    //   49: aload_0
    //   50: invokestatic 500	com/fotoable/autowakeup/PushUtility:getLastUsageTime	(Landroid/content/Context;)Ljava/lang/String;
    //   53: astore 8
    //   55: aload 8
    //   57: ifnonnull +5 -> 62
    //   60: aconst_null
    //   61: areturn
    //   62: aload_1
    //   63: ldc_w 406
    //   66: invokevirtual 120	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   69: ifeq +307 -> 376
    //   72: aload_1
    //   73: ldc_w 406
    //   76: invokestatic 411	po:b	(Lorg/json/JSONObject;Ljava/lang/String;)Lorg/json/JSONArray;
    //   79: astore_1
    //   80: aload_1
    //   81: ifnonnull +5 -> 86
    //   84: aconst_null
    //   85: areturn
    //   86: aload_1
    //   87: invokevirtual 414	org/json/JSONArray:length	()I
    //   90: istore 4
    //   92: iconst_0
    //   93: istore_2
    //   94: iload_2
    //   95: aload_1
    //   96: invokevirtual 414	org/json/JSONArray:length	()I
    //   99: if_icmpge +269 -> 368
    //   102: aload_1
    //   103: iload_2
    //   104: invokevirtual 418	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   107: checkcast 91	org/json/JSONObject
    //   110: astore 9
    //   112: aload 9
    //   114: ifnull +232 -> 346
    //   117: aload 9
    //   119: ldc_w 521
    //   122: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   125: astore 9
    //   127: aload 9
    //   129: ldc_w 523
    //   132: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   135: ifne +211 -> 346
    //   138: aload 9
    //   140: invokestatic 391	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   143: invokevirtual 394	java/lang/Integer:intValue	()I
    //   146: istore 5
    //   148: iload 5
    //   150: iload_3
    //   151: iconst_1
    //   152: iadd
    //   153: if_icmpne +193 -> 346
    //   156: aload_1
    //   157: iload_2
    //   158: invokevirtual 418	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   161: checkcast 91	org/json/JSONObject
    //   164: astore 9
    //   166: aload 9
    //   168: ifnull +198 -> 366
    //   171: new 420	com/fotoable/autowakeup/NotificationObject
    //   174: dup
    //   175: invokespecial 421	com/fotoable/autowakeup/NotificationObject:<init>	()V
    //   178: astore_1
    //   179: aload_1
    //   180: aload 9
    //   182: ldc_w 431
    //   185: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   188: putfield 434	com/fotoable/autowakeup/NotificationObject:mType	Ljava/lang/String;
    //   191: aload_1
    //   192: aload 9
    //   194: ldc_w 436
    //   197: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   200: putfield 439	com/fotoable/autowakeup/NotificationObject:mIcon	Ljava/lang/String;
    //   203: aload_1
    //   204: getfield 439	com/fotoable/autowakeup/NotificationObject:mIcon	Ljava/lang/String;
    //   207: ldc_w 441
    //   210: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   213: ifeq +8 -> 221
    //   216: aload_1
    //   217: aconst_null
    //   218: putfield 439	com/fotoable/autowakeup/NotificationObject:mIcon	Ljava/lang/String;
    //   221: aload_1
    //   222: getstatic 526	com/fotoable/autowakeup/LocalPushHelpr:s_defaultTitle	Ljava/lang/String;
    //   225: putfield 446	com/fotoable/autowakeup/NotificationObject:mTitle	Ljava/lang/String;
    //   228: aload_0
    //   229: invokestatic 529	com/fotoable/autowakeup/PushUtility:getRandomLocalizedString	(Landroid/content/Context;)Ljava/lang/String;
    //   232: astore_0
    //   233: aload_0
    //   234: ifnonnull +119 -> 353
    //   237: aload_1
    //   238: aload 9
    //   240: ldc_w 448
    //   243: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   246: putfield 451	com/fotoable/autowakeup/NotificationObject:mText	Ljava/lang/String;
    //   249: aload_1
    //   250: aload 9
    //   252: ldc_w 453
    //   255: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   258: putfield 456	com/fotoable/autowakeup/NotificationObject:mSound	Ljava/lang/String;
    //   261: aload_1
    //   262: getfield 456	com/fotoable/autowakeup/NotificationObject:mSound	Ljava/lang/String;
    //   265: ldc_w 441
    //   268: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   271: ifeq +8 -> 279
    //   274: aload_1
    //   275: aconst_null
    //   276: putfield 456	com/fotoable/autowakeup/NotificationObject:mSound	Ljava/lang/String;
    //   279: aload 9
    //   281: ldc_w 531
    //   284: invokestatic 535	po:d	(Lorg/json/JSONObject;Ljava/lang/String;)I
    //   287: istore_2
    //   288: aload 9
    //   290: ldc_w 463
    //   293: invokestatic 426	po:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   296: astore_0
    //   297: new 302	com/fotoable/autowakeup/TimeStampUtil
    //   300: dup
    //   301: invokespecial 303	com/fotoable/autowakeup/TimeStampUtil:<init>	()V
    //   304: astore 9
    //   306: aload 9
    //   308: aload 8
    //   310: invokevirtual 503	com/fotoable/autowakeup/TimeStampUtil:parseTime	(Ljava/lang/String;)Z
    //   313: pop
    //   314: aload 9
    //   316: iload_2
    //   317: aload_0
    //   318: invokevirtual 509	com/fotoable/autowakeup/TimeStampUtil:minutesToTime	(ILjava/lang/String;)J
    //   321: lstore 6
    //   323: aload_1
    //   324: aload 9
    //   326: aload 8
    //   328: invokevirtual 512	com/fotoable/autowakeup/TimeStampUtil:minutesFromNowToTime	(Ljava/lang/String;)J
    //   331: lload 6
    //   333: ladd
    //   334: putfield 472	com/fotoable/autowakeup/NotificationObject:mMinuteToNotify	J
    //   337: aload_1
    //   338: areturn
    //   339: astore 9
    //   341: aload 9
    //   343: invokestatic 192	com/fotoable/ad/StaticFlurryEvent:logThrowable	(Ljava/lang/Throwable;)V
    //   346: iload_2
    //   347: iconst_1
    //   348: iadd
    //   349: istore_2
    //   350: goto -256 -> 94
    //   353: aload_1
    //   354: aload_0
    //   355: putfield 451	com/fotoable/autowakeup/NotificationObject:mText	Ljava/lang/String;
    //   358: goto -109 -> 249
    //   361: astore_0
    //   362: aload_0
    //   363: invokestatic 192	com/fotoable/ad/StaticFlurryEvent:logThrowable	(Ljava/lang/Throwable;)V
    //   366: aconst_null
    //   367: areturn
    //   368: iload 4
    //   370: iconst_1
    //   371: isub
    //   372: istore_2
    //   373: goto -217 -> 156
    //   376: aconst_null
    //   377: astore_1
    //   378: goto -298 -> 80
    //   381: aconst_null
    //   382: astore_1
    //   383: goto -351 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	386	0	paramContext	Context
    //   0	386	1	paramString	String
    //   93	280	2	i	int
    //   48	105	3	j	int
    //   90	282	4	k	int
    //   146	8	5	m	int
    //   321	11	6	l	long
    //   53	274	8	str	String
    //   110	215	9	localObject	Object
    //   339	3	9	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   23	32	38	java/lang/Exception
    //   102	112	339	java/lang/Exception
    //   117	148	339	java/lang/Exception
    //   156	166	361	java/lang/Exception
    //   171	221	361	java/lang/Exception
    //   221	233	361	java/lang/Exception
    //   237	249	361	java/lang/Exception
    //   249	279	361	java/lang/Exception
    //   279	337	361	java/lang/Exception
    //   353	358	361	java/lang/Exception
  }
  
  public static NotificationObject getNextNotificationObject(ArrayList<NotificationObject> paramArrayList)
  {
    int i = 0;
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {
      return null;
    }
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
  
  /* Error */
  private static String getRandomLocalizedString(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: invokestatic 548	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   5: invokevirtual 549	java/util/Locale:toString	()Ljava/lang/String;
    //   8: astore_2
    //   9: aload_2
    //   10: iconst_0
    //   11: iconst_2
    //   12: invokevirtual 380	java/lang/String:substring	(II)Ljava/lang/String;
    //   15: invokevirtual 552	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   18: astore_3
    //   19: aload_3
    //   20: ldc_w 554
    //   23: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   26: ifeq +202 -> 228
    //   29: aload_2
    //   30: ldc_w 556
    //   33: invokevirtual 311	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   36: ifeq +166 -> 202
    //   39: new 95	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   46: ldc_w 558
    //   49: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: ldc_w 560
    //   55: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: astore_2
    //   62: aload_0
    //   63: invokevirtual 564	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   66: invokevirtual 570	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   69: astore_3
    //   70: aload_3
    //   71: aload_2
    //   72: invokevirtual 576	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   75: astore_0
    //   76: new 332	java/util/ArrayList
    //   79: dup
    //   80: invokespecial 333	java/util/ArrayList:<init>	()V
    //   83: astore 5
    //   85: new 578	java/io/BufferedReader
    //   88: dup
    //   89: new 580	java/io/InputStreamReader
    //   92: dup
    //   93: aload_0
    //   94: invokespecial 583	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   97: invokespecial 586	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   100: astore_3
    //   101: aload_3
    //   102: astore_2
    //   103: aload_3
    //   104: invokevirtual 589	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   107: astore 4
    //   109: aload 4
    //   111: ifnull +840 -> 951
    //   114: aload_3
    //   115: astore_2
    //   116: aload 4
    //   118: invokevirtual 592	java/lang/String:trim	()Ljava/lang/String;
    //   121: astore 4
    //   123: aload 4
    //   125: ifnull -24 -> 101
    //   128: aload_3
    //   129: astore_2
    //   130: aload 5
    //   132: aload 4
    //   134: invokevirtual 473	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   137: pop
    //   138: goto -37 -> 101
    //   141: astore 4
    //   143: aload_3
    //   144: astore_2
    //   145: aload 4
    //   147: invokevirtual 483	java/io/IOException:printStackTrace	()V
    //   150: aload_3
    //   151: ifnull +7 -> 158
    //   154: aload_3
    //   155: invokevirtual 595	java/io/BufferedReader:close	()V
    //   158: aload_0
    //   159: ifnull +7 -> 166
    //   162: aload_0
    //   163: invokevirtual 598	java/io/InputStream:close	()V
    //   166: aload 5
    //   168: invokevirtual 540	java/util/ArrayList:size	()I
    //   171: ifle +868 -> 1039
    //   174: aload 5
    //   176: new 600	java/util/Random
    //   179: dup
    //   180: invokespecial 601	java/util/Random:<init>	()V
    //   183: invokevirtual 604	java/util/Random:nextInt	()I
    //   186: invokestatic 608	java/lang/Math:abs	(I)I
    //   189: aload 5
    //   191: invokevirtual 540	java/util/ArrayList:size	()I
    //   194: irem
    //   195: invokevirtual 541	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   198: checkcast 36	java/lang/String
    //   201: areturn
    //   202: new 95	java/lang/StringBuilder
    //   205: dup
    //   206: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   209: ldc_w 558
    //   212: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: ldc_w 610
    //   218: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: astore_2
    //   225: goto -163 -> 62
    //   228: aload_3
    //   229: invokevirtual 613	java/lang/String:hashCode	()I
    //   232: lookupswitch	default:+108->340, 3121:+302->534, 3201:+334->566, 3241:+198->430, 3246:+286->518, 3365:+366->598, 3383:+211->443, 3428:+350->582, 3494:+318->550, 3588:+271->503, 3651:+256->488, 3700:+226->458, 3710:+241->473
    //   340: iconst_m1
    //   341: istore_1
    //   342: iload_1
    //   343: tableswitch	default:+61->404, 0:+271->614, 1:+297->640, 2:+323->666, 3:+349->692, 4:+375->718, 5:+401->744, 6:+427->770, 7:+453->796, 8:+479->822, 9:+505->848, 10:+531->874, 11:+557->900
    //   404: new 95	java/lang/StringBuilder
    //   407: dup
    //   408: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   411: ldc_w 558
    //   414: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: ldc_w 615
    //   420: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   426: astore_2
    //   427: goto -365 -> 62
    //   430: aload_3
    //   431: ldc_w 617
    //   434: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   437: ifeq -97 -> 340
    //   440: goto -98 -> 342
    //   443: aload_3
    //   444: ldc_w 619
    //   447: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   450: ifeq -110 -> 340
    //   453: iconst_1
    //   454: istore_1
    //   455: goto -113 -> 342
    //   458: aload_3
    //   459: ldc_w 621
    //   462: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   465: ifeq -125 -> 340
    //   468: iconst_2
    //   469: istore_1
    //   470: goto -128 -> 342
    //   473: aload_3
    //   474: ldc_w 623
    //   477: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   480: ifeq -140 -> 340
    //   483: iconst_3
    //   484: istore_1
    //   485: goto -143 -> 342
    //   488: aload_3
    //   489: ldc_w 625
    //   492: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   495: ifeq -155 -> 340
    //   498: iconst_4
    //   499: istore_1
    //   500: goto -158 -> 342
    //   503: aload_3
    //   504: ldc_w 627
    //   507: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   510: ifeq -170 -> 340
    //   513: iconst_5
    //   514: istore_1
    //   515: goto -173 -> 342
    //   518: aload_3
    //   519: ldc_w 629
    //   522: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   525: ifeq -185 -> 340
    //   528: bipush 6
    //   530: istore_1
    //   531: goto -189 -> 342
    //   534: aload_3
    //   535: ldc_w 631
    //   538: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   541: ifeq -201 -> 340
    //   544: bipush 7
    //   546: istore_1
    //   547: goto -205 -> 342
    //   550: aload_3
    //   551: ldc_w 633
    //   554: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   557: ifeq -217 -> 340
    //   560: bipush 8
    //   562: istore_1
    //   563: goto -221 -> 342
    //   566: aload_3
    //   567: ldc_w 635
    //   570: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   573: ifeq -233 -> 340
    //   576: bipush 9
    //   578: istore_1
    //   579: goto -237 -> 342
    //   582: aload_3
    //   583: ldc_w 637
    //   586: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   589: ifeq -249 -> 340
    //   592: bipush 10
    //   594: istore_1
    //   595: goto -253 -> 342
    //   598: aload_3
    //   599: ldc_w 639
    //   602: invokevirtual 337	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   605: ifeq -265 -> 340
    //   608: bipush 11
    //   610: istore_1
    //   611: goto -269 -> 342
    //   614: new 95	java/lang/StringBuilder
    //   617: dup
    //   618: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   621: ldc_w 558
    //   624: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: ldc_w 615
    //   630: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   633: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   636: astore_2
    //   637: goto -575 -> 62
    //   640: new 95	java/lang/StringBuilder
    //   643: dup
    //   644: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   647: ldc_w 558
    //   650: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   653: ldc_w 641
    //   656: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   659: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   662: astore_2
    //   663: goto -601 -> 62
    //   666: new 95	java/lang/StringBuilder
    //   669: dup
    //   670: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   673: ldc_w 558
    //   676: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   679: ldc_w 643
    //   682: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   685: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   688: astore_2
    //   689: goto -627 -> 62
    //   692: new 95	java/lang/StringBuilder
    //   695: dup
    //   696: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   699: ldc_w 558
    //   702: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   705: ldc_w 645
    //   708: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   711: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   714: astore_2
    //   715: goto -653 -> 62
    //   718: new 95	java/lang/StringBuilder
    //   721: dup
    //   722: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   725: ldc_w 558
    //   728: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   731: ldc_w 647
    //   734: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   737: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   740: astore_2
    //   741: goto -679 -> 62
    //   744: new 95	java/lang/StringBuilder
    //   747: dup
    //   748: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   751: ldc_w 558
    //   754: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   757: ldc_w 649
    //   760: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   763: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   766: astore_2
    //   767: goto -705 -> 62
    //   770: new 95	java/lang/StringBuilder
    //   773: dup
    //   774: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   777: ldc_w 558
    //   780: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   783: ldc_w 651
    //   786: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   789: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   792: astore_2
    //   793: goto -731 -> 62
    //   796: new 95	java/lang/StringBuilder
    //   799: dup
    //   800: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   803: ldc_w 558
    //   806: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   809: ldc_w 653
    //   812: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   815: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   818: astore_2
    //   819: goto -757 -> 62
    //   822: new 95	java/lang/StringBuilder
    //   825: dup
    //   826: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   829: ldc_w 558
    //   832: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   835: ldc_w 655
    //   838: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   841: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   844: astore_2
    //   845: goto -783 -> 62
    //   848: new 95	java/lang/StringBuilder
    //   851: dup
    //   852: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   855: ldc_w 558
    //   858: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   861: ldc_w 657
    //   864: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   867: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   870: astore_2
    //   871: goto -809 -> 62
    //   874: new 95	java/lang/StringBuilder
    //   877: dup
    //   878: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   881: ldc_w 558
    //   884: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   887: ldc_w 659
    //   890: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   893: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   896: astore_2
    //   897: goto -835 -> 62
    //   900: new 95	java/lang/StringBuilder
    //   903: dup
    //   904: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   907: ldc_w 558
    //   910: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   913: ldc_w 661
    //   916: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   922: astore_2
    //   923: goto -861 -> 62
    //   926: astore_0
    //   927: aload_3
    //   928: ldc_w 663
    //   931: invokevirtual 576	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   934: astore_0
    //   935: goto -859 -> 76
    //   938: astore_0
    //   939: aload_0
    //   940: invokevirtual 664	java/lang/Exception:printStackTrace	()V
    //   943: aconst_null
    //   944: astore_0
    //   945: goto -869 -> 76
    //   948: astore_0
    //   949: aload_0
    //   950: athrow
    //   951: aload_3
    //   952: ifnull +7 -> 959
    //   955: aload_3
    //   956: invokevirtual 595	java/io/BufferedReader:close	()V
    //   959: aload_0
    //   960: ifnull -794 -> 166
    //   963: aload_0
    //   964: invokevirtual 598	java/io/InputStream:close	()V
    //   967: goto -801 -> 166
    //   970: astore_0
    //   971: aload_0
    //   972: invokevirtual 483	java/io/IOException:printStackTrace	()V
    //   975: goto -809 -> 166
    //   978: astore_2
    //   979: aload_2
    //   980: invokevirtual 483	java/io/IOException:printStackTrace	()V
    //   983: goto -24 -> 959
    //   986: astore_2
    //   987: aload_2
    //   988: invokevirtual 483	java/io/IOException:printStackTrace	()V
    //   991: goto -833 -> 158
    //   994: astore_0
    //   995: aload_0
    //   996: invokevirtual 483	java/io/IOException:printStackTrace	()V
    //   999: goto -833 -> 166
    //   1002: astore_3
    //   1003: aconst_null
    //   1004: astore_2
    //   1005: aload_2
    //   1006: ifnull +7 -> 1013
    //   1009: aload_2
    //   1010: invokevirtual 595	java/io/BufferedReader:close	()V
    //   1013: aload_0
    //   1014: ifnull +7 -> 1021
    //   1017: aload_0
    //   1018: invokevirtual 598	java/io/InputStream:close	()V
    //   1021: aload_3
    //   1022: athrow
    //   1023: astore_2
    //   1024: aload_2
    //   1025: invokevirtual 483	java/io/IOException:printStackTrace	()V
    //   1028: goto -15 -> 1013
    //   1031: astore_0
    //   1032: aload_0
    //   1033: invokevirtual 483	java/io/IOException:printStackTrace	()V
    //   1036: goto -15 -> 1021
    //   1039: aconst_null
    //   1040: areturn
    //   1041: astore_3
    //   1042: goto -37 -> 1005
    //   1045: astore 4
    //   1047: aconst_null
    //   1048: astore_3
    //   1049: goto -906 -> 143
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1052	0	paramContext	Context
    //   1	610	1	i	int
    //   8	915	2	localObject1	Object
    //   978	2	2	localIOException1	IOException
    //   986	2	2	localIOException2	IOException
    //   1004	6	2	localObject2	Object
    //   1023	2	2	localIOException3	IOException
    //   18	938	3	localObject3	Object
    //   1002	20	3	localObject4	Object
    //   1041	1	3	localObject5	Object
    //   1048	1	3	localObject6	Object
    //   107	26	4	str	String
    //   141	5	4	localIOException4	IOException
    //   1045	1	4	localIOException5	IOException
    //   83	107	5	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   103	109	141	java/io/IOException
    //   116	123	141	java/io/IOException
    //   130	138	141	java/io/IOException
    //   70	76	926	java/lang/Exception
    //   927	935	938	java/lang/Exception
    //   70	76	948	finally
    //   927	935	948	finally
    //   939	943	948	finally
    //   963	967	970	java/io/IOException
    //   955	959	978	java/io/IOException
    //   154	158	986	java/io/IOException
    //   162	166	994	java/io/IOException
    //   85	101	1002	finally
    //   1009	1013	1023	java/io/IOException
    //   1017	1021	1031	java/io/IOException
    //   103	109	1041	finally
    //   116	123	1041	finally
    //   130	138	1041	finally
    //   145	150	1041	finally
    //   85	101	1045	java/io/IOException
  }
  
  public static String getSPTag(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageName();
      if ((paramContext.equalsIgnoreCase(ko.f)) || (paramContext.equalsIgnoreCase(ko.D)) || (paramContext.equalsIgnoreCase(ko.s)) || (paramContext.equalsIgnoreCase(ko.n))) {
        break label85;
      }
      if (paramContext.equalsIgnoreCase(ko.i)) {
        return "pip_localpush";
      }
      if (paramContext.equalsIgnoreCase(ko.u)) {
        return "photocollage_localpush";
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return "wantu_localpush";
    label85:
    return "starcam_localpush";
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
      if (localObject != null) {
        return (String)localObject + "/Download/";
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
    if (paramContext == null) {}
    while (new TimeStampUtil().minutesFromTimeToNow(paramContext) > paramInt * 60) {
      return false;
    }
    return true;
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
    if ((isDevAdb()) || (isDevBlack()) || (isDevRoot()) || (isDevNetwork())) {
      return "YES";
    }
    return "NO";
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
              break label80;
            }
            i = Integer.parseInt(str1);
            str1 = str3;
            if ((!TextUtils.isEmpty(str1)) && (i != -1)) {
              return true;
            }
          }
          else
          {
            str1 = Proxy.getHost(LocalPushHelpr.s_appContext);
            i = Proxy.getPort(LocalPushHelpr.s_appContext);
            continue;
          }
          return false;
        }
      }
      catch (Throwable localThrowable)
      {
        return true;
      }
      int i = 0;
      continue;
      label80:
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
    if (paramString == null) {}
    for (;;)
    {
      return false;
      try
      {
        paramString = new File(paramString);
        if (paramString != null)
        {
          boolean bool = paramString.exists();
          if (!bool) {}
        }
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          StaticFlurryEvent.logThrowable(paramString);
        }
      }
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
    return "" + l;
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
      paramContext.commit();
    }
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
      paramContext.commit();
    }
  }
  
  public static boolean setlasttest(String paramString)
  {
    try
    {
      SharedPreferences.Editor localEditor = LocalPushHelpr.s_appContext.getSharedPreferences(getSPTag(LocalPushHelpr.s_appContext), 4).edit();
      localEditor.putString("lasttest", paramString);
      try
      {
        localEditor.apply();
        return true;
      }
      catch (AbstractMethodError paramString)
      {
        for (;;)
        {
          localEditor.commit();
        }
      }
      return false;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      StaticFlurryEvent.logThrowable(paramString);
    }
  }
  
  public static boolean setlasttestconnection()
  {
    try
    {
      SharedPreferences.Editor localEditor = LocalPushHelpr.s_appContext.getSharedPreferences(getSPTag(LocalPushHelpr.s_appContext), 4).edit();
      localEditor.putString("lasttestconnection", getUTCTime());
      try
      {
        localEditor.apply();
        return true;
      }
      catch (AbstractMethodError localAbstractMethodError)
      {
        for (;;)
        {
          localEditor.commit();
        }
      }
      return false;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      StaticFlurryEvent.logThrowable(localThrowable);
    }
  }
  
  public static boolean settestconf(String paramString)
  {
    kq.a("test", paramString);
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
      for (;;)
      {
        StaticFlurryEvent.logThrowable(paramString);
      }
    }
    return true;
  }
  
  private static void settestincase(Context paramContext, String paramString)
  {
    for (;;)
    {
      try
      {
        int j = (int)(System.currentTimeMillis() / 1000L);
        SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(getSPTag(paramContext), 4).edit();
        paramContext = gettestincase(paramContext);
        paramString = paramString.split(",");
        int k = paramString.length;
        int i = 0;
        if (i < k)
        {
          String str = paramString[i];
          if (paramContext == "") {
            paramContext = str + ":" + String.valueOf(j);
          } else {
            paramContext = paramContext + " " + str + ":" + String.valueOf(j);
          }
        }
        else
        {
          localEditor.putString("testin_case", filterID(paramContext, j, 24));
          try
          {
            localEditor.apply();
            return;
          }
          catch (AbstractMethodError paramContext)
          {
            localEditor.commit();
            return;
          }
        }
        i += 1;
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
        StaticFlurryEvent.logThrowable(paramContext);
        return;
      }
    }
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
      for (;;)
      {
        try
        {
          paramContext.apply();
          return;
        }
        catch (AbstractMethodError localAbstractMethodError)
        {
          paramContext.commit();
        }
        localThrowable = localThrowable;
        localThrowable.printStackTrace();
        StaticFlurryEvent.logThrowable(localThrowable);
      }
    }
    paramContext = paramContext.getSharedPreferences(getSPTag(paramContext), 4).edit();
    paramContext.putString("local_push_ua", (String)localObject);
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
    catch (IllegalBlockSizeException paramString2)
    {
      StaticFlurryEvent.logThrowable(new Exception(paramString1));
      StaticFlurryEvent.logThrowable(paramString2);
      return "";
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      StaticFlurryEvent.logThrowable(paramString1);
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
      kq.a("test jni", paramString);
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
    setLastUsageTime(paramContext, String.valueOf(localTimeStampUtil.mYear) + "-" + String.valueOf(localTimeStampUtil.mMonth) + "-" + String.valueOf(localTimeStampUtil.mDay) + " " + String.valueOf(localTimeStampUtil.mHour) + ":" + String.valueOf(localTimeStampUtil.mMinute) + ":" + String.valueOf(localTimeStampUtil.mSecond));
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
    for (;;)
    {
      try
      {
        Iterator localIterator = LocalPushHelpr.s_appContext.getPackageManager().getInstalledApplications(128).iterator();
        int i = 0;
        int j = i;
        if (localIterator.hasNext())
        {
          j = ((ApplicationInfo)localIterator.next()).flags;
          if ((j & 0x1) == 0) {
            i += 1;
          }
        }
        else
        {
          return String.valueOf(j);
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        StaticFlurryEvent.logThrowable(localThrowable);
        j = 0;
      }
    }
  }
  
  static boolean validateOnlineJson(String paramString)
  {
    return true;
  }
}
