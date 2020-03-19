package in.playsimple.wordtrip;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.TrafficStats;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.android.volley.a.m;
import com.android.volley.i;
import com.android.volley.j.a;
import com.android.volley.j.b;
import com.crashlytics.android.Crashlytics;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.perf.a;
import com.google.firebase.perf.metrics.Trace;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import org.cocos2dx.javascript.AppActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.Cocos2dxJavascriptJavaBridge;
import org.cocos2dx.lib.Cocos2dxLocalStorage;
import org.json.JSONObject;

public class Util
{
  private static Activity activity = null;
  static String appIndexingInfo;
  static boolean check = false;
  private static Context context = null;
  static boolean downloadImageInProgress;
  private static long fileListUpdateTime;
  private static JSONObject filesStored;
  public static boolean initJSComplete;
  private static boolean isNotifClicked;
  static boolean isPingWorking;
  static int lastDownloadCount;
  static long lastDownloadTime;
  private static long lastNetworkCheck;
  private static long lastPingCheck = 0L;
  static int maxAtOneTime;
  private static Trace myTrace;
  private static String notifString;
  private static String pushNotifType;
  private static boolean userIsNetworkConnected;
  private ShareDialog shareDialog;
  
  static
  {
    isPingWorking = true;
    lastNetworkCheck = 0L;
    userIsNetworkConnected = true;
    filesStored = null;
    fileListUpdateTime = 0L;
    initJSComplete = false;
    myTrace = null;
    isNotifClicked = false;
    notifString = "";
    pushNotifType = "";
    appIndexingInfo = "";
    maxAtOneTime = 21;
    downloadImageInProgress = false;
    lastDownloadTime = 0L;
    lastDownloadCount = 0;
  }
  
  public Util() {}
  
  public static void closeApp()
  {
    System.exit(1);
  }
  
