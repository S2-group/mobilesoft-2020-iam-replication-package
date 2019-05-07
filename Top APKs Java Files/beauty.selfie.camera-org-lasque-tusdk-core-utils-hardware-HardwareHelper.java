package org.lasque.tusdk.core.utils.hardware;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Vibrator;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.lasque.tusdk.core.utils.AssetsHelper;
import org.lasque.tusdk.core.utils.ContextUtils;
import org.lasque.tusdk.core.utils.NetworkHelper;
import org.lasque.tusdk.core.utils.StringHelper;
import org.lasque.tusdk.core.utils.TLog;

public class HardwareHelper
{
  public HardwareHelper() {}
  
  private static String[] a()
  {
    String str = Build.CPU_ABI;
    if (str == null) {
      return null;
    }
    return new String[] { str };
  }
  
  public static long appMemoryBit()
  {
    return Runtime.getRuntime().maxMemory();
  }
  
  @TargetApi(21)
  private static String[] b()
  {
    return Build.SUPPORTED_ABIS;
  }
  
  public static boolean exsitAppInstall(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (StringHelper.isEmpty(paramString))) {
      return false;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static int getAlertVolume(Context paramContext, int paramInt)
  {
    paramContext = getAudioManager(paramContext);
    if (paramContext == null) {
      return 100;
    }
    float f = paramContext.getStreamMaxVolume(paramInt);
    return (int)(paramContext.getStreamVolume(paramInt) * 100.0F / f);
  }
  
  public static AudioManager getAudioManager(Context paramContext)
  {
    return (AudioManager)ContextUtils.getSystemService(paramContext, "audio");
  }
  
  public static String getAuthorityFromPermission(Context paramContext, String paramString)
  {
    if (StringHelper.isEmpty(paramString)) {
      return null;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(8);
    if ((paramContext == null) || (paramContext.isEmpty())) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
      if (arrayOfProviderInfo != null)
      {
        int j = arrayOfProviderInfo.length;
        int i = 0;
        while (i < j)
        {
          ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
          if ((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission))) {
            return localProviderInfo.authority;
          }
          i += 1;
        }
      }
    }
    return null;
  }
  
  public static Intent getLaunchIntent(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
  }
  
  public static Vibrator getVibrator(Context paramContext)
  {
    return (Vibrator)ContextUtils.getSystemService(paramContext, "vibrator");
  }
  
  public static String getWifiIp(Context paramContext)
  {
    if (!isNetworkWifi(paramContext)) {
      return null;
    }
    paramContext = (WifiManager)ContextUtils.getSystemService(paramContext, "wifi");
    if ((paramContext == null) || (!paramContext.isWifiEnabled())) {
      return null;
    }
    int i = paramContext.getConnectionInfo().getIpAddress();
    if (i == 0) {
      return null;
    }
    return NetworkHelper.longToIP(i);
  }
  
  public static boolean hasShortcut(Context paramContext, String paramString)
  {
    Object localObject = getAuthorityFromPermission(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    if (localObject == null) {
      return true;
    }
    localObject = Uri.parse("content://" + (String)localObject + "/favorites?notify=true");
    paramContext = paramContext.getContentResolver().query((Uri)localObject, new String[] { "title" }, "title=?", new String[] { paramString }, null);
    if (paramContext == null) {
      return false;
    }
    if (paramContext.moveToNext()) {}
    for (boolean bool = true;; bool = false)
    {
      paramContext.close();
      return bool;
    }
  }
  
  public static boolean isMatchDeviceManuFacturer(String paramString)
  {
    if ((paramString == null) || (Build.MANUFACTURER == null)) {
      return false;
    }
    return Build.MANUFACTURER.equalsIgnoreCase(paramString);
  }
  
  public static boolean isMatchDeviceModel(String paramString)
  {
    if ((paramString == null) || (Build.MODEL == null)) {
      return false;
    }
    return Build.MODEL.equalsIgnoreCase(paramString);
  }
  
  public static boolean isMatchDeviceModelAndManuFacturer(String paramString1, String paramString2)
  {
    return (isMatchDeviceModel(paramString1)) && (isMatchDeviceManuFacturer(paramString2));
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = (ConnectivityManager)ContextUtils.getSystemService(paramContext, "connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getAllNetworkInfo();
    if (paramContext == null) {
      return false;
    }
    int j = paramContext.length;
    int i = 0;
    while (i < j)
    {
      if (paramContext[i].isConnected()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isNetworkWifi(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = ((ConnectivityManager)ContextUtils.getSystemService(paramContext, "connectivity")).getActiveNetworkInfo();
    if ((paramContext != null) && (paramContext.getType() == 1)) {
      return paramContext.isConnected();
    }
    return false;
  }
  
  public static boolean isSupportAbi(String... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length < 1)) {}
    label25:
    label78:
    label87:
    label92:
    for (;;)
    {
      return false;
      String[] arrayOfString;
      int k;
      int i;
      if (Build.VERSION.SDK_INT < 21)
      {
        arrayOfString = a();
        if (arrayOfString == null) {
          break label78;
        }
        k = paramVarArgs.length;
        i = 0;
      }
      for (;;)
      {
        if (i >= k) {
          break label92;
        }
        String str = paramVarArgs[i];
        int m = arrayOfString.length;
        int j = 0;
        for (;;)
        {
          if (j >= m) {
            break label87;
          }
          if (str.equalsIgnoreCase(arrayOfString[j]))
          {
            return true;
            arrayOfString = b();
            break label25;
            break;
          }
          j += 1;
        }
        i += 1;
      }
    }
  }
  
  public static MediaPlayer loadMediaAsset(Context paramContext, String paramString)
  {
    paramContext = AssetsHelper.getAssetFileDescriptor(paramContext, paramString);
    if (paramContext == null) {
      return null;
    }
    MediaPlayer localMediaPlayer = new MediaPlayer();
    try
    {
      localMediaPlayer.setDataSource(paramContext.getFileDescriptor(), paramContext.getStartOffset(), paramContext.getLength());
      paramContext.close();
      localMediaPlayer.prepare();
      return localMediaPlayer;
    }
    catch (IllegalArgumentException paramContext)
    {
      TLog.e(paramContext, "loadMediaAsset: %s", new Object[] { paramString });
      return null;
    }
    catch (IllegalStateException paramContext)
    {
      for (;;)
      {
        TLog.e(paramContext, "loadMediaAsset: %s", new Object[] { paramString });
      }
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        TLog.e(paramContext, "loadMediaAsset: %s", new Object[] { paramString });
      }
    }
  }
  
  public static void playSound(Context paramContext, final int paramInt)
  {
    if (paramContext == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        MediaPlayer localMediaPlayer = MediaPlayer.create(this.a, paramInt);
        localMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
          public void onCompletion(MediaPlayer paramAnonymous2MediaPlayer)
          {
            paramAnonymous2MediaPlayer.release();
          }
        });
        localMediaPlayer.start();
      }
    }).start();
  }
}
