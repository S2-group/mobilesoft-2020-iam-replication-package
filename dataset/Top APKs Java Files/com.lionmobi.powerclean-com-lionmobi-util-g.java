package com.lionmobi.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.widget.Toast;
import com.b.a.b.d;
import com.b.a.b.e;
import com.lionmobi.powerclean.ApplicationEx;
import com.lionmobi.powerclean.c.c;
import com.lionmobi.powerclean.model.bean.y;
import com.lionmobi.powerclean.model.bean.z;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

public final class g
{
  public long a = 0L;
  public long b = 0L;
  public long c = 0L;
  public long d = 0L;
  long e = 0L;
  long f = 0L;
  long g = 0L;
  long h = 0L;
  long i = 0L;
  long j = 0L;
  long k = 0L;
  long l = 0L;
  PackageManager m;
  Drawable n;
  List o = new ArrayList();
  Context p;
  List q;
  List r = new ArrayList();
  private ActivityManager s;
  private int t = 0;
  private double u = 1.073741824D;
  
  public g(Context paramContext)
  {
    this.p = paramContext;
    this.m = paramContext.getPackageManager();
    this.n = paramContext.getResources().getDrawable(17301651);
    this.q = c.getIntentList();
  }
  
  private static String a(Context paramContext, long paramLong)
  {
    if (paramContext == null) {
      return "";
    }
    float f2 = (float)paramLong;
    int i1 = 17039530;
    float f1 = f2;
    if (f2 > 900.0F)
    {
      i1 = 2131165941;
      f1 = f2 / 1024.0F;
    }
    f2 = f1;
    if (f1 > 900.0F)
    {
      i1 = 2131165969;
      f2 = f1 / 1024.0F;
    }
    if (f2 > 900.0F)
    {
      f1 = f2 / 1024.0F;
      i1 = 2131165938;
    }
    for (;;)
    {
      String str;
      if (f1 < 1.0F) {
        str = String.format("%.2f", new Object[] { Float.valueOf(f1) });
      }
      for (;;)
      {
        return str + paramContext.getString(i1);
        if (f1 < 10.0F) {
          str = String.format("%.1f", new Object[] { Float.valueOf(f1) });
        } else {
          str = String.format("%.0f", new Object[] { Float.valueOf(f1) });
        }
      }
      f1 = f2;
    }
  }
  
  private void a(File paramFile)
  {
    int i1 = 0;
    if ((paramFile != null) && (paramFile.exists()))
    {
      this.t += 1;
      if (this.t > 10) {
        this.t = 0;
      }
    }
    try
    {
      Thread.sleep(30L);
      if (paramFile.isFile())
      {
        paramFile.delete();
        paramFile.delete();
        return;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
        continue;
        try
        {
          File[] arrayOfFile = paramFile.listFiles();
          if ((arrayOfFile == null) || (arrayOfFile.length <= 0)) {
            continue;
          }
          while (i1 < arrayOfFile.length)
          {
            a(arrayOfFile[i1]);
            i1 += 1;
          }
          return;
        }
        catch (OutOfMemoryError paramFile) {}
      }
    }
  }
  
