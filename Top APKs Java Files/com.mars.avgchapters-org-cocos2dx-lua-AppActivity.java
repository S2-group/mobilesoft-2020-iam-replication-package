package org.cocos2dx.lua;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Window;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.google.ads.conversiontracking.AdWordsConversionReporter;
import com.google.android.gms.common.GoogleApiAvailability;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.cocos2dx.lib.Cocos2dxActivity;

public class AppActivity
  extends Cocos2dxActivity
{
  static Context cInstance;
  static String hostIPAdress = "0.0.0.0";
  static CallbackManager mFbCManager;
  
  public AppActivity() {}
  
  public static void copyAndShare(String paramString)
  {
    copyFile(paramString, "/sdcard/captureScreen.jpg");
    paramString = new Intent();
    paramString.setAction("android.intent.action.SEND");
    paramString.setType("image/*");
    if (Build.VERSION.SDK_INT < 26) {
      paramString.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File("/sdcard/captureScreen.jpg")));
    }
    for (;;)
    {
      cInstance.startActivity(Intent.createChooser(paramString, "Chapters-Interactive Stories Share"));
      return;
      paramString.putExtra("android.intent.extra.STREAM", "/sdcard/captureScreen.jpg");
    }
  }
  
  public static void copyFile(String paramString1, String paramString2)
  {
    int i = 0;
    try
    {
      if (new File(paramString1).exists())
      {
        paramString1 = new FileInputStream(paramString1);
        paramString2 = new FileOutputStream(paramString2);
        byte[] arrayOfByte = new byte['֤'];
        for (;;)
        {
          int j = paramString1.read(arrayOfByte);
          if (j == -1) {
            break;
          }
          i += j;
          System.out.println(i);
          paramString2.write(arrayOfByte, 0, j);
        }
      }
      return;
    }
    catch (Exception paramString1)
    {
      System.out.println("复制单个文件操作出错");
      paramString1.printStackTrace();
    }
    paramString1.close();
  }
  
  public static void debugLog(String paramString)
  {
    MarsLog.i("avg_lua_debug", "lua debug log===>" + paramString);
  }
  
  public static void exitGame()
  {
    ((Activity)cInstance).finish();
    System.exit(0);
  }
  
  public static String getLocalIpAddress()
  {
    return hostIPAdress;
  }
  
  public static String getVersionName()
  {
    Object localObject = cInstance.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(cInstance.getPackageName(), 0).versionName;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "unkown";
  }
  
  private boolean isNetworkConnected()
  {
    Object localObject = (ConnectivityManager)getSystemService("connectivity");
    ArrayList localArrayList;
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      localArrayList = new ArrayList();
      localArrayList.add(Integer.valueOf(1));
    }
    try
    {
      localArrayList.add(Integer.valueOf(ConnectivityManager.class.getDeclaredField("TYPE_ETHERNET").getInt(null)));
      if ((localObject != null) && (localArrayList.contains(Integer.valueOf(((NetworkInfo)localObject).getType())))) {
        return true;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
      return false;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;) {}
    }
  }
  
  private static native boolean nativeIsDebug();
  
  private static native boolean nativeIsLandScape();
  
  public static void shareCaptureScreen(String paramString)
    throws IOException
  {
    if (new File(paramString).exists())
    {
      MarsLog.i("yuhui test", paramString + " is exist!");
      MarsLog.i("yuhui test", Build.VERSION.SDK_INT + "");
      if (Build.VERSION.SDK_INT >= 23)
      {
        String[] arrayOfString = new String[1];
        arrayOfString[0] = "android.permission.WRITE_EXTERNAL_STORAGE";
        int j = arrayOfString.length;
        int i = 0;
        if (i < j)
        {
          String str = arrayOfString[i];
          if (cInstance.checkSelfPermission(str) != 0) {
            ActivityCompat.requestPermissions((Activity)cInstance, arrayOfString, 101);
          }
          for (;;)
          {
            i += 1;
            break;
            copyAndShare(paramString);
          }
        }
      }
      else
      {
        copyAndShare(paramString);
      }
      return;
    }
    MarsLog.i("yuhui test", paramString + " is not exist!");
  }
  
  public String getGooglePlayServicesVersion()
  {
    new ArrayList();
    List localList = getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      AppInfo localAppInfo = new AppInfo();
      localAppInfo.appName = localPackageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
      localAppInfo.packageName = localPackageInfo.packageName;
      localAppInfo.versionName = localPackageInfo.versionName;
      localAppInfo.versionCode = localPackageInfo.versionCode;
      if (localAppInfo.packageName.equals("com.google.android.gms"))
      {
        localAppInfo.versionName = localAppInfo.versionName.replace(" ", "");
        return localAppInfo.versionName;
      }
      i += 1;
    }
    return "";
  }
  
  public String getHostIpAddress()
  {
    int i = ((WifiManager)getApplicationContext().getSystemService("wifi")).getConnectionInfo().getIpAddress();
    StringBuilder localStringBuilder = new StringBuilder().append(i & 0xFF).append(".");
    i >>>= 8;
    localStringBuilder = localStringBuilder.append(i & 0xFF).append(".");
    i >>>= 8;
    return (i & 0xFF) + "." + (i >>> 8 & 0xFF);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    PayHelper.onActivityResult(paramInt1, paramInt2, paramIntent);
    mFbCManager.onActivityResult(paramInt1, paramInt2, paramIntent);
    LoginHelper.onActivityResult(paramInt1, paramInt2, paramIntent);
    FyberHelper.onActivityResult(paramInt1, paramInt2, paramIntent);
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    try
    {
      ImagePicker.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
    }
    catch (URISyntaxException paramIntent)
    {
      paramIntent.printStackTrace();
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    getWindow().addFlags(128);
    if (nativeIsLandScape()) {
      setRequestedOrientation(6);
    }
    for (;;)
    {
      cInstance = this;
      GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this);
      mFbCManager = CallbackManager.Factory.create();
      LoginHelper.init(this, mFbCManager);
      ShareHelper.init(this, mFbCManager);
      PayHelper.init(this);
      StatisticHelper.init(this);
      MarsUtil.init(this);
      AdWordsConversionReporter.reportWithConversionId(getApplicationContext(), "857274540", "C1OJCN2TvnQQrPHjmAM", "1.00", false);
      ImagePicker.init(this);
      StatisticHelper.onAppOpen();
      if (getIntent() != null) {
        MarsUtil.handleUri(getIntent().getData());
      }
      return;
      setRequestedOrientation(7);
    }
  }
  
  public void onCreate(@Nullable Bundle paramBundle, @Nullable PersistableBundle paramPersistableBundle)
  {
    super.onCreate(paramBundle, paramPersistableBundle);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    PayHelper.onDestory();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    if ("android.intent.action.VIEW".equals(paramIntent.getAction())) {
      MarsUtil.handleUri(getIntent().getData());
    }
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    if (paramInt == 101)
    {
      MarsLog.i("yuhui test", "---请求权限成功==");
      copyAndShare("/data/user/0/com.mars.avgchapters/files//captureScreen.jpg");
    }
    for (;;)
    {
      MarsUtil.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
      super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
      return;
      MarsLog.i("yuhui test", "---请求权限失败==");
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    MarsUtil.hideSysUi();
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public class AppInfo
  {
    public String appName = "";
    public String packageName = "";
    public int versionCode = 0;
    public String versionName = "";
    
    public AppInfo() {}
    
    public void print()
    {
      MarsLog.i("yuhui test", "Name:" + this.appName + " Package:" + this.packageName);
      MarsLog.i("yuhui test", "Name:" + this.appName + " versionName:" + this.versionName);
      MarsLog.i("yuhui test", "Name:" + this.appName + " versionCode:" + this.versionCode);
    }
  }
}
