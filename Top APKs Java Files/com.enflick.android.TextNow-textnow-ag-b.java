package textnow.ag;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.provider.Telephony.Sms;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.enflick.android.TextNow.LogUploadService;
import com.enflick.android.TextNow.activities.DialerActivity;
import com.enflick.android.TextNow.activities.TNBannerActivity;
import com.enflick.android.TextNow.activities.ax;
import com.enflick.android.TextNow.activities.phone.l;
import com.enflick.android.TextNow.featuretoggles.FeatureToggle;
import com.enflick.android.TextNow.featuretoggles.TNFeatureToggleManager;
import com.enflick.android.TextNow.tasks.GetIsPaidUserTask;
import com.facebook.share.model.ShareHashtag.Builder;
import com.facebook.share.model.ShareLinkContent.Builder;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textnow.al.ab;
import textnow.al.m;
import textnow.al.u;
import textnow.al.v;
import textnow.al.x;
import textnow.gi.a;

public final class b
{
  public static String a = null;
  public static final Pattern b = Pattern.compile("textnow://[a-zA-Z0-9\\-_\\.]*(?:\\b|$)");
  private static SimpleDateFormat c = new SimpleDateFormat("MMM d yyyy, h:mma");
  private static SimpleDateFormat d = new SimpleDateFormat("MMM d yyyy");
  private static SimpleDateFormat e = new SimpleDateFormat("MMM d, h:mma");
  private static SimpleDateFormat f = new SimpleDateFormat("MMM d");
  private static SimpleDateFormat g = new SimpleDateFormat("EEE h:mma");
  private static SimpleDateFormat h = new SimpleDateFormat("EEE");
  private static SimpleDateFormat i = new SimpleDateFormat("h:mma");
  private static SimpleDateFormat j = new SimpleDateFormat("EEEE, MMMM d yyyy");
  private static SimpleDateFormat k = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static Calendar l = new GregorianCalendar();
  private static Calendar m = new GregorianCalendar();
  private static final String[] n = { "403", "587", "780", "825", "236", "250", "604", "672", "778", "204", "431", "506", "709", "902", "226", "249", "289", "343", "365", "416", "519", "613", "647", "705", "807", "905", "902", "418", "438", "450", "514", "579", "581", "819", "873", "306", "639", "867" };
  private static PendingIntent o;
  private static String p = null;
  private static boolean q = false;
  private static boolean r = false;
  private static boolean s = false;
  private static boolean t = false;
  
  static
  {
    c.v.addAll(c.o);
    c.x.addAll(c.e);
    c.z.addAll(c.d);
    c.y.addAll(c.b);
    c.y.addAll(c.e);
    c.A.addAll(c.a);
    c.A.addAll(c.e);
    c.A.addAll(c.c);
    c.B.addAll(c.b);
    c.B.addAll(c.q);
    c.B.addAll(c.c);
    c.t.addAll(c.l);
    c.u.addAll(c.l);
    c.u.addAll(c.q);
    c.C.addAll(c.l);
    c.C.addAll(c.q);
    c.C.addAll(c.c);
    c.C.addAll(c.b);
    c.D.put(c.p, null);
    c.D.put(c.q, null);
    c.D.put(c.r, null);
    c.D.put(c.g, null);
    c.D.put(c.h, null);
    c.D.put(c.j, null);
    c.D.put(c.k, Collections.singletonList(Integer.valueOf(19)));
    c.D.put(c.s, Collections.singletonList(Integer.valueOf(19)));
    o = null;
  }
  
  public static boolean A(Context paramContext)
  {
    int i1;
    if ((paramContext.getApplicationInfo().flags & 0x81) != 0)
    {
      i1 = 1;
      if (i1 != 0) {
        break label27;
      }
    }
    label27:
    do
    {
      return false;
      i1 = 0;
      break;
      i1 = new ab(paramContext).getIntByKey("userinfo_is_paid_tn_device", -1);
      if (i1 == -1) {
        break label55;
      }
    } while (i1 != 1);
    return true;
    label55:
    String str = o(paramContext);
    if (!TextUtils.isEmpty(str)) {
      new GetIsPaidUserTask(str).b(paramContext);
    }
    return true;
  }
  
