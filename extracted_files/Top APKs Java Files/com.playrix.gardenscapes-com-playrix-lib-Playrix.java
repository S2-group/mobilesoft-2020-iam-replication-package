package com.playrix.lib;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.Environment;
import android.os.Process;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.safedk.android.analytics.StartTimeStats;
import com.safedk.android.internal.DexBridge;
import com.safedk.android.utils.Logger;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Playrix
{
  private static final double PHABLET_DIAGONAL_MIN = 5.5D;
  public static final boolean USE_SDCARD_WORK_DIR = false;
  private static ActivityManager actMgr;
  private static String advertisingId;
  private static final Object advertisingIdlocker = new Object();
  private static ConnectivityManager connMgr;
  private static volatile boolean externalCacheDir;
  private static final ThreadLocal<String> lastError = new ThreadLocal();
  private static WeakReference<PlayrixActivity> mActivity;
  private static Application mContext;
  private static GLSurfaceViewV17.Scale2D mResolutionScale = new GLSurfaceViewV17.Scale2D();
  private static WindowManager winMgr;
  
  public Playrix() {}
  
  public static void ClearLastError()
  {
    lastError.set(null);
  }
  
  public static void CloseApplication()
  {
    PlayrixActivity localPlayrixActivity = getActivity();
    if (localPlayrixActivity != null) {
      localPlayrixActivity.finish();
    }
    System.exit(0);
  }
  
  public static long GetAvailableSpace(String paramString)
  {
    
    try
    {
      long l = new File(paramString).getFreeSpace();
      return l;
    }
    catch (Exception paramString)
    {
      SetLastError(paramString.toString());
    }
    return -1L;
  }
  
  public static int GetCurrentThreadPriority()
  {
    try
    {
      int i = Process.getThreadPriority(Process.myTid());
      return i;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return 64536;
  }
  
  public static String GetLastError()
  {
    return (String)lastError.get();
  }
  
  public static boolean SetCurrentThreadPriority(int paramInt)
  {
    try
    {
      Process.setThreadPriority(paramInt);
      return true;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static void SetLastError(String paramString)
  {
    lastError.set(paramString);
  }
  
  private static String capitalize(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    char[] arrayOfChar = paramString.toCharArray();
    int k = arrayOfChar.length;
    paramString = "";
    int j = 0;
    int i = 1;
    while (j < k)
    {
      char c = arrayOfChar[j];
      StringBuilder localStringBuilder;
      if ((i != 0) && (Character.isLetter(c)))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(Character.toUpperCase(c));
        paramString = localStringBuilder.toString();
        i = 0;
      }
      else
      {
        if (Character.isWhitespace(c)) {
          i = 1;
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(c);
        paramString = localStringBuilder.toString();
      }
      j += 1;
    }
    return paramString;
  }
  
  private static boolean checkConnectedNetworkDeprecated(int paramInt)
  {
    NetworkInfo localNetworkInfo = connMgr.getNetworkInfo(paramInt);
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public static boolean containsSharedPreferencesValue(String paramString)
  {
    SharedPreferences localSharedPreferences = getPreferences();
    if (localSharedPreferences != null) {
      return localSharedPreferences.contains(paramString);
    }
    return false;
  }
  
  public static void executeInGlThread(long paramLong)
  {
    runOnGLThread(new Runnable()
    {
      public final void run()
      {
        Playrix.nativeExecuteInGlThread(this.val$func);
      }
    });
  }
  
  /* Error */
  public static String findMatchedPackages(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_2
    //   2: invokestatic 224	java/util/regex/Pattern:compile	(Ljava/lang/String;I)Ljava/util/regex/Pattern;
    //   5: astore 5
    //   7: new 160	java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   14: astore_0
    //   15: getstatic 130	com/playrix/lib/Playrix:mContext	Landroid/app/Application;
    //   18: ifnonnull +13 -> 31
    //   21: ldc -30
    //   23: invokestatic 231	com/playrix/lib/Logger:logError	(Ljava/lang/String;)V
    //   26: aload_0
    //   27: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: areturn
    //   31: getstatic 130	com/playrix/lib/Playrix:mContext	Landroid/app/Application;
    //   34: invokevirtual 237	android/app/Application:getPackageManager	()Landroid/content/pm/PackageManager;
    //   37: astore 6
    //   39: iload_1
    //   40: ifeq +335 -> 375
    //   43: getstatic 243	android/os/Build$VERSION:SDK_INT	I
    //   46: istore_3
    //   47: sipush 8192
    //   50: istore_3
    //   51: goto +3 -> 54
    //   54: aload 6
    //   56: iload_3
    //   57: invokevirtual 249	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   60: astore 7
    //   62: aload 7
    //   64: ifnonnull +8 -> 72
    //   67: aload_0
    //   68: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: areturn
    //   72: aload 7
    //   74: invokeinterface 255 1 0
    //   79: astore 7
    //   81: aload 7
    //   83: invokeinterface 260 1 0
    //   88: ifeq +277 -> 365
    //   91: aload 7
    //   93: invokeinterface 263 1 0
    //   98: checkcast 265	android/content/pm/PackageInfo
    //   101: astore 8
    //   103: aload 5
    //   105: aload 8
    //   107: getfield 268	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   110: invokevirtual 272	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   113: invokevirtual 277	java/util/regex/Matcher:find	()Z
    //   116: ifeq +34 -> 150
    //   119: iload_2
    //   120: ifne +24 -> 144
    //   123: aload_0
    //   124: ldc_w 279
    //   127: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: aload_0
    //   132: aload 8
    //   134: getfield 268	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   137: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: goto +9 -> 150
    //   144: aload 8
    //   146: getfield 268	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   149: areturn
    //   150: aload 8
    //   152: getfield 283	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   155: ifnull -74 -> 81
    //   158: getstatic 243	android/os/Build$VERSION:SDK_INT	I
    //   161: bipush 17
    //   163: if_icmplt +41 -> 204
    //   166: aload 8
    //   168: getfield 283	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   171: getfield 288	android/content/pm/ApplicationInfo:flags	I
    //   174: ldc_w 289
    //   177: iand
    //   178: ldc_w 289
    //   181: if_icmpne -100 -> 81
    //   184: aload 8
    //   186: getfield 283	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   189: getfield 288	android/content/pm/ApplicationInfo:flags	I
    //   192: istore_3
    //   193: iload_3
    //   194: ldc_w 290
    //   197: iand
    //   198: ldc_w 290
    //   201: if_icmpeq -120 -> 81
    //   204: aload 6
    //   206: aload 8
    //   208: getfield 268	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   211: iconst_1
    //   212: invokevirtual 294	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   215: astore 9
    //   217: aload 9
    //   219: getfield 298	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   222: ifnull -141 -> 81
    //   225: aload 9
    //   227: getfield 298	android/content/pm/PackageInfo:activities	[Landroid/content/pm/ActivityInfo;
    //   230: astore 10
    //   232: aload 10
    //   234: arraylength
    //   235: istore 4
    //   237: iconst_0
    //   238: istore_3
    //   239: iload_3
    //   240: iload 4
    //   242: if_icmpge -161 -> 81
    //   245: aload 10
    //   247: iload_3
    //   248: aaload
    //   249: astore 9
    //   251: aload 5
    //   253: aload 9
    //   255: getfield 303	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   258: invokevirtual 272	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   261: invokevirtual 277	java/util/regex/Matcher:find	()Z
    //   264: ifeq +78 -> 342
    //   267: iload_2
    //   268: ifne +24 -> 292
    //   271: aload_0
    //   272: ldc_w 305
    //   275: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: pop
    //   279: aload_0
    //   280: aload 9
    //   282: getfield 303	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   285: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: pop
    //   289: goto +53 -> 342
    //   292: new 160	java/lang/StringBuilder
    //   295: dup
    //   296: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   299: astore 10
    //   301: aload 10
    //   303: aload 8
    //   305: getfield 268	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   308: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: pop
    //   312: aload 10
    //   314: ldc_w 307
    //   317: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: pop
    //   321: aload 10
    //   323: aload 9
    //   325: getfield 303	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   328: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: pop
    //   332: aload 10
    //   334: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   337: astore 8
    //   339: aload 8
    //   341: areturn
    //   342: iload_3
    //   343: iconst_1
    //   344: iadd
    //   345: istore_3
    //   346: goto -107 -> 239
    //   349: astore 5
    //   351: ldc_w 309
    //   354: aload 5
    //   356: invokestatic 313	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   359: invokevirtual 316	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   362: invokestatic 319	com/playrix/lib/Logger:logDebug	(Ljava/lang/String;)V
    //   365: aload_0
    //   366: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   369: areturn
    //   370: astore 8
    //   372: goto -291 -> 81
    //   375: iconst_0
    //   376: istore_3
    //   377: goto -323 -> 54
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	380	0	paramString	String
    //   0	380	1	paramBoolean1	boolean
    //   0	380	2	paramBoolean2	boolean
    //   46	331	3	i	int
    //   235	8	4	j	int
    //   5	247	5	localPattern	java.util.regex.Pattern
    //   349	6	5	localThrowable	Throwable
    //   37	168	6	localPackageManager	PackageManager
    //   60	32	7	localObject1	Object
    //   101	239	8	localObject2	Object
    //   370	1	8	localNameNotFoundException	PackageManager.NameNotFoundException
    //   215	109	9	localPackageInfo	PackageInfo
    //   230	103	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   31	39	349	java/lang/Throwable
    //   43	47	349	java/lang/Throwable
    //   54	62	349	java/lang/Throwable
    //   67	72	349	java/lang/Throwable
    //   72	81	349	java/lang/Throwable
    //   81	119	349	java/lang/Throwable
    //   123	141	349	java/lang/Throwable
    //   144	150	349	java/lang/Throwable
    //   150	193	349	java/lang/Throwable
    //   204	237	349	java/lang/Throwable
    //   251	267	349	java/lang/Throwable
    //   271	289	349	java/lang/Throwable
    //   292	339	349	java/lang/Throwable
    //   204	237	370	android/content/pm/PackageManager$NameNotFoundException
    //   204	237	370	java/lang/RuntimeException
    //   251	267	370	android/content/pm/PackageManager$NameNotFoundException
    //   251	267	370	java/lang/RuntimeException
    //   271	289	370	android/content/pm/PackageManager$NameNotFoundException
    //   271	289	370	java/lang/RuntimeException
    //   292	339	370	android/content/pm/PackageManager$NameNotFoundException
    //   292	339	370	java/lang/RuntimeException
  }
  
  public static PlayrixActivity getActivity()
  {
    if (mActivity != null) {
      return (PlayrixActivity)mActivity.get();
    }
    return null;
  }
  
  public static String getAdvertisingId()
  {
    synchronized (advertisingIdlocker)
    {
      String str = advertisingId;
      return str;
    }
  }
  
  @TargetApi(21)
  private static int getAllConnectedNetworksDeprecated()
  {
    Network[] arrayOfNetwork = connMgr.getAllNetworks();
    int i = 0;
    int k = 0;
    if (arrayOfNetwork != null)
    {
      int m = arrayOfNetwork.length;
      for (int j = 0; k < m; j = i)
      {
        Object localObject = arrayOfNetwork[k];
        localObject = connMgr.getNetworkInfo((Network)localObject);
        i = j;
        if (localObject != null)
        {
          i = j;
          if (((NetworkInfo)localObject).isConnected())
          {
            ((NetworkInfo)localObject).getType();
            switch (((NetworkInfo)localObject).getType())
            {
            default: 
              i = j;
              break;
            case 9: 
              i = j | 0x4;
              break;
            case 7: 
              i = j | 0x8;
              break;
            case 1: 
            case 6: 
              i = j | 0x1;
              break;
            case 0: 
              i = j | 0x2;
            }
          }
        }
        k += 1;
      }
      i = j;
    }
    return i;
  }
  
  public static String getAndroidId()
  {
    return Settings.Secure.getString(mContext.getContentResolver(), "android_id");
  }
  
  public static String getAppName()
  {
    PackageManager localPackageManager = mContext.getPackageManager();
    try
    {
      localObject = localPackageManager.getApplicationInfo(mContext.getPackageName(), 0);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = null;
    if (localObject != null) {
      localObject = localPackageManager.getApplicationLabel((ApplicationInfo)localObject);
    } else {
      localObject = "(unknown)";
    }
    return (String)localObject;
  }
  
  public static String getAppPath()
  {
    return mContext.getApplicationInfo().sourceDir;
  }
  
  public static String getAppVersion()
  {
    Object localObject = mContext.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(mContext.getPackageName(), 0).versionName;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static int getAppVersionCode()
  {
    PackageManager localPackageManager = mContext.getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(mContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return 0;
  }
  
  public static Application getApplication()
  {
    return mContext;
  }
  
  public static String getCachesPath()
  {
    if (externalCacheDir)
    {
      File localFile = mContext.getExternalCacheDir();
      if (localFile != null) {
        return localFile.getAbsolutePath();
      }
      return null;
    }
    return mContext.getCacheDir().getAbsolutePath();
  }
  
  public static int getConnectedNetworks()
  {
    if (Build.VERSION.SDK_INT >= 27)
    {
      Object localObject = connMgr.getActiveNetwork();
      int j = 0;
      int i = j;
      if (localObject != null)
      {
        localObject = connMgr.getNetworkCapabilities((Network)localObject);
        i = j;
        if (localObject != null)
        {
          j = 1;
          i = j;
          if (!((NetworkCapabilities)localObject).hasTransport(1)) {
            if (((NetworkCapabilities)localObject).hasTransport(5)) {
              i = j;
            } else {
              i = 0;
            }
          }
          j = i;
          if (((NetworkCapabilities)localObject).hasTransport(0)) {
            j = i | 0x2;
          }
          if (((NetworkCapabilities)localObject).hasTransport(3)) {
            j |= 0x4;
          }
          i = j;
          if (((NetworkCapabilities)localObject).hasTransport(2)) {
            i = j | 0x8;
          }
        }
      }
      return i;
    }
    return getConnectedNetworksDeprecated();
  }
  
  public static int getConnectedNetworksDeprecated()
  {
    if (Build.VERSION.SDK_INT < 21)
    {
      int j = 1;
      int i = j;
      if (!checkConnectedNetworkDeprecated(1)) {
        if (checkConnectedNetworkDeprecated(6)) {
          i = j;
        } else {
          i = 0;
        }
      }
      j = i;
      if (checkConnectedNetworkDeprecated(0)) {
        j = i | 0x2;
      }
      i = j;
      if (checkConnectedNetworkDeprecated(9)) {
        i = j | 0x4;
      }
      j = i;
      if (checkConnectedNetworkDeprecated(7)) {
        j = i | 0x8;
      }
      return j;
    }
    return getAllConnectedNetworksDeprecated();
  }
  
  public static Context getContext()
  {
    return mContext;
  }
  
  public static String getDeviceLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String getDeviceManufacturer()
  {
    String str = Build.MANUFACTURER;
    if (str.equalsIgnoreCase("HTC")) {
      return "HTC";
    }
    return capitalize(str);
  }
  
  public static String getDeviceModel()
  {
    String str2 = Build.MODEL;
    String str3 = Build.MANUFACTURER;
    String str1 = str2;
    if (str2.startsWith(str3)) {
      str1 = str2.substring(str3.length()).trim();
    }
    return str1;
  }
  
  @TargetApi(9)
  public static String getDeviceSerialNumber()
  {
    if (Build.VERSION.SDK_INT >= 9) {
      return Build.SERIAL;
    }
    return "";
  }
  
  public static double getDisplayDiagonal()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    winMgr.getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f1 = localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi;
    float f2 = localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi;
    return Math.sqrt(f1 * f1 + f2 * f2);
  }
  
  public static int getDisplayPpi()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    winMgr.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return (int)((localDisplayMetrics.xdpi + localDisplayMetrics.ydpi) / 2.0F);
  }
  
  public static Point getDisplaySyze()
  {
    Point localPoint = new Point();
    if (Build.VERSION.SDK_INT >= 17)
    {
      winMgr.getDefaultDisplay().getRealSize(localPoint);
      return localPoint;
    }
    winMgr.getDefaultDisplay().getSize(localPoint);
    return localPoint;
  }
  
  public static String getExceptionDesription(Throwable paramThrowable)
  {
    if (paramThrowable == null) {
      return "null";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      localStringBuilder.append(paramThrowable.getMessage());
      localStringBuilder.append(" : ");
      StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
      int j = arrayOfStackTraceElement.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(arrayOfStackTraceElement[i]);
        localStringBuilder.append("->");
        i += 1;
      }
      paramThrowable = paramThrowable.getCause();
      if (paramThrowable == null) {
        break;
      }
      localStringBuilder.append("caused by : ");
    }
    return localStringBuilder.toString();
  }
  
  public static String getExternalStoragePath()
  {
    File localFile = mContext.getExternalFilesDir(null);
    if (localFile != null) {
      return localFile.getAbsolutePath();
    }
    return null;
  }
  
  public static String getInstaller()
  {
    try
    {
      String str = mContext.getPackageManager().getInstallerPackageName(mContext.getPackageName());
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String getInternalStoragePath()
  {
    return mContext.getFilesDir().getAbsolutePath();
  }
  
  public static String getInternetConnectionType()
  {
    if (Build.VERSION.SDK_INT >= 27)
    {
      Object localObject = connMgr.getActiveNetwork();
      if (localObject != null)
      {
        localObject = connMgr.getNetworkCapabilities((Network)localObject);
        if (localObject != null)
        {
          if (((NetworkCapabilities)localObject).hasTransport(3)) {
            return "Ethernet";
          }
          if ((!((NetworkCapabilities)localObject).hasTransport(1)) && (!((NetworkCapabilities)localObject).hasTransport(5)))
          {
            if (((NetworkCapabilities)localObject).hasTransport(2)) {
              return "Bluetooth";
            }
            if (((NetworkCapabilities)localObject).hasTransport(6)) {
              return "LoWPAN";
            }
            if (((NetworkCapabilities)localObject).hasTransport(4)) {
              return "VPN";
            }
            if (((NetworkCapabilities)localObject).hasTransport(0)) {
              return "Mobile";
            }
          }
          else
          {
            return "WiFi";
          }
        }
      }
      return "No";
    }
    return getInternetConnectionTypeDeprecated();
  }
  
  private static String getInternetConnectionTypeDeprecated()
  {
    NetworkInfo localNetworkInfo = connMgr.getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
    {
      int i = localNetworkInfo.getType();
      if ((i != 1) && (i != 6))
      {
        if (i == 9) {
          return "Ethernet";
        }
        if (i == 7) {
          return "Bluetooth";
        }
        return "Mobile";
      }
      return "WiFi";
    }
    return "No";
  }
  
  public static String getLocaleCountry()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static String getMacAddress(String paramString)
  {
    try
    {
      Object localObject = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      while (((Iterator)localObject).hasNext())
      {
        NetworkInterface localNetworkInterface = (NetworkInterface)((Iterator)localObject).next();
        if ((paramString == null) || (localNetworkInterface.getName().equalsIgnoreCase(paramString)))
        {
          paramString = localNetworkInterface.getHardwareAddress();
          if (paramString == null) {
            return null;
          }
          localObject = new StringBuilder();
          int i = 0;
          while (i < paramString.length)
          {
            if (((StringBuilder)localObject).length() > 0) {
              ((StringBuilder)localObject).append(":");
            }
            ((StringBuilder)localObject).append(String.format("%02X", new Object[] { Byte.valueOf(paramString[i]) }));
            i += 1;
          }
          paramString = ((StringBuilder)localObject).toString();
          return paramString;
        }
      }
      return null;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static Debug.MemoryInfo getMemoryInfo()
  {
    int i = Process.myPid();
    Debug.MemoryInfo[] arrayOfMemoryInfo = actMgr.getProcessMemoryInfo(new int[] { i });
    if ((arrayOfMemoryInfo != null) && (arrayOfMemoryInfo.length > 0)) {
      return arrayOfMemoryInfo[0];
    }
    return null;
  }
  
  public static String getOsVer()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static SharedPreferences getPreferences()
  {
    if (mContext == null) {
      return null;
    }
    return PreferenceManager.getDefaultSharedPreferences(mContext);
  }
  
  public static long getProcSize()
  {
    int i = Process.myPid();
    return actMgr.getProcessMemoryInfo(new int[] { i })[0].getTotalPss();
  }
  
  public static Point getRealScreenSize()
  {
    Point localPoint = new Point();
    Object localObject = getActivity();
    if (localObject == null) {
      return localPoint;
    }
    try
    {
      localObject = ((PlayrixActivity)localObject).getWindowManager().getDefaultDisplay();
      if (Build.VERSION.SDK_INT >= 17)
      {
        ((Display)localObject).getRealSize(localPoint);
        return localPoint;
      }
      ((Display)localObject).getSize(localPoint);
      return localPoint;
    }
    catch (Exception localException) {}
    return localPoint;
  }
  
  public static float getResolutionScale()
  {
    return mResolutionScale.getScale();
  }
  
  public static float getResolutionScaleH()
  {
    return mResolutionScale.getScaleH();
  }
  
  public static float getResolutionScaleW()
  {
    return mResolutionScale.getScaleW();
  }
  
  public static String getSecureAndroidId()
  {
    return Settings.Secure.getString(mContext.getContentResolver(), "android_id");
  }
  
  public static int getSharedPreferencesInt(String paramString, int paramInt)
  {
    SharedPreferences localSharedPreferences = getPreferences();
    if (localSharedPreferences != null) {
      return localSharedPreferences.getInt(paramString, paramInt);
    }
    return paramInt;
  }
  
  public static String getSharedPreferencesString(String paramString1, String paramString2)
  {
    SharedPreferences localSharedPreferences = getPreferences();
    if (localSharedPreferences != null) {
      return localSharedPreferences.getString(paramString1, paramString2);
    }
    return null;
  }
  
  public static String getWriteablePath()
  {
    return mContext.getApplicationInfo().dataDir;
  }
  
  public static boolean isDevicePhablet()
  {
    boolean bool3 = isDeviceTablet();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!bool3)
    {
      bool1 = bool2;
      if (getDisplayDiagonal() > 5.5D) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isDeviceTablet()
  {
    return (mContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static Boolean isEmulator()
  {
    return nativeIsEmulator();
  }
  
  public static Boolean isJailbroken()
  {
    return nativeIsJailbroken();
  }
  
  public static boolean isPackageInstalled(String paramString)
  {
    PackageManager localPackageManager = mContext.getPackageManager();
    try
    {
      boolean bool = localPackageManager.getApplicationInfo(paramString, 0).enabled;
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static boolean isProviderDeclared(String paramString)
  {
    Object localObject = mContext.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(mContext.getPackageName(), 8).providers;
      if (localObject != null)
      {
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          boolean bool = localObject[i].authority.equals(paramString);
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static native void nativeCancelMouse();
  
  public static native void nativeDisableInput(boolean paramBoolean);
  
  public static native void nativeDisplayCutout(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native void nativeExecuteInGlThread(long paramLong);
  
  public static native int nativeGetOrientationSettings();
  
  public static native Boolean nativeIsEmulator();
  
  public static native Boolean nativeIsJailbroken();
  
  public static native boolean nativeIsProductionBuild();
  
  public static native void nativeMouseWheel(int paramInt);
  
  public static native void nativeMultiFingerDoubleTap(int paramInt);
  
  public static native void nativeOnCreate();
  
  public static native void nativeOnDestroy();
  
  public static native void nativeOnLowMemory();
  
  public static native void nativeOnNotificationReceived(int paramInt1, int paramInt2, String paramString, Object paramObject);
  
  public static native void nativeOnOrientationChanged();
  
  public static native void nativeOnPause();
  
  private static native void nativeOnPushTokenReceived(String paramString);
  
  public static native void nativeOnResume();
  
  public static native void nativeOnStart();
  
  public static native void nativeOnStop();
  
  public static native void nativeOnUrlActivate(String paramString);
  
  public static native void nativeRender();
  
  public static native void nativeResize(int paramInt1, int paramInt2);
  
  public static native void nativeScale(int paramInt1, int paramInt2, float paramFloat);
  
  public static native void nativeScaleBegin(int paramInt1, int paramInt2, float paramFloat);
  
  public static native void nativeScaleEnd();
  
  public static native void nativeTouch(int paramInt1, int paramInt2, int paramInt3);
  
  public static void onCreate(PlayrixActivity paramPlayrixActivity)
  {
    mActivity = new WeakReference(paramPlayrixActivity);
  }
  
  public static void onPlayrixActivityCreated(PlayrixActivity paramPlayrixActivity)
  {
    paramPlayrixActivity.getGLView().setResolutionScale(mResolutionScale);
    requestAdvertisingId();
    KeyboardManager.onPlayrixActivityCreated();
  }
  
  public static void onPushTokenReceived(String paramString)
  {
    if (paramString != null) {
      runOnGLThread(new Runnable()
      {
        public final void run()
        {
          Playrix.nativeOnPushTokenReceived(this.val$token);
        }
      });
    }
  }
  
  public static boolean putSharedPreferencesInt(String paramString, int paramInt)
  {
    Object localObject = getPreferences();
    if (localObject == null) {
      return false;
    }
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putInt(paramString, paramInt);
    ((SharedPreferences.Editor)localObject).apply();
    return true;
  }
  
  public static boolean putSharedPreferencesString(String paramString1, String paramString2)
  {
    Object localObject = getPreferences();
    if (localObject == null) {
      return false;
    }
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putString(paramString1, paramString2);
    ((SharedPreferences.Editor)localObject).apply();
    return true;
  }
  
  public static boolean removeSharedPreferencesValue(String paramString)
  {
    Object localObject = getPreferences();
    if (localObject == null) {
      return false;
    }
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).remove(paramString);
    ((SharedPreferences.Editor)localObject).apply();
    return true;
  }
  
  private static void requestAdvertisingId()
  {
    new Thread(new Runnable()
    {
      public static String safedk_AdvertisingIdClient$Info_getId_bf1a41a6e25f7451c57044637a1b33f7(AdvertisingIdClient.Info paramAnonymousInfo)
      {
        Logger.d("GoogleMobileAdsAdMob|SafeDK: Call> Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;->getId()Ljava/lang/String;");
        if (!DexBridge.isSDKEnabled("com.google.ads")) {
          return (String)DexBridge.generateEmptyObject("Ljava/lang/String;");
        }
        StartTimeStats localStartTimeStats = StartTimeStats.getInstance();
        localStartTimeStats.startMeasure("com.google.ads", "Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;->getId()Ljava/lang/String;");
        paramAnonymousInfo = paramAnonymousInfo.getId();
        localStartTimeStats.stopMeasure("Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;->getId()Ljava/lang/String;");
        return paramAnonymousInfo;
      }
      
      public static AdvertisingIdClient.Info safedk_AdvertisingIdClient_getAdvertisingIdInfo_a25a8bf8fb49c143963fe20acf62f4eb(Context paramAnonymousContext)
      {
        Logger.d("GoogleMobileAdsAdMob|SafeDK: Call> Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;->getAdvertisingIdInfo(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;");
        if (!DexBridge.isSDKEnabled("com.google.ads")) {
          return null;
        }
        StartTimeStats localStartTimeStats = StartTimeStats.getInstance();
        localStartTimeStats.startMeasure("com.google.ads", "Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;->getAdvertisingIdInfo(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;");
        paramAnonymousContext = AdvertisingIdClient.getAdvertisingIdInfo(paramAnonymousContext);
        localStartTimeStats.stopMeasure("Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;->getAdvertisingIdInfo(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;");
        return paramAnonymousContext;
      }
      
      public final void run()
      {
        ??? = null;
        try
        {
          AdvertisingIdClient.Info localInfo = safedk_AdvertisingIdClient_getAdvertisingIdInfo_a25a8bf8fb49c143963fe20acf62f4eb(Playrix.mContext);
          localObject1 = ???;
          if (safedk_AdvertisingIdClient$Info_getId_bf1a41a6e25f7451c57044637a1b33f7(localInfo) != null) {
            localObject1 = safedk_AdvertisingIdClient$Info_getId_bf1a41a6e25f7451c57044637a1b33f7(localInfo);
          }
        }
        catch (IOException|GooglePlayServicesRepairableException|GooglePlayServicesNotAvailableException|NullPointerException|Exception localIOException)
        {
          try
          {
            ??? = Settings.Secure.getString(Playrix.mContext.getContentResolver(), "advertising_id");
            if (??? == null) {
              break label61;
            }
            Object localObject1 = ???;
            if (!((String)???).isEmpty()) {
              break label64;
            }
            localObject1 = "none";
            synchronized (Playrix.advertisingIdlocker)
            {
              Playrix.access$202((String)localObject1);
              return;
            }
            localIOException = localIOException;
            localObject3 = ???;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              Object localObject3;
              Object localObject5 = localObject3;
            }
          }
        }
        if (localObject1 != null)
        {
          ??? = localObject1;
          if (!((String)localObject1).isEmpty()) {
            break label48;
          }
        }
      }
    }).start();
  }
  
  public static void runOnGLThread(Runnable paramRunnable)
  {
    NativeThread.getInstance().queueEvent(paramRunnable);
  }
  
  public static boolean runOnUiThread(Runnable paramRunnable)
  {
    PlayrixActivity localPlayrixActivity = getActivity();
    if (localPlayrixActivity == null) {
      return false;
    }
    localPlayrixActivity.runOnUiThread(paramRunnable);
    return true;
  }
  
  public static void safedk_Application_sendOrderedBroadcast_8a5c3e0aa05fe014e918040897c36033(Application paramApplication, Intent paramIntent, String paramString)
  {
    Logger.d("SafeDK-Special|SafeDK: Call> Landroid/app/Application;->sendOrderedBroadcast(Landroid/content/Intent;Ljava/lang/String;)V");
    if (paramIntent == null) {
      return;
    }
    paramApplication.sendOrderedBroadcast(paramIntent, paramString);
  }
  
  public static Intent safedk_Intent_putExtra_123208af15330635a9d4be1224cecaa0(Intent paramIntent, String paramString1, String paramString2)
  {
    Logger.d("SafeDK-Special|SafeDK: Call> Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;");
    if (paramIntent == null) {
      return (Intent)DexBridge.generateEmptyObject("Landroid/content/Intent;");
    }
    return paramIntent.putExtra(paramString1, paramString2);
  }
  
  public static Intent safedk_Intent_setPackage_e511a47ad0d911d0a94434c2a77087ae(Intent paramIntent, String paramString)
  {
    Logger.d("SafeDK-Special|SafeDK: Call> Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;");
    if (paramIntent == null) {
      return (Intent)DexBridge.generateEmptyObject("Landroid/content/Intent;");
    }
    return paramIntent.setPackage(paramString);
  }
  
  public static void sendBroadcast(String paramString1, String paramString2, Map<String, String> paramMap, String paramString3)
  {
    paramString1 = new Intent(paramString1);
    safedk_Intent_setPackage_e511a47ad0d911d0a94434c2a77087ae(paramString1, paramString2);
    if (paramMap != null)
    {
      paramString2 = paramMap.entrySet().iterator();
      while (paramString2.hasNext())
      {
        paramMap = (Map.Entry)paramString2.next();
        safedk_Intent_putExtra_123208af15330635a9d4be1224cecaa0(paramString1, (String)paramMap.getKey(), (String)paramMap.getValue());
      }
    }
    safedk_Application_sendOrderedBroadcast_8a5c3e0aa05fe014e918040897c36033(getApplication(), paramString1, paramString3);
  }
  
  public static void setApplicationContext(Application paramApplication)
  {
    if (mContext == paramApplication) {
      return;
    }
    mContext = paramApplication;
    actMgr = (ActivityManager)paramApplication.getSystemService("activity");
    winMgr = (WindowManager)mContext.getSystemService("window");
    connMgr = (ConnectivityManager)mContext.getSystemService("connectivity");
    KeyboardManager.init(mContext);
  }
  
  public static void setDeviceOrientation(Activity paramActivity, int paramInt)
  {
    setDeviceOrientation(paramActivity, paramInt, paramInt);
  }
  
  public static void setDeviceOrientation(Activity paramActivity, int paramInt1, int paramInt2)
  {
    setDeviceOrientation(paramActivity, paramInt1, paramInt1, paramInt2);
  }
  
  public static void setDeviceOrientation(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3)
  {
    if (Build.VERSION.SDK_INT > 8)
    {
      if (isDeviceTablet())
      {
        paramActivity.setRequestedOrientation(paramInt3);
        return;
      }
      if (isDevicePhablet())
      {
        paramActivity.setRequestedOrientation(paramInt2);
        return;
      }
      paramActivity.setRequestedOrientation(paramInt1);
    }
  }
  
  public static void setResolutionScale(float paramFloat)
  {
    setResolutionScale(paramFloat, paramFloat);
  }
  
  public static void setResolutionScale(float paramFloat1, float paramFloat2)
  {
    mResolutionScale = new GLSurfaceViewV17.Scale2D(paramFloat1, paramFloat2);
    PlayrixActivity localPlayrixActivity = getActivity();
    if (localPlayrixActivity != null) {
      localPlayrixActivity.getGLView().setResolutionScale(mResolutionScale);
    }
  }
  
  public static boolean tryStartPackage(final String paramString)
  {
    paramString = mContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString == null) {
      return false;
    }
    PlayrixActivity localPlayrixActivity = getActivity();
    if (localPlayrixActivity == null) {
      return false;
    }
    runOnUiThread(new Runnable()
    {
      public static void safedk_Activity_startActivity_9d898b58165fa4ba0e12c3900a2b8533(Activity paramAnonymousActivity, Intent paramAnonymousIntent)
      {
        Logger.d("SafeDK-Special|SafeDK: Call> Landroid/app/Activity;->startActivity(Landroid/content/Intent;)V");
        if (paramAnonymousIntent == null) {
          return;
        }
        paramAnonymousActivity.startActivity(paramAnonymousIntent);
      }
      
      public final void run()
      {
        try
        {
          safedk_Activity_startActivity_9d898b58165fa4ba0e12c3900a2b8533(this.val$activity, paramString);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException) {}
      }
    });
  }
  
  public static String urlEncode(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static void useExternalCache()
  {
    if ((Environment.getExternalStorageState().equals("mounted")) && (mContext.getExternalCacheDir() != null)) {
      externalCacheDir = true;
    }
  }
}
