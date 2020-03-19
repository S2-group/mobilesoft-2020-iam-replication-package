package com.mob.tools.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;

public class DeviceHelper
{
  private static DeviceHelper deviceHelper;
  private Context context;
  
  private DeviceHelper(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
  }
  
  public static DeviceHelper getInstance(Context paramContext)
  {
    if ((deviceHelper == null) && (paramContext != null)) {
      deviceHelper = new DeviceHelper(paramContext);
    }
    return deviceHelper;
  }
  
  private String getLocalDeviceKey()
    throws Throwable
  {
    if (!getSdcardState()) {}
    do
    {
      do
      {
        return null;
        localObject1 = new File(getSdcardPath(), "ShareSDK");
      } while (!((File)localObject1).exists());
      localObject1 = new File((File)localObject1, ".dk");
    } while (!((File)localObject1).exists());
    ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream((File)localObject1));
    Object localObject3 = localObjectInputStream.readObject();
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (localObject3 != null)
    {
      localObject1 = localObject2;
      if ((localObject3 instanceof char[])) {
        localObject1 = String.valueOf((char[])localObject3);
      }
    }
    localObjectInputStream.close();
    return localObject1;
  }
  
  private Object getSystemService(String paramString)
  {
    try
    {
      paramString = this.context.getSystemService(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      MobLog.getInstance().w(paramString);
    }
    return null;
  }
  
  private boolean is4GMobileNetwork()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null) {}
    while (localTelephonyManager.getNetworkType() != 13) {
      return false;
    }
    return true;
  }
  
  private boolean isFastMobileNetwork()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null) {
      return false;
    }
    switch (localTelephonyManager.getNetworkType())
    {
    case 0: 
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
    default: 
      return false;
    case 3: 
      return true;
    case 5: 
      return true;
    case 6: 
      return true;
    case 8: 
      return true;
    case 10: 
      return true;
    case 9: 
      return true;
    case 14: 
      return true;
    case 12: 
      return true;
    case 15: 
      return true;
    }
    return true;
  }
  
  private boolean isSystemApp(PackageInfo paramPackageInfo)
  {
    boolean bool = false;
    int i;
    if ((paramPackageInfo.applicationInfo.flags & 0x1) == 1)
    {
      i = 1;
      if ((paramPackageInfo.applicationInfo.flags & 0x80) != 1) {
        break label54;
      }
    }
    label54:
    for (int j = 1;; j = 0)
    {
      if ((i != 0) || (j != 0)) {
        bool = true;
      }
      return bool;
      i = 0;
      break;
    }
  }
  
  private void saveLocalDeviceKey(String paramString)
    throws Throwable
  {
    if (!getSdcardState()) {
      return;
    }
    Object localObject = new File(getSdcardPath(), "ShareSDK");
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdirs();
    }
    localObject = new File((File)localObject, ".dk");
    if (((File)localObject).exists()) {
      ((File)localObject).delete();
    }
    localObject = new ObjectOutputStream(new FileOutputStream((File)localObject));
    ((ObjectOutputStream)localObject).writeObject(paramString.toCharArray());
    ((ObjectOutputStream)localObject).flush();
    ((ObjectOutputStream)localObject).close();
  }
  
  public String Base64AES(String paramString1, String paramString2)
  {
    String str = null;
    try
    {
      paramString1 = Base64.encodeToString(Data.AES128Encode(paramString2, paramString1), 0);
      paramString2 = paramString1;
      str = paramString1;
      if (paramString1.contains("\n"))
      {
        str = paramString1;
        paramString2 = paramString1.replace("\n", "");
      }
      return paramString2;
    }
    catch (Throwable paramString1)
    {
      MobLog.getInstance().w(paramString1);
    }
    return str;
  }
  
  public boolean checkPermission(String paramString)
    throws Throwable
  {
    if (Build.VERSION.SDK_INT >= 23) {
      try
      {
        ReflectHelper.importClass("android.content.Context");
        paramString = (Integer)ReflectHelper.invokeInstanceMethod(this.context, "checkSelfPermission", new Object[] { paramString });
        if (paramString == null) {
          i = -1;
        } else {
          i = paramString.intValue();
        }
      }
      catch (Throwable paramString)
      {
        MobLog.getInstance().w(paramString);
        i = -1;
      }
    }
    this.context.checkPermission(paramString, Process.myPid(), Process.myUid());
    int i = this.context.getPackageManager().checkPermission(paramString, getPackageName());
    while (i != 0) {
      return false;
    }
    return true;
  }
  
  public String getAdvertisingID()
  {
    try
    {
      Object localObject1 = new Intent("com.google.android.gms.ads.identifier.service.START");
      ((Intent)localObject1).setPackage("com.google.android.gms");
      Object localObject2 = new GSConnection(null);
      this.context.bindService((Intent)localObject1, (ServiceConnection)localObject2, 1);
      Object localObject3 = ((GSConnection)localObject2).takeBinder();
      localObject1 = Parcel.obtain();
      localObject2 = Parcel.obtain();
      ((Parcel)localObject1).writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
      ((IBinder)localObject3).transact(1, (Parcel)localObject1, (Parcel)localObject2, 0);
      ((Parcel)localObject2).readException();
      localObject3 = ((Parcel)localObject2).readString();
      ((Parcel)localObject2).recycle();
      ((Parcel)localObject1).recycle();
      MobLog.getInstance().i("getAdvertisingID === " + (String)localObject3, new Object[0]);
      return localObject3;
    }
    catch (Throwable localThrowable)
    {
      MobLog.getInstance().d(localThrowable);
    }
    return null;
  }
  
  public String getAndroidID()
  {
    String str = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    MobLog.getInstance().i("getAndroidID === " + str, new Object[0]);
    return str;
  }
  
  public String getAppName()
  {
    String str = this.context.getApplicationInfo().name;
    if (str != null) {
      return str;
    }
    int i = this.context.getApplicationInfo().labelRes;
    if (i > 0) {}
    for (str = this.context.getString(i);; str = String.valueOf(this.context.getApplicationInfo().nonLocalizedLabel)) {
      return str;
    }
  }
  
  public int getAppVersion()
  {
    try
    {
      int i = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Throwable localThrowable)
    {
      MobLog.getInstance().d(localThrowable);
    }
    return 0;
  }
  
  public String getAppVersionName()
  {
    try
    {
      String str = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
      return str;
    }
    catch (Throwable localThrowable)
    {
      MobLog.getInstance().d(localThrowable);
    }
    return "1.0";
  }
  
  public String getBluetoothName()
  {
    try
    {
      Object localObject = BluetoothAdapter.getDefaultAdapter();
      if ((localObject != null) && (checkPermission("android.permission.BLUETOOTH")))
      {
        localObject = ((BluetoothAdapter)localObject).getName();
        return localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      MobLog.getInstance().d(localThrowable);
    }
    return null;
  }
  
  public String getBssid()
  {
    Object localObject = (WifiManager)getSystemService("wifi");
    if (localObject == null) {}
    do
    {
      return null;
      localObject = ((WifiManager)localObject).getConnectionInfo();
    } while (localObject == null);
    String str = ((WifiInfo)localObject).getBSSID();
    localObject = str;
    if (str == null) {
      localObject = null;
    }
    return localObject;
  }
  
  public String getCarrier()
  {
    Object localObject = (TelephonyManager)getSystemService("phone");
    if (localObject == null) {
      localObject = "-1";
    }
    String str;
    do
    {
      return localObject;
      str = ((TelephonyManager)localObject).getSimOperator();
      localObject = str;
    } while (!TextUtils.isEmpty(str));
    return "-1";
  }
  
  public String getCarrierName()
  {
    Object localObject = (TelephonyManager)getSystemService("phone");
    if (localObject == null) {
      localObject = null;
    }
    for (;;)
    {
      return localObject;
      try
      {
        if (checkPermission("android.permission.READ_PHONE_STATE"))
        {
          localObject = ((TelephonyManager)localObject).getSimOperatorName();
          boolean bool = TextUtils.isEmpty((CharSequence)localObject);
          if (!bool) {
            continue;
          }
          return null;
        }
      }
      catch (Throwable localThrowable)
      {
        MobLog.getInstance().w(localThrowable);
      }
    }
    return null;
  }
  
  public String getCharAndNumr(int paramInt)
  {
    long l1 = System.currentTimeMillis();
    long l2 = SystemClock.elapsedRealtime();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(l1 ^ l2);
    Random localRandom = new Random();
    int i = 0;
    if (i < paramInt)
    {
      String str;
      if (localRandom.nextInt(2) % 2 == 0)
      {
        str = "char";
        label60:
        if (!"char".equalsIgnoreCase(str)) {
          break label106;
        }
        localStringBuffer.insert(i + 1, (char)(localRandom.nextInt(26) + 97));
      }
      for (;;)
      {
        i += 1;
        break;
        str = "num";
        break label60;
        label106:
        localStringBuffer.insert(localStringBuffer.length(), localRandom.nextInt(10));
      }
    }
    return localStringBuffer.toString().substring(0, 40);
  }
  
  public String getDetailNetworkTypeForStatic()
  {
    String str2 = getNetworkType().toLowerCase();
    String str1;
    if ((TextUtils.isEmpty(str2)) || ("none".equals(str2))) {
      str1 = "none";
    }
    do
    {
      return str1;
      if (str2.startsWith("wifi")) {
        return "wifi";
      }
      if (str2.startsWith("4g")) {
        return "4g";
      }
      if (str2.startsWith("3g")) {
        return "3g";
      }
      if (str2.startsWith("2g")) {
        return "2g";
      }
      str1 = str2;
    } while (!str2.startsWith("bluetooth"));
    return "bluetooth";
  }
  
  public String getDeviceData()
  {
    return Base64AES(getModel() + "|" + getOSVersionInt() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize(), getDeviceKey().substring(0, 15));
  }
  
  public String getDeviceDataNotAES()
  {
    return getModel() + "|" + getOSVersion() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize();
  }
  
  public String getDeviceId()
  {
    String str2 = getIMEI();
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      str1 = str2;
      if (Build.VERSION.SDK_INT >= 9) {
        str1 = getSerialno();
      }
    }
    return str1;
  }
  
  public String getDeviceKey()
  {
    String str2;
    try
    {
      String str1 = getLocalDeviceKey();
      if (str1 != null) {
        return str1;
      }
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        MobLog.getInstance().w(localThrowable1);
        str2 = null;
      }
    }
    try
    {
      str2 = getMacAddress();
      str3 = getDeviceId();
      String str4 = getModel();
      str2 = Data.byteToHex(Data.SHA1(str2 + ":" + str3 + ":" + str4));
      str3 = str2;
      if (TextUtils.isEmpty(str2)) {
        str3 = getCharAndNumr(40);
      }
      if (str3 == null) {}
    }
    catch (Throwable localThrowable2)
    {
      try
      {
        String str3;
        saveLocalDeviceKey(str3);
        return str3;
        localThrowable2 = localThrowable2;
        MobLog.getInstance().d(localThrowable2);
        Object localObject = null;
      }
      catch (Throwable localThrowable3)
      {
        for (;;)
        {
          MobLog.getInstance().w(localThrowable3);
        }
      }
    }
  }
  
  public String getIMEI()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    Object localObject3;
    if (localTelephonyManager == null) {
      localObject3 = null;
    }
    for (;;)
    {
      return localObject3;
      localObject3 = null;
      Object localObject1 = localObject3;
      try
      {
        if (checkPermission("android.permission.READ_PHONE_STATE")) {
          localObject1 = localTelephonyManager.getDeviceId();
        }
        localObject3 = localObject1;
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          continue;
        }
        return null;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          MobLog.getInstance().w(localThrowable);
          Object localObject2 = localObject3;
        }
      }
    }
  }
  
  public String getIMSI()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    Object localObject3;
    if (localTelephonyManager == null) {
      localObject3 = null;
    }
    for (;;)
    {
      return localObject3;
      localObject3 = null;
      Object localObject1 = localObject3;
      try
      {
        if (checkPermission("android.permission.READ_PHONE_STATE")) {
          localObject1 = localTelephonyManager.getSubscriberId();
        }
        localObject3 = localObject1;
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          continue;
        }
        return null;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          MobLog.getInstance().w(localThrowable);
          Object localObject2 = localObject3;
        }
      }
    }
  }
  
  public String getIPAddress()
  {
    try
    {
      if (checkPermission("android.permission.INTERNET"))
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
        } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
        Object localObject = localInetAddress.getHostAddress();
        return localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      MobLog.getInstance().w(localThrowable);
    }
    return "0.0.0.0";
  }
  
  /* Error */
  public java.util.ArrayList<java.util.HashMap<String, String>> getInstalledApp(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	com/mob/tools/utils/DeviceHelper:context	Landroid/content/Context;
    //   4: invokevirtual 219	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: astore 4
    //   9: aload 4
    //   11: iconst_0
    //   12: invokevirtual 563	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   15: astore_2
    //   16: new 565	java/util/ArrayList
    //   19: dup
    //   20: invokespecial 566	java/util/ArrayList:<init>	()V
    //   23: astore_3
    //   24: aload_2
    //   25: invokeinterface 572 1 0
    //   30: astore 5
    //   32: aload_3
    //   33: astore_2
    //   34: aload 5
    //   36: invokeinterface 577 1 0
    //   41: ifeq +111 -> 152
    //   44: aload 5
    //   46: invokeinterface 580 1 0
    //   51: checkcast 115	android/content/pm/PackageInfo
    //   54: astore_2
    //   55: iload_1
    //   56: ifne +11 -> 67
    //   59: aload_0
    //   60: aload_2
    //   61: invokespecial 582	com/mob/tools/utils/DeviceHelper:isSystemApp	(Landroid/content/pm/PackageInfo;)Z
    //   64: ifne -32 -> 32
    //   67: new 584	java/util/HashMap
    //   70: dup
    //   71: invokespecial 585	java/util/HashMap:<init>	()V
    //   74: astore 6
    //   76: aload 6
    //   78: ldc_w 587
    //   81: aload_2
    //   82: getfield 590	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   85: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   88: pop
    //   89: aload 6
    //   91: ldc_w 595
    //   94: aload_2
    //   95: getfield 119	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   98: aload 4
    //   100: invokevirtual 599	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   103: invokeinterface 602 1 0
    //   108: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   111: pop
    //   112: aload 6
    //   114: ldc_w 604
    //   117: aload_2
    //   118: getfield 346	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   121: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   124: pop
    //   125: aload_3
    //   126: aload 6
    //   128: invokevirtual 607	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   131: pop
    //   132: goto -100 -> 32
    //   135: astore_2
    //   136: invokestatic 94	com/mob/tools/MobLog:getInstance	()Lcom/mob/tools/log/NLog;
    //   139: aload_2
    //   140: invokevirtual 100	com/mob/tools/log/NLog:w	(Ljava/lang/Throwable;)I
    //   143: pop
    //   144: new 565	java/util/ArrayList
    //   147: dup
    //   148: invokespecial 566	java/util/ArrayList:<init>	()V
    //   151: astore_2
    //   152: aload_2
    //   153: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	this	DeviceHelper
    //   0	154	1	paramBoolean	boolean
    //   15	103	2	localObject	Object
    //   135	5	2	localThrowable	Throwable
    //   151	2	2	localArrayList1	java.util.ArrayList
    //   23	103	3	localArrayList2	java.util.ArrayList
    //   7	92	4	localPackageManager	PackageManager
    //   30	15	5	localIterator	Iterator
    //   74	53	6	localHashMap	java.util.HashMap
    // Exception table:
    //   from	to	target	type
    //   0	32	135	java/lang/Throwable
    //   34	55	135	java/lang/Throwable
    //   59	67	135	java/lang/Throwable
    //   67	132	135	java/lang/Throwable
  }
  
  public String getLine1Number()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null) {
      return "-1";
    }
    return localTelephonyManager.getLine1Number();
  }
  
  public float[] getLocation()
  {
    do
    {
      try
      {
        boolean bool = checkPermission("android.permission.ACCESS_FINE_LOCATION");
        if (!bool) {
          return null;
        }
      }
      catch (Throwable localThrowable1)
      {
        MobLog.getInstance().w(localThrowable1);
        return null;
      }
    } while ((LocationManager)getSystemService("location") == null);
    try
    {
      float[] arrayOfFloat = new LocationHelper().getLocation(this.context, 30, 30);
      return arrayOfFloat;
    }
    catch (Throwable localThrowable2)
    {
      MobLog.getInstance().w(localThrowable2);
    }
    return null;
  }
  
  public String getMCC()
  {
    if ((TelephonyManager)getSystemService("phone") == null) {}
    String str;
    do
    {
      return null;
      str = getIMSI();
    } while ((str == null) || (str.length() < 3));
    return str.substring(0, 3);
  }
  
  public String getMNC()
  {
    if ((TelephonyManager)getSystemService("phone") == null) {}
    String str;
    do
    {
      return null;
      str = getIMSI();
    } while ((str == null) || (str.length() < 5));
    return str.substring(3, 5);
  }
  
  public String getMacAddress()
  {
    Object localObject = (WifiManager)getSystemService("wifi");
    if (localObject == null) {}
    do
    {
      return null;
      localObject = ((WifiManager)localObject).getConnectionInfo();
    } while (localObject == null);
    String str = ((WifiInfo)localObject).getMacAddress();
    localObject = str;
    if (str == null) {
      localObject = null;
    }
    return localObject;
  }
  
  public String getManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  public String getMime()
  {
    return getIMEI();
  }
  
  public String getModel()
  {
    return Build.MODEL;
  }
  
  public String getNetworkOperator()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null) {
      return null;
    }
    return localTelephonyManager.getNetworkOperator();
  }
  
  public String getNetworkType()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)getSystemService("connectivity");
    if (localConnectivityManager == null) {
      return "none";
    }
    try
    {
      if (!checkPermission("android.permission.ACCESS_NETWORK_STATE")) {
        return "none";
      }
    }
    catch (Throwable localThrowable)
    {
      MobLog.getInstance().w(localThrowable);
      return "none";
    }
    NetworkInfo localNetworkInfo = localThrowable.getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable())) {
      return "none";
    }
    int i = localNetworkInfo.getType();
    switch (i)
    {
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    default: 
      return String.valueOf(i);
    case 1: 
      return "wifi";
    case 0: 
      if (is4GMobileNetwork()) {
        return "4G";
      }
      if (isFastMobileNetwork()) {
        return "3G";
      }
      return "2G";
    case 7: 
      return "bluetooth";
    case 8: 
      return "dummy";
    case 9: 
      return "ethernet";
    }
    return "wimax";
  }
  
  public String getNetworkTypeForStatic()
  {
    String str = getNetworkType().toLowerCase();
    if ((TextUtils.isEmpty(str)) || ("none".equals(str))) {
      return "none";
    }
    if ((str.startsWith("4g")) || (str.startsWith("3g")) || (str.startsWith("2g"))) {
      return "cell";
    }
    if (str.startsWith("wifi")) {
      return "wifi";
    }
    return "other";
  }
  
  public String getOSCountry()
  {
    return Locale.getDefault().getCountry();
  }
  
  public String getOSLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public String getOSVersion()
  {
    return String.valueOf(getOSVersionInt());
  }
  
  public int getOSVersionInt()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public String getOSVersionName()
  {
    return Build.VERSION.RELEASE;
  }
  
  public String getPackageName()
  {
    return this.context.getPackageName();
  }
  
  public int getPlatformCode()
  {
    return 1;
  }
  
  public JSONArray getRunningApp()
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject = (ActivityManager)getSystemService("activity");
    if (localObject == null) {}
    for (;;)
    {
      return localJSONArray;
      localObject = ((ActivityManager)localObject).getRunningAppProcesses();
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          localJSONArray.put(((ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next()).processName);
        }
      }
    }
  }
  
  public String getRunningAppStr()
    throws JSONException
  {
    JSONArray localJSONArray = getRunningApp();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < localJSONArray.length())
    {
      if (i > 0) {
        localStringBuilder.append(',');
      }
      localStringBuilder.append(String.valueOf(localJSONArray.get(i)));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public String getSSID()
  {
    Object localObject = (WifiManager)getSystemService("wifi");
    if (localObject == null) {}
    do
    {
      return null;
      localObject = ((WifiManager)localObject).getConnectionInfo();
    } while (localObject == null);
    String str = ((WifiInfo)localObject).getSSID().replace("\"", "");
    localObject = str;
    if (str == null) {
      localObject = null;
    }
    return localObject;
  }
  
  public String getScreenSize()
  {
    int[] arrayOfInt = R.getScreenSize(this.context);
    if (this.context.getResources().getConfiguration().orientation == 1) {
      return arrayOfInt[0] + "x" + arrayOfInt[1];
    }
    return arrayOfInt[1] + "x" + arrayOfInt[0];
  }
  
  public String getSdcardPath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public boolean getSdcardState()
  {
    try
    {
      boolean bool = "mounted".equals(Environment.getExternalStorageState());
      return bool;
    }
    catch (Throwable localThrowable)
    {
      MobLog.getInstance().w(localThrowable);
    }
    return false;
  }
  
  public String getSerialno()
  {
    Object localObject = null;
    if (Build.VERSION.SDK_INT >= 9) {}
    try
    {
      localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      MobLog.getInstance().d(localThrowable);
    }
    return null;
  }
  
  public String getSignMD5()
  {
    try
    {
      String str = Data.MD5(this.context.getPackageManager().getPackageInfo(getPackageName(), 64).signatures[0].toByteArray());
      return str;
    }
    catch (Exception localException)
    {
      MobLog.getInstance().w(localException);
    }
    return null;
  }
  
  public String getSimSerialNumber()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null) {
      return "-1";
    }
    return localTelephonyManager.getSimSerialNumber();
  }
  
  /* Error */
  public String getTopTaskPackageName()
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 824
    //   4: invokevirtual 359	com/mob/tools/utils/DeviceHelper:checkPermission	(Ljava/lang/String;)Z
    //   7: istore_1
    //   8: iload_1
    //   9: ifeq +99 -> 108
    //   12: aload_0
    //   13: ldc_w 711
    //   16: invokespecial 104	com/mob/tools/utils/DeviceHelper:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   19: checkcast 713	android/app/ActivityManager
    //   22: astore_2
    //   23: aload_2
    //   24: ifnonnull +19 -> 43
    //   27: aconst_null
    //   28: areturn
    //   29: astore_2
    //   30: invokestatic 94	com/mob/tools/MobLog:getInstance	()Lcom/mob/tools/log/NLog;
    //   33: aload_2
    //   34: invokevirtual 100	com/mob/tools/log/NLog:w	(Ljava/lang/Throwable;)I
    //   37: pop
    //   38: iconst_0
    //   39: istore_1
    //   40: goto -32 -> 8
    //   43: getstatic 186	android/os/Build$VERSION:SDK_INT	I
    //   46: bipush 20
    //   48: if_icmpgt +24 -> 72
    //   51: aload_2
    //   52: iconst_1
    //   53: invokevirtual 827	android/app/ActivityManager:getRunningTasks	(I)Ljava/util/List;
    //   56: iconst_0
    //   57: invokeinterface 828 2 0
    //   62: checkcast 830	android/app/ActivityManager$RunningTaskInfo
    //   65: getfield 834	android/app/ActivityManager$RunningTaskInfo:topActivity	Landroid/content/ComponentName;
    //   68: invokevirtual 837	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   71: areturn
    //   72: aload_2
    //   73: invokevirtual 717	android/app/ActivityManager:getRunningAppProcesses	()Ljava/util/List;
    //   76: iconst_0
    //   77: invokeinterface 828 2 0
    //   82: checkcast 719	android/app/ActivityManager$RunningAppProcessInfo
    //   85: getfield 722	android/app/ActivityManager$RunningAppProcessInfo:processName	Ljava/lang/String;
    //   88: ldc_w 508
    //   91: invokevirtual 841	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   94: iconst_0
    //   95: aaload
    //   96: astore_2
    //   97: aload_2
    //   98: areturn
    //   99: astore_2
    //   100: invokestatic 94	com/mob/tools/MobLog:getInstance	()Lcom/mob/tools/log/NLog;
    //   103: aload_2
    //   104: invokevirtual 100	com/mob/tools/log/NLog:w	(Ljava/lang/Throwable;)I
    //   107: pop
    //   108: aconst_null
    //   109: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	110	0	this	DeviceHelper
    //   7	33	1	bool	boolean
    //   22	2	2	localActivityManager	ActivityManager
    //   29	44	2	localThrowable1	Throwable
    //   96	2	2	str	String
    //   99	5	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	8	29	java/lang/Throwable
    //   12	23	99	java/lang/Throwable
    //   43	72	99	java/lang/Throwable
    //   72	97	99	java/lang/Throwable
  }
  
  public void hideSoftInput(View paramView)
  {
    Object localObject = getSystemService("input_method");
    if (localObject == null) {
      return;
    }
    ((InputMethodManager)localObject).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public boolean isMainProcess(int paramInt)
  {
    Object localObject2 = null;
    Object localObject1 = (ActivityManager)getSystemService("activity");
    if (((ActivityManager)localObject1).getRunningAppProcesses() == null) {
      return paramInt <= 0;
    }
    if (paramInt <= 0) {
      paramInt = Process.myPid();
    }
    for (;;)
    {
      Iterator localIterator = ((ActivityManager)localObject1).getRunningAppProcesses().iterator();
      do
      {
        localObject1 = localObject2;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      } while (((ActivityManager.RunningAppProcessInfo)localObject1).pid != paramInt);
      localObject1 = ((ActivityManager.RunningAppProcessInfo)localObject1).processName;
      return getPackageName().equals(localObject1);
    }
  }
  
  public boolean isMainProcess(Context paramContext, int paramInt)
  {
    return isMainProcess(paramInt);
  }
  
  public boolean isRooted()
  {
    return false;
  }
  
  /* Error */
  public java.util.HashMap<String, String> ping(String paramString, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: new 565	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 566	java/util/ArrayList:<init>	()V
    //   7: astore 13
    //   9: new 279	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 280	java/lang/StringBuilder:<init>	()V
    //   16: ldc_w 869
    //   19: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: iload_2
    //   23: invokevirtual 475	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   26: ldc_w 871
    //   29: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: iload_3
    //   33: invokevirtual 475	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   36: ldc_w 873
    //   39: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: aload_1
    //   43: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual 289	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: astore 11
    //   51: invokestatic 879	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   54: aload 11
    //   56: invokevirtual 883	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   59: astore 14
    //   61: new 885	java/io/BufferedReader
    //   64: dup
    //   65: new 887	java/io/InputStreamReader
    //   68: dup
    //   69: aload 14
    //   71: invokevirtual 893	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   74: invokespecial 894	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   77: invokespecial 897	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   80: astore 15
    //   82: aload 15
    //   84: invokevirtual 900	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   87: astore 11
    //   89: aload 11
    //   91: ifnull +315 -> 406
    //   94: aload 11
    //   96: new 279	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 280	java/lang/StringBuilder:<init>	()V
    //   103: iload_3
    //   104: bipush 8
    //   106: iadd
    //   107: invokevirtual 475	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   110: ldc_w 902
    //   113: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 289	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: invokevirtual 455	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   122: ifeq +75 -> 197
    //   125: aload 11
    //   127: ldc_w 904
    //   130: invokevirtual 907	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   133: ifeq +74 -> 207
    //   136: aload 11
    //   138: iconst_0
    //   139: aload 11
    //   141: invokevirtual 630	java/lang/String:length	()I
    //   144: iconst_2
    //   145: isub
    //   146: invokevirtual 440	java/lang/String:substring	(II)Ljava/lang/String;
    //   149: invokevirtual 910	java/lang/String:trim	()Ljava/lang/String;
    //   152: astore 12
    //   154: aload 12
    //   156: ldc_w 912
    //   159: invokevirtual 916	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   162: istore 9
    //   164: iload 9
    //   166: ifle +31 -> 197
    //   169: aload 12
    //   171: iload 9
    //   173: iconst_5
    //   174: iadd
    //   175: invokevirtual 918	java/lang/String:substring	(I)Ljava/lang/String;
    //   178: invokevirtual 910	java/lang/String:trim	()Ljava/lang/String;
    //   181: astore 11
    //   183: aload 13
    //   185: aload 11
    //   187: invokestatic 924	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   190: invokestatic 927	java/lang/Float:valueOf	(F)Ljava/lang/Float;
    //   193: invokevirtual 607	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   196: pop
    //   197: aload 15
    //   199: invokevirtual 900	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   202: astore 11
    //   204: goto -115 -> 89
    //   207: aload 11
    //   209: astore 12
    //   211: aload 11
    //   213: ldc_w 929
    //   216: invokevirtual 907	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   219: ifeq -65 -> 154
    //   222: new 279	java/lang/StringBuilder
    //   225: dup
    //   226: invokespecial 280	java/lang/StringBuilder:<init>	()V
    //   229: aload 11
    //   231: iconst_0
    //   232: aload 11
    //   234: invokevirtual 630	java/lang/String:length	()I
    //   237: iconst_1
    //   238: isub
    //   239: invokevirtual 440	java/lang/String:substring	(II)Ljava/lang/String;
    //   242: invokevirtual 910	java/lang/String:trim	()Ljava/lang/String;
    //   245: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: ldc_w 931
    //   251: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: invokevirtual 289	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: astore 12
    //   259: goto -105 -> 154
    //   262: astore 11
    //   264: invokestatic 94	com/mob/tools/MobLog:getInstance	()Lcom/mob/tools/log/NLog;
    //   267: aload 11
    //   269: invokevirtual 100	com/mob/tools/log/NLog:w	(Ljava/lang/Throwable;)I
    //   272: pop
    //   273: goto -76 -> 197
    //   276: astore 11
    //   278: aload 11
    //   280: invokevirtual 934	java/lang/Throwable:printStackTrace	()V
    //   283: aload 13
    //   285: invokevirtual 937	java/util/ArrayList:size	()I
    //   288: istore 9
    //   290: aload 13
    //   292: invokevirtual 937	java/util/ArrayList:size	()I
    //   295: istore 10
    //   297: fconst_0
    //   298: fstore 6
    //   300: fconst_0
    //   301: fstore 5
    //   303: fconst_0
    //   304: fstore 8
    //   306: fconst_0
    //   307: fstore 4
    //   309: fconst_0
    //   310: fstore 7
    //   312: iload 9
    //   314: ifle +121 -> 435
    //   317: ldc_w 938
    //   320: fstore 5
    //   322: iconst_0
    //   323: istore_3
    //   324: fload 8
    //   326: fstore 4
    //   328: fload 7
    //   330: fstore 6
    //   332: iload_3
    //   333: iload 9
    //   335: if_icmpge +80 -> 415
    //   338: aload 13
    //   340: iload_3
    //   341: invokevirtual 939	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   344: checkcast 920	java/lang/Float
    //   347: invokevirtual 943	java/lang/Float:floatValue	()F
    //   350: fstore 7
    //   352: fload 5
    //   354: fstore 8
    //   356: fload 7
    //   358: fload 5
    //   360: fcmpg
    //   361: ifge +7 -> 368
    //   364: fload 7
    //   366: fstore 8
    //   368: fload 4
    //   370: fstore 5
    //   372: fload 7
    //   374: fload 4
    //   376: fcmpl
    //   377: ifle +7 -> 384
    //   380: fload 7
    //   382: fstore 5
    //   384: fload 6
    //   386: fload 7
    //   388: fadd
    //   389: fstore 6
    //   391: iload_3
    //   392: iconst_1
    //   393: iadd
    //   394: istore_3
    //   395: fload 5
    //   397: fstore 4
    //   399: fload 8
    //   401: fstore 5
    //   403: goto -71 -> 332
    //   406: aload 14
    //   408: invokevirtual 946	java/lang/Process:waitFor	()I
    //   411: pop
    //   412: goto -129 -> 283
    //   415: fload 6
    //   417: iload 9
    //   419: i2f
    //   420: fdiv
    //   421: fstore 7
    //   423: fload 5
    //   425: fstore 6
    //   427: fload 4
    //   429: fstore 5
    //   431: fload 7
    //   433: fstore 4
    //   435: new 584	java/util/HashMap
    //   438: dup
    //   439: invokespecial 585	java/util/HashMap:<init>	()V
    //   442: astore 11
    //   444: aload 11
    //   446: ldc_w 948
    //   449: aload_1
    //   450: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   453: pop
    //   454: aload 11
    //   456: ldc_w 950
    //   459: iload_2
    //   460: invokestatic 664	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   463: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   466: pop
    //   467: aload 11
    //   469: ldc_w 952
    //   472: iload 9
    //   474: invokestatic 664	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   477: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   480: pop
    //   481: aload 11
    //   483: ldc_w 954
    //   486: iload_2
    //   487: iload 10
    //   489: isub
    //   490: invokestatic 664	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   493: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   496: pop
    //   497: aload 11
    //   499: ldc_w 956
    //   502: fload 6
    //   504: invokestatic 959	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   507: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   510: pop
    //   511: aload 11
    //   513: ldc_w 961
    //   516: fload 5
    //   518: invokestatic 959	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   521: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   524: pop
    //   525: aload 11
    //   527: ldc_w 963
    //   530: fload 4
    //   532: invokestatic 959	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   535: invokevirtual 594	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   538: pop
    //   539: aload 11
    //   541: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	542	0	this	DeviceHelper
    //   0	542	1	paramString	String
    //   0	542	2	paramInt1	int
    //   0	542	3	paramInt2	int
    //   307	224	4	f1	float
    //   301	216	5	f2	float
    //   298	205	6	f3	float
    //   310	122	7	f4	float
    //   304	96	8	f5	float
    //   162	311	9	i	int
    //   295	195	10	j	int
    //   49	184	11	str1	String
    //   262	6	11	localThrowable1	Throwable
    //   276	3	11	localThrowable2	Throwable
    //   442	98	11	localHashMap	java.util.HashMap
    //   152	106	12	str2	String
    //   7	332	13	localArrayList	java.util.ArrayList
    //   59	348	14	localProcess	Process
    //   80	118	15	localBufferedReader	java.io.BufferedReader
    // Exception table:
    //   from	to	target	type
    //   183	197	262	java/lang/Throwable
    //   9	89	276	java/lang/Throwable
    //   94	154	276	java/lang/Throwable
    //   154	164	276	java/lang/Throwable
    //   169	183	276	java/lang/Throwable
    //   197	204	276	java/lang/Throwable
    //   211	259	276	java/lang/Throwable
    //   264	273	276	java/lang/Throwable
    //   406	412	276	java/lang/Throwable
  }
  
  public void showSoftInput(View paramView)
  {
    Object localObject = getSystemService("input_method");
    if (localObject == null) {
      return;
    }
    ((InputMethodManager)localObject).toggleSoftInputFromWindow(paramView.getWindowToken(), 2, 0);
  }
  
  private class GSConnection
    implements ServiceConnection
  {
    boolean got = false;
    private final BlockingQueue<IBinder> iBinders = new LinkedBlockingQueue();
    
    private GSConnection() {}
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      try
      {
        this.iBinders.put(paramIBinder);
        return;
      }
      catch (Throwable paramComponentName)
      {
        MobLog.getInstance().w(paramComponentName);
      }
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName) {}
    
    public IBinder takeBinder()
      throws InterruptedException
    {
      if (this.got) {
        throw new IllegalStateException();
      }
      this.got = true;
      return (IBinder)this.iBinders.poll(1500L, TimeUnit.MILLISECONDS);
    }
  }
}
