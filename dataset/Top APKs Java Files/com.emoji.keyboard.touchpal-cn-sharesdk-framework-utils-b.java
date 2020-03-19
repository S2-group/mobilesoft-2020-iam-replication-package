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
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
  
  private Object b(String paramString)
  {
    try
    {
      paramString = this.a.getSystemService(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      e.b(paramString);
    }
    return null;
  }
  
  private void c(String paramString)
  {
    if (!v()) {
      return;
    }
    Object localObject = new File(w(), "ShareSDK");
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
  
  private boolean x()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)b("phone");
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
  
  private String y()
  {
    if (!v()) {}
    do
    {
      do
      {
        return null;
        localObject = new File(w(), "ShareSDK");
      } while (!((File)localObject).exists());
      localObject = new File((File)localObject, ".dk");
    } while (!((File)localObject).exists());
    ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream((File)localObject));
    Object localObject = localObjectInputStream.readObject();
    if ((localObject != null) && ((localObject instanceof char[]))) {}
    for (localObject = String.valueOf((char[])localObject);; localObject = null)
    {
      localObjectInputStream.close();
      return localObject;
    }
  }
  
  public String a()
  {
    Object localObject = (WifiManager)b("wifi");
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
      e.b(paramString2);
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
      e.b(localThrowable);
    }
  }
  
  public boolean a(String paramString)
  {
    return this.a.getPackageManager().checkPermission(paramString, p()) == 0;
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
    Object localObject1 = (TelephonyManager)b("phone");
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
    return a(b() + "|" + g() + "|" + c() + "|" + j() + "|" + i(), o().substring(0, 16));
  }
  
  public String f()
  {
    return b() + "|" + g() + "|" + c() + "|" + j() + "|" + i();
  }
  
  public String g()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
  }
  
  public String h()
  {
    return Build.VERSION.RELEASE;
  }
  
  public String i()
  {
    DisplayMetrics localDisplayMetrics = this.a.getResources().getDisplayMetrics();
    if (this.a.getResources().getConfiguration().orientation == 1) {
      return localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels;
    }
    return localDisplayMetrics.heightPixels + "x" + localDisplayMetrics.widthPixels;
  }
  
  public String j()
  {
    Object localObject = (TelephonyManager)b("phone");
    if (localObject == null) {
      return "-1";
    }
    String str = ((TelephonyManager)localObject).getSimOperator();
    localObject = str;
    if (TextUtils.isEmpty(str)) {
      localObject = "-1";
    }
    e.c("================= operator: " + (String)localObject, new Object[0]);
    return localObject;
  }
  
  public String k()
  {
    Object localObject1 = (ConnectivityManager)b("connectivity");
    if (localObject1 == null) {
      return "none";
    }
    localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
    if ((localObject1 == null) || (!((NetworkInfo)localObject1).isAvailable())) {
      return "none";
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
      if (x()) {}
      for (str = "3G";; str = "2G") {
        return str + (String)localObject1;
      }
    }
    return "none";
  }
  
  public String l()
  {
    String str2 = k();
    String str1;
    if (TextUtils.isEmpty(str2)) {
      str1 = "none";
    }
    do
    {
      do
      {
        return str1;
        str1 = str2;
      } while ("wifi".equals(str2));
      str1 = str2;
    } while ("none".equals(str2));
    return "cell";
  }
  
  public int m()
  {
    return 1;
  }
  
  public JSONArray n()
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject = (ActivityManager)b("activity");
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
  
  public String o()
  {
    for (;;)
    {
      String str3;
      String str2;
      try
      {
        String str1 = y();
        if (str1 != null)
        {
          str3 = str1;
          return str3;
        }
      }
      catch (Throwable localThrowable1)
      {
        e.b(localThrowable1);
        str2 = null;
        continue;
      }
      try
      {
        str2 = a();
        str3 = d();
        String str4 = b();
        str2 = a.a(a.a(str2 + ":" + str3 + ":" + str4));
        str3 = str2;
        if (str2 == null) {
          continue;
        }
        try
        {
          c(str2);
          return str2;
        }
        catch (Throwable localThrowable3)
        {
          e.b(localThrowable3);
          return str2;
        }
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          e.a(localThrowable2);
          Object localObject = null;
        }
      }
    }
  }
  
  public String p()
  {
    return this.a.getPackageName();
  }
  
  public String q()
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
  
  public int r()
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
  
  public String s()
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
  
  public String t()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)b("phone");
    if (localTelephonyManager == null) {
      return null;
    }
    return localTelephonyManager.getNetworkOperator();
  }
  
  public String u()
  {
    if (a("android.permission.GET_TASKS")) {
      try
      {
        Object localObject = (ActivityManager)b("activity");
        if (localObject == null) {
          return null;
        }
        localObject = ((ActivityManager.RunningTaskInfo)((ActivityManager)localObject).getRunningTasks(1).get(0)).topActivity.getPackageName();
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        e.b(localThrowable);
      }
    }
    return null;
  }
  
  public boolean v()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  public String w()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
}
