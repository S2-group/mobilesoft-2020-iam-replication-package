package es.socialpoint.hydra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Handler;
import es.socialpoint.hydra.services.StoreServices;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

public enum NativeUtils
{
  instance;
  
  private static Context context;
  String env = null;
  String fappId = null;
  int feedActionDebug = 0;
  int forceSD = 0;
  String forceUserId = null;
  String locEnvironmentKey = null;
  int localizationUrlDebug = -1;
  String origin = null;
  int resetCache = 0;
  int staticUrlDebug = -1;
  String type = null;
  
  private NativeUtils() {}
  
  public static boolean checkInstalledApp(String paramString)
  {
    return context.getPackageManager().getLaunchIntentForPackage(paramString) != null;
  }
  
  public static int compareSystemVersion(String paramString)
  {
    String[] arrayOfString = Build.VERSION.RELEASE.split("\\.");
    paramString = paramString.split("\\.");
    int i = 0;
    while ((i < arrayOfString.length) && (i < paramString.length) && (arrayOfString[i].equals(paramString[i]))) {
      i += 1;
    }
    if ((i < arrayOfString.length) && (i < paramString.length)) {
      return Integer.signum(Integer.valueOf(arrayOfString[i]).compareTo(Integer.valueOf(paramString[i])));
    }
    return Integer.signum(arrayOfString.length - paramString.length);
  }
  
  public static void crashDialog(String paramString1, String paramString2)
  {
    Handler localHandler = new Handler(context.getMainLooper());
    SPAlertDialog localSPAlertDialog = new SPAlertDialog(context);
    localSPAlertDialog.setMessage(paramString2);
    localSPAlertDialog.setTitle(paramString1);
    localHandler.post(localSPAlertDialog);
  }
  
  public static int getAppVersion()
  {
    try
    {
      int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return 0;
  }
  
  public static float getDeviceVolume()
  {
    float f = -1.0F;
    AudioManager localAudioManager = (AudioManager)context.getSystemService("audio");
    if (localAudioManager != null)
    {
      int i = localAudioManager.getStreamVolume(3);
      int j = localAudioManager.getStreamMaxVolume(3);
      f = i / j;
    }
    return f;
  }
  
  public static long getFreeMemory()
  {
    return Debug.getNativeHeapFreeSize();
  }
  
  public static String getPackageName()
  {
    return context.getPackageName();
  }
  
  public static String getProxyString()
  {
    try
    {
      Object localObject = ProxySelector.getDefault().select(new URI("http://www.socialpoint.es"));
      if (((List)localObject).size() == 0) {
        return "";
      }
      localObject = (InetSocketAddress)((Proxy)((List)localObject).get(0)).address();
      if (localObject == null) {
        return "";
      }
      localObject = ((InetSocketAddress)localObject).getHostName() + ":" + ((InetSocketAddress)localObject).getPort();
      return localObject;
    }
    catch (URISyntaxException localURISyntaxException) {}
    return "";
  }
  
  public static SharedPreferences getSharedPreferences(String paramString, int paramInt)
  {
    return context.getSharedPreferences(paramString, paramInt);
  }
  
  public static String getStorageFile()
  {
    return PersistentAttrStorage.instance.getStorageFile();
  }
  
  public static long getTotalMemory()
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      long l = Integer.valueOf(localBufferedReader.readLine().split("\\s+")[1]).intValue() * 1024;
      localBufferedReader.close();
      return l;
    }
    catch (IOException localIOException) {}
    return 0L;
  }
  
  public static long getUsedMemory()
  {
    return Debug.getNativeHeapAllocatedSize();
  }
  
  public static String getVersionName()
  {
    try
    {
      String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "";
  }
  
  public static boolean isNetworkAvailable()
  {
    Object localObject = (ConnectivityManager)context.getSystemService("connectivity");
    if (localObject != null) {}
    for (localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo(); (localObject != null) && (((NetworkInfo)localObject).isConnected()) && (((NetworkInfo)localObject).isAvailable()); localObject = null) {
      return true;
    }
    return false;
  }
  
  public static boolean isPackageInstalled(String paramString)
  {
    boolean bool2 = false;
    Iterator localIterator = context.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public static void launchApp(String paramString)
  {
    paramString = context.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString != null) {
      startActivity(paramString);
    }
  }
  
  private native void onSetEnvironment(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
  
  private native void onSetEnvironmentWithLocalizationKey(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString4, long paramLong);
  
  private native void onSetOrigin(String paramString1, String paramString2, long paramLong);
  
  public static void openExternalLink(String paramString)
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
  }
  
  public static void openStore(String paramString)
  {
    StoreServices.openStoreWithApp(paramString);
  }
  
  public static void removeStorageFile()
  {
    PersistentAttrStorage.instance.removeStorageFile();
  }
  
  public static void sendEmail(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    localIntent.putExtra("android.intent.extra.EMAIL", paramArrayOfString);
    localIntent.setType("message/rfc822");
    context.startActivity(Intent.createChooser(localIntent, "Send mail client :"));
  }
  
  public static void sendSMS(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.putExtra("sms_body", paramString2);
    localIntent.putExtra("address", paramString1);
    localIntent.setType("vnd.android-dir/mms-sms");
    context.startActivity(localIntent);
  }
  
  public static void startActivity(Intent paramIntent)
  {
    context.startActivity(paramIntent);
  }
  
  public static void webView(String paramString)
  {
    Handler localHandler = new Handler(context.getMainLooper());
    SPWebDialog localSPWebDialog = new SPWebDialog(context);
    localSPWebDialog.setUrl(paramString);
    localHandler.post(localSPWebDialog);
  }
  
  public void getEnvironment(long paramLong)
  {
    if ((this.locEnvironmentKey != null) && (this.locEnvironmentKey.length() > 0))
    {
      onSetEnvironmentWithLocalizationKey(this.env, this.fappId, this.forceUserId, this.feedActionDebug, this.staticUrlDebug, this.localizationUrlDebug, this.resetCache, this.forceSD, this.locEnvironmentKey, paramLong);
      return;
    }
    onSetEnvironment(this.env, this.fappId, this.forceUserId, this.feedActionDebug, this.staticUrlDebug, this.localizationUrlDebug, this.resetCache, this.forceSD, paramLong);
  }
  
  public void getOrigin(long paramLong)
  {
    onSetOrigin(this.origin, this.type, paramLong);
  }
  
  public void init(Context paramContext)
  {
    context = paramContext;
  }
  
  public void setEnvironment(String paramString)
  {
    this.env = paramString;
  }
  
  public void setFappId(String paramString)
  {
    this.fappId = paramString;
  }
  
  public void setFeedActionDebug(int paramInt)
  {
    this.feedActionDebug = paramInt;
  }
  
  public void setForceSD()
  {
    this.forceSD = 1;
  }
  
  public void setForceUserId(String paramString)
  {
    this.forceUserId = paramString;
  }
  
  public void setLocEnvironmentKey(String paramString)
  {
    this.locEnvironmentKey = paramString;
  }
  
  public void setLocalizationUrlDebug(int paramInt)
  {
    this.localizationUrlDebug = paramInt;
  }
  
  public void setOrigin(String paramString1, String paramString2)
  {
    this.origin = paramString1;
    this.type = paramString2;
  }
  
  public void setResetCache(int paramInt)
  {
    this.resetCache = paramInt;
  }
  
  public void setStaticUrlDebug(int paramInt)
  {
    this.staticUrlDebug = paramInt;
  }
}
