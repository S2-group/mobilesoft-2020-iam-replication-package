package com.samsung.ssc;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.telephony.TelephonyManager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.samsungimaging.connectionmanager.CMMainActivity;
import com.samsungimaging.connectionmanager.CMUtil;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils
{
  static final int ERROR = -1;
  private static final String TAG = "SSC";
  static String[] configString = new String[''];
  private static boolean isXlargeTablet = false;
  private static final int thumbHeight = 80;
  private static final int thumbWidth = 112;
  private static String userAgent = null;
  private static final int zoomHeight = 240;
  private static final int zoomWidth = 336;
  
  public CommonUtils() {}
  
  public static String ByteToHex(byte[] paramArrayOfByte)
  {
    String str2 = "";
    int i = 0;
    String str1;
    String str3;
    for (;;)
    {
      if (i >= paramArrayOfByte.length) {
        return str2.toUpperCase();
      }
      str1 = Integer.toHexString(paramArrayOfByte[i]);
      if (str1.length() < 2) {
        break;
      }
      str3 = str1.substring(str1.length() - 2);
      str2 = str2 + " " + str3;
      i += 1;
    }
    int j = 0;
    for (;;)
    {
      str3 = str1;
      if (j >= 2 - str1.length()) {
        break;
      }
      str1 = "0" + str1;
      j += 1;
    }
  }
  
  public static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int j = paramOptions.outHeight;
    int k = paramOptions.outWidth;
    int i = 1;
    if ((j > paramInt2) || (k > paramInt1))
    {
      if (k > j) {
        i = Math.round(j / paramInt2);
      }
    }
    else {
      return i;
    }
    return Math.round(k / paramInt1);
  }
  
  public static Bitmap decodeForZoom(String paramString)
  {
    if (paramString.toLowerCase().endsWith(".jpg"))
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      localOptions.inSampleSize = calculateInSampleSize(localOptions, 336, 240);
      localOptions.inJustDecodeBounds = false;
      return getRotateBitmap(BitmapFactory.decodeFile(paramString, localOptions), getExifOrientation(paramString));
    }
    return ThumbnailUtils.createVideoThumbnail(paramString, 1);
  }
  
  public static Bitmap decodeThumb(String paramString)
  {
    if (paramString.toLowerCase().endsWith(".jpg"))
    {
      Object localObject = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject);
      ((BitmapFactory.Options)localObject).inSampleSize = calculateInSampleSize((BitmapFactory.Options)localObject, 112, 80);
      ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
      localObject = BitmapFactory.decodeFile(paramString, (BitmapFactory.Options)localObject);
      Logger.d("SSC", "FREE MEMORY : " + Runtime.getRuntime().freeMemory() / 1048576L + "MB");
      return getRotateBitmap((Bitmap)localObject, getExifOrientation(paramString));
    }
    return ThumbnailUtils.createVideoThumbnail(paramString, 3);
  }
  
  public static boolean externalMemoryAvailable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean getActivityRunning(Context paramContext, String paramString)
  {
    return ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(100).get(0)).baseActivity.getPackageName().contains(paramString);
  }
  
  public static long getAvailableExternalMemorySize()
  {
    if (externalMemoryAvailable())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getAvailableBlocks() * l;
    }
    return -1L;
  }
  
  public static long getAvailableInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static String getDefaultStorage()
  {
    String str = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/Samsung Smart Camera Application/";
    switch (CMMainActivity.ACTIVE_APP)
    {
    default: 
      return str;
    case 3: 
      return str + "AutoShare/";
    case 4: 
      return str + "MobileLink/";
    case 2: 
      return str + "RemoteViewfinder/";
    }
    return str + "MobileLink/";
  }
  
  public static boolean getDisplayConnGuide(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("connguide", true);
  }
  
  public static boolean getDisplayDetailGuide(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("guide", true);
  }
  
  public static boolean getDisplayNotice(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("notice", true);
  }
  
  public static int getExifOrientation(String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = new ExifInterface(paramString);
      int i;
      if (paramString != null)
      {
        i = paramString.getAttributeInt("Orientation", 1);
        if (i == 1) {}
      }
      switch (i)
      {
      default: 
        return 0;
      }
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = localObject;
      }
      return 90;
    }
    return 180;
    return 270;
    return 280;
    return 290;
    return 300;
    return 310;
  }
  
  public static String getExtention(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString != null)
    {
      str1 = str2;
      if (paramString.lastIndexOf(".") != -1) {
        str1 = paramString.substring(paramString.lastIndexOf(".") + 1, paramString.length());
      }
    }
    return str1;
  }
  
  public static String getLineNumber(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null)
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext.getSimState() != 1) {
        localObject1 = paramContext.getLine1Number();
      }
    }
    if (localObject1 != null)
    {
      paramContext = (Context)localObject1;
      if (((String)localObject1).length() != 0) {}
    }
    else
    {
      paramContext = "none";
    }
    return paramContext;
  }
  
  public static String getMacAddress(Context paramContext)
  {
    String str = "";
    WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    paramContext = str;
    if (localWifiInfo != null)
    {
      paramContext = localWifiInfo.getMacAddress();
      if (paramContext == null) {}
    }
    else
    {
      return paramContext;
    }
    return "UNKNOWN";
  }
  
  public static String getMimetype(String paramString)
  {
    String str = "";
    if (paramString.lastIndexOf(".") != -1) {
      str = paramString.substring(paramString.lastIndexOf(".") + 1, paramString.length()).toLowerCase();
    }
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
  }
  
  public static Map<String, PackageInfo> getPackageMap(Context paramContext)
  {
    SortedMap localSortedMap = Collections.synchronizedSortedMap(new TreeMap());
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledApplications(128).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localSortedMap;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      try
      {
        localSortedMap.put(localApplicationInfo.packageName, paramContext.getPackageInfo(localApplicationInfo.packageName, 0));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
  }
  
  public static PackageInfo getPackageSmartWiFiCM(Context paramContext)
  {
    paramContext = getPackageMap(paramContext).values().iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while (!localPackageInfo.packageName.startsWith("com.skt.network.wificm."));
    return localPackageInfo;
  }
  
  public static Bitmap getRotateBitmap(Bitmap paramBitmap, int paramInt)
  {
    Object localObject1;
    if ((paramInt < 280) && (paramBitmap != null))
    {
      localObject1 = new Matrix();
      ((Matrix)localObject1).setRotate(paramInt, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
    }
    Bitmap localBitmap;
    do
    {
      do
      {
        try
        {
          localBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), (Matrix)localObject1, true);
          localObject1 = paramBitmap;
          if (paramBitmap != localBitmap)
          {
            paramBitmap.recycle();
            localObject1 = localBitmap;
          }
          return localObject1;
        }
        catch (OutOfMemoryError localOutOfMemoryError1)
        {
          localOutOfMemoryError1.printStackTrace();
          return paramBitmap;
        }
        localObject2 = paramBitmap;
      } while (paramInt < 280);
      localObject2 = paramBitmap;
    } while (paramBitmap == null);
    Object localObject2 = new Matrix();
    if (paramInt == 280) {
      ((Matrix)localObject2).setScale(-1.0F, 1.0F, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
    }
    for (;;)
    {
      try
      {
        localBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), (Matrix)localObject2, false);
        localObject2 = paramBitmap;
        if (paramBitmap == localBitmap) {
          break;
        }
        paramBitmap.recycle();
        return localBitmap;
      }
      catch (OutOfMemoryError localOutOfMemoryError2)
      {
        localOutOfMemoryError2.printStackTrace();
      }
      if (paramInt == 290)
      {
        ((Matrix)localObject2).setScale(1.0F, -1.0F, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
      }
      else if (paramInt == 300)
      {
        ((Matrix)localObject2).setRotate(270.0F, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
        ((Matrix)localObject2).postTranslate(-(paramBitmap.getWidth() - paramBitmap.getHeight()) / 2.0F, -(paramBitmap.getHeight() - paramBitmap.getWidth()) / 2.0F);
        ((Matrix)localObject2).preScale(1.0F, -1.0F);
      }
      else if (paramInt == 310)
      {
        ((Matrix)localObject2).setRotate(90.0F, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
        ((Matrix)localObject2).postTranslate(-(paramBitmap.getWidth() - paramBitmap.getHeight()) / 2.0F, -(paramBitmap.getHeight() - paramBitmap.getWidth()) / 2.0F);
        ((Matrix)localObject2).preScale(1.0F, -1.0F);
      }
    }
    return paramBitmap;
  }
  
  public static boolean getSystemConfigurationChanged(Context paramContext)
  {
    boolean bool2 = true;
    paramContext = paramContext.getResources().getConfiguration();
    Logger.d("SSC", "getSystemConfigurationChanged configString[0] : " + configString[0]);
    Logger.d("SSC", "getSystemConfigurationChanged configString[1] : " + configString[1]);
    Logger.d("SSC", "getSystemConfigurationChanged config : " + paramContext.toString());
    boolean bool1;
    if ((configString[0] != null) && (paramContext.toString().equals(configString[0])))
    {
      bool1 = false;
      Logger.d("SSC", "getSystemConfigurationChanged   config value is Not changed");
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (configString[1] == null);
      bool1 = bool2;
    } while (!paramContext.toString().equals(configString[1]));
    Logger.d("SSC", "getSystemConfigurationChanged   config value is Not changed");
    return false;
  }
  
  public static long getTotalExternalMemorySize()
  {
    if (externalMemoryAvailable())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getBlockCount() * l;
    }
    return -1L;
  }
  
  public static long getTotalInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
  }
  
  public static String getUserAgent()
  {
    return userAgent;
  }
  
  public static String getUseragent(Context paramContext)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = null;
    String str = null;
    if (paramContext != null)
    {
      localObject = (TelephonyManager)paramContext.getSystemService("phone");
      if (((TelephonyManager)localObject).getSimState() != 1) {
        str = ((TelephonyManager)localObject).getLine1Number();
      }
      if (str != null)
      {
        localObject = str;
        if (str.length() != 0) {}
      }
      else
      {
        paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
        localObject = str;
        if (paramContext != null) {
          localObject = paramContext.getMacAddress().toLowerCase();
        }
      }
    }
    if (localObject != null)
    {
      paramContext = (Context)localObject;
      if (((String)localObject).length() != 0) {}
    }
    else
    {
      paramContext = "UNKNOWN";
    }
    localStringBuffer.append(paramContext);
    return localStringBuffer.toString();
  }
  
  public static void goToGallery(Context paramContext)
  {
    Logger.d("SSC", "Model : " + Build.MODEL);
    if (CMUtil.isInstalledPackage("com.motorola.blurgallery", paramContext))
    {
      localObject = new Intent();
      ((Intent)localObject).setClassName("com.motorola.blurgallery", "com.motorola.cgallery.Dashboard");
      ((Intent)localObject).addFlags(268435456);
      paramContext.startActivity((Intent)localObject);
      Logger.d("SSC", "Go to gallery - Motorolla Moto Gallery");
      return;
    }
    if (CMUtil.isInstalledPackage("com.sec.android.gallery3d", paramContext))
    {
      localObject = new Intent();
      ((Intent)localObject).setClassName("com.sec.android.gallery3d", "com.sec.android.gallery3d.app.Gallery");
      ((Intent)localObject).addFlags(268435456);
      Logger.d("SSC", "Os version : " + Build.VERSION.RELEASE + " model : " + Build.MODEL);
      if ((Build.MODEL.contains("E210")) || (Build.MODEL.contains("M440")) || (Build.MODEL.contains("9300")) || (Build.MODEL.contains("SHW-M480"))) {
        if (Build.VERSION.SDK_INT >= 16) {
          ((Intent)localObject).setAction("android.intent.action.VIEW");
        }
      }
      for (;;)
      {
        Logger.d("SSC", "intent.getAction : " + ((Intent)localObject).getAction());
        paramContext.startActivity((Intent)localObject);
        Logger.d("SSC", "Go to gallery - Samsung gallery3d");
        return;
        if ((!Build.MODEL.contains("M250")) && (!Build.MODEL.contains("S7898")) && (!Build.MODEL.contains("I8262")) && (!Build.MODEL.contains("YP-GP1")) && (!Build.MODEL.contains("SHW-M430W")) && (!Build.MODEL.contains("GT-P6200")) && (!Build.MODEL.contains("GT-S6810P")) && (!Build.MODEL.contains("GT-P3100"))) {
          ((Intent)localObject).setAction("android.intent.action.VIEW");
        }
      }
    }
    if (CMUtil.isInstalledPackage("com.android.sec.gallery3d", paramContext))
    {
      localObject = new Intent();
      ((Intent)localObject).setClassName("com.android.sec.gallery3d", "com.android.sec.gallery3d.app.Gallery");
      ((Intent)localObject).addFlags(268435456);
      Logger.d("SSC", "Os version : " + Build.VERSION.RELEASE + " model : " + Build.MODEL);
      Logger.d("SSC", "intent.getAction : " + ((Intent)localObject).getAction());
      paramContext.startActivity((Intent)localObject);
      Logger.d("SSC", "Go to gallery - Samsung com.android.sec.gallery3d");
      return;
    }
    if (CMUtil.isInstalledPackage("com.cooliris.media", paramContext))
    {
      localObject = new Intent();
      ((Intent)localObject).setClassName("com.cooliris.media", "com.cooliris.media.Gallery");
      ((Intent)localObject).addFlags(268435456);
      paramContext.startActivity((Intent)localObject);
      Logger.d("SSC", "Go to gallery - cooliris");
      return;
    }
    if (CMUtil.isInstalledPackage("com.android.gallery3d", paramContext))
    {
      localObject = new Intent();
      ((Intent)localObject).setClassName("com.android.gallery3d", "com.android.gallery3d.app.Gallery");
      ((Intent)localObject).addFlags(268435456);
      paramContext.startActivity((Intent)localObject);
      Logger.d("SSC", "Go to gallery - Google gallery3d");
      return;
    }
    if (CMUtil.isInstalledPackage("com.htc.album", paramContext))
    {
      localObject = new Intent();
      ((Intent)localObject).setClassName("com.htc.album", "com.htc.album.AlbumMain.ActivityMainDropList");
      ((Intent)localObject).addFlags(268435456);
      paramContext.startActivity((Intent)localObject);
      Logger.d("SSC", "Go to gallery - HTC album");
      return;
    }
    if (CMUtil.isInstalledPackage("com.google.android.gallery3d", paramContext))
    {
      localObject = new Intent();
      ((Intent)localObject).setClassName("com.google.android.gallery3d", "com.android.gallery3d.app.Gallery");
      ((Intent)localObject).addFlags(268435456);
      paramContext.startActivity((Intent)localObject);
      Logger.d("SSC", "Go to gallery - Galaxy Nexus album");
      return;
    }
    if (CMUtil.isInstalledPackage("com.sonyericsson.album", paramContext))
    {
      localObject = new Intent();
      ((Intent)localObject).setClassName("com.sonyericsson.album", "com.sonyericsson.album.MainActivity");
      ((Intent)localObject).addFlags(268435456);
      paramContext.startActivity((Intent)localObject);
      Logger.d("SSC", "Go to gallery - Sony Xperia album");
      return;
    }
    Logger.d("SSC", "Go to gallery");
    Uri localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    Object localObject = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/Samsung Smart Camera Application";
    switch (CMMainActivity.ACTIVE_APP)
    {
    }
    for (;;)
    {
      localUri.buildUpon().appendQueryParameter("bucketId", String.valueOf(((String)localObject).toLowerCase().hashCode())).build();
      localObject = new File((String)localObject);
      if ((localObject != null) && (((File)localObject).list() != null) && (((File)localObject).list().length != 0)) {
        break;
      }
      Logger.d("SSC", "Go to gallery  - no file toast");
      Toast.makeText(paramContext, 2131099878, 0).show();
      return;
      localObject = localObject + "/AutoShare";
      continue;
      localObject = localObject + "/MobileLink";
      continue;
      localObject = localObject + "/RemoteViewfinder";
      continue;
      localObject = localObject + "/MobileLink";
    }
    Logger.d("SSC", "Go to gallery  - enter gallery folder");
    localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    ((Intent)localObject).setType("vnd.android.cursor.dir/image");
    ((Intent)localObject).addFlags(268435456);
    paramContext.startActivity((Intent)localObject);
  }
  
  public static String intToIp(int paramInt)
  {
    return (paramInt & 0xFF) + "." + (paramInt >> 8 & 0xFF) + "." + (paramInt >> 16 & 0xFF) + "." + (paramInt >> 24 & 0xFF);
  }
  
  public static boolean isICS()
  {
    return Build.MODEL.contains("Galaxy Nexus");
  }
  
  public static boolean isMemoryFull()
  {
    boolean bool = false;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      int i = localStatFs.getAvailableBlocks();
      if (i * l < 10000000L) {
        bool = true;
      }
      return bool;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean isMountedExternalStorage()
  {
    boolean bool = true;
    String str = Environment.getExternalStorageState();
    if ((str.equals("removed")) || (str.equals("shared")) || (str.equals("bad_removal")) || (str.equals("unmounted")) || (str.equals("checking")) || (str.equals("unmountable"))) {
      bool = false;
    }
    return bool;
  }
  
  public static boolean isTab101()
  {
    return Build.MODEL.contains("SHW-M380");
  }
  
  public static boolean isTopActivity(Context paramContext, String paramString)
  {
    Object localObject;
    if ((paramContext != null) && (paramString != null))
    {
      localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      paramContext = null;
      localObject = ((List)localObject).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        if (!paramContext.topActivity.getClassName().equals(paramString)) {
          break;
        }
        return true;
      }
      paramContext = (ActivityManager.RunningTaskInfo)((Iterator)localObject).next();
    }
    return false;
  }
  
  public static boolean isXlargeTablet()
  {
    return isXlargeTablet;
  }
  
  public static void noop(long paramLong)
  {
    try
    {
      Thread.sleep(paramLong);
      Thread.yield();
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  private static String rename(String paramString)
  {
    Matcher localMatcher = Pattern.compile("(\\-)(\\d*)+[.]").matcher(paramString);
    if (localMatcher.find())
    {
      int i = Integer.parseInt(localMatcher.group().replace("-", "").replace(".", ""));
      return paramString.replaceAll("(\\-)(\\d*)+[.]", "-" + (i + 1) + ".");
    }
    return paramString.substring(0, paramString.lastIndexOf(".")) + "-0" + paramString.substring(paramString.lastIndexOf("."), paramString.length());
  }
  
  public static String renameFile(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1, paramString2);
    String str = paramString2;
    for (paramString2 = localFile;; paramString2 = new File(paramString1, str))
    {
      if (!paramString2.exists()) {
        return str;
      }
      str = rename(str);
    }
  }
  
  public static void setDisplayConnGuide(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("connguide", paramBoolean);
    paramContext.commit();
  }
  
  public static void setDisplayDetailGuide(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("guide", paramBoolean);
    paramContext.commit();
  }
  
  public static void setDisplayNotice(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("notice", paramBoolean);
    paramContext.commit();
  }
  
  public static void setSystemConfigurationChanged(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration();
    if (paramContext.orientation == 1)
    {
      configString[0] = paramContext.toString();
      Logger.d("SSC", "setSystemConfigurationChanged configString[0] : " + configString[0]);
    }
    while (paramContext.orientation != 2) {
      return;
    }
    configString[1] = paramContext.toString();
    Logger.d("SSC", "setSystemConfigurationChanged configString[1] : " + configString[1]);
  }
  
  public static void setUseragent(Context paramContext)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("SEC_DSC_");
    Object localObject = null;
    String str = null;
    if (paramContext != null)
    {
      localObject = (TelephonyManager)paramContext.getSystemService("phone");
      if (((TelephonyManager)localObject).getSimState() != 1) {
        str = ((TelephonyManager)localObject).getLine1Number();
      }
      if (str != null)
      {
        localObject = str;
        if (str.length() != 0) {}
      }
      else
      {
        paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
        localObject = str;
        if (paramContext != null)
        {
          paramContext = paramContext.getMacAddress();
          localObject = str;
          if (paramContext != null) {
            localObject = paramContext.replace(":", "");
          }
        }
      }
    }
    if (localObject != null)
    {
      paramContext = (Context)localObject;
      if (((String)localObject).length() != 0) {}
    }
    else
    {
      paramContext = "UNKNOWN";
    }
    localStringBuffer.append(paramContext);
    userAgent = localStringBuffer.toString();
  }
  
  public static void setXlargeTablet()
  {
    isXlargeTablet = true;
  }
  
  public static long waitFor(long paramLong, IFunc paramIFunc)
  {
    long l3 = System.currentTimeMillis();
    long l1 = 0L;
    for (;;)
    {
      if (paramIFunc.func()) {
        return l1;
      }
      try
      {
        Thread.sleep(10L);
        Thread.yield();
        long l2 = System.currentTimeMillis() - l3;
        l1 = l2;
        if (l2 <= paramLong) {
          continue;
        }
        return l2;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  }
  
  public static abstract interface IFunc
  {
    public abstract boolean func();
  }
}
