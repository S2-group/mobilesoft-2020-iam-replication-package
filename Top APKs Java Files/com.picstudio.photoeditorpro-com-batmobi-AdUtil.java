package com.batmobi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONObject;

public final class AdUtil
{
  private static String a;
  private static int b;
  private static String c;
  private static String d;
  private static String e;
  private static boolean f;
  private static String g;
  private static String h;
  private static String i;
  private static String j;
  private static String k = d.Y;
  private static final String l = d.Z;
  private static final String m = d.aa;
  private static final String n = d.ab;
  private static final String o = d.ac;
  private static String p = null;
  
  static
  {
    AdUtil.class.getName();
    b = -2;
    c = null;
    d = "";
    e = "";
    g = "";
    h = "";
    i = null;
    j = d.U;
    String str = d.V;
    str = d.W;
    str = d.X;
  }
  
  public AdUtil() {}
  
  public static String buildCreatives(String... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length <= 0)) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i2 = paramVarArgs.length;
    int i1 = 0;
    while (i1 < i2)
    {
      String str = paramVarArgs[i1];
      localStringBuilder.append(',').append(str);
      i1 += 1;
    }
    return localStringBuilder.deleteCharAt(0).toString();
  }
  
  public static String getAdvertisingId(Context paramContext)
  {
    if (i == null) {}
    try
    {
      String str = getSettingsSharedPreferences(paramContext).getString(n, "");
      i = str;
      if (TextUtils.isEmpty(str))
      {
        i = AdvertisingIdClient.getAdvertisingIdInfo$c24ace8(paramContext).a;
        paramContext = getSettingsSharedPreferences(paramContext).edit();
        paramContext.putString(n, i);
        paramContext.commit();
      }
      return i;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String getAndroidId(Context paramContext)
  {
    if (TextUtils.isEmpty(c)) {
      c = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    }
    return c;
  }
  
  public static String getAppVersion(Context paramContext)
  {
    if (a == null)
    {
      if (paramContext != null) {
        break label20;
      }
      a = d.aB;
    }
    for (;;)
    {
      return a;
      try
      {
        label20:
        a = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        a = d.aC;
      }
    }
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    if (b < 0)
    {
      if (paramContext != null) {
        break label18;
      }
      b = 0;
    }
    for (;;)
    {
      return b;
      try
      {
        label18:
        b = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        b = 0;
      }
    }
  }
  
  public static int getCPU()
  {
    return Runtime.getRuntime().availableProcessors();
  }
  
  public static String getCarrier(Context paramContext)
  {
    if (!f)
    {
      if (paramContext != null)
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getNetworkOperator();
          e = paramContext;
          if (TextUtils.isEmpty(paramContext)) {
            e = "";
          }
        }
      }
      f = true;
    }
    return e;
  }
  
  public static String getCountry(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {
        break label51;
      }
      paramContext = paramContext.getSimCountryIso().toUpperCase();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        Object localObject;
        paramContext.printStackTrace();
        label51:
        paramContext = "";
      }
    }
    localObject = paramContext;
    if (TextUtils.isEmpty(paramContext)) {
      localObject = Locale.getDefault().getCountry().toString().toUpperCase();
    }
    return localObject;
  }
  
  public static int getDeviceType(Context paramContext)
  {
    if ((paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3) {}
    for (int i1 = 1; i1 != 0; i1 = 0) {
      return 1;
    }
    return 0;
  }
  
  public static File getFile(Context paramContext, String paramString)
  {
    return new File(getFolder(paramContext), paramString);
  }
  
  public static File getFolder(Context paramContext)
  {
    paramContext = new File(paramContext.getFilesDir().getAbsolutePath(), d.aF);
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  public static String getIMEI(Context paramContext)
  {
    if (TextUtils.isEmpty(g)) {}
    try
    {
      g = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      return g;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
  }
  
  public static String getIMSI(Context paramContext)
  {
    if (TextUtils.isEmpty(d)) {}
    try
    {
      d = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      return d;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
  }
  
  public static String getInstallAppsAsString(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      paramContext = getInstalledApps(paramContext).iterator();
      while (paramContext.hasNext())
      {
        String str = (String)paramContext.next();
        localStringBuilder.append(',').append(str);
        continue;
        return localStringBuilder.toString();
      }
    }
    catch (Throwable paramContext) {}
    for (;;)
    {
      if (localStringBuilder.length() > 0) {
        localStringBuilder.deleteCharAt(0);
      }
    }
  }
  
  public static Set<String> getInstalledApps(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      int i1 = 0;
      if (i1 < paramContext.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i1);
        if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0) {
          localHashSet.add(localPackageInfo.packageName);
        }
        for (;;)
        {
          i1 += 1;
          break;
          if ((localPackageInfo.applicationInfo.flags & 0x80) != 0) {
            localHashSet.add(localPackageInfo.packageName);
          }
        }
      }
    }
    return localHashSet;
  }
  
  public static Location getLastKnownLocation(Context paramContext)
  {
    return null;
  }
  
  public static String getLastestFileName(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (p == null)
    {
      paramContext = getSharedPreferences(o, paramContext);
      paramContext.registerOnSharedPreferenceChangeListener(new b());
      p = paramContext.getString(l, "");
    }
    return p;
  }
  
  public static String getLatitude(Context paramContext)
  {
    paramContext = getLastKnownLocation(paramContext);
    if (paramContext != null) {
      return String.valueOf(paramContext.getLatitude());
    }
    return null;
  }
  
  public static String getLauguage(Context paramContext)
  {
    paramContext = Locale.getDefault();
    return String.format(d.az, new Object[] { paramContext.getLanguage().toLowerCase(), paramContext.getCountry().toLowerCase() });
  }
  
  public static String getLongitude(Context paramContext)
  {
    paramContext = getLastKnownLocation(paramContext);
    if (paramContext != null) {
      return String.valueOf(paramContext.getLongitude());
    }
    return null;
  }
  
  public static String getModelName()
  {
    if (TextUtils.isEmpty(h))
    {
      String str = getSystemProperties(d.ad);
      h = str;
      if (TextUtils.isEmpty(str)) {
        h = Build.MODEL;
      }
    }
    return h;
  }
  
  public static String getNetworkType(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        int i1;
        if (paramContext != null)
        {
          paramContext = paramContext.getActiveNetworkInfo();
          if ((paramContext != null) && (paramContext.isAvailable()) && (paramContext.getState() == NetworkInfo.State.CONNECTED)) {
            i1 = paramContext.getType();
          }
        }
        switch (i1)
        {
        default: 
          return k;
        case 1: 
          return j;
        case 0: 
          switch (paramContext.getSubtype())
          {
          }
          break;
        }
        return k;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return k;
      }
      return d.aj;
      return d.ak;
      return d.al;
      return d.am;
      return d.an;
      return d.ao;
      return d.ap;
      return d.aq;
      return d.ar;
      return d.as;
      return d.at;
      return d.au;
      return d.av;
      return d.aw;
      return d.ax;
      return d.ay;
      paramContext = k;
      return paramContext;
    }
  }
  
  public static String getOrientation(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    if (2 == paramContext.getResources().getConfiguration().orientation) {
      return d.aD;
    }
    return d.aE;
  }
  
  public static int getOsVersion(Context paramContext)
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getPackageName(Context paramContext)
  {
    return paramContext.getApplicationInfo().packageName;
  }
  
  public static String getRomSpace(Context paramContext)
  {
    try
    {
      paramContext = new StatFs(Environment.getDataDirectory().getPath());
      long l1 = paramContext.getBlockCount();
      long l2 = paramContext.getBlockSize();
      paramContext = l2 * l1 / 1024L / 1024L;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String getScreenSize(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    paramContext = (WindowManager)paramContext.getSystemService("window");
    int i1 = paramContext.getDefaultDisplay().getWidth();
    int i2 = paramContext.getDefaultDisplay().getHeight();
    return i1 + d.ai + i2;
  }
  
  public static SharedPreferences getSettingsSharedPreferences(Context paramContext)
  {
    return getSharedPreferences(o, paramContext);
  }
  
  public static SharedPreferences getSharedPreferences(String paramString, Context paramContext)
  {
    if (paramContext != null)
    {
      if (Build.VERSION.SDK_INT > 10) {
        return paramContext.getSharedPreferences(paramString, 4);
      }
      return paramContext.getSharedPreferences(paramString, 0);
    }
    return null;
  }
  
  public static String getSystemProperties(String paramString)
  {
    try
    {
      paramString = (String)Class.forName(d.ae).getDeclaredMethod(d.af, new Class[] { String.class, String.class }).invoke(null, new Object[] { paramString, "" });
      return paramString;
    }
    catch (SecurityException paramString)
    {
      paramString.printStackTrace();
      return "";
    }
    catch (IllegalArgumentException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (ClassNotFoundException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (NoSuchMethodException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (IllegalAccessException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (InvocationTargetException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public static String getTZ(Context paramContext)
  {
    try
    {
      paramContext = TimeZone.getDefault().getDisplayName(false, 0);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  /* Error */
  public static long getTotalMemory()
  {
    // Byte code:
    //   0: getstatic 623	com/batmobi/d:ag	Ljava/lang/String;
    //   3: astore 5
    //   5: new 625	java/io/BufferedReader
    //   8: dup
    //   9: new 627	java/io/FileReader
    //   12: dup
    //   13: aload 5
    //   15: invokespecial 628	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   18: sipush 8192
    //   21: invokespecial 631	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   24: astore 5
    //   26: aload 5
    //   28: invokevirtual 634	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   31: getstatic 637	com/batmobi/d:ah	Ljava/lang/String;
    //   34: invokevirtual 641	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   37: iconst_1
    //   38: aaload
    //   39: invokestatic 646	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   42: invokevirtual 649	java/lang/Integer:intValue	()I
    //   45: istore_0
    //   46: iload_0
    //   47: i2l
    //   48: lstore_1
    //   49: aload 5
    //   51: invokevirtual 652	java/io/BufferedReader:close	()V
    //   54: aload 5
    //   56: invokevirtual 652	java/io/BufferedReader:close	()V
    //   59: lload_1
    //   60: lstore_3
    //   61: lload_3
    //   62: ldc2_w 539
    //   65: ldiv
    //   66: lreturn
    //   67: astore 5
    //   69: aload 5
    //   71: invokevirtual 653	java/io/IOException:printStackTrace	()V
    //   74: lload_1
    //   75: lstore_3
    //   76: goto -15 -> 61
    //   79: astore 6
    //   81: aconst_null
    //   82: astore 5
    //   84: lconst_0
    //   85: lstore_1
    //   86: aload 6
    //   88: invokevirtual 497	java/lang/Exception:printStackTrace	()V
    //   91: lload_1
    //   92: lstore_3
    //   93: aload 5
    //   95: ifnull -34 -> 61
    //   98: aload 5
    //   100: invokevirtual 652	java/io/BufferedReader:close	()V
    //   103: lload_1
    //   104: lstore_3
    //   105: goto -44 -> 61
    //   108: astore 5
    //   110: aload 5
    //   112: invokevirtual 653	java/io/IOException:printStackTrace	()V
    //   115: lload_1
    //   116: lstore_3
    //   117: goto -56 -> 61
    //   120: astore 6
    //   122: aconst_null
    //   123: astore 5
    //   125: aload 5
    //   127: ifnull +8 -> 135
    //   130: aload 5
    //   132: invokevirtual 652	java/io/BufferedReader:close	()V
    //   135: aload 6
    //   137: athrow
    //   138: astore 5
    //   140: aload 5
    //   142: invokevirtual 653	java/io/IOException:printStackTrace	()V
    //   145: goto -10 -> 135
    //   148: astore 6
    //   150: goto -25 -> 125
    //   153: astore 6
    //   155: goto -30 -> 125
    //   158: astore 6
    //   160: lconst_0
    //   161: lstore_1
    //   162: goto -76 -> 86
    //   165: astore 6
    //   167: goto -81 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   45	2	0	i1	int
    //   48	114	1	l1	long
    //   60	57	3	l2	long
    //   3	52	5	localObject1	Object
    //   67	3	5	localIOException1	java.io.IOException
    //   82	17	5	localObject2	Object
    //   108	3	5	localIOException2	java.io.IOException
    //   123	8	5	localObject3	Object
    //   138	3	5	localIOException3	java.io.IOException
    //   79	8	6	localException1	Exception
    //   120	16	6	localObject4	Object
    //   148	1	6	localObject5	Object
    //   153	1	6	localObject6	Object
    //   158	1	6	localException2	Exception
    //   165	1	6	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   54	59	67	java/io/IOException
    //   5	26	79	java/lang/Exception
    //   98	103	108	java/io/IOException
    //   5	26	120	finally
    //   130	135	138	java/io/IOException
    //   26	46	148	finally
    //   49	54	148	finally
    //   86	91	153	finally
    //   26	46	158	java/lang/Exception
    //   49	54	165	java/lang/Exception
  }
  
  public static long getUpdateFileTime(Context paramContext)
  {
    if (paramContext == null) {
      return 0L;
    }
    return getSharedPreferences(o, paramContext).getLong(m, 0L);
  }
  
  public static String getUserAgent(Context paramContext)
  {
    return System.getProperty(d.aA);
  }
  
  public static String getValueFromMainifest(Context paramContext, String paramString)
  {
    Object localObject;
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      localObject = null;
    }
    for (;;)
    {
      return localObject;
      try
      {
        str = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString(paramString);
        localObject = str;
        if (str != null) {}
      }
      catch (Exception paramContext)
      {
        try
        {
          int i1 = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getInt(paramString, 0);
          localObject = str;
          if (i1 <= 0) {
            continue;
          }
          return String.valueOf(i1);
        }
        catch (Exception paramContext)
        {
          String str;
          for (;;) {}
        }
        paramContext = paramContext;
        str = "";
      }
    }
    paramContext.printStackTrace();
    return str;
  }
  
  public static boolean isNetworkOK(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext == null) || (!paramContext.isConnected())) {}
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isVisibleOnTree(View paramView)
  {
    boolean bool2 = true;
    while (paramView != null)
    {
      if (paramView.getVisibility() == 0) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        bool2 &= bool1;
        ViewParent localViewParent = paramView.getParent();
        if (localViewParent == paramView.getRootView()) {
          break label45;
        }
        paramView = (View)localViewParent;
        break;
      }
      label45:
      paramView = null;
    }
    return bool2;
  }
  
  public static String optString(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if (paramJSONObject.isNull(paramString1)) {
      return paramString2;
    }
    return paramJSONObject.optString(paramString1, paramString2);
  }
  
  public static void setLastestFileName(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return;
    }
    p = paramString;
    paramContext = getSharedPreferences(o, paramContext).edit();
    paramContext.putString(l, paramString);
    paramContext.commit();
  }
  
  public static void setUpdateFileTime(Context paramContext, long paramLong)
  {
    if ((paramContext == null) || (paramLong < 1L)) {
      return;
    }
    paramContext = getSharedPreferences(o, paramContext).edit();
    paramContext.putLong(m, paramLong);
    paramContext.commit();
  }
}
