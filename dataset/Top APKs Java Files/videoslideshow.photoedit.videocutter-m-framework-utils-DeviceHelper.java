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
    default: 
      return false;
    case 15: 
      return true;
    case 14: 
      return true;
    case 13: 
      return true;
    case 12: 
      return true;
    case 11: 
      return false;
    case 10: 
      return true;
    case 9: 
      return true;
    case 8: 
      return true;
    case 7: 
      return false;
    case 6: 
      return true;
    case 5: 
      return true;
    case 4: 
      return false;
    case 3: 
      return true;
    case 2: 
      return false;
    case 1: 
      return false;
    }
    return false;
  }
  
  private boolean isSystemApp(PackageInfo paramPackageInfo)
  {
    int i;
    if ((paramPackageInfo.applicationInfo.flags & 0x1) == 1) {
      i = 1;
    } else {
      i = 0;
    }
    int j;
    if ((paramPackageInfo.applicationInfo.flags & 0x80) == 1) {
      j = 1;
    } else {
      j = 0;
    }
    return (i != 0) || (j != 0);
  }
  
  public String Base64AES(String paramString1, String paramString2)
  {
    String str = null;
    try
    {
      paramString1 = Base64.encodeToString(Data.AES128Encode(paramString2, paramString1), 0);
      str = paramString1;
      paramString2 = paramString1;
      if (paramString1.contains("\n"))
      {
        str = paramString1;
        paramString1 = paramString1.replace("\n", "");
        return paramString1;
      }
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
      paramString2 = str;
    }
    return paramString2;
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
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(getModel()));
    localStringBuilder.append("|");
    localStringBuilder.append(getOSVersion());
    localStringBuilder.append("|");
    localStringBuilder.append(getFactory());
    localStringBuilder.append("|");
    localStringBuilder.append(getCarrier());
    localStringBuilder.append("|");
    localStringBuilder.append(getScreenSize());
    return Base64AES(localStringBuilder.toString(), getDeviceKey().substring(0, 16));
  }
  
  public String getDeviceId()
  {
    Object localObject = (TelephonyManager)this.context.getSystemService("phone");
    if (localObject == null) {
      return null;
    }
    String str2 = ((TelephonyManager)localObject).getDeviceId();
    localObject = "";
    if (str2 != null) {
      localObject = new String(str2).replace("0", "");
    }
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (((String)localObject).length() > 0) {}
    }
    else
    {
      str1 = str2;
      if (Build.VERSION.SDK_INT >= 9) {
        try
        {
          localObject = Class.forName("android.os.SystemProperties");
          localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
          return localObject;
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          str1 = null;
        }
      }
    }
    return str1;
  }
  
  public String getDeviceKey()
  {
    try
    {
      Object localObject = getMacAddress();
      String str1 = getDeviceId();
      String str2 = getModel();
      localObject = new StringBuilder(String.valueOf(localObject));
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append(":");
      ((StringBuilder)localObject).append(str2);
      str1 = Data.byteToHex(Data.SHA1(((StringBuilder)localObject).toString()));
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
    if (localObject == null) {
      return null;
    }
    localObject = ((WifiManager)localObject).getConnectionInfo();
    if (localObject != null)
    {
      String str = ((WifiInfo)localObject).getMacAddress();
      localObject = str;
      if (str == null) {
        localObject = null;
      }
      return localObject;
    }
    return null;
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
    Object localObject1 = (ConnectivityManager)this.context.getSystemService("connectivity");
    if (localObject1 == null) {
      return null;
    }
    localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
    if (localObject1 != null)
    {
      if (!((NetworkInfo)localObject1).isAvailable()) {
        return null;
      }
      int i = ((NetworkInfo)localObject1).getType();
      if (1 == i) {
        return "wifi";
      }
      if (i == 0)
      {
        String str = Proxy.getDefaultHost();
        Object localObject2 = "";
        localObject1 = localObject2;
        if (str != null)
        {
          localObject1 = localObject2;
          if (str.length() > 0) {
            localObject1 = " wap";
          }
        }
        if (isFastMobileNetwork()) {
          localObject2 = "3G";
        } else {
          localObject2 = "2G";
        }
        localObject2 = new StringBuilder(String.valueOf(localObject2));
        ((StringBuilder)localObject2).append((String)localObject1);
        return ((StringBuilder)localObject2).toString();
      }
      return null;
    }
    return null;
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
    if (localObject == null) {
      return localJSONArray;
    }
    localObject = ((ActivityManager)localObject).getRunningAppProcesses();
    if (localObject == null) {
      return localJSONArray;
    }
    localObject = ((List)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return localJSONArray;
      }
      localJSONArray.put(((ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next()).processName);
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
    if (this.context.getResources().getConfiguration().orientation == 1)
    {
      localStringBuilder = new StringBuilder(String.valueOf(localDisplayMetrics.widthPixels));
      localStringBuilder.append("x");
      localStringBuilder.append(localDisplayMetrics.heightPixels);
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localDisplayMetrics.heightPixels));
    localStringBuilder.append("x");
    localStringBuilder.append(localDisplayMetrics.widthPixels);
    return localStringBuilder.toString();
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