  public static String B(Context paramContext)
  {
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext).getId();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String C(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static Double D(Context paramContext)
  {
    Object localObject = null;
    Intent localIntent = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    paramContext = localObject;
    if (localIntent != null)
    {
      int i1 = localIntent.getIntExtra("level", -1);
      int i2 = localIntent.getIntExtra("scale", -1);
      paramContext = Double.valueOf(i1 / i2);
    }
    return paramContext;
  }
  
  public static String E(Context paramContext)
  {
    if (Build.VERSION.SDK_INT <= 17) {}
    for (paramContext = BluetoothAdapter.getDefaultAdapter(); paramContext == null; paramContext = ((BluetoothManager)paramContext.getSystemService("bluetooth")).getAdapter()) {
      return "";
    }
    return paramContext.getAddress();
  }
  
  public static Point F(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    paramContext.getSize(localPoint);
    return localPoint;
  }
  
  public static String G(Context paramContext)
  {
    return ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
  }
  
  public static int H(Context paramContext)
  {
    return a(paramContext, "ro.enflick.incremental", -1);
  }
  
  public static String I(Context paramContext)
  {
    return a(paramContext, "ro.build.version.incremental", "unknown");
  }
  
  public static void J(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.CALL_EMERGENCY");
    localIntent.setData(Uri.parse("tel:911"));
    paramContext.startActivity(localIntent);
  }
  
  public static String K(Context paramContext)
  {
    if (new u(paramContext).g().equals(v.c))
    {
      a.b("AppUtils", new Object[] { "Using live stripe key" });
      return "pk_live_wVuDm7SIg4xEMcoNpGL24A2z";
    }
    a.b("AppUtils", new Object[] { "Using test stripe key" });
    return "pk_test_MgVVfzaXX8dR1nfIJs9on5dO";
  }
  
  public static int L(Context paramContext)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(new int[] { 2130771981 });
    int i1 = paramContext.getResourceId(0, 2131558624);
    paramContext.recycle();
    return i1;
  }
  
  public static boolean M(Context paramContext)
  {
    Object localObject2 = null;
    boolean bool2;
    if (q) {
      bool2 = r;
    }
    for (;;)
    {
      return bool2;
      try
      {
        com.enflick.android.TextNow.activities.phone.g localG = com.enflick.android.TextNow.activities.phone.g.a(paramContext);
        if (!new ab(paramContext).q())
        {
          paramContext = "cdma fallback disabled in user info";
          bool1 = false;
          bool2 = bool1;
          if (bool1) {
            continue;
          }
          a.b("AppUtils", new Object[] { "CDMA fallback not enabled, " + paramContext });
          return bool1;
        }
      }
      catch (l localL)
      {
        for (;;)
        {
          boolean bool1;
          Object localObject1 = null;
          continue;
          if (!new x(paramContext).b())
          {
            paramContext = "not active subscriber";
            bool1 = false;
          }
          else if (g(paramContext))
          {
            paramContext = "airplane mode on";
            bool1 = false;
          }
          else if (!t(paramContext))
          {
            paramContext = "mdn not match";
            bool1 = false;
          }
          else if (!a(paramContext, false))
          {
            paramContext = "mobile network unavailable";
            bool1 = false;
          }
          else if (localObject1 == null)
          {
            paramContext = "calling not supported";
            bool1 = false;
          }
          else if (!localObject1.b)
          {
            paramContext = "not a phone";
            bool1 = false;
          }
          else
          {
            bool1 = true;
            paramContext = localObject2;
          }
        }
      }
    }
  }
  
  public static boolean N(Context paramContext)
  {
    if (s) {
      return t;
    }
    return android.support.v4.content.g.a(paramContext, "android.permission.MODIFY_PHONE_STATE") == 0;
  }
  
  private static boolean O(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    int i1 = 0;
    if (paramContext.hasNext())
    {
      if (!((ApplicationInfo)paramContext.next()).packageName.equals("com.enflick.textnowoobe")) {
        break label68;
      }
      i1 = 1;
    }
    label68:
    for (;;)
    {
      break;
      return (i1 != 0) && (b(c.B));
    }
  }
  
  public static int a(float paramFloat)
  {
    return Math.round(paramFloat / Resources.getSystem().getDisplayMetrics().density);
  }
  
