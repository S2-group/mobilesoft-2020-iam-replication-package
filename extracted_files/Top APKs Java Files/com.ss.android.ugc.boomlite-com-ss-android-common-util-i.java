package com.ss.android.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.os.Process;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.StringUtils;
import com.bytedance.common.utility.reflect.b;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class i
{
  public static final int FLAG_LOC_GPS = 1;
  public static final int FLAG_LOC_NETWORK = 2;
  public static final int FLAG_LOC_PASSIVE = 4;
  public static String MESSAGE_PROCESS_SUFFIX = ":push";
  public static final String NODEX_PROCESS_SUFFIX = ":nodex";
  private static String a = null;
  private static String b = null;
  private static boolean c = true;
  public static boolean sIsInited;
  public static boolean sIsMiui = false;
  
  static
  {
    sIsInited = false;
  }
  
  public i() {}
  
  /* Error */
  private static String a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 53	java/io/BufferedReader
    //   5: dup
    //   6: new 55	java/io/InputStreamReader
    //   9: dup
    //   10: new 57	java/io/FileInputStream
    //   13: dup
    //   14: new 59	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   21: ldc 62
    //   23: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokestatic 72	android/os/Process:myPid	()I
    //   29: invokevirtual 75	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   32: ldc 77
    //   34: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokespecial 83	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   43: ldc 85
    //   45: invokespecial 88	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   48: invokespecial 91	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   51: astore_1
    //   52: new 59	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   59: astore_2
    //   60: aload_1
    //   61: invokevirtual 94	java/io/BufferedReader:read	()I
    //   64: istore_0
    //   65: iload_0
    //   66: ifle +28 -> 94
    //   69: aload_2
    //   70: iload_0
    //   71: i2c
    //   72: invokevirtual 97	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: goto -16 -> 60
    //   79: astore_2
    //   80: aload_3
    //   81: astore_2
    //   82: aload_1
    //   83: ifnull +9 -> 92
    //   86: aload_1
    //   87: invokevirtual 100	java/io/BufferedReader:close	()V
    //   90: aload_3
    //   91: astore_2
    //   92: aload_2
    //   93: areturn
    //   94: invokestatic 106	com/bytedance/common/utility/Logger:debug	()Z
    //   97: ifeq +30 -> 127
    //   100: ldc 108
    //   102: new 59	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   109: ldc 110
    //   111: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: aload_2
    //   115: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   118: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   124: invokestatic 114	com/bytedance/common/utility/Logger:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   127: aload_2
    //   128: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: astore_2
    //   132: aload_2
    //   133: astore_3
    //   134: aload_3
    //   135: astore_2
    //   136: aload_1
    //   137: ifnull -45 -> 92
    //   140: aload_1
    //   141: invokevirtual 100	java/io/BufferedReader:close	()V
    //   144: aload_3
    //   145: areturn
    //   146: astore_1
    //   147: aload_3
    //   148: areturn
    //   149: astore_1
    //   150: aconst_null
    //   151: astore_2
    //   152: aload_2
    //   153: ifnull +7 -> 160
    //   156: aload_2
    //   157: invokevirtual 100	java/io/BufferedReader:close	()V
    //   160: aload_1
    //   161: athrow
    //   162: astore_1
    //   163: aconst_null
    //   164: areturn
    //   165: astore_2
    //   166: goto -6 -> 160
    //   169: astore_2
    //   170: aload_1
    //   171: astore_3
    //   172: aload_2
    //   173: astore_1
    //   174: aload_3
    //   175: astore_2
    //   176: goto -24 -> 152
    //   179: astore_1
    //   180: aconst_null
    //   181: astore_1
    //   182: goto -102 -> 80
    // Local variable table:
    //   start	length	slot	name	signature
    //   64	7	0	i	int
    //   51	90	1	localBufferedReader	java.io.BufferedReader
    //   146	1	1	localException1	Exception
    //   149	12	1	localObject1	Object
    //   162	9	1	localException2	Exception
    //   173	1	1	localObject2	Object
    //   179	1	1	localThrowable1	Throwable
    //   181	1	1	localObject3	Object
    //   59	11	2	localStringBuilder	StringBuilder
    //   79	1	2	localThrowable2	Throwable
    //   81	76	2	localObject4	Object
    //   165	1	2	localException3	Exception
    //   169	4	2	localObject5	Object
    //   175	1	2	localObject6	Object
    //   1	174	3	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   52	60	79	java/lang/Throwable
    //   60	65	79	java/lang/Throwable
    //   69	76	79	java/lang/Throwable
    //   94	127	79	java/lang/Throwable
    //   127	132	79	java/lang/Throwable
    //   140	144	146	java/lang/Exception
    //   2	52	149	finally
    //   86	90	162	java/lang/Exception
    //   156	160	165	java/lang/Exception
    //   52	60	169	finally
    //   60	65	169	finally
    //   69	76	169	finally
    //   94	127	169	finally
    //   127	132	169	finally
    //   2	52	179	java/lang/Throwable
  }
  
  private static String a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
        if (arrayOfProviderInfo != null)
        {
          int j = arrayOfProviderInfo.length;
          int i = 0;
          while (i < j)
          {
            ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
            if ((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission)))
            {
              paramContext = localProviderInfo.authority;
              return paramContext;
            }
            i += 1;
          }
        }
      }
      return null;
    }
    catch (Exception paramContext) {}
  }
  
  /* Error */
  private static String a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: getstatic 34	com/ss/android/common/util/i:a	Ljava/lang/String;
    //   5: invokestatic 179	com/bytedance/common/utility/StringUtils:isEmpty	(Ljava/lang/String;)Z
    //   8: ifne +9 -> 17
    //   11: getstatic 34	com/ss/android/common/util/i:a	Ljava/lang/String;
    //   14: astore_0
    //   15: aload_0
    //   16: areturn
    //   17: new 53	java/io/BufferedReader
    //   20: dup
    //   21: new 55	java/io/InputStreamReader
    //   24: dup
    //   25: invokestatic 185	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   28: new 59	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   35: ldc -69
    //   37: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_0
    //   41: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   47: invokevirtual 191	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   50: invokevirtual 197	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   53: invokespecial 200	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   56: sipush 1024
    //   59: invokespecial 203	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   62: astore_3
    //   63: aload_3
    //   64: astore_2
    //   65: aload_3
    //   66: invokevirtual 206	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   69: astore_1
    //   70: aload_3
    //   71: astore_2
    //   72: aload_3
    //   73: invokevirtual 100	java/io/BufferedReader:close	()V
    //   76: aload_3
    //   77: astore_2
    //   78: aload_1
    //   79: putstatic 34	com/ss/android/common/util/i:a	Ljava/lang/String;
    //   82: aload_1
    //   83: astore_0
    //   84: aload_3
    //   85: ifnull -70 -> 15
    //   88: aload_3
    //   89: invokevirtual 100	java/io/BufferedReader:close	()V
    //   92: aload_1
    //   93: areturn
    //   94: astore_0
    //   95: ldc -48
    //   97: ldc -46
    //   99: aload_0
    //   100: invokestatic 214	com/bytedance/common/utility/Logger:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   103: aload_1
    //   104: areturn
    //   105: astore 4
    //   107: aconst_null
    //   108: astore_3
    //   109: aconst_null
    //   110: astore_1
    //   111: aload_3
    //   112: astore_2
    //   113: ldc -48
    //   115: new 59	java/lang/StringBuilder
    //   118: dup
    //   119: invokespecial 60	java/lang/StringBuilder:<init>	()V
    //   122: ldc -40
    //   124: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: aload_0
    //   128: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: aload 4
    //   136: invokestatic 214	com/bytedance/common/utility/Logger:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   139: aload_1
    //   140: astore_0
    //   141: aload_3
    //   142: ifnull -127 -> 15
    //   145: aload_3
    //   146: invokevirtual 100	java/io/BufferedReader:close	()V
    //   149: aload_1
    //   150: areturn
    //   151: astore_0
    //   152: ldc -48
    //   154: ldc -46
    //   156: aload_0
    //   157: invokestatic 214	com/bytedance/common/utility/Logger:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   160: aload_1
    //   161: areturn
    //   162: astore_0
    //   163: aload_1
    //   164: ifnull +7 -> 171
    //   167: aload_1
    //   168: invokevirtual 100	java/io/BufferedReader:close	()V
    //   171: aload_0
    //   172: athrow
    //   173: astore_1
    //   174: ldc -48
    //   176: ldc -46
    //   178: aload_1
    //   179: invokestatic 214	com/bytedance/common/utility/Logger:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   182: goto -11 -> 171
    //   185: astore_0
    //   186: aload_2
    //   187: astore_1
    //   188: goto -25 -> 163
    //   191: astore 4
    //   193: aconst_null
    //   194: astore_1
    //   195: goto -84 -> 111
    //   198: astore 4
    //   200: goto -89 -> 111
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	203	0	paramString	String
    //   1	167	1	str	String
    //   173	6	1	localIOException	java.io.IOException
    //   187	8	1	localObject	Object
    //   64	123	2	localBufferedReader1	java.io.BufferedReader
    //   62	84	3	localBufferedReader2	java.io.BufferedReader
    //   105	30	4	localThrowable1	Throwable
    //   191	1	4	localThrowable2	Throwable
    //   198	1	4	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   88	92	94	java/io/IOException
    //   17	63	105	java/lang/Throwable
    //   145	149	151	java/io/IOException
    //   17	63	162	finally
    //   167	171	173	java/io/IOException
    //   65	70	185	finally
    //   72	76	185	finally
    //   78	82	185	finally
    //   113	139	185	finally
    //   65	70	191	java/lang/Throwable
    //   72	76	198	java/lang/Throwable
    //   78	82	198	java/lang/Throwable
  }
  
  private static void a(boolean paramBoolean, Window paramWindow)
  {
    int i = 0;
    try
    {
      Object localObject = paramWindow.getClass();
      Class localClass = Class.forName("android.view.MiuiWindowManager$LayoutParams");
      int j = localClass.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(localClass);
      localObject = ((Class)localObject).getMethod("setExtraFlags", new Class[] { Integer.TYPE, Integer.TYPE });
      if (paramBoolean) {
        i = j;
      }
      ((Method)localObject).invoke(paramWindow, new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
      return;
    }
    catch (Throwable paramWindow)
    {
      ThrowableExtension.printStackTrace(paramWindow);
    }
  }
  
  public static void addImageMedia(Context paramContext, String paramString)
  {
    try
    {
      paramContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(paramString))));
      return;
    }
    catch (Exception paramContext)
    {
      Logger.w("ToolUtils", "add image media exception: " + paramContext);
    }
  }
  
  public static void addImageMedia2(Context paramContext, String paramString)
  {
    try
    {
      new a(paramContext, paramString).startScan();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static int areNotificationsEnabled(Context paramContext)
  {
    if (paramContext == null) {
      return -1;
    }
    try
    {
      Object localObject = (AppOpsManager)paramContext.getSystemService("appops");
      ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
      paramContext = paramContext.getApplicationContext().getPackageName();
      int i = localApplicationInfo.uid;
      localObject = b.on(localObject);
      int j = ((Integer)((b)localObject).field("OP_POST_NOTIFICATION", new Class[] { Integer.TYPE }).get()).intValue();
      i = ((Integer)((b)localObject).call("checkOpNoThrow", new Class[] { Integer.TYPE, Integer.TYPE, String.class }, new Object[] { Integer.valueOf(j), Integer.valueOf(i), paramContext }).get()).intValue();
      if (i == 0) {
        return 1;
      }
      return 0;
    }
    catch (Throwable paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
    return -1;
  }
  
  private static void b(boolean paramBoolean, Window paramWindow)
  {
    try
    {
      WindowManager.LayoutParams localLayoutParams = paramWindow.getAttributes();
      Field localField = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
      if (paramBoolean) {
        localField.set(localLayoutParams, Integer.valueOf(localField.getInt(localLayoutParams) | 0x200));
      }
      for (;;)
      {
        paramWindow.setAttributes(localLayoutParams);
        return;
        localField.set(localLayoutParams, Integer.valueOf(localField.getInt(localLayoutParams) & 0xFDFF));
      }
      return;
    }
    catch (Throwable paramWindow)
    {
      ThrowableExtension.printStackTrace(paramWindow);
    }
  }
  
  public static void clearDir(String paramString)
  {
    paramString = new File(paramString);
    if (!paramString.exists()) {
      return;
    }
    paramString = paramString.listFiles();
    int j = paramString.length;
    int i = 0;
    label27:
    if (i < j)
    {
      if (!paramString[i].isDirectory()) {
        break label57;
      }
      removeDir(paramString[i].getAbsolutePath());
    }
    for (;;)
    {
      i += 1;
      break label27;
      break;
      label57:
      if (paramString[i].isFile()) {
        paramString[i].delete();
      }
    }
  }
  
  public static void clearDir(String paramString, Set<String> paramSet)
  {
    paramString = new File(paramString);
    if (!paramString.exists()) {
      return;
    }
    paramString = paramString.listFiles();
    int j = paramString.length;
    int i = 0;
    label27:
    if (i < j)
    {
      if (!paramString[i].isDirectory()) {
        break label58;
      }
      removeDir(paramString[i].getAbsolutePath(), paramSet);
    }
    for (;;)
    {
      i += 1;
      break label27;
      break;
      label58:
      if (paramString[i].isFile())
      {
        String str = paramString[i].getName();
        if ((paramSet == null) || (!paramSet.contains(str))) {
          paramString[i].delete();
        }
      }
    }
  }
  
  public static void createShortCut(Context paramContext, Intent paramIntent, String paramString, Bitmap paramBitmap)
  {
    if ((paramContext == null) || (paramIntent == null) || (StringUtils.isEmpty(paramString)) || (paramBitmap == null)) {}
    for (;;)
    {
      return;
      if (!isShortcutInstalled(paramContext, paramString)) {
        try
        {
          Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
          localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
          localIntent.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
          localIntent.putExtra("duplicate", false);
          localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
          paramContext.sendBroadcast(localIntent);
          if (Logger.debug())
          {
            Logger.d("launcher_ad", "createShortCut intent " + paramIntent.toUri(0));
            return;
          }
        }
        catch (Exception paramContext) {}
      }
    }
  }
  
  public static void deleteShortCut(Context paramContext, Intent paramIntent, String paramString)
  {
    if ((paramContext == null) || (paramIntent == null) || (StringUtils.isEmpty(paramString))) {}
    for (;;)
    {
      return;
      try
      {
        Intent localIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
        localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
        localIntent.putExtra("duplicate", false);
        paramContext.sendBroadcast(localIntent);
        if (Logger.debug())
        {
          Logger.d("launcher_ad", "deleteShortCut intent " + paramIntent.toUri(0));
          return;
        }
      }
      catch (Exception paramContext) {}
    }
  }
  
  public static String getCacheDirPath(Context paramContext)
  {
    Object localObject = null;
    if (paramContext == null) {
      throw new NullPointerException("Context is NUll");
    }
    try
    {
      if (paramContext.getCacheDir() != null) {
        paramContext = paramContext.getCacheDir().getPath();
      }
      while (StringUtils.isEmpty(paramContext))
      {
        throw new NullPointerException("Cannot Create Cache Dir");
        File localFile = paramContext.getDir("/data/data/" + paramContext.getPackageName() + "/cache/", 0);
        paramContext = localObject;
        if (localFile != null) {
          paramContext = localFile.getPath();
        }
      }
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext = localObject;
      }
    }
  }
  
  public static String getCurProcessName(Context paramContext)
  {
    Object localObject = b;
    if (!StringUtils.isEmpty((String)localObject)) {
      return localObject;
    }
    try
    {
      int i = Process.myPid();
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        localObject = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (((ActivityManager.RunningAppProcessInfo)localObject).pid == i)
        {
          if (Logger.debug()) {
            Logger.d("Process", "processName = " + ((ActivityManager.RunningAppProcessInfo)localObject).processName);
          }
          b = ((ActivityManager.RunningAppProcessInfo)localObject).processName;
          paramContext = b;
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
      b = a();
    }
    return b;
  }
  
  public static String getDefaultBrowserString(Context paramContext)
  {
    try
    {
      Object localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).addCategory("android.intent.category.DEFAULT");
      ((Intent)localObject).addCategory("android.intent.category.BROWSABLE");
      j.a((Intent)localObject, Uri.parse("http://"), null);
      paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 65568);
      if (paramContext.size() > 0)
      {
        localObject = paramContext.iterator();
        while (((Iterator)localObject).hasNext())
        {
          ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
          if ((localResolveInfo != null) && (localResolveInfo.activityInfo != null) && ("com.android.browser".equals(localResolveInfo.activityInfo.packageName))) {
            return localResolveInfo.activityInfo.packageName + "/" + localResolveInfo.activityInfo.name;
          }
        }
        paramContext = (ResolveInfo)paramContext.get(0);
        if ((paramContext != null) && (paramContext.activityInfo != null))
        {
          paramContext = paramContext.activityInfo.packageName + "/" + paramContext.activityInfo.name;
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
    return "com.android.browser/com.android.browser.BrowserActivity";
  }
  
  public static long getDirectorySize(File paramFile, boolean paramBoolean)
  {
    long l1 = 0L;
    long l2 = l1;
    try
    {
      if (paramFile.exists()) {
        break label130;
      }
      l2 = l1;
      if (!paramFile.isDirectory()) {
        break label130;
      }
      return 0L;
    }
    catch (Throwable paramFile) {}
    l2 = l1;
    paramFile = paramFile.listFiles();
    l2 = l1;
    int j = paramFile.length;
    int i = 0;
    for (;;)
    {
      l2 = l1;
      long l3;
      if (i < j)
      {
        File localFile = paramFile[i];
        l2 = l1;
        if (localFile.isDirectory())
        {
          l2 = l1;
          l3 = l1 + getDirectorySize(localFile, paramBoolean);
        }
        else
        {
          l2 = l1;
          l3 = l1;
          if (localFile.isFile())
          {
            l2 = l1;
            l3 = localFile.length();
            l3 = l1 + l3;
          }
        }
      }
      else
      {
        return l2;
        label130:
        if (paramBoolean) {
          break;
        }
        break;
      }
      i += 1;
      l1 = l3;
    }
  }
  
  public static String getEmuiInfo()
  {
    return a("ro.build.version.emui");
  }
  
  public static String getFilesDirPath(Context paramContext)
  {
    Object localObject = null;
    if (paramContext == null) {
      throw new NullPointerException("Context is NUll");
    }
    try
    {
      if (paramContext.getFilesDir() != null) {
        paramContext = paramContext.getFilesDir().getPath();
      }
      while (StringUtils.isEmpty(paramContext))
      {
        throw new NullPointerException("Cannot Create Files Dir");
        File localFile = paramContext.getDir("/data/data/" + paramContext.getPackageName() + "/files/", 0);
        paramContext = localObject;
        if (localFile != null) {
          paramContext = localFile.getPath();
        }
      }
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext = localObject;
      }
    }
  }
  
  public static Intent getLaunchIntentForPackage(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramContext == null) {
      return paramContext;
    }
    if (!paramContext.hasCategory("android.intent.category.LAUNCHER"))
    {
      paramContext.addCategory("android.intent.category.LAUNCHER");
      Logger.d("ToolUtils", "add category LAUNCHER in launch intent");
    }
    paramContext.setPackage(null);
    paramContext.addFlags(2097152);
    paramContext.addFlags(268435456);
    return paramContext;
  }
  
  public static int getLocationMode(Context paramContext)
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
          return i | 0x4;
        }
      }
    }
    catch (Throwable paramContext)
    {
      return -1;
    }
    return j;
  }
  
  public static String getProviderAuthority(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (StringUtils.isEmpty(paramString))) {}
    for (;;)
    {
      return null;
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
    }
  }
  
  public static String getRunningTaskInfoString(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    try
    {
      Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(5);
      if (localObject == null) {
        return "";
      }
      paramContext = paramContext.getPackageName();
      StringBuilder localStringBuilder = new StringBuilder();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)((Iterator)localObject).next();
        if ((localRunningTaskInfo != null) && (localRunningTaskInfo.baseActivity != null) && (paramContext.equals(localRunningTaskInfo.baseActivity.getPackageName())))
        {
          localStringBuilder.append("id = ").append(localRunningTaskInfo.id).append(" ");
          localStringBuilder.append("description = ").append(localRunningTaskInfo.description).append(" ");
          localStringBuilder.append("number_of_activities = ").append(localRunningTaskInfo.numActivities).append(" ");
          localStringBuilder.append("number_of_running_activities = ").append(localRunningTaskInfo.numRunning).append(" ");
          localStringBuilder.append("topActivity = ").append(localRunningTaskInfo.topActivity.toString()).append(" ");
          localStringBuilder.append("baseActivity = ").append(localRunningTaskInfo.baseActivity.toString());
          paramContext = localStringBuilder.toString();
          return paramContext;
        }
      }
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static String getUserSerial(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getSystemService("user");
    if (paramContext == null)
    {
      Logger.e("ToolUtils", "userManager not exsit !!!");
      return null;
    }
    try
    {
      Object localObject = Process.class.getMethod("myUserHandle", (Class[])null).invoke(Process.class, (Object[])null);
      long l = ((Long)paramContext.getClass().getMethod("getSerialNumberForUser", new Class[] { localObject.getClass() }).invoke(paramContext, new Object[] { localObject })).longValue();
      return String.valueOf(l);
    }
    catch (NoSuchMethodException paramContext)
    {
      Logger.e("ToolUtils", "", paramContext);
      return null;
    }
    catch (IllegalArgumentException paramContext)
    {
      for (;;)
      {
        Logger.e("ToolUtils", "", paramContext);
      }
    }
    catch (IllegalAccessException paramContext)
    {
      for (;;)
      {
        Logger.e("ToolUtils", "", paramContext);
      }
    }
    catch (InvocationTargetException paramContext)
    {
      for (;;)
      {
        Logger.e("ToolUtils", "", paramContext);
      }
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
  }
  
  public static String handleZipEntryGetNameLeak(String paramString)
  {
    if (StringUtils.isEmpty(paramString)) {
      return paramString;
    }
    for (;;)
    {
      try
      {
        if (Logger.debug()) {
          Logger.d("Process", "before handle = " + paramString);
        }
        str1 = paramString.replaceAll(".." + File.separator, "");
        paramString = str1;
        str1 = paramString;
      }
      catch (Exception localException1)
      {
        String str1;
        String str2 = paramString;
        continue;
      }
      try
      {
        if (Logger.debug())
        {
          Logger.d("Process", "after handle = " + paramString);
          str1 = paramString;
        }
      }
      catch (Exception localException2)
      {
        String str3 = paramString;
      }
    }
    return str1;
  }
  
  public static void installShortcut(Context paramContext, String paramString)
  {
    if (paramContext == null) {}
    do
    {
      return;
      for (;;)
      {
        Object localObject2;
        ApplicationInfo localApplicationInfo;
        try
        {
          localIntent = getLaunchIntentForPackage(paramContext, paramString);
          if (localIntent == null) {
            break;
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
          Resources localResources;
          String str;
          Object localObject1;
          int i;
          Log.w("ToolUtils", "addAppShortcut failed: " + paramContext);
          return;
        }
        try
        {
          i = ((PackageManager)localObject2).getActivityInfo(localIntent.getComponent(), 0).labelRes;
          if (i > 0) {
            localObject1 = localResources.getString(i);
          }
        }
        catch (Resources.NotFoundException localNotFoundException)
        {
          CharSequence localCharSequence = ((PackageManager)localObject2).getApplicationLabel(localApplicationInfo);
        }
      }
    } while (localObject1 == null);
    localObject2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    ((Intent)localObject2).putExtra("android.intent.extra.shortcut.NAME", (CharSequence)localObject1);
    ((Intent)localObject2).putExtra("android.intent.extra.shortcut.INTENT", localIntent);
    ((Intent)localObject2).putExtra("launch_from", 1);
    localObject1 = new Intent.ShortcutIconResource();
    ((Intent.ShortcutIconResource)localObject1).packageName = paramString;
    ((Intent.ShortcutIconResource)localObject1).resourceName = str;
    ((Intent)localObject2).putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable)localObject1);
    ((Intent)localObject2).putExtra("duplicate", false);
    paramContext.sendBroadcast((Intent)localObject2);
  }
  
  public static boolean isApkInstalled(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null) || (StringUtils.isEmpty(paramString))) {}
    for (;;)
    {
      return false;
      try
      {
        paramString = new File(paramString);
        if (!paramString.exists()) {
          continue;
        }
        paramString = paramContext.getPackageManager().getPackageArchiveInfo(paramString.getAbsolutePath(), 1);
        if (paramString == null) {
          continue;
        }
        String str = paramString.packageName;
        int i = paramString.versionCode;
        try
        {
          paramContext = paramContext.getPackageManager().getPackageInfo(str, 1);
          if (paramContext == null) {
            continue;
          }
          int j = paramContext.versionCode;
          if (i > j) {
            continue;
          }
          return true;
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          for (;;)
          {
            paramContext = null;
          }
        }
        return false;
      }
      catch (Exception paramContext)
      {
        ThrowableExtension.printStackTrace(paramContext);
      }
    }
  }
  
  public static boolean isApplicationForeground(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (StringUtils.isEmpty(paramString))) {}
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      while (!paramContext.hasNext())
      {
        do
        {
          do
          {
            return false;
            paramContext = (ActivityManager)paramContext.getSystemService("activity");
            if (Build.VERSION.SDK_INT >= 21) {
              break;
            }
            paramContext = paramContext.getRunningTasks(1);
          } while (paramContext.isEmpty());
          paramContext = ((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity;
        } while ((paramContext == null) || (!paramString.equals(paramContext.getPackageName())));
        return true;
        paramContext = paramContext.getRunningAppProcesses().iterator();
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
    } while (!paramString.equals(localRunningAppProcessInfo.processName));
    if (localRunningAppProcessInfo.importance == 100) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isEmui(String paramString)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = getEmuiInfo();
    }
    if ((!TextUtils.isEmpty(str)) && (str.toLowerCase().startsWith("emotionui"))) {}
    while (isHuaweiDevice()) {
      return true;
    }
    return false;
  }
  
  public static boolean isFlyme()
  {
    return ((!StringUtils.isEmpty(Build.DISPLAY)) && (Build.DISPLAY.indexOf("Flyme") >= 0)) || ((!StringUtils.isEmpty(Build.USER)) && (Build.USER.equals("flyme")));
  }
  
  public static boolean isHuaweiDevice()
  {
    boolean bool2 = false;
    try
    {
      boolean bool1;
      if ((StringUtils.isEmpty(Build.BRAND)) || (!Build.BRAND.toLowerCase().startsWith("huawei")))
      {
        bool1 = bool2;
        if (!StringUtils.isEmpty(Build.MANUFACTURER))
        {
          boolean bool3 = Build.MANUFACTURER.toLowerCase().startsWith("huawei");
          bool1 = bool2;
          if (!bool3) {}
        }
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  public static boolean isInstalledApp(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null) {}
    do
    {
      return false;
      paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536);
    } while ((paramContext == null) || (paramContext.size() <= 0));
    return true;
  }
  
  public static boolean isInstalledApp(Context paramContext, String paramString)
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
      paramContext = paramContext.getPackageInfo(paramString, 16777216);
      bool1 = bool2;
      if (paramContext != null) {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isMainProcess(Context paramContext)
  {
    String str = getCurProcessName(paramContext);
    if ((str != null) && (str.contains(":"))) {}
    while ((str == null) || (!str.equals(paramContext.getPackageName()))) {
      return false;
    }
    return true;
  }
  
  public static long isMainProcessRetId(Context paramContext)
  {
    if (isMainProcess(paramContext)) {
      return Thread.currentThread().getId();
    }
    return 0L;
  }
  
  public static boolean isMessageProcess(Context paramContext)
  {
    paramContext = getCurProcessName(paramContext);
    return (paramContext != null) && (paramContext.endsWith(MESSAGE_PROCESS_SUFFIX));
  }
  
  public static boolean isMiui()
  {
    if (!sIsInited) {
      try
      {
        if (Class.forName("miui.os.Build") != null)
        {
          sIsMiui = true;
          sIsInited = true;
          boolean bool = sIsMiui;
          return bool;
        }
      }
      catch (Exception localException)
      {
        sIsInited = true;
      }
    }
    return sIsMiui;
  }
  
  public static boolean isNoDexProcess(Context paramContext)
  {
    paramContext = getCurProcessName(paramContext);
    return (paramContext != null) && (paramContext.endsWith(":nodex"));
  }
  
  public static boolean isPackageMatchApk(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || (StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {}
    for (;;)
    {
      return false;
      try
      {
        paramString1 = new File(paramString1);
        if (!paramString1.exists()) {
          continue;
        }
        paramString1 = paramContext.getPackageManager().getPackageArchiveInfo(paramString1.getAbsolutePath(), 1);
        if ((paramString1 == null) || (!paramString1.packageName.equals(paramString2))) {
          continue;
        }
        int i = paramString1.versionCode;
        try
        {
          paramContext = paramContext.getPackageManager().getPackageInfo(paramString2, 1);
          if (paramContext == null) {
            continue;
          }
          int j = paramContext.versionCode;
          if (i != j) {
            continue;
          }
          return true;
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          for (;;)
          {
            paramContext = null;
          }
        }
        return false;
      }
      catch (Exception paramContext)
      {
        ThrowableExtension.printStackTrace(paramContext);
      }
    }
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || (StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2))) {
      return false;
    }
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramContext.next();
          if (paramString1.equals(localRunningServiceInfo.service.getPackageName()))
          {
            boolean bool = paramString2.equals(localRunningServiceInfo.service.getClassName());
            if (bool) {
              return true;
            }
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      return false;
    }
    return false;
  }
  
  public static boolean isShortcutInstalled(Context paramContext, String paramString)
  {
    for (;;)
    {
      try
      {
        String str = a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
        Object localObject = str;
        if (str == null) {
          localObject = "com.android.launcher.settings";
        }
        localObject = Uri.parse("content://" + (String)localObject + "/favorites?notify=true");
        paramContext = paramContext.getContentResolver().query((Uri)localObject, new String[] { "title" }, "title=?", new String[] { paramString }, null);
        if (paramContext != null)
        {
          int i = paramContext.getCount();
          if (i > 0)
          {
            bool = true;
            if (paramContext == null) {}
          }
        }
        boolean bool = false;
      }
      catch (Exception paramContext)
      {
        try
        {
          paramContext.close();
          return bool;
        }
        catch (Exception paramContext)
        {
          return bool;
        }
        paramContext = paramContext;
        return false;
      }
    }
  }
  
  public static boolean isSubProcess(Context paramContext, String paramString)
  {
    paramContext = getCurProcessName(paramContext);
    return (paramContext != null) && (paramContext.endsWith(paramString));
  }
  
  public static void openInstalledApp(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (StringUtils.isEmpty(paramString))) {
      return;
    }
    try
    {
      paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.addFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void removeDir(String paramString)
  {
    paramString = new File(paramString);
    if ((paramString.exists()) && (paramString.isDirectory()))
    {
      File[] arrayOfFile = paramString.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      if (i < j)
      {
        if (arrayOfFile[i].isDirectory()) {
          removeDir(arrayOfFile[i].getAbsolutePath());
        }
        for (;;)
        {
          i += 1;
          break;
          arrayOfFile[i].delete();
        }
      }
      paramString.delete();
    }
  }
  
  public static void removeDir(String paramString, Set<String> paramSet)
  {
    paramString = new File(paramString);
    if ((paramString.exists()) && (paramString.isDirectory()))
    {
      paramString = paramString.listFiles();
      int j = paramString.length;
      int i = 0;
      if (i < j)
      {
        if (paramString[i].isDirectory()) {
          removeDir(paramString[i].getAbsolutePath(), paramSet);
        }
        for (;;)
        {
          i += 1;
          break;
          String str = paramString[i].getName();
          if ((paramSet == null) || (!paramSet.contains(str))) {
            paramString[i].delete();
          }
        }
      }
    }
  }
  
  public static void runApplication(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool = false;
    if (!StringUtils.isEmpty(paramString1)) {
      bool = isInstalledApp(paramContext, paramString1);
    }
    if (bool)
    {
      Intent localIntent = getLaunchIntentForPackage(paramContext, paramString1);
      if (localIntent != null) {
        paramContext.startActivity(localIntent);
      }
    }
    do
    {
      return;
      if (!StringUtils.isEmpty(paramString2)) {
        try
        {
          paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString2)));
          return;
        }
        catch (Exception paramString2) {}
      }
    } while (StringUtils.isEmpty(paramString1));
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString1));
    paramString1.addFlags(268435456);
    try
    {
      paramContext.startActivity(paramString1);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void setCanSetStatusBar(boolean paramBoolean)
  {
    c = paramBoolean;
  }
  
  public static void setMessageProcessSuffix(String paramString)
  {
    MESSAGE_PROCESS_SUFFIX = paramString;
  }
  
  public static void startPhoneScreen(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (StringUtils.isEmpty(paramString))) {
      return;
    }
    try
    {
      paramString = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + Uri.encode(paramString)));
      if (!(paramContext instanceof Activity)) {
        paramString.setFlags(268435456);
      }
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  @TargetApi(21)
  public static void trySetStatusBar(Context paramContext, Window paramWindow, int paramInt, boolean paramBoolean)
  {
    if ((paramContext == null) || (paramWindow == null)) {}
    do
    {
      return;
      try
      {
        if (!c)
        {
          paramWindow.clearFlags(Integer.MIN_VALUE);
          return;
        }
      }
      catch (Throwable paramContext)
      {
        ThrowableExtension.printStackTrace(paramContext);
        return;
      }
    } while (Build.VERSION.SDK_INT < 21);
    if (isMiui()) {
      if (paramBoolean) {
        a(false, paramWindow);
      }
    }
    for (;;)
    {
      paramWindow.clearFlags(67108864);
      paramWindow.addFlags(Integer.MIN_VALUE);
      paramWindow.setStatusBarColor(paramContext.getResources().getColor(paramInt));
      return;
      a(true, paramWindow);
      continue;
      if (isFlyme())
      {
        if (paramBoolean) {
          b(false, paramWindow);
        } else {
          b(true, paramWindow);
        }
      }
      else
      {
        View localView = paramWindow.getDecorView();
        int i = localView.getSystemUiVisibility();
        if (paramBoolean) {
          localView.setSystemUiVisibility(i & 0xDFFF);
        } else {
          localView.setSystemUiVisibility(i | 0x2000);
        }
      }
    }
  }
  
  public static void trySetStatusBarWithFullScreen(Window paramWindow)
  {
    if (paramWindow == null) {}
    for (;;)
    {
      return;
      try
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramWindow.clearFlags(201326592);
          paramWindow.getDecorView().setSystemUiVisibility(1792);
          paramWindow.addFlags(Integer.MIN_VALUE);
          paramWindow.setStatusBarColor(0);
          return;
        }
      }
      catch (Throwable paramWindow)
      {
        ThrowableExtension.printStackTrace(paramWindow);
      }
    }
  }
  
  private static class a
    implements MediaScannerConnection.MediaScannerConnectionClient
  {
    private MediaScannerConnection a;
    private Context b;
    private String c;
    
    public a(Context paramContext, String paramString)
    {
      this.b = paramContext;
      this.c = paramString;
    }
    
    public void onMediaScannerConnected()
    {
      try
      {
        this.a.scanFile(this.c, "image/*");
        return;
      }
      catch (Exception localException) {}
    }
    
    public void onScanCompleted(String paramString, Uri paramUri)
    {
      if (this.a != null)
      {
        if (this.a.isConnected()) {
          this.a.disconnect();
        }
        this.a = null;
      }
    }
    
    public void startScan()
    {
      if ((this.a != null) && (this.a.isConnected())) {
        this.a.disconnect();
      }
      this.a = new MediaScannerConnection(this.b, this);
      this.a.connect();
    }
  }
}
