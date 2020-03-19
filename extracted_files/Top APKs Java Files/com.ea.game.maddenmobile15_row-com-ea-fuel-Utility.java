package com.ea.fuel;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.provider.Settings.Global;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.v4.content.FileProvider;
import android.util.Log;
import com.ea.blast.MainActivity;
import com.google.android.gms.common.GoogleApiAvailability;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;
import org.json.JSONObject;

public class Utility
{
  private static final boolean DEBUG_LOG_ENABLED = false;
  private static final String DEBUG_LOG_TAG = "Utility";
  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1972;
  private static String sAdvertiserId = null;
  private static String sScreenshotFilename = null;
  
  private Utility() {}
  
  private static void Log(String paramString) {}
  
  private static boolean checkPlayServices(Activity paramActivity)
  {
    boolean bool = false;
    GoogleApiAvailability localGoogleApiAvailability = GoogleApiAvailability.getInstance();
    if (localGoogleApiAvailability != null)
    {
      bool = true;
      int i = localGoogleApiAvailability.isGooglePlayServicesAvailable(paramActivity);
      if (i != 0)
      {
        if ((localGoogleApiAvailability.isUserResolvableError(i)) && (!isKindleBuild(paramActivity))) {
          localGoogleApiAvailability.getErrorDialog(paramActivity, i, 1972).show();
        }
        bool = false;
      }
    }
    return bool;
  }
  
  public static void clearScreenshotFilename()
  {
    sScreenshotFilename = null;
  }
  
  public static String getAdvertisingId()
  {
    if (sAdvertiserId == null) {
      return "";
    }
    return sAdvertiserId;
  }
  
