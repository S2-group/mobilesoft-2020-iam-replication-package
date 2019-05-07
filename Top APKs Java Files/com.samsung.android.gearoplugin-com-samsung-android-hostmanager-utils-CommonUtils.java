package com.samsung.android.hostmanager.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.os.SemSystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.widget.Toast;
import com.samsung.android.app.watchmanager.plugin.libfactory.bluetooth.BluetoothDeviceFactory;
import com.samsung.android.app.watchmanager.plugin.libfactory.systemproperty.SystemPropertyFactory;
import com.samsung.android.app.watchmanager.plugin.libfactory.windowmanager.WindowManagerFactory;
import com.samsung.android.app.watchmanager.plugin.libinterface.bluetooth.BluetoothDeviceInterface;
import com.samsung.android.app.watchmanager.plugin.libinterface.systemproperty.AndroidSystemInterface;
import com.samsung.android.app.watchmanager.plugin.libinterface.windowmanager.IWindowManagerInterface;
import com.samsung.android.hostmanager.HMApplication;
import com.samsung.android.hostmanager.aidl.DeviceInfo;
import com.samsung.android.hostmanager.connectionmanager.util.BluetoothUtil;
import com.samsung.android.hostmanager.exception.DeviceNotSupportedException;
import com.samsung.android.hostmanager.manager.DeviceManager;
import com.samsung.android.hostmanager.manager.IPackageManager;
import com.samsung.android.hostmanager.manager.IStatusManager;
import com.samsung.android.hostmanager.manager.WearableDeviceFactory;
import com.samsung.android.hostmanager.service.HostManager;
import com.samsung.android.hostmanager.service.IUHostManager;
import com.samsung.android.uhm.framework.appregistry.RegistryDbManagerWithProvider;
import com.samsung.android.uhm.framework.appregistry.data.DeviceRegistryData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Builder;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class CommonUtils
{
  public static final String AUTO_UPDATE_DIR = "AUTO_UPDATE";
  public static final String CSC_PATH = "/system/csc/sales_code.dat";
  public static final char DATE = 'd';
  private static final String DEVELOPER_MODE_KEY = "developer_mode";
  public static int HIGHER = 0;
  private static final String HOSTMANAGER_TAG;
  public static int LOWER = -1;
  private static final String[] MASS_LIST;
  public static final char MONTH = 'M';
  public static int NONE = -2;
  private static final String[] NOT_SUPPORTED_SCAN_FILTER_MODEL_LIST;
  private static final String PARAMETERS_FILE_LOCAL_PATH;
  private static final String PREF_DEVELOPER_MODE = "pref_developer_mode";
  public static int SAME = 0;
  public static final int STAY_CONNECTED_HIPRI_STATUS_ENABLED = 2;
  public static final int STAY_CONNECTED_HIPRI_STATUS_NEED_UPDATE = -1;
  public static final int STAY_CONNECTED_HIPRI_STATUS_NOT_ENABLED = 1;
  public static final int STAY_CONNECTED_HIPRI_STATUS_NOT_EXIST = -2;
  public static final int STAY_CONNECTED_HIPRI_STATUS_NOT_SIGNED = 0;
  private static final String TAG = CommonUtils.class.getSimpleName();
  private static final String TEST_WEB_STORE_FOLDER = "test_web_store";
  private static final String[] WEBSTORE_URL_LIST;
  public static final char YEAR = 'y';
  public static final int invalidValue = -1;
  private static boolean isMassModel;
  private static boolean isMassModelCheckedBefore;
  private static boolean isMassModelCheckedBeforeforPlugin;
  private static boolean isMassModelforPlugin;
  private static boolean isNotSupportedScanFilterModel;
  private static boolean isNotSupportedScanFilterModelCheckedBefore;
  public static String mDeviceType;
  
  static
  {
    HOSTMANAGER_TAG = HostManager.class.getSimpleName();
    PARAMETERS_FILE_LOCAL_PATH = "test_web_store" + File.separator + "test_config.txt";
    isMassModelCheckedBefore = false;
    isMassModel = false;
    MASS_LIST = new String[] { "SM-E500H", "SM-E500HQ", "SM-E500F", "SM-E500YZ", "SM-E500M", "SM-S978L", "SM-E700H", "SM-E700F", "SM-E700M", "SM-E7009", "SM-E7000", "SM-J321AZ", "SM-J320AZ", "SM-J320A", "SM-J320Y", "SM-J320P", "SM-J320YZ", "SM-J3109", "SM-J320V", "SM-J320H", "SM-J320F", "SM-J320G", "SM-J320M", "SM-J320FN", "SM-J500H", "SM-J500F", "SM-J500M", "SM-J500G", "SM-J5008", "SM-J500Y", "SM-J500N0", "SM-J500FN", "SM-J5007", "SM-J700H", "SM-J7008", "SM-J700F", "SM-J700M", "SM-J700K", "SM-J700T", "SM-J700T1", "SM-J700P" };
    isNotSupportedScanFilterModelCheckedBefore = false;
    isNotSupportedScanFilterModel = false;
    NOT_SUPPORTED_SCAN_FILTER_MODEL_LIST = new String[] { "SM-A310F", "SM-A310N0", "SM-A310Y", "SM-J7108", "SM-J710FN", "SM-J710K", "SM-J710GN", "SM-J710MN", "SM-J710F", "SM-J710FQ", "SM-J320A", "SM-J321AZ", "SM-J320AZ", "SM-J320W8", "SM-G610F", "SM-G610Y", "SM-G610M", "SM-G610S", "SM-G610K", "SM-G610L" };
    WEBSTORE_URL_LIST = new String[] { "52.18.34.211", "gearapps.samsung.com", "cn-gearapps.samsung.com", "stg-img.gw.samsungapps.com", "stg-gearapps.gw.samsungapps.com" };
    isMassModelCheckedBeforeforPlugin = false;
    isMassModelforPlugin = false;
    HIGHER = 1;
    SAME = 0;
  }
  
  public CommonUtils() {}
  
  public static boolean DEBUGGABLE()
  {
    try
    {
      boolean bool = isEngBuild();
      return bool;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      Log.v(TAG, localNoSuchMethodError.toString());
    }
    return false;
  }
  
  public static int _getMNC(Context paramContext)
  {
    int j = 0;
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null)
    {
      Log.i(TAG, "NetworkConfig::_getMNC::telMgr is null");
      return 0;
    }
    paramContext = paramContext.getSimOperator();
    int i = j;
    if (paramContext != null)
    {
      i = j;
      if (paramContext.length() == 0) {}
    }
    try
    {
      i = Integer.parseInt(paramContext.substring(3));
      Log.i(TAG, "MNC = " + i);
      return i;
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      for (;;)
      {
        Log.i(TAG, "NetworkConfig::_getMNC::IndexOutOfBoundsException");
        i = j;
      }
    }
  }
  
  public static String changeAddressToBTaddress(String paramString)
  {
    Log.e(TAG, "changeAddressToBTaddress() address = " + paramString);
    String str = null;
    if (paramString != null) {
      if (!paramString.startsWith("SAMSUNG_ACCESSARY__")) {
        break label83;
      }
    }
    label83:
    for (str = paramString.substring("SAMSUNG_ACCESSARY__".length());; str = paramString)
    {
      Log.e(TAG, "changeAddressToBTaddress() result = " + str);
      return str;
    }
  }
  
  public static int changeFilePermission(String paramString, int paramInt)
  {
    Log.d(TAG, "changeFilePermission(" + paramString + ", " + paramInt + ")");
    try
    {
      paramInt = ((Integer)Class.forName("android.os.FileUtils").getMethod("setPermissions", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE }).invoke(null, new Object[] { paramString, Integer.valueOf(paramInt), Integer.valueOf(-1), Integer.valueOf(-1) })).intValue();
      return paramInt;
    }
    catch (ClassNotFoundException paramString)
    {
      paramString.printStackTrace();
      return 64536;
    }
    catch (ExceptionInInitializerError paramString)
    {
      paramString.printStackTrace();
      return 64536;
    }
    catch (NoSuchMethodError paramString)
    {
      paramString.printStackTrace();
      return 64536;
    }
    catch (NoSuchMethodException paramString)
    {
      paramString.printStackTrace();
      return 64536;
    }
    catch (IllegalAccessException paramString)
    {
      paramString.printStackTrace();
      return 64536;
    }
    catch (IllegalAccessError paramString)
    {
      paramString.printStackTrace();
      return 64536;
    }
    catch (IllegalArgumentException paramString)
    {
      paramString.printStackTrace();
      return 64536;
    }
    catch (LinkageError paramString)
    {
      paramString.printStackTrace();
      return 64536;
    }
    catch (InvocationTargetException paramString)
    {
      paramString.printStackTrace();
      return 64536;
    }
    finally
    {
      Log.d(TAG, "changeFilePermission()-->result = " + 64536);
    }
  }
  
  /* Error */
  public static boolean changeMode(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 68	com/samsung/android/hostmanager/utils/CommonUtils:TAG	Ljava/lang/String;
    //   3: ldc_w 396
    //   6: invokestatic 349	com/samsung/android/hostmanager/utils/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   9: pop
    //   10: aload_1
    //   11: invokestatic 402	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   14: ifne +11 -> 25
    //   17: aload_0
    //   18: aload_1
    //   19: invokevirtual 320	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   22: ifne +5 -> 27
    //   25: iconst_1
    //   26: ireturn
    //   27: invokestatic 408	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   30: iconst_3
    //   31: anewarray 99	java/lang/String
    //   34: dup
    //   35: iconst_0
    //   36: ldc_w 410
    //   39: aastore
    //   40: dup
    //   41: iconst_1
    //   42: ldc_w 412
    //   45: aastore
    //   46: dup
    //   47: iconst_2
    //   48: aload_1
    //   49: aastore
    //   50: invokevirtual 416	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   53: pop
    //   54: aload_0
    //   55: aload_1
    //   56: invokevirtual 420	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   59: iconst_1
    //   60: aaload
    //   61: getstatic 86	java/io/File:separator	Ljava/lang/String;
    //   64: invokevirtual 420	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   67: astore 4
    //   69: aload 4
    //   71: arraylength
    //   72: istore_3
    //   73: iconst_0
    //   74: istore_2
    //   75: iload_2
    //   76: iload_3
    //   77: if_icmpge +135 -> 212
    //   80: aload 4
    //   82: iload_2
    //   83: aaload
    //   84: astore 5
    //   86: aload_1
    //   87: astore_0
    //   88: aload 5
    //   90: invokestatic 402	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   93: ifne +66 -> 159
    //   96: new 74	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   103: aload_1
    //   104: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: getstatic 86	java/io/File:separator	Ljava/lang/String;
    //   110: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: aload 5
    //   115: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   121: astore_0
    //   122: aload_0
    //   123: ldc_w 422
    //   126: invokevirtual 425	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   129: ifne +46 -> 175
    //   132: invokestatic 408	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   135: iconst_3
    //   136: anewarray 99	java/lang/String
    //   139: dup
    //   140: iconst_0
    //   141: ldc_w 410
    //   144: aastore
    //   145: dup
    //   146: iconst_1
    //   147: ldc_w 412
    //   150: aastore
    //   151: dup
    //   152: iconst_2
    //   153: aload_0
    //   154: aastore
    //   155: invokevirtual 416	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   158: pop
    //   159: iload_2
    //   160: iconst_1
    //   161: iadd
    //   162: istore_2
    //   163: aload_0
    //   164: astore_1
    //   165: goto -90 -> 75
    //   168: astore_0
    //   169: aload_0
    //   170: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   173: iconst_0
    //   174: ireturn
    //   175: invokestatic 408	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   178: iconst_3
    //   179: anewarray 99	java/lang/String
    //   182: dup
    //   183: iconst_0
    //   184: ldc_w 410
    //   187: aastore
    //   188: dup
    //   189: iconst_1
    //   190: ldc_w 428
    //   193: aastore
    //   194: dup
    //   195: iconst_2
    //   196: aload_0
    //   197: aastore
    //   198: invokevirtual 416	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   201: pop
    //   202: goto -43 -> 159
    //   205: astore_0
    //   206: aload_0
    //   207: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   210: iconst_0
    //   211: ireturn
    //   212: iconst_1
    //   213: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	214	0	paramString1	String
    //   0	214	1	paramString2	String
    //   74	89	2	i	int
    //   72	6	3	j	int
    //   67	14	4	arrayOfString	String[]
    //   84	30	5	str	String
    // Exception table:
    //   from	to	target	type
    //   27	54	168	java/io/IOException
    //   122	159	205	java/io/IOException
    //   175	202	205	java/io/IOException
  }
  
  public static String checkForValidUrl(String paramString)
  {
    if (paramString != null)
    {
      String str;
      int i;
      if (paramString.startsWith("http://"))
      {
        str = paramString.replace("http://", "");
        paramString = str;
        if (str.contains("/")) {
          paramString = str.replace("/", "");
        }
        Log.d(TAG, " checkForValidUrl() : " + paramString);
        i = 0;
      }
      for (;;)
      {
        if (i >= WEBSTORE_URL_LIST.length) {
          break label136;
        }
        if (WEBSTORE_URL_LIST[i].equals(paramString))
        {
          return WEBSTORE_URL_LIST[i];
          str = paramString;
          if (!paramString.startsWith("https://")) {
            break;
          }
          str = paramString.replace("https://", "");
          break;
        }
        i += 1;
      }
    }
    label136:
    return "reject";
  }
  
  public static String checkNeckletConnected(Context paramContext)
  {
    Object localObject = Uri.parse("content://com.samsung.android.uhm.framework.appregistry.BaseContentProvider.provider/Device");
    paramContext = paramContext.getContentResolver().query((Uri)localObject, null, null, null, null);
    if ((paramContext != null) && (paramContext.moveToFirst())) {
      do
      {
        localObject = paramContext.getString(paramContext.getColumnIndex("package_name"));
        String str1 = paramContext.getString(paramContext.getColumnIndex("device_name"));
        String str2 = paramContext.getString(paramContext.getColumnIndex("bt_id"));
        int i = paramContext.getInt(paramContext.getColumnIndex("last_launch"));
        int j = paramContext.getInt(paramContext.getColumnIndex("connected"));
        String str3 = paramContext.getString(paramContext.getColumnIndex("device_fixed_name"));
        String str4 = paramContext.getString(paramContext.getColumnIndex("necklet_auto_connection"));
        Log.d(TAG, "PackageName : " + (String)localObject);
        Log.d(TAG, "deviceName : " + str1);
        Log.d(TAG, "btId : " + str2);
        Log.d(TAG, "lastLaunch : " + i);
        Log.d(TAG, "connected : " + j);
        Log.d(TAG, "fixedName : " + str3);
        Log.d(TAG, "autoConnection :" + str4);
        if ((str1 != null) && (str1.contains("Gear Circle")) && (j == 2))
        {
          Log.d(TAG, "Gear Necklet is connected state.");
          if (paramContext != null) {
            paramContext.close();
          }
          return str2;
        }
      } while (paramContext.moveToNext());
    }
    if (paramContext != null) {
      paramContext.close();
    }
    return null;
  }
  
  public static int compareGearOSVersion(String paramString)
  {
    int i = 0;
    String str = HMApplication.getAppContext().getSharedPreferences("bnr_deviceinfo", 0).getString("ANDROID_VERSION", "");
    String[] arrayOfString1 = paramString.split("\\.");
    String[] arrayOfString2 = str.split("\\.");
    Log.d(TAG, "compareGearOSVersion() compVer : " + paramString + " or tizenVer : " + str);
    if ((arrayOfString1 != null) && (arrayOfString2 != null))
    {
      if (arrayOfString2.length < arrayOfString1.length) {
        i = 1;
      }
      int j;
      int k;
      if (i != 0)
      {
        j = arrayOfString2.length;
        k = 0;
      }
      for (;;)
      {
        if (k >= j) {
          break label195;
        }
        try
        {
          int m = Integer.parseInt(arrayOfString1[k]);
          int n = Integer.parseInt(arrayOfString2[k]);
          if (n > m)
          {
            i = HIGHER;
            return i;
            j = arrayOfString1.length;
            break;
          }
          if (n < m)
          {
            i = LOWER;
            return i;
          }
        }
        catch (NumberFormatException paramString)
        {
          Log.d(TAG, "compareGearOSVersion() number format exception occured, it is unexpected situation, return false");
          paramString.printStackTrace();
          return NONE;
        }
        k += 1;
      }
      label195:
      if (arrayOfString2.length == arrayOfString1.length)
      {
        Log.d(TAG, "compareGearOSVersion() the version is same");
        return SAME;
      }
      if (i != 0) {
        return LOWER;
      }
      return HIGHER;
    }
    Log.d(TAG, "compareGearOSVersion() certain list is null, return false");
    return NONE;
  }
  
  public static ArrayList<Intent> convertImplicitToExplicitForBroadCast(Context paramContext, Intent paramIntent)
  {
    paramContext = paramContext.getPackageManager().queryBroadcastReceivers(paramIntent, 0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        Object localObject2 = (ResolveInfo)paramContext.get(i);
        Object localObject1 = ((ResolveInfo)localObject2).activityInfo.packageName;
        localObject2 = ((ResolveInfo)localObject2).activityInfo.name;
        com.samsung.android.hostmanager.pm.log.Log.d(TAG, "convertImplicitToExplicitForBroadCast packageName : " + (String)localObject1 + "/ className : " + (String)localObject2);
        localObject1 = new ComponentName((String)localObject1, (String)localObject2);
        localObject2 = new Intent(paramIntent);
        ((Intent)localObject2).setComponent((ComponentName)localObject1);
        localArrayList.add(localObject2);
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public static Bitmap decodeFile(int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inSampleSize = 2;
    return BitmapFactory.decodeResource(HMApplication.getAppContext().getResources(), paramInt, localOptions);
  }
  
  public static Bitmap decodeFile(File paramFile)
  {
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(paramFile), null, localOptions);
      int m = (int)HMApplication.getAppContext().getResources().getDimension(17104901);
      int n = (int)HMApplication.getAppContext().getResources().getDimension(17104902);
      int k = localOptions.outWidth;
      int j = localOptions.outHeight;
      Log.d(TAG, "decodeFile() width : " + k + ", height : " + j + " , notificationLayout_width : " + m + " , notificationLayout_height : " + n);
      int i = 1;
      for (;;)
      {
        Log.d(TAG, "decodeFile() scale : " + i + ", width : " + k + ", height : " + j);
        if ((k / 2 < m / 2) || (j / 2 < n / 2))
        {
          Log.d(TAG, "decodeFile() result : scale : " + i + ", width : " + k + ", height : " + j);
          localOptions = new BitmapFactory.Options();
          localOptions.inSampleSize = i;
          return BitmapFactory.decodeStream(new FileInputStream(paramFile), null, localOptions);
        }
        k /= 2;
        j /= 2;
        i *= 2;
      }
      return null;
    }
    catch (FileNotFoundException paramFile) {}
  }
  
  public static void dismissKeyGuard()
  {
    Log.d(TAG, "dismissKeyGuard!!");
    KeyguardManager localKeyguardManager = (KeyguardManager)HMApplication.getAppContext().getSystemService("keyguard");
    WindowManagerFactory.get().dismissKeyguard(localKeyguardManager);
  }
  
  public static String get2ModemValue()
  {
    String str = SystemPropertyFactory.getAndroidSystemProperty().get("ril.MSIMM");
    Log.d(TAG, "get2ModemValue :: ril.MSIMM [" + str + "]");
    return str;
  }
  
  public static String get2SimValue()
  {
    String str = SystemPropertyFactory.getAndroidSystemProperty().get("persist.radio.calldefault.simid");
    Log.d(TAG, "get2SimValue)() - persist_radio_calldefault_simid : " + str);
    return str;
  }
  
  public static Account[] getAccountArray(Context paramContext, String paramString)
  {
    Context localContext = paramContext;
    if (paramContext == null) {
      localContext = HMApplication.getAppContext();
    }
    if (localContext != null)
    {
      paramContext = AccountManager.get(localContext);
      if (paramContext != null) {
        return paramContext.getAccountsByType(paramString);
      }
      com.samsung.android.hostmanager.log.Log.e(TAG, "isSignedIn() AccountManager is null, return false");
    }
    for (;;)
    {
      return null;
      com.samsung.android.hostmanager.log.Log.e(TAG, "isSignedIn() context is still null, return false");
    }
  }
  
  public static String getAllRegisteredDeviceList(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = new RegistryDbManagerWithProvider().queryAllDeviceRegistryData(paramContext);
      JSONArray localJSONArray = new JSONArray();
      if (paramContext != null)
      {
        int i = 0;
        for (;;)
        {
          if (i < paramContext.size())
          {
            JSONObject localJSONObject = new JSONObject();
            try
            {
              localJSONObject.put("name", ((DeviceRegistryData)paramContext.get(i)).deviceFixedName);
              localJSONObject.put("deviceId", ((DeviceRegistryData)paramContext.get(i)).deviceBtID);
              localJSONArray.put(i, localJSONObject);
              i += 1;
            }
            catch (JSONException localJSONException)
            {
              for (;;)
              {
                localJSONException.printStackTrace();
              }
            }
          }
        }
      }
      return localJSONArray.toString();
    }
    return null;
  }
  
  public static String getAppNameToShow(String paramString, int paramInt)
  {
    com.samsung.android.hostmanager.pm.log.Log.d(TAG, "getAppNameToShow [" + paramString + "]");
    String str = paramString;
    if (paramString == null) {
      str = "gear app";
    }
    paramString = str;
    if (str.length() > paramInt)
    {
      paramString = str.substring(0, paramInt);
      paramString = paramString + "..";
    }
    com.samsung.android.hostmanager.pm.log.Log.d(TAG, "getAppNameToShow res [" + paramString + "]");
    return paramString;
  }
  
  public static String getBTName(String paramString)
  {
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (localBluetoothAdapter != null)
    {
      if (localBluetoothAdapter.isEnabled())
      {
        String str = "Bluetooth";
        try
        {
          paramString = localBluetoothAdapter.getRemoteDevice(paramString).getName();
          Log.d(TAG, "getBTName = " + paramString);
          return getSimpleBTName(paramString);
        }
        catch (IllegalArgumentException paramString)
        {
          for (;;)
          {
            paramString.printStackTrace();
            paramString = str;
          }
        }
      }
      paramString = HMApplication.getAppContext().getSharedPreferences("bnr_deviceinfo", 0).getString("MODEL_NAME", "");
      Log.d(TAG, "mBluetoothAdapter is disable !!!! get BTname from DeviceInfo.xml. device name = " + paramString);
      return getSimpleBTName(paramString);
    }
    Log.d(TAG, "mBluetoothAdapter is null !!!!");
    return null;
  }
  
  public static Bitmap getBitmapFromBytearray(byte[] paramArrayOfByte)
  {
    return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static String getCSC(Context paramContext)
  {
    Log.d(TAG, "getCSC()");
    paramContext = null;
    if (!isCSCExistFile()) {}
    for (;;)
    {
      Object localObject = paramContext;
      if (TextUtils.isEmpty(paramContext))
      {
        if ((!isSamsungDevice()) || (Build.VERSION.SDK_INT < 24)) {
          break;
        }
        localObject = SemSystemProperties.getSalesCode();
      }
      return localObject;
      localObject = getCSCVersion();
      if (localObject == null) {
        Log.d(TAG, "getCSC()-->getCSCVersion() :: value is null.");
      } else if (((String)localObject).equalsIgnoreCase("FAIL")) {
        Log.d(TAG, "getCSC()-->getCSCVersion() ::Failed to read CSC Version.");
      } else {
        paramContext = ((String)localObject).substring(0, 3);
      }
    }
    return SystemPropertyFactory.getAndroidSystemProperty().get("ro.csc.sales_code");
  }
  
  /* Error */
  public static String getCSCVersion()
  {
    // Byte code:
    //   0: getstatic 68	com/samsung/android/hostmanager/utils/CommonUtils:TAG	Ljava/lang/String;
    //   3: ldc_w 867
    //   6: invokestatic 349	com/samsung/android/hostmanager/utils/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   9: pop
    //   10: aconst_null
    //   11: astore_3
    //   12: new 83	java/io/File
    //   15: dup
    //   16: ldc 11
    //   18: invokespecial 870	java/io/File:<init>	(Ljava/lang/String;)V
    //   21: astore_1
    //   22: aload_3
    //   23: astore_2
    //   24: aload_1
    //   25: invokevirtual 873	java/io/File:isFile	()Z
    //   28: ifeq +59 -> 87
    //   31: bipush 20
    //   33: newarray byte
    //   35: astore 5
    //   37: aconst_null
    //   38: astore 4
    //   40: aconst_null
    //   41: astore_0
    //   42: aconst_null
    //   43: astore_2
    //   44: new 651	java/io/FileInputStream
    //   47: dup
    //   48: aload_1
    //   49: invokespecial 654	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   52: astore_1
    //   53: aload_1
    //   54: aload 5
    //   56: invokevirtual 879	java/io/InputStream:read	([B)I
    //   59: ifeq +30 -> 89
    //   62: new 99	java/lang/String
    //   65: dup
    //   66: aload 5
    //   68: invokestatic 885	java/nio/charset/Charset:defaultCharset	()Ljava/nio/charset/Charset;
    //   71: invokespecial 888	java/lang/String:<init>	([BLjava/nio/charset/Charset;)V
    //   74: astore_0
    //   75: aload_0
    //   76: astore_2
    //   77: aload_1
    //   78: ifnull +9 -> 87
    //   81: aload_1
    //   82: invokevirtual 889	java/io/InputStream:close	()V
    //   85: aload_0
    //   86: astore_2
    //   87: aload_2
    //   88: areturn
    //   89: ldc_w 858
    //   92: astore_0
    //   93: goto -18 -> 75
    //   96: astore_1
    //   97: aload_1
    //   98: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   101: goto -16 -> 85
    //   104: astore_0
    //   105: aload_2
    //   106: astore_1
    //   107: aload_0
    //   108: astore_2
    //   109: aload_1
    //   110: astore_0
    //   111: aload_2
    //   112: invokevirtual 890	java/io/FileNotFoundException:printStackTrace	()V
    //   115: aload_3
    //   116: astore_2
    //   117: aload_1
    //   118: ifnull -31 -> 87
    //   121: aload_1
    //   122: invokevirtual 889	java/io/InputStream:close	()V
    //   125: aconst_null
    //   126: areturn
    //   127: astore_0
    //   128: aload_0
    //   129: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   132: goto -7 -> 125
    //   135: astore_2
    //   136: aload 4
    //   138: astore_1
    //   139: aload_1
    //   140: astore_0
    //   141: aload_2
    //   142: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   145: aload_3
    //   146: astore_2
    //   147: aload_1
    //   148: ifnull -61 -> 87
    //   151: aload_1
    //   152: invokevirtual 889	java/io/InputStream:close	()V
    //   155: aconst_null
    //   156: areturn
    //   157: astore_0
    //   158: aload_0
    //   159: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   162: goto -7 -> 155
    //   165: astore_1
    //   166: aload_0
    //   167: ifnull +7 -> 174
    //   170: aload_0
    //   171: invokevirtual 889	java/io/InputStream:close	()V
    //   174: aload_1
    //   175: athrow
    //   176: astore_0
    //   177: aload_0
    //   178: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   181: goto -7 -> 174
    //   184: astore_2
    //   185: aload_1
    //   186: astore_0
    //   187: aload_2
    //   188: astore_1
    //   189: goto -23 -> 166
    //   192: astore_2
    //   193: goto -54 -> 139
    //   196: astore_2
    //   197: goto -88 -> 109
    // Local variable table:
    //   start	length	slot	name	signature
    //   41	52	0	str	String
    //   104	4	0	localFileNotFoundException1	FileNotFoundException
    //   110	1	0	localObject1	Object
    //   127	2	0	localIOException1	IOException
    //   140	1	0	localObject2	Object
    //   157	14	0	localIOException2	IOException
    //   176	2	0	localIOException3	IOException
    //   186	1	0	localObject3	Object
    //   21	61	1	localObject4	Object
    //   96	2	1	localIOException4	IOException
    //   106	46	1	localObject5	Object
    //   165	21	1	localObject6	Object
    //   188	1	1	localObject7	Object
    //   23	94	2	localObject8	Object
    //   135	7	2	localIOException5	IOException
    //   146	1	2	localObject9	Object
    //   184	4	2	localObject10	Object
    //   192	1	2	localIOException6	IOException
    //   196	1	2	localFileNotFoundException2	FileNotFoundException
    //   11	135	3	localObject11	Object
    //   38	99	4	localObject12	Object
    //   35	32	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   81	85	96	java/io/IOException
    //   44	53	104	java/io/FileNotFoundException
    //   121	125	127	java/io/IOException
    //   44	53	135	java/io/IOException
    //   151	155	157	java/io/IOException
    //   44	53	165	finally
    //   111	115	165	finally
    //   141	145	165	finally
    //   170	174	176	java/io/IOException
    //   53	75	184	finally
    //   53	75	192	java/io/IOException
    //   53	75	196	java/io/FileNotFoundException
  }
  
  public static ArrayList<String> getCompanionAppInitList()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = HMApplication.getAppContext().getPackageManager();
    Object localObject1 = null;
    try
    {
      localObject2 = ((PackageManager)localObject2).getInstalledPackages(4096);
      localObject1 = localObject2;
      Log.d(TAG, "isCompanionApp packageInfo is not NULL");
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        int i;
        int j;
        Log.d(TAG, "isCompanionApp packageInfo not found");
        localException.printStackTrace();
        continue;
        j += 1;
      }
      Log.d(TAG, "getCompanionAppInitList Instlled App info list is null");
    }
    if (localObject1 != null)
    {
      i = 0;
      for (;;)
      {
        if (i >= localObject1.size()) {
          break label204;
        }
        Object localObject3 = (PackageInfo)localObject1.get(i);
        if ((localObject3 != null) && (((PackageInfo)localObject3).requestedPermissions != null))
        {
          localObject2 = ((PackageInfo)localObject3).packageName;
          localObject3 = ((PackageInfo)localObject3).requestedPermissions;
          int k = localObject3.length;
          j = 0;
          if (j < k)
          {
            if (!localObject3[j].equals("com.samsung.WATCH_APP_TYPE.Companion")) {
              break;
            }
            Log.d(TAG, "companion app : " + (String)localObject2);
            localArrayList.add(localObject2);
          }
        }
        i += 1;
      }
    }
    label204:
    return localArrayList;
  }
  
  public static String getConncectedBTAliasName(String paramString)
  {
    Log.d(TAG, "getBTAliasName() : BTAddress = " + paramString);
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if ((localBluetoothAdapter != null) && (localBluetoothAdapter.isEnabled()))
    {
      paramString = localBluetoothAdapter.getRemoteDevice(paramString);
      paramString = BluetoothDeviceFactory.get().getAliasName(paramString);
      android.util.Log.d(TAG, "btAliasName = " + paramString);
      return paramString;
    }
    return null;
  }
  
  public static boolean getConnectivityStatus(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    boolean bool1 = false;
    boolean bool2 = false;
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.getType() == 1)
      {
        Log.d(TAG, "getConnectivityStatus :: wifi");
        bool1 = true;
      }
      if (paramContext.getType() == 0)
      {
        Log.d(TAG, "getConnectivityStatus :: mobile");
        bool1 = true;
      }
      if (paramContext.getType() == 7)
      {
        Log.d(TAG, "getConnectivityStatus :: bluetooth");
        bool1 = true;
      }
    }
    Log.d(TAG, "getConnectivityStatus :: none, res [" + bool1 + "]");
    return bool1;
  }
  
  public static String getCurrentDATEFORMAT(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 23)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramContext = DateFormat.getDateFormatOrder(paramContext);
      int i = 0;
      while (i < paramContext.length)
      {
        if (paramContext[i] == 'y') {
          localStringBuilder.append("yyyy");
        }
        if (paramContext[i] == 'M') {
          localStringBuilder.append("MM");
        }
        if (paramContext[i] == 'd') {
          localStringBuilder.append("dd");
        }
        if (i != paramContext.length - 1) {
          localStringBuilder.append("-");
        }
        i += 1;
      }
      return localStringBuilder.toString();
    }
    return getDateFormatOrder();
  }
  
  @TargetApi(18)
  public static String getDateFormatOrder()
  {
    return getDateFormatOrder(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMMdd"));
  }
  
  private static String getDateFormatOrder(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int n = 0;
    int m = 0;
    int k = 0;
    int i = 0;
    if (i < paramString.length())
    {
      char c = paramString.charAt(i);
      int j;
      int i1;
      int i2;
      int i3;
      if ((c == 'd') || (c == 'L') || (c == 'M') || (c == 'y')) {
        if ((c == 'd') && (n == 0))
        {
          localStringBuilder.append("dd");
          n = 1;
          if ((1 != 0) && (m != 0))
          {
            j = i;
            i1 = n;
            i2 = m;
            i3 = k;
            if (k != 0) {}
          }
          else
          {
            localStringBuilder.append("-");
            i3 = k;
            i2 = m;
            i1 = n;
            j = i;
          }
        }
      }
      for (;;)
      {
        i = j + 1;
        n = i1;
        m = i2;
        k = i3;
        break;
        if (((c == 'L') || (c == 'M')) && (m == 0))
        {
          localStringBuilder.append("MM");
          m = 1;
          if ((n != 0) && (1 != 0))
          {
            j = i;
            i1 = n;
            i2 = m;
            i3 = k;
            if (k != 0) {}
          }
          else
          {
            localStringBuilder.append("-");
            j = i;
            i1 = n;
            i2 = m;
            i3 = k;
          }
        }
        else
        {
          j = i;
          i1 = n;
          i2 = m;
          i3 = k;
          if (c == 'y')
          {
            j = i;
            i1 = n;
            i2 = m;
            i3 = k;
            if (k == 0)
            {
              localStringBuilder.append("yyyy");
              k = 1;
              if ((n != 0) && (m != 0))
              {
                j = i;
                i1 = n;
                i2 = m;
                i3 = k;
                if (1 != 0) {}
              }
              else
              {
                localStringBuilder.append("-");
                j = i;
                i1 = n;
                i2 = m;
                i3 = k;
                continue;
                j = i;
                i1 = n;
                i2 = m;
                i3 = k;
                if (c != 'G')
                {
                  if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'))) {
                    throw new IllegalArgumentException("Bad pattern character '" + c + "' in " + paramString);
                  }
                  j = i;
                  i1 = n;
                  i2 = m;
                  i3 = k;
                  if (c == '\'') {
                    if ((i < paramString.length() - 1) && (paramString.charAt(i + 1) == '\''))
                    {
                      j = i + 1;
                      i1 = n;
                      i2 = m;
                      i3 = k;
                    }
                    else
                    {
                      i = paramString.indexOf('\'', i + 1);
                      if (i == -1) {
                        throw new IllegalArgumentException("Bad quoting in " + paramString);
                      }
                      j = i + 1;
                      i1 = n;
                      i2 = m;
                      i3 = k;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String getDomainUrlForWebStore()
  {
    String str2 = "gearapps.samsung.com";
    File localFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test_web_store");
    Log.d(TAG, " getLocalUrlParameters() testWebStore.exists(): " + localFile.exists());
    String str1 = str2;
    if (localFile.exists())
    {
      localFile = new File(Environment.getExternalStorageDirectory() + File.separator + PARAMETERS_FILE_LOCAL_PATH);
      Log.d(TAG, "getLocalUrlParameters() localParametersFile.exists()" + localFile.exists());
      str1 = str2;
      if (localFile.exists()) {
        str1 = checkForValidUrl(processLinebyLine(localFile, "getDomainURL"));
      }
    }
    return str1;
  }
  
  public static String getGearModelName()
  {
    Log.d(TAG, "getGearModelName");
    String str = WearableDeviceFactory.getInstance().getConnectedDeviceIdByType("Gear");
    str = PrefUtils.getPreferenceWithFilename(HMApplication.getAppContext(), str, "bnr_wearableinfoforsamsungapps", "MODELNAME");
    Log.d(TAG, "getGearModelName: Model Name:" + str);
    return str;
  }
  
  public static String getGearOSVersion(String paramString)
  {
    Log.d(TAG, "getGearOSVersion()");
    try
    {
      Object localObject1 = WearableDeviceFactory.getInstance().getDeviceManager(paramString).getStatusManager().getWearableStatus();
      if (localObject1 != null)
      {
        localObject1 = ((DeviceInfo)localObject1).getDevicePlatformVersion();
        Log.d(TAG, "Gear OS version for device ( " + paramString + " ) is " + (String)localObject1);
      }
      for (paramString = (String)localObject1;; paramString = HMApplication.getAppContext().getSharedPreferences("bnr_wearableinfoforsamsungapps", 0).getString("gearOSVersion", ""))
      {
        Log.d(TAG, "Gear OS Version for device is " + paramString);
        return paramString;
        Log.d(TAG, "DeviceInfo for device ( " + paramString + " ) is null.");
      }
      throw new NullPointerException();
    }
    catch (DeviceNotSupportedException localDeviceNotSupportedException)
    {
      localDeviceNotSupportedException.printStackTrace();
      throw new NullPointerException();
      for (;;)
      {
        Log.d(TAG, "Gear OS Version for device is " + paramString);
        return paramString;
        Log.d(TAG, "DeviceInfo for device ( " + paramString + " ) is null.");
        paramString = HMApplication.getAppContext().getSharedPreferences("bnr_wearableinfoforsamsungapps", 0).getString("gearOSVersion", "");
      }
    }
    finally
    {
      if (0 == 0) {}
    }
    for (;;)
    {
      Log.d(TAG, "Gear OS Version for device is " + paramString);
      return paramString;
      Log.d(TAG, "DeviceInfo for device ( " + paramString + " ) is null.");
      paramString = HMApplication.getAppContext().getSharedPreferences("bnr_wearableinfoforsamsungapps", 0).getString("gearOSVersion", "");
    }
  }
  
  public static String getHMVersion()
  {
    try
    {
      String str = HMApplication.getAppContext().getPackageManager().getPackageInfo(HMApplication.getAppContext().getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      return "none";
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "none";
  }
  
  public static String getIMEI(Context paramContext)
  {
    try
    {
      ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      return "";
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String getLabelPackage(Context paramContext, String paramString)
  {
    String str = "";
    Object localObject = str;
    if (paramContext != null)
    {
      localObject = str;
      try
      {
        Log.d(TAG, "isExistPackage:" + paramString);
        localObject = str;
        paramContext = paramContext.getPackageManager();
        if (paramContext != null)
        {
          localObject = str;
          paramContext = (String)paramContext.getApplicationLabel(paramContext.getApplicationInfo(paramString, 0));
          localObject = paramContext;
          Log.d(TAG, paramString + " exist!!");
          localObject = paramContext;
        }
        else
        {
          localObject = str;
          Log.e(TAG, "package manager is null");
          localObject = str;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        return localObject;
      }
    }
    return localObject;
  }
  
  public static String getMCC(Context paramContext)
  {
    Log.d(TAG, "getMCC()");
    String str = "";
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getApplicationContext().getSystemService("phone");
    paramContext = str;
    if (localTelephonyManager != null) {
      paramContext = getMCC(localTelephonyManager.getSimOperator());
    }
    return paramContext;
  }
  
  public static String getMCC(String paramString)
  {
    Log.d(TAG, "getMCC(), networkOperator : " + paramString);
    String str2 = "";
    String str1 = str2;
    if (paramString != null)
    {
      str1 = str2;
      if (paramString.length() >= 3) {
        str1 = paramString.substring(0, 3);
      }
    }
    return str1;
  }
  
  public static String getMCCFromTestconfig()
  {
    Object localObject2 = null;
    File localFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test_web_store");
    Log.d(TAG, " getLocalUrlParameters() testWebStore.exists(): " + localFile.exists());
    Object localObject1 = localObject2;
    if (localFile.exists())
    {
      localFile = new File(Environment.getExternalStorageDirectory() + File.separator + PARAMETERS_FILE_LOCAL_PATH);
      Log.d(TAG, "getLocalUrlParameters() localParametersFile.exists()" + localFile.exists());
      localObject1 = localObject2;
      if (localFile.exists()) {
        localObject1 = processLinebyLine(localFile, "mcc");
      }
    }
    return localObject1;
  }
  
  public static String getMNC(Context paramContext)
  {
    Log.d(TAG, "getMNC()");
    String str = "00";
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    paramContext = str;
    if (localTelephonyManager != null) {
      paramContext = getMNC(localTelephonyManager.getSimOperator());
    }
    return paramContext;
  }
  
  public static String getMNC(String paramString)
  {
    Log.d(TAG, "getMNC() - networkOperator : " + paramString);
    String str2 = "00";
    String str1 = str2;
    if (paramString != null)
    {
      str1 = str2;
      if (paramString.length() > 3) {
        str1 = paramString.substring(3);
      }
    }
    return str1;
  }
  
  public static String getModelName()
  {
    String str = Build.MODEL;
    if ("OMAP_SS".equals(str)) {
      return readModelCMCC();
    }
    return str.replaceFirst("SAMSUNG-", "");
  }
  
  public static String getOriginalBTName(String paramString)
  {
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (localBluetoothAdapter != null)
    {
      if (localBluetoothAdapter.isEnabled())
      {
        String str = "Bluetooth";
        try
        {
          paramString = localBluetoothAdapter.getRemoteDevice(paramString).getName();
          Log.d(TAG, "getBTName = " + paramString);
          return paramString;
        }
        catch (IllegalArgumentException paramString)
        {
          for (;;)
          {
            paramString.printStackTrace();
            paramString = str;
          }
        }
      }
      paramString = HMApplication.getAppContext().getSharedPreferences("bnr_deviceinfo", 0).getString("MODEL_NAME", "");
      Log.d(TAG, "mBluetoothAdapter is disable !!!! get BTname from DeviceInfo.xml. device name = " + paramString);
      return paramString;
    }
    Log.d(TAG, "mBluetoothAdapter is null !!!!");
    return null;
  }
  
  public static IPackageManager getPackageManager(String paramString)
  {
    Object localObject = null;
    try
    {
      DeviceManager localDeviceManager = WearableDeviceFactory.getInstance().getDeviceManager(paramString);
      paramString = localObject;
      if (localDeviceManager != null) {
        paramString = localDeviceManager.getPackageManager();
      }
      return paramString;
    }
    catch (DeviceNotSupportedException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String getProgressString(String paramString1, String paramString2)
  {
    return String.format("%s/%s", new Object[] { paramString1, paramString2 });
  }
  
  public static String getRetailPackageName()
  {
    String str1 = "";
    String str2 = WearableDeviceFactory.getInstance().getConnectedDeviceIdByType("Gear");
    if (ClockUtils.isSolis(str2)) {
      str1 = "com.samsung.retailmode_s3";
    }
    while (!ClockUtils.isGearSport(str2)) {
      return str1;
    }
    return "com.samsung.retailmode_sport";
  }
  
  public static String getSalesCode()
  {
    Object localObject = "";
    try
    {
      String str1 = SystemPropertyFactory.getAndroidSystemProperty().get("ro.csc.sales_code");
      String str2 = str1;
      localObject = str1;
      if (TextUtils.isEmpty(str1))
      {
        localObject = str1;
        str2 = SystemPropertyFactory.getAndroidSystemProperty().get("ril.sales_code");
      }
      return str2;
    }
    catch (Exception localException) {}
    return localObject;
  }
  
  public static String getSimpleBTName(String paramString)
  {
    if (paramString == null) {
      str = null;
    }
    do
    {
      return str;
      Log.d(TAG, "getSimpleBTName BTName = " + paramString);
      str = paramString;
    } while (!paramString.contains("("));
    String str = null;
    int i = paramString.indexOf("(");
    if (i != -1)
    {
      str = paramString.substring(0, i - 1);
      Log.d(TAG, "getSimpleBTName simpleName = " + str);
    }
    return str;
  }
  
  public static String getStringSize(long paramLong)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("0.00");
    float f1 = 1024.0F * 1024.0F;
    float f2 = f1 * 1024.0F;
    String str1 = String.valueOf(0);
    String str2 = "KB";
    if ((float)paramLong < f1)
    {
      str1 = localDecimalFormat.format((float)paramLong / 1024.0F);
      str2 = "KB";
    }
    for (;;)
    {
      return str1 + str2;
      if ((float)paramLong < f2)
      {
        str1 = localDecimalFormat.format((float)paramLong / f1);
        str2 = "MB";
      }
      else if ((float)paramLong < f2 * 1024.0F)
      {
        str1 = localDecimalFormat.format((float)paramLong / f2);
        str2 = "GB";
      }
    }
  }
  
  public static String getSupportFeatureByDeviceInfo(DeviceInfo paramDeviceInfo, String paramString)
  {
    Object localObject = null;
    if (paramDeviceInfo != null)
    {
      HashMap localHashMap = paramDeviceInfo.getDeviceFeature();
      paramDeviceInfo = localObject;
      if (localHashMap != null) {
        paramDeviceInfo = (String)localHashMap.get(paramString);
      }
      return paramDeviceInfo;
    }
    Log.e(TAG, "ST::getSupportFeatureByDeviceInfo deviceInfo is null");
    return null;
  }
  
  public static int getUsingMobileNetworkStatus(Context paramContext)
  {
    Object localObject = Uri.parse("content://" + "com.samsung.android.samsungpay.gear.ExternalProvider" + "/STAY_CONNECTED_HIPRI_STATUS_GET");
    paramContext = paramContext.getContentResolver();
    try
    {
      paramContext = paramContext.insert((Uri)localObject, new ContentValues());
      if (paramContext != null)
      {
        localObject = paramContext.getPathSegments();
        paramContext = "";
        try
        {
          localObject = (String)((List)localObject).get(1);
          paramContext = (Context)localObject;
        }
        catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
        {
          for (;;)
          {
            localIndexOutOfBoundsException.printStackTrace();
          }
        }
        Log.d(TAG, "STATUS is : " + paramContext);
        if (paramContext.equals("2")) {
          return 2;
        }
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      Log.e(TAG, paramContext.toString());
      return -2;
      if (paramContext.equals("1")) {
        return 1;
      }
      if (paramContext.equals("0"))
      {
        return 0;
        Log.d(TAG, "uri == null");
        return -1;
      }
    }
    catch (Exception paramContext)
    {
      Log.e(TAG, paramContext.toString());
      return -1;
    }
    return -1;
  }
  
  public static int getUsingModileNetworkStatus()
  {
    int i = getUsingMobileNetworkStatus(HMApplication.getAppContext());
    Log.d(TAG, "getUsingModileNetworkStatus()::settingValue = " + i);
    if (2 == i) {
      return 1;
    }
    return 0;
  }
  
  public static String getWGTOnlyVersion(String paramString, Context paramContext)
  {
    Log.d(TAG, "getWGTOnlyVersion(" + paramString + ")");
    Object localObject2 = null;
    String str = PrefUtils.getPreferenceWithFilename(paramContext, WearableDeviceFactory.getInstance().getConnectedDeviceIdByType("Gear"), "bnr_wearableinfoforsamsungapps", "MODELNAME");
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (!paramString.isEmpty())
      {
        localObject1 = paramString.trim();
        paramString = paramContext;
        if (paramContext == null) {
          paramString = HMApplication.getAppContext();
        }
        if (paramString == null) {
          break label160;
        }
        paramString = paramString.getSharedPreferences(str + "_" + "bnr_installed_wgt_only_app", 0);
      }
    }
    try
    {
      localObject1 = paramString.getString((String)localObject1 + "_appVersion", null);
      return localObject1;
    }
    catch (ClassCastException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    label160:
    Log.d(TAG, "HMApplication context is null.");
    return null;
  }
  
  public static Locale handleUnsupportableLocaleUpdate(Locale paramLocale)
  {
    Log.d(TAG, "handleUnsupportableLocaleUpdate : " + paramLocale);
    if (Build.VERSION.SDK_INT < 24) {
      return paramLocale;
    }
    Locale localLocale1 = new Locale.Builder().setLanguage("zh").setScript("Hans").setRegion("SG").build();
    Locale localLocale2 = new Locale.Builder().setLanguage("zh").setScript("Hans").setRegion("MO").build();
    Locale localLocale3 = new Locale.Builder().setLanguage("zh").setScript("Hans").setRegion("CN").build();
    Locale localLocale4 = new Locale.Builder().setLanguage("zh").setRegion("CN").build();
    Locale localLocale5 = new Locale.Builder().setLanguage("zh").setScript("Hant").setRegion("MO").build();
    Locale localLocale6 = new Locale.Builder().setLanguage("zh").setScript("Hans").setRegion("HK").build();
    Locale localLocale7 = new Locale.Builder().setLanguage("zh").setScript("Hant").setRegion("HK").build();
    Locale localLocale8 = new Locale.Builder().setLanguage("zh").setRegion("HK").build();
    Locale localLocale9 = new Locale.Builder().setLanguage("zh").setScript("Hant").setRegion("TW").build();
    Locale localLocale10 = new Locale.Builder().setLanguage("zh").setRegion("TW").build();
    Locale localLocale11 = new Locale.Builder().setLanguage("es").setRegion("MX").build();
    Locale localLocale12 = new Locale.Builder().setLanguage("es").setRegion("US").build();
    Locale[][] arrayOfLocale; = new Locale[8][];
    arrayOfLocale;[0] = { localLocale1, localLocale4 };
    arrayOfLocale;[1] = { localLocale2, localLocale4 };
    arrayOfLocale;[2] = { localLocale3, localLocale4 };
    arrayOfLocale;[3] = { localLocale5, localLocale8 };
    arrayOfLocale;[4] = { localLocale6, localLocale8 };
    arrayOfLocale;[5] = { localLocale7, localLocale8 };
    arrayOfLocale;[6] = { localLocale9, localLocale10 };
    arrayOfLocale;[7] = { localLocale11, localLocale12 };
    localLocale1 = paramLocale;
    int i = 0;
    for (;;)
    {
      localLocale2 = localLocale1;
      if (i < arrayOfLocale;.length)
      {
        if (paramLocale.equals(arrayOfLocale;[i][0])) {
          localLocale2 = arrayOfLocale;[i][1];
        }
      }
      else {
        return localLocale2;
      }
      i += 1;
    }
  }
  
  private static boolean hasInstallPermission(Context paramContext)
  {
    int i = paramContext.checkCallingOrSelfPermission("android.permission.INSTALL_PACKAGES");
    Log.d(TAG, "hasInstallPermission() res:" + i);
    return i == 0;
  }
  
  public static boolean hasTwoSim()
  {
    if (!TextUtils.isEmpty(get2SimValue())) {}
    for (boolean bool = true;; bool = false)
    {
      Log.d(TAG, "hasTwoSim() - hasTwoSim : " + bool);
      return bool;
    }
  }
  
  public static boolean isActivityRunning(Context paramContext, String paramString)
  {
    Log.d(TAG, "isActivityRunning()::activityName = " + paramString);
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1024);
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)paramContext.next();
      if (localRunningTaskInfo == null) {
        break;
      }
      try
      {
        Log.d(TAG, "isActivityRunning()::topActivity = " + localRunningTaskInfo.topActivity.getClassName());
        if (localRunningTaskInfo.topActivity.getClassName().contains(paramString))
        {
          Log.d(TAG, "roman.roman::FOUND");
          return true;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean isAdminUser(Context paramContext)
  {
    boolean bool2 = false;
    UserHandle localUserHandle = Process.myUserHandle();
    paramContext = (UserManager)paramContext.getSystemService("user");
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      long l = paramContext.getSerialNumberForUser(localUserHandle);
      Log.d(TAG, "userSerialNumber = " + l);
      bool1 = bool2;
      if (0L == l) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isAvailableSamsungAppsVersion(Context paramContext, int paramInt)
  {
    bool2 = false;
    if (paramContext == null)
    {
      Log.e(TAG, "isAvailableSamsungAppsVersion, context is null");
      return false;
    }
    bool1 = bool2;
    if (isSamsungDevice())
    {
      paramContext = paramContext.getPackageManager();
      bool1 = bool2;
      if (paramContext == null) {}
    }
    for (;;)
    {
      try
      {
        int i = paramContext.getPackageInfo("com.sec.android.app.samsungapps", 128).versionCode;
        Log.d(TAG, "isAvailableSamsungAppsVersion, versionCode [" + i + "]");
        if (i < paramInt) {
          continue;
        }
        bool1 = true;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        bool1 = bool2;
        continue;
      }
      return bool1;
      bool1 = false;
    }
  }
  
  public static boolean isCSCExistFile()
  {
    Log.d(TAG, "isCSCExistFile()");
    boolean bool1 = false;
    File localFile = new File("/system/csc/sales_code.dat");
    try
    {
      boolean bool2 = localFile.exists();
      if (!bool2)
      {
        bool1 = bool2;
        Log.d(TAG, "CSC doesn't exist.");
      }
      return bool2;
    }
    catch (Exception localException)
    {
      Log.d(TAG, "isCSCExistFile() :: " + localException.getMessage());
    }
    return bool1;
  }
  
  public static boolean isChinaModel()
  {
    String str = SystemPropertyFactory.getAndroidSystemProperty().get("ro.csc.sales_code");
    return (str != null) && (("CHZ".equals(str)) || ("CHN".equals(str)) || ("CHM".equals(str)) || ("CHU".equals(str)) || ("CTC".equals(str)) || ("CHC".equals(str)));
  }
  
  public static boolean isCompanionApp(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = null;
    try
    {
      paramString = localPackageManager.getPackageInfo(paramString, 4096);
      paramContext = paramString;
      Log.d(TAG, "isCompanionApp packageInfo is not NULL");
      paramContext = paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        boolean bool1;
        int j;
        int i;
        Log.d(TAG, "isCompanionApp packageInfo not found");
        paramString.printStackTrace();
        continue;
        i += 1;
      }
    }
    Log.d(TAG, "isCompanionApp packageInfo = " + paramContext);
    bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.requestedPermissions != null)
      {
        paramContext = paramContext.requestedPermissions;
        j = paramContext.length;
        i = 0;
        bool1 = bool2;
        if (i < j)
        {
          paramString = paramContext[i];
          Log.d(TAG, "isCompanionApp reqPermission = " + paramString);
          if (!paramString.equals("com.samsung.WATCH_APP_TYPE.Companion")) {
            break label165;
          }
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean isConsumerOfCompanion(Context paramContext, String paramString1, String paramString2)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = null;
    try
    {
      paramString2 = localPackageManager.getApplicationInfo(paramString2, 128);
      paramContext = paramString2;
    }
    catch (PackageManager.NameNotFoundException paramString2)
    {
      for (;;)
      {
        Log.d(TAG, "isConsumerOfCompanion : app not found!");
        continue;
        paramContext = null;
      }
      label91:
      Log.d(TAG, "isConsumerOfCompanion : metadata is null");
    }
    if (paramContext != null)
    {
      paramContext = paramContext.metaData;
      if (paramContext == null) {
        break label91;
      }
      paramContext = paramContext.getString("gear_app_packagename");
      Log.d(TAG, "isConsumerOfCompanion : wgtPackageName:" + paramContext);
      return paramContext.equals(paramString1);
    }
    return false;
  }
  
  public static boolean isDeveloperMode(Context paramContext)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = paramContext.createPackageContext("com.samsung.android.app.watchmanager", 4).getSharedPreferences("pref_developer_mode", 0).getBoolean("developer_mode", false);
      bool1 = bool2;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    Log.d(TAG, "isDevloperModeEnabled() result:" + bool1);
    return bool1;
  }
  
  public static int isDisabledSamsungApps(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getApplicationEnabledSetting("com.sec.android.app.samsungapps");
      Log.d(TAG, "onItemClick menu_samsungapps, isDisabled-->" + i);
      if ((i == 2) || (i == 3)) {
        return 0;
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      return 2;
    }
    catch (ActivityNotFoundException paramContext)
    {
      return 2;
    }
    return 1;
  }
  
  public static boolean isEmailPackageNameChanged(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      if (Build.VERSION.SDK_INT >= 23) {
        paramContext.getApplicationInfo("com.samsung.android.email.provider", 128);
      } else {
        paramContext.getApplicationInfo("com.samsung.android.email.ui", 128);
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      return false;
    }
    return true;
  }
  
  public static boolean isEngBuild()
  {
    return Build.TYPE.equals("eng");
  }
  
  public static boolean isExistPackage(Context paramContext, String paramString)
  {
    boolean bool = false;
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null) {
      try
      {
        Log.d(TAG, "isExistPackage:" + paramString);
        paramContext = paramContext.getPackageManager();
        if (paramContext != null)
        {
          localObject1 = paramContext.getPackageInfo(paramString, 128);
          Log.d(TAG, paramString + " exist!!");
        }
        else
        {
          Log.e(TAG, "package manager is null");
          localObject1 = localObject2;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        return false;
      }
    }
    if (localObject1 != null) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isInstallFromPlaystore(Context paramContext)
  {
    if ((!hasInstallPermission(paramContext)) && (isExistPackage(paramContext, "com.android.vending"))) {}
    for (boolean bool = true;; bool = false)
    {
      Log.d(TAG, "isInstallFromPlaystore() res:" + bool);
      return bool;
    }
  }
  
  public static boolean isInstalledApplication(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager();
    boolean bool1 = bool2;
    if (paramContext != null) {}
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 128);
      bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = paramContext.packageName.equals(paramString);
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.i(TAG, "Package Name Not Found : " + paramString);
      return false;
    }
    catch (Exception paramContext)
    {
      Log.e(TAG, "Package Name Not Found : " + paramString + " / Exception = " + paramContext);
    }
    return false;
  }
  
  public static boolean isLogOutCondition(Context paramContext)
  {
    if (android.provider.Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) == 1)
    {
      Log.d(TAG, "SCS::isLogOutCondition(), airplane mode is on.");
      return true;
    }
    return false;
  }
  
  public static boolean isMassModel()
  {
    boolean bool = true;
    int i;
    String str;
    if (!isMassModelCheckedBefore)
    {
      isMassModelCheckedBefore = true;
      i = BluetoothUtil.haveAPInSupportBLE();
      if (1 == i)
      {
        Log.d(TAG, "HAVE_API_SUPPORT - isMassModel : false");
        bool = false;
        return bool;
      }
      if (2 == i)
      {
        Log.d(TAG, "HAVE_API_NOT_SUPPORT - isMassModel : true");
        isMassModel = true;
        return true;
      }
      str = Build.MODEL.replaceFirst("SAMSUNG-", "");
      Log.d(TAG, "This model's Names is " + str);
      i = 0;
    }
    for (;;)
    {
      if (i < MASS_LIST.length)
      {
        if (str.contains(MASS_LIST[i]))
        {
          Log.d(TAG, "***This is MassModel****");
          isMassModel = true;
        }
      }
      else
      {
        Log.d(TAG, "We already check List of MASS");
        if ((isSamsungDevice()) && (isMassModel)) {
          break;
        }
        return false;
      }
      i += 1;
    }
  }
  
  public static boolean isMassModelforPlugin()
  {
    String str;
    int i;
    if (!isMassModelCheckedBeforeforPlugin)
    {
      isMassModelCheckedBeforeforPlugin = true;
      str = Build.MODEL.replaceFirst("SAMSUNG-", "");
      Log.d(TAG, "isMassModelforPlugin This model's Names is " + str);
      i = 0;
    }
    for (;;)
    {
      if (i < MASS_LIST.length)
      {
        if (str.contains(MASS_LIST[i]))
        {
          Log.d(TAG, "isMassModelforPlugin ***This is MassModel****");
          isMassModelforPlugin = true;
        }
      }
      else
      {
        Log.d(TAG, "isMassModelforPlugin We already check List of MASS");
        if ((!isSamsungDevice()) || (!isMassModelforPlugin)) {
          break;
        }
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isMessageSyncSupported()
  {
    Log.d(TAG, "isMessageSyncSupported()");
    String str = WearableDeviceFactory.getInstance().getConnectedDeviceIdByType("Gear");
    str = getSupportFeatureByDeviceInfo(IUHostManager.getInstance().getWearableStatus(str), "support.quickmessage.sync");
    Log.d(TAG, "ST::isSupportFeatureWearable::feature=[support.quickmessage.sync] value=[" + str + "]");
    return "true".equalsIgnoreCase(str);
  }
  
  public static boolean isNotSupportedScanFilterModel()
  {
    String str;
    int i;
    if (!isNotSupportedScanFilterModelCheckedBefore)
    {
      isNotSupportedScanFilterModelCheckedBefore = true;
      str = Build.MODEL.replaceFirst("SAMSUNG-", "");
      Log.d(TAG, "This model's Names is " + str);
      i = 0;
    }
    for (;;)
    {
      if (i < NOT_SUPPORTED_SCAN_FILTER_MODEL_LIST.length)
      {
        if (str.contains(NOT_SUPPORTED_SCAN_FILTER_MODEL_LIST[i]))
        {
          Log.d(TAG, "***This is not supported scan filter model****");
          isNotSupportedScanFilterModel = true;
        }
      }
      else
      {
        Log.d(TAG, "We already check List of NOT_SUPPORTED_SCAN_FILTER_MODEL : " + isNotSupportedScanFilterModel);
        return isNotSupportedScanFilterModel;
      }
      i += 1;
    }
  }
  
  public static boolean isPaired(String paramString)
  {
    Object localObject = BluetoothAdapter.getDefaultAdapter();
    Log.d(TAG, "isPairder(), deviceID = " + paramString);
    if (!((BluetoothAdapter)localObject).isEnabled())
    {
      Log.d(TAG, "btAdapter is turned off...");
      return true;
    }
    localObject = ((BluetoothAdapter)localObject).getBondedDevices();
    if ((localObject != null) && (((Set)localObject).size() > 0))
    {
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        BluetoothDevice localBluetoothDevice = (BluetoothDevice)((Iterator)localObject).next();
        Log.d(TAG, "isPairder(), founded deviceID = " + localBluetoothDevice.getAddress());
        if (localBluetoothDevice.getAddress().equals(paramString)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean isProcessRunning(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {}
    for (;;)
    {
      return false;
      paramContext = paramContext.iterator();
      label31:
      if ((paramContext != null) && (paramContext.hasNext()))
      {
        Object localObject = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localObject != null) {
          try
          {
            localObject = ((ActivityManager.RunningAppProcessInfo)localObject).processName;
            if (localObject != null)
            {
              Log.i(TAG, "CM::info = " + (String)localObject);
              boolean bool = ((String)localObject).equals(paramString);
              if (!bool) {
                break label31;
              }
              return true;
            }
          }
          catch (Exception paramContext)
          {
            paramContext.printStackTrace();
          }
        }
      }
    }
    return false;
  }
  
  public static int isSamsungConnectPPAgreed(Context paramContext)
  {
    Uri localUri = Uri.parse("content://com.samsung.android.oneconnect.external/exsettings");
    int i = -1;
    paramContext = paramContext.getContentResolver().query(localUri, new String[] { "settings_key", "settings_value" }, "settings_key=?", new String[] { "pp_agreed_enabled" }, null);
    int j = i;
    if (paramContext != null)
    {
      if (paramContext.getCount() > 0)
      {
        paramContext.moveToFirst();
        i = paramContext.getInt(paramContext.getColumnIndex("settings_value"));
      }
      paramContext.close();
      j = i;
    }
    Log.d(TAG, "isSamsungConnectPPAgreed() = " + j);
    return j;
  }
  
  public static boolean isSamsungDevice()
  {
    if (isSamsungGEDModel()) {
      return false;
    }
    return Build.MANUFACTURER.equalsIgnoreCase("SAMSUNG");
  }
  
  public static boolean isSamsungGEDModel()
  {
    String str = Build.MODEL;
    if ((str != null) && ((str.contains("SM-G900FG")) || (str.contains("GT-I9505G"))))
    {
      Log.d("TKCHO", "SamsungGEDModel : " + str);
      return true;
    }
    return false;
  }
  
  public static boolean isSamsungMembersInstalled(Context paramContext)
  {
    Boolean localBoolean1 = Boolean.valueOf(false);
    if (isSamsungDevice())
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null)
      {
        Boolean localBoolean2 = localBoolean1;
        paramContext = localBoolean1;
        try
        {
          if (localPackageManager.getPackageInfo("com.samsung.android.voc", 128) != null) {
            localBoolean2 = Boolean.valueOf(true);
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          paramContext = localBoolean1;
          localBoolean1 = Boolean.valueOf(false);
          paramContext = localBoolean1;
          localNameNotFoundException.printStackTrace();
        }
        finally
        {
          return paramContext.booleanValue();
        }
      }
    }
    return localObject.booleanValue();
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext == null) {
      return false;
    }
    if (paramContext.getRunningServices(10000) != null) {
      paramContext = paramContext.getRunningServices(10000).iterator();
    }
    Object localObject;
    label75:
    do
    {
      if (paramContext.hasNext())
      {
        localObject = (ActivityManager.RunningServiceInfo)paramContext.next();
        if (localObject != null) {
          break label75;
        }
      }
      do
      {
        do
        {
          Log.d(TAG, "iu::Can not find HostManager service!");
          return false;
          localObject = ((ActivityManager.RunningServiceInfo)localObject).service;
        } while (localObject == null);
        localObject = ((ComponentName)localObject).getClassName();
      } while (localObject == null);
    } while (!((String)localObject).equals(paramString));
    Log.d(TAG, "iu::HostManager service is alived!");
    return true;
  }
  
  public static boolean isSignedIn(Context paramContext)
  {
    paramContext = getAccountArray(paramContext, "com.osp.app.signin");
    if ((paramContext != null) && (paramContext.length > 0)) {
      return true;
    }
    com.samsung.android.hostmanager.log.Log.e(TAG, "SCS::UI:: isSignedIn() signed Samsung Account doesn't exist, return false");
    return false;
  }
  
  public static boolean isSmartLockSupport(Context paramContext)
  {
    return android.provider.Settings.System.getInt(paramContext.getContentResolver(), "db_smartlock_supported", 0) == 1;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    if ((mDeviceType != null) && (mDeviceType.length() > 0)) {
      return mDeviceType.contains("tablet");
    }
    mDeviceType = SystemPropertyFactory.getAndroidSystemProperty().get("ro.build.characteristics");
    return (mDeviceType != null) && (mDeviceType.contains("tablet"));
  }
  
  public static boolean isTopActivity(Context paramContext, String paramString)
  {
    Log.d(TAG, "isTopActivity()");
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(2);
    if (paramContext != null)
    {
      int j = paramContext.size();
      int i = 0;
      while (i < j)
      {
        String str = ((ActivityManager.RunningTaskInfo)paramContext.get(i)).topActivity.getClassName();
        Log.d(TAG, "topActivity = " + str);
        if ((str != null) && (str.contains(paramString))) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static boolean isUltraPowerSavingMode()
  {
    return Integer.parseInt(HMApplication.getAppContext().getSharedPreferences("bnr_hm_shared_preference", 0).getString("ups_mode_status_pref", "0")) > 1;
  }
  
  public static boolean isWgtInApkApp(Context paramContext, String paramString)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    paramContext = paramContext.getPackageManager();
    bool2 = bool3;
    if (paramContext != null) {
      try
      {
        ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo(paramString, 128);
        bool2 = bool3;
        if (localApplicationInfo != null)
        {
          bool2 = bool3;
          if (localApplicationInfo.packageName.equals(paramString))
          {
            Log.d(TAG, "isWgtInApkApp packageInfo = " + localApplicationInfo);
            paramContext = paramContext.getPackageInfo(localApplicationInfo.packageName, 4096);
            bool2 = bool3;
            if (paramContext != null)
            {
              bool2 = bool3;
              if (paramContext.requestedPermissions != null)
              {
                paramContext = paramContext.requestedPermissions;
                int j = paramContext.length;
                int i = 0;
                for (;;)
                {
                  bool2 = bool1;
                  if (i >= j) {
                    break;
                  }
                  localApplicationInfo = paramContext[i];
                  Log.d(TAG, "isWgtInApkApp reqPermission = " + localApplicationInfo);
                  bool2 = localApplicationInfo.equals("com.samsung.wmanager.APP");
                  if (bool2) {
                    bool1 = true;
                  }
                  i += 1;
                }
              }
            }
          }
        }
        return bool2;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Log.i(TAG, "Package Name Not Found : " + paramString);
        return false;
      }
      catch (Exception paramContext)
      {
        Log.e(TAG, "Package Name Not Found : " + paramString + " / Exception = " + paramContext);
        return false;
      }
    }
  }
  
  public static boolean istUHMWithNewUpdateLogic(Context paramContext)
  {
    bool2 = false;
    if (paramContext == null)
    {
      Log.e(TAG, "istUHMWithNewUpdateLogic, context is null");
      return false;
    }
    paramContext = paramContext.getPackageManager();
    bool1 = bool2;
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        int i = paramContext.getPackageInfo("com.samsung.android.app.watchmanager", 128).versionCode;
        Log.d(TAG, "istUHMWithNewUpdateLogic, versionCode for tUHM [" + i + "]");
        if (i <= 2116021741) {
          continue;
        }
        bool1 = true;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        bool1 = bool2;
        continue;
      }
      Log.d(TAG, "istUHMWithNewUpdateLogic, result [" + bool1 + "]");
      return bool1;
      bool1 = false;
    }
  }
  
  public static void printPhoneNumber(String paramString1, String paramString2, String paramString3)
  {
    Object localObject1 = paramString3;
    Object localObject2 = localObject1;
    if (!DEBUGGABLE())
    {
      if (TextUtils.isEmpty(paramString3)) {
        break label113;
      }
      localObject1 = new StringBuffer();
      paramString3 = paramString3.toCharArray();
      int j = paramString3.length;
      int i = 0;
      while (i < j)
      {
        ((StringBuffer)localObject1).append((char)(paramString3[i] - '0' + 97));
        i += 1;
      }
    }
    for (localObject2 = ((StringBuffer)localObject1).toString();; localObject2 = localObject1)
    {
      Log.d(paramString1, paramString2 + ", phone [" + (String)localObject2 + "]");
      return;
      label113:
      Log.e(paramString1, paramString2 + ", can not convert phone because it's empty");
    }
  }
  
  /* Error */
  private static String processLinebyLine(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 1729	java/util/Scanner
    //   8: dup
    //   9: aload_0
    //   10: ldc_w 1731
    //   13: invokespecial 1734	java/util/Scanner:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   16: astore_0
    //   17: aload_0
    //   18: invokevirtual 1737	java/util/Scanner:hasNextLine	()Z
    //   21: ifeq +80 -> 101
    //   24: new 1729	java/util/Scanner
    //   27: dup
    //   28: aload_0
    //   29: invokevirtual 1740	java/util/Scanner:nextLine	()Ljava/lang/String;
    //   32: invokespecial 1741	java/util/Scanner:<init>	(Ljava/lang/String;)V
    //   35: astore 4
    //   37: aload 4
    //   39: ldc_w 1743
    //   42: invokevirtual 1747	java/util/Scanner:useDelimiter	(Ljava/lang/String;)Ljava/util/Scanner;
    //   45: pop
    //   46: aload 4
    //   48: invokevirtual 1748	java/util/Scanner:hasNext	()Z
    //   51: istore_2
    //   52: iload_2
    //   53: ifeq -36 -> 17
    //   56: aload 4
    //   58: invokevirtual 1750	java/util/Scanner:next	()Ljava/lang/String;
    //   61: astore_3
    //   62: aload 4
    //   64: invokevirtual 1750	java/util/Scanner:next	()Ljava/lang/String;
    //   67: astore 4
    //   69: aload_3
    //   70: aload_1
    //   71: invokevirtual 448	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   74: istore_2
    //   75: iload_2
    //   76: ifeq -59 -> 17
    //   79: aload_0
    //   80: ifnull +7 -> 87
    //   83: aload_0
    //   84: invokevirtual 1751	java/util/Scanner:close	()V
    //   87: aload 4
    //   89: areturn
    //   90: astore_1
    //   91: aload_0
    //   92: ifnull +7 -> 99
    //   95: aload_0
    //   96: invokevirtual 1751	java/util/Scanner:close	()V
    //   99: aconst_null
    //   100: areturn
    //   101: aload_0
    //   102: ifnull +53 -> 155
    //   105: aload_0
    //   106: invokevirtual 1751	java/util/Scanner:close	()V
    //   109: aconst_null
    //   110: areturn
    //   111: astore_1
    //   112: aload 4
    //   114: astore_0
    //   115: aload_0
    //   116: astore_3
    //   117: aload_1
    //   118: invokevirtual 890	java/io/FileNotFoundException:printStackTrace	()V
    //   121: aload_0
    //   122: ifnull -13 -> 109
    //   125: aload_0
    //   126: invokevirtual 1751	java/util/Scanner:close	()V
    //   129: goto -20 -> 109
    //   132: astore_0
    //   133: aload_3
    //   134: ifnull +7 -> 141
    //   137: aload_3
    //   138: invokevirtual 1751	java/util/Scanner:close	()V
    //   141: aload_0
    //   142: athrow
    //   143: astore_1
    //   144: aload_0
    //   145: astore_3
    //   146: aload_1
    //   147: astore_0
    //   148: goto -15 -> 133
    //   151: astore_1
    //   152: goto -37 -> 115
    //   155: goto -46 -> 109
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	158	0	paramFile	File
    //   0	158	1	paramString	String
    //   51	25	2	bool	boolean
    //   1	145	3	localObject1	Object
    //   3	110	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   56	75	90	java/util/NoSuchElementException
    //   5	17	111	java/io/FileNotFoundException
    //   5	17	132	finally
    //   117	121	132	finally
    //   17	52	143	finally
    //   56	75	143	finally
    //   17	52	151	java/io/FileNotFoundException
    //   56	75	151	java/io/FileNotFoundException
  }
  
  private static String readModelCMCC()
  {
    String str = "";
    int i = -1;
    Object localObject1 = new File("/system/version");
    Object localObject4 = str;
    Object localObject5;
    if (((File)localObject1).isFile())
    {
      localObject4 = new byte[''];
      localObject5 = null;
    }
    try
    {
      localObject1 = new FileInputStream((File)localObject1);
      localObject5 = localObject1;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      for (;;)
      {
        try
        {
          i = localObject5.read((byte[])localObject4);
          localObject1 = str;
          if (i > 0)
          {
            localObject3 = str;
            localObject1 = new String((byte[])localObject4, 0, i, Charset.defaultCharset());
          }
          if (localObject5 != null)
          {
            localObject3 = localObject1;
            localObject5.close();
          }
          localObject4 = localObject1;
        }
        catch (IOException localIOException1)
        {
          Object localObject3;
          Log.i(TAG, "Util::readModelCMCC::" + localIOException1.getMessage());
          localObject4 = localObject3;
          if (localObject5 == null) {
            continue;
          }
          try
          {
            localObject5.close();
            return localObject3;
          }
          catch (IOException localIOException2)
          {
            localIOException2.printStackTrace();
            return localObject3;
          }
        }
        finally
        {
          if (localObject5 == null) {
            break label203;
          }
        }
        try
        {
          localObject5.close();
          localObject4 = localObject1;
          return localObject4;
        }
        catch (IOException localIOException3)
        {
          localIOException3.printStackTrace();
          return localFileNotFoundException;
        }
        localFileNotFoundException = localFileNotFoundException;
        Log.i(TAG, "Util::readModelCMCC::File not found");
      }
    }
    if (localObject5 != null) {
      localObject3 = str;
    }
    try
    {
      localObject5.close();
      label203:
      throw localObject2;
    }
    catch (IOException localIOException4)
    {
      for (;;)
      {
        localIOException4.printStackTrace();
      }
    }
  }
  
  /* Error */
  public static boolean saveImageFile(Bitmap paramBitmap, String paramString, int paramInt)
  {
    // Byte code:
    //   0: getstatic 68	com/samsung/android/hostmanager/utils/CommonUtils:TAG	Ljava/lang/String;
    //   3: new 74	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   10: ldc_w 1765
    //   13: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: aload_1
    //   17: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   23: invokestatic 349	com/samsung/android/hostmanager/utils/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   26: pop
    //   27: aconst_null
    //   28: astore 7
    //   30: aconst_null
    //   31: astore 6
    //   33: iconst_0
    //   34: istore 4
    //   36: aload 7
    //   38: astore 5
    //   40: new 83	java/io/File
    //   43: dup
    //   44: aload_1
    //   45: iconst_0
    //   46: aload_1
    //   47: getstatic 86	java/io/File:separator	Ljava/lang/String;
    //   50: invokevirtual 1768	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   53: invokevirtual 790	java/lang/String:substring	(II)Ljava/lang/String;
    //   56: invokespecial 870	java/io/File:<init>	(Ljava/lang/String;)V
    //   59: astore 8
    //   61: aload 8
    //   63: ifnull +41 -> 104
    //   66: aload 7
    //   68: astore 5
    //   70: aload 8
    //   72: invokevirtual 1029	java/io/File:exists	()Z
    //   75: ifne +29 -> 104
    //   78: aload 7
    //   80: astore 5
    //   82: aload 8
    //   84: invokevirtual 1771	java/io/File:mkdirs	()Z
    //   87: ifne +17 -> 104
    //   90: aload 7
    //   92: astore 5
    //   94: getstatic 68	com/samsung/android/hostmanager/utils/CommonUtils:TAG	Ljava/lang/String;
    //   97: ldc_w 1773
    //   100: invokestatic 349	com/samsung/android/hostmanager/utils/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   103: pop
    //   104: aload 7
    //   106: astore 5
    //   108: new 1775	java/io/FileOutputStream
    //   111: dup
    //   112: aload_1
    //   113: invokespecial 1776	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   116: astore_1
    //   117: aload_0
    //   118: ifnull +19 -> 137
    //   121: iload_2
    //   122: iconst_2
    //   123: if_icmpne +40 -> 163
    //   126: aload_0
    //   127: getstatic 1782	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   130: bipush 100
    //   132: aload_1
    //   133: invokevirtual 1788	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   136: pop
    //   137: aload_1
    //   138: invokevirtual 1789	java/io/FileOutputStream:close	()V
    //   141: iconst_1
    //   142: istore_3
    //   143: aload_1
    //   144: ifnull +151 -> 295
    //   147: aload_1
    //   148: invokevirtual 1789	java/io/FileOutputStream:close	()V
    //   151: getstatic 68	com/samsung/android/hostmanager/utils/CommonUtils:TAG	Ljava/lang/String;
    //   154: ldc_w 1791
    //   157: invokestatic 349	com/samsung/android/hostmanager/utils/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   160: pop
    //   161: iload_3
    //   162: ireturn
    //   163: iload_2
    //   164: iconst_1
    //   165: if_icmpne +59 -> 224
    //   168: aload_0
    //   169: getstatic 1782	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   172: bipush 50
    //   174: aload_1
    //   175: invokevirtual 1788	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   178: pop
    //   179: goto -42 -> 137
    //   182: astore 5
    //   184: aload_1
    //   185: astore_0
    //   186: aload 5
    //   188: astore_1
    //   189: aload_0
    //   190: astore 5
    //   192: aload_1
    //   193: invokevirtual 913	java/lang/Exception:printStackTrace	()V
    //   196: iload 4
    //   198: istore_3
    //   199: aload_0
    //   200: ifnull -49 -> 151
    //   203: aload_0
    //   204: invokevirtual 1789	java/io/FileOutputStream:close	()V
    //   207: iload 4
    //   209: istore_3
    //   210: goto -59 -> 151
    //   213: astore_0
    //   214: aload_0
    //   215: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   218: iload 4
    //   220: istore_3
    //   221: goto -70 -> 151
    //   224: getstatic 68	com/samsung/android/hostmanager/utils/CommonUtils:TAG	Ljava/lang/String;
    //   227: new 74	java/lang/StringBuilder
    //   230: dup
    //   231: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   234: ldc_w 1793
    //   237: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: iload_2
    //   241: invokevirtual 304	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   244: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: invokestatic 349	com/samsung/android/hostmanager/utils/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   250: pop
    //   251: goto -114 -> 137
    //   254: astore_0
    //   255: aload_1
    //   256: ifnull +7 -> 263
    //   259: aload_1
    //   260: invokevirtual 1789	java/io/FileOutputStream:close	()V
    //   263: aload_0
    //   264: athrow
    //   265: astore_0
    //   266: aload_0
    //   267: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   270: goto -119 -> 151
    //   273: astore_1
    //   274: aload_1
    //   275: invokevirtual 426	java/io/IOException:printStackTrace	()V
    //   278: goto -15 -> 263
    //   281: astore_0
    //   282: aload 5
    //   284: astore_1
    //   285: goto -30 -> 255
    //   288: astore_1
    //   289: aload 6
    //   291: astore_0
    //   292: goto -103 -> 189
    //   295: goto -144 -> 151
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	298	0	paramBitmap	Bitmap
    //   0	298	1	paramString	String
    //   0	298	2	paramInt	int
    //   142	79	3	bool1	boolean
    //   34	185	4	bool2	boolean
    //   38	69	5	localObject1	Object
    //   182	5	5	localException	Exception
    //   190	93	5	localBitmap	Bitmap
    //   31	259	6	localObject2	Object
    //   28	77	7	localObject3	Object
    //   59	24	8	localFile	File
    // Exception table:
    //   from	to	target	type
    //   126	137	182	java/lang/Exception
    //   137	141	182	java/lang/Exception
    //   168	179	182	java/lang/Exception
    //   224	251	182	java/lang/Exception
    //   203	207	213	java/io/IOException
    //   126	137	254	finally
    //   137	141	254	finally
    //   168	179	254	finally
    //   224	251	254	finally
    //   147	151	265	java/io/IOException
    //   259	263	273	java/io/IOException
    //   40	61	281	finally
    //   70	78	281	finally
    //   82	90	281	finally
    //   94	104	281	finally
    //   108	117	281	finally
    //   192	196	281	finally
    //   40	61	288	java/lang/Exception
    //   70	78	288	java/lang/Exception
    //   82	90	288	java/lang/Exception
    //   94	104	288	java/lang/Exception
    //   108	117	288	java/lang/Exception
  }
  
  public static void showToast(String paramString)
  {
    if (0 != 0) {}
    try
    {
      if (DEBUGGABLE()) {
        Toast.makeText(HMApplication.getAppContext(), paramString, 1).show();
      }
      return;
    }
    catch (Exception paramString)
    {
      Log.v(TAG, paramString.toString());
    }
  }
  
  public static boolean validateBTAddress(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return false;
    }
    return BluetoothAdapter.checkBluetoothAddress(paramString);
  }
  
  public static void wakeUpScreen(Context paramContext)
  {
    Log.d(TAG, "wakeUpScreen!!");
    paramContext = (PowerManager)paramContext.getSystemService("power");
    if (paramContext != null)
    {
      paramContext.newWakeLock(268435482, HOSTMANAGER_TAG).acquire(5000L);
      return;
    }
    Log.d(TAG, "PowerManager instance is null");
  }
  
  public static void wakeUpScreenWithDismissKeyguard(Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.SCREEN_ON");
    HMApplication.getAppContext().registerReceiver(new CommonUtils.1(), localIntentFilter);
    wakeUpScreen(paramContext);
  }
  
  public static void writeSystemSettingString(Context paramContext, String paramString1, String paramString2)
  {
    com.samsung.android.hostmanager.log.Log.d(TAG, "writeSystemSettingString()::context = " + paramContext + ", key = " + paramString1 + ", value = " + paramString2);
    if (paramContext == null) {}
    do
    {
      do
      {
        return;
      } while ((paramString1 == null) || (paramString1.isEmpty()));
      if (Build.VERSION.SDK_INT < 23) {
        break;
      }
      com.samsung.android.hostmanager.provider.Settings.System.putString(paramContext, paramString1, paramString2);
    } while (!isSamsungDevice());
    try
    {
      android.provider.Settings.System.putString(paramContext.getContentResolver(), paramString1, paramString2);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      com.samsung.android.hostmanager.log.Log.d(TAG, "CM::write setting exeption = " + paramContext.toString());
      return;
    }
    android.provider.Settings.System.putString(paramContext.getContentResolver(), paramString1, paramString2);
  }
}