  public static Bitmap drawable2Bitmap(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    if ((paramDrawable instanceof NinePatchDrawable))
    {
      int i1 = paramDrawable.getIntrinsicWidth();
      int i2 = paramDrawable.getIntrinsicHeight();
      if (paramDrawable.getOpacity() != -1) {}
      for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565)
      {
        localObject = Bitmap.createBitmap(i1, i2, (Bitmap.Config)localObject);
        Canvas localCanvas = new Canvas((Bitmap)localObject);
        paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
        paramDrawable.draw(localCanvas);
        return localObject;
      }
    }
    return null;
  }
  
  public static void forceStopApp(Activity paramActivity, String paramString, boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setFlags(536936448);
    int i1 = Build.VERSION.SDK_INT;
    if (i1 >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
      paramActivity.startActivityForResult(localIntent, 1024);
      paramActivity.overridePendingTransition(0, 0);
      if (paramBoolean) {
        ApplicationEx.startFloatWindow(1);
      }
      return;
    }
    if (i1 == 8) {}
    for (String str = "pkg";; str = "com.android.settings.ApplicationPkgName")
    {
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      localIntent.putExtra(str, paramString);
      break;
    }
  }
  
  public static String formatFileSize(Context paramContext, long paramLong)
  {
    double d1 = paramLong / 1024.0D / 1024.0D;
    if (d1 <= 128.0D) {
      return Formatter.formatFileSize(paramContext, paramLong);
    }
    if (d1 < 256.0D) {
      return "256MB";
    }
    if (d1 < 512.0D) {
      return "512MB";
    }
    d1 /= 1024.0D;
    if (d1 < 1.0D) {
      return "1GB";
    }
    if (d1 < 2.0D) {
      return "2GB";
    }
    if (d1 < 4.0D) {
      return "4GB";
    }
    if (d1 < 8.0D) {
      return "8GB";
    }
    if (d1 < 16.0D) {
      return "16GB";
    }
    if (d1 < 32.0D) {
      return "32GB";
    }
    if (d1 < 64.0D) {
      return "64GB";
    }
    if (d1 < 128.0D) {
      return "128GB";
    }
    return a(paramContext, paramLong);
  }
  
  public static Bitmap getAppIcon(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = drawable2Bitmap(paramContext.getApplicationInfo(paramString, 0).loadIcon(paramContext));
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getCurrentPkg(Context paramContext)
  {
    try
    {
      if (Build.VERSION.SDK_INT > 20) {
        return ((ActivityManager.RunningAppProcessInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().get(0)).processName;
      }
      paramContext = ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static long getMemorySizebyPid(Context paramContext, int paramInt)
  {
    return ((ActivityManager)paramContext.getSystemService("activity")).getProcessMemoryInfo(new int[] { paramInt })[0].getTotalPss() * 1024;
  }
  
  public static String getNameByPackage(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = paramContext.getPackageManager();
        paramString = paramContext.getApplicationInfo(paramString, 8192);
        if (paramString != null)
        {
          paramContext = paramString.loadLabel(paramContext).toString();
          return paramContext;
        }
      }
      catch (Exception paramContext) {}
    }
    return null;
  }
  
  public static long getStorageSize(String paramString)
  {
    try
    {
      paramString = new StatFs(paramString);
      long l1 = paramString.getBlockSize();
      int i1 = paramString.getBlockCount();
      return i1 * l1;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0L;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext != null;
    }
    catch (Exception paramContext)
    {
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isShowFB(Context paramContext)
  {
    try
    {
      new g(paramContext);
      boolean bool;
      if ((!isAppInstalled(paramContext, "com.facebook.katana")) && (!isAppInstalled(paramContext, "com.facebook.lite"))) {
        bool = isAppInstalled(paramContext, "com.instagram.android");
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return true;
  }
  
  public static boolean isThisASystemPackage(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    boolean bool2 = false;
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo("android", 64);
      boolean bool1 = bool2;
      if (paramPackageInfo != null)
      {
        bool1 = bool2;
        if (paramPackageInfo.signatures != null)
        {
          boolean bool3 = paramPackageManager.signatures[0].equals(paramPackageInfo.signatures[0]);
          bool1 = bool2;
          if (bool3) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    catch (Exception paramPackageManager) {}
    return false;
  }
  
  public static void setPacakgeSize(String paramString, y paramY, PackageManager paramPackageManager)
  {
    if (paramString != null) {
      try
      {
        if (Build.VERSION.SDK_INT >= 17)
        {
          paramPackageManager.getClass().getDeclaredMethod("getPackageSizeInfo", new Class[] { String.class, Integer.TYPE, IPackageStatsObserver.class }).invoke(paramPackageManager, new Object[] { paramString, Integer.valueOf(Process.myUid() / 100000), new bf(paramY) });
          return;
        }
        paramPackageManager.getClass().getDeclaredMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class }).invoke(paramPackageManager, new Object[] { paramString, new bf(paramY) });
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public final boolean CheckStorageaccess(String paramString)
  {
    try
    {
      paramString = new File(new File(paramString), "liont.txt");
      if (!paramString.exists()) {
        paramString.createNewFile();
      }
      paramString.delete();
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public final void addHistoryCleanSize(long paramLong)
  {
    Object localObject = this.p.getSharedPreferences("junk_clean", 0);
    long l1 = getHistoryCleanSize();
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putLong("history_clean_size", l1 + paramLong);
    ((SharedPreferences.Editor)localObject).commit();
  }
  
  public final String backupApp(String paramString)
  {
    return backupApp(paramString, "lionbackup");
  }
  
  /* Error */
  protected final String backupApp(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: new 142	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   13: invokestatic 588	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   16: invokevirtual 591	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   19: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: getstatic 594	java/io/File:separator	Ljava/lang/String;
    //   25: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: aload_2
    //   29: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: getstatic 594	java/io/File:separator	Ljava/lang/String;
    //   35: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: astore_2
    //   42: aload_0
    //   43: getfield 87	com/lionmobi/util/g:p	Landroid/content/Context;
    //   46: invokevirtual 93	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   49: astore 6
    //   51: aload 6
    //   53: aload_1
    //   54: iconst_0
    //   55: invokevirtual 367	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   58: astore 8
    //   60: aload 8
    //   62: getfield 597	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   65: astore 7
    //   67: new 142	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   74: aload_2
    //   75: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: aload 8
    //   80: aload 6
    //   82: invokevirtual 441	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   85: invokevirtual 600	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   88: ldc_w 602
    //   91: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: aload 6
    //   96: aload_1
    //   97: iconst_0
    //   98: invokevirtual 464	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   101: getfield 605	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   104: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: ldc_w 607
    //   110: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: astore 6
    //   118: new 167	java/io/File
    //   121: dup
    //   122: aload 7
    //   124: invokespecial 537	java/io/File:<init>	(Ljava/lang/String;)V
    //   127: astore_1
    //   128: new 167	java/io/File
    //   131: dup
    //   132: aload_2
    //   133: invokespecial 537	java/io/File:<init>	(Ljava/lang/String;)V
    //   136: astore_2
    //   137: aload_2
    //   138: invokevirtual 171	java/io/File:exists	()Z
    //   141: ifne +8 -> 149
    //   144: aload_2
    //   145: invokevirtual 610	java/io/File:mkdirs	()Z
    //   148: pop
    //   149: new 167	java/io/File
    //   152: dup
    //   153: aload 6
    //   155: invokespecial 537	java/io/File:<init>	(Ljava/lang/String;)V
    //   158: astore_2
    //   159: aload_2
    //   160: invokevirtual 171	java/io/File:exists	()Z
    //   163: ifne +98 -> 261
    //   166: aload_2
    //   167: invokevirtual 545	java/io/File:createNewFile	()Z
    //   170: pop
    //   171: new 612	java/io/BufferedInputStream
    //   174: dup
    //   175: new 614	java/io/FileInputStream
    //   178: dup
    //   179: aload_1
    //   180: invokespecial 616	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   183: invokespecial 619	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   186: astore_1
    //   187: new 621	java/io/BufferedOutputStream
    //   190: dup
    //   191: new 623	java/io/FileOutputStream
    //   194: dup
    //   195: aload_2
    //   196: invokespecial 624	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   199: invokespecial 627	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   202: astore_2
    //   203: ldc_w 628
    //   206: newarray byte
    //   208: astore 4
    //   210: aload_1
    //   211: aload 4
    //   213: invokevirtual 632	java/io/BufferedInputStream:read	([B)I
    //   216: istore_3
    //   217: iload_3
    //   218: ifle +65 -> 283
    //   221: aload_2
    //   222: aload 4
    //   224: iconst_0
    //   225: iload_3
    //   226: invokevirtual 636	java/io/BufferedOutputStream:write	([BII)V
    //   229: goto -19 -> 210
    //   232: astore 4
    //   234: aload_1
    //   235: ifnull +7 -> 242
    //   238: aload_1
    //   239: invokevirtual 639	java/io/BufferedInputStream:close	()V
    //   242: aload_2
    //   243: ifnull +7 -> 250
    //   246: aload_2
    //   247: invokevirtual 640	java/io/BufferedOutputStream:close	()V
    //   250: ldc 120
    //   252: areturn
    //   253: astore_1
    //   254: aload_1
    //   255: invokevirtual 458	java/lang/Exception:printStackTrace	()V
    //   258: ldc 120
    //   260: areturn
    //   261: aload_2
    //   262: invokevirtual 185	java/io/File:delete	()Z
    //   265: pop
    //   266: aload_2
    //   267: invokevirtual 545	java/io/File:createNewFile	()Z
    //   270: pop
    //   271: goto -100 -> 171
    //   274: astore_1
    //   275: aconst_null
    //   276: astore_1
    //   277: aload 5
    //   279: astore_2
    //   280: goto -46 -> 234
    //   283: aload_2
    //   284: invokevirtual 643	java/io/BufferedOutputStream:flush	()V
    //   287: aload_1
    //   288: invokevirtual 639	java/io/BufferedInputStream:close	()V
    //   291: aload_2
    //   292: invokevirtual 640	java/io/BufferedOutputStream:close	()V
    //   295: aload 6
    //   297: areturn
    //   298: astore_1
    //   299: aload_1
    //   300: invokevirtual 644	java/io/IOException:printStackTrace	()V
    //   303: aload 6
    //   305: areturn
    //   306: astore_1
    //   307: aload_1
    //   308: invokevirtual 644	java/io/IOException:printStackTrace	()V
    //   311: ldc 120
    //   313: areturn
    //   314: astore_2
    //   315: aconst_null
    //   316: astore_1
    //   317: aload_1
    //   318: ifnull +7 -> 325
    //   321: aload_1
    //   322: invokevirtual 639	java/io/BufferedInputStream:close	()V
    //   325: aload 4
    //   327: ifnull +8 -> 335
    //   330: aload 4
    //   332: invokevirtual 640	java/io/BufferedOutputStream:close	()V
    //   335: aload_2
    //   336: athrow
    //   337: astore_1
    //   338: aload_1
    //   339: invokevirtual 644	java/io/IOException:printStackTrace	()V
    //   342: goto -7 -> 335
    //   345: astore_2
    //   346: goto -29 -> 317
    //   349: astore 5
    //   351: aload_2
    //   352: astore 4
    //   354: aload 5
    //   356: astore_2
    //   357: goto -40 -> 317
    //   360: astore_2
    //   361: aload 5
    //   363: astore_2
    //   364: goto -130 -> 234
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	367	0	this	g
    //   0	367	1	paramString1	String
    //   0	367	2	paramString2	String
    //   216	10	3	i1	int
    //   1	222	4	arrayOfByte	byte[]
    //   232	99	4	localIOException	IOException
    //   352	1	4	str1	String
    //   4	274	5	localObject1	Object
    //   349	13	5	localObject2	Object
    //   49	255	6	localObject3	Object
    //   65	58	7	str2	String
    //   58	21	8	localApplicationInfo	ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   203	210	232	java/io/IOException
    //   210	217	232	java/io/IOException
    //   221	229	232	java/io/IOException
    //   283	287	232	java/io/IOException
    //   42	118	253	java/lang/Exception
    //   118	149	274	java/io/IOException
    //   149	171	274	java/io/IOException
    //   171	187	274	java/io/IOException
    //   261	271	274	java/io/IOException
    //   287	295	298	java/io/IOException
    //   238	242	306	java/io/IOException
    //   246	250	306	java/io/IOException
    //   118	149	314	finally
    //   149	171	314	finally
    //   171	187	314	finally
    //   261	271	314	finally
    //   321	325	337	java/io/IOException
    //   330	335	337	java/io/IOException
    //   187	203	345	finally
    //   203	210	349	finally
    //   210	217	349	finally
    //   221	229	349	finally
    //   283	287	349	finally
    //   187	203	360	java/io/IOException
  }
  
  public final boolean checkIfRootDevice()
  {
    for (;;)
    {
      try
      {
        if (!ApplicationEx.f)
        {
          com.b.a.a.a = false;
          ApplicationEx.f = true;
          final Object localObject1 = new Object();
          new Thread(new Runnable()
          {
            public final void run()
            {
              ApplicationEx.g = com.b.a.a.isRootAvailable();
              synchronized (localObject1)
              {
                localObject1.notifyAll();
                return;
              }
            }
          }).start();
          try
          {
            localObject1.wait(8000L);
            bool = ApplicationEx.g;
            return bool;
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
            continue;
          }
          finally {}
        }
        boolean bool = ApplicationEx.g;
      }
      finally {}
    }
  }
  
  public final void cleanAllDownloadFiles()
  {
    try
    {
      Iterator localIterator = o.e.iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          Object localObject2 = new File((String)localIterator.next());
          boolean bool = ((File)localObject2).exists();
          if (!bool) {
            continue;
          }
          try
          {
            localObject2 = ((File)localObject2).listFiles();
            if ((localObject2 != null) && (localObject2.length > 0))
            {
              int i2 = localObject2.length;
              int i1 = 0;
              while (i1 < i2)
              {
                File localFile = localObject2[i1];
                if (localFile.exists())
                {
                  this.t = 0;
                  a(localFile);
                }
                i1 += 1;
              }
            }
          }
          catch (OutOfMemoryError localOutOfMemoryError) {}
        }
      }
      return;
    }
    finally {}
  }
  
  public final boolean cleanApk(String paramString)
  {
    boolean bool = false;
    paramString = new File(paramString);
    if (paramString.exists()) {
      bool = paramString.delete();
    }
    return bool;
  }
  
  public final void cleanLatestCheckUpdateDate()
  {
    SharedPreferences.Editor localEditor = this.p.getSharedPreferences("lion_check_update", 0).edit();
    localEditor.putString("latest_checkUpdate_date", "1970-01-01");
    localEditor.commit();
  }
  
  public final void deleteRestartServicePro(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = q.getInstance(this.p).getWritableDatabase();
    try
    {
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("delete from lion_servicestats where pkgname=?", new Object[] { paramString });
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return;
    }
    catch (Exception paramString)
    {
      paramString = paramString;
      localSQLiteDatabase.endTransaction();
      return;
    }
    finally
    {
      paramString = finally;
      localSQLiteDatabase.endTransaction();
      throw paramString;
    }
  }
  
  public final boolean exeRootSetAppAutoStart(h paramH, List paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return false;
    }
    if (grantSuperUserByRootTools())
    {
      final StringBuffer localStringBuffer = new StringBuffer();
      long l1 = System.currentTimeMillis();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator1 = paramList.iterator();
      y localY;
      do
      {
        Map localMap;
        Iterator localIterator2;
        while (!localIterator2.hasNext())
        {
          do
          {
            if (!localIterator1.hasNext()) {
              break;
            }
            localY = (y)localIterator1.next();
            localMap = localY.n;
          } while ((localMap == null) || (localMap.size() <= 0));
          localIterator2 = localMap.keySet().iterator();
        }
        paramList = (List)localMap.get((String)localIterator2.next());
      } while ((paramList == null) || (paramList.size() <= 0));
      Iterator localIterator3 = paramList.iterator();
      label162:
      if (localIterator3.hasNext())
      {
        String str = (String)localIterator3.next();
        paramList = str;
        if (!TextUtils.isEmpty(str))
        {
          paramList = str;
          if (str.indexOf("$") != -1) {
            paramList = str.replace("$", "\\$");
          }
        }
        if (!localY.k) {
          break label274;
        }
      }
      label274:
      for (paramList = "pm disable " + localY.c + "/" + paramList;; paramList = "pm enable " + localY.c + "/" + paramList)
      {
        localArrayList.add(paramList);
        break label162;
        break;
      }
      paramList = new d((String[])localArrayList.toArray(new String[localArrayList.size()]), localStringBuffer)
      {
        public final void output(int paramAnonymousInt, String paramAnonymousString)
        {
          localStringBuffer.append(paramAnonymousString + "\n");
        }
      };
      try
      {
        com.b.a.a.getShell(true).add(paramList);
        for (;;)
        {
          SystemClock.sleep(20L);
          if (paramList.isFinished())
          {
            localStringBuffer.append("command is finished.");
            paramH.onCmdFinished(localStringBuffer.toString());
            break;
          }
          long l2 = (System.currentTimeMillis() - l1) / 1000L;
          if (l2 <= 30L) {}
        }
        return false;
      }
      catch (IOException paramH)
      {
        paramH.printStackTrace();
        return false;
      }
      catch (TimeoutException paramH)
      {
        paramH.printStackTrace();
        return false;
      }
      catch (com.b.a.a.a paramH)
      {
        paramH.printStackTrace();
        return false;
      }
      catch (Exception paramH)
      {
        paramH.printStackTrace();
      }
    }
    return true;
  }
  
  public final boolean exeRootSetAppAutoStartforBoost(h paramH, List paramList, String paramString, boolean paramBoolean)
  {
    if ((paramList == null) || (paramList.size() == 0)) {
      return false;
    }
    if (grantSuperUserByRootTools())
    {
      final StringBuffer localStringBuffer = new StringBuffer();
      long l1 = System.currentTimeMillis();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        paramList = str;
        if (!TextUtils.isEmpty(str))
        {
          paramList = str;
          if (str.indexOf("$") != -1) {
            paramList = str.replace("$", "\\$");
          }
        }
        if (paramBoolean) {}
        for (paramList = "pm disable " + paramString + "/" + paramList;; paramList = "pm enable " + paramString + "/" + paramList)
        {
          localArrayList.add(paramList);
          break;
        }
      }
      paramList = new d((String[])localArrayList.toArray(new String[localArrayList.size()]), localStringBuffer)
      {
        public final void output(int paramAnonymousInt, String paramAnonymousString)
        {
          localStringBuffer.append(paramAnonymousString + "\n");
        }
      };
      try
      {
        com.b.a.a.getShell(true).add(paramList);
        for (;;)
        {
          SystemClock.sleep(20L);
          if (paramList.isFinished())
          {
            localStringBuffer.append("command is finished.");
            paramH.onCmdFinished(localStringBuffer.toString());
            break;
          }
          long l2 = (System.currentTimeMillis() - l1) / 1000L;
          if (l2 <= 30L) {}
        }
        return false;
      }
      catch (IOException paramH)
      {
        paramH.printStackTrace();
        return false;
      }
      catch (TimeoutException paramH)
      {
        paramH.printStackTrace();
        return false;
      }
      catch (com.b.a.a.a paramH)
      {
        paramH.printStackTrace();
        return false;
      }
      catch (Exception paramH)
      {
        paramH.printStackTrace();
      }
    }
    return true;
  }
  
  @Deprecated
  public final void forceStopApp(String paramString)
  {
    forceStopApp((Activity)this.p, paramString, true);
  }
  
  public final Drawable getAppIcon(String paramString)
  {
    Object localObject = null;
    if (TextUtils.isEmpty(paramString)) {
      localObject = this.n;
    }
    for (;;)
    {
      return localObject;
      try
      {
        paramString = this.m.getApplicationIcon(paramString);
        localObject = paramString;
        if (paramString != null) {
          continue;
        }
        return this.n;
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          paramString = (String)localObject;
        }
      }
    }
  }
  
  /* Error */
  public final Map getAppMap()
  {
    // Byte code:
    //   0: new 852	java/util/concurrent/ConcurrentHashMap
    //   3: dup
    //   4: invokespecial 853	java/util/concurrent/ConcurrentHashMap:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: getfield 87	com/lionmobi/util/g:p	Landroid/content/Context;
    //   12: invokevirtual 93	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   15: astore_2
    //   16: aload_2
    //   17: iconst_0
    //   18: invokevirtual 856	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   21: invokeinterface 750 1 0
    //   26: astore_3
    //   27: aload_3
    //   28: invokeinterface 685 1 0
    //   33: ifeq +174 -> 207
    //   36: aload_3
    //   37: invokeinterface 689 1 0
    //   42: checkcast 482	android/content/pm/PackageInfo
    //   45: astore 4
    //   47: new 752	com/lionmobi/powerclean/model/bean/y
    //   50: dup
    //   51: invokespecial 857	com/lionmobi/powerclean/model/bean/y:<init>	()V
    //   54: astore 5
    //   56: aload 5
    //   58: aload 4
    //   60: getfield 861	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   63: aload_2
    //   64: invokevirtual 441	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   67: invokeinterface 444 1 0
    //   72: invokevirtual 864	com/lionmobi/powerclean/model/bean/y:setName	(Ljava/lang/String;)V
    //   75: aload 5
    //   77: aload 4
    //   79: getfield 867	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   82: invokevirtual 870	com/lionmobi/powerclean/model/bean/y:setPkgName	(Ljava/lang/String;)V
    //   85: aload 5
    //   87: aload 4
    //   89: getfield 605	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   92: invokevirtual 873	com/lionmobi/powerclean/model/bean/y:setCurrentVerName	(Ljava/lang/String;)V
    //   95: aload 5
    //   97: aload 4
    //   99: getfield 876	android/content/pm/PackageInfo:versionCode	I
    //   102: invokestatic 878	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   105: invokevirtual 881	com/lionmobi/powerclean/model/bean/y:setCurrentVerCode	(Ljava/lang/String;)V
    //   108: aload 5
    //   110: aload 4
    //   112: getfield 884	android/content/pm/PackageInfo:firstInstallTime	J
    //   115: invokevirtual 887	com/lionmobi/powerclean/model/bean/y:setFirstInstallTime	(J)V
    //   118: aload 5
    //   120: aload 4
    //   122: getfield 890	android/content/pm/PackageInfo:lastUpdateTime	J
    //   125: invokevirtual 893	com/lionmobi/powerclean/model/bean/y:setLastUpdateTime	(J)V
    //   128: aload 5
    //   130: aload 4
    //   132: getfield 861	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   135: getfield 597	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   138: invokevirtual 896	com/lionmobi/powerclean/model/bean/y:setApkPath	(Ljava/lang/String;)V
    //   141: aload 4
    //   143: getfield 861	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   146: getfield 899	android/content/pm/ApplicationInfo:flags	I
    //   149: iconst_1
    //   150: iand
    //   151: ifle +73 -> 224
    //   154: aload 5
    //   156: getstatic 903	com/lionmobi/powerclean/model/bean/z:b	I
    //   159: invokevirtual 906	com/lionmobi/powerclean/model/bean/y:setType$5483418	(I)V
    //   162: aload 5
    //   164: new 167	java/io/File
    //   167: dup
    //   168: aload 4
    //   170: getfield 861	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   173: getfield 597	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   176: invokespecial 537	java/io/File:<init>	(Ljava/lang/String;)V
    //   179: invokevirtual 909	java/io/File:length	()J
    //   182: invokevirtual 912	com/lionmobi/powerclean/model/bean/y:setSize	(J)V
    //   185: aload_1
    //   186: aload 4
    //   188: getfield 867	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   191: aload 5
    //   193: invokeinterface 916 3 0
    //   198: pop
    //   199: goto -172 -> 27
    //   202: astore_2
    //   203: aload_2
    //   204: invokevirtual 458	java/lang/Exception:printStackTrace	()V
    //   207: aload_1
    //   208: areturn
    //   209: astore 6
    //   211: aload 5
    //   213: aload 4
    //   215: getfield 867	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   218: invokevirtual 870	com/lionmobi/powerclean/model/bean/y:setPkgName	(Ljava/lang/String;)V
    //   221: goto -146 -> 75
    //   224: aload 5
    //   226: getstatic 918	com/lionmobi/powerclean/model/bean/z:a	I
    //   229: invokevirtual 906	com/lionmobi/powerclean/model/bean/y:setType$5483418	(I)V
    //   232: goto -70 -> 162
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	235	0	this	g
    //   7	201	1	localConcurrentHashMap	ConcurrentHashMap
    //   15	49	2	localPackageManager	PackageManager
    //   202	2	2	localException1	Exception
    //   26	11	3	localIterator	Iterator
    //   45	169	4	localPackageInfo	PackageInfo
    //   54	171	5	localY	y
    //   209	1	6	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   8	27	202	java/lang/Exception
    //   27	56	202	java/lang/Exception
    //   75	162	202	java/lang/Exception
    //   162	199	202	java/lang/Exception
    //   211	221	202	java/lang/Exception
    //   224	232	202	java/lang/Exception
    //   56	75	209	java/lang/Exception
  }
  
  public final String getAppRootDirFromFullPath(List paramList, String paramString)
  {
    try
    {
      Iterator localIterator = paramList.iterator();
      do
      {
        paramList = paramString;
        if (!localIterator.hasNext()) {
          break;
        }
        paramList = (String)localIterator.next();
      } while (paramString.indexOf(paramList) == -1);
      paramList = paramString.substring(paramList.length());
      return paramList;
    }
    catch (Exception paramList)
    {
      paramList.printStackTrace();
    }
    return paramString;
  }
  
  public final List getAutoStartList(int paramInt)
  {
    ArrayList localArrayList1 = new ArrayList();
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    Object localObject1 = this.p.getPackageManager();
    Object localObject2;
    Object localObject3;
    Iterator localIterator;
    Object localObject5;
    Object localObject4;
    if (localObject1 != null) {
      try
      {
        localObject2 = this.q.iterator();
        for (;;)
        {
          if (((Iterator)localObject2).hasNext())
          {
            localObject3 = (String)((Iterator)localObject2).next();
            localIterator = ((PackageManager)localObject1).queryBroadcastReceivers(new Intent((String)localObject3), 576).iterator();
            while (localIterator.hasNext())
            {
              localObject5 = (ResolveInfo)localIterator.next();
              localObject4 = ((ResolveInfo)localObject5).activityInfo.packageName;
              localObject5 = ((ResolveInfo)localObject5).activityInfo.name;
              if (!localHashMap1.containsKey(localObject4)) {
                break label217;
              }
              ((List)localHashMap1.get(localObject4)).add(localObject5);
              label172:
              if (!localHashMap2.containsKey(localObject4)) {
                break label251;
              }
              ((List)localHashMap2.get(localObject4)).add(localObject3);
            }
            continue;
            return localArrayList1;
          }
        }
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
    }
    for (;;)
    {
      label217:
      ArrayList localArrayList2 = new ArrayList();
      localArrayList2.add(localObject5);
      localException1.put(localObject4, localArrayList2);
      break label172;
      label251:
      localObject5 = new ArrayList();
      ((List)localObject5).add(localObject3);
      localHashMap2.put(localObject4, localObject5);
      break;
      localObject1 = this.p.getPackageManager();
      if (localObject1 != null)
      {
        localObject1 = ((PackageManager)localObject1).getInstalledPackages(512);
        int i2 = ((List)localObject1).size();
        int i1 = 0;
        while (i1 < i2)
        {
          localObject2 = (PackageInfo)((List)localObject1).get(i1);
          if ((localException1.containsKey(((PackageInfo)localObject2).packageName)) && (!((PackageInfo)localObject2).packageName.equals("com.lionmobi.powerclean")) && (paramInt == 0 ? isSystemApp(((PackageInfo)localObject2).packageName) : (paramInt != 1) || (isSystemApp(((PackageInfo)localObject2).packageName))))
          {
            localObject3 = new y();
            try
            {
              ((y)localObject3).b = ((PackageInfo)localObject2).applicationInfo.loadLabel(this.m).toString();
              ((y)localObject3).c = ((PackageInfo)localObject2).packageName;
              ((y)localObject3).o = 1;
              ((y)localObject3).setApkPath("");
              ((y)localObject3).setApkSize(0L);
              ((y)localObject3).setAppDataSize(0L);
              ((y)localObject3).o = 1;
              localIterator = ((List)localException1.get(((PackageInfo)localObject2).packageName)).iterator();
              while (localIterator.hasNext())
              {
                localObject4 = (String)localIterator.next();
                localObject4 = new ComponentName(((PackageInfo)localObject2).packageName, (String)localObject4);
                if ((this.m.getComponentEnabledSetting((ComponentName)localObject4) != 2) && (this.m.getComponentEnabledSetting((ComponentName)localObject4) != 3)) {
                  ((y)localObject3).k = true;
                }
              }
            }
            catch (Exception localException2)
            {
              for (;;)
              {
                ((y)localObject3).b = "";
              }
              HashMap localHashMap3 = new HashMap();
              localHashMap3.put(((PackageInfo)localObject2).packageName, localException1.get(((PackageInfo)localObject2).packageName));
              ((y)localObject3).n = localHashMap3;
              ((y)localObject3).p = ((List)localHashMap2.get(((PackageInfo)localObject2).packageName));
              if ((paramInt == 0) && (!isSystemApp(((PackageInfo)localObject2).packageName)))
              {
                ((y)localObject3).q = z.a;
                localArrayList1.add(localObject3);
              }
              if ((paramInt == 1) && (isSystemApp(((PackageInfo)localObject2).packageName)))
              {
                ((y)localObject3).q = z.b;
                localArrayList1.add(localObject3);
              }
            }
          }
          i1 += 1;
        }
      }
    }
  }
  
  public final String getCurrentPkg()
  {
    try
    {
      if (Build.VERSION.SDK_INT > 20) {
        return ((ActivityManager.RunningAppProcessInfo)((ActivityManager)this.p.getSystemService("activity")).getRunningAppProcesses().get(0)).processName;
      }
      String str = ((ActivityManager.RunningTaskInfo)((ActivityManager)this.p.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public final long getExternalStorage()
  {
    this.a = 0L;
    this.b = 0L;
    this.c = 0L;
    this.d = 0L;
    this.l = 0L;
    Object localObject1;
    long l2;
    if (Build.VERSION.SDK_INT > 10)
    {
      localObject1 = new aw((Activity)this.p).getVolumeList();
      if (localObject1 != null)
      {
        int i2 = localObject1.length;
        long l1 = 0L;
        int i1 = 0;
        for (;;)
        {
          l2 = l1;
          if (i1 >= i2) {
            break;
          }
          Object localObject2 = localObject1[i1];
          l2 = l1;
          if (ax.getStorageVolumeRemovable(localObject2))
          {
            localObject2 = ax.getStorageVolumePath(localObject2);
            l2 = getStorageSize((String)localObject2);
            long l3 = getUsedStorageSize((String)localObject2);
            l2 = l1 + l2;
            this.l += l3;
          }
          i1 += 1;
          l1 = l2;
        }
      }
    }
    else
    {
      localObject1 = Environment.getExternalStorageDirectory();
      l2 = getStorageSize(((File)localObject1).getPath());
      this.l = getUsedStorageSize(((File)localObject1).getPath());
      return l2;
    }
    return 0L;
  }
  
  public final String getExternalStorageString()
  {
    this.a = 0L;
    this.b = 0L;
    this.c = 0L;
    this.d = 0L;
    this.l = 0L;
    Object localObject1;
    long l2;
    if (Build.VERSION.SDK_INT > 10)
    {
      localObject1 = new aw((Activity)this.p).getVolumeList();
      if (localObject1 != null)
      {
        int i2 = localObject1.length;
        long l1 = 0L;
        int i1 = 0;
        for (;;)
        {
          l2 = l1;
          if (i1 >= i2) {
            break;
          }
          Object localObject2 = localObject1[i1];
          l2 = l1;
          if (ax.getStorageVolumeRemovable(localObject2))
          {
            localObject2 = ax.getStorageVolumePath(localObject2);
            l2 = getStorageSize((String)localObject2);
            long l3 = getUsedStorageSize((String)localObject2);
            l2 = l1 + l2;
            this.l += l3;
          }
          i1 += 1;
          l1 = l2;
        }
      }
    }
    else
    {
      localObject1 = Environment.getExternalStorageDirectory();
      l2 = getStorageSize(((File)localObject1).getPath());
      this.l = getUsedStorageSize(((File)localObject1).getPath());
    }
    for (;;)
    {
      if (l2 == 0L) {
        return "Unknown";
      }
      double d1 = l2 / 1024.0D / 1024.0D;
      if (d1 <= 128.0D) {
        return Formatter.formatFileSize(this.p, l2);
      }
      if (d1 < 256.0D) {
        return "256MB";
      }
      if (d1 < 512.0D) {
        return "512MB";
      }
      d1 /= 1024.0D;
      if (d1 < 1.0D) {
        return "1GB";
      }
      if (d1 < 2.0D) {
        return "2GB";
      }
      if (d1 < 4.0D) {
        return "4GB";
      }
      if (d1 < 8.0D) {
        return "8GB";
      }
      if (d1 < 16.0D) {
        return "16GB";
      }
      if (d1 < 32.0D) {
        return "32GB";
      }
      if (d1 < 64.0D) {
        return "64GB";
      }
      if (d1 < 128.0D) {
        return "128GB";
      }
      return a(this.p, l2);
      l2 = 0L;
    }
  }
  
  public final long getExternalStorageUsedSize()
  {
    return this.l;
  }
  
  public final long getHistoryCleanSize()
  {
    return this.p.getSharedPreferences("junk_clean", 0).getLong("history_clean_size", 0L);
  }
  
  public final String getHomeLauncherPkg()
  {
    try
    {
      Object localObject = new Intent("android.intent.action.MAIN");
      ((Intent)localObject).addCategory("android.intent.category.HOME");
      localObject = this.p.getPackageManager().resolveActivity((Intent)localObject, 65536).activityInfo.packageName;
      return localObject;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public final Map getInstalledAppMap()
  {
    localHashMap = new HashMap();
    try
    {
      PackageManager localPackageManager = this.p.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        try
        {
          String str2 = String.valueOf(localPackageInfo.applicationInfo.loadLabel(localPackageManager));
          String str1 = str2;
          if (!TextUtils.isEmpty(str2)) {
            str1 = str2.toLowerCase();
          }
          localHashMap.put(localPackageInfo.packageName, str1);
        }
        catch (Exception localException1) {}
      }
      return localHashMap;
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
    }
  }
  
  public final long getInternalStorage()
  {
    this.a = 0L;
    this.b = 0L;
    this.c = 0L;
    this.d = 0L;
    Object localObject1 = Environment.getDataDirectory();
    this.b = getStorageSize(((File)localObject1).getPath());
    this.c = getUsedStorageSize(((File)localObject1).getPath());
    long l1 = 0L + this.b;
    if (Build.VERSION.SDK_INT > 10)
    {
      localObject1 = new aw((Activity)this.p).getVolumeList();
      if (localObject1 != null)
      {
        int i2 = localObject1.length;
        int i1 = 0;
        l2 = l1;
        if (i1 >= i2) {
          break label207;
        }
        Object localObject2 = localObject1[i1];
        String str = ax.getStorageVolumePath(localObject2);
        long l3 = getStorageSize(str);
        if (ax.getStorageVolumeEmulated(localObject2))
        {
          l2 = l1;
          if (getUsedStorageSize(str) != this.c)
          {
            l2 = l1;
            if (Math.abs(l3 - this.b) > 524288000L) {
              l2 = l1 + l3;
            }
          }
        }
        for (;;)
        {
          i1 += 1;
          l1 = l2;
          break;
          l2 = l1;
          if (!ax.getStorageVolumeRemovable(localObject2)) {
            l2 = l1 + l3;
          }
        }
      }
    }
    long l2 = l1;
    label207:
    return l2;
  }
  
  public final String getInternalStorageString()
  {
    return formatFileSize(this.p, getInternalStorage());
  }
  
  public final Set getPkgNameOfInstalledApp()
  {
    localHashSet = new HashSet();
    try
    {
      Iterator localIterator = this.p.getPackageManager().getInstalledPackages(0).iterator();
      while (localIterator.hasNext()) {
        localHashSet.add(((PackageInfo)localIterator.next()).packageName);
      }
      return localHashSet;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public final int getRandomNums(int paramInt1, int paramInt2)
  {
    try
    {
      paramInt2 = new Random().nextInt(paramInt2 - paramInt1 + 1);
      return paramInt2 + paramInt1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  public final int getRarelyUsedAppCount()
  {
    int i2 = 0;
    long l1 = System.currentTimeMillis();
    Object localObject4 = q.getInstance(this.p).getReadableDatabase();
    Object localObject3 = null;
    localObject1 = null;
    try
    {
      localObject4 = ((SQLiteDatabase)localObject4).rawQuery("select count(*) from lion_prostats where lastlaunchtime  <= ?", new String[] { String.valueOf(l1 - 604800000L) });
      localObject1 = localObject4;
      localObject3 = localObject4;
      ((Cursor)localObject4).moveToNext();
      localObject1 = localObject4;
      localObject3 = localObject4;
      int i1 = ((Cursor)localObject4).getInt(0);
      i2 = i1;
      if (localObject4 != null)
      {
        ((Cursor)localObject4).close();
        i2 = i1;
      }
    }
    catch (Exception localException)
    {
      return 0;
    }
    finally
    {
      if (localException == null) {
        break label130;
      }
      localException.close();
    }
    return i2;
  }
  
  public final y getRestartServicePro(int paramInt)
  {
    localObject5 = new y();
    Object localObject1 = q.getInstance(this.p).getReadableDatabase();
    Object localObject3 = null;
    try
    {
      localObject1 = ((SQLiteDatabase)localObject1).rawQuery("select * from lion_servicestats where restartcount  >= ?", new String[] { String.valueOf(paramInt) });
      localObject3 = localObject1;
      if (localObject5 == null) {
        break label153;
      }
    }
    catch (Exception localException)
    {
      do
      {
        try
        {
          if (((Cursor)localObject1).moveToNext())
          {
            localObject3 = localObject1;
            ((y)localObject5).c = ((Cursor)localObject1).getString(1);
            localObject3 = localObject1;
            ((y)localObject5).b = ((Cursor)localObject1).getString(2);
            localObject3 = localObject1;
            ((y)localObject5).a = ((Cursor)localObject1).getInt(3);
            localObject3 = localObject1;
            ((y)localObject5).Y = ((Cursor)localObject1).getInt(4);
          }
          if (localObject1 != null) {
            ((Cursor)localObject1).close();
          }
          return localObject5;
        }
        finally
        {
          for (;;)
          {
            localObject5 = localObject2;
          }
        }
        localException = localException;
      } while (localObject3 == null);
      localObject3.close();
      return localObject5;
    }
    finally
    {
      localObject2 = finally;
      localObject5 = null;
      localObject3 = localObject2;
    }
    ((Cursor)localObject5).close();
    label153:
    throw localObject3;
  }
  
  public final Set getRunningProcessList()
  {
    Set localSet = Collections.synchronizedSet(new HashSet());
    this.s = ((ActivityManager)this.p.getSystemService("activity"));
    Iterator localIterator = this.s.getRunningAppProcesses().iterator();
    for (;;)
    {
      Object localObject;
      if (localIterator.hasNext()) {
        localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      }
      try
      {
        String str = ((ActivityManager.RunningAppProcessInfo)localObject).processName;
        localObject = ((ActivityManager.RunningAppProcessInfo)localObject).pkgList;
        if (localObject != null)
        {
          int i2 = localObject.length;
          int i1 = 0;
          while (i1 < i2)
          {
            str = localObject[i1];
            if (!TextUtils.isEmpty(str)) {
              localSet.add(str);
            }
            i1 += 1;
          }
          return localSet;
        }
      }
      catch (Exception localException) {}
    }
  }
  
  public final Map getRunningProcessSet()
  {
    ConcurrentHashMap localConcurrentHashMap = new ConcurrentHashMap();
    this.s = ((ActivityManager)this.p.getSystemService("activity"));
    Iterator localIterator = this.s.getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      Object localObject2 = localRunningAppProcessInfo.processName;
      Object localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        localObject1 = "";
      }
      localObject2 = localRunningAppProcessInfo.pkgList;
      int i1 = this.s.getProcessMemoryInfo(new int[] { localRunningAppProcessInfo.pid })[0].dalvikPrivateDirty;
      localConcurrentHashMap.put(localObject1, Long.valueOf(this.s.getProcessMemoryInfo(new int[] { localRunningAppProcessInfo.pid })[0].dalvikPss * 1024 + i1 * 1024));
    }
    return localConcurrentHashMap;
  }
  
  public final List getSearchPathList()
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      int i1;
      try
      {
        if (Build.VERSION.SDK_INT > 10)
        {
          Object[] arrayOfObject = new aw(this.p).getVolumeList();
          int i2 = arrayOfObject.length;
          i1 = 0;
          if (i1 < i2)
          {
            String str = ax.getStorageVolumePath(arrayOfObject[i1]);
            if ((str.equals("/data")) || (str.equals("/system")) || (str.equals("/dev")) || (str.equals("/cache")) || (str.equals("/sys"))) {
              break label285;
            }
            File localFile2 = new File(str);
            if ((localFile2.listFiles() == null) || (localFile2.listFiles().length <= 0)) {
              break label285;
            }
            if (Build.VERSION.SDK_INT >= 19)
            {
              if (CheckStorageaccess(str)) {
                localArrayList.add(str);
              }
            }
            else {
              localArrayList.add(str);
            }
          }
        }
      }
      catch (Exception localException1)
      {
        try
        {
          if (localArrayList.size() == 0)
          {
            localFile1 = Environment.getExternalStorageDirectory();
            if ((localFile1.listFiles() != null) && (localFile1.listFiles().length > 0)) {
              localArrayList.add(localFile1.getPath());
            }
          }
          return localArrayList;
        }
        catch (OutOfMemoryError localOutOfMemoryError2)
        {
          File localFile1;
          return localArrayList;
        }
        catch (Exception localException2)
        {
          return localArrayList;
        }
        if (!Environment.getExternalStorageState().equals("mounted")) {
          continue;
        }
        localFile1 = Environment.getExternalStorageDirectory();
        if ((localFile1.listFiles() == null) || (localFile1.listFiles().length <= 0)) {
          continue;
        }
        localArrayList.add(localFile1.getPath());
        continue;
      }
      catch (OutOfMemoryError localOutOfMemoryError1)
      {
        continue;
      }
      label285:
      i1 += 1;
    }
  }
  
  /* Error */
  public final Map getServiceFromTable()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 852	java/util/concurrent/ConcurrentHashMap
    //   6: dup
    //   7: invokespecial 853	java/util/concurrent/ConcurrentHashMap:<init>	()V
    //   10: astore 5
    //   12: aload_0
    //   13: getfield 87	com/lionmobi/util/g:p	Landroid/content/Context;
    //   16: invokestatic 708	com/lionmobi/util/q:getInstance	(Landroid/content/Context;)Lcom/lionmobi/util/q;
    //   19: invokevirtual 1063	com/lionmobi/util/q:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   22: astore_3
    //   23: aload_3
    //   24: ldc_w 1140
    //   27: aconst_null
    //   28: invokevirtual 1074	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   31: astore_3
    //   32: aload_3
    //   33: astore 4
    //   35: aload_3
    //   36: invokeinterface 1079 1 0
    //   41: ifeq +133 -> 174
    //   44: aload_3
    //   45: astore 4
    //   47: new 752	com/lionmobi/powerclean/model/bean/y
    //   50: dup
    //   51: invokespecial 857	com/lionmobi/powerclean/model/bean/y:<init>	()V
    //   54: astore 6
    //   56: aload_3
    //   57: astore 4
    //   59: aload_3
    //   60: iconst_1
    //   61: invokeinterface 1088 2 0
    //   66: astore 7
    //   68: aload_3
    //   69: astore 4
    //   71: aload_3
    //   72: iconst_2
    //   73: invokeinterface 1088 2 0
    //   78: astore 8
    //   80: aload_3
    //   81: astore 4
    //   83: aload_3
    //   84: iconst_3
    //   85: invokeinterface 1082 2 0
    //   90: istore_1
    //   91: aload_3
    //   92: astore 4
    //   94: aload_3
    //   95: iconst_4
    //   96: invokeinterface 1082 2 0
    //   101: istore_2
    //   102: aload_3
    //   103: astore 4
    //   105: aload 6
    //   107: aload 7
    //   109: putfield 787	com/lionmobi/powerclean/model/bean/y:c	Ljava/lang/String;
    //   112: aload_3
    //   113: astore 4
    //   115: aload 6
    //   117: aload 8
    //   119: putfield 957	com/lionmobi/powerclean/model/bean/y:b	Ljava/lang/String;
    //   122: aload_3
    //   123: astore 4
    //   125: aload 6
    //   127: iload_1
    //   128: putfield 1089	com/lionmobi/powerclean/model/bean/y:a	I
    //   131: aload_3
    //   132: astore 4
    //   134: aload 6
    //   136: iload_2
    //   137: putfield 1092	com/lionmobi/powerclean/model/bean/y:Y	I
    //   140: aload_3
    //   141: astore 4
    //   143: aload 5
    //   145: aload 7
    //   147: aload 6
    //   149: invokeinterface 916 3 0
    //   154: pop
    //   155: goto -123 -> 32
    //   158: astore_3
    //   159: aload 4
    //   161: ifnull +10 -> 171
    //   164: aload 4
    //   166: invokeinterface 1083 1 0
    //   171: aload 5
    //   173: areturn
    //   174: aload_3
    //   175: ifnull -4 -> 171
    //   178: aload_3
    //   179: invokeinterface 1083 1 0
    //   184: aload 5
    //   186: areturn
    //   187: astore_3
    //   188: aconst_null
    //   189: astore 5
    //   191: aload_3
    //   192: astore 4
    //   194: aload 5
    //   196: ifnull +10 -> 206
    //   199: aload 5
    //   201: invokeinterface 1083 1 0
    //   206: aload 4
    //   208: athrow
    //   209: astore 4
    //   211: aload_3
    //   212: astore 5
    //   214: goto -20 -> 194
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	217	0	this	g
    //   90	38	1	i1	int
    //   101	36	2	i2	int
    //   22	119	3	localObject1	Object
    //   158	21	3	localException	Exception
    //   187	25	3	localObject2	Object
    //   1	206	4	localObject3	Object
    //   209	1	4	localObject4	Object
    //   10	203	5	localObject5	Object
    //   54	94	6	localY	y
    //   66	80	7	str1	String
    //   78	40	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   23	32	158	java/lang/Exception
    //   35	44	158	java/lang/Exception
    //   47	56	158	java/lang/Exception
    //   59	68	158	java/lang/Exception
    //   71	80	158	java/lang/Exception
    //   83	91	158	java/lang/Exception
    //   94	102	158	java/lang/Exception
    //   105	112	158	java/lang/Exception
    //   115	122	158	java/lang/Exception
    //   125	131	158	java/lang/Exception
    //   134	140	158	java/lang/Exception
    //   143	155	158	java/lang/Exception
    //   23	32	187	finally
    //   35	44	209	finally
    //   47	56	209	finally
    //   59	68	209	finally
    //   71	80	209	finally
    //   83	91	209	finally
    //   94	102	209	finally
    //   105	112	209	finally
    //   115	122	209	finally
    //   125	131	209	finally
    //   134	140	209	finally
    //   143	155	209	finally
  }
  
  public final String getSizeString(long paramLong)
  {
    if (Build.MANUFACTURER.equals("Xiaomi"))
    {
      double d1 = paramLong;
      double d2 = this.u;
      return a(this.p, (d1 * d2));
    }
    return a(this.p, paramLong);
  }
  
  public final String getToday()
  {
    return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
  }
  
  public final long getUsedStorageSize(String paramString)
  {
    try
    {
      paramString = new StatFs(paramString);
      long l1 = paramString.getBlockSize();
      long l2 = paramString.getBlockCount();
      int i1 = paramString.getFreeBlocks();
      return (l2 - i1) * l1;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0L;
  }
  
  public final boolean grantSuperUserByRootTools()
  {
    com.b.a.a.a = false;
    if (com.b.a.a.isRootAvailable())
    {
      if (com.b.a.a.isAccessGiven())
      {
        writeSUSatus(true);
        return true;
      }
      ((Activity)this.p).runOnUiThread(new Runnable()
      {
        public final void run()
        {
          Toast.makeText(g.this.p, g.this.p.getResources().getString(2131165714), 0).show();
        }
      });
      return false;
    }
    ((Activity)this.p).runOnUiThread(new Runnable()
    {
      public final void run()
      {
        Toast.makeText(g.this.p, g.this.p.getResources().getString(2131165714), 0).show();
      }
    });
    return false;
  }
  
  public final boolean isFeqEffective()
  {
    Object localObject6 = null;
    long l1 = System.currentTimeMillis();
    SQLiteDatabase localSQLiteDatabase = q.getInstance(this.p).getReadableDatabase();
    Object localObject5 = localObject6;
    for (;;)
    {
      boolean bool1;
      try
      {
        if ((this.p instanceof Activity))
        {
          localObject5 = localObject6;
          localObject1 = ((ApplicationEx)((Activity)this.p).getApplication()).getGlobalSettingPreference();
          localObject5 = localObject6;
          if (l1 - ((SharedPreferences)localObject1).getLong("first_launch_time", l1) < 259200000L) {
            return false;
          }
        }
        else
        {
          localObject5 = localObject6;
          if (!(this.p instanceof Service)) {
            break label257;
          }
          localObject5 = localObject6;
          localObject1 = this.p.getSharedPreferences("com.lionmobi.powerclean_preferences", 0);
          continue;
        }
        localObject5 = localObject6;
        localObject1 = localSQLiteDatabase.rawQuery("select count(*) from lion_prostats where lastlaunchtime >0 and lastlaunchtime  > ?", new String[] { String.valueOf(l1 - 172800000L) });
        localObject5 = localObject1;
      }
      catch (Exception localException1)
      {
        Object localObject1;
        int i1;
        Object localObject2 = null;
        if (localObject2 != null)
        {
          localObject2.close();
          bool2 = false;
          continue;
        }
      }
      finally
      {
        if (localObject5 != null) {
          localObject5.close();
        }
      }
      try
      {
        ((Cursor)localObject1).moveToNext();
        localObject5 = localObject1;
        i1 = ((Cursor)localObject1).getInt(0);
        if (i1 <= 1) {
          break label251;
        }
        bool1 = true;
      }
      catch (Exception localException2)
      {
        continue;
        bool2 = false;
        continue;
        bool1 = false;
        continue;
      }
      boolean bool2 = bool1;
      if (localObject1 != null)
      {
        ((Cursor)localObject1).close();
        bool2 = bool1;
      }
      return bool2;
      label251:
      label257:
      Object localObject4 = null;
    }
  }
  
  public final boolean isInRunningProcessList(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    this.s = ((ActivityManager)this.p.getSystemService("activity"));
    Iterator localIterator = this.s.getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      String[] arrayOfString = ((ActivityManager.RunningAppProcessInfo)localIterator.next()).pkgList;
      if (arrayOfString != null)
      {
        int i2 = arrayOfString.length;
        int i1 = 0;
        while (i1 < i2)
        {
          String str = arrayOfString[i1];
          if ((!TextUtils.isEmpty(str)) && (str.equals(paramString))) {
            return true;
          }
          i1 += 1;
        }
      }
    }
    return false;
  }
  
  public final boolean isInRunningService(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    this.s = ((ActivityManager)this.p.getSystemService("activity"));
    Iterator localIterator = this.s.getRunningServices(Integer.MAX_VALUE).iterator();
    while (localIterator.hasNext())
    {
      String str2 = ((ActivityManager.RunningServiceInfo)localIterator.next()).service.getPackageName();
      if (str2 != null)
      {
        String str1 = str2;
        if (str2.contains(":")) {
          str1 = str2.split(":")[0];
        }
        if ((!TextUtils.isEmpty(str1)) && (str1.equals(paramString))) {
          return true;
        }
      }
    }
    return false;
  }
  
  public final boolean isSystemApp(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        paramString = this.m.getApplicationInfo(paramString, 8192);
        if (paramString != null)
        {
          int i1 = paramString.flags;
          if ((i1 & 0x1) > 0) {
            return true;
          }
        }
      }
      catch (Exception paramString) {}
    }
    return false;
  }
  
  public final void onPowerBattery(String paramString)
  {
    if (isAppInstalled(this.p, "com.lionmobi.battery"))
    {
      paramString = this.p.getPackageManager().getLaunchIntentForPackage("com.lionmobi.battery");
      paramString.addFlags(268435456);
      this.p.startActivity(paramString);
      return;
    }
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.lionmobi.battery&referrer=channel%3Dpower_clean%26sub_ch%3D" + paramString));
      if (isAppInstalled(this.p, "com.android.vending")) {
        localIntent.setPackage("com.android.vending");
      }
      this.p.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.lionmobi.battery&referrer=channel%3Dpower_clean%26sub_ch%3D" + paramString));
      this.p.startActivity(paramString);
    }
  }
  
  public final void onPowerLight(String paramString)
  {
    if (isAppInstalled(this.p, "com.lionmobi.flashlight"))
    {
      paramString = this.p.getPackageManager().getLaunchIntentForPackage("com.lionmobi.flashlight");
      paramString.addFlags(268435456);
      this.p.startActivity(paramString);
      return;
    }
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.lionmobi.flashlight&referrer=channel%3Dpower_clean%26sub_ch%3D" + paramString));
      if (isAppInstalled(this.p, "com.android.vending")) {
        localIntent.setPackage("com.android.vending");
      }
      this.p.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.lionmobi.flashlight&referrer=channel%3Dpower_clean%26sub_ch%3D" + paramString));
      this.p.startActivity(paramString);
    }
  }
  
  public final void openApp(String paramString)
  {
    paramString = this.p.getPackageManager().getLaunchIntentForPackage(paramString);
    paramString.addFlags(335544320);
    this.p.startActivity(paramString);
  }
  
  public final String readLatestCleanDate()
  {
    return this.p.getSharedPreferences("junk_clean", 0).getString("latest_clean_date", "1970-01-01");
  }
  
  public final long readLatestCleanSize()
  {
    return this.p.getSharedPreferences("junk_clean", 0).getLong("cleanSize", 0L);
  }
  
  public final void resetServiceCount()
  {
    SQLiteDatabase localSQLiteDatabase = q.getInstance(this.p).getWritableDatabase();
    try
    {
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("update lion_servicestats set restartcount=?", new Object[] { Integer.valueOf(0) });
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return;
    }
    catch (Exception localException)
    {
      localException = localException;
      localSQLiteDatabase.endTransaction();
      return;
    }
    finally
    {
      localObject = finally;
      localSQLiteDatabase.endTransaction();
      throw localObject;
    }
  }
  
  public final void setTotalAndUsedSize()
  {
    this.a = 0L;
    this.b = 0L;
    this.c = 0L;
    this.d = 0L;
    label354:
    for (;;)
    {
      try
      {
        Object localObject1 = Environment.getDataDirectory();
        this.b = getStorageSize(((File)localObject1).getPath());
        this.c = getUsedStorageSize(((File)localObject1).getPath());
        this.d += this.c;
        this.a += this.b;
        int i1;
        if (Build.VERSION.SDK_INT > 10)
        {
          localObject1 = new aw((Activity)this.p).getVolumeList();
          if (localObject1 == null) {
            return;
          }
          int i2 = localObject1.length;
          i1 = 0;
          if (i1 < i2)
          {
            Object localObject2 = localObject1[i1];
            String str = ax.getStorageVolumePath(localObject2);
            long l1 = getStorageSize(str);
            long l2 = getUsedStorageSize(str);
            if (ax.getStorageVolumeEmulated(localObject2))
            {
              if ((l2 == this.c) || (Math.abs(l1 - this.b) <= 524288000L)) {
                break label354;
              }
              this.a = (l1 + this.a);
              this.d = (l2 + this.d);
              break label354;
            }
            ax.getStorageVolumeRemovable(localObject2);
            continue;
          }
        }
        File localFile;
        i1 += 1;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        if (this.a > this.d)
        {
          this.d -= this.a / 100L;
          return;
          if (Build.MANUFACTURER.equals("Xiaomi"))
          {
            this.d += getUsedStorageSize("/system");
            continue;
            localFile = Environment.getExternalStorageDirectory();
            if (Environment.getExternalStorageState().equals("mounted"))
            {
              this.b = getStorageSize(localFile.getPath());
              this.c = getUsedStorageSize(localFile.getPath());
              this.d += this.c;
              this.a += this.b;
            }
          }
        }
        else
        {
          return;
        }
      }
    }
  }
  
  public final void unInstallApp(Fragment paramFragment, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE", Uri.fromParts("package", paramString, null));
    localIntent.putExtra("pkg", paramString);
    paramFragment.startActivityForResult(localIntent, 5);
  }
  
  /* Error */
  public final void updataServiceCount(ArrayList paramArrayList)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +10 -> 11
    //   4: aload_1
    //   5: invokevirtual 1294	java/util/ArrayList:size	()I
    //   8: ifne +4 -> 12
    //   11: return
    //   12: aload_0
    //   13: getfield 87	com/lionmobi/util/g:p	Landroid/content/Context;
    //   16: invokestatic 708	com/lionmobi/util/q:getInstance	(Landroid/content/Context;)Lcom/lionmobi/util/q;
    //   19: invokevirtual 712	com/lionmobi/util/q:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   22: astore_2
    //   23: aload_2
    //   24: invokevirtual 717	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   27: aload_1
    //   28: invokevirtual 1295	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   31: astore_1
    //   32: aload_1
    //   33: invokeinterface 685 1 0
    //   38: ifeq +91 -> 129
    //   41: aload_1
    //   42: invokeinterface 689 1 0
    //   47: checkcast 752	com/lionmobi/powerclean/model/bean/y
    //   50: astore_3
    //   51: aload_2
    //   52: new 142	java/lang/StringBuilder
    //   55: dup
    //   56: ldc_w 1297
    //   59: invokespecial 785	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   62: aload_3
    //   63: getfield 787	com/lionmobi/powerclean/model/bean/y:c	Ljava/lang/String;
    //   66: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc_w 1299
    //   72: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: aload_3
    //   76: getfield 957	com/lionmobi/powerclean/model/bean/y:b	Ljava/lang/String;
    //   79: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: ldc_w 1301
    //   85: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload_3
    //   89: getfield 1089	com/lionmobi/powerclean/model/bean/y:a	I
    //   92: invokevirtual 1304	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   95: ldc_w 1306
    //   98: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: aload_3
    //   102: getfield 1092	com/lionmobi/powerclean/model/bean/y:Y	I
    //   105: invokevirtual 1304	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   108: ldc_w 1308
    //   111: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokevirtual 1310	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   120: goto -88 -> 32
    //   123: astore_1
    //   124: aload_2
    //   125: invokevirtual 729	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   128: return
    //   129: aload_2
    //   130: invokevirtual 726	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   133: aload_2
    //   134: invokevirtual 729	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   137: return
    //   138: astore_1
    //   139: aload_2
    //   140: invokevirtual 729	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   143: aload_1
    //   144: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	145	0	this	g
    //   0	145	1	paramArrayList	ArrayList
    //   22	118	2	localSQLiteDatabase	SQLiteDatabase
    //   50	52	3	localY	y
    // Exception table:
    //   from	to	target	type
    //   23	32	123	java/lang/Exception
    //   32	120	123	java/lang/Exception
    //   129	133	123	java/lang/Exception
    //   23	32	138	finally
    //   32	120	138	finally
    //   129	133	138	finally
  }
  
  public final void writeLatestCleanDate()
  {
    SharedPreferences.Editor localEditor = this.p.getSharedPreferences("junk_clean", 0).edit();
    Date localDate = new Date();
    localEditor.putString("latest_clean_date", new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(localDate));
    localEditor.commit();
  }
  
  public final void writeSUSatus(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.p.getSharedPreferences("lion_root_check", 0).edit();
    localEditor.putBoolean("is_su", paramBoolean);
    localEditor.commit();
  }
  
  public final void writeTodayCleannedSize(long paramLong)
  {
    try
    {
      if (!readLatestCleanDate().equals(getToday())) {
        writeLatestCleanDate();
      }
      for (;;)
      {
        SharedPreferences.Editor localEditor = this.p.getSharedPreferences("junk_clean", 0).edit();
        localEditor.putLong("cleanSize", paramLong);
        localEditor.commit();
        return;
        long l1 = readLatestCleanSize();
        paramLong += l1;
      }
    }
    catch (ParseException localParseException)
    {
      for (;;)
      {
        writeLatestCleanDate();
        paramLong = 0L;
      }
    }
  }
}
