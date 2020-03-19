package com.zalivka.commons.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.zalivka.commons.R.bool;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

public class EnvUtils
{
  public EnvUtils() {}
  
  public static boolean debug()
  {
    PackageManager localPackageManager = StaticContextHolder.mCtx.getPackageManager();
    boolean bool = false;
    try
    {
      int i = localPackageManager.getApplicationInfo("ru.jecklandin.stickman", 0).flags;
      if ((i & 0x2) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return false;
  }
  
  /* Error */
  public static void dumpPic(android.graphics.Bitmap paramBitmap)
  {
    // Byte code:
    //   0: new 50	java/io/File
    //   3: dup
    //   4: invokestatic 56	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   7: ldc 58
    //   9: invokespecial 61	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   12: astore 5
    //   14: aload 5
    //   16: invokevirtual 64	java/io/File:delete	()Z
    //   19: pop
    //   20: aconst_null
    //   21: astore_3
    //   22: aconst_null
    //   23: astore 4
    //   25: aconst_null
    //   26: astore_2
    //   27: aload_2
    //   28: astore_1
    //   29: aload 5
    //   31: invokevirtual 67	java/io/File:createNewFile	()Z
    //   34: pop
    //   35: aload_2
    //   36: astore_1
    //   37: new 69	java/io/FileOutputStream
    //   40: dup
    //   41: aload 5
    //   43: invokespecial 72	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   46: astore_2
    //   47: aload_0
    //   48: getstatic 78	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   51: bipush 100
    //   53: aload_2
    //   54: invokevirtual 84	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   57: pop
    //   58: aload_2
    //   59: invokestatic 90	com/zalivka/commons/utils/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   62: return
    //   63: astore_0
    //   64: aload_2
    //   65: astore_1
    //   66: goto +46 -> 112
    //   69: astore_1
    //   70: aload_2
    //   71: astore_0
    //   72: aload_1
    //   73: astore_2
    //   74: goto +18 -> 92
    //   77: astore_1
    //   78: aload_2
    //   79: astore_0
    //   80: aload_1
    //   81: astore_2
    //   82: goto +19 -> 101
    //   85: astore_0
    //   86: goto +26 -> 112
    //   89: astore_2
    //   90: aload_3
    //   91: astore_0
    //   92: aload_0
    //   93: astore_1
    //   94: aload_2
    //   95: invokevirtual 91	java/io/IOException:printStackTrace	()V
    //   98: goto +9 -> 107
    //   101: aload_0
    //   102: astore_1
    //   103: aload_2
    //   104: invokevirtual 92	java/io/FileNotFoundException:printStackTrace	()V
    //   107: aload_0
    //   108: invokestatic 90	com/zalivka/commons/utils/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   111: return
    //   112: aload_1
    //   113: invokestatic 90	com/zalivka/commons/utils/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   116: aload_0
    //   117: athrow
    //   118: astore_2
    //   119: aload 4
    //   121: astore_0
    //   122: goto -21 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	paramBitmap	android.graphics.Bitmap
    //   28	38	1	localObject1	Object
    //   69	4	1	localIOException1	java.io.IOException
    //   77	4	1	localFileNotFoundException1	java.io.FileNotFoundException
    //   93	20	1	localBitmap	android.graphics.Bitmap
    //   26	56	2	localObject2	Object
    //   89	15	2	localIOException2	java.io.IOException
    //   118	1	2	localFileNotFoundException2	java.io.FileNotFoundException
    //   21	70	3	localObject3	Object
    //   23	97	4	localObject4	Object
    //   12	30	5	localFile	File
    // Exception table:
    //   from	to	target	type
    //   47	58	63	finally
    //   47	58	69	java/io/IOException
    //   47	58	77	java/io/FileNotFoundException
    //   29	35	85	finally
    //   37	47	85	finally
    //   94	98	85	finally
    //   103	107	85	finally
    //   29	35	89	java/io/IOException
    //   37	47	89	java/io/IOException
    //   29	35	118	java/io/FileNotFoundException
    //   37	47	118	java/io/FileNotFoundException
  }
  
  public static boolean ensureActionForUser(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("first.action", 0);
    if (paramContext.contains(paramString)) {
      return false;
    }
    paramContext.edit().putLong(paramString, System.currentTimeMillis()).apply();
    return true;
  }
  
  public static long firstInstallTime()
  {
    try
    {
      long l = StaticContextHolder.mCtx.getPackageManager().getPackageInfo(StaticContextHolder.mCtx.getPackageName(), 0).firstInstallTime;
      return l;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return 0L;
  }
  
  public static String getRealPathFromURI(Uri paramUri)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "_data";
    Cursor localCursor = StaticContextHolder.mCtx.getContentResolver().query(paramUri, arrayOfString, null, null, null);
    if (localCursor != null)
    {
      localCursor.moveToFirst();
      return localCursor.getString(localCursor.getColumnIndex(arrayOfString[0]));
    }
    try
    {
      paramUri = new File(new URI(paramUri.toString())).getAbsolutePath();
      return paramUri;
    }
    catch (URISyntaxException paramUri)
    {
      Log.e("EnvUtils", "URI ERROR ", paramUri);
    }
    return null;
  }
  
  public static int getVersionCode()
  {
    try
    {
      int i = StaticContextHolder.mCtx.getPackageManager().getPackageInfo(StaticContextHolder.mCtx.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return -1;
  }
  
  public static String getVersionName()
  {
    try
    {
      String str = StaticContextHolder.mCtx.getPackageManager().getPackageInfo(StaticContextHolder.mCtx.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
  
  public static boolean hasVibrator()
  {
    return ((Vibrator)StaticContextHolder.mCtx.getSystemService("vibrator")).hasVibrator();
  }
  
  public static void installPodzalivka(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setData(Uri.parse("market://details?id=com.zalivka.fingerpaint"));
    paramContext.startActivity(localIntent);
  }
  
  public static void installRigid(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setData(Uri.parse("market://details?id=com.zalivka.director"));
    paramContext.startActivity(localIntent);
  }
  
  private boolean isDeviceOnline(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean isFreedomInstalled()
  {
    Iterator localIterator = StaticContextHolder.mCtx.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      String str = ((ApplicationInfo)localIterator.next()).packageName;
      if ((str.contains("cc.madkite.freedom")) || (str.contains("madkite.freedom"))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isLandPhone(Context paramContext)
  {
    return (!isTablet(paramContext)) && (isLandscape());
  }
  
  public static boolean isLandscape()
  {
    return StaticContextHolder.mCtx.getResources().getBoolean(R.bool.is_landscape);
  }
  
  public static boolean isLandscape(Activity paramActivity)
  {
    return paramActivity.getRequestedOrientation() == 0;
  }
  
  public static boolean isMoreThanOreo()
  {
    return Build.VERSION.SDK_INT >= 26;
  }
  
  public static boolean isPortPhone(Context paramContext)
  {
    return (!isTablet(paramContext)) && (!isLandscape());
  }
  
  public static boolean isRigidInstalled()
  {
    try
    {
      StaticContextHolder.mCtx.getPackageManager().getApplicationInfo("com.zalivka.director", 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return false;
  }
  
  public static boolean isTablet()
  {
    return StaticContextHolder.mCtx.getResources().getBoolean(R.bool.isTablet);
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return paramContext.getResources().getBoolean(R.bool.isTablet);
  }
  
  public static long lastUpdateTime()
  {
    try
    {
      long l = StaticContextHolder.mCtx.getPackageManager().getPackageInfo(StaticContextHolder.mCtx.getPackageName(), 0).lastUpdateTime;
      return l;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return 0L;
  }
  
  public static void mediascan(String paramString)
  {
    MediaScannerConnection.scanFile(StaticContextHolder.mCtx, new String[] { paramString }, null, null);
  }
  
  public static void setBasicOrientation(Activity paramActivity)
  {
    paramActivity.setRequestedOrientation(0);
  }
  
  public static boolean startActivityForResultSafely(Fragment paramFragment, Intent paramIntent, int paramInt)
  {
    if (paramIntent.resolveActivity(paramFragment.getActivity().getPackageManager()) != null)
    {
      paramFragment.startActivityForResult(paramIntent, paramInt);
      return true;
    }
    return false;
  }
  
  public static boolean startActivityForResultSafely(FragmentActivity paramFragmentActivity, Intent paramIntent, int paramInt)
  {
    if (paramIntent.resolveActivity(paramFragmentActivity.getPackageManager()) != null)
    {
      paramFragmentActivity.startActivityForResult(paramIntent, paramInt);
      return true;
    }
    return false;
  }
  
  public static boolean startActivitySafely(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.resolveActivity(paramContext.getPackageManager()) != null)
    {
      paramContext.startActivity(paramIntent);
      return true;
    }
    return false;
  }
}