  public static String getAuthData()
  {
    localObject1 = "";
    localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("version", getVersionNumber());
      localObject3 = "" + getVersionNumber();
      localObject1 = localObject3;
    }
    catch (Throwable localThrowable3)
    {
      try
      {
        Object localObject3;
        localObject6 = MainActivity.instance.getPackageManager();
        localObject5 = localObject1;
        Iterator localIterator = ((PackageManager)localObject6).getInstalledApplications(128).iterator();
        for (;;)
        {
          localObject5 = localObject1;
          localObject3 = localObject1;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject5 = localObject1;
          localObject3 = ((PackageManager)localObject6).getLaunchIntentForPackage(((ApplicationInfo)localIterator.next()).packageName);
          if (localObject3 != null)
          {
            localObject5 = localObject1;
            localObject3 = ((Intent)localObject3).getComponent();
            if (localObject3 != null)
            {
              localObject5 = localObject1;
              if (((ComponentName)localObject3).toString().endsWith(".Main}"))
              {
                localObject5 = localObject1;
                localJSONObject.accumulate("component", localObject3);
                localObject5 = localObject1;
                localObject1 = (String)localObject1 + localObject3;
              }
            }
          }
        }
        localThrowable3 = localThrowable3;
        Log("failed putting version number into auth data: " + localThrowable3);
      }
      catch (Throwable localThrowable1)
      {
        Object localObject5;
        Object localObject6;
        Log("App enumeration failed: " + localThrowable1);
        Object localObject4 = localObject5;
        localObject2 = localObject4;
        try
        {
          localObject5 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("/system/bin/ps").getInputStream()));
          localObject2 = localObject4;
          new StringBuilder();
          for (;;)
          {
            localObject2 = localObject4;
            localObject6 = ((BufferedReader)localObject5).readLine();
            localObject2 = localObject4;
            if (localObject6 == null) {
              break;
            }
            localObject2 = localObject4;
            if (!((String)localObject6).contains("catch_.me_"))
            {
              localObject2 = localObject4;
              if (!((String)localObject6).contains("com.vtihzahuln")) {}
            }
            else
            {
              localObject2 = localObject4;
              localObject6 = ((String)localObject6).substring(((String)localObject6).lastIndexOf(' ') + 1);
              localObject2 = localObject4;
              localJSONObject.accumulate("process", localObject6);
              localObject2 = localObject4;
              localObject4 = (String)localObject4 + (String)localObject6;
            }
          }
          try
          {
            CRC32 localCRC32 = new CRC32();
            localCRC32.update(localObject2.getBytes());
            localJSONObject.put("signature", localCRC32.getValue() ^ 0xA5B48BF815971EE4);
            return localJSONObject.toString();
          }
          catch (Throwable localThrowable2)
          {
            for (;;)
            {
              Log("Signature calculation failed" + localThrowable2);
            }
          }
        }
        catch (Throwable localThrowable4)
        {
          Log("Process enumeration failed" + localThrowable4);
        }
      }
    }
    localObject5 = localObject1;
  }
  
  public static String getIntentUrl()
  {
    String str2 = MainActivity.instance.getIntent().getDataString();
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    return str1;
  }
  
  public static Method getMethodQuietly(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    try
    {
      paramClass = paramClass.getMethod(paramString, paramVarArgs);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass) {}
    return null;
  }
  
  public static Method getMethodQuietly(String paramString1, String paramString2, Class<?>... paramVarArgs)
  {
    try
    {
      paramString1 = getMethodQuietly(Class.forName(paramString1), paramString2, paramVarArgs);
      return paramString1;
    }
    catch (ClassNotFoundException paramString1) {}
    return null;
  }
  
  public static String getNativePackageName()
  {
    return MainActivity.instance.getPackageName();
  }
  
  public static String getScreenshotFilename()
  {
    return sScreenshotFilename;
  }
  
  public static String getVersionNumber()
  {
    try
    {
      String str = MainActivity.instance.getPackageManager().getPackageInfo(getNativePackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "NotFound";
  }
  
  public static void handleBackButton()
  {
    MainActivity.instance.finish();
  }
  
  public static Object invokeMethodQuietly(Object paramObject, Method paramMethod, Object... paramVarArgs)
  {
    try
    {
      paramObject = paramMethod.invoke(paramObject, paramVarArgs);
      return paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      return null;
    }
    catch (InvocationTargetException paramObject) {}
    return null;
  }
  
  public static boolean isAirplaneModeActive()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    for (;;)
    {
      try
      {
        if (Build.VERSION.SDK_INT >= 17)
        {
          bool1 = bool2;
          if (1 != Settings.Global.getInt(MainActivity.instance.getContentResolver(), "airplane_mode_on")) {
            break label223;
          }
          bool1 = bool2;
          if (!Settings.Global.getString(MainActivity.instance.getContentResolver(), "airplane_mode_radios").contains("wifi")) {
            break label223;
          }
          bool2 = true;
          bool1 = bool2;
          Log("isAirplaneModeActive: " + Settings.Global.getString(MainActivity.instance.getContentResolver(), "airplane_mode_radios"));
          bool1 = bool2;
          if (!bool2) {
            break label221;
          }
          bool1 = bool2;
          WifiManager localWifiManager = (WifiManager)MainActivity.instance.getSystemService("wifi");
          bool1 = bool2;
          if (localWifiManager == null) {
            break label221;
          }
          bool1 = bool2;
          if (localWifiManager.isWifiEnabled()) {
            break label219;
          }
          return true;
        }
        bool1 = bool2;
        if (1 == Settings.System.getInt(MainActivity.instance.getContentResolver(), "airplane_mode_on"))
        {
          bool1 = bool2;
          if (Settings.System.getString(MainActivity.instance.getContentResolver(), "airplane_mode_radios").contains("wifi"))
          {
            bool2 = true;
            bool1 = bool2;
            Log("isAirplaneModeActive: " + Settings.System.getString(MainActivity.instance.getContentResolver(), "airplane_mode_radios"));
            continue;
          }
        }
        bool2 = false;
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException)
      {
        return bool1;
      }
      continue;
      label219:
      bool1 = false;
      label221:
      return bool1;
      label223:
      bool2 = false;
    }
  }
  
  private static boolean isKindleBuild(Activity paramActivity)
  {
    return paramActivity.getPackageName().toLowerCase().contains("_azn");
  }
  
  public static void onStart(Activity paramActivity)
  {
    new AsyncTask()
    {
      /* Error */
      protected String doInBackground(Void... paramAnonymousVarArgs)
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_1
        //   2: aload_0
        //   3: getfield 15	com/ea/fuel/Utility$1:val$activity	Landroid/app/Activity;
        //   6: invokevirtual 36	android/app/Activity:getApplicationContext	()Landroid/content/Context;
        //   9: invokestatic 42	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
        //   12: astore_2
        //   13: aload_2
        //   14: astore_1
        //   15: aload_1
        //   16: invokevirtual 48	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
        //   19: invokestatic 52	com/ea/fuel/Utility:access$002	(Ljava/lang/String;)Ljava/lang/String;
        //   22: pop
        //   23: invokestatic 55	com/ea/fuel/Utility:access$000	()Ljava/lang/String;
        //   26: areturn
        //   27: astore_2
        //   28: aload_2
        //   29: invokevirtual 58	java/lang/Exception:printStackTrace	()V
        //   32: goto -17 -> 15
        //   35: astore_1
        //   36: aload_1
        //   37: invokevirtual 59	java/lang/NullPointerException:printStackTrace	()V
        //   40: goto -17 -> 23
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	43	0	this	1
        //   0	43	1	paramAnonymousVarArgs	Void[]
        //   12	2	2	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
        //   27	2	2	localException	Exception
        // Exception table:
        //   from	to	target	type
        //   2	13	27	java/lang/Exception
        //   15	23	35	java/lang/NullPointerException
      }
    }.execute(new Void[0]);
  }
  
  public static void shareLink(String paramString)
  {
    paramString = new Runnable()
    {
      public void run()
      {
        try
        {
          Intent localIntent = new Intent();
          localIntent.setAction("android.intent.action.SEND");
          localIntent.setType("text/plain");
          localIntent.putExtra("android.intent.extra.TEXT", this.val$text);
          MainActivity.instance.startActivity(Intent.createChooser(localIntent, "Share using"));
          return;
        }
        catch (Throwable localThrowable)
        {
          Log.e("Utility", "shareLink - caught exception: " + localThrowable);
        }
      }
    };
    new Handler(Looper.getMainLooper()).post(paramString);
  }
  
  public static void shareScreenshot(String paramString1, final String paramString2)
  {
    paramString1 = new Runnable()
    {
      public void run()
      {
        try
        {
          Object localObject = new File(this.val$filename);
          localObject = FileProvider.getUriForFile(MainActivity.instance, Utility.getNativePackageName() + ".fileprovider", (File)localObject);
          Intent localIntent = new Intent("android.intent.action.SEND");
          localIntent.setFlags(1);
          localIntent.setType("image/jpeg");
          localIntent.putExtra("android.intent.extra.STREAM", (Parcelable)localObject);
          localIntent.putExtra("android.intent.extra.TEXT", paramString2);
          MainActivity.instance.startActivity(Intent.createChooser(localIntent, "Share to"));
          return;
        }
        catch (Throwable localThrowable)
        {
          Log.e("Utility", "shareScreenshot - caught exception: " + localThrowable);
        }
      }
    };
    new Handler(Looper.getMainLooper()).post(paramString1);
  }
  
  public static void showSetAirplaneMode()
  {
    Runnable local4 = new Runnable()
    {
      public void run()
      {
        Object localObject2 = null;
        if (Build.VERSION.SDK_INT < 17) {}
        for (;;)
        {
          try
          {
            localObject1 = new Intent("android.settings.AIRPLANE_MODE_SETTINGS");
            Utility.Log("exception: " + localObject2);
          }
          catch (ActivityNotFoundException localActivityNotFoundException2)
          {
            for (;;)
            {
              try
              {
                ((Intent)localObject1).setFlags(268435456);
                Utility.Log("showSetAirplaneMode");
                if (localObject1 == null) {}
              }
              catch (ActivityNotFoundException localActivityNotFoundException1)
              {
                Object localObject1;
                continue;
              }
              try
              {
                MainActivity.instance.startActivity((Intent)localObject1);
                return;
              }
              catch (Exception localException)
              {
                Utility.Log("exception: " + localException);
                return;
              }
            }
            localActivityNotFoundException2 = localActivityNotFoundException2;
            localObject1 = localObject2;
            localObject2 = localActivityNotFoundException2;
          }
          continue;
          localObject1 = new Intent("android.settings.WIRELESS_SETTINGS");
          ((Intent)localObject1).setFlags(268435456);
        }
      }
    };
    MainActivity.instance.runOnUiThread(local4);
  }
  
  public static void takeScreenshot(String paramString)
  {
    sScreenshotFilename = paramString;
    MainActivity.instance.requestScreenshot();
  }
}
