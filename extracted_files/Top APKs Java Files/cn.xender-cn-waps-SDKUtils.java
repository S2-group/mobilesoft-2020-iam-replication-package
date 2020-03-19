package cn.waps;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SDKUtils
{
  static String a = "";
  private Context b;
  private PackageManager c;
  private PackageInfo d;
  private Handler e;
  private WebView f;
  private RelativeLayout g;
  private LinearLayout h;
  private AppListener i;
  private Dialog j;
  
  public SDKUtils(Context paramContext)
  {
    this.b = paramContext;
  }
  
  public SDKUtils(Context paramContext, Dialog paramDialog)
  {
    this.b = paramContext;
    this.j = paramDialog;
  }
  
  public SDKUtils(Context paramContext, Handler paramHandler, WebView paramWebView, RelativeLayout paramRelativeLayout, LinearLayout paramLinearLayout, AppListener paramAppListener)
  {
    this.b = paramContext;
    this.e = paramHandler;
    this.g = paramRelativeLayout;
    this.h = paramLinearLayout;
    this.i = paramAppListener;
    this.f = paramWebView;
  }
  
  public static int getDisplaySize(Context paramContext)
  {
    int k = ((Activity)paramContext).getWindowManager().getDefaultDisplay().getWidth();
    int m = ((Activity)paramContext).getWindowManager().getDefaultDisplay().getHeight();
    if (k < m)
    {
      if (k == 320) {
        return 320;
      }
      if (k < 320) {
        return 240;
      }
      if ((k >= 720) && (k < 1080)) {
        return 720;
      }
      if (k >= 1080) {
        return 1080;
      }
    }
    else
    {
      if (m == 320) {
        return 320;
      }
      if (m < 320) {
        return 240;
      }
      if ((m >= 720) && (m < 1080)) {
        return 720;
      }
      if (m >= 1080) {
        return 1080;
      }
    }
    return 480;
  }
  
  public void callTel(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.DIAL");
    localIntent.setData(Uri.parse("tel:" + paramString));
    this.b.startActivity(localIntent);
  }
  
  public void close()
  {
    if (this.j == null)
    {
      ((Activity)this.b).finish();
      return;
    }
    this.j.cancel();
    SharedPreferences.Editor localEditor = this.b.getSharedPreferences("AppSettings", 0).edit();
    localEditor.putBoolean("pref_offers_shown", false);
    localEditor.remove("pref_user_id");
    localEditor.commit();
  }
  
  public void closeAd()
  {
    try
    {
      this.e.post(new cp(this));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void closeOfDialog(String paramString)
  {
    submit((String)AppConnect.c(this.b).get("message_title"), paramString);
  }
  
  public void closeSubmit(String paramString)
  {
    Toast.makeText(this.b, paramString, 1).show();
    ((Activity)this.b).finish();
  }
  
  public void deleteLocalFiles(File paramFile)
  {
    try
    {
      if (paramFile.exists())
      {
        if (paramFile.isFile())
        {
          paramFile.delete();
          return;
        }
        if (paramFile.isDirectory())
        {
          paramFile = paramFile.listFiles();
          int m = paramFile.length;
          int k = 0;
          while (k < m)
          {
            deleteLocalFiles(paramFile[k]);
            k += 1;
          }
        }
      }
      return;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
  }
  
  public void full_screen()
  {
    this.e.post(new cs(this));
  }
  
  public String[] getAllPermissions()
  {
    Object localObject = this.b.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(this.b.getPackageName(), 4096).requestedPermissions;
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public Map getAppInfoMap(String paramString)
  {
    try
    {
      HashMap localHashMap = new HashMap();
      PackageManager localPackageManager = this.b.getPackageManager();
      Object localObject1 = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
      localObject1 = localPackageManager.queryIntentActivities((Intent)localObject1, 1);
      int k = 0;
      while (k < ((List)localObject1).size())
      {
        Object localObject2 = (ResolveInfo)((List)localObject1).get(k);
        if (((ResolveInfo)localObject2).activityInfo.packageName.equals(paramString))
        {
          String str = ((ResolveInfo)localObject2).loadLabel(localPackageManager).toString();
          int m = ((ResolveInfo)localObject2).activityInfo.applicationInfo.icon;
          localObject2 = ((ResolveInfo)localObject2).activityInfo.name;
          if ((localObject2 != null) && (!"".equals(((String)localObject2).trim())))
          {
            localHashMap.put("appName", str);
            localHashMap.put("appIcon", Integer.valueOf(m));
            localHashMap.put("activityName", localObject2);
            return localHashMap;
          }
        }
        k += 1;
      }
      return null;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public String getAppName()
  {
    try
    {
      String str = (String)this.b.getApplicationInfo().loadLabel(this.b.getPackageManager());
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public String getAppVersion(String paramString)
  {
    try
    {
      paramString = this.b.createPackageContext(paramString, 3);
      this.c = paramString.getPackageManager();
      this.d = this.c.getPackageInfo(paramString.getPackageName(), 0);
      if (this.d != null)
      {
        paramString = this.d.versionName;
        if ((paramString != null) && (!"".equals(paramString.trim()))) {
          return paramString;
        }
        Log.i("APP_SDK", "The app is not exist.");
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    return "";
  }
  
  public String getBrowserPackageName(String paramString)
  {
    for (;;)
    {
      int k;
      try
      {
        String str = getInstalled();
        if ((paramString != null) && (!"".equals(paramString.trim())))
        {
          if (paramString.indexOf(";") < 0)
          {
            if (str.contains(paramString)) {
              return paramString;
            }
          }
          else
          {
            paramString = paramString.split(";");
            k = 0;
            if (k < paramString.length)
            {
              if (!str.contains(paramString[k])) {
                break label88;
              }
              return paramString[k];
            }
          }
          return "";
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return "";
      }
      return "";
      label88:
      k += 1;
    }
  }
  
  public void getHtml(String paramString)
  {
    a = paramString;
  }
  
  public String getImsi()
  {
    String str = "";
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)this.b.getSystemService("phone");
      if (localTelephonyManager != null) {
        str = localTelephonyManager.getSubscriberId();
      }
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  /* Error */
  public String getInstalled()
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield 37	cn/waps/SDKUtils:b	Landroid/content/Context;
    //   5: invokevirtual 240	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   8: putfield 348	cn/waps/SDKUtils:c	Landroid/content/pm/PackageManager;
    //   11: aload_0
    //   12: getfield 348	cn/waps/SDKUtils:c	Landroid/content/pm/PackageManager;
    //   15: iconst_0
    //   16: invokevirtual 397	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   19: astore 5
    //   21: ldc 28
    //   23: astore_3
    //   24: iconst_0
    //   25: istore_1
    //   26: iload_1
    //   27: aload 5
    //   29: invokeinterface 279 1 0
    //   34: if_icmpge +81 -> 115
    //   37: aload 5
    //   39: iload_1
    //   40: invokeinterface 282 2 0
    //   45: checkcast 251	android/content/pm/PackageInfo
    //   48: astore 6
    //   50: aload 6
    //   52: getfield 398	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   55: getfield 401	android/content/pm/ApplicationInfo:flags	I
    //   58: istore_2
    //   59: aload 6
    //   61: getfield 398	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   64: astore 4
    //   66: aload_3
    //   67: astore 4
    //   69: iload_2
    //   70: iconst_1
    //   71: iand
    //   72: ifgt +33 -> 105
    //   75: new 95	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   82: aload_3
    //   83: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: aload 6
    //   88: getfield 402	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   91: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: ldc_w 368
    //   97: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: astore 4
    //   105: iload_1
    //   106: iconst_1
    //   107: iadd
    //   108: istore_1
    //   109: aload 4
    //   111: astore_3
    //   112: goto -86 -> 26
    //   115: aload_3
    //   116: areturn
    //   117: astore_3
    //   118: ldc 28
    //   120: astore 4
    //   122: aload_3
    //   123: invokevirtual 178	java/lang/Exception:printStackTrace	()V
    //   126: aload 4
    //   128: areturn
    //   129: astore 5
    //   131: aload_3
    //   132: astore 4
    //   134: aload 5
    //   136: astore_3
    //   137: goto -15 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	this	SDKUtils
    //   25	84	1	k	int
    //   58	14	2	m	int
    //   23	93	3	localObject1	Object
    //   117	15	3	localException1	Exception
    //   136	1	3	localException2	Exception
    //   64	69	4	localObject2	Object
    //   19	19	5	localList	List
    //   129	6	5	localException3	Exception
    //   48	39	6	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   0	21	117	java/lang/Exception
    //   26	66	129	java/lang/Exception
    //   75	105	129	java/lang/Exception
  }
  
  public String getLanguageCode()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public List getList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int k;
    if ((paramString != null) && (!"".equals(paramString)) && (paramString.indexOf("[;]") >= 0))
    {
      paramString = paramString.split("\\[;\\]");
      k = 0;
    }
    while (k < paramString.length)
    {
      localArrayList.add(paramString[k]);
      k += 1;
      continue;
      localArrayList.add(paramString);
    }
    return localArrayList;
  }
  
  public String getMac_Address()
  {
    try
    {
      if (hasThePermission("ACCESS_WIFI_STATE"))
      {
        Object localObject = ((WifiManager)this.b.getSystemService("wifi")).getConnectionInfo();
        if (localObject != null)
        {
          localObject = ((WifiInfo)localObject).getMacAddress();
          if ((localObject != null) && (!"".equals(((String)localObject).trim()))) {
            return localObject;
          }
        }
      }
      else
      {
        Log.i("APP_SDK", "Permission.ACCESS_WIFI_STATE is not found or the device is Emulator, Please check it!");
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    return "";
  }
  
  public InputStream getNetDataToStream(String paramString)
  {
    try
    {
      paramString = new cl(this.b).a(paramString, null, null);
      paramString.connect();
      paramString = paramString.getInputStream();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public String getNetType()
  {
    try
    {
      Object localObject = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localObject != null)
      {
        if (!((NetworkInfo)localObject).getTypeName().toLowerCase().equals("mobile")) {
          return ((NetworkInfo)localObject).getTypeName().toLowerCase();
        }
        localObject = ((NetworkInfo)localObject).getExtraInfo().toLowerCase();
        return localObject;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public String getNodeTrimValues(NodeList paramNodeList)
  {
    int k = 0;
    Object localObject1 = "";
    if (k < paramNodeList.getLength())
    {
      Object localObject3 = (Element)paramNodeList.item(k);
      Object localObject2 = localObject1;
      if (localObject3 != null)
      {
        localObject3 = ((Element)localObject3).getChildNodes();
        if (((NodeList)localObject3).getLength() <= 0) {
          break label147;
        }
        int m = 0;
        while (m < ((NodeList)localObject3).getLength())
        {
          Node localNode = ((NodeList)localObject3).item(m);
          localObject2 = localObject1;
          if (localNode != null) {
            localObject2 = (String)localObject1 + localNode.getNodeValue() + "[;]";
          }
          m += 1;
          localObject1 = localObject2;
        }
      }
      label147:
      for (localObject2 = localObject1;; localObject2 = (String)localObject1 + "a[;]")
      {
        k += 1;
        localObject1 = localObject2;
        break;
      }
    }
    if ((localObject1 != null) && (!((String)localObject1).equals(""))) {
      return ((String)localObject1).substring(0, ((String)localObject1).length() - 3).trim();
    }
    return null;
  }
  
  public String getResponseResult(HttpResponse paramHttpResponse)
  {
    try
    {
      paramHttpResponse = EntityUtils.toString(paramHttpResponse.getEntity());
      return paramHttpResponse;
    }
    catch (Exception paramHttpResponse)
    {
      paramHttpResponse.printStackTrace();
    }
    return "";
  }
  
  public String getRunningAppPackageNames()
  {
    label137:
    for (;;)
    {
      try
      {
        Iterator localIterator = ((ActivityManager)this.b.getSystemService("activity")).getRunningAppProcesses().iterator();
        String str = "";
        if (localIterator.hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
          if (!getInstalled().contains(localRunningAppProcessInfo.processName)) {
            break label137;
          }
          str = str + localRunningAppProcessInfo.processName + ";";
          break label137;
        }
        if ((str != null) && (!"".equals(str.trim())) && (str.endsWith(";")))
        {
          str = str.substring(0, str.length() - 1);
          return str;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return "";
    }
  }
  
  public String getScreenStatus()
  {
    try
    {
      if (this.b.getResources().getConfiguration().orientation == 1) {
        return "true";
      }
      if (this.b.getResources().getConfiguration().orientation == 2) {
        return "false";
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  /* Error */
  public String getUdid()
  {
    // Byte code:
    //   0: ldc 28
    //   2: astore_1
    //   3: getstatic 583	cn/waps/AppConnect:b	Ljava/lang/String;
    //   6: astore_2
    //   7: aload_2
    //   8: astore_1
    //   9: aload_2
    //   10: astore_3
    //   11: aload_2
    //   12: invokestatic 585	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   15: ifeq +87 -> 102
    //   18: aload_2
    //   19: astore_1
    //   20: aload_0
    //   21: getfield 37	cn/waps/SDKUtils:b	Landroid/content/Context;
    //   24: ldc_w 384
    //   27: invokevirtual 388	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   30: checkcast 390	android/telephony/TelephonyManager
    //   33: astore 4
    //   35: aload_2
    //   36: astore_3
    //   37: aload 4
    //   39: ifnull +63 -> 102
    //   42: aload_2
    //   43: astore_1
    //   44: aload 4
    //   46: invokevirtual 588	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   49: astore_2
    //   50: aload_2
    //   51: astore_1
    //   52: aload_1
    //   53: astore_2
    //   54: aload_1
    //   55: invokestatic 585	cn/waps/cl:b	(Ljava/lang/String;)Z
    //   58: ifeq +35 -> 93
    //   61: new 95	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   68: ldc_w 590
    //   71: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: aload_0
    //   75: invokevirtual 592	cn/waps/SDKUtils:getMac_Address	()Ljava/lang/String;
    //   78: ldc_w 594
    //   81: ldc 28
    //   83: invokevirtual 598	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   86: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: astore_2
    //   93: aload_2
    //   94: areturn
    //   95: astore_2
    //   96: aload_2
    //   97: invokevirtual 178	java/lang/Exception:printStackTrace	()V
    //   100: aload_1
    //   101: astore_3
    //   102: aload_3
    //   103: areturn
    //   104: astore_2
    //   105: goto -9 -> 96
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	SDKUtils
    //   2	99	1	localObject1	Object
    //   6	88	2	localObject2	Object
    //   95	2	2	localException1	Exception
    //   104	1	2	localException2	Exception
    //   10	93	3	localObject3	Object
    //   33	12	4	localTelephonyManager	TelephonyManager
    // Exception table:
    //   from	to	target	type
    //   3	7	95	java/lang/Exception
    //   11	18	95	java/lang/Exception
    //   20	35	95	java/lang/Exception
    //   44	50	95	java/lang/Exception
    //   54	93	104	java/lang/Exception
  }
  
  public void goToTargetBrowser(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = goToTargetBrowser_Intent(paramString1, paramString2);
      this.b.startActivity(paramString1);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public Intent goToTargetBrowser_Intent(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = goToTargetBrowser_Intent(paramString1, "", paramString2);
      if (paramString1 != null) {
        return paramString1;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public Intent goToTargetBrowser_Intent(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      if (getInstalled().contains(paramString1))
      {
        Intent localIntent2 = this.b.getPackageManager().getLaunchIntentForPackage(paramString1);
        Intent localIntent1 = localIntent2;
        if (paramString2 != null)
        {
          localIntent1 = localIntent2;
          if (!"".equals(paramString2.trim()))
          {
            localIntent1 = new Intent();
            localIntent1.setClassName(paramString1, paramString2);
          }
        }
        localIntent1.setAction("android.intent.action.VIEW");
        localIntent1.addCategory("android.intent.category.DEFAULT");
        localIntent1.setData(Uri.parse(paramString3));
        return localIntent1;
      }
      new Intent("android.intent.action.VIEW", Uri.parse(paramString3)).setFlags(268435456);
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
    return null;
  }
  
  public boolean hasThePermission(String paramString)
  {
    try
    {
      String[] arrayOfString = getAllPermissions();
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        int m = arrayOfString.length;
        int k = 0;
        while (k < m)
        {
          String str = arrayOfString[k];
          if (!cl.b(paramString))
          {
            boolean bool = str.contains(paramString);
            if (bool) {
              return true;
            }
          }
          k += 1;
        }
      }
      return false;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void hideAd()
  {
    try
    {
      this.e.post(new cq(this));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public int initAdWidth()
  {
    try
    {
      if (this.b.getResources().getConfiguration().orientation == 1) {
        return ((Activity)this.b).getWindowManager().getDefaultDisplay().getWidth();
      }
      if (this.b.getResources().getConfiguration().orientation == 2)
      {
        int k = ((Activity)this.b).getWindowManager().getDefaultDisplay().getHeight();
        return k;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  public boolean isCmwap()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.getExtraInfo() != null) && (localNetworkInfo.getExtraInfo().toLowerCase().contains(aj.j()));
  }
  
  public boolean isConnect()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null) {
        return true;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public String isInstalled(String paramString)
  {
    try
    {
      if (this.b.getPackageManager().getLaunchIntentForPackage(paramString) != null) {
        return "true";
      }
      return "false";
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public boolean isTimeLimited(String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        Date localDate = new Date(System.currentTimeMillis());
        SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat localSimpleDateFormat3 = new SimpleDateFormat("HH:mm:ss");
        try
        {
          if (!localSimpleDateFormat2.format(localSimpleDateFormat3.parse(paramString1)).equals("1970-01-01")) {
            break label214;
          }
          str1 = localSimpleDateFormat2.format(localDate) + " " + paramString1;
        }
        catch (Exception localException)
        {
          try
          {
            String str1;
            if (!localSimpleDateFormat2.format(localSimpleDateFormat3.parse(paramString1)).equals("1970-01-01")) {
              break label209;
            }
            paramString1 = localSimpleDateFormat2.format(localDate) + " " + paramString2;
            paramString2 = localSimpleDateFormat1.parse(str1);
            paramString1 = localSimpleDateFormat1.parse(paramString1);
            if (localDate.after(paramString2))
            {
              boolean bool = localDate.before(paramString1);
              if (bool)
              {
                return true;
                localException = localException;
                str2 = paramString1;
                continue;
              }
            }
          }
          catch (Exception paramString1)
          {
            paramString1 = paramString2;
            continue;
          }
        }
        return false;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
      }
      label209:
      paramString1 = paramString2;
      continue;
      label214:
      String str2 = paramString1;
    }
  }
  
  public String isVisible()
  {
    if (((Activity)this.b).hasWindowFocus()) {
      return "true";
    }
    return "false";
  }
  
  public boolean isWapNetwork()
  {
    return !cl.b(Proxy.getDefaultHost());
  }
  
  public boolean isWifi()
  {
    String str = "";
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null) {
        if (localNetworkInfo.getTypeName().toLowerCase().equals("mobile")) {
          break label60;
        }
      }
      label60:
      for (str = localNetworkInfo.getTypeName().toLowerCase(); "wifi".equals(str); str = localNetworkInfo.getExtraInfo().toLowerCase()) {
        return true;
      }
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public void load(String paramString)
  {
    if (paramString != null) {
      try
      {
        if (!"".equals(paramString))
        {
          this.c = this.b.getPackageManager();
          paramString = this.c.getLaunchIntentForPackage(paramString);
          if (paramString != null)
          {
            this.b.startActivity(paramString);
            return;
          }
          Log.i("APP_SDK", "The app is not exist.");
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public InputStream loadStreamFromLocal(String paramString1, String paramString2)
  {
    try
    {
      if ("mounted".equals(Environment.getExternalStorageState()))
      {
        paramString2 = Environment.getExternalStorageDirectory().toString() + paramString2;
        paramString2 = new File(paramString2 + "/" + paramString1);
        if ((paramString2.exists()) && (paramString2.length() > 0L)) {
          return new FileInputStream(paramString2);
        }
      }
      paramString1 = this.b.getFileStreamPath(paramString1);
      if ((paramString1.exists()) && (paramString1.length() > 0L))
      {
        paramString1 = new FileInputStream(paramString1);
        return paramString1;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  public String loadStringFromLocal(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: ldc_w 692
    //   3: invokestatic 697	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   6: invokevirtual 297	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: ifeq +518 -> 527
    //   12: new 95	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   19: invokestatic 701	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   22: invokevirtual 702	java/io/File:toString	()Ljava/lang/String;
    //   25: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: aload_2
    //   29: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: astore_2
    //   36: new 212	java/io/File
    //   39: dup
    //   40: new 95	java/lang/StringBuilder
    //   43: dup
    //   44: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   47: aload_2
    //   48: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: ldc_w 704
    //   54: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: aload_1
    //   58: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: invokespecial 705	java/io/File:<init>	(Ljava/lang/String;)V
    //   67: astore_2
    //   68: aload_2
    //   69: invokevirtual 215	java/io/File:exists	()Z
    //   72: ifeq +455 -> 527
    //   75: aload_2
    //   76: invokevirtual 707	java/io/File:length	()J
    //   79: lconst_0
    //   80: lcmp
    //   81: ifle +446 -> 527
    //   84: new 709	java/io/FileInputStream
    //   87: dup
    //   88: aload_2
    //   89: invokespecial 711	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   92: astore_2
    //   93: new 720	java/io/BufferedReader
    //   96: dup
    //   97: new 722	java/io/InputStreamReader
    //   100: dup
    //   101: aload_2
    //   102: invokespecial 725	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   105: invokespecial 728	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   108: astore_3
    //   109: aload_3
    //   110: ifnull +72 -> 182
    //   113: ldc 28
    //   115: astore_1
    //   116: aload_3
    //   117: invokevirtual 731	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   120: astore 4
    //   122: aload 4
    //   124: ifnull +32 -> 156
    //   127: new 95	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   134: aload_1
    //   135: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: aload 4
    //   140: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc_w 733
    //   146: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   152: astore_1
    //   153: goto -37 -> 116
    //   156: aload_2
    //   157: ifnull +7 -> 164
    //   160: aload_2
    //   161: invokevirtual 735	java/io/FileInputStream:close	()V
    //   164: aload_3
    //   165: ifnull +7 -> 172
    //   168: aload_3
    //   169: invokevirtual 736	java/io/BufferedReader:close	()V
    //   172: aload_1
    //   173: areturn
    //   174: astore_2
    //   175: aload_2
    //   176: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   179: goto -7 -> 172
    //   182: aload_3
    //   183: astore 4
    //   185: aload_2
    //   186: astore_3
    //   187: aload 4
    //   189: astore_2
    //   190: aload_0
    //   191: getfield 37	cn/waps/SDKUtils:b	Landroid/content/Context;
    //   194: aload_1
    //   195: invokevirtual 715	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   198: astore 5
    //   200: aload_2
    //   201: astore 4
    //   203: aload_3
    //   204: astore_1
    //   205: aload 5
    //   207: invokevirtual 215	java/io/File:exists	()Z
    //   210: ifeq +121 -> 331
    //   213: aload_2
    //   214: astore 4
    //   216: aload_3
    //   217: astore_1
    //   218: aload 5
    //   220: invokevirtual 707	java/io/File:length	()J
    //   223: lconst_0
    //   224: lcmp
    //   225: ifle +106 -> 331
    //   228: new 709	java/io/FileInputStream
    //   231: dup
    //   232: aload 5
    //   234: invokespecial 711	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   237: astore_1
    //   238: new 720	java/io/BufferedReader
    //   241: dup
    //   242: new 722	java/io/InputStreamReader
    //   245: dup
    //   246: aload_1
    //   247: invokespecial 725	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   250: invokespecial 728	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   253: astore_3
    //   254: aload_3
    //   255: ifnull +73 -> 328
    //   258: ldc 28
    //   260: astore_2
    //   261: aload_3
    //   262: invokevirtual 731	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   265: astore 4
    //   267: aload 4
    //   269: ifnull +32 -> 301
    //   272: new 95	java/lang/StringBuilder
    //   275: dup
    //   276: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   279: aload_2
    //   280: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: aload 4
    //   285: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: ldc_w 733
    //   291: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   297: astore_2
    //   298: goto -37 -> 261
    //   301: aload_1
    //   302: ifnull +7 -> 309
    //   305: aload_1
    //   306: invokevirtual 735	java/io/FileInputStream:close	()V
    //   309: aload_2
    //   310: astore_1
    //   311: aload_3
    //   312: ifnull -140 -> 172
    //   315: aload_3
    //   316: invokevirtual 736	java/io/BufferedReader:close	()V
    //   319: aload_2
    //   320: areturn
    //   321: astore_1
    //   322: aload_1
    //   323: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   326: aload_2
    //   327: areturn
    //   328: aload_3
    //   329: astore 4
    //   331: aload_1
    //   332: ifnull +7 -> 339
    //   335: aload_1
    //   336: invokevirtual 735	java/io/FileInputStream:close	()V
    //   339: aload 4
    //   341: ifnull +8 -> 349
    //   344: aload 4
    //   346: invokevirtual 736	java/io/BufferedReader:close	()V
    //   349: ldc 28
    //   351: areturn
    //   352: astore_1
    //   353: aload_1
    //   354: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   357: goto -8 -> 349
    //   360: astore_1
    //   361: aconst_null
    //   362: astore_2
    //   363: aconst_null
    //   364: astore_3
    //   365: aload_1
    //   366: invokevirtual 178	java/lang/Exception:printStackTrace	()V
    //   369: aload_3
    //   370: ifnull +7 -> 377
    //   373: aload_3
    //   374: invokevirtual 735	java/io/FileInputStream:close	()V
    //   377: aload_2
    //   378: ifnull -29 -> 349
    //   381: aload_2
    //   382: invokevirtual 736	java/io/BufferedReader:close	()V
    //   385: goto -36 -> 349
    //   388: astore_1
    //   389: aload_1
    //   390: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   393: goto -44 -> 349
    //   396: astore_1
    //   397: aconst_null
    //   398: astore_2
    //   399: aconst_null
    //   400: astore_3
    //   401: aload_3
    //   402: ifnull +7 -> 409
    //   405: aload_3
    //   406: invokevirtual 735	java/io/FileInputStream:close	()V
    //   409: aload_2
    //   410: ifnull +7 -> 417
    //   413: aload_2
    //   414: invokevirtual 736	java/io/BufferedReader:close	()V
    //   417: aload_1
    //   418: athrow
    //   419: astore_2
    //   420: aload_2
    //   421: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   424: goto -7 -> 417
    //   427: astore_1
    //   428: aload_2
    //   429: astore_3
    //   430: aconst_null
    //   431: astore_2
    //   432: goto -31 -> 401
    //   435: astore_1
    //   436: aload_2
    //   437: astore 4
    //   439: aload_3
    //   440: astore_2
    //   441: aload 4
    //   443: astore_3
    //   444: goto -43 -> 401
    //   447: astore_1
    //   448: goto -47 -> 401
    //   451: astore 4
    //   453: aload_1
    //   454: astore_3
    //   455: aload 4
    //   457: astore_1
    //   458: goto -57 -> 401
    //   461: astore_2
    //   462: aload_1
    //   463: astore 4
    //   465: aload_2
    //   466: astore_1
    //   467: aload_3
    //   468: astore_2
    //   469: aload 4
    //   471: astore_3
    //   472: goto -71 -> 401
    //   475: astore_1
    //   476: goto -75 -> 401
    //   479: astore_1
    //   480: aload_2
    //   481: astore_3
    //   482: aconst_null
    //   483: astore_2
    //   484: goto -119 -> 365
    //   487: astore_1
    //   488: aload_2
    //   489: astore 4
    //   491: aload_3
    //   492: astore_2
    //   493: aload 4
    //   495: astore_3
    //   496: goto -131 -> 365
    //   499: astore_1
    //   500: goto -135 -> 365
    //   503: astore 4
    //   505: aload_1
    //   506: astore_3
    //   507: aload 4
    //   509: astore_1
    //   510: goto -145 -> 365
    //   513: astore_2
    //   514: aload_1
    //   515: astore 4
    //   517: aload_2
    //   518: astore_1
    //   519: aload_3
    //   520: astore_2
    //   521: aload 4
    //   523: astore_3
    //   524: goto -159 -> 365
    //   527: aconst_null
    //   528: astore_2
    //   529: aconst_null
    //   530: astore_3
    //   531: goto -341 -> 190
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	534	0	this	SDKUtils
    //   0	534	1	paramString1	String
    //   0	534	2	paramString2	String
    //   108	423	3	localObject1	Object
    //   120	322	4	localObject2	Object
    //   451	5	4	localObject3	Object
    //   463	31	4	str1	String
    //   503	5	4	localException	Exception
    //   515	7	4	str2	String
    //   198	35	5	localFile	File
    // Exception table:
    //   from	to	target	type
    //   160	164	174	java/io/IOException
    //   168	172	174	java/io/IOException
    //   305	309	321	java/io/IOException
    //   315	319	321	java/io/IOException
    //   335	339	352	java/io/IOException
    //   344	349	352	java/io/IOException
    //   0	93	360	java/lang/Exception
    //   373	377	388	java/io/IOException
    //   381	385	388	java/io/IOException
    //   0	93	396	finally
    //   405	409	419	java/io/IOException
    //   413	417	419	java/io/IOException
    //   93	109	427	finally
    //   116	122	435	finally
    //   127	153	435	finally
    //   190	200	447	finally
    //   205	213	447	finally
    //   218	238	447	finally
    //   238	254	451	finally
    //   261	267	461	finally
    //   272	298	461	finally
    //   365	369	475	finally
    //   93	109	479	java/lang/Exception
    //   116	122	487	java/lang/Exception
    //   127	153	487	java/lang/Exception
    //   190	200	499	java/lang/Exception
    //   205	213	499	java/lang/Exception
    //   218	238	499	java/lang/Exception
    //   238	254	503	java/lang/Exception
    //   261	267	513	java/lang/Exception
    //   272	298	513	java/lang/Exception
  }
  
  public void openAd()
  {
    openAd("");
  }
  
  public void openAd(String paramString)
  {
    try
    {
      this.e.post(new cr(this, paramString));
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void openAppByUri(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new cl(this.b).a(this.b, paramString1, paramString2, paramString3);
    this.b.startActivity(paramString1);
  }
  
  public void openUrlByBrowser(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = getBrowserPackageName(paramString1);
      if ((paramString1 != null) && (!"".equals(paramString1.trim())))
      {
        goToTargetBrowser(paramString1, paramString2);
        return;
      }
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      paramString1.setFlags(268435456);
      this.b.startActivity(paramString1);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public Intent openUrlByBrowser_Intent(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = getBrowserPackageName(paramString1);
      if ((paramString1 != null) && (!"".equals(paramString1.trim()))) {
        return goToTargetBrowser_Intent(paramString1, paramString2);
      }
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      paramString1.setFlags(268435456);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public String replaceData(String paramString)
  {
    if ((!paramString.equals("")) && (paramString.equals("a"))) {
      return paramString.replace("a", "");
    }
    return paramString;
  }
  
  /* Error */
  public void saveDataToLocal(InputStream paramInputStream, String paramString1, String paramString2, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: sipush 10240
    //   6: newarray byte
    //   8: astore 7
    //   10: ldc_w 692
    //   13: invokestatic 697	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   16: invokevirtual 297	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   19: ifeq +231 -> 250
    //   22: new 95	java/lang/StringBuilder
    //   25: dup
    //   26: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   29: invokestatic 701	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   32: invokevirtual 702	java/io/File:toString	()Ljava/lang/String;
    //   35: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_3
    //   39: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: astore 8
    //   47: new 212	java/io/File
    //   50: dup
    //   51: aload 8
    //   53: invokespecial 705	java/io/File:<init>	(Ljava/lang/String;)V
    //   56: astore_3
    //   57: new 212	java/io/File
    //   60: dup
    //   61: new 95	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   68: aload 8
    //   70: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: ldc_w 704
    //   76: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: aload_2
    //   83: invokespecial 766	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: astore 8
    //   88: aload_3
    //   89: invokevirtual 215	java/io/File:exists	()Z
    //   92: ifne +8 -> 100
    //   95: aload_3
    //   96: invokevirtual 769	java/io/File:mkdirs	()Z
    //   99: pop
    //   100: aload 8
    //   102: invokevirtual 215	java/io/File:exists	()Z
    //   105: ifne +9 -> 114
    //   108: aload 8
    //   110: invokevirtual 772	java/io/File:createNewFile	()Z
    //   113: pop
    //   114: aload 6
    //   116: astore_3
    //   117: aload 8
    //   119: ifnull +55 -> 174
    //   122: new 774	java/io/FileOutputStream
    //   125: dup
    //   126: aload 8
    //   128: invokespecial 775	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   131: astore_3
    //   132: aload_1
    //   133: aload 7
    //   135: invokevirtual 781	java/io/InputStream:read	([B)I
    //   138: istore 5
    //   140: iload 5
    //   142: iconst_m1
    //   143: if_icmpeq +31 -> 174
    //   146: aload_3
    //   147: aload 7
    //   149: iconst_0
    //   150: iload 5
    //   152: invokevirtual 785	java/io/FileOutputStream:write	([BII)V
    //   155: goto -23 -> 132
    //   158: astore_1
    //   159: aload_3
    //   160: astore_2
    //   161: aload_1
    //   162: invokevirtual 178	java/lang/Exception:printStackTrace	()V
    //   165: aload_2
    //   166: ifnull +7 -> 173
    //   169: aload_2
    //   170: invokevirtual 786	java/io/FileOutputStream:close	()V
    //   173: return
    //   174: aload_3
    //   175: astore 6
    //   177: iload 4
    //   179: ifeq +152 -> 331
    //   182: aload_0
    //   183: getfield 37	cn/waps/SDKUtils:b	Landroid/content/Context;
    //   186: aload_2
    //   187: iconst_0
    //   188: invokevirtual 790	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   191: astore_2
    //   192: aload_2
    //   193: astore 6
    //   195: aload_2
    //   196: astore 7
    //   198: sipush 10240
    //   201: newarray byte
    //   203: astore_3
    //   204: aload_2
    //   205: astore 6
    //   207: aload_2
    //   208: astore 7
    //   210: aload_1
    //   211: aload_3
    //   212: invokevirtual 781	java/io/InputStream:read	([B)I
    //   215: istore 5
    //   217: aload_2
    //   218: astore 6
    //   220: iload 5
    //   222: iconst_m1
    //   223: if_icmpeq +108 -> 331
    //   226: aload_2
    //   227: astore 6
    //   229: aload_2
    //   230: astore 7
    //   232: aload_2
    //   233: aload_3
    //   234: iconst_0
    //   235: iload 5
    //   237: invokevirtual 785	java/io/FileOutputStream:write	([BII)V
    //   240: goto -36 -> 204
    //   243: astore_1
    //   244: aload 6
    //   246: astore_2
    //   247: goto -86 -> 161
    //   250: aload_0
    //   251: getfield 37	cn/waps/SDKUtils:b	Landroid/content/Context;
    //   254: aload_2
    //   255: iconst_0
    //   256: invokevirtual 790	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   259: astore_3
    //   260: aload_3
    //   261: astore 6
    //   263: aload_3
    //   264: astore 7
    //   266: sipush 10240
    //   269: newarray byte
    //   271: astore 8
    //   273: aload_3
    //   274: astore 6
    //   276: aload_3
    //   277: astore 7
    //   279: aload_1
    //   280: aload 8
    //   282: invokevirtual 781	java/io/InputStream:read	([B)I
    //   285: istore 5
    //   287: iload 5
    //   289: iconst_m1
    //   290: if_icmpeq +35 -> 325
    //   293: aload_3
    //   294: astore 6
    //   296: aload_3
    //   297: astore 7
    //   299: aload_3
    //   300: aload 8
    //   302: iconst_0
    //   303: iload 5
    //   305: invokevirtual 785	java/io/FileOutputStream:write	([BII)V
    //   308: goto -35 -> 273
    //   311: astore_1
    //   312: aload 7
    //   314: astore_2
    //   315: aload_2
    //   316: ifnull +7 -> 323
    //   319: aload_2
    //   320: invokevirtual 786	java/io/FileOutputStream:close	()V
    //   323: aload_1
    //   324: athrow
    //   325: iconst_0
    //   326: istore 4
    //   328: goto -154 -> 174
    //   331: aload 6
    //   333: ifnull -160 -> 173
    //   336: aload 6
    //   338: invokevirtual 786	java/io/FileOutputStream:close	()V
    //   341: return
    //   342: astore_1
    //   343: aload_1
    //   344: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   347: return
    //   348: astore_1
    //   349: aload_1
    //   350: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   353: return
    //   354: astore_2
    //   355: aload_2
    //   356: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   359: goto -36 -> 323
    //   362: astore_1
    //   363: aconst_null
    //   364: astore_2
    //   365: goto -50 -> 315
    //   368: astore_1
    //   369: aload_3
    //   370: astore_2
    //   371: goto -56 -> 315
    //   374: astore_1
    //   375: aload_3
    //   376: astore_2
    //   377: goto -62 -> 315
    //   380: astore_1
    //   381: goto -66 -> 315
    //   384: astore_1
    //   385: aconst_null
    //   386: astore_2
    //   387: goto -226 -> 161
    //   390: astore_1
    //   391: aload_3
    //   392: astore_2
    //   393: goto -232 -> 161
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	396	0	this	SDKUtils
    //   0	396	1	paramInputStream	InputStream
    //   0	396	2	paramString1	String
    //   0	396	3	paramString2	String
    //   0	396	4	paramBoolean	boolean
    //   138	166	5	k	int
    //   1	336	6	str	String
    //   8	305	7	localObject1	Object
    //   45	256	8	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   132	140	158	java/lang/Exception
    //   146	155	158	java/lang/Exception
    //   198	204	243	java/lang/Exception
    //   210	217	243	java/lang/Exception
    //   232	240	243	java/lang/Exception
    //   266	273	243	java/lang/Exception
    //   279	287	243	java/lang/Exception
    //   299	308	243	java/lang/Exception
    //   198	204	311	finally
    //   210	217	311	finally
    //   232	240	311	finally
    //   266	273	311	finally
    //   279	287	311	finally
    //   299	308	311	finally
    //   336	341	342	java/io/IOException
    //   169	173	348	java/io/IOException
    //   319	323	354	java/io/IOException
    //   3	100	362	finally
    //   100	114	362	finally
    //   122	132	362	finally
    //   250	260	362	finally
    //   132	140	368	finally
    //   146	155	368	finally
    //   182	192	374	finally
    //   161	165	380	finally
    //   3	100	384	java/lang/Exception
    //   100	114	384	java/lang/Exception
    //   122	132	384	java/lang/Exception
    //   250	260	384	java/lang/Exception
    //   182	192	390	java/lang/Exception
  }
  
  /* Error */
  public void saveDataToLocal(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_1
    //   4: ldc_w 793
    //   7: invokevirtual 797	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   10: astore 6
    //   12: ldc_w 692
    //   15: invokestatic 697	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   18: invokevirtual 297	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   21: ifeq +155 -> 176
    //   24: new 95	java/lang/StringBuilder
    //   27: dup
    //   28: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   31: invokestatic 701	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   34: invokevirtual 702	java/io/File:toString	()Ljava/lang/String;
    //   37: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_3
    //   41: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   47: astore_3
    //   48: new 212	java/io/File
    //   51: dup
    //   52: aload_3
    //   53: invokespecial 705	java/io/File:<init>	(Ljava/lang/String;)V
    //   56: astore_1
    //   57: new 212	java/io/File
    //   60: dup
    //   61: new 95	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 96	java/lang/StringBuilder:<init>	()V
    //   68: aload_3
    //   69: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: ldc_w 704
    //   75: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: aload_2
    //   79: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: invokespecial 705	java/io/File:<init>	(Ljava/lang/String;)V
    //   88: astore_3
    //   89: aload_1
    //   90: invokevirtual 215	java/io/File:exists	()Z
    //   93: ifne +8 -> 101
    //   96: aload_1
    //   97: invokevirtual 769	java/io/File:mkdirs	()Z
    //   100: pop
    //   101: aload_3
    //   102: invokevirtual 215	java/io/File:exists	()Z
    //   105: ifne +8 -> 113
    //   108: aload_3
    //   109: invokevirtual 772	java/io/File:createNewFile	()Z
    //   112: pop
    //   113: aload 5
    //   115: astore_1
    //   116: aload_3
    //   117: ifnull +18 -> 135
    //   120: new 774	java/io/FileOutputStream
    //   123: dup
    //   124: aload_3
    //   125: invokespecial 775	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   128: astore_1
    //   129: aload_1
    //   130: aload 6
    //   132: invokevirtual 800	java/io/FileOutputStream:write	([B)V
    //   135: aload_1
    //   136: astore_3
    //   137: iload 4
    //   139: ifeq +28 -> 167
    //   142: aload_0
    //   143: getfield 37	cn/waps/SDKUtils:b	Landroid/content/Context;
    //   146: aload_2
    //   147: iconst_0
    //   148: invokevirtual 790	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   151: astore_2
    //   152: aload_2
    //   153: astore_1
    //   154: aload_1
    //   155: astore_3
    //   156: aload_1
    //   157: astore 5
    //   159: aload_1
    //   160: aload 6
    //   162: invokevirtual 800	java/io/FileOutputStream:write	([B)V
    //   165: aload_1
    //   166: astore_3
    //   167: aload_3
    //   168: ifnull +7 -> 175
    //   171: aload_3
    //   172: invokevirtual 786	java/io/FileOutputStream:close	()V
    //   175: return
    //   176: aload_0
    //   177: getfield 37	cn/waps/SDKUtils:b	Landroid/content/Context;
    //   180: aload_2
    //   181: iconst_0
    //   182: invokevirtual 790	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   185: astore_1
    //   186: aload_1
    //   187: astore_3
    //   188: aload_1
    //   189: astore 5
    //   191: aload_1
    //   192: aload 6
    //   194: invokevirtual 800	java/io/FileOutputStream:write	([B)V
    //   197: iconst_0
    //   198: istore 4
    //   200: goto -65 -> 135
    //   203: astore_1
    //   204: aload_1
    //   205: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   208: return
    //   209: astore_1
    //   210: aconst_null
    //   211: astore_2
    //   212: aload_1
    //   213: invokevirtual 178	java/lang/Exception:printStackTrace	()V
    //   216: aload_2
    //   217: ifnull -42 -> 175
    //   220: aload_2
    //   221: invokevirtual 786	java/io/FileOutputStream:close	()V
    //   224: return
    //   225: astore_1
    //   226: aload_1
    //   227: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   230: return
    //   231: astore_1
    //   232: aconst_null
    //   233: astore_2
    //   234: aload_2
    //   235: ifnull +7 -> 242
    //   238: aload_2
    //   239: invokevirtual 786	java/io/FileOutputStream:close	()V
    //   242: aload_1
    //   243: athrow
    //   244: astore_2
    //   245: aload_2
    //   246: invokevirtual 737	java/io/IOException:printStackTrace	()V
    //   249: goto -7 -> 242
    //   252: astore_3
    //   253: aload_1
    //   254: astore_2
    //   255: aload_3
    //   256: astore_1
    //   257: goto -23 -> 234
    //   260: astore_1
    //   261: aload_3
    //   262: astore_2
    //   263: goto -29 -> 234
    //   266: astore_3
    //   267: aload_1
    //   268: astore_2
    //   269: aload_3
    //   270: astore_1
    //   271: goto -37 -> 234
    //   274: astore_1
    //   275: goto -41 -> 234
    //   278: astore_3
    //   279: aload_1
    //   280: astore_2
    //   281: aload_3
    //   282: astore_1
    //   283: goto -71 -> 212
    //   286: astore_1
    //   287: aload 5
    //   289: astore_2
    //   290: goto -78 -> 212
    //   293: astore_3
    //   294: aload_1
    //   295: astore_2
    //   296: aload_3
    //   297: astore_1
    //   298: goto -86 -> 212
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	301	0	this	SDKUtils
    //   0	301	1	paramString1	String
    //   0	301	2	paramString2	String
    //   0	301	3	paramString3	String
    //   0	301	4	paramBoolean	boolean
    //   1	287	5	str	String
    //   10	183	6	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   171	175	203	java/io/IOException
    //   3	101	209	java/lang/Exception
    //   101	113	209	java/lang/Exception
    //   120	129	209	java/lang/Exception
    //   176	186	209	java/lang/Exception
    //   220	224	225	java/io/IOException
    //   3	101	231	finally
    //   101	113	231	finally
    //   120	129	231	finally
    //   176	186	231	finally
    //   238	242	244	java/io/IOException
    //   129	135	252	finally
    //   159	165	260	finally
    //   191	197	260	finally
    //   142	152	266	finally
    //   212	216	274	finally
    //   129	135	278	java/lang/Exception
    //   159	165	286	java/lang/Exception
    //   191	197	286	java/lang/Exception
    //   142	152	293	java/lang/Exception
  }
  
  public void sendSMS(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SENDTO");
    localIntent.setData(Uri.parse("smsto:" + paramString1));
    localIntent.putExtra("sms_body", paramString2);
    this.b.startActivity(localIntent);
  }
  
  public void showToast(String paramString)
  {
    Toast.makeText(this.b, paramString, 1).show();
  }
  
  public String[] splitString(String paramString1, String paramString2, String paramString3)
  {
    if (paramString2 != null) {}
    for (;;)
    {
      try
      {
        if ("".equals(paramString2.trim())) {
          return new String[] { paramString1 };
        }
        if (paramString3 == null) {
          break label135;
        }
        if (!paramString3.equals("")) {
          break label130;
        }
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        return null;
      }
      if ((paramString1 != null) && (!"".equals(paramString1.trim())))
      {
        if (paramString1.endsWith(paramString2))
        {
          paramString1 = paramString1.substring(0, paramString1.lastIndexOf(paramString2));
          if (paramString1.indexOf(paramString2) > 0) {
            return paramString1.split(paramString3);
          }
          if (paramString1.indexOf(paramString2) == 0) {
            return new String[] { paramString1.substring(1) };
          }
          return new String[] { paramString1 };
        }
      }
      else
      {
        label130:
        return null;
        label135:
        paramString3 = paramString2;
      }
    }
  }
  
  public void submit(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!"".equals(paramString2)))
    {
      new AlertDialog.Builder(this.b).setTitle(paramString1).setMessage(paramString2).setPositiveButton("确定", new co(this)).create().show();
      return;
    }
    ((Activity)this.b).finish();
  }
}
