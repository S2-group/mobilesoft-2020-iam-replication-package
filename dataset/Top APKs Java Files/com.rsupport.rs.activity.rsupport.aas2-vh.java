import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.rsupport.rs.activity.edit.ManualConnActivity;
import com.rsupport.rs.activity.lock.ApplicationLockActivity;
import com.rsupport.rs.service.AgentService;
import com.rsupport.rs.service.BeforeService;
import com.rsupport.rs.service.SupportService;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class vh
{
  public static Context a;
  static Toast jdField_a_of_type_AndroidWidgetToast;
  private static String jdField_a_of_type_JavaLangString = "Utility";
  private static Thread jdField_a_of_type_JavaLangThread = null;
  public static boolean a = false;
  public static Context b;
  private static final String b = "Utility";
  private static final String c = "PREF_SUPPORT_TIME";
  private static final String d = "KEY_STARTTIME_MILLS";
  
  static
  {
    jdField_a_of_type_AndroidContentContext = null;
    b = null;
  }
  
  public vh() {}
  
  private static int a()
  {
    try
    {
      cn localCn = cn.a;
      int i = cn.a();
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  public static int a(int paramInt)
  {
    switch (paramInt)
    {
    case 3: 
    case 5: 
    case 6: 
    case 8: 
    case 9: 
    case 10: 
    case 12: 
    case 14: 
    case 15: 
    default: 
      return 101;
    case 1: 
    case 2: 
    case 4: 
    case 7: 
    case 11: 
      return 103;
    }
    return 104;
  }
  
  private static int a(Activity paramActivity)
  {
    Rect localRect = new Rect();
    paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    vf.c("Utility", "Resolution Height : " + localRect.height());
    vf.c("Utility", "Resolution Width  : " + localRect.width());
    return localRect.height();
  }
  
  public static int a(Context paramContext, int paramInt)
  {
    return (int)(int)(paramContext.getResources().getDisplayMetrics().density * paramInt + 0.5F);
  }
  
  private static int a(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return 0;
    }
    return Integer.parseInt(paramString);
  }
  
  public static int a(String paramString1, String paramString2)
  {
    if ((paramString1.length() == 0) || (paramString2.length() == 0)) {
      return -1;
    }
    int i = paramString1.indexOf("<" + paramString2);
    if (i < 0) {
      return -2;
    }
    return paramString1.indexOf(">", i) + 1;
  }
  
  private static long a()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static long a(Context paramContext)
  {
    return paramContext.getSharedPreferences("PREF_SUPPORT_TIME", 0).getLong("KEY_STARTTIME_MILLS", 0L);
  }
  
  private static ResolveInfo a()
  {
    Object localObject = new Intent();
    ((Intent)localObject).setAction("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    PackageManager localPackageManager = jdField_a_of_type_AndroidContentContext.getPackageManager();
    List localList = localPackageManager.queryIntentActivities((Intent)localObject, 65536);
    vf.c(jdField_a_of_type_JavaLangString, "ResolveInfo List : " + localList);
    if (localList.size() == 1) {
      return (ResolveInfo)localList.get(0);
    }
    localObject = localPackageManager.resolveActivity((Intent)localObject, 65536);
    int j = localList.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return (ResolveInfo)localList.get(0);
      }
      if (((ResolveInfo)localList.get(i)).activityInfo.packageName.equals(((ResolveInfo)localObject).activityInfo.packageName)) {
        return (ResolveInfo)localList.get(i);
      }
      i += 1;
    }
  }
  
  public static Resources a()
  {
    if (jdField_a_of_type_AndroidContentContext != null) {
      return jdField_a_of_type_AndroidContentContext.getResources();
    }
    return null;
  }
  
  public static String a()
  {
    jdField_a_of_type_AndroidContentContext.getSharedPreferences("setting", 0);
    rl.jdField_a_of_type_Boolean = false;
    return "http://";
  }
  
  public static String a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 1: 
      return "GPRS";
    case 2: 
      return "EDGE";
    case 3: 
      return "UMTS";
    case 8: 
      return "HSDPA";
    case 9: 
      return "HSUPA";
    case 10: 
      return "HSPA";
    case 4: 
      return "CDMA";
    case 5: 
      return "CDMA - EvDo rev. 0";
    case 6: 
      return "CDMA - EvDo rev. A";
    case 12: 
      return "CDMA - EvDo rev. B";
    case 7: 
      return "CDMA - 1xRTT";
    case 13: 
      return "LTE";
    case 14: 
      return "CDMA - eHRPD";
    case 11: 
      return "iDEN";
    }
    return "HSPA+";
  }
  
  public static String a(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(paramLong));
  }
  
  public static String a(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration().locale;
    return paramContext.getLanguage() + "-" + paramContext.getCountry();
  }
  
  public static String a(String paramString)
  {
    String str = paramString;
    if (paramString.charAt(paramString.length() - 1) != '/') {
      str = paramString + "/";
    }
    return str;
  }
  
  public static String a(String paramString, Object paramObject)
  {
    String str = paramString + " = ";
    paramString = str;
    if (paramObject != null) {
      paramString = str + paramObject.toString() + "\n";
    }
    return paramString;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    return a(paramString1, paramString2, 0);
  }
  
  public static String a(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1.length() == 0) || (paramString2.length() == 0)) {}
    int i;
    do
    {
      do
      {
        return null;
        paramInt = paramString1.indexOf("<" + paramString2, paramInt);
      } while (paramInt < 0);
      i = paramString1.indexOf("</" + paramString2, paramString2.length() + paramInt + 1);
    } while (i < 0);
    return paramString1.substring(paramString1.indexOf(">", paramInt) + 1, i);
  }
  
  private static short a(Context paramContext)
  {
    Rect localRect = new Rect();
    ((Activity)paramContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    return (short)localRect.top;
  }
  
  public static void a()
  {
    if (a() != null)
    {
      String str = a().getString(2131296313);
      View localView = ((Activity)jdField_a_of_type_AndroidContentContext).getLayoutInflater().inflate(2130903074, (ViewGroup)((Activity)jdField_a_of_type_AndroidContentContext).findViewById(2131493057));
      TextView localTextView = (TextView)localView.findViewById(2131492924);
      Toast localToast = new Toast(jdField_a_of_type_AndroidContentContext);
      localToast.setGravity(16, 0, 0);
      localToast.setView(localView);
      localToast.setDuration(1);
      localTextView.setText("\n" + str + "\n");
      localToast.show();
    }
  }
  
  public static void a(int paramInt)
  {
    if (a() != null) {
      a(a().getString(paramInt));
    }
  }
  
  public static void a(Activity paramActivity)
  {
    paramActivity.getWindow().addFlags(2621568);
  }
  
  public static void a(Activity paramActivity, Handler paramHandler1, Handler paramHandler2)
  {
    new vi(paramActivity, paramHandler1, paramHandler2).start();
  }
  
  public static void a(Activity paramActivity, short paramShort, String paramString, ru paramRu, Handler paramHandler)
  {
    paramActivity = new vl(paramRu, paramActivity, paramShort, paramString, paramHandler);
    jdField_a_of_type_JavaLangThread = paramActivity;
    paramActivity.start();
  }
  
  public static void a(Service paramService)
  {
    if (Integer.parseInt(Build.VERSION.SDK) < 14) {}
    int i;
    do
    {
      return;
      i = paramService.getSharedPreferences("background.process", 0).getInt("count", -1);
    } while (Integer.parseInt(Build.VERSION.SDK) < 14);
    try
    {
      paramService = cn.a;
      cn.a(i);
      return;
    }
    catch (Exception paramService)
    {
      paramService.printStackTrace();
    }
  }
  
  public static void a(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("reconnect.info", 0).edit();
    paramContext.putString("connurl", "");
    paramContext.putString("roomid", "");
    paramContext.putString("agentid", "");
    paramContext.putString("guid", "");
    paramContext.commit();
  }
  
  private static void a(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("background.process", 0).edit();
    paramContext.putInt("count", paramInt);
    paramContext.commit();
  }
  
  public static void a(Context paramContext, long paramLong)
  {
    paramContext = paramContext.getSharedPreferences("PREF_SUPPORT_TIME", 0).edit();
    paramContext.putLong("KEY_STARTTIME_MILLS", paramLong);
    paramContext.commit();
  }
  
  public static void a(Context paramContext, String paramString)
  {
    new Toast(paramContext).setGravity(16, 0, 0);
    Toast.makeText(paramContext, paramString, 1).show();
  }
  
  public static void a(String paramString)
  {
    if (jdField_a_of_type_AndroidContentContext != null)
    {
      Toast localToast = new Toast(jdField_a_of_type_AndroidContentContext);
      View localView = LayoutInflater.from(jdField_a_of_type_AndroidContentContext).inflate(2130903071, null);
      ((TextView)localView.findViewById(2131492924)).setText("\n" + paramString + "\n");
      localToast.setGravity(16, 0, 0);
      localToast.setDuration(1);
      localToast.setView(localView);
      localToast.show();
    }
  }
  
  public static boolean a()
  {
    boolean bool = false;
    if (Build.VERSION.SDK_INT >= 14) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean a(Activity paramActivity)
  {
    Rect localRect = new Rect();
    paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    return localRect.height() >= 600;
  }
  
  public static boolean a(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0x4) == 4;
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    int j = paramContext.size();
    int i = 0;
    if (i >= j) {
      return bool;
    }
    if (paramString.equals(((ActivityManager.RunningAppProcessInfo)paramContext.get(i)).processName.toLowerCase())) {
      bool = true;
    }
    for (;;)
    {
      i += 1;
      break;
    }
  }
  
  public static boolean a(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null) {}
    while ((paramIntent.getString("serveraddr") == null) || (paramIntent.getString("serveraddr").length() <= 0) || (paramIntent.getString("conncode") == null) || (paramIntent.getString("conncode").length() <= 0)) {
      return false;
    }
    return true;
  }
  
  public static boolean a(String paramString)
  {
    return Pattern.compile("https?://[-\\w.]+(:\\d+)?(/([\\w/_.]*)?)?").matcher(paramString).matches();
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission(paramString) == 0)
    {
      vf.c(jdField_a_of_type_JavaLangString, "permission is IN: " + paramString);
      return true;
    }
    vf.c(jdField_a_of_type_JavaLangString, "permission is NO: " + paramString);
    return false;
  }
  
  private static int b(int paramInt)
  {
    if (jdField_a_of_type_AndroidContentContext == null) {
      return 0;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)jdField_a_of_type_AndroidContentContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.densityDpi / 160 * paramInt;
  }
  
  public static int b(String paramString1, String paramString2)
  {
    return c(paramString1, paramString2);
  }
  
  public static String b()
  {
    if (jdField_a_of_type_AndroidContentContext == null) {
      return null;
    }
    return a(jdField_a_of_type_AndroidContentContext);
  }
  
  public static String b(int paramInt)
  {
    return a().getString(paramInt);
  }
  
  private static String b(long paramLong)
  {
    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(paramLong));
  }
  
  public static String b(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.getLanguage();
  }
  
  public static String b(String paramString)
  {
    return paramString.replaceAll("[^\\d]", "");
  }
  
  public static void b()
  {
    vf.c(jdField_a_of_type_JavaLangString, "updateKeepServiceNotification is start");
    if (jdField_a_of_type_AndroidContentContext == null) {}
    while ((!qq.b) || (m.jdField_a_of_type_AndroidAppNotification == null)) {
      return;
    }
    ((NotificationManager)jdField_a_of_type_AndroidContentContext.getSystemService("notification")).notify(2, m.jdField_a_of_type_AndroidAppNotification);
  }
  
  private static void b(int paramInt)
  {
    if (Integer.parseInt(Build.VERSION.SDK) < 14) {
      return;
    }
    try
    {
      cn localCn = cn.a;
      cn.a(paramInt);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void b(Activity paramActivity, Handler paramHandler1, Handler paramHandler2)
  {
    paramHandler1.sendEmptyMessage(0);
    new vj(paramActivity, paramHandler2).start();
  }
  
  public static void b(Service paramService)
  {
    a(paramService, paramService.getString(2131296423));
    a(paramService);
    paramService.stopService(new Intent(paramService, AgentService.class));
    paramService.stopService(new Intent(paramService, SupportService.class));
    ((NotificationManager)paramService.getSystemService("notification")).cancelAll();
    ((ActivityManager)paramService.getSystemService("activity")).restartPackage(paramService.getPackageName());
  }
  
  public static void b(Context paramContext)
  {
    try
    {
      SupportService.connectTime = null;
      if (qq.a(null) != null) {
        qq.a(null).f();
      }
      Thread.sleep(2000L);
      qq localQq = qq.a(paramContext);
      if ((paramContext instanceof cz)) {
        localQq.a((cz)paramContext);
      }
      localQq.a.a(null);
      localQq.b();
      new vk(paramContext).start();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    vf.c(jdField_a_of_type_JavaLangString, "callBrowser");
    if (paramString == null) {
      return;
    }
    Intent localIntent = new Intent(paramContext, AgentService.class);
    localIntent.putExtra("type", "browser");
    localIntent.putExtra("url", paramString);
    localIntent.setFlags(268435456);
    paramContext.startService(localIntent);
  }
  
  public static void b(String paramString)
  {
    View localView = ((Activity)jdField_a_of_type_AndroidContentContext).getLayoutInflater().inflate(2130903071, (ViewGroup)((Activity)jdField_a_of_type_AndroidContentContext).findViewById(2131493057));
    ((TextView)localView.findViewById(2131492924)).setText("\n" + paramString + "\n");
    if (jdField_a_of_type_AndroidWidgetToast == null)
    {
      paramString = new Toast(jdField_a_of_type_AndroidContentContext);
      jdField_a_of_type_AndroidWidgetToast = paramString;
      paramString.setGravity(16, 0, 0);
      jdField_a_of_type_AndroidWidgetToast.setDuration(1);
      jdField_a_of_type_AndroidWidgetToast.setView(localView);
    }
    jdField_a_of_type_AndroidWidgetToast.show();
  }
  
  public static boolean b()
  {
    Object localObject = (WifiManager)jdField_a_of_type_AndroidContentContext.getSystemService("wifi");
    WifiInfo localWifiInfo = ((WifiManager)localObject).getConnectionInfo();
    if (((WifiManager)localObject).isWifiEnabled())
    {
      vf.b(jdField_a_of_type_JavaLangString, "SSID:" + localWifiInfo.getSSID());
      localObject = ((ConnectivityManager)jdField_a_of_type_AndroidContentContext.getSystemService("connectivity")).getNetworkInfo(1).getState();
      if ((localObject == NetworkInfo.State.CONNECTED) || (localObject == NetworkInfo.State.CONNECTING)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean b(Activity paramActivity)
  {
    paramActivity = paramActivity.getIntent().getExtras();
    if (paramActivity == null) {}
    while ((paramActivity.getString("type") == null) || (!paramActivity.getString("type").equals("usb")) || (paramActivity.getString("serveraddr") == null) || (paramActivity.getString("serveraddr").length() <= 0) || (paramActivity.getString("conncode") == null) || (paramActivity.getString("conncode").length() <= 0)) {
      return false;
    }
    return true;
  }
  
  private static boolean b(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isAvailable());
  }
  
  public static boolean b(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null) {}
    while ((paramIntent.getString("serveraddr") == null) || (paramIntent.getString("serveraddr").length() <= 0) || (paramIntent.getString("phonenumber") == null) || (paramIntent.getString("phonenumber").length() <= 0)) {
      return false;
    }
    return true;
  }
  
  private static boolean b(String paramString)
  {
    if (paramString.contains(":")) {}
    while (paramString.equals("127.0.0.1")) {
      return false;
    }
    return true;
  }
  
  public static int c(String paramString1, String paramString2)
  {
    paramString1 = a(paramString1, paramString2, 0);
    if ((paramString1 == null) || (paramString1.length() == 0)) {
      return 0;
    }
    return Integer.parseInt(paramString1);
  }
  
  public static String c()
  {
    if (jdField_a_of_type_AndroidContentContext == null) {
      return null;
    }
    return b(jdField_a_of_type_AndroidContentContext);
  }
  
  private static String c(int paramInt)
  {
    return (paramInt & 0xFF) + "." + (paramInt >> 8 & 0xFF) + "." + (paramInt >> 16 & 0xFF) + "." + (paramInt >> 24 & 0xFF);
  }
  
  public static String c(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getLine1Number();
  }
  
  public static String c(String paramString)
  {
    try
    {
      paramString = new String(uv.a(paramString));
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static void c()
  {
    if (jdField_a_of_type_AndroidContentContext == null) {}
    NetworkInfo localNetworkInfo;
    do
    {
      do
      {
        return;
      } while (qq.a(null) == null);
      localNetworkInfo = ((ConnectivityManager)jdField_a_of_type_AndroidContentContext.getSystemService("connectivity")).getActiveNetworkInfo();
    } while (localNetworkInfo == null);
    if (localNetworkInfo.getTypeName().equals("WIFI")) {}
    for (int i = 11;; i = 3)
    {
      qq.a(null).a(i);
      return;
    }
  }
  
  public static void c(Context paramContext)
  {
    paramContext.stopService(new Intent(paramContext, BeforeService.class));
    paramContext.stopService(new Intent(paramContext, AgentService.class));
    paramContext.stopService(new Intent(paramContext, SupportService.class));
    ((NotificationManager)paramContext.getSystemService("notification")).cancelAll();
    if (mf.jdField_a_of_type_JavaUtilArrayList != null)
    {
      mf.jdField_a_of_type_JavaUtilArrayList.clear();
      mf.jdField_a_of_type_JavaUtilArrayList = null;
    }
    SupportService.connectTime = null;
    if ((jdField_a_of_type_AndroidContentContext != null) && (jdField_a_of_type_AndroidContentContext.getClass().equals(ManualConnActivity.class))) {
      ((ManualConnActivity)jdField_a_of_type_AndroidContentContext).finish();
    }
    if (rl.a() != null) {
      rl.a().k = null;
    }
    m.j = false;
    mf.jdField_a_of_type_ComRsupportRsActivityEditChattingActivity = null;
    m.jdField_a_of_type_AndroidAppNotification = null;
    m.jdField_a_of_type_Boolean = false;
    m.b = false;
    m.k = false;
    m.l = false;
    ApplicationLockActivity.c();
  }
  
  private static void c(String paramString)
  {
    View localView = ((Activity)jdField_a_of_type_AndroidContentContext).getLayoutInflater().inflate(2130903074, (ViewGroup)((Activity)jdField_a_of_type_AndroidContentContext).findViewById(2131493057));
    TextView localTextView = (TextView)localView.findViewById(2131492924);
    Toast localToast = new Toast(jdField_a_of_type_AndroidContentContext);
    localToast.setGravity(16, 0, 0);
    localToast.setView(localView);
    localToast.setDuration(1);
    localTextView.setText("\n" + paramString + "\n");
    localToast.show();
  }
  
  public static boolean c()
  {
    return (k()) || (b()) || (m());
  }
  
  public static boolean c(Activity paramActivity)
  {
    paramActivity = paramActivity.getIntent().getExtras();
    if (paramActivity == null) {}
    while ((paramActivity.getString("serveraddr") == null) || (paramActivity.getString("serveraddr").length() <= 0) || (paramActivity.getString("conncode") == null) || (paramActivity.getString("conncode").length() <= 0)) {
      return false;
    }
    return true;
  }
  
  private static boolean c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0);
      int j = 0;
      int i = 0;
      int k = 0;
      if (j >= paramContext.size()) {
        return false;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.get(j);
      if (localApplicationInfo.packageName.equals("com.rsupport.srn")) {
        k = 1;
      }
      boolean bool = localApplicationInfo.packageName.equals("com.rsupport.uinput");
      if (bool) {
        i = 1;
      }
      for (;;)
      {
        if ((k != 0) && (i != 0)) {
          return true;
        }
        j += 1;
        break;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
  }
  
  private static boolean c(String paramString)
  {
    Iterator localIterator = ((ActivityManager)jdField_a_of_type_AndroidContentContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!paramString.equals(((ActivityManager.RunningServiceInfo)localIterator.next()).service.getClassName()));
    Log.d("Utility", paramString + "Service isLive");
    return true;
  }
  
  private static int d(String paramString1, String paramString2)
  {
    if ((paramString1.length() == 0) || (paramString2.length() == 0)) {
      return -1;
    }
    int i = paramString1.indexOf("<" + paramString2);
    if (i < 0) {
      return -2;
    }
    return paramString1.indexOf(">", i) + 1;
  }
  
  public static String d()
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    return new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("")).append(localGregorianCalendar.get(1)).append(";").toString())).append(localGregorianCalendar.get(2) + 1).append(";").toString())).append(localGregorianCalendar.get(5)).append(";").toString())).append(localGregorianCalendar.get(11)).append(";").toString() + localGregorianCalendar.get(12);
  }
  
  public static String d(Context paramContext)
  {
    String str = ((TelephonyManager)paramContext.getSystemService("phone")).getLine1Number();
    paramContext = str;
    if (str != null)
    {
      if (str.trim().equals("")) {
        paramContext = null;
      }
    }
    else {
      return paramContext;
    }
    return b(str);
  }
  
  public static String d(String paramString)
  {
    byte[] arrayOfByte = paramString.getBytes();
    paramString = new char[16];
    paramString[0] = 48;
    paramString[1] = 49;
    paramString[2] = 50;
    paramString[3] = 51;
    paramString[4] = 52;
    paramString[5] = 53;
    paramString[6] = 54;
    paramString[7] = 55;
    paramString[8] = 56;
    paramString[9] = 57;
    paramString[10] = 65;
    paramString[11] = 66;
    paramString[12] = 67;
    paramString[13] = 68;
    paramString[14] = 69;
    paramString[15] = 70;
    paramString;
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).reset();
      ((MessageDigest)localObject).update(arrayOfByte);
      arrayOfByte = ((MessageDigest)localObject).digest();
      localObject = new char[arrayOfByte.length * 2];
      int i = 0;
      for (;;)
      {
        if (i >= arrayOfByte.length) {
          return new String((char[])localObject);
        }
        int j = (arrayOfByte[i] + 256) % 256;
        localObject[(i * 2 + 0)] = paramString[(j >> 4)];
        localObject[(i * 2 + 1)] = paramString[(j & 0xF)];
        i += 1;
      }
      return null;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void d()
  {
    if (Integer.parseInt(Build.VERSION.SDK) < 14) {
      return;
    }
    Object localObject = jdField_a_of_type_AndroidContentContext;
    int i = a();
    localObject = ((Context)localObject).getSharedPreferences("background.process", 0).edit();
    ((SharedPreferences.Editor)localObject).putInt("count", i);
    ((SharedPreferences.Editor)localObject).commit();
    try
    {
      localObject = cn.a;
      cn.a(100);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private static void d(Activity paramActivity, Handler paramHandler1, Handler paramHandler2)
  {
    qq localQq = qq.a(paramActivity);
    if ((paramActivity instanceof cz)) {
      localQq.a((cz)paramActivity);
    }
    localQq.a.a(null);
    localQq.b();
    b(paramActivity, paramHandler1, paramHandler2);
  }
  
  private static void d(Context paramContext)
  {
    qq localQq = qq.a(paramContext);
    if ((paramContext instanceof cz)) {
      localQq.a((cz)paramContext);
    }
    localQq.a.a(null);
    localQq.b();
    new vk(paramContext).start();
  }
  
  private static void d(String paramString)
  {
    ActivityManager localActivityManager = (ActivityManager)jdField_a_of_type_AndroidContentContext.getSystemService("activity");
    List localList = localActivityManager.getRunningAppProcesses();
    int i;
    if (localList.size() > 35)
    {
      i = 0;
      if (i < localList.size()) {}
    }
    else
    {
      return;
    }
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localList.get(i);
    if ((localRunningAppProcessInfo.processName.contains("rsupport")) || (localRunningAppProcessInfo.processName.contains("launcher")) || (localRunningAppProcessInfo.processName.contains("system")) || (localRunningAppProcessInfo.processName.contains("setting")) || (localRunningAppProcessInfo.processName.contains("ime")) || (localRunningAppProcessInfo.processName.contains("process")) || (localRunningAppProcessInfo.processName.contains("browser")) || (localRunningAppProcessInfo.processName.contains("nfc")) || (localRunningAppProcessInfo.processName.contains("keychain")) || (localRunningAppProcessInfo.processName.contains("facebook")) || (localRunningAppProcessInfo.processName.contains("google")) || (localRunningAppProcessInfo.processName.equals(paramString)) || (localRunningAppProcessInfo.processName.equals(jdField_a_of_type_AndroidContentContext.getPackageName())) || (localRunningAppProcessInfo.importance == 100)) {
      Log.d("Process", "Not Kill Package Name : " + localRunningAppProcessInfo.processName);
    }
    for (;;)
    {
      i += 1;
      break;
      localActivityManager.restartPackage(localRunningAppProcessInfo.processName);
      Log.d("Process", "Kill Package Name : " + localRunningAppProcessInfo.processName);
    }
  }
  
  public static boolean d()
  {
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)jdField_a_of_type_AndroidContentContext.getSystemService("connectivity");
      bool2 = localConnectivityManager.getNetworkInfo(1).isConnected();
      boolean bool3;
      localException1.printStackTrace();
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          bool1 = m();
        }
        catch (Exception localException2)
        {
          boolean bool2;
          boolean bool1 = false;
          continue;
        }
        try
        {
          bool3 = localConnectivityManager.getNetworkInfo(0).isConnected();
          if ((!bool3) && (!bool2)) {
            continue;
          }
          return true;
        }
        catch (Exception localException3) {}
      }
      localException1 = localException1;
      bool1 = false;
      bool2 = false;
    }
    return (bool2) || (bool1);
  }
  
  public static boolean d(Activity paramActivity)
  {
    paramActivity = paramActivity.getIntent().getExtras();
    if (paramActivity == null) {}
    while ((paramActivity.getString("serveraddr") == null) || (paramActivity.getString("serveraddr").length() <= 0) || (paramActivity.getString("phonenumber") == null) || (paramActivity.getString("phonenumber").length() <= 0)) {
      return false;
    }
    return true;
  }
  
  private static boolean d(String paramString)
  {
    try
    {
      boolean bool = new File(paramString).exists();
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static String e()
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    for (;;)
    {
      int i;
      try
      {
        Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
        localObject1 = localObject2;
        new Vector();
        localObject1 = localObject2;
        new Vector();
        localObject1 = localObject2;
        if (!localEnumeration1.hasMoreElements()) {
          return localObject2;
        }
        localObject1 = localObject2;
        localObject3 = (NetworkInterface)localEnumeration1.nextElement();
        localObject1 = localObject2;
        ((NetworkInterface)localObject3).getDisplayName();
        localObject1 = localObject2;
        Enumeration localEnumeration2 = ((NetworkInterface)localObject3).getInetAddresses();
        localObject3 = localObject2;
        localObject2 = localObject3;
        localObject1 = localObject3;
        if (!localEnumeration2.hasMoreElements()) {
          continue;
        }
        localObject1 = localObject3;
        localObject2 = ((InetAddress)localEnumeration2.nextElement()).toString().substring(1);
        localObject1 = localObject2;
        boolean bool;
        localSocketException1.printStackTrace();
      }
      catch (SocketException localSocketException1)
      {
        try
        {
          if (localObject1.contains(":"))
          {
            i = 0;
          }
          else
          {
            bool = localObject1.equals("127.0.0.1");
            if (bool) {
              i = 0;
            } else {
              i = 1;
            }
          }
        }
        catch (SocketException localSocketException2)
        {
          for (;;) {}
          return localObject1;
        }
        localSocketException1 = localSocketException1;
      }
      return localObject1;
      while (i != 0) {}
      Object localObject3 = localObject1;
    }
  }
  
  public static String e(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "default_input_method");
    InputMethodInfo localInputMethodInfo;
    ResolveInfo localResolveInfo;
    do
    {
      Iterator localIterator1 = ((InputMethodManager)paramContext.getSystemService("input_method")).getEnabledInputMethodList().iterator();
      Iterator localIterator2;
      while (!localIterator2.hasNext())
      {
        do
        {
          if (!localIterator1.hasNext()) {
            return "";
          }
          localInputMethodInfo = (InputMethodInfo)localIterator1.next();
          localInputMethodInfo.getServiceInfo();
        } while (!str.equals(localInputMethodInfo.getId()));
        localIterator2 = paramContext.getPackageManager().queryIntentServices(new Intent("android.view.InputMethod"), 4).iterator();
      }
      localResolveInfo = (ResolveInfo)localIterator2.next();
    } while (!localResolveInfo.serviceInfo.packageName.equals(localInputMethodInfo.getPackageName()));
    return localResolveInfo.loadLabel(paramContext.getPackageManager()).toString();
  }
  
  private static String e(String paramString)
  {
    return uv.a(paramString.getBytes());
  }
  
  public static void e()
  {
    try
    {
      Object localObject2 = jdField_a_of_type_AndroidContentContext.getSystemService("statusbar");
      Object localObject1 = Class.forName("android.app.StatusBarManager");
      if (Build.VERSION.SDK_INT <= 16) {}
      for (localObject1 = ((Class)localObject1).getMethod("collapse", new Class[0]);; localObject1 = ((Class)localObject1).getMethod("collapsePanels", new Class[0]))
      {
        ((Method)localObject1).setAccessible(true);
        ((Method)localObject1).invoke(localObject2, new Object[0]);
        return;
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private static void e(Context paramContext)
  {
    new vk(paramContext).start();
  }
  
  private static void e(String paramString)
  {
    ActivityManager localActivityManager = (ActivityManager)jdField_a_of_type_AndroidContentContext.getSystemService("activity");
    List localList = localActivityManager.getRunningAppProcesses();
    int i;
    if (localList.size() > 35)
    {
      i = 0;
      if (i < localList.size()) {}
    }
    else
    {
      return;
    }
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localList.get(i);
    if ((localRunningAppProcessInfo.processName.contains("rsupport")) || (localRunningAppProcessInfo.processName.contains("launcher")) || (localRunningAppProcessInfo.processName.contains("system")) || (localRunningAppProcessInfo.processName.contains("setting")) || (localRunningAppProcessInfo.processName.contains("ime")) || (localRunningAppProcessInfo.processName.contains("process")) || (localRunningAppProcessInfo.processName.contains("browser")) || (localRunningAppProcessInfo.processName.contains("nfc")) || (localRunningAppProcessInfo.processName.contains("push")) || (localRunningAppProcessInfo.processName.contains("keychain")) || (localRunningAppProcessInfo.processName.contains("facebook")) || (localRunningAppProcessInfo.processName.contains("google")) || (localRunningAppProcessInfo.processName.contains("lge")) || (localRunningAppProcessInfo.processName.equals(paramString)) || (localRunningAppProcessInfo.processName.equals(jdField_a_of_type_AndroidContentContext.getPackageName())) || (localRunningAppProcessInfo.importance == 100) || (localRunningAppProcessInfo.importance == 300)) {
      Log.d("Process", "Not Kill Package Name : " + localRunningAppProcessInfo.processName);
    }
    for (;;)
    {
      i += 1;
      break;
      localActivityManager.restartPackage(localRunningAppProcessInfo.processName);
      Log.d("Process", "Kill Package Name : " + localRunningAppProcessInfo.processName);
    }
  }
  
  public static boolean e()
  {
    return new File("/dev/graphics/fb0").canRead();
  }
  
  public static boolean e(Activity paramActivity)
  {
    paramActivity = paramActivity.getIntent().getExtras();
    if (paramActivity == null) {}
    while ((paramActivity.getString("type") == null) || (!paramActivity.getString("type").equals("help")) || (paramActivity.getString("counterkey") == null) || (paramActivity.getString("counterkey").length() <= 0)) {
      return false;
    }
    return true;
  }
  
  public static String f()
  {
    return jdField_a_of_type_AndroidContentContext.getPackageName();
  }
  
  public static String f(Context paramContext)
  {
    paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getDhcpInfo();
    if ((paramContext != null) && (paramContext.ipAddress > 0))
    {
      int i = paramContext.ipAddress;
      return (i & 0xFF) + "." + (i >> 8 & 0xFF) + "." + (i >> 16 & 0xFF) + "." + (i >> 24 & 0xFF);
    }
    return e();
  }
  
  public static boolean f()
  {
    File localFile = new File("/dev/uinput");
    return (localFile.canRead()) && (localFile.canWrite());
  }
  
  public static boolean f(Activity paramActivity)
  {
    paramActivity = paramActivity.getIntent().getExtras();
    if (paramActivity == null) {}
    while (!paramActivity.getBoolean("exit", false)) {
      return false;
    }
    return true;
  }
  
  public static String g()
  {
    Object localObject2 = new Intent();
    ((Intent)localObject2).setAction("android.intent.action.MAIN");
    ((Intent)localObject2).addCategory("android.intent.category.HOME");
    PackageManager localPackageManager = jdField_a_of_type_AndroidContentContext.getPackageManager();
    Object localObject1 = localPackageManager.queryIntentActivities((Intent)localObject2, 65536);
    vf.c(jdField_a_of_type_JavaLangString, "ResolveInfo List : " + localObject1);
    if (((List)localObject1).size() == 1)
    {
      localObject1 = (ResolveInfo)((List)localObject1).get(0);
      return ((ResolveInfo)localObject1).activityInfo.packageName;
    }
    localObject2 = localPackageManager.resolveActivity((Intent)localObject2, 65536);
    int j = ((List)localObject1).size();
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        localObject1 = (ResolveInfo)((List)localObject1).get(0);
        break;
      }
      if (((ResolveInfo)((List)localObject1).get(i)).activityInfo.packageName.equals(((ResolveInfo)localObject2).activityInfo.packageName))
      {
        localObject1 = (ResolveInfo)((List)localObject1).get(i);
        break;
      }
      i += 1;
    }
  }
  
  public static String g(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 128);
      paramContext = "ver " + paramContext.versionName + "\n     Engine C (Build 11)";
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e("Utility", Log.getStackTraceString(paramContext));
    }
    return "";
  }
  
  private static void g()
  {
    if (!vm.a("/system/bin/chdev")) {}
    File localFile;
    do
    {
      return;
      localFile = new File("/dev/uinput");
    } while ((localFile.canRead()) && (localFile.canWrite()));
    try
    {
      Runtime.getRuntime().exec("/system/bin/chdev").waitFor();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static boolean g()
  {
    boolean bool2 = true;
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "ALCATEL_one_touch_916";
    arrayOfString[1] = "ALCATEL ONE TOUCH 916";
    arrayOfString[2] = "ALCATEL_one_touch_916A";
    arrayOfString[3] = "ALCATEL ONE TOUCH 916A";
    arrayOfString[4] = "ALCATEL_one_touch_916d";
    arrayOfString[5] = "ALCATEL ONE TOUCH 916d";
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1;
      if (i >= j) {
        bool1 = false;
      }
      String str;
      do
      {
        return bool1;
        str = arrayOfString[i];
        bool1 = bool2;
      } while (Build.MODEL.equalsIgnoreCase(str));
      i += 1;
    }
  }
  
  public static boolean g(Activity paramActivity)
  {
    Object localObject = paramActivity.getIntent().getExtras();
    if (localObject == null) {}
    long l;
    do
    {
      do
      {
        return false;
      } while (!((Bundle)localObject).getBoolean("REBOOT", false));
      paramActivity = paramActivity.getSharedPreferences("reconnect.info", 0);
      localObject = paramActivity.getString("connurl", "");
      l = paramActivity.getLong("timelife", 0L);
    } while ((System.currentTimeMillis() - l > 1800000L) || (((String)localObject).equals("")));
    return true;
  }
  
  private static String h(Context paramContext)
  {
    ActivityManager.RunningServiceInfo localRunningServiceInfo = null;
    Object localObject = paramContext.getPackageManager();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    localObject = ((PackageManager)localObject).queryIntentServices(new Intent("android.view.InputMethod"), 4).iterator();
    int i;
    if (!((Iterator)localObject).hasNext())
    {
      localObject = ((ActivityManager)paramContext.getApplicationContext().getSystemService("activity")).getRunningServices(50);
      i = 0;
      paramContext = localRunningServiceInfo;
    }
    int j;
    for (;;)
    {
      if (i >= ((List)localObject).size())
      {
        return paramContext;
        ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
        String str = localResolveInfo.loadLabel(paramContext.getPackageManager()).toString();
        localArrayList2.add(localResolveInfo.serviceInfo.packageName);
        localArrayList1.add(str);
        break;
      }
      localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((List)localObject).get(i);
      j = 0;
      if (j < localArrayList2.size()) {
        break label179;
      }
      i += 1;
    }
    label179:
    if (((String)localArrayList2.get(j)).equals(localRunningServiceInfo.service.getPackageName())) {
      paramContext = (String)localArrayList1.get(j);
    }
    for (;;)
    {
      j += 1;
      break;
    }
  }
  
  private static void h()
  {
    vf.c("Utility", "graphics permission : " + e());
    vf.c("Utility", "uinput permission : " + f());
  }
  
  public static boolean h()
  {
    boolean bool = false;
    if (jdField_a_of_type_AndroidContentContext.getPackageName().contains("doro")) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean h(Activity paramActivity)
  {
    if (!c()) {}
    do
    {
      return false;
      paramActivity = paramActivity.getIntent().getExtras();
    } while ((paramActivity != null) && (!paramActivity.getBoolean("Update")));
    return true;
  }
  
  private static void i()
  {
    qq.a(null).f();
    SupportService.connectTime = null;
  }
  
  public static boolean i()
  {
    return true;
  }
  
  private static void j()
  {
    SupportService.connectTime = null;
    if (qq.a(null) == null) {
      return;
    }
    qq.a(null).f();
  }
  
  /* Error */
  public static boolean j()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_0
    //   2: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   5: ldc_w 1248
    //   8: invokestatic 93	vf:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   11: new 72	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 1249	java/lang/StringBuilder:<init>	()V
    //   18: astore_3
    //   19: new 1251	java/io/BufferedReader
    //   22: dup
    //   23: new 1253	java/io/InputStreamReader
    //   26: dup
    //   27: new 1255	java/io/FileInputStream
    //   30: dup
    //   31: ldc_w 1257
    //   34: invokespecial 1258	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   37: invokespecial 1261	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   40: invokespecial 1264	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   43: astore_2
    //   44: aload_2
    //   45: astore_1
    //   46: aload_2
    //   47: invokevirtual 1267	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   50: astore 4
    //   52: aload 4
    //   54: ifnonnull +93 -> 147
    //   57: aload_2
    //   58: astore_1
    //   59: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   62: new 72	java/lang/StringBuilder
    //   65: dup
    //   66: ldc_w 1269
    //   69: invokespecial 77	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   72: aload_3
    //   73: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   76: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokestatic 93	vf:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   82: aload_2
    //   83: astore_1
    //   84: aload_3
    //   85: invokevirtual 1270	java/lang/StringBuilder:length	()I
    //   88: ifle +121 -> 209
    //   91: aload_2
    //   92: astore_1
    //   93: aload_3
    //   94: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: ldc_w 1272
    //   100: invokevirtual 758	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   103: ifne +24 -> 127
    //   106: aload_2
    //   107: astore_1
    //   108: aload_3
    //   109: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: ldc_w 1272
    //   115: invokestatic 1276	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   118: invokevirtual 1280	java/lang/String:toUpperCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   121: invokevirtual 758	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   124: ifeq +85 -> 209
    //   127: aload_2
    //   128: astore_1
    //   129: ldc_w 446
    //   132: ldc_w 1282
    //   135: invokestatic 1284	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   138: pop
    //   139: aload_2
    //   140: invokevirtual 1287	java/io/BufferedReader:close	()V
    //   143: iconst_1
    //   144: istore_0
    //   145: iload_0
    //   146: ireturn
    //   147: aload_2
    //   148: astore_1
    //   149: aload_3
    //   150: aload 4
    //   152: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: pop
    //   156: goto -112 -> 44
    //   159: astore_3
    //   160: aload_2
    //   161: astore_1
    //   162: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   165: aload_3
    //   166: invokestatic 1157	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   169: invokestatic 1289	vf:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   172: aload_2
    //   173: ifnull -28 -> 145
    //   176: aload_2
    //   177: invokevirtual 1287	java/io/BufferedReader:close	()V
    //   180: iconst_0
    //   181: ireturn
    //   182: astore_1
    //   183: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   186: aload_1
    //   187: invokestatic 1157	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   190: invokestatic 1289	vf:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   193: iconst_0
    //   194: ireturn
    //   195: astore_1
    //   196: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   199: aload_1
    //   200: invokestatic 1157	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   203: invokestatic 1289	vf:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   206: goto -63 -> 143
    //   209: aload_2
    //   210: astore_1
    //   211: ldc_w 446
    //   214: ldc_w 1291
    //   217: invokestatic 1284	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   220: pop
    //   221: aload_2
    //   222: invokevirtual 1287	java/io/BufferedReader:close	()V
    //   225: iconst_0
    //   226: ireturn
    //   227: astore_1
    //   228: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   231: aload_1
    //   232: invokestatic 1157	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   235: invokestatic 1289	vf:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   238: iconst_0
    //   239: ireturn
    //   240: astore_2
    //   241: aconst_null
    //   242: astore_1
    //   243: aload_1
    //   244: ifnull +7 -> 251
    //   247: aload_1
    //   248: invokevirtual 1287	java/io/BufferedReader:close	()V
    //   251: aload_2
    //   252: athrow
    //   253: astore_1
    //   254: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   257: aload_1
    //   258: invokestatic 1157	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   261: invokestatic 1289	vf:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   264: goto -13 -> 251
    //   267: astore_2
    //   268: goto -25 -> 243
    //   271: astore_3
    //   272: aconst_null
    //   273: astore_2
    //   274: goto -114 -> 160
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	145	0	bool	boolean
    //   45	117	1	localBufferedReader1	java.io.BufferedReader
    //   182	5	1	localIOException1	IOException
    //   195	5	1	localIOException2	IOException
    //   210	1	1	localBufferedReader2	java.io.BufferedReader
    //   227	5	1	localIOException3	IOException
    //   242	6	1	localObject1	Object
    //   253	5	1	localIOException4	IOException
    //   43	179	2	localBufferedReader3	java.io.BufferedReader
    //   240	12	2	localObject2	Object
    //   267	1	2	localObject3	Object
    //   273	1	2	localObject4	Object
    //   18	132	3	localStringBuilder	StringBuilder
    //   159	7	3	localIOException5	IOException
    //   271	1	3	localIOException6	IOException
    //   50	101	4	str	String
    // Exception table:
    //   from	to	target	type
    //   46	52	159	java/io/IOException
    //   59	82	159	java/io/IOException
    //   84	91	159	java/io/IOException
    //   93	106	159	java/io/IOException
    //   108	127	159	java/io/IOException
    //   129	139	159	java/io/IOException
    //   149	156	159	java/io/IOException
    //   211	221	159	java/io/IOException
    //   176	180	182	java/io/IOException
    //   139	143	195	java/io/IOException
    //   221	225	227	java/io/IOException
    //   11	44	240	finally
    //   247	251	253	java/io/IOException
    //   46	52	267	finally
    //   59	82	267	finally
    //   84	91	267	finally
    //   93	106	267	finally
    //   108	127	267	finally
    //   129	139	267	finally
    //   149	156	267	finally
    //   162	172	267	finally
    //   211	221	267	finally
    //   11	44	271	java/io/IOException
  }
  
  private static boolean k()
  {
    if (jdField_a_of_type_AndroidContentContext != null) {
      try
      {
        boolean bool = ((ConnectivityManager)jdField_a_of_type_AndroidContentContext.getSystemService("connectivity")).getNetworkInfo(0).isConnected();
        return bool;
      }
      catch (Exception localException)
      {
        vf.e(jdField_a_of_type_JavaLangString, localException.getMessage());
      }
    }
    return false;
  }
  
  private static boolean l()
  {
    if (jdField_a_of_type_AndroidContentContext != null) {
      try
      {
        boolean bool = ((ConnectivityManager)jdField_a_of_type_AndroidContentContext.getSystemService("connectivity")).getNetworkInfo(1).isConnectedOrConnecting();
        return bool;
      }
      catch (Exception localException)
      {
        vf.e(jdField_a_of_type_JavaLangString, localException.getMessage());
      }
    }
    return false;
  }
  
  private static boolean m()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)jdField_a_of_type_AndroidContentContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
  }
  
  private static boolean n()
  {
    boolean bool2 = false;
    try
    {
      qq localQq = qq.a(null);
      boolean bool1 = bool2;
      if (localQq != null) {
        if (!localQq.d())
        {
          boolean bool3 = e();
          bool1 = bool2;
          if (!bool3) {}
        }
        else
        {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  private static boolean o()
  {
    return (vm.a("/etc/permissions/rsperm.xml")) && (e()) && (f());
  }
  
  private static boolean p()
  {
    return (jdField_a_of_type_JavaLangThread != null) && (jdField_a_of_type_JavaLangThread.isAlive());
  }
  
  private static boolean q()
  {
    return ((PowerManager)jdField_a_of_type_AndroidContentContext.getSystemService("power")).isScreenOn();
  }
  
  /* Error */
  private static boolean r()
  {
    // Byte code:
    //   0: new 1318	java/lang/StringBuffer
    //   3: dup
    //   4: invokespecial 1319	java/lang/StringBuffer:<init>	()V
    //   7: astore 8
    //   9: new 1321	java/io/RandomAccessFile
    //   12: dup
    //   13: ldc_w 1257
    //   16: ldc_w 1322
    //   19: invokespecial 1324	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   22: astore 6
    //   24: aload 6
    //   26: astore 5
    //   28: aload 6
    //   30: invokevirtual 1325	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   33: astore 7
    //   35: iconst_0
    //   36: istore_0
    //   37: iconst_0
    //   38: istore_1
    //   39: aload 7
    //   41: ifnonnull +92 -> 133
    //   44: aload 6
    //   46: astore 5
    //   48: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   51: ldc_w 1327
    //   54: invokestatic 93	vf:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   57: aload 6
    //   59: astore 5
    //   61: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   64: aload 8
    //   66: invokevirtual 1328	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   69: invokestatic 93	vf:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   72: aload 6
    //   74: astore 5
    //   76: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   79: ldc_w 1327
    //   82: invokestatic 93	vf:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   85: aload 6
    //   87: invokevirtual 1329	java/io/RandomAccessFile:close	()V
    //   90: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   93: new 72	java/lang/StringBuilder
    //   96: dup
    //   97: ldc_w 1331
    //   100: invokespecial 77	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   103: iload_0
    //   104: invokevirtual 1233	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   107: ldc_w 1333
    //   110: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: iload_1
    //   114: invokevirtual 1233	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   117: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: invokestatic 93	vf:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   123: iload_0
    //   124: ifeq +243 -> 367
    //   127: iload_1
    //   128: ifne +239 -> 367
    //   131: iconst_1
    //   132: ireturn
    //   133: aload 6
    //   135: astore 5
    //   137: aload 7
    //   139: ldc_w 1335
    //   142: invokevirtual 758	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   145: ifeq +75 -> 220
    //   148: iload_1
    //   149: istore_2
    //   150: iload_0
    //   151: istore_3
    //   152: aload 6
    //   154: astore 5
    //   156: aload 7
    //   158: ldc_w 1337
    //   161: invokevirtual 758	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   164: ifeq +7 -> 171
    //   167: iconst_1
    //   168: istore_3
    //   169: iload_1
    //   170: istore_2
    //   171: aload 6
    //   173: astore 5
    //   175: aload 8
    //   177: new 72	java/lang/StringBuilder
    //   180: dup
    //   181: aload 7
    //   183: invokestatic 310	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   186: invokespecial 77	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   189: ldc_w 328
    //   192: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   198: invokevirtual 1340	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   201: pop
    //   202: aload 6
    //   204: astore 5
    //   206: aload 6
    //   208: invokevirtual 1325	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   211: astore 7
    //   213: iload_2
    //   214: istore_1
    //   215: iload_3
    //   216: istore_0
    //   217: goto -178 -> 39
    //   220: iload_1
    //   221: istore_2
    //   222: iload_0
    //   223: istore_3
    //   224: aload 6
    //   226: astore 5
    //   228: aload 7
    //   230: ldc_w 1342
    //   233: invokevirtual 758	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   236: ifeq -65 -> 171
    //   239: aload 6
    //   241: astore 5
    //   243: aload 7
    //   245: ldc_w 1272
    //   248: invokevirtual 758	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   251: istore 4
    //   253: iload_1
    //   254: istore_2
    //   255: iload_0
    //   256: istore_3
    //   257: iload 4
    //   259: ifeq -88 -> 171
    //   262: iconst_1
    //   263: istore_2
    //   264: iload_0
    //   265: istore_3
    //   266: goto -95 -> 171
    //   269: astore 7
    //   271: aconst_null
    //   272: astore 6
    //   274: aload 6
    //   276: astore 5
    //   278: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   281: aload 7
    //   283: invokestatic 1157	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   286: invokestatic 1289	vf:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   289: aload 6
    //   291: ifnull +8 -> 299
    //   294: aload 6
    //   296: invokevirtual 1329	java/io/RandomAccessFile:close	()V
    //   299: iconst_0
    //   300: ireturn
    //   301: astore 5
    //   303: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   306: aload 5
    //   308: invokestatic 1157	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   311: invokestatic 1289	vf:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   314: goto -15 -> 299
    //   317: astore 6
    //   319: aconst_null
    //   320: astore 5
    //   322: aload 5
    //   324: ifnull +8 -> 332
    //   327: aload 5
    //   329: invokevirtual 1329	java/io/RandomAccessFile:close	()V
    //   332: aload 6
    //   334: athrow
    //   335: astore 5
    //   337: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   340: aload 5
    //   342: invokestatic 1157	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   345: invokestatic 1289	vf:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   348: goto -16 -> 332
    //   351: astore 5
    //   353: getstatic 24	vh:jdField_a_of_type_JavaLangString	Ljava/lang/String;
    //   356: aload 5
    //   358: invokestatic 1157	android/util/Log:getStackTraceString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   361: invokestatic 1289	vf:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   364: goto -274 -> 90
    //   367: iconst_0
    //   368: ireturn
    //   369: astore 6
    //   371: goto -49 -> 322
    //   374: astore 7
    //   376: goto -102 -> 274
    // Local variable table:
    //   start	length	slot	name	signature
    //   36	229	0	bool1	boolean
    //   38	216	1	bool2	boolean
    //   149	115	2	bool3	boolean
    //   151	115	3	bool4	boolean
    //   251	7	4	bool5	boolean
    //   26	251	5	localRandomAccessFile1	java.io.RandomAccessFile
    //   301	6	5	localIOException1	IOException
    //   320	8	5	localObject1	Object
    //   335	6	5	localIOException2	IOException
    //   351	6	5	localIOException3	IOException
    //   22	273	6	localRandomAccessFile2	java.io.RandomAccessFile
    //   317	16	6	localObject2	Object
    //   369	1	6	localObject3	Object
    //   33	211	7	str	String
    //   269	13	7	localException1	Exception
    //   374	1	7	localException2	Exception
    //   7	169	8	localStringBuffer	StringBuffer
    // Exception table:
    //   from	to	target	type
    //   9	24	269	java/lang/Exception
    //   294	299	301	java/io/IOException
    //   9	24	317	finally
    //   327	332	335	java/io/IOException
    //   85	90	351	java/io/IOException
    //   28	35	369	finally
    //   48	57	369	finally
    //   61	72	369	finally
    //   76	85	369	finally
    //   137	148	369	finally
    //   156	167	369	finally
    //   175	202	369	finally
    //   206	213	369	finally
    //   228	239	369	finally
    //   243	253	369	finally
    //   278	289	369	finally
    //   28	35	374	java/lang/Exception
    //   48	57	374	java/lang/Exception
    //   61	72	374	java/lang/Exception
    //   76	85	374	java/lang/Exception
    //   137	148	374	java/lang/Exception
    //   156	167	374	java/lang/Exception
    //   175	202	374	java/lang/Exception
    //   206	213	374	java/lang/Exception
    //   228	239	374	java/lang/Exception
    //   243	253	374	java/lang/Exception
  }
}
