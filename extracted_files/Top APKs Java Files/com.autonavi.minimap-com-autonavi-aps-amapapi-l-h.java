package com.autonavi.aps.amapapi.l;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.b;
import com.autonavi.aps.amapapi.b.a;
import com.autonavi.aps.amapapi.b.c;
import com.autonavi.aps.amapapi.i.k;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class h
{
  private static int a = 0;
  private static String[] b = null;
  private static Hashtable<String, Long> c = new Hashtable();
  
  public static float a(b paramB1, b paramB2)
  {
    return a(new double[] { paramB1.c(), paramB1.b(), paramB2.c(), paramB2.b() });
  }
  
  public static float a(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length != 4) {
      return 0.0F;
    }
    float[] arrayOfFloat = new float[1];
    Location.distanceBetween(paramArrayOfDouble[0], paramArrayOfDouble[1], paramArrayOfDouble[2], paramArrayOfDouble[3], arrayOfFloat);
    return arrayOfFloat[0];
  }
  
  public static int a(int paramInt)
  {
    return paramInt * 2 - 113;
  }
  
  public static int a(int paramInt1, int paramInt2)
  {
    return new Random().nextInt(paramInt2 - paramInt1 + 1) + paramInt1;
  }
  
  public static int a(CellLocation paramCellLocation, Context paramContext)
  {
    if (a(paramContext)) {}
    while (paramCellLocation == null) {
      return 9;
    }
    if ((paramCellLocation instanceof GsmCellLocation)) {
      return 1;
    }
    try
    {
      Class.forName("android.telephony.cdma.CdmaCellLocation");
      return 2;
    }
    catch (Exception paramCellLocation) {}
    return 9;
  }
  
  public static long a()
  {
    return System.currentTimeMillis();
  }
  
  public static Object a(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return null;
    }
    try
    {
      paramContext = paramContext.getApplicationContext().getSystemService(paramString);
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  private static String a(PackageInfo paramPackageInfo)
  {
    return null;
  }
  
  public static void a(Object paramObject) {}
  
  public static void a(String paramString1, boolean paramBoolean, String paramString2) {}
  
  public static void a(Throwable paramThrowable) {}
  
  public static void a(Object... paramVarArgs) {}
  
  public static boolean a(double paramDouble)
  {
    if (paramDouble > 180.0D) {}
    while (paramDouble < -180.0D) {
      return false;
    }
    return true;
  }
  
  public static boolean a(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getContentResolver();
    if (c() < 17) {}
    for (;;)
    {
      try
      {
        i = ((Integer)f.a("android.provider.Settings$System", "getInt", new Object[] { paramContext, ((String)f.a("android.provider.Settings$System", "AIRPLANE_MODE_ON")).toString() }, new Class[] { ContentResolver.class, String.class })).intValue();
        return i == 1;
      }
      catch (Exception paramContext)
      {
        int i;
        continue;
      }
      try
      {
        i = ((Integer)f.a("android.provider.Settings$Global", "getInt", new Object[] { paramContext, ((String)f.a("android.provider.Settings$Global", "AIRPLANE_MODE_ON")).toString() }, new Class[] { ContentResolver.class, String.class })).intValue();
        return i == 1;
      }
      catch (Exception paramContext) {}
    }
    return false;
  }
  
  public static boolean a(Context paramContext, Location paramLocation)
  {
    if (paramContext == null) {}
    while ("sdk".equalsIgnoreCase(f())) {
      return false;
    }
    Object localObject1 = Settings.Secure.getString(paramContext.getContentResolver(), "mock_location");
    Object localObject2;
    boolean bool2;
    if ((!TextUtils.isEmpty((CharSequence)localObject1)) && (!((String)localObject1).equals("0")))
    {
      localObject1 = paramContext.getPackageManager();
      localObject2 = ((PackageManager)localObject1).getInstalledApplications(128);
      paramContext = paramContext.getPackageName();
      localObject2 = ((List)localObject2).iterator();
      bool2 = false;
    }
    for (;;)
    {
      boolean bool1 = bool2;
      ApplicationInfo localApplicationInfo;
      if (((Iterator)localObject2).hasNext())
      {
        localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
        bool1 = bool2;
        if (bool2) {}
      }
      try
      {
        String[] arrayOfString = ((PackageManager)localObject1).getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
        if (arrayOfString == null) {
          continue;
        }
        int j = arrayOfString.length;
        int i = 0;
        while (i < j)
        {
          if (arrayOfString[i].equals("android.permission.ACCESS_MOCK_LOCATION"))
          {
            bool1 = localApplicationInfo.packageName.equals(paramContext);
            if (bool1) {
              break;
            }
            bool2 = true;
            break;
          }
          i += 1;
        }
        bool1 = false;
        bool2 = bool1;
        if (c() >= 18)
        {
          bool2 = bool1;
          if (!a(paramLocation)) {}
        }
        try
        {
          bool2 = String.valueOf(f.a(paramLocation, "isFromMockProvider", new Object[0])).equals("true");
          return bool2;
        }
        catch (Exception paramContext)
        {
          for (;;)
          {
            bool2 = bool1;
          }
        }
      }
      catch (Exception localException) {}
    }
  }
  
  public static boolean a(Location paramLocation)
  {
    if (paramLocation == null) {}
    for (;;)
    {
      return false;
      boolean bool1 = true;
      try
      {
        double d1 = paramLocation.getLongitude();
        double d2 = paramLocation.getLatitude();
        boolean bool2 = "gps".equals(paramLocation.getProvider());
        boolean bool3 = paramLocation.hasAccuracy();
        if (((d1 != 0.0D) || (d2 != 0.0D)) && (d1 <= 180.0D) && (d2 <= 90.0D) && (d1 >= -180.0D) && (d2 >= -90.0D) && ((!bool2) || (!bool3) || (paramLocation.getAccuracy() >= -1.0E-8F)) && ((bool2) || (!bool3) || (paramLocation.getAccuracy() > 0.0F)))
        {
          if (!Double.isNaN(d2))
          {
            bool2 = Double.isNaN(d1);
            if (!bool2) {}
          }
          else
          {
            bool1 = false;
          }
          return bool1;
        }
      }
      catch (Throwable paramLocation) {}
    }
    return false;
  }
  
  public static boolean a(LocationManager paramLocationManager)
  {
    if (paramLocationManager == null) {}
    do
    {
      return false;
      paramLocationManager = paramLocationManager.getAllProviders();
    } while (paramLocationManager == null);
    return paramLocationManager.contains("gps");
  }
  
  public static boolean a(ScanResult paramScanResult)
  {
    if ((paramScanResult == null) || (TextUtils.isEmpty(paramScanResult.BSSID))) {}
    while ((paramScanResult.BSSID.equals("00:00:00:00:00:00")) || (paramScanResult.BSSID.contains(" :"))) {
      return false;
    }
    return true;
  }
  
  public static boolean a(b paramB)
  {
    boolean bool = true;
    if ((paramB != null) && (!paramB.i().equals("8")) && (!paramB.i().equals("5")) && (!paramB.i().equals("6")))
    {
      double d1 = paramB.b();
      double d2 = paramB.c();
      float f = paramB.e();
      if ((d1 != 0.0D) || (d2 != 0.0D) || (f != 0.0D))
      {
        if ((d1 > 180.0D) || (d2 > 90.0D)) {
          bool = false;
        }
        while ((d1 >= -180.0D) && (d2 >= -90.0D)) {
          return bool;
        }
      }
    }
    return false;
  }
  
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (!TextUtils.isDigitsOnly(paramString)) {
      return false;
    }
    return ",111,123,134,199,202,204,206,208,212,213,214,216,218,219,220,222,225,226,228,230,231,232,234,235,238,240,242,244,246,247,248,250,255,257,259,260,262,266,268,270,272,274,276,278,280,282,283,284,286,288,289,290,292,293,294,295,297,302,308,310,311,312,313,314,315,316,310,330,332,334,338,340,342,344,346,348,350,352,354,356,358,360,362,363,364,365,366,368,370,372,374,376,400,401,402,404,405,406,410,412,413,414,415,416,417,418,419,420,421,422,424,425,426,427,428,429,430,431,432,434,436,437,438,440,441,450,452,454,455,456,457,466,467,470,472,502,505,510,514,515,520,525,528,530,534,535,536,537,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,555,560,598,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,645,646,647,648,649,650,651,652,653,654,655,657,659,665,702,704,706,708,710,712,714,716,722,724,730,732,734,736,738,740,742,744,746,748,750,850,901,".contains("," + paramString + ",");
  }
  
  public static boolean a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramString.equals("0")) || (paramString.equals("460")) || (paramString.equals("461"))) {}
    while (((paramBoolean1) && ((paramString.equals("454")) || (paramString.equals("455")))) || ((paramBoolean2) && (paramString.equals("466")))) {
      return true;
    }
    return false;
  }
  
  public static boolean a(JSONObject paramJSONObject, String paramString)
  {
    return (paramJSONObject != null) && (paramJSONObject.has(paramString));
  }
  
  public static boolean a(JSONObject paramJSONObject1, JSONObject paramJSONObject2, String paramString)
  {
    boolean bool1;
    if ((paramJSONObject1 != null) && (paramJSONObject2 != null))
    {
      bool1 = paramJSONObject1.has(paramString);
      boolean bool2 = paramJSONObject2.has(paramString);
      if ((!bool1) && (!bool2)) {
        return true;
      }
      if ((!bool1) || (!bool2)) {}
    }
    Object localObject;
    do
    {
      try
      {
        paramJSONObject1 = paramJSONObject1.get(paramString);
        paramJSONObject2 = paramJSONObject2.get(paramString);
        paramString = paramJSONObject1.getClass();
        localObject = paramJSONObject2.getClass();
        if (!paramJSONObject1.equals(paramJSONObject2)) {
          continue;
        }
        return true;
      }
      catch (JSONException paramJSONObject1) {}
      if ((paramJSONObject1 instanceof JSONObject))
      {
        paramJSONObject1 = (JSONObject)paramJSONObject1;
        paramJSONObject2 = (JSONObject)paramJSONObject2;
        if (paramJSONObject1.length() != paramJSONObject2.length()) {
          return false;
        }
        paramString = paramJSONObject1.keys();
      }
      for (;;)
      {
        if (paramString.hasNext())
        {
          localObject = (String)paramString.next();
          if (!paramJSONObject2.has((String)localObject)) {
            return false;
          }
          bool1 = paramJSONObject2.get((String)localObject).equals(paramJSONObject1.get((String)localObject));
          if (!bool1) {
            return false;
          }
        }
        else
        {
          return true;
          return false;
        }
      }
    } while (paramString == localObject);
    return false;
  }
  
  public static byte[] a(byte[] paramArrayOfByte)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localObject1 = localObject2;
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localObject1 = localObject2;
      localGZIPOutputStream.write(paramArrayOfByte);
      localObject1 = localObject2;
      localGZIPOutputStream.close();
      localObject1 = localObject2;
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      localObject1 = paramArrayOfByte;
      localByteArrayOutputStream.close();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte) {}
    return localObject1;
  }
  
  public static String[] a(TelephonyManager paramTelephonyManager)
  {
    String str = null;
    if (paramTelephonyManager != null) {
      str = paramTelephonyManager.getNetworkOperator();
    }
    paramTelephonyManager = new String[2];
    paramTelephonyManager[0] = "0";
    paramTelephonyManager[1] = "0";
    int i;
    if (TextUtils.isEmpty(str)) {
      i = 0;
    }
    for (;;)
    {
      if (i != 0)
      {
        paramTelephonyManager[0] = str.substring(0, 3);
        char[] arrayOfChar = str.substring(3).toCharArray();
        i = 0;
        for (;;)
        {
          if ((i < arrayOfChar.length) && (Character.isDigit(arrayOfChar[i])))
          {
            i += 1;
            continue;
            if (!TextUtils.isDigitsOnly(str))
            {
              i = 0;
              break;
            }
            if (str.length() > 4) {
              break label201;
            }
            i = 0;
            break;
          }
        }
        paramTelephonyManager[1] = str.substring(3, i + 3);
      }
      try
      {
        i = Integer.parseInt(paramTelephonyManager[0]);
        if (i == 0) {
          paramTelephonyManager[0] = "0";
        }
        if ((!paramTelephonyManager[0].equals("0")) && (!paramTelephonyManager[1].equals("0")))
        {
          b = paramTelephonyManager;
          return paramTelephonyManager;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          i = 0;
        }
        if ((paramTelephonyManager[0].equals("0")) && (paramTelephonyManager[1].equals("0")) && (b != null)) {
          return b;
        }
        return paramTelephonyManager;
      }
      label201:
      i = 1;
    }
  }
  
  public static long b()
  {
    return SystemClock.elapsedRealtime();
  }
  
  public static String b(Context paramContext)
  {
    CharSequence localCharSequence = null;
    if (paramContext == null) {
      return null;
    }
    if (!TextUtils.isEmpty(a.G)) {
      return a.G;
    }
    Object localObject1 = paramContext.getApplicationContext().getPackageName();
    try
    {
      localObject1 = paramContext.getPackageManager().getPackageInfo((String)localObject1, 64);
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          if (TextUtils.isEmpty(a.I)) {
            a.I = a((PackageInfo)localObject1);
          }
          StringBuilder localStringBuilder = new StringBuilder();
          if (localObject1 != null)
          {
            if (((PackageInfo)localObject1).applicationInfo != null) {
              localCharSequence = ((PackageInfo)localObject1).applicationInfo.loadLabel(paramContext.getPackageManager());
            }
            if (localCharSequence != null) {
              localStringBuilder.append(localCharSequence.toString());
            }
            if (!TextUtils.isEmpty(((PackageInfo)localObject1).versionName)) {
              localStringBuilder.append(((PackageInfo)localObject1).versionName);
            }
          }
          if (!TextUtils.isEmpty(a.g)) {
            localStringBuilder.append(",").append(a.g);
          }
          if (!TextUtils.isEmpty(a.I)) {
            localStringBuilder.append(",").append(a.I);
          }
          return localStringBuilder.toString();
          localException1 = localException1;
          Object localObject2 = null;
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  public static boolean b(double paramDouble)
  {
    if (paramDouble > 90.0D) {}
    while (paramDouble < -90.0D) {
      return false;
    }
    return true;
  }
  
  public static boolean b(LocationManager paramLocationManager)
  {
    if (paramLocationManager == null) {}
    do
    {
      return false;
      paramLocationManager = paramLocationManager.getAllProviders();
    } while (paramLocationManager == null);
    return paramLocationManager.contains("passive");
  }
  
  public static int c()
  {
    if (a > 0) {
      return a;
    }
    i = 0;
    try
    {
      j = f.b("android.os.Build$VERSION", "SDK_INT");
      i = j;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          int j = Integer.parseInt(f.a("android.os.Build$VERSION", "SDK").toString());
          i = j;
        }
        catch (Exception localException2) {}
      }
    }
    a = i;
    return i;
  }
  
  public static NetworkInfo c(Context paramContext)
  {
    paramContext = (ConnectivityManager)a(paramContext, "connectivity");
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = paramContext.getActiveNetworkInfo();
        return paramContext;
      }
      catch (Exception paramContext)
      {
        return null;
      }
      paramContext = null;
    }
  }
  
  public static boolean c(LocationManager paramLocationManager)
  {
    if (paramLocationManager == null) {}
    do
    {
      return false;
      paramLocationManager = paramLocationManager.getAllProviders();
    } while (paramLocationManager == null);
    return paramLocationManager.contains("network");
  }
  
  public static String d()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public static boolean d(Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return false;
      try
      {
        localPackageManager = paramContext.getPackageManager();
        paramContext = null;
        i = 0;
        for (;;)
        {
          if (i >= 3) {
            break label76;
          }
          Object localObject1 = new String[] { "com.baidu.map.location", "com.amap.android.location", "android.htc.china.location.service" }[i];
          try
          {
            localObject1 = localPackageManager.getPackageInfo((String)localObject1, 4);
            paramContext = (Context)localObject1;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;) {}
          }
          if (paramContext != null) {
            break;
          }
          i += 1;
        }
        label76:
        if (c() >= 19)
        {
          paramContext = null;
          i = 0;
          while (i < 2)
          {
            localObject2 = new String[] { "GmsCore.apk", "PrebuiltGmsCore.apk" }[i];
            localObject2 = "/system/priv-app/" + (String)localObject2;
            try
            {
              localObject2 = localPackageManager.getPackageArchiveInfo((String)localObject2, 4);
              paramContext = (Context)localObject2;
            }
            catch (Exception localException1)
            {
              boolean bool;
              int j;
              for (;;) {}
            }
            if (paramContext != null)
            {
              bool = "com.google.android.gms".equals(paramContext.packageName);
              if (bool) {
                return true;
              }
            }
            i += 1;
          }
        }
      }
      catch (Throwable paramContext)
      {
        PackageManager localPackageManager;
        int i;
        Object localObject2;
        label188:
        return false;
      }
      try
      {
        localObject2 = localPackageManager.getPackageInfo("com.google.android.location", 4);
        paramContext = (Context)localObject2;
      }
      catch (Exception localException2)
      {
        break label188;
      }
      if (paramContext != null)
      {
        paramContext = paramContext.services;
        if (paramContext != null)
        {
          j = paramContext.length;
          i = 0;
          while (i < j)
          {
            localObject2 = paramContext[i];
            if (!TextUtils.isEmpty(((ServiceInfo)localObject2).name))
            {
              bool = ((ServiceInfo)localObject2).name.startsWith("com.google.android.location");
              if (bool) {
                return true;
              }
            }
            i += 1;
          }
        }
      }
    }
  }
  
  public static String e()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(d()).append(File.separator);
    localStringBuilder.append("apsamapapi").append(File.separator);
    return localStringBuilder.toString();
  }
  
  public static String e(Context paramContext)
  {
    if (paramContext == null) {
      paramContext = "";
    }
    Object localObject1;
    do
    {
      return paramContext;
      if (!TextUtils.isEmpty(c.d)) {
        return c.d;
      }
      Object localObject2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject1 = localObject2;
        if (!"9774d56d682e549c".equals(localObject2)) {}
      }
      else
      {
        localObject2 = paramContext.getSharedPreferences("pref", 0);
        paramContext = ((SharedPreferences)localObject2).getString("uuid", null);
        localObject1 = paramContext;
        if (TextUtils.isEmpty(paramContext))
        {
          int i = new Random().nextInt();
          localObject1 = "and" + ((i >>> 1) % 8999 + 1000) + System.currentTimeMillis();
          ((SharedPreferences)localObject2).edit().putString("uuid", (String)localObject1).commit();
        }
      }
      paramContext = (Context)localObject1;
    } while (!TextUtils.isEmpty(c.d));
    c.d = "";
    return localObject1;
  }
  
  public static String f()
  {
    return Build.MODEL;
  }
  
  public static String g()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static boolean h()
  {
    return a(0, 1) == 1;
  }
  
  public static void i()
  {
    c.clear();
  }
  
  public static String j()
  {
    try
    {
      String str = k.a(a.e, 2).substring(20);
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static b k()
  {
    b localB = new b();
    localB.b(0.0F);
    localB.b(-200.0D);
    localB.a(-200.0D);
    localB.a(a());
    return localB;
  }
}
