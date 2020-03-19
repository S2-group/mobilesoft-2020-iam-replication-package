package city.russ.alltrackercorp.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraManager.AvailabilityCallback;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.TrafficStats;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Process;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import city.russ.alltrackercorp.listeners.SimpleResultListener;
import city.russ.alltrackercorp.main.AppConstants;
import city.russ.alltrackercorp.managers.NetworkManager;
import city.russ.alltrackercorp.managers.StateManager;
import city.russ.alltrackercorp.managers.StateManager.MicrophoneState;
import city.russ.alltrackercorp.managers.StateManager.NetworkState;
import city.russ.alltrackercorp.managers.StateManager.NotificationListenerState;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;
import de.russcity.at.model.DeviceDetails;
import de.russcity.at.model.DeviceDetails.InternetStatus;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

public class PhoneUtils
{
  public PhoneUtils() {}
  
  private static boolean appContainsInList(String paramString, List<ResolveInfo> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      if (paramString.equals(((ResolveInfo)paramList.next()).activityInfo.packageName)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean checkAndroidPermissions(Context paramContext)
  {
    if (Build.VERSION.SDK_INT > 22)
    {
      int i;
      if (ContextCompat.checkSelfPermission(paramContext, "android.permission.READ_CONTACTS") == 0) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (ContextCompat.checkSelfPermission(paramContext, "android.permission.CAMERA") == 0) {
        j = 1;
      } else {
        j = 0;
      }
      int k;
      if (ContextCompat.checkSelfPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
        k = 1;
      } else {
        k = 0;
      }
      int m;
      if (ContextCompat.checkSelfPermission(paramContext, "android.permission.RECORD_AUDIO") == 0) {
        m = 1;
      } else {
        m = 0;
      }
      int n;
      if (ContextCompat.checkSelfPermission(paramContext, "android.permission.READ_SMS") == 0) {
        n = 1;
      } else {
        n = 0;
      }
      int i1;
      if (ContextCompat.checkSelfPermission(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int i2;
      if (ContextCompat.checkSelfPermission(paramContext, "android.permission.READ_PHONE_STATE") == 0) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      if ((i != 0) && (j != 0) && (k != 0) && (m != 0) && (n != 0) && (i1 != 0)) {
        return i2 != 0;
      }
      return false;
    }
    return true;
  }
  
  @SuppressLint({"NewApi"})
  public static boolean checkForBatteryOptimization(Context paramContext)
  {
    if (Build.VERSION.SDK_INT > 100)
    {
      new Intent();
      String str = paramContext.getPackageName();
      return ((PowerManager)paramContext.getSystemService("power")).isIgnoringBatteryOptimizations(str);
    }
    return true;
  }
  
  /* Error */
  public static String getAppIconForPackage(Context paramContext, String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_1
    //   5: iconst_0
    //   6: invokevirtual 127	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   9: astore_1
    //   10: aload_0
    //   11: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   14: aload_1
    //   15: invokevirtual 131	android/content/pm/PackageManager:getApplicationIcon	(Landroid/content/pm/ApplicationInfo;)Landroid/graphics/drawable/Drawable;
    //   18: astore_1
    //   19: new 133	android/graphics/BitmapFactory$Options
    //   22: dup
    //   23: invokespecial 134	android/graphics/BitmapFactory$Options:<init>	()V
    //   26: bipush 8
    //   28: putfield 137	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   31: aload_1
    //   32: instanceof 139
    //   35: istore_3
    //   36: goto +5 -> 41
    //   39: iconst_0
    //   40: istore_3
    //   41: iload_3
    //   42: istore 4
    //   44: iload_3
    //   45: ifne +9 -> 54
    //   48: aload_1
    //   49: instanceof 141
    //   52: istore 4
    //   54: iload 4
    //   56: ifeq +35 -> 91
    //   59: iload_2
    //   60: iload_2
    //   61: getstatic 147	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   64: invokestatic 153	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   67: astore_0
    //   68: aload_1
    //   69: iconst_0
    //   70: iconst_0
    //   71: iload_2
    //   72: iload_2
    //   73: invokevirtual 159	android/graphics/drawable/Drawable:setBounds	(IIII)V
    //   76: aload_1
    //   77: new 161	android/graphics/Canvas
    //   80: dup
    //   81: aload_0
    //   82: invokespecial 164	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   85: invokevirtual 168	android/graphics/drawable/Drawable:draw	(Landroid/graphics/Canvas;)V
    //   88: goto +22 -> 110
    //   91: aload_1
    //   92: checkcast 170	android/graphics/drawable/BitmapDrawable
    //   95: invokevirtual 174	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   98: iload_2
    //   99: iload_2
    //   100: iconst_0
    //   101: invokestatic 178	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   104: astore_0
    //   105: goto +5 -> 110
    //   108: aconst_null
    //   109: astore_0
    //   110: ldc -76
    //   112: astore_1
    //   113: aload_0
    //   114: ifnull +65 -> 179
    //   117: new 182	java/io/ByteArrayOutputStream
    //   120: dup
    //   121: invokespecial 183	java/io/ByteArrayOutputStream:<init>	()V
    //   124: astore_1
    //   125: aload_0
    //   126: getstatic 189	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   129: bipush 50
    //   131: aload_1
    //   132: invokevirtual 193	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   135: pop
    //   136: aload_1
    //   137: invokevirtual 197	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   140: astore_1
    //   141: new 199	java/lang/StringBuilder
    //   144: dup
    //   145: invokespecial 200	java/lang/StringBuilder:<init>	()V
    //   148: astore 5
    //   150: aload 5
    //   152: ldc -54
    //   154: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: pop
    //   158: aload 5
    //   160: aload_1
    //   161: iconst_0
    //   162: invokestatic 212	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   165: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: pop
    //   169: aload 5
    //   171: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: astore_1
    //   175: aload_0
    //   176: invokevirtual 218	android/graphics/Bitmap:recycle	()V
    //   179: aload_1
    //   180: areturn
    //   181: astore_0
    //   182: aload_0
    //   183: invokevirtual 221	java/lang/Error:printStackTrace	()V
    //   186: aconst_null
    //   187: areturn
    //   188: astore_0
    //   189: aload_0
    //   190: invokevirtual 222	java/lang/Exception:printStackTrace	()V
    //   193: aconst_null
    //   194: areturn
    //   195: astore_0
    //   196: goto -157 -> 39
    //   199: astore_0
    //   200: iload_3
    //   201: istore 4
    //   203: goto -149 -> 54
    //   206: astore_0
    //   207: goto -99 -> 108
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	210	0	paramContext	Context
    //   0	210	1	paramString	String
    //   0	210	2	paramInt	int
    //   35	166	3	bool1	boolean
    //   42	160	4	bool2	boolean
    //   148	22	5	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   0	31	181	java/lang/Error
    //   31	36	181	java/lang/Error
    //   59	88	181	java/lang/Error
    //   91	105	181	java/lang/Error
    //   117	179	181	java/lang/Error
    //   0	31	188	java/lang/Exception
    //   48	54	188	java/lang/Exception
    //   59	88	188	java/lang/Exception
    //   117	179	188	java/lang/Exception
    //   31	36	195	java/lang/Exception
    //   48	54	199	java/lang/Error
    //   91	105	206	java/lang/Exception
  }
  
  public static String getAppName(Context paramContext)
  {
    try
    {
      int i = paramContext.getApplicationInfo().labelRes;
      if (i == 0) {
        paramContext = paramContext.getApplicationInfo().nonLocalizedLabel.toString();
      } else {
        paramContext = paramContext.getString(i);
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    MyLogger.log("ERROR: can't get app name!");
    return "unknown";
  }
  
  public static String getAppNameForPackage(Context paramContext, String paramString)
  {
    try
    {
      paramString = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      paramContext = paramContext.getPackageManager().getApplicationLabel(paramString).toString();
      return paramContext;
    }
    catch (Exception|Error paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String getAppVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    MyLogger.log("ERROR: can't get app version!");
    return "unknown";
  }
  
  public static String getContactDisplayNameByNumber(Context paramContext, String paramString)
  {
    localObject1 = paramString;
    try
    {
      Object localObject2 = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(paramString));
      localObject1 = paramString;
      localObject2 = paramContext.getContentResolver().query((Uri)localObject2, new String[] { "_id", "display_name" }, null, null, null);
      paramContext = paramString;
      if (localObject2 != null) {
        paramContext = paramString;
      }
      try
      {
        if (((Cursor)localObject2).getCount() > 0)
        {
          ((Cursor)localObject2).moveToFirst();
          paramContext = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("display_name"));
        }
      }
      finally
      {
        if (localObject2 != null)
        {
          localObject1 = paramString;
          ((Cursor)localObject2).close();
        }
        localObject1 = paramString;
      }
      return localObject1;
    }
    catch (Exception paramContext) {}
  }
  
  public static String getDefaultVideoCodec()
  {
    if (Build.MANUFACTURER.toLowerCase().equals("xiaomi")) {
      return "VP9";
    }
    return "VP8";
  }
  
  public static void getDeviceDetails(Context paramContext, final DeviceDetailsCallback paramDeviceDetailsCallback)
  {
    DeviceName.with(paramContext).request(new DeviceName.Callback()
    {
      public void onFinished(DeviceName.DeviceInfo paramAnonymousDeviceInfo, Exception paramAnonymousException)
      {
        localDeviceDetails = new DeviceDetails();
        new Time().setToNow();
        paramAnonymousException = null;
        bool2 = true;
        try
        {
          localObject1 = new IntentFilter("android.intent.action.BATTERY_CHANGED");
          localObject1 = this.val$context.registerReceiver(null, (IntentFilter)localObject1);
          i = ((Intent)localObject1).getIntExtra("status", -1);
          if (i == 2) {
            break label1235;
          }
          if (i != 5) {
            break label1229;
          }
        }
        catch (Exception paramAnonymousDeviceInfo)
        {
          for (;;)
          {
            try
            {
              int i;
              bool1 = ((NetworkInfo)localObject1).isRoaming();
              continue;
              bool1 = false;
              localObject1 = (ConnectivityManager)this.val$context.getApplicationContext().getSystemService("connectivity");
              if (((ConnectivityManager)localObject1).getNetworkInfo(0) != null) {
                paramAnonymousException = ((ConnectivityManager)localObject1).getNetworkInfo(0).getState();
              }
              localObject1 = ((ConnectivityManager)localObject1).getNetworkInfo(1).getState();
              if ((paramAnonymousException != null) && ((paramAnonymousException == NetworkInfo.State.CONNECTED) || (paramAnonymousException == NetworkInfo.State.CONNECTING))) {
                i = 1;
              } else {
                i = 0;
              }
              int j;
              if ((localObject1 != NetworkInfo.State.CONNECTED) && (localObject1 != NetworkInfo.State.CONNECTING)) {
                j = 0;
              } else {
                j = 1;
              }
              if (i != 0)
              {
                localDeviceDetails.setInetStatus(DeviceDetails.InternetStatus.MOBILE.name());
                if (bool1) {
                  localDeviceDetails.setInetStatus(DeviceDetails.InternetStatus.MOBILE_ROAMING.name());
                }
              }
              else if (j != 0)
              {
                paramAnonymousException = "";
              }
            }
            catch (Exception paramAnonymousDeviceInfo)
            {
              try
              {
                Object localObject1 = ((WifiManager)this.val$context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getSSID().replaceAll("\"", "");
                paramAnonymousException = (Exception)localObject1;
                localDeviceDetails.setInetStatus(DeviceDetails.InternetStatus.WIFI.name());
                localDeviceDetails.setWifiSSID(paramAnonymousException);
                localDeviceDetails.setScreenOn(((PowerManager)this.val$context.getSystemService("power")).isScreenOn());
                paramAnonymousException = (LocationManager)this.val$context.getSystemService("location");
                bool1 = paramAnonymousException.isProviderEnabled("gps");
                boolean bool3 = paramAnonymousException.isProviderEnabled("network");
                localDeviceDetails.setGpsEnabled(bool1);
                localDeviceDetails.setNetworkLocationEnabled(bool3);
                localDeviceDetails.setTimeOnPhone(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
                localDeviceDetails.setTimeZone(Calendar.getInstance().getTimeZone().getOffset(new Date().getTime()));
                localDeviceDetails.setAppName(PhoneUtils.getAppName(this.val$context));
                localDeviceDetails.setAppPkgName(this.val$context.getPackageName());
                localDeviceDetails.setAppVersionName(PhoneUtils.getAppVersion(this.val$context));
                paramAnonymousException = (TelephonyManager)this.val$context.getSystemService("phone");
                if (paramAnonymousException == null) {}
              }
              catch (Exception paramAnonymousDeviceInfo)
              {
                try
                {
                  if (((ActivityCompat.checkSelfPermission(this.val$context, "android.permission.READ_SMS") == 0) || (ActivityCompat.checkSelfPermission(this.val$context, "android.permission.READ_PHONE_NUMBERS") == 0) || (ActivityCompat.checkSelfPermission(this.val$context, "android.permission.READ_PHONE_STATE") == 0)) && (paramAnonymousException.getLine1Number() != null) && (!paramAnonymousException.getLine1Number().equals(""))) {
                    localDeviceDetails.setPhoneNumber(paramAnonymousException.getLine1Number());
                  }
                  try
                  {
                    localDeviceDetails.setSerial(paramAnonymousException.getSimSerialNumber());
                  }
                  catch (Exception localException1)
                  {
                    MyLogger.log(localException1.getMessage());
                  }
                }
                catch (Exception paramAnonymousDeviceInfo)
                {
                  try
                  {
                    localObject2 = new StringBuilder();
                    ((StringBuilder)localObject2).append("");
                    ((StringBuilder)localObject2).append(Settings.Secure.getString(this.val$context.getContentResolver(), "android_id"));
                    localDeviceDetails.setAndroidId(((StringBuilder)localObject2).toString());
                  }
                  catch (Exception paramAnonymousDeviceInfo)
                  {
                    try
                    {
                      if (paramAnonymousException.getCallState() != 2) {
                        break label1241;
                      }
                      bool1 = true;
                      localDeviceDetails.setInCall(bool1);
                      continue;
                      localDeviceDetails.setInCall(false);
                      localDeviceDetails.setOperatorName(paramAnonymousException.getNetworkOperatorName());
                      continue;
                      localDeviceDetails.setInCall(false);
                      paramAnonymousException = PreferenceManager.getDefaultSharedPreferences(this.val$context);
                      localDeviceDetails.setAppVisibilityHidden(paramAnonymousException.getBoolean("STATUS_APP_HIDDEN", false));
                      localDeviceDetails.setPhoneIsRooted(RootUtils.isDeviceRooted());
                      localDeviceDetails.setMediaStreamingActive(SharedSettings.getBoolean("grandedMediaProjection", false));
                    }
                    catch (Exception paramAnonymousDeviceInfo)
                    {
                      try
                      {
                        bool1 = NotificationManagerCompat.getEnabledListenerPackages(this.val$context).contains("city.russ.alltrackerfamily");
                        Object localObject2 = StateManager.getNotificationListenerState();
                        if ((!bool1) || (!((StateManager.NotificationListenerState)localObject2).equals(StateManager.NotificationListenerState.CONNECTED))) {
                          break label1247;
                        }
                        bool1 = true;
                        localDeviceDetails.setNotificationListenerActive(bool1);
                        localDeviceDetails.setAppHasRootRights(SharedSettings.getBoolean("given_rights_to_use_root", false));
                        localObject2 = paramAnonymousDeviceInfo.manufacturer;
                        String str1 = paramAnonymousDeviceInfo.marketName;
                        String str2 = paramAnonymousDeviceInfo.model;
                        String str3 = paramAnonymousDeviceInfo.codename;
                        paramAnonymousDeviceInfo = paramAnonymousDeviceInfo.getName();
                        localDeviceDetails.setManufacturer((String)localObject2);
                        localDeviceDetails.setPhoneName(str1);
                        localDeviceDetails.setPhoneModel(str2);
                        localDeviceDetails.setPhoneCodeName(str3);
                        localDeviceDetails.setDeviceName(paramAnonymousDeviceInfo);
                        localDeviceDetails.setAndroidVersion(Build.VERSION.RELEASE);
                        paramAnonymousDeviceInfo = PhoneUtils.getRunningAppPackage(this.val$context);
                        if (paramAnonymousDeviceInfo != null)
                        {
                          localDeviceDetails.setRunningAppPackage(paramAnonymousDeviceInfo);
                          localDeviceDetails.setRunningAppName(PhoneUtils.getAppNameForPackage(this.val$context, paramAnonymousDeviceInfo));
                          localDeviceDetails.setRunningAppIcon(PhoneUtils.getAppIconForPackage(this.val$context, paramAnonymousDeviceInfo, 20));
                        }
                      }
                      catch (Exception paramAnonymousDeviceInfo)
                      {
                        try
                        {
                          paramAnonymousDeviceInfo = PhoneUtils.getInstalledAppInfo(this.val$context, "city.russ.alltrackerstarter");
                          bool1 = paramAnonymousException.getBoolean("SERVICE_CHECKER_STARTED", false);
                          if ((paramAnonymousDeviceInfo == null) || (!bool1)) {
                            break label1253;
                          }
                          bool1 = bool2;
                          localDeviceDetails.setServiceCheckerEnabled(bool1);
                          if (paramAnonymousDeviceInfo != null) {
                            localDeviceDetails.setServiceCheckerVersion(paramAnonymousDeviceInfo.versionName);
                          }
                          localDeviceDetails.setLastServiceCheck(Long.valueOf(paramAnonymousException.getLong("LAST_CHECKING_OF_SERVICES", 0L)));
                          localDeviceDetails.setTimestamp(ServerValue.TIMESTAMP);
                          localDeviceDetails.setAndroidPermissionsGranted(PhoneUtils.checkAndroidPermissions(this.val$context));
                          localDeviceDetails.setBatteryOptimized(PhoneUtils.checkForBatteryOptimization(this.val$context));
                          localDeviceDetails.setBuildVersion(AppConstants.APP_VERSION);
                        }
                        catch (Exception paramAnonymousDeviceInfo)
                        {
                          try
                          {
                            paramAnonymousDeviceInfo = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
                            localDeviceDetails.setFreeSpaceInMB(Long.valueOf(paramAnonymousDeviceInfo.getBlockSize() * paramAnonymousDeviceInfo.getAvailableBlocks() / 1048576L));
                            localDeviceDetails.setUsedSpace(PhoneUtils.getUsedSpace());
                          }
                          catch (Exception paramAnonymousDeviceInfo)
                          {
                            try
                            {
                              long l1 = TrafficStats.getUidRxBytes(this.val$context.getApplicationInfo().uid);
                              long l2 = TrafficStats.getUidTxBytes(this.val$context.getApplicationInfo().uid);
                              localDeviceDetails.setTotalBytesRx(Long.valueOf(l1));
                              localDeviceDetails.setTotalBytesTx(Long.valueOf(l2));
                            }
                            catch (Exception paramAnonymousDeviceInfo)
                            {
                              try
                              {
                                localDeviceDetails.setLastRemoveOfCollectedData(SharedSettings.getLong("LAST_REMOVE_OF_COLLECTED_DATA", Long.valueOf(0L)));
                                localDeviceDetails.setMicrophoneStatus(StateManager.getMicrophoneState().name());
                                localDeviceDetails.setNetworkUsageStatus(StateManager.getNetworkState().name());
                                localDeviceDetails.setTotalNetworkUsage(Long.valueOf(NetworkManager.getTotalTrafficOverTime()));
                                paramDeviceDetailsCallback.onDone(localDeviceDetails);
                                return;
                                localException2 = localException2;
                                continue;
                                localException3 = localException3;
                                continue;
                                localException4 = localException4;
                                continue;
                                paramAnonymousException = paramAnonymousException;
                                continue;
                                localException5 = localException5;
                                continue;
                                localException6 = localException6;
                                continue;
                                localException7 = localException7;
                                continue;
                                paramAnonymousDeviceInfo = paramAnonymousDeviceInfo;
                                continue;
                                paramAnonymousDeviceInfo = paramAnonymousDeviceInfo;
                                continue;
                                paramAnonymousDeviceInfo = paramAnonymousDeviceInfo;
                                continue;
                              }
                              catch (Exception paramAnonymousDeviceInfo)
                              {
                                continue;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            boolean bool1 = false;
            continue;
            bool1 = true;
            continue;
            bool1 = false;
            continue;
            bool1 = false;
            continue;
            bool1 = false;
          }
        }
        i = ((Intent)localObject1).getIntExtra("level", 0);
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(i);
        ((StringBuilder)localObject1).append("");
        localDeviceDetails.setBatteryStatus(((StringBuilder)localObject1).toString());
        localDeviceDetails.setCharging(bool1);
        localObject1 = ((ConnectivityManager)this.val$context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
      }
    });
  }
  
  /* Error */
  public static Set<city.russ.alltrackercorp.models.AppDescription> getFullListOfInstalledApps(Context paramContext, city.russ.alltrackercorp.listeners.SimpleProgressListener paramSimpleProgressListener)
  {
    // Byte code:
    //   0: new 356	java/util/LinkedList
    //   3: dup
    //   4: invokespecial 357	java/util/LinkedList:<init>	()V
    //   7: astore 7
    //   9: new 91	android/content/Intent
    //   12: dup
    //   13: ldc_w 359
    //   16: aconst_null
    //   17: invokespecial 362	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   20: astore 8
    //   22: aload 8
    //   24: ldc_w 364
    //   27: invokevirtual 368	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   30: pop
    //   31: aload_0
    //   32: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   35: aload 8
    //   37: iconst_0
    //   38: invokevirtual 372	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   41: astore 8
    //   43: aload 8
    //   45: astore 7
    //   47: new 374	java/util/HashSet
    //   50: dup
    //   51: invokespecial 375	java/util/HashSet:<init>	()V
    //   54: astore 8
    //   56: new 91	android/content/Intent
    //   59: dup
    //   60: ldc_w 359
    //   63: aconst_null
    //   64: invokespecial 362	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   67: ldc_w 364
    //   70: invokevirtual 368	android/content/Intent:addCategory	(Ljava/lang/String;)Landroid/content/Intent;
    //   73: pop
    //   74: aload_0
    //   75: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   78: sipush 128
    //   81: invokevirtual 379	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   84: astore 9
    //   86: aload 9
    //   88: invokeinterface 27 1 0
    //   93: astore 10
    //   95: aload 10
    //   97: invokeinterface 33 1 0
    //   102: ifeq +244 -> 346
    //   105: aload 10
    //   107: invokeinterface 37 1 0
    //   112: checkcast 229	android/content/pm/ApplicationInfo
    //   115: astore 11
    //   117: aload_0
    //   118: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   121: aload 11
    //   123: getfield 380	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   126: iconst_0
    //   127: invokevirtual 127	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   130: astore 13
    //   132: aload_0
    //   133: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   136: aload 13
    //   138: invokevirtual 131	android/content/pm/PackageManager:getApplicationIcon	(Landroid/content/pm/ApplicationInfo;)Landroid/graphics/drawable/Drawable;
    //   141: astore 12
    //   143: aload_0
    //   144: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   147: aload 13
    //   149: invokevirtual 259	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   152: invokeinterface 239 1 0
    //   157: astore 13
    //   159: aload 8
    //   161: new 382	city/russ/alltrackercorp/models/AppDescription
    //   164: dup
    //   165: aload 11
    //   167: getfield 380	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   170: aload 13
    //   172: aload 12
    //   174: aload 11
    //   176: getfield 380	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   179: aload 7
    //   181: invokestatic 384	city/russ/alltrackercorp/utils/PhoneUtils:appContainsInList	(Ljava/lang/String;Ljava/util/List;)Z
    //   184: iconst_1
    //   185: ixor
    //   186: invokespecial 387	city/russ/alltrackercorp/models/AppDescription:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Z)V
    //   189: invokeinterface 392 2 0
    //   194: pop
    //   195: goto +91 -> 286
    //   198: astore 12
    //   200: aload 12
    //   202: invokevirtual 395	java/lang/Throwable:printStackTrace	()V
    //   205: invokestatic 400	java/lang/System:gc	()V
    //   208: aload_0
    //   209: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   212: aload 11
    //   214: getfield 380	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   217: iconst_0
    //   218: invokevirtual 127	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   221: astore 13
    //   223: aload_0
    //   224: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   227: aload 13
    //   229: invokevirtual 131	android/content/pm/PackageManager:getApplicationIcon	(Landroid/content/pm/ApplicationInfo;)Landroid/graphics/drawable/Drawable;
    //   232: astore 12
    //   234: aload_0
    //   235: invokevirtual 121	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   238: aload 13
    //   240: invokevirtual 259	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   243: invokeinterface 239 1 0
    //   248: astore 13
    //   250: aload 8
    //   252: new 382	city/russ/alltrackercorp/models/AppDescription
    //   255: dup
    //   256: aload 11
    //   258: getfield 380	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   261: aload 13
    //   263: aload 12
    //   265: aload 11
    //   267: getfield 380	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   270: aload 7
    //   272: invokestatic 384	city/russ/alltrackercorp/utils/PhoneUtils:appContainsInList	(Ljava/lang/String;Ljava/util/List;)Z
    //   275: iconst_1
    //   276: ixor
    //   277: invokespecial 387	city/russ/alltrackercorp/models/AppDescription:<init>	(Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Z)V
    //   280: invokeinterface 392 2 0
    //   285: pop
    //   286: aload 8
    //   288: invokeinterface 403 1 0
    //   293: istore 6
    //   295: iload 6
    //   297: i2d
    //   298: dstore_2
    //   299: dload_2
    //   300: invokestatic 409	java/lang/Double:isNaN	(D)Z
    //   303: pop
    //   304: aload 9
    //   306: invokeinterface 410 1 0
    //   311: istore 6
    //   313: iload 6
    //   315: i2d
    //   316: dstore 4
    //   318: dload 4
    //   320: invokestatic 409	java/lang/Double:isNaN	(D)Z
    //   323: pop
    //   324: dload_2
    //   325: dconst_1
    //   326: dmul
    //   327: dload 4
    //   329: ddiv
    //   330: ldc2_w 411
    //   333: dmul
    //   334: d2i
    //   335: istore 6
    //   337: aload_1
    //   338: iload 6
    //   340: invokevirtual 418	city/russ/alltrackercorp/listeners/SimpleProgressListener:onProgress	(I)V
    //   343: goto -248 -> 95
    //   346: aload 8
    //   348: areturn
    //   349: astore 8
    //   351: goto -304 -> 47
    //   354: astore_0
    //   355: aload 8
    //   357: areturn
    //   358: astore 11
    //   360: goto -74 -> 286
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	363	0	paramContext	Context
    //   0	363	1	paramSimpleProgressListener	city.russ.alltrackercorp.listeners.SimpleProgressListener
    //   298	27	2	d1	double
    //   316	12	4	d2	double
    //   293	46	6	i	int
    //   7	264	7	localObject1	Object
    //   20	327	8	localObject2	Object
    //   349	7	8	localException1	Exception
    //   84	221	9	localList	List
    //   93	13	10	localIterator	Iterator
    //   115	151	11	localApplicationInfo	ApplicationInfo
    //   358	1	11	localException2	Exception
    //   141	32	12	localDrawable1	android.graphics.drawable.Drawable
    //   198	3	12	localException3	Exception
    //   232	32	12	localDrawable2	android.graphics.drawable.Drawable
    //   130	132	13	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   117	195	198	java/lang/Exception
    //   117	195	198	java/lang/OutOfMemoryError
    //   9	43	349	java/lang/Exception
    //   74	95	354	java/lang/Exception
    //   95	117	354	java/lang/Exception
    //   200	205	354	java/lang/Exception
    //   286	295	354	java/lang/Exception
    //   304	313	354	java/lang/Exception
    //   337	343	354	java/lang/Exception
    //   205	286	358	java/lang/Exception
    //   205	286	358	java/lang/OutOfMemoryError
  }
  
  public static PackageInfo getInstalledAppInfo(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 1);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static void getMinimalDeviceDetails(Context paramContext, DatabaseReference paramDatabaseReference, DeviceDetailsCallbackWithFirebase paramDeviceDetailsCallbackWithFirebase)
  {
    localHashMap = new HashMap();
    new Time().setToNow();
    localObject1 = null;
    k = 1;
    try
    {
      localObject2 = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      i = ((Intent)localObject2).getIntExtra("status", -1);
      if (i == 2) {
        break label615;
      }
      if (i != 5) {
        break label609;
      }
    }
    catch (Exception localException4)
    {
      for (;;)
      {
        try
        {
          int i;
          bool = ((NetworkInfo)localObject2).isRoaming();
          continue;
          bool = false;
          localObject2 = (ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity");
          if (((ConnectivityManager)localObject2).getNetworkInfo(0) != null) {
            localObject1 = ((ConnectivityManager)localObject2).getNetworkInfo(0).getState();
          }
          localObject2 = ((ConnectivityManager)localObject2).getNetworkInfo(1).getState();
          if ((localObject1 != null) && ((localObject1 == NetworkInfo.State.CONNECTED) || (localObject1 == NetworkInfo.State.CONNECTING))) {
            i = 1;
          } else {
            i = 0;
          }
          int j = k;
          if (localObject2 != NetworkInfo.State.CONNECTED) {
            if (localObject2 == NetworkInfo.State.CONNECTING) {
              j = k;
            } else {
              j = 0;
            }
          }
          if (i != 0)
          {
            localHashMap.put("inetStatus", DeviceDetails.InternetStatus.MOBILE.name());
            if (bool) {
              localHashMap.put("inetStatus", DeviceDetails.InternetStatus.MOBILE_ROAMING.name());
            }
          }
          else if (j != 0)
          {
            localObject1 = "";
          }
        }
        catch (Exception localException4)
        {
          try
          {
            Object localObject2 = ((WifiManager)paramContext.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getSSID().replaceAll("\"", "");
            localObject1 = localObject2;
            localHashMap.put("inetStatus", DeviceDetails.InternetStatus.WIFI.name());
            localHashMap.put("wifiSSID", localObject1);
            localHashMap.put("screenOn", Boolean.valueOf(((PowerManager)paramContext.getSystemService("power")).isScreenOn()));
            localObject1 = Calendar.getInstance();
            localHashMap.put("timeOnPhone", Long.valueOf(((Calendar)localObject1).getTimeInMillis()));
            localHashMap.put("timeZone", Integer.valueOf(((Calendar)localObject1).getTimeZone().getOffset(new Date().getTime())));
            localHashMap.put("responseTime", Long.valueOf(System.currentTimeMillis()));
          }
          catch (Exception localException4)
          {
            try
            {
              localObject1 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
              localHashMap.put("freeSpaceInMB", Long.valueOf(((StatFs)localObject1).getBlockSize() * ((StatFs)localObject1).getAvailableBlocks() / 1048576L));
              localObject1 = getRunningAppPackage(paramContext);
              if (localObject1 != null)
              {
                localHashMap.put("runningAppPackage", localObject1);
                localHashMap.put("runningAppName", getAppNameForPackage(paramContext, (String)localObject1));
                localHashMap.put("runningAppIcon", getAppIconForPackage(paramContext, (String)localObject1, 15));
              }
              localHashMap.put("microphoneStatus", StateManager.getMicrophoneState().name());
              paramDeviceDetailsCallbackWithFirebase.onDone(localHashMap, paramDatabaseReference);
              return;
              localException2 = localException2;
              continue;
              localException3 = localException3;
              continue;
              localException4 = localException4;
            }
            catch (Exception localException1)
            {
              continue;
            }
          }
        }
        boolean bool = false;
        continue;
        bool = true;
      }
    }
    localHashMap.put("batteryStatus", Integer.valueOf(((Intent)localObject2).getIntExtra("level", 0)));
    localHashMap.put("charging", Boolean.valueOf(bool));
    localObject2 = ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
  }
  
  public static String getRunningAppPackage(Context paramContext)
  {
    boolean bool = statisticUsageAllowed(paramContext);
    Object localObject1 = null;
    if (!bool) {
      return null;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramContext = (UsageStatsManager)paramContext.getSystemService("usagestats");
      long l = System.currentTimeMillis();
      Object localObject2 = paramContext.queryUsageStats(0, l - 1000000L, l);
      paramContext = localObject1;
      if (localObject2 != null)
      {
        paramContext = localObject1;
        if (((List)localObject2).size() > 0)
        {
          TreeMap localTreeMap = new TreeMap();
          paramContext = ((List)localObject2).iterator();
          while (paramContext.hasNext())
          {
            localObject2 = (UsageStats)paramContext.next();
            localTreeMap.put(Long.valueOf(((UsageStats)localObject2).getLastTimeUsed()), localObject2);
          }
          paramContext = localObject1;
          if (!localTreeMap.isEmpty()) {
            return ((UsageStats)localTreeMap.get(localTreeMap.lastKey())).getPackageName();
          }
        }
      }
    }
    else
    {
      paramContext = ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getApplicationContext().getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
    }
    return paramContext;
  }
  
  public static Long getUsedSpace()
  {
    try
    {
      long l = FileUtils.folderSize(new File(AppConstants.MAIN_FILE_CONTAINER_PATH));
      return Long.valueOf(l);
    }
    catch (Exception localException) {}
    return Long.valueOf(0L);
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private static void isCamera14UsebyAnotherApp(SimpleResultListener paramSimpleResultListener)
  {
    try
    {
      try
      {
        Camera localCamera = Camera.open();
        boolean bool2 = true;
        bool1 = bool2;
        if (localCamera == null) {
          break label26;
        }
        localCamera.release();
        bool1 = bool2;
      }
      finally {}
    }
    catch (RuntimeException localRuntimeException)
    {
      boolean bool1;
      label26:
      for (;;) {}
    }
    bool1 = false;
    paramSimpleResultListener.onDone(Boolean.valueOf(bool1));
  }
  
  @TargetApi(21)
  private static void isCamera21UsebyAnotherApp(Context paramContext, final SimpleResultListener paramSimpleResultListener)
  {
    try
    {
      paramContext = (CameraManager)paramContext.getSystemService("camera");
      paramContext.registerAvailabilityCallback(new CameraManager.AvailabilityCallback()new Handler
      {
        public void onCameraAvailable(String paramAnonymousString)
        {
          try
          {
            super.onCameraAvailable(paramAnonymousString);
            this.val$manager.unregisterAvailabilityCallback(this);
            paramSimpleResultListener.onDone(Boolean.valueOf(true));
            return;
          }
          catch (Exception paramAnonymousString)
          {
            for (;;) {}
          }
          catch (Error paramAnonymousString)
          {
            for (;;) {}
          }
          paramSimpleResultListener.onDone(Boolean.valueOf(false));
          return;
          paramSimpleResultListener.onDone(Boolean.valueOf(false));
        }
        
        public void onCameraUnavailable(String paramAnonymousString)
        {
          try
          {
            super.onCameraUnavailable(paramAnonymousString);
            this.val$manager.unregisterAvailabilityCallback(this);
            paramSimpleResultListener.onDone(Boolean.valueOf(false));
            return;
          }
          catch (Exception paramAnonymousString)
          {
            for (;;) {}
          }
          catch (Error paramAnonymousString)
          {
            for (;;) {}
          }
          paramSimpleResultListener.onDone(Boolean.valueOf(false));
          return;
          paramSimpleResultListener.onDone(Boolean.valueOf(false));
        }
      }, new Handler());
      return;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    catch (Error paramContext)
    {
      for (;;) {}
    }
    paramSimpleResultListener.onDone(Boolean.valueOf(false));
    return;
    paramSimpleResultListener.onDone(Boolean.valueOf(false));
  }
  
  public static void isCameraUsebyAnotherApp(Context paramContext, SimpleResultListener paramSimpleResultListener)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      isCamera21UsebyAnotherApp(paramContext, paramSimpleResultListener);
      return;
    }
    isCamera14UsebyAnotherApp(paramSimpleResultListener);
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null) {
      paramContext = paramContext.getActiveNetworkInfo();
    } else {
      paramContext = null;
    }
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static void removeLocalData(Context paramContext, boolean paramBoolean)
  {
    try
    {
      SugarContext.terminate();
      SchemaGenerator localSchemaGenerator = new SchemaGenerator(paramContext);
      localSchemaGenerator.deleteTables(new SugarDb(paramContext).getDB());
      SugarContext.init(paramContext);
      localSchemaGenerator.createDatabase(new SugarDb(paramContext).getDB());
      if (paramBoolean) {
        PreferenceManager.getDefaultSharedPreferences(paramContext).edit().clear().apply();
      }
      FileUtils.deleteRecursive(new File(AppConstants.MAIN_FILE_CONTAINER_PATH));
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean statisticUsageAllowed(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      int i = ((AppOpsManager)paramContext.getSystemService("appops")).checkOpNoThrow("android:get_usage_stats", Process.myUid(), paramContext.getPackageName());
      if (i == 3) {
        if ((Build.VERSION.SDK_INT >= 23) && (paramContext.checkCallingOrSelfPermission("android.permission.PACKAGE_USAGE_STATS") == 0)) {
          return true;
        }
      }
      while (i != 0) {
        return false;
      }
    }
    return true;
  }
  
  public static abstract interface DeviceDetailsCallback
  {
    public abstract void onDone(DeviceDetails paramDeviceDetails);
  }
  
  public static abstract interface DeviceDetailsCallbackWithFirebase
  {
    public abstract void onDone(Map<String, Object> paramMap, DatabaseReference paramDatabaseReference);
  }
}
