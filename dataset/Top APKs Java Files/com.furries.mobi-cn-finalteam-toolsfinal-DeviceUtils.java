package cn.finalteam.toolsfinal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.Vibrator;
import android.provider.MediaStore.Images.Media;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DeviceUtils
{
  public static final int NETWORK_CLASS_2_G = 2;
  public static final int NETWORK_CLASS_3_G = 3;
  public static final int NETWORK_CLASS_4_G = 4;
  public static final int NETWORK_CLASS_UNKNOWN = 0;
  public static final int NETWORK_WIFI = 1;
  
  public DeviceUtils() {}
  
  public static void callDial(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + paramString)));
  }
  
  public static void callPhone(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramString)));
  }
  
  @TargetApi(11)
  public static void coptyToClipBoard(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      ((android.content.ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", paramString));
      return;
    }
    ((android.text.ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString);
  }
  
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static boolean existSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static long getAllSize()
  {
    if (!existSDCard()) {
      return 0L;
    }
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
  }
  
  public static List<String> getAppPackageNamelist(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      localArrayList.add(((PackageInfo)paramContext.next()).packageName);
    }
    return localArrayList;
  }
  
  public static long getAvailaleSize()
  {
    if (!existSDCard()) {
      return 0L;
    }
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static String getExternalStorageDirectory()
  {
    Map localMap = System.getenv();
    String[] arrayOfString = new String[localMap.values().size()];
    localMap.values().toArray(arrayOfString);
    localMap = arrayOfString[(arrayOfString.length - 1)];
    if ((localMap.startsWith("/mnt/")) && (!Environment.getExternalStorageDirectory().getAbsolutePath().equals(localMap))) {
      return localMap;
    }
    return null;
  }
  
  public static String getIMEI(Context paramContext)
  {
    String str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    paramContext = str;
    if (StringUtils.isEmpty(str)) {
      paramContext = "";
    }
    return paramContext;
  }
  
  public static String getLatestCameraPicture(Context paramContext)
  {
    if (!existSDCard()) {}
    do
    {
      return null;
      paramContext = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_data", "bucket_display_name", "datetaken", "mime_type" }, null, null, "datetaken DESC");
    } while (!paramContext.moveToFirst());
    return paramContext.getString(1);
  }
  
  public static String getLocalIPAddress()
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
      } while (localInetAddress.isLoopbackAddress());
      Object localObject = localInetAddress.getHostAddress().toString();
      return localObject;
    }
    catch (SocketException localSocketException)
    {
      return "0.0.0.0";
    }
    return "0.0.0.0";
  }
  
  public static String getMac(Context paramContext)
  {
    String str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    paramContext = str;
    if (StringUtils.isEmpty(str)) {
      paramContext = "";
    }
    return paramContext;
  }
  
  public static int getNavigationBarHeight(Context paramContext)
  {
    int i = 0;
    paramContext = paramContext.getResources();
    int j = paramContext.getIdentifier("navigation_bar_height", "dimen", "android");
    if (j > 0) {
      i = paramContext.getDimensionPixelSize(j);
    }
    return i;
  }
  
  public static int getNetType(Context paramContext)
  {
    int j = 0;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    int i = j;
    int k;
    if (localNetworkInfo != null)
    {
      i = j;
      if (localNetworkInfo.isConnected())
      {
        k = localNetworkInfo.getType();
        if (k != 1) {
          break label49;
        }
        i = 1;
      }
    }
    label49:
    do
    {
      return i;
      i = j;
    } while (k != 0);
    switch (((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType())
    {
    default: 
      return 0;
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
      return 2;
    case 3: 
    case 5: 
    case 6: 
    case 8: 
    case 9: 
    case 10: 
    case 12: 
    case 14: 
    case 15: 
      return 3;
    }
    return 4;
  }
  
  public static int getPhoneType(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getPhoneType();
  }
  
  public static DisplayMetrics getScreenPix(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    int i = 0;
    int j = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0) {
      i = paramContext.getResources().getDimensionPixelSize(j);
    }
    return i;
  }
  
  public static int getTopBarHeight(Activity paramActivity)
  {
    return paramActivity.getWindow().findViewById(16908290).getTop();
  }
  
  public static String getUDID(Context paramContext)
  {
    Object localObject = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if ((!StringUtils.isEmpty((String)localObject)) && (!((String)localObject).equals("9774d56d682e549c")))
    {
      paramContext = (Context)localObject;
      if (((String)localObject).length() >= 15) {}
    }
    else
    {
      paramContext = new BigInteger(64, new SecureRandom()).toString(16);
    }
    localObject = paramContext;
    if (StringUtils.isEmpty(paramContext)) {
      localObject = "";
    }
    return localObject;
  }
  
  public static void goHome(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.addFlags(270532608);
    paramContext.startActivity(localIntent);
  }
  
  public static void hideInputSoftFromWindowMethod(Context paramContext, View paramView)
  {
    try
    {
      ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean isActiveSoftInput(Context paramContext)
  {
    return ((InputMethodManager)paramContext.getSystemService("input_method")).isActive();
  }
  
  public static boolean isAppInstall(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static boolean isOnline(Context paramContext)
  {
    boolean bool = false;
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null) {
        bool = paramContext.isConnectedOrConnecting();
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isPhone(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getPhoneType() != 0;
  }
  
  public static boolean isProessRunning(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext()) {
      if (((ActivityManager.RunningAppProcessInfo)paramContext.next()).processName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
    if (paramContext.size() == 0) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getClassName().equals(paramString) == true) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static void sendSms(Context paramContext, String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("smsto:");
    Object localObject = paramString1;
    if (TextUtils.isEmpty(paramString1)) {
      localObject = "";
    }
    localObject = new Intent("android.intent.action.SENDTO", Uri.parse((String)localObject));
    paramString1 = paramString2;
    if (TextUtils.isEmpty(paramString2)) {
      paramString1 = "";
    }
    ((Intent)localObject).putExtra("sms_body", paramString1);
    paramContext.startActivity((Intent)localObject);
  }
  
  public static void showInputSoftFromWindowMethod(Context paramContext, View paramView)
  {
    try
    {
      ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramView, 2);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @SuppressLint({"NewApi"})
  public static boolean startActivityForPackage(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramString = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      Object localObject = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
      ((Intent)localObject).setFlags(131072);
      ((Intent)localObject).setPackage(paramString.packageName);
      localObject = (ResolveInfo)paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
      if (localObject != null)
      {
        paramString = ((ResolveInfo)localObject).activityInfo.packageName;
        localObject = ((ResolveInfo)localObject).activityInfo.name;
        Intent localIntent = new Intent("android.intent.action.MAIN");
        localIntent.addCategory("android.intent.category.LAUNCHER");
        localIntent.setFlags(270532608);
        localIntent.setComponent(new ComponentName(paramString, (String)localObject));
        paramContext.startActivity(localIntent);
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static void vibrate(Context paramContext, long paramLong)
  {
    ((Vibrator)paramContext.getSystemService("vibrator")).vibrate(new long[] { 0L, paramLong }, -1);
  }
  
  public boolean isSoftKeyAvail(final Activity paramActivity)
  {
    final boolean[] arrayOfBoolean = new boolean[1];
    arrayOfBoolean[0] = false;
    paramActivity = paramActivity.getWindow().getDecorView().findViewById(16908290);
    paramActivity.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        if (paramActivity.getRootView().getHeight() - paramActivity.getHeight() > 100) {
          arrayOfBoolean[0] = true;
        }
      }
    });
    return arrayOfBoolean[0];
  }
}
