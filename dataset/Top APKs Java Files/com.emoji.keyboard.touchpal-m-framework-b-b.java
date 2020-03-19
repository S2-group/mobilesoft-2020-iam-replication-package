package m.framework.b;

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

public class b
{
  private static b b;
  private Context a;
  
  public b(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
  }
  
  public static b a(Context paramContext)
  {
    if (b == null) {
      b = new b(paramContext);
    }
    return b;
  }
  
  private boolean a(PackageInfo paramPackageInfo)
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
  
  private boolean w()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)this.a.getSystemService("phone");
    if (localTelephonyManager == null) {
      return false;
    }
    switch (localTelephonyManager.getNetworkType())
    {
    default: 
      return false;
    case 7: 
      return false;
    case 4: 
      return false;
    case 2: 
      return false;
    case 5: 
      return true;
    case 6: 
      return true;
    case 1: 
      return false;
    case 8: 
      return true;
    case 10: 
      return true;
    case 9: 
      return true;
    case 3: 
      return true;
    case 14: 
      return true;
    case 12: 
      return true;
    case 15: 
      return true;
    case 11: 
      return false;
    case 13: 
      return true;
    }
    return false;
  }
  
  public String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Base64.encodeToString(a.a(paramString2, paramString1), 0);
      paramString2 = paramString1;
      paramString2.printStackTrace();
    }
    catch (Throwable paramString2)
    {
      try
      {
        if (paramString1.contains("\n")) {
          paramString2 = paramString1.replace("\n", "");
        }
        return paramString2;
      }
      catch (Throwable paramString2)
      {
        for (;;) {}
      }
      paramString2 = paramString2;
      paramString1 = null;
    }
    return paramString1;
  }
  
  public ArrayList<HashMap<String, String>> a(boolean paramBoolean)
  {
    localArrayList = new ArrayList();
    try
    {
      PackageManager localPackageManager = this.a.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return localArrayList;
        }
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((paramBoolean) || (!a(localPackageInfo)))
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
  
  public boolean a()
  {
    return false;
  }
  
  public String b()
  {
    Object localObject = (WifiManager)this.a.getSystemService("wifi");
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
  
  public String c()
  {
    return Build.MODEL;
  }
  
  public String d()
  {
    return Build.MANUFACTURER;
  }
  
  public String e()
  {
    Object localObject1 = (TelephonyManager)this.a.getSystemService("phone");
    if (localObject1 == null) {
      return null;
    }
    String str2 = ((TelephonyManager)localObject1).getDeviceId();
    String str1 = "";
    if (str2 != null) {
      str1 = new String(str2).replace("0", "");
    }
    if (str2 != null)
    {
      localObject1 = str2;
      if (str1.length() > 0) {}
    }
    else
    {
      localObject1 = str2;
      if (Build.VERSION.SDK_INT < 9) {}
    }
    try
    {
      localObject1 = Class.forName("android.os.SystemProperties");
      localObject1 = (String)((Class)localObject1).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject1, new Object[] { "ro.serialno", "unknown" });
      return localObject1;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
        Object localObject2 = null;
      }
    }
  }
  
  public String f()
  {
    return a(c() + "|" + g() + "|" + d() + "|" + i() + "|" + h(), n().substring(0, 16));
  }
  
  public String g()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
  }
  
  public String h()
  {
    DisplayMetrics localDisplayMetrics = this.a.getResources().getDisplayMetrics();
    if (this.a.getResources().getConfiguration().orientation == 1) {
      return localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels;
    }
    return localDisplayMetrics.heightPixels + "x" + localDisplayMetrics.widthPixels;
  }
  
  public String i()
  {
    String str2 = ((TelephonyManager)this.a.getSystemService("phone")).getSimOperator();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "-1";
    }
    return str1;
  }
  
  public String j()
  {
    Object localObject = (ConnectivityManager)this.a.getSystemService("connectivity");
    if (localObject == null) {
      return null;
    }
    localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if ((localObject == null) || (!((NetworkInfo)localObject).isAvailable())) {
      return null;
    }
    int i = ((NetworkInfo)localObject).getType();
    if (1 == i) {
      return "wifi";
    }
    if (i == 0)
    {
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
      if (w()) {}
      for (str1 = "3G";; str1 = "2G") {
        return str1 + (String)localObject;
      }
    }
    return null;
  }
  
  public int k()
  {
    return 1;
  }
  
  public JSONArray l()
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject = (ActivityManager)this.a.getSystemService("activity");
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
  
  public String m()
    throws JSONException
  {
    JSONArray localJSONArray = l();
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
  
  public String n()
  {
    try
    {
      String str1 = b();
      String str2 = e();
      String str3 = c();
      str1 = a.a(a.a(str1 + ":" + str2 + ":" + str3));
      return str1;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public String o()
  {
    return this.a.getPackageName();
  }
  
  public String p()
  {
    String str = this.a.getApplicationInfo().name;
    if (str != null) {}
    int i;
    do
    {
      return str;
      i = this.a.getApplicationInfo().labelRes;
    } while (i <= 0);
    return this.a.getString(i);
  }
  
  public int q()
  {
    try
    {
      int i = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return 0;
  }
  
  public String r()
  {
    try
    {
      String str = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionName;
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return "1.0";
  }
  
  public String s()
  {
    return ((TelephonyManager)this.a.getSystemService("phone")).getNetworkOperator();
  }
  
  public String t()
  {
    try
    {
      String str = ((ActivityManager.RunningTaskInfo)((ActivityManager)this.a.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public boolean u()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  public String v()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
}
