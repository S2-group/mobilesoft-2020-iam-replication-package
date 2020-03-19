package com.playrix.gardenscapes.lib;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.os.LocaleListCompat;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.playrix.lib.Logger;
import com.playrix.lib.Playrix;
import com.playrix.lib.PlayrixActivity;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class Utils
{
  private static final int DEFAULT_MIN_FREE_SPACE_EXTERNAL = 52428800;
  private static final int DEFAULT_MIN_FREE_SPACE_INTERNAL = 52428800;
  private static final int STATE_DISABLED = 0;
  private static final int STATE_ENABLED = 1;
  private static final int STATE_UNKNOWN = -1;
  private static final String TAG = "[PlayrixGsfUtils] ";
  private static String webViewUserAgent = "";
  
  public Utils() {}
  
  public static boolean activeCrashReport()
  {
    if (!GlobalVars.getBool("HockeyappEnabled", true)) {
      return false;
    }
    if (!GlobalVars.getBool("CheckSendCrashReports", true)) {
      return true;
    }
    Boolean localBoolean = Playrix.isEmulator();
    int i;
    if ((localBoolean != null) && (localBoolean.booleanValue())) {
      i = 1;
    } else {
      i = 0;
    }
    boolean bool1 = isValidInstaller();
    boolean bool2 = isValidPackageName();
    if ((!isDebugBuild()) && (!isSupportBuild())) {
      return (i == 0) && (bool1) && (bool2);
    }
    return true;
  }
  
  public static boolean checkExternalStorage(Context paramContext)
  {
    try
    {
      localObject = Environment.getExternalStorageState();
      Logger.sysInfo("[PlayrixGsfUtils] External storage state: ".concat(String.valueOf(localObject)));
      if ("mounted".equals(localObject))
      {
        paramContext = paramContext.getExternalFilesDir(null);
        if (paramContext != null) {
          return true;
        }
      }
      return false;
    }
    catch (NullPointerException paramContext)
    {
      Object localObject = new StringBuilder("[PlayrixGsfUtils] Can't checkExternalStorage ");
      ((StringBuilder)localObject).append(paramContext.getMessage());
      Logger.sysError(((StringBuilder)localObject).toString());
    }
    return false;
  }
  
  public static boolean checkFreeSpace(Activity paramActivity)
  {
    int i;
    if (paramActivity != null)
    {
      if (paramActivity.getApplicationInfo().dataDir != null)
      {
        File localFile = new File(paramActivity.getApplicationInfo().dataDir);
        if (localFile.exists())
        {
          i = GlobalVars.getInt("MinFreeSpaceInternal", 52428800);
          if (localFile.getFreeSpace() >= i)
          {
            i = 1;
            break label64;
          }
        }
      }
      i = 0;
      label64:
      if (paramActivity.getExternalFilesDir(null) == null) {}
    }
    try
    {
      paramActivity = new File(paramActivity.getExternalFilesDir(null).getCanonicalPath());
      if (paramActivity.exists())
      {
        j = GlobalVars.getInt("MinFreeSpaceExternal", 52428800);
        long l = paramActivity.getFreeSpace();
        if (l >= j) {
          j = 1;
        }
      }
    }
    catch (IOException paramActivity)
    {
      int j;
      for (;;) {}
    }
    j = 0;
    return (i != 0) && (j != 0);
    return false;
  }
  
  /* Error */
  public static boolean convertImgAndSave(byte[] paramArrayOfByte, int paramInt, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 6
    //   3: iconst_0
    //   4: istore 7
    //   6: aconst_null
    //   7: astore 10
    //   9: aconst_null
    //   10: astore 9
    //   12: aload_0
    //   13: iconst_0
    //   14: iload_1
    //   15: invokestatic 168	android/graphics/BitmapFactory:decodeByteArray	([BII)Landroid/graphics/Bitmap;
    //   18: astore 11
    //   20: getstatic 174	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   23: astore 8
    //   25: aload_2
    //   26: ldc -80
    //   28: invokestatic 181	java/util/regex/Pattern:quote	(Ljava/lang/String;)Ljava/lang/String;
    //   31: invokevirtual 185	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   34: astore_0
    //   35: aload_0
    //   36: aload_0
    //   37: arraylength
    //   38: iconst_1
    //   39: isub
    //   40: aaload
    //   41: astore_0
    //   42: aload_0
    //   43: ldc -69
    //   45: invokevirtual 191	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   48: ifne +12 -> 60
    //   51: aload_0
    //   52: ldc -63
    //   54: invokevirtual 191	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   57: ifeq +8 -> 65
    //   60: getstatic 195	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   63: astore 8
    //   65: aload 11
    //   67: invokevirtual 201	android/graphics/Bitmap:getWidth	()I
    //   70: istore_1
    //   71: aload 11
    //   73: invokevirtual 204	android/graphics/Bitmap:getHeight	()I
    //   76: istore 5
    //   78: iload_1
    //   79: iload 5
    //   81: if_icmple +234 -> 315
    //   84: iload_1
    //   85: i2f
    //   86: fstore 4
    //   88: goto +3 -> 91
    //   91: iload_3
    //   92: ifeq +21 -> 113
    //   95: fload 4
    //   97: ldc -51
    //   99: fcmpl
    //   100: ifle +223 -> 323
    //   103: ldc -51
    //   105: fload 4
    //   107: fdiv
    //   108: fstore 4
    //   110: goto +21 -> 131
    //   113: fload 4
    //   115: ldc -50
    //   117: fcmpl
    //   118: ifle +205 -> 323
    //   121: ldc -50
    //   123: fload 4
    //   125: fdiv
    //   126: fstore 4
    //   128: goto +3 -> 131
    //   131: new 104	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   138: astore_0
    //   139: aload_0
    //   140: aload_2
    //   141: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: pop
    //   145: aload_0
    //   146: ldc -47
    //   148: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: aload_0
    //   153: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   156: astore 12
    //   158: new 211	java/io/BufferedOutputStream
    //   161: dup
    //   162: new 213	java/io/FileOutputStream
    //   165: dup
    //   166: aload 12
    //   168: invokespecial 214	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   171: invokespecial 217	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   174: astore_0
    //   175: fload 4
    //   177: fconst_1
    //   178: fcmpl
    //   179: ifne +17 -> 196
    //   182: aload 11
    //   184: aload 8
    //   186: bipush 95
    //   188: aload_0
    //   189: invokevirtual 221	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   192: pop
    //   193: goto +31 -> 224
    //   196: aload 11
    //   198: iload_1
    //   199: i2f
    //   200: fload 4
    //   202: fmul
    //   203: f2i
    //   204: iload 5
    //   206: i2f
    //   207: fload 4
    //   209: fmul
    //   210: f2i
    //   211: iconst_1
    //   212: invokestatic 225	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   215: aload 8
    //   217: bipush 95
    //   219: aload_0
    //   220: invokevirtual 221	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   223: pop
    //   224: new 138	java/io/File
    //   227: dup
    //   228: aload 12
    //   230: invokespecial 139	java/io/File:<init>	(Ljava/lang/String;)V
    //   233: astore 8
    //   235: iload 7
    //   237: istore_3
    //   238: aload 8
    //   240: invokevirtual 142	java/io/File:exists	()Z
    //   243: ifeq +17 -> 260
    //   246: aload 8
    //   248: new 138	java/io/File
    //   251: dup
    //   252: aload_2
    //   253: invokespecial 139	java/io/File:<init>	(Ljava/lang/String;)V
    //   256: invokevirtual 229	java/io/File:renameTo	(Ljava/io/File;)Z
    //   259: istore_3
    //   260: iload_3
    //   261: istore 6
    //   263: aload_0
    //   264: invokevirtual 234	java/io/OutputStream:close	()V
    //   267: iload_3
    //   268: ireturn
    //   269: goto +17 -> 286
    //   272: astore_0
    //   273: aload 9
    //   275: astore_2
    //   276: aload_2
    //   277: ifnull +7 -> 284
    //   280: aload_2
    //   281: invokevirtual 234	java/io/OutputStream:close	()V
    //   284: aload_0
    //   285: athrow
    //   286: aload_0
    //   287: ifnull +7 -> 294
    //   290: aload_0
    //   291: invokevirtual 234	java/io/OutputStream:close	()V
    //   294: iconst_0
    //   295: ireturn
    //   296: astore_0
    //   297: aload 10
    //   299: astore_0
    //   300: goto -14 -> 286
    //   303: astore_2
    //   304: goto -35 -> 269
    //   307: astore_0
    //   308: iload 6
    //   310: ireturn
    //   311: astore_2
    //   312: goto -28 -> 284
    //   315: iload 5
    //   317: i2f
    //   318: fstore 4
    //   320: goto -229 -> 91
    //   323: fconst_1
    //   324: fstore 4
    //   326: goto -195 -> 131
    //   329: astore 8
    //   331: aload_0
    //   332: astore_2
    //   333: aload 8
    //   335: astore_0
    //   336: goto -60 -> 276
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	339	0	paramArrayOfByte	byte[]
    //   0	339	1	paramInt	int
    //   0	339	2	paramString	String
    //   0	339	3	paramBoolean	boolean
    //   86	239	4	f	float
    //   76	240	5	i	int
    //   1	308	6	bool1	boolean
    //   4	232	7	bool2	boolean
    //   23	224	8	localObject1	Object
    //   329	5	8	localObject2	Object
    //   10	264	9	localObject3	Object
    //   7	291	10	localObject4	Object
    //   18	179	11	localBitmap	android.graphics.Bitmap
    //   156	73	12	str	String
    // Exception table:
    //   from	to	target	type
    //   12	51	272	finally
    //   51	60	272	finally
    //   60	65	272	finally
    //   65	78	272	finally
    //   103	110	272	finally
    //   121	128	272	finally
    //   131	175	272	finally
    //   12	51	296	java/lang/Exception
    //   51	60	296	java/lang/Exception
    //   60	65	296	java/lang/Exception
    //   65	78	296	java/lang/Exception
    //   103	110	296	java/lang/Exception
    //   121	128	296	java/lang/Exception
    //   131	175	296	java/lang/Exception
    //   182	193	303	java/lang/Exception
    //   196	224	303	java/lang/Exception
    //   224	235	303	java/lang/Exception
    //   238	260	303	java/lang/Exception
    //   263	267	307	java/io/IOException
    //   290	294	307	java/io/IOException
    //   280	284	311	java/io/IOException
    //   182	193	329	finally
    //   196	224	329	finally
    //   224	235	329	finally
    //   238	260	329	finally
  }
  
  private static void errorMethod1() {}
  
  private static void errorMethod2()
  {
    throw new InternalError();
  }
  
  public static String getAppVersionCode()
  {
    return Integer.toString(Playrix.getAppVersionCode());
  }
  
  public static float getDiagonal()
  {
    Display localDisplay = Playrix.getActivity().getWindowManager().getDefaultDisplay();
    Point localPoint = new Point();
    localDisplay.getSize(localPoint);
    int i = localPoint.x;
    int j = localPoint.y;
    return Math.round((float)Math.sqrt(i * i + j * j) / Playrix.getDisplayPpi() * 10.0F);
  }
  
  public static long getElapsedRealtime()
  {
    return SystemClock.elapsedRealtime();
  }
  
  public static String getExternalStorageDir()
  {
    return Playrix.getContext().getExternalFilesDir(null).getAbsolutePath();
  }
  
  public static int getFileDescriptorsCount()
  {
    try
    {
      Object localObject = new StringBuilder("/proc/");
      ((StringBuilder)localObject).append(Process.myPid());
      ((StringBuilder)localObject).append("/fd");
      localObject = new File(((StringBuilder)localObject).toString()).list();
      if (localObject != null)
      {
        int i = localObject.length;
        return i;
      }
      return 0;
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder("[PlayrixGsfUtils] Exception ");
      localStringBuilder.append(localException.getMessage());
      Logger.logError(localStringBuilder.toString());
    }
    return 0;
  }
  
  public static Object[] getFilesListForDir(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = new File(paramString);
    if ((paramString.exists()) && (paramString.isDirectory()))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString.getName());
      localStringBuilder.append("/");
      listFilesRecursive(paramString, localStringBuilder.toString(), localArrayList);
    }
    return localArrayList.toArray();
  }
  
  public static String getFormattedDate()
  {
    return new SimpleDateFormat("yyyyMMdd").format(new Date());
  }
  
  public static Object[] getInstalledPackages()
  {
    Object localObject = Playrix.getContext().getPackageManager().getInstalledApplications(128);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
    }
    return localArrayList.toArray();
  }
  
  private static String getInstaller(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName());
      if (paramContext == null) {
        return "";
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String getMD5(String paramString)
  {
    return getMD5(paramString.getBytes());
  }
  
  public static String getMD5(byte[] paramArrayOfByte)
  {
    for (;;)
    {
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramArrayOfByte);
        localObject = ((MessageDigest)localObject).digest();
        localStringBuffer = new StringBuffer();
        int j = localObject.length;
        i = 0;
        if (i >= j) {
          continue;
        }
        k = localObject[i];
        localStringBuilder = new StringBuilder();
        k &= 0xFF;
        if (k > 15) {
          continue;
        }
        paramArrayOfByte = "0";
      }
      catch (Exception paramArrayOfByte)
      {
        StringBuffer localStringBuffer;
        int i;
        int k;
        StringBuilder localStringBuilder;
        continue;
        paramArrayOfByte = "";
        continue;
      }
      localStringBuilder.append(paramArrayOfByte);
      localStringBuilder.append(Integer.toHexString(k));
      localStringBuffer.append(localStringBuilder.toString());
      i += 1;
    }
    paramArrayOfByte = localStringBuffer.toString();
    return paramArrayOfByte;
    return "";
  }
  
  public static String getPackageName()
  {
    Context localContext = Playrix.getContext();
    if (localContext == null) {
      return "?";
    }
    return localContext.getPackageName();
  }
  
  @TargetApi(19)
  private static int getPostNotificationStatus()
  {
    String str = Playrix.getContext().getPackageName();
    int i = Playrix.getContext().getApplicationInfo().uid;
    try
    {
      localObject = (AppOpsManager)Playrix.getContext().getSystemService("appops");
      Class localClass = Class.forName(AppOpsManager.class.getName());
      i = ((Integer)localClass.getMethod("checkOpNoThrow", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localObject, new Object[] { Integer.valueOf(((Integer)localClass.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), str })).intValue();
      if (i == 0) {
        return 1;
      }
      if (i == 2) {
        return -1;
      }
      return 0;
    }
    catch (Exception localException)
    {
      Object localObject = new StringBuilder("[PlayrixGsfUtils] Error while checking for push notification state:\n");
      ((StringBuilder)localObject).append(Log.getStackTraceString(localException));
      Logger.logError(((StringBuilder)localObject).toString());
    }
    return -1;
  }
  
  public static String getPrimaryCountryTag()
  {
    LocaleListCompat localLocaleListCompat = LocaleListCompat.getDefault();
    if (!localLocaleListCompat.isEmpty()) {
      return localLocaleListCompat.get(0).getCountry();
    }
    return Playrix.getLocaleCountry();
  }
  
  public static String getPublicIp()
  {
    return nativeGetPublicIp();
  }
  
  @TargetApi(14)
  public static int getPushNotificationStatus()
  {
    if (Build.VERSION.SDK_INT < 16) {
      return 1;
    }
    if (Build.VERSION.SDK_INT < 19) {
      return -1;
    }
    return getPostNotificationStatus();
  }
  
  public static Point getRealScreenSize()
  {
    Point localPoint = new Point();
    if (Playrix.getActivity() != null) {
      try
      {
        Display localDisplay = Playrix.getActivity().getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17)
        {
          localDisplay.getRealSize(localPoint);
          return localPoint;
        }
        localDisplay.getSize(localPoint);
        return localPoint;
      }
      catch (Exception localException)
      {
        StringBuilder localStringBuilder = new StringBuilder("[PlayrixGsfUtils] Exception ");
        localStringBuilder.append(localException.getMessage());
        Logger.logError(localStringBuilder.toString());
      }
    }
    return localPoint;
  }
  
  public static String getSupportedOpenGlVersion()
  {
    if (Playrix.getContext() == null) {
      return "n/a";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    ConfigurationInfo localConfigurationInfo = ((ActivityManager)Playrix.getContext().getSystemService("activity")).getDeviceConfigurationInfo();
    if (localConfigurationInfo.reqGlEsVersion != 0)
    {
      localStringBuilder.append(Integer.toString((localConfigurationInfo.reqGlEsVersion & 0xFFFF0000) >> 16));
      localStringBuilder.append(".");
      localStringBuilder.append(Integer.toString(localConfigurationInfo.reqGlEsVersion & 0xFFFF));
    }
    else
    {
      localStringBuilder.append("1.0");
    }
    return localStringBuilder.toString();
  }
  
  public static int getVersionSDK()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getWebViewChromeVersion()
  {
    String str = "n/a";
    if (Build.VERSION.SDK_INT < 19) {
      return "n/a";
    }
    Object localObject = new StringBuilder("[PlayrixGsfUtils] WebView user agent: ");
    ((StringBuilder)localObject).append(webViewUserAgent);
    Logger.logInfo(((StringBuilder)localObject).toString());
    localObject = Pattern.compile(".*Chrome/(\\S+)\\s").matcher(webViewUserAgent);
    if (((Matcher)localObject).find()) {
      str = ((Matcher)localObject).group(1);
    }
    return str;
  }
  
  public static String getWebViewUserAgent()
  {
    return webViewUserAgent;
  }
  
  public static void initializeWebViewUserAgent(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      if (Build.VERSION.SDK_INT >= 17)
      {
        webViewUserAgent = WebSettings.getDefaultUserAgent(paramContext);
        return;
      }
      webViewUserAgent = new WebView(paramContext).getSettings().getUserAgentString();
      return;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    webViewUserAgent = System.getProperty("http.agent");
  }
  
  public static boolean isAmazon()
  {
    return Playrix.getContext().getString(2131689850).equals("amazon");
  }
  
  public static boolean isDebugBuild()
  {
    return false;
  }
  
  public static boolean isGameFeatureEnabled(String paramString, boolean paramBoolean)
  {
    return nativeIsGameFeatureEnabled(paramString, paramBoolean);
  }
  
  public static boolean isGoogle()
  {
    return Playrix.getContext().getString(2131689850).equals("google");
  }
  
  public static boolean isSupportBuild()
  {
    return false;
  }
  
  public static boolean isValidInstaller()
  {
    return nativeIsValidInstaller();
  }
  
  public static boolean isValidPackageName()
  {
    boolean bool = true;
    try
    {
      String str = Playrix.getContext().getPackageName();
      if ((str != null) && (!str.isEmpty()))
      {
        if (!str.equals("com.playrix.gardenscapes"))
        {
          bool = str.equals("com.playrix.gardenscapes.amazon");
          if (bool) {
            return true;
          }
          bool = false;
        }
        return bool;
      }
      return true;
    }
    catch (Exception localException) {}
    return true;
  }
  
  public static String javaObjectToJsonString(Object paramObject)
  {
    if (paramObject == null) {
      return "";
    }
    try
    {
      localObject = ((JSONObject)paramObject).toString();
      return localObject;
    }
    catch (ClassCastException localClassCastException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = new StringBuilder("[PlayrixGsfUtils] rawData of unsupported type ");
    ((StringBuilder)localObject).append(paramObject.getClass().getSimpleName());
    Logger.logError(((StringBuilder)localObject).toString());
    return "";
  }
  
  private static void listFilesRecursive(File paramFile, String paramString, ArrayList<String> paramArrayList)
  {
    if (paramFile.exists())
    {
      if (!paramFile.isDirectory()) {
        return;
      }
      paramFile = paramFile.listFiles();
      int j = paramFile.length;
      int i = 0;
      while (i < j)
      {
        File localFile = paramFile[i];
        StringBuilder localStringBuilder;
        if (localFile.isDirectory())
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString);
          localStringBuilder.append(localFile.getName());
          localStringBuilder.append("/");
          listFilesRecursive(localFile, localStringBuilder.toString(), paramArrayList);
        }
        else
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString);
          localStringBuilder.append(localFile.getName());
          paramArrayList.add(localStringBuilder.toString());
        }
        i += 1;
      }
      return;
    }
  }
  
  private static native String nativeGetPublicIp();
  
  private static native boolean nativeIsGameFeatureEnabled(String paramString, boolean paramBoolean);
  
  private static native boolean nativeIsValidInstaller();
  
  public static void setCutoutLayoutMode(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 28)
    {
      WindowManager.LayoutParams localLayoutParams = paramActivity.getWindow().getAttributes();
      localLayoutParams.layoutInDisplayCutoutMode = 2;
      paramActivity.getWindow().setAttributes(localLayoutParams);
    }
  }
  
  public static void showToast(String paramString)
  {
    if (Playrix.getActivity() == null) {
      return;
    }
    if (paramString != null)
    {
      if (paramString.isEmpty()) {
        return;
      }
      Playrix.getActivity().runOnUiThread(new Utils.1(paramString));
      return;
    }
  }
  
  public static void throwErrorInJava() {}
}
