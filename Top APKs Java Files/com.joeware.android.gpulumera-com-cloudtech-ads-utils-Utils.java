package com.cloudtech.ads.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.annotation.Keep;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Utils
{
  private static String a = "";
  private static String b = "";
  private static String c = "";
  private static final AtomicInteger d = new AtomicInteger(1);
  private static boolean e = false;
  private static boolean f = false;
  private static boolean g = false;
  private static String h;
  
  public Utils() {}
  
  public static BitmapDrawable a()
  {
    Object localObject = Base64.decode("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", 0);
    localObject = BitmapFactory.decodeByteArray((byte[])localObject, 0, localObject.length);
    if (localObject == null) {
      return null;
    }
    return new BitmapDrawable((Bitmap)localObject);
  }
  
  public static Drawable a(int paramInt)
  {
    return ContextHolder.getGlobalAppContext().getResources().getDrawable(paramInt);
  }
  
  /* Error */
  private static ArrayList<String> a(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: new 74	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 75	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: ldc 77
    //   10: ldc 79
    //   12: invokestatic 85	com/cloudtech/ads/utils/YeLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   15: invokestatic 91	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   18: aload_0
    //   19: invokevirtual 95	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   22: astore_0
    //   23: new 97	java/io/BufferedWriter
    //   26: dup
    //   27: new 99	java/io/OutputStreamWriter
    //   30: dup
    //   31: aload_0
    //   32: invokevirtual 105	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   35: invokespecial 108	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   38: invokespecial 111	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   41: pop
    //   42: new 113	java/io/BufferedReader
    //   45: dup
    //   46: new 115	java/io/InputStreamReader
    //   49: dup
    //   50: aload_0
    //   51: invokevirtual 119	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   54: invokespecial 122	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   57: invokespecial 125	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   60: astore_0
    //   61: aload_0
    //   62: invokevirtual 129	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   65: astore_2
    //   66: aload_2
    //   67: ifnull +42 -> 109
    //   70: new 131	java/lang/StringBuilder
    //   73: dup
    //   74: ldc -123
    //   76: invokespecial 136	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   79: astore_3
    //   80: aload_3
    //   81: aload_2
    //   82: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: ldc 77
    //   88: aload_3
    //   89: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokestatic 85	com/cloudtech/ads/utils/YeLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   95: aload_1
    //   96: aload_2
    //   97: invokevirtual 147	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   100: pop
    //   101: goto -40 -> 61
    //   104: astore_0
    //   105: aload_0
    //   106: invokestatic 153	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   109: new 131	java/lang/StringBuilder
    //   112: dup
    //   113: ldc -101
    //   115: invokespecial 136	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   118: astore_0
    //   119: aload_0
    //   120: aload_1
    //   121: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   124: pop
    //   125: ldc 77
    //   127: aload_0
    //   128: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: invokestatic 85	com/cloudtech/ads/utils/YeLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   134: aload_1
    //   135: areturn
    //   136: aconst_null
    //   137: areturn
    //   138: astore_0
    //   139: goto -3 -> 136
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	paramArrayOfString	String[]
    //   7	128	1	localArrayList	ArrayList
    //   65	32	2	str	String
    //   79	10	3	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   61	66	104	java/lang/Exception
    //   70	101	104	java/lang/Exception
    //   8	23	138	java/lang/Exception
  }
  
  public static List<String> a(List<String> paramList)
  {
    return a(paramList, false, false);
  }
  
  public static List<String> a(List<String> paramList, boolean paramBoolean1, boolean paramBoolean2)
  {
    ArrayList localArrayList = new ArrayList();
    long l = System.currentTimeMillis();
    int j = 0;
    Object localObject;
    int i;
    if (paramBoolean1)
    {
      localObject = new StringBuilder("isNormal::");
      ((StringBuilder)localObject).append(paramBoolean2);
      YeLog.d(((StringBuilder)localObject).toString());
      i = 100;
      if (paramBoolean2)
      {
        if (l % 1000L / 100L % 2L == 0L) {
          i = 0;
        }
        l += i;
        i = j;
      }
      else
      {
        if (l % 1000L / 100L % 2L != 0L) {
          i = 0;
        }
        l += i;
        i = j;
      }
    }
    else
    {
      i = j;
    }
    while (i < paramList.size())
    {
      localObject = (String)paramList.get(i);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append("&ts=");
      localStringBuilder.append(l);
      localArrayList.add(localStringBuilder.toString());
      i += 1;
    }
    return localArrayList;
  }
  
  public static void a(View paramView)
  {
    if (paramView == null) {
      return;
    }
    paramView = paramView.getParent();
    if (paramView == null) {
      return;
    }
    if ((paramView instanceof AdapterView)) {
      return;
    }
    ((ViewGroup)paramView).removeAllViews();
  }
  
  private static void a(ViewGroup paramViewGroup, List<View> paramList)
  {
    int i = 0;
    while (i < paramViewGroup.getChildCount())
    {
      View localView = paramViewGroup.getChildAt(i);
      paramList.add(localView);
      if ((localView instanceof ViewGroup)) {
        a((ViewGroup)localView, paramList);
      }
      i += 1;
    }
  }
  
  public static void a(StringBuilder paramStringBuilder, Map<String, String> paramMap, boolean paramBoolean)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = (String)paramMap.get(str1);
      if ((!b(str1)) && (!b(str2)) && (!str2.equals("null")))
      {
        if (paramBoolean)
        {
          paramBoolean = false;
          paramStringBuilder.append("?");
        }
        else
        {
          paramStringBuilder.append("&");
        }
        paramStringBuilder.append(d(str1));
        paramStringBuilder.append("=");
        paramStringBuilder.append(d(str2));
      }
    }
  }
  
  public static boolean a(String paramString)
  {
    return (paramString != null) && (!"".equals(paramString));
  }
  
  public static int[] a(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    if (paramContext == null) {
      return new int[] { 0, 0 };
    }
    return new int[] { paramContext.widthPixels, paramContext.heightPixels };
  }
  
  @Keep
  public static void appendUrlParameter(StringBuilder paramStringBuilder, Map<String, String> paramMap)
  {
    a(paramStringBuilder, paramMap, true);
  }
  
  public static int b(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, Resources.getSystem().getDisplayMetrics());
  }
  
  public static String b(List<String> paramList)
  {
    if ((paramList != null) && (paramList.size() != 0))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        if (!b(str))
        {
          localStringBuilder.append(str);
          localStringBuilder.append(",");
        }
      }
      return localStringBuilder.substring(0, localStringBuilder.length() - 1);
    }
    return "";
  }
  
  public static List<View> b(View paramView)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramView);
    if ((paramView instanceof ViewGroup)) {
      a((ViewGroup)paramView, localArrayList);
    }
    return localArrayList;
  }
  
  private static boolean b()
  {
    try
    {
      if (new File("/system/app/Superuser.apk").exists())
      {
        YeLog.i("Utils", "/system/app/Superuser.apk exist");
        return true;
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo("com.android.vending", 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean b(String paramString)
  {
    return (paramString == null) || ("".equals(paramString));
  }
  
  public static String c(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkCountryIso();
  }
  
  public static void c(String paramString)
  {
    try
    {
      localObject = (ClipboardManager)ContextHolder.getGlobalAppContext().getSystemService("clipboard");
      paramString = ClipData.newPlainText("Label", paramString);
      if (localObject != null) {
        ((ClipboardManager)localObject).setPrimaryClip(paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      Object localObject = new StringBuilder("setClipboard: ");
      ((StringBuilder)localObject).append(paramString.getMessage());
      YeLog.i(((StringBuilder)localObject).toString());
    }
  }
  
  private static boolean c()
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = "/system/bin/";
    arrayOfString[1] = "/system/xbin/";
    arrayOfString[2] = "/system/sbin/";
    arrayOfString[3] = "/sbin/";
    arrayOfString[4] = "/vendor/bin/";
    int i = 0;
    for (;;)
    {
      if (i < 5) {
        try
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(arrayOfString[i]);
          localStringBuilder.append("su");
          if (new File(localStringBuilder.toString()).exists())
          {
            localStringBuilder = new StringBuilder("find su in : ");
            localStringBuilder.append(arrayOfString[i]);
            YeLog.i("Utils", localStringBuilder.toString());
            return true;
          }
          i += 1;
        }
        catch (Exception localException)
        {
          ThrowableExtension.printStackTrace(localException);
        }
      }
    }
    return false;
  }
  
  @Keep
  public static Context checkAndSaveContext(Context paramContext)
  {
    ContextHolder.init(paramContext);
    return ContextHolder.getGlobalAppContext();
  }
  
  public static int d(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    int k = paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
    int j = 8;
    int i = j;
    if (k == 0)
    {
      paramContext = localConnectivityManager.getActiveNetworkInfo();
      i = j;
      if (paramContext != null) {
        i = paramContext.getType();
      }
    }
    return i;
  }
  
  private static String d(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new UnsupportedOperationException(paramString);
    }
  }
  
  public static String e(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(a))
        {
          paramContext = a;
          return paramContext;
        }
        paramContext = n(paramContext);
        if ((paramContext != null) && (!TextUtils.isEmpty(paramContext)) && (!paramContext.equals("null")))
        {
          int i = Math.min(3, paramContext.length());
          if (paramContext == null) {
            paramContext = "";
          } else {
            paramContext = paramContext.substring(0, i);
          }
          a = paramContext;
          return paramContext;
        }
      }
      finally {}
      paramContext = "";
    }
  }
  
  public static String f(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(b))
        {
          paramContext = b;
          return paramContext;
        }
        paramContext = n(paramContext);
        if ((paramContext != null) && (!TextUtils.isEmpty(paramContext)) && (!paramContext.equals("null")))
        {
          int i = Math.min(3, paramContext.length());
          if (paramContext == null) {
            paramContext = "";
          } else {
            paramContext = paramContext.substring(i);
          }
          b = paramContext;
          return paramContext;
        }
      }
      finally {}
      paramContext = "";
    }
  }
  
  public static String g(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  @Keep
  public static int generateViewId()
  {
    int k;
    int i;
    do
    {
      k = d.get();
      int j = k + 1;
      i = j;
      if (j > 16777215) {
        i = 1;
      }
    } while (!d.compareAndSet(k, i));
    return k;
  }
  
  @Keep
  public static String getAndroidId(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    YeLog.e(String.format("[msg=get AndroidId][result=fail]", new Object[0]));
    return "";
  }
  
  @Keep
  public static String getAppPackageName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).packageName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      StringBuilder localStringBuilder = new StringBuilder("工具-应用包名 Error=");
      localStringBuilder.append(paramContext.getMessage());
      YeLog.e(localStringBuilder.toString());
    }
    return "";
  }
  
  @Keep
  public static String getProcessName(Context paramContext, int paramInt)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == paramInt) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  public static List<String> h(Context paramContext)
  {
    localArrayList = new ArrayList();
    YeLog.d("getInstalledApps::");
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      Object localObject;
      while (paramContext.hasNext())
      {
        localObject = (ApplicationInfo)paramContext.next();
        if ((((ApplicationInfo)localObject).flags & 0x1) == 0) {
          localArrayList.add(((ApplicationInfo)localObject).packageName);
        }
      }
      return localArrayList;
    }
    catch (Throwable paramContext)
    {
      localObject = new StringBuilder("getInstalledApps::");
      ((StringBuilder)localObject).append(paramContext.getMessage());
      YeLog.d(((StringBuilder)localObject).toString());
    }
  }
  
  public static String i(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Intent localIntent = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    int i = localIntent.getIntExtra("status", -1);
    int j = 0;
    if (i == 2) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      str = "1";
    } else {
      str = "0";
    }
    localStringBuilder.append(str);
    localStringBuilder.append(",");
    localStringBuilder.append(String.valueOf(localIntent.getIntExtra("level", -1)));
    localStringBuilder.append(",");
    int k = localIntent.getIntExtra("plugged", -1);
    if (k == 2) {
      i = 1;
    } else {
      i = 0;
    }
    if (k == 1) {
      j = 1;
    }
    String str = "0";
    if (i != 0) {
      str = "1";
    } else if (j != 0) {
      str = "2";
    }
    localStringBuilder.append(str);
    localStringBuilder.append(",");
    if (o(paramContext)) {
      paramContext = "0";
    } else {
      paramContext = "1";
    }
    localStringBuilder.append(paramContext);
    return localStringBuilder.toString();
  }
  
  @Keep
  public static boolean isNetEnable(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnected();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      StringBuilder localStringBuilder = new StringBuilder("工具-NetIsOn Error=");
      localStringBuilder.append(paramContext.getMessage());
      YeLog.e(localStringBuilder.toString());
    }
    return false;
  }
  
  public static int j(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Throwable paramContext)
    {
      YeLog.e(paramContext.getMessage());
    }
    return Integer.MAX_VALUE;
  }
  
  public static int k(Context paramContext)
  {
    if (p(paramContext)) {
      i = 4;
    } else {
      i = 0;
    }
    boolean bool = g;
    int k = 1;
    if (!bool)
    {
      Object localObject = Build.TAGS;
      StringBuilder localStringBuilder;
      if ((localObject != null) && (((String)localObject).contains("test-keys")))
      {
        localStringBuilder = new StringBuilder("buildTags=");
        localStringBuilder.append((String)localObject);
        YeLog.i("Utils", localStringBuilder.toString());
        j = 1;
      }
      else
      {
        j = 0;
      }
      if ((j == 0) && (!b()) && (!c()))
      {
        localObject = a(new String[] { "/system/xbin/which", "su" });
        if (localObject != null)
        {
          localStringBuilder = new StringBuilder("execResult=");
          localStringBuilder.append(((ArrayList)localObject).toString());
          YeLog.i("Utils", localStringBuilder.toString());
          j = 1;
        }
        else
        {
          YeLog.i("Utils", "execResult=null");
          j = 0;
        }
        if (j == 0) {
          k = 0;
        }
      }
    }
    int j = i;
    if (k != 0) {
      j = i | 0x2;
    }
    int i = j;
    if (q(paramContext)) {
      i = j | 0x1;
    }
    return i;
  }
  
  public static boolean l(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  /* Error */
  public static String m(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 625	com/cloudtech/ads/utils/Utils:h	Ljava/lang/String;
    //   3: invokestatic 440	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifeq +86 -> 92
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore_1
    //   13: new 627	android/webkit/WebView
    //   16: dup
    //   17: aload_0
    //   18: invokespecial 629	android/webkit/WebView:<init>	(Landroid/content/Context;)V
    //   21: astore_0
    //   22: aload_0
    //   23: invokevirtual 633	android/webkit/WebView:getSettings	()Landroid/webkit/WebSettings;
    //   26: invokevirtual 638	android/webkit/WebSettings:getUserAgentString	()Ljava/lang/String;
    //   29: invokevirtual 642	java/lang/String:getBytes	()[B
    //   32: iconst_0
    //   33: invokestatic 646	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   36: putstatic 625	com/cloudtech/ads/utils/Utils:h	Ljava/lang/String;
    //   39: aload_0
    //   40: invokevirtual 649	android/webkit/WebView:destroy	()V
    //   43: goto +49 -> 92
    //   46: astore_1
    //   47: goto +35 -> 82
    //   50: astore_2
    //   51: goto +14 -> 65
    //   54: astore_2
    //   55: aload_1
    //   56: astore_0
    //   57: aload_2
    //   58: astore_1
    //   59: goto +23 -> 82
    //   62: astore_2
    //   63: aload_3
    //   64: astore_0
    //   65: aload_0
    //   66: astore_1
    //   67: aload_2
    //   68: invokestatic 153	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   71: aload_0
    //   72: ifnull +20 -> 92
    //   75: aload_0
    //   76: invokevirtual 649	android/webkit/WebView:destroy	()V
    //   79: goto +13 -> 92
    //   82: aload_0
    //   83: ifnull +7 -> 90
    //   86: aload_0
    //   87: invokevirtual 649	android/webkit/WebView:destroy	()V
    //   90: aload_1
    //   91: athrow
    //   92: getstatic 625	com/cloudtech/ads/utils/Utils:h	Ljava/lang/String;
    //   95: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	paramContext	Context
    //   12	1	1	localObject1	Object
    //   46	10	1	localObject2	Object
    //   58	33	1	localObject3	Object
    //   50	1	2	localException1	Exception
    //   54	4	2	localObject4	Object
    //   62	6	2	localException2	Exception
    //   10	54	3	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   22	39	46	finally
    //   22	39	50	java/lang/Exception
    //   13	22	54	finally
    //   67	71	54	finally
    //   13	22	62	java/lang/Exception
  }
  
  private static String n(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    String str = localTelephonyManager.getNetworkOperator();
    paramContext = str;
    if (localTelephonyManager.getPhoneType() == 2)
    {
      paramContext = str;
      if (localTelephonyManager.getSimState() == 5) {
        paramContext = localTelephonyManager.getSimOperator();
      }
    }
    return paramContext;
  }
  
  private static boolean o(Context paramContext)
  {
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      return (i & 0x2) != 0;
    }
    catch (Exception paramContext)
    {
      YeLog.e(paramContext.getMessage());
    }
    return false;
  }
  
  @Keep
  public static List<String> optStringArrayHelper(JSONObject paramJSONObject, String... paramVarArgs)
  {
    if (paramJSONObject == null) {
      return Collections.EMPTY_LIST;
    }
    int j = 0;
    int i = 0;
    while (i < paramVarArgs.length - 1)
    {
      paramJSONObject = paramJSONObject.optJSONObject(paramVarArgs[i]);
      if (paramJSONObject == null) {
        return Collections.EMPTY_LIST;
      }
      i += 1;
    }
    if (paramJSONObject == null) {
      return Collections.EMPTY_LIST;
    }
    paramJSONObject = paramJSONObject.optJSONArray(paramVarArgs[(paramVarArgs.length - 1)]);
    if (paramJSONObject == null) {
      return Collections.EMPTY_LIST;
    }
    paramVarArgs = new ArrayList();
    i = j;
    while (i < paramJSONObject.length())
    {
      paramVarArgs.add(paramJSONObject.optString(i));
      i += 1;
    }
    return paramVarArgs;
  }
  
  @Keep
  public static String optStringHelper(JSONObject paramJSONObject, String... paramVarArgs)
  {
    Object localObject = null;
    if (paramJSONObject == null)
    {
      paramJSONObject = localObject;
    }
    else
    {
      int i = 0;
      while (i < paramVarArgs.length - 1)
      {
        paramJSONObject = paramJSONObject.optJSONObject(paramVarArgs[i]);
        if (paramJSONObject == null)
        {
          paramJSONObject = localObject;
          break label65;
        }
        i += 1;
      }
      if (paramJSONObject == null) {
        paramJSONObject = localObject;
      } else {
        paramJSONObject = paramJSONObject.optString(paramVarArgs[(paramVarArgs.length - 1)]);
      }
    }
    label65:
    paramVarArgs = paramJSONObject;
    if (paramJSONObject == null) {
      paramVarArgs = "";
    }
    return paramVarArgs;
  }
  
  private static boolean p(Context paramContext)
  {
    if (!e)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        ThrowableExtension.printStackTrace(paramContext);
        paramContext = null;
      }
      int i = paramContext.applicationInfo.flags;
      boolean bool = true;
      if ((i & 0x1) == 0) {
        bool = false;
      }
      e = bool;
    }
    return e;
  }
  
  private static boolean q(Context paramContext)
  {
    if (f) {
      return true;
    }
    String str = paramContext.getPackageName();
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
    if (!TextUtils.isEmpty(paramContext))
    {
      paramContext = paramContext.split(":");
      int i = 0;
      while (i < paramContext.length)
      {
        ComponentName localComponentName = ComponentName.unflattenFromString(paramContext[i]);
        if ((localComponentName != null) && (TextUtils.equals(str, localComponentName.getPackageName()))) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  @Keep
  public static String stream2String(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    paramInputStream = localByteArrayOutputStream.toString();
    localByteArrayOutputStream.close();
    return paramInputStream;
  }
}
