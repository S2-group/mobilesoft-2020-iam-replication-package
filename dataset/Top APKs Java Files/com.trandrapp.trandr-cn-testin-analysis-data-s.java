package cn.testin.analysis.data;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.Settings.Secure;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import cn.testin.analysis.data.common.utils.DeviceUtils;
import cn.testin.analysis.data.common.utils.LogUtils;
import cn.testin.analysis.data.common.utils.MD5Utils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;

public final class s
{
  public static String a(Context paramContext)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    try
    {
      if (DeviceUtils.checkPermissions(paramContext, "android.permission.READ_PHONE_STATE")) {
        paramContext = DeviceUtils.getTelephonyManager(paramContext).getDeviceId();
      } else {
        paramContext = null;
      }
      try
      {
        if (!TextUtils.isEmpty(paramContext))
        {
          boolean bool = paramContext.matches("0+");
          if (!bool) {}
        }
        else
        {
          LogUtils.d("There is no access to obtain imei or imei is zeros");
          paramContext = localObject1;
        }
      }
      catch (Throwable localThrowable1) {}
      LogUtils.e(localThrowable2);
    }
    catch (Throwable localThrowable2)
    {
      paramContext = localObject2;
    }
    return paramContext;
  }
  
  private static String a(Context paramContext, int paramInt)
  {
    try
    {
      if (!DeviceUtils.checkPermissions(paramContext, "android.permission.READ_PHONE_STATE")) {
        return null;
      }
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      Class localClass = Class.forName("android.telephony.TelephonyManager");
      if (Build.VERSION.SDK_INT > 21) {}
      for (paramContext = localClass.getMethod("getSubscriberId", new Class[] { Integer.TYPE }).invoke(paramContext, new Object[] { Integer.valueOf(paramInt) });; paramContext = localClass.getMethod("getSubscriberId", new Class[] { Long.TYPE }).invoke(paramContext, new Object[] { Long.valueOf(paramInt) }))
      {
        return (String)paramContext;
        if (Build.VERSION.SDK_INT != 21) {
          break;
        }
      }
      return null;
    }
    catch (Throwable paramContext)
    {
      LogUtils.e(paramContext);
    }
  }
  
  public static String[] a()
  {
    try
    {
      String[] arrayOfString = new File("/system/fonts/").list();
      return arrayOfString;
    }
    catch (Throwable localThrowable)
    {
      LogUtils.e(localThrowable);
    }
    return null;
  }
  
  private static int b(Context paramContext, int paramInt)
  {
    paramContext = SubscriptionManager.from(paramContext);
    try
    {
      paramContext = Class.forName(paramContext.getClass().getName()).getMethod("getSubId", new Class[] { Integer.TYPE }).invoke(paramContext, new Object[] { Integer.valueOf(1) });
      if (paramContext != null)
      {
        paramInt = ((int[])paramContext)[0];
        return paramInt;
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  public static String b(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      if (DeviceUtils.checkPermissions(paramContext, "android.permission.READ_PHONE_STATE")) {
        paramContext = Build.getSerial();
      } else {
        paramContext = null;
      }
    }
    else {
      paramContext = Build.SERIAL;
    }
    Context localContext = paramContext;
    if ("unknown".equals(paramContext)) {
      localContext = null;
    }
    return localContext;
  }
  
  /* Error */
  private static int c(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: ldc -100
    //   2: invokestatic 162	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   5: astore_2
    //   6: aload_0
    //   7: invokevirtual 166	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   10: astore_0
    //   11: aload_0
    //   12: aload_2
    //   13: aconst_null
    //   14: ldc -88
    //   16: iconst_1
    //   17: anewarray 36	java/lang/String
    //   20: dup
    //   21: iconst_0
    //   22: ldc -86
    //   24: aastore
    //   25: aconst_null
    //   26: invokevirtual 176	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   29: astore_0
    //   30: aload_0
    //   31: ifnull +47 -> 78
    //   34: aload_0
    //   35: astore_2
    //   36: aload_0
    //   37: invokeinterface 182 1 0
    //   42: ifeq +36 -> 78
    //   45: aload_0
    //   46: astore_2
    //   47: aload_0
    //   48: aload_0
    //   49: ldc -72
    //   51: invokeinterface 188 2 0
    //   56: invokeinterface 192 2 0
    //   61: istore_1
    //   62: aload_0
    //   63: ifnull +9 -> 72
    //   66: aload_0
    //   67: invokeinterface 195 1 0
    //   72: iload_1
    //   73: ireturn
    //   74: astore_3
    //   75: goto +19 -> 94
    //   78: aload_0
    //   79: ifnull +31 -> 110
    //   82: goto +22 -> 104
    //   85: astore_0
    //   86: aconst_null
    //   87: astore_2
    //   88: goto +25 -> 113
    //   91: astore_3
    //   92: aconst_null
    //   93: astore_0
    //   94: aload_0
    //   95: astore_2
    //   96: aload_3
    //   97: invokestatic 52	cn/testin/analysis/data/common/utils/LogUtils:e	(Ljava/lang/Throwable;)V
    //   100: aload_0
    //   101: ifnull +9 -> 110
    //   104: aload_0
    //   105: invokeinterface 195 1 0
    //   110: iconst_m1
    //   111: ireturn
    //   112: astore_0
    //   113: aload_2
    //   114: ifnull +9 -> 123
    //   117: aload_2
    //   118: invokeinterface 195 1 0
    //   123: aload_0
    //   124: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	paramContext	Context
    //   0	125	1	paramInt	int
    //   5	113	2	localObject	Object
    //   74	1	3	localThrowable1	Throwable
    //   91	6	3	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   36	45	74	java/lang/Throwable
    //   47	62	74	java/lang/Throwable
    //   11	30	85	finally
    //   11	30	91	java/lang/Throwable
    //   36	45	112	finally
    //   47	62	112	finally
    //   96	100	112	finally
  }
  
  public static String c(Context paramContext)
  {
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    int i = 0;
    while (i < 2)
    {
      if (paramContext.equals(new String[] { "9774d56d682e549c", "0123456789abcdef" }[i])) {
        return null;
      }
      i += 1;
    }
    return paramContext;
  }
  
  public static String d(Context paramContext)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    try
    {
      if (Build.VERSION.SDK_INT >= 23) {
        return null;
      }
      if (DeviceUtils.checkPermissions(paramContext, "android.permission.BLUETOOTH"))
      {
        paramContext = BluetoothAdapter.getDefaultAdapter();
        if (paramContext != null)
        {
          paramContext = paramContext.getAddress();
          break label62;
        }
      }
      else if (Build.VERSION.SDK_INT >= 18)
      {
        paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "bluetooth_address");
        break label62;
      }
      paramContext = null;
      try
      {
        label62:
        boolean bool = "02:00:00:00:00:00".equals(paramContext);
        if (!bool) {
          return paramContext;
        }
        paramContext = localObject1;
      }
      catch (Throwable localThrowable1) {}
      LogUtils.e(localThrowable2);
    }
    catch (Throwable localThrowable2)
    {
      paramContext = localObject2;
    }
    return paramContext;
  }
  
  public static JSONArray e(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    JSONArray localJSONArray = new JSONArray();
    while (i < ((List)localObject).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localJSONArray.put(localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString());
      }
      i += 1;
    }
    return localJSONArray;
  }
  
  public static JSONArray f(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    paramContext.getApplicationContext().getPackageManager();
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    int i = Process.myUid();
    paramContext = paramContext.getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.uid == i) {
        localJSONArray.put(localRunningAppProcessInfo.processName);
      }
    }
    return localJSONArray;
  }
  
  public static boolean g(Context paramContext)
  {
    try
    {
      paramContext = (LocationManager)paramContext.getSystemService("location");
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.getAllProviders();
      if (paramContext == null) {
        return false;
      }
      boolean bool = paramContext.contains("gps");
      return bool;
    }
    catch (Throwable paramContext)
    {
      LogUtils.e(paramContext);
    }
    return true;
  }
  
  public static JSONArray h(Context paramContext)
  {
    try
    {
      Object localObject = ((InputMethodManager)paramContext.getSystemService("input_method")).getInputMethodList();
      JSONArray localJSONArray = new JSONArray();
      paramContext = paramContext.getPackageManager();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localJSONArray.put(((InputMethodInfo)((Iterator)localObject).next()).loadLabel(paramContext).toString());
      }
      return localJSONArray;
    }
    catch (Throwable paramContext)
    {
      LogUtils.e(paramContext);
    }
    return null;
  }
  
  public static JSONArray i(Context paramContext)
  {
    try
    {
      if (!DeviceUtils.checkPermissions(paramContext, "android.permission.BLUETOOTH")) {
        return null;
      }
      paramContext = BluetoothAdapter.getDefaultAdapter();
      if (paramContext == null) {
        return null;
      }
      Object localObject = paramContext.getBondedDevices();
      if (localObject == null) {
        return null;
      }
      paramContext = new JSONArray();
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramContext.put(((BluetoothDevice)((Iterator)localObject).next()).getName());
      }
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      LogUtils.e(paramContext);
    }
    return null;
  }
  
  public static List<String> j(Context paramContext)
  {
    try
    {
      paramContext = (SensorManager)paramContext.getSystemService("sensor");
      if (paramContext == null) {
        return null;
      }
      Object localObject = paramContext.getSensorList(-1);
      if (localObject != null)
      {
        if (((List)localObject).size() == 0) {
          return null;
        }
        paramContext = new ArrayList();
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          paramContext.add(((Sensor)((Iterator)localObject).next()).getName());
        }
        return paramContext;
      }
      return null;
    }
    catch (Throwable paramContext)
    {
      LogUtils.e(paramContext);
    }
    return null;
  }
  
  public static String k(Context paramContext)
  {
    try
    {
      paramContext = MD5Utils.md5(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures[0].toByteArray());
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Set<String> l(Context paramContext)
  {
    Object localObject = n(paramContext);
    if ((localObject != null) && (((List)localObject).size() != 0))
    {
      paramContext = new HashSet();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramContext.add(((ScanResult)((Iterator)localObject).next()).SSID);
      }
      return paramContext;
    }
    return null;
  }
  
  public static String m(Context paramContext)
  {
    if (!DeviceUtils.checkPermissions(paramContext, "android.permission.READ_PHONE_STATE")) {
      return null;
    }
    if (Build.VERSION.SDK_INT < 21) {
      return null;
    }
    int i;
    if (Build.VERSION.SDK_INT > 21) {
      i = b(paramContext, 1);
    } else {
      i = c(paramContext, 1);
    }
    if (i != -1) {
      return a(paramContext, i);
    }
    return null;
  }
  
  private static List<ScanResult> n(Context paramContext)
  {
    try
    {
      if ((a.u) && (DeviceUtils.checkPermissions(paramContext, "android.permission.ACCESS_WIFI_STATE")))
      {
        if ((!DeviceUtils.checkPermissions(paramContext, "android.permission.ACCESS_FINE_LOCATION")) && (!DeviceUtils.checkPermissions(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))) {
          return null;
        }
        paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getScanResults();
        return paramContext;
      }
      return null;
    }
    catch (Throwable paramContext)
    {
      LogUtils.e(paramContext);
    }
    return null;
  }
}
