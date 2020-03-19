package dd.watchmaster;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import dd.watchmaster.common.mobile.weather.WeatherManager;
import dd.watchmaster.common.util.WmLogger;
import dd.watchmaster.common.util.WmLogger.TAG;
import dd.watchmaster.data.realm.WatchFaceRealmObject;
import dd.watchmaster.ui.PromotionManager;
import io.realm.RealmObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class a
  extends dd.watchmaster.common.mobile.c
{
  public static final String a;
  public static String b = "dd.watchaster.action";
  public static String c = "keyPrefFirbasePushToken";
  public static ArrayList<String> d;
  private static String f = "google";
  private static String g = "samsung";
  
  public static String a()
  {
    if (org.apache.commons.lang3.c.a(r(), "ko")) {
      return "http://www.watchmaster.me/policy/PrivacyPolicy.ko.html";
    }
    return "http://www.watchmaster.me/policy/PrivacyPolicy.html";
  }
  
  public static String a(Context paramContext, WatchFaceRealmObject paramWatchFaceRealmObject)
  {
    if (i()) {
      return null;
    }
    return b(paramContext, paramWatchFaceRealmObject);
  }
  
  private static String a(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      char c1 = paramString.charAt(0);
      if (Character.isUpperCase(c1)) {
        return paramString;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Character.toUpperCase(c1));
      localStringBuilder.append(paramString.substring(1));
      return localStringBuilder.toString();
    }
    return "";
  }
  
  public static void a(Context paramContext)
  {
    a(paramContext, "connect");
  }
  
  public static void a(Context paramContext, WatchFaceRealmObject paramWatchFaceRealmObject, ImageView paramImageView, boolean paramBoolean)
  {
    if (paramWatchFaceRealmObject == null) {
      return;
    }
    if (b.a())
    {
      a(paramContext, paramWatchFaceRealmObject.getPreviewUrl(true, paramBoolean), new dd.watchmaster.ui.a(), paramImageView, paramBoolean);
      return;
    }
    a(paramContext, paramWatchFaceRealmObject.getPreviewUrl(false, paramBoolean), null, paramImageView, paramBoolean);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    Intent localIntent = dd.watchmaster.service.b.a().a(paramContext);
    if (localIntent == null) {
      return;
    }
    localIntent.setAction(paramString);
    if (Build.VERSION.SDK_INT >= 26)
    {
      Crashlytics.log(2, "sendActionToWatchService", "startForegroundService");
      paramContext.startForegroundService(localIntent);
      return;
    }
    paramContext.startService(localIntent);
  }
  
  private static void a(Context paramContext, String paramString, Transformation paramTransformation, ImageView paramImageView, boolean paramBoolean)
  {
    paramContext = Picasso.with(paramContext).load(paramString).noPlaceholder();
    if (paramBoolean) {
      paramContext.resize(200, 200);
    }
    if (paramTransformation != null) {
      paramContext.transform(paramTransformation);
    }
    paramContext.into(paramImageView);
  }
  
  public static boolean a(Activity paramActivity, WatchFaceRealmObject paramWatchFaceRealmObject)
  {
    try
    {
      WmLogger.TAG localTAG = WmLogger.TAG.UI;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[APPLY] mobile.Cons applyWatchFace() - getTags : ");
      localStringBuilder.append(paramWatchFaceRealmObject.getTags());
      WmLogger.e(localTAG, localStringBuilder.toString());
      if ((paramWatchFaceRealmObject.getTags().contains("Weather")) && (!WeatherManager.a().b(paramActivity)))
      {
        Toast.makeText(paramActivity, "For the weather update, your location permissions are required.", 0).show();
        ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" }, 21);
        return false;
      }
      b(paramActivity, paramWatchFaceRealmObject);
      return true;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static boolean a(RealmObject paramRealmObject)
  {
    return (paramRealmObject != null) && (paramRealmObject.isValid()) && (paramRealmObject.isLoaded());
  }
  
  public static String b()
  {
    if (d()) {
      return "http://apps.samsung.com/appquery/appDetail.as?appId=dd.watchmaster";
    }
    return "market://details?id=dd.watchmaster";
  }
  
  public static String b(Context paramContext, WatchFaceRealmObject paramWatchFaceRealmObject)
  {
    if (paramWatchFaceRealmObject == null) {
      return null;
    }
    List localList = paramWatchFaceRealmObject.getTags();
    String str = org.apache.commons.lang3.c.a(paramWatchFaceRealmObject.getPrice());
    if ((org.apache.commons.lang3.c.b(str)) && (org.apache.commons.lang3.c.e(str))) {
      return str;
    }
    if ((localList != null) && (localList.contains("free"))) {
      return "Free";
    }
    if (b(paramContext, paramWatchFaceRealmObject.getProjectName())) {
      return "Paid";
    }
    if (org.apache.commons.lang3.c.b(str)) {
      return str;
    }
    return "$1.29";
  }
  
  private static void b(Activity paramActivity, WatchFaceRealmObject paramWatchFaceRealmObject)
  {
    Intent localIntent = dd.watchmaster.service.b.a().a(paramActivity);
    if (localIntent == null) {
      return;
    }
    try
    {
      WmLogger.TAG localTAG = WmLogger.TAG.UI;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[APPLY] mobile.Cons sendApplyService() - fileName : ");
      localStringBuilder.append(paramWatchFaceRealmObject.getLdwFileName());
      WmLogger.e(localTAG, localStringBuilder.toString());
      localTAG = WmLogger.TAG.UI;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("[APPLY] mobile.Cons sendApplyService() - projectName : ");
      localStringBuilder.append(paramWatchFaceRealmObject.getProjectName());
      WmLogger.e(localTAG, localStringBuilder.toString());
      localIntent.putExtra("fileName", paramWatchFaceRealmObject.getLdwFileName());
      localIntent.putExtra("projectName", paramWatchFaceRealmObject.getProjectName());
      localIntent.putStringArrayListExtra("tags", new ArrayList(paramWatchFaceRealmObject.getTags()));
      localIntent.setAction("send");
      if (Build.VERSION.SDK_INT >= 26)
      {
        Crashlytics.log(2, "sendApplyService", "startForegroundService");
        paramActivity.startForegroundService(localIntent);
        return;
      }
      paramActivity.startService(localIntent);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static boolean b(Context paramContext)
  {
    Intent localIntent = dd.watchmaster.service.b.a().a(paramContext);
    if (localIntent == null) {
      return false;
    }
    localIntent.setAction("weather");
    if (Build.VERSION.SDK_INT >= 26)
    {
      Crashlytics.log(2, "sendWeatherData", "startForegroundService");
      paramContext.startForegroundService(localIntent);
    }
    else
    {
      paramContext.startService(localIntent);
    }
    return true;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    Object localObject;
    if (paramString.equalsIgnoreCase("infracto"))
    {
      paramString = "dd.watchdesigner";
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("dd.watchdesigner.");
      ((StringBuilder)localObject).append(paramString.toLowerCase());
      paramString = ((StringBuilder)localObject).toString();
    }
    if (d == null)
    {
      d = new ArrayList();
      try
      {
        new Intent("android.intent.action.MAIN", null).addCategory("android.intent.category.LAUNCHER");
        paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
        while (paramContext.hasNext())
        {
          localObject = (ApplicationInfo)paramContext.next();
          d.add(((ApplicationInfo)localObject).packageName.toLowerCase());
        }
        return d.contains(paramString);
      }
      catch (Exception paramContext)
      {
        WmLogger.e(WmLogger.TAG.ETC, paramContext);
      }
    }
  }
  
  public static String c()
  {
    if (org.apache.commons.lang3.c.a(r(), "ko")) {
      return "http://www.watchmaster.me/policy/TermsOfService.ko.html";
    }
    return "http://www.watchmaster.me/policy/TermsOfService.html";
  }
  
  public static String c(Context paramContext)
  {
    return null;
  }
  
  public static void c(Context paramContext, WatchFaceRealmObject paramWatchFaceRealmObject)
  {
    Object localObject1 = new SimpleDateFormat("cccc, MMMM dd");
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramWatchFaceRealmObject.getTitle());
    ((StringBuilder)localObject2).append(" watchface will be released in\n");
    ((StringBuilder)localObject2).append(((SimpleDateFormat)localObject1).format(paramWatchFaceRealmObject.getDate()));
    ((StringBuilder)localObject2).append(".\nThank you for waiting!");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new AlertDialog.Builder(paramContext);
    paramWatchFaceRealmObject = (LinearLayout)LayoutInflater.from(paramContext).inflate(2131558468, null);
    ((TextView)paramWatchFaceRealmObject.findViewById(2131362001)).setText(paramContext.getString(2131820709));
    ((TextView)paramWatchFaceRealmObject.findViewById(2131362000)).setText((CharSequence)localObject1);
    ((AlertDialog.Builder)localObject2).setView(paramWatchFaceRealmObject);
    paramContext = ((AlertDialog.Builder)localObject2).create();
    paramWatchFaceRealmObject.findViewById(2131362296).setOnClickListener(new a.1(paramContext));
    paramContext.show();
  }
  
  public static boolean d()
  {
    return org.apache.commons.lang3.c.a(g, "google");
  }
  
  public static String e()
  {
    return "google";
  }
  
  public static String f()
  {
    return "google";
  }
  
  public static int g()
  {
    Context localContext = dd.watchmaster.common.b.b();
    try
    {
      int i = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return 0;
  }
  
  public static boolean h()
  {
    return org.apache.commons.lang3.c.b(b.k());
  }
  
  public static boolean i()
  {
    SharedPreferences localSharedPreferences = t();
    boolean bool = false;
    if ((localSharedPreferences.getBoolean("isSubscribed", false)) || (PromotionManager.d())) {
      bool = true;
    }
    return bool;
  }
  
  public static void j()
  {
    d = null;
  }
  
  public static int k()
  {
    Display localDisplay = ((WindowManager)dd.watchmaster.common.b.b().getSystemService("window")).getDefaultDisplay();
    if (Build.VERSION.SDK_INT >= 13)
    {
      Point localPoint = new Point();
      localDisplay.getSize(localPoint);
      return localPoint.x;
    }
    return localDisplay.getWidth();
  }
  
  public static String l()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return a(str2);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(a(str1));
    localStringBuilder.append(" ");
    localStringBuilder.append(str2);
    return localStringBuilder.toString();
  }
  
  public static boolean m()
  {
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (localBluetoothAdapter == null) {
      return false;
    }
    try
    {
      boolean bool = localBluetoothAdapter.isEnabled();
      return bool;
    }
    catch (SecurityException localSecurityException)
    {
      WmLogger.e(WmLogger.TAG.ETC, localSecurityException);
    }
    return true;
  }
  
  public static String n()
  {
    return c(dd.watchmaster.common.b.b());
  }
  
  public static String o()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static String p()
  {
    return TimeZone.getDefault().getDisplayName(false, 0);
  }
  
  public static boolean q()
  {
    if (0L == b.j()) {
      b.i();
    }
    return (System.currentTimeMillis() - b.j()) / 86400000 > 1L;
  }
  
  public static String r()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static int s()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return 2131886274;
    }
    return 16973937;
  }
}
