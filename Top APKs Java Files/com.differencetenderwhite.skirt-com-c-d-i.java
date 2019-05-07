package com.c.d;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class i
{
  public static boolean a = false;
  public static Context b;
  public static Map c = new HashMap();
  private static String d = "Cocos2dxPrefsFiles";
  private static final char[] e;
  private static Random f;
  
  static
  {
    b = null;
    e = new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
    f = new Random();
  }
  
  public static float a(Activity paramActivity, float paramFloat)
  {
    int i = 0;
    try
    {
      int j = Build.VERSION.SDK_INT;
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      j = localDisplayMetrics.widthPixels;
      i = j;
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        paramActivity.printStackTrace();
      }
    }
    return i / paramFloat;
  }
  
  public static int a(JSONArray paramJSONArray, int paramInt)
  {
    try
    {
      paramInt = paramJSONArray.getInt(paramInt);
      return paramInt;
    }
    catch (Exception paramJSONArray) {}
    return 0;
  }
  
  public static int a(int[] paramArrayOfInt)
  {
    int j = 0;
    int i = 0;
    while (j < paramArrayOfInt.length)
    {
      i += paramArrayOfInt[j];
      j += 1;
    }
    j = i;
    if (i <= 0) {
      j = 1;
    }
    int k = f.nextInt(j);
    i = 0;
    j = 0;
    while (i < paramArrayOfInt.length)
    {
      j += paramArrayOfInt[i];
      if (k < j) {
        return i;
      }
      i += 1;
    }
    return 0;
  }
  
  @SuppressLint({"NewApi"})
  public static long a(Bitmap paramBitmap)
  {
    if (Build.VERSION.SDK_INT >= 12) {
      return paramBitmap.getByteCount();
    }
    return paramBitmap.getRowBytes() * paramBitmap.getHeight();
  }
  
  public static Bitmap a(Uri paramUri)
  {
    try
    {
      paramUri = a(paramUri.toString());
      if (paramUri != null)
      {
        Bitmap localBitmap = BitmapFactory.decodeStream(paramUri);
        paramUri.close();
        return localBitmap;
      }
    }
    catch (Exception paramUri) {}
    return null;
  }
  
  public static InputStream a(String paramString)
  {
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setRequestMethod("GET");
      paramString.setDoInput(true);
      paramString.connect();
      paramString = paramString.getInputStream();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static InputStream a(String[] paramArrayOfString)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      Object localObject = paramArrayOfString[i];
      try
      {
        localObject = (HttpURLConnection)new URL((String)localObject).openConnection();
        ((HttpURLConnection)localObject).setRequestMethod("GET");
        ((HttpURLConnection)localObject).setDoInput(true);
        ((HttpURLConnection)localObject).connect();
        localObject = ((HttpURLConnection)localObject).getInputStream();
        return localObject;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        i += 1;
      }
    }
    return null;
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = AdvertisingIdClient.getAdvertisingIdInfo(paramContext);
      String str = paramContext.getId();
      paramContext.isLimitAdTrackingEnabled();
      return str;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String a(InputStream paramInputStream)
  {
    localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      for (;;)
      {
        int i = paramInputStream.read();
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(i);
      }
      return localByteArrayOutputStream.toString();
    }
    catch (IOException paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
  }
  
  public static String a(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString(paramString);
      return paramJSONObject;
    }
    catch (Exception paramJSONObject) {}
    return "";
  }
  
  public static void a() {}
  
  public static void a(Activity paramActivity, String paramString)
  {
    try
    {
      paramString = paramActivity.getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      do
      {
        for (;;)
        {
          paramString.printStackTrace();
          paramString = null;
        }
        localObject = new Intent("android.intent.action.MAIN", null);
        ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
        ((Intent)localObject).setPackage(paramString.packageName);
        localObject = (ResolveInfo)paramActivity.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
      } while (localObject == null);
      paramString = ((ResolveInfo)localObject).activityInfo.packageName;
      Object localObject = ((ResolveInfo)localObject).activityInfo.name;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName(paramString, (String)localObject));
      paramActivity.startActivity(localIntent);
    }
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, int paramInt)
  {
    paramActivity = paramActivity.getSharedPreferences(paramString1, 0).edit();
    paramActivity.putInt(paramString2, paramInt);
    paramActivity.commit();
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    paramActivity = paramActivity.getSharedPreferences(paramString1, 0).edit();
    paramActivity.putString(paramString2, paramString3);
    paramActivity.commit();
  }
  
  public static void a(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage("com.android.vending");
      localIntent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"));
      localIntent.setData(Uri.parse("market://details?id=" + paramString));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramString)));
    }
  }
  
  public static void a(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences(d, 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  public static void a(String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences(d, 0).edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }
  
  public static void a(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences(d, 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  public static void a(String paramString, Set paramSet)
  {
    int i = 0;
    SharedPreferences.Editor localEditor = b.getSharedPreferences(d, 0).edit();
    String str = "";
    Object localObject = str;
    if (paramSet != null)
    {
      localObject = str;
      if (!paramSet.isEmpty())
      {
        Object[] arrayOfObject = paramSet.toArray();
        int j = arrayOfObject.length;
        paramSet = str;
        for (;;)
        {
          localObject = paramSet;
          if (i >= j) {
            break;
          }
          localObject = arrayOfObject[i];
          paramSet = paramSet + localObject.toString();
          paramSet = paramSet + "&";
          i += 1;
        }
      }
    }
    localEditor.putString(paramString, (String)localObject);
    localEditor.commit();
  }
  
  public static String[] a(Activity paramActivity, String paramString1, String paramString2)
  {
    paramString1 = paramActivity.getSharedPreferences(paramString1, 0);
    paramActivity = new String[1];
    paramActivity[0] = "";
    paramString1 = paramString1.getString(paramString2, "");
    if (!paramString1.equals("")) {
      paramActivity = paramString1.split("&");
    }
    return paramActivity;
  }
  
  public static int b(Context paramContext)
  {
    int k = 0;
    List localList = paramContext.getPackageManager().getInstalledPackages(8192);
    int i = 0;
    for (;;)
    {
      int j = k;
      if (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if (paramContext.getPackageName().compareTo(localPackageInfo.packageName) == 0) {
          j = (int)((System.currentTimeMillis() - localPackageInfo.firstInstallTime) / 86400000L);
        }
      }
      else
      {
        return j;
      }
      i += 1;
    }
  }
  
  public static int b(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int i = paramJSONObject.getInt(paramString);
      return i;
    }
    catch (Exception paramJSONObject) {}
    return 0;
  }
  
  public static String b(Activity paramActivity, String paramString1, String paramString2)
  {
    return paramActivity.getSharedPreferences(paramString1, 0).getString(paramString2, "");
  }
  
  public static String b(String paramString)
  {
    try
    {
      paramString = new ByteArrayInputStream(g.a(Base64.decode(paramString, 10), "red12345679"));
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      c.a(paramString, localByteArrayOutputStream);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.flush();
      localByteArrayOutputStream.close();
      paramString.close();
      paramString = new String(arrayOfByte);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static String b(JSONArray paramJSONArray, int paramInt)
  {
    try
    {
      paramJSONArray = paramJSONArray.getString(paramInt);
      return paramJSONArray;
    }
    catch (Exception paramJSONArray) {}
    return "";
  }
  
  public static JSONObject b(InputStream paramInputStream)
  {
    try
    {
      paramInputStream = a(paramInputStream);
      if (paramInputStream.equals("0")) {
        return null;
      }
      paramInputStream = new JSONObject(paramInputStream);
      return paramInputStream;
    }
    catch (JSONException paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
    return null;
  }
  
  public static void b() {}
  
  public static void b(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage("com.android.vending");
      localIntent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"));
      localIntent.setData(Uri.parse("market://details?id=" + paramString + "&referrer=pid%3Dpush"));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramString + "&referrer=pid%3Dpush")));
    }
  }
  
  private static boolean b(String paramString, int paramInt)
  {
    long l2 = new Date().getTime() / 1000L;
    Long localLong = (Long)c.get(paramString);
    if (localLong != null) {}
    for (long l1 = localLong.longValue();; l1 = 0L)
    {
      new StringBuilder("position:").append(paramString).append(",类型:").append(paramInt).append(",时间间隔::").append(l2 - l1);
      return (l2 - l1 > paramInt) || (l2 < l1);
    }
  }
  
  public static int c(Activity paramActivity, String paramString1, String paramString2)
  {
    return paramActivity.getSharedPreferences(paramString1, 0).getInt(paramString2, 0);
  }
  
  public static long c()
  {
    return new Date().getTime() / 1000L;
  }
  
  public static String c(String paramString)
  {
    if (paramString == null) {}
    for (paramString = null;; paramString = g.a(paramString.getBytes(), "red12345679")) {
      try
      {
        return Base64.encodeToString(paramString, 10);
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return "";
  }
  
  public static JSONObject c(JSONObject paramJSONObject, String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      paramJSONObject = paramJSONObject.getJSONObject(paramString);
      return paramJSONObject;
    }
    catch (Exception paramJSONObject) {}
    return localJSONObject;
  }
  
  public static boolean c(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {}
    for (;;)
    {
      return false;
      paramContext = paramContext.getAllNetworkInfo();
      if (paramContext != null)
      {
        int i = 0;
        while (i < paramContext.length)
        {
          if (paramContext[i].getState() == NetworkInfo.State.CONNECTED)
          {
            Object localObject = paramContext[i];
            if (localObject.getType() == 1) {
              return true;
            }
            if (localObject.getType() == 0) {
              return true;
            }
          }
          i += 1;
        }
      }
    }
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static int d(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static long d()
  {
    return new Date().getTime() / 60000L;
  }
  
  public static String d(String paramString)
  {
    return b.getSharedPreferences(d, 0).getString(paramString, "");
  }
  
  public static JSONArray d(JSONObject paramJSONObject, String paramString)
  {
    JSONArray localJSONArray = new JSONArray();
    try
    {
      paramJSONObject = paramJSONObject.getJSONArray(paramString);
      return paramJSONObject;
    }
    catch (Exception paramJSONObject) {}
    return localJSONArray;
  }
  
  public static String e()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = e.length;
    int i = 0;
    while (i < 50)
    {
      int k = f.nextInt(j);
      localStringBuilder.append(e[k]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  @TargetApi(17)
  public static String e(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    if (Build.VERSION.SDK_INT >= 17)
    {
      Point localPoint = new Point();
      paramContext.getRealSize(localPoint);
      return localPoint.x + "x" + localPoint.y;
    }
    return paramContext.getWidth() + "x" + paramContext.getHeight();
  }
  
  public static Set e(String paramString)
  {
    int i = 0;
    SharedPreferences localSharedPreferences = b.getSharedPreferences(d, 0);
    HashSet localHashSet = new HashSet();
    paramString = localSharedPreferences.getString(paramString, "");
    if (!paramString.equals(""))
    {
      paramString = paramString.split("&");
      int j = paramString.length;
      while (i < j)
      {
        localSharedPreferences = paramString[i];
        if (!localSharedPreferences.equals("")) {
          localHashSet.add(localSharedPreferences);
        }
        i += 1;
      }
    }
    return localHashSet;
  }
  
  public static Long f(String paramString)
  {
    return Long.valueOf(b.getSharedPreferences(d, 0).getLong(paramString, 0L));
  }
  
  public static boolean f()
  {
    boolean bool = false;
    if (b.getResources().getConfiguration().orientation == 2) {
      bool = true;
    }
    return bool;
  }
  
  public static int g(String paramString)
  {
    return b.getSharedPreferences(d, 0).getInt(paramString, 0);
  }
  
  public static long g()
  {
    return new Date().getTime() / 1000L;
  }
  
  public static void h(String paramString)
  {
    SharedPreferences.Editor localEditor = b.getSharedPreferences(d, 0).edit();
    localEditor.putBoolean(paramString, true);
    localEditor.commit();
  }
  
  public static boolean i(String paramString)
  {
    return b.getSharedPreferences(d, 0).getBoolean(paramString, false);
  }
  
  public static boolean j(String paramString)
  {
    try
    {
      b.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static void k(String paramString)
  {
    try
    {
      Intent localIntent = b.getPackageManager().getLaunchIntentForPackage("com.android.vending");
      localIntent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"));
      localIntent.setData(Uri.parse("market://details?id=" + paramString));
      b.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramString)));
    }
  }
  
  public static void l(String paramString)
  {
    long l = new Date().getTime() / 1000L;
    c.put(paramString, Long.valueOf(l));
    new StringBuilder("position:").append(paramString).append(",longtime:").append(l);
  }
  
  public static boolean m(String paramString)
  {
    return b(paramString, 3000);
  }
  
  public static boolean n(String paramString)
  {
    return b(paramString, 3600);
  }
  
  public static void o(String paramString)
  {
    c.put(paramString, Long.valueOf(0L));
  }
  
  public static String p(String paramString)
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    for (;;)
    {
      int i;
      try
      {
        paramString = Base64.encodeToString(c.a(paramString.getBytes()), 10);
        localObject1 = localObject2;
        int j = paramString.length();
        localObject1 = localObject2;
        char[] arrayOfChar = paramString.toCharArray();
        i = 0;
        paramString = (String)localObject2;
        localObject1 = paramString;
        if (i < j)
        {
          localObject2 = paramString;
          if (i % 10 == 0)
          {
            localObject1 = paramString;
            localObject2 = new StringBuilder().append(paramString);
            localObject1 = paramString;
            int k = e.length;
            localObject1 = paramString;
            k = f.nextInt(k);
            localObject1 = paramString;
            localObject2 = e[k];
          }
          if (i + 1 < j)
          {
            localObject1 = localObject2;
            paramString = (String)localObject2 + arrayOfChar[(i + 1)] + arrayOfChar[i];
          }
          else
          {
            localObject1 = localObject2;
            paramString = (String)localObject2 + arrayOfChar[i];
          }
        }
      }
      catch (Exception paramString) {}
      return localObject1;
      i += 2;
    }
  }
}
