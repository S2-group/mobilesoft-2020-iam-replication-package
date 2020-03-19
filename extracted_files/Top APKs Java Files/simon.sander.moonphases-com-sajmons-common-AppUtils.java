package com.sajmons.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class AppUtils
{
  private static String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";
  private static String uniqueID = null;
  
  public AppUtils() {}
  
  public static String capitalize(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException("string");
    }
    if (paramString.equals("")) {
      throw new NullPointerException("string");
    }
    return Character.toUpperCase(paramString.charAt(0)) + paramString.substring(1);
  }
  
  public static Address getAddress(Context paramContext, double paramDouble1, double paramDouble2)
  {
    paramContext = new Geocoder(paramContext, Locale.getDefault());
    try
    {
      paramContext = paramContext.getFromLocation(paramDouble1, paramDouble2, 1);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = (Address)paramContext.get(0);
        return paramContext;
      }
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getAndridMarketUrl(Context paramContext)
  {
    return "http://market.android.com/details?id=" + paramContext.getPackageName();
  }
  
  public static Location getLastKnownLocation(Context paramContext)
  {
    Object localObject2 = null;
    try
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      Object localObject1 = localObject2;
      if (ContextCompat.checkSelfPermission(paramContext, "android.permission.WRITE_CALENDAR") == 0)
      {
        localObject1 = localObject2;
        if (localLocationManager != null) {
          localObject1 = localLocationManager.getLastKnownLocation("network");
        }
      }
      return localObject1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Intent getLaunchIntent(Context paramContext, String paramString)
  {
    Intent localIntent2 = getLaunchIntentForPackage(paramContext, paramString);
    Intent localIntent1 = localIntent2;
    if (localIntent2 == null) {
      localIntent1 = getLaunchIntentForAndroidMarket(paramContext, paramString);
    }
    return localIntent1;
  }
  
  public static Intent getLaunchIntentForAndroidMarket(Context paramContext, String paramString)
  {
    return new Intent("android.intent.action.VIEW", Uri.parse(String.format("market://details?id=%s", new Object[] { paramString })));
  }
  
  public static Intent getLaunchIntentForPackage(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
  }
  
  public static Bitmap getResizedBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f1 = paramInt2 / i;
    float f2 = paramInt1 / j;
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f1, f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, false);
  }
  
  public static Intent getSelfLaunchIntentForAndroidMarket(Context paramContext)
  {
    return new Intent("android.intent.action.VIEW", Uri.parse(String.format("market://details?id=%s", new Object[] { paramContext.getPackageName() })));
  }
  
  public static int getTimeZoneOffsetInHours()
  {
    Calendar localCalendar = Calendar.getInstance();
    return -(localCalendar.get(15) + localCalendar.get(16)) / 60000 / 60;
  }
  
  public static String getVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static String id(Context paramContext)
  {
    try
    {
      if (uniqueID == null)
      {
        paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
        uniqueID = paramContext.getString(PREF_UNIQUE_ID, null);
        if (uniqueID == null)
        {
          uniqueID = UUID.randomUUID().toString();
          paramContext = paramContext.edit();
          paramContext.putString(PREF_UNIQUE_ID, uniqueID);
          paramContext.commit();
        }
      }
      paramContext = uniqueID;
      return paramContext;
    }
    finally {}
  }
  
  public static boolean isDayTime()
  {
    int i = Calendar.getInstance().get(11);
    return (i > 6) && (i < 19);
  }
  
  public static boolean isEmptyString(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }
  
  public static boolean isOnWifi(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected()) && ((paramContext.getType() == 1) || (paramContext.getType() == 6) || (paramContext.getType() == 9) || (paramContext.getType() == 8));
  }
  
  public static boolean isOnline(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean packageExists(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)paramContext.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  /* Error */
  public static String readTextFile(String paramString)
  {
    // Byte code:
    //   0: new 305	java/io/File
    //   3: dup
    //   4: invokestatic 311	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   7: aload_0
    //   8: invokespecial 314	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   11: astore_0
    //   12: new 37	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 38	java/lang/StringBuilder:<init>	()V
    //   19: astore_1
    //   20: new 316	java/io/BufferedReader
    //   23: dup
    //   24: new 318	java/io/FileReader
    //   27: dup
    //   28: aload_0
    //   29: invokespecial 321	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   32: invokespecial 324	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   35: astore_0
    //   36: aload_0
    //   37: invokevirtual 327	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   40: astore_2
    //   41: aload_2
    //   42: ifnull +22 -> 64
    //   45: aload_1
    //   46: aload_2
    //   47: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: goto -15 -> 36
    //   54: astore_0
    //   55: aload_0
    //   56: invokevirtual 328	java/io/FileNotFoundException:printStackTrace	()V
    //   59: aload_1
    //   60: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: areturn
    //   64: aload_0
    //   65: invokevirtual 331	java/io/BufferedReader:close	()V
    //   68: goto -9 -> 59
    //   71: astore_0
    //   72: aload_0
    //   73: invokevirtual 97	java/io/IOException:printStackTrace	()V
    //   76: goto -17 -> 59
    //   79: astore_0
    //   80: goto -8 -> 72
    //   83: astore_0
    //   84: goto -29 -> 55
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	paramString	String
    //   19	41	1	localStringBuilder	StringBuilder
    //   40	7	2	str	String
    // Exception table:
    //   from	to	target	type
    //   36	41	54	java/io/FileNotFoundException
    //   45	51	54	java/io/FileNotFoundException
    //   64	68	54	java/io/FileNotFoundException
    //   20	36	71	java/io/IOException
    //   36	41	79	java/io/IOException
    //   45	51	79	java/io/IOException
    //   64	68	79	java/io/IOException
    //   20	36	83	java/io/FileNotFoundException
  }
  
  public static Drawable resizeImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), paramInt1);
    paramInt1 = localBitmap.getWidth();
    int i = localBitmap.getHeight();
    float f1 = paramInt2 / paramInt1;
    float f2 = paramInt3 / i;
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f1, f2);
    localBitmap = Bitmap.createBitmap(localBitmap, 0, 0, paramInt1, i, localMatrix, true);
    return new BitmapDrawable(paramContext.getResources(), localBitmap);
  }
}
