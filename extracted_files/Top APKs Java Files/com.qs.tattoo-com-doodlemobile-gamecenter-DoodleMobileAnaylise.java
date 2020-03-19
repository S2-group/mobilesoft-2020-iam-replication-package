package com.doodlemobile.gamecenter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.location.Criteria;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.doodlemobile.gamecenter.event.DLogThread;
import com.doodlemobile.gamecenter.utils.DGlobalParams;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(11)
public class DoodleMobileAnaylise
{
  static final boolean DEBUG = false;
  public static final int DMTYPE_APPS = 1;
  public static final int DMTYPE_GAMES = 0;
  public static final int LOG_LEVEL_DEBUG = 1;
  public static final int LOG_LEVEL_ERROR = 8;
  public static final int LOG_LEVEL_FATAL = 16;
  public static final int LOG_LEVEL_INFO = 2;
  private static final int LOG_LEVEL_MAX = 16;
  public static final int LOG_LEVEL_WARN = 4;
  private static String MC_ANALYTICS_DIRECTORY;
  private static String MC_DIRECTORY;
  private static int MC_MAX_ANALYTICS_FILES = 0;
  private static int MC_MAX_EVENTS_PER_FILE = 0;
  static final String PREFS_CONFIG = ".DMConfig";
  public static final int PUSH_MESSAGE_INTERVAL = 240;
  static final int PUSH_MESSAGE_TO_SERVER = 100001;
  private static final String TAG = "DoodleMobileAnaylise";
  private static ApplicationInfo appInfo = null;
  private static File currentFile;
  private static boolean fileCreated;
  public static int gAppType = 0;
  private static final DoodleMobileAnaylise gInstance;
  private static boolean gIsFirstInitialized;
  static Handler mSyncHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (100001 == paramAnonymousMessage.what)
      {
        paramAnonymousMessage = DoodleMobileAnaylise.gInstance;
        paramAnonymousMessage.getClass();
        DLogThread.post(new DoodleMobileAnaylise.Sync(paramAnonymousMessage));
        DoodleMobileAnaylise.mSyncHandler.sendEmptyMessageDelayed(100001, 240000L);
      }
    }
  };
  private static int numLinesWritten;
  private static String syncContents;
  private AsyncTask<String, String, String> ADIDtask = null;
  private String androidVersion;
  private Context context;
  private String deviceHardwareModel;
  private String deviceId;
  private String deviceModel;
  public String dmAppID = "null";
  private String dmAppVersion;
  private boolean haveLocationPermission = false;
  private boolean haveNetworkStatePermission = false;
  private int idleTimeout = 120000;
  private String latitude = "null";
  DLocation location = new DLocation();
  private Criteria locationCriteria;
  private Handler locationHandler;
  private String longitude = "null";
  private String mConnectionType = "null";
  private String mInstalledAllDMPkgName = null;
  private boolean mIsInSession = false;
  private boolean mIsOfflineSession = false;
  private boolean mIsTopTask = false;
  private JSONObject mJsonObj = new JSONObject();
  private String mLanguage = "null";
  private String mLocale = "null";
  private String mPackageName = null;
  private long mSessionEndTime = 0L;
  private long mSessionStartTime = 0L;
  private long mTotalIdleTime = 0L;
  private SharedPreferences sharedPrefs = null;
  
  static
  {
    MC_DIRECTORY = "doodlemobile";
    MC_ANALYTICS_DIRECTORY = "analytics";
    MC_MAX_ANALYTICS_FILES = 100;
    MC_MAX_EVENTS_PER_FILE = 5;
    currentFile = null;
    fileCreated = false;
    numLinesWritten = 0;
    syncContents = null;
    gInstance = new DoodleMobileAnaylise();
    gIsFirstInitialized = true;
  }
  
  public DoodleMobileAnaylise() {}
  
  private boolean OpenAnalyticsFile()
  {
    numLinesWritten = 1;
    gInstance.updateSession();
    try
    {
      File localFile = new File(gInstance.context.getDir(MC_DIRECTORY, 0).getAbsolutePath() + "/" + MC_ANALYTICS_DIRECTORY);
      localFile.mkdir();
      if ((localFile != null) && (localFile.listFiles() != null) && (localFile.listFiles().length >= MC_MAX_ANALYTICS_FILES))
      {
        Log.w("DoodleMobileAnaylise", localFile.listFiles().length + " > " + MC_MAX_ANALYTICS_FILES);
        return false;
      }
      currentFile = new File(localFile.getAbsoluteFile() + "/" + System.currentTimeMillis() + ".log");
      currentFile.createNewFile();
      new FileOutputStream(currentFile).close();
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  static void addPref(String paramString1, String paramString2)
  {
    try
    {
      SharedPreferences.Editor localEditor = gInstance.sharedPrefs.edit();
      localEditor.putString(paramString1, paramString2);
      localEditor.commit();
      return;
    }
    catch (Exception paramString1) {}
  }
  
  static void addPref(Map<String, String> paramMap)
  {
    try
    {
      SharedPreferences.Editor localEditor = gInstance.sharedPrefs.edit();
      paramMap = paramMap.entrySet().iterator();
      for (;;)
      {
        if (!paramMap.hasNext())
        {
          localEditor.commit();
          return;
        }
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localEditor.putString((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      return;
    }
    catch (Exception paramMap) {}
  }
  
  static void clearPref()
  {
    try
    {
      SharedPreferences.Editor localEditor = gInstance.sharedPrefs.edit();
      localEditor.clear();
      localEditor.commit();
      return;
    }
    catch (Exception localException) {}
  }
  
  private void createNewSession()
  {
    long l = System.currentTimeMillis();
    String str = sha1(this.deviceId + l);
    this.mIsTopTask = true;
    this.mSessionStartTime = l;
    this.mSessionEndTime = 0L;
    this.mTotalIdleTime = 0L;
    this.mIsInSession = true;
    try
    {
      this.mJsonObj.put("id", URLEncoder.encode(str, "UTF-8"));
      new Thread(new FetchRemoteConfig(null)).start();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.w("DoodleMobileAnaylise", "createNewSession()  fail...");
        localException.printStackTrace();
      }
    }
  }
  
  private void endSession()
  {
    try
    {
      if (!this.mIsInSession) {
        return;
      }
      long l2 = this.mSessionEndTime - this.mSessionStartTime;
      boolean bool = hasPref("totalSessionTime");
      long l1 = l2;
      if (bool) {}
      try
      {
        l1 = Long.parseLong(getPref("totalSessionTime"));
        l1 = l2 + l1;
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          l1 = l2;
        }
      }
      bool = hasPref("mTotalIdleTime");
      if (bool) {}
      try
      {
        this.mTotalIdleTime += Long.parseLong(getPref("mTotalIdleTime"));
        HashMap localHashMap = new HashMap();
        localHashMap.put("totalSessionTime", Long.toString(l1));
        localHashMap.put("mTotalIdleTime", Long.toString(this.mTotalIdleTime));
        if (this.mIsOfflineSession)
        {
          j = 1;
          bool = hasPref("offlineSessions");
          i = j;
          if (!bool) {}
        }
        try
        {
          i = Integer.parseInt(getPref("offlineSessions"));
          i = 1 + i;
        }
        catch (Exception localException4)
        {
          for (;;)
          {
            localException4.printStackTrace();
            i = j;
          }
        }
        localHashMap.put("offlineSessions", Long.toString(i));
        addPref(localHashMap);
        this.mIsInSession = false;
        this.mIsTopTask = false;
        this.mSessionStartTime = 0L;
        this.mSessionEndTime = 0L;
        this.mTotalIdleTime = 0L;
        return;
      }
      catch (Exception localException1)
      {
        int j;
        int i;
        for (;;) {}
      }
      return;
    }
    catch (Exception localException3) {}
  }
  
  public static String getAllInstalledDMPkgName()
  {
    if (gInstance.mInstalledAllDMPkgName == null) {
      return "null";
    }
    return gInstance.mInstalledAllDMPkgName;
  }
  
  public static String getAndroidVersion()
  {
    if (gInstance.androidVersion == null) {
      return "null";
    }
    return gInstance.androidVersion;
  }
  
  private String getDMAppId()
  {
    if (gInstance.dmAppID == null) {
      return "null";
    }
    return gInstance.dmAppID;
  }
  
  public static String getDMAppVersion()
  {
    if (gInstance.dmAppVersion == null) {
      return "null";
    }
    return gInstance.dmAppVersion;
  }
  
  public static String getDeviceId_static()
  {
    if (gInstance.deviceId == null) {
      return "null";
    }
    return gInstance.deviceId;
  }
  
  public static DoodleMobileAnaylise getInstance()
  {
    return gInstance;
  }
  
  public static final boolean getIsFirstInitialized()
  {
    try
    {
      boolean bool = gIsFirstInitialized;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static final boolean getIsInitialized()
  {
    try
    {
      boolean bool = gIsFirstInitialized;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static String getLanguage_static()
  {
    if (gInstance.mLanguage == null) {
      return "null";
    }
    return gInstance.mLanguage;
  }
  
  public static String getLocale_static()
  {
    if (gInstance.mLocale == null) {
      return "null";
    }
    return gInstance.mLocale;
  }
  
  public static String getPackageName()
  {
    if (gInstance.mPackageName == null) {
      return "null";
    }
    return gInstance.mPackageName;
  }
  
  static String getPref(String paramString)
  {
    try
    {
      paramString = gInstance.sharedPrefs.getString(paramString, "");
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  private void handleSessionStatus(boolean paramBoolean)
  {
    for (;;)
    {
      long l;
      try
      {
        l = System.currentTimeMillis();
        if (!paramBoolean) {
          break label85;
        }
        paramBoolean = this.mIsTopTask;
        if (!paramBoolean) {
          continue;
        }
      }
      catch (Exception localException)
      {
        Log.w("DoodleMobileAnaylise", "handleSessionStatus exception...");
        localException.printStackTrace();
        continue;
      }
      finally {}
      return;
      if (!this.mIsInSession)
      {
        createNewSession();
      }
      else
      {
        this.mTotalIdleTime += l - this.mSessionEndTime;
        this.mIsTopTask = true;
        continue;
        label85:
        if (!this.mIsTopTask)
        {
          if ((l - this.mSessionEndTime > this.idleTimeout) && (this.mIsInSession)) {
            endSession();
          }
        }
        else
        {
          this.mSessionEndTime = l;
          this.mIsTopTask = false;
          this.location.stopLocation();
        }
      }
    }
  }
  
  static boolean hasPref(String paramString)
  {
    try
    {
      boolean bool = gInstance.sharedPrefs.contains(paramString);
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private static void initDMAppId(Context paramContext)
  {
    PackageInfo localPackageInfo1 = null;
    Object localObject1 = localPackageInfo1;
    Object localObject3;
    int i;
    for (;;)
    {
      try
      {
        localObject3 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).sharedUserId;
        localObject2 = localObject3;
        if (localObject3 == null) {
          localObject2 = "com.doodlemobile";
        }
        localObject1 = localPackageInfo1;
        localObject3 = new StringBuffer();
        localObject1 = localPackageInfo1;
        localIterator2 = paramContext.getPackageManager().getInstalledPackages(0).iterator();
        localObject1 = localPackageInfo1;
        if (localIterator2.hasNext()) {
          continue;
        }
        localObject1 = localPackageInfo1;
        gInstance.mInstalledAllDMPkgName = ((StringBuffer)localObject3).toString();
        localObject1 = localPackageInfo1;
        Log.e("homer", "sdk = " + getAndroidVersion() + "; installed shareduserid = " + gInstance.mInstalledAllDMPkgName);
        localObject1 = localPackageInfo1;
        appInfo = paramContext.getPackageManager().getApplicationInfo(gInstance.mPackageName, 128);
        localObject1 = localPackageInfo1;
        localObject2 = appInfo.metaData.getString("doodle_mobile_appid");
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = localObject2;
          i = appInfo.metaData.getInt("doodle_mobile_appid");
          localObject1 = String.valueOf(i);
        }
      }
      catch (Exception localException)
      {
        Object localObject2;
        Iterator localIterator2;
        PackageInfo localPackageInfo2;
        localException.printStackTrace();
        continue;
        gInstance.dmAppID = ((String)localObject1);
        localObject1 = new StringBuffer();
        localIterator1 = paramContext.getPackageManager().getInstalledPackages(128).iterator();
      }
      if ((localObject1 != null) && (!((String)localObject1).equals("0"))) {
        continue;
      }
      throw new Resources.NotFoundException("\"doodle_mobile_appid\" not found in the Android Manifest xml.");
      localObject1 = localPackageInfo1;
      localPackageInfo2 = (PackageInfo)localIterator2.next();
      if ((localObject2 != null) && (localPackageInfo2 != null))
      {
        localObject1 = localPackageInfo1;
        if (localPackageInfo2.sharedUserId != null)
        {
          localObject1 = localPackageInfo1;
          if (localPackageInfo2.sharedUserId.equalsIgnoreCase((String)localObject2))
          {
            localObject1 = localPackageInfo1;
            ((StringBuffer)localObject3).append(localPackageInfo2.packageName).append("=");
          }
        }
      }
    }
    for (;;)
    {
      Iterator localIterator1;
      if (!localIterator1.hasNext())
      {
        if (((StringBuffer)localObject1).length() > 0)
        {
          i = ((StringBuffer)localObject1).length();
          ((StringBuffer)localObject1).delete(i - 1, i);
        }
        paramContext = gInstance;
        paramContext.mInstalledAllDMPkgName += ((StringBuffer)localObject1).toString();
        if (gInstance.mInstalledAllDMPkgName.indexOf(getPackageName()) == -1)
        {
          paramContext = gInstance;
          paramContext.mInstalledAllDMPkgName = (paramContext.mInstalledAllDMPkgName + '=' + getPackageName());
        }
        Log.e("homer", "sdk = " + getAndroidVersion() + "; installed appid = " + gInstance.mInstalledAllDMPkgName);
        return;
      }
      localPackageInfo1 = (PackageInfo)localIterator1.next();
      if ((localPackageInfo1 != null) && (localPackageInfo1.applicationInfo != null))
      {
        try
        {
          localObject3 = paramContext.getPackageManager().getApplicationInfo(localPackageInfo1.applicationInfo.packageName, 128);
          if ((localObject3 == null) || (((ApplicationInfo)localObject3).metaData == null) || (!((ApplicationInfo)localObject3).metaData.containsKey("doodle_mobile_appid"))) {
            break label593;
          }
          ((StringBuffer)localObject1).append(localPackageInfo1.packageName).append("=");
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localNameNotFoundException.printStackTrace();
        }
        continue;
        label593:
        if ((localNameNotFoundException != null) && (localNameNotFoundException.packageName != null) && (DGlobalParams.isInstalledTheseGames(localNameNotFoundException.packageName)) && (!gInstance.mInstalledAllDMPkgName.contains(localNameNotFoundException.packageName))) {
          ((StringBuffer)localObject1).append(localNameNotFoundException.packageName).append("=");
        }
      }
    }
  }
  
  public static final void logEvent(int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (gIsFirstInitialized) {
      Log.v("mobclix-gInstance", "logEvent failed - You must initialize DoodleMobileAnaylise by calling DoodleMobileAnaylise.onCreate(this).");
    }
    while (paramInt > 16) {
      return;
    }
    Object localObject = paramString1 + ", " + paramString2 + ": " + paramString3;
    switch (paramInt)
    {
    }
    for (;;)
    {
      try
      {
        localObject = new JSONObject(gInstance.mJsonObj, new String[] { "ts", "ll", "g", "id" });
        ((JSONObject)localObject).put("el", Integer.toString(paramInt));
        ((JSONObject)localObject).put("ep", URLEncoder.encode(paramString1, "UTF-8"));
        ((JSONObject)localObject).put("en", URLEncoder.encode(paramString2, "UTF-8"));
        ((JSONObject)localObject).put("ed", URLEncoder.encode(paramString3, "UTF-8"));
        ((JSONObject)localObject).put("et", Long.toString(Thread.currentThread().getId()));
        if (!paramBoolean) {
          break label342;
        }
        paramString1 = "1";
        ((JSONObject)localObject).put("es", paramString1);
        paramString1 = gInstance;
        paramString1.getClass();
        DLogThread.post(new LogEvent(paramString1, (JSONObject)localObject));
        return;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        return;
      }
      Log.d("DoodleMobile", (String)localObject);
      continue;
      Log.i("DoodleMobile", (String)localObject);
      continue;
      Log.w("DoodleMobile", (String)localObject);
      continue;
      Log.e("DoodleMobile", (String)localObject);
      continue;
      Log.e("DoodleMobile", (String)localObject);
      continue;
      label342:
      paramString1 = "0";
    }
  }
  
  /* Error */
  public static final void onCreate(Activity paramActivity)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: ifnonnull +7 -> 11
    //   7: ldc 2
    //   9: monitorexit
    //   10: return
    //   11: new 683	com/doodlemobile/gamecenter/cache/DGlobalPrefences
    //   14: dup
    //   15: aload_0
    //   16: invokespecial 685	com/doodlemobile/gamecenter/cache/DGlobalPrefences:<init>	(Landroid/content/Context;)V
    //   19: pop
    //   20: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   23: aload_0
    //   24: invokevirtual 691	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   27: putfield 208	com/doodlemobile/gamecenter/DoodleMobileAnaylise:context	Landroid/content/Context;
    //   30: getstatic 137	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gIsFirstInitialized	Z
    //   33: istore_2
    //   34: iload_2
    //   35: ifeq +143 -> 178
    //   38: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   41: aload_0
    //   42: invokevirtual 692	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   45: putfield 191	com/doodlemobile/gamecenter/DoodleMobileAnaylise:mPackageName	Ljava/lang/String;
    //   48: aload_0
    //   49: invokevirtual 693	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   52: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   55: getfield 191	com/doodlemobile/gamecenter/DoodleMobileAnaylise:mPackageName	Ljava/lang/String;
    //   58: iconst_0
    //   59: invokevirtual 512	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   62: getfield 696	android/content/pm/PackageInfo:versionCode	I
    //   65: istore_1
    //   66: aload_0
    //   67: invokevirtual 693	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   70: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   73: getfield 191	com/doodlemobile/gamecenter/DoodleMobileAnaylise:mPackageName	Ljava/lang/String;
    //   76: iconst_0
    //   77: invokevirtual 512	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   80: getfield 699	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   83: astore_3
    //   84: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   87: getstatic 704	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   90: putfield 470	com/doodlemobile/gamecenter/DoodleMobileAnaylise:androidVersion	Ljava/lang/String;
    //   93: invokestatic 707	com/doodlemobile/gamecenter/cache/DGlobalPrefences:getAppVersionCode	()I
    //   96: iconst_m1
    //   97: if_icmpeq +10 -> 107
    //   100: iload_1
    //   101: invokestatic 707	com/doodlemobile/gamecenter/cache/DGlobalPrefences:getAppVersionCode	()I
    //   104: if_icmple +7 -> 111
    //   107: iload_1
    //   108: invokestatic 710	com/doodlemobile/gamecenter/cache/DGlobalPrefences:setAppVersionCode	(I)V
    //   111: invokestatic 713	com/doodlemobile/gamecenter/cache/DGlobalPrefences:getAppVersionName	()Ljava/lang/String;
    //   114: ifnull +13 -> 127
    //   117: aload_3
    //   118: invokestatic 713	com/doodlemobile/gamecenter/cache/DGlobalPrefences:getAppVersionName	()Ljava/lang/String;
    //   121: invokevirtual 716	java/lang/String:compareToIgnoreCase	(Ljava/lang/String;)I
    //   124: ifle +7 -> 131
    //   127: aload_3
    //   128: invokestatic 719	com/doodlemobile/gamecenter/cache/DGlobalPrefences:setAppVersionName	(Ljava/lang/String;)V
    //   131: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   134: new 8	com/doodlemobile/gamecenter/DoodleMobileAnaylise$2
    //   137: dup
    //   138: invokespecial 720	com/doodlemobile/gamecenter/DoodleMobileAnaylise$2:<init>	()V
    //   141: putfield 722	com/doodlemobile/gamecenter/DoodleMobileAnaylise:locationHandler	Landroid/os/Handler;
    //   144: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   147: invokevirtual 202	com/doodlemobile/gamecenter/DoodleMobileAnaylise:updateSession	()V
    //   150: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   153: invokestatic 728	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   156: invokevirtual 731	java/util/Locale:getCountry	()Ljava/lang/String;
    //   159: putfield 181	com/doodlemobile/gamecenter/DoodleMobileAnaylise:mLocale	Ljava/lang/String;
    //   162: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   165: invokestatic 728	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   168: invokevirtual 734	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   171: putfield 183	com/doodlemobile/gamecenter/DoodleMobileAnaylise:mLanguage	Ljava/lang/String;
    //   174: iconst_0
    //   175: putstatic 137	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gIsFirstInitialized	Z
    //   178: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   181: new 10	com/doodlemobile/gamecenter/DoodleMobileAnaylise$3
    //   184: dup
    //   185: aload_0
    //   186: invokespecial 736	com/doodlemobile/gamecenter/DoodleMobileAnaylise$3:<init>	(Landroid/app/Activity;)V
    //   189: putfield 195	com/doodlemobile/gamecenter/DoodleMobileAnaylise:ADIDtask	Landroid/os/AsyncTask;
    //   192: getstatic 739	android/os/Build$VERSION:SDK_INT	I
    //   195: bipush 11
    //   197: if_icmplt +79 -> 276
    //   200: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   203: getfield 195	com/doodlemobile/gamecenter/DoodleMobileAnaylise:ADIDtask	Landroid/os/AsyncTask;
    //   206: invokestatic 745	java/util/concurrent/Executors:newCachedThreadPool	()Ljava/util/concurrent/ExecutorService;
    //   209: iconst_0
    //   210: anewarray 220	java/lang/String
    //   213: invokevirtual 751	android/os/AsyncTask:executeOnExecutor	(Ljava/util/concurrent/Executor;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   216: pop
    //   217: aload_0
    //   218: invokestatic 753	com/doodlemobile/gamecenter/DoodleMobileAnaylise:initDMAppId	(Landroid/content/Context;)V
    //   221: getstatic 758	com/doodlemobile/gamecenter/featuregames/DFeatureGamesFactory:gInstance	Lcom/doodlemobile/gamecenter/featuregames/DFeatureGamesFactory;
    //   224: aload_0
    //   225: invokevirtual 761	com/doodlemobile/gamecenter/featuregames/DFeatureGamesFactory:getGameList	(Landroid/content/Context;)V
    //   228: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   231: iconst_1
    //   232: invokespecial 763	com/doodlemobile/gamecenter/DoodleMobileAnaylise:handleSessionStatus	(Z)V
    //   235: goto -228 -> 7
    //   238: astore_0
    //   239: ldc 2
    //   241: monitorexit
    //   242: aload_0
    //   243: athrow
    //   244: astore 4
    //   246: iconst_m1
    //   247: istore_1
    //   248: ldc -88
    //   250: astore_3
    //   251: aload 4
    //   253: invokevirtual 291	java/lang/Exception:printStackTrace	()V
    //   256: goto -163 -> 93
    //   259: astore_3
    //   260: ldc 56
    //   262: ldc_w 765
    //   265: invokestatic 258	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   268: pop
    //   269: aload_3
    //   270: invokevirtual 291	java/lang/Exception:printStackTrace	()V
    //   273: goto -99 -> 174
    //   276: getstatic 135	com/doodlemobile/gamecenter/DoodleMobileAnaylise:gInstance	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
    //   279: getfield 195	com/doodlemobile/gamecenter/DoodleMobileAnaylise:ADIDtask	Landroid/os/AsyncTask;
    //   282: iconst_0
    //   283: anewarray 220	java/lang/String
    //   286: invokevirtual 769	android/os/AsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   289: pop
    //   290: goto -73 -> 217
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	293	0	paramActivity	Activity
    //   65	183	1	i	int
    //   33	2	2	bool	boolean
    //   83	168	3	str	String
    //   259	11	3	localException1	Exception
    //   244	8	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   11	34	238	finally
    //   38	93	238	finally
    //   93	107	238	finally
    //   107	111	238	finally
    //   111	127	238	finally
    //   127	131	238	finally
    //   131	174	238	finally
    //   174	178	238	finally
    //   178	217	238	finally
    //   217	235	238	finally
    //   251	256	238	finally
    //   260	273	238	finally
    //   276	290	238	finally
    //   38	93	244	java/lang/Exception
    //   131	174	259	java/lang/Exception
  }
  
  public static final void onCreate(Activity paramActivity, int paramInt)
  {
    try
    {
      gAppType = paramInt;
      onCreate(paramActivity);
      return;
    }
    finally
    {
      paramActivity = finally;
      throw paramActivity;
    }
  }
  
  public static final void onStop(Activity paramActivity)
  {
    try
    {
      gInstance.handleSessionStatus(false);
      return;
    }
    finally
    {
      paramActivity = finally;
      throw paramActivity;
    }
  }
  
  static void removePref(String paramString)
  {
    try
    {
      SharedPreferences.Editor localEditor = gInstance.sharedPrefs.edit();
      localEditor.remove(paramString);
      localEditor.commit();
      return;
    }
    catch (Exception paramString) {}
  }
  
  private static String sha1(String paramString)
  {
    Object localObject = new byte[40];
    for (;;)
    {
      int i;
      try
      {
        localObject = MessageDigest.getInstance("SHA-1");
        ((MessageDigest)localObject).update(paramString.getBytes(), 0, paramString.length());
        paramString = ((MessageDigest)localObject).digest();
        localObject = new StringBuffer();
        i = 0;
        if (i >= paramString.length) {
          return ((StringBuffer)localObject).toString();
        }
      }
      catch (NoSuchAlgorithmException paramString)
      {
        throw new RuntimeException(paramString);
      }
      ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
      i += 1;
    }
  }
  
  public static final void sync()
  {
    if (gIsFirstInitialized)
    {
      Log.v("DoodleMobileAnaylise", "sync failed... - You must initialize DoodleMobileAnaylise");
      return;
    }
    mSyncHandler.removeMessages(100001);
    mSyncHandler.sendEmptyMessage(100001);
  }
  
  private void updateConnectivity()
  {
    for (;;)
    {
      try
      {
        Object localObject = (ConnectivityManager)this.context.getSystemService("connectivity");
        String str2 = "u";
        String str1 = str2;
        if (this.haveNetworkStatePermission)
        {
          localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
          str1 = str2;
          if (localObject != null) {
            str1 = ((NetworkInfo)localObject).getTypeName();
          }
        }
        if ((str1.equalsIgnoreCase("WIFI")) || (str1.equalsIgnoreCase("WI_FI")))
        {
          this.mConnectionType = "wifi";
          if (this.mConnectionType != null) {
            break;
          }
          this.mConnectionType = "null";
          return;
        }
        if (str1.equalsIgnoreCase("MOBILE")) {
          this.mConnectionType = Integer.toString(((TelephonyManager)this.context.getSystemService("phone")).getNetworkType());
        } else {
          this.mConnectionType = "null";
        }
      }
      catch (Exception localException)
      {
        this.mConnectionType = "null";
        return;
      }
    }
  }
  
  private void updateLocation()
  {
    this.location.getLocation(this.context, new DLocation.LocationResult()
    {
      public void gotLocation(Location paramAnonymousLocation)
      {
        try
        {
          DoodleMobileAnaylise.this.latitude = Double.toString(paramAnonymousLocation.getLatitude());
          DoodleMobileAnaylise.this.longitude = Double.toString(paramAnonymousLocation.getLongitude());
          return;
        }
        catch (Exception paramAnonymousLocation)
        {
          paramAnonymousLocation.printStackTrace();
        }
      }
    });
  }
  
  String getConnectionType()
  {
    if (this.mConnectionType == null) {
      return "null";
    }
    return this.mConnectionType;
  }
  
  String getDeviceHardwareModel()
  {
    if (this.deviceHardwareModel == null) {
      return "null";
    }
    return this.deviceHardwareModel;
  }
  
  String getDeviceId()
  {
    if (this.deviceId == null) {
      return "null";
    }
    return this.deviceId;
  }
  
  String getDeviceModel()
  {
    if (this.deviceModel == null) {
      return "null";
    }
    return this.deviceModel;
  }
  
  String getGPS()
  {
    if ((getLatitude().equals("null")) || (getLongitude().equals("null"))) {
      return "null";
    }
    return getLatitude() + "," + getLongitude();
  }
  
  String getLanguage()
  {
    if (this.mLanguage == null) {
      return "null";
    }
    return this.mLanguage;
  }
  
  String getLatitude()
  {
    if (this.latitude == null) {
      return "null";
    }
    return this.latitude;
  }
  
  String getLocale()
  {
    if (this.mLocale == null) {
      return "null";
    }
    return this.mLocale;
  }
  
  String getLongitude()
  {
    if (this.longitude == null) {
      return "null";
    }
    return this.longitude;
  }
  
  String getMobclixVersion()
  {
    return "2.3";
  }
  
  void updateSession()
  {
    updateConnectivity();
    if (this.haveLocationPermission) {
      this.locationHandler.sendEmptyMessage(0);
    }
    try
    {
      this.mJsonObj.put("ts", System.currentTimeMillis());
      String str = getGPS();
      if (!str.equals("null")) {
        this.mJsonObj.put("ll", str);
      }
      for (;;)
      {
        this.mJsonObj.put("g", this.mConnectionType);
        return;
        this.mJsonObj.remove("ll");
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }
  
  private class FetchRemoteConfig
    implements Runnable
  {
    private FetchRemoteConfig() {}
    
    public void run()
    {
      for (;;)
      {
        try
        {
          localPackageManager = DoodleMobileAnaylise.this.context.getPackageManager();
        }
        catch (Exception localException)
        {
          PackageManager localPackageManager;
          localException.printStackTrace();
          continue;
          if (localException.checkPermission("android.permission.ACCESS_COARSE_LOCATION", DoodleMobileAnaylise.gInstance.mPackageName) != 0) {
            continue;
          }
          DoodleMobileAnaylise.this.locationCriteria = new Criteria();
          DoodleMobileAnaylise.this.locationCriteria.setAccuracy(2);
          DoodleMobileAnaylise.this.haveLocationPermission = true;
          continue;
          DoodleMobileAnaylise.this.haveLocationPermission = false;
          continue;
          DoodleMobileAnaylise.this.haveNetworkStatePermission = false;
          continue;
        }
        try
        {
          DoodleMobileAnaylise.this.dmAppVersion = localPackageManager.getPackageInfo(DoodleMobileAnaylise.gInstance.mPackageName, 0).versionName;
          if (localPackageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", DoodleMobileAnaylise.gInstance.mPackageName) == 0)
          {
            DoodleMobileAnaylise.this.locationCriteria = new Criteria();
            DoodleMobileAnaylise.this.locationCriteria.setAccuracy(1);
            DoodleMobileAnaylise.this.haveLocationPermission = true;
            if (localPackageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", DoodleMobileAnaylise.gInstance.mPackageName) != 0) {
              continue;
            }
            DoodleMobileAnaylise.this.haveNetworkStatePermission = true;
            DoodleMobileAnaylise.sync();
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          DoodleMobileAnaylise.this.dmAppVersion = "null";
        }
      }
    }
  }
  
  public class LogEvent
    implements Runnable
  {
    JSONObject event;
    
    public LogEvent() {}
    
    public LogEvent(JSONObject paramJSONObject)
    {
      this.event = paramJSONObject;
    }
    
    public void run()
    {
      try
      {
        if ((!DoodleMobileAnaylise.fileCreated) && (!DoodleMobileAnaylise.this.OpenAnalyticsFile()))
        {
          Log.i("logEvent", "can not open file");
          return;
        }
        DoodleMobileAnaylise.fileCreated = true;
        DoodleMobileAnaylise.gInstance.updateSession();
        try
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(DoodleMobileAnaylise.currentFile, true);
          if (DoodleMobileAnaylise.numLinesWritten > 1) {
            localFileOutputStream.write(",".getBytes());
          }
          localFileOutputStream.write(this.event.toString().getBytes());
          localFileOutputStream.close();
          DoodleMobileAnaylise.numLinesWritten += 1;
          if (DoodleMobileAnaylise.numLinesWritten > DoodleMobileAnaylise.MC_MAX_EVENTS_PER_FILE)
          {
            DoodleMobileAnaylise.fileCreated = false;
            return;
          }
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
          return;
        }
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
  }
  
  class Sync
    implements Runnable
  {
    Sync() {}
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: ldc 24
      //   4: new 26	java/lang/StringBuilder
      //   7: dup
      //   8: ldc 28
      //   10: invokespecial 31	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   13: invokestatic 37	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   16: invokevirtual 41	java/lang/Thread:getId	()J
      //   19: invokevirtual 45	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   22: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   25: invokestatic 55	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   28: pop
      //   29: invokestatic 59	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$0	()Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   32: invokestatic 63	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$8	(Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;)Landroid/content/Context;
      //   35: invokestatic 69	com/doodlemobile/gamecenter/net/DNetworkStatus:isNetworkAvailable	(Landroid/content/Context;)Z
      //   38: ifne +14 -> 52
      //   41: ldc 71
      //   43: ldc 73
      //   45: invokestatic 76	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   48: pop
      //   49: aload_0
      //   50: monitorexit
      //   51: return
      //   52: new 78	java/io/File
      //   55: dup
      //   56: new 26	java/lang/StringBuilder
      //   59: dup
      //   60: invokestatic 59	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$0	()Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   63: invokestatic 63	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$8	(Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;)Landroid/content/Context;
      //   66: invokestatic 81	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$15	()Ljava/lang/String;
      //   69: iconst_0
      //   70: invokevirtual 87	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
      //   73: invokevirtual 90	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   76: invokestatic 96	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   79: invokespecial 31	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   82: ldc 98
      //   84: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   87: invokestatic 104	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$16	()Ljava/lang/String;
      //   90: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   93: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   96: invokespecial 105	java/io/File:<init>	(Ljava/lang/String;)V
      //   99: astore 4
      //   101: aload 4
      //   103: invokevirtual 109	java/io/File:mkdir	()Z
      //   106: pop
      //   107: new 111	org/json/JSONObject
      //   110: dup
      //   111: invokestatic 59	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$0	()Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   114: invokestatic 115	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$17	(Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;)Lorg/json/JSONObject;
      //   117: iconst_3
      //   118: anewarray 92	java/lang/String
      //   121: dup
      //   122: iconst_0
      //   123: ldc 117
      //   125: aastore
      //   126: dup
      //   127: iconst_1
      //   128: ldc 119
      //   130: aastore
      //   131: dup
      //   132: iconst_2
      //   133: ldc 121
      //   135: aastore
      //   136: invokespecial 124	org/json/JSONObject:<init>	(Lorg/json/JSONObject;[Ljava/lang/String;)V
      //   139: astore 6
      //   141: aload 6
      //   143: ldc 126
      //   145: aload_0
      //   146: getfield 15	com/doodlemobile/gamecenter/DoodleMobileAnaylise$Sync:this$0	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   149: invokestatic 130	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$18	(Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;)Ljava/lang/String;
      //   152: ldc -124
      //   154: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   157: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   160: pop
      //   161: aload 6
      //   163: ldc -112
      //   165: ldc -110
      //   167: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   170: pop
      //   171: aload 6
      //   173: ldc -108
      //   175: aload_0
      //   176: getfield 15	com/doodlemobile/gamecenter/DoodleMobileAnaylise$Sync:this$0	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   179: invokevirtual 151	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getMobclixVersion	()Ljava/lang/String;
      //   182: invokestatic 154	java/net/URLEncoder:encode	(Ljava/lang/String;)Ljava/lang/String;
      //   185: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   188: pop
      //   189: aload 6
      //   191: ldc -100
      //   193: invokestatic 159	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getDMAppVersion	()Ljava/lang/String;
      //   196: ldc -124
      //   198: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   201: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   204: pop
      //   205: aload 6
      //   207: ldc -95
      //   209: aload_0
      //   210: getfield 15	com/doodlemobile/gamecenter/DoodleMobileAnaylise$Sync:this$0	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   213: invokevirtual 164	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getDeviceId	()Ljava/lang/String;
      //   216: ldc -124
      //   218: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   221: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   224: pop
      //   225: aload 6
      //   227: ldc -90
      //   229: aload_0
      //   230: getfield 15	com/doodlemobile/gamecenter/DoodleMobileAnaylise$Sync:this$0	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   233: invokevirtual 169	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getDeviceModel	()Ljava/lang/String;
      //   236: ldc -124
      //   238: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   241: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   244: pop
      //   245: aload 6
      //   247: ldc -85
      //   249: invokestatic 174	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getAndroidVersion	()Ljava/lang/String;
      //   252: ldc -124
      //   254: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   257: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   260: pop
      //   261: aload 6
      //   263: ldc -80
      //   265: aload_0
      //   266: getfield 15	com/doodlemobile/gamecenter/DoodleMobileAnaylise$Sync:this$0	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   269: invokevirtual 179	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getDeviceHardwareModel	()Ljava/lang/String;
      //   272: ldc -124
      //   274: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   277: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   280: pop
      //   281: aload 6
      //   283: ldc -108
      //   285: ldc -75
      //   287: ldc -124
      //   289: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   292: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   295: pop
      //   296: aload 6
      //   298: ldc -73
      //   300: aload_0
      //   301: getfield 15	com/doodlemobile/gamecenter/DoodleMobileAnaylise$Sync:this$0	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   304: invokevirtual 186	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getLanguage	()Ljava/lang/String;
      //   307: ldc -124
      //   309: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   312: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   315: pop
      //   316: aload 6
      //   318: ldc -68
      //   320: aload_0
      //   321: getfield 15	com/doodlemobile/gamecenter/DoodleMobileAnaylise$Sync:this$0	Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   324: invokevirtual 191	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getLocale	()Ljava/lang/String;
      //   327: ldc -124
      //   329: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   332: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   335: pop
      //   336: aload 6
      //   338: ldc -63
      //   340: invokestatic 59	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$0	()Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   343: invokestatic 196	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$9	(Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;)Ljava/lang/String;
      //   346: invokevirtual 142	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   349: pop
      //   350: aload 4
      //   352: invokevirtual 200	java/io/File:listFiles	()[Ljava/io/File;
      //   355: astore 7
      //   357: aload 7
      //   359: ifnull -310 -> 49
      //   362: iconst_0
      //   363: istore_1
      //   364: iconst_0
      //   365: istore_2
      //   366: aload 7
      //   368: arraylength
      //   369: istore_3
      //   370: iload_2
      //   371: iload_3
      //   372: if_icmplt +21 -> 393
      //   375: aconst_null
      //   376: invokestatic 203	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$21	(Ljava/lang/String;)V
      //   379: iconst_0
      //   380: invokestatic 207	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$6	(I)V
      //   383: goto -334 -> 49
      //   386: astore 4
      //   388: aload_0
      //   389: monitorexit
      //   390: aload 4
      //   392: athrow
      //   393: new 209	java/lang/StringBuffer
      //   396: dup
      //   397: invokespecial 210	java/lang/StringBuffer:<init>	()V
      //   400: astore 4
      //   402: aload 4
      //   404: ldc -44
      //   406: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   409: pop
      //   410: aload 4
      //   412: ldc -39
      //   414: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   417: invokestatic 59	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$0	()Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   420: invokestatic 130	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$18	(Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;)Ljava/lang/String;
      //   423: ldc -124
      //   425: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   428: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   431: pop
      //   432: aload 4
      //   434: ldc -37
      //   436: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   439: invokestatic 59	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$0	()Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   442: invokevirtual 151	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getMobclixVersion	()Ljava/lang/String;
      //   445: invokestatic 154	java/net/URLEncoder:encode	(Ljava/lang/String;)Ljava/lang/String;
      //   448: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   451: pop
      //   452: aload 4
      //   454: ldc -35
      //   456: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   459: invokestatic 59	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$0	()Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   462: invokevirtual 164	com/doodlemobile/gamecenter/DoodleMobileAnaylise:getDeviceId	()Ljava/lang/String;
      //   465: ldc -124
      //   467: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   470: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   473: pop
      //   474: aload 4
      //   476: ldc -33
      //   478: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   481: invokestatic 59	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$0	()Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;
      //   484: invokestatic 226	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$19	(Lcom/doodlemobile/gamecenter/DoodleMobileAnaylise;)Ljava/lang/String;
      //   487: ldc -124
      //   489: invokestatic 138	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   492: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   495: pop
      //   496: aload 4
      //   498: ldc -28
      //   500: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   503: aload 6
      //   505: invokevirtual 229	org/json/JSONObject:toString	()Ljava/lang/String;
      //   508: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   511: ldc -25
      //   513: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   516: pop
      //   517: new 233	java/io/FileInputStream
      //   520: dup
      //   521: aload 7
      //   523: iload_2
      //   524: aaload
      //   525: invokespecial 236	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   528: astore 5
      //   530: new 238	java/io/BufferedInputStream
      //   533: dup
      //   534: aload 5
      //   536: invokespecial 241	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   539: astore 8
      //   541: new 243	java/io/DataInputStream
      //   544: dup
      //   545: aload 8
      //   547: invokespecial 244	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
      //   550: astore 9
      //   552: aload 9
      //   554: invokevirtual 248	java/io/DataInputStream:available	()I
      //   557: ifne +219 -> 776
      //   560: aload 9
      //   562: invokevirtual 251	java/io/DataInputStream:close	()V
      //   565: aload 8
      //   567: invokevirtual 252	java/io/BufferedInputStream:close	()V
      //   570: aload 5
      //   572: invokevirtual 253	java/io/FileInputStream:close	()V
      //   575: aload 4
      //   577: ldc -1
      //   579: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   582: pop
      //   583: aload 4
      //   585: invokevirtual 256	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   588: astore 5
      //   590: aload 4
      //   592: invokevirtual 256	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   595: invokestatic 203	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$21	(Ljava/lang/String;)V
      //   598: aload 5
      //   600: ldc_w 258
      //   603: invokevirtual 262	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   606: ifeq +17 -> 623
      //   609: aload 5
      //   611: ldc_w 264
      //   614: ldc_w 266
      //   617: invokevirtual 269	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   620: invokestatic 203	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$21	(Ljava/lang/String;)V
      //   623: new 271	java/net/URL
      //   626: dup
      //   627: ldc_w 273
      //   630: invokespecial 274	java/net/URL:<init>	(Ljava/lang/String;)V
      //   633: astore 4
      //   635: aload 4
      //   637: invokevirtual 278	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   640: checkcast 280	java/net/HttpURLConnection
      //   643: astore 4
      //   645: aload 4
      //   647: iconst_1
      //   648: invokevirtual 284	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   651: aload 4
      //   653: iconst_1
      //   654: invokevirtual 287	java/net/HttpURLConnection:setDoInput	(Z)V
      //   657: aload 4
      //   659: iconst_0
      //   660: invokevirtual 290	java/net/HttpURLConnection:setUseCaches	(Z)V
      //   663: aload 4
      //   665: ldc_w 292
      //   668: invokevirtual 295	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   671: new 297	java/io/PrintWriter
      //   674: dup
      //   675: aload 4
      //   677: invokevirtual 301	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   680: invokespecial 304	java/io/PrintWriter:<init>	(Ljava/io/OutputStream;)V
      //   683: astore 5
      //   685: aload 5
      //   687: invokestatic 307	com/doodlemobile/gamecenter/DoodleMobileAnaylise:access$20	()Ljava/lang/String;
      //   690: invokevirtual 310	java/io/PrintWriter:print	(Ljava/lang/String;)V
      //   693: aload 5
      //   695: invokevirtual 313	java/io/PrintWriter:flush	()V
      //   698: aload 5
      //   700: invokevirtual 314	java/io/PrintWriter:close	()V
      //   703: new 316	java/io/BufferedReader
      //   706: dup
      //   707: new 318	java/io/InputStreamReader
      //   710: dup
      //   711: aload 4
      //   713: invokevirtual 322	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   716: invokespecial 323	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   719: invokespecial 326	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   722: astore 8
      //   724: aconst_null
      //   725: astore 4
      //   727: aload 8
      //   729: invokevirtual 329	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   732: astore 5
      //   734: aload 5
      //   736: ifnonnull +64 -> 800
      //   739: aload 4
      //   741: ldc_w 331
      //   744: invokevirtual 335	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   747: ifne +5 -> 752
      //   750: iconst_1
      //   751: istore_1
      //   752: aload 8
      //   754: invokevirtual 336	java/io/BufferedReader:close	()V
      //   757: iload_1
      //   758: ifne -709 -> 49
      //   761: aload 7
      //   763: iload_2
      //   764: aaload
      //   765: invokevirtual 339	java/io/File:delete	()Z
      //   768: pop
      //   769: iload_2
      //   770: iconst_1
      //   771: iadd
      //   772: istore_2
      //   773: goto -407 -> 366
      //   776: aload 4
      //   778: aload 9
      //   780: invokevirtual 340	java/io/DataInputStream:readLine	()Ljava/lang/String;
      //   783: invokevirtual 215	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   786: pop
      //   787: goto -235 -> 552
      //   790: astore 4
      //   792: aload 4
      //   794: invokevirtual 343	java/lang/Exception:printStackTrace	()V
      //   797: goto -422 -> 375
      //   800: aload 4
      //   802: ifnonnull -75 -> 727
      //   805: aload 5
      //   807: astore 4
      //   809: goto -82 -> 727
      //   812: astore 4
      //   814: aload 4
      //   816: invokevirtual 343	java/lang/Exception:printStackTrace	()V
      //   819: iconst_1
      //   820: istore_1
      //   821: goto -64 -> 757
      //   824: astore 4
      //   826: goto -12 -> 814
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	829	0	this	Sync
      //   363	458	1	i	int
      //   365	408	2	j	int
      //   369	4	3	k	int
      //   99	252	4	localFile	File
      //   386	5	4	localObject1	Object
      //   400	377	4	localObject2	Object
      //   790	11	4	localException1	Exception
      //   807	1	4	localObject3	Object
      //   812	3	4	localException2	Exception
      //   824	1	4	localException3	Exception
      //   528	278	5	localObject4	Object
      //   139	365	6	localJSONObject	JSONObject
      //   355	407	7	arrayOfFile	File[]
      //   539	214	8	localObject5	Object
      //   550	229	9	localDataInputStream	java.io.DataInputStream
      // Exception table:
      //   from	to	target	type
      //   2	49	386	finally
      //   52	357	386	finally
      //   366	370	386	finally
      //   375	383	386	finally
      //   393	552	386	finally
      //   552	623	386	finally
      //   623	635	386	finally
      //   635	724	386	finally
      //   727	734	386	finally
      //   739	750	386	finally
      //   752	757	386	finally
      //   761	769	386	finally
      //   776	787	386	finally
      //   792	797	386	finally
      //   814	819	386	finally
      //   52	357	790	java/lang/Exception
      //   366	370	790	java/lang/Exception
      //   393	552	790	java/lang/Exception
      //   552	623	790	java/lang/Exception
      //   761	769	790	java/lang/Exception
      //   776	787	790	java/lang/Exception
      //   814	819	790	java/lang/Exception
      //   623	635	812	java/lang/Exception
      //   635	724	824	java/lang/Exception
      //   727	734	824	java/lang/Exception
      //   739	750	824	java/lang/Exception
      //   752	757	824	java/lang/Exception
    }
  }
}
