package m.framework.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class DeviceHelper
{
  private static DeviceHelper deviceHelper;
  private Context context;
  
  public DeviceHelper(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
  }
  
  public static DeviceHelper getInstance(Context paramContext)
  {
    if (deviceHelper == null) {
      deviceHelper = new DeviceHelper(paramContext);
    }
    return deviceHelper;
  }
  
  private boolean isFastMobileNetwork()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)this.context.getSystemService("phone");
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
    int i;
    if ((paramPackageInfo.applicationInfo.flags & 0x1) == 1)
    {
      i = 1;
      if ((paramPackageInfo.applicationInfo.flags & 0x80) != 1) {
        break label47;
      }
    }
    label47:
    for (int j = 1;; j = 0)
    {
      if ((i != 0) || (j != 0)) {
        break label52;
      }
      return false;
      i = 0;
      break;
    }
    label52:
    return true;
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
      paramString1.printStackTrace();
    }
    return str;
  }
  
  public String getAppName()
  {
    String str = this.context.getApplicationInfo().name;
    if (str != null) {
      return str;
    }
    int i = this.context.getApplicationInfo().labelRes;
    if (i > 0) {
      str = this.context.getString(i);
    }
    return str;
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
      localThrowable.printStackTrace();
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
      localThrowable.printStackTrace();
    }
    return "1.0";
  }
  
  public String getCarrier()
  {
    String str2 = ((TelephonyManager)this.context.getSystemService("phone")).getSimOperator();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "-1";
    }
    return str1;
  }
  
  public String getDeviceData()
  {
    return Base64AES(getModel() + "|" + getOSVersion() + "|" + getFactory() + "|" + getCarrier() + "|" + getScreenSize(), getDeviceKey().substring(0, 16));
  }
  
  public String getDeviceId()
  {
    Object localObject = (TelephonyManager)this.context.getSystemService("phone");
    if (localObject == null) {
      localObject = null;
    }
    do
    {
      String str2;
      String str1;
      do
      {
        return localObject;
        str2 = ((TelephonyManager)localObject).getDeviceId();
        str1 = "";
        if (str2 != null) {
          str1 = new String(str2).replace("0", "");
        }
        if (str2 == null) {
          break;
        }
        localObject = str2;
      } while (str1.length() > 0);
      localObject = str2;
    } while (Build.VERSION.SDK_INT < 9);
    try
    {
      localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public String getDeviceKey()
  {
    try
    {
      String str1 = getMacAddress();
      String str2 = getDeviceId();
      String str3 = getModel();
      str1 = Data.byteToHex(Data.SHA1(str1 + ":" + str2 + ":" + str3));
      return str1;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public String getFactory()
  {
    return Build.MANUFACTURER;
  }
  
  public ArrayList<HashMap<String, String>> getInstalledApp(boolean paramBoolean)
  {
    localArrayList = new ArrayList();
    try
    {
      PackageManager localPackageManager = this.context.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return localArrayList;
        }
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((paramBoolean) || (!isSystemApp(localPackageInfo)))
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("pkg", localPackageInfo.packageName);
          localHashMap.put("name", localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localHashMap.put("version", localPackageInfo.versionName);
          localArrayList.add(localHashMap);
        }
      }
      return localArrayList;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public String getMacAddress()
  {
    Object localObject = (WifiManager)this.context.getSystemService("wifi");
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
  
  public String getModel()
  {
    return Build.MODEL;
  }
  
  public String getNetworkOperator()
  {
    return ((TelephonyManager)this.context.getSystemService("phone")).getNetworkOperator();
  }
  
  public String getNetworkType()
  {
    Object localObject = (ConnectivityManager)this.context.getSystemService("connectivity");
    if (localObject == null) {}
    int i;
    do
    {
      do
      {
        return null;
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      } while ((localObject == null) || (!((NetworkInfo)localObject).isAvailable()));
      i = ((NetworkInfo)localObject).getType();
      if (1 == i) {
        return "wifi";
      }
    } while (i != 0);
    String str2 = Proxy.getDefaultHost();
    String str1 = "";
    localObject = str1;
    if (str2 != null)
    {
      localObject = str1;
      if (str2.length() > 0) {
        localObject = " wap";
      }
    }
    if (isFastMobileNetwork()) {}
    for (str1 = "3G";; str1 = "2G") {
      return str1 + (String)localObject;
    }
  }
  
  public String getOSVersion()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
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
    Object localObject = (ActivityManager)this.context.getSystemService("activity");
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
    for (;;)
    {
      if (i >= localJSONArray.length()) {
        return localStringBuilder.toString();
      }
      if (i > 0) {
        localStringBuilder.append(',');
      }
      localStringBuilder.append(String.valueOf(localJSONArray.get(i)));
      i += 1;
    }
  }
  
  public String getScreenSize()
  {
    DisplayMetrics localDisplayMetrics = this.context.getResources().getDisplayMetrics();
    if (this.context.getResources().getConfiguration().orientation == 1) {
      return localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels;
    }
    return localDisplayMetrics.heightPixels + "x" + localDisplayMetrics.widthPixels;
  }
  
  public String getSdcardPath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public boolean getSdcardState()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  public String getTopTaskPackageName()
  {
    try
    {
      String str = ((ActivityManager.RunningTaskInfo)((ActivityManager)this.context.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public boolean isRooted()
  {
    return false;
  }
}
