package com.instantbits.android.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class p
{
  public static final boolean a;
  public static final boolean b;
  public static final boolean c;
  public static final boolean d;
  public static final boolean e;
  public static final boolean f;
  public static final boolean g;
  public static final boolean h;
  public static final boolean i;
  public static boolean j = false;
  private static final String k = "com.instantbits.android.utils.p";
  private static String l;
  private static String m;
  private static int n = -1;
  private static int o = Runtime.getRuntime().availableProcessors();
  private static Process p;
  
  static
  {
    int i1 = Build.VERSION.SDK_INT;
    boolean bool2 = false;
    if (i1 >= 21) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    a = bool1;
    if (Build.VERSION.SDK_INT >= 23) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    b = bool1;
    if (Build.VERSION.SDK_INT >= 24) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    c = bool1;
    if (Build.VERSION.SDK_INT >= 25) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    d = bool1;
    if (Build.VERSION.SDK_INT >= 26) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    e = bool1;
    if (Build.VERSION.SDK_INT >= 19) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    f = bool1;
    if (Build.VERSION.SDK_INT >= 16) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    g = bool1;
    if (Build.VERSION.SDK_INT >= 17) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    h = bool1;
    if (Build.VERSION.SDK_INT >= 18) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    i = bool1;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT == 19) {
      bool1 = true;
    }
    j = bool1;
  }
  
  private p() {}
  
  public static String a(boolean paramBoolean)
  {
    Object localObject = Arrays.asList(new String[] { "external_sd", "ext_sd", "external", "extsdcard", "sdcard2", "m_external_sd" }).iterator();
    while (((Iterator)localObject).hasNext())
    {
      File localFile = new File("/mnt/", (String)((Iterator)localObject).next());
      if ((localFile.isDirectory()) && ((localFile.canWrite()) || (!paramBoolean)))
      {
        localObject = localFile.getAbsolutePath();
        break label100;
      }
    }
    localObject = null;
    label100:
    if (localObject != null) {
      return new File((String)localObject).getAbsolutePath();
    }
    return null;
  }
  
  @RequiresApi(api=23)
  public static void a(Activity paramActivity, int paramInt, String paramString, p.b paramB)
  {
    a(paramActivity, paramInt, paramString, paramActivity.getString(q.g.permission_title_storage), q.g.write_files_permission_denied_message, new p.4(paramB));
  }
  
  @RequiresApi(api=23)
  private static void a(Activity paramActivity, int paramInt1, String paramString1, String paramString2, int paramInt2, p.a paramA)
  {
    if (paramActivity.shouldShowRequestPermissionRationale(paramString1))
    {
      e.a(paramActivity, paramInt2, paramString1, paramInt1, paramA);
      return;
    }
    e.a(paramActivity, paramString1, paramInt1, paramString2);
  }
  
  @RequiresApi(api=23)
  public static void a(Activity paramActivity, p.b paramB, int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    boolean bool2 = true;
    boolean bool3 = true;
    boolean bool4 = true;
    boolean bool1 = true;
    switch (paramInt)
    {
    default: 
      
    case 5: 
      if (paramArrayOfInt.length > 0)
      {
        if (paramArrayOfInt[0] != 0) {
          bool1 = false;
        }
        paramB.d(bool1);
        if (bool1) {
          return;
        }
        b(paramActivity, paramInt, paramArrayOfString[0], paramB);
        return;
      }
      break;
    case 4: 
      if (paramArrayOfInt.length > 0)
      {
        if (paramArrayOfInt[0] == 0) {
          bool1 = bool2;
        } else {
          bool1 = false;
        }
        paramB.c(bool1);
        if (bool1) {
          return;
        }
        c(paramActivity, paramInt, paramArrayOfString[0], paramB);
        return;
      }
      break;
    case 3: 
      if (paramArrayOfInt.length > 0)
      {
        if (paramArrayOfInt[0] == 0) {
          bool1 = bool3;
        } else {
          bool1 = false;
        }
        paramB.b(bool1);
        if (bool1) {
          return;
        }
        a(paramActivity, paramInt, paramArrayOfString[0], paramB);
        return;
      }
      break;
    case 2: 
      if (paramArrayOfInt.length > 0)
      {
        if (paramArrayOfInt[0] == 0) {
          bool1 = bool4;
        } else {
          bool1 = false;
        }
        paramB.a(bool1);
        if (bool1) {
          return;
        }
        d(paramActivity, paramInt, paramArrayOfString[0], paramB);
      }
      break;
    }
  }
  
  public static void a(Application paramApplication)
  {
    Object localObject = p;
    int i2 = 0;
    int i1 = i2;
    if (localObject != null) {
      try
      {
        i1 = p.exitValue();
        localObject = k;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Exited with code ");
        localStringBuilder.append(i1);
        Log.i((String)localObject, localStringBuilder.toString());
        i1 = i2;
      }
      catch (IllegalThreadStateException localIllegalThreadStateException)
      {
        Log.w(k, localIllegalThreadStateException);
        i1 = 1;
      }
    }
    if (i1 == 0)
    {
      paramApplication = new p.5("logcat", paramApplication);
      paramApplication.setDaemon(true);
      paramApplication.start();
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramString1);
    if (localIntent == null)
    {
      c(paramContext, paramString1, paramString2);
      return;
    }
    paramContext.startActivity(localIntent);
  }
  
  public static boolean a()
  {
    return false;
  }
  
  public static boolean a(Activity paramActivity)
  {
    return a(paramActivity, "android.permission.READ_PHONE_STATE", 2);
  }
  
  protected static boolean a(Activity paramActivity, String paramString, int paramInt)
  {
    if (!c(paramActivity, paramString))
    {
      ActivityCompat.requestPermissions(paramActivity, new String[] { paramString }, paramInt);
      return false;
    }
    return true;
  }
  
  public static boolean a(Context paramContext)
  {
    if (b) {
      return ((PowerManager)paramContext.getSystemService("power")).isIgnoringBatteryOptimizations(c(paramContext));
    }
    return true;
  }
  
  protected static boolean a(Context paramContext, Intent paramIntent)
    throws IOException
  {
    File localFile = c((Application)paramContext.getApplicationContext());
    if ((localFile.exists()) && (localFile.canRead()))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Environment.getExternalStorageDirectory());
      localStringBuilder.append("/");
      localStringBuilder.append(paramContext.getString(q.g.app_name));
      localStringBuilder.append("/app.log");
      paramContext = new File(localStringBuilder.toString());
      h.a(localFile, paramContext);
      paramIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(paramContext));
      return true;
    }
    paramContext = new StringBuilder();
    paramContext.append("Exists ");
    paramContext.append(localFile.exists());
    paramContext.append(" and can read ");
    paramContext.append(localFile.canRead());
    throw new FileNotFoundException(paramContext.toString());
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  @NonNull
  public static String b()
  {
    return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
  }
  
  public static String b(Context paramContext)
  {
    if (l == null) {
      try
      {
        paramContext = d(paramContext);
        paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("v");
        localStringBuilder.append(paramContext.versionName);
        localStringBuilder.append(" r");
        localStringBuilder.append(paramContext.versionCode);
        l = localStringBuilder.toString();
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Log.w(k, "Error getting version.", paramContext);
        a.a(paramContext);
      }
    }
    return l;
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("https://play.google.com/store/apps/details?id=");
    ((StringBuilder)localObject).append(paramString1);
    localObject = ((StringBuilder)localObject).toString();
    paramString1 = (String)localObject;
    if (!TextUtils.isEmpty(paramString2))
    {
      paramString1 = new StringBuilder();
      paramString1.append((String)localObject);
      paramString1.append(e(paramContext, paramString2));
      paramString1 = paramString1.toString();
    }
    return paramString1;
  }
  
  @RequiresApi(api=23)
  private static void b(Activity paramActivity, int paramInt, String paramString, p.b paramB)
  {
    a(paramActivity, paramInt, paramString, paramActivity.getString(q.g.permission_title_accounts), q.g.accounts_permission_denied_message, new p.1(paramB));
  }
  
  public static void b(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    paramContext.startActivity(localIntent);
  }
  
  public static boolean b(Activity paramActivity)
  {
    return a(paramActivity, "android.permission.WRITE_EXTERNAL_STORAGE", 3);
  }
  
  @NonNull
  private static File c(Application paramApplication)
  {
    paramApplication = paramApplication.getFilesDir();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramApplication);
    localStringBuilder.append("/app.log");
    return new File(localStringBuilder.toString());
  }
  
  public static String c(Context paramContext)
  {
    if (m == null) {
      m = d(paramContext).getPackageName();
    }
    return m;
  }
  
  public static void c()
  {
    try
    {
      long l1 = Runtime.getRuntime().totalMemory();
      long l2 = Runtime.getRuntime().freeMemory();
      long l3 = Runtime.getRuntime().maxMemory();
      HashMap localHashMap = new HashMap();
      localHashMap.put("total", Long.valueOf(l1));
      localHashMap.put("free", Long.valueOf(l2));
      localHashMap.put("max", Long.valueOf(l3));
      a.a("mem_usage", localHashMap);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.w(k, localThrowable);
      a.a(localThrowable);
    }
  }
  
  @RequiresApi(api=23)
  private static void c(Activity paramActivity, int paramInt, String paramString, p.b paramB)
  {
    a(paramActivity, paramInt, paramString, paramActivity.getString(q.g.permission_title_location), q.g.location_permission_denied_message, new p.2(paramB));
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("market://details?id=");
      ((StringBuilder)localObject).append(paramString1);
      String str = ((StringBuilder)localObject).toString();
      localObject = str;
      if (!TextUtils.isEmpty(paramString2))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(str);
        ((StringBuilder)localObject).append(e(paramContext, paramString2));
        localObject = ((StringBuilder)localObject).toString();
      }
      localIntent.setData(Uri.parse((String)localObject));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.w(k, "Error starting  intent ", localThrowable);
      a.a(localThrowable);
      if (paramString1 != null) {
        try
        {
          paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(b(paramContext, paramString1, paramString2))));
          return;
        }
        catch (Throwable paramContext)
        {
          Log.w(k, "Error starting intent web page", paramContext);
          a.a(paramContext);
        }
      }
    }
  }
  
  public static boolean c(Activity paramActivity)
  {
    return a(paramActivity, "android.permission.ACCESS_FINE_LOCATION", 4);
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    boolean bool2 = b;
    boolean bool1 = true;
    if (bool2)
    {
      if (ContextCompat.checkSelfPermission(paramContext, paramString) == 0) {
        return true;
      }
      bool1 = false;
    }
    return bool1;
  }
  
  protected static Context d(Context paramContext)
  {
    Object localObject = paramContext;
    if (paramContext == null) {
      localObject = a.b().a();
    }
    return localObject;
  }
  
  public static void d()
  {
    System.setProperty("java.net.preferIPv4Stack", "true");
    System.setProperty("java.net.preferIPv6Addresses", "false");
  }
  
  @TargetApi(23)
  private static void d(Activity paramActivity, int paramInt, String paramString, p.b paramB)
  {
    a(paramActivity, paramInt, paramString, paramActivity.getString(q.g.permission_title_phone_state), q.g.phone_state_permission_denied_message, new p.3(paramB));
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if ((localRunningAppProcessInfo.importance == 100) && (localRunningAppProcessInfo.processName.equals(paramString)))
      {
        a.a("App is on foreground");
        return true;
      }
    }
    return false;
  }
  
  public static String e(Context paramContext)
  {
    return paramContext.getString(paramContext.getApplicationInfo().labelRes);
  }
  
  @NonNull
  private static String e(Context paramContext, String paramString)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("&referrer=utm_source%3D");
      ((StringBuilder)localObject).append(URLEncoder.encode(e(paramContext), "UTF-8"));
      ((StringBuilder)localObject).append("%26utm_medium%3D");
      ((StringBuilder)localObject).append(URLEncoder.encode(paramString, "UTF-8"));
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Log.w(k, "Encoding exception  ", localUnsupportedEncodingException);
      a.a(localUnsupportedEncodingException);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("&referrer=utm_source%3D");
      localStringBuilder.append(e(paramContext));
      localStringBuilder.append("%26utm_medium%3D");
      localStringBuilder.append(paramString);
      return localStringBuilder.toString();
    }
  }
  
  public static boolean f(Context paramContext)
  {
    return d(paramContext, c(paramContext));
  }
}
