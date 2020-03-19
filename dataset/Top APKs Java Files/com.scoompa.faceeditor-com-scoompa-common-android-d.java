package com.scoompa.common.android;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Environment;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.scoompa.common.g;
import com.scoompa.common.p;
import com.scoompa.common.r;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class d
{
  private static Boolean a = null;
  
  private static long a(File paramFile)
  {
    paramFile = new StatFs(paramFile.getAbsolutePath());
    long l = paramFile.getAvailableBlocks();
    return paramFile.getBlockSize() * l;
  }
  
  public static Intent a(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    if (paramBoolean) {
      paramString.putExtra("force_fullscreen", true);
    }
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramString, 0);
    int i = 0;
    while (i < paramContext.size())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.get(i);
      if (localResolveInfo.activityInfo.name.contains("youtube"))
      {
        paramString.setClassName(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name);
        return paramString;
      }
      i += 1;
    }
    return paramString;
  }
  
  public static Intent a(Context paramContext, ArrayList<Uri> paramArrayList, String paramString)
  {
    return a(paramContext, paramArrayList, "video/*", paramString);
  }
  
  public static Intent a(Context paramContext, ArrayList<Uri> paramArrayList, String paramString1, String paramString2)
  {
    if (paramArrayList.size() == 1)
    {
      paramContext = new Intent("android.intent.action.SEND");
      paramContext.putExtra("android.intent.extra.STREAM", (Parcelable)paramArrayList.get(0));
    }
    for (;;)
    {
      paramContext.setType(paramString1);
      paramContext.putExtra("android.intent.extra.TEXT", paramString2);
      return paramContext;
      paramContext = new Intent("android.intent.action.SEND_MULTIPLE");
      paramContext.putParcelableArrayListExtra("android.intent.extra.STREAM", paramArrayList);
    }
  }
  
  public static String a()
  {
    return "Heap [" + p.a(Debug.getNativeHeapAllocatedSize()) + "/" + p.a(Debug.getNativeHeapSize()) + "/" + p.a(Debug.getNativeHeapFreeSize()) + "]";
  }
  
  public static String a(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager();
    for (;;)
    {
      try
      {
        PackageInfo localPackageInfo = ((PackageManager)localObject).getPackageInfo(j(paramContext), 0);
        paramContext = localPackageInfo.packageName;
        int i = paramContext.indexOf("com.scoompa.");
        if (i >= 0)
        {
          paramContext = paramContext.substring(0, i) + paramContext.substring("com.scoompa.".length() + i);
          String str = "http://" + "prod.";
          localObject = str;
          if (!p.c(paramString)) {
            localObject = str + paramString + ".";
          }
          paramContext = (String)localObject + localPackageInfo.versionCode + "." + paramContext + ".scoompa.com";
          return paramContext;
        }
      }
      catch (Throwable paramContext)
      {
        return null;
      }
    }
  }
  
  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    File localFile = null;
    String str = g.f(paramString1).toLowerCase(Locale.US);
    if (str.equals("mp4")) {
      localFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
    }
    while (localFile == null)
    {
      throw new IOException("No storage available or unknown file extension: " + str);
      if (Arrays.asList(new String[] { "png", "jpg", "gif", "bmp", "webp" }).contains(str)) {
        localFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
      } else if (Arrays.asList(new String[] { "mp3", "m4a", "3gp", "aac", "flac", "mid", "mkv", "wav" }).contains(str)) {
        localFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
      }
    }
    paramContext = g.a(localFile.getAbsolutePath(), new String[] { d(paramContext) });
    new File(paramContext).mkdirs();
    paramContext = g.a(paramContext, new String[] { g.d(paramString2) });
    bl.e("AndroidUtil", String.format("Copying %s to %s and starting media scanner.", new Object[] { paramString1, paramContext }));
    g.b(paramString1, paramContext);
    return paramContext;
  }
  
  public static String a(String paramString)
  {
    return "market://details?id=" + paramString;
  }
  
  public static void a(Activity paramActivity)
  {
    switch (paramActivity.getResources().getConfiguration().orientation)
    {
    default: 
      return;
    case 1: 
      paramActivity.setRequestedOrientation(1);
      return;
    }
    paramActivity.setRequestedOrientation(0);
  }
  
  public static void a(Context paramContext, int paramInt1, int paramInt2)
  {
    a(paramContext, paramInt1, paramInt2, null);
  }
  
  public static void a(Context paramContext, int paramInt1, int paramInt2, DialogInterface.OnClickListener paramOnClickListener)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setTitle(paramInt1);
    paramContext.setMessage(paramInt2);
    paramContext.setPositiveButton(17039370, paramOnClickListener);
    paramContext.show();
  }
  
  public static void a(Context paramContext, final View paramView)
  {
    paramContext = (InputMethodManager)paramContext.getSystemService("input_method");
    paramContext.showSoftInput(paramView, 1);
    paramView.postDelayed(new Runnable()
    {
      public void run()
      {
        d.this.showSoftInput(paramView, 1);
      }
    }, 50L);
  }
  
  public static void a(Context paramContext, String paramString, int paramInt)
  {
    paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramInt != 0) {
      paramString.addFlags(paramInt);
    }
    paramContext.startActivity(paramString);
  }
  
  public static void a(Context paramContext, String paramString, long paramLong1, long paramLong2)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    paramContext = PendingIntent.getBroadcast(paramContext, 0, new Intent(paramString), 268435456);
    bl.b("AndroidUtil", "Scheduling/Replacing repeating alarm: " + paramString + " first time in: " + r.b(Locale.US, paramLong1) + " repeating every: " + r.b(Locale.US, paramLong2));
    if (Build.VERSION.SDK_INT < 19) {}
    localAlarmManager.setInexactRepeating(0, System.currentTimeMillis() + paramLong1, paramLong2, paramContext);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Uri paramUri, String paramString3, String paramString4)
  {
    paramString4 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", paramString4, null));
    paramString4.putExtra("android.intent.extra.SUBJECT", paramString1);
    paramString4.putExtra("android.intent.extra.TEXT", paramString2);
    if (paramUri != null) {
      paramString4.putExtra("android.intent.extra.STREAM", paramUri);
    }
    paramContext.startActivity(Intent.createChooser(paramString4, paramString3));
  }
  
  public static boolean a(Context paramContext)
  {
    if (a == null) {
      a = Boolean.valueOf(y(paramContext));
    }
    return a.booleanValue();
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 131072).size() > 0;
  }
  
  public static int[] a(Context paramContext, int paramInt)
  {
    paramContext = b(paramContext);
    int i = paramContext.x;
    int j = Math.round(paramContext.y / paramInt);
    return new int[] { Math.round(i / paramInt), j };
  }
  
  public static int b(Context paramContext, int paramInt)
  {
    int i = b(paramContext).x;
    return i / Math.round(i / paramInt);
  }
  
  public static long b()
  {
    if (!Environment.getExternalStorageState().equals("mounted")) {
      return 0L;
    }
    return a(Environment.getExternalStorageDirectory());
  }
  
  public static Intent b(Context paramContext, ArrayList<Uri> paramArrayList, String paramString)
  {
    return a(paramContext, paramArrayList, "image/png", paramString);
  }
  
  public static Point b(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    if (Build.VERSION.SDK_INT >= 13)
    {
      paramContext.getSize(localPoint);
      return localPoint;
    }
    localPoint.set(paramContext.getWidth(), paramContext.getWidth());
    return localPoint;
  }
  
  public static void b(Activity paramActivity)
  {
    paramActivity.setRequestedOrientation(2);
  }
  
  public static void b(Context paramContext, View paramView)
  {
    bl.b("Hiding keyboard");
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    String str = paramString2;
    if (paramString2 == null) {
      str = "";
    }
    paramString1 = Uri.parse("market://details?id=" + paramString1 + str);
    bl.b("AndroidUtil", "launchMarket: " + paramString1.toString());
    paramString1 = new Intent("android.intent.action.VIEW", paramString1);
    if (paramString1.resolveActivity(paramContext.getPackageManager()) != null)
    {
      paramContext.startActivity(paramString1);
      return;
    }
    bl.b("AndroidUtil", "No Intent available to handle action");
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 1);
      return paramContext != null;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  public static long c()
  {
    return a(Environment.getDataDirectory());
  }
  
  public static void c(Context paramContext, int paramInt)
  {
    Toast.makeText(paramContext, paramInt, 1).show();
  }
  
  public static void c(Context paramContext, String paramString)
  {
    b(paramContext, paramString, null);
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setTitle(paramString1);
    paramContext.setMessage(paramString2);
    paramContext.setPositiveButton(17039370, null);
    paramContext.show();
  }
  
  @SuppressLint({"NewApi"})
  public static boolean c(Activity paramActivity)
  {
    if ((paramActivity == null) || (paramActivity.isFinishing())) {}
    while ((Build.VERSION.SDK_INT >= 17) && (paramActivity.isDestroyed())) {
      return true;
    }
    return false;
  }
  
  public static boolean c(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.camera");
  }
  
  public static float d()
  {
    if (Build.VERSION.SDK_INT < 11) {}
    int i;
    do
    {
      return 0.5F;
      i = (int)(Runtime.getRuntime().maxMemory() / 1024L / 1024L);
      if (i >= 512) {
        return 1.0F;
      }
    } while (i < 128);
    return 0.75F;
  }
  
  public static ViewGroup d(Activity paramActivity)
  {
    ViewGroup localViewGroup1 = (ViewGroup)paramActivity.getWindow().getDecorView().getRootView();
    if ((Build.VERSION.SDK_INT < 21) && ((localViewGroup1 instanceof FrameLayout))) {}
    do
    {
      return localViewGroup1;
      paramActivity = new ArrayList();
      paramActivity.add(localViewGroup1);
      int i = 0;
      while (i < 3)
      {
        ArrayList localArrayList = new ArrayList();
        paramActivity = paramActivity.iterator();
        while (paramActivity.hasNext())
        {
          ViewGroup localViewGroup2 = (ViewGroup)paramActivity.next();
          int j = 0;
          while (j < localViewGroup2.getChildCount())
          {
            View localView = localViewGroup2.getChildAt(j);
            if ((localView instanceof ViewGroup))
            {
              if (((localView instanceof FrameLayout)) && (localView.getVisibility() == 0) && (localView.getWidth() > 200) && (localView.getHeight() > 200)) {
                return (ViewGroup)localView;
              }
              localArrayList.add((ViewGroup)localView);
            }
            j += 1;
          }
        }
        i += 1;
        paramActivity = localArrayList;
      }
    } while ((localViewGroup1 instanceof FrameLayout));
    return null;
  }
  
  public static final String d(Context paramContext)
  {
    Resources localResources = paramContext.getResources();
    int i = localResources.getIdentifier("app_name", "string", paramContext.getPackageName());
    if (i != 0) {}
    for (boolean bool = true;; bool = false)
    {
      bl.a(bool, "saveToGallery requires your app to contain a string resource named 'app_name' which it uses as a picutres/videos subdirectory name");
      return localResources.getText(i).toString();
    }
  }
  
  public static boolean d(Context paramContext, int paramInt)
  {
    return t(paramContext) < paramInt;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
      if (paramContext != null)
      {
        int j = paramContext.length;
        int i = 0;
        while (i < j)
        {
          boolean bool = paramContext[i].equals(paramString);
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Throwable paramContext)
    {
      bl.a(false);
      bl.b("AndroidUtil", "no permission: " + paramString);
    }
  }
  
  public static String e(Context paramContext)
  {
    paramContext = f(paramContext);
    if (paramContext != null) {
      return paramContext[0];
    }
    return null;
  }
  
  public static boolean e()
  {
    return Looper.myLooper() == Looper.getMainLooper();
  }
  
  public static long f()
  {
    Runtime localRuntime = Runtime.getRuntime();
    long l1 = localRuntime.maxMemory();
    long l2 = localRuntime.totalMemory();
    return localRuntime.freeMemory() + (l1 - l2);
  }
  
  public static String[] f(Context paramContext)
  {
    Account[] arrayOfAccount = g(paramContext);
    int j = arrayOfAccount.length;
    if (j > 0)
    {
      String[] arrayOfString = new String[j];
      int i = 0;
      for (;;)
      {
        paramContext = arrayOfString;
        if (i >= j) {
          break;
        }
        arrayOfString[i] = arrayOfAccount[i].name;
        i += 1;
      }
    }
    paramContext = null;
    return paramContext;
  }
  
  public static Account[] g(Context paramContext)
  {
    return AccountManager.get(paramContext).getAccountsByType("com.google");
  }
  
  public static String h(Context paramContext)
  {
    return SimpleDateFormat.getInstance().format(new Date(i(paramContext)));
  }
  
  /* Error */
  public static long i(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 757	java/util/zip/ZipFile
    //   8: dup
    //   9: aload_0
    //   10: invokevirtual 59	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: aload_0
    //   14: invokevirtual 666	android/content/Context:getPackageName	()Ljava/lang/String;
    //   17: iconst_0
    //   18: invokevirtual 761	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   21: getfield 766	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   24: invokespecial 767	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   27: astore_0
    //   28: aload_0
    //   29: ldc_w 769
    //   32: invokevirtual 773	java/util/zip/ZipFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   35: invokevirtual 778	java/util/zip/ZipEntry:getTime	()J
    //   38: lstore_1
    //   39: aload_0
    //   40: ifnull +7 -> 47
    //   43: aload_0
    //   44: invokevirtual 781	java/util/zip/ZipFile:close	()V
    //   47: lload_1
    //   48: lreturn
    //   49: astore_0
    //   50: ldc_w 313
    //   53: new 140	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   60: ldc_w 783
    //   63: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: aload_0
    //   67: invokevirtual 786	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: invokestatic 437	com/scoompa/common/android/bl:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   76: lload_1
    //   77: lreturn
    //   78: astore_3
    //   79: aload 4
    //   81: astore_0
    //   82: aload_3
    //   83: astore 4
    //   85: aload_0
    //   86: astore_3
    //   87: ldc_w 313
    //   90: ldc_w 788
    //   93: aload 4
    //   95: invokestatic 791	com/scoompa/common/android/bl:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   98: aload_0
    //   99: ifnull +7 -> 106
    //   102: aload_0
    //   103: invokevirtual 781	java/util/zip/ZipFile:close	()V
    //   106: lconst_0
    //   107: lreturn
    //   108: astore_0
    //   109: ldc_w 313
    //   112: new 140	java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   119: ldc_w 783
    //   122: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload_0
    //   126: invokevirtual 786	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   129: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: invokestatic 437	com/scoompa/common/android/bl:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   135: goto -29 -> 106
    //   138: astore_0
    //   139: aload_3
    //   140: ifnull +7 -> 147
    //   143: aload_3
    //   144: invokevirtual 781	java/util/zip/ZipFile:close	()V
    //   147: aload_0
    //   148: athrow
    //   149: astore_3
    //   150: ldc_w 313
    //   153: new 140	java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   160: ldc_w 783
    //   163: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: aload_3
    //   167: invokevirtual 786	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: invokestatic 437	com/scoompa/common/android/bl:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   176: goto -29 -> 147
    //   179: astore 4
    //   181: aload_0
    //   182: astore_3
    //   183: aload 4
    //   185: astore_0
    //   186: goto -47 -> 139
    //   189: astore 4
    //   191: goto -106 -> 85
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	194	0	paramContext	Context
    //   38	39	1	l	long
    //   1	1	3	localObject1	Object
    //   78	5	3	localException1	Exception
    //   86	58	3	localContext1	Context
    //   149	18	3	localIOException	IOException
    //   182	1	3	localContext2	Context
    //   3	91	4	localException2	Exception
    //   179	5	4	localObject2	Object
    //   189	1	4	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   43	47	49	java/io/IOException
    //   5	28	78	java/lang/Exception
    //   102	106	108	java/io/IOException
    //   5	28	138	finally
    //   87	98	138	finally
    //   143	147	149	java/io/IOException
    //   28	39	179	finally
    //   28	39	189	java/lang/Exception
  }
  
  public static String j(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static String k(Context paramContext)
  {
    paramContext = paramContext.getPackageName();
    return paramContext.substring(paramContext.lastIndexOf('.') + 1);
  }
  
  @Deprecated
  public static Set<String> l(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
      paramContext = new ArrayList(((List)localObject).size());
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramContext.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
      }
      paramContext = new HashSet(paramContext);
    }
    catch (Throwable paramContext)
    {
      return new HashSet();
    }
    return paramContext;
  }
  
  public static Intent m(Context paramContext)
  {
    Intent localIntent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent2.addCategory("android.intent.category.DEFAULT");
    localIntent2.setData(Uri.parse("package:" + paramContext.getPackageName()));
    Intent localIntent1 = localIntent2;
    if (!a(paramContext, localIntent2)) {
      localIntent1 = new Intent("android.settings.SETTINGS");
    }
    localIntent1.addFlags(268435456);
    return localIntent1;
  }
  
  public static int n(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(j(paramContext), 0).versionCode;
      return i;
    }
    catch (Throwable paramContext) {}
    return 0;
  }
  
  public static String o(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(j(paramContext), 0).versionName;
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static boolean p(Context paramContext)
  {
    return paramContext.getExternalFilesDir(null) != null;
  }
  
  public static String q(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("App version: ").append(n(paramContext));
    localStringBuilder.append("\nLog Level:2");
    localStringBuilder.append("\nAndroid Version: ").append(Build.VERSION.SDK_INT);
    localStringBuilder.append("\nBoard: ").append(Build.BOARD);
    localStringBuilder.append("\nDevice: ").append(Build.DEVICE);
    localStringBuilder.append("\nDisplay: ").append(Build.DISPLAY);
    localStringBuilder.append("\nManufacturer: ").append(Build.MANUFACTURER);
    localStringBuilder.append("\nModel: ").append(Build.MODEL);
    try
    {
      localStringBuilder.append("\nFree internal space: " + p.a(c()) + " external: " + p.a(b()));
      localStringBuilder.append("\n" + a());
      return localStringBuilder.toString();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        bl.b("AndroidUtil", "error creating attribute string: ", paramContext);
      }
    }
  }
  
  public static String r(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static String s(Context paramContext)
  {
    return "https://play.google.com/store/apps/details?id=" + j(paramContext);
  }
  
  @SuppressLint({"NewApi"})
  public static int t(Context paramContext)
  {
    try
    {
      str3 = e(paramContext);
      str1 = str3;
      if (str3 == null)
      {
        str3 = r(paramContext);
        str1 = str3;
        if (str3 == null)
        {
          if (Build.VERSION.SDK_INT >= 9) {
            str3 = Build.SERIAL;
          }
          str1 = str3;
          if (str3 == null) {
            str1 = bj.a(paramContext);
          }
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        String str1;
        String str3 = r(paramContext);
        String str2 = str3;
        if (str3 == null)
        {
          if (Build.VERSION.SDK_INT >= 9) {
            str3 = Build.SERIAL;
          }
          str2 = str3;
          if (str3 == null) {
            str2 = q(paramContext);
          }
        }
      }
    }
    return Math.abs(str1.hashCode()) % 100;
  }
  
  public static File u(Context paramContext)
  {
    boolean bool = d(paramContext, "android.permission.WRITE_EXTERNAL_STORAGE");
    if (Build.VERSION.SDK_INT <= 7) {
      paramContext = paramContext.getCacheDir();
    }
    while (paramContext == null)
    {
      throw new IOException("cache dir not available");
      if (bool) {
        paramContext = paramContext.getExternalCacheDir();
      } else {
        paramContext = paramContext.getCacheDir();
      }
    }
    if (!paramContext.exists())
    {
      bl.a(paramContext.isDirectory());
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  public static boolean v(Context paramContext)
  {
    int i = paramContext.getResources().getConfiguration().screenLayout & 0xF;
    return (i == 4) || (i == 3);
  }
  
  public static String w(Context paramContext)
  {
    int i = Process.myPid();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
      do
      {
        if (!paramContext.hasNext()) {
          break;
        }
        localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      } while (localRunningAppProcessInfo.pid != i);
      return localRunningAppProcessInfo.processName;
    }
    av.a().a("Can't read running app processes");
    av.a().a("Can't find current process id in running app processes");
    return null;
  }
  
  public static boolean x(Context paramContext)
  {
    paramContext = w(paramContext);
    if (paramContext != null) {
      return paramContext.contains(":");
    }
    return false;
  }
  
  private static boolean y(Context paramContext)
  {
    boolean bool2 = true;
    try
    {
      if (Build.VERSION.SDK_INT >= 10) {}
      for (bool1 = true;; bool1 = false)
      {
        bl.a(bool1);
        int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
        if (i != 0) {
          break;
        }
        return true;
      }
      return false;
    }
    catch (Throwable paramContext)
    {
      bl.b("AndroidUtil", "Exception while checking for GPS availability: ", paramContext);
      if ((paramContext instanceof IllegalStateException)) {}
    }
    for (boolean bool1 = bool2;; bool1 = false)
    {
      bl.a(bool1, "add google play service to manifest.");
      return false;
    }
  }
}
