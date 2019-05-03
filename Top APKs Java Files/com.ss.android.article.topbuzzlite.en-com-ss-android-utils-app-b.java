package com.ss.android.utils.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import com.crashlytics.android.Crashlytics;
import com.ss.android.utils.kit.string.StringUtils;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class b
{
  private static final Handler a = new Handler(Looper.getMainLooper());
  private static String b = null;
  private static String c = null;
  private static boolean d = false;
  private static String e = null;
  
  public static int a(Context paramContext)
  {
    if (paramContext == null) {
      return -1;
    }
    int j = 0;
    int i = 0;
    try
    {
      if (!StringUtils.isEmpty(Settings.Secure.getString(paramContext.getContentResolver(), "location_providers_allowed")))
      {
        paramContext = (LocationManager)paramContext.getSystemService("location");
        j = i;
        if (paramContext.isProviderEnabled("gps")) {
          j = 1;
        }
        i = j;
        if (paramContext.isProviderEnabled("network")) {
          i = j | 0x2;
        }
        boolean bool = paramContext.isProviderEnabled("passive");
        j = i;
        if (bool) {
          j = i | 0x4;
        }
      }
      return j;
    }
    catch (Throwable paramContext) {}
    return -1;
  }
  
  public static long a(File paramFile, boolean paramBoolean)
  {
    long l1 = 0L;
    long l3 = l1;
    try
    {
      if (!paramFile.exists())
      {
        l3 = l1;
        if (paramFile.isDirectory()) {
          return 0L;
        }
      }
      l3 = l1;
      paramFile = paramFile.listFiles();
      l3 = l1;
      int j = paramFile.length;
      int i = 0;
      while (i < j)
      {
        File localFile = paramFile[i];
        l3 = l1;
        long l2;
        if (localFile.isDirectory())
        {
          l3 = l1;
          l2 = l1 + a(localFile, paramBoolean);
        }
        else
        {
          l2 = l1;
          l3 = l1;
          if (localFile.isFile())
          {
            l3 = l1;
            l2 = localFile.length();
            l2 = l1 + l2;
          }
        }
        i += 1;
        l1 = l2;
      }
      return l1;
    }
    catch (Throwable paramFile) {}
    return l3;
  }
  
  public static Intent a(Context paramContext, String paramString)
  {
    paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString == null) {
      return paramString;
    }
    paramString.putExtra("LaunchIntentSource", paramContext.toString());
    if (!paramString.hasCategory("android.intent.category.LAUNCHER"))
    {
      paramString.addCategory("android.intent.category.LAUNCHER");
      com.ss.android.utils.kit.b.b("AppUtils", "add category LAUNCHER in launch intent");
    }
    paramString.setPackage(null);
    paramString.addFlags(2097152);
    paramString.addFlags(268435456);
    return paramString;
  }
  
  public static List<ActivityManager.RecentTaskInfo> a(ActivityManager paramActivityManager, int paramInt1, int paramInt2)
  {
    List localList = null;
    if (paramActivityManager == null) {
      return null;
    }
    if (Build.VERSION.SDK_INT < 21) {
      localList = paramActivityManager.getRecentTasks(paramInt1, paramInt2);
    }
    return localList;
  }
  
  @Deprecated
  public static JSONObject a(JSONObject paramJSONObject, String paramString)
  {
    if (StringUtils.isEmpty(paramString)) {
      return paramJSONObject;
    }
    try
    {
      paramString = a(paramJSONObject, new JSONObject(paramString));
      return paramString;
    }
    catch (Exception paramString) {}
    return paramJSONObject;
  }
  
  @Deprecated
  public static JSONObject a(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    str1 = null;
    if ((paramJSONObject1 == null) && (paramJSONObject2 == null)) {
      return null;
    }
    if (paramJSONObject1 == null) {
      return paramJSONObject2;
    }
    if (paramJSONObject2 == null) {
      return paramJSONObject1;
    }
    HashMap localHashMap = new HashMap();
    try
    {
      localObject = new JSONObject();
      Iterator localIterator2 = paramJSONObject1.keys();
      Iterator localIterator1 = paramJSONObject2.keys();
      while (localIterator2.hasNext())
      {
        String str2 = (String)localIterator2.next();
        ((JSONObject)localObject).put(str2, paramJSONObject1.opt(str2));
      }
      while (localIterator1.hasNext())
      {
        paramJSONObject1 = (String)localIterator1.next();
        if ((com.ss.android.utils.kit.b.b()) && (((JSONObject)localObject).has(paramJSONObject1))) {
          localHashMap.put(paramJSONObject1, paramJSONObject2.optString(paramJSONObject1));
        }
        ((JSONObject)localObject).put(paramJSONObject1, paramJSONObject2.opt(paramJSONObject1));
      }
      paramJSONObject1 = (JSONObject)localObject;
    }
    catch (Exception paramJSONObject1)
    {
      for (;;)
      {
        Object localObject;
        paramJSONObject1 = str1;
      }
    }
    if (com.ss.android.utils.kit.b.b())
    {
      com.ss.android.utils.kit.b.b("MiscUtils", "override occurs");
      paramJSONObject2 = localHashMap.keySet().iterator();
      while (paramJSONObject2.hasNext())
      {
        str1 = (String)paramJSONObject2.next();
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(str1);
        ((StringBuilder)localObject).append(":");
        ((StringBuilder)localObject).append((String)localHashMap.get(str1));
        com.ss.android.utils.kit.b.b("MiscUtils", ((StringBuilder)localObject).toString());
      }
    }
    return paramJSONObject1;
  }
  
  public static void a(Activity paramActivity)
  {
    try
    {
      Field localField = ((ActivityManager)paramActivity.getSystemService("activity")).getClass().getDeclaredField("mContext");
      if ((localField.getModifiers() & 0x8) > 0)
      {
        localField.setAccessible(true);
        if (localField.get(null) == paramActivity)
        {
          localField.set(null, null);
          return;
        }
      }
    }
    catch (Exception paramActivity)
    {
      com.ss.android.utils.kit.b.e("AppUtils", paramActivity.getMessage());
    }
  }
  
  public static void a(Context paramContext, long paramLong)
  {
    try
    {
      ((PowerManager)paramContext.getSystemService("power")).newWakeLock(268435482, "topbuzz:light up wakelock").acquire(paramLong);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static void a(Context paramContext, Intent paramIntent, Bundle paramBundle)
  {
    if (paramContext != null)
    {
      if (paramIntent == null) {
        return;
      }
      if (paramBundle != null) {
        paramIntent.putExtras(paramBundle);
      }
      paramContext.startActivity(paramIntent);
      return;
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool;
    if (!StringUtils.isEmpty(paramString1)) {
      bool = b(paramContext, paramString1);
    } else {
      bool = false;
    }
    if (bool)
    {
      Intent localIntent = a(paramContext, paramString1);
      if (localIntent != null)
      {
        paramContext.startActivity(localIntent);
        return;
      }
    }
    if (!StringUtils.isEmpty(paramString2)) {}
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString2)));
      return;
    }
    catch (Exception paramString2)
    {
      for (;;)
      {
        try
        {
          paramContext.startActivity(paramString1);
          return;
        }
        catch (Exception paramContext) {}
        paramString2 = paramString2;
      }
    }
    if (!StringUtils.isEmpty(paramString1))
    {
      paramString2 = new StringBuilder();
      paramString2.append("market://details?id=");
      paramString2.append(paramString1);
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2.toString()));
      paramString1.addFlags(268435456);
    }
  }
  
  public static void a(DialogInterface paramDialogInterface)
  {
    if (paramDialogInterface != null)
    {
      if (c(paramDialogInterface)) {
        return;
      }
      if (Looper.myLooper() == Looper.getMainLooper()) {
        try
        {
          paramDialogInterface.dismiss();
          return;
        }
        catch (Throwable paramDialogInterface)
        {
          Crashlytics.logException(paramDialogInterface);
          return;
        }
      }
      final RuntimeException localRuntimeException = new RuntimeException("Dialog dismiss exception");
      a.post(new Runnable()
      {
        public void run()
        {
          if (b.b(this.a)) {
            return;
          }
          try
          {
            this.a.dismiss();
            return;
          }
          catch (Throwable localThrowable)
          {
            for (;;) {}
          }
          Crashlytics.logException(localRuntimeException);
        }
      });
    }
  }
  
  public static void a(String paramString, Set<String> paramSet)
    throws Exception
  {
    paramString = new File(paramString);
    if (!paramString.exists()) {
      return;
    }
    paramString = paramString.listFiles();
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      if (paramString[i].isDirectory())
      {
        b(paramString[i].getAbsolutePath(), paramSet);
      }
      else if (paramString[i].isFile())
      {
        String str = paramString[i].getName();
        if ((paramSet == null) || (!paramSet.contains(str))) {
          paramString[i].delete();
        }
      }
      i += 1;
    }
  }
  
  public static boolean a()
  {
    try
    {
      Class localClass = Class.forName("miui.os.Build");
      if (localClass != null) {
        return true;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent, String paramString)
  {
    if (paramIntent == null) {
      return false;
    }
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536);
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        if (TextUtils.equals(paramString, ((ResolveInfo)paramContext.next()).activityInfo.packageName)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean a(Object paramObject)
  {
    if ((Build.VERSION.SDK_INT >= 17) && ((paramObject instanceof Activity))) {
      return ((Activity)paramObject).isDestroyed();
    }
    return false;
  }
  
  public static boolean a(Object paramObject1, Object paramObject2)
  {
    return ((paramObject1 == null) && (paramObject2 == null)) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static boolean a(String paramString)
  {
    boolean bool2 = StringUtils.isEmpty(paramString);
    boolean bool1 = false;
    if (bool2) {
      return false;
    }
    if ((paramString.startsWith("http://")) || (paramString.startsWith("https://"))) {
      bool1 = true;
    }
    return bool1;
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    if (paramString1 != null)
    {
      if (paramString2 == null) {
        return false;
      }
      if (paramString1.equals(paramString2)) {
        return true;
      }
      int i = paramString1.indexOf('#');
      String str = paramString1;
      if (i > 0) {
        str = paramString1.substring(0, i);
      }
      i = paramString2.indexOf('#');
      paramString1 = paramString2;
      if (i > 0) {
        paramString1 = paramString2.substring(0, i);
      }
      return str.equals(paramString1);
    }
    return false;
  }
  
  /* Error */
  public static String b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 235	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   9: astore_1
    //   10: aload_1
    //   11: ldc_w 440
    //   14: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: aload_1
    //   19: invokestatic 445	android/os/Process:myPid	()I
    //   22: invokevirtual 448	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload_1
    //   27: ldc_w 450
    //   30: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: new 452	java/io/BufferedReader
    //   37: dup
    //   38: new 454	java/io/InputStreamReader
    //   41: dup
    //   42: new 456	java/io/FileInputStream
    //   45: dup
    //   46: aload_1
    //   47: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokespecial 457	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   53: ldc_w 459
    //   56: invokespecial 462	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   59: invokespecial 465	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   62: astore_1
    //   63: new 235	java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   70: astore_2
    //   71: aload_1
    //   72: invokevirtual 468	java/io/BufferedReader:read	()I
    //   75: istore_0
    //   76: iload_0
    //   77: ifle +13 -> 90
    //   80: aload_2
    //   81: iload_0
    //   82: i2c
    //   83: invokevirtual 471	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: goto -16 -> 71
    //   90: invokestatic 208	com/ss/android/utils/kit/b:b	()Z
    //   93: ifeq +38 -> 131
    //   96: new 235	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   103: astore_3
    //   104: aload_3
    //   105: ldc_w 473
    //   108: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: aload_3
    //   113: aload_2
    //   114: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: pop
    //   121: ldc_w 475
    //   124: aload_3
    //   125: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokestatic 142	com/ss/android/utils/kit/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   131: aload_2
    //   132: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: astore_2
    //   136: aload_1
    //   137: invokevirtual 478	java/io/BufferedReader:close	()V
    //   140: aload_2
    //   141: areturn
    //   142: astore_2
    //   143: goto +9 -> 152
    //   146: goto +18 -> 164
    //   149: astore_2
    //   150: aload_3
    //   151: astore_1
    //   152: aload_1
    //   153: ifnull +7 -> 160
    //   156: aload_1
    //   157: invokevirtual 478	java/io/BufferedReader:close	()V
    //   160: aload_2
    //   161: athrow
    //   162: aconst_null
    //   163: astore_1
    //   164: aload_1
    //   165: ifnull +7 -> 172
    //   168: aload_1
    //   169: invokevirtual 478	java/io/BufferedReader:close	()V
    //   172: aconst_null
    //   173: areturn
    //   174: astore_1
    //   175: goto -13 -> 162
    //   178: astore_2
    //   179: goto -33 -> 146
    //   182: astore_1
    //   183: aload_2
    //   184: areturn
    //   185: astore_1
    //   186: goto -26 -> 160
    //   189: astore_1
    //   190: aconst_null
    //   191: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   75	7	0	i	int
    //   9	160	1	localObject1	Object
    //   174	1	1	localThrowable1	Throwable
    //   182	1	1	localException1	Exception
    //   185	1	1	localException2	Exception
    //   189	1	1	localException3	Exception
    //   70	71	2	localObject2	Object
    //   142	1	2	localObject3	Object
    //   149	12	2	localObject4	Object
    //   178	6	2	localThrowable2	Throwable
    //   1	150	3	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   63	71	142	finally
    //   71	76	142	finally
    //   80	87	142	finally
    //   90	131	142	finally
    //   131	136	142	finally
    //   2	63	149	finally
    //   2	63	174	java/lang/Throwable
    //   63	71	178	java/lang/Throwable
    //   71	76	178	java/lang/Throwable
    //   80	87	178	java/lang/Throwable
    //   90	131	178	java/lang/Throwable
    //   131	136	178	java/lang/Throwable
    //   136	140	182	java/lang/Exception
    //   156	160	185	java/lang/Exception
    //   168	172	189	java/lang/Exception
  }
  
  public static String b(Context paramContext)
  {
    if (b == null) {}
    try
    {
      b = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return b;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
  }
  
  public static void b(String paramString, Set<String> paramSet)
    throws Exception
  {
    paramString = new File(paramString);
    if ((paramString.exists()) && (paramString.isDirectory()))
    {
      paramString = paramString.listFiles();
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        if (paramString[i].isDirectory())
        {
          b(paramString[i].getAbsolutePath(), paramSet);
        }
        else
        {
          String str = paramString[i].getName();
          if ((paramSet == null) || (!paramSet.contains(str))) {
            paramString[i].delete();
          }
        }
        i += 1;
      }
    }
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (!StringUtils.isEmpty(paramString)) {
        paramContext = paramContext.getPackageManager();
      }
    }
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
      bool1 = bool2;
      if (paramContext != null) {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  @SuppressLint({"MissingPermission"})
  public static String c(Context paramContext)
  {
    return c;
  }
  
  public static boolean c()
  {
    try
    {
      boolean bool = "mounted".equals(Environment.getExternalStorageState());
      return bool;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  /* Error */
  public static boolean c(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: invokestatic 501	java/lang/System:currentTimeMillis	()J
    //   3: lstore_2
    //   4: aload_0
    //   5: invokestatic 504	com/ss/android/utils/app/b:k	(Landroid/content/Context;)Ljava/lang/String;
    //   8: astore 7
    //   10: aload 7
    //   12: ifnonnull +5 -> 17
    //   15: iconst_0
    //   16: ireturn
    //   17: invokestatic 501	java/lang/System:currentTimeMillis	()J
    //   20: lstore 4
    //   22: new 235	java/lang/StringBuilder
    //   25: dup
    //   26: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   29: astore 8
    //   31: aload 8
    //   33: ldc_w 506
    //   36: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload 8
    //   42: lload 4
    //   44: lload_2
    //   45: lsub
    //   46: ldc2_w 507
    //   49: ldiv
    //   50: invokevirtual 511	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload 8
    //   56: ldc_w 513
    //   59: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: ldc_w 515
    //   66: aload 8
    //   68: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: invokestatic 142	com/ss/android/utils/kit/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   74: new 235	java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   81: astore 8
    //   83: aload 8
    //   85: ldc_w 517
    //   88: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: pop
    //   92: aload 8
    //   94: aload 7
    //   96: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: aload 8
    //   102: ldc_w 519
    //   105: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: pop
    //   109: aload 8
    //   111: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: astore 10
    //   116: aconst_null
    //   117: astore 8
    //   119: aconst_null
    //   120: astore 9
    //   122: aload 9
    //   124: astore 7
    //   126: aload 10
    //   128: invokestatic 326	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   131: astore 10
    //   133: aload 9
    //   135: astore 7
    //   137: aload_0
    //   138: invokevirtual 48	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   141: aload 10
    //   143: aconst_null
    //   144: ldc_w 521
    //   147: iconst_1
    //   148: anewarray 199	java/lang/String
    //   151: dup
    //   152: iconst_0
    //   153: aload_1
    //   154: aastore
    //   155: aconst_null
    //   156: invokevirtual 527	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   159: astore_0
    //   160: aload_0
    //   161: ifnull +41 -> 202
    //   164: aload_0
    //   165: invokeinterface 532 1 0
    //   170: istore 6
    //   172: iload 6
    //   174: ifeq +28 -> 202
    //   177: aload_0
    //   178: ifnull +9 -> 187
    //   181: aload_0
    //   182: invokeinterface 533 1 0
    //   187: iconst_1
    //   188: ireturn
    //   189: astore_1
    //   190: aload_0
    //   191: astore 7
    //   193: aload_1
    //   194: astore_0
    //   195: goto +46 -> 241
    //   198: astore_1
    //   199: goto +23 -> 222
    //   202: aload_0
    //   203: ifnull +36 -> 239
    //   206: aload_0
    //   207: invokeinterface 533 1 0
    //   212: iconst_0
    //   213: ireturn
    //   214: astore_0
    //   215: goto +26 -> 241
    //   218: astore_1
    //   219: aload 8
    //   221: astore_0
    //   222: aload_0
    //   223: astore 7
    //   225: aload_1
    //   226: invokevirtual 536	java/lang/Exception:printStackTrace	()V
    //   229: aload_0
    //   230: ifnull +9 -> 239
    //   233: aload_0
    //   234: invokeinterface 533 1 0
    //   239: iconst_0
    //   240: ireturn
    //   241: aload 7
    //   243: ifnull +10 -> 253
    //   246: aload 7
    //   248: invokeinterface 533 1 0
    //   253: aload_0
    //   254: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	255	0	paramContext	Context
    //   0	255	1	paramString	String
    //   3	42	2	l1	long
    //   20	23	4	l2	long
    //   170	3	6	bool	boolean
    //   8	239	7	localObject1	Object
    //   29	191	8	localStringBuilder	StringBuilder
    //   120	14	9	localObject2	Object
    //   114	28	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   164	172	189	finally
    //   164	172	198	java/lang/Exception
    //   126	133	214	finally
    //   137	160	214	finally
    //   225	229	214	finally
    //   126	133	218	java/lang/Exception
    //   137	160	218	java/lang/Exception
  }
  
  private static boolean c(DialogInterface paramDialogInterface)
  {
    if ((paramDialogInterface instanceof Dialog))
    {
      paramDialogInterface = (Dialog)paramDialogInterface;
      if (paramDialogInterface.isShowing())
      {
        paramDialogInterface = paramDialogInterface.getWindow();
        if ((paramDialogInterface != null) && (paramDialogInterface.getDecorView() != null)) {}
      }
      else
      {
        return true;
      }
    }
    return false;
  }
  
  public static String d(Context paramContext)
  {
    Object localObject = e;
    if (!StringUtils.isEmpty((String)localObject)) {
      return localObject;
    }
    try
    {
      int i = Process.myPid();
      localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (((Iterator)localObject).hasNext())
      {
        paramContext = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        if (paramContext.pid == i)
        {
          if (com.ss.android.utils.kit.b.b())
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("processName = ");
            ((StringBuilder)localObject).append(paramContext.processName);
            com.ss.android.utils.kit.b.b("Process", ((StringBuilder)localObject).toString());
          }
          e = paramContext.processName;
          paramContext = e;
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      e = b();
    }
    return e;
  }
  
  public static void d(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return;
    }
    for (;;)
    {
      try
      {
        localIntent = a(paramContext, paramString);
        if (localIntent == null) {
          return;
        }
        localObject2 = paramContext.getPackageManager();
        localApplicationInfo = ((PackageManager)localObject2).getApplicationInfo(paramString, 0);
        localResources = ((PackageManager)localObject2).getResourcesForApplication(localApplicationInfo);
        str = localResources.getResourceName(localApplicationInfo.icon);
        localObject1 = null;
      }
      catch (Exception paramContext)
      {
        Intent localIntent;
        Object localObject2;
        ApplicationInfo localApplicationInfo;
        Resources localResources;
        String str;
        Object localObject1;
        int i;
        paramString = new StringBuilder();
        paramString.append("addAppShortcut failed: ");
        paramString.append(paramContext);
        Log.w("AppUtils", paramString.toString());
        return;
      }
      try
      {
        i = ((PackageManager)localObject2).getActivityInfo(localIntent.getComponent(), 0).labelRes;
        if (i <= 0) {
          continue;
        }
        localObject1 = localResources.getString(i);
      }
      catch (Resources.NotFoundException localNotFoundException) {}
    }
    localObject1 = ((PackageManager)localObject2).getApplicationLabel(localApplicationInfo);
    if (localObject1 != null)
    {
      if (c(paramContext, (String)localObject1)) {
        return;
      }
      localObject2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
      ((Intent)localObject2).putExtra("android.intent.extra.shortcut.NAME", (CharSequence)localObject1);
      ((Intent)localObject2).putExtra("android.intent.extra.shortcut.INTENT", localIntent);
      localObject1 = new Intent.ShortcutIconResource();
      ((Intent.ShortcutIconResource)localObject1).packageName = paramString;
      ((Intent.ShortcutIconResource)localObject1).resourceName = str;
      ((Intent)localObject2).putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable)localObject1);
      ((Intent)localObject2).putExtra("duplicate", false);
      paramContext.sendBroadcast((Intent)localObject2);
      return;
    }
  }
  
  public static void e(Context paramContext)
  {
    g(paramContext, paramContext.getPackageName());
  }
  
  public static void e(Context paramContext, String paramString)
  {
    try
    {
      paramContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(paramString))));
      return;
    }
    catch (Exception paramContext)
    {
      paramString = new StringBuilder();
      paramString.append("add image media exception: ");
      paramString.append(paramContext);
      com.ss.android.utils.kit.b.d("AppUtils", paramString.toString());
    }
  }
  
  public static String f(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      if (StringUtils.isEmpty(paramString)) {
        return null;
      }
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 8).providers;
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramContext[i];
        if (paramString.equals(localObject.name))
        {
          paramContext = localObject.authority;
          return paramContext;
        }
        i += 1;
      }
      return null;
    }
    catch (Exception paramContext) {}
    return null;
    return null;
  }
  
  public static boolean f(Context paramContext)
  {
    return ((PowerManager)paramContext.getSystemService("power")).isScreenOn();
  }
  
  public static void g(Context paramContext, String paramString)
  {
    String str = paramString;
    if (StringUtils.isEmpty(paramString)) {
      str = paramContext.getPackageName();
    }
    paramString = new Intent("android.intent.action.VIEW");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("market://details?id=");
    ((StringBuilder)localObject).append(str);
    paramString.setData(Uri.parse(((StringBuilder)localObject).toString()));
    localObject = paramContext.getPackageManager().queryIntentActivities(paramString, 65536).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      if (localResolveInfo.activityInfo.packageName.equals("com.android.vending"))
      {
        paramString = paramContext.getPackageManager().getLaunchIntentForPackage("com.android.vending");
        if (paramString != null)
        {
          i = 1;
          paramString.setComponent(new ComponentName(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name));
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("market://details?id=");
          ((StringBuilder)localObject).append(str);
          paramString.setData(Uri.parse(((StringBuilder)localObject).toString()));
          if (!(paramContext instanceof Activity)) {
            paramString.setFlags(268435456);
          }
          paramContext.startActivity(paramString);
          break label230;
        }
      }
    }
    int i = 0;
    label230:
    if (i == 0)
    {
      paramString = new StringBuilder();
      paramString.append("https://play.google.com/store/apps/details?id=");
      paramString.append(str);
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString.toString()));
      if (!(paramContext instanceof Activity)) {
        paramString.setFlags(268435456);
      }
      paramContext.startActivity(paramString);
    }
  }
  
  public static boolean g(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (paramContext != null)
        {
          int i = paramContext.getIntExtra("status", -1);
          if ((i == 2) || (i == 5)) {
            return true;
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }
  
  public static int h(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return -1;
  }
  
  public static boolean i(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    try
    {
      boolean bool = ((AudioManager)paramContext.getSystemService("audio")).isMusicActive();
      return bool;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static String j(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getInstalledApplications(128);
    paramContext = paramContext.getNameForUid(Binder.getCallingUid());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localApplicationInfo.packageName.equals(paramContext)) {
        return localApplicationInfo.sourceDir;
      }
    }
    return "";
  }
  
  private static String k(Context paramContext)
  {
    Object localObject = new Intent("android.intent.action.MAIN");
    ((Intent)localObject).addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager();
    int i = 0;
    localObject = paramContext.resolveActivity((Intent)localObject, 0);
    if (localObject == null) {
      return null;
    }
    paramContext = paramContext.queryContentProviders(((ResolveInfo)localObject).activityInfo.packageName, ((ResolveInfo)localObject).activityInfo.applicationInfo.uid, 8);
    if (paramContext != null) {
      while (i < paramContext.size())
      {
        localObject = (ProviderInfo)paramContext.get(i);
        if ((((ProviderInfo)localObject).readPermission != null) && (Pattern.matches(".*launcher.*READ_SETTINGS", ((ProviderInfo)localObject).readPermission))) {
          return ((ProviderInfo)localObject).authority;
        }
        i += 1;
      }
    }
    return null;
  }
}