  public static int a(long paramLong, String paramString)
  {
    Object localObject1 = new SimpleDateFormat("yyyy-MM-dd");
    try
    {
      paramString = ((SimpleDateFormat)localObject1).parse(paramString);
    }
    catch (ParseException paramString)
    {
      try
      {
        for (;;)
        {
          localObject1 = ((SimpleDateFormat)localObject1).parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime() + paramLong)));
          if ((paramString != null) && (localObject1 != null)) {
            break;
          }
          return 0;
          paramString = paramString;
          paramString.printStackTrace();
          paramString = null;
        }
      }
      catch (ParseException localParseException)
      {
        Object localObject2;
        for (;;)
        {
          localParseException.printStackTrace();
          localObject2 = null;
        }
        return (int)((Long.valueOf(paramString.getTime() - localObject2.getTime()).longValue() + 86399999L) / 86400000L);
      }
    }
  }
  
  public static int a(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    float f1 = paramInt;
    return Math.round(paramContext.density * f1);
  }
  
  private static int a(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass("android.os.SystemProperties");
      paramInt = ((Integer)paramContext.getMethod("getInt", new Class[] { String.class, Integer.TYPE }).invoke(paramContext, new Object[] { paramString, Integer.valueOf(-1) })).intValue();
      return paramInt;
    }
    catch (ClassNotFoundException paramContext)
    {
      a.e("AppUtils", new Object[] { "class not found!!" });
      return -1;
    }
    catch (NoSuchMethodException paramContext)
    {
      a.e("AppUtils", new Object[] { "No such method!!" });
      paramContext.printStackTrace();
      return -1;
    }
    catch (IllegalAccessException paramContext)
    {
      a.e("AppUtils", new Object[] { "Illegal access!!" });
      return -1;
    }
    catch (InvocationTargetException paramContext)
    {
      a.e("AppUtils", new Object[] { "Invocation target exception!!" });
    }
    return -1;
  }
  
  public static Uri a(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = new File(paramContext.getExternalCacheDir(), "exported_conversation.txt");
    if (paramContext.exists()) {
      paramContext.delete();
    }
    Html.fromHtml(paramString).toString();
    try
    {
      paramContext.createNewFile();
      FileOutputStream localFileOutputStream = new FileOutputStream(paramContext);
      localFileOutputStream.write(paramString.getBytes());
      localFileOutputStream.close();
      return Uri.fromFile(paramContext);
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static PowerManager.WakeLock a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = (PowerManager)paramContext.getSystemService("power");
      int i1 = ((Integer)PowerManager.class.getDeclaredField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").get(null)).intValue();
      boolean bool;
      if (Build.VERSION.SDK_INT >= 17) {
        bool = ((Boolean)paramContext.getClass().getDeclaredMethod("isWakeLockLevelSupported", new Class[] { Integer.TYPE }).invoke(paramContext, new Object[] { Integer.valueOf(i1) })).booleanValue();
      }
      while (bool)
      {
        paramContext = paramContext.newWakeLock(i1, paramString);
        paramContext.setReferenceCounted(false);
        return paramContext;
        int i2 = ((Integer)paramContext.getClass().getDeclaredMethod("getSupportedWakeLockFlags", new Class[0]).invoke(paramContext, new Object[0])).intValue();
        if ((i2 & i1) != 0) {
          bool = true;
        } else {
          bool = false;
        }
      }
      return null;
    }
    catch (Exception paramContext)
    {
      a.b("TextNow", new Object[] { "Impossible to get power manager supported wake lock flags " });
    }
  }
  
  public static String a()
  {
    if (Build.MODEL.toUpperCase().equals("Nexus 5".toUpperCase())) {
      return "*#*#72786#*#*";
    }
    return "##72786#";
  }
  
  public static String a(int paramInt)
  {
    int i1 = paramInt;
    if (paramInt < 0) {
      i1 = 0;
    }
    if (i1 < 60) {
      return i1 + "s";
    }
    if (i1 < 3600)
    {
      paramInt = i1 / 60;
      str2 = "" + i1 % 60;
      str1 = str2;
      if (str2.length() == 1) {
        str1 = "0" + str2;
      }
      return paramInt + ":" + str1;
    }
    paramInt = i1 / 60;
    int i2 = paramInt / 60;
    String str2 = "" + i1 % 60;
    String str1 = str2;
    if (str2.length() == 1) {
      str1 = "0" + str2;
    }
    return i2 + ":" + paramInt % 60 + ":" + str1;
  }
  
  public static String a(int paramInt, String paramString)
  {
    paramString = Currency.getInstance(paramString.toUpperCase());
    int i1 = paramString.getDefaultFractionDigits();
    double d1 = paramInt;
    paramInt = 0;
    while (paramInt < i1)
    {
      d1 /= 10.0D;
      paramInt += 1;
    }
    NumberFormat localNumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
    localNumberFormat.setCurrency(paramString);
    localNumberFormat.setMaximumFractionDigits(i1);
    localNumberFormat.setMinimumFractionDigits(i1);
    return localNumberFormat.format(d1);
  }
  
  public static String a(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm z").format(new Date(paramLong));
  }
  
  public static String a(long paramLong1, long paramLong2)
  {
    return a(new Date(paramLong1 - paramLong2), false);
  }
  
  public static String a(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return null;
    }
    return paramContext.getSimSerialNumber();
  }
  
  private static String a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass("android.os.SystemProperties");
      paramContext = (String)paramContext.getMethod("get", new Class[] { String.class, String.class }).invoke(paramContext, new Object[] { paramString1, paramString2 });
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      a.e("AppUtils", new Object[] { "class not found!!" });
      return paramString2;
    }
    catch (NoSuchMethodException paramContext)
    {
      a.e("AppUtils", new Object[] { "No such method!!" });
      return paramString2;
    }
    catch (IllegalAccessException paramContext)
    {
      a.e("AppUtils", new Object[] { "Illegal access!!" });
      return paramString2;
    }
    catch (InvocationTargetException paramContext)
    {
      a.e("AppUtils", new Object[] { "Invocation target exception!!" });
    }
    return paramString2;
  }
  
  public static String a(Iterable<? extends Object> paramIterable, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      Object localObject = paramIterable.next();
      if (localObject != null)
      {
        localStringBuffer.append(localObject);
        localStringBuffer.append(paramString);
      }
    }
    paramIterable = localStringBuffer.toString();
    if (localStringBuffer.length() > paramString.length()) {
      paramIterable = localStringBuffer.substring(0, localStringBuffer.length() - paramString.length());
    }
    return paramIterable;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    paramString1 = a(paramString1, 0L);
    return new SimpleDateFormat(paramString2).format(paramString1);
  }
  
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = new SimpleDateFormat(paramString2, Locale.getDefault()).parse(paramString1);
      paramString1.setTime(paramString1.getTime());
      paramString1 = new SimpleDateFormat(paramString3, Locale.getDefault()).format(paramString1);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      a.e("TextNow", new Object[] { Log.getStackTraceString(paramString1) });
    }
    return "";
  }
  
  public static String a(Date paramDate, boolean paramBoolean)
  {
    Object localObject = new Date();
    l.setTime(paramDate);
    m.setTime((Date)localObject);
    long l1 = (System.currentTimeMillis() - paramDate.getTime()) / 86400000L;
    if (l1 > 280L) {
      if (paramBoolean) {
        localObject = d;
      }
    }
    for (;;)
    {
      return ((SimpleDateFormat)localObject).format(paramDate);
      localObject = c;
      continue;
      if (l1 > 28L)
      {
        if (paramBoolean) {
          localObject = f;
        } else {
          localObject = e;
        }
      }
      else if (l1 > 6L)
      {
        if (paramBoolean) {
          localObject = f;
        } else {
          localObject = e;
        }
      }
      else if (l.get(5) != m.get(5))
      {
        if (paramBoolean) {
          localObject = h;
        } else {
          localObject = g;
        }
      }
      else {
        localObject = i;
      }
    }
  }
  
  public static String a(Object[] paramArrayOfObject, String paramString)
  {
    return a(Arrays.asList(paramArrayOfObject), paramString);
  }
  
  public static Date a(String paramString)
  {
    return a(paramString, 0L);
  }
  
  private static Date a(String paramString, long paramLong)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = paramString.replace("Z", "+0000");
        paramString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(paramString);
        paramString.setTime(paramString.getTime() + paramLong);
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      a.e("TextNow", new Object[] { Log.getStackTraceString(paramString) });
    }
    return new Date(paramLong);
  }
  
  public static void a(Activity paramActivity)
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName("com.enflick.ota", "com.enflick.ota.UpdateActivity"));
    try
    {
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      t.b(paramActivity, 2131297344);
    }
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2)
  {
    try
    {
      ((ClipboardManager)paramActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("TextNow Share", paramString1));
      Toast.makeText(paramActivity, paramString2, 0).show();
      paramString1 = ((ShareLinkContent.Builder)((ShareLinkContent.Builder)new ShareLinkContent.Builder().setContentTitle(paramActivity.getString(2131296771)).setContentDescription(paramActivity.getString(2131296770)).setContentUrl(Uri.parse("https://www.textnow.com"))).setImageUrl(Uri.parse("http://about.textnow.com/wp-content/uploads/2015/11/textnow_logo_012.png")).setShareHashtag(new ShareHashtag.Builder().setHashtag("TextNow").build())).build();
      paramActivity = new ShareDialog(paramActivity);
      if (paramActivity.canShow(paramString1)) {
        paramActivity.show(paramString1);
      }
      return;
    }
    catch (Throwable paramActivity)
    {
      a.e("AppUtils", new Object[] { "Fail to share on facebook", paramActivity });
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Uri paramUri, String paramString3)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/html");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { paramString3 });
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    if (paramUri != null) {
      localIntent.putExtra("android.intent.extra.STREAM", paramUri);
    }
    localIntent.addFlags(268435456);
    localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml(paramString2));
    paramContext.startActivity(localIntent);
  }
  
  public static void a(Context paramContext, d paramD)
  {
    String str = j(paramContext);
    Uri localUri = h.a(paramContext, str, true);
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/html");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "textnow.android.feedback@enflick.com" });
    localIntent.putExtra("android.intent.extra.SUBJECT", "TextNow " + b(paramContext) + " " + paramD.toString());
    localIntent.addFlags(268435456);
    localIntent.putExtra("android.intent.extra.TEXT", str);
    if (localUri != null) {
      localIntent.putExtra("android.intent.extra.STREAM", localUri);
    }
    paramContext.startActivity(localIntent);
  }
  
  public static void a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramContext, paramBoolean1, paramBoolean2, -1);
  }
  
  public static void a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    ab localAb = new ab(paramContext);
    if (paramBoolean2)
    {
      localAb.setByKey("userinfo_last_updated_logs_for_crash", LogUploadService.a());
      localAb.commitChangesSync();
      u localU = new u(paramContext);
      localU.setByKey("must_upload_debug_logs", true);
      localU.commitChangesSync();
    }
    LogUploadService.a(paramContext, h(paramContext), paramBoolean1, localAb.D(), paramInt);
  }
  
  public static void a(DialerActivity paramDialerActivity)
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName("com.enflick.ota", "com.enflick.ota.UpdateActivity"));
    localIntent.setAction("com.enflick.ota.ACTION_UPDATE_FROM_SCRATCH");
    try
    {
      paramDialerActivity.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      t.b(paramDialerActivity, 2131297344);
    }
  }
  
  public static void a(TNBannerActivity paramTNBannerActivity, String paramString)
  {
    ax.a(paramString, j(paramTNBannerActivity)).show(paramTNBannerActivity.getSupportFragmentManager(), "device_information");
  }
  
  public static void a(TNBannerActivity paramTNBannerActivity, String paramString, Context paramContext)
  {
    FeatureToggle localFeatureToggle = new TNFeatureToggleManager(paramContext).getFeature("BandwidthX");
    String str = "Feature is null or disabled";
    paramContext = str;
    if (localFeatureToggle != null)
    {
      paramContext = str;
      if (localFeatureToggle.getConfiguration() != null) {
        paramContext = localFeatureToggle.getConfiguration().toString().concat(" Enabled: ".concat(Boolean.toString(localFeatureToggle.isEnabled())));
      }
    }
    ax.a(paramString, paramContext).show(paramTNBannerActivity.getSupportFragmentManager(), "features_information");
  }
  
  public static boolean a(char paramChar)
  {
    if ((('A' <= paramChar) && (paramChar <= 'Z')) || (('a' <= paramChar) && (paramChar <= 'z'))) {}
    while (('0' <= paramChar) && (paramChar <= '9')) {
      return true;
    }
    return false;
  }
  
  public static boolean a(Context paramContext, boolean paramBoolean)
  {
    boolean bool = new u(paramContext).getBooleanByKey("allow_fallback_while_roaming", false).booleanValue();
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = paramContext.getAllNetworkInfo();
      int i2 = paramContext.length;
      int i1 = 0;
      while (i1 < i2)
      {
        Object localObject = paramContext[i1];
        if (localObject.getType() == 0)
        {
          a.b("NetworkType", new Object[] { localObject.toString() });
          if (paramBoolean) {
            return (localObject.isAvailable()) && ((bool) || (!localObject.isRoaming())) && (localObject.isConnected());
          }
          return (localObject.isAvailable()) && ((bool) || (!localObject.isRoaming()));
        }
        i1 += 1;
      }
    }
    return false;
  }
  
  public static boolean a(Intent paramIntent)
  {
    paramIntent = paramIntent.getData();
    if (paramIntent != null)
    {
      paramIntent = paramIntent.getSchemeSpecificPart();
      if ((!TextUtils.isEmpty(paramIntent)) && (s.b(paramIntent))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean a(Editable paramEditable)
  {
    char[] arrayOfChar = paramEditable.toString().toCharArray();
    int i1 = 0;
    int i2 = 0;
    while (i1 < arrayOfChar.length)
    {
      if (Character.isUpperCase(arrayOfChar[i1]))
      {
        arrayOfChar[i1] = Character.toLowerCase(arrayOfChar[i1]);
        i2 = 1;
      }
      i1 += 1;
    }
    if (i2 != 0) {
      paramEditable.replace(0, paramEditable.length(), new String(arrayOfChar).trim());
    }
    return true;
  }
  
  public static final boolean a(CharSequence paramCharSequence)
  {
    try
    {
      boolean bool = Patterns.EMAIL_ADDRESS.matcher(paramCharSequence).matches();
      return bool;
    }
    catch (NullPointerException paramCharSequence) {}
    return false;
  }
  
  public static boolean a(List<String> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (Build.MODEL.equalsIgnoreCase(str)) {
        return true;
      }
    }
    return false;
  }
  
  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte1.length];
    int i1 = 0;
    while (i1 < paramArrayOfByte1.length)
    {
      arrayOfByte[i1] = ((byte)(paramArrayOfByte1[i1] ^ paramArrayOfByte2[(i1 % paramArrayOfByte2.length)]));
      i1 += 1;
    }
    return arrayOfByte;
  }
  
  public static double b(int paramInt, String paramString)
  {
    int i1 = Currency.getInstance(paramString).getDefaultFractionDigits();
    double d1 = paramInt;
    paramInt = 0;
    while (paramInt < i1)
    {
      d1 /= 10.0D;
      paramInt += 1;
    }
    return d1;
  }
  
  public static String b(int paramInt)
  {
    if (paramInt == 0) {
      return "0MB";
    }
    float f1 = paramInt / 1024.0F;
    if (f1 < 1.0F) {
      return paramInt + "MB";
    }
    paramInt = (int)Math.floor(f1);
    if (f1 - paramInt > 0.0F) {
      return String.format("%.1fGB", new Object[] { Float.valueOf(f1) });
    }
    return paramInt + "GB";
  }
  
  public static String b(long paramLong1, long paramLong2)
  {
    return a(new Date(paramLong1 - paramLong2), true);
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      a.e("TextNow", new Object[] { Log.getStackTraceString(paramContext) });
    }
    return "NA";
  }
  
  public static boolean b()
  {
    return Locale.getDefault().getLanguage().equals(Locale.ENGLISH.getLanguage());
  }
  
  public static boolean b(char paramChar)
  {
    return (('A' <= paramChar) && (paramChar <= 'Z')) || (('a' <= paramChar) && (paramChar <= 'z'));
  }
  
  private static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    paramString = paramContext.getLaunchIntentForPackage(paramString);
    if (paramString == null) {}
    while (paramContext.queryIntentActivities(paramString, 65536).size() <= 0) {
      return false;
    }
    return true;
  }
  
  public static final boolean b(CharSequence paramCharSequence)
  {
    try
    {
      boolean bool = Patterns.WEB_URL.matcher(paramCharSequence).matches();
      return bool;
    }
    catch (NullPointerException paramCharSequence) {}
    return false;
  }
  
  public static boolean b(String paramString)
  {
    return Build.MANUFACTURER.equalsIgnoreCase(paramString);
  }
  
  public static boolean b(List<String> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (Build.MODEL.toUpperCase().startsWith(str.toUpperCase())) {
        return true;
      }
    }
    return false;
  }
  
  public static String c(int paramInt)
  {
    return a(paramInt, "USD");
  }
  
  public static String c(long paramLong1, long paramLong2)
  {
    return DateUtils.getRelativeTimeSpanString(paramLong1 - paramLong2).toString();
  }
  
  public static boolean c()
  {
    return Build.MANUFACTURER.equalsIgnoreCase("amazon");
  }
  
  public static boolean c(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getAllNetworkInfo();
    if (paramContext == null) {
      return false;
    }
    int i2 = paramContext.length;
    int i1 = 0;
    while (i1 < i2)
    {
      Object localObject = paramContext[i1];
      if ((localObject != null) && ((localObject.getState() == NetworkInfo.State.CONNECTED) || (localObject.getState() == NetworkInfo.State.CONNECTING))) {
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  public static final boolean c(CharSequence paramCharSequence)
  {
    try
    {
      boolean bool = b.matcher(paramCharSequence).matches();
      return bool;
    }
    catch (NullPointerException paramCharSequence) {}
    return false;
  }
  
  public static boolean c(String paramString)
  {
    paramString = paramString.toLowerCase(Locale.US);
    return (paramString.endsWith("@textnow.me")) || (paramString.endsWith(".textnow.me"));
  }
  
  public static int d(Context paramContext)
  {
    if ((!O(paramContext)) || (b(c.t))) {
      return 0;
    }
    return 1;
  }
  
  public static String d(long paramLong1, long paramLong2)
  {
    Date localDate = new Date(paramLong1 - paramLong2);
    return j.format(localDate);
  }
  
  public static String d(String paramString)
  {
    if (!c(paramString)) {}
    int i1;
    do
    {
      return null;
      i1 = paramString.indexOf('@');
    } while (i1 < 0);
    return paramString.substring(0, i1);
  }
  
  public static boolean d()
  {
    return b(c.v);
  }
  
  public static String e(long paramLong1, long paramLong2)
  {
    Date localDate = new Date(paramLong1 - paramLong2);
    return i.format(localDate);
  }
  
  public static boolean e()
  {
    return b(c.w);
  }
  
  public static boolean e(Context paramContext)
  {
    return (O(paramContext)) && (b(c.u));
  }
  
  public static boolean f()
  {
    return b(c.x);
  }
  
  public static boolean f(Context paramContext)
  {
    paramContext = s.d(new ab(paramContext).getStringByKey("userinfo_phone"));
    if (paramContext == null) {}
    for (;;)
    {
      return false;
      paramContext = paramContext.substring(2, 5);
      String[] arrayOfString = n;
      int i2 = arrayOfString.length;
      int i1 = 0;
      while (i1 < i2)
      {
        if (arrayOfString[i1].equals(paramContext)) {
          return true;
        }
        i1 += 1;
      }
    }
  }
  
  public static boolean g()
  {
    return b(c.y);
  }
  
  @TargetApi(17)
  public static boolean g(Context paramContext)
  {
    boolean bool;
    if (Build.VERSION.SDK_INT < 17) {
      if (Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) != 0) {
        bool = true;
      }
    }
    for (;;)
    {
      a.b("TextNow", new Object[] { "airplane mode " + bool });
      return bool;
      bool = false;
      continue;
      if (Settings.Global.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) != 0) {
        bool = true;
      } else {
        bool = false;
      }
    }
  }
  
  public static boolean h()
  {
    return (b(c.A)) || (Build.VERSION.SDK_INT < 19);
  }
  
  public static String[] h(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(h.d(paramContext));
    if (new u(paramContext).i())
    {
      textnow.af.e.a(paramContext).a();
      localArrayList.addAll(textnow.af.e.b(paramContext));
    }
    paramContext = new String[localArrayList.size()];
    int i1 = 0;
    while (i1 < localArrayList.size())
    {
      paramContext[i1] = ((File)localArrayList.get(i1)).getAbsolutePath();
      i1 += 1;
    }
    return paramContext;
  }
  
  public static void i(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(2131296875).setMessage(2131296874).setPositiveButton(2131297425, new b.1(paramContext));
    if (!k(paramContext)) {
      localBuilder.setNeutralButton(2131297424, new b.2(paramContext));
    }
    localBuilder.setNegativeButton(2131296487, null);
    localBuilder.create().show();
  }
  
  public static boolean i()
  {
    Object localObject = c.D.entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      if (a((List)localEntry.getKey()))
      {
        localObject = (List)localEntry.getValue();
        return (localObject == null) || (((List)localObject).contains(Integer.valueOf(Build.VERSION.SDK_INT)));
      }
    }
    return false;
  }
  
  public static String j(Context paramContext)
  {
    Object localObject = "";
    try
    {
      str = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      localObject = str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        String str;
        boolean bool;
        a.e("TextNow", new Object[] { "version not found" });
      }
    }
    bool = A(paramContext);
    str = o(paramContext);
    paramContext = a(paramContext, "ro.enflick.incremental", "unknown");
    return String.format("App Version: %s\n%s   %s\nAccount Information: %b   %s\n\nManufacturer: %s\nModel: %s\nOS: %s\n", new Object[] { localObject, Build.DEVICE, paramContext, Boolean.valueOf(bool), str, Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE });
  }
  
  public static boolean j()
  {
    return Looper.myLooper() == Looper.getMainLooper();
  }
  
  public static boolean k()
  {
    return Build.VERSION.SDK_INT >= 8;
  }
  
  public static boolean k(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    return paramContext.getNetworkInfo(1).isConnected();
  }
  
  public static String l()
  {
    return k.format(new Date());
  }
  
  public static boolean l(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    return (paramContext != null) && (!paramContext.isRoaming()) && (paramContext.isConnected());
  }
  
  public static int m(Context paramContext)
  {
    if ((!paramContext.getPackageManager().hasSystemFeature("android.hardware.telephony")) || (Build.MODEL.startsWith("BNTV2")) || (Build.MODEL.startsWith("BNRV"))) {
      return e.a;
    }
    if (((TelephonyManager)paramContext.getSystemService("phone")).getSimState() != 5) {
      return e.b;
    }
    return e.c;
  }
  
  public static String m()
  {
    return Integer.toString(Build.VERSION.SDK_INT);
  }
  
  public static String n(Context paramContext)
  {
    if (p != null) {
      return p;
    }
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return null;
    }
    return s.e(paramContext.getLine1Number());
  }
  
  public static String o(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return null;
    }
    return paramContext.getDeviceId();
  }
  
  public static boolean p(Context paramContext)
  {
    boolean bool = false;
    String str;
    if (Build.VERSION.SDK_INT >= 19) {
      str = paramContext.getPackageName();
    }
    try
    {
      bool = str.equals(Telephony.Sms.getDefaultSmsPackage(paramContext));
      return bool;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static boolean q(Context paramContext)
  {
    return ((KeyguardManager)paramContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
  }
  
  public static boolean r(Context paramContext)
  {
    AudioManager localAudioManager = (AudioManager)paramContext.getSystemService("audio");
    if (localAudioManager == null) {
      return true;
    }
    if (Build.VERSION.SDK_INT >= 16) {}
    for (;;)
    {
      try
      {
        i1 = Settings.System.getInt(paramContext.getContentResolver(), "vibrate_when_ringing");
        if (i1 != 1) {
          continue;
        }
        i1 = 1;
      }
      catch (Settings.SettingNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        i1 = 1;
        continue;
      }
      if ((localAudioManager.getRingerMode() == 1) || (i1 != 0)) {
        break;
      }
      return false;
      int i1 = 0;
      continue;
      if (((localAudioManager.getVibrateSetting(0) == 2) && (localAudioManager.getRingerMode() == 0)) || (localAudioManager.getVibrateSetting(0) == 1)) {
        i1 = 1;
      } else {
        i1 = 0;
      }
    }
  }
  
  public static boolean s(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return false;
    }
    int i1 = paramContext.getPhoneType();
    return (i1 == 2) || (i1 == 1);
  }
  
  public static boolean t(Context paramContext)
  {
    String str = new m(paramContext).getStringByKey("mdn");
    if (TextUtils.isEmpty(str)) {}
    do
    {
      return false;
      paramContext = n(paramContext);
    } while (TextUtils.isEmpty(paramContext));
    return s.a(str, paramContext);
  }
  
  public static boolean u(Context paramContext)
  {
    String str = new m(paramContext).getStringByKey("esn");
    if (TextUtils.isEmpty(str)) {
      return false;
    }
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return false;
    }
    return str.equals(paramContext.getDeviceId());
  }
  
  /* Error */
  public static android.location.Location v(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 1655
    //   4: invokevirtual 382	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   7: checkcast 1657	android/location/LocationManager
    //   10: astore_0
    //   11: aload_0
    //   12: ldc_w 1659
    //   15: invokevirtual 1663	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   18: astore_1
    //   19: aload_0
    //   20: ldc_w 1665
    //   23: invokevirtual 1663	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   26: astore_0
    //   27: aload_1
    //   28: ifnonnull +101 -> 129
    //   31: aload_0
    //   32: ifnonnull +97 -> 129
    //   35: aconst_null
    //   36: areturn
    //   37: astore_1
    //   38: ldc_w 816
    //   41: iconst_1
    //   42: anewarray 4	java/lang/Object
    //   45: dup
    //   46: iconst_0
    //   47: ldc_w 1667
    //   50: aastore
    //   51: invokestatic 485	textnow/gi/a:b	(Ljava/lang/Object;[Ljava/lang/Object;)Ltextnow/gi/a;
    //   54: pop
    //   55: aconst_null
    //   56: astore_1
    //   57: goto -38 -> 19
    //   60: astore_1
    //   61: ldc_w 816
    //   64: iconst_1
    //   65: anewarray 4	java/lang/Object
    //   68: dup
    //   69: iconst_0
    //   70: ldc_w 1669
    //   73: aastore
    //   74: invokestatic 485	textnow/gi/a:b	(Ljava/lang/Object;[Ljava/lang/Object;)Ltextnow/gi/a;
    //   77: pop
    //   78: aconst_null
    //   79: astore_1
    //   80: goto -61 -> 19
    //   83: astore_0
    //   84: ldc_w 816
    //   87: iconst_1
    //   88: anewarray 4	java/lang/Object
    //   91: dup
    //   92: iconst_0
    //   93: ldc_w 1671
    //   96: aastore
    //   97: invokestatic 485	textnow/gi/a:b	(Ljava/lang/Object;[Ljava/lang/Object;)Ltextnow/gi/a;
    //   100: pop
    //   101: aconst_null
    //   102: astore_0
    //   103: goto -76 -> 27
    //   106: astore_0
    //   107: ldc_w 816
    //   110: iconst_1
    //   111: anewarray 4	java/lang/Object
    //   114: dup
    //   115: iconst_0
    //   116: ldc_w 1673
    //   119: aastore
    //   120: invokestatic 485	textnow/gi/a:b	(Ljava/lang/Object;[Ljava/lang/Object;)Ltextnow/gi/a;
    //   123: pop
    //   124: aconst_null
    //   125: astore_0
    //   126: goto -99 -> 27
    //   129: aload_1
    //   130: ifnull +28 -> 158
    //   133: aload_0
    //   134: ifnull +24 -> 158
    //   137: aload_1
    //   138: invokevirtual 1676	android/location/Location:getTime	()J
    //   141: aload_0
    //   142: invokevirtual 1676	android/location/Location:getTime	()J
    //   145: lcmp
    //   146: ifle +7 -> 153
    //   149: aload_1
    //   150: astore_2
    //   151: aload_2
    //   152: areturn
    //   153: aload_0
    //   154: astore_2
    //   155: goto -4 -> 151
    //   158: aload_1
    //   159: astore_2
    //   160: aload_1
    //   161: ifnonnull -10 -> 151
    //   164: aload_0
    //   165: astore_2
    //   166: goto -15 -> 151
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	paramContext	Context
    //   18	10	1	localLocation	android.location.Location
    //   37	1	1	localSecurityException	SecurityException
    //   56	1	1	localObject1	Object
    //   60	1	1	localIllegalArgumentException	IllegalArgumentException
    //   79	82	1	localObject2	Object
    //   150	16	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   11	19	37	java/lang/SecurityException
    //   11	19	60	java/lang/IllegalArgumentException
    //   19	27	83	java/lang/SecurityException
    //   19	27	106	java/lang/IllegalArgumentException
  }
  
  public static boolean w(Context paramContext)
  {
    a();
    Intent localIntent = new Intent("android.intent.action.DIAL");
    if (b(paramContext, "com.google.android.dialer")) {
      localIntent.setClassName("com.google.android.dialer", "com.android.dialer.DialtactsActivity");
    }
    for (;;)
    {
      try
      {
        paramContext.startActivity(localIntent);
        return true;
      }
      catch (Exception localException)
      {
        a.e("TextNow", new Object[] { "Unable to open system dialer", localException });
        Toast.makeText(paramContext, 2131296718, 1).show();
      }
      if (b(paramContext, "com.android.dialer")) {
        localIntent.setClassName("com.android.dialer", "com.android.dialer.DialtactsActivity");
      } else if (b(paramContext, "com.android.htccontacts")) {
        localIntent.setClassName("com.android.htccontacts", "com.android.htccontacts.DialerTabActivity");
      } else if (b(paramContext, "com.android.contacts")) {
        localIntent.setClassName("com.android.contacts", "com.android.contacts.DialtactsActivity");
      }
    }
    return false;
  }
  
  public static void x(Context paramContext)
  {
    if (o == null) {
      return;
    }
    ((AlarmManager)paramContext.getSystemService("alarm")).cancel(o);
  }
  
  public static boolean y(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo("com.enflick.textnowoobe", 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean z(Context paramContext)
  {
    return (paramContext.getApplicationInfo().flags & 0x81) != 0;
  }
}
