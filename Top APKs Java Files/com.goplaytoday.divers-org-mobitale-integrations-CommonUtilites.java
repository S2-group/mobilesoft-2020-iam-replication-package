package org.mobitale.integrations;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtilites
{
  private static CommonUtilitesBaseDelegate mCommonUtilitesDelegate = null;
  private static ProgressDialogStorage progressDialogStorage = null;
  private static ProgressDialogStorage progressDialogStorageCancellable = null;
  public static final Object sProfileSaveDataLock = new Object();
  
  public CommonUtilites() {}
  
  public static void addLocalNotification(int paramInt1, String paramString1, String paramString2, int paramInt2, boolean paramBoolean)
  {
    NotificationIntegration.addLocalNotification(paramInt1, paramString1, paramString2, paramInt2, paramBoolean);
  }
  
  public static native void alertDialogQuestionResponse(int paramInt);
  
  public static native void alertDialogResponse();
  
  public static void cancelAllLocalNotifications() {}
  
  public static boolean checkAppSignatures()
  {
    int j = 0;
    localList = IntegrationConfig.getAllowedAppSignatures();
    if (localList.isEmpty()) {
      return true;
    }
    for (i = 0;; i = 0)
    {
      try
      {
        k = BaseIntegration.getApplicationContext().getApplicationInfo().flags;
        if ((k & 0x2) == 0) {
          continue;
        }
        i = 1;
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          int k;
          boolean bool1;
          Log.e("mygame", "checkAppSignature 'isDebuggable' error", localException2);
          continue;
          localObject2 = localException2[i];
          try
          {
            localMessageDigest = MessageDigest.getInstance("SHA");
            localMessageDigest.update(localObject2.toByteArray());
            bool2 = localList.contains(new String(Base64.encode(localMessageDigest.digest(), 2)));
            if (bool2) {
              bool1 = true;
            }
          }
          catch (Exception localException3)
          {
            Log.e("mygame", "checkAppSignature sub error", localException3);
            i += 1;
          }
        }
      }
      if (i != 0) {
        break;
      }
      bool1 = false;
      try
      {
        Object localObject1 = BaseIntegration.getApplicationContext();
        localObject1 = ((Context)localObject1).getPackageManager().getPackageInfo(((Context)localObject1).getPackageName(), 64).signatures;
        k = localObject1.length;
        i = j;
        if (i < k) {
          break label103;
        }
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          Object localObject2;
          MessageDigest localMessageDigest;
          boolean bool2;
          Log.e("mygame", "checkAppSignature error", localException1);
        }
      }
      return bool1;
    }
  }
  
  public static boolean checkInternetConnection()
  {
    Object localObject = BaseIntegration.getApplicationContext();
    boolean bool2 = false;
    try
    {
      localObject = (ConnectivityManager)((Context)localObject).getSystemService("connectivity");
      boolean bool1 = bool2;
      if (localObject != null)
      {
        bool1 = bool2;
        if (((ConnectivityManager)localObject).getActiveNetworkInfo() != null)
        {
          bool1 = bool2;
          if (((ConnectivityManager)localObject).getActiveNetworkInfo().isAvailable())
          {
            boolean bool3 = ((ConnectivityManager)localObject).getActiveNetworkInfo().isConnected();
            bool1 = bool2;
            if (bool3) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean checkInternetConnectionViaWiFi()
  {
    Object localObject = BaseIntegration.getApplicationContext();
    boolean bool2 = false;
    try
    {
      localObject = (ConnectivityManager)((Context)localObject).getSystemService("connectivity");
      boolean bool1 = bool2;
      if (localObject != null)
      {
        bool1 = bool2;
        if (((ConnectivityManager)localObject).getActiveNetworkInfo() != null)
        {
          bool1 = bool2;
          if (((ConnectivityManager)localObject).getActiveNetworkInfo().isAvailable())
          {
            bool1 = bool2;
            if (((ConnectivityManager)localObject).getActiveNetworkInfo().isConnected())
            {
              int i = ((ConnectivityManager)localObject).getActiveNetworkInfo().getType();
              bool1 = bool2;
              if (i == 1) {
                bool1 = true;
              }
            }
          }
        }
      }
      return bool1;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static void closeApplication()
  {
    BaseIntegration.getActivity().moveTaskToBack(true);
  }
  
  private static byte[] createChecksum(String paramString)
    throws Exception
  {
    paramString = new FileInputStream(paramString);
    byte[] arrayOfByte = new byte['Ѐ'];
    MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
    int i;
    do
    {
      i = paramString.read(arrayOfByte);
      if (i > 0) {
        localMessageDigest.update(arrayOfByte, 0, i);
      }
    } while (i != -1);
    paramString.close();
    return localMessageDigest.digest();
  }
  
  public static byte[] decryptString(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = AESCryptoHelper.decrypt(paramString2, paramString1).getBytes();
      return paramString2;
    }
    catch (Exception paramString2) {}
    return paramString1.getBytes();
  }
  
  public static void done()
  {
    if (progressDialogStorage != null) {}
    try
    {
      progressDialogStorage.progressDialog.hide();
      progressDialogStorage.progressDialog.dismiss();
      progressDialogStorage.progressDialog = null;
      progressDialogStorage = null;
      if (progressDialogStorageCancellable == null) {}
    }
    catch (Exception localException1)
    {
      try
      {
        progressDialogStorageCancellable.progressDialog.hide();
        progressDialogStorageCancellable.progressDialog.dismiss();
        progressDialogStorageCancellable.progressDialog = null;
        progressDialogStorageCancellable = null;
        return;
        localException1 = localException1;
        Log.e("mygame", localException1.toString());
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          Log.e("mygame", localException2.toString());
        }
      }
    }
  }
  
  public static byte[] encryptString(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = AESCryptoHelper.encrypt(paramString2, paramString1).getBytes();
      byte[] arrayOfByte = new byte[paramString2.length + 1];
      System.arraycopy(paramString2, 0, arrayOfByte, 0, paramString2.length);
      arrayOfByte[paramString2.length] = 0;
      return arrayOfByte;
    }
    catch (Exception paramString2) {}
    return paramString1.getBytes();
  }
  
  static String getAPKExpansionFile(int paramInt)
  {
    if (mCommonUtilitesDelegate != null) {
      return mCommonUtilitesDelegate.getAPKExpansionFile(paramInt);
    }
    return "";
  }
  
  static String getAPKExpansionPatchFile(int paramInt)
  {
    if (mCommonUtilitesDelegate != null) {
      return mCommonUtilitesDelegate.getAPKExpansionPatchFile(paramInt);
    }
    return "";
  }
  
  public static String getAccountIds()
  {
    if (mCommonUtilitesDelegate != null)
    {
      str1 = mCommonUtilitesDelegate.getCustomPrimaryAccountId();
      if (str1 != null) {
        return str1;
      }
    }
    String str1 = "";
    Object localObject1 = str1;
    int j;
    int i;
    do
    {
      try
      {
        localObject2 = AccountManager.get(BaseIntegration.getApplicationContext());
        localObject1 = str1;
        if (localObject2 == null) {
          break;
        }
        localObject1 = str1;
        arrayOfAccount = ((AccountManager)localObject2).getAccountsByType("com.google");
        localObject1 = str1;
        j = arrayOfAccount.length;
        i = 0;
      }
      catch (Exception localException)
      {
        Object localObject2;
        Account[] arrayOfAccount;
        String str2;
        localException.printStackTrace();
        break;
      }
      localObject1 = str1;
      str2 = arrayOfAccount[i].name;
      localObject2 = str1;
      localObject1 = str1;
      if (str1.length() != 0)
      {
        localObject1 = str1;
        localObject2 = str1 + " ";
      }
      localObject1 = localObject2;
      str1 = localObject2 + str2;
      i += 1;
    } while (i < j);
    localObject1 = localException;
    return localObject1;
  }
  
  public static String getApkPath()
  {
    String str = "";
    try
    {
      Object localObject1 = BaseIntegration.getApplicationContext();
      Object localObject2 = ((Context)localObject1).getPackageName();
      PackageManager localPackageManager = ((Context)localObject1).getPackageManager();
      localObject1 = str;
      if (localPackageManager != null)
      {
        localObject2 = localPackageManager.getApplicationInfo((String)localObject2, 0);
        localObject1 = str;
        if (localObject2 != null) {
          localObject1 = ((ApplicationInfo)localObject2).sourceDir;
        }
      }
      return localObject1;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static String getAppVersion()
  {
    Object localObject = BaseIntegration.getApplicationContext();
    try
    {
      localObject = ((Context)localObject).getPackageManager().getPackageInfo(((Context)localObject).getPackageName(), 0);
      return ((PackageInfo)localObject).versionName;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
      return "Error: " + localNameNotFoundException.getMessage();
    }
  }
  
  public static String getCacheDir()
  {
    try
    {
      String str = BaseIntegration.getApplicationContext().getCacheDir().getAbsolutePath();
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static String getCountryCode()
  {
    Object localObject = BaseIntegration.getApplicationContext();
    if (localObject == null) {
      return "US";
    }
    try
    {
      localObject = ((Context)localObject).getResources().getConfiguration().locale.getCountry();
      return localObject;
    }
    catch (Exception localException) {}
    return "US";
  }
  
  public static CommonUtilitesBaseDelegate getDelegate()
  {
    return mCommonUtilitesDelegate;
  }
  
  public static String getDeviceId()
  {
    Object localObject5 = BaseIntegration.getApplicationContext();
    Object localObject1 = null;
    Object localObject3 = null;
    i = 1;
    int k = 1;
    j = i;
    Object localObject2 = localObject1;
    for (;;)
    {
      try
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)((Context)localObject5).getSystemService("phone");
        j = k;
        localObject2 = localObject3;
        if (localTelephonyManager != null)
        {
          j = i;
          localObject2 = localObject1;
          localObject1 = localTelephonyManager.getDeviceId();
          if (localObject1 == null) {
            continue;
          }
          j = i;
          localObject2 = localObject1;
          if (((String)localObject1).length() == 0) {
            continue;
          }
          j = i;
          localObject2 = localObject1;
          if (((String)localObject1).equals("000000000000000")) {
            continue;
          }
          j = i;
          localObject2 = localObject1;
          if (((String)localObject1).equals("0")) {
            continue;
          }
          j = i;
          localObject2 = localObject1;
          if (((String)localObject1).equals("unknown")) {
            continue;
          }
          i = 0;
          j = i;
          localObject2 = localObject1;
          if (i == 0)
          {
            j = i;
            localObject2 = localObject1;
            localObject1 = "sim" + (String)localObject1;
            localObject2 = localObject1;
            j = i;
          }
        }
      }
      catch (Exception localException2)
      {
        localObject1 = localObject2;
        Log.e("mygame", localException2.toString());
        continue;
      }
      i = j;
      localObject3 = localObject2;
      if (j != 0)
      {
        i = j;
        localObject3 = localObject2;
        localObject1 = localObject2;
      }
      try
      {
        if (Build.VERSION.SDK_INT < 9) {
          continue;
        }
        localObject1 = localObject2;
        localObject2 = new CUHardwareUtils().getSerial();
        if (localObject2 != null) {
          break label586;
        }
        j = 1;
      }
      catch (Exception localException1)
      {
        boolean bool;
        continue;
        j = 0;
        continue;
        i = 1;
        Object localObject4 = localException1;
        continue;
        i = 1;
        continue;
        i = 1;
        continue;
      }
      i = j;
      localObject3 = localObject2;
      if (j == 0)
      {
        localObject1 = localObject2;
        if (((String)localObject2).length() == 0) {
          break label591;
        }
        localObject1 = localObject2;
        if (((String)localObject2).equals("000000000000000")) {
          break label591;
        }
        localObject1 = localObject2;
        if (((String)localObject2).equals("0")) {
          break label591;
        }
        localObject1 = localObject2;
        bool = ((String)localObject2).equals("unknown");
        if (bool) {
          break label591;
        }
        i = 0;
        localObject3 = localObject2;
      }
      j = i;
      localObject1 = localObject3;
      if (i != 0)
      {
        k = i;
        localObject2 = localObject3;
      }
      try
      {
        localObject5 = (WifiManager)((Context)localObject5).getSystemService("wifi");
        j = i;
        localObject1 = localObject3;
        if (localObject5 != null)
        {
          j = i;
          localObject1 = localObject3;
          k = i;
          localObject2 = localObject3;
          if (((WifiManager)localObject5).getConnectionInfo() != null)
          {
            k = i;
            localObject2 = localObject3;
            localObject3 = ((WifiManager)localObject5).getConnectionInfo().getMacAddress();
            if (localObject3 == null) {
              break label600;
            }
            k = i;
            localObject2 = localObject3;
            if (((String)localObject3).equals("")) {
              break label600;
            }
            i = 0;
            j = i;
            localObject1 = localObject3;
            if (i == 0)
            {
              k = i;
              localObject2 = localObject3;
              localObject1 = "mac" + (String)localObject3;
              j = i;
            }
          }
        }
      }
      catch (Exception localException3)
      {
        localObject1 = localObject2;
        Log.e("mygame", localException3.toString());
        j = k;
        localObject1 = localObject2;
        continue;
      }
      if (j != 0)
      {
        localObject2 = "android_id";
        if ("android_id" == null) {
          break label605;
        }
      }
      try
      {
        if ("android_id".equals("")) {
          break label605;
        }
        i = 0;
        localObject1 = localObject2;
        if (i == 0) {
          localObject1 = "and" + "android_id";
        }
      }
      catch (Exception localException4)
      {
        localObject1 = localObject2;
        Log.e("mygame", localException4.toString());
        localObject1 = localObject2;
        continue;
      }
      if (localObject1 == null) {
        break;
      }
      return localObject1;
      i = 1;
    }
    return "";
  }
  
  public static String getDeviceModel()
  {
    String str2 = Build.MODEL;
    String str1 = str2;
    if (str2.length() == 0) {
      str1 = "Unknown Device";
    }
    return str1;
  }
  
  public static String getExternalFilesStoragePath()
  {
    Object localObject = BaseIntegration.getApplicationContext();
    String str = "";
    try
    {
      localObject = ((Context)localObject).getExternalFilesDir(null);
      if (localObject != null) {
        str = ((File)localObject).getAbsolutePath();
      }
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static String getInternalFilesStoragePath()
  {
    return getInternalFilesStoragePathForContext(BaseIntegration.getApplicationContext());
  }
  
  public static String getInternalFilesStoragePathForContext(Context paramContext)
  {
    Object localObject1 = "";
    try
    {
      localObject2 = paramContext.getFilesDir();
      if (localObject2 != null) {
        localObject1 = ((File)localObject2).getAbsolutePath();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2;
        String str = "";
        continue;
        str = "/data";
      }
    }
    localObject2 = localObject1;
    if (((String)localObject1).equals(""))
    {
      localObject1 = Environment.getDataDirectory();
      if (localObject1 != null)
      {
        localObject1 = ((File)localObject1).getAbsolutePath();
        paramContext = paramContext.getPackageName();
        localObject2 = localObject1 + "/data/" + paramContext + "/files";
      }
    }
    else
    {
      return localObject2;
    }
  }
  
  public static native String getLocalizableString(String paramString);
  
  public static String getMD5Checksum(String paramString)
  {
    String str = "";
    try
    {
      byte[] arrayOfByte = createChecksum(paramString);
      int i = 0;
      paramString = str;
      for (;;)
      {
        if (i >= arrayOfByte.length) {
          return paramString;
        }
        paramString = paramString + Integer.toString((arrayOfByte[i] & 0xFF) + 256, 16).substring(1);
        i += 1;
      }
      return "";
    }
    catch (Exception paramString) {}
  }
  
  public static String getMD5StringChecksum(String paramString)
  {
    String str = "";
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      int i = 0;
      paramString = str;
      for (;;)
      {
        if (i >= localObject.length) {
          return paramString;
        }
        paramString = paramString + Integer.toString((localObject[i] & 0xFF) + 256, 16).substring(1);
        i += 1;
      }
      return "";
    }
    catch (Exception paramString) {}
  }
  
  public static String getOSVersion()
  {
    String str2 = Build.VERSION.RELEASE;
    String str1 = str2;
    if (str2.length() == 0) {
      str1 = "Unknown Device";
    }
    return str1;
  }
  
  public static String getPrimaryAccountId()
  {
    Object localObject1;
    if (mCommonUtilitesDelegate != null)
    {
      localObject1 = mCommonUtilitesDelegate.getCustomPrimaryAccountId();
      if (localObject1 != null) {
        return localObject1;
      }
    }
    String str = "";
    int j;
    int i;
    do
    {
      try
      {
        localObject1 = BaseIntegration.getApplicationContext();
        localPattern = Patterns.EMAIL_ADDRESS;
        localObject3 = AccountManager.get((Context)localObject1);
        localObject1 = str;
        if (localObject3 == null) {
          break;
        }
        localObject3 = ((AccountManager)localObject3).getAccountsByType("com.google");
        j = localObject3.length;
        i = 0;
      }
      catch (Exception localException)
      {
        Pattern localPattern;
        Object localObject3;
        boolean bool;
        localException.printStackTrace();
        localObject2 = str;
        break;
      }
      localObject1 = localObject3[i].name;
      bool = localPattern.matcher((CharSequence)localObject1).matches();
      if (bool) {
        break;
      }
      i += 1;
    } while (i < j);
    Object localObject2 = str;
    return localObject2;
  }
  
  public static float getScreenDencity()
  {
    try
    {
      float f = BaseIntegration.getApplicationContext().getResources().getDisplayMetrics().density;
      return f;
    }
    catch (Exception localException) {}
    return 1.0F;
  }
  
  public static double getSecondsFromNowToMonday()
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(7);
    if (i != 7) {}
    for (;;)
    {
      return 172800 - (86400 * i + localCalendar.get(11) * 3600 + localCalendar.get(12) * 60 + localCalendar.get(13));
      i = 0;
    }
  }
  
  public static double getSecondsFromNowToSaturday()
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(7);
    if (i != 1) {}
    for (;;)
    {
      return 604800 - (86400 * i + localCalendar.get(11) * 3600 + localCalendar.get(12) * 60 + localCalendar.get(13));
      i = 8;
    }
  }
  
  public static int getVersionCode()
  {
    int i = 0;
    Object localObject = BaseIntegration.getApplicationContext();
    try
    {
      localObject = ((Context)localObject).getPackageManager().getPackageInfo(((Context)localObject).getPackageName(), 0);
      if (localObject != null) {
        i = ((PackageInfo)localObject).versionCode;
      }
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return 0;
  }
  
  public static String getVersionName()
  {
    Object localObject = BaseIntegration.getApplicationContext();
    if (localObject == null) {
      return "1.0";
    }
    try
    {
      localObject = ((Context)localObject).getPackageManager().getPackageInfo(((Context)localObject).getPackageName(), 0).versionName;
      return localObject;
    }
    catch (Exception localException) {}
    return "1.0";
  }
  
  public static void hideCancellableProgressDailog()
  {
    hideProgressDailog(progressDialogStorageCancellable);
  }
  
  public static void hideFAQPage() {}
  
  public static void hideFullScreenProgressDailog()
  {
    hideProgressDailog(progressDialogStorage);
  }
  
  private static void hideProgressDailog(ProgressDialogStorage paramProgressDialogStorage)
  {
    if (paramProgressDialogStorage != null) {
      BaseIntegration.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          try
          {
            if (CommonUtilites.ProgressDialogStorage.access$2(CommonUtilites.this) != null) {
              CommonUtilites.ProgressDialogStorage.access$2(CommonUtilites.this).hide();
            }
            return;
          }
          catch (Exception localException)
          {
            Log.e("mygame", localException.toString());
          }
        }
      });
    }
  }
  
  public static void hideSmallProgressDailog() {}
  
  public static void init()
  {
    Context localContext = BaseIntegration.getContext();
    Activity localActivity = BaseIntegration.getActivity();
    try
    {
      progressDialogStorage = new ProgressDialogStorage(null);
      progressDialogStorage.progressDialog = new ProgressDialog(localContext);
      progressDialogStorage.progressDialog.setOwnerActivity(localActivity);
      progressDialogStorage.progressDialog.setCancelable(false);
      progressDialogStorage.progressDialog.setIndeterminate(true);
      progressDialogStorage.progressDialog.setMessage("Loading...");
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        try
        {
          progressDialogStorageCancellable = new ProgressDialogStorage(null);
          progressDialogStorageCancellable.progressDialog = new ProgressDialog(localContext);
          progressDialogStorageCancellable.progressDialog.setOwnerActivity(localActivity);
          progressDialogStorageCancellable.progressDialog.setCancelable(true);
          progressDialogStorageCancellable.progressDialog.setIndeterminate(true);
          progressDialogStorageCancellable.progressDialog.setMessage("Please wait...");
          return;
        }
        catch (Exception localException1)
        {
          Log.e("mygame", localException1.toString());
        }
        localException2 = localException2;
        Log.e("mygame", localException2.toString());
      }
    }
  }
  
  public static boolean isAppInatalledToInternalStorage()
  {
    Object localObject2 = BaseIntegration.getApplicationContext();
    Object localObject1 = ((Context)localObject2).getPackageName();
    try
    {
      localObject2 = ((Context)localObject2).getPackageManager();
      if (localObject2 != null)
      {
        localObject1 = ((PackageManager)localObject2).getApplicationInfo((String)localObject1, 0);
        if (localObject1 != null)
        {
          localObject1 = ((ApplicationInfo)localObject1).sourceDir;
          localObject2 = Environment.getDataDirectory();
          if (localObject2 != null)
          {
            localObject2 = ((File)localObject2).getAbsolutePath();
            return ((String)localObject2).equals(((String)localObject1).substring(0, ((String)localObject2).length()));
          }
          if (!isExternalStorageAvailable()) {
            break label96;
          }
        }
        else
        {
          if (!isExternalStorageAvailable()) {
            break label100;
          }
          break label98;
        }
      }
      else
      {
        boolean bool = isExternalStorageAvailable();
        return !bool;
      }
    }
    catch (Exception localException)
    {
      return true;
    }
    return false;
    label96:
    return true;
    label98:
    return false;
    label100:
    return true;
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    Object localObject = BaseIntegration.getApplicationContext();
    try
    {
      localObject = ((Context)localObject).getPackageManager().getInstalledApplications(128).iterator();
      boolean bool;
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          return false;
        }
        bool = ((ApplicationInfo)((Iterator)localObject).next()).packageName.equals(paramString);
      } while (!bool);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isDeviceRooted()
  {
    try
    {
      String str = Build.TAGS;
      if (str == null) {
        break label23;
      }
      bool = str.contains("test-keys");
      if (!bool) {
        break label23;
      }
    }
    catch (Exception localException)
    {
      label23:
      int j;
      int i;
      do
      {
        for (;;)
        {
          try
          {
            arrayOfString = new String[9];
            arrayOfString[0] = "/system/app/Superuser.apk";
            arrayOfString[1] = "/sbin/su";
            arrayOfString[2] = "/system/bin/su";
            arrayOfString[3] = "/system/xbin/su";
            arrayOfString[4] = "/data/local/xbin/su";
            arrayOfString[5] = "/data/local/bin/su";
            arrayOfString[6] = "/system/sd/xbin/su";
            arrayOfString[7] = "/system/bin/failsafe/su";
            arrayOfString[8] = "/data/local/su";
            j = arrayOfString.length;
            i = 0;
          }
          catch (Throwable localThrowable)
          {
            boolean bool;
            String[] arrayOfString;
            break label128;
          }
          bool = new File(arrayOfString[i]).exists();
          if (!bool) {
            i += 1;
          }
        }
      } while (i < j);
    }
    return true;
    label128:
    return false;
  }
  
  public static boolean isDeviceSimulator()
  {
    try
    {
      boolean bool = Build.BRAND.equalsIgnoreCase("generic");
      if (bool) {
        return true;
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean isExternalStorageAvailable()
  {
    boolean bool1 = false;
    try
    {
      String str = Environment.getExternalStorageState();
      if (!"mounted".equals(str))
      {
        boolean bool2 = "mounted_ro".equals(str);
        if (!bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception localException) {}
    return false;
  }
  
  public static boolean isExternalStorageWritable()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  public static boolean isLicenseAccepted()
  {
    Object localObject = BaseIntegration.getApplicationContext();
    if (localObject != null)
    {
      localObject = ((Context)localObject).getSharedPreferences("LicenseAgreement", 0);
      if (!((SharedPreferences)localObject).getBoolean("accepted", false))
      {
        localObject = ((SharedPreferences)localObject).edit();
        ((SharedPreferences.Editor)localObject).putBoolean("accepted", true);
        ((SharedPreferences.Editor)localObject).commit();
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  public static void memoryProbe()
  {
    System.gc();
    System.gc();
    Runtime localRuntime = Runtime.getRuntime();
    double d1 = Debug.getNativeHeapAllocatedSize() / 1048576.0D;
    double d2 = Debug.getNativeHeapSize() / 1048576.0D;
    double d3 = Debug.getNativeHeapFreeSize() / 1048576.0D;
    long l1 = localRuntime.maxMemory();
    long l2 = localRuntime.totalMemory();
    long l3 = localRuntime.freeMemory();
    Log.d("mygame", "allocated: " + Double.valueOf(d1).toString());
    Log.d("mygame", "available: " + Double.valueOf(d2).toString());
    Log.d("mygame", "free: " + Double.valueOf(d3).toString());
    Log.d("mygame", "maxMemory: " + Long.toString(l1));
    Log.d("mygame", "totalMemory: " + Long.toString(l2));
    Log.d("mygame", "freeMemory: " + Long.toString(l3));
  }
  
  private static native boolean nativeSynchronizedLoad();
  
  private static native void nativeSynchronizedSave();
  
  public static void notifyDataChanged() {}
  
  public static boolean openUrl(String paramString)
  {
    Context localContext = BaseIntegration.getContext();
    try
    {
      localContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static void relaunchApplication()
  {
    Context localContext = BaseIntegration.getApplicationContext();
    Object localObject = localContext.getPackageManager().getLaunchIntentForPackage(localContext.getPackageName());
    ((Intent)localObject).addFlags(67108864);
    localObject = PendingIntent.getActivity(localContext, 197438, (Intent)localObject, 268435456);
    ((AlarmManager)localContext.getSystemService("alarm")).set(1, System.currentTimeMillis() + 100L, (PendingIntent)localObject);
    System.exit(0);
  }
  
  public static void saveScreenshotToPhotoAlbum(final int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    if (paramArrayOfInt.length != paramInt1 * paramInt2) {
      return;
    }
    paramArrayOfInt = Bitmap.createBitmap(paramArrayOfInt, 0, paramInt1, paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    Activity localActivity = BaseIntegration.getActivity();
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          MediaStore.Images.Media.insertImage(CommonUtilites.this.getContentResolver(), paramArrayOfInt, "", "");
          return;
        }
        catch (Exception localException)
        {
          Log.e("mygame", localException.toString());
        }
      }
    });
  }
  
  public static void setDelegate(CommonUtilitesBaseDelegate paramCommonUtilitesBaseDelegate)
  {
    mCommonUtilitesDelegate = paramCommonUtilitesBaseDelegate;
  }
  
  public static void setKeepScreenOn(boolean paramBoolean)
  {
    final Activity localActivity = BaseIntegration.getActivity();
    if (localActivity == null) {
      return;
    }
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          if (this.val$b)
          {
            localActivity.getWindow().addFlags(128);
            return;
          }
          localActivity.getWindow().clearFlags(128);
          return;
        }
        catch (Exception localException)
        {
          Log.e("mygame", localException.toString());
        }
      }
    });
  }
  
  public static void showAlertDialog(String paramString1, String paramString2)
  {
    final Activity localActivity = BaseIntegration.getActivity();
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localActivity);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.setCancelable(false);
    localBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          AlertDialog localAlertDialog = CommonUtilites.this.create();
          localAlertDialog.setOwnerActivity(localActivity);
          localAlertDialog.show();
          return;
        }
        catch (Exception localException)
        {
          Log.e("mygame", localException.toString());
        }
      }
    });
  }
  
  public static void showAlertDialogQuestion(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    final Activity localActivity = BaseIntegration.getActivity();
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localActivity);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton(paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        CommonUtilites.alertDialogQuestionResponse(0);
      }
    });
    localBuilder.setNegativeButton(paramString4, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        CommonUtilites.alertDialogQuestionResponse(1);
      }
    });
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          AlertDialog localAlertDialog = CommonUtilites.this.create();
          localAlertDialog.setOwnerActivity(localActivity);
          localAlertDialog.show();
          return;
        }
        catch (Exception localException)
        {
          Log.e("mygame", localException.toString());
        }
      }
    });
  }
  
  public static void showCancellableProgressDailog()
  {
    showProgressDailog(progressDialogStorageCancellable);
  }
  
  public static void showEmailDailog(String paramString)
  {
    final Activity localActivity = BaseIntegration.getActivity();
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          Intent localIntent = new Intent("android.intent.action.SEND");
          localIntent.putExtra("android.intent.extra.EMAIL", new String[] { CommonUtilites.this });
          localIntent.putExtra("android.intent.extra.SUBJECT", CommonUtilites.getLocalizableString("send_email_subject"));
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(CommonUtilites.getLocalizableString("send_email_msg"));
          localStringBuilder.append('\n');
          localStringBuilder.append("=============================================\n");
          localStringBuilder.append("Device ID: ");
          localStringBuilder.append(CommonUtilites.getDeviceId());
          localStringBuilder.append('\n');
          localStringBuilder.append("Device Model: ");
          localStringBuilder.append(CommonUtilites.getDeviceModel());
          localStringBuilder.append('\n');
          localStringBuilder.append("App Version: ");
          localStringBuilder.append(CommonUtilites.getAppVersion());
          localStringBuilder.append('\n');
          localStringBuilder.append("OS Version: ");
          localStringBuilder.append(CommonUtilites.getOSVersion());
          localStringBuilder.append('\n');
          localStringBuilder.append("=============================================\n");
          localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
          localIntent.setType("text/plain");
          localActivity.startActivity(Intent.createChooser(localIntent, "Send mail..."));
          return;
        }
        catch (Exception localException)
        {
          Log.e("mygame", localException.toString());
        }
      }
    });
  }
  
  public static void showFAQPage(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    FaqIntegration.showFAQPage(paramString, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void showFullScreenProgressDailog()
  {
    showProgressDailog(progressDialogStorage);
  }
  
  private static void showProgressDailog(ProgressDialogStorage paramProgressDialogStorage)
  {
    if (paramProgressDialogStorage != null) {
      BaseIntegration.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          try
          {
            if (CommonUtilites.ProgressDialogStorage.access$2(CommonUtilites.this) != null) {
              CommonUtilites.ProgressDialogStorage.access$2(CommonUtilites.this).show();
            }
            return;
          }
          catch (Exception localException)
          {
            Log.e("mygame", localException.toString());
          }
        }
      });
    }
  }
  
  public static void showSendProfileDialog(final String paramString1, String paramString2)
  {
    final Activity localActivity = BaseIntegration.getActivity();
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          Intent localIntent = new Intent("android.intent.action.SEND");
          localIntent.putExtra("android.intent.extra.EMAIL", new String[] { CommonUtilites.this });
          localIntent.putExtra("android.intent.extra.SUBJECT", CommonUtilites.getLocalizableString("send_email_subject"));
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(CommonUtilites.getLocalizableString("send_email_msg"));
          localStringBuilder.append('\n');
          localStringBuilder.append("=============================================\n");
          localStringBuilder.append("Device ID: ");
          localStringBuilder.append(CommonUtilites.getDeviceId());
          localStringBuilder.append('\n');
          localStringBuilder.append("Device Model: ");
          localStringBuilder.append(CommonUtilites.getDeviceModel());
          localStringBuilder.append('\n');
          localStringBuilder.append("App Version: ");
          localStringBuilder.append(CommonUtilites.getAppVersion());
          localStringBuilder.append('\n');
          localStringBuilder.append("OS Version: ");
          localStringBuilder.append(CommonUtilites.getOSVersion());
          localStringBuilder.append('\n');
          localStringBuilder.append("=============================================\n");
          localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
          localIntent.setType("text/plain");
          localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(paramString1)));
          localActivity.startActivity(Intent.createChooser(localIntent, "Send mail..."));
          return;
        }
        catch (Exception localException)
        {
          Log.e("mygame", "showSendProfileDialog failed", localException);
        }
      }
    });
  }
  
  public static void showSmallProgressDailog(int paramInt1, int paramInt2) {}
  
  public static boolean synchronizedLoad()
  {
    synchronized (sProfileSaveDataLock)
    {
      boolean bool = nativeSynchronizedLoad();
      return bool;
    }
  }
  
  public static void synchronizedSave()
  {
    synchronized (sProfileSaveDataLock)
    {
      nativeSynchronizedSave();
      return;
    }
  }
  
  public static abstract interface CommonUtilitesBaseDelegate
  {
    public abstract String getAPKExpansionFile(int paramInt);
    
    public abstract String getAPKExpansionPatchFile(int paramInt);
    
    public abstract String getCustomPrimaryAccountId();
  }
  
  public static class CommonUtilitesDelegate_Amazon
    implements CommonUtilites.CommonUtilitesBaseDelegate
  {
    public static String amazonUserId = "";
    
    public CommonUtilitesDelegate_Amazon() {}
    
    public String getAPKExpansionFile(int paramInt)
    {
      return "";
    }
    
    public String getAPKExpansionPatchFile(int paramInt)
    {
      return "";
    }
    
    public String getCustomPrimaryAccountId()
    {
      if (amazonUserId != null) {
        return amazonUserId;
      }
      return "";
    }
  }
  
  public static class CommonUtilitesDelegate_GooglePlay
    implements CommonUtilites.CommonUtilitesBaseDelegate
  {
    private static final String EXP_PATH = "/Android/obb/";
    
    public CommonUtilitesDelegate_GooglePlay() {}
    
    public String getAPKExpansionFile(int paramInt)
    {
      String str2 = "";
      try
      {
        String str3 = BaseIntegration.getApplicationContext().getPackageName();
        String str1 = str2;
        if (Environment.getExternalStorageState().equals("mounted"))
        {
          File localFile = new File(Environment.getExternalStorageDirectory().toString() + "/Android/obb/" + str3);
          str1 = str2;
          if (localFile.exists())
          {
            str1 = str2;
            if (paramInt > 0)
            {
              str3 = localFile + File.separator + "main." + paramInt + "." + str3 + ".obb";
              boolean bool = new File(str3).isFile();
              str1 = str2;
              if (bool) {
                str1 = str3;
              }
            }
          }
        }
        return str1;
      }
      catch (Exception localException) {}
      return "";
    }
    
    public String getAPKExpansionPatchFile(int paramInt)
    {
      String str2 = "";
      try
      {
        String str3 = BaseIntegration.getApplicationContext().getPackageName();
        String str1 = str2;
        if (Environment.getExternalStorageState().equals("mounted"))
        {
          File localFile = new File(Environment.getExternalStorageDirectory().toString() + "/Android/obb/" + str3);
          str1 = str2;
          if (localFile.exists())
          {
            str1 = str2;
            if (paramInt > 0)
            {
              str3 = localFile + File.separator + "patch." + paramInt + "." + str3 + ".obb";
              boolean bool = new File(str3).isFile();
              str1 = str2;
              if (bool) {
                str1 = str3;
              }
            }
          }
        }
        return str1;
      }
      catch (Exception localException) {}
      return "";
    }
    
    public String getCustomPrimaryAccountId()
    {
      return null;
    }
  }
  
  public static class CommonUtilitesDelegate_Samsung
    implements CommonUtilites.CommonUtilitesBaseDelegate
  {
    public static String amazonUserId = "";
    
    public CommonUtilitesDelegate_Samsung() {}
    
    public String getAPKExpansionFile(int paramInt)
    {
      return "";
    }
    
    public String getAPKExpansionPatchFile(int paramInt)
    {
      return "";
    }
    
    public String getCustomPrimaryAccountId()
    {
      return null;
    }
  }
  
  private static class ProgressDialogStorage
  {
    private ProgressDialog progressDialog = null;
    
    private ProgressDialogStorage() {}
  }
}
