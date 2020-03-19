package com.messenger.b;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.support.v4.app.ab.d;
import android.support.v7.a.d;
import android.support.v7.a.d.a;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.messenger.modules.entity.AddAppInfo;
import com.messenger.modules.receiver.NotificationReceiver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class f
{
  private static PackageManager a;
  
  public static float a(float paramFloat, Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().densityDpi / 160.0F * paramFloat;
  }
  
  public static Dialog a(Context paramContext, View paramView)
  {
    Dialog localDialog = new Dialog(paramContext, 2131362128);
    localDialog.setContentView(paramView);
    paramView = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Window localWindow = localDialog.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    if (paramContext.getResources().getConfiguration().orientation == 1) {}
    for (localLayoutParams.width = ((int)(paramView.getWidth() * 0.85D));; localLayoutParams.width = ((int)(paramView.getHeight() * 0.85D)))
    {
      localWindow.setGravity(17);
      localWindow.setAttributes(localLayoutParams);
      localDialog.show();
      return localDialog;
    }
  }
  
  public static Bitmap a(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      return null;
    }
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1) {}
    for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565)
    {
      localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
      Canvas localCanvas = new Canvas((Bitmap)localObject);
      paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
      paramDrawable.draw(localCanvas);
      return localObject;
    }
  }
  
  public static Drawable a(Context paramContext, String paramString)
  {
    paramContext = k(paramContext);
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 0).loadIcon(paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Drawable a(Resources paramResources)
  {
    paramResources = new com.messenger.external.views.a(paramResources);
    paramResources.a(1);
    paramResources.a(1.0F);
    paramResources.b(0.0F);
    paramResources.a(true);
    return paramResources;
  }
  
  public static String a()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String a(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(paramLong));
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = k(paramContext).getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void a(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2)
  {
    try
    {
      paramIntent = PendingIntent.getBroadcast(paramContext, paramInt1, paramIntent, paramInt2);
      paramContext = (AlarmManager)paramContext.getSystemService("alarm");
      if (paramContext != null) {
        paramContext.cancel(paramIntent);
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void a(Context paramContext, long paramLong1, long paramLong2, int paramInt1, Intent paramIntent, int paramInt2)
  {
    try
    {
      paramIntent = PendingIntent.getBroadcast(paramContext, paramInt1, paramIntent, paramInt2);
      paramContext = (AlarmManager)paramContext.getSystemService("alarm");
      if (paramContext != null) {
        paramContext.setRepeating(0, paramLong1, paramLong2, paramIntent);
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static long b()
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    localGregorianCalendar.set(11, 0);
    localGregorianCalendar.set(12, 0);
    localGregorianCalendar.set(13, 0);
    return localGregorianCalendar.getTimeInMillis();
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      String str1 = Build.MODEL;
      String str2 = a(paramContext);
      String str3 = b.a();
      paramContext = paramContext.getResources().getDisplayMetrics();
      int j = paramContext.widthPixels;
      int k = paramContext.heightPixels;
      paramContext = "" + j + "x" + k;
      paramContext = new StringBuilder().append("[ Android : ").append(str3).append(" / ").append(str1).append(" / ").append(i).append(" / ").append(paramContext).append(" / ").append(str2).append("]").toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String b(Context paramContext, String paramString)
  {
    paramContext = k(paramContext);
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 0).loadLabel(paramContext).toString();
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Intent c(Context paramContext, String paramString)
  {
    return k(paramContext).getLaunchIntentForPackage(paramString);
  }
  
  public static SharedPreferences c(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext);
  }
  
  public static void d(Context paramContext)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    Object localObject1;
    if (localSharedPreferences.getBoolean("pref_show_update_summary", true)) {
      localObject1 = null;
    }
    try
    {
      localObject2 = k(paramContext).getPackageInfo(paramContext.getPackageName(), 16384);
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object localObject2;
      d.a localA;
      for (;;) {}
    }
    localObject2 = f(paramContext, "release.log");
    if (localObject1 != null)
    {
      localA = new d.a(paramContext);
      localA.a(paramContext.getString(2131165328, new Object[] { localObject1.versionName })).b((CharSequence)localObject2).a(true).a(paramContext.getResources().getString(2131165261), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      localA.b().show();
    }
    localSharedPreferences.edit().putBoolean("pref_show_update_summary", false).apply();
  }
  
  public static void d(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(c(paramContext, paramString));
      return;
    }
    catch (Exception paramString)
    {
      Toast.makeText(paramContext, paramContext.getResources().getString(2131165298), 1).show();
    }
  }
  
  public static Dialog e(Context paramContext, String paramString)
  {
    View localView = LayoutInflater.from(paramContext).inflate(2130968638, null);
    RelativeLayout localRelativeLayout = (RelativeLayout)localView.findViewById(2131624158);
    ImageView localImageView = (ImageView)localView.findViewById(2131624160);
    if (!TextUtils.isEmpty(paramString)) {
      ((TextView)localView.findViewById(2131624161)).setText(paramString);
    }
    localImageView.startAnimation(AnimationUtils.loadAnimation(paramContext, 2131034128));
    paramContext = new Dialog(paramContext, 2131362127);
    paramContext.setCancelable(false);
    paramContext.setContentView(localRelativeLayout, new LinearLayout.LayoutParams(-1, -1));
    return paramContext;
  }
  
  public static String e(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext != null) {
      return paramContext.getSimCountryIso();
    }
    return null;
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
      if (!TextUtils.isEmpty(paramContext))
      {
        paramContext = paramContext.substring(0, 3);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      return null;
    }
    return null;
  }
  
  /* Error */
  public static String f(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 480	java/io/InputStreamReader
    //   3: dup
    //   4: aload_0
    //   5: invokevirtual 15	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   8: invokevirtual 484	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   11: aload_1
    //   12: invokevirtual 490	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   15: invokespecial 493	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   18: astore_2
    //   19: new 495	java/io/BufferedReader
    //   22: dup
    //   23: aload_2
    //   24: invokespecial 498	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   27: astore_1
    //   28: ldc_w 283
    //   31: astore_0
    //   32: aload_1
    //   33: astore 4
    //   35: aload_2
    //   36: astore_3
    //   37: aload_1
    //   38: invokevirtual 501	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   41: astore 5
    //   43: aload 5
    //   45: ifnull +41 -> 86
    //   48: aload_1
    //   49: astore 4
    //   51: aload_2
    //   52: astore_3
    //   53: new 280	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 281	java/lang/StringBuilder:<init>	()V
    //   60: aload_0
    //   61: invokevirtual 287	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: aload 5
    //   66: invokevirtual 287	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc_w 503
    //   72: invokevirtual 287	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: invokevirtual 295	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: astore 5
    //   80: aload 5
    //   82: astore_0
    //   83: goto -51 -> 32
    //   86: aload_1
    //   87: ifnull +7 -> 94
    //   90: aload_1
    //   91: invokevirtual 506	java/io/BufferedReader:close	()V
    //   94: aload_0
    //   95: astore_1
    //   96: aload_2
    //   97: ifnull +9 -> 106
    //   100: aload_2
    //   101: invokevirtual 507	java/io/InputStreamReader:close	()V
    //   104: aload_0
    //   105: astore_1
    //   106: aload_1
    //   107: areturn
    //   108: astore_1
    //   109: aload_1
    //   110: invokevirtual 508	java/io/IOException:printStackTrace	()V
    //   113: aload_0
    //   114: areturn
    //   115: astore 5
    //   117: aconst_null
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_2
    //   121: ldc_w 283
    //   124: astore_0
    //   125: aload_1
    //   126: astore 4
    //   128: aload_2
    //   129: astore_3
    //   130: aload 5
    //   132: invokevirtual 306	java/lang/Exception:printStackTrace	()V
    //   135: aload_1
    //   136: ifnull +7 -> 143
    //   139: aload_1
    //   140: invokevirtual 506	java/io/BufferedReader:close	()V
    //   143: aload_0
    //   144: astore_1
    //   145: aload_2
    //   146: ifnull -40 -> 106
    //   149: aload_2
    //   150: invokevirtual 507	java/io/InputStreamReader:close	()V
    //   153: aload_0
    //   154: areturn
    //   155: astore_1
    //   156: aload_1
    //   157: invokevirtual 508	java/io/IOException:printStackTrace	()V
    //   160: aload_0
    //   161: areturn
    //   162: astore_0
    //   163: aconst_null
    //   164: astore 4
    //   166: aconst_null
    //   167: astore_2
    //   168: aload 4
    //   170: ifnull +8 -> 178
    //   173: aload 4
    //   175: invokevirtual 506	java/io/BufferedReader:close	()V
    //   178: aload_2
    //   179: ifnull +7 -> 186
    //   182: aload_2
    //   183: invokevirtual 507	java/io/InputStreamReader:close	()V
    //   186: aload_0
    //   187: athrow
    //   188: astore_1
    //   189: aload_1
    //   190: invokevirtual 508	java/io/IOException:printStackTrace	()V
    //   193: goto -7 -> 186
    //   196: astore_0
    //   197: aconst_null
    //   198: astore 4
    //   200: goto -32 -> 168
    //   203: astore_0
    //   204: aload_3
    //   205: astore_2
    //   206: goto -38 -> 168
    //   209: astore 5
    //   211: aconst_null
    //   212: astore_1
    //   213: ldc_w 283
    //   216: astore_0
    //   217: goto -92 -> 125
    //   220: astore 5
    //   222: goto -97 -> 125
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	paramContext	Context
    //   0	225	1	paramString	String
    //   18	188	2	localObject1	Object
    //   36	169	3	localObject2	Object
    //   33	166	4	str1	String
    //   41	40	5	str2	String
    //   115	16	5	localException1	Exception
    //   209	1	5	localException2	Exception
    //   220	1	5	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   90	94	108	java/io/IOException
    //   100	104	108	java/io/IOException
    //   0	19	115	java/lang/Exception
    //   139	143	155	java/io/IOException
    //   149	153	155	java/io/IOException
    //   0	19	162	finally
    //   173	178	188	java/io/IOException
    //   182	186	188	java/io/IOException
    //   19	28	196	finally
    //   37	43	203	finally
    //   53	80	203	finally
    //   130	135	203	finally
    //   19	28	209	java/lang/Exception
    //   37	43	220	java/lang/Exception
    //   53	80	220	java/lang/Exception
  }
  
  public static Notification g(Context paramContext)
  {
    NotificationManager localNotificationManager = h(paramContext);
    ab.d localD = new ab.d(paramContext);
    Object localObject = new Intent();
    ((Intent)localObject).setClass(paramContext, NotificationReceiver.class);
    ((Intent)localObject).setAction("messenger.pro.messenger.CLICK_ACTIVE_APP_NOTIFI");
    localObject = PendingIntent.getBroadcast(paramContext, 1, (Intent)localObject, 268435456);
    Bitmap localBitmap = a(paramContext.getResources().getDrawable(2130903040));
    localD.b(paramContext.getResources().getString(2131165293)).a(paramContext.getResources().getString(2131165294)).a(2130837698).a(localBitmap).a((PendingIntent)localObject).b(1).a(true);
    paramContext = localD.a();
    localNotificationManager.notify(1, paramContext);
    return paramContext;
  }
  
  public static boolean g(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramContext = k(paramContext).getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
  }
  
  public static NotificationManager h(Context paramContext)
  {
    return (NotificationManager)paramContext.getSystemService("notification");
  }
  
  public static boolean i(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((paramContext != null) && (paramContext.isConnected()))
    {
      int i = paramContext.getType();
      if ((i == 1) || (i == 0)) {
        return true;
      }
    }
    return false;
  }
  
  public static ArrayList<AddAppInfo> j(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = k(paramContext);
      List localList = paramContext.getInstalledPackages(0);
      if (localList != null)
      {
        int i = 0;
        while (i < localList.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
          if ((localPackageInfo != null) && (!TextUtils.isEmpty(localPackageInfo.packageName)) && (localPackageInfo.applicationInfo != null) && (localPackageInfo.applicationInfo.loadLabel(paramContext) != null))
          {
            AddAppInfo localAddAppInfo = new AddAppInfo();
            localAddAppInfo.mAppName = localPackageInfo.applicationInfo.loadLabel(paramContext).toString();
            localAddAppInfo.mPkgName = localPackageInfo.packageName;
            localArrayList.add(localAddAppInfo);
          }
          i += 1;
        }
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static final PackageManager k(Context paramContext)
  {
    if (a == null) {
      a = paramContext.getApplicationContext().getPackageManager();
    }
    return a;
  }
  
  public static void l(Context paramContext)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    HashSet localHashSet = new HashSet();
    String[] arrayOfString = com.messenger.modules.a.a.a;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (g(paramContext, str)) {
        localHashSet.add((String)com.messenger.modules.a.a.b.get(str));
      }
      i += 1;
    }
    if (localHashSet.size() > 0)
    {
      localSharedPreferences.edit().putStringSet("pref_has_mainland_app_list", localHashSet).apply();
      localSharedPreferences.edit().putBoolean("pref_has_mainland_app", true).apply();
      return;
    }
    localSharedPreferences.edit().remove("pref_has_mainland_app_list").apply();
    localSharedPreferences.edit().putBoolean("pref_has_mainland_app", false).apply();
  }
}