  public static void contactUs(String paramString1, String paramString2, String paramString3)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in contactUs");
      return;
    }
    try
    {
      str = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          Game localGame = Game.get();
          Intent localIntent = new Intent("android.intent.action.SENDTO");
          localIntent.setData(Uri.parse("mailto:"));
          localIntent.putExtra("android.intent.extra.SUBJECT", activity.getString(2131427360) + " Android v" + str + " - " + paramString3);
          paramString1 = "UserId: " + paramString1 + ", Version: " + str + ", GameId: 9\n";
          paramString1 = paramString1 + "DeviceId: " + getDeviceId() + ",AdvID: " + getAdvertiserId() + ",OS:" + Build.VERSION.RELEASE + ", Language:" + Locale.getDefault().getDisplayLanguage() + ", Country:" + getCountry();
          paramString1 = paramString1 + ", Internet: " + isOnline();
          paramString2 = paramString1 + ", Issue: " + paramString2 + "\n";
          paramString1 = paramString2;
          if (localGame != null)
          {
            paramString1 = paramString2 + ", Puzzle Solved: " + localGame.getPuzCompleted();
            paramString1 = paramString1 + ", PNP: " + localGame.getIsPayer();
          }
          localIntent.putExtra("android.intent.extra.TEXT", paramString1 + "-----------------------\nPlease write below this line\n-----------------------\n");
          localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "support@playsimple.in" });
          paramString1 = Intent.createChooser(localIntent, null);
          activity.startActivity(paramString1);
          return;
          localException1 = localException1;
          String str = "0";
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          Object localObject = null;
        }
      }
    }
  }
  
  public static boolean deleteFile(String paramString)
  {
    if (filesStored != null) {
      filesStored.remove(paramString);
    }
    Cocos2dxLocalStorage.setItem("wotr_file_list.json", filesStored.toString());
    return context.deleteFile(paramString);
  }
  
  public static boolean didCrashOnLastLoad()
  {
    return false;
  }
  
  public static void downloadFile(String paramString1, String paramString2, final String paramString3, final String paramString4, final String paramString5)
  {
    paramString2 = new VolleyFileRequest(paramString2 + paramString3, new j.b()new j.a
    {
      public final void onResponse(byte[] paramAnonymousArrayOfByte)
      {
        Log.i("WordTrip", "Response success for byte resp:" + this.val$saveAs + "in " + Util.getFilesDirPath());
        if (paramString3.equals(".gz")) {}
        for (;;)
        {
          try
          {
            Util.writeToStream(new GZIPInputStream(new ByteArrayInputStream(paramAnonymousArrayOfByte)), Util.getOutputStream(this.val$saveAs));
            if (Util.filesStored != null)
            {
              Util.filesStored.put(this.val$saveAs, true);
              Util.updateFileListFile();
            }
            if (!paramString4.equals("")) {
              Util.sendJSCallBack(paramString4, paramString5);
            }
            return;
          }
          catch (Exception paramAnonymousArrayOfByte)
          {
            Crashlytics.logException(paramAnonymousArrayOfByte);
            continue;
          }
          Util.saveFileAs(this.val$saveAs, paramAnonymousArrayOfByte);
        }
      }
    }, new j.a()
    {
      public final void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        paramAnonymousVolleyError.printStackTrace();
      }
    }, null);
    Log.i("WordTrip", "Volley request: " + paramString1);
    m.a(activity.getApplicationContext(), new com.android.volley.a.j()).a(paramString2);
  }
  
  public static void downloadImages(String paramString)
  {
    int i = 0;
    if (!isPingWorking()) {}
    for (;;)
    {
      return;
      paramString = Cocos2dxLocalStorage.getItem(paramString);
      if (downloadImageInProgress)
      {
        log("Download already in progress");
        return;
      }
      long l = getCurrentTimestampMs();
      if ((l - lastDownloadTime < 90000L) && (lastDownloadCount > 2))
      {
        log("Last download was less than 90 seconds ago");
        return;
      }
      lastDownloadTime = l;
      Log.d("WordTrip", "download image started " + paramString);
      paramString = paramString.split(";");
      lastDownloadCount = 0;
      int m = 0;
      while ((m < paramString.length) && (i < maxAtOneTime))
      {
        String[] arrayOfString = paramString[m].split(",");
        j = i;
        k = i;
        try
        {
          if (!fileExists(arrayOfString[1]))
          {
            j = i + 1;
            k = j;
            downloadFile(arrayOfString[1], arrayOfString[0], "", "", "");
            k = j;
            Log.d("WordTrip", "Downloading " + m + "th image :: " + arrayOfString[1]);
            k = j;
            lastDownloadCount += 1;
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
            j = k;
          }
        }
        m += 1;
        i = j;
      }
    }
  }
  
  public static boolean fileExists(String paramString)
  {
    if (filesStored != null)
    {
      if (filesStored.has(paramString))
      {
        paramString = (Boolean)filesStored.get(paramString);
        if (paramString != null) {
          return paramString.booleanValue();
        }
      }
      return false;
    }
    try
    {
      context.openFileInput(paramString);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static String getAdvertiserId()
  {
    try
    {
      String str = AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static String getAppIndexingInfo()
  {
    return appIndexingInfo;
  }
  
  public static String getBaseDirPath()
  {
    return context.getCacheDir().getParent();
  }
  
  public static String getBuildVersion()
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in contactUs");
      return "0";
    }
    try
    {
      String str = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException) {}
    return "0";
  }
  
  private static byte[] getByteArrayFromInputStream(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      byte[] arrayOfByte = new byte['田'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte, 0, 30000);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      return localByteArrayOutputStream.toByteArray();
    }
    catch (Exception paramInputStream)
    {
      Crashlytics.logException(paramInputStream);
    }
    for (;;)
    {
      localByteArrayOutputStream.flush();
    }
  }
  
  public static String getCacheDirPath()
  {
    return context.getCacheDir().getAbsolutePath();
  }
  
  public static String getCountry()
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in getCountry");
      return "";
    }
    try
    {
      String str1 = ((TelephonyManager)activity.getSystemService("phone")).getSimCountryIso();
      return str1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
        String str2 = "";
      }
    }
  }
  
  public static String getCpuArch()
  {
    return Build.CPU_ABI;
  }
  
  public static long getCurrentTimestamp()
  {
    return getCurrentTimestampMs() / 1000L;
  }
  
  public static long getCurrentTimestampMs()
  {
    return System.currentTimeMillis();
  }
  
  public static int getDayOfYear()
  {
    return Calendar.getInstance().get(6);
  }
  
  public static int getDeviceHeight()
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in getDeviceHeight");
      return 0;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static String getDeviceId()
  {
    if (context == null)
    {
      Log.i("WordTrip", "Context not set in getDeviceId");
      return "";
    }
    return Settings.Secure.getString(context.getContentResolver(), "android_id");
  }
  
  public static String getDeviceModel()
  {
    return Build.MODEL;
  }
  
  public static int getDeviceWidth()
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in getDeviceWidth");
      return 0;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  private static String getEvalString(String paramString1, String paramString2)
  {
    paramString2 = paramString2.replace("\"", "\\\"");
    return paramString1 + "(\"" + paramString2 + "\")";
  }
  
  public static String getExternalBaseDirPath()
  {
    try
    {
      String str = context.getExternalCacheDir().getParent();
      return str;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static String getFailReason(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "not_defined";
    case -1: 
      return "not_defined";
    case -2: 
      return "no_fbconnect";
    case -3: 
      return "no_invitable";
    }
    return "no_image";
  }
  
  public static String[] getFileList()
  {
    String[] arrayOfString = context.fileList();
    filesStored = new JSONObject();
    int i = 0;
    while (i < arrayOfString.length)
    {
      String str = arrayOfString[i];
      filesStored.put(str, true);
      i += 1;
    }
    Cocos2dxLocalStorage.setItem("wotr_file_list.json", filesStored.toString());
    return arrayOfString;
  }
  
  public static String getFilesDirPath()
  {
    return context.getFilesDir().getAbsolutePath();
  }
  
  public static String getFirstName(String paramString)
  {
    String str = paramString.split("\\s+")[0];
    paramString = str;
    if (str.length() > 10) {
      paramString = str.substring(0, 10);
    }
    return paramString.replace(";", "").replace(",", "");
  }
  
  public static String getIpAddress()
  {
    try
    {
      int i = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo().getIpAddress();
      if (i == 0) {
        return getLocalIpAddress();
      }
      String str = String.format("%d.%d.%d.%d", new Object[] { Integer.valueOf(i & 0xFF), Integer.valueOf(i >> 8 & 0xFF), Integer.valueOf(i >> 16 & 0xFF), Integer.valueOf(i >> 24 & 0xFF) });
      return str;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
    }
    return "";
  }
  
  public static boolean getIsNotifClicked()
  {
    return isNotifClicked;
  }
  
  public static String getLocalIpAddress()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while ((localInetAddress.isLoopbackAddress()) || (localInetAddress.getAddress().length != 4));
      Object localObject = localInetAddress.getHostAddress();
      return localObject;
    }
    catch (SocketException localSocketException)
    {
      Crashlytics.logException(localSocketException);
    }
    return "";
  }
  
  public static String getNotifString()
  {
    Log.i("WordTrip", "this is val notif " + notifString);
    return notifString;
  }
  
  public static int getOsCode()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getOsVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  private static FileOutputStream getOutputStream(String paramString)
  {
    try
    {
      paramString = context.openFileOutput(paramString, 0);
      return paramString;
    }
    catch (Exception paramString)
    {
      Crashlytics.logException(paramString);
    }
    return null;
  }
  
  public static String getParseId()
  {
    String str2 = FirebaseInstanceId.a().d();
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    return str1;
  }
  
  public static String getPushNotifType()
  {
    return AppActivity.getPushNotifType();
  }
  
  public static String getPushNotifTypeV1()
  {
    Log.i("WordTrip", "this is val get " + pushNotifType);
    return pushNotifType;
  }
  
  public static int getRandomIntInRange(int paramInt1, int paramInt2)
  {
    return (int)(Math.random() * (paramInt2 - paramInt1)) + paramInt1;
  }
  
  public static String getScreenQuestId()
  {
    return activity.getSharedPreferences("pushNotifPrefFlag", 0).getString("show_screen_quest_id", "nil");
  }
  
  public static int getScreenVal()
  {
    return activity.getSharedPreferences("pushNotifPref", 0).getInt("screen_val", -1);
  }
  
  public static String getShowScreenQuestId()
  {
    return activity.getSharedPreferences("pushNotifPref", 0).getString("showScreenQuestId", "");
  }
  
  public static int getSystemUpTime()
  {
    return (int)(SystemClock.elapsedRealtime() / 1000L);
  }
  
  public static double getTotalDataUsage()
  {
    return TrafficStats.getUidRxBytes(Process.myUid()) / 1024.0D;
  }
  
  public static int getVersionCode()
  {
    if (context == null)
    {
      Log.i("WordTrip", "Context not set in getVersionCode");
      return 0;
    }
    try
    {
      int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public static int getYesterdaysDayOfYear()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(5, -1);
    return localCalendar.get(6);
  }
  
  public static int getshowScreenVal()
  {
    return activity.getSharedPreferences("pushNotifPrefFlag", 0).getInt("screen_val", -1);
  }
  
  public static boolean hasNetworkConnectivity()
  {
    long l = getCurrentTimestamp();
    if (l - lastNetworkCheck < 5L) {
      return userIsNetworkConnected;
    }
    if (context == null)
    {
      Log.i("WordTrip", "Context not set in hasNetworkConnectivity");
      return false;
    }
    lastNetworkCheck = l;
    if (((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {}
    for (userIsNetworkConnected = false;; userIsNetworkConnected = true) {
      return userIsNetworkConnected;
    }
  }
  
  public static void initJSComplete()
  {
    initJSComplete = true;
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    Iterator localIterator = context.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isCallable(Intent paramIntent, Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return false;
      paramIntent = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536);
    } while ((paramIntent == null) || (paramIntent.size() <= 0));
    return true;
  }
  
  public static boolean isErrorCode(int paramInt)
  {
    return (paramInt == -1) || (paramInt == -2) || (paramInt == -3) || (paramInt == -4);
  }
  
  public static boolean isFbInstalled()
  {
    return (isAppInstalled("com.facebook.katana")) || (isAppInstalled("com.facebook.lite")) || (isAppInstalled("com.facebook.android"));
  }
  
  public static boolean isNumeric(String paramString)
  {
    try
    {
      Double.parseDouble(paramString);
      return true;
    }
    catch (NumberFormatException paramString) {}
    return false;
  }
  
  public static boolean isOnline()
  {
    return hasNetworkConnectivity();
  }
  
  public static boolean isPingWorking()
  {
    return true;
  }
  
  public static boolean isSoundPlayable()
  {
    try
    {
      ((AudioManager)activity.getSystemService("audio")).getRingerMode();
      return true;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
  }
  
  public static boolean isTablet()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i = localDisplayMetrics.widthPixels / localDisplayMetrics.densityDpi;
    int j = localDisplayMetrics.heightPixels / localDisplayMetrics.densityDpi;
    return Math.sqrt(j * j + i * i) >= 5.7D;
  }
  
  public static boolean isTabletLayout(Context paramContext)
  {
    boolean bool = false;
    int i;
    if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) == 4)
    {
      i = 1;
      if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) != 3) {
        break label57;
      }
    }
    label57:
    for (int j = 1;; j = 0)
    {
      if ((i != 0) || (j != 0)) {
        bool = true;
      }
      return bool;
      i = 0;
      break;
    }
  }
  
  public static boolean isTriggeredFromPushNotif()
  {
    return AppActivity.checkIfTriggerFromPushNotif();
  }
  
  public static boolean launchAnotherGame(String paramString)
  {
    paramString = context.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString != null)
    {
      context.startActivity(paramString);
      return true;
    }
    return false;
  }
  
  public static boolean levelUpShareTwtr(String paramString)
  {
    return shareTweet("levelup", paramString);
  }
  
  public static void log(String paramString)
  {
    Log.i("WordTrip", paramString);
  }
  
  public static void logFreeMemory()
  {
    try
    {
      Runtime.getRuntime().freeMemory();
      return;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
    }
  }
  
  public static boolean moveScreenshot(String paramString1, String paramString2)
  {
    Bitmap localBitmap = BitmapFactory.decodeFile(Cocos2dxHelper.getCocos2dxWritablePath() + "/" + paramString1);
    if (localBitmap == null)
    {
      Log.i("WordTrip", "Bitmap got read as null:" + paramString1);
      return false;
    }
    return writeBitmapToFile(localBitmap, paramString1, paramString2);
  }
  
  private static void openScreenshot(File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), "image/*");
    activity.startActivity(localIntent);
  }
  
  public static void openUrl(String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      activity.startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  public static float readCpuUsage()
  {
    // Byte code:
    //   0: new 934	java/io/RandomAccessFile
    //   3: dup
    //   4: ldc_w 936
    //   7: ldc_w 938
    //   10: invokespecial 940	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   13: astore 16
    //   15: aload 16
    //   17: invokevirtual 943	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   20: ldc_w 945
    //   23: invokevirtual 375	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   26: astore 17
    //   28: aload 17
    //   30: iconst_4
    //   31: aaload
    //   32: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   35: lstore_0
    //   36: aload 17
    //   38: iconst_2
    //   39: aaload
    //   40: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   43: aload 17
    //   45: iconst_3
    //   46: aaload
    //   47: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   50: ladd
    //   51: aload 17
    //   53: iconst_5
    //   54: aaload
    //   55: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   58: ladd
    //   59: aload 17
    //   61: bipush 6
    //   63: aaload
    //   64: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   67: ladd
    //   68: aload 17
    //   70: bipush 7
    //   72: aaload
    //   73: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   76: ladd
    //   77: aload 17
    //   79: bipush 8
    //   81: aaload
    //   82: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   85: ladd
    //   86: lstore_2
    //   87: ldc 122
    //   89: new 180	java/lang/StringBuilder
    //   92: dup
    //   93: ldc_w 953
    //   96: invokespecial 204	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   99: lload_2
    //   100: invokevirtual 956	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   103: ldc_w 371
    //   106: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: lload_0
    //   110: invokevirtual 956	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   113: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 130	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   119: pop
    //   120: ldc2_w 957
    //   123: invokestatic 964	java/lang/Thread:sleep	(J)V
    //   126: aload 16
    //   128: lconst_0
    //   129: invokevirtual 967	java/io/RandomAccessFile:seek	(J)V
    //   132: aload 16
    //   134: invokevirtual 943	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   137: astore 17
    //   139: aload 16
    //   141: invokevirtual 970	java/io/RandomAccessFile:close	()V
    //   144: aload 17
    //   146: ldc_w 945
    //   149: invokevirtual 375	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   152: astore 16
    //   154: aload 16
    //   156: iconst_4
    //   157: aaload
    //   158: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   161: lstore 4
    //   163: aload 16
    //   165: iconst_2
    //   166: aaload
    //   167: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   170: lstore 6
    //   172: aload 16
    //   174: iconst_3
    //   175: aaload
    //   176: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   179: lstore 8
    //   181: aload 16
    //   183: iconst_5
    //   184: aaload
    //   185: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   188: lstore 10
    //   190: aload 16
    //   192: bipush 6
    //   194: aaload
    //   195: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   198: lstore 12
    //   200: aload 16
    //   202: bipush 7
    //   204: aaload
    //   205: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   208: lstore 14
    //   210: aload 16
    //   212: bipush 8
    //   214: aaload
    //   215: invokestatic 951	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   218: lload 6
    //   220: lload 8
    //   222: ladd
    //   223: lload 10
    //   225: ladd
    //   226: lload 12
    //   228: ladd
    //   229: lload 14
    //   231: ladd
    //   232: ladd
    //   233: lstore 6
    //   235: ldc 122
    //   237: new 180	java/lang/StringBuilder
    //   240: dup
    //   241: ldc_w 972
    //   244: invokespecial 204	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   247: lload 6
    //   249: invokevirtual 956	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   252: ldc_w 371
    //   255: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: lload 4
    //   260: invokevirtual 956	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   263: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: invokestatic 130	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   269: pop
    //   270: lload 6
    //   272: lload_2
    //   273: lsub
    //   274: l2f
    //   275: lload 6
    //   277: lload 4
    //   279: ladd
    //   280: lload_0
    //   281: lload_2
    //   282: ladd
    //   283: lsub
    //   284: l2f
    //   285: fdiv
    //   286: freturn
    //   287: astore 16
    //   289: aload 16
    //   291: invokevirtual 973	java/io/IOException:printStackTrace	()V
    //   294: fconst_0
    //   295: freturn
    //   296: astore 17
    //   298: goto -172 -> 126
    // Local variable table:
    //   start	length	slot	name	signature
    //   35	246	0	l1	long
    //   86	196	2	l2	long
    //   161	117	4	l3	long
    //   170	106	6	l4	long
    //   179	42	8	l5	long
    //   188	36	10	l6	long
    //   198	29	12	l7	long
    //   208	22	14	l8	long
    //   13	198	16	localObject1	Object
    //   287	3	16	localIOException	IOException
    //   26	119	17	localObject2	Object
    //   296	1	17	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   0	120	287	java/io/IOException
    //   120	126	287	java/io/IOException
    //   126	270	287	java/io/IOException
    //   120	126	296	java/lang/Exception
  }
  
  private static void saveFileAs(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      FileOutputStream localFileOutputStream = getOutputStream(paramString);
      localFileOutputStream.write(paramArrayOfByte);
      localFileOutputStream.close();
      if (filesStored != null)
      {
        filesStored.put(paramString, true);
        updateFileListFile();
      }
      return;
    }
    catch (Exception paramArrayOfByte)
    {
      Crashlytics.logException(paramArrayOfByte);
      Log.e("WordTrip", "Failed to save fileName:" + paramString);
    }
  }
  
  public static void screenTraceStart(String paramString)
  {
    Log.i("WordTrip", "screen trace is started" + paramString);
    if (myTrace == null)
    {
      if (!isTablet()) {
        break label72;
      }
      a.a();
    }
    for (myTrace = a.a(paramString + "_tablet");; myTrace = a.a(paramString))
    {
      myTrace.start();
      return;
      label72:
      a.a();
    }
  }
  
  public static void screenTraceStop()
  {
    Log.i("WordTrip", "screen trace is stopped");
    myTrace.stop();
    myTrace = null;
  }
  
  private static void sendJSCallBack(String paramString)
  {
    if (!initJSComplete) {
      return;
    }
    Cocos2dxGLSurfaceView.getInstance().queueEvent(new Runnable()
    {
      public final void run()
      {
        Log.d("WordTrip", "JS callback:" + this.val$msg);
        Cocos2dxJavascriptJavaBridge.evalString(this.val$msg);
      }
    });
  }
  
  public static void sendJSCallBack(String paramString1, String paramString2)
  {
    sendJSCallBack(getEvalString(paramString1, paramString2));
  }
  
  public static void setActivity(Activity paramActivity)
  {
    activity = paramActivity;
    context = paramActivity;
  }
  
  public static void setAppIndexingInfo(String paramString)
  {
    appIndexingInfo = paramString;
  }
  
  public static void setContext(Context paramContext)
  {
    context = paramContext;
  }
  
  public static void setIsNotifClicked(boolean paramBoolean)
  {
    isNotifClicked = paramBoolean;
  }
  
  public static void setNotifString(String paramString)
  {
    notifString = paramString;
    Log.i("WordTrip", "this is val " + paramString);
  }
  
  public static void setPushNotifType(String paramString)
  {
    pushNotifType = paramString;
    Log.i("WordTrip", "this is val 11 " + paramString);
  }
  
  public static void setupAlarmAfter(int paramInt1, int paramInt2)
  {
    if (context == null)
    {
      Log.i("WordTrip", "Context not set in setupAlarmAfter");
      return;
    }
    Log.i("WordTrip", "Setting up alarm after " + paramInt1);
    Object localObject1 = new Intent(context, AlarmReceiver.class);
    ((Intent)localObject1).setFlags(536870912);
    Object localObject2 = new JSONObject();
    try
    {
      ((JSONObject)localObject2).put("type", paramInt2);
      Object localObject3 = new Bundle();
      ((Bundle)localObject3).putString("info", ((JSONObject)localObject2).toString());
      ((Intent)localObject1).putExtras((Bundle)localObject3);
      localObject1 = PendingIntent.getBroadcast(context, paramInt2, (Intent)localObject1, 134217728);
      localObject2 = Calendar.getInstance();
      ((Calendar)localObject2).add(12, paramInt1);
      localObject3 = (AlarmManager)context.getSystemService("alarm");
      ((AlarmManager)localObject3).cancel((PendingIntent)localObject1);
      ((AlarmManager)localObject3).set(1, ((Calendar)localObject2).getTimeInMillis(), (PendingIntent)localObject1);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
      }
    }
  }
  
  public static void setupDailyAlarm(int paramInt)
  {
    if (context == null)
    {
      Log.i("WordTrip", "Context not set in setupDailyAlarm");
      return;
    }
    Log.i("WordTrip", "Setting up daily alarm");
    Object localObject1 = new Intent(context, AlarmReceiver.class);
    ((Intent)localObject1).setFlags(536870912);
    Object localObject2 = new JSONObject();
    try
    {
      ((JSONObject)localObject2).put("type", 1);
      localBundle = new Bundle();
      localBundle.putString("info", ((JSONObject)localObject2).toString());
      ((Intent)localObject1).putExtras(localBundle);
      PendingIntent localPendingIntent = PendingIntent.getBroadcast(context, 1, (Intent)localObject1, 134217728);
      Calendar localCalendar = Calendar.getInstance();
      paramInt = localCalendar.get(11);
      i = getRandomIntInRange(0, 60);
      Log.i("WordTrip", "next alarm minutes:: " + i);
      if (((paramInt == 13) && (localCalendar.get(12) >= i)) || (paramInt > 13)) {
        localCalendar.add(5, 1);
      }
      localCalendar.set(10, 1);
      localCalendar.set(11, 13);
      localCalendar.set(12, i);
      localCalendar.set(13, 0);
      localCalendar.set(9, 1);
      localAlarmManager = (AlarmManager)context.getSystemService("alarm");
      localAlarmManager.cancel(localPendingIntent);
      localAlarmManager.setInexactRepeating(1, localCalendar.getTimeInMillis(), 86400000L, localPendingIntent);
      Log.i("WordTrip", "Setting up daily alarm in Android:" + localCalendar);
    }
    catch (Exception localException1)
    {
      try
      {
        Bundle localBundle;
        int i;
        AlarmManager localAlarmManager;
        ((JSONObject)localObject2).put("type", 2);
        localBundle.putString("info", ((JSONObject)localObject2).toString());
        ((Intent)localObject1).putExtras(localBundle);
        localObject1 = PendingIntent.getBroadcast(context, 2, (Intent)localObject1, 134217728);
        localObject2 = Calendar.getInstance();
        if (((paramInt == 18) && (((Calendar)localObject2).get(12) >= i)) || (paramInt > 18)) {
          ((Calendar)localObject2).add(5, 1);
        }
        ((Calendar)localObject2).set(10, 6);
        ((Calendar)localObject2).set(11, 18);
        ((Calendar)localObject2).set(12, i);
        ((Calendar)localObject2).set(13, 0);
        ((Calendar)localObject2).set(9, 1);
        localAlarmManager.cancel((PendingIntent)localObject1);
        localAlarmManager.setInexactRepeating(1, ((Calendar)localObject2).getTimeInMillis(), 86400000L, (PendingIntent)localObject1);
        return;
        localException1 = localException1;
        Crashlytics.logException(localException1);
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          Crashlytics.logException(localException2);
        }
      }
    }
  }
  
  public static void setupManualAlarm(int paramInt, boolean paramBoolean)
  {
    Log.i("WordTrip", "Setting up manual alarm: Days = " + paramInt + " CurPlayer = " + paramBoolean);
    Object localObject1 = new Intent(context, AlarmReceiver.class);
    ((Intent)localObject1).setFlags(536870912);
    Object localObject2 = new JSONObject();
    try
    {
      ((JSONObject)localObject2).put("type", 1);
      Object localObject3 = new Bundle();
      ((Bundle)localObject3).putString("info", ((JSONObject)localObject2).toString());
      ((Intent)localObject1).putExtras((Bundle)localObject3);
      localObject1 = PendingIntent.getBroadcast(context, 1, (Intent)localObject1, 134217728);
      localObject2 = Calendar.getInstance();
      localObject3 = (AlarmManager)context.getSystemService("alarm");
      ((AlarmManager)localObject3).cancel((PendingIntent)localObject1);
      if (Build.VERSION.SDK_INT >= 19)
      {
        ((AlarmManager)localObject3).setExact(1, ((Calendar)localObject2).getTimeInMillis() + 2000L, (PendingIntent)localObject1);
        AlarmReceiver.testCurPlayer = paramBoolean;
        AlarmReceiver.testDays = paramInt;
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
        continue;
        Log.i("WordTrip", "Error in Trigger notifs - API version too low for alarm:" + Build.VERSION.SDK_INT);
      }
    }
  }
  
  public static void shareAnywhere(String paramString)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in shareAnywhere");
      return;
    }
    Track.setContext(activity);
    User.setContext(activity);
    try
    {
      User.get();
      try
      {
        Track.trackCounter("free_cash", "invite", "others", "", "", "", "", "1", "");
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/plain");
        localIntent.putExtra("android.intent.extra.TEXT", paramString);
        activity.startActivity(Intent.createChooser(localIntent, "Share with"));
        return;
      }
      catch (Exception paramString)
      {
        showMessage(activity.getResources().getString(2131427413));
        return;
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean shareOnGooglePlus(String paramString)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in shareOnTumblr");
      return false;
    }
    Track.setContext(activity);
    User.setContext(activity);
    try
    {
      User.get();
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramString);
      localIntent.setPackage("com.google.android.apps.plus");
      if (isCallable(localIntent, activity))
      {
        activity.startActivity(localIntent);
        return true;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    showMessage(activity.getResources().getString(2131427441));
    return false;
  }
  
  public static boolean shareOnMail(String paramString)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in contactUs");
      return false;
    }
    Intent localIntent = new Intent("android.intent.action.SENDTO");
    paramString = "" + paramString;
    localIntent.putExtra("android.intent.extra.SUBJECT", "Let's go on a WordTrip!");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    localIntent.setData(Uri.parse("mailto:"));
    paramString = Intent.createChooser(localIntent, null);
    activity.startActivity(paramString);
    return true;
  }
  
  public static boolean shareOnMessenger(String paramString)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in shareOnMessenger");
      return false;
    }
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    localIntent.setPackage("com.facebook.orca");
    if (isCallable(localIntent, activity))
    {
      activity.startActivity(localIntent);
      return true;
    }
    showMessage(activity.getResources().getString(2131427442));
    return false;
  }
  
  public static boolean shareOnSms(String paramString)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in shareOnSms");
      return false;
    }
    Track.setContext(activity);
    Track.trackCounter("free_cash", "invite", "sms", "", "", "", "", "1", "");
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("sms:"));
      localIntent.putExtra("android.intent.extra.TEXT", paramString);
      localIntent.putExtra("sms_body", paramString);
      activity.startActivity(localIntent);
      return true;
    }
    catch (Exception paramString)
    {
      showMessage(activity.getResources().getString(2131427413));
    }
    return false;
  }
  
  public static boolean shareOnWhatsapp(String paramString)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in shareOnWhatsapp");
      return false;
    }
    Track.setContext(activity);
    User.setContext(activity);
    try
    {
      User.get();
      Track.trackCounter("free_cash", "invite", "whatsapp", "", "", "", "", "1", "");
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setPackage("com.whatsapp");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramString);
      if (isCallable(localIntent, activity))
      {
        activity.startActivity(localIntent);
        return true;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    showMessage(activity.getResources().getString(2131427444));
    Track.trackCounter("free_cash", "invite", "whatsapp", "not_installed", "", "", "", "0", "");
    return false;
  }
  
  /* Error */
  public static void sharePhotoAndUrlAnywhere(String paramString)
  {
    // Byte code:
    //   0: getstatic 53	in/playsimple/wordtrip/Util:activity	Landroid/app/Activity;
    //   3: ifnonnull +13 -> 16
    //   6: ldc 122
    //   8: ldc_w 1218
    //   11: invokestatic 130	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   14: pop
    //   15: return
    //   16: getstatic 53	in/playsimple/wordtrip/Util:activity	Landroid/app/Activity;
    //   19: invokestatic 1143	in/playsimple/wordtrip/Track:setContext	(Landroid/content/Context;)V
    //   22: getstatic 53	in/playsimple/wordtrip/Util:activity	Landroid/app/Activity;
    //   25: invokestatic 1146	in/playsimple/wordtrip/User:setContext	(Landroid/content/Context;)V
    //   28: invokestatic 1149	in/playsimple/wordtrip/User:get	()Lin/playsimple/wordtrip/User;
    //   31: pop
    //   32: new 180	java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial 181	java/lang/StringBuilder:<init>	()V
    //   39: invokestatic 1220	in/playsimple/wordtrip/Util:getFilesDirPath	()Ljava/lang/String;
    //   42: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc_w 1222
    //   48: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: invokestatic 900	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   57: astore_2
    //   58: new 422	java/io/File
    //   61: dup
    //   62: new 180	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 181	java/lang/StringBuilder:<init>	()V
    //   69: getstatic 53	in/playsimple/wordtrip/Util:activity	Landroid/app/Activity;
    //   72: invokevirtual 1223	android/app/Activity:getExternalCacheDir	()Ljava/io/File;
    //   75: invokevirtual 1117	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   78: ldc_w 1225
    //   81: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokespecial 1226	java/io/File:<init>	(Ljava/lang/String;)V
    //   90: astore_1
    //   91: new 975	java/io/FileOutputStream
    //   94: dup
    //   95: aload_1
    //   96: invokespecial 1228	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   99: astore_3
    //   100: aload_2
    //   101: getstatic 1234	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   104: bipush 100
    //   106: aload_3
    //   107: invokevirtual 1240	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   110: pop
    //   111: aload_3
    //   112: invokevirtual 1243	java/io/OutputStream:flush	()V
    //   115: aload_3
    //   116: invokevirtual 1244	java/io/OutputStream:close	()V
    //   119: aload_1
    //   120: invokevirtual 1247	java/io/File:getPath	()Ljava/lang/String;
    //   123: astore_1
    //   124: new 180	java/lang/StringBuilder
    //   127: dup
    //   128: ldc_w 1249
    //   131: invokespecial 204	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   134: aload_1
    //   135: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   141: invokestatic 172	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   144: astore_1
    //   145: new 159	android/content/Intent
    //   148: dup
    //   149: ldc_w 1163
    //   152: invokespecial 164	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   155: astore_2
    //   156: aload_2
    //   157: ldc_w 1250
    //   160: invokevirtual 1056	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   163: pop
    //   164: aload_2
    //   165: ldc_w 1252
    //   168: aload_1
    //   169: invokevirtual 1255	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   172: pop
    //   173: aload_2
    //   174: ldc_w 270
    //   177: aload_0
    //   178: invokevirtual 201	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   181: pop
    //   182: aload_2
    //   183: ldc -78
    //   185: ldc_w 1257
    //   188: invokevirtual 201	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   191: pop
    //   192: aload_2
    //   193: ldc_w 1259
    //   196: invokevirtual 1168	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   199: pop
    //   200: getstatic 53	in/playsimple/wordtrip/Util:activity	Landroid/app/Activity;
    //   203: aload_2
    //   204: ldc_w 1170
    //   207: invokestatic 285	android/content/Intent:createChooser	(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   210: invokevirtual 289	android/app/Activity:startActivity	(Landroid/content/Intent;)V
    //   213: return
    //   214: astore_0
    //   215: getstatic 53	in/playsimple/wordtrip/Util:activity	Landroid/app/Activity;
    //   218: invokevirtual 1171	android/app/Activity:getResources	()Landroid/content/res/Resources;
    //   221: ldc_w 1172
    //   224: invokevirtual 1173	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   227: invokestatic 1176	in/playsimple/wordtrip/Util:showMessage	(Ljava/lang/String;)V
    //   230: return
    //   231: astore_0
    //   232: aload_0
    //   233: invokevirtual 389	java/lang/Exception:printStackTrace	()V
    //   236: return
    //   237: astore_2
    //   238: aload_2
    //   239: invokevirtual 389	java/lang/Exception:printStackTrace	()V
    //   242: goto -123 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	245	0	paramString	String
    //   90	79	1	localObject1	Object
    //   57	147	2	localObject2	Object
    //   237	2	2	localException	Exception
    //   99	17	3	localFileOutputStream	FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   32	91	214	java/lang/Exception
    //   119	213	214	java/lang/Exception
    //   238	242	214	java/lang/Exception
    //   28	32	231	java/lang/Exception
    //   91	119	237	java/lang/Exception
  }
  
  public static void shareTextAnywhere(String paramString)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in shareTextAnywhere");
      return;
    }
    Track.setContext(activity);
    User.setContext(activity);
    try
    {
      User.get();
      try
      {
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/plain");
        localIntent.putExtra("android.intent.extra.TEXT", paramString);
        activity.startActivity(Intent.createChooser(localIntent, "Share with"));
        return;
      }
      catch (Exception paramString)
      {
        showMessage(activity.getResources().getString(2131427413));
        return;
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean shareTweet(String paramString1, String paramString2)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in shareTwitter");
      return false;
    }
    Track.setContext(activity);
    User.setContext(activity);
    try
    {
      User.get();
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setPackage("com.twitter.android");
      localIntent.setType("text/plain");
      localIntent.putExtra("android.intent.extra.TEXT", paramString2);
      if (isCallable(localIntent, activity))
      {
        activity.startActivity(localIntent);
        return true;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      return false;
    }
    showMessage(activity.getResources().getString(2131427443));
    Track.trackCounter(paramString1, "invite", "twtr", "not_installed", "", "", "", "0", "");
    return false;
  }
  
  public static boolean shareTwtr(String paramString)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in shareOnTwtr");
      return false;
    }
    Track.setContext(activity);
    Track.trackCounter("free_cash", "invite", "twitter", "", "", "", "", "1", "");
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setPackage("com.twitter.android");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    if (isCallable(localIntent, activity))
    {
      activity.startActivity(localIntent);
      return true;
    }
    showMessage(activity.getResources().getString(2131427443));
    Track.trackCounter("free_cash", "invite", "whatsapp", "not_installed", "", "", "", "0", "");
    return false;
  }
  
  public static void showMessage(String paramString)
  {
    if (activity == null)
    {
      Log.i("WordTrip", "Activity not set in showMessage");
      return;
    }
    activity.runOnUiThread(new Runnable()
    {
      public final void run()
      {
        Toast localToast = Toast.makeText(Util.activity, this.val$msg, 0);
        localToast.setGravity(17, 0, 0);
        localToast.show();
      }
    });
  }
  
  public static void showNativeMagicPopup(String paramString)
  {
    activity.runOnUiThread(new Runnable()
    {
      public final void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(Cocos2dxHelper.getActivity());
        localBuilder.setMessage(this.val$msg).setTitle(2131427436);
        localBuilder.setPositiveButton(2131427419, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Util.sendJSCallBack("utilObj.forceSync", "Force sync button clicked");
          }
        });
        localBuilder.setNegativeButton(2131427362, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        });
        localBuilder.create().show();
      }
    });
  }
  
  public static void showVideoWithPlacementId(String paramString1, final String paramString2)
  {
    Log.d("SJ", "Reached video");
    activity.runOnUiThread(new Runnable()
    {
      public final void run()
      {
        try
        {
          Log.d("WordTrip", "calling showVideo " + com.ironsource.mediationsdk.j.a().i());
          SupersonicContent.get(Util.activity);
          SupersonicContent.showVideo(this.val$screen, paramString2);
          return;
        }
        catch (Exception localException)
        {
          Crashlytics.logException(localException);
        }
      }
    });
  }
  
  public static void updateFileListFile()
  {
    if ((filesStored != null) && (getCurrentTimestampMs() - fileListUpdateTime > 5000L))
    {
      Cocos2dxLocalStorage.setItem("wotr_file_list.json", filesStored.toString());
      fileListUpdateTime = getCurrentTimestampMs();
    }
  }
  
  public static boolean writeBitmapToFile(Bitmap paramBitmap, String paramString1, String paramString2)
  {
    paramString1 = new File(activity.getExternalCacheDir() + "/" + paramString1);
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(paramString1);
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
      localFileOutputStream.close();
      paramBitmap = paramString1.getPath();
      paramBitmap = Uri.parse("file://" + paramBitmap);
      paramString1 = new Intent("android.intent.action.SEND");
      paramString1.setFlags(268435456);
      paramString1.putExtra("android.intent.extra.STREAM", paramBitmap);
      paramString1.putExtra("android.intent.extra.TEXT", paramString2);
      paramString1.putExtra("android.intent.extra.SUBJECT", "WordTrip!");
      paramString1.setType("*/*");
      activity.startActivity(Intent.createChooser(paramString1, "Share with"));
      return true;
    }
    catch (Exception paramBitmap)
    {
      Crashlytics.logException(paramBitmap);
    }
    return false;
  }
  
  public static void writeToStream(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    try
    {
      byte[] arrayOfByte = new byte['田'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        paramOutputStream.write(arrayOfByte, 0, i);
      }
      try
      {
        paramInputStream.close();
      }
      catch (IOException paramInputStream)
      {
        try
        {
          paramOutputStream.close();
          throw localObject;
          paramInputStream = paramInputStream;
          Crashlytics.logException(paramInputStream);
        }
        catch (IOException paramInputStream)
        {
          for (;;)
          {
            Crashlytics.logException(paramInputStream);
          }
        }
      }
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
      if (paramInputStream != null) {}
      try
      {
        paramInputStream.close();
        if (paramOutputStream != null) {}
        for (;;)
        {
          try
          {
            paramOutputStream.close();
            return;
          }
          catch (IOException paramInputStream)
          {
            Crashlytics.logException(paramInputStream);
            return;
          }
          if (paramInputStream != null) {}
          try
          {
            paramInputStream.close();
            if (paramOutputStream == null) {
              continue;
            }
            try
            {
              paramOutputStream.close();
              return;
            }
            catch (IOException paramInputStream)
            {
              Crashlytics.logException(paramInputStream);
              return;
            }
          }
          catch (IOException paramInputStream)
          {
            Crashlytics.logException(paramInputStream);
          }
        }
      }
      catch (IOException paramInputStream)
      {
        for (;;)
        {
          Crashlytics.logException(paramInputStream);
        }
      }
    }
    finally
    {
      if (paramInputStream == null) {}
    }
  }
}
