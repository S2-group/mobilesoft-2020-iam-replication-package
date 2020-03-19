package cn.sharesdk.framework.utils;

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
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

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
  
  private boolean s()
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
  
  public String a()
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
  
  public String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Base64.encodeToString(a.a(paramString2, paramString1), 0);
      paramString2 = paramString1;
      e.c(paramString2);
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
      while (localIterator.hasNext())
      {
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
      e.c(localThrowable);
    }
  }
  
  public String b()
  {
    return Build.MODEL;
  }
  
  public String c()
  {
    return Build.MANUFACTURER;
  }
  
  public String d()
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
        e.a(localThrowable);
        Object localObject2 = null;
      }
    }
  }
  
  public String e()
  {
    return a(b() + "|" + f() + "|" + c() + "|" + h() + "|" + g(), l().substring(0, 16));
  }
  
  public String f()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
  }
  
  public String g()
  {
    DisplayMetrics localDisplayMetrics = this.a.getResources().getDisplayMetrics();
    if (this.a.getResources().getConfiguration().orientation == 1) {
      return localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels;
    }
    return localDisplayMetrics.heightPixels + "x" + localDisplayMetrics.widthPixels;
  }
  
  public String h()
  {
    String str2 = ((TelephonyManager)this.a.getSystemService("phone")).getSimOperator();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "-1";
    }
    e.c("================= operator: " + str1, new Object[0]);
    return str1;
  }
  
  public String i()
  {
    Object localObject1 = (ConnectivityManager)this.a.getSystemService("connectivity");
    if (localObject1 == null) {
      return null;
    }
    localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
    if ((localObject1 == null) || (!((NetworkInfo)localObject1).isAvailable())) {
      return null;
    }
    int i = ((NetworkInfo)localObject1).getType();
    if (1 == i) {
      return "wifi";
    }
    if (i == 0)
    {
      Object localObject2 = Proxy.getDefaultHost();
      String str = "";
      localObject1 = str;
      if (localObject2 != null)
      {
        localObject1 = str;
        if (((String)localObject2).length() > 0) {
          localObject1 = " wap";
        }
      }
      localObject2 = new StringBuilder();
      if (s()) {}
      for (str = "3G";; str = "2G") {
        return str + (String)localObject1;
      }
    }
    return null;
  }
  
  public int j()
  {
    return 1;
  }
  
  public JSONArray k()
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
    while (((Iterator)localObject).hasNext()) {
      localJSONArray.put(((ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next()).processName);
    }
    return localJSONArray;
  }
  
  public String l()
  {
    try
    {
      String str1 = a();
      String str2 = d();
      String str3 = b();
      str1 = a.a(a.a(str1 + ":" + str2 + ":" + str3));
      return str1;
    }
    catch (Throwable localThrowable)
    {
      e.a(localThrowable);
    }
    return null;
  }
  
  public String m()
  {
    return this.a.getPackageName();
  }
  
  public String n()
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
  
  public int o()
  {
    try
    {
      int i = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Throwable localThrowable)
    {
      e.a(localThrowable);
    }
    return 0;
  }
  
  public String p()
  {
    try
    {
      String str = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionName;
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.a(localThrowable);
    }
    return "1.0";
  }
  
  public String q()
  {
    return ((TelephonyManager)this.a.getSystemService("phone")).getNetworkOperator();
  }
  
  public String r()
  {
    try
    {
      String str = ((ActivityManager.RunningTaskInfo)((ActivityManager)this.a.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
      return str;
    }
    catch (Throwable localThrowable)
    {
      e.c(localThrowable);
    }
    return null;
  }
}
