package com.habyts.library.Services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.habyts.library.Communications.HbData;
import com.habyts.library.Communications.InstalledApplication;
import com.habyts.library.Communications.InstalledApplications;
import com.habyts.library.Configs;
import com.habyts.library.ServiceLifetimeManager;
import com.habyts.library.utils.HabytsLog;
import com.habyts.library.utils.net.AsyncActionHttp;
import com.habyts.library.utils.net.AsyncExecutor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UploadApplicationListReceiver
  extends BroadcastReceiver
{
  public static final String TAG = "Hb_AppList";
  public static String mLauncherPackage = "";
  public static ArrayList<String> mTrackedBrowsers = new ArrayList();
  public static ArrayList<String> mUnsupportedBrowsers = new ArrayList();
  private static long sNextUpload = 0L;
  private static BigInteger sPreviousListChecksum = BigInteger.ZERO;
  private static boolean sRunning = false;
  private static long sUploadInterval = 3600000L;
  
  public UploadApplicationListReceiver() {}
  
  private static BigInteger calculateCheckSum(Object paramObject)
    throws IOException, NoSuchAlgorithmException
  {
    if (paramObject == null) {
      return BigInteger.ZERO;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
    localObjectOutputStream.writeObject(paramObject);
    localObjectOutputStream.close();
    paramObject = MessageDigest.getInstance("SHA1");
    paramObject.update(localByteArrayOutputStream.toByteArray());
    return new BigInteger(1, paramObject.digest());
  }
  
  private static InstalledApplications captureInstalledApplications(Context paramContext, String paramString, boolean paramBoolean)
  {
    try
    {
      InstalledApplications localInstalledApplications = new InstalledApplications();
      localInstalledApplications.setDeviceId(paramString);
      localInstalledApplications.setDateOfObservation(new Date());
      localInstalledApplications.setApplications(new ArrayList());
      PackageManager localPackageManager = paramContext.getPackageManager();
      int i = 0;
      List localList = localPackageManager.getInstalledPackages(0);
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if ((localPackageInfo.versionName != null) || (paramBoolean))
        {
          InstalledApplication localInstalledApplication = new InstalledApplication();
          paramContext = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
          String str = localPackageInfo.packageName.toLowerCase();
          paramString = new StringBuilder();
          paramString.append(str);
          paramString.append("|");
          paramString.append(paramContext);
          paramString = paramString.toString();
          if (str.equals(mLauncherPackage))
          {
            paramContext = new StringBuilder();
            paramContext.append(paramString);
            paramContext.append("|Launcher");
            paramContext = paramContext.toString();
          }
          else if (mTrackedBrowsers.contains(str))
          {
            paramContext = new StringBuilder();
            paramContext.append(paramString);
            paramContext.append("|Browser");
            paramContext = paramContext.toString();
          }
          else
          {
            paramContext = paramString;
            if (mUnsupportedBrowsers.contains(str))
            {
              paramContext = new StringBuilder();
              paramContext.append(paramString);
              paramContext.append("|BlockedBrowser");
              paramContext = paramContext.toString();
            }
          }
          localInstalledApplication.setAppName(paramContext);
          localInstalledApplication.setEnabled(localPackageInfo.applicationInfo.enabled);
          localInstalledApplications.getApplications().add(localInstalledApplication);
        }
        i += 1;
      }
      return localInstalledApplications;
    }
    catch (Exception paramContext)
    {
      HabytsLog.e("Hb_AppList", "Failure capturing application list", paramContext);
    }
    return null;
  }
  
  private static void doUpload(Context paramContext, boolean paramBoolean)
  {
    try
    {
      InstalledApplications localInstalledApplications = captureInstalledApplications(paramContext, Configs.DeviceID, false);
      BigInteger localBigInteger = calculateCheckSum(localInstalledApplications.getApplications());
      if ((!paramBoolean) && (localBigInteger.compareTo(sPreviousListChecksum) == 0))
      {
        HabytsLog.d("Hb_AppList", "Application List not uploaded as it has not changed");
        sNextUpload = System.currentTimeMillis() + sUploadInterval;
        return;
      }
      sendApplicationList(localInstalledApplications, localBigInteger, paramContext);
      return;
    }
    catch (Exception paramContext)
    {
      HabytsLog.e("Hb_AppList", "Exception in onReceive", paramContext);
    }
  }
  
  public static void forceUploadNow(Context paramContext)
  {
    doUpload(paramContext, true);
  }
  
  public static ServiceLifetimeManager getLifetimeManager(boolean paramBoolean)
  {
    new ServiceLifetimeManager()
    {
      public boolean isRunning(Context paramAnonymousContext)
      {
        return UploadApplicationListReceiver.sRunning;
      }
      
      public void start(Context paramAnonymousContext)
      {
        if (this.val$isDebug) {
          UploadApplicationListReceiver.access$002(60000L);
        }
        UploadApplicationListReceiver.access$102(UploadApplicationListReceiver.loadApplicationListChecksum(paramAnonymousContext));
        PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramAnonymousContext, 0, new Intent(paramAnonymousContext, UploadApplicationListReceiver.class), 268435456);
        ((AlarmManager)paramAnonymousContext.getSystemService("alarm")).setRepeating(1, 20000L + System.currentTimeMillis(), UploadApplicationListReceiver.sUploadInterval, localPendingIntent);
        UploadApplicationListReceiver.access$302(true);
      }
      
      public void stop(Context paramAnonymousContext)
      {
        UploadApplicationListReceiver.storeApplicationListChecksum(paramAnonymousContext, UploadApplicationListReceiver.sPreviousListChecksum);
        PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramAnonymousContext, 0, new Intent(paramAnonymousContext, UploadApplicationListReceiver.class), 268435456);
        ((AlarmManager)paramAnonymousContext.getSystemService("alarm")).cancel(localPendingIntent);
        UploadApplicationListReceiver.access$302(false);
      }
    };
  }
  
  private static BigInteger loadApplicationListChecksum(Context paramContext)
  {
    return new BigInteger(paramContext.getSharedPreferences("Habyts", 0).getString("Hb_AppList", "0"));
  }
  
  private static void sendApplicationList(InstalledApplications paramInstalledApplications, final BigInteger paramBigInteger, final Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Application List about to be uploaded (");
    localStringBuilder.append(paramInstalledApplications.getApplications().size());
    localStringBuilder.append(") applications sent");
    HabytsLog.d("Hb_AppList", localStringBuilder.toString());
    paramInstalledApplications = new AsyncActionHttp(HbData.HbSendApplications(paramInstalledApplications), 60000)
    {
      protected void onFinish(Object paramAnonymousObject, Throwable paramAnonymousThrowable)
      {
        HabytsLog.dOrE("Hb_HttpAct", "HbSendApplications.onFinish.", paramAnonymousThrowable);
        if (paramAnonymousThrowable == null)
        {
          UploadApplicationListReceiver.access$102(paramBigInteger);
          UploadApplicationListReceiver.storeApplicationListChecksum(paramContext, UploadApplicationListReceiver.sPreviousListChecksum);
          UploadApplicationListReceiver.access$502(System.currentTimeMillis() + UploadApplicationListReceiver.sUploadInterval);
        }
      }
    };
    new AsyncExecutor().execute(paramInstalledApplications, new Object[0]);
  }
  
  private static void storeApplicationListChecksum(Context paramContext, BigInteger paramBigInteger)
  {
    paramContext = paramContext.getSharedPreferences("Habyts", 0).edit();
    paramContext.putString("Hb_AppList", paramBigInteger.toString());
    paramContext.apply();
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("onReceive [");
    localStringBuilder.append(paramContext.toString());
    localStringBuilder.append(", I:");
    localStringBuilder.append(paramIntent);
    localStringBuilder.append("]");
    HabytsLog.i("Hb_AppList", localStringBuilder.toString());
    paramIntent = paramIntent.getAction();
    if (((paramIntent != null) && (paramIntent.equals("android.intent.action.PACKAGE_ADDED"))) || (System.currentTimeMillis() > sNextUpload)) {
      doUpload(paramContext, false);
    }
  }
}
