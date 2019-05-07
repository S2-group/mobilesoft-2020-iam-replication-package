package com.rd.lib.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class CoreUtils
{
  public static final int MOBILECONNECTED = 1;
  public static final String REFLESHACTION = "登录。注销action";
  public static final int UNCONNECTED = 0;
  public static final int UNKNOWCONNECTED = 3;
  public static final int VIDEO_SHARE_INPUT_TEXT_MAX_LENGTH = 95;
  public static final int WIFICONNECTED = 2;
  private static Context a;
  private static int b = 0;
  private static boolean c = false;
  private static String d = "";
  private static String e = "";
  private static int f = 0;
  private static float g = 1.0F;
  private static DisplayMetrics h;
  private static String i = "";
  private static String j = "";
  
  public CoreUtils() {}
  
  private static String a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int k = 0;
    while (k < paramArrayOfByte.length)
    {
      String str = Integer.toHexString(paramArrayOfByte[k] & 0xFF);
      if (str.length() == 1) {
        localStringBuilder.append('0');
      }
      localStringBuilder.append(str);
      k += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static void a()
  {
    h = new DisplayMetrics();
    ((WindowManager)a.getSystemService("window")).getDefaultDisplay().getMetrics(h);
    g = h.density;
  }
  
  /* Error */
  public static boolean assetRes2File(AssetManager paramAssetManager, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 4
    //   9: new 133	java/io/FileOutputStream
    //   12: dup
    //   13: aload_2
    //   14: invokespecial 136	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   17: astore_2
    //   18: sipush 1024
    //   21: newarray byte
    //   23: astore 4
    //   25: aload_0
    //   26: ifnonnull +16 -> 42
    //   29: aload_2
    //   30: invokevirtual 139	java/io/FileOutputStream:close	()V
    //   33: iconst_0
    //   34: ireturn
    //   35: astore_0
    //   36: aload_0
    //   37: invokevirtual 140	java/io/IOException:printStackTrace	()V
    //   40: iconst_0
    //   41: ireturn
    //   42: aload_0
    //   43: aload_1
    //   44: invokevirtual 146	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   47: astore_0
    //   48: aload_0
    //   49: aload 4
    //   51: invokevirtual 152	java/io/InputStream:read	([B)I
    //   54: istore_3
    //   55: iload_3
    //   56: iconst_m1
    //   57: if_icmpeq +14 -> 71
    //   60: aload_2
    //   61: aload 4
    //   63: iconst_0
    //   64: iload_3
    //   65: invokevirtual 156	java/io/FileOutputStream:write	([BII)V
    //   68: goto -20 -> 48
    //   71: aload_2
    //   72: invokevirtual 159	java/io/FileOutputStream:flush	()V
    //   75: aload_2
    //   76: invokevirtual 139	java/io/FileOutputStream:close	()V
    //   79: aload_0
    //   80: invokevirtual 160	java/io/InputStream:close	()V
    //   83: aload_2
    //   84: invokevirtual 139	java/io/FileOutputStream:close	()V
    //   87: goto +75 -> 162
    //   90: astore_0
    //   91: goto +73 -> 164
    //   94: astore_1
    //   95: aload_2
    //   96: astore_0
    //   97: goto +20 -> 117
    //   100: astore_1
    //   101: aload_2
    //   102: astore_0
    //   103: goto +36 -> 139
    //   106: astore_0
    //   107: aload 4
    //   109: astore_2
    //   110: goto +54 -> 164
    //   113: astore_1
    //   114: aload 5
    //   116: astore_0
    //   117: aload_0
    //   118: astore 4
    //   120: aload_1
    //   121: invokevirtual 140	java/io/IOException:printStackTrace	()V
    //   124: aload_0
    //   125: ifnull +37 -> 162
    //   128: aload_0
    //   129: invokevirtual 139	java/io/FileOutputStream:close	()V
    //   132: goto +30 -> 162
    //   135: astore_1
    //   136: aload 6
    //   138: astore_0
    //   139: aload_0
    //   140: astore 4
    //   142: aload_1
    //   143: invokevirtual 161	java/io/FileNotFoundException:printStackTrace	()V
    //   146: aload_0
    //   147: ifnull +15 -> 162
    //   150: aload_0
    //   151: invokevirtual 139	java/io/FileOutputStream:close	()V
    //   154: goto +8 -> 162
    //   157: astore_0
    //   158: aload_0
    //   159: invokevirtual 140	java/io/IOException:printStackTrace	()V
    //   162: iconst_1
    //   163: ireturn
    //   164: aload_2
    //   165: ifnull +15 -> 180
    //   168: aload_2
    //   169: invokevirtual 139	java/io/FileOutputStream:close	()V
    //   172: goto +8 -> 180
    //   175: astore_1
    //   176: aload_1
    //   177: invokevirtual 140	java/io/IOException:printStackTrace	()V
    //   180: aload_0
    //   181: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	182	0	paramAssetManager	AssetManager
    //   0	182	1	paramString1	String
    //   0	182	2	paramString2	String
    //   54	11	3	k	int
    //   7	134	4	localObject1	Object
    //   1	114	5	localObject2	Object
    //   4	133	6	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   29	33	35	java/io/IOException
    //   18	25	90	finally
    //   42	48	90	finally
    //   48	55	90	finally
    //   60	68	90	finally
    //   71	83	90	finally
    //   18	25	94	java/io/IOException
    //   42	48	94	java/io/IOException
    //   48	55	94	java/io/IOException
    //   60	68	94	java/io/IOException
    //   71	83	94	java/io/IOException
    //   18	25	100	java/io/FileNotFoundException
    //   42	48	100	java/io/FileNotFoundException
    //   48	55	100	java/io/FileNotFoundException
    //   60	68	100	java/io/FileNotFoundException
    //   71	83	100	java/io/FileNotFoundException
    //   9	18	106	finally
    //   120	124	106	finally
    //   142	146	106	finally
    //   9	18	113	java/io/IOException
    //   9	18	135	java/io/FileNotFoundException
    //   83	87	157	java/io/IOException
    //   128	132	157	java/io/IOException
    //   150	154	157	java/io/IOException
    //   168	172	175	java/io/IOException
  }
  
  private static int b()
  {
    return a.getResources().getConfiguration().orientation;
  }
  
  public static boolean checkFileExit(String paramString)
  {
    return new File(paramString).exists();
  }
  
  public static int checkNetworkInfo(Context paramContext)
  {
    int k = 3;
    if (paramContext == null) {
      return 3;
    }
    Object localObject = (ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity");
    paramContext = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if ((paramContext != null) && (paramContext.isAvailable()))
    {
      paramContext = NetworkInfo.State.UNKNOWN;
      NetworkInfo.State localState = NetworkInfo.State.UNKNOWN;
      NetworkInfo localNetworkInfo = ((ConnectivityManager)localObject).getNetworkInfo(0);
      if (localNetworkInfo != null) {
        paramContext = localNetworkInfo.getState();
      }
      localObject = ((ConnectivityManager)localObject).getNetworkInfo(1);
      if (localObject != null) {
        localState = ((NetworkInfo)localObject).getState();
      }
      if (localState.equals(NetworkInfo.State.CONNECTED)) {
        return 2;
      }
      if (paramContext.equals(NetworkInfo.State.CONNECTED)) {
        k = 1;
      }
      return k;
    }
    return 0;
  }
  
  public static boolean checkValidExtVideoFile(String paramString)
  {
    return (paramString.endsWith(".mp4")) || (paramString.endsWith(".3gp")) || (paramString.endsWith(".3gpp")) || (paramString.endsWith(".3gpp2"));
  }
  
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static int dpToPixel(float paramFloat)
  {
    return Math.round(g * paramFloat);
  }
  
  public static long getAssetResourceLen(AssetManager paramAssetManager, String paramString)
    throws IOException
  {
    if (paramAssetManager == null) {
      return -1L;
    }
    paramAssetManager = paramAssetManager.open(paramString);
    long l = paramAssetManager.available();
    paramAssetManager.close();
    return l;
  }
  
  public static String getAssetText(AssetManager paramAssetManager, String paramString)
  {
    try
    {
      paramAssetManager = new BufferedReader(new InputStreamReader(paramAssetManager.open(paramString)));
      paramString = new StringBuffer();
      for (;;)
      {
        String str = paramAssetManager.readLine();
        if (str == null) {
          break;
        }
        paramString.append(str);
      }
      paramAssetManager = paramString.toString();
      return paramAssetManager;
    }
    catch (Exception paramAssetManager)
    {
      paramAssetManager.printStackTrace();
    }
    return "";
  }
  
  /* Error */
  public static String getCurrentProcessName(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: new 72	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   9: astore_2
    //   10: aload_2
    //   11: ldc_w 289
    //   14: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: aload_2
    //   19: invokestatic 294	android/os/Process:myPid	()I
    //   22: invokevirtual 297	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload_2
    //   27: ldc_w 299
    //   30: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: new 265	java/io/BufferedReader
    //   37: dup
    //   38: new 267	java/io/InputStreamReader
    //   41: dup
    //   42: new 301	java/io/FileInputStream
    //   45: dup
    //   46: aload_2
    //   47: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokespecial 302	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   53: ldc_w 304
    //   56: invokespecial 307	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   59: invokespecial 273	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   62: astore_2
    //   63: new 72	java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   70: astore_0
    //   71: aload_2
    //   72: invokevirtual 309	java/io/BufferedReader:read	()I
    //   75: istore_1
    //   76: iload_1
    //   77: ifle +13 -> 90
    //   80: aload_0
    //   81: iload_1
    //   82: i2c
    //   83: invokevirtual 89	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: goto -16 -> 71
    //   90: aload_0
    //   91: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: astore_0
    //   95: aload_2
    //   96: invokevirtual 310	java/io/BufferedReader:close	()V
    //   99: aload_0
    //   100: areturn
    //   101: astore_2
    //   102: aload_2
    //   103: invokevirtual 140	java/io/IOException:printStackTrace	()V
    //   106: aload_0
    //   107: areturn
    //   108: aload_2
    //   109: invokevirtual 310	java/io/BufferedReader:close	()V
    //   112: aconst_null
    //   113: areturn
    //   114: astore_0
    //   115: aload_0
    //   116: invokevirtual 140	java/io/IOException:printStackTrace	()V
    //   119: aconst_null
    //   120: areturn
    //   121: astore_0
    //   122: goto +8 -> 130
    //   125: astore_3
    //   126: aload_0
    //   127: astore_2
    //   128: aload_3
    //   129: astore_0
    //   130: aload_2
    //   131: ifnull +15 -> 146
    //   134: aload_2
    //   135: invokevirtual 310	java/io/BufferedReader:close	()V
    //   138: goto +8 -> 146
    //   141: astore_2
    //   142: aload_2
    //   143: invokevirtual 140	java/io/IOException:printStackTrace	()V
    //   146: aload_0
    //   147: athrow
    //   148: astore_0
    //   149: aconst_null
    //   150: areturn
    //   151: astore_0
    //   152: aconst_null
    //   153: areturn
    //   154: astore_0
    //   155: goto -47 -> 108
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	158	0	paramContext	Context
    //   75	7	1	k	int
    //   9	87	2	localObject1	Object
    //   101	8	2	localIOException1	IOException
    //   127	8	2	localContext	Context
    //   141	2	2	localIOException2	IOException
    //   125	4	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   95	99	101	java/io/IOException
    //   108	112	114	java/io/IOException
    //   63	71	121	finally
    //   71	76	121	finally
    //   80	87	121	finally
    //   90	95	121	finally
    //   2	63	125	finally
    //   134	138	141	java/io/IOException
    //   2	63	148	java/io/UnsupportedEncodingException
    //   2	63	151	java/io/FileNotFoundException
    //   71	76	154	java/io/IOException
    //   80	87	154	java/io/IOException
  }
  
  public static boolean getDebuggable()
  {
    return c;
  }
  
  public static String getDeviceInfo(Context paramContext)
  {
    if (TextUtils.isEmpty(d))
    {
      Object localObject2 = null;
      try
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          String str1 = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
          localObject2 = str1;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        String str2 = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        localJSONObject.put("mac", str2);
        Object localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2)) {
          localObject1 = str2;
        }
        localObject2 = localObject1;
        if (TextUtils.isEmpty(localObject1)) {
          localObject2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
        }
        localJSONObject.put("device_id", localObject2);
        paramContext = MessageDigest.getInstance("MD5");
        paramContext.update(localJSONObject.toString().getBytes());
        d = a(paramContext.digest());
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        d = "";
      }
    }
    return d;
  }
  
  public static long getFreeMemory(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    paramContext.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem / 1024L;
  }
  
  public static InetAddress getLocalIpAddress(Context paramContext)
  {
    int k = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getIpAddress();
    try
    {
      paramContext = InetAddress.getByName(String.format("%d.%d.%d.%d", new Object[] { Integer.valueOf(k & 0xFF), Integer.valueOf(k >> 8 & 0xFF), Integer.valueOf(k >> 16 & 0xFF), Integer.valueOf(k >> 24 & 0xFF) }));
      return paramContext;
    }
    catch (UnknownHostException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getMd5(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      try
      {
        ((MessageDigest)localObject).reset();
        ((MessageDigest)localObject).update(paramString.getBytes());
        paramString = (String)localObject;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException2)
      {
        paramString = (String)localObject;
        localObject = localNoSuchAlgorithmException2;
      }
      localNoSuchAlgorithmException1.printStackTrace();
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException1)
    {
      paramString = null;
    }
    paramString = paramString.digest();
    StringBuffer localStringBuffer = new StringBuffer();
    int k = 0;
    while (k < paramString.length)
    {
      if (Integer.toHexString(paramString[k] & 0xFF).length() == 1)
      {
        localStringBuffer.append("0");
        localStringBuffer.append(Integer.toHexString(paramString[k] & 0xFF));
      }
      else
      {
        localStringBuffer.append(Integer.toHexString(paramString[k] & 0xFF));
      }
      k += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static String getMetadata(Context paramContext, String paramString)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
      {
        paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128);
        if ((paramContext != null) && (paramContext.metaData != null))
        {
          paramContext = paramContext.metaData.getString(paramString);
          return paramContext;
        }
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static DisplayMetrics getMetrics()
  {
    int k = b();
    if (b != k)
    {
      a();
      b = k;
    }
    return h;
  }
  
  public static float getPixelDensity()
  {
    return g;
  }
  
  public static String getReleaseChannel(Context paramContext)
  {
    if (TextUtils.isEmpty(j))
    {
      paramContext = getMetadata(paramContext, "UMENG_CHANNEL");
      if (!TextUtils.isEmpty(paramContext))
      {
        j = paramContext;
        return j;
      }
      return "官网";
    }
    return j;
  }
  
  public static int getSameAspectRatioHeight(Window paramWindow, int paramInt)
  {
    Rect localRect = new Rect();
    paramWindow.getDecorView().getWindowVisibleDisplayFrame(localRect);
    return paramInt * localRect.width() / localRect.height();
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    try
    {
      Class localClass = Class.forName("com.android.internal.R$dimen");
      Object localObject = localClass.newInstance();
      int k = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
      k = paramContext.getResources().getDimensionPixelSize(k);
      return k;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int getTargetSdk(Context paramContext)
  {
    int k = 0;
    if (paramContext == null) {
      return 0;
    }
    paramContext = paramContext.getApplicationInfo();
    if (paramContext != null) {
      k = paramContext.targetSdkVersion;
    }
    return k;
  }
  
  public static String getVersionCode(Context paramContext)
  {
    if (TextUtils.isEmpty(e)) {
      try
      {
        e = String.valueOf(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode);
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        e = "";
      }
    }
    return e;
  }
  
  public static String getVersionName(Context paramContext)
  {
    if (TextUtils.isEmpty(i)) {
      i = a(paramContext);
    }
    return i;
  }
  
  public static int getVersion_Code(Context paramContext)
  {
    if (f == 0)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      try
      {
        f = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        f = 0;
      }
    }
    return f;
  }
  
  @SuppressLint({"InlinedApi"})
  public static Intent gotoAppInfo(String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("package:");
      localStringBuilder.append(paramString);
      paramString = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse(localStringBuilder.toString()));
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  @SuppressLint({"InlinedApi"})
  public static void gotoAppInfo(Context paramContext, String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("package:");
      localStringBuilder.append(paramString);
      paramContext.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
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
  
  public static boolean hasGingerbreadMR1()
  {
    return Build.VERSION.SDK_INT >= 10;
  }
  
  public static boolean hasHoneycomb()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean hasHoneycombMR1()
  {
    return Build.VERSION.SDK_INT >= 12;
  }
  
  public static boolean hasIceCreamSandwich()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  public static boolean hasJELLY_BEAN_MR2()
  {
    return Build.VERSION.SDK_INT >= 18;
  }
  
  public static boolean hasJellyBean()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean hasKitKat()
  {
    return Build.VERSION.SDK_INT >= 19;
  }
  
  public static boolean hasL()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static boolean hasM()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
  
  public static boolean hasN()
  {
    return Build.VERSION.SDK_INT >= 24;
  }
  
  public static void init(Context paramContext)
  {
    a = paramContext;
    boolean bool;
    if ((a.getApplicationInfo().flags & 0x2) == 2) {
      bool = true;
    } else {
      bool = false;
    }
    c = bool;
    a();
    b = b();
  }
  
  public static boolean installApp(Context paramContext, String paramString)
  {
    try
    {
      paramString = installAppIntent(paramString);
      if ((paramString != null) && (paramContext != null)) {
        paramContext.startActivity(paramString);
      }
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static Intent installAppIntent(String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setFlags(268435456);
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
      return localIntent;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int k = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (k < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(k)).packageName);
        k += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static boolean isMainProcess(Context paramContext)
  {
    paramContext = getCurrentProcessName(paramContext);
    if (TextUtils.isEmpty(paramContext)) {
      return true;
    }
    return paramContext.indexOf(":") == -1;
  }
  
  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static boolean startApp(Context paramContext, String paramString)
  {
    try
    {
      new Intent();
      paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.setFlags(337641472);
      paramContext.startActivity(paramString);
      return true;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return false;
  }
}
